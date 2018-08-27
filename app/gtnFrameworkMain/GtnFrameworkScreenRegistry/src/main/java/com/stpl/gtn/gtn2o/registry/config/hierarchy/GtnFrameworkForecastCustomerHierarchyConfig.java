package com.stpl.gtn.gtn2o.registry.config.hierarchy;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionRelationshipLoadAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnRelationshipVersionLoadAction;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
//import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkForecastCustomerHierarchyConfig {
	GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public List<GtnUIFrameworkComponentConfig> getCustomerSelectionLayoutCompoents(String nameSpace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addCustomerSelectionLayout(componentList, nameSpace);
		return componentList;
	}

	private void addCustomerSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "customerSelectionLayout", true, nameSpace + "_" + "customerSelectionPanel");
		customerSelectionLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		customerSelectionLayout.setSpacing(true);
		componentList.add(customerSelectionLayout);

		addComponents(componentList, nameSpace);
		addCustomerSelectionInnerPanel(componentList, nameSpace);
	}

	private void addComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionHorizontalLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "customerSelectionHorizontalLayout", true,
				nameSpace + "_" + "customerSelectionLayout");
		customerSelectionHorizontalLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(customerSelectionHorizontalLayout);

		GtnUIFrameworkComponentConfig customerSelectionCssLayout = configProvider.getCssLayoutConfig(
				nameSpace + "_" + "customerSelectionCssLayout", true,
				nameSpace + "_" + "customerSelectionHorizontalLayout");
		customerSelectionCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		customerSelectionCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		customerSelectionCssLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(customerSelectionCssLayout);

		addHierarchy(componentList, nameSpace);
		addRelationship(componentList, nameSpace);
		addForecastLevel(componentList, nameSpace);
		addCustomerGroupLookup(componentList, nameSpace);
		addForecastEligibleDate(componentList, nameSpace);
	}

	private void addHierarchy(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		
		
		GtnUIFrameworkComponentConfig hierarchyLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "hierarchyLayout", true, nameSpace + "_" + "customerSelectionCssLayout");
		componentList.add(hierarchyLayout);

		GtnUIFrameworkComponentConfig hierarchyName = new GtnUIFrameworkComponentConfig();
		hierarchyName.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		hierarchyName.setComponentId("forecastLandingScreen_customerHierarchy");
		hierarchyName.setComponentName("Hierarchy");
		hierarchyName.setAddToParent(Boolean.TRUE);
		hierarchyName.setParentComponentId(nameSpace + "_" + "hierarchyLayout");
		
		// Added Customer Hierarchy Lookup
		GtnUIFrameWorkActionConfig forecastCustomerSelectionHierarchypopupAction = new GtnUIFrameWorkActionConfig();
		forecastCustomerSelectionHierarchypopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		forecastCustomerSelectionHierarchypopupAction.addActionParameter("forecastingLandingScreen_customerHierarchyLookup");
		forecastCustomerSelectionHierarchypopupAction.addActionParameter("Forecast Customer Hierarchy LookUp");
		forecastCustomerSelectionHierarchypopupAction.addActionParameter("720");
		forecastCustomerSelectionHierarchypopupAction.addActionParameter("875");
		hierarchyName.addGtnUIFrameWorkActionConfig(forecastCustomerSelectionHierarchypopupAction);
		componentList.add(hierarchyName);
	}

	private void addRelationship(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig relationshipLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "relationshipLayout", true, nameSpace + "_" + "customerSelectionCssLayout");
		componentList.add(relationshipLayout);

		GtnUIFrameworkComponentConfig relationship = new GtnUIFrameworkComponentConfig();
		relationship.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		relationship.setComponentId("Commercial_Forecasting_customerSelectionRelationship");
		relationship.setComponentName("Relationship");
		relationship.setAddToParent(Boolean.TRUE);
		relationship.setParentComponentId(nameSpace + "_" + "relationshipLayout");

		GtnUIFrameworkComboBoxConfig relationshipConfig = new GtnUIFrameworkComboBoxConfig();
		relationshipConfig.setComboBoxType(GtnFrameworkForecastingStringConstants.PRODUCT_RELATIONSHIP);
		relationshipConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		relationship.setGtnComboboxConfig(relationshipConfig);
		
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		
		GtnUIFrameWorkActionConfig forecastingCustomerHierarchyForecastLevelLoadAction = new GtnUIFrameWorkActionConfig();
		forecastingCustomerHierarchyForecastLevelLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		forecastingCustomerHierarchyForecastLevelLoadAction.addActionParameter(GtnCustomerSelectionForecastLevelLoadAction.class.getName());
		forecastingCustomerHierarchyForecastLevelLoadAction.addActionParameter("forecastLandingScreen_customerHierarchy");
		forecastingCustomerHierarchyForecastLevelLoadAction.addActionParameter("Commercial_Forecasting_customerSelectionForecastLevel");
		actionConfigList.add(forecastingCustomerHierarchyForecastLevelLoadAction);
				
		
		relationship.setGtnUIFrameWorkActionConfigList(actionConfigList);
		
		// Added hidden combo box
		GtnUIFrameworkComponentConfig customerSelectionRelationshipVersionLayout = configProvider
				.getHorizontalLayoutConfig("customerSelectionRelationshipVersionLayout", true,
				relationshipLayout.getComponentId());
		customerSelectionRelationshipVersionLayout.setVisible(false);

		GtnUIFrameworkComponentConfig customerRelationshipVersion = configProvider.getUIFrameworkComponentConfig(
				"Commercial_Forecasting_customerRelationshipVersion", true,
				customerSelectionRelationshipVersionLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerRelationshipVersion.setComponentName("CustomerRelationshipVersion");

		GtnUIFrameworkComboBoxConfig customerRelationshipVersionConfig = configProvider.getComboBoxConfig(
				GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerRelationshipVersionConfig.setHasDefaultValue(true);
		customerRelationshipVersionConfig.setDefaultDesc("next");
		customerRelationshipVersion.setGtnComboboxConfig(customerRelationshipVersionConfig);
		
		componentList.add(relationship);
		
		//Added
		componentList.add(customerSelectionRelationshipVersionLayout);
		componentList.add(customerRelationshipVersion);
	}

	private void addForecastLevel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig forecastLevelLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "forecastLevelLayout", true, nameSpace + "_" + "customerSelectionCssLayout");
		componentList.add(forecastLevelLayout);

		GtnUIFrameworkComponentConfig forecastLevel = new GtnUIFrameworkComponentConfig();
		forecastLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		forecastLevel.setComponentId("Commercial_Forecasting_customerSelectionForecastLevel");
		forecastLevel.setComponentName("Forecast Level");
		forecastLevel.setAddToParent(Boolean.TRUE);
		forecastLevel.setParentComponentId(nameSpace + "_" + "forecastLevelLayout");

		GtnUIFrameworkComboBoxConfig forecastLevelConfig = new GtnUIFrameworkComboBoxConfig();
		forecastLevelConfig.setComboBoxType(GtnFrameworkForecastingStringConstants.PRODUCT_FORCAST_LEVEL);
		forecastLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		forecastLevel.setGtnComboboxConfig(forecastLevelConfig);
		
		
