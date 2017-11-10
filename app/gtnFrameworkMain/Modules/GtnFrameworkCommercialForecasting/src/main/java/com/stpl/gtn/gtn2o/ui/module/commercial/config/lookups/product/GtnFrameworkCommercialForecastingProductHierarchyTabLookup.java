package com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.product;

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
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingProductHierarchyTabLookup {

	public GtnUIFrameworkViewConfig getProductHierarchyLookUpView() {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Product Hierarchy Tab LookUp");
		view.setViewId("prodHierarchyTabLookupView");
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
		rootLayoutComponentConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		rootLayoutComponentConfig.setComponentId("prodHierarchyTabLookupRootLayout");
		rootLayoutComponentConfig.setAddToParent(false);
		rootLayoutComponentConfig.setSpacing(Boolean.TRUE);
		rootLayoutComponentConfig.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutComponentConfig.setGtnLayoutConfig(conf);
		componentList.add(rootLayoutComponentConfig);
		getRootComponentForPopUp(componentList, rootLayoutComponentConfig.getComponentId());
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig prodHirerachyVLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConfig = new GtnUIFrameworkLayoutConfig();
		layoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		prodHirerachyVLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodHirerachyVLayout.setGtnLayoutConfig(layoutConfig);
		prodHirerachyVLayout.setAddToParent(true);

		prodHirerachyVLayout.setComponentId("prodHierarchyTabLookupRootVerticalLayout");
		prodHirerachyVLayout.setParentComponentId(parentComponentId);
		prodHirerachyVLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodHirerachyVLayout.setSpacing(Boolean.TRUE);
		prodHirerachyVLayout.setMargin(Boolean.TRUE);
		componentList.add(prodHirerachyVLayout);

		searchCriteriaPanel(componentList, prodHirerachyVLayout.getComponentId());
		searchAndResetButtonLayout(componentList, prodHirerachyVLayout.getComponentId());
		resultsPanel(componentList, prodHirerachyVLayout.getComponentId());
		addControlPopUpButtonLayout(componentList, prodHirerachyVLayout.getComponentId());
	}

	private void searchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig prodHierarchyTabLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		prodHierarchyTabLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		prodHierarchyTabLookupSearchCriteriaPanel.setComponentId("prodHierarchyTabLookupSearchCriteriaPanel");
		prodHierarchyTabLookupSearchCriteriaPanel.setComponentName("Search Criteria");
		prodHierarchyTabLookupSearchCriteriaPanel.setParentComponentId(parentComponentId);
		prodHierarchyTabLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodHierarchyTabLookupSearchCriteriaPanel.setAddToParent(true);
		componentList.add(prodHierarchyTabLookupSearchCriteriaPanel);
		searchCriteriaLayout(componentList, prodHierarchyTabLookupSearchCriteriaPanel.getComponentId());
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig prodHierarchyTabLookupSearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		prodHierarchyTabLookupSearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodHierarchyTabLookupSearchCriteriaLayout.setComponentId("prodHierarchyTabLookupSearchCriteriaLayout");
		prodHierarchyTabLookupSearchCriteriaLayout.setComponentName("Search Criteria");
		prodHierarchyTabLookupSearchCriteriaLayout.setAddToParent(true);
		prodHierarchyTabLookupSearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodHierarchyTabLookupSearchCriteriaLayout.setParentComponentId(parentComponentId);

		GtnUIFrameworkLayoutConfig layoutConfig = new GtnUIFrameworkLayoutConfig();
		prodHierarchyTabLookupSearchCriteriaLayout.setGtnLayoutConfig(layoutConfig);
		componentList.add(prodHierarchyTabLookupSearchCriteriaLayout);
		addHierarchyTypeOptionGroup(componentList, prodHierarchyTabLookupSearchCriteriaLayout.getComponentId());
		addHierarchyNameTextBox(componentList, prodHierarchyTabLookupSearchCriteriaLayout.getComponentId());

	}

	private void addHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		addHierarchyTypeOptionGroup.setComponentId("prodHierarchyTabLookupHierarchyType");
		addHierarchyTypeOptionGroup.setComponentName("Hierarchy Type");
		addHierarchyTypeOptionGroup.setAddToParent(true);
		addHierarchyTypeOptionGroup.setParentComponentId(parentComponentId);

		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(new String[] { "Primary", "Secondary" }));
		comboConfig.setValuesFromService(Boolean.FALSE);
		addHierarchyTypeOptionGroup.setComponentWsFieldId("prodHierarchyTabLookupHierarchyType");
		addHierarchyTypeOptionGroup
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(addHierarchyTypeOptionGroup);

	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyNameTextBox.setComponentId("prodHierarchyTabLookupHierarchyName");
		addHierarchyNameTextBox.setComponentName("Hierarchy Name");
		addHierarchyNameTextBox.setAddToParent(true);
		addHierarchyNameTextBox.setComponentWsFieldId("prodHierarchyTabLookupHierarchyName");
		addHierarchyNameTextBox.setParentComponentId(parentComponentId);
		componentList.add(addHierarchyNameTextBox);

	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig searchAndResetLayout = new GtnUIFrameworkComponentConfig();
		searchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchAndResetLayout.setAddToParent(true);
		searchAndResetLayout.setParentComponentId(parentComponentId);
		searchAndResetLayout.setComponentId("prodHierarchyTabLookupSearchAndResetButtonLayout");
		searchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		searchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(searchAndResetLayout);

		GtnUIFrameworkComponentConfig searchButton = new GtnUIFrameworkComponentConfig();
		searchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButton.setComponentId("prodHierarchyTabLookupSearchtCriteriaButton");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId(searchAndResetLayout.getComponentId());
		searchButton.setAddToParent(true);
		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("prodHierarchyTabLookupResetCriteriaButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(searchAndResetLayout.getComponentId());
		resetButton.setAddToParent(true);
		componentList.add(resetButton);
	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId("prodHierarchyTabLookupResultsPanel");
		searchCriteriaPanel.setComponentName("Results");
		searchCriteriaPanel.setParentComponentId(parentComponentId);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		componentList.add(searchCriteriaPanel);
		resultLayout(componentList, searchCriteriaPanel.getComponentId());

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkLayoutConfig resultLayout = new GtnUIFrameworkLayoutConfig();
		resultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig reasultsPanel = new GtnUIFrameworkComponentConfig();
		reasultsPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reasultsPanel.setComponentId("prodHierarchyTabLookupResultLayout");
		reasultsPanel.setParentComponentId(parentComponentId);
		reasultsPanel.setAddToParent(true);
		reasultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reasultsPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reasultsPanel.setGtnLayoutConfig(resultLayout);
		componentList.add(reasultsPanel);
		addPagedTableComponent(componentList, reasultsPanel.getComponentId());

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig searchResultComponentConfig = new GtnUIFrameworkComponentConfig();
		searchResultComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultComponentConfig.setComponentId("prodHierarchyTabLookupSearchResultTable");
		searchResultComponentConfig.setComponentName("Results");
		searchResultComponentConfig.setParentComponentId(parentComponentId);
		searchResultComponentConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultComponentConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultComponentConfig);
		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(false);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);
		searchResults.setSelectable(true);
		searchResults.setTableColumnDataType(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PRODUCT_HIERARCHY_LOOKUP_TABLE_COLUMNS_DATA_TYPE);
		searchResults.setTableVisibleHeader(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PRODUCT_HIERARCHY_LOOKUP_TABLE_HEADER);
		searchResults.setTableColumnMappingId(
				GtnFrameworkCommercialForecastingTableConstants.GTN_COMMERCIAL_FORECASTING_PRODUCT_HIERARCHY_LOOKUP_TABLE_COLUMNS);

		searchResults.setModuleName("ProductHierarchyTabLookUp");
		searchResults.setQueryName("searchQuery");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultComponentConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(true);
		controlPopUpLayout.setParentComponentId(parentComponentId);
		controlPopUpLayout.setComponentId("prodHierarchyTabLookupControlPopUpButtonLayout");
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton.setComponentId("productHierarchyTabLookupSelectButton");
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId(controlPopUpLayout.getComponentId());
		selectButton.setAddToParent(true);
		componentList.add(selectButton);

		GtnUIFrameworkComponentConfig cancelButton = new GtnUIFrameworkComponentConfig();
		cancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cancelButton.setComponentId("productHierarchyTabLookupCancelButton");
		cancelButton.setComponentName("CANCEL");
		cancelButton.setParentComponentId(controlPopUpLayout.getComponentId());
		cancelButton.setAddToParent(true);
		componentList.add(cancelButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("productHierarchyTabLookupResetControlButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(controlPopUpLayout.getComponentId());
		resetButton.setAddToParent(true);
		componentList.add(resetButton);
	}
}