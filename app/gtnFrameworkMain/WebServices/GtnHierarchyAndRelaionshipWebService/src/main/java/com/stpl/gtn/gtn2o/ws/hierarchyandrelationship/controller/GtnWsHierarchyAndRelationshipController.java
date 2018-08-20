package com.stpl.gtn.gtn2o.ws.hierarchyandrelationship.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.hierarchyandrelationship.service.GtnWsHierarchyAndRelationshipService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@RestController
@RequestMapping(value = "/hierarchyAndRelationship")
public class GtnWsHierarchyAndRelationshipController {
	
	@RequestMapping(value = "/test" , method = RequestMethod.GET)
	public boolean test(){
		GtnWsHierarchyAndRelationshipService gtnWsHierarchyAndRelationshipService = new GtnWsHierarchyAndRelationshipService();
		gtnWsHierarchyAndRelationshipService.getRelationshipLevelValues(new GtnUIFrameworkWebserviceRequest());
		return true;
	}
}
