package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkIdDescDataType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkCDRConstants;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkComplianceAndDeductionRulesInformationTabConfig {

	private final GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

	public void addIdentifierTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_TAB_CDR,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_TAB_CDR, false,
				GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		GtnUIFrameworkComponentConfig cssLayoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_PAN,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_PAN, true,
				GtnUIFrameworkComponentType.LAYOUT);
		cssLayoutConfig
				.setParentComponentId(GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_TAB_CDR);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cssLayoutConfig.setComponentStyle(styleList);

		GtnUIFrameworkLayoutConfig cssLayout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cssLayoutConfig.setGtnLayoutConfig(cssLayout);

		componentList.add(cssLayoutConfig);

		GtnUIFrameworkComponentConfig cssLayoutConfg = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_PANEL_LA,
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_PANEL_LA, true,
				GtnUIFrameworkComponentType.LAYOUT);
		cssLayoutConfg
				.setParentComponentId(GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_TAB_CDR);
		List<String> styleLists = new ArrayList<>();
		styleLists.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		styleLists.add(GtnFrameworkCssConstants.NO_MARGIN);

		cssLayoutConfg.setComponentStyle(styleLists);

		GtnUIFrameworkLayoutConfig cssLayouts = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cssLayoutConfg.setGtnLayoutConfig(cssLayouts);

		componentList.add(cssLayoutConfg);
		ruleInformationPanel(componentList);
		ruleDetailsPanel(componentList);
		addActionButtonLayout(componentList);

	}

	private void ruleInformationPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"ruleInformationPanel", "Rule Information", true, GtnUIFrameworkComponentType.PANEL);
		panel.setAuthorizationIncluded(true);

		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setParentComponentId(GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_INFORMATION_PAN);
		componentList.add(panel);

		GtnUIFrameworkComponentConfig layoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_INFORMATION_PANEL_LAYOUT, "ruleInformationPanelLayout", true,
				GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setParentComponentId("ruleInformationPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);

		componentList.add(layoutConfig);

		ruleInformationLayout(componentList);

	}

	private void ruleInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cssLayoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT,
				GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		cssLayoutConfig.setParentComponentId("ruleInformationPanelLayout");
		List<String> styleList = new ArrayList<>();
		styleList.add("gtnGrid-single-ln-layout-4");
		cssLayoutConfig.setComponentStyle(styleList);

		GtnUIFrameworkLayoutConfig cssLayout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		cssLayoutConfig.setGtnLayoutConfig(cssLayout);

		componentList.add(cssLayoutConfig);
		ruleInformationFields(componentList);
	}

	private void ruleInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addRuleType(componentList);
		addRuleNo(componentList);
		addRuleName(componentList);
		addRuleCategory(componentList);
	}

	private void addRuleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE_LAYOUT,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE_LAYOUT, true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"ruleInformationRuleType", "Rule Type", true, GtnUIFrameworkComponentType.COMBOBOX);
		companyQualifierName.setAuthorizationIncluded(true);
		companyQualifierName.setParentComponentId(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE_LAYOUT);
		companyQualifierName
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		componentList.add(companyQualifierName);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("RULE_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyQualifierName.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);

	}

	private void addRuleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO_LAYOUT,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("ruleInformationRuleNo", "Rule No", true, GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);

		companyIdentifierConfig.setParentComponentId(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO_LAYOUT);
		companyIdentifierConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));

		GtnUIFrameworkTextBoxConfig textboxConfig = gtnFrameworkConfigurationFactory.buildTextBoxConfig(true, true,
				true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addRuleName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME_LAYOUT,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME_LAYOUT, true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"ruleInformationRuleName", "Rule Name", true, GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setParentComponentId(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME_LAYOUT);
		companyIdentifierConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		GtnUIFrameworkTextBoxConfig textboxConfig = gtnFrameworkConfigurationFactory.buildTextBoxConfig(true, true,
				true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		companyIdentifierConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addRuleCategory(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORYLAYOUT,
				GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORYLAYOUT, true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"ruleInformationRuleCategory", "Rule Category", true, GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setParentComponentId(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_CATEGORYLAYOUT);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("RULE_CATEGORY", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void ruleDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = gtnFrameworkConfigurationFactory.buildComponentConfig("ruleDetailsPanel",
				"Rule Details", true, GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setParentComponentId(GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_PANEL_LA);
		componentList.add(panel);

		GtnUIFrameworkComponentConfig layoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_PANEL_LAYOUT,
				GtnFrameworkCommonConstants.RULE_DETAILS_PANEL_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setParentComponentId("ruleDetailsPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		ruleDetailsInformationLayout(componentList);
		ruleDetailsResultPanel(componentList);
	}

	private void ruleDetailsInformationLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT,
				GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_PANEL_LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add("gtnGrid-single-ln-layout-4");

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		layoutConfig.setComponentStyle(styleList);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);
		ruleDetailsInformationFields(componentList);
	}

	private void ruleDetailsInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addRuleDetailsLineType(componentList);
		addIGMSAssociation(componentList);
		addKeyWord(componentList);
		addOperator(componentList);
		addValue(componentList);
		addComparison(componentList);
		addOperatorOne(componentList);
	}

	private void addRuleDetailsLineType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE_LAYOUT,
				GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE, "Line Type:", true,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_LINE_TYPE_LAYOUT);
		companyStatus.setAddToParent(true);
		companyStatus.setAuthorizationIncluded(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("LINE_TYPE", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addIGMSAssociation(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("IGMSAssociationLayout");
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"ruleDetailsIGMSAssociation", "Item/Group/MS Association:", true, GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setParentComponentId("IGMSAssociationLayout");
		companyStatus.setEnable(false);
		companyStatus.setAuthorizationIncluded(true);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("ITEM_GROUP_MS_ASSOCIATION", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatusConfig.setHasDefaultValue(true);
		companyStatusConfig.setDefaultDesc("Item");
		companyStatusConfig.setIsEnabled(false);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addKeyWord(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD_LAYOUT,
				GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD, "Key Word:", true,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_KEY_WORD_LAYOUT);
		companyStatus.setAuthorizationIncluded(true);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("KEYWORD", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addOperator(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_LAYOUT, "ruleDetailsOperatorLayout", true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR, "Operator:", true,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setParentComponentId("ruleDetailsOperatorLayout");
		companyStatus.setAuthorizationIncluded(true);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("OPERATOR", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addValue(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_VALUE_LAYOUT, "ruleDetailsValueLayout", true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, "Value:", true, GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setParentComponentId("ruleDetailsValueLayout");
		companyIdentifierConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkTextBoxConfig textboxConfig = gtnFrameworkConfigurationFactory.buildTextBoxConfig(true, true,
				true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addComparison(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON_LAYOUT, "ruleDetailsComparisonLayout", true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_COMPARISON, "Comparison:", true,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setParentComponentId("ruleDetailsComparisonLayout");
		companyStatus.setAuthorizationIncluded(true);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("COMPARISON", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addOperatorOne(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE_LAYOUT,
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_INFORMATION_LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE, "Operator:", true,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE_LAYOUT);
		companyStatus.setAuthorizationIncluded(true);
		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = gtnFrameworkConfigurationFactory
				.buildComboBoxConfig("LOGICAL_OPERATOR", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void ruleDetailsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = gtnFrameworkConfigurationFactory
				.buildComponentConfig("ruleDetailsResultPanel", "Result", true, GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_PANEL_LAYOUT);
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);
		ruleDetailsResultLayout(componentList);
	}

	private void ruleDetailsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILS_RESULT_LAYOUT,
				GtnFrameworkCommonConstants.RULE_DETAILS_RESULT_LAYOUT, true, GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		layoutConfig.setParentComponentId("ruleDetailsResultPanel");

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		ruleDetailsResultDataTable(componentList);
	}

	private void ruleDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachResultConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE, "Results", true,
				GtnUIFrameworkComponentType.DATATABLE);

		attachResultConfig.setParentComponentId(GtnFrameworkCommonConstants.RULE_DETAILS_RESULT_LAYOUT);
		attachResultConfig.setComponentHight("400px");
		attachResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		attachResultConfig.setAuthorizationIncluded(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		attachResultConfig.setComponentStyle(tableStyle);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		gtnUIFrameworkValidationConfig.setMinSize(1);
		attachResultConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(attachResultConfig);

		GtnUIFrameworkPagedTableConfig attachResults = new GtnUIFrameworkPagedTableConfig();
		attachResultConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setEditable(false);
		attachResults.setFilterBar(true);
		attachResults.setSelectable(true);
		attachResults.setTableColumnDataType(new Class[] { String.class, String.class, String.class, String.class,
				String.class, String.class, String.class });
		attachResults.setTableVisibleHeader(new String[] { "Line Type:", "Item/Group/MS Association", "Keyword",
				"Operator", "Value", "Comparison", "Operator" });
		attachResults.setTableColumnMappingId(new Object[] { "lineType", "iGMSAssociation", "keyword", "operator",
				"value", "comparison", "operatorOne" });

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, true,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_DETAILS_PANEL_LA);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addAddButtonComponent(componentList);
		addRemoveButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig addButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("cDRInfoGtnAddButton", "ADD", true, GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		addButtonConfig.setAuthorizationIncluded(true);
		componentList.add(addButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionOne = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionOne
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.MANDATORY_CHECK }));
		actionConfigList.add(customActionOne);

		GtnUIFrameWorkActionConfig customActionforDuplicateCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionforDuplicateCheck
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.DUPLICATE_CHECK }));

		actionConfigList.add(customActionforDuplicateCheck);

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionforLastOperatorCheck
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.LAST_OPERATOR_CHECK }));

		actionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig selectAction = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.ADD_RECORD);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE);
		Map<String, GtnUIFrameworkIdDescDataType> idDescMap = new HashMap<>();
		idDescMap.put("lineType", GtnUIFrameworkIdDescDataType.DESC);
		idDescMap.put("iGMSAssociation", GtnUIFrameworkIdDescDataType.DESC);
		idDescMap.put("keyword", GtnUIFrameworkIdDescDataType.DESC);
		idDescMap.put("operator", GtnUIFrameworkIdDescDataType.DESC);
		idDescMap.put("value", GtnUIFrameworkIdDescDataType.ID);
		idDescMap.put("comparison", GtnUIFrameworkIdDescDataType.DESC);
		idDescMap.put("operatorOne", GtnUIFrameworkIdDescDataType.DESC);
		actionParameterList.add(idDescMap);

		selectAction.setFieldValues(Arrays.asList(new String[] { "ruleDetailsLineType", "ruleDetailsIGMSAssociation",
				"ruleDetailsKeyWord", GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR,
				GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, "ruleDetailsComparison",
				GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE }));

		selectAction.setActionParameterList(actionParameterList);

		actionConfigList.add(selectAction);
		addButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("cDRInfoGtnRemoveButton", "REMOVE", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionforLastOperatorCheck
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.IS_REMOVE_SELECTED }));
		actionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig confirmationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(" Delete Confirmation ");
		alertParams.add(" Are you sure you want to delete the selected Rule Record? ");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig removeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.REMOVE_ACTION);
		removeActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE }));

		onSucessActionConfig.add(removeActionConfig);

		alertParams.add(onSucessActionConfig);

		confirmationActionConfig.setActionParameterList(alertParams);

		actionConfigList.add(confirmationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("cDRInfoGtnResetButton", "RESET", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT);
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add(" Are you sure you want to reset the values in the ‘Rule Details’ group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE, "");

		resetMap.put("ruleDetailsLineType", null);
		resetMap.put("ruleDetailsKeyWord", null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR, null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_VALUE, "");
		resetMap.put("ruleDetailsComparison", null);
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILS_OPERATOR_ONE, null);

		params.add(resetMap);
		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
