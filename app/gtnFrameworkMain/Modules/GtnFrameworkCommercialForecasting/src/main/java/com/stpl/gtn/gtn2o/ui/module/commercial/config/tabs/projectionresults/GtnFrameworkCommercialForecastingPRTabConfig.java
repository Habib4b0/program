/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.projectionresults;

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
public class GtnFrameworkCommercialForecastingPRTabConfig {
	private GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

	public void addProjectionResultsTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutConfig.getRootLayoutConfig(
				"commercialForecastingProjectionResultsRootLayout", GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rootLayoutConfig.setSpacing(Boolean.TRUE);
		componentList.add(rootLayoutConfig);
		addProjectionResultsSelection(componentList, rootLayoutConfig.getComponentId());
		addResetAndGenerate(componentList, rootLayoutConfig.getComponentId());
		addProjectionResultsPanel(componentList, rootLayoutConfig.getComponentId());
		addResetRefreshButtons(componentList, rootLayoutConfig.getComponentId());
	}

	private void addProjectionResultsSelection(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig projectionResultsSelectionPanel = layoutConfig
				.getPanelConfig("projectionResultsSelection", componentId);
		projectionResultsSelectionPanel.setComponentName(" Projection Results Selection ");
		projectionResultsSelectionPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(projectionResultsSelectionPanel);

		GtnUIFrameworkComponentConfig projectionResultsSelectionPanelLayout = layoutConfig
				.getCssLayoutConfig("projectionResultsSelection", projectionResultsSelectionPanel.getComponentId());
		projectionResultsSelectionPanelLayout.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(projectionResultsSelectionPanelLayout);

		GtnUIFrameworkComponentConfig projectionResultsFrequencySalesUnitsCssLayout = layoutConfig.getCssLayoutConfig(
				"projectionResultsFrequencySalesUnits", projectionResultsSelectionPanelLayout.getComponentId());
		projectionResultsFrequencySalesUnitsCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		projectionResultsFrequencySalesUnitsCssLayout.setComponentWidth("75%");
		componentList.add(projectionResultsFrequencySalesUnitsCssLayout);

		GtnUIFrameworkComponentConfig projectionResultsHistoryProjectionPeriodOrderCssLayout = layoutConfig
				.getCssLayoutConfig("projectionResultsHistoryProjectionPeriodOrder",
						projectionResultsSelectionPanelLayout.getComponentId());
		projectionResultsHistoryProjectionPeriodOrderCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		projectionResultsHistoryProjectionPeriodOrderCssLayout.setComponentWidth("75%");
		componentList.add(projectionResultsHistoryProjectionPeriodOrderCssLayout);

		addFrequency(componentList, projectionResultsFrequencySalesUnitsCssLayout.getComponentId());
		addSalesUnits(componentList, projectionResultsFrequencySalesUnitsCssLayout.getComponentId());
		addProjectionPeriodOrder(componentList,
				projectionResultsHistoryProjectionPeriodOrderCssLayout.getComponentId());
		addHistory(componentList, projectionResultsHistoryProjectionPeriodOrderCssLayout.getComponentId());
		addActualsProjection(componentList, projectionResultsFrequencySalesUnitsCssLayout.getComponentId());
		addPivotView(componentList, projectionResultsHistoryProjectionPeriodOrderCssLayout.getComponentId());

	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionResultsFrequencyLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionResultsFrequency", parentId);
		componentList.add(projectionResultsFrequencyLayout);

		GtnUIFrameworkComponentConfig projectionResultsFrequencyComboBox = new GtnUIFrameworkComponentConfig();
		projectionResultsFrequencyComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		projectionResultsFrequencyComboBox.setComponentId("projectionResultsFrequency");
		projectionResultsFrequencyComboBox.setComponentName("Frequency");
		projectionResultsFrequencyComboBox.setAddToParent(Boolean.TRUE);
		projectionResultsFrequencyComboBox.setParentComponentId(projectionResultsFrequencyLayout.getComponentId());
		projectionResultsFrequencyComboBox.addDependentComponent("projectionResultsHistory");

		GtnUIFrameworkComboBoxConfig projectionResultsFrequencyConfig = new GtnUIFrameworkComboBoxConfig();
		projectionResultsFrequencyConfig.setComboBoxType("PAYMENT_FREQUENCY");
		projectionResultsFrequencyConfig.setHasDefaultValue(Boolean.TRUE);
		projectionResultsFrequencyComboBox.setGtnComboboxConfig(projectionResultsFrequencyConfig);
		componentList.add(projectionResultsFrequencyComboBox);
	}

