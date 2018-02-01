package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsTableConstants;
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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

public class GtnFrameworkForecastReturnProductHierarchyLookUp {

	public GtnUIFrameworkViewConfig getProdHierarchyLookUpView(String namespace) {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Product Hierarchy LookUp");
		view.setViewId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_HIERARCHY_LOOKUP_VIEW);
		view.setDefaultView(false);
		addComponentList(view, namespace);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList, namespace);

	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig vLayout = new GtnUIFrameworkComponentConfig();
		vLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		vLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "rootLayout");
		vLayout.setAddToParent(false);
		vLayout.setSpacing(Boolean.TRUE);
		vLayout.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		vLayout.setGtnLayoutConfig(conf);
		componentList.add(vLayout);
		getRootComponentForPopUp(componentList, namespace);
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(conf);
		gtnLayout.setAddToParent(true);

		gtnLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		gtnLayout.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "rootLayout");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setSpacing(Boolean.TRUE);
		gtnLayout.setMargin(Boolean.TRUE);
		componentList.add(gtnLayout);
		searchCriteriaPanel(componentList, namespace);
		searchAndResetButtonLayout(componentList, namespace);
		resultsPanel(componentList, namespace);
		addControlPopUpButtonLayout(componentList, namespace);
	}

	private void searchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "searchCriteriaPanel");
		searchCriteriaPanel.setComponentName("Search Criteria");
		searchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
                searchCriteriaPanel.setMargin(Boolean.TRUE);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		componentList.add(searchCriteriaPanel);
		searchCriteriaLayout(componentList, namespace);
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig searchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		searchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_CRITERIA);
		searchCriteriaLayout.setComponentName("Search Criteria");
		searchCriteriaLayout.setAddToParent(true);
		searchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaLayout.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "searchCriteriaPanel");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		searchCriteriaLayout.setGtnLayoutConfig(conf);
		searchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		searchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(searchCriteriaLayout);
		addHierarchyTypeOptionGroup(componentList, namespace);
		addHierarchyNameTextBox(componentList, namespace);
	}

	private void addHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		addHierarchyTypeOptionGroup.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		addHierarchyTypeOptionGroup.setComponentName("Hierarchy Type");
		addHierarchyTypeOptionGroup.setAddToParent(true);
		addHierarchyTypeOptionGroup.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_CRITERIA);
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList("Primary", "Secondary"));
		comboConfig.setValuesFromService(Boolean.FALSE);
		addHierarchyTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		addHierarchyTypeOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyNameTextBox.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		addHierarchyNameTextBox.setComponentName("Hierarchy Name");
		addHierarchyNameTextBox.setAddToParent(true);
		addHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		addHierarchyNameTextBox.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_CRITERIA);
		componentList.add(addHierarchyNameTextBox);
	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig searchAndResetLayout = new GtnUIFrameworkComponentConfig();
		searchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchAndResetLayout.setAddToParent(true);
		searchAndResetLayout.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		searchAndResetLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		searchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		searchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(searchAndResetLayout);

		GtnUIFrameworkComponentConfig searchButton = new GtnUIFrameworkComponentConfig();
		searchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "prodHierarchySearch");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		searchButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.HIERARCHY_NAME));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig searchAlertAction = new GtnUIFrameWorkActionConfig();
		Object msg = "There are no Hierarchies that match the search criteria";
		searchAlertAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchAlertAction.setActionParameterList(
				Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_SEARCH_ALERT_ACTION,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE,
						msg, "No result Found", namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SELECT_BUTTON));

		actionConfigList.add(searchAlertAction);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SELECT_BUTTON);
		actionConfigList.add(enableAction);

		searchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productHierarchyResetCriteriaButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		resetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");
		params.add(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.HIERARCHY_NAME));
		params.add(Arrays.asList(new Object[] { "Primary", "" }));

		resetActionConfig.setActionParameterList(params);
		resetActionConfigList.add(resetActionConfig);
		resetButton.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

		componentList.add(resetButton);
	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultsPanel");
		searchCriteriaPanel.setComponentName("Results");
		searchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(true);
		componentList.add(searchCriteriaPanel);
		resultLayout(componentList, namespace);

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig reasultsPanel = new GtnUIFrameworkComponentConfig();
		reasultsPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reasultsPanel.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultLayout");

		reasultsPanel.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultsPanel");
		reasultsPanel.setAddToParent(true);
		reasultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reasultsPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reasultsPanel.setGtnLayoutConfig(conf);
		componentList.add(reasultsPanel);
		addPagedTableComponent(componentList, namespace);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultLayout");
		searchResultConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		searchResultConfig.setComponentWidth("100%");
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);
		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(false);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);
		searchResults.setSelectable(true);
                searchResults.setSinkItemPerPageWithPageLength(false);
		searchResults.setTableColumnDataType(
				GtnForecastReturnsTableConstants.getGtnReturnsForecastHeirarchyTableColumnsDataType());
		searchResults
				.setTableVisibleHeader(GtnForecastReturnsTableConstants.getGtnReturnsForecastHeirarchyTableHeader());
		searchResults
				.setTableColumnMappingId(GtnForecastReturnsTableConstants.getGtnReturnsForecastHeirarchyTableColumns());
		searchResults.setCountUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_HIERARCHY_SEARCH_SERVICE);
		searchResults.setResultSetUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_HIERARCHY_SEARCH_SERVICE);
		searchResults.setModuleName("HierarchyLookUp");
		searchResults.setQueryName("searchQuery");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(true);
		controlPopUpLayout.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		controlPopUpLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SELECT_BUTTON);
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		selectButton.setAddToParent(true);
		selectButton.addDependentComponent(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "relationShipCombobox");

		selectButton.addDependentComponent(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "forecastLevel");

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		actionParameter.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productHierarchy");
		actionParameter.add(Arrays.asList("hierName"));
		actionParameter
				.add(Arrays.asList(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productHierarchy"));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);
		selectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(selectButton);

		GtnUIFrameWorkActionConfig closeOnSelectAction = new GtnUIFrameWorkActionConfig();
		closeOnSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeOnSelectAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_HIERARCHY_LOOKUP_VIEW);
		actionConfigList.add(closeOnSelectAction);

		GtnUIFrameworkComponentConfig cancelButton = new GtnUIFrameworkComponentConfig();
		cancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cancelButton.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productHierarchyCancelButton");
		cancelButton.setComponentName("CANCEL");
		cancelButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		cancelButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigCloseList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_HIERARCHY_LOOKUP_VIEW);
		actionConfigCloseList.add(closeAction);
                cancelButton.setGtnUIFrameWorkActionConfigList(actionConfigCloseList);
		selectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(cancelButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productHierarchyResetControlButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		resetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> confList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the table ?");

		params.add(Arrays.asList(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE));
		params.add(Arrays.asList(new Object[] { "" }));

		resetActionConfig.setActionParameterList(params);
		confList.add(resetActionConfig);
		resetButton.setGtnUIFrameWorkActionConfigList(confList);

		componentList.add(resetButton);
	}
}
