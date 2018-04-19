package com.stpl.gtn.gtn2o.ui.module.ifp.config;

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
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpEditAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpClassContants;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkIfpLandingScreenConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("Search View", "V001", true);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addIfpSearchCriteriaPanel(componentList);
		addIfpButtonLayout(componentList);
		addIfpResultPanel(componentList);
		addIfpActionButtonLayout(componentList);

	}

	private void addIfpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("searchCriteriaPanel", false, null);
		panel.setComponentName("Search Criteria");
		panel.setAuthorizationIncluded(true);
		panel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
	
		componentList.add(panel);
		addIfpFieldLayout(componentList);
	}

	private void addIfpResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig("resultPanel", false, null);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("resultlayout", true,
				"resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addIfpPagedTableComponent(componentList);
	}

	private void addIfpFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true, "searchCriteriaPanel");
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addIfpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addIFPId(componentList);
		addIFPNo(componentList);
		addIFPName(componentList);
		addIFPStatus(componentList);
		addIFPType(componentList);
		addItemId(componentList);
		addItemNo(componentList);
		addItemName(componentList);
	}

	private void addIFPId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpIdlayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_ID, true, "ifpIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		ifpIdConfig.setAuthorizationIncluded(true);
		ifpIdConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(true, false, false));
		ifpIdConfig.getGtnTextBoxConfig().setMaximumLength(50);
		ifpIdConfig.setComponentName("IFP ID");
		componentList.add(ifpIdConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpIdConfig.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addIFPNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpNolayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig ifpNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_NUMBER, true, "ifpNolayout", GtnUIFrameworkComponentType.TEXTBOX);
		ifpNoConfig.setAuthorizationIncluded(true);
		ifpNoConfig.setComponentName("IFP NO");
		ifpNoConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(true, false, false));
		ifpNoConfig.getGtnTextBoxConfig().setMaximumLength(50);
		componentList.add(ifpNoConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addIFPName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpNamelayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_NAME, true, "ifpNamelayout", GtnUIFrameworkComponentType.TEXTBOX);
		ifpNameConfig.setAuthorizationIncluded(true);
		ifpNameConfig.setComponentName("IFP Name");
		ifpNameConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(true, false, false));
		ifpNameConfig.getGtnTextBoxConfig().setMaximumLength(100);
		componentList.add(ifpNameConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addIFPStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpStatuslayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_STATUS, true, "ifpStatuslayout", GtnUIFrameworkComponentType.COMBOBOX);
		ifpStatus.setAuthorizationIncluded(true);
		ifpStatus.setComponentName("IFP Status");
		componentList.add(ifpStatus);

		GtnUIFrameworkComboBoxConfig ifpStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ifpStatus.setGtnComboboxConfig(ifpStatusConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		ifpStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addIFPType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpTypelayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_TYPE, true, "ifpTypelayout", GtnUIFrameworkComponentType.COMBOBOX);
		ifpType.setAuthorizationIncluded(true);
		ifpType.setComponentName("IFP Type");
		componentList.add(ifpType);

		GtnUIFrameworkComboBoxConfig ifpStatusConfig = configProvider.getComboBoxConfig("IFP_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ifpType.setGtnComboboxConfig(ifpStatusConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		ifpType.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addItemId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("itemIdlayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_ID, true, "itemIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		ifpIdConfig.setAuthorizationIncluded(true);
		ifpIdConfig.setComponentName("Item ID");
		componentList.add(ifpIdConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpIdConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("itemNolayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_NO, true, "itemNolayout", GtnUIFrameworkComponentType.TEXTBOX);
		ifpNoConfig.setAuthorizationIncluded(true);
		ifpNoConfig.setComponentName("Item NO");
		ifpNoConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(true, false, false));
		ifpNoConfig.getGtnTextBoxConfig().setMaximumLength(50);
		componentList.add(ifpNoConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addItemName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("itemNamelayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_NAME, true, "itemNamelayout", GtnUIFrameworkComponentType.TEXTBOX);
		ifpNameConfig.setAuthorizationIncluded(true);
		ifpNameConfig.setComponentName("Item Name");
		ifpNameConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(true, false, false));
		ifpNameConfig.getGtnTextBoxConfig().setMaximumLength(100);
		componentList.add(ifpNameConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		ifpNameConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnSearchButtonlayout",
				true, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnSearch01",
				true, "gtnSearchButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.IFP_ID, GtnFrameworkCommonConstants.IFP_NUMBER,
						GtnFrameworkCommonConstants.IFP_NAME, GtnFrameworkCommonConstants.IFP_STATUS,
						GtnFrameworkCommonConstants.IFP_TYPE, GtnFrameworkCommonConstants.ITEM_ID,
						GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParamsList);
		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.IFP_ID, GtnFrameworkCommonConstants.IFP_NUMBER,
						GtnFrameworkCommonConstants.IFP_NAME, GtnFrameworkCommonConstants.IFP_STATUS,
						GtnFrameworkCommonConstants.IFP_TYPE, GtnFrameworkCommonConstants.ITEM_ID,
						GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		actionConfigList.add(notificationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnReset01layout", true,
				GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnReset01",
				true, "gtnReset01layout", GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("Reset");
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_RESET_HEADER);
		params.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_RESET_001);

		params.add(Arrays.asList(GtnFrameworkCommonConstants.IFP_ID, GtnFrameworkCommonConstants.IFP_NUMBER,
				GtnFrameworkCommonConstants.IFP_NAME, GtnFrameworkCommonConstants.IFP_STATUS,
				GtnFrameworkCommonConstants.IFP_TYPE, GtnFrameworkCommonConstants.ITEM_ID,
				GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME,
				GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null));

		resetActionConfig.setActionParameterList(params);

		actionConfigList.add(resetActionConfig);
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addIfpPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE, true, "resultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyleList);

		componentList.add(searchResultConfig);
		GtnUIFrameworkPagedTableConfig searchResults =  new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setItemPerPage(10);
		searchResults.setSinkItemPerPageWithPageLength(false);
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });

		searchResults.setTableVisibleHeader(new String[] { "System ID", "IFP ID", " IFP No", " IFP Name", "IFP Type",
				"IFP Status", "IFP Category", "IFP Start Date", "IFP End Date", "IFP Designation",
				"Total Dollar Commitment", "Commitment Period", "Total Volume Commitment",
				"Total Market Share Commitment", "Created By", "Created Date", "Parent IFP ID", "Parent IFP Name" });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.ITEM_FAMILYPLAN_SYSTEM_ID,
				GtnFrameworkCommonConstants.IFP_ID, GtnFrameworkCommonConstants.IFP_NUMBER,
				GtnFrameworkCommonConstants.IFP_NAME, GtnFrameworkCommonConstants.IFP_TYPE,
				GtnFrameworkCommonConstants.IFP_STATUS, "ifpCategory", "itemFamilyplanStartDate",
				"itemFamilyplanEndDate", "ifpDesignation", "totalDollarCommitment", "commitmentPeriod",
				"totalVolumeCommitment", "totalMarketshareCommitment", "ifpcreatedBy", "ifpcreatedDate",
				"parentItemFamilyplanId", "parentIetmFamilyplanName" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("itemFamilyPlan");
		searchResults.setQueryName("ifpSearchQuery");
		searchResults.setCustomFilterConfigMap(getIfpCustomFilterConfig());
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ITEM_FAMILY_PLAN);
		searchResults.setDoubleClickEnable(true);
		
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpEditAction.class.getName());
		customAction.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		customAction.addActionParameter(GtnFrameworkCommonConstants.ITEM_FAMILYPLAN_SYSTEM_ID);
		customAction.addActionParameter(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE);
		customAction.addActionParameter(Boolean.TRUE);
		actionConfigList.add(customAction);
		searchResults.setGtnUIFrameWorkActionConfigList(actionConfigList);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addIfpActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		componentList.add(gtnLayout);
		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
		addViewButtonComponent(componentList);
		addIfpExcelButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnAddButtonlayout", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig addButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnAddButton",
				true, "gtnAddButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setAuthorizationIncluded(true);
		addButtonConfig.setComponentName("Add");
		componentList.add(addButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig ifpAddNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		ifpAddNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		ifpAddNavigationActionConfig.addActionParameter("V002");
		actionConfigList.add(ifpAddNavigationActionConfig);

		GtnUIFrameWorkActionConfig ifpAddActionConfig = new GtnUIFrameWorkActionConfig();
		ifpAddActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		ifpAddActionConfig.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_ADD_ACTION);
		ifpAddActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		ifpAddActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_FAMILYPLAN_SYSTEM_ID);
		actionConfigList.add(ifpAddActionConfig);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction.addActionParameter(Boolean.TRUE);
		visibleAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.IFPADD_RESET_BUTTON,
				GtnFrameworkCommonConstants.IFP_ADD_SAVE_BUTTON,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_INFORMATION_LAYOUT));
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig visibleAction1 = new GtnUIFrameWorkActionConfig();
		visibleAction1.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction1.addActionParameter(Boolean.FALSE);
		visibleAction1.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.IFP_ADD_DELETE_BUTTON,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT_ON_VIEW));
		actionConfigList.add(visibleAction1);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		List<String> enableList = Arrays.asList(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_NO,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_NAME,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_STATUS,
				GtnFrameworkCommonConstants.IFP_INFORMATION_IFP_START_DATE,
				GtnFrameworkCommonConstants.IFP_INFORMATION_IFP_END_DATE,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_TYPE,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_CATEGORY,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_DESIGNATION,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_FIELD,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_VALUE,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONGTN_SEARCH01, "ifprightResultTable",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVERIGHT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_ALLRIGHT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_ALL_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEMSMASS_UPDATE_PANEL_LAYOUT, "ifpItemsRecordTypelayout");
		enableAction.addActionParameter(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_ID);
		enableAction.addActionParameter(enableList);

		actionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.IFPTAB_SHEET);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB);
		actionConfigList.add(tabAction);

		addButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnEditButtonlayout", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig editButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnEditButton",
				true, "gtnEditButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		editButtonConfig.setAuthorizationIncluded(true);
		editButtonConfig.setComponentName("Edit");
		componentList.add(editButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams1List = new ArrayList<>();
		alertParams1List.add(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		alertParams1List.add(GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_EDIT_HEADER);
		alertParams1List.add(GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_EDIT);

		alertActionConfig.setActionParameterList(alertParams1List);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_HEADER);
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_EDIT);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig ifpEditNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		ifpEditNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		ifpEditNavigationActionConfig.addActionParameter("V002");
		onSucessActionConfigList.add(ifpEditNavigationActionConfig);

		GtnUIFrameWorkActionConfig ifpEditActionConfig = new GtnUIFrameWorkActionConfig();
		ifpEditActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		ifpEditActionConfig.addActionParameter(GtnFrameworkIfpEditAction.class.getName());
		ifpEditActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		ifpEditActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_FAMILYPLAN_SYSTEM_ID);
		ifpEditActionConfig.addActionParameter(GtnFrameworkCommonConstants.IFPRIGHT_RESULT_TABLE);
		ifpEditActionConfig.addActionParameter(Boolean.TRUE);
		onSucessActionConfigList.add(ifpEditActionConfig);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction.addActionParameter(Boolean.TRUE);
		visibleAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.IFPADD_RESET_BUTTON,
				GtnFrameworkCommonConstants.IFP_ADD_SAVE_BUTTON, GtnFrameworkCommonConstants.IFP_ADD_DELETE_BUTTON,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_INFORMATION_LAYOUT));
		onSucessActionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig visibleAction1 = new GtnUIFrameWorkActionConfig();
		visibleAction1.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction1.addActionParameter(Boolean.FALSE);
		visibleAction1.addActionParameter(
				Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT_ON_VIEW));
		onSucessActionConfigList.add(visibleAction1);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		List<String> enableList = Arrays.asList(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_NO,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_NAME,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_STATUS,
				GtnFrameworkCommonConstants.IFP_INFORMATION_IFP_START_DATE,
				GtnFrameworkCommonConstants.IFP_INFORMATION_IFP_END_DATE,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_TYPE,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_CATEGORY,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_DESIGNATION,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_FIELD,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_VALUE,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONGTN_SEARCH01, "ifprightResultTable",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVERIGHT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_ALLRIGHT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_ALL_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEMSMASS_UPDATE_PANEL_LAYOUT, "ifpItemsRecordTypelayout");
		enableAction.addActionParameter(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_ID);
		enableAction.addActionParameter(enableList);

		onSucessActionConfigList.add(enableAction);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);
		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.IFPTAB_SHEET);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB);
		onSucessActionConfigList.add(tabAction);

		editButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnViewButtonlayout", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpViewButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"gtnViewButton", true, "gtnViewButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		ifpViewButtonConfig.setAuthorizationIncluded(true);
		ifpViewButtonConfig.setComponentName("View");
		componentList.add(ifpViewButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParams1List = new ArrayList<>();
		alertParams1List.add(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		alertParams1List.add(GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_ERROR_HEADER);
		alertParams1List.add(GtnFrameworkIfpStringContants.GTN_IFP_VALIDATION_MSG_VIEW);

		alertActionConfig.setActionParameterList(alertParams1List);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_HEADER);
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_VIEW);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig ifpNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		ifpNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		ifpNavigationActionConfig.addActionParameter("V002");
		onSucessActionConfigList.add(ifpNavigationActionConfig);

		GtnUIFrameWorkActionConfig ifpViewtActionConfig = new GtnUIFrameWorkActionConfig();
		ifpViewtActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		ifpViewtActionConfig.addActionParameter(GtnFrameworkIfpEditAction.class.getName());
		ifpViewtActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		ifpViewtActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_FAMILYPLAN_SYSTEM_ID);
		ifpViewtActionConfig.addActionParameter("ifprightResultTableOnView");
		ifpViewtActionConfig.addActionParameter(Boolean.FALSE);
		onSucessActionConfigList.add(ifpViewtActionConfig);

		GtnUIFrameWorkActionConfig ifpVisibleAction = new GtnUIFrameWorkActionConfig();
		ifpVisibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		ifpVisibleAction.addActionParameter(Boolean.FALSE);
		ifpVisibleAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.IFPADD_RESET_BUTTON,
				GtnFrameworkCommonConstants.IFP_ADD_SAVE_BUTTON, GtnFrameworkCommonConstants.IFP_ADD_DELETE_BUTTON,
				GtnFrameworkCommonConstants.IFP_ITEMSMASS_UPDATE_PANEL_LAYOUT,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_INFORMATION_LAYOUT));
		onSucessActionConfigList.add(ifpVisibleAction);

		GtnUIFrameWorkActionConfig visibleAction1 = new GtnUIFrameWorkActionConfig();
		visibleAction1.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction1.addActionParameter(Boolean.TRUE);
		visibleAction1.addActionParameter(
				Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT_ON_VIEW));
		onSucessActionConfigList.add(visibleAction1);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);

		List<String> enableList = Arrays.asList(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_NO,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_NAME,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_STATUS,
				GtnFrameworkCommonConstants.IFP_INFORMATION_IFP_START_DATE,
				GtnFrameworkCommonConstants.IFP_INFORMATION_IFP_END_DATE,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_TYPE,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_CATEGORY,
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_DESIGNATION, "ifpInformationTabParentIFPId",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_FIELD,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_VALUE,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONGTN_SEARCH01,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVERIGHT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_ALLRIGHT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_ALL_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.IFP_ITEMSMASS_UPDATE_PANEL_LAYOUT);
		enableAction.addActionParameter(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_ID);
		enableAction.addActionParameter(enableList);

		onSucessActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.IFPTAB_SHEET);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB);
		onSucessActionConfigList.add(tabAction);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);

		ifpViewButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	/**
	 *
	 * @param componentList
	 */
	private void addIfpExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"gtnIFPLandingScreenExcelButtonlayout", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig(null, true,
				"gtnIFPLandingScreenExcelButtonlayout", GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		excelButtonConfig.setComponentName("View");
		componentList.add(excelButtonConfig);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider
				.getExcelBtnconfig("Item Family Plan", true, GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE, false);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getIfpCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> ifpCustomFilterConfigMap = new HashMap<>();
		String[] propertyIds = GtnFrameworkIfpStringContants.getIfpCustomPropertyIds();
		String[] listNameArray = GtnFrameworkIfpStringContants.getIfpListNameArray();
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig ifpCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			ifpCustomFilterConfig.setPropertId(propertyIds[i]);
			ifpCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig ifpCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			ifpCustomFilterComponentConfig.setComponentId("customFilterComboBox");
			ifpCustomFilterComponentConfig.setComponentName("customFilterComboBox");
			ifpCustomFilterComponentConfig.setGtnComboboxConfig(configProvider.getComboBoxConfig(listNameArray[i],
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX));
			ifpCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			ifpCustomFilterConfig.setGtnComponentConfig(ifpCustomFilterComponentConfig);
			ifpCustomFilterConfigMap.put(ifpCustomFilterConfig.getPropertId(), ifpCustomFilterConfig);

		}
		return ifpCustomFilterConfigMap;
	}
}
