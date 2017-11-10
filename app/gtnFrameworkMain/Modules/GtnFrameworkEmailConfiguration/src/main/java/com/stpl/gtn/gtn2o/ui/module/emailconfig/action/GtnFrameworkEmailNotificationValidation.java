/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.emailconfig.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.constants.GtnFrameworkEmailConfigStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnFrameworkEmailNotificationValidation implements GtnUIFrameworkDynamicClass {

	public void validationForNotification(GtnWsEMailConfigurationBean configurationBean, String componentId)
			throws GtnFrameworkGeneralException {

		final GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();

		final GtnUIFrameWorkAlertAction alertAction = new GtnUIFrameWorkAlertAction();

		if (configurationBean.getEmailNotificationTabEmailTo() == null
				|| configurationBean.getEmailNotificationTabEmailTo().isEmpty()) {
			Object emailMissingMsg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_NOTIFICATION_SUCCESS_EMAIL_MISSING;
			alertActionConfig
					.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, emailMissingMsg));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCommonConstants.VALIDATION_ERROR + emailMissingMsg);
		}

		if (configurationBean.getEmailNotificationTabSubject() == null
				|| configurationBean.getEmailNotificationTabSubject().isEmpty()) {
			Object emailSubMissingMsg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_NOTIFICATION_SUCCESS_EMAIL_SUBJECT_MISSING;
			alertActionConfig
					.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, emailSubMissingMsg));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCommonConstants.VALIDATION_ERROR + emailSubMissingMsg);

		}
		if (configurationBean.getEmailNotificationTabEmailBody() == null
				|| configurationBean.getEmailNotificationTabEmailBody().isEmpty()) {
			Object emailBodyMissingMsg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_NOTIFICATION_SUCCESS_EMAIL_BODY_MISSING;
			alertActionConfig.setActionParameterList(
					Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, emailBodyMissingMsg));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCommonConstants.VALIDATION_ERROR + emailBodyMissingMsg);
		}
		if (configurationBean.getEmailNotificationTabFailureTo() == null
				|| configurationBean.getEmailNotificationTabFailureTo().isEmpty()) {
			Object emailFailMsg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_NOTIFICATION_FAILURE_EMAIL_MISSING;
			alertActionConfig
					.setActionParameterList(Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, emailFailMsg));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCommonConstants.VALIDATION_ERROR + emailFailMsg);
		}
		if (configurationBean.getEmailNotificationTabFailureSubject() == null
				|| configurationBean.getEmailNotificationTabFailureSubject().isEmpty()) {
			Object emailFailSubMissingMsg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_NOTIFICATION_FAILURE_EMAIL_SUBJECT_MISSING;
			alertActionConfig.setActionParameterList(
					Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, emailFailSubMissingMsg));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCommonConstants.VALIDATION_ERROR + emailFailSubMissingMsg);
		}
		if (configurationBean.getEmailNotificationTabFailureEmailBody() == null
				|| configurationBean.getEmailNotificationTabFailureEmailBody().isEmpty()) {
			Object emailFailBodyMissingMsg = GtnFrameworkEmailConfigStringContants.GTN_MAIL_NOTIFICATION_FAILURE_EMAIL_BODY_MISSING;
			alertActionConfig.setActionParameterList(
					Arrays.asList(GtnFrameworkCommonStringConstants.ERROR, emailFailBodyMissingMsg));
			alertAction.doAction(componentId, alertActionConfig);
			throw new GtnFrameworkValidationFailedException(
					GtnFrameworkCommonConstants.VALIDATION_ERROR + emailFailBodyMissingMsg);

		}
	}
}
