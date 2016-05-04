package com.api.general;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.api.infoholder.InfoHolderGeneral;
import com.api.utils.JsonParseRecursive;
import com.api.utils.ServiceCall;
import com.api.utils.UrlCall;

public class UrlType {

	String sourceUrl;
	Map<String, String> queryParams;

	public UrlType(String url, Map<String, String> params) {
		sourceUrl = url;
		queryParams = params;
	}

	public String getResult(InfoHolderGeneral ig) {
		String output = null;

		try {
			if (queryParams == null) {
				return null;
			} else {
				Map<String, String> newParams = ig.getInputValues();
				for (String key : queryParams.keySet()) {
					if (newParams.containsKey(key)) {
						queryParams.put(key, newParams.get(key));
					}
				}
			}
			URL urlFinal = UrlCall.getEncodedURL(sourceUrl, queryParams);
			System.out.println(urlFinal.toString());
			output = ServiceCall.get(urlFinal);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (output.replaceAll("[^A-Za-z0-9 ]", "").trim().length() > 0) {
			System.out.println(output);

			HashMap<String, Object> returnValues = JsonParseRecursive.setKeys(
					output, ig.getKeys());

			for (String key : ig.getKeys()) {
				if (returnValues.containsKey(key)) {
					ig.putInMapOutput(key,
							String.valueOf(returnValues.get(key)));
				} else {
					ig.putInMapOutput(key, null);
				}
			}
			/*
			 * for (String key : returnValues.keySet()) { System.out.println(key
			 * + "<-->" + returnValues.get(key)); }
			 */
		}
		return output;
	}

}
