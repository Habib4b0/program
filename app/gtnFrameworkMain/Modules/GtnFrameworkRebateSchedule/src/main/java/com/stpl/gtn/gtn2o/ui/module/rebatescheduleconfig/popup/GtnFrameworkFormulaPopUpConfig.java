
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

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
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkLoadRuleDetailsAction;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkFormulaPopUpConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW,
				GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW, false);
		addFormulaPopupComponentList(view);
		return view;
	}

	private void addFormulaPopupComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> formulaPopupComponentList = new ArrayList<>();
		view.setGtnComponentList(formulaPopupComponentList);
		addFormulaPopupSearchCriteriaPanel(formulaPopupComponentList);
		addFormulaPopupButtonLayout(formulaPopupComponentList);
		addFormulaPopupMainLayout(formulaPopupComponentList);
		addFormulaPopupResultPanel(formulaPopupComponentList);

		formulaPopupDetailsResultPanel(formulaPopupComponentList);
		addFormulaPopupActionButtonLayout(formulaPopupComponentList);

	}

	private void addFormulaPopupMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopUpSearchSearchViewMainCssConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT, false, null);
		List<String> formulaPopUpSearchSearchViewMainCssCStyleList = new ArrayList<>();
		formulaPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		formulaPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		formulaPopUpSearchSearchViewMainCssConfig.setComponentStyle(formulaPopUpSearchSearchViewMainCssCStyleList);
		componentList.add(formulaPopUpSearchSearchViewMainCssConfig);

		GtnUIFrameworkComponentConfig formulaPopupcssLayoutConfig = configProvider.getCssLayoutConfig(
				"FormulaPopUpSearchSearchViewLeftLayout", true,
				GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		styleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		formulaPopupcssLayoutConfig.setComponentStyle(styleList);

		componentList.add(formulaPopupcssLayoutConfig);

		GtnUIFrameworkComponentConfig cssLayoutConfg = configProvider.getCssLayoutConfig(
				"FormulaPopUpSearchSearchViewRightLayout", true,
				GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
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

	private void addFormulaPopupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanelConfig = configProvider.getPanelConfig("searchCriteriaPanel",
				false, null);
		searchCriteriaPanelConfig.setComponentName("Search Criteria");
		searchCriteriaPanelConfig.setAuthorizationIncluded(true);
		componentList.add(searchCriteriaPanelConfig);
		addFormulaPopupFieldLayout(componentList);
	}

	private void addFormulaPopupResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig("resultPanel", true,
				"FormulaPopUpSearchSearchViewLeftLayout");
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(panelConfig);
		addFormulaPopupResultLayout(componentList);

	}

	private void addFormulaPopupResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.RESULT_LAYOUT, true, "resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addFormulaPopupPagedTableComponent(componentList);
	}

	private void addFormulaPopupFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout.setComponentStyle(styleList);
		componentList.add(gtnLayout);
		addFormulaPopupFieldComponent(componentList);
	}

	private void addFormulaPopupButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, false, null);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFormulaPopupFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addFormulaPopupFormulaType(componentList);
		addFormulaPopupFormulaNo(componentList);
		addFormulaPopupFormulaName(componentList);
		addFormulaPopupFormulaId(componentList);
	}

	private void addFormulaPopupFormulaType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_TYPE, true, GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("Formula Type");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("FORMULA_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();

		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);

		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addFormulaPopupFormulaNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig formulaNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_NO, true, GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaNoConfig.setAuthorizationIncluded(true);
		formulaNoConfig.setComponentName("Formula NO");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		formulaNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(formulaNoConfig);
	}

	private void addFormulaPopupFormulaName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig formulaNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_NAME, true, GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaNameConfig.setAuthorizationIncluded(true);
		formulaNameConfig.setComponentName("Formula Name");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		formulaNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(formulaNameConfig);
	}

	private void addFormulaPopupFormulaId(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_ID_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.SYSTEM_ID, true, GtnFrameworkCommonConstants.FORMULA_ID_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName("Formula Id");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyNameConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"FormulaPopUpSearch01", true, GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.FORMULA_TYPE, GtnFrameworkCommonConstants.FORMULA_NO,
						GtnFrameworkCommonConstants.FORMULA_NAME, GtnFrameworkCommonConstants.SYSTEM_ID));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		notificationActionConfig.setActionParameterList(notificationParams);
		actionConfigList.add(notificationActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"FormulaPopUpSearchSearchViewReset01", true, GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("Reset");
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> forumlaPopUpResetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig forumlaPopUpRestActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		forumlaPopUpRestActionConfig.addActionParameter("Reset Confirmation");
		forumlaPopUpRestActionConfig
				.addActionParameter("Are you sure you want to reset the values in the Search Criteria group box?");
		forumlaPopUpRestActionConfig.addActionParameter(Arrays
				.asList(new String[] { GtnFrameworkCommonConstants.FORMULA_NAME, GtnFrameworkCommonConstants.FORMULA_NO,
						GtnFrameworkCommonConstants.FORMULA_TYPE, GtnFrameworkCommonConstants.SYSTEM_ID }));
		forumlaPopUpRestActionConfig.addActionParameter(Arrays.asList("", "", null, ""));

		forumlaPopUpResetActionConfigList.add(forumlaPopUpRestActionConfig);
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(forumlaPopUpResetActionConfigList);

	}

	private void addFormulaPopupPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig forumlaPopUpSearchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE, true,
				GtnFrameworkCommonConstants.RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		forumlaPopUpSearchResultConfig.setAuthorizationIncluded(true);
		forumlaPopUpSearchResultConfig.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		forumlaPopUpSearchResultConfig.setComponentStyle(tableStyle);
		forumlaPopUpSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		forumlaPopUpSearchResultConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(forumlaPopUpSearchResultConfig);

		GtnUIFrameworkPagedTableConfig forumlaPopUpSearchResults = new GtnUIFrameworkPagedTableConfig();
		forumlaPopUpSearchResults.setEditable(false);
		forumlaPopUpSearchResults.setFilterBar(true);
		forumlaPopUpSearchResults.setSelectable(true);
		forumlaPopUpSearchResults.setItemPerPage(5);
		forumlaPopUpSearchResults.setPageLength(5);
		forumlaPopUpSearchResults
				.setTableColumnDataType(new Class[] { String.class, String.class, String.class, String.class });
		forumlaPopUpSearchResults
				.setTableVisibleHeader(new String[] { "Formula Type", "Formula Id", "Formula No", "Formula Name" });
		forumlaPopUpSearchResults
				.setTableColumnMappingId(new Object[] { "formulaType", "systemId", "formulaNo", "formulaName" });
		forumlaPopUpSearchResults.setExtraColumn(new Object[] { "formula" });
		forumlaPopUpSearchResults.setExtraColumnDataType(new Class[] { String.class });
		forumlaPopUpSearchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		forumlaPopUpSearchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		forumlaPopUpSearchResults.setModuleName("formulaPopUpConfig");
		forumlaPopUpSearchResults.setQueryName("formulaPopUpConfig");
		forumlaPopUpSearchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.FORECAST_FORMULA);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.FORMULA_TYPE);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("formulaTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName("customFilterComboBox");
		GtnUIFrameworkComboBoxConfig formulaTypeConfig = configProvider.getComboBoxConfig("FORMULA_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		customFilterComponentConfig.setGtnComboboxConfig(formulaTypeConfig);

		customFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);

		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		forumlaPopUpSearchResults.setCustomFilterConfigMap(customFilterConfigMap);
		forumlaPopUpSearchResultConfig.setGtnPagedTableConfig(forumlaPopUpSearchResults);
	}

	private void formulaPopupDetailsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("formulaDetailsResultPanel", true,
				"FormulaPopUpSearchSearchViewRightLayout");
		panel.setComponentName("Formula Details");
		componentList.add(panel);
		formulaPopupDetailsResultLayout(componentList);
	}

	private void formulaPopupDetailsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_DETAILS_RESULT_LAYOUT, true, "formulaDetailsResultPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		componentList.add(layoutConfig);

		formulaDetailsResultDataTable(componentList);
	}

	private void formulaDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig forumlaPopUpDeatilsTableConfig = configProvider.getUIFrameworkComponentConfig(
				"FormulaPopUpformulaDetailsattachResultTable", true,
				GtnFrameworkCommonConstants.FORMULA_DETAILS_RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		forumlaPopUpDeatilsTableConfig.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		forumlaPopUpDeatilsTableConfig.setAuthorizationIncluded(true);

		forumlaPopUpDeatilsTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		forumlaPopUpDeatilsTableConfig.setComponentStyle(tableStyle);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		gtnUIFrameworkValidationConfig.setMinSize(1);
		forumlaPopUpDeatilsTableConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(forumlaPopUpDeatilsTableConfig);

		GtnUIFrameworkPagedTableConfig forumlaPopUpDeatilsTable = new GtnUIFrameworkPagedTableConfig();
		forumlaPopUpDeatilsTableConfig.setGtnPagedTableConfig(forumlaPopUpDeatilsTable);

		forumlaPopUpDeatilsTable.setItemPerPage(5);
		forumlaPopUpDeatilsTable.setPageLength(5);
		forumlaPopUpDeatilsTable.setEditable(false);
		forumlaPopUpDeatilsTable.setFilterBar(false);
		forumlaPopUpDeatilsTable.setSelectable(true);
		forumlaPopUpDeatilsTable.setTableColumnDataType(new Class<?>[] { String.class });
		forumlaPopUpDeatilsTable.setTableVisibleHeader(new String[] { "Formula" });
		forumlaPopUpDeatilsTable.setTableColumnMappingId(new Object[] { "formula" });
		forumlaPopUpDeatilsTable.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		forumlaPopUpDeatilsTable.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		forumlaPopUpDeatilsTable.setModuleName("formulaPopUpDetailsConfig");
		forumlaPopUpDeatilsTable.setQueryName("formulaPopUpDetailsConfig");
		forumlaPopUpDeatilsTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.FORECAST_FORMULA);
	}

	private void addFormulaPopupActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addFormulaPopupSelectButtonComponent(componentList);
		addFormulaPopupCloseButtonComponent(componentList);
		addFormulaPopupDetailsButtonComponent(componentList);
	}

	private void addFormulaPopupSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig selectButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"FormulaPopUpSearchSearchViewAddButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		selectButtonConfig.setAuthorizationIncluded(true);
		selectButtonConfig.setComponentName("SELECT");
		componentList.add(selectButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE);
		actionParameter.add("rebatePlanCalculationsTierFormula");
		actionParameter.add(Arrays.asList(GtnFrameworkCommonConstants.FORMULA_NAME));
		actionParameter.add(Arrays.asList("rebatePlanCalculationsTierFormula"));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		Object viewName = GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW;
		closeAction.setActionParameterList(Arrays.asList(viewName));
		actionConfigList.add(closeAction);

		selectButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addFormulaPopupCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig closeButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"FormulaPopUpSearchSearchViewEditButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setAuthorizationIncluded(true);
		closeButtonConfig.setComponentName("CLOSE");
		componentList.add(closeButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		Object viewName = GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW;
		closeAction.setActionParameterList(Arrays.asList(viewName));
		actionConfigList.add(closeAction);

		closeButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addFormulaPopupDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig detailsButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"cDRAddViewButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		detailsButtonConfig.setAuthorizationIncluded(true);
		detailsButtonConfig.setComponentName("DETAILS");

		componentList.add(detailsButtonConfig);
		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig tableLoadActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnFrameworkLoadRuleDetailsAction.class.getName());
		tableLoadActionConfig.addActionParameter(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE);
		tableLoadActionConfig.addActionParameter(4);
		tableLoadActionConfig.addActionParameter("FormulaPopUpformulaDetailsattachResultTable");

		validationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		validationActionConfig.addActionParameter(Arrays.asList(tableLoadActionConfig));

		detailsButtonConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);

	}

}
