package com.stpl.gtn.gtn2o.serviceregistry.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnUIServiceRegistryService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.serviceregistry.GtnServiceRegistryWSResponse;

@RestController
@RequestMapping(value = "/gtnServiceRegistry")
public class GtnUIServiceRegistryController extends GtnServiceRegistryImplClass {

	GtnServiceRegistryImplClass abstr = new GtnUIServiceRegistryController();
	GtnFrameworkDependencyLogger logger = abstr.logInformation(GtnUIServiceRegistryController.class);

	public void init(){
		
	}
	
	@RequestMapping(value = "/serviceRegistryUIControllerMappingWs", method = RequestMethod.POST)
	public GtnServiceRegistryWSResponse serviceRegistryUIControllerMappingWs(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		logger.debug("inside serviceRegistryUIControllerMappingWs");

		GtnServiceRegistryWSResponse gtnServiceRegistryWSResponse = new GtnServiceRegistryWSResponse();

		GtnValidateWsServiceRegistryController gtnValidateWsServiceRegistryController = new GtnValidateWsServiceRegistryController();
		gtnServiceRegistryWSResponse = gtnValidateWsServiceRegistryController
				.serviceRegistryControllerToValidateWs(request);

		return gtnServiceRegistryWSResponse;
	}

	@RequestMapping(value = "/serviceRegistryUIControllerCallingWs", method = RequestMethod.POST)
	public GtnServiceRegistryWSResponse serviceRegistryUIControllerCallingWs(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		logger.debug("inside serviceRegistryUIControllerCallingWs");

		GtnServiceRegistryWSResponse gtnServiceRegistryWSResponse = new GtnServiceRegistryWSResponse();

		GtnUIServiceRegistryService gtnUIServiceRegistryService = new GtnUIServiceRegistryService();
		gtnServiceRegistryWSResponse = gtnUIServiceRegistryService.serviceRegistryUIServiceCallingWs(request);

		return gtnServiceRegistryWSResponse;
	}

}