//		//Added 
//		List<GtnUIFrameWorkActionConfig> forecastLevelactionConfigList = new ArrayList<>();
//
//		GtnUIFrameWorkActionConfig refreshDualListBoxActiion = new GtnUIFrameWorkActionConfig();
//		refreshDualListBoxActiion.setActionType(GtnUIFrameworkActionType.V8DUAL_LISTBOX_RESET_ACTION);
//		refreshDualListBoxActiion.addActionParameter("Commercial_Forecasting_customerDualListBox");
//		refreshDualListBoxActiion.addActionParameter("Commercial_Forecasting_customerSelectionForecastLevel");
//		forecastLevelactionConfigList.add(refreshDualListBoxActiion);
//
//		GtnUIFrameWorkActionConfig forecastLevelValueChangeAction = new GtnUIFrameWorkActionConfig();
//		forecastLevelValueChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
//		forecastLevelValueChangeAction.addActionParameter(GtnCustomerAvailableTableLoadAction.class.getName());
//		forecastLevelValueChangeAction.addActionParameter("forecastLandingScreen_customerHierarchy");
//		forecastLevelValueChangeAction.addActionParameter("Commercial_Forecasting_customerSelectionRelationship");
//		forecastLevelValueChangeAction.addActionParameter("Commercial_Forecasting_customerRelationshipVersion");
//		forecastLevelValueChangeAction.addActionParameter("Commercial_Forecasting_customerSelectionForecastLevel");
//		forecastLevelValueChangeAction.addActionParameter("Commercial_Forecasting_customerSelectionForecastEligibilityDate");
//		forecastLevelValueChangeAction.addActionParameter("Commercial_Forecasting_customerDualListBox");
//
//		forecastLevelactionConfigList.add(forecastLevelValueChangeAction);
//
//		GtnUIFrameWorkActionConfig loadDualListBoxLeftTableAction = new GtnUIFrameWorkActionConfig();
//		loadDualListBoxLeftTableAction.setActionType(GtnUIFrameworkActionType.V8DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
//		loadDualListBoxLeftTableAction.addActionParameter("Commercial_Forecasting_customerDualListBox");
//		
//		forecastLevelactionConfigList.add(loadDualListBoxLeftTableAction);
//		forecastLevel.setGtnUIFrameWorkActionConfigList(forecastLevelactionConfigList);
		
		componentList.add(forecastLevel);
	}

	private void addCustomerGroupLookup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerGroupLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "customerGroupLayout", true, nameSpace + "_" + "customerSelectionCssLayout");
		componentList.add(customerGroupLayout);

		GtnUIFrameworkComponentConfig customerGroup = new GtnUIFrameworkComponentConfig();
		customerGroup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		customerGroup.setComponentId(nameSpace + "_" + "customerGroup");
		customerGroup.setComponentName("Customer Group");
		customerGroup.setAddToParent(Boolean.TRUE);
		customerGroup.setParentComponentId(nameSpace + "_" + "customerGroupLayout");
		componentList.add(customerGroup);
	}

	private void addForecastEligibleDate(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig forecastEligibleDateLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "forecastEligibleDateLayout", true, nameSpace + "_" + "customerSelectionLayout");
		componentList.add(forecastEligibleDateLayout);

		GtnUIFrameworkComponentConfig forecastEligibleDate = new GtnUIFrameworkComponentConfig();
		forecastEligibleDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		forecastEligibleDate.setComponentId("Commercial_Forecasting_customerSelectionForecastEligibilityDate");
		forecastEligibleDate.setComponentName("Forecast Eligible Date");
		forecastEligibleDate.setAddToParent(Boolean.TRUE);
		forecastEligibleDate.setParentComponentId(nameSpace + "_" + "forecastEligibleDateLayout");
		componentList.add(forecastEligibleDate);
	}

	private void addCustomerSelectionInnerPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionInnerPanel = configProvider.getPanelConfig(
				nameSpace + "_" + "customerSelectionInnerPanel", true, nameSpace + "_" + "customerSelectionLayout");
		customerSelectionInnerPanel.setSpacing(true);
		componentList.add(customerSelectionInnerPanel);

		GtnUIFrameworkComponentConfig customerSelectionInnerLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "customerSelectionInnerLayout", true,
				nameSpace + "_" + "customerSelectionInnerPanel");
		customerSelectionInnerLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		componentList.add(customerSelectionInnerLayout);

		GtnUIFrameworkComponentConfig customerSelectionInnerCssLayout = configProvider.getCssLayoutConfig(
				nameSpace + "_" + "customerSelectionInnerCssLayout", true,
				nameSpace + "_" + "customerSelectionInnerLayout");
		customerSelectionInnerCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(customerSelectionInnerCssLayout);
		addLevelComponent(componentList, nameSpace);
		addDualListBoxComponent(componentList, nameSpace);
	}

	private void addLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig levelLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "levelLayout", true, nameSpace + "_" + "customerSelectionInnerCssLayout");
		componentList.add(levelLayout);

		GtnUIFrameworkComponentConfig level = new GtnUIFrameworkComponentConfig();
		level.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		level.setComponentId("Commercial_Forecasting_customerSelectionLevel");
		level.setComponentName("Level");
		level.setAddToParent(Boolean.TRUE);
		level.setParentComponentId(nameSpace + "_" + "levelLayout");

		GtnUIFrameworkComboBoxConfig levelConfig = new GtnUIFrameworkComboBoxConfig();
		levelConfig.setComboBoxType(GtnFrameworkForecastingStringConstants.PRODUCT_RELATIONSHIP);
		levelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		level.setGtnComboboxConfig(levelConfig);
		componentList.add(level);
	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionDualListBoxComponent = new GtnUIFrameworkComponentConfig();
		customerSelectionDualListBoxComponent.setComponentType(GtnUIFrameworkComponentType.DUALLISTBOX);
		customerSelectionDualListBoxComponent.setComponentId("Commercial_Forecasting_customerDualListBox");
		customerSelectionDualListBoxComponent.setComponentName("Customer Selection");
		customerSelectionDualListBoxComponent.setAddToParent(true);
		customerSelectionDualListBoxComponent.setParentComponentId(nameSpace + "_" + "customerSelectionInnerLayout");
		componentList.add(customerSelectionDualListBoxComponent);

		GtnUIFrameworkDualListBoxConfig customerSelectionDualListBoxConfig = new GtnUIFrameworkDualListBoxConfig();
		customerSelectionDualListBoxConfig.setLeftVisibleColumns(new Object[] { "levelValue" });
		customerSelectionDualListBoxConfig.setLeftVisibleHeaders(new String[] { "Level" });

		customerSelectionDualListBoxConfig.setRightVisibleHeaders(new String[] { "Customer Hierarchy Group Builder" });
		customerSelectionDualListBoxConfig.setRightVisibleColumns(new Object[] { "levelValue" });

		customerSelectionDualListBoxComponent.setGtnUIFrameworkDualListBoxConfig(customerSelectionDualListBoxConfig);
	}
	
}
