/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsWorkflowIOService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

/**
 *
 * @author Nimisha.Rakesh
 */
@RestController
public class GtnWsReturnsWorkflowController {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsReturnsWorkflowController.class);

	@Autowired
	GtnWsReturnsWorkflowIOService gtnWsReturnsWorkflowIOService;

	public GtnWsReturnsWorkflowIOService getGtnWsReturnsWorkflowIOService() {
		return gtnWsReturnsWorkflowIOService;
	}

	public void setGtnWsReturnsWorkflowIOService(GtnWsReturnsWorkflowIOService gtnWsReturnsWorkflowIOService) {
		this.gtnWsReturnsWorkflowIOService = gtnWsReturnsWorkflowIOService;
	}

	@PostMapping(value = GtnWsForecastReturnsConstants.GTN_WS_PROJECTION_DETAILS_FOR_WORKFLOW_SERVICE)
	public GtnUIFrameworkWebserviceResponse getDetailsofProjectionForWorkflow(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enters getDetailsofProjectionForWorkflow");
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		gtnUIFrameworkWebserviceResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
		gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(true);
		GtnForecastBean gtnForecastBean = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean();
		try {
			gtnForecastBean = gtnWsReturnsWorkflowIOService.getProjectionDetails(gtnForecastBean);
			gtnForecastBean = gtnWsReturnsWorkflowIOService.getProjectionSelectionDetails(gtnForecastBean);
			forecastResponse.setGtnForecastBean(gtnForecastBean);
			gtnUIFrameworkWebserviceResponse.setGtnWsForecastResponse(forecastResponse);
			return gtnUIFrameworkWebserviceResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting itemAdditionSearch Query", ex);
			gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnUIFrameworkWebserviceResponse;
		} finally {
			LOGGER.info("Exit getDetailsofProjectionForWorkflow");
		}
	}
}
