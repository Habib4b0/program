package com.stpl.gtn.gtn2o.ws.constants.url;

public final class GtnWebServiceUrlConstants {

	/**
	 * Company Master Constants
	 */
	public static final String GTN_WS_COMPANY_MASTER = "/GtnWsCompanyMaster";
	public static final String GTN_WS_CM_SAVE_SERVICE = "/GtnWsCMSaveService";
	public static final String GTN_WS_CM_VALIDATION_SERVICE = "/GtnWsCMValidationService";
	public static final String GTN_WS_CM_VALIDATION_EXISTS = "/GtnWsCMValidationExist";
	public static final String GTN_WS_CM_HIBERNATE_SAVE_SERVICE = "/GtnWsCMHibernateSaveService";
	public static final String GTN_WS_CM_QUALIFIER_SERVICE = "/GtnWsCMQualifierService";
	public static final String GTN_CM_GET_COMPANY_QUALIFIER = "/GtnWsCMgetCompanyQualifier";
	public static final String GTN_CM_QUALIFIER_SAVE_SERVICE = "/GtnCMQualifierSaveService";
	public static final String GTN_CM_QUALIFIER_UPDATE_SERVICE = "/GtnCMQualifierUpdateService";
	public static final String GTN_CM_QUALIFIER_DELETE_SERVICE = "/GtnCMQualifierDeleteService";
	public static final String GTN_CM_GET_DELETE_SERVICE = "/GtnCMgetDeleteService";
	public static final String GTN_CM_GET_DETAILS_SERVICE = "/GtnCMgetDetailsService";
	public static final String GTN_CM_UPDATE_SERVICE = "/GTNCompanyMasterUpdateService";
	public static final String GTN_CM_FINANCIAL_MANUAL_CLOSE_SERVICE = "/GtnCMFinancialManualCloseService";
	public static final String GTN_CM_FINANCIAL_AUTOMATIC_CLOSE_SERVICE = "/GtnCMFinancialAutomaticCloseService";
	public static final String GTN_CM_FINANCIAL_CLOSE = "/GtnCMFinancialClose";
	public static final String GTN_CM_GET_STATUS = "/GtnCMgetStatus";
	public static final String GTN_CM_FC_HISTORY_DATA_SERVICE = "/GtnCMFCHistoryDataService";
	public static final String GTN_CM_FC_SWAP_AUTO_MANUAL_SERVICE = "/GtnCMFCswapAutoManualService";
	public static final String GTN_CM_GET_CALENDAR = "/GtnCMgetCalendar";
	public static final String GTN_CM_IS_THERE_BUSINESSDAY_FOR_MONTHS = "/GtnCMisThereBusinessdayForMonths";
	public static final String GTN_CM_BUSINESS_DAY = "/GtnCMBusinessDay";
	public static final String GTN_CM_BUSINESS_HOUR = "/GtnCMBusinessHour";
	public static final String GTN_CM_BUSINESS_YEAR = "/GtnCMBusinessYear";
	public static final String GTN_CM_BUSINESS_MONTH = "/GtnCMBusinessMonth";
	public static final String GTN_CM_FINANCIAL_TEMP_INSERT = "/insertToTempTable";
	public static final String GTN_CM_FINANCIAL_TEMP_DELETE = "/deleteTempTable";
	public static final String GTN_CM_FINANCIAL_TEMP_EDIT_SELECT = "/tempTableSelectForEdit";
	public static final String GTN_CM_FINANCIAL_CLOSE_VALIDATION = "/financialCloseValidation";
	public static final String GTN_CM_IDENTIFIER_QUALIFIER_VALIDATION = "/identifierQualifierIsExist";
	public static final String GTN_WS_CP_DETAILS_FOR_COMPANY_INSERT_SERVICE = "/GtnWsCPDetailsForCompanyInsertService";
	public static final String GTN_WS_CM_FETCH_HELPERSID = "/GtnWsFetchHelperSid";

	/**
	 * Table header constants
	 */

	public static final String GTN_GENERAL_DUAL_LIST_BOX_GET_LEFT_TABLE_DATA = "/GtnGeneralDualListBoxgetLeftTableData";
	public static final String GTN_GENERAL_DUAL_LIST_BOX_GET_RIGHT_TABLE_TREE_DATA = "/GtnGeneralDualListBoxgetRightTableTreeData";

