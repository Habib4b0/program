/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.TabSheet;

/**
 *
 * @author Harlin.Mani
 */
public class GtnUIFrameworkNextTabAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		TabSheet tabsheet = (TabSheet) GtnUIFrameworkGlobalUI
				.getVaadinComponent((String) gtnUIFrameWorkActionConfig.getActionParameterList().get(0), componentId);
		int currentTabIndex = tabsheet.getTabPosition(tabsheet.getTab(tabsheet.getSelectedTab()));
		if (currentTabIndex == tabsheet.getComponentCount() - 1) {
			tabsheet.setSelectedTab(0);
		} else {
			tabsheet.setSelectedTab(currentTabIndex + 1);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}