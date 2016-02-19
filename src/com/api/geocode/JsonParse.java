package com.api.geocode;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class JsonParse {

	public static HashMap<String, String> parse(String str,
			ArrayList<String> list) {

		System.out.println(str);
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		HashMap<String, String> toReturn = new HashMap<String, String>();
		if (obj.keySet().size() > 1) {
			return null;
		} else {
			for (Object key : obj.keySet()) {
				System.out.println("key->" + key);
				for (String item : list) {
					HashMap<String, String> temp = (HashMap<String, String>) obj
							.get(key);
					if (temp.containsKey(item)) {
						toReturn.put(item, temp.get(item));
					}
				}
			}
			return toReturn;
		}
	}

	public static HashMap<String, Object> parse(String str) {

		System.out.println(str);
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		HashMap<String, Object> toReturn = new HashMap<String, Object>();
		if (obj.keySet().size() > 1 && !obj.containsKey("status")) {
			return null;
		} else {
			String key = "results";
			System.out.println("key->" + key);
			JSONArray temp = (JSONArray) obj.get(key);
			if (temp.size() > 0) {
				for (Object temp1 : temp) {
					JSONObject temp2 = (JSONObject) temp1;
					for (Object item : temp2.keySet()) {
						if (temp2.get(item) instanceof java.lang.String) {
							toReturn.put((String) item, temp2.get(item));
						} else {
							JSONObject value = (JSONObject) temp2.get(item);
							for (Object key2 : value.keySet()) {
								// System.out.println(key2.toString());
								toReturn.put((String) key2, value.get(key2));
							}
						}
					}
				}
			}
			return toReturn;
		}
	}

	public static HashMap<String, Object> parse(String str, String type) {

		System.out.println(str);
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		
		if (obj==null || !obj.containsKey(type)) {
			return null;
		} else {
			HashMap<String, Object> toReturn = new HashMap<String, Object>();
			toReturn = (HashMap<String, Object>) obj.get(type);
			return toReturn;
		}
	}

	public static HashMap<String, Object> parseIA(String str) {
		HashMap<String, Object> toReturn = new HashMap<String, Object>();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		if (obj.containsKey("archived_snapshots")) {
			JSONObject objas = (JSONObject) obj.get("archived_snapshots");
			if (objas.containsKey("closest")) {
				JSONObject objcl = (JSONObject) objas.get("closest");
				for (Object key : objcl.keySet()) {
					toReturn.put((String) key, objcl.get(key));
				}
				toReturn.put("data", str.trim().replaceAll("\n", ""));
			}
		}
		return toReturn;
	}

	public static HashMap<String, Object> parseHR(String str) {
		System.out.println(str);
		HashMap<String, Object> toReturn = new HashMap<String, Object>();
		JSONObject obj = (JSONObject) JSONValue.parse(str);
		if (obj.containsKey("archived_snapshots")) {
			JSONObject objas = (JSONObject) obj.get("archived_snapshots");
			if (objas.containsKey("closest")) {
				JSONObject objcl = (JSONObject) objas.get("closest");
				for (Object key : objcl.keySet()) {
					toReturn.put((String) key, objcl.get(key));
				}
				toReturn.put("data", str.trim().replaceAll("\n", ""));
			}
		}
		return toReturn;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
