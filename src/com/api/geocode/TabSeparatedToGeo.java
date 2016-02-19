package com.api.geocode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.api.infoholder.InfoHolderGeo;
import com.api.utils.FileUtils;

public class TabSeparatedToGeo {

	public ArrayList<InfoHolderGeo> createGeo(String fileName, String oType) {
		// TODO Auto-generated method stub
		
		BufferedReader br = FileUtils.getReaderForFile(fileName);
		ArrayList<InfoHolderGeo> listToReturn = new ArrayList<InfoHolderGeo>();
		InfoHolderGeo ih = new InfoHolderGeo();
		if (br!=null){
			String line;
			try {
				while((line=br.readLine())!=null){
					System.out.println(line);
					String[] chunks = line.split("\t");
					System.out.println(chunks.length);
					if (chunks.length==3){
						ih = new InfoHolderGeo();
						ih.setHouseNumber(chunks[0]);
						ih.setStreet(chunks[1]);
						ih.setBorough(chunks[2]);
						listToReturn.add(ih);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listToReturn;
	}

}
