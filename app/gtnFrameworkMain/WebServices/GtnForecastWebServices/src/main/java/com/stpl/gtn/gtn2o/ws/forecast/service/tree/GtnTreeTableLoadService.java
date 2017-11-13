/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.tree;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsFileIOService;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsResourceService;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

/**
 *
 * @author Sibi.Chakaravarthy
 */
@Service
public class GtnTreeTableLoadService {

	@Autowired
	GtnWsReturnsFileIOService gtnWsReturnsFileIOService;

	public int getCount(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {

		String basePath = gtnWsReturnsFileIOService.getFilePath(gtnForecastBean.getModuleName(),
				gtnForecastBean.getUserId(), gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		String finalFileName = basePath
				+ gtnWsReturnsFileIOService.getTreeFileName(gtnForecastBean.getHierarchyType(), null);
		GtnWsTreeNode rootNode = gtnWsReturnsFileIOService.readDataFromFile(finalFileName, GtnWsTreeNode.class);

		if (gtnForecastBean.getLevelFilter() > 0) {
			int count = getLevelFilterCount(rootNode, gtnForecastBean, 0);
			return count;
		}
		if (StringUtils.isBlank(gtnForecastBean.getHierarchyNo())) {
			return rootNode.getChildren().size();
		} else {
			if (rootNode.getChildren() == null) {
				return 0;
			}
			return readTreeNodeRecursively(rootNode, gtnForecastBean.getHierarchyNo()).getChildren().size();
		}

	}

	/**
	 * Used to get the count based on the parent data with level number and
	 * hierarchy number
	 *
	 * @param rootNode
	 * @param levelNumber
	 * @param hierarchyNo
	 * @param count
	 * @return
	 */
	private GtnWsTreeNode readTreeNodeRecursively(final GtnWsTreeNode rootNode, final String hierarchyNo) {

		GtnWsTreeNode node = null;

		if (rootNode.getChildren() != null) {
			for (GtnWsTreeNode treeNode : rootNode.getChildren()) {
				if (StringUtils.equals(hierarchyNo, treeNode.getHierarchyNo())) {
					return treeNode;
				} else {
					node = readTreeNodeRecursively(treeNode, hierarchyNo);
				}
			}
		}
		return node;
	}

	private int getLevelFilterCount(final GtnWsTreeNode rootNode, final GtnForecastBean gtnForecastBean, int count) {
		if (gtnForecastBean.getLevelFilter() == rootNode.getLevelNumber()) {
			count++;
		}
		if (rootNode.getChildren() != null) {
			for (GtnWsTreeNode treeNode : rootNode.getChildren()) {
				count = getLevelFilterCount(treeNode, gtnForecastBean, count);
			}
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<GtnWsRecordBean> loadBulkData(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException, EvaluationException {

		String basePath = gtnWsReturnsFileIOService.getFilePath(gtnForecastBean.getModuleName(),
				gtnForecastBean.getUserId(), gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		String finalFileName = basePath
				+ gtnWsReturnsFileIOService.getTreeFileName(gtnForecastBean.getHierarchyType(), null);
		GtnWsTreeNode rootNode = gtnWsReturnsFileIOService.readDataFromFile(finalFileName, GtnWsTreeNode.class);

		List<List<Object>> groupedDataList = gtnWsReturnsFileIOService.readJSONDataFromFile(basePath + "TreeData.stpl",
				List.class);

		Calendar calendar = calculateHistoryPeriods(Integer.valueOf(gtnForecastBean.getSelectedHistory()),
				gtnForecastBean.getFrequency());

		GtnWsTreeInput gtnWsTreeInput = generatReturnsTreeInput(gtnForecastBean);

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

		return getGeneratedBulkData(gtnForecastBean, gtnWsTreeInput, groupedDataList, rootNode);
	}

	public Map<String, Integer> loadBulkCount(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException, EvaluationException {
		String basePath = gtnWsReturnsFileIOService.getFilePath(gtnForecastBean.getModuleName(),
				gtnForecastBean.getUserId(), gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		String finalFileName = basePath
				+ gtnWsReturnsFileIOService.getTreeFileName(gtnForecastBean.getHierarchyType(), null);
		GtnWsTreeNode rootNode = gtnWsReturnsFileIOService.readDataFromFile(finalFileName, GtnWsTreeNode.class);
		return getHierarchyNodes(rootNode, gtnForecastBean.getExpandCollapseLevelNo());
	}

	private Map<String, Integer> getHierarchyNodes(GtnWsTreeNode apex, int expandLevelNo) {
		Map<String, Integer> countMap = new HashMap<>();
		if (apex.getLevelNumber() <= expandLevelNo) {
			if (!(apex.getLevelNumber() == 0)) {
				countMap.put(apex.getHierarchyNoForTable(), apex.getChildren() == null ? 0 : apex.getChildren().size());
			} else {
				countMap.put(StringUtils.EMPTY, apex.getChildren() == null ? 0 : apex.getChildren().size());
			}
			for (GtnWsTreeNode treeNode : apex.getChildren()) {
				countMap.putAll(getHierarchyNodes(treeNode, expandLevelNo));
			}
		}

		return countMap;
	}

	@SuppressWarnings("unchecked")
	private List<GtnWsRecordBean> getGeneratedBulkData(GtnForecastBean gtnForecastBean, GtnWsTreeInput gtnWsTreeInput,
			List<List<Object>> groupedDataList, GtnWsTreeNode rootNode)
			throws GtnFrameworkGeneralException, EvaluationException {

		if (gtnForecastBean.getLevelFilter() > 0) {
			List<GtnWsRecordBean> resList = new ArrayList<>();
			getLevelFilterData(gtnWsTreeInput, gtnForecastBean, groupedDataList, rootNode, resList, 0);
			return resList;
		}
		if (gtnForecastBean.getHierarchyList() == null || gtnForecastBean.getHierarchyList().isEmpty()) {
			return Collections.EMPTY_LIST;
		} else {
			return getLoadDataListByHierarchyNo(gtnWsTreeInput, gtnForecastBean, groupedDataList, rootNode);
		}

	}

	@SuppressWarnings("unused")
	private List<GtnWsRecordBean> getLoadDataList(GtnWsTreeInput gtsWsTreeInput, GtnForecastBean gtnForecastBean,
			List<List<Object>> groupedFileData, List<GtnWsTreeNode> children)
			throws GtnFrameworkGeneralException, EvaluationException {
		List<GtnWsRecordBean> resultList = new ArrayList<>();
		for (GtnWsTreeNode treeNode : children.subList(gtnForecastBean.getStart(),
				gtnForecastBean.getStart() + gtnForecastBean.getOffset())) {
			loadDataFromGroupedFile(gtsWsTreeInput, resultList, groupedFileData, treeNode,
					gtnForecastBean.getRecordheader());
		}
		return resultList;
	}

	private List<GtnWsRecordBean> getLoadDataListByHierarchyNo(GtnWsTreeInput gtsWsTreeInput,
			GtnForecastBean gtnForecastBean, List<List<Object>> groupedFileData, GtnWsTreeNode rootNode)
			throws GtnFrameworkGeneralException, EvaluationException {
		List<GtnWsRecordBean> resultList = new ArrayList<>();
		List<String> neededHierarchySet = gtnForecastBean.getHierarchyList();
		for (String hierarchyNoForTable : neededHierarchySet) {
			GtnWsTreeNode treeNode = getTreeNode(rootNode, hierarchyNoForTable);
			loadDataFromGroupedFile(gtsWsTreeInput, resultList, groupedFileData, treeNode,
					gtnForecastBean.getRecordheader());
		}
		return resultList;
	}

	public GtnWsTreeNode getTreeNode(GtnWsTreeNode apex, String hierarchy) {
		if (!hierarchy.isEmpty()) {
			String[] istParent = hierarchy.split("\\.");
			for (String tParent : istParent) {
				apex = apex.getChildren().get(Integer.valueOf(tParent) - 1);
			}
		}
		return apex;
	}

	public int getLevelFilterData(GtnWsTreeInput gtsWsTreeInput, GtnForecastBean gtnForecastBean,
			List<List<Object>> groupedFileData, GtnWsTreeNode rootNode, List<GtnWsRecordBean> resultList, int count)
			throws GtnFrameworkGeneralException, EvaluationException {
		if (!StringUtils.isEmpty(rootNode.getHierarchyNo())
				&& gtnForecastBean.getLevelFilter() == rootNode.getLevelNumber()) {
			if (count >= gtnForecastBean.getStart()
					&& count < gtnForecastBean.getStart() + gtnForecastBean.getOffset()) {
				loadDataFromGroupedFile(gtsWsTreeInput, resultList, groupedFileData, rootNode,
						gtnForecastBean.getRecordheader());
			}
			count++;
		}
		if (rootNode.getChildren() != null) {
			for (GtnWsTreeNode treeNode : rootNode.getChildren()) {
				if (resultList.size() == gtnForecastBean.getOffset()) {
					break;
				}
				count = getLevelFilterData(gtsWsTreeInput, gtnForecastBean, groupedFileData, treeNode, resultList,
						count);
			}
		}
		return count;
	}

	public void loadDataFromGroupedFile(GtnWsTreeInput gtsWsTreeInput, List<GtnWsRecordBean> resultList,
			List<List<Object>> groupedFileData, GtnWsTreeNode treeNode, List<Object> recordHeader)
			throws GtnFrameworkGeneralException {
		List<Object> rowList = groupedFileData.get(treeNode.getNodeIndex());
		GtnWsRecordBean GtnWsRecordBean = new GtnWsRecordBean();
		GtnWsRecordBean.addAdditionalProperty(treeNode.getHierarchyNo());
		GtnWsRecordBean.addAdditionalProperty(treeNode.getLevelNumber());
		GtnWsRecordBean.addProperties(String.valueOf(treeNode.isCheckedNode()));
		addMasterDataList(GtnWsRecordBean, rowList, gtsWsTreeInput);
		GtnWsRecordBean.getProperties().add(1, treeNode.getLevelValue());
		GtnWsRecordBean.setParentFlag(treeNode.getChildren() != null);
		resultList.add(GtnWsRecordBean);
		addActualAndForecastData(treeNode, GtnWsRecordBean, rowList, gtsWsTreeInput);
	}

	public void addMasterDataList(GtnWsRecordBean GtnWsRecordBean, List<Object> rowList,
			GtnWsTreeInput gtnWsTreeInput) {
		int startIndex = gtnWsTreeInput.getMasterFileIgnoreIndex();
		int endIndex = gtnWsTreeInput.getMasterFileInputColumn().size();
		for (int i = startIndex + 1; i < endIndex; i++) {
			if (rowList.get(i) != null) {
				GtnWsRecordBean
						.addProperties(rowList.get(i) == null ? StringUtils.EMPTY : getActualData(rowList.get(i)));
			} else {
				GtnWsRecordBean.addProperties(rowList.get(i) == null ? StringUtils.EMPTY : rowList.get(i).toString());
			}
		}
	}

	private Object getActualData(Object data) {

		if (data instanceof java.lang.Long) {
			Date date = new Date((long) data);
			return date;
		} else {
			return data.toString();
		}

	}

	public void addActualAndForecastData(GtnWsTreeNode treeNode, GtnWsRecordBean GtnWsRecordBean, List<Object> rowList,
			GtnWsTreeInput gtnWsTreeInput) throws GtnFrameworkGeneralException {

		List<Object> calculatedData;
		if (gtnWsTreeInput.isIsAscending()) {
			calculatedData = filterPeriodDataAscending(gtnWsTreeInput, treeNode, rowList);
		} else {
			calculatedData = filterPeriodDataDescending(gtnWsTreeInput, treeNode, rowList);
		}
		addToGtnWsRecordBean(GtnWsRecordBean, calculatedData);
	}

	private void addToGtnWsRecordBean(GtnWsRecordBean GtnWsRecordBean, List<Object> rowList) {
		for (Object object : rowList) {
			String value = String.valueOf(object);
			GtnWsRecordBean.addProperties("null".equals(value) ? StringUtils.EMPTY : value);
		}
	}

	private int calculateTotalPeriodRange(int startYear, int startMonth, int endYear, int endMonth) {
		int diffYear = endYear - startYear;
		return diffYear * 12 + endMonth - startMonth;
	}

	public GtnWsTreeInput generatReturnsTreeInput(GtnForecastBean gtnForecastBean) {

		GtnWsTreeInput gtnWsTreeInput = new GtnWsTreeInput();
		Calendar historyStartDate = Calendar.getInstance();
		historyStartDate.add(Calendar.MONTH, -historyStartDate.get(Calendar.MONTH) % 12);
		historyStartDate.add(Calendar.YEAR, -3);
		gtnWsTreeInput.setActualStartYear(historyStartDate.get(Calendar.YEAR));
		gtnWsTreeInput.setActualStartMonth(0);

		Calendar historyEndDate = Calendar.getInstance();
		historyEndDate.add(Calendar.MONTH, -1);
		gtnWsTreeInput.setActualEndYear(historyEndDate.get(Calendar.YEAR));
		gtnWsTreeInput.setActualEndMonth(historyEndDate.get(Calendar.MONTH));

		Calendar projectionStartDate = Calendar.getInstance();
		projectionStartDate.setTime(gtnForecastBean.getForecastStartDate());
		gtnWsTreeInput.setProjectionStartYear(projectionStartDate.get(Calendar.YEAR));
		gtnWsTreeInput.setProjectionStartMonth(projectionStartDate.get(Calendar.MONTH));

		Calendar projectionEndDate = Calendar.getInstance();
		projectionEndDate.setTime(gtnForecastBean.getForecastEndDate());
		gtnWsTreeInput.setProjectionEndYear(projectionEndDate.get(Calendar.YEAR));
		gtnWsTreeInput.setProjectionEndMonth(projectionEndDate.get(Calendar.MONTH));
		gtnWsTreeInput.setIsAscending(gtnForecastBean.isAscending());
		gtnWsTreeInput.setActualOrProjection(gtnForecastBean.getActualOrProjection());

		Map<String, String> resource = new GtnWsReturnsResourceService().loadAllPropertiesInMap("file-name",
				CommonConstants.RESOURCES_PATH);

		gtnWsTreeInput.setActualDataFileInputColumn(
				Arrays.asList(resource.get("RETURNS_FORECAST_ACTUAL_FILE_COLUMN_INFO").split(",")));
		gtnWsTreeInput.setProjectionDataFileInputColumn(
				Arrays.asList(resource.get("RETURNS_FORECAST_PROJECTION_FILE_COLUMN_INFO").split(",")));
		gtnWsTreeInput.setMasterFileInputColumn(
				Arrays.asList(resource.get("RETURNS_FORECAST_MASTER_FILE_COLUMN_INFO").split(",")));

		gtnWsTreeInput.setActualColumnFormat(
				Arrays.asList(resource.get("RETURNS_FORECAST_ACTUAL_FILE_COLUMN_FORMAT").split(";")));
		gtnWsTreeInput.setProjectionColumnFormat(
				Arrays.asList(resource.get("RETURNS_FORECAST_PROJECTION_FILE_COLUMN_FORMAT").split(";")));

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
		configureSelectedIndexes(gtnWsTreeInput, resource);
		gtnWsTreeInput.setActualFormulaMap(actualFormulaMap);
		gtnWsTreeInput.setProjectionFormulaMap(projectionFormulaMap);

		return gtnWsTreeInput;
	}

	private void configureSelectedIndexes(GtnWsTreeInput gtnWsTreeInput, Map<String, String> resource) {

		gtnWsTreeInput.setSelectedProjectedListViewColumn(new ArrayList<Integer>());
		for (String col : resource.get("RETURNS_FORECAST_PROJECTION_LIST_VIEW_COLUMN_INFO").split(",")) {
			gtnWsTreeInput.getSelectedProjectedListViewColumn()
					.add(gtnWsTreeInput.getProjectionDataFileInputColumn().indexOf(col));
		}

		gtnWsTreeInput.setSelectedActualListViewColumn(new ArrayList<Integer>());
		for (String col : resource.get("RETURNS_FORECAST_ACTUAL_LIST_VIEW_COLUMN_INFO").split(",")) {
			gtnWsTreeInput.getSelectedActualListViewColumn()
					.add(gtnWsTreeInput.getActualDataFileInputColumn().indexOf(col));
		}

	}

	private List<Object> filterPeriodDataAscending(GtnWsTreeInput treeinput, GtnWsTreeNode currentNode,
			List<Object> resultList) throws GtnFrameworkGeneralException {
		List<Object> finalRowResult = new ArrayList<>();

		int totalPeriodRange = calculateTotalPeriodRange(treeinput.getActualStartYear(),
				treeinput.getActualStartMonth(), treeinput.getProjectionEndYear(), treeinput.getProjectionEndMonth());

		int historyStartPeriod = calculateTotalPeriodRange(treeinput.getActualStartYear(),
				treeinput.getActualStartMonth(), treeinput.getSelectedHistoryYear(),
				treeinput.getSelectedHistoryMonth());

		int actualEndPeriod = calculateTotalPeriodRange(treeinput.getActualStartYear(), treeinput.getActualStartMonth(),
				treeinput.getActualEndYear(), treeinput.getActualEndMonth());

		int projectionStartPeriod = totalPeriodRange
				- calculateTotalPeriodRange(treeinput.getProjectionStartYear(), treeinput.getProjectionStartMonth(),
						treeinput.getProjectionEndYear(), treeinput.getProjectionEndMonth());

		int resultStartIndex = treeinput.getMasterFileInputColumn().size()
				+ (historyStartPeriod) * treeinput.getActualDataFileInputColumn().size();

		if (historyStartPeriod > projectionStartPeriod) {
			resultStartIndex += (historyStartPeriod - projectionStartPeriod)
					* treeinput.getProjectionDataFileInputColumn().size();
		}

		for (int k = historyStartPeriod; k <= totalPeriodRange;) {
			List<List<Object>> actualSublist = new ArrayList<>();
			List<List<Object>> projectionSublist = new ArrayList<>();
			int nextStartPeriod = 0;
			if (k >= historyStartPeriod && k + treeinput.getFrequency() < totalPeriodRange) {
				nextStartPeriod = treeinput.getFrequency();
			} else if (k == historyStartPeriod) {
				nextStartPeriod = treeinput.getFrequency()
						- ((treeinput.getSelectedHistoryMonth()) % treeinput.getFrequency());
			} else if (k + treeinput.getFrequency() >= totalPeriodRange) {

				if (k == totalPeriodRange) {
					nextStartPeriod = 1;
				} else {
					nextStartPeriod = totalPeriodRange - k;
					if (treeinput.getFrequency() != 1) {
						nextStartPeriod++;
					}
				}

			}

			for (int j = k; j < k + nextStartPeriod; j++) {

				if (j <= actualEndPeriod) {
					List<Object> subList = resultList.subList(resultStartIndex,
							resultStartIndex + treeinput.getActualDataFileInputColumn().size());
					resultStartIndex += subList.size();
					actualSublist.add(subList);
				}
				if (j >= projectionStartPeriod - 1) {
					List<Object> subList = resultList.subList(resultStartIndex,
							resultStartIndex + treeinput.getProjectionDataFileInputColumn().size());
					resultStartIndex += subList.size();
					projectionSublist.add(subList);
				}
			}

			if (!actualSublist.isEmpty() && !"Projections".equals(treeinput.getActualOrProjection())) {
				finalRowResult.addAll(getCalculatedPeriodData(actualSublist, treeinput.getActualFormulaMap(),
						currentNode.getTotalDataIndex(), treeinput.getSelectedActualListViewColumn(),
						treeinput.getActualColumnFormat()));
			}
			if (!projectionSublist.isEmpty()) {
				finalRowResult.addAll(getCalculatedPeriodData(projectionSublist, treeinput.getProjectionFormulaMap(),
						currentNode.getTotalDataIndex(), treeinput.getSelectedProjectedListViewColumn(),
						treeinput.getProjectionColumnFormat()));
			}
			k += nextStartPeriod;

		}

		return finalRowResult;
	}

	private List<Object> filterPeriodDataDescending(GtnWsTreeInput treeinput, GtnWsTreeNode currentNode,
			List<Object> resultList) throws GtnFrameworkGeneralException {
		List<Object> finalRowResult = new ArrayList<>();

		int totalPeriodRange = calculateTotalPeriodRange(treeinput.getActualStartYear(),
				treeinput.getActualStartMonth(), treeinput.getProjectionEndYear(), treeinput.getProjectionEndMonth());

		int historyStartPeriod = calculateTotalPeriodRange(treeinput.getActualStartYear(),
				treeinput.getActualStartMonth(), treeinput.getSelectedHistoryYear(),
				treeinput.getSelectedHistoryMonth());

		int actualEndPeriod = calculateTotalPeriodRange(treeinput.getActualStartYear(), treeinput.getActualStartMonth(),
				treeinput.getActualEndYear(), treeinput.getActualEndMonth());

		int projectionStartPeriod = totalPeriodRange
				- calculateTotalPeriodRange(treeinput.getProjectionStartYear(), treeinput.getProjectionStartMonth(),
						treeinput.getProjectionEndYear(), treeinput.getProjectionEndMonth());

		int resultStartIndex = resultList.size() - 1;

		for (int k = totalPeriodRange; k >= historyStartPeriod;) {
			List<List<Object>> actualSublist = new ArrayList<>();
			List<List<Object>> projectionSublist = new ArrayList<>();
			int nextStartPeriod = 0;
			if (k == totalPeriodRange) {
				nextStartPeriod = (treeinput.getProjectionEndMonth() % treeinput.getFrequency()) + 1;
			} else if (k <= totalPeriodRange && k - treeinput.getFrequency() >= historyStartPeriod) {
				nextStartPeriod = treeinput.getFrequency();
			} else if (k - treeinput.getFrequency() < historyStartPeriod) {
				nextStartPeriod = k - historyStartPeriod + 1;
			}
			for (int j = k - nextStartPeriod; j < k; j++) {

				if (j >= projectionStartPeriod - 1) {
					List<Object> subList = resultList.subList(
							resultStartIndex - treeinput.getProjectionDataFileInputColumn().size() + 1,
							resultStartIndex + 1);
					resultStartIndex -= subList.size();
					projectionSublist.add(subList);
				}
				if (j < actualEndPeriod) {
					List<Object> subList = resultList.subList(
							resultStartIndex - treeinput.getActualDataFileInputColumn().size() + 1,
							resultStartIndex + 1);
					resultStartIndex -= subList.size();
					actualSublist.add(subList);
				}

			}

			if (!actualSublist.isEmpty()) {
				List<Object> list = getCalculatedPeriodData(actualSublist, treeinput.getActualFormulaMap(),
						currentNode.getTotalDataIndex(), treeinput.getSelectedActualListViewColumn(),
						treeinput.getActualColumnFormat());
				finalRowResult.addAll(list);
			}
			if (!projectionSublist.isEmpty()) {
				List<Object> list = getCalculatedPeriodData(projectionSublist, treeinput.getProjectionFormulaMap(),
						currentNode.getTotalDataIndex(), treeinput.getSelectedProjectedListViewColumn(),
						treeinput.getProjectionColumnFormat());
				finalRowResult.addAll(list);
			}

			k -= nextStartPeriod;
		}

		return finalRowResult;
	}

	private List<Object> getCalculatedPeriodData(List<List<Object>> sublist, Map<Integer, String> treeinput,
			int totalDataRow, List<Integer> selectedIndex, List<DecimalFormat> columnFormat)
			throws GtnFrameworkGeneralException {
		List<Object> aggregatedData = aggregateData(sublist);
		applyFormulaToAggregateData(aggregatedData, treeinput, totalDataRow * sublist.size());
		return filterSelectedIndexToList(selectedIndex, aggregatedData, columnFormat);
	}

	private List<Object> aggregateData(List<List<Object>> aggregationInput) {
		List<Object> parentResult = new ArrayList<>();
		for (List<Object> parentResult1 : aggregationInput) {
			if (parentResult.isEmpty()) {
				parentResult.addAll(parentResult1);
			} else {

				for (int i = 0; i < parentResult1.size(); i++) {
					double currentValue = 0d;
					if (parentResult.get(i) instanceof Double) {
						currentValue += (Double) parentResult.get(i);
					}

					if (parentResult1.get(i) instanceof Double) {
						currentValue += (Double) parentResult1.get(i);
					}

					parentResult.set(i, currentValue);
				}
			}
		}
		return parentResult;
	}

	private List<Object> filterSelectedIndexToList(List<Integer> selectedIndex, List<Object> resultList,
			List<DecimalFormat> columnFormat) {
		List<Object> list = new ArrayList<>();
		for (int index : selectedIndex) {
			Double value = (Double) resultList.get(index);
			list.add(columnFormat.get(index).format(value == null ? 0 : value));
		}
		return list;
	}

	private void applyFormulaToAggregateData(List<Object> resultList, Map<Integer, String> treeinput, int totalDataRow)
			throws GtnFrameworkGeneralException {
		for (Map.Entry<Integer, String> entrySet : treeinput.entrySet()) {
			Integer key = entrySet.getKey();
			String formula = entrySet.getValue();
			Double value = 0d;
			if (formula.equals("AVG")) {
				if (resultList.get(key) instanceof Double) {
					value = (double) resultList.get(key) / totalDataRow;
				}
			} else {
				for (int k = 0; k < resultList.size(); k++) {
					double replacevalue = 0d;
					Object tempValue = resultList.get(k);
					if (tempValue != null) {
						Double doubleValue = Double.valueOf(tempValue.toString());
						if (!doubleValue.isNaN() && !doubleValue.isInfinite()) {
							replacevalue = doubleValue;
						}
					}
					formula = formula.replace("?" + k, BigDecimal.valueOf(replacevalue).toPlainString());
				}
				Evaluator evaluator = new Evaluator();
				try {
					value = evaluator.getNumberResult(formula);
				} catch (EvaluationException e) {
					throw new GtnFrameworkGeneralException("Error in executing query : " + e);
				}
			}
			if (value.isNaN()) {
				resultList.set(key, 0d);
			} else {
				resultList.set(key, value);
			}

		}

	}

	public Calendar calculateHistoryPeriods(int selectedHistory, String frequency) {
		int monthToDeduct = -1;
		Calendar calendar = Calendar.getInstance();

		if (null != frequency) {
			switch (frequency.toUpperCase()) {
			case "MONTHLY":
				monthToDeduct = monthToDeduct * selectedHistory;
				break;
			case "QUARTERLY":
				monthToDeduct = ((selectedHistory * 3) + (calendar.get(Calendar.MONTH) % 3)) * monthToDeduct;
				break;
			case "SEMI-ANNUALLY":
				monthToDeduct = ((selectedHistory * 6) + (calendar.get(Calendar.MONTH) % 6)) * monthToDeduct;
				break;
			default:
				monthToDeduct = ((selectedHistory * 12) + (calendar.get(Calendar.MONTH) % 12)) * monthToDeduct;
				break;
			}
		}

		calendar.add(Calendar.MONTH, monthToDeduct);
		return calendar;
	}

}
