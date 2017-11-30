/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.companygroups.constants;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsCGrpQueryContants {

	private GtnWsCGrpQueryContants() {
	}

	public static final String GTN_CGRP_SEARCH_QUERY = " FROM DBO.COMPANY_GROUP CG ";
	public static final String GTN_CGRP_COUNT_QUERY = " SELECT COUNT(DISTINCT COMPANY_GROUP_SID)  ";
	public static final String GTN_AUDIT_CGRP_COUNT_QUERY = " SELECT COUNT(COMPANY_GROUP_SID)  ";
	public static final String GTN_CGRP_AUDIT_SEARCH_QUERY = " FROM DBO.HIST_COMPANY_GROUP CG ";
	public static final String GTN_CGRP_AVAILABLE_TABLE_SEARCH_QUERY = " FROM COMPANY_MASTER CM OUTER APPLY( SELECT TOP 1 CTC.COMPANY_TRADE_CLASS, CTC.TRADE_CLASS_START_DATE, CTC.TRADE_CLASS_END_DATE, CTC.COMPANY_TRADE_CLASS_SID, CTC.COMPANY_MASTER_SID, HT_TC.DESCRIPTION AS DESCRIPTION FROM COMPANY_TRADE_CLASS CTC INNER JOIN HELPER_TABLE HT_TC ON CTC.COMPANY_TRADE_CLASS = HT_TC.HELPER_TABLE_SID WHERE CTC.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID ORDER BY CTC.MODIFIED_DATE DESC ) CTC OUTER APPLY( SELECT TOP 1 CPD.COMPANY_MASTER_SID, CPD.PARENT_COMPANY_MASTER_SID, CPD.PARENT_START_DATE, CPD.PARENT_END_DATE, CPD.PRIOR_PARENT_START_DATE, CPD.PRIOR_PARENT_CMPY_MASTER_SID, CPD.COMPANY_PARENT_DETAILS_SID FROM COMPANY_PARENT_DETAILS CPD WHERE CPD.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID ORDER BY CPD.MODIFIED_DATE DESC ) CPD JOIN HELPER_TABLE HT_CT ON CM.COMPANY_TYPE = HT_CT.HELPER_TABLE_SID JOIN HELPER_TABLE HT_CS ON CM.COMPANY_STATUS = HT_CS.HELPER_TABLE_SID LEFT JOIN HELPER_TABLE HT_ST ON CM.STATE = HT_ST.HELPER_TABLE_SID LEFT JOIN HELPER_TABLE HT_CN ON CM.COUNTRY = HT_CN.HELPER_TABLE_SID LEFT JOIN DBO.UDCS udcs ON CM.COMPANY_MASTER_SID = udcs.MASTER_SID AND UDCS.MASTER_TYPE='COMPANY_MASTER' LEFT JOIN HELPER_TABLE H_UDC1 ON H_UDC1.HELPER_TABLE_SID = udcs.UDC1 LEFT JOIN HELPER_TABLE H_UDC2 ON H_UDC2.HELPER_TABLE_SID = udcs.UDC2 LEFT JOIN HELPER_TABLE H_UDC3 ON H_UDC3.HELPER_TABLE_SID = udcs.UDC3 LEFT JOIN HELPER_TABLE H_UDC4 ON H_UDC4.HELPER_TABLE_SID = udcs.UDC4 LEFT JOIN HELPER_TABLE H_UDC5 ON H_UDC5.HELPER_TABLE_SID = udcs.UDC5 LEFT JOIN HELPER_TABLE H_UDC6 ON H_UDC6.HELPER_TABLE_SID = udcs.UDC6 LEFT JOIN COMPANY_MASTER PACM ON\n"
			+ "	PACM.COMPANY_MASTER_SID = CPD.COMPANY_PARENT_DETAILS_SID  LEFT JOIN HELPER_TABLE HT ON CM.ORGANIZATION_KEY = HT.HELPER_TABLE_SID";
	public static final String GTN_CGRP_SELECTED_TABLE_SEARCH_QUERY = " FROM DBO.IMTD_COMPANY_GROUP_DETAILS IMTD "
			+ "JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IMTD.COMPANY_MASTER_SID "
			+ "LEFT JOIN DBO.COMPANY_TRADE_CLASS CTC ON CTC.COMPANY_TRADE_CLASS_SID=IMTD.COMPANY_TRADECLASS_SID "
			+ "LEFT JOIN DBO.COMPANY_PARENT_DETAILS CPD ON CPD.COMPANY_PARENT_DETAILS_SID=IMTD.COMPANY_PARENT_DETAILS_SID  "
			+ "LEFT JOIN HELPER_TABLE HT_TC ON CTC.COMPANY_TRADE_CLASS = HT_TC.HELPER_TABLE_SID "
			+ "LEFT JOIN HELPER_TABLE HT_CT ON \n" + "CM.COMPANY_TYPE = HT_CT.HELPER_TABLE_SID \n"
			+ "LEFT JOIN HELPER_TABLE HT_CS ON \n" + "CM.COMPANY_STATUS = HT_CS.HELPER_TABLE_SID "
			+ "LEFT JOIN HELPER_TABLE HT_ST ON \n" + "CM.STATE = HT_ST.HELPER_TABLE_SID \n"
			+ "LEFT JOIN HELPER_TABLE HT_CN ON \n" + "CM.COUNTRY = HT_CN.HELPER_TABLE_SID "
			+ "LEFT JOIN DBO.UDCS udcs ON\n"
			+ "CM.COMPANY_MASTER_SID = udcs.MASTER_SID AND udcs.MASTER_TYPE='COMPANY_MASTER' \n"
			+ "LEFT JOIN HELPER_TABLE H_UDC1 ON \n" + "H_UDC1.HELPER_TABLE_SID = udcs.UDC1 \n"
			+ "LEFT JOIN HELPER_TABLE H_UDC2 ON \n" + "H_UDC2.HELPER_TABLE_SID = udcs.UDC2 "
			+ "LEFT JOIN COMPANY_MASTER PCM ON\n" + "	PCM.COMPANY_MASTER_SID = CPD.PRIOR_PARENT_CMPY_MASTER_SID"
			+ " LEFT JOIN COMPANY_MASTER PACM ON\n" + "	PACM.COMPANY_MASTER_SID = CPD.COMPANY_PARENT_DETAILS_SID"
                        + " LEFT JOIN HELPER_TABLE HT ON CM.ORGANIZATION_KEY = HT.HELPER_TABLE_SID";
	public static final String GTN_CGRP_SELECTED_TABLE_SELECTED_CLAUSE = " SELECT COUNT(DISTINCT IMTD.COMPANY_MASTER_SID) ";
	public static final String GTN_CGRP_SELECTED_TABLE_WHERE_CLAUSE = "  CM.INBOUND_STATUS <> 'D' ";

}
