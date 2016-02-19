package com.api.types.geo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.api.config.ConfigHolderGeo;
import com.api.geocode.JsonParse;
import com.api.infoholder.InfoHolderGeo;
import com.api.utils.JsonParseRecursive;
import com.api.utils.ServiceCall;

public class Address implements IType {

	static int correctAddress;
	static int intAddress;
	static int totalAddress;
	static ConfigHolderGeo chg;

	public Address(ConfigHolderGeo chg) {
		Address.chg = chg;
		correctAddress = 0;
		intAddress = 0;
		totalAddress = 0;
	}

	public int getCorrect() {
		return correctAddress;
	}

	public int getCommon() {
		return intAddress;
	}

	public int getTotal() {
		return totalAddress;
	}

	private String getAddress(String adnum, String street, String borough,
			String zip, InfoHolderGeo ih) {

		if (!(!adnum.equals(null) && !street.equals(null) && (!borough
				.equals(null) || !zip.equals(null)))) {
			return null;
		}
		String output = null;
		boolean found = false;
		StringBuffer tempUrl = new StringBuffer();
		tempUrl.append(chg.getUrl() + "address." + ih.getoFileType() + "?");
		tempUrl.append("houseNumber=" + adnum + "&");
		tempUrl.append("street=" + street + "&");
		if (borough != null) {
			tempUrl.append("borough=" + borough + "&");
		}
		if (zip != null) {
			tempUrl.append("zip=" + zip + "&");
		}
		tempUrl.append("app_id=" + chg.getAppID());
		tempUrl.append("&app_key=" + chg.getAppKey());

		try {
			URL url = new URL(tempUrl.toString());
			output = ServiceCall.get(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (output != null) {
			HashMap<String, Object> returnValues = JsonParseRecursive
					.getMap(output);
			for (String key : ih.getKeys()) {
				if (returnValues.containsKey(key)) {
					found = true;
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
		// For statistics

		/*
		 * String xCoordinate = null; String yCoordinate = null;
		 * 
		 * if (output != null) { HashMap<String, Object> returnValues =
		 * JsonParse.parse(output, "address"); if
		 * (returnValues.containsKey("xCoordinate") &&
		 * returnValues.containsKey("yCoordinate")) {
		 * System.out.println(returnValues.get("xCoordinate") + "<-->" +
		 * returnValues.get("yCoordinate")); xCoordinate = (String)
		 * returnValues.get("xCoordinate"); yCoordinate = (String)
		 * returnValues.get("yCoordinate"); found = true; } }
		 * 
		 * if (found) { totalAddress++; if (ih.getX().trim().length() > 0 &&
		 * ih.getY().trim().length() > 0) { intAddress++; if
		 * (xCoordinate.equals(ih.getX().trim()) &&
		 * yCoordinate.equals(ih.getY().trim())) {
		 * System.out.println("CORRECT!!"); correctAddress++; } else{
		 * System.out.println("InCorrect!!"); } } return output; }
		 */
		return output;
	}

	public String tryAddress(InfoHolderGeo ih) {
		String output = null;
		ArrayList<String> listVariables = new ArrayList<String>();
		listVariables.add("xCoordinate");
		listVariables.add("yCoordinate");
		StringBuffer tempUrl = new StringBuffer();
		tempUrl.append(chg.getUrl() + "address." + ih.getoFileType() + "?");

		HashMap<String, String> setValues = ih.getSetValues();

		if (!(setValues.containsKey("houseNumber")
				&& setValues.containsKey("street") && (setValues
				.containsKey("borough") || setValues.containsKey("zip")))) {
			return null;
		}
		StringBuffer toReturn = new StringBuffer();
		for (String key : setValues.keySet()) {
			String value = setValues.get(key);
			if (value.trim().length() > 0) {
				value = value.replace(" ", "%20");
				toReturn.append("&" + key + "=" + value);
			}
		}
		tempUrl.append("&app_id=" + chg.getAppID());
		tempUrl.append("&app_key=" + chg.getAppKey());

		try {
			URL url = new URL(tempUrl.toString());
			output = ServiceCall.get(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (output != null) {
			HashMap<String, String> returnValues = JsonParse.parse(output,
					listVariables);
		/*	for (String key : returnValues.keySet()) {
				System.out.println(key + "<-->" + returnValues.get(key));
			}*/
		}
		return output;
	}

	public String get(InfoHolderGeo ih) {
		// TODO Auto-generated method stub
		String houseNumber = ih.getHouseNumber().trim().replace(" ", "%20");
		String street = ih.getStreet().replace(" ", "%20").trim();
		String borough = ih.getBorough().trim().replace(" ", "%20");
		String output = null;

		if (houseNumber.length() > 0 && street.length() > 0
				&& borough.length() > 0) {
			output = getAddress(houseNumber, street, borough, null, ih);
		}
		return output;
	}
}
