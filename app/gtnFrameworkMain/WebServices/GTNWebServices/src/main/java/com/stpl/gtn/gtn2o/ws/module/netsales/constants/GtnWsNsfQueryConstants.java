/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.netsales.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;

/**
 *
 * @author STPL
 */
public final class GtnWsNsfQueryConstants {
	public static final String FROM_CONTRACT_MASTER = " FROM CONTRACT_MASTER CM";
	public static final String GTN_NSF_AVAILABLE_CONTRACTS_SEARCH_QUERY = FROM_CONTRACT_MASTER + " \n"
			+ "JOIN HELPER_TABLE H ON CM.CONTRACT_TYPE = H.HELPER_TABLE_SID\n"
			+ "JOIN CFP_CONTRACT CFP ON CFP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "JOIN CFP_MODEL CFPM ON CFPM.CFP_MODEL_SID = CFP.CFP_MODEL_SID \n"
			+ "JOIN CFP_CONTRACT_DETAILS CFPD  ON CFPD.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "JOIN IFP_CONTRACT IFP ON IFP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "AND IFP.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "JOIN IFP_MODEL IFPM ON IFPM.IFP_MODEL_SID = IFP.IFP_MODEL_SID\n"
			+ "JOIN IFP_CONTRACT_DETAILS IFPD ON IFPD.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "JOIN PS_CONTRACT PS ON PS.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "AND PS.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n" + "AND PS.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "JOIN PS_MODEL PSM ON PSM.PS_MODEL_SID = PS.PS_MODEL_SID\n"
			+ "JOIN PS_CONTRACT_DETAILS PSD  ON PSD.PS_CONTRACT_SID = PS.PS_CONTRACT_SID\n"
			+ "AND PSD.ITEM_MASTER_SID = IFPD.ITEM_MASTER_SID\n"
			+ "JOIN RS_CONTRACT RS ON RS.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "AND RS.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n" + "AND RS.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "AND RS.PS_CONTRACT_SID = PS.PS_CONTRACT_SID\n"
			+ "JOIN RS_CONTRACT_DETAILS RSD ON RSD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID\n"
			+ "AND RSD.ITEM_MASTER_SID = PSD.ITEM_MASTER_SID\n"
			+ "JOIN COMPANY_MASTER COM ON COM.COMPANY_MASTER_SID = CFPD.COMPANY_MASTER_SID\n"
			+ "JOIN ITEM_MASTER IM  ON IM.ITEM_MASTER_SID = IFPD.ITEM_MASTER_SID\n"
			+ "LEFT JOIN COMPANY_MASTER CON_HOL  ON CM.CONT_HOLD_COMPANY_MASTER_SID = CON_HOL.COMPANY_MASTER_SID";

	public static final String GTN_NSF_AVAILABLE_CONTRACTS_SEARCH_QUERY_WHERE_CLAUSE = " IM.INBOUND_STATUS <> 'D'\n"
			+ "        AND CM.INBOUND_STATUS <> 'D'\n" + "        AND CFP.INBOUND_STATUS <> 'D'\n"
			+ "        AND CFPD.INBOUND_STATUS <> 'D'\n" + "        AND IFPD.INBOUND_STATUS <> 'D'\n"
			+ "        AND PS.INBOUND_STATUS <> 'D'\n" + "        AND PSD.INBOUND_STATUS <> 'D'\n"
			+ "        AND RS.INBOUND_STATUS <> 'D'\n" + "        AND RSD.INBOUND_STATUS <> 'D'\n"
			+ "        AND COM.INBOUND_STATUS <> 'D'\n" + "        AND CFPM.INBOUND_STATUS <> 'D'\n"
			+ "        AND IFPM.INBOUND_STATUS <> 'D'\n" + "        AND PSM.INBOUND_STATUS <> 'D'";

