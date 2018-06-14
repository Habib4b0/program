package com.stpl.gtn.gtn2o.ws.report.service;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.grid.GtnWsPagedTableResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;

@Service
public class HeaderGeneratorService {

	public static final String MONTHLY = "Monthly";

	public static final String QUARTERLY = "Quarterly";

	public static final String SEMIANNUAL = "Semi-Annually";

	public static final String ANNUAL = "Annually";

	public GtnWsPagedTreeTableResponse getReportLeftTableColumns(GtnWsForecastRequest request)
			throws GtnFrameworkGeneralException {
		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();
		// String[] singleColumn =
		// resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
		// CommonConstants.RESOURCES_PATH,
		// "RETURNS_LEFT_TABLE_SINGLE_COLUMN").split(",");
		// String[] singleHeader =
		// resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
		// CommonConstants.RESOURCES_PATH,
		// "RETURNS_LEFT_TABLE_SINGLE_HEADER").split(",");
		// String[] doubleColumn =
		// resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
		// CommonConstants.RESOURCES_PATH,
		// "RETURNS_LEFT_TABLE_DOUBLE_COLUMN").split(",");
		// String[] doubleHeader =
		// resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
		// CommonConstants.RESOURCES_PATH,
		// "RETURNS_LEFT_TABLE_DOUBLE_HEADER").split(",");
		// String[] doubleHeaderMapKey =
		// resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
		// CommonConstants.RESOURCES_PATH,
		// "RETURNS_LEFT_TABLE_MAPPING_KEY").split(",");
		// String[] doubleHeaderMapValue =
		// resourceService.resourceFileName(GtnFrameworkWebserviceConstant.HEADER,
		// CommonConstants.RESOURCES_PATH,
		// "RETURNS_LEFT_TABLE_MAPPING_VALUE").split(",");

		// for (int i = 0; i < singleColumn.length; i++) {
		tableHeaderDTO.addSingleColumn("levelValue", "Level", String.class);
		// }

		// for (int i = 0; i < doubleColumn.length; i++) {
		tableHeaderDTO.addDoubleColumn("levelValue", "");
		tableHeaderDTO.addTripleColumn("levelValue", "");
		// }

		// for (int i = 0; i < doubleHeaderMapKey.length; i++) {
		// String[] mapValue = doubleHeaderMapValue[i].split("-");
		// tableHeaderDTO.addDoubleHeaderMap(doubleHeaderMapKey[i], mapValue);
		// }

		return tableHeaderDTO;
	}

	public GtnWsPagedTreeTableResponse getReportRightTableColumnsDummy(
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		// HeaderGeneratorService header = new HeaderGeneratorService();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();

		// gtnForecastBean.setHistoryStartYear(2014);
		// gtnForecastBean.setHistoryStartMonth(0);
		// gtnForecastBean.setHistoryEndYear(2017);
		// gtnForecastBean.setHistoryEndMonth(0);
		GtnWsReportDashboardBean dashboardBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
				.getGtnWsReportDashboardBean();
		gtnForecastBean.setHistoryStartDate(new GregorianCalendar(2015, 5, 1, 0, 0, 0).getTime());
		gtnForecastBean.setHistoryEndDate(new GregorianCalendar(2017, 11, 1, 0, 0, 0).getTime());

		gtnForecastBean.setProjectionStartDate(new GregorianCalendar(2014, 0, 1, 0, 0, 0).getTime());
		gtnForecastBean.setProjectionEndDate(new GregorianCalendar(2021, 11, 1, 0, 0, 0).getTime());

		// gtnForecastBean.setProjectionStartYear(2016);
		// gtnForecastBean.setProjectionStartMonth(0);
		// gtnForecastBean.setProjectionEndYear(2020);
		// gtnForecastBean.setProjectionEndMonth(11);
		gtnForecastBean.setForecastStartDate(new GregorianCalendar(2014, 0, 1, 0, 0, 0).getTime());
		gtnForecastBean.setForecastEndDate(new GregorianCalendar(2018, 11, 1, 0, 0, 0).getTime());

		gtnForecastBean.setFrequency(SEMIANNUAL);
		// gtnForecastBean.setSelectedHistory("3");
		gtnForecastBean.setActualOrProjection("Actuals");
		gtnForecastBean.setAscending(true);
		gtnForecastBean.setColumn(true);
		gtnForecastBean.setVariablesVariances(true);
		GtnWsPagedTreeTableResponse response = this.getReportRightTableColumns(gtnForecastBean, dashboardBean);
		return response;

	}

