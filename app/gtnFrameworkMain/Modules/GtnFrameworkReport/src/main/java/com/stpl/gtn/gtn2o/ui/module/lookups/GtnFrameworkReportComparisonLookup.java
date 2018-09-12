/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportComparisonEnableAddBtnAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportComparisonLookup {

	private final GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	private static final String[] columnPropertyIds = { GtnFrameworkReportStringConstants.PROJECTION_NAME,
			GtnFrameworkReportStringConstants.DESCRIPTION2, GtnFrameworkReportStringConstants.MARKET_TYPE,
			GtnFrameworkReportStringConstants.CONTRACT_HOLDER, GtnFrameworkReportStringConstants.CONTRACT,
			GtnFrameworkReportStringConstants.BRAND, GtnFrameworkReportStringConstants.CREATED_DATE2,
			GtnFrameworkReportStringConstants.CREATED_BY };
	private static final GtnUIFrameworkComponentType[] comparisonLookupComponentType = {
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

	public GtnUIFrameworkViewConfig getReportComparisonLookupView() {
		GtnUIFrameworkViewConfig comparisonLookupRootView = configProvider.getViewConfig("Comparison Lookup",
				GtnFrameworkReportStringConstants.REPORT_COMPARISON_LOOKUP_VIEW, false);
		comparisonLookupRootView.setResetAllowed(true);
		addComponentList(comparisonLookupRootView);
		return comparisonLookupRootView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);

		addProjectionType(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
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

	private void addProjectionType(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig projectionTypeLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionTypeLayout", false, null);
		projectionTypeLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		projectionTypeLayoutConfig.addComponentStyle("stpl-margin-top-15");
		projectionTypeLayoutConfig.addComponentStyle("stpl-margin-bottom-15");
		componentList.add(projectionTypeLayoutConfig);

		GtnUIFrameworkComponentConfig projectionType = new GtnUIFrameworkComponentConfig();
		projectionType.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		projectionType.setComponentName("Projection Type");
		projectionType.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PROJECTION_TYPE);
		projectionType.setAddToParent(true);
		projectionType.setCustomReference("integerId");
		projectionType.setParentComponentId(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionTypeLayout");
		projectionType.setDefaultFocus(true);
		projectionType.setComponentWsFieldId("projectionType");

		GtnUIFrameworkComboBoxConfig projectionTypeConfig = new GtnUIFrameworkComboBoxConfig();
		projectionTypeConfig.setItemValues(Arrays.asList("F", "C"));
		projectionTypeConfig.setItemCaptionValues(
				GtnFrameworkReportStringConstants.getReportComparisonProjectionTypeLoadCombobox());
		projectionType.setGtnComboboxConfig(projectionTypeConfig);

		GtnUIFrameworkValidationConfig projectionTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		projectionTypeValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		projectionType.setGtnUIFrameworkValidationConfig(projectionTypeValidationConfig);
		componentList.add(projectionType);
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
		projectionOptionMainLayoutConfig.setAddToParent(true);
		projectionOptionMainLayoutConfig.setSpacing(true);
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
		projectionOptionInnerLayoutConfig.setAddToParent(true);
		projectionOptionInnerLayoutConfig.setSpacing(true);
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
		reportComparisonLookupModeSelectionConfig.setAddToParent(true);
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
		reportComparisonLookupFromToLayoutConfig.setAddToParent(true);
		reportComparisonLookupFromToLayoutConfig.setSpacing(true);
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
		privateViewCompanyProjectionNameLayout.setAddToParent(true);
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
		publicViewsLayoutConfig.setAddToParent(true);
		publicViewsLayoutConfig.setSpacing(true);
		publicViewsLayoutConfig.addComponentStyle("stpl-margin-top-15");
		publicViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsLayoutConfig.setGtnLayoutConfig(workflowStatusNameLayout);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig workflowStatus = new GtnUIFrameworkComponentConfig();
		workflowStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		workflowStatus.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.WORKFLOW_STATUS);
		workflowStatus.setComponentName("Workflow Status: ");
		workflowStatus.setCustomReference("integerId");
		workflowStatus.setAddToParent(true);
		workflowStatus.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		workflowStatus.setComponentWsFieldId("workflowStatus");

		GtnUIFrameworkComboBoxConfig workflowStatusConfig = new GtnUIFrameworkComboBoxConfig();
		workflowStatusConfig
				.setItemValues(GtnFrameworkReportStringConstants.getReportComparisonWorkflowStatusLoadCombobox());
		workflowStatus.setGtnComboboxConfig(workflowStatusConfig);

		GtnUIFrameworkValidationConfig workflowStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		workflowStatusValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		workflowStatus.setGtnUIFrameworkValidationConfig(workflowStatusValidationConfig);

		componentList.add(workflowStatus);

		GtnUIFrameworkLayoutConfig reportComparisonLookupBusinessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonLookupBusinessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupBusinessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonLookupBusinessUnitLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.BUSINESS_UNIT_LAYOUT);
		reportComparisonLookupBusinessUnitLayoutConfig.setAddToParent(true);
		reportComparisonLookupBusinessUnitLayoutConfig.setSpacing(true);
		reportComparisonLookupBusinessUnitLayoutConfig
				.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportComparisonLookupBusinessUnitLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonLookupBusinessUnitLayoutConfig.setGtnLayoutConfig(reportComparisonLookupBusinessUnitLayout);
		componentList.add(reportComparisonLookupBusinessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig reportComparisonLookupMarketType = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupMarketType.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportComparisonLookupMarketType.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupMarketType");
		reportComparisonLookupMarketType.setComponentName("Market Type: ");
		reportComparisonLookupMarketType.setAddToParent(true);
		reportComparisonLookupMarketType.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.BUSINESS_UNIT_LAYOUT);
		reportComparisonLookupMarketType.setComponentWsFieldId(GtnFrameworkReportStringConstants.MARKET_TYPE);

		componentList.add(reportComparisonLookupMarketType);
		GtnUIFrameworkLayoutConfig reportComparisonLookupBrandLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonLookupBrandLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupBrandLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonLookupBrandLayoutConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrandLayoutConfig");
		reportComparisonLookupBrandLayoutConfig.setAddToParent(true);
		reportComparisonLookupBrandLayoutConfig.setSpacing(true);
		reportComparisonLookupBrandLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportComparisonLookupBrandLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonLookupBrandLayoutConfig.setGtnLayoutConfig(reportComparisonLookupBrandLayout);
		componentList.add(reportComparisonLookupBrandLayoutConfig);

		GtnUIFrameworkComponentConfig reportComparisonLookupBrand = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupBrand.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportComparisonLookupBrand.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrand");
		reportComparisonLookupBrand.setComponentName("Brand: ");
		reportComparisonLookupBrand.setAddToParent(true);
		reportComparisonLookupBrand.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrandLayoutConfig");
		reportComparisonLookupBrand.setComponentWsFieldId(GtnFrameworkReportStringConstants.BRAND);
		componentList.add(reportComparisonLookupBrand);
	}

	private void addProjectionNameComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig projectionNameComponent = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		publicViewsLayoutConfig.setAddToParent(true);
		publicViewsLayoutConfig.setSpacing(true);
		publicViewsLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		publicViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsLayoutConfig.setGtnLayoutConfig(projectionNameComponent);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig projectionName = new GtnUIFrameworkComponentConfig();
		projectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		projectionName.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PROJECTION_NAME);
		projectionName.setComponentName("Projection Name: ");
		projectionName.setAddToParent(true);
		projectionName.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		projectionName.setComponentWsFieldId(GtnFrameworkReportStringConstants.PROJECTION_NAME);

		componentList.add(projectionName);

		GtnUIFrameworkLayoutConfig reportComparisonLookupContractHolderLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonLookupContractHolderConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupContractHolderConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonLookupContractHolderConfig.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolderConfig");
		reportComparisonLookupContractHolderConfig.setAddToParent(true);
		reportComparisonLookupContractHolderConfig.setSpacing(true);
		reportComparisonLookupContractHolderConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportComparisonLookupContractHolderConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonLookupContractHolderConfig.setGtnLayoutConfig(reportComparisonLookupContractHolderLayout);
		componentList.add(reportComparisonLookupContractHolderConfig);

		GtnUIFrameworkComponentConfig reportComparisonLookupContractHolder = new GtnUIFrameworkComponentConfig();
		reportComparisonLookupContractHolder.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportComparisonLookupContractHolder.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolder");
		reportComparisonLookupContractHolder.setComponentName("Contract Holder: ");
		reportComparisonLookupContractHolder.setAddToParent(true);
		reportComparisonLookupContractHolder.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolderConfig");
		reportComparisonLookupContractHolder.setComponentWsFieldId(GtnFrameworkReportStringConstants.CONTRACT_HOLDER);
		componentList.add(reportComparisonLookupContractHolder);

		GtnUIFrameworkLayoutConfig ndcLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig ndcLayoutConfig = new GtnUIFrameworkComponentConfig();
		ndcLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		ndcLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_LAYOUT);
		ndcLayoutConfig.setAddToParent(true);
		ndcLayoutConfig.setSpacing(true);
		ndcLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		ndcLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		ndcLayoutConfig.setGtnLayoutConfig(ndcLayout);
		componentList.add(ndcLayoutConfig);

		GtnUIFrameworkComponentConfig ndcConfig = new GtnUIFrameworkComponentConfig();
		ndcConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		ndcConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_CONFIG);
		ndcConfig.setComponentName("NDC #: ");
		ndcConfig.setAddToParent(true);
		ndcConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_LAYOUT);
		ndcConfig.setComponentWsFieldId("comparisonNDC");
		componentList.add(ndcConfig);

	}

	private void addDescriptionComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig descriptionLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		publicViewsLayoutConfig.setAddToParent(true);
		publicViewsLayoutConfig.setSpacing(true);
		publicViewsLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		publicViewsLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsLayoutConfig.setGtnLayoutConfig(descriptionLayout);
		componentList.add(publicViewsLayoutConfig);

		GtnUIFrameworkComponentConfig description = new GtnUIFrameworkComponentConfig();
		description.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		description.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionDescription");
		description.setComponentName("Description: ");
		description.setAddToParent(true);
		description.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		description.setComponentWsFieldId(GtnFrameworkReportStringConstants.DESCRIPTION2);

		componentList.add(description);

		GtnUIFrameworkLayoutConfig reportComparisonContractLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonContractConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonContractConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonContractConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContractConfig");
		reportComparisonContractConfig.setAddToParent(true);
		reportComparisonContractConfig.setSpacing(true);
		reportComparisonContractConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportComparisonContractConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonContractConfig.setGtnLayoutConfig(reportComparisonContractLayout);
		componentList.add(reportComparisonContractConfig);

		GtnUIFrameworkComponentConfig reportComparisonContract = new GtnUIFrameworkComponentConfig();
		reportComparisonContract.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportComparisonContract
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContract");
		reportComparisonContract.setComponentName("Contract: ");
		reportComparisonContract.setAddToParent(true);
		reportComparisonContract.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContractConfig");
		reportComparisonContract.setComponentWsFieldId(GtnFrameworkReportStringConstants.CONTRACT);
		componentList.add(reportComparisonContract);

		GtnUIFrameworkLayoutConfig reportComparisonNdcNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonNdcNameConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonNdcNameConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonNdcNameConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcNameConfig");
		reportComparisonNdcNameConfig.setAddToParent(true);
		reportComparisonNdcNameConfig.setSpacing(true);
		reportComparisonNdcNameConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportComparisonNdcNameConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonNdcNameConfig.setGtnLayoutConfig(reportComparisonNdcNameLayout);
		componentList.add(reportComparisonNdcNameConfig);

		GtnUIFrameworkComponentConfig reportComparisonNdcName = new GtnUIFrameworkComponentConfig();
		reportComparisonNdcName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportComparisonNdcName
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcName");
		reportComparisonNdcName.setComponentName("NDC Name: ");
		reportComparisonNdcName.setAddToParent(true);
		reportComparisonNdcName.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcNameConfig");
		reportComparisonNdcName.setComponentWsFieldId("ndcName");
		componentList.add(reportComparisonNdcName);

	}

	private void addCreatedDate(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName(GtnFrameworkReportStringConstants.CREATED_DATE);
		panel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth("65%");
		panel.setAddToParent(true);
		panel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromToMainLayout");
		panel.addComponentStyle(GtnFrameworkCssConstants.PANEL_TIME_PERIOD_RTN_FOR);
		panel.addComponentStyle("stpl-margin-bottom-14");
		componentList.add(panel);

		GtnUIFrameworkLayoutConfig comparisonLookuptimePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		comparisonLookuptimePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig comparisonFromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		comparisonFromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonFromAndToperiodLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		comparisonFromAndToperiodLayoutConfig.setSpacing(true);
		comparisonFromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonFromAndToperiodLayoutConfig.setAddToParent(true);
		comparisonFromAndToperiodLayoutConfig.setGtnLayoutConfig(comparisonLookuptimePeriodInnerLayout);
		comparisonFromAndToperiodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		comparisonFromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(comparisonFromAndToperiodLayoutConfig);

		GtnUIFrameworkComponentConfig comparisonFromAndToperiodVerticalLayoutConfig = configProvider
				.getVerticalLayoutConfig(
						GtnFrameworkReportStringConstants.COMPARISON_FROM_AND_TOPERIOD_VERTICAL_LAYOUT_CONFIG, true,
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		comparisonFromAndToperiodVerticalLayoutConfig.setComponentWidth("100%");
		comparisonFromAndToperiodVerticalLayoutConfig.setComponentHight("85px");
		componentList.add(comparisonFromAndToperiodVerticalLayoutConfig);

		GtnUIFrameworkLayoutConfig comparisonLookupFromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		comparisonLookupFromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig comparisonLookupFromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		comparisonLookupFromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonLookupFromPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		comparisonLookupFromPeriodLayoutConfig.setAddToParent(true);
		comparisonLookupFromPeriodLayoutConfig.setGtnLayoutConfig(comparisonLookupFromPeriodLayout);
		comparisonLookupFromPeriodLayoutConfig.setParentComponentId(
				GtnFrameworkReportStringConstants.COMPARISON_FROM_AND_TOPERIOD_VERTICAL_LAYOUT_CONFIG);
		componentList.add(comparisonLookupFromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig fromPeriod = new GtnUIFrameworkComponentConfig();
		fromPeriod.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		fromPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD);
		fromPeriod.setComponentName("From ");
		fromPeriod.setAddToParent(true);
		fromPeriod.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		fromPeriod.setComponentWsFieldId("fromPeriod");

		componentList.add(fromPeriod);

		GtnUIFrameworkLayoutConfig comparisonLookupToPeriodLayout = new GtnUIFrameworkLayoutConfig();
		comparisonLookupToPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig comparisonLookupToPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		comparisonLookupToPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonLookupToPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");
		comparisonLookupToPeriodLayoutConfig.setAddToParent(true);
		comparisonLookupToPeriodLayoutConfig.setGtnLayoutConfig(comparisonLookupToPeriodLayout);
		comparisonLookupToPeriodLayoutConfig.setParentComponentId(
				GtnFrameworkReportStringConstants.COMPARISON_FROM_AND_TOPERIOD_VERTICAL_LAYOUT_CONFIG);
		componentList.add(comparisonLookupToPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig comparisonLookupToPeriod = new GtnUIFrameworkComponentConfig();
		comparisonLookupToPeriod.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		comparisonLookupToPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD);
		comparisonLookupToPeriod.setComponentName("To ");
		comparisonLookupToPeriod.setAddToParent(true);
		comparisonLookupToPeriod
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");
		comparisonLookupToPeriod.setComponentWsFieldId("toPeriod");

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

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig projectionTypeValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		projectionTypeValidationActionConfig.setFieldValues(Arrays.asList(nameSpace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.PROJECTION_TYPE));

		GtnUIFrameWorkActionConfig projectionTypeAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> projectionTypeAlertParams = new ArrayList<>();
		projectionTypeAlertParams.add(GtnFrameworkCommonConstants.ERROR);
		projectionTypeAlertParams.add("Please select a Projection Type");
		projectionTypeAlertActionConfig.setActionParameterList(projectionTypeAlertParams);

		projectionTypeValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(projectionTypeAlertActionConfig)));
		searchActionConfigList.add(projectionTypeValidationActionConfig);

		GtnUIFrameWorkActionConfig workflowStatusValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		workflowStatusValidationActionConfig.setFieldValues(Arrays.asList(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PROJECTION_TYPE,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.WORKFLOW_STATUS));

		GtnUIFrameWorkActionConfig workFlowStatusAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkCommonConstants.ERROR);
		alertParams.add("Please select a Workflow Status");
		workFlowStatusAlertActionConfig.setActionParameterList(alertParams);

		workflowStatusValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(workFlowStatusAlertActionConfig)));
		searchActionConfigList.add(workflowStatusValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataGridAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataGridAction.setActionParameterList(
				Arrays.asList(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT));
		loadDataGridAction.setFieldValues(Arrays.asList("reportLandingScreen_displaySelectionTabCustomView",
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
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD));
		searchActionConfigList.add(loadDataGridAction);

		GtnUIFrameWorkActionConfig enableAddAction = new GtnUIFrameWorkActionConfig();
		enableAddAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		enableAddAction.addActionParameter(GtnReportComparisonEnableAddBtnAction.class.getName());
		enableAddAction
				.addActionParameter(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
		enableAddAction
				.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "addButtonConfig");
		searchActionConfigList.add(enableAddAction);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		GtnUIFrameWorkActionConfig searchConfirmResetAction = new GtnUIFrameWorkActionConfig();
		searchConfirmResetAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		searchConfirmResetAction.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		searchConfirmResetAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_SEARCH_PANEL_RESET_CONFIRMATION_MESSAGE);
		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		resetActionConfigList.add(resetButtonAction(false));
		searchConfirmResetAction.addActionParameter(resetActionConfigList);
		resetButtonConfig.addGtnUIFrameWorkActionConfig(searchConfirmResetAction);
		componentList.add(resetButtonConfig);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig addButtonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "addButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentName("ADD");
		addButtonConfig.setAuthorizationIncluded(true);
		addButtonConfig.setEnable(false);
		componentList.add(addButtonConfig);

		GtnUIFrameWorkActionConfig addProjectionAction = new GtnUIFrameWorkActionConfig();
		addProjectionAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addProjectionAction.addActionParameter(GtnReportComparisonProjectionAddAction.class.getName());
		addProjectionAction
				.addActionParameter(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
		addProjectionAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		addProjectionAction.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PROJECTION_TYPE);
		addButtonConfig.addGtnUIFrameWorkActionConfig(addProjectionAction);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig comparisonLookupResultsPagedTableComponent = new GtnUIFrameworkComponentConfig();
		comparisonLookupResultsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		comparisonLookupResultsPagedTableComponent
				.setComponentId(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
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
		comparisonLookupResultsPagedTableConfig.setItemsPerPageAlignCentre(false);

		comparisonLookupResultsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		comparisonLookupResultsPagedTableConfig
				.setColumnHeaders(Arrays.asList("Projection Name", "Description", "Market Type", "Contract Holder",
						"Contract", "Brand", GtnFrameworkReportStringConstants.CREATED_DATE, "Created By"));
		comparisonLookupResultsPagedTableConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkReportStringConstants.PROJECTION_NAME, GtnFrameworkReportStringConstants.DESCRIPTION2,
				GtnFrameworkReportStringConstants.MARKET_TYPE, GtnFrameworkReportStringConstants.CONTRACT_HOLDER,
				GtnFrameworkReportStringConstants.CONTRACT, GtnFrameworkReportStringConstants.BRAND,
				GtnFrameworkReportStringConstants.CREATED_DATE2, GtnFrameworkReportStringConstants.CREATED_BY });
		comparisonLookupResultsPagedTableConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_COMPARISONLOOKUP_AVAILABLETABLE_COUNTSERVICE);
		comparisonLookupResultsPagedTableConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_COMPARISONLOOKUP_AVAILABLETABLE_LOADSERVICE);
		comparisonLookupResultsPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		comparisonLookupResultsPagedTableComponent.setModuleName("report");

		comparisonLookupResultsPagedTableComponent.setGtnPagedTableConfig(comparisonLookupResultsPagedTableConfig);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> comparisonLookupCustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		String[] comparisonLookupComboboxIds = new String[1];
		String[] comparisonLookupComboBoxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig comparisonLookupFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			comparisonLookupFilterConfig.setPropertId(columnPropertyIds[i]);
			comparisonLookupFilterConfig.setGtnComponentType(comparisonLookupComponentType[i]);
			if ((startIndex < comparisonLookupComboboxIds.length)
					&& columnPropertyIds[i].equals(comparisonLookupComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig comparisonLookupSearchFilterConfig = new GtnUIFrameworkComponentConfig();
				comparisonLookupSearchFilterConfig.setComponentId("customFilterComboBox");
				comparisonLookupSearchFilterConfig.setComponentName("customFilterComboBox");
				comparisonLookupSearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				comparisonLookupSearchFilterConfig.getGtnComboboxConfig()
						.setComboBoxType(comparisonLookupComboBoxType[startIndex]);
				comparisonLookupSearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				comparisonLookupFilterConfig.setGtnComponentConfig(comparisonLookupSearchFilterConfig);
				startIndex++;
			}
			comparisonLookupCustomFilterConfigMap.put(comparisonLookupFilterConfig.getPropertId(),
					comparisonLookupFilterConfig);
		}
		return comparisonLookupCustomFilterConfigMap;
	}

	private void addRuleDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpRuleDetailsResultPanel = configProvider
				.getPanelConfig("ruleDetailsResultPanel", false, null);
		cdrPopUpRuleDetailsResultPanel.setComponentWidth("100%");
		cdrPopUpRuleDetailsResultPanel.setComponentName("Projections");
		cdrPopUpRuleDetailsResultPanel.setAuthorizationIncluded(true);
		componentList.add(cdrPopUpRuleDetailsResultPanel);
		ruleDetailsResultDataTable(componentList, cdrPopUpRuleDetailsResultPanel.getComponentId());
	}

	private void ruleDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig comparisonLookupProjectionsPagedTableComponent = new GtnUIFrameworkComponentConfig();
		comparisonLookupProjectionsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		comparisonLookupProjectionsPagedTableComponent.setComponentId(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		comparisonLookupProjectionsPagedTableComponent.setComponentName("Projections");
		comparisonLookupProjectionsPagedTableComponent.setParentComponentId(parentId);
		comparisonLookupProjectionsPagedTableComponent.setAddToParent(true);
		comparisonLookupProjectionsPagedTableComponent.setResetToDefaultAllowed(false);

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
		comparisonLookupProjectionsPagedTableConfig.setPaginationOff(true);

		comparisonLookupProjectionsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		comparisonLookupProjectionsPagedTableConfig
				.setColumnHeaders(Arrays.asList("Projection Name", "Description", "Market Type", "Contract Holder",
						"Contract", "Brand", GtnFrameworkReportStringConstants.CREATED_DATE, "Created By"));
		comparisonLookupProjectionsPagedTableConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkReportStringConstants.PROJECTION_NAME, GtnFrameworkReportStringConstants.DESCRIPTION2,
				GtnFrameworkReportStringConstants.MARKET_TYPE, GtnFrameworkReportStringConstants.CONTRACT_HOLDER,
				GtnFrameworkReportStringConstants.CONTRACT, GtnFrameworkReportStringConstants.BRAND,
				GtnFrameworkReportStringConstants.CREATED_DATE2, GtnFrameworkReportStringConstants.CREATED_BY });
		comparisonLookupProjectionsPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		comparisonLookupProjectionsPagedTableConfig.setFilteron(true);
		comparisonLookupProjectionsPagedTableConfig
				.setGridHeaderCustomClassLoadURL(GtnFrameworkReportStringConstants.REPORT_COMPARISON_FILTER_ACTION);
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

		List<GtnUIFrameWorkActionConfig> submitActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig projectionSubmitAction = new GtnUIFrameWorkActionConfig();
		projectionSubmitAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		projectionSubmitAction.addActionParameter(GtnReportComparisonProjectionSubmitAction.class.getName());
		projectionSubmitAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		projectionSubmitAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_REPORTING_DASHBOARD_COMPARISON_CONFIG);
		submitActionList.add(projectionSubmitAction);

		GtnUIFrameWorkActionConfig popupCloseAction = new GtnUIFrameWorkActionConfig();
		popupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		popupCloseAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_COMPARISON_LOOKUP_VIEW);
		projectionSubmitAction.addActionParameter(popupCloseAction);
		submitButton.setGtnUIFrameWorkActionConfigList(submitActionList);
	}

	private void addRestButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig resetButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		resetButton.setAuthorizationIncluded(true);
		resetButton.setComponentName("RESET");
		GtnUIFrameWorkActionConfig lowerConfirmResetAction = new GtnUIFrameWorkActionConfig();
		lowerConfirmResetAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		lowerConfirmResetAction.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		lowerConfirmResetAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOWER_PANEL_RESET_CONFIRMATION_MESSAGE);
		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		lowerConfirmResetAction.addActionParameter(resetActionConfigList);
		resetActionConfigList.add(resetButtonAction(true));
		GtnUIFrameWorkActionConfig gridReloadAction = new GtnUIFrameWorkActionConfig();
		gridReloadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gridReloadAction.addActionParameter(GtnReportComparisonProjectionResultsLoadAction.class.getName());
		gridReloadAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		gridReloadAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_REPORTING_DASHBOARD_COMPARISON_CONFIG);
		resetActionConfigList.add(gridReloadAction);
		resetButton.addGtnUIFrameWorkActionConfig(lowerConfirmResetAction);
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
		GtnUIFrameWorkActionConfig beforeCloseAction = new GtnUIFrameWorkActionConfig();
		beforeCloseAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		beforeCloseAction.addActionParameter(GtnReportComparisonProjectionBeforeCloseAction.class.getName());
		beforeCloseAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		beforeCloseAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_REPORTING_DASHBOARD_COMPARISON_CONFIG);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_COMPARISON_LOOKUP_VIEW);
		beforeCloseAction.addActionParameter(closeAction);
		actionConfigList.add(beforeCloseAction);

		closeButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig removeButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "removeButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		removeButton.setAuthorizationIncluded(true);
		removeButton.setComponentName("REMOVE");
		componentList.add(removeButton);

		GtnUIFrameWorkActionConfig recordRemoveAction = new GtnUIFrameWorkActionConfig();
		recordRemoveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		recordRemoveAction.addActionParameter(GtnReportComparisonProjectionRemoveAction.class.getName());
		recordRemoveAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		recordRemoveAction
				.addActionParameter(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
		removeButton.addGtnUIFrameWorkActionConfig(recordRemoveAction);
	}

	private GtnUIFrameWorkActionConfig resetButtonAction(boolean gridResetNeeded) {
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.V8_CONFIRMED_RESET_ACTION);
		List<Object> params = new ArrayList<>();
		List<Object> resetComponentId = new ArrayList<>();
		List<Object> resetComponentValue = new ArrayList<>();
		if (gridResetNeeded) {
			resetComponentId.add(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
			resetComponentId
					.add(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
			resetComponentValue.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			resetComponentValue.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		} else {
			resetComponentId.addAll(Arrays.asList("comparisonLookup_projectionType", "comparisonLookup_workflowStatus",
					"comparisonLookup_reportComparisonLookupMarketType", "comparisonLookup_reportComparisonLookupBrand",
					"comparisonLookup_projectionName", "comparisonLookup_reportComparisonLookupContractHolder",
					"comparisonLookup_ndcConfig", "comparisonLookup_projectionDescription",
					"comparisonLookup_reportComparisonContract", "comparisonLookup_reportComparisonNdcName",
					"comparisonLookup_fromPeriod", "comparisonLookup_toPeriod"));
			resetComponentValue.addAll(Arrays.asList(0, 0, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, null, null));
		}
		params.add(resetComponentId);
		params.add(resetComponentValue);
		resetActionConfig.setActionParameterList(params);
		return resetActionConfig;
	}

}
