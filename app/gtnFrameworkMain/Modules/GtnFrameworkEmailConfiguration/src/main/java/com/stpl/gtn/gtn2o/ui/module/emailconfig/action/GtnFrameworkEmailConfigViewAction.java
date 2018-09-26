package com.stpl.gtn.gtn2o.ui.module.emailconfig.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkEmailConfigViewAction
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
		saveForecastConfiguration(parameters);
	}

	private void saveForecastConfiguration(List<Object> parameters) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).setComponentEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString()).setComponentEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString()).setComponentEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString()).setComponentEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString()).setComponentEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString()).setComponentEnable(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(7).toString()).setVisible(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(8).toString()).setVisible(false);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(9).toString()).setVisible(true);
	}

	@Override
	public GtnFrameworkEmailConfigViewAction createInstance() {
		return this;
	}
}
