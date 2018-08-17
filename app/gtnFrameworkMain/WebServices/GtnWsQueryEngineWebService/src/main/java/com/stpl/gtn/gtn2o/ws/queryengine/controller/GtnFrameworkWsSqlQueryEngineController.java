package com.stpl.gtn.gtn2o.ws.queryengine.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.queryengine.service.GtnFrameworkWsSqlQueryEngineService;

@RestController
public class GtnFrameworkWsSqlQueryEngineController extends GtnServiceRegistryImplClass {

	@Autowired
	private GtnFrameworkWsSqlQueryEngineService gtnFrameworkWsSqlQueryEngineService;

	public GtnFrameworkWsSqlQueryEngineController() {
		super();
		initializeLogger();
	}

	@PostConstruct
	public void initializeLogger() {
		super.logInformation(GtnFrameworkWsSqlQueryEngineController.class);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean test() {
		return true;
	}

	@RequestMapping(value = "/executeQuery", method = RequestMethod.POST)
	public GtnQueryEngineWebServiceResponse executeQuery(
			@RequestBody GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest)
			throws GtnFrameworkGeneralException {
		logger.trace("Entering into QueryEngine ExecuteQuery method:");
		GtnQueryEngineWebServiceResponse gtnQueryEngineWebServiceResponse = new GtnQueryEngineWebServiceResponse();
		GtnFrameworkQueryResponseBean queryResponseBean = (GtnFrameworkQueryResponseBean) gtnFrameworkWsSqlQueryEngineService
				.executeQuery(gtnQueryEngineWebServiceRequest.getQueryExecutorBean());
		gtnQueryEngineWebServiceResponse.setQueryResponseBean(queryResponseBean);
		logger.trace("Exiting from QueryEngine ExecuteQuery method:");
		return gtnQueryEngineWebServiceResponse;
	}

}
