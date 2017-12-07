package com.stpl.gtn.gtn2o.ui.module.processmonitor.action;

import java.util.Arrays;
import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.processmonitor.constants.GtnFrameworkProcessMonitorStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processmonitor.bean.GtnWsProcessMonitorBean;
import com.stpl.gtn.gtn2o.ws.processmonitor.constants.GtnWsProcessMonitorConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.processmonitor.GtnWsProcessMonitorRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkProcessMonitorValidationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkProcessMonitorValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Process Monitor Validation Action");

		String processName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processName").getStringFromField();
		String processType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processType").getCaptionFromComboBox();
		Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate").getDateFromDateField();
		Date endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate").getDateFromDateField();

		if (processName.isEmpty()) {
			callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_PROCESS_NAMES, componentId);
			return;
		}
		if (processType.isEmpty()) {
			callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_PROCESS_TYPE, componentId);
			return;
		}
		if (startDate == null) {
			callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_START_DATE, componentId);
			return;
		}
		if (endDate == null) {
			callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_END_DATE, componentId);
			return;
		}
		duplicateProcessName(componentId);
		processTypeAutomatic(componentId);

		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_ADD_BUTTON);

		if (component.getCaption().equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_UPDATE)) {
			processTypeAutomaticForUpdate(componentId);
		}
	}

	public void processTypeAutomaticForUpdate(String componentId) throws GtnFrameworkGeneralException {
		String processType = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_PROCESSTYPE)
				.getCaptionFromComboBox();
		String run1Ddlb = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUNONEDDLB)
				.getCaptionFromComboBox();

		if ((processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_AUTOMATIC)
				|| processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_BOTH))
				&& run1Ddlb.isEmpty()) {
			callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_ATLEAST_ONE_RUN_TIME,
					componentId);
			return;
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

	public void callAlertAction(String message, String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
		if (message.length() > 0) {
			String msg = GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_VALIDATION_MSG + message;
			alertActionConfig.setActionParameterList(
					Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.ERROR, msg }));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCommonConstants.VALIDATION_ERROR);
		}
	}

	public void duplicateProcessName(String componentId) throws GtnFrameworkGeneralException {
		String processName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processName").getStringFromField();
		Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate").getDateFromDateField();
		Date endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate").getDateFromDateField();

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsProcessMonitorRequest cfpRequest = new GtnWsProcessMonitorRequest();
		GtnWsProcessMonitorBean monitorBean = new GtnWsProcessMonitorBean();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();
		if (startDate.equals(endDate)) {
			String msg = GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_VALIDATION_EQUAL_MSG;
			alertActionConfig.setActionParameterList(
					Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.ERROR, msg }));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
		}
		if (startDate.after(endDate)) {
			String msg = GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_VALIDATION_GREATER_MSG;
			alertActionConfig.setActionParameterList(
					Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.ERROR, msg }));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
		}
		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_ADD_BUTTON);
		if (!processName.isEmpty()) {
			monitorBean.setProcessName(processName);
			if (GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_UPDATE
					.equalsIgnoreCase(component.getCaption())) {
				monitorBean.setProcessMonitorSid(
						Integer.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("processMonitorId").toString()));
			}
			monitorBean.setComponent(component.getCaption());
			cfpRequest.setProcessMonitorBean(monitorBean);
			request.setProcessMonitorRequest(cfpRequest);
			GtnUIFrameworkWebserviceResponse reponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_SERVICE_SCREEN
							+ GtnWsProcessMonitorConstants.GTN_WS_PROCESS_MONITOR_DUPLICATE_PROCESS_NAME_SERVICE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsProcessMonitorBean processMonitorBean = reponse.getGtnWsProcessMonitorResponse().getMonitorBean();
			if (processMonitorBean.isErrorMessage()) {
				String msg = GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_DUPLICATE_PROCESS_NAME;
				alertActionConfig.setActionParameterList(
						Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.ERROR, msg }));
				alertAction.doAction(componentId, alertActionConfig);
				throw new GtnFrameworkGeneralException(GtnFrameworkCommonConstants.VALIDATION_ERROR + msg);
			}

		}
	}

	public void processTypeAutomatic(String componentId) throws GtnFrameworkGeneralException {
		String processType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processType").getCaptionFromComboBox();
		String run1Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run1Ddlb").getCaptionFromComboBox();
		String run2Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run2Ddlb").getCaptionFromComboBox();
		String run3Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run3Ddlb").getCaptionFromComboBox();
		String hours1Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUNONEHOURDDLB).getCaptionFromComboBox();
		if (((processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_AUTOMATIC))||
				(processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_BOTH)))
				&& (run1Ddlb.isEmpty()||hours1Ddlb.isEmpty())) {
			callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_ATLEAST_ONE_RUN_TIME,
					componentId);
			return;
		}
		if (processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_AUTOMATIC)
				&& run1Ddlb.isEmpty() && run2Ddlb.isEmpty() && run3Ddlb.isEmpty()) {
			callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_ATLEAST_ONE_RUN_TIME,
					componentId);
			return;
		}
	}

}
