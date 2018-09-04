package com.stpl.gtn.gtn2o.registry.config.discountprojection;

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
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
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
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkDiscountProjectionTabConfig {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDiscountProjectionTabConfig.class);
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	//method to add components in Discount Projection Tab
	public void addDiscountProjectionComponents(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		gtnLogger.info("Started the execution of addDiscountProjectionComponents()");
		addDiscountProjectionRootLayout(componentList, nameSpace);
	}

	//method to Discount Projection Root Layout
	private void addDiscountProjectionRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		gtnLogger.info("Started the execution of addDiscountProjectionRootLayout()");
		GtnUIFrameworkComponentConfig discountProjectionRootLayout = new GtnUIFrameworkComponentConfig();
		discountProjectionRootLayout.setComponentId(nameSpace + "_" + "rootLayout");
		discountProjectionRootLayout.setComponentWidth("100%");
		discountProjectionRootLayout.setAddToParent(false);
		discountProjectionRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		discountProjectionRootLayout.setSpacing(true);
		discountProjectionRootLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		discountProjectionRootLayout.setGtnLayoutConfig(layout);
		componentList.add(discountProjectionRootLayout);

		addDiscountProjectionMainPanel(componentList, discountProjectionRootLayout.getComponentId(), nameSpace);
		addDiscountProjectionPanel(componentList, discountProjectionRootLayout.getComponentId(), nameSpace);
		addUpdatePreviousNextCloseSubmitButtonLayout(componentList, discountProjectionRootLayout.getComponentId(), nameSpace);
		
	}

	//method to add Discount Projection MainPanel for Root layout
	private void addDiscountProjectionMainPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
		gtnLogger.info("Started the execution of addDiscountProjectionMainPanel()");
		GtnUIFrameworkComponentConfig discountProjectionMainPanel = new GtnUIFrameworkComponentConfig();
		discountProjectionMainPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		discountProjectionMainPanel.setComponentId(nameSpace + "_" + "mainPanel");
		discountProjectionMainPanel.setAddToParent(true);
		discountProjectionMainPanel.setParentComponentId(parentComponentId);
		discountProjectionMainPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(discountProjectionMainPanel);

		addDiscountProjectionMainLayout(componentList, nameSpace);
		
	}

	private void addDiscountProjectionMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		gtnLogger.info("Started the execution of addDiscountProjectionMainLayout()");
		GtnUIFrameworkComponentConfig discountProjectionMainLayout = configProvider
				.getCssLayoutConfig(nameSpace + "_" + "mainLayout", true, nameSpace + "_" + "mainPanel");
		discountProjectionMainLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		discountProjectionMainLayout.setComponentWidth("100%");
		componentList.add(discountProjectionMainLayout);
		
		addDiscountProjDisplaySelectionFilterTab(componentList, discountProjectionMainLayout.getComponentId(), nameSpace);
		new GenerateResetButton().addGenerateResetButtonLayout(componentList, nameSpace + "_" + "rootLayout", nameSpace);
	}

	private void addDiscountProjDisplaySelectionFilterTab(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		
		
		GtnUIFrameworkComponentConfig tabLayoutAddDiscountProj = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "displaySelectionFilterTabLayout", true, parentComponentId);
		tabLayoutAddDiscountProj.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		tabLayoutAddDiscountProj.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabLayoutAddDiscountProj);

		GtnUIFrameworkComponentConfig tabSheetAddDiscountProj = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "displaySelectionFilterTabSheet", true, nameSpace + "_" + "displaySelectionFilterTabLayout",
				GtnUIFrameworkComponentType.TABSHEET);
		tabSheetAddDiscountProj.setComponentName("Tab Sheet");
		tabSheetAddDiscountProj.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheetAddDiscountProj);

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
		tabSheetAddDiscountProj.setGtnTabSheetConfigList(gtnTabSheetConfigList);
			
	}

	private void addDisplaySelectionTab(List<GtnUIFrameworkComponentConfig> componentList,
			String nameSpace) {
		DisplaySelectionTab displaySelectionTab = new DisplaySelectionTab();
		displaySelectionTab.addDisplaySelectionTabLayout(componentList,nameSpace);
		displaySelectionTab.addFrequencyHistory(componentList, nameSpace);
		displaySelectionTab.addActualsProjDiscountVariables(componentList, nameSpace);
		displaySelectionTab.addProjPeriodOrderUnitOfmeasure(componentList, nameSpace);
		displaySelectionTab.addDisplayCurrencyFormat(componentList, nameSpace);
	}

	private void addFilterTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		FilterTab filterTab = new FilterTab();
		
		GtnUIFrameworkComponentConfig discountProjFilterLayoutConfig = configProvider
				.getVerticalLayoutConfig(nameSpace + "_" + "discountProjFilterLayoutConfig", false, null);
		discountProjFilterLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		discountProjFilterLayoutConfig.setTabComponent(true);
		componentList.add(discountProjFilterLayoutConfig);

		GtnUIFrameworkLayoutConfig discountProjFiilterInnerLayout = new GtnUIFrameworkLayoutConfig();
		discountProjFiilterInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		discountProjFiilterInnerLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutConfig.setComponentId(nameSpace + "_" + "discountProjFiilterInnerLayout");
		filterInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		filterInnerLayoutConfig.setSpacing(Boolean.TRUE);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4);
		filterInnerLayoutConfig.setParentComponentId(discountProjFilterLayoutConfig.getComponentId());
		filterInnerLayoutConfig.setGtnLayoutConfig(discountProjFiilterInnerLayout);
		componentList.add(filterInnerLayoutConfig);
		
		filterTab.addCustomerLevel(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addProductLevel(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addDeductionLevel(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addDeductionInclusion(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addCustomerFilter(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addProductFilter(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
		filterTab.addDeductionFilter(componentList,filterInnerLayoutConfig.getComponentId(), nameSpace);
	}

	private void addDiscountProjectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig discountProjectionPanel = configProvider
				.getPanelConfig(nameSpace + "_" + "discountProjectionPanel", true, parentComponentId);
		discountProjectionPanel.setComponentName("Discount Projection");
		discountProjectionPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(discountProjectionPanel);
		
		GtnUIFrameworkComponentConfig discountProjectionPanelLayout = configProvider.getVerticalLayoutConfig(nameSpace+"_"+ "discountProjectionPanelLayout", true, discountProjectionPanel.getComponentId());
		discountProjectionPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(discountProjectionPanelLayout);

		addMassUpdateForecastAdjustmentTabSheetPanel(componentList, discountProjectionPanelLayout.getComponentId(), nameSpace);
		new ResultsLayout().addResultsLayout(componentList, discountProjectionPanelLayout.getComponentId(), nameSpace);
		addDiscountProjectionResultTable(componentList, discountProjectionPanelLayout.getComponentId(), nameSpace);
		addDiscountProjectionExcelRefreshButton(componentList, discountProjectionPanelLayout.getComponentId(), nameSpace);
	}
	
	public void addMassUpdateForecastAdjustmentTabSheetPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String nameSpace) {
		GtnUIFrameworkComponentConfig tabSheetPanel = configProvider.getPanelConfig(nameSpace + "_" + "tabSheetPanel",
				true, parentComponentId);
		tabSheetPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheetPanel);

		addDiscountProjectionTabSheet(componentList, tabSheetPanel.getComponentId(), nameSpace);

	}

	private void addDiscountProjectionTabSheet(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String nameSpace) {
			
		GtnUIFrameworkComponentConfig discountProjectionTabLayout = configProvider
				.getHorizontalLayoutConfig(nameSpace + "_" + "discountProjTabLayout", true, parentComponentId);
		discountProjectionTabLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		discountProjectionTabLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(discountProjectionTabLayout);

		GtnUIFrameworkComponentConfig discountProjectionTabSheet = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "discountProjTabSheet", true, discountProjectionTabLayout.getComponentId(),
				GtnUIFrameworkComponentType.TABSHEET);
		discountProjectionTabSheet.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(discountProjectionTabSheet);

		GtnUIFrameworkTabConfig massUpdateTabConfigDiscountProjTab = new GtnUIFrameworkTabConfig();
		massUpdateTabConfigDiscountProjTab.setComponentId(nameSpace + "_" + "massUpdateTab");
		massUpdateTabConfigDiscountProjTab.setTabCaption("Mass Update");
		List<GtnUIFrameworkComponentConfig> massUpdateComponentList = new ArrayList<>();
		massUpdateTabConfigDiscountProjTab.setTabLayoutComponentConfigList(massUpdateComponentList);
		new MassUpdateTab().addMassUpdateTab(massUpdateComponentList, nameSpace);

		GtnUIFrameworkTabConfig forecastTabConfigDiscountProjTab = new GtnUIFrameworkTabConfig();
		forecastTabConfigDiscountProjTab.setComponentId(nameSpace + "_" + "forecastTab");
		forecastTabConfigDiscountProjTab.setTabCaption("Forecast");
		List<GtnUIFrameworkComponentConfig> forecastComponentList = new ArrayList<>();
		forecastTabConfigDiscountProjTab.setTabLayoutComponentConfigList(forecastComponentList);
	    addForecastTab(forecastComponentList, nameSpace);

		GtnUIFrameworkTabConfig adjustmentTab = new GtnUIFrameworkTabConfig();
		adjustmentTab.setComponentId(nameSpace + "_" + "adjustmentTab");
		adjustmentTab.setTabCaption("Adjustment");
		List<GtnUIFrameworkComponentConfig> adjustmentComponentList = new ArrayList<>();
		adjustmentTab.setTabLayoutComponentConfigList(adjustmentComponentList);
		addAdjustmentTab(adjustmentComponentList, nameSpace);

		List<GtnUIFrameworkTabConfig> gtnTabSheetConfigList = new ArrayList<>();
		gtnTabSheetConfigList.add(massUpdateTabConfigDiscountProjTab);
		gtnTabSheetConfigList.add(forecastTabConfigDiscountProjTab);
		gtnTabSheetConfigList.add(adjustmentTab);
		discountProjectionTabSheet.setGtnTabSheetConfigList(gtnTabSheetConfigList);
	}

	private void addAdjustmentTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		AdjustmentTab adjustmentTab=new AdjustmentTab();
		adjustmentTab.addAdjustmentTabLayout(componentList, nameSpace);
		adjustmentTab.addTypeOptionGroup(componentList, nameSpace);
		adjustmentTab.addBasisOptionGroup(componentList, nameSpace);
		adjustmentTab.addAllocationMehodologyComboBox(componentList, nameSpace);
		adjustmentTab.addAdjustmentPeriodComboBox(componentList, nameSpace);
		adjustmentTab.addAdjustButton(componentList, nameSpace);
	}

	private void addForecastTab(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		ForecastTab forecastComponent=new ForecastTab();
		forecastComponent.addForecastTabLayout(componentList, nameSpace);
		forecastComponent.addMethodologyComboBox(componentList, nameSpace);
		forecastComponent.addStartPeriodForecastComboBox(componentList,  nameSpace);
		forecastComponent.addEndPeriodForecastComboBox(componentList,  nameSpace);
		forecastComponent.addCalculateButton(componentList,  nameSpace);

	}
	
	private void addDiscountProjectionResultTable(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace){
		
		GtnUIFrameworkComponentConfig discountProjectionResultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionResultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_TREE_GRID);
		discountProjectionResultTableComponentConfig.setComponentId(nameSpace+"resultPagedTreeGrid");
		discountProjectionResultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		discountProjectionResultTableComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		discountProjectionResultTableComponentConfig.setAddToParent(true);
		discountProjectionResultTableComponentConfig.setParentComponentId(parentComponentId);
		

		GtnUIFrameworkPagedTreeTableConfig discountProjectionGtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> discountProjectionActionConfigList = new ArrayList<>();
		discountProjectionActionConfigList.add(parentComponentId);
		GtnUIFrameWorkActionConfig reportingDashboardGtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		reportingDashboardGtnUIFrameWorkActionConfig.setActionParameterList(discountProjectionActionConfigList);
		discountProjectionGtnPagedTreeTableConfig.setGtnUIFrameWorkActionConfig(reportingDashboardGtnUIFrameWorkActionConfig);

		discountProjectionGtnPagedTreeTableConfig.setLeftHeader(
				GtnWsForecastReturnsConstants.GTN_WS_FORECAST_DISCOUNT_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
		discountProjectionGtnPagedTreeTableConfig.setRightHeader(
				GtnWsForecastReturnsConstants.GTN_WS_FORECAST_DISCOUNT_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);
		discountProjectionGtnPagedTreeTableConfig.setCountUrl("");
		discountProjectionGtnPagedTreeTableConfig.setItemPerPage(10);

		discountProjectionGtnPagedTreeTableConfig.setMaxSplitPosition(1000);
		discountProjectionGtnPagedTreeTableConfig.setMinSplitPosition(300);
		discountProjectionGtnPagedTreeTableConfig.setPageLength(15);
		discountProjectionGtnPagedTreeTableConfig.setResultSetUrl("");

		discountProjectionGtnPagedTreeTableConfig.setSplitPosition(493);
		discountProjectionGtnPagedTreeTableConfig.setTableHeight("650px");
		discountProjectionGtnPagedTreeTableConfig.setDoubleHeaderVisible(true);
		discountProjectionGtnPagedTreeTableConfig.setTripleHeaderVisible(true);

		discountProjectionGtnPagedTreeTableConfig.setLeftTableEditable(true);
		discountProjectionGtnPagedTreeTableConfig.setRightTableEditable(true);
		
		discountProjectionGtnPagedTreeTableConfig.setEnableRadioButtonInSingleHeader(true);
		discountProjectionGtnPagedTreeTableConfig.setEnableCheckBoxInDoubleHeader(true);
		discountProjectionGtnPagedTreeTableConfig.setEnableCheckBoxInTripleHeader(true);

		List<String> resultDashboardFieldFactoryColum = Arrays.asList(GtnFrameworkCommonConstants.CHECK);
		List<GtnUIFrameworkComponentConfig> resultDashboardFieldFactoryComponent = new ArrayList<>();
		discountProjectionGtnPagedTreeTableConfig.setLeftTableEditableColumnList(resultDashboardFieldFactoryColum);

		GtnUIFrameworkComponentConfig reportingDashboardCheckBox = new GtnUIFrameworkComponentConfig();
		reportingDashboardCheckBox.setComponentId(GtnFrameworkCommonConstants.CHECK);
		reportingDashboardCheckBox.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
		GtnUIFrameworkCheckBoxComponentConfig reportingDashboardCheckBoxConfig = new GtnUIFrameworkCheckBoxComponentConfig();
		reportingDashboardCheckBoxConfig.setImmediate(true);

		reportingDashboardCheckBox.setGtnCheckBoxConfig(reportingDashboardCheckBoxConfig);
		List<GtnUIFrameWorkActionConfig> resultDashboardCheckBoxClickActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resultDashboardGtnUIFrameWorkGenerateActionConfig = new GtnUIFrameWorkActionConfig();
		resultDashboardGtnUIFrameWorkGenerateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resultDashboardCheckBoxClickActionList.add(resultDashboardGtnUIFrameWorkGenerateActionConfig);
		resultDashboardGtnUIFrameWorkGenerateActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));
		reportingDashboardCheckBox.setGtnUIFrameWorkItemClickActionConfigList(resultDashboardCheckBoxClickActionList);

		resultDashboardFieldFactoryComponent.add(reportingDashboardCheckBox);
		discountProjectionGtnPagedTreeTableConfig.setLeftTableEditableComponentConfig(resultDashboardFieldFactoryComponent);

		List<GtnUIFrameWorkActionConfig> resultDashboardTextFieldConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig resultDashboardFieldFactoryCustomAction = new GtnUIFrameWorkActionConfig();

		resultDashboardFieldFactoryCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));

		resultDashboardFieldFactoryCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resultDashboardTextFieldConfig.add(resultDashboardFieldFactoryCustomAction);
		discountProjectionGtnPagedTreeTableConfig.setComponentconfigActionlist(resultDashboardTextFieldConfig);

		discountProjectionGtnPagedTreeTableConfig.setCheckBoxVisibleColoumn(Arrays.asList
                                                      (GtnFrameworkScreenRegisteryConstants.ADD_DISCOUNT_PROJECTION_RESULT_TABLE)); 
		List<GtnUIFrameWorkActionConfig> resultDashboardCheckAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig resultDashboardCheckAllActionConfig = new GtnUIFrameWorkActionConfig();
		resultDashboardCheckAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resultDashboardCheckAllActionConfig.addActionParameter(GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_CHECK_ALL_ACTION);
		resultDashboardCheckAllConflist.add(resultDashboardCheckAllActionConfig);
		discountProjectionGtnPagedTreeTableConfig.setCheckBoxActionConfigList(resultDashboardCheckAllConflist);
		discountProjectionGtnPagedTreeTableConfig
				.setCountUrl(GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_COUNT_ACTION);
		discountProjectionGtnPagedTreeTableConfig
				.setCountWsUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE);

		discountProjectionGtnPagedTreeTableConfig.setBulkDataUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_BULK_DATA_ACTION);
		discountProjectionGtnPagedTreeTableConfig.setBulkDataWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE);

		discountProjectionGtnPagedTreeTableConfig.setFillCountUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_FILL_COUNT_DATA_ACTION);
		discountProjectionGtnPagedTreeTableConfig.setFillCountWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE);

		discountProjectionGtnPagedTreeTableConfig.setLeftHeaderUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_COMP_LEFT_HEADER_FORM_HEADER_CONFIG_ACTION);

		discountProjectionGtnPagedTreeTableConfig.setLeftWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);

		discountProjectionGtnPagedTreeTableConfig.setRighttHeaderUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_COMP_RIGHT_HEADER_FORM_HEADER_CONFIG_ACTION);

		discountProjectionGtnPagedTreeTableConfig.setRightWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		discountProjectionGtnPagedTreeTableConfig.setLeftHeaderCustomClassLoadUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_LEFT_HEADER_CONFIG_ACTION);

		discountProjectionGtnPagedTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				GtnCommercialForecastProjectionVarianceClassConstants.GTN_WS_RETURNS_FORECAST_RIGHT_HEADER_CONFIG_ACTION);

		discountProjectionGtnPagedTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);
		
		discountProjectionGtnPagedTreeTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		
		discountProjectionResultTableComponentConfig.setGtnPagedTreeTableConfig(discountProjectionGtnPagedTreeTableConfig);
		componentList.add(discountProjectionResultTableComponentConfig);
		
	}
	
	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMapDiscProjTab = new HashMap<>();
		String[] propertyIds={GtnFrameworkScreenRegisteryConstants.ADD_DISCOUNT_PROJECTION_RESULT_TABLE, "filterTextBox"};
	
		GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.COMBOBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8};
		
		String[] comboboxIds={GtnFrameworkScreenRegisteryConstants.ADD_DISCOUNT_PROJECTION_RESULT_TABLE};
		String[] comboboxType={"STATUS"};
		int comboboxStart=0;
		
		for (int i = 0; i < propertyIds.length; i++) {
			
			GtnUIFrameworkPagedTableCustomFilterConfig pagedTableCustomFilterConfigDiscProjTab = new GtnUIFrameworkPagedTableCustomFilterConfig();
			pagedTableCustomFilterConfigDiscProjTab.setPropertId(propertyIds[i]);
			pagedTableCustomFilterConfigDiscProjTab.setGtnComponentType(componentType[i]);
			customFilterConfigMapDiscProjTab.put(pagedTableCustomFilterConfigDiscProjTab.getPropertId(), pagedTableCustomFilterConfigDiscProjTab);
			
			//for comboBox 
			if((comboboxStart<comboboxIds.length) && propertyIds[i].equals(comboboxIds[comboboxStart])){
				GtnUIFrameworkComponentConfig companyMasterSearchFilterComponentConfigDiscProjTab = new GtnUIFrameworkComponentConfig();
				companyMasterSearchFilterComponentConfigDiscProjTab.setComponentId("discountProjectionCustomFilterComboBox");
				companyMasterSearchFilterComponentConfigDiscProjTab.setComponentName("discountProjectionCustomFilterComboBox");
				companyMasterSearchFilterComponentConfigDiscProjTab.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				companyMasterSearchFilterComponentConfigDiscProjTab.getGtnComboboxConfig().setComboBoxType(comboboxType[comboboxStart]);
				companyMasterSearchFilterComponentConfigDiscProjTab.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				pagedTableCustomFilterConfigDiscProjTab.setGtnComponentConfig(companyMasterSearchFilterComponentConfigDiscProjTab);
				comboboxStart++;
			}
			customFilterConfigMapDiscProjTab.put(pagedTableCustomFilterConfigDiscProjTab.getPropertId(), pagedTableCustomFilterConfigDiscProjTab);
		}
		
		return customFilterConfigMapDiscProjTab;
	}

	
	private void addDiscountProjectionExcelRefreshButton(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig excelBtnLayout = configProvider.getHorizontalLayoutConfig(nameSpace + "_" + "excelButtonlayout", true, parentComponentId);
		componentList.add(excelBtnLayout);

		GtnUIFrameworkComponentConfig excelButton = configProvider.getUIFrameworkComponentConfig(
				nameSpace + "_" + "excelButton", true, excelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButton.setAuthorizationIncluded(true);
		componentList.add(excelButton);
		
		GtnUIFrameworkComponentConfig resetButton = configProvider.getUIFrameworkComponentConfig(nameSpace + "_" + "pmpyButton", true, excelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentName("RESET");
		componentList.add(resetButton);
		
		GtnUIFrameworkComponentConfig refreshButton = configProvider.getUIFrameworkComponentConfig(nameSpace + "_" + "refreshButton", true, excelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		refreshButton.setComponentName("REFRESH");
		componentList.add(refreshButton);

	}
	private void addUpdatePreviousNextCloseSubmitButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId, String nameSpace) {
		UpdatePreviousNextCloseSubmitButton discountProjectionButtonLayout=new UpdatePreviousNextCloseSubmitButton();
		discountProjectionButtonLayout.addCommonButtonLayout(componentList,  parentComponentId, nameSpace);
		discountProjectionButtonLayout.addUpdateButton(componentList, nameSpace);
		discountProjectionButtonLayout.addPreviousButton(componentList, nameSpace);
		discountProjectionButtonLayout.addNextButton(componentList, nameSpace);
		discountProjectionButtonLayout.addCloseButton(componentList, nameSpace);
		discountProjectionButtonLayout.addSubmitButton(componentList, nameSpace);
		
	}
}
