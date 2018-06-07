package com.stpl.gtn.gtn2o.ws.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDashboardFilterOptionService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnWsReportDashboardFilterOptionController {

	@Autowired
	private GtnWsReportDashboardFilterOptionService reportFilterOptionService;

	public GtnUIFrameworkWebserviceResponse loadCustomerFilter(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		reportFilterOptionService.loadCustomerLevelFilter(gtnUIFrameworkWebserviceRequest);
		return null;
	}
}
