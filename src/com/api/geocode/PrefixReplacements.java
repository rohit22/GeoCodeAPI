package com.api.geocode;

import java.util.HashMap;

public class PrefixReplacements {

	/**
	 * @param args
	 */
	
	private static HashMap<String, String> preFixReplacements;
	
	public PrefixReplacements(){
		preFixReplacements = new HashMap<String, String>();
		preFixReplacements.put("102 CROSS DRIVE","102 STREET CROSSING");
		preFixReplacements.put("BEACH CHANNEL DRIVE CIRCLE","BEACH CHANNEL DRIVE");
		preFixReplacements.put("104 STREETT","104 STREET");
		preFixReplacements.put("125TH ST AND AMST","125 STREET AND AMSTERDAM AVENUE");
		preFixReplacements.put("127TH STRTEET","127 STREET");
		preFixReplacements.put("163 RD STREET","163 STREET");
		preFixReplacements.put("163","163 STREET");
		preFixReplacements.put("17 LANE","17 LANE AVENUE");
		preFixReplacements.put("186 LANE AENUE","186 LANE AVENUE");
		preFixReplacements.put("196 REET","196 STREET");
		preFixReplacements.put("200 STTREET","200 STREET");
		preFixReplacements.put("223 STREEET","223 STREET");
		preFixReplacements.put("2289 EAST 2 STREE","2289 EAST 2 STREET");
		preFixReplacements.put("37 AVENEU","37 AVENUE");
		preFixReplacements.put("59 TH STREET","59 STREET");
		preFixReplacements.put("70 TH STREET","70 STREET");
		preFixReplacements.put("72 AVENUE WEST","72 WEST AVENUE");
		preFixReplacements.put("80 TH STREET","80 STREET");
		preFixReplacements.put("8TH AVNEUE","8 AVENUE");
		preFixReplacements.put("AMSTERDAM AVNENUE","AMSTERDAM AVENUE");
		preFixReplacements.put("ARDEN ROAD","WOODS OF ARDEN ROAD");
		preFixReplacements.put("ARVERNE PROJECT","74-15 BEACH CHANNEL DRIVE");
		preFixReplacements.put("ASPEN KNOLL WAY","ASPEN KNOLLS WAY");
		preFixReplacements.put("ASTORIA BOULEVA","ASTORIA BOULEVARD");
		preFixReplacements.put("ATLANTIC AENUE","ATLANTIC AVENUE");
		preFixReplacements.put("AVENUE OF THE AME","6 AVENUE");
		preFixReplacements.put("AVENUE OF THE AMERICAS","6 AVENUE");
		preFixReplacements.put("BAINEBRIDGE AVENUE","BAINBRIDGE AVENUE");
		preFixReplacements.put("BAISLEY PARK PROJECT","116-45 GUY R BREWER BOULEVARD");
		preFixReplacements.put("BARTOW CIRCLE","PELHAM BRIDGE ROAD");
		preFixReplacements.put("BEACH CHANNEL D","BEACH CHANNEL DRIVE");
		preFixReplacements.put("BEACH CHANNEL DRI","BEACH CHANNEL DRIVE");
		preFixReplacements.put("BEACH CHANNEL STREET","BEACH CHANNEL DRIVE");
		preFixReplacements.put("BEDFOD AVENUE","BEDFORD AVENUE");
		preFixReplacements.put("BESSEMUND ROAD","BESSEMUND AVENUE");
		preFixReplacements.put("BESSEUMUND AVENUE","BESSEMUND AVENUE");
		preFixReplacements.put("BLACKFORD STREE","BLACKFORD STREET");
		preFixReplacements.put("BROOKLYN QUEENS E","BROOKLYN QUEENS EXPRESSWAY");
		preFixReplacements.put("BROOKLYN QUEENS EXPRES","BROOKLYN QUEENS EXPRESSWAY");
		preFixReplacements.put("BROOKLYN QUEENS","BROOKLYN QUEENS EXPRESSWAY");
		preFixReplacements.put("CANTERBERRY AVE","CANTERBURY AVENUE");
		preFixReplacements.put("CARISSLE PLACE","CARLISLE PLACE");
		preFixReplacements.put("CASTLETON PARK NORTH","185 ST MARKS PLACE");
		preFixReplacements.put("COLLEGE POINT BOULEVAR","COLLEGE POINT BOULEVARD");
		preFixReplacements.put("COOP CITY BOULEV","COOP CITY BOULEVARD");
		preFixReplacements.put("CRANSTAN AVENUE","CRANSTON STREET");
		preFixReplacements.put("DONGAN HILL AVE","DONGAN HILLS AVENUE");
		preFixReplacements.put("DONGAN HILL AVENUE","DONGAN HILLS AVENUE");
		preFixReplacements.put("DRUMGOOLE ROAD WE","DRUMGOOLE ROAD WEST");
		preFixReplacements.put("DRUMGOOLE ROAD","DRUMGOOLE ROAD");
		preFixReplacements.put("EAST BEDFORD PA","EAST BEDFORD PARK BOULEVARD");
		preFixReplacements.put("EAST BEDFORD PARK BOUL","EAST BEDFORD PARK BOULEVARD");
		preFixReplacements.put("EAST BEDFORD PARK BOULE","EAST BEDFORD PARK BOULEVARD");
		preFixReplacements.put("EAST MOSHOLU PA","MOSHOLU PARKWAY");
		preFixReplacements.put("EAST OCEANA DRI","OCEANA DRIVE EAST");
		preFixReplacements.put("EL GRANT HIGHWAY","EDWARD L GRANT HIGHWAY");
		preFixReplacements.put("FARMERS BOUELVARD","FARMERS BOULEVARD");
		preFixReplacements.put("FLATBUSH AVENUE EXTENS","FLATBUSH AVENUE");
		preFixReplacements.put("FLATBUSH","FLATBUSH AVENUE");
		preFixReplacements.put("FLATLANDS","FLATLANDS AVENUE");
		preFixReplacements.put("FOREST PARK DRIVE EAST","FOREST PARK DRIVE");
		preFixReplacements.put("FOREST PARK DRI","FOREST PARK DRIVE");
		preFixReplacements.put("FOREST PARK DRIVE WEST","FOREST PARK DRIVE");
		preFixReplacements.put("FORESTPARK DRIVE EAST","FOREST PARK DRIVE");
		preFixReplacements.put("FRANCIS LEWIS BOULEVAR","FRANCIS LEWISH BOULEVARD");
		preFixReplacements.put("FRANKILN AVENUE","FRANKLIN AVENUE");
		preFixReplacements.put("G R BREWER BLVD","GUY R BREWER BOULEVARD");
		preFixReplacements.put("GLENWOOD AVENUE","GLENWOOD ROAD");
		preFixReplacements.put("GLENWOOED","GLENWOOD ROAD");
		preFixReplacements.put("GRAND CENTRAL PAR","GRAND CENTRAL PARKWAY");
		preFixReplacements.put("GRANDCENTRAL PARK","GRAND CENTRAL PARKWAY");
		preFixReplacements.put("GUY R BREWER BOUL","GUY R BREWER BOULEVARD");
		preFixReplacements.put("HARDING EXPRESS","HORACE HARDING EXPRESSWAY");
		preFixReplacements.put("HAWK STONE AVENUE","HAWKSTONE STREET");
		preFixReplacements.put("HENLEY AVENUE","HENLEY ROAD");
		preFixReplacements.put("HICKSVILLE AVENUE","HICKSVILLE ROAD");
		preFixReplacements.put("HICKVILLE ROAD","HICKSVILLE ROAD");
		preFixReplacements.put("HICKVILLE","HICKSVILLE ROAD");
		preFixReplacements.put("HILLSIDEM AVENUE","HILLSIDE AVENUE");
		preFixReplacements.put("HUTCHINSON RIVER PARKW","HUTCHINSON RIVER PARKWAY");
		preFixReplacements.put("HUTCHINSON RIVER PARKWA","HUTCHINSON RIVER PARKWAY");
		preFixReplacements.put("JAMACIA AVENUIE","JAMAICA AVENUE");
		preFixReplacements.put("JAMAIC AVENUE","JAMAICA AVENUE");
		preFixReplacements.put("JEFFERSON ST","JEFFERSON STREET");
		preFixReplacements.put("JERESEY STREET","JERSEY STREET");
		preFixReplacements.put("JERESY STREET","JERSEY STREET");
		preFixReplacements.put("LOUIS NINE BLVDE","LOUIS NINE BOULEVARD");
		preFixReplacements.put("MARTIN LUTHER KING","MARTIN LUTHER KING PLACE");
		preFixReplacements.put("MC GUINNESS BOULE","MCGUINNESS BOULEVARD");
		preFixReplacements.put("MAYVILLE AVENUE","MAYVILLE STREET");
		preFixReplacements.put("METROPOLITAN AVEN","METROPOLITAN AVENUE");
		preFixReplacements.put("MORNINGSIDE","MORNINGSIDE AVENUE");
		preFixReplacements.put("MORNINGSIDE AVEN","MORNINGSIDE AVENUE");
		preFixReplacements.put("MORRISS STREET","MORRIS STREET");
		preFixReplacements.put("MOTHER GASTON B","MOTHER GASTON BOULEVARD");
		preFixReplacements.put("MOTHERGASTON BL","MOTHER GASTON BOULEVARD");
		preFixReplacements.put("MOTHERGASTON","MOTHER GASTON BOULEVARD");
		preFixReplacements.put("NORTH CONDUIT A","NORTH CONDUIT AVENUE");
		preFixReplacements.put("NORTH CONDUIT","NORTH CONDUIT AVENUE");
		preFixReplacements.put("OAKELY STREET","OAKLEY STREET");
		preFixReplacements.put("PENNSYLVANIA AVEN","PENNSYLVANIA AVENUE");
		preFixReplacements.put("PITKIN AVENUNE","PITKIN AVENUE");
		preFixReplacements.put("PORSPECT AVENUE","PROSPECT AVENUE");
		preFixReplacements.put("POWELLS COVE BOUL","POWELLS COVE BOULEVARD");
		preFixReplacements.put("PROSPECT PARK SOUTH WES","PROSPECT PARK SOUTHWEST");
		preFixReplacements.put("PROSPECT PARK SOUTHWES","PROSPECT PARK SOUTHWEST");
		preFixReplacements.put("PROSPECT","PROSPECT PARK SOUTHWEST");
		preFixReplacements.put("QUEENS BOLLEVARD","QUEENS BOULEVARD");
		preFixReplacements.put("QUEENS BOUELVARD","QUEENS BOULEVARD");
		preFixReplacements.put("REDERN AVENUE","REDFERN AVENUE");
		preFixReplacements.put("REFERN AVENUE","REDFERN AVENUE");
		preFixReplacements.put("RENSSELAER AVEN","RENSSELAER AVENUE");
		preFixReplacements.put("REV JAMES E POLITE AVE","REV JAMES E POLITE AVENUE");
		preFixReplacements.put("REVERAND JAMES POLITE","REV JAMES E POLITE AVENUE");
		preFixReplacements.put("RIEGALMAN BOARDWALK WE","RIEGELMANN BOARDWALK");
		preFixReplacements.put("RIEGALMAN BOARDWALK WES","RIEGELMANN BOARDWALK");
		preFixReplacements.put("RIEGELMAN BOARDWALK EA","RIEGALMANN BOARDWALK");
		preFixReplacements.put("RIEGELMAN BOARDWALK EAS","RIEGALMANN BOARDWALK");
		preFixReplacements.put("RIVER BOULEVARD","RIVERSIDE BOULEVARD");
		preFixReplacements.put("RIVERSIDE BOULEVA","RIVERSIDE BOULEVARD");
		preFixReplacements.put("ROACKAWAY BOULEVARD","ROCKAWAY BEACH BOULEVARD");
		preFixReplacements.put("ROCKAWAY BEACH BOULEV","ROCKAWAY BEACH BOULEVARD");
		preFixReplacements.put("ROCKAWAY BEACH BO","ROCKAWAY BEACH BOULEVARD");
		preFixReplacements.put("ROCKAWAY BEACH BOULEVA","ROCKAWAY BEACH BOULEVARD");
		preFixReplacements.put("ROCKAWAY BOUELVARD","ROCKAWAY BEACH BOULEVARD");
		preFixReplacements.put("ROCKAWAY FREEWA","ROCKAWAY FREEWAY");
		preFixReplacements.put("ROCKAWAY POINT BOULEVA","ROCKAWAY POINT BOULEVARD");
		preFixReplacements.put("ROCKAWAY BOUILEV","ROCKAWAY POINT BOULEVARD");
		preFixReplacements.put("ROCKNE AVENUE","ROCKAWAY AVENUE");
		preFixReplacements.put("ROCKWAY FREEWAY","ROCKAWAY FREEWAY");
		preFixReplacements.put("ROOSEVELT ISLAND BRIDG","ROOSEVELT ISLAND BRIDGE");
		preFixReplacements.put("SAINT PAULS COU","SAINT PAULS COURT");
		preFixReplacements.put("SCHIEFELIN AVEN","SCHIEFELIN AVENUE");
		preFixReplacements.put("SEAGIRT BOULEVA","SEAGIRT BOULEVARD");
		preFixReplacements.put("SEAGIRT BOULVERAD","SEAGIRT BOULEVARD");
		preFixReplacements.put("SHARROTTS AVE","SHARROTTS ROAD");
		preFixReplacements.put("SHORE FRONT PARKW","SHORE FRONT PARKWAY");
		preFixReplacements.put("ST NICHOLAS AVE 3","ST NICHOLAS AVENUE");
		preFixReplacements.put("ST NICHOLAS","ST NICHOLAS AVENUE");
		preFixReplacements.put("ST. JAMES PLACES","ST JAMES PLACE");
		preFixReplacements.put("STRICTLAND AVENUE","STRICKLAND AVENUE");
		preFixReplacements.put("THROGMORTAN AVENUE","THROGMORTON AVENUE");
		preFixReplacements.put("TIMBER RIDGE RO","TIMBER RIDGE DRIVE");
		preFixReplacements.put("TIMBER RIDGE ROAD","TIMBER RIDGE DRIVE");
		preFixReplacements.put("TIMBERRIDGE DRI","TIMBER RIDGE DRIVE");
		preFixReplacements.put("TIMBERRIDGE SRIVE","TIMBER RIDGE DRIVE");
		preFixReplacements.put("UNIVERSITY AVE","UNIVERSITY AVENUE");
		preFixReplacements.put("UNIVERSITY AVEN","UNIVERSITY AVENUE");
		preFixReplacements.put("VANCORTLANDT AVENUE WE","VAN CORTLANDT AVENUE");
		preFixReplacements.put("VANCORTLANDT PARK WEST","VAN CORTLANDT PARK");
		preFixReplacements.put("VANDERBILT","VANDERBILT AVENUE");
		preFixReplacements.put("VANERVEER STREET","VANDERVEER STREET");
		preFixReplacements.put("VERMONT PLACE","VERMONT STREET");
		preFixReplacements.put("VERMONT","VERMONT STREET");
		preFixReplacements.put("VETERANS ROAD WES","VETERANS ROAD");
		preFixReplacements.put("WAINWRIGHT AVEN","WAINWRIGHT AVENUE");
		preFixReplacements.put("WELLHOUSE DRVE","WELLHOUSE DRIVE");
		preFixReplacements.put("WEST 148 STEEET","148 STREET");
		preFixReplacements.put("WEST BEDFORD PARK BOUL","WEST BEDFORD PARK BOULEVARD");
		preFixReplacements.put("WEST OCEANA DRI","WEST OCEANA DRIVE");
		preFixReplacements.put("WEST SERVICE RO","WEST SERVICE ROAD");
		preFixReplacements.put("WESTCHESTER SQUAR","WESTCHESTER SQUARE");
		preFixReplacements.put("WESTMINSTER RO","WESTMINSTER ROAD");
		preFixReplacements.put("WESTMINISTER RO","WESTMINSTER ROAD");
		preFixReplacements.put("WESTMINISTER ROAD","WESTMINSTER ROAD");
		preFixReplacements.put("WILLETS POINT BOULEVAR","WILLETS POINT BOULEVARD");
		preFixReplacements.put("WOLWORTH AVENUE","WOOLWORTH AVENUE");
		preFixReplacements.put("WOODYCREST AVEN","WOODYCREST AVENUE");
	}
	
	public String replace(String toReplace){
		if (preFixReplacements.containsKey(toReplace)){
			return preFixReplacements.get(toReplace);
		}
		return null;
	}

}
