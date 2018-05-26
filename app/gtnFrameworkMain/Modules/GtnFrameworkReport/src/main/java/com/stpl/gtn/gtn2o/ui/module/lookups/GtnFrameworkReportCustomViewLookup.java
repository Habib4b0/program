/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomSelectAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeRemoveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomTreeSaveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomVariableGridLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomVariablePositionChangeAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUICustomViewEditAction;
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

	public GtnUIFrameworkViewConfig getCustomViewLookUpView(String tabName) {
		this.tabName = tabName;
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("reportCustomViewLookup");
		view.setViewId("reportCustomViewLookup");
		view.setDefaultView(false);
		view.setResetAllowed(true);
		GtnUIFrameWorkActionConfig treeLoadActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		treeLoadActionConfig.addActionParameter(GtnFrameworkUICustomViewEditAction.class.getName());
		treeLoadActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		view.addViewAction(treeLoadActionConfig);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addViewDetailsPanel(componentList, tabName);
		addRootLayout(componentList);

	}

	private void addViewDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig viewDetailsPanel = new GtnUIFrameworkComponentConfig();
		viewDetailsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		viewDetailsPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "viewDetailsPanel");
		viewDetailsPanel.setComponentName("View Details");

		viewDetailsPanel.setMargin(true);
		viewDetailsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		viewDetailsPanel.setComponentHight("100px");
		componentList.add(viewDetailsPanel);
		addViewDetailsLayout(componentList, namespace);
	}

	private void addViewDetailsLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig viewDetailsLayout = new GtnUIFrameworkComponentConfig();
		viewDetailsLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		viewDetailsLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);
		viewDetailsLayout.setComponentName("Search Criteria");
		viewDetailsLayout.setAddToParent(true);
		viewDetailsLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		viewDetailsLayout
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "viewDetailsPanel");
		GtnUIFrameworkLayoutConfig viewDetailsConfig = new GtnUIFrameworkLayoutConfig();
		viewDetailsLayout.setGtnLayoutConfig(viewDetailsConfig);
		viewDetailsLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		viewDetailsLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(viewDetailsLayout);

		addHierarchyNameTextBox(componentList, namespace);
		addVariablePositionOptionGroup(componentList, namespace);
		addVariableTypeOptionGroup(componentList, namespace);
	}

	private void addVariableTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig variableTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		variableTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		variableTypeOptionGroup.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VARIABLE_TYPE_OPTION_GROUP);
		variableTypeOptionGroup.setComponentName("Variable Type: ");
		variableTypeOptionGroup.setAddToParent(true);
		variableTypeOptionGroup.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);
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

		variableTypeOptionGroup.addGtnUIFrameWorkActionConfig(variableLoadConfig);
		componentList.add(variableTypeOptionGroup);
	}

	private void addVariablePositionOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig variablePositionOptionGroup = new GtnUIFrameworkComponentConfig();
		variablePositionOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		variablePositionOptionGroup.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VARIABLE_POSITION_OPTION_GROUP);
		variablePositionOptionGroup.setComponentName("Variable Position: ");
		variablePositionOptionGroup.setAddToParent(true);
		variablePositionOptionGroup.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);
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
		variablePositionOptionGroup.addGtnUIFrameWorkActionConfig(valuChangeConfig);
		componentList.add(variablePositionOptionGroup);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig hierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		hierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		hierarchyNameTextBox.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		hierarchyNameTextBox.setComponentName("Tree View Name: ");
		hierarchyNameTextBox.setAddToParent(true);
		hierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		hierarchyNameTextBox.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);
		componentList.add(hierarchyNameTextBox);
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rootLayoutComponentConfig = new GtnUIFrameworkComponentConfig();
		rootLayoutComponentConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		rootLayoutComponentConfig.setComponentId(tabName + "customViewLookupRootLayout");
		rootLayoutComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rootLayoutComponentConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rootLayoutComponentConfig.setSpacing(true);

		componentList.add(rootLayoutComponentConfig);
		searchCriteriaLayout(componentList, rootLayoutComponentConfig.getComponentId());

	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig searchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		searchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaLayout.setComponentId(tabName + "customViewLookupSearchCriteriaLayout");
		searchCriteriaLayout.setComponentName("Search Criteria");
		searchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		searchCriteriaLayout.setAddToParent(true);
		searchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaLayout.setParentComponentId(parentId);
		searchCriteriaLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(searchCriteriaLayout);
		addMainLayoutForLeftTables(componentList, searchCriteriaLayout.getComponentId());
		addCustomTree(componentList, searchCriteriaLayout.getComponentId());
		addControlButtonLayout(componentList);
	}

	private void addMainLayoutForLeftTables(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig treeMainLayout = layoutsConfig
				.getCssLayoutConfig(tabName + "customViewLookupMainLefttableLayout", parentId);
		treeMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(treeMainLayout);

		addCustomerTree(componentList, treeMainLayout.getComponentId());
		addProductTree(componentList, treeMainLayout.getComponentId());
		addDeductionTree(componentList, treeMainLayout.getComponentId());
		addVariablesTree(componentList, treeMainLayout.getComponentId());
	}

	private void addCustomerTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainCustomerCssLayout = layoutsConfig
				.getCssLayoutConfig(tabName + "mainHorizontalLayout", parentId);
		mainCustomerCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10);
		componentList.add(mainCustomerCssLayout);

		GtnUIFrameworkComponentConfig customerGrid = new GtnUIFrameworkComponentConfig();
		customerGrid.setComponentType(GtnUIFrameworkComponentType.GRID);
		customerGrid.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_CUSTOMER_TABLE);
		customerGrid.setComponentName("Customer Hierarchy");
		customerGrid.setAddToParent(true);
		customerGrid.setParentComponentId(mainCustomerCssLayout.getComponentId());
		customerGrid.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		customerGrid.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
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

		GtnUIFrameworkComponentConfig customerBtnCssLayout = layoutsConfig
				.getCssLayoutConfig(tabName + "customerBtMain", parentId);
		customerBtnCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(customerBtnCssLayout);

		GtnUIFrameworkComponentConfig customerBtnVerticalLayout = layoutsConfig
				.getVerticalLayoutConfig(tabName + "customerBtn", customerBtnCssLayout.getComponentId());
		componentList.add(customerBtnVerticalLayout);

		GtnUIFrameworkComponentConfig addCustomerButton = new GtnUIFrameworkComponentConfig();
		addCustomerButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addCustomerButton.setAddToParent(true);
		addCustomerButton.setParentComponentId(customerBtnVerticalLayout.getComponentId());
		addCustomerButton.setComponentId(tabName + "addCustomerBtn");
		addCustomerButton.setComponentName(" > ");
		componentList.add(addCustomerButton);

		GtnUIFrameWorkActionConfig treeAddActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		treeAddActionConfig.addActionParameter(GtnFrameworkUICustomTreeAddAction.class.getName());
		treeAddActionConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_CUSTOMER_TABLE);
		treeAddActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		addCustomerButton.addGtnUIFrameWorkActionConfig(treeAddActionConfig);

		GtnUIFrameworkComponentConfig removeCustomerButton = new GtnUIFrameworkComponentConfig();
		removeCustomerButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeCustomerButton.setAddToParent(true);
		removeCustomerButton.setParentComponentId(customerBtnVerticalLayout.getComponentId());
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

		GtnUIFrameworkComponentConfig mainCssLayoutForProduct = layoutsConfig
				.getCssLayoutConfig(tabName + GtnFrameworkReportStringConstants.MAIN_CSS_LAYOUT_FOR_PRODUCT, parentId);
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10);
		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig productGrid = new GtnUIFrameworkComponentConfig();
		productGrid.setComponentType(GtnUIFrameworkComponentType.GRID);
		productGrid.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_PRODUCT_TABLE);
		productGrid.setComponentName("Product Hierarchy");
		productGrid.setAddToParent(true);
		productGrid.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		productGrid.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		productGrid.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

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

		GtnUIFrameworkComponentConfig productBtnCssLayout = layoutsConfig
				.getCssLayoutConfig(tabName + GtnFrameworkReportStringConstants.PRODUCT_BTN_MAIN, parentId);
		productBtnCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(productBtnCssLayout);

		GtnUIFrameworkComponentConfig productBtnVerticalLayout = layoutsConfig.getVerticalLayoutConfig(
				tabName + GtnFrameworkReportStringConstants.PRODUCT_BTN, productBtnCssLayout.getComponentId());
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

		GtnUIFrameworkComponentConfig mainCssLayoutForProduct = layoutsConfig
				.getCssLayoutConfig(tabName + GtnFrameworkReportStringConstants.MAIN_CSS_LAYOUT_FOR_PRODUCT, parentId);
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10);
		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig deductionGrid = new GtnUIFrameworkComponentConfig();
		deductionGrid.setComponentType(GtnUIFrameworkComponentType.GRID);
		deductionGrid.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_DEDUCTION_TABLE);
		deductionGrid.setComponentName("Deduction Hierarchy");
		deductionGrid.setAddToParent(true);
		deductionGrid.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		deductionGrid.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		deductionGrid.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

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

		GtnUIFrameworkComponentConfig deductionBtnCssLayout = layoutsConfig
				.getCssLayoutConfig(tabName + GtnFrameworkReportStringConstants.DEDUCTION_BTN_MAIN, parentId);
		deductionBtnCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(deductionBtnCssLayout);

		GtnUIFrameworkComponentConfig deductionBtnVerticalLayout = layoutsConfig.getVerticalLayoutConfig(
				tabName + GtnFrameworkReportStringConstants.DEDUCTION_BTN, deductionBtnCssLayout.getComponentId());
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
		deductionTreeRemoveActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		deductionTreeRemoveActionConfig.addActionParameter(GtnWsHierarchyType.DEDUCTION);
		removeDeductionButton.addGtnUIFrameWorkActionConfig(deductionTreeRemoveActionConfig);

		componentList.add(removeDeductionButton);
	}

	private void addVariablesTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainCssLayoutForProduct = layoutsConfig
				.getCssLayoutConfig(tabName + GtnFrameworkReportStringConstants.MAIN_CSS_LAYOUT_FOR_PRODUCT, parentId);
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10);
		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig variablesGrid = new GtnUIFrameworkComponentConfig();
		variablesGrid.setComponentType(GtnUIFrameworkComponentType.GRID);
		variablesGrid.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);
		variablesGrid.setComponentName("Variables");
		variablesGrid.setAddToParent(true);
		variablesGrid.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		variablesGrid.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		variablesGrid.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameWorkActionConfig variableLoadConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableLoadConfig.addActionParameter(GtnFrameworkUICustomVariableGridLoadAction.class.getName());
		variableLoadConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.VARIABLE_TYPE_OPTION_GROUP);
		variableLoadConfig
				.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_VARIABLE_TABLE);
		variablesGrid.setGtnUIFrameWorkActionConfigList(Arrays.asList(variableLoadConfig));

		GtnUIFrameworkGridComponentConfig gridConfig = new GtnUIFrameworkGridComponentConfig();
		gridConfig.setColumnHeadersName(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		gridConfig.setColumnHeadersId(new String[] { LEVEL_VALUE });
		variablesGrid.setGtnUIFrameWorkGridConfig(gridConfig);

		componentList.add(variablesGrid);

		GtnUIFrameworkComponentConfig variablesBtnCssLayout = layoutsConfig
				.getCssLayoutConfig(tabName + GtnFrameworkReportStringConstants.VARIABLES_BTN_MAIN, parentId);
		variablesBtnCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(variablesBtnCssLayout);

		GtnUIFrameworkComponentConfig variablesBtnVerticalLayout = layoutsConfig.getVerticalLayoutConfig(
				tabName + GtnFrameworkReportStringConstants.VARIABLES_BTN, variablesBtnCssLayout.getComponentId());
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
		deductionTreeRemoveActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		deductionTreeRemoveActionConfig.addActionParameter(GtnWsHierarchyType.VARIABLES);
		removeVariablesButton.addGtnUIFrameWorkActionConfig(deductionTreeRemoveActionConfig);
		componentList.add(removeVariablesButton);
	}

	private void addCustomTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig customTreeMainLayout = layoutsConfig
				.getCssLayoutConfig(tabName + "customViewLookupCssCustomTableLayout", parentId);
		customTreeMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(customTreeMainLayout);
		GtnUIFrameworkComponentConfig customTree = new GtnUIFrameworkComponentConfig();
		customTree.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		customTree.setComponentName("Tree Structure");
		customTree.setComponentType(GtnUIFrameworkComponentType.TREE_GRID);
		customTree.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customTree.setComponentHight("580px");
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

		GtnUIFrameworkComponentConfig saveButton = new GtnUIFrameworkComponentConfig();
		saveButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveButton.setComponentId(tabName + "customViewSave");
		saveButton.setComponentName("SAVE");
		saveButton.setParentComponentId(layoutConfig.getComponentId());
		saveButton.setAddToParent(true);
		componentList.add(saveButton);

		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkUICustomTreeSaveAction.class.getName());
		saveActionConfig.addActionParameter(
				tabName + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		saveActionConfig.addActionParameter(tabName + GtnFrameworkReportStringConstants.CUSTOM_TREETABLE);
		saveButton.addGtnUIFrameWorkActionConfig(saveActionConfig);

		GtnUIFrameworkComponentConfig selectButtonConfig = new GtnUIFrameworkComponentConfig();
		selectButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButtonConfig.setComponentId(tabName + "customViewSelect");
		selectButtonConfig.setComponentName("SELECT");
		selectButtonConfig.setParentComponentId(layoutConfig.getComponentId());
		selectButtonConfig.setAddToParent(true);
		componentList.add(selectButtonConfig);

		GtnUIFrameWorkActionConfig selectButtonActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectButtonActionConfig.addActionParameter(GtnFrameworkUICustomSelectAction.class.getName());
		selectButtonConfig.addGtnUIFrameWorkActionConfig(selectButtonActionConfig);

		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentId(tabName + "customViewClose");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setParentComponentId(layoutConfig.getComponentId());
		closeButtonConfig.setAddToParent(true);

		GtnUIFrameWorkActionConfig closeConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeButtonConfig.addGtnUIFrameWorkActionConfig(closeConfig);
		componentList.add(closeButtonConfig);

	}

}
