/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.contractdashboard.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsContractDashboardContants {
	private GtnWsContractDashboardContants() {
		/**
		 * empty constructor
		 */
	}

	public static final String GTN_CONTRACT_DASHBOARD_SERVICE = "/GtnContractDashboardService";
	public static final String GET_CONTRACT_DASHBOARD_TABLE_DATA = "/getContractDashboardTableData";
	public static final String GET_CONTRACT_DASHBOARD_DETAILS_TABLE_DATA = "/getContractDashboardDetailsTableData";
	public static final String GET_CONTRACT_DASHBOARD_PROCESS_TABLE_DATA = "/getContractDashboardProcessTableData";
	public static final String GET_CD_COMPANY_ADDITION_LEFT_TABLE_DATA = "/getCDCompanyAdditionLeftTableData";
	public static final String COMPANY_ADDITION_MOVE_RIGHT = "/companyAdditionMoveRight";
	public static final String COMPANY_ADDITION_MOVE_ALL_RIGHT = "/companyAdditionMoveAllRight";
	public static final String COMPANY_ADDITION_MOVE_LEFT = "/companyAdditionMoveLeft";
	public static final String COMPANY_ADDITION_MOVE_ALL_LEFT = "/companyAdditionMoveAllLeft";
	public static final String GET_CD_COMPANY_ADDITION_RIGHT_TABLE_DATA = "/getCDCompanyAdditionRightTableData";
	public static final String GET_CONTRACT_DASHBOARD_PROCESS_TABLE_EXCEL_DATA = "/getContractDashboardProcessTableExcelData";

	public static final String GET_CD_ITEM_ADDITION_LEFT_TABLE_DATA = "/getCDItemAdditionLeftTableData";
	public static final String ITEM_ADDITION_MOVE_RIGHT = "/itemAdditionMoveRight";
	public static final String ITEM_ADDITION_MOVE_ALL_RIGHT = "/itemAdditionMoveAllRight";
	public static final String ITEM_ADDITION_MOVE_LEFT = "/itemAdditionMoveLeft";
	public static final String ITEM_ADDITION_MOVE_ALL_LEFT = "/itemAdditionMoveAllLeft";
	public static final String GET_CD_ITEM_ADDITION_RIGHT_TABLE_DATA = "/getCDItemAdditionRightTableData";

	public static final String GET_CD_COMPANIES_DETAIL_TABLE_DATA = "/getCDCompniesDetailTableData";
	public static final String GET_CD_COMPANIES_DETAIL_VIEW_TABLE_DATA = "/getCDCompniesDetailViewTableData";
	public static final String GET_CD_COMPANIES_HISTORY_TABLE_DATA = "/getCDCompniesHistoryTableData";
	public static final String POPULATE_ALL_COMPANY = "/populateAllCompanies";
	public static final String POPULATE_COMPANY = "/populateCompany";
	public static final String POPULATE_COMPANY_FIELD = "/populateCompanyField";

	public static final String GET_CD_ITEMS_DETAIL_TABLE_DATA = "/getCDItemsDetailTableData";
	public static final String GET_CD_ITEMS_DETAIL_VIEW_TABLE_DATA = "/getCDItemsDetailViewTableData";
	public static final String GET_CD_ITEMS_HISTORY_TABLE_DATA = "/getCDItemsHistoryTableData";
	public static final String POPULATE_ALL_ITEM = "/populateAllItems";
	public static final String POPULATE_ITEM = "/populateItem";
	public static final String POPULATE_ITEM_FIELD = "/populateItemField";
	public static final String SUBMIT_CONTRACT = "/submitContractAndStartWorkFlow";
	public static final String CHECK_VALID_USER = "/checkValidUser";
	public static final String APPROVE_CONTRACT = "/approveContract";
	public static final String REJECT_CONTRACT = "/rejectContract";
	public static final String CANCEL_CONTRACT = "/cancelContract";
	public static final String WITHDRAW_CONTRACT = "/withdrawContract";
	public static final String GTN_WS_CONTRACT_WORKFLOW_SERVICE = "/GtnWsContractWorkflowService";

	public static final String GET_CD_PRICING_DETAIL_TABLE_DATA = "/getCDPricingDetailTableData";
	public static final String GET_CD_PRICING_PROTECTION_TABLE_DATA = "/getCDPricingProtectionTableData";
	public static final String GET_CD_PRICING_PROTECTION_EXCEL_DATA = "/getCDPricingProtectionExcelData";
	public static final String GET_CD_PRICING_DETAIL_VIEW_TABLE_DATA = "/getCDPricingDetailViewTableData";
	public static final String GET_CD_PRICING_PROTECTION_VIEW_TABLE_DATA = "/getCDPricingProtectionViewTableData";
	public static final String GET_CD_PRICING_HISTORY_TABLE_DATA = "/getCDPricingHistoryTableData";

	public static final String GET_CD_REBATE_DETAIL_TABLE_DATA = "/getCDRebateDetailTableData";
	public static final String GET_CD_REBATE_DETAIL_VIEW_TABLE_DATA = "/getCDRebateDetailViewTableData";
	public static final String GET_CD_REBATE_HEADER_HISTORY_TABLE_DATA = "/getCDRebateHeaderHistoryTableData";
	public static final String GET_CD_REBATE_PROCESSING_HISTORY_TABLE_DATA = "/getCDRebateProcessingHistoryTableData";

	public static final String GET_NSF_LOOKUP_TABLE_DATA = "/getNSFLookupTableData";
	public static final String GET_CFP_LOOKUP_TABLE_DATA = "/getCFPLookupTableData";
	public static final String GET_IFP_LOOKUP_TABLE_DATA = "/getIFPLookupTableData";
	public static final String GET_TP_LOOKUP_TABLE_DATA = "/getTPLookupTableData";
	public static final String GET_PS_LOOKUP_TABLE_DATA = "/getPSLookupTableData";
	public static final String GET_RS_LOOKUP_TABLE_DATA = "/getRSLookupTableData";
	public static final String GET_RULES_LOOKUP_TABLE_DATA = "/getRulesLookupTableData";
	public static final String GET_RULE_DETAILS_LOOKUP_TABLE_DATA = "/getRuleDetailsLookupTableData";
	public static final String GET_CONTRACT_INFO_FIELD_DATA = "/getContractInfoFieldData";
	public static final String GET_COMPANY_INFO_FIELD_DATA = "/getCompanyInfoFieldData";
	public static final String GET_ITEM_INFO_FIELD_DATA = "/getItemInfoFieldData";
	public static final String GET_PRICING_INFO_FIELD_DATA = "/getPricingInfoFieldData";
	public static final String GET_REBATE_INFO_FIELD_DATA = "/getRebateInfoFieldData";
	public static final String REMOVE_COMPANIES = "/removeCompanies";
	public static final String REMOVE_ITEMS = "/removeItems";
	public static final String CHECK_SELECTED_COMPANIES = "/checkSelectedCompanies";
	public static final String CHECK_SELECTED_ITEMS = "/checkSelectedItems";
	public static final String GET_FORMULA_LOOKUP_TABLE_DATA = "/getFormulaLookupTableData";
	public static final String GET_DC_LOOKUP_TABLE_DATA = "/getDCLookupTableData";
	public static final String GET_RP_LOOKUP_TABLE_DATA = "/getRPLookupTableData";

	public static final String VALIDATE_CONTRACT_DASHBOARD_TO_SAVE = "/validateContractDashboardToSave";
	public static final String SUBMIT_CONTRACT_DASHBOARD = "/submitContractDashboard";
	public static final String APPROVE_CONTRACT_DASHBOARD = "/approveContractDashboard";
	public static final String REJECT_CONTRACT_DASHBOARD = "/rejectContractDashboard";
	public static final String CANCEL_CONTRACT_DASHBOARD = "/cancelContractDashboard";
	public static final String REJECT_CANCEL_CONTRACT_DASHBOARD = "/rejectcancelContractDashboard";
	public static final String GET_WORKFLOW_INFO = "/getWorkFlowInfo";

	public static final String CHECK_ADD_TO_TREE = "/checkAddToTree";
	public static final String ADD_TO_TREE = "/addToTree";
	public static final String SAVE_CONTRACT_TREE = "/saveContractTree";
	public static final String VALIDATE_CONTRACT_TO_PROCESS = "/validateContractToProcess";
	public static final String PROCESS_CONTRACT_INFO_TO_SESSION = "/processContractInfoToSession";
	public static final String DELETE_CONTRACT_INFO_ON_BACK_PROCESS = "/deleteContractInfoOnBackProcess";
	public static final String VALIDATE_CONTRACT_TO_REBUILD = "/validateContractToRebuild";
	public static final String GET_CONTRACT_TO_REBUILD = "/getContractToRebuild";
	public static final String CONTRACT = "Contract";
	public static final String COMPANY_FAMILY_PLAN = "Company Family Plan";
	public static final String ITEM_FAMILY_PLAN = "Item Family Plan";
	public static final String PRICE_SCHEDULE = "Price Schedule";
	public static final String REBATE_SCHEDULE = "Rebate Schedule";
	public static final String SUMMARY = "Summary";
	public static final String DETAIL = "Detail";
	public static final String PRICE_PROTECTION = "Price Protection";
	public static final String PROCESSING_OPTION = "Processing Options";
	public static final String HEADER = "Header";
	public static final String HISTORY = "History";
	public static final String CURRENT = "Current";
	public static final String PENDING = "Pending";
	public static final String FUTURE = "Future";
	public static final String LEFT = "LEFT";
	public static final String RIGHT = "RIGHT";
	public static final String CONTRACT_TYPE = "CONTRACT_TYPE";
	public static final String NS_FORMULA_TYPE = "NS_FORMULA_TYPE";
	public static final String REBATE_PLAN_TYPE = "REBATE_PLAN_TYPE";
	public static final String DEDUCTION_CALENDAR_CATEGORY = "DEDUCTION_CALENDAR_CATEGORY";
	public static final String CFP_TYPE = "CFP_TYPE";
	public static final String IFP_TYPE = "IFP_TYPE";
	public static final String COMP_TYPE = "COMPANY_TYPE";
	public static final String PS_TYPE = "PS_TYPE";
	public static final String RS_TYPE = "RS_TYPE";
	public static final String THERAPEUTIC_CLASS = "THERAPEUTIC_CLASS";
	public static final String FORM = "FORM";
	public static final String STRENGTH = "STRENGTH";
	public static final String RS_UDC_2 = "RS_UDC2";
	public static final String BRAND_NAME = "Brands";
	public static final String SEARCH_ERROR = "Search Error";
	public static final String SEARCH_MESSAGE = "Please enter Search Criteria";
	public static final String TEXT = "Text";
	public static final String COMBO = "Combo";
	public static final String DATE = "Date";
	public static final String MEMBER_ID = "memberId";
	public static final String MEMBER_NO = "memberNo";
	public static final String MEMBER_NAME = "memberName";
	public static final String MEMBER_TYPE = "memberType";
	public static final String ENABLE = "Enable";
	public static final String DISABLE = "Disable";
	public static final String CFP_CATEGORY = "CFP_CATEGORY";
	public static final String CFP_TRADE_CLASS = "CFP_TRADE_CLASS";
	public static final String CFP_DESIGNATION = "CFP_DESIGNATION";
	public static final String LOCKED_STATUS = "LOCKED_STATUS";
	public static final String IFP_CATEGORY = "IFP_CATEGORY";
	public static final String IFP_DESIGNATION = "IFP_DESIGNATION";
	public static final String PS_CATEGORY = "PS_CATEGORY";
	public static final String PS_TRADE_CLASS = "PS_TRADE_CLASS";
	public static final String PS_DESIGNATION = "PS_DESIGNATION";

	public static final String RS_CATEGORY = "RS_CATEGORY";
	public static final String RS_TRADE_CLASS = "RS_TRADE_CLASS";
	public static final String RS_DESIGNATION = "RS_DESIGNATION";

	public static final String RS_UDC1 = "RS_UDC1";
	public static final String RS_UDC2 = RS_UDC_2;
	public static final String RS_UDC3 = "RS_UDC3";
	public static final String RS_UDC4 = "RS_UDC4";
	public static final String RS_UDC5 = "RS_UDC5";
	public static final String RS_UDC6 = "RS_UDC6";
	public static final String REBATE_PROGRAM_TYPE = "REBATE_PROGRAM_TYPE";
	public static final String PAYMENT_METHOD = "PAYMENT_METHOD";
	public static final String REBATE_FREQUENCY = "REBATE_FREQUENCY";
	public static final String PAYMENT_FREQUENCY = "PAYMENT_FREQUENCY";
	public static final String RS_CALENDAR = "RS_CALENDAR";
	public static final String RS_CONTRACT = "RS_CONTRACT";

	public static final String PAYMENT_LEVEL = "PAYMENT_LEVEL";
	public static final String INTEREST_BEARING_INDICATOR = "INTEREST_BEARING_INDICATOR";
	public static final String INTEREST_BEARING_BASIS = "INTEREST_BEARING_BASIS";
	public static final String REBATE_RULE_TYPE = "REBATE_RULE_TYPE";
	public static final String RS_VALIDATION_PROFILE = "RS_VALIDATION_PROFILE";
	public static final String REBATE_PROCESSING_TYPE = "REBATE_PROCESSING_TYPE";

	public static final String RULE_LEVEL = "RULE_LEVEL";
	public static final String CALCULATION_TYPE = "CALCULATION_TYPE";
	public static final String CALCULATION_LEVEL = "CALCULATION_LEVEL";
	public static final String EVALUATION_RULE_TYPE = "EVALUATION_RULE_TYPE";
	public static final String RULE_TYPE = "RULE_TYPE";
	public static final String FORMULA_TYPE = "FORMULA_TYPE";
	public static final String RULE_CATEGORY = "RULE_CATEGORY";
	public static final String STATUS = "STATUS";
	public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";
	public static final String DOCUMENT_CLASS = "DOCUMENT_CLASS";
	public static final String CONTRACT_TRADE_CLASS = "CONTRACT_TRADE_CLASS";
	public static final String COMPANY_MENUFACTURER = "CompanyManufacture";
	public static final String PRICE_RESET_INDICATOR = "PRICE_RESET_INDICATOR";
	public static final String PAYMENT_TERMS = "PAYMENT_TERMS";
	public static final String CONTRACT_ALIAS_TYPE = "CONTRACT_ALIAS_TYPE";
	private static final Object[] ITEM_ADDITION_COLUMN = new Object[] { "itemNo", "itemName", "itemDesc", "itemStatus",
			"form", "strength", "therapeuticClass", "brand" };
	private static final String[] ITEM_ADDITION_HEADER = new String[] { "Item No", "Item Name", "Item Desc",
			"Item Status", "Form", "Strength", "Therapy Class", "Brand" };
	private static final Class<?>[] ITEM__ADDITION_COLUMN_TYPE = { String.class, String.class, String.class,
			String.class, String.class, String.class, String.class, String.class };
	private static final Object[] COMPANY_ADDITION_COLUMN = new Object[] { "companyId", "companyNo", "companyName",
			"companyStatus", "companyType", "tradeClass", "companyCategory", "companyGroup" };
	private static final String[] COMPANY_ADDITION_HEADER = new String[] { "Company ID", "Company No", "Company Name",
			"Status", "Company Type", "Trade Class", "Company Category", "Company Group" };
	private static final Class<?>[] COMPANY_ADDITION_COLUMN_TYPE = { String.class, String.class, String.class,
			String.class, String.class, String.class, String.class, String.class };
	private static final List<String> COMPANY_ADDITION_SEARCH_FIELD_VALUES = Arrays.asList("Company ID", "Company Name",
			"Company No", "Company Status", "Company Type");

	private static final Map<String, String> COMPONENT_VALUE_MAP = new HashMap<>();
	public static final String MMDDYYYY = "MM/dd/yyyy";
	public static final String CFP = "CFP";
	public static final String IFP = "IFP";
	public static final String PS = "PS";
	public static final String RS = "RS";
	public static final String ITEM = "Item";
	public static final String CONTRACT_SUBMIT_BTN = "contractSubmitBtn";
	public static final String CONTRACT_BACK_BTN = "contractBackBtn";
	public static final String CONTRACT_CLOSE_BTN = "contractCloseBtn";
	public static final String CONTRACT_WITHDRAW_BTN = "contractWithdrawBtn";
	public static final String CONTRACT_APPROVE_BTN = "contractApproveBtn";
	public static final String CONTRACT_REJECT_BTN = "contractRejectBtn";
	public static final String CONTRACT_CANCEL_BTN = "contractCancelBtn";
	public static final String CONTRACT_DASHBOARD_TREE = "cdTree";

	public static String getComponentMappedValue(String value) {
		if (COMPONENT_VALUE_MAP.isEmpty()) {
			COMPONENT_VALUE_MAP.put(CONTRACT, "1");
			COMPONENT_VALUE_MAP.put(COMPANY_FAMILY_PLAN, "2");
			COMPONENT_VALUE_MAP.put(ITEM_FAMILY_PLAN, "3");
			COMPONENT_VALUE_MAP.put(PRICE_SCHEDULE, "4");
			COMPONENT_VALUE_MAP.put(REBATE_SCHEDULE, "5");
			COMPONENT_VALUE_MAP.put(SUMMARY, "S");
			COMPONENT_VALUE_MAP.put(DETAIL, "D");
			COMPONENT_VALUE_MAP.put(RIGHT, "R");
			COMPONENT_VALUE_MAP.put(LEFT, "L");
		}
		return COMPONENT_VALUE_MAP.get(value);
	}

	private static final Map<String, String> COMPONENT_SESSION_VAR_MAP = new HashMap<>();

	public static String getComponentSessionVariable(String value) {
		if (COMPONENT_SESSION_VAR_MAP.isEmpty()) {
			COMPONENT_SESSION_VAR_MAP.put("1", CONTRACT);
			COMPONENT_SESSION_VAR_MAP.put("2", CFP);
			COMPONENT_SESSION_VAR_MAP.put("3", IFP);
			COMPONENT_SESSION_VAR_MAP.put("4", PS);
			COMPONENT_SESSION_VAR_MAP.put("5", RS);
		}
		return COMPONENT_SESSION_VAR_MAP.get(value);
	}

	private static final Map<String, List<String>> ITEM_ADDITION_FIELD_VALUE_MAP = new HashMap<>();

	public static List<String> getItemAdditionMappedFieldValue(String value) {
		if (ITEM_ADDITION_FIELD_VALUE_MAP.isEmpty()) {
			ITEM_ADDITION_FIELD_VALUE_MAP.put(IFP, Arrays.asList("IFP No", "IFP Name"));
			ITEM_ADDITION_FIELD_VALUE_MAP.put(ITEM, Arrays.asList("Brand Name", "Form", "Item Description", "Item Name",
					"Item No", "NDC 8", "NDC 9", "Strength", "Therapeutic Class"));
		}
		return ITEM_ADDITION_FIELD_VALUE_MAP.get(value);
	}

	public static final String PRICE_TOLERANCE_TYPE = "PRICE_TOLERANCE_TYPE";
	public static final String PRICE_TOLERANCE_INTERVAL = "PRICE_TOLERANCE_INTERVAL";
	public static final String PRICE_TOLERANCE_FRERQUENCY = "PRICE_TOLERANCE_FREQUENCY";
	public static final String RESET_TYPE = "RESET_TYPE";
	public static final String PRICE_CODE_QUALIFIER_NAME = "pricingCodeQualifierName";
	public static final String BASE_PRICE_TYPE = "BASE_PRICE_TYPE";
	public static final String SUBMIT_CONFIRM_MESSAGE = "Are you sure you want to submit the Contract for approval?";
	public static final String SUBMIT_FAIL_MESSAGE = "The Contract cannot be submitted for approval.  Not all required fields have been completed.";
	public static final String AUTHENTICATION_ERROR = "Authentication Error";
	public static final String AUTHENTICATION_ERROR_SUBMIT_MESSAGE = "User dont have persmission to Submit";

	public static Object[] getItemAdditionColumn() {
		return ITEM_ADDITION_COLUMN.clone();
	}

	public static String[] getItemAdditionHeader() {
		return ITEM_ADDITION_HEADER.clone();
	}

	public static Class<?>[] getItemAdditionColumnType() {
		return ITEM__ADDITION_COLUMN_TYPE.clone();
	}

	public static Object[] getCompanyAdditionColumn() {
		return COMPANY_ADDITION_COLUMN.clone();
	}

	public static String[] getCompanyAdditionHeader() {
		return COMPANY_ADDITION_HEADER.clone();
	}

	public static Class<?>[] getCompanyAdditionColumnType() {
		return COMPANY_ADDITION_COLUMN_TYPE.clone();
	}

	public static List<String> getCompanyAdditionSearchFieldValues() {
		return Collections.unmodifiableList(COMPANY_ADDITION_SEARCH_FIELD_VALUES);
	}
}
