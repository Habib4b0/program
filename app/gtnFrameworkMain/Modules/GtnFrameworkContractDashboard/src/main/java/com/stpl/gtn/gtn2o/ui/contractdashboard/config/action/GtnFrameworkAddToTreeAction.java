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
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkAddToTreeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkSetter setter = new GtnUIFrameworkSetter();
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		GtnUIFrameworkBaseComponent cdTreeBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(2).toString());
		Object selectedTableValue = tableBaseComponent.getValueFromComponent();
		if (selectedTableValue == null) {
			setter.showMessageBox(componentId, GtnUIFrameworkActionType.ALERT_ACTION,
					GtnFrameworkCommonStringConstants.ERROR, parameters.get(3).toString());
			return;
		}
		Object selectedTreeValue = null;
		if (!cdTreeBaseComponent.getItemsFromDataTable().isEmpty()) {
			selectedTreeValue = cdTreeBaseComponent.getValueFromComponent();
		}
		GtnUIFrameWorkActionConfig addToTreeCommonActionConfig = new GtnUIFrameWorkActionConfig();
		addToTreeCommonActionConfig.addActionParameter(GtnFrameworkAddToTreeCommonAction.class.getName());
		addToTreeCommonActionConfig.addActionParameter(parameters.get(2));
		addToTreeCommonActionConfig.addActionParameter(selectedTableValue);
		addToTreeCommonActionConfig.addActionParameter(selectedTreeValue);
		addToTreeCommonActionConfig.addActionParameter(parameters.get(4));
		addToTreeCommonActionConfig.addActionParameter(parameters.get(5));
		addToTreeCommonActionConfig.addActionParameter(parameters.get(6));
		addToTreeCommonActionConfig.addActionParameter(parameters.get(7));
		GtnUIFrameworkActionExecutor.executeCustomAction(componentId, addToTreeCommonActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
