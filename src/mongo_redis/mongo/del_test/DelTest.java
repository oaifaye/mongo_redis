package mongo_redis.mongo.del_test;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientOptions.Builder;

public class DelTest {
	
	private static String ip="10.0.251.101";
	private static String port="27017";
	// 连接池
	private static Mongo pool = null;

	public static void main(String[] args) throws Exception {
		delete();
		System.out.println("完成");
	}
	
	public static void delete() throws Exception{
		DB db = getDbPool().getDB("db_1");
		db.dropDatabase();
		System.out.println("删完成");
		Thread.sleep(1000000);
	}
	
	private static Mongo getDbPool() throws Exception {
		if (pool == null) {
			Builder builder = new MongoClientOptions.Builder();
			builder.maxWaitTime(500000);
			builder.connectTimeout(150000);
			
			pool = new MongoClient(new ServerAddress(String.format("%s:%s", ip, port)), builder.build());
		}

		return pool;
	}
	
}
