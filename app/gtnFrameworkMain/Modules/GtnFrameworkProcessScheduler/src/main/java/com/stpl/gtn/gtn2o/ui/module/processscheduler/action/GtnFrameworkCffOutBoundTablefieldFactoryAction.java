package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsCffOutBoundBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCffOutBoundTablefieldFactoryAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCffOutBoundTablefieldFactoryAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Auto-generated method stub

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info(" Started execution of CffOutBoundTablefieldFactoryAction");
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);
		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
		
		GtnWsCffOutBoundBean gtnCffOutBoundBean=new GtnWsCffOutBoundBean();
		gtnCffOutBoundBean.setCffDetailsSid(actionParam.getItemId().getIntegerPropertyByIndex(4));
		gtnCffOutBoundBean.setRsModelSid(actionParam.getItemId().getIntegerProperty(GtnFrameworkProcessSchedulerStringContants.RS_MODEL_SID));
		gtnCffOutBoundBean.setPeriodSid(actionParam.getItemId().getIntegerProperty(GtnFrameworkProcessSchedulerStringContants.PERIOD_SID));
		gtnCffOutBoundBean.setCheckedRecord(Boolean.parseBoolean(actionParam.getCurrentValue().toString()));
		
		
		
		GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
		gtnWsProcessSchedulerRequest.setCffOutBoundBean(gtnCffOutBoundBean);
		
		GtnUIFrameworkWebserviceRequest wsRequest = new GtnUIFrameworkWebserviceRequest();
		wsRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
		
		GtnUIFrameworkWebserviceResponse response=callProcessSchedularCffOutboundService(wsRequest);
		gtnLogger.info("updated check record col : "+response.getGtnWsGeneralResponse().isSucess());
		

		
	}

    public GtnUIFrameworkWebserviceResponse callProcessSchedularCffOutboundService(GtnUIFrameworkWebserviceRequest wsRequest) {
        return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_CFF_OUTBOUND_SERVICE_SCREEN
                        + GtnWsProcessScedulerConstants.UPDATE_CHECK_RECORD,
                wsRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// Auto-generated method stub
		return this;
	}

}
