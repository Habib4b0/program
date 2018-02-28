package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnFrameworkRebatePlanCalculationAddButtonAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkLoadValueComboBox;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkResetYesButtonAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameworkFromToBlurAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkRPConstants;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkRebatePlanCalculationTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addCalculationTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpCalculationTabMainLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TAB, false, null);
		rpCalculationTabMainLayout.setTabComponent(true);
		rpCalculationTabMainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(rpCalculationTabMainLayout);

	        GtnUIFrameworkComponentConfig rpCalculationTabPanelConfig = configProvider.getPanelConfig(GtnFrameworkCommonConstants.RP_CALC_TAB_MAIN_PANEL, true, GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TAB);
		rpCalculationTabPanelConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
                componentList.add(rpCalculationTabPanelConfig);
                GtnUIFrameworkComponentConfig rpCalculationTabMainCssConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_MAIN_CSS_LAYOUT, true, GtnFrameworkCommonConstants.RP_CALC_TAB_MAIN_PANEL);
		List<String> rpCalculationTabStyles = new ArrayList<>();
		rpCalculationTabStyles.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rpCalculationTabStyles.add(GtnFrameworkCssConstants.NO_MARGIN);
		rpCalculationTabMainCssConfig.setComponentStyle(rpCalculationTabStyles);
		componentList.add(rpCalculationTabMainCssConfig);

		GtnUIFrameworkComponentConfig rpCalculationTabLeftCssLayoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_TAB, true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_MAIN_CSS_LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_3);
		styleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		rpCalculationTabLeftCssLayoutConfig.setComponentStyle(styleList);
		componentList.add(rpCalculationTabLeftCssLayoutConfig);

		GtnUIFrameworkComponentConfig rpCalculationTabRightCssLayoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_LAYOUT, true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_MAIN_CSS_LAYOUT);
		List<String> styleLists = new ArrayList<>();
		styleLists.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_5);
		styleLists.add(GtnFrameworkCssConstants.NO_MARGIN);
		rpCalculationTabRightCssLayoutConfig.setComponentStyle(styleLists);
		componentList.add(rpCalculationTabRightCssLayoutConfig);

		GtnUIFrameworkComponentConfig cssLayoutConfig3 = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_COMPLEX_TABLE_CSSLAYOUT, true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_MAIN_CSS_LAYOUT);
		cssLayoutConfig3.setVisible(false);
		List<String> styleList1 = new ArrayList<>();
		styleList1.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		styleList1.add(GtnFrameworkCssConstants.NO_MARGIN);
		cssLayoutConfig3.setComponentStyle(styleList1);
		componentList.add(cssLayoutConfig3);
		calculationLayout(componentList);
		addActionButtonLayout(componentList);

	}

	public void calculationLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		ruleInformationPanel(componentList);
		ruleDetailsInformationLayoutSimple(componentList);
		ruleDetailsResultDataTableComplex(componentList);
		ruleDetailsResultDataTableSimple(componentList);

	}

	private void ruleInformationPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpCalculationTabRuleInformationPanel = configProvider.getVerticalLayoutConfig(
				"ruleInformationMainLayout", true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_TAB);
		rpCalculationTabRuleInformationPanel.setAuthorizationIncluded(true);
		rpCalculationTabRuleInformationPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(rpCalculationTabRuleInformationPanel);

		ruleInformationLayout(componentList);

	}

	private void ruleInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpCalculationTabRuleInfoLayout = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT, true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_TAB);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		rpCalculationTabRuleInfoLayout.setComponentStyle(styleList);
		componentList.add(rpCalculationTabRuleInfoLayout);
		ruleInformationFields(componentList);
	}

	private void ruleInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {

		addRebateStructure(componentList);
		addRangeBasedOn(componentList);
		addNetSalesFormula(componentList);
		addNetSalesRule(componentList);
		addRebateBasedOn(componentList);
		addSelfGrowthIndicator(componentList);
		addSelfGrowthReference(componentList);
		addSelfGrowthFrom(componentList);
		addSelfGrowthTo(componentList);
		addMarketShareIndicator(componentList);
		addMarketShareReference(componentList);
		addMarketShareFrom(componentList);
		addMarketShareTo(componentList);
	}

	private void ruleDetailsInformationLayoutSimple(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig ruleDetailsInformationLayoutSimple = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT, true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_LAYOUT);
                ruleDetailsInformationLayoutSimple.setComponentWidth("500px");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		ruleDetailsInformationLayoutSimple.setComponentStyle(styleList);
		componentList.add(ruleDetailsInformationLayoutSimple);
		ruleDetailsInformationLayoutComplex(componentList);
	}

	private void ruleDetailsInformationLayoutComplex(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig ruleDetailsInformationFromToLayoutComplex = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_FROM_TO_COMPLEX, true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_LAYOUT);
		ruleDetailsInformationFromToLayoutComplex
				.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4,
						GtnFrameworkCssConstants.STPL_PADDING_BOTTOM_20, GtnFrameworkCssConstants.STPL_PADDING_TOP_20));
		ruleDetailsInformationFromToLayoutComplex.setVisible(false);
		componentList.add(ruleDetailsInformationFromToLayoutComplex);

		GtnUIFrameworkComponentConfig ruleDetailsInformationLayoutComplex = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX, true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		ruleDetailsInformationLayoutComplex.setComponentStyle(styleList);
		ruleDetailsInformationLayoutComplex.setVisible(false);
		componentList.add(ruleDetailsInformationLayoutComplex);
		tierDetailsInformationFields(componentList);
	}

	private void tierDetailsInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addFrom(componentList);
		addTo(componentList);
		addOperator(componentList);
		addValue(componentList);
		addComplexFrom(componentList);
		addComplexTo(componentList);
		addComplexOperator(componentList);
		addComplexValue(componentList);
		addOperatorType(componentList);
		addAdjustmentOperator1(componentList);
		addAdjustmentValue(componentList);
		addOperatorType2(componentList);
		addAdjustmentOperator2(componentList);
		addAdjustmentValue2(componentList);
		addOperatorType3(componentList);
		addAdjustmentOperator3(componentList);
		addAdjustmentValue3(componentList);
		addOperatorType4(componentList);
		addAdjustmentOperator4(componentList);
		addAdjustmentValue4(componentList);
		addOperatorType5(componentList);
		addAdjustmentOperator5(componentList);
		addAdjustmentValue5(componentList);
	}

	private void ruleDetailsResultDataTableComplex(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rpRuleDetailsResultDataTable = configProvider.getUIFrameworkComponentConfig(
				"ruleDetailsattachResultTableComplex", true,
				GtnFrameworkCommonConstants.REBATE_PLAN_COMPLEX_TABLE_CSSLAYOUT, GtnUIFrameworkComponentType.DATATABLE);
		rpRuleDetailsResultDataTable.setAuthorizationIncluded(true);
		rpRuleDetailsResultDataTable.setComponentName("Results");
		rpRuleDetailsResultDataTable.setComponentHight("500px");

		rpRuleDetailsResultDataTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(rpRuleDetailsResultDataTable);

		GtnUIFrameworkValidationConfig rpRuleDetailsResultTableValidationConfig = new GtnUIFrameworkValidationConfig();
		rpRuleDetailsResultTableValidationConfig.setMinSize(1);
		rpRuleDetailsResultDataTable.setGtnUIFrameworkValidationConfig(rpRuleDetailsResultTableValidationConfig);

		GtnUIFrameworkPagedTableConfig ruleDetailsResultDataTableConfig = configProvider.getPagedTableConfig(true, true,
				null, null, "", "");
		ruleDetailsResultDataTableConfig.setEditable(false);
		ruleDetailsResultDataTableConfig
				.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
						GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		ruleDetailsResultDataTableConfig.setTableVisibleHeader(
				new String[] { "From", "To", GtnFrameworkCommonConstants.OPERATOR, GtnFrameworkCommonConstants.VALUE,
						GtnFrameworkCommonConstants.OPERATOR_TYPE, "Adjustment Operator 1", "Adjustment Value 1",
						GtnFrameworkCommonConstants.OPERATOR_TYPE, "Adjustment Operator 2", "Adjustment Value 2",
						GtnFrameworkCommonConstants.OPERATOR_TYPE, "Adjustment Operator 3", "Adjustment Value 3",
						GtnFrameworkCommonConstants.OPERATOR_TYPE, "Adjustment Operator 4", "Adjustment Value 4",
						GtnFrameworkCommonConstants.OPERATOR_TYPE, "Adjustment Operator 5", "Adjustment Value 5" });
		ruleDetailsResultDataTableConfig.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.TIER_FROM,
				GtnFrameworkCommonConstants.TIER_TO, GtnFrameworkCommonConstants.TIER_OPERATOR,
				GtnFrameworkCommonConstants.TIER_VALUE, "operatorType", "adjustmentOperator1", "adjustmentValue1",
				"operatorType2", "adjustmentOperator2", "adjustmentValue2", "operatorType3", "adjustmentOperator3",
				"adjustmentValue3", "operatorType4", "adjustmentOperator4", "adjustmentValue4", "operatorType5",
				"adjustmentOperator5", "adjustmentValue5" });
		ruleDetailsResultDataTableConfig.setItemClickListener(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig rpRuleDetailsResultCustomAction = new GtnUIFrameWorkActionConfig();
		rpRuleDetailsResultCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rpRuleDetailsResultCustomAction.addActionParameter(GtnFrameworkRPConstants.TIER_RESULT_TABLE_ITEM_SELECTED);
		rpRuleDetailsResultCustomAction.addActionParameter("ruleDetailsattachResultTableComplex");
		actionConfigList.add(rpRuleDetailsResultCustomAction);
		ruleDetailsResultDataTableConfig.setItemClickActionConfigList(actionConfigList);
		rpRuleDetailsResultDataTable.setGtnPagedTableConfig(ruleDetailsResultDataTableConfig);

	}

	private void ruleDetailsResultDataTableSimple(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig ruleDetailsResultDataTable = configProvider.getUIFrameworkComponentConfig(
				"ruleDetailsattachResultTable", true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_LAYOUT,
				GtnUIFrameworkComponentType.DATATABLE);
		ruleDetailsResultDataTable.setAuthorizationIncluded(true);
		ruleDetailsResultDataTable.setComponentName("Results");
		ruleDetailsResultDataTable.setComponentHight("400px");
		ruleDetailsResultDataTable.setComponentWidth("500px");
		componentList.add(ruleDetailsResultDataTable);

		GtnUIFrameworkValidationConfig ruleDetailsResultTableValidationConfig = new GtnUIFrameworkValidationConfig();
		ruleDetailsResultTableValidationConfig.setMinSize(1);
		ruleDetailsResultDataTable.setGtnUIFrameworkValidationConfig(ruleDetailsResultTableValidationConfig);

		GtnUIFrameworkPagedTableConfig ruleDetailsResultDataTableConfig = configProvider.getPagedTableConfig(true, true,
				null, null, "", "");
		ruleDetailsResultDataTableConfig.setEditable(false);
		ruleDetailsResultDataTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVALANG_DOUBLE, GtnFrameworkCommonConstants.JAVALANG_DOUBLE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		ruleDetailsResultDataTableConfig.setTableVisibleHeader(
				new String[] { "From", "To", GtnFrameworkCommonConstants.OPERATOR, GtnFrameworkCommonConstants.VALUE });
		ruleDetailsResultDataTableConfig.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.TIER_FROM, GtnFrameworkCommonConstants.TIER_TO,
						GtnFrameworkCommonConstants.TIER_OPERATOR, GtnFrameworkCommonConstants.TIER_VALUE });
		ruleDetailsResultDataTableConfig.setItemClickListener(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkRPConstants.TIER_RESULT_TABLE_ITEM_SELECTED);
		customAction.addActionParameter("ruleDetailsattachResultTable");
		actionConfigList.add(customAction);
		ruleDetailsResultDataTableConfig.setItemClickActionConfigList(actionConfigList);
		ruleDetailsResultDataTable.setGtnPagedTableConfig(ruleDetailsResultDataTableConfig);

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rpCalculationTabActionBtnLayout = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, true,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_LAYOUT);
		rpCalculationTabActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(rpCalculationTabActionBtnLayout);
		addAddButtonComponent(componentList);
		addRemoveButtonComponent(componentList);
		addResetButtonComponent(componentList);
		addRemoveComplexButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rpCalculationTabAddBtn = configProvider.getUIFrameworkComponentConfig(
				"gtnRpAddButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		rpCalculationTabAddBtn.setAuthorizationIncluded(true);
		rpCalculationTabAddBtn.setComponentName("Add");
		componentList.add(rpCalculationTabAddBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = new GtnUIFrameWorkActionConfig();
		customActionrRuleNameExist.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnFrameworkRPConstants.MANDATORY_CHECK);
		actionConfigList.add(customActionrRuleNameExist);

		GtnUIFrameWorkActionConfig addBtnAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addBtnAction.addActionParameter(GtnFrameworkRebatePlanCalculationAddButtonAction.class.getName());
		addBtnAction.addActionParameter(
				Arrays.asList(GtnFrameworkCommonConstants.TIER_FROM, GtnFrameworkCommonConstants.TIER_TO,
						GtnFrameworkCommonConstants.TIER_OPERATOR, GtnFrameworkCommonConstants.TIER_VALUE));
		addBtnAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE));
		addBtnAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.TIER_FROM,
				GtnFrameworkCommonConstants.TIER_TO, GtnFrameworkCommonConstants.TIER_OPERATOR,
				GtnFrameworkCommonConstants.TIER_VALUE, "operatorType", "adjustmentOperator1", "adjustmentValue1",
				"operatorType2", "adjustmentOperator2", "adjustmentValue2", "operatorType3", "adjustmentOperator3",
				"adjustmentValue3", "operatorType4", "adjustmentOperator4", "adjustmentValue4", "operatorType5",
				"adjustmentOperator5", "adjustmentValue5"));
		addBtnAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR_COMPLEX,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE_COMPLEX,
				"rebatePlanCalculationsOperatorType",
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR1,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE,
				"rebatePlanCalculationsOperatorType2",
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR2,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE2,
				"rebatePlanCalculationsOperatorType3",
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR3,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE3,
				"rebatePlanCalculationsOperatorType4",
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR4,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE4,
				"rebatePlanCalculationsOperatorType5",
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR5,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE5));

		addBtnAction.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_FORMULA_TYPE);

		actionConfigList.add(addBtnAction);

		rpCalculationTabAddBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rpCalculationTabRemoveBtn = configProvider.getUIFrameworkComponentConfig(
				"gtnRpRemoveButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		rpCalculationTabRemoveBtn.setAuthorizationIncluded(true);
		rpCalculationTabRemoveBtn.setComponentName("Remove");
		componentList.add(rpCalculationTabRemoveBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig removeConfirmation = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeConfirmation.addActionParameter(GtnFrameworkRPConstants.REMOVE_CONFIRMATION);
		removeConfirmation.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_FORMULA_TYPE);
		actionConfigList.add(removeConfirmation);

		rpCalculationTabRemoveBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rpCalculationTabResetBtn = configProvider.getUIFrameworkComponentConfig(
				"gtnRPResetButton", true, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		rpCalculationTabResetBtn.setAuthorizationIncluded(true);
		rpCalculationTabResetBtn.setComponentName("Reset");
		componentList.add(rpCalculationTabResetBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetTierTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		resetTierTableActionConfig.addActionParameter("Reset Confirmation");
		resetTierTableActionConfig
				.addActionParameter("Are you sure you want to reset the listview to default/previous values?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig yesActionConfig = new GtnUIFrameWorkActionConfig();
		yesActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		yesActionConfig.addActionParameter(GtnUIFrameWorkResetYesButtonAction.class.getName());
		yesActionConfig.addActionParameter("TIER_TABLE_RESET");
		onSucessActionConfigList.add(yesActionConfig);
		resetTierTableActionConfig.addActionParameter(onSucessActionConfigList);
		actionConfigList.add(resetTierTableActionConfig);

		rpCalculationTabResetBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addRebateStructure(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rpCalculationTabRebateStructureLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkStringConstants.REBATE_PLAN_CALCULATIONS_REBATE_STRUCTURE_LAYOUT, true,
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(rpCalculationTabRebateStructureLayout);

		GtnUIFrameworkComponentConfig rebateStructure = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsRebateStructure", true,
				GtnFrameworkStringConstants.REBATE_PLAN_CALCULATIONS_REBATE_STRUCTURE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		rebateStructure.setAuthorizationIncluded(true);
		rebateStructure.setComponentName("Rebate Structure");
		rebateStructure.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		componentList.add(rebateStructure);

		GtnUIFrameworkComboBoxConfig rebateStructureConfig = configProvider.getComboBoxConfig("REBATE_STRUCTURE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateStructure.setGtnComboboxConfig(rebateStructureConfig);
	}

	private void addRangeBasedOn(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rangeBasedOnLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsRangeBasedOnLayout", true, GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		rangeBasedOnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(rangeBasedOnLayout);

		GtnUIFrameworkComponentConfig rangeBasedOn = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsRangeBasedOn", true, "rebatePlanCalculationsRangeBasedOnLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		rangeBasedOn.setAuthorizationIncluded(true);
		rangeBasedOn.setComponentName("Range Based On");
		rangeBasedOn.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		componentList.add(rangeBasedOn);

		GtnUIFrameworkComboBoxConfig rangeBasedOnConfig = configProvider.getComboBoxConfig("REBATE_RANGE_BASED_ON",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rangeBasedOn.setGtnComboboxConfig(rangeBasedOnConfig);
	}

	private void addNetSalesFormula(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesFormulaLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsNetSalesFormulaLayout", true,
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(netSalesFormulaLayout);

		GtnUIFrameworkComponentConfig netSalesFormula = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsNetSalesFormula", true, "rebatePlanCalculationsNetSalesFormulaLayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		netSalesFormula.setAuthorizationIncluded(true);
		netSalesFormula.setComponentName("Net Sales Formula");
		netSalesFormula.addComponentStyle(GtnFrameworkCommonConstants.SEARCHICON);
		componentList.add(netSalesFormula);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("netSalesFormulaPopUpView");
		popupActionParam.add("Net Sales Formula Lookup");
		popupActionParam.add("60%");
		popupActionConfig.setActionParameterList(popupActionParam);

		netSalesFormula.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addNetSalesRule(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig netSalesRuleLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsNetSalesRuleLayout", true, GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(netSalesRuleLayout);

		GtnUIFrameworkComponentConfig netSalesRule = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsNetSalesRule", true, "rebatePlanCalculationsNetSalesRuleLayout",
				GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		netSalesRule.setAuthorizationIncluded(true);
		netSalesRule.setComponentName("Net Sales Rule");
		netSalesRule.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.SEARCHICON));

		componentList.add(netSalesRule);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		actionConfigList.add(popupActionConfig);
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		List<Object> popupActionParam = new ArrayList<>();
		popupActionParam.add("CDRPopUpSearchSearchView");
		popupActionParam.add("Net Sales Rule Lookup");
		popupActionParam.add("60%");

		popupActionConfig.setActionParameterList(popupActionParam);

		netSalesRule.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addRebateBasedOn(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rebateBasedOnLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsRebateBasedOnLayout", true, GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(rebateBasedOnLayout);

		GtnUIFrameworkComponentConfig rebateBasedOn = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsRebateBasedOn", true, "rebatePlanCalculationsRebateBasedOnLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		rebateBasedOn.setAuthorizationIncluded(true);
		rebateBasedOn.setComponentName("Rebate Based On");
		rebateBasedOn.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		componentList.add(rebateBasedOn);

		GtnUIFrameworkComboBoxConfig rebateBasedOnConfig = configProvider.getComboBoxConfig("REBATE_BASED_ON",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		rebateBasedOn.setGtnComboboxConfig(rebateBasedOnConfig);
	}

	private void addSelfGrowthIndicator(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig selfGrowthIndicatorLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationSelfGrowthIndicatorLayout", true,
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(selfGrowthIndicatorLayout);

		GtnUIFrameworkComponentConfig selfGrowthIndicator = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationSelfGrowthIndicator", true, "rebatePlanCalculationSelfGrowthIndicatorLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		selfGrowthIndicator.setAuthorizationIncluded(true);
		selfGrowthIndicator.setComponentName("Self Growth Indicator");
		GtnUIFrameworkTextBoxConfig selfGrowthIndicatorConfig = configProvider.getTextBoxConfig(true, true, true);
		selfGrowthIndicator.setGtnTextBoxConfig(selfGrowthIndicatorConfig);

		GtnUIFrameworkValidationConfig selfGrowthIndicatorValConfig = configProvider.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), true,
				"Self Growth Indicator should be less than 1 or 1 Characters",
				GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		selfGrowthIndicatorValConfig.setAttachLengthValidatior(true);
		selfGrowthIndicatorValConfig.setMinSize(0);
		selfGrowthIndicatorValConfig.setMaxLength(1);
		selfGrowthIndicatorValConfig
				.setLengthValidationMessage(GtnFrameworkCommonConstants.MARKET_SHARE_INDICATOR_SHOULD_BE_LESS_THA);
		selfGrowthIndicator.setGtnUIFrameworkValidationConfig(selfGrowthIndicatorValConfig);

		componentList.add(selfGrowthIndicator);
	}

	private void addSelfGrowthReference(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig selfGrowthReferenceLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationSelfGrowthReferenceLayout", true,
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(selfGrowthReferenceLayout);

		GtnUIFrameworkComponentConfig selfGrowthReference = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationSelfGrowthReference", true, "rebatePlanCalculationSelfGrowthReferenceLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		selfGrowthReference.setAuthorizationIncluded(true);
		selfGrowthReference.setComponentName("Self Growth Reference");

		GtnUIFrameworkTextBoxConfig selfGrowthReferenceConfig = configProvider.getTextBoxConfig(true, true, true);
		selfGrowthReference.setGtnTextBoxConfig(selfGrowthReferenceConfig);

		componentList.add(selfGrowthReference);

	}

	private void addMarketShareIndicator(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig marketShareIndicatorLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationMarketShareIndicatorLayout", true,
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(marketShareIndicatorLayout);

		GtnUIFrameworkComponentConfig marketShareIndicator = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationMarketShareIndicator", true, "rebatePlanCalculationMarketShareIndicatorLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		marketShareIndicator.setAuthorizationIncluded(true);
		marketShareIndicator.setComponentName("Market Share Indicator");

		GtnUIFrameworkTextBoxConfig marketShareIndicatorConfig = configProvider.getTextBoxConfig(true, true, true);
		marketShareIndicator.setGtnTextBoxConfig(marketShareIndicatorConfig);

		GtnUIFrameworkValidationConfig marketShareIndicatorValConfig = configProvider.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), true,
				GtnFrameworkCommonConstants.MARKET_SHARE_INDICATOR_SHOULD_BE_LESS_THA,
				GtnFrameworkRegexStringConstants.ALPHANUMERIC_FORMAT);
		marketShareIndicatorValConfig.setAttachLengthValidatior(true);
		marketShareIndicatorValConfig.setMinSize(0);
		marketShareIndicatorValConfig.setMaxLength(1);
		marketShareIndicatorValConfig
				.setLengthValidationMessage(GtnFrameworkCommonConstants.MARKET_SHARE_INDICATOR_SHOULD_BE_LESS_THA);
		marketShareIndicator.setGtnUIFrameworkValidationConfig(marketShareIndicatorValConfig);
		componentList.add(marketShareIndicator);
	}

	private void addMarketShareReference(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig marketShareReferenceLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationMarketShareReferenceLayout", true,
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(marketShareReferenceLayout);

		GtnUIFrameworkComponentConfig marketShareReference = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationMarketShareReference", true, "rebatePlanCalculationMarketShareReferenceLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		marketShareReference.setAuthorizationIncluded(true);
		marketShareReference.setComponentName("Market Share Reference");

		GtnUIFrameworkTextBoxConfig marketShareReferenceConfig = configProvider.getTextBoxConfig(true, true, true);
		marketShareReference.setGtnTextBoxConfig(marketShareReferenceConfig);

		componentList.add(marketShareReference);

	}

	private void addSelfGrowthFrom(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig selfGrowthFromLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationSelfGrowthFromLayout", true, GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(selfGrowthFromLayout);

		GtnUIFrameworkComponentConfig selfGrowthFrom = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationSelfGrowthFrom", true, "rebatePlanCalculationSelfGrowthFromLayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		selfGrowthFrom.setAuthorizationIncluded(true);
		selfGrowthFrom.setComponentName("Self Growth From");

		GtnUIFrameworkDateFieldConfig selfGrowthFromConfig = new GtnUIFrameworkDateFieldConfig();
		selfGrowthFromConfig.setEnable(true);
		selfGrowthFromConfig.setRequired(true);
		selfGrowthFromConfig.setImmediate(true);
		selfGrowthFrom.setGtnDateFieldConfig(selfGrowthFromConfig);

		GtnUIFrameworkValidationConfig selfGrowthFromValidationConfig = configProvider.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		selfGrowthFrom.setGtnUIFrameworkValidationConfig(selfGrowthFromValidationConfig);
		componentList.add(selfGrowthFrom);

	}

	private void addSelfGrowthTo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig selfGrowthToLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationSelfGrowthToLayout", true, GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(selfGrowthToLayout);

		GtnUIFrameworkComponentConfig selfGrowthTo = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationSelfGrowthTo", true, "rebatePlanCalculationSelfGrowthToLayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		selfGrowthTo.setAuthorizationIncluded(true);
		selfGrowthTo.setComponentName("Self Growth To");

		GtnUIFrameworkDateFieldConfig selfGrowthToConfig = new GtnUIFrameworkDateFieldConfig();
		selfGrowthToConfig.setEnable(true);
		selfGrowthToConfig.setRequired(true);
		selfGrowthToConfig.setImmediate(true);
		selfGrowthTo.setGtnDateFieldConfig(selfGrowthToConfig);

		GtnUIFrameworkValidationConfig selfGrowthToValidationConfig = configProvider.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		selfGrowthTo.setGtnUIFrameworkValidationConfig(selfGrowthToValidationConfig);
		componentList.add(selfGrowthTo);

	}

	private void addMarketShareFrom(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig marketShareFromLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationMarketShareFromLayout", true,
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(marketShareFromLayout);

		GtnUIFrameworkComponentConfig marketShareFrom = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationMarketShareFrom", true, "rebatePlanCalculationMarketShareFromLayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		marketShareFrom.setAuthorizationIncluded(true);
		marketShareFrom.setComponentName("Market Share From");

		GtnUIFrameworkDateFieldConfig marketShareFromConfig = new GtnUIFrameworkDateFieldConfig();
		marketShareFromConfig.setEnable(true);
		marketShareFromConfig.setRequired(true);
		marketShareFromConfig.setImmediate(true);
		marketShareFrom.setGtnDateFieldConfig(marketShareFromConfig);

		GtnUIFrameworkValidationConfig marketShareFromValidationConfig = configProvider.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		marketShareFrom.setGtnUIFrameworkValidationConfig(marketShareFromValidationConfig);
		componentList.add(marketShareFrom);

	}

	private void addMarketShareTo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig marketShareToLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationMarketShareToLayout", true, GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		componentList.add(marketShareToLayout);

		GtnUIFrameworkComponentConfig marketShareTo = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationMarketShareTo", true, "rebatePlanCalculationMarketShareToLayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		marketShareTo.setAuthorizationIncluded(true);
		marketShareTo.setComponentName("Market Share To");

		GtnUIFrameworkDateFieldConfig marketShareToConfig = new GtnUIFrameworkDateFieldConfig();
		marketShareToConfig.setEnable(true);
		marketShareToConfig.setRequired(true);
		marketShareToConfig.setImmediate(true);
		marketShareTo.setGtnDateFieldConfig(marketShareToConfig);

		GtnUIFrameworkValidationConfig marketShareToValidationConfig = configProvider.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), true,
				GtnFrameworkCommonConstants.DATE_FORMAT_NOT_RECOGNIZED, GtnFrameworkRegexStringConstants.DATE_FORMAT);
		marketShareTo.setGtnUIFrameworkValidationConfig(marketShareToValidationConfig);
		componentList.add(marketShareTo);

	}

	private void addFrom(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig fromLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationFromLayout", true, GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		componentList.add(fromLayout);

		GtnUIFrameworkComponentConfig from = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM, true, "rebatePlanCalculationFromLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		from.setAuthorizationIncluded(true);
		from.setComponentName("From");
		from.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));

		GtnUIFrameworkTextBoxConfig fromConfig = configProvider.getTextBoxConfig(true, true, true);
		from.setGtnTextBoxConfig(fromConfig);

		List<GtnUIFrameWorkActionConfig> fromActionBlurConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig blurActionConfig = new GtnUIFrameWorkActionConfig();
		blurActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		blurActionConfig.addActionParameter(GtnUIFrameworkFromToBlurAction.class.getName());
		blurActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM));
		fromActionBlurConfigList.add(blurActionConfig);
		fromConfig.setBlurActionConfigList(fromActionBlurConfigList);

		List<GtnUIFrameWorkActionConfig> fromActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = new GtnUIFrameWorkActionConfig();
		customActionrRuleNameExist.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnFrameworkRPConstants.TIER_TABLE_COMMON_VALIDATION);
		customActionrRuleNameExist
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM,
						GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO, " From"));
		fromActionConfigList.add(customActionrRuleNameExist);
		fromConfig.setValueChangeActionConfigList(fromActionConfigList);
		componentList.add(from);
	}

	private void addTo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig toLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationToLayout", true, GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		componentList.add(toLayout);

		GtnUIFrameworkComponentConfig toTextBox = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO, true, "rebatePlanCalculationToLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		toTextBox.setAuthorizationIncluded(true);
		toTextBox.setComponentName("To");

		GtnUIFrameworkTextBoxConfig toTextboxConfig = configProvider.getTextBoxConfig(true, true, true);
		toTextBox.setGtnTextBoxConfig(toTextboxConfig);

		List<GtnUIFrameWorkActionConfig> toActionBlurConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig toBlurActionConfig = new GtnUIFrameWorkActionConfig();
		toBlurActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		toBlurActionConfig.addActionParameter(GtnUIFrameworkFromToBlurAction.class.getName());
		toBlurActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO));
		toActionBlurConfigList.add(toBlurActionConfig);
		toTextboxConfig.setBlurActionConfigList(toActionBlurConfigList);

		List<GtnUIFrameWorkActionConfig> toActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = new GtnUIFrameWorkActionConfig();
		customActionrRuleNameExist.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnFrameworkRPConstants.TIER_TABLE_COMMON_VALIDATION);
		customActionrRuleNameExist.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO,
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM, " To"));
		toActionConfigList.add(customActionrRuleNameExist);
		toTextboxConfig.setValueChangeActionConfigList(toActionConfigList);

		componentList.add(toTextBox);

	}

	private void addOperator(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig operatorLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsOperatorLayout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		componentList.add(operatorLayout);

		GtnUIFrameworkComponentConfig operator = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR, true,
				"rebatePlanCalculationsOperatorLayout", GtnUIFrameworkComponentType.COMBOBOX);
		operator.setAuthorizationIncluded(true);
		operator.setComponentName(GtnFrameworkCommonConstants.OPERATOR);
		operator.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		componentList.add(operator);

		GtnUIFrameworkComboBoxConfig operatorConfig = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_OPERATOR_LISTNAME, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		operator.setGtnComboboxConfig(operatorConfig);

		List<GtnUIFrameWorkActionConfig> operatorActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnUIFrameWorkLoadValueComboBox.class.getName());
		customActionrRuleNameExist.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR);
		customActionrRuleNameExist.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE);
		operatorActionConfigList.add(customActionrRuleNameExist);
		operator.setGtnUIFrameWorkActionConfigList(operatorActionConfigList);
	}

	private void addValue(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig valueLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsValueLayout", true, GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		componentList.add(valueLayout);

		GtnUIFrameworkComponentConfig value = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE, true, "rebatePlanCalculationsValueLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		value.setAuthorizationIncluded(true);
		value.setComponentName(GtnFrameworkCommonConstants.VALUE);
		value.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		componentList.add(value);

		GtnUIFrameworkComboBoxConfig valueConfig = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_PERCENT_VALUE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		value.setGtnComboboxConfig(valueConfig);
	}

	private void addComplexFrom(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig fromLayoutComplex = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationFromLayoutComplex", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_FROM_TO_COMPLEX);
		componentList.add(fromLayoutComplex);

		GtnUIFrameworkComponentConfig fromComplex = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX, true,
				"rebatePlanCalculationFromLayoutComplex", GtnUIFrameworkComponentType.TEXTBOX);
		fromComplex.setComponentName("From");
		fromComplex.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));

		GtnUIFrameworkTextBoxConfig fromConfigComplex = configProvider.getTextBoxConfig(true, true, true);
		fromComplex.setGtnTextBoxConfig(fromConfigComplex);

		List<GtnUIFrameWorkActionConfig> fromActionConfigListComplex = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExistComplex = new GtnUIFrameWorkActionConfig();
		customActionrRuleNameExistComplex.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExistComplex.addActionParameter(GtnFrameworkRPConstants.TIER_TABLE_COMMON_VALIDATION);
		customActionrRuleNameExistComplex
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX,
						GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX, " From"));
		fromActionConfigListComplex.add(customActionrRuleNameExistComplex);
		fromConfigComplex.setValueChangeActionConfigList(fromActionConfigListComplex);

		List<GtnUIFrameWorkActionConfig> complexFromActionBlurConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig complexFromActionBlurConfig = new GtnUIFrameWorkActionConfig();
		complexFromActionBlurConfig.setActionType((GtnUIFrameworkActionType.CUSTOM_ACTION));
		complexFromActionBlurConfig.addActionParameter(GtnUIFrameworkFromToBlurAction.class.getName());
		complexFromActionBlurConfig
				.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX));
		complexFromActionBlurConfigList.add(complexFromActionBlurConfig);
		fromConfigComplex.setBlurActionConfigList(complexFromActionBlurConfigList);

		componentList.add(fromComplex);
	}

	private void addComplexTo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig toLayoutComplex = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationToLayoutComplex", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_FROM_TO_COMPLEX);
		componentList.add(toLayoutComplex);

		GtnUIFrameworkComponentConfig toTextBoxComplex = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX, true,
				"rebatePlanCalculationToLayoutComplex", GtnUIFrameworkComponentType.TEXTBOX);
		toTextBoxComplex.setComponentName("To");

		GtnUIFrameworkTextBoxConfig toTextboxConfigComplex = configProvider.getTextBoxConfig(true, true, true);
		toTextBoxComplex.setGtnTextBoxConfig(toTextboxConfigComplex);

		List<GtnUIFrameWorkActionConfig> toActionConfigListComplex = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExistComplex = new GtnUIFrameWorkActionConfig();
		customActionrRuleNameExistComplex.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExistComplex.addActionParameter(GtnFrameworkRPConstants.TIER_TABLE_COMMON_VALIDATION);
		customActionrRuleNameExistComplex
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX,
						GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_FROM_COMPLEX, " To"));
		toActionConfigListComplex.add(customActionrRuleNameExistComplex);
		toTextboxConfigComplex.setValueChangeActionConfigList(toActionConfigListComplex);

		List<GtnUIFrameWorkActionConfig> complexToActionBlurConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig complexToActionBlurConfig = new GtnUIFrameWorkActionConfig();
		complexToActionBlurConfig.setActionType((GtnUIFrameworkActionType.CUSTOM_ACTION));
		complexToActionBlurConfig.addActionParameter(GtnUIFrameworkFromToBlurAction.class.getName());
		complexToActionBlurConfig
				.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATION_TO_COMPLEX));
		complexToActionBlurConfigList.add(complexToActionBlurConfig);
		toTextboxConfigComplex.setBlurActionConfigList(complexToActionBlurConfigList);

		componentList.add(toTextBoxComplex);

	}

	private void addComplexOperator(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig operatorLayoutComplex = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsOperatorLayoutComplex", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_FROM_TO_COMPLEX);
		componentList.add(operatorLayoutComplex);

		GtnUIFrameworkComponentConfig operatorComplex = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR_COMPLEX, true,
				"rebatePlanCalculationsOperatorLayoutComplex", GtnUIFrameworkComponentType.COMBOBOX);
		operatorComplex.setComponentName(GtnFrameworkCommonConstants.OPERATOR);
		operatorComplex.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		componentList.add(operatorComplex);

		GtnUIFrameworkComboBoxConfig operatorConfigComplex = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_OPERATOR_LISTNAME, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		operatorComplex.setGtnComboboxConfig(operatorConfigComplex);

		List<GtnUIFrameWorkActionConfig> operatorActionConfigListComplex = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExistComplex = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExistComplex.addActionParameter(GtnUIFrameWorkLoadValueComboBox.class.getName());
		customActionrRuleNameExistComplex
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_OPERATOR_COMPLEX);
		customActionrRuleNameExistComplex
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE_COMPLEX);
		operatorActionConfigListComplex.add(customActionrRuleNameExistComplex);
		operatorComplex.setGtnUIFrameWorkActionConfigList(operatorActionConfigListComplex);
	}

	private void addComplexValue(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig valueLayoutComplex = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsValueLayoutComplex", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_FROM_TO_COMPLEX);
		componentList.add(valueLayoutComplex);

		GtnUIFrameworkComponentConfig valueComplex = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_VALUE_COMPLEX, true,
				"rebatePlanCalculationsValueLayoutComplex", GtnUIFrameworkComponentType.COMBOBOX);
		valueComplex.setComponentName(GtnFrameworkCommonConstants.VALUE);
		valueComplex.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		componentList.add(valueComplex);

		GtnUIFrameworkComboBoxConfig valueConfigComplex = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_PERCENT_VALUE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		valueComplex.setGtnComboboxConfig(valueConfigComplex);
	}

	private void addOperatorType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig operatorTypeLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsOperatorTypeLayout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		componentList.add(operatorTypeLayout);

		GtnUIFrameworkComponentConfig operatorType = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsOperatorType", true, "rebatePlanCalculationsOperatorTypeLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		operatorType.setAuthorizationIncluded(true);
		operatorType.setComponentName(GtnFrameworkCommonConstants.OPERATOR_TYPE);
		componentList.add(operatorType);

		GtnUIFrameworkComboBoxConfig operatorTypeConfig = new GtnUIFrameworkComboBoxConfig();
		operatorTypeConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		operatorTypeConfig.setItemValues(Arrays.asList("+", "-", "*", "/"));

		operatorType.setGtnComboboxConfig(operatorTypeConfig);

	}

	private void addAdjustmentOperator1(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig adjustmentOperator1Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentOperator1Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		adjustmentOperator1Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(adjustmentOperator1Layout);

		GtnUIFrameworkComponentConfig adjustmentOperator1 = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR1, true,
				"rebatePlanCalculationsAdjustmentOperator1Layout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentOperator1.setAuthorizationIncluded(true);
		adjustmentOperator1.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentOperator1.setComponentName("Adjustment Operator 1");
		componentList.add(adjustmentOperator1);

		GtnUIFrameworkComboBoxConfig adjustmentOperator1Config = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_OPERATOR_LISTNAME, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentOperator1.setGtnComboboxConfig(adjustmentOperator1Config);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnUIFrameWorkLoadValueComboBox.class.getName());
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR1);
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE);

		actionConfigList.add(customActionrRuleNameExist);
		adjustmentOperator1.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addAdjustmentValue(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig adjustmentValueLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentValueLayout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		componentList.add(adjustmentValueLayout);

		GtnUIFrameworkComponentConfig adjustmentValue = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE, true,
				"rebatePlanCalculationsAdjustmentValueLayout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentValue.setAuthorizationIncluded(true);
		adjustmentValue.setComponentName("Adjustment Value 1");
		componentList.add(adjustmentValue);

		GtnUIFrameworkComboBoxConfig adjustmentValueConfig = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_PERCENT_VALUE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentValue.setGtnComboboxConfig(adjustmentValueConfig);
	}

	private void addOperatorType2(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig operatorType2Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsOperatorType2Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		operatorType2Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(operatorType2Layout);

		GtnUIFrameworkComponentConfig operatorType2 = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsOperatorType2", true, "rebatePlanCalculationsOperatorType2Layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		operatorType2.setAuthorizationIncluded(true);
		operatorType2.setComponentName(GtnFrameworkCommonConstants.OPERATOR_TYPE);
		componentList.add(operatorType2);

		GtnUIFrameworkComboBoxConfig operatorType2Config = new GtnUIFrameworkComboBoxConfig();
		operatorType2Config.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		operatorType2Config.setItemValues(Arrays.asList("+", "-", "*", "/"));

		operatorType2.setGtnComboboxConfig(operatorType2Config);

	}

	private void addAdjustmentOperator2(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig adjustmentOperator2Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentOperator2Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		adjustmentOperator2Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(adjustmentOperator2Layout);

		GtnUIFrameworkComponentConfig adjustmentOperator2 = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR2, true,
				"rebatePlanCalculationsAdjustmentOperator2Layout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentOperator2.setAuthorizationIncluded(true);
		adjustmentOperator2.setComponentName("Adjustment Operator 2");
		componentList.add(adjustmentOperator2);

		GtnUIFrameworkComboBoxConfig adjustmentOperator2Config = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_OPERATOR_LISTNAME, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentOperator2.setGtnComboboxConfig(adjustmentOperator2Config);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnUIFrameWorkLoadValueComboBox.class.getName());
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR2);
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE2);

		actionConfigList.add(customActionrRuleNameExist);
		adjustmentOperator2.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addAdjustmentValue2(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig adjustmentValue2Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentValue2Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		adjustmentValue2Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(adjustmentValue2Layout);

		GtnUIFrameworkComponentConfig adjustmentValue2 = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE2, true,
				"rebatePlanCalculationsAdjustmentValue2Layout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentValue2.setAuthorizationIncluded(true);
		adjustmentValue2.setComponentName("Adjustment Value 2");
		componentList.add(adjustmentValue2);

		GtnUIFrameworkComboBoxConfig adjustmentValue2Config = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_PERCENT_VALUE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentValue2.setGtnComboboxConfig(adjustmentValue2Config);
	}

	private void addOperatorType3(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig operatorType3Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsOperatorType3Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		operatorType3Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(operatorType3Layout);

		GtnUIFrameworkComponentConfig operatorType3 = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsOperatorType3", true, "rebatePlanCalculationsOperatorType3Layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		operatorType3.setAuthorizationIncluded(true);
		operatorType3.setComponentName(GtnFrameworkCommonConstants.OPERATOR_TYPE);
		componentList.add(operatorType3);

		GtnUIFrameworkComboBoxConfig operatorType3Config = new GtnUIFrameworkComboBoxConfig();
		operatorType3Config.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		operatorType3Config.setItemValues(Arrays.asList("+", "-", "*", "/"));

		operatorType3.setGtnComboboxConfig(operatorType3Config);

	}

	private void addAdjustmentOperator3(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig adjustmentOperator3Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentOperator3Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		adjustmentOperator3Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(adjustmentOperator3Layout);

		GtnUIFrameworkComponentConfig adjustmentOperator3 = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR3, true,
				"rebatePlanCalculationsAdjustmentOperator3Layout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentOperator3.setAuthorizationIncluded(true);
		adjustmentOperator3.setComponentName("Adjustment Operator 3");
		componentList.add(adjustmentOperator3);

		GtnUIFrameworkComboBoxConfig adjustmentOperator3Config = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_OPERATOR_LISTNAME, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentOperator3.setGtnComboboxConfig(adjustmentOperator3Config);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnUIFrameWorkLoadValueComboBox.class.getName());
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR3);
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE3);

		actionConfigList.add(customActionrRuleNameExist);
		adjustmentOperator3.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addAdjustmentValue3(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig adjustmentValue3Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentValue3Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		adjustmentValue3Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(adjustmentValue3Layout);

		GtnUIFrameworkComponentConfig adjustmentValue3 = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE3, true,
				"rebatePlanCalculationsAdjustmentValue3Layout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentValue3.setAuthorizationIncluded(true);
		adjustmentValue3.setComponentName("Adjustment Value 3");
		componentList.add(adjustmentValue3);

		GtnUIFrameworkComboBoxConfig adjustmentValue3Config = new GtnUIFrameworkComboBoxConfig();
		adjustmentValue3Config.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentValue3Config.setComboBoxType(GtnFrameworkCommonConstants.TIER_PERCENT_VALUE);
		adjustmentValue3.setGtnComboboxConfig(adjustmentValue3Config);
	}

	private void addOperatorType4(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig operatorType4Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsOperatorType4Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		operatorType4Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(operatorType4Layout);

		GtnUIFrameworkComponentConfig operatorType4 = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsOperatorType4", true, "rebatePlanCalculationsOperatorType4Layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		operatorType4.setAuthorizationIncluded(true);
		operatorType4.setComponentName(GtnFrameworkCommonConstants.OPERATOR_TYPE);
		componentList.add(operatorType4);

		GtnUIFrameworkComboBoxConfig operatorType4Config = new GtnUIFrameworkComboBoxConfig();
		operatorType4Config.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		operatorType4Config.setItemValues(Arrays.asList("+", "-", "*", "/"));

		operatorType4.setGtnComboboxConfig(operatorType4Config);

	}

	private void addAdjustmentOperator4(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig adjustmentOperator4Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentOperator4Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		adjustmentOperator4Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(adjustmentOperator4Layout);

		GtnUIFrameworkComponentConfig adjustmentOperator4 = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR4, true,
				"rebatePlanCalculationsAdjustmentOperator4Layout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentOperator4.setAuthorizationIncluded(true);
		adjustmentOperator4.setComponentName("Adjustment Operator 4");
		componentList.add(adjustmentOperator4);

		GtnUIFrameworkComboBoxConfig adjustmentOperator4Config = new GtnUIFrameworkComboBoxConfig();
		adjustmentOperator4Config.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentOperator4Config.setComboBoxType(GtnFrameworkCommonConstants.TIER_OPERATOR_LISTNAME);
		adjustmentOperator4.setGtnComboboxConfig(adjustmentOperator4Config);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnUIFrameWorkLoadValueComboBox.class.getName());
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR4);
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE4);

		actionConfigList.add(customActionrRuleNameExist);
		adjustmentOperator4.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addAdjustmentValue4(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig adjustmentValue4Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentValue4Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		adjustmentValue4Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(adjustmentValue4Layout);

		GtnUIFrameworkComponentConfig adjustmentValue4 = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE4, true,
				"rebatePlanCalculationsAdjustmentValue4Layout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentValue4.setAuthorizationIncluded(true);
		adjustmentValue4.setComponentName("Adjustment Value 4");
		componentList.add(adjustmentValue4);

		GtnUIFrameworkComboBoxConfig adjustmentValue4Config = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_PERCENT_VALUE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentValue4.setGtnComboboxConfig(adjustmentValue4Config);
	}

	private void addOperatorType5(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig operatorType5Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsOperatorType5Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		operatorType5Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(operatorType5Layout);

		GtnUIFrameworkComponentConfig operatorType5 = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanCalculationsOperatorType5", true, "rebatePlanCalculationsOperatorType5Layout",
				GtnUIFrameworkComponentType.COMBOBOX);
		operatorType5.setAuthorizationIncluded(true);
		operatorType5.setComponentName(GtnFrameworkCommonConstants.OPERATOR_TYPE);
		componentList.add(operatorType5);

		GtnUIFrameworkComboBoxConfig operatorType5Config = new GtnUIFrameworkComboBoxConfig();
		operatorType5Config.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		operatorType5Config.setItemValues(Arrays.asList("+", "-", "*", "/"));

		operatorType5.setGtnComboboxConfig(operatorType5Config);

	}

	private void addAdjustmentOperator5(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentOperator5Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig adjustmentOperator5 = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR5, true,
				"rebatePlanCalculationsAdjustmentOperator5Layout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentOperator5.setAuthorizationIncluded(true);
		adjustmentOperator5.setComponentName("Adjustment Operator 5");
		componentList.add(adjustmentOperator5);

		GtnUIFrameworkComboBoxConfig adjustmentOperator5Config = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_OPERATOR_LISTNAME, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentOperator5.setGtnComboboxConfig(adjustmentOperator5Config);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnUIFrameWorkLoadValueComboBox.class.getName());
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_OPERATOR5);
		customActionrRuleNameExist
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE5);

		actionConfigList.add(customActionrRuleNameExist);
		adjustmentOperator5.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addAdjustmentValue5(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig adjustmentValue5Layout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanCalculationsAdjustmentValue5Layout", true,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT_COMPLEX);
		adjustmentValue5Layout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(adjustmentValue5Layout);

		GtnUIFrameworkComponentConfig adjustmentValue5 = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_CALCULATIONS_ADJUSTMENT_VALUE5, true,
				"rebatePlanCalculationsAdjustmentValue5Layout", GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentValue5.setAuthorizationIncluded(true);
		adjustmentValue5.setComponentName("Adjustment Value 5");
		adjustmentValue5.setAddToParent(true);

		componentList.add(adjustmentValue5);

		GtnUIFrameworkComboBoxConfig adjustmentValue5Config = configProvider.getComboBoxConfig(
				GtnFrameworkCommonConstants.TIER_PERCENT_VALUE, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentValue5.setGtnComboboxConfig(adjustmentValue5Config);
	}

	private void addRemoveComplexButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rpCalculationTabRemoveComplexBtn = configProvider.getUIFrameworkComponentConfig(
				"gtnRpRemoveComplexButton", true, GtnFrameworkCommonConstants.REBATE_PLAN_COMPLEX_TABLE_CSSLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		rpCalculationTabRemoveComplexBtn.setAuthorizationIncluded(true);
		rpCalculationTabRemoveComplexBtn.setComponentName("Remove");
		componentList.add(rpCalculationTabRemoveComplexBtn);

		List<GtnUIFrameWorkActionConfig> actionRemoveConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig removeComplexConfirmation = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeComplexConfirmation.addActionParameter(GtnFrameworkRPConstants.REMOVE_CONFIRMATION);
		removeComplexConfirmation
				.addActionParameter(GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_FORMULA_TYPE);
		actionRemoveConfigList.add(removeComplexConfirmation);

		rpCalculationTabRemoveComplexBtn.setGtnUIFrameWorkActionConfigList(actionRemoveConfigList);

	}

}