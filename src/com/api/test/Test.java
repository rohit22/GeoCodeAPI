package com.api.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;

public class Test {
	public static void main(String[] args) throws ClientProtocolException,
			IOException, URISyntaxException {
		HttpClient client = new DefaultHttpClient();

		URL url = new URL(
				"https://api.cityofnewyork.us/geoclient/v1/address.json?houseNumber=314&street=west%20100%20st&borough=manhattan&app_id=7a4e791c&app_key=2015DigitalIntern");
		// URL url = new
		// URL("http://api\\.cityofnewyork\\.us/geoclient/v1/address\\.json?houseNumber=314&street=west\\ 100\\ st&borough=manhattan&app_id=7a4e791c&app_key=2015DigitalIntern");
		URI uri = url.toURI();

		HttpGet get = new HttpGet(uri);
/*		JSONObject json = new JSONObject();
		json.put("app_id", "7a4e791c");
		json.put("app_key", "2015DigitalIntern");
		StringEntity se = new StringEntity(json.toString());
		post.setEntity(se);*/
		HttpResponse response = client.execute(get);
		// HttpURLConnection connection =
		// (HttpURLConnection)url.openConnection();
		// connection.setRequestMethod("GET");
		// connection.connect();

//		int code = connection.getResponseCode();
//		System.out.println(code);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}
	}
}
