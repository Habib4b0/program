package com.stpl.gtn.gtn2o.ui.module.processscheduler.config;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnUIFrameworkFrequencyValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class GtnFrameworkProcessSchedulerLandingScreenConfig {

	private GtnFrameworProcessSchedulerLayoutsConfig commonConfig = GtnFrameworProcessSchedulerLayoutsConfig
			.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Search View");
		view.setViewId("V001");
		view.setDefaultView(true);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addMainLayout(componentList);

	}

	private void addMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.MAIN_LAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addManualProcessing(componentList);
		addScheduledProcessing(componentList);
		addMonitorEditor(componentList);
		addAddButton(componentList);

	}

	private void addManualProcessing(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Manual Processing");
		panel.setComponentId("manualProcessingPanel");
		panel.setParentComponentId(GtnFrameworkCommonConstants.MAIN_LAYOUT);
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(false);

		componentList.add(panel);
		addmanualprocessingComponent(componentList);

	}

	private void addScheduledProcessing(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Scheduled Processing");
		panel.setComponentId("scheduledProcessingPanel");
		panel.setParentComponentId(GtnFrameworkCommonConstants.MAIN_LAYOUT);
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panel.setAddToParent(false);

		componentList.add(panel);
		addSchedulerprocessingComponent(componentList);

	}

	private void addMonitorEditor(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentName("Monitor Editor");
		panel.setComponentId("monitorEditorPanel");
		panel.setParentComponentId(GtnFrameworkCommonConstants.MAIN_LAYOUT);
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		panel.setAddToParent(false);

		componentList.add(panel);
		addFieldLayout(componentList);

	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId("fieldlayout");
		gtnLayout.setParentComponentId("monitorEditorPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		gtnLayout.setComponentStyle(styleList);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		addRunTimesPanel(componentList);
		addRunTimesLayout(componentList);
		addRunTimesVertical1LayoutComponent(componentList);
		addRunTimesVertical2LayoutComponent(componentList);
		addRunTimesVertical3LayoutComponent(componentList);

	}

	private void addRunTimesPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = new GtnUIFrameworkComponentConfig();
		panelConfig.setComponentName("Scheduled Process Editor");
		panelConfig.setComponentId("scheduledProcessEditorPanel");
		panelConfig.setParentComponentId("fieldlayout");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_1250);
		panelConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panelConfig.setAddToParent(true);
		componentList.add(panelConfig);

	}

	private void addRunTimesLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.RUNLAYOUT);
		gtnLayout.setParentComponentId("scheduledProcessEditorPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		gtnLayout.setComponentStyle(styleList);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

	}

	private void addRunTimesVertical1LayoutComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.RUNINFORMATION_HORIZONTALLAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RUNLAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		gtnLayout.setComponentStyle(styleList);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		addProcessName(componentList);
		addStatus(componentList);
		addStartDate(componentList);
		addEndDate(componentList);

	}

	private void addRunTimesVertical2LayoutComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.RUN_TINES_HORIZONTAL2LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RUNLAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		gtnLayout.setComponentStyle(styleList);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		addFrequency(componentList);
		addRun1Ddlb(componentList);
		addRun2Ddlb(componentList);
		addRun3Ddlb(componentList);

	}

	private void addRunTimesVertical3LayoutComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.RUN_TINES_HORIZONTAL3LAYOUT);
		gtnLayout.setParentComponentId(GtnFrameworkCommonConstants.RUNLAYOUT);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1);
		gtnLayout.setComponentStyle(styleList);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);

		addHours1Ddlb(componentList);
		addHours2Ddlb(componentList);
		addHours3Ddlb(componentList);

	}

	private void addHours1Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig hours1ddlb = new GtnUIFrameworkComponentConfig();
		hours1ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		hours1ddlb.setComponentId(GtnFrameworkCommonConstants.HOURS1_DDLB);
		hours1ddlb.setComponentName(GtnFrameworkCommonConstants.HOURS);
		hours1ddlb.setParentComponentId(GtnFrameworkCommonConstants.RUN_TINES_HORIZONTAL3LAYOUT);
		hours1ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig hours1DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		hours1DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		hours1ddlb.setGtnComboboxConfig(hours1DdlbConfig);

		componentList.add(hours1ddlb);

	}

	private void addHours2Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig hours2ddlb = new GtnUIFrameworkComponentConfig();
		hours2ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		hours2ddlb.setComponentId(GtnFrameworkCommonConstants.HOURS2_DDLB);
		hours2ddlb.setComponentName(GtnFrameworkCommonConstants.HOURS);
		hours2ddlb.setParentComponentId(GtnFrameworkCommonConstants.RUN_TINES_HORIZONTAL3LAYOUT);
		hours2ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig hours2DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		hours2DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		hours2ddlb.setGtnComboboxConfig(hours2DdlbConfig);

		componentList.add(hours2ddlb);

	}

	private void addHours3Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig hours3ddlb = new GtnUIFrameworkComponentConfig();
		hours3ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		hours3ddlb.setComponentId(GtnFrameworkCommonConstants.HOURS3_DDLB);
		hours3ddlb.setComponentName(GtnFrameworkCommonConstants.HOURS);
		hours3ddlb.setParentComponentId(GtnFrameworkCommonConstants.RUN_TINES_HORIZONTAL3LAYOUT);
		hours3ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig hours3DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		hours3DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		hours3ddlb.setGtnComboboxConfig(hours3DdlbConfig);

		componentList.add(hours3ddlb);

	}

	private void addRun1Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run1Ddlb = new GtnUIFrameworkComponentConfig();
		run1Ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		run1Ddlb.setComponentId(GtnFrameworkCommonConstants.RUN1_DDLB);
		run1Ddlb.setComponentName("Run 1:");
		run1Ddlb.setParentComponentId(GtnFrameworkCommonConstants.RUN_TINES_HORIZONTAL2LAYOUT);
		run1Ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig run1DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		run1DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.RUN.split(",")));
		run1Ddlb.setGtnComboboxConfig(run1DdlbConfig);

		componentList.add(run1Ddlb);

	}

	private void addRun2Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run2Ddlb = new GtnUIFrameworkComponentConfig();
		run2Ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		run2Ddlb.setComponentId(GtnFrameworkCommonConstants.RUN2_DDLB);
		run2Ddlb.setComponentName("Run 2:");
		run2Ddlb.setParentComponentId(GtnFrameworkCommonConstants.RUN_TINES_HORIZONTAL2LAYOUT);
		run2Ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig run2DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		run2DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.RUN.split(",")));
		run2Ddlb.setGtnComboboxConfig(run2DdlbConfig);

		componentList.add(run2Ddlb);

	}

	private void addRun3Ddlb(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig run3Ddlb = new GtnUIFrameworkComponentConfig();
		run3Ddlb.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		run3Ddlb.setComponentId(GtnFrameworkCommonConstants.RUN3_DDLB);
		run3Ddlb.setComponentName("Run 3:");
		run3Ddlb.setParentComponentId(GtnFrameworkCommonConstants.RUN_TINES_HORIZONTAL2LAYOUT);
		run3Ddlb.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig run3DdlbConfig = new GtnUIFrameworkComboBoxConfig();
		run3DdlbConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.RUN.split(",")));
		run3Ddlb.setGtnComboboxConfig(run3DdlbConfig);

		componentList.add(run3Ddlb);

	}

	private void addProcessName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig processNameConfig = new GtnUIFrameworkComponentConfig();
		processNameConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		processNameConfig.setComponentId(GtnFrameworkCommonConstants.PROCESS_NAME);
		processNameConfig.setComponentName(GtnFrameworkCommonConstants.PROCESS_NAME_COMPONENT);
		processNameConfig.setParentComponentId(GtnFrameworkCommonConstants.RUNINFORMATION_HORIZONTALLAYOUT);
		processNameConfig.setAddToParent(true);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		processNameConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(processNameConfig);
	}

	private void addStatus(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig status = new GtnUIFrameworkComponentConfig();
		status.setComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		status.setComponentId(GtnFrameworkCommonConstants.STATUS_COMPONENT);
		status.setComponentName("Status");
		status.setParentComponentId(GtnFrameworkCommonConstants.RUNINFORMATION_HORIZONTALLAYOUT);
		status.setAddToParent(true);

		GtnUIFrameworkComboBoxConfig statusConfig = new GtnUIFrameworkComboBoxConfig();
		statusConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.CALENDER));
		status.setGtnComboboxConfig(statusConfig);

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		status.setGtnUIFrameworkValidationConfig(valConfig);
		status.setGtnComboboxConfig(statusConfig);

		componentList.add(status);
	}

	private void addStartDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig startDate = new GtnUIFrameworkComponentConfig();
		startDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		startDate.setComponentId(GtnFrameworkCommonConstants.START_DATE);
		startDate.setComponentName("Start Date");
		startDate.setParentComponentId(GtnFrameworkCommonConstants.RUNINFORMATION_HORIZONTALLAYOUT);
		startDate.setAddToParent(true);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		startDate.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(startDate);
	}

	private void addEndDate(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig endDate = new GtnUIFrameworkComponentConfig();
		endDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		endDate.setComponentId(GtnFrameworkCommonConstants.END_DATE);
		endDate.setComponentName("End Date");
		endDate.setParentComponentId(GtnFrameworkCommonConstants.RUNINFORMATION_HORIZONTALLAYOUT);
		endDate.setAddToParent(true);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		endDate.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(endDate);
	}

	private void addAddButton(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = new GtnUIFrameworkComponentConfig();
		panel.setComponentId("addButtonPanel");
		panel.setParentComponentId(GtnFrameworkCommonConstants.MAIN_LAYOUT);
		panel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		panel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		panel.setAddToParent(false);

		componentList.add(panel);
		addADDButtonLayout(componentList);

	}

	private void addADDButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);

		GtnUIFrameworkComponentConfig gtnLayout = new GtnUIFrameworkComponentConfig();
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentId(GtnFrameworkCommonConstants.BUTTON_LAYOUT);
		gtnLayout.setParentComponentId("addButtonPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.setAddToParent(true);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addUpdateButtonComponent(componentList);
		addRunButtonComponent(componentList);
	}

	private void addFrequency(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig frequencyComponentConfig = new GtnUIFrameworkComponentConfig();
		frequencyComponentConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		frequencyComponentConfig.setComponentId("frequency");
		frequencyComponentConfig.setComponentName(GtnFrameworkCommonConstants.FREQUENCY);
		frequencyComponentConfig.setAddToParent(true);
		frequencyComponentConfig.setParentComponentId(GtnFrameworkCommonConstants.RUN_TINES_HORIZONTAL2LAYOUT);

		GtnUIFrameworkOptionGroupConfig frequencyOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		frequencyOptionGroupConfig.setValuesFromService(false);
		frequencyOptionGroupConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.INTERVAL,
				GtnFrameworkProcessSchedulerStringContants.TIME));

		frequencyOptionGroupConfig
				.setDefaultSelection(GtnFrameworkProcessSchedulerStringContants.SET_DEFAULT_FREQUENCY);

		frequencyComponentConfig.setComponentStyle(Arrays.asList(new String[] { "horizontal" }));
		frequencyComponentConfig.setGtnUIFrameworkOptionGroupConfig(frequencyOptionGroupConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkFrequencyValueChangeAction.class.getName());
		customAction.addActionParameter(GtnFrameworkCommonConstants.RUN1_DDLB);
		customAction.addActionParameter(GtnFrameworkCommonConstants.HOURS1_DDLB);
		customAction.addActionParameter(GtnFrameworkCommonConstants.RUN1_DDLB);
		customAction.addActionParameter(GtnFrameworkCommonConstants.HOURS1_DDLB);
		customAction.addActionParameter(GtnFrameworkCommonConstants.RUN1_DDLB);
		customAction.addActionParameter(GtnFrameworkCommonConstants.HOURS1_DDLB);
		actionConfigList.add(customAction);
		frequencyComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(frequencyComponentConfig);
	}

	private void addUpdateButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig addButtonConfig = new GtnUIFrameworkComponentConfig();
		addButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentId("gtnUpdateButton");
		addButtonConfig.setComponentName("Update");
		addButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.BUTTON_LAYOUT);
		addButtonConfig.setAddToParent(true);
		componentList.add(addButtonConfig);

		List<GtnUIFrameWorkActionConfig> updateActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnWsProcessScedulerConstants.PROCESS_SCHEDULER_UPDATE_ACTION);
		customAction.addActionParameter(
				Arrays.asList(GtnFrameworkCommonConstants.PROCESS_NAME, GtnFrameworkCommonConstants.STATUS_COMPONENT,
						GtnFrameworkCommonConstants.START_DATE, GtnFrameworkCommonConstants.END_DATE,
						GtnFrameworkCommonConstants.RUN1_DDLB, GtnFrameworkCommonConstants.RUN2_DDLB,
						GtnFrameworkCommonConstants.RUN3_DDLB, GtnFrameworkCommonConstants.HOURS1_DDLB,
						GtnFrameworkCommonConstants.HOURS2_DDLB, GtnFrameworkCommonConstants.HOURS3_DDLB));
		customAction.addActionParameter(
				Arrays.asList(GtnFrameworkCommonConstants.PROCESS_NAME, GtnFrameworkCommonConstants.STATUS_COMPONENT,
						GtnFrameworkCommonConstants.START_DATE, GtnFrameworkCommonConstants.END_DATE,
						GtnFrameworkCommonConstants.RUN1_DDLB, GtnFrameworkCommonConstants.RUN2_DDLB,
						GtnFrameworkCommonConstants.RUN3_DDLB, GtnFrameworkCommonConstants.HOURS1_DDLB,
						GtnFrameworkCommonConstants.HOURS2_DDLB, GtnFrameworkCommonConstants.HOURS3_DDLB));

		updateActionConfigList.add(customAction);
		addButtonConfig.setGtnUIFrameWorkActionConfigList(updateActionConfigList);

	}

	private void addRunButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig addButtonConfig = new GtnUIFrameworkComponentConfig();
		addButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentId("gtnRunButton");
		addButtonConfig.setComponentName("Run");
		addButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.BUTTON_LAYOUT);
		addButtonConfig.setAddToParent(true);
		componentList.add(addButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<GtnUIFrameWorkActionConfig>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();

		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.MANUAL_RESULT_TABLE);
		alertParamsList.add("edit error");
		alertParamsList.add("Please select a record to run");

		alertActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnWsProcessScedulerConstants.PROCESS_SCHEDULER_RUN_ACTION);
		customAction.addActionParameter(
				Arrays.asList(GtnFrameworkCommonConstants.PROCESS_NAME, GtnFrameworkCommonConstants.LAST_RUN));
		customAction.addActionParameter(
				Arrays.asList(GtnFrameworkCommonConstants.PROCESS_NAME, GtnFrameworkCommonConstants.LAST_RUN));
		customAction.addActionParameter(GtnFrameworkCommonConstants.MANUAL_RESULT_TABLE);
		customAction.addActionParameter(GtnFrameworkCommonConstants.PROCESS_NAME);
		customAction.addActionParameter(true);
		actionConfigList.add(customAction);

		addButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addmanualprocessingComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_1250);
		searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
		searchResultConfig.setComponentId(GtnFrameworkCommonConstants.MANUAL_RESULT_TABLE);
		searchResultConfig.setComponentName("Resultsdata");
		searchResultConfig.setParentComponentId("manualProcessingPanel");
		searchResultConfig.setAddToParent(true);
		searchResultConfig.setResetToDefaultAllowed(false);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setSelectable(true);
		searchResults.setPageLength(15);
		searchResults.setItemPerPage(15);
		searchResults.setSinkItemPerPageWithPageLength(false);

		searchResults.setTableColumnDataType(new Class[] { String.class, Date.class });
		searchResults
				.setTableVisibleHeader(new String[] { GtnFrameworkCommonConstants.PROCESS_NAME_COMPONENT, "Last Run" });
		searchResults.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.PROCESS_NAME, GtnFrameworkCommonConstants.LAST_RUN });
		searchResults.setExtraColumn(new Object[] { "scriptname" });

		searchResults.setCountUrl(GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
				+ GtnWsProcessScedulerConstants.GET_PROCESS_SCHEDULER_TABLE_DATA);
		searchResults.setResultSetUrl(GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
				+ GtnWsProcessScedulerConstants.GET_PROCESS_SCHEDULER_TABLE_DATA);

		searchResults.setModuleName("processSchedulerManual");
		searchResults.setQueryName("SearchQuery");
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(searchResultConfig.getComponentId());
		searchResults.addPostCreationActionConfig(loadDataTableActionConfig);
		searchResults.setDoubleClickEnable(true);

		searchResultConfig.setGtnPagedTableConfig(searchResults);

	}

	private void addSchedulerprocessingComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_1250);
		searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
		searchResultConfig.setComponentId("schedulerResultTable");
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setParentComponentId("scheduledProcessingPanel");
		searchResultConfig.setAddToParent(true);
		searchResultConfig.setResetToDefaultAllowed(false);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setSelectable(true);
		searchResults.setPageLength(5);
		searchResults.setItemPerPage(5);
		searchResults.setSinkItemPerPageWithPageLength(false);

		searchResults.setTableColumnDataType(new Class[] { String.class, String.class,
				Date.class, Date.class, String.class, Date.class,
				Date.class, String.class });
		searchResults.setTableVisibleHeader(
				new String[] { GtnFrameworkCommonConstants.PROCESS_NAME_COMPONENT, "Status", "Start Date", "End Date",
						GtnFrameworkCommonConstants.FREQUENCY, "Last Run Date", "Modified Date", "Modified By" });
		searchResults.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.PROCESS_NAME, GtnFrameworkCommonConstants.STATUS_COMPONENT,
						GtnFrameworkCommonConstants.START_DATE, GtnFrameworkCommonConstants.END_DATE,
						GtnFrameworkCommonConstants.FREQUENCY, "lastRunDate", "modifiedDate", "modifiedBy" });

		searchResultConfig.setGtnPagedTableConfig(searchResults);

	}
	
}
