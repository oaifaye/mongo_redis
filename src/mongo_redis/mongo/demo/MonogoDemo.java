package mongo_redis.mongo.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientOptions.Builder;

public class MonogoDemo {

//	private static String ip="10.0.100.138";
	private static String ip="10.0.251.101";
	private static String port="27017";
	
	// 连接池
	private static Mongo pool = null;
	
	public static void main(String[] args) throws Exception {
//		insert();
//		select();
		insert10000();
//		select10000();
//		delete();
	}
	
	public static void insert() throws Exception{
		//取得db和collection连接
		DBCollection table = getCollection("db_1","table_1");
		DBObject data = new BasicDBObject();
		data.put("name", "小明");
		data.put("age", 10);
		table.save(data);
	}
	
	public static void select() throws Exception{
		//取得db和collection连接
		DBCollection table = getCollection("db_1","table_1");
		DBObject match = new BasicDBObject();
		int skipNum = 0;
		int pageSize = 10;
		Cursor cr = table.find(match).skip(skipNum).limit(pageSize);
		int i = 0;
		while (cr.hasNext()) {
			i++;
			BasicDBObject data = (BasicDBObject) cr.next();
//			System.out.println("第" + i + "条");
//			System.out.println("name: " + data.getString("name"));
//			System.out.println("age : " + data.getInt("age"));
		}
	}
	
	private static DBCollection getCollection(String dbName,String collectionName) throws Exception {
		DB db = getDbPool().getDB(dbName);
		DBCollection accesstable = db.getCollection(collectionName);
		return accesstable;
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
	
	public static void delete() throws Exception{
//		DBCollection table = getCollection("db_1","table_1");
		DB db = getDbPool().getDB("db_1");
		db.dropDatabase();
	}
	
	public static void insert10000() throws Exception{
		long l = System.currentTimeMillis();
		DBCollection table = getCollection("db_1","table_1");
		for (int i = 0; i < 100000; i++) {
			DBObject data = new BasicDBObject();
			data.put("key","第"+String.valueOf(i)+"条");
			data.put("value", "第"+String.valueOf(i)+"条的内容");
			table.save(data);
//			System.out.println(i);
		}
		System.out.println( System.currentTimeMillis() - l);
	}
	
	public static void select10000() throws Exception{
		long l = System.currentTimeMillis();
		DBCollection table = getCollection("db_1","table_1");
//		for (int i = 0; i < 10000; i++) {
			DBCursor cur = table.find();
			while(cur.hasNext()){
				DBObject re = cur.next();
//				System.out.println(re.get("key"));
//				System.out.println(re.get("value"));
			}
//		}
		System.out.println( System.currentTimeMillis() - l);
	}
}