	public static final String GTN_NSF_AVAILABLE_CONTRACTS_COUNT_QUERY = FROM_CONTRACT_MASTER + " \n"
			+ "JOIN HELPER_TABLE H ON CM.CONTRACT_TYPE = H.HELPER_TABLE_SID\n"
			+ "JOIN CFP_CONTRACT CFP ON CFP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "JOIN CFP_MODEL CFPM ON CFPM.CFP_MODEL_SID = CFP.CFP_MODEL_SID \n"
			+ "JOIN CFP_CONTRACT_DETAILS CFPD  ON CFPD.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "               JOIN IFP_CONTRACT IFP ON IFP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "               AND IFP.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "               JOIN IFP_MODEL IFPM ON IFPM.IFP_MODEL_SID = IFP.IFP_MODEL_SID\n"
			+ "               JOIN IFP_CONTRACT_DETAILS IFPD ON IFPD.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "               JOIN PS_CONTRACT PS ON PS.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "               AND PS.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "               AND PS.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "               JOIN PS_MODEL PSM ON PSM.PS_MODEL_SID = PS.PS_MODEL_SID\n"
			+ "               JOIN PS_CONTRACT_DETAILS PSD  ON PSD.PS_CONTRACT_SID = PS.PS_CONTRACT_SID\n"
			+ "               AND PSD.ITEM_MASTER_SID = IFPD.ITEM_MASTER_SID\n"
			+ "               JOIN RS_CONTRACT RS ON RS.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "               AND RS.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "               AND RS.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "               AND RS.PS_CONTRACT_SID = PS.PS_CONTRACT_SID\n"
			+ "               JOIN RS_CONTRACT_DETAILS RSD ON RSD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID\n"
			+ "               AND RSD.ITEM_MASTER_SID = PSD.ITEM_MASTER_SID\n"
			+ "               JOIN COMPANY_MASTER COM ON COM.COMPANY_MASTER_SID = CFPD.COMPANY_MASTER_SID\n"
			+ "               JOIN ITEM_MASTER IM  ON IM.ITEM_MASTER_SID = IFPD.ITEM_MASTER_SID\n"
			+ "               LEFT JOIN COMPANY_MASTER CON_HOL  ON CM.CONT_HOLD_COMPANY_MASTER_SID = CON_HOL.COMPANY_MASTER_SID ";

	public static final String GTN_NSF_AVAILABLE_CONTRACTS_COUNT_QUERY_SELECT_CLAUSE = "SELECT Count(1) as result_count FROM (Select \n"
			+ "	distinct CM.CONTRACT_NO,\n" + "	CM.CONTRACT_NAME,\n" + "	CON_HOL.COMPANY_NAME as CONTRACT_HOLDER,\n"
			+ "	H.DESCRIPTION as CONTRACT_TYPE,\n" + "	CFPM.CFP_NO,\n" + "	CFP.CFP_NAME,\n" + "	IFPM.IFP_NO,\n"
			+ "	IFP.IFP_NAME,\n" + "	PSM.PS_NO,\n" + "	PS.PS_NAME,\n" + "	RS.RS_NO,\n" + "	RS.RS_NAME,\n"
			+ "	CM.CONTRACT_MASTER_SID";

	public static final String GTN_NSF_LANDING_SEARCH_QUERY_WHERE_CLAUSE = " NSFM.INBOUND_STATUS <> 'D'";
	public static final String GTN_NSF_LANDING_COUNT_QUERY = " FROM NET_SALES_FORMULA_MASTER NSFM \n"
			+ "JOIN HELPER_TABLE FORMULA_TYPE ON FORMULA_TYPE.HELPER_TABLE_SID=NSFM.NET_SALES_FORMULA_TYPE ";
	public static final String GTN_NSF_LANDING_SEARCH_QUERY = " FROM NET_SALES_FORMULA_MASTER NSFM\n"
			+ "JOIN HELPER_TABLE FORMULA_TYPE ON FORMULA_TYPE.HELPER_TABLE_SID=NSFM.NET_SALES_FORMULA_TYPE";
	public static final String GTN_NSF_LANDING_COUNT_SELECT_CLAUSE = " SELECT COUNT(DISTINCT NSFM.NET_SALES_FORMULA_MASTER_SID)  ";

