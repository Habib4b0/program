package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameWorkCGrpLoadDataTableAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameWorkCGrpLoadDataTableAction.class);

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) GtnUIFrameworkGlobalUI
				.getVaadinComponent("cGrpsearchResultTable").getData();
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		boolean isActiveFlag = Boolean.TRUE;
		String queryName;
		if ("gtnAudictSearch01".equals(componentId)) {
			queryName = "cGrpAuditSearchQuery";
		} else {
			queryName = "cGrpSearchQuery";
		}
		tableLogic.getComponentConfig().getGtnPagedTableConfig().setQueryName(queryName);
		tableLogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), isActiveFlag);
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnUIFrameWorkCGrpLoadDataTableAction");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
