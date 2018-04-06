package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkModeValueChangeAction implements GtnUIFrameWorkAction,GtnUIFrameworkDynamicClass {
    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkModeValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
		if (!value.isEmpty()) {
			boolean visibility = GtnWsForecastConfigurationConstants.MODE_VALUE_INTERVAL.equals(value);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
					.setComponentVisible(!visibility);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
					.setComponentVisible(!visibility);
                        int futureeFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("FCView_intervalFrequency")
					.getIntegerFromField();
			GtnUIFrameworkBaseComponent baseComponentInterval = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("FCView_futureInterval");
                        if("Interval".equals(value)){                        
			if (baseComponentInterval.isValidFieldValue()) {
                            gtnLogger.info("frequency---------------"+futureeFrequency);                            
				String futureInterval = baseComponentInterval.getStringFromField();
                                gtnLogger.info("interval-------------------"+futureInterval);
				GtnWsForecastConfigurationRequest forecastConfigRequest = new GtnWsForecastConfigurationRequest();
				forecastConfigRequest.setFutureFrequency(futureeFrequency);
				forecastConfigRequest.setFutureInterval(futureInterval);

                                GtnUIFrameworkWebserviceRequest futureRequest = new GtnUIFrameworkWebserviceRequest();
				futureRequest.setForecastConfigurationRequest(forecastConfigRequest);

				GtnUIFrameworkWebserviceResponse responseFutureInterval = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
				+ GtnWsForecastConfigurationConstants.FUTURE_FREQUENCY_VALUE_CHANGE,
						futureRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
                                String futureYear = responseFutureInterval.getGtnWsForecastConfigurationResponse().getForecastPeriod();
                                gtnLogger.info("futureYear----------------->>>>"+futureYear);
                        	GtnUIFrameworkGlobalUI.getVaadinBaseComponent("FCView_forecastPeriod").setPropertyValue(futureYear);
                        }
                        }
			for (int pameterindex = 3; pameterindex < parameters.size() - 3; pameterindex++) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(pameterindex).toString())
						.setComponentVisible(visibility);
                                if(!"Interval".equals(value)){
                                GtnWsForecastConfigurationRequest forecastConfigurationRequest = new GtnWsForecastConfigurationRequest();
				GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
				request.setForecastConfigurationRequest(forecastConfigurationRequest);

				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
				+ GtnWsForecastConfigurationConstants.PERIOD_FREQUENCY_VALUE_CHANGE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
                                String forecastYear = response.getGtnWsForecastConfigurationResponse().getForecastPeriod();
                                gtnLogger.info("year----------------->>>>"+forecastYear);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("FCView_forecastPeriod").setPropertyValue(forecastYear);
                                }
			}
			for (int pameterindex = parameters.size() - 3; pameterindex < parameters.size(); pameterindex++) {
				GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(parameters.get(pameterindex).toString());
				boolean required = baseComponent.isEnabled() && baseComponent.isVisible();
				baseComponent.setFieldRequired(required);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
