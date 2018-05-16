package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportDSCustomertHierarchyLookUp {

	public GtnUIFrameworkViewConfig getCustHierarchyLookUpView(String namespace) {
		GtnUIFrameworkViewConfig reportDSCustomertHierarchyView = new GtnUIFrameworkViewConfig();
		reportDSCustomertHierarchyView.setViewName("Customer Hierarchy LookUp");
		reportDSCustomertHierarchyView.setViewId("dataSelectionTab_customerHierarchyLookup");
		reportDSCustomertHierarchyView.setDefaultView(false);
		addreportCustomertHierarchyComponentList(reportDSCustomertHierarchyView, namespace);
		return reportDSCustomertHierarchyView;
	}

	private void addreportCustomertHierarchyComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> reportDSCustomertHierarchyComponentList = new ArrayList<>();
		view.setGtnComponentList(reportDSCustomertHierarchyComponentList);
		addreportCustomerHierarchyRootLayout(reportDSCustomertHierarchyComponentList, namespace);

	}

	private void addreportCustomerHierarchyRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyConfig = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchyConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDsCustomerHierarchyConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyConfig");
		reportDsCustomerHierarchyConfig.setAddToParent(false);
		reportDsCustomerHierarchyConfig.setSpacing(true);
		reportDsCustomerHierarchyConfig.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig reportDsCustomerHierarchyLayout = new GtnUIFrameworkLayoutConfig();
		reportDsCustomerHierarchyLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportDsCustomerHierarchyConfig.setGtnLayoutConfig(reportDsCustomerHierarchyLayout);
		componentList.add(reportDsCustomerHierarchyConfig);
		getReportCustomerHierarchyComponentForPopUp(componentList, namespace);
	}

	public void getReportCustomerHierarchyComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyGtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportDsCustomerHierarchyGtnConfig = new GtnUIFrameworkLayoutConfig();
		reportDsCustomerHierarchyGtnConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportDsCustomerHierarchyGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDsCustomerHierarchyGtnLayout.setGtnLayoutConfig(reportDsCustomerHierarchyGtnConfig);
		reportDsCustomerHierarchyGtnLayout.setAddToParent(true);

		reportDsCustomerHierarchyGtnLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportDsCustomerHierarchyGtnLayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyConfig");
		reportDsCustomerHierarchyGtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDsCustomerHierarchyGtnLayout.setSpacing(true);
		reportDsCustomerHierarchyGtnLayout.setMargin(true);
		componentList.add(reportDsCustomerHierarchyGtnLayout);

		reportCustomerHierarchySearchCriteriaPanel(componentList, namespace);
		reportCustomerHierarchySearchAndResetButtonLayout(componentList, namespace);
		reportCustomerHierarchyResultsPanel(componentList, namespace);
		reportCustomerHierarchyControlPopUpButtonLayout(componentList, namespace);
	}

	private void reportCustomerHierarchySearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsCustomerHierarchySearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchySearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportDsCustomerHierarchySearchCriteriaPanel.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySearchCriteriaPanel");
		reportDsCustomerHierarchySearchCriteriaPanel.setComponentName("Search Criteria");
		reportDsCustomerHierarchySearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportDsCustomerHierarchySearchCriteriaPanel.setMargin(true);
		reportDsCustomerHierarchySearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDsCustomerHierarchySearchCriteriaPanel.setAddToParent(true);
		componentList.add(reportDsCustomerHierarchySearchCriteriaPanel);
		reportCustomerHierarchySearchCriteriaLayout(componentList, namespace);
	}

	private void reportCustomerHierarchySearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsCustomerHierarchySearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchySearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDsCustomerHierarchySearchCriteriaLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);
		reportDsCustomerHierarchySearchCriteriaLayout.setComponentName("Search Criteria Layout");
		reportDsCustomerHierarchySearchCriteriaLayout.setAddToParent(true);
		reportDsCustomerHierarchySearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDsCustomerHierarchySearchCriteriaLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig reportCustomerDsHierarchySearchCriteriaConfig = new GtnUIFrameworkLayoutConfig();
		reportDsCustomerHierarchySearchCriteriaLayout.setGtnLayoutConfig(reportCustomerDsHierarchySearchCriteriaConfig);
		reportDsCustomerHierarchySearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportDsCustomerHierarchySearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(reportDsCustomerHierarchySearchCriteriaLayout);

		addReportCustomerHierarchyTypeOptionGroup(componentList, namespace);
		addReportCustomerHierarchyNameTextBox(componentList, namespace);
	}

	private void addReportCustomerHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		reportDsCustomerHierarchyTypeOptionGroup.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_TYPE_OPTIONGROUP);
		reportDsCustomerHierarchyTypeOptionGroup.setComponentName("Hierarchy Type");
		reportDsCustomerHierarchyTypeOptionGroup.setAddToParent(true);
		reportDsCustomerHierarchyTypeOptionGroup
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);
		GtnUIFrameworkOptionGroupConfig reportDsCustomerHierarchyTypeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		reportDsCustomerHierarchyTypeOptionGroupConfig.setItemValues(Arrays.asList("Primary", "Secondary"));
		reportDsCustomerHierarchyTypeOptionGroupConfig.setValuesFromService(false);
		reportDsCustomerHierarchyTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		reportDsCustomerHierarchyTypeOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		reportDsCustomerHierarchyTypeOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(reportDsCustomerHierarchyTypeOptionGroupConfig);
		componentList.add(reportDsCustomerHierarchyTypeOptionGroup);
	}

	private void addReportCustomerHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportDsCustomerHierarchyNameTextBox.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_NAME_TEXTBOX);
		reportDsCustomerHierarchyNameTextBox.setComponentName("Hierarchy Name");
		reportDsCustomerHierarchyNameTextBox.setAddToParent(true);
		reportDsCustomerHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		reportDsCustomerHierarchyNameTextBox.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);
		
		GtnUIFrameworkValidationConfig dsHierarchyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		dsHierarchyNameValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		reportDsCustomerHierarchyNameTextBox.setGtnUIFrameworkValidationConfig(dsHierarchyNameValidationConfig);
		
		componentList.add(reportDsCustomerHierarchyNameTextBox);
	}

	private void reportCustomerHierarchySearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsCustomerHierarchySearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchySearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDsCustomerHierarchySearchAndResetLayout.setAddToParent(true);
		reportDsCustomerHierarchySearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportDsCustomerHierarchySearchAndResetLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		reportDsCustomerHierarchySearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig reportCustomerHierarchySearchAndResetConfig = new GtnUIFrameworkLayoutConfig();
		reportDsCustomerHierarchySearchAndResetLayout.setGtnLayoutConfig(reportCustomerHierarchySearchAndResetConfig);
		componentList.add(reportDsCustomerHierarchySearchAndResetLayout);

		GtnUIFrameworkComponentConfig reportDsCustomerHierarchySearchButton = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchySearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDsCustomerHierarchySearchButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySearchButton");
		reportDsCustomerHierarchySearchButton.setComponentName("SEARCH");
		reportDsCustomerHierarchySearchButton
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		reportDsCustomerHierarchySearchButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> dsActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDsDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDsDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDsDataTableActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE }));
		loadDsDataTableActionConfig.setFieldValues(Arrays.asList(new String[] {
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_TYPE_OPTIONGROUP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_NAME_TEXTBOX }));
		dsActionConfigList.add(loadDsDataTableActionConfig);
		reportDsCustomerHierarchySearchButton.setGtnUIFrameWorkActionConfigList(dsActionConfigList);

		componentList.add(reportDsCustomerHierarchySearchButton);

		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDsCustomerHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResetButton");
		reportDsCustomerHierarchyResetButton.setComponentName("RESET");
		reportDsCustomerHierarchyResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		reportDsCustomerHierarchyResetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> resetDsActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetDsActionConfig = new GtnUIFrameWorkActionConfig();
		resetDsActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> dsParams = new ArrayList<>();
		dsParams.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		dsParams.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_MESSAGE);
		dsParams.add(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_TYPE_OPTIONGROUP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_NAME_TEXTBOX));
		dsParams.add(Arrays.asList(new Object[] { "Primary", "" }));
		resetDsActionConfig.setActionParameterList(dsParams);
		resetDsActionConfigList.add(resetDsActionConfig);
		reportDsCustomerHierarchyResetButton.setGtnUIFrameWorkActionConfigList(resetDsActionConfigList);
		componentList.add(reportDsCustomerHierarchyResetButton);
	}

	private void reportCustomerHierarchyResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyResultsPanel = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchyResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportDsCustomerHierarchyResultsPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultsPanel");
		reportDsCustomerHierarchyResultsPanel.setComponentName("Results");
		reportDsCustomerHierarchyResultsPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportDsCustomerHierarchyResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDsCustomerHierarchyResultsPanel.setAddToParent(true);
		componentList.add(reportDsCustomerHierarchyResultsPanel);
		reportCustomerHierarchyResultLayout(componentList, namespace);

	}

	private void reportCustomerHierarchyResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig reportDsCustomerHierarchyResultLayout = new GtnUIFrameworkLayoutConfig();
		reportDsCustomerHierarchyResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyResultConfig = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchyResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDsCustomerHierarchyResultConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultConfig");

		reportDsCustomerHierarchyResultConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultsPanel");
		reportDsCustomerHierarchyResultConfig.setAddToParent(true);
		reportDsCustomerHierarchyResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDsCustomerHierarchyResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDsCustomerHierarchyResultConfig.setGtnLayoutConfig(reportDsCustomerHierarchyResultLayout);
		componentList.add(reportDsCustomerHierarchyResultConfig);
		addCustomerHierarchyPagedTableComponent(componentList, namespace);
	}

	private void addCustomerHierarchyPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsCustomerHierarchyPagedTableComponent = new GtnUIFrameworkComponentConfig();
		dsCustomerHierarchyPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		dsCustomerHierarchyPagedTableComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE);
		dsCustomerHierarchyPagedTableComponent.setComponentName("Results");
		dsCustomerHierarchyPagedTableComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultConfig");
		dsCustomerHierarchyPagedTableComponent.setAddToParent(true);
		List<String> dsTableStyle = new ArrayList<>();
		dsTableStyle.add("filterbar");
		dsTableStyle.add("v-has-width");
		dsTableStyle.add("v-table-filterbar");
		dsTableStyle.add("table-header-normal");
		dsCustomerHierarchyPagedTableComponent.setComponentWidth("100%");
		dsCustomerHierarchyPagedTableComponent.setComponentStyle(dsTableStyle);
		dsCustomerHierarchyPagedTableComponent.setModuleName("report");
		
		componentList.add(dsCustomerHierarchyPagedTableComponent);
		GtnUIFrameworkPagedTableConfig dsCustomerHierarchyPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		dsCustomerHierarchyPagedTableConfig.setEditable(false);
		dsCustomerHierarchyPagedTableConfig.setFilterBar(true);
		dsCustomerHierarchyPagedTableConfig.setPageLength(10);
		dsCustomerHierarchyPagedTableConfig.setItemPerPage(10);
		dsCustomerHierarchyPagedTableConfig.setSelectable(true);
		dsCustomerHierarchyPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		dsCustomerHierarchyPagedTableConfig.setTableColumnDataType(
				GtnFrameworkReportStringConstants.getReportCustomerHierarchyTableColumnsDataType());
		dsCustomerHierarchyPagedTableConfig.setColumnHeaders(Arrays.asList(
				GtnFrameworkReportStringConstants.getReportCustomerHierarchyTableColumnsVisibleHeader()));
		dsCustomerHierarchyPagedTableConfig.setTableColumnMappingId(
				GtnFrameworkReportStringConstants.getReportCustomerHierarchyTableColumnsMappingId());

		dsCustomerHierarchyPagedTableConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_CUSTOMERHIERARCHY_SEARCHSERVICE);
		dsCustomerHierarchyPagedTableConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_CUSTOMERHIERARCHY_SEARCHSERVICE);
		dsCustomerHierarchyPagedTableComponent.setGtnPagedTableConfig(dsCustomerHierarchyPagedTableConfig);
	}
	

	private void reportCustomerHierarchyControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyControlPopUpConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportDsCustomerHierarchyControlLayout = new GtnUIFrameworkLayoutConfig();
		reportDsCustomerHierarchyControlPopUpConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDsCustomerHierarchyControlPopUpConfig.setAddToParent(true);
		reportDsCustomerHierarchyControlPopUpConfig.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportDsCustomerHierarchyControlPopUpConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportDsCustomerHierarchyControlPopUpConfig.setSpacing(true);
		reportDsCustomerHierarchyControlPopUpConfig.setGtnLayoutConfig(reportDsCustomerHierarchyControlLayout);
		componentList.add(reportDsCustomerHierarchyControlPopUpConfig);

		GtnUIFrameworkComponentConfig reportDsCustomerHierarchySelectButton = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchySelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDsCustomerHierarchySelectButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySelectButton");
		reportDsCustomerHierarchySelectButton.setComponentName("SELECT");
		reportDsCustomerHierarchySelectButton
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportDsCustomerHierarchySelectButton.setAddToParent(true);
		reportDsCustomerHierarchySelectButton.addDependentComponent("dataSelectionTab_customerSelectionRelationship");
		reportDsCustomerHierarchySelectButton.addDependentComponent("dataSelectionTab_customerSelectionLevel");
		componentList.add(reportDsCustomerHierarchySelectButton);

		List<GtnUIFrameWorkActionConfig> dsActionConfigList = new ArrayList<>();

		
		GtnUIFrameWorkActionConfig reportDsCustomerHierarchySelectAction = new GtnUIFrameWorkActionConfig();
		reportDsCustomerHierarchySelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> dsActionParameter = new ArrayList<>();
		dsActionParameter.add("reportDsCustomerHierarchyLookup_customerHierarchySearchResultTable");
		dsActionParameter.add("dataSelectionTab_customerHierarchy");
		dsActionParameter.add(Arrays.asList("custHierarchyLookupHierName"));
		dsActionParameter.add(Arrays.asList("dataSelectionTab_customerHierarchy"));
		reportDsCustomerHierarchySelectAction.setActionParameterList(dsActionParameter);
		dsActionConfigList.add(reportDsCustomerHierarchySelectAction);
		
		GtnUIFrameWorkActionConfig reportDsCustomHierarchyClosepopup = new GtnUIFrameWorkActionConfig();
		reportDsCustomHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportDsCustomHierarchyClosepopup.addActionParameter("dataSelectionTab_CustomerHierarchyLookup");
		dsActionConfigList.add(reportDsCustomHierarchyClosepopup);

		reportDsCustomerHierarchySelectButton.setGtnUIFrameWorkActionConfigList(dsActionConfigList);

		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyCancelButton = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchyCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDsCustomerHierarchyCancelButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyCancelButton");
		reportDsCustomerHierarchyCancelButton.setComponentName("CANCEL");
		reportDsCustomerHierarchyCancelButton
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportDsCustomerHierarchyCancelButton.setAddToParent(true);

		reportDsCustomerHierarchyCancelButton.addGtnUIFrameWorkActionConfig(reportDsCustomHierarchyClosepopup);
		componentList.add(reportDsCustomerHierarchyCancelButton);

		GtnUIFrameworkComponentConfig reportDsCustomerHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		reportDsCustomerHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDsCustomerHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResetButton");
		reportDsCustomerHierarchyResetButton.setComponentName("RESET");
		reportDsCustomerHierarchyResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportDsCustomerHierarchyResetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> dsConfList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dsResetActionConfig = new GtnUIFrameWorkActionConfig();
		dsResetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> dSparamList = new ArrayList<>();
		dSparamList.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		dSparamList.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_TABLE_MESSAGE);

		dSparamList.add(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE));
		dSparamList.add(Arrays.asList(new Object[] { "" }));

		dsResetActionConfig.setActionParameterList(dSparamList);
		dsConfList.add(dsResetActionConfig);
		reportDsCustomerHierarchyResetButton.setGtnUIFrameWorkActionConfigList(dsConfList);

		componentList.add(reportDsCustomerHierarchyResetButton);
	}
}
