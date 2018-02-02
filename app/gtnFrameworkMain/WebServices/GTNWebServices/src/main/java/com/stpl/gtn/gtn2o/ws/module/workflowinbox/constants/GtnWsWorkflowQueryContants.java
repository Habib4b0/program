package com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;

public class GtnWsWorkflowQueryContants {

	public static final String GTN_WF_CONTRACT_COUNT = GtnFrameworkWebserviceConstant.SELECT_COUNT;
	public static final String GTN_WF_CONTRACT_COUNT_QUERY = "  FROM WORKFLOW_MASTER WM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID JOIN CONTRACT_MASTER CM on WM.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID ";
	public static final String GTN_WF_CONTRACT_SEARCH_QUERY = " FROM WORKFLOW_MASTER WM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID JOIN CONTRACT_MASTER CM on WM.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID ";
	public static final String GTN_WF_CONTRACT_SEARCH_QUERY_WHERE_CLAUSE = " WM.WORKFLOW_ID like 'CM%' ";

	public static final String GTN_WF_FORECASTING_COUNT = GtnFrameworkWebserviceConstant.SELECT_COUNT;
	public static final String GTN_WF_FORECASTING_COUNT_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_MASTER_WM_JOIN_DBO_HELPER_T;
	public static final String GTN_WF_FORECASTING_SEARCH_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_MASTER_WM_JOIN_DBO_HELPER_T;
	public static final String GTN_WF_FORECASTING_SEARCH_QUERY_WHERE_CLAUSE = "  WM.WORKFLOW_ID like '_F%' ";

	public static final String GTN_WF_ARP_COUNT = GtnFrameworkWebserviceConstant.SELECT_COUNT;
	public static final String GTN_WF_ARP_COUNT_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_MASTER_WM_JOIN_DBO_HELPER_T;
	public static final String GTN_WF_ARP_SEARCH_QUERY = "FROM WORKFLOW_MASTER WM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID JOIN PROJECTION_MASTER PM on WM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID JOIN COMPANY_MASTER GL_COMp on GL_COMp.COMPANY_MASTER_SID=PM.COMPANY_MASTER_SID JOIN COMPANY_MASTER BU_COMp on BU_COMp.COMPANY_MASTER_SID=PM.BUSINESS_UNIT ";
	public static final String GTN_WF_ARP_SEARCH_QUERY_WHERE_CLAUSE = "  WM.WORKFLOW_ID like 'ARF%' ";

	public static final String GTN_WF_RETURNS_COUNT = GtnFrameworkWebserviceConstant.SELECT_COUNT;
	public static final String GTN_WF_RETURNS_COUNT_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_MASTER_WM_JOIN_DBO_HELPER_T;
	public static final String GTN_WF_RETURNS_SEARCH_QUERY = "FROM WORKFLOW_MASTER WM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID JOIN PROJECTION_MASTER PM on WM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID JOIN COMPANY_MASTER GL_COMp on GL_COMp.COMPANY_MASTER_SID=PM.COMPANY_MASTER_SID JOIN COMPANY_MASTER BU_COMp on BU_COMp.COMPANY_MASTER_SID=PM.BUSINESS_UNIT ";
	public static final String GTN_WF_RETURNS_SEARCH_QUERY_WHERE_CLAUSE = "  WM.WORKFLOW_ID like 'RE%' ";

	public static final String GTN_WF_ARM_COUNT = GtnFrameworkWebserviceConstant.SELECT_COUNT;
	public static final String GTN_WF_ARM_COUNT_QUERY = "  FROM WORKFLOW_MASTER WM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID JOIN PROJECTION_MASTER PM on WM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID JOIN ARM_ADJUSTMENT_MASTER arm_M on arm_M.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID JOIN HELPER_TABLE CONF_NAME on CONF_NAME.HELPER_TABLE_SID = arm_M.CONFIGURATION_TYPE JOIN COMPANY_MASTER GL_COMp on GL_COMp.COMPANY_MASTER_SID=PM.COMPANY_MASTER_SID JOIN COMPANY_MASTER BU_COMp on BU_COMp.COMPANY_MASTER_SID=arm_M.BU_COMPANY_MASTER_SID JOIN ARM_ADJUSTMENT_CONFIG ADJ_CONF on ADJ_CONF.ARM_ADJUSTMENT_CONFIG_SID = arm_M.TRANSACTION_TYPE JOIN HELPER_TABLE TRANX_NAME on TRANX_NAME.HELPER_TABLE_SID = ADJ_CONF.METHODOLGY JOIN ARM_ADJUSTMENT_DETAILS PD ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID JOIN RS_CONTRACT rs ON rs.RS_MODEL_SID = PD.RS_MODEL_SID ";
	public static final String GTN_WF_ARM_SEARCH_QUERY = " FROM WORKFLOW_MASTER WM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID JOIN PROJECTION_MASTER PM on WM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID JOIN ARM_ADJUSTMENT_MASTER arm_M on arm_M.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID JOIN HELPER_TABLE CONF_NAME on CONF_NAME.HELPER_TABLE_SID = arm_M.CONFIGURATION_TYPE JOIN COMPANY_MASTER GL_COMp on GL_COMp.COMPANY_MASTER_SID=PM.COMPANY_MASTER_SID JOIN COMPANY_MASTER BU_COMp on BU_COMp.COMPANY_MASTER_SID=arm_M.BU_COMPANY_MASTER_SID JOIN ARM_ADJUSTMENT_CONFIG ADJ_CONF on ADJ_CONF.ARM_ADJUSTMENT_CONFIG_SID = arm_M.TRANSACTION_TYPE JOIN HELPER_TABLE TRANX_NAME on TRANX_NAME.HELPER_TABLE_SID = ADJ_CONF.METHODOLGY JOIN ARM_ADJUSTMENT_DETAILS PD ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID JOIN RS_CONTRACT  rs on rs.RS_MODEL_SID=PD.RS_MODEL_SID  ";
	public static final String GTN_WF_ARM_SEARCH_QUERY_WHERE_CLAUSE = "WM.WORKFLOW_ID like 'ARM_TRX%'AND PM.FORECASTING_TYPE in ('ARM')";

