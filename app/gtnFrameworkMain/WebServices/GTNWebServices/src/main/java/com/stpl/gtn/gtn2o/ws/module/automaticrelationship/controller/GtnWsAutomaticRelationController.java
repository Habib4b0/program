package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.automaticrelationupdate.GtnFrameworkAutomaticRelationshipResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE)
public class GtnWsAutomaticRelationController {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService service;

	public GtnWsAutomaticRelationController() {
		super();
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.AUTOMATIC_RELATION_UPDATE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse automaticRelationUpdate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException, InterruptedException {
		Integer relationshipBuilderSid = gtnWsRequest.getAutomaticRelationEequest().getRelationshipBuilderSid();
		boolean isRelationUpdated = service.checkAndUpdateAutomaticRelationship(relationshipBuilderSid,
				gtnWsRequest.getAutomaticRelationEequest().getUserId());
		GtnFrameworkAutomaticRelationshipResponse relationResponse = new GtnFrameworkAutomaticRelationshipResponse();
		relationResponse.setRelationUpdate(isRelationUpdated);
		GtnUIFrameworkWebserviceResponse generalResponse = new GtnUIFrameworkWebserviceResponse();
		generalResponse.setAutomaticRelationResponse(relationResponse);
		return generalResponse;
	}
}
