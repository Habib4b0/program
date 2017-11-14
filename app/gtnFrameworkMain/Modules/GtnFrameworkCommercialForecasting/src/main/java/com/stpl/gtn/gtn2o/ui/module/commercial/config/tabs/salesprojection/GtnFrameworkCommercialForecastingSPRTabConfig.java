package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.salesprojection;

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
public class GtnFrameworkCommercialForecastingSPRTabConfig {

	private GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();

	public void addSalesProjectionResultsTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutConfig.getRootLayoutConfig(
				"commercialForecastingSalesProjectionResultsRootLayout", GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rootLayoutConfig.setSpacing(Boolean.TRUE);
		componentList.add(rootLayoutConfig);
		addSalesProjectionResultsSelection(componentList, rootLayoutConfig.getComponentId());
		addResetAndGenerate(componentList, rootLayoutConfig.getComponentId());
		addSalesProjectionResultsPanel(componentList, rootLayoutConfig.getComponentId());
		addResetRefreshButtons(componentList, rootLayoutConfig.getComponentId());
	}

	private void addSalesProjectionResultsSelection(List<GtnUIFrameworkComponentConfig> componentList,
			String componentId) {
		GtnUIFrameworkComponentConfig salesProjectionResultsSelectionPanel = layoutConfig
				.getPanelConfig("salesProjectionResultsSelection", componentId);
		salesProjectionResultsSelectionPanel.setComponentName("Sales Projection Results Selection ");
		salesProjectionResultsSelectionPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(salesProjectionResultsSelectionPanel);

		GtnUIFrameworkComponentConfig salesProjectionResultsSelectionPanelLayout = layoutConfig.getCssLayoutConfig(
				"salesProjectionResultsSelection", salesProjectionResultsSelectionPanel.getComponentId());
		salesProjectionResultsSelectionPanelLayout
				.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(salesProjectionResultsSelectionPanelLayout);

		GtnUIFrameworkComponentConfig sprFrequencySalesUnitsCssLayout = layoutConfig.getCssLayoutConfig(
				"salesProjectionResultsFrequencySalesUnits",
				salesProjectionResultsSelectionPanelLayout.getComponentId());
		sprFrequencySalesUnitsCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		sprFrequencySalesUnitsCssLayout.setComponentWidth("75%");
		componentList.add(sprFrequencySalesUnitsCssLayout);

		GtnUIFrameworkComponentConfig sprHistoryProjectionPeriodOrderCssLayout = layoutConfig.getCssLayoutConfig(
				"salesProjectionResultsHistoryProjectionPeriodOrder",
				salesProjectionResultsSelectionPanelLayout.getComponentId());
		sprHistoryProjectionPeriodOrderCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		sprHistoryProjectionPeriodOrderCssLayout.setComponentWidth("75%");
		componentList.add(sprHistoryProjectionPeriodOrderCssLayout);

		addFrequency(componentList, sprFrequencySalesUnitsCssLayout.getComponentId());
		addSalesUnits(componentList, sprFrequencySalesUnitsCssLayout.getComponentId());
		addProjectionPeriodOrder(componentList, sprFrequencySalesUnitsCssLayout.getComponentId());
		addHistory(componentList, sprHistoryProjectionPeriodOrderCssLayout.getComponentId());
		addActualsProjection(componentList, sprHistoryProjectionPeriodOrderCssLayout.getComponentId());
		addPivotView(componentList, sprHistoryProjectionPeriodOrderCssLayout.getComponentId());

	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig sprFrequencyLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionResultsFrequency", parentId);
		componentList.add(sprFrequencyLayout);

		GtnUIFrameworkComponentConfig sprFrequencyComboBox = new GtnUIFrameworkComponentConfig();
		sprFrequencyComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		sprFrequencyComboBox.setComponentId("salesProjectionResultsFrequency");
		sprFrequencyComboBox.setComponentName("Frequency");
		sprFrequencyComboBox.setAddToParent(Boolean.TRUE);
		sprFrequencyComboBox.setParentComponentId(sprFrequencyLayout.getComponentId());
		sprFrequencyComboBox.addDependentComponent("salesProjectionResultsHistory");

		GtnUIFrameworkComboBoxConfig sprFrequencyConfig = new GtnUIFrameworkComboBoxConfig();
		sprFrequencyConfig.setComboBoxType("PAYMENT_FREQUENCY");
		sprFrequencyConfig.setHasDefaultValue(Boolean.TRUE);
		sprFrequencyComboBox.setGtnComboboxConfig(sprFrequencyConfig);
		componentList.add(sprFrequencyComboBox);
	}

