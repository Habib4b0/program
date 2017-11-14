/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkErrorLabelAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> mainParameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent errorLabelBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(mainParameters.get(1).toString());
		errorLabelBaseComponent.setVisible(false);
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		processDataBean.setSuccess(true);
		List<String> parameters = (List<String>) mainParameters.get(2);
		List<String> messages = (List<String>) mainParameters.get(3);
		for (int i = 0; i < parameters.size(); i++) {
			if (componentId.equals(parameters.get(i))) {
				errorLabelBaseComponent.setVisible(true);
				errorLabelBaseComponent.setPropertyValue(messages.get(i));
				processDataBean.setSuccess(false);
				break;
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
