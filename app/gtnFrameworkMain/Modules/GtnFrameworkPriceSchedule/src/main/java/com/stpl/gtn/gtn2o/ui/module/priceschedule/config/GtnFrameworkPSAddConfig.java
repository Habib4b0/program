/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.priceschedule.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabSheetLoadType;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkCustomTabChangeAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSPriceProtectionTabAlertAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPsResetAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkPSSaveMandatoryAlertAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkPSSavePriceTabMandatoryAlertAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameworkPSDeleteConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameworkPSSaveConfirmationAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkPSAddConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getAddView() {
		GtnUIFrameworkViewConfig psMainView = configProvider.getViewConfig("priceScheduleAddView",
				"priceScheduleAddView", false);
		addComponentList(psMainView);
		return psMainView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig psMainView) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		psMainView.setGtnComponentList(componentList);
		addTopPanel(componentList);
		addTabLayout(componentList);
		addSaveButtonLayout(componentList);
	}

	private void addTopPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig psMainPanel = configProvider.getPanelConfig("priceScheduleInfoPanel", false,
				null);
		psMainPanel.setComponentName("Price Schedule Information Panel");
		psMainPanel.setAuthorizationIncluded(true);
		componentList.add(psMainPanel);
		addFieldLayout(componentList);

	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig mainFieldLayout = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_PANEL_LAYOUT, true, "priceScheduleInfoPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		styleList.add(GtnFrameworkCssConstants.NO_MARGIN);
		mainFieldLayout.setComponentStyle(styleList);
		componentList.add(mainFieldLayout);
		addFieldComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addPriceScheduleId(componentList);
		addPriceScheduleNo(componentList);
		addPriceScheduleName(componentList);
	}

	private void addPriceScheduleId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleIdLayout = configProvider.getHorizontalLayoutConfig(
				"priceScheduleIdTopLayout", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_PANEL_LAYOUT);
		componentList.add(priceScheduleIdLayout);

		GtnUIFrameworkComponentConfig priceScheduleIdConfig = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleIdTop", true, "priceScheduleIdTopLayout", GtnUIFrameworkComponentType.TEXTBOX);
		priceScheduleIdConfig.setComponentName("Price Schedule ID");
		priceScheduleIdConfig.setAuthorizationIncluded(true);
		priceScheduleIdConfig.setEnable(false);
		componentList.add(priceScheduleIdConfig);
	}

	private void addPriceScheduleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleNoLayout = configProvider.getHorizontalLayoutConfig(
				"priceScheduleNoLayoutTop", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_PANEL_LAYOUT);
		componentList.add(priceScheduleNoLayout);

		GtnUIFrameworkComponentConfig priceScheduleNo = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleNoTop", true, "priceScheduleNoLayoutTop", GtnUIFrameworkComponentType.TEXTBOX);
		priceScheduleNo.setComponentName("Price Schedule No");
		priceScheduleNo.setAuthorizationIncluded(true);
		priceScheduleNo.setEnable(false);
		GtnUIFrameworkValidationConfig priceScheduleNoValidationConfig = new GtnUIFrameworkValidationConfig();
		priceScheduleNoValidationConfig.setMaxLength(5);
		priceScheduleNo.setGtnUIFrameworkValidationConfig(priceScheduleNoValidationConfig);
		componentList.add(priceScheduleNo);
	}

	private void addPriceScheduleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig priceScheduleNameLayout = configProvider.getHorizontalLayoutConfig(
				"priceScheduleNameTopLayout", true, GtnFrameworkCommonConstants.PRICE_SCHEDULE_INFO_PANEL_LAYOUT);
		componentList.add(priceScheduleNameLayout);

		GtnUIFrameworkComponentConfig priceScheduleName = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleNameTop", true, "priceScheduleNameTopLayout", GtnUIFrameworkComponentType.TEXTBOX);
		priceScheduleName.setAuthorizationIncluded(true);
		priceScheduleName.setComponentName("Price Schedule Name");
		componentList.add(priceScheduleName);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig tabLayoutConfig = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.PRICE_SCHEDULE_TAB_SHEET_LAYOUT, false, null);
		tabLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(tabLayoutConfig);

		addTabSheet(componentList);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psTabSheetConfig = configProvider.getUIFrameworkComponentConfig("tabSheet", true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TAB_SHEET_LAYOUT, GtnUIFrameworkComponentType.TABSHEET);
		psTabSheetConfig.setAuthorizationIncluded(true);
		psTabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psTabSheetConfig);

		GtnUIFrameWorkActionConfig setValueChangeFlagAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		setValueChangeFlagAction.addActionParameter(GtnFrameworkCustomTabChangeAction.class.getName());
		psTabSheetConfig.addGtnUIFrameWorkActionConfig(setValueChangeFlagAction);

		GtnUIFrameworkTabConfig priceScheduleInfoTabConfig = configProvider.getTabConfig("priceScheduleInfoTab",
				"Price Schedule Information");
		List<GtnUIFrameworkComponentConfig> priceScheduleInfoTabConfigList = new ArrayList<>();
		priceScheduleInfoTabConfig.setTabLayoutComponentConfigList(priceScheduleInfoTabConfigList);
		new GtnFrameworkPSInformationTabConfig().addpriceScheduleInfoTab(priceScheduleInfoTabConfigList);

		GtnUIFrameworkTabConfig notesTab = configProvider.getTabConfig(GtnFrameworkCommonConstants.NOTES_TAB, "Notes");
		notesTab.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		List<GtnUIFrameworkComponentConfig> notesTabConfigList = new ArrayList<>();
		notesTab.setTabLayoutComponentConfigList(notesTabConfigList);
		notesTabConfigList.add(addNotesTab());

		GtnUIFrameworkTabConfig additionTab = configProvider.getTabConfig("itemAdditionTab", "Item Addition");
		List<GtnUIFrameworkComponentConfig> itemAdditionTabConfigList = new ArrayList<>();
		additionTab.setTabLayoutComponentConfigList(itemAdditionTabConfigList);
		new GtnFrameworkPSItemAdditionTabConfig().addItemAdditionTab(itemAdditionTabConfigList);

		GtnUIFrameworkTabConfig pricingTab = configProvider.getTabConfig("PricingTab", "Item Pricing");
		List<GtnUIFrameworkComponentConfig> pricingTabConfigList = new ArrayList<>();
		pricingTab.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		pricingTab.setTabLayoutComponentConfigList(pricingTabConfigList);
		new GtnFrameworkPSPricingTabConfig().addPriceSchedulePricingTab(pricingTabConfigList);

		GtnUIFrameworkTabConfig priceProtectionTab = configProvider.getTabConfig("PriceProtectionTab",
				"Price Protection");
		List<GtnUIFrameworkComponentConfig> priceProtectionTabConfigList = new ArrayList<>();
		priceProtectionTab.setTabloadingType(GtnUIFrameworkTabSheetLoadType.EARLY_LOAD);
		priceProtectionTab.setTabLayoutComponentConfigList(priceProtectionTabConfigList);
		new GtnFrameworkPSPriceProtectionTabConfig().addPriceProtectionTab(priceProtectionTabConfigList);

		List<GtnUIFrameworkTabConfig> psTabConfigList = new ArrayList<>();
		psTabConfigList.add(priceScheduleInfoTabConfig);
		psTabConfigList.add(additionTab);
		psTabConfigList.add(pricingTab);
		psTabConfigList.add(priceProtectionTab);
		psTabConfigList.add(notesTab);
		psTabSheetConfig.setGtnTabSheetConfigList(psTabConfigList);

	}

	private GtnUIFrameworkComponentConfig addNotesTab() {
		GtnUIFrameworkComponentConfig notesTabLayoutConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.NOTES_TAB, false, null, GtnUIFrameworkComponentType.NOTES_TAB);
		notesTabLayoutConfig.setTabComponent(true);
		GtnUIFrameworkNotesTabConfig config = new GtnUIFrameworkNotesTabConfig();
		notesTabLayoutConfig.setNotesTabConfig(config);
		return notesTabLayoutConfig;

	}

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig savebtnLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT, false, null);
		savebtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_20);
		componentList.add(savebtnLayout);
		addSaveButtonComponent(componentList);
        addDeleteButtonComponent(componentList);
		addBackButtonComponent(componentList);
		addResetButtonComponent(componentList);
		
	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig backButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleAddBackButton", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setAuthorizationIncluded(true);
		backButtonConfig.setComponentName("Back");
		componentList.add(backButtonConfig);
			List<GtnUIFrameWorkActionConfig> backOnSucessActionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION, "");
		backOnSucessActionConfig.add(navigationActionConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = configProvider.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CONFIRMATION_ACTION, " Back Confirmation ",
				" Are you sure you want to navigate back to the Price Schedule landing screen? \nYou will lose all unsaved data if you proceed ?",
				backOnSucessActionConfig);

		actionConfigList.add(confirmationActionConfig);

		backButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);


	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"cDRAddSaveButton", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Save");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig custoValidationAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		custoValidationAction.addActionParameter(GtnUIFrameWorkPSSaveMandatoryAlertAction.class.getName());
		actionConfigList.add(custoValidationAction);
		GtnUIFrameWorkActionConfig priceTabcustomValidationAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		priceTabcustomValidationAction
				.addActionParameter(GtnUIFrameWorkPSSavePriceTabMandatoryAlertAction.class.getName());

		actionConfigList.add(priceTabcustomValidationAction);

		GtnUIFrameWorkActionConfig customPsCommonValidationAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customPsCommonValidationAction.addActionParameter(GtnFrameworkPSPriceProtectionTabAlertAction.class.getName());
		actionConfigList.add(customPsCommonValidationAction);

		GtnUIFrameWorkActionConfig custoSavemAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		custoSavemAction.addActionParameter(GtnUIFrameworkPSSaveConfirmationAction.class.getName());

		actionConfigList.add(custoSavemAction);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleAddViewAAddResetButton", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("Reset");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Reset Confirmation");
		alertParamsList.add("Are you sure you want to reset all values?");
		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig restActionConfig = new GtnUIFrameWorkActionConfig();
		restActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		restActionConfig.addActionParameter(GtnFrameworkPsResetAction.class.getName());
		onSucessActionConfigList.add(restActionConfig);

		alertParamsList.add(onSucessActionConfigList);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		resetActionConfigList.add(confirmationActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRDeleteButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleAddViewAAddDeleteButton", true, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		cDRDeleteButtonConfig.setComponentName("Delete");
		cDRDeleteButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cDRDeleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.addActionParameter(GtnUIFrameworkModeType.DELETE);
		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig deleteConfirmationAction = new GtnUIFrameWorkActionConfig();
		deleteConfirmationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteConfirmationAction.addActionParameter(GtnUIFrameworkPSDeleteConfirmationAction.class.getName());

		actionConfigList.add(deleteConfirmationAction);
		cDRDeleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
