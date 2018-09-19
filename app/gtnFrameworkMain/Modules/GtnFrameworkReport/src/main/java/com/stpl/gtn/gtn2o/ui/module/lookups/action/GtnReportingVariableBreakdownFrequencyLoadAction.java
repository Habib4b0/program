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
		logger.debug("Inside GtnReportingVariableBreakdownFrequencyLoadAction Configure Parameters");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> frequencyLoadActionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

		String frequencyLoadFrequencyId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(frequencyLoadActionParameterList.get(1).toString())
				.getCaptionFromV8ComboBox();
		String frequencyLoadFrequencyCaption = null;
		GtnWsReportDataSelectionBean frequencyLoadDataSelectionBean = null;

		if ("reportingDashboardScreen".equals(frequencyLoadActionParameterList.get(3).toString())) {
			GtnUIFrameworkBaseComponent reportingDashboardFrequencyComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId);

			String reportingDashboardFrequency = reportingDashboardFrequencyComponent.getCaptionFromV8ComboBox();
			frequencyLoadFrequencyCaption = reportingDashboardFrequencyComponent.getStringCaptionFromV8ComboBox();
			String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
			frequencyLoadDataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();

			if (!"0".equals(reportingDashboardFrequency)) {
				frequencyLoadFrequencyId = reportingDashboardFrequency;
			}
			GtnUIFrameworkComboBoxConfig rdRelationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromView(frequencyLoadActionParameterList.get(2).toString(), componentId)
					.getComponentConfig().getGtnComboboxConfig();
			rdRelationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			rdRelationComboboxConfig.setComboBoxType(GtnFrameworkReportStringConstants.REPORT_CONFIG_FREQUENCY);
			rdRelationComboboxConfig.setIsLoadAtStart(true);
			List<GtnUIFrameWorkActionConfig> rdFrequencyActionConfigList = new ArrayList<>();

			GtnUIFrameWorkActionConfig rdVariableBreakdownFrequencyLoadActionConfig = new GtnUIFrameWorkActionConfig();
			rdVariableBreakdownFrequencyLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			rdVariableBreakdownFrequencyLoadActionConfig
					.addActionParameter(GtnReportingVariableBreakdownGridLoadAction.class.getName());
			rdVariableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_RESULTS_LAYOUT_PAGED_TABLE_COMPONENT);
			rdVariableBreakdownFrequencyLoadActionConfig
					.addActionParameter("reportingDashboardTab_reportOptionsTabVariableBreakdown");
			rdVariableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FILE_OR_PROJECTION);
			rdVariableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FREQUENCY_CONFIG);
			rdVariableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FREQUENCY_CONFIG);
			rdVariableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_START_PERIOD);
			rdVariableBreakdownFrequencyLoadActionConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_END_PERIOD);
			rdVariableBreakdownFrequencyLoadActionConfig.addActionParameter(componentId);
			rdVariableBreakdownFrequencyLoadActionConfig.addActionParameter(frequencyLoadDataSelectionBean.getReportDataSource());
			rdFrequencyActionConfigList.add(rdVariableBreakdownFrequencyLoadActionConfig);

			GtnUIFrameWorkActionConfig variableBreakDownHistoryLoadConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			variableBreakDownHistoryLoadConfig
					.addActionParameter(GtnReportingVariableBreakdownHistoryLoadAction.class.getName());
			variableBreakDownHistoryLoadConfig.addActionParameter("reportLandingScreen_fromPeriod");
			variableBreakDownHistoryLoadConfig.addActionParameter(
					GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FREQUENCY_CONFIG);
			variableBreakDownHistoryLoadConfig.addActionParameter("reportOptionsTab_variableBreakdownHistoryConfig");
			if (frequencyLoadFrequencyCaption != null && frequencyLoadDataSelectionBean.getCustomDataList() != null
					&& frequencyLoadFrequencyCaption.equals(String.valueOf(frequencyLoadDataSelectionBean.getCustomDataList().get(0)))) {
				variableBreakDownHistoryLoadConfig
						.addActionParameter(String.valueOf(frequencyLoadDataSelectionBean.getCustomDataList().get(1)));
			}
			rdFrequencyActionConfigList.add(variableBreakDownHistoryLoadConfig);

			GtnUIFrameworkComponentConfig componentConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponentFromView(frequencyLoadActionParameterList.get(2).toString(), componentId)
					.getComponentConfig();
			componentConfig.setGtnUIFrameWorkActionConfigList(rdFrequencyActionConfigList);
			GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
			combobox.reloadComponentFromView(frequencyLoadActionParameterList.get(2).toString(), componentId, Arrays.asList(""));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromView(frequencyLoadActionParameterList.get(2).toString(), componentId)
					.loadV8ComboBoxComponentValue(Integer.valueOf(frequencyLoadFrequencyId));
		} else {
			GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(frequencyLoadActionParameterList.get(2).toString(), componentId).getComponentConfig()
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
					.getVaadinBaseComponent(frequencyLoadActionParameterList.get(2).toString(), componentId).getComponentConfig();
			componentConfig.setGtnUIFrameWorkActionConfigList(frequencyActionConfigList);
			GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
			combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					frequencyLoadActionParameterList.get(2).toString(), componentId, Arrays.asList(""));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(frequencyLoadActionParameterList.get(2).toString(), componentId)
					.loadV8ComboBoxComponentValue(Integer.valueOf(frequencyLoadFrequencyId));
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}