package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfDeleteAction;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUiFrameworkNsfDeleteConfirmationAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

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
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig();
		confirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(" Confirmation ");
		alertParams.add(" Are you sure you want to Delete the selected Net Sales Formula record ? ");
		List<GtnUIFrameWorkActionConfig> onSucessActionConfig = new ArrayList<>();

		GtnUIFrameWorkActionConfig customDeleteAction = new GtnUIFrameWorkActionConfig();
		customDeleteAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customDeleteAction.addActionParameter(GtnUiFrameworkNsfDeleteAction.class.getName());
		customDeleteAction.addActionParameter(viewId);

		onSucessActionConfig.add(customDeleteAction);
		alertParams.add(onSucessActionConfig);
		confirmationActionConfig.setActionParameterList(alertParams);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmationActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