	public GtnWsPagedTreeTableResponse getReportRightTableColumns(GtnForecastBean gtnForecastBean,
			GtnWsReportDashboardBean dashboardBean) {

		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();
		String[] comparisonBasisColumn = new String[] { "TEST_PRojection" };
		String[] comparisonBasisHeader = new String[] { "TEST_PRojection" };
		// String[] comparisonBasisColumn = new String[]{"Actuals", "Accruals",
		// "CurrentProjection",
		// "Projection1", "Projection2", "Projection3", "Projection4",
		// "Projection5"};
		// String[] comparisonBasisHeader = new String[]{"Actuals", "Accruals",
		// "Current
		// Projection",
		// "Prior Projection1", "Prior Projection2", "Prior Projection3", "Prior
		// Projection4", "Prior Projection5"};

		String[] variablesHeader = dashboardBean.getSelectedVariableType();

		String[] variablesColumn = new String[variablesHeader.length];
		// String[] variablesColumn = new String[] { "exfactory",
		// "grossContractSalesPerExFactory", "contractSales",
		// "contractUnits", "contractSalesPerTotalContractSales", "discountAmount",
		// "discountPercent", "rpu",
		// "deductionPerExfactory", "netContractSales", "netContractSalesPerExfactory",
		// "netExfactorySales",
		// "netExfactorySalesPerTotalExfactory", "weightedGtn" };

		// String[] variableCategoryHeader = new String[] { "Value", "Variance", "%
		// Change" };
		// String[] variableCategoryColumn = new String[] { "Value", "Variance",
		// "PerChange", "Volume", "Rate",
		// "ChangeInChange" };
		String[] variableCategoryHeader = dashboardBean.getSelectedVariableCategoryType();

		if (variablesHeader.length == 0 || variableCategoryHeader.length == 0) {
			return tableHeaderDTO;
		}

		String[] variableCategoryColumn = new String[variableCategoryHeader.length];

		List<Object[]> periods = getTimeRange(gtnForecastBean);
		Object[] periodColumn = periods.get(0);
		Object[] periodHeader = periods.get(1);

		List<Object[]> combinedVariableCategoryList = null;
		Object[] combinedVariableCategoryColumn = null;
		Object[] combinedVariableCategoryHeader = null;
		generateColumn(variablesHeader, variablesColumn);
		generateColumn(variableCategoryHeader, variableCategoryColumn);
		if (gtnForecastBean.isColumn()) {
			combinedVariableCategoryList = getCombinedVariableCategory(variablesColumn, variableCategoryColumn,
					variablesHeader, variableCategoryHeader, gtnForecastBean.isVariablesVariances());
			combinedVariableCategoryColumn = combinedVariableCategoryList.get(0);
			combinedVariableCategoryHeader = combinedVariableCategoryList.get(1);
			switch (1) {
			case 1:// 1. Time/Variable/Comparison
				createTableHeader(periodColumn, combinedVariableCategoryColumn, comparisonBasisColumn, periodHeader,
						combinedVariableCategoryHeader, comparisonBasisHeader, tableHeaderDTO);
				break;
			case 2:// 2. Comparison/Variable/Time
				createTableHeader(comparisonBasisColumn, combinedVariableCategoryColumn, periodColumn,
						comparisonBasisHeader, combinedVariableCategoryHeader, periodHeader, tableHeaderDTO);
				break;
			case 3:// 3. Comparison/Time/Variable
				createTableHeader(comparisonBasisColumn, periodColumn, combinedVariableCategoryColumn,
						comparisonBasisHeader, periodHeader, combinedVariableCategoryHeader, tableHeaderDTO);
				break;
			case 4:// 4. Variable/Comparison/Time
				createTableHeader(combinedVariableCategoryColumn, comparisonBasisColumn, periodColumn,
						combinedVariableCategoryHeader, comparisonBasisHeader, periodHeader, tableHeaderDTO);
				break;
			default:
				break;
			}
		} else {
			combinedVariableCategoryList = getCombinedVariableCategory(comparisonBasisColumn, variableCategoryColumn,
					comparisonBasisHeader, variableCategoryHeader, gtnForecastBean.isVariablesVariances());
			combinedVariableCategoryColumn = combinedVariableCategoryList.get(0);
			combinedVariableCategoryHeader = combinedVariableCategoryList.get(1);
			switch (2) {
			case 1:// 1. Time/Variable/Comparison
				createTableHeader(periodColumn, combinedVariableCategoryColumn, periodHeader,
						combinedVariableCategoryHeader, tableHeaderDTO);
				break;
			case 2:// 2. Comparison/Variable/Time
				createTableHeader(combinedVariableCategoryColumn, periodColumn, combinedVariableCategoryHeader,
						periodHeader, tableHeaderDTO);
				break;
			case 3:// 3. Comparison/Time/Variable
				createTableHeader(combinedVariableCategoryColumn, periodColumn, combinedVariableCategoryHeader,
						periodHeader, tableHeaderDTO);
				break;
			case 4:// 4. Variable/Comparison/Time
				createTableHeader(combinedVariableCategoryColumn, periodColumn, combinedVariableCategoryHeader,
						periodHeader, tableHeaderDTO);
				break;
			default:
				break;
			}
		}

		return tableHeaderDTO;
	}

