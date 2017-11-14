/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.ppa;

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
public class GtnFrameworkCommercialForecastingPPATabConfig {
	private GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

	public void addPPAProjectionResultsTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutConfig.getRootLayoutConfig(
				"commercialForecastingPPAProjectionResultsRootLayout", GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rootLayoutConfig.setSpacing(Boolean.TRUE);
		componentList.add(rootLayoutConfig);
		addPPAProjectionResultsSelection(componentList, rootLayoutConfig.getComponentId());
		addResetAndGenerate(componentList, rootLayoutConfig.getComponentId());
		addPPAProjectionResultsPanel(componentList, rootLayoutConfig.getComponentId());
		addResetRefreshButtons(componentList, rootLayoutConfig.getComponentId());
	}

	private void addPPAProjectionResultsSelection(List<GtnUIFrameworkComponentConfig> componentList,
			String componentId) {
		GtnUIFrameworkComponentConfig ppaProjectionResultsSelectionPanel = layoutConfig
				.getPanelConfig("ppaProjectionResultsSelection", componentId);
		ppaProjectionResultsSelectionPanel.setComponentName("PPA Projection Results Selection ");
		ppaProjectionResultsSelectionPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(ppaProjectionResultsSelectionPanel);

		GtnUIFrameworkComponentConfig ppaProjectionResultsSelectionPanelLayout = layoutConfig.getCssLayoutConfig(
				"ppaProjectionResultsSelection", ppaProjectionResultsSelectionPanel.getComponentId());
		ppaProjectionResultsSelectionPanelLayout.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(ppaProjectionResultsSelectionPanelLayout);

		GtnUIFrameworkComponentConfig ppaProjectionResultsFrequencySalesUnitsCssLayout = layoutConfig
				.getCssLayoutConfig("ppaProjectionResultsFrequencySalesUnits",
						ppaProjectionResultsSelectionPanelLayout.getComponentId());
		ppaProjectionResultsFrequencySalesUnitsCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		ppaProjectionResultsFrequencySalesUnitsCssLayout.setComponentWidth("75%");
		componentList.add(ppaProjectionResultsFrequencySalesUnitsCssLayout);

		GtnUIFrameworkComponentConfig ppaProjectionResultsHistoryProjectionPeriodOrderCssLayout = layoutConfig
				.getCssLayoutConfig("ppaProjectionResultsHistoryProjectionPeriodOrder",
						ppaProjectionResultsSelectionPanelLayout.getComponentId());
		ppaProjectionResultsHistoryProjectionPeriodOrderCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		ppaProjectionResultsHistoryProjectionPeriodOrderCssLayout.setComponentWidth("75%");
		componentList.add(ppaProjectionResultsHistoryProjectionPeriodOrderCssLayout);

		addFrequency(componentList, ppaProjectionResultsFrequencySalesUnitsCssLayout.getComponentId());
		addProjectionPeriodOrder(componentList, ppaProjectionResultsFrequencySalesUnitsCssLayout.getComponentId());
		addActualsProjection(componentList, ppaProjectionResultsFrequencySalesUnitsCssLayout.getComponentId());
		addHistory(componentList, ppaProjectionResultsHistoryProjectionPeriodOrderCssLayout.getComponentId());
		addPivotView(componentList, ppaProjectionResultsHistoryProjectionPeriodOrderCssLayout.getComponentId());

	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig ppaFrequencyLayout = layoutConfig
				.getHorizontalLayoutConfig("ppaProjectionResultsFrequency", parentId);
		componentList.add(ppaFrequencyLayout);

		GtnUIFrameworkComponentConfig ppaFrequencyComboBox = new GtnUIFrameworkComponentConfig();
		ppaFrequencyComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		ppaFrequencyComboBox.setComponentId("ppaProjectionResultsFrequency");
		ppaFrequencyComboBox.setComponentName("Frequency");
		ppaFrequencyComboBox.setAddToParent(Boolean.TRUE);
		ppaFrequencyComboBox.setParentComponentId(ppaFrequencyLayout.getComponentId());
		ppaFrequencyComboBox.addDependentComponent("ppaProjectionResultsHistory");

		GtnUIFrameworkComboBoxConfig ppaFrequencyConfig = new GtnUIFrameworkComboBoxConfig();
		ppaFrequencyConfig.setComboBoxType("PAYMENT_FREQUENCY");
		ppaFrequencyConfig.setHasDefaultValue(Boolean.TRUE);
		ppaFrequencyComboBox.setGtnComboboxConfig(ppaFrequencyConfig);
		componentList.add(ppaFrequencyComboBox);
	}

