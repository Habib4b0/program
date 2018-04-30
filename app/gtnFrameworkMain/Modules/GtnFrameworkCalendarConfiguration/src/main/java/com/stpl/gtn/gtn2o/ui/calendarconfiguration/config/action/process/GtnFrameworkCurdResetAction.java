/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.calendarconfiguration.constants.GtnFrameworkCalendarConfigurationContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.calendarconfiguration.constants.GtnWsCalendarConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.calendarconfiguration.GtnWsCalendarConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.calendarconfiguration.GtnWsCalendarConfigurationResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkCurdResetAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCurdResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		try {
			GtnUIFrameworkBaseComponent viewBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(1).toString());
			List<Object> customDataList = viewBaseComponent.getComponentData().getCustomDataList();
			
			gtnLogger.info("CustomDataList = " + customDataList);
			
			String calendarName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			String calendarDesc = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			Object calendarYear = null;
			Boolean defaultHolidays = Boolean.FALSE;
			Object country = null;
			List<Date> holidayList = new ArrayList<>();	
			if (customDataList != null && !customDataList.isEmpty() && customDataList.get(0) != null) {
				gtnLogger.info("CustomDataList = " + customDataList);
				GtnWsRecordBean tableBean = (GtnWsRecordBean) customDataList.get(0);
				gtnLogger.info("Data = " + tableBean);
				calendarName = tableBean.getStringPropertyByIndex(0);
				calendarDesc = tableBean.getStringPropertyByIndex(1);
				calendarYear = tableBean.getStringPropertyByIndex(2);
				defaultHolidays = tableBean.getBooleanPropertyByIndex(8);
				country = tableBean.getPropertyValueByIndex(9);
				holidayList = getDefaultHolidays(tableBean.getIntegerPropertyByIndex(7));
				if (customDataList.size() > 1) {
					customActionBasedOnType(customDataList, tableBean);
				}
			}
			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
			resetActionConfig.addActionParameter(gtnUIFrameWorkActionConfig.getFieldValues());
			resetActionConfig.addActionParameter(Arrays.asList(calendarName, calendarDesc, new ArrayList<>(), country,
					calendarYear, defaultHolidays));

			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
			GtnUIFrameworkBaseComponent calendarComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCalendarConfigurationContants.CC_CALENDAR_FIELD, componentId);
			calendarComponent.setPropertyValue(holidayList);
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkCurdResetAction", e);
		}
	}

	private void customActionBasedOnType(List<Object> customDataList, GtnWsRecordBean tableBean) {
		if (customDataList.get(1).equals("copy")) {
			tableBean.getProperties().set(7, 0);
		}

	}

	private List<Date> getDefaultHolidays(int calendarId) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCalendarConfigurationRequest ccRequest = new GtnWsCalendarConfigurationRequest();
		ccRequest.setCalendarId(calendarId);
		request.setCalendarConfigurationRequest(ccRequest);
		GtnUIFrameworkWebserviceResponse responce = wsclient.callGtnWebServiceUrl(
				GtnWsCalendarConfigurationConstants.GTN_CALENDAR_CONFIGURATION_SERVICE
						+ GtnWsCalendarConfigurationConstants.LOAD_CALENDAR_CONFIGURATION,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsCalendarConfigurationResponse ccResponse = responce.getCalendarConfigurationResponse();
		return ccResponse.getHolidays();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