	private void generateColumn(String[] variablesHeader, String[] variablesColumn) {
		for (int i = 0; i < variablesHeader.length; i++) {
			String tempHeader = variablesHeader[i];
			tempHeader = tempHeader.replaceAll("-", "_");
			tempHeader = tempHeader.replaceAll(" ", "_");
			tempHeader = tempHeader.replaceAll("%", "_percen_");
			variablesColumn[i] = tempHeader;
		}
	}

	private Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public String[] getCommonColumnHeader(String frequency, int year, int month) {
		String commonColumn = "";
		String commonHeader = "";
		int period = 0;
		switch (frequency) {
		case ANNUAL:
			commonColumn = "" + year;
			commonHeader = "" + year;
			period = 12;
			break;
		case QUARTERLY:
			period = calculatePeriod(month, 3);
			commonColumn = period + "" + year;
			commonHeader = "Q" + period + " " + year;
			period *= 3;
			break;
		case SEMIANNUAL:
			period = calculatePeriod(month, 6);
			commonColumn = period + "" + year;
			commonHeader = "S" + period + " " + year;
			period *= 6;
			break;
		case MONTHLY:
			String monthName = getMonthForInt(month);
			commonColumn = month + "" + year;
			commonHeader = monthName + " " + year;
			period = ++month;
			break;
		default:
			break;
		}
		return new String[] { commonColumn, commonHeader, String.valueOf(period) };
	}

	public int calculatePeriod(int month, int num) {
		return (month / num) + 1;
	}

	public String getMonthForInt(int num) {
		DateFormatSymbols dfs = new DateFormatSymbols();
		return dfs.getShortMonths()[num];
	}

	private void createTableHeader(Object[] singleColumn, Object[] doubleColumn, Object[] tripleColumn,
			Object[] singleHeader, Object[] doubleHeader, Object[] tripleHeader,
			GtnWsPagedTreeTableResponse tableHeaderDTO) {

		List<String> tripleMap = new ArrayList<>();
		List<String> doubleMap = new ArrayList<>();
		for (int i = 0; i < tripleColumn.length; i++) {
			for (int j = 0; j < doubleColumn.length; j++) {
				for (int k = 0; k < singleColumn.length; k++) {
					Object single = createSingleColumn(singleColumn[k].toString(), doubleColumn[j].toString(),
							tripleColumn[i].toString());
					tableHeaderDTO.addSingleColumn(single, singleHeader[k].toString(), String.class);
					doubleMap.add(single.toString());
				}
				tableHeaderDTO.addDoubleColumn(doubleColumn[j], doubleHeader[j].toString());
				tableHeaderDTO.addDoubleHeaderMap(doubleColumn[j], doubleMap.toArray());
				doubleMap.clear();
				tripleMap.add(doubleColumn[j].toString());
			}
			tableHeaderDTO.addTripleColumn(tripleColumn[i], tripleHeader[i].toString());
			tableHeaderDTO.addTripleHeaderMap(tripleColumn[i], tripleMap.toArray());
			tripleMap.clear();
		}
	}

	private void createTableHeader(Object[] singleColumn, Object[] doubleColumn, Object[] singleHeader,
			Object[] doubleHeader, GtnWsPagedTreeTableResponse tableHeaderDTO) {
		List<String> doubleMap = new ArrayList<>();
		for (int j = 0; j < doubleColumn.length; j++) {
			for (int k = 0; k < singleColumn.length; k++) {
				Object single = singleColumn[k].toString() + doubleColumn[j];
				tableHeaderDTO.addSingleColumn(single, singleHeader[k].toString(), String.class);
				doubleMap.add(single.toString());
			}
			tableHeaderDTO.addDoubleColumn(doubleColumn[j], doubleHeader[j].toString());
			tableHeaderDTO.addDoubleHeaderMap(doubleColumn[j], doubleMap.toArray());
			doubleMap.clear();
		}
	}

