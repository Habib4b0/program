package com.stpl.gtn.gtn2o.ui.module.emailconfig.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.emailconfig.constants.GtnWsEMailConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkEmailConfigDefaultDataLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkEmailConfigDefaultDataLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		getDefaultValue(componentId);
	}

	private void getDefaultValue(String componentId) {
		try {
			final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsMailConfigurationRequest mcRequest = new GtnWsMailConfigurationRequest();

			request.setMailConfigurationRequest(mcRequest);
			GtnUIFrameworkWebserviceResponse response = callMailConfigSaveAction(wsclient, request);
			GtnWsEMailConfigurationBean bean = response.getGtnWsMailConfigurationResponse().geteMailConfigurationBean();
			List<Object[]> defaultValue = bean.getDefaultDataLoad();

			if (!defaultValue.isEmpty()) {
				Object[] valueList = defaultValue.get(0);
				List<String> fieldList = Arrays.asList("EmailConfigTabHostName", "EmailConfigTabEmailAddress",
						"EmailConfigTabPassword", "EmailConfigPortNumber", "EmailConfigTabSMTP",
						"EmailConfigTabTestMailAddress");
				for (int i = 0; i < fieldList.size(); i++) {
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldList.get(i), componentId)
							.loadFieldValue(valueList[i + 1]);
				}

			} else {
				logger.info("Data is not available");

			}

		} catch (Exception ex) {
			logger.error("The error is " + ex);
		}
	}

    public GtnUIFrameworkWebserviceResponse callMailConfigSaveAction(final GtnUIFrameworkWebServiceClient wsclient, final GtnUIFrameworkWebserviceRequest request) {
         return  wsclient.callGtnWebServiceUrl(
                GtnWsEMailConfigurationConstants.MAIL_CONFIG_SAVE_ACTION_SAVE
                        + GtnWsEMailConfigurationConstants.GET_DEFAULT_VALUE,
                request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());       
    }

	@Override
	public GtnFrameworkEmailConfigDefaultDataLoadAction createInstance() {
		return this;
	}

}
