package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkReportCustomertHierarchyLookUp {

	public GtnUIFrameworkViewConfig getCustHierarchyLookUpView(String namespace) {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Customer Hierarchy LookUp");
		view.setViewId("customerHierarchyLookup");
		view.setDefaultView(false);
		addreportCustomertHierarchyComponentList(view, namespace);
		return view;
	}

	private void addreportCustomertHierarchyComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addreportCustomerHierarchyRootLayout(componentList, namespace);

	}

	private void addreportCustomerHierarchyRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyConfig = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchyConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyConfig");
		reportCustomerHierarchyConfig.setAddToParent(false);
		reportCustomerHierarchyConfig.setSpacing(Boolean.TRUE);
		reportCustomerHierarchyConfig.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig reportCustomerHierarchyLayout = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchyLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportCustomerHierarchyConfig.setGtnLayoutConfig(reportCustomerHierarchyLayout);
		componentList.add(reportCustomerHierarchyConfig);
		getReportCustomerHierarchyComponentForPopUp(componentList, namespace);
	}

	public void getReportCustomerHierarchyComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyGtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportCustomerHierarchyGtnConfig = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchyGtnConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportCustomerHierarchyGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchyGtnLayout.setGtnLayoutConfig(reportCustomerHierarchyGtnConfig);
		reportCustomerHierarchyGtnLayout.setAddToParent(true);

		reportCustomerHierarchyGtnLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportCustomerHierarchyGtnLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyConfig");
		reportCustomerHierarchyGtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchyGtnLayout.setSpacing(Boolean.TRUE);
		reportCustomerHierarchyGtnLayout.setMargin(Boolean.TRUE);
		componentList.add(reportCustomerHierarchyGtnLayout);
		
		reportCustomerHierarchySearchCriteriaPanel(componentList, namespace);
		reportCustomerHierarchySearchAndResetButtonLayout(componentList, namespace);
		reportCustomerHierarchyResultsPanel(componentList, namespace);
		reportCustomerHierarchyControlPopUpButtonLayout(componentList, namespace);
	}

	private void reportCustomerHierarchySearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchySearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportCustomerHierarchySearchCriteriaPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySearchCriteriaPanel");
		reportCustomerHierarchySearchCriteriaPanel.setComponentName("Search Criteria");
		reportCustomerHierarchySearchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportCustomerHierarchySearchCriteriaPanel.setMargin(Boolean.TRUE);
		reportCustomerHierarchySearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchySearchCriteriaPanel.setAddToParent(true);
		componentList.add(reportCustomerHierarchySearchCriteriaPanel);
		reportCustomerHierarchySearchCriteriaLayout(componentList, namespace);
	}

	private void reportCustomerHierarchySearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchySearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchySearchCriteriaLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);
		reportCustomerHierarchySearchCriteriaLayout.setComponentName("Search Criteria Layout");
		reportCustomerHierarchySearchCriteriaLayout.setAddToParent(true);
		reportCustomerHierarchySearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchySearchCriteriaLayout
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig reportCustomerHierarchySearchCriteriaConfig = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchySearchCriteriaLayout.setGtnLayoutConfig(reportCustomerHierarchySearchCriteriaConfig);
		reportCustomerHierarchySearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportCustomerHierarchySearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(reportCustomerHierarchySearchCriteriaLayout);
		
		addReportCustomerHierarchyTypeOptionGroup(componentList, namespace);
		addReportCustomerHierarchyNameTextBox(componentList, namespace);
	}

	private void addReportCustomerHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		reportCustomerHierarchyTypeOptionGroup.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyTypeOptionGroup");
		reportCustomerHierarchyTypeOptionGroup.setComponentName("Hierarchy Type");
		reportCustomerHierarchyTypeOptionGroup.setAddToParent(true);
		reportCustomerHierarchyTypeOptionGroup.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);
		GtnUIFrameworkOptionGroupConfig reportCustomerHierarchyTypeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		reportCustomerHierarchyTypeOptionGroupConfig.setItemValues(Arrays.asList("Primary", "Secondary"));
		reportCustomerHierarchyTypeOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		reportCustomerHierarchyTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		reportCustomerHierarchyTypeOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		reportCustomerHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(reportCustomerHierarchyTypeOptionGroupConfig);
		componentList.add(reportCustomerHierarchyTypeOptionGroup);
	}

	private void addReportCustomerHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		reportCustomerHierarchyNameTextBox.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyNameTextBox");
		reportCustomerHierarchyNameTextBox.setComponentName("Hierarchy Name");
		reportCustomerHierarchyNameTextBox.setAddToParent(true);
		reportCustomerHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		reportCustomerHierarchyNameTextBox.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_CRITERIA_LAYOUT);
		componentList.add(reportCustomerHierarchyNameTextBox);
	}

	private void reportCustomerHierarchySearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchySearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchySearchAndResetLayout.setAddToParent(true);
		reportCustomerHierarchySearchAndResetLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportCustomerHierarchySearchAndResetLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		reportCustomerHierarchySearchAndResetLayout.setSpacing(Boolean.TRUE);
		GtnUIFrameworkLayoutConfig reportCustomerHierarchySearchAndResetConfig = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchySearchAndResetLayout.setGtnLayoutConfig(reportCustomerHierarchySearchAndResetConfig);
		componentList.add(reportCustomerHierarchySearchAndResetLayout);

		GtnUIFrameworkComponentConfig reportCustomerHierarchySearchButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchySearchButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySearchButton");
		reportCustomerHierarchySearchButton.setComponentName("SEARCH");
		reportCustomerHierarchySearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		reportCustomerHierarchySearchButton.setAddToParent(true);

		componentList.add(reportCustomerHierarchySearchButton);

		GtnUIFrameworkComponentConfig reportCustomerHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResetButton");
		reportCustomerHierarchyResetButton.setComponentName("RESET");
		reportCustomerHierarchyResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_CUSTOMER_HIERARCHY_SEARCH_AND_RESULT_LAYOUT);
		reportCustomerHierarchyResetButton.setAddToParent(true);

		componentList.add(reportCustomerHierarchyResetButton);
	}

	private void reportCustomerHierarchyResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyResultsPanel = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportCustomerHierarchyResultsPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultsPanel");
		reportCustomerHierarchyResultsPanel.setComponentName("Results");
		reportCustomerHierarchyResultsPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportCustomerHierarchyResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchyResultsPanel.setAddToParent(true);
		componentList.add(reportCustomerHierarchyResultsPanel);
		reportCustomerHierarchyResultLayout(componentList, namespace);

	}

	private void reportCustomerHierarchyResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig reportCustomerHierarchyResultLayout = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchyResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig reportCustomerHierarchyResultConfig = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchyResultConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultConfig");

		reportCustomerHierarchyResultConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultsPanel");
		reportCustomerHierarchyResultConfig.setAddToParent(true);
		reportCustomerHierarchyResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchyResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportCustomerHierarchyResultConfig.setGtnLayoutConfig(reportCustomerHierarchyResultLayout);
		componentList.add(reportCustomerHierarchyResultConfig);
		addCustomerHierarchyPagedTableComponent(componentList, namespace);
	}

	private void addCustomerHierarchyPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig customerHierarchyPagedTableComponent = new GtnUIFrameworkComponentConfig();
		customerHierarchyPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		customerHierarchyPagedTableComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.CUSTOMER_HIERARCHY_SEARCH_RESULT_TABLE);
		customerHierarchyPagedTableComponent.setComponentName("Results");
		customerHierarchyPagedTableComponent
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResultConfig");
		customerHierarchyPagedTableComponent.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		customerHierarchyPagedTableComponent.setComponentWidth("100%");
		customerHierarchyPagedTableComponent.setComponentStyle(tableStyle);

		componentList.add(customerHierarchyPagedTableComponent);
		GtnUIFrameworkPagedTableConfig customerHierarchyPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		customerHierarchyPagedTableConfig.setEditable(false);
		customerHierarchyPagedTableConfig.setFilterBar(true);
		customerHierarchyPagedTableConfig.setPageLength(10);
		customerHierarchyPagedTableConfig.setItemPerPage(10);
		customerHierarchyPagedTableConfig.setSelectable(true);
		customerHierarchyPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		customerHierarchyPagedTableConfig
				.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING,
						GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
						GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE });
		customerHierarchyPagedTableConfig.setTableVisibleHeader(
				new String[] { "HierarchyName", "Highest Level", "Lowest Level", "Created Date", "Modified Date" });
		customerHierarchyPagedTableConfig.setTableColumnMappingId(
				new Object[] { "hierName", "highestLevel", "lowestLevel", "createdDate", "modifiedDate" });

		customerHierarchyPagedTableComponent.setGtnPagedTableConfig(customerHierarchyPagedTableConfig);
	}

	private void reportCustomerHierarchyControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportCustomerHierarchyControlPopUpConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportCustomerHierarchyControlLayout = new GtnUIFrameworkLayoutConfig();
		reportCustomerHierarchyControlPopUpConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCustomerHierarchyControlPopUpConfig.setAddToParent(true);
		reportCustomerHierarchyControlPopUpConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportCustomerHierarchyControlPopUpConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportCustomerHierarchyControlPopUpConfig.setSpacing(Boolean.TRUE);
		reportCustomerHierarchyControlPopUpConfig.setGtnLayoutConfig(reportCustomerHierarchyControlLayout);
		componentList.add(reportCustomerHierarchyControlPopUpConfig);

		GtnUIFrameworkComponentConfig reportCustomerHierarchySelectButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchySelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchySelectButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchySelectButton");
		reportCustomerHierarchySelectButton.setComponentName("SELECT");
		reportCustomerHierarchySelectButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportCustomerHierarchySelectButton.setAddToParent(true);

		componentList.add(reportCustomerHierarchySelectButton);

		GtnUIFrameworkComponentConfig reportCustomerHierarchyCancelButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchyCancelButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyCancelButton");
		reportCustomerHierarchyCancelButton.setComponentName("CANCEL");
		reportCustomerHierarchyCancelButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportCustomerHierarchyCancelButton.setAddToParent(true);

		componentList.add(reportCustomerHierarchyCancelButton);

		GtnUIFrameworkComponentConfig reportCustomerHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		reportCustomerHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportCustomerHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportCustomerHierarchyResetButton");
		reportCustomerHierarchyResetButton.setComponentName("RESET");
		reportCustomerHierarchyResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		reportCustomerHierarchyResetButton.setAddToParent(true);

		componentList.add(reportCustomerHierarchyResetButton);
	}
}
