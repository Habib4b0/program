package com.stpl.gtn.gtn2o.ui.module.rebateplan.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkRPConstants;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCDRRules {

	private final GtnFrameworkComponentConfigProvider cdrPopUpConfigProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {

		GtnUIFrameworkViewConfig cdrPopoupView = cdrPopUpConfigProvider.getViewConfig(
				GtnFrameworkStringConstants.CDR_POPUP_SEARCH_VIEW, GtnFrameworkStringConstants.CDR_POPUP_SEARCH_VIEW,
				false);
		addCdrComponentList(cdrPopoupView);
		return cdrPopoupView;
	}

	private void addCdrComponentList(GtnUIFrameworkViewConfig view) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addCdrSearchCriteriaPanel(componentList);
		addCdrButtonLayout(componentList);
		addCdrMainLayout(componentList);
		addCdrResultPanel(componentList);
		addCdrRuleDetailsResultPanel(componentList);
		addCdrActionButtonLayout(componentList);

	}

	private void addCdrMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopUpSearchViewMainCssConfig = cdrPopUpConfigProvider.getGtnCssLayoutConfig(
				GtnFrameworkStringConstants.CDR_POPUP_SEARCH_VIEW_MAIN_CSS_LAYOUT, false, null,
				GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> cdrPopUpSearchViewMainCssCStyleList = new ArrayList<>();
		cdrPopUpSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cdrPopUpSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		cdrPopUpSearchViewMainCssConfig.setComponentStyle(cdrPopUpSearchViewMainCssCStyleList);

		componentList.add(cdrPopUpSearchViewMainCssConfig);

		GtnUIFrameworkComponentConfig cdrSearchViewLeftLayout = cdrPopUpConfigProvider.getGtnCssLayoutConfig(
				"CDRPopUpSearchSearchViewLeftLayout", true,
				GtnFrameworkStringConstants.CDR_POPUP_SEARCH_VIEW_MAIN_CSS_LAYOUT, GtnUIFrameworkLayoutType.CSS_LAYOUT);
		List<String> leftLayoutStyleList = new ArrayList<>();
		leftLayoutStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_5);
		leftLayoutStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		cdrSearchViewLeftLayout.setComponentStyle(leftLayoutStyleList);

		componentList.add(cdrSearchViewLeftLayout);

		GtnUIFrameworkComponentConfig cdrPopUpSearchViewRightLayout = cdrPopUpConfigProvider.getGtnCssLayoutConfig(
				"CDRPopUpSearchSearchViewRightLayout", true,
				GtnFrameworkStringConstants.CDR_POPUP_SEARCH_VIEW_MAIN_CSS_LAYOUT, GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> rightLayoutStyleList = new ArrayList<>();
		rightLayoutStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_7);
		rightLayoutStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		cdrPopUpSearchViewRightLayout.setComponentStyle(rightLayoutStyleList);

		componentList.add(cdrPopUpSearchViewRightLayout);

	}

	private void addCdrSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrSearchCriteriaPanelConfig = cdrPopUpConfigProvider
				.getPanelConfig("searchCriteriaPanel", false, null);
		cdrSearchCriteriaPanelConfig.setAuthorizationIncluded(true);
		cdrSearchCriteriaPanelConfig.setComponentName("Search Criteria");
		componentList.add(cdrSearchCriteriaPanelConfig);

		addCdrFieldLayout(componentList);
	}

	private void addCdrResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrSearchResultConfig = cdrPopUpConfigProvider.getPanelConfig("resultPanel", true,
				"CDRPopUpSearchSearchViewLeftLayout");
		cdrSearchResultConfig.setAuthorizationIncluded(true);
		cdrSearchResultConfig.setComponentName(GtnFrameworkStringConstants.RESULTS);
		componentList.add(cdrSearchResultConfig);

		addCdrResultLayout(componentList);

	}

	private void addCdrResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrSearchResultTableLayout = cdrPopUpConfigProvider
				.getHorizontalLayoutConfig(GtnFrameworkStringConstants.RESULTS_LAYOUT, true, "resultPanel");
		cdrSearchResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(cdrSearchResultTableLayout);

		addPagedTableComponent(componentList);
	}

	private void addCdrFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrSearchCriteriaLayoutConfig = cdrPopUpConfigProvider.getGtnCssLayoutConfig(
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel",
				GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		cdrSearchCriteriaLayoutConfig.setComponentStyle(styleList);
		componentList.add(cdrSearchCriteriaLayoutConfig);

		addFieldComponent(componentList);
	}

	private void addCdrButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopupSearchCriteriaButtonLayout = cdrPopUpConfigProvider
				.getCssLayoutConfig(GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT, false, null);
		cdrPopupSearchCriteriaButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdrPopupSearchCriteriaButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cdrPopupSearchCriteriaButtonLayout.setSpacing(true);
		cdrPopupSearchCriteriaButtonLayout.setMargin(true);
		componentList.add(cdrPopupSearchCriteriaButtonLayout);

		addCdrSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addCdrRuleType(componentList);
		addCdrRuleNo(componentList);
		addCdrRuleName(componentList);
		addCdrRuleCategory(componentList);
	}

	private void addCdrRuleType(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig ruleTypeLayoutConfig = cdrPopUpConfigProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.RULE_TYPE_LAYOUT, true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(ruleTypeLayoutConfig);

		GtnUIFrameworkComponentConfig ruleTypeComponentConfig = cdrPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.RULE_TYPE, true, ruleTypeLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		ruleTypeComponentConfig.setAuthorizationIncluded(true);
		ruleTypeComponentConfig.setComponentName("Rule Type");
		componentList.add(ruleTypeComponentConfig);

		GtnUIFrameworkComboBoxConfig ruleTypeComboConfig = cdrPopUpConfigProvider.getComboBoxConfig("RULE_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		ruleTypeComponentConfig.setGtnComboboxConfig(ruleTypeComboConfig);

		GtnUIFrameworkValidationConfig ruleTypeValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> ruleTypeConditions = new ArrayList<>();
		ruleTypeConditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		ruleTypeValidationConfig.setConditionList(ruleTypeConditions);
		ruleTypeComponentConfig.setGtnUIFrameworkValidationConfig(ruleTypeValidationConfig);
		ruleTypeComboConfig.setHasDefaultValue(true);
		ruleTypeComboConfig.setDefaultDesc("Net Sales");
		ruleTypeComponentConfig.setGtnComboboxConfig(ruleTypeComboConfig);
	}

	private void addCdrRuleNo(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrRuleNolayout = cdrPopUpConfigProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.RULE_NO_LAYOUT, true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(cdrRuleNolayout);

		GtnUIFrameworkComponentConfig ruleNoComponentConfig = cdrPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.RULE_NO, true, cdrRuleNolayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		ruleNoComponentConfig.setAuthorizationIncluded(true);
		ruleNoComponentConfig.setComponentName("Rule No");
		componentList.add(ruleNoComponentConfig);

		GtnUIFrameworkValidationConfig ruleNoValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> ruleNoconditions = new ArrayList<>();
		ruleNoconditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		ruleNoValidationConfig.setConditionList(ruleNoconditions);
		ruleNoComponentConfig.setGtnUIFrameworkValidationConfig(ruleNoValidationConfig);

	}

	private void addCdrRuleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrRuleNamelayout = cdrPopUpConfigProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.RULE_NAME_LAYOUT, true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(cdrRuleNamelayout);

		GtnUIFrameworkComponentConfig ruleNameComponentConfig = cdrPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.RULE_NAME, true, cdrRuleNamelayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		ruleNameComponentConfig.setAuthorizationIncluded(true);
		ruleNameComponentConfig.setComponentName("Rule Name");
		componentList.add(ruleNameComponentConfig);

		GtnUIFrameworkValidationConfig ruleNameValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> ruleNameConditions = new ArrayList<>();
		ruleNameConditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		ruleNameValidationConfig.setConditionList(ruleNameConditions);
		ruleNameComponentConfig.setGtnUIFrameworkValidationConfig(ruleNameValidationConfig);
	}

	private void addCdrRuleCategory(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig ruleCategoryLayoutConfig = cdrPopUpConfigProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.RULE_CATEGORY_LAYOUT, true,
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(ruleCategoryLayoutConfig);

		GtnUIFrameworkComponentConfig ruleCategoryComponentConfig = cdrPopUpConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkStringConstants.RULE_CATEGORY, true,
						ruleCategoryLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		ruleCategoryComponentConfig.setAuthorizationIncluded(true);
		ruleCategoryComponentConfig.setComponentName("Rule Category");
		componentList.add(ruleCategoryComponentConfig);

		GtnUIFrameworkComboBoxConfig ruleCategoryComboConfig = cdrPopUpConfigProvider.getComboBoxConfig("RULE_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		ruleCategoryComponentConfig.setGtnComboboxConfig(ruleCategoryComboConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		ruleCategoryComponentConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		ruleCategoryComponentConfig.setGtnComboboxConfig(ruleCategoryComboConfig);
	}

	private void addCdrSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopupSearchBtnLayout = cdrPopUpConfigProvider.getHorizontalLayoutConfig(
				"CDRPopUpSearch01Layout", true, GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(cdrPopupSearchBtnLayout);

		GtnUIFrameworkComponentConfig cdrPopupSearchButtonConfig = cdrPopUpConfigProvider.getUIFrameworkComponentConfig(
				"CDRPopUpSearch01", true, cdrPopupSearchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		cdrPopupSearchButtonConfig.setAuthorizationIncluded(true);
		cdrPopupSearchButtonConfig.setComponentName("SEARCH");
		componentList.add(cdrPopupSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> cdrSearchButtonActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig searchValidationActionConfig = cdrPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		searchValidationActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkStringConstants.RULE_TYPE, GtnFrameworkStringConstants.RULE_NO,
						GtnFrameworkStringConstants.RULE_NAME, GtnFrameworkStringConstants.RULE_CATEGORY));
		searchValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = cdrPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParams);
		onFailure.add(alertActionConfig);
		searchValidationActionConfig.addActionParameter(onFailure);
		cdrSearchButtonActionConfigList.add(searchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = cdrPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkStringConstants.CDR_POPUP_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList(new String[] { GtnFrameworkStringConstants.RULE_TYPE, GtnFrameworkStringConstants.RULE_NO,
						GtnFrameworkStringConstants.RULE_NAME, GtnFrameworkStringConstants.RULE_CATEGORY }));

		cdrSearchButtonActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = cdrPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		notificationActionConfig.setActionParameterList(notificationParams);
		cdrSearchButtonActionConfigList.add(notificationActionConfig);
		cdrPopupSearchButtonConfig.setGtnUIFrameWorkActionConfigList(cdrSearchButtonActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopupResetBtnLayout = cdrPopUpConfigProvider.getHorizontalLayoutConfig(
				"CDRPopUpSearchSearchViewReset01Layout", true, GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(cdrPopupResetBtnLayout);

		GtnUIFrameworkComponentConfig cdrPopupResetButtonConfig = cdrPopUpConfigProvider.getUIFrameworkComponentConfig(
				"CDRPopUpSearchSearchViewReset01", true, cdrPopupResetBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		cdrPopupResetButtonConfig.setComponentName("RESET");
		cdrPopupResetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cdrPopupResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = cdrPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkStringConstants.RULE_NAME, "");
		resetMap.put(GtnFrameworkStringConstants.RULE_NO, "");
		resetMap.put(GtnFrameworkStringConstants.RULE_TYPE, null);
		resetMap.put(GtnFrameworkStringConstants.RULE_CATEGORY, null);
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);

		actionConfigList.add(resetActionConfig);
		cdrPopupResetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig ruleSearchResultConfig = cdrPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.CDR_POPUP_TABLE, true, GtnFrameworkStringConstants.RESULTS_LAYOUT,
				GtnUIFrameworkComponentType.PAGEDTABLE);
		ruleSearchResultConfig.setAuthorizationIncluded(true);
		ruleSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(ruleSearchResultConfig);

		GtnUIFrameworkPagedTableConfig cdrSearchResultsConfig = new GtnUIFrameworkPagedTableConfig();
		cdrSearchResultsConfig.setEditable(false);
		cdrSearchResultsConfig.setFilterBar(true);
		cdrSearchResultsConfig.setSelectable(true);
		cdrSearchResultsConfig.setItemPerPage(10);
		cdrSearchResultsConfig.setPageLength(10);
		cdrSearchResultsConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		cdrSearchResultsConfig
				.setTableVisibleHeader(new String[] { "Rule Type", "Rule No", "Rule Name", "Rule Category" });
		cdrSearchResultsConfig.setTableColumnMappingId(
				new Object[] { GtnFrameworkStringConstants.RULE_TYPE, GtnFrameworkStringConstants.RULE_NO,
						GtnFrameworkStringConstants.RULE_NAME, GtnFrameworkStringConstants.RULE_CATEGORY });
		cdrSearchResultsConfig.setExtraColumn(new Object[] { "systemId" });
		cdrSearchResultsConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		cdrSearchResultsConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		cdrSearchResultsConfig.setModuleName("ComplianceAndDeductionRules");
		cdrSearchResultsConfig.setQueryName("complianceDeductionAndRulesCloseSearch");
		cdrSearchResultsConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.CDR);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> cdrRuleTableFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig ruleCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		ruleCustomFilterConfig.setPropertId(GtnFrameworkStringConstants.RULE_TYPE);
		ruleCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig ruleCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		ruleCustomFilterComponentConfig.setComponentId("ruleTypecustomFilterComboBox");
		ruleCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig ruleTypeConfig = cdrPopUpConfigProvider.getComboBoxConfig("RULE_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		ruleCustomFilterComponentConfig.setGtnComboboxConfig(ruleTypeConfig);

		ruleCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		ruleCustomFilterConfig.setGtnComponentConfig(ruleCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig ruleCategoryCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		ruleCategoryCustomFilterConfig.setPropertId(GtnFrameworkStringConstants.RULE_CATEGORY);
		ruleCategoryCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig ruleCategoryCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		ruleCategoryCustomFilterComponentConfig.setComponentId("ruleCategorycustomFilterComboBox");
		ruleCategoryCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);

		GtnUIFrameworkComboBoxConfig ruleCategoryConfig = cdrPopUpConfigProvider.getComboBoxConfig("RULE_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ruleCategoryCustomFilterComponentConfig.setGtnComboboxConfig(ruleCategoryConfig);
		ruleCategoryCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		ruleCategoryCustomFilterConfig.setGtnComponentConfig(ruleCategoryCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig createedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		createedByCustomFilterConfig.setPropertId("createdBy");
		createedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig createdByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		createdByCustomFilterComponentConfig.setComponentId("createdBycustomFilterComboBox");
		createdByCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig createdByConfig = cdrPopUpConfigProvider.getComboBoxConfig("userIdName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		createdByCustomFilterComponentConfig.setGtnComboboxConfig(createdByConfig);

		createdByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		createedByCustomFilterConfig.setGtnComponentConfig(createdByCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig modifiedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		modifiedByCustomFilterConfig.setPropertId("modifiedBy");
		modifiedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig modifiedByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		modifiedByCustomFilterComponentConfig.setComponentId("modifiedBycustomFilterComboBox");
		modifiedByCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig modifiedByConfig = cdrPopUpConfigProvider.getComboBoxConfig("userIdName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		modifiedByCustomFilterComponentConfig.setGtnComboboxConfig(modifiedByConfig);
		modifiedByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		modifiedByCustomFilterConfig.setGtnComponentConfig(modifiedByCustomFilterComponentConfig);

		cdrRuleTableFilterConfigMap.put(modifiedByCustomFilterConfig.getPropertId(), modifiedByCustomFilterConfig);

		cdrRuleTableFilterConfigMap.put(createedByCustomFilterConfig.getPropertId(), createedByCustomFilterConfig);
		cdrRuleTableFilterConfigMap.put(ruleCategoryCustomFilterConfig.getPropertId(), ruleCategoryCustomFilterConfig);

		cdrRuleTableFilterConfigMap.put(ruleCustomFilterConfig.getPropertId(), ruleCustomFilterConfig);
		cdrSearchResultsConfig.setCustomFilterConfigMap(cdrRuleTableFilterConfigMap);
		ruleSearchResultConfig.setGtnPagedTableConfig(cdrSearchResultsConfig);
	}

	private void addCdrRuleDetailsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrFormulaDetailsResultConfig = cdrPopUpConfigProvider
				.getPanelConfig("ruleDetailsResultPanel", true, "CDRPopUpSearchSearchViewRightLayout");
		cdrFormulaDetailsResultConfig.setComponentName("Rule Details");
		cdrFormulaDetailsResultConfig.setAuthorizationIncluded(true);
		cdrFormulaDetailsResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(cdrFormulaDetailsResultConfig);

		ruleDetailsResultLayout(componentList);
	}

	private void ruleDetailsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrFormulaDetailsResultTableLayout = cdrPopUpConfigProvider
				.getHorizontalLayoutConfig(GtnFrameworkStringConstants.RULE_DETAILS_RESULT_LAYOUT, true,
						"ruleDetailsResultPanel");
		cdrFormulaDetailsResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(cdrFormulaDetailsResultTableLayout);

		ruleDetailsResultDataTable(componentList);
	}

	private void ruleDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrFormulaDetailsResultConfig = cdrPopUpConfigProvider
				.getUIFrameworkComponentConfig("cDRRulePopUpruleDetailsattachResultTable", true,
						GtnFrameworkStringConstants.RULE_DETAILS_RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		cdrFormulaDetailsResultConfig.setAuthorizationIncluded(true);
		cdrFormulaDetailsResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(cdrFormulaDetailsResultConfig);

		GtnUIFrameworkPagedTableConfig cdrDetailsTableConfig = new GtnUIFrameworkPagedTableConfig();

		cdrDetailsTableConfig.setItemPerPage(10);
		cdrDetailsTableConfig.setPageLength(10);
		cdrDetailsTableConfig.setEditable(false);
		cdrDetailsTableConfig.setFilterBar(true);
		cdrDetailsTableConfig.setSelectable(true);
		cdrDetailsTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		cdrDetailsTableConfig.setTableVisibleHeader(new String[] { "Line Type:", "Item/Group/MS Association", "Keyword",
				"Operator", "Value", "Comparison", "Operator" });
		cdrDetailsTableConfig.setTableColumnMappingId(new Object[] { "lineType", "iGMSAssociation", "keyword",
				"operator", "value", "comparison", "operatorOne" });
		cdrDetailsTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		cdrDetailsTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		cdrDetailsTableConfig.setModuleName("cDRPopUpConfig");
		cdrDetailsTableConfig.setQueryName("cDRPopUpConfig");
		cdrDetailsTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.POPUP_CONFIG);
		cdrFormulaDetailsResultConfig.setGtnPagedTableConfig(cdrDetailsTableConfig);
	}

	private void addCdrActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopupActionBtnLayout = cdrPopUpConfigProvider
				.getCssLayoutConfig(GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT, false, null);
		cdrPopupActionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cdrPopupActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdrPopupActionBtnLayout.setSpacing(true);
		cdrPopupActionBtnLayout.setMargin(true);
		componentList.add(cdrPopupActionBtnLayout);

		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
		addDetailsButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopupSelectBtnLayout = cdrPopUpConfigProvider.getHorizontalLayoutConfig(
				"CDRPopUpSearchSearchViewSelectButtonLayout", true, GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(cdrPopupSelectBtnLayout);

		GtnUIFrameworkComponentConfig cdrSelectButtonConfig = cdrPopUpConfigProvider.getUIFrameworkComponentConfig(
				"CDRPopUpSearchSearchViewSelectButton", true, cdrPopupSelectBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		cdrSelectButtonConfig.setComponentName("SELECT");
		cdrSelectButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cdrSelectButtonConfig);

		List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectActionConfig = new GtnUIFrameWorkActionConfig();
		selectActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkStringConstants.CDR_POPUP_TABLE);
		actionParameter.add("rebatePlanCalculationsNetSalesRule");
		actionParameter.add(Arrays.asList(new String[] { GtnFrameworkStringConstants.RULE_NAME }));
		actionParameter.add(Arrays.asList(new String[] { "rebatePlanCalculationsNetSalesRule" }));

		selectActionConfig.setActionParameterList(actionParameter);
		selectActionConfigList.add(selectActionConfig);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.CDR_POPUP_SEARCH_VIEW }));
		selectActionConfigList.add(closeAction);

		cdrSelectButtonConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopupCloseBtnLayout = cdrPopUpConfigProvider.getHorizontalLayoutConfig(
				"CDRPopUpSearchSearchViewEditButtonLayout", true, GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(cdrPopupCloseBtnLayout);

		GtnUIFrameworkComponentConfig cdrCloseButtonConfig = cdrPopUpConfigProvider.getUIFrameworkComponentConfig(
				"CDRPopUpSearchSearchViewEditButton", true, cdrPopupCloseBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		cdrCloseButtonConfig.setAuthorizationIncluded(true);
		cdrCloseButtonConfig.setComponentName("CLOSE");
		componentList.add(cdrCloseButtonConfig);

		List<GtnUIFrameWorkActionConfig> closeActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeActionConfig = new GtnUIFrameWorkActionConfig();
		closeActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.CDR_POPUP_SEARCH_VIEW }));
		closeActionConfigList.add(closeActionConfig);

		cdrCloseButtonConfig.setGtnUIFrameWorkActionConfigList(closeActionConfigList);

	}

	private void addDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopupDetailsBtnLayout = cdrPopUpConfigProvider.getHorizontalLayoutConfig(
				"cDRAddViewButtonLayout", true, GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(cdrPopupDetailsBtnLayout);

		GtnUIFrameworkComponentConfig cdrDetailsButtonConfig = cdrPopUpConfigProvider.getUIFrameworkComponentConfig(
				"cDRAddViewButton", true, cdrPopupDetailsBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		cdrDetailsButtonConfig.setAuthorizationIncluded(true);
		cdrDetailsButtonConfig.setComponentName("DETAILS");
		componentList.add(cdrDetailsButtonConfig);

		List<GtnUIFrameWorkActionConfig> detailsActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = cdrPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkRPConstants.TRIGGER_DATA_TABLE }));
		detailsActionConfigList.add(customActionforLastOperatorCheck);

		cdrDetailsButtonConfig.setGtnUIFrameWorkActionConfigList(detailsActionConfigList);

	}

}
