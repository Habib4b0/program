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
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfCommonConstants;

public class GtnFrameworkNSSearchConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig nsView = configProvider.getViewConfig(
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW, false);
		addComponentList(nsView);
		return nsView;
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

		GtnUIFrameworkComponentConfig netSalesMainPanel = configProvider.getPanelConfig("searchCriteriaPanel", false,
				null);
		netSalesMainPanel.setComponentName("Search Criteria");
		netSalesMainPanel.setAuthorizationIncluded(true);
		componentList.add(netSalesMainPanel);
		addFieldLayout(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesResultPanel = configProvider.getPanelConfig("resultPanel", false, null);
		netSalesResultPanel.setComponentName("Results");
		netSalesResultPanel.setAuthorizationIncluded(true);
		componentList.add(netSalesResultPanel);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesResultLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_RESULT_LAYOUT, true, "resultPanel");
		netSalesResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(netSalesResultLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesFieldLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		netSalesFieldLayout.setComponentStyle(styleList);
		componentList.add(netSalesFieldLayout);
		addFieldComponent(componentList);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.NET_SALES_FORMULA_SEARCH_BUTTON_LAYOUT, false, null);
		componentList.add(netSalesButtonLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addFormulaType(componentList);
		addFormulaId(componentList);
		addFormulaNo(componentList);
		addFormulaName(componentList);

	}

	private void addFormulaType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesFormulaTypeLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(netSalesFormulaTypeLayout);

		GtnUIFrameworkComponentConfig netSalesFormulaType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE, true,
				GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		netSalesFormulaType.setAuthorizationIncluded(true);
		netSalesFormulaType.setComponentName("Net Sales Formula Type");
		componentList.add(netSalesFormulaType);

		GtnUIFrameworkComboBoxConfig netSalesFormulaTypeConfig = configProvider.getComboBoxConfig("NS_FORMULA_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig netSalesFormulaTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		netSalesFormulaTypeValidationConfig.setConditionList(conditions);
		netSalesFormulaType.setGtnUIFrameworkValidationConfig(netSalesFormulaTypeValidationConfig);
		netSalesFormulaType.setGtnComboboxConfig(netSalesFormulaTypeConfig);
	}

	private void addFormulaId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesFormulaIdLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(netSalesFormulaIdLayout);

		GtnUIFrameworkComponentConfig netSalesFormulaId = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_ID, true, GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		netSalesFormulaId.setAuthorizationIncluded(true);
		netSalesFormulaId.setComponentName("Net Sales Formula ID");

		GtnUIFrameworkValidationConfig netSalesFormulaIdValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		netSalesFormulaIdValidationConfig.setConditionList(conditions);
		netSalesFormulaId.setGtnUIFrameworkValidationConfig(netSalesFormulaIdValidationConfig);
		componentList.add(netSalesFormulaId);
	}

	private void addFormulaNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesFormulaNoLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(netSalesFormulaNoLayout);

		GtnUIFrameworkComponentConfig netSalesFormulaNo = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_NO, true, GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		netSalesFormulaNo.setAuthorizationIncluded(true);
		netSalesFormulaNo.setComponentName("Net Sales Formula No");

		GtnUIFrameworkValidationConfig netSalesFormulaNoValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		netSalesFormulaNoValidationConfig.setConditionList(conditions);
		netSalesFormulaNo.setGtnUIFrameworkValidationConfig(netSalesFormulaNoValidationConfig);

		componentList.add(netSalesFormulaNo);
	}

	private void addFormulaName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig netSalesFormulaNameLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(netSalesFormulaNameLayout);

		GtnUIFrameworkComponentConfig netSalesFormulaName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_NAME, true, GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		netSalesFormulaName.setAuthorizationIncluded(true);
		netSalesFormulaName.setComponentName("Net Sales Formula Name");

		GtnUIFrameworkValidationConfig netSalesFormulaNameValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		netSalesFormulaNameValidationConfig.setConditionList(conditions);
		netSalesFormulaName.setGtnUIFrameworkValidationConfig(netSalesFormulaNameValidationConfig);

		componentList.add(netSalesFormulaName);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesSearchBtnConfig = configProvider.getUIFrameworkComponentConfig(
				"netSales01", true, GtnFrameworkCommonConstants.NET_SALES_FORMULA_SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		netSalesSearchBtnConfig.setAuthorizationIncluded(true);
		netSalesSearchBtnConfig.setComponentName("Search");
		componentList.add(netSalesSearchBtnConfig);

		List<GtnUIFrameWorkActionConfig> netSalesSearchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE, GtnFrameworkCommonConstants.FORMULA_NO,
						GtnFrameworkCommonConstants.FORMULA_NAME, GtnFrameworkCommonConstants.FORMULA_ID));
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
		netSalesSearchActionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE, GtnFrameworkCommonConstants.FORMULA_NO,
						GtnFrameworkCommonConstants.FORMULA_NAME, GtnFrameworkCommonConstants.FORMULA_ID));

		netSalesSearchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		notificationActionConfig.setActionParameterList(notificationParams);
		netSalesSearchActionConfigList.add(notificationActionConfig);
		netSalesSearchBtnConfig.setGtnUIFrameWorkActionConfigList(netSalesSearchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig netSalesResetBtn = configProvider.getUIFrameworkComponentConfig(
				"complianceAndDeductionFormulasReset01", true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_SEARCH_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		netSalesResetBtn.setComponentName("Reset");
		netSalesResetBtn.setAuthorizationIncluded(true);
		componentList.add(netSalesResetBtn);

		List<GtnUIFrameWorkActionConfig> netSalesResetBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_ID, null);
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		netSalesResetBtnActionConfigList.add(resetActionConfig);
		netSalesResetBtn.setGtnUIFrameWorkActionConfigList(netSalesResetBtnActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig netSalesResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE, true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		netSalesResultConfig.setAuthorizationIncluded(true);
		netSalesResultConfig.setComponentName("Results");
		netSalesResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig netSalesResultValidationConfig = new GtnUIFrameworkValidationConfig();
		netSalesResultValidationConfig.setMinSize(1);
		netSalesResultConfig.setGtnUIFrameworkValidationConfig(netSalesResultValidationConfig);

		componentList.add(netSalesResultConfig);

		GtnUIFrameworkPagedTableConfig netSalesResultTable = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"netSalesRuleSearch", GtnWsNsfCommonConstants.GTN_NSF_LANDING_SEARCH_QUERY_NAME);
		netSalesResultTable.setEditable(false);
		netSalesResultTable.setItemPerPage(10);
		netSalesResultTable.setPageLength(10);
		netSalesResultTable.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class,
				String.class, GtnFrameworkCommonConstants.JAVAUTIL_DATE, String.class,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, String.class });
		netSalesResultTable.setTableVisibleHeader(
				new String[] { "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula No",
						"Net Sales Formula Name", "Creation Date", "Created By", "Modified Date", "Modified By" });
		netSalesResultTable.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE,
				GtnFrameworkCommonConstants.FORMULA_ID, GtnFrameworkCommonConstants.FORMULA_NO,
				GtnFrameworkCommonConstants.FORMULA_NAME, "creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		netSalesResultTable.setExtraColumn(new Object[] { "systemId" });
		netSalesResultTable.setExtraColumnDataType(new Class<?>[] { String.class });
		netSalesResultTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.NET_SALES);
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("formulaTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig formulaTypeConfig = configProvider.getComboBoxConfig("NS_FORMULA_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		customFilterComponentConfig.setGtnComboboxConfig(formulaTypeConfig);

		customFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig createedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		createedByCustomFilterConfig.setPropertId("createdBy");
		createedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig createdByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		createdByCustomFilterComponentConfig.setComponentId("createdBycustomFilterComboBox");
		createdByCustomFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig createdByConfig = configProvider.getComboBoxConfig("userIdName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
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
		GtnUIFrameworkComboBoxConfig modifiedByConfig = configProvider.getComboBoxConfig("userIdName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		modifiedByCustomFilterComponentConfig.setGtnComboboxConfig(modifiedByConfig);
		modifiedByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		modifiedByCustomFilterConfig.setGtnComponentConfig(modifiedByCustomFilterComponentConfig);

		customFilterConfigMap.put(modifiedByCustomFilterConfig.getPropertId(), modifiedByCustomFilterConfig);

		customFilterConfigMap.put(createedByCustomFilterConfig.getPropertId(), createedByCustomFilterConfig);

		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		netSalesResultTable.setCustomFilterConfigMap(customFilterConfigMap);
		netSalesResultConfig.setGtnPagedTableConfig(netSalesResultTable);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesActionBtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT, false, null);
		netSalesActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(netSalesActionBtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesSelectBtnConfig = configProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpViewAddButton", true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		netSalesSelectBtnConfig.setComponentName("SELECT");
		netSalesSelectBtnConfig.setAuthorizationIncluded(true);
		componentList.add(netSalesSelectBtnConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.FIELDFACTORY_POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		actionParameter.add(GtnFrameworkCommonConstants.FORMULA_NAME);
		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW));
		actionConfigList.add(closeAction);

		netSalesSelectBtnConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesCloseBtn = configProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpViewEditButton", true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		netSalesCloseBtn.setComponentName("CLOSE");
		netSalesCloseBtn.setAuthorizationIncluded(true);
		componentList.add(netSalesCloseBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.NET_SALES_FORMULA_POP_UP_VIEW);
		actionConfigList.add(closeAction);

		netSalesCloseBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
