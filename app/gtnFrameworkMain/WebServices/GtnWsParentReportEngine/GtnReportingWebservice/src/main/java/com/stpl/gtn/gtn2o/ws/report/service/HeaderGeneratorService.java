package com.stpl.gtn.gtn2o.ws.report.service;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.report.serviceimpl.GtnWsReportDataSelectionSqlGenerateServiceImpl;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.grid.GtnWsPagedTableResponse;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;

@Service
public class HeaderGeneratorService {

	private static final String CHANGE_IN_CHANGE = "Change in Change";

	private static final String VOLUME = "Volume";

	private static final String CHANGE = "% Change";

	private static final String PERIOD_DATE = " PERIOD_DATE = '";

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(HeaderGeneratorService.class);

	private static final String ACTUALS = "Actuals";

	public static final String MONTHLY = "Monthly";

	public static final String QUARTERLY = "Quarterly";

	public static final String SEMIANNUAL = "Semi-Annually";

	public static final String ANNUAL = "Annually";

	public static final String ANNUAL_TOTALS = "Annual Totals";

	private static final String LEVEL_VALUE = "levelValue";

	private static final String TOTAL2 = "Total";

	private static final String ANNUAL2 = "Annual";

	private static final String TOTAL = " Total";

	private static final String VARIANCE = "Variance";

	public HeaderGeneratorService() {
		super();
	}

	@Autowired
	private GtnWsReportDataSelectionSqlGenerateServiceImpl reportDataSelectionSql;

	private Map<String, String> getVariableCategorymap() {
		Map<String, String> variableCategoryMap = new HashMap<>();
		variableCategoryMap.put("Value", "PROJ");
		variableCategoryMap.put(VARIANCE, "PROJ_VARIANCE");
		variableCategoryMap.put(CHANGE, "PROJ_PER_CHANGE");
		variableCategoryMap.put(ACTUALS, "ACTUAL_VALUE");
		variableCategoryMap.put("Accruals", "ACCRUAL");
		variableCategoryMap.put(VOLUME, "VOLUME");
		variableCategoryMap.put("Rate", "RATE");
		variableCategoryMap.put(CHANGE_IN_CHANGE, "CHANGEINCHANGE");
		return variableCategoryMap;
	}

	private Map<String, String> getVariableMap() {
		Map<String, String> variableMap = new HashMap<>();
		variableMap.put("Ex-Factory Sales", "EXFACTORY_SALES");
		variableMap.put("Gross Contract Sales % of Ex-Factory", "CON_SALES_PER_FO_EX");
		variableMap.put("Gross Contract Sales", "CONTRACT_SALES");
		variableMap.put("Contract Units", "CONTRACT_UNITS");
		variableMap.put("Contract Sales % of Total Contract Sales", "CONT_SALES_PER_OF_TOTAL_SALES");
		variableMap.put("Deduction $", "DISCOUNT_AMOUNT");
		variableMap.put("Deduction %", "DISCOUNT_RATE");
		variableMap.put("Deduction % of Ex-Factory", "DISC_PER_OF_EX");
		variableMap.put("Net Contract Sales", "NET_SALES");
		variableMap.put("Net Contract Sales % of Ex-Factory", "NET_CON_SALES_PER_OF_EX");
		variableMap.put("Net Ex-Factory Sales", "NET_EX_SALES");
		variableMap.put("Net Ex-Factory Sales % of Total Ex-Factory Sales", "NET_EXP_SALES_PER_OF_TOATL_EX");
		variableMap.put("Weighted GTN Contribution", "WEIGHTED_GTN_CONTRIBUTION");
		variableMap.put("RPU", "RPU");
		return variableMap;
	}

	public GtnWsPagedTreeTableResponse getReportLeftTableColumns() {
		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();
		tableHeaderDTO.addSingleColumn(LEVEL_VALUE, "Level", String.class);
		tableHeaderDTO.addDoubleColumn(LEVEL_VALUE, "");
		tableHeaderDTO.addTripleColumn(LEVEL_VALUE, "");
		return tableHeaderDTO;
	}

	public GtnWsPagedTreeTableResponse getReportRightTableColumnsDummy(
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		gtnForecastBean.setHistoryStartDate(new GregorianCalendar(2015, 5, 1, 0, 0, 0).getTime());
		gtnForecastBean.setHistoryEndDate(new GregorianCalendar(2017, 11, 1, 0, 0, 0).getTime());

		gtnForecastBean.setProjectionStartDate(new GregorianCalendar(2014, 0, 1, 0, 0, 0).getTime());
		gtnForecastBean.setProjectionEndDate(new GregorianCalendar(2021, 11, 1, 0, 0, 0).getTime());

		gtnForecastBean.setForecastStartDate(new GregorianCalendar(2014, 0, 1, 0, 0, 0).getTime());
		gtnForecastBean.setForecastEndDate(new GregorianCalendar(2018, 11, 1, 0, 0, 0).getTime());

		gtnForecastBean.setFrequency(SEMIANNUAL);

		gtnForecastBean.setActualOrProjection(ACTUALS);
		gtnForecastBean.setAscending(true);
		gtnForecastBean.setColumn(false);
		gtnForecastBean.setVariablesVariances(false);
		return this.getReportRightTableColumns(gtnUIFrameworkWebserviceRequest);

	}

