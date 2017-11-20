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
@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_CANCEL_CONTROLER_URI)
public class GtnWsWorkFlowCancelController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsWorkFlowCancelController.class);
	@Autowired
	private WorkflowLogicService workflowLogicService;
	
	
	public GtnWsWorkFlowCancelController() {
		super();
	}
	
	

	public GtnWsWorkFlowCancelController(WorkflowLogicService workflowLogicService) {
		super();
		this.workflowLogicService = workflowLogicService;
		
	}



	@RequestMapping(value = GtnWsForecastReturnsConstants.GTN_WS_CANCEL_PROJECTION_URI)
	@ResponseBody
	public GtnUIFrameworkWebserviceResponse cancelProjection(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnWsForecastProjectionSubmitResponse cancelResponse = new GtnWsForecastProjectionSubmitResponse();
		GtnWsForecastProjectionSubmitRequest forecastProjectionCancelRequest = gtnUIFrameworkWebserviceRequest
				.getGtnWsForecastProjectionSubmitRequest();
		GtnWsForecastProjectionSubmitBean forecastProjectionCancelBean = forecastProjectionCancelRequest
				.getGtnWsForecastProjectionSubmitBean();
		GtnWsGeneralRequest gtnWsGeneralRequest = forecastProjectionCancelRequest.getGtnWsGeneralRequest();
		try {
			int projectionId = forecastProjectionCancelBean.getProjectionId();
			String userId = gtnWsGeneralRequest.getUserId();
			int userIdInt = Integer.parseInt(userId);
			int workflowId = Integer.parseInt(forecastProjectionCancelBean.getWorkflowId());

			GtnWsWorkflowMasterBean wfMasterDto = workflowLogicService.setWorkflowMasterBean(projectionId, workflowId,
					userIdInt, GtnWsBpmCommonConstants.CANCELLED_STATUS, GtnWsBpmCommonConstants.EMPTY,
					forecastProjectionCancelBean.getApprovalLevel());
			String workflowIdUpdate = workflowLogicService.updateWorkflow(wfMasterDto);
			if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals((GtnWsBpmCommonConstants.NOT_SAVED))) {

				Map<String, Object> params = new HashMap<>();
				params.put("approveFlag", "cancel-RWC");
				workflowLogicService.updateTaskInBpm(userId, forecastProjectionCancelBean.getProcessId(), params,
						GtnWsBpmCommonConstants.FORECAST_RETURNS);
			}
			forecastProjectionCancelBean.setWorkflowId(workflowIdUpdate);
			cancelResponse.setGtnWsForecastProjectionSubmitBean(forecastProjectionCancelBean);
			generalResponse.setSucess(true);
		} catch (Exception e) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(e);
			LOGGER.error("Exception in cancelProjection().", e);
		}
		return gtnWsresponse;
	}

}
