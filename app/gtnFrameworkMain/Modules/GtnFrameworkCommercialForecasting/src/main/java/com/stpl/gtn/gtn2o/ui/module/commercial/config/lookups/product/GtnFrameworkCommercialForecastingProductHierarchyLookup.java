package com.stpl.gtn.gtn2o.ui.module.commercial.config.lookups.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingTableConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingProductHierarchyLookup {

	public GtnUIFrameworkViewConfig getProductHierarchyLookUpView() {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Product Hierarchy LookUp");
		view.setViewId("prodHierarchyLookupView");
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
		rootLayoutComponentConfig.setComponentId("prodHierarchyLookupRootLayout");
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

		prodHirerachyVLayout.setComponentId("prodHierarchyLookupRootVerticalLayout");
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

		GtnUIFrameworkComponentConfig prodHierarchyLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		prodHierarchyLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		prodHierarchyLookupSearchCriteriaPanel.setComponentId("prodHierarchyLookupSearchCriteriaPanel");
		prodHierarchyLookupSearchCriteriaPanel.setComponentName("Search Criteria");
		prodHierarchyLookupSearchCriteriaPanel.setParentComponentId(parentComponentId);
		prodHierarchyLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodHierarchyLookupSearchCriteriaPanel.setAddToParent(true);
		componentList.add(prodHierarchyLookupSearchCriteriaPanel);
		searchCriteriaLayout(componentList, prodHierarchyLookupSearchCriteriaPanel.getComponentId());
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig prodHierarchyLookupSearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		prodHierarchyLookupSearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		prodHierarchyLookupSearchCriteriaLayout.setComponentId("prodHierarchyLookupSearchCriteriaLayout");
		prodHierarchyLookupSearchCriteriaLayout.setComponentName("Search Criteria");
		prodHierarchyLookupSearchCriteriaLayout.setAddToParent(true);
		prodHierarchyLookupSearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		prodHierarchyLookupSearchCriteriaLayout.setParentComponentId(parentComponentId);

		GtnUIFrameworkLayoutConfig layoutConfig = new GtnUIFrameworkLayoutConfig();
		prodHierarchyLookupSearchCriteriaLayout.setGtnLayoutConfig(layoutConfig);
		componentList.add(prodHierarchyLookupSearchCriteriaLayout);
		addHierarchyTypeOptionGroup(componentList, prodHierarchyLookupSearchCriteriaLayout.getComponentId());
		addHierarchyNameTextBox(componentList, prodHierarchyLookupSearchCriteriaLayout.getComponentId());

	}

	private void addHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		addHierarchyTypeOptionGroup.setComponentId("prodHierarchyLookupHierarchyType");
		addHierarchyTypeOptionGroup.setComponentName("Hierarchy Type");
		addHierarchyTypeOptionGroup.setAddToParent(true);
		addHierarchyTypeOptionGroup.setParentComponentId(parentComponentId);

		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(new String[] { "Primary", "Secondary" }));
		comboConfig.setValuesFromService(Boolean.FALSE);
		addHierarchyTypeOptionGroup.setComponentWsFieldId("prodHierarchyLookupHierarchyType");
		addHierarchyTypeOptionGroup
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(addHierarchyTypeOptionGroup);

	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyNameTextBox.setComponentId("prodHierarchyLookupHierarchyName");
		addHierarchyNameTextBox.setComponentName("Hierarchy Name");
		addHierarchyNameTextBox.setAddToParent(true);
		addHierarchyNameTextBox.setComponentWsFieldId("prodHierarchyLookupHierarchyName");
		addHierarchyNameTextBox.setParentComponentId(parentComponentId);

		componentList.add(addHierarchyNameTextBox);

	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig searchAndResetLayout = new GtnUIFrameworkComponentConfig();
		searchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchAndResetLayout.setAddToParent(true);
		searchAndResetLayout.setParentComponentId(parentComponentId);
		searchAndResetLayout.setComponentId("prodHierarchyLookupSearchAndResetButtonLayout");
		searchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		searchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(searchAndResetLayout);

		GtnUIFrameworkComponentConfig searchButton = new GtnUIFrameworkComponentConfig();
		searchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButton.setComponentId("prodHierarchyLookupSearchtCriteriaButton");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId(searchAndResetLayout.getComponentId());
		searchButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig
				.setActionParameterList(Arrays.asList(new Object[] { "prodHierarchyLookupSearchResultTable" }));
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList(new String[] { "prodHierarchyLookupHierarchyType", "prodHierarchyLookupHierarchyName" }));
		actionConfigList.add(loadDataTableActionConfig);

		searchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("prodHierarchyLookupResetCriteriaButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(searchAndResetLayout.getComponentId());
		resetButton.setAddToParent(true);
		componentList.add(resetButton);
	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId("prodHierarchyLookupResultsPanel");
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
		reasultsPanel.setComponentId("prodHierarchyLookupResultLayout");

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
		searchResultComponentConfig.setComponentId("prodHierarchyLookupSearchResultTable");
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

		searchResults.setCountUrl("/gtnCommercialForecasting/lookUp/getProductHierarchyLookUp");
		searchResults.setResultSetUrl("/gtnCommercialForecasting/lookUp/getProductHierarchyLookUp");

		searchResults.setModuleName("ProductHierarchyLookUp");
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
		controlPopUpLayout.setComponentId("prodHierarchyLookupControlPopUpButtonLayout");
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton.setComponentId("productHierarchyLookupSelectButton");
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId(controlPopUpLayout.getComponentId());
		selectButton.setAddToParent(true);
		
		selectButton.addDependentComponent("prodSelectionRelationShipCombobox");
		selectButton.addDependentComponent("prodSelectionForecastLevel");
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);

		selectAction.setActionParameterList(Arrays.asList(new Object[] { "prodHierarchyLookupSearchResultTable",
				"productHierarchy", (Arrays.asList(new String[] { "prodHierarchyLookupHierName" })),
				Arrays.asList(new String[] { "productHierarchy" }) }));
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeOnSelectAction = new GtnUIFrameWorkActionConfig();
		closeOnSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeOnSelectAction.setActionParameterList(Arrays.asList(new Object[] { "prodHierarchyLookupView" }));
		actionConfigList.add(closeOnSelectAction);

		selectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(selectButton);

		GtnUIFrameworkComponentConfig cancelButton = new GtnUIFrameworkComponentConfig();
		cancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cancelButton.setComponentId("productHierarchyLookupCancelButton");
		cancelButton.setComponentName("CANCEL");
		cancelButton.setParentComponentId(controlPopUpLayout.getComponentId());
		cancelButton.setAddToParent(true);
		componentList.add(cancelButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId("productHierarchyLookupResetControlButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(controlPopUpLayout.getComponentId());
		resetButton.setAddToParent(true);
		componentList.add(resetButton);
	}
}
