/**
 * 
 */
package cn.com.doit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;

/**
 * @author Administrator
 *
 */
public class asReadPolicy {
    private Policy readPolicy;
 
	public static Policy defaultPolicy;
    private ArrayList<String> bins;
    private String setname;
    private String namespace;
    private String key;
    
    public static asReadPolicy newInstansce(){	
		return new asReadPolicy(defaultPolicy);	
    }
    public static  asReadPolicy newInstansce(Policy readPolicy){	
		return new asReadPolicy(readPolicy);	
    }

	public asReadPolicy(Policy writePolicy) {
		this.readPolicy = writePolicy;
		bins=new ArrayList<String>();
	}

	public String[] getArrayBins() {
		if(bins!=null&&!bins.isEmpty()){
			return (String[]) bins.toArray();
		}
		return null;
	}
	public asReadPolicy setSetname(String setname) {
		this.setname = setname;
		return this;
	}
	public asReadPolicy setNamespace(String namespace) {
		this.namespace = namespace;
		return this;
	}
	public asReadPolicy setKey(String key) {
		this.key = key;
		return this;
	}
	public Key getKey() {
		if(namespace==null||setname==null||key==null)
			return null;
		return new Key(namespace, setname, key);
	}
	public Policy getReadPolicy() {
		return readPolicy;
	}
	public asReadPolicy setReadPolicy(Policy readPolicy) {
		this.readPolicy = readPolicy;
		return this;
	}
	public ArrayList<String> getBins() {
		return bins;
	}
	public asReadPolicy setBins(ArrayList<String> bins) {
		this.bins = bins;
		return this;
	}
	  @Resource(name = "readPolicy")
	public  void setDefaultPolicy(Policy defaultPolicy) {
		asReadPolicy.defaultPolicy = defaultPolicy;
	}
	
}
