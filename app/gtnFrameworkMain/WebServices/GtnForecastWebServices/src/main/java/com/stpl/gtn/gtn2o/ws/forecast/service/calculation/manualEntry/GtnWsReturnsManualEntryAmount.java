/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.calculation.manualEntry;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.components.smallhashmap.GtnSmallHashMap;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.GtnWsCalculationUtilService;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.GtnWsReturnsCalculation;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;

/**
 *
 * @author STPL
 */
@Service
public class GtnWsReturnsManualEntryAmount implements GtnWsReturnsCalculation {

	@Autowired
	private GtnWsCalculationUtilService calculationUtilService;

	private final GtnSmallHashMap columnNameAndIndex = new GtnSmallHashMap();

	private int projectionFileStartDate = 2014;

	private GtnForecastBean gtnForecastBean;

	@Override
	public void calculations(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		this.gtnForecastBean = gtnForecastBean;
		projectionFileStartDate = gtnForecastBean.getProjectionStartYear();
		List<GtnWsTreeNode> treeNodeList = new ArrayList<>();
		calculationUtilService.getTreeNode(gtnForecastBean, treeNodeList);
		List<Integer[]> periods = new ArrayList<>();
		calculationUtilService.getCalculationPeriods(periods, gtnForecastBean);
		for (GtnWsTreeNode gtnWsTreeNode : treeNodeList) {
			calculateAndUpdateInFile(gtnWsTreeNode, periods);
		}

	}

	private void calculateAndUpdateInFile(GtnWsTreeNode treeNode, List<Integer[]> periods)
			throws GtnFrameworkGeneralException {
		List<List<Object>> projectionFileList = calculationUtilService.getFileData(gtnForecastBean,
				CommonConstants.RETURNS_FORECAST_PROJECTION);
		int columnLength = calculationUtilService.getIndexOfColumnNames(columnNameAndIndex,
				CommonConstants.RETURNS_FORECAST_PROJECTION_FILE_COLUMN_INFO);
		List<Integer> dataIndex = getDataIndex(treeNode);
		for (Integer indexOfListInFile : dataIndex) {
			List<Object> fileList = projectionFileList.get(indexOfListInFile);
			for (Integer[] period : periods) {
				// Calculate Projection Amount
				double amountValue = calculateProjectionAmount(fileList, period, columnLength, dataIndex);
				// Calculate Projected Units
				calculateProjectionUnits(fileList, period, columnLength, amountValue);
				// Calculate ProjecitonPercent
				calculcateProjectionPercent(fileList, period, columnLength, amountValue);
				// Calculate Projection RPU
				calculateProjectionRPU(fileList, period, columnLength, amountValue);
			}
		}
		calculationUtilService.writeToFile(gtnForecastBean, CommonConstants.RETURNS_FORECAST_PROJECTION,
				projectionFileList);

	}

	private double calculateProjectionAmount(final List<Object> fileList, final Integer[] period,
			final int columnLength, List<Integer> dataIndex) throws GtnFrameworkGeneralException {
		double calculatedProjectedAmount;
		if (Double.valueOf(gtnForecastBean.getManualEntryOldValue()) > 0d) {

			Double incrOrDecrValue = getAmountIncreamentOrDecreamentValue(gtnForecastBean.getManualEntryValue(),
					gtnForecastBean.getManualEntryOldValue());

			if (incrOrDecrValue.isInfinite() || incrOrDecrValue.isNaN()) {
				calculatedProjectedAmount = Double.valueOf(gtnForecastBean.getManualEntryValue()) / dataIndex.size();
				calculatedProjectedAmount = calculationUtilService.convertValueBasedOnFrequency(
						String.valueOf(calculatedProjectedAmount), "/", gtnForecastBean.getFrequency());
			} else {
				double existingAmount = getValue(period, columnLength, fileList,
						CommonConstants.PROJECTED_RETURN_AMOUNT);
				GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
				formulaKeyAndValue.put("PROJECTED_RETURN_AMOUNT", existingAmount);
				formulaKeyAndValue.put("AMOUNT", incrOrDecrValue);
				calculatedProjectedAmount = calculationUtilService.calculate("AMOUNT_CALC", formulaKeyAndValue);
			}
			fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_AMOUNT),
					calculationUtilService.doubleConversation(String.valueOf(calculatedProjectedAmount)));
		} else {
			calculatedProjectedAmount = Double.valueOf(gtnForecastBean.getManualEntryValue())
					/ (getFrequencyNumber(gtnForecastBean.getFrequency()) * dataIndex.size());
			fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_AMOUNT),
					calculationUtilService.doubleConversation(String.valueOf(calculatedProjectedAmount)));
		}

		return calculatedProjectedAmount;
	}

	private void calculcateProjectionPercent(final List<Object> fileList, final Integer[] period,
			final int columnLength, final double projectedAmount) throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_AMOUNT, projectedAmount);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT));
		Double projectionPercent = calculationUtilService.calculate("AMOUNT_BASED_PERCENT", formulaKeyAndValue);
		if (projectionPercent.isInfinite() || projectionPercent.isNaN()) {
			projectionPercent = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_PERCENT),
				calculationUtilService.doubleConversation(String.valueOf(projectionPercent)));
	}

	private void calculateProjectionRPU(final List<Object> fileList, final Integer[] period, final int columnLength,
			final double projectionAmount) throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_AMOUNT, projectionAmount);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS));
		Double projectionPercent = calculationUtilService.calculate("AMOUNT_BASED_RPU", formulaKeyAndValue);
		if (projectionPercent.isInfinite() || projectionPercent.isNaN()) {
			projectionPercent = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RPU),
				calculationUtilService.doubleConversation(String.valueOf(projectionPercent)));
	}

	private void calculateProjectionUnits(final List<Object> fileList, final Integer[] period, final int columnLength,
			final double projectedAmount) throws GtnFrameworkGeneralException {
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
				calculationUtilService.doubleConversation(String.valueOf(projectionUnits)));
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

	private List<Integer> getDataIndex(final GtnWsTreeNode rootNode) {
		List<Integer> dataIndex = new ArrayList<>();
		calculationUtilService.getDataIndex(rootNode, rootNode.getHierarchyNo(), dataIndex);
		return dataIndex;
	}

	private double getAmountIncreamentOrDecreamentValue(String newValue, String oldValue)
			throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		oldValue = oldValue.replace("$", StringUtils.EMPTY);
		formulaKeyAndValue.put("NEW_AMOUNT", newValue);
		formulaKeyAndValue.put("OLD_AMOUNT", oldValue);
		Double returnPercentage = calculationUtilService.calculate("AMOUNT_INCR_OR_DECR", formulaKeyAndValue);
		if (returnPercentage.isInfinite() || returnPercentage.isNaN()) {
			returnPercentage = 0d;
		}
		return returnPercentage;
	}

	private int getFrequencyNumber(String frequency) {
		if ("Annually".equals(frequency)) {
			return 12;
		} else if ("Semi-Annually".equals(frequency)) {
			return 6;
		} else if ("Quarterly".equals(frequency)) {
			return 3;
		} else {
			return 1;
		}
	}
}
