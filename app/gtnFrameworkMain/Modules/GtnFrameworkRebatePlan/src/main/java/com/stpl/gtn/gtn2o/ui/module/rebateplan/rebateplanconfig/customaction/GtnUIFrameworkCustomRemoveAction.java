
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkCustomRemoveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent resultTable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("ruleDetailsattachResultTable");

		if (!resultTable.getItemsFromDataTable().isEmpty()) {
			resultTable.removeItemFromDataTable(
					resultTable.getItemsFromDataTable().get(resultTable.getItemsFromDataTable().size() - 1));
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCustomRemoveAction();
	}

}