	public static final String GTN_NSF_AVAILABLE_CUSTOMER_SEARCH_QUERY = " FROM   (SELECT ROW_NUMBER()\n"
			+ "                 OVER (\n"
			+ "                   PARTITION BY COMPANY_NAME, COMPANY_NO, CONTRACT_NO, CONTRACT_NAME, CFP_NO, CFP_NAME, CONTRACT_MASTER_SID, CFP_CONTRACT_DETAILS_SID\n"
			+ "                   ORDER BY CONTRACT_NO) RN,\n"
			+ "               COMPANY_NAME              AS CUSTOMER_NAME,\n"
			+ "               COMPANY_NO                AS CUSTOMER_NO,\n" + "               CONTRACT_NO,\n"
			+ "               CONTRACT_NAME,\n" + "               CFP_NO,\n" + "               CFP_NAME,\n"
			+ "               CONTRACT_MASTER_SID,\n" + "               CFP_CONTRACT_DETAILS_SID\n"
			+ "        FROM   (SELECT DISTINCT CM.CONTRACT_MASTER_SID,\n" + "                         CM.CONTRACT_NO,\n"
			+ "                         CM.CONTRACT_NAME,\n"
			+ "                         H.HELPER_TABLE_SID   CONTRACT_TYPE,\n"
			+ "                         CFP.CFP_NAME,\n" + "                         IFP.IFP_NAME,\n"
			+ "                         COM.COMPANY_NO,\n" + "                         COM.COMPANY_NAME,\n"
			+ "                         CON_HOL.COMPANY_NAME AS CONTRACT_HOLDER,\n"
			+ "                         IM.ITEM_NO,\n" + "                         IM.ITEM_NAME,\n"
			+ "                         PS.PS_NAME,\n" + "                         CFPM.CFP_NO,\n"
			+ "                         IFPM.IFP_NO,\n" + "                         PSM.PS_NO,\n"
			+ "                         RS.RS_NO,\n" + "                         RS.RS_NAME,\n"
			+ "                         CFPD.CFP_CONTRACT_DETAILS_SID\n" + "         FROM   CONTRACT_MASTER CM\n"
			+ "                JOIN HELPER_TABLE H\n" + "                  ON CM.CONTRACT_TYPE = H.HELPER_TABLE_SID\n"
			+ "                JOIN CFP_CONTRACT CFP\n"
			+ "                  ON CFP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                JOIN CFP_MODEL CFPM\n" + "                  ON CFPM.CFP_MODEL_SID = CFP.CFP_MODEL_SID\n"
			+ "                JOIN CFP_CONTRACT_DETAILS CFPD\n"
			+ "                  ON CFPD.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "                JOIN IFP_CONTRACT IFP\n"
			+ "                  ON IFP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                     AND IFP.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "                JOIN IFP_MODEL IFPM\n" + "                  ON IFPM.IFP_MODEL_SID = IFP.IFP_MODEL_SID\n"
			+ "                JOIN IFP_CONTRACT_DETAILS IFPD\n"
			+ "                  ON IFPD.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "                JOIN PS_CONTRACT PS\n"
			+ "                  ON PS.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                     AND PS.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "                     AND PS.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "                JOIN PS_MODEL PSM\n" + "                  ON PSM.PS_MODEL_SID = PS.PS_MODEL_SID\n"
			+ "                JOIN PS_CONTRACT_DETAILS PSD\n"
			+ "                  ON PSD.PS_CONTRACT_SID = PS.PS_CONTRACT_SID\n"
			+ "                     AND PSD.ITEM_MASTER_SID = IFPD.ITEM_MASTER_SID\n"
			+ "                JOIN RS_CONTRACT RS\n"
			+ "                  ON RS.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                     AND RS.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "                     AND RS.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "                     AND RS.PS_CONTRACT_SID = PS.PS_CONTRACT_SID\n"
			+ "                JOIN RS_CONTRACT_DETAILS RSD\n"
			+ "                  ON RSD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID\n"
			+ "                     AND RSD.ITEM_MASTER_SID = PSD.ITEM_MASTER_SID\n"
			+ "                JOIN COMPANY_MASTER COM\n"
			+ "                  ON COM.COMPANY_MASTER_SID = CFPD.COMPANY_MASTER_SID\n"
			+ "                JOIN ITEM_MASTER IM\n"
			+ "                  ON IM.ITEM_MASTER_SID = IFPD.ITEM_MASTER_SID\n"
			+ "                LEFT JOIN COMPANY_MASTER CON_HOL\n"
			+ "                       ON CM.CONT_HOLD_COMPANY_MASTER_SID = CON_HOL.COMPANY_MASTER_SID\n"
			+ "                          AND CON_HOL.COMPANY_NAME LIKE '%'\n"
			+ "                          AND CON_HOL.INBOUND_STATUS <> 'D'\n" + "         ) DISPLAY\n"
			+ "           )A ";

