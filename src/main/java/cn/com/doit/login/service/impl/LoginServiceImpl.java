package cn.com.doit.login.service.impl;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aerospike.client.AerospikeClient;
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

@Component(value = "loginService")
public class LoginServiceImpl implements LoginService {
	@Resource(name = "asClient")
	public AerospikeClient asClient;
	@Resource(name = "writePolicy")
	public WritePolicy writePolicy;
	@Resource(name = "readPolicy")
	public Policy readPolicy;
	public boolean queryBySql(String name, String password) {

		return true;
	}

	public boolean addToCache(user_info user) {
		String writekey = JiaMiUtil.MessageDigest(user.getName());
		// 设置写入策略
		WritePolicy policy = new WritePolicy();
		policy.timeout = 50;// 写入超时 单位毫秒
		policy.expiration=600;//过期时间600s
		// policy.sendKey=true;

		// 构造key和record
		Key key = new Key("freeRead", "login", writekey);
		Bin name = new Bin("name", user.getName());
		Bin role = new Bin("role", user.getRole() == null ? "default"
				: user.getRole());
		if (asClient == null) {
			System.out.println("failed to connect server.please to check it");
		}
		try {
			asClient.put(policy, key, name, role);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public user_info getByCache(String key) {
	
		Key readKey = new Key("freeRead", "login", key);
		Record red = asClient.get(readPolicy, readKey);
		if(red!=null){
			Record record = asClient.operate(writePolicy, readKey, Operation.touch());
		}
		System.out.println(red.toString());
		return convert(red);
	}

	// @Autowired
	// private student_infoMapper student_infoMapper;
	//用于转换缓存记录为user_info
  private user_info convert(Record red){
	 // ReflectionUtils bb;
	  user_info user=new user_info();
	  user.setName(red.getString("name"));
	  user.setRole(red.getString("role"));
	return user;
  }
  public void addTTL(){
	  
  }
}
