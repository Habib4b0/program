/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;

/**
 *
 * @author STPL
 */
@Service
public class GtnWsReturnsHeaderService {

	@Autowired
	private GtnWsReturnsResourceService resourceService;

	public GtnWsPagedTreeTableResponse getReturnsLeftTableColumns(GtnWsForecastRequest request)
			throws GtnFrameworkGeneralException {
		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();
		String[] singleColumn = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_LEFT_TABLE_SINGLE_COLUMN").split(",");
		String[] singleHeader = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_LEFT_TABLE_SINGLE_HEADER").split(",");
		String[] doubleColumn = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_LEFT_TABLE_DOUBLE_COLUMN").split(",");
		String[] doubleHeader = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_LEFT_TABLE_DOUBLE_HEADER").split(",");
		String[] doubleHeaderMapKey = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_LEFT_TABLE_MAPPING_KEY").split(",");
		String[] doubleHeaderMapValue = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_LEFT_TABLE_MAPPING_VALUE").split(",");

		for (int i = 0; i < singleColumn.length; i++) {
			tableHeaderDTO.addSingleColumn(singleColumn[i], singleHeader[i], String.class);
		}

		for (int i = 0; i < doubleColumn.length; i++) {
			tableHeaderDTO.addDoubleColumn(doubleColumn[i], doubleHeader[i]);
		}

		for (int i = 0; i < doubleHeaderMapKey.length; i++) {
			String[] mapValue = doubleHeaderMapValue[i].split("-");
			tableHeaderDTO.addDoubleHeaderMap(doubleHeaderMapKey[i], mapValue);
		}

		return tableHeaderDTO;
	}

