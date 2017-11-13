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
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkRPConstants;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkFormulaPopUpConfig {

	private final GtnFrameworkComponentConfigProvider formulaPopUpConfigurationFactory = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = formulaPopUpConfigurationFactory.getViewConfig(
				GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_VIEW,
				GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_VIEW, false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addFormulaPopUpSearchCriteriaPanel(componentList);
		addFormulaPopUpButtonLayout(componentList);
		addFormulaPopUpMainLayout(componentList);
		addFormulaPopUpResultPanel(componentList);

		formulaDetailsResultPanel(componentList);
		addFormulaPopUpActionButtonLayout(componentList);

	}

	private void addFormulaPopUpMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopUpSearchSearchViewMainCssConfig = formulaPopUpConfigurationFactory
				.getGtnCssLayoutConfig(GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_VIEW_MAIN_CSS_LAYOUT, false,
						null, GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> formulaPopUpSearchSearchViewMainCssCStyleList = new ArrayList<>();
		formulaPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		formulaPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		formulaPopUpSearchSearchViewMainCssConfig.setComponentStyle(formulaPopUpSearchSearchViewMainCssCStyleList);

		componentList.add(formulaPopUpSearchSearchViewMainCssConfig);

		GtnUIFrameworkComponentConfig formulaPopUpSearchViewLeftLayout = formulaPopUpConfigurationFactory
				.getGtnCssLayoutConfig("FormulaPopUpSearchSearchViewLeftLayout", true,
						GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_VIEW_MAIN_CSS_LAYOUT,
						GtnUIFrameworkLayoutType.CSS_LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		styleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		formulaPopUpSearchViewLeftLayout.setComponentStyle(styleList);

		componentList.add(formulaPopUpSearchViewLeftLayout);

		GtnUIFrameworkComponentConfig formulaPopUpSearchViewRightLayout = formulaPopUpConfigurationFactory
				.getGtnCssLayoutConfig("FormulaPopUpSearchSearchViewRightLayout", true,
						GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_VIEW_MAIN_CSS_LAYOUT,
						GtnUIFrameworkLayoutType.CSS_LAYOUT);

		formulaPopUpSearchViewRightLayout.setComponentStyle(styleList);

		componentList.add(formulaPopUpSearchViewRightLayout);

	}

	private void addFormulaPopUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanelConfig = formulaPopUpConfigurationFactory
				.getPanelConfig("searchCriteriaPanel", false, null);
		searchCriteriaPanelConfig.setComponentName("Search Criteria");
		searchCriteriaPanelConfig.setAuthorizationIncluded(true);
		componentList.add(searchCriteriaPanelConfig);
		addFieldLayout(componentList);
	}

	private void addFormulaPopUpResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaSearchResultConfig = formulaPopUpConfigurationFactory
				.getPanelConfig("resultPanel", true, "FormulaPopUpSearchSearchViewLeftLayout");
		formulaSearchResultConfig.setComponentName(GtnFrameworkStringConstants.RESULTS);
		formulaSearchResultConfig.setAuthorizationIncluded(true);
		formulaSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(formulaSearchResultConfig);

		addResultLayout(componentList);

	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultTableLayout = formulaPopUpConfigurationFactory
				.getHorizontalLayoutConfig(GtnFrameworkStringConstants.RESULTS_LAYOUT, true, "resultPanel");
		searchResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(searchResultTableLayout);

		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaLayoutConfig = formulaPopUpConfigurationFactory
				.getGtnCssLayoutConfig(GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel",
						GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		searchCriteriaLayoutConfig.setComponentStyle(styleList);
		componentList.add(searchCriteriaLayoutConfig);

		addFieldComponent(componentList);
	}

	private void addFormulaPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopupSearchCriteriaButtonLayout = formulaPopUpConfigurationFactory
				.getCssLayoutConfig(GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT, false, null);
		formulaPopupSearchCriteriaButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		formulaPopupSearchCriteriaButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		formulaPopupSearchCriteriaButtonLayout.setSpacing(true);
		formulaPopupSearchCriteriaButtonLayout.setMargin(true);
		componentList.add(formulaPopupSearchCriteriaButtonLayout);

		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addFormulaType(componentList);
		addFormulaNo(componentList);
		addFormulaName(componentList);
		addFormulaId(componentList);
	}

	private void addFormulaType(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaTypeLayoutConfig = formulaPopUpConfigurationFactory
				.getHorizontalLayoutConfig(GtnFrameworkStringConstants.FORMULA_TYPE_LAYOUT, true,
						GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaTypeLayoutConfig);

		GtnUIFrameworkComponentConfig formulaTypeComponentConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig(GtnFrameworkStringConstants.FORMULA_TYPE, true,
						formulaTypeLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		formulaTypeComponentConfig.setComponentName("Formula Type");
		formulaTypeComponentConfig.setAuthorizationIncluded(true);
		componentList.add(formulaTypeComponentConfig);

		GtnUIFrameworkComboBoxConfig addViewItmTypeComboConfig = formulaPopUpConfigurationFactory
				.getComboBoxConfig("FORMULA_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		formulaTypeComponentConfig.setGtnComboboxConfig(addViewItmTypeComboConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();

		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);

		formulaTypeComponentConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

	}

	private void addFormulaNo(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaNolayout = formulaPopUpConfigurationFactory.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.FORMULA_NO_LAYOUT, true,
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaNolayout);

		GtnUIFrameworkComponentConfig formulaNoComponentConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig(GtnFrameworkStringConstants.FORMULA_NO, true,
						formulaNolayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		formulaNoComponentConfig.setComponentName("Formula No");
		formulaNoComponentConfig.setAuthorizationIncluded(true);
		componentList.add(formulaNoComponentConfig);

		GtnUIFrameworkValidationConfig formulaNoValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> formulaNoconditions = new ArrayList<>();
		formulaNoconditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		formulaNoValidationConfig.setConditionList(formulaNoconditions);
		formulaNoComponentConfig.setGtnUIFrameworkValidationConfig(formulaNoValidationConfig);

	}

	private void addFormulaName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaNamelayout = formulaPopUpConfigurationFactory.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.FORMULA_NAME_LAYOUT, true,
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaNamelayout);

		GtnUIFrameworkComponentConfig formulaNameComponentConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig(GtnFrameworkStringConstants.FORMULA_NAME, true,
						formulaNamelayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		formulaNameComponentConfig.setComponentName("Formula Name");
		formulaNameComponentConfig.setAuthorizationIncluded(true);
		componentList.add(formulaNameComponentConfig);

		GtnUIFrameworkValidationConfig formulaNameValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> formulaNameconditions = new ArrayList<>();
		formulaNameconditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		formulaNameValidationConfig.setConditionList(formulaNameconditions);
		formulaNameComponentConfig.setGtnUIFrameworkValidationConfig(formulaNameValidationConfig);
	}

	private void addFormulaId(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaIdlayout = formulaPopUpConfigurationFactory.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.FORMULA_ID_LAYOUT, true,
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaIdlayout);

		GtnUIFrameworkComponentConfig formulaIdComponentConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig(GtnFrameworkStringConstants.SYSTEM_ID, true,
						formulaIdlayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		formulaIdComponentConfig.setComponentName("Formula Id");
		formulaIdComponentConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		formulaIdComponentConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(formulaIdComponentConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopupSearchBtnLayout = formulaPopUpConfigurationFactory
				.getHorizontalLayoutConfig("FormulaPopUpSearch01Layout", true,
						GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(formulaPopupSearchBtnLayout);

		GtnUIFrameworkComponentConfig formulaPopupSearchButtonConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig("FormulaPopUpSearch01", true,
						formulaPopupSearchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		formulaPopupSearchButtonConfig.setAuthorizationIncluded(true);
		formulaPopupSearchButtonConfig.setComponentName("SEARCH");
		componentList.add(formulaPopupSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchBtnactionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> loadDataTableActionParams = new ArrayList<>();
		loadDataTableActionParams.add(GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setActionParameterList(loadDataTableActionParams);
		loadDataTableActionConfig.setFieldValues(Arrays
				.asList(new String[] { GtnFrameworkStringConstants.FORMULA_TYPE, GtnFrameworkStringConstants.FORMULA_NO,
						GtnFrameworkStringConstants.FORMULA_NAME, GtnFrameworkStringConstants.SYSTEM_ID }));

		searchBtnactionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig searchNotificationActionConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		searchNotificationActionConfig.setActionParameterList(notificationParams);
		searchBtnactionConfigList.add(searchNotificationActionConfig);
		formulaPopupSearchButtonConfig.setGtnUIFrameWorkActionConfigList(searchBtnactionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopupResetBtnLayout = formulaPopUpConfigurationFactory
				.getHorizontalLayoutConfig("FormulaPopUpSearchSearchViewReset01Layout", true,
						GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(formulaPopupResetBtnLayout);

		GtnUIFrameworkComponentConfig formulaPopupResetButtonConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig("FormulaPopUpSearchSearchViewReset01", true,
						formulaPopupResetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		formulaPopupResetButtonConfig.setComponentName("RESET");
		formulaPopupResetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(formulaPopupResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> resetParams = new ArrayList<>();
		resetParams.add("Reset Confirmation");
		resetParams.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkStringConstants.FORMULA_NAME, "");
		resetMap.put(GtnFrameworkStringConstants.FORMULA_NO, "");
		resetMap.put(GtnFrameworkStringConstants.FORMULA_TYPE, null);
		resetMap.put(GtnFrameworkStringConstants.SYSTEM_ID, "");
		resetParams.add(resetMap);

		resetActionConfig.setActionParameterList(resetParams);
		resetActionConfigList.add(resetActionConfig);
		formulaPopupResetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaSearchResultConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig(GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE, true,
						GtnFrameworkStringConstants.RESULTS_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		formulaSearchResultConfig.setAuthorizationIncluded(true);
		formulaSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(formulaSearchResultConfig);

		GtnUIFrameworkValidationConfig formulaTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		formulaTypeValidationConfig.setMinSize(1);
		formulaSearchResultConfig.setGtnUIFrameworkValidationConfig(formulaTypeValidationConfig);

		GtnUIFrameworkPagedTableConfig formulaTypeTableConfig = new GtnUIFrameworkPagedTableConfig();
		formulaTypeTableConfig.setEditable(false);
		formulaTypeTableConfig.setFilterBar(true);
		formulaTypeTableConfig.setSelectable(true);
		formulaTypeTableConfig.setItemPerPage(5);
		formulaTypeTableConfig.setPageLength(5);
		formulaTypeTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		formulaTypeTableConfig
				.setTableVisibleHeader(new String[] { "Formula Type", "Formula ID", "Formula No", "Formula Name" });
		formulaTypeTableConfig.setTableColumnMappingId(
				new Object[] { GtnFrameworkStringConstants.FORMULA_TYPE, GtnFrameworkStringConstants.SYSTEM_ID,
						GtnFrameworkStringConstants.FORMULA_NO, GtnFrameworkStringConstants.FORMULA_NAME });
		formulaTypeTableConfig.setExtraColumn(new Object[] { "formula" });
		formulaTypeTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		formulaTypeTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		formulaTypeTableConfig.setModuleName("formulaPopUpConfig");
		formulaTypeTableConfig.setQueryName("formulaPopUpConfig");
		formulaTypeTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.FORECAST_FORMULA);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> formulaSearchFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig formulaFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		formulaFilterConfig.setPropertId(GtnFrameworkStringConstants.FORMULA_TYPE);
		formulaFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig formulaTypeFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		formulaTypeFilterComponentConfig.setComponentId("formulaTypecustomFilterComboBox");
		formulaTypeFilterComponentConfig.setComponentName("customFilterComboBox");
		GtnUIFrameworkComboBoxConfig formulaTypeConfig = formulaPopUpConfigurationFactory
				.getComboBoxConfig("FORMULA_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		formulaTypeFilterComponentConfig.setGtnComboboxConfig(formulaTypeConfig);

		formulaTypeFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		formulaFilterConfig.setGtnComponentConfig(formulaTypeFilterComponentConfig);

		formulaSearchFilterConfigMap.put(formulaFilterConfig.getPropertId(), formulaFilterConfig);
		formulaTypeTableConfig.setCustomFilterConfigMap(formulaSearchFilterConfigMap);
		formulaSearchResultConfig.setGtnPagedTableConfig(formulaTypeTableConfig);
	}

	private void formulaDetailsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaDetailsResultConfig = formulaPopUpConfigurationFactory
				.getPanelConfig("formulaDetailsResultPanel", true, "FormulaPopUpSearchSearchViewRightLayout");
		formulaDetailsResultConfig.setComponentName("Formula Details");
		formulaDetailsResultConfig.setAuthorizationIncluded(true);
		formulaDetailsResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(formulaDetailsResultConfig);
		formulaDetailsResultLayout(componentList);
	}

	private void formulaDetailsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig addViewResultTableLayout = formulaPopUpConfigurationFactory
				.getHorizontalLayoutConfig(GtnFrameworkStringConstants.FORMULA_DETAILS_RESULT_LAYOUT, true,
						"formulaDetailsResultPanel");
		addViewResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(addViewResultTableLayout);
		formulaDetailsResultDataTable(componentList);
	}

	private void formulaDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaDetailsResultConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig("FormulaPopUpformulaDetailsattachResultTable", true,
						GtnFrameworkStringConstants.FORMULA_DETAILS_RESULT_LAYOUT,
						GtnUIFrameworkComponentType.PAGEDTABLE);
		formulaDetailsResultConfig.setAuthorizationIncluded(true);
		formulaDetailsResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(formulaDetailsResultConfig);

		GtnUIFrameworkValidationConfig formulaDetailsValidationConfig = new GtnUIFrameworkValidationConfig();

		formulaDetailsValidationConfig.setMinSize(1);
		formulaDetailsResultConfig.setGtnUIFrameworkValidationConfig(formulaDetailsValidationConfig);

		GtnUIFrameworkPagedTableConfig formulaDetailsResultsTableConfig = new GtnUIFrameworkPagedTableConfig();
		formulaDetailsResultsTableConfig.setItemPerPage(5);
		formulaDetailsResultsTableConfig.setPageLength(6);
		formulaDetailsResultsTableConfig.setEditable(false);
		formulaDetailsResultsTableConfig.setFilterBar(false);
		formulaDetailsResultsTableConfig.setSelectable(true);
		formulaDetailsResultsTableConfig
				.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING });
		formulaDetailsResultsTableConfig.setTableVisibleHeader(new String[] { "Formula" });
		formulaDetailsResultsTableConfig.setTableColumnMappingId(new Object[] { "formula" });
		formulaDetailsResultsTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		formulaDetailsResultsTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		formulaDetailsResultsTableConfig.setModuleName("cDRPopUpConfig");
		formulaDetailsResultsTableConfig.setQueryName("cDRPopUpConfig");
		formulaDetailsResultConfig.setGtnPagedTableConfig(formulaDetailsResultsTableConfig);
	}

	private void addFormulaPopUpActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopupActionBtnLayout = formulaPopUpConfigurationFactory
				.getCssLayoutConfig(GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT, false, null);
		formulaPopupActionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		formulaPopupActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		formulaPopupActionBtnLayout.setSpacing(true);
		formulaPopupActionBtnLayout.setMargin(true);
		componentList.add(formulaPopupActionBtnLayout);

		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
		addDetailsButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopupSelectBtnLayout = formulaPopUpConfigurationFactory
				.getHorizontalLayoutConfig("FormulaPopUpSearchSearchViewAddButtonLayout", true,
						GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(formulaPopupSelectBtnLayout);

		GtnUIFrameworkComponentConfig selectButtonConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig("FormulaPopUpSearchSearchViewAddButton", true,
						formulaPopupSelectBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		selectButtonConfig.setAuthorizationIncluded(true);
		selectButtonConfig.setComponentName("SELECT");
		componentList.add(selectButtonConfig);

		List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectActionConfig = new GtnUIFrameWorkActionConfig();
		selectActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> selectActionParameter = new ArrayList<>();
		selectActionParameter.add(GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE);
		selectActionParameter.add("rebatePlanCalculationsTierFormula");
		selectActionParameter.add(Arrays.asList(
				new String[] { GtnFrameworkStringConstants.FORMULA_NAME, GtnFrameworkStringConstants.FORMULA_NO }));
		selectActionParameter.add(Arrays
				.asList(new String[] { "rebatePlanCalculationsTierFormula", "rebatePlanCalculationsTierFormulaNo" }));

		selectActionConfig.setActionParameterList(selectActionParameter);
		selectActionConfigList.add(selectActionConfig);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);

		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_VIEW }));
		selectActionConfigList.add(closeAction);

		selectButtonConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopupCloseBtnLayout = formulaPopUpConfigurationFactory
				.getHorizontalLayoutConfig("FormulaPopUpSearchSearchViewEditButtonLayout", true,
						GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(formulaPopupCloseBtnLayout);

		GtnUIFrameworkComponentConfig closeButtonConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig("FormulaPopUpSearchSearchViewEditButton", true,
						formulaPopupCloseBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setAuthorizationIncluded(true);
		closeButtonConfig.setComponentName("CLOSE");
		componentList.add(closeButtonConfig);

		List<GtnUIFrameWorkActionConfig> closeActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeActionConfig = new GtnUIFrameWorkActionConfig();
		closeActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.FORMULA_POPUP_SEARCH_VIEW }));
		closeActionConfigList.add(closeActionConfig);

		closeButtonConfig.setGtnUIFrameWorkActionConfigList(closeActionConfigList);

	}

	private void addDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopupDetailsBtnLayout = formulaPopUpConfigurationFactory
				.getHorizontalLayoutConfig("cDRAddViewButtonLayout", true,
						GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(formulaPopupDetailsBtnLayout);

		GtnUIFrameworkComponentConfig detailsButtonConfig = formulaPopUpConfigurationFactory
				.getUIFrameworkComponentConfig("cDRAddViewButton", true, formulaPopupDetailsBtnLayout.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		detailsButtonConfig.setAuthorizationIncluded(true);
		detailsButtonConfig.setComponentName("DETAILS");
		componentList.add(detailsButtonConfig);

		List<GtnUIFrameWorkActionConfig> detailsActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = formulaPopUpConfigurationFactory
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkRPConstants.ADD_DATA_TABLE }));
		detailsActionConfigList.add(customActionforLastOperatorCheck);

		detailsButtonConfig.setGtnUIFrameWorkActionConfigList(detailsActionConfigList);

	}

}
