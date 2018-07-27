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
import com.stpl.gtn.gtn2o.ui.module.processscheduler.action.GtnUIFrameworkFrequencyValueChangeAction;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.constants.GtnFrameworkProcessSchedulerStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;

public class GtnNewProcessSchedularConfig {
	
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnNewProcessSchedularConfig.class);
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getProcessSchedularView() {
		gtnLogger.info("enetred in new config");
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Process Schedular View");
		view.setViewId("V001");
		view.setDefaultView(true);  // need to change when needed to be the default view
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
		processSchedularRootLayout.setComponentId("rootLayout");
		processSchedularRootLayout.setComponentWidth("100%");
		processSchedularRootLayout.setAddToParent(false);
		processSchedularRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processSchedularRootLayout.setSpacing(true);
		processSchedularRootLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		processSchedularRootLayout.setGtnLayoutConfig(layout);
		componentList.add(processSchedularRootLayout);
		
		addProcessSchedularRootPanel(componentList,processSchedularRootLayout.getComponentId());		
	}
	
	private void addProcessSchedularRootPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		gtnLogger.info("Started the execution of addProcessSchedularMainPanel()");
		GtnUIFrameworkComponentConfig processSchedularRootPanel = new GtnUIFrameworkComponentConfig();
		processSchedularRootPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		processSchedularRootPanel.setComponentId("rootPanel");
		processSchedularRootPanel.setAddToParent(true);
		//processSchedularRootPanel.setComponentName("Root Panel");
		processSchedularRootPanel.setParentComponentId(parentComponentId);
		processSchedularRootPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(processSchedularRootPanel);
		
		addProcessSchedularMainLayout(componentList,processSchedularRootPanel.getComponentId());
	}
	
	private void addProcessSchedularMainLayout(List<GtnUIFrameworkComponentConfig> componentList,String parentComponentId) {
		gtnLogger.info("Started the execution of addProcessSchedularMainLayout()");
		GtnUIFrameworkComponentConfig processSchedularMainLayout = new GtnUIFrameworkComponentConfig();
		processSchedularMainLayout.setComponentId("mainLayout");
		processSchedularMainLayout.setComponentWidth("110%");
		processSchedularMainLayout.setAddToParent(true);
		processSchedularMainLayout.setParentComponentId(parentComponentId);
		processSchedularMainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processSchedularMainLayout.setSpacing(true);
		processSchedularMainLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		processSchedularMainLayout.setGtnLayoutConfig(layout);
		componentList.add(processSchedularMainLayout);
		
		addManualProcessingLayout(componentList,processSchedularMainLayout.getComponentId());
		addSchProcessingSchProcessEditorLayout(componentList,processSchedularMainLayout.getComponentId());
	}
	
	private void addManualProcessingLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		
		GtnUIFrameworkComponentConfig processSchedularMainLayout = new GtnUIFrameworkComponentConfig();
		processSchedularMainLayout.setComponentId("manualProcessingLayout");
		processSchedularMainLayout.setComponentWidth("40%");
		processSchedularMainLayout.setAddToParent(true);
		processSchedularMainLayout.setParentComponentId(parentComponentId);
		processSchedularMainLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processSchedularMainLayout.setSpacing(true);
		//processSchedularMainLayout.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
		processSchedularMainLayout.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		processSchedularMainLayout.setGtnLayoutConfig(layout);
		componentList.add(processSchedularMainLayout);
			
		addManualProcessingPanel(componentList,processSchedularMainLayout.getComponentId());
		addRunButton(componentList,processSchedularMainLayout.getComponentId());
		addCffOutBoundButton(componentList,processSchedularMainLayout.getComponentId());
	}
	private void addCffOutBoundButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig addButtonConfig = new GtnUIFrameworkComponentConfig();
		addButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentId("CffOutBoundButton");
		addButtonConfig.setComponentName("CFF_OUTBOUND");
		addButtonConfig.setParentComponentId(parentComponentId);
		addButtonConfig.setAddToParent(true);
		componentList.add(addButtonConfig);
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<GtnUIFrameWorkActionConfig>();
		
		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		actionConfigList.add(navigationActionConfig);
		addButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addManualProcessingPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addManualProcessingPanel()");
		GtnUIFrameworkComponentConfig manualProcessingPanel = configProvider
				.getPanelConfig("manualProcessingPanelv1", true, parentComponentId);
		manualProcessingPanel.setComponentName("Manual Processing");
		manualProcessingPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		//manualProcessingPanel.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
		componentList.add(manualProcessingPanel);
		
		GtnUIFrameworkComponentConfig manualProcessingPanelLayout = configProvider.getVerticalLayoutConfig("processSchedularPanelLayout", true, manualProcessingPanel.getComponentId());
		manualProcessingPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(manualProcessingPanelLayout);
		
		addmanualprocessingComponent(componentList,manualProcessingPanelLayout.getComponentId());	
	}
	
	private void addmanualprocessingComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

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
	
	private void addRunButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig addButtonConfig = new GtnUIFrameworkComponentConfig();
		addButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentId("ManualProcessingRunButton");
		addButtonConfig.setComponentName("RUN");
		addButtonConfig.setParentComponentId(parentComponentId);
		addButtonConfig.setAddToParent(true);
		componentList.add(addButtonConfig);
		
		
	}
	
	private void addSchProcessingSchProcessEditorLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		
		GtnUIFrameworkComponentConfig processSchedularMainLayout = new GtnUIFrameworkComponentConfig();
		processSchedularMainLayout.setComponentId("SchProcessingSchProcessEditorLayout");
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
		
		addScheduledProcessingPanel(componentList,processSchedularMainLayout.getComponentId());
		addScheduledProcessEditorPanel(componentList,processSchedularMainLayout.getComponentId());
		addUpdateButton(componentList,processSchedularMainLayout.getComponentId());
		
	}

	private void addScheduledProcessingPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addScheduledProcessingPanel()");
		GtnUIFrameworkComponentConfig scheduledProcessingPanel = configProvider
				.getPanelConfig("scheduledProcessingPanel", true, parentComponentId);
		scheduledProcessingPanel.setComponentName("Scheduled Processing");
		scheduledProcessingPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(scheduledProcessingPanel);
		
		GtnUIFrameworkComponentConfig scheduledProcessingPanelLayout = configProvider.getVerticalLayoutConfig("processSchedularPanelLayout", true, scheduledProcessingPanel.getComponentId());
		scheduledProcessingPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(scheduledProcessingPanelLayout);
		
		addScheduledProcessingComponent(componentList,scheduledProcessingPanelLayout.getComponentId());	
	}

	private void addScheduledProcessingComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId) {
		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		//searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
		searchResultConfig.setComponentId("schedulerResultTable");
		searchResultConfig.setComponentName("Results");
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
		searchResults.setExtraColumn(new Object[] { "scriptname" });
		
		searchResults.setCountUrl(GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
				+ GtnWsProcessScedulerConstants.GET_SCHEDULED_PROCESSING_TABLE_DATA);
		gtnLogger.info(searchResults.getCountUrl());
		searchResults.setResultSetUrl(GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN
				+ GtnWsProcessScedulerConstants.GET_SCHEDULED_PROCESSING_TABLE_DATA);
		
		searchResults.setModuleName("scheduledProcessing");
		searchResults.setQueryName("SearchQuery");
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(searchResultConfig.getComponentId());
		searchResults.addPostCreationActionConfig(loadDataTableActionConfig);
		searchResults.setDoubleClickEnable(true);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
		
	}

	private void addScheduledProcessEditorPanel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		gtnLogger.info("Started the execution of addScheduledProcessEditorPanel()");
		GtnUIFrameworkComponentConfig scheduledProcessEditorPanel = configProvider
				.getPanelConfig("scheduledEditorPanel", true, parentComponentId);
		scheduledProcessEditorPanel.setComponentName("Scheduled Process Editor");
		scheduledProcessEditorPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(scheduledProcessEditorPanel);
		
		GtnUIFrameworkComponentConfig scheduledProcessEditorPanelLayout = configProvider.getVerticalLayoutConfig("scheduledProcessEditorPanelLayout", true, scheduledProcessEditorPanel.getComponentId());
		scheduledProcessEditorPanelLayout.setComponentWidth("145%");
		//scheduledProcessEditorPanelLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(scheduledProcessEditorPanelLayout);
		
		addProcessNameFrequencyLayout(componentList,scheduledProcessEditorPanelLayout.getComponentId());	
		addStatusRunEveryLayout(componentList,scheduledProcessEditorPanelLayout.getComponentId());	
		addStartDateMinutesLayout(componentList,scheduledProcessEditorPanelLayout.getComponentId());	
		addEndDateLayout(componentList, scheduledProcessEditorPanelLayout.getComponentId());
	}

	private void addProcessNameFrequencyLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig processNameFrequencyLayout=new GtnUIFrameworkComponentConfig();
		processNameFrequencyLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processNameFrequencyLayout.setComponentId("processNameFrequencyLayout");
		processNameFrequencyLayout.setComponentWidth("100%");
		processNameFrequencyLayout.setAddToParent(true);
		processNameFrequencyLayout.setSpacing(true);
		processNameFrequencyLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig processNameFrequencyLayoutConfig=new GtnUIFrameworkLayoutConfig();
		processNameFrequencyLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		processNameFrequencyLayout.setGtnLayoutConfig(processNameFrequencyLayoutConfig);
		componentList.add(processNameFrequencyLayout);
		
		addProcessName(componentList,processNameFrequencyLayout.getComponentId());
		addFequency(componentList,processNameFrequencyLayout.getComponentId());
	}

	private void addProcessName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig processNameLayout = configProvider
				.getHorizontalLayoutConfig("processNameLayout", true, parentComponentId);
		processNameLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(processNameLayout);
		
		GtnUIFrameworkComponentConfig frequency = new GtnUIFrameworkComponentConfig();
		frequency.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		frequency.setComponentName("Process Name:");
		frequency.setComponentId("processName");
		frequency.setComponentWidth("80%");
		frequency.setParentComponentId(processNameLayout.getComponentId());
		frequency.setAddToParent(true);
		componentList.add(frequency);
		
	}

	private void addFequency(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig fequencyLayout = configProvider
				.getHorizontalLayoutConfig("fequencyLayout", true, parentComponentId);
		fequencyLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(fequencyLayout);
		
		GtnUIFrameworkComponentConfig frequencyOptionGroup = new GtnUIFrameworkComponentConfig();
		frequencyOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		frequencyOptionGroup.setComponentId("frequency");
		frequencyOptionGroup.setComponentName("Frequency:");
		frequencyOptionGroup.setAddToParent(Boolean.TRUE);
		frequencyOptionGroup.setParentComponentId(fequencyLayout.getComponentId());

		GtnUIFrameworkOptionGroupConfig frequencyOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		frequencyOptionGroupConfig.setItemValues(
				Arrays.asList( "Interval" , "Time" ));
		frequencyOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		frequencyOptionGroupConfig.setEnable(Boolean.TRUE);
		frequencyOptionGroupConfig.setIsMultiSelect(false);
		frequencyOptionGroupConfig.setDefaultSelection("Interval");
		frequencyOptionGroup
				.setComponentStyle(Arrays.asList( GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE ));
		frequencyOptionGroup.setGtnUIFrameworkOptionGroupConfig(frequencyOptionGroupConfig);
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnUIFrameworkFrequencyValueChangeAction.class.getName());
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
		GtnUIFrameworkComponentConfig statusRunEveryLayout=new GtnUIFrameworkComponentConfig();
		statusRunEveryLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		statusRunEveryLayout.setComponentId("statusRunEveryLayout");
		statusRunEveryLayout.setComponentWidth("100%");
		statusRunEveryLayout.setAddToParent(true);
		statusRunEveryLayout.setSpacing(true);
		statusRunEveryLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig statusRunEveryLayoutConfig=new GtnUIFrameworkLayoutConfig();
		statusRunEveryLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		statusRunEveryLayout.setGtnLayoutConfig(statusRunEveryLayoutConfig);
		componentList.add(statusRunEveryLayout);
		
		addStatus(componentList,statusRunEveryLayout.getComponentId());
		addRunEvery(componentList,statusRunEveryLayout.getComponentId());
		addRun1(componentList, statusRunEveryLayout.getComponentId());
		addMinutes1(componentList, statusRunEveryLayout.getComponentId());
		//addMinutesLabel(componentList, statusRunEveryLayout.getComponentId());
	}

	private void addStatus(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig statusLayout = configProvider
				.getHorizontalLayoutConfig("statusLayout", true, parentComponentId);
		statusLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(statusLayout);
		
		GtnUIFrameworkComponentConfig status = new GtnUIFrameworkComponentConfig();
		status.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		status.setComponentName("Status:");
		status.setComponentId("status");
		status.setParentComponentId(statusLayout.getComponentId());
		status.setAddToParent(true);
		componentList.add(status);

		GtnUIFrameworkComboBoxConfig statusConfig = new GtnUIFrameworkComboBoxConfig();
		statusConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.STATUS));
		status.setGtnComboboxConfig(statusConfig);

		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		status.setGtnUIFrameworkValidationConfig(valConfig);
		status.setGtnComboboxConfig(statusConfig);
		
		status.setGtnComboboxConfig(statusConfig);
		
	}

	private void addRunEvery(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig runEveryLayout = configProvider
				.getHorizontalLayoutConfig("runEveryLayout", true, parentComponentId);
		runEveryLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(runEveryLayout);
		
		GtnUIFrameworkComponentConfig runEvery = new GtnUIFrameworkComponentConfig();
		runEvery.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		runEvery.setComponentName("Run Every:");
		runEvery.setComponentId("runEvery");
		runEvery.setParentComponentId(runEveryLayout.getComponentId());
		runEvery.setAddToParent(true);
		componentList.add(runEvery);

		GtnUIFrameworkComboBoxConfig runEveryConfig = new GtnUIFrameworkComboBoxConfig();
		runEveryConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		runEvery.setGtnComboboxConfig(runEveryConfig);
		addHoursLabel(componentList,runEveryLayout.getComponentId());
	}

	private void addHoursLabel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		/*GtnUIFrameworkComponentConfig hoursLabelLayout = configProvider
				.getHorizontalLayoutConfig("hoursLabelLayout", true, parentComponentId);
		hoursLabelLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(hoursLabelLayout);*/
		
		GtnUIFrameworkComponentConfig hourslabel = new GtnUIFrameworkComponentConfig();
		hourslabel.setComponentType(GtnUIFrameworkComponentType.V8_LABEL);
		hourslabel.setComponentId("hoursLabel");
		hourslabel.setAddToParent(true);
		hourslabel.setComponentValue("hours");
		hourslabel.addComponentStyle("stpl-left-caption-100");
		hourslabel.addComponentStyle("stpl-text-align--left");
		hourslabel.setParentComponentId(parentComponentId);
		
		componentList.add(hourslabel);
	}

	private void addStartDateMinutesLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig startDateMinutesLayout=new GtnUIFrameworkComponentConfig();
		startDateMinutesLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		startDateMinutesLayout.setComponentId("startDateMinutesLayout");
		startDateMinutesLayout.setComponentWidth("100%");
		startDateMinutesLayout.setAddToParent(true);
		startDateMinutesLayout.setSpacing(true);
		startDateMinutesLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig startDateMinutesLayoutConfig=new GtnUIFrameworkLayoutConfig();
		startDateMinutesLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		startDateMinutesLayout.setGtnLayoutConfig(startDateMinutesLayoutConfig);
		componentList.add(startDateMinutesLayout);
		
		addStartDate(componentList,startDateMinutesLayout.getComponentId());
		addRun2(componentList,startDateMinutesLayout.getComponentId());
		addMinutes2ComboBox(componentList,startDateMinutesLayout.getComponentId());
	}

	private void addStartDate(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig startDateLayout = configProvider
				.getHorizontalLayoutConfig("startDateLayout", true, parentComponentId);
		startDateLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(startDateLayout);
		
		GtnUIFrameworkComponentConfig startDate = new GtnUIFrameworkComponentConfig();
		startDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		startDate.setComponentId("startDate");
		startDate.setComponentName("Start Date:");
		startDate.setParentComponentId(startDateLayout.getComponentId());
		startDate.setAddToParent(true);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
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
		minutes.setComponentId("minutes");
		minutes.setParentComponentId(minutesComboBoxLayout.getComponentId());
		minutes.setAddToParent(true);
		componentList.add(minutes);

		GtnUIFrameworkComboBoxConfig minutesConfig = new GtnUIFrameworkComboBoxConfig();
		minutesConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.MINUTES.split(",")));
		minutes.setGtnComboboxConfig(minutesConfig);
		minutes.setGtnComboboxConfig(minutesConfig);
		addMinutesLabel(componentList, minutesComboBoxLayout.getComponentId());
	}

	private void addMinutesLabel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig minutesLayout = configProvider
				.getHorizontalLayoutConfig("minutesLayout", true, parentComponentId);
		componentList.add(minutesLayout);
		
		GtnUIFrameworkComponentConfig minuteslabel = new GtnUIFrameworkComponentConfig();
		minuteslabel.setComponentType(GtnUIFrameworkComponentType.V8_LABEL);
		minuteslabel.setComponentId("minutesLabel");
		minuteslabel.setAddToParent(true);
		minuteslabel.setComponentValue("minutes");
		minuteslabel.setParentComponentId(minutesLayout.getComponentId());
		minuteslabel.addComponentStyle("stpl-left-caption-100");
		minuteslabel.addComponentStyle("stpl-text-align--left");
		
		componentList.add(minuteslabel);
	}

	private void addEndDateLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig endDateLayout=new GtnUIFrameworkComponentConfig();
		endDateLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		endDateLayout.setComponentId("endDateLayoutCss");
		endDateLayout.setAddToParent(true);
		endDateLayout.setSpacing(true);
		endDateLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig endDateLayoutConfig=new GtnUIFrameworkLayoutConfig();
		endDateLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		endDateLayout.setGtnLayoutConfig(endDateLayoutConfig);
		componentList.add(endDateLayout);
		
		addEndDate(componentList, endDateLayout.getComponentId());
		addRun3(componentList,endDateLayout.getComponentId());
		addMinutes3ComboBox(componentList,endDateLayout.getComponentId());
		
	}
	
	private void addEndDate(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig endDateLayout = configProvider
				.getHorizontalLayoutConfig("endDateLayout", true, parentComponentId);
		endDateLayout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		componentList.add(endDateLayout);

		GtnUIFrameworkComponentConfig endDate = new GtnUIFrameworkComponentConfig();
		endDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		endDate.setComponentId("endDate");
		endDate.setComponentName("End Date:");
		endDate.setParentComponentId(endDateLayout.getComponentId());
		endDate.setAddToParent(true);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
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
		minutes.setComponentId("minutes3");
		minutes.setParentComponentId(minutes3ComboBoxLayout.getComponentId());
		minutes.setAddToParent(true);
		componentList.add(minutes);

		GtnUIFrameworkComboBoxConfig minutesConfig = new GtnUIFrameworkComboBoxConfig();
		minutesConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.MINUTES.split(",")));
		minutes.setGtnComboboxConfig(minutesConfig);
		minutes.setGtnComboboxConfig(minutesConfig);
		addMinutesLabel(componentList, minutes3ComboBoxLayout.getComponentId());
	}

	private void addUpdateButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig addButtonConfig = new GtnUIFrameworkComponentConfig();
		addButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentId("scheduledProcessEditorUpdateButton");
		addButtonConfig.setComponentName("UPDATE");
		addButtonConfig.setParentComponentId(parentComponentId);
		addButtonConfig.setAddToParent(true);
		addButtonConfig.setEnable(false);
		componentList.add(addButtonConfig);
		
		
	}
	
	private void addRun1(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig run1Layout = configProvider
				.getHorizontalLayoutConfig("run1Layout", true, parentComponentId);
		run1Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run1Layout.setVisible(false);
		componentList.add(run1Layout);
		
		GtnUIFrameworkComponentConfig run1 = new GtnUIFrameworkComponentConfig();
		run1.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		run1.setComponentName("Run 1:");
		run1.setComponentId("run1");
		run1.setParentComponentId(run1Layout.getComponentId());
		run1.setAddToParent(true);
		componentList.add(run1);

		GtnUIFrameworkComboBoxConfig run1Config = new GtnUIFrameworkComboBoxConfig();
		run1Config.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		run1.setGtnComboboxConfig(run1Config);
		addHoursLabel(componentList,run1Layout.getComponentId());
	}
	private void addRun2(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig run2Layout = configProvider
				.getHorizontalLayoutConfig("run2Layout", true, parentComponentId);
		run2Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run2Layout.setVisible(false);
		componentList.add(run2Layout);
		
		GtnUIFrameworkComponentConfig run2 = new GtnUIFrameworkComponentConfig();
		run2.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		run2.setComponentName("Run 2:");
		run2.setComponentId("run2");
		run2.setParentComponentId(run2Layout.getComponentId());
		run2.setAddToParent(true);
		componentList.add(run2);

		GtnUIFrameworkComboBoxConfig run2Config = new GtnUIFrameworkComboBoxConfig();
		run2Config.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		run2.setGtnComboboxConfig(run2Config);
		addHoursLabel(componentList,run2Layout.getComponentId());
	}
	private void addRun3(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig run3Layout = configProvider
				.getHorizontalLayoutConfig("run3Layout", true, parentComponentId);
		run3Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		run3Layout.setVisible(false);
		componentList.add(run3Layout);
		
		GtnUIFrameworkComponentConfig run3 = new GtnUIFrameworkComponentConfig();
		run3.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		run3.setComponentName("Run 3:");
		run3.setComponentId("run3");
		run3.setParentComponentId(run3Layout.getComponentId());
		run3.setAddToParent(true);
		componentList.add(run3);

		GtnUIFrameworkComboBoxConfig run3Config = new GtnUIFrameworkComboBoxConfig();
		run3Config.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.HOURS.split(",")));
		run3.setGtnComboboxConfig(run3Config);
		addHoursLabel(componentList,run3Layout.getComponentId());
	}
	
	private void addMinutes1(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig minutes1Layout = configProvider
				.getHorizontalLayoutConfig("minutes1Layout", true, parentComponentId);
		minutes1Layout.addComponentStyle(GtnFrameworkCssConstants.INLINE_CAPTION_100);
		minutes1Layout.setVisible(false);
		componentList.add(minutes1Layout);
		
		GtnUIFrameworkComponentConfig minutes1 = new GtnUIFrameworkComponentConfig();
		minutes1.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		//minutes1.setComponentName("Minutes:");
		minutes1.setComponentId("minutes1");
		minutes1.setParentComponentId(minutes1Layout.getComponentId());
		minutes1.setAddToParent(true);
		componentList.add(minutes1);

		GtnUIFrameworkComboBoxConfig minutesConfig = new GtnUIFrameworkComboBoxConfig();
		minutesConfig.setItemValues(Arrays.asList(GtnFrameworkProcessSchedulerStringContants.MINUTES.split(",")));
		minutes1.setGtnComboboxConfig(minutesConfig);
		addMinutesLabel(componentList,minutes1Layout.getComponentId());
	}
}