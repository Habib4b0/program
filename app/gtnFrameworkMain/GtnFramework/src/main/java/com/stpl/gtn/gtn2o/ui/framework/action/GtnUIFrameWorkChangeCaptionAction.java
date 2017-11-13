/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameWorkChangeCaptionAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<String> propertyIdList = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(0);
		List<String> captionList = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		for (int i = 0; i < propertyIdList.size(); i++) {
			String propertyId = propertyIdList.get(i);
			Component component = GtnUIFrameworkGlobalUI.getVaadinComponent(propertyId, componentId);
			if (component instanceof Button) {
				Button vaadinField = (Button) component;
				vaadinField.setCaption(captionList.get(i));
			}

		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}