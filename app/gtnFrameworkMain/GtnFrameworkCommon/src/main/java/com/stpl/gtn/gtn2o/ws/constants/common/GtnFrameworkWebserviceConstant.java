/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.constants.common;

/**
 *
 * @author saranya.sekar
 */
public class GtnFrameworkWebserviceConstant {
	private GtnFrameworkWebserviceConstant() {
		/**
		 * empty constructor
		 */
	}

	/*
	 * GTNFrameWorkQueryEngine
	 */

	public static final String START_POSITION = "Start Position: ";
	public static final String END_POSITION = "End Position: ";
	public static final String ERROR_IN_EXECUTING_QUERY = "Error in executing query : ";
	public static final String ERROR_WHILE_GETTING_DATA = "Error while getting data.";
	public static final String EXECUTING_QUERY = "Executing query : ";
	public static final String ERROR_IN_EXECUTING_CRITERIA_QUERY = "Error in executing Criteria query : ";

	/*
	 * Common Constant
	 */
	public static final String AND = "' AND '";
	public static final String USER_ID_NAME = "userIdName";
	public static final String HELPER = "Helper";
	public static final String STRING = "String";
	public static final String AND_COLUMN = " AND ";
	public static final String INTEGER = "Integer";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String DATE = "Date";
	public static final String DOUBLE= "java.lang.Double";
	public static final String JAVA_LANG_INTEGER = "java.lang.Integer";


	/*
	 * Webservice Constant
	 */
	public static final String ENTER_GTN_UI_FRAMEWORK_WEBSERVICE_COMBO_BOX_RES = "Enter GtnUIFrameworkWebserviceComboBoxResponse";
	public static final String EXCEPTION_IN_GET_COMBO_BOX_RESULT_SET = "Exception in getComboBoxResultSet ";
	public static final String EXIT_GTN_UI_FRAMEWORK_WEBSERVICE_COMBO_BOX_RESP = "Exit GtnUIFrameworkWebserviceComboBoxResponse";
	public static final String EEE_MMM_DD_KKMMSS_Z_YYYY = "EEE MMM dd kk:mm:ss z yyyy";
	public static final String AND_ISNULL = " AND ISNULL(";
	public static final String AND_CAST_ISNULL = " AND CAST(ISNULL(";
	public static final String CAST = " CAST(";
	public static final String GREATER = "GREATER";

	public static final String COMPANY_ID = "COMPANY_ID";
	public static final String COMPANY_NO = "COMPANY_NO";
	public static final String CFP_NO = "CFP_NO";
	public static final String ENTER_COMPANIES_RESULT_TABLE_DATA = "Enter companiesResultTableData";
	public static final String EXCEPTION_WHILE_EXCUTING_COMPANIES_RESULT_T = "Exception while Excuting companiesResultTableData Query";
	public static final String EXCEPTION_WHILE_EXCUTCOMPANY_NAMEING_COMPANY_ADDITION_M = "Exception while Excuting companyAdditionMoveLeft Query";

