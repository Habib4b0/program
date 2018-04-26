package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

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
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkResetYesButtonAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.validation.GtnFrameworkRebatePlanEditValidationAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkRPLandingScreenConfig {

	private final GtnFrameworkComponentConfigProvider rpLandingScreenConfigProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig rpLandingScreenView = rpLandingScreenConfigProvider
				.getViewConfig("rebatePlanSearchView", "rebatePlanSearchView", true);
		addComponentList(rpLandingScreenView);
		return rpLandingScreenView;
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

		GtnUIFrameworkComponentConfig mainSearchPanelConfig = rpLandingScreenConfigProvider
				.getPanelConfig("searchCriteriaPanel", false, null);
		mainSearchPanelConfig.setComponentName("Search Criteria");
		mainSearchPanelConfig.setAuthorizationIncluded(true);
		componentList.add(mainSearchPanelConfig);
		addFieldLayout(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultPanelConfig = rpLandingScreenConfigProvider.getPanelConfig("resultPanel",
				false, null);
		resultPanelConfig.setComponentName("Results");
		resultPanelConfig.setAuthorizationIncluded(true);
		componentList.add(resultPanelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultTableLayout = rpLandingScreenConfigProvider
				.getHorizontalLayoutConfig(GtnFrameworkStringConstants.RESULTS_LAYOUT, true, "resultPanel");
		searchResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(searchResultTableLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig mainSearchCriteriaLayoutConfig = rpLandingScreenConfigProvider
				.getGtnCssLayoutConfig(GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel",
						GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		mainSearchCriteriaLayoutConfig.setComponentStyle(styleList);
		componentList.add(mainSearchCriteriaLayoutConfig);
		addFieldComponent(componentList);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig mainSearchCriteriaButtonLayout = rpLandingScreenConfigProvider
				.getCssLayoutConfig(GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT, false, null);
		mainSearchCriteriaButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		mainSearchCriteriaButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		mainSearchCriteriaButtonLayout.setSpacing(true);
		mainSearchCriteriaButtonLayout.setMargin(true);
		componentList.add(mainSearchCriteriaButtonLayout);

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

		GtnUIFrameworkComponentConfig rebatePlanIdConfigLayout = rpLandingScreenConfigProvider
				.getHorizontalLayoutConfig("rebatePlanIdLayout", true,
						GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rebatePlanIdConfigLayout);

		GtnUIFrameworkComponentConfig rebatePlanIdConfig = rpLandingScreenConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.REBATE_PLAN_ID, true, rebatePlanIdConfigLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanIdConfig.setAuthorizationIncluded(true);
		rebatePlanIdConfig.setComponentName("Rebate Plan ID");
		componentList.add(rebatePlanIdConfig);

		GtnUIFrameworkValidationConfig rebatePlanIdValidationConfig = new GtnUIFrameworkValidationConfig();
		rebatePlanIdValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanIdConfig.setGtnUIFrameworkValidationConfig(rebatePlanIdValidationConfig);

	}

	private void addRebatePlanNo(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanNoConfigLayout = rpLandingScreenConfigProvider
				.getHorizontalLayoutConfig("rebatePlanNoLayout", true,
						GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rebatePlanNoConfigLayout);

		GtnUIFrameworkComponentConfig rebatePlanNoConfig = rpLandingScreenConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.REBATE_PLAN_NO, true, rebatePlanNoConfigLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNoConfig.setAuthorizationIncluded(true);
		rebatePlanNoConfig.setComponentName("Rebate Plan No");
		componentList.add(rebatePlanNoConfig);

		GtnUIFrameworkValidationConfig rpNoValidationConfig = new GtnUIFrameworkValidationConfig();
		rpNoValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanNoConfig.setGtnUIFrameworkValidationConfig(rpNoValidationConfig);

	}

	private void addRebatePlanName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanNameConfigLayout = rpLandingScreenConfigProvider
				.getHorizontalLayoutConfig("rebatePlanNameLayout", true,
						GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rebatePlanNameConfigLayout);

		GtnUIFrameworkComponentConfig rebatePlanNameConfig = rpLandingScreenConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkStringConstants.REBATE_PLAN_NAME, true,
						rebatePlanNameConfigLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNameConfig.setComponentName("Rebate Plan Name");
		rebatePlanNameConfig.setAuthorizationIncluded(true);
		componentList.add(rebatePlanNameConfig);

		GtnUIFrameworkValidationConfig rpNameValidationConfig = new GtnUIFrameworkValidationConfig();
		rpNameValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanNameConfig.setGtnUIFrameworkValidationConfig(rpNameValidationConfig);

	}

	private void addRebatePlanStatus(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpStatusLayoutConfig = rpLandingScreenConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanStatuslayout", true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rpStatusLayoutConfig);

		GtnUIFrameworkComponentConfig rpStatusComponentConfig = rpLandingScreenConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkStringConstants.REBATE_PLAN_STATUS, true,
						rpStatusLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		rpStatusComponentConfig.setComponentName("Rebate Plan Status");
		rpStatusComponentConfig.setAuthorizationIncluded(true);
		componentList.add(rpStatusComponentConfig);

		GtnUIFrameworkComboBoxConfig rpStatusComboConfig = rpLandingScreenConfigProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rpStatusComponentConfig.setGtnComboboxConfig(rpStatusComboConfig);

		GtnUIFrameworkValidationConfig rpStatusValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();

		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		rpStatusValidationConfig.setConditionList(conditions);

		rpStatusComponentConfig.setGtnUIFrameworkValidationConfig(rpStatusValidationConfig);

	}

	private void addRebatePlanType(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpTypeLayoutConfig = rpLandingScreenConfigProvider.getHorizontalLayoutConfig(
				"RebatePlanTypelayout", true, GtnFrameworkStringConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(rpTypeLayoutConfig);

		GtnUIFrameworkComponentConfig rpTypeComponentConfig = rpLandingScreenConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkStringConstants.REBATE_PLAN_TYPE, true,
						rpTypeLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		rpTypeComponentConfig.setComponentName("Rebate Plan Type");
		rpTypeComponentConfig.setAuthorizationIncluded(true);
		componentList.add(rpTypeComponentConfig);

		GtnUIFrameworkComboBoxConfig rpTypeComboConfig = rpLandingScreenConfigProvider
				.getComboBoxConfig("REBATE_PLAN_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rpTypeComponentConfig.setGtnComboboxConfig(rpTypeComboConfig);

		GtnUIFrameworkValidationConfig rpTypeValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();

		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		rpTypeValidationConfig.setConditionList(conditions);

		rpTypeComponentConfig.setGtnUIFrameworkValidationConfig(rpTypeValidationConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig mainSearchBtnLayout = rpLandingScreenConfigProvider.getHorizontalLayoutConfig(
				"rebatePlan01Layout", true, GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(mainSearchBtnLayout);

		GtnUIFrameworkComponentConfig mainSearchButtonConfig = rpLandingScreenConfigProvider
				.getUIFrameworkComponentConfig("rebatePlan01", true, mainSearchBtnLayout.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		mainSearchButtonConfig.setAuthorizationIncluded(true);
		mainSearchButtonConfig.setComponentName("SEARCH");
		componentList.add(mainSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> mainSearchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkStringConstants.REBATE_PLAN_ID,
				GtnFrameworkStringConstants.REBATE_PLAN_NO, GtnFrameworkStringConstants.REBATE_PLAN_NAME,
				GtnFrameworkStringConstants.REBATE_PLAN_STATUS, GtnFrameworkStringConstants.REBATE_PLAN_TYPE));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParams);
		onFailure.add(alertActionConfig);

		validationActionConfig.addActionParameter(onFailure);
		mainSearchActionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig resultTableActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);

		resultTableActionConfig.setActionParameterList(actionParams);
		resultTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkStringConstants.REBATE_PLAN_ID,
				GtnFrameworkStringConstants.REBATE_PLAN_NO, GtnFrameworkStringConstants.REBATE_PLAN_NAME,
				GtnFrameworkStringConstants.REBATE_PLAN_STATUS, GtnFrameworkStringConstants.REBATE_PLAN_TYPE));

		mainSearchActionConfigList.add(resultTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);
		mainSearchActionConfigList.add(notificationActionConfig);
		mainSearchButtonConfig.setGtnUIFrameWorkActionConfigList(mainSearchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig mainResetBtnLayout = rpLandingScreenConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanReset01Layout", true, GtnFrameworkStringConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(mainResetBtnLayout);

		GtnUIFrameworkComponentConfig mainResetButtonConfig = rpLandingScreenConfigProvider
				.getUIFrameworkComponentConfig("rebatePlanReset01", true, mainResetBtnLayout.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		mainResetButtonConfig.setAuthorizationIncluded(true);
		mainResetButtonConfig.setComponentName("RESET");
		componentList.add(mainResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		List<Object> resetParams = new ArrayList<>();
		resetParams.add("Reset Confirmation");
		resetParams.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_ID, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_NO, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_NAME, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_STATUS, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_TYPE, null);
		resetParams.add(resetMap);

		resetActionConfig.setActionParameterList(resetParams);
		resetActionConfigList.add(resetActionConfig);
		mainResetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig resultTableConfig = rpLandingScreenConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE, true,
				GtnFrameworkStringConstants.RESULTS_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		resultTableConfig.setAuthorizationIncluded(true);
		resultTableConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultTableConfig);

		GtnUIFrameworkPagedTableConfig searchResultsTableConfig = new GtnUIFrameworkPagedTableConfig();
		searchResultsTableConfig.setEditable(false);
		searchResultsTableConfig.setFilterBar(true);
		searchResultsTableConfig.setSelectable(true);
		searchResultsTableConfig.setItemPerPage(10);
		searchResultsTableConfig.setPageLength(10);
		searchResultsTableConfig.setSinkItemPerPageWithPageLength(false);

		searchResultsTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResultsTableConfig.setTableVisibleHeader(new String[] { "Rebate Plan ID", "Rebate Plan No",
				"Rebate Plan Name", "Rebate Plan Status", "Rebate Plan Type", "Rebate Plan Based On",
				"Rebate Structure", "Range Based On", "Net Sales Formula", "Net Sales Rule", "Creation Date",
				"Created By", "Modified Date ", "Modified By" });
		searchResultsTableConfig.setTableColumnMappingId(new Object[] { GtnFrameworkStringConstants.REBATE_PLAN_ID,
				GtnFrameworkStringConstants.REBATE_PLAN_NO, GtnFrameworkStringConstants.REBATE_PLAN_NAME,
				GtnFrameworkStringConstants.REBATE_PLAN_STATUS, GtnFrameworkStringConstants.REBATE_PLAN_TYPE,
				"rebatePlanBasedOn", "rebateStructure", "rangeBasedOn", "netSalesFormula", "netSalesRule",
				"creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		searchResultsTableConfig.setExtraColumn(new Object[] { "systemId", "recordLockStatus" });
		searchResultsTableConfig.setExtraColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResultsTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResultsTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResultsTableConfig.setModuleName("companyMaster");
		searchResultsTableConfig.setQueryName("rebateplanSearch");
		searchResultsTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.REBATE_PLAN);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> resultTableFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig rpStatuscustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		rpStatuscustomFilterConfig.setPropertId(GtnFrameworkStringConstants.REBATE_PLAN_STATUS);
		rpStatuscustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig rpStatusComponentConfig = new GtnUIFrameworkComponentConfig();
		rpStatusComponentConfig.setComponentId("rpStatusCustomomFilterComboBox");
		rpStatusComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig ruleTypeConfig = rpLandingScreenConfigProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rpStatusComponentConfig.setGtnComboboxConfig(ruleTypeConfig);

		rpStatusComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		rpStatuscustomFilterConfig.setGtnComponentConfig(rpStatusComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig rebatePlanTypeCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		rebatePlanTypeCustomFilterConfig.setPropertId(GtnFrameworkStringConstants.REBATE_PLAN_TYPE);
		rebatePlanTypeCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig ruleCategoryCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		ruleCategoryCustomFilterComponentConfig.setComponentId("rebatePlanTypeCustomFilterComboBox");
		ruleCategoryCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);

		GtnUIFrameworkComboBoxConfig ruleCategoryConfig = rpLandingScreenConfigProvider
				.getComboBoxConfig("REBATE_PLAN_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ruleCategoryCustomFilterComponentConfig.setGtnComboboxConfig(ruleCategoryConfig);
		ruleCategoryCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		rebatePlanTypeCustomFilterConfig.setGtnComponentConfig(ruleCategoryCustomFilterComponentConfig);
		GtnUIFrameworkPagedTableCustomFilterConfig rPBasedOncustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		rPBasedOncustomFilterConfig.setPropertId("rebatePlanBasedOn");
		rPBasedOncustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig rebateBasedOncustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		rebateBasedOncustomFilterComponentConfig.setComponentId("rebatePlanBasedOncustomFilterComboBox");
		rebateBasedOncustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig rpBasedOnConfig = rpLandingScreenConfigProvider
				.getComboBoxConfig("REBATE_BASED_ON", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rebateBasedOncustomFilterComponentConfig.setGtnComboboxConfig(rpBasedOnConfig);

		rebateBasedOncustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		rPBasedOncustomFilterConfig.setGtnComponentConfig(rebateBasedOncustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig rebateStructureCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		rebateStructureCustomFilterConfig.setPropertId("rebateStructure");
		rebateStructureCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig rebateStructureCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		rebateStructureCustomFilterComponentConfig.setComponentId("rebateStructurecustomFilterComboBox");
		rebateStructureCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);

		GtnUIFrameworkComboBoxConfig rebateStructureConfig = rpLandingScreenConfigProvider
				.getComboBoxConfig("REBATE_STRUCTURE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateStructureCustomFilterComponentConfig.setGtnComboboxConfig(rebateStructureConfig);
		rebateStructureCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		rebateStructureCustomFilterConfig.setGtnComponentConfig(rebateStructureCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig rangeBasedOnCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		rangeBasedOnCustomFilterConfig.setPropertId("rangeBasedOn");
		rangeBasedOnCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig rangeBasedOnCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		rangeBasedOnCustomFilterComponentConfig.setComponentId("rangeBasedOncustomFilterComboBox");
		rangeBasedOnCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);

		GtnUIFrameworkComboBoxConfig rangeBasedOnConfig = rpLandingScreenConfigProvider
				.getComboBoxConfig("REBATE_RANGE_BASED_ON", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rangeBasedOnCustomFilterComponentConfig.setGtnComboboxConfig(rangeBasedOnConfig);
		rangeBasedOnCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		rangeBasedOnCustomFilterConfig.setGtnComponentConfig(rangeBasedOnCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig createedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		createedByCustomFilterConfig.setPropertId("createdBy");
		createedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig createdByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		createdByCustomFilterComponentConfig.setComponentId("createdBycustomFilterComboBox");
		createdByCustomFilterComponentConfig.setComponentName(GtnFrameworkStringConstants.CUSTOM_FILTER_COMBOBOX);
		GtnUIFrameworkComboBoxConfig createdByConfig = rpLandingScreenConfigProvider.getComboBoxConfig("userIdName",
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
		GtnUIFrameworkComboBoxConfig modifiedByConfig = rpLandingScreenConfigProvider.getComboBoxConfig("userIdName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		modifiedByCustomFilterComponentConfig.setGtnComboboxConfig(modifiedByConfig);
		modifiedByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		modifiedByCustomFilterConfig.setGtnComponentConfig(modifiedByCustomFilterComponentConfig);

		resultTableFilterConfigMap.put(modifiedByCustomFilterConfig.getPropertId(), modifiedByCustomFilterConfig);

		resultTableFilterConfigMap.put(createedByCustomFilterConfig.getPropertId(), createedByCustomFilterConfig);

		resultTableFilterConfigMap.put(rangeBasedOnCustomFilterConfig.getPropertId(), rangeBasedOnCustomFilterConfig);
		resultTableFilterConfigMap.put(rPBasedOncustomFilterConfig.getPropertId(), rPBasedOncustomFilterConfig);
		resultTableFilterConfigMap.put(rebateStructureCustomFilterConfig.getPropertId(),
				rebateStructureCustomFilterConfig);
		resultTableFilterConfigMap.put(rebatePlanTypeCustomFilterConfig.getPropertId(),
				rebatePlanTypeCustomFilterConfig);
		resultTableFilterConfigMap.put(rpStatuscustomFilterConfig.getPropertId(), rpStatuscustomFilterConfig);

		searchResultsTableConfig.setCustomFilterConfigMap(resultTableFilterConfigMap);
		searchResultsTableConfig.setDoubleClickEnable(true);

		List<GtnUIFrameWorkActionConfig> resultTableActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		List<Object> editArrayList = new ArrayList<>();
		editArrayList.add(GtnUIFrameworkModeType.EDIT);
		setModeActionConfig.setActionParameterList(editArrayList);
		resultTableActionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		List<Object> navigatorArrayList = new ArrayList<>();
		navigatorArrayList.add(GtnFrameworkStringConstants.REBATE_PLAN_ADD_VIEW);
		navigationActionConfig.setActionParameterList(navigatorArrayList);

		resultTableActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		
		enableAction.setActionParameterList(new ArrayList<>(Arrays.asList(GtnFrameworkStringConstants.getAddCopyEnableFields())));
		resultTableActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.setActionParameterList(new ArrayList<>(Arrays.asList(GtnFrameworkStringConstants.getPagedTableDisableFields())));
		resultTableActionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.TRUE);
		visibleParameters.add(Arrays.asList(GtnFrameworkStringConstants.getVisibleFields()));
		visibleAction.setActionParameterList(new ArrayList<>(visibleParameters));
		resultTableActionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.addActionParameter(getResetMap());
		resultTableActionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkResetYesButtonAction.class.getName());
		parameters.add(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(Boolean.TRUE);
		parameters.add(14);
		editActionConfig.setActionParameterList(parameters);
		resultTableActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = new GtnUIFrameWorkActionConfig();
		changeCaptionActionConfig.setActionType(GtnUIFrameworkActionType.CHANGE_CAPTION);
		changeCaptionActionConfig
				.addActionParameter(Arrays.asList(GtnFrameworkStringConstants.REBATE_ADD_VIEW_SAVE_BUTTON));
		changeCaptionActionConfig.addActionParameter(Arrays.asList(GtnFrameworkStringConstants.UPDATE));
		resultTableActionConfigList.add(changeCaptionActionConfig);

		searchResultsTableConfig.setGtnUIFrameWorkActionConfigList(resultTableActionConfigList);

		resultTableConfig.setGtnPagedTableConfig(searchResultsTableConfig);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpLandingScreenActionBtnLayout = rpLandingScreenConfigProvider
				.getCssLayoutConfig(GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT, false, null);
		rpLandingScreenActionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rpLandingScreenActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		rpLandingScreenActionBtnLayout.setSpacing(true);
		rpLandingScreenActionBtnLayout.setMargin(true);
		componentList.add(rpLandingScreenActionBtnLayout);

		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
		addViewButtonComponent(componentList);
		addCopyButtonComponent(componentList);
		addTableResetButtonComponent(componentList);
		addExcelButton(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpLandingScreenAddBtnLayout = rpLandingScreenConfigProvider
				.getHorizontalLayoutConfig("rebatePlanAddButtonlayout", true,
						GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(rpLandingScreenAddBtnLayout);

		GtnUIFrameworkComponentConfig addButtonConfig = rpLandingScreenConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanAddButton", true, rpLandingScreenAddBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setAuthorizationIncluded(true);
		addButtonConfig.setComponentName("ADD");
		componentList.add(addButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.ADD }));

		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.REBATE_PLAN_ADD_VIEW }));

		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableAction.setActionParameterList(Arrays.asList(GtnFrameworkStringConstants.getAddCopyEnableFields()));
		actionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.setActionParameterList(Arrays.asList(GtnFrameworkStringConstants.getPagedTableDisableFields()));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.TRUE);
		visibleParameters.add(Arrays.asList(GtnFrameworkStringConstants.getVisibleFields()));
		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.addActionParameter(getResetMap());
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = new GtnUIFrameWorkActionConfig();
		changeCaptionActionConfig.setActionType(GtnUIFrameworkActionType.CHANGE_CAPTION);

		changeCaptionActionConfig
				.addActionParameter(Arrays.asList(GtnFrameworkStringConstants.REBATE_ADD_VIEW_SAVE_BUTTON));
		changeCaptionActionConfig.addActionParameter(Arrays.asList("SAVE"));
		actionConfigList.add(changeCaptionActionConfig);

		GtnUIFrameWorkActionConfig visbleAction = new GtnUIFrameWorkActionConfig();
		visbleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		List<String> visibleList = new ArrayList<>();
		visibleList.add(GtnFrameworkStringConstants.REBATE_ADD_VIEW_DELETE_BUTTON);

		visbleAction.addActionParameter(Boolean.FALSE);
		visbleAction.addActionParameter(visibleList);
		actionConfigList.add(visbleAction);

		addButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpLandingScreenEditBtnLayout = rpLandingScreenConfigProvider
				.getHorizontalLayoutConfig("rebatePlanEditButtonlayout", true,
						GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(rpLandingScreenEditBtnLayout);

		GtnUIFrameworkComponentConfig editButtonConfig = rpLandingScreenConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanEditButton", true, rpLandingScreenEditBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		editButtonConfig.setAuthorizationIncluded(true);
		editButtonConfig.setComponentName("EDIT");
		componentList.add(editButtonConfig);

		List<GtnUIFrameWorkActionConfig> editBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkStringConstants.GTN_RP_VALIDATION_MSG_EDIT_HEADER);
		alertParamsList.add(GtnFrameworkStringConstants.GTN_RP_VALIDATION_MSG_EDIT);

		alertActionConfig.setActionParameterList(alertParamsList);
		editBtnActionConfigList.add(alertActionConfig);
		GtnUIFrameWorkActionConfig setModeActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.EDIT }));

		editBtnActionConfigList.add(setModeActionConfig);
		GtnUIFrameWorkActionConfig editValidationActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> editValparameters = new ArrayList<>();
		editValparameters.add(GtnFrameworkRebatePlanEditValidationAction.class.getName());
		editValparameters.add(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);
		editValidationActionConfig.setActionParameterList(editValparameters);
		editBtnActionConfigList.add(editValidationActionConfig);
		GtnUIFrameWorkActionConfig navigationActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.REBATE_PLAN_ADD_VIEW }));

		editBtnActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableAction.setActionParameterList(Arrays.asList(GtnFrameworkStringConstants.getAddEditEnableFields()));
		editBtnActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.setActionParameterList(Arrays.asList(GtnFrameworkStringConstants.getPagedTableDisableFields()));
		editBtnActionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.TRUE);
		visibleParameters.add(Arrays.asList(GtnFrameworkStringConstants.getVisibleFields()));
		visibleAction.setActionParameterList(visibleParameters);
		editBtnActionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.addActionParameter(getResetMap());
		editBtnActionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkResetYesButtonAction.class.getName());
		parameters.add(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(Boolean.TRUE);
		parameters.add(14);

		editActionConfig.setActionParameterList(parameters);

		editBtnActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = new GtnUIFrameWorkActionConfig();
		changeCaptionActionConfig.setActionType(GtnUIFrameworkActionType.CHANGE_CAPTION);

		changeCaptionActionConfig
				.addActionParameter(Arrays.asList(GtnFrameworkStringConstants.REBATE_ADD_VIEW_SAVE_BUTTON));
		changeCaptionActionConfig.addActionParameter(Arrays.asList(GtnFrameworkStringConstants.UPDATE));
		editBtnActionConfigList.add(changeCaptionActionConfig);

		editButtonConfig.setGtnUIFrameWorkActionConfigList(editBtnActionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpLandingScreenViewBtnLayout = rpLandingScreenConfigProvider
				.getHorizontalLayoutConfig(GtnFrameworkStringConstants.RP_VIEW_BUTTON + "layout", true,
						GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(rpLandingScreenViewBtnLayout);

		GtnUIFrameworkComponentConfig viewButtonConfig = rpLandingScreenConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkStringConstants.RP_VIEW_BUTTON, true, rpLandingScreenViewBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		viewButtonConfig.setAuthorizationIncluded(true);
		viewButtonConfig.setComponentName("VIEW");
		componentList.add(viewButtonConfig);

		List<GtnUIFrameWorkActionConfig> viewActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkStringConstants.GTN_RP_VALIDATION_MSG_VIEW_HEADER);
		alertParamsList.add(GtnFrameworkStringConstants.GTN_RP_VALIDATION_MSG_VIEW);

		alertActionConfig.setActionParameterList(alertParamsList);
		viewActionConfigList.add(alertActionConfig);
		GtnUIFrameWorkActionConfig setModeActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.VIEW }));

		viewActionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.REBATE_PLAN_ADD_VIEW }));

		viewActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.addActionParameter(getResetMap());
		viewActionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig viewActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkResetYesButtonAction.class.getName());
		parameters.add(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(Boolean.TRUE);
		parameters.add(14);

		viewActionConfig.setActionParameterList(parameters);

		viewActionConfigList.add(viewActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = new GtnUIFrameWorkActionConfig();
		changeCaptionActionConfig.setActionType(GtnUIFrameworkActionType.CHANGE_CAPTION);

		changeCaptionActionConfig.addActionParameter(
				Arrays.asList(new String[] { GtnFrameworkStringConstants.REBATE_ADD_VIEW_SAVE_BUTTON }));
		changeCaptionActionConfig
				.addActionParameter(Arrays.asList(new String[] { GtnFrameworkStringConstants.UPDATE }));
		viewActionConfigList.add(changeCaptionActionConfig);

		viewActionConfigList.add(viewActionConfig);
		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.setActionParameterList(Arrays.asList(GtnFrameworkStringConstants.getAddViewDisableFields()));
                viewActionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.FALSE);
		visibleParameters.add(Arrays.asList(GtnFrameworkStringConstants.getVisibleFields()));
		visibleAction.setActionParameterList(visibleParameters);
		viewActionConfigList.add(visibleAction);

		viewButtonConfig.setGtnUIFrameWorkActionConfigList(viewActionConfigList);

	}

	private void addCopyButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpLandingScreenCopyBtnLayout = rpLandingScreenConfigProvider
				.getHorizontalLayoutConfig("rebatePlanDeleteButtonlayout", true,
						GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(rpLandingScreenCopyBtnLayout);

		GtnUIFrameworkComponentConfig copyButtonConfig = rpLandingScreenConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanDeleteButton", true, rpLandingScreenCopyBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		copyButtonConfig.setAuthorizationIncluded(true);
		copyButtonConfig.setComponentName("COPY");
		componentList.add(copyButtonConfig);

		List<GtnUIFrameWorkActionConfig> copyActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkStringConstants.GTN_RP_VALIDATION_MSG_COPY_HEADER);
		alertParamsList.add(GtnFrameworkStringConstants.GTN_RP_VALIDATION_MSG_COPY);

		alertActionConfig.setActionParameterList(alertParamsList);
		copyActionConfigList.add(alertActionConfig);
		GtnUIFrameWorkActionConfig setModeActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.COPY }));

		copyActionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkStringConstants.REBATE_PLAN_ADD_VIEW }));

		copyActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = new GtnUIFrameWorkActionConfig();
		changeCaptionActionConfig.setActionType(GtnUIFrameworkActionType.CHANGE_CAPTION);

		changeCaptionActionConfig
				.addActionParameter(Arrays.asList(GtnFrameworkStringConstants.REBATE_ADD_VIEW_SAVE_BUTTON));
		changeCaptionActionConfig.addActionParameter(Arrays.asList("SAVE"));
		copyActionConfigList.add(changeCaptionActionConfig);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableAction.setActionParameterList(Arrays.asList(GtnFrameworkStringConstants.getAddCopyEnableFields()));
		copyActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.setActionParameterList(Arrays.asList(GtnFrameworkStringConstants.getAddCopyDisableFields()));
		copyActionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.TRUE);
		visibleParameters.add(Arrays.asList(GtnFrameworkStringConstants.getVisibleFields()));
		visibleAction.setActionParameterList(visibleParameters);
		copyActionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.addActionParameter(getResetMap());
		copyActionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkResetYesButtonAction.class.getName());
		parameters.add(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(Boolean.TRUE);
		parameters.add(14);

		editActionConfig.setActionParameterList(parameters);

		copyActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig resetActionConfig = rpLandingScreenConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		List<Object> params = new ArrayList<>();
		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_ID_TOP, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_NAME_TOP, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_NO_TOP, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_INFO_RP_ID, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_INFO_RP_NAME, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_INFO_RP_NO, "");
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		copyActionConfigList.add(resetActionConfig);
		copyButtonConfig.setGtnUIFrameWorkActionConfigList(copyActionConfigList);

	}

	private void addTableResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpLandingScreenResetBtnLayout = rpLandingScreenConfigProvider
				.getHorizontalLayoutConfig("rebatePlanReset02layout", true,
						GtnFrameworkStringConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(rpLandingScreenResetBtnLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = rpLandingScreenConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanReset02", true, rpLandingScreenResetBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("RESET");
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the listview to default/previous values?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE, "");
		params.add(resetMap);
		resetActionConfig.setActionParameterList(params);
		resetActionConfigList.add(resetActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addExcelButton(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpLandingExcelBtnLayout = rpLandingScreenConfigProvider.getHorizontalLayoutConfig(
				"gtnExcelButtonlayout", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(rpLandingExcelBtnLayout);

		GtnUIFrameworkComponentConfig excelButtonComponentConfig = rpLandingScreenConfigProvider
				.getUIFrameworkComponentConfig("resultTableExcelBtn", true, rpLandingExcelBtnLayout.getComponentId(),
						GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonComponentConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkExcelButtonConfig excelButtonConfig = rpLandingScreenConfigProvider.getExcelBtnconfig(
				"REBATEPLAN", true, GtnFrameworkStringConstants.REBATE_PLAN_SEARCH_RESULT_TABLE, false);
		excelButtonConfig.setTitleNeeded(true);
		excelButtonComponentConfig.setGtnUIFrameworkExcelButtonConfig(excelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(excelButtonConfig);
		excelButtonComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
		componentList.add(excelButtonComponentConfig);

	}

	private Map<String, Object> getResetMap() {
		Map<String, Object> resetMap = new HashMap<>(30);
		resetMap.put(GtnFrameworkStringConstants.REBATE_DETAILS_ATTACH_RESULT_TABLE, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_INFORMATION_TYPE, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_REBATE_STATUS, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_REBATE_STRUCTURE, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_RANGE_BASED_ON, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_REBATE_BASED_ON, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_SELF_GROWTH_FROM, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_SELF_GROWTH_TO, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_MARKET_SHARE_FROM, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_MARKET_SHARE_TO, null);
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_ID_TOP, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_NAME_TOP, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_NO_TOP, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_INFO_RP_ID, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_INFO_RP_NAME, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_INFO_RP_NO, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_INFO_FORMULA_TYPE, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_NET_SALES_FORMULA, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_NET_SALES_RULE, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_SELF_GROWTH_INDICATOR, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_SELF_GROWTH_REFERENCE, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_MARKET_SHARE_INDICATOR, "");
		resetMap.put(GtnFrameworkStringConstants.REBATE_PLAN_CALCULATION_MARKET_SHARE_REFERENCE, "");

		return resetMap;

	}

}
