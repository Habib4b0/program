package com.stpl.gtn.gtn2o.serviceregistry.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnServiceRegistryRegisterWs;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnUIServiceRegistryService;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.serviceregistry.GtnServiceRegistryWSResponse;

@RestController
@RequestMapping(value = "/gtnServiceRegistry")
public class GtnUIServiceRegistryController extends GtnServiceRegistryImplClass {

	public GtnUIServiceRegistryController() {
		super();
		initializeLogger();
	}

	@Autowired
	private GtnValidateWsServiceRegistryController gtnValidateWsServiceRegistryController;

	@Autowired
	private GtnUIServiceRegistryService gtnUIServiceRegistryService;

	@Autowired
	private GtnServiceRegistryRegisterWs gtnServiceRegistryRegisterWs;

	@PostConstruct
	public void initializeLogger() {
		super.logInformation(GtnUIServiceRegistryController.class);
	}

	@RequestMapping(value = "/registerWebservices", method = RequestMethod.POST)
	public void registerWebServices(@RequestBody GtnUIFrameworkWebserviceRequest request) {

		logger.debug("inside registerWebservices");
		logger.info("Webservice Url:"
				+ request.getGtnServiceRegistryWsRequest().getGtnWsServiceRegistryBean().getWebserviceEndPointUrl());
		logger.info("Webservice Registered Context:"
				+ request.getGtnServiceRegistryWsRequest().getGtnWsServiceRegistryBean().getRegisteredWebContext());

		gtnServiceRegistryRegisterWs.serviceRegistryRegisterWebServices(request);

		logger.error("webservices registered");
	}

	@RequestMapping(value = "/serviceRegistryUIControllerMappingWs", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse serviceRegistryUIControllerMappingWs(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		logger.debug("inside serviceRegistryUIControllerMappingWs");
		logger.trace("UserId:" + request.getGtnWsGeneralRequest().getUserId());
		logger.trace("SessionId:" + request.getGtnWsGeneralRequest().getSessionId());
		logger.info("UserId:" + request.getGtnWsGeneralRequest().getUserId());
		logger.info("SessionId:" + request.getGtnWsGeneralRequest().getSessionId());

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnServiceRegistryWSResponse gtnServiceRegistryWSResponse = new GtnServiceRegistryWSResponse();

		gtnServiceRegistryWSResponse = gtnValidateWsServiceRegistryController
				.serviceRegistryControllerToValidateWs(request);

		logger.debug("Is Webservice Registered:"
				+ gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isRegisteredService());
		logger.debug("Is Webservice Authorized:"
				+ gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isAuthorizaionService());
		if (gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isAuthorizaionService()
				&& gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isRegisteredService()) {
			response = gtnUIServiceRegistryService.serviceRegistryUIServiceCallingWs(request);
			response.setGtnServiceRegistryWSResponse(gtnServiceRegistryWSResponse);
			return response;
		}
		logger.error("inside service Registry : WebService is not registered or authorization failed");
		return response;
	}

	public String getWebServiceEndpoint(String url) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices.endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices.endPointServiceName") + url;

	}

	public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
				+ url;

	}
}
