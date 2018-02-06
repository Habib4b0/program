/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.controller;

import com.stpl.app.bpm.dto.ForecastingRulesDTO;
import com.stpl.app.bpm.dto.WorkflowRuleDTO;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.entity.user.User;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.userrole.GtnWsUserRoleService;
import com.stpl.gtn.gtn2o.ws.service.workflow.WorkflowLogicService;
import java.util.ArrayList;
import java.util.List;
import org.kie.api.runtime.process.ProcessInstance;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;
import java.util.HashMap;
import java.util.Map;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Abishek.Ram
 */
@Controller
@RequestMapping(value = GtnWsForecastConstants.GTN_WS_FORECAST_WORKFLOW_SERVICE)
public class GtnWsForecastWorkFlowController {

    public GtnWsForecastWorkFlowController() {
		super();
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsForecastWorkFlowController.class);
    @Autowired
    private WorkflowLogicService workflowLogicService;
    @Autowired
    private GtnWsUserRoleService gtnWsUserRoleService;

    private static final String APPROVE_FLAG = "approveFlag";

    @RequestMapping(value = GtnWsForecastConstants.GTN_WS_FORECAST_START_TASK)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse submitProjectionAndStartWorkFlow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionSubmitRequest.getGtnWsGeneralRequest();
        String userId = gtnWsGeneralRequest.getUserId();
        ProcessInstance processInstance = workflowLogicService.startWorkflow("Forecasting_WorkflowId",
                "ForecastingWorkflow.SubmissionWorkflow", GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
        Long processInstanceId = processInstance.getId();
        User userModel = gtnWsUserRoleService.getUser(Long.parseLong(userId.trim()));
        List<String> roleList = new ArrayList<>();
        LOGGER.info("Process Instance ID=========" + processInstanceId);
        boolean workflowFlag = workflowLogicService.isValidWorkflowUser(userModel, roleList,
                processInstance.getId(), GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setHasPermission(workflowFlag);
        wfResponse.setProcessInstanceId(processInstanceId.intValue());
        wfResponse.setRoleList(roleList);
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }

    @RequestMapping(value = GtnWsForecastConstants.GTN_WS_FORECAST_COMPLETE_TASK)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse forecastCompleteTask(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean = forecastProjectionSubmitRequest
                .getGtnWsForecastProjectionSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionSubmitRequest.getGtnWsGeneralRequest();
        int projectionId = forecastProjectionSubmitBean.getProjectionId();
        String userId = gtnWsGeneralRequest.getUserId();
        User userModel = gtnWsUserRoleService.getUser(Long.parseLong(userId.trim()));
        Long processInstanceId = forecastProjectionSubmitBean.getProcessId();
        TaskSummary taskSummary = workflowLogicService.startAndCompleteTask(userModel, projectionId,
                processInstanceId, GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
        processInstanceId = taskSummary.getProcessInstanceId();
        LOGGER.info("Process Instance ID========= taskSummary.getProcessInstanceId()" + processInstanceId);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setProcessInstanceId(processInstanceId.intValue());
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }

    @RequestMapping(value = GtnWsForecastConstants.GTN_WS_FORECAST_GET_VARIABLE)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse getVariable(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean = forecastProjectionSubmitRequest
                .getGtnWsForecastProjectionSubmitBean();
        String processVariable = workflowLogicService.getProcessVariable(forecastProjectionSubmitBean.getProcessId(), forecastProjectionSubmitBean.getVariableName(), GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setProcessVariable(processVariable);
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }

    @RequestMapping(value = GtnWsForecastConstants.GTN_WS_FORECAST_SUBMIT_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse submitWorkflow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean = forecastProjectionSubmitRequest
                .getGtnWsForecastProjectionSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionSubmitRequest.getGtnWsGeneralRequest();
        Map<String, Object> params = new HashMap<>();
        params.put("projectionId", forecastProjectionSubmitBean.getProjectionId());
        List<ForecastingRulesDTO> list = workflowLogicService.getProjectionValues(
                forecastProjectionSubmitBean.getProjectionId(), forecastProjectionSubmitBean.getModuleName(), gtnWsGeneralRequest.getUserId(), gtnWsGeneralRequest.getSessionId());
        for (ForecastingRulesDTO forecastingRulesDTO : list) {
            params.put("out_" + forecastingRulesDTO.getVariableName(), forecastingRulesDTO);
        }
        WorkflowRuleDTO dto = new WorkflowRuleDTO();
        params.put("out_workflowDTO", dto);
        workflowLogicService.updateTaskInBpm(gtnWsGeneralRequest.getUserId(), forecastProjectionSubmitBean.getProcessId(), params,
                GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
    
    @RequestMapping(value = GtnWsForecastConstants.GTN_WS_FORECAST_APPROVE_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse approveWorkFlow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponseApproveWorkFlow = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponseApproveWorkFlow = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequestApproveWorkFlow = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBeanApproveWorkFlow = forecastProjectionSubmitRequestApproveWorkFlow
                .getGtnWsForecastProjectionSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequestApproveWorkFlow = forecastProjectionSubmitRequestApproveWorkFlow.getGtnWsGeneralRequest();
        Map<String, Object> paramsApproveWorkFlow = new HashMap<>();
				paramsApproveWorkFlow.put(APPROVE_FLAG, "approve");
				workflowLogicService.updateTaskInBpm(gtnWsGeneralRequestApproveWorkFlow.getUserId(), forecastProjectionSubmitBeanApproveWorkFlow.getProcessId(), paramsApproveWorkFlow,
						GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
        GtnWsCommonWorkflowResponse wfResponseApproveWorkFlow = new GtnWsCommonWorkflowResponse();
        generalResponseApproveWorkFlow.setSucess(true);
        gtnWsresponseApproveWorkFlow.setGtnWSCommonWorkflowResponse(wfResponseApproveWorkFlow);
        gtnWsresponseApproveWorkFlow.setGtnWsGeneralResponse(generalResponseApproveWorkFlow);
        return gtnWsresponseApproveWorkFlow;
    }
    
     @RequestMapping(value = GtnWsForecastConstants.GTN_WS_FORECAST_REJECT_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse rejectWorkflow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponseRejectWorkflow = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponseRejectWorkflow = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequestRejectWorkflow = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBeanRejectWorkflow = forecastProjectionSubmitRequestRejectWorkflow
                .getGtnWsForecastProjectionSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequestRejectWorkflow = forecastProjectionSubmitRequestRejectWorkflow.getGtnWsGeneralRequest();
        Map<String, Object> paramsRejectWorkflow = new HashMap<>();
				paramsRejectWorkflow.put(APPROVE_FLAG, "reject-RWC");
				workflowLogicService.updateTaskInBpm(gtnWsGeneralRequestRejectWorkflow.getUserId(), forecastProjectionSubmitBeanRejectWorkflow.getProcessId(), paramsRejectWorkflow,
						GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
        GtnWsCommonWorkflowResponse wfResponseRejectWorkflow = new GtnWsCommonWorkflowResponse();
        generalResponseRejectWorkflow.setSucess(true);
        gtnWsresponseRejectWorkflow.setGtnWSCommonWorkflowResponse(wfResponseRejectWorkflow);
        gtnWsresponseRejectWorkflow.setGtnWsGeneralResponse(generalResponseRejectWorkflow);
        return gtnWsresponseRejectWorkflow;
    }
     @RequestMapping(value = GtnWsForecastConstants.GTN_WS_FORECAST_WITHDRAW_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse withDrawWorkflow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponseWithDrawWorkflow = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponseWithDrawWorkflow = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequestWithDrawWorkflow = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBeanWithDrawWorkflow = forecastProjectionSubmitRequestWithDrawWorkflow
                .getGtnWsForecastProjectionSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequestWithDrawWorkflow = forecastProjectionSubmitRequestWithDrawWorkflow.getGtnWsGeneralRequest();
        Map<String, Object> paramsWithDrawWorkflow = new HashMap<>();
				paramsWithDrawWorkflow.put(APPROVE_FLAG, "withdraw-RWC");
				workflowLogicService.updateTaskInBpm(gtnWsGeneralRequestWithDrawWorkflow.getUserId(), forecastProjectionSubmitBeanWithDrawWorkflow.getProcessId(), paramsWithDrawWorkflow,
						GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
        GtnWsCommonWorkflowResponse wfResponseWithDrawWorkflow = new GtnWsCommonWorkflowResponse();
        generalResponseWithDrawWorkflow.setSucess(true);
        gtnWsresponseWithDrawWorkflow.setGtnWSCommonWorkflowResponse(wfResponseWithDrawWorkflow);
        gtnWsresponseWithDrawWorkflow.setGtnWsGeneralResponse(generalResponseWithDrawWorkflow);
        return gtnWsresponseWithDrawWorkflow;
    }
    
      @RequestMapping(value = GtnWsForecastConstants.GTN_WS_FORECAST_CANCEL_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse cancelWorkflow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponseCancelWorkflow = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponseCancelWorkflow = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequestCancelWorkflow = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBeanCancelWorkflow = forecastProjectionSubmitRequestCancelWorkflow
                .getGtnWsForecastProjectionSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequestCancelWorkflow = forecastProjectionSubmitRequestCancelWorkflow.getGtnWsGeneralRequest();
        Map<String, Object> paramsCancelWorkflow = new HashMap<>();
				paramsCancelWorkflow.put(APPROVE_FLAG, "cancel-RWC");
				workflowLogicService.updateTaskInBpm(gtnWsGeneralRequestCancelWorkflow.getUserId(), forecastProjectionSubmitBeanCancelWorkflow.getProcessId(), paramsCancelWorkflow,
						GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
        GtnWsCommonWorkflowResponse wfResponseCancelWorkflow = new GtnWsCommonWorkflowResponse();
        generalResponseCancelWorkflow.setSucess(true);
        gtnWsresponseCancelWorkflow.setGtnWSCommonWorkflowResponse(wfResponseCancelWorkflow);
        gtnWsresponseCancelWorkflow.setGtnWsGeneralResponse(generalResponseCancelWorkflow);
        return gtnWsresponseCancelWorkflow;
    }
    

}
