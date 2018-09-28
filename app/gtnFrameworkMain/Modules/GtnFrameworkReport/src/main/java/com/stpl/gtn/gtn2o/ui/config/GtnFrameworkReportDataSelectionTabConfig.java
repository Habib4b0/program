package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportResetAndCloseAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionResultsLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionReGenerateAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionResetAction;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.hierarchy.config.GtnFrameworkReportCustHierarchyConfig;
import com.stpl.gtn.gtn2o.ui.hierarchy.config.GtnFrameworkReportProdHierarchyConfig;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnFrameworkReportTabChangeAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownFrequencyLoadAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;

public class GtnFrameworkReportDataSelectionTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	private GtnFrameworkReportLayoutsConfig layoutConfig = new GtnFrameworkReportLayoutsConfig();
	private static final String REPORT_LANDING_SCREEN = "reportLandingScreen";

	public void addDataSelectionTab(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionRootLayoutConfig = layoutConfig.getRootLayoutConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dataSelectionRootLayout",
				GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		reportDataSelectionRootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(reportDataSelectionRootLayoutConfig);

		configureMainLayouts(componentList, reportDataSelectionRootLayoutConfig.getComponentId(), namespace);

	}

	private void configureMainLayouts(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkLayoutConfig reportDataSelectionMainLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionMainLayoutConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dataSelectionTabMainLayout");
		reportDataSelectionMainLayoutConfig.setAddToParent(true);
		reportDataSelectionMainLayoutConfig.setParentComponentId(parentId);
		reportDataSelectionMainLayoutConfig.setSpacing(true);
		reportDataSelectionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionMainLayoutConfig.setGtnLayoutConfig(reportDataSelectionMainLayout);
		componentList.add(reportDataSelectionMainLayoutConfig);
		addComponentList(componentList, "dataSelectionTabMainLayout", namespace);
		addNavigationButtonLayout(componentList, reportDataSelectionMainLayoutConfig.getComponentId(), namespace);
	}

	private void addComponentList(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		addProjectionOptionPanel(componentList, parentId, namespace);
		addPrivateViewCompanyProjectionname(componentList, namespace);
		addPublicViewBusinessUnitDescription(componentList, namespace);
		addTimePeriod(componentList, namespace);
		addCustomerSelectionLayout(componentList, parentId, namespace);
		addProductSelectionLayout(componentList, parentId, namespace);

		GtnFrameworkReportCustHierarchyConfig customerSelection = new GtnFrameworkReportCustHierarchyConfig();
		componentList.addAll(
				customerSelection.getCustomerSelectionLayoutComponents(namespace, "reportDsCustomerHierarchyLookup"));

		GtnFrameworkReportProdHierarchyConfig productSelection = new GtnFrameworkReportProdHierarchyConfig();
		componentList.addAll(productSelection.getProductSelectionLayoutComponents(namespace, "dataSelection"));

		addReportingDataSelectionFields(componentList, namespace);
	}

	private void addProjectionOptionPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionProjectionOptionPanel = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionOptionPanel.setComponentName("Projection Options");
		reportDataSelectionProjectionOptionPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionOptions");
		reportDataSelectionProjectionOptionPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		reportDataSelectionProjectionOptionPanel
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + parentId);
		reportDataSelectionProjectionOptionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportDataSelectionProjectionOptionPanel.setAddToParent(true);
		componentList.add(reportDataSelectionProjectionOptionPanel);

		GtnUIFrameworkLayoutConfig reportDataSelectionProjectionOptionMainLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionProjectionOptionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionProjectionOptionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionOptionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionProjectionOptionMainLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_MAIN_LAYOUT);
		reportDataSelectionProjectionOptionMainLayoutConfig.addComponentStyle("v-report-overflow-auto");
		reportDataSelectionProjectionOptionMainLayoutConfig.setComponentHight("155px");
		reportDataSelectionProjectionOptionMainLayoutConfig
				.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		reportDataSelectionProjectionOptionMainLayoutConfig.addComponentStyle("stpl-margin-bottom-10");

		reportDataSelectionProjectionOptionMainLayoutConfig.setAddToParent(true);
		reportDataSelectionProjectionOptionMainLayoutConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionOptions");
		reportDataSelectionProjectionOptionMainLayoutConfig
				.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionProjectionOptionMainLayoutConfig.setSpacing(true);
		reportDataSelectionProjectionOptionMainLayoutConfig
				.setGtnLayoutConfig(reportDataSelectionProjectionOptionMainLayout);
		componentList.add(reportDataSelectionProjectionOptionMainLayoutConfig);

		GtnUIFrameworkLayoutConfig reportDataSelectionProjectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionProjectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionProjectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionProjectionOptionInnerLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_INNER_LAYOUT);
		reportDataSelectionProjectionOptionInnerLayoutConfig.setAddToParent(true);
		reportDataSelectionProjectionOptionInnerLayoutConfig.setSpacing(true);
		reportDataSelectionProjectionOptionInnerLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_MAIN_LAYOUT);
		reportDataSelectionProjectionOptionInnerLayoutConfig
				.setGtnLayoutConfig(reportDataSelectionProjectionOptionInnerLayout);
		componentList.add(reportDataSelectionProjectionOptionInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig reportDataSelectionModeSelectionlayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionModeSelectionlayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionModeLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionModeLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionModeLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DS_TAB_MODE_SELECTION_LAYOUT);
		reportDataSelectionModeLayoutConfig.setAddToParent(true);
		reportDataSelectionModeLayoutConfig.setGtnLayoutConfig(reportDataSelectionModeSelectionlayout);
		reportDataSelectionModeLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_INNER_LAYOUT);
		componentList.add(reportDataSelectionModeLayoutConfig);

		GtnUIFrameworkLayoutConfig modeSelectionCsslayoutConfig = new GtnUIFrameworkLayoutConfig();
		modeSelectionCsslayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig modeSelectionCsslayout = new GtnUIFrameworkComponentConfig();
		modeSelectionCsslayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		modeSelectionCsslayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_DS_CSS_LAYOUT);
		modeSelectionCsslayout.setAddToParent(true);
		modeSelectionCsslayout.setGtnLayoutConfig(modeSelectionCsslayoutConfig);
		modeSelectionCsslayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_INNER_LAYOUT);
		componentList.add(modeSelectionCsslayout);
	}

	private void addPrivateViewCompanyProjectionname(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkLayoutConfig privateViewLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig privateViewLayoutConfig = new GtnUIFrameworkComponentConfig();
		privateViewLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewLayout");
		privateViewLayoutConfig.setAddToParent(true);
		privateViewLayoutConfig.setSpacing(true);
		privateViewLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.DS_TAB_MODE_SELECTION_LAYOUT);
		privateViewLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		privateViewLayoutConfig.setGtnLayoutConfig(privateViewLayout);
		componentList.add(privateViewLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionPrivateView = new GtnUIFrameworkComponentConfig();
		reportDataSelectionPrivateView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		reportDataSelectionPrivateView
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabPrivateViews");
		reportDataSelectionPrivateView.setComponentName("Private Views:");
		reportDataSelectionPrivateView.setAddToParent(true);
		reportDataSelectionPrivateView
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewLayout");
		reportDataSelectionPrivateView.addTextComponentStyle("v-reportLandingScreen-PrivateView");

		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportDataSelectionPrivateViewPopupAction = new GtnUIFrameWorkActionConfig();
		reportDataSelectionPrivateViewPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		reportDataSelectionPrivateViewPopupAction.setActionParameterList(
				Arrays.asList(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PRIVATEVIEW, "Private View", "795",
						"875", GtnFrameworkReportResetAndCloseAction.class.getName(),
						Arrays.asList("dataSelectionPublicView" + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE),
						Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY)));
		list.add(reportDataSelectionPrivateViewPopupAction);

		reportDataSelectionPrivateView.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(reportDataSelectionPrivateView);

		GtnUIFrameworkLayoutConfig reportDataSelectionCompanyLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionCompanyLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionCompanyLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionCompanyLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionCompanyLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabCompanyLayout");
		reportDataSelectionCompanyLayoutConfig.setAddToParent(true);
		reportDataSelectionCompanyLayoutConfig.setSpacing(true);
		reportDataSelectionCompanyLayoutConfig.addComponentStyle("v-reportLandingScreen-companyLayout");
		reportDataSelectionCompanyLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DS_TAB_MODE_SELECTION_LAYOUT);
		reportDataSelectionCompanyLayoutConfig.setGtnLayoutConfig(reportDataSelectionCompanyLayout);
		componentList.add(reportDataSelectionCompanyLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionCompany = new GtnUIFrameworkComponentConfig();
		reportDataSelectionCompany.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionCompany.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "company");
		reportDataSelectionCompany.setComponentName("Company:");
		reportDataSelectionCompany.addComboComponentStyle("v-reportLandingScreen-company");
		reportDataSelectionCompany.setAddToParent(true);
		reportDataSelectionCompany
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabCompanyLayout");
		GtnUIFrameworkComboBoxConfig reportDataSelectionCompanyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		reportDataSelectionCompanyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		reportDataSelectionCompanyTypeConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		reportDataSelectionCompany.setGtnComboboxConfig(reportDataSelectionCompanyTypeConfig);
		componentList.add(reportDataSelectionCompany);

		GtnUIFrameworkLayoutConfig reportDataSelectionProjectionNameLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionProjectionNameLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig reportDataSelectionProjectionNameLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionNameLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionProjectionNameLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionNameLayout");
		reportDataSelectionProjectionNameLayoutConfig.setAddToParent(true);
		reportDataSelectionProjectionNameLayoutConfig.addComponentStyle("v-reportLandingScreen-dataSourceLayout");
		reportDataSelectionProjectionNameLayoutConfig.setSpacing(true);
		reportDataSelectionProjectionNameLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DS_TAB_MODE_SELECTION_LAYOUT);
		reportDataSelectionProjectionNameLayoutConfig.setGtnLayoutConfig(reportDataSelectionProjectionNameLayout);
		componentList.add(reportDataSelectionProjectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionProjectionName = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionName.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionProjectionName
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionName");
		reportDataSelectionProjectionName.setComponentName("Report Data Source:");
		reportDataSelectionProjectionName.addComboComponentStyle("v-reportLandingScreen-dataSource");
		reportDataSelectionProjectionName.setAddToParent(true);
		reportDataSelectionProjectionName.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionNameLayout");

		GtnUIFrameworkComboBoxConfig reportDSDataSourceLoadConfig = new GtnUIFrameworkComboBoxConfig();
		reportDSDataSourceLoadConfig.setItemValues(Arrays.asList(1, 2, 3));
		reportDSDataSourceLoadConfig
				.setItemCaptionValues(GtnFrameworkReportStringConstants.getReportDataSourceLoadCombobox());
		reportDataSelectionProjectionName.setGtnComboboxConfig(reportDSDataSourceLoadConfig);

		componentList.add(reportDataSelectionProjectionName);
	}

	private void addPublicViewBusinessUnitDescription(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkLayoutConfig publicViewLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewDsLayout");
		publicViewLayoutConfig.setAddToParent(true);
		publicViewLayoutConfig.setSpacing(true);
		publicViewLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_DS_CSS_LAYOUT);
		publicViewLayoutConfig.setGtnLayoutConfig(publicViewLayout);
		componentList.add(publicViewLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionPublicView = new GtnUIFrameworkComponentConfig();
		reportDataSelectionPublicView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		reportDataSelectionPublicView
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabPublicViews");
		reportDataSelectionPublicView.setComponentName("Public Views:");
		reportDataSelectionPublicView.setAddToParent(true);
		reportDataSelectionPublicView.addTextComponentStyle("v-publicViewTextField");
		reportDataSelectionPublicView.setEnable(false);
		reportDataSelectionPublicView
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewDsLayout");

		List<GtnUIFrameWorkActionConfig> actionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportDataSelectionPublicViewPopupAction = new GtnUIFrameWorkActionConfig();
		reportDataSelectionPublicViewPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		reportDataSelectionPublicViewPopupAction
				.setActionParameterList(Arrays.asList(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PUBLICVIEW,
						"Public View", "795", "875", GtnFrameworkReportResetAndCloseAction.class.getName(),
						Arrays.asList("dataSelectionPublicView" + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE),
						Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY)));
		actionList.add(reportDataSelectionPublicViewPopupAction);
		reportDataSelectionPublicView.setGtnUIFrameWorkActionConfigList(actionList);

		componentList.add(reportDataSelectionPublicView);

		GtnUIFrameworkLayoutConfig reportDataSelectionBusinessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		businessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		businessUnitLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabBusinessUnitLayout");
		businessUnitLayoutConfig.setAddToParent(true);
		businessUnitLayoutConfig.addComponentStyle("v-BU-layout");
		businessUnitLayoutConfig.setSpacing(true);
		businessUnitLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.MODE_SELECTION_DS_CSS_LAYOUT);
		businessUnitLayoutConfig.setGtnLayoutConfig(reportDataSelectionBusinessUnitLayout);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionBusinessUnit = new GtnUIFrameworkComponentConfig();
		reportDataSelectionBusinessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionBusinessUnit
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "businessUnit");
		reportDataSelectionBusinessUnit.setComponentName("Business Unit:");
		reportDataSelectionBusinessUnit.addComboComponentStyle("v-bu");
		reportDataSelectionBusinessUnit.setAddToParent(true);
		reportDataSelectionBusinessUnit.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabBusinessUnitLayout");
		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType(GtnFrameworkReportStringConstants.BUSINESS_UNIT_GLCOMP);
		reportDataSelectionBusinessUnit.setGtnComboboxConfig(businessUnitConfig);

		componentList.add(reportDataSelectionBusinessUnit);

	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionPanel = new GtnUIFrameworkComponentConfig();
		reportDataSelectionPanel.setComponentName("Time Period");
		reportDataSelectionPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabTimePeriod");
		reportDataSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportDataSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionPanel.setAddToParent(true);
		reportDataSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		reportDataSelectionPanel.addComponentStyle("stpl-margin-right-10");
		reportDataSelectionPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_MAIN_LAYOUT);
		componentList.add(reportDataSelectionPanel);

		GtnUIFrameworkLayoutConfig reportDataSelectionTimePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionTimePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionFromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionFromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionFromAndToperiodLayoutConfig.setComponentHight("50");
		reportDataSelectionFromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		reportDataSelectionFromAndToperiodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.DS_TAB_TIME_PERIOD_INNER_LAYOUT);
		reportDataSelectionFromAndToperiodLayoutConfig.setSpacing(true);
		reportDataSelectionFromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionFromAndToperiodLayoutConfig.setAddToParent(true);
		reportDataSelectionFromAndToperiodLayoutConfig.setGtnLayoutConfig(reportDataSelectionTimePeriodInnerLayout);
		reportDataSelectionFromAndToperiodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabTimePeriod");
		reportDataSelectionFromAndToperiodLayoutConfig
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(reportDataSelectionFromAndToperiodLayoutConfig);

		GtnUIFrameworkLayoutConfig reportDataSelectionFromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionFromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionFromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionFromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionFromPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabFromPeriodLayout");
		reportDataSelectionFromPeriodLayoutConfig.setSpacing(true);
		reportDataSelectionFromPeriodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionFromPeriodLayoutConfig.setAddToParent(true);
		reportDataSelectionFromPeriodLayoutConfig.addComponentStyle("v-reportLandingScreen-fromPeriodLayout");
		reportDataSelectionFromPeriodLayoutConfig.setGtnLayoutConfig(reportDataSelectionFromPeriodLayout);
		reportDataSelectionFromPeriodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.DS_TAB_TIME_PERIOD_INNER_LAYOUT);
		componentList.add(reportDataSelectionFromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionFromPeriod = new GtnUIFrameworkComponentConfig();
		reportDataSelectionFromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionFromPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD);
		reportDataSelectionFromPeriod.setComponentName("From: ");
		reportDataSelectionFromPeriod.setAddToParent(true);

		reportDataSelectionFromPeriod.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabFromPeriodLayout");
		GtnUIFrameworkComboBoxConfig reportDataSelectionFromPeriodConfig = new GtnUIFrameworkComboBoxConfig();

		reportDataSelectionFromPeriodConfig.setModuleName("report");
		reportDataSelectionFromPeriodConfig.setLoadingUrl("/gtnReport/gtnWsReportComboboxLoad");
		reportDataSelectionFromPeriodConfig.setComboBoxType("timePeriodForReportFromDate");
		reportDataSelectionFromPeriodConfig.setHasDefaultValue(true);
		reportDataSelectionFromPeriodConfig.setDefaultDesc("next");
		reportDataSelectionFromPeriod.setGtnComboboxConfig(reportDataSelectionFromPeriodConfig);

		componentList.add(reportDataSelectionFromPeriod);

		GtnUIFrameworkLayoutConfig reportDataSelectionToPeriodLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionToPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionToPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionToPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionToPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabToPeriodLayout");
		reportDataSelectionToPeriodLayoutConfig.setSpacing(true);
		reportDataSelectionToPeriodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionToPeriodLayoutConfig.setAddToParent(true);
		reportDataSelectionToPeriodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);

		reportDataSelectionToPeriodLayoutConfig.setGtnLayoutConfig(reportDataSelectionToPeriodLayout);
		reportDataSelectionToPeriodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.DS_TAB_TIME_PERIOD_INNER_LAYOUT);
		componentList.add(reportDataSelectionToPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionToPeriod = new GtnUIFrameworkComponentConfig();
		reportDataSelectionToPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionToPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.STATUS);
		reportDataSelectionToPeriod.setComponentName("To :");

		reportDataSelectionToPeriod.setAddToParent(true);
		reportDataSelectionToPeriod.setEnable(false);

		reportDataSelectionToPeriod
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabToPeriodLayout");

		GtnUIFrameworkComboBoxConfig reportDataSelectionToPeriodTypeConfig = new GtnUIFrameworkComboBoxConfig();

		reportDataSelectionToPeriodTypeConfig.setModuleName("report");
		reportDataSelectionToPeriodTypeConfig.setLoadingUrl("/gtnReport/gtnWsReportComboboxLoad");
		reportDataSelectionToPeriodTypeConfig.setComboBoxType("timePeriodForReportToDate");
		reportDataSelectionToPeriodTypeConfig.setHasDefaultValue(true);
		reportDataSelectionToPeriodTypeConfig.setDefaultDesc("next");

		reportDataSelectionToPeriod.setGtnComboboxConfig(reportDataSelectionToPeriodTypeConfig);
		componentList.add(reportDataSelectionToPeriod);

	}

	private void addProductSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionProductSelectionPanel = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProductSelectionPanel.setComponentName("Product Selection");

		reportDataSelectionProductSelectionPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productSelectionPanel");
		reportDataSelectionProductSelectionPanel
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + parentId);
		reportDataSelectionProductSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportDataSelectionProductSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		reportDataSelectionProductSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionProductSelectionPanel.setSpacing(true);
		reportDataSelectionProductSelectionPanel.setAddToParent(true);
		componentList.add(reportDataSelectionProductSelectionPanel);

	}

	private void addCustomerSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionCustomerSelectionPanel = new GtnUIFrameworkComponentConfig();
		reportDataSelectionCustomerSelectionPanel.setComponentName("Customer Selection");

		reportDataSelectionCustomerSelectionPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionPanel");
		reportDataSelectionCustomerSelectionPanel
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + parentId);
		reportDataSelectionCustomerSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportDataSelectionCustomerSelectionPanel.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		reportDataSelectionCustomerSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionCustomerSelectionPanel.setSpacing(true);
		reportDataSelectionCustomerSelectionPanel.setAddToParent(true);
		componentList.add(reportDataSelectionCustomerSelectionPanel);

	}

	private void addReportingDataSelectionFields(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig reportHorizontalCssLayout = new GtnUIFrameworkLayoutConfig();
		reportHorizontalCssLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportHorizontalCssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportHorizontalCssLayout", true,
				"dataSelectionTab_prodSelectionMainlayout", GtnUIFrameworkComponentType.LAYOUT);

		reportHorizontalCssGtnLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportHorizontalCssGtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		reportHorizontalCssGtnLayout.setGtnLayoutConfig(reportHorizontalCssLayout);

		GtnUIFrameworkLayoutConfig reportCssLayout = new GtnUIFrameworkLayoutConfig();
		reportCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportDSCssGtnLayout = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT,
				true, namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportHorizontalCssLayout",
				GtnUIFrameworkComponentType.LAYOUT);

		reportDSCssGtnLayout.setGtnLayoutConfig(reportCssLayout);
		componentList.add(reportHorizontalCssGtnLayout);
		componentList.add(reportDSCssGtnLayout);

		addReportDataSelectionVariableComponent(componentList, namespace);
		addReportDataSelectionComparisonComponent(componentList, namespace);
		addReportDataSelectionVariableBreakdownComponent(componentList, namespace);
		addReportDataSelectionFrequencyComponent(componentList, namespace);
	}

	private void addReportDataSelectionFrequencyComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsFrequencyLayoutConfig = layoutConfig.getHorizontalLayoutConfig(
				"variableBreakdownFrequencyLayoutConfig", namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		dsFrequencyLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_25_PX);

		GtnUIFrameworkComponentConfig dsFrequencyConfig = new GtnUIFrameworkComponentConfig();
		dsFrequencyConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		dsFrequencyConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "landingScreenVariableBreakdownFrequencyConfig");
		dsFrequencyConfig.setComponentName("Frequency: ");
		dsFrequencyConfig.setAddToParent(true);
		dsFrequencyConfig.setParentComponentId(dsFrequencyLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig dsFrequencyLoadConfig = new GtnUIFrameworkComboBoxConfig();
		dsFrequencyLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.REPORT_CONFIG_FREQUENCY);
		dsFrequencyLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		dsFrequencyConfig.setGtnComboboxConfig(dsFrequencyLoadConfig);

		componentList.add(dsFrequencyLayoutConfig);
		componentList.add(dsFrequencyConfig);
	}

	private void addReportDataSelectionComparisonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig hierarchyLayout = configProvider.getHorizontalLayoutConfig(
				"comparisonHierarchyLayout", true, namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		hierarchyLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_25_PX);
		componentList.add(hierarchyLayout);

		GtnUIFrameworkComponentConfig dsComparisonLookupConfig = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonLookup", true,
				"comparisonHierarchyLayout", GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		dsComparisonLookupConfig.setAuthorizationIncluded(true);
		dsComparisonLookupConfig.setComponentName("Comparison: ");

		GtnUIFrameworkTextBoxConfig dsComparisonConfig = configProvider.getTextBoxConfig(true, true, true);
		dsComparisonLookupConfig.setGtnTextBoxConfig(dsComparisonConfig);

		componentList.add(dsComparisonLookupConfig);

		List<GtnUIFrameWorkActionConfig> dsComparisonPopupActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDSComparisonPopupConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_ACTION);
		reportingDSComparisonPopupConfig.addActionParameter("dataSelectionComparisonLookupView");
		reportingDSComparisonPopupConfig.addActionParameter("Comparison Lookup");
		reportingDSComparisonPopupConfig.addActionParameter(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		reportingDSComparisonPopupConfig.addActionParameter(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		dsComparisonPopupActionList.add(reportingDSComparisonPopupConfig);

		GtnUIFrameWorkActionConfig selectedGridLoadAction = new GtnUIFrameWorkActionConfig();
		selectedGridLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectedGridLoadAction.addActionParameter(GtnReportComparisonProjectionResultsLoadAction.class.getName());
		selectedGridLoadAction.addActionParameter("dataSelectionComparisonResultsGrid");
		dsComparisonPopupActionList.add(selectedGridLoadAction);
		dsComparisonLookupConfig.setGtnUIFrameWorkActionConfigList(dsComparisonPopupActionList);

	}

	private void addReportDataSelectionVariableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsVariableLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_LAYOUT, true,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		dsVariableLayout.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_25_PX);
		dsVariableLayout.setMargin(true);
		dsVariableLayout.setSpacing(true);
		componentList.add(dsVariableLayout);

		GtnUIFrameworkComponentConfig dsComparisonVariableConfig = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE,
				true, GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_VARIABLE_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOXMULTISELECT);
		dsComparisonVariableConfig.setComponentName("Variables: ");
		dsComparisonVariableConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkCheckedComboBoxConfig dsComparisonVariableLoadConfig = new GtnUIFrameworkCheckedComboBoxConfig();
		GtnWsReportVariablesType[] variableType = Arrays.copyOfRange(GtnWsReportVariablesType.values(), 0,
				GtnWsReportVariablesType.values().length - 1);
		dsComparisonVariableLoadConfig.setItemValueList(
				Arrays.stream(variableType).map(GtnWsReportVariablesType::toString).collect(Collectors.toList()));
		dsComparisonVariableLoadConfig.setDefaultValue(GtnFrameworkCommonConstants.SELECT_ONE);
		dsComparisonVariableConfig.setGtnCheckedComboboxConfig(dsComparisonVariableLoadConfig);
		componentList.add(dsComparisonVariableConfig);
	}

	private void addReportDataSelectionVariableBreakdownComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsVariableBreakdownGtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN_LAYOUT, true,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.DISPLAY_SELECTION_TAB_CSS_LAYOUT);
		dsVariableBreakdownGtnLayout.addComponentStyle("stpl-padding-top-12");
		componentList.add(dsVariableBreakdownGtnLayout);

		GtnUIFrameworkComponentConfig dsVariableBreakdownConfig = configProvider.getUIFrameworkComponentConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN,
				true, GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_VARIABLE_BREAKDOWN_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		dsVariableBreakdownConfig.setComponentName("Variable Breakdown: ");
		dsVariableBreakdownConfig.addComponentStyle(GtnFrameworkReportStringConstants.LINK);
		dsVariableBreakdownConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> dsVariableBreakdownActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dsVariableBreakdownPopupActionConfig = new GtnUIFrameWorkActionConfig();
		dsVariableBreakdownPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("variableBreakdown");
		params.add("Variable Breakdown Popup");
		params.add(GtnFrameworkReportStringConstants.HUNDRED_PERCENT);
		params.add(null);
		params.add(null);
		params.add("reportingLandingScreen");

		dsVariableBreakdownPopupActionConfig.setActionParameterList(params);
		dsVariableBreakdownActionConfigList.add(dsVariableBreakdownPopupActionConfig);

		GtnUIFrameWorkActionConfig reportDSVariableBreakDownGridLoad = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportDSVariableBreakDownGridLoad
				.addActionParameter(GtnReportingVariableBreakdownFrequencyLoadAction.class.getName());
		reportDSVariableBreakDownGridLoad
				.addActionParameter("reportLandingScreen_landingScreenVariableBreakdownFrequencyConfig");
		reportDSVariableBreakDownGridLoad.addActionParameter("reportOptionsTab_variableBreakdownFrequencyConfig");
		reportDSVariableBreakDownGridLoad.addActionParameter("reportingLandingScreen");
		dsVariableBreakdownActionConfigList.add(reportDSVariableBreakDownGridLoad);

		dsVariableBreakdownConfig.setGtnUIFrameWorkActionConfigList(dsVariableBreakdownActionConfigList);

		componentList.add(dsVariableBreakdownConfig);
	}

	private void addNavigationButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionNavigationButtonConfig = layoutConfig
				.getHorizontalLayoutConfig("navigationButtonLayout", parentId);
		reportDataSelectionNavigationButtonConfig.addComponentStyle("stpl-margin-left-10");
		reportDataSelectionNavigationButtonConfig.addComponentStyle("stpl-margin-bottom-13");
		componentList.add(reportDataSelectionNavigationButtonConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionNextButtonConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionNextButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDataSelectionNextButtonConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "nextButtonConfig");
		reportDataSelectionNextButtonConfig.setComponentName("NEXT");
		reportDataSelectionNextButtonConfig.setAddToParent(true);
		reportDataSelectionNextButtonConfig
				.setParentComponentId(reportDataSelectionNavigationButtonConfig.getComponentId());

		List<GtnUIFrameWorkActionConfig> nextActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig nextGenerateAction = new GtnUIFrameWorkActionConfig();
		nextGenerateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		nextGenerateAction.addActionParameter(GtnReportDataSelectionReGenerateAction.class.getName());
		nextActionConfigList.add(nextGenerateAction);

		GtnUIFrameWorkActionConfig nextButtonAction = new GtnUIFrameWorkActionConfig();
		nextButtonAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		nextButtonAction.addActionParameter(GtnFrameworkReportTabChangeAction.class.getName());
		nextButtonAction.addActionParameter("tabSheetMain");
		nextButtonAction.addActionParameter("1");
		nextActionConfigList.add(nextButtonAction);
		reportDataSelectionNextButtonConfig.setGtnUIFrameWorkActionConfigList(nextActionConfigList);

		GtnUIFrameworkComponentConfig reportDataSelectionCloseButtonConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionCloseButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDataSelectionCloseButtonConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "closeButtonConfig");
		reportDataSelectionCloseButtonConfig.setComponentName("CLOSE");
		reportDataSelectionCloseButtonConfig.setAddToParent(true);
		reportDataSelectionCloseButtonConfig
				.setParentComponentId(reportDataSelectionNavigationButtonConfig.getComponentId());

		List<GtnUIFrameWorkActionConfig> actionConfigurationList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertActionParamsList = new ArrayList<>();
		alertActionParamsList.add(GtnFrameworkReportStringConstants.CONFIRMATION);
		alertActionParamsList.add("Are you sure you want to close this Report?");
		List<GtnUIFrameWorkActionConfig> onSucessActConfigList = new ArrayList<>();
		alertActionParamsList.add(onSucessActConfigList);
		GtnUIFrameWorkActionConfig closeActConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeActConfig.addActionParameter(GtnFrameworkReportStringConstants.REPORT_GENERATE_LOOKUP_VIEW);
		onSucessActConfigList.add(closeActConfig);
		confirmActionConfig.setActionParameterList(alertActionParamsList);

		actionConfigurationList.add(confirmActionConfig);
		GtnUIFrameWorkActionConfig resetActionLandingScreenAction = new GtnUIFrameWorkActionConfig();
		resetActionLandingScreenAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetActionLandingScreenAction.setActionParameterList(getResetParameters());
		onSucessActConfigList.add(resetActionLandingScreenAction);
		reportDataSelectionCloseButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigurationList);

		componentList.add(reportDataSelectionNextButtonConfig);
		componentList.add(reportDataSelectionCloseButtonConfig);
	}

	public static List<Object> getResetParameters() {
		return Arrays.asList(GtnReportDataSelectionResetAction.class.getName(),
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "company",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "businessUnit",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_DATA_SOURCE,
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriod",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "STATUS",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "customerHierarchy",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionRelationship",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "customerSelectionLevel",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "customerRelationshipVersion",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE
						+ "customerSelectionForecastEligibilityDate",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "customerDualListBox",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "producthierarchy",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "relationship",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "level",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "productRelationshipVersion",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "productdualListBoxComp",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "displaySelectionTabCustomView",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE + "displaySelectionTabVariable",
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORTING_DASHBOARD_COMPARISON_CONFIG,
				REPORT_LANDING_SCREEN + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.LANDINGSCREEN_VARIABLE_BREAKDOWN_FREQUENCY_CONFIG,
				"reportLandingScreen_reportingDashboardComparisonConfig");
	}

}
