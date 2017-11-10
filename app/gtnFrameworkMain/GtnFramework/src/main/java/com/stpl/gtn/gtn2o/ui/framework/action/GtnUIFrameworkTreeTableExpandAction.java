/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abishek.Ram
 */
public class GtnUIFrameworkTreeTableExpandAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(
				String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(0)), componentId);
		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);

		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();
		int expandLevelNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString(),
						componentId)
				.getIntegerFromField();
		tableLogic.expandCollapse(expandLevelNo);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkTreeTableExpandAction();
	}

}