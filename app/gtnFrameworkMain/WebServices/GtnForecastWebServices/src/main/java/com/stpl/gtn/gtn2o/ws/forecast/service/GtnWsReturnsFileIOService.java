/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.DataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsGroupedDataFileManipulator;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeInput;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

/**
 *
 * @author STPL
 */
@Service
@Scope("singleton")
public class GtnWsReturnsFileIOService {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsReturnsFileIOService.class);

	@Autowired
	GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsTreeService treeService;

	@Autowired
	private GtnWsReturnsResourceService resourceService;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public GtnWsTreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(GtnWsTreeService treeService) {
		this.treeService = treeService;
	}

	public GtnWsReturnsResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(GtnWsReturnsResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}

	public void createFile(final GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {

		String[] fileList = getFileList(gtnForecastBean.getModuleName());
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		String basePath = getFilePath(getModuleName(gtnForecastBean), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		for (String fileName : fileList) {
			String queryId = getQueryIdWithFileName(fileName, gtnForecastBean.getQueryParameters());
			executorService.execute(new GtnWsCustomFileWriterRunnable(fileName, basePath, gtnForecastBean, queryId,
					gtnSqlQueryEngine, gtnWsSqlService));
		}
		executorService.shutdown();
		int timeOut = Integer.parseInt(resourceService.resourceFileName(GtnFrameworkWebserviceConstant.FILENAME,
				CommonConstants.RESOURCES_PATH, "THREAD_WAIT_TIME_OUT_IN_MINUTES"));
		try {
			executorService.awaitTermination(timeOut, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			throw new GtnFrameworkGeneralException("Error in executing query : " + e);

		}
	}

	private String getQueryIdWithFileName(String fileName, Map<String, List<String>> queryInputMap) {
		for (Map.Entry<String, List<String>> entry : queryInputMap.entrySet()) {

			if (entry.getKey().startsWith(fileName)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public String getModuleName(GtnForecastBean gtnForecastBean) {
		String moduleName = gtnForecastBean.getModuleName()
				.replace(GtnFrameworkForecastConstantCommon.UNDERSCORE + gtnForecastBean.getMode(), "");
		return moduleName;
	}

	public void createTreeFile(final GtnForecastBean gtnForecastBean, List<List<Object>> masterFileData,
			String queryKey) throws GtnFrameworkGeneralException {
		String basePath = getFilePath(getModuleName(gtnForecastBean), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		String finalFileName = basePath + getTreeFileName(gtnForecastBean.getHierarchyType(), null);

		List<String> queryParameters = new ArrayList<>(3);
		queryParameters.add(gtnForecastBean.getQueryParameters().get(queryKey).get(0));
		queryParameters.add(String.valueOf(gtnForecastBean.getRelationshipBuilderSid()));
		queryParameters.add(String.valueOf(gtnForecastBean.getForecastLevel()));
		String queryString = gtnWsSqlService.getQueryWithReplacedValues("GET_RELATIONSHIP_LEVEL", queryParameters);
		List<Object[]> dataList = gtnSqlQueryEngine.executeSelectQuery(queryString, finalFileName);
		Map<String, String> hierarchyNames = getHierarchyNames(gtnForecastBean);
		GtnWsTreeNode rootNode = treeService.buildTree(dataList, hierarchyNames);
		addMasterTreeCount(rootNode, masterFileData);
		writeDataIntoFile(finalFileName, rootNode);

	}

	@SuppressWarnings("unchecked")
	private Map<String, String> getHierarchyNames(final GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {
		Map<String, String> inputParam = new HashMap<>();

		String sqlQueryString = gtnWsSqlService.getQuery("GET_HIERARCHY_DETAILS").replace("?",
				String.valueOf(gtnForecastBean.getRelationshipBuilderSid()));
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(sqlQueryString);
		String hierarchyNoType = "PROD_HIERARCHY_NO";
		String customSql;
		String finalQuery = StringUtils.EMPTY;
		for (int i = resultList.size() - 1; i >= 0; i--) {
			Object[] tempListObject = resultList.get(i);
			customSql = gtnWsSqlService.getQuery("GET_RELATIONSHIP_LEVEL_VALUES");
			customSql = customSql.replace("?FIELD", String.valueOf(tempListObject[0]));
			customSql = customSql.replace("?TABLE", String.valueOf(tempListObject[1]));
			customSql = customSql.replace("?IDCOL", String.valueOf(tempListObject[2]));
			customSql = customSql.replace("?LNO", String.valueOf(tempListObject[3]));
			customSql = customSql.replace("?RBSID", String.valueOf(gtnForecastBean.getRelationshipBuilderSid()));
			customSql = customSql.replace("?HIERARCHY_NO", hierarchyNoType);
			if (i != 0) {
				customSql = customSql.concat(" UNION ALL ");
			}
			finalQuery += customSql;
		}
		resultList.clear();
		resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(finalQuery);
		for (int j = resultList.size() - 1; j >= 0; j--) {
			Object[] object = resultList.get(j);
			inputParam.put(String.valueOf(object[0]), String.valueOf(object[1]));
		}

		return inputParam;
	}

	/**
	 *
	 * @param fileName
	 * @param object
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeDataIntoFile(String fileName, Object object) throws GtnFrameworkGeneralException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
		} catch (IOException e) {
			LOGGER.error("Exception while Excuting writeDataIntoFile method", e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	/**
	 *
	 * @param <T>
	 * @param fileName
	 * @param clazz
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public <T extends Object> T readDataFromFile(String fileName, Class<T> clazz) throws GtnFrameworkGeneralException {
		try (FileInputStream fileOutputStream = new FileInputStream(fileName);
				ObjectInputStream objectOutputStream = new ObjectInputStream(fileOutputStream);) {
			return clazz.cast(objectOutputStream.readObject());
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.error("Exception while Excuting readDataFromFile method", e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	/**
	 *
	 * @param fileName
	 * @param list
	 * @throws IOException
	 */
	public void writeToJSONFile(final String fileName, List<Object[]> list) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();
		for (Object[] object : list) {
			writeToJsonArray(arrayNode, object);
		}
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			JsonGenerator generator = mapper.getFactory().createGenerator(fileWriter);
			mapper.writeTree(generator, arrayNode);
			fileWriter.flush();
		}
	}

	/**
	 *
	 * @param parent
	 * @param array
	 */
	public void writeToJsonArray(ArrayNode parent, Object[] array) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();
		for (Object object : array) {
			arrayNode.add("" + object);
		}
		parent.add(arrayNode);
	}

	/**
	 *
	 * @param testFilePath
	 * @param inputParam
	 * @return
	 * 
	 * 		File will be from
	 *         GTN_BASE_PATH/SERIALISED_FILE_PATH/${Module}/${Formatted_Date}/${
	 *         User_ID}/${Session_ID}/
	 */
	public String getFilePath(final String moduleName, final String userId, final String sessionId, String testFilePath)
			throws GtnFrameworkGeneralException {
		StringBuilder filePath = new StringBuilder(System.getProperty(GtnFrameworkCommonStringConstants.GTN_DATA_PATH));
		filePath.append(GtnFrameworkCommonStringConstants.SERIALISED_FILE_PATH);
		filePath.append(moduleName);
		filePath.append("/");
		filePath.append(getFormattedDate());
		filePath.append("/");
		filePath.append(userId);
		filePath.append("/");
		filePath.append(sessionId);
		filePath.append("/");
		Path path = Paths.get(filePath.toString());
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				LOGGER.error("Exception while Excuting getFilePath method", e);
				throw new GtnFrameworkGeneralException(e);

			}
		}
		return filePath.toString();
	}

	/**
	 *
	 * @return
	 */
	protected String[] getFileList(String moduleName) {
		String key = moduleName + "_base_file_list";
		String files = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.FILENAME,
				CommonConstants.RESOURCES_PATH, key);
		String[] fileList = files.split(",");
		return fileList;
	}

	private String getFormattedDate() {
		String dateFormat = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.FILENAME,
				CommonConstants.RESOURCES_PATH, "date_format");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(new Date());
	}

	public String getTreeFileName(final String hierarchyType, final String customHiearchyName) {

		if ("Customer".equalsIgnoreCase(hierarchyType) || "Product".equalsIgnoreCase(hierarchyType)) {
			return hierarchyType + "_Tree" + GtnFrameworkCommonStringConstants.STPL_EXTENSION;
		} else {
			return customHiearchyName;
		}

	}

	@SuppressWarnings("unchecked")
	public List<List<Object>> readJsonDataFromFile(final String path) throws GtnFrameworkGeneralException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<List<Object>> list;
		try {
			list = objectMapper.readValue(new FileReader(path), List.class);
		} catch (IOException e) {
			LOGGER.error("Exception while Excuting readJsonDataFromFile method", e);
			throw new GtnFrameworkGeneralException(e);
		}
		return list;
	}

	public void writeJsonDataToFile(final String path, final List<List<Object>> fileList)
			throws GtnFrameworkGeneralException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileWriter(path), fileList);
		} catch (IOException e) {
			LOGGER.error("Exception while Excuting writeJsonDataToFile method", e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void createGroupedDataFile(final GtnForecastBean gtnForecastBean, List<List<Object>> masterFileData)
			throws GtnFrameworkGeneralException {

		try {
			String basePath = getFilePath(getModuleName(gtnForecastBean), gtnForecastBean.getUserId(),
					gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
			GtnWsTreeNode rootNode = readDataFromFile(
					basePath + getTreeFileName(gtnForecastBean.getHierarchyType(), null), GtnWsTreeNode.class);
			List<List<Object>> resultSet = new ArrayList<>();
			treeService.loadDataFromMasterFile(masterFileData, resultSet, rootNode);
			List<List<Object>> actualFileData = readJSONDataFromFile(
					basePath + "RETURNS_FORECAST_ACTUAL" + GtnFrameworkCommonStringConstants.STPL_EXTENSION,
					List.class);
			List<List<Object>> projectionFileData = readJSONDataFromFile(
					basePath + "RETURNS_FORECAST_PROJECTION" + GtnFrameworkCommonStringConstants.STPL_EXTENSION,
					List.class);
			GtnWsGroupedDataFileManipulator groupedDataFileManipulator = new GtnWsGroupedDataFileManipulator();

			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();

			gtnWsForecastRequest.setGtnForecastBean(gtnForecastBean);
			gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(gtnWsForecastRequest);

			GtnWsTreeInput gtnWsTreeInput = groupedDataFileManipulator
					.generatReturnsTreeInput(gtnUIFrameworkWebserviceRequest);
			groupedDataFileManipulator.writeToFile(actualFileData, projectionFileData, resultSet, gtnWsTreeInput,
					rootNode);

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(new FileOutputStream(basePath + "TreeData.stpl"), resultSet);

		} catch (Exception ex) {
			LOGGER.error("Exception while Excuting createGroupedDataFile method", ex);
			throw new GtnFrameworkGeneralException("Error in createGroupedDataFile : " + ex);
		}

	}

	/**
	 *
	 * @param <T>
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	public <T extends Object> T readJSONDataFromFile(String fileName, Class<T> clazz)
			throws GtnFrameworkGeneralException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(new FileInputStream(fileName), clazz);
		} catch (IOException e) {
			throw new GtnFrameworkGeneralException("Error in executing query : " + e);
		}

	}

	public List<Integer> getEndLevelHierarchyNode(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		GtnWsTreeNode rootNode = getTreeNode(gtnForecastBean);
		List<Integer> dataIndex = new ArrayList<>();
		for (String checkedHierarchyNumber : gtnForecastBean.getCheckedHierarchyNumbers()) {
			getChildNode(rootNode, checkedHierarchyNumber, dataIndex);
		}
		return dataIndex;
	}

	public GtnWsTreeNode getTreeNode(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		String basePath = getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		String finalFileName = basePath + getTreeFileName(gtnForecastBean.getHierarchyType(), null);
		return readDataFromFile(finalFileName, GtnWsTreeNode.class);
	}

	public List<Integer> filterTireOne(List<Integer> dataIndex, List<List<Object>> masterData, int tireOneIndex)
			throws GtnFrameworkGeneralException {
		for (Iterator<Integer> iterator = dataIndex.listIterator(); iterator.hasNext();) {
			int masterRecordIndex = iterator.next();
			List<Object> masterRecord = masterData.get(masterRecordIndex);
			if ("false".equalsIgnoreCase(String.valueOf(masterRecord.get(tireOneIndex)))) {
				iterator.remove();
			}
		}
		return dataIndex;
	}

	public void getChildNode(final GtnWsTreeNode rootNode, final String hierarchyNumbers,
			final List<Integer> dataIndex) {
		if (rootNode.getChildren() != null) {
			for (GtnWsTreeNode treeNode : rootNode.getChildren()) {
				if (treeNode.getHierarchyNo().startsWith(hierarchyNumbers) && treeNode.getChildren() == null) {
					dataIndex.addAll(treeNode.getDataIndex());
				}
				getChildNode(treeNode, hierarchyNumbers, dataIndex);
			}
		}
	}

	public void getForecastDateDetials(final GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		configureHistoryPeriods(gtnForecastBean);
		configureForecastEndDate(gtnForecastBean);
	}

	private void configureHistoryPeriods(final GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		String historyRange = resourceService.resourceFileName(GtnFrameworkWebserviceConstant.FILENAME,
				CommonConstants.RESOURCES_PATH, "FORECAST_HISTORY_RANGE");

		Calendar calendar = Calendar.getInstance();
		gtnForecastBean.setHistoryEndYear(calendar.get(Calendar.YEAR));
		gtnForecastBean.setHistoryEndMonth(calendar.get(Calendar.MONTH));

		calendar.add(Calendar.YEAR, -Integer.valueOf(historyRange));
		calendar.add(Calendar.MONTH, -(gtnForecastBean.getHistoryEndMonth() % 12));
		calendar.set(Calendar.DATE, 1);

		gtnForecastBean.setHistoryStartYear(calendar.get(Calendar.YEAR));
		gtnForecastBean.setHistoryStartMonth(calendar.get(Calendar.MONTH));
		gtnForecastBean.setForecastStartDate(calendar.getTime());
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private void configureForecastEndDate(final GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		String sql = gtnWsSqlService.getQuery("GET_FORECAST_DATES");
		List<Object[]> list = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(sql,
				new Object[] { gtnForecastBean.getModuleName(), gtnForecastBean.getModuleName() },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING });
		Object[] obj = list.get(0);
		gtnForecastBean.setForecastEndDate((Date) obj[1]);
		if (gtnForecastBean.getProjectionEndDate().after(gtnForecastBean.getForecastEndDate())) {
			gtnForecastBean.setForecastEndDate(gtnForecastBean.getProjectionEndDate());
		}

		gtnForecastBean.setProjectionStartYear(gtnForecastBean.getForecastStartDate().getYear() + 1900);
		gtnForecastBean.setProjectionStartMonth(gtnForecastBean.getForecastStartDate().getMonth());
		gtnForecastBean.setProjectionEndYear(gtnForecastBean.getProjectionEndDate().getYear() + 1900);
		gtnForecastBean.setProjectionEndMonth(gtnForecastBean.getProjectionEndDate().getMonth());

	}

	private void addMasterTreeCount(GtnWsTreeNode rootNode, List<List<Object>> masterSource) {

		for (int i = 0; i < masterSource.size(); i++) {
			for (GtnWsTreeNode children : rootNode.getChildren()) {
				addTreeCount(masterSource.get(i), children, i);
			}
		}
	}

	private void addTreeCount(List<Object> masterSourceRow, GtnWsTreeNode node, int dataIndex) {
		try {
			if (masterSourceRow.get(0).toString().startsWith(node.getHierarchyNo())) {
				node.setTotalDataIndex(node.getTotalDataIndex() + 1);
			}

			if (node.getChildren() == null) {
				if (masterSourceRow.get(0).toString().startsWith(node.getHierarchyNo())) {
					if (node.getDataIndex() == null) {
						node.setDataIndex(new TreeSet<Integer>());
					}
					node.getDataIndex().add(dataIndex);
				}
			} else {
				for (GtnWsTreeNode children : node.getChildren()) {
					addTreeCount(masterSourceRow, children, dataIndex);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Error in addTreeCount ", ex);
		}
	}

	public boolean deleteDir(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {

		String filePath = getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		File dir = new File(filePath);
		if (dir.isDirectory()) {
			String[] entries = dir.list();
			if (entries != null)
				for (String child : entries) {
					File file = new File(dir, child);
					file.delete();

				}
		}
		return dir.delete();
	}

	public void createDataSelectionFile(GtnForecastBean gtnForecastBean, DataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException, IOException {
		String filePath = getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		filePath = filePath + "Data_selection_properties.stpl";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new FileWriter(filePath), dataSelectionBean);
		} catch (IOException e) {
			throw new GtnFrameworkGeneralException(e);
		}
	}

	public DataSelectionBean readDataSelectionFile(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {
		String filePath = getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
		filePath = filePath + "Data_selection_properties.stpl";
		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(new FileInputStream(file), DataSelectionBean.class);
		} catch (IOException e) {
			throw new GtnFrameworkGeneralException(e);
		}
	}

}
