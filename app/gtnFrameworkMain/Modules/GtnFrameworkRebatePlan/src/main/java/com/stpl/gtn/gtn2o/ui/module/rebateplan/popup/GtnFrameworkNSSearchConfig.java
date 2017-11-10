package com.stpl.gtn.gtn2o.ui.module.rebateplan.popup;

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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfCommonConstants;

public class GtnFrameworkNSSearchConfig {

	private final GtnFrameworkComponentConfigProvider nsfPopUpConfigProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {

		GtnUIFrameworkViewConfig view = nsfPopUpConfigProvider.getViewConfig(
				GtnFrameworkStringConstants.NET_SALES_FORMULA_POPUP_VIEW,
				GtnFrameworkStringConstants.NET_SALES_FORMULA_POPUP_VIEW, false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addNSPopUpSearchCriteriaPanel(componentList);
		addNSPopUpButtonLayout(componentList);
		addNSPopUpResultPanel(componentList);
		addNSPopUpActionButtonLayout(componentList);
	}

	private void addNSPopUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfSearchCriteriaPanelConfig = nsfPopUpConfigProvider
				.getPanelConfig("searchCriteriaPanel", false, null);
		nsfSearchCriteriaPanelConfig.setComponentName("Search Criteria");
		nsfSearchCriteriaPanelConfig.setAuthorizationIncluded(true);
		componentList.add(nsfSearchCriteriaPanelConfig);
		addFieldLayout(componentList);
	}

	private void addNSPopUpResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig nsfResultPanelConfig = nsfPopUpConfigProvider.getPanelConfig("resultPanel", false,
				null);
		nsfResultPanelConfig.setAuthorizationIncluded(true);
		componentList.add(nsfResultPanelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfPopupResultTableLayout = nsfPopUpConfigProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.NET_SALES_FORMULA_RESULT_LAYOUT, true, "resultPanel");
		nsfPopupResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(nsfPopupResultTableLayout);

		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfPopupsearchCriteriaLayoutConfig = nsfPopUpConfigProvider.getGtnCssLayoutConfig(
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel",
				GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		nsfPopupsearchCriteriaLayoutConfig.setComponentStyle(styleList);
		componentList.add(nsfPopupsearchCriteriaLayoutConfig);

		addFieldComponent(componentList);
	}

	private void addNSPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfPopupSearchCriteriaButtonLayout = nsfPopUpConfigProvider
				.getCssLayoutConfig(GtnFrameworkStringConstants.NET_SALES_FORMULA_SEARCH_BUTTON_LAYOUT, false, null);
		nsfPopupSearchCriteriaButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		nsfPopupSearchCriteriaButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		nsfPopupSearchCriteriaButtonLayout.setSpacing(true);
		nsfPopupSearchCriteriaButtonLayout.setMargin(true);
		componentList.add(nsfPopupSearchCriteriaButtonLayout);

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

