/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.projectionvariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.GtnFrameworkExpandCollapseLevelSection;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkCommercialForecastingPVTabConfig {

	private GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

	public void addProjectionVarianceTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutConfig.getRootLayoutConfig(
				"commercialForecastingProjectionVarianceRootLayout", GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutConfig.setSpacing(Boolean.TRUE);
		componentList.add(rootLayoutConfig);
		addProjectionVarianceSelection(componentList, rootLayoutConfig.getComponentId());
		addResetAndGenerate(componentList, rootLayoutConfig.getComponentId());
		addProjectionVariancePanel(componentList, rootLayoutConfig.getComponentId());
		addResetRefreshButtons(componentList, rootLayoutConfig.getComponentId());
	}

	private void addProjectionVarianceSelection(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig projectionVarianceSelectionPanel = layoutConfig
				.getPanelConfig("projectionVarianceSelection", componentId);
		projectionVarianceSelectionPanel.setComponentName(" Projection Results Selection ");
		componentList.add(projectionVarianceSelectionPanel);

		GtnUIFrameworkComponentConfig projectionVarianceSelectionMainHorizontalLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionVarianceSelectionMainLayout",
						projectionVarianceSelectionPanel.getComponentId());
		componentList.add(projectionVarianceSelectionMainHorizontalLayout);

		GtnUIFrameworkComponentConfig projectionVarianceSelectionMainLayout = layoutConfig.getVerticalLayoutConfig(
				"projectionVarianceSelectionMainLayout",
				projectionVarianceSelectionMainHorizontalLayout.getComponentId());
		componentList.add(projectionVarianceSelectionMainLayout);

		GtnUIFrameworkComponentConfig projectionVarianceComparisonAndLevelLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionVarianceFrequencySalesUnits",
						projectionVarianceSelectionMainLayout.getComponentId());
		componentList.add(projectionVarianceComparisonAndLevelLayout);

		addComparisonLookup(componentList, projectionVarianceComparisonAndLevelLayout.getComponentId());
		addLevelOptionGroup(componentList, projectionVarianceComparisonAndLevelLayout.getComponentId());
		addVariableCategory(componentList, projectionVarianceComparisonAndLevelLayout.getComponentId());
		addProjectionPeriodOrder(componentList, projectionVarianceComparisonAndLevelLayout.getComponentId());

		GtnUIFrameworkComponentConfig projectionVarianceHistoryProjectionPeriodOrderLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionVarianceHistoryProjectionPeriodOrder",
						projectionVarianceSelectionMainLayout.getComponentId());
		componentList.add(projectionVarianceHistoryProjectionPeriodOrderLayout);

		addFrequency(componentList, projectionVarianceHistoryProjectionPeriodOrderLayout.getComponentId());
		addDiscountLevelOptionGroup(componentList,
				projectionVarianceHistoryProjectionPeriodOrderLayout.getComponentId());
		addVariables(componentList, projectionVarianceHistoryProjectionPeriodOrderLayout.getComponentId());
		addPivotView(componentList, projectionVarianceHistoryProjectionPeriodOrderLayout.getComponentId());

		GtnUIFrameworkComponentConfig horizontalLayoutForFromDate = layoutConfig.getHorizontalLayoutConfig(
				"projectionVarianceFromDate", projectionVarianceSelectionMainHorizontalLayout.getComponentId());
		componentList.add(horizontalLayoutForFromDate);
		GtnUIFrameworkComponentConfig verticalLayoutForDateRange = layoutConfig.getVerticalLayoutConfig(
				"projectionVarianceDateRange", projectionVarianceSelectionMainHorizontalLayout.getComponentId());
		componentList.add(verticalLayoutForDateRange);
		addDateRange(componentList, verticalLayoutForDateRange.getComponentId());
		GtnUIFrameworkComponentConfig verticalLayoutForFromToDate = layoutConfig.getVerticalLayoutConfig(
				"projectionVarianceFromToDate", projectionVarianceSelectionMainHorizontalLayout.getComponentId());
		componentList.add(verticalLayoutForFromToDate);
		addFromDate(componentList, verticalLayoutForFromToDate.getComponentId());
		addToDate(componentList, verticalLayoutForFromToDate.getComponentId());

	}

	private void addComparisonLookup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionVarianceComparisonCSSLayout = layoutConfig
				.getCssLayoutConfig("projectionVarianceComparison", parentId);
		projectionVarianceComparisonCSSLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionVarianceComparisonCSSLayout);

		GtnUIFrameworkComponentConfig projectionVarianceComparisonLayout = layoutConfig.getHorizontalLayoutConfig(
				"projectionVarianceComparison", projectionVarianceComparisonCSSLayout.getComponentId());
		componentList.add(projectionVarianceComparisonLayout);

		GtnUIFrameworkComponentConfig projectionVarianceComparisonPopup = new GtnUIFrameworkComponentConfig();
		projectionVarianceComparisonPopup.setComponentType(GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		projectionVarianceComparisonPopup.setComponentId("projectionVarianceComparison");
		projectionVarianceComparisonPopup.setComponentName("Comparison :");
		projectionVarianceComparisonPopup.setAddToParent(Boolean.TRUE);
		projectionVarianceComparisonPopup.setParentComponentId(projectionVarianceComparisonLayout.getComponentId());

		GtnUIFrameworkComboBoxConfig projectionVarianceComparisonConfig = new GtnUIFrameworkComboBoxConfig();
		projectionVarianceComparisonConfig.setComboBoxType("PAYMENT_FREQUENCY");
		projectionVarianceComparisonConfig.setHasDefaultValue(Boolean.TRUE);
		projectionVarianceComparisonPopup.setGtnComboboxConfig(projectionVarianceComparisonConfig);
		componentList.add(projectionVarianceComparisonPopup);
	}

	private void addLevelOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionVarianceLevelCssLayout = layoutConfig.getCssLayoutConfig("level",
				parentId);
		projectionVarianceLevelCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionVarianceLevelCssLayout);

		GtnUIFrameworkComponentConfig projectionVarianceLevelLayout = layoutConfig.getHorizontalLayoutConfig("level",
				projectionVarianceLevelCssLayout.getComponentId());
		componentList.add(projectionVarianceLevelLayout);

		GtnUIFrameworkComponentConfig projectionVarianceLevelOptionGroup = new GtnUIFrameworkComponentConfig();
		projectionVarianceLevelOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionVarianceLevelOptionGroup.setComponentId("projectionVarianceLevelOptionGroup");
		projectionVarianceLevelOptionGroup.setComponentName("Level :");
		projectionVarianceLevelOptionGroup.setAddToParent(Boolean.TRUE);
		projectionVarianceLevelOptionGroup.setParentComponentId(projectionVarianceLevelLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig projectionVarianceLevelOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionVarianceLevelOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Total", "Detail" }));
		projectionVarianceLevelOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		projectionVarianceLevelOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionVarianceLevelOptionGroup.setGtnUIFrameworkOptionGroupConfig(projectionVarianceLevelOptionGroupConfig);

		componentList.add(projectionVarianceLevelOptionGroup);

	}

	private void addDiscountLevelOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionVarianceDiscountLevelCssLayout = layoutConfig
				.getCssLayoutConfig("projectionResultsVariables", parentId);
		projectionVarianceDiscountLevelCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionVarianceDiscountLevelCssLayout);

		GtnUIFrameworkComponentConfig projectionVarianceDiscountLevelLayout = layoutConfig
				.getHorizontalLayoutConfig("discountLevel", projectionVarianceDiscountLevelCssLayout.getComponentId());
		componentList.add(projectionVarianceDiscountLevelLayout);

		GtnUIFrameworkComponentConfig projectionVarianceDiscountLevelOptionGroup = new GtnUIFrameworkComponentConfig();
		projectionVarianceDiscountLevelOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionVarianceDiscountLevelOptionGroup.setComponentId("projectionVarianceDiscountLevelOptionGroup");
		projectionVarianceDiscountLevelOptionGroup.setComponentName("Discount Level :");
		projectionVarianceDiscountLevelOptionGroup.setAddToParent(Boolean.TRUE);
		projectionVarianceDiscountLevelOptionGroup
				.setParentComponentId(projectionVarianceDiscountLevelLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig projectionVarianceDiscountLevelOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionVarianceDiscountLevelOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Total Discount", "Program Catagory", "Program" }));
		projectionVarianceDiscountLevelOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		projectionVarianceDiscountLevelOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionVarianceDiscountLevelOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(projectionVarianceDiscountLevelOptionGroupConfig);

		componentList.add(projectionVarianceDiscountLevelOptionGroup);

	}

	private void addVariableCategory(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionVarianceVariableCategoryCssLayout = layoutConfig
				.getCssLayoutConfig("variableCategory", parentId);
		projectionVarianceVariableCategoryCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionVarianceVariableCategoryCssLayout);

		GtnUIFrameworkComponentConfig projectionVarianceVariableCategoryLayout = layoutConfig.getHorizontalLayoutConfig(
				"variableCategory", projectionVarianceVariableCategoryCssLayout.getComponentId());
		componentList.add(projectionVarianceVariableCategoryLayout);

		GtnUIFrameworkComponentConfig projectionVarianceVariableCategoryOptionGroup = new GtnUIFrameworkComponentConfig();
		projectionVarianceVariableCategoryOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionVarianceVariableCategoryOptionGroup.setComponentId("projectionVarianceVariableCategoryOptionGroup");
		projectionVarianceVariableCategoryOptionGroup.setComponentName("Variable Category :");
		projectionVarianceVariableCategoryOptionGroup.setAddToParent(Boolean.TRUE);
		projectionVarianceVariableCategoryOptionGroup
				.setParentComponentId(projectionVarianceVariableCategoryLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig projectionVarianceVariableCategoryLayoutConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionVarianceVariableCategoryLayoutConfig.setIsMultiSelect(true);
		projectionVarianceVariableCategoryLayoutConfig
				.setItemValues(Arrays.asList(new String[] { "Value", "Variance", " % Change" }));
		projectionVarianceVariableCategoryLayoutConfig.setValuesFromService(Boolean.FALSE);
		projectionVarianceVariableCategoryOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionVarianceVariableCategoryOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(projectionVarianceVariableCategoryLayoutConfig);

		componentList.add(projectionVarianceVariableCategoryOptionGroup);

	}

	private void addProjectionPeriodOrder(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig projectionPeriodOrderCssLayout = layoutConfig
				.getCssLayoutConfig("projectionPeriodOrder1", parentId);
		projectionPeriodOrderCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionPeriodOrderCssLayout);

		GtnUIFrameworkComponentConfig projectionPeriodOrderLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionPeriodOrder1", projectionPeriodOrderCssLayout.getComponentId());
		componentList.add(projectionPeriodOrderLayout);

		GtnUIFrameworkComponentConfig projectionVariancePeriodOrderOptionGroup = new GtnUIFrameworkComponentConfig();
		projectionVariancePeriodOrderOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionVariancePeriodOrderOptionGroup.setComponentId("projectionVariancePeriodOrderOptionGroup");
		projectionVariancePeriodOrderOptionGroup.setComponentName("Projection Period Order :");
		projectionVariancePeriodOrderOptionGroup.setAddToParent(Boolean.TRUE);
		projectionVariancePeriodOrderOptionGroup.setParentComponentId(projectionPeriodOrderLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig projectionVariancePeriodOrderOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionVariancePeriodOrderOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Ascending", "Descending" }));
		projectionVariancePeriodOrderOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		projectionVariancePeriodOrderOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionVariancePeriodOrderOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(projectionVariancePeriodOrderOptionGroupConfig);
		componentList.add(projectionVariancePeriodOrderOptionGroup);

	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionResultsFrequencyCssLayout = layoutConfig
				.getCssLayoutConfig("projectionResultsFrequency", parentId);
		projectionResultsFrequencyCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionResultsFrequencyCssLayout);

		GtnUIFrameworkComponentConfig projectionResultsFrequencyLayout = layoutConfig.getHorizontalLayoutConfig(
				"projectionResultsFrequency", projectionResultsFrequencyCssLayout.getComponentId());
		componentList.add(projectionResultsFrequencyLayout);

		GtnUIFrameworkComponentConfig projectionResultsFrequencyComboBox = new GtnUIFrameworkComponentConfig();
		projectionResultsFrequencyComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		projectionResultsFrequencyComboBox.setComponentId("projectionResultsFrequency");
		projectionResultsFrequencyComboBox.setComponentName("Frequency");
		projectionResultsFrequencyComboBox.setAddToParent(Boolean.TRUE);
		projectionResultsFrequencyComboBox.setParentComponentId(projectionResultsFrequencyLayout.getComponentId());

		GtnUIFrameworkComboBoxConfig projectionResultsFrequencyConfig = new GtnUIFrameworkComboBoxConfig();
		projectionResultsFrequencyConfig.setComboBoxType("PAYMENT_FREQUENCY");
		projectionResultsFrequencyConfig.setHasDefaultValue(Boolean.TRUE);
		projectionResultsFrequencyComboBox.setGtnComboboxConfig(projectionResultsFrequencyConfig);
		componentList.add(projectionResultsFrequencyComboBox);
	}

	private void addVariables(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionResultsVariablesCssLayout = layoutConfig
				.getCssLayoutConfig("projectionResultsVariables", parentId);
		projectionResultsVariablesCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionResultsVariablesCssLayout);

		GtnUIFrameworkComponentConfig projectionResultsVariablesLayout = layoutConfig.getHorizontalLayoutConfig(
				"projectionResultsVariables", projectionResultsVariablesCssLayout.getComponentId());
		componentList.add(projectionResultsVariablesLayout);

		GtnUIFrameworkComponentConfig projectionResultsVariablesComboBox = new GtnUIFrameworkComponentConfig();
		projectionResultsVariablesComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		projectionResultsVariablesComboBox.setComponentId("projectionResultsVariables");
		projectionResultsVariablesComboBox.setComponentName("Variables :");
		projectionResultsVariablesComboBox.setAddToParent(Boolean.TRUE);
		projectionResultsVariablesComboBox.setParentComponentId(projectionResultsVariablesLayout.getComponentId());

		GtnUIFrameworkComboBoxConfig projectionResultsVariablesComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		projectionResultsVariablesComboBoxConfig.setComboBoxType("PAYMENT_FREQUENCY");
		projectionResultsVariablesComboBoxConfig.setHasDefaultValue(Boolean.TRUE);
		projectionResultsVariablesComboBox.setGtnComboboxConfig(projectionResultsVariablesComboBoxConfig);
		componentList.add(projectionResultsVariablesComboBox);
	}

	private void addPivotView(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionVariancePivotViewCssLayout = layoutConfig
				.getCssLayoutConfig("projectionVariancePivotView", parentId);
		projectionVariancePivotViewCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionVariancePivotViewCssLayout);

		GtnUIFrameworkComponentConfig projectionVariancePivotViewLayout = layoutConfig.getHorizontalLayoutConfig(
				"projectionVariancePivotView", projectionVariancePivotViewCssLayout.getComponentId());
		componentList.add(projectionVariancePivotViewLayout);

		GtnUIFrameworkComponentConfig projectionVariancePivotViewOptionGroup = new GtnUIFrameworkComponentConfig();
		projectionVariancePivotViewOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionVariancePivotViewOptionGroup.setComponentId("projectionVariancePivotViewOptionGroup");
		projectionVariancePivotViewOptionGroup.setComponentName("Pivot View :");
		projectionVariancePivotViewOptionGroup.setAddToParent(Boolean.TRUE);
		projectionVariancePivotViewOptionGroup.setParentComponentId(projectionVariancePivotViewLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig projectionVariancePivotViewOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionVariancePivotViewOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Period", "Variable" }));
		projectionVariancePivotViewOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		projectionVariancePivotViewOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionVariancePivotViewOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(projectionVariancePivotViewOptionGroupConfig);

		componentList.add(projectionVariancePivotViewOptionGroup);
	}

	private void addDateRange(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionResultsFromDateRangeCssLayout = layoutConfig
				.getCssLayoutConfig("projectionResultsFromDate", parentId);
		projectionResultsFromDateRangeCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionResultsFromDateRangeCssLayout);

		GtnUIFrameworkComponentConfig projectionResultsFromDateRangeLayout = layoutConfig.getHorizontalLayoutConfig(
				"projectionResultsFromDate", projectionResultsFromDateRangeCssLayout.getComponentId());
		componentList.add(projectionResultsFromDateRangeLayout);

		GtnUIFrameworkComponentConfig projectionResultsDateLabel = new GtnUIFrameworkComponentConfig();
		projectionResultsDateLabel.setComponentType(GtnUIFrameworkComponentType.LABEL);
		projectionResultsDateLabel.setComponentId("projectionResultsDateRange");
		projectionResultsDateLabel.setComponentValue("Date Range :");
		projectionResultsDateLabel.setAddToParent(Boolean.TRUE);
		projectionResultsDateLabel.setParentComponentId(projectionResultsFromDateRangeLayout.getComponentId());
		componentList.add(projectionResultsDateLabel);

	}

	private void addFromDate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionResultsFromDateCssLayout = layoutConfig
				.getCssLayoutConfig("projectionResultsFromDate", parentId);
		projectionResultsFromDateCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionResultsFromDateCssLayout);

		GtnUIFrameworkComponentConfig projectionResultsFromDateLayout = layoutConfig.getHorizontalLayoutConfig(
				"projectionResultsFromDate", projectionResultsFromDateCssLayout.getComponentId());
		componentList.add(projectionResultsFromDateLayout);

		GtnUIFrameworkComponentConfig projectionResultsFromDateComboBox = new GtnUIFrameworkComponentConfig();
		projectionResultsFromDateComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		projectionResultsFromDateComboBox.setComponentId("projectionResultsFromDate");
		projectionResultsFromDateComboBox.setComponentName("From Date :");
		projectionResultsFromDateComboBox.setAddToParent(Boolean.TRUE);
		projectionResultsFromDateComboBox.setParentComponentId(projectionResultsFromDateLayout.getComponentId());

		GtnUIFrameworkComboBoxConfig projectionResultsFromDateConfig = new GtnUIFrameworkComboBoxConfig();
		projectionResultsFromDateConfig.setComboBoxType("PAYMENT_FREQUENCY");
		projectionResultsFromDateConfig.setHasDefaultValue(Boolean.TRUE);
		projectionResultsFromDateComboBox.setGtnComboboxConfig(projectionResultsFromDateConfig);
		componentList.add(projectionResultsFromDateComboBox);
	}

	private void addToDate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionResultsToDateCssLayout = layoutConfig
				.getCssLayoutConfig("projectionResultsToDate", parentId);
		projectionResultsToDateCssLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(projectionResultsToDateCssLayout);

		GtnUIFrameworkComponentConfig projectionResultsToDateLayout = layoutConfig.getHorizontalLayoutConfig(
				"projectionResultsToDate", projectionResultsToDateCssLayout.getComponentId());
		componentList.add(projectionResultsToDateLayout);

		GtnUIFrameworkComponentConfig projectionResultsToDateComboBox = new GtnUIFrameworkComponentConfig();
		projectionResultsToDateComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		projectionResultsToDateComboBox.setComponentId("projectionResultsToDate");
		projectionResultsToDateComboBox.setComponentName("To Date :");
		projectionResultsToDateComboBox.setAddToParent(Boolean.TRUE);
		projectionResultsToDateComboBox.setParentComponentId(projectionResultsToDateLayout.getComponentId());

		GtnUIFrameworkComboBoxConfig projectionResultsToDateConfig = new GtnUIFrameworkComboBoxConfig();
		projectionResultsToDateConfig.setComboBoxType("PAYMENT_FREQUENCY");
		projectionResultsToDateConfig.setHasDefaultValue(Boolean.TRUE);
		projectionResultsToDateComboBox.setGtnComboboxConfig(projectionResultsToDateConfig);
		componentList.add(projectionResultsToDateComboBox);
	}

	private void addResetAndGenerate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		String tabName = "projectVarianceTab";
		GtnUIFrameworkComponentConfig resetAndGeneratePanel = layoutConfig
				.getPanelConfig(tabName + "resetAndGeneratePanel", parentId);
		resetAndGeneratePanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(resetAndGeneratePanel);

		GtnUIFrameworkComponentConfig resetAndGenerateMainLayout = layoutConfig
				.getHorizontalLayoutConfig(tabName + "resetAndGenerateMain", resetAndGeneratePanel.getComponentId());
		componentList.add(resetAndGenerateMainLayout);

		GtnUIFrameworkComponentConfig resetAndGenerateLayout = layoutConfig
				.getHorizontalLayoutConfig(tabName + "resetAndGenerate", resetAndGenerateMainLayout.getComponentId());
		componentList.add(resetAndGenerateLayout);

		GtnUIFrameworkComponentConfig projectionVarianceGenerateButton = new GtnUIFrameworkComponentConfig();
		projectionVarianceGenerateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		projectionVarianceGenerateButton.setComponentId(tabName + "projectionVarianceGenerateBtn");
		projectionVarianceGenerateButton.setComponentName("GENERATE");
		projectionVarianceGenerateButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		projectionVarianceGenerateButton.setAddToParent(true);
		componentList.add(projectionVarianceGenerateButton);

		GtnUIFrameworkComponentConfig projectionVarianceResetButton = new GtnUIFrameworkComponentConfig();
		projectionVarianceResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		projectionVarianceResetButton.setComponentId(tabName + "projectionVarianceResetBtn");
		projectionVarianceResetButton.setComponentName("RESET");
		projectionVarianceResetButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		projectionVarianceResetButton.setAddToParent(Boolean.TRUE);
		componentList.add(projectionVarianceResetButton);

		GtnFrameworkExpandCollapseLevelSection conf = new GtnFrameworkExpandCollapseLevelSection();
		conf.getRootExpandCollapseSectionLayout(componentList, resetAndGenerateMainLayout.getComponentId(),
				"forecastCommercial", tabName);

	}

	private void addProjectionVariancePanel(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig projectionVariancePanel = layoutConfig.getPanelConfig("projectionVarianceTable",
				componentId);
		projectionVariancePanel.setComponentName("Period Pivot View");
		projectionVariancePanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(projectionVariancePanel);

		addResultTable(componentList, projectionVariancePanel.getComponentId());
	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId("projectionVarianceResultTable");
		resultTableComponentConfig.setComponentName("resultTable");
		resultTableComponentConfig.setAddToParent(Boolean.TRUE);
		resultTableComponentConfig.setParentComponentId(componentId);

		GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add("projectionVarianceTablePanel");
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(actionConfigList);
		gtnPagedTreeTableConfig.setGtnUIFrameWorkActionConfig(gtnUIFrameWorkActionConfig);

		gtnPagedTreeTableConfig.setLeftHeader("/getLeftHeaders");
		gtnPagedTreeTableConfig.setRightHeader("/getRightHeaders");

		gtnPagedTreeTableConfig.setCountUrl("");
		gtnPagedTreeTableConfig.setItemPerPage(10);

		gtnPagedTreeTableConfig.setMaxSplitPosition(1000);
		gtnPagedTreeTableConfig.setMinSplitPosition(300);
		gtnPagedTreeTableConfig.setPageLength(15);
		gtnPagedTreeTableConfig.setResultSetUrl("");

		gtnPagedTreeTableConfig.setSplitPosition(600);

		gtnPagedTreeTableConfig.setTableHeight("650px");
		gtnPagedTreeTableConfig.setDoubleHeaderVisible(Boolean.TRUE);

		gtnPagedTreeTableConfig.setLeftTableEditable(true);
		gtnPagedTreeTableConfig.setRightTableEditable(true);
		gtnPagedTreeTableConfig.setLeftHeaderCustomClassLoadUrl(
				"com.stpl.gtn.gtn2o.ui.module.commercial.action.GtnFrameworkConfigureLeftTableHeaderForPTTCompoAction");
		gtnPagedTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				"com.stpl.gtn.gtn2o.ui.module.commercial.action.GtnFrameworkConfigureRightTableHeaderForPTTCompoAction");
		gtnPagedTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);

		resultTableComponentConfig.setGtnPagedTreeTableConfig(gtnPagedTreeTableConfig);

		componentList.add(resultTableComponentConfig);
	}

	private void addResetRefreshButtons(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig projectionresultsExcelButtonLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionVarianceExcel", parentId);
		componentList.add(projectionresultsExcelButtonLayout);
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setComponentId("projectionVarianceResultTableExcelBtn");
		excelButtonConfig.setParentComponentId(projectionresultsExcelButtonLayout.getComponentId());
		excelButtonConfig.setAddToParent(true);
		componentList.add(excelButtonConfig);

	}
}
