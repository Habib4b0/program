/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkSaveAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		saveForecastConfiguration(componentId, parameters);
	}

	public GtnWsForecastConfigurationRequest getModifiedRequest(GtnWsForecastConfigurationRequest fcRequest,
			final List<Object> parameters) throws GtnFrameworkValidationFailedException {
		fcRequest.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
		int futureFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
				.getIntegerFromField();
		String futureInterval = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
				.getStringFromField();
		int historyFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString())
				.getIntegerFromField();
		String historyInterval = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString())
				.getStringFromField();
		Date toDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString())
				.getDateFromDateField();
		Date fromDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString())
				.getDateFromDateField();
		int businessProcess = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(7).toString())
				.getIntegerFromField();
		String processType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(8).toString())
				.getStringFromField();
		String mode = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(9).toString()).getStringFromField();

		fcRequest.setBusinessProcess(businessProcess);
		fcRequest.setFromDate(fromDate);
		fcRequest.setToDate(toDate);
		fcRequest.setMode(mode);
		fcRequest.setProcessType(processType);
		fcRequest.setHistoryFrequency(historyFrequency);
		fcRequest.setHistoryInterval(historyInterval);
		fcRequest.setFutureFrequency(futureFrequency);
		fcRequest.setFutureInterval(futureInterval);
		return fcRequest;
	}

	private void saveForecastConfiguration(String componentId, List<Object> parameters)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastConfigurationRequest fcRequest = new GtnWsForecastConfigurationRequest();
		getModifiedRequest(fcRequest, parameters);
		request.setForecastConfigurationRequest(fcRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
						+ GtnWsForecastConfigurationConstants.CHECK_SAVE_FORECAST_CONF,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsForecastConfigurationResponse fcResponse = response.getGtnWsForecastConfigurationResponse();
		GtnUIFrameworkBaseComponent errorLabelBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(11).toString());
		errorLabelBaseComponent.setVisible(false);
		if (fcResponse.isSuccess()) {
			confirmSaveForecastConfiguration(componentId, request, parameters.get(10).toString());
			return;
		}
		if (GtnFrameworkCommonStringConstants.VALIDATION.equals(fcResponse.getMessageType())) {
			errorLabelBaseComponent.setVisible(true);
			errorLabelBaseComponent.setPropertyValue(fcResponse.getMessage());
			return;
		}
		GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
				fcResponse.getMessageType(), fcResponse.getMessage());
	}

	private void confirmSaveForecastConfiguration(String componentId, GtnUIFrameworkWebserviceRequest request,
			String tableId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig();
		saveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkConfirmSaveAction.class.getName());
		saveActionConfig.addActionParameter(request);
		saveActionConfig.addActionParameter(tableId);
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmActionConfig.addActionParameter("Save Confirmation");
		confirmActionConfig.addActionParameter("Save record ?");

		confirmActionConfig.addActionParameter(Arrays.asList(saveActionConfig));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