		GtnUIFrameworkComponentConfig formulaTypelayout = nsfPopUpConfigProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.FORMULA_TYPE_LAYOUT, true,
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaTypelayout);

		GtnUIFrameworkComponentConfig formulaTypeConfig = nsfPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.FORMULA_TYPE, true, formulaTypelayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		formulaTypeConfig.setAuthorizationIncluded(true);
		formulaTypeConfig.setComponentName("Net Sales Formula Type");
		componentList.add(formulaTypeConfig);

		GtnUIFrameworkComboBoxConfig formulaTypeComboConfig = nsfPopUpConfigProvider
				.getComboBoxConfig("NS_FORMULA_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		formulaTypeConfig.setGtnComboboxConfig(formulaTypeComboConfig);

		GtnUIFrameworkValidationConfig formulaTypeValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> formulaTypeConditions = new ArrayList<>();
		formulaTypeConditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		formulaTypeValidationConfig.setConditionList(formulaTypeConditions);
		formulaTypeConfig.setGtnUIFrameworkValidationConfig(formulaTypeValidationConfig);

	}

	private void addFormulaId(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaIdLayout = nsfPopUpConfigProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.FORMULA_ID_LAYOUT, true,
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaIdLayout);

		GtnUIFrameworkComponentConfig formulaIdLayoutConfig = nsfPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.FORMULA_ID, true, formulaIdLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaIdLayoutConfig.setAuthorizationIncluded(true);
		formulaIdLayoutConfig.setComponentName("Net Sales Formula ID");
		componentList.add(formulaIdLayoutConfig);

		GtnUIFrameworkValidationConfig formulaIdValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> formulaIdconditions = new ArrayList<>();
		formulaIdconditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		formulaIdValidationConfig.setConditionList(formulaIdconditions);
		formulaIdLayoutConfig.setGtnUIFrameworkValidationConfig(formulaIdValidationConfig);

	}

	private void addFormulaNo(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaNoLayout = nsfPopUpConfigProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.FORMULA_NO_LAYOUT, true,
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaNoLayout);

		GtnUIFrameworkComponentConfig formulaNoLayoutConfig = nsfPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.FORMULA_NO, true, formulaNoLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaNoLayoutConfig.setAuthorizationIncluded(true);
		formulaNoLayoutConfig.setComponentName("Net Sales Formula No");
		componentList.add(formulaNoLayoutConfig);

		GtnUIFrameworkValidationConfig formulaNoValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> formulaNoconditions = new ArrayList<>();
		formulaNoconditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		formulaNoValidationConfig.setConditionList(formulaNoconditions);
		formulaNoLayoutConfig.setGtnUIFrameworkValidationConfig(formulaNoValidationConfig);

	}

	private void addFormulaName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaNameLayout = nsfPopUpConfigProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.FORMULA_NAME_LAYOUT, true,
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaNameLayout);

		GtnUIFrameworkComponentConfig formulaNameLayoutConfig = nsfPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.FORMULA_NAME, true, formulaNameLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaNameLayoutConfig.setAuthorizationIncluded(true);
		formulaNameLayoutConfig.setComponentName("Net Sales Formula Name");
		componentList.add(formulaNameLayoutConfig);

		GtnUIFrameworkValidationConfig formulaNameValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> formulaNameconditions = new ArrayList<>();
		formulaNameconditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		formulaNameValidationConfig.setConditionList(formulaNameconditions);
		formulaNameLayoutConfig.setGtnUIFrameworkValidationConfig(formulaNameValidationConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfPopupSearchBtnLayout = nsfPopUpConfigProvider.getHorizontalLayoutConfig(
				"netSales01Layout", true, GtnFrameworkStringConstants.NET_SALES_FORMULA_SEARCH_BUTTON_LAYOUT);
		componentList.add(nsfPopupSearchBtnLayout);

		GtnUIFrameworkComponentConfig nsfPopupSearchButtonConfig = nsfPopUpConfigProvider.getUIFrameworkComponentConfig(
				"netSales01", true, nsfPopupSearchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		nsfPopupSearchButtonConfig.setComponentName("SEARCH");
		nsfPopupSearchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(nsfPopupSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig searchValidationActionConfig = nsfPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		searchValidationActionConfig.setFieldValues(Arrays
				.asList(new String[] { GtnFrameworkStringConstants.FORMULA_TYPE, GtnFrameworkStringConstants.FORMULA_NO,
						GtnFrameworkStringConstants.FORMULA_NAME, GtnFrameworkStringConstants.FORMULA_ID }));
		searchValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = nsfPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParams);

		onFailure.add(alertActionConfig);

		searchValidationActionConfig.addActionParameter(onFailure);
		searchActionConfigList.add(searchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = nsfPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> searchActionParams = new ArrayList<>();
		searchActionParams.add(GtnFrameworkStringConstants.NET_SALES_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(searchActionParams);
		loadDataTableActionConfig.setFieldValues(Arrays
				.asList(new String[] { GtnFrameworkStringConstants.FORMULA_TYPE, GtnFrameworkStringConstants.FORMULA_NO,
						GtnFrameworkStringConstants.FORMULA_NAME, GtnFrameworkStringConstants.FORMULA_ID }));

		searchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig searchNotificationActionConfig = nsfPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		searchNotificationActionConfig.setActionParameterList(notificationParams);
		searchActionConfigList.add(searchNotificationActionConfig);
		nsfPopupSearchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfPopupResetBtnLayout = nsfPopUpConfigProvider.getHorizontalLayoutConfig(
				"complianceAndDeductionFormulasReset01Layout", true,
				GtnFrameworkStringConstants.NET_SALES_FORMULA_SEARCH_BUTTON_LAYOUT);
		componentList.add(nsfPopupResetBtnLayout);

		GtnUIFrameworkComponentConfig nsfPopupResetButtonConfig = nsfPopUpConfigProvider.getUIFrameworkComponentConfig(
				"complianceAndDeductionFormulasReset01", true, nsfPopupResetBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		nsfPopupResetButtonConfig.setAuthorizationIncluded(true);
		nsfPopupResetButtonConfig.setComponentName("RESET");
		componentList.add(nsfPopupResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = nsfPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> resetActionparams = new ArrayList<>();
		resetActionparams.add("Reset Confirmation");
		resetActionparams.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkStringConstants.FORMULA_NAME, "");
		resetMap.put(GtnFrameworkStringConstants.FORMULA_NO, "");
		resetMap.put(GtnFrameworkStringConstants.FORMULA_TYPE, null);
		resetMap.put(GtnFrameworkStringConstants.FORMULA_ID, null);
		resetActionparams.add(resetMap);

		resetActionConfig.setActionParameterList(resetActionparams);
		resetActionConfigList.add(resetActionConfig);
		nsfPopupResetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfSearchResultConfig = nsfPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.NET_SALES_SEARCH_RESULT_TABLE, true,
				GtnFrameworkStringConstants.NET_SALES_FORMULA_RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		nsfSearchResultConfig.setAuthorizationIncluded(true);
		nsfSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(nsfSearchResultConfig);

		GtnUIFrameworkPagedTableConfig nsfSearchResultsTableConfig = new GtnUIFrameworkPagedTableConfig();
		nsfSearchResultsTableConfig.setEditable(false);
		nsfSearchResultsTableConfig.setFilterBar(true);
		nsfSearchResultsTableConfig.setSelectable(true);
		nsfSearchResultsTableConfig.setItemPerPage(10);
		nsfSearchResultsTableConfig.setPageLength(10);
		nsfSearchResultsTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		nsfSearchResultsTableConfig.setTableVisibleHeader(
				new String[] { "Net Sales Formula Type", "Net Sales Formula Id", "Net Sales Formula No",
						"Net Sales Formula Name", "Creation Date", "Created By", "Modified Date", "Modified By" });
		nsfSearchResultsTableConfig.setTableColumnMappingId(new Object[] { GtnFrameworkStringConstants.FORMULA_TYPE,
				GtnFrameworkStringConstants.FORMULA_ID, GtnFrameworkStringConstants.FORMULA_NO,
				GtnFrameworkStringConstants.FORMULA_NAME, "creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		nsfSearchResultsTableConfig.setExtraColumn(new Object[] { "systemId" });
		nsfSearchResultsTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		nsfSearchResultsTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		nsfSearchResultsTableConfig.setModuleName("netSalesRuleSearch");
		nsfSearchResultsTableConfig.setQueryName(GtnWsNsfCommonConstants.GTN_NSF_LANDING_SEARCH_QUERY_NAME);
		nsfSearchResultsTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.NET_SALES);
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkStringConstants.FORMULA_TYPE);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("formulaTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig formulaTypeConfig = nsfPopUpConfigProvider.getComboBoxConfig("NS_FORMULA_TYPE",
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
		createdByCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig createdByConfig = nsfPopUpConfigProvider.getComboBoxConfig("userIdName",
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
		modifiedByCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig modifiedByConfig = nsfPopUpConfigProvider.getComboBoxConfig("userIdName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		modifiedByCustomFilterComponentConfig.setGtnComboboxConfig(modifiedByConfig);
		modifiedByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		modifiedByCustomFilterConfig.setGtnComponentConfig(modifiedByCustomFilterComponentConfig);

		customFilterConfigMap.put(modifiedByCustomFilterConfig.getPropertId(), modifiedByCustomFilterConfig);

		customFilterConfigMap.put(createedByCustomFilterConfig.getPropertId(), createedByCustomFilterConfig);

		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		nsfSearchResultsTableConfig.setCustomFilterConfigMap(customFilterConfigMap);
		nsfSearchResultConfig.setGtnPagedTableConfig(nsfSearchResultsTableConfig);
	}

	private void addNSPopUpActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfPopupActionBtnLayout = nsfPopUpConfigProvider
				.getCssLayoutConfig(GtnFrameworkStringConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT, false, null);
		nsfPopupActionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		nsfPopupActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		nsfPopupActionBtnLayout.setSpacing(true);
		nsfPopupActionBtnLayout.setMargin(true);
		componentList.add(nsfPopupActionBtnLayout);

		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfPopupSelectBtnLayout = nsfPopUpConfigProvider.getHorizontalLayoutConfig(
				"netSalesFormulaPopUpViewAddButtonLayout", true,
				GtnFrameworkStringConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT);
		componentList.add(nsfPopupSelectBtnLayout);

		GtnUIFrameworkComponentConfig nsfPopupSelectButtonConfig = nsfPopUpConfigProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpViewAddButton", true, nsfPopupSelectBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		nsfPopupSelectButtonConfig.setAuthorizationIncluded(true);
		nsfPopupSelectButtonConfig.setComponentName("SELECT");
		componentList.add(nsfPopupSelectButtonConfig);

		List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectActionConfig = new GtnUIFrameWorkActionConfig();
		selectActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkStringConstants.NET_SALES_SEARCH_RESULT_TABLE);
		actionParameter.add("rebatePlanCalculationsNetSalesFormula");
		actionParameter.add(Arrays.asList(new String[] { GtnFrameworkStringConstants.FORMULA_NAME }));
		actionParameter.add(Arrays.asList(new String[] { "rebatePlanCalculationsNetSalesFormula" }));
		selectActionConfig.setActionParameterList(actionParameter);
		selectActionConfigList.add(selectActionConfig);

		GtnUIFrameWorkActionConfig closeActionConfig = new GtnUIFrameWorkActionConfig();
		closeActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);

		closeActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.NET_SALES_FORMULA_POPUP_VIEW }));
		selectActionConfigList.add(closeActionConfig);

		nsfPopupSelectButtonConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig nsfPopupCloseBtnLayout = nsfPopUpConfigProvider.getHorizontalLayoutConfig(
				"netSalesFormulaPopUpViewEditButtonLayout", true,
				GtnFrameworkStringConstants.NET_SALES_FORMULA_ACTION_BUTTON_LAYOUT);
		componentList.add(nsfPopupCloseBtnLayout);

		GtnUIFrameworkComponentConfig nsfPopupCloseButtonConfig = nsfPopUpConfigProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpViewEditButton", true, nsfPopupCloseBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		nsfPopupCloseButtonConfig.setAuthorizationIncluded(true);
		nsfPopupCloseButtonConfig.setComponentName("CLOSE");
		componentList.add(nsfPopupCloseButtonConfig);

		List<GtnUIFrameWorkActionConfig> closeActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeActionConfig = new GtnUIFrameWorkActionConfig();
		closeActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);

		closeActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.NET_SALES_FORMULA_POPUP_VIEW }));
		closeActionConfigList.add(closeActionConfig);

		nsfPopupCloseButtonConfig.setGtnUIFrameWorkActionConfigList(closeActionConfigList);

	}

}