	public static final String GTN_NSF_AVAILABLE_CUSTOMER_COUNT_QUERY = "  FROM   (SELECT ROW_NUMBER()\n"
			+ "                 OVER (\n"
			+ "                   PARTITION BY COMPANY_NAME, COMPANY_NO, CONTRACT_NO, CONTRACT_NAME, CFP_NO, CFP_NAME, CONTRACT_MASTER_SID, CFP_CONTRACT_DETAILS_SID\n"
			+ "                   ORDER BY CONTRACT_NO) RN,\n"
			+ "               COMPANY_NAME              AS CUSTOMER_NAME,\n"
			+ "               COMPANY_NO                AS CUSTOMER_NO,\n" + "               CONTRACT_NO,\n"
			+ "               CONTRACT_NAME,\n" + "               CFP_NO,\n" + "               CFP_NAME,\n"
			+ "               CONTRACT_MASTER_SID,\n" + "               CFP_CONTRACT_DETAILS_SID\n"
			+ "        FROM   (SELECT DISTINCT CM.CONTRACT_MASTER_SID,\n" + "                         CM.CONTRACT_NO,\n"
			+ "                         CM.CONTRACT_NAME,\n"
			+ "                         H.HELPER_TABLE_SID   CONTRACT_TYPE,\n"
			+ "                         CFP.CFP_NAME,\n" + "                         IFP.IFP_NAME,\n"
			+ "                         COM.COMPANY_NO,\n" + "                         COM.COMPANY_NAME,\n"
			+ "                         CON_HOL.COMPANY_NAME AS CONTRACT_HOLDER,\n"
			+ "                         IM.ITEM_NO,\n" + "                         IM.ITEM_NAME,\n"
			+ "                         PS.PS_NAME,\n" + "                         CFPM.CFP_NO,\n"
			+ "                         IFPM.IFP_NO,\n" + "                         PSM.PS_NO,\n"
			+ "                         RS.RS_NO,\n" + "                         RS.RS_NAME,\n"
			+ "                         CFPD.CFP_CONTRACT_DETAILS_SID\n" + "         FROM   CONTRACT_MASTER CM\n"
			+ "                JOIN HELPER_TABLE H\n" + "                  ON CM.CONTRACT_TYPE = H.HELPER_TABLE_SID\n"
			+ "                JOIN CFP_CONTRACT CFP\n"
			+ "                  ON CFP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                JOIN CFP_MODEL CFPM\n" + "                  ON CFPM.CFP_MODEL_SID = CFP.CFP_MODEL_SID\n"
			+ "                JOIN CFP_CONTRACT_DETAILS CFPD\n"
			+ "                  ON CFPD.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "                JOIN IFP_CONTRACT IFP\n"
			+ "                  ON IFP.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                     AND IFP.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "                JOIN IFP_MODEL IFPM\n" + "                  ON IFPM.IFP_MODEL_SID = IFP.IFP_MODEL_SID\n"
			+ "                JOIN IFP_CONTRACT_DETAILS IFPD\n"
			+ "                  ON IFPD.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "                JOIN PS_CONTRACT PS\n"
			+ "                  ON PS.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                     AND PS.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "                     AND PS.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "                JOIN PS_MODEL PSM\n" + "                  ON PSM.PS_MODEL_SID = PS.PS_MODEL_SID\n"
			+ "                JOIN PS_CONTRACT_DETAILS PSD\n"
			+ "                  ON PSD.PS_CONTRACT_SID = PS.PS_CONTRACT_SID\n"
			+ "                     AND PSD.ITEM_MASTER_SID = IFPD.ITEM_MASTER_SID\n"
			+ "                JOIN RS_CONTRACT RS\n"
			+ "                  ON RS.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                     AND RS.CFP_CONTRACT_SID = CFP.CFP_CONTRACT_SID\n"
			+ "                     AND RS.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID\n"
			+ "                     AND RS.PS_CONTRACT_SID = PS.PS_CONTRACT_SID\n"
			+ "                JOIN RS_CONTRACT_DETAILS RSD\n"
			+ "                  ON RSD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID\n"
			+ "                     AND RSD.ITEM_MASTER_SID = PSD.ITEM_MASTER_SID\n"
			+ "                JOIN COMPANY_MASTER COM\n"
			+ "                  ON COM.COMPANY_MASTER_SID = CFPD.COMPANY_MASTER_SID\n"
			+ "                JOIN ITEM_MASTER IM\n"
			+ "                  ON IM.ITEM_MASTER_SID = IFPD.ITEM_MASTER_SID\n"
			+ "                LEFT JOIN COMPANY_MASTER CON_HOL\n"
			+ "                       ON CM.CONT_HOLD_COMPANY_MASTER_SID = CON_HOL.COMPANY_MASTER_SID\n"
			+ "                          AND CON_HOL.COMPANY_NAME LIKE '%'\n"
			+ "                          AND CON_HOL.INBOUND_STATUS <> 'D'\n" + "         ) DISPLAY\n"
			+ "           )A ";

