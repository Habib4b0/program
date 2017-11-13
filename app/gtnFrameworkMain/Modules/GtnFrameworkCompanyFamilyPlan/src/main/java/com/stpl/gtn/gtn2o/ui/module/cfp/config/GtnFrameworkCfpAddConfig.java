package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpClassContants;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkCfpAddConfig {

	public GtnUIFrameworkViewConfig getCFPAddView() {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig addView = componentConfig.getViewConfig("Add View", "V002", false);
		addComponentList(addView, componentConfig);
		return addView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addCompanyInfoPanel(componentList, componentConfig);
		addTabLayout(componentList, componentConfig);
		addSaveButtonLayout(componentList, componentConfig);
	}

	private void addCompanyInfoPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig panel = componentConfig.getPanelConfig("cfpInformationPanel", false, null);
		panel.setComponentName("Company Family Plan Information");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);
		addCompanyInfoFieldLayout(componentList, componentConfig);
	}

	private void addCompanyInfoFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpInfoLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.CFP_INFORMATION_LAYOUT, true, "cfpInformationPanel");
		cfpInfoLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(cfpInfoLayout);
		addCompanyInfoFieldComponent(componentList, componentConfig);
	}

	private void addCompanyInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		addCompanyInfoCFPId(componentList, componentConfig);
		addCompanyInfoCompanyNo(componentList, componentConfig);
		addCompanyInfoCompanyName(componentList, componentConfig);

	}

	private void addCompanyInfoCFPId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig cfpIdLayout = componentConfig.getHorizontalLayoutConfig("cfpCFPIdlayout", true,
				GtnFrameworkCommonConstants.CFP_INFORMATION_LAYOUT);
		componentList.add(cfpIdLayout);

		GtnUIFrameworkComponentConfig companyId = componentConfig.getUIFrameworkComponentConfig("cfpId", true,
				"cfpCFPIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyId.setComponentName("Company Family Plan ID");
		companyId.setAuthorizationIncluded(true);

		GtnUIFrameworkTextBoxConfig companyIdConfig = new GtnUIFrameworkTextBoxConfig();
		companyIdConfig.setEnable(false);
		companyId.setGtnTextBoxConfig(companyIdConfig);

		componentList.add(companyId);
	}

	private void addCompanyInfoCompanyNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpNoLayout = componentConfig.getHorizontalLayoutConfig("cfpCFPNolayout", true,
				GtnFrameworkCommonConstants.CFP_INFORMATION_LAYOUT);
		componentList.add(cfpNoLayout);

		GtnUIFrameworkComponentConfig companyNo = componentConfig.getUIFrameworkComponentConfig("cfpNo", true,
				"cfpCFPNolayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNo.setAuthorizationIncluded(true);
		companyNo.setComponentName("Company Family Plan No");

		GtnUIFrameworkTextBoxConfig companyNoConfig = new GtnUIFrameworkTextBoxConfig();
		companyNoConfig.setEnable(false);
		companyNo.setGtnTextBoxConfig(companyNoConfig);

		componentList.add(companyNo);
	}

	private void addCompanyInfoCompanyName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpNameLayout = componentConfig.getHorizontalLayoutConfig("cfpCFPNamelayout",
				true, GtnFrameworkCommonConstants.CFP_INFORMATION_LAYOUT);
		componentList.add(cfpNameLayout);

		GtnUIFrameworkComponentConfig companyName = componentConfig.getUIFrameworkComponentConfig("cfpName", true,
				"cfpCFPNamelayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyName.setAuthorizationIncluded(true);
		companyName.setComponentName("Company Family Plan Name");

		GtnUIFrameworkTextBoxConfig companyNameConfig = new GtnUIFrameworkTextBoxConfig();
		companyNameConfig.setEnable(false);
		companyName.setGtnTextBoxConfig(companyNameConfig);

		componentList.add(companyName);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpTabsheetConfig = componentConfig.getHorizontalLayoutConfig("cfpTabsheetLayout",
				false, null);
		cfpTabsheetConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(cfpTabsheetConfig);
		addTabSheet(componentList, componentConfig);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig tabSheetConfig = componentConfig.getUIFrameworkComponentConfig("cfpTabSheet",
				true, "cfpTabsheetLayout", GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setAuthorizationIncluded(true);
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(tabSheetConfig);
		// find the No of tabs

		GtnUIFrameworkTabConfig companyInformationTab = componentConfig.getTabConfig("cfpInformationTab",
				"CFP Information");
		List<GtnUIFrameworkComponentConfig> companyInformationTabConfig = new ArrayList<>();
		companyInformationTab.setTabLayoutComponentConfigList(companyInformationTabConfig);
		new GtnFrameworkCfpInformationTabConfig().addCFPInformationTab(companyInformationTabConfig);

		GtnUIFrameworkTabConfig companyAddtionTab = componentConfig.getTabConfig("companyAdditionTab",
				"Company Addition");
		List<GtnUIFrameworkComponentConfig> companyAddtionTabConfig = new ArrayList<>();
		companyAddtionTab.setTabLayoutComponentConfigList(companyAddtionTabConfig);
		new GtnFrameworkCfpCompanyAdditionTabConfig().addCompanyAdditionTab(companyAddtionTabConfig);

		GtnUIFrameworkTabConfig companiesTab = componentConfig.getTabConfig("CFPcompaniesTab", "Companies");
		List<GtnUIFrameworkComponentConfig> companiesTabConfig = new ArrayList<>();
		companiesTab.setTabLayoutComponentConfigList(companiesTabConfig);
		new GtnFrameworkCfpCompaniesTabConfig().addCompaniesTab(companiesTabConfig);

		GtnUIFrameworkTabConfig notesTab = componentConfig.getTabConfig("notesTab", "Notes");
		List<GtnUIFrameworkComponentConfig> notesTabConfig = new ArrayList<>();
		notesTab.setTabLayoutComponentConfigList(notesTabConfig);
		addNotesTab(notesTabConfig);

		List<GtnUIFrameworkTabConfig> cfpTabConfigList = new ArrayList<>();
		cfpTabConfigList.add(companyInformationTab);
		cfpTabConfigList.add(companyAddtionTab);
		cfpTabConfigList.add(companiesTab);
		cfpTabConfigList.add(notesTab);
		tabSheetConfig.setGtnTabSheetConfigList(cfpTabConfigList);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_TAB_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList("cfpCompaniesTabRecordType"));
		actionConfigList.add(customAction);
		tabSheetConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addNotesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig notesLayoutConfig = new GtnUIFrameworkComponentConfig();
		notesLayoutConfig.setComponentId("notesTab");
		notesLayoutConfig.setTabComponent(true);
		notesLayoutConfig.setComponentType(GtnUIFrameworkComponentType.NOTES_TAB);
		notesLayoutConfig.setNotesTabConfig(new GtnUIFrameworkNotesTabConfig());
		notesLayoutConfig.setAuthorizationIncluded(true);
		componentList.add(notesLayoutConfig);

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig buttonLayout = componentConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.CFP_SAVE_BUTTON_LAYOUT, false, null);
		buttonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		buttonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		buttonLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_20);
		buttonLayout.setSpacing(true);
		buttonLayout.setMargin(true);
		componentList.add(buttonLayout);
		addSaveButtonComponent(componentList, componentConfig);
		addDeleteButtonComponent(componentList, componentConfig);
		addBackButtonComponent(componentList, componentConfig);
		addResetButtonComponent(componentList, componentConfig);
	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig backBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpAddBackButtonlayout", true, GtnFrameworkCommonConstants.CFP_SAVE_BUTTON_LAYOUT);
		componentList.add(backBtnLayout);

		GtnUIFrameworkComponentConfig backButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"cfpAddBackButton", true, "cfpAddBackButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setComponentName("Back");
		backButtonConfig.setAuthorizationIncluded(true);
		componentList.add(backButtonConfig);

		List<GtnUIFrameWorkActionConfig> backActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig backConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		backConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG_BACK);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_COMPANIES_DELETE_ACTION);
		onSucessActionConfigList.add(deleteActionConfig);

		alertParamsList.add(onSucessActionConfigList);

		backConfirmationActionConfig.setActionParameterList(alertParamsList);

		backActionConfigList.add(backConfirmationActionConfig);

		backButtonConfig.setGtnUIFrameWorkActionConfigList(backActionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig saveBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpAddSaveButtonlayout", true, GtnFrameworkCommonConstants.CFP_SAVE_BUTTON_LAYOUT);
		componentList.add(saveBtnLayout);

		GtnUIFrameworkComponentConfig saveButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"cfpAddSaveButton", true, "cfpAddSaveButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setComponentName("Save");
		saveButtonConfig.setAuthorizationIncluded(true);
		componentList.add(saveButtonConfig);

		List<GtnUIFrameWorkActionConfig> saveActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig duplicateValidationAction = new GtnUIFrameWorkActionConfig();
		duplicateValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		duplicateValidationAction.addActionParameter(
				GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_ID_AND_CFP_NO_DUPLICATE_VALIDATION_ACTION);
		saveActionConfigList.add(duplicateValidationAction);

		GtnUIFrameWorkActionConfig customCommonValidationAction = new GtnUIFrameWorkActionConfig();
		customCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customCommonValidationAction
				.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_COMMON_VALIDATION_ACTION);
		saveActionConfigList.add(customCommonValidationAction);

		GtnUIFrameWorkActionConfig saveConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		saveConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG_SAVE);
		alertParamsList.add(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_NAME);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customSaveAction = new GtnUIFrameWorkActionConfig();
		customSaveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customSaveAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_SAVE_ACTION);
		customSaveAction.addActionParameter(Arrays.asList("cfpInformationTabCFPId", "cfpInformationTabCFPNo",
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_NAME, "cfpInformationTabCFPStatus",
				"cfpInformationCFPStartDate", "cfpInformationCFPEndDate", "cfpInformationTabCFPType",
				"cfpInformationTabCFPCategory", "cfpInformationTabCFPTradeClass", "cfpInformationTabCFPDesignation",
				"cfpInformationTabParentCFSPId", "cfpInformationTabParentCFPName", "cfpInformationTabCFPSalesInclusion",
				"cfpInformationTabCreatedBy", "cfpInformationCreatedDate", "cfpInformationTabModifiedBy",
				"cfpInformationModifiedDate"));

		customSaveAction.addActionParameter(Arrays.asList("cfpId", "cfpNo", "cfpName", "cfpStatus", "cfpStartDate",
				"cfpEndDate", "cfpType", "cfpCategory", "cfpTradeClass", "cfpDesignation", "parentCfpId",
				"parentCfpName", "salesInclusion", "createdBy", "createdDate", "modifiedBy", "modifiedDate"));

		onSucessActionConfigList.add(customSaveAction);

		alertParamsList.add(onSucessActionConfigList);

		saveConfirmationActionConfig.setActionParameterList(alertParamsList);

		saveActionConfigList.add(saveConfirmationActionConfig);

		saveButtonConfig.setGtnUIFrameWorkActionConfigList(saveActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resetBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"CFPADDResetButtonlayout", true, GtnFrameworkCommonConstants.CFP_SAVE_BUTTON_LAYOUT);
		componentList.add(resetBtnLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"CFPADDResetButton", true, "CFPADDResetButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("Reset");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG_RESET_HEADER);
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG_RESET);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resetActionConfig.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_RESET_ACTION);
		onSucessActionConfigList.add(resetActionConfig);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		resetActionConfigList.add(confirmationActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig deleteLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpAddDeleteButtonlayout", true, GtnFrameworkCommonConstants.CFP_SAVE_BUTTON_LAYOUT);
		componentList.add(deleteLayout);

		GtnUIFrameworkComponentConfig deleteButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"cfpAddDeleteButton", true, "cfpAddDeleteButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setComponentName("Delete");
		deleteButtonConfig.setAuthorizationIncluded(true);
		componentList.add(deleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> deleteActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig deleteConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		deleteConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG);
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG_DELETE);
		alertParamsList.add(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_NAME);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_DELETE_ACTION);
		onSucessActionConfigList.add(deleteActionConfig);

		GtnUIFrameWorkActionConfig setDefaultActionConfig = new GtnUIFrameWorkActionConfig();
		setDefaultActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(Arrays.asList("companyFamilyPlanId", "companyFamilyPlanNo", "companyFamilyPlanName",
				"companyFamilyPlanStatus", "companyFamilyPlanType", "companyId", "companyNo", "companyName",
				"searchResultTable"));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null));
		setDefaultActionConfig.setActionParameterList(params);
		onSucessActionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);

		alertParamsList.add(onSucessActionConfigList);

		deleteConfirmationActionConfig.setActionParameterList(alertParamsList);

		deleteActionConfigList.add(deleteConfirmationActionConfig);

		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(deleteActionConfigList);
	}

}
