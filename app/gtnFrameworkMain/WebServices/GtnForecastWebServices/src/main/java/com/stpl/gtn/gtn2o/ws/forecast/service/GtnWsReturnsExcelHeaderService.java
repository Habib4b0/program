/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

/**
 *
 * @author Karthik.Raja
 */
@Service
public class GtnWsReturnsExcelHeaderService {
	@Autowired
	private GtnWsReturnsResourceService resourceService;

	public GtnWsReturnsResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(GtnWsReturnsResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public GtnWsForecastResponse getReturnsLeftTableColumns() throws GtnFrameworkGeneralException {
		GtnWsForecastResponse tableHeaderDTO = new GtnWsForecastResponse();
		String[] singleColumn = resourceService.resourceFileName(CommonConstants.HEADER, CommonConstants.RESOURCES_PATH,
				"RETURNS_LEFT_TABLE_EXCEL_SINGLE_COLUMN").split(CommonConstants.COMMA);
		String[] singleHeader = resourceService.resourceFileName(CommonConstants.HEADER, CommonConstants.RESOURCES_PATH,
				"RETURNS_LEFT_TABLE_EXCEL_SINGLE_HEADER").split(CommonConstants.COMMA);

		for (int i = 0; i < singleColumn.length; i++) {
			tableHeaderDTO.addSingleColumn(singleColumn[i], singleHeader[i], String.class);
		}

		return tableHeaderDTO;
	}

	public GtnWsForecastResponse getReturnsTableColumns(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {
		GtnWsForecastResponse tableHeaderDTO = new GtnWsForecastResponse();
		List<String> singleHeaderDoubleHeaderLinkList = new ArrayList<>();

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
		String[] singleColumnActuals = resourceService.resourceFileName(CommonConstants.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_RIGHT_TABLE_SINGLE_COLUMN_ACTUALS")
				.split(CommonConstants.COMMA);
		String[] singleColumnProjections = resourceService.resourceFileName(CommonConstants.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_RIGHT_TABLE_SINGLE_COLUMN_PROJECTIONS")
				.split(CommonConstants.COMMA);

		String[] singleHeaderActuals = resourceService.resourceFileName(CommonConstants.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_RIGHT_TABLE_SINGLE_HEADER_ACTUALS")
				.split(CommonConstants.COMMA);
		String[] singleHeaderProjections = resourceService.resourceFileName(CommonConstants.HEADER,
				CommonConstants.RESOURCES_PATH, "RETURNS_RIGHT_TABLE_SINGLE_HEADER_PROJECTIONS")
				.split(CommonConstants.COMMA);

		GtnWsForecastResponse headerResopnse = getReturnsLeftTableColumns();

		for (int year = startYear; year <= endYear; year++) {
			if (year == endYear) {
				endMonthIndex = endMonth;
			}
			for (int month = startIndex; month <= endMonthIndex;) {
				String[] returnValue = getCommonColumnHeader(gtnForecastBean.getFrequency(), year, month);
				// client
				if (gtnForecastBean.isAscending()) {

					// Condition to restrict the actuals column(Only for
					// historical periods. current -3)
					if (!gtnForecastBean.getActualOrProjection().equals(CommonConstants.PROJECTIONS)
							&& isBelowGivenPeriodRange(historyEndDate.getTime(), year, month)) {
						columnConfigure(returnValue, singleColumnActuals, singleHeaderActuals, tableHeaderDTO,
								gtnForecastBean.isAscending());
					}
					// To add projection Column
					columnConfigure(returnValue, singleColumnProjections, singleHeaderProjections, tableHeaderDTO,
							gtnForecastBean.isAscending());
				} else {
					// To add projection Column
					columnConfigure(returnValue, singleColumnProjections, singleHeaderProjections, tableHeaderDTO,
							gtnForecastBean.isAscending());

					// Condition to restrict the actuals column(Only for
					// historical periods. current -3)
					if (!gtnForecastBean.getActualOrProjection().equals(CommonConstants.PROJECTIONS)
							&& isBelowGivenPeriodRange(historyEndDate.getTime(), year, month)) {
						columnConfigure(returnValue, singleColumnActuals, singleHeaderActuals, tableHeaderDTO,
								gtnForecastBean.isAscending());
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
		}

		if (!(tableHeaderDTO.getSingleColumns().size() + headerResopnse.getSingleColumns().size() < 255)) {
			configureSplitIndex(headerResopnse.getSingleColumns(), tableHeaderDTO.getSingleColumns(), headerResopnse,
					gtnForecastBean.getFrequency());
		}

		configureSplitIndex(headerResopnse.getSingleColumns(), tableHeaderDTO.getSingleColumns(), headerResopnse,
				gtnForecastBean.getFrequency());

		headerResopnse.getSingleColumns().addAll(tableHeaderDTO.getSingleColumns());
		headerResopnse.getSingleHeaders().addAll(tableHeaderDTO.getSingleHeaders());

		return headerResopnse;
	}

	private void configureSplitIndex(List<Object> leftTableColumns, List<Object> rightTableColumns,
			GtnWsForecastResponse headerResopnse, String frequency) {

		List<Integer[]> excelSplitIndexList = new ArrayList<>();
		List<String> excelSplitWorksheetNameList = new ArrayList<>();

		int headerStartIndex = leftTableColumns.size();
		int yearIndex = getYearIndex(frequency);
		String yearChangeValue = StringUtils.EMPTY;
		Integer[] indexArray = new Integer[2];

		indexArray[0] = headerStartIndex;

		String columnIdOne = (String) rightTableColumns.get(0);
		String[] columnSplitOne = columnIdOne.split("-");

		excelSplitWorksheetNameList.add(columnSplitOne[yearIndex]);

		for (int i = 0; i < rightTableColumns.size(); i++) {

			String columnId = (String) rightTableColumns.get(i);
			String[] columnSplit = columnId.split("-");

			if (!yearChangeValue.equals(StringUtils.EMPTY) && !yearChangeValue.equals(columnSplit[yearIndex])) {
				indexArray[1] = headerStartIndex - 1;
				excelSplitIndexList.add(indexArray);
				yearChangeValue = columnSplit[yearIndex];
				excelSplitWorksheetNameList.add(yearChangeValue);
				indexArray = new Integer[2];
				indexArray[0] = headerStartIndex;
			}
			yearChangeValue = columnSplit[yearIndex];
			headerStartIndex++;

			if (i == rightTableColumns.size() - 1) {
				indexArray[1] = headerStartIndex - 1;
				excelSplitIndexList.add(indexArray);
			}

		}

		headerResopnse.setExcelSplitIndexList(excelSplitIndexList);
		headerResopnse.setExcelSplitWorksheetName(excelSplitWorksheetNameList);
		headerResopnse.setExcelLeftTableEndIndex(leftTableColumns.size());

	}

	private int getYearIndex(String frequency) {

		if ("MONTHLY".equals(frequency.toUpperCase()) || "QUARTERLY".equals(frequency.toUpperCase())
				|| "SEMI-ANNUALLY".equals(frequency.toUpperCase())) {
			return 1;
		} else {
			return 0;
		}

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
		String commonColumn = StringUtils.EMPTY;
		String commonHeader = StringUtils.EMPTY;
		int period = 0;
		switch (frequency) {
		case CommonConstants.ANNUAL:
			commonColumn = StringUtils.EMPTY + year;
			commonHeader = StringUtils.EMPTY + year;
			period = 12;
			break;
		case CommonConstants.QUARTERLY:
			period = calculatePeriod(month, 3);
			commonColumn = CommonConstants.Q + period + CommonConstants.HYPHEN + year;
			commonHeader = CommonConstants.Q + period + CommonConstants.SPACE + year;
			period *= 3;
			break;
		case CommonConstants.SEMIANNUAL:
			period = calculatePeriod(month, 6);
			commonColumn = CommonConstants.S + period + CommonConstants.HYPHEN + year;
			commonHeader = CommonConstants.S + period + CommonConstants.SPACE + year;
			period *= 6;
			break;
		case CommonConstants.MONTHLY:
			String monthName = getMonthForInt(month);
			commonColumn = CommonConstants.M + month + CommonConstants.HYPHEN + year;
			commonHeader = monthName + CommonConstants.SPACE + year;
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
	 */
	private void columnConfigure(String[] returnValue, String[] column, String[] header,
			GtnWsForecastResponse tableHeaderDTO, final boolean isAscending) {

		if (isAscending) {
			for (int i = 0; i < column.length; i++) {
				tableHeaderDTO.addSingleColumn(returnValue[0] + column[i],
						returnValue[0] + CommonConstants.SPACE + header[i], String.class);
			}
		} else {
			for (int i = column.length - 1; i >= 0; i--) {
				tableHeaderDTO.addSingleColumn(returnValue[0] + column[i],
						returnValue[0] + CommonConstants.SPACE + header[i], String.class);
			}
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

}
