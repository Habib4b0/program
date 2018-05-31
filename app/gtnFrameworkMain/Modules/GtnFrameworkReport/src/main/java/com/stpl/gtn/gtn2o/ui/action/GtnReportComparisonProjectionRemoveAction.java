package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnReportComparisonProjectionRemoveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnUIFrameworkComponentData selectedGridData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParamsList.get(1).toString());
		PagedGrid pagedGrid = (PagedGrid) selectedGridData.getCustomData();
		Set<GtnWsRecordBean> recordBean = pagedGrid.getValue();
		GtnWsRecordBean selectedRecord = recordBean.isEmpty() ? null : recordBean.iterator().next();
		if (selectedRecord == null) {
			GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
			alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			alertAction.addActionParameter("Error");
			alertAction.addActionParameter("Please select a projection to remove.");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
		} else {
			pagedGrid.removeItem(selectedRecord);

			GtnUIFrameworkComponentData availableGridData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(actionParamsList.get(2).toString());
			PagedGrid availablePagedGrid = (PagedGrid) availableGridData.getCustomData();
			availablePagedGrid.addItem(selectedRecord);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
