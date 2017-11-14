/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.calculation.massudate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.smallhashmap.GtnSmallHashMap;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsFileIOService;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.GtnWsCalculationUtilService;
import com.stpl.gtn.gtn2o.ws.forecast.service.calculation.GtnWsReturnsCalculation;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnTreeTableLoadService;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeInput;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;

import net.sourceforge.jeval.EvaluationException;

/**
 *
 * @author STPL
 */
@Service
public class GtnWsReturnsMassUpdateAmount implements GtnWsReturnsCalculation {

	@Autowired
	private GtnWsCalculationUtilService calculationUtilService;

	@Autowired
	private GtnWsReturnsFileIOService gtnWsReturnsFileIOService;

	private final GtnSmallHashMap columnNameAndIndex = new GtnSmallHashMap();

	private int projectionFileStartDate = 2014;

	private GtnForecastBean gtnForecastBean;

	private Map<GtnWsTreeNode, Map<Integer[], String>> oldValueMap;

	@Override
	public void calculations(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		try {
			this.gtnForecastBean = gtnForecastBean;
			projectionFileStartDate = gtnForecastBean.getProjectionStartYear();
			List<GtnWsTreeNode> treeNode = calculationUtilService.getCheckedMassUpdateNode(gtnForecastBean);
			oldValueMap = new HashMap<>();
			List<Integer[]> periods = new ArrayList<>();

			calculationUtilService.getCalculationPeriods(periods, gtnForecastBean);
			calculateOldValue(treeNode, periods, CommonConstants.PROJECTED_RETURN_AMOUNT);
			for (GtnWsTreeNode gtnWsTreeNode : treeNode) {
				calculateAndUpdateInFile(gtnWsTreeNode, periods);
			}
		} catch (EvaluationException | IOException | ClassNotFoundException ex) {
			throw new GtnFrameworkGeneralException("Mass Update Amount Error", ex);
		}

	}

