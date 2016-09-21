package mongo_redis.redis.demo;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDemo {

	//private static String ip = "10.0.100.250";
//	private static int port = 6388;
	private static String ip = "127.0.0.1";
	private static int port = 6379;
	private static JedisPool jedisPool = null;
	
	public static void main(String[] args) throws Exception {
//		insert();
//		select();
		insert10000();
		select10000();
	}
	
	private static void select() throws Exception{
		Jedis jedis = getRedis();
		Set<String> top50 = jedis.zrevrange("table_2",0, 50);
		for (String data : top50) {
			System.out.println(data);
			System.out.println(jedis.zscore("table_2",data));
		}
	}
	
	private static void insert() throws Exception{
		Jedis jedis = getRedis();
		//第一种插入方式
//		jedis.incr("key_1");
//		//第二种插入方式
//		jedis.zincrby("table_2", 1,"key_2");
		for (int i = 0; i < 10000; i++) {
			jedis.set("key_"+i, "value_"+i);
		}
		
	}
	
	private static Jedis getRedis() throws Exception {
		if(jedisPool == null){
			jedisPool = new JedisPool(ip, port);
		}
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}
	
	public static void insert10000() throws Exception{
		Jedis jedis = getRedis();
		long l = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			//jedis.zincrby("table_2", 1,"第"+i+"条的内容");
			jedis.set("key_"+i, "value_"+i);
		}
		System.out.println("插入用："+(System.currentTimeMillis() - l));
	}
	
	public static void select10000() throws Exception{
		Jedis jedis = getRedis();
		long l = System.currentTimeMillis();
		//Set<String> top = jedis.zrevrange("table_2",0, 10000);
		for (int i = 0; i < 100000; i++) {
			jedis.get("key_"+i);
		}
		
	//	for (String data : top) {
//			System.out.println(data);
//			System.out.println(jedis.zscore("table_2",data));
	//	}
		System.out.println("查询用："+(System.currentTimeMillis() - l));
	}
}
