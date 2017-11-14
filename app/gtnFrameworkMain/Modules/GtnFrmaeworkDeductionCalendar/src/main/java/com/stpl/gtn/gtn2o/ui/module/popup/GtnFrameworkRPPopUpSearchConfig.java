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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
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

public class GtnFrameworkRPPopUpSearchConfig {

	private final GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

	public GtnUIFrameworkViewConfig getSearchView() {
		gtnFrameworkConfigurationFactory.setViewId(GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_SEARCH_VIEW);
		gtnFrameworkConfigurationFactory.setViewName(GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_SEARCH_VIEW);
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
		addResultPanel(componentList);
		addActionButtonLayout(componentList);

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
				"Results", false, GtnUIFrameworkComponentType.PANEL);
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
		addRebatePlanId(componentList);
		addRebatePlanNo(componentList);
		addRebatePlanName(componentList);
		addRebatePlanStatus(componentList);
		addRebatePlanType(componentList);
	}

	private void addRebatePlanId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("rebatePlanIdLayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = new GtnUIFrameworkComponentConfig();
		companyIdConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentId(GtnFrameworkCommonConstants.REBATE_PLAN_ID);
		companyIdConfig.setComponentName("Rebate Plan ID");
		companyIdConfig.setParentComponentId("rebatePlanIdLayout");
		companyIdConfig.setAddToParent(true);

		componentList.add(companyIdConfig);
	}

	private void addRebatePlanNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("rebatePlanNoLayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);

		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = new GtnUIFrameworkComponentConfig();
		companyNoConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentId(GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NO);
		companyNoConfig.setComponentName("Rebate Plan NO");
		companyNoConfig.setParentComponentId("rebatePlanNoLayout");
		companyNoConfig.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addRebatePlanName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("rebatePlanNameLayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = new GtnUIFrameworkComponentConfig();
		companyNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setComponentId(GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NAME);
		companyNameConfig.setComponentName("Rebate Plan Name");
		companyNameConfig.setParentComponentId("rebatePlanNameLayout");
		companyNameConfig.setAddToParent(true);

		componentList.add(companyNameConfig);
	}

	private void addRebatePlanStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("rebatePlanStatuslayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentId("rebatePlanStatus");
		companyStatus.setComponentName("Rebate Plan Status");
		companyStatus.setParentComponentId("rebatePlanStatuslayout");
		companyStatus.setAddToParent(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setComboBoxType("STATUS");
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRebatePlanType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("RebatePlanTypelayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = new GtnUIFrameworkComponentConfig();
		companyType.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentId("rpPopUpRebatePlanType");
		companyType.setComponentName("Rebate Plan Type");
		companyType.setParentComponentId("RebatePlanTypelayout");
		gtnLayout.setGtnLayoutConfig(layout);
		companyType.setAddToParent(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType("COMPANY_TYPE");
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("rebatePlan01", "Search", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NO }));

		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParams);

		onFailure.add(alertActionConfig);
		validationActionConfig.addActionParameter(onFailure);
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.RP_POP_UP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.REBATE_PLAN_ID }));

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
				"complianceAndDeductionRulesReset01", "Reset", true, GtnUIFrameworkComponentType.BUTTON);
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
		resetMap.put("ruleName", "");
		resetMap.put("ruleNo", "");
		resetMap.put("ruleType", null);
		resetMap.put("ruleCategory", null);
		params.add(resetMap);

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RP_POP_UP_SEARCH_RESULT_TABLE, "Results", true, GtnUIFrameworkComponentType.PAGEDTABLE);

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
		searchResults.setItemPerPage(10);
		searchResults.setPageLength(10);
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		searchResults.setTableVisibleHeader(new String[] { "Rebate Plan ID", "Rebate Plan No", "Rebate Plan Name",
				"Rebate Plan Status", "Rebate Plan Type", "Rebate Plan Based On", "Rebate Structure", "Range Based On",
				"Net Sales Formula", "Net Sales Rule", "Creation Date", "Created By", "Modified Date ",
				"Modified By" });
		searchResults.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.REBATE_PLAN_ID, GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NO, GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NAME, "rebatePlanStatus",
						"rpPopUpRebatePlanType", "rebatePlanBasedOn", "rebateStructure", "rangeBasedOn",
						"netSalesFormula", "netSalesRule", "creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		searchResults.setExtraColumn(new Object[] { "systemId" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName("companyMaster");
		searchResults.setQueryName("rebateplanSearch");
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.REBATE_PLAN);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId("ruleType");
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("ruleTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig ruleTypeConfig = gtnFrameworkConfigurationFactory.buildComboBoxConfig("RULE_TYPE",
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
		ruleCategoryCustomFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);

		GtnUIFrameworkComboBoxConfig ruleCategoryConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("RULE_CATEGORY", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ruleCategoryCustomFilterComponentConfig.setGtnComboboxConfig(ruleCategoryConfig);
		ruleCategoryCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		ruleCategoryCustomFilterConfig.setGtnComponentConfig(ruleCategoryCustomFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig createedByCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		createedByCustomFilterConfig.setPropertId("createdBy");
		createedByCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig createdByCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		createdByCustomFilterComponentConfig.setComponentId("createdBycustomFilterComboBox");
		createdByCustomFilterComponentConfig.setComponentName(GtnFrameworkCommonConstants.CUSTOM_FILTER_COMBO_BOX);
		GtnUIFrameworkComboBoxConfig createdByConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("userIdName", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
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
		GtnUIFrameworkComboBoxConfig modifiedByConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("userIdName", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);
		modifiedByCustomFilterComponentConfig.setGtnComboboxConfig(modifiedByConfig);
		modifiedByCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		modifiedByCustomFilterConfig.setGtnComponentConfig(modifiedByCustomFilterComponentConfig);

		customFilterConfigMap.put(modifiedByCustomFilterConfig.getPropertId(), modifiedByCustomFilterConfig);

		customFilterConfigMap.put(createedByCustomFilterConfig.getPropertId(), createedByCustomFilterConfig);
		customFilterConfigMap.put(ruleCategoryCustomFilterConfig.getPropertId(), ruleCategoryCustomFilterConfig);

		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
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
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRAddButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"netSalesFormulaPopUpViewAddButton", "SELECT", true, GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDRAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.RP_POP_UP_SEARCH_RESULT_TABLE);
		actionParameter.add("rebatePlanCalculationsSecondaryRebatePlan");
		actionParameter.add(Arrays.asList(new String[] { GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NAME }));
		actionParameter.add(Arrays.asList(new String[] { "rebatePlanCalculationsSecondaryRebatePlan" }));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDRAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDREditButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"netSalesFormulaPopUpViewEditButton", "CLOSE", true, GtnUIFrameworkComponentType.BUTTON);
		cDREditButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(cDREditButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_SEARCH_VIEW }));
		actionConfigList.add(closeAction);

		cDREditButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
