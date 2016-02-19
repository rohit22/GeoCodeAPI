package com.api.config;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.api.utils.FileUtils;

public class ConfigHolderHR {
	private static String urlStart;
	private static String urlEnd;
	
	public String getUrlStart() {
		return urlStart;
	}

	public void setUrlStart(String urlStart) {
		ConfigHolderHR.urlStart = urlStart;
	}
	
	public ConfigHolderHR(String configPath, String config){
		NodeList nList = FileUtils.getNodeListFromXml(configPath, config);
		if (nList != null && nList.getLength() == 1) {
			NodeList children = nList.item(0).getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node n = children.item(i);
				if (n.getNodeName().equals("urlStart")) {
					this.setUrlStart(n.getTextContent().trim());
				}
				if (n.getNodeName().equals("urlEnd")) {
					this.setUrlEnd(n.getTextContent().trim());
				}
			}
		}
		else {
			System.out.println("Incorrect config file or api name");
			System.exit(0);
		}
		return;
	}

	public String getUrlEnd() {
		return urlEnd;
	}

	public void setUrlEnd(String urlEnd) {
		ConfigHolderHR.urlEnd = urlEnd;
	}

}
