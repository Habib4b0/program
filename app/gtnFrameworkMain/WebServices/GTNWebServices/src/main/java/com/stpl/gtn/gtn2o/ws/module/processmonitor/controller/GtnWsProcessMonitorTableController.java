package com.stpl.gtn.gtn2o.ws.module.processmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processmonitor.service.GtnWsProcessMonitorTableService;
import com.stpl.gtn.gtn2o.ws.processmonitor.constants.GtnWsProcessMonitorConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value = GtnWsProcessMonitorConstants.GTN_PROCESS_MONITOR_SERVICE_SCREEN)
public class GtnWsProcessMonitorTableController {
	public GtnWsProcessMonitorTableController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessMonitorTableController.class);

	@Autowired
	private GtnWsProcessMonitorTableService pcTableWebservice;

	@RequestMapping(value = GtnWsProcessMonitorConstants.GET_PROCESS_MONITOR_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse onloadTableProcessMonitor(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.debug("Enter OnloadTableProcessMonitor");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse = pcTableWebservice.tableProcessMonitor(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception e) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting OnloadTableProcessMonitor", e);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return gtnResponse;
		} finally {
			logger.debug("Exit OnloadTableProcessMonitor");
		}
	}
}
