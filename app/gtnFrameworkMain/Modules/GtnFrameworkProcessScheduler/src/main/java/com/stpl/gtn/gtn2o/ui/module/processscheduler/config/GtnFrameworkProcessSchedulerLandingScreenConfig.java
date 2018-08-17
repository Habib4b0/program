package com.stpl.gtn.gtn2o.ui.module.processscheduler.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkMandatoryFieldSettingAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkRunButtonAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkScheduledProcessTableDoubleClickAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkStartEndDateValidationCustomAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnFrameworkFrequencyValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;

/**
 *
 * @author Deepak.kumar
 */

public class GtnFrameworkProcessSchedulerLandingScreenConfig {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkProcessSchedulerLandingScreenConfig.class);
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getProcessSchedularView() {
		gtnLogger.info("enetred in new config");
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName(GtnFrameworkProcessSchedulerStringContants.PROCESS_SCHEDULER_VIEW_NAME);
		view.setViewId(GtnFrameworkProcessSchedulerStringContants.PROCESS_SCHEDULER_VIEWID);
		view.setDefaultView(true); 
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		gtnLogger.info("enetred in new config component list");
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addProcessSchedularRootLayout(componentList);

	}

	private void addProcessSchedularRootLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		gtnLogger.info("Started the execution of addProcessSchedularRootLayout()");
		GtnUIFrameworkComponentConfig processSchedularRootLayout = new GtnUIFrameworkComponentConfig();
		processSchedularRootLayout.setComponentId(GtnFrameworkProcessSchedulerStringContants.ROOT_LAYOUT);
		processSchedularRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		processSchedularRootLayout.setAddToParent(false);
		processSchedularRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processSchedularRootLayout.setSpacing(true);
		processSchedularRootLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		processSchedularRootLayout.setGtnLayoutConfig(layout);
		componentList.add(processSchedularRootLayout);

		addProcessSchedularRootPanel(componentList, processSchedularRootLayout.getComponentId());
	}

	private void addProcessSchedularRootPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addProcessSchedularMainPanel()");
		GtnUIFrameworkComponentConfig processSchedularRootPanel = new GtnUIFrameworkComponentConfig();
		processSchedularRootPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		processSchedularRootPanel.setComponentId(GtnFrameworkProcessSchedulerStringContants.ROOT_PANEL);
		processSchedularRootPanel.setAddToParent(true);
		processSchedularRootPanel.setParentComponentId(parentComponentId);
		processSchedularRootPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(processSchedularRootPanel);

		addProcessSchedularMainLayout(componentList, processSchedularRootPanel.getComponentId());
	}

	private void addProcessSchedularMainLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addProcessSchedularMainLayout()");
		GtnUIFrameworkComponentConfig processSchedularMainLayout = new GtnUIFrameworkComponentConfig();
		processSchedularMainLayout.setComponentId(GtnFrameworkProcessSchedulerStringContants.MAIN_LAYOUT);
		processSchedularMainLayout
				.setComponentWidth(GtnFrameworkProcessSchedulerStringContants.HUNDERED_TEN_PERCENTAGE);
		processSchedularMainLayout.setAddToParent(true);
		processSchedularMainLayout.setParentComponentId(parentComponentId);
		processSchedularMainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processSchedularMainLayout.setSpacing(true);
		processSchedularMainLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		processSchedularMainLayout.setGtnLayoutConfig(layout);
		componentList.add(processSchedularMainLayout);

		addManualProcessingLayout(componentList, processSchedularMainLayout.getComponentId());
		addSchProcessingSchProcessEditorLayout(componentList, processSchedularMainLayout.getComponentId());
	}

	private void addManualProcessingLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig processSchedularMainLayout = new GtnUIFrameworkComponentConfig();
		processSchedularMainLayout.setComponentId(GtnFrameworkProcessSchedulerStringContants.MANUAL_PROCESSING_LAYOUT);
		processSchedularMainLayout.setComponentWidth(GtnFrameworkProcessSchedulerStringContants.FOURTY_PERCENTAGE);
		processSchedularMainLayout.setAddToParent(true);
		processSchedularMainLayout.setParentComponentId(parentComponentId);
		processSchedularMainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processSchedularMainLayout.setSpacing(true);
		processSchedularMainLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		processSchedularMainLayout.setGtnLayoutConfig(layout);
		componentList.add(processSchedularMainLayout);

		addManualProcessingPanel(componentList, processSchedularMainLayout.getComponentId());
		addRunButton(componentList, processSchedularMainLayout.getComponentId());
		
	}

	private void addManualProcessingPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addManualProcessingPanel()");
		GtnUIFrameworkComponentConfig manualProcessingPanel = configProvider.getPanelConfig(
				GtnFrameworkProcessSchedulerStringContants.MANUAL_PROCESSING_PANEL, true, parentComponentId);
		manualProcessingPanel.setComponentName(GtnFrameworkProcessSchedulerStringContants.MANUAL_PROCESSING);
		manualProcessingPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(manualProcessingPanel);

		GtnUIFrameworkComponentConfig manualProcessingPanelLayout = configProvider.getVerticalLayoutConfig(
				GtnFrameworkProcessSchedulerStringContants.MANUAL_PROCESSING_PANEL_LAYOUT, true,
				manualProcessingPanel.getComponentId());
		manualProcessingPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(manualProcessingPanelLayout);

		addManualprocessingComponent(componentList, manualProcessingPanelLayout.getComponentId());
	}

	private void addManualprocessingComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
		searchResultConfig.setComponentId(GtnFrameworkCommonConstants.MANUAL_RESULT_TABLE);
		searchResultConfig.setComponentName("Resultsdata");
		searchResultConfig.setParentComponentId(parentComponentId);
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
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);
		searchResults.setSinkItemPerPageWithPageLength(false);

		searchResults.setTableColumnDataType(new Class[] { String.class, Date.class });
		searchResults.setTableVisibleHeader(new String[] { GtnFrameworkCommonConstants.PROCESS_NAME_COMPONENT,
				GtnFrameworkCommonConstants.LAST_RUN_COMPONENT });
		searchResults.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.PROCESS_NAME, GtnFrameworkCommonConstants.LAST_RUN });
		searchResults.setExtraColumn(new Object[] { GtnFrameworkProcessSchedulerStringContants.SCRIPT_NAME,
				GtnFrameworkProcessSchedulerStringContants.PROCESS_SID });

		searchResults.setCountUrl(GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
				+ GtnWsProcessScedulerConstants.GET_PROCESS_SCHEDULER_TABLE_DATA);
		searchResults.setResultSetUrl(GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
				+ GtnWsProcessScedulerConstants.GET_PROCESS_SCHEDULER_TABLE_DATA);

		searchResults.setModuleName("processSchedulerManual");
		searchResults.setQueryName("SearchQuery");

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		searchResultConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		searchResultConfig.setGtnPagedTableConfig(searchResults);
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(searchResultConfig.getComponentId());
		searchResults.addPostCreationActionConfig(loadDataTableActionConfig);
		searchResults.setDoubleClickEnable(true);
		searchResultConfig.setGtnPagedTableConfig(searchResults);

	}

	private void addRunButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig addButtonConfig = new GtnUIFrameworkComponentConfig();
		addButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentId(GtnFrameworkProcessSchedulerStringContants.MANUAL_RUN_ID);
		addButtonConfig.setComponentName(GtnFrameworkProcessSchedulerStringContants.RUN);
		addButtonConfig.setParentComponentId(parentComponentId);
		addButtonConfig.setAddToParent(true);
		componentList.add(addButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.MANUAL_RESULT_TABLE));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.ERROR);
		alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.MESSAGE);
		alertActionConfig.setActionParameterList(alertParamsList);

		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(alertActionConfig)));

		GtnUIFrameWorkActionConfig customActionConfig = new GtnUIFrameWorkActionConfig();
		customActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionConfig.addActionParameter(GtnFrameworkRunButtonAction.class.getName());

		actionConfigList.add(validationActionConfig);
		actionConfigList.add(customActionConfig);

		addButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addSchProcessingSchProcessEditorLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {

		GtnUIFrameworkComponentConfig processSchedularMainLayout = new GtnUIFrameworkComponentConfig();
		processSchedularMainLayout
				.setComponentId(GtnFrameworkProcessSchedulerStringContants.PROCESS_SCHEDULAR_MAIN_LAYOUT);
		processSchedularMainLayout.setComponentWidth("59%");
		processSchedularMainLayout.setAddToParent(true);
		processSchedularMainLayout.setParentComponentId(parentComponentId);
		processSchedularMainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processSchedularMainLayout.setSpacing(true);
		processSchedularMainLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		processSchedularMainLayout.setGtnLayoutConfig(layout);
		componentList.add(processSchedularMainLayout);

		addScheduledProcessingPanel(componentList, processSchedularMainLayout.getComponentId());
		addScheduledProcessEditorPanel(componentList, processSchedularMainLayout.getComponentId());
		addUpdateButton(componentList, processSchedularMainLayout.getComponentId());

	}

	private void addScheduledProcessingPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addScheduledProcessingPanel()");
		GtnUIFrameworkComponentConfig scheduledProcessingPanel = configProvider.getPanelConfig(
				GtnFrameworkProcessSchedulerStringContants.SCHEDULED_PROCESSING_PANEL, true, parentComponentId);
		scheduledProcessingPanel.setComponentName(GtnFrameworkProcessSchedulerStringContants.SCHEDULED_PROCESSING);
		scheduledProcessingPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(scheduledProcessingPanel);

		GtnUIFrameworkComponentConfig scheduledProcessingPanelLayout = configProvider.getVerticalLayoutConfig(
				GtnFrameworkProcessSchedulerStringContants.SCHEDULED_PROCESSING_PANEL_LAYOUT, true,
				scheduledProcessingPanel.getComponentId());
		scheduledProcessingPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(scheduledProcessingPanelLayout);

		addScheduledProcessingComponent(componentList, scheduledProcessingPanelLayout.getComponentId());
	}

	private void addScheduledProcessingComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		GtnUIFrameworkComponentConfig searchResultScheduledConfig = new GtnUIFrameworkComponentConfig();
		searchResultScheduledConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultScheduledConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultScheduledConfig.setComponentId(GtnFrameworkProcessSchedulerStringContants.SCHEDULER_RESULTS_TABLE);
		searchResultScheduledConfig.setComponentName("Results");
		searchResultScheduledConfig.setParentComponentId(parentComponentId);
		searchResultScheduledConfig.setAddToParent(true);
		searchResultScheduledConfig.setResetToDefaultAllowed(false);
		List<String> scheduledTableStyle = new ArrayList<>();
		scheduledTableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		scheduledTableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		scheduledTableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		scheduledTableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultScheduledConfig.setComponentStyle(scheduledTableStyle);

		componentList.add(searchResultScheduledConfig);

		GtnUIFrameworkPagedTableConfig searchScheduledResults = new GtnUIFrameworkPagedTableConfig();
		searchScheduledResults.setSelectable(true);
		searchScheduledResults.setPageLength(5);
		searchScheduledResults.setItemPerPage(5);
		searchScheduledResults.setSinkItemPerPageWithPageLength(false);

		searchScheduledResults.setTableColumnDataType(new Class[] { String.class, String.class, Date.class, Date.class,
				String.class, Date.class, Date.class, String.class });
		searchScheduledResults.setTableVisibleHeader(new String[] { GtnFrameworkCommonConstants.PROCESS_NAME_COMPONENT,
				GtnFrameworkCommonConstants.STATUS_COMPONENT, "Start Date", "End Date",
				GtnFrameworkCommonConstants.FREQUENCY, "Last Run Date", "Modified Date", "Modified By" });
		searchScheduledResults.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.PROCESS_NAME, GtnFrameworkCommonConstants.STATUS,
						GtnFrameworkCommonConstants.START_DATE, GtnFrameworkCommonConstants.END_DATE,
						GtnFrameworkCommonConstants.FREQUENCY, "lastRunDate", "modifiedDate", "modifiedBy" });
		searchScheduledResults.setExtraColumn(new Object[] { "processSid","scriptName","startHour","startMinute" ,"startHour1","startHour2","startHour3" ,"startMinute1","startMinute2","startMinute3"});

		searchScheduledResults.setCountUrl(GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
				+ GtnWsProcessScedulerConstants.GET_SCHEDULED_PROCESSING_TABLE_DATA);
		gtnLogger.info(searchScheduledResults.getCountUrl());
		searchScheduledResults.setResultSetUrl(GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
				+ GtnWsProcessScedulerConstants.GET_SCHEDULED_PROCESSING_TABLE_DATA);

		searchScheduledResults.setModuleName("scheduledProcessing");
		searchScheduledResults.setQueryName("SearchQuery");
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(searchResultScheduledConfig.getComponentId());
		searchScheduledResults.addPostCreationActionConfig(loadDataTableActionConfig);
		searchScheduledResults.setDoubleClickEnable(true);

		List<GtnUIFrameWorkActionConfig> itemDoubleClickActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customDoubleClickActionConfig = new GtnUIFrameWorkActionConfig();
		customDoubleClickActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customDoubleClickActionConfig
				.addActionParameter(GtnFrameworkScheduledProcessTableDoubleClickAction.class.getName());
		customDoubleClickActionConfig.addActionParameter("schedulerResultTable");
		customDoubleClickActionConfig.addActionParameter(GtnFrameworkCommonConstants.PROCESS_NAME);
		customDoubleClickActionConfig.addActionParameter(Boolean.TRUE);
		itemDoubleClickActionConfigList.add(customDoubleClickActionConfig);

		searchScheduledResults.setGtnUIFrameWorkActionConfigList(itemDoubleClickActionConfigList);
		searchResultScheduledConfig.setGtnPagedTableConfig(searchScheduledResults);
	}

	private void addScheduledProcessEditorPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addScheduledProcessEditorPanel()");
		GtnUIFrameworkComponentConfig scheduledProcessEditorPanel = configProvider
				.getPanelConfig("scheduledEditorPanel", true, parentComponentId);
		scheduledProcessEditorPanel.setComponentName("Scheduled Process Editor");
		scheduledProcessEditorPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(scheduledProcessEditorPanel);

		GtnUIFrameworkComponentConfig scheduledProcessEditorPanelLayout = configProvider.getVerticalLayoutConfig(
				"scheduledProcessEditorPanelLayout", true, scheduledProcessEditorPanel.getComponentId());
		scheduledProcessEditorPanelLayout.setComponentWidth("145%");
		componentList.add(scheduledProcessEditorPanelLayout);

		addProcessNameFrequencyLayout(componentList, scheduledProcessEditorPanelLayout.getComponentId());
		addStatusRunEveryLayout(componentList, scheduledProcessEditorPanelLayout.getComponentId());
		addStartDateMinutesLayout(componentList, scheduledProcessEditorPanelLayout.getComponentId());
		addEndDateLayout(componentList, scheduledProcessEditorPanelLayout.getComponentId());
	}

	private void addProcessNameFrequencyLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		GtnUIFrameworkComponentConfig processNameFrequencyLayout = new GtnUIFrameworkComponentConfig();
		processNameFrequencyLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processNameFrequencyLayout.setComponentId("processNameFrequencyLayout");
		processNameFrequencyLayout.setComponentWidth("100%");
		processNameFrequencyLayout.setAddToParent(true);
		processNameFrequencyLayout.setSpacing(true);
		processNameFrequencyLayout.setParentComponentId(parentComponentId);

		GtnUIFrameworkLayoutConfig processNameFrequencyLayoutConfig = new GtnUIFrameworkLayoutConfig();
		processNameFrequencyLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		processNameFrequencyLayout.setGtnLayoutConfig(processNameFrequencyLayoutConfig);
		componentList.add(processNameFrequencyLayout);

		addProcessName(componentList, processNameFrequencyLayout.getComponentId());
		addFequency(componentList, processNameFrequencyLayout.getComponentId());
	}

	private void addProcessName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig processNameLayout = configProvider.getHorizontalLayoutConfig("processNameLayout",
				true, parentComponentId);
		processNameLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(processNameLayout);

		GtnUIFrameworkComponentConfig processName = new GtnUIFrameworkComponentConfig();
		processName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		processName.setComponentName("Process Name:");
		processName.setComponentId(GtnFrameworkProcessSchedulerStringContants.PROCESS_NAME_ID);
		processName.setComponentWidth("80%");
		processName.setParentComponentId(processNameLayout.getComponentId());
		processName.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		processName.setAddToParent(true);
		componentList.add(processName);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		processName.setGtnUIFrameworkValidationConfig(validationConfig);

	}

	private void addFequency(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig fequencyLayout = configProvider.getHorizontalLayoutConfig("fequencyLayout", true,
				parentComponentId);
		fequencyLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(fequencyLayout);

		GtnUIFrameworkComponentConfig frequencyOptionGroup = new GtnUIFrameworkComponentConfig();
		frequencyOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		frequencyOptionGroup.setComponentId(GtnFrameworkProcessSchedulerStringContants.FREQUENCY_ID);
		frequencyOptionGroup.setComponentName("Frequency:");
		frequencyOptionGroup.setAddToParent(Boolean.TRUE);
		frequencyOptionGroup.setParentComponentId(fequencyLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig frequencyOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		frequencyOptionGroupConfig.setItemValues(Arrays.asList("Interval", "Time"));
		frequencyOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		frequencyOptionGroupConfig.setEnable(Boolean.TRUE);
		frequencyOptionGroupConfig.setIsMultiSelect(false);
		frequencyOptionGroupConfig.setDefaultSelection("Interval");
		frequencyOptionGroup.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE,
				GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		frequencyOptionGroup.setGtnUIFrameworkOptionGroupConfig(frequencyOptionGroupConfig);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		frequencyOptionGroup.setGtnUIFrameworkValidationConfig(validationConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkFrequencyValueChangeAction.class.getName());
		customAction.addActionParameter("run1Layout");
		customAction.addActionParameter("run2Layout");
		customAction.addActionParameter("run3Layout");
		customAction.addActionParameter("minutes1Layout");
		customAction.addActionParameter("minutes3Layout");
		customAction.addActionParameter("runEveryLayout");
		customAction.addActionParameter("scheduledProcessEditorPanelLayout");
		actionConfigList.add(customAction);
		frequencyOptionGroup.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(frequencyOptionGroup);
	}

	private void addStatusRunEveryLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig statusRunEveryLayout = new GtnUIFrameworkComponentConfig();
		statusRunEveryLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		statusRunEveryLayout.setComponentId("statusRunEveryLayout");
		statusRunEveryLayout.setComponentWidth("100%");
		statusRunEveryLayout.setAddToParent(true);
		statusRunEveryLayout.setSpacing(true);
		statusRunEveryLayout.setParentComponentId(parentComponentId);

		GtnUIFrameworkLayoutConfig statusRunEveryLayoutConfig = new GtnUIFrameworkLayoutConfig();
		statusRunEveryLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		statusRunEveryLayout.setGtnLayoutConfig(statusRunEveryLayoutConfig);
		componentList.add(statusRunEveryLayout);

		addStatus(componentList, statusRunEveryLayout.getComponentId());
		addRunEvery(componentList, statusRunEveryLayout.getComponentId());
		addRun1(componentList, statusRunEveryLayout.getComponentId());
		addMinutes1(componentList, statusRunEveryLayout.getComponentId());
	}

	private void addStatus(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig statusLayout = configProvider.getHorizontalLayoutConfig("statusLayout", true,
				parentComponentId);
		statusLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(statusLayout);

		GtnUIFrameworkComponentConfig status = new GtnUIFrameworkComponentConfig();
		status.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		status.setComponentName("Status:");
		status.setComponentId(GtnFrameworkProcessSchedulerStringContants.STATUS_ID);
		status.setParentComponentId(statusLayout.getComponentId());
		status.setAddToParent(true);
		status.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(status);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		status.setGtnUIFrameworkValidationConfig(validationConfig);

		GtnUIFrameworkComboBoxConfig statusConfig = new GtnUIFrameworkComboBoxConfig();
		statusConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.ACTIVE_STATUS, GtnFrameworkProcessSchedulerStringContants.INACTIVE_STATUS));
		status.setGtnComboboxConfig(statusConfig);

		status.setGtnComboboxConfig(statusConfig);

	}

	private void addRunEvery(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig runEveryLayout = configProvider.getHorizontalLayoutConfig("runEveryLayout", true,
				parentComponentId);
		runEveryLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(runEveryLayout);

		GtnUIFrameworkComponentConfig runEvery = new GtnUIFrameworkComponentConfig();
		runEvery.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		runEvery.setComponentName("Run Every:");
		runEvery.setComponentId(GtnFrameworkProcessSchedulerStringContants.RUN_EVERY_ID);
		runEvery.setParentComponentId(runEveryLayout.getComponentId());
		runEvery.setAddToParent(true);
		runEvery.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(runEvery);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		runEvery.setGtnUIFrameworkValidationConfig(validationConfig);

		GtnUIFrameworkComboBoxConfig runEveryConfig = new GtnUIFrameworkComboBoxConfig();
		runEveryConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		runEveryConfig.setItemCaptionValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		runEvery.setGtnComboboxConfig(runEveryConfig);
		addHoursLabel(componentList, runEveryLayout.getComponentId());
	}

	private void addHoursLabel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig hourslabel = new GtnUIFrameworkComponentConfig();
		hourslabel.setComponentType(GtnUIFrameworkComponentType.V8_LABEL);
		hourslabel.setComponentId("hoursLabel");
		hourslabel.setAddToParent(true);
		hourslabel.setComponentValue("hours");
		hourslabel.addComponentStyle(GtnFrameworkCssConstants.STPL_LEFT_CAPTION_100);
		hourslabel.addComponentStyle(GtnFrameworkCssConstants.STPL_TEXT_ALIGN_LEFT);
		hourslabel.setParentComponentId(parentComponentId);

		componentList.add(hourslabel);
	}

	private void addStartDateMinutesLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		GtnUIFrameworkComponentConfig startDateMinutesLayout = new GtnUIFrameworkComponentConfig();
		startDateMinutesLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		startDateMinutesLayout.setComponentId("startDateMinutesLayout");
		startDateMinutesLayout.setComponentWidth("100%");
		startDateMinutesLayout.setAddToParent(true);
		startDateMinutesLayout.setSpacing(true);
		startDateMinutesLayout.setParentComponentId(parentComponentId);

		GtnUIFrameworkLayoutConfig startDateMinutesLayoutConfig = new GtnUIFrameworkLayoutConfig();
		startDateMinutesLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		startDateMinutesLayout.setGtnLayoutConfig(startDateMinutesLayoutConfig);
		componentList.add(startDateMinutesLayout);

		addStartDate(componentList, startDateMinutesLayout.getComponentId());
		addRun2(componentList, startDateMinutesLayout.getComponentId());
		addMinutes2ComboBox(componentList, startDateMinutesLayout.getComponentId());
	}

	private void addStartDate(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig startDateLayout = configProvider.getHorizontalLayoutConfig("startDateLayout",
				true, parentComponentId);
		startDateLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(startDateLayout);

		GtnUIFrameworkComponentConfig startDate = new GtnUIFrameworkComponentConfig();
		startDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		startDate.setComponentId(GtnFrameworkProcessSchedulerStringContants.START_DATE_ID);
		startDate.setComponentName("Start Date:");
		startDate.setParentComponentId(startDateLayout.getComponentId());
		startDate.setAddToParent(true);
		startDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		startDate.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(startDate);
	}

	private void addMinutes2ComboBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig minutesComboBoxLayout = configProvider
				.getHorizontalLayoutConfig("minutesComboBoxLayout", true, parentComponentId);
		minutesComboBoxLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(minutesComboBoxLayout);

		GtnUIFrameworkComponentConfig minutes = new GtnUIFrameworkComponentConfig();
		minutes.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		minutes.setComponentId(GtnFrameworkProcessSchedulerStringContants.MINUTES_ID);
		minutes.setParentComponentId(minutesComboBoxLayout.getComponentId());
		minutes.setAddToParent(true);
		componentList.add(minutes);

		GtnUIFrameworkComboBoxConfig minutesConfig = new GtnUIFrameworkComboBoxConfig();
		minutesConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.MINUTES.split(",")));
		minutesConfig.setItemCaptionValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.MINUTES.split(",")));
		minutes.setGtnComboboxConfig(minutesConfig);
		minutes.setGtnComboboxConfig(minutesConfig);
		addMinutesLabel(componentList, minutesComboBoxLayout.getComponentId());
	}

	private void addMinutesLabel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig minutesLayout = configProvider.getHorizontalLayoutConfig("minutesLayout", true,
				parentComponentId);
		componentList.add(minutesLayout);

		GtnUIFrameworkComponentConfig minuteslabel = new GtnUIFrameworkComponentConfig();
		minuteslabel.setComponentType(GtnUIFrameworkComponentType.V8_LABEL);
		minuteslabel.setComponentId("minutesLabel");
		minuteslabel.setAddToParent(true);
		minuteslabel.setComponentValue("minutes");
		minuteslabel.setParentComponentId(minutesLayout.getComponentId());
		minuteslabel.addComponentStyle(GtnFrameworkCssConstants.STPL_LEFT_CAPTION_100);
		minuteslabel.addComponentStyle(GtnFrameworkCssConstants.STPL_TEXT_ALIGN_LEFT);

		componentList.add(minuteslabel);
	}

	private void addEndDateLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig endDateLayout = new GtnUIFrameworkComponentConfig();
		endDateLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		endDateLayout.setComponentId("endDateLayoutCss");
		endDateLayout.setAddToParent(true);
		endDateLayout.setSpacing(true);
		endDateLayout.setParentComponentId(parentComponentId);

		GtnUIFrameworkLayoutConfig endDateLayoutConfig = new GtnUIFrameworkLayoutConfig();
		endDateLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		endDateLayout.setGtnLayoutConfig(endDateLayoutConfig);
		componentList.add(endDateLayout);

		addEndDate(componentList, endDateLayout.getComponentId());
		addRun3(componentList, endDateLayout.getComponentId());
		addMinutes3ComboBox(componentList, endDateLayout.getComponentId());

	}

	private void addEndDate(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig endDateLayout = configProvider.getHorizontalLayoutConfig("endDateLayout", true,
				parentComponentId);
		endDateLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(endDateLayout);

		GtnUIFrameworkComponentConfig endDate = new GtnUIFrameworkComponentConfig();
		endDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELDVAADIN8);
		endDate.setComponentId(GtnFrameworkProcessSchedulerStringContants.END_DATE_ID);
		endDate.setComponentName("End Date:");
		endDate.setParentComponentId(endDateLayout.getComponentId());
		endDate.setAddToParent(true);
		endDate.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		endDate.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(endDate);
	}

	private void addMinutes3ComboBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig minutes3ComboBoxLayout = configProvider
				.getHorizontalLayoutConfig("minutes3Layout", true, parentComponentId);
		minutes3ComboBoxLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		minutes3ComboBoxLayout.setVisible(false);
		componentList.add(minutes3ComboBoxLayout);

		GtnUIFrameworkComponentConfig minutes = new GtnUIFrameworkComponentConfig();
		minutes.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		minutes.setComponentId(GtnFrameworkProcessSchedulerStringContants.MINUTES3_ID);
		minutes.setParentComponentId(minutes3ComboBoxLayout.getComponentId());
		minutes.setAddToParent(true);
		componentList.add(minutes);

		GtnUIFrameworkComboBoxConfig minutesConfig = new GtnUIFrameworkComboBoxConfig();
		minutesConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.MINUTES.split(",")));
		minutesConfig.setItemCaptionValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.MINUTES.split(",")));
		minutes.setGtnComboboxConfig(minutesConfig);
		addMinutesLabel(componentList, minutes3ComboBoxLayout.getComponentId());
	}

	private void addUpdateButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig updateButtonConfig = new GtnUIFrameworkComponentConfig();
		updateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		updateButtonConfig.setComponentId(GtnFrameworkProcessSchedulerStringContants.UPDATE_ID);
		updateButtonConfig.setComponentName("UPDATE");
		updateButtonConfig.setParentComponentId(parentComponentId);
		updateButtonConfig.setAddToParent(true);
		updateButtonConfig.setEnable(false);
		componentList.add(updateButtonConfig);

		List<GtnUIFrameWorkActionConfig> updateActionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig startEndDateValidationCustomAction = new GtnUIFrameWorkActionConfig();
		startEndDateValidationCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		startEndDateValidationCustomAction.addActionParameter(GtnFrameworkStartEndDateValidationCustomAction.class.getName());
		
		GtnUIFrameWorkActionConfig checkManadatoryFieldAction = new GtnUIFrameWorkActionConfig();
		checkManadatoryFieldAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkManadatoryFieldAction.addActionParameter(GtnFrameworkMandatoryFieldSettingAction.class.getName());

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
				
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.ERROR);
		alertParamsList.add(GtnFrameworkProcessSchedulerStringContants.MESSAGE_MANDATORY);
		alertActionConfig.setActionParameterList(alertParamsList);

		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(alertActionConfig)));

		updateActionList.add(checkManadatoryFieldAction);
		updateActionList.add(validationActionConfig);
		updateActionList.add(startEndDateValidationCustomAction);
		updateButtonConfig.setGtnUIFrameWorkActionConfigList(updateActionList);

	}

	private void addRun1(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig run1Layout = configProvider.getHorizontalLayoutConfig("run1Layout", true,
				parentComponentId);
		run1Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run1Layout.setVisible(false);
		componentList.add(run1Layout);

		GtnUIFrameworkComponentConfig run1 = new GtnUIFrameworkComponentConfig();
		run1.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		run1.setComponentName("Run 1:");
		run1.setComponentId(GtnFrameworkProcessSchedulerStringContants.RUN1_ID);
		run1.setParentComponentId(run1Layout.getComponentId());
		run1.setAddToParent(true);
		run1.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY));
		componentList.add(run1);

		GtnUIFrameworkValidationConfig runOneValidationConfig = new GtnUIFrameworkValidationConfig();
		runOneValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		run1.setGtnUIFrameworkValidationConfig(runOneValidationConfig);

		GtnUIFrameworkComboBoxConfig run1Config = new GtnUIFrameworkComboBoxConfig();
		
		run1Config.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		run1Config.setItemCaptionValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		run1.setGtnComboboxConfig(run1Config);
		addHoursLabel(componentList, run1Layout.getComponentId());
	}

	private void addRun2(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig run2Layout = configProvider.getHorizontalLayoutConfig("run2Layout", true,
				parentComponentId);
		run2Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run2Layout.setVisible(false);
		componentList.add(run2Layout);

		GtnUIFrameworkComponentConfig run2 = new GtnUIFrameworkComponentConfig();
		run2.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		run2.setComponentName("Run 2:");
		run2.setComponentId(GtnFrameworkProcessSchedulerStringContants.RUN2_ID);
		run2.setParentComponentId(run2Layout.getComponentId());
		run2.setAddToParent(true);
		componentList.add(run2);

		GtnUIFrameworkComboBoxConfig run2Config = new GtnUIFrameworkComboBoxConfig();
		run2Config.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		run2Config.setItemCaptionValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		run2.setGtnComboboxConfig(run2Config);
		addHoursLabel(componentList, run2Layout.getComponentId());
	}

	private void addRun3(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig run3Layout = configProvider.getHorizontalLayoutConfig("run3Layout", true,
				parentComponentId);
		run3Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run3Layout.setVisible(false);
		componentList.add(run3Layout);

		GtnUIFrameworkComponentConfig run3 = new GtnUIFrameworkComponentConfig();
		run3.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		run3.setComponentName("Run 3:");
		run3.setComponentId(GtnFrameworkProcessSchedulerStringContants.RUN3_ID);
		run3.setParentComponentId(run3Layout.getComponentId());
		run3.setAddToParent(true);
		componentList.add(run3);

		GtnUIFrameworkComboBoxConfig run3Config = new GtnUIFrameworkComboBoxConfig();
		run3Config.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		run3Config.setItemCaptionValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		run3.setGtnComboboxConfig(run3Config);
		addHoursLabel(componentList, run3Layout.getComponentId());
	}

	private void addMinutes1(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig minutes1Layout = configProvider.getHorizontalLayoutConfig("minutes1Layout", true,
				parentComponentId);
		minutes1Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		minutes1Layout.setVisible(false);
		componentList.add(minutes1Layout);

		GtnUIFrameworkComponentConfig minutes1 = new GtnUIFrameworkComponentConfig();
		minutes1.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		minutes1.setComponentId(GtnFrameworkProcessSchedulerStringContants.MINUTES1_ID);
		minutes1.setParentComponentId(minutes1Layout.getComponentId());
		minutes1.setAddToParent(true);
		componentList.add(minutes1);

		GtnUIFrameworkComboBoxConfig minutesConfig = new GtnUIFrameworkComboBoxConfig();
		minutesConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.MINUTES.split(",")));
		minutesConfig.setItemCaptionValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.MINUTES.split(",")));
		minutes1.setGtnComboboxConfig(minutesConfig);
		addMinutesLabel(componentList, minutes1Layout.getComponentId());
	}
}