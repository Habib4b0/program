package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.constants.GtnFrameworkPeriodConfigurationContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnFrameworkPeriodFieldValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Not Needed

	}

	/*
	 * (non-Javadoc) Param List 1 - source 2 - From/To 3- Date textbox field 4-
	 * Date/Text
	 * 
	 */
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		boolean isDateFieldInitiated = (boolean) gtnUIFrameWorkActionConfig.getActionParameterList().get(4);
		Calendar date;
		if (isDateFieldInitiated) {
			date = loadDateFromField(gtnUIFrameWorkActionConfig);
		} else {
			date = generateDateBasedOnPeriod(componentId, gtnUIFrameWorkActionConfig);
		}

		boolean isFrom = (boolean) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
		if (!isFrom) {
			loadLastDate(date);
		}
		formatDateAndLoad(date.getTime(), String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(3)));
	}

	private Calendar generateDateBasedOnPeriod(String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {

		Object periodValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)))
				.validateAndGetValue();
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
		return getDate(String.valueOf(periodValue));
	}

	private Calendar loadDateFromField(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkValidationFailedException {
		Calendar date;
		Date dateFromField = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)))
				.getDateFromDateField();
		date = Calendar.getInstance();
		if (dateFromField != null) {
			date.setTime(dateFromField);
		}
		date.set(Calendar.DATE, 1);
		return date;
	}

	private void formatDateAndLoad(Date date, String componentId) {
		String formatedString = formatDate(date);
		loadDateToField(formatedString, componentId);
	}

	private void loadDateToField(String value, String componentId) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).loadFieldValue(value);
	}

	private String formatDate(Date date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(GtnFrameworkPeriodConfigurationContants.PERIOD_DATE_FORMAT);
		return dateFormat.format(date);
	}

	private void loadLastDate(Calendar date) {
		date.set(Calendar.DATE, date.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	private Calendar getDate(String periodValue) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, 1);
		date.set(Calendar.MONTH, date.get(Calendar.MONTH) + Integer.valueOf(periodValue));
		return date;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
