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

import com.stpl.app.bpm.dto.ForecastingRulesDTO;
import com.stpl.app.bpm.dto.WorkflowRuleDTO;
import com.stpl.gtn.gtn2o.ws.bpm.service.BpmProcessBean;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.entity.user.User;
import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastProjectionSubmitResponse;
import com.stpl.gtn.gtn2o.ws.service.userrole.GtnWsUserRoleService;
import com.stpl.gtn.gtn2o.ws.service.workflow.WorkflowLogicService;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowMasterBean;

/**
 *
 * @author STPL
 */
@Controller
@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_SUBMIT_CONTROLER_URI)
public class GtnWsWorkFlowSubmissionController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsWorkFlowSubmissionController.class);
	@Autowired
	private WorkflowLogicService workflowLogicService;
	@Autowired
	private GtnWsUserRoleService gtnWsUserRoleService;
	@Autowired
	private BpmProcessBean bpmProcessBean;

	public GtnWsWorkFlowSubmissionController() {
		super();
	}

	public GtnWsWorkFlowSubmissionController(WorkflowLogicService workflowLogicService,
			GtnWsUserRoleService gtnWsUserRoleService, BpmProcessBean bpmProcessBean) {
		super();
		this.workflowLogicService = workflowLogicService;
		this.gtnWsUserRoleService = gtnWsUserRoleService;
		this.bpmProcessBean = bpmProcessBean;
	}

	@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_SUBMIT_PROJECTION_URI)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse submitProjectionAndStartWorkFlow(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnWsForecastProjectionSubmitResponse submitResponse = new GtnWsForecastProjectionSubmitResponse();
		String workflowId = "";
		GtnWsForecastProjectionSubmitRequest forecastProjectionSubmitRequest = gtnUIFrameworkWebserviceRequest
				.getGtnWsForecastProjectionSubmitRequest();
		GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean = forecastProjectionSubmitRequest
				.getGtnWsForecastProjectionSubmitBean();
		GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionSubmitRequest.getGtnWsGeneralRequest();
		Map<String, Object> params = new HashMap<>();
		int projectionId = forecastProjectionSubmitBean.getProjectionId();
		String moduleName = forecastProjectionSubmitBean.getModuleName();
		String userId = gtnWsGeneralRequest.getUserId();
		params.put("projectionId", projectionId);
		try {
			String workflowStatus = workflowLogicService.getWorkflowStatus(projectionId, moduleName);
			if (!workflowStatus.equals("R") && !workflowStatus.equals("W")) {
				ProcessInstance processInstance = workflowLogicService.startWorkflow("Forecasting_WorkflowId",
						"ForecastingWorkflow.SubmissionWorkflow", GtnWsBpmCommonConstants.FORECAST_RETURNS);
				Long processInstanceId = processInstance.getId();
				User userModel = gtnWsUserRoleService.getUser(Long.parseLong(userId.trim()));
				List<String> roleList = new ArrayList<>();
				LOGGER.info("Process Instance ID=========" + processInstanceId);
				boolean workflowFlag = workflowLogicService.isValidWorkflowUser(userModel, roleList,
						processInstance.getId(), GtnWsBpmCommonConstants.FORECAST_RETURNS);
				TaskSummary taskSummary = workflowLogicService.startAndCompleteTask(userModel, projectionId,
						processInstanceId, GtnWsBpmCommonConstants.FORECAST_RETURNS);
				processInstanceId = taskSummary.getProcessInstanceId();
				LOGGER.info("Process Instance ID========= taskSummary.getProcessInstanceId()" + processInstanceId);
				if (workflowFlag) {
					workflowId = submitProjToWorkflow(params, forecastProjectionSubmitBean, gtnWsGeneralRequest);
				} else {
					workflowId = GtnWsBpmCommonConstants.PERMISSION_DENIED;
				}
			} else {
				workflowId = submitProjToWorkflow(params, forecastProjectionSubmitBean, gtnWsGeneralRequest);
			}
			forecastProjectionSubmitBean.setWorkflowId(workflowId);
			submitResponse.setGtnWsForecastProjectionSubmitBean(forecastProjectionSubmitBean);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(e);
			LOGGER.error("Exception in submitProjectionAndStartWorkFlow().", e);
		}
		gtnWsresponse.setGtnWsGeneralResponse(generalResponse);
		gtnWsresponse.setGtnWsForecastProjectionSubmitResponse(submitResponse);
		return gtnWsresponse;
	}

	@SuppressWarnings("rawtypes")
	private String submitProjToWorkflow(Map<String, Object> params,
			GtnWsForecastProjectionSubmitBean forecastProjectionSubmitBean, GtnWsGeneralRequest gtnWsGeneralRequest)
			throws GtnFrameworkGeneralException {

		String workflowId = "";
		List<ForecastingRulesDTO> list = workflowLogicService.getProjectionValues(
				forecastProjectionSubmitBean.getProjectionId(), forecastProjectionSubmitBean.getModuleName(),null,null);
		try {
			for (ForecastingRulesDTO forecastingRulesDTO : list) {
				params.put("out_" + forecastingRulesDTO.getVariableName(), forecastingRulesDTO);
			}
			WorkflowRuleDTO dto = new WorkflowRuleDTO();
			params.put("out_workflowDTO", dto);

			Long processId = 0L;
			List processList = workflowLogicService
					.selectWFInstanceInfo(forecastProjectionSubmitBean.getProjectionId());
			if (processList != null && !(processList.isEmpty())) {
				processId = Long.valueOf(processList.get(0).toString());
			}

			workflowLogicService.updateTaskInBpm(gtnWsGeneralRequest.getUserId(), processId, params,
					GtnWsBpmCommonConstants.FORECAST_RETURNS);
			String autoApproval = bpmProcessBean.getProcessVariableLog(processId, "Auto_Approval",
					GtnWsBpmCommonConstants.FORECAST_RETURNS);
			String noOfUsers = bpmProcessBean.getProcessVariableLog(processId, "NoOfUsers",
					GtnWsBpmCommonConstants.FORECAST_RETURNS);
			if (!autoApproval.isEmpty() && !noOfUsers.isEmpty()) {

				LOGGER.info("autoApproval  : " + autoApproval);
				LOGGER.info("no of users : " + noOfUsers);
				workflowId = workflowLogicService.submitProjection(forecastProjectionSubmitBean, gtnWsGeneralRequest,
						noOfUsers);
				String approvedFlag = "";
				if (GtnWsBpmCommonConstants.TRUE.equals(autoApproval)) {
					WorkflowMaster wm = workflowLogicService
							.getWorkflowMasterByProjectionId(forecastProjectionSubmitBean.getProjectionId());
					GtnWsWorkflowMasterBean wfMasterBean = workflowLogicService.setWorkflowMasterBean(
							forecastProjectionSubmitBean.getProjectionId(), wm.getWorkflowMasterSid(),
							Integer.valueOf(gtnWsGeneralRequest.getUserId()), GtnWsBpmCommonConstants.APPROVED_STATUS,
							"", 2);
					workflowId = workflowLogicService.updateWorkflow(wfMasterBean);
					approvedFlag = GtnWsBpmCommonConstants.SUBMITTED_AND_APPROVED;
				} else {
					approvedFlag = GtnWsBpmCommonConstants.SUBMITTED;
				}
				LOGGER.info("approvedFlag  : " + approvedFlag);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in submitProjToWorkflow().", ex);
			throw new GtnFrameworkGeneralException(ex);
		}
		return workflowId;
	}
}