	private void calculateAndUpdateInFile(GtnWsTreeNode treeNode, List<Integer[]> periods)
			throws GtnFrameworkGeneralException {
		try {
			List<List<Object>> projectionFileList = calculationUtilService.getFileData(gtnForecastBean,
					CommonConstants.RETURNS_FORECAST_PROJECTION);
			int columnLength = calculationUtilService.getIndexOfColumnNames(columnNameAndIndex,
					CommonConstants.RETURNS_FORECAST_PROJECTION_FILE_COLUMN_INFO);
			List<Integer> dataIndex = getDataIndex(treeNode);
			for (Integer indexOfListInFile : dataIndex) {
				List<Object> fileList = projectionFileList.get(indexOfListInFile);
				for (Integer[] period : periods) {
					// Calculate Projection Amount
					String oldValue = oldValueMap.get(treeNode).get(period);
					Double incrOrDecrValue = getAmountIncreamentOrDecreamentValue(gtnForecastBean.getManualEntryValue(),
							oldValue);

					double amountValue = calculateProjectionAmount(fileList, period, columnLength, dataIndex,
							incrOrDecrValue);
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
		} catch (GtnFrameworkGeneralException | EvaluationException ex) {
			throw new GtnFrameworkGeneralException("Exception Occurred", ex);
		}
	}

	private double calculateProjectionAmount(final List<Object> fileList, final Integer[] period,
			final int columnLength, List<Integer> dataIndex, Double incrOrDecrValue)
			throws EvaluationException, GtnFrameworkGeneralException {

		Double calculatedProjectedAmount;
		if (incrOrDecrValue.isInfinite() || incrOrDecrValue.isNaN()
				|| (incrOrDecrValue < 0.0001 && incrOrDecrValue > -.0001)) {
			calculatedProjectedAmount = Double.valueOf(gtnForecastBean.getManualEntryValue()) / dataIndex.size();
			calculatedProjectedAmount = calculationUtilService.convertValueBasedOnFrequency(
					String.valueOf(calculatedProjectedAmount), "/", gtnForecastBean.getFrequency());
		} else {
			double existingAmount = getValue(period, columnLength, fileList, CommonConstants.PROJECTED_RETURN_AMOUNT);
			GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
			formulaKeyAndValue.put("PROJECTED_RETURN_AMOUNT", existingAmount);
			formulaKeyAndValue.put("AMOUNT", incrOrDecrValue);
			calculatedProjectedAmount = calculationUtilService.calculate("AMOUNT_CALC", formulaKeyAndValue);
		}
		if (calculatedProjectedAmount.isInfinite() || calculatedProjectedAmount.isNaN()) {
			calculatedProjectedAmount = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.PROJECTED_RETURN_AMOUNT),
				calculationUtilService.doubleConversation(String.valueOf(calculatedProjectedAmount)));
		return calculatedProjectedAmount;
	}

	private void calculcateProjectionPercent(final List<Object> fileList, final Integer[] period,
			final int columnLength, final double projectedAmount)
			throws EvaluationException, GtnFrameworkGeneralException {
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
			final double projectionAmount) throws EvaluationException, GtnFrameworkGeneralException {
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
			final double projectedAmount) throws EvaluationException, GtnFrameworkGeneralException {
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
			throws EvaluationException, GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		oldValue = oldValue.replace("$", StringUtils.EMPTY).replaceAll(",", StringUtils.EMPTY);
		if (StringUtils.EMPTY.equals(oldValue)) {
			oldValue = "0.0";
		}
		formulaKeyAndValue.put("NEW_AMOUNT", newValue);
		formulaKeyAndValue.put("OLD_AMOUNT", oldValue);
		Double returnPercentage = calculationUtilService.calculate("AMOUNT_INCR_OR_DECR", formulaKeyAndValue);
		if (returnPercentage.isInfinite() || returnPercentage.isNaN()) {
			returnPercentage = 0d;
		}
		return returnPercentage;
	}

	@SuppressWarnings("unchecked")
	private void calculateOldValue(List<GtnWsTreeNode> treeNode, List<Integer[]> periods, String columnName)
			throws EvaluationException, IOException, FileNotFoundException, ClassNotFoundException,
			GtnFrameworkGeneralException {
		GtnTreeTableLoadService treeService = new GtnTreeTableLoadService();

		String basePath = gtnWsReturnsFileIOService.getFilePath(gtnForecastBean.getModuleName(),
				gtnForecastBean.getUserId(), gtnForecastBean.getForecastSessionId(), null);
		List<List<Object>> projectionFileList = calculationUtilService.readJSONDataFromFile(basePath + "TreeData.stpl",
				List.class);
		GtnWsTreeInput gtnWsTreeInput = getTreeInput();

		for (GtnWsTreeNode treeNode1 : treeNode) {
			List<GtnWsRecordBean> tempResult = new ArrayList<>();
			treeService.loadDataFromGroupedFile(gtnWsTreeInput, tempResult, projectionFileList, treeNode1,
					gtnForecastBean.getRecordheader());
			Map<Integer[], String> oldValuePeriodMap = new HashMap<>();
			for (Integer[] period : periods) {
				String header = getColumnNameFromPeriod(period) + "-" + columnName;
				tempResult.get(0).setRecordHeader(gtnForecastBean.getRecordheader());
				oldValuePeriodMap.put(period, tempResult.get(0).getStringProperty(header));
			}
			oldValueMap.put(treeNode1, oldValuePeriodMap);
		}

	}

	private String getColumnNameFromPeriod(Integer[] period) {
		String currentPeriod = "";
		if ("ANNUALLY".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			currentPeriod += period[1];
		} else if ("SEMI-ANNUALLY".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			currentPeriod = "S" + (((period[0] - 1) / 6) + 1) + "-" + period[1];
		} else if ("QUARTERLY".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			currentPeriod = "Q" + (((period[0] - 1) / 3) + 1) + "-" + period[1];
		} else if ("MONTHLY".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			currentPeriod = "M" + period[0] + "-" + period[1];
		}

		return currentPeriod;
	}

	private GtnWsTreeInput getTreeInput() {
		GtnTreeTableLoadService treeService = new GtnTreeTableLoadService();
		GtnWsTreeInput gtnWsTreeInput = treeService.generatReturnsTreeInput(gtnForecastBean);

		Calendar calendar = treeService.calculateHistoryPeriods(Integer.valueOf(gtnForecastBean.getSelectedHistory()),
				gtnForecastBean.getFrequency());
		gtnWsTreeInput.setSelectedHistoryYear(calendar.get(Calendar.YEAR));
		gtnWsTreeInput.setSelectedHistoryMonth(calendar.get(Calendar.MONTH));

		if ("ANNUALLY".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			gtnWsTreeInput.setFrequency(12);
		} else if ("SEMI-ANNUALLY".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			gtnWsTreeInput.setFrequency(6);
		} else if ("QUARTERLY".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			gtnWsTreeInput.setFrequency(3);
		} else if ("MONTHLY".equalsIgnoreCase(gtnForecastBean.getFrequency())) {
			gtnWsTreeInput.setFrequency(1);
		}
		return gtnWsTreeInput;
	}
}
