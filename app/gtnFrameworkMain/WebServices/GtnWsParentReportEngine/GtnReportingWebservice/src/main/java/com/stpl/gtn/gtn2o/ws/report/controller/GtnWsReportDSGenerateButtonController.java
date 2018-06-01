package com.stpl.gtn.gtn2o.ws.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.report.service.GtnUIFrameWorkReportResponseBuilder;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDataSelectionGenerate;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class GtnWsReportDSGenerateButtonController {

	@Autowired
	@Qualifier("reportDataSelectionSql")
	GtnWsReportDataSelectionGenerate generateButtonService;

	@RequestMapping(value = "/gtnWsReportCCPGeneration", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse generateCCPForReprt(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().build();
		generateButtonService.dataSelectionGenerateLogic(gtnWsRequest);
		return response;
	}

}
