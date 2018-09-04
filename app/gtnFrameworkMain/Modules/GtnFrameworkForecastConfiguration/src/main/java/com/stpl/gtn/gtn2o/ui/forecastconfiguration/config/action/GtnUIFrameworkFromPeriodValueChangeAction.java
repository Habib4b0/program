package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
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

public class GtnUIFrameworkFromPeriodValueChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkFromPeriodValueChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameworkBaseComponent dateBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
			Date fromDate = dateBaseComponent.getDateFromDateField();
			if (fromDate != null) {
				GtnWsForecastConfigurationRequest forecastConfigurationRequest = new GtnWsForecastConfigurationRequest();
				forecastConfigurationRequest.setFromDate(fromDate);
				GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
				request.setForecastConfigurationRequest(forecastConfigurationRequest);

				GtnUIFrameworkWebserviceResponse response = getResponse(request);
				if (response.getGtnWsForecastConfigurationResponse().isErrorMessage()) {
					dateBaseComponent.setPropertyValue(null);
					GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
							GtnFrameworkCommonStringConstants.ERROR,
							response.getGtnWsForecastConfigurationResponse().getMessage());
				}
			}
		} catch (Exception exception) {
			gtnLogger.error(exception.getMessage(), exception);
		}
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
						+ GtnWsForecastConfigurationConstants.FROM_PERIOD_VALUE_CHANGE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
