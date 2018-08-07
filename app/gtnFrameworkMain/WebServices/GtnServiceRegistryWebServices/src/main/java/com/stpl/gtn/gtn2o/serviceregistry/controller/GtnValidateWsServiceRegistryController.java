package com.stpl.gtn.gtn2o.serviceregistry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.serviceregistry.bean.GtnWsServiceRegistryBean;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnServiceRegistryAuthorizationService;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnValidateWsServiceRegistryService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.serviceregistry.GtnServiceRegistryWSResponse;

@RestController
@RequestMapping(value = "/gtnValidateServiceRegistry")
public class GtnValidateWsServiceRegistryController extends GtnServiceRegistryImplClass {

	GtnFrameworkDependencyLogger logger;

	public GtnValidateWsServiceRegistryController() {
		super();

	}

	@Autowired
	private GtnValidateWsServiceRegistryController gtnValidateWsServiceRegistryController;

	@Autowired
	private GtnServiceRegistryAuthorizationService gtnServiceRegistryAuthorizationService;

	@Autowired
	private GtnValidateWsServiceRegistryService gtnValidateWsServiceRegistryService;

	@RequestMapping(value = "/serviceRegistryControllerToValidateWs", method = RequestMethod.POST)
	public GtnServiceRegistryWSResponse serviceRegistryControllerToValidateWs(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		GtnFrameworkDependencyLogger logger = gtnValidateWsServiceRegistryController
				.logInformation(GtnValidateWsServiceRegistryController.class);
		logger.debug("inside serviceRegistryUIControllerMappingWs");

		GtnServiceRegistryWSResponse gtnServiceRegistryWSResponse = new GtnServiceRegistryWSResponse();
		GtnWsServiceRegistryBean gtnWsServiceRegistryBean = new GtnWsServiceRegistryBean();

		boolean authorizaionCheck = gtnServiceRegistryAuthorizationService
				.serviceRegistryServiceToAuthorizeWs(gtnWsServiceRegistryBean);

		boolean serviceRegistryCheck = gtnValidateWsServiceRegistryService
				.serviceRegistryServiceToValidateWsIsRegistered(gtnWsServiceRegistryBean);

		gtnWsServiceRegistryBean.setAuthorizaionService(authorizaionCheck);
		gtnWsServiceRegistryBean.setRegisteredService(serviceRegistryCheck);

		gtnServiceRegistryWSResponse.setGtnWsServiceRegistryBean(gtnWsServiceRegistryBean);
		return gtnServiceRegistryWSResponse;

	}

}