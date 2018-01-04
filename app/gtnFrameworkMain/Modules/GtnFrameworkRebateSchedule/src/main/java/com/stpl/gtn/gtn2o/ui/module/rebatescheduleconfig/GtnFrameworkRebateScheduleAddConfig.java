/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkRsCustomResetAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkRebateScheduleAddConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getAddView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("rebateScheduleAddView", "rebateScheduleAddView",
				false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addTopPanel(componentList);
		addTabLayout(componentList);
		addSaveButtonLayout(componentList);
	}

	private void addTopPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("rsInfoPanel", false, null);
		panel.setComponentName("Rebate Schedule Information ");
		panel.setAuthorizationIncluded(true);
		componentList.add(panel);
		addFieldLayout(componentList);

	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkRSConstants.RS_INFO_PANELLAYOUT, true, "rsInfoPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		styleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		gtnLayout.setComponentStyle(styleList);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addRebateSchId(componentList);
		addRebateSchNo(componentList);
		addRebateSchName(componentList);
	}

	private void addRebateSchId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateSchIdTopLayout", true,
				GtnFrameworkRSConstants.RS_INFO_PANELLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig("rebateSchIdTop",
				true, "rebateSchIdTopLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Rebate Schedule ID");
		companyIdConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(false);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		companyIdConfig.setGtnTextBoxConfig(textboxConfig);
		componentList.add(companyIdConfig);
	}

	private void addRebateSchNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateSchNoLayoutTop", true,
				GtnFrameworkRSConstants.RS_INFO_PANELLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig("rebateScheNoTop",
				true, "rebateSchNoLayoutTop", GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName("Rebate Schedule No");
		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(false);
		companyNoConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(companyNoConfig);
	}

	private void addRebateSchName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("rebateSchedNameTopLayout",
				true, GtnFrameworkRSConstants.RS_INFO_PANELLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				"rebateScheNameTop", true, "rebateSchedNameTopLayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName("Rebate Schedule Name");
		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(false);
		companyNameConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(companyNameConfig);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkRSConstants.REBATE_SCHEDULE_TABSHEET_LAYOUT, false, null);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		layoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(layoutConfig);

		addTabSheet(componentList);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig tabSheetConfig = configProvider.getUIFrameworkComponentConfig("tabSheet", true,
				GtnFrameworkRSConstants.REBATE_SCHEDULE_TABSHEET_LAYOUT, GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setAuthorizationIncluded(true);

		tabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabSheetConfig);

		GtnUIFrameworkTabConfig companyInformationTab = configProvider.getTabConfig("rebateScheduleInfoTab",
				"Rebate Schedule Information");
		List<GtnUIFrameworkComponentConfig> rebateScheduleInfoTabConfigList = new ArrayList<>();
		companyInformationTab.setTabLayoutComponentConfigList(rebateScheduleInfoTabConfigList);
		new GtnFrameworkRebateScheduleInformationTabConfig().addrebateScheduleInfoTab(rebateScheduleInfoTabConfigList);

		GtnUIFrameworkTabConfig processingOptionTab = configProvider.getTabConfig("processingOptionTab",
				"Processing Options");
		List<GtnUIFrameworkComponentConfig> processingOptionTabConfigList = new ArrayList<>();
		processingOptionTab.setTabLayoutComponentConfigList(processingOptionTabConfigList);
		new GtnFrameworkRSProcessingOptionConfig().addCompanyAdditionTab(processingOptionTabConfigList);

		GtnUIFrameworkTabConfig additionTab = configProvider.getTabConfig("itemAdditionTab", "Item Addition");
		List<GtnUIFrameworkComponentConfig> itemAdditionTabConfigList = new ArrayList<>();
		additionTab.setTabLayoutComponentConfigList(itemAdditionTabConfigList);
		new GtnFrameworkRSItemAdditionConfig().addItemAdditionTab(itemAdditionTabConfigList);

		GtnUIFrameworkTabConfig pricingTab = configProvider.getTabConfig("RebateSetupTab", "Rebate Setup");
		List<GtnUIFrameworkComponentConfig> rebateSetupTabConfigList = new ArrayList<>();
		pricingTab.setTabLayoutComponentConfigList(rebateSetupTabConfigList);
		new GtnFrameworkRebateScheduleSetUpTabConfig().addPriceSchedulePriocingTab(rebateSetupTabConfigList);

		GtnUIFrameworkTabConfig notesTab = configProvider.getTabConfig(GtnFrameworkCommonConstants.NOTES_TAB, "Notes");
		List<GtnUIFrameworkComponentConfig> notesTabConfigList = new ArrayList<>();
		notesTab.setTabLayoutComponentConfigList(notesTabConfigList);
		addNotesTab(notesTabConfigList);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(companyInformationTab);
		tabConfigList.add(processingOptionTab);
		tabConfigList.add(additionTab);
		tabConfigList.add(pricingTab);
		tabConfigList.add(notesTab);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addNotesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.NOTES_TAB, false, null, GtnUIFrameworkComponentType.NOTES_TAB);
		layoutConfig.setComponentName(GtnFrameworkCommonConstants.NOTES_TAB);
		layoutConfig.setAuthorizationIncluded(true);
		layoutConfig.setTabComponent(true);

		GtnUIFrameworkNotesTabConfig config = new GtnUIFrameworkNotesTabConfig();

		List<String> formats = new ArrayList<>();
		formats.add("docx");
		formats.add("doc");
		formats.add("ppt");
		formats.add("xls");
		formats.add("xlsx");
		formats.add("pdf");
		formats.add("txt");
		formats.add("csv");
		formats.add("jpg");
		formats.add("jpeg");
		formats.add("pptx");

		config.setValidFormats(formats);

		layoutConfig.setNotesTabConfig(config);

		componentList.add(layoutConfig);

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT, false, null);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_20);
		componentList.add(gtnLayout);
		addSaveButtonComponent(componentList);
        addDeleteButtonComponent(componentList);
		addBackButtonComponent(componentList);
		addResetButtonComponent(componentList);
		

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"rsAddBackButton", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Back");
		componentList.add(searchButtonConfig);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION, "");
		onSucessActionConfig.add(navigationActionConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CONFIRMATION_ACTION, " Back Confirmation ",
				" Are you sure you want to navigate back to the Rebate Schedule landing screen? \nYou will lose all unsaved data if you proceed ?",
				onSucessActionConfig);

		actionConfigList.add(confirmationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"rsAddSaveButton", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Save");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig custoValidationAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkRSConstants.SAVE_MANDATERY_ALERT);

		actionConfigList.add(custoValidationAction);
		GtnUIFrameWorkActionConfig priceTabcustomValidationAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkRSConstants.MANDATERY_ALERT);

		actionConfigList.add(priceTabcustomValidationAction);

		GtnUIFrameWorkActionConfig custoSavemAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkRSConstants.RS_SAVE_CONFIRMATION);

		actionConfigList.add(custoSavemAction);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"rebateScheduleAddViewAAddResetButton", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("Reset");
		componentList.add(resetButtonConfig);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig restActionConfig = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkRsCustomResetAction.class.getName());
		onSucessActionConfigList.add(restActionConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CONFIRMATION_ACTION, "Reset Confirmation",
				"Are you sure you want to reset all values?", onSucessActionConfigList);

		actionConfigList.add(confirmationActionConfig);
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRDeleteButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleAddViewAAddDeleteButton", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cDRDeleteButtonConfig.setAuthorizationIncluded(true);
		cDRDeleteButtonConfig.setComponentName("Delete");
		componentList.add(cDRDeleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE, GtnUIFrameworkModeType.DELETE);
		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig deleteConfirmationAction = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkRSConstants.RS_DELETE_CONFIRMATION);

		actionConfigList.add(deleteConfirmationAction);

		cDRDeleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	public List<Object> getResetFieldValueList() {
		List<Object> defaultFieldValueList = new ArrayList<>();
		List<String> resetComponentIdListTemp = new ArrayList<>();
		List<Object> resetComponentValueListTemp = new ArrayList<>();

		resetComponentIdListTemp.add("RSMassCheck");
		resetComponentIdListTemp.add("RSRebateSetupMassCheck");
		resetComponentIdListTemp.add("rebateScheduleId1");
		resetComponentIdListTemp.add("rebateScheduleNo1");
		resetComponentIdListTemp.add("rebateScheduleName1");
		resetComponentIdListTemp.add("rebateScheduleAliasId1");
		resetComponentIdListTemp.add("parentRebateScheduleName");
		resetComponentIdListTemp.add("rsTransactionRefName");
		resetComponentIdListTemp.add("paymentGracePeriod");
		resetComponentIdListTemp.add("rsTransactionRefName");
		resetComponentIdListTemp.add("parentRebateScheduleID");
		resetComponentIdListTemp.add("rsTransactionRefId");
		resetComponentIdListTemp.add("evaluationRuleAssociation");
		resetComponentIdListTemp.add("calculationRule");
		resetComponentIdListTemp.add("rebateScheduleStatus1");
		resetComponentIdListTemp.add("rebateScheduleType1");
		resetComponentIdListTemp.add("rebateProgramType1");
		resetComponentIdListTemp.add("rebateScheduleCategory1");
		resetComponentIdListTemp.add("rebateScheduleTradeClass");
		resetComponentIdListTemp.add("rebateScheduleDesignation");
		resetComponentIdListTemp.add("rsUDC1");
		resetComponentIdListTemp.add("rsUDC2");
		resetComponentIdListTemp.add("rsUDC3");
		resetComponentIdListTemp.add("rsUDC4");
		resetComponentIdListTemp.add("rsUDC5");
		resetComponentIdListTemp.add("rsUDC6");
		resetComponentIdListTemp.add("rsDeductionInclusion");
		resetComponentIdListTemp.add("rsCalendar");
		resetComponentIdListTemp.add("rebateFrequency1");
		resetComponentIdListTemp.add("paymentLevel");
		resetComponentIdListTemp.add("paymentFrequency");
		resetComponentIdListTemp.add("paymentTerms");
		resetComponentIdListTemp.add("paymentMethod");
		resetComponentIdListTemp.add("interestBearingBasis");
		resetComponentIdListTemp.add("evaluationRuleLevel");
		resetComponentIdListTemp.add("evaluationRuleType");
		resetComponentIdListTemp.add("interestBearingIndicator");
		resetComponentIdListTemp.add("calculationRuleLevel");
		resetComponentIdListTemp.add("calculationType1");
		resetComponentIdListTemp.add("calculationLevel");
		resetComponentIdListTemp.add("rebateRuleType");
		resetComponentIdListTemp.add("rebateScheduleStartDate");
		resetComponentIdListTemp.add("rebateScheduleEndDate");
		resetComponentIdListTemp.add("rebateScheduleEndDate");
		resetComponentIdListTemp.add("psRebateSetupTabResultDataTable");
		resetComponentIdListTemp.add("RSrightResultTable");
		resetComponentIdListTemp.add("RSleftResultTable");

		resetComponentValueListTemp.add("IFP");
		resetComponentValueListTemp.add("Disable");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add("");
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);
		resetComponentValueListTemp.add(null);

		defaultFieldValueList.add(resetComponentIdListTemp);
		defaultFieldValueList.add(resetComponentValueListTemp);
		return defaultFieldValueList;
	}
}
