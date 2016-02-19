package com.api.infoholder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;

import com.api.utils.FileUtils;
import com.opencsv.CSVWriter;

public class InfoHolderHrIa {

	private String url;
	private String apiData;
	private boolean available;
	private String dateCaptured;
	private String optional;
	private String ignoreNow;
	private String timeStamp;
	private String[] original;
	private String[] keys;
	private HashMap<String, String> outputValues;

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public boolean putInMap(String key, String value) {
		if (this.outputValues == null) {
			this.outputValues = new HashMap<String, String>();
		}
		if (this.outputValues.containsKey(key)) {
			return false;
		} else {
			this.outputValues.put(key, value);
			return true;
		}
	}

	public void setOriginal(String[] line) {
		this.original = line;
	}

	public String[] getOriginal() {
		return this.original;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getApiData() {
		if (apiData == null) {
			return "";
		}
		return apiData;
	}

	public void setApiData(String apiData) {
		this.apiData = apiData;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getDateCapturedFormatted() {
		if (dateCaptured != null) {
			String toReturn = dateCaptured.substring(0, 4) + "-"
					+ dateCaptured.substring(4, 6) + "-"
					+ dateCaptured.substring(6, 8);
			return toReturn;
		}
		return null;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public String getIgnoreNow() {
		return ignoreNow;
	}

	public void setIgnoreNow(String ignoreNow) {
		this.ignoreNow = ignoreNow;
	}

	public void printCSV(FileWriter writer) {
		try {
			// writer.write("\"");
			for (String value : original) {
				writer.write(FileUtils.formatForCSV(value) + ",");
			}
			if (this.outputValues != null) {
				for (String key : this.keys) {
					writer.write(FileUtils.formatForCSV(this.outputValues
							.get(key)) + ",");
				}
			}
			writer.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeCSV(CSVWriter writer) {
		String[] output = new String[this.keys.length];
		int i=0;
		for (String key : this.keys) {
			if (this.outputValues.containsKey(key)){
				output[i] = this.outputValues.get(key);
			} else{
				output[i] = null;
			}
			i++;
		}
		String[] toWrite = ArrayUtils.addAll(original,output);
		writer.writeNext(toWrite);
	}

	public void printCSV() {
		for (String value : original) {
			System.out.println(FileUtils.formatForCSV(value) + ",");
		}
		if (this.outputValues != null) {
			for (String key : this.keys) {
				System.out.println(FileUtils.formatForCSV(this.outputValues
						.get(key)) + ",");
			}
		}
		System.out.println("\r\n");
	}
}
