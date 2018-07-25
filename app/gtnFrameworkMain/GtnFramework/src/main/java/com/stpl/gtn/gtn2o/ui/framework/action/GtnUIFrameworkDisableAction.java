package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

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
		if (componentIdList.get(0).toString().equals("disableReadOnly")) {
			GtnUIFrameworkBaseComponent component1 = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIdList.get(1).toString(),componentId);
			AbstractComponent component = component1.getComponent();
			if (component instanceof HorizontalLayout ) {
				HorizontalLayout horizontalLayout = (HorizontalLayout) component;
				horizontalLayout.setEnabled(false);
				TextField textField = (TextField) horizontalLayout.getComponent(0);
				textField.setReadOnly(true);
				textField.setEnabled(false);
			}
			return;
		}
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