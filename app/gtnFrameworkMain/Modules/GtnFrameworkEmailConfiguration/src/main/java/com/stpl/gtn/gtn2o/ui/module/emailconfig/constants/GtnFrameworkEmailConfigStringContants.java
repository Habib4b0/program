/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.emailconfig.constants;

import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigDefaultDataLoadAction;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigSaveAction;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigViewAction;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigurationValidation;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailNotificationSaveAction;

public final class GtnFrameworkEmailConfigStringContants {

	private GtnFrameworkEmailConfigStringContants() {
	}

	private static final String[] SMTP_COMBOBOX_DATALOAD = { "Yes", "No" };

	public static final String VALIDATION_MESSAGE = "Validation Error :";
	public static final String EMPTY_STRING = "";
	public static final String SEVENTY_FIVE_PERCENTAGE = "75%";
	public static final String ONE_TWENTY_PERCENTAGE = "120%";
	public static final String HUNDRED_PERCENTAGE = "100%";
	public static final String ONE_THIRTY_PERCENTAGE = "130%";
	public static final String STPL_MARGIN_TOP_14= "stpl-margin-top-14";
	
	public static final String FAILURE_MAIL_NOTIFICATION_VERTICAL_LAYOUT_LEFT = "failureMailNotificationVerticalLayoutLeft";
	public static final String FAILURE_MAIL_NOTIFICATION_VERTICAL_LAYOUT_RIGHT = "failureMailNotificationVerticalLayoutRight";
	public static final String SUCCESS_MAIL_NOTIFICATION_VERTICAL_LAYOUT_LEFT = "successMailNotificationVerticalLayoutLeft";
	public static final String SUCCESS_MAIL_NOTIFICATION_VERTICAL_LAYOUT_RIGHT = "successMailNotificationVerticalLayoutRight";
			
	public static final String DATA_LOAD_ACTION = GtnFrameworkEmailConfigDefaultDataLoadAction.class.getName();
	public static final String EMAIL_CONFIG_SAVE_ACTION = GtnFrameworkEmailConfigSaveAction.class.getName();
	public static final String EMAIL_CONFIG_VIEW_ACTION = GtnFrameworkEmailConfigViewAction.class.getName();
	public static final String EMAIL_NOTIFICATION_SAVE_ACTION = GtnFrameworkEmailNotificationSaveAction.class.getName();
	public static final String EMAIL_CONFIGURATION_VALIDATION = GtnFrameworkEmailConfigurationValidation.class
			.getName();

	public static final String GTN_MAIL_CONFIG_CONFIRMATION_MSG = "Confirmation";
	public static final String GTN_MAIL_CONFIG_CONFIRMATION_MSG_SAVE = "Are you sure you want to Save the Configuration?";

	public static final String GTN_MAIL_CONFIG_EMAIL_SMTP_MISSING = "Please select SMTP";
	public static final String GTN_MAIL_CONFIG_EMAIL_HOST_NAME_MISSING = "Please Enter HostName";
	public static final String GTN_MAIL_CONFIG_EMAIL_ADDRESS_MISSING = "Please Enter Email Address";
	public static final String GTN_MAIL_CONFIG_EMAIL_PORT_NO_MISSING = "Please Enter Port No";
	public static final String GTN_MAIL_CONFIG_TEST_EMAIL_ADDRESS_MISSING = "Please Enter Test Email Address";

	public static final String GTN_MAIL_NOTIFICATION_SUCCESS_EMAIL_MISSING = "Please enter  Email Address for Email To";
	public static final String GTN_MAIL_NOTIFICATION_SUCCESS_EMAIL_SUBJECT_MISSING = "Please enter  Subject in Success Mail Notification";
	public static final String GTN_MAIL_NOTIFICATION_SUCCESS_EMAIL_BODY_MISSING = "Please enter Email Body in Success Mail Notification";
	public static final String GTN_MAIL_NOTIFICATION_FAILURE_EMAIL_MISSING = "Please enter  Email Address for Failure To";
	public static final String GTN_MAIL_NOTIFICATION_FAILURE_EMAIL_SUBJECT_MISSING = "Please enter Subject in Failure Mail Notification";
	public static final String GTN_MAIL_NOTIFICATION_FAILURE_EMAIL_BODY_MISSING = "Please enter Email Body  in Failure Mail Notification";

	public static String[] getSmtpComboBoxDataLoad() {
		return SMTP_COMBOBOX_DATALOAD.clone();
	}
}
