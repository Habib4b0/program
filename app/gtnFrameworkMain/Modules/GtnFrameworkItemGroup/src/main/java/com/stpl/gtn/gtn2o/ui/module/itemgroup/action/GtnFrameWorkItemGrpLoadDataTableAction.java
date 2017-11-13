package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameWorkItemGrpLoadDataTableAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameWorkItemGrpLoadDataTableAction.class);

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkPagedTableLogic tableLogic = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("itemGrpsearchResultTable").getLogicFromPagedDataTable();
		boolean isActiveFlag = Boolean.TRUE;
		String queryName;
		if ("gtnAudictSearch01".equals(componentId)) {
			queryName = "itemGrpAuditSearchQuery";
		} else {
			queryName = "itemGrpSearchQuery";
		}
		GtnUIFrameworkComponentConfig componentConfig =  GtnUIFrameworkGlobalUI
				.getVaadinComponentData("itemGrpsearchResultTable").getCurrentComponentConfig();
		componentConfig.getGtnPagedTableConfig().setQueryName(queryName);
		tableLogic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), isActiveFlag);
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnUIFrameWorkItemGrpLoadDataTableAction");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
