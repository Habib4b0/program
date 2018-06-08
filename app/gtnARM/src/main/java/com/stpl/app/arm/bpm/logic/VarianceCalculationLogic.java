package com.stpl.app.arm.bpm.logic;

import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.DataSelectionQueryUtils;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;

public class VarianceCalculationLogic {

    private VarianceCalculationLogic() {
        /*
        Empty Constructor
         */
    }

    public static void submitWorkflow(final Long processInstanceId, final SessionDTO session,String moduleName) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsForecastProjectionSubmitRequest submitRequest = new GtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean submitBean = new GtnWsForecastProjectionSubmitBean();
        submitBean.setProcessId(processInstanceId);
        submitBean.setProjectionId(session.getProjectionId());
        submitBean.setModuleName(moduleName);
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(String.valueOf(session.getUserId()));
        generalRequest.setSessionId(String.valueOf(session.getSessionId()));
        submitRequest.setGtnWsForecastProjectionSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnWsForecastProjectionSubmitRequest(submitRequest);
         new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE
                + GtnWsForecastConstants.GTN_WS_FORECAST_SUBMIT_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, gtnUIFrameworkWebserviceRequest, DataSelectionQueryUtils.getGsnWsSecurityToken());
    }
    
}
