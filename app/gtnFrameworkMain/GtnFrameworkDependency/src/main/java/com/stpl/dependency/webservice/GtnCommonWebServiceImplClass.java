package com.stpl.dependency.webservice;

import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.manager.GtnWsSecurityManager;

public abstract class GtnCommonWebServiceImplClass {

	public String readPropertyFile(String propertyFile) {
		return "readPropertyFile:" + propertyFile;
	}

	public GtnFrameworkDependencyLogger logInformation(Class<?> className) {
		GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger.getGTNLogger(className);
		return gtnLogger;
	}

	public abstract void registerWs();

	public GtnQueryEngineWebServiceResponse callQueryEngine(String url, GtnQueryEngineWebServiceRequest request,
			GtnWsSecurityToken securityToken) {
		GtnFrameworkDependencyLogger gtnLogger = logInformation(GtnCommonWebServiceImplClass.class);
		try {

			RestTemplate restTemplate = new RestTemplate();
			updateRequestWithSecurityToken(request, securityToken);
			GtnQueryEngineWebServiceResponse webServiceResponse = restTemplate.postForObject(getEndPointUrl(url),
					request, GtnQueryEngineWebServiceResponse.class);
			return webServiceResponse;
		} catch (Exception e) {
			gtnLogger.error("Exception in web service call", e);
			return null;
		}

	}

	private void updateRequestWithSecurityToken(GtnQueryEngineWebServiceRequest request,
			GtnWsSecurityToken securityToken) {
		GtnWsSecurityManager gtnWsSecurityManager = new GtnWsSecurityManager();
		request.setUserId(securityToken.getUserId());
		request.setSessionId(securityToken.getSessionId());
		request.setToken(gtnWsSecurityManager.createToken(request.getUserId(), request.getSessionId()));
	}

	private String getEndPointUrl(String url) {
		return GtnFrameworkPropertyManager.getProperty("gtn.queryengine.endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.queryengine.endPointServiceName") + url;
	}
}
