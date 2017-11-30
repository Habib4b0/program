package com.stpl.gtn.gtn2o.ws.module.udc.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@Service
@Scope(value = "singleton")
public class GtnWsUdcTableService {

	public GtnUIFrameworkWebserviceResponse tableUdc(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		return gtnResponse;
	}

}
