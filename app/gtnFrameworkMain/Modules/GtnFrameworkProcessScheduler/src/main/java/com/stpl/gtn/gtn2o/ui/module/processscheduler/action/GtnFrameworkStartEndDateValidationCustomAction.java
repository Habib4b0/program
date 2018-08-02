package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkStartEndDateValidationCustomAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkStartEndDateValidationCustomAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.info("inside GtnFrameworkUpdateButtonAction do action");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate= GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate").getV8DateFromDateField();
		LocalDate endDate= GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate").getV8DateFromDateField();
		String startDateString= startDate.format(dateFormatter);
		//String startDateString=simpleDateFormat.format(startDate);
		//String endDateString=simpleDateFormat.format(endDate);
		String endDateString= endDate.format(dateFormatter);
		logger.info("start date: "+startDate+"     end date: "+endDate);
		logger.info("start string date: "+startDateString+"     end string date: "+endDateString);
		logger.info("**********  "+GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.STATUS_ID).getStringFromV8ComboBox());
		try {
			if((simpleDateFormat.parse(endDateString).compareTo(simpleDateFormat.parse(startDateString)))<0){
				logger.info("start date  is greater than enddate");
				GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
				alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

				List<Object> alertParamsList = new ArrayList<>();
				alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.ERROR);
				alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.MESSAGE_DATE_VALIDATION);
				alertActionConfig.setActionParameterList(alertParamsList); 
				
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			}
		} catch (ParseException exp) {
			logger.info("exception due parsing date"+exp);
		}
		
		
		
		
		
		
		
		
		/*GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
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
*/
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