	public static final String GTN_WF_LOOKUP_STATUS_DESCRIPTION_QUERY = " JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = WM.WORKFLOW_STATUS ";
	
	public static final String GTN_WF_PRIVATE_VIEW_COUNT = " SELECT COUNT(WORKFLOW_ID)  ";
	public static final String GTN_WF_PRIVATE_VIEW_COUNT_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_INBOX_WM + GTN_WF_LOOKUP_STATUS_DESCRIPTION_QUERY;
	public static final String GTN_WF_PRIVATE_VIEW_SEARCH_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_INBOX_WM_QUERY + GTN_WF_LOOKUP_STATUS_DESCRIPTION_QUERY;
	public static final String GTN_WF_PRIVATE_VIEW_SEARCH_QUERY_WHERE_CLAUSE = "  VIEW_TYPE = 'Private' ";

	public static final String GTN_WF_PUBLIC_VIEW_COUNT = " SELECT COUNT(WORKFLOW_ID)  ";
	public static final String GTN_WF_PUBLIC_VIEW_COUNT_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_INBOX_WM + GTN_WF_LOOKUP_STATUS_DESCRIPTION_QUERY;
	public static final String GTN_WF_PUBLIC_VIEW_SEARCH_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_INBOX_WM_QUERY + GTN_WF_LOOKUP_STATUS_DESCRIPTION_QUERY;
	public static final String GTN_WF_PUBLIC_VIEW_SEARCH_QUERY_WHERE_CLAUSE = "  VIEW_TYPE = 'Public'   ";

	public static final String GTN_WF_CREATED_BY_COUNT = " SELECT COUNT(*)  ";
	public static final String GTN_WF_CREATED_BY_COUNT_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_INBOX_WM;
	public static final String GTN_WF_CREATED_BY_SEARCH_QUERY = GtnFrameworkWebserviceConstant.FROM_WORKFLOW_INBOX_WM_QUERY;
	public static final String GTN_WF_CREATED_BY_SEARCH_QUERY_WHERE_CLAUSE = "  WM.WORKFLOW_ID like 'RE%' ";

	public static final String GTN_WF_HISTORY_COUNT = GtnFrameworkWebserviceConstant.SELECT_COUNT;
	public static final String GTN_WF_HISTORY_COUNT_QUERY = "  FROM HIST_WORKFLOW_MASTER HWM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = HWM.WORKFLOW_STATUS_ID  ";
	public static final String GTN_WF_HISTORY_SEARCH_QUERY = " FROM HIST_WORKFLOW_MASTER HWM JOIN dbo.HELPER_TABLE HT_STATUS on HT_STATUS.HELPER_TABLE_SID = HWM.WORKFLOW_STATUS_ID  ";
	public static final String GTN_WF_HISTORY_SEARCH_QUERY_WHERE_CLAUSE = " HWM.WORKFLOW_MASTER_SID like '?' ";

	public static final String GTN_WF_ATTACHMENT_COUNT = GtnFrameworkWebserviceConstant.SELECT_COUNT;
	public static final String GTN_WF_ATTACHMENT_COUNT_QUERY = "  FROM DOC_DETAILS DD ";
	public static final String GTN_WF_ATTACHMENT_SEARCH_QUERY = " FROM DOC_DETAILS DD ";
	public static final String GTN_WF_ATTACHMENT_SEARCH_QUERY_WHERE_CLAUSE = " DD.DOC_DETAILS_ID IN '?' ";
        
	public static final String GET_GCM_WF_LIST = "/getGcmWfList";
	public static final String WORKFLOWID = "workflowId";
	public static final String WORKFLOWNAME = "workflowName";
	public static final String WORKFLOWDESC = "workflowDesc";
	public static final String STATUS = "status";
	public static final String CREATEDBY = "createdBy";
	public static final String CREATEDDATE = "creationDate";
	public static final String APPROVEDBY = "approvedBy";
	public static final String APPROVEDDATE = "approvedDate";
	public static final String GETGCMWFQUERY = "getGcmWorkflowQuery";
	public static final String WHERE_PROJECTIONID = "WHERE PM.PROJECTION_MASTER_SID = ";
	public static final String MMDDYYYY = "MM/dd/yyyy";
	public static final String YYYYSSS = "yyyy-MM-dd hh:mm:ss.SSS";
	public static final String MMYYYYSS = "MM/dd/yyyy hh:mm:ss";
	public static final String WORKFLOWLOOKUPSLASH = "/workFlowLookup";
        
        
    
	private GtnWsWorkflowQueryContants() {
	}

}
