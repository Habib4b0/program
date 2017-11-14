package com.stpl.gtn.gtn2o.ws.module.processmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processmonitor.service.GtnWsProcessMonitorService;
import com.stpl.gtn.gtn2o.ws.processmonitor.bean.GtnWsProcessMonitorBean;
import com.stpl.gtn.gtn2o.ws.processmonitor.constants.GtnWsProcessMonitorConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.processmonitor.GtnWsProcessMonitorResponse;

@RestController
@RequestMapping(value = GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_SERVICE_SCREEN)
public class GtnWsProcessMonitorSaveController {
	public GtnWsProcessMonitorSaveController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessMonitorSaveController.class);

	@Autowired
	private GtnWsProcessMonitorService pcSaveWebservice;

	@PostMapping(value = GtnWsProcessMonitorConstants.GTN_WS_PROCESS_MONITOR_SAVE_SERVICE)
	public GtnUIFrameworkWebserviceResponse saveProcessMonitor(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter saveProcessMonitor");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		try {
			pcSaveWebservice.saveUpdateProcessMonitor(gtnWsRequest);
			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting saveProcessMonitor", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit saveProcessMonitor");
		}
	}

	@PostMapping(value = GtnWsProcessMonitorConstants.GTN_WS_PROCESS_MONITOR_DELETE_SERVICE)
	public GtnUIFrameworkWebserviceResponse deleteProcessMonitor(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter delete ProcessMonitor");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		try {
			pcSaveWebservice.deleteProcessMonitor(gtnWsRequest);
			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting delete ProcessMonitor qury", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit delete ProcessMonitor");
		}
	}

	@PostMapping(value = GtnWsProcessMonitorConstants.GTN_WS_PROCESS_MONITOR_DUPLICATE_PROCESS_NAME_SERVICE)
	public GtnUIFrameworkWebserviceResponse duplicateProcessName(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter duplicateProcessName");
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsProcessMonitorResponse gtnWsProcessMonitorResponse = new GtnWsProcessMonitorResponse();
		GtnWsProcessMonitorBean monitorBean = new GtnWsProcessMonitorBean();
		try {
			int count = pcSaveWebservice.duplicateProcessName(gtnWsRequest);
			monitorBean.setErrorMessage(count > 0);
			gtnWsProcessMonitorResponse.setMonitorBean(monitorBean);
			response.setGtnWsProcessMonitorResponse(gtnWsProcessMonitorResponse);
			return response;
		} catch (Exception e) {
			response.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting duplicateProcessName", e);
			response.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return response;
		} finally {
			logger.info("Exit duplicateProcessName");
		}
	}
}
