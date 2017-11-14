package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCompanyMasterSearchConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig companyMasterSearchView = configProvider.getViewConfig("Search View", "V001", true);
		GtnUIFrameWorkActionConfig reloadHelperTableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RELOAD_HELPER_TABLE_ACTION);
		companyMasterSearchView.addViewAction(reloadHelperTableAction);
		addComponentList(companyMasterSearchView);
		return companyMasterSearchView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addCMSearchCriteriaPanel(componentList);
		addCMButtonLayout(componentList);
		addCMResultPanel(componentList);
		addCMActionButtonLayout(componentList);

	}

	private void addCMSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("searchCriteriaPanel", false, null);
		panel.setComponentName("Search Criteria");
		panel.setAuthorizationIncluded(true);
		panel.setComponentWidth("100%");

		componentList.add(panel);
		addCMFieldLayout(componentList);
	}

	private void addCMResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig("resultPanel", false, null);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setComponentWidth("100%");
		componentList.add(panelConfig);
		addCMResultLayout(componentList);
	}

	private void addCMResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("resultlayout", true,
				"resultPanel");
		componentList.add(gtnLayout);
		addCMPagedTableComponent(componentList);
	}

	private void addCMFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnFrameworkCompanyStringContants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel",
				GtnUIFrameworkLayoutType.COL3_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.addComponentStyle("gtnGrid-single-ln-layout-3");
		componentList.add(gtnLayout);
		addCMFieldComponent(componentList);
	}

	private void addCMButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnFrameworkCompanyStringContants.SEARCH_BUTTON_LAYOUT, false, null,
				GtnUIFrameworkLayoutType.CSS_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth("20%");
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(GtnWsNumericConstants.TWO);
		componentList.add(gtnLayout);
		addCMSearchButtonComponent(componentList);
		addCMResetButtonComponent(componentList);
	}

	private void addCMFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addCMSearchGeneralSearcField(componentList, GtnFrameworkCompanyStringContants.COMPANY_ID, "Company ID",
				GtnUIFrameworkComponentType.TEXTBOX, GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		addCMSearchGeneralSearcField(componentList, GtnFrameworkCompanyStringContants.COMPANY_NO, "Company No",
				GtnUIFrameworkComponentType.TEXTBOX, GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		addCMSearchGeneralSearcField(componentList, GtnFrameworkCompanyStringContants.COMPANY_NAME, "Company Name",
				GtnUIFrameworkComponentType.TEXTBOX, GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		addCMSearchCompanyStatus(componentList);
		addCMSearchCompanyType(componentList);
		addCMSearchCompanyCategory(componentList);
		addCMSearchCompanyGroup(componentList);
		addCMSearchCompanyTradeClass(componentList);
		addCMSearchCompanyQualifierName(componentList);
		addCMSearchGeneralSearcField(componentList, GtnFrameworkCompanyStringContants.COMP_IDENTIFIER_NAME,
				"Company Identifier", GtnUIFrameworkComponentType.TEXTBOX,
				GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
	}

	private GtnUIFrameworkComponentConfig addCMSearchGeneralSearcField(
			List<GtnUIFrameworkComponentConfig> componentList, String componentId, String componentName,
			GtnUIFrameworkComponentType componentType, GtnUIFrameworkConditionalValidationType validationType) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(componentId + "layout", true,
				GtnFrameworkCompanyStringContants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				componentId + "layout", componentType);
		companyIdConfig.setComponentName(componentName);
		companyIdConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(validationType));
		companyIdConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(companyIdConfig);

		return companyIdConfig;
	}

	private void addCMSearchCompanyStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig companyStatus = addCMSearchGeneralSearcField(componentList,
				GtnFrameworkCompanyStringContants.COMPANY_STATUS, "Company Status",
				GtnUIFrameworkComponentType.COMBOBOX, GtnUIFrameworkConditionalValidationType.NOT_NULL);

		companyStatus.addDependentComponent(GtnFrameworkCompanyStringContants.COMPANY_CATEGORY);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig valueChangeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION);
		actionConfigList.add(valueChangeAction);

		companyStatus.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addCMSearchCompanyType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig companyType = addCMSearchGeneralSearcField(componentList,
				GtnFrameworkCompanyStringContants.COMPANY_TYPE, "Company Type", GtnUIFrameworkComponentType.COMBOBOX,
				GtnUIFrameworkConditionalValidationType.NOT_NULL);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMPANY_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addCMSearchCompanyCategory(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig companyCategory = addCMSearchGeneralSearcField(componentList,
				GtnFrameworkCompanyStringContants.COMPANY_CATEGORY, "Company Category",
				GtnUIFrameworkComponentType.COMBOBOX, GtnUIFrameworkConditionalValidationType.NOT_NULL);

		GtnUIFrameworkComboBoxConfig companyCategoryConfig = configProvider.getComboBoxConfig("COMPANY_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyCategory.setGtnComboboxConfig(companyCategoryConfig);

	}

	private void addCMSearchCompanyGroup(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig companyGroup = addCMSearchGeneralSearcField(componentList,
				GtnFrameworkCompanyStringContants.COMPANY_GROUP, "Company Group", GtnUIFrameworkComponentType.COMBOBOX,
				GtnUIFrameworkConditionalValidationType.NOT_NULL);

		GtnUIFrameworkComboBoxConfig companyGroupConfig = configProvider.getComboBoxConfig("COMPANY_GROUP",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyGroup.setGtnComboboxConfig(companyGroupConfig);

	}

	private void addCMSearchCompanyTradeClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig tradeClass = addCMSearchGeneralSearcField(componentList,
				GtnFrameworkCompanyStringContants.TRADE_CLASS, "Trade Class", GtnUIFrameworkComponentType.COMBOBOX,
				GtnUIFrameworkConditionalValidationType.NOT_NULL);

		GtnUIFrameworkComboBoxConfig tradeClassConfig = configProvider.getComboBoxConfig("COMPANY_TRADE_CLASS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		tradeClass.setGtnComboboxConfig(tradeClassConfig);

	}

	private void addCMSearchCompanyQualifierName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig companyQualifierName = addCMSearchGeneralSearcField(componentList,
				GtnFrameworkCompanyStringContants.COMP_QUALIFIER_NAME, "Company Qualifier Name",
				GtnUIFrameworkComponentType.COMBOBOX, GtnUIFrameworkConditionalValidationType.NOT_NULL);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = configProvider.getComboBoxConfig("CompanyIdentifier",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.COMPANY_CUSTOM_VALUE_CHANGE_QUALIFIER);
		actionConfigList.add(customAction);
		companyQualifierName.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCMSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cmSearchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnSearch01",
				true, GtnFrameworkCompanyStringContants.SEARCH_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		cmSearchButtonConfig.setComponentName("SEARCH");
		cmSearchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(cmSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> cmSearchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig cmSearchValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		cmSearchValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCompanyStringContants.COMPANY_ID,
				GtnFrameworkCompanyStringContants.COMPANY_NO, GtnFrameworkCompanyStringContants.TRADE_CLASS,
				GtnFrameworkCompanyStringContants.COMP_IDENTIFIER_NAME,
				GtnFrameworkCompanyStringContants.COMP_QUALIFIER_NAME, GtnFrameworkCompanyStringContants.COMPANY_GROUP,
				GtnFrameworkCompanyStringContants.COMPANY_CATEGORY, GtnFrameworkCompanyStringContants.COMPANY_TYPE,
				GtnFrameworkCompanyStringContants.COMPANY_NAME, GtnFrameworkCompanyStringContants.COMPANY_STATUS));

		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParamsList);
		cmSearchValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)));
		cmSearchActionConfigList.add(cmSearchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCompanyStringContants.COMPANY_ID,
				GtnFrameworkCompanyStringContants.COMPANY_NO, GtnFrameworkCompanyStringContants.COMPANY_NAME,
				GtnFrameworkCompanyStringContants.COMPANY_STATUS, GtnFrameworkCompanyStringContants.COMPANY_TYPE,
				GtnFrameworkCompanyStringContants.TRADE_CLASS, GtnFrameworkCompanyStringContants.COMPANY_CATEGORY,
				GtnFrameworkCompanyStringContants.COMP_QUALIFIER_NAME,
				GtnFrameworkCompanyStringContants.COMP_IDENTIFIER_NAME));

		cmSearchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE);
		cmSearchActionConfigList.add(notificationActionConfig);

		cmSearchButtonConfig.setGtnUIFrameWorkActionConfigList(cmSearchActionConfigList);

	}

	private void addCMResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnReset01",
				true, GtnFrameworkCompanyStringContants.SEARCH_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);

		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_RESET_HEADER);
		params.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_RESET_MSG);

		params.add(Arrays.asList(GtnFrameworkCompanyStringContants.COMPANY_ID,
				GtnFrameworkCompanyStringContants.COMPANY_NO, GtnFrameworkCompanyStringContants.COMPANY_NAME,
				GtnFrameworkCompanyStringContants.COMPANY_STATUS, GtnFrameworkCompanyStringContants.COMPANY_TYPE,
				GtnFrameworkCompanyStringContants.TRADE_CLASS, GtnFrameworkCompanyStringContants.COMP_QUALIFIER_NAME,
				GtnFrameworkCompanyStringContants.COMP_IDENTIFIER_NAME, GtnFrameworkCompanyStringContants.COMPANY_GROUP,
				GtnFrameworkCompanyStringContants.COMPANY_CATEGORY,
				GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, null, null, GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null, null));

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCMPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE, true, "resultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setAuthorizationIncluded(true);

		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setSinkItemPerPageWithPageLength(false);
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResults.setTableVisibleHeader(new String[] { "System ID", "Company ID", "Company No", "Company Name",
				"Company Status", "Company Type", "Company Start Date", "Company End Date", "Trade Class",
				"Trade Class Start Date", "Trade Class End Date", "Company Group", "Company Category", "Organization ",
				"Financial System", "Parent Company No.", "Parent Start Date", "Parent End Date",
				"Prior Parent Company No.", "Prior Parent Start Date", "Region Code", "UDC 1", "UDC 2", "UDC 3",
				"UDC 4", "UDC 5", "UDC 6", "Address 1", "Address 2", "Zip Code", "City", "State", "Country" });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID,
				GtnFrameworkCompanyStringContants.COMPANY_ID, GtnFrameworkCompanyStringContants.COMPANY_NO,
				GtnFrameworkCompanyStringContants.COMPANY_NAME, GtnFrameworkCompanyStringContants.COMPANY_STATUS,
				GtnFrameworkCompanyStringContants.COMPANY_TYPE, "companyStartDate", "companyEndDate",
				"companyTradeClass", "tradeClassStartDate", "tradeClassEndDate",
				GtnFrameworkCompanyStringContants.COMPANY_GROUP, GtnFrameworkCompanyStringContants.COMPANY_CATEGORY,
				"organizationKey", "financialSystem", "parentCompanyNo", "parentStartDate", "parentEndDate",
				"priorParentCompanyNo", "priorParentStartDate", "regionCode", "udc1", "udc2", "udc3", "udc4", "udc5",
				"udc6", "address1", "address2", "zipCode", "city", "state", "country" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("companyMaster");
		searchResults.setQueryName("SearchQuery");

		searchResults.setCustomFilterConfigMap(getCustomFilterConfig());
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);

		searchResults.setDoubleClickEnable(true);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCompanyClassContants.COMPANY_EDIT_ACTION);
		parameters.add(GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE);
		parameters.add(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID);
		editActionConfig.setActionParameterList(parameters);
		actionConfigList.add(editActionConfig);

		searchResults.setGtnUIFrameWorkActionConfigList(actionConfigList);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addCMActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCompanyStringContants.ACTION_BUTTON_LAYOUT, false, null);
		gtnLayout.setComponentWidth("30%");
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(GtnWsNumericConstants.THREE);
		componentList.add(gtnLayout);
		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
		addViewButtonComponent(componentList);
		addExcelButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnAddButton",
				true, GtnFrameworkCompanyStringContants.ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("ADD");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		Object navigationConfig = "V002";
		navigationActionConfig.setActionParameterList(Arrays.asList(navigationConfig));

		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig enableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ENABLE_ACTION);
		Object[] enableField = new String[] { GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_ID,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_NO,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_NAME,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_STATUS,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_START_DATE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_END_DATE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_CATEGORY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_GROUP,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_TYPE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_ORG_KEY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_SOURCE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_FINANCE_SYSTEM,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_SYSTEM_ID,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_CREATED_BY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_CREATED_DATE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_MODIFIED_BY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_MODIFIED_DATE,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_ADDRESS_1,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_CITY,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_ZIP_CODE,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_COUNTRY,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_ADDRESS_2,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_STATE,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_REGION_CODE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC1,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC2,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC3,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC4,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC5,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC6 };

		enableAction.setActionParameterList(Arrays.asList(enableField));
		actionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig visibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCompanyStringContants.COMPANY_ADD_SAVE_BUTTON,
				GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESET_BTN,
				GtnFrameworkCompanyStringContants.COMPANY_MASTER_DELETE_BTN,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFO_PANEL,
				GtnFrameworkCompanyStringContants.IDENTIFIER_REMOVE_BTN_LAYOUT,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_PANEL,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_REMOVE_BTN_LAYOUT,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_PANEL,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_REMOVE_BTN_LAYOUT };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(true);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig resetAction = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionList = new ArrayList<>();
		resetActionList
				.add(GtnFrameworkCompanyClassContants.COMPANY_PACKAGE + "GtnUIFrameworkCompanyMasterAddResetAction");

		resetAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		resetAction.setActionParameterList(resetActionList);
		actionConfigList.add(resetAction);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnEditButton",
				true, GtnFrameworkCompanyStringContants.ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("EDIT");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> companyMasterSearchEditActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();

		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_EDIT_MSG_HEADER);
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_EDIT_STRING);

		alertActionConfig.setActionParameterList(alertParamsList);
		companyMasterSearchEditActionConfigList.add(alertActionConfig);
		GtnUIFrameWorkActionConfig companyMasterSearchEditNavigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		Object navigationConfig = "V002";
		companyMasterSearchEditNavigationActionConfig.setActionParameterList(Arrays.asList(navigationConfig));
		companyMasterSearchEditActionConfigList.add(companyMasterSearchEditNavigationActionConfig);

		GtnUIFrameWorkActionConfig companyMasterSearchEditActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCompanyClassContants.COMPANY_EDIT_ACTION);
		parameters.add(GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE);
		parameters.add(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID);

		companyMasterSearchEditActionConfig.setActionParameterList(parameters);
		companyMasterSearchEditActionConfigList.add(companyMasterSearchEditActionConfig);

		GtnUIFrameWorkActionConfig enableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ENABLE_ACTION);
		Object[] disableField = new Object[] { GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_ID,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_NO,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_NAME,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_STATUS,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_START_DATE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_END_DATE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_CATEGORY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_GROUP,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_TYPE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_ORG_KEY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_FINANCE_SYSTEM,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_ADDRESS_1,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_CITY,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_ZIP_CODE,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_COUNTRY,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_ADDRESS_2,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_STATE,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_REGION_CODE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC1,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC2,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC3,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC4,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC5,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC6,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_MODE,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_YEAR,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_RESET_BTN,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_OPEN_BTN,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_CLOSE_BTN };

		enableAction.setActionParameterList(Arrays.asList(disableField));
		companyMasterSearchEditActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig customActionEdit = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> parametersAction = new ArrayList<>();
		parametersAction.add(GtnFrameworkCompanyClassContants.COMPANY_CUSTOM_EDIT_ACTION);
		customActionEdit.setActionParameterList(parametersAction);
		companyMasterSearchEditActionConfigList.add(customActionEdit);

		GtnUIFrameWorkActionConfig visibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCompanyStringContants.COMPANY_ADD_SAVE_BUTTON,
				GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESET_BTN,
				GtnFrameworkCompanyStringContants.COMPANY_MASTER_DELETE_BTN,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFO_PANEL,
				GtnFrameworkCompanyStringContants.IDENTIFIER_REMOVE_BTN_LAYOUT,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_PANEL,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_REMOVE_BTN_LAYOUT,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_PANEL,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_REMOVE_BTN_LAYOUT };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(true);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		companyMasterSearchEditActionConfigList.add(visibleAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(companyMasterSearchEditActionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnViewButton",
				true, GtnFrameworkCompanyStringContants.ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("VIEW");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig companyMasterSearchViewActionConfigList = new GtnUIFrameWorkActionConfig();

		companyMasterSearchViewActionConfigList.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VIEW_MSG_HEADER);
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_VIEW);

		companyMasterSearchViewActionConfigList.setActionParameterList(alertParamsList);
		actionConfigList.add(companyMasterSearchViewActionConfigList);

		GtnUIFrameWorkActionConfig companyMasterSearchViewNavigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		Object navigationConfig = "V002";
		companyMasterSearchViewNavigationActionConfig.setActionParameterList(Arrays.asList(navigationConfig));

		actionConfigList.add(companyMasterSearchViewNavigationActionConfig);

		GtnUIFrameWorkActionConfig companyMasterSearchViewActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCompanyClassContants.COMPANY_EDIT_ACTION);
		parameters.add(GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE);
		parameters.add(GtnFrameworkCompanyStringContants.COMPANY_MASTER_SID);
		companyMasterSearchViewActionConfig.setActionParameterList(parameters);
		actionConfigList.add(companyMasterSearchViewActionConfig);

		GtnUIFrameWorkActionConfig disableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.DISABLE_ACTION);
		Object[] disableField = new String[] { GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_ID,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_NO,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_NAME,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_STATUS,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_START_DATE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_END_DATE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_CATEGORY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_GROUP,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_TYPE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_ORG_KEY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_SOURCE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_COMPANY_FINANCE_SYSTEM,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_SYSTEM_ID,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_CREATED_BY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_CREATED_DATE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_MODIFIED_BY,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_MODIFIED_DATE,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_ADDRESS_1,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_CITY,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_ZIP_CODE,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_COUNTRY,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_ADDRESS_2,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_STATE,
				GtnFrameworkCompanyStringContants.ADDRESS_INFO_REGION_CODE,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC1,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC2,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC3,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC4,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC5,
				GtnFrameworkCompanyStringContants.COMPANY_INFO_UDC6,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_MODE,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_YEAR,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_RESET_BTN,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_OPEN_BTN,
				GtnFrameworkCompanyStringContants.FINANCIAL_CLOSE_CLOSE_BTN };

		disableAction.setActionParameterList(Arrays.asList(disableField));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig customActionView = new GtnUIFrameWorkActionConfig();

		customActionView.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> parametersAction = new ArrayList<>();
		parametersAction.add(GtnFrameworkCompanyClassContants.COMPANY_VIEW_ACTION);
		customActionView.setActionParameterList(parametersAction);
		actionConfigList.add(customActionView);

		GtnUIFrameWorkActionConfig visibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCompanyStringContants.COMPANY_ADD_SAVE_BUTTON,
				GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESET_BTN,
				GtnFrameworkCompanyStringContants.COMPANY_MASTER_DELETE_BTN,
				GtnFrameworkCompanyStringContants.IDENTIFIER_INFO_PANEL,
				GtnFrameworkCompanyStringContants.IDENTIFIER_REMOVE_BTN_LAYOUT,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_INFO_PANEL,
				GtnFrameworkCompanyStringContants.TRADE_CLASS_REMOVE_BTN_LAYOUT,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_INFO_PANEL,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_REMOVE_BTN_LAYOUT };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(false);
		visibleParameters.add(Arrays.asList(visibleFields));
		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	/**
	 * 
	 * @param componentList
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"cmMainExceBbutton", true, GtnFrameworkCompanyStringContants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		componentList.add(excelButtonConfig);
		excelButtonConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider.getExcelBtnconfig(
				"COMPANYMASTER", true, GtnFrameworkCompanyStringContants.COMPANY_MASTER_RESULT_TABLE, false);
		gtnUIFrameworkExcelButtonConfig.setTitleNeeded(true);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkCompanyStringContants.COMPANY_TYPE,
				GtnFrameworkCompanyStringContants.COMPANY_STATUS, "companyTradeClass",
				GtnFrameworkCompanyStringContants.COMPANY_GROUP, GtnFrameworkCompanyStringContants.COMPANY_CATEGORY,
				"organizationKey", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "state", "country" };
		String[] listNameArray = { "COMPANY_TYPE", "STATUS", "COMPANY_TRADE_CLASS", "COMPANY_GROUP", "COMPANY_CATEGORY",
				"ORGANIZATION_KEY", "COMP_UDC1", "COMP_UDC2", "COMP_UDC3", "COMP_UDC4", "COMP_UDC5", "COMP_UDC6",
				"STATE", "COUNTRY" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			customFilterConfig.setPropertId(propertyIds[i]);
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig companyMasterSearchFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			companyMasterSearchFilterComponentConfig.setComponentId("customFilterComboBox");
			companyMasterSearchFilterComponentConfig.setComponentName("customFilterComboBox");
			companyMasterSearchFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			companyMasterSearchFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			companyMasterSearchFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			companyMasterSearchFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customFilterConfig.setGtnComponentConfig(companyMasterSearchFilterComponentConfig);
			customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);

		}
		return customFilterConfigMap;
	}
}