	public static final String COMPANY_NAME = "COMPANY_NAME";
	public static final String ROWS = " Rows";
	public static final String TRADE = "trade";
	public static final String PARENT = "parent";
	public static final String CURRENT_PARAM_POSITION = "Current Param Position:";
	public static final String EXCEPTION_IN_EXECUTIG_QUERY = "Exception in executig query-";
	public static final String ON_FORMULA_TYPEHELPER_TABLE_SID = "        ON FORMULA_TYPE.HELPER_TABLE_SID = FF.FORMULA_TYPE ";
	public static final String LEFT_JOIN_HELPER_TABLE_FORMULA_TYP = "       LEFT JOIN HELPER_TABLE FORMULA_TYPE\n";
	public static final String HELPER_TABLE_SID = "HELPER_TABLE_SID";
	public static final String CASTFLOORCAST = "cast(floor(cast(";
	public static final String POPULATE_ITEMS_VALUE = "populateItemsValue";
	public static final String IMTDITEM_MASTER_SID = "IMTD.ITEM_MASTER_SID";
	public static final String HT_DESCRIPTION = "ht.DESCRIPTION";
	public static final String HT = "HT";
	public static final String WHERE = " WHERE ";
	public static final String SYS_ID = "sysId";
	public static final String USRFIRST_NAME_USRMIDDLE_NAME_USRLAS = "usr.firstName + ' ' + usr.middleName + ' ' + usr.lastName";
	public static final String USR1FIRST_NAME_USR1MIDDLE_NAME_USR1 = "usr1.firstName + ' ' + usr1.middleName + ' ' + usr1.lastName";
	public static final String HD_DESCRIPTION = "hd.DESCRIPTION";
	public static final String HCT_DESCRIPTION = "hct.DESCRIPTION";
	public static final String HST_DESCRIPTION = "hst.DESCRIPTION";
	public static final String RECORD_LOCK_STATUS = "recordLockStatus";
	public static final String CFPCO_CFP_CONTRACT_SID = "= cfpco.CFP_CONTRACT_SID";
	public static final String IS_NULL = " IS NULL";
	public static final String CONTRACT_MASTER = "contractMaster";
	public static final String PSCO_PS_CONTRACT_SID = "= psco.PS_CONTRACT_SID";
	public static final String IFPCO_IFP_CONTRACT_SID = "= ifpco.IFP_CONTRACT_SID";
	public static final String EXCEPTION_IN_GET_CONTRACT_DASHBOARD_REBUILD_T = "Exception in getContractDashboardRebuildTreeData";
	public static final String IMTD_ITEM_PRICE_REBATE_SID = "imtdItemPriceRebateSid";
	public static final String USERS_SID = "usersSid";
	public static final String EXCEPTION_IN_CHECK_IFP_TABLE_NULL_VALUE = "Exception in checkIfpTableNullValue ";
	public static final String CHECK_RECORD = "checkRecord";
	public static final String IMTD_CFP_DETAILS_SID = "imtdCfpDetailsSid";
	public static final String OPERATION = "operation";
	public static final String LEFT_JOIN_UDCS_UDCS_ON_UDCSMASTER_SIDIMIT = "LEFT join UDCS UDCS ON UDCS.MASTER_SID=IM.ITEM_MASTER_SID AND UDCS.MASTER_TYPE='ITEM_MASTER'  \n";
	public static final String ORGANIZATION_KEY = "ORGANIZATION_KEY";
	public static final String COUNTRY = "COUNTRY";
	public static final String ORGANIZATION_KEY_COLUMN = "organizationKey";
	public static final String C_UD_C1 = "C_UDC1";
	public static final String CUSTOMER_NAME = "customerName";
	public static final String MANUFACTURER_ID = "MANUFACTURER_ID";
	public static final String C_UD_C2 = "C_UDC2";
	public static final String C_UD_C3 = "C_UDC3";
	public static final String DISPLAY_BRAND = "DISPLAY_BRAND";
	public static final String C_UD_C4 = "C_UDC4";
	public static final String C_UD_C5 = "C_UDC5";
	public static final String C_UD_C6 = "C_UDC6";
	public static final String LEFT_JOIN_COMPANY_MASTER_CM_ON_CMCOMPANY = "LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.MANUFACTURER_ID \n";
	public static final String BIG_DECIMAL = "BigDecimal";
	public static final String CUSTOMER_NO = "customerNo";
	public static final String CUSTOMER_ID = "customerId";
	public static final String EXIT = "Exit ";
	public static final String ERROR_IN = "Error in ";
	public static final String RECORD_LOCK_STATUS_COLUMN = "RECORD_LOCK_STATUS";
	public static final String A_BOOLEAN = "Boolean";
	public static final String EXIT_DELETE_ITEM_MASTER = "Exit deleteItemMaster";
	public static final String EXCEPTION_WHILE_EXCUTING_DELETE_ITEM_MASTER = "Exception while Excuting deleteItemMaster Query";
	public static final String ENTER_DELETE_ITEM_MASTER = "Enter deleteItemMaster";
	public static final String ITEM_MASTER_SID = "itemMasterSid";
    public static final String ITEM_MASTER_SID_COLUMN = "ITEM_MASTER_SID";
	public static final String EXCEPTION_IN_CHECK_ITEM_IDENTIFIER_QUALFIER_V = "Exception in checkItemIdentifierQualfierValueExist ";
	public static final String BMBRAND_NAME = "BM.BRAND_NAME";
	public static final String STADESCRIPTION = "STA.DESCRIPTION";
	public static final String STRDESCRIPTION = "STR.DESCRIPTION";
	public static final String TCDESCRIPTION = "TC.DESCRIPTION";
	public static final String FODESCRIPTION = "FO.DESCRIPTION";
	public static final String IMINBOUND_STATUS_D = "  IM.INBOUND_STATUS <> 'D' ";
	public static final String ERROR_WHILE_EXECUTING_QUERY = "Error while executing query ";
	public static final String CMCONTRACT_NAME = "CM.CONTRACT_NAME";
	public static final String RS_TYPE = "RS_TYPE";
	public static final String DEDUCTION_CATEGORY_SYS_ID = "deductionCategorySysId";
	public static final String REBATE_PROGRAM_TYPE = "REBATE_PROGRAM_TYPE";
	public static final String REBATE_PROGRAM_TYPE_DESC = "REBATE_PROGRAM_TYPE_DESC";
	public static final String RS_CATEGORY_DESC = "RS_CATEGORY_DESC";
	public static final String CHECK_RECORD_COLUMN = "CHECK_RECORD";
	public static final String CONTRACT_TYPE = "CONTRACT_TYPE";
	public static final String FORMULA_TYPE = "FORMULA_TYPE";
	public static final String LEFT_JOIN_DBO_HELPER_TABLE_RS_TYPE_ON_RSCR = "LEFT JOIN dbo.HELPER_TABLE RS_TYPE ON RSC.RS_TYPE=RS_TYPE.HELPER_TABLE_SID\n";
	public static final String LEFT_JOIN_DBO_HELPER_TABLE_REBATE_PROGRAM = "LEFT JOIN dbo.HELPER_TABLE REBATE_PROGRAM_TYPE ON RSC.REBATE_PROGRAM_TYPE=REBATE_PROGRAM_TYPE.HELPER_TABLE_SID\n";
	public static final String CONTRACT_NAME = "CONTRACT_NAME";
	public static final String RS_CATEGORY = "RS_CATEGORY";
	public static final String DEDUCTION_SUB_TYPE = "deductionSubType";
	public static final String RULE_SID = "ruleSid";
	public static final String CONTRACT_MASTER_SID = "CONTRACT_MASTER_SID";
	public static final String CDR_MODEL_SID = "CDR_MODEL_SID";
	public static final String CONTRACT_NO = "CONTRACT_NO";
	public static final String SESSION_ID = "SESSION_ID";
	public static final String CFP_NAME = "CFP_NAME";
	public static final String USER_ID = "userId";
	public static final String RS_NO = "RS_NO";
	public static final String IFP_NO = "IFP_NO";
	public static final String PS_NO = "PS_NO";
	public static final String IMSBD = "IMSBD";
	public static final String DEDUCTION_TYPE_SYS_ID = "deductionTypeSysId";
	public static final String RS_TYPE_DESC = "RS_TYPE_DESC";
	public static final String REBATE_PROGRAM_TYPE_DES = "REBATE_PROGRAM_TYPE_DES";
	public static final String LEFT_JOIN_DBO_HELPER_TABLE_RS_CATEGORY_ON = "LEFT JOIN dbo.HELPER_TABLE RS_CATEGORY ON RSC.RS_CATEGORY=RS_CATEGORY.HELPER_TABLE_SID\n";
	public static final String PS_NAME = "PS_NAME";
	public static final String CONTRACT_HOLDER = "contractHolder";
	public static final String RULE_NAME = "RULE_NAME";
	public static final String CONTRACT_NO_PROPERTYID = "contractNo";
	public static final String CONTRACT_NAME_PROPERTYID = "contractName";
	public static final String USERS_SID_COLUMN = "USERS_SID";
	public static final String RULE_NO = "RULE_NO";
	public static final String INDICATOR = "indicator";
	public static final String DEDUCTION_SUB_TYPE_SYS_ID = "deductionSubTypeSysId";
	public static final String DEDUCTION_CATEGORY = "deductionCategory";
	public static final String CREATED_BY_FILTER = "createdByFilter";
	public static final String COMSTPLGTNGTN2OWSENTITYTRANSACTION = "com.stpl.gtn.gtn2o.ws.entity.transaction.";
	public static final String ACTIVE = "isActive";
	public static final String GL_BALANCE = "GlBalance";
	public static final String FROM_WORKFLOW_MASTER_WM_JOIN_DBO_HELPER_T = " FROM WORKFLOW_MASTER WM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID JOIN PROJECTION_MASTER PM on WM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID JOIN COMPANY_MASTER GL_COMp on GL_COMp.COMPANY_MASTER_SID=PM.COMPANY_MASTER_SID JOIN COMPANY_MASTER BU_COMp on BU_COMp.COMPANY_MASTER_SID=PM.BUSINESS_UNIT ";
	public static final String IFP_NAME = "IFP_NAME";
	public static final String RS_CATEGORY_DES = "RS_CATEGORY_DES";
	public static final String MARKET_TYPE = "marketType";
	public static final String DEDUCTION_TYPE = "deductionType";
	public static final String RS_NAME = "RS_NAME";
	public static final String PS_NET_B_SUBSEQ_PERIOD_PRICE = "psNetBSubseqPeriodPrice";
	public static final String PS_NET_RESET_PRICE_FORMULA_NAME = "psNetResetPriceFormulaName";
	public static final String PS_NET_RESET_PRICE_FORMULA_ID = "psNetResetPriceFormulaId";
	public static final String NET_RESET_PRICE_FORMULA_ID = "NET_RESET_PRICE_FORMULA_ID"; 
        public static final String NET_PRICE_TYPE_FORMULA = "NET_PRICE_TYPE_FORMULA";
	public static final String PS_NET_RESET_PRICE_FORMULA_ID_ONE = "psNetResetPriceFormulaId1";
	public static final String PS_NET_SUBSEQUENT_PRICE_FORMULA_ID_ONE = "psNetBSubseqPriceFormulaId1";
	public static final String PS_NET_BASE_PRICE_FORMULA_ID_ONE = "psNetBasePriceFormulaId1";
	public static final String PS_NEP_FORMULA_ONE = "psNEPFormula1";
	public static final String PS_NET_PRICE_TYPE_FORMULA_ONE = "psNetPriceTypeFormula1";
	public static final String PS_NET_PRICE_TYPE = "psNetPriceType";
	public static final String PS_NET_RESET_PRICE_TYPE = "psNetResetPriceType";
	public static final String PS_RESET_PRICE_TYPE = "psResetPriceType";
	public static final String PS_RESET_FREQUENCY = "psResetFrequency";
	public static final String PS_RESET_INTERVAL = "psResetInterval";
	public static final String PS_RESET_DATE = "psResetDate";
	public static final String PS_RESET_TYPE = "psResetType";
	public static final String RESET_DATE = "RESET_DATE";
	public static final String PS_RESET_ELIGIBLE = "psResetEligible";
	public static final String PS_MAX_INCREMENTAL_CHANGE = "psMaxIncrementalChange";
	public static final String MAX_INCREMENTAL_CHANGE = "MAX_INCREMENTAL_CHANGE";
	public static final String PS_DETAILS_PRICE_TOL = "psDetailsPriceTol";
	public static final String PS_TOLERANCE_TYPE = "psToleranceType";
	public static final String PS_TOLERANCE_FREQ = "psToleranceFreq";
	public static final String PS_TOLERANCE_INTERVAL = "psToleranceInterval";
	public static final String PS_SUBSEQ_PERIOD_PRICE_TYPE = "psSubseqPeriodPriceType";
	public static final String PS_NET_B_SUBSEQ_PRICE_FORMULA_ID_COLUMN = "NET_SUBSEQUENT_PRICE_FORMULA_ID";
	public static final String PS_NET_B_SUBSEQ_PRICE_FORMULA_ID = "psNetBSubseqPriceFormulaId";
	public static final String PS_NET_B_RESET_PRICE_FORMULA_ID = "NET_RESET_PRICE_FORMULA_ID";
	public static final String PS_NET_BASE_PRICE_FORMULA_ID = "psNetBasePriceFormulaId";
	public static final String PS_NET_BASE_PRICE_FORMULA_ID_COLUMN = "NET_BASE_PRICE_FORMULA_ID";
        public static final String PS_NEP_FORMULA_ID_COLUMN = "NEP_FORMULA";
        public static final String PS_NET_PRICE_TYPE_FORMULA_ID_COLUMN = "NET_PRICE_TYPE_FORMULA";
	public static final String PS_NET_BASE_PRICE = "psNetBasePrice";
	public static final String PS_BASE_PRICE_ENTRY = "psBasePriceEntry";
	public static final String PS_BASE_PRICE_TYPE = "psBasePriceType";
	public static final String PS_NEP_FORMULA = "psNEPFormula";
	public static final String PS_NEP = "psNEP";
	public static final String PS_PP_PRICE_TYPE = "psPPPriceType";
	public static final String PS_PP_END_DATE = "psPPEndDate";
	public static final String PS_PP_START_DATE = "psPPStartDate";
	public static final String PS_PP_STATUS = "psPPStatus";
	public static final String IMTD_PS_DETAILS_SID = "IMTD_PS_DETAILS_SID";
	public static final String PS_ATTACHED_DATE = "psAttachedDate";
	public static final String PS_DETAILS_ATTACHED_DATE = "PS_DETAILS_ATTACHED_DATE";
	public static final String BRAND_NAME = "brandName";
	public static final String IMPSD = "IMPSD";
	public static final String IFP_STATUS = "IFP_STATUS";
	public static final String PS_MODEL_SID = "PS_MODEL_SID";
	public static final String ITEM_ID_COLUMN = "ITEM_ID";
	public static final String IFP_TYPE = "IFP_TYPE";
	public static final String IFP_CATEGORY = "IFP_CATEGORY";
	public static final String UPDATE_IMTD_PS_DETAILS_SET = " UPDATE IMTD_PS_DETAILS  SET ";
	public static final String YYYY_M_MDD_H_HMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String SQL_GET_DATE = "GetDate(),";
	public static final String AND_SESSION_ID = "' AND SESSION_ID='";
	public static final String GROUP = "Group";
	public static final String TRIGGER = "trigger";
	public static final String RS_STATUS = "RS_STATUS";
	public static final String REBATE_PLAN_NAME = "REBATE_PLAN_NAME";
	public static final String FORMULA_NAME = "formulaName";
	public static final String CALCULATION_RULE = "CALCULATION_RULE";
	public static final String RS_DETAILS_REBATE_PLAN_NAME = "RS_DETAILS_REBATE_PLAN_NAME";
	public static final String DEDUCTION_CALENDAR = "DEDUCTION_CALENDAR";
	public static final String RULE_NAME_PROPERTY = "ruleName";
	public static final String EVALUATION_RULE = "EVALUATION_RULE";
	public static final String IMTD_RSD = "IMTD_RSD";
	public static final String AND_SESSION_ID_COLUMN = "' and SESSION_ID='";
	public static final String RBSTART_DATE = "RB.START_DATE";
	public static final String RBCREATED_DATE = "RB.CREATED_DATE";
	public static final String GREATER_VAR = " >= '";
	public static final String LESSER = " <= '";
	public static final String JOIN_CCP_DETAILS_CCPD_ON_CCPDCOMPANY_MAS = " JOIN CCP_DETAILS CCPD ON CCPD.COMPANY_MASTER_SID = TP.COMPANY_MASTER_SID";
	public static final String JOIN_CONTRACT_MASTER_CM_ON_CMCONTRACT_MA = " JOIN CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID";
	public static final String JOIN_COMPANY_MASTER_CH_ON_CHCOMPANY_MAST = " JOIN COMPANY_MASTER CH ON CH.COMPANY_MASTER_SID = CM.CONT_HOLD_COMPANY_MASTER_SID";
	public static final String FROM = " FROM ";
	public static final String AND2 = " and ";
	public static final String IS_NOT_NULL = " IS NOT NULL ";
	public static final String EXCLUSION = "Exclusion";
	public static final String JOIN = " JOIN ";
	public static final String USER_DEFINED = "User Defined";
	public static final String HIST = "HIST_";
	public static final String PRODUCT_GROUP_FILTER = "productGroupFilter";
	public static final String BUSINESS_UNIT_FILTER = "businessUnitFilter";
	public static final String MODIFIED_BY_FILTER = "modifiedByFilter";
	public static final String FROM_TIME_PERIOD_FILTER = "fromTimePeriodFilter";
	public static final String PRODUCT_LEVEL_FILTER = "productLevelFilter";
	public static final String VIEW_NAME_FILTER = "viewNameFilter";
	public static final String VIEW_DESCRIPTION_FILTER = "viewDescriptionFilter";
	public static final String TO_TIME_PERIOD_FILTER = "toTimePeriodFilter";
	public static final String COMPANY_FILTER = "companyFilter";
	public static final String PRODUCT_HIERARCHY_FILTER = "productHierarchyFilter";
	public static final String CREATED_DATE_FILTER = "createdDateFilter";
	public static final String IGITEM_GROUP_NAME = "IG.ITEM_GROUP_NAME";
	public static final String BUSINESSUNITID = "BUSINESSUNITID";
	public static final String BUSINESSUNITNO = "BUSINESSUNITNO";
	public static final String BUSINESSUNITNAME = "BUSINESSUNITNAME";
	public static final String ITEM_NO = "ITEM_NO";
	public static final String CONTRACT_ID = "CONTRACT_ID";
	public static final String DEDUCTION_VALUE = "DEDUCTION_VALUE";
	public static final String DEDUCTION_LEVEL = "DEDUCTION_LEVEL";
	public static final String ITEM_NAME = "ITEM_NAME";
	public static final String SELECT_COUNT = " SELECT count (*)  ";
	public static final String FROM_WORKFLOW_INBOX_WM = " FROM WORKFLOW_INBOX WM";
	public static final String FROM_WORKFLOW_INBOX_WM_QUERY = "FROM WORKFLOW_INBOX WM";
	public static final String NEP_FORMULA = "NEP_FORMULA";
	public static final String BASE_PRICE_ENTRY = "BASE_PRICE_ENTRY";
	public static final String BRAND_NAME_COLUMN = "BRAND_NAME";
	public static final String NET_SALES_FORMULA_NAME = "NET_SALES_FORMULA_NAME";
	public static final String PS_BASE_PRICE_DATE = "psBasePriceDate";
	public static final String PS_BASE_PRICE_DDLB = "psBasePriceDdlb";
	public static final String BASE_PRICE_DATE = "BASE_PRICE_DATE";
	public static final String BASE_PRICE_DDLB = "BASE_PRICE_DDLB";

