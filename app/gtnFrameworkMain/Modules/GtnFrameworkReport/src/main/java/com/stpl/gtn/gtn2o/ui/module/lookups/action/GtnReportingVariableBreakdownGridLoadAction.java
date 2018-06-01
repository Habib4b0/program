package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Grid;

public class GtnReportingVariableBreakdownGridLoadAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameterList.get(1).toString());
		List<GtnReportComparisonProjectionBean> comparisonBean = (List<GtnReportComparisonProjectionBean>) componentData
				.getCustomData();

		// Webservice

		GtnUIFrameworkWebserviceResponse response = null;

		GtnUIFrameworkComponentData gridComponent = GtnUIFrameworkGlobalUI.getVaadinComponentData(componentId);
		PagedGrid pagedGrid = (PagedGrid) gridComponent.getCustomData();
		Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();
		grid.setItems(getRecordBeanList(response.getGtnSerachResponse().getResultSet().getDataTable()));
	}

	private List<GtnWsRecordBean> getRecordBeanList(List<GtnUIFrameworkDataRow> dataRow) {
		if (dataRow != null && !dataRow.isEmpty()) {
			List<GtnWsRecordBean> dataRowList = new ArrayList<>();
			for (GtnUIFrameworkDataRow gtnWsRecordBean : dataRow) {
				GtnWsRecordBean recordBean = new GtnWsRecordBean();
				recordBean.setProperties(gtnWsRecordBean.getColList());
				recordBean.setRecordHeader(GtnFrameworkReportStringConstants.getVariableBreakdownHeader());
				dataRowList.add(recordBean);
			}
			return dataRowList;
		}
		return Collections.emptyList();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
