/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardProcessBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractWorkflowBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.request.workflow.GtnWsCommonWorkflowRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkContractDashBoardConfirmSubmitAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
    
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            boolean var = contractPriceProtectionStartDateAlert(componentId);
            if (!var) {
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setContractDashboardBean(processDataBean);
		cdRequest.setUserId(processDataBean.getProcessBean().getUserId());
		cdRequest.setSessionId(processDataBean.getProcessBean().getSessionId());
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		request.setGtnWsContractDashboardRequest(cdRequest);
		String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
		generalWSRequest.setUserId(userId);
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		request.setGtnWsGeneralRequest(generalWSRequest);
		GtnUIFrameworkWebserviceResponse newResponse = wsclient.callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.SUBMIT_CONTRACT_DASHBOARD,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsContractDashboardResponse cdResponse = newResponse.getGtnWsContractDashboardResponse();
		if (cdResponse.isSuccess()) {
			GtnWsCommonWorkflowRequest workflowRequest = new GtnWsCommonWorkflowRequest();

			getModifiedWorkFlowRequest(workflowRequest, processDataBean);
			request.setGtnWSCommonWorkflowRequest(workflowRequest);
			newResponse = wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_WS_CONTRACT_WORKFLOW_SERVICE
							+ GtnWsContractDashboardContants.SUBMIT_CONTRACT,
					GtnFrameworkCommonStringConstants.GTN_BPM, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			String workflowId = newResponse.getGtnWSCommonWorkflowResponse().getWorkflowId();
			processDataBean.getWorkflowBean().setWorkflowId(workflowId);
			if (!"Not Saved".equals(workflowId)) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).setEnable(false);
				GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
						cdResponse.getMessageHeader(), cdResponse.getMessage().replace("[WORKFLOW_ID]", workflowId));
			}
			return;
		}
		GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION,
				cdResponse.getMessageHeader(), cdResponse.getMessage());
            }
	}

	private void getModifiedWorkFlowRequest(GtnWsCommonWorkflowRequest workflowRequest,
			GtnWsContractDashboardSessionBean processDataBean) {
		GtnUIFrameworkContractWorkflowUpdateAction propertyFile=new GtnUIFrameworkContractWorkflowUpdateAction();
		java.util.Properties path =propertyFile.getPropertyFile(System.getProperty(GtnFrameworkCommonStringConstants.GTNFRAMEWORK_BASE_PATH_PROPERTY));
		workflowRequest.setContractBean(processDataBean.getWorkflowBean());
		workflowRequest.setPersistanceId(processDataBean.getPersistanceId());
		workflowRequest.setModuleKey("Contract_WorkflowId");
		workflowRequest.setDefaultValue("ForecastingWorkflow.ContractSubmissionWorkflow");

		workflowRequest.setModuleName(GtnWsBpmCommonConstants.CONTRACT_MASTER);
		workflowRequest.setWorkflowGeneratorPath(path.getProperty("Workflowpath"));
		if (workflowRequest.getContractBean() == null) {
			GtnWsContractWorkflowBean workflowMaster = new GtnWsContractWorkflowBean();
			workflowMaster.setCreatedBy(Integer.parseInt(processDataBean.getProcessBean().getUserId()));
			workflowMaster.setCreatedDate(new Date());
			workflowMaster.setNotes("");
			workflowMaster.setNoOfApprovals(1);
			workflowMaster.setApprovalLevel(1);
			workflowMaster.setContractId(processDataBean.getProcessBean().getContractId());
			workflowMaster.setContractStructure(createContractStructure(processDataBean.getProcessBean()));
			workflowRequest.setContractBean(workflowMaster);
			processDataBean.setWorkflowBean(workflowMaster);
		}
	}

	private String createContractStructure(GtnWsContractDashboardProcessBean processBean) {
		return "C" + processBean.getCfpContractId() + "|I" + processBean.getIfpContractId() + "|P"
				+ processBean.getPsContractId() + "|R" + processBean.getRsContractId();
	}
        public boolean contractPriceProtectionStartDateAlert(String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		GtnWsContractDashboardSessionBean contractPsInfoBean = new GtnWsContractDashboardSessionBean();

		gtnWsGeneralRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		gtnWsGeneralRequest.setSessionId(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		GtnWsContractDashboardRequest getGtnWsContractGeneralRequest = new GtnWsContractDashboardRequest();
		List<Object> inpList = new ArrayList<>();
		inpList.add(GtnUIFrameworkGlobalUI.getCurrentUser());
		inpList.add(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inpList);
		getGtnWsContractGeneralRequest.setContractDashboardBean(contractPsInfoBean);
		request.setGtnWsContractDashboardRequest(getGtnWsContractGeneralRequest);
		GtnUIFrameworkWebserviceResponse responseStartDateContract = wsclient.callGtnWebServiceUrl(
				"/" + GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE+ "/contractPriceProtectionStartDateAlert", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		int responseResult = Integer.parseInt(String.valueOf(responseStartDateContract.getOutBountData()[0] != null ? responseStartDateContract.getOutBountData()[0] : "0"));
		if (responseResult > 1) {
			GtnUIFrameWorkActionConfig startDateAlertActionConfig = new GtnUIFrameWorkActionConfig();
			startDateAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertMsgParamList = new ArrayList<>();
			alertMsgParamList.add("Error");
			alertMsgParamList.add(
					"For the same Item, the ‘Price Protection Start Date’ field cannot have the same month/year combination across multiple records.");
			startDateAlertActionConfig.setActionParameterList(alertMsgParamList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, startDateAlertActionConfig);
			return true;
		}
		return false;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
