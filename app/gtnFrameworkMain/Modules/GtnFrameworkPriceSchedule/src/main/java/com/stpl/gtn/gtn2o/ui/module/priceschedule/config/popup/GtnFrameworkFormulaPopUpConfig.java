
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
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkFormulaPopUpConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig formulaPopupView = configProvider.getViewConfig(
				GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW,
				GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW, false);
		addComponentList(formulaPopupView);
		return formulaPopupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addFormulaPopUpSearchCriteriaPanel(componentList);
		addFormulaPopUpButtonLayout(componentList);
		addFormulaPopUpMainLayout(componentList);
		addFormulaPopUpResultPanel(componentList);

		formulaDetailsResultPanel(componentList);
		addActionButtonLayout(componentList);

	}

	private void addFormulaPopUpMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopUpMainCssConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT, false, null);
		List<String> formulaPopUpSearchSearchViewMainCssCStyleList = new ArrayList<>();
		formulaPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		formulaPopUpSearchSearchViewMainCssCStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		formulaPopUpMainCssConfig.setComponentStyle(formulaPopUpSearchSearchViewMainCssCStyleList);
		componentList.add(formulaPopUpMainCssConfig);

		GtnUIFrameworkComponentConfig formulaPopUpLeftCssLayout = configProvider.getCssLayoutConfig(
				"FormulaPopUpSearchSearchViewLeftLayout", true,
				GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		styleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		formulaPopUpLeftCssLayout.setComponentStyle(styleList);

		componentList.add(formulaPopUpLeftCssLayout);

		GtnUIFrameworkComponentConfig formulaPopUpRightCssLayout = configProvider.getCssLayoutConfig(
				"FormulaPopUpSearchSearchViewRightLayout", true,
				GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_SEARCH_VIEW_MAIN_CSS_LAYOUT);
		List<String> styleLists = new ArrayList<>();
		styleLists.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		styleLists.add(GtnFrameworkCssConstants.NO_MARGIN);
		formulaPopUpRightCssLayout.setComponentStyle(styleLists);
		componentList.add(formulaPopUpRightCssLayout);

	}

	private void addFormulaPopUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopUpSearchPanel = configProvider.getPanelConfig("searchCriteriaPanel",
				false, null);
		formulaPopUpSearchPanel.setComponentName("Search Criteria");
		formulaPopUpSearchPanel.setAuthorizationIncluded(true);
		componentList.add(formulaPopUpSearchPanel);
		addFieldLayout(componentList);
	}

	private void addFormulaPopUpResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpResultPanel = configProvider.getPanelConfig("resultPanel", true,
				"FormulaPopUpSearchSearchViewLeftLayout");
		formulaPopUpResultPanel.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		formulaPopUpResultPanel.setAuthorizationIncluded(true);
		componentList.add(formulaPopUpResultPanel);
		addResultLayout(componentList);

	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpResultLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.RESULT_LAYOUT, true, "resultPanel");
		formulaPopUpResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(formulaPopUpResultLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpFieldLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		formulaPopUpFieldLayout.setComponentStyle(styleList);
		componentList.add(formulaPopUpFieldLayout);
		addFieldComponent(componentList);
	}

	private void addFormulaPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, false, null);
		formulaPopUpButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(formulaPopUpButtonLayout);
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
		GtnUIFrameworkComponentConfig formulaPopUpFormulaTypeLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaPopUpFormulaTypeLayout);

		GtnUIFrameworkComponentConfig formulaPopUpFormulaType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE, true,
				GtnFrameworkCommonConstants.FORMULA_TYPE_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		formulaPopUpFormulaType.setComponentName("Formula Type");
		formulaPopUpFormulaType.setAuthorizationIncluded(true);
		componentList.add(formulaPopUpFormulaType);

		GtnUIFrameworkComboBoxConfig formulaPopUpFormulaTypeConfig = configProvider.getComboBoxConfig("FORMULA_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig formulaPopUpFormulaTypeValConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		formulaPopUpFormulaTypeValConfig.setConditionList(conditions);
		formulaPopUpFormulaType.setGtnUIFrameworkValidationConfig(formulaPopUpFormulaTypeValConfig);
		formulaPopUpFormulaType.setGtnComboboxConfig(formulaPopUpFormulaTypeConfig);
	}

	private void addFormulaNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpFormulaNoLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaPopUpFormulaNoLayout);

		GtnUIFrameworkComponentConfig formulaPopUpFormulaNo = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_FORMULA_NO, true, GtnFrameworkCommonConstants.FORMULA_NO_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaPopUpFormulaNo.setComponentName("Formula NO");
		formulaPopUpFormulaNo.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig formulaPopUpFormulaNoValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		formulaPopUpFormulaNoValidationConfig.setConditionList(conditions);
		formulaPopUpFormulaNo.setGtnUIFrameworkValidationConfig(formulaPopUpFormulaNoValidationConfig);

		componentList.add(formulaPopUpFormulaNo);
	}

	private void addFormulaName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopUpFormulaNameLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaPopUpFormulaNameLayout);

		GtnUIFrameworkComponentConfig formulaPopUpFormulaName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_NAME, true, GtnFrameworkCommonConstants.FORMULA_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaPopUpFormulaName.setComponentName("Formula Name");
		formulaPopUpFormulaName.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig formulaPopUpFormulaNameValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		formulaPopUpFormulaNameValidationConfig.setConditionList(conditions);
		formulaPopUpFormulaName.setGtnUIFrameworkValidationConfig(formulaPopUpFormulaNameValidationConfig);
		componentList.add(formulaPopUpFormulaName);
	}

	private void addFormulaId(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopUpFormulaIdLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_ID_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(formulaPopUpFormulaIdLayout);

		GtnUIFrameworkComponentConfig formulaPopUpFormulaId = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.SYSTEM_ID, true, GtnFrameworkCommonConstants.FORMULA_ID_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		formulaPopUpFormulaId.setComponentName("Formula Id");
		formulaPopUpFormulaId.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig formulaPopUpFormulaIdValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		formulaPopUpFormulaIdValidationConfig.setConditionList(conditions);
		formulaPopUpFormulaId.setGtnUIFrameworkValidationConfig(formulaPopUpFormulaIdValidationConfig);
		componentList.add(formulaPopUpFormulaId);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpSearchBtn = configProvider.getUIFrameworkComponentConfig(
				"FormulaPopUpSearch01", true, GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		formulaPopUpSearchBtn.setComponentName("Search");
		formulaPopUpSearchBtn.setAuthorizationIncluded(true);
		componentList.add(formulaPopUpSearchBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE,
				GtnFrameworkCommonConstants.PROPERTY_FORMULA_NO, GtnFrameworkCommonConstants.FORMULA_NAME,
				GtnFrameworkCommonConstants.SYSTEM_ID));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		notificationActionConfig.setActionParameterList(notificationParams);
		actionConfigList.add(notificationActionConfig);
		formulaPopUpSearchBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig formulaPopUpResetBtn = configProvider.getUIFrameworkComponentConfig(
				"FormulaPopUpSearchSearchViewReset01", true, GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		formulaPopUpResetBtn.setAuthorizationIncluded(true);
		formulaPopUpResetBtn.setComponentName("Reset");
		componentList.add(formulaPopUpResetBtn);

		List<GtnUIFrameWorkActionConfig> formulaPopUpResetBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> searchButtonResetMap = new HashMap<>();
		searchButtonResetMap.put(GtnFrameworkCommonConstants.FORMULA_NAME, "");
		searchButtonResetMap.put(GtnFrameworkCommonConstants.PROPERTY_FORMULA_NO, "");
		searchButtonResetMap.put(GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE, null);
		searchButtonResetMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, "");
		params.add(searchButtonResetMap);

		resetActionConfig.setActionParameterList(params);
		formulaPopUpResetBtnActionConfigList.add(resetActionConfig);
		formulaPopUpResetBtn.setGtnUIFrameWorkActionConfigList(formulaPopUpResetBtnActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpResultTable = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE, true,
				GtnFrameworkCommonConstants.RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		formulaPopUpResultTable.setAuthorizationIncluded(true);
		formulaPopUpResultTable.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		formulaPopUpResultTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig formulaPopUpResultTableValidationConfig = new GtnUIFrameworkValidationConfig();
		formulaPopUpResultTableValidationConfig.setMinSize(1);
		formulaPopUpResultTable.setGtnUIFrameworkValidationConfig(formulaPopUpResultTableValidationConfig);

		componentList.add(formulaPopUpResultTable);

		GtnUIFrameworkPagedTableConfig formulaPopUpResultConfig = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"formulaPopUpConfig", "formulaPopUpConfig");
		formulaPopUpResultConfig.setEditable(false);
		formulaPopUpResultConfig.setItemPerPage(5);
		formulaPopUpResultConfig.setPageLength(5);
		formulaPopUpResultConfig
				.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class, String.class });
		formulaPopUpResultConfig
				.setTableVisibleHeader(new String[] { "Formula Type", "Formula Id", "Formula No", "Formula Name" });
		formulaPopUpResultConfig.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE, GtnFrameworkCommonConstants.SYSTEM_ID,
						GtnFrameworkCommonConstants.PROPERTY_FORMULA_NO, GtnFrameworkCommonConstants.FORMULA_NAME });
		formulaPopUpResultConfig.setExtraColumn(new Object[] { "formula" });
		formulaPopUpResultConfig.setExtraColumnDataType(new Class<?>[] { String.class });
		formulaPopUpResultConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.FORECAST_FORMULA);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.PROPERTY_FORMULA_TYPE);
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
		formulaPopUpResultConfig.setCustomFilterConfigMap(customFilterConfigMap);
		formulaPopUpResultTable.setGtnPagedTableConfig(formulaPopUpResultConfig);
	}

	private void formulaDetailsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpFormulaDetailsPanel = configProvider
				.getPanelConfig("formulaDetailsResultPanel", true, null);
		formulaPopUpFormulaDetailsPanel.setComponentName("Formula Details");
		formulaPopUpFormulaDetailsPanel.setAuthorizationIncluded(true);
		formulaPopUpFormulaDetailsPanel.setParentComponentId("FormulaPopUpSearchSearchViewRightLayout");
		componentList.add(formulaPopUpFormulaDetailsPanel);
		formulaDetailsResultLayout(componentList);
	}

	private void formulaDetailsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpFormulaDetailsLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.FORMULA_DETAILS_RESULT_LAYOUT, true, "formulaDetailsResultPanel");
		componentList.add(formulaPopUpFormulaDetailsLayout);

		formulaDetailsResultDataTable(componentList);
	}

	private void formulaDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpFormulaDetailsResultTable = configProvider
				.getUIFrameworkComponentConfig("FormulaPopUpformulaDetailsattachResultTable", true,
						GtnFrameworkCommonConstants.FORMULA_DETAILS_RESULT_LAYOUT,
						GtnUIFrameworkComponentType.PAGEDTABLE);
		formulaPopUpFormulaDetailsResultTable.setAuthorizationIncluded(true);
		formulaPopUpFormulaDetailsResultTable.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		formulaPopUpFormulaDetailsResultTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		formulaPopUpFormulaDetailsResultTable.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(formulaPopUpFormulaDetailsResultTable);

		GtnUIFrameworkPagedTableConfig formulaPopUpFormulaDetailsTableConfig = configProvider.getPagedTableConfig(false,
				true, GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"cDRPopUpConfig", "cDRPopUpConfig");
		formulaPopUpFormulaDetailsResultTable.setGtnPagedTableConfig(formulaPopUpFormulaDetailsTableConfig);

		formulaPopUpFormulaDetailsTableConfig.setItemPerPage(5);
		formulaPopUpFormulaDetailsTableConfig.setPageLength(5);
		formulaPopUpFormulaDetailsTableConfig.setEditable(false);
		formulaPopUpFormulaDetailsTableConfig.setTableColumnDataType(new Class<?>[] { String.class });
		formulaPopUpFormulaDetailsTableConfig.setTableVisibleHeader(new String[] { "Formula" });
		formulaPopUpFormulaDetailsTableConfig.setTableColumnMappingId(new Object[] { "formula" });
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpActionBtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT, false, null);
		formulaPopUpActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(formulaPopUpActionBtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
		addDetailsButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpSelectBtn = configProvider.getUIFrameworkComponentConfig(
				"FormulaPopUpSearchSearchViewAddButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		formulaPopUpSelectBtn.setAuthorizationIncluded(true);
		formulaPopUpSelectBtn.setComponentName("SELECT");
		componentList.add(formulaPopUpSelectBtn);

		List<GtnUIFrameWorkActionConfig> formulaPopUpSelectActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig formulaPopUpSelectAction = new GtnUIFrameWorkActionConfig();
		formulaPopUpSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.FORMULA_POPUP_SEARCH_RESULT_TABLE);
		actionParameter.add("rebatePlanCalculationsTierFormula");
		actionParameter.add(Arrays.asList(GtnFrameworkCommonConstants.FORMULA_NAME));
		actionParameter.add(Arrays.asList("rebatePlanCalculationsTierFormula"));
		formulaPopUpSelectAction.setActionParameterList(actionParameter);
		formulaPopUpSelectActionConfigList.add(formulaPopUpSelectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW);
		formulaPopUpSelectActionConfigList.add(closeAction);

		formulaPopUpSelectBtn.setGtnUIFrameWorkActionConfigList(formulaPopUpSelectActionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpCloseBtn = configProvider.getUIFrameworkComponentConfig(
				"FormulaPopUpSearchSearchViewEditButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		formulaPopUpCloseBtn.setAuthorizationIncluded(true);
		formulaPopUpCloseBtn.setComponentName("CLOSE");
		componentList.add(formulaPopUpCloseBtn);

		List<GtnUIFrameWorkActionConfig> formulaPopUpCloseActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.FORMULA_POP_UP_SEARCH_SEARCH_VIEW);
		formulaPopUpCloseActionConfigList.add(closeAction);

		formulaPopUpCloseBtn.setGtnUIFrameWorkActionConfigList(formulaPopUpCloseActionConfigList);

	}

	private void addDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig formulaPopUpDetailsBtn = configProvider.getUIFrameworkComponentConfig(
				"cDRAddViewButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		formulaPopUpDetailsBtn.setAuthorizationIncluded(true);
		formulaPopUpDetailsBtn.setComponentName("DETAILS");
		componentList.add(formulaPopUpDetailsBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionforLastOperatorCheck.addActionParameter(
				"com.stpl.gtn.gtn2o.ui.module.rebateplanconfig.logic.GtnUIFrameWorkAddDataTableAction");
		actionConfigList.add(customActionforLastOperatorCheck);

		formulaPopUpDetailsBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
