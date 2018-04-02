package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnFrameworkCfpTabChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCfpTabChangeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkCfpTabChangeAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {

			if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getTabSheetSelectedTabIndex() == 2) {
				ExtPagedTable<?> cfpCompaniesTabResultDataTable = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("cfpCompaniesTabResultDataTable").getExtPagedTable();
				if (cfpCompaniesTabResultDataTable.getData() != null) {
					GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(false);
					GtnUIFrameworkPagedTableLogic logic = (GtnUIFrameworkPagedTableLogic) cfpCompaniesTabResultDataTable
							.getContainerLogic();
					logic.startSearchProcess(gtnUIFrameWorkActionConfig.getFieldValues(), true);
					cfpCompaniesTabResultDataTable.setData(null);
				}

			}
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		} finally {
			GtnFrameworkCfpValueChangeManager.setValueChangeAllowed(true);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
