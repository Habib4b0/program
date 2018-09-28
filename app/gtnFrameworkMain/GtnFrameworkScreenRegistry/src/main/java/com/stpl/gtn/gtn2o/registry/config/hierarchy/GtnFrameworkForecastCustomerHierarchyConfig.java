package com.stpl.gtn.gtn2o.registry.config.hierarchy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastDateValueChangeAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastInnerLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkForecastCustomerHierarchyConfig {
	private final GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public List<GtnUIFrameworkComponentConfig> getCustomerSelectionLayoutCompoents(String nameSpace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addCustomerSelectionLayout(componentList, nameSpace);
		return componentList;
	}

	private void addCustomerSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT, true, nameSpace + "_" + "customerSelectionPanel");
		customerSelectionLayout.setSpacing(true);
		customerSelectionLayout.setComponentWidth("125%");
		customerSelectionLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		componentList.add(customerSelectionLayout);

		addComponents(componentList, nameSpace);
		addCustomerSelectionInnerPanel(componentList, nameSpace);
	}

	private void addComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionHorizontalLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "customerSelectionHorizontalLayout", true,
				nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT);
		customerSelectionHorizontalLayout.addComponentStyle("stpl-margin-top-18");
		componentList.add(customerSelectionHorizontalLayout);

		GtnUIFrameworkComponentConfig customerSelectionCssLayout = configProvider.getCssLayoutConfig(
				nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT_CSS, true,
				nameSpace + "_" + "customerSelectionHorizontalLayout");
		customerSelectionCssLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerSelectionCssLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		customerSelectionCssLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(customerSelectionCssLayout);

		addHierarchy(componentList, nameSpace);
		addRelationship(componentList, nameSpace);
		addForecastLevel(componentList, nameSpace);
		addCustomerGroupLookup(componentList, nameSpace);
		addForecastEligibleDate(componentList, nameSpace);
	}

	private void addHierarchy(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig hierarchyLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "hierarchyLayout", true, nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT_CSS);
		componentList.add(hierarchyLayout);

		GtnUIFrameworkComponentConfig hierarchyName = new GtnUIFrameworkComponentConfig();
		hierarchyName.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		hierarchyName.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		hierarchyName.setComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY);
		hierarchyName.setComponentName("Hierarchy");
		hierarchyName.setAddToParent(true);
		hierarchyName.setParentComponentId(nameSpace + "_" + "hierarchyLayout");

		GtnUIFrameWorkActionConfig forecastCustomerSelectionHierarchypopupAction = new GtnUIFrameWorkActionConfig();
		forecastCustomerSelectionHierarchypopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		forecastCustomerSelectionHierarchypopupAction
				.addActionParameter("forecastingLandingScreen_customerHierarchyLookup");
		forecastCustomerSelectionHierarchypopupAction.addActionParameter("Forecast Customer Hierarchy LookUp");
		forecastCustomerSelectionHierarchypopupAction.addActionParameter("1000px");
		forecastCustomerSelectionHierarchypopupAction.addActionParameter("845px");
		hierarchyName.addGtnUIFrameWorkActionConfig(forecastCustomerSelectionHierarchypopupAction);
		componentList.add(hierarchyName);
	}

	private void addRelationship(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig relationshipLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "relationshipLayout", true, nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT_CSS);
		componentList.add(relationshipLayout);

		GtnUIFrameworkComponentConfig relationship = new GtnUIFrameworkComponentConfig();
		relationship.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		relationship.setComponentName("Relationship");
		relationship.setComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_SELECTION_RELATIONSHIP_WITH_UNDERSCORE);
		relationship.setAddToParent(true);
		relationship.setParentComponentId(nameSpace + "_" + "relationshipLayout");
		relationship.setVaadinComponentPlaceHolder(GtnFrameworkScreenRegisteryConstants.SELECTONE);

		GtnUIFrameworkComboBoxConfig relationshipConfig = new GtnUIFrameworkComboBoxConfig();
		relationship.setGtnComboboxConfig(relationshipConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		actionConfigList.add(dualListResetAction(nameSpace));

		GtnUIFrameWorkActionConfig forecastingCustomerHierarchyForecastLevelLoadAction = new GtnUIFrameWorkActionConfig();
		forecastingCustomerHierarchyForecastLevelLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		forecastingCustomerHierarchyForecastLevelLoadAction
				.addActionParameter(GtnCustomerSelectionForecastLevelLoadAction.class.getName());
		forecastingCustomerHierarchyForecastLevelLoadAction.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.FORECAST_CUST_HIER_LOOKUP_CONTROL_POP);
		forecastingCustomerHierarchyForecastLevelLoadAction
				.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_SELECTION_FORECASTLEVEL_WITH_UNDERSCORE);
		forecastingCustomerHierarchyForecastLevelLoadAction
				.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_SELECTION_RELATIONSHIP_WITH_UNDERSCORE);
		forecastingCustomerHierarchyForecastLevelLoadAction
				.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_RELATIONSHIP_VERSION);
		actionConfigList.add(forecastingCustomerHierarchyForecastLevelLoadAction);

		relationship.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig customerSelectionRelationshipVersionLayout = configProvider
				.getHorizontalLayoutConfig("customerSelectionRelationshipVersionLayout", true,
						relationshipLayout.getComponentId());
		customerSelectionRelationshipVersionLayout.setVisible(false);

		GtnUIFrameworkComponentConfig customerRelationshipVersion = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_RELATIONSHIP_VERSION, true,
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
				nameSpace + "_" + "forecastLevelLayout", true, nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT_CSS);
		componentList.add(forecastLevelLayout);

		GtnUIFrameworkComponentConfig forecastLevel = new GtnUIFrameworkComponentConfig();
		forecastLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		forecastLevel.setComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_SELECTION_FORECASTLEVEL_WITH_UNDERSCORE);
		forecastLevel.setComponentName("Forecast Level");
		forecastLevel.setAddToParent(true);
		forecastLevel.setParentComponentId(nameSpace + "_" + "forecastLevelLayout");
		forecastLevel.setVaadinComponentPlaceHolder(GtnFrameworkScreenRegisteryConstants.SELECTONE);

		GtnUIFrameworkComboBoxConfig forecastLevelConfig = new GtnUIFrameworkComboBoxConfig();
		forecastLevel.setGtnComboboxConfig(forecastLevelConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		actionConfigList.add(dualListResetAction(nameSpace));

		GtnUIFrameWorkActionConfig innerLevelLoadAction = new GtnUIFrameWorkActionConfig();
		innerLevelLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		innerLevelLoadAction.addActionParameter(GtnFrameworkForecastInnerLevelLoadAction.class.getName());
		innerLevelLoadAction.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY);
		innerLevelLoadAction.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_SELECTION_FORECASTLEVEL_WITH_UNDERSCORE);
		innerLevelLoadAction.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_SELECTION_LEVEL_WITH_UNDERSCORE);
		forecastLevel.addGtnUIFrameWorkActionConfig(innerLevelLoadAction);
		componentList.add(forecastLevel);
	}

	private void addCustomerGroupLookup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerGroupLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "customerGroupLayout", true, nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT_CSS);
		componentList.add(customerGroupLayout);

		GtnUIFrameworkComponentConfig customerGroup = new GtnUIFrameworkComponentConfig();
		customerGroup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		customerGroup.setComponentId(nameSpace + "_" + "customerGroup");
		customerGroup.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		customerGroup.setComponentName("Customer Group");
		customerGroup.setAddToParent(true);
		customerGroup.setParentComponentId(nameSpace + "_" + "customerGroupLayout");
		componentList.add(customerGroup);

		GtnUIFrameWorkActionConfig customerGroupActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.POPUP_ACTION);
		customerGroupActionConfig.addActionParameter("CustomerGroupLookupView");
		customerGroupActionConfig.addActionParameter("Customer Group Lookup");
		customerGroupActionConfig.addActionParameter("720");
		customerGroupActionConfig.addActionParameter("875");

		customerGroup.addGtnUIFrameWorkActionConfig(customerGroupActionConfig);
	}

	private void addForecastEligibleDate(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig forecastEligibleDateLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "forecastEligibleDateLayout", true, nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT);
		forecastEligibleDateLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(forecastEligibleDateLayout);

		GtnUIFrameworkComponentConfig forecastEligibleDate = new GtnUIFrameworkComponentConfig();
		forecastEligibleDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		forecastEligibleDate.setComponentId(nameSpace + "_customerSelectionForecastEligibilityDate");
		forecastEligibleDate.setComponentName("Forecast Eligible Date");
		forecastEligibleDate.setAddToParent(true);
		forecastEligibleDate.setParentComponentId(nameSpace + "_" + "forecastEligibleDateLayout");
		forecastEligibleDate.setComponentHight("20px");
		componentList.add(forecastEligibleDate);

		List<GtnUIFrameWorkActionConfig> levelactionConfigList = availableLoadActionList(nameSpace);

		GtnUIFrameWorkActionConfig dateValueChangeAction = new GtnUIFrameWorkActionConfig();
		dateValueChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dateValueChangeAction.addActionParameter(GtnFrameworkForecastDateValueChangeAction.class.getName());
		dateValueChangeAction.addActionParameter(levelactionConfigList);

		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		dateFieldConfig.addValueChangeActionConfig(dateValueChangeAction);
	}

	private void addCustomerSelectionInnerPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionInnerPanel = configProvider.getPanelConfig(
				nameSpace + "_" + "customerSelectionInnerPanel", true, nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT);
		customerSelectionInnerPanel.setSpacing(true);
		componentList.add(customerSelectionInnerPanel);

		GtnUIFrameworkComponentConfig customerSelectionInnerLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT_INNER, true,
				nameSpace + "_" + "customerSelectionInnerPanel");
		customerSelectionInnerLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		componentList.add(customerSelectionInnerLayout);

		GtnUIFrameworkComponentConfig customerSelectionInnerCssLayout = configProvider.getCssLayoutConfig(
				nameSpace + "_" + "customerSelectionInnerCssLayout", true,
				nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT_INNER);
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
		level.setComponentId(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_SELECTION_LEVEL_WITH_UNDERSCORE);
		level.setComponentName("Level");
		level.setAddToParent(true);
		level.setParentComponentId(nameSpace + "_" + "levelLayout");
		level.setVaadinComponentPlaceHolder(GtnFrameworkScreenRegisteryConstants.SELECTONE);

		GtnUIFrameworkComboBoxConfig levelConfig = new GtnUIFrameworkComboBoxConfig();
		level.setGtnComboboxConfig(levelConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = availableLoadActionList(nameSpace);

		level.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(level);
	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customerSelectionDualListBoxComponent = new GtnUIFrameworkComponentConfig();
		customerSelectionDualListBoxComponent.setComponentType(GtnUIFrameworkComponentType.V8_DUALLISTBOX);
		customerSelectionDualListBoxComponent.setComponentId(nameSpace + "_" + "customerDualListBox");
		customerSelectionDualListBoxComponent.setComponentName("Customer Selection");
		customerSelectionDualListBoxComponent.setParentComponentId(nameSpace + "_" + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY_ADD_CUST_SEL_LAYOUT_INNER);
		customerSelectionDualListBoxComponent.setAddToParent(true);

		GtnUIFrameworkValidationConfig customerSelectionDualListBoxValidationConfig = new GtnUIFrameworkValidationConfig();
		customerSelectionDualListBoxValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		customerSelectionDualListBoxComponent
				.setGtnUIFrameworkValidationConfig(customerSelectionDualListBoxValidationConfig);

		componentList.add(customerSelectionDualListBoxComponent);
		GtnUIFrameworkV8DualListBoxConfig customerSelectionDualListBoxConfig = new GtnUIFrameworkV8DualListBoxConfig();
		customerSelectionDualListBoxConfig.setLeftVisibleColumns(new Object[] { GtnFrameworkScreenRegisteryConstants.LEVEL_VALUE });
		customerSelectionDualListBoxConfig.setLeftVisibleHeaders(new String[] { "Level" });

		customerSelectionDualListBoxConfig.setRightVisibleHeaders(new String[] { "Customer Hierarchy Group Builder" });
		customerSelectionDualListBoxConfig.setRightVisibleColumns(new Object[] { GtnFrameworkScreenRegisteryConstants.LEVEL_VALUE });
		customerSelectionDualListBoxConfig.setRecordHeader(Arrays.asList("parent_relationrelationship_level_values",
				"parent_relationlevel_no", "parent_relationparent_node", "parent_relationrelationship_level_sid",
				"parent_relationhierarchy_no", "parent_relationrelationship_builder_sid", GtnFrameworkScreenRegisteryConstants.LEVEL_VALUE, "levelNo",
				"levelValueReference", "tableName", "fieldName", "level", "hierarchyLevelDefSid", "hierarchyDefSid",
				"hierarchyType"));
		customerSelectionDualListBoxConfig.setRightRecordHeader(
				Arrays.asList("levelNo", "relationshipLevelValues", "parentNode", "levelName", "levelValuReference",
						"tableName", "fieldName", "relationshipLevelSid", "hierarchyNo", "relationshipBuilderSid",
						"hierarchyLevelDefSid", "hierarchyDefSid", "versionNo", GtnFrameworkScreenRegisteryConstants.LEVEL_VALUE));
		customerSelectionDualListBoxConfig.setModuleType(GtnFrameworkForecastingStringConstants.HIERARCHY_RELATIONSHIP);
		customerSelectionDualListBoxConfig.setLeftTableURL("/loadAvailableTable");
		customerSelectionDualListBoxConfig.setMoveRightURL("/loadCustomerSelectedTable");
		customerSelectionDualListBoxConfig.setMoveAllDataURL("/loadBulkCustomerSelectedTable");

		customerSelectionDualListBoxComponent.setGtnUIFrameworkV8DualListBoxConfig(customerSelectionDualListBoxConfig);
	}

	private List<GtnUIFrameWorkActionConfig> availableLoadActionList(String nameSpace) {
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		actionConfigList.add(dualListResetAction(nameSpace));
		GtnUIFrameWorkActionConfig levelValueChangeAction = new GtnUIFrameWorkActionConfig();
		levelValueChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		levelValueChangeAction.addActionParameter(GtnCustomerAvailableTableLoadAction.class.getName());
		levelValueChangeAction.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE + GtnFrameworkScreenRegisteryConstants.CUSTOMER_HIERARCHY);
		levelValueChangeAction.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_SELECTION_RELATIONSHIP_WITH_UNDERSCORE);
		levelValueChangeAction.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_RELATIONSHIP_VERSION);
		levelValueChangeAction.addActionParameter(nameSpace + GtnFrameworkScreenRegisteryConstants.CUSTOMER_SELECTION_LEVEL_WITH_UNDERSCORE);
		levelValueChangeAction.addActionParameter(nameSpace + "_customerSelectionForecastEligibilityDate");
		levelValueChangeAction.addActionParameter(nameSpace + "_customerDualListBox");
		actionConfigList.add(levelValueChangeAction);

		GtnUIFrameWorkActionConfig loadDualListBoxLeftTableAction = new GtnUIFrameWorkActionConfig();
		loadDualListBoxLeftTableAction.setActionType(GtnUIFrameworkActionType.V8DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
		loadDualListBoxLeftTableAction.addActionParameter(nameSpace + "_customerDualListBox");
		actionConfigList.add(loadDualListBoxLeftTableAction);

		return actionConfigList;
	}

	private GtnUIFrameWorkActionConfig dualListResetAction(String nameSpace) {
		GtnUIFrameWorkActionConfig dualListResetAction = new GtnUIFrameWorkActionConfig();
		dualListResetAction.setActionType(GtnUIFrameworkActionType.V8CONFIRMED_DUALLISTBOX_RESET_ACTION);
		dualListResetAction.addActionParameter(nameSpace + "_" + "customerDualListBox");
		return dualListResetAction;
	}

}
