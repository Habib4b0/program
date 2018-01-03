/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.controller;

import com.stpl.app.bpm.dto.WorkflowRuleDTO;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.entity.user.User;
import com.stpl.gtn.gtn2o.ws.service.userrole.GtnWsUserRoleService;
import com.stpl.gtn.gtn2o.ws.service.workflow.WorkflowLogicService;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cff.GtnWsCFFSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsCFFSubmitBean;
import java.util.ArrayList;
import java.util.HashMap;
import org.kie.api.runtime.process.ProcessInstance;
import java.util.List;
import java.util.Map;
import org.kie.api.task.model.TaskSummary;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_WORKFLOW_SERVICE)
public class GtnWsCFFWorkFlowController {
    
    
    private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCFFWorkFlowController.class);
    @Autowired
    private WorkflowLogicService workflowLogicService;
    @Autowired
    private GtnWsUserRoleService gtnWsUserRoleService;
    
    @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_START_TASK)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse submitProjectionAndStartWorkFlow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsCFFSubmitRequest cffSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnCffsubmitRequest();
        GtnWsGeneralRequest gtnWsGeneralRequest = cffSubmitRequest.getGtnWsGeneralRequest();
        String userId = gtnWsGeneralRequest.getUserId();
        ProcessInstance processInstance = workflowLogicService.startWorkflow("CFF_WorkflowId", "CFFWorkflow.CFFWorkflow", GtnWsBpmCommonConstants.CFF);
        Long processInstanceId = processInstance.getId();
        User userModel = gtnWsUserRoleService.getUser(Long.parseLong(userId.trim()));
        List<String> roleList = new ArrayList<>();
        LOGGER.info("Process Instance ID=========" + processInstanceId);
        boolean workflowFlag = workflowLogicService.isValidWorkflowUser(userModel, roleList,
                processInstance.getId(), GtnWsBpmCommonConstants.CFF);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setHasPermission(workflowFlag);
        wfResponse.setProcessInstanceId(processInstanceId.intValue());
        wfResponse.setRoleList(roleList);
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
    
    
    @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_COMPLETE_TASK)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse cffCompleteTask(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsCFFSubmitRequest cffSubmitCompleteRequest = gtnUIFrameworkWebserviceRequest
                .getGtnCffsubmitRequest();
        GtnWsCFFSubmitBean cffProjectionSubmitBean = cffSubmitCompleteRequest
                .getGtnWsCFFSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequest = cffSubmitCompleteRequest.getGtnWsGeneralRequest();
        int projectionId = cffProjectionSubmitBean.getProjectionId();
        String userId = gtnWsGeneralRequest.getUserId();
        User userModel = gtnWsUserRoleService.getUser(Long.parseLong(userId.trim()));
        Long processInstanceId = cffProjectionSubmitBean.getProcessId();
        TaskSummary taskSummary = workflowLogicService.startAndCompleteTask(userModel, projectionId,
                processInstanceId, GtnWsBpmCommonConstants.CFF);
        processInstanceId = taskSummary.getProcessInstanceId();
        LOGGER.info("Process Instance ID========= taskSummary.getProcessInstanceId()" + processInstanceId);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setProcessInstanceId(processInstanceId.intValue());
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
    
    @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_GET_VARIABLE)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse getVariable(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsCFFSubmitRequest gtnCffSubmit = gtnUIFrameworkWebserviceRequest
                .getGtnCffsubmitRequest();
        GtnWsCFFSubmitBean gtnCffSubmitBean = gtnCffSubmit
                .getGtnWsCFFSubmitBean();
        String processVariable = workflowLogicService.getProcessVariable(gtnCffSubmitBean.getProcessId(), gtnCffSubmitBean.getVariableName(), GtnWsBpmCommonConstants.CFF);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setProcessVariable(processVariable);
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
    
    @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_SUBMIT_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse submitWorkflow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsCFFSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnCffsubmitRequest();
        GtnWsCFFSubmitBean cffWSSubmitBean = forecastProjectionSubmitRequest
                .getGtnWsCFFSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionSubmitRequest.getGtnWsGeneralRequest();
        Map<String, Object> params = new HashMap<>();
        WorkflowRuleDTO dto = new WorkflowRuleDTO();
        params.put("out_workflowDTO", dto);
        params.put("projectionId", cffWSSubmitBean.getProjectionId());
        workflowLogicService.updateTaskInBpm(gtnWsGeneralRequest.getUserId(), cffWSSubmitBean.getProcessId(), params,
                GtnWsBpmCommonConstants.CFF);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
    
    @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_APPROVE_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse approveWorkFlow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsCFFSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnCffsubmitRequest();
        GtnWsCFFSubmitBean forecastProjectionSubmitBean = forecastProjectionSubmitRequest
                .getGtnWsCFFSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionSubmitRequest.getGtnWsGeneralRequest();
        Map<String, Object> params = new HashMap<>();
				params.put("approveFlag", "approve");
				workflowLogicService.updateTaskInBpm(gtnWsGeneralRequest.getUserId(), forecastProjectionSubmitBean.getProcessId(), params,
						GtnWsBpmCommonConstants.CFF);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
    
     @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_REJECT_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse rejectWorkflow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsCFFSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnCffsubmitRequest();
        GtnWsCFFSubmitBean forecastProjectionSubmitBean = forecastProjectionSubmitRequest
                .getGtnWsCFFSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionSubmitRequest.getGtnWsGeneralRequest();
        Map<String, Object> params = new HashMap<>();
				params.put("approveFlag", "reject-RWC");
				workflowLogicService.updateTaskInBpm(gtnWsGeneralRequest.getUserId(), forecastProjectionSubmitBean.getProcessId(), params,
						GtnWsBpmCommonConstants.CFF);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
     @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_WITHDRAW_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse withDrawWorkflow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsCFFSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnCffsubmitRequest();
        GtnWsCFFSubmitBean forecastProjectionSubmitBean = forecastProjectionSubmitRequest
                .getGtnWsCFFSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionSubmitRequest.getGtnWsGeneralRequest();
        Map<String, Object> params = new HashMap<>();
				params.put("approveFlag", "withdraw-RWC");
				workflowLogicService.updateTaskInBpm(gtnWsGeneralRequest.getUserId(), forecastProjectionSubmitBean.getProcessId(), params,
						GtnWsBpmCommonConstants.CFF);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
    
      @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_CANCEL_WORKFLOW)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse cancelWorkflow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsCFFSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnCffsubmitRequest();
        GtnWsCFFSubmitBean forecastProjectionSubmitBean = forecastProjectionSubmitRequest
                .getGtnWsCFFSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionSubmitRequest.getGtnWsGeneralRequest();
        Map<String, Object> params = new HashMap<>();
				params.put("approveFlag", "cancel-RWC");
				workflowLogicService.updateTaskInBpm(gtnWsGeneralRequest.getUserId(), forecastProjectionSubmitBean.getProcessId(), params,
						GtnWsBpmCommonConstants.CFF);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
    
    @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_CFF_IS_VALID_USER)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse isValidWorkFlowUser(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsCFFSubmitRequest userRequest = gtnUIFrameworkWebserviceRequest
                .getGtnCffsubmitRequest();
        GtnWsGeneralRequest gtnWsGeneralUserRequest = userRequest.getGtnWsGeneralRequest();
        String userId = gtnWsGeneralUserRequest.getUserId();
        Long processInstanceId = userRequest.getProcessId();
        User userModel = gtnWsUserRoleService.getUser(Long.parseLong(userId.trim()));
        List<String> roleList = new ArrayList<>();
        boolean workflowFlag = workflowLogicService.isValidWorkflowUser(userModel, roleList,
                processInstanceId, GtnWsBpmCommonConstants.CFF);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setHasPermission(workflowFlag);
        wfResponse.setProcessInstanceId(processInstanceId.intValue());
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
   
}
