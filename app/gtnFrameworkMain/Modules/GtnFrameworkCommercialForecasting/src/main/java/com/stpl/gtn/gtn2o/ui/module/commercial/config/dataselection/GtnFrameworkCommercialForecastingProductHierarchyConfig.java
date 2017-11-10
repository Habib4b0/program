package com.stpl.gtn.gtn2o.ui.module.commercial.config.dataselection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxSourceSubsetType;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUiFrameworkComboBoxSourceType;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingClassConstants;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingStringConstants;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingTableConstants;
import com.stpl.gtn.gtn2o.ws.commercial.constants.GtnWsCommercialForecastingConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingProductHierarchyConfig {

	public List<GtnUIFrameworkComponentConfig> getProductSelectionLayoutComponents(String parentComponentId) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addParentVerticalLayout(componentList, parentComponentId);
		return componentList;
	}

	private void addParentVerticalLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkLayoutConfig parentVerticalLayout = new GtnUIFrameworkLayoutConfig();
		parentVerticalLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig prodSelectionMainLayout = new GtnUIFrameworkComponentConfig();
		prodSelectionMainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodSelectionMainLayout.setComponentId("prodSelectionMainLayout");
		prodSelectionMainLayout.setAddToParent(Boolean.TRUE);
		prodSelectionMainLayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		prodSelectionMainLayout.setGtnLayoutConfig(parentVerticalLayout);
		prodSelectionMainLayout.setParentComponentId(parentComponentId);
		parentVerticalLayout.setSpacing(Boolean.TRUE);
		componentList.add(prodSelectionMainLayout);

		GtnUIFrameworkLayoutConfig prodSelectionHierarchyRelationshipLayout = new GtnUIFrameworkLayoutConfig();
		prodSelectionHierarchyRelationshipLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig hierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		hierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarchyRelationshipConfig.setComponentId("prodSelectionHierarchyRelationshipLayout");
		hierarchyRelationshipConfig.setAddToParent(Boolean.TRUE);
		hierarchyRelationshipConfig.setGtnLayoutConfig(prodSelectionHierarchyRelationshipLayout);
		hierarchyRelationshipConfig.setParentComponentId(prodSelectionMainLayout.getComponentId());
		hierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(hierarchyRelationshipConfig);

		GtnUIFrameworkLayoutConfig forecastLevelProductGroupLayout = new GtnUIFrameworkLayoutConfig();
		forecastLevelProductGroupLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig forecastLevelProductGroupConfig = new GtnUIFrameworkComponentConfig();
		forecastLevelProductGroupConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastLevelProductGroupConfig.setComponentId("forecastLevelProductGroupLayout");
		forecastLevelProductGroupConfig.setAddToParent(Boolean.TRUE);
		forecastLevelProductGroupConfig.setGtnLayoutConfig(forecastLevelProductGroupLayout);
		forecastLevelProductGroupConfig.setParentComponentId(prodSelectionMainLayout.getComponentId());
		forecastLevelProductGroupConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(forecastLevelProductGroupConfig);

		GtnUIFrameworkComponentConfig productSelectionInnerPanel = new GtnUIFrameworkComponentConfig();
		productSelectionInnerPanel.setComponentId("productSelectionInnerPanel");
		productSelectionInnerPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		productSelectionInnerPanel.setParentComponentId(prodSelectionMainLayout.getComponentId());
		productSelectionInnerPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionInnerPanel.setAddToParent(Boolean.TRUE);
		productSelectionInnerPanel.setSpacing(Boolean.TRUE);
		componentList.add(productSelectionInnerPanel);

		GtnUIFrameworkLayoutConfig productSelectionInnerLayout = new GtnUIFrameworkLayoutConfig();
		productSelectionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerConfig.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		productSelectionInnerConfig.setComponentId("productSelectionInnerLayout");
		productSelectionInnerConfig.setAddToParent(Boolean.TRUE);
		productSelectionInnerConfig.setGtnLayoutConfig(productSelectionInnerLayout);
		productSelectionInnerConfig.setParentComponentId(productSelectionInnerPanel.getComponentId());
		productSelectionInnerConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(productSelectionInnerConfig);

		GtnUIFrameworkLayoutConfig productSelectionInnerCsslayout = new GtnUIFrameworkLayoutConfig();
		productSelectionInnerCsslayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerCssLayoutConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerCssLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerCssLayoutConfig.setComponentId("productSelectionInnerCssLayout");
		productSelectionInnerCssLayoutConfig.setAddToParent(Boolean.TRUE);
		productSelectionInnerCssLayoutConfig.setGtnLayoutConfig(productSelectionInnerCsslayout);
		productSelectionInnerCssLayoutConfig.setParentComponentId(productSelectionInnerConfig.getComponentId());
		productSelectionInnerCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(productSelectionInnerCssLayoutConfig);

		addHierarchyRelationshipComponent(componentList, hierarchyRelationshipConfig.getComponentId());
		addForecastLevelProductGroupComponent(componentList);
		addProductLevelComponent(componentList);
		addDualListBoxComponent(componentList, productSelectionInnerConfig.getComponentId());
	}

	private void addHierarchyRelationshipComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkLayoutConfig prodSelectionHierarchyLookupLayout = new GtnUIFrameworkLayoutConfig();
		prodSelectionHierarchyLookupLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig hierarechylayoutConfig = new GtnUIFrameworkComponentConfig();
		hierarechylayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarechylayoutConfig.setComponentId("prodSelectionHierarchyLookupLayout");
		hierarechylayoutConfig.setParentComponentId(parentComponentId);
		hierarechylayoutConfig.setAddToParent(Boolean.TRUE);
		hierarechylayoutConfig.setGtnLayoutConfig(prodSelectionHierarchyLookupLayout);
		componentList.add(hierarechylayoutConfig);

		GtnUIFrameworkComponentConfig productHierarchytextFieldConfig = new GtnUIFrameworkComponentConfig();
		productHierarchytextFieldConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		productHierarchytextFieldConfig.setComponentId("productHierarchy");
		productHierarchytextFieldConfig.setComponentName("Hierarchy:");
		productHierarchytextFieldConfig.setAddToParent(Boolean.TRUE);
		productHierarchytextFieldConfig.setParentComponentId(hierarechylayoutConfig.getComponentId());

		List<GtnUIFrameWorkActionConfig> productHierarchytextFieldActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig productHierarchytextFieldActionConfig = new GtnUIFrameWorkActionConfig();
		productHierarchytextFieldActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		productHierarchytextFieldActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { "prodHierarchyLookupView", "Product Hierarchy Look Up",
						GtnFrameworkCssConstants.SEVEN_TWO_ZERO, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		productHierarchytextFieldActionConfigList.add(productHierarchytextFieldActionConfig);
		productHierarchytextFieldActionConfigList.add(addDualListBoxCustomAction(Collections.<String> emptyList()));
		productHierarchytextFieldConfig.setGtnUIFrameWorkActionConfigList(productHierarchytextFieldActionConfigList);

		componentList.add(productHierarchytextFieldConfig);

		GtnUIFrameworkLayoutConfig prodSelectionRelationShiplayout = new GtnUIFrameworkLayoutConfig();
		prodSelectionRelationShiplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig relationShiplayoutConfig = new GtnUIFrameworkComponentConfig();
		relationShiplayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		relationShiplayoutConfig.setComponentId("prodSelectionRelationShipLayout");
		relationShiplayoutConfig.setParentComponentId(parentComponentId);
		relationShiplayoutConfig.setAddToParent(Boolean.TRUE);
		relationShiplayoutConfig.setGtnLayoutConfig(prodSelectionRelationShiplayout);

		List<GtnUIFrameWorkActionConfig> relationShiplayoutActionList = new ArrayList<>();
		relationShiplayoutActionList.add(addDualListBoxCustomAction(Collections.<String> emptyList()));
		relationShiplayoutConfig.setGtnUIFrameWorkActionConfigList(relationShiplayoutActionList);

		componentList.add(relationShiplayoutConfig);

		GtnUIFrameworkComponentConfig prodSelectionRelationShipCombobox = new GtnUIFrameworkComponentConfig();
		prodSelectionRelationShipCombobox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		prodSelectionRelationShipCombobox.setComponentId("prodSelectionRelationShipCombobox");
		prodSelectionRelationShipCombobox.setComponentName("Relationship");
		prodSelectionRelationShipCombobox.setAddToParent(Boolean.TRUE);
		prodSelectionRelationShipCombobox.setParentComponentId(relationShiplayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig relationShipComboboxConfig = new GtnUIFrameworkComboBoxConfig();
		relationShipComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		relationShipComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);
		prodSelectionRelationShipCombobox.setGtnComboboxConfig(relationShipComboboxConfig);

		componentList.add(prodSelectionRelationShipCombobox);
	}

	private void addForecastLevelProductGroupComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
		GtnUIFrameworkComponentConfig productForecastLevelLayout = layoutConfig
				.getHorizontalLayoutConfig("productForecastLevelLayout", "forecastLevelProductGroupLayout");
		componentList.add(productForecastLevelLayout);

		GtnUIFrameworkComponentConfig prodSelectionForecastLevelComponentConfig = new GtnUIFrameworkComponentConfig();
		prodSelectionForecastLevelComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		prodSelectionForecastLevelComponentConfig.setComponentId("prodSelectionForecastLevel");
		prodSelectionForecastLevelComponentConfig.setComponentName(" Forecast Level:");
		prodSelectionForecastLevelComponentConfig.setParentComponentId(productForecastLevelLayout.getComponentId());
		prodSelectionForecastLevelComponentConfig.setAddToParent(Boolean.TRUE);

		/* Used for dependent configuration */
		prodSelectionForecastLevelComponentConfig.addDependentComponent("productLevelComboBox");
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig depentdableActionConfig = new GtnUIFrameWorkActionConfig();
		depentdableActionConfig.setActionType(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION);
		actionConfigList.add(depentdableActionConfig);
		actionConfigList.add(addDualListBoxCustomAction(Arrays.asList(new String[] { "prodSelectionForecastLevel" })));
		prodSelectionForecastLevelComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComboBoxConfig prodSelectionForecastLevelComboxboxConfig = new GtnUIFrameworkComboBoxConfig();
		prodSelectionForecastLevelComboxboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		prodSelectionForecastLevelComboxboxConfig
				.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_FORCAST_LEVEL);
		prodSelectionForecastLevelComponentConfig.setGtnComboboxConfig(prodSelectionForecastLevelComboxboxConfig);
		componentList.add(prodSelectionForecastLevelComponentConfig);

		GtnUIFrameworkComponentConfig productGroupLookupLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig("productGroupLookupLayout", "forecastLevelProductGroupLayout");
		componentList.add(productGroupLookupLayoutConfig);

		GtnUIFrameworkComponentConfig productGroupLookupComponentConfig = new GtnUIFrameworkComponentConfig();
		productGroupLookupComponentConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		productGroupLookupComponentConfig.setComponentId("productGroup");
		productGroupLookupComponentConfig.setComponentName("Product Group:");
		productGroupLookupComponentConfig.setAddToParent(Boolean.TRUE);
		productGroupLookupComponentConfig.setParentComponentId(productGroupLookupLayoutConfig.getComponentId());

		List<GtnUIFrameWorkActionConfig> productGroupLookupActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig productGroupLookupActionConfig = new GtnUIFrameWorkActionConfig();
		productGroupLookupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		productGroupLookupActionConfig
				.setActionParameterList(Arrays.asList(new Object[] { "prodGroupLookupView", "Product Group Look Up",
						GtnFrameworkCssConstants.SEVEN_TWO_ZERO, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		productGroupLookupActionConfigList.add(productGroupLookupActionConfig);
		productGroupLookupComponentConfig.setGtnUIFrameWorkActionConfigList(productGroupLookupActionConfigList);

		componentList.add(productGroupLookupComponentConfig);
	}

	private void addProductLevelComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
		GtnUIFrameworkComponentConfig productLevelLayout = layoutConfig.getHorizontalLayoutConfig("productLevelLayout",
				"productSelectionInnerCssLayout");
		componentList.add(productLevelLayout);

		GtnUIFrameworkComponentConfig productInnerLevel = new GtnUIFrameworkComponentConfig();
		productInnerLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		productInnerLevel.setComponentId("productLevelComboBox");
		productInnerLevel.setComponentName("Level:");
		productInnerLevel.setAddToParent(Boolean.TRUE);
		productInnerLevel.setParentComponentId(productLevelLayout.getComponentId());

		GtnUIFrameworkComboBoxConfig productInnerLevelConfig = new GtnUIFrameworkComboBoxConfig();
		productInnerLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productInnerLevelConfig.setSourceType(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SUBSET_OF_COMBOBOX);
		productInnerLevelConfig.setSourceComboboxId("prodSelectionForecastLevel");
		productInnerLevelConfig.setSubsetType(GtnUIFrameworkComboboxSourceSubsetType.BEFORE_SELECTED);
		productInnerLevelConfig.setInclusionOfSelected(Boolean.TRUE);
		productInnerLevel.setGtnComboboxConfig(productInnerLevelConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig initialLoadActionConfig = new GtnUIFrameWorkActionConfig();
		initialLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		initialLoadActionConfig.setActionParameterList(Arrays.asList(new Object[] {
				GtnFrameworkCommercialForecastingClassConstants.COMMERCIAL_FORECASTING_LEFT_TABLE_LOAD_ACTION,
				"prodSelectionDualListBoxComp", "prodSelectionRelationShipCombobox", "productLevelComboBox", "company",
				"businessUnit", "productGroup", "levelNo", "product selection", "returns", null }));

		GtnUIFrameWorkActionConfig dualListBoxLeftTableLoadAction = new GtnUIFrameWorkActionConfig();
		dualListBoxLeftTableLoadAction.setActionType(GtnUIFrameworkActionType.DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
		dualListBoxLeftTableLoadAction
				.setActionParameterList(Arrays.asList(new Object[] { "prodSelectionDualListBoxComp" }));

		GtnUIFrameWorkActionConfig dualListBoxHeaderChangeAction = new GtnUIFrameWorkActionConfig();
		dualListBoxHeaderChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dualListBoxHeaderChangeAction.setActionParameterList(Arrays.asList(new Object[] {
				GtnFrameworkCommercialForecastingClassConstants.COMMERCIAL_FORECASTING_DUALLIST_CONFIG_ACTION,
				"ResetLeftTableHeaders", "prodSelectionDualListBoxComp", "productLevelComboBox" }));

		actionConfigList.add(dualListBoxHeaderChangeAction);
		actionConfigList.add(initialLoadActionConfig);
		actionConfigList.add(dualListBoxLeftTableLoadAction);
		productInnerLevel.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(productInnerLevel);

	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig prodSelectionDualListBoxComponentConfig = new GtnUIFrameworkComponentConfig();
		prodSelectionDualListBoxComponentConfig.setComponentType(GtnUIFrameworkComponentType.DUALLISTBOX);
		prodSelectionDualListBoxComponentConfig.setComponentId("prodSelectionDualListBoxComp");
		prodSelectionDualListBoxComponentConfig.setComponentName("Product Selection");
		prodSelectionDualListBoxComponentConfig.setParentComponentId(parentComponentId);
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
		prodSelectionDualListBoxConfig.setModuleType(GtnFrameworkCommercialForecastingStringConstants.MODULE_TYPE);
		prodSelectionDualListBoxConfig.setLoadingLevel(0);

		prodSelectionDualListBoxConfig.setLeftTableURL(
				GtnWsCommercialForecastingConstants.COMMERCIAL_FORECASTING_PROD_HIERARCHY_DUAL_LIST_BOX_LEFT_TABLE_LOAD_SERVICE);
		prodSelectionDualListBoxConfig.setMoveRightURL(
				GtnWsCommercialForecastingConstants.COMMERCIAL_FORECASTING_PROD_HIERARCHY_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_SERVICE);

		List<String> recordHeader = Arrays.asList(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERICAL_FORECASTING_PROD_HIERARCHY_DUAL_LIST_BOX_FILE_RECORD_HEADER);
		prodSelectionDualListBoxConfig.setRecordHeader(recordHeader);

		prodSelectionDualListBoxComponentConfig.setGtnUIFrameworkDualListBoxConfig(prodSelectionDualListBoxConfig);
	}

	public GtnUIFrameWorkActionConfig addDualListBoxCustomAction(List<String> additionalParameters) {
		GtnUIFrameWorkActionConfig dualListBoxConfigurationOnReset = new GtnUIFrameWorkActionConfig();
		List<Object> actionParam = new ArrayList<>();
		actionParam.add(GtnFrameworkCommercialForecastingClassConstants.COMMERCIAL_FORECASTING_RESET_ACTION);
		actionParam.add("prodSelectionDualListBoxComp");
		if (!additionalParameters.isEmpty()) {
			actionParam.addAll(additionalParameters);
		}
		dualListBoxConfigurationOnReset.setActionParameterList(actionParam);
		dualListBoxConfigurationOnReset.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		return dualListBoxConfigurationOnReset;
	}
}
