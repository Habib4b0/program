package com.stpl.gtn.gtn2o.ui.config.dataselection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkForecastReturnLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsStringConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsTableConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxSourceSubsetType;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUiFrameworkComboBoxSourceType;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

public class GtnFrameworkForecastReturnProdHierarchyConfig {

	public List<GtnUIFrameworkComponentConfig> getProductSelectionLayoutComponents(String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addParentVerticalLayout(componentList, namespace);
		addComponents(componentList, namespace);
		return componentList;
	}

	private void addParentVerticalLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig parentVerticalLayout = new GtnUIFrameworkLayoutConfig();
		parentVerticalLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig prodSelectionMainlayout = new GtnUIFrameworkComponentConfig();
		prodSelectionMainlayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodSelectionMainlayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_SELECTION_MAINLAYOUT);
		prodSelectionMainlayout.setAddToParent(Boolean.TRUE);
		prodSelectionMainlayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		prodSelectionMainlayout.setGtnLayoutConfig(parentVerticalLayout);
		prodSelectionMainlayout.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productSelectionPanel");
		parentVerticalLayout.setSpacing(Boolean.TRUE);
		componentList.add(prodSelectionMainlayout);

		GtnUIFrameworkLayoutConfig hierarchyRelationshiplayout = new GtnUIFrameworkLayoutConfig();
		hierarchyRelationshiplayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig hierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		hierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarchyRelationshipConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.HIERARCHY_RELATIONSHIP_LAYOUT);
		hierarchyRelationshipConfig.setAddToParent(Boolean.TRUE);
		hierarchyRelationshipConfig.setGtnLayoutConfig(hierarchyRelationshiplayout);
		hierarchyRelationshipConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_SELECTION_MAINLAYOUT);
		hierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(hierarchyRelationshipConfig);

		GtnUIFrameworkLayoutConfig forecastLevelProductGrouplayout = new GtnUIFrameworkLayoutConfig();
		forecastLevelProductGrouplayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig forecastLevelProductGroupConfig = new GtnUIFrameworkComponentConfig();
		forecastLevelProductGroupConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastLevelProductGroupConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.FORECAST_LEVEL_PRODUCT_GROUPLAYOUT);
		forecastLevelProductGroupConfig.setAddToParent(Boolean.TRUE);
		forecastLevelProductGroupConfig.setGtnLayoutConfig(forecastLevelProductGrouplayout);
		forecastLevelProductGroupConfig.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_SELECTION_MAINLAYOUT);
		forecastLevelProductGroupConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(forecastLevelProductGroupConfig);

		GtnUIFrameworkComponentConfig productSelectionInnerPanel = new GtnUIFrameworkComponentConfig();
		productSelectionInnerPanel.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productSelectionInnerPanel");
		productSelectionInnerPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		productSelectionInnerPanel.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_SELECTION_MAINLAYOUT);
		productSelectionInnerPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionInnerPanel.setAddToParent(Boolean.TRUE);
		productSelectionInnerPanel.setSpacing(Boolean.TRUE);
		componentList.add(productSelectionInnerPanel);

		GtnUIFrameworkLayoutConfig productSelectionInnerlayout = new GtnUIFrameworkLayoutConfig();
		productSelectionInnerlayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerConfig.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		productSelectionInnerConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_SELECTION_INNERLAYOUT);
		productSelectionInnerConfig.setAddToParent(Boolean.TRUE);
		productSelectionInnerConfig.setGtnLayoutConfig(productSelectionInnerlayout);
		productSelectionInnerConfig.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productSelectionInnerPanel");
		productSelectionInnerConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(productSelectionInnerConfig);

		GtnUIFrameworkLayoutConfig productSelectionInnerCsslayout = new GtnUIFrameworkLayoutConfig();
		productSelectionInnerCsslayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerCssLayoutConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerCssLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerCssLayoutConfig.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productSelectionInnerCssLayout");
		productSelectionInnerCssLayoutConfig.setAddToParent(Boolean.TRUE);
		productSelectionInnerCssLayoutConfig.setGtnLayoutConfig(productSelectionInnerCsslayout);
		productSelectionInnerCssLayoutConfig.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productSelectionInnerlayout");
		productSelectionInnerCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(productSelectionInnerCssLayoutConfig);

	}

	private void addComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		addHierarchyRelationshipComponent(componentList, namespace);
		addForecastLevelProductGroupComponent(componentList, namespace);
		addProductLevelComponent(componentList, namespace);
		addDualListBoxComponent(componentList, namespace);
	}

	private void addHierarchyRelationshipComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkLayoutConfig hierarchyLookuplayout = new GtnUIFrameworkLayoutConfig();
		hierarchyLookuplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig hierarechylayoutConfig = new GtnUIFrameworkComponentConfig();
		hierarechylayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarechylayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "hierarchyLookuplayout");
		hierarechylayoutConfig.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "hierarchyRelationshipLayout");
		hierarechylayoutConfig.setAddToParent(Boolean.TRUE);
		hierarechylayoutConfig.setGtnLayoutConfig(hierarchyLookuplayout);
		componentList.add(hierarechylayoutConfig);

		GtnUIFrameworkComponentConfig productHierarchytextFieldConfig = new GtnUIFrameworkComponentConfig();
		productHierarchytextFieldConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		productHierarchytextFieldConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productHierarchy");
		productHierarchytextFieldConfig.setComponentName("Hierarchy:");
		productHierarchytextFieldConfig.setAddToParent(Boolean.TRUE);
		productHierarchytextFieldConfig.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "hierarchyLookuplayout");
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> popupChildActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(Arrays.asList(
				new Object[] { namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "prodHierarchyLookupView",
						"Product Hierarchy Look Up", "720", "875" }));
		conf.setChildActionList(popupChildActionList);
		list.add(conf);
		list.add(addDualListBoxCustomAction(Collections.<String> emptyList(), namespace));
		productHierarchytextFieldConfig.setGtnUIFrameWorkActionConfigList(list);
		GtnUIFrameworkValidationConfig valConfigForProductHierarchy = new GtnUIFrameworkValidationConfig();
		valConfigForProductHierarchy.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		productHierarchytextFieldConfig.setGtnUIFrameworkValidationConfig(valConfigForProductHierarchy);

		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productHierarchySearchResultTable", null);
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "hierarchyName", "");
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		popupChildActionList.add(resetTableConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.addActionParameter(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productHierarchySelectButton");
		popupChildActionList.add(disableAction);

		componentList.add(productHierarchytextFieldConfig);

		GtnUIFrameworkLayoutConfig relationShiplayout = new GtnUIFrameworkLayoutConfig();
		relationShiplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig relationShiplayoutConfig = new GtnUIFrameworkComponentConfig();
		relationShiplayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		relationShiplayoutConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "relationShiplayout");
		relationShiplayoutConfig.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "hierarchyRelationshipLayout");
		relationShiplayoutConfig.setAddToParent(Boolean.TRUE);
		relationShiplayoutConfig.setGtnLayoutConfig(relationShiplayout);
		componentList.add(relationShiplayoutConfig);

		GtnUIFrameworkComponentConfig relationShipCombobox = new GtnUIFrameworkComponentConfig();
		relationShipCombobox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		relationShipCombobox
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "relationShipCombobox");
		relationShipCombobox.setComponentName("Relationship");
		relationShipCombobox.setAddToParent(Boolean.TRUE);
		relationShipCombobox
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "relationShiplayout");
		GtnUIFrameworkComboBoxConfig relationShipComboboxConfig = new GtnUIFrameworkComboBoxConfig();
		relationShipComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		relationShipComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP);
		relationShipCombobox.setGtnComboboxConfig(relationShipComboboxConfig);
		List<GtnUIFrameWorkActionConfig> relationActionList = new ArrayList<>();
		relationActionList.add(addDualListBoxCustomAction(Collections.<String> emptyList(), namespace));
		relationShipCombobox.setGtnUIFrameWorkActionConfigList(relationActionList);
		GtnUIFrameworkValidationConfig valConfigForRelationShip = new GtnUIFrameworkValidationConfig();
		valConfigForRelationShip.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		relationShipCombobox.setGtnUIFrameworkValidationConfig(valConfigForRelationShip);
		componentList.add(relationShipCombobox);
	}

	private void addForecastLevelProductGroupComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnFrameworkForecastReturnLayoutsConfig layoutConfig = new GtnFrameworkForecastReturnLayoutsConfig();
		GtnUIFrameworkComponentConfig forecastLevelLayout = layoutConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "forecastLevelLayout",
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "forecastLevelProductGrouplayout");
		componentList.add(forecastLevelLayout);

		GtnUIFrameworkComponentConfig forecastLevelComboxbox = new GtnUIFrameworkComponentConfig();
		forecastLevelComboxbox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		forecastLevelComboxbox.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FORECAST_LEVEL);
		forecastLevelComboxbox.setComponentName(" Forecast Level:");
		forecastLevelComboxbox.setParentComponentId(forecastLevelLayout.getComponentId());
		forecastLevelComboxbox.setAddToParent(Boolean.TRUE);

		/* Used for dependent configuration */
		forecastLevelComboxbox.addDependentComponent(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_LEVEL_COMBO_BOX);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig depentdableActionConfig = new GtnUIFrameWorkActionConfig();
		depentdableActionConfig.setActionType(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION);
		actionConfigList.add(depentdableActionConfig);
		actionConfigList.add(addDualListBoxCustomAction(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FORECAST_LEVEL),
				namespace));

		forecastLevelComboxbox.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComboBoxConfig forecastLevelComboxboxConfig = new GtnUIFrameworkComboBoxConfig();
		forecastLevelComboxboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		forecastLevelComboxboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_FORCAST_LEVEL);
		forecastLevelComboxbox.setGtnComboboxConfig(forecastLevelComboxboxConfig);

		GtnUIFrameworkValidationConfig valConfigForForecastLevel = new GtnUIFrameworkValidationConfig();
		valConfigForForecastLevel.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		forecastLevelComboxbox.setGtnUIFrameworkValidationConfig(valConfigForForecastLevel);

		componentList.add(forecastLevelComboxbox);

		GtnUIFrameworkComponentConfig productGroupLookuplayout = layoutConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupLookupLayout",
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "forecastLevelProductGrouplayout");
		componentList.add(productGroupLookuplayout);

		GtnUIFrameworkComponentConfig productGroupLookupConfig = new GtnUIFrameworkComponentConfig();
		productGroupLookupConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		productGroupLookupConfig
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroup");
		productGroupLookupConfig.setComponentName("Product Group:");
		productGroupLookupConfig.setAddToParent(Boolean.TRUE);
		productGroupLookupConfig.setParentComponentId(productGroupLookuplayout.getComponentId());
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> popupChildActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(Arrays.asList(
				new Object[] { namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "prodGroupLookupView",
						"Product Group Look Up", "720", "875" }));
		conf.setChildActionList(popupChildActionList);
		list.add(conf);
		GtnUIFrameworkValidationConfig valConfigForProductGroup = new GtnUIFrameworkValidationConfig();
		valConfigForProductGroup.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		productGroupLookupConfig.setGtnUIFrameworkValidationConfig(valConfigForProductGroup);

		GtnUIFrameWorkActionConfig resetTableConfig = new GtnUIFrameWorkActionConfig();
		resetTableConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> resetSelectMap = new HashMap<>();
		List<Object> selectResetParams = new ArrayList<>();
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupSearchResultTable",
				null);
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupNo", "");
		resetSelectMap.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupName", "");
		selectResetParams.add(resetSelectMap);
		resetTableConfig.setActionParameterList(selectResetParams);
		popupChildActionList.add(resetTableConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.addActionParameter(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupSelectButton");
		popupChildActionList.add(disableAction);

		productGroupLookupConfig.setGtnUIFrameWorkActionConfigList(list);

		componentList.add(productGroupLookupConfig);
	}

	private void addProductLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnFrameworkForecastReturnLayoutsConfig layoutConfig = new GtnFrameworkForecastReturnLayoutsConfig();
		GtnUIFrameworkComponentConfig productLevellayout = layoutConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productLevellayout",
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productSelectionInnerCssLayout");
		componentList.add(productLevellayout);

		GtnUIFrameworkComponentConfig productInnerLevel = new GtnUIFrameworkComponentConfig();
		productInnerLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		productInnerLevel.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_LEVEL_COMBO_BOX);
		productInnerLevel.setComponentName("Level:");
		productInnerLevel.setAddToParent(Boolean.TRUE);
		productInnerLevel.setParentComponentId(productLevellayout.getComponentId());
		GtnUIFrameworkComboBoxConfig productInnerLevelConfig = new GtnUIFrameworkComboBoxConfig();
		productInnerLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productInnerLevelConfig.setComboBoxType("HierarchyLevels");

		productInnerLevelConfig.setSourceType(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SUBSET_OF_COMBOBOX);
		productInnerLevelConfig.setSourceComboboxId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FORECAST_LEVEL);
		productInnerLevelConfig.setSubsetType(GtnUIFrameworkComboboxSourceSubsetType.BEFORE_SELECTED);
		productInnerLevelConfig.setInclusionOfSelected(Boolean.TRUE);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig initialLoadActionConfig = new GtnUIFrameWorkActionConfig();
		initialLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		initialLoadActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_LEFT_TABLE_LOAD_ACTION,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "relationShipCombobox",
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.PRODUCT_LEVEL_COMBO_BOX,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "company",
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "businessUnit",
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroup", "levelNo",
						"product selection", "returns", getComponentIdBasedOnNamespace(namespace) }));

		GtnUIFrameWorkActionConfig dualListBoxLeftTableLoadAction = new GtnUIFrameWorkActionConfig();
		dualListBoxLeftTableLoadAction.setActionType(GtnUIFrameworkActionType.DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
		dualListBoxLeftTableLoadAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);

		GtnUIFrameWorkActionConfig dualListBoxHeaderChangeAction = new GtnUIFrameWorkActionConfig();
		List<Object> actionParam = new ArrayList<>();
		actionParam.add(GtnForecastReturnsClassConstants.RETURNS_FORECAST_DUALLIST_CONFIG_ACTION);
		actionParam.add("ResetLeftTableHeaders");
		actionParam.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		actionParam.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_LEVEL_COMBO_BOX);
		dualListBoxHeaderChangeAction.setActionParameterList(actionParam);
		dualListBoxHeaderChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		actionConfigList.add(dualListBoxHeaderChangeAction);
		actionConfigList.add(initialLoadActionConfig);
		actionConfigList.add(dualListBoxLeftTableLoadAction);

		productInnerLevel.setGtnUIFrameWorkActionConfigList(actionConfigList);
		productInnerLevel.setGtnComboboxConfig(productInnerLevelConfig);

		GtnUIFrameworkValidationConfig valConfigForForecastLevel = new GtnUIFrameworkValidationConfig();
		valConfigForForecastLevel.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		productInnerLevel.setGtnUIFrameworkValidationConfig(valConfigForForecastLevel);

		componentList.add(productInnerLevel);

	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig dualListBoxConfig = new GtnUIFrameworkComponentConfig();
		dualListBoxConfig.setComponentType(GtnUIFrameworkComponentType.DUALLISTBOX);
		dualListBoxConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		dualListBoxConfig.setComponentName("Product Selection");
		dualListBoxConfig.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productSelectionInnerlayout");
		dualListBoxConfig.setAddToParent(Boolean.TRUE);

		componentList.add(dualListBoxConfig);
		GtnUIFrameworkDualListBoxConfig config = new GtnUIFrameworkDualListBoxConfig();
		config.setLeftVisibleColumns(new Object[] { GtnForecastReturnsStringConstants.LEVEL_VALUE });
		config.setLeftVisibleHeaders(new String[] { GtnForecastReturnsStringConstants.LEVEL });

		config.setRightVisibleHeaders(
				new String[] { GtnForecastReturnsStringConstants.PRODUCT_HIERARCHY_GROUP_BUILDER });
		config.setRightVisibleColumns(new Object[] { GtnForecastReturnsStringConstants.LEVEL_VALUE });

		config.setModuleName(GtnForecastReturnsStringConstants.RETURNS);
		config.setModuleType(GtnForecastReturnsStringConstants.MODULE_TYPE);

		config.setLeftTableURL(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FROECAST_DUAL_LIST_BOX_LEFT_TABLE_LOAD_SERVICE);
		config.setMoveRightURL(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FROECAST_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_SERVICE);

		List<String> recordHeader = Arrays
				.asList(GtnForecastReturnsTableConstants.getGtnReturnsForecastDualListBoxFileRecordHeader());
		config.setRecordHeader(recordHeader);

		dualListBoxConfig.setGtnUIFrameworkDualListBoxConfig(config);
	}

	public GtnUIFrameWorkActionConfig addDualListBoxCustomAction(List<String> additionalParameters, String namespace) {
		GtnUIFrameWorkActionConfig dualListBoxConfigurationOnReset = new GtnUIFrameWorkActionConfig();
		List<Object> actionParam = new ArrayList<>();
		actionParam.add(GtnForecastReturnsClassConstants.RETURNS_FORECAST_RELATION_RESET_ACTION);
		actionParam.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		if (!additionalParameters.isEmpty()) {
			actionParam.addAll(additionalParameters);
		}
		dualListBoxConfigurationOnReset.setActionParameterList(actionParam);
		dualListBoxConfigurationOnReset.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		return dualListBoxConfigurationOnReset;
	}

	private String getComponentIdBasedOnNamespace(String namespace) {
		if (namespace.equals("returnsForecastTabSheet")) {
			return "projectionDetailsTabsheetMainLayout";
		}
		return null;
	}

}
