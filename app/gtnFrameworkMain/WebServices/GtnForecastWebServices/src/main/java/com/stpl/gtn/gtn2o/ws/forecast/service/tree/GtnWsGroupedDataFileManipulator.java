/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsResourceService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

/**
 *
 * @author STPL
 */
public class GtnWsGroupedDataFileManipulator {

	private static final GtnWSLogger GTN_LOGGER = GtnWSLogger.getGTNLogger(GtnWsGroupedDataFileManipulator.class);

	public GtnWsTreeInput generatReturnsTreeInput(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnWsForecastRequest request = gtnUIFrameworkWebserviceRequest.getGtnWsForecastRequest();

		GtnWsTreeInput gtnWsTreeInput = new GtnWsTreeInput();

		Calendar historyStartDate = Calendar.getInstance();
		historyStartDate.add(Calendar.YEAR, -3);
		gtnWsTreeInput.setActualStartYear(historyStartDate.get(Calendar.YEAR));
		gtnWsTreeInput.setActualStartMonth(0);

		Calendar historyEndDate = Calendar.getInstance();
		historyEndDate.add(Calendar.MONTH, -1);
		gtnWsTreeInput.setActualEndYear(historyEndDate.get(Calendar.YEAR));
		gtnWsTreeInput.setActualEndMonth(historyEndDate.get(Calendar.MONTH));

		Calendar projectionStartDate = Calendar.getInstance();
		projectionStartDate.setTime(request.getGtnForecastBean().getForecastStartDate());
		gtnWsTreeInput.setProjectionStartYear(projectionStartDate.get(Calendar.YEAR));
		gtnWsTreeInput.setProjectionStartMonth(projectionStartDate.get(Calendar.MONTH));

		Calendar projectionEndDate = Calendar.getInstance();
		projectionEndDate.setTime(request.getGtnForecastBean().getForecastEndDate());
		gtnWsTreeInput.setProjectionEndYear(projectionEndDate.get(Calendar.YEAR));
		gtnWsTreeInput.setProjectionEndMonth(projectionEndDate.get(Calendar.MONTH));

		Map<String, String> resource = new GtnWsReturnsResourceService().loadAllPropertiesInMap("file-name",
				CommonConstants.RESOURCES_PATH);

		gtnWsTreeInput.setActualDataFileInputColumn(
				Arrays.asList(resource.get("RETURNS_FORECAST_ACTUAL_FILE_COLUMN_INFO").split(",")));
		gtnWsTreeInput.setProjectionDataFileInputColumn(
				Arrays.asList(resource.get("RETURNS_FORECAST_PROJECTION_FILE_COLUMN_INFO").split(",")));
		gtnWsTreeInput.setMasterFileInputColumn(
				Arrays.asList(resource.get("RETURNS_FORECAST_MASTER_FILE_COLUMN_INFO").split(",")));

		Map<Integer, String> actualFormulaMap = new HashMap<>();
		String actualFormulaColumn = resource.get("RETURNS_FORECAST_ACTUAL_FORMULA_LIST");

		if (actualFormulaColumn != null) {
			Map<String, String> formulaResource = new GtnWsReturnsResourceService()
					.loadAllPropertiesInMap("return-formula", CommonConstants.RESOURCES_PATH);
			for (String split : actualFormulaColumn.split(",")) {
				int key = gtnWsTreeInput.getActualDataFileInputColumn().indexOf(split);
				String formula = formulaResource.get(split);

				for (int i = 0; i < gtnWsTreeInput.getActualDataFileInputColumn().size(); i++) {
					formula = formula.replace(gtnWsTreeInput.getActualDataFileInputColumn().get(i), "?" + i);
				}
				actualFormulaMap.put(key, formula);
			}
		}

		Map<Integer, String> projectionFormulaMap = new HashMap<>();
		String projectionFormulaColumn = resource.get("RETURNS_FORECAST_PROJECTION_FORMULA_LIST");

		if (projectionFormulaColumn != null) {
			Map<String, String> formulaResource = new GtnWsReturnsResourceService()
					.loadAllPropertiesInMap("return-formula", CommonConstants.RESOURCES_PATH);
			for (String split : projectionFormulaColumn.split(",")) {
				int key = gtnWsTreeInput.getProjectionDataFileInputColumn().indexOf(split);
				String formula = formulaResource.get(split);

				for (int i = 0; i < gtnWsTreeInput.getProjectionDataFileInputColumn().size(); i++) {
					formula = formula.replace(gtnWsTreeInput.getProjectionDataFileInputColumn().get(i), "?" + i);
				}
				projectionFormulaMap.put(key, formula);
			}
		}
		gtnWsTreeInput.setActualFormulaMap(actualFormulaMap);
		gtnWsTreeInput.setProjectionFormulaMap(projectionFormulaMap);
		return gtnWsTreeInput;
	}

	public void writeToFile(List<List<Object>> actualSource, List<List<Object>> projectionSource,
			List<List<Object>> resultset, GtnWsTreeInput treeinput, GtnWsTreeNode rootNode) {
		try {
			for (GtnWsTreeNode children : rootNode.getChildren()) {
				buildTreeData(actualSource, projectionSource, children, resultset, treeinput);
			}
		} catch (Exception ex) {
			GTN_LOGGER.error("Error in Forecast Web service Write To file", ex);
		}

	}

