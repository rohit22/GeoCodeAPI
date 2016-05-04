package com.api.run;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.mail.Session;

import org.apache.commons.lang3.ArrayUtils;

import com.api.general.ProcessRequestGeneral;
import com.api.infoholder.InfoHolderGeneral;
import com.api.utils.FileUtils;
import com.api.utils.SendEmail;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class RunForLargeFileGeneral {

	ProcessRequestGeneral pr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String url = "https://api.cityofnewyork.us/geoclient/v1/address.json?houseNumber=346&street=Broadway&zip=10013&app_id=7a4e791c&app_key=2015DigitalIntern";
		String url = "https://api.what3words.com/position?key=CYLC1G22&lang=en&position=51.521251,-0.203586";
		
		//String inputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/Education_and_Youth_to_Geo.csv";
		//String outputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/output_education_youth.csv";

		String inputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/test_12.csv";
		String outputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/test_3words.csv";
		
		String[] keys = new String[6];
		/*keys[0] = "xCoordinate";
		keys[1] = "yCoordinate";
		keys[2] = "fromXCoordinate";
		keys[3] = "toXCoordinate";
		keys[4] = "fromYCoordinate";
		keys[5] = "toYCoordinate";*/
		keys[0] = "words";

		HashMap<String, Integer> urlKeys = new HashMap<String, Integer>();

/*		urlKeys.put("houseNumber", 1);
		urlKeys.put("street", 6);
		urlKeys.put("zip", 8);*/
		
		urlKeys.put("position", 23);

		RunForLargeFileGeneral check = new RunForLargeFileGeneral(url);
		check.runGeneralWithFile(inputFile, outputFile, keys, urlKeys, false,
				"rohit14331@gmail.com");

	}

	public RunForLargeFileGeneral(String url) {
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		pr = new ProcessRequestGeneral(url);
	}

	public String runGeneralWithFile(String inputFile, String outputFile,
			String[] keys, HashMap<String, Integer> urlKeys, boolean sendEmail,
			String emailID) {

		try {
			return runGeneral(inputFile, outputFile, keys, urlKeys, sendEmail,
					emailID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String runGeneral(String inputFile, String outputFile,
			String[] keys, HashMap<String, Integer> urlKeys, boolean sendEmail,
			String emailID) throws IOException {

		CSVWriter writer = new CSVWriter(new FileWriter(new File(outputFile)),
				',');
		System.out.println(inputFile);

		int count = 1;
		CSVReader reader = FileUtils.getCSVReader(inputFile);

		Session s = SendEmail.getSession("app4api.noreply",
				"digitalCentersIntern");

		int max = getMax(urlKeys.values());

		String[] header = ArrayUtils.addAll(reader.readNext(), keys);
		writer.writeNext(header);
		int chunk = 0;
		InfoHolderGeneral ih = new InfoHolderGeneral();
		boolean isWritten = false;
		try {
			// ArrayList<String[]> lines = (ArrayList<String[]>)
			// reader.readAll();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				System.out.println(nextLine.length + "<-->" + nextLine.length
						+ "<-->" + max);
				if (nextLine.length >= max) {
					count++;
					// nextLine[] is an array of values from the line
					ih = new InfoHolderGeneral();
					ih.setKeys(keys);
					ih.setOriginal(nextLine);
					for (String key : urlKeys.keySet()) {
						String value = nextLine[urlKeys.get(key)];
						ih.putInMapInput(key, value);
					}
					if (pr.set(ih)) {
						ih.writeCSV(writer);
						isWritten = true;
	//					System.out.println(count + " success");
						if (sendEmail) {
							if (count == 2000) {
								count = 0;
								writer.flush();
								writer.close();
								isWritten = false;
								SendEmail.sendEmailWithAttachment(s,
										"Please find attached the result of chunk number "
												+ String.valueOf(chunk),
										emailID, "GeoCode Result", outputFile,
										"chunk-" + String.valueOf(chunk)
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
			if (sendEmail) {
				SendEmail.sendEmailWithAttachment(s, "Test", emailID, "Test",
						outputFile, "chunk-" + String.valueOf(chunk) + ".csv");
			}

		}
		reader.close();
		return outputFile;
	}

	private int getMax(Collection<Integer> array) {
		int maxInt = Integer.MIN_VALUE;
		for (Integer val : array) {
			if (val > maxInt) {
				maxInt = val;
			}
		}
		return maxInt;
	}

}
