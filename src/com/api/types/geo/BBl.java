package com.api.types.geo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.api.config.ConfigHolderGeo;
import com.api.geocode.JsonParse;
import com.api.infoholder.InfoHolderGeo;
import com.api.utils.ServiceCall;

public class BBl implements IType{

	static int totalBBL;
	static ConfigHolderGeo chg;
	
	
	public BBl(ConfigHolderGeo ch){
		chg = ch;
	}
	
	public String get(InfoHolderGeo ih) {
		// TODO Auto-generated method stub
		
		String block = ih.getBlock().trim().replace(" ", "%20");
		String lot = ih.getLot().replace(" ", "%20").trim();
		String borough = ih.getBorough().trim().replace(" ", "%20");
		String output = null;
		
		if (block.length() > 0 && lot.length() > 0
				&& borough.length() > 0) {
			output = getBBL(block,lot,borough, ih);
		}
		return output;
	}
	
	private String getBBL(String block, String lot, String borough, InfoHolderGeo ih){
		String output = null;
		if (!(!block.equals(null) && !lot.equals(null) && !borough.equals(null))) {
			return null;
		}
		String xCoordinate = null;
		String yCoordinate = null;
		boolean found = false;
		StringBuffer tempUrl = new StringBuffer();
		tempUrl.append(chg.getUrl() + "address." + ih.getoFileType() + "?");
		tempUrl.append("borough=" + borough + "&");
		tempUrl.append("block=" + block + "&");
		tempUrl.append("lot=" + lot + "&");
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
			HashMap<String, Object> returnValues = JsonParse.parse(output,
					"address");
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
			totalBBL++;
		}
		return output;
	}
	
	
	public int getTotal() {
		// TODO Auto-generated method stub
		return totalBBL;
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
