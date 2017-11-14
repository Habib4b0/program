/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.calculation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ws.components.smallhashmap.GtnSmallHashMap;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsFileIOService;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsResourceService;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

/**
 *
 * @author STPL
 */
@Service
public class GtnWsCalculationUtilService {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCalculationUtilService.class);

	@Autowired
	private GtnWsReturnsResourceService resourceService;

	@Autowired
	private GtnWsReturnsFileIOService ioService;

	public double doubleConversation(String value) {
		try {
			double result = Double.parseDouble(value);
			if (Double.isInfinite(Double.valueOf(value))) {
				result = 0.0;
			}
			return result;
		} catch (NumberFormatException ex) {
			return 0.0;
		}
	}

	/**
	 * To calculate the index of the column from the base file. Index is not
	 * based on the file index it is based on the list. Each hierarchy will be
	 * in separate list so List<List> it will return from file. So in particular
	 * hierarchy(inner list) we will calculate the index of the value
	 *
	 * @param month
	 * @param year
	 * @param columnLength
	 *            --> ColumnLength is used to define number of columns in one
	 *            month
	 * @param indexOfColumn
	 *            --> Index of the column from where we need to get the value
	 * @return
	 */
	public int getIndex(int month, int year, final int columnLength, final int indexOfColumn) {
		// To traverse upto the year before the selected year
		// ((Number of the year need to traverse #{ie:1 or 2} * 12 #{(12 month
		// in a year)} * Number of columns in one month)
		int traverseYear = ((year * 12) * columnLength);

		// To get the previous month of selected month
		month = month - 1;

		// To traverse upto the month before the selected month.
		// (Number of coulmn in one month * perivous month of selected month) =
		// index of the selected month starting in the selected year.
		int traverseMonth = (columnLength * month);

		int index = traverseYear + traverseMonth + indexOfColumn;

		return index;
	}

	/**
	 * To return the start month and year of selected periods.
	 *
	 * @param frequency
	 * @param selectedPreiod
	 * @param isToCalculateEndDate
	 * @return
	 * @throws ParseException
	 */
	public Integer[] getStartMonthAndYear(final String frequency, final String selectedPreiod,
			boolean isToCalculateEndDate) throws GtnFrameworkGeneralException {
		Integer[] monthAndYear = new Integer[2];
		switch (frequency) {
		case CommonConstants.ANNUAL:
			monthAndYear[0] = 1;
			monthAndYear[1] = Integer.valueOf(selectedPreiod);
			break;
		case CommonConstants.QUARTERLY:
			monthAndYear = calculateMonthAndYear(selectedPreiod, 3, "Q", isToCalculateEndDate);
			break;
		case CommonConstants.SEMIANNUAL:
			monthAndYear = calculateMonthAndYear(selectedPreiod, 6, "S", isToCalculateEndDate);
			break;
		case CommonConstants.MONTHLY:
			monthAndYear = getIntForMonthAndYear(selectedPreiod);
			break;
		default:
			break;
		}
		return monthAndYear;
	}

	/**
	 * To calculate the starting month and year based on the selected periods.
	 * Eg: Q2 2016 is return as 4th month and 2016 year
	 *
	 * @param selectedPreiod
	 * @param division
	 * @param valueToReplace
	 * @param isToCalculateEndDate
	 * @return
	 */
	private Integer[] calculateMonthAndYear(String selectedPreiod, int division, final String valueToReplace,
			boolean isToCalculateEndDate) {
		selectedPreiod = selectedPreiod.replace(valueToReplace, "");
		String[] monthAndYear = selectedPreiod.split(" ");
		int value = Integer.valueOf(monthAndYear[0]);
		int month = 0;
		if (isToCalculateEndDate) {
			month = ((value - 1) * division) + division;
		} else {
			month = ((value - 1) * division) + 1;
		}

		return new Integer[] { month, Integer.valueOf(monthAndYear[1]) };
	}

	/**
	 * To calculate the starting month and year based on the selected periods.
	 * Eg: Feb 2016 is return month as 2 and year as 2016
	 *
	 * @param selectedPreiod
	 * @return
	 * @throws ParseException
	 */
	private Integer[] getIntForMonthAndYear(String selectedPeriod) throws GtnFrameworkGeneralException {
		String[] monthAndYear = selectedPeriod.split(" ");
		int month;

		try {
			Date date;
			date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(monthAndYear[0]);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			month = cal.get(Calendar.MONTH);
		} catch (ParseException e) {
			month = Integer.valueOf(monthAndYear[0].replace("M", ""));
		}

		return new Integer[] { month + 1, Integer.valueOf(monthAndYear[1]) };
	}

	/**
	 * To get the months. For Example, Q2 2014 is selected, it will return 4
	 * 2014, 5 2014 and 6 2014 Since 4th,5th and 6th month fall in Q2. It will
	 * used to get the data in file(In file we will month level data)
	 *
	 * @param frequency
	 * @param period
	 * @return
	 * @throws ParseException
	 */
	public List<Integer[]> getMonthFormPeriod(final String frequency, final String period)
			throws GtnFrameworkGeneralException {
		List<Integer[]> months = new ArrayList<>();
		Integer[] monthAndYear = getStartMonthAndYear(frequency, period, false);

		switch (frequency) {
		case CommonConstants.ANNUAL:
			monthAndYear[0] = 1;
			getSelectedMonth(monthAndYear, 12, months);
			break;
		case CommonConstants.QUARTERLY:
			getSelectedMonth(monthAndYear, 3, months);
			break;
		case CommonConstants.SEMIANNUAL:
			getSelectedMonth(monthAndYear, 6, months);
			break;
		case CommonConstants.MONTHLY:
			months.add(monthAndYear);
			break;
		default:
			break;
		}
		return months;
	}

	/**
	 * It will calculate number of months in selected period(ie: Q2, S2 etc)
	 *
	 * @param monthAndYear
	 * @param numberOfMonths
	 * @param months
	 * @return
	 */
	private List<Integer[]> getSelectedMonth(Integer[] monthAndYear, int numberOfMonths, final List<Integer[]> months) {
		int startingMonth = monthAndYear[0];
		for (int j = 0; j < numberOfMonths; j++) {
			months.add(new Integer[] { startingMonth, monthAndYear[1] });
			startingMonth++;
		}
		return months;
	}

	/**
	 * To return the list of column in required file using the property key
	 *
	 * @param propertyKey
	 * @return
	 */
	public List<String> getColumnList(final String propertyKey) {
		String[] columnNames = resourceService
				.resourceFileName(GtnFrameworkWebserviceConstant.FILENAME, CommonConstants.RESOURCES_PATH, propertyKey)
				.split(",");
		return Arrays.asList(columnNames);
	}

	public List<List<Object>> getFileData(GtnForecastBean gtnForecastBean, final String fileName)
			throws GtnFrameworkGeneralException {
		String basePath = ioService.getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		return ioService.readJsonDataFromFile(basePath + fileName + GtnFrameworkCommonStringConstants.STPL_EXTENSION);
	}

	public <T extends Object> T readJSONDataFromFile(String fileName, Class<T> clazz)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new FileInputStream(fileName), clazz);
	}

	public String getFilePath(final String moduleName, final String userId, final String sessionId) throws IOException {
		StringBuilder filePath = new StringBuilder(resourceService.resourceFileName(
				GtnFrameworkWebserviceConstant.FILENAME, CommonConstants.RESOURCES_PATH, "base_path"));
		filePath.append("/");
		filePath.append(moduleName);
		filePath.append("/");
		filePath.append(getFormattedDate());
		filePath.append("/");
		filePath.append(userId);
		filePath.append("/");
		filePath.append(sessionId);
		filePath.append("/");
		Path path = Paths.get(filePath.toString());
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
		return filePath.toString();
	}

	public void writeToFile(GtnForecastBean gtnForecastBean, final String fileName,
			final List<List<Object>> projectionFileList) throws GtnFrameworkGeneralException {
		String basePath = ioService.getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		ioService.writeJsonDataToFile(basePath + fileName + GtnFrameworkCommonStringConstants.STPL_EXTENSION,
				projectionFileList);
	}

	private String getFormattedDate() {
		String dateFormat = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.FILENAME,
				CommonConstants.RESOURCES_PATH, "date_format");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(new Date());
	}

	/**
	 * To calculate the given values for the formula in the
	 * properties.return-formula file
	 *
	 * @param propertyKey
	 *            --> Key to get the formula
	 * @param inputs
	 *            --> inputs to the formula
	 * @return
	 * @throws EvaluationException
	 */
	public double calculate(final String propertyKey, final GtnSmallHashMap inputs)
			throws GtnFrameworkGeneralException {
		Evaluator evaluator = new Evaluator();
		String expression = resourceService.resourceFileName("return-formula", CommonConstants.RESOURCES_PATH,
				propertyKey);
		for (int i = 0; i < inputs.size(); i++) {
			evaluator.putVariable(inputs.getIndex(i).getKey().toString(), inputs.getIndex(i).getValue().toString());
		}
		try {
			return evaluator.getNumberResult(expression);
		} catch (EvaluationException e) {
			LOGGER.error("Exception while Excuting calculate method", e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	/**
	 * To get the required column name and column index in query selection
	 *
	 * @param columnNameAndIndex
	 * @param propertyKey
	 */
	public int getIndexOfColumnNames(GtnSmallHashMap columnNameAndIndex, String propertyKey) {
		String[] neededColumns = resourceService
				.resourceFileName("return-formula", CommonConstants.RESOURCES_PATH, propertyKey).split(",");
		List<String> columnNames = getColumnList(propertyKey);
		for (String columnName : neededColumns) {
			columnNameAndIndex.put(columnName, columnNames.indexOf(columnName));
		}

		neededColumns = resourceService
				.resourceFileName(GtnFrameworkWebserviceConstant.FILENAME, CommonConstants.RESOURCES_PATH, propertyKey)
				.split(",");
		return neededColumns.length;
	}

	public void getCalculationPeriods(final List<Integer[]> periods, final GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {

		int endMonthIndex = 12;
		Integer[] calculatedStartMonthAndYear = getStartMonthAndYear(gtnForecastBean.getFrequency(),
				gtnForecastBean.getCalculationStartPeriod(), false);
		Integer[] calculatedEndMonthAndYear = null;
		if (StringUtils.isEmpty(gtnForecastBean.getCalculationEndPeriod())
				|| gtnForecastBean.getCalculationEndPeriod() == null) {
			calculatedEndMonthAndYear = new Integer[] { gtnForecastBean.getProjectionEndMonth(),
					gtnForecastBean.getProjectionEndYear() };
		} else {
			calculatedEndMonthAndYear = getStartMonthAndYear(gtnForecastBean.getFrequency(),
					gtnForecastBean.getCalculationEndPeriod(), true);
		}
		for (int year = calculatedStartMonthAndYear[1]; year <= calculatedEndMonthAndYear[1]; year++) {
			if (year == calculatedEndMonthAndYear[1] && CommonConstants.ANNUAL.equals(gtnForecastBean.getFrequency())) {

				if (year == gtnForecastBean.getProjectionEndYear()) {
					endMonthIndex = gtnForecastBean.getProjectionEndMonth() + 1;
				} else {
					endMonthIndex = 12;
				}

			} else if (year == calculatedEndMonthAndYear[1]) {
				endMonthIndex = calculatedEndMonthAndYear[0];
			}
			for (int month = calculatedStartMonthAndYear[0]; month <= endMonthIndex; month++) {
				periods.add(new Integer[] { month, year });
			}
			calculatedStartMonthAndYear[0] = 1;
		}

	}

	/**
	 * Used to iterate and get the selected hierarchy number
	 *
	 * @param gtnForecastBean
	 * @param rootNode
	 * @return
	 * @throws com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException
	 */
	private void treeNodeTraverser(String hierarchyNumber, GtnWsTreeNode rootNode, List<GtnWsTreeNode> treeNodeList)
			throws GtnFrameworkGeneralException {
		if (rootNode.getChildren() != null) {
			for (GtnWsTreeNode childNode : rootNode.getChildren()) {
				if (hierarchyNumber.equals(childNode.getHierarchyNo())) {
					treeNodeList.add(childNode);
				} else {
					treeNodeTraverser(hierarchyNumber, childNode, treeNodeList);
				}
			}
		}

	}

	public void getTreeNode(GtnForecastBean gtnForecastBean, List<GtnWsTreeNode> treeNodeList)
			throws GtnFrameworkGeneralException {
		GtnWsTreeNode rootNode = ioService.getTreeNode(gtnForecastBean);
		for (String checkedHierarchyNumber : gtnForecastBean.getCheckedHierarchyNumbers()) {
			treeNodeTraverser(checkedHierarchyNumber, rootNode, treeNodeList);
		}
	}

	public List<GtnWsTreeNode> getCheckedMassUpdateNode(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {
		GtnWsTreeNode rootNode = ioService.getTreeNode(gtnForecastBean);
		List<GtnWsTreeNode> gtnWsTreeNode = new ArrayList<>();
		getCheckedLevelNodeList(rootNode, gtnWsTreeNode, gtnForecastBean.getMassUpdateLevelNo());
		return gtnWsTreeNode;
	}

	private void getCheckedLevelNodeList(GtnWsTreeNode rootNode, List<GtnWsTreeNode> result, int levelNo)
			throws GtnFrameworkGeneralException {
		if (rootNode.isCheckedNode() && rootNode.getLevelNumber() == levelNo) {
			result.add(rootNode);
			return;
		}
		if (rootNode.getChildren() != null) {
			for (GtnWsTreeNode childNode : rootNode.getChildren()) {
				getCheckedLevelNodeList(childNode, result, levelNo);
			}
		}
	}

	public List<GtnWsTreeNode> getCheckedMassUpdateNodeForGrowth(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {
		GtnWsTreeNode rootNode = ioService.getTreeNode(gtnForecastBean);
		List<GtnWsTreeNode> gtnWsTreeNode = new ArrayList<>();
		getCheckedLevelNodeListForGrowth(rootNode, gtnWsTreeNode);
		return gtnWsTreeNode;
	}

	private void getCheckedLevelNodeListForGrowth(GtnWsTreeNode rootNode, List<GtnWsTreeNode> result)
			throws GtnFrameworkGeneralException {
		if (rootNode.isCheckedNode()) {
			result.add(rootNode);
			return;
		}
		if (rootNode.getChildren() != null) {
			for (GtnWsTreeNode childNode : rootNode.getChildren()) {
				getCheckedLevelNodeListForGrowth(childNode, result);
			}
		}
	}

	public List<Integer> getEndLevelHierarchyNode(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		return new ArrayList<>(new LinkedHashSet<>(ioService.getEndLevelHierarchyNode(gtnForecastBean)));
	}

	public List<Integer> filterTireOne(List<Integer> dataIndex, List<List<Object>> masterData, int columnIndex)
			throws GtnFrameworkGeneralException {
		return ioService.filterTireOne(dataIndex, masterData, columnIndex);
	}

	public void getDataIndex(final GtnWsTreeNode rootNode, final String hierarchyNumbers,
			final List<Integer> dataIndex) {
		if (rootNode.getChildren() == null) {
			dataIndex.addAll(rootNode.getDataIndex());
		} else {
			ioService.getChildNode(rootNode, hierarchyNumbers, dataIndex);
		}
	}

	public double convertValueBasedOnFrequency(final String value, String expression, String frequency)
			throws GtnFrameworkGeneralException {
		switch (frequency) {
		case CommonConstants.ANNUAL:
			frequency = "12";
			break;
		case CommonConstants.QUARTERLY:
			frequency = "3";
			break;
		case CommonConstants.SEMIANNUAL:
			frequency = "6";
			break;
		case CommonConstants.MONTHLY:
			frequency = "1";
			break;
		default:
			break;
		}
		Evaluator evaluator = new Evaluator();
		expression = value + expression + frequency;
		try {
			return evaluator.getNumberResult(expression);
		} catch (EvaluationException e) {
			throw new GtnFrameworkGeneralException("Exception Occurred", e);
		}
	}
}
