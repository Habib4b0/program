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

public class GtnUIFrameworkIntervalFrequencyValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnUIFrameworkIntervalFrequencyValueChangeAction.class);

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
			int frequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
					.getIntegerFromField();
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(2).toString());
			if (baseComponent.isValidFieldValue() && frequency != 0) {
				String interval = baseComponent.getStringFromField();
				GtnWsForecastConfigurationRequest forecastConfigurationRequest = new GtnWsForecastConfigurationRequest();
				gtnLogger.info("parameters.size()------------------->>>>" + parameters.size());
				if (parameters.size() > 4) {
					interval = "";
				}
				forecastConfigurationRequest.setFutureFrequency(frequency);
				forecastConfigurationRequest.setFutureInterval(interval);

				GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
				request.setForecastConfigurationRequest(forecastConfigurationRequest);

				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
								+ GtnWsForecastConfigurationConstants.FUTURE_FREQUENCY_VALUE_CHANGE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				String year = response.getGtnWsForecastConfigurationResponse().getForecastPeriod();
				gtnLogger.info("year------------------->>>>" + year);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString()).setPropertyValue(year);
				if (response.getGtnWsForecastConfigurationResponse().isErrorMessage()) {
					GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
							GtnFrameworkCommonStringConstants.ERROR,
							response.getGtnWsForecastConfigurationResponse().getMessage());
				}
			}
		} catch (Exception exception) {
			gtnLogger.error(exception.getMessage(), exception);
			throw new GtnFrameworkGeneralException("IntervalFrequencyValueChangeAction Error", exception);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
