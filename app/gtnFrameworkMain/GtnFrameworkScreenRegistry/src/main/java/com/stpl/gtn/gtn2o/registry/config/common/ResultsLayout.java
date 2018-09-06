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

public class ResultsLayout {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addResultsLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig resultsPanel = configProvider.getPanelConfig(nameSpace + "_" + "resultsPanel",
				true, parentComponentId);
		resultsPanel.setComponentName("Results");
		resultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(resultsPanel);

		GtnUIFrameworkComponentConfig resultsLayout = new GtnUIFrameworkComponentConfig();
		resultsLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		resultsLayout.setComponentId(nameSpace + "-" + "resultsLayout");
		resultsLayout.setComponentWidth("115%");
		resultsLayout.setAddToParent(true);
		resultsLayout.setParentComponentId(resultsPanel.getComponentId());

		GtnUIFrameworkLayoutConfig resultsLayoutConfig = new GtnUIFrameworkLayoutConfig();
		resultsLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		resultsLayout.setGtnLayoutConfig(resultsLayoutConfig);
		componentList.add(resultsLayout);

		addLevelComboBox(componentList, resultsLayout.getComponentId(), nameSpace);
		addExpandButton(componentList, resultsLayout.getComponentId(), nameSpace);
		addCollapseButton(componentList, resultsLayout.getComponentId(), nameSpace);
		addLevelFilterComboBox(componentList, resultsLayout.getComponentId(), nameSpace);
		addViewOptionGroup(componentList, resultsLayout.getComponentId(), nameSpace);
		addCustomComboBox(componentList, resultsLayout.getComponentId(), nameSpace);
		addNewButton(componentList, resultsLayout.getComponentId(), nameSpace);
		addEditButton(componentList, resultsLayout.getComponentId(), nameSpace);
	}

	private void addLevelComboBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig levelComboBoxLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "levelComboBoxLayout", true, parentComponentId);
		levelComboBoxLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		levelComboBoxLayout.setSpacing(true);
		componentList.add(levelComboBoxLayout);

		GtnUIFrameworkComponentConfig levelComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "level", true, levelComboBoxLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		levelComboBox.setComponentName("Level:");
		levelComboBox.setSpacing(true);
		componentList.add(levelComboBox);

		GtnUIFrameworkComboBoxConfig levelConfig = new GtnUIFrameworkComboBoxConfig();
		levelConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		levelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelComboBox.setGtnComboboxConfig(levelConfig);
	}

	private void addExpandButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig expandButtonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "expandButtonLayout", true, parentComponentId);
		expandButtonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		expandButtonLayout.setSpacing(true);
		componentList.add(expandButtonLayout);

		GtnUIFrameworkComponentConfig expandButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "expand", true, expandButtonLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		expandButton.setComponentName("EXPAND");
		expandButton.setSpacing(true);
		componentList.add(expandButton);
	}

	private void addCollapseButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig collapseButtonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "collapseButtonLayout", true, parentComponentId);
		collapseButtonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		collapseButtonLayout.setSpacing(true);
		componentList.add(collapseButtonLayout);

		GtnUIFrameworkComponentConfig collapseButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "collapse", true, collapseButtonLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		collapseButton.setComponentName("COLLAPSE");
		collapseButton.setSpacing(true);
		componentList.add(collapseButton);
	}

	private void addLevelFilterComboBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig levelFilterLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "levelFilterLayout", true, parentComponentId);
		levelFilterLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		levelFilterLayout.setSpacing(true);
		componentList.add(levelFilterLayout);

		GtnUIFrameworkComponentConfig levelFilterComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "levelFilter", true, levelFilterLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		levelFilterComboBox.setComponentName("Level Filter:");
		levelFilterComboBox.setSpacing(true);
		componentList.add(levelFilterComboBox);

		GtnUIFrameworkComboBoxConfig levelFilterConfig = new GtnUIFrameworkComboBoxConfig();
		levelFilterConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		levelFilterConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelFilterComboBox.setGtnComboboxConfig(levelFilterConfig);
	}

	private void addViewOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig viewOptionLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "viewOptionLayout", true, parentComponentId);
		viewOptionLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		viewOptionLayout.setSpacing(true);
		componentList.add(viewOptionLayout);

		GtnUIFrameworkComponentConfig viewRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "view", true, viewOptionLayout.getComponentId(),
				GtnUIFrameworkComponentType.OPTIONGROUP);
		viewRadioGroup.setSpacing(true);
		viewRadioGroup.setComponentName("View:");

		GtnUIFrameworkOptionGroupConfig adjustmentVariableRadioGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		adjustmentVariableRadioGroupConfig.setItemValues(Arrays.asList("Customer", "Product", "Custom"));
		adjustmentVariableRadioGroupConfig.setValuesFromService(false);
		adjustmentVariableRadioGroupConfig.setEnable(true);
		viewRadioGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		viewRadioGroup.setGtnUIFrameworkOptionGroupConfig(adjustmentVariableRadioGroupConfig);

		componentList.add(viewRadioGroup);
	}

	private void addCustomComboBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig customComboBoxLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "customComboBoxLayout", true, parentComponentId);
		customComboBoxLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		customComboBoxLayout.setSpacing(true);
		componentList.add(customComboBoxLayout);

		GtnUIFrameworkComponentConfig customComboBox = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "customComboBox", true, customComboBoxLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customComboBox.setSpacing(true);
		componentList.add(customComboBox);

		GtnUIFrameworkComboBoxConfig customComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		customComboBoxConfig.setComboBoxType(GtnFrameworkScreenRegisteryConstants.COMBOBOX_TYPE);
		customComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customComboBox.setGtnComboboxConfig(customComboBoxConfig);
	}

	private void addNewButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig newButtonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "newButtonLayout", true, parentComponentId);
		newButtonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		newButtonLayout.setSpacing(true);
		componentList.add(newButtonLayout);

		GtnUIFrameworkComponentConfig newButton = configProvider.getUIFrameworkComponentConfig(nameSpace + "_" + "new",
				true, newButtonLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		newButton.setSpacing(true);
		newButton.setComponentName("NEW");
		componentList.add(newButton);
	}

	private void addEditButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig editButtonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "editButtonLayout", true, parentComponentId);
		editButtonLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		editButtonLayout.setSpacing(true);
		componentList.add(editButtonLayout);

		GtnUIFrameworkComponentConfig editButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "edit", true, editButtonLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		editButton.setSpacing(true);
		editButton.setComponentName("EDIT");
		componentList.add(editButton);
	}
}
