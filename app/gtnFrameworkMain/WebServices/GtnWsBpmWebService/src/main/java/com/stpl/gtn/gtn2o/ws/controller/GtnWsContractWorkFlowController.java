package com.stpl.gtn.gtn2o.ws.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.entity.user.User;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.request.workflow.GtnWsCommonWorkflowRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.service.userrole.GtnWsUserRoleService;
import com.stpl.gtn.gtn2o.ws.service.workflow.WorkflowLogicService;

/**
 *
 * @author STPL
 */
@Controller
@RequestMapping(value = GtnWsContractDashboardContants.GTN_WS_CONTRACT_WORKFLOW_SERVICE)
public class GtnWsContractWorkFlowController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsContractWorkFlowController.class);
	@Autowired
	private WorkflowLogicService workflowLogicService;
	@Autowired
	private GtnWsUserRoleService gtnWsUserRoleService;

	public GtnWsContractWorkFlowController() {
		super();
	}

	public GtnWsContractWorkFlowController(WorkflowLogicService workflowLogicService,
			GtnWsUserRoleService gtnWsUserRoleService) {
		super();
		this.workflowLogicService = workflowLogicService;
		this.gtnWsUserRoleService = gtnWsUserRoleService;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.SUBMIT_CONTRACT)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse submitContractAndStartWorkFlow(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		String workflowId = "";
		GtnWsCommonWorkflowRequest workflowRequest = gtnUIFrameworkWebserviceRequest.getGtnWSCommonWorkflowRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		try {
			LOGGER.info("workflowRequest.getPersistanceId() " + workflowRequest.getPersistanceId());
			workflowId = workflowLogicService.submitContract(workflowRequest,gtnUIFrameworkWebserviceRequest);
			User userModel = gtnWsUserRoleService.getUser(Long.parseLong(gtnWsGeneralRequest.getUserId().trim()));
			TaskSummary taskSummary = workflowLogicService.startAndCompleteContractTask(userModel,
					workflowRequest.getContractBean().getContractId(), workflowRequest.getPersistanceId(),
					workflowRequest.getContractBean().getContractStructure(), GtnWsBpmCommonConstants.CONTRACT_MASTER);
			long processInstanceId = taskSummary.getProcessInstanceId();
			LOGGER.info("Process Instance ID========= taskSummary.getProcessInstanceId()" + processInstanceId);
			workflowLogicService.updateTaskInBpm(gtnWsGeneralRequest.getUserId(), processInstanceId, null,
					GtnWsBpmCommonConstants.CONTRACT_MASTER);
			GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
			wfResponse.setWorkflowId(workflowId);
			gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
		} catch (Exception e) {
			LOGGER.error("Exception in submitContractAndStartWorkFlow().", e);
		}
		return gtnWsresponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.CHECK_VALID_USER)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse checkValidUser(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsCommonWorkflowRequest workflowRequest = gtnUIFrameworkWebserviceRequest.getGtnWSCommonWorkflowRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		try {
			ProcessInstance processInstance = workflowLogicService.startWorkflow(workflowRequest.getModuleKey(),
					workflowRequest.getDefaultValue(), GtnWsBpmCommonConstants.CONTRACT_MASTER);
			Long processInstanceId = processInstance.getId();
			User userModel = gtnWsUserRoleService.getUser(Long.parseLong(gtnWsGeneralRequest.getUserId().trim()));
			List<String> roleList = new ArrayList<>();
			LOGGER.info("Process Instance ID=========" + processInstanceId);
			boolean workflowFlag = workflowLogicService.isValidWorkflowUser(userModel, roleList,
					processInstance.getId(), GtnWsBpmCommonConstants.CONTRACT_MASTER);
			GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
			wfResponse.setHasPermission(workflowFlag);
			wfResponse.setWorkflowId(workflowFlag ? "" : GtnWsBpmCommonConstants.PERMISSION_DENIED);
			wfResponse.setPersistanceId(processInstanceId);
			gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
		} catch (Exception e) {
			LOGGER.error("Exception in checkValidUser().", e);
		}
		return gtnWsresponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.APPROVE_CONTRACT)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse approveContract(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		return updateContractWorkflow(gtnUIFrameworkWebserviceRequest, "approve");
	}

	private GtnUIFrameworkWebserviceResponse updateContractWorkflow(
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest, String approveFlag) {
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		String workflowId = "";
		GtnWsCommonWorkflowRequest workflowRequest = gtnUIFrameworkWebserviceRequest.getGtnWSCommonWorkflowRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		try {
			workflowId = workflowLogicService.updateContract(workflowRequest);
			if (!GtnFrameworkWebserviceConstant.NOT_SAVED.equals(workflowId)) {
				Map<String, Object> params = new HashMap<>();
				params.put(GtnFrameworkWebserviceConstant.APPROVE_FLAG, approveFlag);
				workflowLogicService.updateTaskInBpm(gtnWsGeneralRequest.getUserId(),
						workflowRequest.getPersistanceId(), params, GtnWsBpmCommonConstants.CONTRACT_MASTER);
			}

			GtnWsCommonWorkflowResponse wfResponse = new GtnWsCommonWorkflowResponse();
			wfResponse.setWorkflowId(workflowId);
			gtnWsresponse.setGtnWSCommonWorkflowResponse(wfResponse);
		} catch (Exception e) {
			LOGGER.error("Exception in approveContract().", e);
		}
		return gtnWsresponse;
	}

	@RequestMapping(value = GtnWsContractDashboardContants.CANCEL_CONTRACT)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse cancelContract(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		return updateContractWorkflow(gtnUIFrameworkWebserviceRequest, "Cancel-RC");
	}

	@RequestMapping(value = GtnWsContractDashboardContants.REJECT_CONTRACT)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse rejectContract(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		return updateContractWorkflow(gtnUIFrameworkWebserviceRequest, "Reject-RC");
	}

	@RequestMapping(value = GtnWsContractDashboardContants.WITHDRAW_CONTRACT)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse withdrawContract(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		return updateContractWorkflow(gtnUIFrameworkWebserviceRequest, "Withdraw-RC");
	}

	@RequestMapping(value = GtnWsContractDashboardContants.GET_WORKFLOW_INFO)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse getWorkFlowInfo(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enter getWorkFlowInfo");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			cdResponse.setWorkflowBean(workflowLogicService.getGtnWsContractWorkflowBean(cdRequest));
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}

		LOGGER.info("Exit getWorkFlowInfo");
		return gtnResponse;
	}
}
