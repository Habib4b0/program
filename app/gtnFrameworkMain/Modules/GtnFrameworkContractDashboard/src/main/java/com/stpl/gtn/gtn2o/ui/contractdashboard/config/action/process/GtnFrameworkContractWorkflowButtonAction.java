/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkContractWorkflowButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_BACK_BTN)
				.setVisible(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_CLOSE_BTN)
				.setVisible(true);
		boolean rejected = GtnFrameworkCommonStringConstants.REJECTED
				.equalsIgnoreCase(processDataBean.getWorkflowBean().getWorkflowStatusValue());
		boolean pending = GtnFrameworkCommonStringConstants.PENDNG
				.equalsIgnoreCase(processDataBean.getWorkflowBean().getWorkflowStatusValue());
		boolean withdrawn = GtnFrameworkCommonStringConstants.WITHDRAWN
				.equalsIgnoreCase(processDataBean.getWorkflowBean().getWorkflowStatusValue());
		boolean canceled = GtnFrameworkCommonStringConstants.CANCELLED
				.equalsIgnoreCase(processDataBean.getWorkflowBean().getWorkflowStatusValue());
		boolean creater = GtnFrameworkCommonStringConstants.CREATOR
				.equalsIgnoreCase(processDataBean.getWorkflowBean().getUserType());
		boolean approver = GtnFrameworkCommonStringConstants.APPROVER
				.equalsIgnoreCase(processDataBean.getWorkflowBean().getUserType());
		boolean pendingAndApprover = pending && approver;
		boolean createrAndRejOrWithdrawn = creater && (rejected || withdrawn);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_SUBMIT_BTN)
				.setEnable(createrAndRejOrWithdrawn);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_SUBMIT_BTN)
				.setVisible(createrAndRejOrWithdrawn);
		processDataBean.setViewMode(!createrAndRejOrWithdrawn);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_REJECT_BTN)
				.setVisible(pendingAndApprover);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_APPROVE_BTN)
				.setVisible(pendingAndApprover);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_CANCEL_BTN)
				.setVisible(pendingAndApprover);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnWsContractDashboardContants.CONTRACT_WITHDRAW_BTN)
				.setVisible(creater && (pending || canceled));
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent("gtnAddLineButtonlayout")
                                .setVisible(createrAndRejOrWithdrawn);
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent("gtnRemoveLineButtonlayout")
                                .setVisible(createrAndRejOrWithdrawn);
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent("gtnCopyLineButtonlayout")
                                .setVisible(createrAndRejOrWithdrawn);
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}