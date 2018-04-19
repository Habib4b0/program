package com.stpl.gtn.gtn2o.registry.config.projectionvariance;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class ProjectionVarianceGenerateResetButton {
	
	GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addGenerateResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		
		GtnUIFrameworkComponentConfig generateResetButtonButtonPanel = new GtnUIFrameworkComponentConfig();
		generateResetButtonButtonPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		generateResetButtonButtonPanel.setComponentId(nameSpace + "_" + "GRMainPanel");
		generateResetButtonButtonPanel.setAddToParent(Boolean.TRUE);
		generateResetButtonButtonPanel.setParentComponentId(parentComponentId);
		generateResetButtonButtonPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		generateResetButtonButtonPanel.setMargin(true);
		generateResetButtonButtonPanel.setSpacing(true);
		componentList.add(generateResetButtonButtonPanel);
		
	
		GtnUIFrameworkComponentConfig generateResetButtonLayoutConfig = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "GRLayout", true, nameSpace + "_" + "GRMainPanel");
		generateResetButtonLayoutConfig.setComponentWidth("145%");
		generateResetButtonLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		generateResetButtonButtonPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		//generateResetButtonButtonPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10);
		generateResetButtonLayoutConfig.setAddToParent(true);
		componentList.add(generateResetButtonLayoutConfig);

		addGRInnerLayout(componentList,generateResetButtonLayoutConfig.getComponentId(), nameSpace);		
	}

	private void addGRInnerLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		
		
		GtnUIFrameworkLayoutConfig grInnerLayout = new GtnUIFrameworkLayoutConfig();
		grInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		//grInnerLayout.setMargin(true);
		//grInnerLayout.setSpacing(true);
		
		GtnUIFrameworkComponentConfig grInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		grInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		grInnerLayoutConfig.setComponentId(nameSpace + "_" + "GRInnerLayout");
		grInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		grInnerLayoutConfig.setSpacing(Boolean.TRUE);
		grInnerLayoutConfig.setParentComponentId(parentComponentId);
		grInnerLayoutConfig.setGtnLayoutConfig(grInnerLayout);
		
		
		componentList.add(grInnerLayoutConfig);

		
		addGeneralResetButtonComponent(componentList, grInnerLayoutConfig.getComponentId(), nameSpace);
		addLevelComponent(componentList, grInnerLayoutConfig.getComponentId(), nameSpace);
		addExpandCollapseButtonComponent(componentList, grInnerLayoutConfig.getComponentId(), nameSpace);
		addLevelFilterComponent(componentList, grInnerLayoutConfig.getComponentId(), nameSpace);	
		addViewComponent(componentList, grInnerLayoutConfig.getComponentId(), nameSpace);
		addCustomDropdownComponent(componentList, grInnerLayoutConfig.getComponentId(), nameSpace);
		addNewEditButtonComponent(componentList, grInnerLayoutConfig.getComponentId(), nameSpace);
		
	}
	private void addGeneralResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig generateButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "generateButton", true, parentComponentId, GtnUIFrameworkComponentType.BUTTON);
		generateButton.setComponentName("GENERATE");

		GtnUIFrameworkComponentConfig resetButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "resetButton", true, parentComponentId, GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentName("RESET");

		componentList.add(generateButton);
		componentList.add(resetButton);
	}
	
	private void addExpandCollapseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		
		GtnUIFrameworkComponentConfig expandButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "expandButton", true, parentComponentId, GtnUIFrameworkComponentType.BUTTON);
		expandButton.setComponentName("EXPAND");

		GtnUIFrameworkComponentConfig collapseButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "collapseButton", true, parentComponentId, GtnUIFrameworkComponentType.BUTTON);
		collapseButton.setComponentName("COLLAPSE");

		
		componentList.add(expandButton);
		componentList.add(collapseButton);
	}
	private void addNewEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		
		GtnUIFrameworkComponentConfig newButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "newButton", true, parentComponentId, GtnUIFrameworkComponentType.BUTTON);
		newButton.setComponentName("NEW");

		GtnUIFrameworkComponentConfig editButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "editButton", true, parentComponentId, GtnUIFrameworkComponentType.BUTTON);
		editButton.setComponentName("EDIT");

		
		componentList.add(newButton);
		componentList.add(editButton);
	}


	private void addLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig levelLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "levelLayout", true, parentComponentId);
		levelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		//levelLayout.setSpacing(true);
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
		levelConfig.setComboBoxType("FORECAST_FREQUENCY");
		levelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		level.setGtnComboboxConfig(levelConfig);
		
		componentList.add(level);
	}
	
	private void addLevelFilterComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig levelFilterLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "levelFilterLayout", true, parentComponentId);
		levelFilterLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		//levelFilterLayout.setSpacing(true);
		componentList.add(levelFilterLayout);

		GtnUIFrameworkComponentConfig levelFilter = new GtnUIFrameworkComponentConfig();
		levelFilter.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		levelFilter.setComponentName("Level Filter:");
		levelFilter.setComponentId(nameSpace + "_" + "levelFilter");
		levelFilter.setParentComponentId(levelFilterLayout.getComponentId());
		levelFilter.setAddToParent(true);
		levelFilter.setMargin(true);
		
		GtnUIFrameworkComboBoxConfig levelFilterConfig = new GtnUIFrameworkComboBoxConfig();
		levelFilterConfig.setComboBoxType("FORECAST_FREQUENCY");
		levelFilterConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelFilter.setGtnComboboxConfig(levelFilterConfig);
		
		componentList.add(levelFilter);
	}
	
	private void addViewComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig viewLayoutConfig = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "viewLayout", true, parentComponentId);
		viewLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(viewLayoutConfig);

		GtnUIFrameworkComponentConfig viewLayoutOptionRadioGroup = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "view", true, nameSpace + "_" + "viewLayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		viewLayoutOptionRadioGroup.setComponentName("View:");
		//viewLayoutOptionRadioGroup.setSpacing(true);
		viewLayoutOptionRadioGroup.setMargin(true);

		GtnUIFrameworkOptionGroupConfig viewOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		viewOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Customer", "Product", "Custom"}));
		viewOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		viewOptionGroupConfig.setEnable(Boolean.TRUE);
		viewLayoutOptionRadioGroup.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		viewLayoutOptionRadioGroup.setGtnUIFrameworkOptionGroupConfig(viewOptionGroupConfig);

		componentList.add(viewLayoutOptionRadioGroup);
	}
	
	private void addCustomDropdownComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig customMenuLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "customMenulayout", true, parentComponentId);
		customMenuLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		//customMenuLayout.setSpacing(true);
		componentList.add(customMenuLayout);

		GtnUIFrameworkComponentConfig customMenu = new GtnUIFrameworkComponentConfig();
		customMenu.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customMenu.setComponentId(nameSpace + "_" + "customMenu");
		customMenu.setParentComponentId(customMenuLayout.getComponentId());
		customMenu.setAddToParent(true);
		
		GtnUIFrameworkComboBoxConfig customMenuConfig = new GtnUIFrameworkComboBoxConfig();
		customMenuConfig.setComboBoxType("FORECAST_FREQUENCY");
		customMenuConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customMenu.setGtnComboboxConfig(customMenuConfig);
		
		componentList.add(customMenu);
	}
}

