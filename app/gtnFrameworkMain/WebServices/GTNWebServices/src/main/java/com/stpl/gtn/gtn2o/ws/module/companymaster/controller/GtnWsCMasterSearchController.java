package com.stpl.gtn.gtn2o.ws.module.companymaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companymaster.service.GtnWsCMasterService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RestController
public class GtnWsCMasterSearchController {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCMasterSearchController.class);

	public GtnWsCMasterSearchController() {
		super();
	}

	@Autowired
	private GtnWsCMasterService cmService;

	@RequestMapping(value = "/GtnCompanyMasterDemoSearchService", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCompantMasterSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnMainResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
		try {
			generalWSResponse.setSucess(true);
			gtnSerachResponse = cmService.getCompantMasterSearch(gtnUIFrameworkWebserviceRequest);
		} catch (Exception exception) {
			generalWSResponse.setSucess(false);
			logger.error("Exception in Comtroller", exception);
		}
		gtnMainResponse.setGtnSerachResponse(gtnSerachResponse);
		gtnMainResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnMainResponse;
	}

}
