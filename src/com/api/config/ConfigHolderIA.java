package com.api.config;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.api.utils.FileUtils;

public class ConfigHolderIA implements ConfigHolder {
	private static String url;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		ConfigHolderIA.url = url;
	}
	
	public ConfigHolderIA(String configPath, String config){
		NodeList nList = FileUtils.getNodeListFromXml(configPath, config);
		if (nList != null && nList.getLength() == 1) {
			NodeList children = nList.item(0).getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node n = children.item(i);
				if (n.getNodeName().equals("url")) {
					this.setUrl(n.getTextContent().trim());
				}
			}
		}
		else {
			System.out.println("Incorrect config file or api name");
			System.exit(0);
		}
		return;
	}

}
