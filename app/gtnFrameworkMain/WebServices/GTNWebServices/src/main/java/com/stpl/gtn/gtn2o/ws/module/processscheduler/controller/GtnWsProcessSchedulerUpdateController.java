/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.GtnWsProcessSchedulerUpdateService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Deepak.kumar
 */
@RestController
@RequestMapping(value = GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN)
public class GtnWsProcessSchedulerUpdateController {
	public GtnWsProcessSchedulerUpdateController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessSchedulerUpdateController.class);

	@Autowired
	private GtnWsProcessSchedulerUpdateService pcSaveWebservice;
	@Autowired
	private GtnWsProcessSchedularServiceUtil gtnWsPSServiceUtil;

	@PostMapping(value = GtnWsProcessScedulerConstants.GTN_WS_PROCESS_SCHEDULER_RUN_SERVICE_DATA)
	public GtnUIFrameworkWebserviceResponse runProcessScheduler(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		logger.info("Enter runProcessScheduler");
		String scriptName=gtnWsRequest.getProcessSchedulerRequest().getProcessSchedulerBean().getPsSchemaName();
		Integer processSid = gtnWsRequest.getProcessSchedulerRequest().getProcessSchedulerBean().getProcessSchedulerSid();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object> inputList = new ArrayList<>();
		inputList.add(gtnWsPSServiceUtil.getSysSchemaCatalog());
		try {
			pcSaveWebservice.runProcessScheduler(scriptName,processSid,inputList);
			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting runProcessScheduler", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit runProcessScheduler");
		}
	}
}
