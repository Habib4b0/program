package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkIfpTabChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkIfpTabChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkIfpTabChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getTabSheetSelectedTabIndex() == 2) {
				ExtPagedTable<?> ifpItemsTabResultDataTable = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("ifpItemsTabResultDataTable").getExtPagedTable();
				if (ifpItemsTabResultDataTable.getData() != null) {
					GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
					GtnUIFrameworkPagedTableLogic logic = (GtnUIFrameworkPagedTableLogic) ifpItemsTabResultDataTable
							.getContainerLogic();
					logic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);
					ifpItemsTabResultDataTable.setData(null);
				}

			}
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
