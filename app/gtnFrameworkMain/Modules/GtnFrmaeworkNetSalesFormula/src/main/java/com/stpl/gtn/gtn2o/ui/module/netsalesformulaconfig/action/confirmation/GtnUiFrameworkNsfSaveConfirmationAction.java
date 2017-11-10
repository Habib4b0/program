/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfSaveAction;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfSaveConfirmationAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		//
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(" Save Confirmation ");
		alertParams.add(" Save record "
				+ GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId + "formulaName").getStringFromField() + "?");
		List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig customSaveAction = new GtnUIFrameWorkActionConfig();
		customSaveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customSaveAction.addActionParameter(GtnUiFrameworkNsfSaveAction.class.getName());
		customSaveAction.addActionParameter(viewId);

		onSucessActionConfig.add(customSaveAction);
		alertParams.add(onSucessActionConfig);
		confirmationActionConfig.setActionParameterList(alertParams);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmationActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
