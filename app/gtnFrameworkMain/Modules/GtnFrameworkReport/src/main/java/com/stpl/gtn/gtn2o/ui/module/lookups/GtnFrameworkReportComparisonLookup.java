/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkReportComparisonLookup {

	private final GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getReportComparisonLookupView() {
		GtnUIFrameworkViewConfig comparisonLookupRootView = configProvider.getViewConfig("Comparison Lookup",
				"comparisonLookupView", false);
		addComponentList(comparisonLookupRootView);
		return comparisonLookupRootView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);

		addProjectionSearchPanel(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addWorkflowStatusNameComponents(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addProjectionNameComponents(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addDescriptionComponents(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addCreatedDate(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addControlButtonLayout(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addResultsMainLayout(componentList);
		addResultPanel(componentList);
		addResultsButtonLayout(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addRuleDetailsPanel(componentList);
		addActionButtonLayout(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);

	}

	private void addResultsMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig resultsMainLayout = configProvider.getPanelConfig("resultsMainLayout", false,
				null);
		componentList.add(resultsMainLayout);

		GtnUIFrameworkComponentConfig resultsMainLayoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkReportStringConstants.RESULTS_MAIN_LAYOUT_CONFIG, true, "resultsMainLayout");
		resultsMainLayoutConfig.setComponentWidth("100%");

		List<String> resultsMainLayoutStyleList = new ArrayList<>();
		resultsMainLayoutStyleList.add(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		resultsMainLayoutStyleList.add(GtnFrameworkCssConstants.NO_MARGIN);

		resultsMainLayoutConfig.setComponentStyle(resultsMainLayoutStyleList);

		componentList.add(resultsMainLayoutConfig);

	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultPanel = configProvider.getPanelConfig("resultPanel", true,
				GtnFrameworkReportStringConstants.RESULTS_MAIN_LAYOUT_CONFIG);
		resultPanel.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		resultPanel.setAuthorizationIncluded(true);
		resultPanel.setComponentWidth("100%");
		componentList.add(resultPanel);
		addPagedTableComponent(componentList, resultPanel.getComponentId());

	}

	private void addProjectionSearchPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig projectionSearchOptionsCriteriaPanel = new GtnUIFrameworkComponentConfig();
		projectionSearchOptionsCriteriaPanel.setComponentName("Projection Search");
		projectionSearchOptionsCriteriaPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionOptions");
		projectionSearchOptionsCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionSearchOptionsCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionSearchOptionsCriteriaPanel.setSpacing(true);
		componentList.add(projectionSearchOptionsCriteriaPanel);

		GtnUIFrameworkLayoutConfig projectionSearchOptionMainLayout = new GtnUIFrameworkLayoutConfig();
		projectionSearchOptionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		projectionSearchOptionMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig projectionOptionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionMainLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		projectionOptionMainLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionMainLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionMainLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionOptions");
		projectionOptionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionOptionMainLayoutConfig.setGtnLayoutConfig(projectionSearchOptionMainLayout);
		componentList.add(projectionOptionMainLayoutConfig);

		GtnUIFrameworkLayoutConfig projectionSearchOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		projectionSearchOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig projectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		projectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		projectionOptionInnerLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		projectionOptionInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.setSpacing(Boolean.TRUE);
		projectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		projectionOptionInnerLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		projectionOptionInnerLayoutConfig.setGtnLayoutConfig(projectionSearchOptionInnerLayout);
		componentList.add(projectionOptionInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig reportComparisonLookupModeSelectionLayout = new GtnUIFrameworkLayoutConfig();
		reportComparisonLookupModeSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportComparisonLookupModeSelectionConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupModeSelectionConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonLookupModeSelectionConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupModeSelectionLayout");
		reportComparisonLookupModeSelectionConfig.setAddToParent(Boolean.TRUE);
		reportComparisonLookupModeSelectionConfig.setGtnLayoutConfig(reportComparisonLookupModeSelectionLayout);
		reportComparisonLookupModeSelectionConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		reportComparisonLookupModeSelectionConfig
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(reportComparisonLookupModeSelectionConfig);

		GtnUIFrameworkLayoutConfig reportComparisonLookupFromToMainLayout = new GtnUIFrameworkLayoutConfig();
		reportComparisonLookupFromToMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportComparisonLookupFromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupFromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonLookupFromToLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromToMainLayout");
		reportComparisonLookupFromToLayoutConfig.setAddToParent(Boolean.TRUE);
		reportComparisonLookupFromToLayoutConfig.setSpacing(Boolean.TRUE);
		reportComparisonLookupFromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		reportComparisonLookupFromToLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		reportComparisonLookupFromToLayoutConfig.setGtnLayoutConfig(reportComparisonLookupFromToMainLayout);
		componentList.add(reportComparisonLookupFromToLayoutConfig);

		GtnUIFrameworkLayoutConfig privateViewCompanyProjectionNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		privateViewCompanyProjectionNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewCompanyProjectionNameLayout = new GtnUIFrameworkComponentConfig();
		privateViewCompanyProjectionNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewCompanyProjectionNameLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		privateViewCompanyProjectionNameLayout.setAddToParent(Boolean.TRUE);
		privateViewCompanyProjectionNameLayout.setGtnLayoutConfig(privateViewCompanyProjectionNameLayoutConfig);
		privateViewCompanyProjectionNameLayout
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		privateViewCompanyProjectionNameLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(privateViewCompanyProjectionNameLayout);

	}

	private void addWorkflowStatusNameComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig workflowStatusNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		publicViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		publicViewsLayoutConfig.setSpacing(Boolean.TRUE);
		publicViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsLayoutConfig.setGtnLayoutConfig(workflowStatusNameLayout);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig workflowStatus = new GtnUIFrameworkComponentConfig();
		workflowStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		workflowStatus.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS);
		workflowStatus.setComponentName("Workflow Status: ");
		workflowStatus.setAddToParent(Boolean.TRUE);
		workflowStatus.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		GtnUIFrameworkComboBoxConfig workflowStatusConfig = new GtnUIFrameworkComboBoxConfig();
		workflowStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		workflowStatusConfig.setComboBoxType(GtnFrameworkReportStringConstants.BUSINESS_UNIT_GLCOMP);
		workflowStatus.setGtnComboboxConfig(workflowStatusConfig);

		componentList.add(workflowStatus);

		GtnUIFrameworkLayoutConfig reportComparisonLookupBusinessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonLookupBusinessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupBusinessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonLookupBusinessUnitLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.BUSINESS_UNIT_LAYOUT);
		reportComparisonLookupBusinessUnitLayoutConfig.setAddToParent(Boolean.TRUE);
		reportComparisonLookupBusinessUnitLayoutConfig.setSpacing(Boolean.TRUE);
		reportComparisonLookupBusinessUnitLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonLookupBusinessUnitLayoutConfig.setGtnLayoutConfig(reportComparisonLookupBusinessUnitLayout);
		componentList.add(reportComparisonLookupBusinessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig reportComparisonLookupMarketType = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupMarketType.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		reportComparisonLookupMarketType.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupMarketType");
		reportComparisonLookupMarketType.setComponentName("Market Type: ");
		reportComparisonLookupMarketType.setAddToParent(Boolean.TRUE);
		reportComparisonLookupMarketType.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.BUSINESS_UNIT_LAYOUT);
		componentList.add(reportComparisonLookupMarketType);
		GtnUIFrameworkLayoutConfig reportComparisonLookupBrandLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonLookupBrandLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupBrandLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonLookupBrandLayoutConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrandLayoutConfig");
		reportComparisonLookupBrandLayoutConfig.setAddToParent(Boolean.TRUE);
		reportComparisonLookupBrandLayoutConfig.setSpacing(Boolean.TRUE);
		reportComparisonLookupBrandLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonLookupBrandLayoutConfig.setGtnLayoutConfig(reportComparisonLookupBrandLayout);
		componentList.add(reportComparisonLookupBrandLayoutConfig);

		GtnUIFrameworkComponentConfig reportComparisonLookupBrand = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupBrand.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		reportComparisonLookupBrand.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrand");
		reportComparisonLookupBrand.setComponentName("Brand: ");
		reportComparisonLookupBrand.setAddToParent(Boolean.TRUE);
		reportComparisonLookupBrand.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrandLayoutConfig");
		componentList.add(reportComparisonLookupBrand);
	}

	private void addProjectionNameComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig projectionNameComponent = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		publicViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		publicViewsLayoutConfig.setSpacing(Boolean.TRUE);
		publicViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsLayoutConfig.setGtnLayoutConfig(projectionNameComponent);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		projectionName.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS);
		projectionName.setComponentName("Projection Name: ");
		projectionName.setAddToParent(Boolean.TRUE);
		projectionName.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);

		componentList.add(projectionName);

		GtnUIFrameworkLayoutConfig reportComparisonLookupContractHolderLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonLookupContractHolderConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupContractHolderConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonLookupContractHolderConfig.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolderConfig");
		reportComparisonLookupContractHolderConfig.setAddToParent(Boolean.TRUE);
		reportComparisonLookupContractHolderConfig.setSpacing(Boolean.TRUE);
		reportComparisonLookupContractHolderConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonLookupContractHolderConfig.setGtnLayoutConfig(reportComparisonLookupContractHolderLayout);
		componentList.add(reportComparisonLookupContractHolderConfig);

		GtnUIFrameworkComponentConfig reportComparisonLookupContractHolder = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupContractHolder.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		reportComparisonLookupContractHolder.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolder");
		reportComparisonLookupContractHolder.setComponentName("Contract Holder: ");
		reportComparisonLookupContractHolder.setAddToParent(Boolean.TRUE);
		reportComparisonLookupContractHolder.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolderConfig");
		componentList.add(reportComparisonLookupContractHolder);

		GtnUIFrameworkLayoutConfig ndcLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig ndcLayoutConfig = new GtnUIFrameworkComponentConfig();
		ndcLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		ndcLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_LAYOUT);
		ndcLayoutConfig.setAddToParent(Boolean.TRUE);
		ndcLayoutConfig.setSpacing(Boolean.TRUE);
		ndcLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		ndcLayoutConfig.setGtnLayoutConfig(ndcLayout);
		componentList.add(ndcLayoutConfig);

		GtnUIFrameworkComponentConfig ndcConfig = new GtnUIFrameworkComponentConfig();
		ndcConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		ndcConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_CONFIG);
		ndcConfig.setComponentName("NDC: ");
		ndcConfig.setAddToParent(Boolean.TRUE);
		ndcConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_LAYOUT);
		componentList.add(ndcConfig);

	}

	private void addDescriptionComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig descriptionLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		publicViewsLayoutConfig.setAddToParent(Boolean.TRUE);
		publicViewsLayoutConfig.setSpacing(Boolean.TRUE);
		publicViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsLayoutConfig.setGtnLayoutConfig(descriptionLayout);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig description = new GtnUIFrameworkComponentConfig();
		description.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		description.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PUBLIC_VIEWS);
		description.setComponentName("Description: ");
		description.setAddToParent(Boolean.TRUE);
		description.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);

		componentList.add(description);

		GtnUIFrameworkLayoutConfig reportComparisonContractLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonContractConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonContractConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonContractConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContractConfig");
		reportComparisonContractConfig.setAddToParent(Boolean.TRUE);
		reportComparisonContractConfig.setSpacing(Boolean.TRUE);
		reportComparisonContractConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonContractConfig.setGtnLayoutConfig(reportComparisonContractLayout);
		componentList.add(reportComparisonContractConfig);

		GtnUIFrameworkComponentConfig reportComparisonContract = new GtnUIFrameworkComponentConfig();
		reportComparisonContract.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		reportComparisonContract
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContract");
		reportComparisonContract.setComponentName("Contract: ");
		reportComparisonContract.setAddToParent(Boolean.TRUE);
		reportComparisonContract.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContractConfig");
		componentList.add(reportComparisonContract);

		GtnUIFrameworkLayoutConfig reportComparisonNdcNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonNdcNameConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonNdcNameConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonNdcNameConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcNameConfig");
		reportComparisonNdcNameConfig.setAddToParent(Boolean.TRUE);
		reportComparisonNdcNameConfig.setSpacing(Boolean.TRUE);
		reportComparisonNdcNameConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonNdcNameConfig.setGtnLayoutConfig(reportComparisonNdcNameLayout);
		componentList.add(reportComparisonNdcNameConfig);

		GtnUIFrameworkComponentConfig reportComparisonNdcName = new GtnUIFrameworkComponentConfig();
		reportComparisonNdcName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		reportComparisonNdcName
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcName");
		reportComparisonNdcName.setComponentName("NDC Name: ");
		reportComparisonNdcName.setAddToParent(Boolean.TRUE);
		reportComparisonNdcName.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcNameConfig");
		componentList.add(reportComparisonNdcName);

	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Created Date");
		panel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(Boolean.TRUE);
		panel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromToMainLayout");
		panel.addComponentStyle(GtnFrameworkCssConstants.PANEL_TIME_PERIOD_RTN_FOR);
		componentList.add(panel);

		GtnUIFrameworkLayoutConfig comparisonLookuptimePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		comparisonLookuptimePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig comparisonFromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		comparisonFromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonFromAndToperiodLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		comparisonFromAndToperiodLayoutConfig.setSpacing(Boolean.TRUE);
		comparisonFromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonFromAndToperiodLayoutConfig.setAddToParent(Boolean.TRUE);
		comparisonFromAndToperiodLayoutConfig.setGtnLayoutConfig(comparisonLookuptimePeriodInnerLayout);
		comparisonFromAndToperiodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		comparisonFromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(comparisonFromAndToperiodLayoutConfig);

		GtnUIFrameworkLayoutConfig comparisonLookupFromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		comparisonLookupFromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig comparisonLookupFromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		comparisonLookupFromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonLookupFromPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		comparisonLookupFromPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		comparisonLookupFromPeriodLayoutConfig.setGtnLayoutConfig(comparisonLookupFromPeriodLayout);
		comparisonLookupFromPeriodLayoutConfig.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		componentList.add(comparisonLookupFromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		fromPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD);
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(Boolean.TRUE);

		fromPeriod.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");

		componentList.add(fromPeriod);

		GtnUIFrameworkLayoutConfig comparisonLookupToPeriodLayout = new GtnUIFrameworkLayoutConfig();
		comparisonLookupToPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig comparisonLookupToPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		comparisonLookupToPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonLookupToPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");
		comparisonLookupToPeriodLayoutConfig.setAddToParent(Boolean.TRUE);
		comparisonLookupToPeriodLayoutConfig.setGtnLayoutConfig(comparisonLookupToPeriodLayout);
		comparisonLookupToPeriodLayoutConfig.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		componentList.add(comparisonLookupToPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig comparisonLookupToPeriod = new GtnUIFrameworkComponentConfig();
		comparisonLookupToPeriod.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		comparisonLookupToPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD);
		comparisonLookupToPeriod.setComponentName("To ");
		comparisonLookupToPeriod.setAddToParent(Boolean.TRUE);
		comparisonLookupToPeriod
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");

		componentList.add(comparisonLookupToPeriod);
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig controlButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkReportStringConstants.CONTROL_BUTTON_LAYOUT, false, null);
		controlButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(controlButtonLayout);
		addSearchButtonComponent(componentList, nameSpace);
		addResetButtonComponent(componentList, nameSpace);
	}

	private void addResultsButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig resultsButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkReportStringConstants.CONTROL_BUTTON_LAYOUT, false, null);
		resultsButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(resultsButtonLayout);
		addAddButtonComponent(componentList, nameSpace);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "searchButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("SEARCH");
		componentList.add(searchButtonConfig);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig addButtonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "addButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentName("ADD");
		addButtonConfig.setAuthorizationIncluded(true);
		componentList.add(addButtonConfig);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig comparisonLookupResultsPagedTableComponent = new GtnUIFrameworkComponentConfig();
		comparisonLookupResultsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		comparisonLookupResultsPagedTableComponent.setComponentId("comparisonLookupResultsPagedTableComponent");
		comparisonLookupResultsPagedTableComponent.setComponentName("Results");
		comparisonLookupResultsPagedTableComponent.setParentComponentId(parentId);
		comparisonLookupResultsPagedTableComponent.setAddToParent(true);

		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		comparisonLookupResultsPagedTableComponent.setComponentWidth("100%");
		comparisonLookupResultsPagedTableComponent.setComponentStyle(tableStyle);

		componentList.add(comparisonLookupResultsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig comparisonLookupResultsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		comparisonLookupResultsPagedTableConfig.setEditable(false);
		comparisonLookupResultsPagedTableConfig.setFilterBar(true);
		comparisonLookupResultsPagedTableConfig.setPageLength(10);
		comparisonLookupResultsPagedTableConfig.setItemPerPage(10);
		comparisonLookupResultsPagedTableConfig.setSelectable(true);
		comparisonLookupResultsPagedTableConfig.setSinkItemPerPageWithPageLength(false);

		comparisonLookupResultsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		comparisonLookupResultsPagedTableConfig.setTableVisibleHeader(new String[] { "Projection Name", "Description",
				"Market Type", "Contract Holder", "Contract", "Brand" });
		comparisonLookupResultsPagedTableConfig.setTableColumnMappingId(
				new Object[] { "projectionName", "description", "marketType", "contractHolder", "contract", "brand" });

		comparisonLookupResultsPagedTableComponent.setGtnPagedTableConfig(comparisonLookupResultsPagedTableConfig);
	}

	private void addRuleDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpRuleDetailsResultPanel = configProvider
				.getPanelConfig("ruleDetailsResultPanel", false, null);
		cdrPopUpRuleDetailsResultPanel.setComponentWidth("100%");
		cdrPopUpRuleDetailsResultPanel.setComponentName("Rule Details");
		cdrPopUpRuleDetailsResultPanel.setAuthorizationIncluded(true);
		componentList.add(cdrPopUpRuleDetailsResultPanel);
		ruleDetailsResultDataTable(componentList, cdrPopUpRuleDetailsResultPanel.getComponentId());
	}

	private void ruleDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig comparisonLookupProjectionsPagedTableComponent = new GtnUIFrameworkComponentConfig();
		comparisonLookupProjectionsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		comparisonLookupProjectionsPagedTableComponent.setComponentId("comparisonLookupProjectionsPagedTableComponent");
		comparisonLookupProjectionsPagedTableComponent.setComponentName("Projections");
		comparisonLookupProjectionsPagedTableComponent.setParentComponentId(parentId);
		comparisonLookupProjectionsPagedTableComponent.setAddToParent(true);

		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		comparisonLookupProjectionsPagedTableComponent.setComponentWidth("100%");
		comparisonLookupProjectionsPagedTableComponent.setComponentStyle(tableStyle);

		componentList.add(comparisonLookupProjectionsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig comparisonLookupProjectionsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		comparisonLookupProjectionsPagedTableConfig.setEditable(false);
		comparisonLookupProjectionsPagedTableConfig.setFilterBar(true);
		comparisonLookupProjectionsPagedTableConfig.setPageLength(10);
		comparisonLookupProjectionsPagedTableConfig.setItemPerPage(10);
		comparisonLookupProjectionsPagedTableConfig.setSelectable(true);
		comparisonLookupProjectionsPagedTableConfig.setSinkItemPerPageWithPageLength(false);

		comparisonLookupProjectionsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		comparisonLookupProjectionsPagedTableConfig.setTableVisibleHeader(new String[] { "Projection Name",
				"Description", "Market Type", "Contract Holder", "Contract", "Brand" });
		comparisonLookupProjectionsPagedTableConfig.setTableColumnMappingId(
				new Object[] { "projectionName", "description", "marketType", "contractHolder", "contract", "brand" });

		comparisonLookupProjectionsPagedTableComponent
				.setGtnPagedTableConfig(comparisonLookupProjectionsPagedTableConfig);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig actionButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkReportStringConstants.ACTION_BUTTON_LAYOUT, false, null);
		actionButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(actionButtonLayout);
		addSubmitButtonComponent(componentList, nameSpace);
		addRestButtonComponent(componentList, nameSpace);
		addCloseButtonComponent(componentList, nameSpace);
		addRemoveButtonComponent(componentList, nameSpace);
	}

	private void addSubmitButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig submitButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "submitButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		submitButton.setAuthorizationIncluded(true);
		submitButton.setComponentName("SUBMIT");
		componentList.add(submitButton);
	}

	private void addRestButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig resetButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		resetButton.setAuthorizationIncluded(true);
		resetButton.setComponentName("RESET");
		componentList.add(resetButton);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig closeButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "closeButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		closeButton.setComponentName("CLOSE");
		closeButton.setAuthorizationIncluded(true);
		componentList.add(closeButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.CDR_POP_UP_SEARCH_SEARCH_VIEW);
		actionConfigList.add(closeAction);

		closeButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig removeButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "removeButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		removeButton.setAuthorizationIncluded(true);
		removeButton.setComponentName("REMOVE");
		componentList.add(removeButton);
	}

}
