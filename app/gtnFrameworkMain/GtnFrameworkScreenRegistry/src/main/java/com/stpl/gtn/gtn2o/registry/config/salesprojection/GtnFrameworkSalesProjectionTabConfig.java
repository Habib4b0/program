package com.stpl.gtn.gtn2o.registry.config.salesprojection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.config.common.AdjustmentTab;
import com.stpl.gtn.gtn2o.registry.config.common.DisplaySelectionTab;
import com.stpl.gtn.gtn2o.registry.config.common.FilterTab;
import com.stpl.gtn.gtn2o.registry.config.common.ForecastTab;
import com.stpl.gtn.gtn2o.registry.config.common.GenerateResetButton;
import com.stpl.gtn.gtn2o.registry.config.common.MassUpdateTab;
import com.stpl.gtn.gtn2o.registry.config.common.ResultsLayout;
import com.stpl.gtn.gtn2o.registry.config.common.UpdatePreviousNextCloseSubmitButton;
import com.stpl.gtn.gtn2o.registry.config.projectionvariance.GtnCommercialForecastProjectionVarianceClassConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

public class GtnFrameworkSalesProjectionTabConfig {

	GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addSalesProjectionTabComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		addSalesProjectionPanel(componentList, nameSpace);
	}

	private void addSalesProjectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig salesProjectionMainPanel = new GtnUIFrameworkComponentConfig();
		salesProjectionMainPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		salesProjectionMainPanel.setComponentId(nameSpace + "_" + "salesProjectionMainPanel");
		salesProjectionMainPanel.setAddToParent(false);
		salesProjectionMainPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(salesProjectionMainPanel);
		addSalesProjectionMainLayout(componentList, nameSpace);
	}

	private void addSalesProjectionMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		GtnUIFrameworkComponentConfig salesProjectionMainLayout = configProvider.getVerticalLayoutConfig(
			nameSpace + "_" + "salesProjectionMainLayout", true, nameSpace + "_" + "salesProjectionMainPanel");
		
		salesProjectionMainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(salesProjectionMainLayout);
		addSalesProjDisplaySelectionFilterTab(componentList, salesProjectionMainLayout.getComponentId(), nameSpace);
		new GenerateResetButton().addGenerateResetButtonLayout(componentList, salesProjectionMainLayout.getComponentId(), nameSpace);
		addSalesProjectionPanel(componentList, salesProjectionMainLayout.getComponentId(), nameSpace);
		addUpdatePreviousNextCloseSubmitButtonLayout(componentList, salesProjectionMainLayout.getComponentId(), nameSpace);
	}

	private void addSalesProjDisplaySelectionFilterTab(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		
		
		GtnUIFrameworkComponentConfig tabLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "displaySelectionFilterTabLayout", true, parentComponentId);
		tabLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		tabLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabLayout);

		GtnUIFrameworkComponentConfig tabSheet = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "displaySelectionFilterTabSheet", true, nameSpace + "_" + "displaySelectionFilterTabLayout",
				GtnUIFrameworkComponentType.TABSHEET);
		tabSheet.setComponentName("Tab Sheet");
		tabSheet.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheet);

		GtnUIFrameworkTabConfig displaySelectionTabConfig = new GtnUIFrameworkTabConfig();
		displaySelectionTabConfig.setComponentId(nameSpace + "_" + "displaySelectionTabConfig");
		displaySelectionTabConfig.setTabCaption("Display Selection");
		List<GtnUIFrameworkComponentConfig> displaySelectionComponentList = new ArrayList<>();
		displaySelectionTabConfig.setTabLayoutComponentConfigList(displaySelectionComponentList);
		addDisplaySelectionTab(displaySelectionComponentList, nameSpace);

		GtnUIFrameworkTabConfig filterOptionTab = new GtnUIFrameworkTabConfig();
		filterOptionTab.setComponentId(nameSpace + "_" + "filterTab");
		filterOptionTab.setTabCaption("Filter Option");
		List<GtnUIFrameworkComponentConfig> filterComponentList = new ArrayList<>();
		filterOptionTab.setTabLayoutComponentConfigList(filterComponentList);
		addFilterTab(filterComponentList, nameSpace);

		List<GtnUIFrameworkTabConfig> gtnTabSheetConfigList = new ArrayList<>();
		gtnTabSheetConfigList.add(displaySelectionTabConfig);
		gtnTabSheetConfigList.add(filterOptionTab);
		tabSheet.setGtnTabSheetConfigList(gtnTabSheetConfigList);
			
	}

	private void addDisplaySelectionTab(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {
		DisplaySelectionTab displaySelectionTab = new DisplaySelectionTab();
		displaySelectionTab.addDisplaySelectionTabLayout(componentList,nameSpace);
		displaySelectionTab.addFrequencyHistory(componentList, nameSpace);
		displaySelectionTab.addActualsProjSalesVariables(componentList, nameSpace);
		displaySelectionTab.addProjPeriodOrderUnitOfmeasure(componentList, nameSpace);
		displaySelectionTab.addDisplayCurrencyFormat(componentList, nameSpace);
	}

	private void addFilterTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		FilterTab filterTab = new FilterTab();
		
		GtnUIFrameworkComponentConfig salesProjFilterLayoutConfig = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "salesProjFilterLayoutConfig", false, null);
		salesProjFilterLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		salesProjFilterLayoutConfig.setTabComponent(true);
		componentList.add(salesProjFilterLayoutConfig);

		GtnUIFrameworkLayoutConfig salesProjFilterInnerLayout = new GtnUIFrameworkLayoutConfig();
		salesProjFilterInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		salesProjFilterInnerLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutConfig.setComponentId(nameSpace + "_" + "salesProjFilterInnerLayout");
		filterInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		filterInnerLayoutConfig.setSpacing(Boolean.TRUE);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		filterInnerLayoutConfig.setParentComponentId(salesProjFilterLayoutConfig.getComponentId());
		filterInnerLayoutConfig.setGtnLayoutConfig(salesProjFilterInnerLayout);
		componentList.add(filterInnerLayoutConfig);
		
		filterTab.addCustomerLevel(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addProductLevel(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addSalesInclusion(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addCustomerFilter(componentList, filterInnerLayoutConfig.getComponentId(),nameSpace);
		filterTab.addProductFilter(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
	}
	
	private void addSalesProjectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig salesProjectionPanel = configProvider
				.getPanelConfig(nameSpace + "_" + "salesProjectionPanel", true, parentComponentId);
		salesProjectionPanel.setComponentName("Sales Projection");
		salesProjectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(salesProjectionPanel);
		
		GtnUIFrameworkComponentConfig salesProjectionPanelLayout = configProvider.getVerticalLayoutConfig(nameSpace+"_"+ "salesProjectionPanelLayout", true, salesProjectionPanel.getComponentId());
		salesProjectionPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(salesProjectionPanelLayout);

		addMassUpdateForecastAdjustmentTabSheetPanel(componentList, salesProjectionPanelLayout.getComponentId(), nameSpace);
		new ResultsLayout().addResultsLayout(componentList, salesProjectionPanelLayout.getComponentId(), nameSpace);
		addSalesProjectionExcelButton(componentList, salesProjectionPanelLayout.getComponentId(), nameSpace);
	}
	
	public void addMassUpdateForecastAdjustmentTabSheetPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig tabSheetPanel = configProvider.getPanelConfig(nameSpace + "_" + "tabSheetPanel",
				true, parentComponentId);
		tabSheetPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheetPanel);

		addSalesProjectionTabSheet(componentList, tabSheetPanel.getComponentId(), nameSpace);

	}

	private void addSalesProjectionTabSheet(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
			
		GtnUIFrameworkComponentConfig salesProjectionTabLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "salesProjTabLayout", true, parentComponentId);
		salesProjectionTabLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		salesProjectionTabLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(salesProjectionTabLayout);

		GtnUIFrameworkComponentConfig tabSheet = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "salesProjTabSheet", true, nameSpace + "_" + "salesProjTabLayout",
				GtnUIFrameworkComponentType.TABSHEET);
		tabSheet.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheet);

		GtnUIFrameworkTabConfig massUpdateTabConfig = new GtnUIFrameworkTabConfig();
		massUpdateTabConfig.setComponentId(nameSpace + "_" + "massUpdateTab");
		massUpdateTabConfig.setTabCaption("Mass Update");
		List<GtnUIFrameworkComponentConfig> massUpdateComponentList = new ArrayList<>();
		massUpdateTabConfig.setTabLayoutComponentConfigList(massUpdateComponentList);
		new MassUpdateTab().addMassUpdateTab(massUpdateComponentList, nameSpace);

		GtnUIFrameworkTabConfig forecastTabConfig = new GtnUIFrameworkTabConfig();
		forecastTabConfig.setComponentId(nameSpace + "_" + "forecastTab");
		forecastTabConfig.setTabCaption("Forecast");
		List<GtnUIFrameworkComponentConfig> forecastComponentList = new ArrayList<>();
		forecastTabConfig.setTabLayoutComponentConfigList(forecastComponentList);
		addForecastTab(forecastComponentList, nameSpace);

		GtnUIFrameworkTabConfig adjustmentTabConfig = new GtnUIFrameworkTabConfig();
		adjustmentTabConfig.setComponentId(nameSpace + "_" + "adjustmentTab");
		adjustmentTabConfig.setTabCaption("Adjustment");
		List<GtnUIFrameworkComponentConfig> adjustmentComponentList = new ArrayList<>();
		adjustmentTabConfig.setTabLayoutComponentConfigList(adjustmentComponentList);
		addAdjustmentTab(adjustmentComponentList, nameSpace);

		List<GtnUIFrameworkTabConfig> gtnTabSheetConfigList = new ArrayList<>();
		gtnTabSheetConfigList.add(massUpdateTabConfig);
		gtnTabSheetConfigList.add(forecastTabConfig);
		gtnTabSheetConfigList.add(adjustmentTabConfig);
		tabSheet.setGtnTabSheetConfigList(gtnTabSheetConfigList);
	}

	private void addForecastTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		ForecastTab forecastComponent=new ForecastTab();
		forecastComponent.addForecastTabLayout(componentList, nameSpace);

		forecastComponent.addMethodologyComboBox(componentList, nameSpace);
		forecastComponent.addAllocationBasisComboBox(componentList, nameSpace);
		forecastComponent.addStartPeriodForecastComboBox(componentList,  nameSpace);
		forecastComponent.addEndPeriodForecastComboBox(componentList,  nameSpace);
		forecastComponent.addCalculateButton(componentList,  nameSpace);

	}

	private void addAdjustmentTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		AdjustmentTab adjustmentTab=new AdjustmentTab();
		adjustmentTab.addAdjustmentTabLayout(componentList, nameSpace);
		adjustmentTab.addTypeOptionGroup(componentList, nameSpace);
		adjustmentTab.addBasisOptionGroup(componentList, nameSpace);
		adjustmentTab.addVariableOptionGroup(componentList, nameSpace);
		adjustmentTab.addAllocationMehodologyComboBox(componentList, nameSpace);
		adjustmentTab.addAdjustmentPeriodComboBox(componentList, nameSpace);
		adjustmentTab.addAdjustButton(componentList, nameSpace);
	}
	
	private void addSalesProjectionExcelButton(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig excelBtuttonLayout = configProvider.getHorizontalLayoutConfig(nameSpace + "_" + "excelButtonlayout", true, parentComponentId);
		componentList.add(excelBtuttonLayout);

		GtnUIFrameworkComponentConfig excelButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "excelButton", true, excelBtuttonLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButton.setAuthorizationIncluded(true);
		componentList.add(excelButton);
		
		GtnUIFrameworkComponentConfig pmpyButton = configProvider.getUIFrameworkComponentConfig(nameSpace + "_" + "pmpyButton", true, excelBtuttonLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		pmpyButton.setComponentName("PMPY");
		componentList.add(pmpyButton);
		
		GtnUIFrameworkComponentConfig refreshButton = configProvider.getUIFrameworkComponentConfig(nameSpace + "_" + "refreshButton", true, excelBtuttonLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		refreshButton.setComponentName("REFRESH");
		componentList.add(refreshButton);


	}private void addProjectionPivoteViewTablePanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {

		GtnUIFrameworkComponentConfig projectionpivoteViewPanel = new GtnUIFrameworkComponentConfig();
		projectionpivoteViewPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		projectionpivoteViewPanel.setComponentId(nameSpace + "_" + "projectionpivoteViewPanel");
		projectionpivoteViewPanel.setComponentName("Projecion Pivot View");
		projectionpivoteViewPanel.setAddToParent(true);
		projectionpivoteViewPanel.setParentComponentId(parentComponentId);
		projectionpivoteViewPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(projectionpivoteViewPanel);

		addProjectionPivoteViewTablePanelComponents(componentList, nameSpace + "_" + "projectionpivoteViewPanel",
				nameSpace);

	}

	private void addProjectionPivoteViewTablePanelComponents(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig ppvComponetsLayout = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "ppvComponetsLayout", true, parentComponentId);
		ppvComponetsLayout.setComponentWidth("100%");
		componentList.add(ppvComponetsLayout);

		addProjectionPivoteViewTable(componentList, nameSpace + "_" + "ppvComponetsLayout", nameSpace);
	}

	private void addProjectionPivoteViewTable(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig ppvComponetsLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "ppvTableLayout", true, parentComponentId);
		ppvComponetsLayout.setComponentWidth("100%");
		ppvComponetsLayout.setSpacing(false);
		componentList.add(ppvComponetsLayout);

		GtnUIFrameworkComponentConfig projectionpivoteViewResultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		projectionpivoteViewResultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_TREE_GRID);
		projectionpivoteViewResultTableComponentConfig.setComponentId(GtnFrameworkCommonConstants.RESULT_TABLE);
		projectionpivoteViewResultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		projectionpivoteViewResultTableComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		projectionpivoteViewResultTableComponentConfig.setAddToParent(true);
		projectionpivoteViewResultTableComponentConfig.setParentComponentId(nameSpace + "_" + "ppvTableLayout");

		GtnUIFrameworkPagedTreeTableConfig reportingDashboardGtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> reportingDashboardActionConfigList = new ArrayList<>();
		reportingDashboardActionConfigList.add(parentComponentId);

		GtnUIFrameWorkActionConfig reportingDashboardGtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardGtnUIFrameWorkActionConfig.setActionParameterList(reportingDashboardActionConfigList);
		reportingDashboardGtnPagedTreeTableConfig
				.setGtnUIFrameWorkActionConfig(reportingDashboardGtnUIFrameWorkActionConfig);

		reportingDashboardGtnPagedTreeTableConfig.setLeftHeader(
				GtnWsForecastReturnsConstants.GTN_WS_COMMERCIAL_FORECAST_PROJECTION_VARIANCE_TAB_LEFT_HEADERS_SERVICE);
		reportingDashboardGtnPagedTreeTableConfig.setRightHeader(
				GtnWsForecastReturnsConstants.GTN_WS_COMMERCIAL_FORECAST_PROJECTION_VARIANCE_TAB_RIGHT_HEADERS_SERVICE);
		// reportingDashboardGtnPagedTreeTableConfig.setLeftWsHeaderUrl(
		// GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
		// reportingDashboardGtnPagedTreeTableConfig.setRightWsHeaderUrl(
		// GtnWsForecastReturnsConstants.GTN_WS_REPORT_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);
		reportingDashboardGtnPagedTreeTableConfig.setCountUrl("");
		reportingDashboardGtnPagedTreeTableConfig.setItemPerPage(10);

		reportingDashboardGtnPagedTreeTableConfig.setMaxSplitPosition(1000);
		reportingDashboardGtnPagedTreeTableConfig.setMinSplitPosition(300);
		reportingDashboardGtnPagedTreeTableConfig.setPageLength(15);
		reportingDashboardGtnPagedTreeTableConfig.setResultSetUrl("");

		reportingDashboardGtnPagedTreeTableConfig.setSplitPosition(493);
		reportingDashboardGtnPagedTreeTableConfig.setTableHeight("650px");
		reportingDashboardGtnPagedTreeTableConfig.setDoubleHeaderVisible(true);
		
		reportingDashboardGtnPagedTreeTableConfig.setLeftTableEditable(true);
		reportingDashboardGtnPagedTreeTableConfig.setRightTableEditable(true);

		List<String> reportingDashboardFieldFactoryColum = Arrays.asList(GtnFrameworkCommonConstants.CHECK);
		List<GtnUIFrameworkComponentConfig> reportingDashboardFieldFactoryComponent = new ArrayList<>();
		reportingDashboardGtnPagedTreeTableConfig.setLeftTableEditableColumnList(reportingDashboardFieldFactoryColum);

		GtnUIFrameworkComponentConfig reportingDashboardCheckBox = new GtnUIFrameworkComponentConfig();
		reportingDashboardCheckBox.setComponentId(GtnFrameworkCommonConstants.CHECK);
		reportingDashboardCheckBox.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
		GtnUIFrameworkCheckBoxComponentConfig reportingDashboardCheckBoxConfig = new GtnUIFrameworkCheckBoxComponentConfig();
		reportingDashboardCheckBoxConfig.setImmediate(true);

		reportingDashboardCheckBox.setGtnCheckBoxConfig(reportingDashboardCheckBoxConfig);
		List<GtnUIFrameWorkActionConfig> reportingDashboardCheckBoxClickActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardGtnUIFrameWorkGenerateActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardGtnUIFrameWorkGenerateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardGtnUIFrameWorkGenerateActionConfig.addActionParameter(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_LEFT_FIELD_FACTORY_ACTION);
		reportingDashboardCheckBoxClickActionList.add(reportingDashboardGtnUIFrameWorkGenerateActionConfig);
		reportingDashboardGtnUIFrameWorkGenerateActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));
		reportingDashboardCheckBox
				.setGtnUIFrameWorkItemClickActionConfigList(reportingDashboardCheckBoxClickActionList);

		reportingDashboardFieldFactoryComponent.add(reportingDashboardCheckBox);
		reportingDashboardGtnPagedTreeTableConfig
				.setLeftTableEditableComponentConfig(reportingDashboardFieldFactoryComponent);

		List<GtnUIFrameWorkActionConfig> reportingDashboardTextFieldConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardFieldFactoryCustomAction = new GtnUIFrameWorkActionConfig();

		reportingDashboardFieldFactoryCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));

		reportingDashboardFieldFactoryCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardFieldFactoryCustomAction.addActionParameter(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_RIGHT_FIELD_FACTORY_ACTION);
		reportingDashboardTextFieldConfig.add(reportingDashboardFieldFactoryCustomAction);
		reportingDashboardGtnPagedTreeTableConfig.setComponentconfigActionlist(reportingDashboardTextFieldConfig);

		reportingDashboardGtnPagedTreeTableConfig
				.setCheckBoxVisibleColoumn(Arrays.asList(GtnFrameworkCommonConstants.CHECK));
		List<GtnUIFrameWorkActionConfig> reportingDashboardCheckAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig reportingDashboardCheckAllActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardCheckAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		reportingDashboardCheckAllActionConfig.addActionParameter(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_CHECK_ALL_ACTION);
		reportingDashboardCheckAllConflist.add(reportingDashboardCheckAllActionConfig);
		reportingDashboardGtnPagedTreeTableConfig.setCheckBoxActionConfigList(reportingDashboardCheckAllConflist);
		reportingDashboardGtnPagedTreeTableConfig.setCountUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_COUNT_ACTION);
		reportingDashboardGtnPagedTreeTableConfig
				.setCountWsUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setBulkDataUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_BULK_DATA_ACTION);
		reportingDashboardGtnPagedTreeTableConfig.setBulkDataWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setFillCountUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_FILL_COUNT_DATA_ACTION);
		reportingDashboardGtnPagedTreeTableConfig.setFillCountWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setLeftHeaderUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_COMP_LEFT_HEADER_FORM_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setLeftWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setRighttHeaderUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_COMP_RIGHT_HEADER_FORM_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setRightWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		reportingDashboardGtnPagedTreeTableConfig.setLeftHeaderCustomClassLoadUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_LEFT_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_RIGHT_HEADER_CONFIG_ACTION);

		reportingDashboardGtnPagedTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);

		projectionpivoteViewResultTableComponentConfig
				.setGtnPagedTreeTableConfig(reportingDashboardGtnPagedTreeTableConfig);
		componentList.add(projectionpivoteViewResultTableComponentConfig);

	}

	private void addUpdatePreviousNextCloseSubmitButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		UpdatePreviousNextCloseSubmitButton salesProjectionButtonLayout=new UpdatePreviousNextCloseSubmitButton();
		salesProjectionButtonLayout.addCommonButtonLayout(componentList,  parentComponentId, nameSpace);
		salesProjectionButtonLayout.addUpdateButton(componentList, nameSpace);
		salesProjectionButtonLayout.addPreviousButton(componentList, nameSpace);
		salesProjectionButtonLayout.addNextButton(componentList, nameSpace);
		salesProjectionButtonLayout.addCloseButton(componentList, nameSpace);
		salesProjectionButtonLayout.addSubmitButton(componentList, nameSpace);
		
	}
}
