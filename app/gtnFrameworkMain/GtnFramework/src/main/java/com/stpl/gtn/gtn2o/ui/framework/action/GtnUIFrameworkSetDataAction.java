/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameworkSetDataAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> selectParam = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultTableId = (String) selectParam.get(0);
		String viewId = (String) selectParam.get(1);
		GtnUIFrameworkComponentData componenetData = GtnUIFrameworkGlobalUI.getVaadinComponentData(resultTableId,
				componentId);
		ExtFilterTable resultTable = (ExtFilterTable) componenetData.getCustomData();

		GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(viewId, componentId)
				.getComponentData();
		idComponentData.setCustomData(resultTable.getValue());

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkSetDataAction();
	}

}