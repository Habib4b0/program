package com.stpl.gtn.gtn2o.registry.config.common;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class AdjustmentTab {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addAdjustmentTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig adjustmentPanel = configProvider
				.getPanelConfig(nameSpace + "_" + "adjustmentPanel", false, null);
		componentList.add(adjustmentPanel);

		GtnUIFrameworkComponentConfig adjustmentLayout = new GtnUIFrameworkComponentConfig();
		adjustmentLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		adjustmentLayout.setComponentId(nameSpace + "_" + "adjustmentLayout");
		adjustmentLayout.setComponentWidth("118%");
		adjustmentLayout.setAddToParent(true);
		adjustmentLayout.setParentComponentId(adjustmentPanel.getComponentId());

		GtnUIFrameworkLayoutConfig adjustmentLayoutConfig = new GtnUIFrameworkLayoutConfig();
		adjustmentLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		adjustmentLayout.setGtnLayoutConfig(adjustmentLayoutConfig);
		componentList.add(adjustmentLayout);

	}

	public void addTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig typeOptionLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "typeOptionLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.ADJUSTMENT_TAB_PARENT_ID);
		typeOptionLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		typeOptionLayout.setSpacing(true);
		componentList.add(typeOptionLayout);

		GtnUIFrameworkComponentConfig adjustmentTypeRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "adjustmentType", true, typeOptionLayout.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		adjustmentTypeRadioGroup.setComponentName("Type:");

		GtnUIFrameworkOptionGroupConfig adjustmentTypeRadioGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		adjustmentTypeRadioGroupConfig.setItemValues(Arrays.asList("Incremental", "Override"));
		adjustmentTypeRadioGroupConfig.setValuesFromService(false);
		adjustmentTypeRadioGroupConfig.setEnable(true);
		adjustmentTypeRadioGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		adjustmentTypeRadioGroup.setGtnUIFrameworkOptionGroupConfig(adjustmentTypeRadioGroupConfig);

		componentList.add(adjustmentTypeRadioGroup);
	}

	public void addBasisOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig basisOptionLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "basisOptionLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.ADJUSTMENT_TAB_PARENT_ID);
		basisOptionLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		basisOptionLayout.setSpacing(true);
		componentList.add(basisOptionLayout);

		GtnUIFrameworkComponentConfig adjustmentBasisRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "adjustmentBasis", true, basisOptionLayout.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		adjustmentBasisRadioGroup.setComponentName("Basis:");

		GtnUIFrameworkOptionGroupConfig adjustmentBasisRadioGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		adjustmentBasisRadioGroupConfig.setItemValues(Arrays.asList("Amount", "Percentage"));
		adjustmentBasisRadioGroupConfig.setValuesFromService(false);
		adjustmentBasisRadioGroupConfig.setEnable(true);
		adjustmentBasisRadioGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		adjustmentBasisRadioGroup.setGtnUIFrameworkOptionGroupConfig(adjustmentBasisRadioGroupConfig);

		componentList.add(adjustmentBasisRadioGroup);
	}

	public void addVariableOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig variableOptionLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "variableOptionLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.ADJUSTMENT_TAB_PARENT_ID);
		variableOptionLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		variableOptionLayout.setSpacing(true);
		componentList.add(variableOptionLayout);

		GtnUIFrameworkComponentConfig adjustmentVariableRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "adjustmentVariable", true, variableOptionLayout.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		adjustmentVariableRadioGroup.setComponentName("Variable:");

		GtnUIFrameworkOptionGroupConfig adjustmentVariableRadioGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		adjustmentVariableRadioGroupConfig.setItemValues(Arrays.asList("Sales", "Unit", "Actual"));
		adjustmentVariableRadioGroupConfig.setValuesFromService(false);
		adjustmentVariableRadioGroupConfig.setEnable(true);
		adjustmentVariableRadioGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		adjustmentVariableRadioGroup.setGtnUIFrameworkOptionGroupConfig(adjustmentVariableRadioGroupConfig);

		componentList.add(adjustmentVariableRadioGroup);
	}

	public void addAdjustmentTextBox(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig adjustmentLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "adjustmentTextBox", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.ADJUSTMENT_TAB_PARENT_ID);
		adjustmentLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		adjustmentLayout.setSpacing(true);
		componentList.add(adjustmentLayout);

		GtnUIFrameworkComponentConfig adjustmentComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "adjustment", true, adjustmentLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		adjustmentComboBox.setComponentName("Adjustment:");
		componentList.add(adjustmentComboBox);

		GtnUIFrameworkComboBoxConfig adjustmentConfig = new GtnUIFrameworkComboBoxConfig();
		adjustmentConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		adjustmentConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentComboBox.setGtnComboboxConfig(adjustmentConfig);
	}

	public void addAllocationMehodologyComboBox(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig allocationMehodologyLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "allocationMehodologyLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.ADJUSTMENT_TAB_PARENT_ID);
		allocationMehodologyLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		allocationMehodologyLayout.setSpacing(true);
		componentList.add(allocationMehodologyLayout);

		GtnUIFrameworkComponentConfig allocationMehodologyComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "adjustmentAllocationMehodology", true, allocationMehodologyLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		allocationMehodologyComboBox.setComponentName("Allocation Mehodology:");
		componentList.add(allocationMehodologyComboBox);

		GtnUIFrameworkComboBoxConfig allocationMehodologyConfig = new GtnUIFrameworkComboBoxConfig();
		allocationMehodologyConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		allocationMehodologyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		allocationMehodologyComboBox.setGtnComboboxConfig(allocationMehodologyConfig);
	}

	public void addAdjustmentPeriodComboBox(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig adjustmentPeriodLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "adjustmentPeriodLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.ADJUSTMENT_TAB_PARENT_ID);
		adjustmentPeriodLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		adjustmentPeriodLayout.setSpacing(true);
		componentList.add(adjustmentPeriodLayout);

		GtnUIFrameworkComponentConfig adjustmentPeriodComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "adjustmentPeriod", true, adjustmentPeriodLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		adjustmentPeriodComboBox.setComponentName("Adjustment Period:");
		componentList.add(adjustmentPeriodComboBox);

		GtnUIFrameworkComboBoxConfig adjustmentPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		adjustmentPeriodConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		adjustmentPeriodConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		adjustmentPeriodComboBox.setGtnComboboxConfig(adjustmentPeriodConfig);
	}

	public void addAdjustButton(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig adjustLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "adjustLayout", true,
				nameSpace + GtnFrameworkScreenRegisteryConstants.ADJUSTMENT_TAB_PARENT_ID);
		adjustLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		adjustLayout.setSpacing(true);
		componentList.add(adjustLayout);

		GtnUIFrameworkComponentConfig adjustButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "adjustButton", true, adjustLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		adjustButton.setComponentName("Adjust");
		componentList.add(adjustButton);
	}
}
