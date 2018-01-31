package com.stpl.gtn.gtn2o.ws.module.returnsforecasting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.controller.GtnWsGeneralController;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 *
 * @author Stpl
 */

@RestController
@RequestMapping(value = "gtnReturnsForecasting/DataSelectionValidation")
public class GtnWsReturnsSearchValidationController {
	public GtnWsReturnsSearchValidationController() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private GtnWsGeneralController gtnGeneralServiceController;
        @Autowired
	private GtnWsSqlService gtnWsSqlService;
	public GtnWsGeneralController getGtnGeneralServiceController() {
		return gtnGeneralServiceController;
	}

	public void setGtnGeneralServiceController(GtnWsGeneralController gtnGeneralServiceController) {
		this.gtnGeneralServiceController = gtnGeneralServiceController;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/getBusinessUnitValidation")

	public GtnUIFrameworkWebserviceResponse getBusinessUnitValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String queryName = "getBusinessUnitValidation";
			List<Object[]> result = gtnGeneralServiceController
					.executeQuery(gtnWsSqlService.getQuery(getValidationInput(gtnWsRequest.getGtnWsSearchRequest()), queryName));

			gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));

			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		return gtnResponse;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getValidationInput(GtnWsSearchRequest gtnWsSearchRequest) {
		List inputList = new ArrayList<>();
		for (Object queryInput : gtnWsSearchRequest.getQueryInputList()) {
			if (queryInput != null) {
				inputList.add(String.valueOf(queryInput));
			}
		}
		return inputList;
	}

}
