
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class GtnFrameworkDeductionCalendarPopUpConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW,
				GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW, false);
		addDcComponentList(view);
		return view;
	}

	private void addDcComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> dcComponentList = new ArrayList<>();
		view.setGtnComponentList(dcComponentList);
		addDcSearchCriteriaPanel(dcComponentList);
		addDcButtonLayout(dcComponentList);
		addDcResultPanel(dcComponentList);
		addDcActionButtonLayout(dcComponentList);

	}

	private void addDcSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = configProvider.getPanelConfig("searchCriteriaPanel", false,
				null);
		searchCriteriaPanel.setComponentName("Search Criteria");
		searchCriteriaPanel.setAuthorizationIncluded(true);
		componentList.add(searchCriteriaPanel);
		addFieldLayout(componentList);
	}

	private void addDcResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultsPanelConfig = configProvider.getPanelConfig("resultPanel", false, null);
		resultsPanelConfig.setComponentName("Results");
		resultsPanelConfig.setAuthorizationIncluded(true);
		componentList.add(resultsPanelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.RESULT_LAYOUT, true, "resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout.setComponentStyle(styleList);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addDcButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, false, null);
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
		GtnUIFrameworkComponentConfig deductionCalendarNoLayout = configProvider.getHorizontalLayoutConfig(
				"deductionCalendarNoLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(deductionCalendarNoLayout);

		GtnUIFrameworkComponentConfig deductionConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO, true, "deductionCalendarNoLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		deductionConfig.setAuthorizationIncluded(true);
		deductionConfig.setComponentName("Deduction Calendar No");

		componentList.add(deductionConfig);
	}

	private void addDeductionCalendarName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"deductionCalendarNameLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig deductionCalendarNameConfig = configProvider.getUIFrameworkComponentConfig(
				"deductionCalendarName", true, "deductionCalendarNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		deductionCalendarNameConfig.setAuthorizationIncluded(true);
		deductionCalendarNameConfig.setComponentName("Deduction Calendar Name");
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		deductionCalendarNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(deductionCalendarNameConfig);
	}

	private void addCustomerGroupDesc(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("customerGroupDescLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig customerGroupDescConfig = configProvider.getUIFrameworkComponentConfig(
				"customerGroupDesc", true, "customerGroupDescLayout", GtnUIFrameworkComponentType.TEXTBOX);
		customerGroupDescConfig.setAuthorizationIncluded(true);
		customerGroupDescConfig.setComponentName("Customer Group Desc");

		componentList.add(customerGroupDescConfig);
	}

	private void addCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("categoryLayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig categoryConfig = configProvider.getUIFrameworkComponentConfig("category", true,
				"categoryLayout", GtnUIFrameworkComponentType.COMBOBOX);
		categoryConfig.setAuthorizationIncluded(true);
		categoryConfig.setComponentName("Category");

		componentList.add(categoryConfig);

		GtnUIFrameworkComboBoxConfig companyCategoryConfig = configProvider.getComboBoxConfig("COMPANY_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		categoryConfig.setGtnComboboxConfig(companyCategoryConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("dc01", true,
				GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> dcPopUpSearchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig dcPopUpSearchValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		dcPopUpSearchValidationActionConfig.setFieldValues(
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO }));

		dcPopUpSearchValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig dcPopUpSearchAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		dcPopUpSearchAlertActionConfig.setActionParameterList(alertParams);

		onFailure.add(dcPopUpSearchAlertActionConfig);
		dcPopUpSearchValidationActionConfig.addActionParameter(onFailure);
		dcPopUpSearchActionConfigList.add(dcPopUpSearchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.DC_POP_UP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] { "dcId" }));

		dcPopUpSearchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		notificationActionConfig.setActionParameterList(notificationParams);
		dcPopUpSearchActionConfigList.add(notificationActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(dcPopUpSearchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("dcReset01",
				true, GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Reset");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> dcPopUpResetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dcPopUpResetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		dcPopUpResetActionConfig.addActionParameter("Reset Confirmation");
		dcPopUpResetActionConfig
				.addActionParameter("Are you sure you want to reset the values in the Search Criteria group box?");
		dcPopUpResetActionConfig
				.addActionParameter(Arrays.asList(new String[] { "ruleName", "ruleNo", "ruleType", "ruleCategory" }));
		dcPopUpResetActionConfig.addActionParameter(Arrays.asList("", "", null, null));
		dcPopUpResetActionConfigList.add(dcPopUpResetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(dcPopUpResetActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig dcPopUpResetSearchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.DC_POP_UP_SEARCH_RESULT_TABLE, true,
				GtnFrameworkCommonConstants.RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		dcPopUpResetSearchResultConfig.setAuthorizationIncluded(true);

		dcPopUpResetSearchResultConfig.setComponentName("Results");
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		dcPopUpResetSearchResultConfig.setComponentStyle(tableStyle);
		dcPopUpResetSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		dcPopUpResetSearchResultConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(dcPopUpResetSearchResultConfig);

		GtnUIFrameworkPagedTableConfig dcPopUpResetSearchResults = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"DeductionCalendar", "deductionCalendarSearch");
		dcPopUpResetSearchResults.setEditable(false);
		dcPopUpResetSearchResults.setItemPerPage(10);
		dcPopUpResetSearchResults.setPageLength(10);
		dcPopUpResetSearchResults.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class });
		dcPopUpResetSearchResults.setTableVisibleHeader(
				new String[] { "Deduction Calendar No", "Deduction Calendar Name", "Deduction Calendar Desc",
						"Category", "Creation Date", "Created By", "Modified Date", "Modified By" });
		dcPopUpResetSearchResults.setTableColumnMappingId(new Object[] {
				GtnFrameworkCommonConstants.PROPERTY_DEDUCTION_CALENDAR_NO, "deductionCalendarName",
				"deductionCalendarDesc", "category", "creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		dcPopUpResetSearchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.DEDUCTION_CALENDAR);
		dcPopUpResetSearchResultConfig.setGtnPagedTableConfig(dcPopUpResetSearchResults);
	}

	private void addDcActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT, false, null);
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRAddButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"dcPopUpViewSelectButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setAuthorizationIncluded(true);
		cDRAddButtonConfig.setComponentName("SELECT");
		componentList.add(cDRAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.DC_POP_UP_SEARCH_RESULT_TABLE);
		actionParameter.add("dcCalculationsSecondaryRebatePlan");
		actionParameter.add(Arrays.asList("secondaryRebatePlanName"));
		actionParameter.add(Arrays.asList("dcCalculationsSecondaryRebatePlan"));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDRAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDREditButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"dcPopUpViewEditButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cDREditButtonConfig.setAuthorizationIncluded(true);
		cDREditButtonConfig.setComponentName("CLOSE");
		componentList.add(cDREditButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.DEDUCTION_CALENDAR_POP_UP_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDREditButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
