package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.pagetreetable.GtnWsPagedTreeTableResponse;

@Service
public class HeaderGeneratorService {

	private GtnWSLogger gtnWSLogger = GtnWSLogger.getGTNLogger(HeaderGeneratorService.class);
	
	public static final String MONTHLY = "Monthly";
	public static final String QUARTERLY = "Quarterly";
	public static final String SEMIANNUAL = "Semi-Annually";
	public static final String ANNUAL = "Annually";

	public GtnWsPagedTreeTableResponse getProjectionVarianceLeftTableColumns(GtnWsForecastRequest request)
			throws GtnFrameworkGeneralException {

		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();
		
		tableHeaderDTO.addSingleColumn("leftSingleHeader", "", String.class);
		tableHeaderDTO.addDoubleHeaderMap("leftDoubleHeader",new Object[] {"leftSingleHeader"});
		tableHeaderDTO.addDoubleColumn("leftDoubleHeader", "");

		return tableHeaderDTO;
	}	
	
	public GtnWsPagedTreeTableResponse getProjectionVarianceRightTableColumns(GtnWsForecastRequest request)
			throws GtnFrameworkGeneralException {

		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();

		tableHeaderDTO.addSingleColumn("currentProjection2018", "Current Projection",String.class);
		tableHeaderDTO.addSingleColumn("currentProjection2019", "Current Projection",String.class);
		tableHeaderDTO.addSingleColumn("currentProjection2020", "Current Projection",String.class);
		
		tableHeaderDTO.addDoubleHeaderMap("D2018",new Object[] {"currentProjection2018"});
		tableHeaderDTO.addDoubleHeaderMap("D2019",new Object[] {"currentProjection2019"});
		tableHeaderDTO.addDoubleHeaderMap("D2020",new Object[] {"currentProjection2020"});
		
		tableHeaderDTO.addDoubleColumn("D2018", "2018");
		tableHeaderDTO.addDoubleColumn("D2019", "2019");
		tableHeaderDTO.addDoubleColumn("D2020", "2020");
		
		return tableHeaderDTO;
	}
	
	public GtnWsPagedTreeTableResponse getDiscountProjectionLeftTableColumns(GtnWsForecastRequest request)
			{

		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();
		tableHeaderDTO.addSingleColumn("filterComboBox", "",String.class);
		tableHeaderDTO.addSingleColumn("filterTextBox", "Level Name",String.class);
		
		tableHeaderDTO.addDoubleHeaderMap("firstDoubleLeftHeaderId",new Object[] {"filterComboBox"});
		tableHeaderDTO.addDoubleHeaderMap("levelName",new Object[] {"filterTextBox"});
		
		tableHeaderDTO.addDoubleColumn("firstDoubleLeftHeaderId", "");
		tableHeaderDTO.addDoubleColumn("levelName", "Level Name");
		
		return tableHeaderDTO;
	}
	