	private void addProjectionPeriodOrder(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig ppaProjectionPeriodOrderLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionPeriodOrder1", parentId);
		componentList.add(ppaProjectionPeriodOrderLayout);

		GtnUIFrameworkComponentConfig ppaPeriodOrderOptionGroup = new GtnUIFrameworkComponentConfig();
		ppaPeriodOrderOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		ppaPeriodOrderOptionGroup.setComponentId("ppaProjectionResultsPeriodOrderOptionGroup");
		ppaPeriodOrderOptionGroup.setComponentName("Projection Period Order :");
		ppaPeriodOrderOptionGroup.setAddToParent(Boolean.TRUE);
		ppaPeriodOrderOptionGroup.setParentComponentId(ppaProjectionPeriodOrderLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig ppaPeriodOrderOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		ppaPeriodOrderOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Ascending", "Descending" }));
		ppaPeriodOrderOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		ppaPeriodOrderOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		ppaPeriodOrderOptionGroup.setGtnUIFrameworkOptionGroupConfig(ppaPeriodOrderOptionGroupConfig);
		componentList.add(ppaPeriodOrderOptionGroup);

	}

	private void addHistory(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig ppaProjectionResultsHistoryLayout = layoutConfig
				.getHorizontalLayoutConfig("ppaProjectionResultsHistory", parentId);
		componentList.add(ppaProjectionResultsHistoryLayout);

		GtnUIFrameworkComponentConfig ppaProjectionResultsHistoryComponentConfig = new GtnUIFrameworkComponentConfig();
		ppaProjectionResultsHistoryComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		ppaProjectionResultsHistoryComponentConfig.setComponentId("ppaProjectionResultsHistory");
		ppaProjectionResultsHistoryComponentConfig.setComponentName("History");
		ppaProjectionResultsHistoryComponentConfig.setAddToParent(Boolean.TRUE);
		ppaProjectionResultsHistoryComponentConfig
				.setParentComponentId(ppaProjectionResultsHistoryLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig historyConfig = new GtnUIFrameworkComboBoxConfig();
		historyConfig.setComboBoxType("PAYMENT_FREQUENCY");
		historyConfig.setHasDefaultValue(Boolean.TRUE);
		ppaProjectionResultsHistoryComponentConfig.setGtnComboboxConfig(historyConfig);
		componentList.add(ppaProjectionResultsHistoryComponentConfig);
	}

	private void addActualsProjection(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig ppaProjectionResultsActualProjectionLayout = layoutConfig
				.getHorizontalLayoutConfig("ppaProjectionResultsActualsProjection", parentId);
		componentList.add(ppaProjectionResultsActualProjectionLayout);

		GtnUIFrameworkComponentConfig ppaActualsProjectionOptionGroup = new GtnUIFrameworkComponentConfig();
		ppaActualsProjectionOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		ppaActualsProjectionOptionGroup.setComponentId("ppaProjectionResultsActualsProjectionOptionGroup");
		ppaActualsProjectionOptionGroup.setComponentName("Actuals/Projections:");
		ppaActualsProjectionOptionGroup.setAddToParent(Boolean.TRUE);
		ppaActualsProjectionOptionGroup
				.setParentComponentId(ppaProjectionResultsActualProjectionLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig actualsProjectionOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		actualsProjectionOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Actuals", "Projections", "Both" }));
		actualsProjectionOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		ppaActualsProjectionOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		ppaActualsProjectionOptionGroup.setGtnUIFrameworkOptionGroupConfig(actualsProjectionOptionGroupConfig);

		componentList.add(ppaActualsProjectionOptionGroup);
	}

	private void addPivotView(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig ppaProjectionResultsPivotViewLayout = layoutConfig
				.getHorizontalLayoutConfig("ppaProjectionResultsPivotView", parentId);
		componentList.add(ppaProjectionResultsPivotViewLayout);

		GtnUIFrameworkComponentConfig ppaPivotViewOptionGroup = new GtnUIFrameworkComponentConfig();
		ppaPivotViewOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		ppaPivotViewOptionGroup.setComponentId("ppaProjectionResultsPivotViewOptionGroup");
		ppaPivotViewOptionGroup.setComponentName("Pivot View :");
		ppaPivotViewOptionGroup.setAddToParent(Boolean.TRUE);
		ppaPivotViewOptionGroup.setParentComponentId(ppaProjectionResultsPivotViewLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig ppaPivotViewOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		ppaPivotViewOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Period", "Variable" }));
		ppaPivotViewOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		ppaPivotViewOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		ppaPivotViewOptionGroup.setGtnUIFrameworkOptionGroupConfig(ppaPivotViewOptionGroupConfig);

		componentList.add(ppaPivotViewOptionGroup);
	}

	private void addResetAndGenerate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		String tabName = "ppaProjectionResultsTab";

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

		GtnUIFrameworkComponentConfig ppaProjectionResultsGenerateButton = new GtnUIFrameworkComponentConfig();
		ppaProjectionResultsGenerateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		ppaProjectionResultsGenerateButton.setComponentId(tabName + "ppaProjectionResultsGenerateBtn");
		ppaProjectionResultsGenerateButton.setComponentName("GENERATE");
		ppaProjectionResultsGenerateButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		ppaProjectionResultsGenerateButton.setAddToParent(true);
		componentList.add(ppaProjectionResultsGenerateButton);

		GtnUIFrameworkComponentConfig ppaProjectionResultsResetButton = new GtnUIFrameworkComponentConfig();
		ppaProjectionResultsResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		ppaProjectionResultsResetButton.setComponentId(tabName + "ppaProjectionResultsResetBtn");
		ppaProjectionResultsResetButton.setComponentName("RESET");
		ppaProjectionResultsResetButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		ppaProjectionResultsResetButton.setAddToParent(Boolean.TRUE);
		componentList.add(ppaProjectionResultsResetButton);

		GtnFrameworkExpandCollapseLevelSection conf = new GtnFrameworkExpandCollapseLevelSection();
		conf.getRootExpandCollapseSectionLayout(componentList, resetAndGenerateMainLayout.getComponentId(),
				"forecastCommercial", tabName);

	}

	private void addPPAProjectionResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig ppaProjectionResultsPanel = layoutConfig
				.getPanelConfig("ppaProjectionResultTable", componentId);
		ppaProjectionResultsPanel.setComponentName("Period Pivot View");
		ppaProjectionResultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(ppaProjectionResultsPanel);

		addResultTable(componentList, ppaProjectionResultsPanel.getComponentId());
	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId("ppaProjectionResultsResultTable");
		resultTableComponentConfig.setComponentName("resultTable");
		resultTableComponentConfig.setAddToParent(Boolean.TRUE);
		resultTableComponentConfig.setParentComponentId(componentId);

		GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add("ppaProjectionResultTablePanel");
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
		GtnUIFrameworkComponentConfig ppaProjectionresultsExcelButtonLayout = layoutConfig
				.getHorizontalLayoutConfig("ppaProjectionResultsExcel", parentId);
		componentList.add(ppaProjectionresultsExcelButtonLayout);
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setComponentId("ppaProjectionResultsResultTableExcelBtn");
		excelButtonConfig.setParentComponentId(ppaProjectionresultsExcelButtonLayout.getComponentId());
		excelButtonConfig.setAddToParent(true);
		componentList.add(excelButtonConfig);

	}
}
