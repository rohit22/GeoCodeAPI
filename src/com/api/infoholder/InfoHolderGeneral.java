package com.api.infoholder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.api.utils.FileUtils;
import com.opencsv.CSVWriter;

public class InfoHolderGeneral {

	private String type = null;
	private String oFileType = null;
	private boolean isSet;
	private String sourceString;
	private String[] original;
	private String[] keys;
	private HashMap<String, String> outputValues;
	private String sourceUrl;
	private Map<String, String> inputValues;

	public String[] getOriginal() {
		return original;
	}

	public void setOriginal(String[] original) {
		this.original = original;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public boolean putInMapOutput(String key, String value) {
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
	
	public void putInMapInput(String key, String value) {
		if (this.inputValues == null) {
			this.inputValues = new HashMap<String, String>();
		}
		this.inputValues.put(key, value);
	}
	
	public Map<String, String> getInputValues(){
		return this.inputValues;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = removeUnwantedChars(type);
	}

	public String getoFileType() {
		return oFileType;
	}

	public void setoFileType(String oFileType) {
		this.oFileType = removeUnwantedChars(oFileType);
	}

	private String removeUnwantedChars(String input) {
		return input.replaceAll("[^A-Za-z0-9% ]", "");
	}

	public boolean isSet() {
		return isSet;
	}

	public void setSet(boolean isSet) {
		this.isSet = isSet;
	}

	public String getSourceString() {
		return sourceString;
	}

	public void setSourceString(String sourceString) {
		this.sourceString = sourceString;
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
			} else {
				for (String key : this.keys) {
					writer.write(FileUtils.formatForCSV("null") + ",");
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
		int i = 0;
		for (String key : this.keys) {
			if (this.outputValues.containsKey(key)) {
				output[i] = this.outputValues.get(key);
			} else {
				output[i] = null;
			}
			i++;
		}
		String[] toWrite = ArrayUtils.addAll(original, output);
		writer.writeNext(toWrite);
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	/*
	 * public void printCSV(FileWriter writer) { try { writer.write("\"" +
	 * getRowid() + "\",\"" + formatForCSV(getJournal()) + "\",\"" +
	 * formatForCSV(getArticle()) + "\",\"" + formatForCSV(getCitations()) +
	 * "\",\"" + formatForCSV(getUrl()) + "\",\"" + getAvailable() + "\",\"" +
	 * getDateCapturedFormatted() + "\",\"" + getHrURL() + "\"\r\n"); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } }
	 */
}
