package com.stpl.gtn.gtn2o.ws.periodconf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dependency.queryengine.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.GtnWsQueryType;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.periodconf.sqlservice.GtnWsPeriodConfSqlService;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

@Service
public class GtnWsPeriodConfigurationService extends GtnCommonWebServiceImplClass{

	@Autowired
	private GtnWsPeriodConfSqlService gtnWsPeriodConfSqlService;

	@Override
	public void registerWs() {
//		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
//		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();
//		GtnWebServiceRegisterBean webServiceRegisterBean = new GtnWebServiceRegisterBean();
//		webServiceRegisterBean.setServiceUrl("http://localhost:8085");
//		webServiceRegisterBean.setServiceName("/GtnWsPeriodConfigurationWebService");
//		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegisterBean);
//		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
	}

	public List<Object[]> loadDate(GtnWsGeneralRequest gtnWsGeneralRequest) {
		String loadDateQuery = gtnWsPeriodConfSqlService.getQuery("loadFromAndToPeriod");
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(loadDateQuery);
		queryExecutorBean.setQueryType(GtnWsQueryType.SELECT);
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		GtnCommonWebServiceImplClass webServiceImpl = new GtnWsPeriodConfigurationService();
		GtnQueryEngineWebServiceResponse response = webServiceImpl.callQueryEngine("/executeQuery",
				gtnQueryEngineWebServiceRequest, getGtnSecurityToken(gtnWsGeneralRequest));
		List<Object[]> resultList =response.getQueryEngineWebServiceBean().getResultList();
		return resultList;
	}

	private GtnWsSecurityToken getGtnSecurityToken(GtnWsGeneralRequest gtnWsGeneralRequest) {
		GtnWsSecurityToken wsToken = new GtnWsSecurityToken();
		wsToken.setUserId(gtnWsGeneralRequest.getUserId());
		wsToken.setSessionId(gtnWsGeneralRequest.getSessionId());
		return wsToken;
	}	
	
	
}
