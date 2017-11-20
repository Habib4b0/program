package com.stpl.gtn.gtn2o.ui.module.processscheduler.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkProcessSchedulerStringContants {

	public static final String VALIDATAION_ERROR = "Validation Error :";
	public static final String[] CALENDER = new String[] { "Active", "Inactive" };
	public static final String RUN =  "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23"  ;
	public static final String HOURS =  "0,15,30,45" ;
	
	public static final Object[] PROCESS_SCHEDULER_AUTOMATIC_TABLE_COLUMNS = new Object[] { "processName", "status", "startDate","endDate","Frequency","lastRunDate","modifiedDate", "modifiedBy" };
	
	public static final String[] PROCESS_SCHEDULER_AUTOMATIC_TABLE_HEADER = new String[] { "Process Name", "status","Start Date","End Date", "Frequency","Last Run Date","Modified Date", "Modified By"};

	public static final Class<?>[] PROCESS_SCHEDULER_AUTOMATIC_TABLE_COLUMN_TYPE = new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
			GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING};
	
	public static final String SET_DEFAULT_FREQUENCY = "Interval";

	public static final String TIME = "Time";

	public static final String INTERVAL = "Interval";

	public static final String TWUNTY_FOUR = "24";

	private GtnFrameworkProcessSchedulerStringContants() {
	}
}
