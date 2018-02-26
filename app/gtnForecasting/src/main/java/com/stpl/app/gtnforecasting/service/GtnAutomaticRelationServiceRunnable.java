package com.stpl.app.gtnforecasting.service;

import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.automaticrelationupdate.GtnFrameworkAutomaticRelationshipRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.Calendar;
import java.util.concurrent.Callable;

public class GtnAutomaticRelationServiceRunnable implements Callable {

	private final Object value;
	private final int hierarchySid;
        private static  String userId;

	public GtnAutomaticRelationServiceRunnable(Object value, int hierarchySid,String userId) {
		super();
		this.value = value;
		this.hierarchySid = hierarchySid;
                GtnAutomaticRelationServiceRunnable.userId = userId;
	}

	public static void testWebservice() {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		client.callGtnWebServiceUrl(GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE
				+ GtnWebServiceUrlConstants.RELATION_WEBSERVICE_TEST, request, getGsnWsSecurityToken());
	}

	private static GtnWsSecurityToken getGsnWsSecurityToken() {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		Integer sessionId = Calendar.getInstance().get(Calendar.MILLISECOND);
		token.setUserId(userId);
		token.setSessionId(sessionId.toString());
		return token;
	}

	@Override
	public Object call() throws Exception {
		if (value == null)
			return Boolean.FALSE;

		int relationShipBuilderSid = Integer.parseInt(value.toString());
		GtnFrameworkAutomaticRelationshipRequest relationRequest = new GtnFrameworkAutomaticRelationshipRequest();
		relationRequest.setRelationshipBuilderSid(relationShipBuilderSid);
		relationRequest.setHierarchyBuilderSid(hierarchySid);
		relationRequest.setUserId(userId);

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setAutomaticRelationEequest(relationRequest);
		GtnUIFrameworkWebserviceResponse newResponse = client
				.callGtnWebServiceUrl(
						GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE
								+ GtnWebServiceUrlConstants.AUTOMATIC_RELATION_UPDATE,
						request, getGsnWsSecurityToken());
		return newResponse.getAutomaticRelationResponse().isRelationUpdate();
	}
}
