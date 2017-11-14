package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkInfoTabLevelValueChangeAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent viewBastComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		String viewValue = viewBastComponent.getStringFromField();
		boolean viewChange = viewValue.equals(parameters.get(2).toString());
		if (viewChange) {
			viewBastComponent.setPropertyValue(parameters.get(3).toString());
			return;
		}
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, (GtnUIFrameWorkActionConfig) parameters.get(4));
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString()).setVisible(false);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
