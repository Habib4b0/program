package com.stpl.gtn.gtn2o.serviceregistry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.serviceregistry.bean.GtnWsServiceRegistryBean;
import com.stpl.gtn.gtn2o.serviceregistry.constants.GtnWsServiceRegistryConstants;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnUIServiceRegistryService;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.serviceregistry.GtnServiceRegistryWSResponse;

@RestController
@RequestMapping(value = "/gtnServiceRegistry")
public class GtnUIServiceRegistryController extends GtnServiceRegistryImplClass {

	public GtnUIServiceRegistryController() {
		super();
	}

	@Autowired
	private GtnUIServiceRegistryController gtnUIServiceRegistryController;

	@Autowired
	private GtnValidateWsServiceRegistryController gtnValidateWsServiceRegistryController;
	
	@Autowired
	private GtnUIServiceRegistryService gtnUIServiceRegistryService;

	@RequestMapping(value = "/registerWebservices", method = RequestMethod.POST)
	public void registerWebServices(GtnUIFrameworkWebserviceRequest request) {
		String registerQuery = GtnWsServiceRegistryConstants.INSERT_QUERY;
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl("url", request,getGsnWsSecurityToken(request.getGtnWsGeneralRequest().getUserId(),request.getGtnWsGeneralRequest().getSessionId()));
	}
	

	@RequestMapping(value = "/serviceRegistryUIControllerMappingWs", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse serviceRegistryUIControllerMappingWs(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		GtnFrameworkDependencyLogger logger = gtnUIServiceRegistryController
				.logInformation(GtnUIServiceRegistryController.class);
		logger.debug("inside serviceRegistryUIControllerMappingWs");

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnServiceRegistryWSResponse gtnServiceRegistryWSResponse = new GtnServiceRegistryWSResponse();

		gtnServiceRegistryWSResponse = gtnValidateWsServiceRegistryController
				.serviceRegistryControllerToValidateWs(request);

		response.setGtnServiceRegistryWSResponse(gtnServiceRegistryWSResponse);

		return response;
	}

	@RequestMapping(value = "/serviceRegistryUIControllerCallingWs", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse serviceRegistryUIControllerCallingWs(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		GtnFrameworkDependencyLogger logger = gtnUIServiceRegistryController
				.logInformation(GtnUIServiceRegistryController.class);
		logger.debug("inside serviceRegistryUIControllerCallingWs");

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		response = gtnUIServiceRegistryService.serviceRegistryUIServiceCallingWs(request);

		return response;
	}

	private GtnWsSecurityToken getGsnWsSecurityToken(String userId, String sessionId) {
		GtnWsSecurityToken wsToken = new GtnWsSecurityToken();
		wsToken.setUserId(userId);
		wsToken.setSessionId(sessionId);
		return wsToken;
	}
}
