package com.stpl.gtn.gtn2o.ui.action.validation;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastAlertMsgConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnUIFrameworkPopulateButtonValidateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkPopulateButtonValidateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("inside GtnUIFrameworkPopulateButtonValidateAction");

		String msg = "";
		boolean ischecked = loadDataFromService(gtnUIFrameWorkActionConfig, componentId);

		if (!ischecked) {
			msg += GtnFrameworkForecastAlertMsgConstants.SELECT_MIN_ONE_RECORD_MSG;
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
			alertActionConfig.setActionParameterList(Arrays
					.asList(new Object[] { GtnFrameworkForecastAlertMsgConstants.NO_RECORD_SELECTED_MSG_HEADER, msg }));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException("Validation Error " + msg);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private boolean loadDataFromService(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId)
			throws GtnFrameworkGeneralException {
		try {

			GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();

			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();

			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(
					gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString(), componentId);

			GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

			gtnWsForecastRequest.setGtnForecastBean(gtnForecastBean);
			gtnWsRequest.setGtnWsForecastRequest(gtnWsForecastRequest);

			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_READ_CHECKBOX_SERVICE,
					GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, gtnWsRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			GtnWsForecastResponse gtnWsForecastResponse = response.getGtnWsForecastResponse();
			GtnForecastBean gtnForecastBeanResult = gtnWsForecastResponse.getGtnForecastBean();

			return gtnForecastBeanResult.isCheckedLeftTreeTable();

		} catch (Exception ex) {
			gtnLogger.error("Error in loadDataFromService Method", ex);
			throw new GtnFrameworkGeneralException("Error in loadDataFromService  Method", ex);
		}
	}

}
