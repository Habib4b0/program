/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

/**
 *
 * @author Sibi.Chakaravarthy
 */
public final class GtnWsForecastReturnsConstants {

	private GtnWsForecastReturnsConstants() {

	}

	public static final String GTN_WS_RETURNS_FORECAST_CREATE_FILE_SERVICE = "/writeForecastReturnsFiles";

	public static final String GTN_WS_RETURNS_FORECAST_DATE_CONFIGURATION_SERVICE = "/configureForecastDateDetails";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_COUNT_SERVICE = "/getCount";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LOAD_DATA_SERVICE = "/loadData";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_DATA_SERVICE = "/loadBulkData";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_BULK_LOAD_COUNT_SERVICE = "/loadCount";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_LEFT_HEADERS_SERVICE = "/getLeftHeaders";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_RIGHT_HEADERS_SERVICE = "/getRightHeaders";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_CALCULATE_SERVICE = "/calculate";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_UPDATE_CHECKBOX_SERVICE = "/updateCheckedNodesInFile";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_TAB_READ_CHECKBOX_SERVICE = "/readCheckedNodesFromFile";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_MASTER_SAVE_SERVICE = "/saveProjectionMaster";

	public static final String GTN_WS_RETURNS_FORECAST_VIEW_SAVE_SERVICE = "/saveForecastView";

	public static final String GTN_WS_RETURNS_FORECAST_CREATE_FILE_ON_EDIT_SERVICE = "/writeForecastReturnsFilesOnEdit";

	public static final String GTN_WS_RETURNS_FORECAST_GET_PROJECTION_SELECTION_DETAILS_SERVICE = "/getProjectionSelectionDetails";

	public static final String GTN_WS_RETURNS_FORECAST_GET_PROJECTION_VIEW_DETAILS_SERVICE = "/getProjectionViewDetailsService";

	public static final String GTN_WS_RETURNS_FORECAST_GET_EXISTING_VIEW_DETAILS_SERVICE = "/getExistingViewService";

	public static final String GTN_WS_RETURNS_FORECAST_DELETE_FILE_ON_CLOSE_SERVICE = "/deleteForecastReturnsFilesOnClose";

	public static final String GTN_WS_RETURNS_FORECAST_VIEW_DELETE_SERVICE = "/viewDeleteService";

	public static final String GTN_WS_RETURNS_FORECAST_SEARCH_SERVICE = "/gtnReturnsForecasting/DataSelection/getDataSelectionSearch";

	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_DELETE_SERVICE = "/gtnForecastReturns/deleteService";

	public static final String GTN_WS_RETURNS_FORECAST_PRIVATE_VIEW_SEARCH_SERVICE = "/gtnReturnsForecasting/lookUp/getPrivateViewSearchLookUp";

	public static final String GTN_WS_RETURNS_FORECAST_PRODUCT_GROUP_SEARCH_SERVICE = "/gtnReturnsForecasting/lookUp/getProductGroupLookUp";

	public static final String GTN_WS_RETURNS_FORECAST_HIERARCHY_SEARCH_SERVICE = "/gtnReturnsForecasting/lookUp/getHierarchyLookUp";

	public static final String GTN_WS_RETURNS_FORECAST_PUBLIC_VIEW_SEARCH_SERVICE = "/gtnReturnsForecasting/lookUp/getPublicViewSearchLookUp";

	public static final String GTN_WS_RETURNS_FORECAST_GET_VIEW_DETAILS_SERVICE = "/gtnReturnsForecasting/lookUp/getReturnsForecastViewDetailsService";

	public static final String GTN_WS_RETURNS_FORECAST_BUSINESSUNIT_VALIDATION_SERVICE = "/gtnReturnsForecasting/DataSelectionValidation/getBusinessUnitValidation";

	public static final String GTN_WS_RETURNS_FORECAST_CREATE_FILE_DS_TAB_CHANGE_SERVICE = "/writeForecastReturnsFilesOnDSTabChange";

	// Added for Submit

	public static final String GTN_WS_SUBMIT_CONTROLER_URI = "/GtnWsWorkFlowSubmissionController";

	public static final String GTN_WS_SUBMIT_PROJECTION_URI = "/submitProjection";
	// Excel service URL
	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_EXCEL_DATA_SERVICE = "/getReturnsExcelData";
	public static final String GTN_WS_RETURNS_FORECAST_PROJECTION_EXCEL_HEADERS_SERVICE = "/getReturnsExcelHeaders";

	// Added for Dual List Box

	public static final String GTN_WS_RETURNS_FROECAST_DUAL_LIST_BOX_LEFT_TABLE_LOAD_SERVICE = "/gtnReturnsForecasting/DataSelection/loadDualListBoxLeftTable";

	public static final String GTN_WS_RETURNS_FROECAST_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_SERVICE = "/gtnReturnsForecasting/DataSelection/loadDualListBoxRightTable";

	public static final String GTN_WS_APPROVE_CONTROLER_URI = "/GtnWsWorkFlowApprovalController";

	public static final String GTN_WS_APPROVE_PROJECTION_URI = "/approveProjection";

	public static final String GTN_WS_CANCEL_PROJECTION_URI = "/cancelProjection";

	public static final String GTN_WS_CANCEL_CONTROLER_URI = "/GtnWsWorkFlowCancelController";

	public static final String GTN_WS_WITHDRAW_CONTROLER_URI = "/GtnWsWorkFlowWithdrawController";

	public static final String GTN_WS_WITHDRAW_PROJECTION_URI = "/withdrawProjection";

	public static final String GTN_WS_REJECT_CONTROLER_URI = "/GtnWsWorkFlowRejectController";

	public static final String GTN_WS_REJECT_PROJECTION_URI = "/rejectProjection";

	public static final String GTN_WS_PROJECTION_DETAILS_FOR_WORKFLOW_SERVICE = "/getProjectionDetailsForWorkflow";

	public static final String GTN_WS_RETURNS_FORECAST_WRITE_DATA_SELECTION_FILE = "/writeDataSelectionFile";

	public static final String GTN_WS_RETURNS_FORECAST_READ_DATA_SELECTION_FILE = "/readDataSelectionFile";
}
