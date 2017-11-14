/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingTableConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkCommercialForecastingCustomerHierarchyTabLookUp {

	public GtnUIFrameworkViewConfig getCustHierarchyLookUpView() {
	
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Customer Hierarchy Tab LookUp");
		view.setViewId("custHierarchyTabLookupView");
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
		
		GtnUIFrameworkComponentConfig vLayout = new GtnUIFrameworkComponentConfig();
		vLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		vLayout.setComponentId("customerHierarchyTabLookupRootLayout");
		vLayout.setAddToParent(false);
		vLayout.setSpacing(Boolean.TRUE);
		vLayout.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		vLayout.setGtnLayoutConfig(conf);
		componentList.add(vLayout);
		getRootComponentForPopUp(componentList,vLayout.getComponentId());
		
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(conf);
		gtnLayout.setAddToParent(true);
		gtnLayout.setComponentId("customerHierarchyTabLookupRootVerticalLayout");
		gtnLayout.setParentComponentId(parentComponentId);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setSpacing(Boolean.TRUE);
		gtnLayout.setMargin(Boolean.TRUE);
		componentList.add(gtnLayout);
		
		searchCriteriaPanel(componentList,gtnLayout.getComponentId());
		searchAndResetButtonLayout(componentList,gtnLayout.getComponentId());
		resultsPanel(componentList,gtnLayout.getComponentId());
		addControlPopUpButtonLayout(componentList,gtnLayout.getComponentId());
		
	}

	private void searchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId("custHierarchyTabLookupSearchCriteriaPanel");
		searchCriteriaPanel.setComponentName("Search Criteria");
		searchCriteriaPanel.setParentComponentId(parentComponentId);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		componentList.add(searchCriteriaPanel);
		searchCriteriaLayout(componentList,searchCriteriaPanel.getComponentId());
		
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig custHierarchyTabLookupSearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		custHierarchyTabLookupSearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		custHierarchyTabLookupSearchCriteriaLayout.setComponentId("custHierarchyTabLookupSearchCriteriaLayout");
		custHierarchyTabLookupSearchCriteriaLayout.setComponentName("Search Criteria");
		custHierarchyTabLookupSearchCriteriaLayout.setAddToParent(true);
		custHierarchyTabLookupSearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		custHierarchyTabLookupSearchCriteriaLayout.setParentComponentId(parentComponentId);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		custHierarchyTabLookupSearchCriteriaLayout.setGtnLayoutConfig(conf);
		componentList.add(custHierarchyTabLookupSearchCriteriaLayout);
		addHierarchyTypeOptionGroup(componentList,custHierarchyTabLookupSearchCriteriaLayout.getComponentId());
		addHierarchyNameTextBox(componentList,custHierarchyTabLookupSearchCriteriaLayout.getComponentId());
		
	}

	private void addHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		customerHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		customerHierarchyTypeOptionGroup.setComponentId("customerHierarchyTabLookupType");
		customerHierarchyTypeOptionGroup.setComponentName("Hierarchy Type");
		customerHierarchyTypeOptionGroup.setAddToParent(true);
		customerHierarchyTypeOptionGroup.setParentComponentId(parentComponentId);
		GtnUIFrameworkOptionGroupConfig customerHierarchyTypeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		customerHierarchyTypeOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Primary", "Secondary" }));
		customerHierarchyTypeOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		customerHierarchyTypeOptionGroup.setComponentWsFieldId("customerHierarchyTabLookupType");
		customerHierarchyTypeOptionGroup
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		customerHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(customerHierarchyTypeOptionGroupConfig);
		componentList.add(customerHierarchyTypeOptionGroup);
		
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerLookupHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		customerLookupHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		customerLookupHierarchyNameTextBox.setComponentId("customerHierarchyTabLookupName");
		customerLookupHierarchyNameTextBox.setComponentName("Hierarchy Name");
		customerLookupHierarchyNameTextBox.setAddToParent(true);
		customerLookupHierarchyNameTextBox.setComponentWsFieldId("customerHierarchyTabLookupName");
		customerLookupHierarchyNameTextBox.setParentComponentId(parentComponentId);
		componentList.add(customerLookupHierarchyNameTextBox);
	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerHierarchyLookupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		customerHierarchyLookupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerHierarchyLookupSearchAndResetLayout.setAddToParent(true);
		customerHierarchyLookupSearchAndResetLayout
				.setParentComponentId(parentComponentId);
		customerHierarchyLookupSearchAndResetLayout.setComponentId("custHierarchyTabLookupSearchAndResetButtonLayout");
		customerHierarchyLookupSearchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		customerHierarchyLookupSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(customerHierarchyLookupSearchAndResetLayout);

		GtnUIFrameworkComponentConfig customerHierarchyLookupSearchButton = new GtnUIFrameworkComponentConfig();
		customerHierarchyLookupSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerHierarchyLookupSearchButton.setComponentId("custHierarchyTabLookupSearchCriteriaButton");
		customerHierarchyLookupSearchButton.setComponentName("SEARCH");
		customerHierarchyLookupSearchButton.setParentComponentId(customerHierarchyLookupSearchAndResetLayout.getComponentId());
		customerHierarchyLookupSearchButton.setAddToParent(true);
		componentList.add(customerHierarchyLookupSearchButton);

		GtnUIFrameworkComponentConfig customerHierarchylookupResetButton = new GtnUIFrameworkComponentConfig();
		customerHierarchylookupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerHierarchylookupResetButton.setComponentId("customerHierarchyTabLookupResetCriteriaButton");
		customerHierarchylookupResetButton.setComponentName("RESET");
		customerHierarchylookupResetButton.setParentComponentId(customerHierarchyLookupSearchAndResetLayout.getComponentId());
		customerHierarchylookupResetButton.setAddToParent(true);
		componentList.add(customerHierarchylookupResetButton);
		
	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId("customerHierarchyTabLookupResultsPanel");
		searchCriteriaPanel.setComponentName("Results");
		searchCriteriaPanel.setParentComponentId(parentComponentId);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		componentList.add(searchCriteriaPanel);
		resultLayout(componentList,searchCriteriaPanel.getComponentId());

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig reasultsPanel = new GtnUIFrameworkComponentConfig();
		reasultsPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reasultsPanel.setComponentId("customerHierarchyTabLookupResultLayout");

		reasultsPanel.setParentComponentId(parentComponentId);
		reasultsPanel.setAddToParent(true);
		reasultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reasultsPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reasultsPanel.setGtnLayoutConfig(conf);
		componentList.add(reasultsPanel);
		addPagedTableComponent(componentList,reasultsPanel.getComponentId());
		
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerHierarchySearchResultConfig = new GtnUIFrameworkComponentConfig();
		customerHierarchySearchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		customerHierarchySearchResultConfig.setComponentId("customerHierarchyTabLookupSearchResultTable");
		customerHierarchySearchResultConfig.setComponentName("Results");
		customerHierarchySearchResultConfig.setParentComponentId(parentComponentId);
		customerHierarchySearchResultConfig.setAddToParent(Boolean.TRUE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		customerHierarchySearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerHierarchySearchResultConfig.setComponentStyle(tableStyle);

		componentList.add(customerHierarchySearchResultConfig);
		GtnUIFrameworkPagedTableConfig customerHierarchySearchResults = new GtnUIFrameworkPagedTableConfig();
		customerHierarchySearchResults.setEditable(Boolean.FALSE);
		customerHierarchySearchResults.setFilterBar(Boolean.TRUE);
		customerHierarchySearchResults.setSelectable(Boolean.FALSE);
		customerHierarchySearchResults.setPageLength(10);
		customerHierarchySearchResults.setItemPerPage(10);
		customerHierarchySearchResults.setSelectable(Boolean.TRUE);
		customerHierarchySearchResults.setTableColumnDataType(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_HIERARCHY_TABLE_COLUMNS_DATA_TYPE);
		customerHierarchySearchResults.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_HIERARCHY_TABLE_HEADER);

		customerHierarchySearchResults.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_HIERARCHY_TABLE_COLUMNS);

		customerHierarchySearchResults.setModuleName("HierarchyLookUp");
		customerHierarchySearchResults.setQueryName("searchQuery");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		customerHierarchySearchResults.setCustomFilterConfigMap(customFilterConfigMap);
		customerHierarchySearchResultConfig.setGtnPagedTableConfig(customerHierarchySearchResults);
		
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(true);
		controlPopUpLayout.setParentComponentId(parentComponentId);
		controlPopUpLayout.setComponentId("customerHierarchyTabLookupControlPopUpButtonLayout");
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton.setComponentId("customerHierarchyTabLookupSelectButton");
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId(controlPopUpLayout.getComponentId());
		selectButton.setAddToParent(true);
		componentList.add(selectButton);

		GtnUIFrameworkComponentConfig cancelButton = new GtnUIFrameworkComponentConfig();
		cancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cancelButton.setComponentId("customerHierarchyTabLookupCancelButton");
		cancelButton.setComponentName("CANCEL");
		cancelButton.setParentComponentId(controlPopUpLayout.getComponentId());
		cancelButton.setAddToParent(true);
		componentList.add(cancelButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("customerHierarchyTabLookupResetControlButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(controlPopUpLayout.getComponentId());
		resetButton.setAddToParent(true);
		componentList.add(resetButton);
		
	}
}
