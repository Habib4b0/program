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
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkCommercialForecastingCustHierarchyTabConfig {
    public List<GtnUIFrameworkComponentConfig> getCustomerSelectionLayoutComponents() {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addParentVerticalLayout(componentList);
		addComponents(componentList);
		return componentList;
	}

	private void addParentVerticalLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig parentVerticalLayout = new GtnUIFrameworkLayoutConfig();
		parentVerticalLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig custSelectionMainlayout = new GtnUIFrameworkComponentConfig();
		custSelectionMainlayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		custSelectionMainlayout.setComponentId("dsTabCustSelectionMainlayout");
		custSelectionMainlayout.setAddToParent(Boolean.TRUE);
		custSelectionMainlayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		custSelectionMainlayout.setGtnLayoutConfig(parentVerticalLayout);
		custSelectionMainlayout.setParentComponentId("dsTabCustomerSelectionPanel");
		parentVerticalLayout.setSpacing(Boolean.TRUE);
		componentList.add(custSelectionMainlayout);

		GtnUIFrameworkLayoutConfig custHierarchyRelationshiplayout = new GtnUIFrameworkLayoutConfig();
		custHierarchyRelationshiplayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig custHierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		custHierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		custHierarchyRelationshipConfig.setComponentId("dsTabCustHierarchyRelationshipLayout");
		custHierarchyRelationshipConfig.setAddToParent(Boolean.TRUE);
		custHierarchyRelationshipConfig.setGtnLayoutConfig(custHierarchyRelationshiplayout);
		custHierarchyRelationshipConfig.setParentComponentId("dsTabCustSelectionMainlayout");
		custHierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(custHierarchyRelationshipConfig);

		GtnUIFrameworkLayoutConfig forecastLevelCustomerGrouplayout = new GtnUIFrameworkLayoutConfig();
		forecastLevelCustomerGrouplayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig forecastLevelCustomerGroupConfig = new GtnUIFrameworkComponentConfig();
		forecastLevelCustomerGroupConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastLevelCustomerGroupConfig.setComponentId("dsTabForecastLevelCustomerGrouplayout");
		forecastLevelCustomerGroupConfig.setAddToParent(Boolean.TRUE);
		forecastLevelCustomerGroupConfig.setGtnLayoutConfig(forecastLevelCustomerGrouplayout);
		forecastLevelCustomerGroupConfig.setParentComponentId("dsTabCustSelectionMainlayout");
		forecastLevelCustomerGroupConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(forecastLevelCustomerGroupConfig);

		GtnUIFrameworkComponentConfig customerSelectionInnerPanel = new GtnUIFrameworkComponentConfig();
		customerSelectionInnerPanel.setComponentId("dsTabCustomerSelectionInnerPanel");
		customerSelectionInnerPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerSelectionInnerPanel.setParentComponentId("dsTabCustSelectionMainlayout");
		customerSelectionInnerPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerSelectionInnerPanel.setAddToParent(Boolean.TRUE);
		customerSelectionInnerPanel.setSpacing(Boolean.TRUE);
		componentList.add(customerSelectionInnerPanel);

		GtnUIFrameworkLayoutConfig customerSelectionInnerlayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionInnerlayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig customerSelectionInnerConfig = new GtnUIFrameworkComponentConfig();
		customerSelectionInnerConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerSelectionInnerConfig.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		customerSelectionInnerConfig.setComponentId("dsTabCustomerSelectionInnerlayout");
		customerSelectionInnerConfig.setAddToParent(Boolean.TRUE);
		customerSelectionInnerConfig.setGtnLayoutConfig(customerSelectionInnerlayout);
		customerSelectionInnerConfig.setParentComponentId("dsTabCustomerSelectionInnerPanel");
		customerSelectionInnerConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(customerSelectionInnerConfig);

		GtnUIFrameworkLayoutConfig customerSelectionInnerCsslayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionInnerCsslayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig customerSelectionInnerCssLayoutConfig = new GtnUIFrameworkComponentConfig();
		customerSelectionInnerCssLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerSelectionInnerCssLayoutConfig.setComponentId("dsTabCustomerSelectionInnerCssLayout");
		customerSelectionInnerCssLayoutConfig.setAddToParent(Boolean.TRUE);
		customerSelectionInnerCssLayoutConfig.setGtnLayoutConfig(customerSelectionInnerCsslayout);
		customerSelectionInnerCssLayoutConfig.setParentComponentId("dsTabCustomerSelectionInnerlayout");
		customerSelectionInnerCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(customerSelectionInnerCssLayoutConfig);

	}

	private void addComponents(List<GtnUIFrameworkComponentConfig> componentList) {
		addCustomerHierarchyRelationshipComponent(componentList);
		addForecastLevelCustomerGroupComponent(componentList);
		addCustomerLevelComponent(componentList);
		addCustomerDualListBoxComponent(componentList);
	}

	private void addCustomerHierarchyRelationshipComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig customerHierarchyLookuplayout = new GtnUIFrameworkLayoutConfig();
		customerHierarchyLookuplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig customerHierarechylayoutConfig = new GtnUIFrameworkComponentConfig();
		customerHierarechylayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerHierarechylayoutConfig.setComponentId("dsTabCustomerHierarchyLookuplayout");
		customerHierarechylayoutConfig.setParentComponentId("dsTabCustHierarchyRelationshipLayout");
		customerHierarechylayoutConfig.setAddToParent(Boolean.TRUE);
		customerHierarechylayoutConfig.setGtnLayoutConfig(customerHierarchyLookuplayout);
		componentList.add(customerHierarechylayoutConfig);

		GtnUIFrameworkComponentConfig customerHierarchytextFieldConfig = new GtnUIFrameworkComponentConfig();
		customerHierarchytextFieldConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		customerHierarchytextFieldConfig.setComponentId("dsTabCustomerHierarchy");
		customerHierarchytextFieldConfig.setComponentName("Hierarchy:");
		customerHierarchytextFieldConfig.setAddToParent(Boolean.TRUE);
		customerHierarchytextFieldConfig.setParentComponentId("dsTabCustomerHierarchyLookuplayout");
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(
				Arrays.asList(new Object[] { "custHierarchyTabLookupView", "Customer Hierarchy Look Up", GtnFrameworkCssConstants.SEVEN_TWO_ZERO, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		list.add(conf);
		customerHierarchytextFieldConfig.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(customerHierarchytextFieldConfig);

		GtnUIFrameworkLayoutConfig customerRelationShiplayout = new GtnUIFrameworkLayoutConfig();
		customerRelationShiplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig customerRelationShiplayoutConfig = new GtnUIFrameworkComponentConfig();
		customerRelationShiplayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerRelationShiplayoutConfig.setComponentId("dsTabCustomerRelationShiplayout");
		customerRelationShiplayoutConfig.setParentComponentId("dsTabCustHierarchyRelationshipLayout");
		customerRelationShiplayoutConfig.setAddToParent(Boolean.TRUE);
		customerRelationShiplayoutConfig.setGtnLayoutConfig(customerRelationShiplayout);
		componentList.add(customerRelationShiplayoutConfig);

		GtnUIFrameworkComponentConfig customerRelationShipCombobox = new GtnUIFrameworkComponentConfig();
		customerRelationShipCombobox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		customerRelationShipCombobox.setComponentId("dsTabCustomerRelationShipCombobox");
		customerRelationShipCombobox.setComponentName("Relationship");
		customerRelationShipCombobox.setAddToParent(Boolean.TRUE);
		customerRelationShipCombobox.setParentComponentId("dsTabCustomerRelationShiplayout");
		GtnUIFrameworkComboBoxConfig relationShipComboboxConfig = new GtnUIFrameworkComboBoxConfig();
		relationShipComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		relationShipComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);
		customerRelationShipCombobox.setGtnComboboxConfig(relationShipComboboxConfig);

		componentList.add(customerRelationShipCombobox);
	}

	private void addForecastLevelCustomerGroupComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
		GtnUIFrameworkComponentConfig customerForecastLevelLayout = layoutConfig
				.getHorizontalLayoutConfig("dsTabCustomerForecastLevelLayout", "dsTabForecastLevelCustomerGrouplayout");
		componentList.add(customerForecastLevelLayout);

		GtnUIFrameworkComponentConfig customerForecastLevelComboxbox = new GtnUIFrameworkComponentConfig();
		customerForecastLevelComboxbox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		customerForecastLevelComboxbox.setComponentId("dsTabCustomerForecastLevel");
		customerForecastLevelComboxbox.setComponentName("Forecast Level:");
		customerForecastLevelComboxbox.setParentComponentId(customerForecastLevelLayout.getComponentId());
		customerForecastLevelComboxbox.setAddToParent(Boolean.TRUE);

		GtnUIFrameworkComboBoxConfig customerForecastLevelComboxboxConfig = new GtnUIFrameworkComboBoxConfig();
		customerForecastLevelComboxbox.setGtnComboboxConfig(customerForecastLevelComboxboxConfig);
		componentList.add(customerForecastLevelComboxbox);

		GtnUIFrameworkComponentConfig customerGroupLookuplayout = layoutConfig
				.getHorizontalLayoutConfig("custGroupTabLookupView", "dsTabForecastLevelCustomerGrouplayout");
		componentList.add(customerGroupLookuplayout);

		GtnUIFrameworkComponentConfig customerGroupLookupConfig = new GtnUIFrameworkComponentConfig();
		customerGroupLookupConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		customerGroupLookupConfig.setComponentId("dsTabCustomerGroup");
		customerGroupLookupConfig.setComponentName("Customer Group:");
		customerGroupLookupConfig.setAddToParent(Boolean.TRUE);
		customerGroupLookupConfig.setParentComponentId(customerGroupLookuplayout.getComponentId());
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(
				Arrays.asList(new Object[] { "custGroupTabLookupView", "Customer Group Look Up",GtnFrameworkCssConstants.SEVEN_TWO_ZERO, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		list.add(conf);
		customerGroupLookupConfig.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(customerGroupLookupConfig);

	}

	private void addCustomerLevelComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
		GtnUIFrameworkComponentConfig customerLevellayout = layoutConfig
				.getHorizontalLayoutConfig("dsTabCustomerLevelLayout", "dsTabCustomerSelectionInnerCssLayout");
		componentList.add(customerLevellayout);

		GtnUIFrameworkComponentConfig customerInnerLevel = new GtnUIFrameworkComponentConfig();
		customerInnerLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		customerInnerLevel.setComponentId("dsTabCustomerLevelComboBox");
		customerInnerLevel.setComponentName("Level:");
		customerInnerLevel.setAddToParent(Boolean.TRUE);
		customerInnerLevel.setParentComponentId(customerLevellayout.getComponentId());
		GtnUIFrameworkComboBoxConfig customerInnerLevelConfig = new GtnUIFrameworkComboBoxConfig();

		customerInnerLevel.setGtnComboboxConfig(customerInnerLevelConfig);

		componentList.add(customerInnerLevel);

	}

	private void addCustomerDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig customerDualListBoxConfig = new GtnUIFrameworkComponentConfig();
		customerDualListBoxConfig.setComponentType(GtnUIFrameworkComponentType.DUALLISTBOX);
		customerDualListBoxConfig.setComponentId("dsTabCustomerDualListBoxComp");
		customerDualListBoxConfig.setComponentName("Customer Selection");
		customerDualListBoxConfig.setParentComponentId("dsTabCustomerSelectionInnerlayout");
		customerDualListBoxConfig.setAddToParent(Boolean.TRUE);
		GtnUIFrameworkDualListBoxConfig customerDualListConfig = new GtnUIFrameworkDualListBoxConfig();
		customerDualListConfig
				.setLeftVisibleColumns(new Object[] { GtnFrameworkCommercialForecastingStringConstants.LEVEL_VALUE });
		customerDualListConfig
				.setLeftVisibleHeaders(new String[] { GtnFrameworkCommercialForecastingStringConstants.LEVEL });

		customerDualListConfig.setRightVisibleHeaders(
				new String[] { GtnFrameworkCommercialForecastingStringConstants.CUSTOMER_HIERARCHY_GROUP_BUILDER });
		customerDualListConfig
				.setRightVisibleColumns(new Object[] { GtnFrameworkCommercialForecastingStringConstants.LEVEL_VALUE });

		customerDualListConfig.setModuleName(GtnFrameworkCommercialForecastingStringConstants.MODULE_NAME);

		customerDualListBoxConfig.setGtnUIFrameworkDualListBoxConfig(customerDualListConfig);
		componentList.add(customerDualListBoxConfig);

	}
}
