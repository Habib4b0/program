package com.stpl.gtn.gtn2o.ws.report.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDashboardFilterOptionService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_FILTER_SERVICE)
public class GtnWsReportDashboardFilterOptionController {

	@Autowired
	private GtnWsReportDashboardFilterOptionService reportFilterOptionService;

	@RequestMapping(value = GtnWsReportConstants.GTN_WS_REPORT_CUST_PRODLEVEL_LOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustProdLevelValues(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object[]> resultList = reportFilterOptionService
				.getCustAndProdLevelValues(gtnUIFrameworkWebserviceRequest);
		List<String> itemCodeList = new ArrayList<>();
		List<String> itemValueList = new ArrayList<>();
		for (Object[] object : resultList) {
			itemCodeList.add(String.valueOf(object[0]));
			itemValueList.add(String.valueOf(object[1]));
		}
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		comboBoxResponse.setItemCodeList(itemCodeList);
		comboBoxResponse.setItemValueList(itemValueList);
		response.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_WS_REPORT_FILTER_LOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustomerFilter(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object[]> resultList = reportFilterOptionService.loadCustomerLevelFilter(gtnUIFrameworkWebserviceRequest);
		List<String> itemCodeList = new ArrayList<>();
		List<String> itemValueList = new ArrayList<>();
		for (Object[] object : resultList) {
			itemCodeList.add(String.valueOf(object[1]));
			itemValueList.add(String.valueOf(object[0]));
		}
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		comboBoxResponse.setItemCodeList(itemCodeList);
		comboBoxResponse.setItemValueList(itemValueList);
		response.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_WS_REPORT_DEDUCTION_FILTER_LOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadDeductionFilter(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object[]> resultList = reportFilterOptionService.loadDeductionFilter(gtnUIFrameworkWebserviceRequest);
		return null;
	}
}
