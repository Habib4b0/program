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
						Integer.parseInt(GtnUIFrameworkGlobalUI.getSessionProperty("processMonitorId").toString()));
			}
			monitorBean.setComponent(component.getCaption());
			cfpRequest.setProcessMonitorBean(monitorBean);
			request.setProcessMonitorRequest(cfpRequest);
			GtnUIFrameworkWebserviceResponse reponse = callProcessMonitorServiceScreen(request);
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

    public GtnUIFrameworkWebserviceResponse callProcessMonitorServiceScreen(GtnUIFrameworkWebserviceRequest request) {
        return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_SERVICE_SCREEN
                        + GtnWsProcessMonitorConstants.GTN_WS_PROCESS_MONITOR_DUPLICATE_PROCESS_NAME_SERVICE,
                request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
    }

	public void processTypeAutomatic(String componentId) throws GtnFrameworkGeneralException {
		String processType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("processType").getCaptionFromComboBox();
		String run1Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run1Ddlb").getCaptionFromComboBox();
		String run2Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run2Ddlb").getCaptionFromComboBox();
		String run3Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("run3Ddlb").getCaptionFromComboBox();
		String hours1Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUNONEHOURDDLB).getCaptionFromComboBox();
		String hours2Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUNTWOHOURDDLB).getCaptionFromComboBox();
		String hours3Ddlb = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUNTHREEHOURDDLB).getCaptionFromComboBox();
	        String[] run={run1Ddlb,run2Ddlb,run3Ddlb};
	        String[] hour={hours1Ddlb,hours2Ddlb,hours3Ddlb};
                
		if (alertForOneHour(processType,run,hour, componentId)) return;
                
		if (alertForHourAndMinute(processType, run1Ddlb, hours1Ddlb, componentId)) return;
                
		if(alertForHourTwoAndMinuteTwo(processType, run1Ddlb, hours1Ddlb, run2Ddlb, hours2Ddlb, componentId) ) return;
                
                
		if(((processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_AUTOMATIC)) ||
				(processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_BOTH)))
				&& (!run1Ddlb.isEmpty()||!hours1Ddlb.isEmpty()) && ((!run3Ddlb.isEmpty() && hours3Ddlb.isEmpty()) || (run3Ddlb.isEmpty() && !hours3Ddlb.isEmpty())) ) {
			if(run3Ddlb.isEmpty()) {
				callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUN3_HOURS,
						componentId);
				return;
				}
				if(hours3Ddlb.isEmpty()) {
					callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUN3_MINUTES,
							componentId);
					return;
				}
		}
	}

    private boolean alertForHourTwoAndMinuteTwo(String processType, String run1Ddlb, String hours1Ddlb, String run2Ddlb, String hours2Ddlb, String componentId) throws GtnFrameworkGeneralException {
        if (((processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_AUTOMATIC)) ||
                (processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_BOTH)))
                && (!run1Ddlb.isEmpty()||!hours1Ddlb.isEmpty()) && ((!run2Ddlb.isEmpty() && hours2Ddlb.isEmpty()) || (run2Ddlb.isEmpty() && !hours2Ddlb.isEmpty()))) {
            if (run2Ddlb.isEmpty()) {
                callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUN2_HOURS,
                        componentId);
                return true;
            }
            if (hours2Ddlb.isEmpty()) {
                callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUN2_MINUTES,
                        componentId);
                return true;
            }
        }
        return false;
    }

    private boolean alertForHourAndMinute(String processType, String run1Ddlb, String hours1Ddlb, String componentId) throws GtnFrameworkGeneralException {
        if (((processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_AUTOMATIC))||
                (processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_BOTH)))
                && (run1Ddlb.isEmpty()||hours1Ddlb.isEmpty())) {
            if (run1Ddlb.isEmpty()) {
                callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUN1_HOURS,
                        componentId);
                return true;
            }
            if (hours1Ddlb.isEmpty()) {
                callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_RUN1_MINUTES,
                        componentId);
                return true;
            }
        }
        return false;
    }

    private boolean alertForOneHour(String processType, String[] run, String[] hours, String componentId) throws GtnFrameworkGeneralException {
        if (((processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_AUTOMATIC))
                || (processType.equals(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_BOTH)))
                && (run[0].isEmpty() && hours[0].isEmpty())
                && (run[1].isEmpty() && hours[1].isEmpty())
                && (run[2].isEmpty() && hours[2].isEmpty())) {
            callAlertAction(GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_ATLEAST_ONE_RUN_TIME,
                    componentId);
            return true;
        }
        return false;
    }

}
