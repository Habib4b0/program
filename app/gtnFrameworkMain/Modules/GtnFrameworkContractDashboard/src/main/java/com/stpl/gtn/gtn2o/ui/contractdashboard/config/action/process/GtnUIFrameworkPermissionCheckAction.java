package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.workflow.GtnWsCommonWorkflowRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkPermissionCheckAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsContractDashboardSessionBean sharedBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(sharedBean.getProcessBean().getUserId());
		generalWSRequest.setSessionId(sharedBean.getProcessBean().getSessionId());
		request.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsCommonWorkflowRequest workflowRequest = new GtnWsCommonWorkflowRequest();
		workflowRequest.setModuleKey("Contract_WorkflowId");
		workflowRequest.setDefaultValue("ForecastingWorkflow.ContractSubmissionWorkflow");
		workflowRequest.setContractBean(sharedBean.getWorkflowBean());
		request.setGtnWSCommonWorkflowRequest(workflowRequest);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_WS_CONTRACT_WORKFLOW_SERVICE
						+ GtnWsContractDashboardContants.CHECK_VALID_USER,
				GtnFrameworkCommonStringConstants.GTN_BPM, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		sharedBean.setHasPermission(response.getGtnWSCommonWorkflowResponse().isHasPermission());
		sharedBean.setPersistanceId(response.getGtnWSCommonWorkflowResponse().getPersistanceId());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
