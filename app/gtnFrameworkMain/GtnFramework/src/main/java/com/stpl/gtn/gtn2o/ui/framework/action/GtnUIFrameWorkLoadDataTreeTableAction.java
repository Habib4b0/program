package com.stpl.gtn.gtn2o.ui.framework.action;

import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;

public class GtnUIFrameWorkLoadDataTreeTableAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String componentIds = gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString();

		configureParams(gtnUIFrameWorkActionConfig);

		AbstractComponent resultLayoutInMap = GtnUIFrameworkGlobalUI.getVaadinComponent(componentIds, componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) resultLayoutInMap.getData();
		GtnUIFrameworkPagedTreeTableComponent componentClassObj = (GtnUIFrameworkPagedTreeTableComponent) componentData
				.getCustomData();

		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);
		GtnUIFrameworkPagedTreeTableConfig tableConfig = componentData.getCurrentComponentConfig()
				.getGtnPagedTreeTableConfig();
		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();
		componentClassObj.reloadComponent(gtnUIFrameWorkActionConfig, componentIds, componentId);

		tableLogic.startGenerateProcess(gtnUIFrameWorkActionConfig, tableConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}