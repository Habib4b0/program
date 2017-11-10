package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkForecastReturnLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsTableConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

public class GtnFrameworkForecastReturnPrivateViewSearchLookUp {

	private GtnFrameworkForecastReturnLayoutsConfig layoutsConfig = new GtnFrameworkForecastReturnLayoutsConfig();	


	public GtnUIFrameworkViewConfig getPrivateViewLookUpView(String namespace) {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Private Views");
		view.setViewId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_SEARCH_LOOKUP_VIEW);
		view.setDefaultView(Boolean.FALSE);
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
		vLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		vLayout.setAddToParent(Boolean.FALSE);
		vLayout.setSpacing(Boolean.TRUE);
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
		gtnLayout.setAddToParent(Boolean.TRUE);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		gtnLayout.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "rootLayout");
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
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(searchCriteriaPanel);
		searchCriteriaLayout(componentList, namespace);
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig searchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		searchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaLayout
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "searchCriteria");
		searchCriteriaLayout.setComponentName("Search Criteria");
		searchCriteriaLayout.setAddToParent(Boolean.TRUE);
		searchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaLayout.setParentComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "searchCriteriaPanel");
		searchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		searchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		searchCriteriaLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(searchCriteriaLayout);
		addHierarchyNameTextBox(componentList, namespace);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig horizontalViewNameLayout = layoutsConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "horizontalViewNameLayout",
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "searchCriteria");
		componentList.add(horizontalViewNameLayout);

		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyNameTextBox.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);
		addHierarchyNameTextBox.setComponentName("View Name");
		addHierarchyNameTextBox.setAddToParent(Boolean.TRUE);
		addHierarchyNameTextBox.setParentComponentId(horizontalViewNameLayout.getComponentId());
		addHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);

		GtnUIFrameworkValidationConfig valConfigForViewName = new GtnUIFrameworkValidationConfig();
		valConfigForViewName.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addHierarchyNameTextBox.setGtnUIFrameworkValidationConfig(valConfigForViewName);

		componentList.add(addHierarchyNameTextBox);

	}

	private void searchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig searchAndResetLayout = new GtnUIFrameworkComponentConfig();
		searchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchAndResetLayout.setAddToParent(Boolean.TRUE);
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
		searchButton
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewSearchButton");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		searchButton.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig resetTableActionConfig = new GtnUIFrameWorkActionConfig();
		resetTableActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> reset = new HashMap<>();
		List<Object> searchResetParams = new ArrayList<>();
		reset.put(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE, null);
		searchResetParams.add(reset);
		resetTableActionConfig.setActionParameterList(searchResetParams);
		actionConfigList.add(resetTableActionConfig);

		GtnUIFrameWorkActionConfig searchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		searchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		searchValidationActionConfig.setFieldValues(Arrays.asList(namespace
				+ GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME));

		GtnUIFrameWorkActionConfig searchAlertActionConfig = new GtnUIFrameWorkActionConfig();
		searchAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> searchAlertParams = new ArrayList<>();
		searchAlertParams.add("No Search Criteria ");
		searchAlertParams.add("No search criteria were found. Please enter search criteria and try again.");

		searchAlertActionConfig.setActionParameterList(searchAlertParams);
		searchValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(searchAlertActionConfig)));
		actionConfigList.add(searchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		actionConfigList.add(notificationActionConfig);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableAction.addActionParameter(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewSelectButton");
		actionConfigList.add(enableAction);

		searchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewResetCriteriaButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		resetButton.setAddToParent(Boolean.TRUE);
		componentList.add(resetButton);

		List<GtnUIFrameWorkActionConfig> confList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Confirm Reset");
		params.add("Are you sure you want to reset the search criteria to its default state?");

		params.add(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME));
		params.add(Arrays.asList(new Object[] { "", "" }));

		resetActionConfig.setActionParameterList(params);
		confList.add(resetActionConfig);
		resetButton.setGtnUIFrameWorkActionConfigList(confList);

	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchCriteriaPanel.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultsPanel");
		searchCriteriaPanel.setComponentName("Results");
		searchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(searchCriteriaPanel);
		resultLayout(componentList, namespace);

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig searchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		searchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaPanel.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultLayout");

		searchCriteriaPanel.setGtnLayoutConfig(conf);
		searchCriteriaPanel
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultsPanel");
		searchCriteriaPanel.setAddToParent(Boolean.TRUE);
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(searchCriteriaPanel);
		addPagedTableComponent(componentList, namespace);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultLayout");
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
				GtnForecastReturnsTableConstants.getGtnReturnsForecastPrivateViewTableColumnsDataType());
		searchResults.setTableVisibleHeader(
				GtnForecastReturnsTableConstants.getGtnReturnsForecastPrivateViewTableColumnsHeaders());
		searchResults.setTableColumnMappingId(
				GtnForecastReturnsTableConstants.getGtnReturnsForecastPrivateViewTableColumns());
		searchResults.setCountUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PRIVATE_VIEW_SEARCH_SERVICE);
		searchResults
				.setResultSetUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PRIVATE_VIEW_SEARCH_SERVICE);
		searchResults.setModuleName("returns");
		searchResults.setQueryName("private");

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		searchResults.setCustomFilterConfigMap(customFilterConfigMap);
		searchResultConfig.setGtnPagedTableConfig(searchResults);

	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(Boolean.TRUE);
		controlPopUpLayout.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		controlPopUpLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		controlPopUpLayout.setSpacing(Boolean.TRUE);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewSelectButton");
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		selectButton.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_VIEW_ACTION);
		selectAction.addActionParameter("privateViews");
		selectAction.addActionParameter(namespace);
		selectAction.setFieldValues(Arrays.asList("publicSearchResultTable",
				GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE, "dsDeleteView", "company", "businessUnit",
				"projectionName", "projectionDescription", "fromPeriod", "toPeriod", "productHierarchy",
				"relationShipCombobox", "forecastLevel", "productGroup", "productLevelComboBox", "dualListBoxComp",
				"publicViews", "privateViews", "returnsForecastMainScreenDataSelection_dataSelectionMainLayout",
				"viewName", GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME));
		actionConfigList.add(selectAction);
		selectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(selectButton);

		GtnUIFrameWorkActionConfig closeOnSelectAction = new GtnUIFrameWorkActionConfig();
		closeOnSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeOnSelectAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_SEARCH_LOOKUP_VIEW);
		actionConfigList.add(closeOnSelectAction);

		GtnUIFrameworkComponentConfig closeButton = new GtnUIFrameworkComponentConfig();
		closeButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButton
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "privateViewCloseButton");
		closeButton.setComponentName("CLOSE");
		closeButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		closeButton.setAddToParent(Boolean.TRUE);
		componentList.add(closeButton);

		List<GtnUIFrameWorkActionConfig> actionConfigCloseList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_SEARCH_LOOKUP_VIEW);
		closeButton.setGtnUIFrameWorkActionConfigList(actionConfigCloseList);
		actionConfigCloseList.add(closeAction);

	}
}
