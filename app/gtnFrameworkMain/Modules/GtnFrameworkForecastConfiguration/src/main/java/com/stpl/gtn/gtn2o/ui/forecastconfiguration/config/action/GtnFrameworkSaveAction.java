/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkValidationFailedException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		try {
			saveForecastConfiguration(componentId, parameters);
		} catch (Exception ex) {
			Logger.getLogger(GtnFrameworkSaveAction.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public GtnWsForecastConfigurationRequest getModifiedRequest(GtnWsForecastConfigurationRequest fcRequest,
			final List<Object> parameters) throws GtnFrameworkValidationFailedException, ParseException {
		fcRequest.setUserId(Integer.parseInt(GtnUIFrameworkGlobalUI.getCurrentUser()));
		int futureFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
				.getIntegerFromField();
		String futureInterval = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
				.getStringFromField();
		int historyFrequency = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString())
				.getIntegerFromField();
		String historyInterval = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString())
				.getStringFromField();
		String modeValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(14).toString())
				.getStringFromField();
		String processType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(8).toString())
				.getStringFromField();
		String historyfrequencyCaption = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("FCView_dataFrequency")
				.getCaptionFromComboBox();
		Date toDate;
		Date fromDate;
		if ("Period".equals(modeValue)) {
			toDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString()).getDateFromDateField();
			fromDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString())
					.getDateFromDateField();
		} else {

			String fromDateString = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(12).toString())
					.getStringFromField();
			String toDateString = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(13).toString())
					.getStringFromField();
			String[] splitFromDate = fromDateString.split(" ");
			String[] splitToDate = toDateString.split(" ");
			String frommonth;
			String tomonth;
			Calendar cal = Calendar.getInstance();
			Calendar cal1 = Calendar.getInstance();
			Date fromdateInterval;
			Date todateInterval;
			if (splitFromDate[0].startsWith("Q") && "Quarter".equals(historyfrequencyCaption)
					&& !GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_AUTO_UPDATE.equals(processType)) {
				LOGGER.info("processType-in first if--" + processType);
				String fromMonthName = splitFromDate[0].substring(1, 2);
				frommonth = returnQuarterMonth(fromMonthName);
				String toMonthName = splitToDate[0].substring(1, 2);
				tomonth = returnQuarterMonth(toMonthName);
				fromdateInterval = new SimpleDateFormat("MMMM").parse(frommonth);
				cal.setTime(fromdateInterval);
				cal.set(Integer.parseInt(splitFromDate[1]), cal.get(Calendar.MONTH), 1);
				todateInterval = new SimpleDateFormat("MMMM").parse(tomonth);
				cal1.setTime(todateInterval);
				cal1.set(Integer.parseInt(splitToDate[1]), cal1.get(Calendar.MONTH), 1);
			}
			if (splitFromDate[0].startsWith("Q") && "Quarter".equals(historyfrequencyCaption)
					&& GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_AUTO_UPDATE.equals(processType)) {
				LOGGER.info("processType---" + processType);
				String fromMonthName = splitFromDate[0].substring(1, 2);
				frommonth = returnQuarterMonth(fromMonthName);
				fromdateInterval = new SimpleDateFormat("MMMM").parse(frommonth);
				cal.setTime(fromdateInterval);
				cal.set(Integer.parseInt(splitFromDate[1]), cal.get(Calendar.MONTH), 1);
				todateInterval = new SimpleDateFormat("MMMM").parse(splitToDate[0]);
				cal1.setTime(todateInterval);
				cal1.set(Integer.parseInt(splitToDate[1]), cal1.get(Calendar.MONTH), 1);
			} else if (splitFromDate[0].startsWith("S") && "Semi-Annual".equals(historyfrequencyCaption)
					&& !GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_AUTO_UPDATE.equals(processType)) {
				String fromMonthName = splitFromDate[0].substring(1, 2);
				frommonth = returnSemmiAnnualMonth(fromMonthName);
				String toMonthName = splitToDate[0].substring(1, 2);
				tomonth = returnSemmiAnnualMonth(toMonthName);
				fromdateInterval = new SimpleDateFormat("MMMM").parse(frommonth);
				cal.setTime(fromdateInterval);
				cal.set(Integer.parseInt(splitFromDate[1]), cal.get(Calendar.MONTH), 1);
				todateInterval = new SimpleDateFormat("MMMM").parse(tomonth);
				cal1.setTime(todateInterval);
				cal1.set(Integer.parseInt(splitToDate[1]), cal1.get(Calendar.MONTH), 1);
			} else if (splitFromDate[0].startsWith("S") && "Semi-Annual".equals(historyfrequencyCaption)
					&& GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_AUTO_UPDATE.equals(processType)) {
				String fromMonthName = splitFromDate[0].substring(1, 2);
				frommonth = returnSemmiAnnualMonth(fromMonthName);
				fromdateInterval = new SimpleDateFormat("MMMM").parse(frommonth);
				cal.setTime(fromdateInterval);
				cal.set(Integer.parseInt(splitFromDate[1]), cal.get(Calendar.MONTH), 1);
				todateInterval = new SimpleDateFormat("MMMM").parse(splitToDate[0]);
				cal1.setTime(todateInterval);
				cal1.set(Integer.parseInt(splitToDate[1]), cal1.get(Calendar.MONTH), 1);
			}
			List<String> inputParameters = new ArrayList<>(4);
			inputParameters.add(historyfrequencyCaption);
			inputParameters.add(processType);
			inputParameters.add(fromDateString);
			inputParameters.add(toDateString);
			annuallyConfiguration(inputParameters, cal, cal1, splitFromDate, splitToDate);
			fromDate = cal.getTime();
			toDate = cal1.getTime();
		}
		int businessProcess = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(7).toString())
				.getIntegerFromField();

		String mode = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(9).toString()).getStringFromField();

		fcRequest.setBusinessProcess(businessProcess);
		fcRequest.setFromDate(fromDate);
		fcRequest.setToDate(toDate);
		fcRequest.setMode(mode);
		fcRequest.setProcessType(processType);
		fcRequest.setHistoryFrequency(historyFrequency);
		fcRequest.setHistoryInterval(historyInterval);
		fcRequest.setFutureFrequency(futureFrequency);
		fcRequest.setFutureInterval(futureInterval);
		return fcRequest;
	}

	private void annuallyConfiguration(List<String> inputParameters, Calendar cal, Calendar cal1,
			String[] splitFromDate, String[] splitToDate) throws ParseException {
		String historyfrequencyCaption = inputParameters.get(0);
		String processType = inputParameters.get(1);
		String fromDateString = inputParameters.get(2);
		String toDateString = inputParameters.get(3);

		Date fromdateInterval;
		Date todateInterval;
		if ("Annual".equals(historyfrequencyCaption)
				&& !GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_AUTO_UPDATE.equals(processType)) {
			cal.set(Integer.parseInt(fromDateString), 0, 1);
			cal1.set(Integer.parseInt(toDateString), 0, 1);
		} else if ("Annual".equals(historyfrequencyCaption)
				&& GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_AUTO_UPDATE.equals(processType)) {
			cal.set(Integer.parseInt(fromDateString), 0, 1);
			todateInterval = new SimpleDateFormat("MMMM").parse(splitToDate[0]);
			cal1.setTime(todateInterval);
			cal1.set(Integer.parseInt(splitToDate[1]), cal1.get(Calendar.MONTH), 1);
		} else if ("Month".equals(historyfrequencyCaption)) {
			fromdateInterval = new SimpleDateFormat("MMMM").parse(splitFromDate[0]);
			cal.setTime(fromdateInterval);
			cal.set(Integer.parseInt(splitFromDate[1]), cal.get(Calendar.MONTH), 1);
			todateInterval = new SimpleDateFormat("MMMM").parse(splitToDate[0]);
			cal1.setTime(todateInterval);
			cal1.set(Integer.parseInt(splitToDate[1]), cal1.get(Calendar.MONTH), 1);
		}
	}

	private void saveForecastConfiguration(String componentId, List<Object> parameters)
			throws GtnFrameworkValidationFailedException, ParseException {
		try {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsForecastConfigurationRequest fcRequest = new GtnWsForecastConfigurationRequest();
			getModifiedRequest(fcRequest, parameters);
			request.setForecastConfigurationRequest(fcRequest);
			GtnUIFrameworkWebserviceResponse response = getResponse(wsclient, request);
			GtnWsForecastConfigurationResponse fcResponse = response.getGtnWsForecastConfigurationResponse();
			GtnUIFrameworkBaseComponent errorLabelBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(11).toString());
			errorLabelBaseComponent.setVisible(false);
			if (fcResponse.isSuccess()) {
				confirmSaveForecastConfiguration(componentId, request, parameters.get(10).toString());
				return;
			}
			if (GtnFrameworkCommonStringConstants.VALIDATION.equals(fcResponse.getMessageType())) {
				errorLabelBaseComponent.setVisible(true);
				errorLabelBaseComponent.setPropertyValue(fcResponse.getMessage());
				return;
			}
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
					fcResponse.getMessageType(), fcResponse.getMessage());
		} catch (Exception e) {
			LOGGER.info("**************************");
		}
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebServiceClient wsclient,
			GtnUIFrameworkWebserviceRequest request) {
		return wsclient.callGtnWebServiceUrl(
				GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE
						+ GtnWsForecastConfigurationConstants.CHECK_SAVE_FORECAST_CONF,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	private void confirmSaveForecastConfiguration(String componentId, GtnUIFrameworkWebserviceRequest request,
			String tableId) throws GtnFrameworkValidationFailedException {
		try {
			GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig();
			saveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			saveActionConfig.addActionParameter(GtnFrameworkConfirmSaveAction.class.getName());
			saveActionConfig.addActionParameter(request);
			saveActionConfig.addActionParameter(tableId);
			GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
			confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
			confirmActionConfig.addActionParameter("Save Confirmation");
			confirmActionConfig.addActionParameter("Save record ?");

			confirmActionConfig.addActionParameter(Arrays.asList(saveActionConfig));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);
		} catch (Exception e) {
			LOGGER.info("**************************");
		}
	}

	private static String returnQuarterMonth(String monthname) {
		String month = monthname;
		switch (month) {
		case "1":
			month = "Jan";
			break;
		case "2":
			month = "Apr";
			break;
		case "3":
			month = "Jul";
			break;
		case "4":
			month = "Oct";
			break;
		default:
			LOGGER.info("default case============");
		}
		return month;
	}

	private static String returnSemmiAnnualMonth(String monthname) {
		String month = monthname;
		if ("1".equals(month)) {
			month = "Jan";
		}
		if ("2".equals(month)) {
			month = "Jul";
		}
		return month;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
