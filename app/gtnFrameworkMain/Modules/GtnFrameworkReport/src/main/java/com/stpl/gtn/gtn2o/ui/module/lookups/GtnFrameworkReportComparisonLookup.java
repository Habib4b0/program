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

		addCLProjectionType(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addCLProjectionSearchPanel(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addCLWorkflowStatusNameComponents(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addCLProjectionNameComponents(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addCLDescriptionComponents(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addCLCreatedDate(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addCLControlButtonLayout(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addCLResultsMainLayout(componentList);
		addCLResultPanel(componentList);
		addCLResultsButtonLayout(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);
		addCLRuleDetailsPanel(componentList);
		addCLActionButtonLayout(componentList, GtnFrameworkReportStringConstants.COMPARISON_LOOKUP);

	}

	private void addCLProjectionType(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig clProjectionTypeLayoutConfig = configProvider.getHorizontalLayoutConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionTypeLayout", false, null);
		clProjectionTypeLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		clProjectionTypeLayoutConfig.addComponentStyle("stpl-margin-top-15");
		clProjectionTypeLayoutConfig.addComponentStyle("stpl-margin-bottom-15");
		componentList.add(clProjectionTypeLayoutConfig);

		GtnUIFrameworkComponentConfig clProjectionType = new GtnUIFrameworkComponentConfig();
		clProjectionType.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		clProjectionType.setComponentName("Projection Type");
		clProjectionType.setComponentId(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PROJECTION_TYPE);
		clProjectionType.setAddToParent(true);
		clProjectionType.setCustomReference("integerId");
		clProjectionType.setParentComponentId(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionTypeLayout");
		clProjectionType.setDefaultFocus(true);
		clProjectionType.setComponentWsFieldId("projectionType");

		GtnUIFrameworkComboBoxConfig clProjectionTypeConfig = new GtnUIFrameworkComboBoxConfig();
		clProjectionTypeConfig.setItemValues(Arrays.asList("F", "C"));
		clProjectionTypeConfig.setItemCaptionValues(
				GtnFrameworkReportStringConstants.getReportComparisonProjectionTypeLoadCombobox());
		clProjectionType.setGtnComboboxConfig(clProjectionTypeConfig);

		GtnUIFrameworkValidationConfig clProjectionTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		clProjectionTypeValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		clProjectionType.setGtnUIFrameworkValidationConfig(clProjectionTypeValidationConfig);
		componentList.add(clProjectionType);
	}

	private void addCLResultsMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {

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

	private void addCLResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultPanel = configProvider.getPanelConfig("resultPanel", true,
				GtnFrameworkReportStringConstants.RESULTS_MAIN_LAYOUT_CONFIG);
		resultPanel.setComponentName(GtnFrameworkCommonConstants.RESULTS);
		resultPanel.setAuthorizationIncluded(true);
		resultPanel.setComponentWidth("100%");
		componentList.add(resultPanel);
		addCLPagedTableComponent(componentList, resultPanel.getComponentId());

	}

	private void addCLProjectionSearchPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig clProjectionSearchOptionsCriteriaPanel = new GtnUIFrameworkComponentConfig();
		clProjectionSearchOptionsCriteriaPanel.setComponentName("Projection Search");
		clProjectionSearchOptionsCriteriaPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionOptions");
		clProjectionSearchOptionsCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		clProjectionSearchOptionsCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		clProjectionSearchOptionsCriteriaPanel.setSpacing(true);
		componentList.add(clProjectionSearchOptionsCriteriaPanel);

		GtnUIFrameworkLayoutConfig clProjectionSearchOptionMainLayout = new GtnUIFrameworkLayoutConfig();
		clProjectionSearchOptionMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		clProjectionSearchOptionMainLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig clProjectionOptionMainLayoutConfig = new GtnUIFrameworkComponentConfig();
		clProjectionOptionMainLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		clProjectionOptionMainLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		clProjectionOptionMainLayoutConfig.setAddToParent(true);
		clProjectionOptionMainLayoutConfig.setSpacing(true);
		clProjectionOptionMainLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionOptions");
		clProjectionOptionMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		clProjectionOptionMainLayoutConfig.setGtnLayoutConfig(clProjectionSearchOptionMainLayout);
		componentList.add(clProjectionOptionMainLayoutConfig);

		GtnUIFrameworkLayoutConfig clProjectionSearchOptionInnerLayout = new GtnUIFrameworkLayoutConfig();
		clProjectionSearchOptionInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig clProjectionOptionInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		clProjectionOptionInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		clProjectionOptionInnerLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		clProjectionOptionInnerLayoutConfig.setAddToParent(true);
		clProjectionOptionInnerLayoutConfig.setSpacing(true);
		clProjectionOptionInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		clProjectionOptionInnerLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		clProjectionOptionInnerLayoutConfig.setGtnLayoutConfig(clProjectionSearchOptionInnerLayout);
		componentList.add(clProjectionOptionInnerLayoutConfig);

		GtnUIFrameworkLayoutConfig reportCLComparisonLookupModeSelectionLayout = new GtnUIFrameworkLayoutConfig();
		reportCLComparisonLookupModeSelectionLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportCLComparisonLookupModeSelectionConfig = new GtnUIFrameworkComponentConfig();
		reportCLComparisonLookupModeSelectionConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCLComparisonLookupModeSelectionConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupModeSelectionLayout");
		reportCLComparisonLookupModeSelectionConfig.setAddToParent(true);
		reportCLComparisonLookupModeSelectionConfig.setGtnLayoutConfig(reportCLComparisonLookupModeSelectionLayout);
		reportCLComparisonLookupModeSelectionConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		reportCLComparisonLookupModeSelectionConfig
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(reportCLComparisonLookupModeSelectionConfig);

		GtnUIFrameworkLayoutConfig reportCLComparisonLookupFromToMainLayout = new GtnUIFrameworkLayoutConfig();
		reportCLComparisonLookupFromToMainLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig reportCLComparisonLookupFromToLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportCLComparisonLookupFromToLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCLComparisonLookupFromToLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromToMainLayout");
		reportCLComparisonLookupFromToLayoutConfig.setAddToParent(true);
		reportCLComparisonLookupFromToLayoutConfig.setSpacing(true);
		reportCLComparisonLookupFromToLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		reportCLComparisonLookupFromToLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_OPTION_MAIN_LAYOUT);
		reportCLComparisonLookupFromToLayoutConfig.setGtnLayoutConfig(reportCLComparisonLookupFromToMainLayout);
		componentList.add(reportCLComparisonLookupFromToLayoutConfig);

		GtnUIFrameworkLayoutConfig privateViewCLCompanyProjectionNameLayoutConfig = new GtnUIFrameworkLayoutConfig();
		privateViewCLCompanyProjectionNameLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewCLCompanyProjectionNameLayout = new GtnUIFrameworkComponentConfig();
		privateViewCLCompanyProjectionNameLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewCLCompanyProjectionNameLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		privateViewCLCompanyProjectionNameLayout.setAddToParent(true);
		privateViewCLCompanyProjectionNameLayout.setGtnLayoutConfig(privateViewCLCompanyProjectionNameLayoutConfig);
		privateViewCLCompanyProjectionNameLayout
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_OPTION_INNER_LAYOUT);
		privateViewCLCompanyProjectionNameLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(privateViewCLCompanyProjectionNameLayout);

	}

	private void addCLWorkflowStatusNameComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig clWorkflowStatusNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsCLLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsCLLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsCLLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		publicViewsCLLayoutConfig.setAddToParent(true);
		publicViewsCLLayoutConfig.setSpacing(true);
		publicViewsCLLayoutConfig.addComponentStyle("stpl-margin-top-15");
		publicViewsCLLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsCLLayoutConfig.setGtnLayoutConfig(clWorkflowStatusNameLayout);
		componentList.add(publicViewsCLLayoutConfig);

		GtnUIFrameworkComponentConfig clWorkflowStatus = new GtnUIFrameworkComponentConfig();
		clWorkflowStatus.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		clWorkflowStatus.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.WORKFLOW_STATUS);
		clWorkflowStatus.setComponentName("Workflow Status: ");
		clWorkflowStatus.setCustomReference("integerId");
		clWorkflowStatus.setAddToParent(true);
		clWorkflowStatus.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		clWorkflowStatus.setComponentWsFieldId("workflowStatus");

		GtnUIFrameworkComboBoxConfig clWorkflowStatusConfig = new GtnUIFrameworkComboBoxConfig();
		clWorkflowStatusConfig
				.setItemValues(GtnFrameworkReportStringConstants.getReportComparisonWorkflowStatusLoadCombobox());
		clWorkflowStatus.setGtnComboboxConfig(clWorkflowStatusConfig);

		GtnUIFrameworkValidationConfig clWorkflowStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		clWorkflowStatusValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		clWorkflowStatus.setGtnUIFrameworkValidationConfig(clWorkflowStatusValidationConfig);

		componentList.add(clWorkflowStatus);

		GtnUIFrameworkLayoutConfig reportCLComparisonLookupBusinessUnitLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportComparisonCLLookupBusinessUnitLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportComparisonCLLookupBusinessUnitLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportComparisonCLLookupBusinessUnitLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.BUSINESS_UNIT_LAYOUT);
		reportComparisonCLLookupBusinessUnitLayoutConfig.setAddToParent(true);
		reportComparisonCLLookupBusinessUnitLayoutConfig.setSpacing(true);
		reportComparisonCLLookupBusinessUnitLayoutConfig
				.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportComparisonCLLookupBusinessUnitLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportComparisonCLLookupBusinessUnitLayoutConfig.setGtnLayoutConfig(reportCLComparisonLookupBusinessUnitLayout);
		componentList.add(reportComparisonCLLookupBusinessUnitLayoutConfig);

		GtnUIFrameworkComponentConfig reportCLComparisonLookupMarketType = new GtnUIFrameworkComponentConfig();
		reportCLComparisonLookupMarketType.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportCLComparisonLookupMarketType.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupMarketType");
		reportCLComparisonLookupMarketType.setComponentName("Market Type: ");
		reportCLComparisonLookupMarketType.setAddToParent(true);
		reportCLComparisonLookupMarketType.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.BUSINESS_UNIT_LAYOUT);
		reportCLComparisonLookupMarketType.setComponentWsFieldId(GtnFrameworkReportStringConstants.MARKET_TYPE);

		componentList.add(reportCLComparisonLookupMarketType);
		GtnUIFrameworkLayoutConfig reportCLComparisonLookupBrandLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportCLComparisonLookupBrandLayoutConfig = new GtnUIFrameworkComponentConfig();
		reportCLComparisonLookupBrandLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCLComparisonLookupBrandLayoutConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrandLayoutConfig");
		reportCLComparisonLookupBrandLayoutConfig.setAddToParent(true);
		reportCLComparisonLookupBrandLayoutConfig.setSpacing(true);
		reportCLComparisonLookupBrandLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportCLComparisonLookupBrandLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportCLComparisonLookupBrandLayoutConfig.setGtnLayoutConfig(reportCLComparisonLookupBrandLayout);
		componentList.add(reportCLComparisonLookupBrandLayoutConfig);

		GtnUIFrameworkComponentConfig reportCLComparisonLookupBrand = new GtnUIFrameworkComponentConfig();
		reportCLComparisonLookupBrand.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportCLComparisonLookupBrand.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrand");
		reportCLComparisonLookupBrand.setComponentName("Brand: ");
		reportCLComparisonLookupBrand.setAddToParent(true);
		reportCLComparisonLookupBrand.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrandLayoutConfig");
		reportCLComparisonLookupBrand.setComponentWsFieldId(GtnFrameworkReportStringConstants.BRAND);
		componentList.add(reportCLComparisonLookupBrand);
	}

	private void addCLProjectionNameComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkLayoutConfig clProjectionNameComponent = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsCLLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsCLLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsCLLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		publicViewsCLLayoutConfig.setAddToParent(true);
		publicViewsCLLayoutConfig.setSpacing(true);
		publicViewsCLLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		publicViewsCLLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsCLLayoutConfig.setGtnLayoutConfig(clProjectionNameComponent);
		componentList.add(publicViewsCLLayoutConfig);

		GtnUIFrameworkComponentConfig clProjectionName = new GtnUIFrameworkComponentConfig();
		clProjectionName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		clProjectionName.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PROJECTION_NAME);
		clProjectionName.setComponentName("Projection Name: ");
		clProjectionName.setAddToParent(true);
		clProjectionName.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		clProjectionName.setComponentWsFieldId(GtnFrameworkReportStringConstants.PROJECTION_NAME);

		componentList.add(clProjectionName);

		GtnUIFrameworkLayoutConfig reportCLComparisonLookupContractHolderLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportCLComparisonLookupContractHolderConfig = new GtnUIFrameworkComponentConfig();
		reportCLComparisonLookupContractHolderConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCLComparisonLookupContractHolderConfig.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolderConfig");
		reportCLComparisonLookupContractHolderConfig.setAddToParent(true);
		reportCLComparisonLookupContractHolderConfig.setSpacing(true);
		reportCLComparisonLookupContractHolderConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportCLComparisonLookupContractHolderConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportCLComparisonLookupContractHolderConfig.setGtnLayoutConfig(reportCLComparisonLookupContractHolderLayout);
		componentList.add(reportCLComparisonLookupContractHolderConfig);

		GtnUIFrameworkComponentConfig reportCLComparisonLookupContractHolder = new GtnUIFrameworkComponentConfig();
		reportCLComparisonLookupContractHolder.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportCLComparisonLookupContractHolder.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolder");
		reportCLComparisonLookupContractHolder.setComponentName("Contract Holder: ");
		reportCLComparisonLookupContractHolder.setAddToParent(true);
		reportCLComparisonLookupContractHolder.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolderConfig");
		reportCLComparisonLookupContractHolder.setComponentWsFieldId(GtnFrameworkReportStringConstants.CONTRACT_HOLDER);
		componentList.add(reportCLComparisonLookupContractHolder);

		GtnUIFrameworkLayoutConfig clNdcLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig clNdcLayoutConfig = new GtnUIFrameworkComponentConfig();
		clNdcLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		clNdcLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_LAYOUT);
		clNdcLayoutConfig.setAddToParent(true);
		clNdcLayoutConfig.setSpacing(true);
		clNdcLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		clNdcLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		clNdcLayoutConfig.setGtnLayoutConfig(clNdcLayout);
		componentList.add(clNdcLayoutConfig);

		GtnUIFrameworkComponentConfig clNdcConfig = new GtnUIFrameworkComponentConfig();
		clNdcConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		clNdcConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_CONFIG);
		clNdcConfig.setComponentName("NDC #: ");
		clNdcConfig.setAddToParent(true);
		clNdcConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.NDC_LAYOUT);
		clNdcConfig.setComponentWsFieldId("comparisonNDC");
		componentList.add(clNdcConfig);

	}

	private void addCLDescriptionComponents(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig clDescriptionLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig publicViewsCLLayoutConfig = new GtnUIFrameworkComponentConfig();
		publicViewsCLLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewsCLLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		publicViewsCLLayoutConfig.setAddToParent(true);
		publicViewsCLLayoutConfig.setSpacing(true);
		publicViewsCLLayoutConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		publicViewsCLLayoutConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		publicViewsCLLayoutConfig.setGtnLayoutConfig(clDescriptionLayout);
		componentList.add(publicViewsCLLayoutConfig);

		GtnUIFrameworkComponentConfig clDescription = new GtnUIFrameworkComponentConfig();
		clDescription.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		clDescription.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionDescription");
		clDescription.setComponentName("Description: ");
		clDescription.setAddToParent(true);
		clDescription.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LAYOUT);
		clDescription.setComponentWsFieldId(GtnFrameworkReportStringConstants.DESCRIPTION2);

		componentList.add(clDescription);

		GtnUIFrameworkLayoutConfig reportCLComparisonContractLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportCLComparisonContractConfig = new GtnUIFrameworkComponentConfig();
		reportCLComparisonContractConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCLComparisonContractConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContractConfig");
		reportCLComparisonContractConfig.setAddToParent(true);
		reportCLComparisonContractConfig.setSpacing(true);
		reportCLComparisonContractConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportCLComparisonContractConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportCLComparisonContractConfig.setGtnLayoutConfig(reportCLComparisonContractLayout);
		componentList.add(reportCLComparisonContractConfig);

		GtnUIFrameworkComponentConfig reportCLComparisonContract = new GtnUIFrameworkComponentConfig();
		reportCLComparisonContract.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportCLComparisonContract
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContract");
		reportCLComparisonContract.setComponentName("Contract: ");
		reportCLComparisonContract.setAddToParent(true);
		reportCLComparisonContract.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContractConfig");
		reportCLComparisonContract.setComponentWsFieldId(GtnFrameworkReportStringConstants.CONTRACT);
		componentList.add(reportCLComparisonContract);

		GtnUIFrameworkLayoutConfig reportCLComparisonNdcNameLayout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig reportCLComparisonNdcNameConfig = new GtnUIFrameworkComponentConfig();
		reportCLComparisonNdcNameConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportCLComparisonNdcNameConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcNameConfig");
		reportCLComparisonNdcNameConfig.setAddToParent(true);
		reportCLComparisonNdcNameConfig.setSpacing(true);
		reportCLComparisonNdcNameConfig.addComponentStyle(GtnFrameworkReportStringConstants.STPL_PADDING_10);
		reportCLComparisonNdcNameConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PROJECTION_SELECTIONLAYOUT1);
		reportCLComparisonNdcNameConfig.setGtnLayoutConfig(reportCLComparisonNdcNameLayout);
		componentList.add(reportCLComparisonNdcNameConfig);

		GtnUIFrameworkComponentConfig reportCLComparisonNdcName = new GtnUIFrameworkComponentConfig();
		reportCLComparisonNdcName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		reportCLComparisonNdcName
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcName");
		reportCLComparisonNdcName.setComponentName("NDC Name: ");
		reportCLComparisonNdcName.setAddToParent(true);
		reportCLComparisonNdcName.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcNameConfig");
		reportCLComparisonNdcName.setComponentWsFieldId("ndcName");
		componentList.add(reportCLComparisonNdcName);

	}

	private void addCLCreatedDate(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig clPanel = new GtnUIFrameworkComponentConfig();
		clPanel.setComponentName(GtnFrameworkReportStringConstants.CREATED_DATE);
		clPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		clPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		clPanel.setComponentWidth("65%");
		clPanel.setAddToParent(true);
		clPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromToMainLayout");
		clPanel.addComponentStyle(GtnFrameworkCssConstants.PANEL_TIME_PERIOD_RTN_FOR);
		clPanel.addComponentStyle("stpl-margin-bottom-14");
		componentList.add(clPanel);

		GtnUIFrameworkLayoutConfig clComparisonLookuptimePeriodInnerLayout = new GtnUIFrameworkLayoutConfig();
		clComparisonLookuptimePeriodInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig clComparisonFromAndToperiodLayoutConfig = new GtnUIFrameworkComponentConfig();
		clComparisonFromAndToperiodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		clComparisonFromAndToperiodLayoutConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		clComparisonFromAndToperiodLayoutConfig.setSpacing(true);
		clComparisonFromAndToperiodLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		clComparisonFromAndToperiodLayoutConfig.setAddToParent(true);
		clComparisonFromAndToperiodLayoutConfig.setGtnLayoutConfig(clComparisonLookuptimePeriodInnerLayout);
		clComparisonFromAndToperiodLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "timePeriod");
		clComparisonFromAndToperiodLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(clComparisonFromAndToperiodLayoutConfig);

		GtnUIFrameworkComponentConfig clComparisonFromAndToperiodVerticalLayoutConfig = configProvider
				.getVerticalLayoutConfig(
						GtnFrameworkReportStringConstants.COMPARISON_FROM_AND_TOPERIOD_VERTICAL_LAYOUT_CONFIG, true,
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkCommonConstants.TIME_PERIOD_INNER_LAYOUT);
		clComparisonFromAndToperiodVerticalLayoutConfig.setComponentWidth("100%");
		clComparisonFromAndToperiodVerticalLayoutConfig.setComponentHight("85px");
		componentList.add(clComparisonFromAndToperiodVerticalLayoutConfig);

		GtnUIFrameworkLayoutConfig clComparisonLookupFromPeriodLayout = new GtnUIFrameworkLayoutConfig();
		clComparisonLookupFromPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig clComparisonLookupFromPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		clComparisonLookupFromPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		clComparisonLookupFromPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		clComparisonLookupFromPeriodLayoutConfig.setAddToParent(true);
		clComparisonLookupFromPeriodLayoutConfig.setGtnLayoutConfig(clComparisonLookupFromPeriodLayout);
		clComparisonLookupFromPeriodLayoutConfig.setParentComponentId(
				GtnFrameworkReportStringConstants.COMPARISON_FROM_AND_TOPERIOD_VERTICAL_LAYOUT_CONFIG);
		componentList.add(clComparisonLookupFromPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig clFromPeriod = new GtnUIFrameworkComponentConfig();
		clFromPeriod.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		clFromPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD);
		clFromPeriod.setComponentName("From ");
		clFromPeriod.setAddToParent(true);
		clFromPeriod.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "fromPeriodLayout");
		clFromPeriod.setComponentWsFieldId("fromPeriod");

		componentList.add(clFromPeriod);

		GtnUIFrameworkLayoutConfig clComparisonLookupToPeriodLayout = new GtnUIFrameworkLayoutConfig();
		clComparisonLookupToPeriodLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig clComparisonLookupToPeriodLayoutConfig = new GtnUIFrameworkComponentConfig();
		clComparisonLookupToPeriodLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		clComparisonLookupToPeriodLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");
		clComparisonLookupToPeriodLayoutConfig.setAddToParent(true);
		clComparisonLookupToPeriodLayoutConfig.setGtnLayoutConfig(clComparisonLookupToPeriodLayout);
		clComparisonLookupToPeriodLayoutConfig.setParentComponentId(
				GtnFrameworkReportStringConstants.COMPARISON_FROM_AND_TOPERIOD_VERTICAL_LAYOUT_CONFIG);
		componentList.add(clComparisonLookupToPeriodLayoutConfig);

		GtnUIFrameworkComponentConfig clComparisonLookupToPeriod = new GtnUIFrameworkComponentConfig();
		clComparisonLookupToPeriod.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		clComparisonLookupToPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD);
		clComparisonLookupToPeriod.setComponentName("To ");
		clComparisonLookupToPeriod.setAddToParent(true);
		clComparisonLookupToPeriod
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "toPeriodLayout");
		clComparisonLookupToPeriod.setComponentWsFieldId("toPeriod");

		componentList.add(clComparisonLookupToPeriod);
	}

	private void addCLControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig clControlButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkReportStringConstants.CONTROL_BUTTON_LAYOUT, false, null);
		clControlButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(clControlButtonLayout);
		addCLSearchButtonComponent(componentList, nameSpace);
		addCLResetButtonComponent(componentList, nameSpace);
	}

	private void addCLResultsButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig resultsButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkReportStringConstants.CONTROL_BUTTON_LAYOUT, false, null);
		resultsButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(resultsButtonLayout);
		addAddButtonComponent(componentList, nameSpace);
	}

	private void addCLSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig clSearchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "searchButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		clSearchButtonConfig.setAuthorizationIncluded(true);
		clSearchButtonConfig.setComponentName("SEARCH");
		componentList.add(clSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> clSearchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig clProjectionTypeValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		clProjectionTypeValidationActionConfig.setFieldValues(Arrays.asList(nameSpace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.PROJECTION_TYPE));

		GtnUIFrameWorkActionConfig clProjectionTypeAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> clProjectionTypeAlertParams = new ArrayList<>();
		clProjectionTypeAlertParams.add(GtnFrameworkCommonConstants.ERROR);
		clProjectionTypeAlertParams.add("Please select a Projection Type");
		clProjectionTypeAlertActionConfig.setActionParameterList(clProjectionTypeAlertParams);

		clProjectionTypeValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(clProjectionTypeAlertActionConfig)));
		clSearchActionConfigList.add(clProjectionTypeValidationActionConfig);

		GtnUIFrameWorkActionConfig clWorkflowStatusValidationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		clWorkflowStatusValidationActionConfig.setFieldValues(Arrays.asList(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PROJECTION_TYPE,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.WORKFLOW_STATUS));

		GtnUIFrameWorkActionConfig clWorkFlowStatusAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> clAlertParams = new ArrayList<>();
		clAlertParams.add(GtnFrameworkCommonConstants.ERROR);
		clAlertParams.add("Please select a Workflow Status");
		clWorkFlowStatusAlertActionConfig.setActionParameterList(clAlertParams);

		clWorkflowStatusValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(clWorkFlowStatusAlertActionConfig)));
		clSearchActionConfigList.add(clWorkflowStatusValidationActionConfig);

		GtnUIFrameWorkActionConfig clLoadDataGridAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		clLoadDataGridAction.setActionParameterList(
				Arrays.asList(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT));
		clLoadDataGridAction.setFieldValues(Arrays.asList("reportLandingScreen_displaySelectionTabCustomView",
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
		clSearchActionConfigList.add(clLoadDataGridAction);

		GtnUIFrameWorkActionConfig clEnableAddAction = new GtnUIFrameWorkActionConfig();
		clEnableAddAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		clEnableAddAction.addActionParameter(GtnReportComparisonEnableAddBtnAction.class.getName());
		clEnableAddAction
				.addActionParameter(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
		clEnableAddAction
				.addActionParameter(nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "addButtonConfig");
		clSearchActionConfigList.add(clEnableAddAction);

		clSearchButtonConfig.setGtnUIFrameWorkActionConfigList(clSearchActionConfigList);

	}

	private void addCLResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {

		GtnUIFrameworkComponentConfig clResetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButtonConfig", true,
				GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		clResetButtonConfig.setComponentName("RESET");
		clResetButtonConfig.setAuthorizationIncluded(true);
		GtnUIFrameWorkActionConfig clSearchConfirmResetAction = new GtnUIFrameWorkActionConfig();
		clSearchConfirmResetAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		clSearchConfirmResetAction.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		clSearchConfirmResetAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_SEARCH_PANEL_RESET_CONFIRMATION_MESSAGE);
		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		resetActionConfigList.add(resetButtonAction(false));
		clSearchConfirmResetAction.addActionParameter(resetActionConfigList);
		clResetButtonConfig.addGtnUIFrameWorkActionConfig(clSearchConfirmResetAction);
		componentList.add(clResetButtonConfig);
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

	private void addCLPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig clComparisonLookupResultsPagedTableComponent = new GtnUIFrameworkComponentConfig();
		clComparisonLookupResultsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		clComparisonLookupResultsPagedTableComponent
				.setComponentId(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
		clComparisonLookupResultsPagedTableComponent.setComponentName("Results");
		clComparisonLookupResultsPagedTableComponent.setParentComponentId(parentId);
		clComparisonLookupResultsPagedTableComponent.setAddToParent(true);

		List<String> clTableStyle = new ArrayList<>();
		clTableStyle.add("filterbar");
		clTableStyle.add("v-has-width");
		clTableStyle.add("v-table-filterbar");
		clTableStyle.add("table-header-normal");
		clComparisonLookupResultsPagedTableComponent.setComponentWidth("100%");
		clComparisonLookupResultsPagedTableComponent.setComponentStyle(clTableStyle);

		componentList.add(clComparisonLookupResultsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig clComparisonLookupResultsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		clComparisonLookupResultsPagedTableConfig.setEditable(false);
		clComparisonLookupResultsPagedTableConfig.setFilterBar(true);
		clComparisonLookupResultsPagedTableConfig.setPageLength(10);
		clComparisonLookupResultsPagedTableConfig.setItemPerPage(10);
		clComparisonLookupResultsPagedTableConfig.setSelectable(true);
		clComparisonLookupResultsPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		clComparisonLookupResultsPagedTableConfig.setItemsPerPageAlignCentre(false);

		clComparisonLookupResultsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		clComparisonLookupResultsPagedTableConfig
				.setColumnHeaders(Arrays.asList("Projection Name", "Description", "Market Type", "Contract Holder",
						"Contract", "Brand", GtnFrameworkReportStringConstants.CREATED_DATE, "Created By"));
		clComparisonLookupResultsPagedTableConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkReportStringConstants.PROJECTION_NAME, GtnFrameworkReportStringConstants.DESCRIPTION2,
				GtnFrameworkReportStringConstants.MARKET_TYPE, GtnFrameworkReportStringConstants.CONTRACT_HOLDER,
				GtnFrameworkReportStringConstants.CONTRACT, GtnFrameworkReportStringConstants.BRAND,
				GtnFrameworkReportStringConstants.CREATED_DATE2, GtnFrameworkReportStringConstants.CREATED_BY });
		clComparisonLookupResultsPagedTableConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_COMPARISONLOOKUP_AVAILABLETABLE_COUNTSERVICE);
		clComparisonLookupResultsPagedTableConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_COMPARISONLOOKUP_AVAILABLETABLE_LOADSERVICE);
		clComparisonLookupResultsPagedTableConfig.setCustomFilterConfigMap(getCLCustomFilterConfig());
		clComparisonLookupResultsPagedTableComponent.setModuleName("report");

		clComparisonLookupResultsPagedTableComponent.setGtnPagedTableConfig(clComparisonLookupResultsPagedTableConfig);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCLCustomFilterConfig() {

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> clComparisonLookupCustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		String[] clComparisonLookupComboboxIds = new String[1];
		String[] clCcomparisonLookupComboBoxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig clComparisonLookupFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			clComparisonLookupFilterConfig.setPropertId(columnPropertyIds[i]);
			clComparisonLookupFilterConfig.setGtnComponentType(comparisonLookupComponentType[i]);
			if ((startIndex < clComparisonLookupComboboxIds.length)
					&& columnPropertyIds[i].equals(clComparisonLookupComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig clComparisonLookupSearchFilterConfig = new GtnUIFrameworkComponentConfig();
				clComparisonLookupSearchFilterConfig.setComponentId("customFilterComboBox");
				clComparisonLookupSearchFilterConfig.setComponentName("customFilterComboBox");
				clComparisonLookupSearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				clComparisonLookupSearchFilterConfig.getGtnComboboxConfig()
						.setComboBoxType(clCcomparisonLookupComboBoxType[startIndex]);
				clComparisonLookupSearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				clComparisonLookupFilterConfig.setGtnComponentConfig(clComparisonLookupSearchFilterConfig);
				startIndex++;
			}
			clComparisonLookupCustomFilterConfigMap.put(clComparisonLookupFilterConfig.getPropertId(),
					clComparisonLookupFilterConfig);
		}
		return clComparisonLookupCustomFilterConfigMap;
	}

	private void addCLRuleDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cdrPopUpRuleDetailsResultPanel = configProvider
				.getPanelConfig("ruleDetailsResultPanel", false, null);
		cdrPopUpRuleDetailsResultPanel.setComponentWidth("100%");
		cdrPopUpRuleDetailsResultPanel.setComponentName("Projections");
		cdrPopUpRuleDetailsResultPanel.setAuthorizationIncluded(true);
		componentList.add(cdrPopUpRuleDetailsResultPanel);
		ruleDetailsResultDataTable(componentList, cdrPopUpRuleDetailsResultPanel.getComponentId());
	}

	private void ruleDetailsResultDataTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig clCcomparisonLookupProjectionsPagedTableComponent = new GtnUIFrameworkComponentConfig();
		clCcomparisonLookupProjectionsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		clCcomparisonLookupProjectionsPagedTableComponent.setComponentId(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		clCcomparisonLookupProjectionsPagedTableComponent.setComponentName("Projections");
		clCcomparisonLookupProjectionsPagedTableComponent.setParentComponentId(parentId);
		clCcomparisonLookupProjectionsPagedTableComponent.setAddToParent(true);
		clCcomparisonLookupProjectionsPagedTableComponent.setResetToDefaultAllowed(false);

		List<String> clTableStyle = new ArrayList<>();
		clTableStyle.add("filterbar");
		clTableStyle.add("v-has-width");
		clTableStyle.add("v-table-filterbar");
		clTableStyle.add("table-header-normal");
		clCcomparisonLookupProjectionsPagedTableComponent.setComponentWidth("100%");
		clCcomparisonLookupProjectionsPagedTableComponent.setComponentStyle(clTableStyle);

		componentList.add(clCcomparisonLookupProjectionsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig clComparisonLookupProjectionsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		clComparisonLookupProjectionsPagedTableConfig.setEditable(false);
		clComparisonLookupProjectionsPagedTableConfig.setFilterBar(true);
		clComparisonLookupProjectionsPagedTableConfig.setPageLength(10);
		clComparisonLookupProjectionsPagedTableConfig.setItemPerPage(10);
		clComparisonLookupProjectionsPagedTableConfig.setSelectable(true);
		clComparisonLookupProjectionsPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		clComparisonLookupProjectionsPagedTableConfig.setPaginationOff(true);

		clComparisonLookupProjectionsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		clComparisonLookupProjectionsPagedTableConfig
				.setColumnHeaders(Arrays.asList("Projection Name", "Description", "Market Type", "Contract Holder",
						"Contract", "Brand", GtnFrameworkReportStringConstants.CREATED_DATE, "Created By"));
		clComparisonLookupProjectionsPagedTableConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkReportStringConstants.PROJECTION_NAME, GtnFrameworkReportStringConstants.DESCRIPTION2,
				GtnFrameworkReportStringConstants.MARKET_TYPE, GtnFrameworkReportStringConstants.CONTRACT_HOLDER,
				GtnFrameworkReportStringConstants.CONTRACT, GtnFrameworkReportStringConstants.BRAND,
				GtnFrameworkReportStringConstants.CREATED_DATE2, GtnFrameworkReportStringConstants.CREATED_BY });
		clComparisonLookupProjectionsPagedTableConfig.setCustomFilterConfigMap(getCLCustomFilterConfig());
		clComparisonLookupProjectionsPagedTableConfig.setFilteron(true);
		clComparisonLookupProjectionsPagedTableConfig
				.setGridHeaderCustomClassLoadURL(GtnFrameworkReportStringConstants.REPORT_COMPARISON_FILTER_ACTION);
		clCcomparisonLookupProjectionsPagedTableComponent
				.setGtnPagedTableConfig(clComparisonLookupProjectionsPagedTableConfig);
	}

	private void addCLActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig actionButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkReportStringConstants.ACTION_BUTTON_LAYOUT, false, null);
		actionButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(actionButtonLayout);
		addCLSubmitButtonComponent(componentList, nameSpace);
		addCLRestButtonComponent(componentList, nameSpace);
		addCLCloseButtonComponent(componentList, nameSpace);
		addCLRemoveButtonComponent(componentList, nameSpace);
	}

	private void addCLSubmitButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig clSubmitButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "submitButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		clSubmitButton.setAuthorizationIncluded(true);
		clSubmitButton.setComponentName("SUBMIT");
		componentList.add(clSubmitButton);

		List<GtnUIFrameWorkActionConfig> clSubmitActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig clProjectionSubmitAction = new GtnUIFrameWorkActionConfig();
		clProjectionSubmitAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		clProjectionSubmitAction.addActionParameter(GtnReportComparisonProjectionSubmitAction.class.getName());
		clProjectionSubmitAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		clProjectionSubmitAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_REPORTING_DASHBOARD_COMPARISON_CONFIG);
		clSubmitActionList.add(clProjectionSubmitAction);

		GtnUIFrameWorkActionConfig clPopupCloseAction = new GtnUIFrameWorkActionConfig();
		clPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		clPopupCloseAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_COMPARISON_LOOKUP_VIEW);
		clProjectionSubmitAction.addActionParameter(clPopupCloseAction);
		clSubmitButton.setGtnUIFrameWorkActionConfigList(clSubmitActionList);
	}

	private void addCLRestButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig clResetButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		clResetButton.setAuthorizationIncluded(true);
		clResetButton.setComponentName("RESET");
		GtnUIFrameWorkActionConfig clLowerConfirmResetAction = new GtnUIFrameWorkActionConfig();
		clLowerConfirmResetAction.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		clLowerConfirmResetAction.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		clLowerConfirmResetAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOWER_PANEL_RESET_CONFIRMATION_MESSAGE);
		List<GtnUIFrameWorkActionConfig> clResetActionConfigList = new ArrayList<>();
		clLowerConfirmResetAction.addActionParameter(clResetActionConfigList);
		clResetActionConfigList.add(resetButtonAction(true));
		GtnUIFrameWorkActionConfig clGridReloadAction = new GtnUIFrameWorkActionConfig();
		clGridReloadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		clGridReloadAction.addActionParameter(GtnReportComparisonProjectionResultsLoadAction.class.getName());
		clGridReloadAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		clGridReloadAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_REPORTING_DASHBOARD_COMPARISON_CONFIG);
		clResetActionConfigList.add(clGridReloadAction);
		clResetButton.addGtnUIFrameWorkActionConfig(clLowerConfirmResetAction);
		componentList.add(clResetButton);
	}

	private void addCLCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig clCloseButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "closeButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		clCloseButton.setComponentName("CLOSE");
		clCloseButton.setAuthorizationIncluded(true);
		componentList.add(clCloseButton);

		List<GtnUIFrameWorkActionConfig> clActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig clBeforeCloseAction = new GtnUIFrameWorkActionConfig();
		clBeforeCloseAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		clBeforeCloseAction.addActionParameter(GtnReportComparisonProjectionBeforeCloseAction.class.getName());
		clBeforeCloseAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		clBeforeCloseAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_REPORTING_DASHBOARD_COMPARISON_CONFIG);

		GtnUIFrameWorkActionConfig clCloseAction = new GtnUIFrameWorkActionConfig();
		clCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		clCloseAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_COMPARISON_LOOKUP_VIEW);
		clBeforeCloseAction.addActionParameter(clCloseAction);
		clActionConfigList.add(clBeforeCloseAction);

		clCloseButton.setGtnUIFrameWorkActionConfigList(clActionConfigList);

	}

	private void addCLRemoveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig clRemoveButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "removeButton", true,
				GtnFrameworkCommonConstants.ACTION_BUTTONLAYOUT, GtnUIFrameworkComponentType.BUTTON);
		clRemoveButton.setAuthorizationIncluded(true);
		clRemoveButton.setComponentName("REMOVE");
		componentList.add(clRemoveButton);

		GtnUIFrameWorkActionConfig clRecordRemoveAction = new GtnUIFrameWorkActionConfig();
		clRecordRemoveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		clRecordRemoveAction.addActionParameter(GtnReportComparisonProjectionRemoveAction.class.getName());
		clRecordRemoveAction.addActionParameter(
				GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_PROJECTIONS_RESULTS_PAGED_TABLE_COMPONENT);
		clRecordRemoveAction
				.addActionParameter(GtnFrameworkReportStringConstants.COMPARISON_LOOKUP_RESULTS_PAGED_TABLE_COMPONENT);
		clRemoveButton.addGtnUIFrameWorkActionConfig(clRecordRemoveAction);
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