	public GtnWsPagedTreeTableResponse getReturnsRightTableColumns(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {
		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();
		List<String> singleHeaderDoubleHeaderLinkList = new ArrayList<>();

		// Editable visible columns are stored in this list and send to clients
		List<String> editableFileds = new ArrayList<>();
		// To store the editable header and it will used to load From Period and
		// To Period
		List<String> editablePeriods = new ArrayList<>();
		// For EditableFileds taking projection start and projection end date
		Calendar projectionStartDate = dateToCalendar(gtnForecastBean.getProjectionStartDate());
		Calendar projectionEndDate = dateToCalendar(gtnForecastBean.getProjectionEndDate());
		// To check largest end date wheater
		Date date = gtnForecastBean.getProjectionEndDate().after(gtnForecastBean.getForecastEndDate())
				? gtnForecastBean.getProjectionEndDate() : gtnForecastBean.getForecastEndDate();

		Calendar historyEndDate = Calendar.getInstance();

		// Current minus three years
		Calendar historyStartDate = calculateHistoryPeriods(Integer.valueOf(gtnForecastBean.getSelectedHistory()),
				gtnForecastBean.getFrequency());
		int startYear = historyStartDate.get(Calendar.YEAR);
		int startMonth = historyStartDate.get(Calendar.MONTH);

		Calendar endDate = dateToCalendar(date);
		int endYear = endDate.get(Calendar.YEAR);
		int endMonth = endDate.get(Calendar.MONTH);

		int startIndex = startMonth;
		int endMonthIndex = 11;
		String[] singleColumnActuals = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_RIGHT_TABLE_SINGLE_COLUMN_ACTUALS").split(",");
		String[] singleColumnProjections = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_RIGHT_TABLE_SINGLE_COLUMN_PROJECTIONS").split(",");

		String[] singleHeaderActuals = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_RIGHT_TABLE_SINGLE_HEADER_ACTUALS").split(",");
		String[] singleHeaderProjections = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_RIGHT_TABLE_SINGLE_HEADER_PROJECTIONS").split(",");

		for (int year = startYear; year <= endYear; year++) {
			if (year == endYear) {
				endMonthIndex = endMonth;
			}
			for (int month = startIndex; month <= endMonthIndex;) {
				String[] returnValue = getCommonColumnHeader(gtnForecastBean.getFrequency(), year, month);
				// Editable list will be added in one list and send back to
				// client
				boolean isEditable = periodRangeCheck(projectionStartDate.getTime(), projectionEndDate.getTime(), month,
						year);
				if (gtnForecastBean.isAscending()) {

					// Condition to restrict the actuals column(Only for
					// historical periods. current -3)
					if (!gtnForecastBean.getActualOrProjection().equals("Projections")
							&& isBelowGivenPeriodRange(historyEndDate.getTime(), year, month)) {
						columnConfigure(returnValue, singleColumnActuals, singleHeaderActuals, tableHeaderDTO,
								singleHeaderDoubleHeaderLinkList, gtnForecastBean.isAscending());
					}
					// To add projection Column
					columnConfigure(returnValue, singleColumnProjections, singleHeaderProjections, tableHeaderDTO,
							singleHeaderDoubleHeaderLinkList, editableFileds, editablePeriods, isEditable,
							gtnForecastBean.isAscending());
				} else {
					// To add projection Column
					columnConfigure(returnValue, singleColumnProjections, singleHeaderProjections, tableHeaderDTO,
							singleHeaderDoubleHeaderLinkList, editableFileds, editablePeriods, isEditable,
							gtnForecastBean.isAscending());

					// Condition to restrict the actuals column(Only for
					// historical periods. current -3)
					if (!gtnForecastBean.getActualOrProjection().equals("Projections")
							&& isBelowGivenPeriodRange(historyEndDate.getTime(), year, month)) {
						columnConfigure(returnValue, singleColumnActuals, singleHeaderActuals, tableHeaderDTO,
								singleHeaderDoubleHeaderLinkList, gtnForecastBean.isAscending());
					}
				}
				if (!singleHeaderDoubleHeaderLinkList.isEmpty()) {
					tableHeaderDTO.addDoubleColumn(returnValue[0], returnValue[1]);
					tableHeaderDTO.addDoubleHeaderMap(returnValue[0], singleHeaderDoubleHeaderLinkList.toArray());
					singleHeaderDoubleHeaderLinkList.clear();
				}
				month = Integer.valueOf(returnValue[2]);

			}
			startIndex = 0;
		}

		// To reverse the header
		if (!gtnForecastBean.isAscending()) {
			Collections.reverse(tableHeaderDTO.getSingleColumns());
			Collections.reverse(tableHeaderDTO.getSingleHeaders());
			Collections.reverse(tableHeaderDTO.getDoubleColumns());
			Collections.reverse(tableHeaderDTO.getDoubleHeaders());
		}

		tableHeaderDTO.setEditableFields(editableFileds);
		tableHeaderDTO.setEditablePeriods(editablePeriods);
		return tableHeaderDTO;
	}

	private Calendar calculateHistoryPeriods(int selectedHistory, String frequency) {
		int monthToDeduct = -1;
		Calendar calendar = Calendar.getInstance();

		if (null != frequency) {
			switch (frequency.toUpperCase()) {
			case "MONTHLY":
				monthToDeduct = monthToDeduct * selectedHistory;
				break;
			case "QUARTERLY":
				monthToDeduct = ((selectedHistory * 3) + (calendar.get(Calendar.MONTH) % 3)) * monthToDeduct;
				break;
			case "SEMI-ANNUALLY":
				monthToDeduct = ((selectedHistory * 6) + (calendar.get(Calendar.MONTH) % 6)) * monthToDeduct;
				break;
			default:
				monthToDeduct = ((selectedHistory * 12) + (calendar.get(Calendar.MONTH) % 12)) * monthToDeduct;
				break;
			}
		}

		calendar.add(Calendar.MONTH, monthToDeduct);
		return calendar;
	}

	/**
	 * To convert the date as calendar object
	 *
	 * @param date
	 * @return
	 */
	private Calendar dateToCalendar(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;

	}

	/**
	 * Depends on the frequency division, it will return the period and year
	 * case 1 is Year , case 4 is Quarter, case 2 is semi-annual and case 12 is
	 * monthly
	 *
	 *
	 * @param frequency
	 * @param year
	 * @param month
	 * @return String array. index of 1 is column name, 2 is header name and 3
	 *         is period
	 */
	public String[] getCommonColumnHeader(String frequency, int year, int month) {
		String commonColumn = "";
		String commonHeader = "";
		int period = 0;
		switch (frequency) {
		case CommonConstants.ANNUAL:
			commonColumn = "" + year;
			commonHeader = "" + year;
			period = 12;
			break;
		case CommonConstants.QUARTERLY:
			period = calculatePeriod(month, 3);
			commonColumn = "Q" + period + "-" + year;
			commonHeader = "Q" + period + " " + year;
			period *= 3;
			break;
		case CommonConstants.SEMIANNUAL:
			period = calculatePeriod(month, 6);
			commonColumn = "S" + period + "-" + year;
			commonHeader = "S" + period + " " + year;
			period *= 6;
			break;
		case CommonConstants.MONTHLY:
			String monthName = getMonthForInt(month);
			commonColumn = "M" + month + "-" + year;
			commonHeader = monthName + " " + year;
			period = ++month;
			break;
		default:
			break;
		}
		return new String[] { commonColumn, commonHeader, String.valueOf(period) };
	}

	/**
	 * To calculate the period based on the frequency. Eg: May month will fall
	 * in Q2, S1
	 *
	 * @param num
	 * @param month
	 * @return
	 */
	public int calculatePeriod(int month, int num) {
		return (month / num) + 1;
	}

	/**
	 * To get the name of the month based on the index
	 *
	 * @param num
	 * @return String. contains the name of the month. eg) for index 0 is Jan
	 *         and 1 is Feb and so on
	 */
	public String getMonthForInt(int num) {
		DateFormatSymbols dfs = new DateFormatSymbols();
		return dfs.getShortMonths()[num];
	}

	/**
	 * Actuals column will be added to the header dto
	 *
	 * @param returnValue
	 * @param column
	 * @param header
	 * @param tableHeaderDTO
	 * @param dMap
	 */
	private void columnConfigure(String[] returnValue, String[] column, String[] header,
			GtnWsPagedTreeTableResponse tableHeaderDTO, List<String> dMap, final boolean isAscending) {

		if (isAscending) {
			for (int i = 0; i < column.length; i++) {
				tableHeaderDTO.addSingleColumn(returnValue[0] + column[i], header[i], String.class);
				dMap.add(returnValue[0] + column[i]);
			}
		} else {
			for (int i = column.length - 1; i >= 0; i--) {
				tableHeaderDTO.addSingleColumn(returnValue[0] + column[i], header[i], String.class);
				dMap.add(returnValue[0] + column[i]);
			}
		}
	}

	/**
	 *
	 * @param returnValue
	 * @param column
	 * @param header
	 * @param tableHeaderDTO
	 * @param dMap
	 */
	private void columnConfigure(String[] returnValue, String[] column, String[] header,
			GtnWsPagedTreeTableResponse tableHeaderDTO, List<String> dMap, List<String> editableFileds,
			List<String> editablePeriods, boolean isEditable, final boolean isAscending) {

		if (isAscending) {
			for (int i = 0; i < column.length; i++) {
				String visibleColumns = returnValue[0] + column[i];
				tableHeaderDTO.addSingleColumn(visibleColumns, header[i], String.class);
				dMap.add(visibleColumns);
				if (isEditable) {
					editableFileds.add(visibleColumns);
				}
			}
		} else {
			for (int i = column.length - 1; i >= 0; i--) {
				String visibleColumns = returnValue[0] + column[i];
				tableHeaderDTO.addSingleColumn(visibleColumns, header[i], String.class);
				dMap.add(visibleColumns);
				if (isEditable) {
					editableFileds.add(visibleColumns);
				}
			}
		}

		if (isEditable) {
			editablePeriods.add(returnValue[1]);
		}
	}

	/**
	 * It will return true If given year and month is before the date.
	 *
	 * @param historyEndYear
	 * @param historyEndMonth
	 * @param year
	 * @param month
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private boolean isBelowGivenPeriodRange(Date comparisonDate, int year, int month) {
		Date date = new Date();
		date.setMonth(month);
		date.setYear(year - 1900);
		if (date.before(comparisonDate)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param month
	 * @param year
	 * @return
	 */
	private boolean periodRangeCheck(Date startDate, Date endDate, int month, int year) {
		Calendar compare = Calendar.getInstance();
		compare.set(Calendar.YEAR, year);
		compare.set(Calendar.MONTH, month);
		compare.set(Calendar.DATE, 1);

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		start.set(Calendar.DATE, 1);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.set(Calendar.DATE, 1);

		if ((compare.after(start) && compare.before(end)))
			return true;

		if (compare.get(Calendar.YEAR) == start.get(Calendar.YEAR)
				&& compare.get(Calendar.MONTH) == start.get(Calendar.MONTH)
				&& compare.get(Calendar.DATE) == start.get(Calendar.DATE))
			return true;

		if (compare.get(Calendar.YEAR) == end.get(Calendar.YEAR)
				&& compare.get(Calendar.MONTH) == end.get(Calendar.MONTH)
				&& compare.get(Calendar.DATE) == end.get(Calendar.DATE))
			return true;

		return false;
	}

}
