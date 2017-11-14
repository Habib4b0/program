package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkPSdefaultValuesetAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<String> disableFieldList = (List<String>) actionParameterList.get(1);
		List<String> disableValueList = (List<String>) actionParameterList.get(2);
		for (int i = 0; i < disableFieldList.size(); i++) {
			if ("createdBy".equals(disableFieldList.get(i))) {
				setUserNametoField(disableFieldList.get(i), GtnUIFrameworkGlobalUI.getCurrentUser());
			} else
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(disableFieldList.get(i))
						.setPropertyValue(disableValueList.get(i));

		}

	}

	private void setUserNametoField(String componentId, String userid) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalReq = new GtnWsGeneralRequest();
		generalReq.setUserId(userid);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE_USER_NAME,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		String userName = response.getGtnWsGeneralResponse().getUserName();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).setPropertyValue(userName);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
