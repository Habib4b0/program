/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Deepak.kumar
 */
public class GtnFrameworkRunButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkRunButtonAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.MANUAL_RESULT_TABLE).getValueFromPagedDataTable();

		gtnLogger.info("---------------> properties: " + gtnWsRecordBean.getProperties());
		gtnLogger.info("---------------> RecordHeader: " + gtnWsRecordBean.getRecordHeader());

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsProcessSchedulerRequest psrequest = new GtnWsProcessSchedulerRequest();
		GtnWsProcessSchedulerBean processSchedulerBean = new GtnWsProcessSchedulerBean();
		Object schemaName = gtnWsRecordBean.getPropertyValueByIndex(2);
		Object processName = gtnWsRecordBean.getPropertyValueByIndex(0);
		Object processSid = gtnWsRecordBean.getPropertyValueByIndex(3);
		gtnLogger.info("================= schema Name: " + schemaName);
		gtnLogger.info("================= process sid: " + gtnWsRecordBean.getPropertyValueByIndex(3));

		if ("CFF_OUTBOUND_INTERFACE".equalsIgnoreCase((String)processName) || "CFF OUTBOUND INTERFACE".equalsIgnoreCase((String)processName)) {
			List<Object> customDataList = new ArrayList<>();
			customDataList.add(processSid);
			customDataList.add(processName);
			customDataList.add(schemaName);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MANUAL_RUN_ID).setCustomData(customDataList);
			
			GtnUIFrameWorkActionConfig popUpActionConfig = new GtnUIFrameWorkActionConfig();
			popUpActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
			popUpActionConfig.addActionParameter("V002");
			popUpActionConfig.addActionParameter("CFF Search");
			popUpActionConfig.addActionParameter("85%");
			popUpActionConfig.addActionParameter("70%");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, popUpActionConfig);
		} else {

			processSchedulerBean.setPsSchemaName((String) schemaName);
			processSchedulerBean.setPsProcessName((String) processName);
			processSchedulerBean.setProcessSchedulerSid((Integer) processSid);
			psrequest.setProcessSchedulerBean(processSchedulerBean);
			request.setProcessSchedulerRequest(psrequest);

			GtnUIFrameworkWebserviceResponse processSchedularRunResponse = callProcessSchedulerRunService(							request);
			GtnUIFrameWorkActionConfig notificationAction = new GtnUIFrameWorkActionConfig();
			notificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			if (!processSchedularRunResponse.getGtnWsGeneralResponse().isSucess()) {
				gtnLogger.info("================  process running");

				notificationAction.addActionParameter(" Selected record " + schemaName
						+ " is currently being processed. Please wait till the process is completed, to run the interface again");
				notificationAction.addActionParameter("");
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationAction);
			} else {
				gtnLogger.info("================= processcurrently not running");			

				 new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
								+ GtnWsProcessScedulerConstants.GTN_WS_PROCESS_SCHEDULER_RUN_SERVICE_DATA,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
				notificationAction.addActionParameter(schemaName + " Manual process completed successfully");
				notificationAction.addActionParameter("");
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationAction);
			}
		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.MANUAL_RESULT_TABLE)
				.getLogicFromPagedDataTable().startSearchProcess(true);

	}

    public GtnUIFrameworkWebserviceResponse callProcessSchedulerRunService(GtnUIFrameworkWebserviceRequest request) {
        return new GtnUIFrameworkWebServiceClient()
                .callGtnWebServiceUrl(
                        GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
                                + GtnWsProcessScedulerConstants.GTN_WS_PROCESS_SCHEDULER_RUN_SERVICE,
                        request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}