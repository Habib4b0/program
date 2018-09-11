package com.stpl.gtn.gtn2o.ws.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.service.GtnUIFrameWorkReportResponseBuilder;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDataSelectionGenerate;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportResponse;

@RestController
public class GtnWsReportDSGenerateButtonController {

	public GtnWsReportDSGenerateButtonController() {
		super();
	}

	@Autowired
	@Qualifier("reportDataSelectionSql")
	private GtnWsReportDataSelectionGenerate generateButtonService;

	@RequestMapping(value = GtnWsReportConstants.GTN_WS_DATA_SELECTION_GENERATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse generateCCPForReport(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().build();
		GtnWsReportResponse gtnWsReportRespose = new GtnWsReportResponse();
		generateButtonService.dataSelectionGenerateLogic(gtnWsRequest);
		gtnWsReportRespose.setReportBean(gtnWsRequest.getGtnWsReportRequest().getReportBean());
		response.setGtnWsReportResponse(gtnWsReportRespose);
		return response;
	}

	@PostMapping(value = GtnWsReportConstants.GTN_WS_REPORT_DASHBOARD_CUSTOM_VIEW_AND_DATA_REGENERATION_SERVICE)
	public GtnUIFrameworkWebserviceResponse repopulateCustomViewAndDataTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse repopulateResponse = new GtnUIFrameWorkReportResponseBuilder().build();
		generateButtonService.regenerateTreeAndData(gtnWsRequest);
		return repopulateResponse;
	}

	@PostMapping(value = GtnWsReportConstants.GTN_WS_DATA_SELECTION_REGENERATE_SERVICE)
	public GtnUIFrameworkWebserviceResponse repopulateCCPForReport(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse repopulateCCPResponse = new GtnUIFrameWorkReportResponseBuilder().build();
		generateButtonService.dataSelectionRegenerateLogic(gtnWsRequest);
		repopulateCCPResponse.setResponseStatus("Success");
		return repopulateCCPResponse;
	}

}
