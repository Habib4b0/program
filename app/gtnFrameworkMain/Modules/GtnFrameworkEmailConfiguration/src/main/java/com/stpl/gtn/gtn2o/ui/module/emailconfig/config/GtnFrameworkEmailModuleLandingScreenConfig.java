package com.stpl.gtn.gtn2o.ui.module.emailconfig.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.constants.GtnFrameworkEmailConfigStringContants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkEmailModuleLandingScreenConfig {

	public GtnUIFrameworkViewConfig getMainView() {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig addView = componentConfig.getViewConfig("Main_View", "V009", true);
		addComponentList(addView, componentConfig);

		GtnUIFrameWorkActionConfig mainCustomAction = new GtnUIFrameWorkActionConfig();
		mainCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		mainCustomAction.addActionParameter(GtnFrameworkEmailConfigStringContants.DATA_LOAD_ACTION);
		addView.addViewAction(mainCustomAction);
		return addView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addTabLayout(componentList, componentConfig);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig layoutConfig = componentConfig.getVerticalLayoutConfig("EMailTabsheetLayout",
				false, null);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);
		addTabSheet(componentList, componentConfig);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig tabSheetConfig = componentConfig.getUIFrameworkComponentConfig("EMailTabSheet",
				true, "EMailTabsheetLayout", GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setAuthorizationIncluded(true);
		tabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(tabSheetConfig);

		GtnUIFrameworkTabConfig emailConfigurationTab = componentConfig.getTabConfig("emailConfigurationTab",
				"Email Configuration");
		List<GtnUIFrameworkComponentConfig> emailConfigurationTabConfigList = new ArrayList<>();
		emailConfigurationTab.setTabLayoutComponentConfigList(emailConfigurationTabConfigList);
		new GtnFrameworkEmailServerTabConfig().addEMailConfigurationTab(emailConfigurationTabConfigList);

		GtnUIFrameworkTabConfig emailNotificationtab = componentConfig.getTabConfig("emailNotificationTab",
				"Email Notification");
		List<GtnUIFrameworkComponentConfig> emailNotificationTabConfigList = new ArrayList<>();
		emailNotificationtab.setTabLayoutComponentConfigList(emailNotificationTabConfigList);
		new GtnFrameworkEmailNotificationTabConfig().addEMailNotificationTab(emailNotificationTabConfigList);

		List<GtnUIFrameworkTabConfig> emailConfigTabConfigList = new ArrayList<>();
		emailConfigTabConfigList.add(emailConfigurationTab);
		emailConfigTabConfigList.add(emailNotificationtab);
		tabSheetConfig.setGtnTabSheetConfigList(emailConfigTabConfigList);
	}
}
