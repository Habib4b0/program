package com.stpl.gtn.gtn2o.ws.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastProjectionSubmitRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastProjectionSubmitResponse;
import com.stpl.gtn.gtn2o.ws.service.workflow.WorkflowLogicService;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsForecastProjectionSubmitBean;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowMasterBean;

/**
 *
 * @author STPL
 */
@Controller
@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_APPROVE_CONTROLER_URI)
public class GtnWsWorkFlowApprovalController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsWorkFlowApprovalController.class);
	@Autowired
	private WorkflowLogicService workflowLogicService;

	public GtnWsWorkFlowApprovalController() {
		super();
	}

	public GtnWsWorkFlowApprovalController(WorkflowLogicService workflowLogicService) {
		super();
		this.workflowLogicService = workflowLogicService;

	}

	@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_APPROVE_PROJECTION_URI)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse approveProjection(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse approveProjectionResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnWsForecastProjectionSubmitResponse approveResponse = new GtnWsForecastProjectionSubmitResponse();
		GtnWsForecastProjectionSubmitRequest forecastProjectionApproveRequest = gtnUIFrameworkWebserviceRequest
				.getGtnWsForecastProjectionSubmitRequest();
		GtnWsForecastProjectionSubmitBean forecastProjectionApproveBean = forecastProjectionApproveRequest
				.getGtnWsForecastProjectionSubmitBean();
		GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionApproveRequest.getGtnWsGeneralRequest();
		try {
			int projectionId = forecastProjectionApproveBean.getProjectionId();
			String userId = gtnWsGeneralRequest.getUserId();
			int userIdInt = Integer.parseInt(userId);
			int workflowId = Integer.parseInt(forecastProjectionApproveBean.getWorkflowId());
			GtnWsWorkflowMasterBean wfMasterDto = workflowLogicService.setWorkflowMasterBean(projectionId, workflowId,
					userIdInt, GtnWsBpmCommonConstants.APPROVED_STATUS, GtnWsBpmCommonConstants.EMPTY,
					forecastProjectionApproveBean.getApprovalLevel());
			String workflowIdUpdate = workflowLogicService.updateWorkflow(wfMasterDto);
			if (forecastProjectionApproveBean.getNoOfApproval() > forecastProjectionApproveBean.getApprovalLevel()) {
				wfMasterDto = workflowLogicService.setWorkflowMasterBean(projectionId, workflowId, userIdInt,
						GtnWsBpmCommonConstants.PENDING_STATUS, GtnWsBpmCommonConstants.EMPTY,
						forecastProjectionApproveBean.getApprovalLevel() + 1);
				workflowIdUpdate = workflowLogicService.updateWorkflow(wfMasterDto);
			}
			if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(GtnWsBpmCommonConstants.NOT_SAVED)) {
				Map<String, Object> params = new HashMap<>();
				params.put("approveFlag", "approve");
				workflowLogicService.updateTaskInBpm(userId, forecastProjectionApproveBean.getProcessId(), params,
						GtnWsBpmCommonConstants.FORECAST_RETURNS);
			}
			forecastProjectionApproveBean.setWorkflowId(workflowIdUpdate);
			approveResponse.setGtnWsForecastProjectionSubmitBean(forecastProjectionApproveBean);
			generalResponse.setSucess(true);
		} catch (Exception ex) {
			generalResponse.setSucess(false);
			LOGGER.error("Exception in approveProjection().", ex);
		}
		return approveProjectionResponse;
	}

}
