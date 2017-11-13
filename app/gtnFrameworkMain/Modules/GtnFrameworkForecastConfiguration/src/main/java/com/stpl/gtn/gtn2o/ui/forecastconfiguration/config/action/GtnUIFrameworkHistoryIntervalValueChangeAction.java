package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkHistoryIntervalValueChangeAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnUIFrameworkHistoryIntervalValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
			if (baseComponent.isValidFieldValue()) {
				String interval = baseComponent.getStringFromField();
				int frequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
						.getIntegerFromField();
				GtnWsForecastConfigurationRequest forecastConfigurationRequest = new GtnWsForecastConfigurationRequest();
				forecastConfigurationRequest.setHistoryFrequency(frequency);
				forecastConfigurationRequest.setHistoryInterval(interval);

				GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
				request.setForecastConfigurationRequest(forecastConfigurationRequest);

				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
								+ GtnWsForecastConfigurationConstants.HISTORY_INTERVAL_VALUE_CHANGE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
						.setPropertyValue(response.getGtnWsForecastConfigurationResponse().getHistoryPeriod());
				if (response.getGtnWsForecastConfigurationResponse().isErrorMessage()) {
					baseComponent.setPropertyValue(response.getGtnWsForecastConfigurationResponse().getHistoryPeriod());
					GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
							GtnFrameworkCommonStringConstants.ERROR,
							response.getGtnWsForecastConfigurationResponse().getMessage());
				}
			}
		} catch (Exception exception) {
			gtnLogger.error(exception.getMessage(), exception);
			throw new GtnFrameworkGeneralException("HistoryIntervalValueChange Error", exception);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
