package com.stpl.gtn.gtn2o.ws.module.udc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.udc.service.GtnWsUdcTableService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.udc.constants.GtnWsUdcConstants;

@RestController
@RequestMapping(value = GtnWsUdcConstants.GTN_UDC_SERVICE_SCREEN)
public class GtnWsUdcTableController {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsUdcTableController.class);
	
	@Autowired
	private GtnWsUdcTableService udcTableWebService;
	
	@RequestMapping(value = GtnWsUdcConstants.GET_UDC_TABLE_DATA)
	private GtnUIFrameworkWebserviceResponse onloadTableUdc(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest)
	{
		logger.debug("Enter OnloadTableUdc");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			gtnResponse = udcTableWebService.tableUdc(gtnWsRequest, gtnResponse);
			return gtnResponse;
		} catch (Exception e) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while Excuting OnloadTableUdc", e);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(e);
			return gtnResponse;
		} finally {
			logger.debug("Exit OnloadTableUdc");
		}
	}
	
}
