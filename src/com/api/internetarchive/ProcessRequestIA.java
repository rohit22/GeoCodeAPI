package com.api.internetarchive;

import java.util.ArrayList;
import java.util.HashMap;

import com.api.config.ConfigHolderIA;
import com.api.geocode.JsonParse;
import com.api.infoholder.InfoHolderHrIa;


public class ProcessRequestIA {
	static ConfigHolderIA chi;
	UrlType urlt;
	public ProcessRequestIA(ConfigHolderIA ci){
		ProcessRequestIA.chi = ci;
		urlt = new UrlType(ci);
	}
	
	public String test(InfoHolderHrIa ia) {
		String output = null;
		output = urlt.get(ia);
		return output;
	}
	
	public boolean set(InfoHolderHrIa hr) {
		String output = null;
		output = urlt.get(hr);
		if (output.trim().length()>0){
			return true;
		}
		return false;
	}	
}
