/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.popup;

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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkCDRRules {

	private final GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

	public GtnUIFrameworkViewConfig getSearchView() {
		gtnFrameworkConfigurationFactory.setViewId(GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW);
		gtnFrameworkConfigurationFactory.setViewName(GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW);
		gtnFrameworkConfigurationFactory.setIsdefaultView(false);
		GtnUIFrameworkViewConfig view = gtnFrameworkConfigurationFactory.buildView();
		addComponentList(view);
		return view;
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


		GtnUIFrameworkComponentConfig cdrPopUpSearchSearchViewMainCssConfig = new GtnUIFrameworkComponentConfig();
		cdrPopUpSearchSearchViewMainCssConfig.setComponentId(GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		cdrPopUpSearchSearchViewMainCssConfig.setAddToParent(false);
		cdrPopUpSearchSearchViewMainCssConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		List<String> cdrPopUpSearchSearchViewMainCssCStyleList = new ArrayList<>();
		cdrPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cdrPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		cdrPopUpSearchSearchViewMainCssConfig.setComponentStyle(cdrPopUpSearchSearchViewMainCssCStyleList);

		GtnUIFrameworkLayoutConfig cdrPopUpSearchSearchViewMainCssLayout = new GtnUIFrameworkLayoutConfig();
		cdrPopUpSearchSearchViewMainCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cdrPopUpSearchSearchViewMainCssConfig.setGtnLayoutConfig(cdrPopUpSearchSearchViewMainCssLayout);

		componentList.add(cdrPopUpSearchSearchViewMainCssConfig);

		GtnUIFrameworkComponentConfig cssLayoutConfig = new GtnUIFrameworkComponentConfig();
		cssLayoutConfig.setComponentId("CDRPopUpSearchSearchViewLeftLayout");
		cssLayoutConfig.setAddToParent(true);
		cssLayoutConfig.setParentComponentId(GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		cssLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add("gtnFrameworkCol-5");
		styleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		cssLayoutConfig.setComponentStyle(styleList);

		GtnUIFrameworkLayoutConfig cssLayout = new GtnUIFrameworkLayoutConfig();
		cssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cssLayoutConfig.setGtnLayoutConfig(cssLayout);

		componentList.add(cssLayoutConfig);

		GtnUIFrameworkComponentConfig cssLayoutConfg = new GtnUIFrameworkComponentConfig();
		cssLayoutConfg.setComponentId("CDRPopUpSearchSearchViewRightLayout");
		cssLayoutConfg.setAddToParent(true);
		cssLayoutConfg.setParentComponentId(GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		cssLayoutConfg.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		List<String> styleLists = new ArrayList<>();
		styleLists.add("gtnFrameworkCol-7");
		styleLists.add(GtnFrameworkCssConstants.NO_MARGIN);

		cssLayoutConfg.setComponentStyle(styleLists);

		GtnUIFrameworkLayoutConfig cssLayouts = new GtnUIFrameworkLayoutConfig();
		cssLayouts.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cssLayoutConfg.setGtnLayoutConfig(cssLayouts);

		componentList.add(cssLayoutConfg);

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
				GtnFrameworkCommonConstants.RESULTS, true, GtnUIFrameworkComponentType.PANEL);
		panelConfig.setParentComponentId("CDRPopUpSearchSearchViewLeftLayout");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(panelConfig);
		addResultLayout(componentList);

	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(GtnFrameworkCommonConstants.RESULTLAYOUT,
				GtnFrameworkCommonConstants.RESULTLAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
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
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
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
				GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, false, GtnUIFrameworkComponentType.LAYOUT);
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
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.RULE_TYPE_LAYOUT, GtnFrameworkCommonConstants.RULE_TYPE_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = gtnFrameworkConfigurationFactory.buildComponentConfig(GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE,
				"Rule Type", true, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setParentComponentId(GtnFrameworkCommonConstants.RULE_TYPE_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		companyType.setEnable(false);
		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("RULE_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		companyTypeConfig.setHasDefaultValue(true);
		companyTypeConfig.setDefaultDesc("Net Sales");
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addRuleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(GtnFrameworkCommonConstants.RULE_NO_LAYOUT,
				GtnFrameworkCommonConstants.RULE_NO_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(GtnFrameworkCommonConstants.RULE_NO,
				"Rule NO", true, GtnUIFrameworkComponentType.TEXTBOX);
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
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.RULE_NAME_LAYOUT, GtnFrameworkCommonConstants.RULE_NAME_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.RULE_NAME, "Rule Name", true, GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setParentComponentId(GtnFrameworkCommonConstants.RULE_NAME_LAYOUT);

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
				GtnFrameworkCommonConstants.RULE_CATEGORY_LAYOUT, GtnFrameworkCommonConstants.RULE_CATEGORY_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.RULE_CATEGORY, "Rule Category", true, GtnUIFrameworkComponentType.COMBOBOX);
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
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("CDRPopUpSearch01", "Search", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig
				.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, GtnFrameworkCommonConstants.RULE_NO, GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkCommonConstants.RULE_CATEGORY }));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParams);
		onFailure.add(alertActionConfig);
		validationActionConfig.addActionParameter(onFailure);
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add("cDRPopUpsearchResultTable");

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, GtnFrameworkCommonConstants.RULE_NO, GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkCommonConstants.RULE_CATEGORY }));

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
				"CDRPopUpSearchSearchViewReset01", "Reset", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
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
				"cDRPopUpsearchResultTable", GtnFrameworkCommonConstants.RESULTS, true, GtnUIFrameworkComponentType.PAGEDTABLE);

		searchResultConfig.setParentComponentId(GtnFrameworkCommonConstants.RESULTLAYOUT);
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
		searchResults.setItemPerPage(5);
		searchResults.setPageLength(5);
		searchResults.setTableColumnDataType(
				new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResults.setTableVisibleHeader(new String[] { "Rule Type", "Rule No", "Rule Name", "Rule Category" });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.PROPERTY_RULE_TYPE, GtnFrameworkCommonConstants.RULE_NO, GtnFrameworkCommonConstants.RULE_NAME, GtnFrameworkCommonConstants.RULE_CATEGORY });
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
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
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

	private void ruleDetailsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"ruleDetailsResultPanel", "Rule Details", true, GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setParentComponentId("CDRPopUpSearchSearchViewRightLayout");
		componentList.add(panel);
		ruleDetailsResultLayout(componentList);
	}

	private void ruleDetailsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_RESULT_LAYOUT, GtnFrameworkCommonConstants.RULE_DETAILS_RESULT_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		layoutConfig.setParentComponentId("ruleDetailsResultPanel");

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		ruleDetailsResultDataTable(componentList);
	}

	private void ruleDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachResultConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"cDRRulePopUpruleDetailsattachResultTable", GtnFrameworkCommonConstants.RESULTS, true, GtnUIFrameworkComponentType.PAGEDTABLE);

		attachResultConfig.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_RESULT_LAYOUT);
		attachResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		attachResultConfig.setComponentStyle(tableStyle);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		gtnUIFrameworkValidationConfig.setMinSize(1);
		attachResultConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(attachResultConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		attachResultConfig.setGtnPagedTableConfig(attachResults);

		attachResults.setItemPerPage(5);
		attachResults.setPageLength(5);
		attachResults.setEditable(false);
		attachResults.setFilterBar(true);
		attachResults.setSelectable(true);
		attachResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		attachResults.setTableVisibleHeader(new String[] { "Line Type:", "Item/Group/MS Association", "Keyword",
				"Operator", "Value", "Comparison", "Operator" });
		attachResults.setTableColumnMappingId(new Object[] { "lineType", "iGMSAssociation", "keyword", "operator",
				"value", "comparison", "operatorOne" });
		attachResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		attachResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		attachResults.setModuleName("cDRPopUpConfig");
		attachResults.setQueryName("cDRPopUpConfig");
		attachResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.POPUP_CONFIG);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(
				gtnFrameworkConfigurationFactory.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT));
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
		addDetailsButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRAddButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"CDRPopUpSearchSearchViewSelectButton", "SELECT", true, GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDRAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add("cDRRulePopUpruleDetailsattachResultTable");
		actionParameter.add("rebatePlanCalculationsNetSalesRule");
		actionParameter.add(Arrays.asList(new String[] { GtnFrameworkCommonConstants.RULE_NAME }));
		actionParameter.add(Arrays.asList(new String[] { "rebatePlanCalculationsNetSalesRule" }));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDRAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDREditButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"CDRPopUpSearchSearchViewEditButton", "CLOSE", true, GtnUIFrameworkComponentType.BUTTON);
		cDREditButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDREditButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDREditButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("cDRAddViewButton");
		searchButtonConfig.setComponentName("DETAILS");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck.setActionParameterList(Arrays.asList(new Object[] {
				"com.stpl.gtn.gtn2o.ui.module.rebateplanconfig.logic.GtnUIFrameWorkTriggerDataTableAction" }));
		actionConfigList.add(customActionforLastOperatorCheck);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
