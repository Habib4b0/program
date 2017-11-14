/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_REJECT_CONTROLER_URI)
public class GtnWsWorkFlowRejectController {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsWorkFlowRejectController.class);
	@Autowired
	private WorkflowLogicService workflowLogicService;

	public GtnWsWorkFlowRejectController() {
		super();
	}

	public GtnWsWorkFlowRejectController(WorkflowLogicService workflowLogicService) {
		super();
		this.workflowLogicService = workflowLogicService;

	}

	@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_REJECT_PROJECTION_URI)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse rejectProjection(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnWsForecastProjectionSubmitResponse rejectResponse = new GtnWsForecastProjectionSubmitResponse();
		GtnWsForecastProjectionSubmitRequest forecastProjectionRejectRequest = gtnUIFrameworkWebserviceRequest
				.getGtnWsForecastProjectionSubmitRequest();
		GtnWsForecastProjectionSubmitBean forecastProjectionRejectBean = forecastProjectionRejectRequest
				.getGtnWsForecastProjectionSubmitBean();
		GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionRejectRequest.getGtnWsGeneralRequest();
		try {
			int projectionId = forecastProjectionRejectBean.getProjectionId();
			String userId = gtnWsGeneralRequest.getUserId();
			int userIdInt = Integer.parseInt(userId);
			int workflowId = Integer.parseInt(forecastProjectionRejectBean.getWorkflowId());

			GtnWsWorkflowMasterBean wfMasterDto = workflowLogicService.setWorkflowMasterBean(projectionId, workflowId,
					userIdInt, GtnWsBpmCommonConstants.REJECTED_STATUS, GtnWsBpmCommonConstants.EMPTY,
					forecastProjectionRejectBean.getApprovalLevel());
			String workflowIdUpdate = workflowLogicService.updateWorkflow(wfMasterDto);
			if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals((GtnWsBpmCommonConstants.NOT_SAVED))) {

				Map<String, Object> params = new HashMap<>();
				params.put("approveFlag", "reject-RWC");
				workflowLogicService.updateTaskInBpm(userId, forecastProjectionRejectBean.getProcessId(), params,
						GtnWsBpmCommonConstants.FORECAST_RETURNS);
			}
			forecastProjectionRejectBean.setWorkflowId(workflowIdUpdate);
			rejectResponse.setGtnWsForecastProjectionSubmitBean(forecastProjectionRejectBean);
			generalResponse.setSucess(true);
		} catch (Exception e) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(e);
			LOGGER.error("Exception in rejectProjection().", e);
		}
		return gtnWsresponse;
	}

}