	public static final String GTN_NSF_SELECTED_CUSTOMERS_SEARCH_QUERY = " FROM dbo.IMTD_SALES_BASIS_DETAILS IMSBD "
			+ " LEFT JOIN dbo.CDR_MODEL CDR ON CDR.CDR_MODEL_SID=IMSBD.CDR_MODEL_SID ";
	public static final String GTN_NSF_SELECTED_CUSTOMERS_COUNT_QUERY = " FROM dbo.IMTD_SALES_BASIS_DETAILS IMSBD "
			+ " LEFT JOIN dbo.CDR_MODEL CDR ON CDR.CDR_MODEL_SID=IMSBD.CDR_MODEL_SID ";
	public static final String GTN_NSF_AVAILABLE_DEDUCTION_COUNT_QUERY = " FROM  dbo.RS_MODEL RSM "
			+ " LEFT JOIN dbo.HELPER_TABLE RS_TYPE ON RSM.RS_TYPE=RS_TYPE.HELPER_TABLE_SID "
			+ " LEFT JOIN dbo.HELPER_TABLE REBATE_PROGRAM_TYPE ON RSM.REBATE_PROGRAM_TYPE=REBATE_PROGRAM_TYPE.HELPER_TABLE_SID "
			+ " LEFT JOIN dbo.HELPER_TABLE RS_CATEGORY ON RSM.RS_CATEGORY=RS_CATEGORY.HELPER_TABLE_SID " + "  ";
	public static final String GTN_NSF_AVAILABLE_DEDUCTION_SEARCH_QUERY = " FROM  dbo.RS_MODEL RSM "
			+ " LEFT JOIN dbo.HELPER_TABLE RS_TYPE ON RSM.RS_TYPE=RS_TYPE.HELPER_TABLE_SID "
			+ " LEFT JOIN dbo.HELPER_TABLE REBATE_PROGRAM_TYPE ON RSM.REBATE_PROGRAM_TYPE=REBATE_PROGRAM_TYPE.HELPER_TABLE_SID "
			+ " LEFT JOIN dbo.HELPER_TABLE RS_CATEGORY ON RSM.RS_CATEGORY=RS_CATEGORY.HELPER_TABLE_SID " + " ";
	public static final String GTN_NSF_AVAILABLE_DEDUCTION_COUNT_SELECT_CLAUSE = " Select count(distinct RS_TYPE.DESCRIPTION+REBATE_PROGRAM_TYPE.DESCRIPTION+RS_CATEGORY.DESCRIPTION)  ";

