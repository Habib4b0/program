package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkLoadDataTableAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = (gtnUIFrameWorkActionConfig.getActionParameterList());
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData((String) params.get(0), componentId);
		if (componentData != null) {
			GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
			if (params.size() > 1) {
				tableLogic.setExtraParameter(params.get(1));
			}
			if (gtnUIFrameWorkActionConfig.getFieldDescription() == null) {
				tableLogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);
			} else {
				tableLogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(),
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