	public GtnWsPagedTreeTableResponse getReportRightTableColumns(
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();
		try {
			GtnWsReportDashboardBean dashboardBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
					.getGtnWsReportDashboardBean();
			GtnWsReportDataSelectionBean dataSelectionBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
					.getDataSelectionBean();
			boolean isColumn = getColumnFlag(dashboardBean.getCustomViewMasterSid());
			List<GtnReportComparisonProjectionBean> beanList = dashboardBean.getComparisonProjectionBeanList();
			List<String> comparsionHeader = new ArrayList<>();
			int noSource = dataSelectionBean.getReportDataSource() != 3 ? 0 : 1;
			if (dataSelectionBean.getReportDataSource() != 3)
				comparsionHeader.add("Current Projection");
			if (beanList != null) {
				beanList.stream().forEach(bean -> comparsionHeader.add(bean.getProjectionName()));
			}
			String[] comparisonBasisHeader = comparsionHeader.stream().toArray(String[]::new);
			String[] comparisonBasisColumn = new String[comparisonBasisHeader.length];
			for (int i = 0; i < comparisonBasisHeader.length; i++) {
				comparisonBasisColumn[i] = String.valueOf(i + 1 + noSource);
			}
			if (isMandatoryFieldsAdded(dashboardBean, isColumn)) {
				return tableHeaderDTO;
			}
			String[] variablesHeader = dashboardBean.getSelectedVariableType();
			String[] variablesColumn = new String[variablesHeader.length];

			String[] variableCategoryHeader = dashboardBean.getSelectedVariableCategoryType();

			String[] variableCategoryColumn = new String[variableCategoryHeader.length];
			List<List<String>> timePeriodHeaderData = getTimeRange(dashboardBean);

			Object[] periodColumn = timePeriodHeaderData.get(1).toArray();
			Object[] periodHeader = timePeriodHeaderData.get(0).toArray();

			List<Object[]> combinedVariableCategoryList;
			Object[] combinedVariableCategoryColumn;
			Object[] combinedVariableCategoryHeader;
			generateColumn(variablesHeader, variablesColumn);
			generateColumn(variableCategoryHeader, variableCategoryColumn);

			int headerSequence = dashboardBean.getHeaderSequence();
			boolean isVariableOnlyAllowed = comparisonBasisHeader.length > 1
					&& Arrays.asList(variablesHeader).contains("Deduction % of Ex-Factory")
					&& Arrays.asList(variableCategoryHeader).contains(VARIANCE);
			if (isColumn) {
				combinedVariableCategoryList = getCombinedVariableCategory(variablesHeader, variableCategoryHeader,
						dashboardBean.isVariablesVariances(), isColumn, isVariableOnlyAllowed);
				combinedVariableCategoryColumn = combinedVariableCategoryList.get(0);
				combinedVariableCategoryHeader = combinedVariableCategoryList.get(1);
			} else {
				combinedVariableCategoryList = getCombinedVariableCategory(comparisonBasisHeader,
						variableCategoryHeader, dashboardBean.isVariablesVariances(), isColumn, isVariableOnlyAllowed);
				combinedVariableCategoryColumn = combinedVariableCategoryList.get(0);
				combinedVariableCategoryHeader = combinedVariableCategoryList.get(1);
			}

			switch (headerSequence) {
			case 0:// 0. Time/Variable/Comparison
				createTableHeader(
						Arrays.asList(comparisonBasisColumn, combinedVariableCategoryColumn, periodColumn,
								comparisonBasisHeader, combinedVariableCategoryHeader, periodHeader),
						tableHeaderDTO, headerSequence);
				break;
			case 1:// 1. Comparison/Variable/Time
				createTableHeader(Arrays.asList(periodColumn, combinedVariableCategoryColumn, comparisonBasisColumn,
						periodHeader, combinedVariableCategoryHeader, comparisonBasisHeader), tableHeaderDTO,
						headerSequence);
				break;
			case 2:// 2. Comparison/Time/Variable
				createTableHeader(
						Arrays.asList(combinedVariableCategoryColumn, periodColumn, comparisonBasisColumn,
								combinedVariableCategoryHeader, periodHeader, comparisonBasisHeader),
						tableHeaderDTO, headerSequence);
				break;
			case 3:// 3. Variable/Comparison/Time
				createTableHeader(Arrays.asList(periodColumn, comparisonBasisColumn, combinedVariableCategoryColumn,
						periodHeader, comparisonBasisHeader, combinedVariableCategoryHeader), tableHeaderDTO,
						headerSequence);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
		return tableHeaderDTO;
	}

	@SuppressWarnings("unchecked")
	private List<List<String>> getTimeRange(GtnWsReportDashboardBean dashboardBean) {
		try {
			LocalDate startDate = parseDate(dashboardBean.getPeriodStart(), dashboardBean.getSelectFreqString());
			LocalDate endDate = parseDate(dashboardBean.getPeriodTo(), dashboardBean.getSelectFreqString());
			Set<String> dateString = new LinkedHashSet<>();
			Set<String> dateheaderColumnId = new LinkedHashSet<>();
			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(1)) {
				getFormat(dateheaderColumnId, dateString, dashboardBean.getSelectFreqString(), date);
			}

			getFormat(dateheaderColumnId, dateString, dashboardBean.getSelectFreqString(), endDate);
			List<String> headerId = new ArrayList<>(dateheaderColumnId);
			List<String> headers = new ArrayList<>(dateString);
			if (dashboardBean.getSelectFreqString().equals(ANNUAL2)) {
				headers.replaceAll(s -> s + TOTAL);
			}
			addValuesToTimeRangeHeaderList(dashboardBean, headerId, headers, dateheaderColumnId);
			return Arrays.asList(headers.stream().distinct().collect(Collectors.toList()),
					headerId.stream().distinct().collect(Collectors.toList()));
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
			return Collections.emptyList();
		}
	}

	private void addValuesToTimeRangeHeaderList(GtnWsReportDashboardBean dashboardBean, List<String> headerId,
			List<String> headers, Set<String> dateheaderColumnId) {
		if (!dashboardBean.getSelectFreqString().equals(ANNUAL2) && dashboardBean.getAnnualTotals().equals("Yes")) {
			List<String> listForIteration = new ArrayList<>(headerId);
			Iterator<String> it = listForIteration.iterator();
			String frequency = dashboardBean.getSelectFreqString();
			int added = 0;
			String year = "";
			for (int i = 0; i < dateheaderColumnId.size(); i++) {
				String id = it.next();
				year = id.substring(1);
				Pattern p = Pattern.compile("[0-9]{6}");
				Matcher m = p.matcher(id);
				if (m.matches()) {
					year = id.substring(2);
				}

				if (addValuesToTimeRangeHeaderList(frequency, id)) {
					int index = i + ++added;
					if (index != headerId.size()) {
						headerId.add(index, year + TOTAL2);
						headers.add(index, year + TOTAL);
					} else {
						headerId.add(year + TOTAL2);
						headers.add(year + TOTAL);
					}
				}

			}
			headerId.add(year + TOTAL2);
			headers.add(year + TOTAL);
		}
	}

	private boolean addValuesToTimeRangeHeaderList(String frequency, String id) {
		return ((frequency.startsWith("Q") && (Integer.parseInt(id.substring(0, 1))) % 4 == 0)
				|| (frequency.startsWith("S") && (Integer.parseInt(id.substring(0, 1))) % 2 == 0)
				|| (frequency.startsWith("M") && (id.length() == 6)
						&& (Integer.parseInt(id.substring(0, 2))) % 12 == 0));
	}

	private static String[] getMOnthly(LocalDate date) {

		return new String[] { date.getMonth().name().substring(0, 3) + " " + date.getYear(),
				Integer.toString(date.getMonthValue()) + date.getYear() };
	}

	private static String[] getAnnual(LocalDate date) {
		return new String[] { String.valueOf(date.getYear()), String.valueOf(date.getYear()) };
	}

	private static String[] getSemiAnnual(LocalDate date) {
		return new String[] { "S" + (date.getMonthValue() > 5 ? 2 : 1) + " " + date.getYear(),
				Integer.toString(date.getMonthValue() > 5 ? 2 : 1) + date.getYear() };
	}

	private static String[] getQuaterly(LocalDate date) {
		return new String[] { "Q" + date.get(IsoFields.QUARTER_OF_YEAR) + " " + date.getYear(),
				Integer.toString(date.get(IsoFields.QUARTER_OF_YEAR)) + date.getYear() };
	}

	private static void getFormat(Set<String> dateheaderColumn, Set<String> dateString2, String selectedFrequency,
			LocalDate date) {
		String theDate = "";
		String headerColumn = "";
		String[] tempe = null;
		switch (selectedFrequency) {
		case "Quarter":
			tempe = getQuaterly(date);
			theDate = tempe[0];
			headerColumn = tempe[1];
			break;
		case "Semi-Annual":
			tempe = getSemiAnnual(date);
			theDate = tempe[0];
			headerColumn = tempe[1];
			break;
		case ANNUAL2:
			tempe = getAnnual(date);
			theDate = tempe[0];
			headerColumn = tempe[1];
			break;
		case "Month":
			tempe = getMOnthly(date);
			theDate = tempe[0];
			headerColumn = tempe[1];
			break;
		default:
			break;
		}
		dateString2.add(theDate);
		dateheaderColumn.add(headerColumn);

	}

	// ^([Q])([0-9])*
	// ^([S])([0-9])*
	// ^([0-9])

	private LocalDate parseDate(String periodStart, String selectedFreq) {
		LocalDate startDate = null;
		int previousQuaterLastMonth = 0;
		String[] yearData = periodStart.contains("-") ? periodStart.split("-") : periodStart.split("\\s+");
		Month startMonth = null;

		switch (selectedFreq) {

		case "Quarter":
			previousQuaterLastMonth = (Character.getNumericValue(yearData[0].trim().charAt(1)) - 1) * 3;
			startMonth = Month.of(previousQuaterLastMonth + 1);
			startDate = LocalDate.of(Integer.parseInt(yearData[1].trim()), startMonth, 1);
			break;
		case "Semi-Annual":
			previousQuaterLastMonth = (Character.getNumericValue(yearData[0].trim().charAt(1))) > 1 ? 6 : 1;
			startMonth = Month.of(previousQuaterLastMonth);
			startDate = LocalDate.of(Integer.parseInt(yearData[1].trim()), startMonth, 1);
			break;
		case ANNUAL2:
			startDate = LocalDate.of(Integer.parseInt(yearData[0].trim()), 1, 1);
			break;
		case "Month":
			startDate = LocalDate.of(Integer.parseInt(yearData[1].trim()), getMonthIntegerFromYear(yearData[0]), 1);
			break;
		default:
			break;
		}
		return startDate;
	}

	private boolean isMandatoryFieldsAdded(GtnWsReportDashboardBean dashboardBean, boolean isColumn) {
		return (dashboardBean.getSelectedVariableType().length == 0 && isColumn)
				|| dashboardBean.getSelectedVariableCategoryType().length == 0
				|| dashboardBean.getPeriodRangeToSid() == 0 || dashboardBean.getPeriodRangeFromSid() == 0;
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
			commonColumn = Integer.toString(year);
			commonHeader = Integer.toString(year);
			period = 12;
			break;
		case QUARTERLY:
			period = calculatePeriod(month, 3);
			commonColumn = Integer.toString(period) + year;
			commonHeader = "Q" + period + " " + year;
			period *= 3;
			break;
		case SEMIANNUAL:
			period = calculatePeriod(month, 6);
			commonColumn = Integer.toString(period) + year;
			commonHeader = "S" + period + " " + year;
			period *= 6;
			break;
		case MONTHLY:
			String monthName = getMonthForInt(month);
			commonColumn = Integer.toString(month) + year;
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

	private void createTableHeader(List<Object[]> input, GtnWsPagedTreeTableResponse tableHeaderDTO,
			int headerSequence) {

		Object[] singleColumn = input.get(0);
		Object[] doubleColumn = input.get(1);
		Object[] tripleColumn = input.get(2);
		Object[] singleHeader = input.get(3);
		Object[] doubleHeader = input.get(4);
		Object[] tripleHeader = input.get(5);
		List<String> tripleMap = new ArrayList<>();
		List<String> doubleMap = new ArrayList<>();

		for (int i = 0; i < tripleColumn.length; i++) {
			for (int j = 0; j < doubleColumn.length; j++) {
				for (int k = 0; k < singleColumn.length; k++) {
					Object single = createSingleColumn(singleColumn[k].toString(), doubleColumn[j].toString(),
							tripleColumn[i].toString(), headerSequence);
					tableHeaderDTO.addSingleColumn(single, singleHeader[k].toString(), String.class);
					doubleMap.add(single.toString());
				}
				tableHeaderDTO.addDoubleColumn(doubleColumn[j] + "" + tripleColumn[i], doubleHeader[j].toString());
				tableHeaderDTO.addDoubleHeaderMap(doubleColumn[j] + "" + tripleColumn[i], doubleMap.toArray());
				doubleMap.clear();
				tripleMap.add(doubleColumn[j] + "" + tripleColumn[i]);
			}
			tableHeaderDTO.addTripleColumn(tripleColumn[i], tripleHeader[i].toString());
			tableHeaderDTO.addTripleHeaderMap(tripleColumn[i], tripleMap.toArray());
			tripleMap.clear();
		}
	}

	public void createTableHeader(Object[] singleColumn, Object[] doubleColumn, Object[] singleHeader,
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

	private String createSingleColumn(String singleColumn, String doubleColumn, String tripleColumn,
			int headerSequence) {
		StringBuilder singleColumnValue = new StringBuilder();
		switch (headerSequence) {
		case 0:// 0. Time/Variable/Comparison
			singleColumnValue.append(tripleColumn);
			singleColumnValue.append(doubleColumn);
			singleColumnValue.append('_');
			singleColumnValue.append(singleColumn);
			break;
		case 1:// 1. Comparison/Variable/Time
			singleColumnValue.append(singleColumn);
			singleColumnValue.append(doubleColumn);
			singleColumnValue.append('_');
			singleColumnValue.append(tripleColumn);
			break;
		case 2:// 2. Comparison/Time/Variable
			singleColumnValue.append(doubleColumn);
			singleColumnValue.append(singleColumn);
			singleColumnValue.append('_');
			singleColumnValue.append(tripleColumn);
			break;
		case 3:// 3. Variable/Comparison/Time
			singleColumnValue.append(singleColumn);
			singleColumnValue.append(tripleColumn);
			singleColumnValue.append('_');
			singleColumnValue.append(doubleColumn);
			break;
		default:
			break;
		}
		return singleColumnValue.toString();
	}

	private List<Object[]> getCombinedVariableCategory(String[] firstHeader, String[] variableCategoryHeader,
			boolean isVariablesAndVariances, boolean isColumn, boolean isVariableOnlyAllowed) {
		List<Object[]> combinedVariableCategory = new ArrayList<>();
		List<String> categorySeperationList = new ArrayList<>();
		List<String> categoryWhichWillNotBeUnitedList = new ArrayList<>();
		List<String> variableCategoryOnlyColumn = Arrays.asList(VOLUME, "Rate", CHANGE_IN_CHANGE);

		variableCategoryListSpecialCondition(variableCategoryHeader, categorySeperationList,
				categoryWhichWillNotBeUnitedList, variableCategoryOnlyColumn);

		String[] variableCategoryHeaderCombinationColumOnly = categorySeperationList
				.toArray(new String[categorySeperationList.size()]);
		String[] variableOnlyHeader = categoryWhichWillNotBeUnitedList
				.toArray(new String[categoryWhichWillNotBeUnitedList.size()]);

		int combinedArraySize = (firstHeader.length * variableCategoryHeaderCombinationColumOnly.length)
				+ variableOnlyHeader.length;
		if (!isVariableOnlyAllowed) {
			combinedArraySize = (firstHeader.length * variableCategoryHeaderCombinationColumOnly.length);
		}
		Object[] combinedVariableCategoryColumn = new Object[combinedArraySize];
		Object[] combinedVariableCategoryHeader = new Object[combinedArraySize];

		Map<String, String> variableMap = getVariableMap();
		Map<String, String> variableCategoryMap = getVariableCategorymap();
		int index = 0;

		String[] variablesHeader = null;
		String[] variancesHeader = null;

		if (isVariablesAndVariancesAndColumn(isVariablesAndVariances, isColumn)) {
			variablesHeader = firstHeader;
			variancesHeader = variableCategoryHeaderCombinationColumOnly;
		} else {
			variablesHeader = variableCategoryHeaderCombinationColumOnly;
			variancesHeader = firstHeader;
		}

		for (int i = 0; i < variablesHeader.length; i++) {
			for (int j = 0; j < variancesHeader.length; j++) {
				if (isVariablesAndVariancesAndColumn(isVariablesAndVariances, isColumn)) {
					combinedVariableCategoryColumn[index] = variableMap.get(variablesHeader[i]) + "#"
							+ variableCategoryMap.get(variancesHeader[j]);
					combinedVariableCategoryHeader[index] = getPropertyHeaderString(isColumn,
							variablesHeader[i] + " " + variancesHeader[j], variablesHeader[i]);
				} else {
					String variable = variableMap.get(variancesHeader[j]);
					combinedVariableCategoryColumn[index] = getPropertyColumnString(variable, variablesHeader[i],
							variancesHeader[j], variableMap, variableCategoryMap);
					combinedVariableCategoryHeader[index] = getPropertyHeaderString(isColumn,
							variancesHeader[j] + " " + variablesHeader[i], variablesHeader[i]);
				}
				index++;
			}
		}
		if (isVariableOnlyAllowed) {
			for (int k = 0; k < variableOnlyHeader.length; k++) {
				combinedVariableCategoryColumn[index] = variableCategoryMap.get(variableOnlyHeader[k]);
				combinedVariableCategoryHeader[index] = variableOnlyHeader[k];
				index++;
			}
		}
		combinedVariableCategory.add(removeDuplicatesFromColumnProperty(combinedVariableCategoryColumn));
		combinedVariableCategory.add(removeDuplicatesFromColumnProperty(combinedVariableCategoryHeader));
		return combinedVariableCategory;
	}

	private String getPropertyColumnString(String variable, String variablesHeader, String variancesHeader,
			Map<String, String> variableMap, Map<String, String> variableCategoryMap) {
		return variable == null ? variableCategoryMap.get(variablesHeader)
				: variableMap.get(variancesHeader) + "#" + variableCategoryMap.get(variablesHeader);
	}

	private String getPropertyHeaderString(boolean isColumn, String propertyOne, String propertyTwo) {
		return isColumn ? propertyOne : propertyTwo;
	}

	private boolean isVariablesAndVariancesAndColumn(boolean isVariablesAndVariances, boolean isColumn) {
		return isVariablesAndVariances && isColumn;
	}

	private void variableCategoryListSpecialCondition(String[] variableCategoryHeader,
			List<String> categorySeperationList, List<String> categoryWhichWillNotBeUnitedList,
			List<String> variableCategoryOnlyColumn) {
		for (int i = 0; i < variableCategoryHeader.length; i++) {
			if (variableCategoryOnlyColumn.contains(variableCategoryHeader[i])) {
				categoryWhichWillNotBeUnitedList.add(variableCategoryHeader[i]);
			} else {
				categorySeperationList.add(variableCategoryHeader[i]);
			}
		}
	}

	private Object[] removeDuplicatesFromColumnProperty(Object[] combinedVariableCategoryColumn) {
		ArrayList<Object> columnProperty = new ArrayList<>();
		for (int i = 0; i < combinedVariableCategoryColumn.length - 1; i++) {
			if (combinedVariableCategoryColumn[i] != combinedVariableCategoryColumn[i + 1]) {
				columnProperty.add(combinedVariableCategoryColumn[i]);
			}
		}
		if (combinedVariableCategoryColumn.length > 0) {
			columnProperty.add(combinedVariableCategoryColumn[combinedVariableCategoryColumn.length - 1]);
			columnProperty.trimToSize();
			return columnProperty.toArray();
		}
		return columnProperty.toArray();

	}

	public List<Object[]> getTimeRange(GtnForecastBean gtnForecastBean) {
		List<Object[]> periods = new ArrayList<>();
		Date date = gtnForecastBean.getProjectionEndDate().after(gtnForecastBean.getForecastEndDate())
				? gtnForecastBean.getProjectionEndDate()
				: gtnForecastBean.getForecastEndDate();
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
			int month = startIndex;
			while (month <= endMonthIndex) {
				String[] returnValue = getCommonColumnHeader(gtnForecastBean.getFrequency(), year, month);
				periods.add(returnValue);
				month = Integer.parseInt(returnValue[2]);
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

	public String getVariableBreakdownPeriods(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		List variableBreakdown = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getDataSelectionBean()
				.getVariableBreakdownHeaderLoadList();
		String dateFromPeriodQuery = null;
		String splitParameter = " ";
		String frequency = "";
		StringBuilder whereClauseParameters = new StringBuilder();
		for (int i = 0; i < variableBreakdown.size(); i++) {
			String variableBreakdownFromPeriod = variableBreakdown.get(i).toString();
			if (variableBreakdownFromPeriod.startsWith("Q")) {
				List<Integer> vbQuarterToDateForFromPeriod = getQuarterToDate(variableBreakdownFromPeriod,
						splitParameter);
				dateFromPeriodQuery = getDateFromFrequency(vbQuarterToDateForFromPeriod);
				frequency = "QUARTER,";
			} else if (variableBreakdownFromPeriod.startsWith("S")
					&& !variableBreakdownFromPeriod.toLowerCase(Locale.ENGLISH).startsWith("sep")) {
				List<Integer> vbSemiAnnualToDateForFromPeriod = getSemiAnnualToDate(variableBreakdownFromPeriod,
						splitParameter);
				dateFromPeriodQuery = getDateFromFrequency(vbSemiAnnualToDateForFromPeriod);
				frequency = "SEMI_ANNUAL,";
			} else if (variableBreakdownFromPeriod.matches(GtnWsQueryConstants.YEAR_FREQUENCY)) {
				List<Integer> vbYearToDateForFromPeriod = new ArrayList<>();
				vbYearToDateForFromPeriod.add(Integer.valueOf(variableBreakdownFromPeriod));
				vbYearToDateForFromPeriod.add(1);
				vbYearToDateForFromPeriod.add(1);
				dateFromPeriodQuery = getDateFromFrequency(vbYearToDateForFromPeriod);
				frequency = "YEAR,";
			} else {
				List<Integer> vbMonthToDateForFromPeriod = new ArrayList<>();
				String[] monthToDateForFromPeriodSplit = variableBreakdownFromPeriod.split(" ");
				vbMonthToDateForFromPeriod.add(Integer.valueOf(monthToDateForFromPeriodSplit[1]));
				vbMonthToDateForFromPeriod.add(getMonthIntegerFromYear(monthToDateForFromPeriodSplit[0]));
				vbMonthToDateForFromPeriod.add(1);
				dateFromPeriodQuery = getDateFromFrequency(vbMonthToDateForFromPeriod);
				frequency = "MONTH,";
			}
			if (i < variableBreakdown.size() - 1) {
				whereClauseParameters.append(PERIOD_DATE);
				whereClauseParameters.append(dateFromPeriodQuery);
				whereClauseParameters.append("' OR  ");
			} else {
				whereClauseParameters.append(PERIOD_DATE);
				whereClauseParameters.append(dateFromPeriodQuery);
				whereClauseParameters.append("' ");
			}
		}
		String finalQuery = GtnWsQueryConstants.VARIABLE_AND_COMPARISON_BREAKDOWN_PERIOD_DATAS;
		finalQuery = finalQuery.replace("@periodDateCondition", whereClauseParameters.toString()).replace("@frequency",
				frequency);
		return finalQuery;
	}

	private String getDateFromFrequency(List<Integer> periodList) {
		StringBuilder date = new StringBuilder();
		for (int i = 0; i < periodList.size(); i++) {
			if (i < periodList.size() - 1) {
				date.append(periodList.get(i));
				date.append('-');
			} else {
				date.append(periodList.get(i));
			}
		}
		return date.toString().trim();
	}

	public String getComparisonBreakdownPeriods(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

		List comnparisonBreakdownList = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getDataSelectionBean()
				.getVariableBreakdownHeaderLoadList();
		String dateFromPeriodQuery = null;
		String splitParameter = " ";
		String frequency = "";
		StringBuilder whereClauseParameters = new StringBuilder();
		for (int i = 0; i < comnparisonBreakdownList.size(); i++) {
			String fromPeriod = comnparisonBreakdownList.get(i).toString();

			if (fromPeriod.startsWith("Q")) {
				List<Integer> quarterToDateForFromPeriod = getQuarterToDate(fromPeriod, splitParameter);
				dateFromPeriodQuery = getDateFromFrequency(quarterToDateForFromPeriod);
				frequency = "QUARTER,";
			} else if (fromPeriod.startsWith("S") && !fromPeriod.toLowerCase(Locale.ENGLISH).startsWith("sep")) {

				List<Integer> semiAnnualToDateForFromPeriod = getSemiAnnualToDate(fromPeriod, splitParameter);

				dateFromPeriodQuery = getDateFromFrequency(semiAnnualToDateForFromPeriod);
				frequency = "SEMI_ANNUAL,";

			} else if (fromPeriod.matches(GtnWsQueryConstants.YEAR_FREQUENCY)) {
				List<Integer> yearToDateForFromPeriod = new ArrayList<>();
				yearToDateForFromPeriod.add(Integer.valueOf(fromPeriod));
				yearToDateForFromPeriod.add(1);
				yearToDateForFromPeriod.add(1);

				dateFromPeriodQuery = getDateFromFrequency(yearToDateForFromPeriod);
				frequency = "YEAR,";

			} else {
				List<Integer> monthToDateForFromPeriod = new ArrayList<>();
				String[] monthToDateForFromPeriodSplit = fromPeriod.split(" ");
				monthToDateForFromPeriod.add(Integer.valueOf(monthToDateForFromPeriodSplit[1]));
				monthToDateForFromPeriod.add(getMonthIntegerFromYear(monthToDateForFromPeriodSplit[0]));
				monthToDateForFromPeriod.add(1);

				dateFromPeriodQuery = getDateFromFrequency(monthToDateForFromPeriod);
				frequency = "MONTH,";
			}
			if (i < comnparisonBreakdownList.size() - 1) {
				whereClauseParameters.append(PERIOD_DATE);
				whereClauseParameters.append(dateFromPeriodQuery);
				whereClauseParameters.append("' OR  ");
			} else {
				whereClauseParameters.append(PERIOD_DATE);
				whereClauseParameters.append(dateFromPeriodQuery);
				whereClauseParameters.append("' ");
			}
		}
		String finalQuery = GtnWsQueryConstants.VARIABLE_AND_COMPARISON_BREAKDOWN_PERIOD_DATAS;
		finalQuery = finalQuery.replace("@periodDateCondition", whereClauseParameters).replace("@frequency", frequency);

		return finalQuery;
	}

	public GtnWsPagedTableResponse getVariableBreakdownHeaderColumns(
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {

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
			getHeaderBasedOnFrequency(frequency, quarterToDateForFromPeriod, quarterToDateForToPeriod, tableHeaderDTO,
					fromPeriod);
		} else if (fromPeriod.startsWith("S")) {

			List<Integer> semiAnnualToDateForFromPeriod = getSemiAnnualToDate(fromPeriod, splitParameter);
			List<Integer> semiAnnualToDateForToPeriod = getSemiAnnualToDate(toPeriod, splitParameter);
			getHeaderBasedOnFrequency(frequency, semiAnnualToDateForFromPeriod, semiAnnualToDateForToPeriod,
					tableHeaderDTO, fromPeriod);

		} else if (fromPeriod.matches(GtnWsQueryConstants.YEAR_FREQUENCY)) {
			List<Integer> yearToDateForFromPeriod = new ArrayList<>();
			yearToDateForFromPeriod.add(Integer.valueOf(fromPeriod));
			yearToDateForFromPeriod.add(1);
			yearToDateForFromPeriod.add(1);
			List<Integer> yearToDateForToPeriod = new ArrayList<>();
			yearToDateForToPeriod.add(Integer.valueOf(toPeriod));
			yearToDateForToPeriod.add(1);
			yearToDateForToPeriod.add(1);
			getHeaderBasedOnFrequency(frequency, yearToDateForFromPeriod, yearToDateForToPeriod, tableHeaderDTO,
					fromPeriod);
		} else {
			List<Integer> monthToDateForFromPeriod = new ArrayList<>();
			String[] monthToDateForFromPeriodSplit = fromPeriod.contains("-") ? fromPeriod.split(splitParameter)
					: fromPeriod.split(" ");
			monthToDateForFromPeriod.add(Integer.valueOf(monthToDateForFromPeriodSplit[1].trim()));
			monthToDateForFromPeriod.add(getMonthIntegerFromYear(monthToDateForFromPeriodSplit[0].trim()));
			monthToDateForFromPeriod.add(1);

			List<Integer> monthToDateForToPeriod = new ArrayList<>();
			String[] monthToDateForToPeriodSplit = toPeriod.contains("-") ? toPeriod.split(splitParameter)
					: toPeriod.split(" ");
			monthToDateForToPeriod.add(Integer.valueOf(monthToDateForToPeriodSplit[1].trim()));
			monthToDateForToPeriod.add(getMonthIntegerFromYear(monthToDateForToPeriodSplit[0].trim()));
			monthToDateForToPeriod.add(1);
			getHeaderBasedOnFrequency(frequency, monthToDateForFromPeriod, monthToDateForToPeriod, tableHeaderDTO,
					fromPeriod);
		}
		return tableHeaderDTO;
	}

	private void getHeaderBasedOnFrequency(String frequency, List<Integer> quarterToDateForFromPeriod,
			List<Integer> quarterToDateForToPeriod, GtnWsPagedTableResponse tableHeaderDTO, String fromPeriod) {

		loadingTableHeaderDtoForAnnual(frequency, quarterToDateForFromPeriod, quarterToDateForToPeriod, tableHeaderDTO);
		loadingTableHeaderDtoForSemiAnnual(frequency, quarterToDateForFromPeriod, quarterToDateForToPeriod,
				tableHeaderDTO);
		loadingTableHeaderDtoForQuater(frequency, quarterToDateForFromPeriod, quarterToDateForToPeriod, tableHeaderDTO);
		loadingTableHeaderDtoForMonthly(frequency, quarterToDateForFromPeriod, quarterToDateForToPeriod, tableHeaderDTO,
				fromPeriod);
	}

	private void loadingTableHeaderDtoForAnnual(String frequency, List<Integer> quarterToDateForFromPeriod,
			List<Integer> quarterToDateForToPeriod, GtnWsPagedTableResponse tableHeaderDTO) {
		if (frequency.startsWith("Ann")) {
			Integer startYear = quarterToDateForFromPeriod.get(0);
			Integer endYear = quarterToDateForToPeriod.get(0);
			for (int i = startYear; i <= endYear; i++) {
				tableHeaderDTO.addSingleColumn(String.valueOf(i) + "year", String.valueOf(i), String.class);
			}
		}
	}

	private void loadingTableHeaderDtoForQuater(String frequency, List<Integer> quarterToDateForFromPeriod,
			List<Integer> quarterToDateForToPeriod, GtnWsPagedTableResponse tableHeaderDTO) {
		if (frequency.startsWith("Quar") || frequency.startsWith("-Sel")) {
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
			int quarterEnding;
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
					tableHeaderDTO.addSingleColumn("q" + j + i + "quarter", "Q" + j + " " + i, String.class);
				}
			}
		}
	}

	private void loadingTableHeaderDtoForSemiAnnual(String frequency, List<Integer> quarterToDateForFromPeriod,
			List<Integer> quarterToDateForToPeriod, GtnWsPagedTableResponse tableHeaderDTO) {
		if (frequency.startsWith("Sem")) {
			int semiAnnualStarting = 1;
			int semiAnnualEnding;

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
					tableHeaderDTO.addSingleColumn("s" + j + i + "semiAnnual", "S" + j + " " + i, String.class);
				}
			}
		}
	}

	private void loadingTableHeaderDtoForMonthly(String frequency, List<Integer> quarterToDateForFromPeriod,
			List<Integer> quarterToDateForToPeriod, GtnWsPagedTableResponse tableHeaderDTO, String fromPeriod) {
		if (frequency.startsWith("Mon")) {
			Integer startYear = quarterToDateForFromPeriod.get(0);
			Integer endYear = quarterToDateForToPeriod.get(0);

			int monthStarting = 1;
			int monthEnding;

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
				addMonthlyHeader(monthStarting, monthEnding, i, tableHeaderDTO);
			}
		}
	}

