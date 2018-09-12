package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportSearchNotification;
import com.stpl.gtn.gtn2o.ui.action.GtnReportingDashboardReportProfileLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkReportConfirmedDeleteButtonAction;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportProfileLookUp {

	private static final String[] columnPropertyIds = { "viewNameFilter", "createdDateFilter", "modifiedDateFilter",
			"createdByFilter" };
	private static final GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

	public GtnUIFrameworkViewConfig getReportProfileLookupView(String namespace) {
		GtnUIFrameworkViewConfig reportProfileLookUpView = new GtnUIFrameworkViewConfig();
		reportProfileLookUpView.setViewName("Report Profile Popup ");
		reportProfileLookUpView.setViewId("reportProfileLookupView");
		reportProfileLookUpView.setDefaultView(false);
		reportProfileLookUpView.setResetAllowed(true);
		addReportProfileLookUpComponentList(reportProfileLookUpView, namespace);
		return reportProfileLookUpView;
	}

	private void addReportProfileLookUpComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> reportProfileLookUpComponentList = new ArrayList<>();
		view.setGtnComponentList(reportProfileLookUpComponentList);
		addReportProfileLookUpRootLayout(reportProfileLookUpComponentList, namespace);

	}

	private void addReportProfileLookUpRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProfileLookUpRootLayout = new GtnUIFrameworkComponentConfig();
		reportProfileLookUpRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProfileLookUpRootLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileLookUpRootLayout");
		reportProfileLookUpRootLayout.setAddToParent(false);
		reportProfileLookUpRootLayout.setSpacing(true);
		reportProfileLookUpRootLayout.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig reportProfileLookUpRootConfig = new GtnUIFrameworkLayoutConfig();
		reportProfileLookUpRootConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportProfileLookUpRootLayout.setGtnLayoutConfig(reportProfileLookUpRootConfig);
		componentList.add(reportProfileLookUpRootLayout);
		getReportProfileLookUpRootComponentForPopUp(componentList, namespace);
	}

	public void getReportProfileLookUpRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportProfileLookUpRootComponentForPopUp = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportProfileLookUpRootComponentForPopupConfig = new GtnUIFrameworkLayoutConfig();
		reportProfileLookUpRootComponentForPopupConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportProfileLookUpRootComponentForPopUp.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProfileLookUpRootComponentForPopUp.setGtnLayoutConfig(reportProfileLookUpRootComponentForPopupConfig);
		reportProfileLookUpRootComponentForPopUp.setAddToParent(true);

		reportProfileLookUpRootComponentForPopUp.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProfileLookUpRootComponentForPopUp.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileLookUpRootLayout");
		reportProfileLookUpRootComponentForPopUp.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProfileLookUpRootComponentForPopUp.setSpacing(true);
		reportProfileLookUpRootComponentForPopUp.setMargin(true);
		componentList.add(reportProfileLookUpRootComponentForPopUp);

		addReportViewSearchCriteriaPanel(componentList, namespace);
		addSearchAndResetButtonLayout(componentList, namespace);
		resultsPanel(componentList, namespace);
		addControlPopUpButtonLayout(componentList, namespace);
	}

	private void addReportViewSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportViewSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		reportViewSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportViewSearchCriteriaPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportViewSearchCriteriaPanel");
		reportViewSearchCriteriaPanel.setComponentName("Report View Search");
		reportViewSearchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportViewSearchCriteriaPanel.addComponentStyle("stpl-margin-left-10");
		reportViewSearchCriteriaPanel.addComponentStyle("stpl-margin-top-11");
		reportViewSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportViewSearchCriteriaPanel.setAddToParent(true);
		componentList.add(reportViewSearchCriteriaPanel);
		reportViewSearchCriteriaLayout(componentList, namespace);
	}

	private void reportViewSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportViewSearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		reportViewSearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportViewSearchCriteriaLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_VIEW_SEARCH_CRITERIA_LAYOUT);
		reportViewSearchCriteriaLayout.setComponentName("Search Criteria");
		reportViewSearchCriteriaLayout.setAddToParent(true);
		reportViewSearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportViewSearchCriteriaLayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportViewSearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		reportViewSearchCriteriaLayout.setGtnLayoutConfig(conf);
		reportViewSearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportViewSearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(reportViewSearchCriteriaLayout);

		addViewTypeOptionGroup(componentList, namespace);
		addViewNameTextBox(componentList, namespace);
	}

	private void addViewTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig viewTypeOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		viewTypeOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		viewTypeOptionGroupConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.VIEW_TYPE);
		viewTypeOptionGroupConfig.setComponentName("View Type: ");
		viewTypeOptionGroupConfig.setAddToParent(true);
		viewTypeOptionGroupConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_VIEW_SEARCH_CRITERIA_LAYOUT);
		GtnUIFrameworkOptionGroupConfig viewTypeOptionGroupLoadConfig = new GtnUIFrameworkOptionGroupConfig();
		viewTypeOptionGroupLoadConfig.setItemValues(Arrays.asList("Public", "Private"));
		viewTypeOptionGroupLoadConfig.setValuesFromService(false);

		viewTypeOptionGroupConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		viewTypeOptionGroupConfig.setGtnUIFrameworkOptionGroupConfig(viewTypeOptionGroupLoadConfig);
		componentList.add(viewTypeOptionGroupConfig);
	}

	private void addViewNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig viewNameTextBoxConfig = new GtnUIFrameworkComponentConfig();
		viewNameTextBoxConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		viewNameTextBoxConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.VIEW_NAME);
		viewNameTextBoxConfig.setComponentName("View Name: ");
		viewNameTextBoxConfig.addComponentStyle("stpl-margin-bottom-22");
		viewNameTextBoxConfig.addComponentStyle("stpl-margin-left-22");
		viewNameTextBoxConfig.setAddToParent(true);
		viewNameTextBoxConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_VIEW_SEARCH_CRITERIA_LAYOUT);
		viewNameTextBoxConfig.setDefaultFocus(true);
		componentList.add(viewNameTextBoxConfig);
	}

	private void addSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProfileSearchAndResetButtonLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		reportProfileSearchAndResetButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProfileSearchAndResetButtonLayout.setAddToParent(true);
		reportProfileSearchAndResetButtonLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProfileSearchAndResetButtonLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_SEARCH_AND_RESET_BUTTON_LAYOUT);
		reportProfileSearchAndResetButtonLayout.setSpacing(true);
		reportProfileSearchAndResetButtonLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(reportProfileSearchAndResetButtonLayout);

		GtnUIFrameworkComponentConfig reportProfileSearchButton = new GtnUIFrameworkComponentConfig();
		reportProfileSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileSearchButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileSearchButton");
		reportProfileSearchButton.setComponentName("SEARCH");
		reportProfileSearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_SEARCH_AND_RESET_BUTTON_LAYOUT);
		reportProfileSearchButton.setAddToParent(true);

		componentList.add(reportProfileSearchButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataTableActionConfig
				.setActionParameterList(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_PROFILE_PAGED_TABLE_COMPONENT));
		loadDataTableActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.VIEW_NAME,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.VIEW_TYPE));
		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig searchAction = new GtnUIFrameWorkActionConfig();
		searchAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchAction.addActionParameter(GtnFrameworkReportSearchNotification.class.getName());
		searchAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_PAGED_TABLE_COMPONENT);
		actionConfigList.add(searchAction);
		reportProfileSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig reportProfileResetButton = new GtnUIFrameworkComponentConfig();
		reportProfileResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileResetButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResetButton");
		reportProfileResetButton.setComponentName("RESET");
		reportProfileResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_SEARCH_AND_RESET_BUTTON_LAYOUT);
		reportProfileResetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		params.add("Are you sure you want to reset?");
		params.add(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.VIEW_TYPE,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.VIEW_NAME,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_PROFILE_PAGED_TABLE_COMPONENT));
		params.add(Arrays.asList("Public", GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY));
		resetActionConfig.setActionParameterList(params);
		resetActionConfigList.add(resetActionConfig);
		reportProfileResetButton.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

		componentList.add(reportProfileResetButton);
	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProfileResultPanel = new GtnUIFrameworkComponentConfig();
		reportProfileResultPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportProfileResultPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResultPanel");
		reportProfileResultPanel.setComponentName("Results");
		reportProfileResultPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProfileResultPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProfileResultPanel.setAddToParent(true);
		reportProfileResultPanel.addComponentStyle("stpl-margin-left-10");
		reportProfileResultPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(reportProfileResultPanel);
		addReportProfilePagedTableComponent(componentList, namespace);
	}

	private void addReportProfilePagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportProfilePagedTableComponent = new GtnUIFrameworkComponentConfig();
		reportProfilePagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		reportProfilePagedTableComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_PAGED_TABLE_COMPONENT);
		reportProfilePagedTableComponent.setComponentName("Results");
		reportProfilePagedTableComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResultPanel");
		reportProfilePagedTableComponent.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		reportProfilePagedTableComponent.setComponentWidth("100%");
		reportProfilePagedTableComponent.setComponentStyle(tableStyle);
		reportProfilePagedTableComponent.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		componentList.add(reportProfilePagedTableComponent);

		GtnUIFrameworkPagedTableConfig reportProfilePagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		reportProfilePagedTableConfig.setEditable(false);
		reportProfilePagedTableConfig.setFilterBar(true);
		reportProfilePagedTableConfig.setPageLength(10);
		reportProfilePagedTableConfig.setItemPerPage(10);
		reportProfilePagedTableConfig.setSelectable(true);
		reportProfilePagedTableConfig.setSinkItemPerPageWithPageLength(false);

		reportProfilePagedTableConfig.setPaginationOff(true);

		reportProfilePagedTableConfig.setItemsPerPageAlignCentre(false);

		reportProfilePagedTableConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_REPORT_PROFILE_LOOKUP_SERVICE_COUNT);
		reportProfilePagedTableConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_REPORT_PROFILE_LOOKUP_SERVICE);
		reportProfilePagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		reportProfilePagedTableConfig.setColumnHeaders(Arrays.asList("View Name",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER,
				GtnFrameworkCommonConstants.CREATED_BY_HEADER, "viewId", "viewData"));
		reportProfilePagedTableConfig
				.setTableColumnMappingId(new Object[] { GtnFrameworkReportStringConstants.VIEW_NAME_FILTER,
						"createdDateFilter", "modifiedDateFilter", "createdByFilter" });
		reportProfilePagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		reportProfilePagedTableComponent.setGtnPagedTableConfig(reportProfilePagedTableConfig);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> reportProfileFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		String[] reportProfileComboboxIds = new String[1];
		String[] reportProfileComboboxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig reportProfileFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			reportProfileFilterConfig.setPropertId(columnPropertyIds[i]);
			reportProfileFilterConfig.setGtnComponentType(componentType[i]);
			if ((startIndex < reportProfileComboboxIds.length)
					&& columnPropertyIds[i].equals(reportProfileComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig custHierarchySearchFilterConfig = new GtnUIFrameworkComponentConfig();
				custHierarchySearchFilterConfig.setComponentId("customFilterComboBox");
				custHierarchySearchFilterConfig.setComponentName("customFilterComboBox");
				custHierarchySearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				custHierarchySearchFilterConfig.getGtnComboboxConfig()
						.setComboBoxType(reportProfileComboboxType[startIndex]);
				custHierarchySearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				reportProfileFilterConfig.setGtnComponentConfig(custHierarchySearchFilterConfig);
				startIndex++;
			}
			reportProfileFilterConfigMap.put(reportProfileFilterConfig.getPropertId(), reportProfileFilterConfig);
		}
		return reportProfileFilterConfigMap;
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProfileControlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		reportProfileControlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProfileControlPopUpLayout.setAddToParent(true);
		reportProfileControlPopUpLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProfileControlPopUpLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_CONTROL_POPUP_BUTTON_LAYOUT);
		reportProfileControlPopUpLayout.setSpacing(true);
		reportProfileControlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(reportProfileControlPopUpLayout);

		GtnUIFrameworkComponentConfig reportProfileSelectButton = new GtnUIFrameworkComponentConfig();
		reportProfileSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileSelectButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileSelectButton");
		reportProfileSelectButton.setComponentName("SELECT");
		reportProfileSelectButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_CONTROL_POPUP_BUTTON_LAYOUT);
		reportProfileSelectButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig reportProfileSelectAction = new GtnUIFrameWorkActionConfig();
		reportProfileSelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_PAGED_TABLE_COMPONENT);
		actionParameter.add(GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_REPORT_PROFILE_CONFIG);
		actionParameter.add(Arrays.asList("viewNameFilter"));
		actionParameter
				.add(Arrays.asList(GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_REPORT_PROFILE_CONFIG));
		reportProfileSelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(reportProfileSelectAction);

		GtnUIFrameWorkActionConfig reportProfileClosepopup = new GtnUIFrameWorkActionConfig();
		reportProfileClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportProfileClosepopup.addActionParameter("reportProfileLookupView");
		actionConfigList.add(reportProfileClosepopup);

		GtnUIFrameWorkActionConfig reportProfileLoadReportingDashboardAction = new GtnUIFrameWorkActionConfig();
		reportProfileLoadReportingDashboardAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportProfileLoadReportingDashboardAction
				.addActionParameter(GtnReportingDashboardReportProfileLoadAction.class.getName());
		reportProfileLoadReportingDashboardAction
				.addActionParameter(GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_REPORT_PROFILE_CONFIG);
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_displaySelectionTabVariable");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboard_displaySelectionTabPeriodRangeFrom");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboard_displaySelectionTabPeriodRangeTo");
		reportProfileLoadReportingDashboardAction.addActionParameter("reportingDashboard_displaySelectionTabFrequency");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboard_displaySelectionTabComparisonBasis");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_displaySelectionTabCustomView");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_displaySelectionTabVariableCategory");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_displaySelectionTabAnnualTotals");
		reportProfileLoadReportingDashboardAction.addActionParameter("reportingDashboardTab_filterTabCustomerLevel");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_filterOptionsTabCustomerFilter");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_filterOptionsTabProductLevel");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_filterOptionsTabProductFilter");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_filterOptionsTabDeductionLevel");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_filterOptionsTabDeductionFilter");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_filterOptionsTabSalesInclusion");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_filterOptionsTabDeductionInclusion");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_reportOptionsTabVariableAndVarianceSequencing");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_reportOptionsTabHeaderSequencing");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_reportOptionsTabViewOptions");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_reportOptionsTabDisplayFormat");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_reportOptionsTabUnitOfMeasure");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_reportOptionsTabCurrencyDisplay");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_reportingDashboardComparisonConfig");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_reportOptionsTabVariableBreakdown");
		reportProfileLoadReportingDashboardAction
				.addActionParameter("reportingDashboardTab_reportOptionsTabComparisonOptions");

		actionConfigList.add(reportProfileLoadReportingDashboardAction);

		reportProfileSelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(reportProfileSelectButton);

		GtnUIFrameworkComponentConfig reportProfileCancelButton = new GtnUIFrameworkComponentConfig();
		reportProfileCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileCancelButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileCancelButton");
		reportProfileCancelButton.setComponentName("DELETE");
		reportProfileCancelButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_CONTROL_POPUP_BUTTON_LAYOUT);
		reportProfileCancelButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigListForDelete = new ArrayList<>();
		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnUIFrameworkReportConfirmedDeleteButtonAction.class.getName());
		deleteActionConfig.addActionParameter("reportProfileLookup_reportProfilePagedTableComponent");
		deleteActionConfig.addActionParameter("No Value Selected");
		deleteActionConfig.addActionParameter("Please select a Report View to delete.");
		deleteActionConfig.addActionParameter("Are you sure you want to delete the selected view?");
		actionConfigListForDelete.add(deleteActionConfig);
		reportProfileCancelButton.setGtnUIFrameWorkActionConfigList(actionConfigListForDelete);

		componentList.add(reportProfileCancelButton);

		GtnUIFrameworkComponentConfig reportProfileResetButton = new GtnUIFrameworkComponentConfig();

		reportProfileResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileResetButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResetButton");
		reportProfileResetButton.setComponentName("CLOSE");
		reportProfileResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PROFILE_CONTROL_POPUP_BUTTON_LAYOUT);
		reportProfileResetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigListForClose = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		actionConfigListForClose.add(closeAction);
		reportProfileResetButton.setGtnUIFrameWorkActionConfigList(actionConfigListForClose);
		componentList.add(reportProfileResetButton);
	}
}
