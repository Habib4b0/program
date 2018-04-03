package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.automaticrelationupdate.GtnFrameworkAutomaticRelationshipResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE)
public class GtnWsAutomaticRelationController {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService service;

	@Autowired
	private GtnFrameworkAutomaticService automaticService;

	public GtnWsAutomaticRelationController() {
		super();
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnFrameworkAutomaticRelationUpdateService.class);

	@RequestMapping(value = GtnWebServiceUrlConstants.AUTOMATIC_RELATION_UPDATE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse automaticRelationUpdate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnFrameworkAutomaticRelationshipResponse relationResponse = new GtnFrameworkAutomaticRelationshipResponse();
		GtnUIFrameworkWebserviceResponse generalResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			Integer relationshipBuilderSid = gtnWsRequest.getAutomaticRelationEequest().getRelationshipBuilderSid();
			boolean isRelationUpdated = service.checkAndUpdateAutomaticRelationship(relationshipBuilderSid);
			relationResponse.setRelationUpdate(isRelationUpdated);
			generalResponse.setAutomaticRelationResponse(relationResponse);
		} catch (GtnFrameworkGeneralException | InterruptedException ex) {
			LOGGER.error("Error in automaticRelationUpdate", ex);
			relationResponse.setRelationUpdate(false);
		}
		return generalResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.AUTOMATIC_ALL_RELATION_UPDATE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse automaticRelationUpdateForAllLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnFrameworkAutomaticRelationshipResponse relationResponse = new GtnFrameworkAutomaticRelationshipResponse();
		GtnUIFrameworkWebserviceResponse generalResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			automaticService.checkAndUpdateAllRelationShip("");
			relationResponse.setRelationUpdate(Boolean.TRUE);
			generalResponse.setAutomaticRelationResponse(relationResponse);
		} catch (Exception ex) {
			LOGGER.error("Error in automaticRelationUpdateForAllLevel", ex);
			relationResponse.setRelationUpdate(false);
		}
		return generalResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.WAIT_AUTOMATIC_RELATION_UPDATE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse waitAutomaticRelationUpdateForAllLevel(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnFrameworkAutomaticRelationshipResponse relationResponse = new GtnFrameworkAutomaticRelationshipResponse();
		GtnUIFrameworkWebserviceResponse generalResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			automaticService.waitForRelaitonUpdatetoFinish();
			relationResponse.setRelationUpdate(Boolean.TRUE);
			generalResponse.setAutomaticRelationResponse(relationResponse);
		} catch (Exception ex) {
			LOGGER.error("Error in waitAutomaticRelationUpdateForAllLevel", ex);
			relationResponse.setRelationUpdate(false);
		}
		return generalResponse;
	}
}
