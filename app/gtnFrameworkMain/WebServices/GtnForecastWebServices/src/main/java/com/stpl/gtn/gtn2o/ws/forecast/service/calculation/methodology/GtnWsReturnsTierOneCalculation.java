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
public class GtnWsReturnsTierOneCalculation implements GtnWsReturnsCalculation {

	@Autowired
	private GtnWsCalculationUtilService calculationUtilService;

	double baseLineValue = 0d;

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
		List<Integer[]> periods = calculationUtilService.getMonthFormPeriod(gtnForecastBean.getFrequency(),
				gtnForecastBean.getBaselinePeriodList().get(0));

		double cumReturnUnits = 0.0;
		double originalSalesUnits = 0.0;

		List<List<Object>> actualFileList = calculationUtilService.getFileData(gtnForecastBean,
				CommonConstants.RETURNS_FORECAST_ACTUAL);
		int index = 0;

		calculationUtilService.getIndexOfColumnNames(columnNameAndIndex,
				CommonConstants.RETURNS_FORECAST_MASTER_FILE_COLUMN_INFO);

		dataIndex = calculationUtilService.getEndLevelHierarchyNode(gtnForecastBean);
		List<List<Object>> masterData = calculationUtilService.getFileData(gtnForecastBean,
				CommonConstants.RETURNS_FORECAST_MASTER);
		calculationUtilService.filterTireOne(dataIndex, masterData, getColumnIndex(CommonConstants.IS_TIER_ONE));
		int columnLength = calculationUtilService.getIndexOfColumnNames(columnNameAndIndex,
				CommonConstants.RETURNS_FORECAST_ACTUAL_FILE_COLUMN_INFO);
		for (Integer indexOfListInFile : dataIndex) {
			List<Object> list = actualFileList.get(indexOfListInFile);
			for (Integer[] period : periods) {
				index = calculationUtilService.getIndex(period[0], (period[1] - actualFileStartDate), columnLength,
						getColumnIndex(CommonConstants.CUM_RETURN_UNITS));
				cumReturnUnits += calculationUtilService.doubleConversation(String.valueOf(list.get(index)));
				index = calculationUtilService.getIndex(period[0], (period[1] - actualFileStartDate), columnLength,
						getColumnIndex(CommonConstants.ORIG_SALE_UNITS));
				originalSalesUnits += calculationUtilService.doubleConversation(String.valueOf(list.get(index)));
			}
		}
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.CUM_RETURN_UNITS, cumReturnUnits);
		formulaKeyAndValue.put(CommonConstants.ORIG_SALE_UNITS, originalSalesUnits);
		baseLineValue = calculationUtilService.calculate("TIER_ONE_BASELINE", formulaKeyAndValue);

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
			for (Integer[] period : periods) {

				// Calculate ProjecitonPercent
				double percentValue = calculcateProjectionPercent(fileList, period, columnLength);

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

	private double calculcateProjectionPercent(final List<Object> fileList, final Integer[] period,
			final int columnLength) throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put("BASELINE", baseLineValue);
		formulaKeyAndValue.put(CommonConstants.GROWTH_RATE,
				getValue(period, columnLength, fileList, CommonConstants.GROWTH_RATE));
		Double projectionPercent = calculationUtilService.calculate("TIER_ONE_PERCENT", formulaKeyAndValue);
		if (projectionPercent.isInfinite() || projectionPercent.isNaN()) {
			projectionPercent = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_PERCENT), projectionPercent);
		return projectionPercent;
	}

	private double calculateProjectionAmount(final List<Object> fileList, final Integer[] period,
			final int columnLength, final String projectionPercent) throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_PERCENT, projectionPercent);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT));
		Double projectionAmount = calculationUtilService.calculate("TIER_ONE_AMOUNT", formulaKeyAndValue);
		if (projectionAmount.isInfinite() || projectionAmount.isNaN()) {
			projectionAmount = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_AMOUNT), projectionAmount);
		return projectionAmount;
	}

	private void calculateProjectionRPU(final List<Object> fileList, final Integer[] period, final int columnLength,
			final String projectionAmount) throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_AMOUNT, projectionAmount);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS));
		Double projectionRPU = calculationUtilService.calculate("TIER_ONE_RPU", formulaKeyAndValue);
		if (projectionRPU.isInfinite() || projectionRPU.isNaN()) {
			projectionRPU = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RPU), projectionRPU);
	}

	private double getValue(Integer[] period, int columnLength, List<Object> fileList, String columnName) {
		int index = getIndexInFile(period, columnLength, columnName);
		return calculationUtilService.doubleConversation(String.valueOf(fileList.get(index)));
	}

	private int getIndexInFile(Integer[] period, int columnLength, String columnName) {
		return calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(columnName));
	}

	private int getColumnIndex(final String columnName) {
		return columnNameAndIndex.getInt(columnName);
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
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_UNITS),
				Double.isNaN(projectionUnits) ? 0.0 : projectionUnits);
	}

}
