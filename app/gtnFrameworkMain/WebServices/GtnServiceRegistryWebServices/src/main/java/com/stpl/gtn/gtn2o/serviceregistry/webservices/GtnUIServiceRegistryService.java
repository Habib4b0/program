package com.stpl.gtn.gtn2o.serviceregistry.webservices;


import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnUIServiceRegistryService extends GtnServiceRegistryImplClass {

	private GtnUIServiceRegistryService() {
		super(GtnUIServiceRegistryService.class);
		
	}

	
	public GtnUIFrameworkWebserviceResponse serviceRegistryUIServiceCallingWs(GtnUIFrameworkWebserviceRequest request) {
		logger.info("Entering into Calling Webservice");
		GtnWsServiceRegistryBean serviceRegistryBean = request.getGtnServiceRegistryWsRequest()
				.getGtnWsServiceRegistryBean();

		logger.info("Webservice Url:" + serviceRegistryBean.getUrl());
		logger.info("ModuleName:" + serviceRegistryBean.getModuleName());
		logger.trace("UserId:" + request.getGtnWsGeneralRequest().getUserId());
		logger.trace("SessionId:" + request.getGtnWsGeneralRequest().getSessionId());
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(serviceRegistryBean.getUrl(),
				serviceRegistryBean.getModuleName(), request, getGsnWsSecurityToken(
						request.getGtnWsGeneralRequest().getUserId(), request.getGtnWsGeneralRequest().getSessionId()));

	}

	private GtnWsSecurityToken getGsnWsSecurityToken(String userId, String sessionId) {
		GtnWsSecurityToken wsToken = new GtnWsSecurityToken();
		wsToken.setUserId(userId);
		wsToken.setSessionId(sessionId);
		return wsToken;
	}
}
