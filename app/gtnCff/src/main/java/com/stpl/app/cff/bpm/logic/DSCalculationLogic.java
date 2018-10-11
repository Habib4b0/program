package com.stpl.app.cff.bpm.logic;


import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.dataselection.logic.RelationShipFilterLogic;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cff.GtnWsCFFSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsCFFSubmitBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DSCalculationLogic {
    private static final Logger LOGGER = LoggerFactory.getLogger(DSCalculationLogic.class);

	/**
	 * The Constant LOGGER.
	 */
    
    private DSCalculationLogic()
    {
        LOGGER.debug("DSCalculationLogic ");
    }
	public static GtnWsCommonWorkflowResponse startWorkflow(SessionDTO session, String userId) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCFFSubmitRequest submitRequest = new GtnWsCFFSubmitRequest();
        GtnWsCFFSubmitBean submitBean = new GtnWsCFFSubmitBean();
        submitBean.setProjectionId(session.getProjectionId());
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(userId);
        generalRequest.setSessionId(session.getSessionId());
        submitRequest.setGtnWsCFFSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnCffsubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_START_TASK,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        session.setProcessId(workFlowResponse.getProcessInstanceId());
        return workFlowResponse;
    }

	public static GtnWsCommonWorkflowResponse startAndCompleteTask(SessionDTO session, String userId) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCFFSubmitRequest submitRequest = new GtnWsCFFSubmitRequest();
        GtnWsCFFSubmitBean submitBean = new GtnWsCFFSubmitBean();
        submitBean.setProjectionId(session.getProjectionId());
        submitBean.setProcessId(session.getProcessId());
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(userId);
        generalRequest.setSessionId(session.getSessionId());
        submitRequest.setGtnWsCFFSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnCffsubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_COMPLETE_TASK,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        session.setProcessId(workFlowResponse.getProcessInstanceId());
        return workFlowResponse;
    }

    public static String getProcessVariableLog(Long processId, String processVariableName) {
        LOGGER.debug("getProcessVariableLog ");
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCFFSubmitRequest submitRequest = new GtnWsCFFSubmitRequest();
        GtnWsCFFSubmitBean submitProcessBean = new GtnWsCFFSubmitBean();
        submitProcessBean.setProcessId(processId);
        submitProcessBean.setVariableName(processVariableName);
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        submitRequest.setGtnWsCFFSubmitBean(submitProcessBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnCffsubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_GET_VARIABLE,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        return workFlowResponse.getProcessVariable();
    }
    
    public static GtnWsCommonWorkflowResponse isValidWorkflowUser(String userId,Long processId) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceUserRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsCFFSubmitRequest submitUserRequest = new GtnWsCFFSubmitRequest();
        GtnWsCFFSubmitBean submitUserBean = new GtnWsCFFSubmitBean();
        GtnWsGeneralRequest validUserRequest = new GtnWsGeneralRequest();
        validUserRequest.setUserId(userId);
        submitUserRequest.setGtnWsCFFSubmitBean(submitUserBean);
        submitUserRequest.setGtnWsGeneralRequest(validUserRequest);
        submitUserRequest.setProcessId(processId);
        gtnUIFrameworkWebserviceUserRequest.setGtnCffsubmitRequest(submitUserRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_CFF_IS_VALID_USER,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceUserRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
        return response.getGtnWSCommonWorkflowResponse();
    }
}
