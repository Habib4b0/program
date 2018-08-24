package com.stpl.gtn.gtn2o.ui.module.processscheduler.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

public class GtnFrameworkStartEndDateValidationCustomAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

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
			LocalDate startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("startDate").getV8DateFromDateField();
			LocalDate endDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("endDate").getV8DateFromDateField();
			String startDateString = startDate.format(dateFormatter);
			
			String endDateString = endDate.format(dateFormatter);
			logger.info("start date: " + startDate + "     end date: " + endDate);
			logger.info("start string date: " + startDateString + "     end string date: " + endDateString);
			logger.info("**********  " + GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkProcessSchedulerStringContants.STATUS_ID)
					.getStringFromV8ComboBox());
			try {
				if ((simpleDateFormat.parse(endDateString).compareTo(simpleDateFormat.parse(startDateString))) <= 0) {
					logger.info("start date  is greater than end date");
					GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
					alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

					List<Object> alertParamsList = new ArrayList<>();
					alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.ERROR);
					alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.MESSAGE_DATE_VALIDATION);
					alertActionConfig.setActionParameterList(alertParamsList);

					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
				}else {
					GtnUIFrameWorkActionConfig updateAction = new GtnUIFrameWorkActionConfig();
					updateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
					updateAction.addActionParameter(GtnFrameworkUpdateProcessAction.class.getName());
					GtnUIFrameworkActionExecutor.executeSingleAction(componentId, updateAction);
				}
			} catch (ParseException exp) {
				logger.info("exception due parsing date" + exp);
			}

		

		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
