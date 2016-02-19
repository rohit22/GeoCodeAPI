package com.api.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;

public class FileUtils {

	public static String readFileAsString(String filePath) {
		StringBuffer sb = new StringBuffer();
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fileReader);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static ArrayList<String> readFileAsList() {

		return null;
	}

	public static CSVReader getCSVReader(String fileName){
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reader;
	}
	
	public static BufferedReader getReaderForFile(String filePath) {
		FileReader fileReader;
		BufferedReader br = null;
		try {
			fileReader = new FileReader(filePath);
			br = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return br;
	}

	public static NodeList getNodeListFromXml(String filePath, String parentNode){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document dom = db.parse(new File(filePath));
			Element root = dom.getDocumentElement();
			if (parentNode.equals("root")){
				NodeList nl = root.getChildNodes();
				return nl;
			}
			else{
				NodeList nl = root.getElementsByTagName(parentNode);
				return nl;
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}
	
	public static String formatForCSV(String input) {
		if (input != null) {
			input = input.replaceAll("\"", "\"\"");
		}
		input = "\"" + input + "\"";
		return input;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
