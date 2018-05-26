/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.GtnUIFrameworkPagedGridLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkLoadDataGridAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = (gtnUIFrameWorkActionConfig.getActionParameterList());
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData((String) params.get(0), componentId);
		if (componentData != null) {
			GtnUIFrameworkPagedGridLogic gridLogic = componentData.getCurrentPageGridLogic();
			if (gtnUIFrameWorkActionConfig.getFieldDescription() == null) {
				gridLogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);
			} else {
				gridLogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(),
						gtnUIFrameWorkActionConfig.getFieldDescription(), true);
			}
		}
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
