package jedis;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import redis.clients.jedis.Jedis;


public class TestJedisString {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(1);
		// 一般字串資料
		jedis.set("myKey", "儲存測試");
		System.out.println("myKey is: " + jedis.get("myKey"));
		
		jedis.append("myKey", "安安你好");
		System.out.println("Append result: " + jedis.get("myKey"));
		System.out.println("myKey's length: " + jedis.strlen("myKey"));
		System.out.println("Get range: " + jedis.getrange("myKey", 7, 11));
		System.out.println("Get range(2): " + jedis.getrange("myKey", -18, -14));
		
		
	
		// 多筆key處理
		jedis.mset("key1", "value1", "key2", "value2", "key3", "value3");
		List<String> data = jedis.mget("key1", "key2", "key3");
		for (String str : data)
			System.out.println(str);
		
		
	
		
		jedis.close();
	}

}
