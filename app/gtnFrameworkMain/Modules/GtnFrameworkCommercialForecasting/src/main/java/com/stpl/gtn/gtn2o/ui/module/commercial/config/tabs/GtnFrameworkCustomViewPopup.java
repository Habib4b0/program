/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingTableConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkCustomViewPopup {

	private final GtnFrameworkCommercialForecastingLayoutsConfig layoutsConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
	private String tabName;

	public GtnUIFrameworkViewConfig getCustomViewLookUpView(String tabName) throws GtnFrameworkGeneralException {
		this.tabName = tabName;
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Custom Tree Lookup");
		view.setViewId(this.tabName + "gtnForecastingCustomViewPopup");
		view.setDefaultView(false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList);

	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rootLayoutComponentConfig = new GtnUIFrameworkComponentConfig();
		rootLayoutComponentConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		rootLayoutComponentConfig.setComponentId(tabName + "customViewLookupRootLayout");
		rootLayoutComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rootLayoutComponentConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rootLayoutComponentConfig.setSpacing(Boolean.TRUE);

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
		addTreeViewNameTextBox(componentList, searchCriteriaLayout.getComponentId());
		addMainLayoutForLeftTables(componentList, searchCriteriaLayout.getComponentId());
		addCustomTree(componentList, searchCriteriaLayout.getComponentId());
		addControlButtonLayout(componentList);
	}

	private void addTreeViewNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig treeViewNameLayout = layoutsConfig.getCssLayoutConfig(tabName + "customViewLookupViewName",
				parentId);
		treeViewNameLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(treeViewNameLayout);
		GtnUIFrameworkComponentConfig treeViewNameTextBox = new GtnUIFrameworkComponentConfig();
		treeViewNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		treeViewNameTextBox.setComponentId(tabName + "customViewLookupTreeViewName");
		treeViewNameTextBox.setComponentName("Tree View Name : ");
		treeViewNameTextBox.setAddToParent(true);
		treeViewNameTextBox.setParentComponentId(treeViewNameLayout.getComponentId());
		treeViewNameTextBox.setComponentWsFieldId(tabName + "customViewLookupTreeViewName");
		componentList.add(treeViewNameTextBox);
	}

	private void addMainLayoutForLeftTables(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig treeMainLayout = layoutsConfig
				.getCssLayoutConfig(tabName + "customViewLookupMainLefttableLayout", parentId);
		treeMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(treeMainLayout);

		addCustomerTree(componentList, treeMainLayout.getComponentId());

		addProductTree(componentList, treeMainLayout.getComponentId());

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
		customerTreeTable.setComponentHight("270px");
		customerTreeTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig customerTreeTableConfig = new GtnUIFrameworkPagedTableConfig();
		customerTreeTableConfig.setCaptionVisible(Boolean.TRUE);
		customerTreeTableConfig.setSelectable(Boolean.TRUE);
		customerTreeTableConfig.setPageLength(10);
		customerTreeTableConfig.setItemPerPage(10);
		customerTreeTableConfig.setTableColumnDataType(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_TABLE_COLUMNS_DATA_TYPE);
		customerTreeTableConfig.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_TABLE_HERDERS);
		customerTreeTableConfig.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECAST_CUSTOMER_TABLE_COLUMNS);

		customerTreeTableConfig.setModuleName("commercialForecasting");
		customerTreeTableConfig.setQueryName("searchQuery");

		customerTreeTable.setGtnPagedTableConfig(customerTreeTableConfig);
		componentList.add(customerTreeTable);

		GtnUIFrameworkComponentConfig customerBtnCssLayout = layoutsConfig.getCssLayoutConfig(tabName + "customerBtMain",
				parentId);
		customerBtnCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(customerBtnCssLayout);

		GtnUIFrameworkComponentConfig customerBtnVerticalLayout = layoutsConfig.getVerticalLayoutConfig(tabName + "customerBtn",
				customerBtnCssLayout.getComponentId());
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
				.getCssLayoutConfig(tabName + "mainCssLayoutForProduct", parentId);
		mainCssLayoutForProduct.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10);
		componentList.add(mainCssLayoutForProduct);

		GtnUIFrameworkComponentConfig productTreeTable = new GtnUIFrameworkComponentConfig();
		productTreeTable.setComponentType(GtnUIFrameworkComponentType.DATATABLE);
		productTreeTable.setComponentId(tabName + "customViewLookupProductTable");
		productTreeTable.setComponentName("Product Hierarchy");
		productTreeTable.setAddToParent(true);
		productTreeTable.setParentComponentId(mainCssLayoutForProduct.getComponentId());
		productTreeTable.setComponentHight("270px");
		productTreeTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkPagedTableConfig productTreeTableConfig = new GtnUIFrameworkPagedTableConfig();
		productTreeTableConfig.setCaptionVisible(Boolean.TRUE);
		productTreeTableConfig.setPageLength(10);
		productTreeTableConfig.setItemPerPage(10);
		productTreeTableConfig.setSelectable(Boolean.TRUE);

		productTreeTableConfig.setTableColumnDataType(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_TABLE_COLUMNS_DATA_TYPE);
		productTreeTableConfig.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_TABLE_HERDERS);
		productTreeTableConfig.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECAST_CUSTOMER_TABLE_COLUMNS);

		productTreeTableConfig.setModuleName("commercialForecasting");
		productTreeTableConfig.setQueryName("searchQuery");
		productTreeTable.setGtnPagedTableConfig(productTreeTableConfig);
		componentList.add(productTreeTable);

		GtnUIFrameworkComponentConfig productBtnCssLayout = layoutsConfig.getCssLayoutConfig(tabName + "productBtnMain",
				parentId);
		productBtnCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(productBtnCssLayout);

		GtnUIFrameworkComponentConfig productBtnVerticalLayout = layoutsConfig
				.getVerticalLayoutConfig(tabName + "productBtn", productBtnCssLayout.getComponentId());
		componentList.add(productBtnVerticalLayout);

		GtnUIFrameworkComponentConfig addProductButton = new GtnUIFrameworkComponentConfig();
		addProductButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addProductButton.setAddToParent(true);
		addProductButton.setParentComponentId(productBtnVerticalLayout.getComponentId());
		addProductButton.setComponentId(tabName + "addProductBtn");
		addProductButton.setComponentName(" > ");
		componentList.add(addProductButton);

		GtnUIFrameworkComponentConfig removeProductButton = new GtnUIFrameworkComponentConfig();
		removeProductButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		removeProductButton.setAddToParent(true);
		removeProductButton.setParentComponentId(productBtnVerticalLayout.getComponentId());
		removeProductButton.setComponentId(tabName + "removeProductBtn");
		removeProductButton.setComponentName(" < ");
		componentList.add(removeProductButton);
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
		GtnUIFrameworkPagedTableConfig config = new GtnUIFrameworkPagedTableConfig();
		config.setSelectable(true);
		config.setTableColumnDataType(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_TABLE_COLUMNS_DATA_TYPE);
		config.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECAST_CUSTOMER_TABLE_COLUMNS);
		config.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_TABLE_HERDERS);
		customTree.setGtnPagedTableConfig(config);
		componentList.add(customTree);

	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId(tabName + "customLookupControlButtonLayout");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);

		componentList.add(layoutConfig);

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
