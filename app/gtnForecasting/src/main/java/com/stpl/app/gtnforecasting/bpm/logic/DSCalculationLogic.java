package com.stpl.app.gtnforecasting.bpm.logic;

import com.stpl.app.bpm.dto.ForecastingRulesDTO;
import com.stpl.app.gtnforecasting.bpm.persistance.WorkflowPersistance;
import com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.List;
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

    public static List<ForecastingRulesDTO> getProjectionValues(int projectionId, String userId, String sessionId, String screenName, SessionDTO sessionDto) {
        List<ForecastingRulesDTO> list = new ArrayList<>();
        try {
            List<Object[]> returnList = WorkflowPersistance.getProjectionRecords(projectionId, userId, sessionId, screenName, sessionDto);
            for (int i = 0; i < returnList.size(); i++) {
                Object[] obj = returnList.get(i);
                if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS)) {
                    ForecastingRulesDTO retRate = new ForecastingRulesDTO("Projected_Return_Percent");
                    retRate.setAmountLowest(Double.parseDouble(String.valueOf(obj[0])));
                    retRate.setAmountGreatest(Double.parseDouble(String.valueOf(obj[1])));
                    retRate.setPercentLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.TWO])));
                    retRate.setPercentGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.THREE])));
                    list.add(retRate);
                } else {
                    ForecastingRulesDTO sales = new ForecastingRulesDTO("Projected_Contract_Sales_Dollars");
                    sales.setAmountLowest(Double.parseDouble(String.valueOf(obj[0])));
                    sales.setAmountGreatest(Double.parseDouble(String.valueOf(obj[1])));
                    sales.setPercentLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.TWELVE])));
                    sales.setPercentGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.THIRTEEN])));

                    ForecastingRulesDTO units = new ForecastingRulesDTO("Projected_Contract_Sales_Units");
                    units.setAmountLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.TWO])));
                    units.setAmountGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.THREE])));
                    units.setPercentLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.FOURTEEN])));
                    units.setPercentGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.FIFTEEN])));

                    ForecastingRulesDTO discount = new ForecastingRulesDTO("Projected_Discount_Dollars");
                    discount.setAmountLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR])));
                    discount.setAmountGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE])));
                    discount.setPercentLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.SIXTEEN])));
                    discount.setPercentGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.SEVENTEEN])));

                    ForecastingRulesDTO rate = new ForecastingRulesDTO("Projected_Discount_Rate");
                    rate.setAmountLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])));
                    rate.setAmountGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])));
                    rate.setPercentLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHTEEN])));
                    rate.setPercentGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.NINETEEN])));

                    ForecastingRulesDTO netSales = new ForecastingRulesDTO("Net_Sales");
                    netSales.setAmountLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.EIGHT])));
                    netSales.setAmountGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.NINE])));
                    netSales.setPercentLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.TWENTY])));
                    netSales.setPercentGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.TWENTY_ONE])));

                    ForecastingRulesDTO netProfit = new ForecastingRulesDTO("Net_Profit");
                    netProfit.setAmountLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.TEN])));
                    netProfit.setAmountGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.ELEVEN])));
                    netProfit.setPercentLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.TWENTY_TWO])));
                    netProfit.setPercentGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.TWENTY_THREE])));

                    list.add(sales);
                    list.add(units);
                    list.add(discount);
                    list.add(rate);
                    list.add(netSales);
                    list.add(netProfit);
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    public static List<ForecastingRulesDTO> getProjectionValuesForAccrual(int projectionId, String userId, String sessionId) {
        List<ForecastingRulesDTO> list = new ArrayList<>();
        try {
            List<Object[]> returnList = WorkflowPersistance.getProjectionRecordsForAccrual(projectionId, userId, sessionId);

            for (int i = 0; i < returnList.size(); i++) {
                Object[] obj = returnList.get(i);
                ForecastingRulesDTO accrualRate = new ForecastingRulesDTO("Accrual_Rate_Projection");
                accrualRate.setAmountLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.TWO])));
                accrualRate.setAmountGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.THREE])));
                accrualRate.setPercentLowest(Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])));
                accrualRate.setPercentGreatest(Double.parseDouble(String.valueOf(obj[NumericConstants.SEVEN])));

                list.add(accrualRate);
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        return list;
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
