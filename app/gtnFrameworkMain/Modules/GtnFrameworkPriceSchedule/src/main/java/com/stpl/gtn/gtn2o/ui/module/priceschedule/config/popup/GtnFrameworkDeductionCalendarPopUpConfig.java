/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.config.popup;

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
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkDeductionCalendarPopUpConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig deductionCalendarPopUpView = configProvider.getViewConfig(
				GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW,
				GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW, false);
		addComponentList(deductionCalendarPopUpView);
		return deductionCalendarPopUpView;
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

		GtnUIFrameworkComponentConfig deductionCalendarPopUpSearchPanel = configProvider
				.getPanelConfig("searchCriteriaPanel", false, null);
		deductionCalendarPopUpSearchPanel.setComponentName("Search Criteria");
		deductionCalendarPopUpSearchPanel.setAuthorizationIncluded(true);
		deductionCalendarPopUpSearchPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(deductionCalendarPopUpSearchPanel);
		addFieldLayout(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig deductionCalendarPopUpResultPanel = configProvider.getPanelConfig("resultPanel",
				false, null);
		deductionCalendarPopUpResultPanel.setComponentName("Results");
		deductionCalendarPopUpResultPanel.setAuthorizationIncluded(true);
		deductionCalendarPopUpResultPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(deductionCalendarPopUpResultPanel);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig deductionCalendarPopUpResultLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.RESULT_LAYOUT, true, "resultPanel");
		deductionCalendarPopUpResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(deductionCalendarPopUpResultLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig deductionCalendarPopUpFieldLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		deductionCalendarPopUpFieldLayout.setComponentStyle(styleList);
		componentList.add(deductionCalendarPopUpFieldLayout);
		addFieldComponent(componentList);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig deductionCalendarPopUpBtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, false, null);
		deductionCalendarPopUpBtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(deductionCalendarPopUpBtnLayout);
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
		GtnUIFrameworkComponentConfig dcPopUpDeductionCalendarNoLayout = configProvider.getHorizontalLayoutConfig(
				"deductionCalendarNoLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(dcPopUpDeductionCalendarNoLayout);

		GtnUIFrameworkComponentConfig dcPopUpDeductionCalendarNo = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO, true, "deductionCalendarNoLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		dcPopUpDeductionCalendarNo.setComponentName("Deduction Calendar No");
		componentList.add(dcPopUpDeductionCalendarNo);
	}

	private void addDeductionCalendarName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig dcPopUpDeductionCalendarNameLayout = configProvider.getHorizontalLayoutConfig(
				"deductionCalendarNameLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(dcPopUpDeductionCalendarNameLayout);

		GtnUIFrameworkComponentConfig dcPopUpDeductionCalendarName = configProvider.getUIFrameworkComponentConfig(
				"deductionCalendarName", true, "deductionCalendarNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		dcPopUpDeductionCalendarName.setAuthorizationIncluded(true);
		dcPopUpDeductionCalendarName.setComponentName("Deduction Calendar Name");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		dcPopUpDeductionCalendarName.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(dcPopUpDeductionCalendarName);
	}

	private void addCustomerGroupDesc(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig dcPopUpCustomerGroupDescLayout = configProvider.getHorizontalLayoutConfig(
				"customerGroupDescLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(dcPopUpCustomerGroupDescLayout);

		GtnUIFrameworkComponentConfig dcPopUpCustomerGroupDesc = configProvider.getUIFrameworkComponentConfig(
				"customerGroupDesc", true, "customerGroupDescLayout", GtnUIFrameworkComponentType.TEXTBOX);
		dcPopUpCustomerGroupDesc.setAuthorizationIncluded(true);
		dcPopUpCustomerGroupDesc.setComponentName("Customer Group Desc");
		componentList.add(dcPopUpCustomerGroupDesc);
	}

	private void addCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig dcPopUpCategoryLayout = configProvider.getHorizontalLayoutConfig("categoryLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(dcPopUpCategoryLayout);

		GtnUIFrameworkComponentConfig dcPopUpCategory = configProvider.getUIFrameworkComponentConfig("category", true,
				"categoryLayout", GtnUIFrameworkComponentType.COMBOBOX);
		dcPopUpCategory.setAuthorizationIncluded(true);
		dcPopUpCategory.setComponentName("Category");
		componentList.add(dcPopUpCategory);

		GtnUIFrameworkComboBoxConfig dcPopUpCategoryConfig = configProvider.getComboBoxConfig("COMPANY_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		dcPopUpCategory.setGtnComboboxConfig(dcPopUpCategoryConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig dcPopUpSearchButton = configProvider.getUIFrameworkComponentConfig("dc01", true,
				GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		dcPopUpSearchButton.setComponentName("Search");
		dcPopUpSearchButton.setAuthorizationIncluded(true);
		componentList.add(dcPopUpSearchButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");
		alertActionConfig.setActionParameterList(alertParams);
		onFailure.add(alertActionConfig);
		validationActionConfig.addActionParameter(onFailure);
		actionConfigList.add(validationActionConfig);
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.DC_POP_UP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList("dcId"));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		notificationActionConfig.setActionParameterList(notificationParams);
		actionConfigList.add(notificationActionConfig);
		dcPopUpSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig dcPopUpResetButton = configProvider.getUIFrameworkComponentConfig("dcReset01",
				true, GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		dcPopUpResetButton.setComponentName("Reset");
		dcPopUpResetButton.setAuthorizationIncluded(true);
		componentList.add(dcPopUpResetButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
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
		dcPopUpResetButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig dcPopUpResultTable = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.DC_POP_UP_SEARCH_RESULT_TABLE, true,
				GtnFrameworkCommonConstants.RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		dcPopUpResultTable.setComponentName("Results");
		dcPopUpResultTable.setAuthorizationIncluded(true);
		dcPopUpResultTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig dcPopUpResultTableValidationConfig = new GtnUIFrameworkValidationConfig();
		dcPopUpResultTableValidationConfig.setMinSize(1);
		dcPopUpResultTable.setGtnUIFrameworkValidationConfig(dcPopUpResultTableValidationConfig);
		componentList.add(dcPopUpResultTable);

		GtnUIFrameworkPagedTableConfig dcPopUpResultTableConfig = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"DeductionCalendar", "deductionCalendarSearch");
		dcPopUpResultTableConfig.setEditable(false);
		dcPopUpResultTableConfig.setItemPerPage(10);
		dcPopUpResultTableConfig.setPageLength(10);
		dcPopUpResultTableConfig.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class,
				GtnFrameworkCommonConstants.JAVALANG_STRING, String.class, String.class, String.class, String.class });
		dcPopUpResultTableConfig.setTableVisibleHeader(
				new String[] { "Deduction Calendar No", "Deduction Calendar Name", "Deduction Calendar Desc",
						"Category", "Creation Date", "Created By", "Modified Date", "Modified By" });
		dcPopUpResultTableConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO, "deductionCalendarName",
				"deductionCalendarDesc", "category", "creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		dcPopUpResultTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.DEDUCTION_CALENDAR);

		dcPopUpResultTable.setGtnPagedTableConfig(dcPopUpResultTableConfig);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig dcPopUpActionBtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT, false, null);
		dcPopUpActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(dcPopUpActionBtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig dcPopUpSelectBtnLayout = configProvider.getUIFrameworkComponentConfig(
				"dcPopUpViewSelectButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		dcPopUpSelectBtnLayout.setAuthorizationIncluded(true);
		dcPopUpSelectBtnLayout.setComponentName("SELECT");
		componentList.add(dcPopUpSelectBtnLayout);

		List<GtnUIFrameWorkActionConfig> dcPopUpSelectBtnActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig dcPopUpSelectAction = new GtnUIFrameWorkActionConfig();
		dcPopUpSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.DC_POP_UP_SEARCH_RESULT_TABLE);
		actionParameter.add("dcCalculationsSecondaryRebatePlan");
		actionParameter.add(Arrays.asList("secondaryRebatePlanName"));
		actionParameter.add(Arrays.asList("dcCalculationsSecondaryRebatePlan"));

		dcPopUpSelectAction.setActionParameterList(actionParameter);
		dcPopUpSelectBtnActionConfigList.add(dcPopUpSelectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW);
		dcPopUpSelectBtnActionConfigList.add(closeAction);

		dcPopUpSelectBtnLayout.setGtnUIFrameWorkActionConfigList(dcPopUpSelectBtnActionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig dcPopUpCloseBtnLayout = configProvider.getUIFrameworkComponentConfig(
				"dcPopUpViewEditButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		dcPopUpCloseBtnLayout.setAuthorizationIncluded(true);
		dcPopUpCloseBtnLayout.setComponentName("CLOSE");
		componentList.add(dcPopUpCloseBtnLayout);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW);
		actionConfigList.add(closeAction);

		dcPopUpCloseBtnLayout.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
