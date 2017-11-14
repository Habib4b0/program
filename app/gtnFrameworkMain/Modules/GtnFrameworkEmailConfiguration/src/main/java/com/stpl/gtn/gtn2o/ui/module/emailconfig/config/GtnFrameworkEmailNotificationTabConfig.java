package com.stpl.gtn.gtn2o.ui.module.emailconfig.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textarea.GtnUIFrameworkTextAreaConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.action.GtnFrameworkEmailConfigSaveValueChange;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.constants.GtnFrameworkEmailConfigStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkEmailNotificationTabConfig {

	public void addEMailNotificationTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkComponentConfig emailNotificationRootLayout = componentConfig
				.getRootLayoutConfig("emailNotificationTab", GtnUIFrameworkLayoutType.VERTICAL_LAYOUT, true);
		componentList.add(emailNotificationRootLayout);

		GtnUIFrameworkComponentConfig emailNotificationMainCssLayout = componentConfig.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.EMAIL_NOTIFICATION_MAIN_TAB_LAYOUT, true, "emailNotificationTab");
		componentList.add(emailNotificationMainCssLayout);
		addPanel(componentList, componentConfig);
		addMainLayout(componentList, componentConfig);
		addSuccessMailNoficationPanel(componentList, componentConfig);
		addSuccessMailNoficationLayout(componentList, componentConfig);
		addFailureMailNotificationPanel(componentList, componentConfig);
		addFailureMailNotificationLayout(componentList, componentConfig);
		addSaveButtonLayout(componentList, componentConfig);
	}

	private void addPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig emailNotificationMainpanel = componentConfig.getPanelConfig(
				"mailNotificationPanel", true, GtnFrameworkCommonConstants.EMAIL_NOTIFICATION_MAIN_TAB_LAYOUT);
		emailNotificationMainpanel.setComponentName("Mail Notification");
		componentList.add(emailNotificationMainpanel);
	}

	private void addSuccessMailNoficationPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig successMailNotificationPanel = componentConfig.getPanelConfig(
				"successMailNotificationPanel", true, GtnFrameworkCommonConstants.EMAIL_NOTIFICATION_MAIN_TAB_LAYOUT);
		successMailNotificationPanel.setComponentName("Success Mail Notification");
		componentList.add(successMailNotificationPanel);
	}

	private void addFailureMailNotificationPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig panel = componentConfig.getPanelConfig("failureMailNotificationPanel", true,
				GtnFrameworkCommonConstants.EMAIL_NOTIFICATION_MAIN_TAB_LAYOUT);
		panel.setComponentName("Failure Mail Notification");
		componentList.add(panel);
	}

	private void addMainLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig emailNotificationMainLayout = componentConfig
				.getHorizontalLayoutConfig("mailNotificationLayout", true, "mailNotificationPanel");
		componentList.add(emailNotificationMainLayout);
		addProcessName(componentList, componentConfig);

	}

	private void addSuccessMailNoficationLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig successMailNotificationCssLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.SUCCESS_MAIL_NOTIFICATION_LAYOUT, true, "successMailNotificationPanel");
		successMailNotificationCssLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		successMailNotificationCssLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_90);
		componentList.add(successMailNotificationCssLayout);

		addSuccessEmailTo(componentList, componentConfig);
		addSuccessEmailCc(componentList, componentConfig);
		addSuccessSubject(componentList, componentConfig);
		addSuccessEmailBody(componentList, componentConfig);

	}

	private void addFailureMailNotificationLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig failureMailNotificationCssLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.FAILURE_MAIL_NOTIFICATION_LAYOUT, true, "failureMailNotificationPanel");
		failureMailNotificationCssLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		failureMailNotificationCssLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_90);
		componentList.add(failureMailNotificationCssLayout);

		addFieldComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addFailureEmailTo(componentList, componentConfig);
		addFailureEmailCc(componentList, componentConfig);
		addFailureSubject(componentList, componentConfig);
		addFailureEmailBody(componentList, componentConfig);
	}

	/*
	 * To configure for different process name
	 */

	private void addProcessName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig processNameLayout = componentConfig.getCssLayoutConfig("ProcessNamelayout", true,
				"mailNotificationLayout");
		processNameLayout.setSpacing(true);
		componentList.add(processNameLayout);

		GtnUIFrameworkComponentConfig processName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ADD_PROCESS_NAME, true, "ProcessNamelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		processName.setAuthorizationIncluded(true);
		processName.setComponentName("Process Name");
		processName.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		componentList.add(processName);

		GtnUIFrameworkComboBoxConfig processNameConfig = componentConfig.getComboBoxConfig("processName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		processName.setGtnComboboxConfig(processNameConfig);

		List<GtnUIFrameWorkActionConfig> processNameActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig processNameCustomAction = new GtnUIFrameWorkActionConfig();
		processNameCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		processNameCustomAction.addActionParameter(GtnFrameworkEmailConfigSaveValueChange.class.getName());
		processNameCustomAction.addActionParameter(GtnFrameworkCommonConstants.ADD_PROCESS_NAME);
		processNameCustomAction.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.ADD_EMAIL_TO, GtnFrameworkCommonConstants.ADD_EMAIL_CC,
						GtnFrameworkCommonConstants.ADD_SUBJECT, GtnFrameworkCommonConstants.ADD_EMAIL_BODY,
						GtnFrameworkCommonConstants.ADD_FAILURE_TO, GtnFrameworkCommonConstants.ADD_EMAIL_CC1,
						GtnFrameworkCommonConstants.ADD_SUBJECT1, GtnFrameworkCommonConstants.ADD_EMAIL_BODY1));

		processNameActionConfigList.add(processNameCustomAction);

		processName.setGtnUIFrameWorkActionConfigList(processNameActionConfigList);
	}

	private void addSuccessEmailTo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addEmailToLayout = componentConfig.getHorizontalLayoutConfig("addEmailTolayout",
				true, GtnFrameworkCommonConstants.SUCCESS_MAIL_NOTIFICATION_LAYOUT);
		addEmailToLayout.setSpacing(true);
		addEmailToLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		addEmailToLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(addEmailToLayout);

		GtnUIFrameworkComponentConfig emailToConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ADD_EMAIL_TO, true, "addEmailTolayout",
				GtnUIFrameworkComponentType.TEXTAREA);
		emailToConfig.setAuthorizationIncluded(true);
		emailToConfig.setComponentName("Email To");
		emailToConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextAreaConfig emailToConfigConfig = componentConfig.getTextAreaConfig(
				GtnFrameworkCommonConstants.USE_COMMA_TO_SEPERATE_MULTIPLE_EMAIL_IDS, true, GtnWsNumericConstants.ONE);
		emailToConfig.setGtnTextAreaConfig(emailToConfigConfig);
		componentList.add(emailToConfig);
	}

	private void addSuccessSubject(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addSubjectLayout = componentConfig.getHorizontalLayoutConfig("addSubjectlayout",
				true, GtnFrameworkCommonConstants.SUCCESS_MAIL_NOTIFICATION_LAYOUT);
		addSubjectLayout.setSpacing(true);
		addSubjectLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		addSubjectLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(addSubjectLayout);

		GtnUIFrameworkComponentConfig addSubject = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ADD_SUBJECT, true, "addSubjectlayout",
				GtnUIFrameworkComponentType.TEXTAREA);
		addSubject.setAuthorizationIncluded(true);
		addSubject.setComponentName("Subject");
		addSubject.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextAreaConfig addSubjectConfig = componentConfig.getTextAreaConfig(null, true,
				GtnWsNumericConstants.FIVE);
		addSubject.setGtnTextAreaConfig(addSubjectConfig);
		componentList.add(addSubject);

	}

	private void addSuccessEmailCc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addEmailCcLayout = componentConfig.getHorizontalLayoutConfig("addEmailCclayout",
				true, GtnFrameworkCommonConstants.SUCCESS_MAIL_NOTIFICATION_LAYOUT);
		addEmailCcLayout.setSpacing(true);
		addEmailCcLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		addEmailCcLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(addEmailCcLayout);

		GtnUIFrameworkComponentConfig addEmailCc = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ADD_EMAIL_CC, true, "addEmailCclayout",
				GtnUIFrameworkComponentType.TEXTAREA);
		addEmailCc.setAuthorizationIncluded(true);
		addEmailCc.setComponentName("Email Cc");

		GtnUIFrameworkTextAreaConfig addEmailCcConfig = componentConfig.getTextAreaConfig(
				GtnFrameworkCommonConstants.USE_COMMA_TO_SEPERATE_MULTIPLE_EMAIL_IDS, true, GtnWsNumericConstants.ONE);
		addEmailCc.setGtnTextAreaConfig(addEmailCcConfig);
		componentList.add(addEmailCc);

	}

	private void addSuccessEmailBody(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addEmailBodyLayout = componentConfig.getHorizontalLayoutConfig(
				"addEmailBodylayout", true, GtnFrameworkCommonConstants.SUCCESS_MAIL_NOTIFICATION_LAYOUT);
		addEmailBodyLayout.setSpacing(true);
		addEmailBodyLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		addEmailBodyLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(addEmailBodyLayout);

		GtnUIFrameworkComponentConfig addEmailBody = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ADD_EMAIL_BODY, true, "addEmailBodylayout",
				GtnUIFrameworkComponentType.TEXTAREA);
		addEmailBody.setAuthorizationIncluded(true);
		addEmailBody.setComponentName("Email Body");
		addEmailBody.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextAreaConfig frameworkTextAreaConfig = componentConfig.getTextAreaConfig(null, true,
				GtnWsNumericConstants.FIVE);
		addEmailBody.setGtnTextAreaConfig(frameworkTextAreaConfig);
		componentList.add(addEmailBody);

	}

	private void addFailureEmailTo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addFailureToLayout = componentConfig.getHorizontalLayoutConfig(
				"addFailureTolayout", true, GtnFrameworkCommonConstants.FAILURE_MAIL_NOTIFICATION_LAYOUT);
		addFailureToLayout.setSpacing(true);
		addFailureToLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		addFailureToLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(addFailureToLayout);

		GtnUIFrameworkComponentConfig failureEmailTo = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ADD_FAILURE_TO, true, "addFailureTolayout",
				GtnUIFrameworkComponentType.TEXTAREA);
		failureEmailTo.setAuthorizationIncluded(true);
		failureEmailTo.setComponentName("Failure To");
		failureEmailTo.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextAreaConfig failureEmailToConfig = componentConfig.getTextAreaConfig(
				GtnFrameworkCommonConstants.USE_COMMA_TO_SEPERATE_MULTIPLE_EMAIL_IDS, true, GtnWsNumericConstants.ONE);
		failureEmailTo.setGtnTextAreaConfig(failureEmailToConfig);
		componentList.add(failureEmailTo);

	}

	private void addFailureSubject(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addSubject1Layout = componentConfig.getHorizontalLayoutConfig("addSubjectlayout1",
				true, GtnFrameworkCommonConstants.FAILURE_MAIL_NOTIFICATION_LAYOUT);
		addSubject1Layout.setSpacing(true);
		addSubject1Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		addSubject1Layout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(addSubject1Layout);

		GtnUIFrameworkComponentConfig addSubject1 = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ADD_SUBJECT1, true, "addSubjectlayout1",
				GtnUIFrameworkComponentType.TEXTAREA);
		addSubject1.setAuthorizationIncluded(true);
		addSubject1.setComponentName("Subject");
		addSubject1.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextAreaConfig addSubject1Config = componentConfig.getTextAreaConfig(null, true,
				GtnWsNumericConstants.FIVE);
		addSubject1.setGtnTextAreaConfig(addSubject1Config);
		componentList.add(addSubject1);

	}

	private void addFailureEmailCc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addEmailCc1Layout = componentConfig.getHorizontalLayoutConfig("addEmailCclayout1",
				true, GtnFrameworkCommonConstants.FAILURE_MAIL_NOTIFICATION_LAYOUT);
		addEmailCc1Layout.setSpacing(true);
		addEmailCc1Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		addEmailCc1Layout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(addEmailCc1Layout);

		GtnUIFrameworkComponentConfig addEmailCc1 = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ADD_EMAIL_CC1, true, "addEmailCclayout1",
				GtnUIFrameworkComponentType.TEXTAREA);
		addEmailCc1.setAuthorizationIncluded(true);
		addEmailCc1.setComponentName("Email Cc");

		GtnUIFrameworkTextAreaConfig frameworkTextAreaConfig = new GtnUIFrameworkTextAreaConfig();
		frameworkTextAreaConfig.setInputPrompt(GtnFrameworkCommonConstants.USE_COMMA_TO_SEPERATE_MULTIPLE_EMAIL_IDS);
		frameworkTextAreaConfig.setEnable(true);
		frameworkTextAreaConfig.setRows(1);
		addEmailCc1.setGtnTextAreaConfig(frameworkTextAreaConfig);
		componentList.add(addEmailCc1);

	}

	private void addFailureEmailBody(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addEmailBody1Layout = componentConfig.getHorizontalLayoutConfig(
				"addEmailBodylayout1", true, GtnFrameworkCommonConstants.FAILURE_MAIL_NOTIFICATION_LAYOUT);
		addEmailBody1Layout.setSpacing(true);
		addEmailBody1Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		addEmailBody1Layout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);
		componentList.add(addEmailBody1Layout);

		GtnUIFrameworkComponentConfig addEmailBody = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ADD_EMAIL_BODY1, true, "addEmailBodylayout1",
				GtnUIFrameworkComponentType.TEXTAREA);
		addEmailBody.setAuthorizationIncluded(true);
		addEmailBody.setComponentName("Email Body");
		addEmailBody.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkTextAreaConfig addEmailBodyConfig = new GtnUIFrameworkTextAreaConfig();
		addEmailBodyConfig.setEnable(true);
		addEmailBodyConfig.setRows(5);
		addEmailBody.setGtnTextAreaConfig(addEmailBodyConfig);
		componentList.add(addEmailBody);

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig saveButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"MailNotificationsaveButtonlayout", true,
				GtnFrameworkCommonConstants.EMAIL_NOTIFICATION_MAIN_TAB_LAYOUT);
		saveButtonLayout.setSpacing(true);
		saveButtonLayout.setMargin(true);
		componentList.add(saveButtonLayout);
		addSaveButtonComponent(componentList, componentConfig);
	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig saveBtnLayout = componentConfig
				.getCssLayoutConfig("EmailNotificationAddSaveButtonlayout", true, "MailNotificationsaveButtonlayout");
		componentList.add(saveBtnLayout);

		GtnUIFrameworkComponentConfig saveButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"EmailNotificationAddSaveButton", true, "EmailNotificationAddSaveButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setAuthorizationIncluded(true);
		saveButtonConfig.setComponentName("Save");
		componentList.add(saveButtonConfig);

		List<GtnUIFrameWorkActionConfig> saveActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customSaveAction = new GtnUIFrameWorkActionConfig();
		customSaveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		Object saveActionClass = GtnFrameworkEmailConfigStringContants.EMAIL_NOTIFICATION_SAVE_ACTION;
		customSaveAction
				.setActionParameterList(Arrays.asList(saveActionClass, GtnFrameworkCommonConstants.ADD_PROCESS_NAME,
						GtnFrameworkCommonConstants.ADD_EMAIL_TO, GtnFrameworkCommonConstants.ADD_SUBJECT,
						GtnFrameworkCommonConstants.ADD_EMAIL_CC, GtnFrameworkCommonConstants.ADD_EMAIL_BODY,
						GtnFrameworkCommonConstants.ADD_FAILURE_TO, GtnFrameworkCommonConstants.ADD_SUBJECT1,
						GtnFrameworkCommonConstants.ADD_EMAIL_CC1, GtnFrameworkCommonConstants.ADD_EMAIL_BODY1));

		saveActionConfigList.add(customSaveAction);

		saveButtonConfig.setGtnUIFrameWorkActionConfigList(saveActionConfigList);

	}

}
