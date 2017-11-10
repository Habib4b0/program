package com.stpl.gtn.gtn2o.ui.module.emailconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.emailconfig.constants.GtnWsEMailConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkEmailConfigSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkEmailConfigSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsMailConfigurationRequest request = (GtnWsMailConfigurationRequest) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(1);
		saveMailConfiguration(request, componentId);
	}

	private void saveMailConfiguration(GtnWsMailConfigurationRequest mcRequest, String componentId)
			throws GtnFrameworkGeneralException {
		logger.debug("Entering saveMailConfiguration");
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();

		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		request.setGtnWsGeneralRequest(generalRequest);
		request.setMailConfigurationRequest(mcRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsEMailConfigurationConstants.MAIL_CONFIG_SAVE_ACTION_SAVE
						+ GtnWsEMailConfigurationConstants.SAVE_MAIL_CONF,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		boolean successValue = response.getGtnWsMailConfigurationResponse().isSuccess();
		if (successValue) {
			GtnUIFrameWorkActionConfig notificationConfig = new GtnUIFrameWorkActionConfig();
			notificationConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			notificationConfig.addActionParameter("Successfully Saved");
			notificationConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			notificationConfig.addActionParameter(0);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, notificationConfig);

		}

	}

	@Override
	public GtnFrameworkEmailConfigSaveAction createInstance() {
		return new GtnFrameworkEmailConfigSaveAction();
	}

}
