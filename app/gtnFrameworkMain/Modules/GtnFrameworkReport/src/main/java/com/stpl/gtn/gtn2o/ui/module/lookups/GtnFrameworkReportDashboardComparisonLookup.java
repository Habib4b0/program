package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionAddAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionBeforeCloseAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionRemoveAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionResultsLoadAction;
import com.stpl.gtn.gtn2o.ui.action.GtnReportComparisonProjectionSubmitAction;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportDashboardComparisonResultsSearchAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportDashboardComparisonLookup {

	private final GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	private static final String[] columnPropertyIds = { GtnFrameworkReportStringConstants.PROJECTION_NAME,
			GtnFrameworkReportStringConstants.DESCRIPTION2, GtnFrameworkReportStringConstants.MARKET_TYPE,
			GtnFrameworkReportStringConstants.CONTRACT_HOLDER, GtnFrameworkReportStringConstants.CONTRACT_LOWER,
			GtnFrameworkReportStringConstants.BRAND, GtnFrameworkReportStringConstants.CREATED_DATE2,
			GtnFrameworkReportStringConstants.CREATED_BY };
	private static final GtnUIFrameworkComponentType[] comparisonLookupComponentType = {
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

	public GtnUIFrameworkViewConfig getReportComparisonLookupView() {
		GtnUIFrameworkViewConfig dashboardComparisonLookupRootView = configProvider.getViewConfig("Comparison Lookup",
				"dashboardcomparisonLookupView", false);
		dashboardComparisonLookupRootView.setResetAllowed(true);
		addComponentList(dashboardComparisonLookupRootView);
		return dashboardComparisonLookupRootView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig lookupView) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		lookupView.setGtnComponentList(componentList);

		addProjectionType(componentList, GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_LOOKUP);
		addProjectionSearchPanel(componentList, GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_LOOKUP);
		addWorkflowStatusNameComponents(componentList, GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_LOOKUP);
		addProjectionNameComponents(componentList, GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_LOOKUP);
		addDescriptionComponents(componentList, GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_LOOKUP);
		addCreatedDate(componentList, GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_LOOKUP);
		addControlButtonLayout(componentList, GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_LOOKUP);
		addResultsMainLayout(componentList);
		addResultPanel(componentList);
		addResultsButtonLayout(componentList, GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_LOOKUP);
		addRDRuleDetailsPanel(componentList);
		addActionButtonLayout(componentList, GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_LOOKUP);

	}

	private void addProjectionType(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig dashboardProjectionTypeLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionTypeLayout", false, null);
		dashboardProjectionTypeLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		dashboardProjectionTypeLayoutConfig.addComponentStyle("stpl-margin-top-15");
		dashboardProjectionTypeLayoutConfig.addComponentStyle("stpl-margin-bottom-15");
		componentList.add(dashboardProjectionTypeLayoutConfig);

		GtnUIFrameworkComponentConfig dashboardProjectionType = new GtnUIFrameworkComponentConfig();
		dashboardProjectionType.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		dashboardProjectionType.setComponentName("Projection Type");
		dashboardProjectionType.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PROJECTION_TYPE);
		dashboardProjectionType.setAddToParent(true);
		dashboardProjectionType.setCustomReference("integerId");
		dashboardProjectionType.setParentComponentId(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionTypeLayout");
		dashboardProjectionType.setDefaultFocus(true);
		dashboardProjectionType.setComponentWsFieldId("projectionType");

		GtnUIFrameworkComboBoxConfig dashboardprojectionTypeConfig = new GtnUIFrameworkComboBoxConfig();
		dashboardprojectionTypeConfig.setItemValues(Arrays.asList("F", "C"));
		dashboardprojectionTypeConfig.setItemCaptionValues(
				GtnFrameworkReportStringConstants.getReportComparisonProjectionTypeLoadCombobox());
		dashboardProjectionType.setGtnComboboxConfig(dashboardprojectionTypeConfig);

		GtnUIFrameworkValidationConfig dashboardProjectionTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		dashboardProjectionTypeValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		dashboardProjectionType.setGtnUIFrameworkValidationConfig(dashboardProjectionTypeValidationConfig);
		componentList.add(dashboardProjectionType);
	}

	private void addResultsMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig resultsPanel = configProvider.getPanelConfig("resultsMainLayout", false, null);
		componentList.add(resultsPanel);

		GtnUIFrameworkComponentConfig resultsMainLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.RESULTS_MAIN_LAYOUT_CONFIG, true, "resultsMainLayout");
		resultsMainLayout.setComponentWidth("100%");

		List<String> resultsMainLayoutStyles = new ArrayList<>();
		resultsMainLayoutStyles.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		resultsMainLayoutStyles.add(GtnFrameworkCssConstants.NO_MARGIN);

		resultsMainLayout.setComponentStyle(resultsMainLayoutStyles);

		componentList.add(resultsMainLayout);

	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultsPanel = configProvider.getPanelConfig("resultPanel", true,
				GtnFrameworkReportStringConstants.RESULTS_MAIN_LAYOUT_CONFIG);
		resultsPanel.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		resultsPanel.setAuthorizationIncluded(true);
		resultsPanel.setComponentWidth("100%");
		componentList.add(resultsPanel);
		addPagedTableComponent(componentList, resultsPanel.getComponentId());

	}

	private void addProjectionSearchPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig projectionSearchOptionsPanel = new GtnUIFrameworkComponentConfig();
		projectionSearchOptionsPanel.setComponentName("Projection Search");
		projectionSearchOptionsPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionOptions");
		projectionSearchOptionsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionSearchOptionsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionSearchOptionsPanel.setSpacing(true);
		componentList.add(projectionSearchOptionsPanel);

		GtnUIFrameworkLayoutConfig projectionSearchOptionsMainLayout = new GtnUIFrameworkLayoutConfig();
		projectionSearchOptionsMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		projectionSearchOptionsMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig projectionOptionsMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionsMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionsMainLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		projectionOptionsMainLayoutConfig.setAddToParent(true);
		projectionOptionsMainLayoutConfig.setSpacing(true);
		projectionOptionsMainLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionOptions");
		projectionOptionsMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionsMainLayoutConfig.setGtnLayoutConfig(projectionSearchOptionsMainLayout);
		componentList.add(projectionOptionsMainLayoutConfig);

		GtnUIFrameworkLayoutConfig projectionSearchOptionsInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionSearchOptionsInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionsInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionsInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionsInnerLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		projectionOptionsInnerLayoutConfig.setAddToParent(true);
		projectionOptionsInnerLayoutConfig.setSpacing(true);
		projectionOptionsInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		projectionOptionsInnerLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		projectionOptionsInnerLayoutConfig.setGtnLayoutConfig(projectionSearchOptionsInnerLayout);
		componentList.add(projectionOptionsInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig reportDashboardComparisonModeSelectionLayout = new GtnUIFrameworkLayoutConfig();
		reportDashboardComparisonModeSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportDashboardComparisonModeSelectionConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardComparisonModeSelectionConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardComparisonModeSelectionConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupModeSelectionLayout");
		reportDashboardComparisonModeSelectionConfig.setAddToParent(true);
		reportDashboardComparisonModeSelectionConfig.setGtnLayoutConfig(reportDashboardComparisonModeSelectionLayout);
		reportDashboardComparisonModeSelectionConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		reportDashboardComparisonModeSelectionConfig
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(reportDashboardComparisonModeSelectionConfig);

		GtnUIFrameworkLayoutConfig reportDashboardComparisonFromToMainLayout = new GtnUIFrameworkLayoutConfig();
		reportDashboardComparisonFromToMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportDashboardComparisonFromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardComparisonFromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardComparisonFromToLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromToMainLayout");
		reportDashboardComparisonFromToLayoutConfig.setAddToParent(true);
		reportDashboardComparisonFromToLayoutConfig.setSpacing(true);
		reportDashboardComparisonFromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		reportDashboardComparisonFromToLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		reportDashboardComparisonFromToLayoutConfig.setGtnLayoutConfig(reportDashboardComparisonFromToMainLayout);
		componentList.add(reportDashboardComparisonFromToLayoutConfig);

		GtnUIFrameworkLayoutConfig projectionSearchOptionsCssLayout = new GtnUIFrameworkLayoutConfig();
		projectionSearchOptionsCssLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionsLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionsLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		projectionOptionsLayoutConfig.setAddToParent(true);
		projectionOptionsLayoutConfig.setGtnLayoutConfig(projectionSearchOptionsCssLayout);
		projectionOptionsLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		projectionOptionsLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(projectionOptionsLayoutConfig);

	}

	private void addWorkflowStatusNameComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig workflowStatusLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig workflowStatusLayoutConfig = new GtnUIFrameworkComponentConfig();
		workflowStatusLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		workflowStatusLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		workflowStatusLayoutConfig.setAddToParent(true);
		workflowStatusLayoutConfig.setSpacing(true);
		workflowStatusLayoutConfig.addComponentStyle("stpl-margin-top-15");
		workflowStatusLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		workflowStatusLayoutConfig.setGtnLayoutConfig(workflowStatusLayout);
		componentList.add(workflowStatusLayoutConfig);

		GtnUIFrameworkComponentConfig dashboardWorkflowStatus = new GtnUIFrameworkComponentConfig();
		dashboardWorkflowStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		dashboardWorkflowStatus.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.WORKFLOW_STATUS);
		dashboardWorkflowStatus.setComponentName("Workflow Status: ");
		dashboardWorkflowStatus.setAddToParent(true);
		dashboardWorkflowStatus.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		dashboardWorkflowStatus.setCustomReference("integerId");
		dashboardWorkflowStatus.setComponentWsFieldId("workflowStatus");

		GtnUIFrameworkComboBoxConfig dashboardWorkflowStatusConfig = new GtnUIFrameworkComboBoxConfig();
		dashboardWorkflowStatusConfig
				.setItemValues(GtnFrameworkReportStringConstants.getReportComparisonWorkflowStatusLoadCombobox());
		dashboardWorkflowStatus.setGtnComboboxConfig(dashboardWorkflowStatusConfig);

		GtnUIFrameworkValidationConfig dashboardWorkflowStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		dashboardWorkflowStatusValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		dashboardWorkflowStatus.setGtnUIFrameworkValidationConfig(dashboardWorkflowStatusValidationConfig);

		componentList.add(dashboardWorkflowStatus);

		GtnUIFrameworkLayoutConfig reportDashboardBusinessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportDashboardBusinessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardBusinessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardBusinessUnitLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.BUSINESS_UNIT_LAYOUT);
		reportDashboardBusinessUnitLayoutConfig.setAddToParent(true);
		reportDashboardBusinessUnitLayoutConfig.setSpacing(true);
		reportDashboardBusinessUnitLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportDashboardBusinessUnitLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportDashboardBusinessUnitLayoutConfig.setGtnLayoutConfig(reportDashboardBusinessUnitLayout);
		componentList.add(reportDashboardBusinessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig reportDashboardMarketType = new GtnUIFrameworkComponentConfig();
		reportDashboardMarketType.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportDashboardMarketType.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupMarketType");
		reportDashboardMarketType.setComponentName("Market Type: ");
		reportDashboardMarketType.setAddToParent(true);
		reportDashboardMarketType.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.BUSINESS_UNIT_LAYOUT);
		reportDashboardMarketType.setComponentWsFieldId(GtnFrameworkReportStringConstants.MARKET_TYPE);

		componentList.add(reportDashboardMarketType);
		GtnUIFrameworkLayoutConfig reportDashboardBrandLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportDashboardBrandLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardBrandLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardBrandLayoutConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrandLayoutConfig");
		reportDashboardBrandLayoutConfig.setAddToParent(true);
		reportDashboardBrandLayoutConfig.setSpacing(true);
		reportDashboardBrandLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportDashboardBrandLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportDashboardBrandLayoutConfig.setGtnLayoutConfig(reportDashboardBrandLayout);
		componentList.add(reportDashboardBrandLayoutConfig);

		GtnUIFrameworkComponentConfig reportDashboardBrand = new GtnUIFrameworkComponentConfig();
		reportDashboardBrand.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportDashboardBrand.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrand");
		reportDashboardBrand.setComponentName("Brand: ");
		reportDashboardBrand.setAddToParent(true);
		reportDashboardBrand.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrandLayoutConfig");
		reportDashboardBrand.setComponentWsFieldId(GtnFrameworkReportStringConstants.BRAND);
		componentList.add(reportDashboardBrand);
	}

	private void addProjectionNameComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig projectionNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionNameLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionNameLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionNameLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		projectionNameLayoutConfig.setAddToParent(true);
		projectionNameLayoutConfig.setSpacing(true);
		projectionNameLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		projectionNameLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		projectionNameLayoutConfig.setGtnLayoutConfig(projectionNameLayout);
		componentList.add(projectionNameLayoutConfig);

		GtnUIFrameworkComponentConfig reportDashboardprojectionName = new GtnUIFrameworkComponentConfig();
		reportDashboardprojectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportDashboardprojectionName.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PROJECTION_NAME);
		reportDashboardprojectionName.setComponentName("Projection Name: ");
		reportDashboardprojectionName.setAddToParent(true);
		reportDashboardprojectionName.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		reportDashboardprojectionName.setComponentWsFieldId(GtnFrameworkReportStringConstants.PROJECTION_NAME);

		componentList.add(reportDashboardprojectionName);

		GtnUIFrameworkLayoutConfig reportDashboardContractHolderLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportDashboardContractHolderConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardContractHolderConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardContractHolderConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "reportComparisonLookupContractHolderConfig");
		reportDashboardContractHolderConfig.setAddToParent(true);
		reportDashboardContractHolderConfig.setSpacing(true);
		reportDashboardContractHolderConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportDashboardContractHolderConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportDashboardContractHolderConfig.setGtnLayoutConfig(reportDashboardContractHolderLayout);
		componentList.add(reportDashboardContractHolderConfig);

		GtnUIFrameworkComponentConfig reportDashboardContractHolder = new GtnUIFrameworkComponentConfig();
		reportDashboardContractHolder.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportDashboardContractHolder.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolder");
		reportDashboardContractHolder.setComponentName("Contract Holder: ");
		reportDashboardContractHolder.setAddToParent(true);
		reportDashboardContractHolder.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "reportComparisonLookupContractHolderConfig");
		reportDashboardContractHolder.setComponentWsFieldId(GtnFrameworkReportStringConstants.CONTRACT_HOLDER);
		componentList.add(reportDashboardContractHolder);

		GtnUIFrameworkLayoutConfig reportDashboardNdcLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportDashboardNdcLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardNdcLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardNdcLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_LAYOUT);
		reportDashboardNdcLayoutConfig.setAddToParent(true);
		reportDashboardNdcLayoutConfig.setSpacing(true);
		reportDashboardNdcLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportDashboardNdcLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportDashboardNdcLayoutConfig.setGtnLayoutConfig(reportDashboardNdcLayout);
		componentList.add(reportDashboardNdcLayoutConfig);

		GtnUIFrameworkComponentConfig reportDashboardNdcConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardNdcConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportDashboardNdcConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_CONFIG);
		reportDashboardNdcConfig.setComponentName("NDC #: ");
		reportDashboardNdcConfig.setAddToParent(true);
		reportDashboardNdcConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_LAYOUT);
		reportDashboardNdcConfig.setComponentWsFieldId("comparisonNDC");
		componentList.add(reportDashboardNdcConfig);

	}

	private void addDescriptionComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig projectionDescriptionLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig projectionDescriptionLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionDescriptionLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionDescriptionLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		projectionDescriptionLayoutConfig.setAddToParent(true);
		projectionDescriptionLayoutConfig.setSpacing(true);
		projectionDescriptionLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		projectionDescriptionLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		projectionDescriptionLayoutConfig.setGtnLayoutConfig(projectionDescriptionLayout);
		componentList.add(projectionDescriptionLayoutConfig);

		GtnUIFrameworkComponentConfig projectionDescription = new GtnUIFrameworkComponentConfig();
		projectionDescription.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionDescription
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionDescription");
		projectionDescription.setComponentName("Description: ");
		projectionDescription.setAddToParent(true);
		projectionDescription.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		projectionDescription.setComponentWsFieldId(GtnFrameworkReportStringConstants.DESCRIPTION2);

		componentList.add(projectionDescription);

		GtnUIFrameworkLayoutConfig reportContractLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportDashboardContractConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardContractConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardContractConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContractConfig");
		reportDashboardContractConfig.setAddToParent(true);
		reportDashboardContractConfig.setSpacing(true);
		reportDashboardContractConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportDashboardContractConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportDashboardContractConfig.setGtnLayoutConfig(reportContractLayout);
		componentList.add(reportDashboardContractConfig);

		GtnUIFrameworkComponentConfig reportDashboardContract = new GtnUIFrameworkComponentConfig();
		reportDashboardContract.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportDashboardContract
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContract");
		reportDashboardContract.setComponentName("Contract: ");
		reportDashboardContract.setAddToParent(true);
		reportDashboardContract.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContractConfig");
		reportDashboardContract.setComponentWsFieldId(GtnFrameworkReportStringConstants.CONTRACT_LOWER);
		componentList.add(reportDashboardContract);

		GtnUIFrameworkLayoutConfig reportDashboardNdcNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportDashboardNdcNameConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardNdcNameConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardNdcNameConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcNameConfig");
		reportDashboardNdcNameConfig.setAddToParent(true);
		reportDashboardNdcNameConfig.setSpacing(true);
		reportDashboardNdcNameConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportDashboardNdcNameConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportDashboardNdcNameConfig.setGtnLayoutConfig(reportDashboardNdcNameLayout);
		componentList.add(reportDashboardNdcNameConfig);

		GtnUIFrameworkComponentConfig reportDashboardNdcName = new GtnUIFrameworkComponentConfig();
		reportDashboardNdcName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportDashboardNdcName
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcName");
		reportDashboardNdcName.setComponentName("NDC Name: ");
		reportDashboardNdcName.setAddToParent(true);
		reportDashboardNdcName.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcNameConfig");
		reportDashboardNdcName.setComponentWsFieldId("ndcName");
		componentList.add(reportDashboardNdcName);

	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig datePanel = new GtnUIFrameworkComponentConfig();
		datePanel.setComponentName(GtnFrameworkReportStringConstants.CREATED_DATE);
		datePanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		datePanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		datePanel.setComponentWidth("65%");
		datePanel.setAddToParent(true);
		datePanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromToMainLayout");
		datePanel.addComponentStyle(GtnFrameworkCssConstants.PANEL_TIME_PERIOD_RTN_FOR);
		datePanel.addComponentStyle("stpl-margin-bottom-14");
		componentList.add(datePanel);

		GtnUIFrameworkLayoutConfig dashboardTimePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		dashboardTimePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig dashboardFromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		dashboardFromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dashboardFromAndToperiodLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		dashboardFromAndToperiodLayoutConfig.setSpacing(true);
		dashboardFromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dashboardFromAndToperiodLayoutConfig.setAddToParent(true);
		dashboardFromAndToperiodLayoutConfig.setGtnLayoutConfig(dashboardTimePeriodInnerLayout);
		dashboardFromAndToperiodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		dashboardFromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(dashboardFromAndToperiodLayoutConfig);

		GtnUIFrameworkComponentConfig dashboardComparisonFromAndToperiodVerticalLayoutConfig = configProvider
				.getVerticalLayoutConfig(
						GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_FROM_AND_TOPERIOD_VERTICAL_LAYOUT_CONFIG,
						true, namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		dashboardComparisonFromAndToperiodVerticalLayoutConfig.setComponentWidth("100%");
		dashboardComparisonFromAndToperiodVerticalLayoutConfig.setComponentHight("85px");
		componentList.add(dashboardComparisonFromAndToperiodVerticalLayoutConfig);

		GtnUIFrameworkLayoutConfig dashboardFromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		dashboardFromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig dashboardComparisonLookupFromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		dashboardComparisonLookupFromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dashboardComparisonLookupFromPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		dashboardComparisonLookupFromPeriodLayoutConfig.setAddToParent(true);
		dashboardComparisonLookupFromPeriodLayoutConfig.setGtnLayoutConfig(dashboardFromPeriodLayout);
		dashboardComparisonLookupFromPeriodLayoutConfig.setParentComponentId(
				GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_FROM_AND_TOPERIOD_VERTICAL_LAYOUT_CONFIG);
		componentList.add(dashboardComparisonLookupFromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig dashboardComparisonfromPeriod = new GtnUIFrameworkComponentConfig();
		dashboardComparisonfromPeriod.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		dashboardComparisonfromPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD);
		dashboardComparisonfromPeriod.setComponentName("From ");
		dashboardComparisonfromPeriod.setAddToParent(true);
		dashboardComparisonfromPeriod
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		dashboardComparisonfromPeriod.setComponentWsFieldId("fromPeriod");

		componentList.add(dashboardComparisonfromPeriod);

		GtnUIFrameworkLayoutConfig reportDashboardToPeriodLayout = new GtnUIFrameworkLayoutConfig();
		reportDashboardToPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig reportDashboardToPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportDashboardToPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDashboardToPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");
		reportDashboardToPeriodLayoutConfig.setAddToParent(true);
		reportDashboardToPeriodLayoutConfig.setGtnLayoutConfig(reportDashboardToPeriodLayout);
		reportDashboardToPeriodLayoutConfig.setParentComponentId(
				GtnFrameworkReportStringConstants.DASHBOARD_COMPARISON_FROM_AND_TOPERIOD_VERTICAL_LAYOUT_CONFIG);
		componentList.add(reportDashboardToPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig reportDashboardToPeriod = new GtnUIFrameworkComponentConfig();
		reportDashboardToPeriod.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		reportDashboardToPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD);
		reportDashboardToPeriod.setComponentName("To ");
		reportDashboardToPeriod.setAddToParent(true);
		reportDashboardToPeriod
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");
		reportDashboardToPeriod.setComponentWsFieldId("toPeriod");

		componentList.add(reportDashboardToPeriod);
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig dashboardComparisonControlButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkReportStringConstants.CONTROL_BUTTON_LAYOUT, false, null);
		dashboardComparisonControlButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(dashboardComparisonControlButtonLayout);
		addSearchButtonComponent(componentList, nameSpace);
		addResetButtonComponent(componentList, nameSpace);
	}

	private void addResultsButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig resultsBtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkReportStringConstants.CONTROL_BUTTON_LAYOUT, false, null);
		resultsBtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(resultsBtnLayout);
		addAddButtonComponent(componentList, nameSpace);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig searchBtnConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "searchButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchBtnConfig.setAuthorizationIncluded(true);
		searchBtnConfig.setComponentName("SEARCH");
		componentList.add(searchBtnConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig searchCriteriaValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		searchCriteriaValidationActionConfig.setFieldValues(Arrays.asList(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PROJECTION_TYPE,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.WORKFLOW_STATUS));

		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkCommonConstants.ERROR);
		alertParams.add("Please select a Workflow Status");
		alertActionConfig.setActionParameterList(alertParams);

		searchCriteriaValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(alertActionConfig)));
		searchActionConfigList.add(searchCriteriaValidationActionConfig);

		GtnUIFrameWorkActionConfig searchAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		searchAction.setActionParameterList(Arrays.asList(
				GtnReportDashboardComparisonResultsSearchAction.class.getName(),
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT,
				"reportingDashboardTab_displaySelectionTabCustomView",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PROJECTION_TYPE,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.WORKFLOW_STATUS,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PROJECTION_NAME,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolder",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.NDC_CONFIG,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupMarketType",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrand",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionDescription",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContract",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcName",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD,
				nameSpace, nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "addButtonConfig"));
		searchActionConfigList.add(searchAction);

		searchBtnConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		GtnUIFrameWorkActionConfig dsSearchConfirmResetAction = new GtnUIFrameWorkActionConfig();
		dsSearchConfirmResetAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		dsSearchConfirmResetAction.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		dsSearchConfirmResetAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_SEARCH_PANEL_RESET_CONFIRMATION_MESSAGE);
		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		resetActionConfigList.add(rdResetButtonAction(false));
		dsSearchConfirmResetAction.addActionParameter(resetActionConfigList);
		resetButtonConfig.addGtnUIFrameWorkActionConfig(dsSearchConfirmResetAction);
		componentList.add(resetButtonConfig);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig addBtnConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "addButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		addBtnConfig.setComponentName("ADD");
		addBtnConfig.setAuthorizationIncluded(true);
		addBtnConfig.setEnable(false);
		componentList.add(addBtnConfig);
		GtnUIFrameWorkActionConfig addComparisonProjectionAction = new GtnUIFrameWorkActionConfig();
		addComparisonProjectionAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addComparisonProjectionAction.addActionParameter(GtnReportComparisonProjectionAddAction.class.getName());
		addComparisonProjectionAction
				.addActionParameter(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
		addComparisonProjectionAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		addComparisonProjectionAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PROJECTION_TYPE);
		addBtnConfig.addGtnUIFrameWorkActionConfig(addComparisonProjectionAction);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig comparisonLookupResultsGridComponent = new GtnUIFrameworkComponentConfig();
		comparisonLookupResultsGridComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		comparisonLookupResultsGridComponent
				.setComponentId(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
		comparisonLookupResultsGridComponent.setComponentName("Results");
		comparisonLookupResultsGridComponent.setParentComponentId(parentId);
		comparisonLookupResultsGridComponent.setAddToParent(true);

		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		comparisonLookupResultsGridComponent.setComponentWidth("100%");
		comparisonLookupResultsGridComponent.setComponentStyle(tableStyle);

		componentList.add(comparisonLookupResultsGridComponent);

		GtnUIFrameworkPagedTableConfig comparisonLookupResultsGridConfig = new GtnUIFrameworkPagedTableConfig();
		comparisonLookupResultsGridConfig.setEditable(false);
		comparisonLookupResultsGridConfig.setFilterBar(true);
		comparisonLookupResultsGridConfig.setPageLength(10);
		comparisonLookupResultsGridConfig.setItemPerPage(10);
		comparisonLookupResultsGridConfig.setSelectable(true);
		comparisonLookupResultsGridConfig.setSinkItemPerPageWithPageLength(false);
		comparisonLookupResultsGridConfig.setItemsPerPageAlignCentre(false);
		comparisonLookupResultsGridConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		comparisonLookupResultsGridConfig
				.setColumnHeaders(Arrays.asList("Projection Name", "Description", "Market Type", "Contract Holder",
						"Contract", "Brand", GtnFrameworkReportStringConstants.CREATED_DATE, "Created By"));
		comparisonLookupResultsGridConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkReportStringConstants.PROJECTION_NAME, GtnFrameworkReportStringConstants.DESCRIPTION2,
				GtnFrameworkReportStringConstants.MARKET_TYPE, GtnFrameworkReportStringConstants.CONTRACT_HOLDER,
				GtnFrameworkReportStringConstants.CONTRACT_LOWER, GtnFrameworkReportStringConstants.BRAND,
				GtnFrameworkReportStringConstants.CREATED_DATE2, GtnFrameworkReportStringConstants.CREATED_BY });
		comparisonLookupResultsGridConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_COMPARISONLOOKUP_AVAILABLETABLE_COUNTSERVICE);
		comparisonLookupResultsGridConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_COMPARISONLOOKUP_AVAILABLETABLE_LOADSERVICE);
		comparisonLookupResultsGridConfig.setCustomFilterConfigMap(getRDCustomFilterConfig());
		comparisonLookupResultsGridComponent.setModuleName("report");

		comparisonLookupResultsGridComponent.setGtnPagedTableConfig(comparisonLookupResultsGridConfig);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getRDCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> rdComparisonLookupCustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		String[] rdComparisonLookupComboboxIds = new String[1];
		String[] rdComparisonLookupComboBoxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig rdComparisonLookupFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			rdComparisonLookupFilterConfig.setPropertId(columnPropertyIds[i]);
			rdComparisonLookupFilterConfig.setGtnComponentType(comparisonLookupComponentType[i]);
			if ((startIndex < rdComparisonLookupComboboxIds.length)
					&& columnPropertyIds[i].equals(rdComparisonLookupComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig rdComparisonLookupSearchFilterConfig = new GtnUIFrameworkComponentConfig();
				rdComparisonLookupSearchFilterConfig.setComponentId("customFilterComboBox");
				rdComparisonLookupSearchFilterConfig.setComponentName("customFilterComboBox");
				rdComparisonLookupSearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				rdComparisonLookupSearchFilterConfig.getGtnComboboxConfig()
						.setComboBoxType(rdComparisonLookupComboBoxType[startIndex]);
				rdComparisonLookupSearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				rdComparisonLookupFilterConfig.setGtnComponentConfig(rdComparisonLookupSearchFilterConfig);
				startIndex++;
			}
			rdComparisonLookupCustomFilterConfigMap.put(rdComparisonLookupFilterConfig.getPropertId(),
					rdComparisonLookupFilterConfig);
		}
		return rdComparisonLookupCustomFilterConfigMap;
	}

	private void addRDRuleDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig selectedProjectionsPanel = configProvider.getPanelConfig("ruleDetailsResultPanel",
				false, null);
		selectedProjectionsPanel.setComponentWidth("100%");
		selectedProjectionsPanel.setComponentName("Projections");
		selectedProjectionsPanel.setAuthorizationIncluded(true);
		componentList.add(selectedProjectionsPanel);
		ruleDetailsResultDataTable(componentList, selectedProjectionsPanel.getComponentId());
	}

	private void ruleDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig projectionResultsPagedGridComponent = new GtnUIFrameworkComponentConfig();
		projectionResultsPagedGridComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		projectionResultsPagedGridComponent.setComponentId(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		projectionResultsPagedGridComponent.setComponentName("Projections");
		projectionResultsPagedGridComponent.setParentComponentId(parentId);
		projectionResultsPagedGridComponent.setAddToParent(true);
		projectionResultsPagedGridComponent.setResetToDefaultAllowed(false);

		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		projectionResultsPagedGridComponent.setComponentWidth("100%");
		projectionResultsPagedGridComponent.setComponentStyle(tableStyle);

		componentList.add(projectionResultsPagedGridComponent);

		GtnUIFrameworkPagedTableConfig comparisonLookupProjectionsPagedGridConfig = new GtnUIFrameworkPagedTableConfig();
		comparisonLookupProjectionsPagedGridConfig.setEditable(false);
		comparisonLookupProjectionsPagedGridConfig.setFilterBar(true);
		comparisonLookupProjectionsPagedGridConfig.setPageLength(10);
		comparisonLookupProjectionsPagedGridConfig.setItemPerPage(10);
		comparisonLookupProjectionsPagedGridConfig.setSelectable(true);
		comparisonLookupProjectionsPagedGridConfig.setSinkItemPerPageWithPageLength(false);
		comparisonLookupProjectionsPagedGridConfig.setPaginationOff(true);
		comparisonLookupProjectionsPagedGridConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		comparisonLookupProjectionsPagedGridConfig
				.setColumnHeaders(Arrays.asList("Projection Name", "Description", "Market Type", "Contract Holder",
						"Contract", "Brand", GtnFrameworkReportStringConstants.CREATED_DATE, "Created By"));
		comparisonLookupProjectionsPagedGridConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkReportStringConstants.PROJECTION_NAME, GtnFrameworkReportStringConstants.DESCRIPTION2,
				GtnFrameworkReportStringConstants.MARKET_TYPE, GtnFrameworkReportStringConstants.CONTRACT_HOLDER,
				GtnFrameworkReportStringConstants.CONTRACT_LOWER, GtnFrameworkReportStringConstants.BRAND,
				GtnFrameworkReportStringConstants.CREATED_DATE2, GtnFrameworkReportStringConstants.CREATED_BY });
		comparisonLookupProjectionsPagedGridConfig.setCustomFilterConfigMap(getRDCustomFilterConfig());
		projectionResultsPagedGridComponent.setGtnPagedTableConfig(comparisonLookupProjectionsPagedGridConfig);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig actionBtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkReportStringConstants.ACTION_BUTTON_LAYOUT, false, null);
		actionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(actionBtnLayout);
		addSubmitButtonComponent(componentList, nameSpace);
		addRestButtonComponent(componentList, nameSpace);
		addCloseButtonComponent(componentList, nameSpace);
		addRemoveButtonComponent(componentList, nameSpace);
	}

	private void addSubmitButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig submitBtn = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "submitButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		submitBtn.setAuthorizationIncluded(true);
		submitBtn.setComponentName("SUBMIT");
		componentList.add(submitBtn);

		List<GtnUIFrameWorkActionConfig> submitActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig comparisonProjectionSubmitAction = new GtnUIFrameWorkActionConfig();
		comparisonProjectionSubmitAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		comparisonProjectionSubmitAction.addActionParameter(GtnReportComparisonProjectionSubmitAction.class.getName());
		comparisonProjectionSubmitAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		comparisonProjectionSubmitAction.addActionParameter("reportingDashboardTab_reportingDashboardComparisonConfig");
		submitActionList.add(comparisonProjectionSubmitAction);

		GtnUIFrameWorkActionConfig comparisonPopupCloseAction = new GtnUIFrameWorkActionConfig();
		comparisonPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		comparisonProjectionSubmitAction.addActionParameter(comparisonPopupCloseAction);
		submitBtn.setGtnUIFrameWorkActionConfigList(submitActionList);
	}

	private void addRestButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig reportRDResetButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		reportRDResetButton.setAuthorizationIncluded(true);
		reportRDResetButton.setComponentName("RESET");
		GtnUIFrameWorkActionConfig reportRDLowerConfirmResetAction = new GtnUIFrameWorkActionConfig();
		reportRDLowerConfirmResetAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		reportRDLowerConfirmResetAction.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		reportRDLowerConfirmResetAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOWER_PANEL_RESET_CONFIRMATION_MESSAGE);
		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		reportRDLowerConfirmResetAction.addActionParameter(resetActionConfigList);
		resetActionConfigList.add(rdResetButtonAction(true));
		GtnUIFrameWorkActionConfig rdGridReloadAction = new GtnUIFrameWorkActionConfig();
		rdGridReloadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rdGridReloadAction.addActionParameter(GtnReportComparisonProjectionResultsLoadAction.class.getName());
		rdGridReloadAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		resetActionConfigList.add(rdGridReloadAction);
		reportRDResetButton.addGtnUIFrameWorkActionConfig(reportRDLowerConfirmResetAction);
		componentList.add(reportRDResetButton);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig closeBtn = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "closeButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		closeBtn.setComponentName("CLOSE");
		closeBtn.setAuthorizationIncluded(true);
		componentList.add(closeBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig rdBeforeCloseAction = new GtnUIFrameWorkActionConfig();
		rdBeforeCloseAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rdBeforeCloseAction.addActionParameter(GtnReportComparisonProjectionBeforeCloseAction.class.getName());
		rdBeforeCloseAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		rdBeforeCloseAction.addActionParameter("reportingDashboardTab_reportingDashboardComparisonConfig");

		GtnUIFrameWorkActionConfig rdCloseAction = new GtnUIFrameWorkActionConfig();
		rdCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		rdCloseAction.addActionParameter("dashboardcomparisonLookupView");
		rdBeforeCloseAction.addActionParameter(rdCloseAction);
		actionConfigList.add(rdBeforeCloseAction);

		closeBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig removeBtn = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "removeButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		removeBtn.setAuthorizationIncluded(true);
		removeBtn.setComponentName("REMOVE");
		componentList.add(removeBtn);

		GtnUIFrameWorkActionConfig removeAction = new GtnUIFrameWorkActionConfig();
		removeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeAction.addActionParameter(GtnReportComparisonProjectionRemoveAction.class.getName());
		removeAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		removeAction
				.addActionParameter(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
		removeBtn.addGtnUIFrameWorkActionConfig(removeAction);
	}

	private GtnUIFrameWorkActionConfig rdResetButtonAction(boolean gridResetNeeded) {
		GtnUIFrameWorkActionConfig rdResetActionConfig = new GtnUIFrameWorkActionConfig();
		rdResetActionConfig.setActionType(GtnUIFrameworkActionType.V8_CONFIRMED_RESET_ACTION);
		List<Object> params = new ArrayList<>();
		List<Object> rdResetComponentId = new ArrayList<>();
		List<Object> rdResetComponentValue = new ArrayList<>();
		if (gridResetNeeded) {
			rdResetComponentId.add(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
			rdResetComponentId
					.add(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
			rdResetComponentValue.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			rdResetComponentValue.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		} else {
			rdResetComponentId.addAll(Arrays.asList("dashboardComparisonLookup_projectionType",
					"dashboardComparisonLookup_workflowStatus",
					"dashboardComparisonLookup_reportComparisonLookupMarketType",
					"dashboardComparisonLookup_reportComparisonLookupBrand", "dashboardComparisonLookup_projectionName",
					"dashboardComparisonLookup_reportComparisonLookupContractHolder",
					"dashboardComparisonLookup_ndcConfig", "dashboardComparisonLookup_projectionDescription",
					"dashboardComparisonLookup_reportComparisonContract",
					"dashboardComparisonLookup_reportComparisonNdcName", "dashboardComparisonLookup_fromPeriod",
					"dashboardComparisonLookup_toPeriod"));
			rdResetComponentValue.addAll(Arrays.asList(0, 0, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null));
		}
		params.add(rdResetComponentId);
		params.add(rdResetComponentValue);
		rdResetActionConfig.setActionParameterList(params);
		return rdResetActionConfig;
	}

}
