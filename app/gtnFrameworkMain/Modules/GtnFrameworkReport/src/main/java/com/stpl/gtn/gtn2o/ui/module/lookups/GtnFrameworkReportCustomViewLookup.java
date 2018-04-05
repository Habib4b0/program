/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkReportCustomViewLookup {

	private final GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();
	private String tabName="";

	public GtnUIFrameworkViewConfig getCustomViewLookUpView(String tabName) {
		this.tabName = tabName;
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("reportCustomViewLookup");
		view.setViewId("reportCustomViewLookup");
		view.setDefaultView(false);
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
		viewDetailsPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "viewDetailsPanel");
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
		viewDetailsLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);
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
		variableTypeOptionGroup.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		variableTypeOptionGroup.setComponentName("Variable Type: ");
		variableTypeOptionGroup.setAddToParent(true);
		variableTypeOptionGroup.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList("Expandable", "Static"));
		comboConfig.setValuesFromService(false);
		variableTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		variableTypeOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		variableTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(variableTypeOptionGroup);
	}

	private void addVariablePositionOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig variablePositionOptionGroup = new GtnUIFrameworkComponentConfig();
		variablePositionOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		variablePositionOptionGroup.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		variablePositionOptionGroup.setComponentName("Variable Position: ");
		variablePositionOptionGroup.setAddToParent(true);
		variablePositionOptionGroup.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList("Rows", "Columns"));
		comboConfig.setValuesFromService(false);
		variablePositionOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		variablePositionOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		variablePositionOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
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
		hierarchyNameTextBox.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.VIEW_DETAILS_LAYOUT);
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

		GtnUIFrameworkComponentConfig customerTreeTable = new GtnUIFrameworkComponentConfig();
		customerTreeTable.setComponentType(GtnUIFrameworkComponentType.DATATABLE);
		customerTreeTable.setComponentId(tabName + "customViewLookupCustomerTable");
		customerTreeTable.setComponentName("Customer Hierarchy");
		customerTreeTable.setAddToParent(true);
		customerTreeTable.setParentComponentId(mainCustomerCssLayout.getComponentId());
		customerTreeTable.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		customerTreeTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig customerTreeTableConfig = new GtnUIFrameworkPagedTableConfig();
		customerTreeTableConfig.setCaptionVisible(true);
		customerTreeTableConfig.setSelectable(true);
		customerTreeTableConfig.setPageLength(10);
		customerTreeTableConfig.setItemPerPage(10);

		customerTreeTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		customerTreeTableConfig.setTableVisibleHeader(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		customerTreeTableConfig.setTableColumnMappingId(new Object[] { "customerLevel" });

		customerTreeTable.setGtnPagedTableConfig(customerTreeTableConfig);
		componentList.add(customerTreeTable);

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

		GtnUIFrameworkComponentConfig removeCustomerButton = new GtnUIFrameworkComponentConfig();
		removeCustomerButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeCustomerButton.setAddToParent(true);
		removeCustomerButton.setParentComponentId(customerBtnVerticalLayout.getComponentId());
		removeCustomerButton.setComponentId(tabName + "removeCustomerBtn");
		removeCustomerButton.setComponentName(" < ");
		componentList.add(removeCustomerButton);

	}

	private void addProductTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainCssLayoutForProduct = layoutsConfig
				.getCssLayoutConfig(tabName + GtnFrameworkReportStringConstants.MAIN_CSS_LAYOUT_FOR_PRODUCT, parentId);
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10);
		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig productTreeTable = new GtnUIFrameworkComponentConfig();
		productTreeTable.setComponentType(GtnUIFrameworkComponentType.DATATABLE);
		productTreeTable.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_PRODUCT_TABLE);
		productTreeTable.setComponentName("Product Hierarchy");
		productTreeTable.setAddToParent(true);
		productTreeTable.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		productTreeTable.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		productTreeTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkPagedTableConfig productTreeTableConfig = new GtnUIFrameworkPagedTableConfig();
		productTreeTableConfig.setCaptionVisible(true);
		productTreeTableConfig.setPageLength(10);
		productTreeTableConfig.setItemPerPage(10);
		productTreeTableConfig.setSelectable(true);

		productTreeTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		productTreeTableConfig.setTableVisibleHeader(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		productTreeTableConfig.setTableColumnMappingId(new Object[] { "productLevel" });

		productTreeTable.setGtnPagedTableConfig(productTreeTableConfig);
		componentList.add(productTreeTable);

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

		GtnUIFrameworkComponentConfig removeProductButton = new GtnUIFrameworkComponentConfig();
		removeProductButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeProductButton.setAddToParent(true);
		removeProductButton.setParentComponentId(productBtnVerticalLayout.getComponentId());
		removeProductButton.setComponentId(tabName + GtnFrameworkReportStringConstants.REMOVE_PRODUCT_BTN);
		removeProductButton.setComponentName(" < ");
		componentList.add(removeProductButton);
	}

	private void addDeductionTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainCssLayoutForProduct = layoutsConfig
				.getCssLayoutConfig(tabName + GtnFrameworkReportStringConstants.MAIN_CSS_LAYOUT_FOR_PRODUCT, parentId);
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10);
		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig deductionTreeTable = new GtnUIFrameworkComponentConfig();
		deductionTreeTable.setComponentType(GtnUIFrameworkComponentType.DATATABLE);
		deductionTreeTable.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_PRODUCT_TABLE);
		deductionTreeTable.setComponentName("Deduction Hierarchy");
		deductionTreeTable.setAddToParent(true);
		deductionTreeTable.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		deductionTreeTable.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		deductionTreeTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkPagedTableConfig deductionTreeTableConfig = new GtnUIFrameworkPagedTableConfig();
		deductionTreeTableConfig.setCaptionVisible(true);
		deductionTreeTableConfig.setPageLength(10);
		deductionTreeTableConfig.setItemPerPage(10);
		deductionTreeTableConfig.setSelectable(true);

		deductionTreeTableConfig
				.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		deductionTreeTableConfig.setTableVisibleHeader(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		deductionTreeTableConfig.setTableColumnMappingId(new Object[] { "deductionLevel" });

		deductionTreeTable.setGtnPagedTableConfig(deductionTreeTableConfig);
		componentList.add(deductionTreeTable);

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

		GtnUIFrameworkComponentConfig removeDeductionButton = new GtnUIFrameworkComponentConfig();
		removeDeductionButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeDeductionButton.setAddToParent(true);
		removeDeductionButton.setParentComponentId(deductionBtnVerticalLayout.getComponentId());
		removeDeductionButton.setComponentId(tabName + GtnFrameworkReportStringConstants.REMOVE_DEDUCTION_BTN);
		removeDeductionButton.setComponentName(" < ");
		componentList.add(removeDeductionButton);
	}

	private void addVariablesTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainCssLayoutForProduct = layoutsConfig
				.getCssLayoutConfig(tabName + GtnFrameworkReportStringConstants.MAIN_CSS_LAYOUT_FOR_PRODUCT, parentId);
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10);
		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig variablesTreeTable = new GtnUIFrameworkComponentConfig();
		variablesTreeTable.setComponentType(GtnUIFrameworkComponentType.DATATABLE);
		variablesTreeTable.setComponentId(tabName + GtnFrameworkReportStringConstants.CUSTOM_VIEW_LOOKUP_PRODUCT_TABLE);
		variablesTreeTable.setComponentName("Variables");
		variablesTreeTable.setAddToParent(true);
		variablesTreeTable.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		variablesTreeTable.setComponentHight(GtnFrameworkReportStringConstants.TWO_SEVENTY_PIXEL);
		variablesTreeTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkPagedTableConfig variablesTreeTableConfig = new GtnUIFrameworkPagedTableConfig();
		variablesTreeTableConfig.setCaptionVisible(true);
		variablesTreeTableConfig.setPageLength(10);
		variablesTreeTableConfig.setItemPerPage(10);
		variablesTreeTableConfig.setSelectable(true);

		variablesTreeTableConfig
				.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		variablesTreeTableConfig.setTableVisibleHeader(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		variablesTreeTableConfig.setTableColumnMappingId(new Object[] { "variablesLevel" });

		variablesTreeTable.setGtnPagedTableConfig(variablesTreeTableConfig);
		componentList.add(variablesTreeTable);

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

		GtnUIFrameworkComponentConfig removeVariablesButton = new GtnUIFrameworkComponentConfig();
		removeVariablesButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeVariablesButton.setAddToParent(true);
		removeVariablesButton.setParentComponentId(variablesBtnVerticalLayout.getComponentId());
		removeVariablesButton.setComponentId(tabName + GtnFrameworkReportStringConstants.REMOVE_VARIABLES_BTN);
		removeVariablesButton.setComponentName(" < ");
		componentList.add(removeVariablesButton);
	}

	private void addCustomTree(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig customTreeMainLayout = layoutsConfig
				.getCssLayoutConfig(tabName + "customViewLookupCssCustomTableLayout", parentId);
		customTreeMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(customTreeMainLayout);
		GtnUIFrameworkComponentConfig customTree = new GtnUIFrameworkComponentConfig();
		customTree.setComponentId(tabName + "customTreeTable");
		customTree.setComponentName("Tree Structure");
		customTree.setComponentType(GtnUIFrameworkComponentType.TREE_TABLE);
		customTree.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customTree.setComponentHight("580px");
		customTree.setAddToParent(true);
		customTree.setParentComponentId(customTreeMainLayout.getComponentId());
		GtnUIFrameworkPagedTableConfig customTreeTableconfig = new GtnUIFrameworkPagedTableConfig();
		customTreeTableconfig.setSelectable(true);

		customTreeTableconfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		customTreeTableconfig.setTableVisibleHeader(new String[] { GtnFrameworkReportStringConstants.LEVEL });
		customTreeTableconfig.setTableColumnMappingId(new Object[] { "customTreeLevel" });

		customTree.setGtnPagedTableConfig(customTreeTableconfig);
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

		GtnUIFrameworkComponentConfig updateButton = new GtnUIFrameworkComponentConfig();
		updateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		updateButton.setComponentId(tabName + "customViewSelect");
		updateButton.setComponentName("SELECT");
		updateButton.setParentComponentId(layoutConfig.getComponentId());
		updateButton.setAddToParent(true);
		componentList.add(updateButton);

		GtnUIFrameworkComponentConfig nextButton = new GtnUIFrameworkComponentConfig();
		nextButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		nextButton.setComponentId(tabName + "customViewClose");
		nextButton.setComponentName("CLOSE");
		nextButton.setParentComponentId(layoutConfig.getComponentId());
		nextButton.setAddToParent(true);
		componentList.add(nextButton);

	}

}
