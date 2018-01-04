/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnUIFrameWorkDesignationTypeChangeAction;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkRebateScheduleInformationTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addrebateScheduleInfoTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getVerticalLayoutConfig(GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_TAB, false, null);
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setMargin(true);
		layoutConfig.getGtnLayoutConfig().setMargin(true);
		componentList.add(layoutConfig);

		addRebateScheduleInformationPanel(componentList);
		addRebateOptionsPanel(componentList);

	}

	private void addRebateScheduleInformationPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("rebateScheduleInformationPanel", true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_TAB);
		panel.setComponentName("Rebate Schedule Information");
		panel.setAuthorizationIncluded(true);

		componentList.add(panel);
		rebateScheduleInfoInformationLayout(componentList);
	}

	private void addRebateOptionsPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("rebateRateOptionsPanel", true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_TAB);
		panel.setComponentName("Rebate Options");
		panel.setAuthorizationIncluded(true);

		componentList.add(panel);
		rebateScheduleOptionsLayout(componentList);
	}

	private void rebateScheduleInfoInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT, true,
				"rebateScheduleInformationPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);

		layoutConfig.setComponentStyle(styleList);
		componentList.add(layoutConfig);
		addRsInfoFieldComponent(componentList);
	}

	private void rebateScheduleOptionsLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getCssLayoutConfig(GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT, true, "rebateRateOptionsPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);

		layoutConfig.setComponentStyle(styleList);
		componentList.add(layoutConfig);
		addRsOptionsFieldComponent(componentList);
	}

	private void addRsInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		addRebateScheduleId(componentList);
		addRebateScheduleNo(componentList);
		addRebateScheduleName(componentList);
		addRebateScheduleStatus(componentList);
		addRebateScheduleType(componentList);
		addRebateProgramType(componentList);
		addRebateScheduleCategory(componentList);
		addRebateScheduleStartDate(componentList);
		addRebateScheduleEndDate(componentList);
		addRebateScheduleTradeClass(componentList);
		addRebateScheduleDesignation(componentList);
		addRebateScheduleAliasId(componentList);
		addUDC1(componentList);
		addUDC4(componentList);
		addParenRebateScheduleID(componentList);
		addTransactionRefId(componentList);
		addUDC2(componentList);
		addUDC5(componentList);
		addParentRebateScheduleName(componentList);
		addTransactionRefName(componentList);
		addUDC3(componentList);
		addUDC6(componentList);
		addDeductionInclusion(componentList);
	}

	private void addRsOptionsFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		addCalendar(componentList);
		addRebateFrequency(componentList);
		addPaymentLevel(componentList);
		addPaymentFrequency(componentList);
		addPaymentGracePeriod(componentList);
		addPaymentTerms(componentList);
		addPaymentMethod(componentList);
		addInterestBearingBasis(componentList);
		addEvaluationRuleLevel(componentList);
		addEvaluationRuleType(componentList);
		addEvaluationRuleAssociation(componentList);
		addInterestBearingIndicator(componentList);
		addCalculationRuleLevel(componentList);
		addCalculationType(componentList);
		addCalculationRule(componentList);
		addCalculationLevel(componentList);
		addRebateRuleType(componentList);

	}

	private void addRebateScheduleId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ID_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleId1", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_ID_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Rebate Schedule ID");

		companyIdConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		componentList.add(companyIdConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addRebateScheduleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NO_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleNo1", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_NO_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName("Rebate Schedule No");

		companyNoConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addRebateScheduleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleName1", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName("Rebate Schedule Name");

		companyNameConfig.setAddToParent(true);
		companyNameConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyNameConfig);
	}

	private void addRebateScheduleStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUSLAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebateScheduleStatus = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleStatus1", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_STATUSLAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		rebateScheduleStatus.setAuthorizationIncluded(true);
		rebateScheduleStatus.setComponentName("Rebate Schedule Status");

		rebateScheduleStatus
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		componentList.add(rebateScheduleStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateScheduleStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateScheduleStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addRebateScheduleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPELAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebateScheduleType = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleType1", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_TYPELAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		rebateScheduleType.setAuthorizationIncluded(true);
		rebateScheduleType.setComponentName("Rebate Schedule Type");

		rebateScheduleType
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		componentList.add(rebateScheduleType);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("RS_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateScheduleType.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateScheduleType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addRebateProgramType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_PROGRAM_TYPELAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig("rebateProgramType1",
				true, GtnFrameworkRSConstants.REBATE_PROGRAM_TYPELAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentName("Rebate Program Type");
		companyStatus.setAuthorizationIncluded(true);

		componentList.add(companyStatus);
		companyStatus
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("REBATE_PROGRAM_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addRebateScheduleCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORYLAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebateScheduleCategory = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleCategory1", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_CATEGORYLAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		rebateScheduleCategory.setAuthorizationIncluded(true);
		rebateScheduleCategory.setComponentName("Rebate Schedule Category");

		rebateScheduleCategory
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		componentList.add(rebateScheduleCategory);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("RS_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateScheduleCategory.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateScheduleCategory.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addRebateScheduleTradeClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TRADE_CLASSLAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleTradeClass", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_TRADE_CLASSLAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("RS Trade Class");

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("RS_TRADE_CLASS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addRebateScheduleStartDate(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_START_DATE_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleStartDate", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_START_DATE_LAYOUT,
				GtnUIFrameworkComponentType.DATEFIELD);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("Start Date");

		companyIdentifierConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addRebateScheduleEndDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleEndDate", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_END_DATE_LAYOUT,
				GtnUIFrameworkComponentType.DATEFIELD);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("End Date");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addRebateScheduleDesignation(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"PriceScheduleDesignationlayout", true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		gtnLayout.setComponentName("PriceScheduleTypelayout");

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rebateScheduleDesignation = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleDesignation", true, "PriceScheduleDesignationlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		rebateScheduleDesignation.setAuthorizationIncluded(true);
		rebateScheduleDesignation.setComponentName("Rebate Schedule Designation");

		componentList.add(rebateScheduleDesignation);

		GtnUIFrameworkComboBoxConfig rsDesignationType = configProvider.getComboBoxConfig("RS_DESIGNATION",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateScheduleDesignation.setGtnComboboxConfig(rsDesignationType);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rebateScheduleDesignation.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig custoSavemAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnUIFrameWorkDesignationTypeChangeAction.class.getName());
		actionConfigList.add(custoSavemAction);
		rebateScheduleDesignation.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addRebateScheduleAliasId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleAliasId1", true, GtnFrameworkRSConstants.REBATE_SCHEDULE_ALIAS_ID_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("Rebate Schedule Alias Id");

		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		gtnUIFrameworkValidationConfig.setAttachRegxValidatior(true);
		gtnUIFrameworkValidationConfig.setFormatString("^[a-zA-Z0-9@#$*_.-]*$");
		gtnUIFrameworkValidationConfig.setRegxValidationMessage("Allowed Sprcial Characters are @,*,#,.,$,-,_");
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addUDC1(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_UDC1LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("rsUDC1", true,
				GtnFrameworkRSConstants.RS_UDC1LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("UDC1");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("RS_UDC1",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setHasDefaultValue(true);
		companyTypeConfig.setDefaultDesc("UDC1");
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addUDC4(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_UDC4LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("rsUDC4", true,
				GtnFrameworkRSConstants.RS_UDC4LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("UDC4");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("RS_UDC4",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setHasDefaultValue(true);
		companyTypeConfig.setDefaultDesc("UDC4");
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addParenRebateScheduleID(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("parentRebateScheduleIDLayout");
		gtnLayout.setParentComponentId(GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig parentRebateScheduleID = configProvider.getUIFrameworkComponentConfig(
				"parentRebateScheduleID", true, "parentRebateScheduleIDLayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentRebateScheduleID.setAuthorizationIncluded(true);
		parentRebateScheduleID.setComponentName("Parent Rebate Schedule ID");
		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(false);
		parentRebateScheduleID.setGtnTextBoxConfig(textboxConfig);
		parentRebateScheduleID
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.SEARCHICON }));
		componentList.add(parentRebateScheduleID);

		GtnUIFrameWorkActionConfig popupActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter(GtnFrameworkRSConstants.RS_POPUP_VIEW);
		popupActionConfig.addActionParameter("Parent Rebate Schedule Lookup");
		popupActionConfig.addActionParameter("70%");
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(
				Arrays.asList(parentRebateScheduleID.getComponentId(), Arrays.asList("rsNo", "rsName"),
						Arrays.asList("parentRebateScheduleID", "parentRebateScheduleName"), null));
		parentRebateScheduleID.addGtnUIFrameWorkActionConfig(popupActionConfig);

	}

	private void addTransactionRefId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("rsTransactionRefIdLayout");
		gtnLayout.setParentComponentId(GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig parentCompanyNo = configProvider.getUIFrameworkComponentConfig(
				"rsTransactionRefId", true, "rsTransactionRefIdLayout", GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		parentCompanyNo.setComponentName("Transaction Ref ID");
		parentCompanyNo.setAuthorizationIncluded(true);

		parentCompanyNo.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.SEARCHICON }));
		componentList.add(parentCompanyNo);

		GtnUIFrameWorkActionConfig popupActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter(GtnFrameworkRSConstants.RS_POPUP_VIEW);
		popupActionConfig.addActionParameter("Rebate Schedule Reference No");
		popupActionConfig.addActionParameter("70%");
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(Arrays.asList(parentCompanyNo.getComponentId(),
				Arrays.asList("rsNo", "rsName"), Arrays.asList("rsTransactionRefId", "rsTransactionRefName"), null));
		parentCompanyNo.addGtnUIFrameWorkActionConfig(popupActionConfig);

	}

	private void addUDC2(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_UDC2LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("rsUDC2", true,
				GtnFrameworkRSConstants.RS_UDC2LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("UDC2");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("RS_UDC2",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setHasDefaultValue(true);
		companyTypeConfig.setDefaultDesc("UDC2");
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addUDC5(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_UDC5LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("rsUDC5", true,
				GtnFrameworkRSConstants.RS_UDC5LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("UDC5");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("RS_UDC5",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setHasDefaultValue(true);
		companyTypeConfig.setDefaultDesc("UDC5");
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addParentRebateScheduleName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_NAME_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"parentRebateScheduleName", true, GtnFrameworkRSConstants.PARENT_REBATE_SCHEDULE_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("Parent Rebate Schedule Name");

		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(false, false, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addTransactionRefName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"rsTransactionRefName", true, GtnFrameworkRSConstants.RS_TRANSACTION_REF_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName("RS Transaction Ref Name");

		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(false, false, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addUDC3(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_UDC3LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("rsUDC3", true,
				GtnFrameworkRSConstants.RS_UDC3LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("UDC3");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("RS_UDC3",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setHasDefaultValue(true);
		companyTypeConfig.setDefaultDesc("UDC3");
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addUDC6(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_UDC6LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("rsUDC6", true,
				GtnFrameworkRSConstants.RS_UDC6LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("UDC6");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("RS_UDC6",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setHasDefaultValue(true);
		companyTypeConfig.setDefaultDesc("UDC6");
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addDeductionInclusion(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_DEDUCTION_INCLUSIONLAYOUT, true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_INFO_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rsDeductionInclusion = configProvider.getUIFrameworkComponentConfig("rsDeductionInclusion",
				true, GtnFrameworkRSConstants.RS_DEDUCTION_INCLUSIONLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		rsDeductionInclusion.setComponentName("Deduction Inclusion");
		rsDeductionInclusion.setAuthorizationIncluded(true);

		rsDeductionInclusion
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));

		componentList.add(rsDeductionInclusion);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("LOCKED_STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rsDeductionInclusion.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rsDeductionInclusion.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addCalendar(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_CALENDARLAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("rsCalendar", true,
				GtnFrameworkRSConstants.RS_CALENDARLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Calendar");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("RS_CALENDAR",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addRebateFrequency(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_FREQUENCYLAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("rebateFrequency1",
				true, GtnFrameworkRSConstants.REBATE_FREQUENCYLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Rebate Frequency");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("REBATE_FREQUENCY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addPaymentLevel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.PAYMENT_LEVELLAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("paymentLevel", true,
				GtnFrameworkRSConstants.PAYMENT_LEVELLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Payment Level");
                companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("PAYMENT_LEVEL",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addPaymentFrequency(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.PAYMENT_FREQUENCYLAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig paymentFrequency = configProvider.getUIFrameworkComponentConfig("paymentFrequency",
				true, GtnFrameworkRSConstants.PAYMENT_FREQUENCYLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		paymentFrequency.setComponentName("Payment Frequency");
		paymentFrequency.setAuthorizationIncluded(true);

		paymentFrequency
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));

		componentList.add(paymentFrequency);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("PAYMENT_FREQUENCY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		paymentFrequency.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		paymentFrequency.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addPaymentGracePeriod(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.PAYMENT_GRACE_PERIOD_LAYOUT, true,
				GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"paymentGracePeriod", true, GtnFrameworkRSConstants.PAYMENT_GRACE_PERIOD_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setComponentName("Payment Grace Period");
		companyIdentifierConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		gtnUIFrameworkValidationConfig.setAttachRegxValidatior(true);
		gtnUIFrameworkValidationConfig.setFormatString(GtnFrameworkRegexStringConstants.NUMERIC_FORMAT);
		gtnUIFrameworkValidationConfig.setRegxValidationMessage("Value can contain only numbers.");
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addPaymentTerms(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.PAYMENT_TERMSLAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("paymentTerms", true,
				GtnFrameworkRSConstants.PAYMENT_TERMSLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Payment Terms");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("PAYMENT_TERMS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addPaymentMethod(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.PAYMENT_METHODLAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("paymentMethod", true,
				GtnFrameworkRSConstants.PAYMENT_METHODLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Payment Method");
		companyType.setAuthorizationIncluded(true);

		companyType
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("PAYMENT_METHOD",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addInterestBearingBasis(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.INTEREST_BEARING_BASISLAYOUT, true,
				GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);

		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("interestBearingBasis",
				true, GtnFrameworkRSConstants.INTEREST_BEARING_BASISLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Interest Bearing Basis");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("INTEREST_BEARING_BASIS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addEvaluationRuleLevel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.EVALUATION_RULE_LEVELLAYOUT, true,
				GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("evaluationRuleLevel",
				true, GtnFrameworkRSConstants.EVALUATION_RULE_LEVELLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Evaluation Rule Level");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig(
				"Evaluation_Rule_Level", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addEvaluationRuleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.EVALUATION_RULE_TYPELAYOUT, true,
				GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("evaluationRuleType",
				true, GtnFrameworkRSConstants.EVALUATION_RULE_TYPELAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Evaluation Rule Type");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("EVALUATION_RULE_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addEvaluationRuleAssociation(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.EVALUATION_RULE_ASSOCIATIONLAYOUT, true,
				GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig evaluationRuleConfig = configProvider.getUIFrameworkComponentConfig(
				"evaluationRuleAssociation", true, GtnFrameworkRSConstants.EVALUATION_RULE_ASSOCIATIONLAYOUT,
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		evaluationRuleConfig.setAuthorizationIncluded(true);
		evaluationRuleConfig.setComponentName("Evaluation Rule/Association");
		evaluationRuleConfig.setAddToParent(true);

		evaluationRuleConfig.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.SEARCHICON }));
		componentList.add(evaluationRuleConfig);

		GtnUIFrameWorkActionConfig popupActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter(GtnFrameworkRSConstants.RS_NS_RULE_VIEW);
		popupActionConfig.addActionParameter("Evaluation Rule Lookup");
		popupActionConfig.addActionParameter("70%");
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(Arrays.asList(evaluationRuleConfig.getComponentId(),
				Arrays.asList("ruleNo"), Arrays.asList(evaluationRuleConfig.getComponentId()), null, "Evaluation"));
		evaluationRuleConfig.addGtnUIFrameWorkActionConfig(popupActionConfig);
	}

	private void addInterestBearingIndicator(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.INTEREST_BEARING_INDICATORLAYOUT, true,
				GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"interestBearingIndicator", true, GtnFrameworkRSConstants.INTEREST_BEARING_INDICATORLAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("Interest Bearing Indicator");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("INTEREST_BEARING_INDICATOR",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addCalculationRuleLevel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.CALCULATION_RULE_LEVELLAYOUT, true,
				GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("calculationRuleLevel",
				true, GtnFrameworkRSConstants.CALCULATION_RULE_LEVELLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Calculation Rule Level");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig(
				"Calculation_Rule_Level", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addCalculationType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.CALCULATION_TYPELAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("calculationType1",
				true, GtnFrameworkRSConstants.CALCULATION_TYPELAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Calculation  Type");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("CALCULATION_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig custoSavemAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkRSConstants.CALC_TYPE_CHANGE_ACTION);

		actionConfigList.add(custoSavemAction);
		companyType.setGtnUIFrameWorkActionConfigList(actionConfigList);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addCalculationRule(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.CALCULATION_RULELAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig calulationRule = new GtnUIFrameworkComponentConfig();
		calulationRule.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		calulationRule.setAuthorizationIncluded(true);
		calulationRule.setComponentId("calculationRule");
		calulationRule.setComponentName("Calculation Rule");
		calulationRule.setParentComponentId(GtnFrameworkRSConstants.CALCULATION_RULELAYOUT);
		calulationRule.setAddToParent(true);

		calulationRule.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.SEARCHICON }));
		componentList.add(calulationRule);

		GtnUIFrameWorkActionConfig popupActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter(GtnFrameworkRSConstants.RS_NS_RULE_VIEW);
		popupActionConfig.addActionParameter("Calculation Rule Lookup");
		popupActionConfig.addActionParameter("70%");
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(null);
		popupActionConfig.addActionParameter(Arrays.asList(calulationRule.getComponentId(), Arrays.asList("ruleNo"),
				Arrays.asList(calulationRule.getComponentId()), null, "Calculation"));
		calulationRule.addGtnUIFrameWorkActionConfig(popupActionConfig);
	}

	private void addCalculationLevel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.CALCULATION_LEVELLAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("calculationLevel",
				true, GtnFrameworkRSConstants.CALCULATION_LEVELLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Calculation Level");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("CALCULATION_LEVEL",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

	private void addRebateRuleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.REBATE_RULE_TYPELAYOUT, true, GtnFrameworkRSConstants.REBATE_OPTIONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig("rebateRuleType", true,
				GtnFrameworkRSConstants.REBATE_RULE_TYPELAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Rebate Rule Type");
		companyType.setAuthorizationIncluded(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("REBATE_RULE_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
	}

}
