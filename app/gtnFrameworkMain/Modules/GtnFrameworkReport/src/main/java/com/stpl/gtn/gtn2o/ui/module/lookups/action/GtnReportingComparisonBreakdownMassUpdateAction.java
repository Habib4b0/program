package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;

public class GtnReportingComparisonBreakdownMassUpdateAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

		List<GtnReportComparisonProjectionBean> comparisonLookupBeanList = new ArrayList<>();
		GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
				"reportingDashboardTab_reportingDashboardComparisonConfig", componentId).getComponentData();

		comparisonLookupBeanList = (List<GtnReportComparisonProjectionBean>) idComponentData.getCustomData();

		/*
		 * String sourceComponentId =
		 * GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId) .getViewId();
		 * GtnWsReportDataSelectionBean dataSelectionBean =
		 * (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
		 * .getVaadinBaseComponent(sourceComponentId).getComponentData().
		 * getSharedPopupData(); comparisonLookupBeanList =
		 * dataSelectionBean.getComparisonProjectionBeanList();
		 */
		String comparisonBreakdownValues = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(1).toString()).getCaptionFromV8ComboBox();
		String comparisonBreakdownComparisonComboBox = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(2).toString()).getStringCaptionFromV8ComboBox();
		String comparisonBreakdownStartPeriod = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(3).toString()).getCaptionFromV8ComboBox();
		String comparisonBreakdownEndPeriod = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(4).toString()).getCaptionFromV8ComboBox();

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinComponent(actionParameterList.get(5).toString(), componentId);
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		PagedGrid pagedGrid = (PagedGrid) gridComponent.getPagedGrid();
		Grid<GtnWsRecordBean> grid = (Grid<GtnWsRecordBean>) pagedGrid.getGrid();

		ArrayList<String> startAndEndPeriodItemIdList = new ArrayList(
				Arrays.asList(pagedGrid.getTableConfig().getTableColumnMappingId()));

		int startDate = 0;
		int endDate = 0;
		for (int j = 0; j < startAndEndPeriodItemIdList.size(); j++) {
			if (startAndEndPeriodItemIdList.get(j).equalsIgnoreCase(comparisonBreakdownStartPeriod)) {
				startDate = j;
			}
			if (startAndEndPeriodItemIdList.get(j).equalsIgnoreCase(comparisonBreakdownEndPeriod)) {
				endDate = j;
			}
		}

		ArrayList<String> gridColumnIdSubList = new ArrayList<String>(
				startAndEndPeriodItemIdList.subList(startDate, endDate + 1));

		HeaderRow gridHeaderRow = grid.getHeaderRow(0);

		// List<Object[]> resultList = (List<Object[]>)
		// response.getGtnReportResponse().getVariableBreakdownLookupBean().getResultList();
		for (int i = 1; i < grid.getHeaderRowCount(); i++) {
			Label projectionNames = (Label) grid.getHeaderRow(i).getCell("projectionNames").getComponent();
			if (projectionNames.getValue().equalsIgnoreCase(comparisonBreakdownComparisonComboBox)) {
				for (int k = 0; k < gridColumnIdSubList.size(); k++) {

					CheckBoxGroup headerCheckboxGroup = (CheckBoxGroup) gridHeaderRow
							.getCell(gridColumnIdSubList.get(k)).getComponent();
					Set<String> headerCheckboxSelectedSet = headerCheckboxGroup.getSelectedItems();
					boolean isHeaderCheckboxSelected = headerCheckboxSelectedSet.toArray().length == 0 ? false
							: headerCheckboxGroup.isSelected(headerCheckboxSelectedSet.toArray()[0].toString());
					if (isHeaderCheckboxSelected) {
						HeaderRow headerRow = grid.getHeaderRow(i);
						String header = gridColumnIdSubList.get(k);
						HeaderCell headerCell = headerRow.getCell(header);
						Component component = headerCell.getComponent();
						ComboBox<String> comboBox = (ComboBox) component;
						comboBox.setSelectedItem(comparisonBreakdownValues);
					}

				}
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
