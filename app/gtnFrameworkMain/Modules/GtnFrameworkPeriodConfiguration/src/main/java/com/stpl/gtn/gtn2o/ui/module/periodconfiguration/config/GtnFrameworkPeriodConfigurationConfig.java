/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkModeFromToAction;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action.GtnFrameworkPeriodFieldValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.constants.GtnFrameworkPeriodConfigurationContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.periodconfig.constants.GtnWsPeriodConfigurationConstants;

public class GtnFrameworkPeriodConfigurationConfig {

	public GtnUIFrameworkViewConfig getMainView() {
		GtnFrameworkComponentConfigProvider componentConfigProvider = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig view = componentConfigProvider.getViewConfig("PCView", "PCView", true);
		addComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE,
				componentConfigProvider);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addPeriodConfigurationMainPanel(componentList, namspacePrefix, componentConfigProvider);

	}

	private void addPeriodConfigurationMainPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig panel = componentConfigProvider
				.getPanelConfig(namspacePrefix + "PeriodConfigurationMainPanel", false, null);
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);
		addPeriodConfigurationMainLayout(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addPeriodConfigurationMainLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getVerticalLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIGURATION_MAIN_LAYOUT, true,
				namspacePrefix + "PeriodConfigurationMainPanel");
		gtnLayout.setMargin(true);
		gtnLayout.setSpacing(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);

		addSelectionConfigurationPanel(componentList, namspacePrefix, componentConfigProvider);
		addPeriodConfigurationPanel(componentList, namspacePrefix, componentConfigProvider);
		addResultPanel(componentList, namspacePrefix, componentConfigProvider);

	}

	private void addSelectionConfigurationPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig panelConfig = componentConfigProvider.getPanelConfig(
				namspacePrefix + "selectionConfigurationPanel", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIGURATION_MAIN_LAYOUT);
		panelConfig.setComponentName("Selection");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addSelectionLayout(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addPeriodConfigurationPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig panelConfig = componentConfigProvider.getPanelConfig(
				namspacePrefix + "PeriodConfigurationPanel", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIGURATION_MAIN_LAYOUT);
		panelConfig.setComponentName("Period Configuration");
		componentList.add(panelConfig);
		addPeriodConfigurationLayout(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addPeriodConfigurationLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnMainLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "periodConfigurationFromToMainLayout", true,
				namspacePrefix + "PeriodConfigurationPanel");

		gtnMainLayout.setComponentWidth(GtnFrameworkPeriodConfigurationContants.ONE_HUNDRED_PERCENT);
		gtnMainLayout.setSpacing(true);
		gtnMainLayout.setMargin(true);
		componentList.add(gtnMainLayout);

		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIG_LAYOUT, true,
				namspacePrefix + "periodConfigurationFromToMainLayout");

		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addPeriodView(componentList, namspacePrefix, componentConfigProvider);
		addPeriodConfigFromToLayout(componentList, namspacePrefix, componentConfigProvider);

	}

	private void addPeriodConfigurationFromPeriodPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig panelConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "PeriodConfigurationFromPeriodPanel", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIG_FROM_TO_LAYOUT,
				GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setComponentName("From Period");
                panelConfig.setComponentWidth(GtnFrameworkPeriodConfigurationContants.FIFTY_PERCENT);
		panelConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(panelConfig);
		addPeriodFromLayout(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addPeriodConfigurationToPeriodPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig panelConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "PeriodConfigurationToPeriodPanel", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIG_FROM_TO_LAYOUT,
				GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setComponentName("To Period");
                panelConfig.setComponentWidth(GtnFrameworkPeriodConfigurationContants.FIFTY_PERCENT);
		panelConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(panelConfig);
		addPeriodtoLayout(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig panelConfig = componentConfigProvider.getPanelConfig(
				namspacePrefix + "resultPanel", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIGURATION_MAIN_LAYOUT);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addResultLayout(componentList, namspacePrefix, componentConfigProvider);

	}

	private void addPeriodConfigFromToLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIG_FROM_TO_LAYOUT, true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIG_LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addPeriodConfigurationFromPeriodPanel(componentList, namspacePrefix, componentConfigProvider);
		addPeriodConfigurationToPeriodPanel(componentList, namspacePrefix, componentConfigProvider);

	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider
				.getVerticalLayoutConfig(namspacePrefix + "resultMainlayout", true, namspacePrefix + "resultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList, namspacePrefix, componentConfigProvider);
		addActionButtonLayout(componentList, namspacePrefix, componentConfigProvider);

	}

	private void addSelectionLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.SELECTION_LAYOUT, true,
				namspacePrefix + "selectionConfigurationPanel");
		gtnLayout.setComponentWidth(GtnFrameworkPeriodConfigurationContants.ONE_HUNDRED_PERCENT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_4));
		componentList.add(gtnLayout);
		addSelectionFieldComponent(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addPeriodFromLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getCssLayoutConfig(
				namspacePrefix + "periodMainFromLayout", true, namspacePrefix + "PeriodConfigurationFromPeriodPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_LAYOUT, true,
				namspacePrefix + "periodMainFromLayout");

		gtnCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(gtnCssLayout);

		addPeriodConfigurationFromComponent(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addPeriodtoLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getCssLayoutConfig(
				namspacePrefix + "periodMainToLayout", true, namspacePrefix + "PeriodConfigurationToPeriodPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_LAYOUT, true,
				namspacePrefix + "periodMainToLayout");

		gtnCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(gtnCssLayout);
		addPeriodConfigurationToComponent(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addSelectionFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		/**
		 * Need to add grid layout
		 */
		addModule(componentList, namspacePrefix, componentConfigProvider);
		addBusinessProcess(componentList, namspacePrefix, componentConfigProvider);
		addCompany(componentList, namspacePrefix, componentConfigProvider);
		addBusinessUnit(componentList, namspacePrefix, componentConfigProvider);

	}

	private void addPeriodConfigurationFromComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, GtnFrameworkComponentConfigProvider componentConfigProvider) {
		addModeFrom(componentList, namspacePrefix, componentConfigProvider);
		addDefaultModeFrom(componentList, namspacePrefix, componentConfigProvider);
		addFrequencyFrom(componentList, namspacePrefix, componentConfigProvider);
		addDefaultFrequencyFrom(componentList, namspacePrefix, componentConfigProvider);
		addPeriodFrom(componentList, namspacePrefix, componentConfigProvider);
		addDefaultPeriodFrom(componentList, namspacePrefix, componentConfigProvider);
		addPeriodFromTextbox(componentList, namspacePrefix, componentConfigProvider);
		addDefaultPeriodFromTextBox(componentList, namspacePrefix, componentConfigProvider);
		addDateFrom(componentList, namspacePrefix, componentConfigProvider);
		addDefaultDateFrom(componentList, namspacePrefix, componentConfigProvider);

	}

	private void addPeriodConfigurationToComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, GtnFrameworkComponentConfigProvider componentConfigProvider) {
		addModeTo(componentList, namspacePrefix, componentConfigProvider);
		addDefaultModeTo(componentList, namspacePrefix, componentConfigProvider);
		addFrequencyTo(componentList, namspacePrefix, componentConfigProvider);
		addDefaultFrequencyTo(componentList, namspacePrefix, componentConfigProvider);
		addPeriodTo(componentList, namspacePrefix, componentConfigProvider);
		addDefaultPeriodTo(componentList, namspacePrefix, componentConfigProvider);
		addPeriodToTextbox(componentList, namspacePrefix, componentConfigProvider);
		addDefaultPeriodToTextBox(componentList, namspacePrefix, componentConfigProvider);
		addDateTo(componentList, namspacePrefix, componentConfigProvider);
		addDefaultDateTo(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.ACTION_BUTTONLAYOUT, true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIGURATION_MAIN_LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(gtnLayout);
		addSaveButtonComponent(componentList, namspacePrefix, componentConfigProvider);
		addResetButtonComponent(componentList, namspacePrefix, componentConfigProvider);
		addExcelButtonLayout(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addModule(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "ModuleLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.SELECTION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig moduleComponentConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.MODULE, true, namspacePrefix + "ModuleLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		moduleComponentConfig.setAuthorizationIncluded(true);
		moduleComponentConfig.setComponentName("Module");
		moduleComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.MODULE);
		componentList.add(moduleComponentConfig);

		GtnUIFrameworkComboBoxConfig moduleComboBoxConfig = componentConfigProvider.getComboBoxConfig("Module",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		moduleComboBoxConfig.setRequired(true);
		moduleComponentConfig.setGtnComboboxConfig(moduleComboBoxConfig);

	}

	private void addBusinessProcess(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "businessProcessLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.SELECTION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig businessProcessComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(
						namspacePrefix + GtnFrameworkPeriodConfigurationContants.BUSINESS_PROCESS, true,
						namspacePrefix + "businessProcessLayout", GtnUIFrameworkComponentType.COMBOBOX);
		businessProcessComponentConfig.setAuthorizationIncluded(true);

		businessProcessComponentConfig.setComponentName("Business Process");
		businessProcessComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.BUSINESS_PROCESS);
		componentList.add(businessProcessComponentConfig);
		GtnUIFrameworkComboBoxConfig businessProcessComboBoxConfig = componentConfigProvider
				.getComboBoxConfig("BUSINESS_PROCESS", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessProcessComboBoxConfig.setRequired(true);
		businessProcessComponentConfig.setGtnComboboxConfig(businessProcessComboBoxConfig);

	}

	private void addCompany(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "companyLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.SELECTION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyComponentConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.COMPANY, true,
				namspacePrefix + "companyLayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyComponentConfig.setAuthorizationIncluded(true);
		companyComponentConfig.setComponentName("Company");
		companyComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.COMPANY);
		componentList.add(companyComponentConfig);
		GtnUIFrameworkComboBoxConfig companyComboBoxConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkPeriodConfigurationContants.COMPANY, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyComponentConfig.setGtnComboboxConfig(companyComboBoxConfig);

	}

	private void addBusinessUnit(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "businessUnitLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.SELECTION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig businessUnitComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(namspacePrefix + GtnFrameworkPeriodConfigurationContants.BUSINESS_UNIT,
						true, namspacePrefix + "businessUnitLayout", GtnUIFrameworkComponentType.COMBOBOX);
		businessUnitComponentConfig.setAuthorizationIncluded(true);
		businessUnitComponentConfig.setComponentName("Business Unit");
		businessUnitComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.BUSINESS_UNIT);
		componentList.add(businessUnitComponentConfig);
		GtnUIFrameworkComboBoxConfig businessUnitComboBoxConfig = componentConfigProvider
				.getComboBoxConfig("BusinessUnitGLcomp", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		businessUnitComponentConfig.setGtnComboboxConfig(businessUnitComboBoxConfig);

	}

	private void addPeriodView(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider.getCssLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW, true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_CONFIG_LAYOUT);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig processTypeComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW, true,
						namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW,
						GtnUIFrameworkComponentType.OPTIONGROUP);
		processTypeComponentConfig.setAuthorizationIncluded(true);
		processTypeComponentConfig.setComponentName("Period View");
		processTypeComponentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		processTypeComponentConfig.setSpacing(true);

		GtnUIFrameworkOptionGroupConfig processTypeOptionGroupConfig = componentConfigProvider.getOptionGroupConfig(
				Arrays.asList(GtnFrameworkPeriodConfigurationContants.SINGLE, "Multiple"),
				GtnFrameworkPeriodConfigurationContants.SINGLE, false);
		processTypeComponentConfig.setComponentStyle(Arrays.asList("horizontal"));
		processTypeComponentConfig.setGtnUIFrameworkOptionGroupConfig(processTypeOptionGroupConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW_ACTION);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.MODE_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.FREQUENCY_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_TO_TEXT_BOX);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_TEXT_BOX);
		actionConfigList.add(customAction);
		processTypeComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(processTypeComponentConfig);
	}

	private void addModeFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "modeFromLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_LAYOUT);

		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig modeFrom = componentConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkPeriodConfigurationContants.MODE_FROM_COMPONENT, true, namspacePrefix + "modeFromLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		modeFrom.setAuthorizationIncluded(true);
		modeFrom.setComponentName("Mode");
		GtnUIFrameworkComboBoxConfig modeFromComboBoxConfig = componentConfigProvider.getComboBoxConfig("Mode",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		modeFromComboBoxConfig.setRequired(true);
		modeFrom.setGtnComboboxConfig(modeFromComboBoxConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW_MODE_FROM_TO_ACTION);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.MODE_FROM_COMPONENT);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.FREQUENCY_FROM_COMPONENT);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_TEXT_BOX);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DATE_FROM);

		actionConfigList.add(customAction);
		modeFrom.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(modeFrom);
	}

	private void addDefaultModeFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "defaultModeFrom", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_LAYOUT);

		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig defmodeFrom = componentConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_FROM_COMPONENT, true,
				namspacePrefix + "defaultModeFrom", GtnUIFrameworkComponentType.COMBOBOX);
		defmodeFrom.setAuthorizationIncluded(true);
		defmodeFrom.setComponentName("Default Mode");

		GtnUIFrameworkComboBoxConfig modeFromComboBoxConfig = componentConfigProvider.getComboBoxConfig("Mode",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		modeFromComboBoxConfig.setRequired(true);
		defmodeFrom.setGtnComboboxConfig(modeFromComboBoxConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkModeFromToAction.class.getName());
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_FROM_COMPONENT);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_FROM);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM_TEXT_BOX);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_FROM);

		actionConfigList.add(customAction);
		defmodeFrom.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(defmodeFrom);
	}

	private void addFrequencyFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "frequencyFromLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_LAYOUT);

		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig frequencyFrom = componentConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkPeriodConfigurationContants.FREQUENCY_FROM_COMPONENT, true,
				namspacePrefix + "frequencyFromLayout", GtnUIFrameworkComponentType.COMBOBOX);
		frequencyFrom.setAuthorizationIncluded(true);
		frequencyFrom.setComponentName(GtnFrameworkPeriodConfigurationContants.FREQUENCY);

		GtnUIFrameworkComboBoxConfig frequencyFromComboBoxConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkPeriodConfigurationContants.FREQUENCY, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequencyFromComboBoxConfig.setRequired(true);
		frequencyFrom.setGtnComboboxConfig(frequencyFromComboBoxConfig);
		componentList.add(frequencyFrom);
	}

	private void addDefaultFrequencyFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "defaultFrequencyFromLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_LAYOUT);

		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig defaultFrequencyComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_FROM, true,
						namspacePrefix + "defaultFrequencyFromLayout", GtnUIFrameworkComponentType.COMBOBOX);
		defaultFrequencyComponentConfig.setAuthorizationIncluded(true);
		defaultFrequencyComponentConfig.setComponentName("Default Frequency");

		GtnUIFrameworkComboBoxConfig defaultFrequencyFromComboBoxConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkPeriodConfigurationContants.FREQUENCY, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		defaultFrequencyFromComboBoxConfig.setRequired(true);
		defaultFrequencyComponentConfig.setGtnComboboxConfig(defaultFrequencyFromComboBoxConfig);
		componentList.add(defaultFrequencyComponentConfig);
	}

	private void addPeriodFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_COMPONENT_LAYOUT, true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_LAYOUT);

		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig addPeriodFromComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM, true,
						namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_COMPONENT_LAYOUT,
						GtnUIFrameworkComponentType.DATEFIELD);
		addPeriodFromComponentConfig.setAuthorizationIncluded(true);
		addPeriodFromComponentConfig.setComponentName(GtnFrameworkPeriodConfigurationContants.PERIOD);
		addPeriodFromComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionPeriodFrom = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodFieldValueChangeAction.class.getName());
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM);
		customActionPeriodFrom.addActionParameter(Boolean.TRUE);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DATE_FROM);
		customActionPeriodFrom.addActionParameter(Boolean.TRUE);
		actionConfigList.add(customActionPeriodFrom);
		GtnUIFrameworkDateFieldConfig datefieldConfig = componentConfigProvider.getDateFieldConfig(true, true,
				actionConfigList);
		datefieldConfig.setRequired(true);
		datefieldConfig.setImmediate(true);

		addPeriodFromComponentConfig.setGtnDateFieldConfig(datefieldConfig);

		componentList.add(addPeriodFromComponentConfig);
	}

	private void addDefaultPeriodFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM_LAYOUT, true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_LAYOUT);

		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig defaultPeriodFromComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM, true,
						namspacePrefix + GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM_LAYOUT,
						GtnUIFrameworkComponentType.DATEFIELD);
		defaultPeriodFromComponentConfig.setAuthorizationIncluded(true);
		defaultPeriodFromComponentConfig.setComponentName(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionPeriodFrom = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodFieldValueChangeAction.class.getName());
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM);
		customActionPeriodFrom.addActionParameter(Boolean.TRUE);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_FROM);
		customActionPeriodFrom.addActionParameter(Boolean.TRUE);
		actionConfigList.add(customActionPeriodFrom);
		GtnUIFrameworkDateFieldConfig datefieldConfig = componentConfigProvider.getDateFieldConfig(true, true,
				actionConfigList);
		datefieldConfig.setImmediate(true);
		datefieldConfig.setRequired(true);
		datefieldConfig.setValueChangeActionConfigList(actionConfigList);
		defaultPeriodFromComponentConfig.setGtnDateFieldConfig(datefieldConfig);

		componentList.add(defaultPeriodFromComponentConfig);

	}

	private void addPeriodFromTextbox(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig periodFromTextboxComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_TEXT_BOX, true,
						namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_COMPONENT_LAYOUT,
						GtnUIFrameworkComponentType.TEXTBOX);
		periodFromTextboxComponentConfig.setAuthorizationIncluded(true);
		periodFromTextboxComponentConfig.setComponentName(GtnFrameworkPeriodConfigurationContants.PERIOD);
		periodFromTextboxComponentConfig.setVisible(false);

		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfigProvider.getTextBoxConfig(true, true, true);
		periodFromTextboxComponentConfig.setGtnUIFrameworkValidationConfig(componentConfigProvider
				.getRegexOnlyValidationConfig(GtnFrameworkRegexStringConstants.ACCEPT_NEGATIVE_AND_ZERO_L7,
						GtnFrameworkPeriodConfigurationContants.NEGATIVE_ONLY_MSG));

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionPeriodFrom = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodFieldValueChangeAction.class.getName());
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_TEXT_BOX);
		customActionPeriodFrom.addActionParameter(Boolean.TRUE);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DATE_FROM);
		customActionPeriodFrom.addActionParameter(Boolean.FALSE);
		actionConfigList.add(customActionPeriodFrom);
		textboxConfig.setRequired(true);
		textboxConfig.setValueChangeActionConfigList(actionConfigList);
		periodFromTextboxComponentConfig.setGtnTextBoxConfig(textboxConfig);
		componentList.add(periodFromTextboxComponentConfig);

	}

	private void addDefaultPeriodFromTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig defaultPeriodFromTextBoxComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM_TEXT_BOX,
						true, namspacePrefix + GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM_LAYOUT,
						GtnUIFrameworkComponentType.TEXTBOX);
		defaultPeriodFromTextBoxComponentConfig.setAuthorizationIncluded(true);
		defaultPeriodFromTextBoxComponentConfig.setVisible(false);
		defaultPeriodFromTextBoxComponentConfig
				.setComponentName(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD);
		GtnUIFrameworkTextBoxConfig textboxConfig = componentConfigProvider.getTextBoxConfig(true, true, true);
		defaultPeriodFromTextBoxComponentConfig.setGtnUIFrameworkValidationConfig(componentConfigProvider
				.getRegexOnlyValidationConfig(GtnFrameworkRegexStringConstants.ACCEPT_NEGATIVE_AND_ZERO_L7,
						GtnFrameworkPeriodConfigurationContants.NEGATIVE_ONLY_MSG));

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionPeriodFrom = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodFieldValueChangeAction.class.getName());
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM_TEXT_BOX);
		customActionPeriodFrom.addActionParameter(Boolean.TRUE);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_FROM);
		customActionPeriodFrom.addActionParameter(Boolean.FALSE);
		actionConfigList.add(customActionPeriodFrom);
		textboxConfig.setValueChangeActionConfigList(actionConfigList);
		textboxConfig.setRequired(true);
		defaultPeriodFromTextBoxComponentConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(defaultPeriodFromTextBoxComponentConfig);

	}

	private void addDateFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "dateFromLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_LAYOUT);

		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig datefromComponentConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkPeriodConfigurationContants.DATE_FROM, true, namspacePrefix + "dateFromLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		datefromComponentConfig.setAuthorizationIncluded(true);
		datefromComponentConfig.setComponentName("Date");
		datefromComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.DATE_FROM);
		datefromComponentConfig.setEnable(false);
		componentList.add(datefromComponentConfig);
	}

	private void addDefaultDateFrom(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "defaultDateFromLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_LAYOUT);

		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig defaultDateFromComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_FROM, true,
						namspacePrefix + "defaultDateFromLayout", GtnUIFrameworkComponentType.TEXTBOX);
		defaultDateFromComponentConfig.setAuthorizationIncluded(true);

		defaultDateFromComponentConfig.setEnable(false);
		defaultDateFromComponentConfig.setComponentName("Default Date");
		defaultDateFromComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_FROM);

		componentList.add(defaultDateFromComponentConfig);

	}

	private void addModeTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "modeToLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_LAYOUT);

		componentList.add(gtnCssLayout);
		GtnUIFrameworkComponentConfig modeToComponentConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkPeriodConfigurationContants.MODE_TO, true, namspacePrefix + "modeToLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		modeToComponentConfig.setAuthorizationIncluded(true);
		modeToComponentConfig.setComponentName("Mode");
		modeToComponentConfig.setEnable(false);

		GtnUIFrameworkComboBoxConfig modeToComboBoxConfig = componentConfigProvider.getComboBoxConfig("Mode",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		modeToComboBoxConfig.setRequired(true);
		modeToComponentConfig.setGtnComboboxConfig(modeToComboBoxConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW_MODE_FROM_TO_ACTION);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.MODE_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.FREQUENCY_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_TO_TEXT_BOX);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DATE_TO);
		actionConfigList.add(customAction);
		modeToComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(modeToComponentConfig);

	}

	private void addDefaultModeTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "defaultModeToLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_LAYOUT);

		componentList.add(gtnCssLayout);
		GtnUIFrameworkComponentConfig defaultModeToComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_TO, true,
						namspacePrefix + "defaultModeToLayout", GtnUIFrameworkComponentType.COMBOBOX);
		defaultModeToComponentConfig.setAuthorizationIncluded(true);
		defaultModeToComponentConfig.setComponentName("Default Mode");
		defaultModeToComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_TO);
		defaultModeToComponentConfig.setEnable(false);
		componentList.add(defaultModeToComponentConfig);

		GtnUIFrameworkComboBoxConfig defaultModeComboBoxConfig = componentConfigProvider.getComboBoxConfig("Mode",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		defaultModeComboBoxConfig.setRequired(true);
		defaultModeToComponentConfig.setGtnComboboxConfig(defaultModeComboBoxConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW_MODE_FROM_TO_ACTION);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_TEXT_BOX);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_TO);
		actionConfigList.add(customAction);
		defaultModeToComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addFrequencyTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "frequencyToLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_LAYOUT);
		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig frequencyToComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.FREQUENCY_TO, true,
						namspacePrefix + "frequencyToLayout", GtnUIFrameworkComponentType.COMBOBOX);
		frequencyToComponentConfig.setAuthorizationIncluded(true);
		frequencyToComponentConfig.setComponentName(GtnFrameworkPeriodConfigurationContants.FREQUENCY);
		frequencyToComponentConfig.setEnable(false);
		componentList.add(frequencyToComponentConfig);
		GtnUIFrameworkComboBoxConfig frequencyComboBoxConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkPeriodConfigurationContants.FREQUENCY, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		frequencyComboBoxConfig.setRequired(true);
		frequencyToComponentConfig.setGtnComboboxConfig(frequencyComboBoxConfig);
	}

	private void addDefaultFrequencyTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "defaultFrequencyToLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_LAYOUT);
		componentList.add(gtnCssLayout);
		GtnUIFrameworkComponentConfig defaultFrequencyToComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_TO, true,
						namspacePrefix + "defaultFrequencyToLayout", GtnUIFrameworkComponentType.COMBOBOX);
		defaultFrequencyToComponentConfig.setAuthorizationIncluded(true);
		defaultFrequencyToComponentConfig.setComponentName("Default Frequency");
		defaultFrequencyToComponentConfig.setEnable(false);

		componentList.add(defaultFrequencyToComponentConfig);
		GtnUIFrameworkComboBoxConfig defaultFrequencyComboBoxConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkPeriodConfigurationContants.FREQUENCY, GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		defaultFrequencyComboBoxConfig.setRequired(true);

		defaultFrequencyToComponentConfig.setGtnComboboxConfig(defaultFrequencyComboBoxConfig);

	}

	private void addPeriodTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_COMPONENT_LAYOUT, true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_LAYOUT);
		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig periodToComponentConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				GtnFrameworkPeriodConfigurationContants.PERIOD_TO, true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_COMPONENT_LAYOUT,
				GtnUIFrameworkComponentType.DATEFIELD);
		periodToComponentConfig.setComponentName(GtnFrameworkPeriodConfigurationContants.PERIOD);
		periodToComponentConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionPeriodFrom = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodFieldValueChangeAction.class.getName());
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_TO);
		customActionPeriodFrom.addActionParameter(Boolean.FALSE);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DATE_TO);
		customActionPeriodFrom.addActionParameter(Boolean.TRUE);

		actionConfigList.add(customActionPeriodFrom);
		GtnUIFrameworkDateFieldConfig datefieldConfig = componentConfigProvider.getDateFieldConfig(false, true,
				actionConfigList);
		datefieldConfig.setImmediate(true);
		datefieldConfig.setRequired(true);
		datefieldConfig.setValueChangeActionConfigList(actionConfigList);
		periodToComponentConfig.setGtnDateFieldConfig(datefieldConfig);

		componentList.add(periodToComponentConfig);
	}

	private void addDefaultPeriodTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_LAYOUT, true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_LAYOUT);
		componentList.add(gtnCssLayout);
		GtnUIFrameworkComponentConfig defaultPeriodToComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO, true,
						namspacePrefix + GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_LAYOUT,
						GtnUIFrameworkComponentType.DATEFIELD);
		defaultPeriodToComponentConfig.setAuthorizationIncluded(true);
		defaultPeriodToComponentConfig.setComponentName(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD);
		defaultPeriodToComponentConfig.setEnable(false);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionPeriodFrom = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodFieldValueChangeAction.class.getName());
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO);
		customActionPeriodFrom.addActionParameter(Boolean.FALSE);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_TO);
		customActionPeriodFrom.addActionParameter(Boolean.TRUE);

		actionConfigList.add(customActionPeriodFrom);
		GtnUIFrameworkDateFieldConfig defaultDatefieldConfig = componentConfigProvider.getDateFieldConfig(false, true,
				actionConfigList);
		defaultDatefieldConfig.setImmediate(true);
		defaultDatefieldConfig.setRequired(true);
		defaultPeriodToComponentConfig.setGtnDateFieldConfig(defaultDatefieldConfig);
		componentList.add(defaultPeriodToComponentConfig);

	}

	private void addPeriodToTextbox(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig periodToTextBoxComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.PERIOD_TO_TEXT_BOX, true,
						namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_COMPONENT_LAYOUT,
						GtnUIFrameworkComponentType.TEXTBOX);
		periodToTextBoxComponentConfig.setAuthorizationIncluded(true);
		periodToTextBoxComponentConfig.setComponentName(GtnFrameworkPeriodConfigurationContants.PERIOD);
		periodToTextBoxComponentConfig.setVisible(false);
		periodToTextBoxComponentConfig.setGtnUIFrameworkValidationConfig(componentConfigProvider
				.getRegexOnlyValidationConfig(GtnFrameworkRegexStringConstants.ACCEPT_POS_AND_ZERO_AND_NEGA,
						GtnFrameworkPeriodConfigurationContants.POSITIVE_ONLY_MSG));

		GtnUIFrameworkTextBoxConfig periodTextboxConfig = componentConfigProvider.getTextBoxConfig(false, true, true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionPeriodFrom = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodFieldValueChangeAction.class.getName());
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_TO_TEXT_BOX);
		customActionPeriodFrom.addActionParameter(Boolean.FALSE);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DATE_TO);
		customActionPeriodFrom.addActionParameter(Boolean.FALSE);
		actionConfigList.add(customActionPeriodFrom);

		periodTextboxConfig.setValueChangeActionConfigList(actionConfigList);
		periodTextboxConfig.setRequired(true);
		periodToTextBoxComponentConfig.setGtnTextBoxConfig(periodTextboxConfig);

		componentList.add(periodToTextBoxComponentConfig);

	}

	private void addDefaultPeriodToTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {

		GtnUIFrameworkComponentConfig defaultPeriodToComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_TEXT_BOX, true,
						namspacePrefix + GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_LAYOUT,
						GtnUIFrameworkComponentType.TEXTBOX);
		defaultPeriodToComponentConfig.setAuthorizationIncluded(true);
		defaultPeriodToComponentConfig.setVisible(false);
		defaultPeriodToComponentConfig
				.setComponentId(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_TEXT_BOX);
		defaultPeriodToComponentConfig.setComponentName(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD);

		GtnUIFrameworkTextBoxConfig defaultPeriodTextboxConfig = componentConfigProvider.getTextBoxConfig(false, true,
				true);
		defaultPeriodToComponentConfig.setGtnUIFrameworkValidationConfig(componentConfigProvider
				.getRegexOnlyValidationConfig(GtnFrameworkRegexStringConstants.ACCEPT_POS_AND_ZERO_AND_NEGA,
						GtnFrameworkPeriodConfigurationContants.POSITIVE_ONLY_MSG));

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionPeriodFrom = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodFieldValueChangeAction.class.getName());
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_TEXT_BOX);
		customActionPeriodFrom.addActionParameter(Boolean.FALSE);
		customActionPeriodFrom.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_TO);
		customActionPeriodFrom.addActionParameter(Boolean.FALSE);
		actionConfigList.add(customActionPeriodFrom);
		defaultPeriodTextboxConfig.setRequired(true);
		defaultPeriodTextboxConfig.setValueChangeActionConfigList(actionConfigList);
		defaultPeriodToComponentConfig.setGtnTextBoxConfig(defaultPeriodTextboxConfig);

		componentList.add(defaultPeriodToComponentConfig);

	}

	private void addDateTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "dateToLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_LAYOUT);
		componentList.add(gtnCssLayout);

		GtnUIFrameworkComponentConfig dateToTextBoxComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DATE_TO, true,
						namspacePrefix + "dateToLayout", GtnUIFrameworkComponentType.TEXTBOX);
		dateToTextBoxComponentConfig.setAuthorizationIncluded(true);
		dateToTextBoxComponentConfig.setEnable(false);
		dateToTextBoxComponentConfig.setComponentName("Date");
		dateToTextBoxComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.DATE_TO);
		componentList.add(dateToTextBoxComponentConfig);

	}

	private void addDefaultDateTo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig gtnCssLayout = componentConfigProvider.getHorizontalLayoutConfig(
				namspacePrefix + "defaultDateToLayout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.PERIOD_TO_LAYOUT);
		componentList.add(gtnCssLayout);
		GtnUIFrameworkComponentConfig defaultDateToTextBoxComponentConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_TO, true,
						namspacePrefix + "defaultDateToLayout", GtnUIFrameworkComponentType.TEXTBOX);
		defaultDateToTextBoxComponentConfig.setAuthorizationIncluded(true);
		defaultDateToTextBoxComponentConfig.setEnable(false);
		defaultDateToTextBoxComponentConfig.setComponentName("Default Date");
		defaultDateToTextBoxComponentConfig.setComponentId(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_TO);
		componentList.add(defaultDateToTextBoxComponentConfig);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
                GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnSave01Layout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSave", true,
				namspacePrefix + "gtnSave01Layout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Save");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_VALUE_VALIDATION_ACTION);

		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.MODULE);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.BUSINESS_PROCESS);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.COMPANY);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.BUSINESS_UNIT);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW);

		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.MODE_FROM_COMPONENT);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_FROM_COMPONENT);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.FREQUENCY_FROM_COMPONENT);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_FROM);

		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_TEXT_BOX);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM_TEXT_BOX);

		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DATE_FROM);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_FROM);

		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.MODE_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.FREQUENCY_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.PERIOD_TO_TEXT_BOX);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_TEXT_BOX);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DATE_TO);
		customAction.addActionParameter(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_TO);
		actionConfigList.add(customAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
                GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnReset01Layout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig resetButtonConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true,
				namspacePrefix + "gtnReset01Layout",
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("Reset");
		componentList.add(resetButtonConfig);

		List<String> resetFieldList = new ArrayList<>(25);

		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.MODULE);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.BUSINESS_PROCESS);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.COMPANY);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.BUSINESS_UNIT);

		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW);

		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.MODE_FROM_COMPONENT);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_FROM_COMPONENT);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.FREQUENCY_FROM_COMPONENT);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_FROM);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.PERIOD_FROM_TEXT_BOX);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_FROM_TEXT_BOX);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DATE_FROM);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_FROM);

		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.MODE_TO);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_MODE_TO);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.FREQUENCY_TO);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_FREQUENCY_TO);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.PERIOD_TO);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.PERIOD_TO_TEXT_BOX);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_PERIOD_TO_TEXT_BOX);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DATE_TO);
		resetFieldList.add(GtnFrameworkPeriodConfigurationContants.DEFAULT_DATE_TO);

		List<Object> resetValueList = new ArrayList<>(25);

		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);

		resetValueList.add(GtnFrameworkPeriodConfigurationContants.SINGLE);

		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);

		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetValueList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetValueList.add(null);
		resetValueList.add(null);

		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(null);

		resetValueList.add(null);
		resetValueList.add(null);
		resetValueList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetValueList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetValueList.add(null);
		resetValueList.add(null);

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		resetActionConfig.addActionParameter("Are you sure you want to reset the page to default/previous values ?");
		resetActionConfig.addActionParameter(resetFieldList);
		resetActionConfig.addActionParameter(resetValueList);
		resetButtonConfig.addGtnUIFrameWorkActionConfig(resetActionConfig);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig searchResultConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "loadResultTable", true, namspacePrefix + "resultMainlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setResetToDefaultAllowed(false);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig loadResults = componentConfigProvider.getPagedTableConfig(true, true,
				GtnWsPeriodConfigurationConstants.PERIOD_CONFIG_ONSCREEN_LOAD_SAVE_COUNT
						+ GtnWsPeriodConfigurationConstants.PERIOD_CONFIG_ONSCREEN_LOAD_COUNT,
				GtnWsPeriodConfigurationConstants.PERIOD_CONFIG_ONSCREEN_LOAD_SAVE_COUNT
						+ GtnWsPeriodConfigurationConstants.PERIOD_CONFIG_ONSCREEN_LOAD_COUNT,
				"periodConfiguration", "SearchQuery");
		loadResults.setEditable(false);
		loadResults.setPageLength(10);
		loadResults.setItemPerPage(10);
		loadResults.setSinkItemPerPageWithPageLength(false);
		HashMap<Object, Object[]> doubleHeaderMap = new HashMap<>();
		loadResults.setTableVisibleHeader(GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationTableHeader());
		loadResults
				.setTableColumnMappingId(GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationTableColumns());
		loadResults.setTableColumnDataType(
				GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationTableColumnType());
		loadResults.setDoubleHeaderVisible(true);
		loadResults.setTableDoubleHeaderVisibleColumns(
				GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationDoubleHeaderVisibleColumns());
		loadResults.setTableDoubleHeaderVisibleHeaders(
				GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationDoubleHeaderVisibleHeader());

		doubleHeaderMap.put("group1", GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationGroup1());

		doubleHeaderMap.put("group2", GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationGroup2());

		doubleHeaderMap.put("group3", GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationGroup3());

		loadResults.setTableDoubleHeaderMap(doubleHeaderMap);
		loadResults.setDoubleHeaderObject(new Object[] { "group1", "group2", "group3" });
		loadResults.setDoubleHeaderColumnAlignment(new String[] { GtnFrameworkCommonStringConstants.CENTER,
				GtnFrameworkCommonStringConstants.CENTER, GtnFrameworkCommonStringConstants.CENTER });

		loadResults.setEditableColumnList(
				Arrays.asList(GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationTableFieldFactory()));
		loadResults.setEditable(true);
		loadResults.setEditableField(createTableFieldFactoryComponents(loadResults.getEditableColumnList()));

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(searchResultConfig.getComponentId());
		loadResults.addPostCreationActionConfig(loadDataTableActionConfig);
		searchResultConfig.setGtnPagedTableConfig(loadResults);

	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType = Arrays
					.asList(GtnFrameworkPeriodConfigurationContants.getPeriodConfigurationTableFieldFactory())
					.contains(propertyId) ? GtnUIFrameworkComponentType.DATEFIELD : GtnUIFrameworkComponentType.TEXTBOX;

			GtnUIFrameworkComponentConfig periodTableFieldConfig = new GtnUIFrameworkComponentConfig();
			periodTableFieldConfig.setComponentType(gtnUIFrameworkComponentType);
			periodTableFieldConfig.setEnable(false);
			editableFields.add(periodTableFieldConfig);
		}
		return editableFields;
	}

	public void addExcelButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			GtnFrameworkComponentConfigProvider componentConfigProvider) {
                GtnUIFrameworkComponentConfig gtnLayout = componentConfigProvider.getHorizontalLayoutConfig(namspacePrefix + "gtnExcel01Layout", true,
				namspacePrefix + GtnFrameworkPeriodConfigurationContants.ACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig periodConfigExcelButtonLayout = componentConfigProvider.getCssLayoutConfig(
				namspacePrefix + "excelButtonlayout", true,
				namspacePrefix + "gtnExcel01Layout");
		periodConfigExcelButtonLayout.setAddToParent(true);
		periodConfigExcelButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(periodConfigExcelButtonLayout);
		addPeriodConfigExcelButtonComponent(componentList, namspacePrefix, componentConfigProvider);
	}

	private void addPeriodConfigExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, GtnFrameworkComponentConfigProvider componentConfigProvider) {
		GtnUIFrameworkComponentConfig periodConfigExcelBtn = componentConfigProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnExcelButton", true, namspacePrefix + "excelButtonlayout",
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		periodConfigExcelBtn.setAuthorizationIncluded(true);
		componentList.add(periodConfigExcelBtn);
		GtnUIFrameworkExcelButtonConfig periodConfigExcelButtonConfig = componentConfigProvider
				.getExcelBtnconfig("Period Configuration", true, namspacePrefix + "loadResultTable", false);
		periodConfigExcelBtn.setGtnUIFrameworkExcelButtonConfig(periodConfigExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(periodConfigExcelButtonConfig);
		periodConfigExcelBtn.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}
}
