package com.stpl.gtn.gtn2o.ui.module.processscheduler.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkProcessSchedulerStringContants {

	public static final String VALIDATAION_ERROR = "Validation Error :";
	public static final String[] STATUS = new String[] { "Active", "Inactive" };
	public static final String[] TYPE = new String[] { "Both", "Latest Estimate" , "Update Cycle"};
	public static final String HOURS =  "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23"  ;
	public static final String MINUTES =  "0,15,30,45" ;
	
	public static final Object[] PROCESS_SCHEDULER_AUTOMATIC_TABLE_COLUMNS = new Object[] { "processName", "status", "startDate","endDate","Frequency","lastRunDate","modifiedDate", "modifiedBy" };
	
	public static final String[] PROCESS_SCHEDULER_AUTOMATIC_TABLE_HEADER = new String[] { "Process Name", "status","Start Date","End Date", "Frequency","Last Run Date","Modified Date", "Modified By"};

	public static final Class<?>[] PROCESS_SCHEDULER_AUTOMATIC_TABLE_COLUMN_TYPE = new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
			GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING};
	
	public static final String SET_DEFAULT_FREQUENCY = "Interval";

	public static final String TIME = "Time";

	public static final String INTERVAL = "Interval";

	public static final String TWENTY_FOUR = "24";
	public static final String ROOT_LAYOUT = "rootLayout";
	public static final String ROOT_PANEL = "rootPanel";
	public static final String MAIN_LAYOUT = "mainLayout";
	public static final String HUNDERED_TEN_PERCENTAGE = "110%";
	public static final String MANUAL_PROCESSING_LAYOUT = "manualProcessingLayout";
	public static final String FOURTY_PERCENTAGE = "40%";
	public static final String PROCESS_SCHEDULER_VIEW_NAME = "Process Schedular View";
	public static final String PROCESS_SCHEDULER_VIEWID = "V001";
	public static final String MANUAL_PROCESSING_PANEL = "manualProcessingPanel";
	public static final String MANUAL_PROCESSING = "Manual Processing";
	public static final String MANUAL_PROCESSING_PANEL_LAYOUT = "manualProcessingPanelLayout";
	public static final Object SCRIPT_NAME = "scriptname";
	public static final Object PROCESS_SID = "processsid";
	public static final String MANUAL_RUN_ID = "ManualProcessingRunButton";
	public static final String RUN = "RUN";
	public static final Object ERROR = "ERROR";
	public static final Object MESSAGE = "Please select a record to run";
	public static final String PROCESS_SCHEDULAR_MAIN_LAYOUT = "SchProcessingSchProcessEditorLayout";
	public static final String SCHEDULED_PROCESSING_PANEL = "scheduledProcessingPanel";
	public static final String SCHEDULED_PROCESSING_PANEL_LAYOUT = "scheduledProcessingPanelLayout";
	public static final String SCHEDULED_PROCESSING = "Scheduled Processing";
	public static final String SCHEDULER_RESULTS_TABLE = "schedulerResultTable";
	public static final String PROCESS_NAME_ID = "processName";
	public static final String STATUS_ID = "status";
	public static final String RUN_EVERY_ID = "runEvery";
	public static final String START_DATE_ID = "startDate";
	public static final String END_DATE_ID = "endDate";
	public static final String FREQUENCY_ID = "frequency";
	public static final String RUN1_ID = "run1";
	public static final Object MESSAGE_MANDATORY = "Please enter all mandatory information";
	public static final Object MESSAGE_DATE_VALIDATION = "End date should be after start date";

	private GtnFrameworkProcessSchedulerStringContants() {
	}
}
