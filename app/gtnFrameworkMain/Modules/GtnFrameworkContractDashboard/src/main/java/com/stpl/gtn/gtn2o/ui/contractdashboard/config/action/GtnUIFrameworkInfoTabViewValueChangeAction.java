package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkInfoTabViewValueChangeAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> mainParameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<String> parameters = (List<String>) mainParameters.get(1);
		String prefixValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(mainParameters.get(4).toString())
				.getStringFromField();
		if (prefixValue.isEmpty()) {
			prefixValue = componentId;
		}
		prefixValue = prefixValue.replace(" ", "");
		String prefix = prefixValue + "_";
		String postfix = "_";
		String componentValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getStringFromField();
		if (componentValue.isEmpty()) {
			componentValue = componentId;
		}
		componentValue = componentValue.replace(" ", "");
		int start = Integer.parseInt(String.valueOf(mainParameters.get(2)));
		int end = Integer.parseInt(String.valueOf(mainParameters.get(3)));
		for (int i = start; i < parameters.size() - end; i++) {
			String parameter = parameters.get(i);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameter)
					.setVisible(parameter.contains(prefix + componentValue + postfix));
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
