package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkPricingTableExcelExportAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = (GtnUIFrameworkExcelButtonConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(1);
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		String tableId = gtnExcelButtonConfig.getExportTableId();
		GtnUIFrameworkPagedTableLogic tableLogic = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId, componentId)
				.getLogicFromPagedDataTable();
		gtnExcelButtonConfig
				.setServiceInput(new Object[] { tableLogic.getRecordHeader(), GtnUIFrameworkGlobalUI.getCurrentUser(),
						GtnUIFrameworkGlobalUI.getCurrentSessionBean().getSessionId() });
		excelAction.addActionParameter(gtnExcelButtonConfig);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, excelAction);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
