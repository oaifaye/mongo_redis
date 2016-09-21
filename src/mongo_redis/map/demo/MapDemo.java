package mongo_redis.map.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {

	private static Map<String ,String> map = new HashMap<String ,String>();
	
	public static void main(String[] args) {
		insert10000();
		select10000();
	}
	
	public static void insert10000(){
		long l = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			map.put("第"+i+"条", "第"+i+"条的内容");
		}
		System.out.println(System.currentTimeMillis() - l);
	}
	
	public static void select10000(){
		long l = System.currentTimeMillis();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			map.get(key);
		}
		System.out.println(System.currentTimeMillis() - l);
	}
}
