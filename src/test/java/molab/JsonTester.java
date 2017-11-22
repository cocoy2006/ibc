package molab;

import com.alibaba.fastjson.JSONObject;

public class JsonTester {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		obj.put("ticket", 1);
		obj.put("key", "value");
		
		System.out.println(obj.toJSONString());
		System.out.println(obj.toString());
		
		System.exit(0);
	}

}
