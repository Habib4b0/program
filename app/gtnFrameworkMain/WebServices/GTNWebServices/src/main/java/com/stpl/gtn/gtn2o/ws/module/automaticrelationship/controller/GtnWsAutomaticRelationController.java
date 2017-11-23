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
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.automaticrelationupdate.GtnFrameworkAutomaticRelationshipResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE)
public class GtnWsAutomaticRelationController {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService service;

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsAutomaticRelationController.class);

	public GtnWsAutomaticRelationController() {
		super();
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.AUTOMATIC_RELATION_UPDATE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse automaticRelationUpdate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnFrameworkAutomaticRelationshipResponse relationResponse = new GtnFrameworkAutomaticRelationshipResponse();
		GtnUIFrameworkWebserviceResponse generalResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			Integer relationshipBuilderSid = gtnWsRequest.getAutomaticRelationEequest().getRelationshipBuilderSid();
			boolean isRelationUpdated = service.checkAndUpdateAutomaticRelationship(relationshipBuilderSid,
					gtnWsRequest.getAutomaticRelationEequest().getUserId());
			relationResponse.setRelationUpdate(isRelationUpdated);
			generalResponse.setAutomaticRelationResponse(relationResponse);
		} catch (GtnFrameworkGeneralException | InterruptedException ex) {
			relationResponse.setRelationUpdate(false);
		}
		return generalResponse;
	}
}
