package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkSelectButtonEnableActionInHierarchyLookup;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportCustomertHierarchyLookUp {

	private static final String[] columnPropertyIds = { "custHierarchyLookupHierName",
			"custHierarchyLookupHighestLevel", "custHierarchyLookupLowestLevel", "custHierarchyLookupCreatedDate",
			"custHierarchyLookupModifiedDate" };
	private static final GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8 };

	public GtnUIFrameworkViewConfig getCustHierarchyLookUpView(String namespace) {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Customer Hierarchy LookUp");
		view.setViewId("reportLandingScreen_customerHierarchyLookup");
		view.setDefaultView(false);
		view.setResetAllowed(true);
		addreportCustomertHierarchyComponentList(view, namespace);
		return view;
	}

	private void addreportCustomertHierarchyComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addreportCustomerHierarchyRootLayout(componentList, namespace);

	}

	private void addreportCustomerHierarchyRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyConfig = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchyConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyConfig");
		reportCustomerHierarchyConfig.setAddToParent(false);
		reportCustomerHierarchyConfig.setSpacing(true);
		reportCustomerHierarchyConfig.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig reportCustomerHierarchyLayout = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchyLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportCustomerHierarchyConfig.setGtnLayoutConfig(reportCustomerHierarchyLayout);
		componentList.add(reportCustomerHierarchyConfig);
		getReportCustomerHierarchyComponentForPopUp(componentList, namespace);
	}

	public void getReportCustomerHierarchyComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyGtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportCustomerHierarchyGtnConfig = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchyGtnConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportCustomerHierarchyGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchyGtnLayout.setGtnLayoutConfig(reportCustomerHierarchyGtnConfig);
		reportCustomerHierarchyGtnLayout.setAddToParent(true);

		reportCustomerHierarchyGtnLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportCustomerHierarchyGtnLayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyConfig");
		reportCustomerHierarchyGtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchyGtnLayout.setSpacing(true);
		reportCustomerHierarchyGtnLayout.setMargin(true);
		componentList.add(reportCustomerHierarchyGtnLayout);

		reportCustomerHierarchySearchCriteriaPanel(componentList, namespace);
		reportCustomerHierarchySearchAndResetButtonLayout(componentList, namespace);
		reportCustomerHierarchyResultsPanel(componentList, namespace);
		reportCustomerHierarchyControlPopUpButtonLayout(componentList, namespace);
	}

	private void reportCustomerHierarchySearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchySearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportCustomerHierarchySearchCriteriaPanel.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySearchCriteriaPanel");
		reportCustomerHierarchySearchCriteriaPanel.setComponentName("Search Criteria");
		reportCustomerHierarchySearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportCustomerHierarchySearchCriteriaPanel.setMargin(true);
		reportCustomerHierarchySearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchySearchCriteriaPanel.setAddToParent(true);
		reportCustomerHierarchySearchCriteriaPanel.addComponentStyle("stpl-margin-left-10");
		reportCustomerHierarchySearchCriteriaPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(reportCustomerHierarchySearchCriteriaPanel);
		reportCustomerHierarchySearchCriteriaLayout(componentList, namespace);
	}

	private void reportCustomerHierarchySearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig reportCustomerHierarchySearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchySearchCriteriaLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);
		reportCustomerHierarchySearchCriteriaLayout.setComponentName("Search Criteria Layout");
		reportCustomerHierarchySearchCriteriaLayout.setAddToParent(true);
		reportCustomerHierarchySearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchySearchCriteriaLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig reportCustomerHierarchySearchCriteriaConfig = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchySearchCriteriaLayout.setGtnLayoutConfig(reportCustomerHierarchySearchCriteriaConfig);
		reportCustomerHierarchySearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportCustomerHierarchySearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(reportCustomerHierarchySearchCriteriaLayout);

		addReportCustomerHierarchyTypeOptionGroup(componentList, namespace);
		addReportCustomerHierarchyNameTextBox(componentList, namespace);
	}

	private void addReportCustomerHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig addReportCustomerHierarchyTypeOptionGroupLayout = new GtnUIFrameworkComponentConfig();
		addReportCustomerHierarchyTypeOptionGroupLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		addReportCustomerHierarchyTypeOptionGroupLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ "reportCustomerHierarchyTypeOptionGroupVerticalLayout");
		addReportCustomerHierarchyTypeOptionGroupLayout.addComponentStyle("stpl-margin-left-25");
		addReportCustomerHierarchyTypeOptionGroupLayout.setAddToParent(true);
		addReportCustomerHierarchyTypeOptionGroupLayout
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);

		GtnUIFrameworkLayoutConfig reportCustomerHierarchyTypeOptionGroupainLayout = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchyTypeOptionGroupainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		addReportCustomerHierarchyTypeOptionGroupLayout
				.setGtnLayoutConfig(reportCustomerHierarchyTypeOptionGroupainLayout);

		GtnUIFrameworkComponentConfig reportCustomerHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		reportCustomerHierarchyTypeOptionGroup.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_TYPE_OPTIONGROUP);
		reportCustomerHierarchyTypeOptionGroup.setComponentName("Hierarchy Type:  ");
		reportCustomerHierarchyTypeOptionGroup.setAddToParent(true);
		reportCustomerHierarchyTypeOptionGroup
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ "reportCustomerHierarchyTypeOptionGroupVerticalLayout");
		GtnUIFrameworkOptionGroupConfig reportCustomerHierarchyTypeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		reportCustomerHierarchyTypeOptionGroupConfig.setItemValues(Arrays.asList("Primary", "Secondary"));
		reportCustomerHierarchyTypeOptionGroupConfig.setValuesFromService(false);
		reportCustomerHierarchyTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		reportCustomerHierarchyTypeOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(reportCustomerHierarchyTypeOptionGroupConfig);
		componentList.add(addReportCustomerHierarchyTypeOptionGroupLayout);
		componentList.add(reportCustomerHierarchyTypeOptionGroup);
	}

	private void addReportCustomerHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig addReportCustomerHierarchyNameTextBoxLayout = new GtnUIFrameworkComponentConfig();
		addReportCustomerHierarchyNameTextBoxLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		addReportCustomerHierarchyNameTextBoxLayout.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyNameTextBoxVerticalLayout");
		addReportCustomerHierarchyNameTextBoxLayout.setAddToParent(true);
		addReportCustomerHierarchyNameTextBoxLayout
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);

		GtnUIFrameworkLayoutConfig reportLandingScreenCustomerHierarchyNameMainLayout = new GtnUIFrameworkLayoutConfig();
		reportLandingScreenCustomerHierarchyNameMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		addReportCustomerHierarchyNameTextBoxLayout
				.setGtnLayoutConfig(reportLandingScreenCustomerHierarchyNameMainLayout);

		GtnUIFrameworkComponentConfig reportCustomerHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportCustomerHierarchyNameTextBox.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_NAME_TEXTBOX);
		reportCustomerHierarchyNameTextBox.setComponentName("Hierarchy Name:    ");
		reportCustomerHierarchyNameTextBox.addComponentStyle("stpl-margin-left-25");
		reportCustomerHierarchyNameTextBox.setComponentHight("100%");
		reportCustomerHierarchyNameTextBox.setAddToParent(true);
		reportCustomerHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		reportCustomerHierarchyNameTextBox.setDefaultFocus(true);
		reportCustomerHierarchyNameTextBox.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "reportCustomerHierarchyNameTextBoxVerticalLayout");

		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();

		reportCustomerHierarchyNameTextBox.setGtnTextBoxConfig(textBoxConfig);

		GtnUIFrameworkValidationConfig hierarchyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		hierarchyNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		hierarchyNameValidationConfig.setAttachRegxValidatior(true);
		hierarchyNameValidationConfig.setFormatString(GtnFrameworkRegexStringConstants.ACCEPT_MIN_1_MAX_200_CHARACTER);
		hierarchyNameValidationConfig.setRegxValidationMessage("Hierarchy Name Should be less than 200 Characters");
		reportCustomerHierarchyNameTextBox.setGtnUIFrameworkValidationConfig(hierarchyNameValidationConfig);
		componentList.add(addReportCustomerHierarchyNameTextBoxLayout);
		componentList.add(reportCustomerHierarchyNameTextBox);
	}

	private void reportCustomerHierarchySearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchySearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchySearchAndResetLayout.setAddToParent(true);
		reportCustomerHierarchySearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);

		reportCustomerHierarchySearchAndResetLayout.setMargin(true);
		reportCustomerHierarchySearchAndResetLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		GtnUIFrameworkLayoutConfig reportCustomerHierarchySearchAndResetConfig = new GtnUIFrameworkLayoutConfig();

		reportCustomerHierarchySearchAndResetLayout.setGtnLayoutConfig(reportCustomerHierarchySearchAndResetConfig);
		componentList.add(reportCustomerHierarchySearchAndResetLayout);

		GtnUIFrameworkComponentConfig reportCustomerHierarchySearchButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchySearchButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySearchButton");
		reportCustomerHierarchySearchButton.setComponentName("SEARCH");
		reportCustomerHierarchySearchButton
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		reportCustomerHierarchySearchButton.addComponentStyle(
				GtnFrameworkReportStringConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);

		reportCustomerHierarchySearchButton
				.setCustomReference(GtnFrameworkReportStringConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);
		reportCustomerHierarchySearchButton.setAddToParent(true);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);

		validationActionConfig
				.setFieldValues(Arrays.asList("reportCustomerHierarchyLookup_reportCustomerHierarchyNameTextBox"));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("No Results Found");
		alertParamsList.add("There are no Hierarchies that match the search criteria.");

		alertActionConfig.setActionParameterList(alertParamsList);
		Object validationType = GtnUIFrameworkValidationType.OR;
		validationActionConfig.setActionParameterList(Arrays.asList(validationType, Arrays.asList(alertActionConfig)));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataTableActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE }));
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] {
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_TYPE_OPTIONGROUP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_NAME_TEXTBOX }));
		actionConfigList.add(loadDataTableActionConfig);
		reportCustomerHierarchySearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(reportCustomerHierarchySearchButton);

		GtnUIFrameworkComponentConfig reportCustomerHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResetButton");
		reportCustomerHierarchyResetButton.setComponentName("RESET");
		reportCustomerHierarchyResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		reportCustomerHierarchyResetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		params.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_MESSAGE);
		params.add(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_TYPE_OPTIONGROUP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_NAME_TEXTBOX));
		params.add(Arrays.asList(new Object[] { "Primary", GtnFrameworkCommonStringConstants.STRING_EMPTY }));
		resetActionConfig.setActionParameterList(params);
		resetActionConfigList.add(resetActionConfig);
		reportCustomerHierarchyResetButton.setGtnUIFrameWorkActionConfigList(resetActionConfigList);
		componentList.add(reportCustomerHierarchyResetButton);
	}

	private void reportCustomerHierarchyResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyResultsPanel = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportCustomerHierarchyResultsPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultsPanel");
		reportCustomerHierarchyResultsPanel.setComponentName("RESULTS");
		reportCustomerHierarchyResultsPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportCustomerHierarchyResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchyResultsPanel.setAddToParent(true);
		reportCustomerHierarchyResultsPanel.addComponentStyle("stpl-margin-left-10");
		reportCustomerHierarchyResultsPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(reportCustomerHierarchyResultsPanel);
		addCustomerHierarchyPagedTableComponent(componentList, namespace);

	}

	private void addCustomerHierarchyPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig customerHierarchyPagedTableComponent = new GtnUIFrameworkComponentConfig();
		customerHierarchyPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		customerHierarchyPagedTableComponent.setMargin(false);
		customerHierarchyPagedTableComponent.setSpacing(false);
		customerHierarchyPagedTableComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE);
		customerHierarchyPagedTableComponent.setComponentName("Results");
		customerHierarchyPagedTableComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultsPanel");
		customerHierarchyPagedTableComponent.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		customerHierarchyPagedTableComponent.setComponentWidth("100%");
		customerHierarchyPagedTableComponent.setComponentStyle(tableStyle);
		customerHierarchyPagedTableComponent.setModuleName("report");
		customerHierarchyPagedTableComponent.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(customerHierarchyPagedTableComponent);
		GtnUIFrameworkPagedTableConfig customerHierarchyPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		customerHierarchyPagedTableConfig.setEditable(false);
		customerHierarchyPagedTableConfig.setFilterBar(true);
		customerHierarchyPagedTableConfig.setPageLength(10);
		customerHierarchyPagedTableConfig.setItemPerPage(10);
		customerHierarchyPagedTableConfig.setSelectable(true);
		customerHierarchyPagedTableConfig.setPaginationOff(true);
		customerHierarchyPagedTableConfig.setSinkItemPerPageWithPageLength(false);

		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		alertAction.addActionParameter("No Results Found");
		alertAction.addActionParameter("There are no Hierarchies that match the search criteria.");
		customerHierarchyPagedTableConfig.setRecordTypeManageActionConfig(alertAction);

		customerHierarchyPagedTableConfig.setTableColumnDataType(
				GtnFrameworkReportStringConstants.getReportCustomerHierarchyTableColumnsDataType());
		customerHierarchyPagedTableConfig.setColumnHeaders(
				Arrays.asList(GtnFrameworkReportStringConstants.getReportCustomerHierarchyTableColumnsVisibleHeader()));
		customerHierarchyPagedTableConfig.setTableColumnMappingId(
				GtnFrameworkReportStringConstants.getReportCustomerHierarchyTableColumnsMappingId());

		customerHierarchyPagedTableConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_CUSTOMERHIERARCHY_SEARCHSERVICE);
		customerHierarchyPagedTableConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_CUSTOMERHIERARCHY_SEARCHSERVICE);
		customerHierarchyPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		customerHierarchyPagedTableConfig.setSelectionListener(true);

		List<GtnUIFrameWorkActionConfig> itemClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectButtonEnableActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectButtonEnableActionConfig
				.addActionParameter(GtnFrameworkSelectButtonEnableActionInHierarchyLookup.class.getName());
		selectButtonEnableActionConfig.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySelectButton");
		selectButtonEnableActionConfig.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE);
		itemClickActionConfigList.add(selectButtonEnableActionConfig);
		customerHierarchyPagedTableConfig.setItemClickActionConfigList(itemClickActionConfigList);
		customerHierarchyPagedTableComponent.setGtnPagedTableConfig(customerHierarchyPagedTableConfig);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customerHierarchyustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		String[] custComboboxIds = new String[1];
		String[] custComboBoxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig custHierarchyFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			custHierarchyFilterConfig.setPropertId(columnPropertyIds[i]);
			custHierarchyFilterConfig.setGtnComponentType(componentType[i]);
			if ((startIndex < custComboboxIds.length) && columnPropertyIds[i].equals(custComboboxIds[startIndex])) {
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

	private void reportCustomerHierarchyControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyControlPopUpConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportCustomerHierarchyControlLayout = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchyControlPopUpConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchyControlPopUpConfig.setAddToParent(true);
		reportCustomerHierarchyControlPopUpConfig.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportCustomerHierarchyControlPopUpConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportCustomerHierarchyControlPopUpConfig.setGtnLayoutConfig(reportCustomerHierarchyControlLayout);
		componentList.add(reportCustomerHierarchyControlPopUpConfig);

		GtnUIFrameworkComponentConfig reportCustomerHierarchySelectButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchySelectButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySelectButton");
		reportCustomerHierarchySelectButton.setComponentName("SELECT");
		reportCustomerHierarchySelectButton
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportCustomerHierarchySelectButton.setAddToParent(true);
		reportCustomerHierarchySelectButton.addDependentComponent("reportLandingScreen_customerSelectionRelationship");
		reportCustomerHierarchySelectButton.setEnable(false);
		reportCustomerHierarchySelectButton.addComponentStyle(
				GtnFrameworkReportStringConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);
		reportCustomerHierarchySelectButton
				.setCustomReference(GtnFrameworkReportStringConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);
		componentList.add(reportCustomerHierarchySelectButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig reportCustomerHierarchySelectAction = new GtnUIFrameWorkActionConfig();
		reportCustomerHierarchySelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE);
		actionParameter.add("reportLandingScreen_customerHierarchy");
		actionParameter.add(Arrays.asList("custHierarchyLookupHierName"));
		actionParameter.add(Arrays.asList("reportLandingScreen_customerHierarchy"));
		reportCustomerHierarchySelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(reportCustomerHierarchySelectAction);

		GtnUIFrameWorkActionConfig reportCustomHierarchyClosepopup = new GtnUIFrameWorkActionConfig();
		reportCustomHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportCustomHierarchyClosepopup.addActionParameter("customerHierarchyLookup");
		actionConfigList.add(reportCustomHierarchyClosepopup);

		reportCustomerHierarchySelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig reportCustomerHierarchyCancelButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchyCancelButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyCancelButton");
		reportCustomerHierarchyCancelButton.setComponentName("CANCEL");
		reportCustomerHierarchyCancelButton
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportCustomerHierarchyCancelButton.setAddToParent(true);
		reportCustomerHierarchyCancelButton.addComponentStyle(
				GtnFrameworkReportStringConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);
		reportCustomerHierarchyCancelButton
				.setCustomReference(GtnFrameworkReportStringConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);
		reportCustomerHierarchyCancelButton.addGtnUIFrameWorkActionConfig(reportCustomHierarchyClosepopup);
		componentList.add(reportCustomerHierarchyCancelButton);

		GtnUIFrameworkComponentConfig reportCustomerHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResetButton");
		reportCustomerHierarchyResetButton.setComponentName("RESET");
		reportCustomerHierarchyResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportCustomerHierarchyResetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> confList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> paramList = new ArrayList<>();
		paramList.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		paramList.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_TABLE_MESSAGE);

		paramList.add(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE));
		paramList.add(Arrays.asList(new Object[] { "" }));

		resetActionConfig.setActionParameterList(paramList);
		confList.add(resetActionConfig);
		reportCustomerHierarchyResetButton.setGtnUIFrameWorkActionConfigList(confList);

		componentList.add(reportCustomerHierarchyResetButton);
	}
}
