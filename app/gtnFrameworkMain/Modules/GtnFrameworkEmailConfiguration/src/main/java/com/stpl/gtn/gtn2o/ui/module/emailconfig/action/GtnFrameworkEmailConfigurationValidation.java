/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.emailconfig.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.constants.GtnFrameworkEmailConfigStringContants;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;

public class GtnFrameworkEmailConfigurationValidation
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsMailConfigurationRequest request = new GtnWsMailConfigurationRequest();
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
		String emailConfigTabSMTP = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
				.getStringFromField();
		String emailConfigTabHostName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
				.getStringFromField();
		String emailConfigTabEmailAddress = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString())
				.getStringFromField();
		String emailConfigTabPassword = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString())
				.getStringFromField();
		String emailConfigPortNumber = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString())
				.getStringFromField();
		String emailConfigTabTestMailAddress = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(6).toString()).getStringFromField();

		configurationBean.setEmailConfigTabSMTP(emailConfigTabSMTP);
		configurationBean.setEmailConfigTabHostName(emailConfigTabHostName);
		configurationBean.setEmailConfigTabemailAddress(emailConfigTabEmailAddress);
		configurationBean.setEmailConfigTabPassword(emailConfigTabPassword);
		configurationBean.setEmailConfigPortNumber(emailConfigPortNumber);
		configurationBean.setEmailConfigTabTestMailAddress(emailConfigTabTestMailAddress);

		validationForConfig(configurationBean, componentId);
		request.setConfigurationBean(configurationBean);

		saveActionTrigger(request, componentId);
	}

	public void validationForConfig(GtnWsEMailConfigurationBean configurationBean, String componentId)
			throws GtnFrameworkGeneralException {
		String msg;

		if (configurationBean.getEmailConfigTabSMTP() == null || configurationBean.getEmailConfigTabSMTP().isEmpty()) {
			msg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_CONFIG_EMAIL_SMTP_MISSING;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		else if (configurationBean.getEmailConfigTabHostName() == null
				|| configurationBean.getEmailConfigTabHostName().isEmpty()) {
			msg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_CONFIG_EMAIL_HOST_NAME_MISSING;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		} else if (configurationBean.getEmailConfigTabemailAddress() == null
				|| configurationBean.getEmailConfigTabemailAddress().isEmpty()) {
			msg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_CONFIG_EMAIL_ADDRESS_MISSING;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		} else if (configurationBean.getEmailConfigTabPassword() == null
				|| configurationBean.getEmailConfigTabPassword().isEmpty()) {
			msg = "Please Enter Password";
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		} else if (configurationBean.getEmailConfigPortNumber() == null
				|| configurationBean.getEmailConfigPortNumber().isEmpty()) {
			msg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_CONFIG_EMAIL_PORT_NO_MISSING;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		} else if (configurationBean.getEmailConfigTabTestMailAddress() == null
				|| configurationBean.getEmailConfigTabTestMailAddress().isEmpty()) {
			msg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_CONFIG_TEST_EMAIL_ADDRESS_MISSING;
			throw new GtnFrameworkValidationFailedException(msg, componentId);
		}

		GtnUIFrameworkActionExecutor.clearErrorBanner(componentId);

	}

	public void saveActionTrigger(GtnWsMailConfigurationRequest mcRequest, String componentId)
			throws GtnFrameworkGeneralException {

		GtnUIFrameWorkActionConfig customActionForConformation = new GtnUIFrameWorkActionConfig();
		customActionForConformation.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		customActionForConformation
				.addActionParameter(GtnFrameworkEmailConfigStringContants.GTN_MAIL_CONFIG_CONFIRMATION_MSG);
		customActionForConformation
				.addActionParameter(GtnFrameworkEmailConfigStringContants.GTN_MAIL_CONFIG_CONFIRMATION_MSG_SAVE);

		List<GtnUIFrameWorkActionConfig> actionConfigSaveList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customActionForSave = new GtnUIFrameWorkActionConfig();
		customActionForSave.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionForSave.addActionParameter(GtnFrameworkEmailConfigStringContants.EMAIL_CONFIG_SAVE_ACTION);
		customActionForSave.addActionParameter(mcRequest);
		actionConfigSaveList.add(customActionForSave);
		customActionForConformation.addActionParameter(actionConfigSaveList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, customActionForConformation);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
