package com.api.run;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;

import org.apache.commons.lang3.ArrayUtils;

import com.api.config.ConfigHolderGeo;
import com.api.config.ConfigHolderHR;
import com.api.config.ConfigHolderIA;
import com.api.geocode.ProcessRequestGeo;
import com.api.humanresource.ProcessRequestHR;
import com.api.infoholder.InfoHolderHrIa;
import com.api.internetarchive.ProcessRequestIA;
import com.api.utils.FileUtils;
import com.api.utils.SendEmail;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class RunForLargeFilesHR {

	ConfigHolderHR chi;
	ProcessRequestHR pri;

	public static void main(String[] args) throws IOException {
		String configPath = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/etc/config.xml";
		String config = "HumanResource";
		String timeStamp = "2009";
		String inputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/Data set URLs HRWA-SOLR 2016_tlds.csv";
		String outputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/result_Data set URLs HRWA-SOLR 2016_tlds_2.csv";

		String[] keys = new String[2];
		keys[0] = "date_of_capture_yyyymmdd";
		keys[1] = "archived_url";

		RunForLargeFilesHR check = new RunForLargeFilesHR(configPath, config);

		check.runHR(outputFile, inputFile, timeStamp, 1, keys,false);
	}

	public RunForLargeFilesHR(String configPath, String config) {
		chi = new ConfigHolderHR(configPath, config);
		pri = new ProcessRequestHR(chi);
	}

	public String runHRWithFile(String inFile, String outputFile,
			String timeStamp, int url, String[] keys, boolean email) {
		// String configPath =
		// "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/etc/config.xml";
		// String config = "HumanResource";
		// String timeStamp = "2008";
		try {
			return runHR(outputFile, inFile, timeStamp, url, keys,email);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String runHR(String outputFile, String inputFile, String timeStamp,
			int url, String[] keys, boolean email) throws IOException {

		CSVWriter writer = new CSVWriter(new FileWriter(new File(outputFile)),
				',');

		Session s = SendEmail.getSession("app4api.noreply",
				"digitalCentersIntern");
		
		CSVReader reader = FileUtils.getCSVReader(inputFile);
		InfoHolderHrIa hr = new InfoHolderHrIa();

		String[] header = ArrayUtils.addAll(reader.readNext(), keys);
		writer.writeNext(header);

		ArrayList<String[]> lines = (ArrayList<String[]>) reader.readAll();
		int i = 0;
		int count = 0;
		int chunk=1;
		boolean isWritten = false;
		for (String[] nextLine : lines) {
			if (length(nextLine) > url) {
				count++;
				System.out.println(nextLine.length + "<-->" + i++);
				hr = new InfoHolderHrIa();
				hr.setKeys(keys);
				hr.setOriginal(nextLine);
				hr.setTimeStamp(timeStamp);
				hr.setUrl(nextLine[url]);
				if (pri.set(hr)) {
					hr.writeCSV(writer);
					isWritten = true;
					if (email) {
						if (count == 2000) {
							count = 0;
							writer.flush();
							writer.close();
							isWritten = false;
							SendEmail.sendEmailWithAttachment(s,
									"Please find attached the result of chunk number "
											+ String.valueOf(chunk),
									"rohit14331@gmail.com", "Human Resources Archive",
									outputFile,
									"chunk-" + String.valueOf(chunk) + ".csv");
							writer = new CSVWriter(new FileWriter(new File(
									outputFile)), ',');
							writer.writeNext(header);
							chunk++;
						}
					}
				}
				System.out.println("<------------->");
			}
		}

		if (isWritten) {
			writer.flush();
			writer.close();
			if (email) {
				SendEmail.sendEmailWithAttachment(s,
						"Please find attached the result of chunk number "
								+ String.valueOf(chunk),
						"rohit14331@gmail.com", "Human Resources Archive",
						outputFile,
						"chunk-" + String.valueOf(chunk) + ".csv");
			}

		}
		reader.close();
		return outputFile;
	}

	private static int length(String[] chunks) {
		int toReturn = 0;
		for (String s : chunks) {
			toReturn = toReturn + s.trim().length();
		}
		return toReturn;

	}
}
