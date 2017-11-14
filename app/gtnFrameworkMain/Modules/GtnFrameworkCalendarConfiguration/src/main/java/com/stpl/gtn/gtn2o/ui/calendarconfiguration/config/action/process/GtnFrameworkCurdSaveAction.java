/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.calendarconfiguration.constants.GtnWsCalendarConfigurationConstants;
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
public class GtnFrameworkCurdSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCurdSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> parameters = gtnUIFrameWorkActionConfig.getFieldValues();
		try {
			GtnWsCalendarConfigurationRequest ccRequest = new GtnWsCalendarConfigurationRequest();
			String calendarName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(0)).getStringFromField();
			String calendarDesc = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1)).getStringFromField();
			List<Date> holidayList = (List<Date>) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2))
					.getObjectFromField();
			int countryCode = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3)).getIntegerFromField();
			String country = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3)).getCaptionFromComboBox();
			int calendarYear = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4)).getIntegerFromField();
			boolean defaultHolidays = Boolean.parseBoolean(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5)).getStringFromField());

			ccRequest.setCalendarName(calendarName);
			ccRequest.setCalendarDescription(calendarDesc);
			ccRequest.setCalendarYear(calendarYear);
			ccRequest.setDefaultHolidays(defaultHolidays);
			ccRequest.setCountry(country);
			ccRequest.setCountryCode(countryCode);
			ccRequest.setHolidays(holidayList);
			ccRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			GtnUIFrameworkBaseComponent viewBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString());
			List<Object> customDataList = viewBaseComponent.getComponentData().getCustomDataList();
			if (customDataList != null && customDataList.get(0) != null) {
				GtnWsRecordBean tableBean = (GtnWsRecordBean) customDataList.get(0);
				ccRequest.setCalendarId(tableBean.getIntegerPropertyByIndex(7));
			}
			GtnWsCalendarConfigurationResponse ccResponse = saveCalendarConfig(ccRequest);
			if (ccResponse != null && ccResponse.isSuccess()) {
				List<Object> newCustomDataList = new ArrayList<>();
				newCustomDataList.add(ccResponse.getCalendarBean());
				viewBaseComponent.getComponentData().setCustomDataList(newCustomDataList);
				GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.NOTIFICATION_ACTION,
						ccResponse.getMessage(), null);
			}

		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkCurdSaveAction", e);
		}
	}

	private GtnWsCalendarConfigurationResponse saveCalendarConfig(GtnWsCalendarConfigurationRequest ccRequest) {

		GtnUIFrameworkWebserviceResponse responce = null;
		try {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setCalendarConfigurationRequest(ccRequest);
			responce = wsclient.callGtnWebServiceUrl(
					GtnWsCalendarConfigurationConstants.GTN_CALENDAR_CONFIGURATION_SERVICE
							+ GtnWsCalendarConfigurationConstants.SAVE_CALENDAR_CONFIGURATION,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		} catch (Exception e) {
			gtnLogger.error("Exception in saveCalendarConfig", e);
		}
		return responce != null ? responce.getCalendarConfigurationResponse() : null;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
