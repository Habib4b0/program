package com.stpl.app.gtnforecasting.bpm.logic;

import com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.ForecastForm;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;

public class VarianceCalculationLogic {

    static ForecastForm forecastForm;
    static String notiMsg = "";
    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(VarianceCalculationLogic.class);

    public static void submitWorkflow(final Long processInstanceId, final SessionDTO session, String moduleName) {

        new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE
                + GtnWsForecastConstants.GTN_WS_FORECAST_SUBMIT_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processInstanceId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }

    public static void approveWorkflow(long processId, SessionDTO session, String moduleName) {
        new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE
                + GtnWsForecastConstants.GTN_WS_FORECAST_APPROVE_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }

    private static GtnUIFrameworkWebserviceRequest getWebserviceRequest(final Long processInstanceId, final SessionDTO session, String moduleName) {
        GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsForecastProjectionSubmitRequest submitRequest = new GtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean submitBean = new GtnWsForecastProjectionSubmitBean();
        submitBean.setProcessId(processInstanceId);
        submitBean.setProjectionId(session.getProjectionId());
        submitBean.setModuleName(moduleName);
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(session.getUserId());
        generalRequest.setSessionId(session.getSessionId());
        submitRequest.setGtnWsForecastProjectionSubmitBean(submitBean);
        submitRequest.setGtnWsGeneralRequest(generalRequest);
        gtnUIFrameworkWebserviceRequest.setGtnWsForecastProjectionSubmitRequest(submitRequest);
        return gtnUIFrameworkWebserviceRequest;
    }

    public static void rejectWorkFlow(long processId, SessionDTO session, String moduleName) {
        new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE
                + GtnWsForecastConstants.GTN_WS_FORECAST_REJECT_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }

    public static void withDrawWorkflow(long processId, SessionDTO session, String moduleName) {
         new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE
                + GtnWsForecastConstants.GTN_WS_FORECAST_WITHDRAW_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }

    public static void cancelWorkFlow(long processId, SessionDTO session, String moduleName) {
         new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE
                + GtnWsForecastConstants.GTN_WS_FORECAST_CANCEL_WORKFLOW,
                GtnFrameworkCommonStringConstants.GTN_BPM, getWebserviceRequest(processId, session, moduleName), RelationShipFilterLogic.getGsnWsSecurityToken());
    }

}
