package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfCommonConstants;

public class GtnFrameworkNSSearchConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW, false);
		addNsComponentList(view);
		return view;
	}

	private void addNsComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> nsComponentList = new ArrayList<>();
		view.setGtnComponentList(nsComponentList);
		addNsSearchCriteriaPanel(nsComponentList);
		addNsButtonLayout(nsComponentList);
		addNsResultPanel(nsComponentList);
		addNsActionButtonLayout(nsComponentList);

	}

	private void addNsSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = configProvider.getPanelConfig("searchCriteriaPanel", false,
				null);
		searchCriteriaPanel.setComponentName("Search Criteria");
		searchCriteriaPanel.setAuthorizationIncluded(true);
		componentList.add(searchCriteriaPanel);
		addNsFieldLayout(componentList);
	}

	private void addNsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultPanelConfig = configProvider.getPanelConfig("resultPanel", false, null);
		resultPanelConfig.setComponentName("Results");
		resultPanelConfig.setAuthorizationIncluded(true);
		resultPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(resultPanelConfig);
		addNsResultLayout(componentList);
	}

	private void addNsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_RESULT_LAYOUT, true, "resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addNsPagedTableComponent(componentList);
	}

	private void addNsFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchCriteriaPanelLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		searchCriteriaPanelLayout.setComponentStyle(styleList);
		componentList.add(searchCriteriaPanelLayout);
		addNsFieldComponent(componentList);
	}

	private void addNsButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.NET_SALES_FORMULA_SEARCH_BUTTON_LAYOUT, false, null);
		componentList.add(gtnLayout);
		addNsResetButtonComponent(componentList);
		addNsSearchButtonComponent(componentList);
	}

	private void addNsFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addFormulaTypeDdlb(componentList);
		addFormulaIdTextField(componentList);
		addFormulaNoTextField(componentList);
		addFormulaNameTextField(componentList);

	}

	private void addFormulaTypeDdlb(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyTypeDdlbConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_TYPE, true, GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyTypeDdlbConfig.setAuthorizationIncluded(true);
		companyTypeDdlbConfig.setComponentName("Net Sales Formula Type");

		componentList.add(companyTypeDdlbConfig);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("NS_FORMULA_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyTypeDdlbConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		companyTypeDdlbConfig.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addFormulaIdTextField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig formulaIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_ID, true, GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaIdConfig.setAuthorizationIncluded(true);
		formulaIdConfig.setComponentName("Net Sales Formula ID");
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		formulaIdConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(formulaIdConfig);
	}

	private void addFormulaNoTextField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig formulaNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_NO, true, GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaNoConfig.setAuthorizationIncluded(true);
		formulaNoConfig.setComponentName("Net Sales Formula NO");
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		formulaNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(formulaNoConfig);
	}

	private void addFormulaNameTextField(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig formulaNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_NAME, true, GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaNameConfig.setAuthorizationIncluded(true);
		formulaNameConfig.setComponentName("Net Sales Formula Name");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		formulaNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(formulaNameConfig);
	}

	private void addNsSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("netSales01",
				true, GtnFrameworkCommonConstants.NET_SALES_FORMULA_SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> rsSearchButtonActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig rsSearchBtnValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		rsSearchBtnValidationActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.FORMULA_TYPE, GtnFrameworkCommonConstants.FORMULA_NO,
						GtnFrameworkCommonConstants.FORMULA_NAME, GtnFrameworkCommonConstants.FORMULA_ID));
		rsSearchBtnValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> rsSearchButtonOnFailureActionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig rsSearchButtonAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		rsSearchButtonAlertActionConfig.setActionParameterList(alertParams);

		rsSearchButtonOnFailureActionConfig.add(rsSearchButtonAlertActionConfig);

		rsSearchBtnValidationActionConfig.addActionParameter(rsSearchButtonOnFailureActionConfig);
		rsSearchButtonActionConfigList.add(rsSearchBtnValidationActionConfig);

		GtnUIFrameWorkActionConfig rsLoadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		rsLoadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		rsLoadDataTableActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.FORMULA_TYPE, GtnFrameworkCommonConstants.FORMULA_NO,
						GtnFrameworkCommonConstants.FORMULA_NAME, GtnFrameworkCommonConstants.FORMULA_ID));

		rsSearchButtonActionConfigList.add(rsLoadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		notificationActionConfig.setActionParameterList(notificationParams);
		rsSearchButtonActionConfigList.add(notificationActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(rsSearchButtonActionConfigList);

	}

	private void addNsResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"complianceAndDeductionFormulasReset01", true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_SEARCH_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Reset");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_ID, null);
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addNsPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsPopupPagedTableConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE, true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		nsPopupPagedTableConfig.setAuthorizationIncluded(true);

		nsPopupPagedTableConfig.setComponentName("Results");
		List<String> nsPopupPagedTableStyle = new ArrayList<>();
		nsPopupPagedTableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		nsPopupPagedTableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		nsPopupPagedTableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		nsPopupPagedTableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		nsPopupPagedTableConfig.setComponentStyle(nsPopupPagedTableStyle);
		nsPopupPagedTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		nsPopupPagedTableConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(nsPopupPagedTableConfig);

		GtnUIFrameworkPagedTableConfig nsSearchPopupResultsTableConfig = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"netSalesRuleSearch", GtnWsNsfCommonConstants.GTN_NSF_LANDING_SEARCH_QUERY_NAME);
		nsSearchPopupResultsTableConfig.setEditable(false);
		nsSearchPopupResultsTableConfig.setItemPerPage(10);
		nsSearchPopupResultsTableConfig.setPageLength(10);
		nsSearchPopupResultsTableConfig.setSinkItemPerPageWithPageLength(false);
		nsSearchPopupResultsTableConfig.setTableColumnDataType(new Class[] { String.class, String.class, String.class,
				String.class, Date.class, String.class, Date.class, String.class });
		nsSearchPopupResultsTableConfig.setTableVisibleHeader(
				new String[] { "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula NO",
						"Net Sales Formula Name", "Creation Date", "Created By", "Modified Date", "Modified By" });
		nsSearchPopupResultsTableConfig.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.FORMULA_TYPE,
				GtnFrameworkCommonConstants.FORMULA_ID, GtnFrameworkCommonConstants.FORMULA_NO,
				GtnFrameworkCommonConstants.FORMULA_NAME, "creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		nsSearchPopupResultsTableConfig.setExtraColumn(new Object[] { "systemId" });
		nsSearchPopupResultsTableConfig.setExtraColumnDataType(new Class[] { Integer.class });
		nsSearchPopupResultsTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.NET_SALES);
		nsSearchPopupResultsTableConfig.setCustomFilterConfigMap(getNsPopupCustomFilterConfig());
		nsPopupPagedTableConfig.setGtnPagedTableConfig(nsSearchPopupResultsTableConfig);
	}

	private void addNsActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addNsSelectButtonComponent(componentList);
		addNsCloseButtonComponent(componentList);
		addNsExportButtonComponent(componentList);
	}

	private void addNsSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig selectButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpViewAddButton", true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		selectButtonConfig.setComponentName("SELECT");
		selectButtonConfig.setAuthorizationIncluded(true);
		componentList.add(selectButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		actionParameter.add("rebatePlanCalculationsNetSalesFormula");
		actionParameter.add(Arrays.asList(GtnFrameworkCommonConstants.FORMULA_NAME));
		actionParameter.add(Arrays.asList("rebatePlanCalculationsNetSalesFormula"));
		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		Object viewName = GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW;
		closeAction.setActionParameterList(Arrays.asList(viewName));
		actionConfigList.add(closeAction);

		selectButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addNsCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig closeButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpViewEditButton", true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAuthorizationIncluded(true);
		componentList.add(closeButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		Object viewName = GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW;
		closeAction.setActionParameterList(Arrays.asList(viewName));
		actionConfigList.add(closeAction);

		closeButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addNsExportButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig exportButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpExportButton", true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		exportButtonConfig.setComponentName("EXPORT");
		exportButtonConfig.setAuthorizationIncluded(true);
		componentList.add(exportButtonConfig);

		GtnUIFrameworkExcelButtonConfig excelButtonConfig = configProvider.getExcelBtnconfig("Rebate Setup", true,
				GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE, false);
		exportButtonConfig.setGtnUIFrameworkExcelButtonConfig(excelButtonConfig);
		GtnUIFrameWorkActionConfig excelExportAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelExportAction.addActionParameter(excelButtonConfig);
		exportButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelExportAction));
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getNsPopupCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> sPopupCustomFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkCommonConstants.FORMULA_TYPE, "createdBy", "modifiedBy" };
		String[] listNameArray = { "NS_FORMULA_TYPE", "userIdName", "userIdName" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			customFilterConfig.setPropertId(propertyIds[i]);
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig nsPopupCustomFilterConfig = new GtnUIFrameworkComponentConfig();
			nsPopupCustomFilterConfig.setComponentId("customFilterComboBox");
			nsPopupCustomFilterConfig.setComponentName("customFilterComboBox");
			nsPopupCustomFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			nsPopupCustomFilterConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			nsPopupCustomFilterConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			nsPopupCustomFilterConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customFilterConfig.setGtnComponentConfig(nsPopupCustomFilterConfig);
			sPopupCustomFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);

		}
		return sPopupCustomFilterConfigMap;
	}

}
