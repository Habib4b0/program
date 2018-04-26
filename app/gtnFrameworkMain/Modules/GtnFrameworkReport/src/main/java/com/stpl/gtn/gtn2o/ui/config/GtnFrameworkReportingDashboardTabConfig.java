package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

public class GtnFrameworkReportingDashboardTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addReportingDashboardLayout(List<GtnUIFrameworkComponentConfig> componentList,String nameSpace) {

		addTabLayout(componentList,nameSpace);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_SHEET_LAYOUT, false, null);
		layoutConfig.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(layoutConfig);

		addTabSheet(componentList,nameSpace);

	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig tabSheetConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.TAB_SHEET, true,
				GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_SHEET_LAYOUT,
				GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setAuthorizationIncluded(true);
		tabSheetConfig.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(tabSheetConfig);

		GtnUIFrameworkTabConfig displaySelectionTab = new GtnUIFrameworkTabConfig();
		displaySelectionTab.setComponentId(GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB);
		displaySelectionTab.setTabCaption("Display Selection");
		List<GtnUIFrameworkComponentConfig> displaySelectionTabConfigList = new ArrayList<>();
		displaySelectionTab.setTabLayoutComponentConfigList(displaySelectionTabConfigList);
		addDisplaySelectionTab(displaySelectionTabConfigList,nameSpace);

		GtnUIFrameworkTabConfig filterOptionsTab = new GtnUIFrameworkTabConfig();
		filterOptionsTab.setComponentId(GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB);
		filterOptionsTab.setTabCaption("Filter Options");
		List<GtnUIFrameworkComponentConfig> filterOptionsTabConfigList = new ArrayList<>();
		filterOptionsTab.setTabLayoutComponentConfigList(filterOptionsTabConfigList);
		addFilterOptionsTab(filterOptionsTabConfigList,nameSpace);

		GtnUIFrameworkTabConfig reportOptionsTab = new GtnUIFrameworkTabConfig();
		reportOptionsTab.setComponentId(GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB);
		reportOptionsTab.setTabCaption("Report Options");
		List<GtnUIFrameworkComponentConfig> reportOptionsTabConfigList = new ArrayList<>();
		reportOptionsTab.setTabLayoutComponentConfigList(reportOptionsTabConfigList);
		addReportOptionsTab(reportOptionsTabConfigList,nameSpace);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(displaySelectionTab);
		tabConfigList.add(filterOptionsTab);
		tabConfigList.add(reportOptionsTab);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addDisplaySelectionTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB, false, null);
		gtnLayout.setTabComponent(true);
		gtnLayout.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig displaySelectionTabComponentLayout = configProvider.getHorizontalLayoutConfig(
				"displaySelectionTabComponentLayout", true, GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB);
		componentList.add(displaySelectionTabComponentLayout);

		GtnUIFrameworkComponentConfig displaySelectionTabControlButtonLayout = configProvider.getHorizontalLayoutConfig(
				"displaySelectionTabControlButtonLayout", true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB);
		componentList.add(displaySelectionTabControlButtonLayout);

		GtnUIFrameworkComponentConfig displaySelectionTabPagedTreeTableLayout = configProvider
				.getHorizontalLayoutConfig("displaySelectionTabPagedTreeTableLayout", true,
						GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB);
		componentList.add(displaySelectionTabPagedTreeTableLayout);

		GtnUIFrameworkComponentConfig displaySelectionTabNavigationButtonLayout = configProvider
				.getHorizontalLayoutConfig("displaySelectionTabNavigationButtonLayout", true,
						GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB);
		componentList.add(displaySelectionTabNavigationButtonLayout);

		GtnUIFrameworkComponentConfig displaySelectionTabPanel = configProvider
				.getPanelConfig("displaySelectionTabPanel", true, "displaySelectionTabComponentLayout");
		displaySelectionTabPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		displaySelectionTabPanel.setComponentHight("180px");
		componentList.add(displaySelectionTabPanel);

		GtnUIFrameworkComponentConfig displaySelectionTabPanelLayout = configProvider
				.getHorizontalLayoutConfig("displaySelectionTabPanelLayout", true, "displaySelectionTabPanel");
		componentList.add(displaySelectionTabPanelLayout);

		GtnUIFrameworkLayoutConfig cssLayout = new GtnUIFrameworkLayoutConfig();
		cssLayout.setLayoutType(GtnUIFrameworkLayoutType.COL4_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT, true,
				"displaySelectionTabPanelLayout", GtnUIFrameworkComponentType.LAYOUT);
		cssGtnLayout.setGtnLayoutConfig(cssLayout);
		componentList.add(cssGtnLayout);

		addDisplaySelectionTabComponent(componentList,nameSpace);
		addControlButtonLayout(componentList, "displaySelectionTabControlButtonLayout",nameSpace);
		addResultsPanel(componentList, "displaySelectionTabPagedTreeTableLayout",nameSpace);
		addNavigationButtonLayout(componentList, nameSpace, "displaySelectionTabNavigationButtonLayout");
	}

	private void addDisplaySelectionTabComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		addReportProfileComponent(componentList,nameSpace);
		addComparisonComponent(componentList,nameSpace);
		addVariableComponent(componentList,nameSpace);
		addFrequencyComponent(componentList,nameSpace);
		addPeriodRangeFromComponent(componentList,nameSpace);
		addCustomViewButtonComponent(componentList,nameSpace);
		addCustomViewComponent(componentList,nameSpace);
		addComparisonBasisComponent(componentList,nameSpace);
		addVariableCategoryComponent(componentList,nameSpace);
		addAnnualTotalsComponent(componentList,nameSpace);
		addPeriodRangeToComponent(componentList,nameSpace);
	}

	private void addReportProfileComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
				
		GtnUIFrameworkComponentConfig reportProfileConfig = new GtnUIFrameworkComponentConfig();
		reportProfileConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		reportProfileConfig.setComponentId(nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+"reportProfileConfig");
		reportProfileConfig.setComponentName("Report Profile: ");
		reportProfileConfig.setAddToParent(true);
		reportProfileConfig.setParentComponentId(GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);

		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		Object reportProfileLookup = "Report Profile Lookup";
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(Arrays.asList("reportProfileLookupView", reportProfileLookup, "795", "875"));
		list.add(conf);

		reportProfileConfig.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(reportProfileConfig);
		
	}

	private void addComparisonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig reportingDashboardComparisonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+"reportingDashboardComparisonConfig", true, GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT, GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		reportingDashboardComparisonConfig.setAuthorizationIncluded(true);
		reportingDashboardComparisonConfig.setComponentName("Comparison: ");

		GtnUIFrameworkTextBoxConfig reportingDashboardComparisonTextboxConfig = configProvider.getTextBoxConfig(true, true, true);
		reportingDashboardComparisonConfig.setGtnTextBoxConfig(reportingDashboardComparisonTextboxConfig);

		componentList.add(reportingDashboardComparisonConfig);

		List<GtnUIFrameWorkActionConfig> reportingDashboardComparisonPopupActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardComparisonPopupConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		reportingDashboardComparisonPopupConfig.addActionParameter("comparisonLookupView");
		reportingDashboardComparisonPopupConfig.addActionParameter("Comparison Lookup");
		reportingDashboardComparisonPopupConfig.addActionParameter(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		reportingDashboardComparisonPopupConfig.addActionParameter(null);
		reportingDashboardComparisonPopupActionList.add(reportingDashboardComparisonPopupConfig);
		reportingDashboardComparisonConfig.setGtnUIFrameWorkActionConfigList(reportingDashboardComparisonPopupActionList);

	}

	private void addVariableComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig variableConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		variableConfig.setComponentName("Variable: ");
		variableConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkCheckedComboBoxConfig variableLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		variableLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		variableLoadConfig.setDefaultValue(GtnFrameworkReportStringConstants.SELECT_VALUES);
		variableLoadConfig.setCheckedComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		variableConfig.setGtnCheckedComboboxConfig(variableLoadConfig);
		componentList.add(variableConfig);
	}

	private void addFrequencyComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_FREQUENCY_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig frequencyConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_FREQUENCY, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_FREQUENCY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		frequencyConfig.setComponentName("Frequency: ");
		frequencyConfig.setAuthorizationIncluded(true);

		componentList.add(frequencyConfig);

		GtnUIFrameworkComboBoxConfig frequencyLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequencyConfig.setGtnComboboxConfig(frequencyLoadConfig);

	}

	private void addPeriodRangeFromComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_FROM_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig periodRangeFromConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_FROM, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_FROM_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		periodRangeFromConfig.setComponentName("Period Range From: ");
		periodRangeFromConfig.setAuthorizationIncluded(true);

		componentList.add(periodRangeFromConfig);

		GtnUIFrameworkComboBoxConfig periodRangeFromLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		periodRangeFromConfig.setGtnComboboxConfig(periodRangeFromLoadConfig);

	}

	private void addCustomViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig customViewConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW_BUTTON, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		customViewConfig.setComponentName("Custom View: ");
		customViewConfig.addComponentStyle(GtnFrameworkReportStringConstants.LINK);
		customViewConfig.setAuthorizationIncluded(true);

		componentList.add(customViewConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customViewPopupAction = new GtnUIFrameWorkActionConfig();
		customViewPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		customViewPopupAction.addActionParameter("reportCustomViewLookup");
		customViewPopupAction.addActionParameter("reportCustomViewLookup");
		customViewPopupAction.addActionParameter("75%");
		customViewPopupAction.addActionParameter(null);
		actionConfigList.add(customViewPopupAction);

		customViewConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCustomViewComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW_COMBO_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		gtnLayout.setComponentWidth("13%");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig customViewComboboxConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW_COMBO_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customViewComboboxConfig.setComponentName("");
		customViewComboboxConfig.setAuthorizationIncluded(true);

		componentList.add(customViewComboboxConfig);

		GtnUIFrameworkComboBoxConfig customViewLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customViewComboboxConfig.setGtnComboboxConfig(customViewLoadConfig);

	}

	private void addComparisonBasisComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_COMPARISON_BASIS_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig comparisonBasisConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_COMPARISON_BASIS, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_COMPARISON_BASIS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		comparisonBasisConfig.setComponentName("Comparison Basis: ");
		comparisonBasisConfig.setAuthorizationIncluded(true);

		componentList.add(comparisonBasisConfig);

		GtnUIFrameworkComboBoxConfig comparisonBasisLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comparisonBasisConfig.setGtnComboboxConfig(comparisonBasisLoadConfig);

	}

	private void addVariableCategoryComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_CATEGORY_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig variableCategoryConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_CATEGORY, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_CATEGORY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		variableCategoryConfig.setComponentName("Variable Category: ");
		variableCategoryConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkCheckedComboBoxConfig variableCategoryLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		variableCategoryLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		variableCategoryLoadConfig.setDefaultValue(GtnFrameworkReportStringConstants.SELECT_VALUES);
		variableCategoryLoadConfig.setCheckedComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		variableCategoryConfig.setGtnCheckedComboboxConfig(variableCategoryLoadConfig);
		componentList.add(variableCategoryConfig);

	}

	private void addAnnualTotalsComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_ANNUAL_TOTALS_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig annualTotalsConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_ANNUAL_TOTALS, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_ANNUAL_TOTALS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		annualTotalsConfig.setComponentName("Annual Totals: ");
		annualTotalsConfig.setAuthorizationIncluded(true);

		componentList.add(annualTotalsConfig);

		GtnUIFrameworkComboBoxConfig annualTotalsLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		annualTotalsConfig.setGtnComboboxConfig(annualTotalsLoadConfig);

	}

	private void addPeriodRangeToComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_TO_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig periodRangeToConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_TO, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_TO_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		periodRangeToConfig.setComponentName("Period Range To: ");
		periodRangeToConfig.setAuthorizationIncluded(true);

		componentList.add(periodRangeToConfig);

		GtnUIFrameworkComboBoxConfig periodRangeToLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		periodRangeToConfig.setGtnComboboxConfig(periodRangeToLoadConfig);

	}

	private void addFilterOptionsTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB, false, null);
		gtnLayout.setTabComponent(true);
		gtnLayout.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig filterOptionsTabComponentLayout = configProvider.getHorizontalLayoutConfig(
				"filterOptionsTabComponentLayout", true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB);
		componentList.add(filterOptionsTabComponentLayout);

		GtnUIFrameworkComponentConfig filterOptionsTabControlButtonLayout = configProvider.getHorizontalLayoutConfig(
				"filterOptionsTabControlButtonLayout", true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB);
		componentList.add(filterOptionsTabControlButtonLayout);

		GtnUIFrameworkComponentConfig filterOptionsTabPagedTreeTableLayout = configProvider.getHorizontalLayoutConfig(
				"filterOptionsTabPagedTreeTableLayout", true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB);
		componentList.add(filterOptionsTabPagedTreeTableLayout);

		GtnUIFrameworkComponentConfig filterOptionsTabNavigationButtonLayout = configProvider.getHorizontalLayoutConfig(
				"filterOptionsTabNavigationButtonLayout", true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB);
		componentList.add(filterOptionsTabNavigationButtonLayout);

		GtnUIFrameworkComponentConfig filterOptionsTabPanel = configProvider.getPanelConfig("filterOptionsTabPanel",
				true, "filterOptionsTabComponentLayout");
		filterOptionsTabPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		filterOptionsTabPanel.setComponentHight("100px");
		componentList.add(filterOptionsTabPanel);

		GtnUIFrameworkComponentConfig filterOptionsTabPanelLayout = configProvider
				.getHorizontalLayoutConfig("filterOptionsTabPanelLayout", true, "filterOptionsTabPanel");
		componentList.add(filterOptionsTabPanelLayout);

		GtnUIFrameworkLayoutConfig cssLayout = new GtnUIFrameworkLayoutConfig();
		cssLayout.setLayoutType(GtnUIFrameworkLayoutType.COL4_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CSS_LAYOUT, true, "filterOptionsTabPanelLayout",
				GtnUIFrameworkComponentType.LAYOUT);
		cssGtnLayout.setGtnLayoutConfig(cssLayout);
		componentList.add(cssGtnLayout);

		addFilterOptionsTabComponent(componentList,nameSpace);
		addControlButtonLayout(componentList, "filterOptionsTabControlButtonLayout",nameSpace);
		addResultsPanel(componentList, "filterOptionsTabPagedTreeTableLayout",nameSpace);
		addNavigationButtonLayout(componentList, nameSpace, "filterOptionsTabNavigationButtonLayout");
	}

	private void addFilterOptionsTabComponent(List<GtnUIFrameworkComponentConfig> componentList,String nameSpace) {
		addCustomerLevelComponent(componentList,nameSpace);
		addProductLevelComponent(componentList,nameSpace);
		addDeductionLevelComponent(componentList,nameSpace);
		addSalesInclusionComponent(componentList,nameSpace);
		addCustomerFilterComponent(componentList,nameSpace);
		addProductFilterComponent(componentList,nameSpace);
		addDeductionFilterComponent(componentList,nameSpace);
		addDeductionInclusionComponent(componentList,nameSpace);
	}

	private void addDeductionInclusionComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_INCLUSION_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig deductionInclusionConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_INCLUSION, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_INCLUSION_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		deductionInclusionConfig.setComponentName("Deduction Inclusion: ");
		deductionInclusionConfig.setAuthorizationIncluded(true);

		componentList.add(deductionInclusionConfig);

		GtnUIFrameworkComboBoxConfig deductionInclusionLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionInclusionConfig.setGtnComboboxConfig(deductionInclusionLoadConfig);
	}

	private void addDeductionFilterComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig deductionFilterConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionFilterConfig.addComponentStyle(GtnFrameworkCssConstants.V_HAS_WIDTH);
		deductionFilterConfig.setComponentWidth(GtnFrameworkReportStringConstants.ONE_FIFTY_PIXEL);
		deductionFilterConfig.setComponentName("Deduction Filter: ");
		deductionFilterConfig.setAuthorizationIncluded(true);

		componentList.add(deductionFilterConfig);

		GtnUIFrameworkCheckedComboBoxConfig deductionFilterLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionFilterLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		deductionFilterLoadConfig.setDefaultValue(GtnFrameworkReportStringConstants.SELECT_VALUES);
		deductionFilterLoadConfig.setCheckedComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		deductionFilterConfig.setGtnCheckedComboboxConfig(deductionFilterLoadConfig);
	}

	private void addProductFilterComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig productFilterConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		productFilterConfig.addComponentStyle(GtnFrameworkCssConstants.V_HAS_WIDTH);
		productFilterConfig.setComponentWidth(GtnFrameworkReportStringConstants.ONE_FIFTY_PIXEL);
		productFilterConfig.setComponentName("Product Filter: ");
		productFilterConfig.setAuthorizationIncluded(true);

		componentList.add(productFilterConfig);

		GtnUIFrameworkCheckedComboBoxConfig productFilterLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		productFilterLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		productFilterLoadConfig.setDefaultValue(GtnFrameworkReportStringConstants.SELECT_VALUES);
		productFilterLoadConfig.setCheckedComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		productFilterConfig.setGtnCheckedComboboxConfig(productFilterLoadConfig);
	}

	private void addCustomerFilterComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig customerFilterConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		customerFilterConfig.addComponentStyle(GtnFrameworkCssConstants.V_HAS_WIDTH);
		customerFilterConfig.setComponentWidth(GtnFrameworkReportStringConstants.ONE_FIFTY_PIXEL);
		customerFilterConfig.setComponentName("Customer Filter: ");
		customerFilterConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkCheckedComboBoxConfig customerFilterLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		customerFilterLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		customerFilterLoadConfig.setDefaultValue(GtnFrameworkReportStringConstants.SELECT_VALUES);
		customerFilterLoadConfig.setCheckedComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		customerFilterConfig.setGtnCheckedComboboxConfig(customerFilterLoadConfig);
		componentList.add(customerFilterConfig);

	}

	private void addSalesInclusionComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_SALES_INCLUSION_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig salesInclusionConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_SALES_INCLUSION, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_SALES_INCLUSION_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		salesInclusionConfig.setComponentName("Sales Inclusion: ");
		salesInclusionConfig.setAuthorizationIncluded(true);

		componentList.add(salesInclusionConfig);

		GtnUIFrameworkComboBoxConfig salesInclusionLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		salesInclusionConfig.setGtnComboboxConfig(salesInclusionLoadConfig);
	}

	private void addDeductionLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_LEVEL_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig deductionLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_LEVEL, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_LEVEL_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		deductionLevel.setComponentName("Deduction Level: ");
		deductionLevel.setAuthorizationIncluded(true);

		componentList.add(deductionLevel);

		GtnUIFrameworkComboBoxConfig deductionLevelConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		deductionLevel.setGtnComboboxConfig(deductionLevelConfig);
	}

	private void addProductLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_LEVEL_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig productLevelConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_LEVEL, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_LEVEL_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		productLevelConfig.setComponentName("Product Level: ");
		productLevelConfig.setAuthorizationIncluded(true);

		componentList.add(productLevelConfig);

		GtnUIFrameworkComboBoxConfig productLevelLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		productLevelConfig.setGtnComboboxConfig(productLevelLoadConfig);
	}

	private void addCustomerLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_LEVEL_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+"companyInformationTabCompanyStatus", true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_LEVEL_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		companyStatus.setComponentName("Customer Level: ");
		companyStatus.setAuthorizationIncluded(true);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addReportOptionsTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig(GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB, false, null);
		gtnLayout.setTabComponent(true);
		gtnLayout.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig reportOptionsTabComponentLayout = configProvider.getHorizontalLayoutConfig(
				"reportOptionsTabComponentLayout", true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB);
		componentList.add(reportOptionsTabComponentLayout);

		GtnUIFrameworkComponentConfig reportOptionsTabControlButtonLayout = configProvider.getHorizontalLayoutConfig(
				"reportOptionsTabControlButtonLayout", true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB);
		componentList.add(reportOptionsTabControlButtonLayout);

		GtnUIFrameworkComponentConfig reportOptionsTabPagedTreeTableLayout = configProvider.getHorizontalLayoutConfig(
				"reportOptionsTabPagedTreeTableLayout", true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB);
		componentList.add(reportOptionsTabPagedTreeTableLayout);

		GtnUIFrameworkComponentConfig reportOptionsTabNavigationButtonLayout = configProvider.getHorizontalLayoutConfig(
				"reportOptionsTabNavigationButtonLayout", true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB);
		componentList.add(reportOptionsTabNavigationButtonLayout);

		GtnUIFrameworkComponentConfig reportOptionsTabPanel = configProvider.getPanelConfig("reportOptionsTabPanel",
				true, "reportOptionsTabComponentLayout");
		reportOptionsTabPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		reportOptionsTabPanel.setComponentHight("120px");
		componentList.add(reportOptionsTabPanel);

		GtnUIFrameworkComponentConfig reportOptionsTabPanelLayout = configProvider
				.getHorizontalLayoutConfig("reportOptionsTabPanelLayout", true, "reportOptionsTabPanel");
		componentList.add(reportOptionsTabPanelLayout);

		GtnUIFrameworkLayoutConfig cssLayout = new GtnUIFrameworkLayoutConfig();
		cssLayout.setLayoutType(GtnUIFrameworkLayoutType.COL4_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CSS_LAYOUT, true, "reportOptionsTabPanelLayout",
				GtnUIFrameworkComponentType.LAYOUT);
		cssGtnLayout.setGtnLayoutConfig(cssLayout);
		componentList.add(cssGtnLayout);

		addReportOptionsTabComponent(componentList,nameSpace);
		addControlButtonLayout(componentList, "reportOptionsTabControlButtonLayout",nameSpace);
		addResultsPanel(componentList, "reportOptionsTabPagedTreeTableLayout",nameSpace);
		addNavigationButtonLayout(componentList, nameSpace, "reportOptionsTabNavigationButtonLayout");
	}

	private void addReportOptionsTabComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		addVariableAndVarianceSequencingComponent(componentList,nameSpace);
		addVariableBreakdownComponent(componentList,nameSpace);
		addViewOptionsComponent(componentList,nameSpace);
		addUnitOfMeasureComponent(componentList,nameSpace);
		addHeaderSequencingComponent(componentList,nameSpace);
		addComparisonOptionsComponent(componentList,nameSpace);
		addDisplayFormatComponent(componentList,nameSpace);
		addCurrencyDisplayComponent(componentList,nameSpace);
	}

	private void addVariableAndVarianceSequencingComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_AND_VARIANCE_SEQUENCING_LAYOUT, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig variableAndVarianceSequencingConfig = configProvider
				.getUIFrameworkComponentConfig(
						nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_AND_VARIANCE_SEQUENCING, true,
						GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_AND_VARIANCE_SEQUENCING_LAYOUT,
						GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableAndVarianceSequencingConfig.setComponentName("Variable & Variance Sequencing: ");
		variableAndVarianceSequencingConfig.setAuthorizationIncluded(true);

		componentList.add(variableAndVarianceSequencingConfig);

		GtnUIFrameworkComboBoxConfig variableAndVarianceSequencingLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableAndVarianceSequencingConfig.setGtnComboboxConfig(variableAndVarianceSequencingLoadConfig);
	}

	private void addVariableBreakdownComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN_LAYOUT, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CSS_LAYOUT);
		gtnLayout.setComponentWidth("12%");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig variableBreakdownConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownConfig.setComponentName("Variable Breakdown: ");
		variableBreakdownConfig.addComponentStyle(GtnFrameworkReportStringConstants.LINK);
		variableBreakdownConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig variableBreakdownPopupActionConfig = new GtnUIFrameWorkActionConfig();
		variableBreakdownPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		variableBreakdownPopupActionConfig.addActionParameter("variableBreakdown");
		variableBreakdownPopupActionConfig.addActionParameter("Variable Breakdown");
		variableBreakdownPopupActionConfig.addActionParameter(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);

		actionConfigList.add(variableBreakdownPopupActionConfig);
		variableBreakdownConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(variableBreakdownConfig);
	}

	private void addViewOptionsComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VIEW_OPTIONS_LAYOUT, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig viewOptionsConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VIEW_OPTIONS, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VIEW_OPTIONS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		viewOptionsConfig.setComponentName("View Options: ");
		viewOptionsConfig.setAuthorizationIncluded(true);

		componentList.add(viewOptionsConfig);

		GtnUIFrameworkComboBoxConfig viewOptionsLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		viewOptionsConfig.setGtnComboboxConfig(viewOptionsLoadConfig);
	}

	private void addUnitOfMeasureComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE_LAYOUT, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig unitOfMeasureConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		unitOfMeasureConfig.setComponentName("Unit Of Measure: ");
		unitOfMeasureConfig.setAuthorizationIncluded(true);

		componentList.add(unitOfMeasureConfig);

		GtnUIFrameworkComboBoxConfig unitOfMeasureLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		unitOfMeasureConfig.setGtnComboboxConfig(unitOfMeasureLoadConfig);
	}

	private void addHeaderSequencingComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_HEADER_SEQUENCING_LAYOUT, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig headerSequencingConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_HEADER_SEQUENCING, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_HEADER_SEQUENCING_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		headerSequencingConfig.setComponentName("Header Sequencing: ");
		headerSequencingConfig.setAuthorizationIncluded(true);

		componentList.add(headerSequencingConfig);

		GtnUIFrameworkComboBoxConfig headerSequencingLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		headerSequencingConfig.setGtnComboboxConfig(headerSequencingLoadConfig);
	}

	private void addComparisonOptionsComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_COMPARISON_OPTIONS_LAYOUT, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CSS_LAYOUT);
		gtnLayout.setComponentWidth("12%");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig comparisonOptionsConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_COMPARISON_OPTIONS, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_COMPARISON_OPTIONS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		comparisonOptionsConfig.setComponentName("Comparison Options: ");
		comparisonOptionsConfig.setAuthorizationIncluded(true);
		comparisonOptionsConfig.addComponentStyle(GtnFrameworkReportStringConstants.LINK);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig comparisonOptionsPopupActionConfig = new GtnUIFrameWorkActionConfig();
		comparisonOptionsPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		comparisonOptionsPopupActionConfig.addActionParameter("comparisonOptions");
		comparisonOptionsPopupActionConfig.addActionParameter("Comparison Options");
		comparisonOptionsPopupActionConfig.addActionParameter(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);

		actionConfigList.add(comparisonOptionsPopupActionConfig);
		comparisonOptionsConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(comparisonOptionsConfig);
	}

	private void addDisplayFormatComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_DISPLAY_FORMAT_LAYOUT, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig displayFormatConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_DISPLAY_FORMAT, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_DISPLAY_FORMAT_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		displayFormatConfig.setComponentName("Display Format: ");
		displayFormatConfig.setAuthorizationIncluded(true);

		componentList.add(displayFormatConfig);

		GtnUIFrameworkComboBoxConfig displayFormatLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		displayFormatConfig.setGtnComboboxConfig(displayFormatLoadConfig);
	}

	private void addCurrencyDisplayComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CURRENCY_DISPLAY_LAYOUT, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig currencyDisplayConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CURRENCY_DISPLAY, true,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CURRENCY_DISPLAY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		currencyDisplayConfig.setComponentName("Currency Display: ");
		currencyDisplayConfig.setAuthorizationIncluded(true);

		componentList.add(currencyDisplayConfig);

		GtnUIFrameworkComboBoxConfig currencyDisplayLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		currencyDisplayConfig.setGtnComboboxConfig(currencyDisplayLoadConfig);
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String nameSpace) {

		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig controlButtonLayoutConfig = new GtnUIFrameworkComponentConfig();
		controlButtonLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlButtonLayoutConfig.setComponentId("controlButtonLayoutConfig");
		controlButtonLayoutConfig.setComponentName("controlButtonLayoutConfig");
		controlButtonLayoutConfig.setAddToParent(true);
		controlButtonLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		controlButtonLayoutConfig.setParentComponentId(parentId);
		controlButtonLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		controlButtonLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		controlButtonLayoutConfig.setGtnLayoutConfig(layoutConf);
		componentList.add(controlButtonLayoutConfig);

		GtnUIFrameworkComponentConfig generateButton = new GtnUIFrameworkComponentConfig();
		generateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateButton.setComponentName("GENERATE");
		generateButton.setComponentId(nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+"generateButton");
		generateButton.setParentComponentId(controlButtonLayoutConfig.getComponentId());
		generateButton.setAddToParent(true);
		componentList.add(generateButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentName("RESET");
		resetButton.setComponentId(nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+"resetButton");
		resetButton.setParentComponentId(controlButtonLayoutConfig.getComponentId());
		resetButton.setAddToParent(true);
		componentList.add(resetButton);

		GtnUIFrameworkComponentConfig saveProfileButton = new GtnUIFrameworkComponentConfig();
		saveProfileButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveProfileButton.setComponentName("SAVE PROFILE");
		saveProfileButton.setComponentId(nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+"saveProfileButton");
		saveProfileButton.setParentComponentId(controlButtonLayoutConfig.getComponentId());
		saveProfileButton.setAddToParent(true);
		componentList.add(saveProfileButton);

		GtnUIFrameWorkActionConfig saveProfileActionConfig = new GtnUIFrameWorkActionConfig();
		saveProfileActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		saveProfileActionConfig.addActionParameter("saveProfileViewLookUp");
		saveProfileActionConfig.addActionParameter("Profile Save View");
		saveProfileButton.addGtnUIFrameWorkActionConfig(saveProfileActionConfig);

		addExpandAndCollapseButtonComponent(componentList, controlButtonLayoutConfig.getComponentId(),nameSpace);
	}

	private void addExpandAndCollapseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId,String nameSpace) {

		GtnUIFrameworkComponentConfig expandAndCollapseHorizontalConfig = configProvider
				.getHorizontalLayoutConfig("expandAndCollapseHorizontalConfig", true, parentId);

		GtnUIFrameworkComponentConfig levelConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE, true,
				"expandAndCollapseHorizontalConfig", GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		levelConfig.setComponentName("Level: ");

		GtnUIFrameworkComboBoxConfig levelLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelConfig.setGtnComboboxConfig(levelLoadConfig);

		GtnUIFrameworkComponentConfig expandButton = new GtnUIFrameworkComponentConfig();
		expandButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		expandButton.setComponentName("EXPAND");
		expandButton.setComponentId(nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+"expandButton");
		expandButton.setParentComponentId(parentId);
		expandButton.setAddToParent(true);

		GtnUIFrameworkComponentConfig collapseButton = new GtnUIFrameworkComponentConfig();
		collapseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		collapseButton.setComponentName("COLLAPSE");
		collapseButton.setComponentId(nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+"collapseButton");
		collapseButton.setParentComponentId(parentId);
		collapseButton.setAddToParent(true);

		componentList.add(expandAndCollapseHorizontalConfig);
		componentList.add(levelConfig);
		componentList.add(expandButton);
		componentList.add(collapseButton);
	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String nameSpace) {
		GtnUIFrameworkComponentConfig resultsPanel = configProvider.getPanelConfig("reportingDashboardResultsPanel",
				true, parentId);
		resultsPanel.setComponentName("Results");
		resultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(resultsPanel);
		
		GtnUIFrameworkComponentConfig resultsLayout = configProvider.getVerticalLayoutConfig("resultsLayout", true, resultsPanel.getComponentId());
		componentList.add(resultsLayout);
		
		addResultTable(componentList, resultsLayout.getComponentId());
		addExcelButtonComponent(componentList,nameSpace, resultsLayout.getComponentId());
	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig reportingDashboardResultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		reportingDashboardResultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_TREE_GRID);
		reportingDashboardResultTableComponentConfig.setComponentId(GtnFrameworkCommonConstants.RESULT_TABLE);
		reportingDashboardResultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		reportingDashboardResultTableComponentConfig.setAddToParent(true);
		reportingDashboardResultTableComponentConfig.setParentComponentId(parentId);

		GtnUIFrameworkPagedTreeTableConfig reportingDashboardGtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> reportingDashboardActionConfigList = new ArrayList<>();
		reportingDashboardActionConfigList.add(parentId);
		GtnUIFrameWorkActionConfig reportingDashboardGtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardGtnUIFrameWorkActionConfig.setActionParameterList(reportingDashboardActionConfigList);
		reportingDashboardGtnPagedTreeTableConfig.setGtnUIFrameWorkActionConfig(reportingDashboardGtnUIFrameWorkActionConfig);

		reportingDashboardGtnPagedTreeTableConfig.setLeftHeader(
				GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
		reportingDashboardGtnPagedTreeTableConfig.setRightHeader(
				GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

//            reportingDashboardGtnPagedTreeTableConfig.setLeftWsHeaderUrl(
//                    GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
//             reportingDashboardGtnPagedTreeTableConfig.setRightWsHeaderUrl(
//                    GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);
		reportingDashboardGtnPagedTreeTableConfig.setCountUrl("");
		reportingDashboardGtnPagedTreeTableConfig.setItemPerPage(10);

		reportingDashboardGtnPagedTreeTableConfig.setMaxSplitPosition(1000);
		reportingDashboardGtnPagedTreeTableConfig.setMinSplitPosition(300);
		reportingDashboardGtnPagedTreeTableConfig.setPageLength(15);
		reportingDashboardGtnPagedTreeTableConfig.setResultSetUrl("");

		reportingDashboardGtnPagedTreeTableConfig.setSplitPosition(493);

		reportingDashboardGtnPagedTreeTableConfig.setTableHeight("650px");
		reportingDashboardGtnPagedTreeTableConfig.setDoubleHeaderVisible(true);

		reportingDashboardGtnPagedTreeTableConfig.setLeftTableEditable(true);
		reportingDashboardGtnPagedTreeTableConfig.setRightTableEditable(true);

		List<String> reportingDashboardFieldFactoryColum = Arrays.asList(GtnFrameworkCommonConstants.CHECK);
		List<GtnUIFrameworkComponentConfig> reportingDashboardFieldFactoryComponent = new ArrayList<>();
		reportingDashboardGtnPagedTreeTableConfig.setLeftTableEditableColumnList(reportingDashboardFieldFactoryColum);

		GtnUIFrameworkComponentConfig reportingDashboardCheckBox = new GtnUIFrameworkComponentConfig();
		reportingDashboardCheckBox.setComponentId(GtnFrameworkCommonConstants.CHECK);
		reportingDashboardCheckBox.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
		GtnUIFrameworkCheckBoxComponentConfig reportingDashboardCheckBoxConfig = new GtnUIFrameworkCheckBoxComponentConfig();
		reportingDashboardCheckBoxConfig.setImmediate(true);

		reportingDashboardCheckBox.setGtnCheckBoxConfig(reportingDashboardCheckBoxConfig);
		List<GtnUIFrameWorkActionConfig> reportingDashboardCheckBoxClickActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardGtnUIFrameWorkGenerateActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardGtnUIFrameWorkGenerateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardGtnUIFrameWorkGenerateActionConfig
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_LEFT_FIELD_FACTORY_ACTION);
		reportingDashboardCheckBoxClickActionList.add(reportingDashboardGtnUIFrameWorkGenerateActionConfig);
		reportingDashboardGtnUIFrameWorkGenerateActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));
		reportingDashboardCheckBox.setGtnUIFrameWorkItemClickActionConfigList(reportingDashboardCheckBoxClickActionList);

		reportingDashboardFieldFactoryComponent.add(reportingDashboardCheckBox);
		reportingDashboardGtnPagedTreeTableConfig.setLeftTableEditableComponentConfig(reportingDashboardFieldFactoryComponent);

		List<GtnUIFrameWorkActionConfig> reportingDashboardTextFieldConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardFieldFactoryCustomAction = new GtnUIFrameWorkActionConfig();

		reportingDashboardFieldFactoryCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));

		reportingDashboardFieldFactoryCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardFieldFactoryCustomAction
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_RIGHT_FIELD_FACTORY_ACTION);
		reportingDashboardTextFieldConfig.add(reportingDashboardFieldFactoryCustomAction);
		reportingDashboardGtnPagedTreeTableConfig.setComponentconfigActionlist(reportingDashboardTextFieldConfig);

		reportingDashboardGtnPagedTreeTableConfig.setCheckBoxVisibleColoumn(Arrays.asList(GtnFrameworkCommonConstants.CHECK));
		List<GtnUIFrameWorkActionConfig> reportingDashboardCheckAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardCheckAllActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardCheckAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardCheckAllActionConfig.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_CHECK_ALL_ACTION);
		reportingDashboardCheckAllConflist.add(reportingDashboardCheckAllActionConfig);
		reportingDashboardGtnPagedTreeTableConfig.setCheckBoxActionConfigList(reportingDashboardCheckAllConflist);
		reportingDashboardGtnPagedTreeTableConfig
				.setCountUrl(GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_COUNT_ACTION);
		reportingDashboardGtnPagedTreeTableConfig
				.setCountWsUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setBulkDataUrl(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_BULK_DATA_ACTION);
		reportingDashboardGtnPagedTreeTableConfig.setBulkDataWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setFillCountUrl(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_FILL_COUNT_DATA_ACTION);
		reportingDashboardGtnPagedTreeTableConfig.setFillCountWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setLeftHeaderUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_COMP_LEFT_HEADER_FORM_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setLeftWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setRighttHeaderUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_COMP_RIGHT_HEADER_FORM_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setRightWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setLeftHeaderCustomClassLoadUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_LEFT_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_RIGHT_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);

		reportingDashboardResultTableComponentConfig.setGtnPagedTreeTableConfig(reportingDashboardGtnPagedTreeTableConfig);
		componentList.add(reportingDashboardResultTableComponentConfig);
	}

	private void addNavigationButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace,
			String parentId) {

		GtnUIFrameworkComponentConfig previousButtonConfig = new GtnUIFrameworkComponentConfig();
		previousButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButtonConfig.setComponentId(nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+ "previousButtonConfig");
		previousButtonConfig.setComponentName("PREVIOUS");
		previousButtonConfig.setAddToParent(true);
		previousButtonConfig.setParentComponentId(parentId);

		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentId(nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+ "closeButtonConfig");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAddToParent(true);
		closeButtonConfig.setParentComponentId(parentId);

		componentList.add(previousButtonConfig);
		componentList.add(closeButtonConfig);
	}
	

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace,
			String parentId) {

		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getVerticalLayoutConfig(
				nameSpace+GtnFrameworkReportStringConstants.UNDERSCORE+"excelButtonConfig", true, parentId);
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);
		
	}

}
