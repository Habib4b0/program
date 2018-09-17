/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkCVSaveValidationAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportCustomViewDeleteAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportCustomViewEditAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeRemoveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomVariableGridLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomVariablePositionChangeAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomViewHierarchyLoadAction;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.GtnUIFrameworkGridComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;

public class GtnFrameworkReportCustomViewLookup {

	private static final String LEVEL_VALUE = "levelValue";
	private final GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();
	private String tabName = "";

	public GtnUIFrameworkViewConfig getCustomViewLookUpViewLandingScreen(String tabName) {
		this.tabName = tabName;
		GtnUIFrameworkViewConfig view = getViewConfig("reportCustomViewLookup");
		addComponentList(view);
		return view;
	}

	public GtnUIFrameworkViewConfig getCustomViewLookUpViewDataSelection(String tabName) {
		this.tabName = tabName;
		GtnUIFrameworkViewConfig view = getViewConfig(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_DS);
		addComponentList(view);
		return view;
	}

	public GtnUIFrameworkViewConfig getCustomViewLookUpViewReportDashboard(String tabName) {
		this.tabName = tabName;
		GtnUIFrameworkViewConfig view = getViewConfig(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_RD);
		addComponentList(view);
		return view;
	}

	private GtnUIFrameworkViewConfig getViewConfig(String viewId) {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Custom Tree View Popup");
		view.setViewId(viewId);
		view.setDefaultView(false);
		view.setResetAllowed(true);
		GtnUIFrameWorkActionConfig treeLoadActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		treeLoadActionConfig.addActionParameter(GtnFrameworkReportCustomViewEditAction.class.getName());
		treeLoadActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		treeLoadActionConfig.addActionParameter(viewId);
		view.addViewAction(treeLoadActionConfig);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addViewDetailsPanel(componentList, tabName);
		addViewSelectionPanel(componentList, tabName);
		addControlButtonLayout(componentList);

	}

