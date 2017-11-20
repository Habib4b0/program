package com.stpl.gtn.gtn2o.ws.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsBcpService;


@RestController(value = GtnWebServiceUrlConstants.GTN_BCP_SERVICE)
public class GtnWsBcpController {
	public GtnWsBcpController() {
		super();
	}


	@RequestMapping(value = GtnWebServiceUrlConstants.CALCULATE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse calculate(@RequestBody GtnUIFrameworkWebserviceRequest bcpServiceRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			GtnWsBcpService bcpService = new GtnWsBcpService();
			generalResponse.setSucess(true);
			bcpService.calculate(bcpServiceRequest);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException e) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(e);
			return gtnResponse;
		}
	}

}
