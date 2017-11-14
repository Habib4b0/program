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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkRPPopUpSearchConfig {

	private final GtnFrameworkComponentConfigProvider rpPopUpConfigProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = rpPopUpConfigProvider.getViewConfig(
				GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_VIEW,
				GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_VIEW, false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRPPopUpSearchCriteriaPanel(componentList);
		addRPPopUpButtonLayout(componentList);
		addRPPopUpResultPanel(componentList);
		addRPPopUpActionButtonLayout(componentList);

	}

	private void addRPPopUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanelConfig = rpPopUpConfigProvider
				.getPanelConfig("searchCriteriaPanel", false, null);
		searchCriteriaPanelConfig.setComponentName("Search Criteria");
		searchCriteriaPanelConfig.setAuthorizationIncluded(true);
		componentList.add(searchCriteriaPanelConfig);

		addFieldLayout(componentList);
	}

	private void addRPPopUpResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupResultConfig = rpPopUpConfigProvider.getPanelConfig("resultPanel", false,
				null);
		rpPopupResultConfig.setComponentName(GtnFrameworkStringConstants.RESULTS);
		rpPopupResultConfig.setAuthorizationIncluded(true);
		rpPopupResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(rpPopupResultConfig);

		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupResultTableLayout = rpPopUpConfigProvider
				.getHorizontalLayoutConfig(GtnFrameworkStringConstants.RESULTS_LAYOUT, true, "resultPanel");
		rpPopupResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(rpPopupResultTableLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupsearchCriteriaLayoutConfig = rpPopUpConfigProvider.getGtnCssLayoutConfig(
				GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel",
				GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		rpPopupsearchCriteriaLayoutConfig.setComponentStyle(styleList);
		componentList.add(rpPopupsearchCriteriaLayoutConfig);

		addFieldComponent(componentList);
	}

	private void addRPPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupSearchCriteriaButtonLayout = rpPopUpConfigProvider
				.getCssLayoutConfig(GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT, false, null);
		rpPopupSearchCriteriaButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		rpPopupSearchCriteriaButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rpPopupSearchCriteriaButtonLayout.setSpacing(true);
		rpPopupSearchCriteriaButtonLayout.setMargin(true);
		componentList.add(rpPopupSearchCriteriaButtonLayout);

		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addRebatePlanId(componentList);
		addRebatePlanNo(componentList);
		addRebatePlanName(componentList);
		addRebatePlanStatus(componentList);
		addRebatePlanType(componentList);
	}

	private void addRebatePlanId(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanIdLayout = rpPopUpConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanIdLayout", true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rebatePlanIdLayout);

		GtnUIFrameworkComponentConfig rebatePlanIdLayoutConfig = rpPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.REBATE_PLAN_ID, true, rebatePlanIdLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanIdLayoutConfig.setAuthorizationIncluded(true);
		rebatePlanIdLayoutConfig.setComponentName("Rebate Plan ID");
		componentList.add(rebatePlanIdLayoutConfig);

	}

	private void addRebatePlanNo(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanNoLayout = rpPopUpConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanNoLayout", true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rebatePlanNoLayout);

		GtnUIFrameworkComponentConfig rebatePlanNoLayoutConfig = rpPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.SECONDARY_REBATE_PLAN_NO, true, rebatePlanNoLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNoLayoutConfig.setAuthorizationIncluded(true);
		rebatePlanNoLayoutConfig.setComponentName("Rebate Plan No");
		componentList.add(rebatePlanNoLayoutConfig);
	}

	private void addRebatePlanName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanNameLayout = rpPopUpConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanNameLayout", true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rebatePlanNameLayout);

		GtnUIFrameworkComponentConfig rebatePlanNameLayoutConfig = rpPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.SECONDARY_REBATE_PLAN_NAME, true, rebatePlanNameLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNameLayoutConfig.setAuthorizationIncluded(true);
		rebatePlanNameLayoutConfig.setComponentName("Rebate Plan Name");
		componentList.add(rebatePlanNameLayoutConfig);

	}

	private void addRebatePlanStatus(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanStatuslayout = rpPopUpConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanStatuslayout", true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rebatePlanStatuslayout);

		GtnUIFrameworkComponentConfig rebatePlanStatusConfig = rpPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.REBATE_PLAN_STATUS, true, rebatePlanStatuslayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		rebatePlanStatusConfig.setAuthorizationIncluded(true);
		rebatePlanStatusConfig.setComponentName("Rebate Plan Status");
		componentList.add(rebatePlanStatusConfig);

		GtnUIFrameworkComboBoxConfig rpStatusComboConfig = rpPopUpConfigProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rebatePlanStatusConfig.setGtnComboboxConfig(rpStatusComboConfig);
	}

	private void addRebatePlanType(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanTypelayout = rpPopUpConfigProvider.getHorizontalLayoutConfig(
				"RebatePlanTypelayout", true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rebatePlanTypelayout);

		GtnUIFrameworkComponentConfig rebatePlanTypeConfig = rpPopUpConfigProvider.getUIFrameworkComponentConfig(
				"rpPopUpRebatePlanType", true, rebatePlanTypelayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		rebatePlanTypeConfig.setAuthorizationIncluded(true);
		rebatePlanTypeConfig.setComponentName("Rebate Plan Type");
		componentList.add(rebatePlanTypeConfig);

		GtnUIFrameworkComboBoxConfig rpRebatePlanTypeComboConfig = rpPopUpConfigProvider
				.getComboBoxConfig("COMPANY_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rebatePlanTypeConfig.setGtnComboboxConfig(rpRebatePlanTypeComboConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupSearchBtnLayout = rpPopUpConfigProvider.getHorizontalLayoutConfig(
				"rebatePlan01Layout", true, GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(rpPopupSearchBtnLayout);

		GtnUIFrameworkComponentConfig rpPopupSearchButtonConfig = rpPopUpConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlan01", true, rpPopupSearchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		rpPopupSearchButtonConfig.setComponentName("SEARCH");
		rpPopupSearchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(rpPopupSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> rpsearchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig rpSearchvalidationActionConfig = rpPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		rpSearchvalidationActionConfig
				.setFieldValues(Arrays.asList(new String[] { GtnFrameworkStringConstants.SECONDARY_REBATE_PLAN_NO }));

		rpSearchvalidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> rpSearchValidationonFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig rpSearchAlertActionConfig = rpPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> rpSearchAlertParams = new ArrayList<>();
		rpSearchAlertParams.add("No Search Criteria ");
		rpSearchAlertParams.add("Please enter Search Criteria");

		rpSearchAlertActionConfig.setActionParameterList(rpSearchAlertParams);

		rpSearchValidationonFailure.add(rpSearchAlertActionConfig);
		rpSearchvalidationActionConfig.addActionParameter(rpSearchValidationonFailure);
		rpsearchActionConfigList.add(rpSearchvalidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = rpPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkStringConstants.RP_POPUP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(new String[] { GtnFrameworkStringConstants.REBATE_PLAN_ID }));

		rpsearchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig searchNotificationActionConfig = rpPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		List<Object> notificationParams = new ArrayList<>();
		notificationParams.add(" Search Completed ");
		notificationParams.add("");

		searchNotificationActionConfig.setActionParameterList(notificationParams);
		rpsearchActionConfigList.add(searchNotificationActionConfig);
		rpPopupSearchButtonConfig.setGtnUIFrameWorkActionConfigList(rpsearchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpResetPopupResetBtnLayout = rpPopUpConfigProvider.getHorizontalLayoutConfig(
				"complianceAndDeductionRulesReset01Layout", true, GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(rpResetPopupResetBtnLayout);

		GtnUIFrameworkComponentConfig rpResetPopupResetButtonConfig = rpPopUpConfigProvider
				.getUIFrameworkComponentConfig("complianceAndDeductionRulesReset01", true,
						rpResetPopupResetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		rpResetPopupResetButtonConfig.setComponentName("RESET");
		rpResetPopupResetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(rpResetPopupResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> rpResetactionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = rpPopUpConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> resetParams = new ArrayList<>();
		resetParams.add("Reset Confirmation");
		resetParams.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put("ruleName", "");
		resetMap.put("ruleNo", "");
		resetMap.put("ruleType", null);
		resetMap.put("ruleCategory", null);
		resetParams.add(resetMap);

		resetActionConfig.setActionParameterList(resetParams);
		rpResetactionConfigList.add(resetActionConfig);
		rpResetPopupResetButtonConfig.setGtnUIFrameWorkActionConfigList(rpResetactionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupSearchResultConfig = rpPopUpConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.RP_POPUP_SEARCH_RESULT_TABLE, true,
				GtnFrameworkStringConstants.RESULTS_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		rpPopupSearchResultConfig.setAuthorizationIncluded(true);
		rpPopupSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		GtnUIFrameworkValidationConfig rpSearchTableValidationConfig = new GtnUIFrameworkValidationConfig();
		rpSearchTableValidationConfig.setMinSize(1);
		rpPopupSearchResultConfig.setGtnUIFrameworkValidationConfig(rpSearchTableValidationConfig);

		componentList.add(rpPopupSearchResultConfig);

		GtnUIFrameworkPagedTableConfig rpPopupSearchResultsTableConfig = new GtnUIFrameworkPagedTableConfig();
		rpPopupSearchResultsTableConfig.setEditable(false);
		rpPopupSearchResultsTableConfig.setFilterBar(true);
		rpPopupSearchResultsTableConfig.setSelectable(true);
		rpPopupSearchResultsTableConfig.setItemPerPage(10);
		rpPopupSearchResultsTableConfig.setPageLength(10);
		rpPopupSearchResultsTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING });
		rpPopupSearchResultsTableConfig.setTableVisibleHeader(new String[] { "Rebate Plan ID", "Rebate Plan No",
				"Rebate Plan Name", "Rebate Plan Status", "Rebate Plan Type", "Rebate Plan Based On",
				"Rebate Structure", "Range Based On", "Net Sales Formula", "Net Sales Rule", "Creation Date",
				"Created By", "Modified Date ", "Modified By" });
		rpPopupSearchResultsTableConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkStringConstants.REBATE_PLAN_ID, GtnFrameworkStringConstants.SECONDARY_REBATE_PLAN_NO,
				GtnFrameworkStringConstants.SECONDARY_REBATE_PLAN_NAME, GtnFrameworkStringConstants.REBATE_PLAN_STATUS,
				"rpPopUpRebatePlanType", "rebatePlanBasedOn", "rebateStructure", "rangeBasedOn", "netSalesFormula",
				"netSalesRule", "creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		rpPopupSearchResultsTableConfig.setExtraColumn(new Object[] { "systemId" });
		rpPopupSearchResultsTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		rpPopupSearchResultsTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		rpPopupSearchResultsTableConfig.setModuleName("companyMaster");
		rpPopupSearchResultsTableConfig.setQueryName("rebateplanSearch");
		rpPopupSearchResultsTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.REBATE_PLAN);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId("ruleType");
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("ruleTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig ruleTypeConfig = rpPopUpConfigProvider.getComboBoxConfig("RULE_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		customFilterComponentConfig.setGtnComboboxConfig(ruleTypeConfig);

		customFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig ruleCategoryCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		ruleCategoryCustomFilterConfig.setPropertId("ruleCategory");
		ruleCategoryCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig ruleCategoryCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		ruleCategoryCustomFilterComponentConfig.setComponentId("ruleCategorycustomFilterComboBox");
		ruleCategoryCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);

		GtnUIFrameworkComboBoxConfig ruleCategoryConfig = rpPopUpConfigProvider.getComboBoxConfig("RULE_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ruleCategoryCustomFilterComponentConfig.setGtnComboboxConfig(ruleCategoryConfig);
		ruleCategoryCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		ruleCategoryCustomFilterConfig.setGtnComponentConfig(ruleCategoryCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig createedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		createedByCustomFilterConfig.setPropertId("createdBy");
		createedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig createdByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		createdByCustomFilterComponentConfig.setComponentId("createdBycustomFilterComboBox");
		createdByCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig createdByConfig = rpPopUpConfigProvider.getComboBoxConfig("userIdName",
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
		GtnUIFrameworkComboBoxConfig modifiedByConfig = rpPopUpConfigProvider.getComboBoxConfig("userIdName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		modifiedByCustomFilterComponentConfig.setGtnComboboxConfig(modifiedByConfig);
		modifiedByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		modifiedByCustomFilterConfig.setGtnComponentConfig(modifiedByCustomFilterComponentConfig);

		customFilterConfigMap.put(modifiedByCustomFilterConfig.getPropertId(), modifiedByCustomFilterConfig);

		customFilterConfigMap.put(createedByCustomFilterConfig.getPropertId(), createedByCustomFilterConfig);
		customFilterConfigMap.put(ruleCategoryCustomFilterConfig.getPropertId(), ruleCategoryCustomFilterConfig);

		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		rpPopupSearchResultsTableConfig.setCustomFilterConfigMap(customFilterConfigMap);
		rpPopupSearchResultConfig.setGtnPagedTableConfig(rpPopupSearchResultsTableConfig);
	}

	private void addRPPopUpActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupActionBtnLayout = rpPopUpConfigProvider
				.getCssLayoutConfig(GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT, false, null);
		rpPopupActionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rpPopupActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		rpPopupActionBtnLayout.setSpacing(true);
		rpPopupActionBtnLayout.setMargin(true);
		componentList.add(rpPopupActionBtnLayout);

		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupSelectBtnLayout = rpPopUpConfigProvider.getHorizontalLayoutConfig(
				"netSalesFormulaPopUpViewAddButtonLayout", true, GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(rpPopupSelectBtnLayout);

		GtnUIFrameworkComponentConfig rpPopupSelectButtonConfig = rpPopUpConfigProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpViewAddButton", true, rpPopupSelectBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		rpPopupSelectButtonConfig.setAuthorizationIncluded(true);
		rpPopupSelectButtonConfig.setComponentName("SELECT");
		componentList.add(rpPopupSelectButtonConfig);

		List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectActionConfig = new GtnUIFrameWorkActionConfig();
		selectActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> selectActionParameter = new ArrayList<>();
		selectActionParameter.add(GtnFrameworkStringConstants.RP_POPUP_SEARCH_RESULT_TABLE);
		selectActionParameter.add("rebatePlanCalculationsSecondaryRebatePlan");
		selectActionParameter.add(Arrays.asList(new String[] { GtnFrameworkStringConstants.SECONDARY_REBATE_PLAN_NAME,
				GtnFrameworkStringConstants.SECONDARY_REBATE_PLAN_NO }));
		selectActionParameter.add(Arrays.asList(new String[] { "rebatePlanCalculationsSecondaryRebatePlan",
				"rebatePlanCalculationsSecondaryRebatePlanNo" }));

		selectActionConfig.setActionParameterList(selectActionParameter);
		selectActionConfigList.add(selectActionConfig);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_VIEW }));
		selectActionConfigList.add(closeAction);

		rpPopupSelectButtonConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupCloseBtnLayout = rpPopUpConfigProvider.getHorizontalLayoutConfig(
				"netSalesFormulaPopUpViewEditButtonLayout", true, GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(rpPopupCloseBtnLayout);

		GtnUIFrameworkComponentConfig rpPopupCloseButtonConfig = rpPopUpConfigProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpViewEditButton", true, rpPopupCloseBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		rpPopupCloseButtonConfig.setAuthorizationIncluded(true);
		rpPopupCloseButtonConfig.setComponentName("CLOSE");
		componentList.add(rpPopupCloseButtonConfig);

		List<GtnUIFrameWorkActionConfig> closeActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeActionConfig = new GtnUIFrameWorkActionConfig();
		closeActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_VIEW }));
		closeActionConfigList.add(closeActionConfig);

		rpPopupCloseButtonConfig.setGtnUIFrameWorkActionConfigList(closeActionConfigList);

	}

}
