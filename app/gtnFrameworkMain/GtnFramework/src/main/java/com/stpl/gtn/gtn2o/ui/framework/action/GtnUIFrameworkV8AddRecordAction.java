package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

public class GtnUIFrameworkV8AddRecordAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultGridId = parameters.get(1).toString();

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(resultGridId,
				componentId);
		PagedGrid pagedGrid = (PagedGrid) componentData.getCustomData();
		Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();
		GtnWsRecordBean recordBean = new GtnWsRecordBean();

		GtnUIFrameworkBaseComponent availableGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(0).toString());
		GtnUIFrameworkComponentData availableGridData = (GtnUIFrameworkComponentData) availableGrid.getData();
		PagedGrid availablePagedGrid = availableGridData.getPagedGrid();
		Set<GtnWsRecordBean> recordBeans = availableGridData.getPagedGrid().getValue();
		GtnWsRecordBean selectedRecord = recordBeans.isEmpty() ? null : recordBeans.iterator().next();

		recordBean.setRecordHeader(selectedRecord.getRecordHeader());
		recordBean.setProperties(selectedRecord.getProperties());

		if (parameters.size() > 2) {
			for (int i = 2; i < parameters.size(); i++) {
				recordBean.addAdditionalProperty(parameters.get(i));
			}

		}

		if (grid.getDataProvider() instanceof ListDataProvider) {
			pagedGrid.addItem(recordBean);
		} else {
			grid.setItems(recordBean);
		}
		availablePagedGrid.removeItem(selectedRecord);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
