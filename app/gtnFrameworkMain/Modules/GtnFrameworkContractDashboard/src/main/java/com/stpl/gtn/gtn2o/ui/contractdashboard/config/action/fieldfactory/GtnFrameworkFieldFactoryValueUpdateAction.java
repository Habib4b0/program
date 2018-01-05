/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
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
public class GtnFrameworkFieldFactoryValueUpdateAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		request.setGtnWsContractDashboardRequest(cdRequest);
		cdRequest.setSystemId(Integer.parseInt(String.valueOf(parameters.get(2))));
		cdRequest.setPopulateField(String.valueOf(parameters.get(3)));
		if (String.valueOf(parameters.get(4)).endsWith("%")) {

			Object perValue = String.valueOf(parameters.get(4)).substring(0,
					String.valueOf(parameters.get(4)).length() - 1);

			cdRequest.setPopulateValue(perValue);
		}

		else if (String.valueOf(parameters.get(4)).startsWith("$")) {

			Object dollarValue = String.valueOf(parameters.get(4)).substring(1);

			cdRequest.setPopulateValue(dollarValue);
		} else {

			cdRequest.setPopulateValue(parameters.get(4));

		}
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getCurrentSessionBean().getSessionId());
		request.setGtnWsGeneralRequest(generalWSRequest);
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE + parameters.get(1), request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
