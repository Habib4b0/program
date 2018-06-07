package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportPublicViewSearchLookUp {
	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getPublicViewLookUpView(String namespace) {
		GtnUIFrameworkViewConfig publicViewLookUpView = new GtnUIFrameworkViewConfig();
		publicViewLookUpView.setViewName("Public View");
		publicViewLookUpView.setViewId(GtnFrameworkCommonConstants.PUBLIC_VIEW_SEARCH_LOOKUP_VIEW);
		publicViewLookUpView.setDefaultView(false);
		addPublicViewLookUpComponentList(publicViewLookUpView, namespace);
		return publicViewLookUpView;
	}

	private void addPublicViewLookUpComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addPublicViewLookUpRootLayout(componentList, namespace);

	}

	private void addPublicViewLookUpRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpLayout = new GtnUIFrameworkComponentConfig();
		publicViewLookUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpLayout");
		publicViewLookUpLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpLayout.setAddToParent(false);
		publicViewLookUpLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig publicViewLookUpConfig = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		publicViewLookUpLayout.setGtnLayoutConfig(publicViewLookUpConfig);
		componentList.add(publicViewLookUpLayout);
		getpublicViewLookUpComponentForPopUp(componentList, namespace);
	}

	public void getpublicViewLookUpComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpLayoutgtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig publicViewLookUpgtnLayoutConfig = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpgtnLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		publicViewLookUpLayoutgtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpLayoutgtnLayout.setGtnLayoutConfig(publicViewLookUpgtnLayoutConfig);
		publicViewLookUpLayoutgtnLayout.setAddToParent(true);
		publicViewLookUpLayoutgtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpLayoutgtnLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookUpLayoutgtnLayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpLayout");
		publicViewLookUpLayoutgtnLayout.setSpacing(true);
		publicViewLookUpLayoutgtnLayout.setMargin(true);
		componentList.add(publicViewLookUpLayoutgtnLayout);

		addPublicViewLookUpSearchCriteriaPanel(componentList, namespace);
		addPublicViewLookUpSearchAndResetButtonLayout(componentList, namespace);
		addPublicViewLookUpResultsPanel(componentList, namespace);
		addPublicViewLookupControlPopUpButtonLayout(componentList, namespace);
	}

	private void addPublicViewLookUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		publicViewLookUpSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		publicViewLookUpSearchCriteriaPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaPanel");
		publicViewLookUpSearchCriteriaPanel.setComponentName("Search Criteria");
		publicViewLookUpSearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookUpSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpSearchCriteriaPanel.setAddToParent(true);
		componentList.add(publicViewLookUpSearchCriteriaPanel);
		publicViewLookUpSearchCriteriaLayout(componentList, namespace);
	}

	private void publicViewLookUpSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig publicViewLookUpSearchCriteriaLayout = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpSearchCriteriaLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig publicViewLookUpSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		publicViewLookUpSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpSearchCriteriaConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaConfig");
		publicViewLookUpSearchCriteriaConfig.setComponentName("Search Criteria");
		publicViewLookUpSearchCriteriaConfig.setAddToParent(true);
		publicViewLookUpSearchCriteriaConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpSearchCriteriaConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaPanel");
		publicViewLookUpSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		publicViewLookUpSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		publicViewLookUpSearchCriteriaConfig.setGtnLayoutConfig(publicViewLookUpSearchCriteriaLayout);
		componentList.add(publicViewLookUpSearchCriteriaConfig);
		addPublicViewLookUpViewNameTextBox(componentList, namespace);
	}

	private void addPublicViewLookUpViewNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpViewNameLayout = layoutsConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpViewNameLayout",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaConfig");
		componentList.add(publicViewLookUpViewNameLayout);

		GtnUIFrameworkComponentConfig publicViewLookUpViewNameTextBox = new GtnUIFrameworkComponentConfig();
		publicViewLookUpViewNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		publicViewLookUpViewNameTextBox.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpViewNameTextBox");
		publicViewLookUpViewNameTextBox.setComponentName("View Name");
		publicViewLookUpViewNameTextBox.setAddToParent(true);
		publicViewLookUpViewNameTextBox.setParentComponentId(publicViewLookUpViewNameLayout.getComponentId());
		publicViewLookUpViewNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.PUBLIC_VIEW_NAME);

		componentList.add(publicViewLookUpViewNameTextBox);

	}

	private void addPublicViewLookUpSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		publicViewLookUpSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpSearchAndResetLayout.setAddToParent(true);
		publicViewLookUpSearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookUpSearchAndResetLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		publicViewLookUpSearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(publicViewLookUpSearchAndResetLayout);

		GtnUIFrameworkComponentConfig publicViewLookUpSearchButton = new GtnUIFrameworkComponentConfig();
		publicViewLookUpSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewLookUpSearchButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewSearchButton");
		publicViewLookUpSearchButton.setComponentName("SEARCH");
		publicViewLookUpSearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		publicViewLookUpSearchButton.setAddToParent(true);

		componentList.add(publicViewLookUpSearchButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataTableActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE }));
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] {
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpViewNameTextBox" }));
		actionConfigList.add(loadDataTableActionConfig);
		publicViewLookUpSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig publicViewLookUpResetButton = new GtnUIFrameworkComponentConfig();
		publicViewLookUpResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewLookUpResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewResetCriteriaButton");
		publicViewLookUpResetButton.setComponentName("RESET");
		publicViewLookUpResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		publicViewLookUpResetButton.setAddToParent(true);
		componentList.add(publicViewLookUpResetButton);
	}

	private void addPublicViewLookUpResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpResultsPanel = new GtnUIFrameworkComponentConfig();
		publicViewLookUpResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		publicViewLookUpResultsPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultsPanel");
		publicViewLookUpResultsPanel.setComponentName("Results");
		publicViewLookUpResultsPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookUpResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpResultsPanel.setAddToParent(true);
		componentList.add(publicViewLookUpResultsPanel);
		publicViewLookUpResultLayout(componentList, namespace);

	}

	private void publicViewLookUpResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig publicViewLookUpResultLayout = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig publicViewLookUpResultConfig = new GtnUIFrameworkComponentConfig();
		publicViewLookUpResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpResultConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultConfig");

		publicViewLookUpResultConfig.setGtnLayoutConfig(publicViewLookUpResultLayout);
		publicViewLookUpResultConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultsPanel");
		publicViewLookUpResultConfig.setAddToParent(true);
		publicViewLookUpResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(publicViewLookUpResultConfig);
		addPublicViewPagedTableComponent(componentList, namespace);
	}

	private void addPublicViewPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig publicViewPagedTableComponent = new GtnUIFrameworkComponentConfig();
		publicViewPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		publicViewPagedTableComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE);
		publicViewPagedTableComponent.setComponentName("Results");
		publicViewPagedTableComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultConfig");
		publicViewPagedTableComponent.setAddToParent(true);
		publicViewPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		publicViewPagedTableComponent.setComponentStyle(tableStyle);
		publicViewPagedTableComponent.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		componentList.add(publicViewPagedTableComponent);
		GtnUIFrameworkPagedTableConfig publicViewPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		publicViewPagedTableConfig.setEditable(false);
		publicViewPagedTableConfig.setFilterBar(true);
		publicViewPagedTableConfig.setSelectable(true);
		publicViewPagedTableConfig.setPageLength(10);
		publicViewPagedTableConfig.setItemPerPage(10);

		publicViewPagedTableConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PUBLICVIEWLOOKUP_SERVICE);
		publicViewPagedTableConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PUBLICVIEWLOOKUP_SERVICE);
		publicViewPagedTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		publicViewPagedTableConfig.setColumnHeaders(Arrays.asList("View Name", "Description", "Time Period:From",
				"Time Period:To", "Company", GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_HEADER,
				GtnFrameworkCommonConstants.PRODUCT_LEVEL_HEADER, "Product Group",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER,
				GtnFrameworkCommonConstants.CREATED_BY_HEADER, GtnFrameworkCommonConstants.BUSINESS_UNIT_HEADER));
		publicViewPagedTableConfig.setTableColumnMappingId(
				new Object[] { "viewNameFilter", "viewDescriptionFilter", "fromTimePeriodFilter", "toTimePeriodFilter",
						"companyFilter", "productHierarchyFilter", "productLevelFilter", "productGroupFilter",
						"createdDateFilter", "modifiedByFilter", "createdByFilter", "businessUnitFilter" });
		publicViewPagedTableConfig.setQueryName("Public");

		publicViewPagedTableComponent.setGtnPagedTableConfig(publicViewPagedTableConfig);

	}

	private void addPublicViewLookupControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookupcontrolPopUpConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig publicViewLookupControlLayout = new GtnUIFrameworkLayoutConfig();
		publicViewLookupcontrolPopUpConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookupcontrolPopUpConfig.setAddToParent(true);
		publicViewLookupcontrolPopUpConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookupcontrolPopUpConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		publicViewLookupcontrolPopUpConfig.setSpacing(true);
		publicViewLookupcontrolPopUpConfig.setGtnLayoutConfig(publicViewLookupControlLayout);
		componentList.add(publicViewLookupcontrolPopUpConfig);

		GtnUIFrameworkComponentConfig publicViewLookupcontrolPopUpSelectButton = new GtnUIFrameworkComponentConfig();
		publicViewLookupcontrolPopUpSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewLookupcontrolPopUpSelectButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookupcontrolPopUpSelectButton");
		publicViewLookupcontrolPopUpSelectButton.setComponentName("SELECT");
		publicViewLookupcontrolPopUpSelectButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "controlPopUpButtonLayout");
		publicViewLookupcontrolPopUpSelectButton.setAddToParent(true);
		componentList.add(publicViewLookupcontrolPopUpSelectButton);
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig reportCustomerHierarchySelectAction = new GtnUIFrameWorkActionConfig();
		reportCustomerHierarchySelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE);
		actionParameter.add("reportLandingScreen_publicViews");
		actionParameter.add(Arrays.asList("viewNameFilter"));
		actionParameter.add(Arrays.asList("reportLandingScreen_publicViews"));
		reportCustomerHierarchySelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(reportCustomerHierarchySelectAction);

		GtnUIFrameWorkActionConfig reportCustomHierarchyClosepopup = new GtnUIFrameWorkActionConfig();
		reportCustomHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportCustomHierarchyClosepopup.addActionParameter(GtnFrameworkCommonConstants.PUBLIC_VIEW_SEARCH_LOOKUP_VIEW);
		actionConfigList.add(reportCustomHierarchyClosepopup);
		publicViewLookupcontrolPopUpSelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig publicViewLookupcontrolPopUpCloseButton = new GtnUIFrameworkComponentConfig();
		publicViewLookupcontrolPopUpCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewLookupcontrolPopUpCloseButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookupcontrolPopUpCloseButton");
		publicViewLookupcontrolPopUpCloseButton.setComponentName("CLOSE");
		publicViewLookupcontrolPopUpCloseButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "controlPopUpButtonLayout");
		publicViewLookupcontrolPopUpCloseButton.setAddToParent(true);

		componentList.add(publicViewLookupcontrolPopUpCloseButton);
		publicViewLookupcontrolPopUpCloseButton.addGtnUIFrameWorkActionConfig(reportCustomHierarchyClosepopup);
	}
}
