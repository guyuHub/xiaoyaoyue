/**
 * 
 */
package cn.com.doit.util;

import com.aerospike.client.Record;

/**
 * @author Administrator
 *
 */
public interface cacheService {
    public Record read(asReadPolicy as);
    public Record[]  batchRead(String setname,String[] key);
    public boolean write(asWritePolicy as);
    public boolean delete(String setname,String key);
}
