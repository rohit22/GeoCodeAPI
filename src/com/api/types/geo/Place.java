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

public class Place implements IType{
	static int totalPlace;
	static int correctPlace;
	static int intPlace;
	static ConfigHolderGeo chg;

	public Place(ConfigHolderGeo ch){
		Place.chg = ch;
		totalPlace = 0;
		correctPlace = 0;
		intPlace = 0;
	}
	
	public int getCorrect() {
		return correctPlace;
	}

	public int getCommon() {
		return intPlace;
	}

	public int getTotal() {
		return totalPlace;
	}


	// name=empire state building&borough=manhattan
	public String getPlace(String name, String borough, InfoHolderGeo ih) {
		String output = null;
		boolean found = false;
		StringBuffer tempUrl = new StringBuffer();
//		String xCoordinate = null;
//		String yCoordinate = null;

		tempUrl.append(chg.getUrl() + "place." + ih.getoFileType() + "?");

		tempUrl.append("name=" + name + "&");
		tempUrl.append("borough=" + borough + "&");
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
		// For statistics
		/*if (output != null) {
			HashMap<String, Object> returnValues = JsonParse.parse(output,
					"place");
			if (returnValues.containsKey("xCoordinate")
					&& returnValues.containsKey("yCoordinate")) {
				System.out.println(returnValues.get("xCoordinate") + "<-->"
						+ returnValues.get("yCoordinate"));
				xCoordinate = (String) returnValues.get("xCoordinate");
				yCoordinate = (String) returnValues.get("yCoordinate");
				found = true;
			}
		}
		if (found) {
			totalPlace++;
			if (ih.getX().trim().length() > 0 && ih.getY().trim().length() > 0) {
				intPlace++;
				if (xCoordinate.equals(ih.getX().trim())
						&& yCoordinate.equals(ih.getY().trim())) {
					correctPlace++;
					System.out.println("CORRECT!!");
				}
			}
			return output;
		}*/
		return null;
	}

	public String tryPlace(InfoHolderGeo ih) {
		String output = null;
		StringBuffer tempUrl = new StringBuffer();
		boolean found = false;
	//	ArrayList<String> listVariables = new ArrayList<String>();
	//	listVariables.add("xCoordinate");
	//	listVariables.add("yCoordinate");
		tempUrl.append(chg.getUrl() + "place." + ih.getoFileType() + "?");
		HashMap<String, String> setValues = ih.getSetValues();
		for (String key : setValues.keySet()) {
			String value = setValues.get(key);
			if (key != "borough" && key != "zip") {
				key = "name";
			}
			if (value.trim().length() > 0) {
				value = value.replace(" ", "%20");
				tempUrl.append("&" + key + "=" + value);
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
	/*	
		if (output != null) {
			HashMap<String, String> returnValues = JsonParse.parse(output,
					listVariables);
			for (String key : returnValues.keySet()) {
				System.out.println(key + "<-->" + returnValues.get(key));
			}
		}*/
		if (output != null){
			HashMap<String, Object> returnValues = JsonParseRecursive.getMap(output);
			for (String key : ih.getKeys()){
				if (returnValues.containsKey(key)){
					found = true;
					ih.putInMap(key, String.valueOf(returnValues.get(key)));
				} else{
					ih.putInMap(key, null);
				}
			}
			if (found){
				return output;
			} else{
				return null;
			}
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
		if (street.length() > 0 && borough.length() > 0) {
			output = getPlace(street, borough, ih);
		}
		if (output == null && inStreet.length() > 0 && borough.length() > 0) {
			output = getPlace(inStreet, borough, ih);
		}
		if (output == null && crStreet.length() > 0 && borough.length() > 0) {
			output = getPlace(crStreet, borough, ih);
		}
		return output;
	}

}
