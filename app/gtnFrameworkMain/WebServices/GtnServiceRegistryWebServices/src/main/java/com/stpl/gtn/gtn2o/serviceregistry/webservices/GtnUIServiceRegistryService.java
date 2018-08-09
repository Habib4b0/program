package com.stpl.gtn.gtn2o.serviceregistry.webservices;

import com.stpl.gtn.gtn2o.serviceregistry.bean.GtnWsServiceRegistryBean;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIServiceRegistryService {

	public GtnUIFrameworkWebserviceResponse serviceRegistryUIServiceCallingWs(GtnUIFrameworkWebserviceRequest request){
		
		GtnWsServiceRegistryBean serviceRegistryBean = request.getGtnServiceRegistryWsRequest().getGtnWsServiceRegistryBean();
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(serviceRegistryBean.getUrl(), serviceRegistryBean.getModuleName(), request, getGsnWsSecurityToken(
				request.getGtnWsGeneralRequest().getUserId(),request.getGtnWsGeneralRequest().getSessionId()));
		
		return response;
	}
	
	private GtnWsSecurityToken getGsnWsSecurityToken(String userId, String sessionId) {
		GtnWsSecurityToken wsToken = new GtnWsSecurityToken();
		wsToken.setUserId(userId);
		wsToken.setSessionId(sessionId);
		return wsToken;
	}
}