	public static final String GTN_NSF_SELECTED_DEDUCTION_COUNT_QUERY = " FROM   dbo.IMTD_DEDUCTION_DETAILS IMDD\n"
			+ "LEFT JOIN dbo.HELPER_TABLE RS_TYPE ON IMDD.DEDUCTION_TYPE=RS_TYPE.HELPER_TABLE_SID\n"
			+ "LEFT JOIN dbo.HELPER_TABLE REBATE_PROGRAM_TYPE ON IMDD.DEDUCTION_SUB_TYPE=REBATE_PROGRAM_TYPE.HELPER_TABLE_SID\n"
			+ "LEFT JOIN dbo.HELPER_TABLE RS_CATEGORY ON IMDD.DEDUCTION_CATEGORY=RS_CATEGORY.HELPER_TABLE_SID\n"
			+ "LEFT JOIN dbo.CDR_MODEL CDRM ON CDRM.CDR_MODEL_SID=IMDD.CDR_MODEL_SID ";
	public static final String GTN_NSF_SELECTED_DEDUCTION_SEARCH_QUERY = " FROM   dbo.IMTD_DEDUCTION_DETAILS IMDD\n"
			+ "LEFT JOIN dbo.HELPER_TABLE RS_TYPE ON IMDD.DEDUCTION_TYPE=RS_TYPE.HELPER_TABLE_SID\n"
			+ "LEFT JOIN dbo.HELPER_TABLE REBATE_PROGRAM_TYPE ON IMDD.DEDUCTION_SUB_TYPE=REBATE_PROGRAM_TYPE.HELPER_TABLE_SID\n"
			+ "LEFT JOIN dbo.HELPER_TABLE RS_CATEGORY ON IMDD.DEDUCTION_CATEGORY=RS_CATEGORY.HELPER_TABLE_SID\n"
			+ "LEFT JOIN dbo.CDR_MODEL CDRM ON CDRM.CDR_MODEL_SID=IMDD.CDR_MODEL_SID";
	public static final String GTN_NSF_FORMULA_TYPE_CONTRACT_AVAILABLE_DEDUCTION_COUNT_QUERY = " FROM   CONTRACT_MASTER CM\n"
			+ "							JOIN HELPER_TABLE H ON CM.CONTRACT_TYPE = H.HELPER_TABLE_SID\n"
			+ "                         LEFT JOIN HELPER_TABLE conTypeHelper on conTypeHelper.HELPER_TABLE_SID=CM.CONTRACT_TYPE\n"
			+ "                         LEFT JOIN CFP_CONTRACT CFC\n"
			+ "                                ON CFC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                                   AND CFC.INBOUND_STATUS <> 'D'\n"
			+ "                         LEFT JOIN CFP_MODEL CFPM\n"
			+ "                                ON CFPM.CFP_MODEL_SID = CFC.CFP_MODEL_SID\n"
			+ "                         LEFT JOIN COMPANY_MASTER COMP\n"
			+ "                                ON CM.CONT_HOLD_COMPANY_MASTER_SID = COMP.COMPANY_MASTER_SID\n"
			+ "                                   AND COMP.COMPANY_NAME LIKE '%'\n"
			+ "                         LEFT JOIN IFP_CONTRACT IFC\n"
			+ "                                ON IFC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                                   AND IFC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
			+ "                                   AND IFC.INBOUND_STATUS <> 'D'\n"
			+ "                         LEFT JOIN IFP_MODEL IFPM\n"
			+ "                                ON IFPM.IFP_MODEL_SID = IFC.IFP_MODEL_SID\n"
			+ "                         LEFT JOIN PS_CONTRACT PSC\n"
			+ "                                ON PSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                                   AND PSC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
			+ "                                   AND PSC.IFP_CONTRACT_SID = iFC.IFP_CONTRACT_SID\n"
			+ "                                   AND PSC.INBOUND_STATUS <> 'D'\n"
			+ "                              JOIN PS_MODEL PS \n"
			+ "                                ON ps.PS_MODEL_SID=PSC.PS_MODEL_SID\n"
			+ "                         LEFT JOIN RS_CONTRACT RSC\n"
			+ "                                ON RSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                                   AND RSC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
			+ "                                   AND RSC.IFP_CONTRACT_SID = iFC.IFP_CONTRACT_SID\n"
			+ "                                   AND RSC.PS_CONTRACT_SID = PSC.PS_CONTRACT_SID\n"
			+ "                                   AND RSC.INBOUND_STATUS <> 'D' "
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_RS_TYPE_ON_RSCR
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_REBATE_PROGRAM
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_RS_CATEGORY_ON;
	public static final String GTN_NSF_FORMULATYPE_CONTRACT_AVAILABLE_DEDUCTION_SEARCH_QUERY = " FROM   CONTRACT_MASTER CM\n"
			+ "							JOIN HELPER_TABLE H ON CM.CONTRACT_TYPE = H.HELPER_TABLE_SID\n"
			+ "                         LEFT JOIN HELPER_TABLE conTypeHelper on conTypeHelper.HELPER_TABLE_SID=CM.CONTRACT_TYPE\n"
			+ "                         LEFT JOIN CFP_CONTRACT CFC\n"
			+ "                                ON CFC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                                   AND CFC.INBOUND_STATUS <> 'D'\n"
			+ "                         LEFT JOIN CFP_MODEL CFPM\n"
			+ "                                ON CFPM.CFP_MODEL_SID = CFC.CFP_MODEL_SID\n"
			+ "                         LEFT JOIN COMPANY_MASTER COMP\n"
			+ "                                ON CM.CONT_HOLD_COMPANY_MASTER_SID = COMP.COMPANY_MASTER_SID\n"
			+ "                                   AND COMP.COMPANY_NAME LIKE '%'\n"
			+ "                         LEFT JOIN IFP_CONTRACT IFC\n"
			+ "                                ON IFC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                                   AND IFC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
			+ "                                   AND IFC.INBOUND_STATUS <> 'D'\n"
			+ "                         LEFT JOIN IFP_MODEL IFPM\n"
			+ "                                ON IFPM.IFP_MODEL_SID = IFC.IFP_MODEL_SID\n"
			+ "                         LEFT JOIN PS_CONTRACT PSC\n"
			+ "                                ON PSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                                   AND PSC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
			+ "                                   AND PSC.IFP_CONTRACT_SID = iFC.IFP_CONTRACT_SID\n"
			+ "                                   AND PSC.INBOUND_STATUS <> 'D'\n"
			+ "                              JOIN PS_MODEL PS \n"
			+ "                                ON ps.PS_MODEL_SID=PSC.PS_MODEL_SID\n"
			+ "                         LEFT JOIN RS_CONTRACT RSC\n"
			+ "                                ON RSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n"
			+ "                                   AND RSC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID\n"
			+ "                                   AND RSC.IFP_CONTRACT_SID = iFC.IFP_CONTRACT_SID\n"
			+ "                                   AND RSC.PS_CONTRACT_SID = PSC.PS_CONTRACT_SID\n"
			+ "                                   AND RSC.INBOUND_STATUS <> 'D'\n"
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_RS_TYPE_ON_RSCR
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_REBATE_PROGRAM
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_RS_CATEGORY_ON;
	public static final String QUERY_MAPPING_DEDUCTION_CATEGORY_COMP_ID = "netSalesFormulaAddView_deductionsTabDeductionCategory";

