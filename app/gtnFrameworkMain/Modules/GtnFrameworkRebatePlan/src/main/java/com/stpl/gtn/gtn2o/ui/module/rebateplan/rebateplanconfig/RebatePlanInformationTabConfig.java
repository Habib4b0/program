package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkLoadValueFormulaTypeComboBoxAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkRPConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class RebatePlanInformationTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addCompanyInformationTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebatePlanInformationTab",
				false, null);
		gtnLayout.setTabComponent(true);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_LAYOUT, true, "rebatePlanInformationTab");
		cssGtnLayout.getGtnLayoutConfig().setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(cssGtnLayout);

		addFieldComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addRebatePlanID(componentList);
		addRebatePlanName(componentList);
		addRebatePlanType(componentList);
		addRebatePlanNo(componentList);
		addRebatePlanStatus(componentList);
		addFormulaType(componentList);

	}

	private void addRebatePlanID(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanIdConfigLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanInformationTabRebatePlanIDLayout", true,
				GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_LAYOUT);
		componentList.add(rebatePlanIdConfigLayout);

		GtnUIFrameworkComponentConfig rebatePlanIdConfig = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanInformationTabRebatePlanID", true, rebatePlanIdConfigLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanIdConfig.setAuthorizationIncluded(true);
		rebatePlanIdConfig.setComponentName("Rebate Plan ID");

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanIdConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		rebatePlanIdConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));

		componentList.add(rebatePlanIdConfig);
	}

	private void addRebatePlanName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanNameConfigLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanInformationTabRebatePlanNameLayout", true,
				GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_LAYOUT);
		componentList.add(rebatePlanNameConfigLayout);

		GtnUIFrameworkComponentConfig rebatePlanNameConfig = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanInformationTabRebatePlanName", true, rebatePlanNameConfigLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNameConfig.setAuthorizationIncluded(true);
		rebatePlanNameConfig.setComponentName("Rebate Plan Name");

		GtnUIFrameworkValidationConfig rpNameValidationConfig = new GtnUIFrameworkValidationConfig();
		rpNameValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanNameConfig.setGtnUIFrameworkValidationConfig(rpNameValidationConfig);
		rebatePlanNameConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));

		componentList.add(rebatePlanNameConfig);
	}

	private void addRebatePlanType(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpTypeLayoutConfig = configProvider.getHorizontalLayoutConfig(
				"rebatePlanInformationTabRebatePlanTypeLayout", true,
				GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_LAYOUT);
		componentList.add(rpTypeLayoutConfig);

		GtnUIFrameworkComponentConfig rpTypeComponentConfig = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanInformationTabRebatePlanType", true, rpTypeLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		rpTypeComponentConfig.setAuthorizationIncluded(true);
		rpTypeComponentConfig.setComponentName("Rebate Plan Type");

		GtnUIFrameworkValidationConfig rpTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		rpTypeValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rpTypeComponentConfig.setGtnUIFrameworkValidationConfig(rpTypeValidationConfig);
		rpTypeComponentConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		componentList.add(rpTypeComponentConfig);

		GtnUIFrameworkComboBoxConfig rpTypeComboConfig = configProvider.getComboBoxConfig("REBATE_PLAN_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rpTypeComponentConfig.setGtnComboboxConfig(rpTypeComboConfig);

	}

	private void addRebatePlanNo(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanNameConfigLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanInformationTabRebatePlanNoLayout", true,
				GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_LAYOUT);
		componentList.add(rebatePlanNameConfigLayout);

		GtnUIFrameworkComponentConfig rebatePlanNameConfig = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanInformationTabRebatePlanNo", true, rebatePlanNameConfigLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNameConfig.setAuthorizationIncluded(true);
		rebatePlanNameConfig.setComponentName("Rebate Plan No");

		GtnUIFrameworkValidationConfig rebatePlanNameValidationConfig = new GtnUIFrameworkValidationConfig();
		rebatePlanNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanNameConfig.setGtnUIFrameworkValidationConfig(rebatePlanNameValidationConfig);
		rebatePlanNameConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));

		componentList.add(rebatePlanNameConfig);
	}

	private void addRebatePlanStatus(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpStatusLayoutConfig = configProvider.getHorizontalLayoutConfig(
				"rebatePlanInformationTabRebateStatusLayout", true,
				GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_LAYOUT);
		componentList.add(rpStatusLayoutConfig);

		GtnUIFrameworkComponentConfig rpStatusComponentConfig = configProvider.getUIFrameworkComponentConfig(
				"rebatePlanInformationTabRebateStatus", true, rpStatusLayoutConfig.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		rpStatusComponentConfig.setAuthorizationIncluded(true);
		rpStatusComponentConfig.setComponentName("Rebate Plan Status");

		GtnUIFrameworkComboBoxConfig rpTypeComboConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rpStatusComponentConfig.setGtnComboboxConfig(rpTypeComboConfig);

		rpStatusComponentConfig
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY }));
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		rpStatusComponentConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(rpStatusComponentConfig);

	}

	private void addFormulaType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rebatePlanInformationTabformulaTypeLayout", true,
				GtnFrameworkCommonConstants.REBATE_PLAN_INFORMATION_TAB_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = new GtnUIFrameworkComponentConfig();
		companyStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentId("rebatePlanInformationTabformulaType");
		companyStatus.setComponentName("Formula Type");
		companyStatus.setParentComponentId("rebatePlanInformationTabformulaTypeLayout");
		companyStatus.setComponentStyle(Arrays.asList(GtnFrameworkCommonConstants.GTN_FRAMEWORK_MANDATORY));
		companyStatus.setAddToParent(true);
		componentList.add(companyStatus);
		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setItemValues(Arrays.asList("Simple", "Complex"));

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionrRuleNameExist = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionrRuleNameExist.addActionParameter(GtnUIFrameWorkLoadValueFormulaTypeComboBoxAction.class.getName());
		customActionrRuleNameExist.addActionParameter(GtnFrameworkRPConstants.LOAD_VALUE_COMBOBOX_FORMULA_TYPE);
		actionConfigList.add(customActionrRuleNameExist);
		companyStatus.setGtnUIFrameWorkActionConfigList(actionConfigList);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

}
