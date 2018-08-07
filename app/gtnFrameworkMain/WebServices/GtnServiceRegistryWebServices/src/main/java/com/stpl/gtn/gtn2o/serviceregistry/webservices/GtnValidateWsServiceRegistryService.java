package com.stpl.gtn.gtn2o.serviceregistry.webservices;

import com.stpl.gtn.gtn2o.serviceregistry.bean.GtnWsServiceRegistryBean;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnValidateWsServiceRegistryService {

	public boolean serviceRegistryServiceToValidateWsIsRegistered(GtnWsServiceRegistryBean gtnWsServiceRegistryBean){
		
//		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl("url", request,getGsnWsSecurityToken(request.getGtnWsGeneralRequest().getUserId(),request.getGtnWsGeneralRequest().getSessionId()));
//		if(response.getGtnServiceRegistryWSResponse().getGtnWsServiceRegistryBean()gtnWsServiceRegistryBean <=0){
//			return false;
//		}
		return true;
	}
}
