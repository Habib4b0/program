package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;


import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkGenerateCffOutBoundAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{
	
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkGenerateCffOutBoundAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Auto-generated method stub

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		 gtnLogger.info("GtnFrameworkGenerateCffOutBoundAction started executing");
		@SuppressWarnings("unchecked")
		List<Object> customDataList = (List<Object>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MANUAL_RUN_ID).getComponentData().getCustomData();
		
		Object processSid = customDataList.get(0);
		Object processName = customDataList.get(1);
		Object schemaName = customDataList.get(2);
		
		gtnLogger.info("processSid: "+customDataList.get(0));
		gtnLogger.info("processName: "+customDataList.get(1));
		gtnLogger.info("schemaName: "+customDataList.get(2));
		
		GtnWsProcessSchedulerBean processSchedulerBean = new GtnWsProcessSchedulerBean();
		processSchedulerBean.setPsSchemaName((String) schemaName);
		processSchedulerBean.setPsProcessName((String) processName);
		processSchedulerBean.setProcessSchedulerSid((Integer) processSid);
		
		GtnUIFrameworkWebserviceRequest webServiceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsProcessSchedulerRequest cffRrequest = new GtnWsProcessSchedulerRequest();
		cffRrequest.setProcessSchedulerBean(processSchedulerBean);
		webServiceRequest.setProcessSchedulerRequest(cffRrequest);
		
		GtnUIFrameworkWebserviceResponse webServiceResponse = callProcessSchedularCffOutboundServive(webServiceRequest);
		
		gtnLogger.info("response result "+webServiceResponse.getGtnWsGeneralResponse().isSucess());
		GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig();
		notificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		if(webServiceResponse.getGtnWsGeneralResponse().isSucess()) {
			notificationAction.addActionParameter(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_MANUAL_SUCCESS_MESSAGE);
			notificationAction.addActionParameter("");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationAction);
		}else{
			notificationAction.addActionParameter(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_MANUAL_FAILURE_MESSAGE);
			notificationAction.addActionParameter("");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationAction);
		}
	}

    public GtnUIFrameworkWebserviceResponse callProcessSchedularCffOutboundServive(GtnUIFrameworkWebserviceRequest webServiceRequest) {
        return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_CFF_OUTBOUND_SERVICE_SCREEN
                        + GtnWsProcessScedulerConstants.GTN_WS_PROCESS_SCHEDULER_CFF_OUTBOUND_SERVICE_DATA,
                webServiceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// Auto-generated method stub
		return this;
	}

}
