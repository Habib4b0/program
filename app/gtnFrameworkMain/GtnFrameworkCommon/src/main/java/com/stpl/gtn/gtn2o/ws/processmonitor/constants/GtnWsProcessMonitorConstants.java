/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.processmonitor.constants;

/**
 *
 * @author
 */
public class GtnWsProcessMonitorConstants {
    private GtnWsProcessMonitorConstants(){
        /**
         * empty constructor
         */
    }

	public static final String PROCESS_MONITOR_PACKAGE = "com.stpl.gtn.gtn2o.ui.module.processmonitor.action.";
	public static final String GTN_PROCESS_MONITOR_SERVICE_SCREEN = "/GtnProcessMonitorService";
	public static final String GET_PROCESS_MONITOR_TABLE_DATA = "/getProcessMonitorTableData";
	public static final String CHECK_SAVE_PROCESS_MONITOR = "/checkSaveProcessMonitor";
	public static final String SAVE_PROCESS_MONITOR = "/saveProcessMonitor";
	public static final String PROCESS_TYPE_VALUE_MANUAL = "Manual";
	public static final String GTNFRAMEWORK_PROCESS_TYPE_DDLB_CHANGE_ACTION = PROCESS_MONITOR_PACKAGE
			+ "GtnUIFrameworkProcessTypeDblbChangeAction";
	public static final String SET_DEFAULT_HOLIDAY_SCHEDULE = "Holiday Schedule";
	public static final String SET_DEFAULT_HOURS = "Select Hour";
	public static final String SET_DEFAULT_MINUTES = "Select Minute";

	public static final String PROCESS_MONITOR_VALIDATION_ACTION = PROCESS_MONITOR_PACKAGE
			+ "GtnFrameworkProcessMonitorValidationAction";

	public static final String PROCESS_MONITOR_SAVE_ACTION = PROCESS_MONITOR_PACKAGE + "GtnFrameworkSaveButtonAction";

	public static final String GTN_WS_PROCESS_MONITOR_SAVE_SERVICE = "/saveService";
	public static final String GTN_WS_PROCESS_MONITOR_DELETE_SERVICE = "/pmDeleteService";

	public static final String GTN_WS_PROCESS_MONITOR_UPDATE_SERVICE = "/updateService";

	public static final String GTN_WS_PROCESS_MONITOR_DUPLICATE_PROCESS_NAME_SERVICE = "/duplicateProcessNameService";

	public static final String PROCESS_MONITOR_TABLE_CLICK_ACTION = PROCESS_MONITOR_PACKAGE
			+ "GtnFrameworkTableClickAction";

	public static final String GTN_WS_PROCESS_MONITOR_FETCH_INFORMATION_SERVICE = "/fetchProcessMonitorInformation";

	public static final String GTN_PROCESS_MONITOR_ADD_BUTTON = "gtnAddButton";

	public static final String GTN_PROCESS_MONITOR_ADD = "ADD"; 
	public static final String GTN_PROCESS_MONITOR_DELETE = "pmDeleteButton"; 

}
