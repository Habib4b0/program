
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction.GtnUIFrameWorkResetOnEditModeAction;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkRPConstants;
import com.stpl.gtn.gtn2o.ui.module.rebateplan.util.GtnFrameworkStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkRebatePlanAddConfig {

	private final GtnFrameworkComponentConfigProvider rpAddConfigProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getAddView() {
		GtnUIFrameworkViewConfig rpAddScreenView = rpAddConfigProvider.getViewConfig("rebatePlanAddView",
				"rebatePlanAddView", false);
		addRPComponentList(rpAddScreenView);
		return rpAddScreenView;
	}

	private void addRPComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRpTopPanel(componentList);
		addRpTabLayout(componentList);
		addRpSaveButtonLayout(componentList);
	}

	private void addRpTopPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanInfoPanel = rpAddConfigProvider.getPanelConfig("rebatePlanInfoPanel",
				false, null);
		rebatePlanInfoPanel.setComponentName("Rebate Plan Information");
		rebatePlanInfoPanel.setAuthorizationIncluded(true);
		componentList.add(rebatePlanInfoPanel);

		addRpFieldLayout(componentList);

	}

	private void addRpFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpInfoLayout = rpAddConfigProvider.getGtnCssLayoutConfig(
				GtnFrameworkStringConstants.RP_INFO_PLAN_LAYOUT, true, "rebatePlanInfoPanel",
				GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		rpInfoLayout.setComponentStyle(styleList);
		componentList.add(rpInfoLayout);

		addRpFieldComponents(componentList);
	}

	private void addRpFieldComponents(List<GtnUIFrameworkComponentConfig> componentList) {
		addRebatePlanId(componentList);
		addRebatePlanNo(componentList);
		addRebatePlanName(componentList);
	}

	private void addRebatePlanId(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanIdConfigLayout = rpAddConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanIdTopLayout", true, GtnFrameworkStringConstants.RP_INFO_PLAN_LAYOUT);
		componentList.add(rebatePlanIdConfigLayout);

		GtnUIFrameworkComponentConfig rebatePlanIdConfig = rpAddConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanIdTop", true, rebatePlanIdConfigLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanIdConfig.setAuthorizationIncluded(true);
		rebatePlanIdConfig.setComponentName("Rebate Plan ID");

		GtnUIFrameworkTextBoxConfig rebatePlanIdTextboxConfig = new GtnUIFrameworkTextBoxConfig();
		rebatePlanIdTextboxConfig.setEnable(false);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanIdConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		rebatePlanIdConfig.setGtnTextBoxConfig(rebatePlanIdTextboxConfig);
		componentList.add(rebatePlanIdConfig);
	}

	private void addRebatePlanNo(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanNoConfigLayout = rpAddConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanNoLayoutTop", true, GtnFrameworkStringConstants.RP_INFO_PLAN_LAYOUT);
		componentList.add(rebatePlanNoConfigLayout);

		GtnUIFrameworkComponentConfig rebatePlanNoConfig = rpAddConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanNoTop", true, rebatePlanNoConfigLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNoConfig.setAuthorizationIncluded(true);
		rebatePlanNoConfig.setComponentName("Rebate Plan No");

		GtnUIFrameworkTextBoxConfig textboxConfig = new GtnUIFrameworkTextBoxConfig();
		textboxConfig.setEnable(false);
		rebatePlanNoConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(rebatePlanNoConfig);
	}

	private void addRebatePlanName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanNameConfigLayout = rpAddConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanNameTopLayout", true, GtnFrameworkStringConstants.RP_INFO_PLAN_LAYOUT);
		componentList.add(rebatePlanNameConfigLayout);

		GtnUIFrameworkComponentConfig rebatePlanNameConfig = rpAddConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanNameTop", true, rebatePlanNameConfigLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		rebatePlanNameConfig.setAuthorizationIncluded(true);
		rebatePlanNameConfig.setComponentName("Rebate Plan Name");

		GtnUIFrameworkTextBoxConfig rpNameTextboxConfig = new GtnUIFrameworkTextBoxConfig();
		rpNameTextboxConfig.setEnable(false);
		rebatePlanNameConfig.setGtnTextBoxConfig(rpNameTextboxConfig);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		rebatePlanNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		componentList.add(rebatePlanNameConfig);
	}

	private void addRpTabLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rebatePlanAddViewTabsheetLayoutConfig = rpAddConfigProvider
				.getHorizontalLayoutConfig("rebatePlanAddViewTabsheetLayout", false, null);
		rebatePlanAddViewTabsheetLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(rebatePlanAddViewTabsheetLayoutConfig);

		addTabSheet(componentList);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpTabSheetConfig = rpAddConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanAddViewtabSheet", true, "rebatePlanAddViewTabsheetLayout",
				GtnUIFrameworkComponentType.TABSHEET);
		rpTabSheetConfig.setAuthorizationIncluded(true);
		rpTabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		rpTabSheetConfig.setComponentName("Tab Sheet");
		componentList.add(rpTabSheetConfig);

		GtnUIFrameworkTabConfig rebatePlanInformationTab = rpAddConfigProvider.getTabConfig("rebatePlanInformationTab",
				"Rebate Plan Information");
		List<GtnUIFrameworkComponentConfig> rebatePlanInformationTabConfigList = new ArrayList<>();
		rebatePlanInformationTab.setTabLayoutComponentConfigList(rebatePlanInformationTabConfigList);
		new RebatePlanInformationTabConfig().addCompanyInformationTab(rebatePlanInformationTabConfigList);

		GtnUIFrameworkTabConfig rebatePlanCalculationTab = rpAddConfigProvider.getTabConfig("rebatePlanCalculationTab",
				"Calculations");
		List<GtnUIFrameworkComponentConfig> rebatePlanCalculationTabConfigList = new ArrayList<>();
		rebatePlanCalculationTab.setTabLayoutComponentConfigList(rebatePlanCalculationTabConfigList);
		new GtnFrameworkRebatePlanCalculationTabConfig().addCalculationTab(rebatePlanCalculationTabConfigList);

		GtnUIFrameworkTabConfig notesTab = rpAddConfigProvider.getTabConfig("notesTab", "Notes");
		List<GtnUIFrameworkComponentConfig> notesTabConfigList = new ArrayList<>();
		notesTab.setTabLayoutComponentConfigList(notesTabConfigList);
		addNotesTab(notesTabConfigList);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(rebatePlanInformationTab);
		tabConfigList.add(rebatePlanCalculationTab);
		tabConfigList.add(notesTab);
		rpTabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addNotesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("notesTab");
		layoutConfig.setAuthorizationIncluded(true);
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.NOTES_TAB);

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

	private void addRpSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig rpGtnBtnLayout = rpAddConfigProvider
				.getHorizontalLayoutConfig(GtnFrameworkStringConstants.RP_INFO_ADD_VIEW_BUTTON_LAYOUT, false, null);
		rpGtnBtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		rpGtnBtnLayout.setSpacing(true);
		rpGtnBtnLayout.setMargin(true);
		rpGtnBtnLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_20);
		componentList.add(rpGtnBtnLayout);

		addSaveButtonComponent(componentList);
		addBackButtonComponent(componentList);
		addResetButtonComponent(componentList);
		addDeleteButtonComponent(componentList);
	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig backBtnLayout = rpAddConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanAddViewBackButtonlayout", true, GtnFrameworkStringConstants.RP_INFO_ADD_VIEW_BUTTON_LAYOUT);
		componentList.add(backBtnLayout);

		GtnUIFrameworkComponentConfig backButtonConfig = rpAddConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanAddViewBackButton", true, "rebatePlanAddViewBackButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		backButtonConfig.setAuthorizationIncluded(true);
		backButtonConfig.setComponentName("Back");
		componentList.add(backButtonConfig);

		List<GtnUIFrameWorkActionConfig> backActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = rpAddConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(" Back Confirmation ");
		alertParamsList.add("Any unsaved information will not be saved.\n Do you want to proceed?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = rpAddConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { "" }));
		onSucessActionConfig.add(navigationActionConfig);
		alertParamsList.add(onSucessActionConfig);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		backActionConfigList.add(confirmationActionConfig);

		backButtonConfig.setGtnUIFrameWorkActionConfigList(backActionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig saveBtnLayout = rpAddConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanAddViewAddSaveButtonlayout", true,
				GtnFrameworkStringConstants.RP_INFO_ADD_VIEW_BUTTON_LAYOUT);
		componentList.add(saveBtnLayout);

		GtnUIFrameworkComponentConfig saveButtonConfig = rpAddConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanAddViewAddSaveButton", true, "rebatePlanAddViewAddSaveButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		saveButtonConfig.setAuthorizationIncluded(true);
		saveButtonConfig.setComponentName("Save");
		componentList.add(saveButtonConfig);

		List<GtnUIFrameWorkActionConfig> saveActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionrRuleNameExist = rpAddConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionrRuleNameExist
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkRPConstants.SAVE_MANDATORY_ALERT }));
		saveActionConfigList.add(customActionrRuleNameExist);

		GtnUIFrameWorkActionConfig saveConfirmationAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.SAVE_CONFIRMATION_ACTION);
		saveConfirmationAction.addActionParameter("Confirmation");
		saveConfirmationAction.addActionParameter("Save record ");
		saveConfirmationAction.addActionParameter("rebatePlanInformationTabRebatePlanName");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customSavemAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		customSavemAction.addActionParameter(GtnFrameworkRPConstants.SAVE_ACTION);

		onSucessActionConfigList.add(customSavemAction);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = new GtnUIFrameWorkActionConfig();
		changeCaptionActionConfig.setActionType(GtnUIFrameworkActionType.CHANGE_CAPTION);
		changeCaptionActionConfig.addActionParameter(Arrays.asList(new String[] { "rebatePlanAddViewAddSaveButton" }));
		changeCaptionActionConfig.addActionParameter(Arrays.asList(new String[] { "UPDATE" }));
		onSucessActionConfigList.add(changeCaptionActionConfig);
		saveConfirmationAction.addActionParameter(onSucessActionConfigList);
		saveActionConfigList.add(saveConfirmationAction);
		saveButtonConfig.setGtnUIFrameWorkActionConfigList(saveActionConfigList);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig resetBtnLayout = rpAddConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanAddViewAAddResetButtonlayout", true,
				GtnFrameworkStringConstants.RP_INFO_ADD_VIEW_BUTTON_LAYOUT);
		componentList.add(resetBtnLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = rpAddConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanAddViewAAddResetButton", true, "rebatePlanAddViewAAddResetButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setAuthorizationIncluded(true);
		resetButtonConfig.setComponentName("Reset");
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig editActionConfig = rpAddConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnUIFrameWorkResetOnEditModeAction.class.getName());
		resetActionConfigList.add(editActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig deleteBtnLayout = rpAddConfigProvider.getHorizontalLayoutConfig(
				"rebatePlanAddViewAAddDeleteButtonlayout", true,
				GtnFrameworkStringConstants.RP_INFO_ADD_VIEW_BUTTON_LAYOUT);
		componentList.add(deleteBtnLayout);

		GtnUIFrameworkComponentConfig deleteButtonConfig = rpAddConfigProvider.getUIFrameworkComponentConfig(
				"rebatePlanAddViewAAddDeleteButton", true, "rebatePlanAddViewAAddDeleteButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setAuthorizationIncluded(true);
		deleteButtonConfig.setComponentName("Delete");
		componentList.add(deleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> deleteActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig setModeActionConfig = rpAddConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.DELETE }));
		deleteActionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig deleteConfirmationAction = new GtnUIFrameWorkActionConfig();
		deleteConfirmationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteConfirmationAction
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkRPConstants.DELETE_CONFIRMATION }));

		deleteActionConfigList.add(deleteConfirmationAction);

		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(deleteActionConfigList);

	}
}
