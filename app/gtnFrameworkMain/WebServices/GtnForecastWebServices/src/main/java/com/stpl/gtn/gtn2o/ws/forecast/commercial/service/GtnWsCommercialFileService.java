package com.stpl.gtn.gtn2o.ws.forecast.commercial.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsResourceService;
import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.forecast.service.file.GtnWsFileBean;
import com.stpl.gtn.gtn2o.ws.forecast.service.file.GtnWsFileService;
import com.stpl.gtn.gtn2o.ws.forecast.service.file.GtnWsFileWriterRunnable;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsGroupedDataFileManipulator;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeInput;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeNode;
import com.stpl.gtn.gtn2o.ws.forecast.service.tree.GtnWsTreeService;

@Service
public class GtnWsCommercialFileService {

	@Autowired
	GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnWsFileService gtnWsFileService;

	@Autowired
	private GtnWsReturnsResourceService resourceService;

	@Autowired
	private GtnWsTreeService treeService;

	public void writeSalesBaseFiles(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException, InterruptedException {

		this.createBaseFiles(gtnForecastBean);
	}

	public void writeDiscountBaseFiles(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException, InterruptedException {

		this.createBaseFiles(gtnForecastBean);
	}

	public void writeCustomerTreeFile(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {

		createTreeFile(gtnForecastBean, null, "CUSTOMER_TREE");

	}

	public void writeProductTreeFile(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {

		createTreeFile(gtnForecastBean, null, "PRODUCT_TREE");

	}

	@SuppressWarnings("unchecked")
	public void writeSalesCustomerGroupedFile(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {

		String treeFilePath = getBasePath(gtnForecastBean) + "CUSTOMER_TREE.ser";
		GtnWsTreeNode rootNode = gtnWsFileService.readDataFromFile(treeFilePath, GtnWsTreeNode.class);

		String masterFilePath = getBasePath(gtnForecastBean) + "COMMERCIAL_SALES_MASTER.json";
		List<List<Object>> masterFileDataList = gtnWsFileService.readJSONDataFromFile(masterFilePath, List.class);

		List<List<Object>> resultSet = new ArrayList<>();

		treeService.loadDataFromMasterFile(masterFileDataList, resultSet, rootNode);

		String actualFilePath = getBasePath(gtnForecastBean) + "COMMERCIAL_SALES_ACTUAL.json";
		List<List<Object>> actualFileDataList = gtnWsFileService.readJSONDataFromFile(actualFilePath, List.class);

		String projectionFilePath = getBasePath(gtnForecastBean) + "COMMERCIAL_SALES_PROJECTION.json";
		List<List<Object>> projectionFileDataList = gtnWsFileService.readJSONDataFromFile(projectionFilePath,
				List.class);

		GtnWsTreeInput gtnWsTreeInput = generateTreeInput(gtnForecastBean);
		GtnWsGroupedDataFileManipulator gtnWsGroupedDataFileManipulator = new GtnWsGroupedDataFileManipulator();
		gtnWsGroupedDataFileManipulator.writeToFile(actualFileDataList, projectionFileDataList, resultSet,
				gtnWsTreeInput, rootNode);

		String customerGroupedFileName = getBasePath(gtnForecastBean) + "COMMERCIAL_SALES_CUSTOMER_GROUPED_DATA.json";
		gtnWsFileService.writeJsonDataToFile(customerGroupedFileName, resultSet);
	}

	@SuppressWarnings("unchecked")
	public void writeSalesProductGroupedFile(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {
		String treeFilePath = getBasePath(gtnForecastBean) + "PRODUCT_TREE.ser";
		GtnWsTreeNode rootNode = gtnWsFileService.readDataFromFile(treeFilePath, GtnWsTreeNode.class);

		String masterFilePath = getBasePath(gtnForecastBean) + "COMMERCIAL_SALES_MASTER.json";
		List<List<Object>> masterFileDataList = gtnWsFileService.readJSONDataFromFile(masterFilePath, List.class);

		List<List<Object>> resultSet = new ArrayList<>();

		treeService.loadDataFromMasterFile(masterFileDataList, resultSet, rootNode);

		String actualFilePath = getBasePath(gtnForecastBean) + "COMMERCIAL_SALES_ACTUAL.json";
		List<List<Object>> actualFileDataList = gtnWsFileService.readJSONDataFromFile(actualFilePath, List.class);

		String projectionFilePath = getBasePath(gtnForecastBean) + "COMMERCIAL_SALES_PROJECTION.json";
		List<List<Object>> projectionFileDataList = gtnWsFileService.readJSONDataFromFile(projectionFilePath,
				List.class);

		GtnWsTreeInput gtnWsTreeInput = generateTreeInput(gtnForecastBean);
		GtnWsGroupedDataFileManipulator gtnWsGroupedDataFileManipulator = new GtnWsGroupedDataFileManipulator();
		gtnWsGroupedDataFileManipulator.writeToFile(actualFileDataList, projectionFileDataList, resultSet,
				gtnWsTreeInput, rootNode);
	}

	private void createBaseFiles(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException, InterruptedException {

		String[] fileList = getBaseFileListFromPropertyFile("COMMERCIAL_SALES_BASE_FILE_LIST", "file",
				CommonConstants.COMMERCIAL_RESOURCES_PATH);

		ExecutorService executorService = Executors.newFixedThreadPool(3);

		String basePath = getBasePath(gtnForecastBean);

		for (String fileName : fileList) {
			GtnWsFileBean gtnWsFileBean = configureGtnWsFileBean(fileName, basePath);
			String queryId = getQueryId(fileName, gtnForecastBean);
			executorService.execute(new GtnWsFileWriterRunnable(gtnWsFileBean, gtnForecastBean, queryId,
					gtnSqlQueryEngine, gtnWsSqlService));
		}

		executorService.shutdown();
		int timeOut = Integer.parseInt(resourceService.resourceFileName("file-name", CommonConstants.RESOURCES_PATH,
				"THREAD_WAIT_TIME_OUT_IN_MINUTES"));
		executorService.awaitTermination(timeOut, TimeUnit.MINUTES);

	}

	private String[] getBaseFileListFromPropertyFile(String propertyKey, String propertyFileName, String filePath) {

		String files = resourceService.resourceFileName(propertyFileName, filePath, propertyKey);
		String[] fileList = files.split(",");
		return fileList;

	}

	private GtnWsFileBean configureGtnWsFileBean(String baseFileName, String basePath) {

		GtnWsFileBean gtnWsFileBean = new GtnWsFileBean();

		gtnWsFileBean.setFileName(baseFileName);
		gtnWsFileBean.setFilePath(basePath);

		gtnWsFileBean.setFileStartIndex(Integer.valueOf(resourceService.getPropertyValue("file",
				CommonConstants.COMMERCIAL_RESOURCES_PATH, baseFileName + ".QUERY_RESULT_START_INDEX")));

		gtnWsFileBean.setGroupingColumnIndex(Integer.valueOf(resourceService.getPropertyValue("file",
				CommonConstants.COMMERCIAL_RESOURCES_PATH, baseFileName + ".QUERY_RESULT_GROUPING_INDEX")));

		return gtnWsFileBean;

	}

	private String getQueryId(String fileName, GtnForecastBean gtnForecastBean) {

		return fileName + "_" + gtnForecastBean.getMode();

	}

	private String getBasePath(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {

		return gtnWsFileService.getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());

	}

	public void createTreeFile(final GtnForecastBean gtnForecastBean, List<List<Object>> masterFileData,
			String fileName) throws GtnFrameworkGeneralException {

		String basePath = gtnWsFileService.getFilePath(gtnForecastBean.getModuleName(), gtnForecastBean.getUserId(),
				gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());

		String finalFileName = basePath + fileName + GtnFrameworkCommonStringConstants.SERIALIZATION_EXTENSION;

		List<String> queryParameters = new ArrayList<>(3);
		queryParameters.add(gtnForecastBean.getQueryParameters().get("hierarchy_no").get(0));
		queryParameters.add(String.valueOf(gtnForecastBean.getRelationshipBuilderSid()));
		queryParameters.add(String.valueOf(gtnForecastBean.getForecastLevel()));
		String queryString = gtnWsSqlService.getQueryWithReplacedValues("GET_RELATIONSHIP_LEVEL", queryParameters);

		List<Object[]> dataList = gtnSqlQueryEngine.executeSelectQuery(queryString, finalFileName);
		Map<String, String> hierarchyNames = getHierarchyNames(gtnForecastBean);
		GtnWsTreeNode rootNode = treeService.buildTree(dataList, hierarchyNames);
		gtnWsFileService.writeDataIntoFile(finalFileName, rootNode);

	}

	@SuppressWarnings("unchecked")
	private Map<String, String> getHierarchyNames(final GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {
		Map<String, String> inputParam = new HashMap<>();

		String sqlQueryString = gtnWsSqlService.getQuery("GET_HIERARCHY_DETAILS").replace("?",
				String.valueOf(gtnForecastBean.getRelationshipBuilderSid()));
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(sqlQueryString);
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

	@SuppressWarnings("deprecation")
	public GtnWsTreeInput generateTreeInput(GtnForecastBean gtnForecastBean) {

		GtnWsTreeInput gtnWsTreeInput = new GtnWsTreeInput();

		Calendar historyStartDate = Calendar.getInstance();
		historyStartDate.setTime(new Date(2014 - 1900, 7, 1));
		gtnWsTreeInput.setActualStartYear(historyStartDate.get(Calendar.YEAR));
		gtnWsTreeInput.setActualStartMonth(historyStartDate.get(Calendar.MONTH));

		Calendar historyEndDate = Calendar.getInstance();
		historyEndDate.setTime(new Date(2014 - 1900, 11, 30));
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

		Map<String, String> resource = new GtnWsReturnsResourceService().loadAllPropertiesInMap("file",
				CommonConstants.COMMERCIAL_RESOURCES_PATH);

		gtnWsTreeInput.setActualDataFileInputColumn(
				Arrays.asList(resource.get("COMMERCIAL_SALES_ACTUAL_COLUMN_INFO").split(",")));
		gtnWsTreeInput.setProjectionDataFileInputColumn(
				Arrays.asList(resource.get("COMMERCIAL_SALES_PROJECTION_COLUMN_INFO").split(",")));
		gtnWsTreeInput.setMasterFileInputColumn(
				Arrays.asList(resource.get("COMMERCIAL_SALES_MASTER_COLUMN_INFO").split(",")));

		return gtnWsTreeInput;
	}

}