package com.stpl.dependency.webservice;

import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

public abstract class GtnCommonWebServiceImplClass {

	public GtnFrameworkDependencyLogger logger;
	
	public String readPropertyFile(String propertyFile) {
		return "readPropertyFile:" + propertyFile;
	}

	public void logInformation(Class<?> className){
		logger = GtnFrameworkDependencyLogger.getGTNLogger(className);
	}

	public abstract GtnUIFrameworkWebserviceRequest registerWs();

	public GtnQueryEngineWebServiceResponse callQueryEngine(String url, GtnQueryEngineWebServiceRequest request,
			GtnWsSecurityToken securityToken) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			GtnQueryEngineWebServiceResponse webServiceResponse = restTemplate.postForObject(
					getWebServiceEndpointBasedOnModule(url, "queryEngine"), request,
					GtnQueryEngineWebServiceResponse.class);
			return webServiceResponse;
		} catch (Exception e) {
			logger.error("Exception in web service call", e);
			return null;
		}

	}

	public GtnQueryEngineWebServiceResponse callQueryEngineWithoutSecurityToken(String url,
			GtnQueryEngineWebServiceRequest request) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			GtnQueryEngineWebServiceResponse webServiceResponse = restTemplate.postForObject(
					getWebServiceEndpointBasedOnModule(url, "queryEngine"), request,
					GtnQueryEngineWebServiceResponse.class);
			return webServiceResponse;
		} catch (Exception e) {
			logger.error("Exception in web service call", e);
			return null;
		}

	}

	public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
		
				return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
				+ url;

	}
}
