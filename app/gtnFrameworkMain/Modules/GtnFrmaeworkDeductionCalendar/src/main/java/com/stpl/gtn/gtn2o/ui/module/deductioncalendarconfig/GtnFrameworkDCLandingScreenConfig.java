package com.stpl.gtn.gtn2o.ui.module.deductioncalendarconfig;

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
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GtnFrameworkDCLandingScreenConfig {

	private final GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

	public GtnUIFrameworkViewConfig getSearchView() {
		gtnFrameworkConfigurationFactory.setViewId("dedctionCalendarSearchView");
		gtnFrameworkConfigurationFactory.setViewName("dedctionCalendarSearchView");
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

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Search Criteria");
		panel.setComponentId("searchCriteriaPanel");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(false);

		componentList.add(panel);
		addFieldLayout(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName("Results");
		panelConfig.setComponentId("resultPanel");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAddToParent(false);
		componentList.add(panelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("resultlayout");
		gtnLayout.setParentComponentId("resultPanel");
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setParentComponentId("searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout.setComponentStyle(styleList);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT);
		gtnLayout.setAddToParent(false);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addDeductionCalendarNo(componentList);
		addDeductionCalendarName(componentList);
		addCustomerGroupDesc(componentList);
		addCategory(componentList);

	}

	private void addDeductionCalendarNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("deductionCalendarNoLayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = new GtnUIFrameworkComponentConfig();
		companyIdConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentId(GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO);
		companyIdConfig.setComponentName("Deduction Calendar No");
		companyIdConfig.setParentComponentId("deductionCalendarNoLayout");
		companyIdConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyIdConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdConfig);
	}

	private void addDeductionCalendarName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("deductionCalendarNameLayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);

		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = new GtnUIFrameworkComponentConfig();
		companyNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentId(GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_NAME);
		companyNoConfig.setComponentName("Deduction Calendar Name");
		companyNoConfig.setParentComponentId("deductionCalendarNameLayout");
		companyNoConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addCustomerGroupDesc(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("customerGroupDescLayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = new GtnUIFrameworkComponentConfig();
		companyNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setComponentId(GtnFrameworkCommonConstants.CUSTOMER_GROUP_DESC);
		companyNameConfig.setComponentName("Customer Group Desc");
		companyNameConfig.setParentComponentId("customerGroupDescLayout");
		companyNameConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNameConfig);
	}

	private void addCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("categoryLayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyCategory = new GtnUIFrameworkComponentConfig();
		companyCategory.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyCategory.setComponentId(GtnFrameworkCommonConstants.CATEGORY);
		companyCategory.setComponentName("Category");
		companyCategory.setParentComponentId("categoryLayout");
		companyCategory.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyCategory.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyCategory);

		GtnUIFrameworkComboBoxConfig companyCategoryConfig = new GtnUIFrameworkComboBoxConfig();
		companyCategoryConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyCategoryConfig.setComboBoxType("COMPANY_CATEGORY");
		companyCategory.setGtnComboboxConfig(companyCategoryConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("dedctionCalendar01", "Search", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.CATEGORY, GtnFrameworkCommonConstants.CUSTOMER_GROUP_DESC, GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_NAME, GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO }));
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
		actionParams.add(GtnFrameworkCommonConstants.DC_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.CATEGORY, GtnFrameworkCommonConstants.CUSTOMER_GROUP_DESC, GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_NAME, GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO }));

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

		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("dcReset01", "Reset", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.CUSTOMER_GROUP_DESC, "");
		resetMap.put(GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.CATEGORY, null);

		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.DC_SEARCH_RESULT_TABLE, "Results", true, GtnUIFrameworkComponentType.PAGEDTABLE);

		searchResultConfig.setParentComponentId("resultlayout");
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
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResults.setTableVisibleHeader(new String[] { "Deduction Calendar No", "Deduction Calendar Name",
				"Deduction Calendar Desc", "Category", "Creation Date", "Created By", "Modified Date", "Modified By" });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO, GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_NAME,
				"deductionCalendarDesc", GtnFrameworkCommonConstants.CATEGORY, "creationDatas", "createdBy", "modifiedDatas", "modifiedBy" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("DeductionCalendar");
		searchResults.setQueryName("deductionCalendarSearch");
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.DEDUCTION_CALENDAR);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId("formulaType");
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("formulaTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig formulaTypeConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("NS_FORMULA_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		customFilterComponentConfig.setGtnComboboxConfig(formulaTypeConfig);

		customFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig formulaCategoryCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		formulaCategoryCustomFilterConfig.setPropertId("formulaCategory");
		formulaCategoryCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig formulaCategoryCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		formulaCategoryCustomFilterComponentConfig.setComponentId("formulaCategorycustomFilterComboBox");
		formulaCategoryCustomFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);

		GtnUIFrameworkComboBoxConfig formulaCategoryConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("RULE_CATEGORY", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		formulaCategoryCustomFilterComponentConfig.setGtnComboboxConfig(formulaCategoryConfig);
		formulaCategoryCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		formulaCategoryCustomFilterConfig.setGtnComponentConfig(formulaCategoryCustomFilterComponentConfig);

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
		customFilterConfigMap.put(formulaCategoryCustomFilterConfig.getPropertId(), formulaCategoryCustomFilterConfig);

		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(
				gtnFrameworkConfigurationFactory.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT));
		componentList.add(gtnLayout);
		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
		addViewButtonComponent(componentList);
		addCopyButtonComponent(componentList);
		addTableResetButtonComponent(componentList);
		addDeleteButtonComponent(componentList);

	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRAddButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"dedctionCalendarSearchAddButton", "ADD", true, GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDRAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.ADD }));

		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.DC_ADD_VIEW}));

		actionConfigList.add(navigationActionConfig);
		GtnUIFrameWorkActionConfig customAction1 = new GtnUIFrameWorkActionConfig();
		customAction1.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction1.setActionParameterList(Arrays.asList(new Object[] {
				"com.stpl.gtn.gtn2o.ui.module.deductioncalendarconfig.customaction.DeductionDetailsLoadAction" }));

		actionConfigList.add(customAction1);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);

		Object[] enableField = new Object[] { GtnFrameworkCommonConstants.DCONTRACT_NO, GtnFrameworkCommonConstants.DCONTRACT_NAME, GtnFrameworkCommonConstants.DCONTRACT_HOLDER, GtnFrameworkCommonConstants.DMARKET_TYPE, GtnFrameworkCommonConstants.DCFP_NO, GtnFrameworkCommonConstants.DCFP_NAME, GtnFrameworkCommonConstants.DIFP_NO, GtnFrameworkCommonConstants.DIFP_NAME, GtnFrameworkCommonConstants.DEDUCTION_ALIAS, GtnFrameworkCommonConstants.PS_NO, GtnFrameworkCommonConstants.PS_NAME, GtnFrameworkCommonConstants.DEDUCTION_NO_CAL, GtnFrameworkCommonConstants.DEDUCTION_NAME_CAL};

		disableAction.setActionParameterList(Arrays.asList(enableField));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig defaultActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		List<Object> params = new ArrayList<>();
		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RS_REBATE_SETUP_MASS_CHECK, "Disable");
		resetMap.put("contractSelectionCheck", "Existing Contract");
		params.add(resetMap);

		defaultActionConfig.setActionParameterList(params);
		actionConfigList.add(defaultActionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.setActionParameterList(Arrays.asList(new Object[] {
				"com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.logic.GtnUIFrameWorkContractSelectionChangeAction" }));

		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		Map<String, String> captionMap = new HashMap<>();
		captionMap.put(GtnFrameworkCommonConstants.NET_SALES_ADD_SAVE_BUTTON, "SAVE");
		changeCaptionActionConfig.setActionParameterList(Arrays.asList(new Object[] { captionMap }));
		actionConfigList.add(changeCaptionActionConfig);

		cDRAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDREditButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("netSalesSearchEditButton", "EDIT", true, GtnUIFrameworkComponentType.BUTTON);
		cDREditButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDREditButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.EDIT }));

		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck.setActionParameterList(Arrays.asList(new Object[] {
				GtnFrameworkCommonConstants.COMSTPLGTNGTN2OUIMODULENETSALESFORMULACON}));

		actionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.DC_ADD_VIEW}));

		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCommonConstants.COMSTPLGTNGTN2OUIMODULENETSALESFORMULACTION);
		parameters.add(GtnFrameworkCommonConstants.DC_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(8);
		editActionConfig.setActionParameterList(parameters);

		actionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		Map<String, String> captionMap = new HashMap<>();
		captionMap.put(GtnFrameworkCommonConstants.NET_SALES_ADD_SAVE_BUTTON, "UPDATE");
		changeCaptionActionConfig.setActionParameterList(Arrays.asList(new Object[] { captionMap }));
		actionConfigList.add(changeCaptionActionConfig);

		cDREditButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("nsAddViewButton");
		searchButtonConfig.setComponentName("VIEW");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.VIEW }));

		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck.setActionParameterList(Arrays.asList(new Object[] {
				GtnFrameworkCommonConstants.COMSTPLGTNGTN2OUIMODULENETSALESFORMULACON}));
		actionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.DC_ADD_VIEW}));
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCommonConstants.COMSTPLGTNGTN2OUIMODULENETSALESFORMULACTION);
		parameters.add(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(8);
		editActionConfig.setActionParameterList(parameters);
		actionConfigList.add(editActionConfig);
		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		String[] disableField = new String[] { "contractNo", "contractName", "contractHolder", "marketType", "cfpNo",
				"cfpName", "ifpNo", "ifpName", "companyNo", "companyName", "itemNo", "itemName", "netSalesBasisSearch",
				"NSRestContractSearch", "NSDisPlayButton", "netSalesContractSearchResultTable",
				"netSalesAvailableCustomerSearchResultTable", "NSADDButton", GtnFrameworkCommonConstants.RS_REBATE_SETUP_MASS_CHECK,
				"netSalesSelectedCustomerSearchResultTable", "NSSelectedCustomersMassUpdatePanel", GtnFrameworkCommonConstants.DCONTRACT_NO, GtnFrameworkCommonConstants.DCONTRACT_NAME, GtnFrameworkCommonConstants.DCONTRACT_HOLDER, GtnFrameworkCommonConstants.DMARKET_TYPE, GtnFrameworkCommonConstants.DCFP_NO, GtnFrameworkCommonConstants.DCFP_NAME, GtnFrameworkCommonConstants.DIFP_NO, GtnFrameworkCommonConstants.DIFP_NAME,
				"deductionType", "deductionSubType", "deductionCategory", GtnFrameworkCommonConstants.DEDUCTION_ALIAS, GtnFrameworkCommonConstants.PS_NO, GtnFrameworkCommonConstants.PS_NAME, GtnFrameworkCommonConstants.DEDUCTION_NO_CAL, GtnFrameworkCommonConstants.DEDUCTION_NAME_CAL};

		disableAction.setActionParameterList(Arrays.asList(new Object[] { disableField }));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCommonConstants.NET_SALES_ADD_SAVE_BUTTON};

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(false);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		Map<String, String> captionMap = new HashMap<>();
		captionMap.put(GtnFrameworkCommonConstants.NET_SALES_ADD_SAVE_BUTTON, "SAVE");
		changeCaptionActionConfig.setActionParameterList(Arrays.asList(new Object[] { captionMap }));
		actionConfigList.add(changeCaptionActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCopyButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rPCopyButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("rebatePlanDeleteButton", "COPY", true, GtnUIFrameworkComponentType.BUTTON);
		rPCopyButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		rPCopyButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(rPCopyButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.COPY }));

		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck.setActionParameterList(Arrays.asList(new Object[] {
				GtnFrameworkCommonConstants.COMSTPLGTNGTN2OUIMODULENETSALESFORMULACON}));

		actionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { "netSalesFormulaAddView" }));

		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		Object[] disableField = new Object[] { "contractNo", "contractName", "contractHolder", "marketType", "cfpNo",
				"cfpName", "ifpNo", "ifpName", "companyNo", "companyName", "itemNo", "itemName", "netSalesBasisSearch",
				"NSRestContractSearch", "NSDisPlayButton", "netSalesContractSearchResultTable",
				"netSalesAvailableCustomerSearchResultTable", "NSADDButton", GtnFrameworkCommonConstants.RS_REBATE_SETUP_MASS_CHECK,
				"netSalesSelectedCustomerSearchResultTable", "NSSelectedCustomersMassUpdatePanel", GtnFrameworkCommonConstants.DCONTRACT_NO, GtnFrameworkCommonConstants.DCONTRACT_NAME, GtnFrameworkCommonConstants.DCONTRACT_HOLDER, GtnFrameworkCommonConstants.DMARKET_TYPE, GtnFrameworkCommonConstants.DCFP_NO, GtnFrameworkCommonConstants.DCFP_NAME, GtnFrameworkCommonConstants.DIFP_NO, GtnFrameworkCommonConstants.DIFP_NAME,
				"deductionType", "deductionSubType", "deductionCategory", GtnFrameworkCommonConstants.DEDUCTION_ALIAS, GtnFrameworkCommonConstants.PS_NO, GtnFrameworkCommonConstants.PS_NAME, GtnFrameworkCommonConstants.DEDUCTION_NO_CAL, GtnFrameworkCommonConstants.DEDUCTION_NAME_CAL};

		disableAction.setActionParameterList(Arrays.asList(disableField));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCommonConstants.NET_SALES_ADD_SAVE_BUTTON};

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(true);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig editActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCommonConstants.COMSTPLGTNGTN2OUIMODULENETSALESFORMULACTION);
		parameters.add(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(8);

		editActionConfig.setActionParameterList(parameters);

		actionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		List<Object> params = new ArrayList<>();
		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put("formulaName", "");
		resetMap.put("formulaNo", "");
		resetMap.put("formulaId", "");
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		Map<String, String> captionMap = new HashMap<>();
		captionMap.put(GtnFrameworkCommonConstants.NET_SALES_ADD_SAVE_BUTTON, "SAVE");
		changeCaptionActionConfig.setActionParameterList(Arrays.asList(new Object[] { captionMap }));
		actionConfigList.add(changeCaptionActionConfig);
		rPCopyButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addTableResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("netSalesReset02", "RESET", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the listview to default/previous values?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE, "");
		params.add(resetMap);
		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRDeleteButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("netsalesDeleteButton", "DELETE", true, GtnUIFrameworkComponentType.BUTTON);
		cDRDeleteButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cDRDeleteButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDRDeleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.DELETE }));
		actionConfigList.add(setModeActionConfig);
		GtnUIFrameWorkActionConfig isRecordSelectedAction = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		isRecordSelectedAction.setActionParameterList(Arrays.asList(new Object[] {
				GtnFrameworkCommonConstants.COMSTPLGTNGTN2OUIMODULENETSALESFORMULACON}));
		actionConfigList.add(isRecordSelectedAction);

		GtnUIFrameWorkActionConfig editActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.DELETE_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add("/" + GtnWsCDRContants.NSF_SERVICE + "/" + GtnWsCDRContants.NS_DELETE_SERVICE);
		parameters.add(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(8);

		editActionConfig.setActionParameterList(parameters);
		actionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(new String[] { "formulaType", "formulaNo", "formulaName" }));

		actionConfigList.add(loadDataTableActionConfig);

		cDRDeleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
