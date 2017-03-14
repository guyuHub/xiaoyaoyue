package cn.com.doit.login.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.apache.catalina.security.SecurityUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Operation;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;

import cn.com.doit.login.service.LoginService;
import cn.com.doit.pojo.login.student_info;
import cn.com.doit.pojo.login.student_infoExample;
import cn.com.doit.pojo.login.user_info;
import cn.com.doit.pojo.login.mapper.student_infoMapper;
import cn.com.doit.util.JiaMiUtil;

@Configuration(value = "loginService")
public class LoginServiceImpl implements LoginService {
	private Log log = LogFactory.getLog(LoginServiceImpl.class);

	@Resource(name = "asClient")
	public AerospikeClient asClient;
	@Resource(name = "writePolicy")
	public WritePolicy writePolicy;
	@Resource(name = "readPolicy")
	public Policy readPolicy;
	@Resource(name = "random")
	public Random random;

	public boolean queryBySql(String name, String password) {

		return true;
	}

	public String addToCache(user_info user) {
		String writekey = JiaMiUtil.MessageDigest(user.getName());
		// 构造key和record
		Key key = new Key("freeRead", "login", writekey);
		Bin name = new Bin("name", user.getName());
		Bin role = new Bin("role", user.getRole() == null ? "default"
				: user.getRole());
		Bin islogin = new Bin("islogin", String.valueOf(user.isLogin()));
		if (asClient == null) {
			System.out.println("failed to connect server.please to check it");
		}
		try {
			asClient.put(writePolicy, key, name, role,islogin);
		} catch (Exception e) {
			return "asError";
		}
		log.info("the write key is..."+writekey);
		return writekey;
	}

	
	public user_info getByCache(String key) {

		Key readKey = new Key("freeRead", "login", key);
		Record red = asClient.get(readPolicy, readKey);
		if (red != null) {
			Record record = asClient.operate(writePolicy, readKey,
					Operation.touch());
		}
		log.info("the read key is..."+key);
		return convert(red);
	}

	// 用于转换缓存记录为user_info
	private user_info convert(Record red) {
		// ReflectionUtils bb;
		user_info user = new user_info();
		user.setName(red.getString("name"));
		user.setRole(red.getString("role"));
		user.setLogin(Boolean.parseBoolean(red.getString("islogin")));
		return user;
	}

	public String randomKey() {
		long value = random.nextLong();
		long time = System.currentTimeMillis();
		String reString = Long.valueOf(value).toString()
				+ Long.valueOf(time).toString();
		String imgKey = JiaMiUtil.MessageDigest(reString);
		return imgKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.doit.login.service.LoginService#validateCaptchaWihtCookie(java
	 * .lang.String, org.springframework.validation.BindingResult)
	 */
	public void validateCaptchaWihtCookie(String cookie, BindingResult result,
			String code) {
		String res = getByCacheValue(cookie);
		if (res == null || res.equals("")) {
			result.addError(new ObjectError("服务器过期", "请重新输入验证码"));
		}
		if (!res.equalsIgnoreCase(code)) {
			result.addError(new ObjectError("造假", "孤灭了你"));
		}
	}
	public void addToCache(String key, String value) {

		Key writekey = new Key("freeRead", "session", key);
		Bin writevalue = new Bin("value", value);

		if (asClient == null) {
			System.out.println("failed to connect server.please to check it");
		}
		try {
			asClient.put(writePolicy, writekey, writevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getByCacheValue(String key) {
		try {
			Key readKey = new Key("freeRead", "session", key);
			Record red = asClient.get(readPolicy, readKey);
			String result = red.getString("value");
			if (red != null) {
				boolean record = asClient.delete(writePolicy, readKey);
			}
			return result;
		} catch (AerospikeException e) {
			log.error(e);
			return "eeeeee";
		}

	}

}
