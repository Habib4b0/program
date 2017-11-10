package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

public class GtnUIFrameworkCloseButtonAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsContractDashboardSessionBean sharedBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		if (sharedBean.getProcessBean().getCfpContractId() != 0 || sharedBean.getProcessBean().getIfpContractId() != 0
				|| sharedBean.getProcessBean().getPsContractId() != 0
				|| sharedBean.getProcessBean().getRsContractId() != 0) {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			generalWSRequest.setUserId(sharedBean.getProcessBean().getUserId());
			generalWSRequest.setSessionId(sharedBean.getProcessBean().getSessionId());
			request.setGtnWsGeneralRequest(generalWSRequest);
			wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
							+ GtnWsContractDashboardContants.DELETE_CONTRACT_INFO_ON_BACK_PROCESS,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		}
		if (parameters.size() > 1) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).closeUI();
			GtnUIFrameworkGlobalUI.windowClose();
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
