package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
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
		comparisonOptionsLookupView.setViewId(GtnFrameworkReportStringConstants.COMPARISON_OPTIONS);
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
		comparisonOptionsPanelConfig.addComponentStyle(GtnFrameworkReportStringConstants.MARGIN_LEFT_9);
		comparisonOptionsPanelConfig.addComponentStyle(GtnFrameworkReportStringConstants.MARGIN_TOP_11);
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
		componentList.add(comparisonBasisConfig);
		comparisonBasisConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		comparisonBasisConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		GtnUIFrameworkComponentConfig comparisonBasis = new GtnUIFrameworkComponentConfig();
		comparisonBasis.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		comparisonBasis.setComponentId("comparisonOptionsLookup_comparisonBasis");
		comparisonBasis.setComponentName("Comparison Basis: ");
		comparisonBasis.setAddToParent(true);
		comparisonBasis.setParentComponentId(comparisonBasisConfig.getComponentId());
		comparisonBasis.setEnable(false);
		comparisonBasis.setComponentHight("32px");
		comparisonBasis.addComponentStyle(GtnFrameworkCssConstants.V_REPORT_COMPARISON_OPTION_WIDTH);

		componentList.add(comparisonBasis);
	}

	private void addFrequencyComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig frequencyConfig = layoutsConfig.getHorizontalLayoutConfig("frequencyConfig",
				parentId);
		frequencyConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		frequencyConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(frequencyConfig);

		GtnUIFrameworkComponentConfig frequency = new GtnUIFrameworkComponentConfig();
		frequency.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		frequency.setComponentId("comparisonOptionsLookup_frequencyConfig");
		frequency.setComponentName("Frequency: ");
		frequency.setAddToParent(true);
		frequency.setParentComponentId(frequencyConfig.getComponentId());
		frequency.setEnable(false);
		frequency.setComponentHight("32px");
		frequency.addComponentStyle(GtnFrameworkCssConstants.V_REPORT_COMPARISON_OPTION_WIDTH);
		componentList.add(frequency);
	}

	private void addPeriodRangeFromComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig periodRangeFromConfig = layoutsConfig
				.getHorizontalLayoutConfig("periodRangeFromConfig", parentId);
		periodRangeFromConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		periodRangeFromConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(periodRangeFromConfig);

		GtnUIFrameworkComponentConfig periodRangeFrom = new GtnUIFrameworkComponentConfig();
		periodRangeFrom.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		periodRangeFrom.setComponentId("comparisonOptionsLookup_periodRangeFrom");
		periodRangeFrom.setComponentName("Period Range From: ");
		periodRangeFrom.setAddToParent(true);
		periodRangeFrom.setParentComponentId(periodRangeFromConfig.getComponentId());
		periodRangeFrom.setEnable(false);
		periodRangeFrom.setComponentHight("32px");
		periodRangeFrom.addComponentStyle(GtnFrameworkCssConstants.V_REPORT_COMPARISON_OPTION_WIDTH);
		componentList.add(periodRangeFrom);
	}

	private void addPeriodRangeToComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId) {
		GtnUIFrameworkComponentConfig periodRangeToConfig = layoutsConfig
				.getHorizontalLayoutConfig("periodRangeToConfig", parentId);
		periodRangeToConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		periodRangeToConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(periodRangeToConfig);

		GtnUIFrameworkComponentConfig periodRangeTo = new GtnUIFrameworkComponentConfig();
		periodRangeTo.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		periodRangeTo.setComponentId("comparisonOptionsLookup_periodRangeTo");
		periodRangeTo.setComponentName("Period Range To: ");
		periodRangeTo.setAddToParent(true);
		periodRangeTo.setParentComponentId(periodRangeToConfig.getComponentId());
		periodRangeTo.setEnable(false);
		periodRangeTo.setComponentHight("32px");
		periodRangeTo.addComponentStyle(GtnFrameworkCssConstants.V_REPORT_COMPARISON_OPTION_WIDTH);

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
		massUpdatePanelConfig.addComponentStyle(GtnFrameworkReportStringConstants.MARGIN_LEFT_9);
		massUpdatePanelConfig.addComponentStyle(GtnFrameworkReportStringConstants.MARGIN_TOP_11);
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
		valueConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		valueConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
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

		GtnUIFrameworkValidationConfig valueValidationConfig = new GtnUIFrameworkValidationConfig();
		valueValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		value.setGtnUIFrameworkValidationConfig(valueValidationConfig);

		componentList.add(value);
	}

	private void addComparisonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig comparisonConfig = layoutsConfig.getHorizontalLayoutConfig("comparisonConfig",
				parentId);
		comparisonConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		comparisonConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(comparisonConfig);

		GtnUIFrameworkComponentConfig comparison = new GtnUIFrameworkComponentConfig();
		comparison.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		comparison.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparison");
		comparison.setComponentName("File/Projection: ");
		comparison.setAddToParent(true);
		comparison.addComboComponentStyle(GtnFrameworkCssConstants.V_REPORT_COMPARISON_OPTION_COMBOWIDTH);
		comparison.setComponentHight("32px");
		comparison.setParentComponentId(comparisonConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig comparisonBreakdownComparisonBreakdownLoadConfig = new GtnUIFrameworkComboBoxConfig();
		comparisonBreakdownComparisonBreakdownLoadConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		comparisonBreakdownComparisonBreakdownLoadConfig.setItemValues(new ArrayList<>());
		comparisonBreakdownComparisonBreakdownLoadConfig.setItemCaptionValues(new ArrayList<>());
		comparison.setGtnComboboxConfig(comparisonBreakdownComparisonBreakdownLoadConfig);

		GtnUIFrameworkValidationConfig comparisonValidationConfig = new GtnUIFrameworkValidationConfig();
		comparisonValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		comparison.setGtnUIFrameworkValidationConfig(comparisonValidationConfig);

		componentList.add(comparison);
	}

	private void addStartPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig startPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("startPeriodConfig",
				parentId);
		startPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		startPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(startPeriodConfig);

		GtnUIFrameworkComponentConfig startPeriod = new GtnUIFrameworkComponentConfig();
		startPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		startPeriod.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "startPeriod");
		startPeriod.setComponentName("Start Period: ");
		startPeriod.setAddToParent(true);
		startPeriod.setComponentHight("32px");
		startPeriod.addComboComponentStyle(GtnFrameworkCssConstants.V_REPORT_COMPARISON_OPTION_COMBOWIDTH);
		startPeriod.setParentComponentId(startPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig startPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		startPeriodLoadConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		startPeriodLoadConfig.setItemValues(new ArrayList());
		startPeriodLoadConfig.setItemCaptionValues(new ArrayList());

		startPeriod.setGtnComboboxConfig(startPeriodLoadConfig);

		GtnUIFrameworkValidationConfig startPeriodValidationConfig = new GtnUIFrameworkValidationConfig();
		startPeriodValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		startPeriod.setGtnUIFrameworkValidationConfig(startPeriodValidationConfig);

		componentList.add(startPeriod);
	}

	private void addEndPeriodComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String namespace) {
		GtnUIFrameworkComponentConfig endPeriodConfig = layoutsConfig.getHorizontalLayoutConfig("endPeriodConfig",
				parentId);
		endPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_10);
		endPeriodConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		endPeriodConfig.addComponentStyle(GtnFrameworkReportStringConstants.MARGIN_CUSTOM_STYLE_10);
		componentList.add(endPeriodConfig);

		GtnUIFrameworkComponentConfig endPeriod = new GtnUIFrameworkComponentConfig();
		endPeriod.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		endPeriod.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "endPeriod");
		endPeriod.setComponentName("End Period: ");
		endPeriod.addComboComponentStyle(GtnFrameworkCssConstants.V_REPORT_COMPARISON_OPTION_COMBOWIDTH);
		endPeriod.setAddToParent(true);
		endPeriod.addComponentStyle(GtnFrameworkReportStringConstants.MARGIN_LEFT_10);
		endPeriod.setComponentHight("32px");
		endPeriod.setParentComponentId(endPeriodConfig.getComponentId());
		GtnUIFrameworkComboBoxConfig endPeriodLoadConfig = new GtnUIFrameworkComboBoxConfig();
		endPeriodLoadConfig.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		endPeriodLoadConfig.setItemValues(new ArrayList());
		endPeriodLoadConfig.setItemCaptionValues(new ArrayList());
		endPeriod.setGtnComboboxConfig(endPeriodLoadConfig);

		GtnUIFrameworkValidationConfig endPeriodValidationConfig = new GtnUIFrameworkValidationConfig();
		endPeriodValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		endPeriod.setGtnUIFrameworkValidationConfig(endPeriodValidationConfig);

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
		populateButton.setAuthorizationIncluded(true);
		populateButton.setAddToParent(true);
		populateButton.setParentComponentId(populateButtonConfig.getComponentId());
		componentList.add(populateButton);

		List<GtnUIFrameWorkActionConfig> populateButtonActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig populateButtonValidationActionConfig = new GtnUIFrameWorkActionConfig();
		populateButtonValidationActionConfig.setActionType(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		populateButtonValidationActionConfig
				.setFieldValues(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "value",
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "comparison",
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "startPeriod",
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "endPeriod"));

		GtnUIFrameWorkActionConfig populateButtonAlertActionConfig = new GtnUIFrameWorkActionConfig();
		populateButtonAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("Missing Fields");
		alertParams.add("Please make sure that all Mass Update fields are populated. Then try again.");
		populateButtonAlertActionConfig.setActionParameterList(alertParams);

		populateButtonValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(populateButtonAlertActionConfig)));
		populateButtonActionConfigList.add(populateButtonValidationActionConfig);

		GtnUIFrameWorkActionConfig comparisonBreakDownPopulateLoadAction = new GtnUIFrameWorkActionConfig();
		comparisonBreakDownPopulateLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		comparisonBreakDownPopulateLoadAction
				.addActionParameter(GtnReportingComparisonBreakdownMassUpdateAction.class.getName());
		comparisonBreakDownPopulateLoadAction.addActionParameter("reportOptionsTabComparisonOptions_value");
		comparisonBreakDownPopulateLoadAction.addActionParameter("reportOptionsTabComparisonOptions_comparison");
		comparisonBreakDownPopulateLoadAction.addActionParameter("reportOptionsTabComparisonOptions_startPeriod");
		comparisonBreakDownPopulateLoadAction.addActionParameter("reportOptionsTabComparisonOptions_endPeriod");
		comparisonBreakDownPopulateLoadAction
				.addActionParameter("comparisonBreakdownResultsLayout_comparisonBreakdownResultsPagedTableComponent");
		populateButtonActionConfigList.add(comparisonBreakDownPopulateLoadAction);

		populateButton.setGtnUIFrameWorkActionConfigList(populateButtonActionConfigList);
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
		comparisonBreakdownPopupCloseAction.addActionParameter(GtnFrameworkReportStringConstants.COMPARISON_OPTIONS);
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
		actionParamForReset.add("Reset Confirmation");
		actionParamForReset.add("Are you sure you want to reset the popup?");
		List<GtnUIFrameWorkActionConfig> actionConfigListForReset = new ArrayList<>();
		GtnUIFrameWorkActionConfig comparisonBreakdownResetButtonAction = new GtnUIFrameWorkActionConfig();
		comparisonBreakdownResetButtonAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		comparisonBreakdownResetButtonAction
				.addActionParameter(GtnReportingComparisonBreakdownGridResetAction.class.getName());
		comparisonBreakdownResetButtonAction
				.addActionParameter("comparisonBreakdownResultsLayout_comparisonBreakdownResultsPagedTableComponent");
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

		List<Object> actionParamForClose = new ArrayList<>();
		GtnUIFrameWorkActionConfig comparisonBreakdownCloseButtonConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		comparisonBreakdownCloseButtonConfirmationActionConfig
				.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		actionParamForClose.add("Close Confirmation");
		actionParamForClose.add("Are you sure you want to close the popup?");

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkReportStringConstants.COMPARISON_OPTIONS);
		actionConfigList.add(closeAction);
		actionParamForClose.add(actionConfigList);
		comparisonBreakdownCloseButtonConfirmationActionConfig.setActionParameterList(actionParamForClose);
		closeButton.addGtnUIFrameWorkActionConfig(comparisonBreakdownCloseButtonConfirmationActionConfig);

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
		comparisonBreakdownResultsPanel.addComponentStyle(GtnFrameworkReportStringConstants.MARGIN_LEFT_9);
		comparisonBreakdownResultsPanel.addComponentStyle(GtnFrameworkReportStringConstants.MARGIN_TOP_11);
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
