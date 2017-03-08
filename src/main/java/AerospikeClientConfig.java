

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;

@Configuration
public class AerospikeClientConfig {
	@Value("#{nosql.aerospike.host}")
     private String host;
	@Value("#{nosql.aerospike.port}")
     private int port;
	@Value("#{nosql.aerospike.policy.expiration:600}")
	private int expiration;
	@Value("#{nosql.aerospike.policy.timeout:50}")
	private int timeout;
     //@ConfigurationProperties和@CrossOrigin整理
	@Bean(name="asClient")
	public AerospikeClient asClient(){
		AerospikeClient as=new AerospikeClient(host, port);
		return as;
	}
	@Bean(name="writePolicy")
	public WritePolicy writePolicy(){
		WritePolicy policy = new WritePolicy();
		policy.timeout = timeout;// 写入超时 单位毫秒
		policy.expiration=expiration;//过期时间600s
		return policy;
	}
	@Bean(name="readPolicy")
	public Policy readPolicy(){
		Policy policy = new Policy();
		policy.timeout = timeout;// 写入超时 单位毫秒
		return policy;
	}
}
