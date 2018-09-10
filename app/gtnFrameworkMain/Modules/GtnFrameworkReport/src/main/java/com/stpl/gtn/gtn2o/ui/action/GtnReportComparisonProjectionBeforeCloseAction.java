package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

public class GtnReportComparisonProjectionBeforeCloseAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkBaseComponent selectedGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString());
		PagedGrid pagedGrid = (PagedGrid) selectedGrid.getComponentData().getCustomData();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
		if (grid.getDataProvider() instanceof ListDataProvider) {
			@SuppressWarnings("unchecked")
			ListDataProvider<GtnWsRecordBean> selectedTableItems = (ListDataProvider<GtnWsRecordBean>) grid
					.getDataProvider();
			List<GtnWsRecordBean> selectedRecords = (List<GtnWsRecordBean>) selectedTableItems.getItems();
			if (selectedRecords == null || selectedRecords.isEmpty()) {
				GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(
								gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString(), componentId)
						.getComponentData();
				GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(idComponentData.getViewId()).getComponentData().getSharedPopupData();
				if (Optional.ofNullable(dataSelectionBean).isPresent()) {
					dataSelectionBean.setComparisonProjectionBeanList(null);
				}
				idComponentData.setCustomData(null);
				GtnUIFrameworkGlobalUI
						.getVaadinBaseComponentFromParent(
								gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString(), componentId)
						.setV8PopupFieldValue(" ");
			}
		}
		GtnUIFrameWorkActionConfig closeAction = (GtnUIFrameWorkActionConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(3);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, closeAction);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
