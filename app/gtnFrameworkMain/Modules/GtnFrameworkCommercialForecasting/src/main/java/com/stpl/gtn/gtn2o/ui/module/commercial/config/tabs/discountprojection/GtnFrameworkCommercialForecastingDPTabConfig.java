package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.discountprojection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabSheetLoadType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.GtnFrameworkExpandCollapseLevelSection;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingClassConstants;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkCommercialForecastingDPTabConfig {

	private final GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
	private final String tabName = "discountProjectionTab";

	public void addDiscountProjectionTab(List<GtnUIFrameworkComponentConfig> componentList)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutConfig.getRootLayoutConfig(
				"commercialForecastingDiscountProjectionRootLayout", GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rootLayoutConfig.setSpacing(Boolean.TRUE);
		componentList.add(rootLayoutConfig);

		addSalesProjectionSelection(componentList, rootLayoutConfig.getComponentId());
		addResetAndGenerate(componentList, rootLayoutConfig.getComponentId());
		addDiscountProjectionParentPanel(componentList, rootLayoutConfig.getComponentId());
		addDiscountProjectionPanel(componentList, "discountProjectionVerticalLayout");
		addResultsPanel(componentList, "discountProjectionVerticalLayout");
		addResultTable(componentList, "discountProjectionVerticalLayout");
		addResetRefreshButtons(componentList, "discountProjectionVerticalLayout");

	}

	private void addSalesProjectionSelection(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig mainLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionMainLayout", parentId);
		mainLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		mainLayoutConfig.setSpacing(Boolean.TRUE);
		mainLayoutConfig.setMargin(Boolean.TRUE);
		componentList.add(mainLayoutConfig);

		GtnUIFrameworkComponentConfig discountProjectionSalesProjectionSelectionPanel = layoutConfig
				.getPanelConfig("discountProjectionSelectionPanel", mainLayoutConfig.getComponentId());
		discountProjectionSalesProjectionSelectionPanel.setComponentName("Discount Projection Selection ");
		discountProjectionSalesProjectionSelectionPanel
				.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_8.toString());
		componentList.add(discountProjectionSalesProjectionSelectionPanel);

		GtnUIFrameworkComponentConfig discountProjectionProgramLevelPanel = layoutConfig
				.getPanelConfig("discountProjectionProgramLevelPanel", mainLayoutConfig.getComponentId());
		discountProjectionProgramLevelPanel.setComponentName("Program Level ");
		discountProjectionProgramLevelPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_4.toString());
		componentList.add(discountProjectionProgramLevelPanel);

		GtnUIFrameworkComponentConfig discountProjectionSalesProjectionSelectionPanelLayout = layoutConfig
				.getVerticalLayoutConfig("discountProjectionSalesProjectionSelectionPanelLayout",
						discountProjectionSalesProjectionSelectionPanel.getComponentId());
		componentList.add(discountProjectionSalesProjectionSelectionPanelLayout);

		GtnUIFrameworkComponentConfig discountProjectionProgramLevelPanelLayout = layoutConfig.getVerticalLayoutConfig(
				"discountProjectionProgramLevelPanel", discountProjectionProgramLevelPanel.getComponentId());
		componentList.add(discountProjectionProgramLevelPanelLayout);

		GtnUIFrameworkComponentConfig discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout = layoutConfig
				.getCssLayoutConfig("discountProjectionFrequencyActualsProjectionsProjectionPeriodOrderCssLayout",
						discountProjectionSalesProjectionSelectionPanelLayout.getComponentId());
		discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout.setComponentWidth("35%");
		componentList.add(discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout);

		GtnUIFrameworkComponentConfig discountProjectionHistoryProjectionVariablesCssLayout = layoutConfig
				.getCssLayoutConfig("discountProjectionHistoryProjectionVariablesCssLayout",
						discountProjectionSalesProjectionSelectionPanelLayout.getComponentId());
		discountProjectionHistoryProjectionVariablesCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		discountProjectionHistoryProjectionVariablesCssLayout.setComponentWidth("35%");
		componentList.add(discountProjectionHistoryProjectionVariablesCssLayout);

		GtnUIFrameworkComponentConfig discountProjectionProgramLevelSelectionCssLayout = layoutConfig
				.getCssLayoutConfig("discountProjectionProgramLevelSelectionCssLayout",
						discountProjectionProgramLevelPanelLayout.getComponentId());
		discountProjectionProgramLevelSelectionCssLayout
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		discountProjectionProgramLevelSelectionCssLayout.setComponentWidth("45%");
		componentList.add(discountProjectionProgramLevelSelectionCssLayout);


		addFrequency(componentList, discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout.getComponentId());
	
		addActualsProjection(componentList,
				discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout.getComponentId());
		
		addProjectionPeriodOrder(componentList,
				discountProjectionFrequencyActualsProjectionsPeriodOrderCssLayout.getComponentId());

		addHistory(componentList, discountProjectionHistoryProjectionVariablesCssLayout.getComponentId());

		addVariables(componentList, discountProjectionHistoryProjectionVariablesCssLayout.getComponentId());

		addProgramLevel(componentList, discountProjectionProgramLevelSelectionCssLayout.getComponentId());

		addProgramSelection(componentList, discountProjectionProgramLevelSelectionCssLayout.getComponentId());

		addYear(componentList, discountProjectionProgramLevelSelectionCssLayout.getComponentId());
	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig forecastReturnsFrequencyConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionForecastReturnsFrequency", parentId);
		forecastReturnsFrequencyConfig.addComponentStyle(GtnFrameworkCssConstants.MARGIN_LEFT_MINUS_85_PX);
		componentList.add(forecastReturnsFrequencyConfig);

		GtnUIFrameworkComponentConfig frequencyComponentConfig = new GtnUIFrameworkComponentConfig();
		frequencyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		frequencyComponentConfig.setComponentId("discountProjectionForecastReturnsFrequency");
		frequencyComponentConfig.setComponentName("Frequency");
		frequencyComponentConfig.setAddToParent(Boolean.TRUE);
		frequencyComponentConfig.setParentComponentId(forecastReturnsFrequencyConfig.getComponentId());
		frequencyComponentConfig.addDependentComponent("discountProjectionForecastReturnsHistory");

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
				.getHorizontalLayoutConfig("discountProjectionActualsProjection", parentId);
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig actualsProjectionOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		actualsProjectionOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		actualsProjectionOptionGroupConfig.setComponentId("discountProjectionActualsProjectionOptionGroup");
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

		GtnUIFrameworkComponentConfig hLConfig = layoutConfig.getHorizontalLayoutConfig("discountProjectionPeriodOrder",
				parentId);
		hLConfig.addComponentStyle(GtnFrameworkCssConstants.MARGIN_LEFT_PLUS_85_PX);
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig projectionPeriodOrderOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		projectionPeriodOrderOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionPeriodOrderOptionGroupConfig.setComponentId("discountProjectionPeriodOrderOptionGroup");
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
				.getHorizontalLayoutConfig("discountProjectionHistory", parentId);
		forecastReturnsHistoryConfig.addComponentStyle(GtnFrameworkCssConstants.MARGIN_LEFT_MINUS_85_PX);
		componentList.add(forecastReturnsHistoryConfig);

		GtnUIFrameworkComponentConfig historyComponentConfig = new GtnUIFrameworkComponentConfig();
		historyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		historyComponentConfig.setComponentId("discountProjectionHistory");
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

		GtnUIFrameworkComponentConfig hLConfig = layoutConfig.getHorizontalLayoutConfig("discountProjectionVariables",
				parentId);
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig projectionPeriodOrderOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		projectionPeriodOrderOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionPeriodOrderOptionGroupConfig.setComponentId("discountProjectionVariablesCheckboxGroup");
		projectionPeriodOrderOptionGroupConfig.setComponentName("Variables:");
		projectionPeriodOrderOptionGroupConfig.setAddToParent(Boolean.TRUE);
		projectionPeriodOrderOptionGroupConfig.setParentComponentId(hLConfig.getComponentId());

		GtnUIFrameworkOptionGroupConfig checkboxGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		checkboxGroupConfig.setItemValues(
				Arrays.asList(new String[] { "Discount Rate", "Rebate Per Unit", "Discount Amount", "Growth" }));
		checkboxGroupConfig.setValuesFromService(Boolean.FALSE);
		checkboxGroupConfig.setIsMultiSelect(true);
		projectionPeriodOrderOptionGroupConfig.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionPeriodOrderOptionGroupConfig.setGtnUIFrameworkOptionGroupConfig(checkboxGroupConfig);
		componentList.add(projectionPeriodOrderOptionGroupConfig);
	}

	private void addProgramLevel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig hLConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionProgramLevel", parentId);
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig discountProjectionProgramLevelOptionGroupCompConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionProgramLevelOptionGroupCompConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		discountProjectionProgramLevelOptionGroupCompConfig.setComponentId("discountProjectionProgramLevelOptionGroup");
		discountProjectionProgramLevelOptionGroupCompConfig.setComponentName("Level:");
		discountProjectionProgramLevelOptionGroupCompConfig.setAddToParent(Boolean.TRUE);
		discountProjectionProgramLevelOptionGroupCompConfig.setParentComponentId(hLConfig.getComponentId());

		GtnUIFrameworkOptionGroupConfig programLevelOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		programLevelOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Program Category", "Program" }));
		programLevelOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		discountProjectionProgramLevelOptionGroupCompConfig
				.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		discountProjectionProgramLevelOptionGroupCompConfig
				.setGtnUIFrameworkOptionGroupConfig(programLevelOptionGroupConfig);
		componentList.add(discountProjectionProgramLevelOptionGroupCompConfig);
	}

	private void addProgramSelection(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig discountProjectionHistoryLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionProgramSelection", parentId);
		componentList.add(discountProjectionHistoryLayoutConfig);

		GtnUIFrameworkComponentConfig discountProjectionProgramSelectionButtonConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionProgramSelectionButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		discountProjectionProgramSelectionButtonConfig.setComponentId("discountProjectionProgramSelectionButton");
		discountProjectionProgramSelectionButtonConfig.setComponentName("Program");
		discountProjectionProgramSelectionButtonConfig
				.setParentComponentId(discountProjectionHistoryLayoutConfig.getComponentId());
		discountProjectionProgramSelectionButtonConfig.setAddToParent(Boolean.TRUE);
		componentList.add(discountProjectionProgramSelectionButtonConfig);

		GtnUIFrameworkComponentConfig discountProjectionProgramSelectionComboboxConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionProgramSelectionComboboxConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		discountProjectionProgramSelectionComboboxConfig.setComponentId("discountProjectionProgramSelectionCombobox");
		discountProjectionProgramSelectionComboboxConfig.setAddToParent(Boolean.TRUE);
		discountProjectionProgramSelectionComboboxConfig
				.setParentComponentId(discountProjectionHistoryLayoutConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig historyConfig = new GtnUIFrameworkComboBoxConfig();
		discountProjectionProgramSelectionComboboxConfig.setGtnComboboxConfig(historyConfig);
		componentList.add(discountProjectionProgramSelectionComboboxConfig);
	}

	private void addYear(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig discountProjectionYearSelectionLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionYearSelection", parentId);
		componentList.add(discountProjectionYearSelectionLayoutConfig);

		GtnUIFrameworkComponentConfig discountProjectionYearSelectionComboboxtConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionYearSelectionComboboxtConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		discountProjectionYearSelectionComboboxtConfig.setComponentId("discountProjectionYearSelection");
		discountProjectionYearSelectionComboboxtConfig.setComponentName("Year");
		discountProjectionYearSelectionComboboxtConfig.setAddToParent(Boolean.TRUE);
		discountProjectionYearSelectionComboboxtConfig
				.setParentComponentId(discountProjectionYearSelectionLayoutConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig historyConfig = new GtnUIFrameworkComboBoxConfig();
		discountProjectionYearSelectionComboboxtConfig.setGtnComboboxConfig(historyConfig);
		componentList.add(discountProjectionYearSelectionComboboxtConfig);
	}

	private void addResetAndGenerate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig discountProjectionResetAndGenerateLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionResetAndGenerateLayout", parentId);
		componentList.add(discountProjectionResetAndGenerateLayoutConfig);

		GtnUIFrameworkComponentConfig resetButtonConfig = new GtnUIFrameworkComponentConfig();
		resetButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentId("discountProjectionResetButton");
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setParentComponentId(discountProjectionResetAndGenerateLayoutConfig.getComponentId());
		resetButtonConfig.setAddToParent(Boolean.TRUE);
		componentList.add(resetButtonConfig);

		GtnUIFrameworkComponentConfig generateButtonConfig = new GtnUIFrameworkComponentConfig();
		generateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateButtonConfig.setComponentId("discountProjectionGenerateButton");
		generateButtonConfig.setComponentName("GENERATE");
		generateButtonConfig.setParentComponentId(discountProjectionResetAndGenerateLayoutConfig.getComponentId());
		generateButtonConfig.setAddToParent(true);
		componentList.add(generateButtonConfig);
	}

	private void addDiscountProjectionParentPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String componentId) {
		GtnUIFrameworkComponentConfig discountProjectionParentPanel = new GtnUIFrameworkComponentConfig();
		discountProjectionParentPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		discountProjectionParentPanel.setComponentId("discountProjectionParentPanel");
		discountProjectionParentPanel.setParentComponentId(componentId);
		discountProjectionParentPanel.setComponentWidth("100%");
		discountProjectionParentPanel.setAddToParent(Boolean.TRUE);
		discountProjectionParentPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(discountProjectionParentPanel);
		GtnUIFrameworkComponentConfig salesProjectionVerticalLayout = layoutConfig
				.getVerticalLayoutConfig("discountProjection", discountProjectionParentPanel.getComponentId());
		componentList.add(salesProjectionVerticalLayout);

	}

	private void addDiscountProjectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {

		GtnUIFrameworkComponentConfig discountProjectionPanelConfig = layoutConfig.getPanelConfig("discountProjection",
				componentId);
		discountProjectionPanelConfig.setComponentName("Disount Projection ");
		discountProjectionPanelConfig.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(discountProjectionPanelConfig);
		addDiscountProjectionPanelTabSheets(componentList, discountProjectionPanelConfig.getComponentId());

	}

	private void addDiscountProjectionPanelTabSheets(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig vLayout = new GtnUIFrameworkComponentConfig();
		vLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		vLayout.setComponentId("discountProjectionVLayout");
		vLayout.setComponentName("Discount Projection Panel Tab Sheet");
		vLayout.setComponentWidth("100%");
		vLayout.setAddToParent(Boolean.TRUE);
		vLayout.setParentComponentId(parentId);
		vLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(vLayout);

		GtnUIFrameworkComponentConfig discountProjectionPanelTabSheet = new GtnUIFrameworkComponentConfig();
		discountProjectionPanelTabSheet.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		discountProjectionPanelTabSheet.setComponentId("discountProjectionPanelTabSheet");
		discountProjectionPanelTabSheet.setComponentName("Discount Projection Panel Tab Sheet");
		discountProjectionPanelTabSheet.setComponentWidth("100%");
		discountProjectionPanelTabSheet.setAddToParent(Boolean.TRUE);
		discountProjectionPanelTabSheet.setParentComponentId(vLayout.getComponentId());

		// find the No of tabs
		GtnUIFrameworkTabConfig discountProjectionMassUpdate = new GtnUIFrameworkTabConfig();
		List<GtnUIFrameworkComponentConfig> massUpdateTabLayoutComponentConfigList = new ArrayList<>();
		discountProjectionMassUpdate.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		addDiscountProjectionMassUpdateTab(massUpdateTabLayoutComponentConfigList);
		discountProjectionMassUpdate.setComponentId("discountProjectionMassUpdateTabCssLayout");
		discountProjectionMassUpdate.setTabCaption("Mass Update");
		discountProjectionMassUpdate.setTabLayoutComponentConfigList(massUpdateTabLayoutComponentConfigList);

		GtnUIFrameworkTabConfig discountProjectionForecastTab = new GtnUIFrameworkTabConfig();
		discountProjectionForecastTab.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		List<GtnUIFrameworkComponentConfig> forecastTabLayoutComponentConfigList = new ArrayList<>();
		addDiscountProjectionForecastTab(forecastTabLayoutComponentConfigList);
		discountProjectionForecastTab.setComponentId("discountProjectionForecastTabCssLayout");
		discountProjectionForecastTab.setTabCaption("Forecast");
		discountProjectionForecastTab.setTabLayoutComponentConfigList(forecastTabLayoutComponentConfigList);

		GtnUIFrameworkTabConfig discountProjectionAdjustmentTab = new GtnUIFrameworkTabConfig();
		List<GtnUIFrameworkComponentConfig> adjustmentTabLayoutComponentConfigList = new ArrayList<>();
		discountProjectionAdjustmentTab.setTabloadingType(GtnUIFrameworkTabSheetLoadType.LAZY_LOAD);
		addDiscountProjectionAdjustmentTab(adjustmentTabLayoutComponentConfigList);
		discountProjectionAdjustmentTab.setComponentId("discountProjectionAdjustmentTabCssLayout");
		discountProjectionAdjustmentTab.setTabCaption("Adjustment");
		discountProjectionAdjustmentTab.setTabLayoutComponentConfigList(adjustmentTabLayoutComponentConfigList);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(discountProjectionMassUpdate);
		tabConfigList.add(discountProjectionForecastTab);
		tabConfigList.add(discountProjectionAdjustmentTab);
		discountProjectionPanelTabSheet.setGtnTabSheetConfigList(tabConfigList);

		componentList.add(discountProjectionPanelTabSheet);

	}

	private void addDiscountProjectionMassUpdateTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig discountProjectionMassUpdateCssLayout = layoutConfig
				.getCssLayoutConfig("discountProjectionMassUpdateTab", "");
		discountProjectionMassUpdateCssLayout.setAddToParent(Boolean.FALSE);
		discountProjectionMassUpdateCssLayout.setComponentWidth("100%");
		discountProjectionMassUpdateCssLayout.setTabComponent(Boolean.TRUE);
		discountProjectionMassUpdateCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_MASS_UPDATE);
		discountProjectionMassUpdateCssLayout.setMargin(Boolean.TRUE);
		componentList.add(discountProjectionMassUpdateCssLayout);
		addDiscountProjectionFieldComponent(componentList, discountProjectionMassUpdateCssLayout.getComponentId());
		addDiscountProjectionValueComponent(componentList, discountProjectionMassUpdateCssLayout.getComponentId());
		addDiscountProjectionMassUpdateStartPeriodComponent(componentList,
				discountProjectionMassUpdateCssLayout.getComponentId());
		addDiscountProjectionMassUpdateEndPeriodComponent(componentList,
				discountProjectionMassUpdateCssLayout.getComponentId());
		addDiscountProjectionMassupdatePopulateButton(componentList,
				discountProjectionMassUpdateCssLayout.getComponentId());
	}

	private void addDiscountProjectionMassupdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig discountProjectionMassUpdatePopulateButtonConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionMassUpdatePopulateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		discountProjectionMassUpdatePopulateButtonConfig.setComponentId("discountProjectionMassUpdatePopulateButton");
		discountProjectionMassUpdatePopulateButtonConfig.setComponentName("POPULATE");
		discountProjectionMassUpdatePopulateButtonConfig.setParentComponentId(parentId);
		discountProjectionMassUpdatePopulateButtonConfig.setAddToParent(Boolean.TRUE);
		componentList.add(discountProjectionMassUpdatePopulateButtonConfig);

	}

	private void addDiscountProjectionMassUpdateEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig discountProjectionMassUpdateEndPeriodConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionMassUpdateEndPeriod", parentId);
		discountProjectionMassUpdateEndPeriodConfig
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(discountProjectionMassUpdateEndPeriodConfig);

		GtnUIFrameworkComponentConfig discountProjectionMassUpdateEndPeriodCompConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionMassUpdateEndPeriodCompConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		discountProjectionMassUpdateEndPeriodCompConfig.setComponentId("discountProjectionmassUpdateEndPeriod");
		discountProjectionMassUpdateEndPeriodCompConfig.setComponentName("End Period");
		discountProjectionMassUpdateEndPeriodCompConfig.setAddToParent(Boolean.TRUE);
		discountProjectionMassUpdateEndPeriodCompConfig
				.setParentComponentId(discountProjectionMassUpdateEndPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig discountProjectionMassUpdateEndPeriodComboboxConfig = new GtnUIFrameworkComboBoxConfig();
		discountProjectionMassUpdateEndPeriodCompConfig
				.setGtnComboboxConfig(discountProjectionMassUpdateEndPeriodComboboxConfig);
		componentList.add(discountProjectionMassUpdateEndPeriodCompConfig);
	}

	private void addDiscountProjectionMassUpdateStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig discountProjectionMassUpdateStartPeriodConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionMassUpdateStartPeriod", parentId);
		discountProjectionMassUpdateStartPeriodConfig
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(discountProjectionMassUpdateStartPeriodConfig);

		GtnUIFrameworkComponentConfig discountProjectionMassUpdateStartPeriodCompConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionMassUpdateStartPeriodCompConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		discountProjectionMassUpdateStartPeriodCompConfig.setComponentId("discountProjectionMassUpdateStartPeriod");
		discountProjectionMassUpdateStartPeriodCompConfig.setComponentName("Start Period:");
		discountProjectionMassUpdateStartPeriodCompConfig.setAddToParent(Boolean.TRUE);
		discountProjectionMassUpdateStartPeriodCompConfig
				.setParentComponentId(discountProjectionMassUpdateStartPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig discountProjectionMassUpdateStartPeriodComboboxConfig = new GtnUIFrameworkComboBoxConfig();
		discountProjectionMassUpdateStartPeriodCompConfig
				.setGtnComboboxConfig(discountProjectionMassUpdateStartPeriodComboboxConfig);
		componentList.add(discountProjectionMassUpdateStartPeriodCompConfig);
	}

	private void addDiscountProjectionFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig discountProjectionMassUpdateFieldConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionMassUpdateField", parentId);// massUpdateFieldHorizontal
		discountProjectionMassUpdateFieldConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(discountProjectionMassUpdateFieldConfig);

		GtnUIFrameworkComponentConfig discountProjectionMassUpdateFieldComponentComboboxConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionMassUpdateFieldComponentComboboxConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		discountProjectionMassUpdateFieldComponentComboboxConfig.setComponentId("discountProjectionMassUpdateField");
		discountProjectionMassUpdateFieldComponentComboboxConfig.setComponentName("Field:");
		discountProjectionMassUpdateFieldComponentComboboxConfig.setAddToParent(Boolean.TRUE);
		discountProjectionMassUpdateFieldComponentComboboxConfig
				.setParentComponentId(discountProjectionMassUpdateFieldConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		discountProjectionMassUpdateFieldComponentComboboxConfig.setGtnComboboxConfig(frequencyConfig);

		componentList.add(discountProjectionMassUpdateFieldComponentComboboxConfig);
	}

	private void addDiscountProjectionValueComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig massUpdateValueConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionMassUpdateValue", parentId);
		massUpdateValueConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(massUpdateValueConfig);

		GtnUIFrameworkComponentConfig massUpdateValueComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateValueComponentConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		massUpdateValueComponentConfig.setComponentId("discountProjectionMassUpdateValue");
		massUpdateValueComponentConfig.setComponentName("Value:");
		massUpdateValueComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateValueComponentConfig.setParentComponentId(massUpdateValueConfig.getComponentId());

		componentList.add(massUpdateValueComponentConfig);
	}

	private void addDiscountProjectionForecastTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig discountProjectionForecastTabCssLayout = layoutConfig
				.getCssLayoutConfig("discountProjectionForecastTab", "");
		discountProjectionForecastTabCssLayout.setAddToParent(Boolean.FALSE);
		discountProjectionForecastTabCssLayout.setComponentWidth("100%");
		discountProjectionForecastTabCssLayout.setTabComponent(Boolean.TRUE);
		discountProjectionForecastTabCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_MASS_UPDATE);
		componentList.add(discountProjectionForecastTabCssLayout);
		addDiscountProjectionMethodologyComponent(componentList,
				discountProjectionForecastTabCssLayout.getComponentId());
		addDiscountProjectionForecastTabStartPeriodComponent(componentList,
				discountProjectionForecastTabCssLayout.getComponentId());
		addDiscountProjectionForecastTabEndPeriodComponent(componentList,
				discountProjectionForecastTabCssLayout.getComponentId());
		addDiscountProjectionForecastTabCalculateButton(componentList,
				discountProjectionForecastTabCssLayout.getComponentId());
	}

	private void addDiscountProjectionForecastTabEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig discountProjectionForecastTabEndPeriodConfig = layoutConfig
				.getHorizontalLayoutConfig("forecastTabEndPeriod", parentId);
		discountProjectionForecastTabEndPeriodConfig
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(discountProjectionForecastTabEndPeriodConfig);

		GtnUIFrameworkComponentConfig discountProjectionForecastTabEndPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionForecastTabEndPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		discountProjectionForecastTabEndPeriodComponentConfig.setComponentId("forecastTabEndPeriod");
		discountProjectionForecastTabEndPeriodComponentConfig.setComponentName("End Period");
		discountProjectionForecastTabEndPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		discountProjectionForecastTabEndPeriodComponentConfig
				.setParentComponentId(discountProjectionForecastTabEndPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig discountProjectionForecastTabEndPeriodComboboxConfig = new GtnUIFrameworkComboBoxConfig();
		discountProjectionForecastTabEndPeriodComponentConfig
				.setGtnComboboxConfig(discountProjectionForecastTabEndPeriodComboboxConfig);
		componentList.add(discountProjectionForecastTabEndPeriodComponentConfig);

	}

	private void addDiscountProjectionForecastTabStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig discountProjectionForecastTabStartPeriodConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionForecastTabStartPeriod", parentId);
		discountProjectionForecastTabStartPeriodConfig
				.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(discountProjectionForecastTabStartPeriodConfig);

		GtnUIFrameworkComponentConfig discountProjectionForecastTabStartPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionForecastTabStartPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		discountProjectionForecastTabStartPeriodComponentConfig
				.setComponentId("discountProjectionForecastTabStartPeriod");
		discountProjectionForecastTabStartPeriodComponentConfig.setComponentName("Start Period:");
		discountProjectionForecastTabStartPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		discountProjectionForecastTabStartPeriodComponentConfig
				.setParentComponentId(discountProjectionForecastTabStartPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig forecastTabStartPeriod = new GtnUIFrameworkComboBoxConfig();
		discountProjectionForecastTabStartPeriodComponentConfig.setGtnComboboxConfig(forecastTabStartPeriod);

		componentList.add(discountProjectionForecastTabStartPeriodComponentConfig);

	}

	private void addDiscountProjectionForecastTabCalculateButton(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig discountProjectionForecastTabCalculateButtonConfig = new GtnUIFrameworkComponentConfig();
		discountProjectionForecastTabCalculateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		discountProjectionForecastTabCalculateButtonConfig
				.setComponentId("discountProjectionForecastTabCalculateButton");
		discountProjectionForecastTabCalculateButtonConfig.setComponentName("CALCULATE");
		discountProjectionForecastTabCalculateButtonConfig.setParentComponentId(parentId);
		discountProjectionForecastTabCalculateButtonConfig.setAddToParent(Boolean.TRUE);
		componentList.add(discountProjectionForecastTabCalculateButtonConfig);

	}

	private void addDiscountProjectionMethodologyComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig forecastTabMethodologyLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionForecastTabMethodology", parentId);
		forecastTabMethodologyLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(forecastTabMethodologyLayoutConfig);

		GtnUIFrameworkComponentConfig forecastTabMethodologyComponentConfig = new GtnUIFrameworkComponentConfig();
		forecastTabMethodologyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		forecastTabMethodologyComponentConfig.setComponentId("discountProjectionForecastTabMethodology");
		forecastTabMethodologyComponentConfig.setComponentName("Methodology:");
		forecastTabMethodologyComponentConfig.setAddToParent(Boolean.TRUE);
		forecastTabMethodologyComponentConfig.setParentComponentId(forecastTabMethodologyLayoutConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig methodologyConfig = new GtnUIFrameworkComboBoxConfig();
		forecastTabMethodologyComponentConfig.setGtnComboboxConfig(methodologyConfig);
		componentList.add(forecastTabMethodologyComponentConfig);

	}

	private void addDiscountProjectionAdjustmentTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig forecastTabCssLayout = layoutConfig
				.getCssLayoutConfig("discountProjectionAdjustmentTab", "");
		forecastTabCssLayout.setAddToParent(Boolean.FALSE);
		forecastTabCssLayout.setComponentWidth("100%");
		forecastTabCssLayout.setTabComponent(Boolean.TRUE);
		forecastTabCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_MASS_UPDATE);
		componentList.add(forecastTabCssLayout);
		addDiscountProjectionTypeOptionGroup(componentList, forecastTabCssLayout.getComponentId());
		addDiscountProjectionBasisOptionGroup(componentList, forecastTabCssLayout.getComponentId());
		addDiscountProjectionVariableOptionGroup(componentList, forecastTabCssLayout.getComponentId());
		addDiscountProjectionAdjustmentTabAdjustmentField(componentList, forecastTabCssLayout.getComponentId());
		addDiscountProjectionAdjustmentTabAdjustmentComboBox(componentList, forecastTabCssLayout.getComponentId());
		addDiscountProjectionAdjustmentTabAdjustmentPeriod(componentList, forecastTabCssLayout.getComponentId());
		addDiscountProjectionAdjustmentTabAdjustButton(componentList, forecastTabCssLayout.getComponentId());
	}

	private void addDiscountProjectionTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig adjustmentTypeOptionGroupLayout = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionAdjustmentTypeOptionGroup", parentId);
		adjustmentTypeOptionGroupLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(adjustmentTypeOptionGroupLayout);

		GtnUIFrameworkComponentConfig adjustmentTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		adjustmentTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		adjustmentTypeOptionGroup.setComponentId("discountProjectionAdjustmenttype");
		adjustmentTypeOptionGroup.setComponentName("Type :");
		adjustmentTypeOptionGroup.setAddToParent(Boolean.TRUE);
		adjustmentTypeOptionGroup.setParentComponentId(adjustmentTypeOptionGroupLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig salesProjectionAdjustmentTypeConfig = new GtnUIFrameworkOptionGroupConfig();
		salesProjectionAdjustmentTypeConfig.setItemValues(Arrays.asList(new String[] { "Incremental", "Override" }));
		salesProjectionAdjustmentTypeConfig.setValuesFromService(Boolean.FALSE);
		adjustmentTypeOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		adjustmentTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(salesProjectionAdjustmentTypeConfig);

		componentList.add(adjustmentTypeOptionGroup);
	}

	private void addDiscountProjectionBasisOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig basisTypeOptionGroupLayout = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionBasisTypeOptionGroup", parentId);
		basisTypeOptionGroupLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(basisTypeOptionGroupLayout);

		GtnUIFrameworkComponentConfig basisTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		basisTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		basisTypeOptionGroup.setComponentId("discountProjectionBasisType");
		basisTypeOptionGroup.setComponentName("Basis :");
		basisTypeOptionGroup.setAddToParent(Boolean.TRUE);
		basisTypeOptionGroup.setParentComponentId(basisTypeOptionGroupLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig salesProjectionBasisTypeConfig = new GtnUIFrameworkOptionGroupConfig();
		salesProjectionBasisTypeConfig.setItemValues(Arrays.asList(new String[] { "Amount", "Percentage" }));
		salesProjectionBasisTypeConfig.setValuesFromService(Boolean.FALSE);
		basisTypeOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		basisTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(salesProjectionBasisTypeConfig);

		componentList.add(basisTypeOptionGroup);
	}

	private void addDiscountProjectionVariableOptionGroup(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig variableTypeOptionGroupLayout = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionVariableTypeOptionGroup", parentId);
		variableTypeOptionGroupLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(variableTypeOptionGroupLayout);

		GtnUIFrameworkComponentConfig variableTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		variableTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		variableTypeOptionGroup.setComponentId("discountProjectionVariableType");
		variableTypeOptionGroup.setComponentName("");
		variableTypeOptionGroup.setAddToParent(Boolean.TRUE);
		variableTypeOptionGroup.setParentComponentId(variableTypeOptionGroupLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig variableTypeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		variableTypeOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Sales", "Units" }));
		variableTypeOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		variableTypeOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		variableTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(variableTypeOptionGroupConfig);

		componentList.add(variableTypeOptionGroup);
	}

	private void addDiscountProjectionAdjustmentTabAdjustmentField(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig adjustmentValueConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionAdjustmentValue", parentId);
		adjustmentValueConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(adjustmentValueConfig);

		GtnUIFrameworkComponentConfig adjustmentValueComponentConfig = new GtnUIFrameworkComponentConfig();
		adjustmentValueComponentConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		adjustmentValueComponentConfig.setComponentId("discountProjectionAdjustmentValue");
		adjustmentValueComponentConfig.setComponentName("Adjustment:");
		adjustmentValueComponentConfig.setAddToParent(Boolean.TRUE);
		adjustmentValueComponentConfig.setParentComponentId(adjustmentValueConfig.getComponentId());

		componentList.add(adjustmentValueComponentConfig);
	}

	private void addDiscountProjectionAdjustmentTabAdjustmentComboBox(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig adjustmentMethodologyLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionAdjustmentMethodology", parentId);
		adjustmentMethodologyLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(adjustmentMethodologyLayoutConfig);

		GtnUIFrameworkComponentConfig adjustmentMethodologyComponentConfig = new GtnUIFrameworkComponentConfig();
		adjustmentMethodologyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentMethodologyComponentConfig.setComponentId("discountProjectionAdjustmentMethodology");
		adjustmentMethodologyComponentConfig.setComponentName("Adjustment Methodology:");
		adjustmentMethodologyComponentConfig.setAddToParent(Boolean.TRUE);
		adjustmentMethodologyComponentConfig.setParentComponentId(adjustmentMethodologyLayoutConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig methodologyConfig = new GtnUIFrameworkComboBoxConfig();
		adjustmentMethodologyComponentConfig.setGtnComboboxConfig(methodologyConfig);
		componentList.add(adjustmentMethodologyComponentConfig);
	}

	private void addDiscountProjectionAdjustmentTabAdjustmentPeriod(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig adjustmentPeriodOptionGroupLayout = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionEnableDisableOptionGroup", parentId);
		adjustmentPeriodOptionGroupLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(adjustmentPeriodOptionGroupLayout);

		GtnUIFrameworkComponentConfig adjustmentPeriodOptionGroup = new GtnUIFrameworkComponentConfig();
		adjustmentPeriodOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		adjustmentPeriodOptionGroup.setComponentId("discountProjectionEnableDisableMode");
		adjustmentPeriodOptionGroup.setComponentName("Adjustment Periods :");
		adjustmentPeriodOptionGroup.setAddToParent(Boolean.TRUE);
		adjustmentPeriodOptionGroup.setParentComponentId(adjustmentPeriodOptionGroupLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig salesProjectionAdjustmentPeriodConfig = new GtnUIFrameworkOptionGroupConfig();
		salesProjectionAdjustmentPeriodConfig.setItemValues(Arrays.asList(new String[] { "All", "Select" }));
		salesProjectionAdjustmentPeriodConfig.setValuesFromService(Boolean.FALSE);
		adjustmentPeriodOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		adjustmentPeriodOptionGroup.setGtnUIFrameworkOptionGroupConfig(salesProjectionAdjustmentPeriodConfig);

		componentList.add(adjustmentPeriodOptionGroup);
	}

	private void addDiscountProjectionAdjustmentTabAdjustButton(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {

		GtnUIFrameworkComponentConfig adjustButtonConfig = new GtnUIFrameworkComponentConfig();
		adjustButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		adjustButtonConfig.setComponentId("discountProjectionAdjustButton");
		adjustButtonConfig.setComponentName("ADJUST");
		adjustButtonConfig.setParentComponentId(parentId);
		adjustButtonConfig.setAddToParent(Boolean.TRUE);
		componentList.add(adjustButtonConfig);
	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig discountProjectionResultsPanel = layoutConfig
				.getPanelConfig(tabName + "discountProjectionPanel", parentId);
		discountProjectionResultsPanel.setComponentName("Results");
		discountProjectionResultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(discountProjectionResultsPanel);

		GtnFrameworkExpandCollapseLevelSection conf = new GtnFrameworkExpandCollapseLevelSection();
		conf.getRootExpandCollapseSectionLayout(componentList, discountProjectionResultsPanel.getComponentId(),
				"commercialForecasting", tabName);
	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId("discountProjectionResultTable");
		resultTableComponentConfig.setComponentName("resultTable");
		resultTableComponentConfig.setAddToParent(Boolean.TRUE);
		resultTableComponentConfig.setParentComponentId(parentId);


		GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add(tabName + "discountProjectionPanelPanel");
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(actionConfigList);
		gtnPagedTreeTableConfig.setGtnUIFrameWorkActionConfig(gtnUIFrameWorkActionConfig);

		
		gtnPagedTreeTableConfig.setLeftHeader(
				GtnFrameworkCommercialForecastingStringConstants.GTN_WS_COMMERCIAL_FORECASTING_LEFT_HEADERS_SERVICE);
		gtnPagedTreeTableConfig.setRightHeader(
				GtnFrameworkCommercialForecastingStringConstants.GTN_WS_COMMERCIAL_FORECASTING_RIGHT_HEADERS_SERVICE);

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
				GtnFrameworkCommercialForecastingClassConstants.COMMERCIAL_FORECASTING_DEFAULT_LEFT_HEADER_LOAD);
		gtnPagedTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				GtnFrameworkCommercialForecastingClassConstants.COMMERCIAL_FORECASTING_DEFAULT_RIGHT_HEADER_LOAD);
		gtnPagedTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);

		resultTableComponentConfig.setGtnPagedTreeTableConfig(gtnPagedTreeTableConfig);

		componentList.add(resultTableComponentConfig);
	}

	private void addResetRefreshButtons(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig resetAndRefreshLayout = layoutConfig
				.getHorizontalLayoutConfig("discountProjectionResetAndRefreshLayout", parentId);
		componentList.add(resetAndRefreshLayout);
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setComponentId("resultTableExcelBtn");
		excelButtonConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		excelButtonConfig.setAddToParent(true);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonInput = new GtnUIFrameworkExcelButtonConfig();
		gtnUIFrameworkExcelButtonInput.setIsTreeTable(true);
		gtnUIFrameworkExcelButtonInput.setExportFileName("Commercial Forecasting");
		gtnUIFrameworkExcelButtonInput.setHeaderServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_EXCEL_HEADERS_SERVICE);
		gtnUIFrameworkExcelButtonInput.setLoadDataServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_EXCEL_DATA_SERVICE);
		gtnUIFrameworkExcelButtonInput.setServiceType("forecast");
		gtnUIFrameworkExcelButtonInput.setServiceInput(new Object[] { "projectionDetailsTabsheetMainLayout" });
		GtnUIFrameWorkActionConfig resultTableExcelAction = new GtnUIFrameWorkActionConfig();
		resultTableExcelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonInput);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(resultTableExcelAction));
		componentList.add(excelButtonConfig);

		GtnUIFrameworkComponentConfig resultTableAltHistorytButton = new GtnUIFrameworkComponentConfig();
		resultTableAltHistorytButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resultTableAltHistorytButton.setComponentId("resultTableAltHistorytButton");
		resultTableAltHistorytButton.setComponentName("ALT History");
		resultTableAltHistorytButton.setParentComponentId(resetAndRefreshLayout.getComponentId());
		resultTableAltHistorytButton.setAddToParent(Boolean.TRUE);
		componentList.add(resultTableAltHistorytButton);

		GtnUIFrameworkComponentConfig resultTableRefreshBtnConfig = new GtnUIFrameworkComponentConfig();
		resultTableRefreshBtnConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resultTableRefreshBtnConfig.setComponentId("resultTableRefreshButton");
		resultTableRefreshBtnConfig.setComponentName("REFRESH");
		resultTableRefreshBtnConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		resultTableRefreshBtnConfig.setAddToParent(Boolean.TRUE);

		GtnUIFrameworkComponentConfig resultTableResetConfig = new GtnUIFrameworkComponentConfig();
		resultTableResetConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resultTableResetConfig.setComponentId("resultTableResetButton");
		resultTableResetConfig.setComponentName("RESET");
		resultTableResetConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		resultTableResetConfig.setAddToParent(Boolean.TRUE);
		componentList.add(resultTableResetConfig);

		componentList.add(resultTableRefreshBtnConfig);
	}
}
