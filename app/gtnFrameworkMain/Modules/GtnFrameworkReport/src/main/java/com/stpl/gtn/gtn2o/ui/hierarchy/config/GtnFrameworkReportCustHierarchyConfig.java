package com.stpl.gtn.gtn2o.ui.hierarchy.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.ForecastEligibilityDateValueChangeAction;
import com.stpl.gtn.gtn2o.ui.action.GtnCustomerAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportResetAndCloseAction;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnRelationshipVersionLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportForecastLevelLoadAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportCustHierarchyConfig {

	private String currentScreenNameSpace;

	public GtnFrameworkReportCustHierarchyConfig() {
		super();
	}

	public List<GtnUIFrameworkComponentConfig> getCustomerSelectionLayoutComponents(String namespace,
			String currentScreenNameSpace) {
		this.currentScreenNameSpace = currentScreenNameSpace;
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addCustomerSelectionParentVerticalLayout(componentList, namespace);
		addCustomerSelectionComponents(componentList, namespace);

		return componentList;
	}

	private void addCustomerSelectionParentVerticalLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig customerSelectionParentVerticalLayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionParentVerticalLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig custSelectionMainlayout = new GtnUIFrameworkComponentConfig();
		custSelectionMainlayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		custSelectionMainlayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUST_SELECTION_MAINLAYOUT);
		custSelectionMainlayout.setAddToParent(true);
		custSelectionMainlayout.setComponentWidth("125%");
		custSelectionMainlayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		custSelectionMainlayout.setGtnLayoutConfig(customerSelectionParentVerticalLayout);
		custSelectionMainlayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionPanel");
		customerSelectionParentVerticalLayout.setSpacing(true);
		componentList.add(custSelectionMainlayout);

		GtnUIFrameworkLayoutConfig customerSelectionHierarchyRelationshiplayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionHierarchyRelationshiplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig hierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		hierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarchyRelationshipConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.HIERARCHY_RELATIONSHIP_LAYOUT);
		hierarchyRelationshipConfig.addComponentStyle("stpl-margin-top-18");
		hierarchyRelationshipConfig.setAddToParent(true);
		hierarchyRelationshipConfig.setGtnLayoutConfig(customerSelectionHierarchyRelationshiplayout);
		hierarchyRelationshipConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUST_SELECTION_MAINLAYOUT);
		componentList.add(hierarchyRelationshipConfig);

		GtnUIFrameworkComponentConfig customerSelectionInnerPanel = new GtnUIFrameworkComponentConfig();
		customerSelectionInnerPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionInnerPanel");
		customerSelectionInnerPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUST_SELECTION_MAINLAYOUT);
		customerSelectionInnerPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerSelectionInnerPanel.setAddToParent(true);
		customerSelectionInnerPanel.setSpacing(true);
		componentList.add(customerSelectionInnerPanel);

		GtnUIFrameworkLayoutConfig customerSelectionInnerlayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionInnerlayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerConfig.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		productSelectionInnerConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_SELECTION_INNERLAYOUT);
		productSelectionInnerConfig.setAddToParent(true);
		productSelectionInnerConfig.setGtnLayoutConfig(customerSelectionInnerlayout);
		productSelectionInnerConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionInnerPanel");
		componentList.add(productSelectionInnerConfig);

		GtnUIFrameworkLayoutConfig customerSelectionInnerCsslayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionInnerCsslayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig customerSelectionInnerCssLayoutConfig = new GtnUIFrameworkComponentConfig();
		customerSelectionInnerCssLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerSelectionInnerCssLayoutConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionInnerCsslayout");
		customerSelectionInnerCssLayoutConfig.setAddToParent(true);
		customerSelectionInnerCssLayoutConfig.setGtnLayoutConfig(customerSelectionInnerCsslayout);
		customerSelectionInnerCssLayoutConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionInnerlayout");
		customerSelectionInnerCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(customerSelectionInnerCssLayoutConfig);

	}

	private void addCustomerSelectionComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		addHierarchyRelationshipComponent(componentList, namespace);
		addDualListBoxComponent(componentList, namespace);
	}

	private void addHierarchyRelationshipComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

		GtnUIFrameworkLayoutConfig customerSelectionLayoutConf = new GtnUIFrameworkLayoutConfig();
		customerSelectionLayoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig customerSelectionHierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		customerSelectionHierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerSelectionHierarchyRelationshipConfig.setComponentId("customerSelectionHierarchyRelationshipConfig");
		customerSelectionHierarchyRelationshipConfig.setComponentName("customerSelectionHierarchyRelationshipConfig");
		customerSelectionHierarchyRelationshipConfig.setAddToParent(true);
		customerSelectionHierarchyRelationshipConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerSelectionHierarchyRelationshipConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "hierarchyRelationshipLayout");
		customerSelectionHierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		customerSelectionHierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		customerSelectionHierarchyRelationshipConfig.setGtnLayoutConfig(customerSelectionLayoutConf);
		componentList.add(customerSelectionHierarchyRelationshipConfig);

		GtnUIFrameworkComponentConfig customerSelectionHierarchyLayout = configProvider.getHorizontalLayoutConfig(
				"customerSelectionHierarchyLayout", true,
				customerSelectionHierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig customerSelectionHierarchy = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY,
				true, customerSelectionHierarchyLayout.getComponentId(),
				GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		customerSelectionHierarchy.setComponentName("Hierarchy: ");

		GtnUIFrameWorkActionConfig customerSelectionHierarchypopupAction = new GtnUIFrameWorkActionConfig();
		customerSelectionHierarchypopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		customerSelectionHierarchypopupAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerHierarchyLookup");
		customerSelectionHierarchypopupAction.addActionParameter("Customer Hierarchy LookUp");
		customerSelectionHierarchypopupAction.addActionParameter("1000px");
		customerSelectionHierarchypopupAction.addActionParameter("845px");
		customerSelectionHierarchypopupAction.addActionParameter(GtnFrameworkReportResetAndCloseAction.class.getName());
		customerSelectionHierarchypopupAction
				.addActionParameter(Arrays.asList(currentScreenNameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE));
		customerSelectionHierarchypopupAction
				.addActionParameter(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY));
		customerSelectionHierarchy.addGtnUIFrameWorkActionConfig(customerSelectionHierarchypopupAction);

		GtnUIFrameworkComponentConfig customerSelectionRelationshipLayout = configProvider.getHorizontalLayoutConfig(
				"customerSelectionRelationshipLayout", true,
				customerSelectionHierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig customerSelectionRelationship = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_SELECTION_LEVEL_RELATION,
				true, customerSelectionRelationshipLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerSelectionRelationship.setComponentName("Relationship: ");
		customerSelectionRelationship
				.setVaadinComponentPlaceHolder(GtnFrameworkReportStringConstants.SELECT_ONE_PLACE_HOLDER);
		GtnUIFrameworkComboBoxConfig customerSelectionRelationshipLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.PRODUCT_RELATIONSHIP,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerSelectionRelationship.setGtnComboboxConfig(customerSelectionRelationshipLoadConfig);

		GtnUIFrameworkValidationConfig customerSelectionRelationshipValidationConfig = new GtnUIFrameworkValidationConfig();
		customerSelectionRelationshipValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		customerSelectionRelationship.setGtnUIFrameworkValidationConfig(customerSelectionRelationshipValidationConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig relationshipValueChangeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		relationshipValueChangeAction.addActionParameter(GtnRelationshipVersionLoadAction.class.getName());
		relationshipValueChangeAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_SELECTION_LEVEL_RELATION);
		relationshipValueChangeAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_RELATION_VERSION);
		actionConfigList.add(relationshipValueChangeAction);

		GtnUIFrameWorkActionConfig loadForecastLavelAction = new GtnUIFrameWorkActionConfig();
		loadForecastLavelAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadForecastLavelAction.addActionParameter(GtnReportForecastLevelLoadAction.class.getName());
		loadForecastLavelAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_RELATION_VERSION);
		loadForecastLavelAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY);
		loadForecastLavelAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_SELECTION_LEVEL);
		actionConfigList.add(loadForecastLavelAction);

		GtnUIFrameWorkActionConfig resetDualListBoxActiion = new GtnUIFrameWorkActionConfig();
		resetDualListBoxActiion.setActionType(GtnUIFrameworkActionType.V8CONFIRMED_DUALLISTBOX_RESET_ACTION);
		resetDualListBoxActiion.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_DUALLISTBOX);
		actionConfigList.add(resetDualListBoxActiion);

		customerSelectionRelationship.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig customerSelectionRelationshipVersionLayout = configProvider
				.getHorizontalLayoutConfig("customerSelectionRelationshipVersionLayout", true,
						customerSelectionHierarchyRelationshipConfig.getComponentId());
		customerSelectionRelationshipVersionLayout.setVisible(false);

		GtnUIFrameworkComponentConfig customerRelationshipVersion = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_RELATION_VERSION,
				true, customerSelectionRelationshipVersionLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerRelationshipVersion
				.setComponentName(GtnFrameworkReportStringConstants.REPORT_CUSTOMER_RELATION_VERSION);

		GtnUIFrameworkComboBoxConfig customerRelationshipVersionConfig = configProvider.getComboBoxConfig(
				GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerRelationshipVersionConfig.setHasDefaultValue(true);
		customerRelationshipVersionConfig.setDefaultDesc("next");
		customerRelationshipVersion.setGtnComboboxConfig(customerRelationshipVersionConfig);

		GtnUIFrameworkComponentConfig customerSelectionLevelLayout = configProvider.getHorizontalLayoutConfig(
				"customerSelectionLevelLayout", true, customerSelectionHierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig customerSelectionLevel = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_SELECTION_LEVEL,
				true, customerSelectionLevelLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerSelectionLevel.setComponentName("Level: ");
		customerSelectionLevel.setVaadinComponentPlaceHolder(GtnFrameworkReportStringConstants.SELECT_ONE_PLACE_HOLDER);
		GtnUIFrameworkComboBoxConfig customerSelectionLevelLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkForecastConstantCommon.REPORT_FORECAST_LEVEL,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerSelectionLevel.setGtnComboboxConfig(customerSelectionLevelLoadConfig);

		GtnUIFrameworkValidationConfig customerSelectionLevelValidationConfig = new GtnUIFrameworkValidationConfig();
		customerSelectionLevelValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		customerSelectionLevel.setGtnUIFrameworkValidationConfig(customerSelectionLevelValidationConfig);

		List<GtnUIFrameWorkActionConfig> levelactionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig refreshDualListBoxActiion = new GtnUIFrameWorkActionConfig();
		refreshDualListBoxActiion.setActionType(GtnUIFrameworkActionType.V8DUAL_LISTBOX_RESET_ACTION);
		refreshDualListBoxActiion.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_DUALLISTBOX);
		refreshDualListBoxActiion.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_SELECTION_LEVEL);
		refreshDualListBoxActiion.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_SELECTION_LEVEL_RELATION);
		levelactionConfigList.add(refreshDualListBoxActiion);

		GtnUIFrameWorkActionConfig levelValueChangeAction = new GtnUIFrameWorkActionConfig();
		levelValueChangeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		levelValueChangeAction.addActionParameter(GtnCustomerAvailableTableLoadAction.class.getName());
		levelValueChangeAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY);
		levelValueChangeAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_SELECTION_LEVEL_RELATION);
		levelValueChangeAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_RELATION_VERSION);
		levelValueChangeAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_SELECTION_LEVEL);
		levelValueChangeAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionForecastEligibilityDate");
		levelValueChangeAction
				.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerDualListBox");

		// Yes Action
		refreshDualListBoxActiion.addActionParameter(levelValueChangeAction);

		GtnUIFrameWorkActionConfig loadDualListBoxLeftTableAction = new GtnUIFrameWorkActionConfig();
		loadDualListBoxLeftTableAction.setActionType(GtnUIFrameworkActionType.V8DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
		loadDualListBoxLeftTableAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_DUALLISTBOX);

		refreshDualListBoxActiion.addActionParameter(loadDualListBoxLeftTableAction);

		customerSelectionLevel.setGtnUIFrameWorkActionConfigList(levelactionConfigList);

		GtnUIFrameworkComponentConfig customerSelectionForecastEligibilityDateLayout = configProvider
				.getHorizontalLayoutConfig("customerSelectionForecastEligibilityDateLayout", true,
						customerSelectionHierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig customerSelectionForecastEligibilityDate = configProvider
				.getUIFrameworkComponentConfig(
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ "customerSelectionForecastEligibilityDate",
						true, customerSelectionForecastEligibilityDateLayout.getComponentId(),
						GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		customerSelectionForecastEligibilityDate.setComponentName("Forecast Eligibility Date: ");
		customerSelectionForecastEligibilityDate.addComponentStyle("datefieldcentered");
		customerSelectionForecastEligibilityDate.setComponentHight("20px");

		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.addActionParameter(ForecastEligibilityDateValueChangeAction.class.getName());
		actionConfig.addActionParameter(levelactionConfigList);

		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		dateFieldConfig.addValueChangeActionConfig(actionConfig);
		customerSelectionForecastEligibilityDate.setGtnDateFieldConfig(dateFieldConfig);
		componentList.add(customerSelectionHierarchyLayout);
		componentList.add(customerSelectionHierarchy);
		componentList.add(customerSelectionRelationshipLayout);
		componentList.add(customerSelectionRelationship);
		componentList.add(customerSelectionRelationshipVersionLayout);
		componentList.add(customerRelationshipVersion);
		componentList.add(customerSelectionLevelLayout);
		componentList.add(customerSelectionLevel);
		componentList.add(customerSelectionForecastEligibilityDateLayout);
		componentList.add(customerSelectionForecastEligibilityDate);

	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig customerSelectionDualListBoxComponent = new GtnUIFrameworkComponentConfig();
		customerSelectionDualListBoxComponent.setComponentType(GtnUIFrameworkComponentType.V8_DUALLISTBOX);
		customerSelectionDualListBoxComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_DUALLISTBOX);
		customerSelectionDualListBoxComponent.setComponentName("Customer Selection");
		customerSelectionDualListBoxComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionInnerlayout");
		customerSelectionDualListBoxComponent.setAddToParent(true);

		GtnUIFrameworkValidationConfig customerSelectionDualListBoxValidationConfig = new GtnUIFrameworkValidationConfig();
		customerSelectionDualListBoxValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		customerSelectionDualListBoxComponent
				.setGtnUIFrameworkValidationConfig(customerSelectionDualListBoxValidationConfig);

		componentList.add(customerSelectionDualListBoxComponent);
		GtnUIFrameworkV8DualListBoxConfig customerSelectionDualListBoxConfig = new GtnUIFrameworkV8DualListBoxConfig();
		customerSelectionDualListBoxConfig
				.setLeftVisibleColumns(new Object[] { GtnFrameworkReportStringConstants.LEVEL_VALUE });
		customerSelectionDualListBoxConfig
				.setLeftVisibleHeaders(new String[] { GtnFrameworkReportStringConstants.LEVEL });

		customerSelectionDualListBoxConfig.setRightVisibleHeaders(
				new String[] { GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_GROUP_BUILDER });
		customerSelectionDualListBoxConfig
				.setRightVisibleColumns(new Object[] { GtnFrameworkReportStringConstants.LEVEL_VALUE });
		customerSelectionDualListBoxConfig.setRecordHeader(Arrays.asList("parent_relationrelationship_level_values",
				"parent_relationlevel_no", "parent_relationparent_node", "parent_relationrelationship_level_sid",
				"parent_relationhierarchy_no", "parent_relationrelationship_builder_sid",
				GtnFrameworkReportStringConstants.LEVEL_VALUE, "levelNo", "levelValueReference", "tableName",
				"fieldName", "level", "hierarchyLevelDefSid", "hierarchyDefSid", "hierarchyType"));
		customerSelectionDualListBoxConfig.setRightRecordHeader(Arrays.asList("levelNo", "relationshipLevelValues",
				"parentNode", "levelName", "levelValuReference", "tableName", "fieldName", "relationshipLevelSid",
				"hierarchyNo", "relationshipBuilderSid", "hierarchyLevelDefSid", "hierarchyDefSid", "versionNo",
				GtnFrameworkReportStringConstants.LEVEL_VALUE));
		customerSelectionDualListBoxConfig.setModuleType("");
		customerSelectionDualListBoxConfig
				.setLeftTableURL(GtnWsReportConstants.GTN_REPORT_CUSTHIERARCHY_LEFT_TABLELOAD_SERVICE);
		customerSelectionDualListBoxConfig
				.setMoveRightURL(GtnWsReportConstants.GTN_REPORT_CUSTHIERARCHY_RIGHT_TABLELOAD_SERVICE);
		customerSelectionDualListBoxConfig
				.setMoveAllDataURL(GtnWsReportConstants.GTN_REPORT_CUSTHIERARCHY_ALL_DATA_TABLELOAD_SERVICE);

		customerSelectionDualListBoxComponent.setGtnUIFrameworkV8DualListBoxConfig(customerSelectionDualListBoxConfig);
	}
}
