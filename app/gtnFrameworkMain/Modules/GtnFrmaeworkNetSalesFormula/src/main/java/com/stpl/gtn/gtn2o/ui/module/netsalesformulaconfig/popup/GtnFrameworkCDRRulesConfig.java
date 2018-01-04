/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.popup;

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
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnFrameworkLoadRuleDetailsAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkCDRRulesConfig {

	private final GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig cdrRulesView = configProvider.getViewConfig(
				GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW,
				GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW, false);
		addComponentList(cdrRulesView);
		return cdrRulesView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addSearchCriteriaPanel(componentList);
		addButtonLayout(componentList);
		addMainLayout(componentList);
		addResultPanel(componentList);

		ruleDetailsResultPanel(componentList);
		addActionButtonLayout(componentList);

	}

	private void addMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrRulesMainPanel = configProvider.getPanelConfig("mainLayout", false, null);
		componentList.add(cdrRulesMainPanel);

		GtnUIFrameworkComponentConfig cdrPopUpSearchMainLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT, true, "mainLayout");
		cdrPopUpSearchMainLayout.setComponentWidth("1250px");

		List<String> cdrPopUpSearchMainLayoutStyleList = new ArrayList<>();
		cdrPopUpSearchMainLayoutStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cdrPopUpSearchMainLayoutStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);

		cdrPopUpSearchMainLayout.setComponentStyle(cdrPopUpSearchMainLayoutStyleList);

		componentList.add(cdrPopUpSearchMainLayout);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopUpSearchPanel = configProvider
				.getPanelConfig(GtnFrameworkNSFConstants.SEARCH_CRITERIA_PANEL, false, null);
		cdrPopUpSearchPanel.setComponentName("Search Criteria");
		cdrPopUpSearchPanel.setAuthorizationIncluded(true);
		componentList.add(cdrPopUpSearchPanel);
		addFieldLayout(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpResultPanel = configProvider.getPanelConfig("resultPanel", true,
				GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		cdrPopUpResultPanel.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		cdrPopUpResultPanel.setAuthorizationIncluded(true);
		cdrPopUpResultPanel.setComponentWidth("600px");
		componentList.add(cdrPopUpResultPanel);
		addResultLayout(componentList);

	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpResultLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkCommonConstants.RESULT_LAYOUT, true, "resultPanel");
		cdrPopUpResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(cdrPopUpResultLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpFieldLayout = configProvider.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true, GtnFrameworkNSFConstants.SEARCH_CRITERIA_PANEL,
				GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(cdrPopUpFieldLayout);
		addFieldComponent(componentList);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, false, null);
		cdrPopUpButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(cdrPopUpButtonLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addRuleType(componentList);
		addRuleNo(componentList);
		addRuleName(componentList);
		addRuleCategory(componentList);
	}

	private void addRuleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpRuleTypeLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.RULE_TYPE_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(cdrPopUpRuleTypeLayout);

		GtnUIFrameworkComponentConfig cdrPopUpRuleType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, true, GtnFrameworkCommonConstants.RULE_TYPE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		cdrPopUpRuleType.setAuthorizationIncluded(true);
		cdrPopUpRuleType.setComponentName("Rule Type");
		cdrPopUpRuleType.setEnable(false);
		componentList.add(cdrPopUpRuleType);

		GtnUIFrameworkComboBoxConfig cdrPopUpRuleTypeConfig = configProvider.getComboBoxConfig("RULE_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig cdrPopUpRuleTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		cdrPopUpRuleTypeValidationConfig.setConditionList(conditions);
		cdrPopUpRuleType.setGtnUIFrameworkValidationConfig(cdrPopUpRuleTypeValidationConfig);
		cdrPopUpRuleTypeConfig.setHasDefaultValue(true);
		cdrPopUpRuleTypeConfig.setDefaultDesc("Net Sales");
		cdrPopUpRuleType.setGtnComboboxConfig(cdrPopUpRuleTypeConfig);
	}

	private void addRuleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpRuleNoLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.RULE_NO_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(cdrPopUpRuleNoLayout);

		GtnUIFrameworkComponentConfig cdrPopUpRuleNo = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.RULE_NO, true, GtnFrameworkCommonConstants.RULE_NO_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		cdrPopUpRuleNo.setAuthorizationIncluded(true);
		cdrPopUpRuleNo.setComponentName("Rule NO");

		GtnUIFrameworkValidationConfig cdrPopUpRuleNoValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		cdrPopUpRuleNoValidationConfig.setConditionList(conditions);
		cdrPopUpRuleNo.setGtnUIFrameworkValidationConfig(cdrPopUpRuleNoValidationConfig);

		componentList.add(cdrPopUpRuleNo);
	}

	private void addRuleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopUpRuleNameLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.RULE_NAME_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(cdrPopUpRuleNameLayout);

		GtnUIFrameworkComponentConfig cdrPopUpRuleName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.RULE_NAME, true, GtnFrameworkCommonConstants.RULE_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		cdrPopUpRuleName.setAuthorizationIncluded(true);
		cdrPopUpRuleName.setComponentName("Rule Name");

		GtnUIFrameworkValidationConfig cdrPopUpRuleNameValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		cdrPopUpRuleNameValidationConfig.setConditionList(conditions);
		cdrPopUpRuleName.setGtnUIFrameworkValidationConfig(cdrPopUpRuleNameValidationConfig);
		componentList.add(cdrPopUpRuleName);
	}

	private void addRuleCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpRuleCategoryLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.RULE_CATEGORY_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(cdrPopUpRuleCategoryLayout);

		GtnUIFrameworkComponentConfig cdrPopUpRuleCategory = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.RULE_CATEGORY, true, GtnFrameworkCommonConstants.RULE_CATEGORY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		cdrPopUpRuleCategory.setAuthorizationIncluded(true);
		cdrPopUpRuleCategory.setComponentName("Rule Category");
		componentList.add(cdrPopUpRuleCategory);

		GtnUIFrameworkComboBoxConfig cdrPopUpRuleCategoryConfig = configProvider.getComboBoxConfig("RULE_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig cdrPopUpRuleCategoryValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		cdrPopUpRuleCategoryValidationConfig.setConditionList(conditions);
		cdrPopUpRuleCategory.setGtnUIFrameworkValidationConfig(cdrPopUpRuleCategoryValidationConfig);
		cdrPopUpRuleCategory.setGtnComboboxConfig(cdrPopUpRuleCategoryConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpSearchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"CDRPopUpSearch01", true, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cdrPopUpSearchButtonConfig.setAuthorizationIncluded(true);
		cdrPopUpSearchButtonConfig.setComponentName("Search");
		componentList.add(cdrPopUpSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> cdrPopUpSearchBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig cdrPopUpSearchBtnValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		cdrPopUpSearchBtnValidationActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, GtnFrameworkCommonConstants.RULE_NO,
						GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkCommonConstants.RULE_CATEGORY));
		cdrPopUpSearchBtnValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> cdrPopUpSearchbtnOnFailure = new ArrayList<>();
		GtnUIFrameWorkActionConfig cdrPopUpSearchAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> cdrPopUpSearchAlertParams = new ArrayList<>();
		cdrPopUpSearchAlertParams.add("No Search Criteria ");
		cdrPopUpSearchAlertParams.add("Please enter Search Criteria");
		cdrPopUpSearchAlertActionConfig.setActionParameterList(cdrPopUpSearchAlertParams);
		cdrPopUpSearchbtnOnFailure.add(cdrPopUpSearchAlertActionConfig);
		cdrPopUpSearchBtnValidationActionConfig.addActionParameter(cdrPopUpSearchbtnOnFailure);
		cdrPopUpSearchBtnActionConfigList.add(cdrPopUpSearchBtnValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkNSFConstants.getNetSalesRulePopupResultTable());
		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, GtnFrameworkCommonConstants.RULE_NO,
						GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkCommonConstants.RULE_CATEGORY));
		cdrPopUpSearchBtnActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");
		notificationActionConfig.setActionParameterList(notificationParams);
		cdrPopUpSearchBtnActionConfigList.add(notificationActionConfig);
		cdrPopUpSearchButtonConfig.setGtnUIFrameWorkActionConfigList(cdrPopUpSearchBtnActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopUpResetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"CDRPopUpSearchSearchViewReset01", true, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cdrPopUpResetButtonConfig.setComponentName("Reset");
		cdrPopUpResetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cdrPopUpResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RULE_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, "Net Sales");
		resetMap.put(GtnFrameworkCommonConstants.RULE_CATEGORY, null);
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);

		actionConfigList.add(resetActionConfig);
		cdrPopUpResetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdrPopUpResultTable = configProvider.getUIFrameworkComponentConfig(
				"cDRPopUpsearchResultTable", true, GtnFrameworkCommonConstants.RESULT_LAYOUT,
				GtnUIFrameworkComponentType.PAGEDTABLE);
		cdrPopUpResultTable.setAuthorizationIncluded(true);
		cdrPopUpResultTable.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		cdrPopUpResultTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		cdrPopUpResultTable.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(cdrPopUpResultTable);

		GtnUIFrameworkPagedTableConfig cdrPopUpResultTableConfig = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"ComplianceAndDeductionRules", "complianceDeductionAndRulesCloseSearch");
		cdrPopUpResultTableConfig.setEditable(false);
		cdrPopUpResultTableConfig.setItemPerPage(5);
		cdrPopUpResultTableConfig.setPageLength(5);
		cdrPopUpResultTableConfig.setSinkItemPerPageWithPageLength(false);
		cdrPopUpResultTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		cdrPopUpResultTableConfig
				.setTableVisibleHeader(new String[] { "Rule Type", "Rule No", "Rule Name", "Rule Category" });
		cdrPopUpResultTableConfig.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, GtnFrameworkCommonConstants.RULE_NO,
						GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkCommonConstants.RULE_CATEGORY });
		cdrPopUpResultTableConfig.setExtraColumnDataType(new Class<?>[] { String.class });
		cdrPopUpResultTableConfig.setExtraColumn(new Object[] { "systemId" });
		cdrPopUpResultTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.CDR);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("ruleTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig ruleTypeConfig = configProvider.getComboBoxConfig("RULE_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		customFilterComponentConfig.setGtnComboboxConfig(ruleTypeConfig);

		customFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig ruleCategoryCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		ruleCategoryCustomFilterConfig.setPropertId(GtnFrameworkCommonConstants.RULE_CATEGORY);
		ruleCategoryCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig ruleCategoryCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		ruleCategoryCustomFilterComponentConfig.setComponentId("ruleCategorycustomFilterComboBox");
		ruleCategoryCustomFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);

		GtnUIFrameworkComboBoxConfig ruleCategoryConfig = configProvider.getComboBoxConfig("RULE_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ruleCategoryCustomFilterComponentConfig.setGtnComboboxConfig(ruleCategoryConfig);
		ruleCategoryCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		ruleCategoryCustomFilterConfig.setGtnComponentConfig(ruleCategoryCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig cdrPopUpCreateedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		cdrPopUpCreateedByCustomFilterConfig.setPropertId("createdBy");
		cdrPopUpCreateedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig createdByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		createdByCustomFilterComponentConfig.setComponentId("createdBycustomFilterComboBox");
		createdByCustomFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig createdByConfig = configProvider.getComboBoxConfig("userIdName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		createdByCustomFilterComponentConfig.setGtnComboboxConfig(createdByConfig);

		createdByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		cdrPopUpCreateedByCustomFilterConfig.setGtnComponentConfig(createdByCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig cdrPopUpModifiedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		cdrPopUpModifiedByCustomFilterConfig.setPropertId("modifiedBy");
		cdrPopUpModifiedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig modifiedByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		modifiedByCustomFilterComponentConfig.setComponentId("modifiedBycustomFilterComboBox");
		modifiedByCustomFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig modifiedByConfig = configProvider.getComboBoxConfig("userIdName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		modifiedByCustomFilterComponentConfig.setGtnComboboxConfig(modifiedByConfig);
		modifiedByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		cdrPopUpModifiedByCustomFilterConfig.setGtnComponentConfig(modifiedByCustomFilterComponentConfig);

		customFilterConfigMap.put(cdrPopUpModifiedByCustomFilterConfig.getPropertId(),
				cdrPopUpModifiedByCustomFilterConfig);

		customFilterConfigMap.put(cdrPopUpCreateedByCustomFilterConfig.getPropertId(),
				cdrPopUpCreateedByCustomFilterConfig);
		customFilterConfigMap.put(ruleCategoryCustomFilterConfig.getPropertId(), ruleCategoryCustomFilterConfig);

		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		cdrPopUpResultTableConfig.setCustomFilterConfigMap(customFilterConfigMap);
		cdrPopUpResultTable.setGtnPagedTableConfig(cdrPopUpResultTableConfig);
	}

	private void ruleDetailsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpRuleDetailsResultPanel = configProvider.getPanelConfig(
				"ruleDetailsResultPanel", true,
				GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		cdrPopUpRuleDetailsResultPanel.setComponentWidth("650px");
		cdrPopUpRuleDetailsResultPanel.setComponentName("Rule Details");
		cdrPopUpRuleDetailsResultPanel.setAuthorizationIncluded(true);
		componentList.add(cdrPopUpRuleDetailsResultPanel);
		ruleDetailsResultLayout(componentList);
	}

	private void ruleDetailsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpRuleDetailsResultLayout = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_RESULT_LAYOUT, true, "ruleDetailsResultPanel");
		cdrPopUpRuleDetailsResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(cdrPopUpRuleDetailsResultLayout);

		ruleDetailsResultDataTable(componentList);
	}

	private void ruleDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpRuleDetailsResultTable = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkNSFConstants.getNetSalesRulePopupDetailsTable(), true,
				GtnFrameworkCommonConstants.RULE_DETAILS_RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		cdrPopUpRuleDetailsResultTable.setAuthorizationIncluded(true);
		cdrPopUpRuleDetailsResultTable.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		cdrPopUpRuleDetailsResultTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		cdrPopUpRuleDetailsResultTable.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(cdrPopUpRuleDetailsResultTable);

		GtnUIFrameworkPagedTableConfig cdrPopUpRuleDetailsResultTableConfig = configProvider.getPagedTableConfig(true,
				true,
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.GET_RULE_DETAILS_LOOKUP_TABLE_DATA,
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.GET_RULE_DETAILS_LOOKUP_TABLE_DATA,
				"", "");
		cdrPopUpRuleDetailsResultTable.setGtnPagedTableConfig(cdrPopUpRuleDetailsResultTableConfig);
		cdrPopUpRuleDetailsResultTableConfig.setItemPerPage(5);
		cdrPopUpRuleDetailsResultTableConfig.setPageLength(5);
		cdrPopUpRuleDetailsResultTableConfig.setEditable(false);
		cdrPopUpRuleDetailsResultTableConfig.setSinkItemPerPageWithPageLength(false);
		cdrPopUpRuleDetailsResultTableConfig.setTableColumnDataType(new Class<?>[] { String.class, String.class,
				String.class, String.class, String.class, String.class, String.class });
		cdrPopUpRuleDetailsResultTableConfig
				.setTableVisibleHeader(GtnFrameworkNSFConstants.getRuleDetailsLookupHeader());
		cdrPopUpRuleDetailsResultTableConfig
				.setTableColumnMappingId(GtnFrameworkNSFConstants.getRuleDetailsLookupColumn());
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpActionBtn = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false, null);
		cdrPopUpActionBtn.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(cdrPopUpActionBtn);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
		addDetailsButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpSelectBtn = configProvider.getUIFrameworkComponentConfig(
				"CDRPopUpSearchSearchViewSelectButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cdrPopUpSelectBtn.setAuthorizationIncluded(true);
		cdrPopUpSelectBtn.setComponentName("SELECT");
		componentList.add(cdrPopUpSelectBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add("cDRRulePopUpruleDetailsattachResultTable");
		actionParameter.add("rebatePlanCalculationsNetSalesRule");
		actionParameter.add(Arrays.asList(GtnFrameworkCommonConstants.RULE_NAME));
		actionParameter.add(Arrays.asList("rebatePlanCalculationsNetSalesRule"));
		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW);
		actionConfigList.add(closeAction);

		cdrPopUpSelectBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpCloseBtn = configProvider.getUIFrameworkComponentConfig(
				"CDRPopUpSearchSearchViewEditButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cdrPopUpCloseBtn.setComponentName("CLOSE");
		cdrPopUpCloseBtn.setAuthorizationIncluded(true);
		componentList.add(cdrPopUpCloseBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW);
		actionConfigList.add(closeAction);

		cdrPopUpCloseBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpDetailsButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"cDRAddViewButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cdrPopUpDetailsButtonConfig.setAuthorizationIncluded(true);
		cdrPopUpDetailsButtonConfig.setComponentName("DETAILS");
		componentList.add(cdrPopUpDetailsButtonConfig);

		GtnUIFrameWorkActionConfig cdrPopUpValidationActionConfig = new GtnUIFrameWorkActionConfig();
		cdrPopUpValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		cdrPopUpValidationActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkNSFConstants.getNetSalesRulePopupResultTable()));
		cdrPopUpValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnFrameworkLoadRuleDetailsAction.class.getName());
		tableLoadActionConfig.addActionParameter(GtnFrameworkNSFConstants.getNetSalesRulePopupResultTable());
		tableLoadActionConfig.addActionParameter(GtnFrameworkNSFConstants.getNetSalesRulePopupDetailsTable());

		cdrPopUpValidationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		cdrPopUpValidationActionConfig.addActionParameter(Arrays.asList(tableLoadActionConfig));

		cdrPopUpDetailsButtonConfig.addGtnUIFrameWorkActionConfig(cdrPopUpValidationActionConfig);

	}

}
