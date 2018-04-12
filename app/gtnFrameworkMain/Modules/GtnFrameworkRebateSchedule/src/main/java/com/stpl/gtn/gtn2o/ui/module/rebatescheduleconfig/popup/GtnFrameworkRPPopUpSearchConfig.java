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
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkRSSearchNoticationAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkRPPopUpSearchConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_SEARCH_VIEW,
				GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_SEARCH_VIEW, false);
		addRpPopupComponentList(view);
		return view;
	}

	private void addRpPopupComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> rpPopupComponentList = new ArrayList<>();
		view.setGtnComponentList(rpPopupComponentList);
		addRpPopupSearchCriteriaPanel(rpPopupComponentList);
		addRpPopupButtonLayout(rpPopupComponentList);
		addRpPopupResultPanel(rpPopupComponentList);
		addRpPopupActionButtonLayout(rpPopupComponentList);
		addExcelButtonComponent(rpPopupComponentList);
	}

	private void addRpPopupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = configProvider.getPanelConfig("searchCriteriaPanel", false,
				null);
		searchCriteriaPanel.setComponentName("Search Criteria");
		searchCriteriaPanel.setAuthorizationIncluded(true);
		componentList.add(searchCriteriaPanel);
		addRpPopupFieldLayout(componentList);
	}

	private void addRpPopupResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig("resultPanel", false, null);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addRpPopupResultLayout(componentList);
	}

	private void addRpPopupResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.RESULTLAYOUT, true, "resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addRpPopupPagedTableComponent(componentList);
	}

	private void addRpPopupFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout.setComponentStyle(styleList);
		componentList.add(gtnLayout);
		addRpPopupFieldComponent(componentList);
	}

	private void addRpPopupButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addRpPopupSearchButtonComponent(componentList);
		addRpPopupResetButtonComponent(componentList);
	}

	private void addRpPopupFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addRebatePlanId(componentList);
		addRebatePlanNo(componentList);
		addRebatePlanName(componentList);
		addRebatePlanStatus(componentList);
		addRebatePlanType(componentList);
	}

	private void addRebatePlanId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebatePlanIdLayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebatePlanIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_ID, true, "rebatePlanIdLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanIdConfig.setAuthorizationIncluded(true);
		rebatePlanIdConfig.setComponentName("Rebate Plan ID");
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanIdConfig.setGtnUIFrameworkValidationConfig(validationConfig);
		componentList.add(rebatePlanIdConfig);
	}

	private void addRebatePlanNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebatePlanNoLayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebatePlanConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NO, true, "rebatePlanNoLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanConfig.setAuthorizationIncluded(true);
		rebatePlanConfig.setComponentName("Rebate Plan No");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		gtnUIFrameworkValidationConfig.setMaxLength(50);
		rebatePlanConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(rebatePlanConfig);
	}

	private void addRebatePlanName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebatePlanNameLayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebatePlanNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NAME, true, "rebatePlanNameLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNameConfig.setAuthorizationIncluded(true);
		rebatePlanNameConfig.setComponentName("Rebate Plan Name");
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanNameConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(rebatePlanNameConfig);
	}

	private void addRebatePlanStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebatePlanStatuslayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebatePlanStatus = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanStatus", true, "rebatePlanStatuslayout", GtnUIFrameworkComponentType.COMBOBOX);
		rebatePlanStatus.setComponentName("Rebate Plan Status");

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebatePlanStatus.setGtnUIFrameworkValidationConfig(validationConfig);

		rebatePlanStatus.setAuthorizationIncluded(true);

		componentList.add(rebatePlanStatus);

		GtnUIFrameworkComboBoxConfig rebatePlanConfig = new GtnUIFrameworkComboBoxConfig();
		rebatePlanConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebatePlanConfig.setComboBoxType("STATUS");
		rebatePlanStatus.setGtnComboboxConfig(rebatePlanConfig);
	}

	private void addRebatePlanType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("RebatePlanTypelayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setAddToParent(true);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebatePlanType = configProvider.getUIFrameworkComponentConfig(
				"rpPopUpRebatePlanType", true, "RebatePlanTypelayout", GtnUIFrameworkComponentType.COMBOBOX);
		rebatePlanType.setComponentName("Rebate Plan Type");

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebatePlanType.setGtnUIFrameworkValidationConfig(validationConfig);

		rebatePlanType.setAuthorizationIncluded(true);

		componentList.add(rebatePlanType);

		GtnUIFrameworkComboBoxConfig rebatePlanTypeConfig = new GtnUIFrameworkComboBoxConfig();
		rebatePlanTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebatePlanTypeConfig.setComboBoxType("REBATE_PLAN_TYPE");
		rebatePlanType.setGtnComboboxConfig(rebatePlanTypeConfig);
	}

	private void addRpPopupSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("rebatePlan01",
				true, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Search");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> rpPopupSearchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig rpPopupSearchValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		rpPopupSearchValidationActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_ID,
						GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NO,
						GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NAME,
						GtnFrameworkCommonConstants.REBATE_PLAN_STATUS,
						GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_TYPE));

		GtnUIFrameWorkActionConfig rpPopupSearchAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.WARNING_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		rpPopupSearchAlertActionConfig.setActionParameterList(alertParams);

		rpPopupSearchValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(rpPopupSearchAlertActionConfig)));
		rpPopupSearchActionConfigList.add(rpPopupSearchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.RP_POP_UP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_ID,
				GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NO,
				GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NAME, GtnFrameworkCommonConstants.REBATE_PLAN_STATUS,
				GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_TYPE));

		rpPopupSearchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		notificationActionConfig.addActionParameter(GtnUIFrameWorkRSSearchNoticationAction.class.getName());
		notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.RP_POP_UP_SEARCH_RESULT_TABLE);
		rpPopupSearchActionConfigList.add(notificationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(rpPopupSearchActionConfigList);

	}

	private void addRpPopupResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupResetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"complianceAndDeductionRulesReset01", true, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		rpPopupResetButtonConfig.setAuthorizationIncluded(true);
		rpPopupResetButtonConfig.setComponentName("Reset");
		componentList.add(rpPopupResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> rpPopupResetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig rpPopupResetActionConfig = new GtnUIFrameWorkActionConfig();
		rpPopupResetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		params.add(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_ID,
				GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NO,
				GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NAME, GtnFrameworkCommonConstants.REBATE_PLAN_STATUS,
				GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_TYPE,
				GtnFrameworkCommonConstants.RP_POP_UP_SEARCH_RESULT_TABLE));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, null));

		rpPopupResetActionConfig.setActionParameterList(params);

		rpPopupResetActionConfigList.add(rpPopupResetActionConfig);
		rpPopupResetButtonConfig.setGtnUIFrameWorkActionConfigList(rpPopupResetActionConfigList);

	}

	private void addRpPopupPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpPopupSearchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.RP_POP_UP_SEARCH_RESULT_TABLE, true,
				GtnFrameworkCommonConstants.RESULTLAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		rpPopupSearchResultConfig.setAuthorizationIncluded(true);

		rpPopupSearchResultConfig.setComponentName("Results");
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		rpPopupSearchResultConfig.setComponentStyle(tableStyle);
		rpPopupSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		rpPopupSearchResultConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(rpPopupSearchResultConfig);

		GtnUIFrameworkPagedTableConfig rpPopupSearchResults = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"companyMaster", "rebateplanSearch");
		rpPopupSearchResults.setEditable(false);
		rpPopupSearchResults.setItemPerPage(10);
		rpPopupSearchResults.setPageLength(10);
		rpPopupSearchResults.setSinkItemPerPageWithPageLength(false);
		rpPopupSearchResults.setTableColumnDataType(new Class[] { String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				Date.class, String.class, Date.class, String.class });
		rpPopupSearchResults.setTableVisibleHeader(new String[] { "Rebate Plan ID", "Rebate Plan No",
				"Rebate Plan Name", "Rebate Plan Status", "Rebate Plan Type", "Rebate Plan Based On",
				"Rebate Structure", "Range Based On", "Net Sales Formula", "Net Sales Rule", "Creation Date",
				"Created By", "Modified Date ", "Modified By" });
		rpPopupSearchResults.setTableColumnMappingId(
				new Object[] { "rebatePlanId", "secondaryRebatePlanNo", "secondaryRebatePlanName", "rebatePlanStatus",
						"rpPopUpRebatePlanType", "rebatePlanBasedOn", "rebateStructure", "rangeBasedOn",
						"netSalesFormula", "netSalesRule", "creationDate", "createdBy", "modifiedDate", "modifiedBy" });
		rpPopupSearchResults.setExtraColumn(new Object[] { "systemId" });
		rpPopupSearchResults.setExtraColumnDataType(new Class[] { Integer.class });
		rpPopupSearchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.REBATE_PLAN);
		rpPopupSearchResults.setCustomFilterConfigMap(getRPPopupCustomFilterConfig());
		rpPopupSearchResultConfig.setGtnPagedTableConfig(rpPopupSearchResults);
	}

	private void addRpPopupActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false, null);
		componentList.add(gtnLayout);
		addRpPopupSelectButtonComponent(componentList);
		addRpPopupCloseButtonComponent(componentList);
	}

	private void addRpPopupSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig selectButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanPopUpViewSelectButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		selectButtonConfig.setAuthorizationIncluded(true);
		selectButtonConfig.setComponentName("SELECT");

		componentList.add(selectButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.RP_POP_UP_SEARCH_RESULT_TABLE);
		actionParameter.add("rebatePlanCalculationsSecondaryRebatePlan");
		actionParameter.add(Arrays.asList(GtnFrameworkCommonConstants.SECONDARY_REBATE_PLAN_NAME));
		actionParameter.add(Arrays.asList("rebatePlanCalculationsSecondaryRebatePlan"));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		Object viewName = GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_SEARCH_VIEW;
		closeAction.setActionParameterList(Arrays.asList(viewName));
		actionConfigList.add(closeAction);

		selectButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addRpPopupCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig closeButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"netSalesFormulaPopUpViewEditButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setAuthorizationIncluded(true);
		closeButtonConfig.setComponentName("CLOSE");
		componentList.add(closeButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		Object viewName = GtnFrameworkCommonConstants.REBATE_PLAN_POPUP_SEARCH_VIEW;
		closeAction.setActionParameterList(Arrays.asList(viewName));
		actionConfigList.add(closeAction);

		closeButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getRPPopupCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = GtnFrameworkRSConstants.getRP_POPUP_PROPERTY_IDS();
		String[] listNameArray = GtnFrameworkRSConstants.getRP_LIST_NAME_ARRAY();
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig rPPopupCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			rPPopupCustomFilterConfig.setPropertId(propertyIds[i]);
			rPPopupCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig rPPopupCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			rPPopupCustomFilterComponentConfig.setComponentId("customFilterComboBox");
			rPPopupCustomFilterComponentConfig.setComponentName("customFilterComboBox");
			rPPopupCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			rPPopupCustomFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			rPPopupCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			rPPopupCustomFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			rPPopupCustomFilterConfig.setGtnComponentConfig(rPPopupCustomFilterComponentConfig);
			customFilterConfigMap.put(rPPopupCustomFilterConfig.getPropertId(), rPPopupCustomFilterConfig);

		}
		return customFilterConfigMap;
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig("rpLookupAddExcel",
				true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		excelButtonConfig.setComponentName("true;");
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider.getExcelBtnconfig(
				"Rebate Plan", true, "rpPopUpSearchResultTable", false);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}
}
