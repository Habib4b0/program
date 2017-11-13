package com.stpl.gtn.gtn2o.ws.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class GtnWsGeneralCalculationService {

	private static volatile GtnWsGeneralCalculationService gtnGeneralCalculationService;

	private GtnWsGeneralCalculationService() {

	}

	public static synchronized GtnWsGeneralCalculationService getInstance() {
		if (gtnGeneralCalculationService == null) {
			gtnGeneralCalculationService = new GtnWsGeneralCalculationService();
		}
		return gtnGeneralCalculationService;
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
	 * 
	 * @param value
	 * @return
	 */
	public double doubleConversion(String value) {
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
	 * Method used to get the index of the header values used in the headers
	 * array.
	 * 
	 * @param formula
	 * @param headersArray
	 * @return
	 */
	public Map<String, Number[]> getFormulaComponentIndex(String formula, String[] headersArray) {
		Map<String, Number[]> indexMap = new HashMap<>();
		for (int i = 0; i < headersArray.length; i++) {
			if (formula.contains(headersArray[i])) {
				indexMap.put(headersArray[i], new Number[] { i, 0.0 });
			}
		}
		return indexMap;
	}

	/**
	 * Method used to get the index of the header values used in the headers
	 * array.
	 * 
	 * @param formula
	 * @param headersArray
	 * @return
	 */
	public Map<String, Integer> getFileHeaderIndex(String[] headersArray) {
		Map<String, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < headersArray.length; i++) {
			indexMap.put(headersArray[i], i);
		}
		return indexMap;
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
	public double calculate(GtnWsMethodologyBean methodologyBean, String expression, final Map<String, Number[]> inputs)
			throws Exception {

		Evaluator evaluator = new Evaluator();
		for (Map.Entry<String, Number[]> entry : inputs.entrySet()) {

			expression = expression.replace(entry.getKey(),
					convertDoubleToString((Double) entry.getValue()[1], methodologyBean.getDecimalFormat()));

		}
		return evaluator.getNumberResult(expression);

	}

	/**
	 * 
	 * @param expression
	 * @param projectionFileDataList
	 * @param periodArray
	 * @param headerIndexMap
	 * @return
	 */
	public String generateExpression(String expression, List<Object> projectionFileDataList, Integer[] periodArray,
			Map<String, Integer> headerIndexMap, GtnWsMethodologyBean methodologyBean) {

		for (String columnName : headerIndexMap.keySet()) {

			if (expression.contains(columnName)) {

				int index = getIndex(periodArray[0], (periodArray[1] - methodologyBean.getProjectionStartYear()),
						methodologyBean.getProjectionFileHeaders().length, headerIndexMap.get(columnName));

				expression = expression.replace(columnName,
						projectionFileDataList.get(index) == null ? "0.0"
								: convertDoubleToString((Double) projectionFileDataList.get(index),
										methodologyBean.getDecimalFormat()));
			}
		}

		return expression;
	}

	/**
	 * Method converts the double value to String. DecimalFormat class is used
	 * to avoid exponential values while converting from double to string.
	 * 
	 * @param value
	 * @param decimalFormat
	 * @return
	 */
	public String convertDoubleToString(Double value, DecimalFormat decimalFormat) {
		if (value == null || Double.isInfinite(value) || Double.isNaN(value)) {
			return "0.0";
		}
		return decimalFormat.format(value);
	}

	/**
	 * Method returns the index of a particular column in the Header. Index
	 * Starts from 0.
	 * 
	 * @param columnName
	 * @param columnHeaders
	 * @return
	 */
	public int getColumnIndex(String columnName, String[] columnHeaders) {

		if (columnName != null && columnHeaders != null) {
			for (int i = 0; i < columnHeaders.length; i++) {
				if (columnName.equals(columnHeaders[i])) {
					return i;
				}
			}
		}

		throw new IllegalArgumentException("Column Name or Headers is invalid");
	}

	/**
	 * Method used to check the end of a particular frequency.
	 * 
	 * Condition Used: (month) % frequncyNumber == 0
	 * <p>
	 * month - starts from 1.
	 * <p>
	 * If frequency = Quarterly, then frequencyNumber = 3
	 * 
	 * @param methodologyBean
	 * @param periods
	 * @return
	 */
	public boolean checkFrequencyEnd(GtnWsMethodologyBean methodologyBean, Integer[] periods) {

		return ((periods[0]) % methodologyBean.getFrequency().getFrequencyNumber()) == 0;

	}

}
