package com.stpl.gtn.gtn2o.ui.module.emailconfig.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.constants.GtnFrameworkEmailConfigStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkEmailServerTabConfig {

	public void addEMailConfigurationTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkComponentConfig emailConfigurationTabRootLayout = componentConfig
				.getRootLayoutConfig("emailConfigurationTab", GtnUIFrameworkLayoutType.VERTICAL_LAYOUT, true);
		emailConfigurationTabRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(emailConfigurationTabRootLayout);

		GtnUIFrameworkComponentConfig emailConfigurationTabMainLayout = componentConfig.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_MAIN_TAB_LAYOUT, true, "emailConfigurationTab");
		emailConfigurationTabMainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(emailConfigurationTabMainLayout);
		addPanel(componentList, componentConfig);
		addMainLayout(componentList, componentConfig);
	}

	private void addPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig mailServerConfigurationPanel = componentConfig.getPanelConfig(
				"mailServerConfigurationPanel", true, GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_MAIN_TAB_LAYOUT);
		mailServerConfigurationPanel.setComponentName("Mail Server Configuration");
		mailServerConfigurationPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		mailServerConfigurationPanel.setMargin(false);
		mailServerConfigurationPanel.setSpacing(true);
		componentList.add(mailServerConfigurationPanel);

	}

	private void addMainLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig emailConfigurationTab1Layout = componentConfig
				.getCssLayoutConfig("emailConfigurationTab1Layout", true, "mailServerConfigurationPanel");
		emailConfigurationTab1Layout.setComponentWidth(GtnFrameworkCssConstants.FOURTY_FIVE_PERCENTAGE);
		emailConfigurationTab1Layout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6));
		emailConfigurationTab1Layout.setMargin(false);
		emailConfigurationTab1Layout.setSpacing(true);
		componentList.add(emailConfigurationTab1Layout);

		GtnUIFrameworkComponentConfig emailConfigurationTabLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_TAB_LAYOUT, true, "emailConfigurationTab1Layout");
		emailConfigurationTabLayout.setComponentWidth(GtnFrameworkEmailConfigStringContants.SEVENTY_FIVE_PERCENTAGE);
		emailConfigurationTabLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		emailConfigurationTabLayout.setMargin(false);
		emailConfigurationTabLayout.setSpacing(true);
		componentList.add(emailConfigurationTabLayout);

		addFieldComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addSMTPNo(componentList, componentConfig);
		addHostName(componentList, componentConfig);
		addEmailAddress(componentList, componentConfig);
		addPassword(componentList, componentConfig);
		addPortNumber(componentList, componentConfig);
		addTestMailAddress(componentList, componentConfig);
		addSaveButtonLayout(componentList, componentConfig);
	}

	private void addSMTPNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig emailConfigTabSMTPMainLayout = componentConfig.getHorizontalLayoutConfig(
				"EmailConfigTabSMTPMainlayout", true, GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_TAB_LAYOUT);
		componentList.add(emailConfigTabSMTPMainLayout);

		GtnUIFrameworkComponentConfig emailConfigTabSMTPLayout = componentConfig
				.getCssLayoutConfig("EmailConfigTabSMTPlayout", true, "EmailConfigTabSMTPMainlayout");
		componentList.add(emailConfigTabSMTPLayout);

		GtnUIFrameworkComponentConfig smtp = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_SMTP, true, "EmailConfigTabSMTPlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		smtp.setAuthorizationIncluded(true);
		smtp.setComponentName("SMTP");
		smtp.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(smtp);

		GtnUIFrameworkComboBoxConfig smtpConfig = new GtnUIFrameworkComboBoxConfig();
		smtpConfig.setItemValues(Arrays.asList(GtnFrameworkEmailConfigStringContants.getSmtpComboBoxDataLoad()));
		smtp.setGtnComboboxConfig(smtpConfig);

	}

	private void addHostName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig emailConfigTabMainHostNamelayout = componentConfig.getHorizontalLayoutConfig(
				"EmailConfigTabMainHostNamelayout", true, GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_TAB_LAYOUT);
		componentList.add(emailConfigTabMainHostNamelayout);

		GtnUIFrameworkComponentConfig emailConfigTabHostNamelayout = componentConfig
				.getCssLayoutConfig("EmailConfigTabHostNamelayout", true, "EmailConfigTabMainHostNamelayout");
		componentList.add(emailConfigTabHostNamelayout);

		GtnUIFrameworkComponentConfig hostName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_HOST_NAME, true, "EmailConfigTabHostNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		hostName.setAuthorizationIncluded(true);
		hostName.setComponentName("Host Name");
		hostName.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(hostName);

	}

	private void addEmailAddress(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig emailConfigTabMainEmailAddressLayout = componentConfig.getHorizontalLayoutConfig(
				"EmailConfigTabMainEmailAddresslayout", true,
				GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_TAB_LAYOUT);
		componentList.add(emailConfigTabMainEmailAddressLayout);

		GtnUIFrameworkComponentConfig emailConfigTabEmailAddressLayout = componentConfig
				.getCssLayoutConfig("EmailConfigTabEmailAddresslayout", true, "EmailConfigTabMainEmailAddresslayout");
		componentList.add(emailConfigTabEmailAddressLayout);

		GtnUIFrameworkComponentConfig emailAddressConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_EMAIL_ADDRESS, true, "EmailConfigTabEmailAddresslayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		emailAddressConfig.setAuthorizationIncluded(true);
		emailAddressConfig.setComponentName("Email Address");
		emailAddressConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(emailAddressConfig);

	}

	private void addPassword(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig emailConfigTabMainPasswordLayout = componentConfig.getHorizontalLayoutConfig(
				"EmailConfigTabMainPasswordlayout", true, GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_TAB_LAYOUT);
		emailConfigTabMainPasswordLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(emailConfigTabMainPasswordLayout);

		GtnUIFrameworkComponentConfig emailConfigTabPasswordLayout = componentConfig
				.getCssLayoutConfig("EmailConfigTabPasswordlayout", true, "EmailConfigTabMainPasswordlayout");
		componentList.add(emailConfigTabPasswordLayout);

		GtnUIFrameworkComponentConfig emailConfigTabPassword = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_PASS_FIELD, true, "EmailConfigTabPasswordlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		emailConfigTabPassword.setAuthorizationIncluded(true);
		emailConfigTabPassword.setComponentName("Password");
		emailConfigTabPassword.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextBoxConfig emailConfigTabPasswordConfig = new GtnUIFrameworkTextBoxConfig();
		emailConfigTabPasswordConfig.setPasswordField(true);
		emailConfigTabPassword.setGtnTextBoxConfig(emailConfigTabPasswordConfig);
		componentList.add(emailConfigTabPassword);

	}

	private void addPortNumber(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig emailConfigMainPortNumberLayout = componentConfig.getHorizontalLayoutConfig(
				"EmailConfigMainPortNumberlayout", true, GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_TAB_LAYOUT);
		componentList.add(emailConfigMainPortNumberLayout);

		GtnUIFrameworkComponentConfig emailConfigPortNumberLayout = componentConfig
				.getCssLayoutConfig("EmailConfigPortNumberlayout", true, "EmailConfigMainPortNumberlayout");
		componentList.add(emailConfigPortNumberLayout);

		GtnUIFrameworkComponentConfig portNumberConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EMAIL_CONFIG_PORT_NUMBER, true, "EmailConfigPortNumberlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		portNumberConfig.setAuthorizationIncluded(true);
		portNumberConfig.setComponentName("Port Number");
		portNumberConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(portNumberConfig);

	}

	private void addTestMailAddress(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig emailConfigTabMainTestMailAddressLayout = componentConfig
				.getHorizontalLayoutConfig("EmailConfigTabMainTestMailAddresslayout", true,
						GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_TAB_LAYOUT);
		componentList.add(emailConfigTabMainTestMailAddressLayout);

		GtnUIFrameworkComponentConfig emailConfigTabTestMailAddressLayout = componentConfig.getCssLayoutConfig(
				"EmailConfigTabTestMailAddresslayout", true, "EmailConfigTabMainTestMailAddresslayout");
		componentList.add(emailConfigTabTestMailAddressLayout);

		GtnUIFrameworkComponentConfig testMailAddressConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_TEST_MAIL_ADDRESS, true,
				"EmailConfigTabTestMailAddresslayout", GtnUIFrameworkComponentType.TEXTBOX);
		testMailAddressConfig.setAuthorizationIncluded(true);
		testMailAddressConfig.setComponentName("Test Mail Address");
		testMailAddressConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(testMailAddressConfig);
	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig mailConfigsaveButtonLayout = componentConfig.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.MAIL_CONFIGSAVE_BUTTONLAYOUT, true,
				GtnFrameworkCommonConstants.EMAIL_CONFIGURATION_MAIN_TAB_LAYOUT);
		mailConfigsaveButtonLayout.setSpacing(true);
		mailConfigsaveButtonLayout.setMargin(true);
		componentList.add(mailConfigsaveButtonLayout);
		addSaveButtonComponent(componentList, componentConfig);
		addBackButtonComponent(componentList, componentConfig);
		addViewButtonComponent(componentList, componentConfig);
	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig mailConfigBackButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"MailConfigBackButtonLayout", true, GtnFrameworkCommonConstants.MAIL_CONFIGSAVE_BUTTONLAYOUT);
		mailConfigBackButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(mailConfigBackButtonLayout);

		GtnUIFrameworkComponentConfig backButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"EmailConfigBackButton", true, "MailConfigBackButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setAuthorizationIncluded(true);
		backButtonConfig.setComponentName("Back");
		backButtonConfig.setVisible(false);
		componentList.add(backButtonConfig);

		List<GtnUIFrameWorkActionConfig> backActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		backActionConfigList.add(navigationActionConfig);

		backButtonConfig.setGtnUIFrameWorkActionConfigList(backActionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig emailConfigAddSaveButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"EmailConfigAddSaveButtonlayout", true, GtnFrameworkCommonConstants.MAIL_CONFIGSAVE_BUTTONLAYOUT);
		componentList.add(emailConfigAddSaveButtonLayout);

		GtnUIFrameworkComponentConfig emailConfigAddSaveButton = componentConfig.getUIFrameworkComponentConfig(
				"EmailConfigAddSaveButton", true, "EmailConfigAddSaveButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		emailConfigAddSaveButton.setAuthorizationIncluded(true);
		emailConfigAddSaveButton.setComponentName("Save");
		componentList.add(emailConfigAddSaveButton);

		List<GtnUIFrameWorkActionConfig> addActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkEmailConfigStringContants.EMAIL_CONFIGURATION_VALIDATION);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_SMTP);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_HOST_NAME);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_EMAIL_ADDRESS);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_PASS_FIELD);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_PORT_NUMBER);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_TEST_MAIL_ADDRESS);
		addActionConfigList.add(customAction);
		emailConfigAddSaveButton.setGtnUIFrameWorkActionConfigList(addActionConfigList);
	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig mailConfigViewButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"MailConfigViewButtonlayout", true, GtnFrameworkCommonConstants.MAIL_CONFIGSAVE_BUTTONLAYOUT);
		componentList.add(mailConfigViewButtonLayout);

		GtnUIFrameworkComponentConfig viewButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"MailConfigViewButton", true, "MailConfigViewButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		viewButtonConfig.setAuthorizationIncluded(true);
		viewButtonConfig.setComponentName("View");
		componentList.add(viewButtonConfig);

		List<GtnUIFrameWorkActionConfig> viewActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customAction.addActionParameter(GtnFrameworkEmailConfigStringContants.EMAIL_CONFIG_VIEW_ACTION);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_SMTP);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_HOST_NAME);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_EMAIL_ADDRESS);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_PASS_FIELD);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_PORT_NUMBER);
		customAction.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_TEST_MAIL_ADDRESS);
		customAction.addActionParameter("MailConfigViewButton");
		customAction.addActionParameter("EmailConfigAddSaveButton");
		customAction.addActionParameter("EmailConfigBackButton");
		viewActionConfigList.add(customAction);
		viewButtonConfig.setGtnUIFrameWorkActionConfigList(viewActionConfigList);

	}

}
