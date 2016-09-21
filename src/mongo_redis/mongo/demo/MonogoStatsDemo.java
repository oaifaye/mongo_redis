package mongo_redis.mongo.demo;

import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
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

public class MonogoStatsDemo {

//	private static String ip="10.0.100.138";
	private static String ip="10.0.251.108";
	private static String port="27017";
	
	// ���ӳ�
	private static Mongo pool = null;
	
	public static void main(String[] args) throws Exception {
		stats();
	}
	
	public static void stats() throws Exception{
		//ȡ��db��collection����
		DB db = getDbPool().getDB("admin");
		CommandResult stats = db.doEval("db.serverStatus()");
		Map retval = (Map)stats.get("retval");
		Map mem = (Map)retval.get("mem");
		System.out.println(mem.get("resident"));//�������ܹ�ʹ�õ������ڴ�,��λ��MB
		System.out.println(mem.get("bits"));//����λ��,32λ��64λ
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
	
}
