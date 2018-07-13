package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;

public class GtnReportDashboardComparisonResultsSearchAction
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
		String customViewId = actionParamsList.get(2).toString();
		String sourceViewId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
		String nameSpace = actionParamsList.get(15).toString();

		GtnUIFrameWorkActionConfig loadDataGridAction = new GtnUIFrameWorkActionConfig();
		loadDataGridAction.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataGridAction.setActionParameterList(Arrays.asList(actionParamsList.get(1).toString()));
		loadDataGridAction.setFieldValues(Arrays.asList(new String[] { sourceViewId + "_" + customViewId,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PROJECTION_TYPE,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.WORKFLOW_STATUS,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionName",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupContractHolder",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.NDC_CONFIG,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupMarketType",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonLookupBrand",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "projectionDescription",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonContract",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportComparisonNdcName",
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD,
				nameSpace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD }));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataGridAction);
		
		GtnUIFrameworkBaseComponent availableGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString());
		GtnUIFrameworkComponentData availableGridData = (GtnUIFrameworkComponentData) availableGrid.getData();
		Grid<GtnWsRecordBean> grid = availableGridData.getPagedGrid().getGrid();
		if (grid.getDataProvider() instanceof ListDataProvider) {
			ListDataProvider dataProvider = (ListDataProvider) grid.getDataProvider();
			if (dataProvider.getItems().size() > 0) {
				GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
				enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
				enableAction.addActionParameter(actionParamsList.get(16).toString());
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, enableAction);
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
