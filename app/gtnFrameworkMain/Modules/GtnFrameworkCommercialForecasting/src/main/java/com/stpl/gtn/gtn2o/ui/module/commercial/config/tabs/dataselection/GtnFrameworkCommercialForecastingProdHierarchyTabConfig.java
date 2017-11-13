/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.dataselection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkCommercialForecastingProdHierarchyTabConfig {
    
    public List<GtnUIFrameworkComponentConfig> getProductSelectionLayoutComponents() {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addParentVerticalLayout(componentList);
		addComponents(componentList);
		return componentList;
	}

	private void addParentVerticalLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig parentVerticalLayout = new GtnUIFrameworkLayoutConfig();
		parentVerticalLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig prodSelectionMainLayout = new GtnUIFrameworkComponentConfig();
		prodSelectionMainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodSelectionMainLayout.setComponentId("dsTabProdSelectionMainLayout");
		prodSelectionMainLayout.setAddToParent(Boolean.TRUE);
		prodSelectionMainLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		prodSelectionMainLayout.setGtnLayoutConfig(parentVerticalLayout);
		prodSelectionMainLayout.setParentComponentId("dsTabProductSelectionPanel");
		parentVerticalLayout.setSpacing(Boolean.TRUE);
		componentList.add(prodSelectionMainLayout);

		GtnUIFrameworkLayoutConfig prodSelectionHierarchyRelationshipLayout = new GtnUIFrameworkLayoutConfig();
		prodSelectionHierarchyRelationshipLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig hierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		hierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarchyRelationshipConfig.setComponentId("dsTabProdSelectionHierarchyRelationshipLayout");
		hierarchyRelationshipConfig.setAddToParent(Boolean.TRUE);
		hierarchyRelationshipConfig.setGtnLayoutConfig(prodSelectionHierarchyRelationshipLayout);
		hierarchyRelationshipConfig.setParentComponentId("dsTabProdSelectionMainLayout");
		hierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(hierarchyRelationshipConfig);

		GtnUIFrameworkLayoutConfig forecastLevelProductGroupLayout = new GtnUIFrameworkLayoutConfig();
		forecastLevelProductGroupLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig forecastLevelProductGroupConfig = new GtnUIFrameworkComponentConfig();
		forecastLevelProductGroupConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastLevelProductGroupConfig.setComponentId("dsTabForecastLevelProductGroupLayout");
		forecastLevelProductGroupConfig.setAddToParent(Boolean.TRUE);
		forecastLevelProductGroupConfig.setGtnLayoutConfig(forecastLevelProductGroupLayout);
		forecastLevelProductGroupConfig.setParentComponentId("dsTabProdSelectionMainLayout");
		forecastLevelProductGroupConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(forecastLevelProductGroupConfig);

		GtnUIFrameworkComponentConfig productSelectionInnerPanel = new GtnUIFrameworkComponentConfig();
		productSelectionInnerPanel.setComponentId("dsTabProductSelectionInnerPanel");
		productSelectionInnerPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		productSelectionInnerPanel.setParentComponentId("dsTabProdSelectionMainLayout");
		productSelectionInnerPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionInnerPanel.setAddToParent(Boolean.TRUE);
		productSelectionInnerPanel.setSpacing(Boolean.TRUE);
		componentList.add(productSelectionInnerPanel);

		GtnUIFrameworkLayoutConfig productSelectionInnerLayout = new GtnUIFrameworkLayoutConfig();
		productSelectionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerConfig.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		productSelectionInnerConfig.setComponentId("dsTabProductSelectionInnerLayout");
		productSelectionInnerConfig.setAddToParent(Boolean.TRUE);
		productSelectionInnerConfig.setGtnLayoutConfig(productSelectionInnerLayout);
		productSelectionInnerConfig.setParentComponentId("dsTabProductSelectionInnerPanel");
		productSelectionInnerConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(productSelectionInnerConfig);

		GtnUIFrameworkLayoutConfig productSelectionInnerCsslayout = new GtnUIFrameworkLayoutConfig();
		productSelectionInnerCsslayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerCssLayoutConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerCssLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerCssLayoutConfig.setComponentId("dsTabProductSelectionInnerCssLayout");
		productSelectionInnerCssLayoutConfig.setAddToParent(Boolean.TRUE);
		productSelectionInnerCssLayoutConfig.setGtnLayoutConfig(productSelectionInnerCsslayout);
		productSelectionInnerCssLayoutConfig.setParentComponentId("dsTabProductSelectionInnerLayout");
		productSelectionInnerCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(productSelectionInnerCssLayoutConfig);

	}

	private void addComponents(List<GtnUIFrameworkComponentConfig> componentList) {
		addHierarchyRelationshipComponent(componentList);
		addForecastLevelProductGroupComponent(componentList);
		addProductLevelComponent(componentList);
		addDualListBoxComponent(componentList);
	}

	private void addHierarchyRelationshipComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig prodSelectionHierarchyLookupLayout = new GtnUIFrameworkLayoutConfig();
		prodSelectionHierarchyLookupLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig hierarechylayoutConfig = new GtnUIFrameworkComponentConfig();
		hierarechylayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarechylayoutConfig.setComponentId("dsTabProdSelectionHierarchyLookupLayout");
		hierarechylayoutConfig.setParentComponentId("dsTabProdSelectionHierarchyRelationshipLayout");
		hierarechylayoutConfig.setAddToParent(Boolean.TRUE);
		hierarechylayoutConfig.setGtnLayoutConfig(prodSelectionHierarchyLookupLayout);
		componentList.add(hierarechylayoutConfig);

		GtnUIFrameworkComponentConfig productHierarchytextFieldConfig = new GtnUIFrameworkComponentConfig();
		productHierarchytextFieldConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		productHierarchytextFieldConfig.setComponentId("dsTabProductHierarchy");
		productHierarchytextFieldConfig.setComponentName("Hierarchy:");
		productHierarchytextFieldConfig.setAddToParent(Boolean.TRUE);
		productHierarchytextFieldConfig.setParentComponentId("dsTabProdSelectionHierarchyLookupLayout");

		List<GtnUIFrameWorkActionConfig> productHierarchytextFieldActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig productHierarchytextFieldActionConfig = new GtnUIFrameWorkActionConfig();
		productHierarchytextFieldActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		productHierarchytextFieldActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { "prodHierarchyTabLookupView", "Product Hierarchy Look Up",GtnFrameworkCssConstants.SEVEN_TWO_ZERO, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE}));
		productHierarchytextFieldActionConfigList.add(productHierarchytextFieldActionConfig);
		productHierarchytextFieldConfig.setGtnUIFrameWorkActionConfigList(productHierarchytextFieldActionConfigList);

		componentList.add(productHierarchytextFieldConfig);

		GtnUIFrameworkLayoutConfig prodSelectionRelationShiplayout = new GtnUIFrameworkLayoutConfig();
		prodSelectionRelationShiplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig relationShiplayoutConfig = new GtnUIFrameworkComponentConfig();
		relationShiplayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		relationShiplayoutConfig.setComponentId("dsTabProdSelectionRelationShipLayout");
		relationShiplayoutConfig.setParentComponentId("dsTabProdSelectionHierarchyRelationshipLayout");
		relationShiplayoutConfig.setAddToParent(Boolean.TRUE);
		relationShiplayoutConfig.setGtnLayoutConfig(prodSelectionRelationShiplayout);
		componentList.add(relationShiplayoutConfig);

		GtnUIFrameworkComponentConfig prodSelectionRelationShipCombobox = new GtnUIFrameworkComponentConfig();
		prodSelectionRelationShipCombobox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		prodSelectionRelationShipCombobox.setComponentId("dsTabProdSelectionRelationShipCombobox");
		prodSelectionRelationShipCombobox.setComponentName("Relationship");
		prodSelectionRelationShipCombobox.setAddToParent(Boolean.TRUE);
		prodSelectionRelationShipCombobox.setParentComponentId("dsTabProdSelectionRelationShipLayout");
		GtnUIFrameworkComboBoxConfig relationShipComboboxConfig = new GtnUIFrameworkComboBoxConfig();
		prodSelectionRelationShipCombobox.setGtnComboboxConfig(relationShipComboboxConfig);
		componentList.add(prodSelectionRelationShipCombobox);
	}

	private void addForecastLevelProductGroupComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
		GtnUIFrameworkComponentConfig productForecastLevelLayout = layoutConfig
				.getHorizontalLayoutConfig("dsTabProductForecastLevelLayout", "dsTabForecastLevelProductGroupLayout");
		componentList.add(productForecastLevelLayout);

		GtnUIFrameworkComponentConfig prodSelectionForecastLevelComponentConfig = new GtnUIFrameworkComponentConfig();
		prodSelectionForecastLevelComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		prodSelectionForecastLevelComponentConfig.setComponentId("dsTabProdSelectionForecastLevel");
		prodSelectionForecastLevelComponentConfig.setComponentName(" Forecast Level:");
		prodSelectionForecastLevelComponentConfig.setParentComponentId(productForecastLevelLayout.getComponentId());
		prodSelectionForecastLevelComponentConfig.setAddToParent(Boolean.TRUE);

		GtnUIFrameworkComboBoxConfig prodSelectionForecastLevelComboxboxConfig = new GtnUIFrameworkComboBoxConfig();
		prodSelectionForecastLevelComponentConfig.setGtnComboboxConfig(prodSelectionForecastLevelComboxboxConfig);
		componentList.add(prodSelectionForecastLevelComponentConfig);

		GtnUIFrameworkComponentConfig productGroupLookupLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig("dsTabProductGroupLookupLayout", "dsTabForecastLevelProductGroupLayout");
		componentList.add(productGroupLookupLayoutConfig);

		GtnUIFrameworkComponentConfig productGroupLookupComponentConfig = new GtnUIFrameworkComponentConfig();
		productGroupLookupComponentConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		productGroupLookupComponentConfig.setComponentId("dsTabProductGroup");
		productGroupLookupComponentConfig.setComponentName("Product Group:");
		productGroupLookupComponentConfig.setAddToParent(Boolean.TRUE);
		productGroupLookupComponentConfig.setParentComponentId(productGroupLookupLayoutConfig.getComponentId());

		List<GtnUIFrameWorkActionConfig> productGroupLookupActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig productGroupLookupActionConfig = new GtnUIFrameWorkActionConfig();
		productGroupLookupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		productGroupLookupActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { "prodGroupTabLookupView", "Product Group Look Up", GtnFrameworkCssConstants.SEVEN_TWO_ZERO, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		productGroupLookupActionConfigList.add(productGroupLookupActionConfig);
		productGroupLookupComponentConfig.setGtnUIFrameWorkActionConfigList(productGroupLookupActionConfigList);

		componentList.add(productGroupLookupComponentConfig);
	}

	private void addProductLevelComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
		GtnUIFrameworkComponentConfig productLevelLayout = layoutConfig.getHorizontalLayoutConfig("dsTabProductLevelLayout",
				"dsTabProductSelectionInnerCssLayout");
		componentList.add(productLevelLayout);

		GtnUIFrameworkComponentConfig productInnerLevel = new GtnUIFrameworkComponentConfig();
		productInnerLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		productInnerLevel.setComponentId("dsTabProductLevelComboBox");
		productInnerLevel.setComponentName("Level:");
		productInnerLevel.setAddToParent(Boolean.TRUE);
		productInnerLevel.setParentComponentId(productLevelLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig productInnerLevelConfig = new GtnUIFrameworkComboBoxConfig();
		productInnerLevel.setGtnComboboxConfig(productInnerLevelConfig);
		componentList.add(productInnerLevel);

	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig prodSelectionDualListBoxComponentConfig = new GtnUIFrameworkComponentConfig();
		prodSelectionDualListBoxComponentConfig.setComponentType(GtnUIFrameworkComponentType.DUALLISTBOX);
		prodSelectionDualListBoxComponentConfig.setComponentId("dsTabProdSelectionDualListBoxComp");
		prodSelectionDualListBoxComponentConfig.setComponentName("Product Selection");
		prodSelectionDualListBoxComponentConfig.setParentComponentId("dsTabProductSelectionInnerLayout");
		prodSelectionDualListBoxComponentConfig.setAddToParent(Boolean.TRUE);

		componentList.add(prodSelectionDualListBoxComponentConfig);

		GtnUIFrameworkDualListBoxConfig prodSelectionDualListBoxConfig = new GtnUIFrameworkDualListBoxConfig();
		prodSelectionDualListBoxConfig
				.setLeftVisibleColumns(new Object[] { GtnFrameworkCommercialForecastingStringConstants.LEVEL_VALUE });
		prodSelectionDualListBoxConfig
				.setLeftVisibleHeaders(new String[] { GtnFrameworkCommercialForecastingStringConstants.LEVEL });

		prodSelectionDualListBoxConfig.setRightVisibleHeaders(
				new String[] { GtnFrameworkCommercialForecastingStringConstants.PRODUCT_HIERARCHY_GROUP_BUILDER });
		prodSelectionDualListBoxConfig
				.setRightVisibleColumns(new Object[] { GtnFrameworkCommercialForecastingStringConstants.LEVEL_VALUE });

		prodSelectionDualListBoxConfig.setModuleName(GtnFrameworkCommercialForecastingStringConstants.MODULE_NAME);

		prodSelectionDualListBoxComponentConfig.setGtnUIFrameworkDualListBoxConfig(prodSelectionDualListBoxConfig);
	}
}
