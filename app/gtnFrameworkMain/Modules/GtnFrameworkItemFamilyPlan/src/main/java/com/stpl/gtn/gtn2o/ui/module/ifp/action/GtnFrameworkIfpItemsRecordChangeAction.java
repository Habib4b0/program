package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkIfpItemsRecordChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable,GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpItemsRecordChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpItemsRecordChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		GtnUIFrameworkPagedTableLogic logic = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("ifpItemsTabResultDataTable").getLogicFromPagedDataTable();
		logic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
