package com.api.run;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Session;

import org.apache.commons.lang3.ArrayUtils;

import com.api.config.ConfigHolderGeo;
import com.api.geocode.ProcessRequestGeo;
import com.api.infoholder.InfoHolderGeo;
import com.api.utils.FileUtils;
import com.api.utils.SendEmail;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class RunForLargeFileGeo {

	ConfigHolderGeo configHolderGeo;
	ProcessRequestGeo pr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String configPath = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/etc/config.xml";
		String config = "NycGeoCode";
		String inputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/Book1.csv";
		String outputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/output.csv";

		String[] keys = new String[6];
		keys[0] = "xCoordinate";
		keys[1] = "yCoordinate";
		keys[2] = "fromXCoordinate";
		keys[3] = "toXCoordinate";
		keys[4] = "fromYCoordinate";
		keys[5] = "toYCoordinate";

		RunForLargeFileGeo check = new RunForLargeFileGeo(configPath, config);
		check.runGeoWithFile(inputFile, outputFile, keys, 9, 1, 9, 8, 9, false);

	}

	public RunForLargeFileGeo(String configFile, String config) {
		configHolderGeo = new ConfigHolderGeo(configFile, config, null);
		pr = new ProcessRequestGeo(configHolderGeo);
	}

	public String runGeoWithFile(String inputFile, String outputFile,
			String[] keys, int inStreet, int borough, int crStreet,
			int houseNum, int street, boolean email) {

		try {
			return runGeo(inputFile, outputFile, keys, inStreet, borough,
					crStreet, houseNum, street,email);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String runGeo(String inputFile, String outputFile, String[] keys,
			int inStreet, int borough, int crStreet, int houseNum, int street,
			boolean email) throws IOException {

		CSVWriter writer = new CSVWriter(new FileWriter(new File(outputFile)),
				',');

		int count = 1;
		CSVReader reader = FileUtils.getCSVReader(inputFile);

		int[] list = new int[5];
		list[0] = inStreet;
		list[1] = borough;
		list[2] = crStreet;
		list[3] = houseNum;
		list[4] = street;

		Session s = SendEmail.getSession("app4api.noreply",
				"digitalCentersIntern");

		int max = getMax(list);

		String[] header = ArrayUtils.addAll(reader.readNext(), keys);
		writer.writeNext(header);
		int chunk = 0;
		InfoHolderGeo ih = new InfoHolderGeo();
		boolean isWritten = false;
		try {
			// ArrayList<String[]> lines = (ArrayList<String[]>)
			// reader.readAll();
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				// System.out.println(nextLine.length + "<-->" + i++);
				if (nextLine.length >= max) {
					count++;
					// nextLine[] is an array of values from the line
					ih = new InfoHolderGeo();
					ih.setKeys(keys);
					ih.setOriginal(nextLine);
					ih.setInStreet(nextLine[inStreet]);
					ih.setBorough(nextLine[borough]);
					ih.setCrStreet(nextLine[crStreet]);
					ih.setHouseNumber(nextLine[houseNum]);
					ih.setStreet(nextLine[street]);
					// ih.setX(nextLine[107]);
					// ih.setY(nextLine[108]);
					if (pr.set(ih)) {
						ih.writeCSV(writer);
						isWritten = true;
						System.out.println(count + " success");
						if (email) {
							if (count == 2000) {
								count = 0;
								writer.flush();
								writer.close();
								isWritten = false;
								SendEmail.sendEmailWithAttachment(s,
										"Please find attached the result of chunk number "
												+ String.valueOf(chunk),
										"rohit14331@gmail.com",
										"GeoCode Result", outputFile, "chunk-"
												+ String.valueOf(chunk)
												+ ".csv");
								writer = new CSVWriter(new FileWriter(new File(
										outputFile)), ',');
								writer.writeNext(header);
								chunk++;
							}
						}

					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count);
		// pr.printStats();
		if (isWritten) {
			writer.flush();
			writer.close();
			if (email) {
				SendEmail.sendEmailWithAttachment(s, "Test",
						"rohit14331@gmail.com", "Test", outputFile, "chunk-"
								+ String.valueOf(chunk) + ".csv");
			}

		}
		reader.close();
		return outputFile;
	}

	private int getMax(int[] array) {
		int maxInt = Integer.MIN_VALUE;
		for (int val : array) {
			if (val > maxInt) {
				maxInt = val;
			}
		}
		return maxInt;
	}

}
