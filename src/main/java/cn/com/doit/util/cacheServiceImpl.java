/**
 * 
 */
package cn.com.doit.util;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import cn.com.doit.login.service.impl.LoginServiceImpl;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Record;

/**
 * @author Administrator
 *
 */
@Configuration(value="cache")
public class cacheServiceImpl implements cacheService {
	private Log log = LogFactory.getLog(cacheServiceImpl.class);
	@Resource(name = "asClient")
	public AerospikeClient asClient;
	
    public Record read(asReadPolicy as){
    	try {
    		if(as.getKey()!=null){
    			if(as.getArrayBins()!=null){
    				return  asClient.get(as.getReadPolicy(), as.getKey(), as.getArrayBins());
    			}else{
    				return  asClient.get(as.getReadPolicy(), as.getKey());
    			}
        		
        	}
		} catch (AerospikeException e) {
			log.error(e);
			return null;
		}
		return null;
    	
    }
    public Record[]  batchRead(String setname,String[] key){
		return null;
    	
    }
    public boolean write(asWritePolicy as){
    	try {
    		if(as.getKey()!=null&&as.getArrayBins()!=null){
        		asClient.put(as.getWritePolicy(), as.getKey(), as.getArrayBins());
        		return true;
        	}
		} catch (AerospikeException e) {
			log.error(e);
			return false;
		}
    	
		return false;
    	
    }
    public boolean delete(String setname,String key){
		return false;
    	
    }

}
