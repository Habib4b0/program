/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownFrequencyLoadAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownFrequencyLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside Configure Parameters");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

		String frequencyId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(1).toString())
				.getCaptionFromV8ComboBox();
		String frequencyCaption = null;
		GtnWsReportDataSelectionBean dataSelectionBean = null;

		if ("reportingDashboardScreen".equals(actionParameterList.get(3).toString())) {
			GtnUIFrameworkBaseComponent reportingDashboardFrequencyComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId);

			String reportingDashboardFrequency = reportingDashboardFrequencyComponent.getCaptionFromV8ComboBox();
			frequencyCaption = reportingDashboardFrequencyComponent.getStringCaptionFromV8ComboBox();
			String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
			dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();

			if (!"0".equals(reportingDashboardFrequency)) {
				frequencyId = reportingDashboardFrequency;
			}
			GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromView(actionParameterList.get(2).toString(), componentId)
					.getComponentConfig().getGtnComboboxConfig();
			relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			relationComboboxConfig.setComboBoxType(GtnFrameworkReportStringConstants.REPORT_CONFIG_FREQUENCY);
			relationComboboxConfig.setIsLoadAtStart(true);
			List<GtnUIFrameWorkActionConfig> frequencyActionConfigList = new ArrayList<>();

			GtnUIFrameWorkActionConfig variableBreakdownFrequencyLoadActionConfig = new GtnUIFrameWorkActionConfig();
			variableBreakdownFrequencyLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			variableBreakdownFrequencyLoadActionConfig
					.addActionParameter(GtnReportingVariableBreakdownGridLoadAction.class.getName());
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_RESULTS_LAYOUT_PAGED_TABLE_COMPONENT);
			variableBreakdownFrequencyLoadActionConfig
					.addActionParameter("reportingDashboardTab_reportOptionsTabVariableBreakdown");
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FILE_OR_PROJECTION);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FREQUENCY_CONFIG);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FREQUENCY_CONFIG);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_START_PERIOD);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_END_PERIOD);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(componentId);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(dataSelectionBean.getReportDataSource());
			frequencyActionConfigList.add(variableBreakdownFrequencyLoadActionConfig);

			GtnUIFrameWorkActionConfig variableBreakDownHistoryLoadConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			variableBreakDownHistoryLoadConfig
					.addActionParameter(GtnReportingVariableBreakdownHistoryLoadAction.class.getName());
			variableBreakDownHistoryLoadConfig.addActionParameter("reportLandingScreen_fromPeriod");
			variableBreakDownHistoryLoadConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FREQUENCY_CONFIG);
			variableBreakDownHistoryLoadConfig.addActionParameter("reportOptionsTab_variableBreakdownHistoryConfig");
			if (frequencyCaption != null && dataSelectionBean.getCustomDataList() != null
					&& frequencyCaption.equals(String.valueOf(dataSelectionBean.getCustomDataList().get(0)))) {
				variableBreakDownHistoryLoadConfig
						.addActionParameter(String.valueOf(dataSelectionBean.getCustomDataList().get(1)));
			}
			frequencyActionConfigList.add(variableBreakDownHistoryLoadConfig);

			GtnUIFrameworkComponentConfig componentConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromView(actionParameterList.get(2).toString(), componentId)
					.getComponentConfig();
			componentConfig.setGtnUIFrameWorkActionConfigList(frequencyActionConfigList);
			GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
			combobox.reloadComponentFromView(actionParameterList.get(2).toString(), componentId, Arrays.asList(""));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromView(actionParameterList.get(2).toString(), componentId)
					.loadV8ComboBoxComponentValue(Integer.valueOf(frequencyId));
		} else {
			GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId).getComponentConfig()
					.getGtnComboboxConfig();
			relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			relationComboboxConfig.setComboBoxType(GtnFrameworkReportStringConstants.REPORT_CONFIG_FREQUENCY);
			relationComboboxConfig.setIsLoadAtStart(true);
			List<GtnUIFrameWorkActionConfig> frequencyActionConfigList = new ArrayList<>();

			GtnUIFrameWorkActionConfig variableBreakdownFrequencyLoadActionConfig = new GtnUIFrameWorkActionConfig();
			variableBreakdownFrequencyLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			variableBreakdownFrequencyLoadActionConfig
					.addActionParameter(GtnReportingVariableBreakdownGridLoadAction.class.getName());
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_RESULTS_LAYOUT_PAGED_TABLE_COMPONENT);
			variableBreakdownFrequencyLoadActionConfig
					.addActionParameter("reportLandingScreen_reportingDashboardComparisonConfig");
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FILE_OR_PROJECTION);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FREQUENCY_CONFIG);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FREQUENCY_CONFIG);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_START_PERIOD);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_END_PERIOD);
			variableBreakdownFrequencyLoadActionConfig.addActionParameter("");
			variableBreakdownFrequencyLoadActionConfig
					.addActionParameter("reportLandingScreen" + GtnFrameworkReportStringConstants.UNDERSCORE
							+ GtnFrameworkReportStringConstants.REPORT_DATA_SOURCE);

			GtnUIFrameWorkActionConfig variableBreakDownHistoryLoadConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			variableBreakDownHistoryLoadConfig
					.addActionParameter(GtnReportingVariableBreakdownHistoryLoadAction.class.getName());
			variableBreakDownHistoryLoadConfig.addActionParameter("reportLandingScreen_fromPeriod");
			variableBreakDownHistoryLoadConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FREQUENCY_CONFIG);
			variableBreakDownHistoryLoadConfig.addActionParameter("reportOptionsTab_variableBreakdownHistoryConfig");

			frequencyActionConfigList.add(variableBreakdownFrequencyLoadActionConfig);
			frequencyActionConfigList.add(variableBreakDownHistoryLoadConfig);
			GtnUIFrameworkComponentConfig componentConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId).getComponentConfig();
			componentConfig.setGtnUIFrameWorkActionConfigList(frequencyActionConfigList);
			GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
			combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					actionParameterList.get(2).toString(), componentId, Arrays.asList(""));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId)
					.loadV8ComboBoxComponentValue(Integer.valueOf(frequencyId));
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}