package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.discountprojection;

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
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingDPRTabConfig {
	
	private GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
	private final String tabName = "discountProjectionResultsTab";
	
	public void addDiscountProjectionResultsTab(List<GtnUIFrameworkComponentConfig> componentList)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutConfig.getRootLayoutConfig(
				"commercialForecastingDiscountProjectionResultsRootLayout", GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rootLayoutConfig.setSpacing(Boolean.TRUE);
		componentList.add(rootLayoutConfig);

		addSalesProjectionResultSelection(componentList, rootLayoutConfig.getComponentId());
		addResetAndGenerate(componentList, rootLayoutConfig.getComponentId());
		addDiscountProjectionResultsPanel(componentList, rootLayoutConfig.getComponentId());
		addResetRefreshButtons(componentList, rootLayoutConfig.getComponentId());

	}
	
	private void addSalesProjectionResultSelection(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionResultsMainLayout", parentId);
		mainLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		mainLayoutConfig.setSpacing(Boolean.TRUE);
		mainLayoutConfig.setMargin(Boolean.TRUE);
		componentList.add(mainLayoutConfig);

		GtnUIFrameworkComponentConfig discountProjectionSalesProjectionSelectionPanel = layoutConfig
				.getPanelConfig("discountProjectionResultsSelectionPanel", mainLayoutConfig.getComponentId());
		discountProjectionSalesProjectionSelectionPanel.setComponentName("Discount Projection Results Selection ");
		discountProjectionSalesProjectionSelectionPanel
				.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(discountProjectionSalesProjectionSelectionPanel);

		GtnUIFrameworkComponentConfig discountProjectionSalesProjectionSelectionPanelLayout = layoutConfig
				.getVerticalLayoutConfig("discountProjectionResultsSelectionPanelLayout",
						discountProjectionSalesProjectionSelectionPanel.getComponentId());
		discountProjectionSalesProjectionSelectionPanelLayout
		.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(discountProjectionSalesProjectionSelectionPanelLayout);

		GtnUIFrameworkComponentConfig discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout = layoutConfig
				.getCssLayoutConfig("discountProjectionResultsFrequencyActualsProjectionsProjectionPeriodOrderCssLayout",
						discountProjectionSalesProjectionSelectionPanelLayout.getComponentId());
		discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout.setComponentWidth("75%");
		componentList.add(discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout);

		GtnUIFrameworkComponentConfig discountProjectionHistoryProjectionVariablesCssLayout = layoutConfig
				.getCssLayoutConfig("discountProjectionResultsHistoryProjectionVariablesCssLayout",
						discountProjectionSalesProjectionSelectionPanelLayout.getComponentId());
		discountProjectionHistoryProjectionVariablesCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		discountProjectionHistoryProjectionVariablesCssLayout.setComponentWidth("75%");
		componentList.add(discountProjectionHistoryProjectionVariablesCssLayout);

		addFrequency(componentList, discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout.getComponentId());
		
		addActualsProjection(componentList,
				discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout.getComponentId());
		
		addProjectionPeriodOrder(componentList,
				discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout.getComponentId());

		addHistory(componentList, discountProjectionHistoryProjectionVariablesCssLayout.getComponentId());

		addVariables(componentList, discountProjectionHistoryProjectionVariablesCssLayout.getComponentId());
		
		addPivotView(componentList, discountProjectionHistoryProjectionVariablesCssLayout.getComponentId());
		
	}
	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig forecastReturnsFrequencyConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionResultsForecastReturnsFrequency", parentId);
		componentList.add(forecastReturnsFrequencyConfig);

		GtnUIFrameworkComponentConfig frequencyComponentConfig = new GtnUIFrameworkComponentConfig();
		frequencyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		frequencyComponentConfig.setComponentId("discountProjectionResultsForecastReturnsFrequency");
		frequencyComponentConfig.setComponentName("Frequency");
		frequencyComponentConfig.setAddToParent(Boolean.TRUE);
		frequencyComponentConfig.setParentComponentId(forecastReturnsFrequencyConfig.getComponentId());
		frequencyComponentConfig.addDependentComponent("discountProjectionResultsForecastReturnsHistory");

		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequencyConfig.setComboBoxType("PAYMENT_FREQUENCY");
		frequencyConfig.setHasDefaultValue(Boolean.TRUE);
		frequencyComponentConfig.setGtnComboboxConfig(frequencyConfig);
		componentList.add(frequencyComponentConfig);
	}

	private void addActualsProjection(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig hLConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionResultsActualsProjection", parentId);

		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig actualsProjectionOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		actualsProjectionOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		actualsProjectionOptionGroupConfig.setComponentId("discountProjectionResultsActualsProjectionOptionGroup");
		actualsProjectionOptionGroupConfig.setComponentName("Actuals/Projections:");
		actualsProjectionOptionGroupConfig.setAddToParent(Boolean.TRUE);
		actualsProjectionOptionGroupConfig.setParentComponentId(hLConfig.getComponentId());

		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(new String[] { "Actuals", "Projections", "Both" }));
		comboConfig.setValuesFromService(Boolean.FALSE);
		actualsProjectionOptionGroupConfig.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		actualsProjectionOptionGroupConfig.setGtnUIFrameworkOptionGroupConfig(comboConfig);

		componentList.add(actualsProjectionOptionGroupConfig);
	}

	private void addProjectionPeriodOrder(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig hLConfig = layoutConfig.getHorizontalLayoutConfig("discountProjectionResultsPeriodOrder",
				parentId);
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig projectionPeriodOrderOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		projectionPeriodOrderOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionPeriodOrderOptionGroupConfig.setComponentId("discountProjectionResultsPeriodOrderOptionGroup");
		projectionPeriodOrderOptionGroupConfig.setComponentName("Actuals/Projections:");
		projectionPeriodOrderOptionGroupConfig.setAddToParent(Boolean.TRUE);
		projectionPeriodOrderOptionGroupConfig.setParentComponentId(hLConfig.getComponentId());

		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(new String[] { "Ascending", "Descending" }));
		comboConfig.setValuesFromService(Boolean.FALSE);
		projectionPeriodOrderOptionGroupConfig.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionPeriodOrderOptionGroupConfig.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(projectionPeriodOrderOptionGroupConfig);
	}

	private void addHistory(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig forecastReturnsHistoryConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionResultsHistory", parentId);
		componentList.add(forecastReturnsHistoryConfig);

		GtnUIFrameworkComponentConfig historyComponentConfig = new GtnUIFrameworkComponentConfig();
		historyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		historyComponentConfig.setComponentId("discountProjectionResultsHistory");
		historyComponentConfig.setComponentName("History");
		historyComponentConfig.setAddToParent(Boolean.TRUE);
		historyComponentConfig.setParentComponentId(forecastReturnsHistoryConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig historyConfig = new GtnUIFrameworkComboBoxConfig();
		historyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		historyConfig.setComboBoxType("PAYMENT_FREQUENCY");
		historyConfig.setHasDefaultValue(Boolean.TRUE);
		historyComponentConfig.setGtnComboboxConfig(historyConfig);
		componentList.add(historyComponentConfig);
	}

	private void addVariables(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig hLConfig = layoutConfig.getHorizontalLayoutConfig("discountProjectionResultsVariables",
				parentId);
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig projectionPeriodOrderOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		projectionPeriodOrderOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionPeriodOrderOptionGroupConfig.setComponentId("discountProjectionResultsVariablesCheckboxGroup");
		projectionPeriodOrderOptionGroupConfig.setComponentName("Variables:");
		projectionPeriodOrderOptionGroupConfig.setAddToParent(Boolean.TRUE);
		projectionPeriodOrderOptionGroupConfig.setParentComponentId(hLConfig.getComponentId());

		GtnUIFrameworkOptionGroupConfig checkboxGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		checkboxGroupConfig.setItemValues(
				Arrays.asList(new String[] { "Discount Rate", "Rebate Per Unit", "Discount Amount" }));
		checkboxGroupConfig.setValuesFromService(Boolean.FALSE);
		checkboxGroupConfig.setIsMultiSelect(true);
		projectionPeriodOrderOptionGroupConfig.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionPeriodOrderOptionGroupConfig.setGtnUIFrameworkOptionGroupConfig(checkboxGroupConfig);
		componentList.add(projectionPeriodOrderOptionGroupConfig);
	}
	
	private void addPivotView(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig hLConfig = layoutConfig.getHorizontalLayoutConfig("discountProjectionResultsPivotView",
				parentId);
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig discountProjectionResultsPivotViewConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionResultsPivotViewConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		discountProjectionResultsPivotViewConfig.setComponentId("discountProjectionResultsPivotView");
		discountProjectionResultsPivotViewConfig.setComponentName("Pivot View:");
		discountProjectionResultsPivotViewConfig.setAddToParent(Boolean.TRUE);
		discountProjectionResultsPivotViewConfig.setParentComponentId(hLConfig.getComponentId());

		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(new String[] { "Period", "Discount" }));
		comboConfig.setValuesFromService(Boolean.FALSE);
		discountProjectionResultsPivotViewConfig.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		discountProjectionResultsPivotViewConfig.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(discountProjectionResultsPivotViewConfig);
	
	}
	private void addResetAndGenerate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

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
		salesProjectionResultsGenerateButton.setComponentId(tabName + "discountProjectionResultsGenerateBtn");
		salesProjectionResultsGenerateButton.setComponentName("GENERATE");
		salesProjectionResultsGenerateButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		salesProjectionResultsGenerateButton.setAddToParent(true);
		componentList.add(salesProjectionResultsGenerateButton);

		GtnUIFrameworkComponentConfig salesProjectionResultsResetButton = new GtnUIFrameworkComponentConfig();
		salesProjectionResultsResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		salesProjectionResultsResetButton.setComponentId(tabName + "discountProjectionResultsResetBtn");
		salesProjectionResultsResetButton.setComponentName("RESET");
		salesProjectionResultsResetButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		salesProjectionResultsResetButton.setAddToParent(Boolean.TRUE);
		componentList.add(salesProjectionResultsResetButton);

		GtnFrameworkExpandCollapseLevelSection conf = new GtnFrameworkExpandCollapseLevelSection();
		conf.getRootExpandCollapseSectionLayout(componentList, resetAndGenerateMainLayout.getComponentId(),
				"forecastCommercial", tabName);

	}

	private void addDiscountProjectionResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig salesProjectionResultsPanel = layoutConfig
				.getPanelConfig("discountProjectionResultsTable", componentId);
		salesProjectionResultsPanel.setComponentName("Period Pivot View");
		salesProjectionResultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(salesProjectionResultsPanel);

		addResultTable(componentList, salesProjectionResultsPanel.getComponentId());
	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId("discountProjectionResultsResultTable");
		resultTableComponentConfig.setComponentName("resultTable");
		resultTableComponentConfig.setAddToParent(Boolean.TRUE);
		resultTableComponentConfig.setParentComponentId(componentId);

		GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add("discountProjectionResultsTablePanel");
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
				.getHorizontalLayoutConfig("discountProjectionResultsExcel", parentId);
		componentList.add(salesProjectionResultsExcelButtonLayout);
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setComponentId("discountProjectionResultsTableExcelBtn");
		excelButtonConfig.setParentComponentId(salesProjectionResultsExcelButtonLayout.getComponentId());
		excelButtonConfig.setAddToParent(true);
		componentList.add(excelButtonConfig);

	}

}
