package com.stpl.gtn.gtn2o.serviceregistry.controller;

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
import com.stpl.gtn.gtn2o.ws.serviceregistry.GtnServiceRegistryWSResponse;

@RestController
@RequestMapping(value = "/gtnValidateServiceRegistry")
public class GtnValidateWsServiceRegistryController  extends GtnServiceRegistryImplClass {

	
	GtnServiceRegistryImplClass abstr = new GtnUIServiceRegistryController();
	GtnFrameworkDependencyLogger logger = abstr.logInformation(GtnUIServiceRegistryController.class);

	@RequestMapping(value = "/serviceRegistryControllerToValidateWs", method = RequestMethod.POST)
	public GtnServiceRegistryWSResponse serviceRegistryControllerToValidateWs(@RequestBody GtnUIFrameworkWebserviceRequest request) {
		logger.debug("inside serviceRegistryUIControllerMappingWs");

		GtnServiceRegistryWSResponse gtnServiceRegistryWSResponse = new GtnServiceRegistryWSResponse();
		GtnWsServiceRegistryBean gtnWsServiceRegistryBean = new GtnWsServiceRegistryBean();
		
		GtnServiceRegistryAuthorizationService gtnServiceRegistryAuthorizationService = new GtnServiceRegistryAuthorizationService();
		boolean authorizaionCheck = gtnServiceRegistryAuthorizationService.serviceRegistryServiceToAuthorizeWs(gtnWsServiceRegistryBean);
		
		GtnValidateWsServiceRegistryService gtnValidateWsServiceRegistryService = new GtnValidateWsServiceRegistryService();
		boolean serviceRegistryCheck = gtnValidateWsServiceRegistryService.serviceRegistryServiceToValidateWsIsRegistered(gtnWsServiceRegistryBean);
		
		gtnWsServiceRegistryBean.setAuthorizaionService(authorizaionCheck);
		gtnWsServiceRegistryBean.setRegisteredService(serviceRegistryCheck);
		
		gtnServiceRegistryWSResponse.setGtnWsServiceRegistryBean(gtnWsServiceRegistryBean);
		return gtnServiceRegistryWSResponse;
		
	}
	
}