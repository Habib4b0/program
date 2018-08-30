package com.stpl.gtn.gtn2o.registry.config.hierarchy;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastInnerLevelLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkForecastProdHierarchyConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public List<GtnUIFrameworkComponentConfig> getProductSelectionLayoutComponents(String nameSpace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addProductSelectionLayout(componentList, nameSpace);
		return componentList;
	}

	private void addProductSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + 
                                        GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_PROD_SEL_LAYOUT,
                                                                        true, nameSpace + "_" + "productSelectionPanel");
		productSelectionLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		productSelectionLayout.setSpacing(true);
		componentList.add(productSelectionLayout);

		addComponents(componentList, nameSpace);
		addCustomerSelectionInnerPanel(componentList, nameSpace);
	}

	private void addComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionHorizontalLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "productSelectionHorizontalLayout", true,
				nameSpace + "_" + 
                                        GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_PROD_SEL_LAYOUT);
		productSelectionHorizontalLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(productSelectionHorizontalLayout);

		GtnUIFrameworkComponentConfig productSelectionCssLayout = configProvider.getCssLayoutConfig(
				nameSpace + "_" + 
                                    GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_PROD_SEL_LAYOUT_CSS, true,
				nameSpace + "_" + "productSelectionHorizontalLayout");
