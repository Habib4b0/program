/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

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
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkFilterBarInvisibleAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkCalculationTypeChangeAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkRSLoadAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkRebateScheduleLandingScreenConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("Search View", "V001", true);
		GtnUIFrameWorkActionConfig reloadHelperTableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RELOAD_HELPER_TABLE_ACTION);
		view.addViewAction(reloadHelperTableAction);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRsLandingScreenSearchCriteriaPanel(componentList);
		addRsLandingScreenButtonLayout(componentList);
		addRsLandingScreenResultPanel(componentList);
		addRsLandingScreenActionButtonLayout(componentList);

	}

	private void addRsLandingScreenSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("searchCriteriaPanel", false, null);
		panel.setComponentName("Search Criteria");
		panel.setAuthorizationIncluded(true);

		componentList.add(panel);
		addRsLandingScreenFieldLayout(componentList);
	}

	private void addRsLandingScreenResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig("resultPanel", false, null);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addRsLandingScreenResultLayout(componentList);
	}

	private void addRsLandingScreenResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("resultlayout", true,
				"resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList);
	}

	private void addRsLandingScreenFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout.setComponentStyle(styleList);
		componentList.add(gtnLayout);
		addRsLandingScreenFieldComponent(componentList);
	}

	private void addRsLandingScreenButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addRsLandingScreenFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addRsLandingScreenRebateScheduleId(componentList);
		addRsLandingScreenRebateScheduleNo(componentList);
		addRebateScheduleName(componentList);
		addRsLandingScreenRebateScheduleType(componentList);
		addRsLandingScreenRebateScheduleStatus(componentList);
		addRsLandingScreenRebateProgramType(componentList);
		addRsLandingScreenRebateScheduleCategory(componentList);
		addRsLandingScreenRebateScheduleAliasId(componentList);
		addRsLandingScreenRebateFrequency(componentList);
		addRsLandingScreenCalculationType(componentList);
		addRsLandingScreenItemNo(componentList);
		addRsLandingScreenItemName(componentList);
	}

	private void addRsLandingScreenRebateScheduleId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateScheduleIdLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ID, true, "rebateScheduleIdLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Rebate Schedule ID");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdConfig);
	}

	private void addRsLandingScreenRebateScheduleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateScheduleNoLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NO, true, "rebateScheduleNoLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName("Rebate Schedule No");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addRebateScheduleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateScheduleNameLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME, true, "rebateScheduleNameLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName("Rebate Schedule Name");
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNameConfig);
	}

	private void addRsLandingScreenRebateScheduleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateScheduleTypeLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE, true, "rebateScheduleTypeLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("Rebate Schedule Type");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("RS_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addRsLandingScreenRebateScheduleStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateScheduleStatuslayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS, true, "rebateScheduleStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Rebate Schedule Status");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsLandingScreenRebateProgramType(List<GtnUIFrameworkComponentConfig> componentList) {
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

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("REBATE_PROGRAM_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsLandingScreenRebateScheduleCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateScheduleCategoryLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY, true, "rebateScheduleCategoryLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Rebate Schedule Category");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("RS_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsLandingScreenRebateScheduleAliasId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebateScheduleAliasIdLayout", true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID, true, "rebateScheduleAliasIdLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("Rebate Schedule Alias ID");

		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		companyIdentifierConfig.setVisible(true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyIdentifierConfig);

	}

	private void addRsLandingScreenRebateFrequency(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateFrequencyLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebateFrequency = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_FREQUENCY, true, "rebateFrequencyLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		rebateFrequency.setAuthorizationIncluded(true);
		rebateFrequency.setComponentName("Rebate Frequency");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateFrequency.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(rebateFrequency);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("REBATE_FREQUENCY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateFrequency.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsLandingScreenCalculationType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("calculationTypeLayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.REBATE_CALCULATION_TYPE, true, "calculationTypeLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Calculation Type");
		companyStatus.setAddToParent(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("CALCULATION_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addRsLandingScreenItemNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("itemNoLayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_NO, true, "itemNoLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setComponentName("Item No");
		companyIdentifierConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		companyIdentifierConfig.setVisible(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyIdentifierConfig);

	}

	private void addRsLandingScreenItemName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("itemNameLayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_NAME, true, "itemNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setComponentName("Item Name");
		companyIdentifierConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		companyIdentifierConfig.setVisible(true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnSearch01",
				true, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Search");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkRSConstants.REBATE_SCHEDULE_ID,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NO, GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE, GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS,
				GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE, GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID, GtnFrameworkRSConstants.REBATE_SCHEDULE_FREQUENCY,
				GtnFrameworkRSConstants.REBATE_CALCULATION_TYPE, GtnFrameworkCommonConstants.ITEM_NO,
				GtnFrameworkCommonConstants.ITEM_NAME));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION, "No Search Criteria ", "Please enter Search Criteria");

		onFailure.add(alertActionConfig);

		validationActionConfig.addActionParameter(onFailure);
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION, GtnFrameworkRSConstants.RS_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY,
				GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE, GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE, GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NO, GtnFrameworkRSConstants.REBATE_SCHEDULE_ID,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_FREQUENCY, GtnFrameworkRSConstants.REBATE_CALCULATION_TYPE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID, GtnFrameworkCommonConstants.ITEM_NO,
				GtnFrameworkCommonConstants.ITEM_NAME));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkRSConstants.RS_SEARCH_RESULT_TABLE);
		actionConfigList.add(notificationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rsLandingScreenResetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"gtnReset01", true, GtnFrameworkCommonConstants.SEARCH_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		rsLandingScreenResetButtonConfig.setAuthorizationIncluded(true);
		rsLandingScreenResetButtonConfig.setComponentName("Reset");

		componentList.add(rsLandingScreenResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> rsLandingScreenResetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		List<String> rsLandingScreenResetComponentIdList = new ArrayList<>();
		List<Object> rsLandingScreenResetComponentValueList = new ArrayList<>();
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME);
		rsLandingScreenResetComponentValueList.add("");
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_NO);
		rsLandingScreenResetComponentValueList.add("");
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_ID);
		rsLandingScreenResetComponentValueList.add("");
		rsLandingScreenResetComponentIdList.add(GtnFrameworkCommonConstants.ITEM_NAME);
		rsLandingScreenResetComponentValueList.add("");
		rsLandingScreenResetComponentIdList.add(GtnFrameworkCommonConstants.ITEM_NO);
		rsLandingScreenResetComponentValueList.add("");
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID);
		rsLandingScreenResetComponentValueList.add("");
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS);
		rsLandingScreenResetComponentValueList.add(null);
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE);
		rsLandingScreenResetComponentValueList.add(null);
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE);
		rsLandingScreenResetComponentValueList.add(null);
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_CALCULATION_TYPE);
		rsLandingScreenResetComponentValueList.add(null);
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_FREQUENCY);
		rsLandingScreenResetComponentValueList.add(null);
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY);
		rsLandingScreenResetComponentValueList.add(null);
		rsLandingScreenResetComponentIdList.add(GtnFrameworkRSConstants.RS_SEARCH_RESULT_TABLE);
		rsLandingScreenResetComponentValueList.add(null);
		params.add(rsLandingScreenResetComponentIdList);
		params.add(rsLandingScreenResetComponentValueList);

		resetActionConfig.setActionParameterList(params);
		rsLandingScreenResetActionConfigList.add(resetActionConfig);
		rsLandingScreenResetButtonConfig.setGtnUIFrameWorkActionConfigList(rsLandingScreenResetActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.RS_SEARCH_RESULT_TABLE, true, "resultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		searchResultConfig.setSpacing(true);
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
		searchResults.setSinkItemPerPageWithPageLength(false);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);
		searchResults.setTableColumnDataType(new Class[] { String.class, String.class, String.class, String.class,
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
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkRSConstants.REBATE_SCHEDULE_ID,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NO, GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE, GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS,
				"rebateScheduleProgramType", GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TRADE_CLASS, GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_FREQUENCY, "rebateCalendar",
				GtnFrameworkRSConstants.REBATE_CALCULATION_TYPE, "rebateCalculationLevel",
				GtnFrameworkRSConstants.REBATE_RULE_TYPE, "rebateRuleAssociation", "rebatePaymentLevel",
				GtnFrameworkRSConstants.PAYMENT_GRACE_PERIOD, GtnFrameworkRSConstants.INTEREST_BEARING_INDICATOR,
				GtnFrameworkRSConstants.INTEREST_BEARING_BASIS, GtnFrameworkRSConstants.REBATE_SCHEDULE_DESIGNATION,
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_ID, GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_NAME,
				"rSTransactionReferenceID", "rSTransactionReferenceName", GtnFrameworkRSConstants.RS_UDC1,
				GtnFrameworkRSConstants.RS_UDC2, GtnFrameworkRSConstants.RS_UDC3, GtnFrameworkRSConstants.RS_UDC4,
				GtnFrameworkRSConstants.RS_UDC5, GtnFrameworkRSConstants.RS_UDC6 });
		searchResults.setExtraColumn(new Object[] { "systemId" });
		searchResults.setExtraColumnDataType(new Class[] { String.class });
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.REBATE_SCHEDULE);

		searchResults.setCustomFilterConfigMap(getCustomFilterConfig());
		searchResultConfig.setGtnPagedTableConfig(searchResults);

		searchResults.setDoubleClickEnable(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE, GtnUIFrameworkModeType.EDIT);
		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkRSConstants.REBATE_SCHEDULE_ADD_VIEW);
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.setActionParameterList(getDefaultFieldValueList());
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig addBtnCaptionChangeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		List<String> addBtnCaptionChangeComponentIdList = new ArrayList<>();
		addBtnCaptionChangeComponentIdList.add(GtnFrameworkRSConstants.RS_ADD_SAVE_BUTTON);
		List<String> captionList = new ArrayList<>();
		captionList.add("Update");
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(addBtnCaptionChangeComponentIdList);
		actionParameterList.add(captionList);
		addBtnCaptionChangeActionConfig.setActionParameterList(actionParameterList);
		actionConfigList.add(addBtnCaptionChangeActionConfig);

		GtnUIFrameWorkActionConfig enableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ENABLE_ACTION);
		Object[] enableField = new Object[] { GtnFrameworkCommonConstants.NOTES_TAB,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ID1, GtnFrameworkRSConstants.REBATE_SCHEDULE_NO1,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME1, GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID1,
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_NAME, GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME,
				GtnFrameworkRSConstants.PAYMENT_GRACE_PERIOD, GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME,
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_ID, GtnFrameworkRSConstants.RS_TRANSACTION_REF_ID,
				GtnFrameworkRSConstants.EVALUATION_RULE_ASSOCIATION, GtnFrameworkRSConstants.CALCULATION_RULE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS1, GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE1,
				GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE1, GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY1,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TRADE_CLASS,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_DESIGNATION, GtnFrameworkRSConstants.RS_UDC1,
				GtnFrameworkRSConstants.RS_UDC2, GtnFrameworkRSConstants.RS_UDC3, GtnFrameworkRSConstants.RS_UDC4,
				GtnFrameworkRSConstants.RS_UDC5, GtnFrameworkRSConstants.RS_UDC6,
				GtnFrameworkRSConstants.RS_DEDUCTION_INCLUSION, GtnFrameworkRSConstants.RS_CALENDAR,
				GtnFrameworkRSConstants.REBATE_FREQUENCY1, GtnFrameworkRSConstants.PAYMENT_LEVEL,
				GtnFrameworkRSConstants.PAYMENT_FREQUENCY, GtnFrameworkRSConstants.PAYMENT_TERMS,
				GtnFrameworkRSConstants.PAYMENT_METHOD, GtnFrameworkRSConstants.INTEREST_BEARING_BASIS,
				GtnFrameworkRSConstants.EVALUATION_RULE_LEVEL, GtnFrameworkRSConstants.EVALUATION_RULE_TYPE,
				GtnFrameworkRSConstants.INTEREST_BEARING_INDICATOR, GtnFrameworkRSConstants.CALCULATION_RULE_LEVEL,
				GtnFrameworkRSConstants.CALCULATION_TYPE1, GtnFrameworkRSConstants.CALCULATION_LEVEL,
				GtnFrameworkRSConstants.REBATE_RULE_TYPE, GtnFrameworkRSConstants.REBATE_SCHEDULE_START_DATE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE, GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE,
				GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE, "RSleftResultTable",
				"RSrightResultTable" };

		enableAction.setActionParameterList(new ArrayList<>(Arrays.asList(enableField)));
		actionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig disableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.DISABLE_ACTION);
		Object[] disableField = new Object[] { GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_ID };

		disableAction.setActionParameterList(new ArrayList<>(Arrays.asList(disableField)));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkRSConstants.RS_ADD_SAVE_BUTTON,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ADD_VIEW_A_ADD_RESET_BUTTON,
				GtnFrameworkRSConstants.PRICE_SCHEDULE_ADD_VIEW_A_ADD_DELETE_BUTTON };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(true);
		visibleParameters.add(new ArrayList<Object>(Arrays.asList(visibleFields)));

		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig editActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkRSLoadAction.class.getName());
		parameters.add(GtnFrameworkRSConstants.RS_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(30);
		editActionConfig.setActionParameterList(parameters);
		actionConfigList.add(editActionConfig);
		searchResults.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addRsLandingScreenActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
		addViewButtonComponent(componentList);
		addExcelButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig addButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnAddButton",
				true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentName("ADD");
		addButtonConfig.setAuthorizationIncluded(true);

		componentList.add(addButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE, GtnUIFrameworkModeType.ADD);
		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.NAVIGATION_ACTION, GtnFrameworkRSConstants.REBATE_SCHEDULE_ADD_VIEW);

		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.setActionParameterList(getDefaultFieldValueList());
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig enableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ENABLE_ACTION);
		Object[] enableField = new String[] { GtnFrameworkCommonConstants.NOTES_TAB,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ID1, GtnFrameworkRSConstants.REBATE_SCHEDULE_NO1,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME1, GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID1,
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_NAME, GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME,
				GtnFrameworkRSConstants.PAYMENT_GRACE_PERIOD, GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME,
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_ID, GtnFrameworkRSConstants.RS_TRANSACTION_REF_ID,
				GtnFrameworkRSConstants.EVALUATION_RULE_ASSOCIATION, GtnFrameworkRSConstants.CALCULATION_RULE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS1, GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE1,
				GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE1, GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY1,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TRADE_CLASS,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_DESIGNATION, GtnFrameworkRSConstants.RS_UDC1,
				GtnFrameworkRSConstants.RS_UDC2, GtnFrameworkRSConstants.RS_UDC3, GtnFrameworkRSConstants.RS_UDC4,
				GtnFrameworkRSConstants.RS_UDC5, GtnFrameworkRSConstants.RS_UDC6,
				GtnFrameworkRSConstants.RS_DEDUCTION_INCLUSION, GtnFrameworkRSConstants.RS_CALENDAR,
				GtnFrameworkRSConstants.REBATE_FREQUENCY1, GtnFrameworkRSConstants.PAYMENT_LEVEL,
				GtnFrameworkRSConstants.PAYMENT_FREQUENCY, GtnFrameworkRSConstants.PAYMENT_TERMS,
				GtnFrameworkRSConstants.PAYMENT_METHOD, GtnFrameworkRSConstants.INTEREST_BEARING_BASIS,
				GtnFrameworkRSConstants.EVALUATION_RULE_LEVEL, GtnFrameworkRSConstants.EVALUATION_RULE_TYPE,
				GtnFrameworkRSConstants.INTEREST_BEARING_INDICATOR, GtnFrameworkRSConstants.CALCULATION_RULE_LEVEL,
				GtnFrameworkRSConstants.CALCULATION_TYPE1, GtnFrameworkRSConstants.CALCULATION_LEVEL,
				GtnFrameworkRSConstants.REBATE_RULE_TYPE, GtnFrameworkRSConstants.REBATE_SCHEDULE_START_DATE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE, GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE,
				GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE,
				GtnFrameworkRSConstants.RS_ITEM_ADDITIONMOVERIGHT_BUTTONS,
				GtnFrameworkRSConstants.RS_ITEM_ADDITIONMOVE_LEFT_BUTTONS,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_REBATE_PROCESSING_TYPE };

		enableAction.setActionParameterList(Arrays.asList(enableField));
		actionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig disableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.DISABLE_ACTION);
		Object[] disableField = new Object[] { GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_ID };

		disableAction.setActionParameterList(Arrays.asList(disableField));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkRSConstants.PRICE_SCHEDULE_ADD_VIEW_A_ADD_DELETE_BUTTON };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(false);
		visibleParameters.add(Arrays.asList(visibleFields));

		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig layoutVisibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] layoutVisibleArray = new String[] { GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT,
				GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT };

		List<Object> visibleActionParameters = new ArrayList<>();
		visibleActionParameters.add(true);
		visibleActionParameters.add(Arrays.asList(layoutVisibleArray));

		layoutVisibleAction.setActionParameterList(visibleActionParameters);
		actionConfigList.add(layoutVisibleAction);

		GtnUIFrameWorkActionConfig addButtonCaptionChangeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		List<String> addButtonCaptionChangeComponentIdList = new ArrayList<>();
		addButtonCaptionChangeComponentIdList.add(GtnFrameworkRSConstants.RS_ADD_SAVE_BUTTON);
		List<String> captionList = new ArrayList<>();
		captionList.add("SAVE");

		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(addButtonCaptionChangeComponentIdList);
		actionParameterList.add(captionList);

		addButtonCaptionChangeActionConfig.setActionParameterList(actionParameterList);
		actionConfigList.add(addButtonCaptionChangeActionConfig);

		GtnUIFrameWorkActionConfig calculationTypeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> customActionParams = new ArrayList<>();
		customActionParams.add(GtnUIFrameWorkCalculationTypeChangeAction.class.getName());
		calculationTypeActionConfig.setActionParameterList(customActionParams);
		actionConfigList.add(calculationTypeActionConfig);

		GtnUIFrameWorkActionConfig filterBarInvisibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkFilterBarInvisibleAction.class.getName());
		actionParams.add("psRebateSetupTabResultDataTable");
		Map<String, Boolean> filterInvisibleMap = new HashMap<>();
		filterInvisibleMap.put(GtnFrameworkCommonConstants.CHECK_RECORD_ID, false);
		actionParams.add(filterInvisibleMap);
		filterBarInvisibleAction.setActionParameterList(actionParams);
		actionConfigList.add(filterBarInvisibleAction);
		addButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig editButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnEditButton",
				true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		editButtonConfig.setComponentName("EDIT");
		editButtonConfig.setAuthorizationIncluded(true);

		componentList.add(editButtonConfig);

		List<GtnUIFrameWorkActionConfig> editActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.addActionParameter(GtnUIFrameworkModeType.EDIT);
		editActionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig isRecordSelectedAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		isRecordSelectedAction
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkRSConstants.RSIS_RECORD_SELECTED }));
		editActionConfigList.add(isRecordSelectedAction);

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkRSConstants.REBATE_SCHEDULE_ADD_VIEW }));
		editActionConfigList.add(navigationActionConfig);
		GtnUIFrameWorkActionConfig setDefaultActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.setActionParameterList(getDefaultFieldValueList());
		editActionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		List<String> componentIdList = new ArrayList<>();
		componentIdList.add(GtnFrameworkRSConstants.RS_ADD_SAVE_BUTTON);
		List<String> captionList = new ArrayList<>();
		captionList.add("UPDATE");
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(componentIdList);
		actionParameterList.add(captionList);
		changeCaptionActionConfig.setActionParameterList(actionParameterList);
		editActionConfigList.add(changeCaptionActionConfig);

		GtnUIFrameWorkActionConfig enableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ENABLE_ACTION);
		Object[] enableField = new Object[] { GtnFrameworkCommonConstants.NOTES_TAB,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ID1, GtnFrameworkRSConstants.REBATE_SCHEDULE_NO1,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME1, GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID1,
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_NAME, GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME,
				GtnFrameworkRSConstants.PAYMENT_GRACE_PERIOD, GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME,
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_ID, GtnFrameworkRSConstants.RS_TRANSACTION_REF_ID,
				GtnFrameworkRSConstants.EVALUATION_RULE_ASSOCIATION, GtnFrameworkRSConstants.CALCULATION_RULE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS1, GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE1,
				GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE1, GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY1,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TRADE_CLASS,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_DESIGNATION, GtnFrameworkRSConstants.RS_UDC1,
				GtnFrameworkRSConstants.RS_UDC2, GtnFrameworkRSConstants.RS_UDC3, GtnFrameworkRSConstants.RS_UDC4,
				GtnFrameworkRSConstants.RS_UDC5, GtnFrameworkRSConstants.RS_UDC6,
				GtnFrameworkRSConstants.RS_DEDUCTION_INCLUSION, GtnFrameworkRSConstants.RS_CALENDAR,
				GtnFrameworkRSConstants.REBATE_FREQUENCY1, GtnFrameworkRSConstants.PAYMENT_LEVEL,
				GtnFrameworkRSConstants.PAYMENT_FREQUENCY, GtnFrameworkRSConstants.PAYMENT_TERMS,
				GtnFrameworkRSConstants.PAYMENT_METHOD, GtnFrameworkRSConstants.INTEREST_BEARING_BASIS,
				GtnFrameworkRSConstants.EVALUATION_RULE_LEVEL, GtnFrameworkRSConstants.EVALUATION_RULE_TYPE,
				GtnFrameworkRSConstants.INTEREST_BEARING_INDICATOR, GtnFrameworkRSConstants.CALCULATION_RULE_LEVEL,
				GtnFrameworkRSConstants.CALCULATION_TYPE1, GtnFrameworkRSConstants.CALCULATION_LEVEL,
				GtnFrameworkRSConstants.REBATE_RULE_TYPE, GtnFrameworkRSConstants.REBATE_SCHEDULE_START_DATE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE, GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE,
				GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE,
				GtnFrameworkRSConstants.RS_ITEM_ADDITIONMOVERIGHT_BUTTONS,
				GtnFrameworkRSConstants.RS_ITEM_ADDITIONMOVE_LEFT_BUTTONS,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_REBATE_PROCESSING_TYPE };

		enableAction.setActionParameterList(Arrays.asList(enableField));
		editActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig disableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.DISABLE_ACTION);
		Object[] disableField = new Object[] { GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_ID };

		disableAction.setActionParameterList(Arrays.asList(disableField));
		editActionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkRSConstants.RS_ADD_SAVE_BUTTON,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ADD_VIEW_A_ADD_RESET_BUTTON,
				GtnFrameworkRSConstants.PRICE_SCHEDULE_ADD_VIEW_A_ADD_DELETE_BUTTON,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT,
				GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(true);
		visibleParameters.add(Arrays.asList(visibleFields));

		visibleAction.setActionParameterList(visibleParameters);
		editActionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig editActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkRSLoadAction.class.getName());
		parameters.add(GtnFrameworkRSConstants.RS_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(30);
		editActionConfig.setActionParameterList(parameters);
		editActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig calculationTypeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> customActionParams = new ArrayList<>();
		customActionParams.add(GtnUIFrameWorkCalculationTypeChangeAction.class.getName());
		calculationTypeActionConfig.setActionParameterList(customActionParams);
		editActionConfigList.add(calculationTypeActionConfig);

		GtnUIFrameWorkActionConfig filterBarInvisibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkFilterBarInvisibleAction.class.getName());
		actionParams.add("psRebateSetupTabResultDataTable");
		Map<String, Boolean> filterInvisibleMap = new HashMap<>();
		filterInvisibleMap.put(GtnFrameworkCommonConstants.CHECK_RECORD_ID, false);
		actionParams.add(filterInvisibleMap);
		filterBarInvisibleAction.setActionParameterList(actionParams);
		editActionConfigList.add(filterBarInvisibleAction);

		editButtonConfig.setGtnUIFrameWorkActionConfigList(editActionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig viewButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"cDRAddViewButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		viewButtonConfig.setComponentName("VIEW");
		viewButtonConfig.setAuthorizationIncluded(true);

		componentList.add(viewButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.VIEW }));

		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig isRecordSelectedAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		isRecordSelectedAction
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkRSConstants.RSIS_RECORD_SELECTED }));

		actionConfigList.add(isRecordSelectedAction);

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkRSConstants.REBATE_SCHEDULE_ADD_VIEW }));

		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		setDefaultActionConfig.setActionParameterList(getViewDefaultFieldValueList());
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkRSLoadAction.class.getName());
		parameters.add(GtnFrameworkRSConstants.RS_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(true);
		parameters.add(30);

		editActionConfig.setActionParameterList(parameters);
		actionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig disableAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.DISABLE_ACTION);
		Object[] disableField = new String[] { GtnFrameworkCommonConstants.NOTES_TAB,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ID1, GtnFrameworkRSConstants.REBATE_SCHEDULE_NO1,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME1, GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID1,
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_NAME, GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME,
				GtnFrameworkRSConstants.PAYMENT_GRACE_PERIOD, GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME,
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_ID, GtnFrameworkRSConstants.RS_TRANSACTION_REF_ID,
				GtnFrameworkRSConstants.EVALUATION_RULE_ASSOCIATION, GtnFrameworkRSConstants.CALCULATION_RULE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS1, GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE1,
				GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE1, GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY1,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TRADE_CLASS,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_DESIGNATION, GtnFrameworkRSConstants.RS_UDC1,
				GtnFrameworkRSConstants.RS_UDC2, GtnFrameworkRSConstants.RS_UDC3, GtnFrameworkRSConstants.RS_UDC4,
				GtnFrameworkRSConstants.RS_UDC5, GtnFrameworkRSConstants.RS_UDC6,
				GtnFrameworkRSConstants.RS_DEDUCTION_INCLUSION, GtnFrameworkRSConstants.RS_CALENDAR,
				GtnFrameworkRSConstants.REBATE_FREQUENCY1, GtnFrameworkRSConstants.PAYMENT_LEVEL,
				GtnFrameworkRSConstants.PAYMENT_FREQUENCY, GtnFrameworkRSConstants.PAYMENT_TERMS,
				GtnFrameworkRSConstants.PAYMENT_METHOD, GtnFrameworkRSConstants.INTEREST_BEARING_BASIS,
				GtnFrameworkRSConstants.EVALUATION_RULE_LEVEL, GtnFrameworkRSConstants.EVALUATION_RULE_TYPE,
				GtnFrameworkRSConstants.INTEREST_BEARING_INDICATOR, GtnFrameworkRSConstants.CALCULATION_RULE_LEVEL,
				GtnFrameworkRSConstants.CALCULATION_TYPE1, GtnFrameworkRSConstants.CALCULATION_LEVEL,
				GtnFrameworkRSConstants.REBATE_RULE_TYPE, GtnFrameworkRSConstants.REBATE_SCHEDULE_START_DATE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE, GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE,
				GtnFrameworkRSConstants.RS_ITEM_ADDITIONMOVERIGHT_BUTTONS,
				GtnFrameworkRSConstants.RS_ITEM_ADDITIONMOVE_LEFT_BUTTONS,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_REBATE_PROCESSING_TYPE,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_VALIDATION_PROFILE,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_INTEREST_BEARING_INDICATOR,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_INTEREST_BEARING_BASIS,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_MOVE_RIGHT_BUTTON_INDICATOR,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_MOVE_LEFT_BUTTON_INDICATOR,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_MOVE_ALL_RIGHT_INDICATOR,
				GtnFrameworkRSConstants.CFP_COMPANY_ADDITION_MOVE_ALL_LEFT_INDICATOR };

		disableAction.setActionParameterList(Arrays.asList(disableField));
		actionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkRSConstants.RS_ADD_SAVE_BUTTON,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ADD_VIEW_A_ADD_RESET_BUTTON,
				GtnFrameworkRSConstants.PRICE_SCHEDULE_ADD_VIEW_A_ADD_DELETE_BUTTON,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT,
				GtnFrameworkRSConstants.REBATE_SETUPMASS_UPDATE_PANEL_LAYOUT };

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(false);
		visibleParameters.add(Arrays.asList(visibleFields));

		visibleAction.setActionParameterList(visibleParameters);
		actionConfigList.add(visibleAction);

		viewButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS, "rebateScheduleProgramType",
				GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY, GtnFrameworkRSConstants.REBATE_SCHEDULE_TRADE_CLASS,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_FREQUENCY, "rebateCalendar",
				GtnFrameworkRSConstants.REBATE_CALCULATION_TYPE, "rebateCalculationLevel",
				GtnFrameworkRSConstants.REBATE_RULE_TYPE, GtnFrameworkRSConstants.PAYMENT_TERMS,
				GtnFrameworkRSConstants.PAYMENT_METHOD, GtnFrameworkRSConstants.PAYMENT_FREQUENCY,
				GtnFrameworkRSConstants.INTEREST_BEARING_INDICATOR, GtnFrameworkRSConstants.INTEREST_BEARING_BASIS,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_DESIGNATION, GtnFrameworkRSConstants.RS_UDC1,
				GtnFrameworkRSConstants.RS_UDC2, GtnFrameworkRSConstants.RS_UDC3, GtnFrameworkRSConstants.RS_UDC4,
				GtnFrameworkRSConstants.RS_UDC5, GtnFrameworkRSConstants.RS_UDC6 };
		String[] listNameArray = { "RS_TYPE", "STATUS", "REBATE_PROGRAM_TYPE", "RS_CATEGORY", "RS_TRADE_CLASS",
				"REBATE_FREQUENCY", "RS_CALENDAR", "CALCULATION_TYPE", "RULE_LEVEL", "REBATE_RULE_TYPE",
				"PAYMENT_TERMS", "PAYMENT_METHOD", "PAYMENT_FREQUENCY", "INTEREST_BEARING_INDICATOR",
				"INTEREST_BEARING_BASIS", "RS_DESIGNATION", "RS_UDC1", "RS_UDC2", "RS_UDC3", "RS_UDC4", "RS_UDC5",
				"RS_UDC6" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig rsLandingScreenCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			rsLandingScreenCustomFilterConfig.setPropertId(propertyIds[i]);
			rsLandingScreenCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig rsLandingScreenCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			rsLandingScreenCustomFilterComponentConfig.setComponentId("customFilterComboBox");
			rsLandingScreenCustomFilterComponentConfig.setComponentName("customFilterComboBox");
			rsLandingScreenCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			rsLandingScreenCustomFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			rsLandingScreenCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			rsLandingScreenCustomFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			rsLandingScreenCustomFilterConfig.setGtnComponentConfig(rsLandingScreenCustomFilterComponentConfig);
			customFilterConfigMap.put(rsLandingScreenCustomFilterConfig.getPropertId(),
					rsLandingScreenCustomFilterConfig);

		}
		return customFilterConfigMap;
	}

	public List<Object> getDefaultFieldValueList() {
		List<Object> defaultFieldValueList = new ArrayList<>();
		List<String> resetComponentIdListTemp = new ArrayList<>();
		List<Object> resetComponentValueListTemp = new ArrayList<>();

		resetComponentIdListTemp.add("RSMassCheck");
		resetComponentIdListTemp.add("RSRebateSetupMassCheck");
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_ID1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_NO1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_NAME);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.PAYMENT_GRACE_PERIOD);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_ID);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.RS_TRANSACTION_REF_ID);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.EVALUATION_RULE_ASSOCIATION);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.CALCULATION_RULE);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUS1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPE1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_PROGRAM_TYPE1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORY1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_TRADE_CLASS);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_DESIGNATION);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.RS_DEDUCTION_INCLUSION);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.RS_CALENDAR);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_FREQUENCY1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.PAYMENT_LEVEL);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.PAYMENT_FREQUENCY);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.PAYMENT_TERMS);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.PAYMENT_METHOD);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.INTEREST_BEARING_BASIS);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.EVALUATION_RULE_LEVEL);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.EVALUATION_RULE_TYPE);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.INTEREST_BEARING_INDICATOR);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.CALCULATION_RULE_LEVEL);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.CALCULATION_TYPE1);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.CALCULATION_LEVEL);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_RULE_TYPE);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_START_DATE);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE);
		resetComponentIdListTemp.add(GtnFrameworkRSConstants.PS_REBATE_SETUP_TAB_RESULT_DATA_TABLE);
		resetComponentIdListTemp.add("RSrightResultTable");
		resetComponentIdListTemp.add("RSleftResultTable");

		resetComponentValueListTemp.add("IFP");
		resetComponentValueListTemp.add("Disable");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);

		defaultFieldValueList.add(resetComponentIdListTemp);
		defaultFieldValueList.add(resetComponentValueListTemp);
		return defaultFieldValueList;
	}

	/**
	 * 
	 * @param componentList
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnExcelButtonlayout", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"rsLandingScreenExcel", true, "gtnExcelButtonlayout", GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider
				.getExcelBtnconfig("Rebate Schedule", true, GtnFrameworkRSConstants.RS_SEARCH_RESULT_TABLE, false);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	public List<Object> getViewDefaultFieldValueList() {
		List<Object> defaultFieldValueList = new ArrayList<>();
		List<String> resetComponentIdListTemp = new ArrayList<>();
		List<Object> resetComponentValueListTemp = new ArrayList<>();

		resetComponentIdListTemp.add("RSMassCheck");
		resetComponentIdListTemp.add("RSRebateSetupMassCheck");

		resetComponentValueListTemp.add("IFP");
		resetComponentValueListTemp.add("Disable");

		defaultFieldValueList.add(resetComponentIdListTemp);
		defaultFieldValueList.add(resetComponentValueListTemp);
		return defaultFieldValueList;
	}
}