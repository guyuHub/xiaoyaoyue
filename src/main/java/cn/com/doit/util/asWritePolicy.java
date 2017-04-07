/**
 * 
 */
package cn.com.doit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.doit.pojo.book.Free_book_info;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;

/**
 * @author Administrator
 *
 */
public class asWritePolicy {
    private WritePolicy writePolicy;
    private static WritePolicy defaultPolicy;
    private Map<String, Object> bins;
    private String setname;
    private String namespace;
    private String key;
    
    public static asWritePolicy newInstansce(){	
		return new asWritePolicy(defaultPolicy);	
    }
    public asWritePolicy newInstansce(WritePolicy writePolicy){	
		return new asWritePolicy(writePolicy);	
    }

	public asWritePolicy(WritePolicy writePolicy) {
		this.writePolicy = writePolicy;
		bins=new HashMap<String, Object>();
	}

	public WritePolicy getWritePolicy() {
		return writePolicy;
	}

	public void setWritePolicy(WritePolicy writePolicy) {
		this.writePolicy = writePolicy;
	}

	public Map<String, Object> getBins() {
		return bins;
	}

	public void setBins(Map<String, Object> bins) {
		this.bins = bins;
	}

	public Bin[] getArrayBins() {
		
		if(bins!=null&&!bins.isEmpty()){
			ArrayList<Bin> linshi=new ArrayList<Bin>(bins.size());
			for (String  binName : bins.keySet()) {
				Bin sb=new Bin(binName,bins.get(binName));
				linshi.add(sb);
			}
			Bin[] var=new Bin[linshi.size()];
			return linshi.toArray(var);
		}
		return null;
	}
	public asWritePolicy setSetname(String setname) {
		this.setname = setname;
		return this;
	}
	public asWritePolicy setNamespace(String namespace) {
		this.namespace = namespace;
		return this;
	}
	public asWritePolicy setKey(String key) {
		this.key = key;
		return this;
	}
	public Key getKey() {
		if(namespace==null||setname==null||key==null)
			return null;
		return new Key(namespace, setname, key);
	}
	@Resource(name = "writePolicy")
	public  void setDefaultPolicy(WritePolicy defaultPolicy) {
		asWritePolicy.defaultPolicy = defaultPolicy;
	}
	/**
	 * @param string
	 * @param result
	 * @return
	 */
	public asWritePolicy put(String string, Object result) {
		this.getBins().put(string, result);
		return this;
	}
	
}
