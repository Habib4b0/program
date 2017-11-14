package com.stpl.gtn.gtn2o.ui.module.processmonitor.action;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.constants.GtnFrameworkProcessMonitorStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processmonitor.bean.GtnWsProcessMonitorBean;
import com.stpl.gtn.gtn2o.ws.processmonitor.constants.GtnWsProcessMonitorConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.processmonitor.GtnWsProcessMonitorRequest;

public class GtnFrameworkSaveButtonAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkSaveButtonAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {

			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsProcessMonitorRequest monitorRequest = new GtnWsProcessMonitorRequest();
			GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
			GtnWsProcessMonitorBean monitorBean = new GtnWsProcessMonitorBean();
			GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_ADD_BUTTON);

			String processName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processName").getStringFromField();
			Object processType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processType").getStringFromField();
			int calender = 1;
			Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate").getDateFromDateField();
			Date endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate").getDateFromDateField();

			String run1Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run1Ddlb").getStringFromField();
			String run2Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run2Ddlb").getStringFromField();
			String run3Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run3Ddlb").getStringFromField();
			String hours1Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("hours1Ddlb").getStringFromField();
			String hours2Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("hours2Ddlb").getStringFromField();
			String hours3Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("hours3Ddlb").getStringFromField();

			monitorBean.setProcessName(processName);
			monitorBean.setProcessDisplayName(processName);
			monitorBean.setProcessType((String) processType);
			monitorBean.setStartDate(startDate);
			monitorBean.setEndDate(endDate);
			monitorBean.setActiveFlag(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_ACTIVE_FLAG);
			monitorBean.setSchemaName(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_SCHEME_NAME);
			monitorBean.setFrequency(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_FREQUENCY);
			monitorBean.setSlaCalendarMasterSid(calender);
			monitorBean.setCreatedDate(new Date());
			monitorBean.setModifiedDate(new Date());
			monitorBean.setHours1(getDdlbDefaultValue(run1Ddlb));
			monitorBean.setHours2(getDdlbDefaultValue(run2Ddlb));
			monitorBean.setHours3(getDdlbDefaultValue(run3Ddlb));
			monitorBean.setMinutes1(getDdlbDefaultValue(hours1Ddlb));
			monitorBean.setMinutes2(getDdlbDefaultValue(hours2Ddlb));
			monitorBean.setMinutes3(getDdlbDefaultValue(hours3Ddlb));
			generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());

			String message = "";
			String serviceUrl = GtnWsProcessMonitorConstants.GTN_WS_PROCESS_MONITOR_SAVE_SERVICE;
			if (!componentId.equals(GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_DELETE)
					&& GtnFrameworkCommonStringConstants.ADD.equalsIgnoreCase(component.getCaption())) {
				monitorBean.setInboundStatus(
						GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_INBOUND_STATUS_ADD);
				message = processName + GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_SAVED_SUCCESSFULLY;

			} else if (!componentId.equals(GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_DELETE)
					&& GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_UPDATE
							.equalsIgnoreCase(component.getCaption())) {
				if (GtnUIFrameworkGlobalUI
						.getSessionProperty(GtnFrameworkProcessMonitorStringContants.PROCESS_MONITOR_ID) != null) {
					monitorBean.setProcessMonitorSid(Integer.valueOf(GtnUIFrameworkGlobalUI
							.getSessionProperty(GtnFrameworkProcessMonitorStringContants.PROCESS_MONITOR_ID)
							.toString()));
				}
				monitorBean.setInboundStatus(
						GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_INBOUND_STATUS_CHANGE);
				message = processName
						+ GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_UPDATED_SUCCESSFULLY;
			} else {
				if (GtnUIFrameworkGlobalUI
						.getSessionProperty(GtnFrameworkProcessMonitorStringContants.PROCESS_MONITOR_ID) != null) {
					monitorBean.setProcessMonitorSid(Integer.valueOf(GtnUIFrameworkGlobalUI
							.getSessionProperty(GtnFrameworkProcessMonitorStringContants.PROCESS_MONITOR_ID)
							.toString()));
				}
				message = processName
						+ GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_DELETED_SUCCESSFULLY;
				serviceUrl = GtnWsProcessMonitorConstants.GTN_WS_PROCESS_MONITOR_DELETE_SERVICE;
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_DELETE)
					.setComponentEnable(false);
			monitorRequest.setProcessMonitorBean(monitorBean);
			request.setProcessMonitorRequest(monitorRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_SERVICE_SCREEN + serviceUrl, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
			alertActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
			alertActionConfig.addActionParameter("Confirmation");
			alertActionConfig.addActionParameter(message);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);

			GtnUIFrameWorkActionConfig refreshTableActionConfig = new GtnUIFrameWorkActionConfig();
			refreshTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
			refreshTableActionConfig.addActionParameter("searchResultTable");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, refreshTableActionConfig);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

	private String getDdlbDefaultValue(String value) {

		return value.isEmpty() ? null : value;
	}
}
