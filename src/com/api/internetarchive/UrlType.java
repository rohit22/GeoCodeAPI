package com.api.internetarchive;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import com.api.config.ConfigHolderIA;
import com.api.infoholder.InfoHolderHrIa;
import com.api.utils.JsonParseRecursive;
import com.api.utils.ServiceCall;

public class UrlType {
	static ConfigHolderIA chi;

	public UrlType(ConfigHolderIA chi) {
		UrlType.chi = chi;
	}

	private String getURL(String url, String timeStamp, InfoHolderHrIa ia) {
		String output = null;
		StringBuffer tempUrl = new StringBuffer();
		try {
			tempUrl.append(chi.getUrl() + "?");
			tempUrl.append("url=" + URLEncoder.encode(url, "UTF-8"));
			tempUrl.append("&timestamp=" + URLEncoder.encode(timeStamp, "UTF-8"));
			URL urlFinal = new URL(tempUrl.toString());
			output = ServiceCall.get(urlFinal);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (output.replaceAll("[^A-Za-z0-9 ]", "").trim().length()>0) {
	//		System.out.println(output);
			HashMap<String, Object> returnValues = JsonParseRecursive.getMap(output);
			
	/*		System.out.println("<---RecursiveParse-->");
			for (String key : returnValues.keySet()){
				System.out.println(key + "<-->" + returnValues.get(key));
			}
			System.out.println("<---RecursiveParse-->");*/
			
			if (returnValues.keySet().size() > 2){
				ia.setApiData(output.trim().replaceAll("\n", ""));
				ia.setAvailable((Boolean)returnValues.get("available"));
			}
			for(String key : ia.getKeys()){
				if (returnValues.containsKey(key)){
					ia.putInMap(key, String.valueOf(returnValues.get(key)));
				} else{
					ia.putInMap(key, null);
				}
			}
	/*		for (String key : returnValues.keySet()) {
				System.out.println(key + "<-->" + returnValues.get(key));
			}*/
		}
		return output;
	}

	public String get(InfoHolderHrIa ia) {
		String output = null;
		String url = ia.getUrl().trim().replace("[\\s\\t\\n\\r]", "%20");
		String timestamp = ia.getTimeStamp();
		if (url.trim().length() > 0 && timestamp.trim().length() > 0) {
			output = getURL(url, timestamp, ia);
		}
		return output;
	}
}
