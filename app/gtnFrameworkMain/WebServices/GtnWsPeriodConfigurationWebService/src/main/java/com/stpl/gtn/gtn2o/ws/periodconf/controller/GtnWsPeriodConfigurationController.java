package com.stpl.gtn.gtn2o.ws.periodconf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.ws.periodconf.service.GtnWsPeriodConfigurationService;
import com.stpl.gtn.gtn2o.ws.request.periodconfig.GtnWsPeriodConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = "/gtnPeriodConfigurationController")
public class GtnWsPeriodConfigurationController extends GtnServiceRegistryImplClass {

	@Autowired
	private GtnWsPeriodConfigurationService periodConfigurationService;
	
	private GtnWsPeriodConfigurationController() {
		super(GtnWsPeriodConfigurationController.class);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean testGet() {
		return testPost();
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public boolean testPost() {
		return true;
	}
	
	@RequestMapping(value = "/loadDate", method = RequestMethod.GET)
	public GtnUIFrameworkWebserviceResponse loadDateGet() {
		GtnWsPeriodConfigurationRequest gtnWsPeriodConfigurationRequest = new GtnWsPeriodConfigurationRequest();
		return loadDate(gtnWsPeriodConfigurationRequest);
	}
	
	@RequestMapping(value = "/loadDate", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadDate(
			@RequestBody GtnWsPeriodConfigurationRequest gtnWsPeriodConfigurationRequest) {
		logger.info("Entering into PeriodConfiguration Controller to load From To Period");
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		gtnWsPeriodConfigurationRequest.setBusinessProcessType("Commercial");
		comboBoxResponse.setComboBoxList(periodConfigurationService.getPeriodResults(gtnWsPeriodConfigurationRequest.getBusinessProcessType()));
		gtnUIFrameworkWebserviceResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
		return gtnUIFrameworkWebserviceResponse;
	}

	@RequestMapping(value = "/loadRefreshDate", method = RequestMethod.GET)
	public void loadRefreshDateGet() {
		loadRefreshDate();
	}
		
	@RequestMapping(value = "/loadRefreshDate", method = RequestMethod.POST)
	public void loadRefreshDate() {
		logger.info("Reloading From To Results after Forecast Configuration Change");
		periodConfigurationService.loadDate();
	}
	
	
}
