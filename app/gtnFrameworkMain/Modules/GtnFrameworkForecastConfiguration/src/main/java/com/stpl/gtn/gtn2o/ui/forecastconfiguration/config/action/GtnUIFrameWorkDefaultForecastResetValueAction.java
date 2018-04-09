/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;

/**
 *
 * @author mekalai.madhappa
 */
public class GtnUIFrameWorkDefaultForecastResetValueAction implements GtnUIFrameWorkAction,GtnUIFrameworkDynamicClass {
    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkDefaultForecastResetValueAction.class);
    @Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
                GtnWsForecastConfigurationRequest forecastConfigurationRequest = new GtnWsForecastConfigurationRequest();
				GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
				request.setForecastConfigurationRequest(forecastConfigurationRequest);

				GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
				+ GtnWsForecastConfigurationConstants.PERIOD_FREQUENCY_VALUE_CHANGE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
                                String forecastYear = response.getGtnWsForecastConfigurationResponse().getForecastPeriod();
                                gtnLogger.info("default year------&&&&&&----------->>>>"+forecastYear);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("FCView_forecastPeriod").setPropertyValue(forecastYear);
                                
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
    
}
