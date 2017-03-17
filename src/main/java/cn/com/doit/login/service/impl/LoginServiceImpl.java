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
import cn.com.doit.pojo.login.user_info;
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
   /**
    * @param user:将要存入as缓存的user_info对象
    * @return Sring: 返回存入对象的key，用以构造as的key，进行查询或操作
    */
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

	/**
	 * @param key:用以构造as查询的key
	 * @return user_info:返回该key对应as中的user_info对象,若没有则返回null
	 */
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
		user_info user=null;
		if(red!=null&&red.getString("name")!=null){
			user  = new user_info();
		user.setName(red.getString("name"));
		user.setRole(red.getString("role"));
		user.setLogin(Boolean.parseBoolean(red.getString("islogin")));
	   }
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

	/**
	 * @param cookie:传入的cookie值
	 * @param result:结果对象
	 * @param code：用户输入的验证码
	 * 根据cookie从as中获取对应存入的验证吗与客户输入的验证码进行匹配
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
	/**
	 * @param key:存入as中的key
	 * @param value:存入as中的值
	 * 将生成的验证码字符串根据指定的Key存入as中
	 */
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
//			if (red != null) {
//				boolean record = asClient.delete(writePolicy, readKey);
//			}
			return result;
		} catch (AerospikeException e) {
			log.error(e);
			return "eeeeee";
		}

	}

}
