package com.api.humanresource;

import com.api.config.ConfigHolderHR;
import com.api.infoholder.InfoHolderHrIa;


public class ProcessRequestHR {
	static ConfigHolderHR chi;
	UrlType urlt;
	
	public ProcessRequestHR(ConfigHolderHR ci){
		ProcessRequestHR.chi = ci;
		urlt = new UrlType(ci);
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
