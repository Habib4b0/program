package com.stpl.gtn.gtn2o.registry.config.salesprojection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
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

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
	private String[] propertyIds = { "filterTextBox1", "filterTextBox2", "filterTextBox3", "filterTextBox4" };
	private GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

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
		new GenerateResetButton().addGenerateResetButtonLayout(componentList,
				salesProjectionMainLayout.getComponentId(), nameSpace);
		addSalesProjectionPanel(componentList, salesProjectionMainLayout.getComponentId(), nameSpace);
		addUpdatePreviousNextCloseSubmitButtonLayout(componentList, salesProjectionMainLayout.getComponentId(),
				nameSpace);
	}

	private void addSalesProjDisplaySelectionFilterTab(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {

		GtnUIFrameworkComponentConfig tabLayout = configProvider.getHorizontalLayoutConfig(
				nameSpace + "_" + "displaySelectionFilterTabLayout", true, parentComponentId);
		tabLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		tabLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabLayout);

		GtnUIFrameworkComponentConfig tabSheet = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "displaySelectionFilterTabSheet", true,
				nameSpace + "_" + "displaySelectionFilterTabLayout", GtnUIFrameworkComponentType.TABSHEET);
		tabSheet.setComponentName("Tab Sheet");
		tabSheet.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheet);

		GtnUIFrameworkTabConfig displaySelectionTabConfigSalesProjection = new GtnUIFrameworkTabConfig();
		displaySelectionTabConfigSalesProjection.setComponentId(nameSpace + "_" + "displaySelectionTabConfig");
		displaySelectionTabConfigSalesProjection.setTabCaption("Display Selection");
		List<GtnUIFrameworkComponentConfig> displaySelectionComponentList = new ArrayList<>();
		displaySelectionTabConfigSalesProjection.setTabLayoutComponentConfigList(displaySelectionComponentList);
		addDisplaySelectionTab(displaySelectionComponentList, nameSpace);

		GtnUIFrameworkTabConfig filterOptionTabSalesProjection = new GtnUIFrameworkTabConfig();
		filterOptionTabSalesProjection.setComponentId(nameSpace + "_" + "filterTab");
		filterOptionTabSalesProjection.setTabCaption("Filter Option");
		List<GtnUIFrameworkComponentConfig> filterComponentList = new ArrayList<>();
		filterOptionTabSalesProjection.setTabLayoutComponentConfigList(filterComponentList);
		addFilterTab(filterComponentList, nameSpace);

		List<GtnUIFrameworkTabConfig> gtnTabSheetConfigList = new ArrayList<>();
		gtnTabSheetConfigList.add(displaySelectionTabConfigSalesProjection);
		gtnTabSheetConfigList.add(filterOptionTabSalesProjection);
		tabSheet.setGtnTabSheetConfigList(gtnTabSheetConfigList);

	}

	private void addDisplaySelectionTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		DisplaySelectionTab displaySelectionTab = new DisplaySelectionTab();
		displaySelectionTab.addDisplaySelectionTabLayout(componentList, nameSpace);
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
		filterInnerLayoutConfig.setAddToParent(true);
		filterInnerLayoutConfig.setSpacing(true);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		filterInnerLayoutConfig.setParentComponentId(salesProjFilterLayoutConfig.getComponentId());
		filterInnerLayoutConfig.setGtnLayoutConfig(salesProjFilterInnerLayout);
		componentList.add(filterInnerLayoutConfig);

		filterTab.addCustomerLevel(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addProductLevel(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addSalesInclusion(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addCustomerFilter(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addProductFilter(componentList, filterInnerLayoutConfig.getComponentId(), nameSpace);
	}

	private void addSalesProjectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig salesProjectionPanel = configProvider
				.getPanelConfig(nameSpace + "_" + "salesProjectionPanel", true, parentComponentId);
		salesProjectionPanel.setComponentName("Sales Projection");
		salesProjectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(salesProjectionPanel);

		GtnUIFrameworkComponentConfig salesProjectionPanelLayout = configProvider.getVerticalLayoutConfig(
				nameSpace + "_" + "salesProjectionPanelLayout", true, salesProjectionPanel.getComponentId());
		salesProjectionPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(salesProjectionPanelLayout);

		addMassUpdateForecastAdjustmentTabSheetPanel(componentList, salesProjectionPanelLayout.getComponentId(),
				nameSpace);
		new ResultsLayout().addResultsLayout(componentList, salesProjectionPanelLayout.getComponentId(), nameSpace);
		addSalesProjectionTable(componentList, salesProjectionPanelLayout.getComponentId(), nameSpace);
		addSalesProjectionExcelButton(componentList, salesProjectionPanelLayout.getComponentId(), nameSpace);
	}

	public void addMassUpdateForecastAdjustmentTabSheetPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig tabSheetPanel = configProvider.getPanelConfig(nameSpace + "_" + "tabSheetPanel",
				true, parentComponentId);
		tabSheetPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheetPanel);

		addSalesProjectionTabSheet(componentList, tabSheetPanel.getComponentId(), nameSpace);

	}

	private void addSalesProjectionTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {

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

		List<GtnUIFrameworkTabConfig> gtnTabSheetConfigListSalesProjection = new ArrayList<>();
		gtnTabSheetConfigListSalesProjection.add(massUpdateTabConfig);
		gtnTabSheetConfigListSalesProjection.add(forecastTabConfig);
		gtnTabSheetConfigListSalesProjection.add(adjustmentTabConfig);
		tabSheet.setGtnTabSheetConfigList(gtnTabSheetConfigListSalesProjection);
	}

	private void addForecastTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		ForecastTab forecastComponent = new ForecastTab();
		forecastComponent.addForecastTabLayout(componentList, nameSpace);

		forecastComponent.addMethodologyComboBox(componentList, nameSpace);
		forecastComponent.addAllocationBasisComboBox(componentList, nameSpace);
		forecastComponent.addStartPeriodForecastComboBox(componentList, nameSpace);
		forecastComponent.addEndPeriodForecastComboBox(componentList, nameSpace);
		forecastComponent.addCalculateButton(componentList, nameSpace);

	}

	private void addAdjustmentTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		AdjustmentTab adjustmentTab = new AdjustmentTab();
		adjustmentTab.addAdjustmentTabLayout(componentList, nameSpace);
		adjustmentTab.addTypeOptionGroup(componentList, nameSpace);
		adjustmentTab.addBasisOptionGroup(componentList, nameSpace);
		adjustmentTab.addVariableOptionGroup(componentList, nameSpace);
		adjustmentTab.addAllocationMehodologyComboBox(componentList, nameSpace);
		adjustmentTab.addAdjustmentPeriodComboBox(componentList, nameSpace);
		adjustmentTab.addAdjustButton(componentList, nameSpace);
	}

	private void addSalesProjectionExcelButton(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig excelBtuttonLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "excelButtonlayout", true, parentComponentId);
		componentList.add(excelBtuttonLayout);

		GtnUIFrameworkComponentConfig excelButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "excelButton", true, excelBtuttonLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButton.setAuthorizationIncluded(true);
		componentList.add(excelButton);

		GtnUIFrameworkComponentConfig pmpyButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "pmpyButton", true, excelBtuttonLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		pmpyButton.setComponentName("PMPY");
		componentList.add(pmpyButton);

		GtnUIFrameworkComponentConfig refreshButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "refreshButton", true, excelBtuttonLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		refreshButton.setComponentName("REFRESH");
		componentList.add(refreshButton);

	}

	private void addSalesProjectionTable(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {

		GtnUIFrameworkComponentConfig tableLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "tableLayout", true, parentComponentId);
		tableLayout.setComponentWidth("100%");
		tableLayout.setSpacing(false);
		componentList.add(tableLayout);

		GtnUIFrameworkComponentConfig salesProjectionResultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		salesProjectionResultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_TREE_GRID);
		salesProjectionResultTableComponentConfig.setComponentId(nameSpace + "resultPagedTreeGrid");
		salesProjectionResultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		salesProjectionResultTableComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		salesProjectionResultTableComponentConfig.setAddToParent(true);
		salesProjectionResultTableComponentConfig.setParentComponentId(tableLayout.getComponentId());

		GtnUIFrameworkPagedTreeTableConfig salesProjectionTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> salesProjectionActionConfigList = new ArrayList<>();
		salesProjectionActionConfigList.add(parentComponentId);

		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionParameterList(salesProjectionActionConfigList);
		salesProjectionTreeTableConfig.setGtnUIFrameWorkActionConfig(actionConfig);

		salesProjectionTreeTableConfig.setLeftHeader(
				GtnWsForecastReturnsConstants.GTN_WS_COMMERCIAL_FORECAST_SALES_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
		salesProjectionTreeTableConfig.setRightHeader(
				GtnWsForecastReturnsConstants.GTN_WS_COMMERCIAL_FORECAST_SALES_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);
		salesProjectionTreeTableConfig.setCountUrl("");
		salesProjectionTreeTableConfig.setItemPerPage(10);

		salesProjectionTreeTableConfig.setMaxSplitPosition(1000);
		salesProjectionTreeTableConfig.setMinSplitPosition(300);
		salesProjectionTreeTableConfig.setPageLength(15);
		salesProjectionTreeTableConfig.setResultSetUrl("");

		salesProjectionTreeTableConfig.setSplitPosition(493);
		salesProjectionTreeTableConfig.setTableHeight("650px");
		salesProjectionTreeTableConfig.setDoubleHeaderVisible(true);

		salesProjectionTreeTableConfig.setLeftTableEditable(true);
		salesProjectionTreeTableConfig.setRightTableEditable(true);

		List<String> fieldFactoryColum = Arrays.asList(GtnFrameworkCommonConstants.CHECK);
		List<GtnUIFrameworkComponentConfig> reportingDashboardFieldFactoryComponent = new ArrayList<>();
		salesProjectionTreeTableConfig.setLeftTableEditableColumnList(fieldFactoryColum);

		salesProjectionTreeTableConfig.setEnableRadioButtonInSingleHeader(true);
		salesProjectionTreeTableConfig.setEnableCheckBoxInDoubleHeader(true);

		GtnUIFrameworkComponentConfig checkBox = new GtnUIFrameworkComponentConfig();
		checkBox.setComponentId(GtnFrameworkCommonConstants.CHECK);
		checkBox.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
		GtnUIFrameworkCheckBoxComponentConfig checkBoxConfig = new GtnUIFrameworkCheckBoxComponentConfig();
		checkBoxConfig.setImmediate(true);

		checkBox.setGtnCheckBoxConfig(checkBoxConfig);
		List<GtnUIFrameWorkActionConfig> checkBoxClickActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig generateActionConfig = new GtnUIFrameWorkActionConfig();
		generateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkBoxClickActionList.add(generateActionConfig);
		generateActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));
		checkBox.setGtnUIFrameWorkItemClickActionConfigList(checkBoxClickActionList);

		reportingDashboardFieldFactoryComponent.add(checkBox);
		salesProjectionTreeTableConfig.setLeftTableEditableComponentConfig(reportingDashboardFieldFactoryComponent);

		List<GtnUIFrameWorkActionConfig> textFieldConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig fieldFactoryCustomAction = new GtnUIFrameWorkActionConfig();

		fieldFactoryCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));

		fieldFactoryCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		textFieldConfig.add(fieldFactoryCustomAction);
		salesProjectionTreeTableConfig.setComponentconfigActionlist(textFieldConfig);

		salesProjectionTreeTableConfig.setCheckBoxVisibleColoumn(Arrays.asList("filterTextBox1"));
		List<GtnUIFrameWorkActionConfig> checkAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig checkAllActionConfig = new GtnUIFrameWorkActionConfig();
		checkAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllActionConfig.addActionParameter(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_CHECK_ALL_ACTION);
		checkAllConflist.add(checkAllActionConfig);
		salesProjectionTreeTableConfig.setCheckBoxActionConfigList(checkAllConflist);
		salesProjectionTreeTableConfig.setCountUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_COUNT_ACTION);
		salesProjectionTreeTableConfig
				.setCountWsUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE);

		salesProjectionTreeTableConfig.setBulkDataUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_BULK_DATA_ACTION);
		salesProjectionTreeTableConfig.setBulkDataWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE);

		salesProjectionTreeTableConfig.setFillCountUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_FILL_COUNT_DATA_ACTION);
		salesProjectionTreeTableConfig.setFillCountWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE);

		salesProjectionTreeTableConfig.setLeftHeaderUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_COMP_LEFT_HEADER_FORM_HEADER_CONFIG_ACTION);

		salesProjectionTreeTableConfig.setLeftWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);

		salesProjectionTreeTableConfig.setRighttHeaderUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_COMP_RIGHT_HEADER_FORM_HEADER_CONFIG_ACTION);

		salesProjectionTreeTableConfig.setRightWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		salesProjectionTreeTableConfig.setLeftHeaderCustomClassLoadUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_LEFT_HEADER_CONFIG_ACTION);

		salesProjectionTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_RIGHT_HEADER_CONFIG_ACTION);

		salesProjectionTreeTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		salesProjectionTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);

		salesProjectionResultTableComponentConfig.setGtnPagedTreeTableConfig(salesProjectionTreeTableConfig);
		componentList.add(salesProjectionResultTableComponentConfig);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>(
				propertyIds.length);
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig pagedTableCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			pagedTableCustomFilterConfig.setPropertId(propertyIds[i]);
			pagedTableCustomFilterConfig.setGtnComponentType(componentType[i]);
			customFilterConfigMap.put(pagedTableCustomFilterConfig.getPropertId(), pagedTableCustomFilterConfig);

		}
		return customFilterConfigMap;
	}

	private void addUpdatePreviousNextCloseSubmitButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		UpdatePreviousNextCloseSubmitButton salesProjectionButtonLayout = new UpdatePreviousNextCloseSubmitButton();
		salesProjectionButtonLayout.addCommonButtonLayout(componentList, parentComponentId, nameSpace);
		salesProjectionButtonLayout.addUpdateButton(componentList, nameSpace);
		salesProjectionButtonLayout.addPreviousButton(componentList, nameSpace);
		salesProjectionButtonLayout.addNextButton(componentList, nameSpace);
		salesProjectionButtonLayout.addCloseButton(componentList, nameSpace);
		salesProjectionButtonLayout.addSubmitButton(componentList, nameSpace);

	}
}
