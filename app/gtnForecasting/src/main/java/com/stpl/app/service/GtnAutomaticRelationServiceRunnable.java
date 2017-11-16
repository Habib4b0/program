package com.stpl.app.service;

import java.util.Calendar;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.automaticrelationupdate.GtnFrameworkAutomaticRelationshipRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.server.VaadinSession;

public class GtnAutomaticRelationServiceRunnable implements Runnable {

	private Object value;
	private int hierarchySid;

	public GtnAutomaticRelationServiceRunnable(Object value, int hierarchySid) {
		super();
		this.value = value;
		this.hierarchySid = hierarchySid;
	}

	public static void testWebservice() {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE
						+ GtnWebServiceUrlConstants.RELATION_WEBSERVICE_TEST,
				request, getGsnWsSecurityToken());
	}

	private static GtnWsSecurityToken getGsnWsSecurityToken() {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		Integer sessionId = Calendar.getInstance().get(Calendar.MILLISECOND);
		String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
		token.setUserId(userId);
		token.setSessionId(sessionId.toString());
		return token;
	}

	@Override
	public void run() {
		if (value == null)
			return;

		Integer relationShipBuilderSid = Integer.parseInt(value.toString());
		GtnFrameworkAutomaticRelationshipRequest relationRequest = new GtnFrameworkAutomaticRelationshipRequest();
		relationRequest.setRelationshipBuilderSid(relationShipBuilderSid);
		relationRequest.setHierarchyBuilderSid(hierarchySid);
		String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
		relationRequest.setUserId(userId);

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setAutomaticRelationEequest(relationRequest);
		GtnUIFrameworkWebserviceResponse newResponse = client
				.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE
						+ GtnWebServiceUrlConstants.AUTOMATIC_RELATION_UPDATE,
				request, getGsnWsSecurityToken());
	}
}
