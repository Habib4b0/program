package com.stpl.gtn.gtn2o.ui.module.emailconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.emailconfig.constants.GtnWsEMailConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkEmailNotificationSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		saveMailNotification(parameters, componentId);
	}

	public GtnWsEMailConfigurationBean getModifiedRequest(GtnWsEMailConfigurationBean bean,
			final List<Object> parameters) {
		String addProcessName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
				.getStringFromField();
		String addEmailTo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
				.getStringFromField();
		String addSubject = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString())
				.getStringFromField();
		String addEmailCc = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString())
				.getStringFromField();
		String addEmailBody = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString())
				.getStringFromField();
		String addFailureTo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString())
				.getStringFromField();
		String addFailureSubject = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(7).toString())
				.getStringFromField();
		String addFailureEmailCc = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(8).toString())
				.getStringFromField();
		String addFailureEmailBody = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(9).toString())
				.getStringFromField();

		bean.setEmailNotificationTabProcessName(addProcessName);
		bean.setEmailNotificationTabEmailBody(addEmailBody);
		bean.setEmailNotificationTabFailureEmailBody(addFailureEmailBody);
		bean.setEmailNotificationTabEmailCc(emailIdCommaReplacedWithSpace(addEmailCc));
		bean.setEmailNotificationTabFailureEmailCc(emailIdCommaReplacedWithSpace(addFailureEmailCc));
		bean.setEmailNotificationTabEmailTo(emailIdCommaReplacedWithSpace(addEmailTo));
		bean.setEmailNotificationTabFailureTo(emailIdCommaReplacedWithSpace(addFailureTo));
		bean.setEmailNotificationTabSubject(addSubject);
		bean.setEmailNotificationTabFailureSubject(addFailureSubject);

		return bean;
	}

	public void saveMailNotification(final List<Object> parameters, String componentId)
			throws GtnFrameworkGeneralException {
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();

		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		GtnFrameworkEmailNotificationValidation gtnFrameworkEmailNotificationValidation = new GtnFrameworkEmailNotificationValidation();
		GtnWsMailConfigurationRequest mcRequest = new GtnWsMailConfigurationRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnWsEMailConfigurationBean bean = new GtnWsEMailConfigurationBean();
		getModifiedRequest(bean, parameters);

		gtnFrameworkEmailNotificationValidation.validationForNotification(bean, componentId);
		mcRequest.setConfigurationBean(bean);
		request.setGtnWsGeneralRequest(generalRequest);
		request.setMailConfigurationRequest(mcRequest);
		GtnUIFrameworkWebserviceResponse response = callMailConfigSaveAction(wsclient, request);
		boolean successValue = response.getGtnWsMailConfigurationResponse().isNotificationSuccess();

		if (successValue) {

			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
			List<Object> params = new ArrayList<>();
			params.add(Arrays.asList(parameters.get(2).toString(), parameters.get(3).toString(),
					parameters.get(4).toString(), parameters.get(5).toString(), parameters.get(6).toString(),
					parameters.get(7).toString(), parameters.get(8).toString(), parameters.get(9).toString(),
					parameters.get(1).toString()));
			params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, null));
			resetActionConfig.setActionParameterList(params);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
		}
	}

    public GtnUIFrameworkWebserviceResponse callMailConfigSaveAction(final GtnUIFrameworkWebServiceClient wsclient, final GtnUIFrameworkWebserviceRequest request) {
         return  wsclient.callGtnWebServiceUrl(
                GtnWsEMailConfigurationConstants.MAIL_CONFIG_SAVE_ACTION_SAVE
                        + GtnWsEMailConfigurationConstants.SAVE_MAIL_NOTIFICATION,
                request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());      
    }

	public String emailIdCommaReplacedWithSpace(String inputMaildId) {
		String replacedCommaWithSpaceString = "";
		String[] tempInput = null;
		String tempMailId = inputMaildId;
		if (tempMailId.contains(",")) {
			tempInput = tempMailId.split(",");
		} else {
			replacedCommaWithSpaceString = tempMailId;
		}
		if (tempInput != null && tempInput.length != 0) {
			for (int i = 0; i < tempInput.length; i++) {
				replacedCommaWithSpaceString = (i != 0) ? replacedCommaWithSpaceString + " " + tempInput[i].trim() : tempInput[i].trim();
			}
		}
		return replacedCommaWithSpaceString;
	}

	@Override
	public GtnFrameworkEmailNotificationSaveAction createInstance() {
		return this;
	}

}
