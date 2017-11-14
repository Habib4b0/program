package com.stpl.gtn.gtn2o.ui.action.workflow;

import java.util.List;

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
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;

/**
 *
 * @author STPL
 */
public class GtnFrameworkForecastWorkflowAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastWorkflowAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String url = "";
		gtnLogger.info(" inside GtnFrameworkForecastWorkflowAction ");
		List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();

		GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequest = new GtnWsForecastProjectionSubmitRequest();

		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(fieldValues.get(0), componentId);
		GtnForecastBean projMasterBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();

		GtnWsForecastProjectionSubmitBean gtnWsForecastProjectionSubmitBean = new GtnWsForecastProjectionSubmitBean();
		gtnWsForecastProjectionSubmitBean.setProjectionId(Integer.valueOf(projMasterBean.getProjectionMasterSid()));
		gtnWsForecastProjectionSubmitBean.setWorkflowId(projMasterBean.getWorkflowId());
		gtnWsForecastProjectionSubmitBean.setWorkflowStatus(projMasterBean.getWorkflowStatus());
		gtnWsForecastProjectionSubmitBean.setNoOfApproval(projMasterBean.getNoOfApprovals());
		gtnWsForecastProjectionSubmitBean.setApprovalLevel(projMasterBean.getApprovalLevels());
		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());

		if ("Approve".equalsIgnoreCase(fieldValues.get(1))) {
			url = GtnWsForecastReturnsConstants.GTN_WS_APPROVE_CONTROLER_URI
					+ GtnWsForecastReturnsConstants.GTN_WS_APPROVE_PROJECTION_URI;
		} else if ("Reject".equalsIgnoreCase(fieldValues.get(1))) {
			url = GtnWsForecastReturnsConstants.GTN_WS_REJECT_CONTROLER_URI
					+ GtnWsForecastReturnsConstants.GTN_WS_REJECT_PROJECTION_URI;
		} else if ("Cancel".equalsIgnoreCase(fieldValues.get(1))) {
			url = GtnWsForecastReturnsConstants.GTN_WS_CANCEL_CONTROLER_URI
					+ GtnWsForecastReturnsConstants.GTN_WS_CANCEL_PROJECTION_URI;
		} else if ("Withdraw".equalsIgnoreCase(fieldValues.get(1))) {
			url = GtnWsForecastReturnsConstants.GTN_WS_WITHDRAW_CONTROLER_URI
					+ GtnWsForecastReturnsConstants.GTN_WS_WITHDRAW_PROJECTION_URI;
		}

		forecastProjectionSubmitRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		forecastProjectionSubmitRequest.setGtnWsForecastProjectionSubmitBean(gtnWsForecastProjectionSubmitBean);
		request.setGtnWsForecastProjectionSubmitRequest(forecastProjectionSubmitRequest);
		wsclient.callGtnWebServiceUrl(url, GtnFrameworkCommonStringConstants.GTN_BPM, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
