package com.api.run;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.mail.Session;
import org.apache.commons.lang3.ArrayUtils;
import com.api.config.ConfigHolderIA;
import com.api.infoholder.InfoHolderHrIa;
import com.api.internetarchive.ProcessRequestIA;
import com.api.utils.FileUtils;
import com.api.utils.SendEmail;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class RunForLargeFilesIA {

	ConfigHolderIA chi;
	ProcessRequestIA pri;

	public static void main(String[] args) throws IOException {
		String configPath = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/etc/config.xml";
		String config = "InternetArchive";
		String timeStamp = "2008";
		String inputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/Data set URLs IA 2016.csv";
		String outputFile = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/test/result_Data set URLs IA 2016_1.csv";

		String[] keys = new String[3];
		keys[0] = "url";
		keys[1] = "available";
		keys[2] = "timestamp";

		RunForLargeFilesIA runner = new RunForLargeFilesIA(configPath, config);
		String oFile = runner.runIA(outputFile, inputFile, timeStamp, 1, keys,
				false,"rohit14331@gmail.com");
		System.out.println(oFile);

	}

	public RunForLargeFilesIA(String configPath, String config) {
		chi = new ConfigHolderIA(configPath, config);
		pri = new ProcessRequestIA(chi);
	}

	public String runIAWithFile(String inFile, String outputFile,
			String timeStamp, int url,
			String[] keys, boolean sendEmail, String emailID) {
		// String configPath =
		// "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/etc/config.xml";
		// String config = "HumanResource";
		// String timeStamp = "2008";
		try {
			return runIA(outputFile, inFile, timeStamp,
					url, keys, sendEmail, emailID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String runIA(String outputFile, String inputFile, String timeStamp,
			int url, String[] keys, boolean sendEmail, String emailID) throws IOException {

		CSVWriter writer = new CSVWriter(new FileWriter(new File(outputFile)),
				',');

		Session s = SendEmail.getSession("app4api.noreply",
				"digitalCentersIntern");

		InfoHolderHrIa ia = new InfoHolderHrIa();

		CSVReader reader = FileUtils.getCSVReader(inputFile);

		String[] header = ArrayUtils.addAll(reader.readNext(), keys);
		writer.writeNext(header);

		int i = 0;
		boolean isWritten = false;
		int count = 0;
		int chunk = 1;
		try {
			ArrayList<String[]> lines = (ArrayList<String[]>) reader.readAll();
			for (String[] nextLine : lines) {
				if (length(nextLine) > url) {
					count++;
					System.out.println(nextLine.length + "<-->" + i++);
					ia = new InfoHolderHrIa();
					ia.setKeys(keys);
					ia.setOriginal(nextLine);
					ia.setTimeStamp(timeStamp);
					ia.setUrl(nextLine[url]);
					if (pri.set(ia)) {
						ia.writeCSV(writer);
						isWritten = true;
						if (sendEmail) {
							if (count == 2000) {
								count = 0;
								writer.flush();
								writer.close();
								isWritten = false;
								SendEmail.sendEmailWithAttachment(s,
										"Please find attached the result of chunk number "
												+ String.valueOf(chunk),
										emailID,
										"Internet Archive Result", outputFile,
										"chunk-" + String.valueOf(chunk)
												+ ".csv");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (isWritten) {
			writer.flush();
			writer.close();
			if (sendEmail) {
				SendEmail.sendEmailWithAttachment(s,
						"Please find attached the result of chunk number "
								+ String.valueOf(chunk),
						emailID, "Internet Archive Result",
						outputFile, "chunk-" + String.valueOf(chunk) + ".csv");
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
