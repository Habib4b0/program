package com.stpl.gtn.gtn2o.ui.module.complianceanddeductionrulesconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkCDRConstants;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkComplianceAndDeductionRulesAddConfig {

	private final GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

	public GtnUIFrameworkViewConfig getAddView() {
		gtnFrameworkConfigurationFactory.setViewId("complianceAndDeductionRulesAddView");
		gtnFrameworkConfigurationFactory.setViewName("complianceAndDeductionRulesAddView");
		gtnFrameworkConfigurationFactory.setIsdefaultView(false);
		GtnUIFrameworkViewConfig view = gtnFrameworkConfigurationFactory.buildView();
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addTabLayout(componentList);
		addSaveButtonLayout(componentList);
	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.COMPLIANCE_AND_DEDUCTION_RULES_TABSHEET_LAYOUT,
				"complianceAndDeductionRulesTabsheetLayout", false, GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		addTabSheet(componentList);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig tabSheetConfig = gtnFrameworkConfigurationFactory.buildComponentConfig("tabSheet",
				"Tab Sheet", true, GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setAuthorizationIncluded(true);
		tabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		tabSheetConfig.setParentComponentId("complianceAndDeductionRulesTabsheetLayout");
		componentList.add(tabSheetConfig);

		GtnUIFrameworkTabConfig companyInformationTab = new GtnUIFrameworkTabConfig();
		companyInformationTab.setComponentId("complianceAndDeductionRulesInformationTab");
		companyInformationTab.setTabCaption("Rules Information");
		List<GtnUIFrameworkComponentConfig> ruleInformationTabConfigList = new ArrayList<>();
		companyInformationTab.setTabLayoutComponentConfigList(ruleInformationTabConfigList);
		new GtnFrameworkComplianceAndDeductionRulesInformationTabConfig()
				.addIdentifierTab(ruleInformationTabConfigList);

		GtnUIFrameworkTabConfig notesTab = new GtnUIFrameworkTabConfig();
		notesTab.setComponentId(GtnFrameworkCommonConstants.NOTES_TAB);
		notesTab.setTabCaption("Notes");
		List<GtnUIFrameworkComponentConfig> notesTabConfigList = new ArrayList<>();
		notesTab.setTabLayoutComponentConfigList(notesTabConfigList);
		addNotesTab(notesTabConfigList);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(companyInformationTab);
		tabConfigList.add(notesTab);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addNotesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.NOTES_TAB, GtnFrameworkCommonConstants.NOTES_TAB, false,
				GtnUIFrameworkComponentType.NOTES_TAB);
		layoutConfig.setTabComponent(true);
		layoutConfig.setAuthorizationIncluded(true);

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
		GtnUIFrameworkLayoutConfig layout = gtnFrameworkConfigurationFactory
				.buildLayoutConfig(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = gtnFrameworkConfigurationFactory.buildComponentConfig(
				GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT, GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT, false,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addSaveButtonComponent(componentList);
		addBackButtonComponent(componentList);
		addResetButtonComponent(componentList);

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"complianceDeductionAddBackButton", "BACK", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT);
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setDefaultActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE, "");

		resetMap.put("ruleDetailsLineType", null);
		resetMap.put("ruleDetailsKeyWord", null);
		resetMap.put("ruleDetailsOperator", null);
		resetMap.put("ruleDetailsValue", "");
		resetMap.put("ruleDetailsComparison", null);
		resetMap.put("ruleDetailsOperatorOne", null);

		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NO, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_NAME, "");
		resetMap.put(GtnFrameworkCommonConstants.RULE_INFORMATION_RULE_TYPE, null);
		resetMap.put("ruleInformationRuleCategory", null);

		setDefaultActionConfig.addActionParameter(resetMap);
		actionConfigList.add(setDefaultActionConfig);

		GtnUIFrameWorkActionConfig confirmationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(" Back Confirmation ");
		alertParamsList.add(
				" Are you sure you want to navigate back to the Deduction Rules landing screen? \nYou will lose all unsaved data if you proceed ?");

		List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { "" }));
		onSucessActionConfig.add(navigationActionConfig);
		alertParamsList.add(onSucessActionConfig);

		confirmationActionConfig.setActionParameterList(alertParamsList);

		actionConfigList.add(confirmationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("cDRAddSaveButton", "SAVE", true, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT);
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(
				new String[] { "ruleInformationRuleType", "ruleInformationRuleName", "ruleInformationRuleNo" }));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.AND);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("Missing Required Fields");
		alertParams.add("You cannot save this record until all required fields have been populated");

		alertActionConfig.setActionParameterList(alertParams);
		onFailure.add(alertActionConfig);
		validationActionConfig.addActionParameter(onFailure);

		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig detailsSizeValidActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		detailsSizeValidActionConfig.setFieldValues(
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE }));

		detailsSizeValidActionConfig.addActionParameter(GtnUIFrameworkValidationType.AND);

		List<GtnUIFrameWorkActionConfig> ondetailSizeValidationFailure = new ArrayList<>();

		GtnUIFrameWorkActionConfig ondetailSizeValidationFailureActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> ondetailSizeValidationFailureParams = new ArrayList<>();
		ondetailSizeValidationFailureParams.add("Missing Required Fields");
		ondetailSizeValidationFailureParams.add("Add atleast one Rule Detail.");

		ondetailSizeValidationFailureActionConfig.setActionParameterList(ondetailSizeValidationFailureParams);

		ondetailSizeValidationFailure.add(ondetailSizeValidationFailureActionConfig);
		detailsSizeValidActionConfig.addActionParameter(ondetailSizeValidationFailure);

		actionConfigList.add(detailsSizeValidActionConfig);

		GtnUIFrameWorkActionConfig customActionrRuleNameExist = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionrRuleNameExist
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.RULE_DUPLICATE_CHECK }));
		actionConfigList.add(customActionrRuleNameExist);

		GtnUIFrameWorkActionConfig customAction = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.SAVE_FINAL_CHECK }));
		actionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig custoSavemAction = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		custoSavemAction
				.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkCDRConstants.SAVE_CONFIRMATION }));
		actionConfigList.add(custoSavemAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resetButtonConfig = gtnFrameworkConfigurationFactory.buildComponentConfig(
				"complianceDeductionAddResetButton", "RESET", true, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.SAVE_BUTTONLAYOUT);
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> paramsList = new ArrayList<>();
		paramsList.add("Reset Confirmation");
		paramsList.add("Are you sure you want to reset all values?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(GtnFrameworkCommonConstants.RULE_DETAILSATTACH_RESULT_TABLE, "");

		resetMap.put("ruleDetailsLineType", null);
		resetMap.put("ruleDetailsKeyWord", null);
		resetMap.put("ruleDetailsOperator", null);
		resetMap.put("ruleDetailsValue", "");
		resetMap.put("ruleDetailsComparison", null);
		resetMap.put("ruleDetailsOperatorOne", null);

		resetMap.put("ruleInformationRuleNo", "");
		resetMap.put("ruleInformationRuleName", "");
		resetMap.put("ruleInformationRuleType", null);
		resetMap.put("ruleInformationRuleCategory", null);

		paramsList.add(resetMap);

		resetActionConfig.setActionParameterList(paramsList);
		actionConfigList.add(resetActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

}