	public void buildTreeData(List<List<Object>> actualSource, List<List<Object>> projectionSource,
			GtnWsTreeNode treeNode, List<List<Object>> resultSet, GtnWsTreeInput treeinput) {
		try {
			if (treeNode.getChildren() == null) {
				List<Object> childData = aggregateChildData(actualSource, projectionSource, treeNode.getDataIndex(),
						treeinput);
				// applyFormula(treeinput, childData,
				// treeNode.getDataIndex().size());
				resultSet.get(treeNode.getNodeIndex()).addAll(childData);
			} else {
				List<List<Object>> childDataList = new ArrayList<>();
				for (GtnWsTreeNode children : treeNode.getChildren()) {
					buildTreeData(actualSource, projectionSource, children, resultSet, treeinput);
					childDataList.add(resultSet.get(children.getNodeIndex()));
				}
				List<Object> parentData = aggregateParentData(childDataList,
						treeinput.getMasterFileInputColumn().size());
				// applyFormula(treeinput, parentData,
				// treeNode.getChildren().size());
				resultSet.get(treeNode.getNodeIndex()).addAll(parentData);
			}
		} catch (Exception ex) {
			GTN_LOGGER.error("Error in Forecast Web Service Bind Tree Data Method", ex);
		}
	}

	private List<Object> aggregateChildData(List<List<Object>> actualSource, List<List<Object>> projectionSource,
			TreeSet<Integer> indexSet, GtnWsTreeInput treeinput) {

		List<Object> resultList = new ArrayList<>();
		try {
			for (int index : indexSet) {
				configurePeriodData(actualSource.get(index), projectionSource.get(index), treeinput, resultList);
			}
		} catch (Exception ex) {
			GTN_LOGGER.error("Error in Forecast Web Service Aggreagate Child Data Method", ex);
		}
		return resultList;

	}

	private List<Object> aggregateParentData(List<List<Object>> aggregationInput, int masterColumnSize) {

		List<Object> parentResult = new ArrayList<>();
		try {
			for (List<Object> parentResult1 : aggregationInput) {
				if (parentResult.isEmpty()) {
					parentResult.addAll(parentResult1.subList(masterColumnSize, parentResult1.size()));
				} else {

					for (int i = masterColumnSize; i < parentResult1.size(); i++) {
						double currentValue = 0d;
						if (parentResult.get(i - masterColumnSize) instanceof Double) {
							currentValue += (Double) parentResult.get(i - masterColumnSize);
						}
						if (parentResult1.get(i) instanceof Double) {
							currentValue += (Double) parentResult1.get(i);
						}
						parentResult.set(i - masterColumnSize, currentValue);
					}
				}
			}
		} catch (Exception ex) {
			GTN_LOGGER.error("Error in Forecast web Service Aggregate Parent Data", ex);
		}
		return parentResult;
	}

	private void configurePeriodData(List<Object> actualData, List<Object> projectionData, GtnWsTreeInput treeinput,
			List<Object> resultList) {
		try {
			int totalPeriodRange = calculateTotalPeriodRange(treeinput.getActualStartYear(),
					treeinput.getActualStartMonth(), treeinput.getProjectionEndYear(),
					treeinput.getProjectionEndMonth());

			int actualEndIndex = calculateTotalPeriodRange(treeinput.getActualStartYear(),
					treeinput.getActualStartMonth(), treeinput.getActualEndYear(), treeinput.getActualEndMonth());

			int projectionStartIndex = totalPeriodRange
					- calculateTotalPeriodRange(treeinput.getProjectionStartYear(), treeinput.getProjectionStartMonth(),
							treeinput.getProjectionEndYear(), treeinput.getProjectionEndMonth());

			int resultStartIndex = 0;
			int actualIndex = 0;
			int projectionIndex = 0;
			for (int i = 0; i <= totalPeriodRange; i++) {

				if (i <= actualEndIndex) {
					List<Object> subList = actualData.subList(
							actualIndex * treeinput.getActualDataFileInputColumn().size(),
							(actualIndex + 1) * treeinput.getActualDataFileInputColumn().size());
					addDataBasedOnIndex(subList, resultStartIndex, resultList, treeinput);
					resultStartIndex += subList.size();
					actualIndex++;
				}

				if (i >= projectionStartIndex - 1) {
					List<Object> subList = projectionData.subList(
							projectionIndex * treeinput.getProjectionDataFileInputColumn().size(),
							(projectionIndex + 1) * treeinput.getProjectionDataFileInputColumn().size());
					addDataBasedOnIndex(subList, resultStartIndex, resultList, treeinput);
					resultStartIndex += subList.size();
					projectionIndex++;
				}
			}
		} catch (Exception ex) {
			GTN_LOGGER.error("Error in Forecast Web Service Configure Period Data", ex);
		}
	}

	private void addDataBasedOnIndex(List<Object> data, int resultStartIndex, List<Object> resultList,
			GtnWsTreeInput treeinput) {
		try {
			if (resultList.isEmpty() || resultList.size() <= resultStartIndex) {
				resultList.addAll(data);
			} else {
				for (int i = 0; i < data.size(); i++) {
					double val1 = 0d;
					double val2 = 0d;
					if (data.get(i) instanceof Double) {
						val1 = (Double) data.get(i);
					}

					if (resultList.get(i + resultStartIndex) instanceof Double) {
						val2 = (Double) resultList.get(i + resultStartIndex);
					}

					resultList.set(i, val1 + val2);
				}
			}
		} catch (Exception ex) {
			GTN_LOGGER.error("Error in Add Data Based On Index", ex);
		}
	}

	private int calculateTotalPeriodRange(int startYear, int startMonth, int endYear, int endMonth) {
		int diffYear = endYear - startYear;
		return diffYear * 12 + endMonth - startMonth;
	}
}
