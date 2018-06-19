package com.stpl.gtn.gtn2o.ws.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportEndPointUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.service.GtnUIFrameWorkReportResponseBuilder;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportCustomViewService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

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
			response.getGtnWsReportResponse().getReportBean().getCustomViewBean().setGridData(dataTable);
		} catch (GtnFrameworkGeneralException e) {

		}
		return response;
	}

	@RequestMapping(value = GtnWsReportEndPointUrlConstants.LOAD_DEDUCTION_HIERARCHY, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadDeductionHierarchyLevels(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().withCustomViewBean()
				.build();
		try {
			GtnUIFrameworkDataTable dataTable = service.loadDeductionHierarchy(gtnWsRequestF);
			response.getGtnWsReportResponse().getReportBean().getCustomViewBean().setGridData(dataTable);
		} catch (GtnFrameworkGeneralException e) {

		}
		return response;
	}

	@RequestMapping(value = GtnWsReportEndPointUrlConstants.SAVE_CUSTOM_TREE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveCustomTree(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().withCustomViewBean()
				.build();
		// service.saveCustomViewTree(gtnWsRequestF);
		return response;
	}

	@RequestMapping(value = GtnWsReportEndPointUrlConstants.LOAD_CUSTOM_VIEW, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomViewString(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().withCustomViewBean()
				.build();
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		// List<String> customViewList = service.loadCustomViewString();
		// comboBoxResponse.setItemValueList(customViewList);
		// comboBoxResponse.setItemCodeList(customViewList);
		response.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportEndPointUrlConstants.LOAD_CUSTOM_VIEW_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomView(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequestF) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameWorkReportResponseBuilder().withCustomViewBean()
				.build();
		// GtnWsReportCustomViewDataBean viewBean =
		// service.loadCustomView(gtnWsRequestF);
		// response.getGtnWsReportResponse().getReportBean().getCustomViewBean().setCustomViewDataBean(viewBean);
		return response;
	}

}
