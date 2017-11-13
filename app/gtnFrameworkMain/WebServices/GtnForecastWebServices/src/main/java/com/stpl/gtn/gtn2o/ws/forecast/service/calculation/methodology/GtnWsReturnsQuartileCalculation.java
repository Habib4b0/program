/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.calculation.methodology;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.components.smallhashmap.GtnSmallHashMap;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.GtnWsCalculationUtilService;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.GtnWsReturnsCalculation;

/**
 *
 * @author STPL
 */
@Deprecated
@Service
public class GtnWsReturnsQuartileCalculation implements GtnWsReturnsCalculation {

	@Autowired
	private GtnWsCalculationUtilService calculationUtilService;

	private final GtnSmallHashMap listIndexAndBaseLineValues = new GtnSmallHashMap();

	private final GtnSmallHashMap columnNameAndIndex = new GtnSmallHashMap();

	private List<Integer> dataIndex = new ArrayList<>();

	static int projectionFileStartDate = 2014;

	static int actualFileStartDate = 2014;

	@Override
	public void calculations(final GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		try {
			projectionFileStartDate = gtnForecastBean.getProjectionStartYear();
			actualFileStartDate = gtnForecastBean.getHistoryStartYear();
			calculateBaseLine(gtnForecastBean);
			cummulativeMultiplication(gtnForecastBean);
		} catch (IOException | ClassNotFoundException ex) {
			throw new GtnFrameworkGeneralException(ex);
		}
	}

	private void calculateBaseLine(final GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException, IOException, FileNotFoundException, ClassNotFoundException {

		List<List<Object>> actualFileList = calculationUtilService.getFileData(gtnForecastBean,
				CommonConstants.RETURNS_FORECAST_ACTUAL);

		int columnLength = calculationUtilService.getIndexOfColumnNames(columnNameAndIndex,
				CommonConstants.RETURNS_FORECAST_ACTUAL_FILE_COLUMN_INFO);

		dataIndex = calculationUtilService.getEndLevelHierarchyNode(gtnForecastBean);
		int index = getColumnIndex(CommonConstants.EXPECTED_RETURN_RATE) + 1;
		double baseline = 0d;
		for (Integer indexOfListInFile : dataIndex) {
			List<Object> list = actualFileList.get(indexOfListInFile);
			for (int i = list.size() - columnLength - 1; i >= 0; i -= columnLength) {
				if (list.get(i + index) != null) {
					Double currentExpectedReturn = Double.valueOf(String.valueOf(list.get(i + index)));
					if (currentExpectedReturn > 0d) {
						baseline = currentExpectedReturn;
						break;
					}
				}

			}

			listIndexAndBaseLineValues.put(indexOfListInFile, baseline);
		}

	}

	private void cummulativeMultiplication(final GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException, IOException {

		List<List<Object>> projectionFileList = calculationUtilService.getFileData(gtnForecastBean,
				CommonConstants.RETURNS_FORECAST_PROJECTION);
		List<Integer[]> periods = new ArrayList<>();
		calculationUtilService.getCalculationPeriods(periods, gtnForecastBean);
		int columnLength = calculationUtilService.getIndexOfColumnNames(columnNameAndIndex,
				CommonConstants.RETURNS_FORECAST_PROJECTION_FILE_COLUMN_INFO);

		for (Integer indexOfListInFile : dataIndex) {
			List<Object> fileList = projectionFileList.get(indexOfListInFile);
			Double percentValue = (double) listIndexAndBaseLineValues.get(indexOfListInFile);
			if (percentValue.isInfinite() || percentValue.isNaN()) {
				percentValue = 0d;
			}
			for (Integer[] period : periods) {
				// Set ProjecitonPercent to the perticular index
				setProjectionPercent(fileList, period, columnLength, percentValue);

				// Calculate Projection Amount
				double projectionAmount = calculateProjectionAmount(fileList, period, columnLength,
						String.valueOf(percentValue));

				// Calculate Projected Units
				calculateProjectionUnits(fileList, period, columnLength, String.valueOf(projectionAmount));

				// Calculate Projection RPU
				calculateProjectionRPU(fileList, period, columnLength, String.valueOf(projectionAmount));

			}
		}
		calculationUtilService.writeToFile(gtnForecastBean, CommonConstants.RETURNS_FORECAST_PROJECTION,
				projectionFileList);

	}

	private void setProjectionPercent(final List<Object> fileList, final Integer[] period, final int columnLength,
			final double percentValue) throws GtnFrameworkGeneralException {
		int index = calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(CommonConstants.PROJECTED_RETURN_PERCENT));
		fileList.set(index, percentValue);
	}

	private double calculateProjectionAmount(final List<Object> fileList, final Integer[] period,
			final int columnLength, final String value) throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		int index = calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT));
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_PERCENT, value);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT,
				calculationUtilService.doubleConversation(String.valueOf(fileList.get(index))));
		Double projectionAmount = calculationUtilService.calculate("SINGLE_PERIOD_AMOUNT", formulaKeyAndValue);
		if (projectionAmount.isInfinite() || projectionAmount.isNaN()) {
			projectionAmount = 0d;
		}
		index = calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(CommonConstants.PROJECTED_RETURN_AMOUNT));
		fileList.set(index, projectionAmount);
		return projectionAmount;
	}

	private void calculateProjectionRPU(final List<Object> fileList, final Integer[] period, final int columnLength,
			final String value) throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		int index = calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS));
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_AMOUNT, value);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS,
				calculationUtilService.doubleConversation(String.valueOf(fileList.get(index))));
		Double projectionRPU = calculationUtilService.calculate("SINGLE_PERIOD_RPU", formulaKeyAndValue);
		if (projectionRPU.isInfinite() || projectionRPU.isNaN()) {
			projectionRPU = 0d;
		}
		index = calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(CommonConstants.PROJECTED_RPU));
		fileList.set(index, projectionRPU);
	}

	private int getColumnIndex(final String columnName) {
		return columnNameAndIndex.getInt(columnName);
	}

	private double getValue(Integer[] period, int columnLength, List<Object> fileList, String columnName) {
		int index = getIndexInFile(period, columnLength, columnName);
		return calculationUtilService.doubleConversation(String.valueOf(fileList.get(index)));
	}

	private int getIndexInFile(Integer[] period, int columnLength, String columnName) {
		return calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(columnName));
	}

	private void calculateProjectionUnits(final List<Object> fileList, final Integer[] period, final int columnLength,
			final String projectedAmount) throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_AMOUNT, projectedAmount);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT));
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS));
		Double projectionUnits = calculationUtilService.calculate("PROJECTED_RETURN_UNITS", formulaKeyAndValue);
		if (projectionUnits.isInfinite() || projectionUnits.isNaN()) {
			projectionUnits = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_UNITS), projectionUnits);
	}

}
