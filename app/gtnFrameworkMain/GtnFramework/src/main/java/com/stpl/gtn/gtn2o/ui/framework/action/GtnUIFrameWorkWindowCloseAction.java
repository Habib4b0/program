/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.server.Page;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnUIFrameWorkWindowCloseAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		Page.getCurrent().getJavaScript().execute("window.open('','_parent','');window.close(); ");

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameWorkWindowCloseAction();
	}

}