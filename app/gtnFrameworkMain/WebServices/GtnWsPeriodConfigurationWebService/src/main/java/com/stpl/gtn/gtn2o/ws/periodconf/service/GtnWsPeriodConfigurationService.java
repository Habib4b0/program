package com.stpl.gtn.gtn2o.ws.periodconf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.singleton.bean.GtnFrameworkSingletonObjectBean;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.periodconf.sqlservice.GtnWsPeriodConfSqlService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWebServiceRegisterBean;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@Service
public class GtnWsPeriodConfigurationService extends GtnCommonWebServiceImplClass {

	@Autowired
	private GtnWsPeriodConfSqlService gtnWsPeriodConfSqlService;

	GtnFrameworkSingletonObjectBean singletonObjectBean = GtnFrameworkSingletonObjectBean.getInstance();

	public void init() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();
		GtnWsServiceRegistryBean webServiceRegisterBean = new GtnWsServiceRegistryBean();

		webServiceRegisterBean.setHostName("http://localhost");
		webServiceRegisterBean.setPort("8092");
		webServiceRegisterBean.setRegisteredWebContext("/GtnWsPeriodConfigurationWebService");
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegisterBean);

		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/registerWebservices", "serviceRegistry"),
				request, GtnUIFrameworkWebserviceResponse.class);
		List<Object[]> resultList = loadDate(request.getGtnWsGeneralRequest());

		singletonObjectBean.setPeriodConfigResultList(resultList);
	}

	@Override
	public void registerWs() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();
		GtnWebServiceRegisterBean webServiceRegisterBean = new GtnWebServiceRegisterBean();
		webServiceRegisterBean.setServiceUrl("http://localhost:8085");
		webServiceRegisterBean.setServiceName("/GtnWsPeriodConfigurationWebService");
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
	}

	public List<Object[]> loadDate(GtnWsGeneralRequest gtnWsGeneralRequest) {

		String loadDateQuery = gtnWsPeriodConfSqlService.getQuery("loadDate");
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(loadDateQuery);
		queryExecutorBean.setQueryType("SELECT");
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		GtnCommonWebServiceImplClass webServiceImpl = new GtnWsPeriodConfigurationService();
		GtnQueryEngineWebServiceResponse response = webServiceImpl.callQueryEngineWithoutSecurityToken("/executeQuery",
				gtnQueryEngineWebServiceRequest);
		List<Object[]> resultList = response.getQueryResponseBean().getResultList();

		return resultList;
	}
}
