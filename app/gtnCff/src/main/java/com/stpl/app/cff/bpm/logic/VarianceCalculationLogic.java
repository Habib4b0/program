package com.stpl.app.cff.bpm.logic;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.dataSelection.logic.RelationShipFilterLogic;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cff.GtnWsCFFSubmitRequest;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsCFFSubmitBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;

public class VarianceCalculationLogic {

    public static void submitWorkflow(final Long processInstanceId, final SessionDTO session, String moduleName) {
        
        new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_SUBMIT_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processInstanceId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }

    public static void approveWorkflow(long processId, SessionDTO session, String moduleName) {
        new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_APPROVE_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }

    private static GtnUIFrameworkWebserviceRequest getWebserviceRequest(final Long processInstanceId, final SessionDTO session, String moduleName) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCFFSubmitRequest submitRequest = new GtnWsCFFSubmitRequest();
        GtnWsCFFSubmitBean submitBean = new GtnWsCFFSubmitBean();
        submitBean.setProcessId(processInstanceId);
        submitBean.setProjectionId(session.getProjectionId());
        submitBean.setModuleName(moduleName);
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(session.getUserId());
        generalRequest.setSessionId(session.getSessionId());
        submitRequest.setGtnWsCFFSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnCffsubmitRequest(submitRequest);
        return gtnUIFrameworkWebserviceRequest;
    }

    public static void rejectWorkFlow(long processId, SessionDTO session, String moduleName) {
        new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_REJECT_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }

    public static void withDrawWorkflow(long processId, SessionDTO session, String moduleName) {
        new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_WITHDRAW_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }

    public static void cancelWorkFlow(long processId, SessionDTO session, String moduleName) {
        new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_CANCEL_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }
}
