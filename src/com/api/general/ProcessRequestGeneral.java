package com.api.general;

import java.util.Map;

import com.api.infoholder.InfoHolderGeneral;
import com.api.utils.UrlCall;

public class ProcessRequestGeneral {
	
	String sourceUrl;
	UrlType urlt;
	Map<String, String> queryParams;
		
	public ProcessRequestGeneral(String url){
		sourceUrl = url;
		String tempUrl = sourceUrl;
		UrlCall uc = new UrlCall(tempUrl);
		queryParams = uc.getInputKeysValues();
		urlt = new UrlType(sourceUrl, queryParams);
		
	}

	public boolean set(InfoHolderGeneral ig) {
		String output = null;
		output = urlt.getResult(ig);
		if (output.trim().length() > 0) {
			return true;
		}
		return false;
	}

}
