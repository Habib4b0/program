package com.stpl.gtn.gtn2o.ui.action.crud;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

/**
 *
 * @author Stpl
 */
public class GtnFrameworkForecastReturnDeleteFileAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastReturnDeleteFileAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info(" inside GtnFrameworkForecastReturnDeleteFileAction ");

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(
				gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString(), componentId);
		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();
		forecastRequest.setGtnForecastBean(gtnForecastBean);
		request.setGtnWsForecastRequest(forecastRequest);
		wsclient.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_DELETE_FILE_ON_CLOSE_SERVICE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,

				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
