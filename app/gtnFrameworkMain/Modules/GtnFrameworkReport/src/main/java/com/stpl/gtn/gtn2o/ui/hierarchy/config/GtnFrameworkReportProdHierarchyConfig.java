package com.stpl.gtn.gtn2o.ui.hierarchy.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkReportProdHierarchyConfig {

	public List<GtnUIFrameworkComponentConfig> getProductSelectionLayoutComponents(String namespace) {

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
		addDualListBoxComponent(componentList, namespace);
	}

	private void addHierarchyRelationshipComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig hierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		hierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarchyRelationshipConfig.setComponentId("hierarchyRelationshipConfig");
		hierarchyRelationshipConfig.setComponentName("hierarchyRelationshipConfig");
		hierarchyRelationshipConfig.setAddToParent(true);
		hierarchyRelationshipConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		hierarchyRelationshipConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "hierarchyRelationshipLayout");
		hierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		hierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		hierarchyRelationshipConfig.setGtnLayoutConfig(layoutConf);
		componentList.add(hierarchyRelationshipConfig);

		GtnUIFrameworkComponentConfig hierarchyLayout = configProvider.getHorizontalLayoutConfig("hierarchyLayout",
				true, hierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig hierarchy = configProvider.getUIFrameworkComponentConfig(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"producthierarchy", true,
				hierarchyLayout.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		hierarchy.setComponentName("Hierarchy: ");

		GtnUIFrameWorkActionConfig hierarchypopupAction = new GtnUIFrameWorkActionConfig();
		hierarchypopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		hierarchypopupAction.addActionParameter("productHierarchyLookup");
		hierarchypopupAction.addActionParameter("Product Hierarchy LookUp");
		hierarchypopupAction.addActionParameter("720");
		hierarchypopupAction.addActionParameter("875");
		hierarchy.addGtnUIFrameWorkActionConfig(hierarchypopupAction);

		GtnUIFrameworkComponentConfig relationshipLayout = configProvider
				.getHorizontalLayoutConfig("relationshipLayout", true, hierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig relationship = configProvider.getUIFrameworkComponentConfig(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"relationship", true,
				relationshipLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		relationship.setComponentName("Relationship: ");

		GtnUIFrameworkComboBoxConfig relationshipLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		relationship.setGtnComboboxConfig(relationshipLoadConfig);

		GtnUIFrameworkComponentConfig levelLayout = configProvider.getHorizontalLayoutConfig("levelLayout", true,
				hierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig level = configProvider.getUIFrameworkComponentConfig(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"level", true,
				levelLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		level.setComponentName("Level: ");

		GtnUIFrameworkComboBoxConfig levelLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		level.setGtnComboboxConfig(levelLoadConfig);

		componentList.add(hierarchyLayout);
		componentList.add(hierarchy);
		componentList.add(relationshipLayout);
		componentList.add(relationship);
		componentList.add(levelLayout);
		componentList.add(level);
	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig productSelectionDualListBoxComponent = new GtnUIFrameworkComponentConfig();
		productSelectionDualListBoxComponent.setComponentType(GtnUIFrameworkComponentType.DUALLISTBOX);
		productSelectionDualListBoxComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		productSelectionDualListBoxComponent.setComponentName("Product Selection");
		productSelectionDualListBoxComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productSelectionInnerlayout");
		productSelectionDualListBoxComponent.setAddToParent(true);

		componentList.add(productSelectionDualListBoxComponent);
		GtnUIFrameworkDualListBoxConfig productSelectionDualListBoxConfig = new GtnUIFrameworkDualListBoxConfig();
		productSelectionDualListBoxConfig.setLeftVisibleColumns(new Object[] { GtnFrameworkReportStringConstants.LEVEL_VALUE });
		productSelectionDualListBoxConfig.setLeftVisibleHeaders(new String[] { GtnFrameworkReportStringConstants.LEVEL });

		productSelectionDualListBoxConfig.setRightVisibleHeaders(
				new String[] { GtnFrameworkReportStringConstants.PRODUCT_HIERARCHY_GROUP_BUILDER });
		productSelectionDualListBoxConfig.setRightVisibleColumns(new Object[] { GtnFrameworkReportStringConstants.LEVEL_VALUE });

		productSelectionDualListBoxComponent.setGtnUIFrameworkDualListBoxConfig(productSelectionDualListBoxConfig);
	}
}
