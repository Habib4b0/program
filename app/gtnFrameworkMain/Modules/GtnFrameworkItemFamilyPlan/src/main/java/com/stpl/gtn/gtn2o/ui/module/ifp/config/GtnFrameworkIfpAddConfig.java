package com.stpl.gtn.gtn2o.ui.module.ifp.config;

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
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpClassContants;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkIfpAddConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getIFPAddView() {

		GtnUIFrameworkViewConfig addView = configProvider.getViewConfig("Add View", "V002", false);
		addComponentList(addView);
		return addView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addCompanyInfoPanel(componentList);
		addTabLayout(componentList);
		addSaveButtonLayout(componentList);
	}

	private void addCompanyInfoPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("ifpInformationPanel", false, null);
		panel.setComponentName("Item Family Plan Information");
		panel.setAuthorizationIncluded(true);
		panel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(panel);
		addCompanyInfoFieldLayout(componentList);
	}

	private void addCompanyInfoFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.IFP_INFORMATION_LAYOUT, true, "ifpInformationPanel");
		layoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(layoutConfig);
		addCompanyInfoFieldComponent(componentList);
	}

	private void addCompanyInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		addCompanyInfoIFPId(componentList);
		addCompanyInfoCompanyNo(componentList);
		addCompanyInfoCompanyName(componentList);

	}

	private void addCompanyInfoIFPId(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpIFPIdlayout", true,
				GtnFrameworkCommonConstants.IFP_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig("ifpInfoifpId",
				true, "ifpIFPIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Item Family Plan ID");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(false, false, false);
		companyIdConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyIdConfig);
	}

	private void addCompanyInfoCompanyNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpIFPNolayout", true,
				GtnFrameworkCommonConstants.IFP_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig("ifpInfoifpNo",
				true, "ifpIFPNolayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName("Item Family Plan No");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(false, false, false);
		companyNoConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyNoConfig);
	}

	private void addCompanyInfoCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpIFPNamelayout", true,
				GtnFrameworkCommonConstants.IFP_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig("ifpInfoifpName",
				true, "ifpIFPNamelayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName("Item Family Plan Name");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(false, false, false);
		companyNameConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyNameConfig);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("ifpTabsheetLayout",
				false, null);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);

		addTabSheet(componentList);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig tabSheetConfig = configProvider.getUIFrameworkComponentConfig("ifptabSheet", true,
				"ifpTabsheetLayout", GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setAuthorizationIncluded(true);
		tabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		tabSheetConfig.setComponentName("Tab Sheet");
		componentList.add(tabSheetConfig);
		// find the No of tabs

		GtnUIFrameworkTabConfig ifpInformationTab = configProvider.getTabConfig("ifpInformationTab", "IFP Information");
		List<GtnUIFrameworkComponentConfig> ifpInformationList = new ArrayList<>();
		ifpInformationTab.setTabLayoutComponentConfigList(ifpInformationList);
		new GtnFrameworkIfpInformationTabConfig().addIFPInformationTab(ifpInformationList);

		GtnUIFrameworkTabConfig itemAdditionTab = configProvider.getTabConfig("itemAdditionTab", "Item Addition");
		List<GtnUIFrameworkComponentConfig> ifpItemAdditionList = new ArrayList<>();
		itemAdditionTab.setTabLayoutComponentConfigList(ifpItemAdditionList);
		new GtnFrameworkIfpItemAdditionTabConfig().addItemAdditionTab(ifpItemAdditionList);

		GtnUIFrameworkTabConfig ifpItemsTab = configProvider.getTabConfig("ifpItemsTab", "Items");
		List<GtnUIFrameworkComponentConfig> ifpItemsList = new ArrayList<>();
		ifpItemsTab.setTabLayoutComponentConfigList(ifpItemsList);
		new GtnFrameworkIfpItemsTabConfig().addItemsTab(ifpItemsList);

		GtnUIFrameworkTabConfig notesTab = configProvider.getTabConfig("notesTab", "Notes");
		List<GtnUIFrameworkComponentConfig> ifpNotesTabList = new ArrayList<>();
		notesTab.setTabLayoutComponentConfigList(ifpNotesTabList);
		addNotesTab(ifpNotesTabList);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(ifpInformationTab);
		tabConfigList.add(itemAdditionTab);
		tabConfigList.add(ifpItemsTab);
		tabConfigList.add(notesTab);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_TAB_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList("ifpItemsTabRecordType"));
		actionConfigList.add(customAction);
		tabSheetConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addNotesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkComponentConfig("notesTab", false,
				null, GtnUIFrameworkComponentType.NOTES_TAB);
		layoutConfig.setAuthorizationIncluded(true);
		layoutConfig.setTabComponent(true);

		GtnUIFrameworkNotesTabConfig config = new GtnUIFrameworkNotesTabConfig();

		layoutConfig.setNotesTabConfig(config);

		componentList.add(layoutConfig);

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.IF_PSAVE_BUTTONLAYOUT, false, null);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_20);
		componentList.add(gtnLayout);
		addBackButtonComponent(componentList);
		addSaveButtonComponent(componentList);
		addResetButtonComponent(componentList);
		addDeleteButtonComponent(componentList);

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpAddBackButtonlayout",
				true, GtnFrameworkCommonConstants.IF_PSAVE_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig backButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpAddBackButton", true, "ifpAddBackButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setAuthorizationIncluded(true);
		backButtonConfig.setComponentName("Back");
		componentList.add(backButtonConfig);
		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_HEADER);
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_BACK);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig deleteActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		deleteActionConfig.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_TEMP_DELETE_ACTION);
		onSucessActionConfigList.add(deleteActionConfig);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);
		backButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpAddSaveButtonlayout",
				true, GtnFrameworkCommonConstants.IF_PSAVE_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpAddSaveButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpAddSaveButton", true, "ifpAddSaveButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		ifpAddSaveButtonConfig.setAuthorizationIncluded(true);
		ifpAddSaveButtonConfig.setComponentName("Save");
		componentList.add(ifpAddSaveButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customValidationAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customValidationAction.addActionParameter(
				GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_ID_AND_IFP_NO_DUPLICATE_VALIDATION_ACTION);
		actionConfigList.add(customValidationAction);

		GtnUIFrameWorkActionConfig customCommonValidationAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customCommonValidationAction
				.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_COMMON_VALIDATION_ACTION);
		actionConfigList.add(customCommonValidationAction);

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_HEADER);
		alertParamsList.add("Save Record ");
		alertParamsList.add(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_NAME);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_SAVE_ACTION);
		customAction.addActionParameter(Arrays.asList("ifpInformationTabIFPId", "ifpInformationTabIFPNo",
				GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_NAME, "ifpInformationTabIFPStatus",
				"ifpInformationIFPStartDate", "ifpInformationIFPEndDate", "ifpInformationTabIFPType",
				"ifpInformationTabIFPCategory", "ifpInformationTabIFPDesignation", "ifpInformationTabParentIFPId",
				"ifpInformationTabParentIFPName", "ifpInformationCreatedDate", "ifpInformationModifiedDate"));

		customAction.addActionParameter(Arrays.asList("ifpId", "ifpNo", "ifpName", "ifpStatus", "ifpStartDate",
				"ifpEndDate", "ifpType", "ifpCategory", "ifpDesignation", "parentIfpId", "parentIfpName", "createdDate",
				"modifiedDate"));

		onSucessActionConfigList.add(customAction);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);
		ifpAddSaveButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("IFPADDResetButtonlayout",
				true, GtnFrameworkCommonConstants.IF_PSAVE_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpAddResetButtonlayoutConfig = configProvider.getUIFrameworkComponentConfig(
				"IFPADDResetButton", true, "IFPADDResetButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		ifpAddResetButtonlayoutConfig.setAuthorizationIncluded(true);
		ifpAddResetButtonlayoutConfig.setComponentName("Reset");
		componentList.add(ifpAddResetButtonlayoutConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_RESET_HEADER);
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_RESET);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig restActionConfig = new GtnUIFrameWorkActionConfig();
		restActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		restActionConfig.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_RESET_ACTION);
		onSucessActionConfigList.add(restActionConfig);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);
		ifpAddResetButtonlayoutConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentId("ifpAddDeleteButton");
		searchButtonConfig.setComponentName("Delete");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.IF_PSAVE_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_HEADER);
		alertParamsList.add(GtnFrameworkIfpStringContants.GTN_IFP_CONFIRMATION_MSG_DELETE);
		alertParamsList.add(GtnFrameworkCommonConstants.IFP_INFORMATION_TAB_IFP_NAME);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig deleteActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_DELETE_ACTION);
		onSucessActionConfigList.add(deleteActionConfig);
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(Arrays.asList("ifpId", "ifpNo", "ifpName", "ifpStatus", "ifpType", "itemId", "itemNo", "itemName",
				"searchResultTable"));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null));
		resetActionConfig.setActionParameterList(params);
		onSucessActionConfigList.add(resetActionConfig);
		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

}
