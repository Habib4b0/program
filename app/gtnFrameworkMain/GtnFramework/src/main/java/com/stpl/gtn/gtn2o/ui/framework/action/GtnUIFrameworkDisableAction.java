package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.Component;

public class GtnUIFrameworkDisableAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> componentIdList = gtnUIFrameWorkActionConfig.getActionParameterList();
		for (Object componentId1 : componentIdList) {
			Component component = GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId1), componentId);
			if (component != null) {
				component.setEnabled(false);
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}