	private void addViewDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig viewDetailsPanel = new GtnUIFrameworkComponentConfig();
		viewDetailsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		viewDetailsPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "viewDetailsPanel");
		viewDetailsPanel.setComponentName("View Details");
		viewDetailsPanel.addComponentStyle("stpl-margin-left-5px");
		viewDetailsPanel.setMargin(true);
		viewDetailsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		viewDetailsPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(viewDetailsPanel);
		addViewDetailsLayout(componentList, namespace);
	}

	private void addViewSelectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig viewSelectionPanel = new GtnUIFrameworkComponentConfig();
		viewSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		viewSelectionPanel
				.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "viewSelectionPanel");
		viewSelectionPanel.setComponentName("View Selection");
		viewSelectionPanel.setMargin(true);
		viewSelectionPanel.addComponentStyle("stpl-margin-left-5px");
		viewSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		viewSelectionPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(viewSelectionPanel);
		searchCriteriaLayout(componentList, viewSelectionPanel.getComponentId());
	}

	private void addViewDetailsLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig viewDetailsLayout = new GtnUIFrameworkComponentConfig();
		viewDetailsLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		viewDetailsLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);
		viewDetailsLayout.setComponentName("Search Criteria");
		viewDetailsLayout.setAddToParent(true);
		viewDetailsLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		viewDetailsLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		viewDetailsLayout
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "viewDetailsPanel");
		GtnUIFrameworkLayoutConfig viewDetailsConfig = new GtnUIFrameworkLayoutConfig();
		viewDetailsConfig.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		viewDetailsLayout.setGtnLayoutConfig(viewDetailsConfig);
		viewDetailsLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		viewDetailsLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(viewDetailsLayout);

		addHierarchyNameTextBox(componentList, namespace);
		addFieldLayout(componentList, namespace);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig fieldLayout = new GtnUIFrameworkComponentConfig();
		fieldLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fieldLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FIELD_LAYOUT);
		fieldLayout.addComponentStyle("stpl-margin-left-19");
		fieldLayout.addComponentStyle("stpl-margin-top-12");
		fieldLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		fieldLayout.setAddToParent(true);
		fieldLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);

		GtnUIFrameworkLayoutConfig fieldVerticalLayout = new GtnUIFrameworkLayoutConfig();
		fieldVerticalLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		fieldLayout.setGtnLayoutConfig(fieldVerticalLayout);
		componentList.add(fieldLayout);
		addVariablePositionOptionGroup(componentList, namespace);
		addVariableTypeOptionGroup(componentList, namespace);
	}

	private void addVariableTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig variableTypeLayout = new GtnUIFrameworkComponentConfig();
		variableTypeLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableTypeLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableTypeLayout");
		variableTypeLayout.setAddToParent(true);
		variableTypeLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FIELD_LAYOUT);

		GtnUIFrameworkLayoutConfig variableTypeLayoutConfig = new GtnUIFrameworkLayoutConfig();
		variableTypeLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		variableTypeLayout.setGtnLayoutConfig(variableTypeLayoutConfig);
		componentList.add(variableTypeLayout);

		GtnUIFrameworkComponentConfig variableTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		variableTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		variableTypeOptionGroup.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VARIABLE_TYPE_OPTION_GROUP);
		variableTypeOptionGroup.setComponentName("Variable Type: ");
		variableTypeOptionGroup.addComponentStyle("stpl-margin-left-47px");
		variableTypeOptionGroup.setAddToParent(true);
		variableTypeOptionGroup
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableTypeLayout");
		GtnUIFrameworkOptionGroupConfig optionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		optionGroupConfig.setItemValues(Arrays.asList("Expandable", "Static"));
		optionGroupConfig.setValuesFromService(false);
		optionGroupConfig.setDefaultSelection("Expandable");
		variableTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		variableTypeOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		variableTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(optionGroupConfig);

		GtnUIFrameWorkActionConfig variableLoadConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableLoadConfig.addActionParameter(GtnFrameworkUICustomVariableGridLoadAction.class.getName());
		variableLoadConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VARIABLE_TYPE_OPTION_GROUP);
		variableLoadConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);
		variableLoadConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		variableLoadConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_CUSTOMER_TABLE);
		variableLoadConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_PRODUCT_TABLE);
		variableLoadConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_DEDUCTION_TABLE);
		variableLoadConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);

		variableTypeOptionGroup.addGtnUIFrameWorkActionConfig(variableLoadConfig);
		componentList.add(variableTypeOptionGroup);
	}

	private void addVariablePositionOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig variablePositionLayout = new GtnUIFrameworkComponentConfig();
		variablePositionLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variablePositionLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variablePositionLayout");
		variablePositionLayout.setAddToParent(true);
		variablePositionLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FIELD_LAYOUT);

		GtnUIFrameworkLayoutConfig variablePositionLayoutConfig = new GtnUIFrameworkLayoutConfig();
		variablePositionLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		variablePositionLayout.setGtnLayoutConfig(variablePositionLayoutConfig);
		componentList.add(variablePositionLayout);

		GtnUIFrameworkComponentConfig variablePositionOptionGroup = new GtnUIFrameworkComponentConfig();
		variablePositionOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		variablePositionOptionGroup.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VARIABLE_POSITION_OPTION_GROUP);
		variablePositionOptionGroup.addComponentStyle("stpl-margin-left-27");
		variablePositionOptionGroup.setComponentName("Variable Position: ");
		variablePositionOptionGroup.setAddToParent(true);
		variablePositionOptionGroup.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variablePositionLayout");
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList("Rows", "Columns"));
		comboConfig.setValuesFromService(false);
		variablePositionOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		variablePositionOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		variablePositionOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		GtnUIFrameWorkActionConfig valuChangeConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		valuChangeConfig.addActionParameter(GtnFrameworkUICustomVariablePositionChangeAction.class.getName());
		valuChangeConfig.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VARIABLE_TYPE_OPTION_GROUP);
		valuChangeConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);
		valuChangeConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		valuChangeConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.ADD_VARIABLES_BTN);
		valuChangeConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.REMOVE_VARIABLES_BTN);
		valuChangeConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_CUSTOMER_TABLE);
		valuChangeConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_PRODUCT_TABLE);
		valuChangeConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_DEDUCTION_TABLE);
		valuChangeConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);

		variablePositionOptionGroup.addGtnUIFrameWorkActionConfig(valuChangeConfig);
		componentList.add(variablePositionOptionGroup);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig hierarchyNameLayout = new GtnUIFrameworkComponentConfig();
		hierarchyNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		hierarchyNameLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "hierarchyNameLayout");
		hierarchyNameLayout.setAddToParent(true);
		hierarchyNameLayout.addComponentStyle("stpl-margin-left-47px");
		hierarchyNameLayout.addComponentStyle("stpl-margin-top-17");
		hierarchyNameLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);

		GtnUIFrameworkLayoutConfig hierarchyNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		hierarchyNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		hierarchyNameLayout.setGtnLayoutConfig(hierarchyNameLayoutConfig);
		componentList.add(hierarchyNameLayout);

		GtnUIFrameworkComponentConfig hierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		hierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		hierarchyNameTextBox.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		hierarchyNameTextBox.setComponentName("Tree View Name: ");
		hierarchyNameTextBox.setAddToParent(true);
		hierarchyNameTextBox.addComponentStyle("stpl-margin-left-30");

		hierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		hierarchyNameTextBox.setComponentWidth("300px");
		hierarchyNameTextBox.setDefaultFocus(true);
		hierarchyNameTextBox
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "hierarchyNameLayout");
		componentList.add(hierarchyNameTextBox);
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig searchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		searchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaLayout.setComponentId(tabName + "customViewLookupSearchCriteriaLayout");
		searchCriteriaLayout.setComponentName("Search Criteria");
		searchCriteriaLayout.setAddToParent(true);
		searchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaLayout.setParentComponentId(parentId);
		searchCriteriaLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(searchCriteriaLayout);

		GtnUIFrameworkLayoutConfig cssLayoutConfig = new GtnUIFrameworkLayoutConfig();
		cssLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig searchCriteriaCssLayout = new GtnUIFrameworkComponentConfig();
		searchCriteriaCssLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaCssLayout.setComponentId(tabName + "customViewSearchCriteriaCssLayout");
		searchCriteriaCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		searchCriteriaCssLayout.setAddToParent(true);
		searchCriteriaCssLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaCssLayout.setParentComponentId(searchCriteriaLayout.getComponentId());
		searchCriteriaCssLayout.setGtnLayoutConfig(cssLayoutConfig);
		componentList.add(searchCriteriaCssLayout);

		addMainLayoutForLeftTables(componentList, searchCriteriaCssLayout.getComponentId());
		addCustomTree(componentList, searchCriteriaCssLayout.getComponentId());
	}

	private void addMainLayoutForLeftTables(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig treeMainLayout = layoutsConfig
				.getCssLayoutConfig(tabName + "customViewLookupMainLefttableLayout", parentId);
		treeMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_7);
		treeMainLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_10);
		treeMainLayout.addComponentStyle("stpl-margin-bottom-25");
		treeMainLayout.setComponentWidth("45%");
		componentList.add(treeMainLayout);

		addCustomerTree(componentList, treeMainLayout.getComponentId());
		addProductTree(componentList, treeMainLayout.getComponentId());
		addDeductionTree(componentList, treeMainLayout.getComponentId());
		addVariablesTree(componentList, treeMainLayout.getComponentId());
	}

	private void addCustomerTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainCustomerCssLayout = layoutsConfig
				.getHorizontalLayoutConfig(tabName + "mainHorizontalLayout", parentId);
		mainCustomerCssLayout.setComponentWidth("75%");
		mainCustomerCssLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_10);
		componentList.add(mainCustomerCssLayout);

		GtnUIFrameworkComponentConfig customerGrid = new GtnUIFrameworkComponentConfig();
		customerGrid.setComponentType(GtnUIFrameworkComponentType.GRID);
		customerGrid.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_CUSTOMER_TABLE);
		customerGrid.setComponentName("Customer Hierarchy");
		customerGrid.setAddToParent(true);
		customerGrid.setParentComponentId(mainCustomerCssLayout.getComponentId());
		customerGrid.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		customerGrid.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		customerGrid.setComponentWidth("100%");
		GtnUIFrameworkGridComponentConfig gridConfig = new GtnUIFrameworkGridComponentConfig();
		gridConfig.setColumnHeadersName(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		gridConfig.setColumnHeadersId(new String[] { LEVEL_VALUE });

		GtnUIFrameWorkActionConfig hierachyLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		hierachyLoadAction.addActionParameter(GtnFrameworkUICustomViewHierarchyLoadAction.class.getName());
		hierachyLoadAction.addActionParameter(GtnWsHierarchyType.CUSTOMER);
		customerGrid.setGtnUIFrameWorkActionConfigList(Arrays.asList(hierachyLoadAction));
		customerGrid.setGtnUIFrameWorkGridConfig(gridConfig);
		componentList.add(customerGrid);

		GtnUIFrameworkComponentConfig customerBtnVerLayout = layoutsConfig
				.getVerticalLayoutConfig(tabName + "customerBtn", mainCustomerCssLayout.getComponentId());
		customerBtnVerLayout.setSpacing(true);
		customerBtnVerLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_100);
		customerBtnVerLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_10);
		componentList.add(customerBtnVerLayout);

		GtnUIFrameworkComponentConfig addCustomerButton = new GtnUIFrameworkComponentConfig();
		addCustomerButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addCustomerButton.setAddToParent(true);
		addCustomerButton.setParentComponentId(customerBtnVerLayout.getComponentId());
		addCustomerButton.setComponentId(tabName + "addCustomerBtn");
		addCustomerButton.setComponentName(" > ");
		componentList.add(addCustomerButton);

		GtnUIFrameWorkActionConfig treeAddActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		treeAddActionConfig.addActionParameter(GtnFrameworkUICustomTreeAddAction.class.getName());
		treeAddActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_CUSTOMER_TABLE);
		treeAddActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		treeAddActionConfig
				.addActionParameter(this.tabName + GtnFrameworkReportStringConstants.CUSTOM_VARIABLE_TYPE_OPTION_GROUP);
		addCustomerButton.addGtnUIFrameWorkActionConfig(treeAddActionConfig);

		GtnUIFrameworkComponentConfig removeCustomerButton = new GtnUIFrameworkComponentConfig();
		removeCustomerButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeCustomerButton.setAddToParent(true);
		removeCustomerButton.setParentComponentId(customerBtnVerLayout.getComponentId());
		removeCustomerButton.setComponentId(tabName + "removeCustomerBtn");
		removeCustomerButton.setComponentName(" < ");

		GtnUIFrameWorkActionConfig removeAddActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeAddActionConfig.addActionParameter(GtnFrameworkUICustomTreeRemoveAction.class.getName());
		removeAddActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_CUSTOMER_TABLE);
		removeAddActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		removeAddActionConfig.addActionParameter(GtnWsHierarchyType.CUSTOMER);
		removeCustomerButton.addGtnUIFrameWorkActionConfig(removeAddActionConfig);

		componentList.add(removeCustomerButton);

	}

	private void addProductTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainCssLayoutForProduct = layoutsConfig.getHorizontalLayoutConfig(
				tabName + GtnFrameworkReportStringConstants.MAIN_CSS_LAYOUT_FOR_PRODUCT, parentId);
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_10);
		mainCssLayoutForProduct.setComponentWidth("75%");
		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig productGrid = new GtnUIFrameworkComponentConfig();
		productGrid.setComponentType(GtnUIFrameworkComponentType.GRID);
		productGrid.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_PRODUCT_TABLE);
		productGrid.setComponentName("Product Hierarchy");
		productGrid.setAddToParent(true);
		productGrid.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		productGrid.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		productGrid.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		productGrid.setComponentWidth("100%");

		GtnUIFrameWorkActionConfig hierachyLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		hierachyLoadAction.addActionParameter(GtnFrameworkUICustomViewHierarchyLoadAction.class.getName());
		hierachyLoadAction.addActionParameter(GtnWsHierarchyType.PRODUCT);
		productGrid.setGtnUIFrameWorkActionConfigList(Arrays.asList(hierachyLoadAction));

		GtnUIFrameworkGridComponentConfig gridConfig = new GtnUIFrameworkGridComponentConfig();
		gridConfig.setColumnHeadersName(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		gridConfig.setColumnHeadersId(new String[] { LEVEL_VALUE });
		productGrid.setGtnUIFrameWorkGridConfig(gridConfig);

		componentList.add(productGrid);

		GtnUIFrameworkComponentConfig productBtnVerticalLayout = layoutsConfig.getVerticalLayoutConfig(
				tabName + GtnFrameworkReportStringConstants.PRODUCT_BTN, mainCssLayoutForProduct.getComponentId());
		productBtnVerticalLayout.setSpacing(true);
		productBtnVerticalLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_100);
		productBtnVerticalLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_10);
		componentList.add(productBtnVerticalLayout);

		GtnUIFrameworkComponentConfig addProductButton = new GtnUIFrameworkComponentConfig();
		addProductButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addProductButton.setAddToParent(true);
		addProductButton.setParentComponentId(productBtnVerticalLayout.getComponentId());
		addProductButton.setComponentId(tabName + GtnFrameworkReportStringConstants.ADD_PRODUCT_BTN);
		addProductButton.setComponentName(" > ");
		componentList.add(addProductButton);

		GtnUIFrameWorkActionConfig productTreeAddActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		productTreeAddActionConfig.addActionParameter(GtnFrameworkUICustomTreeAddAction.class.getName());
		productTreeAddActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_PRODUCT_TABLE);
		productTreeAddActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		productTreeAddActionConfig
				.addActionParameter(this.tabName + GtnFrameworkReportStringConstants.CUSTOM_VARIABLE_TYPE_OPTION_GROUP);
		addProductButton.addGtnUIFrameWorkActionConfig(productTreeAddActionConfig);

		GtnUIFrameworkComponentConfig removeProductButton = new GtnUIFrameworkComponentConfig();
		removeProductButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeProductButton.setAddToParent(true);
		removeProductButton.setParentComponentId(productBtnVerticalLayout.getComponentId());
		removeProductButton.setComponentId(tabName + GtnFrameworkReportStringConstants.REMOVE_PRODUCT_BTN);
		removeProductButton.setComponentName(" < ");

		GtnUIFrameWorkActionConfig productTreeRemoveActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		productTreeRemoveActionConfig.addActionParameter(GtnFrameworkUICustomTreeRemoveAction.class.getName());
		productTreeRemoveActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_PRODUCT_TABLE);
		productTreeRemoveActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		productTreeRemoveActionConfig.addActionParameter(GtnWsHierarchyType.PRODUCT);
		removeProductButton.addGtnUIFrameWorkActionConfig(productTreeRemoveActionConfig);

		componentList.add(removeProductButton);
	}

	private void addDeductionTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainCssLayoutForProduct = layoutsConfig.getHorizontalLayoutConfig(
				tabName + GtnFrameworkReportStringConstants.MAIN_CSS_LAYOUT_FOR_PRODUCT, parentId);
		mainCssLayoutForProduct.setComponentWidth("75%");
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_10);
		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig deductionGrid = new GtnUIFrameworkComponentConfig();
		deductionGrid.setComponentType(GtnUIFrameworkComponentType.GRID);
		deductionGrid.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_DEDUCTION_TABLE);
		deductionGrid.setComponentName("Deduction Hierarchy");
		deductionGrid.setAddToParent(true);
		deductionGrid.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		deductionGrid.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		deductionGrid.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		deductionGrid.setComponentWidth("100%");

		GtnUIFrameWorkActionConfig hierachyLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		hierachyLoadAction.addActionParameter(GtnFrameworkUICustomViewHierarchyLoadAction.class.getName());
		hierachyLoadAction.addActionParameter(GtnWsHierarchyType.DEDUCTION);
		deductionGrid.setGtnUIFrameWorkActionConfigList(Arrays.asList(hierachyLoadAction));

		GtnUIFrameworkGridComponentConfig gridConfig = new GtnUIFrameworkGridComponentConfig();
		gridConfig.setColumnHeadersName(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		gridConfig.setColumnHeadersId(new String[] { LEVEL_VALUE });
		deductionGrid.setGtnUIFrameWorkGridConfig(gridConfig);

		componentList.add(deductionGrid);

		GtnUIFrameworkComponentConfig deductionBtnVerticalLayout = layoutsConfig.getVerticalLayoutConfig(
				tabName + GtnFrameworkReportStringConstants.DEDUCTION_BTN, mainCssLayoutForProduct.getComponentId());
		deductionBtnVerticalLayout.setSpacing(true);
		deductionBtnVerticalLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_100);
		deductionBtnVerticalLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_10);

		componentList.add(deductionBtnVerticalLayout);

		GtnUIFrameworkComponentConfig addDeductionButton = new GtnUIFrameworkComponentConfig();
		addDeductionButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addDeductionButton.setAddToParent(true);
		addDeductionButton.setParentComponentId(deductionBtnVerticalLayout.getComponentId());
		addDeductionButton.setComponentId(tabName + GtnFrameworkReportStringConstants.ADD_DEDUCTION_BTN);
		addDeductionButton.setComponentName(" > ");
		componentList.add(addDeductionButton);

		GtnUIFrameWorkActionConfig deductionTreeAddActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		deductionTreeAddActionConfig.addActionParameter(GtnFrameworkUICustomTreeAddAction.class.getName());
		deductionTreeAddActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_DEDUCTION_TABLE);
		deductionTreeAddActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		deductionTreeAddActionConfig
				.addActionParameter(this.tabName + GtnFrameworkReportStringConstants.CUSTOM_VARIABLE_TYPE_OPTION_GROUP);
		addDeductionButton.addGtnUIFrameWorkActionConfig(deductionTreeAddActionConfig);

		GtnUIFrameworkComponentConfig removeDeductionButton = new GtnUIFrameworkComponentConfig();
		removeDeductionButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeDeductionButton.setAddToParent(true);
		removeDeductionButton.setParentComponentId(deductionBtnVerticalLayout.getComponentId());
		removeDeductionButton.setComponentId(tabName + GtnFrameworkReportStringConstants.REMOVE_DEDUCTION_BTN);
		removeDeductionButton.setComponentName(" < ");

		GtnUIFrameWorkActionConfig deductionTreeRemoveActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		deductionTreeRemoveActionConfig.addActionParameter(GtnFrameworkUICustomTreeRemoveAction.class.getName());
		deductionTreeRemoveActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_DEDUCTION_TABLE);
		deductionTreeRemoveActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		deductionTreeRemoveActionConfig.addActionParameter(GtnWsHierarchyType.DEDUCTION);
		removeDeductionButton.addGtnUIFrameWorkActionConfig(deductionTreeRemoveActionConfig);

		componentList.add(removeDeductionButton);
	}

	private void addVariablesTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainCssLayoutForProduct = layoutsConfig.getHorizontalLayoutConfig(
				tabName + GtnFrameworkReportStringConstants.MAIN_CSS_LAYOUT_FOR_PRODUCT, parentId);
		mainCssLayoutForProduct.setComponentWidth("75%");
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_10);

		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig variablesGrid = new GtnUIFrameworkComponentConfig();
		variablesGrid.setComponentType(GtnUIFrameworkComponentType.GRID);
		variablesGrid.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);
		variablesGrid.setComponentName("Variables");
		variablesGrid.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		variablesGrid.setAddToParent(true);
		variablesGrid.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		variablesGrid.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		variablesGrid.setComponentWidth("100%");

		GtnUIFrameWorkActionConfig variableLoadConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableLoadConfig.addActionParameter(GtnFrameworkUICustomVariableGridLoadAction.class.getName());
		variableLoadConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VARIABLE_TYPE_OPTION_GROUP);
		variableLoadConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);
		variableLoadConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		variablesGrid.setGtnUIFrameWorkActionConfigList(Arrays.asList(variableLoadConfig));

		GtnUIFrameworkGridComponentConfig gridConfig = new GtnUIFrameworkGridComponentConfig();
		gridConfig.setColumnHeadersName(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		gridConfig.setColumnHeadersId(new String[] { LEVEL_VALUE });
		variablesGrid.setGtnUIFrameWorkGridConfig(gridConfig);

		componentList.add(variablesGrid);

		GtnUIFrameworkComponentConfig variablesBtnVerticalLayout = layoutsConfig.getVerticalLayoutConfig(
				tabName + GtnFrameworkReportStringConstants.VARIABLES_BTN, mainCssLayoutForProduct.getComponentId());
		variablesBtnVerticalLayout.setSpacing(true);
		variablesBtnVerticalLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_100);
		variablesBtnVerticalLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_10);

		componentList.add(variablesBtnVerticalLayout);

		GtnUIFrameworkComponentConfig addVariablesButton = new GtnUIFrameworkComponentConfig();
		addVariablesButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addVariablesButton.setAddToParent(true);
		addVariablesButton.setParentComponentId(variablesBtnVerticalLayout.getComponentId());
		addVariablesButton.setComponentId(tabName + GtnFrameworkReportStringConstants.ADD_VARIABLES_BTN);
		addVariablesButton.setComponentName(" > ");
		componentList.add(addVariablesButton);

		GtnUIFrameWorkActionConfig deductionTreeAddActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		deductionTreeAddActionConfig.addActionParameter(GtnFrameworkUICustomTreeAddAction.class.getName());
		deductionTreeAddActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);
		deductionTreeAddActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		deductionTreeAddActionConfig
				.addActionParameter(this.tabName + GtnFrameworkReportStringConstants.CUSTOM_VARIABLE_TYPE_OPTION_GROUP);
		addVariablesButton.addGtnUIFrameWorkActionConfig(deductionTreeAddActionConfig);

		GtnUIFrameworkComponentConfig removeVariablesButton = new GtnUIFrameworkComponentConfig();
		removeVariablesButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeVariablesButton.setAddToParent(true);
		removeVariablesButton.setParentComponentId(variablesBtnVerticalLayout.getComponentId());
		removeVariablesButton.setComponentId(tabName + GtnFrameworkReportStringConstants.REMOVE_VARIABLES_BTN);
		removeVariablesButton.setComponentName(" < ");

		GtnUIFrameWorkActionConfig deductionTreeRemoveActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		deductionTreeRemoveActionConfig.addActionParameter(GtnFrameworkUICustomTreeRemoveAction.class.getName());
		deductionTreeRemoveActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);
		deductionTreeRemoveActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		deductionTreeRemoveActionConfig.addActionParameter(GtnWsHierarchyType.VARIABLES);
		removeVariablesButton.addGtnUIFrameWorkActionConfig(deductionTreeRemoveActionConfig);
		componentList.add(removeVariablesButton);
	}

	private void addCustomTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig customTreeMainLayout = layoutsConfig
				.getHorizontalLayoutConfig(tabName + "customViewLookupCssCustomTableLayout", parentId);
		customTreeMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		customTreeMainLayout.setComponentWidth("55%");
		componentList.add(customTreeMainLayout);
		GtnUIFrameworkComponentConfig customTree = new GtnUIFrameworkComponentConfig();
		customTree.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		customTree.setComponentName("Tree Structure");
		customTree.setComponentType(GtnUIFrameworkComponentType.TREE_GRID);
		customTree.setComponentWidth("98%");
		customTree.setComponentHight("900px");
		customTree.setAddToParent(true);
		customTree.setParentComponentId(customTreeMainLayout.getComponentId());

		GtnUIFrameworkGridComponentConfig gridConfig = new GtnUIFrameworkGridComponentConfig();
		gridConfig.setColumnHeadersName(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		gridConfig.setColumnHeadersId(new String[] { LEVEL_VALUE });
		customTree.setGtnUIFrameWorkGridConfig(gridConfig);
		componentList.add(customTree);

	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId(tabName + "customLookupControlButtonLayout");
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setMargin(true);
		layoutConfig.addComponentStyle("stpl-margin-top-10");
		layoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);

		componentList.add(layoutConfig);

		GtnUIFrameworkComponentConfig deleteButton = new GtnUIFrameworkComponentConfig();
		deleteButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteButton.setComponentId(tabName + "customViewDelete");
		deleteButton.setComponentName("DELETE");
		deleteButton.setParentComponentId(layoutConfig.getComponentId());
		deleteButton.setAddToParent(true);
		componentList.add(deleteButton);
		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkReportCustomViewDeleteAction.class.getName());
		deleteActionConfig.addActionParameter(this.tabName);

		GtnUIFrameWorkActionConfig deletecloseActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		deleteActionConfig.addActionParameter(deletecloseActionConfig);

		deleteButton.addGtnUIFrameWorkActionConfig(deleteActionConfig);

		GtnUIFrameworkComponentConfig saveButton = new GtnUIFrameworkComponentConfig();
		saveButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveButton.setComponentId(tabName + "customViewSave");
		saveButton.setComponentName("SAVE");
		saveButton.setParentComponentId(layoutConfig.getComponentId());
		saveButton.setAddToParent(true);
		componentList.add(saveButton);

		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkCVSaveValidationAction.class.getName());
		saveActionConfig.addActionParameter(new String[] {
				tabName + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME,
				"reportLandingScreen_customerSelectionRelationship", "reportLandingScreen_relationship",
				this.tabName + GtnFrameworkReportStringConstants.CUSTOM_VARIABLE_TYPE_OPTION_GROUP,
				this.tabName + "_custom_Variable_OptionGroup" });
		saveActionConfig.addActionParameter(tabName + "customTreeTable");
		saveActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.UNDERSCORE);
		saveActionConfig.addActionParameter(Boolean.TRUE);
		saveButton.addGtnUIFrameWorkActionConfig(saveActionConfig);

		GtnUIFrameworkComponentConfig selectButtonConfig = new GtnUIFrameworkComponentConfig();
		selectButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButtonConfig.setComponentId(tabName + "customViewSelect");
		selectButtonConfig.setComponentName("SELECT");
		selectButtonConfig.setParentComponentId(layoutConfig.getComponentId());
		selectButtonConfig.setAddToParent(true);
		componentList.add(selectButtonConfig);

		GtnUIFrameWorkActionConfig selectSaveActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectSaveActionConfig.addActionParameter(GtnFrameworkCVSaveValidationAction.class.getName());
		selectSaveActionConfig.addActionParameter(new String[] {
				tabName + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME,
				"reportLandingScreen_customerSelectionRelationship", "reportLandingScreen_relationship",
				this.tabName + GtnFrameworkReportStringConstants.CUSTOM_VARIABLE_TYPE_OPTION_GROUP,
				this.tabName + "_custom_Variable_OptionGroup" });
		selectSaveActionConfig.addActionParameter(tabName + "customTreeTable");
		selectSaveActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.UNDERSCORE);
		selectSaveActionConfig.addActionParameter(Boolean.TRUE);
		GtnUIFrameWorkActionConfig closeConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);

		selectSaveActionConfig.addActionParameter(closeConfig);

		selectButtonConfig.addGtnUIFrameWorkActionConfig(selectSaveActionConfig);

		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentId(tabName + "customViewClose");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setParentComponentId(layoutConfig.getComponentId());
		closeButtonConfig.setAddToParent(true);

		closeButtonConfig.addGtnUIFrameWorkActionConfig(closeConfig);
		componentList.add(closeButtonConfig);

	}

}