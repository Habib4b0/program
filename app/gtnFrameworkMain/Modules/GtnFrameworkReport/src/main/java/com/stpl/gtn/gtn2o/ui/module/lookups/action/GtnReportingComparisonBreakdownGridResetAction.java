package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;

public class GtnReportingComparisonBreakdownGridResetAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingComparisonBreakdownGridResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		logger.debug("Grid Reset Action Configure Params");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		resetComparisonBreakdownComboboxComponents(actionParameters, componentId);
		AbstractComponent vaadinAbstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinComponent(actionParameters.get(1).toString(), componentId);
		GtnUIFrameworkComponentData gridComponentData = (GtnUIFrameworkComponentData) vaadinAbstractComponent.getData();
		PagedGrid pagedGridInComparisonBreakdown = gridComponentData.getPagedGrid();
		Grid<GtnWsRecordBean> gridInComparisonBreakdown = pagedGridInComparisonBreakdown.getGrid();
		List<Object[]> gtnReportComparisonBreakdownLookupBeanList = null;
		try {
			gtnReportComparisonBreakdownLookupBeanList = (List<Object[]>) gridComponentData.getCustomData();
		} catch(ClassCastException ex) {
			logger.info("Exception occured during paged grid reset: " + ex.getMessage());
			return;
		}
		int comparisonBreakdownLookupBeanCount = gtnReportComparisonBreakdownLookupBeanList.size();
		int rowCount = gridInComparisonBreakdown.getHeaderRowCount();
		resetComparisonBreakdownGridComponent(comparisonBreakdownLookupBeanCount, rowCount, gridInComparisonBreakdown,
				gtnReportComparisonBreakdownLookupBeanList);
	}

	private void resetComparisonBreakdownGridComponent(int comparisonBreakdownLookupBeanCount, int rowCount,
			Grid<GtnWsRecordBean> grid, List<Object[]> gtnReportComparisonBreakdownLookupBeanList) {
		for (int i = 0; i < comparisonBreakdownLookupBeanCount; i++) {
			for (int j = 1; j < rowCount; j++) {
				Label projectionNames = (Label) grid.getHeaderRow(j).getCell("projectionNames").getComponent();
				if (gtnReportComparisonBreakdownLookupBeanList.get(i)[4].toString()
						.equals(projectionNames.getValue().toString())) {
					ComboBox comparisonBreakdownResetCombo = (ComboBox) grid.getHeaderRow(j)
							.getCell(gtnReportComparisonBreakdownLookupBeanList.get(i)[3].toString()).getComponent();
					comparisonBreakdownResetCombo.setSelectedItem("0");
				}
			}
		}
	}

	private void resetComparisonBreakdownComboboxComponents(List<Object> actionParam, String componentId)
			throws GtnFrameworkValidationFailedException {
		for (int k = 2; k < actionParam.size(); k++) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParam.get(k).toString(), componentId)
					.loadV8ComboBoxComponentValue(0);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
