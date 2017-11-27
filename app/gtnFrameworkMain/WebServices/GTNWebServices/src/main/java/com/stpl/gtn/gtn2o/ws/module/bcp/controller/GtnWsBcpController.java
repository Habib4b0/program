package com.stpl.gtn.gtn2o.ws.module.bcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.bcp.service.GtnWsBcpService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;


@RestController()
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_BCP_SERVICE)
public class GtnWsBcpController {
	@Autowired
	private GtnWsBcpService bcpService;
	public GtnWsBcpController() {
		super();
	}


	@RequestMapping(value = GtnWebServiceUrlConstants.CALCULATE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse calculate(@RequestBody GtnUIFrameworkWebserviceRequest bcpServiceRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
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
