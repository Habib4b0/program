/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.controller;

import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.service.GtnGeneralSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anandh.karuppusamy
 */
@RestController
public class GtnSearchController extends GtnServiceRegistryImplClass {

	 private GtnSearchController() {
	        super(GtnSearchController.class);
	    }


	@Autowired
	private GtnGeneralSearchService gtnGeneralSearch;

	@GetMapping(value = "/searchTest")
	public boolean test() {
		return true;
	}

	@PostMapping(value = "/gtnSearch")
	public GtnUIFrameworkWebserviceResponse gtnGeneralSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest) {
		logger.info("entering into general search controller");
		GtnUIFrameworkWebserviceResponse response;
		response = gtnGeneralSearch.commonMethod(gtnUiFrameworkWebservicerequest);
		return response;
	}

	@PostMapping(value = "/forecastingPagedTableSearch")
	public GtnUIFrameworkWebserviceResponse gtnPagedTableSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest) {
		GtnUIFrameworkWebserviceResponse response;
		response = gtnGeneralSearch.pagedTableSearch(gtnUiFrameworkWebservicerequest);
		return response;
	}

	@PostMapping(value = "/gtnForecastSaveView")
	public GtnUIFrameworkWebserviceResponse gtnForecastSaveView(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest) {
		logger.info("entering into general search controller");
		GtnUIFrameworkWebserviceResponse response;
		response = gtnGeneralSearch.saveView(gtnUiFrameworkWebservicerequest);
		return response;
	}
}
