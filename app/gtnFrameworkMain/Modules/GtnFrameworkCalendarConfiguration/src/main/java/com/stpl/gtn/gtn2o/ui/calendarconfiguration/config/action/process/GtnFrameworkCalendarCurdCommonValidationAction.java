package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.calendarconfiguration.constants.GtnFrameworkCalendarConfigurationContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.calendarconfiguration.constants.GtnWsCalendarConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.calendarconfiguration.GtnWsCalendarConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.calendarconfiguration.GtnWsCalendarConfigurationResponse;

public class GtnFrameworkCalendarCurdCommonValidationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCalendarCurdCommonValidationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Entering inside configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> param = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnWsCalendarConfigurationRequest calendarConfigurationRequest = new GtnWsCalendarConfigurationRequest();
		String calendarNameValidate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(param.get(0)).getStringFromField();
		String calendarDescValidate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(param.get(1)).getStringFromField();
		List<Date> holidayListValidate = (List<Date>) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(param.get(2)).getV8CalenderValue();
		int countryCodeValidate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(param.get(3)).getIntegerFromField();
		String countryValidate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(param.get(3)).getCaptionFromComboBox();
		int calendarYearValidate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(param.get(4)).getIntegerFromField();
		boolean defaultHolidaysValidate = Boolean.parseBoolean(
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(param.get(5)).getStringFromField());
		calendarConfigurationRequest.setCalendarName(calendarNameValidate);
		calendarConfigurationRequest.setCalendarDescription(calendarDescValidate);
		calendarConfigurationRequest.setCalendarYear(calendarYearValidate);
		calendarConfigurationRequest.setDefaultHolidays(defaultHolidaysValidate);
		calendarConfigurationRequest.setCountry(countryValidate);
		calendarConfigurationRequest.setCountryCode(countryCodeValidate);
		calendarConfigurationRequest.setHolidays(holidayListValidate);
		calendarConfigurationRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkBaseComponent viewBaseCalendarComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString());
		List<Object> customCalendarDataList = viewBaseCalendarComponent.getComponentData().getCustomDataList();
		if (customCalendarDataList != null && customCalendarDataList.get(0) != null) {
			GtnWsRecordBean tableBean = (GtnWsRecordBean) customCalendarDataList.get(0);
			calendarConfigurationRequest.setCalendarId(tableBean.getIntegerPropertyByIndex(7));
		}
		GtnUIFrameworkWebServiceClient webServiceClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest wsRequest = new GtnUIFrameworkWebserviceRequest();
		wsRequest.setCalendarConfigurationRequest(calendarConfigurationRequest);
		GtnUIFrameworkWebserviceResponse wsResponse = getResponse(webServiceClient, wsRequest);
		GtnWsCalendarConfigurationResponse calendarConfigurationResponse = wsResponse.getCalendarConfigurationResponse();
		boolean calendarNameAlreadyExists = calendarConfigurationResponse.isCalendarNameExists();
		if (calendarNameAlreadyExists) {
			throw new GtnFrameworkValidationFailedException(GtnFrameworkCalendarConfigurationContants.CALENDAR_NAME_EXISTS, componentId);
		}
		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);
	}

	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebServiceClient webServiceClient,
			GtnUIFrameworkWebserviceRequest wsRequest) {
		return webServiceClient.callGtnWebServiceUrl(
				GtnWsCalendarConfigurationConstants.GTN_CALENDAR_CONFIGURATION_SERVICE
						+ GtnWsCalendarConfigurationConstants.LOAD_CALENDAR_CONFIGURATION_CALENDAR_NAME,
				wsRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkCalendarCurdCommonValidationAction();
	}

}
