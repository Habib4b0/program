package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service.GtnWsDataselectionHierarchyUpdateService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE)
public class GtnWsDataSelectionEditController {
	public GtnWsDataSelectionEditController() {
		super();
	}

	@Autowired
	private GtnWsDataselectionHierarchyUpdateService service;
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_DATASELECTION_EDIT_CUSTHIERARCHY_INSERT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkAndUpdateCustAndProdHierarchy(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {

		service.checkAndInsertHierarchy(gtnWsRequest.getGtnWshirarchyInsertRequest());

		return new GtnUIFrameworkWebserviceResponse();
	}
}
