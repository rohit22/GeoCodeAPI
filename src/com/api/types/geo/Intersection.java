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

public class Intersection implements IType {
	static int totalIntersection;
	static int correctIntersection;
	static int intIntersection;
	static ConfigHolderGeo chg;

	public Intersection(ConfigHolderGeo chg) {
		Intersection.chg = chg;
		totalIntersection = 0;
		correctIntersection = 0;
		intIntersection = 0;
	}

	public int getCorrect() {
		return correctIntersection;
	}

	public int getCommon() {
		return intIntersection;
	}

	public int getTotal() {
		return totalIntersection;
	}

	public String getIntersection(String street1, String street2,
			String borough, InfoHolderGeo ih) {
		String output = null;
		boolean found = false;
//		String xCoordinate = null;
//		String yCoordinate = null;
		StringBuffer tempUrl = new StringBuffer();

		for (int i = 0; i < 3; i++) {
			tempUrl = new StringBuffer();
			tempUrl.append(chg.getUrl() + "intersection." + ih.getoFileType()
					+ "?");
			tempUrl.append("crossStreetOne=" + street1 + "&");
			tempUrl.append("crossStreetTwo=" + street2 + "&");
			tempUrl.append("borough=" + borough + "&");
			if (i == 1) {
				tempUrl.append("compassDirection=n&");
			}
			if (i == 2) {
				tempUrl.append("compassDirection=e&");
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
			// For statistics
			/*
			if (output != null) {
				HashMap<String, Object> returnValues = JsonParse.parse(output,
						"intersection");
				if (returnValues.containsKey("xCoordinate")
						&& returnValues.containsKey("yCoordinate")) {
					System.out.println(returnValues.get("xCoordinate") + "<-->"
							+ returnValues.get("yCoordinate"));
					xCoordinate = (String) returnValues.get("xCoordinate");
					yCoordinate = (String) returnValues.get("yCoordinate");
					found = true;
				}
				if (found) {
					break;
				}
			}*/
			if (output != null){
				HashMap<String, Object> returnValues = JsonParseRecursive.getMap(output);
				for (String key : ih.getKeys()){
					if (returnValues.containsKey(key)){
						ih.putInMap(key, String.valueOf(returnValues.get(key)));
						found = true;
					} else{
						ih.putInMap(key, null);
					}
				}
				if (found) {
					return output;
				}
			}
		}
		// For statisics
		/*if (found) {
			totalIntersection++;
			if (ih.getX().trim().length() > 0 && ih.getY().trim().length() > 0) {
				intIntersection++;
				if (xCoordinate.equals(ih.getX().trim())
						&& yCoordinate.equals(ih.getY().trim())) {
					correctIntersection++;
					System.out.println("CORRECT!!");
				}
			}
			return output;
		}*/
		return null;
	}

	public String tryIntersection(InfoHolderGeo ih) {
		String output = null;
		ArrayList<String> listVariables = new ArrayList<String>();
		listVariables.add("xCoordinate");
		listVariables.add("yCoordinate");
		StringBuffer tempUrl = new StringBuffer();
		tempUrl.append(chg.getUrl() + "intersection." + ih.getoFileType() + "?");

		HashMap<String, String> setValues = ih.getSetValues();

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
		String street = ih.getStreet().replace(" ", "%20").trim();
		String borough = ih.getBorough().trim().replace(" ", "%20");
		String inStreet = ih.getInStreet().trim().replace(" ", "%20");
		String crStreet = ih.getCrStreet().trim().replace(" ", "%20");
		String output = null;
		if (street.length() > 0 && inStreet.length() > 0
				&& borough.length() > 0) {
			output = getIntersection(street, inStreet, borough, ih);
		}
		if (output == null && street.length() > 0 && crStreet.length() > 0
				&& borough.length() > 0) {
			output = getIntersection(street, crStreet, borough, ih);
		}

		if (output == null && inStreet.length() > 0 && crStreet.length() > 0
				&& borough.length() > 0) {
			output = getIntersection(inStreet, crStreet, borough, ih);
		}
		return null;
	}
}
