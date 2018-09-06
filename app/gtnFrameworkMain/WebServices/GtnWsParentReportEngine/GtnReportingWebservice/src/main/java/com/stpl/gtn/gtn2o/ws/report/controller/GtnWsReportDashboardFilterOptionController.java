package com.stpl.gtn.gtn2o.ws.report.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardFilterBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDashboardFilterOptionService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_FILTER_SERVICE)
public class GtnWsReportDashboardFilterOptionController {

	public GtnWsReportDashboardFilterOptionController() {
		super();
	}

	@Autowired
	private GtnWsReportDashboardFilterOptionService reportFilterOptionService;

	@RequestMapping(value = GtnWsReportConstants.GTN_WS_REPORT_CUST_PRODLEVEL_LOAD_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCustProdLevelValues(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		List<Object[]> resultList;
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		if (gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getGtnWsReportDashboardBean().getHierarchyType()
				.equals(GtnWsHierarchyType.DEDUCTION)) {
			resultList = reportFilterOptionService.getDeductionLevelValues(gtnUIFrameworkWebserviceRequest);
		} else {
			resultList = reportFilterOptionService.getCustAndProdLevelValues(gtnUIFrameworkWebserviceRequest);
		}
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
		List<Object[]> resultList;
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportDashboardFilterBean filterBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
				.getFilterBean();
		if (filterBean.getHierarchyType().equals("D") && filterBean.getDeductionLevelNo() != 0) {
			resultList = reportFilterOptionService.loadDeductionFilter(gtnUIFrameworkWebserviceRequest);
		} else if (filterBean.getHierarchyType().equals("D") && filterBean.getDeductionLevelNo() == 0) {
			resultList = Collections.emptyList();
		} else {
			resultList = reportFilterOptionService.loadCustomerLevelFilter(gtnUIFrameworkWebserviceRequest);
		}
		List<String> itemCodeList = new ArrayList<>();
		List<String> itemValueList = new ArrayList<>();
		if (!resultList.isEmpty()) {
			for (Object[] object : resultList) {
				itemCodeList.add(String.valueOf(object[1]));
				itemValueList.add(String.valueOf(object[0]));
			}
		}
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		comboBoxResponse.setItemCodeList(itemCodeList);
		comboBoxResponse.setItemValueList(itemValueList);
		response.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_WS_FILTERCCP_GENERATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCCPFromFilter(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		List<Object[]> finalList = reportFilterOptionService.getFilteredValues(gtnUIFrameworkWebserviceRequest);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(Optional.ofNullable(finalList).orElseGet(ArrayList::new));
		searchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(searchResponse);
		return response;
	}
}
