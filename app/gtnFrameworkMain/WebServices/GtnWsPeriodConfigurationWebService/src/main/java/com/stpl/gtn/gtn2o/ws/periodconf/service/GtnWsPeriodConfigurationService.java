package com.stpl.gtn.gtn2o.ws.periodconf.service;

import org.springframework.stereotype.Service;

import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.serviceregistry.bean.GtnWebServiceRegisterBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;

@Service
public class GtnWsPeriodConfigurationService extends GtnCommonWebServiceImplClass{

	@Override
	public void registerWs() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();
		GtnWebServiceRegisterBean webServiceRegisterBean = new GtnWebServiceRegisterBean();
		webServiceRegisterBean.setServiceUrl("http://localhost:8085");
		webServiceRegisterBean.setServiceName("/GtnWsPeriodConfigurationWebService");
		gtnServiceRegistryWsRequest.setGtnWebServiceRegisterBean(webServiceRegisterBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
	}
	
	
	
}
