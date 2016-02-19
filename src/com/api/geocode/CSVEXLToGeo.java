package com.api.geocode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.api.infoholder.InfoHolderGeo;
import com.opencsv.CSVReader;

public class CSVEXLToGeo {

	private CSVReader reader;

	public ArrayList<InfoHolderGeo> createGeo(String fileName, String oType) {
		// TODO Auto-generated method stub
		// 95,96,97,98,112,
		// adnum,street,instreet,crstreet,borough

		ArrayList<InfoHolderGeo> listToReturn = new ArrayList<InfoHolderGeo>();
		InfoHolderGeo ih = new InfoHolderGeo();
		try {
			reader = new CSVReader(new FileReader(fileName));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine.length == 114) {
					// nextLine[] is an array of values from the line
					ih = new InfoHolderGeo();
					ih.setoFileType(oType);
					ih.setInStreet(nextLine[97]);
					ih.setBorough(nextLine[112]);
					ih.setCrStreet(nextLine[98]);
					ih.setHouseNumber(nextLine[95]);
					ih.setStreet(nextLine[96]);
					listToReturn.add(ih);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listToReturn;
	}
	
	

}
