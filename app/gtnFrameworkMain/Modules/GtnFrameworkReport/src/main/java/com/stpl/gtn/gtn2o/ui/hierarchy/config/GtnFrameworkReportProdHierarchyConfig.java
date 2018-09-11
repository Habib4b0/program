package com.stpl.gtn.gtn2o.ui.hierarchy.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkComparisonLookupTextFieldEnableAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportResetAndCloseAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIReportCustomViewReloadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnProductLevelAvailableTableLoadAction;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
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

public class GtnFrameworkReportProdHierarchyConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	private String currentScreenNameSpace;

	public GtnFrameworkReportProdHierarchyConfig() {
		super();
	}

	public List<GtnUIFrameworkComponentConfig> getProductSelectionLayoutComponents(String namespace,
			String currentScreenNameSpace) {

		this.currentScreenNameSpace = currentScreenNameSpace;
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addParentVerticalLayout(componentList, namespace);
		addProductSelectionComponents(componentList, namespace);
		return componentList;
	}

	private void addParentVerticalLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig parentVerticalLayout = new GtnUIFrameworkLayoutConfig();
		parentVerticalLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig prodSelectionMainlayout = new GtnUIFrameworkComponentConfig();
		prodSelectionMainlayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodSelectionMainlayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_SELECTION_MAINLAYOUT);
		prodSelectionMainlayout.setAddToParent(true);
		prodSelectionMainlayout.setComponentWidth("130%");
		prodSelectionMainlayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		prodSelectionMainlayout.setGtnLayoutConfig(parentVerticalLayout);
		prodSelectionMainlayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productSelectionPanel");
		parentVerticalLayout.setSpacing(true);
		componentList.add(prodSelectionMainlayout);

		GtnUIFrameworkLayoutConfig hierarchyRelationshiplayout = new GtnUIFrameworkLayoutConfig();
		hierarchyRelationshiplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig hierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		hierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarchyRelationshipConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.HIERARCHY_RELATIONSHIP_LAYOUT);
		hierarchyRelationshipConfig.setAddToParent(true);
		hierarchyRelationshipConfig.setGtnLayoutConfig(hierarchyRelationshiplayout);
		hierarchyRelationshipConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_SELECTION_MAINLAYOUT);
		componentList.add(hierarchyRelationshipConfig);

		GtnUIFrameworkComponentConfig productSelectionInnerPanel = new GtnUIFrameworkComponentConfig();
		productSelectionInnerPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productSelectionInnerPanel");
		productSelectionInnerPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_SELECTION_MAINLAYOUT);
		productSelectionInnerPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionInnerPanel.setAddToParent(true);
		productSelectionInnerPanel.setSpacing(true);
		componentList.add(productSelectionInnerPanel);

		GtnUIFrameworkLayoutConfig productSelectionInnerlayout = new GtnUIFrameworkLayoutConfig();
		productSelectionInnerlayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerConfig.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		productSelectionInnerConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_SELECTION_INNERLAYOUT);
		productSelectionInnerConfig.setAddToParent(true);
		productSelectionInnerConfig.setGtnLayoutConfig(productSelectionInnerlayout);
		productSelectionInnerConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productSelectionInnerPanel");
		componentList.add(productSelectionInnerConfig);

		GtnUIFrameworkLayoutConfig productSelectionInnerCsslayout = new GtnUIFrameworkLayoutConfig();
		productSelectionInnerCsslayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerCssLayoutConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerCssLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerCssLayoutConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productSelectionInnerCssLayout");
		productSelectionInnerCssLayoutConfig.setAddToParent(true);
		productSelectionInnerCssLayoutConfig.setGtnLayoutConfig(productSelectionInnerCsslayout);
		productSelectionInnerCssLayoutConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productSelectionInnerlayout");
		productSelectionInnerCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(productSelectionInnerCssLayoutConfig);

	}

	private void addProductSelectionComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		addHierarchyRelationshipComponent(componentList, namespace);
		addCustomViewLayoutComponent(componentList, namespace);
		addDualListBoxComponent(componentList, namespace);

	}

	private void addHierarchyRelationshipComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig hierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		hierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarchyRelationshipConfig.setComponentId(GtnFrameworkReportStringConstants.HIERARCHY_RELATIONSHIP_CONFIG);
		hierarchyRelationshipConfig.setComponentName(GtnFrameworkReportStringConstants.HIERARCHY_RELATIONSHIP_CONFIG);
		hierarchyRelationshipConfig.setAddToParent(true);
		hierarchyRelationshipConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		hierarchyRelationshipConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_HIERARCHY_RELATIONSHIP_LAYOUT);
		hierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		hierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		hierarchyRelationshipConfig.setGtnLayoutConfig(layoutConf);
		componentList.add(hierarchyRelationshipConfig);

		GtnUIFrameworkComponentConfig hierarchyLayout = configProvider.getHorizontalLayoutConfig("hierarchyLayout",
				true, hierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig hierarchy = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRODUCTHIERARCHY,
				true, hierarchyLayout.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		hierarchyLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_18_PX);
		hierarchy.setComponentName("Hierarchy: ");

		GtnUIFrameWorkActionConfig hierarchypopupAction = new GtnUIFrameWorkActionConfig();
		hierarchypopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		hierarchypopupAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchyLookup");
		hierarchypopupAction.addActionParameter("Product Hierarchy LookUp");
		hierarchypopupAction.addActionParameter("1000px");
		hierarchypopupAction.addActionParameter("845px");
		hierarchypopupAction.addActionParameter(GtnFrameworkReportResetAndCloseAction.class.getName());
		hierarchypopupAction
				.addActionParameter(Arrays.asList(currentScreenNameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE));
		hierarchypopupAction.addActionParameter(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY));
		hierarchy.addGtnUIFrameWorkActionConfig(hierarchypopupAction);

		GtnUIFrameworkComponentConfig relationshipLayout = configProvider
				.getHorizontalLayoutConfig("relationshipLayout", true, hierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig relationship = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRODUCT_HIERARCHYRELATIONSHIP,
				true, relationshipLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		relationshipLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_18_PX);
		relationship.setComponentName("Relationship: ");
		relationship.setVaadinComponentPlaceHolder(GtnFrameworkReportStringConstants.SELECT_ONE_PLACE_HOLDER);

		GtnUIFrameworkComboBoxConfig relationshipLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.PRODUCT_RELATIONSHIP,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		relationship.setGtnComboboxConfig(relationshipLoadConfig);

		GtnUIFrameworkValidationConfig relationshipValidationConfig = new GtnUIFrameworkValidationConfig();
		relationshipValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		relationship.setGtnUIFrameworkValidationConfig(relationshipValidationConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig relationshipValueChangeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		relationshipValueChangeAction.addActionParameter(GtnRelationshipVersionLoadAction.class.getName());
		relationshipValueChangeAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_HIERARCHYRELATIONSHIP);
		relationshipValueChangeAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_RELATIONSHIP_VERSION);
		actionConfigList.add(relationshipValueChangeAction);

		GtnUIFrameWorkActionConfig loadForecastLavelAction = new GtnUIFrameWorkActionConfig();
		loadForecastLavelAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadForecastLavelAction.addActionParameter(GtnReportForecastLevelLoadAction.class.getName());
		loadForecastLavelAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_RELATIONSHIP_VERSION);
		loadForecastLavelAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCTHIERARCHY);
		loadForecastLavelAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_LEVEL);
		actionConfigList.add(loadForecastLavelAction);

		GtnUIFrameWorkActionConfig resetDualListBoxAction = new GtnUIFrameWorkActionConfig();
		resetDualListBoxAction.setActionType(GtnUIFrameworkActionType.V8CONFIRMED_DUALLISTBOX_RESET_ACTION);
		resetDualListBoxAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_DUALLISTBOX
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		actionConfigList.add(resetDualListBoxAction);

		relationship.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig productSelectionRelationshipVersionLayout = configProvider
				.getHorizontalLayoutConfig("productSelectionRelationshipVersionLayout", true,
						hierarchyRelationshipConfig.getComponentId());
		productSelectionRelationshipVersionLayout.setVisible(false);
		componentList.add(productSelectionRelationshipVersionLayout);

		GtnUIFrameworkComponentConfig productRelationshipVersion = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRODUCT_RELATIONSHIP_VERSION,
				true, productSelectionRelationshipVersionLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		productRelationshipVersion.setComponentName("ProductRelationshipVersion");

		GtnUIFrameworkComboBoxConfig productRelationshipVersionConfig = configProvider.getComboBoxConfig(
				GtnFrameworkForecastConstantCommon.RELATIONSHIP_VERSION,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productRelationshipVersionConfig.setHasDefaultValue(true);
		productRelationshipVersionConfig.setDefaultDesc("next");
		productRelationshipVersion.setGtnComboboxConfig(productRelationshipVersionConfig);
		componentList.add(productRelationshipVersion);

		GtnUIFrameworkComponentConfig levelLayout = configProvider.getHorizontalLayoutConfig("levelLayout", true,
				hierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig level = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRODUCT_LEVEL,
				true, levelLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		levelLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_18_PX);
		level.setComponentName("Level: ");
		level.addComboComponentStyle("v-report-width-200");
		level.setVaadinComponentPlaceHolder(GtnFrameworkReportStringConstants.SELECT_ONE_PLACE_HOLDER);

		GtnUIFrameworkComboBoxConfig levelLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkForecastConstantCommon.REPORT_FORECAST_LEVEL,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		level.setGtnComboboxConfig(levelLoadConfig);

		GtnUIFrameworkValidationConfig levelValidationConfig = new GtnUIFrameworkValidationConfig();
		levelValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		level.setGtnUIFrameworkValidationConfig(levelValidationConfig);

		List<GtnUIFrameWorkActionConfig> actionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig refreshDualListBoxAction = new GtnUIFrameWorkActionConfig();
		refreshDualListBoxAction.setActionType(GtnUIFrameworkActionType.V8DUAL_LISTBOX_RESET_ACTION);
		refreshDualListBoxAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_DUALLISTBOX
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		refreshDualListBoxAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_LEVEL);
		refreshDualListBoxAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_HIERARCHYRELATIONSHIP);
		actionList.add(refreshDualListBoxAction);

		GtnUIFrameWorkActionConfig loadAvailabletableActionConfig = new GtnUIFrameWorkActionConfig();
		loadAvailabletableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadAvailabletableActionConfig
				.setActionParameterList(Arrays.asList(GtnProductLevelAvailableTableLoadAction.class.getName(),
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.PRODUCTHIERARCHY,
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.PRODUCT_HIERARCHYRELATIONSHIP,
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.PRODUCT_RELATIONSHIP_VERSION,
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.PRODUCT_LEVEL,
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "businessUnit",
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerRelationshipVersion",
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productdualListBoxComp"));

		refreshDualListBoxAction.addActionParameter(loadAvailabletableActionConfig);

		GtnUIFrameWorkActionConfig loadLeftDualListBoxtableActionConfig = new GtnUIFrameWorkActionConfig();
		loadLeftDualListBoxtableActionConfig
				.setActionType(GtnUIFrameworkActionType.V8DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
		loadLeftDualListBoxtableActionConfig.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_DUALLISTBOX
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		refreshDualListBoxAction.addActionParameter(loadLeftDualListBoxtableActionConfig);

		level.setGtnUIFrameWorkActionConfigList(actionList);

		componentList.add(hierarchyLayout);
		componentList.add(hierarchy);
		componentList.add(relationshipLayout);
		componentList.add(relationship);
		componentList.add(levelLayout);
		componentList.add(level);
	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig productSelectionDualListBoxComponent = new GtnUIFrameworkComponentConfig();
		productSelectionDualListBoxComponent.setComponentType(GtnUIFrameworkComponentType.V8_DUALLISTBOX);
		productSelectionDualListBoxComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_DUALLISTBOX
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		productSelectionDualListBoxComponent.setComponentName("Product Selection");
		productSelectionDualListBoxComponent
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_SELECTION_INNERLAYOUT);
		productSelectionDualListBoxComponent.setAddToParent(true);

		GtnUIFrameworkValidationConfig productSelectionDualListBoxValidationConfig = new GtnUIFrameworkValidationConfig();
		productSelectionDualListBoxValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		productSelectionDualListBoxComponent
				.setGtnUIFrameworkValidationConfig(productSelectionDualListBoxValidationConfig);

		componentList.add(productSelectionDualListBoxComponent);
		GtnUIFrameworkV8DualListBoxConfig productSelectionDualListBoxConfig = new GtnUIFrameworkV8DualListBoxConfig();
		productSelectionDualListBoxConfig
				.setLeftVisibleColumns(new Object[] { GtnFrameworkReportStringConstants.LEVEL_VALUE });
		productSelectionDualListBoxConfig
				.setLeftVisibleHeaders(new String[] { GtnFrameworkReportStringConstants.LEVEL });

		productSelectionDualListBoxConfig.setRightVisibleHeaders(
				new String[] { GtnFrameworkReportStringConstants.PRODUCT_HIERARCHY_GROUP_BUILDER });
		productSelectionDualListBoxConfig
				.setRightVisibleColumns(new Object[] { GtnFrameworkReportStringConstants.LEVEL_VALUE });
		productSelectionDualListBoxConfig.setModuleType("report");
		productSelectionDualListBoxConfig.setRecordHeader(Arrays.asList("parent_relationrelationship_level_values",
				"parent_relationlevel_no", "parent_relationparent_node", "parent_relationrelationship_level_sid",
				"parent_relationhierarchy_no", "parent_relationrelationship_builder_sid", "levelValue", "levelNo",
				"levelValueReference", "tableName", "fieldName", "level", "hierarchyLevelDefSid", "hierarchyDefSid",
				"hierarchyType"));
		productSelectionDualListBoxConfig.setRightRecordHeader(
				Arrays.asList("levelNo", "relationshipLevelValues", "parentNode", "levelName", "levelValuReference",
						"tableName", "fieldName", "relationshipLevelSid", "hierarchyNo", "relationshipBuilderSid",
						"hierarchyLevelDefSid", "hierarchyDefSid", "versionNo", "levelValue"));
		productSelectionDualListBoxConfig.setLeftTableURL(GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_CUSTHIERARCHY_LEFT_TABLELOAD_SERVICE);
		productSelectionDualListBoxConfig.setMoveRightURL(GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_PRODHIERARCHY_RIGHT_TABLELOAD_SERVICE);
		productSelectionDualListBoxConfig.setMoveAllDataURL(GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_PRODHIERARCHY_ALL_DATA_TABLELOAD_SERVICE);
		productSelectionDualListBoxComponent.setGtnUIFrameworkV8DualListBoxConfig(productSelectionDualListBoxConfig);
	}

	private void addCustomViewLayoutComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkLayoutConfig reportCssLayout = new GtnUIFrameworkLayoutConfig();
		reportCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportCssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT,
				true, GtnFrameworkReportStringConstants.HIERARCHY_RELATIONSHIP_CONFIG,
				GtnUIFrameworkComponentType.LAYOUT);
		reportCssGtnLayout.setGtnLayoutConfig(reportCssLayout);
		componentList.add(reportCssGtnLayout);
		addCustomViewButtonComponent(componentList, nameSpace);
		addCustomViewComponent(componentList, nameSpace);
	}

	private void addCustomViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customViewConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW_BUTTON,
				true,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		customViewConfig.setComponentName("Custom View: ");
		customViewConfig.addComponentStyle(GtnFrameworkReportStringConstants.LINK);
		customViewConfig.setAuthorizationIncluded(true);

		componentList.add(customViewConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customViewPopupAction = new GtnUIFrameWorkActionConfig();
		customViewPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		customViewPopupAction.addActionParameter("dataSelection".equals(currentScreenNameSpace)
				? GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_DS : "reportCustomViewLookup");
		customViewPopupAction.addActionParameter("Custom Tree View pop-up");
		customViewPopupAction.addActionParameter("75%");
		customViewPopupAction.addActionParameter(null);
		actionConfigList.add(customViewPopupAction);

		customViewConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCustomViewComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW_COMBO_LAYOUT, true,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		gtnLayout.addComponentStyle("stpl-padding-top-13");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig customViewComboboxConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW,
				true, GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW_COMBO_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customViewComboboxConfig.setAuthorizationIncluded(true);
		customViewComboboxConfig.setComponentWsFieldId("customViewName");
		customViewComboboxConfig.setVaadinComponentPlaceHolder("-Select one-");
		customViewComboboxConfig.addComboComponentStyle("v-report-width-170");
		componentList.add(customViewComboboxConfig);

		GtnUIFrameworkComboBoxConfig customViewLoadConfig = configProvider.getComboBoxConfig("REPORT_CUSTOM_VIEW",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customViewComboboxConfig.setGtnComboboxConfig(customViewLoadConfig);

		GtnUIFrameWorkActionConfig reloadActionConfig = new GtnUIFrameWorkActionConfig();
		reloadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reloadActionConfig.addActionParameter(GtnFrameworkUIReportCustomViewReloadAction.class.getName());
		customViewComboboxConfig.setReloadActionConfig(reloadActionConfig);
		customViewComboboxConfig
				.setReloadLogicActionClassName(GtnFrameworkUIReportCustomViewReloadAction.class.getName());

		GtnUIFrameworkValidationConfig customViewValidationConfig = new GtnUIFrameworkValidationConfig();
		customViewValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		customViewComboboxConfig.setGtnUIFrameworkValidationConfig(customViewValidationConfig);

		GtnUIFrameWorkActionConfig enableComparisonLookupActionConfig = new GtnUIFrameWorkActionConfig();
		enableComparisonLookupActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		enableComparisonLookupActionConfig
				.addActionParameter(GtnFrameworkComparisonLookupTextFieldEnableAction.class.getName());
		enableComparisonLookupActionConfig.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_COMPARISON_CONFIG);
		enableComparisonLookupActionConfig
				.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonLookup");
		enableComparisonLookupActionConfig.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW);
		enableComparisonLookupActionConfig.addActionParameter(currentScreenNameSpace);
		customViewComboboxConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(enableComparisonLookupActionConfig));

	}
}
