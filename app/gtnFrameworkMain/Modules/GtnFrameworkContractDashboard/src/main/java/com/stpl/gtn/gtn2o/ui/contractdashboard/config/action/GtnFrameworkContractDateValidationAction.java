/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkContractDateValidationAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		if (processDataBean.needOperation()) {
			List<String> startDateField = new ArrayList<>((List<String>) parameters.get(2));
			List<String> endDateField = new ArrayList<>((List<String>) parameters.get(3));
			if ((!parameters.get(1).toString().equals(processDataBean.getContractInfoFieldList().get(6)))
					&& !componentId.equals(processDataBean.getContractInfoFieldList().get(7))) {
				startDateField.add(processDataBean.getContractInfoFieldList().get(6));
				endDateField.add(processDataBean.getContractInfoFieldList().get(7));
			}
			boolean value = validateDate(componentId, parameters.get(1).toString(), startDateField, endDateField,
					parameters.get(4).toString(), parameters.get(5).toString());
			if (value && parameters.size() > 6) {
				GtnUIFrameworkActionExecutor.executeActionList(componentId,
						(List<GtnUIFrameWorkActionConfig>) parameters.get(6));
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private boolean validateDate(String componentId, String dateField, List<String> startDateField,
			List<String> endDateField, String header, String date) throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent mainBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(dateField,
				componentId);
		Date mainDate = mainBaseComponent.getDateFromDateField();
		String message = "";
		if (mainDate != null) {
			mainDate = getUpdatedDate(mainDate);
			SimpleDateFormat dateFormat = new SimpleDateFormat(GtnWsContractDashboardContants.MMDDYYYY);
			message = validateStartDate(startDateField, date, mainDate, message, dateFormat);
			if (message.isEmpty()) {
				message = validateEndDate(endDateField, date, mainDate, message, dateFormat);
			}
			if (!message.isEmpty()) {
				executeWarningAction(componentId, header, message);
				mainBaseComponent.setPropertyValue(null);
			}
		}
		return message.isEmpty();
	}

	private void executeWarningAction(String componentId, String header, String message)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig failureActionConfig = new GtnUIFrameWorkActionConfig();
		failureActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);
		failureActionConfig.addActionParameter(header);
		failureActionConfig.addActionParameter(message);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, failureActionConfig);
	}

	private String validateEndDate(List<String> endDateField, String date, Date mainDate, String message,
			SimpleDateFormat dateFormat) throws GtnFrameworkValidationFailedException {
		String endDateValidationMessage = String.valueOf(message);
		for (String endFieldId : endDateField) {
			GtnUIFrameworkBaseComponent compareBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(endFieldId);
			Date compareDate = compareBaseComponent.getDateFromDateField();
			if (compareDate != null && mainDate.after(getUpdatedDate(compareDate))) {
				endDateValidationMessage = date + " date cannot be after " + dateFormat.format(compareDate);
				break;
			}
		}
		return endDateValidationMessage;
	}

	private String validateStartDate(List<String> startDateField, String date, Date mainDate, String message,
			SimpleDateFormat dateFormat) throws GtnFrameworkValidationFailedException {
		String startDateValidationMessage = String.valueOf(message);
		for (String startFieldId : startDateField) {
			GtnUIFrameworkBaseComponent compareBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(startFieldId);
			Date compareDate = compareBaseComponent.getDateFromDateField();
			if (compareDate != null && mainDate.before(getUpdatedDate(compareDate))) {
				startDateValidationMessage = date + " date cannot be before " + dateFormat.format(compareDate);
				break;
			}
		}
		return startDateValidationMessage;
	}

	private Date getUpdatedDate(Date date) {
		Calendar trCStDate = Calendar.getInstance();
		trCStDate.setTime(date);
		trCStDate.set(trCStDate.get(Calendar.YEAR), trCStDate.get(Calendar.MONTH), trCStDate.get(Calendar.DATE), 0, 0,
				0);
		return trCStDate.getTime();
	}
}
