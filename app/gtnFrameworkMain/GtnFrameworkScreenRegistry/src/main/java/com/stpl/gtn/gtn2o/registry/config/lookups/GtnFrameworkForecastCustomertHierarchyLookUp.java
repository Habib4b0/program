package com.stpl.gtn.gtn2o.registry.config.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.registry.action.GtnCustomerSelectionRelationshipLoadAction;
import com.stpl.gtn.gtn2o.registry.config.lookups.action.GtnForecastFilterAction;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastNewArchitectureConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkForecastCustomertHierarchyLookUp {

	private static final String[] COLUMNPROPERTYIDS = { "custHierarchyLookupHierName",
			"custHierarchyLookupHighestLevel", "custHierarchyLookupLowestLevel", "custHierarchyLookupCreatedDate",
			"custHierarchyLookupModifiedDate" };
	private static final GtnUIFrameworkComponentType[] COLUMNCOMPONENTTYPE = {
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8,
			GtnUIFrameworkComponentType.DATEFIELDVAADIN8 };

	public GtnUIFrameworkViewConfig getCustHierarchyLookUpView(String namespace) {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Forecast Customer Hierarchy LookUp");
		view.setViewId("forecastingLandingScreen_customerHierarchyLookup");
		view.setDefaultView(false);
		view.setResetAllowed(true);
		addForecastCustomertHierarchyComponentList(view, namespace);
		return view;
	}

	private void addForecastCustomertHierarchyComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addForecastCustomerHierarchyRootLayout(componentList, namespace);

	}

	private void addForecastCustomerHierarchyRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerHierarchyConfig = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerHierarchyConfig.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyConfig");
		forecastCustomerHierarchyConfig.setAddToParent(false);
		forecastCustomerHierarchyConfig.setSpacing(true);
		forecastCustomerHierarchyConfig.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig forecastCustomerHierarchyLayout = new GtnUIFrameworkLayoutConfig();
		forecastCustomerHierarchyLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		forecastCustomerHierarchyConfig.setGtnLayoutConfig(forecastCustomerHierarchyLayout);
		componentList.add(forecastCustomerHierarchyConfig);
		getForecastCustomerHierarchyComponentForPopUp(componentList, namespace);
	}

	public void getForecastCustomerHierarchyComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerHierarchyGtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig forecastCustomerHierarchyGtnConfig = new GtnUIFrameworkLayoutConfig();
		forecastCustomerHierarchyGtnConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		forecastCustomerHierarchyGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerHierarchyGtnLayout.setGtnLayoutConfig(forecastCustomerHierarchyGtnConfig);
		forecastCustomerHierarchyGtnLayout.setAddToParent(true);

		forecastCustomerHierarchyGtnLayout
				.setComponentId(namespace + "_" + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerHierarchyGtnLayout.setParentComponentId(namespace + "_" + "forecastCustomerHierarchyConfig");
		forecastCustomerHierarchyGtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerHierarchyGtnLayout.setSpacing(true);
		forecastCustomerHierarchyGtnLayout.setMargin(true);
		componentList.add(forecastCustomerHierarchyGtnLayout);

		forecastCustomerHierarchySearchCriteriaPanel(componentList, namespace);
		forecastCustomerHierarchySearchAndResetButtonLayout(componentList, namespace);
		forecastCustomerHierarchyResultsPanel(componentList, namespace);
		forecastCustomerHierarchyControlPopUpButtonLayout(componentList, namespace);
	}

	private void forecastCustomerHierarchySearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerHierarchySearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchySearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		forecastCustomerHierarchySearchCriteriaPanel.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchySearchCriteriaPanel");
		forecastCustomerHierarchySearchCriteriaPanel.setComponentName("Search Criteria");
		forecastCustomerHierarchySearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerHierarchySearchCriteriaPanel.setMargin(true);
		forecastCustomerHierarchySearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerHierarchySearchCriteriaPanel.addComponentStyle("stpl-margin-left-10");
		forecastCustomerHierarchySearchCriteriaPanel.addComponentStyle("stpl-margin-top-11");
		forecastCustomerHierarchySearchCriteriaPanel.setAddToParent(true);
		componentList.add(forecastCustomerHierarchySearchCriteriaPanel);
		forecastCustomerHierarchySearchCriteriaLayout(componentList, namespace);
	}

	private void forecastCustomerHierarchySearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerHierarchySearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchySearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerHierarchySearchCriteriaLayout
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);
		forecastCustomerHierarchySearchCriteriaLayout.setComponentName("Search Criteria Layout");
		forecastCustomerHierarchySearchCriteriaLayout.setAddToParent(true);
		forecastCustomerHierarchySearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerHierarchySearchCriteriaLayout.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchySearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig forecastCustomerHierarchySearchCriteriaConfig = new GtnUIFrameworkLayoutConfig();
		forecastCustomerHierarchySearchCriteriaLayout.setGtnLayoutConfig(forecastCustomerHierarchySearchCriteriaConfig);
		forecastCustomerHierarchySearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		forecastCustomerHierarchySearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(forecastCustomerHierarchySearchCriteriaLayout);

		addForecastCustomerHierarchyTypeOptionGroup(componentList, namespace);
		addForecastCustomerHierarchyNameTextBox(componentList, namespace);
	}

	private void addForecastCustomerHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig forecastCustomerHierarchyTypeOptionGroupLayout = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyTypeOptionGroupLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerHierarchyTypeOptionGroupLayout
				.setComponentId(namespace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE
						+ "forecastCustomerHierarchyTypeOptionGroupVerticalLayout");
		forecastCustomerHierarchyTypeOptionGroupLayout.addComponentStyle("stpl-margin-left-25");
		forecastCustomerHierarchyTypeOptionGroupLayout.setAddToParent(true);
		forecastCustomerHierarchyTypeOptionGroupLayout
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);

		GtnUIFrameworkLayoutConfig forecastCustomerHierarchyTypeOptionGroupainLayout = new GtnUIFrameworkLayoutConfig();
		forecastCustomerHierarchyTypeOptionGroupainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		forecastCustomerHierarchyTypeOptionGroupLayout
				.setGtnLayoutConfig(forecastCustomerHierarchyTypeOptionGroupainLayout);

		GtnUIFrameworkComponentConfig forecastCustomerHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		forecastCustomerHierarchyTypeOptionGroup
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_TYPE_OPTIONGROUP);
		forecastCustomerHierarchyTypeOptionGroup.setComponentName("Hierarchy Type:  ");
		forecastCustomerHierarchyTypeOptionGroup.setAddToParent(true);
		forecastCustomerHierarchyTypeOptionGroup
				.setParentComponentId(namespace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE
						+ "forecastCustomerHierarchyTypeOptionGroupVerticalLayout");
		GtnUIFrameworkOptionGroupConfig forecastCustomerHierarchyTypeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		forecastCustomerHierarchyTypeOptionGroupConfig.setItemValues(Arrays.asList("Primary", "Secondary"));
		forecastCustomerHierarchyTypeOptionGroupConfig.setValuesFromService(false);
		forecastCustomerHierarchyTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		forecastCustomerHierarchyTypeOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(forecastCustomerHierarchyTypeOptionGroupConfig);
		componentList.add(forecastCustomerHierarchyTypeOptionGroupLayout);
		componentList.add(forecastCustomerHierarchyTypeOptionGroup);
	}

	private void addForecastCustomerHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerHierarchyNameTextBoxLayout = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyNameTextBoxLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerHierarchyNameTextBoxLayout
				.setComponentId(namespace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE
						+ "forecastCustomerHierarchyNameTextBoxVerticalLayout");
		forecastCustomerHierarchyNameTextBoxLayout.setAddToParent(true);
		forecastCustomerHierarchyNameTextBoxLayout
				.setParentComponentId(namespace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);

		GtnUIFrameworkLayoutConfig forecastCustomerHierarchyNameMainLayout = new GtnUIFrameworkLayoutConfig();
		forecastCustomerHierarchyNameMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		forecastCustomerHierarchyNameTextBoxLayout.setGtnLayoutConfig(forecastCustomerHierarchyNameMainLayout);

		GtnUIFrameworkComponentConfig forecastCustomerHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		forecastCustomerHierarchyNameTextBox
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_NAME_TEXTBOX);
		forecastCustomerHierarchyNameTextBox.setComponentName("Hierarchy Name:    ");
		forecastCustomerHierarchyNameTextBox.setAddToParent(true);
		forecastCustomerHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		forecastCustomerHierarchyNameTextBox.addComponentStyle("stpl-margin-left-25");
		forecastCustomerHierarchyNameTextBox.setComponentHight("100%");
		forecastCustomerHierarchyNameTextBox
				.setParentComponentId(namespace + GtnFrameworkScreenRegisteryConstants.UNDERSCORE
						+ "forecastCustomerHierarchyNameTextBoxVerticalLayout");
		forecastCustomerHierarchyNameTextBox.setDefaultFocus(true);

		GtnUIFrameworkValidationConfig forecasthierarchyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		forecasthierarchyNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		forecastCustomerHierarchyNameTextBox.setGtnUIFrameworkValidationConfig(forecasthierarchyNameValidationConfig);
		componentList.add(forecastCustomerHierarchyNameTextBoxLayout);
		componentList.add(forecastCustomerHierarchyNameTextBox);
	}

	private void forecastCustomerHierarchySearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerHierarchySearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchySearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerHierarchySearchAndResetLayout.setAddToParent(true);
		forecastCustomerHierarchySearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerHierarchySearchAndResetLayout
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		forecastCustomerHierarchySearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig forecastCustomerHierarchySearchAndResetConfig = new GtnUIFrameworkLayoutConfig();
		forecastCustomerHierarchySearchAndResetLayout.setGtnLayoutConfig(forecastCustomerHierarchySearchAndResetConfig);
		componentList.add(forecastCustomerHierarchySearchAndResetLayout);

		GtnUIFrameworkComponentConfig forecastCustomerHierarchySearchButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchySearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerHierarchySearchButton.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchySearchButton");
		forecastCustomerHierarchySearchButton.setComponentName("SEARCH");
		forecastCustomerHierarchySearchButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		forecastCustomerHierarchySearchButton.setAddToParent(true);
		forecastCustomerHierarchySearchButton.addComponentStyle(
				GtnFrameworkScreenRegisteryConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);
		forecastCustomerHierarchySearchButton
				.setCustomReference(GtnFrameworkScreenRegisteryConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataTableActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE }));
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] {
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_TYPE_OPTIONGROUP,
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_NAME_TEXTBOX }));
		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig tableAlertAction = new GtnUIFrameWorkActionConfig();
		tableAlertAction.setActionType(GtnUIFrameworkActionType.V8_GRID_ALERT_ACTION);
		tableAlertAction.addActionParameter(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE);
		tableAlertAction.addActionParameter("No Results Found");
		tableAlertAction.addActionParameter("There are no Hierarchies that match the search criteria.");
		actionConfigList.add(tableAlertAction);

		forecastCustomerHierarchySearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(forecastCustomerHierarchySearchButton);

		GtnUIFrameworkComponentConfig forecastCustomerHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyResetButton");
		forecastCustomerHierarchyResetButton.setComponentName("RESET");
		forecastCustomerHierarchyResetButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		forecastCustomerHierarchyResetButton.setAddToParent(true);

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION);
		params.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION_MESSAGE);
		params.add(Arrays.asList(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_TYPE_OPTIONGROUP,
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_HIERARCHY_NAME_TEXTBOX));
		params.add(Arrays.asList(new Object[] { "Primary", GtnFrameworkCommonStringConstants.STRING_EMPTY }));
		resetActionConfig.setActionParameterList(params);
		forecastCustomerHierarchyResetButton.addGtnUIFrameWorkActionConfig(resetActionConfig);
		componentList.add(forecastCustomerHierarchyResetButton);
	}

	private void forecastCustomerHierarchyResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerHierarchyResultsPanel = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		forecastCustomerHierarchyResultsPanel.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyResultsPanel");
		forecastCustomerHierarchyResultsPanel.setComponentName("Results");
		forecastCustomerHierarchyResultsPanel.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerHierarchyResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerHierarchyResultsPanel.addComponentStyle("stpl-margin-left-10");
		forecastCustomerHierarchyResultsPanel.addComponentStyle("stpl-margin-top-11");
		forecastCustomerHierarchyResultsPanel.setAddToParent(true);
		componentList.add(forecastCustomerHierarchyResultsPanel);
		forecastCustomerHierarchyResultLayout(componentList, namespace);

	}

	private void forecastCustomerHierarchyResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig forecastCustomerHierarchyResultLayout = new GtnUIFrameworkLayoutConfig();
		forecastCustomerHierarchyResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig forecastCustomerHierarchyResultConfig = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerHierarchyResultConfig.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyResultConfig");

		forecastCustomerHierarchyResultConfig.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyResultsPanel");
		forecastCustomerHierarchyResultConfig.setAddToParent(true);
		forecastCustomerHierarchyResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerHierarchyResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerHierarchyResultConfig.setGtnLayoutConfig(forecastCustomerHierarchyResultLayout);
		componentList.add(forecastCustomerHierarchyResultConfig);
		addCustomerHierarchyPagedTableComponent(componentList, namespace);
	}

	private void addCustomerHierarchyPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig customerHierarchyPagedTableComponent = new GtnUIFrameworkComponentConfig();
		customerHierarchyPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		customerHierarchyPagedTableComponent
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE);
		customerHierarchyPagedTableComponent.setComponentName("Results");
		customerHierarchyPagedTableComponent.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyResultConfig");
		customerHierarchyPagedTableComponent.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		customerHierarchyPagedTableComponent.setComponentWidth("100%");
		customerHierarchyPagedTableComponent.setComponentStyle(tableStyle);
		customerHierarchyPagedTableComponent.setModuleName(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY);
		customerHierarchyPagedTableComponent.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		customerHierarchyPagedTableComponent.setModuleName(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY);

		componentList.add(customerHierarchyPagedTableComponent);
		GtnUIFrameworkPagedTableConfig customerHierarchyPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		customerHierarchyPagedTableConfig.setEditable(false);
		customerHierarchyPagedTableConfig.setFilterBar(true);
		customerHierarchyPagedTableConfig.setPageLength(10);
		customerHierarchyPagedTableConfig.setItemPerPage(10);
		customerHierarchyPagedTableConfig.setSelectable(true);
		customerHierarchyPagedTableConfig.setPaginationOff(true);
		customerHierarchyPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		customerHierarchyPagedTableConfig.setTableColumnDataType(
				GtnFrameworkForecastingStringConstants.getForecastCustomerHierarchyTableColumnsDataType());
		customerHierarchyPagedTableConfig.setColumnHeaders(Arrays.asList(
				GtnFrameworkForecastingStringConstants.getForecastCustomerHierarchyTableColumnsVisibleHeader()));
		customerHierarchyPagedTableConfig.setTableColumnMappingId(
				GtnFrameworkForecastingStringConstants.getForecastCustomerHierarchyTableColumnsMappingId());
		customerHierarchyPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		customerHierarchyPagedTableConfig.setSelectionListener(true);

		List<GtnUIFrameWorkActionConfig> itemClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectBtnEnableAction = new GtnUIFrameWorkActionConfig();
		selectBtnEnableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		selectBtnEnableAction.addActionParameter(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ "forecastCustomerHierarchySelectButton");
		itemClickActionConfigList.add(selectBtnEnableAction);
		customerHierarchyPagedTableConfig.setItemClickActionConfigList(itemClickActionConfigList);

		List<String> additionalSearchCriteria = new ArrayList<>();
		additionalSearchCriteria.add("Customer Hierarchy");
		customerHierarchyPagedTableConfig.setAdditionalSearchCriteriaListValues(additionalSearchCriteria);

		customerHierarchyPagedTableConfig
				.setCountUrl(GtnFrameworkForecastNewArchitectureConstants.HIERARCHY_RESULTS_SERVICE_REGISTRY_URL);
		customerHierarchyPagedTableConfig
				.setResultSetUrl(GtnFrameworkForecastNewArchitectureConstants.HIERARCHY_RESULTS_SERVICE_REGISTRY_URL);
		customerHierarchyPagedTableConfig.setPagedTableWsUrl("/loadHierarchyResults");
		customerHierarchyPagedTableConfig.setRegisteredWebContext(
				GtnFrameworkForecastNewArchitectureConstants.HIERARCHY_RESULTS_REGISTERED_WEB_CONTEXT);
		customerHierarchyPagedTableConfig
				.setModuleName(GtnFrameworkForecastNewArchitectureConstants.HIERARCHY_RESULTS_END_POINT_URL_NAME);
		customerHierarchyPagedTableConfig.setFilteron(true);
		customerHierarchyPagedTableConfig.setGridHeaderCustomClassLoadURL(GtnForecastFilterAction.class.getName());
		customerHierarchyPagedTableConfig
				.setGridHeaderCustomParameter(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE);
		customerHierarchyPagedTableComponent.setGtnPagedTableConfig(customerHierarchyPagedTableConfig);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customerHierarchyustomFilterConfigMap = new HashMap<>(
				COLUMNPROPERTYIDS.length);
		String[] custComboboxIds = new String[1];
		String[] custComboBoxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < COLUMNPROPERTYIDS.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig custHierarchyFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			custHierarchyFilterConfig.setPropertId(COLUMNPROPERTYIDS[i]);
			custHierarchyFilterConfig.setGtnComponentType(COLUMNCOMPONENTTYPE[i]);
			if ((startIndex < custComboboxIds.length) && COLUMNPROPERTYIDS[i].equals(custComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig custHierarchySearchFilterConfig = new GtnUIFrameworkComponentConfig();
				custHierarchySearchFilterConfig.setComponentId("customFilterComboBox");
				custHierarchySearchFilterConfig.setComponentName("customFilterComboBox");
				custHierarchySearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				custHierarchySearchFilterConfig.getGtnComboboxConfig().setComboBoxType(custComboBoxType[startIndex]);
				custHierarchySearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				custHierarchyFilterConfig.setGtnComponentConfig(custHierarchySearchFilterConfig);
				startIndex++;
			}
			customerHierarchyustomFilterConfigMap.put(custHierarchyFilterConfig.getPropertId(),
					custHierarchyFilterConfig);
		}
		return customerHierarchyustomFilterConfigMap;
	}

	private void forecastCustomerHierarchyControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerHierarchyControlPopUpConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig forecastingCustomerHierarchyControlLayout = new GtnUIFrameworkLayoutConfig();
		forecastCustomerHierarchyControlPopUpConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerHierarchyControlPopUpConfig.setAddToParent(true);
		forecastCustomerHierarchyControlPopUpConfig.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerHierarchyControlPopUpConfig
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		forecastCustomerHierarchyControlPopUpConfig.setSpacing(true);
		forecastCustomerHierarchyControlPopUpConfig.setGtnLayoutConfig(forecastingCustomerHierarchyControlLayout);
		componentList.add(forecastCustomerHierarchyControlPopUpConfig);

		GtnUIFrameworkComponentConfig forecastCustomerHierarchySelectButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchySelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerHierarchySelectButton.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchySelectButton");
		forecastCustomerHierarchySelectButton.setComponentName("SELECT");
		forecastCustomerHierarchySelectButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		forecastCustomerHierarchySelectButton.setAddToParent(true);
		forecastCustomerHierarchySelectButton.setEnable(false);

		componentList.add(forecastCustomerHierarchySelectButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig forecastCustomerHierarchySelectAction = new GtnUIFrameWorkActionConfig();
		forecastCustomerHierarchySelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE);
		actionParameter.add(namespace + GtnFrameworkScreenRegisteryConstants.FORECAST_CUST_HIER_LOOKUP_CONTROL_POP);
		actionParameter.add(Arrays.asList("custHierarchyLookupHierName"));
		actionParameter.add(
				Arrays.asList(namespace + GtnFrameworkScreenRegisteryConstants.FORECAST_CUST_HIER_LOOKUP_CONTROL_POP));
		forecastCustomerHierarchySelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(forecastCustomerHierarchySelectAction);

		GtnUIFrameWorkActionConfig forecastingCustomHierarchyClosepopup = new GtnUIFrameWorkActionConfig();
		forecastingCustomHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		forecastingCustomHierarchyClosepopup.addActionParameter("customerHierarchyLookup");
		actionConfigList.add(forecastingCustomHierarchyClosepopup);

		GtnUIFrameWorkActionConfig forecastingCustomerHierarchyRelationshipLoadAction = new GtnUIFrameWorkActionConfig();
		forecastingCustomerHierarchyRelationshipLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		forecastingCustomerHierarchyRelationshipLoadAction
				.addActionParameter(GtnCustomerSelectionRelationshipLoadAction.class.getName());
		forecastingCustomerHierarchyRelationshipLoadAction.addActionParameter(
				namespace + GtnFrameworkScreenRegisteryConstants.FORECAST_CUST_HIER_LOOKUP_CONTROL_POP);
		forecastingCustomerHierarchyRelationshipLoadAction
				.addActionParameter(namespace + "_customerSelectionRelationship");

		actionConfigList.add(forecastingCustomerHierarchyRelationshipLoadAction);

		forecastCustomerHierarchySelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig forecastCustomerHierarchyCancelButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerHierarchyCancelButton.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyCancelButton");
		forecastCustomerHierarchyCancelButton.setComponentName("CANCEL");
		forecastCustomerHierarchyCancelButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		forecastCustomerHierarchyCancelButton.setAddToParent(true);

		forecastCustomerHierarchyCancelButton.addGtnUIFrameWorkActionConfig(forecastingCustomHierarchyClosepopup);
		componentList.add(forecastCustomerHierarchyCancelButton);

		GtnUIFrameworkComponentConfig forecastCustomerHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyResetButton");
		forecastCustomerHierarchyResetButton.setComponentName("RESET");
		forecastCustomerHierarchyResetButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		forecastCustomerHierarchyResetButton.setAddToParent(true);

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> paramList = new ArrayList<>();
		paramList.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION);
		paramList.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION_TABLE_MESSAGE);

		paramList.add(Arrays.asList(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE));
		paramList.add(Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.STRING_EMPTY }));

		resetActionConfig.setActionParameterList(paramList);
		forecastCustomerHierarchyResetButton.addGtnUIFrameWorkActionConfig(resetActionConfig);

		componentList.add(forecastCustomerHierarchyResetButton);
	}
}
