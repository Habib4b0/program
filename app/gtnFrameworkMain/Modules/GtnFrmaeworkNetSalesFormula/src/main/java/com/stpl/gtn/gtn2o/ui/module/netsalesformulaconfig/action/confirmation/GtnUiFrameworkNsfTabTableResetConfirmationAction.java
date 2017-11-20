package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfTabTableResetAction;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUiFrameworkNsfTabTableResetConfirmationAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		String resetMsg = (String) actionParemeterList.get(2);
		boolean isSalesBasis = (boolean) actionParemeterList.get(3);
		GtnUIFrameWorkActionConfig resetConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		resetConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(" Reset Confirmation ");
		alertParams.add(resetMsg);
		List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig customResetAction = new GtnUIFrameWorkActionConfig();
		customResetAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customResetAction.addActionParameter(GtnUiFrameworkNsfTabTableResetAction.class.getName());
		customResetAction.addActionParameter(viewId);
		customResetAction.addActionParameter(isSalesBasis);

		onSucessActionConfig.add(customResetAction);
		alertParams.add(onSucessActionConfig);
		resetConfirmationActionConfig.setActionParameterList(alertParams);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetConfirmationActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
