/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameWorkCMViewAction implements GtnUIFrameWorkAction,GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameWorkCMViewAction.class);

	@Override

	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside Configure Parameters");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("parentCompanyattachResultTable", componentId).getComponentData();
		GtnUIFrameworkBaseComponent resultTableidentifierComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("identifierattachResultTable", componentId);
		GtnUIFrameworkComponentData componentDataidentifier = resultTableidentifierComponent.getComponentData();
		GtnUIFrameworkComponentData componentDataTrade = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("tradeClassattachResultTable", componentId).getComponentData();

		ExtFilterTable resultTable = (ExtFilterTable) componentData.getCustomData();
		ExtFilterTable resultTableidentifier = (ExtFilterTable) componentDataidentifier.getCustomData();
		ExtFilterTable resultTableTrade = (ExtFilterTable) componentDataTrade.getCustomData();
		resultTable.setEditable(false);
		handleIdentifierTableColumn(resultTableidentifierComponent);
		resultTableidentifier.setEditable(false);
		resultTableTrade.setEditable(false);

		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
				.getVaadinComponent("notesTab");
		notesTab.removeAndDisablingComponents(true);
	}

	private void handleIdentifierTableColumn(GtnUIFrameworkBaseComponent resultTableidentifier) {
		resultTableidentifier.setTableColumns(GtnFrameworkCompanyStringContants.getIdentifierTabTableViewColumnList());
		resultTableidentifier
				.setTableColumnHeaders(GtnFrameworkCompanyStringContants.getIdentifierTabTableViewHeaderList());
		resultTableidentifier.resetFilter();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
