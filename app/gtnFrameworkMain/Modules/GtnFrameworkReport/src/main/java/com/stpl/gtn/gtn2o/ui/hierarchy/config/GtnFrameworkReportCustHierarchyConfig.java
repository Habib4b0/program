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

public class GtnFrameworkReportCustHierarchyConfig {

	public List<GtnUIFrameworkComponentConfig> getCustomerSelectionLayoutComponents(String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		addCustomerSelectionParentVerticalLayout(componentList, namespace);
		addCustomerSelectionComponents(componentList, namespace);

		return componentList;
	}

	private void addCustomerSelectionParentVerticalLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig customerSelectionParentVerticalLayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionParentVerticalLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig custSelectionMainlayout = new GtnUIFrameworkComponentConfig();
		custSelectionMainlayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		custSelectionMainlayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUST_SELECTION_MAINLAYOUT);
		custSelectionMainlayout.setAddToParent(Boolean.TRUE);
		custSelectionMainlayout.setComponentWidth("125%");
		custSelectionMainlayout.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		custSelectionMainlayout.setGtnLayoutConfig(customerSelectionParentVerticalLayout);
		custSelectionMainlayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionPanel");
		customerSelectionParentVerticalLayout.setSpacing(Boolean.TRUE);
		componentList.add(custSelectionMainlayout);

		GtnUIFrameworkLayoutConfig customerSelectionHierarchyRelationshiplayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionHierarchyRelationshiplayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig hierarchyRelationshipConfig = new GtnUIFrameworkComponentConfig();
		hierarchyRelationshipConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarchyRelationshipConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.HIERARCHY_RELATIONSHIP_LAYOUT);
		hierarchyRelationshipConfig.setAddToParent(Boolean.TRUE);
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
		customerSelectionInnerPanel.setAddToParent(Boolean.TRUE);
		customerSelectionInnerPanel.setSpacing(Boolean.TRUE);
		componentList.add(customerSelectionInnerPanel);

		GtnUIFrameworkLayoutConfig customerSelectionInnerlayout = new GtnUIFrameworkLayoutConfig();
		customerSelectionInnerlayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig productSelectionInnerConfig = new GtnUIFrameworkComponentConfig();
		productSelectionInnerConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productSelectionInnerConfig.addComponentStyle(GtnUIFrameworkConstants.PADDING.toString());
		productSelectionInnerConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_SELECTION_INNERLAYOUT);
		productSelectionInnerConfig.setAddToParent(Boolean.TRUE);
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
		customerSelectionInnerCssLayoutConfig.setAddToParent(Boolean.TRUE);
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
		customerSelectionHierarchyRelationshipConfig.setAddToParent(Boolean.TRUE);
		customerSelectionHierarchyRelationshipConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerSelectionHierarchyRelationshipConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "hierarchyRelationshipLayout");
		customerSelectionHierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		customerSelectionHierarchyRelationshipConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		customerSelectionHierarchyRelationshipConfig.setGtnLayoutConfig(customerSelectionLayoutConf);
		componentList.add(customerSelectionHierarchyRelationshipConfig);

		GtnUIFrameworkComponentConfig customerSelectionHierarchyLayout = configProvider.getHorizontalLayoutConfig("customerSelectionHierarchyLayout",
				true, customerSelectionHierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig customerSelectionHierarchy = configProvider.getUIFrameworkComponentConfig(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"hierarchy", true,
				customerSelectionHierarchyLayout.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		customerSelectionHierarchy.setComponentName("Hierarchy: ");

		GtnUIFrameWorkActionConfig customerSelectionHierarchypopupAction = new GtnUIFrameWorkActionConfig();
		customerSelectionHierarchypopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		customerSelectionHierarchypopupAction.addActionParameter("customerHierarchyLookup");
		customerSelectionHierarchypopupAction.addActionParameter("Customer Hierarchy LookUp");
		customerSelectionHierarchypopupAction.addActionParameter("720");
		customerSelectionHierarchypopupAction.addActionParameter("875");
		customerSelectionHierarchy.addGtnUIFrameWorkActionConfig(customerSelectionHierarchypopupAction);

		GtnUIFrameworkComponentConfig customerSelectionRelationshipLayout = configProvider
				.getHorizontalLayoutConfig("customerSelectionRelationshipLayout", true, customerSelectionHierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig customerSelectionRelationship = configProvider.getUIFrameworkComponentConfig(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"customerSelectionRelationship", true,
				customerSelectionRelationshipLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		customerSelectionRelationship.setComponentName("Relationship: ");

		GtnUIFrameworkComboBoxConfig customerSelectionRelationshipLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerSelectionRelationship.setGtnComboboxConfig(customerSelectionRelationshipLoadConfig);

		GtnUIFrameworkComponentConfig customerSelectionLevelLayout = configProvider.getHorizontalLayoutConfig("customerSelectionLevelLayout", true,
				customerSelectionHierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig customerSelectionLevel = configProvider.getUIFrameworkComponentConfig(namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"customerSelectionLevel", true,
				customerSelectionLevelLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		customerSelectionLevel.setComponentName("Level: ");

		GtnUIFrameworkComboBoxConfig customerSelectionLevelLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customerSelectionLevel.setGtnComboboxConfig(customerSelectionLevelLoadConfig);

		GtnUIFrameworkComponentConfig customerSelectionForecastEligibilityDateLayout = configProvider.getHorizontalLayoutConfig(
				"customerSelectionForecastEligibilityDateLayout", true, customerSelectionHierarchyRelationshipConfig.getComponentId());

		GtnUIFrameworkComponentConfig customerSelectionForecastEligibilityDate = configProvider.getUIFrameworkComponentConfig(
				namespace+GtnFrameworkReportStringConstants.UNDERSCORE+"customerSelectionForecastEligibilityDate", true, customerSelectionForecastEligibilityDateLayout.getComponentId(),
				GtnUIFrameworkComponentType.DATEFIELD);
		customerSelectionForecastEligibilityDate.setComponentName("Forecast Eligibility Date: ");

		componentList.add(customerSelectionHierarchyLayout);
		componentList.add(customerSelectionHierarchy);
		componentList.add(customerSelectionRelationshipLayout);
		componentList.add(customerSelectionRelationship);
		componentList.add(customerSelectionLevelLayout);
		componentList.add(customerSelectionLevel);
		componentList.add(customerSelectionForecastEligibilityDateLayout);
		componentList.add(customerSelectionForecastEligibilityDate);
	}

	private void addDualListBoxComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig customerSelectionDualListBoxComponent = new GtnUIFrameworkComponentConfig();
		customerSelectionDualListBoxComponent.setComponentType(GtnUIFrameworkComponentType.DUALLISTBOX);
		customerSelectionDualListBoxComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		customerSelectionDualListBoxComponent.setComponentName("Customer Selection");
		customerSelectionDualListBoxComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionInnerlayout");
		customerSelectionDualListBoxComponent.setAddToParent(Boolean.TRUE);

		componentList.add(customerSelectionDualListBoxComponent);
		GtnUIFrameworkDualListBoxConfig customerSelectionDualListBoxConfig = new GtnUIFrameworkDualListBoxConfig();
		customerSelectionDualListBoxConfig
				.setLeftVisibleColumns(new Object[] { GtnFrameworkReportStringConstants.LEVEL_VALUE });
		customerSelectionDualListBoxConfig
				.setLeftVisibleHeaders(new String[] { GtnFrameworkReportStringConstants.LEVEL });

		customerSelectionDualListBoxConfig.setRightVisibleHeaders(
				new String[] { GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_GROUP_BUILDER });
		customerSelectionDualListBoxConfig
				.setRightVisibleColumns(new Object[] { GtnFrameworkReportStringConstants.LEVEL_VALUE });

		customerSelectionDualListBoxComponent.setGtnUIFrameworkDualListBoxConfig(customerSelectionDualListBoxConfig);
	}
}
