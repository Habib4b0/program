package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnFrameworkCCPInsertService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CCP_INSERT_SERVICE)
public class GtnFrameworkCCPInsertController {

	@Autowired
	private GtnFrameworkCCPInsertService service;

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CCP_INSERT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse ccpInsertToForecasting(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsForecastRequest forecastRequet = gtnWsRequest.getGtnWsForecastRequest();
		GtnForecastHierarchyInputBean inputBean = forecastRequet.getInputBean();
		service.insertToCPPTable(inputBean);
		return new GtnUIFrameworkWebserviceResponse();
	}

}
