/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.constants;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsCfpQueryContants {

	public static final String GTN_CFP_COMPANIES_ADDITION_SEARCH_QUERY = " FROM COMPANY_MASTER CM JOIN COMPANY_TRADE_CLASS CTC ON CTC.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID ";

	public static final String GTN_CFP_ADDITION_SEARCH_QUERY = " FROM COMPANY_MASTER CM JOIN COMPANY_TRADE_CLASS CTC ON CTC.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID ";

	public static final String GTN_CFP_ADDITION_SEARCH_QUERY_WHERE_CLAUSE = " CTC.COMPANY_TRADE_CLASS =(SELECT TOP 1(CT.COMPANY_TRADE_CLASS) FROM\n"
			+ "COMPANY_TRADE_CLASS CT  WHERE CT.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID AND CT.INBOUND_STATUS <> 'D'        ORDER BY CT.TRADE_CLASS_START_DATE DESC) \n"
			+ "  AND CM.INBOUND_STATUS <> 'D'   AND CTC.INBOUND_STATUS <> 'D' ";
	public static final String GTN_CFP_COUNT = " SELECT COUNT(DISTINCT CFP.CFP_MODEL_SID)  ";
	public static final String GTN_CFP_COMPANIES_COUNT_QUERY = " FROM CFP_MODEL CFP \n"
			+ "LEFT JOIN CFP_MODEL PARENCFP ON PARENCFP.CFP_MODEL_SID = CFP.PARENT_CFP_ID\n"
			+ "JOIN CFP_DETAILS CFD ON CFP.CFP_MODEL_SID=CFD.CFP_MODEL_SID \n"
			+ "JOIN COMPANY_MASTER CM ON CFD.COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID  "
                        + " LEFT JOIN HELPER_TABLE companyFamilyPlanTypeHelper on CFP.CFP_TYPE = companyFamilyPlanTypeHelper.HELPER_TABLE_SID"
                        + " LEFT JOIN HELPER_TABLE cfpStatusHelper on CFP.CFP_STATUS = cfpStatusHelper.HELPER_TABLE_SID"
                        + " LEFT JOIN HELPER_TABLE cfpCategoryHelper on CFP.CFP_CATEGORY = cfpCategoryHelper.HELPER_TABLE_SID";
        
	public static final String GTN_CFP_COMPANIES_SEARCH_QUERY = " FROM CFP_MODEL CFP LEFT JOIN CFP_MODEL PARENCFP ON PARENCFP.CFP_MODEL_SID = CFP.PARENT_CFP_ID\n"
			+ "JOIN CFP_DETAILS CFD ON CFP.CFP_MODEL_SID=CFD.CFP_MODEL_SID \n"
			+ "JOIN COMPANY_MASTER CM ON CFD.COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID"
                        + " LEFT JOIN HELPER_TABLE companyFamilyPlanTypeHelper on CFP.CFP_TYPE = companyFamilyPlanTypeHelper.HELPER_TABLE_SID "
                        + " LEFT JOIN HELPER_TABLE cfpStatusHelper on CFP.CFP_STATUS = cfpStatusHelper.HELPER_TABLE_SID"
                        + " LEFT JOIN HELPER_TABLE cfpCategoryHelper on CFP.CFP_CATEGORY = cfpCategoryHelper.HELPER_TABLE_SID";
        
	public static final String GTN_CFP_COMPANIES_SEARCH_QUERY_WHERE_CLAUSE = " CFP.INBOUND_STATUS <> 'D' ";
	public static final String GTN_CFP_COMPANIES_ADDITION_SEARCH_QUERY_WHERE_CLAUSE = " CTC.COMPANY_TRADE_CLASS =(SELECT TOP 1(CT.COMPANY_TRADE_CLASS) FROM\n"
			+ "COMPANY_TRADE_CLASS CT  WHERE CT.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID AND CT.INBOUND_STATUS <> 'D'        ORDER BY CT.TRADE_CLASS_START_DATE DESC) \n"
			+ "  AND CM.INBOUND_STATUS <> 'D'   AND CTC.INBOUND_STATUS <> 'D'";
	public static final String CFP_TEMP_TABLE_DELETE_QUERY = "DELETE FROM IMTD_CFP_DETAILS WHERE USERS_SID = ";

	public static final String CFP_DETAILS_ATTACHED_STATUS = "CFP_DETAILS_ATTACHED_STATUS";
	public static final String CFP_DETAILS_START_DATE = "CFP_DETAILS_START_DATE";
	public static final String CFP_DETAILS_END_DATE = "CFP_DETAILS_END_DATE";
	public static final String TRADING_PARTNER_CONTRACT_NO = "TRADING_PARTNER_CONTRACT_NO";
	public static final String CFP_DETAILS_MODIFIED_BY = "CFP_DETAILS_MODIFIED_BY";
	public static final String CHECK_RECORD = "CHECK_RECORD";

	private GtnWsCfpQueryContants() {
	}
}
