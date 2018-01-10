package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkCDRConstants;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkComplianceAndDeductionRulesLandingScreenConfig {

	private final GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

	public GtnUIFrameworkViewConfig getSearchView() {
		gtnFrameworkConfigurationFactory.setViewId("complianceAndDeductionRulesSearchSearchView");
		gtnFrameworkConfigurationFactory.setViewName("complianceAndDeductionRulesSearchSearchView");
		gtnFrameworkConfigurationFactory.setIsdefaultView(true);
		GtnUIFrameworkViewConfig view = gtnFrameworkConfigurationFactory.buildView();
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addSearchCriteriaPanel(componentList);
		addButtonLayout(componentList);
		addResultPanel(componentList);
		addActionButtonLayout(componentList);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"searchCriteriaPanel", "Search Criteria", false, GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(panel);
		addFieldLayout(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = gtnFrameworkConfigurationFactory.buildComponentConfig("resultPanel",
				"Results", false, GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(panelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RESULT_LAYOUT, GtnFrameworkCommonConstants.RESULT_LAYOUT, true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId("resultPanel");
		gtnLayout.setGtnLayoutConfig(layout);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT,
				true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId("searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout.setComponentStyle(styleList);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, false,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
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
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_TYPE_LAYOUT, GtnFrameworkCommonConstants.RULE_TYPE_LAYOUT, true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, "Rule Type", true,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setParentComponentId(GtnFrameworkCommonConstants.RULE_TYPE_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("RULE_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addRuleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_NO_LAYOUT, GtnFrameworkCommonConstants.RULE_NO_LAYOUT, true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_NO, "Rule No", true, GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setParentComponentId(GtnFrameworkCommonConstants.RULE_NO_LAYOUT);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addRuleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_NAME_LAYOUT, GtnFrameworkCommonConstants.RULE_NAME_LAYOUT, true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_NAME, "Rule Name", true, GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setParentComponentId(GtnFrameworkCommonConstants.RULE_NAME_LAYOUT);
		companyNameConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNameConfig);
	}

	private void addRuleCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_CATEGORY_LAYOUT, GtnFrameworkCommonConstants.RULE_CATEGORY_LAYOUT,
				true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_CATEGORY, "Rule Category", true, GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setParentComponentId(GtnFrameworkCommonConstants.RULE_CATEGORY_LAYOUT);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("RULE_CATEGORY", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"complianceAndDeductionRulesSearch01", "Search", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(
				new String[] { GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, GtnFrameworkCommonConstants.RULE_NO,
						GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkCommonConstants.RULE_CATEGORY }));

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParams);
		onFailure.add(alertActionConfig);

		validationActionConfig
				.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkValidationType.OR, onFailure }));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.CD_RSEARCH_RESULT_TABLE }));
		loadDataTableActionConfig.setFieldValues(Arrays.asList(
				new String[] { GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, GtnFrameworkCommonConstants.RULE_NO,
						GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkCommonConstants.RULE_CATEGORY }));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		notificationActionConfig.setActionParameterList(notificationParams);
		actionConfigList.add(notificationActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"complianceAndDeductionRulesReset01", "Reset", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RULE_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_CATEGORY, null);
		params.add(resetMap);
		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.CD_RSEARCH_RESULT_TABLE, "Results", true,
				GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);

		searchResultConfig.setParentComponentId(GtnFrameworkCommonConstants.RESULT_LAYOUT);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		searchResultConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setItemPerPage(10);
		searchResults.setPageLength(10);
		searchResults.setTableColumnDataType(new Class[] { String.class, String.class, String.class, String.class,
				Date.class, String.class, String.class, String.class });
		searchResults.setTableVisibleHeader(new String[] { "Rule Type", "Rule No", "Rule Name", "Rule Category",
				"Creation Date", "Created By", "Modified Date", "Modified By" });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE,
				GtnFrameworkCommonConstants.RULE_NO, GtnFrameworkCommonConstants.RULE_NAME,
				GtnFrameworkCommonConstants.RULE_CATEGORY, "creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		searchResults.setExtraColumn(new Object[] { "systemId" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("ComplianceAndDeductionRules");
		searchResults.setQueryName("complianceDeductionAndRulesCloseSearch");
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.CDR);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("ruleTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig ruleTypeConfig = gtnFrameworkConfigurationFactory.buildComboBoxConfig("RULE_TYPE",
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

		GtnUIFrameworkComboBoxConfig ruleCategoryConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("RULE_CATEGORY", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ruleCategoryCustomFilterComponentConfig.setGtnComboboxConfig(ruleCategoryConfig);
		ruleCategoryCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		ruleCategoryCustomFilterConfig.setGtnComponentConfig(ruleCategoryCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig createedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		createedByCustomFilterConfig.setPropertId("createdBy");
		createedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig createdByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		createdByCustomFilterComponentConfig.setComponentId("createdBycustomFilterComboBox");
		createdByCustomFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig createdByConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("userIdName", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		createdByCustomFilterComponentConfig.setGtnComboboxConfig(createdByConfig);

		createdByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		createedByCustomFilterConfig.setGtnComponentConfig(createdByCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig modifiedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		modifiedByCustomFilterConfig.setPropertId("modifiedBy");
		modifiedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig modifiedByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		modifiedByCustomFilterComponentConfig.setComponentId("modifiedBycustomFilterComboBox");
		modifiedByCustomFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig modifiedByConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("userIdName", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		modifiedByCustomFilterComponentConfig.setGtnComboboxConfig(modifiedByConfig);
		modifiedByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		modifiedByCustomFilterConfig.setGtnComponentConfig(modifiedByCustomFilterComponentConfig);

		customFilterConfigMap.put(modifiedByCustomFilterConfig.getPropertId(), modifiedByCustomFilterConfig);

		customFilterConfigMap.put(createedByCustomFilterConfig.getPropertId(), createedByCustomFilterConfig);
		customFilterConfigMap.put(ruleCategoryCustomFilterConfig.getPropertId(), ruleCategoryCustomFilterConfig);

		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				false, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(
				gtnFrameworkConfigurationFactory.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT));
		componentList.add(gtnLayout);
		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
		addViewButtonComponent(componentList);
		addDeleteButtonComponent(componentList);
		addCopyButtonComponent(componentList);
		addTableResetButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRAddButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"complianceAndDeductionRulesAddButton", "ADD", true, GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setAuthorizationIncluded(true);
		cDRAddButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(cDRAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.ADD }));

		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_ADD_VIEW }));
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE, "");

		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE, null);

		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORY, null);

		setDefaultActionConfig.addActionParameter(resetMap);
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		Object[] disableField = new String[] { GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORY,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO,
				GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE,
				GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE,
				GtnFrameworkCommonConstants.RULE_DETAILS_IGMS_ASSOCIATION,
				GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD, GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR,
				GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON,
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_ADD_BUTTON,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_REMOVE_BUTTON,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_RESET_BUTTON, GtnFrameworkCommonConstants.NOTES_TAB };

		disableAction.setActionParameterList(Arrays.asList(disableField));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON,
				GtnFrameworkCommonConstants.COMPLIANCE_DEDUCTION_ADD_RESET_BUTTON };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(true);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		// Map<String, String> captionMap = new HashMap<>();
		// captionMap.put(GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON,
		// "SAVE");
		changeCaptionActionConfig
				.addActionParameter(Arrays.asList(new String[] { GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON }));
		changeCaptionActionConfig.addActionParameter(Arrays.asList(new String[] { "SAVE" }));

		// changeCaptionActionConfig.setActionParameterList(Arrays.asList(new
		// Object[] { captionMap }));
		actionConfigList.add(changeCaptionActionConfig);

		cDRAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDREditButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"complianceAndDeductionRulesEditButton", "EDIT", true, GtnUIFrameworkComponentType.BUTTON);
		cDREditButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		cDREditButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cDREditButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.EDIT }));

		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.IS_RECORD_SELECTED }));

		actionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_ADD_VIEW }));

		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE, "");

		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE, null);

		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORY, null);

		setDefaultActionConfig.addActionParameter(resetMap);
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		Object[] disableField = new String[] { GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORY,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO,
				GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE,
				GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE,
				GtnFrameworkCommonConstants.RULE_DETAILS_IGMS_ASSOCIATION,
				GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD, GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR,
				GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON,
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_ADD_BUTTON,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_REMOVE_BUTTON,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_RESET_BUTTON, GtnFrameworkCommonConstants.NOTES_TAB };

		disableAction.setActionParameterList(Arrays.asList(disableField));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON,
				GtnFrameworkCommonConstants.COMPLIANCE_DEDUCTION_ADD_RESET_BUTTON };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(true);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig editActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCDRConstants.LOAD_ACTION);

		parameters.add(GtnFrameworkCommonConstants.CD_RSEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(8);

		editActionConfig.setActionParameterList(parameters);
		actionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		// Map<String, String> captionMap = new HashMap<>();
		// captionMap.put(GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON,
		// "UPDATE");
		changeCaptionActionConfig
				.addActionParameter(Arrays.asList(new String[] { GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON }));
		changeCaptionActionConfig.addActionParameter(Arrays.asList(new String[] { "UPDATE" }));

		// changeCaptionActionConfig.setActionParameterList(Arrays.asList(new
		// Object[] { captionMap }));
		actionConfigList.add(changeCaptionActionConfig);

		cDREditButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("cDRAddViewButton");
		searchButtonConfig.setComponentName("VIEW");
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.VIEW }));

		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.IS_RECORD_SELECTED }));
		actionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_ADD_VIEW }));

		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE, "");

		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE, null);

		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORY, null);

		setDefaultActionConfig.addActionParameter(resetMap);
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCDRConstants.LOAD_ACTION);
		parameters.add(GtnFrameworkCommonConstants.CD_RSEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(8);

		editActionConfig.setActionParameterList(parameters);
		actionConfigList.add(editActionConfig);
		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		Object[] disableField = new Object[] { GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORY,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO,
				GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE,
				GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE,
				GtnFrameworkCommonConstants.RULE_DETAILS_IGMS_ASSOCIATION,
				GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD, GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR,
				GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON,
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_ADD_BUTTON,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_REMOVE_BUTTON,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_RESET_BUTTON, GtnFrameworkCommonConstants.NOTES_TAB };

		disableAction.setActionParameterList(Arrays.asList(disableField));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON,
				GtnFrameworkCommonConstants.COMPLIANCE_DEDUCTION_ADD_RESET_BUTTON };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(false);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		// Map<String, String> captionMap = new HashMap<>();
		// captionMap.put(GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON,
		// "SAVE");
		//
		// changeCaptionActionConfig.setActionParameterList(Arrays.asList(new
		// Object[] { captionMap }));
		changeCaptionActionConfig
				.addActionParameter(Arrays.asList(new String[] { GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON }));
		changeCaptionActionConfig.addActionParameter(Arrays.asList(new String[] { "SAVE" }));
		actionConfigList.add(changeCaptionActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRDeleteButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"complianceAndDeductionRulesDeleteButton", "DELETE", true, GtnUIFrameworkComponentType.BUTTON);
		cDRDeleteButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cDRDeleteButtonConfig.setAuthorizationIncluded(true);
		cDRDeleteButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(cDRDeleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.DELETE }));
		actionConfigList.add(setModeActionConfig);
		GtnUIFrameWorkActionConfig isRecordSelectedAction = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		isRecordSelectedAction
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.IS_RECORD_SELECTED }));
		actionConfigList.add(isRecordSelectedAction);

		GtnUIFrameWorkActionConfig deleteConfirmation = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		deleteConfirmation
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.DELETE_CONFIRMATION }));
		actionConfigList.add(deleteConfirmation);

		cDRDeleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCopyButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRCopyButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"complianceAndDeductionRulesDeleteButton", "COPY", true, GtnUIFrameworkComponentType.BUTTON);
		cDRCopyButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cDRCopyButtonConfig.setAuthorizationIncluded(true);
		cDRCopyButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(cDRCopyButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.COPY }));
		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.IS_RECORD_SELECTED }));
		actionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_ADD_VIEW }));

		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE, "");

		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE, null);

		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORY, null);

		setDefaultActionConfig.addActionParameter(resetMap);
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCDRConstants.LOAD_ACTION);
		parameters.add(GtnFrameworkCommonConstants.CD_RSEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(8);

		editActionConfig.setActionParameterList(parameters);

		actionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> resetMap1 = new HashMap<>();
		resetMap1.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO, "");
		resetMap1.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME, "");
		resetMap1.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE, null);
		resetMap1.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORY, null);

		resetActionConfig.setActionParameterList(Arrays.asList(new Object[] { resetMap1 }));
		actionConfigList.add(resetActionConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		String[] disableField = new String[] { GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORY,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO,
				GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE,
				GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE,
				GtnFrameworkCommonConstants.RULE_DETAILS_IGMS_ASSOCIATION,
				GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD, GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR,
				GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON,
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_ADD_BUTTON,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_REMOVE_BUTTON,
				GtnFrameworkCommonConstants.CDR_INFO_GTN_RESET_BUTTON, GtnFrameworkCommonConstants.NOTES_TAB };

		disableAction.addActionParameter(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE);
		disableAction.addActionParameter(Arrays.asList(disableField));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON,
				GtnFrameworkCommonConstants.COMPLIANCE_DEDUCTION_ADD_RESET_BUTTON };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(true);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		// Map<String, String> captionMap = new HashMap<>();
		// captionMap.put(GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON,
		// "SAVE");
		changeCaptionActionConfig
				.addActionParameter(Arrays.asList(new String[] { GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON }));
		changeCaptionActionConfig.addActionParameter(Arrays.asList(new String[] { "SAVE" }));

		// changeCaptionActionConfig.setActionParameterList(Arrays.asList(new
		// Object[] { captionMap }));
		actionConfigList.add(changeCaptionActionConfig);

		cDRCopyButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addTableResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"complianceAndDeductionRulesReset02", "RESET", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the listview to default/previous values?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.CD_RSEARCH_RESULT_TABLE, "");
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
