package com.stpl.gtn.gtn2o.registry.config.salesprojection;

import java.util.ArrayList;
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
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

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
