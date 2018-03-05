/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.duallistbox;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.duallistbox.GtnWsDualListBoxFilter;
import com.stpl.gtn.gtn2o.ws.components.duallistbox.GtnWsDualListBoxFilterManager;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsDatabaseService;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsResourceService;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.forecast.service.duallistbox.filter.GtnWsDualListBoxDataFilter;
import com.stpl.gtn.gtn2o.ws.forecast.service.duallistbox.filter.GtnWsDualListBoxHierarchyDataFilter;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Service
public class GtnWsReturnsDualListBoxService {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsReturnsDualListBoxService.class);

	@Autowired
	GtnWsReturnsDatabaseService databaseService;

	@Autowired
	private GtnWsDualListBoxSerializingService serializingService;

	@Autowired
	private GtnWsReturnsResourceService resourceService;

	@Autowired
	GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	public GtnWsReturnsDatabaseService getDatabaseService() {
		return databaseService;
	}

	public void setDatabaseService(GtnWsReturnsDatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	public GtnWsDualListBoxSerializingService getSerializingService() {
		return serializingService;
	}

	public void setSerializingService(GtnWsDualListBoxSerializingService serializingService) {
		this.serializingService = serializingService;
	}

	public GtnWsReturnsResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(GtnWsReturnsResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public GtnFrameworkSqlQueryEngine getGtnFrameworkSqlQueryEngine() {
		return gtnFrameworkSqlQueryEngine;
	}

	public void setGtnFrameworkSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine) {
		this.gtnFrameworkSqlQueryEngine = gtnFrameworkSqlQueryEngine;
	}

	public String generateFile(List<Object> inputList, String testFilePath)
			throws IOException, GtnFrameworkGeneralException {

		String path = generateFilePath(inputList, testFilePath);
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = path + "/" + getLeftTableBaseFileName(inputList.get(6).toString()) + inputList.get(0)
				+ ".ser";
		LOGGER.info("Created File Name:" + fileName);
		File filePath = new File(fileName);
		filePath.createNewFile();
		saveToFile(filePath.getAbsolutePath(), inputList);
		return filePath.getAbsolutePath();
	}

	/**
	 * 
	 * @param inputParameters
	 * @return File will be from
	 *         GTN_BASE_PATH/SERIALISED_FILE_PATH/${Module}/${Formatted_Date}/${
	 *         User_ID}/${Session_ID}/
	 */
	private String generateFilePath(List<Object> inputParameters, String testFilePath) {
		StringBuilder fileName = new StringBuilder(System.getProperty(GtnFrameworkCommonStringConstants.GTN_DATA_PATH));
		fileName.append("/");
		fileName.append(GtnFrameworkCommonStringConstants.SERIALISED_FILE_PATH);
		fileName.append(inputParameters.get(7));
		fileName.append("/");
		fileName.append(getFormattedDate());
		fileName.append("/");
		fileName.append(inputParameters.get(9));
		fileName.append("/");
		if (inputParameters.get(8) != null) {
			fileName.append(inputParameters.get(8));
		}
		return fileName.toString();
	}

	private String getFormattedDate() {
		String dateFormat = "MMM_dd_yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(new Date());
	}

	private String getLeftTableBaseFileName(String fileName) {
		return fileName.trim().replace(" ", "_");
	}

	private void saveToFile(String fileName, List<Object> inputList) throws GtnFrameworkGeneralException {
		try {
			Set<GtnWsRecordBean> rawDataSet = getLeftTableRawData(fileName, inputList);
			serializingService.serialize(rawDataSet, fileName);
		} catch (Exception e) {
			LOGGER.error("Error in Returns Save To file Method", e);
			throw new GtnFrameworkGeneralException(e.getMessage(), e);
		}
	}

	private Set<GtnWsRecordBean> getLeftTableRawData(String fileName, List<Object> inputList)
			throws GtnFrameworkGeneralException {
		Set<GtnWsRecordBean> resultList = new HashSet<>();
		try {
			List<Object[]> result = gtnFrameworkSqlQueryEngine.executeSelectQuery(getRelationshipDetails(inputList),
					fileName);
			for (Object[] objects : result) {
				resultList.add(new GtnWsRecordBean(objects));
			}
			result.clear();
		} catch (Exception ex) {
			LOGGER.error("Error in Returns Left Table Raw Data", ex);
			throw new GtnFrameworkGeneralException(ex);
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	private String getRelationshipDetails(List<Object> inputList) throws GtnFrameworkGeneralException {

		String hierarchyLevelDetailsQuery = gtnWsSqlService.getQuery("GET_HIERARCHY_DETAILS");
		List<Object[]> tempList = (List<Object[]>) gtnFrameworkSqlQueryEngine.executeSelectQuery(
				hierarchyLevelDetailsQuery, new Object[] { inputList.get(0).toString() },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });

		if ("customer selection".equals(inputList.get(6))) {
			return buildRelationshipLevelQueryForCustomerHierarchy(inputList.get(0).toString(), inputList, tempList);
		} else {
			return buildRelationshipLevelQuery(inputList.get(0).toString(), inputList, tempList);
		}
	}

	private String buildRelationshipLevelQuery(final String relationshipBuilderSid, List<Object> inputList,
			final List<Object[]> tempList) {
		String finalQuery = buildFilterQuery(relationshipBuilderSid, inputList);

		for (int i = tempList.size() - 1; i >= 0; i--) {
			Object[] object = tempList.get(i);
			String customSql = gtnWsSqlService.getQuery(getLevelDetailsQueryName(object[4].toString()));
			customSql = customSql.replace("?FIELD", String.valueOf(object[0]));
			customSql = customSql.replace("?TABLE", String.valueOf(object[1]));
			customSql = customSql.replace("?IDCOL", String.valueOf(object[2]));
			customSql = customSql.replace("?LNO", String.valueOf(object[3]));
			if (i != 0) {
				customSql = customSql.concat(" UNION ALL ");
			}
			finalQuery += customSql;
		}
		tempList.clear();
		return finalQuery;
	}

	private String buildRelationshipLevelQueryForCustomerHierarchy(final String relationshipBuilderSid,
			List<Object> inputList, final List<Object[]> tempList) {
		String finalQuery = "";
		for (int i = tempList.size() - 1; i >= 0; i--) {
			Object[] object = tempList.get(i);
			String customSql = gtnWsSqlService.getQuery("relation-level-details-customer-hierarchy-query");
			customSql = customSql.replace("?FIELD", String.valueOf(object[0]));
			customSql = customSql.replace("?TABLE", String.valueOf(object[1]));
			customSql = customSql.replace("?IDCOL", String.valueOf(object[2]));
			customSql = customSql.replace("?LNO", String.valueOf(object[3]));
			customSql = customSql.replace("?RBUILDERSID", String.valueOf(inputList.get(0)));

			if (i != 0) {
				customSql = customSql.concat(" UNION ALL ");
			}
			finalQuery += customSql;
		}
		tempList.clear();
		return finalQuery;
	}

	private String buildFilterQuery(final String relationshipBuilderSid, List<Object> inputList) {
		String query = gtnWsSqlService.getQuery("relation-level-filter-query");
		query = query.replace("[?RLD_ID]", relationshipBuilderSid);
		query = query.replace("[?PRODUCT_GROUP]",
				inputList.get(4) == null || StringUtils.isBlank(inputList.get(4).toString()) ? "0"
						: inputList.get(4).toString());
		query = query.replace("[?GLCOMP]",
				inputList.get(2) == null || StringUtils.isBlank(inputList.get(2).toString()) ? "0"
						: inputList.get(2).toString());
		query = query.replace("[?BUSINESS_UNIT]",
				inputList.get(3) == null || StringUtils.isBlank(inputList.get(3).toString()) ? "0"
						: inputList.get(3).toString());
		query = query.replace("[?PRDT_JOIN]",
				inputList.get(4) == null || StringUtils.isBlank(inputList.get(4).toString()) ? "LEFT" : "");
		return query;
	}

	private String getLevelDetailsQueryName(String levelName) {

		if ("ndc".equalsIgnoreCase(levelName) || "item".equalsIgnoreCase(levelName)
				|| "product".equalsIgnoreCase(levelName)) {
			return "relation-level-details-query-ndc";
		}
		return "relation-level-details-query";
	}

	@SuppressWarnings("unchecked")
	public List<GtnWsRecordBean> getLeftTableData(List<Object> inputList, String fileName)
			throws GtnFrameworkGeneralException {
		GtnWsDualListBoxFilter filter = new GtnWsDualListBoxDataFilter(
				(List<String>) inputList.get(inputList.size() - 1), inputList.get(5).toString(),
				inputList.get(1).toString());
		List<GtnWsRecordBean> dataList = null;
		GtnWsDualListBoxFilterManager.setFilter(filter);

		List<GtnWsRecordBean> dualListBoxRawDataList = new ArrayList<>(getFilteredDataFromFile(fileName));
		if (!dualListBoxRawDataList.isEmpty()) {
			dataList = new ArrayList<>(dualListBoxRawDataList.size());
			int[] indexArray = getIndexBasedOnLevel(dualListBoxRawDataList.get(0).getRawObjectArray());
			for (GtnWsRecordBean dualListDTO : dualListBoxRawDataList) {
				buildGtnWsRecordBean(dualListDTO, indexArray, dataList);
			}
			dualListBoxRawDataList.clear();
		}
		return dataList;

	}

	private void buildGtnWsRecordBean(GtnWsRecordBean recordBean, int[] indexNeedToBeAddedList,
			List<GtnWsRecordBean> dataList) {
		Object[] objectArray = recordBean.getRawObjectArray();
		for (int index : indexNeedToBeAddedList) {
			recordBean.addProperties(objectArray[index] == null ? "" : objectArray[index].toString());
		}
		recordBean.addAdditionalProperty(objectArray[0]);
		recordBean.addAdditionalProperty(objectArray[3]);
		recordBean.addAdditionalProperty(objectArray[2]);
		recordBean.addAdditionalProperty(objectArray[4]);
		dataList.add(recordBean);
	}

	private int[] getIndexBasedOnLevel(Object[] obj) {
		if ("ndc".equalsIgnoreCase(obj[4].toString()) || "item".equalsIgnoreCase(obj[4].toString())
				|| "product".equalsIgnoreCase(obj[4].toString())) {
			return new int[] { 1, 5, 6 };
		} else {
			return new int[] { 1 };
		}
	}

	public List<GtnWsRecordBean> getTreeData(List<String> filterDataList, String fileName, boolean isGetAll)
			throws GtnFrameworkGeneralException {
		GtnWsDualListBoxFilter filter = new GtnWsDualListBoxHierarchyDataFilter(3, filterDataList, isGetAll);
		GtnWsDualListBoxFilterManager.setFilter(filter);
		return convertToGtnWsRecordBean(getFilteredDataFromFile(fileName));
	}

	@SuppressWarnings("unchecked")
	private Set<GtnWsRecordBean> getFilteredDataFromFile(String fileName) throws GtnFrameworkGeneralException {
		long startTime = System.currentTimeMillis();
		Set<GtnWsRecordBean> resultSet;
		try {
			resultSet = (HashSet<GtnWsRecordBean>) serializingService.deserialize(fileName);
			resultSet.remove(new GtnWsRecordBean(GtnFrameworkCommonConstants.EMPTY_ARRAY));
			LOGGER.info("Deserialazing time :" + (System.currentTimeMillis() - startTime));
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			throw new GtnFrameworkGeneralException(
					"Error during deserialization.Please check the file. File Name:" + fileName, e);
		}
		return resultSet;
	}

	private List<GtnWsRecordBean> convertToGtnWsRecordBean(final Set<GtnWsRecordBean> inputSet) {

		List<GtnWsRecordBean> inputList = new ArrayList<>(inputSet.size());
		inputList.addAll(inputSet);
		// List is sorted based on Level No to avoid problems while building the
		// tree in UI.
		Collections.sort(inputList, new GtnWsDualListBoxRawDataComparator(2));

		int[] indexArray = new int[] { 1 };
		inputSet.clear();
		return convertRawDataToBean(inputList, indexArray);
	}

	private List<GtnWsRecordBean> convertRawDataToBean(final List<GtnWsRecordBean> inputRawDataList,
			final int[] indexArray) {
		List<GtnWsRecordBean> resultList = new ArrayList<>();
		for (GtnWsRecordBean rawData : inputRawDataList) {
			buildGtnWsRecordBean(rawData, indexArray, resultList);
		}
		inputRawDataList.clear();
		return resultList;
	}

}
