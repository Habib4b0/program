package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdate;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE)
public class GtnWsAutomaticRelationController {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdate service;

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsAutomaticRelationController.class);

	public GtnWsAutomaticRelationController() {
		super();
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.RELATION_WEBSERVICE_TEST, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse testRelationShipWebService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info(
				"************************Success(***************************************************************************");
		return new GtnUIFrameworkWebserviceResponse();
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.AUTOMATIC_RELATION_UPDATE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse automaticRelationUpdate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException, ClassNotFoundException, IOException, InterruptedException {
		Integer relationshipBuilderSid = gtnWsRequest.getAutomaticRelationEequest().getRelationshipBuilderSid();
		service.checkAndUpdateAutomaticRelationship(relationshipBuilderSid,
				gtnWsRequest.getAutomaticRelationEequest().getUserId());

		return new GtnUIFrameworkWebserviceResponse();
	}
}
