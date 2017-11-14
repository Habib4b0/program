package com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.product;

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
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingTableConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingProductGroupTabLookup {

	public GtnUIFrameworkViewConfig getProductGroupTabLookUpView() {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Product Group Tab LookUp");
		view.setViewId("prodGroupTabLookupView");
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

		GtnUIFrameworkComponentConfig prodGroupLookupRootLayout = new GtnUIFrameworkComponentConfig();
		prodGroupLookupRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodGroupLookupRootLayout.setComponentId("prodGroupLookupRootLayout");
		prodGroupLookupRootLayout.setAddToParent(Boolean.FALSE);
		prodGroupLookupRootLayout.setSpacing(Boolean.TRUE);
		prodGroupLookupRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		prodGroupLookupRootLayout.setGtnLayoutConfig(conf);
		componentList.add(prodGroupLookupRootLayout);
		getRootComponentForPopUp(componentList,prodGroupLookupRootLayout.getComponentId());

	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig prodGroupVLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConfig = new GtnUIFrameworkLayoutConfig();
		layoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		prodGroupVLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodGroupVLayout.setGtnLayoutConfig(layoutConfig);
		prodGroupVLayout.setAddToParent(Boolean.TRUE);
		prodGroupVLayout.setComponentId("prodGroupLookupRootVerticalLayout");
		prodGroupVLayout.setParentComponentId(parentComponentId);
		prodGroupVLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodGroupVLayout.setSpacing(Boolean.TRUE);
		prodGroupVLayout.setMargin(Boolean.TRUE);
		componentList.add(prodGroupVLayout);

		searchCriteriaPanel(componentList,prodGroupVLayout.getComponentId());
		searchAndResetButtonLayout(componentList,prodGroupVLayout.getComponentId());
		resultsPanel(componentList,prodGroupVLayout.getComponentId());
		addControlPopUpButtonLayout(componentList,prodGroupVLayout.getComponentId());

	}

	private void searchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig prodGroupLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		prodGroupLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		prodGroupLookupSearchCriteriaPanel.setComponentId("prodGroupLookupSearchCriteriaPanel");
		prodGroupLookupSearchCriteriaPanel.setComponentName("Search Criteria");
		prodGroupLookupSearchCriteriaPanel.setParentComponentId(parentComponentId);
		prodGroupLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodGroupLookupSearchCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(prodGroupLookupSearchCriteriaPanel);
		searchCriteriaLayout(componentList,prodGroupLookupSearchCriteriaPanel.getComponentId());
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig prodGroupLookupSearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		prodGroupLookupSearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodGroupLookupSearchCriteriaLayout.setComponentId("prodGroupLookupSearchCriteriaLayout");
		prodGroupLookupSearchCriteriaLayout.setComponentName("Search Criteria");
		prodGroupLookupSearchCriteriaLayout.setAddToParent(Boolean.TRUE);
		prodGroupLookupSearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodGroupLookupSearchCriteriaLayout.setParentComponentId(parentComponentId);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		prodGroupLookupSearchCriteriaLayout.setGtnLayoutConfig(conf);
		componentList.add(prodGroupLookupSearchCriteriaLayout);
		addHierarchyTypeOptionGroup(componentList,prodGroupLookupSearchCriteriaLayout.getComponentId());
		addHierarchyNameTextBox(componentList,prodGroupLookupSearchCriteriaLayout.getComponentId());
	}

	private void addHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyTypeOptionGroup.setComponentId("prodGroupLookupProductGroupName");
		addHierarchyTypeOptionGroup.setComponentName("Product Group Name:");
		addHierarchyTypeOptionGroup.setAddToParent(Boolean.TRUE);
		addHierarchyTypeOptionGroup.setComponentWsFieldId("prodGroupLookupProductGroupName");
		addHierarchyTypeOptionGroup.setParentComponentId(parentComponentId);
		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyNameTextBox.setComponentId("prodGroupLookupProductGroupNo");
		addHierarchyNameTextBox.setComponentName("Product Group No:");
		addHierarchyNameTextBox.setAddToParent(Boolean.TRUE);
		addHierarchyNameTextBox.setComponentWsFieldId("prodGroupLookupProductGroupNo");
		addHierarchyNameTextBox.setParentComponentId(parentComponentId);

		componentList.add(addHierarchyNameTextBox);
	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig prodGroupLookupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		prodGroupLookupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodGroupLookupSearchAndResetLayout.setAddToParent(Boolean.TRUE);
		prodGroupLookupSearchAndResetLayout.setParentComponentId(parentComponentId);
		prodGroupLookupSearchAndResetLayout.setComponentId("prodGroupLookupSearchAndResetButtonLayout");
		prodGroupLookupSearchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		prodGroupLookupSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(prodGroupLookupSearchAndResetLayout);

		GtnUIFrameworkComponentConfig searchButton = new GtnUIFrameworkComponentConfig();
		searchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButton.setComponentId("prodGroupLookupProductGroupSearchtCriteriaButton");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId(prodGroupLookupSearchAndResetLayout.getComponentId());
		searchButton.setAddToParent(Boolean.TRUE);
		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("prodGroupLookupProductGroupResetCriteriaButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(prodGroupLookupSearchAndResetLayout.getComponentId());
		resetButton.setAddToParent(Boolean.TRUE);
		componentList.add(resetButton);
	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig prodGroupLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		prodGroupLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		prodGroupLookupSearchCriteriaPanel.setComponentId("prodGroupLookupResultsPanel");
		prodGroupLookupSearchCriteriaPanel.setComponentName("Results");
		prodGroupLookupSearchCriteriaPanel.setParentComponentId(parentComponentId);
		prodGroupLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodGroupLookupSearchCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(prodGroupLookupSearchCriteriaPanel);
		resultLayout(componentList,prodGroupLookupSearchCriteriaPanel.getComponentId());

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkLayoutConfig prodGroupLookupResultLayout = new GtnUIFrameworkLayoutConfig();
		prodGroupLookupResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig prodGroupLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		prodGroupLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodGroupLookupSearchCriteriaPanel.setComponentId("prodGroupLookupResultLayout");

		prodGroupLookupSearchCriteriaPanel.setGtnLayoutConfig(prodGroupLookupResultLayout);
		prodGroupLookupSearchCriteriaPanel.setParentComponentId(parentComponentId);
		prodGroupLookupSearchCriteriaPanel.setAddToParent(Boolean.TRUE);
		prodGroupLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodGroupLookupSearchCriteriaPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(prodGroupLookupSearchCriteriaPanel);
		addPagedTableComponent(componentList,prodGroupLookupSearchCriteriaPanel.getComponentId());
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId("prodGroupLookupSearchResultTable");
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setParentComponentId(parentComponentId);
		searchResultConfig.setAddToParent(Boolean.TRUE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);
		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(Boolean.FALSE);
		searchResults.setFilterBar(Boolean.TRUE);
		searchResults.setSelectable(Boolean.FALSE);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);
		searchResults.setSelectable(Boolean.TRUE);
		searchResults.setTableColumnDataType(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PRODUCT_GROUP_LOOKUP_TABLE_COLUMNS_DATA_TYPE);
		searchResults.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PRODUCT_GROUP_LOOKUP_TABLE_HEADER);

		searchResults.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PRODUCT_GROUP_LOOKUP_TABLE_COLUMNS);

		searchResults.setModuleName("ProductGroupLookUp");
		searchResults.setQueryName("searchQuery");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {

		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(Boolean.TRUE);
		controlPopUpLayout.setParentComponentId(parentComponentId);
		controlPopUpLayout.setComponentId("prodGroupLookupControlPopUpButtonLayout");
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton.setComponentId("prodGroupLookupSelectButton");
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId(controlPopUpLayout.getComponentId());
		selectButton.setAddToParent(Boolean.TRUE);
		componentList.add(selectButton);

		GtnUIFrameworkComponentConfig cancelButton = new GtnUIFrameworkComponentConfig();
		cancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cancelButton.setComponentId("prodGroupLookupCancelButton");
		cancelButton.setComponentName("CANCEL");
		cancelButton.setParentComponentId(controlPopUpLayout.getComponentId());
		cancelButton.setAddToParent(Boolean.TRUE);
		componentList.add(cancelButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("prodGroupLookupResetControlButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(controlPopUpLayout.getComponentId());
		resetButton.setAddToParent(Boolean.TRUE);
		componentList.add(resetButton);
	}
}
