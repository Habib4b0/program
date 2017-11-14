/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.emailconfig.constants;

public class GtnWsEMailConfigurationConstants {
    private GtnWsEMailConfigurationConstants(){
        /**
         * empty constructor
         */
    }

	public static final String MAIL_CONFIG_SAVE_ACTION_SAVE = "/GtnMailConfigurationService";
	public static final String SAVE_MAIL_CONF = "/checkSaveMailConfiguration";
	public static final String SAVE_MAIL_NOTIFICATION = "/checkSaveMailNotification";

	public static final String MAIL_CONFIG_ONLOAD_EMAIL_ADDRESS = "/GtnMailConfigurationEmailAdress";
	public static final String MAIL_CONFIG_ONLOAD_PORT = "/GtnMailConfigurationPort";
	public static final String MAIL_CONFIG_ONLOAD_SMTP = "/GtnMailConfigurationSMTP";
	public static final String MAIL_CONFIG_ONLOAD_TEST_EMAIL_ADDRESS = "/GtnMailConfigurationTestEmailAddress";
	public static final String GET_DEFAULT_VALUE = "/GtnMailConfigurationDefault";
	public static final String MAIL_CONFIG_COMBOBOX_ONCHANGE = "/GtnMailConfigurationComboboxOnchange";
	public static final String SUCCESS_MESSAGE_AFTER_SAVE = "Successfully saved !";

}
