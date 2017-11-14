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

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingStringConstants;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingTableConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkCommercialForecastingCustomerGroupLookUp {

	public GtnUIFrameworkViewConfig getCustomerGroupLookUpView() {
	
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Customer Group LookUp");
		view.setViewId("custGroupLookupView");
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
		
		GtnUIFrameworkComponentConfig customerGroupRootLayout = new GtnUIFrameworkComponentConfig();
		customerGroupRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerGroupRootLayout.setComponentId("customerGroupLookupRootLayout");
		customerGroupRootLayout.setAddToParent(Boolean.FALSE);
		customerGroupRootLayout.setSpacing(Boolean.TRUE);
		customerGroupRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkLayoutConfig customerGroupRootLayoutConf = new GtnUIFrameworkLayoutConfig();
		customerGroupRootLayoutConf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		customerGroupRootLayout.setGtnLayoutConfig(customerGroupRootLayoutConf);
		componentList.add(customerGroupRootLayout);
		getRootComponentForPopUp(componentList,customerGroupRootLayout.getComponentId());
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(conf);
		gtnLayout.setAddToParent(Boolean.TRUE);
		gtnLayout.setComponentId("customerGroupLookupRootVerticalLayout");
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

	private void searchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
	
		GtnUIFrameworkComponentConfig customerGroupLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		customerGroupLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerGroupLookupSearchCriteriaPanel.setComponentId("customerGroupLookupSearchCriteriaPanel");
		customerGroupLookupSearchCriteriaPanel.setComponentName("Search Criteria");
		customerGroupLookupSearchCriteriaPanel.setParentComponentId(parentComponentId);
		customerGroupLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerGroupLookupSearchCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupLookupSearchCriteriaPanel);
		searchCriteriaLayout(componentList,customerGroupLookupSearchCriteriaPanel.getComponentId());
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
	
		GtnUIFrameworkComponentConfig customerGroupLookupSearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		customerGroupLookupSearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerGroupLookupSearchCriteriaLayout.setComponentId("customerGroupLookupSearchCriteriaLayout");
		customerGroupLookupSearchCriteriaLayout.setComponentName("Search Criteria");
		customerGroupLookupSearchCriteriaLayout.setAddToParent(Boolean.TRUE);
		customerGroupLookupSearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerGroupLookupSearchCriteriaLayout.setParentComponentId(parentComponentId);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		customerGroupLookupSearchCriteriaLayout.setGtnLayoutConfig(conf);
		componentList.add(customerGroupLookupSearchCriteriaLayout);
		addCustomerGroupNameTextBox(componentList,customerGroupLookupSearchCriteriaLayout.getComponentId());
		addCustomerNoTextBox(componentList,customerGroupLookupSearchCriteriaLayout.getComponentId());
	}

	private void addCustomerGroupNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig customerGropuNameTextBox = new GtnUIFrameworkComponentConfig();
		customerGropuNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		customerGropuNameTextBox.setComponentId("customerGroupLookupName");
		customerGropuNameTextBox.setComponentName("Customer Group Name:");
		customerGropuNameTextBox.setAddToParent(Boolean.TRUE);
		customerGropuNameTextBox.setComponentWsFieldId("customerGroupLookupName");
		customerGropuNameTextBox.setParentComponentId(parentComponentId);
		componentList.add(customerGropuNameTextBox);
	}

	private void addCustomerNoTextBox(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerGroupNoTextBox = new GtnUIFrameworkComponentConfig();
		customerGroupNoTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		customerGroupNoTextBox.setComponentId("customerGroupLookupNo");
		customerGroupNoTextBox.setComponentName("Customer Group No:");
		customerGroupNoTextBox.setAddToParent(Boolean.TRUE);
		customerGroupNoTextBox.setComponentWsFieldId("customerGroupLookupNo");
		customerGroupNoTextBox.setParentComponentId(parentComponentId);
		componentList.add(customerGroupNoTextBox);
	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerGroupLookupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		customerGroupLookupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		customerGroupLookupSearchAndResetLayout.setAddToParent(Boolean.TRUE);
		customerGroupLookupSearchAndResetLayout.setParentComponentId(parentComponentId);
		customerGroupLookupSearchAndResetLayout.setComponentId("customerGroupLookupSearchAndResetButtonLayout");
		customerGroupLookupSearchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		customerGroupLookupSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(customerGroupLookupSearchAndResetLayout);

		GtnUIFrameworkComponentConfig customerGroupLookupSearchButton = new GtnUIFrameworkComponentConfig();
		customerGroupLookupSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupLookupSearchButton.setComponentId("customerGroupLookupSearchCriteriaButton");
		customerGroupLookupSearchButton.setComponentName("SEARCH");
		customerGroupLookupSearchButton.setParentComponentId(customerGroupLookupSearchAndResetLayout.getComponentId());
		customerGroupLookupSearchButton.setAddToParent(Boolean.TRUE);
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig
				.setActionParameterList(Arrays.asList(new Object[] { "customerGroupLookupSearchResultTable" }));
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList(new String[] { "customerGroupLookupName", "customerGroupLookupNo" }));
		actionConfigList.add(loadDataTableActionConfig);

		customerGroupLookupSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		
		componentList.add(customerGroupLookupSearchButton);

		GtnUIFrameworkComponentConfig customerGroupResetButton = new GtnUIFrameworkComponentConfig();
		customerGroupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupResetButton.setComponentId("customerGroupLookupResetCriteriaButton");
		customerGroupResetButton.setComponentName("RESET");
		customerGroupResetButton.setParentComponentId(customerGroupLookupSearchAndResetLayout.getComponentId());
		customerGroupResetButton.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupResetButton);
	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerGroupLookupResultsPanel = new GtnUIFrameworkComponentConfig();
		customerGroupLookupResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerGroupLookupResultsPanel.setComponentId("customerGroupLookupResultsPanel");
		customerGroupLookupResultsPanel.setComponentName("Results");
		customerGroupLookupResultsPanel.setParentComponentId(parentComponentId);
		customerGroupLookupResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerGroupLookupResultsPanel.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupLookupResultsPanel);
		resultLayout(componentList,customerGroupLookupResultsPanel.getComponentId());

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkLayoutConfig customerGroupLookupLayoutConfig = new GtnUIFrameworkLayoutConfig();
		customerGroupLookupLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaPanel.setComponentId("customerGroupLookupResultLayout");
		searchCriteriaPanel.setGtnLayoutConfig(customerGroupLookupLayoutConfig);
		searchCriteriaPanel.setParentComponentId(parentComponentId);
		searchCriteriaPanel.setAddToParent(Boolean.TRUE);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(searchCriteriaPanel);
		addPagedleComponent(componentList,searchCriteriaPanel.getComponentId());
	}

	private void addPagedleComponent(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		
		GtnUIFrameworkComponentConfig customerGroupSearchResultConfig = new GtnUIFrameworkComponentConfig();
		customerGroupSearchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		customerGroupSearchResultConfig.setComponentId("customerGroupLookupSearchResultTable");
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
		customerGroupSearchResults.setCountUrl("/gtnCommercialForecasting/lookUp/getCustomerGroupLookUp");
		customerGroupSearchResults.setResultSetUrl("/gtnCommercialForecasting/lookUp/getCustomerGroupLookUp");
		
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
		controlPopUpLayout.setComponentId("customerGroupLookupControlPopUpButtonLayout");
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig customerGroupSelectButton = new GtnUIFrameworkComponentConfig();
		customerGroupSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupSelectButton.setComponentId("customerGroupLookupSelectButton");
		customerGroupSelectButton.setComponentName("SELECT");
		customerGroupSelectButton.setParentComponentId(controlPopUpLayout.getComponentId());
		customerGroupSelectButton.setAddToParent(Boolean.TRUE);
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);

		selectAction.setActionParameterList(Arrays.asList(new Object[] { "customerGroupLookupSearchResultTable",
				"customerGroup", (Arrays.asList(new String[] { "custGroupLookupCustomerGroupNameFilterView" })),
				Arrays.asList(new String[] { "customerGroup" }) }));
		actionConfigList.add(selectAction);
		
		GtnUIFrameWorkActionConfig closeOnSelectAction = new GtnUIFrameWorkActionConfig();
		closeOnSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeOnSelectAction.setActionParameterList(Arrays.asList(new Object[] { "custGroupLookupView" }));
		actionConfigList.add(closeOnSelectAction);
		
		customerGroupSelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		
		componentList.add(customerGroupSelectButton);

		GtnUIFrameworkComponentConfig customerGroupCancelButton = new GtnUIFrameworkComponentConfig();
		customerGroupCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupCancelButton.setComponentId("customerGroupLookupCancelButton");
		customerGroupCancelButton.setComponentName("CANCEL");
		customerGroupCancelButton.setParentComponentId(controlPopUpLayout.getComponentId());
		customerGroupCancelButton.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupCancelButton);

		GtnUIFrameworkComponentConfig customerGroupResetButton = new GtnUIFrameworkComponentConfig();
		customerGroupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		customerGroupResetButton.setComponentId("customerGroupLookupResetControlButton");
		customerGroupResetButton.setComponentName("RESET");
		customerGroupResetButton.setParentComponentId(controlPopUpLayout.getComponentId());
		customerGroupResetButton.setAddToParent(Boolean.TRUE);
		componentList.add(customerGroupResetButton);
	}
}
