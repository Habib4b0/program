package com.stpl.gtn.gtn2o.ui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboboxSourceSubsetType;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUiFrameworkComboBoxSourceType;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConstants;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;

public class GtnForecastReturnsProjectionTabConfig {

	private GtnFrameworkForecastReturnLayoutsConfig layoutConfig = new GtnFrameworkForecastReturnLayoutsConfig();

	public void addReturnsProjectionTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rootLayoutConfig = layoutConfig.getRootLayoutConfig("returnsProjectionRootLayout",
				GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		rootLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		rootLayoutConfig.setSpacing(Boolean.TRUE);
		componentList.add(rootLayoutConfig);
		addSalesProjectionSelection(componentList, rootLayoutConfig.getComponentId());
		addResetAndGenerate(componentList, rootLayoutConfig.getComponentId());
		addSalesProjectionParentPanel(componentList, rootLayoutConfig.getComponentId());
		addSalesProjectionPanel(componentList, GtnFrameworkCommonConstants.SALES_PROJECTION_VERTICAL_LAYOUT);
		addResultsPanel(componentList, GtnFrameworkCommonConstants.SALES_PROJECTION_VERTICAL_LAYOUT);
		addResultTable(componentList, GtnFrameworkCommonConstants.SALES_PROJECTION_VERTICAL_LAYOUT);
		addResetRefreshButtons(componentList, GtnFrameworkCommonConstants.SALES_PROJECTION_VERTICAL_LAYOUT);
	}

	private void addSalesProjectionSelection(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig salesProjectionSelectionPanel = layoutConfig
				.getPanelConfig("salesProjectionSelectionPanel", componentId);
		salesProjectionSelectionPanel.setComponentName("Sales Projection Selection ");
		salesProjectionSelectionPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_6.toString());
		componentList.add(salesProjectionSelectionPanel);

		GtnUIFrameworkComponentConfig salesProjectionSelectionPanelLayout = layoutConfig.getVerticalLayoutConfig(
				"salesProjectionSelectionPanel", salesProjectionSelectionPanel.getComponentId());
		componentList.add(salesProjectionSelectionPanelLayout);

		GtnUIFrameworkComponentConfig frequencyActualsProjectionCssLayout = layoutConfig.getCssLayoutConfig(
				"frequencyActualsProjectionCssLayout", salesProjectionSelectionPanelLayout.getComponentId());
		frequencyActualsProjectionCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		frequencyActualsProjectionCssLayout.setComponentWidth("45%");
		componentList.add(frequencyActualsProjectionCssLayout);

		GtnUIFrameworkComponentConfig historyProjectionPeriodOrderCssLayout = layoutConfig.getCssLayoutConfig(
				"historyProjectionPeriodOrderCssLayout", salesProjectionSelectionPanelLayout.getComponentId());
		historyProjectionPeriodOrderCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		historyProjectionPeriodOrderCssLayout.setComponentWidth("45%");
		componentList.add(historyProjectionPeriodOrderCssLayout);

