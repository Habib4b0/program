package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

public class GtnReportComparisonProjectionAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> paramsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<GtnWsRecordBean> selectedRecordList = new ArrayList<>();
		GtnUIFrameworkBaseComponent availableGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(paramsList.get(1).toString());
		GtnUIFrameworkComponentData availableGridData = (GtnUIFrameworkComponentData) availableGrid.getData();
		Set<GtnWsRecordBean> recordBeans = availableGridData.getPagedGrid().getValue();
		GtnWsRecordBean selectedRow = recordBeans.isEmpty() ? null : recordBeans.iterator().next();

		GtnUIFrameworkBaseComponent selectedGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(paramsList.get(2).toString());
		PagedGrid pagedGrid = (PagedGrid) selectedGrid.getComponentData().getCustomData();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
		if (grid.getDataProvider() instanceof ListDataProvider) {
			ListDataProvider<GtnWsRecordBean> selectedTableItems = (ListDataProvider<GtnWsRecordBean>) grid
					.getDataProvider();
			if (!selectedTableItems.getItems().isEmpty()) {
				selectedRecordList = (List<GtnWsRecordBean>) selectedTableItems.getItems();
			}

		}
		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		if (selectedRow == null) {
			alertAction.addActionParameter("Error");
			alertAction.addActionParameter("Please select a projection to add");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
		} else if (selectedRecordList.size() == 5) {
			alertAction.addActionParameter("Error");
			alertAction.addActionParameter(
					"Cannot Add more than Five items.  Please select five records or below and try again.");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertAction);
		} else {
			GtnUIFrameWorkActionConfig addRecordAction = new GtnUIFrameWorkActionConfig();
			addRecordAction.setActionType(GtnUIFrameworkActionType.V8_ADD_RECORD_ACTION);
			List<Object> actionParamsList = new ArrayList<>();
			actionParamsList.add(paramsList.get(1).toString());
			actionParamsList.add(paramsList.get(2).toString());
			addRecordAction.setFieldValues(Arrays.asList("projectionName", "description", "marketType",
					"contractHolder", "contract", "brand"));
			addRecordAction.setActionParameterList(actionParamsList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, addRecordAction);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
