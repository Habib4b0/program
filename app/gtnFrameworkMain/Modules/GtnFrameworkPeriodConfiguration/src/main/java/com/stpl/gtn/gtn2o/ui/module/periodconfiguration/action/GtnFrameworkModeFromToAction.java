package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkModeFromToAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		modechange(parameters, componentId);
	}

	private void modechange(List<Object> parameters, String componentId) throws GtnFrameworkGeneralException {

		String modeFromValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
				.getCaptionFromComboBox();
		if ("Auto".equalsIgnoreCase(modeFromValue)) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString()).setComponentEnable(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString()).setComponentVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString()).setComponentVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString()).setComponentVisible(true);

		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString()).setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString()).setComponentVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString()).setComponentVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString()).setComponentVisible(true);

		}

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(Arrays.asList(parameters.get(2).toString(), parameters.get(3).toString(),
				parameters.get(4).toString(),parameters.get(5).toString()));
		params.add(Arrays.asList(null, null, "",""));
		resetActionConfig.setActionParameterList(params);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);

	}

	@Override
	public GtnFrameworkModeFromToAction createInstance() {
		return new GtnFrameworkModeFromToAction();
	}

}
