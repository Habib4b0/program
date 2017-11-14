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
 * @author Kalpana.Ramanana This class is used to Validate Tree Table Left
 *         header
 */
public class GtnFrameworkReturnProjectionTabLeftTreeTableValidateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkReturnProjectionTabLeftTreeTableValidateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("inside GtnFrameworkReturnProjectionTabLeftTreeTableValidateAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String msg = "";
		boolean ischecked = loadDataFromService(gtnUIFrameWorkActionConfig, componentId);

		if (!ischecked) {
			msg += GtnFrameworkForecastAlertMsgConstants.RETURNS_LEFT_TREE_TABLE_VALIDATION_MSG;
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
			alertActionConfig.setActionParameterList(Arrays.asList(new Object[] {
					GtnFrameworkForecastAlertMsgConstants.RETURNS_LEFT_TREE_TABLE_VALIDATION_MSG_HEADER, msg }));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException("Validation Error " + msg);
		}

	}

	private boolean loadDataFromService(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String componentId)
			throws GtnFrameworkGeneralException {
		try {

			GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();

			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();

			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);

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

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
