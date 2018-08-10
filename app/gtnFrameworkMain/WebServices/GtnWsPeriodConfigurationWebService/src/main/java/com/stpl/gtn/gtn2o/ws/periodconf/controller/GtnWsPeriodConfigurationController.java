package com.stpl.gtn.gtn2o.ws.periodconf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.periodconf.service.GtnWsPeriodConfigurationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class GtnWsPeriodConfigurationController{
	
	@Autowired
	private GtnWsPeriodConfigurationService gtnWsPeriodConfigurationService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean test() {
		return true;
	}

	@RequestMapping(value = "/loadDate", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadDate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		List<Object[]> resultList = gtnWsPeriodConfigurationService.loadDate(generalRequest);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		searchResponse.setResultSet(dataTable);
		gtnUIFrameworkWebserviceResponse.setGtnSerachResponse(searchResponse);
		return gtnUIFrameworkWebserviceResponse;
	}
	
	
}
