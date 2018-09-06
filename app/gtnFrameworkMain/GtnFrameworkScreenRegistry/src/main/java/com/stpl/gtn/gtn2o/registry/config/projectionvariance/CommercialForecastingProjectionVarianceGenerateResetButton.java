package com.stpl.gtn.gtn2o.registry.config.projectionvariance;

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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class CommercialForecastingProjectionVarianceGenerateResetButton {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addGenerateResetButtonComponetsLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {

		GtnUIFrameworkComponentConfig generateResetButtonComponentsPanelConfig = configProvider
				.getPanelConfig(nameSpace + "_" + "generatesResetButtonComponentsPanelConfig", true, parentComponentId);
		componentList.add(generateResetButtonComponentsPanelConfig);

		GtnUIFrameworkComponentConfig generateResetButtonComponentsLayout = new GtnUIFrameworkComponentConfig();
		generateResetButtonComponentsLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		generateResetButtonComponentsLayout.setComponentId(
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT);
		generateResetButtonComponentsLayout.setComponentWidth("140%");
		generateResetButtonComponentsLayout.setAddToParent(true);
		generateResetButtonComponentsLayout
				.setParentComponentId(generateResetButtonComponentsPanelConfig.getComponentId());

		GtnUIFrameworkLayoutConfig adjustmentLayoutConfig = new GtnUIFrameworkLayoutConfig();
		adjustmentLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		generateResetButtonComponentsLayout.setGtnLayoutConfig(adjustmentLayoutConfig);
		componentList.add(generateResetButtonComponentsLayout);

		addGeneralResetButtonComponent(componentList, nameSpace);
		addLevelComponent(componentList, nameSpace);
		addExpandCollapseButtonComponent(componentList, nameSpace);
		addLevelFilterComponent(componentList, nameSpace);
		addViewComponent(componentList, nameSpace);
		addCustomDropdownComponent(componentList, nameSpace);
		addNewEditButtonComponent(componentList, nameSpace);
	}

	private void addGeneralResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig generateButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "generateButton", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		generateButton.setComponentName("GENERATE");

		GtnUIFrameworkComponentConfig resetButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "resetButton", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentName("RESET");

		componentList.add(generateButton);
		componentList.add(resetButton);
	}

	private void addExpandCollapseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig expandButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "expandButton", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		expandButton.setComponentName("EXPAND");

		GtnUIFrameworkComponentConfig collapseButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "collapseButton", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		collapseButton.setComponentName("COLLAPSE");

		componentList.add(expandButton);
		componentList.add(collapseButton);
	}

	private void addNewEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig newButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "newButton", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		newButton.setComponentName("NEW");

		GtnUIFrameworkComponentConfig editButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "editButton", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		editButton.setComponentName("EDIT");

		componentList.add(newButton);
		componentList.add(editButton);
	}

	private void addLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig levelLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "levelLayout", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT);
		levelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		levelLayout.setMargin(true);
		componentList.add(levelLayout);

		GtnUIFrameworkComponentConfig level = new GtnUIFrameworkComponentConfig();
		level.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		level.setComponentName("Level:");
		level.setComponentId(nameSpace + "_" + "level");
		level.setParentComponentId(levelLayout.getComponentId());
		level.setAddToParent(true);
		level.setMargin(true);

		GtnUIFrameworkComboBoxConfig levelConfig = new GtnUIFrameworkComboBoxConfig();
		levelConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		levelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		level.setGtnComboboxConfig(levelConfig);

		componentList.add(level);
	}

	private void addLevelFilterComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig levelFilterLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "levelFilterLayout", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT);
		levelFilterLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(levelFilterLayout);

		GtnUIFrameworkComponentConfig levelFilter = new GtnUIFrameworkComponentConfig();
		levelFilter.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		levelFilter.setComponentName("Level Filter:");
		levelFilter.setComponentId(nameSpace + "_" + "levelFilter");
		levelFilter.setParentComponentId(levelFilterLayout.getComponentId());
		levelFilter.setAddToParent(true);
		levelFilter.setMargin(true);

		GtnUIFrameworkComboBoxConfig levelFilterConfig = new GtnUIFrameworkComboBoxConfig();
		levelFilterConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		levelFilterConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelFilter.setGtnComboboxConfig(levelFilterConfig);

		componentList.add(levelFilter);
	}

	private void addViewComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig viewLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "viewLayout", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT);
		viewLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(viewLayoutConfig);

		GtnUIFrameworkComponentConfig viewLayoutOptionRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "view", true, nameSpace + "_" + "viewLayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		viewLayoutOptionRadioGroup.setComponentName("View:");
		viewLayoutOptionRadioGroup.setMargin(true);

		GtnUIFrameworkOptionGroupConfig viewOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		viewOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Customer", "Product", "Custom" }));
		viewOptionGroupConfig.setValuesFromService(false);
		viewOptionGroupConfig.setEnable(true);
		viewLayoutOptionRadioGroup
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		viewLayoutOptionRadioGroup.setGtnUIFrameworkOptionGroupConfig(viewOptionGroupConfig);

		componentList.add(viewLayoutOptionRadioGroup);
	}

	private void addCustomDropdownComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig customMenuLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "customMenulayout", true,
				nameSpace + "_" + GtnFrameworkCommonConstants.SCREEN_REGISTRY_GENERATE_RESET_BUTTON_COMPONENTS_LAYOUT);
		customMenuLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(customMenuLayout);

		GtnUIFrameworkComponentConfig customMenu = new GtnUIFrameworkComponentConfig();
		customMenu.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customMenu.setComponentId(nameSpace + "_" + "customMenu");
		customMenu.setParentComponentId(customMenuLayout.getComponentId());
		customMenu.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig customMenuConfig = new GtnUIFrameworkComboBoxConfig();
		customMenuConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		customMenuConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customMenu.setGtnComboboxConfig(customMenuConfig);

		componentList.add(customMenu);
	}

}