/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkDeductionCalendarPopUpConfig {

	private final GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

	public GtnUIFrameworkViewConfig getSearchView() {
		gtnFrameworkConfigurationFactory.setViewId(GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW);
		gtnFrameworkConfigurationFactory.setViewName(GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW);
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
		addResultPanel(componentList);
		addActionButtonLayout(componentList);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"searchCriteriaPanel", "Search Criteria", false, GtnUIFrameworkComponentType.PANEL);
		panel.setAuthorizationIncluded(true);
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
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Deduction Calendar No");
		companyIdConfig.setParentComponentId("deductionCalendarNoLayout");
		companyIdConfig.setAddToParent(true);

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
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentId("deductionCalendarName");
		companyNoConfig.setComponentName("Deduction Calendar Name");
		companyNoConfig.setParentComponentId("deductionCalendarNameLayout");
		companyNoConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
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
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentId("customerGroupDesc");
		companyNameConfig.setComponentName("Customer Group Desc");
		companyNameConfig.setParentComponentId("customerGroupDescLayout");
		companyNameConfig.setAddToParent(true);

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
		companyCategory.setAuthorizationIncluded(true);
		companyCategory.setComponentId("category");
		companyCategory.setComponentName("Category");
		companyCategory.setParentComponentId("categoryLayout");
		companyCategory.setAddToParent(true);

		componentList.add(companyCategory);

		GtnUIFrameworkComboBoxConfig companyCategoryConfig = new GtnUIFrameworkComboBoxConfig();
		companyCategoryConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyCategoryConfig.setComboBoxType("COMPANY_CATEGORY");
		companyCategory.setGtnComboboxConfig(companyCategoryConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig("dc01",
				"Search", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO }));

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
		actionParams.add(GtnFrameworkCommonConstants.DC_POP_UP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] { "dcId" }));

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
		resetMap.put("ruleName", "");
		resetMap.put("ruleNo", "");
		resetMap.put("ruleType", null);
		resetMap.put("ruleCategory", null);
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.DC_POP_UP_SEARCH_RESULT_TABLE, "Results", true,
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
				String.class, String.class, String.class, String.class });
		searchResults.setTableVisibleHeader(new String[] { "Deduction Calendar No", "Deduction Calendar Name",
				"Deduction Calendar Desc", "Category", "Creation Date", "Created By", "Modified Date", "Modified By" });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO,
				"deductionCalendarName", "deductionCalendarDesc", "category", "creationDate", "createdBy",
				"modifiedDate", "modifiedBy" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("DeductionCalendar");
		searchResults.setQueryName("deductionCalendarSearch");
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.DEDUCTION_CALENDAR);

		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(
				gtnFrameworkConfigurationFactory.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT));
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRAddButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("dcPopUpViewSelectButton", "SELECT", true, GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setAuthorizationIncluded(true);
		cDRAddButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDRAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.DC_POP_UP_SEARCH_RESULT_TABLE);
		actionParameter.add("dcCalculationsSecondaryRebatePlan");
		actionParameter.add(Arrays.asList(new String[] { "secondaryRebatePlanName" }));
		actionParameter.add(Arrays.asList(new String[] { "dcCalculationsSecondaryRebatePlan" }));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDRAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDREditButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("dcPopUpViewEditButton", "CLOSE", true, GtnUIFrameworkComponentType.BUTTON);
		cDREditButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		cDREditButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cDREditButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDREditButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
