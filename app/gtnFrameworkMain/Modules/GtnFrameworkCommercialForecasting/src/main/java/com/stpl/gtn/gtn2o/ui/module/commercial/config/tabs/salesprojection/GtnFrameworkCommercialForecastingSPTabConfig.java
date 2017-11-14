package com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.salesprojection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabSheetLoadType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.GtnFrameworkCommercialForecastingLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.module.commercial.config.tabs.GtnFrameworkExpandCollapseLevelSection;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingClassConstants;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkCommercialForecastingSPTabConfig {

	private GtnFrameworkCommercialForecastingLayoutsConfig layoutConfig = new GtnFrameworkCommercialForecastingLayoutsConfig();
	private final String tabName = "salesProjectionTab";

	public void addSalesProjectionTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutConfig.getRootLayoutConfig(
				"commercialForecastingSalesProjectionRootLayout", GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rootLayoutConfig.setSpacing(Boolean.TRUE);
		componentList.add(rootLayoutConfig);
		addSalesProjectionSelection(componentList, rootLayoutConfig.getComponentId());
		addResetAndGenerate(componentList, rootLayoutConfig.getComponentId());
		addSalesProjectionParentPanel(componentList, rootLayoutConfig.getComponentId());
		addSalesProjectionPanel(componentList, "salesProjectionVerticalLayout");
		addResultsPanel(componentList, "salesProjectionVerticalLayout");
		addResultTable(componentList, "salesProjectionVerticalLayout");
		addResetRefreshButtons(componentList, "salesProjectionVerticalLayout");
	}

	private void addSalesProjectionSelection(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig salesProjectionSelectionPanel = layoutConfig
				.getPanelConfig("salesProjectionSelection", componentId);
		salesProjectionSelectionPanel.setComponentName("Sales Projection Selection ");
		salesProjectionSelectionPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(salesProjectionSelectionPanel);

		GtnUIFrameworkComponentConfig salesProjectionSelectionPanelLayout = layoutConfig.getVerticalLayoutConfig(
				"salesProjectionSelectionPanel", salesProjectionSelectionPanel.getComponentId());
		componentList.add(salesProjectionSelectionPanelLayout);

		GtnUIFrameworkComponentConfig frequencyActualsProjectionCssLayout = layoutConfig.getCssLayoutConfig(
				"salesProjectionFrequencyActualsProjection", salesProjectionSelectionPanelLayout.getComponentId());
		frequencyActualsProjectionCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		frequencyActualsProjectionCssLayout.setComponentWidth("75%");
		componentList.add(frequencyActualsProjectionCssLayout);

		GtnUIFrameworkComponentConfig historyProjectionPeriodOrderCssLayout = layoutConfig.getCssLayoutConfig(
				"salesProjectionHistoryProjectionPeriodOrder", salesProjectionSelectionPanelLayout.getComponentId());
		historyProjectionPeriodOrderCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		historyProjectionPeriodOrderCssLayout.setComponentWidth("75%");
		componentList.add(historyProjectionPeriodOrderCssLayout);

		addFrequency(componentList, frequencyActualsProjectionCssLayout.getComponentId());
		addActualsProjection(componentList, frequencyActualsProjectionCssLayout.getComponentId());
		addProjectionPeriodOrder(componentList, frequencyActualsProjectionCssLayout.getComponentId());
		addHistory(componentList, historyProjectionPeriodOrderCssLayout.getComponentId());
		addVariables(componentList, historyProjectionPeriodOrderCssLayout.getComponentId());

	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig salesProjectionFrequencyLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionFrequency", parentId);
		componentList.add(salesProjectionFrequencyLayout);

		GtnUIFrameworkComponentConfig salesProjectionfrequencyComboBox = new GtnUIFrameworkComponentConfig();
		salesProjectionfrequencyComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		salesProjectionfrequencyComboBox.setComponentId("salesProjectionFrequency");
		salesProjectionfrequencyComboBox.setComponentName("Frequency");
		salesProjectionfrequencyComboBox.setAddToParent(Boolean.TRUE);
		salesProjectionfrequencyComboBox.setParentComponentId(salesProjectionFrequencyLayout.getComponentId());
		salesProjectionfrequencyComboBox.addDependentComponent("salesProjectionHistory");

		GtnUIFrameworkComboBoxConfig salesProjectionFrequencyConfig = new GtnUIFrameworkComboBoxConfig();
		salesProjectionFrequencyConfig.setComboBoxType("PAYMENT_FREQUENCY");
		salesProjectionFrequencyConfig.setHasDefaultValue(Boolean.TRUE);
		salesProjectionfrequencyComboBox.setGtnComboboxConfig(salesProjectionFrequencyConfig);
		componentList.add(salesProjectionfrequencyComboBox);
	}

	private void addActualsProjection(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig salesProjectionActionProjectionLayout = layoutConfig
				.getHorizontalLayoutConfig("actualsProjection", parentId);
		componentList.add(salesProjectionActionProjectionLayout);

		GtnUIFrameworkComponentConfig salesProjectionActualsProjectionOptionGroup = new GtnUIFrameworkComponentConfig();
		salesProjectionActualsProjectionOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		salesProjectionActualsProjectionOptionGroup.setComponentId("salesProjectionActualsProjectionOptionGroup");
		salesProjectionActualsProjectionOptionGroup.setComponentName("Actuals/Projections:");
		salesProjectionActualsProjectionOptionGroup.setAddToParent(Boolean.TRUE);
		salesProjectionActualsProjectionOptionGroup
				.setParentComponentId(salesProjectionActionProjectionLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig salesProjectionActualsProjectionOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		salesProjectionActualsProjectionOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Actuals", "Projections", "Both" }));
		salesProjectionActualsProjectionOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		salesProjectionActualsProjectionOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		salesProjectionActualsProjectionOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(salesProjectionActualsProjectionOptionGroupConfig);

		componentList.add(salesProjectionActualsProjectionOptionGroup);

	}

	private void addProjectionPeriodOrder(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig salesProjectionProjectionPeriodOrderLayout = layoutConfig
				.getHorizontalLayoutConfig("projectionPeriodOrder", parentId);
		componentList.add(salesProjectionProjectionPeriodOrderLayout);

		GtnUIFrameworkComponentConfig salesProjectionPeriodOrderOptionGroup = new GtnUIFrameworkComponentConfig();
		salesProjectionPeriodOrderOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		salesProjectionPeriodOrderOptionGroup.setComponentId("salesProjectionPeriodOrderOptionGroup");
		salesProjectionPeriodOrderOptionGroup.setComponentName("Projection Period Order :");
		salesProjectionPeriodOrderOptionGroup.setAddToParent(Boolean.TRUE);
		salesProjectionPeriodOrderOptionGroup
				.setParentComponentId(salesProjectionProjectionPeriodOrderLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig salesProjectionPeriodOrderOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		salesProjectionPeriodOrderOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Ascending", "Descending" }));
		salesProjectionPeriodOrderOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		salesProjectionPeriodOrderOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		salesProjectionPeriodOrderOptionGroup
				.setGtnUIFrameworkOptionGroupConfig(salesProjectionPeriodOrderOptionGroupConfig);
		componentList.add(salesProjectionPeriodOrderOptionGroup);

	}

	private void addHistory(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig salesProjectionHistoryComboBoxLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionHistory", parentId);
		componentList.add(salesProjectionHistoryComboBoxLayout);

		GtnUIFrameworkComponentConfig salesProjectionHistoryComboBox = new GtnUIFrameworkComponentConfig();
		salesProjectionHistoryComboBox.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		salesProjectionHistoryComboBox.setComponentId("salesProjectionHistory");
		salesProjectionHistoryComboBox.setComponentName("History");
		salesProjectionHistoryComboBox.setAddToParent(Boolean.TRUE);
		salesProjectionHistoryComboBox.setParentComponentId(salesProjectionHistoryComboBoxLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig salesProjectionHistoryConfig = new GtnUIFrameworkComboBoxConfig();
		salesProjectionHistoryConfig.setComboBoxType("PAYMENT_FREQUENCY");
		salesProjectionHistoryConfig.setHasDefaultValue(Boolean.TRUE);
		salesProjectionHistoryComboBox.setGtnComboboxConfig(salesProjectionHistoryConfig);
		componentList.add(salesProjectionHistoryComboBox);
	}

	private void addVariables(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig salesProjectionVariableOptionGroupLayout = layoutConfig
				.getHorizontalLayoutConfig("variables", parentId);
		componentList.add(salesProjectionVariableOptionGroupLayout);

		GtnUIFrameworkComponentConfig salesProjectionVariableOptionGroup = new GtnUIFrameworkComponentConfig();
		salesProjectionVariableOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		salesProjectionVariableOptionGroup.setComponentId("salesProjectionVariableCheckBox");
		salesProjectionVariableOptionGroup.setComponentName("Variables :");
		salesProjectionVariableOptionGroup.setAddToParent(Boolean.TRUE);
		salesProjectionVariableOptionGroup
				.setParentComponentId(salesProjectionVariableOptionGroupLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig salesProjectionVariableOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		salesProjectionVariableOptionGroupConfig.setIsMultiSelect(true);
		salesProjectionVariableOptionGroupConfig
				.setItemValues(Arrays.asList(new String[] { "Sales", "Units", "Product Growth", "Account Growth" }));
		salesProjectionVariableOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		salesProjectionVariableOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		salesProjectionVariableOptionGroup.setGtnUIFrameworkOptionGroupConfig(salesProjectionVariableOptionGroupConfig);
		componentList.add(salesProjectionVariableOptionGroup);
	}

	private void addResetAndGenerate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig resetAndGenerateLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionResetAndGenerate", parentId);
		componentList.add(resetAndGenerateLayout);

		GtnUIFrameworkComponentConfig salesProjectionGenerateButton = new GtnUIFrameworkComponentConfig();
		salesProjectionGenerateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		salesProjectionGenerateButton.setComponentId("salesProjectionGenerateBtn");
		salesProjectionGenerateButton.setComponentName("GENERATE");
		salesProjectionGenerateButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		salesProjectionGenerateButton.setAddToParent(true);
		componentList.add(salesProjectionGenerateButton);

		GtnUIFrameworkComponentConfig salesProjectionResetButton = new GtnUIFrameworkComponentConfig();
		salesProjectionResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		salesProjectionResetButton.setComponentId("salessProjectionResetBtn");
		salesProjectionResetButton.setComponentName("RESET");
		salesProjectionResetButton.setParentComponentId(resetAndGenerateLayout.getComponentId());
		salesProjectionResetButton.setAddToParent(Boolean.TRUE);

		componentList.add(salesProjectionResetButton);

	}

	private void addSalesProjectionPanel(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig salesProjectionPanel = layoutConfig.getPanelConfig("salesProjection",
				componentId);
		salesProjectionPanel.setComponentName("Sales Projection ");
		salesProjectionPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(salesProjectionPanel);
		addSalesProjectionPanelTabSheets(componentList, salesProjectionPanel.getComponentId());

	}

	private void addSalesProjectionPanelTabSheets(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig vLayout = new GtnUIFrameworkComponentConfig();
		vLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		vLayout.setComponentId("salesProjectionVLayout");
		vLayout.setComponentName("Sales Projection Panel Tab Sheet");
		vLayout.setComponentWidth("100%");
		vLayout.setAddToParent(Boolean.TRUE);
		vLayout.setParentComponentId(parentId);
		vLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(vLayout);

		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId("salesProjectionPanelTabSheet");
		tabSheetConfig.setComponentName("Sales Projection Panel Tab Sheet");
		tabSheetConfig.setComponentWidth("100%");
		tabSheetConfig.setAddToParent(Boolean.TRUE);
		tabSheetConfig.setParentComponentId(vLayout.getComponentId());

		// find the No of tabs

		List<GtnUIFrameworkComponentConfig> massUpdateTabList = new ArrayList<>();
		GtnUIFrameworkTabConfig massUpdate = new GtnUIFrameworkTabConfig();
		massUpdate.setComponentId("salesProjectionMassUpdateCssLayout");
		massUpdate.setTabCaption("Mass Update");
		massUpdate.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		addMassUpdateTab(massUpdateTabList);
		massUpdate.setTabLayoutComponentConfigList(massUpdateTabList);

		List<GtnUIFrameworkComponentConfig> forecastTabList = new ArrayList<>();
		GtnUIFrameworkTabConfig forecast = new GtnUIFrameworkTabConfig();
		forecast.setComponentId("salesProjectionForecastTabCssLayout");
		forecast.setTabCaption("Forecast");
		forecast.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		addForecastTab(forecastTabList);
		forecast.setTabLayoutComponentConfigList(forecastTabList);

		List<GtnUIFrameworkComponentConfig> adjustmentTabList = new ArrayList<>();
		GtnUIFrameworkTabConfig adjustment = new GtnUIFrameworkTabConfig();
		adjustment.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		adjustment.setComponentId("salesProjectionAdjustmentTabCssLayout");
		adjustment.setTabCaption("Adjustment");
		addAdjustmentTab(adjustmentTabList);
		adjustment.setTabLayoutComponentConfigList(adjustmentTabList);

		componentList.add(tabSheetConfig);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(massUpdate);
		tabConfigList.add(forecast);
		tabConfigList.add(adjustment);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addMassUpdateTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig massUpdateCssLayout = layoutConfig.getCssLayoutConfig("salesProjectionMassUpdate",
				"");
		massUpdateCssLayout.setAddToParent(Boolean.FALSE);
		massUpdateCssLayout.setComponentWidth("100%");
		massUpdateCssLayout.setTabComponent(Boolean.TRUE);
		massUpdateCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_MASS_UPDATE);
		massUpdateCssLayout.setMargin(Boolean.TRUE);
		componentList.add(massUpdateCssLayout);
		addEnableDisableOpbtionGroup(componentList, massUpdateCssLayout.getComponentId());
		addFieldComponent(componentList, massUpdateCssLayout.getComponentId());
		addValueComponent(componentList, massUpdateCssLayout.getComponentId());
		addMassUpdateStartPeriodComponent(componentList, massUpdateCssLayout.getComponentId());
		addMassUpdateEndPeriodComponent(componentList, massUpdateCssLayout.getComponentId());
		addMassupdatePopulateButton(componentList, massUpdateCssLayout.getComponentId());
	}

	private void addForecastTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig forecastTabCssLayout = layoutConfig
				.getCssLayoutConfig("salesProjectionForecastTab", "");
		forecastTabCssLayout.setAddToParent(Boolean.FALSE);
		forecastTabCssLayout.setComponentWidth("100%");
		forecastTabCssLayout.setTabComponent(Boolean.TRUE);
		forecastTabCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_MASS_UPDATE);
		componentList.add(forecastTabCssLayout);
		addMethodologyComponent(componentList, forecastTabCssLayout.getComponentId());
		addForecastTabStartPeriodComponent(componentList, forecastTabCssLayout.getComponentId());
		addForecastTabEndPeriodComponent(componentList, forecastTabCssLayout.getComponentId());
		addForecastTabCalculateButton(componentList, forecastTabCssLayout.getComponentId());
	}

	private void addAdjustmentTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig forecastTabCssLayout = layoutConfig
				.getCssLayoutConfig("salesProjectionAdjustmentTab", "");
		forecastTabCssLayout.setComponentStyle(Arrays.asList(new String[] {
				GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12, GtnFrameworkCssConstants.GTN_MASS_UPDATE }));
		forecastTabCssLayout.setAddToParent(Boolean.FALSE);
		forecastTabCssLayout.setComponentWidth("100%");
		forecastTabCssLayout.setTabComponent(Boolean.TRUE);
		componentList.add(forecastTabCssLayout);
		addTypeOptionGroup(componentList, forecastTabCssLayout.getComponentId());
		addBasisOptionGroup(componentList, forecastTabCssLayout.getComponentId());
		addVariableOptionGroup(componentList, forecastTabCssLayout.getComponentId());
		addAdjustmentTabAdjustmentField(componentList, forecastTabCssLayout.getComponentId());
		addAdjustmentTabAdjustmentComboBox(componentList, forecastTabCssLayout.getComponentId());
		addAdjustmentTabAdjustmentPeriod(componentList, forecastTabCssLayout.getComponentId());
		addAdjustmentTabAdjustButton(componentList, forecastTabCssLayout.getComponentId());
	}

	private void addEnableDisableOpbtionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig enableDisableOptionGroupLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionEnableDisableOptionGroup", parentId);
		enableDisableOptionGroupLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(enableDisableOptionGroupLayout);

		GtnUIFrameworkComponentConfig enableDisableOptionGroup = new GtnUIFrameworkComponentConfig();
		enableDisableOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		enableDisableOptionGroup.setComponentId("salesProjectionEnableDisableMode");
		enableDisableOptionGroup.setComponentName("");
		enableDisableOptionGroup.setAddToParent(Boolean.TRUE);
		enableDisableOptionGroup.setParentComponentId(enableDisableOptionGroupLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig salesProjectionEnableDisableConfig = new GtnUIFrameworkOptionGroupConfig();
		salesProjectionEnableDisableConfig.setItemValues(Arrays.asList(new String[] { "Enable", "Disable" }));
		salesProjectionEnableDisableConfig.setValuesFromService(Boolean.FALSE);
		enableDisableOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		enableDisableOptionGroup.setGtnUIFrameworkOptionGroupConfig(salesProjectionEnableDisableConfig);

		componentList.add(enableDisableOptionGroup);

	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdateFieldLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionMassUpdateField", parentId);
		massUpdateFieldLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(massUpdateFieldLayout);

		GtnUIFrameworkComponentConfig massUpdateFieldComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateFieldComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		massUpdateFieldComponentConfig.setComponentId("salesProjectionMassUpdateField");
		massUpdateFieldComponentConfig.setComponentName("Field:");
		massUpdateFieldComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateFieldComponentConfig.setParentComponentId(massUpdateFieldLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setComboBoxType("RETURNS_MASSUPDATE_FIELD");
		massUpdateFieldComponentConfig.setGtnComboboxConfig(frequencyConfig);

		componentList.add(massUpdateFieldComponentConfig);
	}

	private void addValueComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdateValueLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionMassUpdateValue", parentId);
		massUpdateValueLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(massUpdateValueLayout);

		GtnUIFrameworkComponentConfig massUpdateValueComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateValueComponentConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		massUpdateValueComponentConfig.setComponentId("salesProjectionMassUpdateValue");
		massUpdateValueComponentConfig.setComponentName("Value:");
		massUpdateValueComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateValueComponentConfig.setParentComponentId(massUpdateValueLayout.getComponentId());

		componentList.add(massUpdateValueComponentConfig);
	}

	private void addMassUpdateStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdateStartPeriodLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionMassUpdateStartPeriod", parentId);
		massUpdateStartPeriodLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(massUpdateStartPeriodLayout);

		GtnUIFrameworkComponentConfig massUpdateStartPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateStartPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		massUpdateStartPeriodComponentConfig.setComponentId("salesProjectionMassUpdateStartPeriod");
		massUpdateStartPeriodComponentConfig.setComponentName("Start Period:");
		massUpdateStartPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateStartPeriodComponentConfig.setParentComponentId(massUpdateStartPeriodLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig massUpdateStartPeriod = new GtnUIFrameworkComboBoxConfig();
		massUpdateStartPeriod.setComboBoxType("PAYMENT_FREQUENCY");
		massUpdateStartPeriodComponentConfig.setGtnComboboxConfig(massUpdateStartPeriod);

		componentList.add(massUpdateStartPeriodComponentConfig);
	}

	private void addMassUpdateEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdateEndPeriodLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionMassUpdateEndPeriod", parentId);
		massUpdateEndPeriodLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(massUpdateEndPeriodLayout);

		GtnUIFrameworkComponentConfig massUpdateEndPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateEndPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		massUpdateEndPeriodComponentConfig.setComponentId("salesProjectionMassUpdateEndPeriod");
		massUpdateEndPeriodComponentConfig.setComponentName("End Period");
		massUpdateEndPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateEndPeriodComponentConfig.setParentComponentId(massUpdateEndPeriodLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig massUpdateEndPeriod = new GtnUIFrameworkComboBoxConfig();
		massUpdateEndPeriod.setComboBoxType("PAYMENT_FREQUENCY");
		massUpdateEndPeriodComponentConfig.setGtnComboboxConfig(massUpdateEndPeriod);
		componentList.add(massUpdateEndPeriodComponentConfig);
	}

	private void addMassupdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdatePopulateButtonConfig = new GtnUIFrameworkComponentConfig();
		massUpdatePopulateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		massUpdatePopulateButtonConfig.setComponentId("salesProjectionMassUpdatePopulateButton");
		massUpdatePopulateButtonConfig.setComponentName("POPULATE");
		massUpdatePopulateButtonConfig.setParentComponentId(parentId);
		massUpdatePopulateButtonConfig.setAddToParent(Boolean.TRUE);

		componentList.add(massUpdatePopulateButtonConfig);

	}

	/**
	 * @param componentList
	 * @param parentId
	 */
	private void addForecastTabCalculateButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig forecastTabCalculateButtonConfig = new GtnUIFrameworkComponentConfig();
		forecastTabCalculateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastTabCalculateButtonConfig.setComponentId("salesProjectionForecastTabCalculateButton");
		forecastTabCalculateButtonConfig.setComponentName("CALCULATE");
		forecastTabCalculateButtonConfig.setParentComponentId(parentId);
		forecastTabCalculateButtonConfig.setAddToParent(Boolean.TRUE);
		componentList.add(forecastTabCalculateButtonConfig);

	}

	private void addForecastTabEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig forecastTabEndPeriodLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionForecastTabEndPeriod", parentId);
		forecastTabEndPeriodLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(forecastTabEndPeriodLayout);

		GtnUIFrameworkComponentConfig forecastTabEndPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		forecastTabEndPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		forecastTabEndPeriodComponentConfig.setComponentId("salesProjectionForecastTabEndPeriod");
		forecastTabEndPeriodComponentConfig.setComponentName("End Period");
		forecastTabEndPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		forecastTabEndPeriodComponentConfig.setParentComponentId(forecastTabEndPeriodLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig forecastTabEndPeriod = new GtnUIFrameworkComboBoxConfig();
		forecastTabEndPeriod.setComboBoxType("PAYMENT_FREQUENCY");
		forecastTabEndPeriodComponentConfig.setGtnComboboxConfig(forecastTabEndPeriod);
		componentList.add(forecastTabEndPeriodComponentConfig);

	}

	private void addForecastTabStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig forecastTabStartPeriodLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionForecastTabStartPeriod", parentId);
		forecastTabStartPeriodLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(forecastTabStartPeriodLayout);

		GtnUIFrameworkComponentConfig forecastTabStartPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		forecastTabStartPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		forecastTabStartPeriodComponentConfig.setComponentId("salesProjectionForecastTabStartPeriod");
		forecastTabStartPeriodComponentConfig.setComponentName("Start Period:");
		forecastTabStartPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		forecastTabStartPeriodComponentConfig.setParentComponentId(forecastTabStartPeriodLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig forecastTabStartPeriod = new GtnUIFrameworkComboBoxConfig();
		forecastTabStartPeriod.setComboBoxType("PAYMENT_FREQUENCY");
		forecastTabStartPeriodComponentConfig.setGtnComboboxConfig(forecastTabStartPeriod);
		componentList.add(forecastTabStartPeriodComponentConfig);

	}

	private void addMethodologyComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig forecastTabMethodologyLayout = layoutConfig
				.getHorizontalLayoutConfig("salesProjectionForecastTabMethodology", parentId);
		forecastTabMethodologyLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		componentList.add(forecastTabMethodologyLayout);

		GtnUIFrameworkComponentConfig forecastTabMethodologyComponentConfig = new GtnUIFrameworkComponentConfig();
		forecastTabMethodologyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		forecastTabMethodologyComponentConfig.setComponentId("salesProjectionForecastTabMethodology");
		forecastTabMethodologyComponentConfig.setComponentName("Methodology:");
		forecastTabMethodologyComponentConfig.setAddToParent(Boolean.TRUE);
		forecastTabMethodologyComponentConfig.setParentComponentId(forecastTabMethodologyLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig methodologyConfig = new GtnUIFrameworkComboBoxConfig();
		methodologyConfig.setComboBoxType("RETURNS_METHODOLOGY");
		forecastTabMethodologyComponentConfig.setGtnComboboxConfig(methodologyConfig);
		componentList.add(forecastTabMethodologyComponentConfig);

	}

	private void addTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig adjustmentTypeOptionGroupLayout = layoutConfig
				.getCssLayoutConfig("salesProjectionAdjustmentTypeOptionGroup", parentId);
		adjustmentTypeOptionGroupLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(adjustmentTypeOptionGroupLayout);

		GtnUIFrameworkComponentConfig adjustmentTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		adjustmentTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		adjustmentTypeOptionGroup.setComponentId("salesProjectionAdjustmenttype");
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

	private void addBasisOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig basisTypeOptionGroupLayout = layoutConfig
				.getCssLayoutConfig("salesProjectionBasisTypeOptionGroup", parentId);
		basisTypeOptionGroupLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(basisTypeOptionGroupLayout);

		GtnUIFrameworkComponentConfig basisTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		basisTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		basisTypeOptionGroup.setComponentId("salesProjectionBasisType");
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

	private void addVariableOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig variableTypeOptionGroupLayout = layoutConfig
				.getCssLayoutConfig("salesProjectionVariableTypeOptionGroup", parentId);
		variableTypeOptionGroupLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(variableTypeOptionGroupLayout);

		GtnUIFrameworkComponentConfig variableTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		variableTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		variableTypeOptionGroup.setComponentId("salesProjectionVariableType");
		variableTypeOptionGroup.setComponentName("Variable");
		variableTypeOptionGroup.setAddToParent(Boolean.TRUE);
		variableTypeOptionGroup.setParentComponentId(variableTypeOptionGroupLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig variableTypeOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		variableTypeOptionGroupConfig.setItemValues(Arrays.asList(new String[] { "Sales", "Units" }));
		variableTypeOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		variableTypeOptionGroup.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		variableTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(variableTypeOptionGroupConfig);

		componentList.add(variableTypeOptionGroup);
	}

	private void addAdjustmentTabAdjustmentField(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig adjustmentValueLayout = layoutConfig
				.getCssLayoutConfig("salesProjectionAdjustmentValue", parentId);
		adjustmentValueLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(adjustmentValueLayout);

		GtnUIFrameworkComponentConfig adjustmentValueComponentConfig = new GtnUIFrameworkComponentConfig();
		adjustmentValueComponentConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		adjustmentValueComponentConfig.setComponentId("salesProjectionAdjustmentValue");
		adjustmentValueComponentConfig.setComponentName("Adjustment:");
		adjustmentValueComponentConfig.setAddToParent(Boolean.TRUE);
		adjustmentValueComponentConfig.setParentComponentId(adjustmentValueLayout.getComponentId());

		componentList.add(adjustmentValueComponentConfig);
	}

	private void addAdjustmentTabAdjustmentComboBox(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig adjustmentMethodologyLayout = layoutConfig
				.getCssLayoutConfig("salesProjectionAdjustmentMethodology", parentId);
		adjustmentMethodologyLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_3);
		componentList.add(adjustmentMethodologyLayout);

		GtnUIFrameworkComponentConfig adjustmentMethodologyComponentConfig = new GtnUIFrameworkComponentConfig();
		adjustmentMethodologyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		adjustmentMethodologyComponentConfig.setComponentId("salesProjectionAdjustmentMethodology");
		adjustmentMethodologyComponentConfig.setComponentName("Adjustment Methodology:");
		adjustmentMethodologyComponentConfig.setAddToParent(Boolean.TRUE);
		adjustmentMethodologyComponentConfig.setParentComponentId(adjustmentMethodologyLayout.getComponentId());
		GtnUIFrameworkComboBoxConfig methodologyConfig = new GtnUIFrameworkComboBoxConfig();
		methodologyConfig.setComboBoxType("RETURNS_METHODOLOGY");
		adjustmentMethodologyComponentConfig.setGtnComboboxConfig(methodologyConfig);
		componentList.add(adjustmentMethodologyComponentConfig);
	}

	private void addAdjustmentTabAdjustmentPeriod(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig adjustmentPeriodOptionGroupLayout = layoutConfig
				.getCssLayoutConfig("salesProjectionEnableDisableOptionGroup", parentId);
		adjustmentPeriodOptionGroupLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(adjustmentPeriodOptionGroupLayout);

		GtnUIFrameworkComponentConfig adjustmentPeriodOptionGroup = new GtnUIFrameworkComponentConfig();
		adjustmentPeriodOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		adjustmentPeriodOptionGroup.setComponentId("salesProjectionEnableDisableMode");
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

	private void addAdjustmentTabAdjustButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig adjustButtonLayout = layoutConfig
				.getCssLayoutConfig("salesProjectionEnableDisableOptionGroup", parentId);
		adjustButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(adjustButtonLayout);
		GtnUIFrameworkComponentConfig adjustButtonConfig = new GtnUIFrameworkComponentConfig();
		adjustButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		adjustButtonConfig.setComponentId("salesProjectionAdjustButton");
		adjustButtonConfig.setComponentName("ADJUST");
		adjustButtonConfig.setParentComponentId(adjustButtonLayout.getComponentId());
		adjustButtonConfig.setAddToParent(Boolean.TRUE);
		componentList.add(adjustButtonConfig);
	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig resultsPanel = layoutConfig
				.getPanelConfig(tabName + "salesProjectionResultsPanel", parentId);
		resultsPanel.setComponentName("Results");
		resultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(resultsPanel);

		GtnFrameworkExpandCollapseLevelSection conf = new GtnFrameworkExpandCollapseLevelSection();
		conf.getRootExpandCollapseSectionLayout(componentList, resultsPanel.getComponentId(), "forecastCommercial",
				tabName);
	}

	public void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId("salesProjectionResultTable");
		resultTableComponentConfig.setComponentName("resultTable");
		resultTableComponentConfig.setAddToParent(Boolean.TRUE);
		resultTableComponentConfig.setParentComponentId(parentId);

		GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add(tabName + "salesProjectionResultsPanelPanel");
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
				.getHorizontalLayoutConfig("salesProjectionResetAndRefresh", parentId);
		componentList.add(resetAndRefreshLayout);
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setComponentId("salesProjectionResultTableExcelBtn");
		excelButtonConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		excelButtonConfig.setAddToParent(true);
		componentList.add(excelButtonConfig);

		GtnUIFrameworkComponentConfig resultTablePmpyConfig = new GtnUIFrameworkComponentConfig();
		resultTablePmpyConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resultTablePmpyConfig.setComponentId("salesProjectionResultTablePmpyBtn");
		resultTablePmpyConfig.setComponentName("PMPY");
		resultTablePmpyConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		resultTablePmpyConfig.setAddToParent(Boolean.TRUE);
		resultTablePmpyConfig.setEnable(false);
		componentList.add(resultTablePmpyConfig);

		GtnUIFrameworkComponentConfig resultTableAltHistoryConfig = new GtnUIFrameworkComponentConfig();
		resultTableAltHistoryConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resultTableAltHistoryConfig.setComponentId("salesProjectionResultTableAltHistoryBtn");
		resultTableAltHistoryConfig.setComponentName("ALT HISTORY");
		resultTableAltHistoryConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		resultTableAltHistoryConfig.setAddToParent(Boolean.TRUE);
		componentList.add(resultTableAltHistoryConfig);

		GtnUIFrameworkComponentConfig resultTableRefreshBtnConfig = new GtnUIFrameworkComponentConfig();
		resultTableRefreshBtnConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resultTableRefreshBtnConfig.setComponentId("salesProjectionResultTableRefreshBtn");
		resultTableRefreshBtnConfig.setComponentName("REFRESH");
		resultTableRefreshBtnConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		resultTableRefreshBtnConfig.setAddToParent(Boolean.TRUE);

		componentList.add(resultTableRefreshBtnConfig);
	}

	private void addSalesProjectionParentPanel(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig salesProjectionParentPanel = new GtnUIFrameworkComponentConfig();
		salesProjectionParentPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		salesProjectionParentPanel.setComponentId("salesProjectionParentPanel");
		salesProjectionParentPanel.setParentComponentId(componentId);
		salesProjectionParentPanel.setComponentWidth("100%");
		salesProjectionParentPanel.setAddToParent(Boolean.TRUE);
		salesProjectionParentPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(salesProjectionParentPanel);
		GtnUIFrameworkComponentConfig salesProjectionVerticalLayout = layoutConfig
				.getVerticalLayoutConfig("salesProjection", "salesProjectionParentPanel");
		componentList.add(salesProjectionVerticalLayout);

	}

}
