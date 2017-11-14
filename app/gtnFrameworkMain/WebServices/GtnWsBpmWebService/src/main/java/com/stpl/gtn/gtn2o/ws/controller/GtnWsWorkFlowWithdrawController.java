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
@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_WITHDRAW_CONTROLER_URI)
public class GtnWsWorkFlowWithdrawController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsWorkFlowWithdrawController.class);
	@Autowired
	private WorkflowLogicService workflowLogicService;

	public GtnWsWorkFlowWithdrawController() {
		super();
	}

	public GtnWsWorkFlowWithdrawController(WorkflowLogicService workflowLogicService) {
		super();
		this.workflowLogicService = workflowLogicService;
	}

	@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_WITHDRAW_PROJECTION_URI)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse withdrawProjection(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnWsForecastProjectionSubmitResponse withdrawResponse = new GtnWsForecastProjectionSubmitResponse();
		GtnWsForecastProjectionSubmitRequest forecastProjectionWithdraRequest = gtnUIFrameworkWebserviceRequest
				.getGtnWsForecastProjectionSubmitRequest();
		GtnWsForecastProjectionSubmitBean forecastProjectionWithdraBean = forecastProjectionWithdraRequest
				.getGtnWsForecastProjectionSubmitBean();
		GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionWithdraRequest.getGtnWsGeneralRequest();
		try {
			int projectionId = forecastProjectionWithdraBean.getProjectionId();
			String userId = gtnWsGeneralRequest.getUserId();
			int userIdInt = Integer.parseInt(userId);
			int workflowId = Integer.parseInt(forecastProjectionWithdraBean.getWorkflowId());

			GtnWsWorkflowMasterBean wfMasterDto = workflowLogicService.setWorkflowMasterBean(projectionId, workflowId,
					userIdInt, GtnWsBpmCommonConstants.WITHDRAWN_STATUS, GtnWsBpmCommonConstants.EMPTY,
					forecastProjectionWithdraBean.getApprovalLevel());
			String workflowIdUpdate = workflowLogicService.updateWorkflow(wfMasterDto);
			if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals((GtnWsBpmCommonConstants.NOT_SAVED))) {

				Map<String, Object> params = new HashMap<>();
				params.put("approveFlag", "withdraw-RWC");
				workflowLogicService.updateTaskInBpm(userId, forecastProjectionWithdraBean.getProcessId(), params,
						GtnWsBpmCommonConstants.FORECAST_RETURNS);

			}
			forecastProjectionWithdraBean.setWorkflowId(workflowIdUpdate);
			withdrawResponse.setGtnWsForecastProjectionSubmitBean(forecastProjectionWithdraBean);
			generalResponse.setSucess(true);
		} catch (Exception e) {
			generalResponse.setSucess(false);
			LOGGER.error("Exception in withdrawProjection().", e);
		}
		return gtnWsresponse;
	}

}
