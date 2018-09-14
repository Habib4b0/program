package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
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

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingComparisonBreakdownMassUpdateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		logger.debug("Mass Update Action Configure Params");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

		String comparisonBreakdownValues = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(1).toString()).getCaptionFromV8ComboBox();
		String comparisonBreakdownComparisonComboBox = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(2).toString()).getStringCaptionFromV8ComboBox();
		String comparisonBreakdownStartPeriod = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(3).toString()).getCaptionFromV8ComboBox();
		String comparisonBreakdownEndPeriod = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(4).toString()).getCaptionFromV8ComboBox();

		String startPeriodValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(3).toString())
				.getStringCaptionFromV8ComboBox();
		String endPeriodValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(4).toString())
				.getStringCaptionFromV8ComboBox();

		LocalDate startPeriodLocalDate = convertStringDateToLocalDate(startPeriodValue);
		LocalDate endPeriodLocalDate = convertStringDateToLocalDate(endPeriodValue);

		if (!comparisonMassUpdateDateConflictCheck(componentId, startPeriodLocalDate, endPeriodLocalDate)) {
			return;
		}

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinComponent(actionParameterList.get(5).toString(), componentId);
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		PagedGrid pagedGrid = gridComponent.getPagedGrid();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();

		ArrayList<String> startAndEndPeriodItemIdList = new ArrayList(
				Arrays.asList(pagedGrid.getTableConfig().getTableColumnMappingId()));

		List<String> gridColumnIdSubList = getGridColumnIdSubList(startAndEndPeriodItemIdList,
				comparisonBreakdownStartPeriod, comparisonBreakdownEndPeriod);

		HeaderRow gridHeaderRow = grid.getHeaderRow(0);

		for (int i = 1; i < grid.getHeaderRowCount(); i++) {
			Label projectionNames = (Label) grid.getHeaderRow(i).getCell("projectionNames").getComponent();
			if (projectionNames.getValue().equalsIgnoreCase(comparisonBreakdownComparisonComboBox)) {
				for (int k = 0; k < gridColumnIdSubList.size(); k++) {
					CheckBoxGroup headerCheckboxGroup = (CheckBoxGroup) gridHeaderRow
							.getCell(gridColumnIdSubList.get(k)).getComponent();
					Set<String> headerCheckboxSelectedSet = headerCheckboxGroup.getSelectedItems();
					if (headerCheckboxSelectedSet.toArray().length != 0) {
						massUpdateInGrid(comparisonBreakdownValues, grid, gridColumnIdSubList, i, k);
					}

				}
			}
		}
	}

	private List<String> getGridColumnIdSubList(ArrayList<String> startAndEndPeriodItemIdList,
			String comparisonBreakdownStartPeriod, String comparisonBreakdownEndPeriod) {
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

		return startAndEndPeriodItemIdList.subList(startDate, endDate + 1);
	}

	private void massUpdateInGrid(String comparisonBreakdownValues, Grid<GtnWsRecordBean> grid,
			List<String> gridColumnIdSubList, int i, int k) {
		HeaderRow headerRow = grid.getHeaderRow(i);
		String header = gridColumnIdSubList.get(k);
		HeaderCell headerCell = headerRow.getCell(header);
		Component component = headerCell.getComponent();
		ComboBox<String> comboBox = (ComboBox) component;
		comboBox.setSelectedItem(comparisonBreakdownValues);
	}

	// Method to check date conflict between start period and end period
	private boolean comparisonMassUpdateDateConflictCheck(String componentId, LocalDate startPeriodLocalDate,
			LocalDate endPeriodLocalDate) throws GtnFrameworkGeneralException {
		if (startPeriodLocalDate.compareTo(endPeriodLocalDate) > 0) {
			GtnUIFrameWorkActionConfig dateConflictActionConfig = new GtnUIFrameWorkActionConfig();
			dateConflictActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			dateConflictActionConfig.addActionParameter("Date Conflict");
			dateConflictActionConfig.addActionParameter("The End Period cannot be before the Start Period. ");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, dateConflictActionConfig);
			return false;
		}
		return true;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

	// Method to convert String Date to LocalDate
	public static LocalDate convertStringDateToLocalDate(String dateInput) {
		Pattern patternOne = Pattern.compile("\\bQ..[0-9]{4}\\b");
		Pattern patternTwo = Pattern.compile("\\bS..[0-9]{4}\\b");
		Pattern patternThree = Pattern.compile("[0-9]{4}");
		LocalDate localDate;

		if (patternOne.matcher(dateInput).find()) {
			int[] arr = { 0, 1, 4, 7, 10 };
			localDate = LocalDate.parse(
					"01/" + arr[Character.getNumericValue(dateInput.charAt(1))] + "/" + dateInput.substring(3),
					DateTimeFormatter.ofPattern("dd/M/yyyy"));
		} else if (patternTwo.matcher(dateInput).find()) {
			int[] arr = { 0, 1, 7 };
			localDate = LocalDate.parse(
					"01/" + arr[Character.getNumericValue(dateInput.charAt(1))] + "/" + dateInput.substring(3),
					DateTimeFormatter.ofPattern("dd/M/yyyy"));
		} else if (patternThree.matcher(dateInput).matches()) {
			localDate = LocalDate.parse("01/01/" + dateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} else {
			localDate = LocalDate.parse("01/" + dateInput.substring(0, 3) + "/" + dateInput.substring(4),
					DateTimeFormatter.ofPattern("dd/MMM/yyyy"));
		}
		return localDate;
	}
}
