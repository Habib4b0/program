package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;

public class GtnFrameworkUpdateButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkUpdateButtonAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("inside GtnFrameworkUpdateButtonAction do action");
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsProcessSchedulerRequest psRequest = new GtnWsProcessSchedulerRequest();
		GtnWsProcessSchedulerBean bean = new GtnWsProcessSchedulerBean();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		String processName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processName").getStringFromField();
		Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate").getDateFromDateField();
		Date endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate").getDateFromDateField();

		String run1Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run1Ddlb").getStringFromField();
		String run2Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run2Ddlb").getStringFromField();
		String run3Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run3Ddlb").getStringFromField();
		String hours1Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("hours1Ddlb").getStringFromField();
		String hours2Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("hours2Ddlb").getStringFromField();
		String hours3Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("hours3Ddlb").getStringFromField();

		bean.setPsProcessName(processName);
		bean.setPsStartDate(startDate);
		bean.setPsEndDate(endDate);
		bean.setPsSchemaName("BPI");
		bean.setPsCreatedDate(new Date());
		bean.setPsModifiedDate(new Date());
		bean.setPsHours1((String) (run1Ddlb == null ? null : run1Ddlb));
		bean.setPsHours2(run2Ddlb);
		bean.setPsHours3(run3Ddlb);
		bean.setPsMinutes1(hours1Ddlb);
		bean.setPsMinutes2(hours2Ddlb);
		bean.setPsMinutes3(hours3Ddlb);
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());

		bean.setProcessSchedulerSid(
				Integer.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("processMonitorId").toString()));
		psRequest.setProcessSchedulerBean(bean);
		request.setGtnWsGeneralRequest(generalRequest);
		request.setProcessSchedulerRequest(psRequest);
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
						+ GtnWsProcessScedulerConstants.GTN_WS_PROCESS_SCHEDULER_UPDATE_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
