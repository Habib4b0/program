package com.stpl.gtn.gtn2o.registry.config.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.registry.util.GtnFrameworkAlertUtil;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastNewArchitectureConstants;

public class GtnFrameworkCustomerAndProductGroupLookup {

	public GtnUIFrameworkViewConfig getCustProdGroupLookUpView(String namespace) {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName(namespace + " Group Lookup");
		view.setViewId(namespace + "GroupLookupView");
		view.setDefaultView(false);
		addForecastCustomertProductGroupComponentList(view, namespace);
		return view;
	}

	private void addForecastCustomertProductGroupComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addForecastCustomerProductGroupRootLayout(componentList, namespace);

	}

	private void addForecastCustomerProductGroupRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerProductGroupConfig = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerProductGroupConfig.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerProductGroupConfig");
		forecastCustomerProductGroupConfig.setAddToParent(false);
		forecastCustomerProductGroupConfig.setSpacing(true);
		forecastCustomerProductGroupConfig.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig forecastCustomerProductGroupLayout = new GtnUIFrameworkLayoutConfig();
		forecastCustomerProductGroupLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		forecastCustomerProductGroupConfig.setGtnLayoutConfig(forecastCustomerProductGroupLayout);
		componentList.add(forecastCustomerProductGroupConfig);
		getForecastCustomerHierarchyComponentForPopUp(componentList, namespace);
	}

	public void getForecastCustomerHierarchyComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerProductGroupGtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig forecastCustomerProductGroupGtnConfig = new GtnUIFrameworkLayoutConfig();
		forecastCustomerProductGroupGtnConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		forecastCustomerProductGroupGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerProductGroupGtnLayout.setGtnLayoutConfig(forecastCustomerProductGroupGtnConfig);
		forecastCustomerProductGroupGtnLayout.setAddToParent(true);

		forecastCustomerProductGroupGtnLayout
				.setComponentId(namespace + "_" + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerProductGroupGtnLayout
				.setParentComponentId(namespace + "_" + "forecastCustomerProductGroupConfig");
		forecastCustomerProductGroupGtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerProductGroupGtnLayout.setSpacing(true);
		forecastCustomerProductGroupGtnLayout.setMargin(true);
		componentList.add(forecastCustomerProductGroupGtnLayout);

		forecastCustomerProductGroupSearchCriteriaPanel(componentList, namespace);
		forecastCustomerProductGroupSearchAndResetButtonLayout(componentList, namespace);
		forecastCustomerProductGroupResultsPanel(componentList, namespace);
		forecastCustomerProductGroupControlPopUpButtonLayout(componentList, namespace);
	}

	private void forecastCustomerProductGroupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerProductGroupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		forecastCustomerProductGroupSearchCriteriaPanel
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ "forecastCustomerProductGroupSearchCriteriaPanel");
		forecastCustomerProductGroupSearchCriteriaPanel.setComponentName("Search Criteria");
		forecastCustomerProductGroupSearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerProductGroupSearchCriteriaPanel.setMargin(true);
		forecastCustomerProductGroupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerProductGroupSearchCriteriaPanel.setAddToParent(true);
		componentList.add(forecastCustomerProductGroupSearchCriteriaPanel);
		forecastCustomerProductGroupSearchCriteriaLayout(componentList, namespace);
	}

	private void forecastCustomerProductGroupSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerProductGroupSearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupSearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerProductGroupSearchCriteriaLayout.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_SEARCH_CRITERIA_LAYOUT);
		forecastCustomerProductGroupSearchCriteriaLayout.setComponentName("Search Criteria Layout");
		forecastCustomerProductGroupSearchCriteriaLayout.setAddToParent(true);
		forecastCustomerProductGroupSearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerProductGroupSearchCriteriaLayout
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ "forecastCustomerProductGroupSearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig forecastCustomerHierarchySearchCriteriaConfig = new GtnUIFrameworkLayoutConfig();
		forecastCustomerProductGroupSearchCriteriaLayout
				.setGtnLayoutConfig(forecastCustomerHierarchySearchCriteriaConfig);
		forecastCustomerProductGroupSearchCriteriaLayout
				.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		forecastCustomerProductGroupSearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(forecastCustomerProductGroupSearchCriteriaLayout);

		addForecastCustomerProductGroupNameTextBox(componentList, namespace);
		addForecastCustomerProductGroupNoTextBox(componentList, namespace);
	}

	private void addForecastCustomerProductGroupNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerProductGroupNameTextBox = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		forecastCustomerProductGroupNameTextBox
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_NAME_TEXTBOX);
		forecastCustomerProductGroupNameTextBox.setComponentName(namespace + " Group Name: ");
		forecastCustomerProductGroupNameTextBox.setAddToParent(true);
		forecastCustomerProductGroupNameTextBox.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_SEARCH_CRITERIA_LAYOUT);

		GtnUIFrameworkValidationConfig forecastProductGroupNameValidationConfig = new GtnUIFrameworkValidationConfig();
		forecastProductGroupNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		forecastCustomerProductGroupNameTextBox
				.setGtnUIFrameworkValidationConfig(forecastProductGroupNameValidationConfig);

		componentList.add(forecastCustomerProductGroupNameTextBox);
	}

	private void addForecastCustomerProductGroupNoTextBox(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerProductGroupNoTextBox = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupNoTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		forecastCustomerProductGroupNoTextBox
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_NO_TEXTBOX);
		forecastCustomerProductGroupNoTextBox.setComponentName(namespace + " Group No");
		forecastCustomerProductGroupNoTextBox.setAddToParent(true);
		forecastCustomerProductGroupNoTextBox.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_SEARCH_CRITERIA_LAYOUT);

		GtnUIFrameworkValidationConfig forecastProductGroupNoValidationConfig = new GtnUIFrameworkValidationConfig();
		forecastProductGroupNoValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		forecastCustomerProductGroupNoTextBox.setGtnUIFrameworkValidationConfig(forecastProductGroupNoValidationConfig);

		componentList.add(forecastCustomerProductGroupNoTextBox);
	}

	private void forecastCustomerProductGroupSearchAndResetButtonLayout(
			List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerProductGroupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerProductGroupSearchAndResetLayout.setAddToParent(true);
		forecastCustomerProductGroupSearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerProductGroupSearchAndResetLayout.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_SEARCH_AND_RESULT_LAYOUT);
		forecastCustomerProductGroupSearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig forecastCustomerHierarchySearchAndResetConfig = new GtnUIFrameworkLayoutConfig();
		forecastCustomerProductGroupSearchAndResetLayout
				.setGtnLayoutConfig(forecastCustomerHierarchySearchAndResetConfig);
		componentList.add(forecastCustomerProductGroupSearchAndResetLayout);

		GtnUIFrameworkComponentConfig forecastCustomerProductGroupSearchButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerProductGroupSearchButton.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerProductGroupSearchButton");
		forecastCustomerProductGroupSearchButton.setComponentName("SEARCH");
		forecastCustomerProductGroupSearchButton.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_SEARCH_AND_RESULT_LAYOUT);
		forecastCustomerProductGroupSearchButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> customerAndProductGroupActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadCustomerAndProductGroupDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadCustomerAndProductGroupDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadCustomerAndProductGroupDataTableActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.CUSTOMER_AND_PRODUCT_GROUP_SEARCH_RESULT_TABLE }));
		loadCustomerAndProductGroupDataTableActionConfig.setFieldValues(Arrays.asList(new String[] {
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_NAME_TEXTBOX,
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_NO_TEXTBOX }));
		customerAndProductGroupActionConfigList.add(loadCustomerAndProductGroupDataTableActionConfig);
		forecastCustomerProductGroupSearchButton
				.setGtnUIFrameWorkActionConfigList(customerAndProductGroupActionConfigList);

		componentList.add(forecastCustomerProductGroupSearchButton);

		GtnUIFrameworkComponentConfig forecastCustomerProductGroupResetButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerProductGroupResetButton.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerProductGroupResetButton");
		forecastCustomerProductGroupResetButton.setComponentName("RESET");
		forecastCustomerProductGroupResetButton.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_SEARCH_AND_RESULT_LAYOUT);
		forecastCustomerProductGroupResetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> customerAndProductGroupResetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customerAndProductGroupResetActionConfig = new GtnUIFrameWorkActionConfig();
		customerAndProductGroupResetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION);
		params.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION_MESSAGE);
		params.add(Arrays.asList(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_NAME_TEXTBOX,
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.FORECAST_CUSTOMER_PRODUCT_GROUP_NO_TEXTBOX));
		params.add(Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY }));
		customerAndProductGroupResetActionConfig.setActionParameterList(params);
		customerAndProductGroupResetActionConfigList.add(customerAndProductGroupResetActionConfig);
		forecastCustomerProductGroupResetButton
				.setGtnUIFrameWorkActionConfigList(customerAndProductGroupResetActionConfigList);
		componentList.add(forecastCustomerProductGroupResetButton);
	}

	private void forecastCustomerProductGroupResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerProductGroupResultsPanel = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		forecastCustomerProductGroupResultsPanel.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerProductGroupResultsPanel");
		forecastCustomerProductGroupResultsPanel.setComponentName("Results");
		forecastCustomerProductGroupResultsPanel.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerProductGroupResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerProductGroupResultsPanel.setAddToParent(true);
		componentList.add(forecastCustomerProductGroupResultsPanel);
		forecastCustomerProductGroupResultLayout(componentList, namespace);

	}

	private void forecastCustomerProductGroupResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig forecastCustomerProductGroupResultLayout = new GtnUIFrameworkLayoutConfig();
		forecastCustomerProductGroupResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig forecastCustomerProductGroupResultConfig = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerProductGroupResultConfig.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerProductGroupResultConfig");

		forecastCustomerProductGroupResultConfig.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerProductGroupResultsPanel");
		forecastCustomerProductGroupResultConfig.setAddToParent(true);
		forecastCustomerProductGroupResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerProductGroupResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastCustomerProductGroupResultConfig.setGtnLayoutConfig(forecastCustomerProductGroupResultLayout);
		componentList.add(forecastCustomerProductGroupResultConfig);
		addCustomerProductGroupPagedTableComponent(componentList, namespace);
	}

	private void addCustomerProductGroupPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig customerProductGroupPagedTableComponent = new GtnUIFrameworkComponentConfig();
		customerProductGroupPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		customerProductGroupPagedTableComponent
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.CUSTOMER_AND_PRODUCT_GROUP_SEARCH_RESULT_TABLE);
		customerProductGroupPagedTableComponent.setComponentName("Results");
		customerProductGroupPagedTableComponent.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerProductGroupResultConfig");
		customerProductGroupPagedTableComponent.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		customerProductGroupPagedTableComponent.setComponentWidth("100%");
		customerProductGroupPagedTableComponent.setComponentStyle(tableStyle);
		customerProductGroupPagedTableComponent.setModuleName(GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY);

		componentList.add(customerProductGroupPagedTableComponent);
		GtnUIFrameworkPagedTableConfig customerProductGroupPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		customerProductGroupPagedTableConfig.setEditable(false);
		customerProductGroupPagedTableConfig.setFilterBar(true);
		customerProductGroupPagedTableConfig.setPageLength(10);
		customerProductGroupPagedTableConfig.setItemPerPage(10);
		customerProductGroupPagedTableConfig.setSelectable(true);
		customerProductGroupPagedTableConfig.setSinkItemPerPageWithPageLength(false);

		customerProductGroupPagedTableConfig.setTableColumnDataType(namespace.contains(GtnFrameworkForecastingStringConstants.CUSTOMER)
				? GtnFrameworkForecastingStringConstants.getForecastCustomerGroupTableColumnsDataType()
				: GtnFrameworkForecastingStringConstants.getForecastProductGroupTableColumnsDataType());
		customerProductGroupPagedTableConfig.setColumnHeaders(Arrays.asList(namespace.contains(GtnFrameworkForecastingStringConstants.CUSTOMER)
				? GtnFrameworkForecastingStringConstants.getForecastCustomerGroupTableColumnsVisibleHeader()
				: GtnFrameworkForecastingStringConstants.getForecastProductGroupTableColumnsVisibleHeader()));
		customerProductGroupPagedTableConfig.setTableColumnMappingId(namespace.contains(GtnFrameworkForecastingStringConstants.CUSTOMER)
				? GtnFrameworkForecastingStringConstants.getForecastCustomerGroupTableColumnsMappingId()
				: GtnFrameworkForecastingStringConstants.getForecastProductGroupTableColumnsMappingId());

		List<String> customerProductGroupAdditionalSearchCriteria = new ArrayList<>();
		customerProductGroupAdditionalSearchCriteria.add(namespace);
		customerProductGroupPagedTableConfig
				.setAdditionalSearchCriteriaListValues(customerProductGroupAdditionalSearchCriteria);
		GtnFrameworkAlertUtil customerProductGroupAlertActionUtil = new GtnFrameworkAlertUtil();
		GtnUIFrameWorkActionConfig alertAction = customerProductGroupAlertActionUtil
				.throwAlertUtil(GtnFrameworkForecastNewArchitectureConstants.CUSTOMER_AND_PRODUCT_GROUP_RESULTS_URL);
		customerProductGroupPagedTableConfig.setRecordTypeManageActionConfig(alertAction);

		customerProductGroupPagedTableConfig.setQueryName(namespace + "Group");
		customerProductGroupPagedTableConfig.setCountUrl(
				GtnFrameworkForecastNewArchitectureConstants.CUSTOMER_AND_PRODUCT_GROUP_RESULTS_SERVICE_REGISTRY_URL);
		customerProductGroupPagedTableConfig.setResultSetUrl(
				GtnFrameworkForecastNewArchitectureConstants.CUSTOMER_AND_PRODUCT_GROUP_RESULTS_SERVICE_REGISTRY_URL);
		customerProductGroupPagedTableConfig.setPagedTableWsUrl(
				GtnFrameworkForecastNewArchitectureConstants.CUSTOMER_AND_PRODUCT_GROUP_RESULTS_URL);
		customerProductGroupPagedTableConfig.setRegisteredWebContext(
				GtnFrameworkForecastNewArchitectureConstants.CUSTOMER_AND_PRODUCT_GROUP_RESULTS_REGISTERED_WEB_CONTEXT);
		customerProductGroupPagedTableConfig.setModuleName(
				GtnFrameworkForecastNewArchitectureConstants.CUSTOMER_AND_PRODUCT_GROUP_RESULTS_END_POINT_URL_NAME);
		customerProductGroupPagedTableComponent.setGtnPagedTableConfig(customerProductGroupPagedTableConfig);
	}

	private void forecastCustomerProductGroupControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig forecastCustomerProductGroupControlPopUpConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig forecastingCustomerHierarchyControlLayout = new GtnUIFrameworkLayoutConfig();
		forecastCustomerProductGroupControlPopUpConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastCustomerProductGroupControlPopUpConfig.setAddToParent(true);
		forecastCustomerProductGroupControlPopUpConfig.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastCustomerProductGroupControlPopUpConfig
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		forecastCustomerProductGroupControlPopUpConfig.setSpacing(true);
		forecastCustomerProductGroupControlPopUpConfig.setGtnLayoutConfig(forecastingCustomerHierarchyControlLayout);
		componentList.add(forecastCustomerProductGroupControlPopUpConfig);

		GtnUIFrameworkComponentConfig forecastCustomerProductGroupSelectButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerProductGroupSelectButton.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerProductGroupSelectButton");
		forecastCustomerProductGroupSelectButton.setComponentName("SELECT");
		forecastCustomerProductGroupSelectButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		forecastCustomerProductGroupSelectButton.setAddToParent(true);

		componentList.add(forecastCustomerProductGroupSelectButton);

		List<GtnUIFrameWorkActionConfig> customerAndProductGroupActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig forecastCustomerProductGroupSelectAction = new GtnUIFrameWorkActionConfig();
		forecastCustomerProductGroupSelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> customerAndProductGroupActionParameter = new ArrayList<>();
		customerAndProductGroupActionParameter.add(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.CUSTOMER_AND_PRODUCT_GROUP_SEARCH_RESULT_TABLE);
		customerAndProductGroupActionParameter.add(namespace.contains(GtnFrameworkForecastingStringConstants.CUSTOMER)
				? "Commercial Forecasting_customerGroup" : "Commercial Forecasting_productGroup");
		customerAndProductGroupActionParameter
				.add(Arrays.asList(namespace.contains(GtnFrameworkForecastingStringConstants.CUSTOMER) ? "customerGroupName" : "productGroupName"));
		customerAndProductGroupActionParameter.add(Arrays.asList(namespace.contains(GtnFrameworkForecastingStringConstants.CUSTOMER)
				? "Commercial Forecasting_customerGroup" : "Commercial Forecasting_productGroup"));
		forecastCustomerProductGroupSelectAction.setActionParameterList(customerAndProductGroupActionParameter);
		customerAndProductGroupActionConfigList.add(forecastCustomerProductGroupSelectAction);

		GtnUIFrameWorkActionConfig forecastingCustomProductGroupClosepopup = new GtnUIFrameWorkActionConfig();
		forecastingCustomProductGroupClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		forecastingCustomProductGroupClosepopup.addActionParameter(namespace + "GroupLookupView");
		customerAndProductGroupActionConfigList.add(forecastingCustomProductGroupClosepopup);

		forecastCustomerProductGroupSelectButton
				.setGtnUIFrameWorkActionConfigList(customerAndProductGroupActionConfigList);

		GtnUIFrameworkComponentConfig forecastCustomerProductGroupCancelButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerProductGroupCancelButton.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyCancelButton");
		forecastCustomerProductGroupCancelButton.setComponentName("CANCEL");
		forecastCustomerProductGroupCancelButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		forecastCustomerProductGroupCancelButton.setAddToParent(true);

		forecastCustomerProductGroupCancelButton.addGtnUIFrameWorkActionConfig(forecastingCustomProductGroupClosepopup);
		componentList.add(forecastCustomerProductGroupCancelButton);

		GtnUIFrameworkComponentConfig forecastCustomerProductGroupResetButton = new GtnUIFrameworkComponentConfig();
		forecastCustomerProductGroupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastCustomerProductGroupResetButton.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "forecastCustomerHierarchyResetButton");
		forecastCustomerProductGroupResetButton.setComponentName("RESET");
		forecastCustomerProductGroupResetButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		forecastCustomerProductGroupResetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> customerAndProductGroupconfList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customerAndProductGroupResetActionConfig = new GtnUIFrameWorkActionConfig();
		customerAndProductGroupResetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> customerAndProductGroupParamList = new ArrayList<>();
		customerAndProductGroupParamList.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION);
		customerAndProductGroupParamList.add(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION_TABLE_MESSAGE);

		customerAndProductGroupParamList.add(Arrays.asList(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.CUSTOMER_AND_PRODUCT_GROUP_SEARCH_RESULT_TABLE));
		customerAndProductGroupParamList
				.add(Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.STRING_EMPTY }));

		customerAndProductGroupResetActionConfig.setActionParameterList(customerAndProductGroupParamList);
		customerAndProductGroupconfList.add(customerAndProductGroupResetActionConfig);
		forecastCustomerProductGroupResetButton.setGtnUIFrameWorkActionConfigList(customerAndProductGroupconfList);

		componentList.add(forecastCustomerProductGroupResetButton);
	}
}
