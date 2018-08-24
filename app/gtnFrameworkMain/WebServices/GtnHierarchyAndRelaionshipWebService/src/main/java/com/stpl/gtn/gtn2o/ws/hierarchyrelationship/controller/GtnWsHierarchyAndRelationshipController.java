package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.service.GtnWsHierarchyAndRelationshipService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class GtnWsHierarchyAndRelationshipController {

	@Autowired
	private GtnWsHierarchyAndRelationshipService gtnWsHierarchyRelationshipService;

	private GtnWsHierarchyAndRelationshipController() {
		super();
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean display() {
		return true;
	}

	@RequestMapping(value = "/loadHierarchyResults", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadHierarchyResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		
		List<Object[]> hierarchyList = gtnWsHierarchyRelationshipService
				.loadHierarchyResults(gtnUIFrameworkWebserviceRequest);
		
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(hierarchyList);
		searchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(searchResponse);
		
		return response;
	}
}
