package com.stpl.gtn.gtn2o.ui.config;

import static com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportDataSelectionTabConfig.getResetParameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkComparisonLookupTextFieldEnableAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkLoadFromInDataSelectionAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkLoadToInDataSelectionAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportDashBoardRightHeaderRequestAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportOptionsViewOptionsAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportResetAndCloseAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportingComparisonOptionsGroupValuesLoadingAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportingDashboardSaveProfileAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIReportCustomViewReloadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIReportDasboardTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIReportGenerateRequestAction;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkUIUOMLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionResultsLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDashboardFrequencyLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDashboardValuesResetAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportFilterReloadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportLevelFilterReloadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkReportFilterGenerateLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkReportLevelDdlbLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIReportExpandCollapseAction;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.button.GtnUIFrameworkButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabSheetLoadType;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnFrameworkReportTabChangeAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingComparisonBreakdownGridLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownFrequencyLoadAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariableCategory;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportingDashboardTabConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	private String reportProfileLookupId = "reportProfileLookup";

	public void addReportingDashboardLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		addTabLayout(componentList, nameSpace);

	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_SHEET_LAYOUT, false, null);
		layoutConfig.setComponentWidth(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(layoutConfig);

		addTabSheet(componentList, nameSpace);

		addControlButtonLayout(componentList, GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_SHEET_LAYOUT,
				nameSpace);
		addResultsPanel(componentList, GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_SHEET_LAYOUT,
				nameSpace);
		addNavigationButtonLayout(componentList, nameSpace,
				GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_TAB_SHEET_LAYOUT);

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
		displaySelectionTab.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		List<GtnUIFrameworkComponentConfig> displaySelectionTabConfigList = new ArrayList<>();
		displaySelectionTab.setTabLayoutComponentConfigList(displaySelectionTabConfigList);
		addDisplaySelectionTab(displaySelectionTabConfigList, nameSpace);

		GtnUIFrameworkTabConfig filterOptionsTab = new GtnUIFrameworkTabConfig();
		filterOptionsTab.setComponentId(GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB);
		filterOptionsTab.setTabCaption("Filter Options");
		filterOptionsTab.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		List<GtnUIFrameworkComponentConfig> filterOptionsTabConfigList = new ArrayList<>();
		filterOptionsTab.setTabLayoutComponentConfigList(filterOptionsTabConfigList);
		addFilterOptionsTab(filterOptionsTabConfigList, nameSpace);

		GtnUIFrameworkTabConfig reportOptionsTab = new GtnUIFrameworkTabConfig();
		reportOptionsTab.setComponentId(GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB);
		reportOptionsTab.setTabCaption("Report Options");
		reportOptionsTab.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		List<GtnUIFrameworkComponentConfig> reportOptionsTabConfigList = new ArrayList<>();
		reportOptionsTab.setTabLayoutComponentConfigList(reportOptionsTabConfigList);
		addReportOptionsTab(reportOptionsTabConfigList, nameSpace);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(displaySelectionTab);
		tabConfigList.add(filterOptionsTab);
		tabConfigList.add(reportOptionsTab);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addDisplaySelectionTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig displaySelectionTabPanel = configProvider
				.getPanelConfig("displaySelectionTabPanel", false, null);
		displaySelectionTabPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		displaySelectionTabPanel.setComponentHight("180px");
		displaySelectionTabPanel.setTabComponent(true);
		componentList.add(displaySelectionTabPanel);

		GtnUIFrameworkComponentConfig displaySelectionTabPanelVerticalLayout = configProvider
				.getVerticalLayoutConfig("displaySelectionTabPanelVerticalLayout", true, "displaySelectionTabPanel");
		displaySelectionTabPanelVerticalLayout.setComponentWidth("1640px");
		displaySelectionTabPanelVerticalLayout.setComponentHight("100%");
		componentList.add(displaySelectionTabPanelVerticalLayout);

		GtnUIFrameworkComponentConfig displaySelectionTabPanelLayout = configProvider.getHorizontalLayoutConfig(
				"displaySelectionTabPanelLayout", true, "displaySelectionTabPanelVerticalLayout");
		displaySelectionTabPanelLayout.addComponentStyle("stpl-padding-top-20");
		componentList.add(displaySelectionTabPanelLayout);

		GtnUIFrameworkLayoutConfig cssLayout = new GtnUIFrameworkLayoutConfig();
		cssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT, true,
				"displaySelectionTabPanelLayout", GtnUIFrameworkComponentType.LAYOUT);
		cssGtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cssGtnLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		cssGtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		cssGtnLayout.setGtnLayoutConfig(cssLayout);
		componentList.add(cssGtnLayout);

		addDisplaySelectionTabComponent(componentList, nameSpace);

		GtnUIFrameworkComponentConfig displaySelectionTabPanelLayoutBottom = configProvider.getHorizontalLayoutConfig(
				"displaySelectionTabPanelLayoutBottom", true, "displaySelectionTabPanelVerticalLayout");
		componentList.add(displaySelectionTabPanelLayoutBottom);

		GtnUIFrameworkLayoutConfig cssLayoutBottom = new GtnUIFrameworkLayoutConfig();
		cssLayoutBottom.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnLayoutBottom = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT_BOTTOM, true,
				"displaySelectionTabPanelLayoutBottom", GtnUIFrameworkComponentType.LAYOUT);
		cssGtnLayoutBottom.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cssGtnLayoutBottom.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		cssGtnLayoutBottom.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		cssGtnLayoutBottom.setGtnLayoutConfig(cssLayoutBottom);
		componentList.add(cssGtnLayoutBottom);
		addDisplaySelectionTabComponentBottom(componentList, nameSpace);
	}

	private void addDisplaySelectionTabComponentBottom(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {
		addCustomViewCssComponent(componentList, nameSpace);
		addComparisonBasisComponent(componentList);
		addVariableCategoryComponent(componentList, nameSpace);
		addAnnualTotalsComponent(componentList, nameSpace);
		addPeriodRangeToComponent(componentList);
	}

	private void addDisplaySelectionTabComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		addReportProfileComponent(componentList, nameSpace);
		addComparisonComponent(componentList, nameSpace);
		addVariableComponent(componentList, nameSpace);
		addFrequencyComponent(componentList, nameSpace);
		addPeriodRangeFromComponent(componentList);

	}

	private void addCustomViewCssComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkLayoutConfig cssCustomViewLayout = new GtnUIFrameworkLayoutConfig();
		cssCustomViewLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnCustomViewLayout = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "customViewCssLayout", true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT_BOTTOM,
				GtnUIFrameworkComponentType.LAYOUT);
		cssGtnCustomViewLayout.addComponentStyle("stpl-padding-top-10");
		cssGtnCustomViewLayout.setGtnLayoutConfig(cssCustomViewLayout);
		componentList.add(cssGtnCustomViewLayout);
		addCustomViewButtonComponent(componentList, nameSpace, cssGtnCustomViewLayout.getComponentId());
		addCustomViewComponent(componentList, nameSpace, cssGtnCustomViewLayout.getComponentId());
	}

	private void addCustomViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace,
			String parentId) {
		GtnUIFrameworkComponentConfig customViewButtonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW_BUTTON,
				true, parentId, GtnUIFrameworkComponentType.BUTTON);
		customViewButtonConfig.setComponentName("Custom View: ");
		GtnUIFrameworkButtonConfig buttonConfig = new GtnUIFrameworkButtonConfig();
		buttonConfig.setButtonCaptionInUpperCase(false);
		customViewButtonConfig.setButtonConfig(buttonConfig);
		customViewButtonConfig.addComponentStyle(GtnFrameworkReportStringConstants.LINK);
		customViewButtonConfig.setAuthorizationIncluded(true);
		customViewButtonConfig.addComponentStyle("stpl-padding-bottom-22");
		componentList.add(customViewButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customViewButtonPopupAction = new GtnUIFrameWorkActionConfig();
		customViewButtonPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		customViewButtonPopupAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_CUSTOM_VIEW_LOOKUP_RD);
		customViewButtonPopupAction.addActionParameter("Custom Tree View Popup");
		customViewButtonPopupAction.addActionParameter("75%");
		customViewButtonPopupAction.addActionParameter(null);
		actionConfigList.add(customViewButtonPopupAction);

		customViewButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCustomViewComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace,
			String parentId) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW_COMBO_LAYOUT, true, parentId);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig customViewComboboxConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW,
				true, GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW_COMBO_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customViewComboboxConfig.setAuthorizationIncluded(true);
		customViewComboboxConfig.addComponentStyle("v-report-custom-view");
		customViewComboboxConfig.setComponentWsFieldId("customViewName");

		componentList.add(customViewComboboxConfig);

		GtnUIFrameworkComboBoxConfig customViewLoadConfig = configProvider.getComboBoxConfig("REPORT_CUSTOM_VIEW",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		customViewComboboxConfig.setGtnComboboxConfig(customViewLoadConfig);

		GtnUIFrameWorkActionConfig reloadActionConfig = new GtnUIFrameWorkActionConfig();
		reloadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reloadActionConfig.addActionParameter(GtnFrameworkUIReportCustomViewReloadAction.class.getName());
		customViewComboboxConfig.setReloadActionConfig(reloadActionConfig);
		customViewComboboxConfig
				.setReloadLogicActionClassName(GtnFrameworkUIReportCustomViewReloadAction.class.getName());

		GtnUIFrameWorkActionConfig enableComparisonLookupActionConfig = new GtnUIFrameWorkActionConfig();
		enableComparisonLookupActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		enableComparisonLookupActionConfig
				.addActionParameter(GtnFrameworkComparisonLookupTextFieldEnableAction.class.getName());
		enableComparisonLookupActionConfig.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_COMPARISON_CONFIG);
		enableComparisonLookupActionConfig.addActionParameter("");
		enableComparisonLookupActionConfig.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW);
		enableComparisonLookupActionConfig.addActionParameter("");
		customViewComboboxConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(enableComparisonLookupActionConfig));

	}

	private void addVariableComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		gtnLayout.addComponentStyle("stpl-padding-left-36");
		gtnLayout.addComponentStyle("stpl-padding-top-14");
		gtnLayout.addComponentStyle("v-report-display-selection-variables");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig variableConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE,
				true, GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		variableConfig.setComponentName("Variables: ");
		variableConfig.addComponentStyle("v-report-display-selection-variables-combo");
		variableConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkCheckedComboBoxConfig variableLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		GtnWsReportVariablesType[] variableType = Arrays.copyOfRange(GtnWsReportVariablesType.values(), 0,
				GtnWsReportVariablesType.values().length - 1);
		variableLoadConfig.setItemValueList(
				Arrays.stream(variableType).map(GtnWsReportVariablesType::toString).collect(Collectors.toList()));
		variableLoadConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		variableConfig.setGtnCheckedComboboxConfig(variableLoadConfig);
		componentList.add(variableConfig);
	}

	private void addComparisonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig reportingDashboardComparisonConfigHorizontalLayout = configProvider
				.getHorizontalLayoutConfig("reportingDashboardComparisonConfigHorizontalLayout", true,
						GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		reportingDashboardComparisonConfigHorizontalLayout.addComponentStyle("stpl-padding-left-32");
		reportingDashboardComparisonConfigHorizontalLayout.addComponentStyle("stpl-padding-top-14");
		reportingDashboardComparisonConfigHorizontalLayout.addComponentStyle("v-report-display-selection-comparison");
		componentList.add(reportingDashboardComparisonConfigHorizontalLayout);

		GtnUIFrameworkComponentConfig reportingDashboardComparisonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportingDashboardComparisonConfig", true,
				reportingDashboardComparisonConfigHorizontalLayout.getComponentId(),
				GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		reportingDashboardComparisonConfig.setAuthorizationIncluded(true);
		reportingDashboardComparisonConfig.setComponentName("Comparison: ");
		reportingDashboardComparisonConfig.addTextComponentStyle("v-report-display-selection-comparison-search");
		GtnUIFrameworkTextBoxConfig reportingDashboardComparisonTextboxConfig = configProvider.getTextBoxConfig(true,
				true, true);
		reportingDashboardComparisonConfig.setGtnTextBoxConfig(reportingDashboardComparisonTextboxConfig);

		componentList.add(reportingDashboardComparisonConfig);

		List<GtnUIFrameWorkActionConfig> reportingDashboardComparisonPopupActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardComparisonPopupConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		reportingDashboardComparisonPopupConfig.addActionParameter("dashboardcomparisonLookupView");
		reportingDashboardComparisonPopupConfig.addActionParameter("Comparison Popup");
		reportingDashboardComparisonPopupConfig.addActionParameter(null);
		reportingDashboardComparisonPopupConfig.addActionParameter(null);
		reportingDashboardComparisonPopupActionList.add(reportingDashboardComparisonPopupConfig);

		GtnUIFrameWorkActionConfig selectedGridLoadAction = new GtnUIFrameWorkActionConfig();
		selectedGridLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectedGridLoadAction.addActionParameter(GtnReportComparisonProjectionResultsLoadAction.class.getName());
		selectedGridLoadAction.addActionParameter("comparisonLookupProjectionsResultsPagedTableComponent");
		reportingDashboardComparisonPopupActionList.add(selectedGridLoadAction);
		reportingDashboardComparisonConfig
				.setGtnUIFrameWorkActionConfigList(reportingDashboardComparisonPopupActionList);

	}

	private void addReportProfileComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig reportProfileConfigHorizontalLayout = configProvider.getHorizontalLayoutConfig(
				"reportProfileConfigHorizontalLayout", true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		reportProfileConfigHorizontalLayout.addComponentStyle("stpl-padding-top-13");
		reportProfileConfigHorizontalLayout.addComponentStyle("stpl-padding-left-35");
		reportProfileConfigHorizontalLayout.addComponentStyle("v-report-display-selection-profile");
		componentList.add(reportProfileConfigHorizontalLayout);

		GtnUIFrameworkComponentConfig reportProfileConfig = new GtnUIFrameworkComponentConfig();
		reportProfileConfig.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		reportProfileConfig
				.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileConfig");
		reportProfileConfig.setComponentName("Report Profile: ");
		reportProfileConfig.setAddToParent(true);
		reportProfileConfig.setParentComponentId(reportProfileConfigHorizontalLayout.getComponentId());
		reportProfileConfig.addComponentStyle("stpl-padding-left-34");
		reportProfileConfig.addTextComponentStyle("v-report-display-selection-profile-search");

		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		Object reportProfileLookup = "Report Profile Popup";
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(Arrays.asList("reportProfileLookupView", reportProfileLookup, "1000px", "845px",
				GtnFrameworkReportResetAndCloseAction.class.getName(),
				Arrays.asList(reportProfileLookupId + GtnFrameworkReportStringConstants.UNDERSCORE + "viewType",
						reportProfileLookupId + GtnFrameworkReportStringConstants.UNDERSCORE + "viewName",
						reportProfileLookupId + GtnFrameworkReportStringConstants.UNDERSCORE
								+ "reportProfilePagedTableComponent"),
				Arrays.asList(new Object[] { "Public", GtnFrameworkCommonStringConstants.STRING_EMPTY,
						GtnFrameworkCommonStringConstants.STRING_EMPTY })));
		list.add(conf);

		reportProfileConfig.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(reportProfileConfig);

	}

	private void addFrequencyComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_FREQUENCY_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		gtnLayout.addComponentStyle("stpl-padding-left-17");
		gtnLayout.addComponentStyle("stpl-padding-top-14");
		gtnLayout.addComponentStyle("v-report-display-selection-freq");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig frequencyConfig = configProvider.getUIFrameworkComponentConfig(
				"reportingDashboard_" + GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_FREQUENCY, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_FREQUENCY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		frequencyConfig.setComponentName("Frequency: ");
		frequencyConfig.addComponentStyle("v-report-display-selection-freq-combo");
		frequencyConfig.setAuthorizationIncluded(true);

		componentList.add(frequencyConfig);

		GtnUIFrameworkComboBoxConfig frequencyLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.REPORT_CONFIG_FREQUENCY,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequencyConfig.setGtnComboboxConfig(frequencyLoadConfig);

		GtnUIFrameWorkActionConfig selectedFrequencyAction = new GtnUIFrameWorkActionConfig();
		selectedFrequencyAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectedFrequencyAction.addActionParameter(GtnReportDashboardFrequencyLoadAction.class.getName());
		frequencyConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(selectedFrequencyAction));
	}

	private void addPeriodRangeFromComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_FROM_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		gtnLayout.addComponentStyle("stpl-padding-top-14");
		gtnLayout.addComponentStyle("v-report-display-selection-annual-totals");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig periodRangeFromConfig = configProvider.getUIFrameworkComponentConfig(
				"reportingDashboard_" + GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_FROM, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_FROM_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		periodRangeFromConfig.setComponentName("Period Range From: ");
		periodRangeFromConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkComboBoxConfig periodRangeFromComponentLoadConfig = new GtnUIFrameworkComboBoxConfig();
		periodRangeFromComponentLoadConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		periodRangeFromComponentLoadConfig.setItemValues(new ArrayList<>());
		periodRangeFromComponentLoadConfig.setItemCaptionValues(new ArrayList<>());
		periodRangeFromConfig.setGtnComboboxConfig(periodRangeFromComponentLoadConfig);
		componentList.add(periodRangeFromConfig);

		GtnUIFrameWorkActionConfig loadAction = new GtnUIFrameWorkActionConfig();
		loadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		periodRangeFromConfig.setReloadActionConfig(loadAction);
		periodRangeFromConfig.setReloadLogicActionClassName(GtnFrameworkLoadFromInDataSelectionAction.class.getName());

	}

	private void addComparisonBasisComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_COMPARISON_BASIS_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT_BOTTOM);
		gtnLayout.addComponentStyle("stpl-padding-left-14");
		gtnLayout.addComponentStyle("stpl-padding-top-16");
		gtnLayout.addComponentStyle("v-report-display-selection-comparison-basis");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig comparisonBasisConfig = configProvider.getUIFrameworkComponentConfig(
				"reportingDashboard_" + GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_COMPARISON_BASIS, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_COMPARISON_BASIS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		comparisonBasisConfig.setComponentName("Comparison Basis: ");
		comparisonBasisConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkComboBoxConfig comparisonBasisComponentLoadConfig = new GtnUIFrameworkComboBoxConfig();
		comparisonBasisComponentLoadConfig.setModuleName("report");
		comparisonBasisConfig.setGtnComboboxConfig(comparisonBasisComponentLoadConfig);
		componentList.add(comparisonBasisConfig);

	}

	private void addVariableCategoryComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_CATEGORY_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT_BOTTOM);
		gtnLayout.addComponentStyle("stpl-padding-top-16");
		gtnLayout.addComponentStyle("v-report-display-selection-variable-category");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig variableCategoryConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_CATEGORY,
				true, GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_CATEGORY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		variableCategoryConfig.setComponentName("Variable Category: ");
		variableCategoryConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkCheckedComboBoxConfig variableCategoryLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		variableCategoryLoadConfig.setItemValueList(Arrays.stream(GtnWsReportVariableCategory.values())
				.map(GtnWsReportVariableCategory::toString).collect(Collectors.toList()));
		variableCategoryLoadConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		variableCategoryLoadConfig.setCheckedComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		variableCategoryConfig.setGtnCheckedComboboxConfig(variableCategoryLoadConfig);
		componentList.add(variableCategoryConfig);
	}

	private void addAnnualTotalsComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_ANNUAL_TOTALS_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT_BOTTOM);
		gtnLayout.addComponentStyle("stpl-padding-top-16");
		gtnLayout.addComponentStyle("v-report-display-selection-annual-totals");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig annualTotalsConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_ANNUAL_TOTALS,
				true, GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_ANNUAL_TOTALS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		annualTotalsConfig.setComponentName("Annual Totals: ");
		annualTotalsConfig.setAuthorizationIncluded(true);

		componentList.add(annualTotalsConfig);

		GtnUIFrameworkComboBoxConfig annualTotalsLoadConfig = new GtnUIFrameworkComboBoxConfig();
		annualTotalsLoadConfig.setItemValues(Arrays.asList("Yes", "No"));
		annualTotalsLoadConfig.setDefaultDesc("next");
		annualTotalsLoadConfig.setHasDefaultValue(true);
		annualTotalsConfig.setGtnComboboxConfig(annualTotalsLoadConfig);

	}

	private void addPeriodRangeToComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_TO_LAYOUT, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT_BOTTOM);
		gtnLayout.addComponentStyle("stpl-padding-left-16");
		gtnLayout.addComponentStyle("stpl-padding-top-17");
		gtnLayout.addComponentStyle("v-report-display-selection-prt");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig periodRangeToConfig = configProvider.getUIFrameworkComponentConfig(
				"reportingDashboard_" + GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_TO, true,
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_PERIOD_RANGE_TO_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		periodRangeToConfig.setComponentName("Period Range To: ");
		periodRangeToConfig.addComponentStyle("v-report-display-selection-freq-combo");
		periodRangeToConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkComboBoxConfig periodRangeToComponentLoadConfig = new GtnUIFrameworkComboBoxConfig();
		periodRangeToComponentLoadConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		periodRangeToComponentLoadConfig.setItemValues(new ArrayList<>());
		periodRangeToComponentLoadConfig.setItemCaptionValues(new ArrayList<>());
		periodRangeToConfig.setGtnComboboxConfig(periodRangeToComponentLoadConfig);
		componentList.add(periodRangeToConfig);

		GtnUIFrameWorkActionConfig loadAction = new GtnUIFrameWorkActionConfig();
		loadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		periodRangeToConfig.setReloadActionConfig(loadAction);
		periodRangeToConfig.setReloadLogicActionClassName(GtnFrameworkLoadToInDataSelectionAction.class.getName());
	}

	private void addFilterOptionsTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig filterOptionsTabPanel = configProvider
				.getPanelConfig(GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PANEL, false, null);
		filterOptionsTabPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		filterOptionsTabPanel.setComponentHight(GtnFrameworkCssConstants.PIXEL_100);
		filterOptionsTabPanel.setTabComponent(true);
		componentList.add(filterOptionsTabPanel);

		GtnUIFrameworkComponentConfig filterOptionsTabPanelVerticalLayout = configProvider.getVerticalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PANEL_VERTICAL_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PANEL);
		filterOptionsTabPanelVerticalLayout.setComponentWidth(GtnFrameworkCssConstants.PIXEL_1300);
		filterOptionsTabPanelVerticalLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(filterOptionsTabPanelVerticalLayout);

		GtnUIFrameworkComponentConfig filterOptionsTabTopLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_LAYOUT_TOP, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PANEL_VERTICAL_LAYOUT);
		filterOptionsTabTopLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_TOP_10);
		componentList.add(filterOptionsTabTopLayout);

		GtnUIFrameworkLayoutConfig filterOptionsTabTopCssLayout = new GtnUIFrameworkLayoutConfig();
		filterOptionsTabTopCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig filterOptionsTabTopCssLayoutConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_TOP_CSS_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_LAYOUT_TOP, GtnUIFrameworkComponentType.LAYOUT);
		filterOptionsTabTopCssLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		filterOptionsTabTopCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		filterOptionsTabTopCssLayoutConfig.setGtnLayoutConfig(filterOptionsTabTopCssLayout);
		componentList.add(filterOptionsTabTopCssLayoutConfig);

		addFilterOptionsTabTopComponent(componentList, nameSpace);

		GtnUIFrameworkComponentConfig filterOptionsTabBottomLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_LAYOUT_BOTTOM, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PANEL_VERTICAL_LAYOUT);
		componentList.add(filterOptionsTabBottomLayout);

		GtnUIFrameworkLayoutConfig filterOptionsTabBottomCssLayout = new GtnUIFrameworkLayoutConfig();
		filterOptionsTabBottomCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig filterOptionsTabBottomCssLayoutConfig = configProvider
				.getUIFrameworkComponentConfig(GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_BOTTOM_CSS_LAYOUT,
						true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_LAYOUT_BOTTOM,
						GtnUIFrameworkComponentType.LAYOUT);
		filterOptionsTabBottomCssLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		filterOptionsTabBottomCssLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		filterOptionsTabBottomCssLayoutConfig.setGtnLayoutConfig(filterOptionsTabBottomCssLayout);
		componentList.add(filterOptionsTabBottomCssLayoutConfig);

		addFilterOptionsTabBottomComponent(componentList, nameSpace);
	}

	private void addFilterOptionsTabTopComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		addCustomerLevelComponent(componentList, nameSpace);
		addProductLevelComponent(componentList, nameSpace);
		addDeductionLevelComponent(componentList, nameSpace);
		addSalesInclusionComponent(componentList, nameSpace);
	}

	private void addFilterOptionsTabBottomComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {

		addCustomerFilterComponent(componentList, nameSpace);
		addProductFilterComponent(componentList, nameSpace);
		addDeductionFilterComponent(componentList, nameSpace);
		addDeductionInclusionComponent(componentList, nameSpace);
	}

	private void addDeductionInclusionComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_INCLUSION_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_BOTTOM_CSS_LAYOUT);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_LEFT_25);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig deductionInclusionConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_INCLUSION,
				true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_INCLUSION_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionInclusionConfig.setComponentName("Deduction Inclusion: ");
		deductionInclusionConfig.addComboComponentStyle(GtnFrameworkCssConstants.REPORT_FILTER_OPTIONS_COMBO_WIDTH);
		deductionInclusionConfig.setAuthorizationIncluded(true);

		componentList.add(deductionInclusionConfig);

		GtnUIFrameworkCheckedComboBoxConfig deductionInclusionLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionInclusionLoadConfig.setItemCodeList(Arrays.asList("1", "2"));
		deductionInclusionLoadConfig.setItemValueList(Arrays.asList("Yes", "No"));
		deductionInclusionLoadConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		deductionInclusionConfig.setGtnCheckedComboboxConfig(deductionInclusionLoadConfig);
	}

	private void addDeductionFilterComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_BOTTOM_CSS_LAYOUT);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_LEFT_25);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig deductionFilterConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER,
				true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		deductionFilterConfig.setComponentName("Deduction Filter: ");
		deductionFilterConfig.addComponentStyle(GtnFrameworkCssConstants.REPORT_CUSTOMER_FILTER_MARGIN_LEFT);
		deductionFilterConfig.addComboComponentStyle(GtnFrameworkCssConstants.REPORT_FILTER_OPTIONS_COMBO_WIDTH);
		deductionFilterConfig.setAuthorizationIncluded(true);

		componentList.add(deductionFilterConfig);

		deductionFilterConfig.addDependentComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER);
		deductionFilterConfig.addDependentComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER);

		GtnUIFrameWorkActionConfig reloadAction = new GtnUIFrameWorkActionConfig();
		reloadAction.setActionType(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION);
		deductionFilterConfig.addGtnUIFrameWorkActionConfig(reloadAction);

		GtnUIFrameWorkActionConfig reloadActionConfig = getFilterReloadAction(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER,
				"D", nameSpace);
		deductionFilterConfig.setReloadActionConfig(reloadActionConfig);
		deductionFilterConfig.setReloadLogicActionClassName(GtnReportFilterReloadAction.class.getName());

		GtnUIFrameworkCheckedComboBoxConfig deductionFilterLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		deductionFilterLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		deductionFilterLoadConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		deductionFilterConfig.setGtnCheckedComboboxConfig(deductionFilterLoadConfig);
	}

	private void addProductFilterComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_BOTTOM_CSS_LAYOUT);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_LEFT_25);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig productFilterConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER,
				true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		productFilterConfig.addComponentStyle(GtnFrameworkCssConstants.V_HAS_WIDTH);
		productFilterConfig.setComponentWidth(GtnFrameworkReportStringConstants.ONE_FIFTY_PIXEL);
		productFilterConfig.setComponentName("Product Filter: ");
		productFilterConfig.addComponentStyle(GtnFrameworkCssConstants.REPORT_CUSTOMER_FILTER_MARGIN_LEFT);
		productFilterConfig.addComboComponentStyle(GtnFrameworkCssConstants.REPORT_FILTER_OPTIONS_COMBO_WIDTH);
		productFilterConfig.setAuthorizationIncluded(true);

		productFilterConfig.addDependentComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER);
		productFilterConfig.addDependentComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER);

		componentList.add(productFilterConfig);

		GtnUIFrameWorkActionConfig reloadActionConfig = getFilterReloadAction(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER,
				"P", nameSpace);
		productFilterConfig.setReloadActionConfig(reloadActionConfig);
		productFilterConfig.setReloadLogicActionClassName(GtnReportFilterReloadAction.class.getName());

		GtnUIFrameWorkActionConfig reloadAction = new GtnUIFrameWorkActionConfig();
		reloadAction.setActionType(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION);
		productFilterConfig.addGtnUIFrameWorkActionConfig(reloadAction);

		GtnUIFrameworkCheckedComboBoxConfig productFilterLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		productFilterLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		productFilterLoadConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		productFilterConfig.setGtnCheckedComboboxConfig(productFilterLoadConfig);
	}

	private void addCustomerFilterComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_BOTTOM_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig customerFilterConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER,
				true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		customerFilterConfig.setComponentName("Customer Filter: ");
		customerFilterConfig.addComponentStyle(GtnFrameworkCssConstants.REPORT_CUSTOMER_FILTER_MARGIN_LEFT);
		customerFilterConfig.addComboComponentStyle(GtnFrameworkCssConstants.REPORT_FILTER_OPTIONS_COMBO_WIDTH);
		customerFilterConfig.setAuthorizationIncluded(true);

		customerFilterConfig.addDependentComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER);
		customerFilterConfig.addDependentComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER);

		GtnUIFrameWorkActionConfig reloadAction = new GtnUIFrameWorkActionConfig();
		reloadAction.setActionType(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION);
		customerFilterConfig.addGtnUIFrameWorkActionConfig(reloadAction);

		GtnUIFrameworkCheckedComboBoxConfig customerFilterLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		customerFilterLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameWorkActionConfig reloadActionConfig = getFilterReloadAction(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER,
				"C", nameSpace);
		customerFilterConfig.setReloadActionConfig(reloadActionConfig);
		customerFilterConfig.setReloadLogicActionClassName(GtnReportFilterReloadAction.class.getName());

		customerFilterLoadConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		customerFilterConfig.setGtnCheckedComboboxConfig(customerFilterLoadConfig);
		componentList.add(customerFilterConfig);

	}

	private void addSalesInclusionComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_SALES_INCLUSION_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_TOP_CSS_LAYOUT);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_LEFT_25);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig salesInclusionConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_SALES_INCLUSION,
				true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_SALES_INCLUSION_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		salesInclusionConfig.setComponentName("Sales Inclusion: ");
		salesInclusionConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_30);
		salesInclusionConfig.addComboComponentStyle(GtnFrameworkCssConstants.REPORT_FILTER_OPTIONS_COMBO_WIDTH);
		salesInclusionConfig.setAuthorizationIncluded(true);

		componentList.add(salesInclusionConfig);

		GtnUIFrameworkCheckedComboBoxConfig salesInclusionLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		salesInclusionLoadConfig.setItemCodeList(Arrays.asList("1", "2"));
		salesInclusionLoadConfig.setItemValueList(Arrays.asList("Yes", "No"));
		salesInclusionLoadConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		salesInclusionConfig.setGtnCheckedComboboxConfig(salesInclusionLoadConfig);

	}

	private void addDeductionLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_LEVEL_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_TOP_CSS_LAYOUT);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_LEFT_25);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig deductionLevel = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_LEVEL,
				true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_LEVEL_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		deductionLevel.setComponentName("Deduction Level: ");
		deductionLevel.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_25);
		deductionLevel.addComboComponentStyle(GtnFrameworkCssConstants.REPORT_FILTER_OPTIONS_COMBO_WIDTH);
		deductionLevel.setAuthorizationIncluded(true);

		GtnUIFrameWorkActionConfig reloadAction = new GtnUIFrameWorkActionConfig();
		reloadAction.setActionType(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION);
		deductionLevel.addGtnUIFrameWorkActionConfig(reloadAction);

		componentList.add(deductionLevel);

		GtnUIFrameWorkActionConfig reloadActionConfig = getLevelFilterReloadAction(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_LEVEL,
				GtnWsHierarchyType.DEDUCTION, nameSpace);
		deductionLevel.setReloadActionConfig(reloadActionConfig);
		deductionLevel.setReloadLogicActionClassName(GtnReportLevelFilterReloadAction.class.getName());

		deductionLevel.addDependentComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER);
	
		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setItemCaptionValues(new ArrayList<>());
		companyStatusConfig.setItemValues(new ArrayList<>());
		deductionLevel.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addProductLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_LEVEL_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_TOP_CSS_LAYOUT);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_LEFT_25);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig productLevelConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_LEVEL,
				true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_LEVEL_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		productLevelConfig.setComponentName("Product Level: ");
		productLevelConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_25);
		productLevelConfig.addComboComponentStyle(GtnFrameworkCssConstants.REPORT_FILTER_OPTIONS_COMBO_WIDTH);
		productLevelConfig.setAuthorizationIncluded(true);

		productLevelConfig.addDependentComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER);

		GtnUIFrameWorkActionConfig reloadActionConfig = getLevelFilterReloadAction(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_LEVEL,
				GtnWsHierarchyType.PRODUCT, nameSpace);
		productLevelConfig.setReloadActionConfig(reloadActionConfig);
		productLevelConfig.setReloadLogicActionClassName(GtnReportLevelFilterReloadAction.class.getName());

		GtnUIFrameWorkActionConfig reloadAction = new GtnUIFrameWorkActionConfig();
		reloadAction.setActionType(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION);
		productLevelConfig.addGtnUIFrameWorkActionConfig(reloadAction);

		componentList.add(productLevelConfig);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setItemCaptionValues(new ArrayList<>());
		companyStatusConfig.setItemValues(new ArrayList<>());
		productLevelConfig.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addCustomerLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_LEVEL_LAYOUT, true,
				GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_TOP_CSS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig customerLevelConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_FILTER_TAB_CUSTOMER_LEVEL,
				true, GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_LEVEL_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		customerLevelConfig.setComponentName("Customer Level: ");
		customerLevelConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_LEFT_25);
		customerLevelConfig.addComboComponentStyle(GtnFrameworkCssConstants.REPORT_FILTER_OPTIONS_COMBO_WIDTH);
		customerLevelConfig.setAuthorizationIncluded(true);

		componentList.add(customerLevelConfig);

		customerLevelConfig.addDependentComponent(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER);

		GtnUIFrameWorkActionConfig reloadActionConfig = getLevelFilterReloadAction(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_FILTER_TAB_CUSTOMER_LEVEL,
				GtnWsHierarchyType.CUSTOMER, nameSpace);
		customerLevelConfig.setReloadActionConfig(reloadActionConfig);
		customerLevelConfig.setReloadLogicActionClassName(GtnReportLevelFilterReloadAction.class.getName());

		GtnUIFrameWorkActionConfig reloadAction = new GtnUIFrameWorkActionConfig();
		reloadAction.setActionType(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION);
		customerLevelConfig.addGtnUIFrameWorkActionConfig(reloadAction);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
		companyStatusConfig.setItemCaptionValues(new ArrayList<>());
		companyStatusConfig.setItemValues(new ArrayList<>());
		customerLevelConfig.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addReportOptionsTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig reportOptionsTabPanel = configProvider.getPanelConfig("reportOptionsTabPanel",
				false, null);
		reportOptionsTabPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		reportOptionsTabPanel.setComponentHight("180px");
		reportOptionsTabPanel.setTabComponent(true);
		componentList.add(reportOptionsTabPanel);

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getVerticalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB, true, "reportOptionsTabPanel");
		gtnLayout.setComponentWidth("1200px");
		gtnLayout.setComponentHight(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig reportOptionsTabComponentLayout = configProvider.getHorizontalLayoutConfig(
				"reportOptionsTabComponentLayout", true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB);
		reportOptionsTabComponentLayout.addComponentStyle("stpl-padding-top-20");
		componentList.add(reportOptionsTabComponentLayout);

		GtnUIFrameworkLayoutConfig cssLayout = new GtnUIFrameworkLayoutConfig();
		cssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				"reportOptionsTabCssLayout", true, "reportOptionsTabComponentLayout",
				GtnUIFrameworkComponentType.LAYOUT);
		cssGtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cssGtnLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		cssGtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		cssGtnLayout.setGtnLayoutConfig(cssLayout);
		componentList.add(cssGtnLayout);

		addReportOptionsTabComponent(componentList, nameSpace);

		GtnUIFrameworkComponentConfig reportOptionsTabPanelLayout = configProvider.getHorizontalLayoutConfig(
				"reportOptionsTabPanelLayoutBottom", true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB);
		componentList.add(reportOptionsTabPanelLayout);

		GtnUIFrameworkLayoutConfig cssLayoutBottom = new GtnUIFrameworkLayoutConfig();
		cssLayoutBottom.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig cssGtnLayoutBottom = configProvider.getUIFrameworkComponentConfig(
				"reportOptionsTabCssLayoutBottom", true, "reportOptionsTabPanelLayoutBottom",
				GtnUIFrameworkComponentType.LAYOUT);
		cssGtnLayoutBottom.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cssGtnLayoutBottom.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		cssGtnLayoutBottom.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		cssGtnLayoutBottom.setGtnLayoutConfig(cssLayoutBottom);
		componentList.add(cssGtnLayoutBottom);
		addReportOptionsTabComponentBottom(componentList, nameSpace);

	}

	private void addReportOptionsTabComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		addVariableAndVarianceSequencingComponent(componentList, nameSpace);
		addVariableBreakdownComponent(componentList, nameSpace);
		addViewOptionsComponent(componentList, nameSpace);
		addUnitOfMeasureComponent(componentList, nameSpace);
	}

	private void addReportOptionsTabComponentBottom(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {
		addHeaderSequencingComponent(componentList, nameSpace);
		addComparisonOptionsComponent(componentList, nameSpace);
		addDisplayFormatComponent(componentList, nameSpace);
		addCurrencyDisplayComponent(componentList, nameSpace);
	}

	private void addComparisonOptionsComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_COMPARISON_OPTIONS_LAYOUT, true,
				"reportOptionsTabCssLayoutBottom");
		gtnLayout.setComponentWidth("12%");
		gtnLayout.addComponentStyle("v-report-reportoptions-comparison");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig comparisonOptionsConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_COMPARISON_OPTIONS,
				true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_COMPARISON_OPTIONS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		GtnUIFrameworkButtonConfig buttonConfig = new GtnUIFrameworkButtonConfig();
		buttonConfig.setButtonCaptionInUpperCase(false);
		comparisonOptionsConfig.setButtonConfig(buttonConfig);
		comparisonOptionsConfig.setComponentName("Comparison Options: ");
		comparisonOptionsConfig.setAuthorizationIncluded(true);
		comparisonOptionsConfig.addComponentStyle(GtnFrameworkReportStringConstants.LINK);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig comparisonOptionsPopupActionConfig = new GtnUIFrameWorkActionConfig();
		comparisonOptionsPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		comparisonOptionsPopupActionConfig.addActionParameter("comparisonOptions");
		comparisonOptionsPopupActionConfig.addActionParameter("Comparison Options Popup");
		comparisonOptionsPopupActionConfig.addActionParameter(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		actionConfigList.add(comparisonOptionsPopupActionConfig);

		// load comparison options group values --------
		GtnUIFrameWorkActionConfig loadComparisonOptionValuesActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadComparisonOptionValuesActionConfig
				.addActionParameter(GtnFrameworkReportingComparisonOptionsGroupValuesLoadingAction.class.getName());
		GtnUIFrameWorkActionConfig loadGridComparisonActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadGridComparisonActionConfig
				.addActionParameter(GtnReportingComparisonBreakdownGridLoadAction.class.getName());
		loadGridComparisonActionConfig
				.addActionParameter("comparisonBreakdownResultsLayout_comparisonBreakdownResultsPagedTableComponent");
		actionConfigList.add(loadComparisonOptionValuesActionConfig);
		actionConfigList.add(loadGridComparisonActionConfig);

		comparisonOptionsConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(comparisonOptionsConfig);
	}

	private void addVariableBreakdownComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN_LAYOUT, true,
				"reportOptionsTabCssLayout");
		gtnLayout.setComponentWidth("12%");
		gtnLayout.addComponentStyle("v-report-options-vb");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig variableBreakdownConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN,
				true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownConfig.setComponentName("Variable Breakdown: ");
		variableBreakdownConfig.addComponentStyle(GtnFrameworkReportStringConstants.LINK);
		variableBreakdownConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkButtonConfig buttonConfig = new GtnUIFrameworkButtonConfig();
		buttonConfig.setButtonCaptionInUpperCase(false);
		variableBreakdownConfig.setButtonConfig(buttonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig variableBreakdownPopupActionConfig = new GtnUIFrameWorkActionConfig();
		variableBreakdownPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("variableBreakdown");
		params.add("Variable Breakdown Popup");
		params.add(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		params.add(null);
		params.add(null);
		params.add("reportingDashboardScreen");

		variableBreakdownPopupActionConfig.setActionParameterList(params);

		actionConfigList.add(variableBreakdownPopupActionConfig);

		GtnUIFrameWorkActionConfig variableBreakDownGridLoad = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableBreakDownGridLoad.addActionParameter(GtnReportingVariableBreakdownFrequencyLoadAction.class.getName());
		variableBreakDownGridLoad
				.addActionParameter("reportLandingScreen_landingScreenVariableBreakdownFrequencyConfig");
		variableBreakDownGridLoad.addActionParameter("reportOptionsTab_variableBreakdownFrequencyConfig");
		variableBreakDownGridLoad.addActionParameter("reportingDashboardScreen");
		actionConfigList.add(variableBreakDownGridLoad);

		variableBreakdownConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(variableBreakdownConfig);
	}

	private void addVariableAndVarianceSequencingComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_AND_VARIANCE_SEQUENCING_LAYOUT, true,
				"reportOptionsTabCssLayout");
		gtnLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_TOP_17);
		gtnLayout.addComponentStyle("v-layout-custom-report");
		gtnLayout.addComponentStyle("v-report-display-selection-freq");
		gtnLayout.setComponentWidth("36%");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig variableAndVarianceSequencingConfig = configProvider
				.getUIFrameworkComponentConfig(
						nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_AND_VARIANCE_SEQUENCING,
						true,
						GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_AND_VARIANCE_SEQUENCING_LAYOUT,
						GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableAndVarianceSequencingConfig.setComponentName("Variable & Variance Sequencing: ");
		variableAndVarianceSequencingConfig.setAuthorizationIncluded(true);
		variableAndVarianceSequencingConfig.addComboComponentStyle("v-report-reportoptions-vseq");
		variableAndVarianceSequencingConfig.addComboComponentStyle("v-report-width-200");
		componentList.add(variableAndVarianceSequencingConfig);

		GtnUIFrameworkComboBoxConfig variableAndVarianceSequencingLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableAndVarianceSequencingLoadConfig.setItemValues(Arrays.asList(0, 1));
		variableAndVarianceSequencingLoadConfig
				.setItemCaptionValues(Arrays.asList(GtnFrameworkReportStringConstants.VARIABLE_VARIANCE,
						GtnFrameworkReportStringConstants.VARIABLES_VARIANCES));
		variableAndVarianceSequencingLoadConfig.setHasDefaultValue(true);
		variableAndVarianceSequencingLoadConfig.setDefaultDesc(GtnFrameworkReportStringConstants.VARIABLE_VARIANCE);
		variableAndVarianceSequencingConfig.setGtnComboboxConfig(variableAndVarianceSequencingLoadConfig);
	}

	private void addViewOptionsComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VIEW_OPTIONS_LAYOUT, true,
				"reportOptionsTabCssLayout");
		gtnLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_TOP_17);
		gtnLayout.addComponentStyle("stpl-margin-left-15");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig viewOptionsConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VIEW_OPTIONS,
				true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VIEW_OPTIONS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		viewOptionsConfig.addComboComponentStyle("v-report-width-170");
		viewOptionsConfig.setComponentName("View Options: ");
		viewOptionsConfig.setAuthorizationIncluded(true);

		componentList.add(viewOptionsConfig);

		GtnUIFrameworkComboBoxConfig viewOptionsLoadConfig = new GtnUIFrameworkComboBoxConfig();
		viewOptionsLoadConfig.setItemValues(Arrays.asList(1, 2));
		viewOptionsLoadConfig.setItemCaptionValues(Arrays.asList("Standard", "Fully Expanded"));
		viewOptionsConfig.setGtnComboboxConfig(viewOptionsLoadConfig);
	}

	private void addUnitOfMeasureComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE_LAYOUT, true,
				"reportOptionsTabCssLayout");
		gtnLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_TOP_17);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig unitOfMeasureConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE,
				true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		unitOfMeasureConfig.setComponentName("Unit Of Measure: ");
		unitOfMeasureConfig.setAuthorizationIncluded(true);

		GtnUIFrameWorkActionConfig unitOfMeasureLoadConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		unitOfMeasureLoadConfig.addActionParameter(GtnFrameworkUIUOMLoadAction.class.getName());
		unitOfMeasureLoadConfig
				.addActionParameter("reportingDashboardTab" + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE);
		unitOfMeasureConfig.setReloadActionConfig(unitOfMeasureLoadConfig);
		unitOfMeasureConfig.setReloadLogicActionClassName(GtnFrameworkUIUOMLoadAction.class.getName());

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setHasDefaultValue(true);
		comboBoxConfig.setDefaultDesc(GtnFrameworkReportStringConstants.UOM_DEFAULT);
		unitOfMeasureConfig.setGtnComboboxConfig(comboBoxConfig);
		componentList.add(unitOfMeasureConfig);
	}

	private void addHeaderSequencingComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_HEADER_SEQUENCING_LAYOUT, true,
				"reportOptionsTabCssLayoutBottom");
		gtnLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_TOP_17);
		gtnLayout.setComponentWidth("36%");
		gtnLayout.addComponentStyle("v-layout-report-custom");
		gtnLayout.addComponentStyle("v-report-reportoptions-hseq");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig headerSequencingConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_HEADER_SEQUENCING,
				true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_HEADER_SEQUENCING_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		headerSequencingConfig.addComboComponentStyle("v-report-width-200");
		headerSequencingConfig.setComponentName("Header Sequencing:     ");
		headerSequencingConfig.addComponentStyle("v-report-reportoptions-hseq-combo");
		headerSequencingConfig.setAuthorizationIncluded(true);

		componentList.add(headerSequencingConfig);

		GtnUIFrameworkComboBoxConfig headerSequencingLoadConfig = new GtnUIFrameworkComboBoxConfig();
		headerSequencingLoadConfig.setItemValues(Arrays.asList(0, 1, 2, 3));
		headerSequencingLoadConfig
				.setItemCaptionValues(Arrays.asList(GtnFrameworkReportStringConstants.TIME_VARIABLE_COMPARISON,
						GtnFrameworkReportStringConstants.COMPARISON_VARIABLE_TIME,
						GtnFrameworkReportStringConstants.COMPARISON_TIME_VARIABLE,
						GtnFrameworkReportStringConstants.VARIABLE_COMPARISON_TIME));
		headerSequencingLoadConfig.setHasDefaultValue(true);
		headerSequencingLoadConfig.setDefaultDesc(GtnFrameworkReportStringConstants.COMPARISON_VARIABLE_TIME);
		headerSequencingConfig.setGtnComboboxConfig(headerSequencingLoadConfig);
	}

	private void addDisplayFormatComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_DISPLAY_FORMAT_LAYOUT, true,
				"reportOptionsTabCssLayoutBottom");
		gtnLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_TOP_17);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig displayFormatConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_DISPLAY_FORMAT,
				true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_DISPLAY_FORMAT_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		displayFormatConfig.setComponentName("Display Format: ");
		displayFormatConfig.setAuthorizationIncluded(true);
		displayFormatConfig.addComboComponentStyle("v-report-width-170");
		componentList.add(displayFormatConfig);

		GtnUIFrameworkCheckedComboBoxConfig displayFormatLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		displayFormatLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		displayFormatLoadConfig.setSelectedItem("Name");
		displayFormatLoadConfig.setCheckedComboBoxType(GtnFrameworkReportStringConstants.DISPLAY_FORMAT);
		displayFormatConfig.setGtnCheckedComboboxConfig(displayFormatLoadConfig);
	}

	private void addCurrencyDisplayComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CURRENCY_DISPLAY_LAYOUT, true,
				"reportOptionsTabCssLayoutBottom");
		gtnLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_TOP_17);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig currencyDisplayConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CURRENCY_DISPLAY,
				true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CURRENCY_DISPLAY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		currencyDisplayConfig.setComponentName("Currency Display: ");
		currencyDisplayConfig.setAuthorizationIncluded(true);
		componentList.add(currencyDisplayConfig);

		GtnUIFrameworkComboBoxConfig currencyDisplayLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.CURRENCY_DISPLAY, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		currencyDisplayLoadConfig.setDefaultValue("No Conversion");
		currencyDisplayConfig.setGtnComboboxConfig(currencyDisplayLoadConfig);
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String nameSpace) {

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
		generateButton.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "generateButton");
		generateButton.setParentComponentId(controlButtonLayoutConfig.getComponentId());
		generateButton.setAddToParent(true);
		componentList.add(generateButton);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig filterInputLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		filterInputLoadAction.addActionParameter(GtnUIFrameworkReportFilterGenerateLoadAction.class.getName());
		filterInputLoadAction.addActionParameter("reportingDashboardTab_filterOptionsTabDeductionLevel");
		filterInputLoadAction.addActionParameter("reportingDashboardTab_filterOptionsTabCustomerFilter");
		filterInputLoadAction.addActionParameter("reportingDashboardTab_filterOptionsTabProductFilter");
		filterInputLoadAction.addActionParameter("reportingDashboardTab_filterOptionsTabDeductionFilter");
		filterInputLoadAction.addActionParameter("reportDashboard" + GtnFrameworkCommonConstants.RESULT_TABLE);
		actionConfigList.add(filterInputLoadAction);

		GtnUIFrameWorkActionConfig tableLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadAction.addActionParameter(GtnFrameworkUIReportDasboardTableLoadAction.class.getName());
		tableLoadAction.addActionParameter("reportDashboard" + GtnFrameworkCommonConstants.RESULT_TABLE);
		tableLoadAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_SALES_INCLUSION);
		tableLoadAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_INCLUSION);
		tableLoadAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_DISPLAY_FORMAT);
		tableLoadAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_CURRENCY_DISPLAY);
		tableLoadAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CUSTOM_VIEW);
		tableLoadAction.addActionParameter(
				"reportingDashboard_" + GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_FREQUENCY);
		tableLoadAction.addActionParameter(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportingDashboardComparisonConfig");
		tableLoadAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_AND_VARIANCE_SEQUENCING);
		actionConfigList.add(tableLoadAction);

		GtnUIFrameWorkActionConfig expandCollapseAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		expandCollapseAction.addActionParameter(GtnFrameworkReportOptionsViewOptionsAction.class.getName());
		expandCollapseAction.addActionParameter("reportDashboard" + GtnFrameworkCommonConstants.RESULT_TABLE);
		expandCollapseAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VIEW_OPTIONS);
		actionConfigList.add(expandCollapseAction);

		generateButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentName("RESET");
		resetButton.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButton");
		resetButton.setParentComponentId(controlButtonLayoutConfig.getComponentId());
		resetButton.setAddToParent(true);
		componentList.add(resetButton);

		GtnUIFrameWorkActionConfig dashboardValuesResetAction = new GtnUIFrameWorkActionConfig();
		dashboardValuesResetAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		dashboardValuesResetAction.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		dashboardValuesResetAction
				.addActionParameter(GtnFrameworkReportStringConstants.RP_DASH_RESET_CONFIRMATION_MESSAGE);
		List<GtnUIFrameWorkActionConfig> onSuccessConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig valuesResetAction = new GtnUIFrameWorkActionConfig();
		valuesResetAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		valuesResetAction.addActionParameter(GtnReportDashboardValuesResetAction.class.getName());
		valuesResetAction.addActionParameter(nameSpace);
		onSuccessConfigList.add(valuesResetAction);
		dashboardValuesResetAction.addActionParameter(onSuccessConfigList);
		resetButton.addGtnUIFrameWorkActionConfig(dashboardValuesResetAction);

		GtnUIFrameworkComponentConfig saveProfileButton = new GtnUIFrameworkComponentConfig();
		saveProfileButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveProfileButton.setComponentName("SAVE PROFILE");
		saveProfileButton
				.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "saveProfileButton");
		saveProfileButton.setParentComponentId(controlButtonLayoutConfig.getComponentId());
		saveProfileButton.setAddToParent(true);
		componentList.add(saveProfileButton);

		GtnUIFrameWorkActionConfig saveProfileSetValueActionConfig = new GtnUIFrameWorkActionConfig();
		saveProfileSetValueActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveProfileSetValueActionConfig
				.addActionParameter(GtnFrameworkReportingDashboardSaveProfileAction.class.getName());
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_displaySelectionTabVariable");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboard_displaySelectionTabPeriodRangeFrom");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboard_displaySelectionTabPeriodRangeTo");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboard_displaySelectionTabFrequency");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboard_displaySelectionTabComparisonBasis");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_displaySelectionTabCustomView");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_displaySelectionTabVariableCategory");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_displaySelectionTabAnnualTotals");

		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_filterTabCustomerLevel");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_filterOptionsTabCustomerFilter");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_filterOptionsTabProductLevel");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_filterOptionsTabProductFilter");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_filterOptionsTabDeductionLevel");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_filterOptionsTabDeductionFilter");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_filterOptionsTabSalesInclusion");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_filterOptionsTabDeductionInclusion");

		saveProfileSetValueActionConfig
				.addActionParameter("reportingDashboardTab_reportOptionsTabVariableAndVarianceSequencing");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_reportOptionsTabHeaderSequencing");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_reportOptionsTabViewOptions");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_reportOptionsTabDisplayFormat");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_reportOptionsTabUnitOfMeasure");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_reportOptionsTabCurrencyDisplay");
		saveProfileSetValueActionConfig.addActionParameter("reportingDashboardTab_reportingDashboardComparisonConfig");
		saveProfileButton.addGtnUIFrameWorkActionConfig(saveProfileSetValueActionConfig);

		addExpandAndCollapseButtonComponent(componentList, controlButtonLayoutConfig.getComponentId(), nameSpace);
	}

	private void addExpandAndCollapseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String nameSpace) {

		GtnUIFrameworkComponentConfig expandAndCollapseHorizontalConfig = configProvider
				.getHorizontalLayoutConfig("expandAndCollapseHorizontalConfig", true, parentId);

		expandAndCollapseHorizontalConfig.addComponentStyle("stpl-padding-top-12");

		GtnUIFrameworkComponentConfig levelConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE, true,
				"expandAndCollapseHorizontalConfig", GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		levelConfig.setComponentName("Level: ");
		levelConfig.setVaadinComponentPlaceHolder("Select one-");
		GtnUIFrameworkComboBoxConfig levelLoadConfig = configProvider.getComboBoxConfig(
				GtnFrameworkReportStringConstants.STATUS, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		levelConfig.setGtnComboboxConfig(levelLoadConfig);

		GtnUIFrameWorkActionConfig reloadActionConfig = new GtnUIFrameWorkActionConfig();
		reloadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reloadActionConfig.addActionParameter(GtnUIFrameworkReportLevelDdlbLoadAction.class.getName());
		levelConfig.setReloadActionConfig(reloadActionConfig);
		levelConfig.setReloadLogicActionClassName(GtnUIFrameworkReportLevelDdlbLoadAction.class.getName());

		GtnUIFrameworkComponentConfig expandButton = new GtnUIFrameworkComponentConfig();
		expandButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		expandButton.setComponentName("EXPAND");
		expandButton.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "expandButton");
		expandButton.setParentComponentId(parentId);
		expandButton.setAddToParent(true);
		GtnUIFrameWorkActionConfig expandAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		expandAction.addActionParameter(GtnUIReportExpandCollapseAction.class.getName());
		expandAction.addActionParameter("reportDashboard" + GtnFrameworkCommonConstants.RESULT_TABLE);
		expandButton.addGtnUIFrameWorkActionConfig(expandAction);

		GtnUIFrameworkComponentConfig collapseButton = new GtnUIFrameworkComponentConfig();
		collapseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		collapseButton.setComponentName("COLLAPSE");
		collapseButton.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "collapseButton");
		collapseButton.setParentComponentId(parentId);
		collapseButton.setAddToParent(true);
		collapseButton.addGtnUIFrameWorkActionConfig(expandAction);

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
		GtnUIFrameworkComponentConfig resultsLayout = configProvider.getVerticalLayoutConfig("resultsLayout", true,
				resultsPanel.getComponentId());
		resultsLayout.setComponentWidth("100%");
		componentList.add(resultsLayout);
		addResultTable(componentList, resultsLayout.getComponentId());
		String layoutId = addButtonLayout(componentList, nameSpace, resultsLayout.getComponentId());
		addChartButtonComponent(componentList, nameSpace, layoutId);
		addExcelButtonComponent(componentList, nameSpace, layoutId);

	}

	private String addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace,
			String componentId) {
		String buttonLayoutId = nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "buttonLayout";
		GtnUIFrameworkComponentConfig chartButtonConfig = configProvider.getHorizontalLayoutConfig(buttonLayoutId, true,
				componentId);
		chartButtonConfig.setAuthorizationIncluded(true);
		componentList.add(chartButtonConfig);
		return buttonLayoutId;
	}

	private void addChartButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace,
			String layoutId) {
		GtnUIFrameworkComponentConfig chartButtonConfig = new GtnUIFrameworkComponentConfig();
		chartButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		chartButtonConfig
				.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "chartButtonConfig");
		chartButtonConfig.setParentComponentId(layoutId);
		chartButtonConfig.setAddToParent(true);
		GtnUIFrameworkButtonConfig config = new GtnUIFrameworkButtonConfig();
		config.setIconUrl(GtnFrameworkCommonStringConstants.CHART_ICON_URL);
		chartButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		chartButtonConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_65);
		chartButtonConfig.addComponentStyle(GtnFrameworkCssConstants.LINK);
		chartButtonConfig.setAuthorizationIncluded(true);
		chartButtonConfig.setButtonConfig(config);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig chartViewAction = new GtnUIFrameWorkActionConfig();
		chartViewAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		chartViewAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_CHART_LOOKUP_VIEW);
		chartViewAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_CHART_LOOKUP_VIEW);
		chartViewAction.addActionParameter("75%");
		chartViewAction.addActionParameter(null);
		actionConfigList.add(chartViewAction);

		chartButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(chartButtonConfig);

	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig reportingDashboardResultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		reportingDashboardResultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_TREE_GRID);
		reportingDashboardResultTableComponentConfig
				.setComponentId("reportDashboard" + GtnFrameworkCommonConstants.RESULT_TABLE);

		reportingDashboardResultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		reportingDashboardResultTableComponentConfig.addComponentStyle("v-margin-bottom1");
		reportingDashboardResultTableComponentConfig.setAddToParent(true);
		reportingDashboardResultTableComponentConfig.setComponentWidth("100%");
		reportingDashboardResultTableComponentConfig.setComponentHight("450px");
		reportingDashboardResultTableComponentConfig
				.setModuleName(GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME);
		reportingDashboardResultTableComponentConfig.setParentComponentId(parentId);

		GtnUIFrameworkPagedTreeTableConfig reportingDashboardGtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> reportingDashboardActionConfigList = new ArrayList<>();
		reportingDashboardActionConfigList.add(parentId);
		GtnUIFrameWorkActionConfig reportingDashboardGtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardGtnUIFrameWorkActionConfig.setActionParameterList(reportingDashboardActionConfigList);
		reportingDashboardGtnPagedTreeTableConfig
				.setGtnUIFrameWorkActionConfig(reportingDashboardGtnUIFrameWorkActionConfig);

		reportingDashboardGtnPagedTreeTableConfig
				.setLeftHeader(GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
		reportingDashboardGtnPagedTreeTableConfig
				.setRightHeader(GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setCountUrl("");
		reportingDashboardGtnPagedTreeTableConfig.setItemPerPage(10);

		reportingDashboardGtnPagedTreeTableConfig.setMaxSplitPosition(1000);
		reportingDashboardGtnPagedTreeTableConfig.setMinSplitPosition(300);
		reportingDashboardGtnPagedTreeTableConfig.setPageLength(10);
		reportingDashboardGtnPagedTreeTableConfig.setResultSetUrl("");

		reportingDashboardGtnPagedTreeTableConfig.setSplitPosition(493);

		reportingDashboardGtnPagedTreeTableConfig.setTableHeight("650px");
		reportingDashboardGtnPagedTreeTableConfig.setDoubleHeaderVisible(true);
		reportingDashboardGtnPagedTreeTableConfig.setTripleHeaderVisible(true);

		reportingDashboardGtnPagedTreeTableConfig.setLeftTableEditable(true);
		reportingDashboardGtnPagedTreeTableConfig.setRightTableEditable(true);
		reportingDashboardGtnPagedTreeTableConfig.setAggregationColumnHeader("Total");

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
		reportingDashboardCheckBox
				.setGtnUIFrameWorkItemClickActionConfigList(reportingDashboardCheckBoxClickActionList);

		reportingDashboardFieldFactoryComponent.add(reportingDashboardCheckBox);
		reportingDashboardGtnPagedTreeTableConfig
				.setLeftTableEditableComponentConfig(reportingDashboardFieldFactoryComponent);

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

		List<GtnUIFrameWorkActionConfig> reportingDashboardCheckAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardCheckAllActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardCheckAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardCheckAllActionConfig
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_CHECK_ALL_ACTION);
		reportingDashboardCheckAllConflist.add(reportingDashboardCheckAllActionConfig);
		reportingDashboardGtnPagedTreeTableConfig.setCheckBoxActionConfigList(reportingDashboardCheckAllConflist);
		reportingDashboardGtnPagedTreeTableConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_DASHBOARD_LEFT_DATA);
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

		reportingDashboardGtnPagedTreeTableConfig
				.setRightHeaderCustomClassLoadUrl(GtnFrameworkReportDashBoardRightHeaderRequestAction.class.getName());

		reportingDashboardGtnPagedTreeTableConfig
				.setGridRequestGenerateActionClass(GtnFrameworkUIReportGenerateRequestAction.class.getName());

		reportingDashboardGtnPagedTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME);
		reportingDashboardGtnPagedTreeTableConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_DASHBOARD_LEFT_DATA);

		reportingDashboardResultTableComponentConfig
				.setGtnPagedTreeTableConfig(reportingDashboardGtnPagedTreeTableConfig);
		componentList.add(reportingDashboardResultTableComponentConfig);
	}

	private void addNavigationButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace,
			String parentId) {
		GtnUIFrameworkComponentConfig reportDashboardControlLayout = configProvider
				.getHorizontalLayoutConfig("reportDashboardControlLayout", true, parentId);
		reportDashboardControlLayout.addComponentStyle("stpl-margin-left-25");
		reportDashboardControlLayout.addComponentStyle("stpl-margin-bottom-13");
		componentList.add(reportDashboardControlLayout);

		GtnUIFrameworkComponentConfig previousButtonConfig = new GtnUIFrameworkComponentConfig();
		previousButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButtonConfig
				.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "previousButtonConfig");
		previousButtonConfig.setComponentName("PREVIOUS");
		previousButtonConfig.setAddToParent(true);
		previousButtonConfig.setParentComponentId(reportDashboardControlLayout.getComponentId());
		GtnUIFrameWorkActionConfig previousButtonAction = new GtnUIFrameWorkActionConfig();
		previousButtonAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		previousButtonAction.addActionParameter(GtnFrameworkReportTabChangeAction.class.getName());
		previousButtonAction.addActionParameter("tabSheetMain");
		previousButtonAction.addActionParameter("1");
		previousButtonConfig.addGtnUIFrameWorkActionConfig(previousButtonAction);

		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig
				.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "closeButtonConfig");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAddToParent(true);
		closeButtonConfig.setParentComponentId(reportDashboardControlLayout.getComponentId());
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkReportStringConstants.CONFIRMATION);
		alertParamsList.add("Are you sure you want to close this Report?");
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		GtnUIFrameWorkActionConfig closeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeActionConfig.addActionParameter(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP_VIEW);
		onSucessActionConfigList.add(closeActionConfig);
		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);
		GtnUIFrameWorkActionConfig resetLandingScreenAction = new GtnUIFrameWorkActionConfig();
		resetLandingScreenAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetLandingScreenAction.setActionParameterList(getResetParameters());
		onSucessActionConfigList.add(resetLandingScreenAction);
		closeButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(previousButtonConfig);
		componentList.add(closeButtonConfig);
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace,
			String parentId) {

		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig
				.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "excelButtonConfig");
		excelButtonConfig.setParentComponentId(parentId);
		excelButtonConfig.setAddToParent(true);
		excelButtonConfig.addComponentStyle("v-report-excel-button");
		excelButtonConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonInput = new GtnUIFrameworkExcelButtonConfig();
		gtnUIFrameworkExcelButtonInput.setIsTreeTable(true);
		gtnUIFrameworkExcelButtonInput.setExportFileName("Report");
		gtnUIFrameworkExcelButtonInput.setExportTableId("reportDashboard" + GtnFrameworkCommonConstants.RESULT_TABLE);

		GtnUIFrameWorkActionConfig resultTableExcelAction = new GtnUIFrameWorkActionConfig();
		resultTableExcelAction.setActionType(GtnUIFrameworkActionType.TREEGRID_EXCEL_EXPORT_ACTION);
		resultTableExcelAction.addActionParameter(gtnUIFrameworkExcelButtonInput);

		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonInput);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(resultTableExcelAction));
		componentList.add(excelButtonConfig);
	}

	private GtnUIFrameWorkActionConfig getFilterReloadAction(String componentId, String indicator, String nameSpace) {
		GtnUIFrameWorkActionConfig filterAction = new GtnUIFrameWorkActionConfig();
		filterAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		filterAction.addActionParameter(GtnReportFilterReloadAction.class.getName());
		filterAction.addActionParameter(componentId);
		filterAction.addActionParameter(indicator);

		filterAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_FILTER_TAB_CUSTOMER_LEVEL);
		filterAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_LEVEL);
		filterAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_LEVEL);
		filterAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_CUSTOMER_FILTER);
		filterAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_PRODUCT_FILTER);
		filterAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.FILTER_OPTIONS_TAB_DEDUCTION_FILTER);

		return filterAction;
	}

	private GtnUIFrameWorkActionConfig getLevelFilterReloadAction(String componentId, Object indicator,
			String nameSpace) {
		GtnUIFrameWorkActionConfig filterAction = new GtnUIFrameWorkActionConfig();
		filterAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		filterAction.addActionParameter(GtnReportLevelFilterReloadAction.class.getName());
		filterAction.addActionParameter(componentId);
		filterAction.addActionParameter(indicator);

		return filterAction;
	}

}