	private String createSingleColumn(String singleColumn, String doubleColumn, String tripleColumn) {
		StringBuilder singleColumnValue = new StringBuilder();
		switch (1) {
		case 1:// 1. Time/Variable/Comparison
			singleColumnValue.append(singleColumn);
			// singleColumnValue.append("~");
			singleColumnValue.append(doubleColumn);
			singleColumnValue.append(tripleColumn);
			break;
		case 2:// 2. Comparison/Variable/Time
			singleColumnValue.append(tripleColumn);
			singleColumnValue.append("~");
			singleColumnValue.append(doubleColumn);
			singleColumnValue.append(singleColumn);
			break;
		case 3:// 3. Comparison/Time/Variable
			singleColumnValue.append(doubleColumn);
			singleColumnValue.append("~");
			singleColumnValue.append(tripleColumn);
			singleColumnValue.append(singleColumn);
			break;
		case 4:// 4. Variable/Comparison/Time
			singleColumnValue.append(tripleColumn);
			singleColumnValue.append("~");
			singleColumnValue.append(singleColumn);
			singleColumnValue.append(doubleColumn);
			break;
		default:
			break;
		}
		return singleColumnValue.toString();
	}

	private List<Object[]> getCombinedVariableCategory(String[] firstColumn, String[] variableCategoryColumn,
			String[] firstHeader, String[] variableCategoryHeader, boolean isVariablesAndVariances) {
		List<Object[]> combinedVariableCategory = new ArrayList<>();
		int combinedArraySize = firstColumn.length * variableCategoryColumn.length;
		Object[] combinedVariableCategoryColumn = new Object[combinedArraySize];
		Object[] combinedVariableCategoryHeader = new Object[combinedArraySize];
		int index = 0;

		String[] variablesColumn = null;
		String[] variablesHeader = null;
		String[] variancesColumn = null;
		String[] variancesHeader = null;

		if (isVariablesAndVariances) {
			variablesColumn = variableCategoryColumn;
			variablesHeader = variableCategoryHeader;
			variancesColumn = firstColumn;
			variancesHeader = firstHeader;
		} else {
			variablesColumn = firstColumn;
			variablesHeader = firstHeader;
			variancesColumn = variableCategoryColumn;
			variancesHeader = variableCategoryHeader;
		}

		for (int i = 0; i < variablesColumn.length; i++) {
			for (int j = 0; j < variancesColumn.length; j++) {
				if (isVariablesAndVariances) {
					combinedVariableCategoryColumn[index] = variancesColumn[j].trim() + "#" + variablesColumn[i].trim();
					combinedVariableCategoryHeader[index] = variancesHeader[j] + " " + variablesHeader[i];
				} else {
					combinedVariableCategoryColumn[index] = variablesColumn[i].trim() + "#" + variancesColumn[j].trim();
					combinedVariableCategoryHeader[index] = variablesHeader[i] + " " + variancesHeader[j];
				}
				index++;
			}
		}
		combinedVariableCategory.add(combinedVariableCategoryColumn);
		combinedVariableCategory.add(combinedVariableCategoryHeader);
		return combinedVariableCategory;
	}

	private List<Object[]> getTimeRange(GtnForecastBean gtnForecastBean) {
		// To check largest end date wheater
		List<Object[]> periods = new ArrayList<>();
		Date date = gtnForecastBean.getProjectionEndDate().after(gtnForecastBean.getForecastEndDate())
				? gtnForecastBean.getProjectionEndDate()
				: gtnForecastBean.getForecastEndDate();
		// Current minus three years
		// Calendar historyStartDate =
		// calculateHistoryPeriods(Integer.valueOf(gtnForecastBean.getSelectedHistory()),
		// gtnForecastBean.getFrequency());
		Calendar historyStartDate = dateToCalendar(gtnForecastBean.getHistoryStartDate());
		int startYear = historyStartDate.get(Calendar.YEAR);
		int startMonth = historyStartDate.get(Calendar.MONTH);

		Calendar endDate = dateToCalendar(date);
		int endYear = endDate.get(Calendar.YEAR);
		int endMonth = endDate.get(Calendar.MONTH);

		int startIndex = startMonth;
		int endMonthIndex = 11;

		for (int year = startYear; year <= endYear; year++) {
			if (year == endYear) {
				endMonthIndex = endMonth;
			}
			for (int month = startIndex; month <= endMonthIndex;) {
				String[] returnValue = getCommonColumnHeader(gtnForecastBean.getFrequency(), year, month);
				periods.add(returnValue);
				month = Integer.valueOf(returnValue[2]);
			}
		}
		return getPeriodColumnHeader(periods);
	}

