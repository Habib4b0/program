/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkCompanyConfirmPopulateAction implements GtnUIFrameWorkAction,GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> parameters = gtnUIFrameWorkActionConfig.getFieldValues();
		GtnUIFrameworkBaseComponent valueBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1));
		if (isValidValue(valueBaseComponent)) {
			GtnUIFrameworkBaseComponent fieldBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(0));
			String propertyId = fieldBaseComponent.getStringFromField();
			if (parameters.size() > 3 && Boolean.valueOf(parameters.get(3))) {
				propertyId = fieldBaseComponent.getCaptionFromComboBox();
				propertyId += "1";
			}
			propertyId = propertyId.trim();
			Object value = valueBaseComponent.getObjectFromField();
			if (parameters.get(1).contains("popup")) {
				propertyId += "1";
				value = valueBaseComponent.getComponentData().getCustomDataList().get(0);
			}
			String servicePath = parameters.get(2);
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
			request.setGtnWsContractDashboardRequest(cdRequest);
			cdRequest.setPopulateField(propertyId);
			cdRequest.setPopulateValue(value);
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
			generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getCurrentSessionBean().getSessionId());
			request.setGtnWsGeneralRequest(generalWSRequest);
			new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE + servicePath, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private boolean isValidValue(GtnUIFrameworkBaseComponent baseComponent) {
		boolean ret = true;
		GtnUIFrameworkValidationConfig valConfig = baseComponent.getComponentData().getCurrentComponentConfig()
				.getGtnUIFrameworkValidationConfig();
		if (valConfig != null && (valConfig.isAttachRegxValidatior() || valConfig.isAttachLengthValidatior())) {
			ret = baseComponent.isValidFieldValue();
		}
		return ret;
	}

}
