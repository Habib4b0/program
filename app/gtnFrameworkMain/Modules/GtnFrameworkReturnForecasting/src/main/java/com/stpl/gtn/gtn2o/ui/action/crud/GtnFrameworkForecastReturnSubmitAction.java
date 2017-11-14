/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action.crud;

import java.util.Arrays;

import org.asi.ui.customwindow.CustomWindow;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkInfoAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastProjectionSubmitResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;
import com.vaadin.server.VaadinService;

/**
 *
 * @author STPL
 */
public class GtnFrameworkForecastReturnSubmitAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastReturnSubmitAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("inside GTnFrameworkForecastReturnSubmitAction");
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequest = new GtnWsForecastProjectionSubmitRequest();
		GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean = new GtnWsForecastProjectionSubmitBean();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(gtnUIFrameWorkActionConfig.getFieldValues().get(0), componentId);
		GtnForecastBean projMasterBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();
		gtnLogger.info("ProjectionMaster Sidddd============================" + projMasterBean.getProjectionMasterSid());
		String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() != null
				? VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() : "";
		String filePath1 = GtnFrameworkCommonStringConstants.ST_MAIL_CONFIG;
		forecastProjectionSubmitBean.setModuleName(GtnWsBpmCommonConstants.FORECAST_RETURNS);
		forecastProjectionSubmitBean.setProjectionId(Integer.valueOf(projMasterBean.getProjectionMasterSid()));
		forecastProjectionSubmitBean.setWorkflowIdGeneratorXmlPath(path + filePath1);
		forecastProjectionSubmitBean.setNotes("Please enter notes.");
		gtnLogger.info("File PAth===============================" + path);
		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		forecastProjectionSubmitRequest.setGtnWsForecastProjectionSubmitBean(forecastProjectionSubmitBean);
		forecastProjectionSubmitRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		request.setGtnWsForecastProjectionSubmitRequest(forecastProjectionSubmitRequest);

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse = wsclient.callGtnWebServiceUrl(
				GtnWsForecastReturnsConstants.GTN_WS_SUBMIT_CONTROLER_URI
						+ GtnWsForecastReturnsConstants.GTN_WS_SUBMIT_PROJECTION_URI,
				GtnFrameworkCommonStringConstants.GTN_BPM, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsForecastProjectionSubmitResponse forecastProjectionSubmitResponse = frameworkWebserviceResponse
				.getGtnWsForecastProjectionSubmitResponse();
		GtnWsForecastProjectionSubmitBean forecastProjectionSubmitResponseBean = forecastProjectionSubmitResponse
				.getGtnWsForecastProjectionSubmitBean();
		String workflowId = forecastProjectionSubmitResponseBean.getWorkflowId();
		GtnWsGeneralResponse generalResponse = frameworkWebserviceResponse.getGtnWsGeneralResponse();
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId);
		CustomWindow vaadinComponent = componentData.getCustomWindow();
		vaadinComponent.close();
		if (generalResponse.isSucess() && !workflowId.isEmpty()
				&& !GtnWsBpmCommonConstants.PERMISSION_DENIED.equals(workflowId)) {
			callFramwWorkAlertAction("Submitted Successfully ", " Workflow Id: " + workflowId + "  ", componentId);
		} else {
			callFramwWorkAlertAction("Permission Denied", "You dont have permission to submit a projection.",
					componentId);
		}
	}

	private void callFramwWorkAlertAction(String header, String msg, String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig alertInfoActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkInfoAction alertInfoAction = new GtnUIFrameWorkInfoAction();
		alertInfoActionConfig.setActionParameterList(Arrays.asList(new Object[] { header, msg }));
		alertInfoAction.doAction(componentId, alertInfoActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