	public static final String GTN_NSF_FORMULATYPE_CONTRACT_SELECTED_DEDUCTION_COUNT_QUERY = " FROM IMTD_DEDUCTION_DETAILS IMD\n"
			+ "                            JOIN RS_CONTRACT RSC on RSC.RS_CONTRACT_SID=IMD.RS_CONTRACT_SID \n"
			+ "                            JOIN CONTRACT_MASTER CM on CM.CONTRACT_MASTER_SID=RSC.CONTRACT_MASTER_SID\n"
			+ "                            JOIN HELPER_TABLE H ON CM.CONTRACT_TYPE = H.HELPER_TABLE_SID\n"
			+ "                            LEFT JOIN CDR_MODEL NS ON NS.CDR_MODEL_SID=IMD.CDR_MODEL_SID \n"
			+ "                            LEFT JOIN DBO.COMPANY_MASTER COMP ON CM.CONT_HOLD_COMPANY_MASTER_SID = COMP.COMPANY_MASTER_SID\n"
			+ "                            LEFT JOIN HELPER_TABLE conTypeHelper on conTypeHelper.HELPER_TABLE_SID=CM.CONTRACT_TYPE\n"
			+ "                            LEFT JOIN CFP_CONTRACT CFPC on CFPC.CFP_CONTRACT_SID=RSC.CFP_CONTRACT_SID\n"
			+ "                            JOIN CFP_MODEL CFPM ON CFPM.CFP_MODEL_SID = CFPC.CFP_MODEL_SID\n"
			+ "                            LEFT JOIN IFP_CONTRACT IFPC on IFPC.IFP_CONTRACT_SID=RSC.IFP_CONTRACT_SID\n"
			+ "                            JOIN IFP_MODEL IFPM ON IFPM.IFP_MODEL_SID = IFPC.IFP_MODEL_SID\n"
			+ "                            LEFT JOIN PS_CONTRACT PSC on PSC.PS_CONTRACT_SID=RSC.RS_CONTRACT_SID\n"
			+ "                            LEFT JOIN PS_MODEL PS ON PS.PS_MODEL_SID=PSC.PS_MODEL_SID "
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_RS_TYPE_ON_RSCR
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_REBATE_PROGRAM
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_RS_CATEGORY_ON;