	public GtnWsPagedTreeTableResponse getDiscountProjectionRightTableColumns(GtnWsForecastRequest request)	{

		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();

		tableHeaderDTO.addSingleColumn("q12017actualRate", "Actual Rate", String.class);
		tableHeaderDTO.addSingleColumn("q12017actualRPU", "Actual RPU", String.class);
		tableHeaderDTO.addSingleColumn("q12017actualAmount", "Actual Amount", String.class);
		tableHeaderDTO.addSingleColumn("q12017projectedRate", "Projected Rate", String.class);
		tableHeaderDTO.addSingleColumn("q12017projectedRPU", "Projected RPU", String.class);
		tableHeaderDTO.addSingleColumn("q12017projectedAmount", "Projected Amount", String.class);
		tableHeaderDTO.addSingleColumn("q12017growth", "Growth", String.class);
		
		tableHeaderDTO.addDoubleHeaderMap("q1-17",new Object[] {"q12017actualRate","q12017actualRPU","q12017actualAmount","q12017projectedRate","q12017projectedRPU","q12017projectedAmount","q12017growth"});
	
		
		tableHeaderDTO.addSingleColumn("q22017actualRate", "Actual Rate", String.class);
		tableHeaderDTO.addSingleColumn("q22017actualRPU", "Actual RPU", String.class);
		tableHeaderDTO.addSingleColumn("q22017actualAmount", "Actual Amount", String.class);
		tableHeaderDTO.addSingleColumn("q22017projectedRate", "Projected Rate", String.class);
		tableHeaderDTO.addSingleColumn("q22017projectedRPU", "Projected RPU", String.class);
		tableHeaderDTO.addSingleColumn("q22017projectedAmount", "Projected Amount", String.class);
		tableHeaderDTO.addSingleColumn("q22017growth", "Growth", String.class);
		
		tableHeaderDTO.addDoubleHeaderMap("q2-17",new Object[] {"q22017actualRate","q22017actualRPU","q22017actualAmount","q22017projectedRate","q22017projectedRPU","q22017projectedAmount","q22017growth"});
		//tableHeaderDTO.addDoubleHeaderMap("q3-17",new Object[] {"actualRate","actualRPU","actualAmount","projectedRate","projectedRPU","projectedAmount","growth"});
		//tableHeaderDTO.addDoubleHeaderMap("q4-17",new Object[] {"actualRate","actualRPU","actualAmount","projectedRate","projectedRPU","projectedAmount","growth"});
		
		tableHeaderDTO.addDoubleColumn("q1-17", "Q1 2017");
		
		tableHeaderDTO.addDoubleColumn("q2-17", "Q2 2017");
		
		
		
		tableHeaderDTO.addTripleHeaderMap("q1-17",new Object[] {"q22017actualRate","q22017actualRPU","q22017actualAmount","q22017projectedRate","q22017projectedRPU","q22017projectedAmount","q22017growth","q12017actualRate","q12017actualRPU","q12017actualAmount","q12017projectedRate","q12017projectedRPU","q12017projectedAmount","q12017growth"});
		
		tableHeaderDTO.addTripleColumn("allDiscount", "All Discount");
		
		//tableHeaderDTO.addDoubleColumn();
		/*tableHeaderDTO.addDoubleColumn("q3-17", "Q3 2017");
		tableHeaderDTO.addDoubleColumn("q4-17", "Q4 2017");*/
		
		return tableHeaderDTO;
	}

	public GtnWsPagedTreeTableResponse getSalesProjectionLeftTableColumns(GtnWsForecastRequest request) {
		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();
		
		tableHeaderDTO.addSingleColumn("filterTextBox1", "",String.class);
		tableHeaderDTO.addSingleColumn("filterTextBox2", "Level Name",String.class);
		tableHeaderDTO.addSingleColumn("filterTextBox3", "Base Line",String.class);
		tableHeaderDTO.addSingleColumn("filterTextBox4", "Methodology",String.class);
		
		/*tableHeaderDTO.addDoubleHeaderMap("firstLeftHeaderId",new Object[] {"filterTextBox1"});
		tableHeaderDTO.addDoubleHeaderMap("levelName",new Object[] {"filterTextBox2"});
		tableHeaderDTO.addDoubleHeaderMap("baseLine",new Object[] {"filterTextBox3"});
		tableHeaderDTO.addDoubleHeaderMap("methodology",new Object[] {"filterTextBox4"});
		
		tableHeaderDTO.addDoubleColumn("firstLeftHeaderId", "");
		tableHeaderDTO.addDoubleColumn("levelName", "Level Name");
		tableHeaderDTO.addDoubleColumn("baseLine", "Base Line");
		tableHeaderDTO.addDoubleColumn("methodology", "Methodology");*/
		
		
		/*tableHeaderDTO.addSingleColumn("levelName", "Level Name", String.class);
		tableHeaderDTO.addSingleColumn("baseLine", "Base Line", String.class);
		tableHeaderDTO.addSingleColumn("methodology", "Methodology", String.class);*/

		return tableHeaderDTO;
	}
	
	public GtnWsPagedTreeTableResponse getSalesProjectionRightTableColumns(GtnWsForecastRequest request){

		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();

		tableHeaderDTO.addSingleColumn("actualSales", "Actual Sales", String.class);
		tableHeaderDTO.addSingleColumn("actualUnits", "Actual Units", String.class);
		tableHeaderDTO.addSingleColumn("projectedSales", "Projected Sales", String.class);
		tableHeaderDTO.addSingleColumn("projectedUnits", "Projected Units", String.class);

		tableHeaderDTO.addDoubleHeaderMap("q1-2017",new Object[] {"actualSales","actualUnits","projectedSales","projectedUnits"});
		
		tableHeaderDTO.addDoubleColumn("q1-2017", "2017");
		tableHeaderDTO.addDoubleColumn("SalesProjection2019", "2019");
		tableHeaderDTO.addDoubleColumn("SalesProjection2020", "2020");

		return tableHeaderDTO;
	}

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
		tableHeaderDTO.addSingleColumn("levelName", "Level", String.class);
		// }
		gtnWSLogger.info("left header single column added");
		// for (int i = 0; i < doubleColumn.length; i++) {
		tableHeaderDTO.addDoubleColumn("levelName", "");
		gtnWSLogger.info("left header duoble column added");
		tableHeaderDTO.addTripleColumn("levelName", "");
		gtnWSLogger.info("left header single triple added");
		// }


