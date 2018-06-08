package com.stpl.app.arm.bpm.logic;

import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.DataSelectionQueryUtils;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;

public class DSCalculationLogic {

    private DSCalculationLogic() {
        /*
        Empty Constructor
         */
    }

    public static GtnWsCommonWorkflowResponse startWorkflow(SessionDTO session, String userId) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsForecastProjectionSubmitRequest submitRequest = new GtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean submitBean = new GtnWsForecastProjectionSubmitBean();
        submitBean.setProjectionId(session.getProjectionId());
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(userId);
        generalRequest.setSessionId(String.valueOf(session.getSessionId()));
        submitRequest.setGtnWsForecastProjectionSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnWsForecastProjectionSubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_ARM_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_ARM_START_TASK,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, DataSelectionQueryUtils.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        session.setProcessId(workFlowResponse.getProcessInstanceId());
        return workFlowResponse;
    }

    public static GtnWsCommonWorkflowResponse startAndCompleteTask(SessionDTO session, String userId) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsForecastProjectionSubmitRequest submitRequest = new GtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean submitBean = new GtnWsForecastProjectionSubmitBean();
        submitBean.setProjectionId(session.getProjectionId());
        submitBean.setProcessId(session.getProcessId());
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(userId);
        generalRequest.setSessionId(String.valueOf(session.getSessionId()));
        submitRequest.setGtnWsForecastProjectionSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnWsForecastProjectionSubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_ARM_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_ARM_COMPLETE_TASK,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, DataSelectionQueryUtils.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        session.setProcessId(workFlowResponse.getProcessInstanceId());
        return workFlowResponse;
    }

    public static String getProcessVariableLog(Long processId, String processVariableName) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsForecastProjectionSubmitRequest submitRequest = new GtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean submitBean = new GtnWsForecastProjectionSubmitBean();
        submitBean.setProcessId(processId);
        submitBean.setVariableName(processVariableName);
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        submitRequest.setGtnWsForecastProjectionSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnWsForecastProjectionSubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsWorkFlowConstants.GTN_WS_ARM_WORKFLOW_SERVICE
                + GtnWsWorkFlowConstants.GTN_WS_ARM_GET_VARIABLE,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, DataSelectionQueryUtils.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        return workFlowResponse.getProcessVariable();
    }
}
