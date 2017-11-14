package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkResetAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = (gtnUIFrameWorkActionConfig.getActionParameterList());
		String resetMessageHeader = (String) params.get(0);
		String resetMessageBody = (String) params.get(1);

		configureParams(gtnUIFrameWorkActionConfig);
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.addActionParameter(resetMessageHeader);
		confirmActionConfig.addActionParameter(resetMessageBody);
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
		resetActionConfig.addActionParameter(params.get(2));
		if (params.size() > 3) {
			resetActionConfig.addActionParameter(params.get(3));
		}
		successActionConfigList.add(resetActionConfig);
		confirmActionConfig.addActionParameter(successActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);

	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
