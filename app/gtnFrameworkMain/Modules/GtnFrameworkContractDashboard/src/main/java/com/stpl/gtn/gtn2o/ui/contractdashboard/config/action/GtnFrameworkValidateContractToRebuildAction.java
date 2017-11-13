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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkValidateContractToRebuildAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

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
		if (bean.getIntegerPropertyByIndex(4) != 1) {
			GtnUIFrameWorkActionConfig failureActionConfig = new GtnUIFrameWorkActionConfig();
			failureActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);
			failureActionConfig.addActionParameter(parameters.get(2));
			String message = parameters.get(3).toString().replace(parameters.get(4).toString(),
					bean.getStringPropertyByIndex(0));
			failureActionConfig.addActionParameter(message);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, failureActionConfig);
			return;
		}
		GtnUIFrameworkActionExecutor.executeActionList(componentId,
				(List<GtnUIFrameWorkActionConfig>) parameters.get(5));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
