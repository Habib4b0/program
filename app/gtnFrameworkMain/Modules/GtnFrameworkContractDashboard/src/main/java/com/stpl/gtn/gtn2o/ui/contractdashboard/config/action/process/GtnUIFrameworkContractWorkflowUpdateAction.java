package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.request.workflow.GtnWsCommonWorkflowRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkContractWorkflowUpdateAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

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

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(sharedBean.getProcessBean().getUserId());
		generalWSRequest.setSessionId(sharedBean.getProcessBean().getSessionId());
		request.setGtnWsGeneralRequest(generalWSRequest);

		GtnWsCommonWorkflowRequest workflowRequest = new GtnWsCommonWorkflowRequest();
		workflowRequest.setPersistanceId(sharedBean.getPersistanceId());
		workflowRequest.setModuleKey(GtnFrameworkCommonStringConstants.CONTRACT_WORKFLOW_ID);
		workflowRequest.setDefaultValue("ForecastingWorkflow.ContractSubmissionWorkflow");
		StringBuilder filePath = new StringBuilder(System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH));
		filePath.append(GtnFrameworkCommonStringConstants.WORKFLOW_ID_XML_PATH);

		workflowRequest.setModuleName(GtnWsBpmCommonConstants.CONTRACT_MASTER);
		workflowRequest.setWorkflowGeneratorPath(filePath.toString());
		workflowRequest.setContractBean(sharedBean.getWorkflowBean());
		workflowRequest.getContractBean().setModifiedBy(Integer.parseInt(sharedBean.getProcessBean().getUserId()));
		workflowRequest.getContractBean().setWorkflowStatusValue(parameters.get(1).toString());
		if (GtnFrameworkCommonStringConstants.APPROVED
				.equals(workflowRequest.getContractBean().getWorkflowStatusValue())) {
			workflowRequest.getContractBean().setApprovedBy(workflowRequest.getContractBean().getModifiedBy());
			workflowRequest.getContractBean().setApprovedDate(new Date());
		}
		request.setGtnWSCommonWorkflowRequest(workflowRequest);

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(parameters.get(2).toString(),
				GtnFrameworkCommonStringConstants.GTN_BPM, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		String workflowId = response.getGtnWSCommonWorkflowResponse().getWorkflowId();

		if ("Not Saved".equals(workflowId)) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
					parameters.get(5).toString(), parameters.get(6).toString());
			return;
		}

		if (parameters.get(1).toString().equals(GtnFrameworkCommonStringConstants.APPROVED)) {
			GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
			cdRequest.setContractDashboardBean(sharedBean);
			cdRequest.setUserId(sharedBean.getProcessBean().getUserId());
			cdRequest.setSessionId(sharedBean.getProcessBean().getSessionId());
			request.setGtnWsContractDashboardRequest(cdRequest);
			wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
							+ GtnWsContractDashboardContants.APPROVE_CONTRACT_DASHBOARD,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		}
		if (parameters.get(1).toString().equals(GtnFrameworkCommonStringConstants.REJECTED)
				|| parameters.get(1).toString().equals(GtnFrameworkCommonStringConstants.CANCELED)) {
			GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
			cdRequest.setContractDashboardBean(sharedBean);
			cdRequest.setUserId(sharedBean.getProcessBean().getUserId());
			cdRequest.setSessionId(sharedBean.getProcessBean().getSessionId());
			request.setGtnWsContractDashboardRequest(cdRequest);
			wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
							+ GtnWsContractDashboardContants.REJECT_CANCEL_CONTRACT_DASHBOARD,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_REJECT_BTN)
				.setEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_APPROVE_BTN)
				.setEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_CANCEL_BTN)
				.setEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_WITHDRAW_BTN)
				.setEnable(false);
		GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
				parameters.get(3).toString(), parameters.get(4).toString().replace("[WORKFLOW_ID]", workflowId));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
