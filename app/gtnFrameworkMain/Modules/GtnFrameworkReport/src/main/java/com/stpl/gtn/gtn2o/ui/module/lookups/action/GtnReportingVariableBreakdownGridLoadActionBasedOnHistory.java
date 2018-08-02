package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;

public class GtnReportingVariableBreakdownGridLoadActionBasedOnHistory
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnReportingVariableBreakdownGridLoadActionBasedOnHistory.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Configure Params");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			loadGridBasedOnHistory(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception ex) {
			logger.error("Error message", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

	private void loadGridBasedOnHistory(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkValidationFailedException {
		List<String> valueList = new ArrayList<>();
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("variableBreakdownResultsLayout_comparisonLookupResultsPagedTableComponent",
						componentId)
				.getComponent();
		if (abstractComponent == null) {
			return;
		}
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		PagedGrid pagedGrid = gridComponent.getPagedGrid();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();
		GtnUIFrameworkPagedTableConfig pagedTableConfig = pagedGrid.getTableConfig();
		List<String> currentStartingColumnArray = pagedTableConfig.getColumnHeaders();
		List<String> columnHeaders = pagedTableConfig.getColumnHeaders();
		List<Column<GtnWsRecordBean, ?>> gridColumns = grid.getColumns();
		if(gridColumns.isEmpty())
			return;
		grid.setFrozenColumnCount(1);
		String currentStartingColumn = currentStartingColumnArray.get(1);
		String variableBreakdownFrequency = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportOptionsTab_variableBreakdownFrequencyConfig")
				.getStringCaptionFromV8ComboBox();
		String columnRequiredBasedOnHistorySelection = getFieldBasedOnHistory(componentId, variableBreakdownFrequency,
				currentStartingColumn);
		if (columnRequiredBasedOnHistorySelection == null) {
			return;
		}
		columnRequiredBasedOnHistorySelection = columnRequiredBasedOnHistorySelection.replaceAll(" - ", " ");
		int index = columnHeaders.indexOf(columnRequiredBasedOnHistorySelection);
		if (index != -1) {
			for (int i = 1; i < index; i++) {
				String columnHeader = columnHeaders.get(i).trim();
				columnHeader = getColumnHeader(columnHeader);
				
				grid.getColumn(columnHeader).setHidden(true);
			}

			gridColumns.subList(index, gridColumns.size() - 1).forEach(col -> col.setHidden(false));

			List<String> captionList = new ArrayList<>(columnHeaders.subList(index, columnHeaders.size() - 1));
			for(String caption :captionList) {
				String value = getColumnHeader(caption);
				valueList.add(value);
			}
			GtnUIFrameworkComboBoxConfig variableBreakdownFromPeriodInMassUpdateComboBoxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportOptionsTab_variableBreakdownStartPeriod", componentId)
					.getComponentConfig().getGtnComboboxConfig();
			GtnUIFrameworkComboBoxConfig variableBreakdownEndPeriodInMassUpdateComboBoxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportOptionsTab_variableBreakdownEndPeriod", componentId)
					.getComponentConfig().getGtnComboboxConfig();
			variableBreakdownFromPeriodInMassUpdateComboBoxConfig.setItemCaptionValues(captionList);
			variableBreakdownFromPeriodInMassUpdateComboBoxConfig.setItemValues(valueList);
			variableBreakdownEndPeriodInMassUpdateComboBoxConfig.setItemCaptionValues(captionList);
			variableBreakdownEndPeriodInMassUpdateComboBoxConfig.setItemValues(valueList);
			

			GtnUIFrameworkComboBoxComponent fromPeriodComboBox = new GtnUIFrameworkComboBoxComponent();
			fromPeriodComboBox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportOptionsTab_variableBreakdownStartPeriod", componentId, Arrays.asList(""));
			GtnUIFrameworkComboBoxComponent endPeriodComboBox = new GtnUIFrameworkComboBoxComponent();
			endPeriodComboBox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					"reportOptionsTab_variableBreakdownEndPeriod", componentId, Arrays.asList(""));
			captionList.clear();
			valueList.clear();
			String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();	
    		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
			if (dataSelectionBean != null && dataSelectionBean.getVariableBreakdownSaveList() != null) {
				List<GtnReportVariableBreakdownLookupBean> variableLookupBeanList = dataSelectionBean
						.getVariableBreakdownSaveList();

				for (GtnReportVariableBreakdownLookupBean bean : variableLookupBeanList) {

					ComboBox comboBox = (ComboBox) grid.getHeaderRow(bean.getRowCount()).getCell(bean.getProperty())
							.getComponent();
					comboBox.setSelectedItem(bean.getSelectedVariable());
				}
			}
		}
	}

	private String getColumnHeader(String columnHeader) {
		if (columnHeader.charAt(0) == 'Q') {
			columnHeader = "q" + columnHeader.charAt(1) + columnHeader.substring(3) + "quarter";
		} else if (columnHeader.charAt(0) == 'S' && columnHeader.charAt(2) == ' ') {
			columnHeader = "s" + columnHeader.charAt(1) + columnHeader.substring(3) + "semiAnnual";
		} else if (columnHeader.charAt(0)=='2') {
			columnHeader = columnHeader+"year";
		} else { 
			columnHeader = columnHeader.substring(0, 3)+"month"+columnHeader.substring(4);
		}
		return columnHeader;
	}

	private String getFieldBasedOnHistory(String componentId, String variableBreakdownFrequency,
			String variableBreakdownFromPeriod) throws GtnFrameworkValidationFailedException {
		String historyValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportOptionsTab_variableBreakdownHistoryConfig", componentId)
				.getCaptionFromV8ComboBox();
		if (historyValue.equals("0")) {
			return null;
		}
		LocalDate localDate = LocalDate.now();
		char freq = variableBreakdownFrequency.charAt(0);
		Pattern p1 = Pattern.compile("Months");
		Pattern p2 = Pattern.compile("Semi-Annual Periods");
		Pattern p3 = Pattern.compile("Quarters");
		Pattern p4 = Pattern.compile("Year");

		if (p1.matcher(historyValue).find()) {
			if (historyValue.substring(1, 2).equals(" ")) {
				localDate = localDate.minus(Period.ofMonths(Integer.parseInt(historyValue.substring(0, 1))));
			} else {
				localDate = localDate.minus(Period.ofMonths(Integer.parseInt(historyValue.substring(0, 2))));
			}
		} else if (p2.matcher(historyValue).find()) {
			localDate = localDate.minus(Period.ofMonths(6 * Integer.parseInt(historyValue.substring(0, 1))));
		} else if (p3.matcher(historyValue).find()) {
			if (historyValue.substring(1, 2).equals(" ")) {
				localDate = localDate.minus(Period.ofMonths(3 * Integer.parseInt(historyValue.substring(0, 1))));
			} else {
				localDate = localDate.minus(Period.ofMonths(3 * Integer.parseInt(historyValue.substring(0, 2))));
			}
		} else if (p4.matcher(historyValue).find()) {
			localDate = localDate.minus(Period.ofYears(Integer.parseInt(historyValue.substring(0, 1))));
		}

		if (Pattern.matches("[0-9]{4}", variableBreakdownFromPeriod)) {
			variableBreakdownFromPeriod = String.valueOf(localDate.getYear());
		} else if (freq == 'Q' || variableBreakdownFrequency.equals("-Select one-")) {
			int quarterValue = (localDate.getMonthValue() / 3) + 1;
			variableBreakdownFromPeriod = "Q" + quarterValue + " - " + localDate.getYear();
		} else if (freq == 'S' && !variableBreakdownFromPeriod.subSequence(1, 3).equals("ep")) {
			int semi_annual = (localDate.getMonthValue() / 6) + 1;
			variableBreakdownFromPeriod = "S" + semi_annual + " - " + localDate.getYear();
		} else {
			variableBreakdownFromPeriod = localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " "
					+ localDate.getYear();
		}
		return variableBreakdownFromPeriod;
	}
}
