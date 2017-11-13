package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkExpandCollapseLevelSection {

	private GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
	private String moduleName;
	private String tabName;

	public List<GtnUIFrameworkComponentConfig> getRootExpandCollapseSectionLayout(
			List<GtnUIFrameworkComponentConfig> componentList, String parentId, String moduleName, String tabName) {
		this.moduleName = moduleName;
		this.tabName = tabName;
		GtnUIFrameworkComponentConfig rootVerticalLayout = layoutConfig
				.getHorizontalLayoutConfig(this.tabName + this.moduleName + "ExpandCollapseSection", parentId);
		componentList.add(rootVerticalLayout);
		addDiviedExpandCollapseComponents(componentList, rootVerticalLayout.getComponentId());

		return componentList;
	}

	private void addDiviedExpandCollapseComponents(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig cssLayoutLn4ForLevelDdlb = layoutConfig
				.getCssLayoutConfig(tabName + moduleName + "cssLayoutLn4ForLevelDdlb", parentId);
		componentList.add(cssLayoutLn4ForLevelDdlb);

		levelDdlb(componentList, cssLayoutLn4ForLevelDdlb.getComponentId());

		expandCollapseButton(componentList, parentId);

		GtnUIFrameworkComponentConfig cssLayoutLn4ForLevelFilterDdlb = layoutConfig
				.getCssLayoutConfig(tabName + moduleName + "cssLayoutLn4ForLevelFilterDdlb", parentId);
		componentList.add(cssLayoutLn4ForLevelFilterDdlb);

		levelFilterFilterFilter(componentList, cssLayoutLn4ForLevelFilterDdlb.getComponentId());

		GtnUIFrameworkComponentConfig cssLayoutLn4ForViewOptionGroup = layoutConfig
				.getCssLayoutConfig(tabName + moduleName + "cssLayoutLn4ForvieOptionGroup", parentId);
		componentList.add(cssLayoutLn4ForViewOptionGroup);

		addViewType(componentList, cssLayoutLn4ForViewOptionGroup.getComponentId());
		addViewNameDdlb(componentList, parentId);
		addNewEditButtons(componentList, parentId);
	}

	private void levelDdlb(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig levelDdlbCssLayout = layoutConfig
				.getCssLayoutConfig(tabName + moduleName + "levelDdlb", parentId);
		levelDdlbCssLayout.addComponentStyle("inline-caption-100");
		componentList.add(levelDdlbCssLayout);

		GtnUIFrameworkComponentConfig levelDdlbHorizontalLayout = layoutConfig
				.getHorizontalLayoutConfig(tabName + moduleName + "levelDdlb", levelDdlbCssLayout.getComponentId());
		componentList.add(levelDdlbHorizontalLayout);

		GtnUIFrameworkComponentConfig levelDdlb = new GtnUIFrameworkComponentConfig();
		levelDdlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		levelDdlb.setComponentId(tabName + moduleName + "levelDdlb");
		levelDdlb.setComponentName("Level :");
		levelDdlb.setParentComponentId(levelDdlbHorizontalLayout.getComponentId());
		levelDdlb.setAddToParent(Boolean.TRUE);

		GtnUIFrameworkComboBoxConfig levelDdlbConfig = new GtnUIFrameworkComboBoxConfig();
		levelDdlbConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelDdlbConfig.setComboBoxType("productLevelComboBox");
		levelDdlb.setGtnComboboxConfig(levelDdlbConfig);

		componentList.add(levelDdlb);

	}

	private void expandCollapseButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig expandAndCollapseLayout = layoutConfig
				.getHorizontalLayoutConfig(tabName + moduleName + "expandCollapseBtn", parentId);
		componentList.add(expandAndCollapseLayout);

		GtnUIFrameworkComponentConfig expandButton = new GtnUIFrameworkComponentConfig();
		expandButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		expandButton.setComponentId(tabName + moduleName + "expandBtn");
		expandButton.setComponentName("EXPAND");
		expandButton.setParentComponentId(expandAndCollapseLayout.getComponentId());
		expandButton.setAddToParent(Boolean.TRUE);
		componentList.add(expandButton);

		GtnUIFrameworkComponentConfig collapseButton = new GtnUIFrameworkComponentConfig();
		collapseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		collapseButton.setComponentId(tabName + moduleName + "collapseBtn");
		collapseButton.setComponentName("COLLAPSE");
		collapseButton.setParentComponentId(expandAndCollapseLayout.getComponentId());
		collapseButton.setAddToParent(Boolean.TRUE);

		componentList.add(collapseButton);

	}

	private void levelFilterFilterFilter(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig levelFilterDdlbCssLayout = layoutConfig
				.getCssLayoutConfig(tabName + moduleName + "levelFilterDdlb", parentId);
		levelFilterDdlbCssLayout.addComponentStyle("inline-caption-100");
		componentList.add(levelFilterDdlbCssLayout);

		GtnUIFrameworkComponentConfig levelFilterDdlbHorizontalLayout = layoutConfig.getHorizontalLayoutConfig(
				tabName + moduleName + "levelFilterDdlb", levelFilterDdlbCssLayout.getComponentId());
		componentList.add(levelFilterDdlbHorizontalLayout);

		GtnUIFrameworkComponentConfig levelFilterDdlb = new GtnUIFrameworkComponentConfig();
		levelFilterDdlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		levelFilterDdlb.setComponentId(tabName + moduleName + "levelFilterDdlb");
		levelFilterDdlb.setComponentName("Level Filter:");
		levelFilterDdlb.setParentComponentId(levelFilterDdlbHorizontalLayout.getComponentId());
		levelFilterDdlb.setAddToParent(Boolean.TRUE);
		levelFilterDdlb.setAddToParent(Boolean.TRUE);

		GtnUIFrameworkComboBoxConfig levelFilterDdlbConfig = new GtnUIFrameworkComboBoxConfig();
		levelFilterDdlbConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelFilterDdlbConfig.setComboBoxType("productLevelComboBox");
		levelFilterDdlb.setGtnComboboxConfig(levelFilterDdlbConfig);
		componentList.add(levelFilterDdlb);

	}

	private void addViewType(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig viewTypeOptionGroupLayout = layoutConfig
				.getCssLayoutConfig(tabName + "viewTypeOptionGroup", parentId);
		componentList.add(viewTypeOptionGroupLayout);

		GtnUIFrameworkComponentConfig viewTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		viewTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		viewTypeOptionGroup.setComponentId(tabName + "salesProjectionEnableDisableMode");
		viewTypeOptionGroup.setComponentName("View :");
		viewTypeOptionGroup.setAddToParent(Boolean.TRUE);
		viewTypeOptionGroup.setParentComponentId(viewTypeOptionGroupLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig viewTypeConfig = new GtnUIFrameworkOptionGroupConfig();
		viewTypeConfig.setItemValues(Arrays.asList(new String[] { "Customer", "Product", "Custom" }));
		viewTypeConfig.setValuesFromService(Boolean.FALSE);
		viewTypeOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		viewTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(viewTypeConfig);

		componentList.add(viewTypeOptionGroup);
	}

	private void addViewNameDdlb(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig viewNameDdlbHorizontalLayout = layoutConfig
				.getHorizontalLayoutConfig(tabName + moduleName + "viewNameDdlb", parentId);
		viewNameDdlbHorizontalLayout.addComponentStyle("stpl-margin-top-12");
		componentList.add(viewNameDdlbHorizontalLayout);

		GtnUIFrameworkComponentConfig viewNameDdlb = new GtnUIFrameworkComponentConfig();
		viewNameDdlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		viewNameDdlb.setComponentId(tabName + moduleName + "viewNameDdlb");
		viewNameDdlb.setEnable(false);
		viewNameDdlb.setParentComponentId(viewNameDdlbHorizontalLayout.getComponentId());
		viewNameDdlb.setAddToParent(Boolean.TRUE);

		GtnUIFrameworkComboBoxConfig viewNameDdlbConfig = new GtnUIFrameworkComboBoxConfig();
		viewNameDdlbConfig.setComboBoxType("productLevelComboBox");
		viewNameDdlb.setGtnComboboxConfig(viewNameDdlbConfig);
		componentList.add(viewNameDdlb);

	}

	private void addNewEditButtons(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig resetAndRefreshLayout = layoutConfig
				.getHorizontalLayoutConfig(tabName + "newAndEditButtonLayout", parentId);
		componentList.add(resetAndRefreshLayout);

		GtnUIFrameworkComponentConfig newButton = new GtnUIFrameworkComponentConfig();
		newButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		newButton.setComponentId(tabName + "expandCollapseNewBtn");
		newButton.setComponentName("NEW");
		newButton.setParentComponentId(resetAndRefreshLayout.getComponentId());
		newButton.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> newBtnLookupConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig newBtnLookupConfig = new GtnUIFrameWorkActionConfig();
		newBtnLookupConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		newBtnLookupConfig.setActionParameterList(
				Arrays.asList(new Object[] { tabName + "gtnForecastingCustomViewPopup", "Custom View Lookup",
						GtnFrameworkCssConstants.SEVEN_NINE_FIVE, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		newBtnLookupConfigList.add(newBtnLookupConfig);

		newButton.setGtnUIFrameWorkActionConfigList(newBtnLookupConfigList);
		componentList.add(newButton);

		GtnUIFrameworkComponentConfig editBtn = new GtnUIFrameworkComponentConfig();
		editBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		editBtn.setComponentId(tabName + "expandCollapseEditBtn");
		editBtn.setComponentName("EDIT");
		editBtn.setParentComponentId(resetAndRefreshLayout.getComponentId());
		editBtn.setEnable(false);
		editBtn.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> editBtnLookupConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig editBtnLookupConfig = new GtnUIFrameWorkActionConfig();
		editBtnLookupConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		editBtnLookupConfig.setActionParameterList(
				Arrays.asList(new Object[] { tabName + "gtnForecastingCustomViewPopup", "Custom View Lookup",
						GtnFrameworkCssConstants.SEVEN_NINE_FIVE, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		editBtnLookupConfigList.add(editBtnLookupConfig);

		editBtn.setGtnUIFrameWorkActionConfigList(editBtnLookupConfigList);

		componentList.add(editBtn);
	}
}
