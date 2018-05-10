package com.stpl.gtn.gtn2o.ws.report.controller;

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
import com.stpl.gtn.gtn2o.ws.report.service.GtnUIFrameWorkReportResponseBuilder;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportCustomViewService;

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

	@RequestMapping(value = GtnWsReportEndPointUrlConstants.SAVE_CUSTOM_TREE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveCustomTree(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().withCustomViewBean()
				.build();

		service.saveCustomViewTree(gtnWsRequestF);

		return response;
	}

	@RequestMapping(value = GtnWsReportEndPointUrlConstants.LOAD_CUSTOM_VIEW, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomView(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().withCustomViewBean()
				.build();
		service.loadCustomView(gtnWsRequestF);
		return response;
	}

}
