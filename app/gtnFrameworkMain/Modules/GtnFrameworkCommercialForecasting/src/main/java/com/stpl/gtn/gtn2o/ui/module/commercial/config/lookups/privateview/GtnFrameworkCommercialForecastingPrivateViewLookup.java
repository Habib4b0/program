package com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.privateview;

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

public class GtnFrameworkCommercialForecastingPrivateViewLookup {

	public GtnUIFrameworkViewConfig getPrivateViewLookUpView() {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Private Views");
		view.setViewId("privateViewSearchLookupView");
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

		GtnUIFrameworkComponentConfig rootLayoutComponentConfig = new GtnUIFrameworkComponentConfig();
		rootLayoutComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		rootLayoutComponentConfig.setComponentId("privateViewLookupRootLayout");
		rootLayoutComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rootLayoutComponentConfig.setAddToParent(Boolean.FALSE);
		rootLayoutComponentConfig.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig rootLayoutConfig = new GtnUIFrameworkLayoutConfig();
		rootLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutComponentConfig.setGtnLayoutConfig(rootLayoutConfig);
		componentList.add(rootLayoutComponentConfig);
		getRootComponentForPopUp(componentList);
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(conf);
		gtnLayout.setAddToParent(Boolean.TRUE);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setComponentId("privateViewLookupRootVerticalLayout");
		gtnLayout.setParentComponentId("privateViewLookupRootLayout");
		gtnLayout.setSpacing(Boolean.TRUE);
		gtnLayout.setMargin(Boolean.TRUE);
		componentList.add(gtnLayout);
		searchCriteriaPanel(componentList);
		searchAndResetButtonLayout(componentList);
		resultsPanel(componentList);
		addControlPopUpButtonLayout(componentList);
	}

	private void searchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId("privateViewLookupSearchCriteriaPanel");
		searchCriteriaPanel.setComponentName("Search Criteria");
		searchCriteriaPanel.setParentComponentId("privateViewLookupRootVerticalLayout");
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(searchCriteriaPanel);
		searchCriteriaLayout(componentList);

	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig searchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		searchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaLayout.setComponentId("privateViewLookupSearchCriteriaLayout");
		searchCriteriaLayout.setComponentName("Search Criteria");
		searchCriteriaLayout.setAddToParent(Boolean.TRUE);
		searchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaLayout.setParentComponentId("privateViewLookupSearchCriteriaPanel");
		searchCriteriaLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(searchCriteriaLayout);
		addHierarchyNameTextBox(componentList);

	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnFrameworkCommercialForecastingLayoutsConfig layoutsConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

		GtnUIFrameworkComponentConfig horizontalViewNameLayout = layoutsConfig.getHorizontalLayoutConfig(
				"privateViewLookupHorizontalViewNameLayout", "privateViewLookupSearchCriteriaLayout");
		componentList.add(horizontalViewNameLayout);

		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyNameTextBox.setComponentId("privateViewLookupViewName");
		addHierarchyNameTextBox.setComponentName("View Name");
		addHierarchyNameTextBox.setAddToParent(Boolean.TRUE);
		addHierarchyNameTextBox.setParentComponentId(horizontalViewNameLayout.getComponentId());
		addHierarchyNameTextBox.setComponentWsFieldId("privateViewLookupViewName");

		componentList.add(addHierarchyNameTextBox);

	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchAndResetLayout = new GtnUIFrameworkComponentConfig();
		searchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchAndResetLayout.setAddToParent(Boolean.TRUE);
		searchAndResetLayout.setParentComponentId("privateViewLookupRootVerticalLayout");
		searchAndResetLayout.setComponentId("privateViewLookupSearchAndResetButtonLayout");
		searchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		searchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(searchAndResetLayout);

		GtnUIFrameworkComponentConfig searchButton = new GtnUIFrameworkComponentConfig();
		searchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButton.setComponentId("privateViewLookupSearchCriteriaButton");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId("privateViewLookupSearchAndResetButtonLayout");
		searchButton.setAddToParent(Boolean.TRUE);
		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("privateViewLookupResetCriteriaButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId("privateViewLookupSearchAndResetButtonLayout");
		resetButton.setAddToParent(Boolean.TRUE);
		componentList.add(resetButton);

	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId("privateViewLookupResultsPanel");
		searchCriteriaPanel.setComponentName("Results");
		searchCriteriaPanel.setParentComponentId("privateViewLookupRootVerticalLayout");
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(searchCriteriaPanel);
		resultLayout(componentList);

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaPanel.setComponentId("privateViewLookupResultLayout");

		searchCriteriaPanel.setGtnLayoutConfig(conf);
		searchCriteriaPanel.setParentComponentId("privateViewLookupResultsPanel");
		searchCriteriaPanel.setAddToParent(Boolean.TRUE);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(searchCriteriaPanel);
		addPagedTableComponent(componentList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId("privateViewLookupSearchResultTable");
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setParentComponentId("privateViewLookupResultLayout");
		searchResultConfig.setAddToParent(Boolean.TRUE);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);
		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(Boolean.FALSE);
		searchResults.setFilterBar(Boolean.TRUE);
		searchResults.setSelectable(Boolean.TRUE);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);

		searchResults.setTableColumnDataType(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PRIVATE_VIEW_TABLE_COLUMNS_DATA_TYPE);
		searchResults.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PRIVATE_VIEW_TABLE_COLUMNS_HERDERS);
		searchResults.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PRIVATE_VIEW_TABLE_COLUMNS);

		searchResults.setModuleName("PrivateViewLookup");
		searchResults.setQueryName("private");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);

	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(Boolean.TRUE);
		controlPopUpLayout.setParentComponentId("privateViewLookupRootVerticalLayout");
		controlPopUpLayout.setComponentId("privateViewLookupControlPopUpButtonLayout");
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton.setComponentId("privateViewLookupSelectButton");
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId("privateViewLookupControlPopUpButtonLayout");
		selectButton.setAddToParent(Boolean.TRUE);
		componentList.add(selectButton);

		GtnUIFrameworkComponentConfig closeButton = new GtnUIFrameworkComponentConfig();
		closeButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButton.setComponentId("privateViewLookupCloseButton");
		closeButton.setComponentName("CLOSE");
		closeButton.setParentComponentId("privateViewLookupControlPopUpButtonLayout");
		closeButton.setAddToParent(Boolean.TRUE);
		componentList.add(closeButton);

	}
}
