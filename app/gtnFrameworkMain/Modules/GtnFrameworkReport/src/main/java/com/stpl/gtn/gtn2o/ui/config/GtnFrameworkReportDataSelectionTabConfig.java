package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.hierarchy.config.GtnFrameworkReportCustHierarchyConfig;
import com.stpl.gtn.gtn2o.ui.hierarchy.config.GtnFrameworkReportProdHierarchyConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkReportDataSelectionTabConfig {

	private GtnFrameworkReportLayoutsConfig layoutConfig = new GtnFrameworkReportLayoutsConfig();

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
		componentList.addAll(customerSelection.getCustomerSelectionLayoutComponents(namespace));

		GtnFrameworkReportProdHierarchyConfig productSelection = new GtnFrameworkReportProdHierarchyConfig();
		componentList.addAll(productSelection.getProductSelectionLayoutComponents(namespace));

	}

	private void addProjectionOptionPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionProjectionOptionPanel = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionOptionPanel.setComponentName("Projection Options");
		reportDataSelectionProjectionOptionPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionOptions");
		reportDataSelectionProjectionOptionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionProjectionOptionPanel
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + parentId);
		reportDataSelectionProjectionOptionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportDataSelectionProjectionOptionPanel.setAddToParent(true);
		componentList.add(reportDataSelectionProjectionOptionPanel);

		GtnUIFrameworkLayoutConfig reportDataSelectionProjectionOptionMainLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionProjectionOptionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		reportDataSelectionProjectionOptionMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig reportDataSelectionProjectionOptionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionOptionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionProjectionOptionMainLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_MAIN_LAYOUT);
		reportDataSelectionProjectionOptionMainLayoutConfig.setAddToParent(true);
		reportDataSelectionProjectionOptionMainLayoutConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionOptions");
		reportDataSelectionProjectionOptionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionProjectionOptionMainLayoutConfig.setSpacing(true);
		reportDataSelectionProjectionOptionMainLayoutConfig.setGtnLayoutConfig(reportDataSelectionProjectionOptionMainLayout);
		componentList.add(reportDataSelectionProjectionOptionMainLayoutConfig);

		GtnUIFrameworkLayoutConfig reportDataSelectionProjectionOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionProjectionOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionProjectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionProjectionOptionInnerLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_INNER_LAYOUT);
		reportDataSelectionProjectionOptionInnerLayoutConfig.setAddToParent(true);
		reportDataSelectionProjectionOptionInnerLayoutConfig.setSpacing(true);
		reportDataSelectionProjectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		reportDataSelectionProjectionOptionInnerLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_MAIN_LAYOUT);
		reportDataSelectionProjectionOptionInnerLayoutConfig.setGtnLayoutConfig(reportDataSelectionProjectionOptionInnerLayout);
		componentList.add(reportDataSelectionProjectionOptionInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig reportDataSelectionModeSelectionlayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionModeSelectionlayout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionModeLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionModeLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionModeLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabModeSelectionLayout");
		reportDataSelectionModeLayoutConfig.setAddToParent(true);
		reportDataSelectionModeLayoutConfig.setGtnLayoutConfig(reportDataSelectionModeSelectionlayout);
		reportDataSelectionModeLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_INNER_LAYOUT);
		componentList.add(reportDataSelectionModeLayoutConfig);

		GtnUIFrameworkLayoutConfig reportDataSelectionFromToMainLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionFromToMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionFromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionFromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionFromToLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabFromToMainLayout");
		reportDataSelectionFromToLayoutConfig.setAddToParent(true);
		reportDataSelectionFromToLayoutConfig.setSpacing(true);
		reportDataSelectionFromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		reportDataSelectionFromToLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_MAIN_LAYOUT);
		reportDataSelectionFromToLayoutConfig.setGtnLayoutConfig(reportDataSelectionFromToMainLayout);
		componentList.add(reportDataSelectionFromToLayoutConfig);

		GtnUIFrameworkLayoutConfig reportDataSelectionPrivateViewCompanyProjectionNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionPrivateViewCompanyProjectionNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewCompanyProjectionNameLayout = new GtnUIFrameworkComponentConfig();
		privateViewCompanyProjectionNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewCompanyProjectionNameLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		privateViewCompanyProjectionNameLayout.setAddToParent(true);
		privateViewCompanyProjectionNameLayout.setGtnLayoutConfig(reportDataSelectionPrivateViewCompanyProjectionNameLayoutConfig);
		privateViewCompanyProjectionNameLayout
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_OPTION_INNER_LAYOUT);
		componentList.add(privateViewCompanyProjectionNameLayout);

	}

	private void addPrivateViewCompanyProjectionname(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionPrivateView = new GtnUIFrameworkComponentConfig();
		reportDataSelectionPrivateView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		reportDataSelectionPrivateView.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabPrivateViews");
		reportDataSelectionPrivateView.setComponentName("Private Views");
		reportDataSelectionPrivateView.setAddToParent(true);
		reportDataSelectionPrivateView.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);

		
		List<GtnUIFrameWorkActionConfig> list = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportDataSelectionPrivateViewPopupAction = new GtnUIFrameWorkActionConfig();
		reportDataSelectionPrivateViewPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		reportDataSelectionPrivateViewPopupAction.setActionParameterList(Arrays.asList("privateViewSearchLookupView", "Private View", "795", "875"));
		list.add(reportDataSelectionPrivateViewPopupAction);

		reportDataSelectionPrivateView.setGtnUIFrameWorkActionConfigList(list);
		componentList.add(reportDataSelectionPrivateView);

		GtnUIFrameworkLayoutConfig reportDataSelectionCompanyLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportDataSelectionCompanyLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionCompanyLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionCompanyLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabCompanyLayout");
		reportDataSelectionCompanyLayoutConfig.setAddToParent(true);
		reportDataSelectionCompanyLayoutConfig.setSpacing(true);
		reportDataSelectionCompanyLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		reportDataSelectionCompanyLayoutConfig.setGtnLayoutConfig(reportDataSelectionCompanyLayout);
		componentList.add(reportDataSelectionCompanyLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionCompany = new GtnUIFrameworkComponentConfig();
		reportDataSelectionCompany.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionCompany.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "company");
		reportDataSelectionCompany.setComponentName("Company");
		reportDataSelectionCompany.setAddToParent(true);
		reportDataSelectionCompany.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabCompanyLayout");
		GtnUIFrameworkComboBoxConfig reportDataSelectionCompanyTypeConfig = new GtnUIFrameworkComboBoxConfig();
		reportDataSelectionCompanyTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		reportDataSelectionCompanyTypeConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		reportDataSelectionCompany.setGtnComboboxConfig(reportDataSelectionCompanyTypeConfig);
		componentList.add(reportDataSelectionCompany);

		GtnUIFrameworkLayoutConfig reportDataSelectionProjectionNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportDataSelectionProjectionNameLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionNameLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionProjectionNameLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionNameLayout");
		reportDataSelectionProjectionNameLayoutConfig.setAddToParent(true);
		reportDataSelectionProjectionNameLayoutConfig.setSpacing(true);
		reportDataSelectionProjectionNameLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		reportDataSelectionProjectionNameLayoutConfig.setGtnLayoutConfig(reportDataSelectionProjectionNameLayout);
		componentList.add(reportDataSelectionProjectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionProjectionName = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProjectionName.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionProjectionName.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionName");
		reportDataSelectionProjectionName.setComponentName("Report Data Source");
		reportDataSelectionProjectionName.setAddToParent(true);
		reportDataSelectionProjectionName.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionNameLayout");

		GtnUIFrameworkComboBoxConfig reportDataSelectionProjectionNameConfig = new GtnUIFrameworkComboBoxConfig();
		reportDataSelectionProjectionNameConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		reportDataSelectionProjectionNameConfig.setComboBoxType(GtnFrameworkReportStringConstants.COMPANY_MASTER_GLCOMP);
		reportDataSelectionProjectionName.setGtnComboboxConfig(reportDataSelectionProjectionNameConfig);

		componentList.add(reportDataSelectionProjectionName);
	}

	private void addPublicViewBusinessUnitDescription(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionPublicView = new GtnUIFrameworkComponentConfig();
		reportDataSelectionPublicView.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELDVAADIN8);
		reportDataSelectionPublicView.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabPublicViews");
		reportDataSelectionPublicView.setComponentName("Public Views");
		reportDataSelectionPublicView.setAddToParent(true);
		reportDataSelectionPublicView.setEnable(false);
		reportDataSelectionPublicView.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);

		List<GtnUIFrameWorkActionConfig> actionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportDataSelectionPublicViewPopupAction = new GtnUIFrameWorkActionConfig();
		reportDataSelectionPublicViewPopupAction.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		reportDataSelectionPublicViewPopupAction.setActionParameterList(Arrays.asList("publicViewSearchLookupView", "Public View", "795", "875"));
		actionList.add(reportDataSelectionPublicViewPopupAction);
		reportDataSelectionPublicView.setGtnUIFrameWorkActionConfigList(actionList);


		componentList.add(reportDataSelectionPublicView);

		GtnUIFrameworkLayoutConfig reportDataSelectionBusinessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig businessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		businessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		businessUnitLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabBusinessUnitLayout");
		businessUnitLayoutConfig.setAddToParent(true);
		businessUnitLayoutConfig.setSpacing(true);
		businessUnitLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		businessUnitLayoutConfig.setGtnLayoutConfig(reportDataSelectionBusinessUnitLayout);
		componentList.add(businessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionBusinessUnit = new GtnUIFrameworkComponentConfig();
		reportDataSelectionBusinessUnit.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionBusinessUnit.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "businessUnit");
		reportDataSelectionBusinessUnit.setComponentName("Business Unit");
		reportDataSelectionBusinessUnit.setAddToParent(true);
		reportDataSelectionBusinessUnit.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabBusinessUnitLayout");
		GtnUIFrameworkComboBoxConfig businessUnitConfig = new GtnUIFrameworkComboBoxConfig();
		businessUnitConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitConfig.setComboBoxType(GtnFrameworkReportStringConstants.BUSINESS_UNIT_GLCOMP);
		reportDataSelectionBusinessUnit.setGtnComboboxConfig(businessUnitConfig);

		componentList.add(reportDataSelectionBusinessUnit);

		GtnUIFrameworkLayoutConfig reportDataSelectionProjectionDescLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionDescLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionDescLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionDescLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabProjectionDescLayout");
		projectionDescLayoutConfig.setAddToParent(true);
		projectionDescLayoutConfig.setSpacing(true);
		projectionDescLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_PROJECTION_SELECTIONLAYOUT1);
		projectionDescLayoutConfig.setGtnLayoutConfig(reportDataSelectionProjectionDescLayout);
		componentList.add(projectionDescLayoutConfig);

	}

	private void addTimePeriod(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig reportDataSelectionTimePeriodOuterLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionTimePeriodOuterLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig timePeriodPanel = new GtnUIFrameworkComponentConfig();
		timePeriodPanel.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		timePeriodPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabTimePeriodOuterLayout");
		timePeriodPanel.setAddToParent(true);
		timePeriodPanel.setSpacing(true);
		timePeriodPanel.setGtnLayoutConfig(reportDataSelectionTimePeriodOuterLayout);
		timePeriodPanel.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabFromToMainLayout");
		componentList.add(timePeriodPanel);
	
		GtnUIFrameworkComponentConfig reportDataSelectionPanel = new GtnUIFrameworkComponentConfig();
		reportDataSelectionPanel.setComponentName("Time Period");
		reportDataSelectionPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabTimePeriod");
		reportDataSelectionPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportDataSelectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionPanel.setAddToParent(true);
		reportDataSelectionPanel.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabTimePeriodOuterLayout");
		componentList.add(reportDataSelectionPanel);
	
		GtnUIFrameworkLayoutConfig reportDataSelectionTimePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		reportDataSelectionTimePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportDataSelectionFromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionFromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDataSelectionFromAndToperiodLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_TIME_PERIOD_INNER_LAYOUT);
		reportDataSelectionFromAndToperiodLayoutConfig.setSpacing(true);
		reportDataSelectionFromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDataSelectionFromAndToperiodLayoutConfig.setAddToParent(true);
		reportDataSelectionFromAndToperiodLayoutConfig.setGtnLayoutConfig(reportDataSelectionTimePeriodInnerLayout);
		reportDataSelectionFromAndToperiodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabTimePeriod");
		reportDataSelectionFromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
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
		reportDataSelectionFromPeriodLayoutConfig.setGtnLayoutConfig(reportDataSelectionFromPeriodLayout);
		reportDataSelectionFromPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_TIME_PERIOD_INNER_LAYOUT);
		componentList.add(reportDataSelectionFromPeriodLayoutConfig);
	
		GtnUIFrameworkComponentConfig reportDataSelectionFromPeriod = new GtnUIFrameworkComponentConfig();
		reportDataSelectionFromPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionFromPeriod.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabFromPeriod");
		reportDataSelectionFromPeriod.setComponentName("From ");
		reportDataSelectionFromPeriod.setAddToParent(true);
	
		reportDataSelectionFromPeriod.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabFromPeriodLayout");
		GtnUIFrameworkComboBoxConfig reportDataSelectionFromPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		reportDataSelectionFromPeriodLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		reportDataSelectionFromPeriodLoadConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_FROM_DATE);
		reportDataSelectionFromPeriod.setGtnComboboxConfig(reportDataSelectionFromPeriodLoadConfig);
	
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
		reportDataSelectionToPeriodLayoutConfig.setGtnLayoutConfig(reportDataSelectionToPeriodLayout);
		reportDataSelectionToPeriodLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.DS_TAB_TIME_PERIOD_INNER_LAYOUT);
		componentList.add(reportDataSelectionToPeriodLayoutConfig);
	
		GtnUIFrameworkComponentConfig reportDataSelectionToPeriod = new GtnUIFrameworkComponentConfig();
		reportDataSelectionToPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		reportDataSelectionToPeriod.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabToPeriod");
		reportDataSelectionToPeriod.setComponentName("To ");
		reportDataSelectionToPeriod.setAddToParent(true);
		reportDataSelectionToPeriod.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "dsTabToPeriodLayout");
		reportDataSelectionToPeriod.setEnable(false);
	
		GtnUIFrameworkComboBoxConfig reportDataSelectionToPeriodTypeLoadConfig = new GtnUIFrameworkComboBoxConfig();
		reportDataSelectionToPeriodTypeLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		reportDataSelectionToPeriodTypeLoadConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_TO_DATE);
		reportDataSelectionToPeriod.setGtnComboboxConfig(reportDataSelectionToPeriodTypeLoadConfig);
		componentList.add(reportDataSelectionToPeriod);
	}

	private void addProductSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionProductSelectionPanel = new GtnUIFrameworkComponentConfig();
		reportDataSelectionProductSelectionPanel.setComponentName("Product Selection");

		reportDataSelectionProductSelectionPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productSelectionPanel");
		reportDataSelectionProductSelectionPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + parentId);
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

	private void addNavigationButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig reportDataSelectionNavigationButtonConfig = layoutConfig
				.getHorizontalLayoutConfig("navigationButtonLayout", parentId);
		componentList.add(reportDataSelectionNavigationButtonConfig);

		GtnUIFrameworkComponentConfig reportDataSelectionNextButtonConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionNextButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDataSelectionNextButtonConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE+ "nextButtonConfig");
		reportDataSelectionNextButtonConfig.setComponentName("NEXT");
		reportDataSelectionNextButtonConfig.setAddToParent(true);
		reportDataSelectionNextButtonConfig.setParentComponentId(reportDataSelectionNavigationButtonConfig.getComponentId());

		GtnUIFrameworkComponentConfig reportDataSelectionCloseButtonConfig = new GtnUIFrameworkComponentConfig();
		reportDataSelectionCloseButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportDataSelectionCloseButtonConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE+ "closeButtonConfig");
		reportDataSelectionCloseButtonConfig.setComponentName("CLOSE");
		reportDataSelectionCloseButtonConfig.setAddToParent(true);
		reportDataSelectionCloseButtonConfig.setParentComponentId(reportDataSelectionNavigationButtonConfig.getComponentId());

		componentList.add(reportDataSelectionNextButtonConfig);
		componentList.add(reportDataSelectionCloseButtonConfig);
	}

}
