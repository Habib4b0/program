/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.deductioncalendarconfig;

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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkConfigurationFactory;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnFrameworkDCAddConfig {

	private final GtnFrameworkConfigurationFactory gtnFrameworkConfigurationFactory = new GtnFrameworkConfigurationFactory();

	public GtnUIFrameworkViewConfig getAddView() {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("dcAddView");
		view.setViewId("dcAddView");
		view.setDefaultView(false);
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

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Rebate Olan In formation Panel");
		panel.setComponentId("dcInfoPanel");
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(false);

		componentList.add(panel);

	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("dcAddViewTabsheetLayout");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		layoutConfig.setAddToParent(false);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		new GtnFrameworkDCCustomerConfig().addCustomerSearchTab(componentList);
		new GtnFrameworkDCItemTabConfig().addDCItemrTab(componentList);
		new GtnFrameworkDCDedctionDetailsConfig().addDeductionDetailsTab(componentList);
		addNotesTab(componentList);

		addTabSheet(componentList);
	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId("dcAddViewtabSheet");
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		tabSheetConfig.setAddToParent(true);
		tabSheetConfig.setParentComponentId("dcAddViewTabsheetLayout");
		componentList.add(tabSheetConfig);

		GtnUIFrameworkTabConfig dcInformationTab = new GtnUIFrameworkTabConfig();
		dcInformationTab.setComponentId("customerSelectionTab");
		dcInformationTab.setTabCaption("Customer Selection");

		GtnUIFrameworkTabConfig dcCalculationTab = new GtnUIFrameworkTabConfig();
		dcCalculationTab.setComponentId("itemSelectionTab");
		dcCalculationTab.setTabCaption("Item Selection ");

		GtnUIFrameworkTabConfig deductionDetails = new GtnUIFrameworkTabConfig();
		deductionDetails.setComponentId("deductionDetailsTab");
		deductionDetails.setTabCaption("Deduction Details ");

		GtnUIFrameworkTabConfig notesTab = new GtnUIFrameworkTabConfig();
		notesTab.setComponentId("notesTab");
		notesTab.setTabCaption("Notes");

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(dcInformationTab);
		tabConfigList.add(dcCalculationTab);
		tabConfigList.add(deductionDetails);
		tabConfigList.add(notesTab);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

	}

	private void addNotesTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("notesTab");
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

	private void addSaveButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.DC_ADD_VIEWSAVE_BUTTONLAYOUT);
		gtnLayout.setAddToParent(false);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addBackButtonComponent(componentList);
		addSaveButtonComponent(componentList);
		addDeleteButtonComponent(componentList);
		addResetButtonComponent(componentList);

	}

	private void addBackButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcAddViewBackButton");
		searchButtonConfig.setComponentName("Back");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.DC_ADD_VIEWSAVE_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig confirmationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(" Back Confirmation ");
		alertParamsList.add("Any unsaved information will not be saved.\n Do you want to proceed?");

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
		GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
		searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentId("dcAddViewAddSaveButton");
		searchButtonConfig.setComponentName("Save");
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.DC_ADD_VIEWSAVE_BUTTONLAYOUT);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionrRuleNameExist = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionrRuleNameExist.setActionParameterList(Arrays.asList(new Object[] {
				"com.stpl.gtn.gtn2o.ui.module.rebateplanconfig.logic.GtnUIFrameWorkSaveMandatoryAlert" }));
		actionConfigList.add(customActionrRuleNameExist);

		GtnUIFrameWorkActionConfig custoSavemAction = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		custoSavemAction.setActionParameterList(Arrays.asList(new Object[] {
				"com.stpl.gtn.gtn2o.ui.module.rebateplanconfig.logic.GtnUIFrameworkRPSaveConfirmationAction" }));

		actionConfigList.add(custoSavemAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resetButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("dcAddViewAAddResetButton", "RESET", true, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.DC_ADD_VIEWSAVE_BUTTONLAYOUT);
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> paramsList = new ArrayList<>();
		paramsList.add("Reset Confirmation");
		paramsList.add("Are you sure you want to reset all values?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put("ruleDetailsattachResultTable", "");

		resetMap.put("dcInformationTabRebatePlanType", null);
		resetMap.put("dcInformationTabRebateStatus", null);
		resetMap.put("dcCalculationsRebateStructure", null);
		resetMap.put("dcCalculationsRangeBasedOn", null);
		resetMap.put("dcCalculationsRebateBasedOn", null);
		resetMap.put("dcCalculationSelfGrowthFrom", null);
		resetMap.put("dcCalculationSelfGrowthTo", null);
		resetMap.put("dcCalculationMarketShareFrom", null);
		resetMap.put("dcCalculationMarketShareTo", null);
		resetMap.put("dcIdTop", "");
		resetMap.put("dcNameTop", "");
		resetMap.put("dcNoTop", "");
		resetMap.put("dcInformationTabRebatePlanID", "");
		resetMap.put("dcInformationTabRebatePlanName", "");
		resetMap.put("dcInformationTabRebatePlanNo", "");
		resetMap.put("dcInformationTabformulaType", "");
		resetMap.put("dcCalculationsNetSalesFormula", "");
		resetMap.put("dcCalculationsNetSalesRule", "");
		resetMap.put("dcCalculationSelfGrowthIndicator", "");
		resetMap.put("dcCalculationSelfGrowthReference", "");
		resetMap.put("dcCalculationMarketShareIndicator", "");
		resetMap.put("dcCalculationMarketShareReference", "");

		paramsList.add(resetMap);

		resetActionConfig.setActionParameterList(paramsList);
		actionConfigList.add(resetActionConfig);

		resetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig cDRDeleteButtonConfig = gtnFrameworkConfigurationFactory
				.buildComponentConfig("dcAddViewAAddDeleteButton", "DELETE", true, GtnUIFrameworkComponentType.BUTTON);
		cDRDeleteButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cDRDeleteButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.DC_ADD_VIEWSAVE_BUTTONLAYOUT);
		componentList.add(cDRDeleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig setModeActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.setActionParameterList(Arrays.asList(new Object[] { GtnUIFrameworkModeType.DELETE }));
		actionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig deleteConfirmationAction = new GtnUIFrameWorkActionConfig();
		deleteConfirmationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteConfirmationAction.setActionParameterList(Arrays.asList(new Object[] {
				"com.stpl.gtn.gtn2o.ui.module.rebateplanconfig.logic.GtnUIFrameworkRPDeleteConfirmationAction" }));

		actionConfigList.add(deleteConfirmationAction);

		GtnUIFrameWorkActionConfig navigationActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.setActionParameterList(Arrays.asList(new Object[] { "" }));
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = gtnFrameworkConfigurationFactory
				.buildActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add("rpSearchResultTable");

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(new String[] { "dcId", "dcNo", "dcName", "dcStatus", "dcType" }));

		actionConfigList.add(loadDataTableActionConfig);

		cDRDeleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}
}