	private void addMonthlyHeader(int monthStarting, int monthEnding, int i, GtnWsPagedTableResponse tableHeaderDTO) {
		for (int j = monthStarting; j <= monthEnding; j++) {
			tableHeaderDTO.addSingleColumn(getMonthFromYear(j) + "month" + i, getMonthFromYear(j) + " " + i,
					String.class);
		}
	}

	private List<Integer> getQuarterToDate(String fromPeriod, String splitParameter) {
		String[] quarterToDateSplit = fromPeriod.trim().split(splitParameter);
		List<Integer> quarterToDate = new ArrayList<>();
		quarterToDate.add(0, Integer.valueOf(quarterToDateSplit[1].trim()));

		quarterToDate.add(1,
				Integer.valueOf(returnMonthForQuarter(String.valueOf(quarterToDateSplit[0].trim().charAt(1)))));

		quarterToDate.add(2, Integer.valueOf("1"));
		return quarterToDate;
	}

	private List<Integer> getSemiAnnualToDate(String fromPeriod, String splitParameter) {
		String[] semiAnnual = fromPeriod.trim().split(splitParameter);
		List<Integer> semiAnnualToDate = new ArrayList<>();
		semiAnnualToDate.add(0, Integer.valueOf(semiAnnual[1].trim()));

		semiAnnualToDate.add(1,
				Integer.valueOf(returnMonthOfSemiAnnual(String.valueOf(semiAnnual[0].trim().charAt(1)))));

		semiAnnualToDate.add(2, Integer.valueOf("1"));
		return semiAnnualToDate;
	}

