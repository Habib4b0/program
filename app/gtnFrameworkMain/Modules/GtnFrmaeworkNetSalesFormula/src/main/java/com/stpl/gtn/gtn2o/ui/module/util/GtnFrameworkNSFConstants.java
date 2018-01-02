package com.stpl.gtn.gtn2o.ui.module.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkNSFConstants {

	public static final String SEARCH_CRITERIA_PANEL = "searchCriteriaPanel";

	private GtnFrameworkNSFConstants() {
		super();
	}

	private static final String NSF_MAIN_VIEW = "netSalesFormulaMainView";
	private static final String NO_SEARCH_CRITERIA = "No Search Criteria";
	private static final String PLEASE_ENTER_SEARCH_CRITERIA = "Please enter Search Criteria";
	private static final String RESET_CONFIRMATION = "Reset Confirmation";
	private static final String RESET_CONFIRMATION_MSG = "Are you sure you want to reset the values in the Search Criteria group box?";

	private static final String[] NSF_LANDING_SCREEN_RESULT_TABLE_VISIBLE_HEADERS = new String[] {
			"Net Sales Formula Type", "Net Sales Formula Id", "Net Sales Formula No", "Net Sales Formula Name",
			"Creation Date", "Created By", "Modified Date", "Modified By" };

	private static final Object[] NSF_LANDING_SCREEN_RESULT_TABLE_VISIBLE_COLUMNS = new Object[] { "formulaType",
			"formulaId", "formulaNo", "formulaName", "creationDate", "createdBy", "modifiedDate", "modifiedBy" };

	private static final String SYSTEMID = "systemId";

	private static final String NSF_ADD_VIEW = "netSalesFormulaAddView";
	private static final String EXISTING_CONTRACT = "Existing Contract";
	private static final String SELECT_CONTRACT = "Select Contract";
	private static final String[] AVAILABLE_CUSTOMER_VISIBLE_HEADERS = new String[] { "Customer No", "Customer Name",
			GtnFrameworkCommonConstants.CONTRACT_NO_HEADER, GtnFrameworkCommonConstants.CONTRACT_NAME_HEADER,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NO_HEADER,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NAME_HEADER };
	private static final Object[] AVAILABLE_CUSTOMER_VISIBLE_COLUMNS = new Object[] { "customerNo", "customerName",
			GtnFrameworkCommonConstants.CONTRACT_NO, GtnFrameworkCommonConstants.CONTRACT_NAME,
			GtnFrameworkCommonConstants.CFP_NO, GtnFrameworkCommonConstants.CFP_NAME };
	private static final String CFP_CONTRACT_DETAILS_SID = "cfpContractDetailsSid";

	private static final String ENABLE = "Enable";
	private static final String DISABLE = "Disable";
	private static final String EMPTY = "";
	public static final String NET_SALES_RULE_NO = "Net Sales Rule No";
	public static final String NET_SALES_RULE_NAME = "Net Sales Rule Name";

	private static final String[] SELECTED_CUSTOMER_VISIBLE_HEADERS = new String[] { "", "Customer No", "Customer Name",
			"Contract No", "Contract Name", "Company Family Plan No", "Company Family Plan Name",
			GtnFrameworkNSFConstants.NET_SALES_RULE_NO, GtnFrameworkNSFConstants.NET_SALES_RULE_NAME };
	private static final Object[] SELECTED_CUSTOMER_VISIBLE_COLUMNS = new Object[] { "checkRecordId", "customerNo",
			"customerName", "contractNo", "contractName", "cfpName", "cfpNo", "ruleNo", "ruleName" };

	private static final String[] AVAILABLE_CONTRACT_VISIBLE_HEADERS = new String[] { "Contract No", "Contract Name",
			"Contract Holder", "Market Type", "Company Family Plan No", "Company Family Plan Name",
			"Item Family Plan No", "Item Family Plan Name", "Price Schedule No", "Price Schedule Name", "Deduction No",
			"Deduction Name" };
	private static final Object[] AVAILABLE_CONTRACT_VISIBLE_COLUMNS = new Object[] { "contractNo", "contractName",
			"contractHolder", "marketType", "cfpNo", "cfpName", "ifpNo", "ifpName", "psNo", "psName", "rsNo",
			"rsName" };
	private static final Object[] AVAILABLE_DEDUCTIONS_VISIBLE_COLUMNS = new Object[] { "deductionType",
			"deductionSubType", "deductionCategory" };
	private static final String[] AVAILABLE_DEDUCTIONS_VISIBLE_HEADERS = new String[] { "Deduction Type",
			"Deduction Sub Type", "Deduction Category" };

	private static final String ADD_SUBTRACT_INDICATOR = "+/- Indicator";
	private static final String ADD = "Add";
	private static final String SUBTRACT = "Subtract";
	private static final String[] SELECTED_DEDUCTIONS_VISIBLE_HEADERS = new String[] { "",
			GtnFrameworkCommonConstants.DEDUCTION_TYPE, GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE,
			GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_HEADER, GtnFrameworkNSFConstants.INDICATOR_HEADER,
			NET_SALES_RULE_NO, NET_SALES_RULE_NAME };
	private static final Object[] SELECTED_DEDUCTIONS_VISIBLE_COLUMNS = new Object[] {
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_TYPE,
			GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE_HEADER,
			GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_PROPERTY, "indicator", "netSalesRuleNo",
			"netSalesRuleName" };
	private static final String ROW_NUMBER = "rn";

	private static final String[] AVAILABLE_DEDUCTIONS_HEADERS_FORMULA_TYPE_CONTRACT = new String[] {
			GtnFrameworkCommonConstants.CONTRACT_NO_HEADER, GtnFrameworkCommonConstants.CONTRACT_NAME_HEADER,
			GtnFrameworkCommonConstants.DEDUCTION_NO, GtnFrameworkCommonConstants.DEDUCTION_NAME,
			GtnFrameworkCommonConstants.DEDUCTION_TYPE, GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE,
			GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_HEADER, GtnFrameworkCommonConstants.MARKET_TYPE_HEADER,
			"Start Date", "End Date", GtnFrameworkCommonConstants.CONTRACT_HOLDER_HEADER,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NO_HEADER,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NAME_HEADER,
			GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN_NO, GtnFrameworkCommonConstants.HEADER_ITEM_FAMILY_PLAN_NAME,
			GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_HEADER,
			GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_HEADER };

	private static final Object[] AVAILABLE_DEDUCTIONS_COLUMNS_FORMULA_TYPE_CONTRACT = new Object[] {
			GtnFrameworkCommonConstants.CONTRACT_NO, GtnFrameworkCommonConstants.CONTRACT_NAME, "deductionNo",
			"deductionName", GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_TYPE,
			GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE_HEADER,
			GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_PROPERTY, GtnFrameworkCommonConstants.MARKET_TYPE,
			"startDate", "endDate", GtnFrameworkCommonConstants.CONTRACT_HOLDER, GtnFrameworkCommonConstants.CFP_NO,
			GtnFrameworkCommonConstants.CFP_NAME, GtnFrameworkCommonConstants.IFP_NUMBER,
			GtnFrameworkCommonConstants.IFP_NAME, "psNo", GtnFrameworkCommonConstants.PS_NAME };

	private static final String[] SELECTED_DEDUCTIONS_HEADERS_FORMULA_TYPE_CONTRACT = new String[] { "",
			GtnFrameworkCommonConstants.CONTRACT_NO_HEADER, GtnFrameworkCommonConstants.CONTRACT_NAME_HEADER,
			GtnFrameworkCommonConstants.DEDUCTION_NO, GtnFrameworkCommonConstants.DEDUCTION_NAME,
			GtnFrameworkCommonConstants.DEDUCTION_TYPE, GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE,
			GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_HEADER, GtnFrameworkCommonConstants.MARKET_TYPE_HEADER,
			"Start Date", "End Date", GtnFrameworkCommonConstants.CONTRACT_HOLDER_HEADER,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NO_HEADER,
			GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NAME_HEADER,
			GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN_NO, GtnFrameworkCommonConstants.HEADER_ITEM_FAMILY_PLAN_NAME,
			GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_HEADER,
			GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_HEADER, GtnFrameworkNSFConstants.INDICATOR_HEADER,
			NET_SALES_RULE_NO, NET_SALES_RULE_NAME };

	private static final Object[] SELECTED_DEDUCTIONS_COLUMNS_FORMULA_TYPE_CONTRACT = new Object[] {
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.CONTRACT_NO,
			GtnFrameworkCommonConstants.CONTRACT_NAME, "deductionNo", "deductionName",
			GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_TYPE, GtnFrameworkCommonConstants.DEDUCTION_SUB_TYPE_HEADER,
			GtnFrameworkCommonConstants.DEDUCTION_CATEGORY_PROPERTY, GtnFrameworkCommonConstants.MARKET_TYPE,
			"startDate", "endDate", GtnFrameworkCommonConstants.CONTRACT_HOLDER, GtnFrameworkCommonConstants.CFP_NO,
			GtnFrameworkCommonConstants.CFP_NAME, GtnFrameworkCommonConstants.IFP_NUMBER,
			GtnFrameworkCommonConstants.IFP_NAME, "psNo", GtnFrameworkCommonConstants.PS_NAME, "indicator",
			GtnFrameworkCommonConstants.RULE_NO, GtnFrameworkCommonConstants.RULE_NAME };

	private static final String FORMULA_TYPE_CONTRACT = "Contract";
	private static final String DEDUCTION_TYPE = "deductionTypeSysId";
	private static final String DEDUCTION_SUB_TYPE = "deductionSubTypeSysId";
	private static final String DEDUCTION_CATEGORY = "deductionCategorySysId";
	public static final String INDICATOR_HEADER = "Indicator";
	public static final String SELECTED_DEDUCTION_RESULT_TABLE = "selectedDeductionsResultTable";

	public static final List<String> POP_UP_TEXT_FIELD_PROPERTIES = Collections
			.unmodifiableList(Arrays.asList("netSalesRuleNo", GtnFrameworkCommonConstants.RULE_NO));
	public static final List<String> TEXT_FIELD_PROPERTIES = Collections
			.unmodifiableList(Arrays.asList("netSalesRuleName", GtnFrameworkCommonConstants.RULE_NAME));

	private static final String NET_SALES_RULE_POPUP_VIEW = "CDRPopUpSearchSearchView";
	private static final String POPULATE_BUTTON_CHECK_RECORD_ERROR_HEADER = "Populate Error";
	private static final String POPULATE_BUTTON_CHECK_RECORD_ERROR_MSG = "Please select atleast one value in the 'Selected Deductions' list view";
	private static final String[] RULE_DETAILS_LOOKUP_COLUMN = { "lineType", "itemGroupAsso", "keyword", "keyOperator",
			"value", "comparison", "compOperator" };

	private static final String[] RULE_DETAILS_LOOKUP_HEADER = { "Line Type", "Item/Group/Association", "Keyword",
			"Operator", "Value", "Comparison", "Operator" };

	public static final String VALUE_CHANGE_ALLOWED = "ValueChangeAllowed";
	private static final String NET_SALES_RULE_POPUP_RESULT_TABLE = "cDRPopUpsearchResultTable";
	private static final String NET_SALES_RULE_POPUP_DETAILS_TABLE = "cDRRulePopUpruleDetailsattachResultTable";

	public static String getNsfMainView() {
		return NSF_MAIN_VIEW;
	}

	public static String getNoSearchCriteria() {
		return NO_SEARCH_CRITERIA;
	}

	public static String getPleaseEnterSearchCriteria() {
		return PLEASE_ENTER_SEARCH_CRITERIA;
	}

	public static String getResetConfirmation() {
		return RESET_CONFIRMATION;
	}

	public static String getResetConfirmationMsg() {
		return RESET_CONFIRMATION_MSG;
	}

	public static String[] getNsfLandingScreenResultTableVisibleHeaders() {
		return NSF_LANDING_SCREEN_RESULT_TABLE_VISIBLE_HEADERS.clone();
	}

	public static Object[] getNsfLandingScreenResultTableVisibleColumns() {
		return NSF_LANDING_SCREEN_RESULT_TABLE_VISIBLE_COLUMNS.clone();
	}

	public static String getSystemid() {
		return SYSTEMID;
	}

	public static String getNsfAddView() {
		return NSF_ADD_VIEW;
	}

	public static String getExistingContract() {
		return EXISTING_CONTRACT;
	}

	public static String getSelectContract() {
		return SELECT_CONTRACT;
	}

	public static String[] getAvailableCustomerVisibleHeaders() {
		return AVAILABLE_CUSTOMER_VISIBLE_HEADERS.clone();
	}

	public static Object[] getAvailableCustomerVisibleColumns() {
		return AVAILABLE_CUSTOMER_VISIBLE_COLUMNS.clone();
	}

	public static String getCfpContractDetailsSid() {
		return CFP_CONTRACT_DETAILS_SID;
	}

	public static String getEnable() {
		return ENABLE;
	}

	public static String getDisable() {
		return DISABLE;
	}

	public static String getEmpty() {
		return EMPTY;
	}

	public static String getNetSalesRuleNo() {
		return NET_SALES_RULE_NO;
	}

	public static String[] getSelectedCustomerVisibleHeaders() {
		return SELECTED_CUSTOMER_VISIBLE_HEADERS.clone();
	}

	public static Object[] getSelectedCustomerVisibleColumns() {
		return SELECTED_CUSTOMER_VISIBLE_COLUMNS.clone();
	}

	public static String[] getAvailableContractVisibleHeaders() {
		return AVAILABLE_CONTRACT_VISIBLE_HEADERS.clone();
	}

	public static Object[] getAvailableContractVisibleColumns() {
		return AVAILABLE_CONTRACT_VISIBLE_COLUMNS.clone();
	}

	public static Object[] getAvailableDeductionsVisibleColumns() {
		return AVAILABLE_DEDUCTIONS_VISIBLE_COLUMNS.clone();
	}

	public static String[] getAvailableDeductionsVisibleHeaders() {
		return AVAILABLE_DEDUCTIONS_VISIBLE_HEADERS.clone();
	}

	public static String getAddSubtractIndicator() {
		return ADD_SUBTRACT_INDICATOR;
	}

	public static String getAdd() {
		return ADD;
	}

	public static String getSubtract() {
		return SUBTRACT;
	}

	public static String[] getSelectedDeductionsVisibleHeaders() {
		return SELECTED_DEDUCTIONS_VISIBLE_HEADERS.clone();
	}

	public static Object[] getSelectedDeductionsVisibleColumns() {
		return SELECTED_DEDUCTIONS_VISIBLE_COLUMNS.clone();
	}

	public static String getRowNumber() {
		return ROW_NUMBER;
	}

	public static String[] getAvailableDeductionsHeadersFormulaTypeContract() {
		return AVAILABLE_DEDUCTIONS_HEADERS_FORMULA_TYPE_CONTRACT.clone();
	}

	public static Object[] getAvailableDeductionsColumnsFormulaTypeContract() {
		return AVAILABLE_DEDUCTIONS_COLUMNS_FORMULA_TYPE_CONTRACT.clone();
	}

	public static String[] getSelectedDeductionsHeadersFormulaTypeContract() {
		return SELECTED_DEDUCTIONS_HEADERS_FORMULA_TYPE_CONTRACT.clone();
	}

	public static Object[] getSelectedDeductionsColumnsFormulaTypeContract() {
		return SELECTED_DEDUCTIONS_COLUMNS_FORMULA_TYPE_CONTRACT.clone();
	}

	public static String getFormulaTypeContract() {
		return FORMULA_TYPE_CONTRACT;
	}

	public static String getDeductionType() {
		return DEDUCTION_TYPE;
	}

	public static String getDeductionSubType() {
		return DEDUCTION_SUB_TYPE;
	}

	public static String getDeductionCategory() {
		return DEDUCTION_CATEGORY;
	}

	public static List<String> getPopUpTextFieldProperties() {
		return POP_UP_TEXT_FIELD_PROPERTIES;
	}

	public static List<String> getTextFieldProperties() {
		return TEXT_FIELD_PROPERTIES;
	}

	public static String getNetSalesRulePopupView() {
		return NET_SALES_RULE_POPUP_VIEW;
	}

	public static String getPopulateButtonCheckRecordErrorHeader() {
		return POPULATE_BUTTON_CHECK_RECORD_ERROR_HEADER;
	}

	public static String getPopulateButtonCheckRecordErrorMsg() {
		return POPULATE_BUTTON_CHECK_RECORD_ERROR_MSG;
	}

	public static String[] getRuleDetailsLookupColumn() {
		return RULE_DETAILS_LOOKUP_COLUMN.clone();
	}

	public static String[] getRuleDetailsLookupHeader() {
		return RULE_DETAILS_LOOKUP_HEADER.clone();
	}

	public static String getNetSalesRulePopupResultTable() {
		return NET_SALES_RULE_POPUP_RESULT_TABLE;
	}

	public static String getNetSalesRulePopupDetailsTable() {
		return NET_SALES_RULE_POPUP_DETAILS_TABLE;
	}

}
