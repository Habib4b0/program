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
public class GtnWsReturnsMassUpdatePercentage implements GtnWsReturnsCalculation {

	@Autowired
	private GtnWsCalculationUtilService calculationUtilService;

	private final GtnSmallHashMap columnNameAndIndex = new GtnSmallHashMap();

	private int projectionFileStartDate = 2014;

	@Override
	public void calculations(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		projectionFileStartDate = gtnForecastBean.getProjectionStartYear();
		List<GtnWsTreeNode> treeNode = calculationUtilService.getCheckedMassUpdateNode(gtnForecastBean);
		List<Integer[]> periods = new ArrayList<>();
		calculationUtilService.getCalculationPeriods(periods, gtnForecastBean);
		for (GtnWsTreeNode gtnWsTreeNode : treeNode) {
			calculateAndUpdateInFile(gtnWsTreeNode, periods, gtnForecastBean);
		}
	}

	private void calculateAndUpdateInFile(GtnWsTreeNode treeNode, List<Integer[]> periods,
			GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		try {
			List<List<Object>> projectionFileList = calculationUtilService.getFileData(gtnForecastBean,
					CommonConstants.RETURNS_FORECAST_PROJECTION);
			int columnLength = calculationUtilService.getIndexOfColumnNames(columnNameAndIndex,
					CommonConstants.RETURNS_FORECAST_PROJECTION_FILE_COLUMN_INFO);

			double percentValue = calculationUtilService.doubleConversation(gtnForecastBean.getManualEntryValue())
					/ 100;

			for (Integer indexOfListInFile : getDataIndex(treeNode)) {
				List<Object> fileList = projectionFileList.get(indexOfListInFile);
				for (Integer[] period : periods) {

					// Calculate ProjecitonPercent
					calculcateProjectionPercent(fileList, period, columnLength, percentValue);

					// Calculate Projection Amount
					double projectionAmount = calculateProjectionAmount(fileList, period, columnLength, percentValue);

					// Calculate Projected Units
					calculateProjectionUnits(fileList, period, columnLength, String.valueOf(projectionAmount));

					// Calculate Projection RPU
					calculateProjectionRPU(fileList, period, columnLength, projectionAmount);
				}
			}
			calculationUtilService.writeToFile(gtnForecastBean, CommonConstants.RETURNS_FORECAST_PROJECTION,
					projectionFileList);
		} catch (GtnFrameworkGeneralException | EvaluationException ex) {
			throw new GtnFrameworkGeneralException("Exception Occurred", ex);
		}
	}

	private void calculcateProjectionPercent(final List<Object> fileList, final Integer[] period,
			final int columnLength, final double projectedPercent) throws EvaluationException {
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_PERCENT), projectedPercent);
	}

	private double calculateProjectionAmount(final List<Object> fileList, final Integer[] period,
			final int columnLength, final double projectedPercent)
			throws EvaluationException, GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_PERCENT, projectedPercent);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_AMOUNT));
		Double projectionAmount = calculationUtilService.calculate("PERCENT_BASED_AMOUNT", formulaKeyAndValue);
		if (projectionAmount.isInfinite() || projectionAmount.isNaN()) {
			projectionAmount = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_AMOUNT),
				calculationUtilService.doubleConversation(String.valueOf(projectionAmount)));
		return projectionAmount;
	}

	private void calculateProjectionRPU(final List<Object> fileList, final Integer[] period, final int columnLength,
			final double projectionAmount) throws EvaluationException, GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.PROJECTED_RETURN_AMOUNT, projectionAmount);
		formulaKeyAndValue.put(CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS,
				getValue(period, columnLength, fileList, CommonConstants.ACTIVE_EXFACTORY_SALES_UNITS));
		Double projectionPercent = calculationUtilService.calculate("PERCENT_BASED_RPU", formulaKeyAndValue);
		if (projectionPercent.isInfinite() || projectionPercent.isNaN()) {
			projectionPercent = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RPU),
				calculationUtilService.doubleConversation(String.valueOf(projectionPercent)));
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

}