	private String returnMonthOfSemiAnnual(String charAt) {
		String semiAnnualInMonth = "";
		switch (charAt) {
		case "1":
			semiAnnualInMonth = "1";
			break;
		case "2":
			semiAnnualInMonth = "7";
			break;
		default:
			break;
		}
		return semiAnnualInMonth;
	}

	private String returnMonthForQuarter(String charAt) {
		String quarterInMonth = "";
		switch (charAt) {
		case "1":
			quarterInMonth = "1";
			break;
		case "2":
			quarterInMonth = "4";
			break;
		case "3":
			quarterInMonth = "7";
			break;
		case "4":
			quarterInMonth = "10";
			break;
		default:
			break;
		}
		return quarterInMonth;
	}

	private String getMonthFromYear(int count) {
		return getMonthStringYear(count);
	}

	private String getMonthStringYear(int count) {
		Map<Integer, String> month = new HashMap<>(12);
		month.put(1, "JAN");
		month.put(2, "FEB");
		month.put(3, "MAR");
		month.put(4, "APR");
		month.put(5, "MAY");
		month.put(6, "JUN");
		month.put(7, "JUL");
		month.put(8, "AUG");
		month.put(9, "SEP");
		month.put(10, "OCT");
		month.put(11, "NOV");
		month.put(12, "DEC");
		String monthStr = month.get(count);
		return monthStr == null ? StringUtils.EMPTY : monthStr;
	}

	private int getMonthIntegerFromYear(String month) {
		return getMonthIntegerFromMap(month.toUpperCase(Locale.ENGLISH));
	}

	private int getMonthIntegerFromMap(String monthStr) {
		Map<String, Integer> month = new HashMap<>(12);
		month.put("JAN", 1);
		month.put("FEB", 2);
		month.put("MAR", 3);
		month.put("APR", 4);
		month.put("MAY", 5);
		month.put("JUN", 6);
		month.put("JUL", 7);
		month.put("AUG", 8);
		month.put("SEP", 9);
		month.put("OCT", 10);
		month.put("NOV", 11);
		month.put("DEC", 12);
		Integer monthInt = month.get(monthStr);
		return monthInt == null ? 0 : monthInt.intValue();
	}

	private boolean getColumnFlag(int customViewMasterSid) {
		List<Object[]> result = reportDataSelectionSql.getCustomViewType(customViewMasterSid);
		if (Optional.ofNullable(result).isPresent() && !result.isEmpty()) {
			String customViewType = String.valueOf(result.get(0));
			String[] customViewTypeDataArray = customViewType.split("~");
			return customViewTypeDataArray[2].equals("Columns");
		}
		return false;
	}
}