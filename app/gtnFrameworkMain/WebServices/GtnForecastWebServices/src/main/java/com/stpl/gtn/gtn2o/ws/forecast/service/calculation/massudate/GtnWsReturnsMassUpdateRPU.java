/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.calculation.massudate;

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
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;

import net.sourceforge.jeval.EvaluationException;

/**
 *
 * @author STPL
 */
@Service
public class GtnWsReturnsMassUpdateRPU implements GtnWsReturnsCalculation {

	private int projectionFileStartDate = 2014;

	private final GtnSmallHashMap columnNameAndIndex = new GtnSmallHashMap();

	private final GtnSmallHashMap hierarchyNoAndAmountValues = new GtnSmallHashMap();

	@Autowired
	private GtnWsCalculationUtilService calculationUtilService;

	private GtnForecastBean gtnForecastBean;

	@Override
	public void calculations(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		this.gtnForecastBean = gtnForecastBean;
		projectionFileStartDate = gtnForecastBean.getProjectionStartYear();
		List<GtnWsTreeNode> treeNode = calculationUtilService.getCheckedMassUpdateNode(gtnForecastBean);
		List<Integer[]> periods = new ArrayList<>();
		calculationUtilService.getCalculationPeriods(periods, gtnForecastBean);
		List<List<Object>> projectionFileList = calculationUtilService.getFileData(gtnForecastBean,
				CommonConstants.RETURNS_FORECAST_PROJECTION);
		int columnLength = calculationUtilService.getIndexOfColumnNames(columnNameAndIndex,
				CommonConstants.RETURNS_FORECAST_PROJECTION_FILE_COLUMN_INFO);
		for (GtnWsTreeNode gtnWsTreeNode : treeNode) {
			hierarchyNoAndAmountValues.put(gtnWsTreeNode.getHierarchyNo(), gtnForecastBean.getManualEntryValue());

			if (gtnWsTreeNode.getChildren() == null) {
				calculateAndUpdateInFile(gtnWsTreeNode, periods, columnLength, projectionFileList,
						getAmountValue(gtnWsTreeNode));
			} else {
				calculateRPUForNDCLevel(gtnWsTreeNode, periods, columnLength, projectionFileList);
			}
		}
	}

	private void calculateRPUForNDCLevel(GtnWsTreeNode treeNode, List<Integer[]> periods, int columnLength,
			List<List<Object>> projectionFileList) throws GtnFrameworkGeneralException {
		double calculatedAmountForHigherLevel = applyInitialLevelCalculation(treeNode, periods, columnLength,
				projectionFileList);
		hierarchyNoAndAmountValues.put(treeNode.getHierarchyNo(), calculatedAmountForHigherLevel);
		getChildNode(treeNode, projectionFileList, periods, columnLength);
	}

	private void getChildNode(final GtnWsTreeNode rootNode, List<List<Object>> projectionFileList,
			List<Integer[]> periods, int columnLength) throws GtnFrameworkGeneralException {
		if (rootNode.getChildren() == null) {
			double calculatedAmountForLastLevel = applyLastLevelCalculation(getAmountValue(rootNode), rootNode, periods,
					columnLength, projectionFileList);
			calculateAndUpdateInFile(rootNode, periods, columnLength, projectionFileList, calculatedAmountForLastLevel);

		} else {
			for (GtnWsTreeNode treeNode : rootNode.getChildren()) {
				double calculatedAmountForLowerLevel = applyLowerLevelCalculation(getAmountValue(treeNode.getParent()),
						treeNode, periods, columnLength, projectionFileList);
				hierarchyNoAndAmountValues.put(treeNode.getHierarchyNo(), calculatedAmountForLowerLevel);
				getChildNode(treeNode, projectionFileList, periods, columnLength);
			}
		}
	}

