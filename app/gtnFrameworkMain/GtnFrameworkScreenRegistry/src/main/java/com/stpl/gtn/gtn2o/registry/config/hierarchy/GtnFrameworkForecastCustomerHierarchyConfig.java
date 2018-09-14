package com.stpl.gtn.gtn2o.registry.config.hierarchy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastInnerLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnForecastEligibleDateLoadAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

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
		customerSelectionLayout.setSpacing(true);
		componentList.add(customerSelectionLayout);

		addComponents(componentList, nameSpace);
		addCustomerSelectionInnerPanel(componentList, nameSpace);
	}

	private void addComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionHorizontalLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "customerSelectionHorizontalLayout", true,
				nameSpace + "_" + "customerSelectionLayout");
		componentList.add(customerSelectionHorizontalLayout);

		GtnUIFrameworkComponentConfig customerSelectionCssLayout = configProvider.getCssLayoutConfig(
				nameSpace + "_" + "customerSelectionCssLayout", true,
				nameSpace + "_" + "customerSelectionHorizontalLayout");
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

		GtnUIFrameWorkActionConfig forecastCustomerSelectionHierarchypopupAction = new GtnUIFrameWorkActionConfig();
		forecastCustomerSelectionHierarchypopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		forecastCustomerSelectionHierarchypopupAction
				.addActionParameter("forecastingLandingScreen_customerHierarchyLookup");
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
		relationship.setComponentName("Relationship");
		relationship.setComponentId("Commercial_Forecasting_customerSelectionRelationship");
		relationship.setAddToParent(Boolean.TRUE);
		relationship.setParentComponentId(nameSpace + "_" + "relationshipLayout");
		relationship.setVaadinComponentPlaceHolder("-Select One-");

		GtnUIFrameworkComboBoxConfig relationshipConfig = new GtnUIFrameworkComboBoxConfig();
		relationship.setGtnComboboxConfig(relationshipConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig forecastingCustomerHierarchyForecastLevelLoadAction = new GtnUIFrameWorkActionConfig();
		forecastingCustomerHierarchyForecastLevelLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		forecastingCustomerHierarchyForecastLevelLoadAction
				.addActionParameter(GtnCustomerSelectionForecastLevelLoadAction.class.getName());
		forecastingCustomerHierarchyForecastLevelLoadAction
				.addActionParameter("forecastLandingScreen_customerHierarchy");
		forecastingCustomerHierarchyForecastLevelLoadAction
				.addActionParameter("Commercial_Forecasting_customerSelectionForecastLevel");
		forecastingCustomerHierarchyForecastLevelLoadAction
				.addActionParameter("Commercial_Forecasting_customerSelectionRelationship");
		forecastingCustomerHierarchyForecastLevelLoadAction
				.addActionParameter("Commercial_Forecasting_customerRelationshipVersion");
		actionConfigList.add(forecastingCustomerHierarchyForecastLevelLoadAction);

		relationship.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig customerSelectionRelationshipVersionLayout = configProvider
				.getHorizontalLayoutConfig("customerSelectionRelationshipVersionLayout", true,
						relationshipLayout.getComponentId());
		// customerSelectionRelationshipVersionLayout.setVisible(false);

		GtnUIFrameworkComponentConfig customerRelationshipVersion = configProvider.getUIFrameworkComponentConfig(
				"Commercial_Forecasting_customerRelationshipVersion", true,
				customerSelectionRelationshipVersionLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerRelationshipVersion.setComponentName("CustomerRelationshipVersion");

		GtnUIFrameworkComboBoxConfig customerRelationshipVersionConfig = new GtnUIFrameworkComboBoxConfig();
		customerRelationshipVersionConfig.setHasDefaultValue(true);
		customerRelationshipVersionConfig.setDefaultDesc("next");
		customerRelationshipVersion.setGtnComboboxConfig(customerRelationshipVersionConfig);

		componentList.add(relationship);

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
		forecastLevel.setVaadinComponentPlaceHolder("-Select One-");
		

		GtnUIFrameworkComboBoxConfig forecastLevelConfig = new GtnUIFrameworkComboBoxConfig();
		forecastLevel.setGtnComboboxConfig(forecastLevelConfig);

		GtnUIFrameWorkActionConfig innerLevelLoadAction = new GtnUIFrameWorkActionConfig();
		innerLevelLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		innerLevelLoadAction.addActionParameter(GtnFrameworkForecastInnerLevelLoadAction.class.getName());
		innerLevelLoadAction.addActionParameter("forecastLandingScreen_customerHierarchy");
		innerLevelLoadAction.addActionParameter("Commercial_Forecasting_customerSelectionForecastLevel");
		innerLevelLoadAction.addActionParameter("Commercial_Forecasting_customerSelectionLevel");
		forecastLevel.addGtnUIFrameWorkActionConfig(innerLevelLoadAction);
		componentList.add(forecastLevel);
	}

	private void addCustomerGroupLookup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerGroupLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "customerGroupLayout", true, nameSpace + "_" + "customerSelectionCssLayout");
		componentList.add(customerGroupLayout);

		GtnUIFrameworkComponentConfig customerGroup = new GtnUIFrameworkComponentConfig();
		customerGroup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
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
		forecastEligibleDate.addComponentStyle("datefieldcentered");
		forecastEligibleDate.setComponentHight("20px");
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
		level.setVaadinComponentPlaceHolder("-Select One-");

		GtnUIFrameworkComboBoxConfig levelConfig = new GtnUIFrameworkComboBoxConfig();
		level.setGtnComboboxConfig(levelConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = availableLoadActionList();

		level.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(level);
	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionDualListBoxComponent = new GtnUIFrameworkComponentConfig();
		customerSelectionDualListBoxComponent.setComponentType(GtnUIFrameworkComponentType.V8_DUALLISTBOX);
		customerSelectionDualListBoxComponent.setComponentId(nameSpace + "_" + "customerDualListBox");
		customerSelectionDualListBoxComponent.setComponentName("Customer Selection");
		customerSelectionDualListBoxComponent.setParentComponentId(nameSpace + "_" + "customerSelectionInnerLayout");
		customerSelectionDualListBoxComponent.setAddToParent(true);

		GtnUIFrameworkValidationConfig customerSelectionDualListBoxValidationConfig = new GtnUIFrameworkValidationConfig();
		customerSelectionDualListBoxValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		customerSelectionDualListBoxComponent
				.setGtnUIFrameworkValidationConfig(customerSelectionDualListBoxValidationConfig);

		componentList.add(customerSelectionDualListBoxComponent);
		GtnUIFrameworkV8DualListBoxConfig customerSelectionDualListBoxConfig = new GtnUIFrameworkV8DualListBoxConfig();
		customerSelectionDualListBoxConfig.setLeftVisibleColumns(new Object[] { "levelValue" });
		customerSelectionDualListBoxConfig.setLeftVisibleHeaders(new String[] { "Level" });

		customerSelectionDualListBoxConfig.setRightVisibleHeaders(new String[] { "Customer Hierarchy Group Builder" });
		customerSelectionDualListBoxConfig.setRightVisibleColumns(new Object[] { "levelValue" });
		customerSelectionDualListBoxConfig.setRecordHeader(Arrays.asList("parent_relationrelationship_level_values",
				"parent_relationlevel_no", "parent_relationparent_node", "parent_relationrelationship_level_sid",
				"parent_relationhierarchy_no", "parent_relationrelationship_builder_sid", "levelValue", "levelNo",
				"levelValueReference", "tableName", "fieldName", "level", "hierarchyLevelDefSid", "hierarchyDefSid",
				"hierarchyType"));
		customerSelectionDualListBoxConfig.setRightRecordHeader(
				Arrays.asList("levelNo", "relationshipLevelValues", "parentNode", "levelName", "levelValuReference",
						"tableName", "fieldName", "relationshipLevelSid", "hierarchyNo", "relationshipBuilderSid",
						"hierarchyLevelDefSid", "hierarchyDefSid", "versionNo", "levelValue"));
		customerSelectionDualListBoxConfig.setModuleType("hierarchyRelationship");
		customerSelectionDualListBoxConfig.setLeftTableURL("/loadAvailableTable");
		customerSelectionDualListBoxConfig.setMoveRightURL("/loadCustomerSelectedTable");
		customerSelectionDualListBoxConfig.setMoveAllDataURL("/loadBulkCustomerSelectedTable");

		customerSelectionDualListBoxComponent.setGtnUIFrameworkV8DualListBoxConfig(customerSelectionDualListBoxConfig);
	}

	private List<GtnUIFrameWorkActionConfig> availableLoadActionList() {
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig levelValueChangeAction = new GtnUIFrameWorkActionConfig();
		levelValueChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		levelValueChangeAction.addActionParameter(GtnCustomerAvailableTableLoadAction.class.getName());
		levelValueChangeAction.addActionParameter("forecastLandingScreen_customerHierarchy");
		levelValueChangeAction.addActionParameter("Commercial_Forecasting_customerSelectionRelationship");
		levelValueChangeAction.addActionParameter("Commercial_Forecasting_customerRelationshipVersion");
		levelValueChangeAction.addActionParameter("Commercial_Forecasting_customerSelectionLevel");
		levelValueChangeAction.addActionParameter("Commercial_Forecasting_customerSelectionForecastEligibilityDate");
		levelValueChangeAction.addActionParameter("Commercial Forecasting_customerDualListBox");
		actionConfigList.add(levelValueChangeAction);

		GtnUIFrameWorkActionConfig loadDualListBoxLeftTableAction = new GtnUIFrameWorkActionConfig();
		loadDualListBoxLeftTableAction.setActionType(GtnUIFrameworkActionType.V8DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
		loadDualListBoxLeftTableAction.addActionParameter("Commercial Forecasting_customerDualListBox");
		actionConfigList.add(loadDualListBoxLeftTableAction);

		return actionConfigList;
	}

}
