/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.popup;

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
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkRebateSchedulePopUpConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_POP_UP_CONFIG,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_POP_UP_CONFIG, false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRsPopupSearchCriteriaPanel(componentList);
		addRsPopupButtonLayout(componentList);
		addRsPopupResultPanel(componentList);
		addRsPopupActionButtonLayout(componentList);

	}

	private void addRsPopupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("searchCriteriaPanel", false, null);
		panel.setComponentName("Search Criteria");
		panel.setAuthorizationIncluded(true);

		componentList.add(panel);
		addRsPopupFieldLayout(componentList);
	}

	private void addRsPopupResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig("resultPanel", false, null);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addRsPopupResultLayout(componentList);
	}

	private void addRsPopupResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("resultlayout", true,
				"resultPanel");
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList);
	}

	private void addRsPopupFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout.setComponentStyle(styleList);
		componentList.add(gtnLayout);
		addRsPopupFieldComponent(componentList);
	}

	private void addRsPopupButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addRsPopupFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addRsPopupRebateScheduleId(componentList);
		addRsPopupRebateScheduleNo(componentList);
		addRsPopupRebateScheduleName(componentList);
		addRsPopupRebateScheduleType(componentList);
		addRsPopupRebateScheduleStatus(componentList);
		addRsPopupRebateProgramType(componentList);
		addRsPopupRebateScheduleCategory(componentList);
		addRsPopupRebateScheduleAliasId(componentList);
		addRsPopupRebateFrequency(componentList);
		addRsPopupCalculationType(componentList);
		addRsPopupItemNo(componentList);
		addRsPopupItemName(componentList);
	}

	private void addRsPopupRebateScheduleId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"pouUpRebateScheduleIdLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_ID, true, "pouUpRebateScheduleIdLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Rebate Schedule ID");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdConfig);
	}

	private void addRsPopupRebateScheduleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"pouUpRebateScheduleNoLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_NO, true, "pouUpRebateScheduleNoLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName("Rebate Schedule NO");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addRsPopupRebateScheduleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"pouUpRebateScheduleNameLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_NAME, true, "pouUpRebateScheduleNameLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName("Rebate Schedule Name");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNameConfig);
	}

	private void addRsPopupRebateScheduleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"pouUpRebateScheduleTypeLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_TYPE, true, "pouUpRebateScheduleTypeLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("Rebate Schedule Type");
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMPANY_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addRsPopupRebateScheduleStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"pouUpRebateScheduleStatuslayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_STATUS, true, "pouUpRebateScheduleStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Rebate Schedule Status");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsPopupRebateProgramType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateProgramTypeLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE, true, "rebateProgramTypeLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Rebate Program Type");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsPopupRebateScheduleCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"pouUpRebateScheduleCategoryLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_CATEGORY, true, "pouUpRebateScheduleCategoryLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Rebate Schedule Category");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsPopupRebateScheduleAliasId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"pouUpRebateScheduleAliasIdLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"pouUpRebateScheduleAliasId", true, "pouUpRebateScheduleAliasIdLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("Rebate Schedule Alias ID");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addRsPopupRebateFrequency(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateFrequencyLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig("rebateFrequency",
				true, "rebateFrequencyLayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Rebate Frequency");

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsPopupCalculationType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("calculationTypeLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig("calculationType",
				true, "calculationTypeLayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Calculation Type");

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsPopupItemNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("itemNoLayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig("itemNo",
				true, "itemNoLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("Item No");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addRsPopupItemName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("itemNameLayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig("itemName",
				true, "itemNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("Item Name");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rsPopupSearchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"gtnSearch01", true, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		rsPopupSearchButtonConfig.setAuthorizationIncluded(true);
		rsPopupSearchButtonConfig.setComponentName("Search");

		componentList.add(rsPopupSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> rsPopupSearchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig rsPopupSearchValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		rsPopupSearchValidationActionConfig.setFieldValues(Arrays.asList(new String[] {
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_CATEGORY, GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE,
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_STATUS,
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_TYPE,
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_NAME, GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_NO,
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_ID }));
		rsPopupSearchValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig rsPopupSearchButtonAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("Please enter Search Criteria");

		rsPopupSearchButtonAlertActionConfig.setActionParameterList(alertParams);
		onFailure.add(rsPopupSearchButtonAlertActionConfig);

		rsPopupSearchValidationActionConfig.addActionParameter(onFailure);
		rsPopupSearchActionConfigList.add(rsPopupSearchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add("rsPopupSearchResultTable");

		loadDataTableActionConfig.setActionParameterList(actionParams);

		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] {
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_CATEGORY, GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE,
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_STATUS,
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_TYPE,
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_NAME, GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_NO,
				GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_ID }));

		rsPopupSearchActionConfigList.add(loadDataTableActionConfig);

		rsPopupSearchButtonConfig.setGtnUIFrameWorkActionConfigList(rsPopupSearchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnReset01",
				true, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Reset");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> rsPopupResetButtonActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig rsPopupResetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_NAME, "");
		resetMap.put(GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_NO, "");
		resetMap.put(GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_ID, "");
		resetMap.put(GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_STATUS, null);
		resetMap.put(GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_TYPE, null);
		resetMap.put(GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE, null);
		resetMap.put(GtnFrameworkRSConstants.POU_UP_REBATE_SCHEDULE_CATEGORY, null);

		params.add(resetMap);

		rsPopupResetActionConfig.setActionParameterList(params);
		rsPopupResetButtonActionConfigList.add(rsPopupResetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(rsPopupResetButtonActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
				"rsPopupSearchResultTable", true, "resultlayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setAuthorizationIncluded(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"rebateScheduleSearch", "rebateScheduleSearch");
		searchResults.setEditable(false);
		searchResults.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class

		});
		searchResults.setTableVisibleHeader(new String[] { "Rebate Schedule ID", "Rebate Schedule No",
				"Rebate Schedule Name", "Rebate Schedule Type", "Rebate Schedule Status", "Rebate Program Type",
				"Rebate Schedule Category", "RS Trade Class", "Rebate Schedule Alias ID", "Rebate Frequency",
				"Calendar", "Calculation Type", "Calculation Level", "Rebate Rule Type", "Rebate Rule Association",
				"Payment Level", "Payment Grace Period", "Interest Bearing Indicator", "Interest Bearing Basis",
				"Rebate Schedule Designation", "Parent Rebate Schedule ID", "Parent Rebate Schedule Name",
				"RS Transaction Reference ID", "RS Transaction Reference Name", "UDC 1", "UDC 2", "UDC 3", "UDC 4",
				"UDC 5", "UDC 6" });
		searchResults.setTableColumnMappingId(new Object[] { "rebateScheduleId", "rebateScheduleNo",
				"rebateScheduleName", "rebateScheduleType", "rebateScheduleStatus", "rebateScheduleProgramType",
				"rebateScheduleCategory", "rebateScheduleTradeClass", "rebateScheduleAlias", "rebateScheduleFrequency",
				"rebateCalendar", "rebateCalculationType", "rebateCalculationLevel", "rebateRuleType",
				"rebateRuleAssociation", "rebatePaymentLevel", "paymentGracePeriod", "interestBearingIndicator",
				"interestBearingBasis", "rebateScheduleDesignation", "parentRebateScheduleID",
				"parentRebateScheduleName", "rSTransactionReferenceID", "rSTransactionReferenceName", "rsUDC1",
				"rsUDC2", "rsUDC3", "rsUDC4", "rsUDC5", "rsUDC6" });
		searchResults.setExtraColumn(new Object[] { "systemId" });
		searchResults.setExtraColumnDataType(new Class[] { Integer.class });
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.REBATE_SCHEDULE);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId("companyType");
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("customFilterComboBox");
		customFilterComponentConfig.setComponentName("customFilterComboBox");
		customFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
		customFilterComponentConfig.getGtnComboboxConfig().setComboBoxType("COMPANY_TYPE");
		customFilterComponentConfig.getGtnComboboxConfig()
				.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
		customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addRsPopupActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkRSConstants.RS_POPUPACTION_BUTTONLAYOUT, false, null);
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRAddButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"rsPopUpViewSelectButton", true, GtnFrameworkRSConstants.RS_POPUPACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cDRAddButtonConfig.setComponentName("SELECT");
		cDRAddButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cDRAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add("netSalesSearchResultTable");
		actionParameter.add("rebatePlanCalculationsNetSalesFormula");
		actionParameter.add(Arrays.asList(new String[] { "formulaName" }));
		actionParameter.add(Arrays.asList(new String[] { "rebatePlanCalculationsNetSalesFormula" }));
		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);

		closeAction.setActionParameterList(Arrays.asList(new Object[] { "rsPopUpView" }));
		actionConfigList.add(closeAction);

		cDRAddButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDREditButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"rsPopUpViewEditButton", true, GtnFrameworkRSConstants.RS_POPUPACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cDREditButtonConfig.setComponentName("CLOSE");
		cDREditButtonConfig.setAuthorizationIncluded(true);

		componentList.add(cDREditButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);

		closeAction.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkRSConstants.REBATE_SCHEDULE_POP_UP_CONFIG }));
		actionConfigList.add(closeAction);

		cDREditButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
