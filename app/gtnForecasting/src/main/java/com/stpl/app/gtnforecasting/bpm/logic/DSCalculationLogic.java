package com.stpl.app.gtnforecasting.bpm.logic;

import com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DSCalculationLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DSCalculationLogic.class);
    private DSCalculationLogic() {
        // DSCalculationLogic
    }

    public static GtnWsCommonWorkflowResponse startWorkflow(SessionDTO session, String userId) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
            GtnWsForecastProjectionSubmitRequest submitRequest = new GtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean submitBean = new GtnWsForecastProjectionSubmitBean();
        submitBean.setProjectionId(session.getProjectionId());
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(userId);
        generalRequest.setSessionId(session.getSessionId());
        submitRequest.setGtnWsForecastProjectionSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnWsForecastProjectionSubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE
                + GtnWsForecastConstants.GTN_WS_FORECAST_START_TASK,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
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
        generalRequest.setSessionId(session.getSessionId());
        submitRequest.setGtnWsForecastProjectionSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnWsForecastProjectionSubmitRequest(submitRequest);
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE
                + GtnWsForecastConstants.GTN_WS_FORECAST_COMPLETE_TASK,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
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
                GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE
                + GtnWsForecastConstants.GTN_WS_FORECAST_GET_VARIABLE,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, RelationShipFilterLogic.getGsnWsSecurityToken());
        GtnWsCommonWorkflowResponse workFlowResponse = response.getGtnWSCommonWorkflowResponse();
        return workFlowResponse.getProcessVariable();
    }
}
