/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.controller;

import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.entity.user.User;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.service.userrole.GtnWsUserRoleService;
import com.stpl.gtn.gtn2o.ws.service.workflow.WorkflowLogicService;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.constants.GtnWsWorkFlowConstants;
import java.util.ArrayList;
import java.util.List;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Sathya.Seelan
 */
@Controller
@RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_ARM_WORKFLOW_SERVICE)
public class GtnWsARMWorkFlowController {

    private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCFFWorkFlowController.class);
    @Autowired
    private WorkflowLogicService workflowLogicService;
    @Autowired
    private GtnWsUserRoleService gtnWsUserRoleService;

    public GtnWsARMWorkFlowController() {
        super();
    }

    @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_ARM_START_TASK, method = RequestMethod.POST)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse submitProjectionAndStartWorkFlow(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest cffSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsGeneralRequest gtnWsGeneralRequest = cffSubmitRequest.getGtnWsGeneralRequest();
        String userId = gtnWsGeneralRequest.getUserId();
        ProcessInstance processInstance = workflowLogicService.startWorkflow("ARM_WorkflowId", "ARMWorkflow.ARMWorkflow", GtnWsBpmCommonConstants.ARM);
        Long processInstanceId = processInstance.getId();
        User userModel = gtnWsUserRoleService.getUser(Long.parseLong(userId.trim()));
        List<String> roleList = new ArrayList<>();
        LOGGER.info("Process Instance ID=========" + processInstanceId);
        boolean workflowFlag = workflowLogicService.isValidWorkflowUser(userModel, roleList,
                processInstance.getId(), GtnWsBpmCommonConstants.ARM);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setHasPermission(workflowFlag);
        wfResponse.setProcessInstanceId(processInstanceId.intValue());
        wfResponse.setRoleList(roleList);
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }

    @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_ARM_COMPLETE_TASK)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse forecastCompleteTask(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnArmWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse armGeneralResponse = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest armProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean = armProjectionSubmitRequest
                .getGtnWsForecastProjectionSubmitBean();
        GtnWsGeneralRequest gtnWsGeneralRequest = armProjectionSubmitRequest.getGtnWsGeneralRequest();
        int projectionId = forecastProjectionSubmitBean.getProjectionId();
        String userId = gtnWsGeneralRequest.getUserId();
        User userModel = gtnWsUserRoleService.getUser(Long.parseLong(userId.trim()));
        Long processInstanceId = forecastProjectionSubmitBean.getProcessId();
        TaskSummary taskSummary = workflowLogicService.startAndCompleteTask(userModel, projectionId,
                processInstanceId, GtnWsBpmCommonConstants.ARM);
        processInstanceId = taskSummary.getProcessInstanceId();
        LOGGER.info("Process Instance ID========= taskSummary.getProcessInstanceId()" + processInstanceId);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setProcessInstanceId(processInstanceId.intValue());
        armGeneralResponse.setSucess(true);
        gtnArmWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnArmWsresponse.setGtnWsGeneralResponse(armGeneralResponse);
        return gtnArmWsresponse;
    }
    @RequestMapping(value = GtnWsWorkFlowConstants.GTN_WS_ARM_GET_VARIABLE)
    @ResponseBody
    public GtnUIFrameworkWebserviceResponse getVariable(
            @RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
        GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
        GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
                .getGtnWsForecastProjectionSubmitRequest();
        GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean = forecastProjectionSubmitRequest
                .getGtnWsForecastProjectionSubmitBean();
        String processVariable = workflowLogicService.getProcessVariable(forecastProjectionSubmitBean.getProcessId(), forecastProjectionSubmitBean.getVariableName(), GtnWsBpmCommonConstants.ARM);
        GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
        wfResponse.setProcessVariable(processVariable);
        generalResponse.setSucess(true);
        gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
        gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
        return gtnWsresponse;
    }
}
