/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.calculation.manualEntry;

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

/**
 *
 * @author STPL
 */
@Service
public class GtnWsReturnsManualEntryGrowthRate implements GtnWsReturnsCalculation {

	@Autowired
	private GtnWsCalculationUtilService calculationUtilService;

	private final GtnSmallHashMap columnNameAndIndex = new GtnSmallHashMap();

	private int projectionFileStartDate = 2014;

	@Override
	public void calculations(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		projectionFileStartDate = gtnForecastBean.getProjectionStartYear();
		List<GtnWsTreeNode> treeNodeList = new ArrayList<>();
		calculationUtilService.getTreeNode(gtnForecastBean, treeNodeList);

		List<Integer[]> periods = new ArrayList<>();
		calculationUtilService.getCalculationPeriods(periods, gtnForecastBean);
		for (GtnWsTreeNode gtnWsTreeNode : treeNodeList) {
			calculateAndUpdateInFile(gtnWsTreeNode, periods, gtnForecastBean);
		}

	}

	private void calculateAndUpdateInFile(GtnWsTreeNode treeNode, List<Integer[]> periods,
			GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		List<List<Object>> projectionFileList = calculationUtilService.getFileData(gtnForecastBean,
				CommonConstants.RETURNS_FORECAST_PROJECTION);
		int columnLength = calculationUtilService.getIndexOfColumnNames(columnNameAndIndex,
				CommonConstants.RETURNS_FORECAST_PROJECTION_FILE_COLUMN_INFO);

		for (Integer indexOfListInFile : getDataIndex(treeNode)) {
			List<Object> fileList = projectionFileList.get(indexOfListInFile);
			for (Integer[] period : periods) {
				// Calculate ProjecitonPercent
				updateGrowthRate(fileList, period, columnLength, gtnForecastBean.getManualEntryValue());
			}
		}
		calculationUtilService.writeToFile(gtnForecastBean, CommonConstants.RETURNS_FORECAST_PROJECTION,
				projectionFileList);

	}

	private void updateGrowthRate(final List<Object> fileList, final Integer[] period, final int columnLength,
			final String growthRate) throws GtnFrameworkGeneralException {
		GtnSmallHashMap formulaKeyAndValue = new GtnSmallHashMap();
		formulaKeyAndValue.put(CommonConstants.GROWTH_RATE, growthRate);
		Double growthRateValue = calculationUtilService.calculate(CommonConstants.MANUAL_ENTRY_GROWTH_RATE,
				formulaKeyAndValue);
		if (growthRateValue.isInfinite() || growthRateValue.isNaN()) {
			growthRateValue = 0d;
		}
		fileList.set(getIndexInFile(period, columnLength, CommonConstants.GROWTH_RATE), growthRateValue);
	}

	private int getIndexInFile(Integer[] period, int columnLength, String columnName) {
		return calculationUtilService.getIndex(period[0], (period[1] - projectionFileStartDate), columnLength,
				getColumnIndex(columnName));
	}

	private int getColumnIndex(final String columnName) {
		return columnNameAndIndex.getInt(columnName);
	}

	private List<Integer> getDataIndex(final GtnWsTreeNode rootNode) {
		List<Integer> dataIndex = new ArrayList<>();
		calculationUtilService.getDataIndex(rootNode, rootNode.getHierarchyNo(), dataIndex);
		return dataIndex;
	}

}