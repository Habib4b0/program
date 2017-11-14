/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkValidateContractToProcessAction implements GtnUIFrameWorkAction,GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		GtnWsRecordBean bean = (GtnWsRecordBean) tableBaseComponent.getValueFromComponent();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setContractId(bean.getIntegerPropertyByIndex(6));
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsContractDashboardRequest(cdRequest);
		GtnUIFrameworkWebserviceResponse newResponse = wsclient.callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.VALIDATE_CONTRACT_TO_PROCESS,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsContractDashboardResponse cdNewResponse = newResponse.getGtnWsContractDashboardResponse();
		if (!cdNewResponse.isSuccess()) {
			GtnUIFrameworkActionExecutor.executeActionList(componentId,
					(List<GtnUIFrameWorkActionConfig>) parameters.get(2));
			return;
		}
		GtnUIFrameworkActionExecutor.executeActionList(componentId,
				(List<GtnUIFrameWorkActionConfig>) parameters.get(3));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