	/*
	 * FORECAST WEBSERVICE CONSTANT
	 */
	public static final String LOADDATA = "loaddata";
	public static final String RETURNS_FORECAST_ACTUAL = "RETURNS_FORECAST_ACTUAL_";
	public static final String EXCEPTION_IN_GETTING_DATA = "Exception in getting data -";
	public static final String FILENAME = "file-name";
	public static final String HEADER = "header";
	public static final String SALES_PROJECTION = "Sales Projection";
	public static final String A_DOUBLE = "Double";
	public static final String RETURNS_FORECAST_MASTER = "RETURNS_FORECAST_MASTER";
	public static final String GTNRETURNSFORECASTMETHODOLOGYFORMULA = "gtn-returns-forecast-methodology-formula";
	public static final String PROJECTED_RETURN_PERCENT = "PROJECTED_RETURN_PERCENT";
	/*
	 * END
	 */
	/*
	 * BPM webservice constant
	 */
	public static final String ADMINISTRATOR = "Administrator";
	public static final String EXCEPTION_IN = "Exception in ";
	public static final String NOT_SAVED = "Not Saved";
	public static final String APPROVE_FLAG = "approveFlag";
	public static final String EXECUTING_QUERY_IN_GTN_WS_RETURNS_DATABASE_SE = "Executing query in GtnWsReturnsDatabaseService: ";
	public static final String USER_NAME = "userName :";
	public static final String WORK_FLOW_STATUS = "WorkFlowStatus";
	public static final String TASK_SUMMARY = "taskSummary :";
	public static final String EVA_RULE_NAME = "EVA_RULE_NAME";
	public static final String ONE_TWO_THREE = "1-2-3";

