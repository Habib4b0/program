/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.module.compliancedeductionrule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsSearchServiceController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Mahesh.James
 */
@RestController
@RequestMapping(value = GtnWsCDRContants.RULE_POPUP_SERVICE)
public class GtnWsPopUpController {
	public GtnWsPopUpController() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private GtnWsSearchServiceController gTNSearchServiceController;

	@RequestMapping(value = "/" + GtnWsCDRContants.PS_SEARCH_HELPER_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse priceScheduleSearch(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList();
		GtnWebServiceSearchCriteria searchFieldCriteria = gtnWebServiceSearchCriteriaList.get(0);
		GtnWebServiceSearchCriteria searchValueCriteria = gtnWebServiceSearchCriteriaList.get(1);
		searchValueCriteria.setFieldId(searchFieldCriteria.getFilterValue1());
		gtnWsRequest.getGtnWsSearchRequest().removeGtnWebServiceSearchCriteriaList(0);
		return gTNSearchServiceController.getSearchResultForAllModule(gtnWsRequest);
	}

}
