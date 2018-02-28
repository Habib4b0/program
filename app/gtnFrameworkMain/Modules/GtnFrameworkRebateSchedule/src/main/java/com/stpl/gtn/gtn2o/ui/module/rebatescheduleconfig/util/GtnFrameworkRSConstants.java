package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkRSConstants {


	public static final String DEDUCTION_CALENDAR_NO = "Deduction Calendar No";
	public static final String FORMULA_NO2 = "Formula No";
	public static final String REBATE_PLAN_NO2 = "Rebate Plan No";
	public static final String BUNDLE_NO = "Bundle No";
	public static final String NET_SALES_RULE = "Net Sales Rule";
	public static final String NET_SALES_FORMULA = "Net Sales Formula";
	public static final String FORMULA_NO = "formulaNo";
	public static final String RS_BUNDLE_NO = "rsBundleNo";
	public static final String DESCRIPTION = "description";

	private GtnFrameworkRSConstants() {
		/**
		 * empty constructor
		 */
	}

	public static final String CFP_COMPANY_ADDITION_REBATE_PROCESSING_TYPE = "CFPCompanyAdditionRebateProcessingType";
	public static final String CFP_COMPANY_ADDITION_VALIDATION_PROFILE = "CFPCompanyAdditionValidationProfile";
	public static final String CFP_COMPANY_ADDITION_INTEREST_BEARING_BASIS = "CFPCompanyAdditionInterestBearingBasis";
	public static final String CFP_COMPANY_ADDITION_INTEREST_BEARING_INDICATOR = "CFPCompanyAdditionInterestBearingIndicator";
	public static final String CFP_COMPANY_ADDITION_MOVE_RIGHT_BUTTON_INDICATOR = "CFPcompanyAdditionmoverightButtonLayout";
	public static final String CFP_COMPANY_ADDITION_MOVE_LEFT_BUTTON_INDICATOR = "CFPcompanyAdditionmoveLeftButtons";
	public static final String CFP_COMPANY_ADDITION_MOVE_ALL_RIGHT_INDICATOR = "CFPcompanyAdditionmoveAllrightButtons";
	public static final String CFP_COMPANY_ADDITION_MOVE_ALL_LEFT_INDICATOR = "CFPcompanyAdditionmoveAllLeftButtonsLayout";
	public static final String RSIS_RECORD_SELECTED = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameworkRSValidationActionIsRecordSelected";
	public static final String MANDATERY_ALERT = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation.GtnUIFrameWorkRSSaveRebateSetupTabMandatoryAlert";
	public static final String TABLECHECK_ACTION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRSRebateSetUpTabTableCheckAction";
	public static final String MASS_FIELD_VALUE_CHANGE = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRebateSetupMassFieldValueChangeAction";
	public static final String FIELD_VALUE_CHANGE = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRSItemAdditionFieldValueChangeAction";
	public static final String DELETE_RSACTION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIDeleteRSAction";
	public static final String ADD_DATA_TABLE = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkAddDataTableAction";
	public static final String CALC_TYPE_CHANGE_ACTION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkCalculationTypeChangeAction";
	public static final String DESIG_TYPE_CHANGE_ACTION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkDesignationTypeChangeAction";
	public static final String RS_LOAD_ACTION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkRSLoadAction";
	public static final String RS_SAVE_ACTION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkRSSaveAction";
	public static final String SET_SELECTION_CONFIG = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkSetSelectionConfigAction";
	public static final String TRIGGER_DATA_TABLE = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkTriggerDataTablesAction";
	public static final String RS_DELETE_CONFIRMATION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameworkRSDeleteConfirmationAction";
	public static final String RS_SAVE_CONFIRMATION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameworkRSSaveConfirmationAction";
	public static final String RS_POPULATE_ACTION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameworkRSPopulateAction";
	public static final String SAVE_MANDATERY_ALERT = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation.GtnUIFrameWorkRSSaveMandatoryAlert";

	public static final String RS_POPUP_VIEW = "RSPopUpView";
	public static final String RS_NS_RULE_VIEW = "RSNSRuleView";
	public static final String RS_STATUS = "rsStatus";

	private static final String[] RS_LOOKUP_COLUMN = { "systemId", "rsId", "rsNo", "rsName", "rsType", RS_STATUS };
	private static final String[] RS_LOOKUP_HEADER = { "System ID", "Rebate Schedule ID", "Rebate Schedule No",
			"Rebate Schedule Name", "Rebate Schedule Type", "Rebate Schedule Status" };
	private static final Class<?>[] RS_LOOKUP_COLUMN_TYPE = { String.class, String.class, String.class, String.class,
			String.class, String.class };

	private static final String[] RULE_LOOKUP_COLUMN = { "ruleType", "ruleNo", "ruleName", "ruleCategory" };
	private static final String[] RULE_LOOKUP_HEADER = { "Rule Type", "Rule No", "Rule Name", "Rule Category" };

	private static final Class<?>[] RULE_LOOKUP_COLUMN_TYPE = { String.class, String.class, String.class,
			String.class };

	private static final String[] RULE_DETAILS_LOOKUP_COLUMN = { "lineType", "itemGroupAsso", "keyword", "keyOperator",
			"value", "comparison", "compOperator" };

	private static final String[] RULE_DETAILS_LOOKUP_HEADER = { "Line Type", "Item/Group/Association", "Keyword",
			"Operator", "Value", "Comparison", "Operator" };

	private static final Class<?>[] RULE_DETAILS_LOOKUP_COLUMN_TYPE = { String.class, String.class, String.class,
			String.class, String.class, String.class, String.class };

	public static final String RS_START_DATE = "rsStartDate";
	public static final String RS_END_DATE1 = "rsEndDate";

	private static final List<String> DATE_FIELD_PROPERTIES = Arrays.asList(RS_START_DATE, RS_END_DATE1);

	public static final String FORMULA_NAME = "formulaName";

	private static final List<String> NON_EIDTABLE_LIST = Arrays.asList(FORMULA_NAME);

	public static final String DEDUCTION_NO = "deductionNo";
	public static final String EVALUATION_RULE_BUNDLE1 = "evaluationRuleBundle";
	public static final String CALCULATION_RULE_BUNDLE1 = "calculationRuleBundle";
	private static final List<String> TEXT_FIELD_PROPERTIES = Arrays.asList(GtnFrameworkCommonConstants.ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME, "deductionName", EVALUATION_RULE_BUNDLE1, CALCULATION_RULE_BUNDLE1,
			FORMULA_NAME, RS_BUNDLE_NO);
	private static final String NET_SALES_FORMULA_NAME = "netSalesFormulaName";
	public static final String NET_SALES_RULE_NAME = "netSalesRuleName";
	public static final String EVALUATION_RULE_NAME = "evaluationRuleName";
	public static final String CALCULATION_RULE_NAME = "calculationRuleName";
	public static final String REBATE_PLAN_NO1 = "rebatePlanNo";

	private static final List<String> POP_UP_TEXT_FIELD_PROPERTIES = Arrays.asList(DEDUCTION_NO, EVALUATION_RULE_NAME,
			CALCULATION_RULE_NAME, NET_SALES_RULE_NAME, FORMULA_NO, NET_SALES_FORMULA_NAME, REBATE_PLAN_NO1);
	public static final String REBATE_SETUP_FIELD_FACTORY_ACTION = "com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRSTableFieldFactoryFieldUpdateAction";
	public static final String EVALUATION_RULE_BUNDLE = "Evaluation Rule Bundle";
	public static final String CALCULATION_RULE_BUNDLE = "Calculation Rule bundle";
	public static final String EVALUATION_RULE = "Evaluation Rule";
	public static final String RS_STATUS1 = "RS Status";
	public static final String ATTACHED_DATE = "Attached Date";
	private static final String ITEM_NO = "Item NO";
	public static final String RS_END_DATE = "RS End Date";
	public static final String CALCULATION_RULE1 = "Calculation Rule";
	public static final String RS_START_DATE1 = "RS Start Date";

	private static final String[] RS_SETUP_TAB_CALCULATION_TYPE_REBATE_PLAN_TABLEHEADERS = { "", ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME_HEADER, RS_STATUS1, RS_START_DATE1, RS_END_DATE, BUNDLE_NO,
			REBATE_PLAN_NO2, "Rebate Plan Name", NET_SALES_FORMULA, NET_SALES_RULE, EVALUATION_RULE,
			EVALUATION_RULE_BUNDLE, CALCULATION_RULE1, CALCULATION_RULE_BUNDLE, ATTACHED_DATE };

	public static final String ATTACHED_DATE1 = "attachedDate";

	private static final Object[] RS_SETUP_TAB_CALCULATION_TYPE_REBATE_PLAN_VISIBLECOLUMNS = {
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME, RS_STATUS, RS_START_DATE, RS_END_DATE1, RS_BUNDLE_NO,
			REBATE_PLAN_NO1, "rebatePlanName", NET_SALES_FORMULA_NAME, NET_SALES_RULE_NAME, EVALUATION_RULE_NAME,
			EVALUATION_RULE_BUNDLE1, CALCULATION_RULE_NAME, CALCULATION_RULE_BUNDLE1, ATTACHED_DATE1 };

	private static final String[] RS_SETUP_TAB_CALCULATION_TYPE_FORMULA_TABLEHEADERS = { " ", ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME_HEADER, RS_STATUS1, RS_START_DATE1, RS_END_DATE, "Formula Type",
			FORMULA_NO2, "Formula Name", NET_SALES_FORMULA, NET_SALES_RULE, EVALUATION_RULE, EVALUATION_RULE_BUNDLE,
			CALCULATION_RULE1, CALCULATION_RULE_BUNDLE, ATTACHED_DATE };

	private static final Object[] RS_SETUP_TAB_CALCULATION_TYPE_FORMULA_VISIBLECOLUMNS = {
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME, RS_STATUS, RS_START_DATE, RS_END_DATE1, "formulaType", FORMULA_NO,
			FORMULA_NAME, NET_SALES_FORMULA_NAME, NET_SALES_RULE_NAME, EVALUATION_RULE_NAME, EVALUATION_RULE_BUNDLE1,
			CALCULATION_RULE_NAME, CALCULATION_RULE_BUNDLE1, ATTACHED_DATE1 };

	private static final String[] RS_SETUP_TAB_CALCULATION_TYPE_DEDUCTION_CALENDER_TABLEHEADERS = { " ", ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME_HEADER, RS_STATUS1, RS_START_DATE1, RS_END_DATE,
			DEDUCTION_CALENDAR_NO, "Deduction Calendar Name", EVALUATION_RULE, EVALUATION_RULE_BUNDLE,
			CALCULATION_RULE1, CALCULATION_RULE_BUNDLE, ATTACHED_DATE };

	private static final String[] RS_SETUP_TAB_CALCULATION_TYPE_PRICE_PROTECTION_TABLEHEADERS = { " ",
			GtnFrameworkCommonConstants.ITEM_NO_HEADER, GtnFrameworkCommonConstants.ITEM_NAME_HEADER,
			GtnFrameworkRSConstants.RS_STATUS1, GtnFrameworkRSConstants.RS_START_DATE1,
			GtnFrameworkRSConstants.RS_END_DATE };

	private static final Object[] RS_SETUP_TAB_CALCULATION_TYPE_PRICE_PROTECTION_VISIBLECOLUMNS = {
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME, RS_STATUS, GtnFrameworkRSConstants.RS_START_DATE,
			GtnFrameworkRSConstants.RS_END_DATE1 };

	private static final Object[] RS_SETUP_TAB_CALCULATION_TYPE_DEDUCTION_CALENDER_VISIBLECOLUMNS = {
			GtnFrameworkCommonConstants.CHECK_RECORD_ID, GtnFrameworkCommonConstants.ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME, RS_STATUS, RS_START_DATE, RS_END_DATE1, "deductionName",
			DEDUCTION_NO, EVALUATION_RULE_NAME, EVALUATION_RULE_BUNDLE1, CALCULATION_RULE_NAME,
			CALCULATION_RULE_BUNDLE1, ATTACHED_DATE1 };

	private static final String[] RS_SETUP_TAB_TABLE_HEADERS = { "", GtnFrameworkCommonConstants.ITEM_NO_HEADER,
			GtnFrameworkCommonConstants.ITEM_NAME_HEADER, RS_STATUS1, GtnFrameworkRSConstants.RS_START_DATE1,
			GtnFrameworkRSConstants.RS_END_DATE, RS_STATUS1 };

	private static final Object[] RS_SETUP_TAB_VISIBLE_COLUMNS = { GtnFrameworkCommonConstants.CHECK_RECORD_ID,
			"itemNo", "itemName", RS_STATUS, RS_START_DATE, RS_END_DATE1, DESCRIPTION };

	private static final Object[] RS_SETUP_TAB_VISIBLE_COLUMNS_VIEW = { "itemNo", "itemName", DESCRIPTION,
			RS_START_DATE, RS_END_DATE1 };

	private static final String[] RS_SETUP_TAB_VISIBLE_HEADERS_VIEW = { GtnFrameworkCommonConstants.ITEM_NO_HEADER,
			GtnFrameworkCommonConstants.ITEM_NAME_HEADER, RS_STATUS1, GtnFrameworkRSConstants.RS_START_DATE1,
			GtnFrameworkRSConstants.RS_END_DATE };

	public static final String DETAILS_RESULT_TABLE = "detailsResultTable";
	public static final String POU_UP_REBATE_SCHEDULE_TYPE = "pouUpRebateScheduleType";
	public static final String REBATE_SCHEDULE_POP_UP_CONFIG = "RebateSchedulePopUpConfig";
	public static final String POU_UP_REBATE_SCHEDULE_STATUS = "pouUpRebateScheduleStatus";
	public static final String REBATE_PROGRAM_TYPE = "rebateProgramType";
	public static final String RS_POPUPACTION_BUTTONLAYOUT = "rsPopupactionButtonlayout";
	public static final String POU_UP_REBATE_SCHEDULE_ID = "pouUpRebateScheduleId";
	public static final String POU_UP_REBATE_SCHEDULE_CATEGORY = "pouUpRebateScheduleCategory";
	public static final String POU_UP_REBATE_SCHEDULE_NAME = "pouUpRebateScheduleName";
	public static final String POU_UP_REBATE_SCHEDULE_NO = "pouUpRebateScheduleNo";
	public static final String IFP_ID = "IFP ID";
	public static final String RS_ITEM_ADDITIONMOVE_BUTTONS_LAYOUT = "RSItemAdditionmoveButtonsLayout";
	public static final String RS_ITEM_ADDITION_PANEL_LAYOUT = "RSItemAdditionPanelLayout";
	public static final String R_SRIGHT_RESULT_TABLE = "RSrightResultTable";
	public static final String RS_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT = "RSItemAdditionDualListBoxLayout";
	public static final String R_SLEFT_RESULT_TABLE = "RSleftResultTable";
	public static final String RS_ITEM_ADDITION_INFORMATION_LAYOUT = "RSItemAdditionInformationLayout";
	public static final String PROCESSING_OPTION_TAB = "processingOptionTab";
	public static final String RS_INFO_PANELLAYOUT = "rsInfoPanellayout";
	public static final String REBATE_SCHEDULE_TABSHEET_LAYOUT = "rebateScheduleTabsheetLayout";
	public static final String PAYMENT_GRACE_PERIOD_LAYOUT = "paymentGracePeriodLayout";
	public static final String RS_UDC3LAYOUT = "rsUDC3layout";
	public static final String EVALUATION_RULE_LEVELLAYOUT = "evaluationRuleLevellayout";
	public static final String RS_UDC1LAYOUT = "rsUDC1layout";
	public static final String RS_UDC5LAYOUT = "rsUDC5layout";
	public static final String RULE_LEVEL = "RULE_LEVEL";
	public static final String REBATE_SCHEDULE_NAME_LAYOUT = "rebateScheduleNameLayout";
	public static final String REBATE_SCHEDULE_NO_LAYOUT = "rebateScheduleNoLayout";
	public static final String PARENT_REBATE_SCHEDULE_NAME_LAYOUT = "parentRebateScheduleNameLayout";
	public static final String CALCULATION_RULELAYOUT = "calculationRulelayout";
	public static final String RS_TRANSACTION_REF_NAME_LAYOUT = "rsTransactionRefNameLayout";
	public static final String CALCULATION_TYPELAYOUT = "calculationTypelayout";
	public static final String REBATE_SCHEDULE_START_DATE_LAYOUT = "rebateScheduleStartDateLayout";
	public static final String EVALUATION_RULE_ASSOCIATIONLAYOUT = "evaluationRuleAssociationlayout";
	public static final String CALCULATION_RULE_LEVELLAYOUT = "calculationRuleLevellayout";
	public static final String RS_UDC4LAYOUT = "rsUDC4layout";
	public static final String PAYMENT_TERMSLAYOUT = "paymentTermslayout";
	public static final String REBATE_SCHEDULE_INFO_TAB = "rebateScheduleInfoTab";
	public static final String REBATE_PROGRAM_TYPELAYOUT = "rebateProgramTypelayout";
	public static final String RS_DEDUCTION_INCLUSIONLAYOUT = "rsDeductionInclusionlayout";
	public static final String REBATE_SCHEDULE_TRADE_CLASSLAYOUT = "rebateScheduleTradeClasslayout";
	public static final String REBATE_SCHEDULE_CATEGORYLAYOUT = "rebateScheduleCategorylayout";
	public static final String REBATE_SCHEDULE_TYPELAYOUT = "rebateScheduleTypelayout";
	public static final String REBATE_SCHEDULE_END_DATE_LAYOUT = "rebateScheduleEndDateLayout";
	public static final String REBATE_FREQUENCYLAYOUT = "rebateFrequencylayout";
	public static final String REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT = "rebateScheduleInfoInformationLayout";
	public static final String PAYMENT_LEVEL_LAYOUT = "paymentLevellayout";
	public static final String REBATE_SCHEDULE_STATUSLAYOUT = "rebateScheduleStatuslayout";
	public static final String REBATE_SCHEDULE_ALIAS_ID_LAYOUT = "rebateScheduleAliasIdLayout";
	public static final String INTEREST_BEARING_BASISLAYOUT = "interestBearingBasislayout";
	public static final String PAYMENT_FREQUENCYLAYOUT = "paymentFrequencylayout";
	public static final String REBATE_RULE_TYPELAYOUT = "rebateRuleTypelayout";
	public static final String REBATE_SCHEDULE_ID_LAYOUT = "rebateScheduleIdLayout";
	public static final String RS_CALENDARLAYOUT = "rsCalendarlayout";
	public static final String RS_UDC2LAYOUT = "rsUDC2layout";
	public static final String EVALUATION_RULE_TYPELAYOUT = "evaluationRuleTypelayout";
	public static final String RS_UDC6LAYOUT = "rsUDC6layout";
	public static final String CALCULATION_LEVELLAYOUT = "calculationLevellayout";
	public static final String REBATE_OPTIONS_LAYOUT = "rebateOptionsLayout";
	public static final String INTEREST_BEARING_INDICATORLAYOUT = "interestBearingIndicatorlayout";
	public static final String VALUE_CHANGE_ALLOWED = "ValueChangeAllowed";
	public static final String MASS_CUSTOM_TEXT_FIELD = "massCustomTextField";
	public static final String DEDUCTION_CALENDAR_NAME = "deductionCalendarName";
	public static final String PAYMENT_METHODLAYOUT = "paymentMethodlayout";
	public static final String REBATE_SCHEDULE_END_DATE = "rebateScheduleEndDate";
	public static final String RS_UDC6 = "rsUDC6";
	public static final String REBATE_SCHEDULE_STATUS = "rebateScheduleStatus";
	public static final String EVALUATION_RULE_TYPE = "evaluationRuleType";
	public static final String CALCULATION_RULE = "calculationRule";
	public static final String REBATE_SCHEDULE_START_DATE = "rebateScheduleStartDate";
	public static final String REBATE_SCHEDULE_NO = "rebateScheduleNo";
	public static final String REBATE_RULE_TYPE = "rebateRuleType";
	public static final String REBATE_SCHEDULE_NAME1 = "rebateScheduleName1";
	public static final String REBATE_SCHEDULE_CATEGORY = "rebateScheduleCategory";
	public static final String REBATE_SCHEDULE_FREQUENCY = "rebateScheduleFrequency";
	public static final String RS_ITEM_ADDITIONMOVE_LEFT_BUTTONS = "RSItemAdditionmoveLeftButtons";
	public static final String RS_ITEM_ADDITIONMOVERIGHT_BUTTONS = "RSItemAdditionmoverightButtons";
	public static final String RS_UDC5 = "rsUDC5";
	public static final String PARENT_REBATE_SCHEDULE_NAME = "parentRebateScheduleName";
	public static final String RS_SEARCH_RESULT_TABLE = "rsSearchResultTable";
	public static final String CALCULATION_TYPE1 = "calculationType1";
	public static final String REBATE_SCHEDULE_CATEGORY1 = "rebateScheduleCategory1";
	public static final String REBATE_SCHEDULE_ID1 = "rebateScheduleId1";
	public static final String RS_CALENDAR = "rsCalendar";
	public static final String RS_DEDUCTION_INCLUSION = "rsDeductionInclusion";
	public static final String CALCULATION_RULE_LEVEL = "calculationRuleLevel";
	public static final String REBATE_SCHEDULE_NO1 = "rebateScheduleNo1";
	public static final String RS_UDC4 = "rsUDC4";
	public static final String REBATE_SCHEDULE_TYPE1 = "rebateScheduleType1";
	public static final String REBATE_SCHEDULE_NAME = "rebateScheduleName";
	public static final String RS_TRANSACTION_REF_ID = "rsTransactionRefId";
	public static final String REBATE_FREQUENCY1 = "rebateFrequency1";
	public static final String RS_UDC3 = "rsUDC3";
	public static final String PAYMENT_TERMS = "paymentTerms";
	public static final String PAYMENT_GRACE_PERIOD = "paymentGracePeriod";
	public static final String REBATE_SCHEDULE_TYPE = "rebateScheduleType";
	public static final String PAYMENT_METHOD = "paymentMethod";
	public static final String EVALUATION_RULE_LEVEL = "evaluationRuleLevel";
	public static final String REBATE_SCHEDULE_ADD_VIEW_A_ADD_RESET_BUTTON = "rebateScheduleAddViewAAddResetButton";
	public static final String REBATE_CALCULATION_TYPE = "rebateCalculationType";
	public static final String REBATE_SCHEDULE_ADD_VIEW = "rebateScheduleAddView";
	public static final String RS_UDC2 = "rsUDC2";
	public static final String RS_ADD_SAVE_BUTTON = "rsAddSaveButton";
	public static final String REBATE_PROGRAM_TYPE1 = "rebateProgramType1";
	public static final String REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT = "rebateSetupmassUpdatePanelLayout";
	public static final String PARENT_REBATE_SCHEDULE_ID = "parentRebateScheduleID";
	public static final String RS_UDC1 = "rsUDC1";
	public static final String PRICE_SCHEDULE_ADD_VIEW_A_ADD_DELETE_BUTTON = "priceScheduleAddViewAAddDeleteButton";
	public static final String CALCULATION_LEVEL = "calculationLevel";
	public static final String REBATE_SCHEDULE_ALIAS_ID = "rebateScheduleAliasId";
	public static final String EVALUATION_RULE_ASSOCIATION = "evaluationRuleAssociation";
	public static final String INTEREST_BEARING_INDICATOR = "interestBearingIndicator";
	public static final String INTEREST_BEARING_BASIS = "interestBearingBasis";
	public static final String PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE = "psRebateSetupTabResultDataTable";
	public static final String REBATE_SCHEDULE_TRADE_CLASS = "rebateScheduleTradeClass";
	public static final String RS_TRANSACTION_REF_NAME = "rsTransactionRefName";
	public static final String REBATE_SCHEDULE_STATUS1 = "rebateScheduleStatus1";
	public static final String PAYMENT_LEVEL = "paymentLevel";
	public static final String PAYMENT_FREQUENCY = "paymentFrequency";
	public static final String SYS_ID = "SysId";
	public static final String END_DATE = "End Date";
	public static final String CALCULATION_RULE_BUNDLE2 = "Calculation Rule Bundle";
	public static final String START_DATE = "Start Date";
	public static final String REBATE_SCHEDULE_ALIAS_ID1 = "rebateScheduleAliasId1";
	public static final String REBATE_SCHEDULE_DESIGNATION = "rebateScheduleDesignation";
	public static final String REBATE_SETUP_TAB_MASS_DROP_DOWN = "rebateSetupTabMassDropDown";
	public static final String REBATE_SETUP_TAB_POPULATE_BUTTON = "rebateSetupTabPopulateButton";
	public static final String REBATE_SETUP_TAB_POPULATE_ALL_BUTTON = "rebateSetupTabPopulateAllButton";
	public static final String REBATE_SETUP_TAB_RECORD_TYPE = "rebateSetupTabRecordType";
	public static final String REBATE_SETUP_TAB_MASS_DATE_FEILD = "rebateSetupTabMassDateFeild";
	public static final String REBATE_SETUP_TAB = "RebateSetupTab";
	public static final String RS_ITEM_ADDITION_SEARCH_FIELD = "RSItemAdditionSearchField";
	public static final String RS_ITEM_ADDITION_SEARCH_VALUE_TEXT = "RSItemAdditionSearchValueText";
	public static final String RS_ITEM_ADDITION_SEARCH_VALUE_STATUS_DROP_DOWN = "RSItemAdditionSearchValueStatusDropDown";
	public static final String RS_ITEM_ADDITION_SEARCH_VALUE_TYPE_DROP_DOWN = "RSItemAdditionSearchValueTypeDropDown";
	public static final String RS_ITEM_ADDITION_SEARCH_VALUE_DATE = "RSItemAdditionSearchValueDate";
	public static final String RS_ITEM_ADDITION_SEARCH_VALUE_CATEGORY_DROP_DOWN = "RSItemAdditionSearchValueCategoryDropDown";
	public static final String REBATE_SCHEDULE_ID = "rebateScheduleId";
	public static final String REBATE_PLAN_NO = REBATE_PLAN_NO1;

	public static final String RS_NS_RULE_VIEW_SELECT_BUTTON = "RSNSRuleView_selectBtn";
	public static final String RS_NS_RULE_VIEW_RESULT_TABLE = "RSNSRuleView_"
			+ GtnFrameworkCommonConstants.RESULT_TABLE;
	public static final String TEMP_CHECKED_COUNT = "tempCheckedCount";

	public static String[] getRsLookUpColumn() {
		return RS_LOOKUP_COLUMN.clone();
	}

	public static String[] getRsLookUpHeader() {
		return RS_LOOKUP_HEADER.clone();
	}

	public static Class<?>[] getRsLookUpColumnType() {
		return RS_LOOKUP_COLUMN_TYPE.clone();
	}

	public static String[] getRuleDetailsLookUpColumn() {
		return RULE_DETAILS_LOOKUP_COLUMN.clone();
	}

	public static String[] getRuleLookUpColumn() {
		return RULE_LOOKUP_COLUMN.clone();
	}

	public static String[] getRuleLookUpHeader() {
		return RULE_LOOKUP_HEADER.clone();
	}

	public static Class<?>[] getruleLookUpColumnType() {
		return RULE_LOOKUP_COLUMN_TYPE.clone();
	}

	public static String[] getRuleDetailsLookUpHeader() {
		return RULE_DETAILS_LOOKUP_HEADER.clone();
	}

	public static Class<?>[] getRuleDetailsLookUpColumnType() {
		return RULE_DETAILS_LOOKUP_COLUMN_TYPE.clone();
	}

	public static Object[] getRsSetUpTabCalculationTypeDeductionCalendarVisibleColumns() {
		return RS_SETUP_TAB_CALCULATION_TYPE_DEDUCTION_CALENDER_VISIBLECOLUMNS.clone();
	}

	public static Object[] getRsSetUpTabCalculationTypePriceProtectionVisibleColumns() {
		return RS_SETUP_TAB_CALCULATION_TYPE_PRICE_PROTECTION_VISIBLECOLUMNS.clone();
	}

	public static List<String> getNonEditableList() {
		return Collections.unmodifiableList(NON_EIDTABLE_LIST);
	}

	public static List<String> getDateFieldProperties() {
		return Collections.unmodifiableList(DATE_FIELD_PROPERTIES);
	}

	public static String[] getRsSetUpTabCalculationTypeDeductionCalendarTableHeaders() {
		return RS_SETUP_TAB_CALCULATION_TYPE_DEDUCTION_CALENDER_TABLEHEADERS.clone();
	}

	public static String[] getRsSetUpTabCalculationTypePriceProtectionTableHeaders() {
		return RS_SETUP_TAB_CALCULATION_TYPE_PRICE_PROTECTION_TABLEHEADERS.clone();
	}

	public static Object[] getRsSetUPTabCalculationTypeFormulaVisibleColumns() {
		return RS_SETUP_TAB_CALCULATION_TYPE_FORMULA_VISIBLECOLUMNS.clone();
	}

	public static List<String> getTextFieldProperties() {
		return Collections.unmodifiableList(TEXT_FIELD_PROPERTIES);
	}

	public static List<String> getPopUpTextFieldProperties() {
		return Collections.unmodifiableList(POP_UP_TEXT_FIELD_PROPERTIES);
	}

	public static String[] getRsSetUpTabCalculationTypeFormulaTableHeaders() {
		return RS_SETUP_TAB_CALCULATION_TYPE_FORMULA_TABLEHEADERS.clone();
	}

	public static Object[] getRsSetUPTabCalculationTypeRebatePlanVisibleColumns() {
		return RS_SETUP_TAB_CALCULATION_TYPE_REBATE_PLAN_VISIBLECOLUMNS.clone();
	}

	public static String[] getRsSetUpTabCalculationTypeRebatePlanTableHeaders() {
		return RS_SETUP_TAB_CALCULATION_TYPE_REBATE_PLAN_TABLEHEADERS.clone();
	}

	public static String[] geRsLookUpHeader() {
		return RS_LOOKUP_HEADER.clone();
	}

	public static Object[] getRsSetupTabVisibleColumnsView() {
		return RS_SETUP_TAB_VISIBLE_COLUMNS_VIEW.clone();
	}

	public static String[] getRsSetupTabVisibleHeadersView() {
		return RS_SETUP_TAB_VISIBLE_HEADERS_VIEW.clone();
	}

	public static String[] getRsSetupTabTableHeaders() {
		return RS_SETUP_TAB_TABLE_HEADERS.clone();
	}

	public static Object[] getRsSetupTabVisibleColumns() {
		return RS_SETUP_TAB_VISIBLE_COLUMNS.clone();
	}

	public static String[] getRsSetUpTabCalculationTypeRebatePlanTableHeadersforView() {
		return RS_SETUP_TAB_CALCULATION_TYPE_REBATE_PLAN_TABLEHEADERS_VIEW.clone();
	}

	public static Object[] getRsSetUPTabCalculationTypeRebatePlanVisibleColumnsforView() {
		return RS_SETUP_TAB_CALCULATION_TYPE_REBATE_PLAN_VISIBLECOLUMNS_VIEW.clone();
	}

	public static String[] getRsSetUpTabCalculationTypeFormulaTableHeadersforView() {
		return RS_SETUP_TAB_CALCULATION_TYPE_FORMULA_TABLEHEADERS_VIEW.clone();
	}

	public static Object[] getRsSetUPTabCalculationTypeFormulaVisibleColumnsforView() {
		return RS_SETUP_TAB_CALCULATION_TYPE_FORMULA_VISIBLECOLUMNS_VIEW.clone();
	}

	public static String[] getRsSetUpTabCalculationTypeDeductionCalendarTableHeadersforView() {
		return RS_SETUP_TAB_CALCULATION_TYPE_DEDUCTION_CALENDER_TABLEHEADERS_VIEW.clone();
	}

	public static Object[] getRsSetUpTabCalculationTypeDeductionCalendarVisibleColumnsforView() {
		return RS_SETUP_TAB_CALCULATION_TYPE_DEDUCTION_CALENDER_VISIBLECOLUMNS_VIEW.clone();
	}

	private static final String[] RS_SETUP_TAB_CALCULATION_TYPE_REBATE_PLAN_TABLEHEADERS_VIEW = { ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME_HEADER, RS_STATUS1, RS_START_DATE1, RS_END_DATE, BUNDLE_NO,
			REBATE_PLAN_NO2, "Rebate Plan Name", NET_SALES_FORMULA, NET_SALES_RULE, EVALUATION_RULE,
			EVALUATION_RULE_BUNDLE, CALCULATION_RULE1, CALCULATION_RULE_BUNDLE, ATTACHED_DATE };
	private static final Object[] RS_SETUP_TAB_CALCULATION_TYPE_REBATE_PLAN_VISIBLECOLUMNS_VIEW = {
			GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME, DESCRIPTION, RS_START_DATE,
			RS_END_DATE1, RS_BUNDLE_NO, REBATE_PLAN_NO1, "rebatePlanName", NET_SALES_FORMULA_NAME, NET_SALES_RULE_NAME,
			EVALUATION_RULE_NAME, EVALUATION_RULE_BUNDLE1, CALCULATION_RULE_NAME, CALCULATION_RULE_BUNDLE1,
			ATTACHED_DATE1 };
	private static final String[] RS_SETUP_TAB_CALCULATION_TYPE_FORMULA_TABLEHEADERS_VIEW = { ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME_HEADER, RS_STATUS1, RS_START_DATE1, RS_END_DATE, "Formula Type",
			FORMULA_NO2, "Formula Name", NET_SALES_FORMULA, NET_SALES_RULE, EVALUATION_RULE, EVALUATION_RULE_BUNDLE,
			CALCULATION_RULE1, CALCULATION_RULE_BUNDLE, ATTACHED_DATE };

	private static final Object[] RS_SETUP_TAB_CALCULATION_TYPE_FORMULA_VISIBLECOLUMNS_VIEW = {
			GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME, DESCRIPTION, RS_START_DATE,
			RS_END_DATE1, "formulaType", FORMULA_NO, FORMULA_NAME, NET_SALES_FORMULA_NAME, NET_SALES_RULE_NAME,
			EVALUATION_RULE_NAME, EVALUATION_RULE_BUNDLE1, CALCULATION_RULE_NAME, CALCULATION_RULE_BUNDLE1,
			ATTACHED_DATE1 };
	private static final String[] RS_SETUP_TAB_CALCULATION_TYPE_DEDUCTION_CALENDER_TABLEHEADERS_VIEW = { ITEM_NO,
			GtnFrameworkCommonConstants.ITEM_NAME_HEADER, RS_STATUS1, RS_START_DATE1, RS_END_DATE,
			DEDUCTION_CALENDAR_NO, "Deduction Calendar Name", EVALUATION_RULE, EVALUATION_RULE_BUNDLE,
			CALCULATION_RULE1, CALCULATION_RULE_BUNDLE, ATTACHED_DATE };
	private static final Object[] RS_SETUP_TAB_CALCULATION_TYPE_DEDUCTION_CALENDER_VISIBLECOLUMNS_VIEW = {
			GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME, DESCRIPTION, RS_START_DATE,
			RS_END_DATE1, "deductionName", DEDUCTION_NO, EVALUATION_RULE_NAME, EVALUATION_RULE_BUNDLE1,
			CALCULATION_RULE_NAME, CALCULATION_RULE_BUNDLE1, ATTACHED_DATE1 };

}
