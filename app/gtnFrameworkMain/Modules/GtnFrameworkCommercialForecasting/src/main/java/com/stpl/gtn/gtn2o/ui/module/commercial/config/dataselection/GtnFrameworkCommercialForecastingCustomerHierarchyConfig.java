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

public class GtnFrameworkCommercialForecastingCustomerHierarchyConfig {

	public List<GtnUIFrameworkComponentConfig> getCustomerSelectionLayoutComponents(String parentComponentId) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addParentVerticalLayout(componentList, parentComponentId);
		return componentList;
	}

	private void addParentVerticalLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkLayoutConfig parentVerticalLayout = new GtnUIFrameworkLayoutConfig();
		parentVerticalLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig custSelectionMainlayout = new GtnUIFrameworkComponentConfig();
		custSelectionMainlayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		custSelectionMainlayout.setComponentId("custSelectionMainlayout");
		custSelectionMainlayout.setAddToParent(Boolean.TRUE);
		custSelectionMainlayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		custSelectionMainlayout.setGtnLayoutConfig(parentVerticalLayout);
		custSelectionMainlayout.setParentComponentId(parentComponentId);
		parentVerticalLayout.setSpacing(Boolean.TRUE);
		componentList.add(custSelectionMainlayout);

		GtnUIFrameworkLayoutConfig custHierarchyRelationshiplayout = new GtnUIFrameworkLayoutConfig();
		custHierarchyRelationshiplayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig custHierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		custHierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		custHierarchyRelationshipConfig.setComponentId("custHierarchyRelationshipLayout");
		custHierarchyRelationshipConfig.setAddToParent(Boolean.TRUE);
		custHierarchyRelationshipConfig.setGtnLayoutConfig(custHierarchyRelationshiplayout);
		custHierarchyRelationshipConfig.setParentComponentId(custSelectionMainlayout.getComponentId());
		custHierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(custHierarchyRelationshipConfig);

		GtnUIFrameworkLayoutConfig forecastLevelCustomerGrouplayout = new GtnUIFrameworkLayoutConfig();
		forecastLevelCustomerGrouplayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig forecastLevelCustomerGroupConfig = new GtnUIFrameworkComponentConfig();
		forecastLevelCustomerGroupConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastLevelCustomerGroupConfig.setComponentId("forecastLevelCustomerGrouplayout");
		forecastLevelCustomerGroupConfig.setAddToParent(Boolean.TRUE);
		forecastLevelCustomerGroupConfig.setGtnLayoutConfig(forecastLevelCustomerGrouplayout);
		forecastLevelCustomerGroupConfig.setParentComponentId(custSelectionMainlayout.getComponentId());
		forecastLevelCustomerGroupConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(forecastLevelCustomerGroupConfig);

		GtnUIFrameworkComponentConfig customerSelectionInnerPanel = new GtnUIFrameworkComponentConfig();
		customerSelectionInnerPanel.setComponentId("customerSelectionInnerPanel");
		customerSelectionInnerPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerSelectionInnerPanel.setParentComponentId(custSelectionMainlayout.getComponentId());
		customerSelectionInnerPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerSelectionInnerPanel.setAddToParent(Boolean.TRUE);
		customerSelectionInnerPanel.setSpacing(Boolean.TRUE);
		componentList.add(customerSelectionInnerPanel);

		GtnUIFrameworkLayoutConfig customerSelectionInnerlayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionInnerlayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig customerSelectionInnerConfig = new GtnUIFrameworkComponentConfig();
		customerSelectionInnerConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerSelectionInnerConfig.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		customerSelectionInnerConfig.setComponentId("customerSelectionInnerlayout");
		customerSelectionInnerConfig.setAddToParent(Boolean.TRUE);
		customerSelectionInnerConfig.setGtnLayoutConfig(customerSelectionInnerlayout);
		customerSelectionInnerConfig.setParentComponentId(customerSelectionInnerPanel.getComponentId());
		customerSelectionInnerConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(customerSelectionInnerConfig);

		GtnUIFrameworkLayoutConfig customerSelectionInnerCsslayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionInnerCsslayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig customerSelectionInnerCssLayoutConfig = new GtnUIFrameworkComponentConfig();
		customerSelectionInnerCssLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerSelectionInnerCssLayoutConfig.setComponentId("customerSelectionInnerCssLayout");
		customerSelectionInnerCssLayoutConfig.setAddToParent(Boolean.TRUE);
		customerSelectionInnerCssLayoutConfig.setGtnLayoutConfig(customerSelectionInnerCsslayout);
		customerSelectionInnerCssLayoutConfig.setParentComponentId(customerSelectionInnerConfig.getComponentId());
		customerSelectionInnerCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(customerSelectionInnerCssLayoutConfig);

		addCustomerHierarchyRelationshipComponent(componentList, custHierarchyRelationshipConfig.getComponentId());
		addForecastLevelCustomerGroupComponent(componentList);
		addCustomerLevelComponent(componentList);
		addCustomerDualListBoxComponent(componentList, customerSelectionInnerConfig.getComponentId());

	}

	private void addCustomerHierarchyRelationshipComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkLayoutConfig customerHierarchyLookuplayout = new GtnUIFrameworkLayoutConfig();
		customerHierarchyLookuplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig customerHierarechylayoutConfig = new GtnUIFrameworkComponentConfig();
		customerHierarechylayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerHierarechylayoutConfig.setComponentId("customerHierarchyLookuplayout");
		customerHierarechylayoutConfig.setParentComponentId(parentComponentId);
		customerHierarechylayoutConfig.setAddToParent(Boolean.TRUE);
		customerHierarechylayoutConfig.setGtnLayoutConfig(customerHierarchyLookuplayout);
		componentList.add(customerHierarechylayoutConfig);

		GtnUIFrameworkComponentConfig customerHierarchytextFieldConfig = new GtnUIFrameworkComponentConfig();
		customerHierarchytextFieldConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		customerHierarchytextFieldConfig.setComponentId("customerHierarchy");
		customerHierarchytextFieldConfig.setComponentName("Hierarchy:");
		customerHierarchytextFieldConfig.setAddToParent(Boolean.TRUE);
		customerHierarchytextFieldConfig.setParentComponentId(customerHierarechylayoutConfig.getComponentId());
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(
				Arrays.asList(new Object[] { "custHierarchyLookupView", "Customer Hierarchy Look Up",
						GtnFrameworkCssConstants.SEVEN_TWO_ZERO, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		list.add(conf);
		list.add(addDualListBoxCustomAction(Collections.<String> emptyList()));

		customerHierarchytextFieldConfig.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(customerHierarchytextFieldConfig);

		GtnUIFrameworkLayoutConfig customerRelationShiplayout = new GtnUIFrameworkLayoutConfig();
		customerRelationShiplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig customerRelationShiplayoutConfig = new GtnUIFrameworkComponentConfig();
		customerRelationShiplayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerRelationShiplayoutConfig.setComponentId("customerRelationShiplayout");
		customerRelationShiplayoutConfig.setParentComponentId(parentComponentId);
		customerRelationShiplayoutConfig.setAddToParent(Boolean.TRUE);
		customerRelationShiplayoutConfig.setGtnLayoutConfig(customerRelationShiplayout);

		List<GtnUIFrameWorkActionConfig> relationShiplayoutActionList = new ArrayList<>();
		relationShiplayoutActionList.add(addDualListBoxCustomAction(Collections.<String> emptyList()));
		customerRelationShiplayoutConfig.setGtnUIFrameWorkActionConfigList(relationShiplayoutActionList);

		componentList.add(customerRelationShiplayoutConfig);

		GtnUIFrameworkComponentConfig customerRelationShipCombobox = new GtnUIFrameworkComponentConfig();
		customerRelationShipCombobox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		customerRelationShipCombobox.setComponentId("customerRelationShipCombobox");
		customerRelationShipCombobox.setComponentName("Relationship");
		customerRelationShipCombobox.setAddToParent(Boolean.TRUE);
		customerRelationShipCombobox.setParentComponentId(customerRelationShiplayoutConfig.getComponentId());

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
				.getHorizontalLayoutConfig("customerForecastLevelLayout", "forecastLevelCustomerGrouplayout");
		componentList.add(customerForecastLevelLayout);

		GtnUIFrameworkComponentConfig customerForecastLevelComboxbox = new GtnUIFrameworkComponentConfig();
		customerForecastLevelComboxbox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		customerForecastLevelComboxbox.setComponentId("customerForecastLevel");
		customerForecastLevelComboxbox.setComponentName("Forecast Level:");
		customerForecastLevelComboxbox.setParentComponentId(customerForecastLevelLayout.getComponentId());
		customerForecastLevelComboxbox.setAddToParent(Boolean.TRUE);

		/* Used for dependent configuration */
		customerForecastLevelComboxbox.addDependentComponent("customerLevelComboBox");
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig depentdableActionConfig = new GtnUIFrameWorkActionConfig();
		depentdableActionConfig.setActionType(GtnUIFrameworkActionType.VALUE_CHANGE_ACTION);
		actionConfigList.add(depentdableActionConfig);
		actionConfigList.add(addDualListBoxCustomAction(Arrays.asList(new String[] { "customerForecastLevel" })));

		customerForecastLevelComboxbox.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComboBoxConfig customerForecastLevelComboxboxConfig = new GtnUIFrameworkComboBoxConfig();
		customerForecastLevelComboxboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerForecastLevelComboxboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.PRODUCT_FORCAST_LEVEL);
		customerForecastLevelComboxbox.setGtnComboboxConfig(customerForecastLevelComboxboxConfig);
		componentList.add(customerForecastLevelComboxbox);

		GtnUIFrameworkComponentConfig customerGroupLookuplayout = layoutConfig
				.getHorizontalLayoutConfig("customerGroupLookupLayout", "forecastLevelCustomerGrouplayout");
		componentList.add(customerGroupLookuplayout);

		GtnUIFrameworkComponentConfig customerGroupLookupConfig = new GtnUIFrameworkComponentConfig();
		customerGroupLookupConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		customerGroupLookupConfig.setComponentId("customerGroup");
		customerGroupLookupConfig.setComponentName("Customer Group:");
		customerGroupLookupConfig.setAddToParent(Boolean.TRUE);
		customerGroupLookupConfig.setParentComponentId(customerGroupLookuplayout.getComponentId());
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(Arrays.asList(new Object[] { "custGroupLookupView", "Customer Group Look Up",
				GtnFrameworkCssConstants.SEVEN_TWO_ZERO, GtnFrameworkCssConstants.EIGHT_SEVEN_FIVE }));
		list.add(conf);
		customerGroupLookupConfig.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(customerGroupLookupConfig);

	}

	private void addCustomerLevelComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
		GtnUIFrameworkComponentConfig customerLevellayout = layoutConfig
				.getHorizontalLayoutConfig("customerLevellayout", "customerSelectionInnerCssLayout");
		componentList.add(customerLevellayout);

		GtnUIFrameworkComponentConfig customerInnerLevel = new GtnUIFrameworkComponentConfig();
		customerInnerLevel.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		customerInnerLevel.setComponentId("customerLevelComboBox");
		customerInnerLevel.setComponentName("Level:");
		customerInnerLevel.setAddToParent(Boolean.TRUE);
		customerInnerLevel.setParentComponentId(customerLevellayout.getComponentId());
		GtnUIFrameworkComboBoxConfig customerInnerLevelConfig = new GtnUIFrameworkComboBoxConfig();
		customerInnerLevelConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerInnerLevelConfig.setSourceType(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SUBSET_OF_COMBOBOX);
		customerInnerLevelConfig.setSourceComboboxId("customerForecastLevel");
		customerInnerLevelConfig.setSubsetType(GtnUIFrameworkComboboxSourceSubsetType.BEFORE_SELECTED);
		customerInnerLevel.setGtnComboboxConfig(customerInnerLevelConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig initialLoadActionConfig = new GtnUIFrameWorkActionConfig();
		initialLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		initialLoadActionConfig.setActionParameterList(Arrays.asList(new Object[] {
				GtnFrameworkCommercialForecastingClassConstants.COMMERCIAL_FORECASTING_LEFT_TABLE_LOAD_ACTION,
				"customerDualListBoxComp", "customerRelationShipCombobox", "customerLevelComboBox", "company",
				"businessUnit", "customerGroup", "levelNo", "customer selection", "returns", null }));

		GtnUIFrameWorkActionConfig dualListBoxLeftTableLoadAction = new GtnUIFrameWorkActionConfig();
		dualListBoxLeftTableLoadAction.setActionType(GtnUIFrameworkActionType.DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
		dualListBoxLeftTableLoadAction
				.setActionParameterList(Arrays.asList(new Object[] { "customerDualListBoxComp" }));

		GtnUIFrameWorkActionConfig dualListBoxHeaderChangeAction = new GtnUIFrameWorkActionConfig();
		dualListBoxHeaderChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dualListBoxHeaderChangeAction.setActionParameterList(Arrays.asList(new Object[] {
				GtnFrameworkCommercialForecastingClassConstants.COMMERCIAL_FORECASTING_DUALLIST_CONFIG_ACTION,
				"ResetLeftTableHeaders", "customerDualListBoxComp", "customerLevelComboBox" }));

		actionConfigList.add(dualListBoxHeaderChangeAction);
		actionConfigList.add(initialLoadActionConfig);
		actionConfigList.add(dualListBoxLeftTableLoadAction);
		customerInnerLevel.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(customerInnerLevel);

	}

	private void addCustomerDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig customerDualListBoxConfig = new GtnUIFrameworkComponentConfig();
		customerDualListBoxConfig.setComponentType(GtnUIFrameworkComponentType.DUALLISTBOX);
		customerDualListBoxConfig.setComponentId("customerDualListBoxComp");
		customerDualListBoxConfig.setComponentName("Customer Selection");
		customerDualListBoxConfig.setParentComponentId(parentComponentId);
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
		customerDualListConfig.setModuleType(GtnFrameworkCommercialForecastingStringConstants.MODULE_TYPE);
		customerDualListConfig.setLoadingLevel(0);

		customerDualListConfig.setLeftTableURL(
				GtnWsCommercialForecastingConstants.COMMERCIAL_FORECASTING_CUST_HIERARCHY_DUAL_LIST_BOX_LEFT_TABLE_LOAD_SERVICE);
		customerDualListConfig.setMoveRightURL(
				GtnWsCommercialForecastingConstants.COMMERCIAL_FORECASTING_CUST_HIERARCHY_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_SERVICE);

		List<String> recordHeader = Arrays.asList(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERICAL_FORECASTING_CUST_HIERARCHY_DUAL_LIST_BOX_FILE_RECORD_HEADER);
		customerDualListConfig.setRecordHeader(recordHeader);

		customerDualListBoxConfig.setGtnUIFrameworkDualListBoxConfig(customerDualListConfig);
		componentList.add(customerDualListBoxConfig);

	}

	public GtnUIFrameWorkActionConfig addDualListBoxCustomAction(List<String> additionalParameters) {
		GtnUIFrameWorkActionConfig dualListBoxConfigurationOnReset = new GtnUIFrameWorkActionConfig();
		List<Object> actionParam = new ArrayList<>();
		actionParam.add(GtnFrameworkCommercialForecastingClassConstants.COMMERCIAL_FORECASTING_RESET_ACTION);
		actionParam.add("customerDualListBoxComp");
		if (!additionalParameters.isEmpty()) {
			actionParam.addAll(additionalParameters);
		}
		dualListBoxConfigurationOnReset.setActionParameterList(actionParam);
		dualListBoxConfigurationOnReset.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		return dualListBoxConfigurationOnReset;
	}
}
