package com.stpl.gtn.gtn2o.ws.module.processscheduler.constant;

public class GtnWsCffQueryConstants {

	private GtnWsCffQueryConstants() {
		
	}
	
	public static final String GTN_CFF_SEARCH_QUERY_SELECT = "Select Count(*) ";
	public static final String GTN_IFP_SEARCH_QUERY_COUNT = "";
	public static final String GTN_IFP_SEARCH_QUERY = "FROM ST_CFF_OUTBOUND_MASTER ST "
			+ "JOIN CFF_OUTBOUND_MASTER CFFOM ON ST.CFF_DETAILS_SID = CFFOM.CFF_DETAILS_SID " 
			+ "AND ST.RS_MODEL_SID = CFFOM.RS_MODEL_SID AND ST.PERIOD_SID = CFFOM.PERIOD_SID "
			+ "LEFT JOIN HELPER_TABLE CFT ON CFT.HELPER_TABLE_SID = CFFOM.TYPE "
			+ "LEFT JOIN HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID = CFFOM.CONTRACT_TYPE "
			+ "LEFT JOIN HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID = CFFOM.DEDUCTION_CATEGORY "
			+ "LEFT JOIN HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID = CFFOM.DEDUCTION_TYPE "
			+ "LEFT JOIN HELPER_TABLE HT4 ON HT4.HELPER_TABLE_SID = CFFOM.DEDUCTION_PROGRAM "
			+ "LEFT JOIN HELPER_TABLE HT5 ON HT5.HELPER_TABLE_SID = CFFOM.DEDUCTION_INCLUSION "
			+ "LEFT JOIN HELPER_TABLE HT6 ON HT6.HELPER_TABLE_SID = CFFOM.DEDUCTION_CATEGORY1 "
			+ "LEFT JOIN HELPER_TABLE HT7 ON HT7.HELPER_TABLE_SID = CFFOM.DEDUCTION_CATEGORY2 "
			+ "LEFT JOIN HELPER_TABLE HT8 ON HT8.HELPER_TABLE_SID = CFFOM.DEDUCTION_CATEGORY3 "
			+ "LEFT JOIN HELPER_TABLE HT9 ON HT9.HELPER_TABLE_sid = CFFOM.DEDUCTION_CATEGORY4 "
			+ "LEFT JOIN HELPER_TABLE HT10 ON HT10.HELPER_TABLE_SID = CFFOM.DEDUCTION_CATEGORY5 "
			+ "LEFT JOIN HELPER_TABLE HT11 ON HT11.HELPER_TABLE_sid = CFFOM.DEDUCTION_CATEGORY6 ";
}