		// for (int i = 0; i < doubleHeaderMapKey.length; i++) {
		// String[] mapValue = doubleHeaderMapValue[i].split("-");
		 //tableHeaderDTO.addDoubleHeaderMap(doubleHeaderMapKey[i], mapValue);
		// }

		return tableHeaderDTO;
	}

	public GtnWsPagedTreeTableResponse getReportRightTableColumnsDummy() {
		HeaderGeneratorService header = new HeaderGeneratorService();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();

		// gtnForecastBean.setHistoryStartYear(2014);
		// gtnForecastBean.setHistoryStartMonth(0);
		// gtnForecastBean.setHistoryEndYear(2017);
		// gtnForecastBean.setHistoryEndMonth(0);
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

		gtnForecastBean.setFrequency(QUARTERLY);
		// gtnForecastBean.setSelectedHistory("3");
		gtnForecastBean.setActualOrProjection("Actuals");
		gtnForecastBean.setAscending(true);
		gtnForecastBean.setColumn(true);
		gtnForecastBean.setVariablesVariances(true);
		GtnWsPagedTreeTableResponse response = header.getReportRightTableColumns(gtnForecastBean);
		return response;

	}

	public GtnWsPagedTreeTableResponse getReportRightTableColumns(GtnForecastBean gtnForecastBean) {

		GtnWsPagedTreeTableResponse tableHeaderDTO = new GtnWsPagedTreeTableResponse();

		String[] comparisonBasisColumn = new String[] { "Actuals", "Accruals", "CurrentProjection", "Projection1",
				"Projection2", "Projection3", "Projection4", "Projection5" };
		String[] comparisonBasisHeader = new String[] { "Actuals", "Accruals", "Current Projection",
				"Prior Projection1", "Prior Projection2", "Prior Projection3", "Prior Projection4",
				"Prior Projection5" };

		String[] variablesHeader = new String[] { "Ex-Factory Sales", "Gross Contract Sales % of Ex-Factory",
				"Gross Contract Sales", "Contract Units", "Contract Sales % of Total Contract Sales", "Deduction $",
				"Deduction %", "RPU", "Deduction % of Ex-Factory", "Net Contract Sales",
				"Net Contract Sales % of Ex-Factory", "Net Ex-Factory Sales",
				"Net Ex-Factory Sales % of Total Ex-Factory", "Weighted GTN Contribution" };

		// String[] variablesColumn = new String[]{"exfactory",
		// "grossContractSalesPerExFactory"};
		String[] variablesColumn = new String[] { "exfactory", "grossContractSalesPerExFactory", "contractSales",
				"contractUnits", "contractSalesPerTotalContractSales", "discountAmount", "discountPercent", "rpu",
				"deductionPerExfactory", "netContractSales", "netContractSalesPerExfactory", "netExfactorySales",
				"netExfactorySalesPerTotalExfactory", "weightedGtn" };

		String[] variableCategoryColumn = new String[] { "Value", "Variance", "PerChange", "Volume", "Rate",
				"ChangeInChange" };
		String[] variableCategoryHeader = new String[] { "Value", "Variance", "% Change", "Volume", "Rate",
				"Change in Change" };

		List<Object[]> periods = getTimeRange(gtnForecastBean);
		Object[] periodColumn = periods.get(0);
		Object[] periodHeader = periods.get(1);

		List<Object[]> combinedVariableCategoryList = null;
		Object[] combinedVariableCategoryColumn = null;
		Object[] combinedVariableCategoryHeader = null;

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
			commonColumn = "Q" + period + "-" + year;
			commonHeader = "Q" + period + " " + year;
			period *= 3;
			break;
		case SEMIANNUAL:
			period = calculatePeriod(month, 6);
			commonColumn = "S" + period + "-" + year;
			commonHeader = "S" + period + " " + year;
			period *= 6;
			break;
		case MONTHLY:
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
			singleColumnValue.append("~");
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
					combinedVariableCategoryColumn[index] = variancesColumn[j].trim() + variablesColumn[i].trim();
					combinedVariableCategoryHeader[index] = variancesHeader[j] + " " + variablesHeader[i];
				} else {
					combinedVariableCategoryColumn[index] = variablesColumn[i].trim() + variancesColumn[j].trim();
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
				? gtnForecastBean.getProjectionEndDate() : gtnForecastBean.getForecastEndDate();
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

}
