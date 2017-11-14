package com.stpl.gtn.gtn2o.ui.module.contractheader.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderResetAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderSaveAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.validation.GtnUIFrameworkContractHeaderCommonValidationAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnUIFrameworkContractHeaderAddConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getContractHeaderAddView() {

		GtnUIFrameworkViewConfig addView = configProvider.getViewConfig("Add View", "V002", false);
		addComponentList(addView);
		return addView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addContractHeaderInfoPanel(componentList);
		addTabLayout(componentList);
		addSaveButtonLayout(componentList);
	}

	private void addContractHeaderInfoPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getUIFrameworkComponentConfig("contractInformationPanel",
				false, null, GtnUIFrameworkComponentType.PANEL);
		panel.setComponentName("Contract Information");
		panel.setAuthorizationIncluded(true);
		panel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(panel);
		addContractHeaderInfoFieldLayout(componentList);
	}

	private void addContractHeaderInfoFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getVerticalLayoutConfig("contractInformationVerticallayout", true, "contractInformationPanel");
		gtnLayout.setMargin(true);
		gtnLayout.setSpacing(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addFieldCSSLayout(componentList);
	}

	private void addFieldCSSLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_INFORMATION_LAYOUT, true,
				"contractInformationVerticallayout", GtnUIFrameworkLayoutType.COL3_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setAddToParent(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);
		addContractHeaderInfoFieldComponent(componentList);
	}

	private void addContractHeaderInfoFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		addContractHeaderInfoContractHeaderId(componentList);
		addContractHeaderInfoContractHeaderNo(componentList);
		addContractHeaderInfoContractHeaderName(componentList);

	}

	private void addContractHeaderInfoContractHeaderId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("contractIdLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderIdConfig = configProvider.getUIFrameworkComponentConfig(
				"contractId", true, "contractIdLayout", GtnUIFrameworkComponentType.TEXTBOX);
		contractHeaderIdConfig.setAuthorizationIncluded(true);
		contractHeaderIdConfig.setComponentName("Contract ID");

		contractHeaderIdConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(false, false, true));

		componentList.add(contractHeaderIdConfig);
	}

	private void addContractHeaderInfoContractHeaderNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("contractNoLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderNoConfig = configProvider.getUIFrameworkComponentConfig(
				"contractNo", true, "contractNoLayout", GtnUIFrameworkComponentType.TEXTBOX);
		contractHeaderNoConfig.setAuthorizationIncluded(true);
		contractHeaderNoConfig.setComponentName("Contract No");

		contractHeaderNoConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(false, false, true));
		componentList.add(contractHeaderNoConfig);
	}

	private void addContractHeaderInfoContractHeaderName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("contractNameLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_INFORMATION_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderNameConfig = configProvider.getUIFrameworkComponentConfig(
				"contractName", true, "contractNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		contractHeaderNameConfig.setAuthorizationIncluded(true);
		contractHeaderNameConfig.setComponentName("Contract Name");

		contractHeaderNameConfig.setGtnTextBoxConfig(configProvider.getTextBoxConfig(false, false, true));

		componentList.add(contractHeaderNameConfig);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("contractTabsheetLayout",
				false, null);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);

		addTabSheet(componentList);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setAuthorizationIncluded(true);
		tabSheetConfig.setComponentId("contractHeaderTabSheet");
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		tabSheetConfig.setAddToParent(true);
		tabSheetConfig.setParentComponentId("contractTabsheetLayout");
		componentList.add(tabSheetConfig);

		GtnUIFrameworkTabConfig contactHeaderInformationTab = configProvider.getTabConfig("contactHeaderTab",
				"Contract Header");
		List<GtnUIFrameworkComponentConfig> contactHeaderTabConfigList = new ArrayList<>();
		contactHeaderInformationTab.setTabLayoutComponentConfigList(contactHeaderTabConfigList);
		new GtnUIFrameworkContractHeaderTabConfig().addContractHeaderTab(contactHeaderTabConfigList);
		GtnUIFrameworkTabConfig contractInformationTab = configProvider.getTabConfig("contractAdditionalInformationTab",
				"Contract Information");
		List<GtnUIFrameworkComponentConfig> contractAdditionalInformationTabConfigList = new ArrayList<>();
		contractInformationTab.setTabLayoutComponentConfigList(contractAdditionalInformationTabConfigList);
		new GtnUIFrameworkContracHeadertInfoTabConfig()
				.addAdditionalInfoTab(contractAdditionalInformationTabConfigList);
		GtnUIFrameworkTabConfig contractAliasTab = configProvider.getTabConfig("contractAliasTab", "Alias");
		List<GtnUIFrameworkComponentConfig> contractAliasTabConfigList = new ArrayList<>();
		contractAliasTab.setTabLayoutComponentConfigList(contractAliasTabConfigList);
		new GtnUIFrameworkContractHeaderAliasTabConfig().addAliasTab(contractAliasTabConfigList);
		GtnUIFrameworkTabConfig notesTab = configProvider.getTabConfig("notesTab", "Notes");
		List<GtnUIFrameworkComponentConfig> notesTabConfigList = new ArrayList<>();
		notesTab.setTabLayoutComponentConfigList(notesTabConfigList);
		addNotesTab(notesTabConfigList);
		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(contactHeaderInformationTab);
		tabConfigList.add(contractInformationTab);
		tabConfigList.add(contractAliasTab);
		tabConfigList.add(notesTab);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addNotesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkComponentConfig("notesTab", false,
				null, GtnUIFrameworkComponentType.NOTES_TAB);
		layoutConfig.setAuthorizationIncluded(true);
		layoutConfig.setTabComponent(true);
		layoutConfig.setNotesTabConfig(new GtnUIFrameworkNotesTabConfig());
		componentList.add(layoutConfig);

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnUIFrameworkContractHeaderStringContants.SAVE_BUTTON_LAYOUT, false, null);
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addSaveButtonComponent(componentList);
		addDeleteButtonComponent(componentList);
		addBackButtonComponent(componentList);
		addResetButtonComponent(componentList);

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderAddBackButtonLayout", true,
				GtnUIFrameworkContractHeaderStringContants.SAVE_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig chBackButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderAddBackButton", true, "contractHeaderAddBackButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		chBackButtonConfig.setAuthorizationIncluded(true);
		chBackButtonConfig.setComponentName("Back");

		componentList.add(chBackButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig chBackButtonConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		chBackButtonConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTACT_HEADER_CONFIRMATION_MSG);
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTACT_HEADER_MSG_BACK);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);
		alertParamsList.add(onSucessActionConfigList);
		chBackButtonConfirmationActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(chBackButtonConfirmationActionConfig);
		chBackButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderDeleteButtonLayout", true,
				GtnUIFrameworkContractHeaderStringContants.SAVE_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderDeleteButton", true, "contractHeaderDeleteButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Delete");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_CONFIRMATION_MSG);
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTACT_HEADER_VALIDATION_MSG_DELETE);
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_NAME);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		onSucessActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkContractHeaderDeleteAction.class.getName());
		onSucessActionConfigList.add(customAction);
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(Arrays.asList("contractHeaderSearchCriteriaContractId", "contractHeaderSearchCriteriaContractNo",
				"contractHeaderSearchCriteriaContractName", "contractHeaderSearchCriteriaContractStatus",
				"contractHeaderSearchCriteriaContractType", "contractHeaderSearchCriteriaTradeClass",
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TP_NO, "searchResultTable"));

		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				null));
		resetActionConfig.setActionParameterList(params);
		onSucessActionConfigList.add(resetActionConfig);
		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderAddSaveButtonLayout", true,
				GtnUIFrameworkContractHeaderStringContants.SAVE_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderAddSaveButton", true, "contractHeaderAddSaveButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Save");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customCommonValidationAction = new GtnUIFrameWorkActionConfig();
		customCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customCommonValidationAction
				.addActionParameter(GtnUIFrameworkContractHeaderCommonValidationAction.class.getName());
		actionConfigList.add(customCommonValidationAction);

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_CONFIRMATION_MSG);
		alertParamsList.add("Save record ");
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_NAME);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		alertParamsList.add(onSucessActionConfigList);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkContractHeaderSaveAction.class.getName());
		customAction.addActionParameter(Arrays.asList("contractHeaderTabContractId", "contractHeaderTabContractNo",
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_NAME,
				"contractHeaderTabContractType", "contractHeaderTabContractStatus", "contractHeaderTabDocumentType",
				"contractHeaderContractStartDate", "contractHeaderContractEndDate", "contractHeaderTabDocumentClass",
				"contractHeaderTabTradeClass", "contractHeaderTabTradingPartner",
				"contractHeaderTabReNegotiationStartDate", "contractHeaderTabReNegotiationEndDate",
				"contractHeaderTabPriceProtectionStartDate", "contractHeaderTabPriceProtectionEndDate",
				"contractHeaderTaManufacturerNo", "contractInformationTabInsideOwner",
				"contractInformationTabInsidePhone", "contractInformationTabInsideAuthor",
				"contractInformationTabInsideAdditional", "contractInformationTabInsideAdditionalName",
				"contractInformationTabInsideAdditionalPhone", "contractInformationTabOutsideOwner",
				"contractInformationTabOutsidePhone", "contractInformationTabOutsideAuthor",
				"contractInformationTabOutsideAdditional", "contractInformationTabOutsideAdditionalName",
				"contractInformationTabOutsideAdditionalPhone", "contractInformationTabAffiliatedContractInfo",
				"contractInformationTabShippingTerms", "contractInformationTabProposalStartDate",
				"contractInformationTabProposalEndDate", "contractInformationTabOriginalStartDate",
				"contractInformationTabOriginalEndDate", "contractInformationTabAwardStatus",
				"contractInformationTabLastUpdatedDate", "contractInformationTabPriceEscalationClause",
				"contractInformationTabExemptFromLowPrice", "contractInformationTabCancellationClause",
				"contractInformationTabMostFavoredNation", "contractInformationTabCategory",
				"contractInformationTabCurrency", "contractInformationTabMinimumOrder"));

		customAction.addActionParameter(Arrays.asList("contractId", "contractNo", "contractName", "contractType",
				"contractStatus", "documentType", "startDate", "endDate", "documentClass", "contractTradeClass",
				"organizationKey", "renegotiationStartDate", "renegotiationEndDate", "priceprotectionStartDate",
				"priceprotectionEndDate", "companyMasterByManfCompanyMasterSid", "insideOwner", "insidePhone",
				"insideAuthor", "insideAdditional", "insideAdditionalName", "insideAdditionalPhone", "outsideOwner",
				"outsidePhone", "outsideAuthor", "outsideAdditional", "outsideAdditionalName", "outsideAdditionalPhone",
				"affiliatedContractInfo", "shippingTerms", "proposalStartDate", "proposalEndDate", "originalStartDate",
				"originalEndDate", "awardStatus", "lastUpdatedDate", "priceEscalationClause", "exemptFromLowPrice",
				"cancellationClause", "mostFavoredNation", "category", "currency", "minimumOrder"));
		onSucessActionConfigList.add(customAction);
		confirmationActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(confirmationActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderAddResetButtonLayout", true,
				GtnUIFrameworkContractHeaderStringContants.SAVE_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig backButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderAddResetButton", true, "contractHeaderAddResetButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setAuthorizationIncluded(true);
		backButtonConfig.setComponentName("Reset");

		componentList.add(backButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList
				.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_CONFIRMATION_MSG_RESET_HEADER);
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_CONFIRMATION_MSG_RESET);

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkContractHeaderResetAction.class.getName());
		onSucessActionConfigList.add(customAction);
		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);

		backButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
