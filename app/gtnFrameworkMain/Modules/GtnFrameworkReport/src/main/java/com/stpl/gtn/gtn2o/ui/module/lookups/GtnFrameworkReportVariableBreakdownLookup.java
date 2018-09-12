package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkReportConfirmedCloseAction;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownFrequencyLoadAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownGridLoadActionBasedOnHistory;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownMassUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingVariableBreakdownSubmitAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportVariableBreakdownLookup {

	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getVariableBreakdownLookUpView(String namespace) {

		GtnUIFrameworkViewConfig variableBreakdownView = new GtnUIFrameworkViewConfig();
		variableBreakdownView.setViewName("Variable Breakdown");
		variableBreakdownView.setViewId(GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_LOOKUP_VIEW_ID);
		variableBreakdownView.setDefaultView(false);
		variableBreakdownView.setResetAllowed(true);
		addComponentList(variableBreakdownView, namespace);
		return variableBreakdownView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addVariableBreakdownMainLayout(componentList, namespace);
	}

	private void addVariableBreakdownMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig variableBreakdownLayout = new GtnUIFrameworkComponentConfig();
		variableBreakdownLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownLayout");
		variableBreakdownLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownLayout.setAddToParent(false);
		variableBreakdownLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig variableBreakdownConfig = new GtnUIFrameworkLayoutConfig();
		variableBreakdownConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		variableBreakdownLayout.setGtnLayoutConfig(variableBreakdownConfig);
		componentList.add(variableBreakdownLayout);
		getVariableBreakdownPopupComponents(componentList, namespace);
	}

	public void getVariableBreakdownPopupComponents(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownPopupConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig variableBreakdownPopupLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownPopupLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		variableBreakdownPopupConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownPopupConfig.setGtnLayoutConfig(variableBreakdownPopupLayout);
		variableBreakdownPopupConfig.setAddToParent(true);
		variableBreakdownPopupConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownPopupConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownPopupConfig");
		variableBreakdownPopupConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownLayout");
		variableBreakdownPopupConfig.setSpacing(true);
		variableBreakdownPopupConfig.setMargin(true);
		componentList.add(variableBreakdownPopupConfig);

		addVariableBreakdownMassUpdateRootPanel(componentList, namespace);
		addVariableBreakdownResultsPanel(componentList, variableBreakdownPopupConfig.getComponentId());
		addVariableBreakdownControlButtonComponent(componentList, variableBreakdownPopupConfig.getComponentId(),
				namespace);
	}

	private void addVariableBreakdownFrequencyAndHistoryComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownFrequencyAndHistoryConfig = layoutsConfig
				.getHorizontalLayoutConfig("resultLayoutConfig", parentId);
		variableBreakdownFrequencyAndHistoryConfig.addComponentStyle("stpl-margin-custom-variable");
		componentList.add(variableBreakdownFrequencyAndHistoryConfig);

		GtnUIFrameworkLayoutConfig variableBreakdownFrequencyAndHistoryLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownFrequencyAndHistoryLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig variableBreakdownFrequencyAndHistoryResultLayout = new GtnUIFrameworkComponentConfig();
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownFrequencyAndHistoryResultLayout
				.setComponentId("variableBreakdownFrequencyAndHistoryResultLayout");
		variableBreakdownFrequencyAndHistoryResultLayout
				.setComponentName("variableBreakdownFrequencyAndHistoryResultLayout");
		variableBreakdownFrequencyAndHistoryResultLayout.setAddToParent(true);
		variableBreakdownFrequencyAndHistoryResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownFrequencyAndHistoryResultLayout
				.setParentComponentId(variableBreakdownFrequencyAndHistoryConfig.getComponentId());
		variableBreakdownFrequencyAndHistoryResultLayout
				.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		variableBreakdownFrequencyAndHistoryResultLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		variableBreakdownFrequencyAndHistoryResultLayout.setGtnLayoutConfig(variableBreakdownFrequencyAndHistoryLayout);
		componentList.add(variableBreakdownFrequencyAndHistoryResultLayout);

		GtnUIFrameworkComponentConfig variableBreakdownFrequencyLayoutConfig = layoutsConfig.getHorizontalLayoutConfig(
				"variableBreakdownFrequencyLayoutConfig",
				variableBreakdownFrequencyAndHistoryResultLayout.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownFrequencyConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownFrequencyConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownFrequencyConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownFrequencyConfig");
		variableBreakdownFrequencyConfig.setComponentName("Frequency: ");
		variableBreakdownFrequencyConfig.setAddToParent(true);
		variableBreakdownFrequencyConfig.setParentComponentId(variableBreakdownFrequencyLayoutConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig variableBreakdownFrequencyLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownFrequencyLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.REPORT_CONFIG_FREQUENCY);
		variableBreakdownFrequencyLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownFrequencyConfig.setGtnComboboxConfig(variableBreakdownFrequencyLoadConfig);

		GtnUIFrameworkComponentConfig variableBreakdownHistoryLayoutConfig = layoutsConfig.getHorizontalLayoutConfig(
				"variableBreakdownHistoryLayoutConfig",
				variableBreakdownFrequencyAndHistoryResultLayout.getComponentId());

		GtnUIFrameworkComponentConfig variableBreakdownHistoryConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownHistoryConfig.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownHistoryConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownHistoryConfig");
		variableBreakdownHistoryConfig.setComponentName("History: ");
		variableBreakdownHistoryConfig.setAddToParent(true);
		variableBreakdownHistoryConfig.setParentComponentId(variableBreakdownHistoryLayoutConfig.getComponentId());

		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.addActionParameter(GtnReportingVariableBreakdownGridLoadActionBasedOnHistory.class.getName());
		variableBreakdownHistoryConfig.addGtnUIFrameWorkActionConfig(actionConfig);

		GtnUIFrameworkComboBoxConfig variableBreakdownHistoryLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownHistoryLoadConfig.setComboBoxType(GtnFrameworkReportStringConstants.STATUS);
		variableBreakdownHistoryLoadConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		variableBreakdownHistoryConfig.setGtnComboboxConfig(variableBreakdownHistoryLoadConfig);

		componentList.add(variableBreakdownFrequencyLayoutConfig);
		componentList.add(variableBreakdownFrequencyConfig);
		componentList.add(variableBreakdownHistoryLayoutConfig);
		componentList.add(variableBreakdownHistoryConfig);
	}

	private void addVariableBreakdownMassUpdateRootPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownMassUpdateRootPanelConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownMassUpdateRootPanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		variableBreakdownMassUpdateRootPanelConfig.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownMassUpdateRootPanelConfig");
		variableBreakdownMassUpdateRootPanelConfig.setComponentName("Mass Update");
		variableBreakdownMassUpdateRootPanelConfig.addComponentStyle("stpl-margin-left-10");
		variableBreakdownMassUpdateRootPanelConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_11);
		variableBreakdownMassUpdateRootPanelConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownPopupConfig");
		variableBreakdownMassUpdateRootPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownMassUpdateRootPanelConfig.setAddToParent(true);
		componentList.add(variableBreakdownMassUpdateRootPanelConfig);
		addVariableBreakdownMassUpdateLayout(componentList, namespace);
	}

	private void addVariableBreakdownMassUpdateLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig variableBreakdownMassUpdateLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownMassUpdateLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig variableBreakdownMassUpdateConfig = new GtnUIFrameworkComponentConfig();
		variableBreakdownMassUpdateConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownMassUpdateConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownMassUpdateConfig");
		variableBreakdownMassUpdateConfig.setComponentName("MassUpdateLayout");
		variableBreakdownMassUpdateConfig.setAddToParent(true);
		variableBreakdownMassUpdateConfig.setComponentWidth("98%");
		variableBreakdownMassUpdateConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "variableBreakdownMassUpdateRootPanelConfig");
		variableBreakdownMassUpdateConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		variableBreakdownMassUpdateConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		variableBreakdownMassUpdateConfig.setGtnLayoutConfig(variableBreakdownMassUpdateLayout);
		componentList.add(variableBreakdownMassUpdateConfig);

		addVariableBreakdownValueComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),
				namespace);
		addVariableBreakdownFileorProjectionComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),
				namespace);
		addVariableBreakdownStartPeriodComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),
				namespace);
		addVariableBreakdownEndPeriodComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),
				namespace);
		addVariableBreakdownPopulateButtonComponent(componentList, variableBreakdownMassUpdateConfig.getComponentId(),
				namespace);
	}

	private void addVariableBreakdownValueComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownValueConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownValueConfig", parentId);
		variableBreakdownValueConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_11);
		componentList.add(variableBreakdownValueConfig);

		GtnUIFrameworkComponentConfig variableBreakdownValue = new GtnUIFrameworkComponentConfig();
		variableBreakdownValue.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownValue
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownValue");
		variableBreakdownValue.setComponentName("Value: ");
		variableBreakdownValue.setAddToParent(true);
		variableBreakdownValue.setParentComponentId(variableBreakdownValueConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig variableBreakdownValueLoadConfig = new GtnUIFrameworkComboBoxConfig();
		variableBreakdownValueLoadConfig.setItemValues(Arrays.asList(1, 2, 3));
		variableBreakdownValueLoadConfig
				.setItemCaptionValues(Arrays.asList("Actuals", "Projections", "P & L (Accruals)"));
		variableBreakdownValue.setGtnComboboxConfig(variableBreakdownValueLoadConfig);

		componentList.add(variableBreakdownValue);
	}

	private void addVariableBreakdownFileorProjectionComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownValueFileorProjectionConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownValueFileorProjectionConfig", parentId);
		variableBreakdownValueFileorProjectionConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_11);
		componentList.add(variableBreakdownValueFileorProjectionConfig);

		GtnUIFrameworkComponentConfig variableBreakdownValueFileorProjection = new GtnUIFrameworkComponentConfig();
		variableBreakdownValueFileorProjection.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownValueFileorProjection.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownValueFileorProjection");
		variableBreakdownValueFileorProjection.setComponentName("File/Projection: ");
		variableBreakdownValueFileorProjection.setAddToParent(true);
		variableBreakdownValueFileorProjection
				.setParentComponentId(variableBreakdownValueFileorProjectionConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig variableBreakdownValueFileorProjectionLoadConfig = new GtnUIFrameworkComboBoxConfig();

		variableBreakdownValueFileorProjectionLoadConfig
				.setItemValues(Arrays.asList("Ex-Factory Sales", "Latest Approved"));
		variableBreakdownValueFileorProjectionLoadConfig
				.setItemCaptionValues(Arrays.asList("Ex-Factory Sales", "Latest Approved"));
		variableBreakdownValueFileorProjection.setGtnComboboxConfig(variableBreakdownValueFileorProjectionLoadConfig);
		componentList.add(variableBreakdownValueFileorProjection);
	}

	private void addVariableBreakdownStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownStartPeriodConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownStartPeriodConfig", parentId);
		variableBreakdownStartPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_11);
		componentList.add(variableBreakdownStartPeriodConfig);

		GtnUIFrameworkComponentConfig variableBreakdownStartPeriod = new GtnUIFrameworkComponentConfig();
		variableBreakdownStartPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownStartPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownStartPeriod");
		variableBreakdownStartPeriod.setComponentName("Start Period: ");
		variableBreakdownStartPeriod.setAddToParent(true);
		variableBreakdownStartPeriod.setParentComponentId(variableBreakdownStartPeriodConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig variableBreakdownStartPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();

		variableBreakdownStartPeriodLoadConfig.setModuleName("report");
		variableBreakdownStartPeriodLoadConfig.setItemValues(new ArrayList());
		variableBreakdownStartPeriodLoadConfig.setItemCaptionValues(new ArrayList());

		variableBreakdownStartPeriod.setGtnComboboxConfig(variableBreakdownStartPeriodLoadConfig);

		componentList.add(variableBreakdownStartPeriod);
	}

	private void addVariableBreakdownEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownEndPeriodConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownEndPeriodConfig", parentId);
		variableBreakdownEndPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_11);
		componentList.add(variableBreakdownEndPeriodConfig);

		GtnUIFrameworkComponentConfig variableBreakdownEndPeriod = new GtnUIFrameworkComponentConfig();
		variableBreakdownEndPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		variableBreakdownEndPeriod.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownEndPeriod");
		variableBreakdownEndPeriod.setComponentName("End Period: ");
		variableBreakdownEndPeriod.setAddToParent(true);
		variableBreakdownEndPeriod.setParentComponentId(variableBreakdownEndPeriodConfig.getComponentId());

		GtnUIFrameworkComboBoxConfig variableBreakdownEndPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();

		variableBreakdownEndPeriodLoadConfig.setModuleName("report");
		variableBreakdownEndPeriodLoadConfig.setItemValues(new ArrayList());
		variableBreakdownEndPeriodLoadConfig.setItemCaptionValues(new ArrayList());

		variableBreakdownEndPeriod.setGtnComboboxConfig(variableBreakdownEndPeriodLoadConfig);

		componentList.add(variableBreakdownEndPeriod);
	}

	private void addVariableBreakdownPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownPopulateButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownPopulateButtonConfig", parentId);
		componentList.add(variableBreakdownPopulateButtonConfig);

		GtnUIFrameworkComponentConfig variableBreakdownPopulateButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownPopulateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownPopulateButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownPopulateButton");
		variableBreakdownPopulateButton.setComponentName("POPULATE");
		variableBreakdownPopulateButton.setAddToParent(true);
		variableBreakdownPopulateButton.setParentComponentId(variableBreakdownPopulateButtonConfig.getComponentId());

		GtnUIFrameWorkActionConfig variableBreakDownPopulateLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableBreakDownPopulateLoadAction
				.addActionParameter(GtnReportingVariableBreakdownMassUpdateAction.class.getName());
		variableBreakDownPopulateLoadAction.addActionParameter("reportOptionsTab_variableBreakdownValue");
		variableBreakDownPopulateLoadAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_FILE_OR_PROJECTION);
		variableBreakDownPopulateLoadAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_START_PERIOD);
		variableBreakDownPopulateLoadAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_REPORT_OPTIONS_END_PERIOD);
		variableBreakDownPopulateLoadAction.addActionParameter(
				GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_RESULTS_LAYOUT_PAGED_TABLE_COMPONENT);

		variableBreakdownPopulateButton.addGtnUIFrameWorkActionConfig(variableBreakDownPopulateLoadAction);
		componentList.add(variableBreakdownPopulateButton);
	}

	private void addVariableBreakdownControlButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentId, String namespace) {
		GtnUIFrameworkComponentConfig variableBreakdownControlButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownControlButtonConfig", parentId);
		componentList.add(variableBreakdownControlButtonConfig);

		GtnUIFrameworkComponentConfig variableBreakdownSubmitButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownSubmitButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownSubmitButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownSubmitButton");
		variableBreakdownSubmitButton.setComponentName("SUBMIT");
		variableBreakdownSubmitButton.setAddToParent(true);
		variableBreakdownSubmitButton.setParentComponentId(variableBreakdownControlButtonConfig.getComponentId());

		List<GtnUIFrameWorkActionConfig> variableBreakdownSubmitActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig variableBreakDownSubmitAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableBreakDownSubmitAction.addActionParameter(GtnReportingVariableBreakdownSubmitAction.class.getName());

		variableBreakdownSubmitActionConfigList.add(variableBreakDownSubmitAction);

		GtnUIFrameWorkActionConfig variableBreakDownSubmitClosePopup = new GtnUIFrameWorkActionConfig();
		variableBreakDownSubmitClosePopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		variableBreakDownSubmitClosePopup
				.addActionParameter(GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_LOOKUP_VIEW_ID);

		variableBreakdownSubmitActionConfigList.add(variableBreakDownSubmitClosePopup);

		variableBreakdownSubmitButton.setGtnUIFrameWorkActionConfigList(variableBreakdownSubmitActionConfigList);

		GtnUIFrameworkComponentConfig variableBreakdownResetButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownResetButton");
		variableBreakdownResetButton.setComponentName("RESET");
		variableBreakdownResetButton.setAddToParent(true);
		variableBreakdownResetButton.setParentComponentId(variableBreakdownControlButtonConfig.getComponentId());

		List<Object> actionParamForReset = new ArrayList<>();
		GtnUIFrameWorkActionConfig variableBreakdownResetButtonConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		variableBreakdownResetButtonConfirmationActionConfig
				.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		actionParamForReset.add("Reset Confirmation");
		actionParamForReset.add("Are you sure you want to reset the popup?");
		List<GtnUIFrameWorkActionConfig> actionConfigListForReset = new ArrayList<>();
		GtnUIFrameWorkActionConfig variableBreakdownResetButtonAction = new GtnUIFrameWorkActionConfig();
		variableBreakdownResetButtonAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableBreakdownResetButtonAction
				.addActionParameter(GtnReportingVariableBreakdownFrequencyLoadAction.class.getName());
		variableBreakdownResetButtonAction
				.addActionParameter("reportLandingScreen_landingScreenVariableBreakdownFrequencyConfig");
		variableBreakdownResetButtonAction.addActionParameter("reportOptionsTab_variableBreakdownFrequencyConfig");
		variableBreakdownResetButtonAction.addActionParameter("reportingLandingScreen");

		actionConfigListForReset.add(variableBreakdownResetButtonAction);
		actionParamForReset.add(actionConfigListForReset);

		variableBreakdownResetButtonConfirmationActionConfig.setActionParameterList(actionParamForReset);

		variableBreakdownResetButton
				.addGtnUIFrameWorkActionConfig(variableBreakdownResetButtonConfirmationActionConfig);

		GtnUIFrameworkComponentConfig variableBreakdownCloseButton = new GtnUIFrameworkComponentConfig();
		variableBreakdownCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		variableBreakdownCloseButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "variableBreakdownCloseButton");
		variableBreakdownCloseButton.setComponentName("CLOSE");
		variableBreakdownCloseButton.setAddToParent(true);
		variableBreakdownCloseButton.setParentComponentId(variableBreakdownControlButtonConfig.getComponentId());

		GtnUIFrameWorkActionConfig variableBreakdownCloseButtonpopup = new GtnUIFrameWorkActionConfig();
		variableBreakdownCloseButtonpopup.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableBreakdownCloseButtonpopup.addActionParameter(GtnFrameworkReportConfirmedCloseAction.class.getName());
		variableBreakdownCloseButton.addGtnUIFrameWorkActionConfig(variableBreakdownCloseButtonpopup);

		componentList.add(variableBreakdownSubmitButton);
		componentList.add(variableBreakdownResetButton);
		componentList.add(variableBreakdownCloseButton);
	}

	private void addVariableBreakdownResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig variableBreakdownResultsPanel = new GtnUIFrameworkComponentConfig();
		variableBreakdownResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		variableBreakdownResultsPanel.setComponentId("variableBreakdownResultsPanel");
		variableBreakdownResultsPanel.setParentComponentId(parentId);
		variableBreakdownResultsPanel.setAddToParent(true);
		variableBreakdownResultsPanel.addComponentStyle("stpl-margin-left-10");
		variableBreakdownResultsPanel.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_11);
		variableBreakdownResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(variableBreakdownResultsPanel);
		GtnUIFrameworkLayoutConfig variableBreakdownColLayout = new GtnUIFrameworkLayoutConfig();
		variableBreakdownColLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig variableBreakdownResultsLayout = new GtnUIFrameworkComponentConfig();
		variableBreakdownResultsLayout
				.setComponentId(GtnFrameworkReportStringConstants.VARIABLE_BREAKDOWN_RESULTS_LAYOUT);
		variableBreakdownResultsLayout.setParentComponentId(variableBreakdownResultsPanel.getComponentId());
		variableBreakdownResultsLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownResultsLayout.setGtnLayoutConfig(variableBreakdownColLayout);
		variableBreakdownResultsLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownResultsLayout.setAddToParent(true);

		componentList.add(variableBreakdownResultsLayout);
		addVariableBreakdownFrequencyAndHistoryComponent(componentList,
				GtnFrameworkReportStringConstants.VARIABLE_BREAKDOWN_RESULTS_LAYOUT,
				GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB);
		addPagedTableComponent(componentList, GtnFrameworkReportStringConstants.VARIABLE_BREAKDOWN_RESULTS_LAYOUT);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig variableBreakdownLookupResultsPagedTableComponentLayout = layoutsConfig
				.getHorizontalLayoutConfig("variableBreakdownLookupResultsPagedTableComponentLayout", parentId);
		variableBreakdownLookupResultsPagedTableComponentLayout.setComponentWidth("100%");
		componentList.add(variableBreakdownLookupResultsPagedTableComponentLayout);
		GtnUIFrameworkComponentConfig variableBreakdownLookupResultsPagedTableComponent = new GtnUIFrameworkComponentConfig();
		variableBreakdownLookupResultsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		variableBreakdownLookupResultsPagedTableComponent.setComponentId(
				parentId + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonLookupResultsPagedTableComponent");
		variableBreakdownLookupResultsPagedTableComponent.setComponentName("Results");
		variableBreakdownLookupResultsPagedTableComponent
				.setParentComponentId(variableBreakdownLookupResultsPagedTableComponentLayout.getComponentId());
		variableBreakdownLookupResultsPagedTableComponent.setAddToParent(true);
		variableBreakdownLookupResultsPagedTableComponent.setComponentWidth("1000px");
		List<String> variableBreakdownLookupTableStyle = new ArrayList<>();
		variableBreakdownLookupTableStyle.add("filterbar");
		variableBreakdownLookupTableStyle.add("v-has-width");
		variableBreakdownLookupTableStyle.add("v-table-filterbar");
		variableBreakdownLookupTableStyle.add("table-header-normal");
		variableBreakdownLookupResultsPagedTableComponent.setComponentWidth("100%");
		variableBreakdownLookupResultsPagedTableComponent.setComponentStyle(variableBreakdownLookupTableStyle);

		componentList.add(variableBreakdownLookupResultsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig variableBreakdownLookupResultsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		variableBreakdownLookupResultsPagedTableConfig.setEditable(false);
		variableBreakdownLookupResultsPagedTableConfig.setFilterBar(true);
		variableBreakdownLookupResultsPagedTableConfig.setPageLength(10);
		variableBreakdownLookupResultsPagedTableConfig.setItemPerPage(10);
		variableBreakdownLookupResultsPagedTableConfig.setSelectable(true);
		variableBreakdownLookupResultsPagedTableConfig.setPaginationOff(true);
		variableBreakdownLookupResultsPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		variableBreakdownLookupResultsPagedTableConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		variableBreakdownLookupResultsPagedTableConfig
				.setGridColumnHeader(GtnWsReportConstants.GTN_WS_REPORT_VARIABLE_BREAKDOWN_TABLE_HEADERS_SERVICE);
		variableBreakdownLookupResultsPagedTableConfig.setEnableCheckBoxInGridHeader(true);
		variableBreakdownLookupResultsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		variableBreakdownLookupResultsPagedTableConfig
				.setColumnHeaders(GtnFrameworkReportStringConstants.getVariableBreakdownHeader());
		variableBreakdownLookupResultsPagedTableConfig.setTableColumnMappingId(new Object[] {});

		GtnUIFrameWorkActionConfig variableBreakDownHeaderLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		variableBreakDownHeaderLoadAction
				.addActionParameter("reportLandingScreen_landingScreenVariableBreakdownFrequencyConfig");
		variableBreakDownHeaderLoadAction.addActionParameter("reportLandingScreen_fromPeriod");
		variableBreakDownHeaderLoadAction.addActionParameter("reportLandingScreen_STATUS");

		variableBreakdownLookupResultsPagedTableConfig.setGtnUIFrameWorkActionConfig(variableBreakDownHeaderLoadAction);
		variableBreakdownLookupResultsPagedTableConfig.setGridHeaderCustomClassLoadURL(
				GtnFrameworkReportStringConstants.REPORT_VARIABLE_BREAKDOWN_HEADER_ACTION);
		variableBreakdownLookupResultsPagedTableComponent
				.setGtnPagedTableConfig(variableBreakdownLookupResultsPagedTableConfig);

	}
}