	public static final String GTN_NSF_FORMULATYPE_CONTRACT_SELECTED_DEDUCTION_SEARCH_QUERY = " FROM IMTD_DEDUCTION_DETAILS IMD \n"
			+ "                            JOIN RS_CONTRACT RSC on RSC.RS_CONTRACT_SID=IMD.RS_CONTRACT_SID \n"
			+ "                            JOIN CONTRACT_MASTER CM on CM.CONTRACT_MASTER_SID=RSC.CONTRACT_MASTER_SID\n"
			+ "							   JOIN HELPER_TABLE H ON CM.CONTRACT_TYPE = H.HELPER_TABLE_SID\n"
			+ "                            LEFT JOIN CDR_MODEL NS ON NS.CDR_MODEL_SID=IMD.CDR_MODEL_SID \n"
			+ "                            LEFT JOIN DBO.COMPANY_MASTER COMP ON CM.CONT_HOLD_COMPANY_MASTER_SID = COMP.COMPANY_MASTER_SID\n"
			+ "                            LEFT JOIN HELPER_TABLE conTypeHelper on conTypeHelper.HELPER_TABLE_SID=CM.CONTRACT_TYPE\n"
			+ "                            LEFT JOIN CFP_CONTRACT CFPC on CFPC.CFP_CONTRACT_SID=RSC.CFP_CONTRACT_SID\n"
			+ "                            JOIN CFP_MODEL CFPM ON CFPM.CFP_MODEL_SID = CFPC.CFP_MODEL_SID\n"
			+ "                            LEFT JOIN IFP_CONTRACT IFPC on IFPC.IFP_CONTRACT_SID=RSC.IFP_CONTRACT_SID\n"
			+ "                            JOIN IFP_MODEL IFPM ON IFPM.IFP_MODEL_SID = IFPC.IFP_MODEL_SID\n"
			+ "                            LEFT JOIN PS_CONTRACT PSC on PSC.PS_CONTRACT_SID=RSC.RS_CONTRACT_SID\n"
			+ "                            LEFT JOIN PS_MODEL PS ON PS.PS_MODEL_SID=PSC.PS_MODEL_SID "
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_RS_TYPE_ON_RSCR
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_REBATE_PROGRAM
			+ GtnFrameworkWebserviceConstant.LEFT_JOIN_DBO_HELPER_TABLE_RS_CATEGORY_ON;

	private GtnWsNsfQueryConstants() {
		super();
	}
}