	private double applyLowerLevelCalculation(double calculatedAmount, GtnWsTreeNode treeNode, List<Integer[]> periods,
			int columnLength, List<List<Object>> projectionFileList) throws GtnFrameworkGeneralException {
		double activeExFactoryChildAmount = calculateExFactoryValue(getDataIndex(treeNode), projectionFileList, periods,
				columnLength, CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT);
		double activeExFactoryparentAmount = calculateExFactoryValue(getDataIndex(treeNode.getParent()),
				projectionFileList, periods, columnLength, CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT);
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put("CHILD_" + CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT, activeExFactoryChildAmount);
		formulaKeyAndValue.put("PARENT_" + CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT, activeExFactoryparentAmount);
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_AMOUNT, calculatedAmount);
		Double projectionAmount = calculationUtilService.calculate("RPU_CALCULATION_FOR_LOWER_LEVEL",
				formulaKeyAndValue);
		if (projectionAmount.isInfinite() || projectionAmount.isNaN()) {
			projectionAmount = 0d;
		}
		return projectionAmount;
	}

	private double applyInitialLevelCalculation(GtnWsTreeNode treeNode, List<Integer[]> periods, int columnLength,
			List<List<Object>> projectionFileList) throws GtnFrameworkGeneralException {
		double activeExFactoryUnits = calculateExFactoryValue(getDataIndex(treeNode), projectionFileList, periods,
				columnLength, CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS);
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RPU, getAmountValue(treeNode));
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS, activeExFactoryUnits);
		Double projectionAmount = calculationUtilService.calculate("RPU_CALCULATION_FOR_HIGHER_LEVEL",
				formulaKeyAndValue);
		if (projectionAmount.isInfinite() || projectionAmount.isNaN()) {
			projectionAmount = 0d;
		}
		return projectionAmount;
	}

	private double applyLastLevelCalculation(double calculatedAmount, GtnWsTreeNode treeNode, List<Integer[]> periods,
			int columnLength, List<List<Object>> projectionFileList) throws GtnFrameworkGeneralException {
		double activeExFactoryUnits = calculateExFactoryValue(getDataIndex(treeNode), projectionFileList, periods,
				columnLength, CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS);
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_AMOUNT, calculatedAmount);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS, activeExFactoryUnits);
		Double projectionAmount = calculationUtilService.calculate("RPU_CALCULATION_FOR_LAST_LEVEL",
				formulaKeyAndValue);
		if (projectionAmount.isInfinite() || projectionAmount.isNaN()) {
			projectionAmount = 0d;
		}
		return projectionAmount;
	}

	private double calculateExFactoryValue(List<Integer> dataIndex, List<List<Object>> projectionFileList,
			List<Integer[]> periods, int columnLength, String columnName) {
		double activeExFactoryValue = 0d;
		for (Integer indexOfListInFile : dataIndex) {
			List<Object> fileList = projectionFileList.get(indexOfListInFile);
			for (Integer[] period : periods) {
				int index = calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate),
						columnLength, getColumnIndex(columnName));
				activeExFactoryValue += calculationUtilService.doubleConversation(String.valueOf(fileList.get(index)));
			}
		}
		return activeExFactoryValue;
	}

	private void calculateAndUpdateInFile(GtnWsTreeNode treeNode, List<Integer[]> periods, int columnLength,
			List<List<Object>> projectionFileList, double rpuValue) throws GtnFrameworkGeneralException {
		try {
			for (Integer indexOfListInFile : treeNode.getDataIndex()) {
				List<Object> fileList = projectionFileList.get(indexOfListInFile);
				for (Integer[] period : periods) {

					// Calculate Projection RPU
					calculateProjectionRPU(fileList, period, columnLength, rpuValue);

					// Calculate Projection Amount
					double projectionAmount = calculateProjectionAmount(fileList, period, columnLength, rpuValue);

					// Calculate Projected Units
					calculateProjectionUnits(fileList, period, columnLength, String.valueOf(projectionAmount));

					// Calculate ProjecitonPercent
					calculcateProjectionPercent(fileList, period, columnLength, projectionAmount);
				}
			}
			calculationUtilService.writeToFile(gtnForecastBean, CommonConstants.RETURNS_FORECAST_PROJECTION,
					projectionFileList);
		} catch (EvaluationException | GtnFrameworkGeneralException ex) {
			throw new GtnFrameworkGeneralException("Exception Occurred", ex);
		}
	}

	private void calculateProjectionRPU(final List<Object> fileList, final Integer[] period, final int columnLength,
			final double projectedRPU) throws EvaluationException {
		int index = calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(CommonConstants.PROJECTED_RPU));
		fileList.set(index, projectedRPU);
	}

	private double calculateProjectionAmount(final List<Object> fileList, final Integer[] period,
			final int columnLength, final double projectedRPU)
			throws EvaluationException, GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RPU, projectedRPU);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS));
		Double projectionAmount = calculationUtilService.calculate("RPU_BASED_AMOUNT", formulaKeyAndValue);
		if (projectionAmount.isInfinite() || projectionAmount.isNaN()) {
			projectionAmount = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_AMOUNT),
				calculationUtilService.doubleConversation(String.valueOf(projectionAmount)));
		return projectionAmount;
	}

	private void calculcateProjectionPercent(final List<Object> fileList, final Integer[] period,
			final int columnLength, final double projectionAmount)
			throws EvaluationException, GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_AMOUNT, projectionAmount);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT));
		Double projectionPercent = calculationUtilService.calculate("RPU_BASED_PERCENT", formulaKeyAndValue);
		if (projectionPercent.isInfinite() || projectionPercent.isNaN()) {
			projectionPercent = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_PERCENT),
				calculationUtilService.doubleConversation(String.valueOf(projectionPercent)));
	}

	private int getColumnIndex(final String columnName) {
		return columnNameAndIndex.getInt(columnName);
	}

	private void calculateProjectionUnits(final List<Object> fileList, final Integer[] period, final int columnLength,
			final String projectedAmount) throws EvaluationException, GtnFrameworkGeneralException {
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

	private double getValue(Integer[] period, int columnLength, List<Object> fileList, String columnName) {
		int index = getIndexInFile(period, columnLength, columnName);
		return calculationUtilService.doubleConversation(String.valueOf(fileList.get(index)));
	}

	private int getIndexInFile(Integer[] period, int columnLength, String columnName) {
		return calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(columnName));
	}

	private double getAmountValue(final GtnWsTreeNode treeNode) {
		return Double.valueOf(hierarchyNoAndAmountValues.get(treeNode.getHierarchyNo()).toString());
	}

	private List<Integer> getDataIndex(final GtnWsTreeNode rootNode) {
		List<Integer> dataIndex = new ArrayList<>();
		calculationUtilService.getDataIndex(rootNode, rootNode.getHierarchyNo(), dataIndex);
		return dataIndex;
	}

}
