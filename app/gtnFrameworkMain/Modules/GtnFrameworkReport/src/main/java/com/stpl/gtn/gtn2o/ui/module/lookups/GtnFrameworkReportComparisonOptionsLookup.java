package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.List;

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
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingComparisonBreakdownGridResetAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingComparisonBreakdownMassUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.lookups.action.GtnReportingComparisonBreakdownSubmitAction;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportComparisonOptionsLookup {
	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getComparisonOptionsLookUpView(String namespace) {

		GtnUIFrameworkViewConfig comparisonOptionsLookupView = new GtnUIFrameworkViewConfig();
		comparisonOptionsLookupView.setViewName("Comparison Options");
		comparisonOptionsLookupView.setViewId("comparisonOptions");
		comparisonOptionsLookupView.setDefaultView(false);
		addComponentList(comparisonOptionsLookupView, namespace);
		return comparisonOptionsLookupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> comparisonOptionsLookupComponentList = new ArrayList<>();
		view.setGtnComponentList(comparisonOptionsLookupComponentList);
		addRootLayout(comparisonOptionsLookupComponentList, namespace);
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig comparisonOptionsLookupVLayout = new GtnUIFrameworkComponentConfig();
		comparisonOptionsLookupVLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonOptionsLookupVLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "rootLayout");
		comparisonOptionsLookupVLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonOptionsLookupVLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonOptionsLookupVLayout.setAddToParent(false);
		comparisonOptionsLookupVLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig comparisonOptionsLookupConf = new GtnUIFrameworkLayoutConfig();
		comparisonOptionsLookupConf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		comparisonOptionsLookupVLayout.setGtnLayoutConfig(comparisonOptionsLookupConf);
		componentList.add(comparisonOptionsLookupVLayout);
		getRootComponentForPopUp(componentList, namespace);
	}

	public void getRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(conf);
		gtnLayout.setAddToParent(true);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		gtnLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "rootLayout");
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		componentList.add(gtnLayout);

		addComparisonOptionsPanel(componentList, namespace);
		addMassUpdateRootPanel(componentList, namespace);
		addResultsPanel(componentList, gtnLayout.getComponentId());
		addControlButtonComponent(componentList, gtnLayout.getComponentId(), namespace);
	}

	private void addComparisonOptionsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig comparisonOptionsPanelConfig = new GtnUIFrameworkComponentConfig();
		comparisonOptionsPanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		comparisonOptionsPanelConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonOptionsPanel");
		comparisonOptionsPanelConfig.addComponentStyle("stpl-margin-left-9px");
		comparisonOptionsPanelConfig.addComponentStyle("stpl-margin-top-11");
		comparisonOptionsPanelConfig.setComponentName("Comparison Options");
		comparisonOptionsPanelConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		comparisonOptionsPanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		comparisonOptionsPanelConfig.setAddToParent(true);
		componentList.add(comparisonOptionsPanelConfig);
		addComparisonOptionsLayout(componentList, namespace);
	}

	private void addComparisonOptionsLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig comparisonOptionsLayoutConfig = new GtnUIFrameworkComponentConfig();
		comparisonOptionsLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		comparisonOptionsLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonOptionsLayout");
		comparisonOptionsLayoutConfig.setComponentName("Comparison Options Layout");
		comparisonOptionsLayoutConfig.setAddToParent(true);
		comparisonOptionsLayoutConfig.setComponentWidth("98%");
		comparisonOptionsLayoutConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonOptionsPanel");
		comparisonOptionsLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		comparisonOptionsLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		comparisonOptionsLayoutConfig.setGtnLayoutConfig(layoutConf);
		componentList.add(comparisonOptionsLayoutConfig);

		addComparisonBasisComponent(componentList, comparisonOptionsLayoutConfig.getComponentId());
		addFrequencyComponent(componentList, comparisonOptionsLayoutConfig.getComponentId());
		addPeriodRangeFromComponent(componentList, comparisonOptionsLayoutConfig.getComponentId());
		addPeriodRangeToComponent(componentList, comparisonOptionsLayoutConfig.getComponentId());
	}

	private void addComparisonBasisComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig comparisonBasisConfig = layoutsConfig
				.getHorizontalLayoutConfig("comparisonBasisConfig", parentId);
		comparisonBasisConfig.addComponentStyle("v-margin-custom-style-10");
		componentList.add(comparisonBasisConfig);

		GtnUIFrameworkComponentConfig comparisonBasis = new GtnUIFrameworkComponentConfig();
		comparisonBasis.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		comparisonBasis.setComponentId("comparisonOptionsLookup_comparisonBasis");
		comparisonBasis.setComponentName("Comparison Basis: ");
		comparisonBasis.setAddToParent(true);
		comparisonBasis.setParentComponentId(comparisonBasisConfig.getComponentId());
		comparisonBasis.addComponentStyle("stpl-margin-left-10");
		comparisonBasis.setEnable(false);
		comparisonBasis.setComponentHight("32px");
		componentList.add(comparisonBasis);
	}

	private void addFrequencyComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig frequencyConfig = layoutsConfig.getHorizontalLayoutConfig("frequencyConfig",
				parentId);
		frequencyConfig.addComponentStyle("v-margin-custom-style-10");

		componentList.add(frequencyConfig);

		GtnUIFrameworkComponentConfig frequency = new GtnUIFrameworkComponentConfig();
		frequency.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		frequency.setComponentId("comparisonOptionsLookup_frequencyConfig");
		frequency.setComponentName("Frequency: ");
		frequency.setAddToParent(true);
		frequency.setParentComponentId(frequencyConfig.getComponentId());
		frequency.setEnable(false);
		frequency.addComponentStyle("stpl-margin-left-10");
		frequency.setComponentHight("32px");
		componentList.add(frequency);
	}

	private void addPeriodRangeFromComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig periodRangeFromConfig = layoutsConfig
				.getHorizontalLayoutConfig("periodRangeFromConfig", parentId);
		periodRangeFromConfig.addComponentStyle("v-margin-custom-style-10");

		componentList.add(periodRangeFromConfig);

		GtnUIFrameworkComponentConfig periodRangeFrom = new GtnUIFrameworkComponentConfig();
		periodRangeFrom.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		periodRangeFrom.setComponentId("comparisonOptionsLookup_periodRangeFrom");
		periodRangeFrom.setComponentName("Period Range From: ");
		periodRangeFrom.setAddToParent(true);
		periodRangeFrom.setParentComponentId(periodRangeFromConfig.getComponentId());
		periodRangeFrom.setEnable(false);
		periodRangeFrom.addComponentStyle("stpl-margin-left-10");
		periodRangeFrom.setComponentHight("32px");
		componentList.add(periodRangeFrom);
	}

	private void addPeriodRangeToComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig periodRangeToConfig = layoutsConfig
				.getHorizontalLayoutConfig("periodRangeToConfig", parentId);
		periodRangeToConfig.addComponentStyle("v-margin-custom-style-10");

		componentList.add(periodRangeToConfig);

		GtnUIFrameworkComponentConfig periodRangeTo = new GtnUIFrameworkComponentConfig();
		periodRangeTo.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		periodRangeTo.setComponentId("comparisonOptionsLookup_periodRangeTo");
		periodRangeTo.setComponentName("Period Range To: ");
		periodRangeTo.setAddToParent(true);
		periodRangeTo.setParentComponentId(periodRangeToConfig.getComponentId());
		periodRangeTo.setEnable(false);
		periodRangeTo.addComponentStyle("stpl-margin-left-10");
		periodRangeTo.setComponentHight("32px");
		componentList.add(periodRangeTo);
	}

	private void addMassUpdateRootPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig massUpdatePanelConfig = new GtnUIFrameworkComponentConfig();
		massUpdatePanelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		massUpdatePanelConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "massUpdatePanel");
		massUpdatePanelConfig.setComponentName("Mass Update");
		massUpdatePanelConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		massUpdatePanelConfig.addComponentStyle("stpl-margin-left-9px");
		massUpdatePanelConfig.addComponentStyle("stpl-margin-top-11");
		massUpdatePanelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		massUpdatePanelConfig.setAddToParent(true);
		componentList.add(massUpdatePanelConfig);
		massUpdateLayout(componentList, namespace);
	}

	private void massUpdateLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		layoutConf.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig massUpdateLayoutConfig = new GtnUIFrameworkComponentConfig();
		massUpdateLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		massUpdateLayoutConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "massUpdateLayout");
		massUpdateLayoutConfig.setComponentName("MassUpdateLayout");
		massUpdateLayoutConfig.setAddToParent(true);
		massUpdateLayoutConfig.setComponentWidth("98%");
		massUpdateLayoutConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "massUpdatePanel");
		massUpdateLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		massUpdateLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		massUpdateLayoutConfig.setGtnLayoutConfig(layoutConf);
		componentList.add(massUpdateLayoutConfig);

		addValueComponent(componentList, massUpdateLayoutConfig.getComponentId(), namespace);
		addComparisonComponent(componentList, massUpdateLayoutConfig.getComponentId(), namespace);
		addStartPeriodComponent(componentList, massUpdateLayoutConfig.getComponentId(), namespace);
		addEndPeriodComponent(componentList, massUpdateLayoutConfig.getComponentId(), namespace);
		addPopulateButtonComponent(componentList, massUpdateLayoutConfig.getComponentId(), namespace);
	}

	private void addValueComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig valueConfig = layoutsConfig.getHorizontalLayoutConfig("valueConfig", parentId);
		valueConfig.addComponentStyle("v-margin-custom-style-10");
		componentList.add(valueConfig);

		GtnUIFrameworkComponentConfig value = new GtnUIFrameworkComponentConfig();
		value.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		value.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "value");
		value.setComponentName("Value: ");
		value.setAddToParent(true);

		value.setParentComponentId(valueConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig comparisonBreakdownValueLoadConfig = new GtnUIFrameworkComboBoxConfig();
		comparisonBreakdownValueLoadConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		comparisonBreakdownValueLoadConfig.setItemValues(new ArrayList<>());
		comparisonBreakdownValueLoadConfig.setItemCaptionValues(new ArrayList<>());
		value.setGtnComboboxConfig(comparisonBreakdownValueLoadConfig);
		componentList.add(value);
	}

	private void addComparisonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig comparisonConfig = layoutsConfig.getHorizontalLayoutConfig("comparisonConfig",
				parentId);
		comparisonConfig.addComponentStyle("v-margin-custom-style-10");
		componentList.add(comparisonConfig);

		GtnUIFrameworkComponentConfig comparison = new GtnUIFrameworkComponentConfig();
		comparison.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		comparison.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparison");
		comparison.setComponentName("File/Projection: ");
		comparison.setAddToParent(true);

		comparison.addComponentStyle("stpl-margin-left-10");
		comparison.setComponentHight("32px");
		comparison.setParentComponentId(comparisonConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig comparisonBreakdownComparisonBreakdownLoadConfig = new GtnUIFrameworkComboBoxConfig();
		comparisonBreakdownComparisonBreakdownLoadConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		comparisonBreakdownComparisonBreakdownLoadConfig.setItemValues(new ArrayList<>());
		comparisonBreakdownComparisonBreakdownLoadConfig.setItemCaptionValues(new ArrayList<>());
		comparison.setGtnComboboxConfig(comparisonBreakdownComparisonBreakdownLoadConfig);
		componentList.add(comparison);
	}

	private void addStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig startPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("startPeriodConfig",
				parentId);
		startPeriodConfig.addComponentStyle("v-margin-custom-style-10");
		componentList.add(startPeriodConfig);

		GtnUIFrameworkComponentConfig startPeriod = new GtnUIFrameworkComponentConfig();
		startPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		startPeriod.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "startPeriod");
		startPeriod.setComponentName("Start Period: ");
		startPeriod.setAddToParent(true);
		startPeriod.addComponentStyle("stpl-margin-left-10");
		startPeriod.setComponentHight("32px");
		startPeriod.setParentComponentId(startPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig startPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		startPeriodLoadConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		startPeriodLoadConfig.setItemValues(new ArrayList());
		startPeriodLoadConfig.setItemCaptionValues(new ArrayList());

		startPeriod.setGtnComboboxConfig(startPeriodLoadConfig);
		componentList.add(startPeriod);
	}

	private void addEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig endPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("endPeriodConfig",
				parentId);
		endPeriodConfig.addComponentStyle("v-margin-custom-style-10");
		componentList.add(endPeriodConfig);

		GtnUIFrameworkComponentConfig endPeriod = new GtnUIFrameworkComponentConfig();
		endPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		endPeriod.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "endPeriod");
		endPeriod.setComponentName("End Period: ");
		endPeriod.setAddToParent(true);
		endPeriod.addComponentStyle("stpl-margin-left-10");
		endPeriod.setComponentHight("32px");
		endPeriod.setParentComponentId(endPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig endPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		endPeriodLoadConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		endPeriodLoadConfig.setItemValues(new ArrayList());
		endPeriodLoadConfig.setItemCaptionValues(new ArrayList());
		endPeriod.setGtnComboboxConfig(endPeriodLoadConfig);
		componentList.add(endPeriod);
	}

	private void addPopulateButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig populateButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("populateButtonConfig", parentId);
		componentList.add(populateButtonConfig);

		GtnUIFrameworkComponentConfig populateButton = new GtnUIFrameworkComponentConfig();
		populateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		populateButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "populateButton");
		populateButton.setComponentName("POPULATE");
		populateButton.setAddToParent(true);
		populateButton.setParentComponentId(populateButtonConfig.getComponentId());

		GtnUIFrameWorkActionConfig comparisonBreakDownPopulateLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		comparisonBreakDownPopulateLoadAction
				.addActionParameter(GtnReportingComparisonBreakdownMassUpdateAction.class.getName());
		comparisonBreakDownPopulateLoadAction.addActionParameter("reportOptionsTabComparisonOptions_value");
		comparisonBreakDownPopulateLoadAction.addActionParameter("reportOptionsTabComparisonOptions_comparison");
		comparisonBreakDownPopulateLoadAction.addActionParameter("reportOptionsTabComparisonOptions_startPeriod");
		comparisonBreakDownPopulateLoadAction.addActionParameter("reportOptionsTabComparisonOptions_endPeriod");
		comparisonBreakDownPopulateLoadAction
				.addActionParameter("comparisonBreakdownResultsLayout_comparisonBreakdownResultsPagedTableComponent");
		populateButton.addGtnUIFrameWorkActionConfig(comparisonBreakDownPopulateLoadAction);
		componentList.add(populateButton);
	}

	private void addControlButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {

		GtnUIFrameworkComponentConfig controlButtonConfig = layoutsConfig
				.getHorizontalLayoutConfig("controlButtonConfig", parentId);
		componentList.add(controlButtonConfig);

		GtnUIFrameworkComponentConfig comparisonBreakDownSubmitButton = new GtnUIFrameworkComponentConfig();
		comparisonBreakDownSubmitButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		comparisonBreakDownSubmitButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "submitButton");
		comparisonBreakDownSubmitButton.setComponentName("SUBMIT");
		comparisonBreakDownSubmitButton.setAddToParent(true);
		comparisonBreakDownSubmitButton.setParentComponentId(controlButtonConfig.getComponentId());
		List<GtnUIFrameWorkActionConfig> comparisonBreakdownSubmitActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig comparisonBreakDownSubmitAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		comparisonBreakDownSubmitAction.addActionParameter(GtnReportingComparisonBreakdownSubmitAction.class.getName());
		comparisonBreakdownSubmitActionList.add(comparisonBreakDownSubmitAction);
		GtnUIFrameWorkActionConfig comparisonBreakdownPopupCloseAction = new GtnUIFrameWorkActionConfig();
		comparisonBreakdownPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		comparisonBreakdownPopupCloseAction.addActionParameter("comparisonOptions");
		comparisonBreakdownSubmitActionList.add(comparisonBreakdownPopupCloseAction);
		comparisonBreakDownSubmitButton.setGtnUIFrameWorkActionConfigList(comparisonBreakdownSubmitActionList);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resetButton");
		resetButton.setComponentName("RESET");
		resetButton.setAddToParent(true);
		resetButton.setParentComponentId(controlButtonConfig.getComponentId());

		List<Object> actionParamForReset = new ArrayList<>();
		GtnUIFrameWorkActionConfig comparisonBreakdownResetButtonConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		comparisonBreakdownResetButtonConfirmationActionConfig
				.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		actionParamForReset.add("Confirm Reset");
		actionParamForReset.add("Are you sure you want to reset the page to default values?");
		List<GtnUIFrameWorkActionConfig> actionConfigListForReset = new ArrayList<>();
		GtnUIFrameWorkActionConfig comparisonBreakdownResetButtonAction = new GtnUIFrameWorkActionConfig();
		comparisonBreakdownResetButtonAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		comparisonBreakdownResetButtonAction.addActionParameter(GtnReportingComparisonBreakdownGridResetAction.class.getName());
		comparisonBreakdownResetButtonAction.addActionParameter("comparisonBreakdownResultsLayout_comparisonBreakdownResultsPagedTableComponent");
        comparisonBreakdownResetButtonAction.addActionParameter("reportOptionsTabComparisonOptions_value");
        comparisonBreakdownResetButtonAction.addActionParameter("reportOptionsTabComparisonOptions_comparison");
        comparisonBreakdownResetButtonAction.addActionParameter("reportOptionsTabComparisonOptions_startPeriod");
        comparisonBreakdownResetButtonAction.addActionParameter("reportOptionsTabComparisonOptions_endPeriod");
        actionConfigListForReset.add(comparisonBreakdownResetButtonAction);
        actionParamForReset.add(actionConfigListForReset);
        comparisonBreakdownResetButtonConfirmationActionConfig.setActionParameterList(actionParamForReset);
        resetButton.addGtnUIFrameWorkActionConfig(comparisonBreakdownResetButtonConfirmationActionConfig);

        

		GtnUIFrameworkComponentConfig closeButton = new GtnUIFrameworkComponentConfig();
		closeButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "closeButton");
		closeButton.setComponentName("CLOSE");
		closeButton.setAddToParent(true);
		closeButton.setParentComponentId(controlButtonConfig.getComponentId());
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter("comparisonOptions");
		actionConfigList.add(closeAction);
		closeButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(comparisonBreakDownSubmitButton);
		componentList.add(resetButton);
		componentList.add(closeButton);
	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig comparisonBreakdownResultsPanel = new GtnUIFrameworkComponentConfig();
		comparisonBreakdownResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		comparisonBreakdownResultsPanel.setComponentId("comparisonBreakdownResultsPanel");
		comparisonBreakdownResultsPanel.setParentComponentId(parentId);
		comparisonBreakdownResultsPanel.setAddToParent(true);
		comparisonBreakdownResultsPanel.setComponentName("Time Period");
		comparisonBreakdownResultsPanel.addComponentStyle("stpl-margin-left-9px");
		comparisonBreakdownResultsPanel.addComponentStyle("stpl-margin-top-11");
		comparisonBreakdownResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(comparisonBreakdownResultsPanel);

		GtnUIFrameworkLayoutConfig comparisonBreakdownColLayout = new GtnUIFrameworkLayoutConfig();
		comparisonBreakdownColLayout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig variableBreakdownResultsLayout = new GtnUIFrameworkComponentConfig();
		variableBreakdownResultsLayout.setComponentId("comparisonBreakdownResultsLayout");
		variableBreakdownResultsLayout.setParentComponentId(comparisonBreakdownResultsPanel.getComponentId());
		variableBreakdownResultsLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		variableBreakdownResultsLayout.setGtnLayoutConfig(comparisonBreakdownColLayout);
		variableBreakdownResultsLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		variableBreakdownResultsLayout.setAddToParent(true);

		componentList.add(variableBreakdownResultsLayout);
		addResultTable(componentList, "comparisonBreakdownResultsLayout");

	}

	private void addResultTable(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {

		GtnUIFrameworkComponentConfig comparisonBreakdownLookupResultsPagedTableComponentLayout = layoutsConfig
				.getHorizontalLayoutConfig("comparisonBreakdownLookupResultsPagedTableComponentLayout", parentId);
		comparisonBreakdownLookupResultsPagedTableComponentLayout.setComponentWidth("100%");
		componentList.add(comparisonBreakdownLookupResultsPagedTableComponentLayout);

		GtnUIFrameworkComponentConfig comparisonBreakdownLookupResultsPagedTableComponent = new GtnUIFrameworkComponentConfig();
		comparisonBreakdownLookupResultsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		comparisonBreakdownLookupResultsPagedTableComponent.setComponentId(parentId
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "comparisonBreakdownResultsPagedTableComponent");
		comparisonBreakdownLookupResultsPagedTableComponent.setComponentName("Results");
		comparisonBreakdownLookupResultsPagedTableComponent
				.setParentComponentId(comparisonBreakdownLookupResultsPagedTableComponentLayout.getComponentId());
		comparisonBreakdownLookupResultsPagedTableComponent.setAddToParent(true);
		comparisonBreakdownLookupResultsPagedTableComponent.setComponentWidth("1000px");

		List<String> comparisonBreakdownLookupTableStyle = new ArrayList<>();
		comparisonBreakdownLookupTableStyle.add("filterbar");
		comparisonBreakdownLookupTableStyle.add("v-has-width");
		comparisonBreakdownLookupTableStyle.add("v-table-filterbar");
		comparisonBreakdownLookupTableStyle.add("table-header-normal");
		comparisonBreakdownLookupResultsPagedTableComponent.setComponentWidth("100%");
		comparisonBreakdownLookupResultsPagedTableComponent.setComponentStyle(comparisonBreakdownLookupTableStyle);

		componentList.add(comparisonBreakdownLookupResultsPagedTableComponent);

		GtnUIFrameworkPagedTableConfig comparisonBreakdownLookupResultsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		comparisonBreakdownLookupResultsPagedTableConfig.setEditable(false);
		comparisonBreakdownLookupResultsPagedTableConfig.setFilterBar(true);
		comparisonBreakdownLookupResultsPagedTableConfig.setPageLength(10);
		comparisonBreakdownLookupResultsPagedTableConfig.setItemPerPage(10);
		comparisonBreakdownLookupResultsPagedTableConfig.setSelectable(true);
		comparisonBreakdownLookupResultsPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		comparisonBreakdownLookupResultsPagedTableConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		comparisonBreakdownLookupResultsPagedTableConfig
				.setGridColumnHeader(GtnWsReportConstants.GTN_WS_REPORT_COMPARISON_BREAKDOWN_TABLE_HEADERS_SERVICE);
		comparisonBreakdownLookupResultsPagedTableConfig.setEnableCheckBoxInGridHeader(true);
		comparisonBreakdownLookupResultsPagedTableConfig.setPaginationOff(true);

		comparisonBreakdownLookupResultsPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		comparisonBreakdownLookupResultsPagedTableConfig
				.setColumnHeaders(GtnFrameworkReportStringConstants.getComparisonBreakdownHeader());
		comparisonBreakdownLookupResultsPagedTableConfig.setTableColumnMappingId(new Object[] {});

		GtnUIFrameWorkActionConfig comparisonBreakdownHeaderLoadAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		comparisonBreakdownHeaderLoadAction
				.addActionParameter("reportLandingScreen_landingScreenVariableBreakdownFrequencyConfig");
		comparisonBreakdownHeaderLoadAction.addActionParameter("reportingDashboard_displaySelectionTabPeriodRangeFrom");
		comparisonBreakdownHeaderLoadAction.addActionParameter("reportingDashboard_displaySelectionTabPeriodRangeTo");
		comparisonBreakdownHeaderLoadAction.addActionParameter("reportingDashboard_displaySelectionTabFrequency");
		comparisonBreakdownHeaderLoadAction.addActionParameter("dataSelectionTab_fromPeriod");
		comparisonBreakdownHeaderLoadAction.addActionParameter("dataSelectionTab_STATUS");				
		comparisonBreakdownLookupResultsPagedTableConfig
				.setGtnUIFrameWorkActionConfig(comparisonBreakdownHeaderLoadAction);
		comparisonBreakdownLookupResultsPagedTableConfig.setGridHeaderCustomClassLoadURL(
				GtnFrameworkReportStringConstants.REPORT_COMPARISON_BREAKDOWN_HEADER_ACTION);
		comparisonBreakdownLookupResultsPagedTableComponent
				.setGtnPagedTableConfig(comparisonBreakdownLookupResultsPagedTableConfig);

	}
}
