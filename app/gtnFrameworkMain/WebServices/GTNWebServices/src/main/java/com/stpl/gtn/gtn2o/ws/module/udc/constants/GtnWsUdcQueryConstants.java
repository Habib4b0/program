package com.stpl.gtn.gtn2o.ws.module.udc.constants;

public class GtnWsUdcQueryConstants {

	private GtnWsUdcQueryConstants() {

	}

	public static final String GTN_UDC_SEARCH_QUERY = " FROM DBO.HELPER_TABLE HT";
	public static final String GTN_UDC_BRANDSEARCH_QUERY = " FROM DBO.BRAND_MASTER BM ";
	public static final String GTN_UDC_BRANDSEARCH_WHERE_CLAUSE = " INBOUND_STATUS <> 'D'";
	public static final String GTN_UDC_SEARCH_WHERE_CLAUSE = "  HT.List_name NOT IN('CATEGORYNAME')";
}
