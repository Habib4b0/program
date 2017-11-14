
package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class GtnFrameworkForecastReturnProductGroupLookUp {


	public GtnUIFrameworkViewConfig getProductGroupLookUpView(String namespace) {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Product Group LookUp");
		view.setViewId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_GROUP_LOOKUP_VIEW);
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
		vLayout.setAddToParent(Boolean.FALSE);
		vLayout.setSpacing(Boolean.TRUE);
		vLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
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
		searchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchCriteriaPanel.setAddToParent(Boolean.TRUE);
		componentList.add(searchCriteriaPanel);
		searchCriteriaLayout(componentList, namespace);
	}

	private void searchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig searchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		searchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchCriteriaLayout.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_CRITERIA);
		searchCriteriaLayout.setComponentName("Search Criteria");
		searchCriteriaLayout.setAddToParent(Boolean.TRUE);
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
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyTypeOptionGroup.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_GROUP_NAME);
		addHierarchyTypeOptionGroup.setComponentName("Product Group Name:");
		addHierarchyTypeOptionGroup.setAddToParent(Boolean.TRUE);
		addHierarchyTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.PRODUCT_GROUP_NAME);
		addHierarchyTypeOptionGroup.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_CRITERIA);

		GtnUIFrameworkValidationConfig valConfigForProductGroupName = new GtnUIFrameworkValidationConfig();
		valConfigForProductGroupName.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addHierarchyTypeOptionGroup.setGtnUIFrameworkValidationConfig(valConfigForProductGroupName);

		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		addHierarchyNameTextBox.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_GROUP_NO);
		addHierarchyNameTextBox.setComponentName("Product Group No:");
		addHierarchyNameTextBox.setAddToParent(Boolean.TRUE);
		addHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.PRODUCT_GROUP_NO);
		addHierarchyNameTextBox.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_CRITERIA);

		GtnUIFrameworkValidationConfig valConfigForProductGroupNo = new GtnUIFrameworkValidationConfig();
		valConfigForProductGroupNo.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addHierarchyNameTextBox.setGtnUIFrameworkValidationConfig(valConfigForProductGroupNo);

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
		searchButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "search");
		searchButton.setComponentName("SEARCH");
		searchButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		searchButton.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig searchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		searchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		searchValidationActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_GROUP_NO,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_GROUP_NAME));

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
				+ GtnFrameworkCommonConstants.PRODUCT_GROUP_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_GROUP_NAME,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_GROUP_NO));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableAction.addActionParameter(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupSelectButton");
		actionConfigList.add(enableAction);

		searchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(searchButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupResetCriteriaButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		resetButton.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		params.add(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_GROUP_NAME,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRODUCT_GROUP_NO));
		params.add(Arrays.asList(new Object[] { "", "" }));

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
				+ GtnFrameworkCommonConstants.PRODUCT_GROUP_SEARCH_RESULT_TABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig
				.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "resultLayout");
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
				GtnForecastReturnsTableConstants.getGtnReturnsForecastProductGroupTableColumnsDataType());
		searchResults
				.setTableVisibleHeader(GtnForecastReturnsTableConstants.getGtnReturnsForecastProductGroupTableHeader());

		searchResults.setTableColumnMappingId(
				GtnForecastReturnsTableConstants.getGtnReturnsForecastProductGroupTableColumns());

		searchResults.setCountUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PRODUCT_GROUP_SEARCH_SERVICE);
		searchResults
				.setResultSetUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PRODUCT_GROUP_SEARCH_SERVICE);
		searchResults.setModuleName("forecastReturns");
		searchResults.setQueryName("searchQuery");

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
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupSelectButton");
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		selectButton.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_GROUP_SEARCH_RESULT_TABLE);
		actionParameter.add(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRODUCT_GROUP);
		actionParameter.add(Arrays.asList("productGroupNameFilterView"));
		actionParameter.add(Arrays.asList(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.PRODUCT_GROUP));
		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig initialLoadActionConfig = new GtnUIFrameWorkActionConfig();
		initialLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		initialLoadActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_LEFT_TABLE_LOAD_ACTION,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP,
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "relationShipCombobox",
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productLevelComboBox",
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "company",
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "businessUnit",
						namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
								+ GtnFrameworkCommonConstants.PRODUCT_GROUP,
						"levelNo", "product selection", "returns", getComponentIdBasedOnNamespace(namespace) }));

		GtnUIFrameWorkActionConfig dualListBoxLeftTableLoadAction = new GtnUIFrameWorkActionConfig();
		dualListBoxLeftTableLoadAction.setActionType(GtnUIFrameworkActionType.DUAL_LISTBOX_LEFT_TABLE_LOADACTION);
		dualListBoxLeftTableLoadAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		actionConfigList.add(addDualListBoxCustomAction(
				Arrays.asList(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "forecastLevel"), namespace));
		actionConfigList.add(initialLoadActionConfig);
		actionConfigList.add(dualListBoxLeftTableLoadAction);
		selectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(selectButton);

		GtnUIFrameWorkActionConfig closeOnSelectAction = new GtnUIFrameWorkActionConfig();
		closeOnSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeOnSelectAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_GROUP_LOOKUP_VIEW);
		actionConfigList.add(closeOnSelectAction);

		GtnUIFrameworkComponentConfig cancelButton = new GtnUIFrameworkComponentConfig();
		cancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cancelButton
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupCancelButton");
		cancelButton.setComponentName("CANCEL");
		cancelButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		cancelButton.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfigCloseList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROD_GROUP_LOOKUP_VIEW);
		actionConfigCloseList.add(closeAction);
		cancelButton.setGtnUIFrameWorkActionConfigList(actionConfigCloseList);
		componentList.add(cancelButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "productGroupResetControlButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		resetButton.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> confList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the table ?");

		params.add(Arrays.asList(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_GROUP_SEARCH_RESULT_TABLE));
		params.add(Arrays.asList(new Object[] { "" }));

		resetActionConfig.setActionParameterList(params);
		confList.add(resetActionConfig);
		resetButton.setGtnUIFrameWorkActionConfigList(confList);

		componentList.add(resetButton);
	}

	public GtnUIFrameWorkActionConfig addDualListBoxCustomAction(List<String> additionalParameters, String namespace) {
		GtnUIFrameWorkActionConfig dualListBoxConfigurationOnReset = new GtnUIFrameWorkActionConfig();
		List<Object> actionParam = new ArrayList<>();
		actionParam.add(GtnForecastReturnsClassConstants.RETURNS_FORECAST_RELATION_RESET_ACTION);
		actionParam.add(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP);
		if (!additionalParameters.isEmpty()) {
			actionParam.addAll(additionalParameters);
		}
		dualListBoxConfigurationOnReset.setActionParameterList(actionParam);
		dualListBoxConfigurationOnReset.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		return dualListBoxConfigurationOnReset;
	}

	private String getComponentIdBasedOnNamespace(String namespace) {
		if (namespace.equals("returnsForecastTabSheet")) {
			return "projectionDetailsTabsheetMainLayout";
		}
		return null;
	}
}
