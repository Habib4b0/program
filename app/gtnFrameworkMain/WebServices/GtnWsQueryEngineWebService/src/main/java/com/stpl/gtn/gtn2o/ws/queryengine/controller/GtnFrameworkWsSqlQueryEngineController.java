package com.stpl.gtn.gtn2o.ws.queryengine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.service.GtnFrameworkWsSqlQueryEngineService;

@RestController
public class GtnFrameworkWsSqlQueryEngineController {

	@Autowired
	private GtnFrameworkWsSqlQueryEngineService gtnFrameworkWsSqlQueryEngineService;

	public GtnFrameworkWsSqlQueryEngineController() {
		super();
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean test() {
		return true;
	}

	@RequestMapping(value = "/executeQuery", method = RequestMethod.POST)
	public GtnQueryEngineWebServiceResponse executeQuery(
			@RequestBody GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest)
			throws GtnFrameworkGeneralException {
		GtnQueryEngineWebServiceResponse gtnQueryEngineWebServiceResponse = new GtnQueryEngineWebServiceResponse();
		Object result = gtnFrameworkWsSqlQueryEngineService
				.executeQuery(gtnQueryEngineWebServiceRequest.getQueryExecutorBean());
		gtnQueryEngineWebServiceResponse.setResult(result);
		return gtnQueryEngineWebServiceResponse;
	}

}
