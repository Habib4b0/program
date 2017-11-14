/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.processscheduler.constants;

/**
 *
 * @author
 */
public class GtnWsProcessScedulerConstants {
    private GtnWsProcessScedulerConstants(){
        /**
         * empty constructor
         */
    }

	public static final String PROCESS_SCHEDULER_PACKAGE = "com.stpl.gtn.gtn2o.ui.module.processmonitor.action.";

	public static final String GTN_PROCESS_SCHEDULER_SERVICE_SCREEN = "/GtnProcessSchedulerService";

	public static final String GET_PROCESS_SCHEDULER_TABLE_DATA = "/getProcessSchedulerTableData";

	public static final String PROCESS_SCHEDULER_TABLE_CLICK_ACTION = PROCESS_SCHEDULER_PACKAGE
			+ "GtnFrameworkTableClickAction";

	public static final String PROCESS_SCHEDULER_UPDATE_ACTION = PROCESS_SCHEDULER_PACKAGE
			+ "GtnFrameworkUpdateButtonAction";

	public static final String PROCESS_SCHEDULER_RUN_ACTION = PROCESS_SCHEDULER_PACKAGE + "GtnFrameworkRunButtonAction";

	public static final String GTN_WS_PROCESS_SCHEDULER_UPDATE_SERVICE = "/updateService";

	public static final String GTN_WS_PROCESS_SCHEDULER_RUN_SERVICE_DATA = "/runService";

}
