/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
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
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.components.grid.HeaderRow;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportingVariableBreakdownMassUpdateAction
		implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private static final String SELECT_ONE = "-Select one-";
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingVariableBreakdownMassUpdateAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside Configure Parameters");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

		String variableBreakdownValues = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(1).toString()).getCaptionFromV8ComboBox();
		String variableBreakdownFileorProjections = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(2).toString()).getStringCaptionFromV8ComboBox();
		String variableBreakdownStartPeriod = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(3).toString()).getCaptionFromV8ComboBox();
		String variableBreakdownEndPeriod = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(4).toString()).getCaptionFromV8ComboBox();

		String startPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(3).toString())
				.getStringCaptionFromV8ComboBox();
		String endPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParameterList.get(4).toString())
				.getStringCaptionFromV8ComboBox();

		if (startPeriod.equals(SELECT_ONE) || endPeriod.equals(SELECT_ONE)) {
			massUpdateMissingFieldAlertAction(componentId);
			return;
		}

		LocalDate localDate1 = convert(startPeriod);
		LocalDate localDate2 = convert(endPeriod);

		if (!massUpdateCheck(componentId, variableBreakdownValues, variableBreakdownFileorProjections,
				variableBreakdownStartPeriod, variableBreakdownEndPeriod))
			return;
		if (!massUpdateDateConflictCheck(componentId, localDate1, localDate2))
			return;

		if (!massUpdateFuturePeriodUpdateCheck(componentId, localDate1, localDate2))
			return;

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinComponent(actionParameterList.get(5).toString(), componentId);
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		PagedGrid pagedGrid = gridComponent.getPagedGrid();
		Grid<GtnWsRecordBean> grid = pagedGrid.getGrid();

		ArrayList<String> startAndEndPeriodItemIdList = new ArrayList(
				Arrays.asList(pagedGrid.getTableConfig().getTableColumnMappingId()));

		int startDate = 0;
		int endDate = 0;

		for (int j = 0; j < startAndEndPeriodItemIdList.size(); j++) {
			if (startAndEndPeriodItemIdList.get(j).equalsIgnoreCase(variableBreakdownStartPeriod)) {
				startDate = j;
			}
			if (startAndEndPeriodItemIdList.get(j).equalsIgnoreCase(variableBreakdownEndPeriod)) {
				endDate = j;
			}
		}

		ArrayList<String> gridColumnIdSubList = new ArrayList<>(
				startAndEndPeriodItemIdList.subList(startDate, endDate + 1));

		setMassUpdateForVariableBreakdown(variableBreakdownValues, variableBreakdownFileorProjections, grid,
				gridColumnIdSubList);
	}

	private boolean massUpdateCheck(String componentId, String variableBreakdownValues,
			String variableBreakdownFileorProjections, String variableBreakdownStartPeriod,
			String variableBreakdownEndPeriod) throws GtnFrameworkGeneralException {
		Pattern p = Pattern.compile("\\b0\\b");
		Pattern p2 = Pattern.compile(SELECT_ONE);
		String input = "(" + variableBreakdownValues + ")" + "(" + variableBreakdownFileorProjections + ")" + "("
				+ variableBreakdownStartPeriod + ")" + "(" + variableBreakdownEndPeriod + ")";
		Matcher m = p.matcher(input);
		if (m.find() || p2.matcher(input).find()) {
			massUpdateMissingFieldAlertAction(componentId);
			return false;
		}
		return true;
	}

	private void massUpdateMissingFieldAlertAction(String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig massUpdateFailedActionConfig = new GtnUIFrameWorkActionConfig();
		massUpdateFailedActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		massUpdateFailedActionConfig.addActionParameter("Missing Fields");
		massUpdateFailedActionConfig
				.addActionParameter("Please make sure that all Mass Update fields are populated. Then try again. ");
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, massUpdateFailedActionConfig);
	}

	private boolean massUpdateFuturePeriodUpdateCheck(String componentId, LocalDate localDate1, LocalDate localDate2)
			throws GtnFrameworkGeneralException {
		if (localDate1.isAfter(LocalDate.now()) || localDate2.isAfter(LocalDate.now())) {
			GtnUIFrameWorkActionConfig massUpdateFailedActionConfig = new GtnUIFrameWorkActionConfig();
			massUpdateFailedActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			massUpdateFailedActionConfig.addActionParameter("Cannot Update Past Current Period");
			massUpdateFailedActionConfig.addActionParameter(
					"Future periods cannot be mass updated. All future periods are currently set to ‘Projections’ only.");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, massUpdateFailedActionConfig);
			return false;
		}
		return true;
	}

	private boolean massUpdateDateConflictCheck(String componentId, LocalDate localDate1, LocalDate localDate2)
			throws GtnFrameworkGeneralException {
		if (localDate1.compareTo(localDate2) > 0) {
			GtnUIFrameWorkActionConfig massUpdateFailedActionConfig = new GtnUIFrameWorkActionConfig();
			massUpdateFailedActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			massUpdateFailedActionConfig.addActionParameter("Date Conflict");
			massUpdateFailedActionConfig.addActionParameter("The End Period cannot be before the Start Period. ");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, massUpdateFailedActionConfig);
			return false;
		}
		return true;
	}

	private void setMassUpdateForVariableBreakdown(String variableBreakdownValues,
			String variableBreakdownFileorProjections, Grid<GtnWsRecordBean> grid,
			ArrayList<String> gridColumnIdSubList) {
		HeaderRow gridHeaderRow = grid.getHeaderRow(0);
		getGridHeaderRow(variableBreakdownValues, variableBreakdownFileorProjections, grid, gridColumnIdSubList,
				gridHeaderRow);
	}

	private void getGridHeaderRow(String variableBreakdownValues, String variableBreakdownFileorProjections,
			Grid<GtnWsRecordBean> grid, ArrayList<String> gridColumnIdSubList, HeaderRow gridHeaderRow) {
		for (int i = 1; i < grid.getHeaderRowCount(); i++) {
			Label projectionNames = (Label) grid.getHeaderRow(i).getCell("projectionNames").getComponent();
			setGridHeaderRowMassUpdateWithCheckBox(variableBreakdownValues, variableBreakdownFileorProjections, grid,
					gridColumnIdSubList, gridHeaderRow, i, projectionNames);
		}
	}

	private void setGridHeaderRowMassUpdateWithCheckBox(String variableBreakdownValues,
			String variableBreakdownFileorProjections, Grid<GtnWsRecordBean> grid,
			ArrayList<String> gridColumnIdSubList, HeaderRow gridHeaderRow, int i, Label projectionNames) {
		if (projectionNames.getValue().equalsIgnoreCase(variableBreakdownFileorProjections)) {
			setSelectedCheckboxGroup(variableBreakdownValues, grid, gridColumnIdSubList, gridHeaderRow, i);
		}
	}

	private void setSelectedCheckboxGroup(String variableBreakdownValues, Grid<GtnWsRecordBean> grid,
			ArrayList<String> gridColumnIdSubList, HeaderRow gridHeaderRow, int i) {
		for (int k = 0; k < gridColumnIdSubList.size(); k++) {
			CheckBoxGroup headerCheckboxGroup = (CheckBoxGroup) gridHeaderRow.getCell(gridColumnIdSubList.get(k))
					.getComponent();
			Set<String> headerCheckboxSelectedSet = headerCheckboxGroup.getSelectedItems();
			boolean isHeaderCheckboxSelected = headerCheckboxSelectedSet.toArray().length == 0 ? false
					: headerCheckboxGroup.isSelected(headerCheckboxSelectedSet.toArray()[0].toString());
			if (isHeaderCheckboxSelected) {
				ComboBox variableBreakdownGridCombo = (ComboBox) grid.getHeaderRow(i)
						.getCell(gridColumnIdSubList.get(k)).getComponent();
				variableBreakdownGridCombo.setSelectedItem(Integer.valueOf(variableBreakdownValues));
			}
		}
	}

	public static LocalDate convert(String input) {
		Pattern p = Pattern.compile("\\bQ..[0-9]{4}\\b");
		Pattern p2 = Pattern.compile("\\bS..[0-9]{4}\\b");
		Pattern p3 = Pattern.compile("[0-9]{4}");
		LocalDate localDate;

		if (p.matcher(input).find()) {
			int[] arr = { 0, 1, 4, 7, 10 };
			localDate = LocalDate.parse(
					"01/" + arr[Character.getNumericValue(input.charAt(1))] + "/" + input.substring(3),
					DateTimeFormatter.ofPattern("dd/M/yyyy"));
		} else if (p2.matcher(input).find()) {
			int[] arr = { 0, 1, 7 };
			localDate = LocalDate.parse(
					"01/" + arr[Character.getNumericValue(input.charAt(1))] + "/" + input.substring(3),
					DateTimeFormatter.ofPattern("dd/M/yyyy"));
		} else if (p3.matcher(input).matches()) {
			localDate = LocalDate.parse("01/01/" + input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} else {
			localDate = LocalDate.parse("01/" + input.substring(0, 3) + "/" + input.substring(4),
					DateTimeFormatter.ofPattern("dd/MMM/yyyy"));
		}
		return localDate;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
