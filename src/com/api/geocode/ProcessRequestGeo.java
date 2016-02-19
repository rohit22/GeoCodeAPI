package com.api.geocode;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.api.types.geo.Address;
import com.api.types.geo.Blockface;
import com.api.types.geo.Intersection;
import com.api.types.geo.Place;
import com.api.types.geo.Search;
import com.api.config.ConfigHolderGeo;
import com.api.infoholder.InfoHolderGeo;

public class ProcessRequestGeo {

	static ConfigHolderGeo chg;
	static Address ad;
	static Intersection in;
	static Blockface bl;
	static Place pl;
	static Search sr;

	public ProcessRequestGeo(ConfigHolderGeo ch) {
		chg = ch;
		ad = new Address(ch);
		in = new Intersection(ch);
		bl = new Blockface(ch);
		pl = new Place(ch);
		sr = new Search(ch);

	}

	public boolean request(InfoHolderGeo ih) throws MalformedURLException {

		ArrayList<String> types = chg.getTypes();

		boolean found = false;
		for (String type : types) {
			System.out.println(type);
			String output = null;
			if (type.equals("address")) {
				output = ad.tryAddress(ih);
			} else if (type.equals("place")) {
				output = pl.tryPlace(ih);
			} else if (type.equals("intersection")) {
				output = in.tryIntersection(ih);
			} else if (type.equals("blockface")) {
				output = bl.tryBlockface(ih);
			}

			if (output != null) {
				HashMap<String, Object> returnValues = JsonParse.parse(output);
				for (String key : returnValues.keySet()) {
					System.out.println(key + "<-->" + returnValues.get(key));
					found = true;
				}
			}
			if (found) {
				break;
			}
		}
		return found;
	}

	public boolean set(InfoHolderGeo ih) {
		String output = null;

		output = ad.get(ih);
		if (output == null) {
			output = bl.get(ih);
		}
		if (output == null) {
			output = in.get(ih);
		}
		if (output == null) {
			output = pl.get(ih);
		}
		if (output == null) {
			output = sr.get(ih);
		}
		if (output == null) {
			System.out.println("Insufficient Data");
		}
		if (output.trim().length() > 0) {
			return true;
		}
		return false;
	}

	private static String getType(HashMap<String, String> setValues) {

		// houseNumber, street, borough/zip - address
		// borough, block, lot - bbl
		// bin - bin
		// onStreet, crossStreetOne, crossStreetTwo, borough,
		// boroughCrossStreetOne, boroughCrossStreetTwo, compassDirection -
		// blockface
		// crossStreetOne, crossStreetTwo, borough, boroughCrossStreetTwo,
		// compassDirection (Optional for most) - intersection
		// name, borough | zip - place
		if (setValues.containsKey("houseNumber")
				&& setValues.containsKey("street")
				&& (setValues.containsKey("borough") || setValues
						.containsKey("zip"))) {
			return "address";
		}
		if (setValues.containsKey("borough") && setValues.containsKey("block")
				&& setValues.containsKey("lot")) {
			return "bbl";
		}
		if (setValues.containsKey("bin")) {
			return "bin";
		}

		if (setValues.containsKey("onStreet")
				&& setValues.containsKey("crossStreetOne")
				&& setValues.containsKey("crossStreetTwo")
				&& setValues.containsKey("borough")
				&& (setValues.containsKey("boroughCrossStreetOne")
						|| setValues.containsKey("boroughCrossStreetTwo")
						|| setValues.containsKey("compassDirection") || true)) {
			return "blockface";
		}
		if (setValues.containsKey("crossStreetOne")
				&& setValues.containsKey("crossStreetTwo")
				&& setValues.containsKey("borough")
				&& (setValues.containsKey("boroughCrossStreetTwo")
						|| setValues.containsKey("compassDirection") || true)) {
			return "intersection";
		}
		return "place";

	}

	public static void printStats() {
		System.out.println("Address->" + ad.getTotal());
		System.out.println("Intersection->" + in.getTotal());
		System.out.println("Place->" + pl.getTotal());
		System.out.println("Block->" + bl.getTotal());
		System.out.println("Search->" + sr.getTotal());

		System.out.println("CorrectAddress->" + ad.getCorrect());
		System.out.println("CorrectIntersection->" + in.getCorrect());
		System.out.println("CorrectPlace->" + pl.getCorrect());
		System.out.println("CorrectBlock->" + bl.getCorrect());

		System.out.println("IntAddress->" + ad.getCommon());
		System.out.println("IntIntersection->" + in.getCommon());
		System.out.println("IntPlace->" + pl.getCommon());
		System.out.println("IntBlock->" + bl.getCommon());

	}

}
