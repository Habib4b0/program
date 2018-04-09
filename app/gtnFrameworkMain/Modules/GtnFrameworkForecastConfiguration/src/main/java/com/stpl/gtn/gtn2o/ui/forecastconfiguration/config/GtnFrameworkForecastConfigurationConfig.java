/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnFrameworkSaveAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkDataFrequencyValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkFromPeriodValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkHistoryIntervalValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkIntervalFrequencyValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkModeValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkProcessTypeValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action.GtnUIFrameworkToPeriodValueChangeAction;
import com.stpl.gtn.gtn2o.ui.forecastconfiguration.constants.GtnFrameworkForecastConfigurationContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkForecastConfigurationConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getMainView() {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName(GtnFrameworkForecastConfigurationContants.FC_VIEW);
		view.setViewId(GtnFrameworkForecastConfigurationContants.FC_VIEW);
		view.setDefaultView(true);
		addComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addForecastConfigurationMainPanel(componentList, namspacePrefix);

	}

	private void addForecastConfigurationMainPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_MAIN_PANEL, false,
				null);
		componentList.add(panel);
		addForecastConfigurationMainLayout(componentList, namspacePrefix);
	}

	private void addForecastConfigurationMainLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getVerticalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_MAIN_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_MAIN_PANEL);
		gtnLayout.setMargin(true);
		gtnLayout.setSpacing(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addForecastConfigurationPanel(componentList, namspacePrefix);
		addResultPanel(componentList, namspacePrefix);
		addExcelButtonLayout(componentList, namspacePrefix);
	}

	private void addForecastConfigurationPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_PANEL, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_MAIN_LAYOUT);
		panelConfig.setComponentName(GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION);
		componentList.add(panelConfig);
		addFieldLayout(componentList, namspacePrefix);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "resultPanel", true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_MAIN_LAYOUT);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addResultLayout(componentList, namspacePrefix);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "resultlayout", true, namspacePrefix + "resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList, namspacePrefix);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getVerticalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_PANEL);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addErrorDisplay(componentList, namspacePrefix);
		addProcessConfigurationFieldComponent(componentList, namspacePrefix);
		addDataConfigurationLayout(componentList, namspacePrefix);
		addActionButtonLayout(componentList, namspacePrefix);
	}

	private void addErrorDisplay(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + "errorDisplayLayout", true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_LAYOUT);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig errorLabelComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "errorDisplay", true, namspacePrefix + "errorDisplayLayout",
				GtnUIFrameworkComponentType.LABEL);
		errorLabelComponentConfig.setAuthorizationIncluded(true);
		errorLabelComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		errorLabelComponentConfig.addComponentStyle(GtnFrameworkCssConstants.ERROR);
		errorLabelComponentConfig.setVisible(false);
		componentList.add(errorLabelComponentConfig);
	}

	private void addProcessConfigurationFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig mainCssConfig = commonConfig.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_CSS_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_LAYOUT);
		mainCssConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		mainCssConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		componentList.add(mainCssConfig);

		GtnUIFrameworkComponentConfig availableHierarchyCssConfig = commonConfig.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.PROCESS_CONFIG_MAIN_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_CSS_LAYOUT);
		availableHierarchyCssConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_5);
		availableHierarchyCssConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		componentList.add(availableHierarchyCssConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.PROCESS_CONFIG_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.PROCESS_CONFIG_MAIN_LAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		gtnLayoutConfig.setComponentStyle(styleList);
		componentList.add(gtnLayoutConfig);

		addBusinessProcess(componentList, namspacePrefix);
		addProcessType(componentList, namspacePrefix);
		addMode(componentList, namspacePrefix);
	}

	private void addDataConfigurationLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig mainCssConfig = commonConfig.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_CONFIGURATION_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_LAYOUT);
		mainCssConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(mainCssConfig);
		addDataConfigurationFieldComponent(componentList, namspacePrefix);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnCssLayout = commonConfig.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.ACTION_BUTTON_CSSLAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_LAYOUT);
		gtnCssLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnCssLayout);
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.ACTION_BUTTONLAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.ACTION_BUTTON_CSSLAYOUT);
		componentList.add(gtnLayout);
		addSaveButtonComponent(componentList, namspacePrefix);
		addResetButtonComponent(componentList, namspacePrefix);
	}

	private void addDataConfigurationFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		addHistoricalDataFrequency(componentList, namspacePrefix);
		addHistoricalDataTimePeriodFrom(componentList, namspacePrefix);
		addHistoricalDataInterval(componentList, namspacePrefix);
		addHistoricalDataPeriod(componentList, namspacePrefix);
		addFutureIntervalFrequency(componentList, namspacePrefix);
		addFutureInterval(componentList, namspacePrefix);
		addHistoricalDataTimePeriodTo(componentList, namspacePrefix);
		addForecastPeriod(componentList, namspacePrefix);
	}

	private void addBusinessProcess(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.BUSINESS_PROCESS_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.PROCESS_CONFIG_LAYOUT);
		componentList.add(gtnLayoutConfig);
		GtnUIFrameworkComponentConfig businessProcessComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.BUSINESS_PROCESS, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.BUSINESS_PROCESS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		businessProcessComponentConfig.setComponentName("Business Process");
		componentList.add(businessProcessComponentConfig);
		GtnUIFrameworkComboBoxConfig businessProcessComboBoxConfig = commonConfig.getComboBoxConfig(
				GtnFrameworkForecastConfigurationContants.BUSINESS_PROCESS_TYPE,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessProcessComponentConfig.setGtnComboboxConfig(businessProcessComboBoxConfig);
		businessProcessComboBoxConfig.setRequired(true);
		businessProcessComboBoxConfig.setRequiredMessage("Please Select Business Process");
	}

	private void addProcessType(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + "processTypeLayout", true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.PROCESS_CONFIG_LAYOUT);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig processTypeComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.PROCESS_TYPE, true,
				namspacePrefix + "processTypeLayout", GtnUIFrameworkComponentType.OPTIONGROUP);
		processTypeComponentConfig.setAuthorizationIncluded(true);
		processTypeComponentConfig.setComponentName("Process Type");

		GtnUIFrameworkOptionGroupConfig processTypeOptionGroupConfig = commonConfig.getOptionGroupConfig(
				Arrays.asList(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_AUTO_UPDATE,
						GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED),
				GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED, false);

		processTypeComponentConfig
				.setComponentStyle(Arrays.asList(GtnFrameworkForecastConfigurationContants.HORIZONTAL));
		processTypeComponentConfig.setGtnUIFrameworkOptionGroupConfig(processTypeOptionGroupConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkProcessTypeValueChangeAction.class.getName());
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_TO);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL);
		actionConfigList.add(customAction);
		processTypeComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(processTypeComponentConfig);
	}

	private void addMode(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.MODE_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.PROCESS_CONFIG_LAYOUT);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig modeComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.MODE, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.MODE_LAYOUT,
				GtnUIFrameworkComponentType.OPTIONGROUP);
		modeComponentConfig.setAuthorizationIncluded(true);
		modeComponentConfig.setComponentName("Mode");

		GtnUIFrameworkOptionGroupConfig modeOptionGroupConfig = commonConfig.getOptionGroupConfig(
				Arrays.asList(GtnWsForecastConfigurationConstants.MODE_VALUE_INTERVAL,
						GtnWsForecastConfigurationConstants.MODE_VALUE_PERIOD),
				GtnWsForecastConfigurationConstants.MODE_VALUE_INTERVAL, false);

		modeComponentConfig.setComponentStyle(Arrays.asList(GtnFrameworkForecastConfigurationContants.HORIZONTAL));
		modeComponentConfig.setGtnUIFrameworkOptionGroupConfig(modeOptionGroupConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkModeValueChangeAction.class.getName());
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_FROM_LAYOUT);
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_TO_LAYOUT);
		customAction
				.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_FREQUENCY_LAYOUT);
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_INTERVAL);
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY_LAYOUT);
		customAction
				.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL_LAYOUT);
		customAction
				.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_PERIOD);
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_TO);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL);
		actionConfigList.add(customAction);
		modeComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(modeComponentConfig);
	}

	private void addHistoricalDataTimePeriodFrom(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_FROM_LAYOUT,
				true, namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_CONFIGURATION_LAYOUT);
		gtnLayoutConfig.setVisible(false);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig historicalDataTimePeriodConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_FROM, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_FROM_LAYOUT,
				GtnUIFrameworkComponentType.DATEFIELD);
		historicalDataTimePeriodConfig.setAuthorizationIncluded(true);
		historicalDataTimePeriodConfig.setComponentName("Historical Data Time Period From");
		componentList.add(historicalDataTimePeriodConfig);

		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkFromPeriodValueChangeAction.class.getName());
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_TO);
		dateFieldConfig.addValueChangeActionConfig(customAction);
		historicalDataTimePeriodConfig.setGtnDateFieldConfig(dateFieldConfig);
		dateFieldConfig.setImmediate(true);
		dateFieldConfig.setRequired(true);
		dateFieldConfig.setRequiredMessage("Please Enter Historical Data Time Period From");

	}

	private void addHistoricalDataTimePeriodTo(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_TO_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_CONFIGURATION_LAYOUT);
		gtnLayoutConfig.setVisible(false);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig historicalDataTimePeriodConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_TO, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_TO_LAYOUT,
				GtnUIFrameworkComponentType.DATEFIELD);
		historicalDataTimePeriodConfig.setAuthorizationIncluded(true);
		historicalDataTimePeriodConfig.setComponentName("Historical Data Time Period To");
		componentList.add(historicalDataTimePeriodConfig);

		GtnUIFrameworkDateFieldConfig dateFieldConfig = new GtnUIFrameworkDateFieldConfig();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkToPeriodValueChangeAction.class.getName());
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_FROM);
		dateFieldConfig.addValueChangeActionConfig(customAction);
		dateFieldConfig.setImmediate(true);
		dateFieldConfig.setRequired(true);
		dateFieldConfig.setRequiredMessage("Please Enter Historical Data Time Period To");
		historicalDataTimePeriodConfig.setGtnDateFieldConfig(dateFieldConfig);
	}

	private void addHistoricalDataFrequency(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_FREQUENCY_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_CONFIGURATION_LAYOUT);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig dataFrequencyComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_FREQUENCY, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_FREQUENCY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		dataFrequencyComponentConfig.setAuthorizationIncluded(true);
		dataFrequencyComponentConfig.setComponentName("Historical Data Frequency");
		componentList.add(dataFrequencyComponentConfig);

		GtnUIFrameworkComboBoxConfig dataFrequencyComboBoxConfig = commonConfig.getComboBoxConfig(
				GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_FREQUENCY_LIST_NAME,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		dataFrequencyComponentConfig.setGtnComboboxConfig(dataFrequencyComboBoxConfig);
		dataFrequencyComboBoxConfig.setRequired(true);
		dataFrequencyComboBoxConfig.setRequiredMessage("Please Select Historical Data Frequency");

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkDataFrequencyValueChangeAction.class.getName());
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_INTERVAL);
		customAction
				.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_PERIOD);
		actionConfigList.add(customAction);
		dataFrequencyComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addHistoricalDataInterval(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + "historicalDataIntervalLayout", true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_CONFIGURATION_LAYOUT);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig companyIdConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_INTERVAL, true,
				namspacePrefix + "historicalDataIntervalLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Historical Data Interval");

		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkHistoryIntervalValueChangeAction.class.getName());
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_FREQUENCY);
		customAction
				.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_PERIOD);
		textBoxConfig.addValueChangeActionConfig(customAction);
		setIntervalTextboxConfig(companyIdConfig, textBoxConfig, componentList,
				"Please Enter Historical Data Interval");
	}

	private void addHistoricalDataPeriod(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + "historicalDataPeriodLayout", true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_CONFIGURATION_LAYOUT);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig historicalDataPeriod = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_PERIOD, true,
				namspacePrefix + "historicalDataPeriodLayout", GtnUIFrameworkComponentType.TEXTBOX);
		historicalDataPeriod.setAuthorizationIncluded(true);
		historicalDataPeriod.setComponentName("Historical Data Period");
		historicalDataPeriod.setEnable(false);

		GtnUIFrameworkValidationConfig historicalDataPeriodValidationConfig = new GtnUIFrameworkValidationConfig();
		historicalDataPeriodValidationConfig.setMaxLength(5);
		historicalDataPeriod.setGtnUIFrameworkValidationConfig(historicalDataPeriodValidationConfig);

		componentList.add(historicalDataPeriod);
	}

	private void addFutureIntervalFrequency(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_CONFIGURATION_LAYOUT);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig intervalFrequencyComponentConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		intervalFrequencyComponentConfig.setAuthorizationIncluded(true);
		intervalFrequencyComponentConfig.setComponentName("Future Interval Frequency");

		componentList.add(intervalFrequencyComponentConfig);

		GtnUIFrameworkComboBoxConfig intervalFrequencyComboBoxConfig = commonConfig.getComboBoxConfig(
				GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_FREQUENCY_LIST_NAME,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		intervalFrequencyComponentConfig.setGtnComboboxConfig(intervalFrequencyComboBoxConfig);
		intervalFrequencyComboBoxConfig.setRequired(true);
		intervalFrequencyComboBoxConfig.setRequiredMessage("Please Select Future Interval Frequency");
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkIntervalFrequencyValueChangeAction.class.getName());
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_PERIOD);
		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig customActionValueChange = new GtnUIFrameWorkActionConfig();
		customActionValueChange.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionValueChange.addActionParameter(GtnUIFrameworkDataFrequencyValueChangeAction.class.getName());
		customActionValueChange
				.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL);
		customActionValueChange
				.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_PERIOD);
		actionConfigList.add(customActionValueChange);
		intervalFrequencyComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addFutureInterval(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL_LAYOUT, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_CONFIGURATION_LAYOUT);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig companyIdConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL, true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Future Interval");

		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkIntervalFrequencyValueChangeAction.class.getName());
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_PERIOD);
		textBoxConfig.addValueChangeActionConfig(customAction);
		setIntervalTextboxConfig(companyIdConfig, textBoxConfig, componentList, "Please Enter Future Interval");
	}

	private void addForecastPeriod(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getHorizontalLayoutConfig(
				namspacePrefix + "forecastPeriodLayout", true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_CONFIGURATION_LAYOUT);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig forecastPeriodConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_PERIOD, true,
				namspacePrefix + "forecastPeriodLayout", GtnUIFrameworkComponentType.TEXTBOX);
		forecastPeriodConfig.setAuthorizationIncluded(true);
		forecastPeriodConfig.setComponentName("Forecast Period");
		forecastPeriodConfig.setEnable(false);
                forecastPeriodConfig.setResetToDefaultAllowed(false);
		GtnUIFrameworkTextBoxConfig textBoxConfig = commonConfig.getTextBoxConfig(false, false, true);
		textBoxConfig.setValueLoadFromService(true);
		textBoxConfig.setLoadingUrl(GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
				+ GtnWsForecastConfigurationConstants.LOAD_FORECAST_PERIOD);

		GtnUIFrameworkValidationConfig historicalDataPeriodValidationConfig = new GtnUIFrameworkValidationConfig();
		historicalDataPeriodValidationConfig.setMaxLength(5);
		forecastPeriodConfig.setGtnUIFrameworkValidationConfig(historicalDataPeriodValidationConfig);
                forecastPeriodConfig.setGtnTextBoxConfig(textBoxConfig);
		componentList.add(forecastPeriodConfig);
	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSave", true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Save");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkSaveAction.class.getName());
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_FREQUENCY);
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_INTERVAL);
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_TO);
		customAction.addActionParameter(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_FROM);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.BUSINESS_PROCESS);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.PROCESS_TYPE);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.MODE);
		customAction.addActionParameter(namspacePrefix + GtnFrameworkForecastConfigurationContants.SEARCH_RESULT_TABLE);
		customAction.addActionParameter(namspacePrefix + "errorDisplay");
		actionConfigList.add(customAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true,
				namspacePrefix + GtnFrameworkForecastConfigurationContants.ACTION_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("Reset");
		componentList.add(resetButtonConfig);

		List<String> resetFieldList = new ArrayList<>();

		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.BUSINESS_PROCESS);
		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.PROCESS_TYPE);
		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.MODE);
		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_TO);
		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_TIME_PERIOD_FROM);
		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.DATA_FREQUENCY);
		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.INTERVAL_FREQUENCY);
		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_INTERVAL);
		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.FUTURE_INTERVAL);
		resetFieldList.add(namspacePrefix + GtnFrameworkForecastConfigurationContants.HISTORICAL_DATA_PERIOD);
		List<Object> resetValueList = new ArrayList<>();
		resetValueList.add(null);
		resetValueList.add(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED);
		resetValueList.add(GtnWsForecastConfigurationConstants.MODE_VALUE_INTERVAL);
		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetValueList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetValueList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		resetActionConfig.addActionParameter("Are you sure you want to reset the page to default/previous values ?");
		resetActionConfig.addActionParameter(resetFieldList);
		resetActionConfig.addActionParameter(resetValueList);
		resetButtonConfig.addGtnUIFrameWorkActionConfig(resetActionConfig);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig forecastConfigSearchResultConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.SEARCH_RESULT_TABLE, true,
				namspacePrefix + "resultlayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		forecastConfigSearchResultConfig.setAuthorizationIncluded(true);
		forecastConfigSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastConfigSearchResultConfig.setComponentName("Results");
		forecastConfigSearchResultConfig.setResetToDefaultAllowed(false);

		componentList.add(forecastConfigSearchResultConfig);

		GtnUIFrameworkPagedTableConfig forecastConfigSearchResult = commonConfig.getPagedTableConfig(true, true,
				GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
						+ GtnWsForecastConfigurationConstants.GET_FORECAST_CONF_TABLE_DATA,
				GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
						+ GtnWsForecastConfigurationConstants.GET_FORECAST_CONF_TABLE_DATA,
				"forecastConfiguration", "SearchQuery");
		forecastConfigSearchResult.setEditable(false);
		forecastConfigSearchResult.setSinkItemPerPageWithPageLength(false);
		forecastConfigSearchResult.setTableColumnDataType(
				GtnFrameworkForecastConfigurationContants.getForecastConfigurationTableColumnType());
		forecastConfigSearchResult
				.setTableVisibleHeader(GtnFrameworkForecastConfigurationContants.getForecastConfigurationTableHeader());
		forecastConfigSearchResult.setTableColumnMappingId(
				GtnFrameworkForecastConfigurationContants.getForecastConfigurationTableColumns());
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(forecastConfigSearchResultConfig.getComponentId());
		forecastConfigSearchResult.addPostCreationActionConfig(loadDataTableActionConfig);
		forecastConfigSearchResultConfig.setGtnPagedTableConfig(forecastConfigSearchResult);
		forecastConfigSearchResult.setCustomFilterConfigMap(getTableFilterFieldMap());

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getTableFilterFieldMap() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig cfpStatusFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		GtnUIFrameworkComponentConfig businessProcessConfig = commonConfig.getUIFrameworkComponentConfig(
				"customFilterComboBox", false, null, GtnUIFrameworkComponentType.COMBOBOX);
		businessProcessConfig.setAuthorizationIncluded(true);
		businessProcessConfig.setComponentName("customFilterComboBox");
		GtnUIFrameworkComboBoxConfig businessProcessComboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		businessProcessComboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessProcessComboBoxConfig.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
		businessProcessComboBoxConfig.setComboBoxType(GtnFrameworkForecastConfigurationContants.BUSINESS_PROCESS_TYPE);
		businessProcessConfig.setGtnComboboxConfig(businessProcessComboBoxConfig);
		cfpStatusFilterConfig.setPropertId(GtnFrameworkForecastConfigurationContants.BUSINESS_PROCESS);
		cfpStatusFilterConfig.setGtnComponentConfig(businessProcessConfig);
		cfpStatusFilterConfig.setGtnComponentType(businessProcessConfig.getComponentType());
		customFilterConfigMap.put(cfpStatusFilterConfig.getPropertId(), cfpStatusFilterConfig);
		return customFilterConfigMap;
	}

	public void addExcelButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(namspacePrefix + GtnFrameworkForecastConfigurationContants.EXCEL_BUTTONLAYOUT);
		gtnLayout.setParentComponentId(
				namspacePrefix + GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION_MAIN_LAYOUT);
		gtnLayout.setAddToParent(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addExcelButtonComponent(componentList, namspacePrefix);
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig excelBtnComponentConfig = new GtnUIFrameworkComponentConfig();
		excelBtnComponentConfig.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelBtnComponentConfig.setAuthorizationIncluded(true);
		excelBtnComponentConfig.setComponentId(namspacePrefix + "gtnExcelButton");
		excelBtnComponentConfig.setAddToParent(true);
		excelBtnComponentConfig
				.setParentComponentId(namspacePrefix + GtnFrameworkForecastConfigurationContants.EXCEL_BUTTONLAYOUT);
		componentList.add(excelBtnComponentConfig);
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName(GtnFrameworkForecastConfigurationContants.FORECAST_CONFIGURATION);
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig
				.setExportTableId(namspacePrefix + GtnFrameworkForecastConfigurationContants.SEARCH_RESULT_TABLE);
		excelBtnComponentConfig.setGtnUIFrameworkExcelButtonConfig(gtnExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnExcelButtonConfig);
		excelBtnComponentConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	void setIntervalTextboxConfig(GtnUIFrameworkComponentConfig companyIdConfig,
			GtnUIFrameworkTextBoxConfig textBoxConfig, List<GtnUIFrameworkComponentConfig> componentList,
			String message) {
		companyIdConfig.setGtnTextBoxConfig(textBoxConfig);
		textBoxConfig.setImmediate(true);
		textBoxConfig.setRequired(true);
		textBoxConfig.setRequiredMessage(message);
		componentList.add(companyIdConfig);
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setAttachRegxValidatior(true);
		valConfig.setFormatString("([0-9])*");
		valConfig.setRegxValidationMessage("Interval values can contain only numbers");
		companyIdConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}
}