	public static final String ICD_ITEM_NO = "ICD.ITEM_NO";
	public static final String ICD_ITEM_NAME = "ICD.ITEM_NAME";
	public static final String ICD_ITEM_DESC = "ICD.ITEM_DESC";
	public static final String ICD_IFP_DETAILS_ATTACHED_STATUS = "ICD.IFP_DETAILS_ATTACHED_STATUS";
	public static final String ICD_IFP_DETAILS_START_DATE = "ICD.IFP_DETAILS_START_DATE";
	public static final String ICD_IFP_DETAILS_END_DATE = "ICD.IFP_DETAILS_END_DATE";
	public static final String ICD_IFP_DETAILS_ATTACHED_DATE = "ICD.IFP_DETAILS_ATTACHED_DATE";
	public static final String ICD_IFP_DETAILS_MODIFIED_DATE = "ICD.IFP_DETAILS_MODIFIED_DATE";
	public static final String ICD_IFP_DETAILS_MODIFIED_BY = "ICD.IFP_DETAILS_MODIFIED_BY";
	public static final String ICD_IFP_DETAILS_CREATED_DATE = "ICD.IFP_DETAILS_CREATED_DATE";
	public static final String ICD_IFP_DETAILS_CREATED_BY = "ICD.IFP_DETAILS_CREATED_BY";
	public static final String ICD_CHECK_BOX = "ICD.CHECK_BOX";

	public static final String STRING_HASH = " # ";
	public static final String STRING_TILT = "~";
	public static final String ZERO = "0";
}
