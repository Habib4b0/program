/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.constants;

/**
 *
 * @author Abishek.Ram
 */
public class GtnWsForecastConstants {
    
	private GtnWsForecastConstants() {
		super();
	}
	
	public static final String GTN_FORECAST_SERVICE = "/GtnForecast";
	public static final String GTN_FORECAST_SERVICE_REGISTRY = "/gtnServiceRegistry";
	public static final String GTN_FORECAST_REDIRECT_QUERY_ENGINE="/serviceRegistryWebservicesForRedirectToQueryEngine";
	public static final String GTN_FORECAST_CUSTOMERHIERARCHY_SEARCHSERVICE = "/GtnWsForecastCustomerHierarchySearchService";
	public static final String GTN_FORECAST_PRODUCTHIERARCHY_SEARCHSERVICE = "/GtnWsForecastProductHierarchySearchService";
	
	public static final String GTN_WS_FORECAST_WORKFLOW_SERVICE = "/GtnWsForecastWorkflowService";
	public static final String GTN_FORECAST_SAVEVIEW_SERVICE = "/forecastSaveViewService";
    public static final String GTN_WS_FORECAST_START_TASK = "/GtnWsForecastStartTask";
    public static final String GTN_WS_FORECAST_COMPLETE_TASK = "/GtnWsForecastCompleteTask";
    public static final String GTN_WS_FORECAST_GET_VARIABLE = "/GetProcessVariable";
    public static final String GTN_WS_FORECAST_SUBMIT_WORKFLOW = "/GtnWsForecastSubmitWorkflow";
    public static final String GTN_WS_FORECAST_APPROVE_WORKFLOW = "/GtnWsForecastApproveWorkflow";
    public static final String GTN_WS_FORECAST_REJECT_WORKFLOW = "/GtnWsForecastRejectWorkflow";
    public static final String GTN_WS_FORECAST_WITHDRAW_WORKFLOW = "/GtnWsForecastWithDrawWorkflow";
    public static final String GTN_WS_FORECAST_CANCEL_WORKFLOW = "/GtnWsForecastCancelWorkflow";
}
