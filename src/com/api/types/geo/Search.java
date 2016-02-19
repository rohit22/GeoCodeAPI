package com.api.types.geo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.api.config.ConfigHolderGeo;
import com.api.geocode.JsonParse;
import com.api.infoholder.InfoHolderGeo;
import com.api.utils.JsonParseRecursive;
import com.api.utils.ServiceCall;

public class Search implements IType {

	static ConfigHolderGeo chg;
	static int totalSearch;

	public Search(ConfigHolderGeo ch) {
		Search.chg = ch;
		totalSearch = 0;

	}

	public String get(InfoHolderGeo ih) {
		// TODO Auto-generated method stub
		String houseNumber = ih.getHouseNumber().trim().replace(" ", "%20");
		String street = ih.getStreet().replace(" ", "%20").trim();
		String borough = ih.getBorough().trim().replace(" ", "%20");
		String inStreet = ih.getInStreet().trim().replace(" ", "%20");
		String crStreet = ih.getCrStreet().trim().replace(" ", "%20");
		String xCoordinate = null;
		String yCoordinate = null;
		String output = null;
		boolean found = false;
		StringBuffer tempUrl = new StringBuffer();

		tempUrl.append(chg.getUrl() + "search." + ih.getoFileType() + "?");
		String input = houseNumber + " " + street + " " + inStreet + " "
				+ crStreet + " " + borough;
		input = input.trim().replace(" ", "%20");
		tempUrl.append("input=" + input + "&");

		tempUrl.append("app_id=" + chg.getAppID());
		tempUrl.append("&app_key=" + chg.getAppKey());

		try {
			URL url = new URL(tempUrl.toString());
			output = ServiceCall.get(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	if (output != null) {
			HashMap<String, Object> returnValues = JsonParse.parse(output);
			if (returnValues != null) {
				if (returnValues.containsKey("xCoordinate")
						&& returnValues.containsKey("yCoordinate")) {
					System.out.println(returnValues.get("xCoordinate") + "<-->"
							+ returnValues.get("yCoordinate"));
					xCoordinate = (String) returnValues.get("xCoordinate");
					yCoordinate = (String) returnValues.get("yCoordinate");
					found = true;
				}
			}
		}
		if (found) {
			totalSearch++;
			System.out.println(xCoordinate + "<-->" + yCoordinate);
		}*/
		if (output != null) {
			HashMap<String, Object> returnValues = JsonParseRecursive
					.getMap(output);
			for (String key : ih.getKeys()) {
				found = true;
				if (returnValues.containsKey(key)) {
					ih.putInMap(key, String.valueOf(returnValues.get(key)));
				} else {
					ih.putInMap(key, null);
				}
			}
			if (found) {
				return output;
			} else {
				return null;
			}
		}
		return output;

	}

	public int getTotal() {
		// TODO Auto-generated method stub
		return totalSearch;
	}

	public int getCommon() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCorrect() {
		// TODO Auto-generated method stub
		return 0;
	}

}
