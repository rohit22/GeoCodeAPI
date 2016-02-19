package com.api.types.geo;

import com.api.infoholder.InfoHolderGeo;

public interface IType {
	
	public String get(InfoHolderGeo ih);
	public int getTotal();
	public int getCommon();
	public int getCorrect();
	
}
