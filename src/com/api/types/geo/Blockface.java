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

public class Blockface implements IType{
	static ConfigHolderGeo chg;
	static int totalBlock;
	static int correctBlock;
	static int intBlock;
	
	public Blockface(ConfigHolderGeo ch){
		Blockface.chg = ch;
		totalBlock = 0;
		correctBlock = 0;
		intBlock = 0;
	}
	
	public int getTotal() {
		return totalBlock;
	}
	public int getCorrect() {
		return correctBlock;
	}

	public int getCommon() {
		return intBlock;
	}

	public String getBlockface(String onStreet, String crossStreetOne,
			String crossStreetTwo, String borough, InfoHolderGeo ih) {
		String output = null;
		boolean found = false;
		String xCoordinate = null;
		String yCoordinate = null;
		StringBuffer tempUrl = new StringBuffer();

		tempUrl.append(chg.getUrl() + "blockface." + ih.getoFileType() + "?");

		tempUrl.append("onStreet=" + onStreet + "&");
		tempUrl.append("crossStreetOne=" + crossStreetOne + "&");
		tempUrl.append("crossStreetTwo=" + crossStreetTwo + "&");
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
		if (output != null){
			HashMap<String, Object> returnValues = JsonParseRecursive.getMap(output);
			for (String key : ih.getKeys()){
				found = true;
				if (returnValues.containsKey(key)){
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
		
		// For statistics
		/*if (output != null) {
			HashMap<String, Object> returnValues = JsonParse.parse(output,
					"blockface");
			if (returnValues.containsKey("fromXCoordinate")
					&& returnValues.containsKey("toXCoordinate")
					&& returnValues.containsKey("fromYCoordinate")
					&& returnValues.containsKey("toYCoordinate")) {
				System.out.println(returnValues.get("fromXCoordinate") + "<-->"
						+ returnValues.get("toXCoordinate"));
				System.out.println(returnValues.get("fromYCoordinate") + "<-->"
						+ returnValues.get("fromYCoordinate"));
				xCoordinate = String.valueOf(
						(
								Integer.valueOf((String)returnValues.get("fromXCoordinate")) + 
								Integer.valueOf((String)returnValues.get("toXCoordinate"))
						)/2);
				yCoordinate = String.valueOf(
						(
								Integer.valueOf((String)returnValues.get("fromYCoordinate")) + 
								Integer.valueOf((String)returnValues.get("toYCoordinate"))
						)/2);
				found = true;
			}
		}
		if (found) {
			totalBlock++;
			if (ih.getX().trim().length() > 0 && ih.getY().trim().length() > 0) {
				intBlock++;
				if (xCoordinate.equals(ih.getX().trim())
						&& yCoordinate.equals(ih.getY().trim())) {
					correctBlock++;
					System.out.println("CORRECT!!");
				}
			}
			return output;
		}*/
		
		return null;
	}
	
	public String tryBlockface(InfoHolderGeo ih) {
		String output = null;
		StringBuffer tempUrl = new StringBuffer();
		ArrayList<String> listVariables = new ArrayList<String>();
		listVariables.add("fromXCoordinate");
		listVariables.add("toXCoordinate");
		listVariables.add("fromYCoordinate");
		listVariables.add("toYCoordinate");
		tempUrl.append(chg.getUrl() + "blockface." + ih.getoFileType() + "?");

		HashMap<String, String> setValues = ih.getSetValues();
		for (String key : setValues.keySet()) {
			String value = setValues.get(key);
			if (key.equals("street")) {
				key = "onStreet";
			}
			if (key.equals("crStreet")) {
				key = "crossStreetOne";
			}
			if (key.equals("inStreet")) {
				key = "crossStreetTwo";
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
		if (output != null) {
			HashMap<String, String> returnValues = JsonParse.parse(output,
					listVariables);
		/*	for (String key : returnValues.keySet()) {
				System.out.println(key + "<-->" + returnValues.get(key));
			}*/
		}
		if (output != null){
			HashMap<String, Object> returnValues = JsonParseRecursive.getMap(output);
			for (String key : ih.getKeys()){
				if (returnValues.containsKey(key)){
					ih.putInMap(key, String.valueOf(returnValues.get(key)));
				} else{
					ih.putInMap(key, null);
				}
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
		if (street.length() > 0 && inStreet.length() > 0
				&& crStreet.length() > 0 && borough.length() > 0) {
			output = getBlockface(street, inStreet, crStreet, borough, ih);
		}
		return output;
	}
}
