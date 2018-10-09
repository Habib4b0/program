package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;

public class GtnFrameworkUpdateProcessAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkUpdateProcessAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Auto-generated method stub

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("starting execution of GtnFrameworkUpdateProcessAction..........");

		String status = (String) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.STATUS_ID).getV8StringFromField();
		gtnLogger.info("status    " + status);

		GtnWsProcessSchedulerBean processSchedulerBean = new GtnWsProcessSchedulerBean();

		Integer processSid = (Integer) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("scheduledProcessEditorUpdateButton").getComponentData().getCustomData();
		gtnLogger.info(" process sid: " + processSid);

		processSchedulerBean.setProcessSchedulerSid(processSid);

		if ("Inactive".equals(status)) {
			gtnLogger.info("executing inactive frequency");
			processSchedulerBean
					.setPsStartDate((Date) (Date.from(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.START_DATE_ID)
							.getV8DateFromDateField().atStartOfDay(ZoneId.systemDefault()).toInstant()) == null
									? new Date()
									: Date.from(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.START_DATE_ID)
											.getV8DateFromDateField().atStartOfDay(ZoneId.systemDefault())
											.toInstant())));

			processSchedulerBean.setPsEndDate((Date) (Date.from(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.END_DATE_ID)
					.getV8DateFromDateField().atStartOfDay(ZoneId.systemDefault()).toInstant()) == null ? new Date()
							: Date.from(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.END_DATE_ID)
									.getV8DateFromDateField().atStartOfDay(ZoneId.systemDefault()).toInstant())));

			gtnLogger.info("-------------start date: " + processSchedulerBean.getPsStartDate() + "  end date   "
					+ processSchedulerBean.getPsEndDate());
			getFieldValueForInactive(processSchedulerBean);

			callWebService(processSchedulerBean, componentId);

		} else if (hourAndMinValidation()) {
			
			getFieldValuesForActive(processSchedulerBean);

			callWebService(processSchedulerBean, componentId);
		}

	}

	private void callWebService(GtnWsProcessSchedulerBean processSchedulerBean, String componentId) throws GtnFrameworkGeneralException {
		gtnLogger.info("-  calling webservice URL");
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsProcessSchedulerRequest psrequest = new GtnWsProcessSchedulerRequest();
		psrequest.setProcessSchedulerBean(processSchedulerBean);
		request.setProcessSchedulerRequest(psrequest);
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
						+ GtnWsProcessScedulerConstants.GTN_WS_PROCESS_SCHEDULER_UPDATE_SERVICE,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		GtnUIFrameWorkActionConfig updateSuccessNotificationAction = new GtnUIFrameWorkActionConfig();
		updateSuccessNotificationAction.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		updateSuccessNotificationAction
				.addActionParameter(GtnFrameworkProcessSchedulerStringContants.UPDATE_SUCCESSFUL_MESSAGE);
		updateSuccessNotificationAction.addActionParameter("");
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, updateSuccessNotificationAction);

		resetFieldValues();

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.UPDATE_ID)
				.setEnable(false);
		
	}

	private void getFieldValuesForActive(GtnWsProcessSchedulerBean processSchedulerBean)
			throws GtnFrameworkValidationFailedException {
		gtnLogger.info("executing getFieldValueForActive");
		processSchedulerBean.setPsProcessName((String) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.PROCESS_NAME_ID)
				.getV8StringFromField());
		String frequency = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.FREQUENCY_ID).getStringFromField();
		processSchedulerBean.setPsProcessFrequency(frequency);
		processSchedulerBean
				.setPsStatus((String) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.STATUS_ID).getV8StringFromField());
		processSchedulerBean.setPsStartDate((Date) (Date.from(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.START_DATE_ID)
				.getV8DateFromDateField().atStartOfDay(ZoneId.systemDefault()).toInstant())));
		processSchedulerBean.setPsEndDate((Date) (Date.from(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.END_DATE_ID)
				.getV8DateFromDateField().atStartOfDay(ZoneId.systemDefault()).toInstant())));

		getFrequencyBasedInfo(processSchedulerBean);
	}

	private boolean hourAndMinValidation() throws GtnFrameworkGeneralException {

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.ERROR);

		if ("Time".equals(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.FREQUENCY_ID)
						.getStringFromField())) {
			return hourValidationForTime(alertActionConfig, alertParamsList);
		} else {
			if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES_ID)
					.getV8StringFromField() == null) {
				alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.RUN_EVERY_MINUTE_VALIDATION_MESAGE);
				alertActionConfig.setActionParameterList(alertParamsList);
				GtnUIFrameworkActionExecutor.executeSingleAction(GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.UPDATE_ID).getComponentId(),
						alertActionConfig);
				return false;
			} else {
				return true;
			}

		}
	}

	private boolean hourValidationForTime(GtnUIFrameWorkActionConfig alertActionConfig, List<Object> alertParamsList)
			throws GtnFrameworkGeneralException {
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN2_ID)
				.getV8StringFromField() == null) {
			alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.HOUR2_VALIDATION_MESAGE);
			alertActionConfig.setActionParameterList(alertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.UPDATE_ID).getComponentId(),
					alertActionConfig);
			return false;
		}
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN3_ID)
				.getV8StringFromField() == null) {
			alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.HOUR3_VALIDATION_MESAGE);
			alertActionConfig.setActionParameterList(alertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.UPDATE_ID).getComponentId(),
					alertActionConfig);
			return false;
		}
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES1_ID)
				.getV8StringFromField() == null) {
			alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.MINUTE1_VALIDATION_MESAGE);
			alertActionConfig.setActionParameterList(alertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.UPDATE_ID).getComponentId(),
					alertActionConfig);
			return false;
		}
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES_ID)
				.getV8StringFromField() == null) {
			alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.MINUTE2_VALIDATION_MESAGE);
			alertActionConfig.setActionParameterList(alertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.UPDATE_ID).getComponentId(),
					alertActionConfig);
			return false;
		}
		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES3_ID)
				.getV8StringFromField() == null) {
			alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.MINUTE3_VALIDATION_MESAGE);
			alertActionConfig.setActionParameterList(alertParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.UPDATE_ID).getComponentId(),
					alertActionConfig);
			return false;
		} else {
			return true;
		}
	}

	private void getFieldValueForInactive(GtnWsProcessSchedulerBean processSchedulerBean)
			throws GtnFrameworkValidationFailedException {
		gtnLogger.info("executing getFieldValueForInactive");
		processSchedulerBean.setPsProcessName((String) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.PROCESS_NAME_ID)
				.getV8StringFromField());
		String frequency = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.FREQUENCY_ID).getStringFromField();
		processSchedulerBean.setPsProcessFrequency(frequency);
		processSchedulerBean
				.setPsStatus((String) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.STATUS_ID).getV8StringFromField());

		getFrequencyBasedInfo(processSchedulerBean);
	}

	private void getFrequencyBasedInfo(GtnWsProcessSchedulerBean processSchedulerBean) {
		if ("Interval".equals(processSchedulerBean.getPsProcessFrequency())) {
			setValuesForInterval(processSchedulerBean);
		} else {
			processSchedulerBean.setStartHour(GtnFrameworkProcessSchedulerStringContants.TWENTY_FOUR);
			gtnLogger.info("in inactive run every : " + processSchedulerBean.getStartHour());
			processSchedulerBean.setStartMinute(GtnFrameworkProcessSchedulerStringContants.SIXTY);
			String hour1 = (String) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN1_ID).getV8StringFromField();
			String hour2 = (String) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN2_ID).getV8StringFromField();
			String hour3 = (String) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN3_ID).getV8StringFromField();
			processSchedulerBean
					.setPsHours1(hour1 == null ? GtnFrameworkProcessSchedulerStringContants.TWENTY_FOUR : hour1);
			processSchedulerBean
					.setPsHours2(hour2 == null ? GtnFrameworkProcessSchedulerStringContants.TWENTY_FOUR : hour2);
			processSchedulerBean
					.setPsHours3(hour2 == null ? GtnFrameworkProcessSchedulerStringContants.TWENTY_FOUR : hour3);

			String minute1 = (String) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES1_ID)
					.getV8StringFromField();
			String minute2 = (String) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES_ID)
					.getV8StringFromField();
			String minute3 = (String) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES3_ID)
					.getV8StringFromField();
			processSchedulerBean
					.setPsMinutes1(minute1 == null ? GtnFrameworkProcessSchedulerStringContants.SIXTY : minute1);
			processSchedulerBean
					.setPsMinutes2(minute2 == null ? GtnFrameworkProcessSchedulerStringContants.SIXTY : minute2);
			processSchedulerBean
					.setPsMinutes3(minute3 == null ? GtnFrameworkProcessSchedulerStringContants.SIXTY : minute3);
			gtnLogger.info(" else for time " + processSchedulerBean.getPsMinutes1());
		}
	}

	private void setValuesForInterval(GtnWsProcessSchedulerBean processSchedulerBean) {
		String startHour = (String) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN_EVERY_ID).getV8StringFromField();
		processSchedulerBean
				.setStartHour(startHour == null ? (GtnFrameworkProcessSchedulerStringContants.TWENTY_FOUR) : startHour);
		gtnLogger.info("start hour: " + processSchedulerBean.getStartHour());
		String startMinute = (String) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES_ID).getV8StringFromField();
		processSchedulerBean
				.setStartMinute(startMinute == null ? (GtnFrameworkProcessSchedulerStringContants.SIXTY) : startMinute);
		gtnLogger.info("start minute: " + processSchedulerBean.getStartMinute());
		processSchedulerBean.setPsHours1(GtnFrameworkProcessSchedulerStringContants.TWENTY_FOUR);
		processSchedulerBean.setPsHours2(GtnFrameworkProcessSchedulerStringContants.TWENTY_FOUR);
		processSchedulerBean.setPsHours3(GtnFrameworkProcessSchedulerStringContants.TWENTY_FOUR);
		processSchedulerBean.setPsMinutes1(GtnFrameworkProcessSchedulerStringContants.SIXTY);
		processSchedulerBean.setPsMinutes2(GtnFrameworkProcessSchedulerStringContants.SIXTY);
		processSchedulerBean.setPsMinutes3(GtnFrameworkProcessSchedulerStringContants.SIXTY);
		gtnLogger.info(" if for interval  " + processSchedulerBean.getStartMinute());
	}

	private void resetFieldValues() throws GtnFrameworkValidationFailedException {
		gtnLogger.info(" resetting field values:");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.PROCESS_NAME_ID)
				.loadV8FieldValue("");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.START_DATE_ID)
				.loadV8DateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.END_DATE_ID)
				.loadV8DateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.STATUS_ID)
				.loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN_EVERY_ID)
				.loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN1_ID)
				.loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN2_ID)
				.loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.RUN3_ID)
				.loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES_ID)
				.loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES1_ID)
				.loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.MINUTES3_ID)
				.loadV8ComboBoxComponentValue(GtnFrameworkProcessSchedulerStringContants.ZERO);

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.SCHEDULER_RESULTS_TABLE)
				.getLogicFromPagedDataTable().startSearchProcess(true);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// Auto-generated method stub
		return this;
	}

}
