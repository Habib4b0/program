package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkPublicViewDeleteValidation;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportResetAndCloseAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportCCPTableLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionResultsLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionDeleteViewAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionResetAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkSaveViewAction;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.hierarchy.config.GtnFrameworkReportCustHierarchyConfig;
import com.stpl.gtn.gtn2o.ui.hierarchy.config.GtnFrameworkReportProdHierarchyConfig;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnForecastEligibleDateLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownFrequencyLoadAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;

public class GtnFrameworkReportLandingScreenConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getLandingScreenView(String namespace) {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "View");
		view.setViewId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "V001");
		view.setDefaultView(true);
		view.setResetAllowed(false);
		addComponentList(view, namespace);
		view.addViewAction(loadForecastEligibleDate());
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);

		configureMainLayouts(componentList, namespace);

		addReportOptionsPanel(componentList, namespace);
		addPrivateViewCompanyName(componentList, namespace);
		addPublicViewBusinessUnit(componentList, namespace);
		addTimePeriod(componentList, namespace);
		addCustomerSelectionLayout(componentList, namespace);
		addProductSelectionLayout(componentList, namespace);

		GtnFrameworkReportCustHierarchyConfig customerSelection = new GtnFrameworkReportCustHierarchyConfig();
		componentList.addAll(
				customerSelection.getCustomerSelectionLayoutComponents(namespace, "reportCustomerHierarchyLookup"));

		GtnFrameworkReportProdHierarchyConfig productSelection = new GtnFrameworkReportProdHierarchyConfig();
		componentList.addAll(productSelection.getProductSelectionLayoutComponents(namespace, "landingScreen"));

		addReportingNecessaryFields(componentList, namespace);
		addControlButtonLayout(componentList, namespace);
	}

	private void addReportingNecessaryFields(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig reportHorizontalCssLayout = new GtnUIFrameworkLayoutConfig();
		reportHorizontalCssLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportHorizontalCssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportHorizontalCssLayout", true,
				"reportLandingScreen_prodSelectionMainlayout", GtnUIFrameworkComponentType.LAYOUT);

		reportHorizontalCssGtnLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportHorizontalCssGtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		reportHorizontalCssGtnLayout.setGtnLayoutConfig(reportHorizontalCssLayout);

		GtnUIFrameworkLayoutConfig reportCssLayout = new GtnUIFrameworkLayoutConfig();
		reportCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportCssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT,
				true, namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportHorizontalCssLayout",
				GtnUIFrameworkComponentType.LAYOUT);

		reportCssGtnLayout.setGtnLayoutConfig(reportCssLayout);

		componentList.add(reportHorizontalCssGtnLayout);
		componentList.add(reportCssGtnLayout);

		addLandingScreenVariableComponent(componentList, namespace);
		addLandingScreenComparisonComponent(componentList, namespace);
		addLandingScreenVariableBreakdownComponent(componentList, namespace);
		addLandingScreenFrequencyComponent(componentList, namespace);
	}

	private void addLandingScreenFrequencyComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig landingScreenVariableBreakdownFrequencyLayoutConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownFrequencyLayoutConfig",
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		landingScreenVariableBreakdownFrequencyLayoutConfig
				.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_25_PX);
		GtnUIFrameworkComponentConfig landingScreenVariableBreakdownFrequencyConfig = new GtnUIFrameworkComponentConfig();
		landingScreenVariableBreakdownFrequencyConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		landingScreenVariableBreakdownFrequencyConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.LANDINGSCREEN_VARIABLE_BREAKDOWN_FREQUENCY_CONFIG);
		landingScreenVariableBreakdownFrequencyConfig.setComponentName("Frequency: ");
		landingScreenVariableBreakdownFrequencyConfig.setAddToParent(true);
		landingScreenVariableBreakdownFrequencyConfig
				.setParentComponentId(landingScreenVariableBreakdownFrequencyLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig landingScreenVariableBreakdownLoadConfig = new GtnUIFrameworkComboBoxConfig();
		landingScreenVariableBreakdownLoadConfig.setHasDefaultValue(true);
		landingScreenVariableBreakdownLoadConfig.setDefaultDesc(GtnFrameworkReportStringConstants.QUARTER);
		landingScreenVariableBreakdownLoadConfig
				.setComboBoxType(GtnFrameworkReportStringConstants.REPORT_CONFIG_FREQUENCY);
		landingScreenVariableBreakdownLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		landingScreenVariableBreakdownFrequencyConfig.setGtnComboboxConfig(landingScreenVariableBreakdownLoadConfig);

		componentList.add(landingScreenVariableBreakdownFrequencyLayoutConfig);
		componentList.add(landingScreenVariableBreakdownFrequencyConfig);
	}

	private void addLandingScreenComparisonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig hierarchyLayout = configProvider.getHorizontalLayoutConfig(
				"comparisonHierarchyLayout", true, namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		hierarchyLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_25_PX);
		componentList.add(hierarchyLayout);
		GtnUIFrameworkComponentConfig landingScreenVariableComparisonConfig = configProvider
				.getUIFrameworkComponentConfig(
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_COMPARISON_CONFIG,
						true, "comparisonHierarchyLayout", GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		landingScreenVariableComparisonConfig.setAuthorizationIncluded(true);
		landingScreenVariableComparisonConfig.setComponentName("Comparison: ");
		landingScreenVariableComparisonConfig.setEnable(false);
		GtnUIFrameworkTextBoxConfig landingScreenVariableComparisonTextboxConfig = configProvider.getTextBoxConfig(true,
				true, true);
		landingScreenVariableComparisonTextboxConfig.setEnable(false);
		landingScreenVariableComparisonTextboxConfig.setReadOnly(false);
		landingScreenVariableComparisonConfig.setGtnTextBoxConfig(landingScreenVariableComparisonTextboxConfig);

		componentList.add(landingScreenVariableComparisonConfig);

		List<GtnUIFrameWorkActionConfig> landingScreenVariableComparisonPopupActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardComparisonPopupConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		reportingDashboardComparisonPopupConfig.addActionParameter("comparisonLookupView");
		reportingDashboardComparisonPopupConfig.addActionParameter("Comparison Popup");
		reportingDashboardComparisonPopupConfig.addActionParameter(GtnFrameworkReportStringConstants.EIGHTY_PERCENT);
		reportingDashboardComparisonPopupConfig.addActionParameter(GtnFrameworkReportStringConstants.EIGHTY_PERCENT);
		reportingDashboardComparisonPopupConfig.addActionParameter(null);
		landingScreenVariableComparisonPopupActionList.add(reportingDashboardComparisonPopupConfig);

		GtnUIFrameWorkActionConfig landingScreenComparisonLookupGridLoadAction = new GtnUIFrameWorkActionConfig();
		landingScreenComparisonLookupGridLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		landingScreenComparisonLookupGridLoadAction
				.addActionParameter(GtnReportComparisonProjectionResultsLoadAction.class.getName());
		landingScreenComparisonLookupGridLoadAction
				.addActionParameter("comparisonLookupProjectionsResultsPagedTableComponent");
		landingScreenComparisonLookupGridLoadAction
				.addActionParameter(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_REPORTING_DASHBOARD_COMPARISON_CONFIG);
		landingScreenVariableComparisonPopupActionList.add(landingScreenComparisonLookupGridLoadAction);

		landingScreenVariableComparisonConfig
				.setGtnUIFrameWorkActionConfigList(landingScreenVariableComparisonPopupActionList);

	}

	private void addLandingScreenVariableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig landingScreenComparisonGtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_DISPLAY_SELECTION_TAB_VARIABLE_LAYOUT, true,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		landingScreenComparisonGtnLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_25_PX);
		landingScreenComparisonGtnLayout.setMargin(true);
		landingScreenComparisonGtnLayout.setSpacing(true);
		componentList.add(landingScreenComparisonGtnLayout);

		GtnUIFrameworkComponentConfig landingScreenComparisonVariableConfig = configProvider
				.getUIFrameworkComponentConfig(
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.REPORT_DISPLAY_SELECTION_TAB_VARIABLE,
						true, GtnFrameworkReportStringConstants.REPORT_DISPLAY_SELECTION_TAB_VARIABLE_LAYOUT,
						GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		landingScreenComparisonVariableConfig.setComponentName("Variables: ");
		landingScreenComparisonVariableConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkCheckedComboBoxConfig landingScreenComparisonVariableLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		GtnWsReportVariablesType[] variableType = Arrays.copyOfRange(GtnWsReportVariablesType.values(), 0,
				GtnWsReportVariablesType.values().length - 1);
		landingScreenComparisonVariableLoadConfig.setItemValueList(
				Arrays.stream(variableType).map(GtnWsReportVariablesType::toString).collect(Collectors.toList()));
		landingScreenComparisonVariableLoadConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		landingScreenComparisonVariableConfig.setGtnCheckedComboboxConfig(landingScreenComparisonVariableLoadConfig);
		componentList.add(landingScreenComparisonVariableConfig);
	}

	private void addLandingScreenVariableBreakdownComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig landingScreenVariableBreakdownGtnLayout = configProvider
				.getHorizontalLayoutConfig(
						GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN_LAYOUT, true,
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		landingScreenVariableBreakdownGtnLayout.addComponentStyle("stpl-padding-top-12");
		componentList.add(landingScreenVariableBreakdownGtnLayout);

		GtnUIFrameworkComponentConfig landingScreenVariableBreakdownConfig = configProvider
				.getUIFrameworkComponentConfig(
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN,
						true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN_LAYOUT,
						GtnUIFrameworkComponentType.BUTTON);
		landingScreenVariableBreakdownConfig.setComponentName("Variable Breakdown: ");
		landingScreenVariableBreakdownConfig.addComponentStyle(GtnFrameworkReportStringConstants.LINK);
		landingScreenVariableBreakdownConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> landingScreenVariableBreakdownActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig landingScreenVariableBreakdownPopupActionConfig = new GtnUIFrameWorkActionConfig();
		landingScreenVariableBreakdownPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("variableBreakdown");
		params.add("Variable Breakdown Popup");
		params.add(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		params.add(null);
		params.add(null);
		params.add("reportingLandingScreen");

		landingScreenVariableBreakdownPopupActionConfig.setActionParameterList(params);
		landingScreenVariableBreakdownActionConfigList.add(landingScreenVariableBreakdownPopupActionConfig);

		GtnUIFrameWorkActionConfig variableBreakDownGridLoad = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableBreakDownGridLoad.addActionParameter(GtnReportingVariableBreakdownFrequencyLoadAction.class.getName());
		variableBreakDownGridLoad
				.addActionParameter("reportLandingScreen_landingScreenVariableBreakdownFrequencyConfig");
		variableBreakDownGridLoad.addActionParameter("reportOptionsTab_variableBreakdownFrequencyConfig");
		variableBreakDownGridLoad.addActionParameter("reportingLandingScreen");
		landingScreenVariableBreakdownActionConfigList.add(variableBreakDownGridLoad);

		landingScreenVariableBreakdownConfig
				.setGtnUIFrameWorkActionConfigList(landingScreenVariableBreakdownActionConfigList);

		componentList.add(landingScreenVariableBreakdownConfig);
	}

	private void configureMainLayouts(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig dataSelectionMainLayout = new GtnUIFrameworkLayoutConfig();
		dataSelectionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig dataSelectionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		dataSelectionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dataSelectionMainLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DATA_SELECTION_MAIN_LAYOUT);
		dataSelectionMainLayoutConfig.setAddToParent(false);
		dataSelectionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dataSelectionMainLayoutConfig.setGtnLayoutConfig(dataSelectionMainLayout);
		componentList.add(dataSelectionMainLayoutConfig);

	}

	private void addReportOptionsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig reportOptionsPanel = new GtnUIFrameworkComponentConfig();
		reportOptionsPanel.setComponentName("Report Options");
		reportOptionsPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportOptions");
		reportOptionsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		reportOptionsPanel.setComponentHight("200px");
		reportOptionsPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DATA_SELECTION_MAIN_LAYOUT);
		reportOptionsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportOptionsPanel.setAddToParent(true);
		reportOptionsPanel.setSpacing(true);
		componentList.add(reportOptionsPanel);

		GtnUIFrameworkLayoutConfig reportOptionsMainLayout = new GtnUIFrameworkLayoutConfig();
		reportOptionsMainLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportOptionsMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportOptionsMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportOptionsMainLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_MAIN_LAYOUT);
		reportOptionsMainLayoutConfig.setAddToParent(true);
		reportOptionsMainLayoutConfig.addComponentStyle("v-report-overflow-auto");
		reportOptionsMainLayoutConfig.setComponentHight("155px");
		reportOptionsMainLayoutConfig.setSpacing(true);
		reportOptionsMainLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportOptions");
		reportOptionsMainLayoutConfig.setGtnLayoutConfig(reportOptionsMainLayout);
		componentList.add(reportOptionsMainLayoutConfig);

		GtnUIFrameworkLayoutConfig reportOptionsInnerLayout = new GtnUIFrameworkLayoutConfig();
		reportOptionsInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportOptionsInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportOptionsInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportOptionsInnerLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_INNER_LAYOUT);
		reportOptionsInnerLayoutConfig.setAddToParent(true);
		reportOptionsInnerLayoutConfig.setSpacing(true);
		reportOptionsInnerLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_MAIN_LAYOUT);
		reportOptionsInnerLayoutConfig.setGtnLayoutConfig(reportOptionsInnerLayout);
		componentList.add(reportOptionsInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig modeSelectionlayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionlayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig modeLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_LAYOUT);
		modeLayoutConfig.setAddToParent(true);
		modeLayoutConfig.setGtnLayoutConfig(modeSelectionlayout);
		modeLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_INNER_LAYOUT);
		componentList.add(modeLayoutConfig);

		GtnUIFrameworkLayoutConfig modeSelectionCsslayoutConfig = new GtnUIFrameworkLayoutConfig();
		modeSelectionCsslayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig modeSelectionCsslayout = new GtnUIFrameworkComponentConfig();
		modeSelectionCsslayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeSelectionCsslayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_CSS_LAYOUT);
		modeSelectionCsslayout.setAddToParent(true);
		modeSelectionCsslayout.setGtnLayoutConfig(modeSelectionCsslayoutConfig);
		modeSelectionCsslayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_INNER_LAYOUT);
		componentList.add(modeSelectionCsslayout);
	}

	private void addPrivateViewCompanyName(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig privateViewLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig privateViewLayoutConfig = new GtnUIFrameworkComponentConfig();
		privateViewLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewLayout");
		privateViewLayoutConfig.setAddToParent(true);
		privateViewLayoutConfig.setSpacing(true);
		privateViewLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_LAYOUT);
		privateViewLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		privateViewLayoutConfig.setGtnLayoutConfig(privateViewLayout);
		componentList.add(privateViewLayoutConfig);

		GtnUIFrameworkComponentConfig privateView = new GtnUIFrameworkComponentConfig();
		privateView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		privateView.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEWS);
		privateView.setComponentName("Private Views:");
		privateView.setAddToParent(true);
		privateView
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewLayout");
		privateView.addTextComponentStyle("v-reportLandingScreen-PrivateView");
		privateView.setComponentWsFieldId(GtnFrameworkCommonConstants.PRIVATE_VIEWS);

		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		Object privateViewLookup = "Private View";
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(Arrays.asList("privateViewSearchLookupView", privateViewLookup, "50%", "94%",
				GtnFrameworkReportResetAndCloseAction.class.getName(),
				Arrays.asList("reportPrivateViewSearchLookUp" + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE),
				Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY)));
		list.add(conf);
		privateView.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(privateView);

		GtnUIFrameworkLayoutConfig companyLayout = new GtnUIFrameworkLayoutConfig();
		companyLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig companyLayoutConfig = new GtnUIFrameworkComponentConfig();
		companyLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		companyLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "companyLayout");
		companyLayoutConfig.setAddToParent(true);
		companyLayoutConfig.setSpacing(true);
		companyLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_LAYOUT);
		companyLayoutConfig.addComponentStyle("v-reportLandingScreen-companyLayout");
		companyLayoutConfig.setGtnLayoutConfig(companyLayout);
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		company.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY);
		company.setComponentName("Company:");
		company.setAddToParent(true);
		company.addComboComponentStyle("v-reportLandingScreen-company");
		company.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "companyLayout");

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		companyTypeConfig.setFocus(true);
		company.setGtnComboboxConfig(companyTypeConfig);

		GtnUIFrameworkValidationConfig companyTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		companyTypeValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		company.setGtnUIFrameworkValidationConfig(companyTypeValidationConfig);

		componentList.add(company);

		GtnUIFrameworkLayoutConfig reportDataSourceLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSourceLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSourceLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSourceLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSourceLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportDataSourceLayout");
		reportDataSourceLayoutConfig.setAddToParent(true);
		reportDataSourceLayoutConfig.addComponentStyle("v-reportLandingScreen-dataSourceLayout");
		reportDataSourceLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_LAYOUT);
		reportDataSourceLayoutConfig.setGtnLayoutConfig(reportDataSourceLayout);
		componentList.add(reportDataSourceLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSource = new GtnUIFrameworkComponentConfig();
		reportDataSource.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSource.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_DATA_SOURCE);
		reportDataSource.setComponentName("Report Data Source:");
		reportDataSource.setAddToParent(true);
		reportDataSource.addComboComponentStyle("v-reportLandingScreen-dataSource");
		reportDataSource.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportDataSourceLayout");

		GtnUIFrameworkComboBoxConfig reportDataSourceLoadConfig = new GtnUIFrameworkComboBoxConfig();
		reportDataSourceLoadConfig.setItemValues(Arrays.asList(1, 2, 3));
		reportDataSourceLoadConfig
				.setItemCaptionValues(GtnFrameworkReportStringConstants.getReportDataSourceLoadCombobox());
		reportDataSource.setGtnComboboxConfig(reportDataSourceLoadConfig);

		GtnUIFrameworkValidationConfig reportDataSourceValidationConfig = new GtnUIFrameworkValidationConfig();
		reportDataSourceValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		reportDataSource.setGtnUIFrameworkValidationConfig(reportDataSourceValidationConfig);

		componentList.add(reportDataSource);
	}

	private void addPublicViewBusinessUnit(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig publicViewLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLayout");
		publicViewLayoutConfig.setAddToParent(true);
		publicViewLayoutConfig.setSpacing(true);
		publicViewLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_CSS_LAYOUT);
		publicViewLayoutConfig.setGtnLayoutConfig(publicViewLayout);
		componentList.add(publicViewLayoutConfig);

		GtnUIFrameworkComponentConfig publicView = new GtnUIFrameworkComponentConfig();
		publicView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		publicView.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS);
		publicView.setComponentName("Public Views:");
		publicView.setAddToParent(true);
		publicView.addTextComponentStyle("v-publicViewTextField");
		publicView.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLayout");

		List<GtnUIFrameWorkActionConfig> publicViewActionConfigList = new ArrayList<>();
		Object publicViewLookup = "Public View";
		GtnUIFrameWorkActionConfig publicViewActionConfig = new GtnUIFrameWorkActionConfig();
		publicViewActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		publicViewActionConfig.setActionParameterList(Arrays.asList("publicViewSearchLookupView", publicViewLookup,
				"50%", "94%", GtnFrameworkReportResetAndCloseAction.class.getName(),
				Arrays.asList("reportPublicViewSearchLookUp" + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE),
				Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY)));
		publicViewActionConfigList.add(publicViewActionConfig);

		GtnUIFrameWorkActionConfig publicViewDisableAction = new GtnUIFrameWorkActionConfig();
		publicViewDisableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		publicViewDisableAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewSelectButton");
		publicViewActionConfigList.add(publicViewDisableAction);

		publicView.setGtnUIFrameWorkActionConfigList(publicViewActionConfigList);

		componentList.add(publicView);

		GtnUIFrameworkLayoutConfig businessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		businessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		businessUnitLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "businessUnitLayout");
		businessUnitLayoutConfig.setAddToParent(true);
		businessUnitLayoutConfig.setSpacing(true);
		businessUnitLayoutConfig.addComponentStyle("v-BU-layout");
		businessUnitLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_CSS_LAYOUT);
		businessUnitLayoutConfig.setGtnLayoutConfig(businessUnitLayout);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		businessUnit.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT);
		businessUnit.setComponentName("Business Unit:");
		businessUnit.addComboComponentStyle("v-bu");
		businessUnit.setAddToParent(true);
		businessUnit
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "businessUnitLayout");
		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType(GtnFrameworkReportStringConstants.BUSINESS_UNIT_GLCOMP);
		businessUnit.setGtnComboboxConfig(businessUnitConfig);

		GtnUIFrameworkValidationConfig businessUnitValidationConfig = new GtnUIFrameworkValidationConfig();
		businessUnitValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		businessUnit.setGtnUIFrameworkValidationConfig(businessUnitValidationConfig);

		componentList.add(businessUnit);
	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Time Period");
		panel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setAddToParent(true);
		panel.setComponentHight("100px");
		panel.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		panel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_MAIN_LAYOUT);
		componentList.add(panel);

		GtnUIFrameworkLayoutConfig timePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		timePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromAndToperiodLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		fromAndToperiodLayoutConfig.setSpacing(true);
		fromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		fromAndToperiodLayoutConfig.setAddToParent(true);
		fromAndToperiodLayoutConfig.setGtnLayoutConfig(timePeriodInnerLayout);
		fromAndToperiodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		fromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(fromAndToperiodLayoutConfig);

		GtnUIFrameworkLayoutConfig fromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		fromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig fromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		fromPeriodLayoutConfig.setAddToParent(true);
		fromPeriodLayoutConfig.setGtnLayoutConfig(fromPeriodLayout);
		fromPeriodLayoutConfig.addComponentStyle("v-reportLandingScreen-fromPeriodLayout");
		fromPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		fromPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD);
		fromPeriod.setComponentName("From: ");
		fromPeriod.setAddToParent(true);
		fromPeriod.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		GtnUIFrameworkComboBoxConfig fromPeriodConfig = new GtnUIFrameworkComboBoxConfig();

		fromPeriodConfig.setModuleName("report");
		fromPeriodConfig.setLoadingUrl("/gtnReport/gtnWsReportComboboxLoad");
		fromPeriodConfig.setComboBoxType("timePeriodForReportFromDate");
		fromPeriodConfig.setHasDefaultValue(true);
		fromPeriodConfig.setDefaultDesc("next");
		fromPeriod.setGtnComboboxConfig(fromPeriodConfig);

		GtnUIFrameworkValidationConfig fromPeriodValidationConfig = new GtnUIFrameworkValidationConfig();
		fromPeriodValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		fromPeriod.setGtnUIFrameworkValidationConfig(fromPeriodValidationConfig);

		componentList.add(fromPeriod);

		GtnUIFrameworkLayoutConfig toPeriodLayout = new GtnUIFrameworkLayoutConfig();
		toPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig toPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		toPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		toPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");
		toPeriodLayoutConfig.setAddToParent(true);
		toPeriodLayoutConfig.setGtnLayoutConfig(toPeriodLayout);
		toPeriodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		toPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		componentList.add(toPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig toPeriod = new GtnUIFrameworkComponentConfig();
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		toPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.STATUS);
		toPeriod.setComponentName("To: ");
		toPeriod.setAddToParent(true);
		toPeriod.setEnable(false);
		toPeriod.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");

		GtnUIFrameworkComboBoxConfig toPeriodTypeConfig = new GtnUIFrameworkComboBoxConfig();

		toPeriodTypeConfig.setModuleName("report");
		toPeriodTypeConfig.setLoadingUrl("/gtnReport/gtnWsReportComboboxLoad");
		toPeriodTypeConfig.setComboBoxType("timePeriodForReportToDate");
		toPeriodTypeConfig.setHasDefaultValue(true);
		toPeriodTypeConfig.setDefaultDesc("next");

		toPeriod.setGtnComboboxConfig(toPeriodTypeConfig);

		componentList.add(toPeriod);

	}

	private void addProductSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig productSelectionPanel = new GtnUIFrameworkComponentConfig();
		productSelectionPanel.setComponentName("Product Selection");
		productSelectionPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productSelectionPanel");
		productSelectionPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DATA_SELECTION_MAIN_LAYOUT);
		productSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		productSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		productSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		productSelectionPanel.setSpacing(true);
		productSelectionPanel.setAddToParent(true);
		componentList.add(productSelectionPanel);

	}

	private void addCustomerSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig customerSelectionPanel = new GtnUIFrameworkComponentConfig();
		customerSelectionPanel.setComponentName("Customer Selection");
		customerSelectionPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionPanel");
		customerSelectionPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DATA_SELECTION_MAIN_LAYOUT);
		customerSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		customerSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		customerSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		customerSelectionPanel.setSpacing(true);
		customerSelectionPanel.setAddToParent(true);
		componentList.add(customerSelectionPanel);

	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnFrameworkReportLayoutsConfig layoutConfig = new GtnFrameworkReportLayoutsConfig();
		GtnUIFrameworkComponentConfig controlMainLayouts = layoutConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsControlMainLayout",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DATA_SELECTION_MAIN_LAYOUT);
		controlMainLayouts.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		controlMainLayouts.setSpacing(true);
		controlMainLayouts.setMargin(true);
		componentList.add(controlMainLayouts);

		GtnUIFrameworkComponentConfig controlLayouts = layoutConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsControlLayout",
				controlMainLayouts.getComponentId());
		componentList.add(controlLayouts);

		GtnUIFrameworkComponentConfig generateBtn = new GtnUIFrameworkComponentConfig();
		generateBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateBtn.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsGenerate");
		generateBtn.setComponentName("GENERATE");
		generateBtn.setParentComponentId(controlLayouts.getComponentId());
		generateBtn.setAddToParent(true);

		componentList.add(generateBtn);
		List<GtnUIFrameWorkActionConfig> actionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig generateAction = new GtnUIFrameWorkActionConfig();
		generateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		generateAction.addActionParameter(GtnReportCCPTableLoadAction.class.getName());
		generateAction.addActionParameter("reportLandingScreen_customerDualListBox");
		generateAction.addActionParameter("reportLandingScreen_productdualListBoxComp");
		generateAction.addActionParameter("reportLandingScreen_customerHierarchy");
		generateAction.addActionParameter("reportLandingScreen_customerSelectionRelationship");
		generateAction.addActionParameter("reportLandingScreen_customerRelationshipVersion");
		generateAction.addActionParameter("reportLandingScreen_customerSelectionLevel");
		generateAction.addActionParameter("reportLandingScreen_customerSelectionForecastEligibilityDate");
		generateAction.addActionParameter("reportLandingScreen_producthierarchy");
		generateAction.addActionParameter("reportLandingScreen_relationship");
		generateAction.addActionParameter("reportLandingScreen_level");
		generateAction.addActionParameter("reportLandingScreen_productRelationshipVersion");
		generateAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_DATA_SOURCE);
		generateAction.addActionParameter("reportLandingScreen_company");
		generateAction.addActionParameter("reportLandingScreen_businessUnit");
		generateAction.addActionParameter("reportLandingScreen_fromPeriod");
		generateAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_REPORTING_DASHBOARD_COMPARISON_CONFIG);
		generateAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_DISPLAY_SELECTION_TAB_CUSTOM_VIEW);
		generateAction.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.LANDINGSCREEN_VARIABLE_BREAKDOWN_FREQUENCY_CONFIG);
		generateAction.addActionParameter("reportLandingScreen_toPeriod");
		generateAction.addActionParameter("reportLandingScreen_STATUS");
		generateAction.addActionParameter("variableBreakdownResultsLayout_comparisonLookupResultsPagedTableComponent");
		generateAction.addActionParameter("reportLandingScreen_displaySelectionTabVariable");
		generateAction.addActionParameter("reportLandingScreen_privateViews");
		generateAction.addActionParameter("reportLandingScreen_publicViews");
		actionList.add(generateAction);
		generateBtn.setGtnUIFrameWorkActionConfigList(actionList);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsReset");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(controlLayouts.getComponentId());
		resetButton.setAddToParent(true);
		componentList.add(resetButton);

		List<GtnUIFrameWorkActionConfig> resetActionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmResetAction = new GtnUIFrameWorkActionConfig();
		confirmResetAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmResetAction.addActionParameter("Confirm Reset");
		confirmResetAction.addActionParameter("Are you sure you want to reset the page to default values?");
		List<GtnUIFrameWorkActionConfig> onSuccessActionConfigList = new ArrayList<>();
		confirmResetAction.addActionParameter(onSuccessActionConfigList);

		GtnUIFrameWorkActionConfig resetAction = new GtnUIFrameWorkActionConfig();
		resetAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetAction.setActionParameterList(Arrays.asList(GtnReportDataSelectionResetAction.class.getName(),
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.COMPANY,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.BUSINESS_UNIT,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_DATA_SOURCE,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FROM_PERIOD,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "STATUS",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerHierarchy",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_SELECTION_RELATIONSHIP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_SELECTION_LEVEL,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerRelationshipVersion",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionForecastEligibilityDate",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_DUAL_LIST_BOX,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "producthierarchy",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.RELATIONSHIP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.LEVEL_LOWERCASE,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productRelationshipVersion",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRODUCTDUAL_LIST_BOX_COMP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "displaySelectionTabCustomView",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "displaySelectionTabVariable",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_COMPARISON_CONFIG,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.LANDINGSCREEN_VARIABLE_BREAKDOWN_FREQUENCY_CONFIG,
						GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_REPORTING_DASHBOARD_COMPARISON_CONFIG));
		onSuccessActionConfigList.add(resetAction);
		resetActionList.add(confirmResetAction);
		resetButton.setGtnUIFrameWorkActionConfigList(resetActionList);

		GtnUIFrameworkComponentConfig saveViewBtn = new GtnUIFrameworkComponentConfig();
		saveViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewBtn.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsSaveView");
		saveViewBtn.setComponentName("SAVE VIEW");
		saveViewBtn.setParentComponentId(controlLayouts.getComponentId());
		saveViewBtn.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> saveViewActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig saveViewDataSelectionValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		saveViewDataSelectionValidationActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.COMPANY,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.BUSINESS_UNIT,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_DATA_SOURCE,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FROM_PERIOD,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_SELECTION_RELATIONSHIP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_SELECTION_LEVEL,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_DUAL_LIST_BOX,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.RELATIONSHIP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.LEVEL_LOWERCASE,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRODUCTDUAL_LIST_BOX_COMP));

		GtnUIFrameWorkActionConfig saveViewDataSelectionAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Search Criteria ");
		alertParams.add("No search criteria were found. Please enter search criteria and try again.");
		saveViewDataSelectionAlertActionConfig.setActionParameterList(alertParams);

		saveViewDataSelectionValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(saveViewDataSelectionAlertActionConfig)));
		saveViewActionConfigList.add(saveViewDataSelectionValidationActionConfig);

		GtnUIFrameWorkActionConfig saveViewAction = new GtnUIFrameWorkActionConfig();
		saveViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveViewAction.setActionParameterList(Arrays.asList(GtnUIFrameworkSaveViewAction.class.getName(),
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.COMPANY,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.BUSINESS_UNIT,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_DATA_SOURCE,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.FROM_PERIOD,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "STATUS",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerHierarchy",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_SELECTION_RELATIONSHIP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_SELECTION_LEVEL,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerRelationshipVersion",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionForecastEligibilityDate",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.CUSTOMER_DUAL_LIST_BOX,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "producthierarchy",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.RELATIONSHIP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.LEVEL_LOWERCASE,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productRelationshipVersion",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRODUCTDUAL_LIST_BOX_COMP,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "displaySelectionTabCustomView",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "displaySelectionTabVariable",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_COMPARISON_CONFIG,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.LANDINGSCREEN_VARIABLE_BREAKDOWN_FREQUENCY_CONFIG));
		saveViewActionConfigList.add(saveViewAction);
		saveViewBtn.setGtnUIFrameWorkActionConfigList(saveViewActionConfigList);
		componentList.add(saveViewBtn);

		GtnUIFrameworkComponentConfig deleteViewButton = new GtnUIFrameworkComponentConfig();
		deleteViewButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteViewButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsDeleteView");
		deleteViewButton.setComponentName("DELETE VIEW");
		deleteViewButton.setParentComponentId(controlLayouts.getComponentId());
		deleteViewButton.setAddToParent(true);
		deleteViewButton.setEnable(false);
		componentList.add(deleteViewButton);

		GtnUIFrameWorkActionConfig confirmDeleteAction = new GtnUIFrameWorkActionConfig();
		confirmDeleteAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmDeleteAction.addActionParameter("Confirmation");
		confirmDeleteAction.addActionParameter("Are you sure you want to delete the view?");
		List<GtnUIFrameWorkActionConfig> onSuccessDeleteActionConfigList = new ArrayList<>();
		confirmDeleteAction.addActionParameter(onSuccessDeleteActionConfigList);

		GtnUIFrameWorkActionConfig deleteValidationAction = new GtnUIFrameWorkActionConfig();
		deleteValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteValidationAction.addActionParameter(GtnFrameworkPublicViewDeleteValidation.class.getName());
		deleteValidationAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEWS);
		deleteValidationAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS);
		deleteValidationAction.addActionParameter(namespace);
		onSuccessDeleteActionConfigList.add(deleteValidationAction);

		GtnUIFrameWorkActionConfig deleteViewAction = new GtnUIFrameWorkActionConfig();
		deleteViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteViewAction.addActionParameter(GtnReportDataSelectionDeleteViewAction.class.getName());
		deleteViewAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEWS);
		deleteViewAction.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS);
		deleteViewAction.addActionParameter(namespace);
		onSuccessDeleteActionConfigList.add(deleteViewAction);
		deleteViewButton.addGtnUIFrameWorkActionConfig(confirmDeleteAction);
	}

	private GtnUIFrameWorkActionConfig loadForecastEligibleDate() {
		GtnUIFrameWorkActionConfig loadDateAction = new GtnUIFrameWorkActionConfig();
		loadDateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadDateAction.addActionParameter(GtnForecastEligibleDateLoadAction.class.getName());
		loadDateAction.addActionParameter("reportLandingScreen_customerSelectionForecastEligibilityDate");
		return loadDateAction;
	}

}