//		productSelectionCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
//		productSelectionCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
//		productSelectionCssLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(productSelectionCssLayout);

		addHierarchy(componentList, nameSpace);
		addRelationship(componentList, nameSpace);
		addForecastLevel(componentList, nameSpace);
		addCustomerGroupLookup(componentList, nameSpace);
	}

	private void addHierarchy(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig producthierarchyLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "producthierarchyLayout", true, nameSpace + "_" +
                                  GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_PROD_SEL_LAYOUT_CSS);
		componentList.add(producthierarchyLayout);

		GtnUIFrameworkComponentConfig hierarchyName = new GtnUIFrameworkComponentConfig();
		hierarchyName.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		hierarchyName.setComponentId(nameSpace + "_" + "prodhierarchyName");
		hierarchyName.setComponentName("Hierarchy");
		hierarchyName.setAddToParent(Boolean.TRUE);
		hierarchyName.setParentComponentId(nameSpace + "_" + "producthierarchyLayout");
		
		GtnUIFrameWorkActionConfig hierarchyPopupAction = new GtnUIFrameWorkActionConfig();
		hierarchyPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		hierarchyPopupAction.addActionParameter("forecastLandingScreen_productHierarchyLookup");
		hierarchyPopupAction.addActionParameter("Forecast Product Hierarchy LookUp");
		hierarchyPopupAction.addActionParameter("720");
		hierarchyPopupAction.addActionParameter("875");
		hierarchyName.addGtnUIFrameWorkActionConfig(hierarchyPopupAction);
		
		componentList.add(hierarchyName);
	}

	private void addRelationship(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig prodrelationshipLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "prodrelationshipLayout", true, nameSpace + "_" + 
                              GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_PROD_SEL_LAYOUT_CSS);
		componentList.add(prodrelationshipLayout);

		GtnUIFrameworkComponentConfig relationship = new GtnUIFrameworkComponentConfig();
		relationship.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		relationship.setComponentId(nameSpace + "_" + "prodrelationship");
		relationship.setComponentName("Relationship");
		relationship.setAddToParent(Boolean.TRUE);
		relationship.setParentComponentId(nameSpace + "_" + "prodrelationshipLayout");

		GtnUIFrameworkComboBoxConfig relationshipConfig = new GtnUIFrameworkComboBoxConfig();
		relationship.setGtnComboboxConfig(relationshipConfig);
		componentList.add(relationship);
		
		
		GtnUIFrameWorkActionConfig forecastingProductHierarchyForecastLevelLoadAction = new GtnUIFrameWorkActionConfig();
		forecastingProductHierarchyForecastLevelLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		forecastingProductHierarchyForecastLevelLoadAction.addActionParameter(GtnCustomerSelectionForecastLevelLoadAction.class.getName());
		forecastingProductHierarchyForecastLevelLoadAction.addActionParameter("Commercial Forecasting_prodhierarchyName");
		forecastingProductHierarchyForecastLevelLoadAction.addActionParameter("Commercial Forecasting_prodforecastLevel");
		relationship.addGtnUIFrameWorkActionConfig(forecastingProductHierarchyForecastLevelLoadAction);
	}

	private void addForecastLevel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig prodforecastLevelLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "prodforecastLevelLayout", true, nameSpace + "_" + 
                                        GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_PROD_SEL_LAYOUT_CSS);
		componentList.add(prodforecastLevelLayout);

		GtnUIFrameworkComponentConfig forecastLevel = new GtnUIFrameworkComponentConfig();
		forecastLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		forecastLevel.setComponentId(nameSpace + "_" + "prodforecastLevel");
		forecastLevel.setComponentName("Forecast Level");
		forecastLevel.setAddToParent(Boolean.TRUE);
		forecastLevel.setParentComponentId(nameSpace + "_" + "prodforecastLevelLayout");

		GtnUIFrameworkComboBoxConfig forecastLevelConfig = new GtnUIFrameworkComboBoxConfig();
		forecastLevel.setGtnComboboxConfig(forecastLevelConfig);
		
		GtnUIFrameWorkActionConfig innerProductLevelLoadAction = new GtnUIFrameWorkActionConfig();
		innerProductLevelLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		innerProductLevelLoadAction.addActionParameter(GtnFrameworkForecastInnerLevelLoadAction.class.getName());
		innerProductLevelLoadAction.addActionParameter("Commercial Forecasting_prodhierarchyName");
		innerProductLevelLoadAction.addActionParameter("Commercial Forecasting_prodforecastLevel");
		innerProductLevelLoadAction.addActionParameter("Commercial Forecasting_productLevel");
		forecastLevel.addGtnUIFrameWorkActionConfig(innerProductLevelLoadAction);
		componentList.add(forecastLevel);
	}

	private void addCustomerGroupLookup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productGroupLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "productGroupLayout", true, nameSpace + "_" +
                                        GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_PROD_SEL_LAYOUT_CSS);
		componentList.add(productGroupLayout);

		GtnUIFrameworkComponentConfig customerGroup = new GtnUIFrameworkComponentConfig();
		customerGroup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		customerGroup.setComponentId(nameSpace + "_" + "productGroup");
		customerGroup.setComponentName("Product Group");
		customerGroup.setAddToParent(Boolean.TRUE);
		customerGroup.setParentComponentId(nameSpace + "_" + "productGroupLayout");
		componentList.add(customerGroup);
	}


	private void addCustomerSelectionInnerPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionInnerPanel = configProvider.getPanelConfig(
				nameSpace + "_" + "productSelectionInnerPanel", true, nameSpace + "_" +
                                        GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_PROD_SEL_LAYOUT);
		productSelectionInnerPanel.setSpacing(true);
		componentList.add(productSelectionInnerPanel);

		GtnUIFrameworkComponentConfig productSelectionInnerLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + 
                                        GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_CUST_SEL_INNER_LAYOUT, true,
				nameSpace + "_" + "productSelectionInnerPanel");
		productSelectionInnerLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		componentList.add(productSelectionInnerLayout);

		GtnUIFrameworkComponentConfig productSelectionInnerCssLayout = configProvider.getCssLayoutConfig(
				nameSpace + "_" + "productSelectionInnerCssLayout", true,
				nameSpace + "_" +
                                        GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_CUST_SEL_INNER_LAYOUT);
		productSelectionInnerCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(productSelectionInnerCssLayout);
		addLevelComponent(componentList, nameSpace);
		addDualListBoxComponent(componentList, nameSpace);
	}

	private void addLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productlevelLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "productlevelLayout", true, nameSpace + "_" + "productSelectionInnerCssLayout");
		componentList.add(productlevelLayout);

		GtnUIFrameworkComponentConfig level = new GtnUIFrameworkComponentConfig();
		level.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		level.setComponentId(nameSpace + "_" + "productLevel");
		level.setComponentName("Level");
		level.setAddToParent(Boolean.TRUE);
		level.setParentComponentId(nameSpace + "_" + "productlevelLayout");

		GtnUIFrameworkComboBoxConfig levelConfig = new GtnUIFrameworkComboBoxConfig();
		level.setGtnComboboxConfig(levelConfig);
		componentList.add(level);
	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionDualListBoxComponent = new GtnUIFrameworkComponentConfig();
		productSelectionDualListBoxComponent.setComponentType(GtnUIFrameworkComponentType.DUALLISTBOX);
		productSelectionDualListBoxComponent.setComponentId(nameSpace + "_" + "prodDualListBoxComp");
		productSelectionDualListBoxComponent.setComponentName("Customer Selection");
		productSelectionDualListBoxComponent.setAddToParent(true);
		productSelectionDualListBoxComponent.setParentComponentId(nameSpace + "_" +
                        GtnFrameworkScreenRegisteryConstants.PRODUCT_HIERARCHY_ADD_CUST_SEL_INNER_LAYOUT);
		componentList.add(productSelectionDualListBoxComponent);

		GtnUIFrameworkDualListBoxConfig productSelectionDualListBoxConfig = new GtnUIFrameworkDualListBoxConfig();
		productSelectionDualListBoxConfig.setLeftVisibleColumns(new Object[] { "levelValue" });
		productSelectionDualListBoxConfig.setLeftVisibleHeaders(new String[] { "Level" });

		productSelectionDualListBoxConfig.setRightVisibleHeaders(new String[] { "Product Hierarchy Group Builder" });
		productSelectionDualListBoxConfig.setRightVisibleColumns(new Object[] { "levelValue" });

		productSelectionDualListBoxComponent.setGtnUIFrameworkDualListBoxConfig(productSelectionDualListBoxConfig);
	}
}
