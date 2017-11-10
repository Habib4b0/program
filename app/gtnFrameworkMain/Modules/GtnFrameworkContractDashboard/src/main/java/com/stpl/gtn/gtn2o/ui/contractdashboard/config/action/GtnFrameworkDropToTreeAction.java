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
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkDropToTreeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

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
		Object eventParam = gtnUIFrameWorkActionConfig.getEventParameter();
		if (eventParam == null || !(eventParam instanceof List)) {
			return;
		}
		List<Object> itemIdList = (List<Object>) eventParam;
		if (itemIdList.size() < 2) {
			return;
		}
		Object selectedTableValue = itemIdList.get(0);
		Object selectedTreeValue = itemIdList.get(1);
		GtnUIFrameWorkActionConfig dropToTreeCommonActionConfig = new GtnUIFrameWorkActionConfig();
		dropToTreeCommonActionConfig.addActionParameter(GtnFrameworkAddToTreeCommonAction.class.getName());
		dropToTreeCommonActionConfig.addActionParameter(parameters.get(2));
		dropToTreeCommonActionConfig.addActionParameter(selectedTableValue);
		dropToTreeCommonActionConfig.addActionParameter(selectedTreeValue);
		dropToTreeCommonActionConfig.addActionParameter(parameters.get(4));
		dropToTreeCommonActionConfig.addActionParameter(parameters.get(5));
		dropToTreeCommonActionConfig.addActionParameter(parameters.get(6));
		dropToTreeCommonActionConfig.addActionParameter(parameters.get(7));
		GtnUIFrameworkActionExecutor.executeCustomAction(componentId, dropToTreeCommonActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkDropToTreeAction();
	}

}
