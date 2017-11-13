package com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingStringConstants;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingTableConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingCustomerGroupTabLookUp {

	public GtnUIFrameworkViewConfig getCustomerGroupTabLookUpView() {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Customer Group Tab LookUp");
		view.setViewId("custGroupTabLookupView");
		view.setDefaultView(Boolean.FALSE);

		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList);

	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList) {
	
		GtnUIFrameworkComponentConfig customerGroupTabLookupRootLayout = new GtnUIFrameworkComponentConfig();
		customerGroupTabLookupRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerGroupTabLookupRootLayout.setComponentId("customerGroupTabLookupRootLayout");
		customerGroupTabLookupRootLayout.setAddToParent(Boolean.FALSE);
		customerGroupTabLookupRootLayout.setSpacing(Boolean.TRUE);
		customerGroupTabLookupRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		
		GtnUIFrameworkLayoutConfig customerGroupTabLookupRootLayoutConf = new GtnUIFrameworkLayoutConfig();
		customerGroupTabLookupRootLayoutConf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		customerGroupTabLookupRootLayout.setGtnLayoutConfig(customerGroupTabLookupRootLayoutConf);
		componentList.add(customerGroupTabLookupRootLayout);
		getRootComponentForPopUp(componentList,customerGroupTabLookupRootLayout.getComponentId());
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(conf);
		gtnLayout.setAddToParent(Boolean.TRUE);
		gtnLayout.setComponentId("customerGroupTabLookupRootVerticalLayout");
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
		
		GtnUIFrameworkComponentConfig customerGroupTabLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		customerGroupTabLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerGroupTabLookupSearchCriteriaPanel.setComponentId("customerGroupTabLookupSearchCriteriaPanel");
		customerGroupTabLookupSearchCriteriaPanel.setComponentName("Search Criteria");
		customerGroupTabLookupSearchCriteriaPanel.setParentComponentId(parentComponentId);
		customerGroupTabLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerGroupTabLookupSearchCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupTabLookupSearchCriteriaPanel);
		searchCriteriaLayout(componentList,customerGroupTabLookupSearchCriteriaPanel.getComponentId());
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerGroupTabLookupSearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		customerGroupTabLookupSearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerGroupTabLookupSearchCriteriaLayout.setComponentId("customerGroupTabLookupSearchCriteriaLayout");
		customerGroupTabLookupSearchCriteriaLayout.setComponentName("Search Criteria");
		customerGroupTabLookupSearchCriteriaLayout.setAddToParent(Boolean.TRUE);
		customerGroupTabLookupSearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerGroupTabLookupSearchCriteriaLayout.setParentComponentId(parentComponentId);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		customerGroupTabLookupSearchCriteriaLayout.setGtnLayoutConfig(conf);
		componentList.add(customerGroupTabLookupSearchCriteriaLayout);
		addCustomerGroupNameTextBox(componentList,customerGroupTabLookupSearchCriteriaLayout.getComponentId());
		addCustomerNoTextBox(componentList,customerGroupTabLookupSearchCriteriaLayout.getComponentId());
	}

	private void addCustomerGroupNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerGropuNameTextBox = new GtnUIFrameworkComponentConfig();
		customerGropuNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		customerGropuNameTextBox.setComponentId("customerGroupTabLookupName");
		customerGropuNameTextBox.setComponentName("Customer Group Name:");
		customerGropuNameTextBox.setAddToParent(Boolean.TRUE);
		customerGropuNameTextBox.setComponentWsFieldId("customerGroupTabLookupName");
		customerGropuNameTextBox.setParentComponentId(parentComponentId);
		componentList.add(customerGropuNameTextBox);
	}

	private void addCustomerNoTextBox(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		GtnUIFrameworkComponentConfig customerGroupNoTextBox = new GtnUIFrameworkComponentConfig();
		customerGroupNoTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		customerGroupNoTextBox.setComponentId("customerGroupTabLookupNo");
		customerGroupNoTextBox.setComponentName("Customer Group No:");
		customerGroupNoTextBox.setAddToParent(Boolean.TRUE);
		customerGroupNoTextBox.setComponentWsFieldId("customerGroupTabLookupNo");
		customerGroupNoTextBox.setParentComponentId(parentComponentId);
		componentList.add(customerGroupNoTextBox);
	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerGroupTabLookupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		customerGroupTabLookupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerGroupTabLookupSearchAndResetLayout.setAddToParent(Boolean.TRUE);
		customerGroupTabLookupSearchAndResetLayout.setParentComponentId(parentComponentId);
		customerGroupTabLookupSearchAndResetLayout.setComponentId("customerGroupTabLookupSearchAndResetButtonLayout");
		customerGroupTabLookupSearchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		customerGroupTabLookupSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(customerGroupTabLookupSearchAndResetLayout);

		GtnUIFrameworkComponentConfig customerGroupTabLookupSearchButton = new GtnUIFrameworkComponentConfig();
		customerGroupTabLookupSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupTabLookupSearchButton.setComponentId("customerGroupTabLookupSearchCriteriaButton");
		customerGroupTabLookupSearchButton.setComponentName("SEARCH");
		customerGroupTabLookupSearchButton.setParentComponentId(customerGroupTabLookupSearchAndResetLayout.getComponentId());
		customerGroupTabLookupSearchButton.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupTabLookupSearchButton);

		GtnUIFrameworkComponentConfig customerGroupResetButton = new GtnUIFrameworkComponentConfig();
		customerGroupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupResetButton.setComponentId("customerGroupTabLookupResetCriteriaButton");
		customerGroupResetButton.setComponentName("RESET");
		customerGroupResetButton.setParentComponentId(customerGroupTabLookupSearchAndResetLayout.getComponentId());
		customerGroupResetButton.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupResetButton);
	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerGroupTabLookupResultsPanel = new GtnUIFrameworkComponentConfig();
		customerGroupTabLookupResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerGroupTabLookupResultsPanel.setComponentId("customerGroupTabLookupResultsPanel");
		customerGroupTabLookupResultsPanel.setComponentName("Results");
		customerGroupTabLookupResultsPanel.setParentComponentId(parentComponentId);
		customerGroupTabLookupResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerGroupTabLookupResultsPanel.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupTabLookupResultsPanel);
		resultLayout(componentList,customerGroupTabLookupResultsPanel.getComponentId());

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
	
		GtnUIFrameworkLayoutConfig customerGroupTabLookupLayoutConfig = new GtnUIFrameworkLayoutConfig();
		customerGroupTabLookupLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaPanel.setComponentId("customerGroupTabLookupResultLayout");
		searchCriteriaPanel.setGtnLayoutConfig(customerGroupTabLookupLayoutConfig);
		searchCriteriaPanel.setParentComponentId(parentComponentId);
		searchCriteriaPanel.setAddToParent(Boolean.TRUE);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(searchCriteriaPanel);
		addPagedTableComponent(componentList,searchCriteriaPanel.getComponentId());
		
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerGroupSearchResultConfig = new GtnUIFrameworkComponentConfig();
		customerGroupSearchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		customerGroupSearchResultConfig.setComponentId("customerGroupTabLookupSearchResultTable");
		customerGroupSearchResultConfig.setComponentName("Results");
		customerGroupSearchResultConfig.setParentComponentId(parentComponentId);
		customerGroupSearchResultConfig.setAddToParent(Boolean.TRUE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		customerGroupSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerGroupSearchResultConfig.setComponentStyle(tableStyle);

		componentList.add(customerGroupSearchResultConfig);
		GtnUIFrameworkPagedTableConfig customerGroupSearchResults = new GtnUIFrameworkPagedTableConfig();
		customerGroupSearchResults.setEditable(Boolean.FALSE);
		customerGroupSearchResults.setFilterBar(Boolean.TRUE);
		customerGroupSearchResults.setSelectable(Boolean.FALSE);
		customerGroupSearchResults.setPageLength(10);
		customerGroupSearchResults.setItemPerPage(10);
		customerGroupSearchResults.setSelectable(Boolean.TRUE);
		customerGroupSearchResults.setTableColumnDataType(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_GROUP_TABLE_COLUMNS_DATA_TYPE);
		customerGroupSearchResults.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_GROUP_TABLE_HEADER);

		customerGroupSearchResults.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_CUSTOMER_GROUP_TABLE_COLUMNS);

		customerGroupSearchResults.setModuleName(GtnFrameworkCommercialForecastingStringConstants.MODULE_NAME);
		customerGroupSearchResults.setQueryName("searchQuery");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		customerGroupSearchResults.setCustomFilterConfigMap(customFilterConfigMap);
		customerGroupSearchResultConfig.setGtnPagedTableConfig(customerGroupSearchResults);
		
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(Boolean.TRUE);
		controlPopUpLayout.setParentComponentId(parentComponentId);
		controlPopUpLayout.setComponentId("customerGroupTabLookupControlPopUpButtonLayout");
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig customerGroupSelectButton = new GtnUIFrameworkComponentConfig();
		customerGroupSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupSelectButton.setComponentId("customerGroupTabLookupSelectButton");
		customerGroupSelectButton.setComponentName("SELECT");
		customerGroupSelectButton.setParentComponentId(controlPopUpLayout.getComponentId());
		customerGroupSelectButton.setAddToParent(Boolean.TRUE);

		GtnUIFrameworkComponentConfig customerGroupCancelButton = new GtnUIFrameworkComponentConfig();
		customerGroupCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupCancelButton.setComponentId("customerGroupTabLookupCancelButton");
		customerGroupCancelButton.setComponentName("CANCEL");
		customerGroupCancelButton.setParentComponentId(controlPopUpLayout.getComponentId());
		customerGroupCancelButton.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupCancelButton);

		GtnUIFrameworkComponentConfig customerGroupResetButton = new GtnUIFrameworkComponentConfig();
		customerGroupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupResetButton.setComponentId("customerGroupTabLookupResetControlButton");
		customerGroupResetButton.setComponentName("RESET");
		customerGroupResetButton.setParentComponentId(controlPopUpLayout.getComponentId());
		customerGroupResetButton.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupResetButton);
	}
}
