package cn.com.doit.cfg;

import java.util.Random;

import javax.annotation.PreDestroy;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import cn.com.doit.login.controller.LonginController;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;

@Configuration
public class AerospikeClientConfig {

	@Value("${freeRead.random.seed}")
	private long seed;
	@Value("${nosql.aerospike.host}")
	private String host;
	@Value("${nosql.aerospike.port}")
	private int port;
	@Value("${nosql.aerospike.policy.expiration:120}")
	private int expiration;
	@Value("${nosql.aerospike.policy.timeout:50}")
	private int timeout;
	private AerospikeClient as;
	  private Log log=LogFactory.getLog(AerospikeClientConfig.class);

	// @ConfigurationProperties和@CrossOrigin整理
	@Bean(name = "asClient")
	public AerospikeClient asClient() {
		 as = new AerospikeClient(host, port);
		
		return as;
	}

	@Bean(name = "writePolicy")
	public WritePolicy writePolicy() {
		WritePolicy policy = new WritePolicy();
		policy.timeout = timeout;// 写入超时 单位毫秒
		policy.expiration = expiration;// 过期时间60s
		return policy;
	}

	@Bean(name = "readPolicy")
	public Policy readPolicy() {
		Policy policy = new Policy();
		policy.timeout = timeout;// 写入超时 单位毫秒
		return policy;
	}

	@Bean(name = "random")
	public Random random() {
		return new Random(seed);
	}
    @PreDestroy
	public void close() {
	   as.close();
	   log.info("Aerospike 销毁");
	}
	public void writeRecord() {
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getExpiration() {
		return expiration;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
}
