package com.api.humanresource;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import com.api.config.ConfigHolderHR;
import com.api.infoholder.InfoHolderHrIa;
import com.api.utils.JsonParseRecursive;
import com.api.utils.ServiceCall;

public class UrlType {
	static ConfigHolderHR chi;

	public UrlType(ConfigHolderHR chi) {
		UrlType.chi = chi;
	}

	private String getURL(String url, String timeStamp, InfoHolderHrIa hr) {
		String output = null;
		StringBuffer tempUrl = new StringBuffer();
		try {
			tempUrl.append("http://spatha.cul.columbia.edu:8081/solr-4.7-hrwa/hrwa-asf/select?q=1&fq=original_url%3A%22"
					+ URLEncoder.encode(url, "UTF-8"));
			tempUrl.append("%22&group=true&group.field=original_url&group.limit=1&group.sort=date_of_capture_yyyymmdd%20desc&wt=json");
			URL urlFinal = new URL(tempUrl.toString());
			output = ServiceCall.get(urlFinal);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (output.replaceAll("[^A-Za-z0-9 ]", "").trim().length() > 0) {
			System.out.println(output);

			// System.out.println("<---RecursiveParse-->");
			// JsonParse.printKey(output, "archived_url");
			HashMap<String, Object> returnValues = JsonParseRecursive.setKeys(
					output, hr.getKeys());
			returnValues.put("available", false);
			if (returnValues.containsKey("matches")
					&& Integer.parseInt(returnValues.get("matches").toString()) > 0) {
				returnValues.put("available", true);
			}

			for (String key : hr.getKeys()) {
				if (returnValues.containsKey(key)) {
					hr.putInMap(key, String.valueOf(returnValues.get(key)));
				} else {
					hr.putInMap(key, null);
				}
			}
			/*
			 * for (String key : returnValues.keySet()) { System.out.println(key
			 * + "<-->" + returnValues.get(key)); }
			 */
		}
		return output;
	}

	public String get(InfoHolderHrIa hr) {
		String output = null;
		String url = hr.getUrl().trim().replace("[\\s\\t\\n\\r]", "%20");
		String timestamp = hr.getTimeStamp();
		if (url.trim().length() > 0 && timestamp.trim().length() > 0) {
			output = getURL(url, timestamp, hr);
		}
		return output;
	}
}
