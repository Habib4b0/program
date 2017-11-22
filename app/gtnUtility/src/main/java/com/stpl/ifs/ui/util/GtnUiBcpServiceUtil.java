package com.stpl.ifs.ui.util;

import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.bean.bcp.GtnWsBcpServiceBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.bcp.GtnWsBcpServiceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUiBcpServiceUtil {

	private GtnUiBcpServiceUtil() {
		super();
	}

	public static GtnUIFrameworkWebserviceResponse callBcpService(GtnWsBcpServiceBean gtnWsBcpServiceBean, String url) {
		GtnUIFrameworkWebServiceClient wsBcpClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest wsBcpRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsBcpServiceRequest gtnWsBcpServiceRequest = new GtnWsBcpServiceRequest();
		gtnWsBcpServiceRequest.setGtnWsBcpServiceBean(gtnWsBcpServiceBean);
		wsBcpRequest.setGtnWsBcpServiceRequest(gtnWsBcpServiceRequest);
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		token.setUserId(gtnWsBcpServiceBean.getUserId());
		token.setSessionId(gtnWsBcpServiceBean.getSessionId());
		return wsBcpClient.callGtnWebServiceUrl(url, wsBcpRequest, token);
	}
}
