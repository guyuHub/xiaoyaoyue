package cn.com.doit.z.test.nosql;

import java.nio.channels.ReadPendingException;

import org.junit.Test;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.BatchPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;

public class AerospikeClientTest {
	public String host = "192.168.49.111";
	public int port = 3000;

	// As连接测试
	public AerospikeClient initConect() {
		AerospikeClient as = null;
		as = new AerospikeClient(host, port);
		return as;
	}

	// 写入记录
	/*
	 * Note that if they do not already exist, set and bins are automatically
	 * created. You don't have to define a schema for the database.
	 */
	// 写入单条记录
	public void writeSingleValue(AerospikeClient as) {
		// 设置写入策略
		WritePolicy policy = new WritePolicy();
		policy.timeout = 50;// 写入超时 单位毫秒
		// policy.sendKey=true;

		// 构造key和record
		Key key = new Key("doit", "login", "张三");
		Bin bin = new Bin("age", 12);
		Bin bin11 = new Bin("key", "张三");
		if (as == null) {
			System.out.println("failed to connect server.please to check it");
		}
		as.put(policy, key, bin, bin11);
		// 添加bins
		Bin bin12 = new Bin("way", "巢湖");
		as.put(policy, key, bin12);
		/**
		 * put() 操作的基本行为: 1. 如果集群中 不存在该记录那么就创建它 2. 添加这个bins如果记录不存在这个bins 3.
		 * 如果记录中有这个bins则用指定的值更新它。如果记录中还含有其它bins，其它bins的值保持不变 使用
		 * WritePolicy对象去改变默认的写入行为. 一些策略如下所示 1. Only write if the record does
		 * not already exist: Change the WritePolicy to CREATE_ONLY Completely
		 * 2. replace a record ONLY if it exists: Change the WritePolicy to
		 * REPLACE_ONLY 其它写入策略参看文档
		 */
		/*--------------------------------------------------------------------------------------------------*/
		// Writing Record with Time-to-live (TTL)写入记录并指定存活时间
		WritePolicy policyForTTL = new WritePolicy();
		policyForTTL.expiration = 5000;// 单位为妙
		// policyForTTL.sendKey=true;
		Key key2 = new Key("doit", "login", "李四");
		Bin bin2 = new Bin("age", 13);
		Bin bin22 = new Bin("key", "李四");
		Bin bin23 = new Bin("way", "巢湖");
		as.put(policyForTTL, key2, bin2, bin22, bin23);

		/**
		 * 0为采取服务器端默认TTL设置，-1为记录永不过期 Set writePolicy.expiration to 0 to apply
		 * the default TTL on the server side each time a record updates. Set
		 * writePolicy.expiration to -1 to specify that the record never
		 * expires.
		 */
		if (as != null) {
			as.close();
		}
		/**
		 * Read-Modify-Write Record Read-Modify-Write (or Check-and-Set)
		 * involves: 1. Reading the record. 2. Modifying the record at the
		 * application level. 3. Setting the write policy to EXPECT_GEN_EQUAL.
		 * Writing the 4. modified data with the generation-previous read.
		 */
	}

	// 读取记录
	/**
	 * Aerospike Java 客户端可以提供一下几种选择用以从数据库中读取记录 : 读取一条记录的所有bins. 读取一条记录的指定bins.
	 * 读取一条记录的元数据西信息. Each method returns a Record object that contains the
	 * metadata and bins of the record.
	 */

	public void readSingelRecord(AerospikeClient as) {
		// Read All Bins in a Record 读取记录中所有的bins
		Policy policy = new Policy();
		policy.timeout = 50;
		Key readKey = new Key("doit", "login", "张三");
		Record red = as.get(policy, readKey);
		System.out.println(red.toString());
		// Read Specific Bins of a Record 读取记录中指定的bins
		Key readKey2 = new Key("doit", "login", "李四");
		Record red2 = as.get(policy, readKey2, "key");
		System.out.println(red2.toString());
	}

	// Delete a Record
	public void deletSingelRecord(AerospikeClient as) {
		WritePolicy policy = new WritePolicy();
		policy.timeout = 50;
		policy.durableDelete = false;
		Key key = new Key("doit", "login", "张三");
		as.delete(policy, key);
		// 目前durableDelete会报错，错误代码25
		// WritePolicy wpolicy=new WritePolicy();
		// wpolicy.timeout=50;
		// wpolicy.durableDelete=true;
		// Key wkey = new Key("doit", "login", "张三");
		// as.delete(wpolicy, wkey);
	}

	// Deleting a Bin
	// To delete a bin, set the bin value to NULL:
	public void deletSingelBin(AerospikeClient as) {
		WritePolicy policy = new WritePolicy();
		policy.timeout = 50;
		policy.durableDelete = false;
		Key wkey = new Key("doit", "login", "李四");
		Bin bins = Bin.asNull("key");
		as.put(policy, wkey, bins);
	}

	// Batch Reads
	/** 批量读取 */
	public void readMultipleRecord(AerospikeClient as) {
		BatchPolicy policy = new BatchPolicy();
		policy.timeout = 50;
		Key[] keys = new Key[2];
		String[] mykeys = new String[2];
		mykeys[0] = "张三";
		mykeys[1] = "李四";
		for (int i = 0; i < 2; i++) {
			keys[i] = new Key("doit", "login", mykeys[i]);
		}

		Record[] records = as.get(policy, keys);
		for (Record record : records) {
			if (record == null) {
				System.out.println("It's null,never do it");
			} else {
				System.out.println(record.toString());
			}
		}

		/**
		 * This call groups keys based on which Aerospike Server node can best
		 * handle the request, and uses a ThreadPool object to concurrently
		 * handle each request to each node. After all nodes return the record
		 * data, the records are returned to the caller. The array of records
		 * returned is in the same order the keys are passed in. If a record is
		 * not found in the database, the array entry is null.
		 */
	}

	// 一些其它的操作方法
	public void anOntherRecordOperation(AerospikeClient as) {
		/**
		 * Operation Description Conditions write Write a value to a bin. read
		 * Read the value of a bin. read_header Read only the metadata
		 * (generation and time-to-live) of the record. add Add an integer to
		 * the existing value of the bin. The existing value must be integer.
		 * append Append a string to the existing value of the bin. The existing
		 * value must be string. prepend Prepend a string to the existing value
		 * of the bin. The existing value must be string. touch Rewrite the same
		 * record.(目前还不知道具体作用) Generation and time-to-live are updated.
		 */
		WritePolicy write = new WritePolicy();
		write.timeout = 100;
		Key key = new Key("doit", "login", "张三");
		Key shkey = new Key("doit", "login", "李四");
		Bin bin = new Bin("age", 15);
		Bin qbin = new Bin("key", "前入式");
		Bin hbin = new Bin("key", "后入式");
		// add
		as.add(write, key, bin);
		// 前入
		as.prepend(write, shkey, qbin);
		// 后入
		as.append(write, shkey, hbin);

	}

	// 测试AS服务器连接
	// 写入记录(包括写入策略的设置与过期时间)
	// 读取记录，读取所有bins,读取指定bins
	// 删除记录，目前durableDelete会报错，误代码25
	// 删除指定bins
	// 批量读
	// 一些特殊操作
	@Test
	public void testOne() {
		AerospikeClient as = initConect();
		// writeSingleValue(as);
		// readSingelRecord(as);
		// deletSingelRecord(as);
		// deletSingelBin(as);
		// readMultipleRecord(as);
		anOntherRecordOperation(as);
	}
	
	// UDF
}
