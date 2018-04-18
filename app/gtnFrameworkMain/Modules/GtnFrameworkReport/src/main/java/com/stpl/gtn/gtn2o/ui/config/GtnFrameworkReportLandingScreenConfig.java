package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.hierarchy.config.GtnFrameworkReportCustHierarchyConfig;
import com.stpl.gtn.gtn2o.ui.hierarchy.config.GtnFrameworkReportProdHierarchyConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkReportLandingScreenConfig {

	public GtnUIFrameworkViewConfig getLandingScreenView(String namespace) {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "View");
		view.setViewId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "V001");
		view.setDefaultView(true);
		addComponentList(view, namespace);
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
		componentList.addAll(customerSelection.getCustomerSelectionLayoutComponents(namespace));

		GtnFrameworkReportProdHierarchyConfig productSelection = new GtnFrameworkReportProdHierarchyConfig();
		componentList.addAll(productSelection.getProductSelectionLayoutComponents(namespace));

		addControlButtonLayout(componentList, namespace);
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
		reportOptionsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportOptionsPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DATA_SELECTION_MAIN_LAYOUT);
		reportOptionsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportOptionsPanel.setAddToParent(true);
		reportOptionsPanel.setSpacing(true);
		componentList.add(reportOptionsPanel);

		GtnUIFrameworkLayoutConfig reportOptionsMainLayout = new GtnUIFrameworkLayoutConfig();
		reportOptionsMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		reportOptionsMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig reportOptionsMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportOptionsMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportOptionsMainLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_MAIN_LAYOUT);
		reportOptionsMainLayoutConfig.setAddToParent(true);
		reportOptionsMainLayoutConfig.setSpacing(true);
		reportOptionsMainLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportOptions");
		reportOptionsMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportOptionsMainLayoutConfig.setGtnLayoutConfig(reportOptionsMainLayout);
		componentList.add(reportOptionsMainLayoutConfig);

		GtnUIFrameworkLayoutConfig reportOptionsInnerLayout = new GtnUIFrameworkLayoutConfig();
		reportOptionsInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportOptionsInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportOptionsInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportOptionsInnerLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_INNER_LAYOUT);
		reportOptionsInnerLayoutConfig.setAddToParent(true);
		reportOptionsInnerLayoutConfig.setSpacing(true);
		reportOptionsInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		reportOptionsInnerLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_MAIN_LAYOUT);
		reportOptionsInnerLayoutConfig.setGtnLayoutConfig(reportOptionsInnerLayout);
		componentList.add(reportOptionsInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig modeSelectionlayout = new GtnUIFrameworkLayoutConfig();
		modeSelectionlayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig modeLayoutConfig = new GtnUIFrameworkComponentConfig();
		modeLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "modeSelectionLayout");
		modeLayoutConfig.setAddToParent(true);
		modeLayoutConfig.setGtnLayoutConfig(modeSelectionlayout);
		modeLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_INNER_LAYOUT);
		modeLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(modeLayoutConfig);

		GtnUIFrameworkLayoutConfig fromToMainLayout = new GtnUIFrameworkLayoutConfig();
		fromToMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig fromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		fromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		fromToLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromToMainLayout");
		fromToLayoutConfig.setAddToParent(true);
		fromToLayoutConfig.setSpacing(true);
		fromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		fromToLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_MAIN_LAYOUT);
		fromToLayoutConfig.setGtnLayoutConfig(fromToMainLayout);
		componentList.add(fromToLayoutConfig);

		GtnUIFrameworkLayoutConfig privateViewCompanyReportNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		privateViewCompanyReportNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewCompanyReportNameLayout = new GtnUIFrameworkComponentConfig();
		privateViewCompanyReportNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewCompanyReportNameLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_SELECTIONLAYOUT1);
		privateViewCompanyReportNameLayout.setAddToParent(true);
		privateViewCompanyReportNameLayout.setGtnLayoutConfig(privateViewCompanyReportNameLayoutConfig);
		privateViewCompanyReportNameLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_INNER_LAYOUT);
		privateViewCompanyReportNameLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(privateViewCompanyReportNameLayout);

	}

	private void addPrivateViewCompanyName(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig privateView = new GtnUIFrameworkComponentConfig();
		privateView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		privateView.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEWS);
		privateView.setComponentName("Private Views");
		privateView.setAddToParent(true);
		privateView
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_SELECTIONLAYOUT1);
		privateView.setComponentWsFieldId(GtnFrameworkCommonConstants.PRIVATE_VIEWS);
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		Object privateViewLookup = "Private View";
		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.setActionParameterList(Arrays.asList("privateViewSearchLookupView", privateViewLookup, "795", "875"));
		list.add(conf);

		privateView.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(privateView);

		GtnUIFrameworkLayoutConfig companyLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig companyLayoutConfig = new GtnUIFrameworkComponentConfig();
		companyLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		companyLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "companyLayout");
		companyLayoutConfig.setAddToParent(true);
		companyLayoutConfig.setSpacing(true);
		companyLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_SELECTIONLAYOUT1);
		companyLayoutConfig.setGtnLayoutConfig(companyLayout);
		componentList.add(companyLayoutConfig);

		GtnUIFrameworkComponentConfig company = new GtnUIFrameworkComponentConfig();
		company.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		company.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.COMPANY);
		company.setComponentName("Company");
		company.setAddToParent(true);
		company.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "companyLayout");

		GtnUIFrameworkComboBoxConfig companyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		companyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyTypeConfig.setComboBoxType(GtnFrameworkReportStringConstants.BUSINESS_UNIT_GLCOMP);
		company.setGtnComboboxConfig(companyTypeConfig);
		
		componentList.add(company);

		GtnUIFrameworkLayoutConfig reportDataSourceLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportDataSourceLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSourceLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSourceLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportDataSourceLayout");
		reportDataSourceLayoutConfig.setAddToParent(true);
		reportDataSourceLayoutConfig.setSpacing(true);
		reportDataSourceLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_SELECTIONLAYOUT1);
		reportDataSourceLayoutConfig.setGtnLayoutConfig(reportDataSourceLayout);
		componentList.add(reportDataSourceLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSource = new GtnUIFrameworkComponentConfig();
		reportDataSource.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSource.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_DATA_SOURCE);
		reportDataSource.setComponentName("Report Data Source");
		reportDataSource.setAddToParent(true);
		reportDataSource.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportDataSourceLayout");

		GtnUIFrameworkComboBoxConfig reportDataSourceLoadConfig = new GtnUIFrameworkComboBoxConfig();
		reportDataSourceLoadConfig.setItemValues(GtnFrameworkReportStringConstants.getReportDataSourceLoadCombobox());
		reportDataSource.setGtnComboboxConfig(reportDataSourceLoadConfig);

		componentList.add(reportDataSource);
	}

	private void addPublicViewBusinessUnit(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig publicView = new GtnUIFrameworkComponentConfig();
		publicView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		publicView.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS);
		publicView.setComponentName("Public Views");
		publicView.setAddToParent(true);
		publicView.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_SELECTIONLAYOUT1);

		List<GtnUIFrameWorkActionConfig> publicViewActionConfigList = new ArrayList<>();
		Object publicViewLookup = "Public View";
		GtnUIFrameWorkActionConfig publicViewActionConfig = new GtnUIFrameWorkActionConfig();
		publicViewActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		publicViewActionConfig.setActionParameterList(Arrays.asList("publicViewSearchLookupView", publicViewLookup, "795", "875"));
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
		businessUnitLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_SELECTIONLAYOUT1);
		businessUnitLayoutConfig.setGtnLayoutConfig(businessUnitLayout);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig businessUnit = new GtnUIFrameworkComponentConfig();
		businessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		businessUnit.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.BUSINESS_UNIT);
		businessUnit.setComponentName("Business Unit");
		businessUnit.setAddToParent(true);
		businessUnit
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "businessUnitLayout");
		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType(GtnFrameworkReportStringConstants.BUSINESS_UNIT_GLCOMP);
		businessUnit.setGtnComboboxConfig(businessUnitConfig);

		componentList.add(businessUnit);
	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Time Period");
		panel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(true);
		panel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromToMainLayout");
		panel.addComponentStyle(GtnFrameworkCssConstants.PANEL_TIME_PERIOD_RTN_FOR);
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
		fromPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		componentList.add(fromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		fromPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD);
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(true);

		fromPeriod.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		GtnUIFrameworkComboBoxConfig fromPeriodConfig = new GtnUIFrameworkComboBoxConfig();
		fromPeriodConfig.setModuleName("report");
		fromPeriodConfig.setLoadingUrl("/gtnWsReportComboboxLoad");
		fromPeriodConfig.setComboBoxType("timePeriodForReportFromDate");
		fromPeriod.setGtnComboboxConfig(fromPeriodConfig);

		GtnUIFrameworkValidationConfig valConfigForFromPeriod = new GtnUIFrameworkValidationConfig();
		valConfigForFromPeriod.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		fromPeriod.setGtnUIFrameworkValidationConfig(valConfigForFromPeriod);
		componentList.add(fromPeriod);

		GtnUIFrameworkLayoutConfig toPeriodLayout = new GtnUIFrameworkLayoutConfig();
		toPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig toPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		toPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		toPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");
		toPeriodLayoutConfig.setAddToParent(true);
		toPeriodLayoutConfig.setGtnLayoutConfig(toPeriodLayout);
		toPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		componentList.add(toPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig toPeriod = new GtnUIFrameworkComponentConfig();
		toPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		toPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD);
		toPeriod.setComponentName("To ");
		toPeriod.setAddToParent(true);
		toPeriod.setEnable(true);

		toPeriod.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");

		GtnUIFrameworkComboBoxConfig toPeriodTypeConfig = new GtnUIFrameworkComboBoxConfig();
		toPeriodTypeConfig.setModuleName("report");
		toPeriodTypeConfig.setLoadingUrl("/gtnWsReportComboboxLoad");
		toPeriodTypeConfig.setComboBoxType("timePeriodForReportToDate");


		toPeriod.setGtnComboboxConfig(toPeriodTypeConfig);
		GtnUIFrameworkValidationConfig valConfigForToPeriod = new GtnUIFrameworkValidationConfig();
		valConfigForToPeriod.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		toPeriod.setGtnUIFrameworkValidationConfig(valConfigForToPeriod);
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

		GtnUIFrameWorkActionConfig gtnUIFrameWorkGeneratePopupAction = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkGeneratePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		gtnUIFrameWorkGeneratePopupAction
				.addActionParameter(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP_VIEW);
		gtnUIFrameWorkGeneratePopupAction.addActionParameter("Report Generate Lookup View");
		gtnUIFrameWorkGeneratePopupAction.addActionParameter(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnUIFrameWorkGeneratePopupAction.addActionParameter(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		generateBtn.addGtnUIFrameWorkActionConfig(gtnUIFrameWorkGeneratePopupAction);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsReset");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(controlLayouts.getComponentId());
		resetButton.setAddToParent(true);

		componentList.add(resetButton);

		GtnUIFrameworkComponentConfig saveViewBtn = new GtnUIFrameworkComponentConfig();
		saveViewBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveViewBtn.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsSaveView");
		saveViewBtn.setComponentName("SAVE VIEW");
		saveViewBtn.setParentComponentId(controlLayouts.getComponentId());
		saveViewBtn.setAddToParent(true);
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();

		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		conf.addActionParameter("dsSaveViewLookUp");
		conf.addActionParameter("Save view");
		list.add(conf);
		saveViewBtn.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(saveViewBtn);

		GtnUIFrameworkComponentConfig deleteViewButton = new GtnUIFrameworkComponentConfig();
		deleteViewButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		deleteViewButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsDeleteView");
		deleteViewButton.setComponentName("DELETE VIEW");
		deleteViewButton.setParentComponentId(controlLayouts.getComponentId());
		deleteViewButton.setAddToParent(true);
		deleteViewButton.setEnable(false);
		componentList.add(deleteViewButton);
	}
}