	/**
	 * Common Constants
	 */
	public static final String GTN_COMMON_COMPONENTS = "/GtnCommonComponents";
	public static final String GTN_COMMON_DUAL_LIST_BOX = "/GtnCommonDualListBox";
	public static final String GTN_COMMON_GENERAL_SERVICE = "/GtnCommonGeneralService";
	public static final String GTN_COMMON_LOAD_COMBO_BOX = "/GtnCommonLoadComboBox";
	public static final String GTN_COMMON_LOAD_NON_HELPER_DDLB = "/GtnCommonLoadNonHelperDDLB";
	public static final String GTN_COMMON_SEARCH_SERVICE = "/GtnCommonSearchService";
	public static final String GTN_COMMON_SEARCH = "/GtnCommonSearch";
	public static final String GTN_COMMON_LOAD_USER_COMBO_BOX = "/loadUserIdNameComboBox";
	public static final String GTN_COMMON_GENERAL_SERVICE_USER_NAME = "/getUserNameById";
	public static final String GTN_COMMON_RELOAD_COMBO_BOX = "/GtnCommonReLoadComboBox";
	public static final String GTN_TEMP_EXCEL_FILE_DELETE = "/tempExcelFileDelete";
	public static final String GTN_AUTOMATIC_RELATION_SERIVCE = "/GtnAutomaticRelaitonService";
	public static final String GTN_AUTOMATIC_RELATION_UPDATE_SERIVCE = "/GtnAutomaticRelaitonUpdateService";
	public static final String RELATION_WEBSERVICE_TEST = "/relstionshipWebserviceTest";
	public static final String AUTOMATIC_RELATION_UPDATE = "/updateAutoMaticRelations";
	public static final String AUTOMATIC_ALL_RELATION_UPDATE = "/updateAutoMaticAllRelations";
	public static final String WAIT_AUTOMATIC_RELATION_UPDATE = "/waitupdateAutoMaticRelations";
	public static final String GTN_CSV_EXPORT_FILE_SERVICE = "/gtnCsvExportFileService";
	public static final String GTN_BCP_SERVICE = "/gtnBcpService";
	public static final String CALCULATE = "/calulate";
	public static final String GTN_DOWNLOAD_FILE_SERVICE = "/gtnDownloadAttachmentService";
	public static final String GTN_DEDUCTION_LEVEL_COMBOBOX_SERVICE = "/GtnSearchWebService";
	public static final String GTN_DEDUCTION_LEVEL_COMBOBOX_LOAD = "/gtnSearch";

	/**
	 * Forecast dataselection Constants
	 */
	public static final String GTN_DATASELCTION_EDIT_SERVICE = "/GtnDataSelectionEdit";
	public static final String GTN_CCP_INSERT_SERVICE = "/GtnCCPInsert";
	public static final String GTN_HIERARCHY_CONTROL = "/GtnHierarchyQueryGenerator";
	public static final String GTN_DATASELECTION_EDIT_CUSTHIERARCHY_INSERT = "/relationShipCustHierarchyInsert";
	public static final String GTN_CCP_INSERT = "/forecastCCPInsert";
        public static final String GTN_CCP_INSERT_FOR_ARP = "/forecastCCPInsertForARP";
	public static final String GTN_REPORT_CCP_INSERT_SQL = "/reportCCPInsertSql";
	public static final String GTN_REPORT_CCP_INSERT_MONGO = "/reportCCPInsertMongo";
	public static final String GTN_QUERY_FOR_TABLENAME_HIERARCHY_TYPE = "/getQueryByTableNameAndHierarchyTypeForMultiLevel";
	public static final String GTN_DATASELECTION_LOAD_CUSTOMER_LEVEL = "/dataSelectionLoadCustomerHierarcy";
	public static final String GTN_DATASELECTION_LOAD_PRODUCT_LEVEL = "/dataSelectionLoadProductHierarcy";
	public static final String GTN_DATASELECTION_LOAD_SELECTED_PRODUCT = "/dataSelectionSelectedProductChild";
	public static final String GTN_DATASELECTION_LOAD_SELECTED_CUSTOMER = "/dataSelectionSelectedCustomerChild";
	public static final String GTN_DATASELECTION_LOAD_LEVELVALUE_MAP = "/dataSelectionSelectedLevelValueMap";
	public static final String GTN_REPORTDATASELECTION_LOAD_LEVELVALUE_MAP = "/reportDataSelectionLevelValueMap";
	public static final String GTN_REPORTCUSTOMER_HIERARCHYLEVEL_VALUES = "/reportCustomerHierarchyLevelValues";
	public static final String GTN_REPORT_LOAD_CUSTOMER_LEVEL = "/reportLoadCustomerLevel";
	public static final String GTN_REPORT_LOAD_MULTISELECT_DDLB = "/reportLoadMultiselectDdlb";

        public static final String GTN_DATASELECTION_ARM_LOAD_CUSTOMER_LEVEL = "/dataSelectionARMLoadCustomerHierarcy";
        public static final String GTN_DATASELCTION_ARM_EDIT_SERVICE = "/GtnDataSelectionARMEdit";
	public static final String GTN_DATASELECTION_ARM_LOAD_LEVEL_VALUE_MAP = "/dataSelectionARMSelectedLevelValueMap";
        public static final String GTN_DATASELECTION_ARM_LOAD_PRODUCT_LEVEL = "/dataSelectionARMLoadProductHierarcy";
        
        public static final String GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_SERVICE = "/GtnAdjustmentDetailsDeductionValue";
        public static final String GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER = "/GtnAdjustmentDetailsDeductionValueController";
        public static final String GTN_ADJUSTMENT_DETAILS_TABLE_LOAD_SERVICE = "/gtnAdjustmentDetailsTableLoadService";
        public static final String GTN_ADJUSTMENT_DETAILS_PUBLIC_VIEW_TABLE_LOAD_SERVICE = "/gtnAdjustmentDetailsPublicViewTableLoadService";
        public static final String GTN_ADJUSTMENT_DETAILS_SAVE_VIEW_MASTER_SERVICE = "/gtnAdjustmentDetailsSaveViewMasterService";
        public static final String GTN_ADJUSTMENT_DETAILS_UPDATE_REPROCESS_SERVICE = "/gtnAdjustmentDetailsUpdateReprocessService";

	private GtnWebServiceUrlConstants() {
		super();
	}

}
