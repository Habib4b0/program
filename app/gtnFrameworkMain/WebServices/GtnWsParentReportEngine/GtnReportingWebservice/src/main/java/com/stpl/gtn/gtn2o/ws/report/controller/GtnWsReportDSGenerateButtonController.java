package com.stpl.gtn.gtn2o.ws.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.report.service.GtnUIFrameWorkReportResponseBuilder;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDSGenerateButtonService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GtnWsReportDSGenerateButtonController {

	@Autowired
	GtnWsReportDSGenerateButtonService generateButtonService;

	@RequestMapping(value = "/gtnWsReportCCPGeneration", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse generateCCPForReprt(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().build();
		generateButtonService.generateCCPForReporting(gtnWsRequest);
		return response;
	}

}
