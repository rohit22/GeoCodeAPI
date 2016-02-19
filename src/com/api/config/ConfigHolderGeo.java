package com.api.config;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.api.utils.FileUtils;

public class ConfigHolderGeo implements ConfigHolder {

	private static String url;
	private static String appID;
	private static String appKey;
	private static ArrayList<String> types;

	public void setUrl(String url) {
		// TODO Auto-generated method stub
		ConfigHolderGeo.url = url;
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		return url;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		ConfigHolderGeo.appID = appID;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		ConfigHolderGeo.appKey = appKey;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public ConfigHolderGeo(String configPath, String config, ArrayList<String> types) {
		// String xml = utils.FileUtils.readFileAsString(configPath);
		NodeList nList = FileUtils.getNodeListFromXml(configPath, config);
		if (types!=null){
			ConfigHolderGeo.types = types;
		}
		if (nList != null && nList.getLength() == 1) {
			NodeList children = nList.item(0).getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node n = children.item(i);
				if (n.getNodeName().equals("url")) {
					this.setUrl(n.getTextContent().trim());
				}
				if (n.getNodeName().equals("app_id")) {
					this.setAppID(n.getTextContent());
				}
				if (n.getNodeName().equals("app_key")) {
					this.setAppKey(n.getTextContent());
				}
				if (types==null && n.getNodeName().equals("types")) {
					types = new ArrayList<String>();
					String[] type = n.getTextContent().split(",");
					for (String temp : type) {
						if (temp != null) {
							System.out.println(temp);
							types.add(temp);
						}
					}

				}
			}
		} else {
			System.out.println("Incorrect config file or api name");
			System.exit(0);
		}
		return;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String configPath = "/Users/Admin/Documents/DigitalIntern2015/workspace/GeoCodeAPI/etc/config.xml";
		String config = "NycGeoCode";
		ConfigHolder nycGeo = new ConfigHolderGeo(configPath, config,null);
		System.out.println(nycGeo.getUrl() + " "
				+ ((ConfigHolderGeo) nycGeo).getAppKey() + " "
				+ ((ConfigHolderGeo) nycGeo).getAppID());

	}

}
