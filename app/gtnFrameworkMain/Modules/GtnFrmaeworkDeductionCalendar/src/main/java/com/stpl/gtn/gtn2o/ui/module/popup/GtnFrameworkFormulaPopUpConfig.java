/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.popup;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
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
public class GtnFrameworkFormulaPopUpConfig {

	private final GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

	public GtnUIFrameworkViewConfig getSearchView() {
		gtnFrameworkConfigurationFactory.setViewId(GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW);
		gtnFrameworkConfigurationFactory.setViewName(GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW);
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
		addMainLayout(componentList);
		addResultPanel(componentList);

		formulaDetailsResultPanel(componentList);
		addActionButtonLayout(componentList);

	}

	private void addMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {


		GtnUIFrameworkComponentConfig formulaPopUpSearchSearchViewMainCssConfig = new GtnUIFrameworkComponentConfig();
		formulaPopUpSearchSearchViewMainCssConfig.setComponentId(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		formulaPopUpSearchSearchViewMainCssConfig.setAddToParent(false);
		formulaPopUpSearchSearchViewMainCssConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		List<String> formulaPopUpSearchSearchViewMainCssCStyleList = new ArrayList<>();
		formulaPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		formulaPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		formulaPopUpSearchSearchViewMainCssConfig.setComponentStyle(formulaPopUpSearchSearchViewMainCssCStyleList);

		GtnUIFrameworkLayoutConfig formulaPopUpSearchSearchViewMainCssLayout = new GtnUIFrameworkLayoutConfig();
		formulaPopUpSearchSearchViewMainCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		formulaPopUpSearchSearchViewMainCssConfig.setGtnLayoutConfig(formulaPopUpSearchSearchViewMainCssLayout);

		componentList.add(formulaPopUpSearchSearchViewMainCssConfig);

		GtnUIFrameworkComponentConfig cssLayoutConfig = new GtnUIFrameworkComponentConfig();
		cssLayoutConfig.setComponentId("FormulaPopUpSearchSearchViewLeftLayout");
		cssLayoutConfig.setAddToParent(true);
		cssLayoutConfig.setParentComponentId(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		cssLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		styleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		cssLayoutConfig.setComponentStyle(styleList);

		GtnUIFrameworkLayoutConfig cssLayout = new GtnUIFrameworkLayoutConfig();
		cssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cssLayoutConfig.setGtnLayoutConfig(cssLayout);

		componentList.add(cssLayoutConfig);

		GtnUIFrameworkComponentConfig cssLayoutConfg = new GtnUIFrameworkComponentConfig();
		cssLayoutConfg.setComponentId("FormulaPopUpSearchSearchViewRightLayout");
		cssLayoutConfg.setAddToParent(true);
		cssLayoutConfg.setParentComponentId(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		cssLayoutConfg.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		List<String> styleLists = new ArrayList<>();
		styleLists.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		styleLists.add(GtnFrameworkCssConstants.NO_MARGIN);

		cssLayoutConfg.setComponentStyle(styleLists);

		GtnUIFrameworkLayoutConfig cssLayouts = new GtnUIFrameworkLayoutConfig();
		cssLayouts.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cssLayoutConfg.setGtnLayoutConfig(cssLayouts);

		componentList.add(cssLayoutConfg);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"searchCriteriaPanel", "Search Criteria", false, GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(panel);
		addFieldLayout(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = gtnFrameworkConfigurationFactory.buildComponentConfig("resultPanel",
				GtnFrameworkCommonConstants.RESULTS, true, GtnUIFrameworkComponentType.PANEL);
		panelConfig.setParentComponentId("FormulaPopUpSearchSearchViewLeftLayout");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(panelConfig);
		addResultLayout(componentList);

	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(GtnFrameworkCommonConstants.RESULTLAYOUT,
				GtnFrameworkCommonConstants.RESULTLAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
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
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
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
				GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, false, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
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
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT, GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = gtnFrameworkConfigurationFactory.buildComponentConfig(GtnFrameworkCommonConstants.FORMULA_TYPE,
				"Formula Type", true, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setParentComponentId(GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("FORMULA_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();

		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);

		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addFormulaNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT, GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.FORMULA_NO, "Formula NO", true, GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setParentComponentId(GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addFormulaName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT, GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.FORMULA_NAME, "Formula Name", true, GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setParentComponentId(GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyNameConfig);
	}

	private void addFormulaId(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.FORMULA_ID_LAYOUT, GtnFrameworkCommonConstants.FORMULA_ID_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig(GtnFrameworkCommonConstants.SYSTEM_ID, "Formula Id", true, GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setParentComponentId(GtnFrameworkCommonConstants.FORMULA_ID_LAYOUT);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyNameConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("FormulaPopUpSearch01", "Search", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();


		GtnUIFrameWorkActionConfig loadDataTableActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.FORMULA_TYPE, GtnFrameworkCommonConstants.FORMULA_NO, GtnFrameworkCommonConstants.FORMULA_NAME, GtnFrameworkCommonConstants.SYSTEM_ID }));

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

		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"FormulaPopUpSearchSearchViewReset01", "Reset", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.FORMULA_TYPE, null);
		resetMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, "");
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE, GtnFrameworkCommonConstants.RESULTS, true, GtnUIFrameworkComponentType.PAGEDTABLE);

		searchResultConfig.setParentComponentId(GtnFrameworkCommonConstants.RESULTLAYOUT);
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
		searchResults.setItemPerPage(5);
		searchResults.setPageLength(5);
		searchResults.setTableColumnDataType(
				new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResults
				.setTableVisibleHeader(new String[] { "Formula Type", "Formula Id", "Formula No", "Formula Name" });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.FORMULA_TYPE, GtnFrameworkCommonConstants.SYSTEM_ID, GtnFrameworkCommonConstants.FORMULA_NO, GtnFrameworkCommonConstants.FORMULA_NAME });
		searchResults.setExtraColumn(new Object[] { "formula" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("formulaPopUpConfig");
		searchResults.setQueryName("formulaPopUpConfig");
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.FORECAST_FORMULA);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.FORMULA_TYPE);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("formulaTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName("customFilterComboBox");
		GtnUIFrameworkComboBoxConfig formulaTypeConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("FORMULA_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		customFilterComponentConfig.setGtnComboboxConfig(formulaTypeConfig);

		customFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);

		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void formulaDetailsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"formulaDetailsResultPanel", "Formula Details", true, GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setParentComponentId("FormulaPopUpSearchSearchViewRightLayout");
		componentList.add(panel);
		formulaDetailsResultLayout(componentList);
	}

	private void formulaDetailsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_DETAILS_RESULT_LAYOUT, GtnFrameworkCommonConstants.FORMULA_DETAILS_RESULT_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		layoutConfig.setParentComponentId("formulaDetailsResultPanel");

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		formulaDetailsResultDataTable(componentList);
	}

	private void formulaDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachResultConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"FormulaPopUpformulaDetailsattachResultTable", GtnFrameworkCommonConstants.RESULTS, true, GtnUIFrameworkComponentType.PAGEDTABLE);

		attachResultConfig.setParentComponentId(GtnFrameworkCommonConstants.FORMULA_DETAILS_RESULT_LAYOUT);
		attachResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		attachResultConfig.setComponentStyle(tableStyle);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		gtnUIFrameworkValidationConfig.setMinSize(1);
		attachResultConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(attachResultConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		attachResultConfig.setGtnPagedTableConfig(attachResults);

		attachResults.setItemPerPage(5);
		attachResults.setPageLength(5);
		attachResults.setEditable(false);
		attachResults.setFilterBar(false);
		attachResults.setSelectable(true);
		attachResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING });
		attachResults.setTableVisibleHeader(new String[] { "Formula" });
		attachResults.setTableColumnMappingId(new Object[] { "formula" });
		attachResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		attachResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		attachResults.setModuleName("cDRPopUpConfig");
		attachResults.setQueryName("cDRPopUpConfig");
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(
				gtnFrameworkConfigurationFactory.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT));
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
		addDetailsButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRAddButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"FormulaPopUpSearchSearchViewAddButton", "SELECT", true, GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDRAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE);
		actionParameter.add("rebatePlanCalculationsTierFormula");
		actionParameter.add(Arrays.asList(new String[] { GtnFrameworkCommonConstants.FORMULA_NAME }));
		actionParameter.add(Arrays.asList(new String[] { "rebatePlanCalculationsTierFormula" }));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);

		closeAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDRAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDREditButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"FormulaPopUpSearchSearchViewEditButton", "CLOSE", true, GtnUIFrameworkComponentType.BUTTON);
		cDREditButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDREditButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDREditButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("cDRAddViewButton");
		searchButtonConfig.setComponentName("DETAILS");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck.setActionParameterList(Arrays.asList(new Object[] {
				"com.stpl.gtn.gtn2o.ui.module.rebateplanconfig.logic.GtnUIFrameWorkAddDataTableAction" }));
		actionConfigList.add(customActionforLastOperatorCheck);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
