package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@Service
public class GtnWsHierarchyAndRelationshipService extends GtnCommonWebServiceImplClass {

	private GtnWsHierarchyAndRelationshipService() {
		super();
	}

	public void init() {
		initializeLogger();
		GtnUIFrameworkWebserviceRequest request = registerWs();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/registerWebservices", "serviceRegistry"),
				request, GtnUIFrameworkWebserviceResponse.class);
		logger.info("Webservice Registered");
		List<Object[]> resultList = loadHierarchyRelationshipResults();

	}

	public void initializeLogger() {
		super.logInformation(GtnWsHierarchyAndRelationshipService.class);
	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		logger.info("Building request to register Webservice in Service Registry");
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();

		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		getEndPointServiceURL(webServiceRegistryBean);
		logger.info("Webservice to Register: " + webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		return request;
	}

	private void getEndPointServiceURL(GtnWsServiceRegistryBean webServiceRegistryBean) {
		webServiceRegistryBean.setWebserviceEndPointUrl(
				GtnFrameworkPropertyManager.getProperty("gtn.webservices.hierarchyRelationship.endPointUrl"));
		webServiceRegistryBean.setRegisteredWebContext("/GtnHierarchyAndRelaionshipWebService");
	}

	public List<Object[]> loadHierarchyRelationshipResults() {
		return null;
	}
}
