package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsCffOutBoundBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkCheckAllRecordAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCheckAllRecordAction.class);
	
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}
	
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Executing the GtnFrameworkCheckAllRecordAction");
		GtnWsCffOutBoundBean gtnCffOutBoundBean=new GtnWsCffOutBoundBean();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI	.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.CFF_OUTBOUND_RESULTS_TABLE);
		Object value = tableBaseComponent
				.getTableColumnCheckboxValue(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		gtnCffOutBoundBean.setCheckAll(true);
		
		gtnCffOutBoundBean.setColumnName(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		gtnCffOutBoundBean.setValue( value);
		updateField(gtnCffOutBoundBean);
		for (GtnWsRecordBean record : tableBaseComponent.getItemsFromDataTable()) {
			tableBaseComponent.setTableColumnValue(record, GtnFrameworkCommonConstants.CHECK_RECORD_ID, value);
		}

	}

	private void updateField(GtnWsCffOutBoundBean gtnCffOutBoundBean) {
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		
		GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
		gtnWsProcessSchedulerRequest.setCffOutBoundBean(gtnCffOutBoundBean);
		
		
		updateRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		
		callProcessSchedularCffOutbound(updateRequest);		
	}

    public GtnUIFrameworkWebserviceResponse callProcessSchedularCffOutbound(GtnUIFrameworkWebserviceRequest updateRequest) {
        return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_CFF_OUTBOUND_SERVICE_SCREEN + GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_CFF_OUTBOUND_UPDATE_CHECKED_ALL,
                updateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