	private void addSalesUnits(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig projectionresultsSalesUnitLayout = layoutConfig
				.getHorizontalLayoutConfig("salesUnit", parentId);
		componentList.add(projectionresultsSalesUnitLayout);

		GtnUIFrameworkComponentConfig projectionResultsSalesUnitsOptionGroup = new GtnUIFrameworkComponentConfig();
		projectionResultsSalesUnitsOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionResultsSalesUnitsOptionGroup.setComponentId("projectionResultsSalesUnitOptionGroup");
		projectionResultsSalesUnitsOptionGroup.setComponentName("Sales/Units:");
		projectionResultsSalesUnitsOptionGroup.setAddToParent(Boolean.TRUE);
		projectionResultsSalesUnitsOptionGroup.setParentComponentId(projectionresultsSalesUnitLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig projectionResultsSalesUnitsOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionResultsSalesUnitsOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Sales", "Units", "Both" }));
		projectionResultsSalesUnitsOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		projectionResultsSalesUnitsOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionResultsSalesUnitsOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(projectionResultsSalesUnitsOptionGroupConfig);

		componentList.add(projectionResultsSalesUnitsOptionGroup);

	}

	private void addProjectionPeriodOrder(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig projectionPeriodOrderLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionPeriodOrder1", parentId);
		componentList.add(projectionPeriodOrderLayout);

		GtnUIFrameworkComponentConfig projectionResultsPeriodOrderOptionGroup = new GtnUIFrameworkComponentConfig();
		projectionResultsPeriodOrderOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionResultsPeriodOrderOptionGroup.setComponentId("projectionResultsPeriodOrderOptionGroup");
		projectionResultsPeriodOrderOptionGroup.setComponentName("Projection Period Order :");
		projectionResultsPeriodOrderOptionGroup.setAddToParent(Boolean.TRUE);
		projectionResultsPeriodOrderOptionGroup.setParentComponentId(projectionPeriodOrderLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig projectionResultsPeriodOrderOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionResultsPeriodOrderOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Ascending", "Descending" }));
		projectionResultsPeriodOrderOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		projectionResultsPeriodOrderOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionResultsPeriodOrderOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(projectionResultsPeriodOrderOptionGroupConfig);
		componentList.add(projectionResultsPeriodOrderOptionGroup);

	}

	private void addHistory(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig projectionResultsHistoryLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionResultsHistory", parentId);
		componentList.add(projectionResultsHistoryLayout);

		GtnUIFrameworkComponentConfig projectionResultsHistoryComponentConfig = new GtnUIFrameworkComponentConfig();
		projectionResultsHistoryComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		projectionResultsHistoryComponentConfig.setComponentId("projectionResultsHistory");
		projectionResultsHistoryComponentConfig.setComponentName("History");
		projectionResultsHistoryComponentConfig.setAddToParent(Boolean.TRUE);
		projectionResultsHistoryComponentConfig.setParentComponentId(projectionResultsHistoryLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig historyConfig = new GtnUIFrameworkComboBoxConfig();
		historyConfig.setComboBoxType("PAYMENT_FREQUENCY");
		historyConfig.setHasDefaultValue(Boolean.TRUE);
		projectionResultsHistoryComponentConfig.setGtnComboboxConfig(historyConfig);
		componentList.add(projectionResultsHistoryComponentConfig);
	}

	private void addActualsProjection(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig projectionResultsActualProjectionLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionResultsActualsProjection", parentId);
		componentList.add(projectionResultsActualProjectionLayout);

		GtnUIFrameworkComponentConfig projectionResultsActualsProjectionOptionGroup = new GtnUIFrameworkComponentConfig();
		projectionResultsActualsProjectionOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionResultsActualsProjectionOptionGroup.setComponentId("projectionResultsActualsProjectionOptionGroup");
		projectionResultsActualsProjectionOptionGroup.setComponentName("Actuals/Projections:");
		projectionResultsActualsProjectionOptionGroup.setAddToParent(Boolean.TRUE);
		projectionResultsActualsProjectionOptionGroup
				.setParentComponentId(projectionResultsActualProjectionLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig actualsProjectionOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		actualsProjectionOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Actuals", "Projections", "Both" }));
		actualsProjectionOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		projectionResultsActualsProjectionOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionResultsActualsProjectionOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(actualsProjectionOptionGroupConfig);

		componentList.add(projectionResultsActualsProjectionOptionGroup);
	}

	private void addPivotView(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig projectionResultsPivotViewLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionResultsPivotView", parentId);
		componentList.add(projectionResultsPivotViewLayout);

		GtnUIFrameworkComponentConfig projectionResultsPivotViewOptionGroup = new GtnUIFrameworkComponentConfig();
		projectionResultsPivotViewOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionResultsPivotViewOptionGroup.setComponentId("projectionResultsPivotViewOptionGroup");
		projectionResultsPivotViewOptionGroup.setComponentName("Pivot View :");
		projectionResultsPivotViewOptionGroup.setAddToParent(Boolean.TRUE);
		projectionResultsPivotViewOptionGroup.setParentComponentId(projectionResultsPivotViewLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig projectionResultsPivotViewOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		projectionResultsPivotViewOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Period", "Variable" }));
		projectionResultsPivotViewOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		projectionResultsPivotViewOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionResultsPivotViewOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(projectionResultsPivotViewOptionGroupConfig);

		componentList.add(projectionResultsPivotViewOptionGroup);
	}

	private void addResetAndGenerate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		String tabName = "projectionResultsTab";
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

		GtnUIFrameworkComponentConfig projectionResultsGenerateButton = new GtnUIFrameworkComponentConfig();
		projectionResultsGenerateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		projectionResultsGenerateButton.setComponentId(tabName + "projectionResultsGenerateBtn");
		projectionResultsGenerateButton.setComponentName("GENERATE");
		projectionResultsGenerateButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		projectionResultsGenerateButton.setAddToParent(true);
		componentList.add(projectionResultsGenerateButton);

		GtnUIFrameworkComponentConfig projectionResultsResetButton = new GtnUIFrameworkComponentConfig();
		projectionResultsResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		projectionResultsResetButton.setComponentId(tabName + "projectionResultsResetBtn");
		projectionResultsResetButton.setComponentName("RESET");
		projectionResultsResetButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		projectionResultsResetButton.setAddToParent(Boolean.TRUE);
		componentList.add(projectionResultsResetButton);

		GtnFrameworkExpandCollapseLevelSection conf = new GtnFrameworkExpandCollapseLevelSection();
		conf.getRootExpandCollapseSectionLayout(componentList, resetAndGenerateMainLayout.getComponentId(),
				"forecastCommercial", tabName);

	}

	private void addProjectionResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig projectionResultsPanel = layoutConfig.getPanelConfig("projectionResultTable",
				componentId);
		projectionResultsPanel.setComponentName("Period Pivot View");
		projectionResultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(projectionResultsPanel);

		addResultTable(componentList, projectionResultsPanel.getComponentId());
	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId("projectionResultsResultTable");
		resultTableComponentConfig.setComponentName("resultTable");
		resultTableComponentConfig.setAddToParent(Boolean.TRUE);
		resultTableComponentConfig.setParentComponentId(componentId);

		GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add("projectionResultTablePanel");
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
				.getHorizontalLayoutConfig("projectionResultsExcel", parentId);
		componentList.add(projectionresultsExcelButtonLayout);
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setComponentId("projectionResultsResultTableExcelBtn");
		excelButtonConfig.setParentComponentId(projectionresultsExcelButtonLayout.getComponentId());
		excelButtonConfig.setAddToParent(true);
		componentList.add(excelButtonConfig);

	}
}
