package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class GtnUIFrameworkEnableAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> componentIdList = gtnUIFrameWorkActionConfig.getActionParameterList();
		if (componentIdList.get(0).toString().equals("disableReadOnly")) {
		Component component = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(componentIdList.get(1)),
				componentId).getComponent();
				if (component instanceof HorizontalLayout) {
					HorizontalLayout horizontalLayout = (HorizontalLayout) component;
					horizontalLayout.setEnabled(true);
					TextField textField = (TextField) horizontalLayout.getComponent(0);
					textField.setReadOnly(false);
					textField.setEnabled(true);
				}
				return;
			}
			
			
		for (Object componentId1 : componentIdList) {
			Component component = GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId1), componentId);
			if (component != null) {
				component.setEnabled(true);
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}