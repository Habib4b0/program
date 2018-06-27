package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.ui.Grid;

public class GtnReportComparisonProjectionResultsLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnReportComparisonProjectionResultsLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("------------GtnReportingComparisonBreakdownGridLoadAction----------------");

		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		gtnLogger.info("component Id = = = " + componentId);
		gtnLogger.info("sourceComponentId = = =" + sourceComponentId);
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		List<GtnReportComparisonProjectionBean> comparisonProjectionsList = dataSelectionBean
				.getComparisonProjectionBeanList();
		GtnUIFrameworkBaseComponent selectedGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString());
		PagedGrid pagedGrid = (PagedGrid) selectedGrid.getComponentData().getCustomData();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
		List<GtnWsRecordBean> recordBeanList = new ArrayList<>();
		Optional.ofNullable(comparisonProjectionsList).ifPresent(e -> {
			for (GtnReportComparisonProjectionBean comparisonBean : e) {
				GtnWsRecordBean recordBean = new GtnWsRecordBean();
				recordBean.setRecordHeader(Arrays.asList("projectionName", "description", "marketType",
						"contractHolder", "contract", "brand"));
				recordBean.addProperties(comparisonBean.getProjectionName());
				recordBean.addProperties(comparisonBean.getProjectionDescription());
				recordBean.addProperties(comparisonBean.getMarketType());
				recordBean.addProperties(comparisonBean.getContractHolder());
				recordBean.addProperties(comparisonBean.getContract());
				recordBean.addProperties(comparisonBean.getBrand());
				recordBean.addProperties(comparisonBean.getItemNo());
				recordBean.addProperties(comparisonBean.getItemName());
				recordBean.addProperties(comparisonBean.getProjectionMasterSid());
				recordBean.addProperties(comparisonBean.getCreatedDate());
				recordBean.addProperties(comparisonBean.getCreatedBy());
				recordBean.addAdditionalProperty(comparisonBean.getProjectionType());
				recordBeanList.add(recordBean);
			}
		});

		grid.setItems(recordBeanList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}