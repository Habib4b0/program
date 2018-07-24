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
		view.setDefaultView(false);  // need to change when needed to be the default view
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
		processSchedularMainLayout.setComponentWidth("100%");
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
		processSchedularMainLayout.setComponentWidth("50%");
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
		processSchedularMainLayout.setComponentWidth("50%");
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
		searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_300);
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
		scheduledProcessEditorPanelLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(scheduledProcessEditorPanelLayout);
		
		addProcessNameFrequencyLayout(componentList,scheduledProcessEditorPanelLayout.getComponentId());	
		addStatusRunEveryLayout(componentList,scheduledProcessEditorPanelLayout.getComponentId());	
		addStartDateMinutesLayout(componentList,scheduledProcessEditorPanelLayout.getComponentId());	
		addEndDateLayout(componentList, scheduledProcessEditorPanelLayout.getComponentId());
	}

	private void addProcessNameFrequencyLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		/*GtnUIFrameworkComponentConfig processNameFrequencyLayout = configProvider.getHorizontalLayoutConfig("processNameFrequencyLayout", true, parentComponentId);
		processNameFrequencyLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(scheduledProcessEditorPanelLayout);*/
		
		GtnUIFrameworkComponentConfig processNameFrequencyLayout=new GtnUIFrameworkComponentConfig();
		processNameFrequencyLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		processNameFrequencyLayout.setComponentId("processNameFrequencyLayout");
		processNameFrequencyLayout.setComponentWidth("100%");
		processNameFrequencyLayout.setAddToParent(true);
		processNameFrequencyLayout.setSpacing(true);
		processNameFrequencyLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig processNameFrequencyLayoutConfig=new GtnUIFrameworkLayoutConfig();
		processNameFrequencyLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		processNameFrequencyLayout.setGtnLayoutConfig(processNameFrequencyLayoutConfig);
		componentList.add(processNameFrequencyLayout);
		
		GtnUIFrameworkLayoutConfig discountProjFiilterInnerLayout = new GtnUIFrameworkLayoutConfig();
		discountProjFiilterInnerLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		discountProjFiilterInnerLayout.setComponentColumnSize(12);
		GtnUIFrameworkComponentConfig filterInnerLayoutConfig = new GtnUIFrameworkComponentConfig();
		filterInnerLayoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		filterInnerLayoutConfig.setComponentId("discountProjFiilterInnerLayout");
		filterInnerLayoutConfig.setAddToParent(Boolean.TRUE);
		filterInnerLayoutConfig.setSpacing(Boolean.TRUE);
		filterInnerLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		filterInnerLayoutConfig.setParentComponentId(processNameFrequencyLayout.getComponentId());
		filterInnerLayoutConfig.setGtnLayoutConfig(discountProjFiilterInnerLayout);
		componentList.add(filterInnerLayoutConfig);
		
		addProcessName(componentList,filterInnerLayoutConfig.getComponentId());
		addFequency(componentList,filterInnerLayoutConfig.getComponentId());
	}

	private void addProcessName(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig frequency = new GtnUIFrameworkComponentConfig();
		frequency.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		frequency.setComponentName("Process Name:");
		frequency.setComponentId("processName");
		frequency.setParentComponentId(parentComponentId);
		frequency.setAddToParent(true);
		componentList.add(frequency);
		
	}

	private void addFequency(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig frequencyOptionGroup = new GtnUIFrameworkComponentConfig();
		frequencyOptionGroup.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		frequencyOptionGroup.setComponentId("frequency");
		frequencyOptionGroup.setComponentName("Frequency:");
		frequencyOptionGroup.setAddToParent(Boolean.TRUE);
		frequencyOptionGroup.setParentComponentId(parentComponentId);

		GtnUIFrameworkOptionGroupConfig frequencyOptionGroupConfig = new GtnUIFrameworkOptionGroupConfig();
		frequencyOptionGroupConfig.setItemValues(
				Arrays.asList( "Interval" , "Time" ));
		frequencyOptionGroupConfig.setValuesFromService(Boolean.FALSE);
		frequencyOptionGroupConfig.setEnable(Boolean.TRUE);
		frequencyOptionGroupConfig.setIsMultiSelect(false);
		frequencyOptionGroup
				.setComponentStyle(Arrays.asList( GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE ));
		frequencyOptionGroup.setGtnUIFrameworkOptionGroupConfig(frequencyOptionGroupConfig);

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
		addHoursLabel(componentList,statusRunEveryLayout.getComponentId());
	}

	private void addStatus(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig status = new GtnUIFrameworkComponentConfig();
		status.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		status.setComponentName("Status:");
		status.setComponentId("status");
		status.setParentComponentId(parentComponentId);
		status.addComponentStyle(GtnFrameworkCssConstants.STPL_PADDING_14);
		status.setAddToParent(true);
		componentList.add(status);

		GtnUIFrameworkComboBoxConfig statusConfig = new GtnUIFrameworkComboBoxConfig();
		statusConfig.setComboBoxType("FORECAST_FREQUENCY");
		statusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		status.setGtnComboboxConfig(statusConfig);
		
	}

	private void addRunEvery(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig runEvery = new GtnUIFrameworkComponentConfig();
		runEvery.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		runEvery.setComponentName("Run Every:");
		runEvery.setComponentId("runEvery");
		runEvery.setParentComponentId(parentComponentId);
		runEvery.setAddToParent(true);
		componentList.add(runEvery);

		GtnUIFrameworkComboBoxConfig runEveryConfig = new GtnUIFrameworkComboBoxConfig();
		runEveryConfig.setComboBoxType("FORECAST_FREQUENCY");
		runEveryConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		runEvery.setGtnComboboxConfig(runEveryConfig);
		
	}

	private void addHoursLabel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig hourslabel = new GtnUIFrameworkComponentConfig();
		hourslabel.setComponentType(GtnUIFrameworkComponentType.LABEL);
		hourslabel.setComponentId("hoursLabel");
		hourslabel.setAddToParent(true);
		hourslabel.setComponentValue("hours");
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
		addMinutesComboBox(componentList,startDateMinutesLayout.getComponentId());
		addMinutesLabel(componentList, startDateMinutesLayout.getComponentId());
	}

	private void addStartDate(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig startDate = new GtnUIFrameworkComponentConfig();
		startDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		startDate.setComponentId("startDate");
		startDate.setComponentName("Start Date:");
		startDate.setParentComponentId(parentComponentId);
		startDate.setAddToParent(true);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		startDate.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(startDate);
	}
	
	private void addMinutesComboBox(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig minutes = new GtnUIFrameworkComponentConfig();
		minutes.setComponentType(GtnUIFrameworkComponentType.COMBOBOX_VAADIN8);
		//minutes.setComponentName("Run Every:");
		minutes.setComponentId("minutes");
		minutes.setParentComponentId(parentComponentId);
		minutes.setAddToParent(true);
		componentList.add(minutes);

		GtnUIFrameworkComboBoxConfig minutesConfig = new GtnUIFrameworkComboBoxConfig();
		minutesConfig.setComboBoxType("FORECAST_FREQUENCY");
		minutesConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		minutes.setGtnComboboxConfig(minutesConfig);
		
	}

	private void addMinutesLabel(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig minuteslabel = new GtnUIFrameworkComponentConfig();
		minuteslabel.setComponentType(GtnUIFrameworkComponentType.LABEL);
		minuteslabel.setComponentId("minutesLabel");
		minuteslabel.setAddToParent(true);
		minuteslabel.setComponentValue("minutes");
		minuteslabel.setParentComponentId(parentComponentId);
		
		componentList.add(minuteslabel);
	}

	private void addEndDateLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig endDateLayout=new GtnUIFrameworkComponentConfig();
		endDateLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		endDateLayout.setComponentId("endDateLayout");
		endDateLayout.setComponentWidth("100%");
		endDateLayout.setAddToParent(true);
		endDateLayout.setSpacing(true);
		endDateLayout.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkLayoutConfig endDateLayoutConfig=new GtnUIFrameworkLayoutConfig();
		endDateLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		endDateLayout.setGtnLayoutConfig(endDateLayoutConfig);
		componentList.add(endDateLayout);
		
		addEndDate(componentList, endDateLayout.getComponentId());
	}
	
	private void addEndDate(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {

		GtnUIFrameworkComponentConfig endDate = new GtnUIFrameworkComponentConfig();
		endDate.setComponentType(GtnUIFrameworkComponentType.DATEFIELD);
		endDate.setComponentId("endDate");
		endDate.setComponentName("End Date:");
		endDate.setParentComponentId(parentComponentId);
		endDate.setAddToParent(true);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		endDate.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(endDate);
	}

	private void addUpdateButton(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId) {
		GtnUIFrameworkComponentConfig addButtonConfig = new GtnUIFrameworkComponentConfig();
		addButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentId("scheduledProcessEditorUpdateButton");
		addButtonConfig.setComponentName("UPDATE");
		addButtonConfig.setParentComponentId(parentComponentId);
		addButtonConfig.setAddToParent(true);
		componentList.add(addButtonConfig);
		
		
	}	
}