/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.Component;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameworkEnableDisableAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<String> componentIdList = gtnUIFrameWorkActionConfig.getFieldValues();
		for (String componentId1 : componentIdList) {

			Component component = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId1, componentId);
			component.setEnabled(!component.isEnabled());
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}