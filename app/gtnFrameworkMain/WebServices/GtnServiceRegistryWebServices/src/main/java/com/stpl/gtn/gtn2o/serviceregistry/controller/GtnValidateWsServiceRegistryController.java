package com.stpl.gtn.gtn2o.serviceregistry.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnServiceRegistryAuthorizationService;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnValidateWsServiceRegistryService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.serviceregistry.GtnServiceRegistryWSResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@RestController
@RequestMapping(value = "/gtnValidateServiceRegistry")
public class GtnValidateWsServiceRegistryController extends GtnServiceRegistryImplClass {

	public GtnValidateWsServiceRegistryController() {
		super(GtnValidateWsServiceRegistryController.class);
		
	}

	@Autowired
	private GtnServiceRegistryAuthorizationService gtnServiceRegistryAuthorizationService;

	@Autowired
	private GtnValidateWsServiceRegistryService gtnValidateWsServiceRegistryService;

	public GtnServiceRegistryWSResponse serviceRegistryControllerToValidateWs(GtnUIFrameworkWebserviceRequest request) {
		logger.info("inside serviceRegistryUIControllerMappingWs");
		logger.trace("UserId:" + request.getGtnWsGeneralRequest().getUserId());
		logger.trace("SessionId:" + request.getGtnWsGeneralRequest().getSessionId());

		GtnServiceRegistryWSResponse gtnServiceRegistryWSResponse = new GtnServiceRegistryWSResponse();
		GtnWsServiceRegistryBean gtnWsServiceRegistryBean = request.getGtnServiceRegistryWsRequest()
				.getGtnWsServiceRegistryBean();

		boolean authorizaionCheck = gtnServiceRegistryAuthorizationService.serviceRegistryServiceToAuthorizeWs();

		boolean serviceRegistryCheck = gtnValidateWsServiceRegistryService
				.serviceRegistryServiceToValidateWsIsRegistered(gtnWsServiceRegistryBean);

		gtnWsServiceRegistryBean.setAuthorizaionService(authorizaionCheck);
		gtnWsServiceRegistryBean.setRegisteredService(serviceRegistryCheck);

		gtnServiceRegistryWSResponse.setGtnWsServiceRegistryBean(gtnWsServiceRegistryBean);
		return gtnServiceRegistryWSResponse;

	}

}