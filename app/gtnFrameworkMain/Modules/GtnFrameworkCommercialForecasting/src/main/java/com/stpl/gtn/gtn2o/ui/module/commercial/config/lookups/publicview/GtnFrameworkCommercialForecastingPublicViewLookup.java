package com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.publicview;

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
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingTableConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkCommercialForecastingPublicViewLookup {

	public GtnUIFrameworkViewConfig getPublicViewLookUpView() {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Public Views");
		view.setViewId("publicViewSearchLookupView");
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

		GtnUIFrameworkComponentConfig publicViewLooupVLayout = new GtnUIFrameworkComponentConfig();
		publicViewLooupVLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLooupVLayout.setComponentId("publicViewLookupRootLayout");
		publicViewLooupVLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLooupVLayout.setAddToParent(false);
		publicViewLooupVLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		publicViewLooupVLayout.setGtnLayoutConfig(conf);
		componentList.add(publicViewLooupVLayout);
		getRootComponentForPopUp(componentList);
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig publicViewLookupComponentConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig publicViewLookupLayoutConfig = new GtnUIFrameworkLayoutConfig();
		publicViewLookupLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		publicViewLookupComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookupComponentConfig.setGtnLayoutConfig(publicViewLookupLayoutConfig);
		publicViewLookupComponentConfig.setAddToParent(true);
		publicViewLookupComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookupComponentConfig.setComponentId("publicViewLookupRootVerticalLayout");
		publicViewLookupComponentConfig.setParentComponentId("publicViewLookupRootLayout");
		publicViewLookupComponentConfig.setSpacing(Boolean.TRUE);
		publicViewLookupComponentConfig.setMargin(Boolean.TRUE);
		componentList.add(publicViewLookupComponentConfig);
		searchCriteriaPanel(componentList);
		searchAndResetButtonLayout(componentList);
		resultsPanel(componentList);
		addControlPopUpButtonLayout(componentList);
	}

	private void searchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId("publicViewLookupSearchCriteriaPanel");
		searchCriteriaPanel.setComponentName("Search Criteria");
		searchCriteriaPanel.setParentComponentId("publicViewLookupRootVerticalLayout");
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		componentList.add(searchCriteriaPanel);
		searchCriteriaLayout(componentList);
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig searchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		searchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaLayout.setComponentId("publicViewLookupSearchCriteriaLayout");
		searchCriteriaLayout.setComponentName("Search Criteria");
		searchCriteriaLayout.setAddToParent(true);
		searchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaLayout.setParentComponentId("publicViewLookupSearchCriteriaPanel");
		searchCriteriaLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(searchCriteriaLayout);
		addHierarchyNameTextBox(componentList);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutsConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

		GtnUIFrameworkComponentConfig horizontalViewNameLayout = layoutsConfig.getHorizontalLayoutConfig(
				"publicViewLookupHorizontalViewNameLayout", "publicViewLookupSearchCriteriaLayout");
		componentList.add(horizontalViewNameLayout);
		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyNameTextBox.setComponentId("publicViewLookupViewName");
		addHierarchyNameTextBox.setComponentName("View Name");
		addHierarchyNameTextBox.setAddToParent(true);
		addHierarchyNameTextBox.setParentComponentId(horizontalViewNameLayout.getComponentId());
		addHierarchyNameTextBox.setComponentWsFieldId("publicViewLookupViewName");
		componentList.add(addHierarchyNameTextBox);

	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchAndResetLayout = new GtnUIFrameworkComponentConfig();
		searchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchAndResetLayout.setAddToParent(true);
		searchAndResetLayout.setParentComponentId("publicViewLookupRootVerticalLayout");
		searchAndResetLayout.setComponentId("publicViewLookupSearchAndResetButtonLayout");
		searchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		searchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(searchAndResetLayout);

		GtnUIFrameworkComponentConfig searchButton = new GtnUIFrameworkComponentConfig();
		searchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButton.setComponentId("publicViewLookupSearchCriteriaButton");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId("publicViewLookupSearchAndResetButtonLayout");
		searchButton.setAddToParent(true);
		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("publicViewLookupResetCriteriaButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId("publicViewLookupSearchAndResetButtonLayout");
		resetButton.setAddToParent(true);
		componentList.add(resetButton);

	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId("publicViewLookupResultsPanel");
		searchCriteriaPanel.setComponentName("Results");
		searchCriteriaPanel.setParentComponentId("publicViewLookupRootVerticalLayout");
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		componentList.add(searchCriteriaPanel);
		resultLayout(componentList);

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaPanel.setComponentId("publicViewLookupResultLayout");

		searchCriteriaPanel.setGtnLayoutConfig(conf);
		searchCriteriaPanel.setParentComponentId("publicViewLookupResultsPanel");
		searchCriteriaPanel.setAddToParent(true);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(searchCriteriaPanel);
		addPagedTableComponent(componentList);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId("publicViewLookupSearchResultTable");
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setParentComponentId("publicViewLookupResultLayout");
		searchResultConfig.setAddToParent(true);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);
		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);

		searchResults.setTableColumnDataType(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PUBLIC_VIEW_TABLE_COLUMNS_DATA_TYPE);
		searchResults.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PUBLIC_VIEW_TABLE_HERDERS);
		searchResults.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PUBLIC_VIEW_TABLE_COLUMNS);

		searchResults.setModuleName("PublicViewLookup");
		searchResults.setQueryName("public");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);

	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(true);
		controlPopUpLayout.setParentComponentId("publicViewLookupRootVerticalLayout");
		controlPopUpLayout.setComponentId("publicViewLookupControlPopUpButtonLayout");
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton.setComponentId("publicViewLookupSelectButton");
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId("publicViewLookupControlPopUpButtonLayout");
		selectButton.setAddToParent(true);
		componentList.add(selectButton);

		GtnUIFrameworkComponentConfig closeButton = new GtnUIFrameworkComponentConfig();
		closeButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButton.setComponentId("publicViewLookupCloseButton");
		closeButton.setComponentName("CLOSE");
		closeButton.setParentComponentId("publicViewLookupControlPopUpButtonLayout");
		closeButton.setAddToParent(true);
		componentList.add(closeButton);

	}

}
