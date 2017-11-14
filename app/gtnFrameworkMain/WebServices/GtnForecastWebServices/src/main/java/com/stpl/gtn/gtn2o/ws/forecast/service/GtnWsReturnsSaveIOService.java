package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineMainConfig;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkQueryEngineMain;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.CommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

/**
 *
 * @author STPL
 */
@Service
@Scope("singleton")
public class GtnWsReturnsSaveIOService {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsReturnsSaveIOService.class);

	@Autowired
	GtnWsReturnsDatabaseService databaseService;

	@Autowired
	GtnFrameworkQueryEngineMain gtnFrameworkQueryEngineMain;

	@Autowired
	private GtnWsReturnsFileIOService fileService;

	@Autowired
	private GtnWsReturnsResourceService resourceService;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine;

	public GtnWsReturnsDatabaseService getDatabaseService() {
		return databaseService;
	}

	public void setDatabaseService(GtnWsReturnsDatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	public GtnFrameworkQueryEngineMain getGtnFrameworkQueryEngineMain() {
		return gtnFrameworkQueryEngineMain;
	}

	public void setGtnFrameworkQueryEngineMain(GtnFrameworkQueryEngineMain gtnFrameworkQueryEngineMain) {
		this.gtnFrameworkQueryEngineMain = gtnFrameworkQueryEngineMain;
	}

	public GtnWsReturnsFileIOService getFileService() {
		return fileService;
	}

	public void setFileService(GtnWsReturnsFileIOService fileService) {
		this.fileService = fileService;
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

	public GtnFrameworkSqlQueryEngine getGtnFrameworkSqlQueryEngine() {
		return gtnFrameworkSqlQueryEngine;
	}

	public void setGtnFrameworkSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine) {
		this.gtnFrameworkSqlQueryEngine = gtnFrameworkSqlQueryEngine;
	}

	public GtnFrameworkQueryEngineMainConfig buildQueryConfigForView(GtnFrameworkQueryEngineMainConfig mainConfig,
			GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnFrameworkQueryEngineConfig projMasterConfig = new GtnFrameworkQueryEngineConfig();
		projMasterConfig = getProjMasterQueryConfigForView(projMasterConfig, gtnWsRequest);
		mainConfig.setRootEngineConfig(projMasterConfig);

		return mainConfig;
	}

	public GtnFrameworkQueryEngineMainConfig configureDataArrayForView(GtnFrameworkQueryEngineMainConfig mainConfig,
			GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		Object[] saveDataArray;
		List<Object> saveDataList = new ArrayList<>();
		GtnForecastBean pmRequest = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean();
		saveDataList = getProjectionMasterInfoForView(saveDataList, pmRequest);
		saveDataList = getViewMasterInfo(saveDataList, pmRequest);
		saveDataList = getProdHierarchyInfo(saveDataList, pmRequest);
		saveDataArray = saveDataList.toArray();

		mainConfig.setQueryMemoryArray(saveDataArray);
		return mainConfig;
	}

	public GtnFrameworkQueryEngineMainConfig buildQueryConfigForProjection(GtnFrameworkQueryEngineMainConfig mainConfig,
			GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		try {
			GtnFrameworkQueryEngineConfig projMasterConfig = new GtnFrameworkQueryEngineConfig();
			projMasterConfig = getProjMasterQueryConfig(projMasterConfig, gtnWsRequest);
			mainConfig.setRootEngineConfig(projMasterConfig);
		} catch (Exception e) {
			LOGGER.error("Error in Returns Save Query config For Projection", e);
		}
		return mainConfig;
	}

	public GtnFrameworkQueryEngineMainConfig configureDataArray(GtnFrameworkQueryEngineMainConfig mainConfig,
			GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		Object[] saveDataArray;
		List<Object> saveDataList = new ArrayList<>();
		GtnForecastBean pmRequest = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean();
		saveDataList = getProjectionMasterInfo(saveDataList, pmRequest);
		saveDataList = getReturnsProjectionSelection(saveDataList, pmRequest);
		saveDataList = getProdHierarchyInfo(saveDataList, pmRequest);
		try {
			saveDataList = readJsonFileToSaveDB(gtnWsRequest, saveDataList);
		} catch (Exception ex) {
			LOGGER.error("Error while getting data.", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : " + ex);
		}

		saveDataArray = saveDataList.toArray();

		mainConfig.setQueryMemoryArray(saveDataArray);
		return mainConfig;
	}

	private List<Object> getReturnsProjectionSelection(List<Object> saveDataList, GtnForecastBean pmRequest) {

		saveDataList.add(GtnFrameworkWebserviceConstant.SALES_PROJECTION);
		saveDataList.add("Actuals/Projections");
		saveDataList.add(pmRequest.getForecastType());
		saveDataList.add(GtnFrameworkWebserviceConstant.SALES_PROJECTION);
		saveDataList.add("History");
		saveDataList.add(pmRequest.getHistory());
		saveDataList.add(GtnFrameworkWebserviceConstant.SALES_PROJECTION);
		saveDataList.add("Period Order");
		saveDataList.add(pmRequest.getProjectionPeriodOrder());
		saveDataList.add(GtnFrameworkWebserviceConstant.SALES_PROJECTION);
		saveDataList.add("Frequency");
		saveDataList.add(pmRequest.getFrequency());

		return saveDataList;
	}

	@SuppressWarnings("unchecked")
	private List<Object> getProdHierarchyInfo(List<Object> saveDataList, GtnForecastBean pmRequest)
			throws GtnFrameworkGeneralException {
		List<String> hierarchyNbrResultList1 = new ArrayList<>();
		List<String> inputList = pmRequest.getRelationshipSidList();
		String queryParameterListids = "hireachyids";
		String hierarchyNbrQuery = gtnWsSqlService.getQuery("GET_RELATIONSHIP_LEVEL_DETAILS");
		List<Object> hierarchyNbrResultList = (List<Object>) gtnFrameworkSqlQueryEngine
				.executeSelectQueryList(hierarchyNbrQuery, queryParameterListids, inputList);

		for (Object object : hierarchyNbrResultList) {

			saveDataList.add(Integer.valueOf(String.valueOf(object)));
			hierarchyNbrResultList1.add(String.valueOf(object));
		}

		pmRequest.setRelationshipSidList(hierarchyNbrResultList1);
		return saveDataList;
	}

	private List<Object> getProjectionMasterInfo(List<Object> saveDataList, GtnForecastBean pmRequest) {
		Date currentDate = new Date();

		pmRequest.setSaveFlag(1);
		saveDataList.add(nullCheck(pmRequest.getProjectionName()));
		saveDataList.add(nullCheck(pmRequest.getProjectionDescription()));
		saveDataList.add(nullCheck(pmRequest.getModuleName()));
		saveDataList.add(nullCheck(pmRequest.getProjectionStartDate()));
		saveDataList.add(nullCheck(pmRequest.getProjectionEndDate()));
		saveDataList.add(nullCheck(pmRequest.getCompanyMasterSid()));
		saveDataList.add(nullCheck(pmRequest.getProductHierarchySid()));
		saveDataList.add(nullCheck(pmRequest.getProductHierarchyLevel()));
		saveDataList.add(nullCheck(pmRequest.getProductHierarchyVersionNo()));
		saveDataList.add(nullCheck(pmRequest.getItemGroupSid()));
		saveDataList.add(nullCheck(pmRequest.getProductHierarchyInnerLevel()));
		saveDataList.add(nullCheck(pmRequest.getProdRelationshipBuilderSid()));
		saveDataList.add(nullCheck(pmRequest.getSaveFlag()));
		if (pmRequest.isSubmitFlag()) {
			saveDataList.add("Y");
		} else {
			saveDataList.add("");
		}
		saveDataList.add(nullCheck(pmRequest.getCreatedBy()));
		saveDataList.add(currentDate);
		saveDataList.add(nullCheck(pmRequest.getBusinessUnitId()));
		if (pmRequest.isUpdateFlag()) {
			saveDataList.add(Integer.valueOf(pmRequest.getProjectionMasterSid()));
		} else {
			saveDataList.add(nullCheck(pmRequest.getCreatedBy()));
			saveDataList.add(currentDate);
			saveDataList.add(null);
		}

		return saveDataList;
	}

	private GtnFrameworkQueryEngineConfig getProjMasterQueryConfig(GtnFrameworkQueryEngineConfig projMasterConfig,
			GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		try {
			int currentParamPos = 0;
			int projectmasterSidPosition = 0;
			List<Integer> sizeofReturnsRow = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean()
					.getReturnsMasterListSize();

			GtnForecastBean pmRequest = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean();

			if (pmRequest.isUpdateFlag()) {
				projMasterConfig = getProjectionMasterEditQueryConfig(projMasterConfig, pmRequest);
				currentParamPos = 17;
			} else {
				currentParamPos = getProjectionMasterAddQueryConfig(projMasterConfig, currentParamPos);

			}
			projectmasterSidPosition = currentParamPos;
			List<GtnFrameworkQueryEngineConfig> childQueryConfigList = new ArrayList<>();
			projMasterConfig.setChildQueryEngineConfigList(childQueryConfigList);

			for (int i = 0; i < 4; i++) {
				GtnFrameworkQueryEngineConfig projectionSelectionConfig = new GtnFrameworkQueryEngineConfig();
				currentParamPos = getSaveProjectionSelectionConfigQueryConfig(projectionSelectionConfig,
						currentParamPos, projectmasterSidPosition);
				childQueryConfigList.add(projectionSelectionConfig);
			}
			int relationshipSidListSize = pmRequest.getRelationshipSidList().size();

			for (int i = 0; i < relationshipSidListSize; i++) {

				GtnFrameworkQueryEngineConfig productConfig = new GtnFrameworkQueryEngineConfig();
				currentParamPos = getSaveProductHeirarchyQueryConfig(productConfig, currentParamPos,
						projectmasterSidPosition);
				childQueryConfigList.add(productConfig);

			}

			for (int i = 0; i < sizeofReturnsRow.get(2); i++) {
				GtnFrameworkQueryEngineConfig returnMasterConfig = new GtnFrameworkQueryEngineConfig();
				currentParamPos = getSaveReturnDetailsQueryConfig(returnMasterConfig, currentParamPos,
						projectmasterSidPosition);
				childQueryConfigList.add(returnMasterConfig);

			}

			for (int i = 0; i < sizeofReturnsRow.get(0); i++) {

				GtnFrameworkQueryEngineConfig returnMasterConfig = new GtnFrameworkQueryEngineConfig();
				currentParamPos = getSaveReturnActualsQueryConfig(returnMasterConfig, currentParamPos,
						projectmasterSidPosition);
				childQueryConfigList.add(returnMasterConfig);

			}

			for (int i = 0; i < sizeofReturnsRow.get(1); i++) {

				GtnFrameworkQueryEngineConfig returnMasterConfig = new GtnFrameworkQueryEngineConfig();
				currentParamPos = getSaveReturnProjDetailsQueryConfig(returnMasterConfig, currentParamPos,
						projectmasterSidPosition);
				childQueryConfigList.add(returnMasterConfig);

			}
			for (int i = 0; i < sizeofReturnsRow.get(2); i++) {

				GtnFrameworkQueryEngineConfig returnMasterConfig = new GtnFrameworkQueryEngineConfig();
				currentParamPos = getSaveReturnProjMasterQueryConfig(returnMasterConfig, currentParamPos,
						projectmasterSidPosition);
				childQueryConfigList.add(returnMasterConfig);

			}

		} catch (Exception e) {
			LOGGER.error("Error in Return Save Projection Master Query Config", e);
		}
		return projMasterConfig;

	}

	private int getProjectionMasterAddQueryConfig(GtnFrameworkQueryEngineConfig pmInsertQueryEngineConfig,
			int currentParamPos) {
		GtnFrameworkQueryConfig pmInsertQueryConfig = new GtnFrameworkQueryConfig();
		List<GtnFrameworkQueryConfig> pmQueryConfigList = new ArrayList<>();
		String pmInsertQuery = gtnWsSqlService.getQuery("PROJECTION_MASTER_INSERT");
		pmInsertQueryConfig.setQuery(pmInsertQuery);
		pmInsertQueryConfig.setInsertOrSelectQuery(true);
		pmInsertQueryConfig.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING, "Date", "Date",
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.INTEGER, "Date",
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER, "Date" });
		currentParamPos = pmInsertQueryConfig.setParamPositionArray(currentParamPos,
				currentParamPos + pmInsertQueryConfig.getDataTypeArray().length);

		pmInsertQueryConfig.setResultStoragePositionArray(new int[] { currentParamPos });
		pmQueryConfigList.add(pmInsertQueryConfig);
		pmInsertQueryEngineConfig.setQueryConfigList(pmQueryConfigList);
		return currentParamPos;
	}

	private GtnFrameworkQueryEngineConfig getProjectionMasterEditQueryConfig(
			GtnFrameworkQueryEngineConfig projMasterConfig, GtnForecastBean pmRequest)
			throws GtnFrameworkGeneralException {
		LOGGER.info("Enter getProjectionMasterEditQueryConfig");
		GtnFrameworkQueryConfig pmUpdateQueryConfig = new GtnFrameworkQueryConfig();
		String pmUpdateQuery = gtnWsSqlService.getQuery("PROJECTION_MASTER_UPDATE").replace("@PROJECTION_MASTER_SID",
				pmRequest.getProjectionMasterSid());
		pmUpdateQueryConfig.setQuery(pmUpdateQuery);
		pmUpdateQueryConfig.setUpdateOrDeleteQuery(true);
		pmUpdateQueryConfig.setDataTypeArray(
				new String[] { GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
						GtnFrameworkWebserviceConstant.STRING, "Date", "Date", GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
						GtnFrameworkWebserviceConstant.INTEGER, "Date", GtnFrameworkWebserviceConstant.INTEGER });
		pmUpdateQueryConfig.setParamPositionArray(0, 17);
		pmUpdateQueryConfig.setResultStoragePositionArray(new int[] { 17 });

		List<GtnFrameworkQueryConfig> pmqueries = new ArrayList<>();
		pmqueries.add(pmUpdateQueryConfig);

		pmqueries.add(deleteReturnsDetails(pmRequest.getProjectionMasterSid()));
		projMasterConfig.setQueryConfigList(pmqueries);
		LOGGER.info("Exit getProjectionMasterEditQueryConfig");
		return projMasterConfig;
	}

	private GtnFrameworkQueryConfig deleteReturnsDetails(String projectionMasterSid)
			throws GtnFrameworkGeneralException {
		LOGGER.info("Enter getChildTableDeleteQuery");

		String returnsDetailsDeleteQuery = getReturnsDetailsDeleteQuery(projectionMasterSid);

		GtnFrameworkQueryConfig returnsDetailsDeleteQueryConfig = new GtnFrameworkQueryConfig();
		returnsDetailsDeleteQueryConfig.setQuery(returnsDetailsDeleteQuery);
		returnsDetailsDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		returnsDetailsDeleteQueryConfig.setDataTypeArray(null);

		LOGGER.info("Exit getChildTableDeleteQuery");
		return returnsDetailsDeleteQueryConfig;
	}

	private String getProductHierarchyDeleteQuery(String projectionMasterSid) {

		return "DELETE  FROM PROJECTION_PROD_HIERARCHY WHERE PROJECTION_MASTER_SID=" + projectionMasterSid;
	}

	private String getReturnsDetailsDeleteQuery(String projectionMasterSid) {
		String deleteQuery = gtnWsSqlService.getQuery("RETURNS_DELETE_QUERY");
		deleteQuery = deleteQuery.replace("?", projectionMasterSid);
		return deleteQuery;
	}

	private int getSaveProjectionSelectionConfigQueryConfig(GtnFrameworkQueryEngineConfig projectionSelectionConfig,
			int currentParamPos, int projectmasterSidPosition) {
		List<GtnFrameworkQueryConfig> projectionSelectionQueryList = new ArrayList<>();
		projectionSelectionConfig.setQueryConfigList(projectionSelectionQueryList);

		GtnFrameworkQueryConfig projectionSelectionQueryConfig = new GtnFrameworkQueryConfig();
		String returnProjMasterSaveQuery = gtnWsSqlService.getQuery("RETURNS_PROJECTION_SELECTION_INSERT");
		projectionSelectionQueryConfig.setQuery(returnProjMasterSaveQuery);
		projectionSelectionQueryConfig.setInsertOrSelectQuery(true);
		projectionSelectionQueryConfig.setDataTypeArray(
				new String[] { GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
						GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING });

		currentParamPos = projectionSelectionQueryConfig.setParamPositionArrayForChild(
				new int[] { projectmasterSidPosition }, currentParamPos,
				currentParamPos + (projectionSelectionQueryConfig.getDataTypeArray().length) - 1);

		projectionSelectionQueryList.add(projectionSelectionQueryConfig);

		return currentParamPos;
	}

	private int getSaveReturnProjMasterQueryConfig(GtnFrameworkQueryEngineConfig returnProjMasterConfig,
			int currentParamPos, int projectmasterSidPosition) {
		List<GtnFrameworkQueryConfig> returnProjMasterQueryList = new ArrayList<>();
		returnProjMasterConfig.setQueryConfigList(returnProjMasterQueryList);

		GtnFrameworkQueryConfig returnProjMasterQueryConfig = new GtnFrameworkQueryConfig();
		String returnProjMasterSaveQuery = gtnWsSqlService.getQuery("RETURNS_PROJ_MASTER_TEST_INSERT");
		returnProjMasterQueryConfig.setQuery(returnProjMasterSaveQuery);
		returnProjMasterQueryConfig.setInsertOrSelectQuery(true);
		returnProjMasterQueryConfig.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, "Date", "Date" });

		currentParamPos = returnProjMasterQueryConfig.setParamPositionArrayForChild(
				new int[] { projectmasterSidPosition }, currentParamPos,
				currentParamPos + (returnProjMasterQueryConfig.getDataTypeArray().length) - 1);

		returnProjMasterQueryList.add(returnProjMasterQueryConfig);

		return currentParamPos;
	}

	private int getSaveReturnProjDetailsQueryConfig(GtnFrameworkQueryEngineConfig returnProjDetailsConfig,
			int currentParamPos, int projectmasterSidPosition) {
		List<GtnFrameworkQueryConfig> returnProjDetailsQueryList = new ArrayList<>();
		returnProjDetailsConfig.setQueryConfigList(returnProjDetailsQueryList);

		GtnFrameworkQueryConfig returnProjDetailsQueryConfig = new GtnFrameworkQueryConfig();
		String returnProjDetailsSaveQuery = gtnWsSqlService.getQuery("RETURNS_PROJ_DETAILS_TEST_INSERT");
		returnProjDetailsQueryConfig.setQuery(returnProjDetailsSaveQuery);
		returnProjDetailsQueryConfig.setInsertOrSelectQuery(true);
		returnProjDetailsQueryConfig.setDataTypeArray(
				new String[] { GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.A_DOUBLE,
						GtnFrameworkWebserviceConstant.A_DOUBLE, GtnFrameworkWebserviceConstant.A_DOUBLE,
						GtnFrameworkWebserviceConstant.A_DOUBLE, GtnFrameworkWebserviceConstant.A_DOUBLE,
						GtnFrameworkWebserviceConstant.A_DOUBLE, GtnFrameworkWebserviceConstant.A_DOUBLE });

		currentParamPos = returnProjDetailsQueryConfig.setParamPositionArrayForChild(
				new int[] { projectmasterSidPosition }, currentParamPos,
				currentParamPos + (returnProjDetailsQueryConfig.getDataTypeArray().length) - 1);

		returnProjDetailsQueryList.add(returnProjDetailsQueryConfig);

		return currentParamPos;
	}

	private int getSaveReturnActualsQueryConfig(GtnFrameworkQueryEngineConfig returnProjDetailsConfig,
			int currentParamPos, int projectmasterSidPosition) {
		List<GtnFrameworkQueryConfig> returnActualsQueryList = new ArrayList<>();
		returnProjDetailsConfig.setQueryConfigList(returnActualsQueryList);

		GtnFrameworkQueryConfig returnActualsQueryConfig = new GtnFrameworkQueryConfig();
		String returnActualsSaveQuery = gtnWsSqlService.getQuery("RETURNS_ACTUALS_TEST_INSERT");
		returnActualsQueryConfig.setQuery(returnActualsSaveQuery);
		returnActualsQueryConfig.setInsertOrSelectQuery(true);
		returnActualsQueryConfig.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.A_DOUBLE, GtnFrameworkWebserviceConstant.A_DOUBLE,
				GtnFrameworkWebserviceConstant.A_DOUBLE, GtnFrameworkWebserviceConstant.A_DOUBLE,
				GtnFrameworkWebserviceConstant.A_DOUBLE, GtnFrameworkWebserviceConstant.A_DOUBLE,
				GtnFrameworkWebserviceConstant.A_DOUBLE, GtnFrameworkWebserviceConstant.A_DOUBLE });

		currentParamPos = returnActualsQueryConfig.setParamPositionArrayForChild(new int[] { projectmasterSidPosition },
				currentParamPos, currentParamPos + (returnActualsQueryConfig.getDataTypeArray().length) - 1);

		returnActualsQueryList.add(returnActualsQueryConfig);

		return currentParamPos;
	}

	private int getSaveReturnDetailsQueryConfig(GtnFrameworkQueryEngineConfig returnProjDetailsConfig,
			int currentParamPos, int projectmasterSidPosition) {
		List<GtnFrameworkQueryConfig> returnActualsQueryList = new ArrayList<>();
		returnProjDetailsConfig.setQueryConfigList(returnActualsQueryList);

		GtnFrameworkQueryConfig returnActualsQueryConfig = new GtnFrameworkQueryConfig();
		String returnActualsSaveQuery = gtnWsSqlService.getQuery("RETURNS_DETAILS_INSERT");
		returnActualsQueryConfig.setQuery(returnActualsSaveQuery);
		returnActualsQueryConfig.setInsertOrSelectQuery(true);
		returnActualsQueryConfig.setDataTypeArray(
				new String[] { GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER });

		currentParamPos = returnActualsQueryConfig.setParamPositionArrayForChild(new int[] { projectmasterSidPosition },
				currentParamPos, currentParamPos + (returnActualsQueryConfig.getDataTypeArray().length) - 1);

		returnActualsQueryList.add(returnActualsQueryConfig);

		return currentParamPos;
	}

	private int getSaveProductHeirarchyQueryConfig(GtnFrameworkQueryEngineConfig productConfig, int currentParamPos,
			int projectmasterSidPosition) {
		List<GtnFrameworkQueryConfig> productQueryList = new ArrayList<>();
		productConfig.setQueryConfigList(productQueryList);

		GtnFrameworkQueryConfig productQueryConfig = new GtnFrameworkQueryConfig();
		String prodHierarchySaveQuery = gtnWsSqlService.getQuery("PROJECTION_PROD_HIERARCHY_INSERT");
		productQueryConfig.setQuery(prodHierarchySaveQuery);
		productQueryConfig.setInsertOrSelectQuery(true);
		productQueryConfig.setDataTypeArray(
				new String[] { GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER });

		currentParamPos = productQueryConfig.setParamPositionArrayForChild(new int[] { projectmasterSidPosition },
				currentParamPos, currentParamPos + (productQueryConfig.getDataTypeArray().length) - 1);

		productQueryList.add(productQueryConfig);

		return currentParamPos;
	}

	private Object nullCheck(Object input) {
		Object inputParemeter = null;
		if (input != null && !input.equals(0)) {
			inputParemeter = input;
		}

		return inputParemeter;
	}

	@SuppressWarnings("unchecked")
	public List<Object> readJsonFileToSaveDB(GtnUIFrameworkWebserviceRequest gtnWsRequest, List<Object> saveDataList)
			throws GtnFrameworkGeneralException {
		int listIndex = 0;
		List<List<Object>> inputList;
		int sizeOfInputcomingFromFile = 0;
		int formattingstartIndex = 0;
		List<Integer> sizeOfinputFromFileList = new ArrayList<>();
		String basePath = fileService.getFilePath(
				gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean().getModuleName(),
				gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean().getUserId(),
				gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean().getForecastSessionId(),
				gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean().getTestFilePath());
		String[] fileList = fileService.getFileList("RETURNS");

		inputList = fileService.readJSONDataFromFile(basePath + GtnFrameworkWebserviceConstant.RETURNS_FORECAST_MASTER
				+ GtnFrameworkCommonStringConstants.STPL_EXTENSION, List.class);
		for (List<Object> obj : inputList) {
			saveDataList.add(obj.get(1));
		}

		for (String fileName : fileList) {
			listIndex = 0;

			inputList = fileService.readJSONDataFromFile(
					basePath + fileName + GtnFrameworkCommonStringConstants.STPL_EXTENSION, List.class);

			sizeOfInputcomingFromFile = inputList.size();

			int restartStartIndex = Integer.valueOf(resourceService.resourceFileName("file-name",
					CommonConstants.RESOURCES_PATH, fileName + "_RESTART_INDEX"));
			String dateColumnIndex = resourceService.resourceFileName("file-name", CommonConstants.RESOURCES_PATH,
					fileName + "_FILE_COLUMN_DATATYPE_INFO");

			String[] dateColumnList = dateColumnIndex.split(",");

			for (int i = 0; i < sizeOfInputcomingFromFile; i++) {
				formattingstartIndex = -1;
				List<Object> obj = (List<Object>) inputList.get(i);
				if (!GtnFrameworkWebserviceConstant.RETURNS_FORECAST_MASTER.equals(fileName)) {
					for (int j = 0; j < obj.size(); j++) {
						Object value = obj.get(j);

						if (j % restartStartIndex == 0) {
							formattingstartIndex = 0;
							listIndex++;
						} else {
							formattingstartIndex++;
						}
						value = formatingOfColumnValues(value, dateColumnList, formattingstartIndex);
						saveDataList.add(value);

					}

				} else {

					for (int j = 1; j < obj.size(); j++) {
						Object value = obj.get(j);
						if (!"Skip".equals(dateColumnList[j])) {
							value = formatingOfColumnValues(value, dateColumnList, j);
							saveDataList.add(value);
						}

					}

				}

			}
			if (listIndex != 0 && !GtnFrameworkWebserviceConstant.RETURNS_FORECAST_MASTER.equals(fileName)) {
				sizeOfinputFromFileList.add(listIndex);
			} else if (GtnFrameworkWebserviceConstant.RETURNS_FORECAST_MASTER.equals(fileName)) {
				sizeOfinputFromFileList.add(sizeOfInputcomingFromFile);
			}
		}
		gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean().setReturnsMasterListSize(sizeOfinputFromFileList);
		return saveDataList;
	}

	private Object formatingOfColumnValues(Object value, String[] dateColumnList, int index) {

		if (value != null) {
			if ("Date".equals(dateColumnList[index])) {
				value = new Date((long) value);
			}

			if ("Boolean".equals(dateColumnList[index])) {
				if ("false".equals(String.valueOf(value))) {
					value = 0;
				} else if ("true".equals(String.valueOf(value))) {
					value = 1;
				} else {
					value = Integer.valueOf(String.valueOf(value));
				}
			}

			if (GtnFrameworkWebserviceConstant.A_DOUBLE.equals(dateColumnList[index])
					&& "NaN".equals(String.valueOf(value))) {
				value = Double.valueOf(0.00);
			}

		}
		return value;
	}

	private GtnFrameworkQueryEngineConfig getProjMasterQueryConfigForView(
			GtnFrameworkQueryEngineConfig projMasterConfig, GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		int currentParamPos = 0;
		int projectmasterSidPosition = 0;

		GtnForecastBean pmRequest = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean();
		if (pmRequest.isViewMode()) {
			projMasterConfig = getProjectionMasterEditQueryConfigForView(projMasterConfig, pmRequest);
			currentParamPos = 16;
			projectmasterSidPosition = currentParamPos;
		} else {
			currentParamPos = getProjectionMasterAddQueryConfigForView(projMasterConfig, currentParamPos);
			projectmasterSidPosition = currentParamPos;
		}
		List<GtnFrameworkQueryEngineConfig> childQueryConfigList = new ArrayList<>();
		projMasterConfig.setChildQueryEngineConfigList(childQueryConfigList);

		GtnFrameworkQueryEngineConfig viewMasterConfig = new GtnFrameworkQueryEngineConfig();
		if (pmRequest.isViewMode()) {
			currentParamPos = getViewSaveMasterEditQueryConfig(viewMasterConfig, currentParamPos,
					projectmasterSidPosition, pmRequest.getProjectionMasterSid());

		} else {
			currentParamPos = getViewSaveMasterQueryConfig(viewMasterConfig, currentParamPos, projectmasterSidPosition);

		}
		childQueryConfigList.add(viewMasterConfig);
		int relationshipSidListSize = pmRequest.getRelationshipSidList().size();
		if (relationshipSidListSize != 0) {
			for (int i = 0; i < relationshipSidListSize; i++) {

				GtnFrameworkQueryEngineConfig productConfig = new GtnFrameworkQueryEngineConfig();
				currentParamPos = getSaveProductHeirarchyQueryConfig(productConfig, currentParamPos,
						projectmasterSidPosition);
				childQueryConfigList.add(productConfig);

			}
		}
		return projMasterConfig;
	}

	private List<Object> getProjectionMasterInfoForView(List<Object> saveDataList, GtnForecastBean pmRequest) {
		Date currentDate = new Date();

		saveDataList.add(nullCheck(pmRequest.getProjectionName()));
		saveDataList.add(nullCheck(pmRequest.getProjectionDescription()));
		saveDataList.add(nullCheck(pmRequest.getModuleName()));
		saveDataList.add(nullCheck(pmRequest.getProjectionStartDate()));
		saveDataList.add(nullCheck(pmRequest.getProjectionEndDate()));
		saveDataList.add(nullCheck(pmRequest.getCompanyMasterSid()));
		saveDataList.add(nullCheck(pmRequest.getProductHierarchySid()));
		saveDataList.add(nullCheck(pmRequest.getProductHierarchyLevel()));
		saveDataList.add(nullCheck(pmRequest.getProductHierarchyVersionNo()));
		saveDataList.add(nullCheck(pmRequest.getItemGroupSid()));
		saveDataList.add(nullCheck(pmRequest.getProductHierarchyInnerLevel()));
		saveDataList.add(nullCheck(pmRequest.getProdRelationshipBuilderSid()));
		saveDataList.add(0);
		saveDataList.add(nullCheck(pmRequest.getCreatedBy()));
		saveDataList.add(currentDate);
		saveDataList.add(nullCheck(pmRequest.getBusinessUnitId()));
		if (pmRequest.isViewMode()) {
			saveDataList.add(Integer.valueOf(pmRequest.getProjectionMasterSid()));
		} else {
			saveDataList.add(nullCheck(pmRequest.getCreatedBy()));
			saveDataList.add(currentDate);
			saveDataList.add(null);

		}

		return saveDataList;
	}

	private List<Object> getViewMasterInfo(List<Object> saveDataList, GtnForecastBean pmRequest) {
		Date currentDate = new Date();
		saveDataList.add(nullCheck(pmRequest.getViewName()));
		saveDataList.add(nullCheck(pmRequest.getViewType()));
		if (!pmRequest.isViewMode()) {
			saveDataList.add(nullCheck(String.valueOf(pmRequest.getCreatedBy())));
			saveDataList.add(currentDate);
		}
		saveDataList.add(nullCheck(String.valueOf(pmRequest.getCreatedBy())));
		saveDataList.add(currentDate);
		return saveDataList;
	}

	private int getViewSaveMasterEditQueryConfig(GtnFrameworkQueryEngineConfig viewMasterConfig, int currentParamPos,
			int projectmasterSidPosition, String projectionMasterSid) {
		List<GtnFrameworkQueryConfig> viewMasterQueryList = new ArrayList<>();
		viewMasterConfig.setQueryConfigList(viewMasterQueryList);

		GtnFrameworkQueryConfig viewMasterQueryConfig = new GtnFrameworkQueryConfig();
		String viewMasterUpdateQuery = gtnWsSqlService.getQuery("FORECASTING_VIEW_MASTER_UPDATE")
				.replace("@PROJECTION_ID", projectionMasterSid);
		viewMasterQueryConfig.setQuery(viewMasterUpdateQuery);
		viewMasterQueryConfig.setInsertOrSelectQuery(true);
		viewMasterQueryConfig.setDataTypeArray(
				new String[] { GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
						GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING, "Date" });

		currentParamPos = viewMasterQueryConfig.setParamPositionArrayForChild(new int[] { projectmasterSidPosition },
				currentParamPos, currentParamPos + (viewMasterQueryConfig.getDataTypeArray().length) - 1);

		viewMasterQueryList.add(viewMasterQueryConfig);

		return currentParamPos;
	}

	private GtnFrameworkQueryEngineConfig getProjectionMasterEditQueryConfigForView(
			GtnFrameworkQueryEngineConfig projMasterConfig, GtnForecastBean pmRequest) {
		LOGGER.info("Enter getProjectionMasterEditQueryConfig");
		GtnFrameworkQueryConfig pmUpdateQueryConfig = new GtnFrameworkQueryConfig();
		String pmUpdateQuery = gtnWsSqlService.getQuery("PROJECTION_MASTER_FOR_VIEW_UPDATE")
				.replace("@PROJECTION_MASTER_SID", pmRequest.getProjectionMasterSid());
		pmUpdateQueryConfig.setQuery(pmUpdateQuery);
		pmUpdateQueryConfig.setUpdateOrDeleteQuery(true);
		pmUpdateQueryConfig.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING, "Date", "Date",
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, "Date", GtnFrameworkWebserviceConstant.INTEGER });
		pmUpdateQueryConfig.setParamPositionArray(0, 16);

		List<GtnFrameworkQueryConfig> pmqueries = new ArrayList<>();
		pmqueries.add(pmUpdateQueryConfig);
		pmqueries.addAll(getChildTableDeleteQueryForView(pmRequest.getProjectionMasterSid()));
		projMasterConfig.setQueryConfigList(pmqueries);
		LOGGER.info("Exit getProjectionMasterEditQueryConfig");
		return projMasterConfig;
	}

	private Collection<? extends GtnFrameworkQueryConfig> getChildTableDeleteQueryForView(String projectionMasterSid) {
		LOGGER.info("Enter getChildTableDeleteQuery");
		List<GtnFrameworkQueryConfig> childDeleteQueries = new ArrayList<>();
		String productHierarchyDeleteQuery = getProductHierarchyDeleteQuery(projectionMasterSid);
		GtnFrameworkQueryConfig productHierarchyDeleteQueryConfig = new GtnFrameworkQueryConfig();
		productHierarchyDeleteQueryConfig.setQuery(productHierarchyDeleteQuery);
		productHierarchyDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		productHierarchyDeleteQueryConfig.setDataTypeArray(null);
		childDeleteQueries.add(productHierarchyDeleteQueryConfig);
		LOGGER.info("Exit getChildTableDeleteQuery");
		return childDeleteQueries;
	}

	private int getViewSaveMasterQueryConfig(GtnFrameworkQueryEngineConfig viewMasterConfig, int currentParamPos,
			int projectmasterSidPosition) {
		List<GtnFrameworkQueryConfig> viewMasterQueryList = new ArrayList<>();
		viewMasterConfig.setQueryConfigList(viewMasterQueryList);

		GtnFrameworkQueryConfig viewMasterQueryConfig = new GtnFrameworkQueryConfig();
		String viewMasterSaveQuery = gtnWsSqlService.getQuery("FORECASTING_VIEW_MASTER_INSERT");
		viewMasterQueryConfig.setQuery(viewMasterSaveQuery);
		viewMasterQueryConfig.setInsertOrSelectQuery(true);
		viewMasterQueryConfig.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, "Date", GtnFrameworkWebserviceConstant.STRING, "Date" });

		currentParamPos = viewMasterQueryConfig.setParamPositionArrayForChild(new int[] { projectmasterSidPosition },
				currentParamPos, currentParamPos + (viewMasterQueryConfig.getDataTypeArray().length) - 1);

		viewMasterQueryList.add(viewMasterQueryConfig);

		return currentParamPos;
	}

	private int getProjectionMasterAddQueryConfigForView(GtnFrameworkQueryEngineConfig projMasterConfig,
			int currentParamPos) {
		GtnFrameworkQueryConfig pmInsertQueryConfig = new GtnFrameworkQueryConfig();
		List<GtnFrameworkQueryConfig> pmQueryConfigList = new ArrayList<>();
		String pmInsertQuery = gtnWsSqlService.getQuery("PROJECTION_MASTER_FOR_VIEW_INSERT");
		pmInsertQueryConfig.setQuery(pmInsertQuery);
		pmInsertQueryConfig.setInsertOrSelectQuery(true);
		pmInsertQueryConfig.setDataTypeArray(
				new String[] { GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
						GtnFrameworkWebserviceConstant.STRING, "Date", "Date", GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER, "Date",
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER, "Date" });
		currentParamPos = pmInsertQueryConfig.setParamPositionArray(currentParamPos,
				currentParamPos + pmInsertQueryConfig.getDataTypeArray().length);

		pmInsertQueryConfig.setResultStoragePositionArray(new int[] { currentParamPos });
		pmQueryConfigList.add(pmInsertQueryConfig);
		projMasterConfig.setQueryConfigList(pmQueryConfigList);
		return currentParamPos;
	}

	public boolean executeQuery(GtnFrameworkQueryEngineMainConfig mainConfig) throws GtnFrameworkGeneralException {
		boolean saveFlag = false;
		Session current = databaseService.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = current.beginTransaction();
			gtnFrameworkQueryEngineMain.executeQuery(current, mainConfig);
			saveFlag = true;
			tx.commit();
		} catch (Exception ex) {
			LOGGER.error("Error while getting data.", ex);
			saveFlag = false;
			if (tx != null) {
				tx.rollback();
			}
			throw new GtnFrameworkGeneralException("Error in executing query : " + ex);
		} finally {
			current.close();
		}
		LOGGER.info("Exit saveProjectMaster");
		return saveFlag;
	}

	public GtnWsForecastResponse getSavedProjectionMasterSid(
			GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse,
			GtnFrameworkQueryEngineMainConfig mainConfig, GtnWsForecastResponse forecastResponse)
			throws GtnFrameworkGeneralException {
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		GtnFrameworkQueryConfig config = mainConfig.getRootEngineConfig().getQueryConfigList().get(0);
		int projectMasterIndex = config.getResultStoragePositionArray()[0];
		int projectionMasterSid = (int) mainConfig.getQueryMemoryArray()[projectMasterIndex];
		gtnForecastBean.setProjectionMasterSid(String.valueOf(projectionMasterSid));
		forecastResponse.setGtnForecastBean(gtnForecastBean);

		return forecastResponse;
	}

}
