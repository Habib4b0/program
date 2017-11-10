/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.itemgroups.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsItemGrpQueryContants {

	private GtnWsItemGrpQueryContants() {
	}

	public static final String GTN_IFP_SEARCH_QUERY = " FROM DBO.ITEM_GROUP IG JOIN COMPANY_MASTER IM ON IG.COMPANY_MASTER_SID=IM.COMPANY_MASTER_SID ";
	public static final String GTN_IFP_SEARCH_QUERY_SELECT = " SELECT COUNT(DISTINCT IG.ITEM_GROUP_SID)  ";
	public static final String GTN_IFP_AUDIT_SEARCH_QUERY_SELECT = " SELECT COUNT(IG.ITEM_GROUP_SID) ";
	public static final String GTN_IFP_SEARCH_QUERY_WHERE = GtnFrameworkWebserviceConstant.IMINBOUND_STATUS_D;
	public static final String GTN_IFP_AUDIT_SEARCH_QUERY = " FROM DBO.HIST_ITEM_GROUP IG JOIN COMPANY_MASTER IM ON IG.COMPANY_MASTER_SID=IM.COMPANY_MASTER_SID  ";
	public static final String GTN_IFP_AVAILABLE_SEARCH_QUERY = " FROM\n" + "	ITEM_MASTER IM\n "
			+ " JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID " + "LEFT JOIN UDCS UDC ON\n"
			+ "	IM.ITEM_MASTER_SID = UDC.MASTER_SID\n" + "	AND UDC.MASTER_TYPE = 'ITEM_MASTER'\n"
			+ "LEFT JOIN HELPER_TABLE H_UDC1 ON\n" + "	UDC.UDC1 = H_UDC1.HELPER_TABLE_SID\n"
			+ "LEFT JOIN HELPER_TABLE H_UDC2 ON\n" + "	UDC.UDC2 = H_UDC2.HELPER_TABLE_SID\n"
			+ "LEFT JOIN HELPER_TABLE H_UDC3 ON\n" + "	UDC.UDC3 = H_UDC3.HELPER_TABLE_SID\n"
			+ "LEFT JOIN HELPER_TABLE H_UDC4 ON\n" + "	UDC.UDC4 = H_UDC4.HELPER_TABLE_SID\n"
			+ "LEFT JOIN HELPER_TABLE H_UDC5 ON\n" + "	UDC.UDC5 = H_UDC5.HELPER_TABLE_SID\n"
			+ "LEFT JOIN HELPER_TABLE H_UDC6 ON\n" + "	UDC.UDC6 = H_UDC6.HELPER_TABLE_SID  "
			+ "LEFT JOIN HELPER_TABLE STRENGTH ON\n" + "        IM.STRENGTH = STRENGTH.HELPER_TABLE_SID";
	public static final String GTN_IFP_AVAILABLE_SEARCH_QUERY_SELECT = " SELECT COUNT(DISTINCT IM.ITEM_MASTER_SID) ";
	public static final String GTN_IFP_AVAILABLE_SEARCH_QUERY_WHERE = GtnFrameworkWebserviceConstant.IMINBOUND_STATUS_D;
	public static final String GTN_IFP_SELECTED_SEARCH_QUERY = " FROM\n" + "    IMTD_ITEM_GROUP_DETAILS IMTD\n"
			+ "JOIN ITEM_MASTER IM ON\n" + "        IMTD.ITEM_MASTER_SID= IM.ITEM_MASTER_SID\n"
			+ "JOIN BRAND_MASTER BM ON\n" + "        BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
			+ "LEFT JOIN UDCS UDC ON\n" + "        IM.ITEM_MASTER_SID = UDC.MASTER_SID\n"
			+ "        AND UDC.MASTER_TYPE = 'ITEM_MASTER'\n" + "LEFT JOIN HELPER_TABLE H_UDC1 ON\n"
			+ "        UDC.UDC1 = H_UDC1.HELPER_TABLE_SID\n" + "LEFT JOIN HELPER_TABLE H_UDC2 ON\n"
			+ "        UDC.UDC2 = H_UDC2.HELPER_TABLE_SID\n" + "LEFT JOIN HELPER_TABLE H_UDC3 ON\n"
			+ "        UDC.UDC3 = H_UDC3.HELPER_TABLE_SID\n" + "LEFT JOIN HELPER_TABLE H_UDC4 ON\n"
			+ "        UDC.UDC4 = H_UDC4.HELPER_TABLE_SID\n" + "LEFT JOIN HELPER_TABLE H_UDC5 ON\n"
			+ "        UDC.UDC5 = H_UDC5.HELPER_TABLE_SID\n" + "LEFT JOIN HELPER_TABLE H_UDC6 ON\n"
			+ "        UDC.UDC6 = H_UDC6.HELPER_TABLE_SID\n " + "LEFT JOIN HELPER_TABLE STRENGTH ON\n"
			+ "        IM.STRENGTH = STRENGTH.HELPER_TABLE_SID";
	public static final String GTN_IFP_SELECTED_SEARCH_QUERY_WHERE = GtnFrameworkWebserviceConstant.IMINBOUND_STATUS_D;

}
