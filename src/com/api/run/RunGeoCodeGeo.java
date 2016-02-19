package com.api.run;

import java.io.IOException;
import java.util.ArrayList;
import com.api.config.ConfigHolderGeo;
import com.api.geocode.CSVEXLToGeo;
import com.api.geocode.ProcessRequestGeo;
import com.api.infoholder.InfoHolderGeo;

public class RunGeoCodeGeo {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String configPath = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/etc/config.xml";
		String config = "NycGeoCode";
//		String inputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/test.txt";
		String inputFile2 = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/2013noxy.csv";
		String oType = "json";

		ConfigHolderGeo configHolderGeo = new ConfigHolderGeo(configPath,
				config, null);

		CSVEXLToGeo ihc1 = new CSVEXLToGeo();
		ArrayList<InfoHolderGeo> list = ihc1.createGeo(inputFile2, oType);

		// InfoHolderCreater ihc = new TabSeparatedReader();
		// ArrayList<InfoHolder> list = ihc.create(inputFile,oType);

		ProcessRequestGeo pr = new ProcessRequestGeo(configHolderGeo);
		for (InfoHolderGeo ih : list) {
			pr.set(ih);
			for (String key : ih.getSetValues().keySet()) {
				System.out.println(key + "<-->" + ih.getSetValues().get(key));
			}
		}
	}
}