	private List<Object[]> getPeriodColumnHeader(List<Object[]> periods) {
		Object[] periodHeader = new Object[periods.size()];
		Object[] periodColumn = new Object[periods.size()];
		List<Object[]> periodColumnHeader = new ArrayList<>();
		for (int i = 0; i < periods.size(); i++) {
			Object[] period = periods.get(i);
			periodColumn[i] = period[0];
			periodHeader[i] = period[1];
		}
		periodColumnHeader.add(periodColumn);
		periodColumnHeader.add(periodHeader);
		return periodColumnHeader;
	}

	public String getVariableBreakdownPeriods(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {

		List variableBreakdown = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getDataSelectionBean()
				.getVariableBreakdownHeaderLoadList();

		String fromPeriod = variableBreakdown.get(0).toString();
		String toPeriod = variableBreakdown.get(1).toString();
		String dateFromPeriodQuery = null;
		String dateToPeriodQuery = null;
		String splitParameter = " ";
		String frequency = "";
		if (fromPeriod.startsWith("Q")) {
			List<Integer> quarterToDateForFromPeriod = getQuarterToDate(fromPeriod, splitParameter);
			List<Integer> quarterToDateForToPeriod = getQuarterToDate(toPeriod, splitParameter);
			dateFromPeriodQuery = getDateFromFrequency(quarterToDateForFromPeriod);
			dateToPeriodQuery = getDateFromFrequency(quarterToDateForToPeriod);
			frequency = "QUARTER,";
		} else if (fromPeriod.startsWith("S")) {

			List<Integer> semiAnnualToDateForFromPeriod = getSemiAnnualToDate(fromPeriod, splitParameter);
			List<Integer> semiAnnualToDateForToPeriod = getSemiAnnualToDate(toPeriod, splitParameter);

			dateFromPeriodQuery = getDateFromFrequency(semiAnnualToDateForFromPeriod);
			dateToPeriodQuery = getDateFromFrequency(semiAnnualToDateForToPeriod);
			frequency = "SEMI_ANNUAL,";
		} else if (fromPeriod.matches("[0-9]+")) {
			List<Integer> yearToDateForFromPeriod = new ArrayList<>();
			yearToDateForFromPeriod.add(Integer.valueOf(fromPeriod));
			yearToDateForFromPeriod.add(1);
			yearToDateForFromPeriod.add(1);
			List<Integer> yearToDateForToPeriod = new ArrayList<>();
			yearToDateForToPeriod.add(Integer.valueOf(toPeriod));
			yearToDateForToPeriod.add(1);
			yearToDateForToPeriod.add(1);

			dateFromPeriodQuery = getDateFromFrequency(yearToDateForFromPeriod);
			dateToPeriodQuery = getDateFromFrequency(yearToDateForToPeriod);
			frequency = "YEAR,";
		} else {
			List<Integer> monthToDateForFromPeriod = new ArrayList<>();
			String[] monthToDateForFromPeriodSplit = fromPeriod.split(" ");
			monthToDateForFromPeriod.add(Integer.valueOf(monthToDateForFromPeriodSplit[1]));
			monthToDateForFromPeriod.add(getMonthIntegerFromYear(monthToDateForFromPeriodSplit[0]));
			monthToDateForFromPeriod.add(1);

			List<Integer> monthToDateForToPeriod = new ArrayList<>();
			String[] monthToDateForToPeriodSplit = toPeriod.split(" ");
			monthToDateForToPeriod.add(Integer.valueOf(monthToDateForToPeriodSplit[1]));
			monthToDateForToPeriod.add(getMonthIntegerFromYear(monthToDateForToPeriodSplit[0]));
			monthToDateForToPeriod.add(1);

			dateFromPeriodQuery = getDateFromFrequency(monthToDateForFromPeriod);
			dateToPeriodQuery = getDateFromFrequency(monthToDateForToPeriod);
			frequency = "MONTH,";
		}
		String finalQuery = GtnWsQueryConstants.VARIABLE_BREAKDOWN_PERIOD_DATAS;
		finalQuery = finalQuery.replace("@startDate", dateFromPeriodQuery).replace("@endDate", dateToPeriodQuery)
				.replace("@frequency", frequency);

		return finalQuery;
	}

	private String getDateFromFrequency(List<Integer> periodList) {
		String date = "";
		for (int i = 0; i < periodList.size(); i++) {
			if (i < periodList.size() - 1) {
				date = date + periodList.get(i) + "-";
			} else {
				date = date + periodList.get(i);
			}
		}
		return date.trim();
	}

	public GtnWsPagedTableResponse getVariableBreakdownHeaderColumns(
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) throws GtnFrameworkGeneralException {

		GtnWsPagedTableResponse tableHeaderDTO = new GtnWsPagedTableResponse();

		List variableBreakdown = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getDataSelectionBean()
				.getVariableBreakdownHeaderLoadList();

		String fromPeriod = variableBreakdown.get(0).toString();
		String toPeriod = variableBreakdown.get(1).toString();
		String frequency = variableBreakdown.get(2).toString();
		String splitParameter = "-";
		if (fromPeriod.startsWith("Q")) {
			List<Integer> quarterToDateForFromPeriod = getQuarterToDate(fromPeriod, splitParameter);
			List<Integer> quarterToDateForToPeriod = getQuarterToDate(toPeriod, splitParameter);
			tableHeaderDTO = getHeaderBasedOnFrequency(frequency, quarterToDateForFromPeriod, quarterToDateForToPeriod,
					tableHeaderDTO, fromPeriod);
		} else if (fromPeriod.startsWith("S")) {

			List<Integer> semiAnnualToDateForFromPeriod = getSemiAnnualToDate(fromPeriod, splitParameter);
			List<Integer> semiAnnualToDateForToPeriod = getSemiAnnualToDate(toPeriod, splitParameter);
			tableHeaderDTO = getHeaderBasedOnFrequency(frequency, semiAnnualToDateForFromPeriod,
					semiAnnualToDateForToPeriod, tableHeaderDTO, fromPeriod);

		} else if (fromPeriod.matches("[0-9]+")) {
			List<Integer> yearToDateForFromPeriod = new ArrayList<>();
			yearToDateForFromPeriod.add(Integer.valueOf(fromPeriod));
			yearToDateForFromPeriod.add(1);
			yearToDateForFromPeriod.add(1);
			List<Integer> yearToDateForToPeriod = new ArrayList<>();
			yearToDateForToPeriod.add(Integer.valueOf(toPeriod));
			yearToDateForToPeriod.add(1);
			yearToDateForToPeriod.add(1);

			tableHeaderDTO = getHeaderBasedOnFrequency(frequency, yearToDateForFromPeriod, yearToDateForToPeriod,
					tableHeaderDTO, fromPeriod);
		} else {
			List<Integer> monthToDateForFromPeriod = new ArrayList<>();
			String[] monthToDateForFromPeriodSplit = fromPeriod.split(" ");
			monthToDateForFromPeriod.add(Integer.valueOf(monthToDateForFromPeriodSplit[1].trim()));
			monthToDateForFromPeriod.add(getMonthIntegerFromYear(monthToDateForFromPeriodSplit[0].trim()));
			monthToDateForFromPeriod.add(1);

			List<Integer> monthToDateForToPeriod = new ArrayList<>();
			String[] monthToDateForToPeriodSplit = toPeriod.split(" ");
			monthToDateForToPeriod.add(Integer.valueOf(monthToDateForToPeriodSplit[1].trim()));
			monthToDateForToPeriod.add(getMonthIntegerFromYear(monthToDateForToPeriodSplit[0].trim()));
			monthToDateForToPeriod.add(1);

			tableHeaderDTO = getHeaderBasedOnFrequency(frequency, monthToDateForFromPeriod, monthToDateForToPeriod,
					tableHeaderDTO, fromPeriod);

		}

		return tableHeaderDTO;
	}

	private GtnWsPagedTableResponse getHeaderBasedOnFrequency(String frequency,
			List<Integer> quarterToDateForFromPeriod, List<Integer> quarterToDateForToPeriod,
			GtnWsPagedTableResponse tableHeaderDTO, String fromPeriod) {
		if (frequency.startsWith("Ann")) {
			Integer startYear = quarterToDateForFromPeriod.get(0);
			Integer endYear = quarterToDateForToPeriod.get(0);
			for (int i = startYear; i <= endYear; i++) {
				tableHeaderDTO.addSingleColumn(String.valueOf(i) + "year", String.valueOf(i), String.class);
			}
		} else if (frequency.startsWith("Quar")) {
			Map<Integer, Integer> newQuarterMap = new HashMap<>();
			newQuarterMap.put(1, 1);
			newQuarterMap.put(2, 1);
			newQuarterMap.put(3, 1);
			newQuarterMap.put(4, 2);
			newQuarterMap.put(5, 2);
			newQuarterMap.put(6, 2);
			newQuarterMap.put(7, 3);
			newQuarterMap.put(8, 3);
			newQuarterMap.put(9, 3);
			newQuarterMap.put(10, 4);
			newQuarterMap.put(11, 4);
			newQuarterMap.put(12, 4);
			int quarterStarting = 1;
			int quarterEnding = 4;
			Integer startYear = quarterToDateForFromPeriod.get(0);
			Integer endYear = quarterToDateForToPeriod.get(0);
			int changedFromQuarter = quarterToDateForFromPeriod.get(1);
			int changedToQuarter = quarterToDateForToPeriod.get(1);
			quarterToDateForFromPeriod.add(1, newQuarterMap.get(changedFromQuarter));
			quarterToDateForToPeriod.add(1, newQuarterMap.get(changedToQuarter));

			for (int i = startYear; i <= endYear; i++) {
				if (i == startYear) {
					quarterStarting = quarterToDateForFromPeriod.get(1);
				} else {
					quarterStarting = 1;
				}
				if (i == endYear) {
					quarterEnding = quarterToDateForToPeriod.get(1);
				} else {
					quarterEnding = 4;
				}
				for (int j = quarterStarting; j <= quarterEnding; j++) {
					tableHeaderDTO.addSingleColumn("q" + j + String.valueOf(i) + "quarter",
							"Q" + j + " " + String.valueOf(i), String.class);
				}
			}
		} else if (frequency.startsWith("Sem")) {
			int semiAnnualStarting = 1;
			int semiAnnualEnding = 2;

			Integer startYear = quarterToDateForFromPeriod.get(0);
			Integer endYear = quarterToDateForToPeriod.get(0);

			Map<Integer, Integer> newSemiAnnualMap = new HashMap<>();
			newSemiAnnualMap.put(1, 1);
			newSemiAnnualMap.put(2, 1);
			newSemiAnnualMap.put(3, 1);
			newSemiAnnualMap.put(4, 1);
			newSemiAnnualMap.put(5, 1);
			newSemiAnnualMap.put(6, 1);
			newSemiAnnualMap.put(7, 2);
			newSemiAnnualMap.put(8, 2);
			newSemiAnnualMap.put(9, 2);
			newSemiAnnualMap.put(10, 2);
			newSemiAnnualMap.put(11, 2);
			newSemiAnnualMap.put(12, 2);

			int changedFromSemiAnnual = quarterToDateForFromPeriod.get(1);
			int changedToSemiAnnual = quarterToDateForToPeriod.get(1);
			quarterToDateForFromPeriod.add(1, newSemiAnnualMap.get(changedFromSemiAnnual));
			quarterToDateForToPeriod.add(1, newSemiAnnualMap.get(changedToSemiAnnual));

			for (int i = startYear; i <= endYear; i++) {
				if (i == startYear) {
					semiAnnualStarting = quarterToDateForFromPeriod.get(1);
				} else {
					semiAnnualStarting = 1;
				}
				if (i == endYear) {
					semiAnnualEnding = quarterToDateForToPeriod.get(1);
				} else {
					semiAnnualEnding = 2;
				}
				for (int j = semiAnnualStarting; j <= semiAnnualEnding; j++) {
					tableHeaderDTO.addSingleColumn("s" + j + String.valueOf(i) + "semiAnnual",
							"S" + j + " " + String.valueOf(i), String.class);
				}
			}
		} else {
			Integer startYear = quarterToDateForFromPeriod.get(0);
			Integer endYear = quarterToDateForToPeriod.get(0);

			int monthStarting = 1;
			int monthEnding = 12;

			Map<Integer, Integer> newMonthMapForQuarter = new HashMap<>();
			newMonthMapForQuarter.put(1, 3);
			newMonthMapForQuarter.put(4, 6);
			newMonthMapForQuarter.put(7, 9);
			newMonthMapForQuarter.put(10, 12);

			Map<Integer, Integer> newMonthMapForSemiAnnual = new HashMap<>();
			newMonthMapForSemiAnnual.put(1, 6);
			newMonthMapForSemiAnnual.put(6, 12);

			if (fromPeriod.startsWith("Q")) {
				quarterToDateForToPeriod.add(1, newMonthMapForQuarter.get(quarterToDateForToPeriod.get(1)));
			} else if (fromPeriod.startsWith("S")) {
				quarterToDateForToPeriod.add(1, newMonthMapForSemiAnnual.get(quarterToDateForToPeriod.get(1)));
			}

			for (int i = startYear; i <= endYear; i++) {
				if (i == startYear) {
					monthStarting = quarterToDateForFromPeriod.get(1);
				} else {
					monthStarting = 1;
				}
				if (i == endYear) {

					monthEnding = quarterToDateForToPeriod.get(1);
				} else {
					monthEnding = 12;
				}
				for (int j = monthStarting; j <= monthEnding; j++) {
					tableHeaderDTO.addSingleColumn(getMonthFromYear(j) + "month" + String.valueOf(i),
							getMonthFromYear(j) + " " + String.valueOf(i), String.class);
				}
			}
		}
		return tableHeaderDTO;
	}

	private List<Integer> getQuarterToDate(String fromPeriod, String splitParameter) throws NumberFormatException {
		String[] quarterToDateSplit = fromPeriod.trim().split(splitParameter);
		List<Integer> quarterToDate = new ArrayList<>();
		quarterToDate.add(0, Integer.valueOf(quarterToDateSplit[1].trim()));
		quarterToDate.add(1,
				Integer.valueOf(returnMonthOfQuarter(String.valueOf(quarterToDateSplit[0].trim().charAt(1)))));
		quarterToDate.add(2, Integer.valueOf("1"));
		return quarterToDate;
	}

	private List<Integer> getSemiAnnualToDate(String fromPeriod, String splitParameter) throws NumberFormatException {
		String[] semiAnnual = fromPeriod.trim().split(splitParameter);
		List<Integer> semiAnnualToDate = new ArrayList<>();
		semiAnnualToDate.add(0, Integer.valueOf(semiAnnual[1].trim()));
		semiAnnualToDate.add(1,
				Integer.valueOf(returnMonthOfSemiAnnual(String.valueOf(semiAnnual[0].trim().charAt(1)))));
		semiAnnualToDate.add(2, Integer.valueOf("1"));
		return semiAnnualToDate;
	}

	private String returnMonthOfSemiAnnual(String charAt) {
		String semiAnnualMonth = "";
		switch (charAt) {
		case "1":
			semiAnnualMonth = "1";
			break;
		case "2":
			semiAnnualMonth = "7";
			break;

		}
		return semiAnnualMonth;
	}

	private String returnMonthOfQuarter(String charAt) {
		String quarterMonth = "";
		switch (charAt) {
		case "1":
			quarterMonth = "1";
			break;
		case "2":
			quarterMonth = "4";
			break;
		case "3":
			quarterMonth = "7";
			break;
		case "4":
			quarterMonth = "10";
			break;
		}
		return quarterMonth;
	}

	// comparison breakdown grid Header service
	public GtnWsPagedTableResponse getComparisonBreakdownHeaderColumns() throws GtnFrameworkGeneralException {

		GtnWsPagedTableResponse tableHeaderDTO = new GtnWsPagedTableResponse();

		tableHeaderDTO.addSingleColumn("q12017", "Q1 2017", String.class);
		tableHeaderDTO.addSingleColumn("q22017", "Q2 2017", String.class);
		tableHeaderDTO.addSingleColumn("q32017", "Q3 2017", String.class);
		tableHeaderDTO.addSingleColumn("q42017", "Q4 2017", String.class);

		return tableHeaderDTO;
	}

	private String getMonthFromYear(int count) {
		String month = "";
		switch (count) {
		case 1:
			month = "Jan";
			break;
		case 2:
			month = "Feb";
			break;
		case 3:
			month = "Mar";
			break;
		case 4:
			month = "Apr";
			break;
		case 5:
			month = "May";
			break;
		case 6:
			month = "Jun";
			break;
		case 7:
			month = "Jul";
			break;
		case 8:
			month = "Aug";
			break;
		case 9:
			month = "Sep";
			break;
		case 10:
			month = "Oct";
			break;
		case 11:
			month = "Nov";
			break;
		case 12:
			month = "Dec";
			break;
		}
		return month;
	}

	private int getMonthIntegerFromYear(String month) {
		int monthCount = 0;
		switch (month) {
		case "Jan":
			monthCount = 1;
			break;
		case "Feb":
			monthCount = 2;
			break;
		case "Mar":
			monthCount = 3;
			break;
		case "Apr":
			monthCount = 4;
			break;
		case "May":
			monthCount = 5;
			break;
		case "Jun":
			monthCount = 6;
			break;
		case "Jul":
			monthCount = 7;
			break;
		case "Aug":
			monthCount = 8;
			break;
		case "Sep":
			monthCount = 9;
			break;
		case "Oct":
			monthCount = 10;
			break;
		case "Nov":
			monthCount = 11;
			break;
		case "Dec":
			monthCount = 12;
			break;

		}
		return monthCount;
	}
}
