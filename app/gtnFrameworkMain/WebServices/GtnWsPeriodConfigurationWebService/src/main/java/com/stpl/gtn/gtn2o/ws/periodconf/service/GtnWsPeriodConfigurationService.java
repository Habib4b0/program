package com.stpl.gtn.gtn2o.ws.periodconf.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.singleton.bean.GtnFrameworkSingletonObjectBean;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.periodconf.sqlservice.GtnWsPeriodConfSqlService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@Service
public class GtnWsPeriodConfigurationService extends GtnCommonWebServiceImplClass {

	@Autowired
	private GtnWsPeriodConfSqlService gtnWsPeriodConfSqlService;

	private GtnWsPeriodConfigurationService() {
		super();
	}

	@PostConstruct
	private void initializeLogger() {
		super.logInformation(GtnWsPeriodConfigurationService.class);
	}


	private GtnFrameworkSingletonObjectBean singletonObjectBean = GtnFrameworkSingletonObjectBean.getInstance();

	public void init() {
		initializeLogger();
		logger.info("Entering into init method");
		GtnUIFrameworkWebserviceRequest request = registerWs();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/registerWebservices", "serviceRegistry"),
				request, GtnUIFrameworkWebserviceResponse.class);
		logger.info("Webservice Registered");
		List<Object[]> resultList = loadDate(request.getGtnWsGeneralRequest());

		singletonObjectBean.setPeriodConfigResultList(resultList);
	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		logger.info("Building request to register Webservice in Service Registry");
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();

		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		getEndPointUrl(webServiceRegistryBean);
		logger.info("Webservice to Register:" + webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		return request;
	}

	private void getEndPointUrl(GtnWsServiceRegistryBean webServiceRegistryBean) {
		webServiceRegistryBean.setWebserviceEndPointUrl(
				GtnFrameworkPropertyManager.getProperty("gtn.webservices.periodConfiguration.endPointUrl"));
		webServiceRegistryBean.setRegisteredWebContext("/GtnWsPeriodConfigurationWebService");

	}

	public List<Object[]> loadDate(GtnWsGeneralRequest gtnWsGeneralRequest) {

		logger.info("Entering into webservice loadDate  WS->SR->QE->SR->WS");

		String loadDateQuery = gtnWsPeriodConfSqlService.getQuery("loadDate");
		logger.debug("LoadDate Query:" + loadDateQuery);
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(loadDateQuery);
		queryExecutorBean.setQueryType("SELECT");
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		RestTemplate restTemplate1 = new RestTemplate();

		GtnQueryEngineWebServiceResponse response1 = restTemplate1.postForObject(
				getWebServiceEndpointBasedOnModule(
						"/gtnServiceRegistry/serviceRegistryWebservicesForRedirectToQueryEngine", "serviceRegistry"),
				gtnQueryEngineWebServiceRequest, GtnQueryEngineWebServiceResponse.class);
		List<Object[]> resultList1 = response1.getQueryResponseBean().getResultList();
		return resultList1;

	}

}
