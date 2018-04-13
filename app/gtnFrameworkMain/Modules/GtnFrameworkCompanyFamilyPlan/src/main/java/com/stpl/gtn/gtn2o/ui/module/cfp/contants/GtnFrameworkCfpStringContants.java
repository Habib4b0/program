
package com.stpl.gtn.gtn2o.ui.module.cfp.contants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkCfpStringContants {

	public static final String GTN_CFP_ATLEAST_ONE_COMPANY_VALIDATION_MSG = "Add at least one company in Company Addition tab for CFP";
	public static final String GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG = "Start Date required for selected companies";
	public static final String GTN_CFP_STATUS_REQUIRED_VALIDATION_MSG = "Status required for selected companies";
	public static final String GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_001 = "CFP Start Date is less than CFP End date for ";
	public static final String GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_002 = " companies in Companies tab";
	public static final String GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_003 = "CFP Start Date is less than CFP End date for Company No ";
	public static final String GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_004 = " in Companies tab";
	public static final String GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_005 = "Please enter different Start Date. The selected Trading Partner and Start Date Combination already exists for Company No ";
	public static final String GTN_CFP_START_DATE_REQUIRED_VALIDATION_MSG_006 = "Please enter different Start Date.Since selected Trading Partner and Start Date Combination already exists for Company No ";
	public static final String GTN_CFP_CHECK_ATLEAST_ONE_VALIDATION_MSG = "Check Aleast one Company to save in Companies tab";
	public static final String GTN_CFP_ID_AND_NO_DUPLICATE_VALIDATION_MSG = "Company Family Plan ID & Company Family Plan NO already exists.";
	public static final String GTN_CFP_ID_DUPLICATE_VALIDATION_MSG = "Company Family Plan ID already exists.";
	public static final String GTN_CFP_NO_DUPLICATE_VALIDATION_MSG = "Company Family Plan NO already exists.";
	public static final String GTN_CFP_DELETE_VALIDATION_MSG = "Company Family Plan cannot be deleted, associated as parent to another Company Family Plan";
	public static final String GTN_CFP_VALIDATION_MSG_START_DATE_NULL = "Start Date required for Company No ";
	public static final String GTN_CFP_VALIDATION_MSG_STATUS_NULL = "Status required for Company No ";
	public static final String GTN_CFP_VALIDATION_MSG_COMPANY_COUNT = " more company/companies in Companies tab";
	public static final String GTN_CFP_CONFIRMATION_MSG = "Confirmation";
	public static final String GTN_CFP_CONFIRMATION_MSG_BACK = "Any unsaved information will not be saved. Do you want to proceed?";
	public static final String GTN_CFP_CONFIRMATION_MSG_SAVE = "Save Record ";
	public static final String GTN_CFP_CONFIRMATION_MSG_RESET_HEADER = "Reset Confirmation";
	public static final String GTN_CFP_PARENT_CONFIRMATION_MSG_RESET = "Are you sure you want to reset the page to default/previous values?";
	public static final String GTN_CFP_CONFIRMATION_MSG_DELETE = "Are you sure you want to delete the record";
	public static final String GTN_CFP_VALIDATION_MSG_POPULATE_001 = "Please Select the Field to populate";
	public static final String GTN_CFP_VALIDATION_MSG_POPULATE_002 = "Please Select a value to populate";
	public static final String GTN_CFP_VALIDATION_MSG_POPULATE_003 = "Please Select a date to populate";
	public static final String GTN_CFP_VALIDATION_MSG_POPULATE_004 = "Please select a row to populate";
	public static final String GTN_CFP_VALIDATION_MSG_COMPANY_ADDITION_REMOVE = "Please select a company to remove";
	public static final String GTN_CFP_VALIDATION_MSG_COMPANY_ADDITION_ADD = "Please select a company to add";
	public static final String GTN_CFP_VALIDATION_MSG_VIEW_HEADER = "View Error";
	public static final String GTN_CFP_VALIDATION_MSG_VIEW = "Please select a record to view";
	public static final String GTN_CFP_VALIDATION_MSG_EDIT_HEADER = "Edit Error";
	public static final String GTN_CFP_VALIDATION_MSG_EDIT = "Please select a record to edit";
	public static final String GTN_CFP_LANDING_SCREEN_RESET_CONFIRMATION_MSG_RESET = "Are you sure you want to reset the values in the Search Criteria group box?";
	public static final String CFP_COMPANIES_TAB_RESULT_DATA_TABLE = "cfpCompaniesTabResultDataTable";
	public static final String CFP_DATE_EQUAL_VALIDATION = "CFP Start date and CFP End date should not be equal";
	public static final String CFP_DATE_LESS_THAN_VALIDATION = "CFP End date should be greater than CFP Start date";
        public static final String CFP_ELIGIBLE_DATE = "CfpEligibleDate";
	public static final List<String> CFP_TEXTFIELD_PROPERTIES_LIST = Collections
			.unmodifiableList(Arrays.asList(GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID,
					GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME,
					GtnFrameworkCommonConstants.TRADE_CLASS, GtnFrameworkCommonConstants.PROPERTY_COMPANY_CATEGORY,
					GtnFrameworkCommonConstants.TRADING_PARTNER_CONTRACT_NO, GtnFrameworkCommonConstants.MODIFIED_BY,
					GtnFrameworkCommonConstants.CREATED_BY, GtnFrameworkCommonConstants.COMPANY_STATUS_VALUE,
					GtnFrameworkCommonConstants.COMPANY_TYPE_VALUE));
	public static final List<String> CFP_DATEFIELD_PROPERTIES_LIST = Collections.unmodifiableList(Arrays.asList(
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_START_DATE,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_END_DATE, GtnFrameworkCommonConstants.CFP_ATTACHED_DATE,
			GtnFrameworkCommonConstants.MODIFIED_DATE, GtnFrameworkCommonConstants.CREATED_DATE, CFP_ELIGIBLE_DATE));
	public static final List<String> CFP_EDITABLEFIELD_LIST = Collections.unmodifiableList(Arrays.asList(
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_START_DATE,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_END_DATE, "companyFamilyPlanStatusValue",
			GtnFrameworkCommonConstants.TRADING_PARTNER_CONTRACT_NO, CFP_ELIGIBLE_DATE));

	public static final List<String> CFP_COMPANIES_VIEW_HEADERS_LIST = Collections.unmodifiableList(Arrays.asList(
			"Company ID", "Company No", "Company Name", "CFP Status", "CFP Start Date", "CFP End Date",
			"Company Status", "Company Type", "Company TradeClass", "Company Category", "Trading Partner Contract No",
			"Attached Date", "Modified Date", "Modified By", "Created Date", "Created By"));

	public static final List<String> CFP_COMPANIES_HEADERS_LIST = Collections
			.unmodifiableList(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY, "Company ID", "Company No",
					"Company Name", "CFP Status", "CFP Start Date", "CFP End Date", "Company Status", "Company Type",
					"Company TradeClass", "Company Category", "Trading Partner Contract No", "Attached Date",
					"Modified Date", "Modified By", "Created Date", "Created By","Forecast Eligibility Date"));

	public static final List<?> CFP_COMPANIES_COLUMN_LIST = Collections.unmodifiableList(Arrays.asList(
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID,
			GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME,
			"companyFamilyPlanStatusValue", GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_START_DATE,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_END_DATE, GtnFrameworkCommonConstants.COMPANY_STATUS_VALUE,
			GtnFrameworkCommonConstants.COMPANY_TYPE_VALUE, GtnFrameworkCommonConstants.TRADE_CLASS,
			GtnFrameworkCommonConstants.PROPERTY_COMPANY_CATEGORY,
			GtnFrameworkCommonConstants.TRADING_PARTNER_CONTRACT_NO, GtnFrameworkCommonConstants.CFP_ATTACHED_DATE,
			GtnFrameworkCommonConstants.MODIFIED_DATE, GtnFrameworkCommonConstants.MODIFIED_BY,
			GtnFrameworkCommonConstants.CREATED_DATE, GtnFrameworkCommonConstants.CREATED_BY, CFP_ELIGIBLE_DATE));

	public static final List<?> CFP_COMPANIES_VIEW_COLUMN_LIST = Collections.unmodifiableList(Arrays.asList(
			GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO,
			GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME, "companyFamilyPlanStatusHelperValue",
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_START_DATE,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_END_DATE, GtnFrameworkCommonConstants.COMPANY_STATUS_VALUE,
			GtnFrameworkCommonConstants.COMPANY_TYPE_VALUE, GtnFrameworkCommonConstants.TRADE_CLASS,
			GtnFrameworkCommonConstants.PROPERTY_COMPANY_CATEGORY,
			GtnFrameworkCommonConstants.TRADING_PARTNER_CONTRACT_NO, GtnFrameworkCommonConstants.CFP_ATTACHED_DATE,
			GtnFrameworkCommonConstants.MODIFIED_DATE, GtnFrameworkCommonConstants.MODIFIED_BY,
			GtnFrameworkCommonConstants.CREATED_DATE, GtnFrameworkCommonConstants.CREATED_BY));

	public static final List<String> CFP_FIELDS = Collections.unmodifiableList(
			Arrays.asList("cfpInformationTabCFPId", "cfpInformationTabCFPNo", "cfpInformationTabCFPName", "cfpId",
					"cfpNo", "cfpName", "cfpInformationTabModifiedBy", "cfpInformationTabParentCFPId",
					"cfpInformationTabParentCFPName", "cfpInformationTabCFPStatus", "cfpInformationTabCFPType",
					"cfpInformationTabCFPCategory", "cfpInformationTabCFPTradeClass", "cfpInformationTabCFPDesignation",
					"cfpInformationTabCFPSalesInclusion", "cfpInformationCFPStartDate", "cfpInformationCFPEndDate",
					"cfpInformationCreatedDate", "cfpInformationModifiedDate"));
	public static final List<Object> DISABLED_CFP_FIELDS = Collections
			.unmodifiableList(Arrays.asList(new Object[] { "cfpInformationTabCreatedBy", "cfpInformationCreatedDate",
					"cfpInformationTabModifiedBy", "cfpInformationModifiedDate" }));
        
        private static final String[] CFP_VALIDATE_FIELDS = new String[] { "cfpInformationTabCFPId", "cfpInformationTabCFPNo",
				"cfpInformationTabCFPStatus", "cfpInformationCFPStartDate", "cfpInformationTabCFPName",
				"cfpInformationTabCFPSalesInclusion" };
        private static final String[] CUSTOM_FILTER_PROPERTY_IDS = { GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS_VALUE, "companyStatusValue",
				"companyTypeValue", "tradeClass", "companyCategory", "modifiedBy", "createdBy" };
        private static final String[] LIST_NAME_LIST = { GtnFrameworkCommonConstants.STATUS, GtnFrameworkCommonConstants.STATUS,
				"COMPANY_TYPE", "COMPANY_TRADE_CLASS", "COMPANY_CATEGORY", GtnFrameworkCommonConstants.USERS, GtnFrameworkCommonConstants.USERS };
        private static final String[] PROPERTY_IDS = { GtnFrameworkCommonConstants.PROPERTY_COMPANY_STATUS,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE, GtnFrameworkCommonConstants.TRADE_CLASS,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_CATEGORY,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_GROUP };
        private static final String[] CUSTOM_LIST_NAME_LIST = { "STATUS", "COMPANY_TYPE", "COMPANY_TRADE_CLASS", "COMPANY_CATEGORY",
				"COMPANY_GROUP" };
        private static final  String[] LANDING_SCREEN_PROPERTYIDS = { "companyFamilyPlanCategory", GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_TYPE,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS, "companyFamilyPlanDesignation", GtnFrameworkCfpStringContants.CFP_MODIFIED_BY,
				GtnFrameworkCfpStringContants.CFP_CREATED_BY };
        private static final  String[] LANDING_SCREEN_LIST_NAME_ARRAY = { "CFP_CATEGORY", "CFP_TYPE", "STATUS", "CFP_DESIGNATION", GtnFrameworkCommonConstants.USERS, GtnFrameworkCommonConstants.USERS };

    public static String[] getLandingScreenListNameArray() {
        return LANDING_SCREEN_LIST_NAME_ARRAY.clone();
    }
        

    public static String[] getLandingScreenpropertyIds() {
        return LANDING_SCREEN_PROPERTYIDS.clone();
    }
        

    public static String[] getCustomListNameList() {
        return CUSTOM_LIST_NAME_LIST.clone();
    }
        

    public static String[] getPropertyIds() {
        return PROPERTY_IDS.clone();
    }

        
    public static String[] getListNameList() {
        return LIST_NAME_LIST.clone();
    }
        

    public static String[] getCfpValidateFields() {
        return CFP_VALIDATE_FIELDS.clone();
    }

    public static String[] getCustomFilterPropertyIds() {
        return CUSTOM_FILTER_PROPERTY_IDS.clone();
    }
    
	public static final String CFP_CREATED_BY = "cfpcreatedBy";
	public static final String CFP_MODIFIED_BY = "cfpmodifiedBy";

	

	private GtnFrameworkCfpStringContants() {
	}
}
