package com.stpl.gtn.gtn2o.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportEndPointUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnUIFrameWorkReportResponseBuilder;
import com.stpl.gtn.gtn2o.ws.service.GtnWsReportCustomViewService;

@RestController
public class GtnWsReportCustomViewController {
	@Autowired
	GtnWsReportCustomViewService service;

	@RequestMapping(value = GtnWsReportEndPointUrlConstants.LOAD_HIERARCHY, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadHierarchyLevels(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().withCustomViewBean()
				.build();
		try {
			GtnUIFrameworkDataTable dataTable = service.loadHierarchy(gtnWsRequestF);
			response.getGtnReportResponse().getReportBean().getCustomViewBean().setGridData(dataTable);
		} catch (GtnFrameworkGeneralException e) {

		}
		return response;
	}

}
