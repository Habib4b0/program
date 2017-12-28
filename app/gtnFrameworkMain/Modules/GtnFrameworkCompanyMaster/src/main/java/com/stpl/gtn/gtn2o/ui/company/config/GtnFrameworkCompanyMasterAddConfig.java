package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;

public class GtnFrameworkCompanyMasterAddConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getCompanyMasterAddView() {
		GtnUIFrameworkViewConfig addView = new GtnUIFrameworkViewConfig();
		addView.setViewName("Add View");
		addView.setViewId("V002");
		addView.setDefaultView(false);
		addComponentList(addView);
		return addView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addErrorBanner(componentList);
		addCompanyInfoPanel(componentList);
		addTabLayout(componentList);
		addSaveButtonLayout(componentList);
	}

	private void addErrorBanner(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig errorBannerConfig = new GtnUIFrameworkComponentConfig();
		errorBannerConfig.setComponentType(GtnUIFrameworkComponentType.ERROR_BANNER);
		errorBannerConfig.setComponentId("companyErrorBanner");
		errorBannerConfig.setAddToParent(false);
		errorBannerConfig.setAuthorizationIncluded(true);
		componentList.add(errorBannerConfig);
	}

	private void addCompanyInfoPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("companyInformationPanel", false, null);
		panel.setComponentName("Company Information");
		panel.setAuthorizationIncluded(true);
		panel.setComponentWidth("100%");
		componentList.add(panel);
		addCompanyInfoFieldLayout(componentList);
	}

	private void addCompanyInfoFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.COMPANY_INFORMATION_LAYOUT, true, "companyInformationPanel",
				GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);
		addCompanyInfoFieldComponent(componentList);
	}

	private void addCompanyInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		addCompanyInfoCompanyId(componentList);
		addCompanyInfoCompanyNo(componentList);
		addCompanyInfoCompanyName(componentList);

	}

	private void addCompanyInfoCompanyId(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationcompanyIdlayout", true,
				GtnFrameworkCompanyStringContants.COMPANY_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationcompanyId", true, "companyInformationcompanyIdlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Company ID");
		companyIdConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(false);
		companyIdConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyIdConfig);
	}

	private void addCompanyInfoCompanyNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig("companyInformationcompanyNolayout", true, "companyInformationLayout");
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationcompanyNo", true, "companyInformationcompanyNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentName("Company No");
		companyNoConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(false);
		companyNoConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyNoConfig);
	}

	private void addCompanyInfoCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"companyInformationcompanyNamelayout", true,
				GtnFrameworkCompanyStringContants.COMPANY_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				"companyInformationcompanyName", true, "companyInformationcompanyNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setComponentName("Company Name");
		companyNameConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(false);
		companyNameConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyNameConfig);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("companyTabsheetLayout",
				false, null);
		layoutConfig.setComponentWidth("100%");

		componentList.add(layoutConfig);

		addTabSheet(componentList);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig tabSheetConfig = configProvider.getUIFrameworkComponentConfig("tabSheet", true,
				"companyTabsheetLayout", GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setAuthorizationIncluded(true);
		tabSheetConfig.setComponentWidth("100%");
		componentList.add(tabSheetConfig);
		// find the No of tabs

		GtnUIFrameworkTabConfig companyInformationTab = new GtnUIFrameworkTabConfig();
		companyInformationTab.setComponentId("companyInformationTab");
		companyInformationTab.setTabCaption("Company Information");
		List<GtnUIFrameworkComponentConfig> companyInformationTabConfigList = new ArrayList<>();
		companyInformationTab.setTabLayoutComponentConfigList(companyInformationTabConfigList);
		new GtnUIFrameworkCompanyInformationTab().addCompanyInformationTab(companyInformationTabConfigList);

		GtnUIFrameworkTabConfig addressTab = new GtnUIFrameworkTabConfig();
		addressTab.setComponentId("addressTab");
		addressTab.setTabCaption("Address");
		List<GtnUIFrameworkComponentConfig> addressTabConfigList = new ArrayList<>();
		addressTab.setTabLayoutComponentConfigList(addressTabConfigList);
		new GtnUIFrameworkCMAdderssTabConfig().addAddressTab(addressTabConfigList);

		GtnUIFrameworkTabConfig identifierTab = new GtnUIFrameworkTabConfig();
		identifierTab.setComponentId("identifierTab");
		identifierTab.setTabCaption("Identifier");
		List<GtnUIFrameworkComponentConfig> identifierTabConfigList = new ArrayList<>();
		identifierTab.setTabLayoutComponentConfigList(identifierTabConfigList);
		new GtnFrameworkCMIdentifierTab().addIdentifierTab(identifierTabConfigList);

		GtnUIFrameworkTabConfig companyTradeClassTab = new GtnUIFrameworkTabConfig();
		companyTradeClassTab.setComponentId("tradeClassTab");
		companyTradeClassTab.setTabCaption("Company Trade Class");
		List<GtnUIFrameworkComponentConfig> companyTradeClassTabConfigList = new ArrayList<>();
		companyTradeClassTab.setTabLayoutComponentConfigList(companyTradeClassTabConfigList);
		new GtnUIFrameworkCMTradeClassTab().addTradeClassTab(companyTradeClassTabConfigList);

		GtnUIFrameworkTabConfig parentCompanyTab = new GtnUIFrameworkTabConfig();
		parentCompanyTab.setComponentId("parentCompanyTab");
		parentCompanyTab.setTabCaption("Parent Company");
		List<GtnUIFrameworkComponentConfig> parentCompanyTabConfigList = new ArrayList<>();
		parentCompanyTab.setTabLayoutComponentConfigList(parentCompanyTabConfigList);
		new GtnUIFrameworkCMParentCompanyInformationTab().addParentCompanyTab(parentCompanyTabConfigList);

		GtnUIFrameworkTabConfig financialCloseTab = new GtnUIFrameworkTabConfig();
		financialCloseTab.setComponentId("financialCloseTab");
		financialCloseTab.setTabCaption("Financial Close");
		List<GtnUIFrameworkComponentConfig> financialCloseTabConfigList = new ArrayList<>();
		financialCloseTab.setTabLayoutComponentConfigList(financialCloseTabConfigList);
		new GtnFrameWorkCompanyMasterFinancialCloseConfig().addFinancialCloseTab(financialCloseTabConfigList);

		GtnUIFrameworkTabConfig notesTab = new GtnUIFrameworkTabConfig();
		notesTab.setComponentId("notesTab");
		notesTab.setTabCaption("Notes");
		List<GtnUIFrameworkComponentConfig> notesConfig = new ArrayList<>();
		addNotesTab(notesConfig);
		notesTab.setTabLayoutComponentConfigList(notesConfig);
		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(companyInformationTab);
		tabConfigList.add(addressTab);
		tabConfigList.add(identifierTab);
		tabConfigList.add(companyTradeClassTab);
		tabConfigList.add(parentCompanyTab);
		tabConfigList.add(financialCloseTab);
		tabConfigList.add(notesTab);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addNotesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("notesTab");
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.NOTES_TAB);
		layoutConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkNotesTabConfig config = new GtnUIFrameworkNotesTabConfig();
		layoutConfig.setNotesTabConfig(config);

		componentList.add(layoutConfig);

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCompanyStringContants.SAVE_BUTTON_LAYOUT, false, null);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(GtnWsNumericConstants.FOUR);
		gtnLayout.setComponentWidth("40%");
		componentList.add(gtnLayout);
		addSaveButtonComponent(componentList);
        addDeleteButtonComponent(componentList);
		addBackButtonComponent(componentList);
		addResetButtonComponent(componentList);
		

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig backButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"companyMasterAddBackButton", true, GtnFrameworkCompanyStringContants.SAVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setComponentName("Back");
		backButtonConfig.setAuthorizationIncluded(true);

		componentList.add(backButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG);
		alertParamsList.add("Any unsaved information will not be saved. Do you want to proceed?");
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.FINANCIAL_MODE_BACK_ACTION_CUSTOM);
		onSucessActionConfigList.add(customAction);
		onSucessActionConfigList.add(navigationActionConfig);
		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);
		backButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"companyMasterAddSaveButton", true, GtnFrameworkCompanyStringContants.SAVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Save");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customCommonValidationAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customCommonValidationAction
				.addActionParameter(GtnFrameworkCompanyClassContants.COMPANY_MANDATORY_VALIDATION_ACTION);
		actionConfigList.add(customCommonValidationAction);

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG);
		alertParamsList.add("Save record ");
		alertParamsList.add("companyInformationTabCompanyName");
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		actionConfigList.add(confirmationActionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();

		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.COMPANY_SAVE_ACTION);
		onSucessActionConfigList.add(customAction);
		alertParamsList.add(onSucessActionConfigList);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"companyMasterAddResetButton", true, GtnFrameworkCompanyStringContants.SAVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Reset");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG_RESET_001);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCompanyClassContants.COMPANY_RESET_ACTION);
		onSucessActionConfigList.add(customAction);
		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig deleteButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"companyMasterAddDeleteButton", true, GtnFrameworkCompanyStringContants.SAVE_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setComponentName("Delete");
		deleteButtonConfig.setAuthorizationIncluded(true);

		componentList.add(deleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCompanyStringContants.GTN_COMPANY_MASTER_CONFIRMATION_MSG);
		alertParamsList.add("Are you sure you want to delete record ");
		alertParamsList.add("companyInformationcompanyName");
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		alertParamsList.add(onSucessActionConfigList);
		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);
		GtnUIFrameWorkActionConfig deleteActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkCompanyClassContants.COMPANY_DELETE_ACTION);
		onSucessActionConfigList.add(deleteActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);

		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
