package com.stpl.gtn.gtn2o.ui.module.processmonitor.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GtnFrameworkProcessMonitorStringContants {

	public static final String VALIDATAION_ERROR = "Validation Error :";
	private static final String[] CALENDER = new String[] { "Holiday" };
	public static final String RUN = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23";
	public static final String HOURS = "0,15,30,45";
	public static final String PROCESS_TYPE = "processType";
	public static final String GTN_PROCESS_MONITOR_PROCESS_NAME = "processName";
	private static final Object[] PROCESS_MONITOR_TABLE_COLUMNS = new Object[] { GTN_PROCESS_MONITOR_PROCESS_NAME, PROCESS_TYPE,
			"slaCalendarMasterSid", "modifiedDate", "modifiedBy" };

	private static final String[] PROCESS_MONITOR_TABLE_HEADER = new String[] { "Monitor Name", "Process Type",
			"Calendar", "Modified Date", "Modified By" };

	private static final Class<?>[] PROCESS_MONITOR_TABLE_COLUMN_TYPE = new Class<?>[] {
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING };

	public static final String GTN_PROCESS_MONITOR_VALIDATION_MSG = "Please enter ";

	public static final String GTN_PROCESS_MONITOR_VALIDATION_EQUAL_MSG = "Start date and End date should not be equal";

	public static final String GTN_PROCESS_MONITOR_VALIDATION_GREATER_MSG = "End date should be after Start date";

	public static final String GTN_PROCESS_MONITOR_DUPLICATE_PROCESS_NAME = "Process Name already exists";

	public static final String GTN_PROCESS_MONITOR_SAVE_MESSAGE = "Are You Sure To save This Process ";
	public static final String GTN_PROCESS_MONITOR_DELETE_MESSAGE = "Are You Sure To Delete This Process ";

	public static final String GTN_PROCESS_MONITOR_CONFIRMATION = "Confirmation";

	public static final String GTN_PROCESS_MONITOR_PROCESS_TYPE = "process Type";

	public static final String GTN_PROCESS_MONITOR_AUTOMATIC = "Automatic";

	public static final String GTN_PROCESS_MONITOR_UPDATE = "UPDATE";
	public static final String GTN_PROCESS_MONITOR_DELETE = "DELETE";

	public static final String GTN_PROCESS_MONITOR_SAVED_SUCCESSFULLY = "Saved Successfuly";

	public static final String GTN_PROCESS_MONITOR_UPDATED_SUCCESSFULLY = " Updated Successfuly";
	public static final String GTN_PROCESS_MONITOR_DELETED_SUCCESSFULLY = " Deleted Successfuly";

	public static final String GTN_PROCESS_MONITOR_SCHEME_NAME = "BPI";

	public static final String GTN_PROCESS_MONITOR_FREQUENCY = "Time";

	public static final String GTN_PROCESS_MONITOR_ACTIVE_FLAG = "Y";

	public static final String GTN_PROCESS_MONITOR_INBOUND_STATUS_ADD = "A";

	public static final String GTN_PROCESS_MONITOR_INBOUND_STATUS_CHANGE = "C";

	public static final String GTN_PROCESS_MONITOR_ATLEAST_ONE_RUN_TIME = "atleast one run time";

	public static final String GTN_PROCESS_MONITOR_PROCESS_NAMES = "process Name";

	public static final String GTN_PROCESS_MONITOR_START_DATE = "Start Date";

	public static final String GTN_PROCESS_MONITOR_END_DATE = "End Date";
	public static final String RUN_1 = "Run 1:";
	public static final String RUN_2 = "Run 2:";
	public static final String RUN_3 = "Run 3:";

	public static final String GTN_PROCESS_MONITOR_BOTH = "Both";
	public static final String GTN_PROCESS_MONITOR_PROCESSTYPE = PROCESS_TYPE;
	public static final String GTN_PROCESS_MONITOR_RUNONEDDLB = "run1Ddlb";
	public static final String GTN_PROCESS_MONITOR_RUNTWODDLB = "run2Ddlb";
	public static final String GTN_PROCESS_MONITOR_RUNTHREEDDLB = "run3Ddlb";
	public static final String GTN_PROCESS_MONITOR_RUNONEHOURDDLB = "hours1Ddlb";
	
	public static final List<String> PROCESS_MONITOR_FIELDS = Collections.unmodifiableList(
			Arrays.asList(GtnFrameworkCommonConstants.PROCESS_NAME, PROCESS_TYPE, "calender", "startDate", "endDate",
					GtnFrameworkCommonConstants.RUN1_DDLB, GtnFrameworkCommonConstants.RUN2_DDLB,
					GtnFrameworkCommonConstants.RUN3_DDLB, GtnFrameworkCommonConstants.HOURS1_DDLB,
					GtnFrameworkCommonConstants.HOURS2_DDLB, GtnFrameworkCommonConstants.HOURS3_DDLB));
    public static final String PROCESS_MONITOR_ID = "processMonitorId";
    public static final String BUTTON_LAYOUT = "buttonLayout";

	private GtnFrameworkProcessMonitorStringContants() {
	}

	public static String[] getCalendar() {
		return CALENDER.clone();
	}

	public static Object[] getProcessMonitorTableColumns() {
		return PROCESS_MONITOR_TABLE_COLUMNS.clone();
	}

	public static String[] getProcessMonitorTableHeader() {
		return PROCESS_MONITOR_TABLE_HEADER.clone();
	}

	public static Class<?>[] getProcessMonitorTableColumnType() {
		return PROCESS_MONITOR_TABLE_COLUMN_TYPE.clone();
	}
}
