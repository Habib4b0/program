/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkConfirmSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		saveForecastConfiguration(componentId, (GtnUIFrameworkWebserviceRequest) parameters.get(1),
				parameters.get(2).toString());
	}

	private void saveForecastConfiguration(String componentId, GtnUIFrameworkWebserviceRequest request, String tableId)
			throws GtnFrameworkGeneralException {
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse newResponse = getResponse(request, wsclient);
		GtnWsForecastConfigurationResponse fcNewResponse = newResponse.getGtnWsForecastConfigurationResponse();
		if (fcNewResponse.isSuccess()) {
			loadGrid(componentId, tableId);
			GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
			notificationActionConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			notificationActionConfig.addActionParameter(fcNewResponse.getMessage());
			notificationActionConfig.addActionParameter(null);
			notificationActionConfig.addActionParameter(-1);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationActionConfig);
		}
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request,
			final GtnUIFrameworkWebServiceClient wsclient) {
		return wsclient.callGtnWebServiceUrl(
		GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE 
		+ GtnWsForecastConfigurationConstants.SAVE_FORECAST_CONF,
		request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	private void loadGrid(String componentId, String tableId) throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentConfig tableComponentConfig = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId)
				.getComponentConfig();
		GtnUIFrameWorkActionConfig actionConfig = tableComponentConfig.getGtnPagedTableConfig()
				.getPostCreationActionConfigList().get(0);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, actionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