		addFrequency(componentList, frequencyActualsProjectionCssLayout.getComponentId());
		addActualsProjection(componentList, frequencyActualsProjectionCssLayout.getComponentId());
		addHistory(componentList, historyProjectionPeriodOrderCssLayout.getComponentId());
		addProjectionPeriodOrder(componentList, historyProjectionPeriodOrderCssLayout.getComponentId());

	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig forecastReturnsFrequencyConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY, parentId);
		componentList.add(forecastReturnsFrequencyConfig);

		GtnUIFrameworkComponentConfig frequencyComponentConfig = new GtnUIFrameworkComponentConfig();
		frequencyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		frequencyComponentConfig.setComponentId(GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY);
		frequencyComponentConfig.setComponentName("Frequency");
		frequencyComponentConfig.setAddToParent(Boolean.TRUE);
		frequencyComponentConfig.setParentComponentId(forecastReturnsFrequencyConfig.getComponentId());
		frequencyComponentConfig.addDependentComponent(GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY);

		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequencyConfig.setComboBoxType(GtnFrameworkCommonConstants.PAYMENT_FREQUENCY);
		frequencyConfig.setHasDefaultValue(Boolean.TRUE);
		frequencyComponentConfig.setGtnComboboxConfig(frequencyConfig);
		componentList.add(frequencyComponentConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dependableActionConfig = new GtnUIFrameWorkActionConfig();

		dependableActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_FREQUENCY_LOAD_ACTION,
						GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
						GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY }));
		dependableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfigList.add(dependableActionConfig);
		frequencyComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addActualsProjection(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig hLConfig = layoutConfig.getHorizontalLayoutConfig("actualsProjection", parentId);
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig actualsProjectionOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		actualsProjectionOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		actualsProjectionOptionGroupConfig.setComponentId(GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP);
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

	private void addHistory(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig forecastReturnsHistoryConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY, parentId);
		componentList.add(forecastReturnsHistoryConfig);

		GtnUIFrameworkComponentConfig historyComponentConfig = new GtnUIFrameworkComponentConfig();
		historyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		historyComponentConfig.setComponentId(GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY);
		historyComponentConfig.setComponentName("History");
		historyComponentConfig.setAddToParent(Boolean.TRUE);
		historyComponentConfig.setParentComponentId(forecastReturnsHistoryConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig historyConfig = new GtnUIFrameworkComboBoxConfig();
		historyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		historyConfig.setComboBoxType(GtnFrameworkCommonConstants.PAYMENT_FREQUENCY);
		historyConfig.setHasDefaultValue(Boolean.TRUE);
		historyComponentConfig.setGtnComboboxConfig(historyConfig);
		componentList.add(historyComponentConfig);
	}

	private void addProjectionPeriodOrder(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig hLConfig = layoutConfig.getHorizontalLayoutConfig("projectionPeriodOrder",
				parentId);
		componentList.add(hLConfig);

		GtnUIFrameworkComponentConfig projectionPeriodOrderOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		projectionPeriodOrderOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		projectionPeriodOrderOptionGroupConfig
				.setComponentId(GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP);
		projectionPeriodOrderOptionGroupConfig.setComponentName("Actuals/Projections:");
		projectionPeriodOrderOptionGroupConfig.setAddToParent(Boolean.TRUE);
		projectionPeriodOrderOptionGroupConfig.setParentComponentId(hLConfig.getComponentId());

		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.ASCENDING, "Descending" }));
		comboConfig.setValuesFromService(Boolean.FALSE);
		projectionPeriodOrderOptionGroupConfig.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		projectionPeriodOrderOptionGroupConfig.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(projectionPeriodOrderOptionGroupConfig);
	}

	private void addResetAndGenerate(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig resetAndGenerateLayout = layoutConfig
				.getHorizontalLayoutConfig("returnsProjectionResetAndGenerateLayout", parentId);
		componentList.add(resetAndGenerateLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = new GtnUIFrameworkComponentConfig();
		resetButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentId("returnsProjectionResetBtn");
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setParentComponentId(resetAndGenerateLayout.getComponentId());
		resetButtonConfig.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetTableActionConfig = new GtnUIFrameWorkActionConfig();

		resetTableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetTableActionConfig.setActionParameterList(Arrays.asList(
				new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_RETURNS_PROJECTION_TAB_RESET_ACTION,
						GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
						GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
						GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
						GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP, "Quarterly", "4 Quarters",
						"Actuals", GtnFrameworkCommonConstants.ASCENDING }));

		resetActionConfigList.add(resetTableActionConfig);
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);
		componentList.add(resetButtonConfig);

		GtnUIFrameworkComponentConfig generateButtonConfig = new GtnUIFrameworkComponentConfig();
		generateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		generateButtonConfig.setComponentId("returnsProjectionGenerateBtn");
		generateButtonConfig.setComponentName("GENERATE");
		generateButtonConfig.setParentComponentId(resetAndGenerateLayout.getComponentId());
		generateButtonConfig.setAddToParent(true);
		componentList.add(generateButtonConfig);

		List<GtnUIFrameWorkActionConfig> configList = new ArrayList<>();

		GtnUIFrameWorkActionConfig conf = new GtnUIFrameWorkActionConfig();
		conf.setActionType(GtnUIFrameworkActionType.TREETABLE_ACTION);
		configList.add(conf);
		conf.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCommonConstants.RESULT_TABLE,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
				GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP, "forecastReturnslevelFilterDdlb",
				GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
				GtnFrameworkCommonConstants.ASCENDING }));

		GtnUIFrameWorkActionConfig customActionConfig = new GtnUIFrameWorkActionConfig();
		customActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		configList.add(customActionConfig);

		customActionConfig.setActionParameterList(Arrays.asList(
				new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_RETURNS_PROJECTION_TAB_GENERATE_ACTION,
						GtnFrameworkCommonConstants.FORECAST_TAB_START_PERIOD,
						GtnFrameworkCommonConstants.FORECAST_TAB_END_PERIOD,
						GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
						GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
						GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
						GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP,
						GtnFrameworkCommonConstants.RESULT_TABLE, GtnFrameworkCommonConstants.MASS_UPDATE_START_PERIOD,
						GtnFrameworkCommonConstants.MASS_UPDATE_END_PERIOD, "forecastReturnslevelFilterDdlb" }));
		customActionConfig.setFieldValues(Arrays.asList(new String[] { GtnFrameworkCommonConstants.ASCENDING,
				GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT }));

		generateButtonConfig.setGtnUIFrameWorkActionConfigList(configList);

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
		vLayout.setComponentHight("160px");
		vLayout.setAddToParent(Boolean.TRUE);
		vLayout.setParentComponentId(parentId);
		vLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(vLayout);

		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId("salesProjectionPanelTabSheet");
		tabSheetConfig.setComponentName("Sales Projection Panel Tab Sheet");
		tabSheetConfig.setComponentWidth("100%");
		tabSheetConfig.setComponentHight("100%");
		tabSheetConfig.setAddToParent(Boolean.TRUE);
		tabSheetConfig.setParentComponentId(vLayout.getComponentId());

		// find the No of tabs

		GtnUIFrameworkTabConfig massUpdate = new GtnUIFrameworkTabConfig();
		massUpdate.setComponentId("massUpdateCssLayout");
		massUpdate.setTabCaption("Mass Update");
		List<GtnUIFrameworkComponentConfig> massUpdateTabConfigList = new ArrayList<>();
		addMassUpdateTab(massUpdateTabConfigList);
		massUpdate.setTabLayoutComponentConfigList(massUpdateTabConfigList);

		GtnUIFrameworkTabConfig forecast = new GtnUIFrameworkTabConfig();
		forecast.setComponentId("forecastTabCssLayout");
		forecast.setTabCaption("Forecast");
		List<GtnUIFrameworkComponentConfig> forecastTabConfigList = new ArrayList<>();
		forecast.setTabLayoutComponentConfigList(forecastTabConfigList);
		addForecastTab(forecastTabConfigList);

		componentList.add(tabSheetConfig);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(massUpdate);
		tabConfigList.add(forecast);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addMassUpdateTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig massUpdateCssLayout = layoutConfig.getCssLayoutConfig("massUpdate", "");
		massUpdateCssLayout.setAddToParent(Boolean.FALSE);
		massUpdateCssLayout.setComponentWidth("100%");
		massUpdateCssLayout.setComponentHight("80px");
		massUpdateCssLayout.setTabComponent(Boolean.TRUE);
		massUpdateCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_MASS_UPDATE);
		massUpdateCssLayout.setMargin(Boolean.TRUE);
		componentList.add(massUpdateCssLayout);
		addFieldComponent(componentList, massUpdateCssLayout.getComponentId());
		addValueComponent(componentList, massUpdateCssLayout.getComponentId());
		addMassUpdateLevelComponent(componentList, massUpdateCssLayout.getComponentId());
		addMassUpdateStartPeriodComponent(componentList, massUpdateCssLayout.getComponentId());
		addMassUpdateEndPeriodComponent(componentList, massUpdateCssLayout.getComponentId());
		addMassupdatePopulateButton(componentList, massUpdateCssLayout.getComponentId());
	}

	private void addForecastTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig forecastTabCssLayout = layoutConfig.getCssLayoutConfig("forecastTab", "");
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

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdateFieldConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.MASS_UPDATE_FIELD, parentId);// massUpdateFieldHorizontal
		massUpdateFieldConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		massUpdateFieldConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(massUpdateFieldConfig);

		GtnUIFrameworkComponentConfig massUpdateFieldComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateFieldComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		massUpdateFieldComponentConfig.setComponentId(GtnFrameworkCommonConstants.MASS_UPDATE_FIELD);
		massUpdateFieldComponentConfig.setComponentName("Field:");
		massUpdateFieldComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateFieldComponentConfig.setParentComponentId(massUpdateFieldConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequencyConfig.setComboBoxType("RETURNS_MASSUPDATE_FIELD");
		massUpdateFieldComponentConfig.setGtnComboboxConfig(frequencyConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig massUpdateActionConfig = new GtnUIFrameWorkActionConfig();
		massUpdateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		massUpdateActionConfig.setActionParameterList(Arrays
				.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_MASS_UPDATE_FIELD_DDLB_ACTION,
						GtnFrameworkCommonConstants.MASS_UPDATE_FIELD, "massUpdateLevelHorizontal" }));
		actionConfigList.add(massUpdateActionConfig);

		massUpdateFieldComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		massUpdateFieldComponentConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(massUpdateFieldComponentConfig);
	}

	private void addValueComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdateValueConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.MASS_UPDATE_VALUE, parentId);
		massUpdateValueConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		massUpdateValueConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(massUpdateValueConfig);

		GtnUIFrameworkComponentConfig massUpdateValueComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateValueComponentConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		massUpdateValueComponentConfig.setComponentId(GtnFrameworkCommonConstants.MASS_UPDATE_VALUE);
		massUpdateValueComponentConfig.setComponentName("Value:");
		massUpdateValueComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateValueComponentConfig.setParentComponentId(massUpdateValueConfig.getComponentId());

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		massUpdateValueComponentConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(massUpdateValueComponentConfig);
	}

	private void addMassUpdateLevelComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdateLevelConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.MASS_UPDATE_LEVEL, parentId);
		massUpdateLevelConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		massUpdateLevelConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(massUpdateLevelConfig);

		GtnUIFrameworkComponentConfig massUpdateLevelComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateLevelComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		massUpdateLevelComponentConfig.setComponentId(GtnFrameworkCommonConstants.MASS_UPDATE_LEVEL);
		massUpdateLevelComponentConfig.setComponentName("Mass Update Level:");
		massUpdateLevelComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateLevelComponentConfig.setParentComponentId(massUpdateLevelConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig frequencyConfig = new GtnUIFrameworkComboBoxConfig();
		frequencyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequencyConfig.setComboBoxType("productLevelComboBox");
		massUpdateLevelComponentConfig.setGtnComboboxConfig(frequencyConfig);
		frequencyConfig.setSourceType(GtnUiFrameworkComboBoxSourceType.VALUES_FROM_SUBSET_OF_COMBOBOX);
		frequencyConfig.setSubsetType(GtnUIFrameworkComboboxSourceSubsetType.AFTER_SELECTED);
		frequencyConfig.setInclusionOfSelected(Boolean.TRUE);
		frequencyConfig.setSourceComboboxId("returnsForecastTabSheet_forecastLevel");

		componentList.add(massUpdateLevelComponentConfig);
	}

	private void addMassUpdateStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdateStartPeriodConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.MASS_UPDATE_START_PERIOD, parentId);
		massUpdateStartPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		massUpdateStartPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(massUpdateStartPeriodConfig);

		GtnUIFrameworkComponentConfig massUpdateStartPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateStartPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		massUpdateStartPeriodComponentConfig.setComponentId(GtnFrameworkCommonConstants.MASS_UPDATE_START_PERIOD);
		massUpdateStartPeriodComponentConfig.setComponentName("Start Period:");
		massUpdateStartPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateStartPeriodComponentConfig.setParentComponentId(massUpdateStartPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig massUpdateStartPeriod = new GtnUIFrameworkComboBoxConfig();
		massUpdateStartPeriod.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		massUpdateStartPeriod.setComboBoxType(GtnFrameworkCommonConstants.PAYMENT_FREQUENCY);
		massUpdateStartPeriodComponentConfig.setGtnComboboxConfig(massUpdateStartPeriod);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig gtnUIFrameWorkStartEndPeriodActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkStartEndPeriodActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkStartEndPeriodActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_START_END_PERIOD_ACTION,
						GtnFrameworkCommonConstants.MASS_UPDATE_START_PERIOD,
						GtnFrameworkCommonConstants.MASS_UPDATE_END_PERIOD }));

		actionConfigList.add(gtnUIFrameWorkStartEndPeriodActionConfig);

		massUpdateStartPeriodComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		massUpdateStartPeriodComponentConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(massUpdateStartPeriodComponentConfig);
	}

	private void addMassUpdateEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdateEndPeriodConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.MASS_UPDATE_END_PERIOD, parentId);
		massUpdateEndPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		massUpdateEndPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(massUpdateEndPeriodConfig);

		GtnUIFrameworkComponentConfig massUpdateEndPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		massUpdateEndPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		massUpdateEndPeriodComponentConfig.setComponentId(GtnFrameworkCommonConstants.MASS_UPDATE_END_PERIOD);
		massUpdateEndPeriodComponentConfig.setComponentName("End Period");
		massUpdateEndPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		massUpdateEndPeriodComponentConfig.setParentComponentId(massUpdateEndPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig massUpdateEndPeriod = new GtnUIFrameworkComboBoxConfig();
		massUpdateEndPeriod.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		massUpdateEndPeriod.setComboBoxType(GtnFrameworkCommonConstants.PAYMENT_FREQUENCY);
		massUpdateEndPeriodComponentConfig.setGtnComboboxConfig(massUpdateEndPeriod);
		componentList.add(massUpdateEndPeriodComponentConfig);
	}

	private void addMassupdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig massUpdatePopulateButtonConfig = new GtnUIFrameworkComponentConfig();
		massUpdatePopulateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		massUpdatePopulateButtonConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		massUpdatePopulateButtonConfig.setComponentId("massUpdatePopulateButton");
		massUpdatePopulateButtonConfig.setComponentName("POPULATE");
		massUpdatePopulateButtonConfig.setParentComponentId(parentId);
		massUpdatePopulateButtonConfig.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfigForField = new GtnUIFrameWorkActionConfig();

		validationActionConfigForField.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfigForField.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.MASS_UPDATE_FIELD));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Field Selected  ");
		alertParams.add("Please select any Field to update ");

		List<GtnUIFrameWorkActionConfig> alertParamsConfig = new ArrayList<>();
		alertParamsConfig.add(alertActionConfig);

		alertActionConfig.setActionParameterList(alertParams);
		validationActionConfigForField.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.AND, alertParamsConfig }));

		actionConfigList.add(validationActionConfigForField);
		// -------------------------------------------------------------------------------------------------------------
		GtnUIFrameWorkActionConfig validationActionConfigForStartDate = new GtnUIFrameWorkActionConfig();

		validationActionConfigForStartDate.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfigForStartDate
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.MASS_UPDATE_START_PERIOD));

		GtnUIFrameWorkActionConfig alertActionConfigForStartDate = new GtnUIFrameWorkActionConfig();
		alertActionConfigForStartDate.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsForStartDate = new ArrayList<>();
		alertParamsForStartDate.add("No Start Date Selected  ");
		alertParamsForStartDate.add("Please select a start date. ");

		List<GtnUIFrameWorkActionConfig> alertParamsConfigForStartDate = new ArrayList<>();
		alertParamsConfigForStartDate.add(alertActionConfigForStartDate);

		alertActionConfigForStartDate.setActionParameterList(alertParamsForStartDate);
		validationActionConfigForStartDate.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.AND, alertParamsConfigForStartDate }));

		actionConfigList.add(validationActionConfigForStartDate);
		// --------------------------------------------------------------------------------------------------------------
		GtnUIFrameWorkActionConfig validationActionConfigForValue = new GtnUIFrameWorkActionConfig();

		validationActionConfigForValue.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfigForValue.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.MASS_UPDATE_VALUE));

		GtnUIFrameWorkActionConfig alertActionConfigForValue = new GtnUIFrameWorkActionConfig();
		alertActionConfigForValue.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsForValue = new ArrayList<>();
		alertParamsForValue.add("No Value Selected  ");
		alertParamsForValue.add("Please Enter a Value. ");

		List<GtnUIFrameWorkActionConfig> alertParamsConfigForValue = new ArrayList<>();
		alertParamsConfigForValue.add(alertActionConfigForValue);

		alertActionConfigForValue.setActionParameterList(alertParamsForValue);
		validationActionConfigForValue.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.AND, alertParamsConfigForValue }));

		actionConfigList.add(validationActionConfigForValue);
		// --------------------------------------------------------------------------------------------------------------
		GtnUIFrameWorkActionConfig massUpdateValidateActionConfig = new GtnUIFrameWorkActionConfig();
		massUpdateValidateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		massUpdateValidateActionConfig.setActionParameterList(Arrays
				.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_MASS_UPDATE_VALIDATE_ACTION,
						GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE, GtnFrameworkCommonConstants.MASS_UPDATE_FIELD,
						GtnFrameworkCommonConstants.MASS_UPDATE_LEVEL }));
		actionConfigList.add(massUpdateValidateActionConfig);
		// --------------------------------------------------------------------------------------------------------------

		GtnUIFrameWorkActionConfig massUpdateActionConfig = new GtnUIFrameWorkActionConfig();
		massUpdateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		massUpdateActionConfig.setActionParameterList(Arrays.asList(new Object[] {
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_MASS_UPDATE_ACTION,
				GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
				GtnFrameworkCommonConstants.RESULT_TABLE, GtnFrameworkCommonConstants.MASS_UPDATE_FIELD,
				GtnFrameworkCommonConstants.MASS_UPDATE_VALUE, GtnFrameworkCommonConstants.MASS_UPDATE_START_PERIOD,
				GtnFrameworkCommonConstants.MASS_UPDATE_END_PERIOD,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.MASS_UPDATE_LEVEL }));
		actionConfigList.add(massUpdateActionConfig);

		GtnUIFrameWorkActionConfig refreshActionConfig = new GtnUIFrameWorkActionConfig();
		refreshActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		refreshActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_REFRESH_ACTION,
						GtnFrameworkCommonConstants.RESULT_TABLE, Boolean.TRUE }));
		actionConfigList.add(refreshActionConfig);

		massUpdatePopulateButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(massUpdatePopulateButtonConfig);

	}

	/**
	 * @param componentList
	 * @param parentId
	 */
	private void addForecastTabCalculateButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig forecastTabCalculateButtonConfig = new GtnUIFrameworkComponentConfig();
		forecastTabCalculateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		forecastTabCalculateButtonConfig.setComponentId("forecastTabCalculateButton");
		forecastTabCalculateButtonConfig.setComponentName("CALCULATE");
		forecastTabCalculateButtonConfig.setParentComponentId(parentId);
		forecastTabCalculateButtonConfig.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfigForMethodology = new GtnUIFrameWorkActionConfig();

		validationActionConfigForMethodology.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfigForMethodology
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.FORECAST_TAB_METHODOLOGY));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParams = new ArrayList<>();
		alertParams.add("No Methodology Selected  ");
		alertParams.add("Please Select a Methodology ");

		List<GtnUIFrameWorkActionConfig> alertParamsConfig = new ArrayList<>();
		alertParamsConfig.add(alertActionConfig);

		alertActionConfig.setActionParameterList(alertParams);
		validationActionConfigForMethodology.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.AND, alertParamsConfig }));

		// -------------------------------------------------------------------------------
		GtnUIFrameWorkActionConfig validationActionConfigForSP = new GtnUIFrameWorkActionConfig();

		validationActionConfigForSP.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfigForSP
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.FORECAST_TAB_START_PERIOD));

		GtnUIFrameWorkActionConfig alertActionConfigForSP = new GtnUIFrameWorkActionConfig();
		alertActionConfigForSP.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsForSP = new ArrayList<>();
		alertParamsForSP.add(" No Start Period Selected ");
		alertParamsForSP.add("Please Select a Start Period ");

		List<GtnUIFrameWorkActionConfig> alertParamsListForSP = new ArrayList<>();
		alertParamsListForSP.add(alertActionConfigForSP);

		alertActionConfigForSP.setActionParameterList(alertParamsForSP);
		validationActionConfigForSP.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.AND, alertParamsListForSP }));

		// -------------------------------------------------------------------

		GtnUIFrameWorkActionConfig leftTreeTableActionConfig = new GtnUIFrameWorkActionConfig();

		leftTreeTableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		leftTreeTableActionConfig.setActionParameterList(Arrays.asList(new Object[] {
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_RETURNS_PROJECTION_TAB_LEFT_TABLE_VALIDATION_ACTION }));
		leftTreeTableActionConfig.setFieldValues(
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT }));
		// -------------------------------------------------------------------

		GtnUIFrameWorkActionConfig rightTreeTableActionConfig = new GtnUIFrameWorkActionConfig();
		rightTreeTableActionConfig.setFieldValues(Arrays.asList(new String[] { "Descending" }));

		rightTreeTableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rightTreeTableActionConfig.setActionParameterList(Arrays.asList(new Object[] {
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_RETURNS_PROJECTION_TAB_RIGHT_TABLE_VALIDATION_ACTION,
				GtnFrameworkCommonConstants.FORECAST_TAB_METHODOLOGY, GtnFrameworkCommonConstants.RESULT_TABLE,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.FORECAST_TAB_START_PERIOD,
				GtnFrameworkCommonConstants.FORECAST_TAB_END_PERIOD,
				GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP }));

		// -------------------------------------------------------------------
		GtnUIFrameWorkActionConfig customActionConfig = new GtnUIFrameWorkActionConfig();

		customActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionConfig.setActionParameterList(Arrays.asList(new Object[] {
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_RETURNS_PROJECTION_TAB_CALCULATE_ACTION,
				GtnFrameworkCommonConstants.FORECAST_TAB_METHODOLOGY,
				GtnFrameworkCommonConstants.FORECAST_TAB_START_PERIOD,
				GtnFrameworkCommonConstants.FORECAST_TAB_END_PERIOD, GtnFrameworkCommonConstants.RESULT_TABLE,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY }));
		customActionConfig.setFieldValues(
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT }));

		GtnUIFrameWorkActionConfig refreshActionConfig = new GtnUIFrameWorkActionConfig();
		refreshActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		refreshActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_REFRESH_ACTION,
						GtnFrameworkCommonConstants.RESULT_TABLE, Boolean.TRUE }));

		actionConfigList.add(validationActionConfigForMethodology);

		actionConfigList.add(validationActionConfigForSP);

		actionConfigList.add(leftTreeTableActionConfig);

		actionConfigList.add(rightTreeTableActionConfig);

		actionConfigList.add(customActionConfig);

		actionConfigList.add(refreshActionConfig);

		forecastTabCalculateButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(forecastTabCalculateButtonConfig);

	}

	private void addForecastTabEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig forecastTabEndPeriodConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.FORECAST_TAB_END_PERIOD, parentId);
		forecastTabEndPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(forecastTabEndPeriodConfig);

		GtnUIFrameworkComponentConfig forecastTabEndPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		forecastTabEndPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		forecastTabEndPeriodComponentConfig.setComponentId(GtnFrameworkCommonConstants.FORECAST_TAB_END_PERIOD);
		forecastTabEndPeriodComponentConfig.setComponentName("End Period");
		forecastTabEndPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		forecastTabEndPeriodComponentConfig.setParentComponentId(forecastTabEndPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig forecastTabEndPeriod = new GtnUIFrameworkComboBoxConfig();
		forecastTabEndPeriod.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		forecastTabEndPeriod.setComboBoxType(GtnFrameworkCommonConstants.PAYMENT_FREQUENCY);
		forecastTabEndPeriodComponentConfig.setGtnComboboxConfig(forecastTabEndPeriod);
		componentList.add(forecastTabEndPeriodComponentConfig);

	}

	private void addForecastTabStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId) {
		GtnUIFrameworkComponentConfig forecastTabStartPeriodConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.FORECAST_TAB_START_PERIOD, parentId);
		forecastTabStartPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(forecastTabStartPeriodConfig);

		GtnUIFrameworkComponentConfig forecastTabStartPeriodComponentConfig = new GtnUIFrameworkComponentConfig();
		forecastTabStartPeriodComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		forecastTabStartPeriodComponentConfig.setComponentId(GtnFrameworkCommonConstants.FORECAST_TAB_START_PERIOD);
		forecastTabStartPeriodComponentConfig.setComponentName("Start Period:");
		forecastTabStartPeriodComponentConfig.setAddToParent(Boolean.TRUE);
		forecastTabStartPeriodComponentConfig.setParentComponentId(forecastTabStartPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig forecastTabStartPeriod = new GtnUIFrameworkComboBoxConfig();
		forecastTabStartPeriod.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		forecastTabStartPeriod.setComboBoxType(GtnFrameworkCommonConstants.PAYMENT_FREQUENCY);
		forecastTabStartPeriodComponentConfig.setGtnComboboxConfig(forecastTabStartPeriod);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig gtnUIFrameWorkStartEndPeriodActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkStartEndPeriodActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkStartEndPeriodActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_START_END_PERIOD_ACTION,
						GtnFrameworkCommonConstants.FORECAST_TAB_START_PERIOD,
						GtnFrameworkCommonConstants.FORECAST_TAB_END_PERIOD }));

		actionConfigList.add(gtnUIFrameWorkStartEndPeriodActionConfig);

		forecastTabStartPeriodComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		forecastTabStartPeriodComponentConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(forecastTabStartPeriodComponentConfig);

	}

	private void addMethodologyComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig forecastTabMethodologyLayoutConfig = layoutConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.FORECAST_TAB_METHODOLOGY, parentId);
		forecastTabMethodologyLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		componentList.add(forecastTabMethodologyLayoutConfig);

		GtnUIFrameworkComponentConfig forecastTabMethodologyComponentConfig = new GtnUIFrameworkComponentConfig();
		forecastTabMethodologyComponentConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		forecastTabMethodologyComponentConfig.setComponentId(GtnFrameworkCommonConstants.FORECAST_TAB_METHODOLOGY);
		forecastTabMethodologyComponentConfig.setComponentName("Methodology:");
		forecastTabMethodologyComponentConfig.setAddToParent(Boolean.TRUE);
		forecastTabMethodologyComponentConfig.setParentComponentId(forecastTabMethodologyLayoutConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig methodologyConfig = new GtnUIFrameworkComboBoxConfig();
		methodologyConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		methodologyConfig.setComboBoxType("RETURNS_METHODOLOGY");
		forecastTabMethodologyComponentConfig.setGtnComboboxConfig(methodologyConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		forecastTabMethodologyComponentConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(forecastTabMethodologyComponentConfig);

	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig resultsPanel = layoutConfig.getPanelConfig("returnsProjectionResultsPanel",
				parentId);
		resultsPanel.setComponentName("Results");
		resultsPanel.addComponentStyle(GtnUIFrameworkConstants.GTNFRAMEWORK_12.toString());
		componentList.add(resultsPanel);

		GtnFrameworkExpandCollapseLevelSection conf = new GtnFrameworkExpandCollapseLevelSection();
		conf.getRootExpandCollapseSectionLayout(componentList, resultsPanel.getComponentId(), "forecastReturns");
	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String componentId) {
		GtnUIFrameworkComponentConfig resultTableComponentConfig = new GtnUIFrameworkComponentConfig();
		resultTableComponentConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTREETABLE);
		resultTableComponentConfig.setComponentId(GtnFrameworkCommonConstants.RESULT_TABLE);
		resultTableComponentConfig.setComponentName(GtnFrameworkCommonConstants.RESULT_TABLE);
		resultTableComponentConfig.setAddToParent(Boolean.TRUE);
		resultTableComponentConfig.setParentComponentId(componentId);

		GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig = new GtnUIFrameworkPagedTreeTableConfig();

		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add("returnsProjectionResultsPanelPanel");
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(actionConfigList);
		gtnPagedTreeTableConfig.setGtnUIFrameWorkActionConfig(gtnUIFrameWorkActionConfig);

		gtnPagedTreeTableConfig.setLeftHeader(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);
		gtnPagedTreeTableConfig.setRightHeader(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

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

		List<String> fieldFactoryColum = Arrays.asList(GtnFrameworkCommonConstants.CHECK);
		List<GtnUIFrameworkComponentConfig> fieldFactoryComponent = new ArrayList<>();
		gtnPagedTreeTableConfig.setLeftTableEditableColumnList(fieldFactoryColum);

		GtnUIFrameworkComponentConfig checkBox = new GtnUIFrameworkComponentConfig();
		checkBox.setComponentId(GtnFrameworkCommonConstants.CHECK);
		checkBox.setComponentType(GtnUIFrameworkComponentType.CHECKBOX);
		GtnUIFrameworkCheckBoxComponentConfig checkBoxConfig = new GtnUIFrameworkCheckBoxComponentConfig();
		checkBoxConfig.setImmediate(true);

		checkBox.setGtnCheckBoxConfig(checkBoxConfig);
		List<GtnUIFrameWorkActionConfig> checkBoxClickActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkGenerateActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkGenerateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkGenerateActionConfig
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_LEFT_FIELD_FACTORY_ACTION);
		checkBoxClickActionList.add(gtnUIFrameWorkGenerateActionConfig);
		gtnUIFrameWorkGenerateActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));
		checkBox.setGtnUIFrameWorkItemClickActionConfigList(checkBoxClickActionList);

		fieldFactoryComponent.add(checkBox);
		gtnPagedTreeTableConfig.setLeftTableEditableComponentConfig(fieldFactoryComponent);

		List<GtnUIFrameWorkActionConfig> textFieldConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig fieldFactoryCustomAction = new GtnUIFrameWorkActionConfig();

		fieldFactoryCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.RESULT_TABLE));

		fieldFactoryCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		fieldFactoryCustomAction
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_RIGHT_FIELD_FACTORY_ACTION);
		textFieldConfig.add(fieldFactoryCustomAction);
		gtnPagedTreeTableConfig.setComponentconfigActionlist(textFieldConfig);

		gtnPagedTreeTableConfig.setCheckBoxVisibleColoumn(Arrays.asList(GtnFrameworkCommonConstants.CHECK));
		List<GtnUIFrameWorkActionConfig> checkAllConflist = new ArrayList<>();
		GtnUIFrameWorkActionConfig checkAllActionConfig = new GtnUIFrameWorkActionConfig();
		checkAllActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllActionConfig.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_CHECK_ALL_ACTION);
		checkAllConflist.add(checkAllActionConfig);
		gtnPagedTreeTableConfig.setCheckBoxActionConfigList(checkAllConflist);
		gtnPagedTreeTableConfig
				.setCountUrl(GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_COUNT_ACTION);
		gtnPagedTreeTableConfig
				.setCountWsUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE);

		gtnPagedTreeTableConfig.setBulkDataUrl(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_GET_BULK_DATA_ACTION);
		gtnPagedTreeTableConfig.setBulkDataWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE);

		gtnPagedTreeTableConfig.setFillCountUrl(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_PAGED_TREE_TABLE_FILL_COUNT_DATA_ACTION);
		gtnPagedTreeTableConfig.setFillCountWsUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE);

		gtnPagedTreeTableConfig.setLeftHeaderUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_COMP_LEFT_HEADER_FORM_HEADER_CONFIG_ACTION);

		gtnPagedTreeTableConfig.setLeftWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE);

		gtnPagedTreeTableConfig.setRighttHeaderUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_COMP_RIGHT_HEADER_FORM_HEADER_CONFIG_ACTION);

		gtnPagedTreeTableConfig.setRightWsHeaderUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE);

		gtnPagedTreeTableConfig.setLeftHeaderCustomClassLoadUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_LEFT_HEADER_CONFIG_ACTION);

		gtnPagedTreeTableConfig.setRightHeaderCustomClassLoadUrl(
				GtnForecastReturnsClassConstants.GTN_WS_RETURNS_FORECAST_RIGHT_HEADER_CONFIG_ACTION);

		gtnPagedTreeTableConfig.setModuleName(GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME);

		resultTableComponentConfig.setGtnPagedTreeTableConfig(gtnPagedTreeTableConfig);
		componentList.add(resultTableComponentConfig);
	}

	private void addResetRefreshButtons(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig resetAndRefreshLayout = layoutConfig
				.getHorizontalLayoutConfig("returnsProjectionResetAndRefreshLayout", parentId);
		componentList.add(resetAndRefreshLayout);
		GtnUIFrameworkComponentConfig excelButtonConfig = new GtnUIFrameworkComponentConfig();
		excelButtonConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setComponentId("resultTableExcelBtn");
		excelButtonConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		excelButtonConfig.setAddToParent(true);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonInput = new GtnUIFrameworkExcelButtonConfig();
		gtnUIFrameworkExcelButtonInput.setIsTreeTable(true);
		gtnUIFrameworkExcelButtonInput.setExportFileName("Returns Forecasting");
		gtnUIFrameworkExcelButtonInput.setHeaderServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_EXCEL_HEADERS_SERVICE);
		gtnUIFrameworkExcelButtonInput.setLoadDataServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_EXCEL_DATA_SERVICE);
		gtnUIFrameworkExcelButtonInput.setServiceType("forecast");
		gtnUIFrameworkExcelButtonInput
				.setServiceInput(new Object[] { GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT });

		GtnUIFrameWorkActionConfig resultTableExcelAction = new GtnUIFrameWorkActionConfig();
		resultTableExcelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		resultTableExcelAction.addActionParameter(gtnUIFrameworkExcelButtonInput);

		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonInput);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(resultTableExcelAction));
		componentList.add(excelButtonConfig);

		GtnUIFrameworkComponentConfig resultTableResetConfig = new GtnUIFrameworkComponentConfig();
		resultTableResetConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resultTableResetConfig.setComponentId("resultTableResetBtn");
		resultTableResetConfig.setComponentName("RESET");
		resultTableResetConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		resultTableResetConfig.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> gtnUIFrameworkComponentConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkGenerateActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkGenerateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUIFrameWorkGenerateActionConfig.setActionParameterList(Arrays
				.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_GENERATE_ACTION, "returns",
						"product", "Quarterly", "returnsForecastMainScreenDataSelection_relationShipCombobox",
						"returnsForecastMainScreenDataSelection_dualListBoxComp",
						"returnsForecastMainScreenDataSelection_forecastLevel",
						GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
						GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
						"returnsForecastMainScreenDataSelection_fromPeriod",
						"returnsForecastMainScreenDataSelection_toPeriod", "forecastReturnsResetButton" }));

		gtnUIFrameworkComponentConfigList.add(gtnUIFrameWorkGenerateActionConfig);
		resultTableResetConfig.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkComponentConfigList);
		componentList.add(resultTableResetConfig);

		GtnUIFrameworkComponentConfig resultTableRefreshBtnConfig = new GtnUIFrameworkComponentConfig();
		resultTableRefreshBtnConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resultTableRefreshBtnConfig.setComponentId("resultTableRefreshBtn");
		resultTableRefreshBtnConfig.setComponentName("REFRESH");
		resultTableRefreshBtnConfig.setParentComponentId(resetAndRefreshLayout.getComponentId());
		resultTableRefreshBtnConfig.setAddToParent(Boolean.TRUE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig refreshActionConfig = new GtnUIFrameWorkActionConfig();
		refreshActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		refreshActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_REFRESH_ACTION,
						GtnFrameworkCommonConstants.RESULT_TABLE, Boolean.FALSE }));
		actionConfigList.add(refreshActionConfig);
		resultTableRefreshBtnConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
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