	private void addSalesUnits(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig sprSalesUnitLayout = layoutConfig.getHorizontalLayoutConfig("salesUnit",
				parentId);
		componentList.add(sprSalesUnitLayout);

		GtnUIFrameworkComponentConfig sprSalesUnitsOptionGroup = new GtnUIFrameworkComponentConfig();
		sprSalesUnitsOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		sprSalesUnitsOptionGroup.setComponentId("salesProjectionResultsSalesUnitOptionGroup");
		sprSalesUnitsOptionGroup.setComponentName("Sales/Units:");
		sprSalesUnitsOptionGroup.setAddToParent(Boolean.TRUE);
		sprSalesUnitsOptionGroup.setParentComponentId(sprSalesUnitLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig sprSalesUnitsOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		sprSalesUnitsOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Sales", "Units", "Both" }));
		sprSalesUnitsOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		sprSalesUnitsOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		sprSalesUnitsOptionGroup.setGtnUIFrameworkOptionGroupConfig(sprSalesUnitsOptionGroupConfig);

		componentList.add(sprSalesUnitsOptionGroup);

	}

	private void addProjectionPeriodOrder(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig sprProjectionPeriodOrderLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionPeriodOrder1", parentId);
		componentList.add(sprProjectionPeriodOrderLayout);

		GtnUIFrameworkComponentConfig sprPeriodOrderOptionGroup = new GtnUIFrameworkComponentConfig();
		sprPeriodOrderOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		sprPeriodOrderOptionGroup.setComponentId("salesProjectionResultsPeriodOrderOptionGroup");
		sprPeriodOrderOptionGroup.setComponentName("Projection Period Order :");
		sprPeriodOrderOptionGroup.setAddToParent(Boolean.TRUE);
		sprPeriodOrderOptionGroup.setParentComponentId(sprProjectionPeriodOrderLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig sprPeriodOrderOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		sprPeriodOrderOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Ascending", "Descending" }));
		sprPeriodOrderOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		sprPeriodOrderOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		sprPeriodOrderOptionGroup.setGtnUIFrameworkOptionGroupConfig(sprPeriodOrderOptionGroupConfig);
		componentList.add(sprPeriodOrderOptionGroup);

	}

	private void addHistory(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig salesProjectionResultsHistoryLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionResultsHistory", parentId);
		componentList.add(salesProjectionResultsHistoryLayout);

		GtnUIFrameworkComponentConfig sprHistoryComponentConfig = new GtnUIFrameworkComponentConfig();
		sprHistoryComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		sprHistoryComponentConfig.setComponentId("salesProjectionResultsHistory");
		sprHistoryComponentConfig.setComponentName("History");
		sprHistoryComponentConfig.setAddToParent(Boolean.TRUE);
		sprHistoryComponentConfig.setParentComponentId(salesProjectionResultsHistoryLayout.getComponentId());

		GtnUIFrameworkComboBoxConfig sprHistoryConfig = new GtnUIFrameworkComboBoxConfig();
		sprHistoryConfig.setComboBoxType("PAYMENT_FREQUENCY");
		sprHistoryConfig.setHasDefaultValue(Boolean.TRUE);
		sprHistoryComponentConfig.setGtnComboboxConfig(sprHistoryConfig);
		componentList.add(sprHistoryComponentConfig);
	}

	private void addActualsProjection(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig sprActualsProjectionLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionResultsActualsProjection", parentId);
		componentList.add(sprActualsProjectionLayout);

		GtnUIFrameworkComponentConfig sprActualsProjectionOptionGroup = new GtnUIFrameworkComponentConfig();
		sprActualsProjectionOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		sprActualsProjectionOptionGroup.setComponentId("salesProjectionResultsActualsProjectionOptionGroup");
		sprActualsProjectionOptionGroup.setComponentName("Actuals/Projections:");
		sprActualsProjectionOptionGroup.setAddToParent(Boolean.TRUE);
		sprActualsProjectionOptionGroup.setParentComponentId(sprActualsProjectionLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig sprActualsProjectionOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		sprActualsProjectionOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Actuals", "Projections", "Both" }));
		sprActualsProjectionOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		sprActualsProjectionOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		sprActualsProjectionOptionGroup.setGtnUIFrameworkOptionGroupConfig(sprActualsProjectionOptionGroupConfig);

		componentList.add(sprActualsProjectionOptionGroup);
	}

	private void addPivotView(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig salesProjectionResultsPivotViewLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionResultsPivotView", parentId);
		componentList.add(salesProjectionResultsPivotViewLayout);

		GtnUIFrameworkComponentConfig sprPivotViewOptionGroup = new GtnUIFrameworkComponentConfig();
		sprPivotViewOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		sprPivotViewOptionGroup.setComponentId("salesProjectionResultsPivotViewOptionGroup");
		sprPivotViewOptionGroup.setComponentName("Pivot View :");
		sprPivotViewOptionGroup.setAddToParent(Boolean.TRUE);
		sprPivotViewOptionGroup.setParentComponentId(salesProjectionResultsPivotViewLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig sprPivotViewOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		sprPivotViewOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Period", "Variable" }));
		sprPivotViewOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		sprPivotViewOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		sprPivotViewOptionGroup.setGtnUIFrameworkOptionGroupConfig(sprPivotViewOptionGroupConfig);

		componentList.add(sprPivotViewOptionGroup);
	}

	private void addResetAndGenerate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		String tabName = "salesProjectionResultsTab";
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

		GtnUIFrameworkComponentConfig salesProjectionResultsGenerateButton = new GtnUIFrameworkComponentConfig();
		salesProjectionResultsGenerateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		salesProjectionResultsGenerateButton.setComponentId(tabName + "salesProjectionResultsGenerateBtn");
		salesProjectionResultsGenerateButton.setComponentName("GENERATE");
		salesProjectionResultsGenerateButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		salesProjectionResultsGenerateButton.setAddToParent(true);
		componentList.add(salesProjectionResultsGenerateButton);

		GtnUIFrameworkComponentConfig salesProjectionResultsResetButton = new GtnUIFrameworkComponentConfig();
		salesProjectionResultsResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		salesProjectionResultsResetButton.setComponentId(tabName + "salessProjectionResetBtn");
		salesProjectionResultsResetButton.setComponentName("RESET");
		salesProjectionResultsResetButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		salesProjectionResultsResetButton.setAddToParent(Boolean.TRUE);
		componentList.add(salesProjectionResultsResetButton);

		GtnFrameworkExpandCollapseLevelSection conf = new GtnFrameworkExpandCollapseLevelSection();
		conf.getRootExpandCollapseSectionLayout(componentList, resetAndGenerateMainLayout.getComponentId(),
				"forecastCommercial", tabName);

	}

	private void addSalesProjectionResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig salesProjectionResultsPanel = layoutConfig
				.getPanelConfig("salesProjectionResultTable", componentId);
		salesProjectionResultsPanel.setComponentName("Period Pivot View");
		salesProjectionResultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(salesProjectionResultsPanel);

		addResultTable(componentList, salesProjectionResultsPanel.getComponentId());
	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId("salesProjectionResultsResultTable");
		resultTableComponentConfig.setComponentName("resultTable");
		resultTableComponentConfig.setAddToParent(Boolean.TRUE);
		resultTableComponentConfig.setParentComponentId(componentId);

		GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add("salesProjectionResultTablePanel");
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
		GtnUIFrameworkComponentConfig salesProjectionResultsExcelButtonLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionResultsExcel", parentId);
		componentList.add(salesProjectionResultsExcelButtonLayout);
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setComponentId("salesProjectionResultsResultTableExcelBtn");
		excelButtonConfig.setParentComponentId(salesProjectionResultsExcelButtonLayout.getComponentId());
		excelButtonConfig.setAddToParent(true);
		componentList.add(excelButtonConfig);

	}

}
