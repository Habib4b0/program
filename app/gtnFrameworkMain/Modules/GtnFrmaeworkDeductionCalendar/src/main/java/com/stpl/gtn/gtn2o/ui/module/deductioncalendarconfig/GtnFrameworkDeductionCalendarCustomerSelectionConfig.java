/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.deductioncalendarconfig;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
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
public class GtnFrameworkDeductionCalendarCustomerSelectionConfig {

	public void addCustomerSelectionTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB);
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);
		addSearchCriteriaPanel(componentList);
		addButtonLayout(componentList);
		addResultPanel(componentList);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Customer Search");
		panel.setComponentId("customerSearchPanel");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(true);
		panel.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB);

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
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setParentComponentId("customerSearchPanel");
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
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addCompanyNo(componentList);
		addCompanyName(componentList);
		addCompanyType(componentList);
		addCompanyCategory(componentList);
		addCompanyGroup(componentList);
		addCompanyTradeClass(componentList);
		addCompanyQualifierName(componentList);
		addCompanyIdentifier(componentList);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyNolayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SEARCH_LAYOUT);

		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = new GtnUIFrameworkComponentConfig();
		companyNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentId(GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO);
		companyNoConfig.setComponentName("Company NO");
		companyNoConfig.setParentComponentId("companyNolayout");
		companyNoConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyNamelayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = new GtnUIFrameworkComponentConfig();
		companyNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setComponentId(GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO);
		companyNameConfig.setComponentName("Company Name");
		companyNameConfig.setParentComponentId("companyNamelayout");
		companyNameConfig.setAddToParent(true);

		componentList.add(companyNameConfig);
	}

	private void addCompanyType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyTypelayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = new GtnUIFrameworkComponentConfig();
		companyType.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentId(GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE);
		companyType.setComponentName("Company Type");
		companyType.setParentComponentId("companyTypelayout");
		gtnLayout.setGtnLayoutConfig(layout);
		companyType.setAddToParent(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType("COMPANY_TYPE");
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addCompanyCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyCategorylayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyCategory = new GtnUIFrameworkComponentConfig();
		companyCategory.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyCategory.setComponentId("companyCategory");
		companyCategory.setComponentName("Company Category");
		companyCategory.setParentComponentId("companyCategorylayout");
		companyCategory.setAddToParent(true);

		componentList.add(companyCategory);

		GtnUIFrameworkComboBoxConfig companyCategoryConfig = new GtnUIFrameworkComboBoxConfig();
		companyCategoryConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyCategoryConfig.setComboBoxType("COMPANY_CATEGORY");
		companyCategory.setGtnComboboxConfig(companyCategoryConfig);

	}

	private void addCompanyGroup(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyGrouplayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyGroup = new GtnUIFrameworkComponentConfig();
		companyGroup.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyGroup.setComponentId("companyGroup");
		companyGroup.setComponentName("Company Group");
		companyGroup.setParentComponentId("companyGrouplayout");
		companyGroup.setAddToParent(true);

		componentList.add(companyGroup);

		GtnUIFrameworkComboBoxConfig companyGroupConfig = new GtnUIFrameworkComboBoxConfig();
		companyGroupConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyGroupConfig.setComboBoxType("COMPANY_GROUP");
		companyGroup.setGtnComboboxConfig(companyGroupConfig);

	}

	private void addCompanyTradeClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("tradeClasslayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig tradeClass = new GtnUIFrameworkComponentConfig();
		tradeClass.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		tradeClass.setComponentId("tradeClass");
		tradeClass.setComponentName("Trade Class");
		tradeClass.setParentComponentId("tradeClasslayout");
		tradeClass.setAddToParent(true);

		componentList.add(tradeClass);

		GtnUIFrameworkComboBoxConfig tradeClassConfig = new GtnUIFrameworkComboBoxConfig();
		tradeClassConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		tradeClassConfig.setComboBoxType("COMPANY_TRADE_CLASS");

		tradeClass.setGtnComboboxConfig(tradeClassConfig);

	}

	private void addCompanyQualifierName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyQualifierNamelayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = new GtnUIFrameworkComponentConfig();
		companyQualifierName.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyQualifierName.setComponentId("companyQualifierName");
		companyQualifierName.setComponentName("Company Qualifier Name");

		companyQualifierName.setParentComponentId("companyQualifierNamelayout");
		companyQualifierName.setAddToParent(true);

		componentList.add(companyQualifierName);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = new GtnUIFrameworkComboBoxConfig();
		companyQualifierNameConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyQualifierNameConfig.setComboBoxType("CompanyIdentifier");
		companyQualifierNameConfig.setValueChangeListenerClassName(
				"com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIFrameworkCMQualiferComboValueChangeListener");
		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);

	}

	private void addCompanyIdentifier(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("companyIdentifierlayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SEARCH_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = new GtnUIFrameworkComponentConfig();
		companyIdentifierConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setComponentId("companyIdentifier");
		companyIdentifierConfig.setComponentName("Company Identifier");
		companyIdentifierConfig.setParentComponentId("companyIdentifierlayout");
		companyIdentifierConfig.setAddToParent(true);
		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(false);
		textboxConfig.setRequired(false);
		textboxConfig.setImmediate(true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("gtnSearch01");
		searchButtonConfig.setComponentName("Search");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add("searchResultTable");

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO,
				"companyStatus", GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE, "tradeClass", "companyQualifierName", "companyIdentifier" }));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		List<String> validationFields = new ArrayList<>();
		validationFields.add(GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID);
		validationActionConfig.setFieldValues(validationFields);

		actionConfigList.add(validationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("gtnReset01");
		searchButtonConfig.setComponentName("Reset");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID, "");
		resetMap.put(GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE, null);

		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId("searchResultTable");
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setParentComponentId("resultlayout");
		searchResultConfig.setAddToParent(true);
		searchResultConfig.setParentComponentId(GtnFrameworkCommonConstants.CUSTOMER_SELECTION_TAB);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_INTEGER, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResults.setTableVisibleHeader(new String[] { "System ID", "Company ID", "Company No", "Company Name",
				"Company Status", "Company Type", "Company Start Date", "Company End Date", "Trade Class",
				"Trade Class Start Date", "Trade Class End Date", "Company Group", "Company Category", "Organization ",
				"Financial System", "Parent Company No.", "Parent Start Date", "Parent End Date",
				"Prior Parent Company No.", "Prior Parent Start Date", "Region Code", "UDC 1", "UDC 2", "UDC 3",
				"UDC 4", "UDC 5", "UDC 6", "Address 1", "Address 2", "Zip Code", "City", "State", "Country" });
		searchResults.setTableColumnMappingId(new Object[] { "companyMasterSid", GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, "companyStatus", GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE, "companyStartDate", "companyEndDate",
				"companyTradeClass", "tradeClassStartDate", "tradeClassEndDate", "companyGroup", "companyCategory",
				"organizationKey", "financialSystem", "parentCompanyNo", "parentStartDate", "parentEndDate",
				"priorParentCompanyNo", "priorParentStartDate", "regionCode", "udc1", "udc2", "udc3", "udc4", "udc5",
				"udc6", "address1", "address2", "zipCode", "city", "state", "country" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("companyMaster");
		searchResults.setQueryName("SearchQuery");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("customFilterComboBox");
		customFilterComponentConfig.setComponentName("customFilterComboBox");
		customFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
		customFilterComponentConfig.getGtnComboboxConfig().setComboBoxType("COMPANY_TYPE");
		customFilterComponentConfig.getGtnComboboxConfig()
				.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}



}
