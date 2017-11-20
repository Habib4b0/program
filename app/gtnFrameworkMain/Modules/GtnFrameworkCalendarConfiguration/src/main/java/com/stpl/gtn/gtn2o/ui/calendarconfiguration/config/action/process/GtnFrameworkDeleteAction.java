/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import java.util.List;

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
public class GtnFrameworkDeleteAction implements GtnUIFrameWorkAction  ,GtnUIFrameworkDynamicClass{

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDeleteAction.class);

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
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(1).toString());
			GtnWsRecordBean tableBean = (GtnWsRecordBean) tableBaseComponent.getValueFromComponent();
			GtnWsCalendarConfigurationRequest ccRequest = new GtnWsCalendarConfigurationRequest();
			ccRequest.setCalendarId(tableBean.getIntegerPropertyByIndex(7));
			ccRequest.setCalendarName(tableBean.getStringPropertyByIndex(0));
			GtnWsCalendarConfigurationResponse ccResponse = deleteCalendarConfig(ccRequest);
			if (ccResponse.isSuccess()) {
				GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.NOTIFICATION_ACTION,
						ccResponse.getMessage(), null);
				tableBaseComponent.setTableValue(null);
				loadTable(componentId, parameters.get(1).toString(), gtnUIFrameWorkActionConfig.getFieldValues());
				return;
			}
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION,
					ccResponse.getMessage(), ccResponse.getMessage());
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkDeleteAction", e);
		}
	}

	private void loadTable(String componentId, String tableId, List<String> componentIdList)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		tableLoadActionConfig.addActionParameter(tableId);
		tableLoadActionConfig.setFieldValues(componentIdList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, tableLoadActionConfig);
	}

	private GtnWsCalendarConfigurationResponse deleteCalendarConfig(GtnWsCalendarConfigurationRequest ccRequest) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setCalendarConfigurationRequest(ccRequest);
		GtnUIFrameworkWebserviceResponse responce = wsclient.callGtnWebServiceUrl(
				GtnWsCalendarConfigurationConstants.GTN_CALENDAR_CONFIGURATION_SERVICE
						+ GtnWsCalendarConfigurationConstants.DELETE_CALENDAR_CONFIGURATION,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return responce.getCalendarConfigurationResponse();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
