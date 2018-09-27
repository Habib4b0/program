package com.stpl.gtn.gtn2o.registry.config.hierarchy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnForecastingProductAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.registry.action.GtnFrameworkForecastInnerLevelLoadAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnFrameworkForecastCustomViewLoadAction;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
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

public class GtnFrameworkForecastProdHierarchyConfig {

	GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public List<GtnUIFrameworkComponentConfig> getProductSelectionLayoutComponents(String nameSpace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addProductSelectionLayout(componentList, nameSpace);
		return componentList;
	}

	private void addProductSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "productSelectionLayout", true, nameSpace + "_" + "productSelectionPanel");
		productSelectionLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		productSelectionLayout.setSpacing(true);
		componentList.add(productSelectionLayout);

		addComponents(componentList, nameSpace);
		addCustomerSelectionInnerPanel(componentList, nameSpace);
	}

	private void addComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionHorizontalLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "productSelectionHorizontalLayout", true, nameSpace + "_" + "productSelectionLayout");
		productSelectionHorizontalLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(productSelectionHorizontalLayout);

		GtnUIFrameworkComponentConfig productSelectionCssLayout = configProvider.getCssLayoutConfig(
				nameSpace + "_" + "productSelectionCssLayout", true,
				nameSpace + "_" + "productSelectionHorizontalLayout");
		componentList.add(productSelectionCssLayout);

		addHierarchy(componentList, nameSpace);
		addRelationship(componentList, nameSpace);
		addForecastLevel(componentList, nameSpace);
		addProductGroupLookup(componentList, nameSpace);
		addSalesCustomView(componentList, nameSpace);
		addDeductionCustomView(componentList, nameSpace);
	}

	private void addHierarchy(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig producthierarchyLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "producthierarchyLayout", true, nameSpace + "_" + "productSelectionCssLayout");
		componentList.add(producthierarchyLayout);

		GtnUIFrameworkComponentConfig hierarchyName = new GtnUIFrameworkComponentConfig();
		hierarchyName.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		hierarchyName.setComponentId(nameSpace + "_" + "prodhierarchyName");
		hierarchyName.setComponentName("Hierarchy");
		hierarchyName.setAddToParent(true);
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
				nameSpace + "_" + "prodrelationshipLayout", true, nameSpace + "_" + "productSelectionCssLayout");
		componentList.add(prodrelationshipLayout);

		GtnUIFrameworkComponentConfig relationship = new GtnUIFrameworkComponentConfig();
		relationship.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		relationship.setComponentId(nameSpace + "_" + "prodrelationship");
		relationship.setComponentName("Relationship");
		relationship.setAddToParent(true);
		relationship.setParentComponentId(nameSpace + "_" + "prodrelationshipLayout");
		relationship.setVaadinComponentPlaceHolder("-Select One-");
		relationship.addDependentComponent(nameSpace + "_" + "salesCustomView");
		relationship.addDependentComponent(nameSpace + "_" + "deductionCustomView");

		GtnUIFrameworkComboBoxConfig relationshipConfig = new GtnUIFrameworkComboBoxConfig();
		relationship.setGtnComboboxConfig(relationshipConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig valueChangeAction = new GtnUIFrameWorkActionConfig();
		valueChangeAction.setActionType(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION);

		componentList.add(relationship);

		GtnUIFrameWorkActionConfig forecastingProductHierarchyForecastLevelLoadAction = new GtnUIFrameWorkActionConfig();
		forecastingProductHierarchyForecastLevelLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		forecastingProductHierarchyForecastLevelLoadAction
				.addActionParameter(GtnCustomerSelectionForecastLevelLoadAction.class.getName());
		forecastingProductHierarchyForecastLevelLoadAction
				.addActionParameter(GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODHIERNAME);
		forecastingProductHierarchyForecastLevelLoadAction
				.addActionParameter(GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODFORECASTLEVEL);
		forecastingProductHierarchyForecastLevelLoadAction.addActionParameter(nameSpace + "_" + "prodrelationship");
		forecastingProductHierarchyForecastLevelLoadAction
				.addActionParameter(GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODRELATIONVERSION);
		actionConfigList.add(forecastingProductHierarchyForecastLevelLoadAction);
		actionConfigList.add(valueChangeAction);
		relationship.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig productSelectionRelationshipVersionLayout = configProvider
				.getHorizontalLayoutConfig("productSelectionRelationshipVersionLayout", true,
						prodrelationshipLayout.getComponentId());
		productSelectionRelationshipVersionLayout.setVisible(false);

		GtnUIFrameworkComponentConfig productRelationshipVersion = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODRELATIONVERSION, true,
				productSelectionRelationshipVersionLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		productRelationshipVersion.setComponentName("ProductRelationshipVersion");

		GtnUIFrameworkComboBoxConfig customerRelationshipVersionConfig = new GtnUIFrameworkComboBoxConfig();
		customerRelationshipVersionConfig.setHasDefaultValue(true);
		customerRelationshipVersionConfig.setDefaultDesc("next");
		productRelationshipVersion.setGtnComboboxConfig(customerRelationshipVersionConfig);

		componentList.add(productSelectionRelationshipVersionLayout);
		componentList.add(productRelationshipVersion);

	}

	private void addForecastLevel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig prodforecastLevelLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "prodforecastLevelLayout", true, nameSpace + "_" + "productSelectionCssLayout");
		componentList.add(prodforecastLevelLayout);

		GtnUIFrameworkComponentConfig forecastLevel = new GtnUIFrameworkComponentConfig();
		forecastLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		forecastLevel.setComponentId(nameSpace + "_" + "prodforecastLevel");
		forecastLevel.setComponentName("Forecast Level");
		forecastLevel.setAddToParent(true);
		forecastLevel.setParentComponentId(nameSpace + "_" + "prodforecastLevelLayout");
		forecastLevel.setVaadinComponentPlaceHolder("-Select One-");

		GtnUIFrameworkComboBoxConfig forecastLevelConfig = new GtnUIFrameworkComboBoxConfig();
		forecastLevel.setGtnComboboxConfig(forecastLevelConfig);

		GtnUIFrameWorkActionConfig innerProductLevelLoadAction = new GtnUIFrameWorkActionConfig();
		innerProductLevelLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		innerProductLevelLoadAction.addActionParameter(GtnFrameworkForecastInnerLevelLoadAction.class.getName());
		innerProductLevelLoadAction.addActionParameter(GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODHIERNAME);
		innerProductLevelLoadAction.addActionParameter(GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODFORECASTLEVEL);
		innerProductLevelLoadAction.addActionParameter(GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTLEVEL);
		forecastLevel.addGtnUIFrameWorkActionConfig(innerProductLevelLoadAction);
		componentList.add(forecastLevel);
	}

	private void addProductGroupLookup(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productGroupLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "productGroupLayout", true, nameSpace + "_" + "productSelectionCssLayout");
		componentList.add(productGroupLayout);

		GtnUIFrameworkComponentConfig productGroup = new GtnUIFrameworkComponentConfig();
		productGroup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		productGroup.setComponentId(nameSpace + "_" + "productGroup");
		productGroup.setComponentName("Product Group");
		productGroup.setAddToParent(true);
		productGroup.setParentComponentId(nameSpace + "_" + "productGroupLayout");
		componentList.add(productGroup);

		GtnUIFrameWorkActionConfig productGroupActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.POPUP_ACTION);
		productGroupActionConfig.addActionParameter("ProductGroupLookupView");
		productGroupActionConfig.addActionParameter("Product Group Lookup");
		productGroupActionConfig.addActionParameter("720");
		productGroupActionConfig.addActionParameter("875");
		productGroup.addGtnUIFrameWorkActionConfig(productGroupActionConfig);

	}

	private void addSalesCustomView(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig salesCustomViewLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "salesCustomViewLayout", true, nameSpace + "_" + "productSelectionCssLayout");
		componentList.add(salesCustomViewLayout);

		GtnUIFrameworkComponentConfig salesCustomView = new GtnUIFrameworkComponentConfig();
		salesCustomView.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		salesCustomView.setComponentId(nameSpace + "_" + "salesCustomView");
		salesCustomView.setComponentName("Sales Custom View");
		salesCustomView.setAddToParent(true);
		salesCustomView.setParentComponentId(nameSpace + "_" + "salesCustomViewLayout");
		salesCustomView.setVaadinComponentPlaceHolder("-Select One-");
		componentList.add(salesCustomView);

		GtnUIFrameworkComboBoxConfig salesCustomViewConfig = new GtnUIFrameworkComboBoxConfig();
		salesCustomViewConfig.setHasDefaultValue(true);
		salesCustomViewConfig.setDefaultDesc("next");
		GtnUIFrameWorkActionConfig loadCustomViewAction = new GtnUIFrameWorkActionConfig();
		loadCustomViewAction.addActionParameter(GtnFrameworkForecastCustomViewLoadAction.class.getName());
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "prodrelationship");
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "productRelationshipVersion");
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "customerSelectionRelationship");
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "customerRelationshipVersion");
		loadCustomViewAction.addActionParameter("salesCustomView");
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "salesCustomView");
		salesCustomView.setReloadActionConfig(loadCustomViewAction);
		salesCustomView.setReloadLogicActionClassName(GtnFrameworkForecastCustomViewLoadAction.class.getName());
		salesCustomView.setGtnComboboxConfig(salesCustomViewConfig);

	}

	private void addDeductionCustomView(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig deductionViewLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "deductionViewLayout", true, nameSpace + "_" + "productSelectionCssLayout");
		componentList.add(deductionViewLayout);

		GtnUIFrameworkComponentConfig deductionCustomView = new GtnUIFrameworkComponentConfig();
		deductionCustomView.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		deductionCustomView.setComponentId(nameSpace + "_" + "deductionCustomView");
		deductionCustomView.setComponentName("Deduction Custom View");
		deductionCustomView.setAddToParent(true);
		deductionCustomView.setParentComponentId(nameSpace + "_" + "deductionViewLayout");
		deductionCustomView.setVaadinComponentPlaceHolder("-Select One-");
		componentList.add(deductionCustomView);

		GtnUIFrameworkComboBoxConfig salesCustomViewConfig = new GtnUIFrameworkComboBoxConfig();
		salesCustomViewConfig.setHasDefaultValue(true);
		salesCustomViewConfig.setDefaultDesc("next");
		GtnUIFrameWorkActionConfig loadCustomViewAction = new GtnUIFrameWorkActionConfig();
		loadCustomViewAction.addActionParameter(GtnFrameworkForecastCustomViewLoadAction.class.getName());
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "prodrelationship");
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "productRelationshipVersion");
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "customerSelectionRelationship");
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "customerRelationshipVersion");
		loadCustomViewAction.addActionParameter("deductionCustomView");
		loadCustomViewAction.addActionParameter(nameSpace + "_" + "deductionCustomView");
		deductionCustomView.setReloadActionConfig(loadCustomViewAction);
		deductionCustomView.setReloadLogicActionClassName(GtnFrameworkForecastCustomViewLoadAction.class.getName());
		deductionCustomView.setGtnComboboxConfig(salesCustomViewConfig);
	}

	private void addCustomerSelectionInnerPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionInnerPanel = configProvider.getPanelConfig(
				nameSpace + "_" + "productSelectionInnerPanel", true, nameSpace + "_" + "productSelectionLayout");
		productSelectionInnerPanel.setSpacing(true);
		componentList.add(productSelectionInnerPanel);

		GtnUIFrameworkComponentConfig productSelectionInnerLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "productSelectionInnerLayout", true, nameSpace + "_" + "productSelectionInnerPanel");
		productSelectionInnerLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		componentList.add(productSelectionInnerLayout);

		GtnUIFrameworkComponentConfig productSelectionInnerCssLayout = configProvider.getCssLayoutConfig(
				nameSpace + "_" + "productSelectionInnerCssLayout", true,
				nameSpace + "_" + "productSelectionInnerLayout");
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
		level.setAddToParent(true);
		level.setParentComponentId(nameSpace + "_" + "productlevelLayout");
		level.setVaadinComponentPlaceHolder("-Select One-");

		GtnUIFrameworkComboBoxConfig levelConfig = new GtnUIFrameworkComboBoxConfig();
		level.setGtnComboboxConfig(levelConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadAvailabletableActionConfig = new GtnUIFrameWorkActionConfig();
		loadAvailabletableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadAvailabletableActionConfig
				.setActionParameterList(Arrays.asList(GtnForecastingProductAvailableTableLoadAction.class.getName(),
						GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODHIERNAME, GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODRELATIONSHIP,
						GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODRELATIONVERSION, GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTLEVEL,
						"Commercial Forecasting_businessUnit", GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTRELATIONVERSION,
						nameSpace + "_" + "productDualListBox"));
		actionConfigList.add(loadAvailabletableActionConfig);

		GtnUIFrameWorkActionConfig loadDualListBoxLeftTableAction = new GtnUIFrameWorkActionConfig();
		loadDualListBoxLeftTableAction.setActionType(GtnUIFrameworkActionType.V8DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
		loadDualListBoxLeftTableAction.addActionParameter(nameSpace + "_" + "productDualListBox");
		actionConfigList.add(loadDualListBoxLeftTableAction);

		level.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(level);
	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig productSelectionDualListBoxComponent = new GtnUIFrameworkComponentConfig();
		productSelectionDualListBoxComponent.setComponentType(GtnUIFrameworkComponentType.V8_DUALLISTBOX);
		productSelectionDualListBoxComponent.setComponentId(nameSpace + "_" + "productDualListBox");
		productSelectionDualListBoxComponent.setComponentName("Product Selection");
		productSelectionDualListBoxComponent.setParentComponentId(nameSpace + "_" + "productSelectionInnerLayout");
		productSelectionDualListBoxComponent.setAddToParent(true);

		GtnUIFrameworkValidationConfig productSelectionDualListBoxValidationConfig = new GtnUIFrameworkValidationConfig();
		productSelectionDualListBoxValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		productSelectionDualListBoxComponent
				.setGtnUIFrameworkValidationConfig(productSelectionDualListBoxValidationConfig);

		componentList.add(productSelectionDualListBoxComponent);
		GtnUIFrameworkV8DualListBoxConfig productSelectionDualListBoxConfig = new GtnUIFrameworkV8DualListBoxConfig();
		productSelectionDualListBoxConfig.setLeftVisibleColumns(new Object[] { "levelValue" });
		productSelectionDualListBoxConfig.setLeftVisibleHeaders(new String[] { "Level" });

		productSelectionDualListBoxConfig.setRightVisibleHeaders(new String[] { "Product Hierarchy Group Builder" });
		productSelectionDualListBoxConfig.setRightVisibleColumns(new Object[] { "levelValue" });
		productSelectionDualListBoxConfig.setModuleType(GtnFrameworkForecastingStringConstants.HIERARCHY_RELATIONSHIP);
		productSelectionDualListBoxConfig.setRecordHeader(Arrays.asList("parent_relationrelationship_level_values",
				"parent_relationlevel_no", "parent_relationparent_node", "parent_relationrelationship_level_sid",
				"parent_relationhierarchy_no", "parent_relationrelationship_builder_sid", "levelValue", "levelNo",
				"levelValueReference", "tableName", "fieldName", "level", "hierarchyLevelDefSid", "hierarchyDefSid",
				"hierarchyType"));
		productSelectionDualListBoxConfig.setRightRecordHeader(
				Arrays.asList("levelNo", "relationshipLevelValues", "parentNode", "levelName", "levelValuReference",
						"tableName", "fieldName", "relationshipLevelSid", "hierarchyNo", "relationshipBuilderSid",
						"hierarchyLevelDefSid", "hierarchyDefSid", "versionNo", "levelValue"));
		productSelectionDualListBoxConfig.setLeftTableURL("/loadAvailableTable");
		productSelectionDualListBoxConfig.setMoveRightURL("/loadProductSelectedTable");
		productSelectionDualListBoxConfig.setMoveAllDataURL("/loadBulkProductSelectedTable");
		productSelectionDualListBoxComponent.setGtnUIFrameworkV8DualListBoxConfig(productSelectionDualListBoxConfig);
	}
}
