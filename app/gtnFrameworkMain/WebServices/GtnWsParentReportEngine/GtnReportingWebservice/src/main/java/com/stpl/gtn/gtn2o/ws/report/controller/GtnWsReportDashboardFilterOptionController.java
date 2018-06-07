package com.stpl.gtn.gtn2o.ws.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDashboardFilterOptionService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_FILTER_SERVICE)
public class GtnWsReportDashboardFilterOptionController {

	@Autowired
	private GtnWsReportDashboardFilterOptionService reportFilterOptionService;

	@RequestMapping(value = GtnWsReportConstants.GTN_WS_REPORT_FILTER_LOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomerFilter(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		reportFilterOptionService.loadCustomerLevelFilter(gtnUIFrameworkWebserviceRequest);
		return null;
	}
}
