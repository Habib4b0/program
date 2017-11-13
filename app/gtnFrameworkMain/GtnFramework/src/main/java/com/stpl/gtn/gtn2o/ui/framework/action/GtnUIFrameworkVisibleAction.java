package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.Component;

public class GtnUIFrameworkVisibleAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> param = gtnUIFrameWorkActionConfig.getActionParameterList();
		boolean visible = (boolean) param.get(0);
		List<String> componentIdList = (List<String>) param.get(1);
		for (String componentId1 : componentIdList) {
			Component component = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId1, componentId);
			if (component != null) {
				component.setVisible(visible);
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkVisibleAction();
	}

}