package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

/**
 *
 * @author Stpl
 */
@Service
public class GtnWsReturnsEditIOService {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsReturnsEditIOService.class);

	@Autowired
	GtnWsReturnsDatabaseService databaseService;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnWsReturnsFileIOService gtnWsReturnsFileIOService;

	@Autowired
	GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine;

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}

	public GtnWsReturnsFileIOService getGtnWsReturnsFileIOService() {
		return gtnWsReturnsFileIOService;
	}

	public void setGtnWsReturnsFileIOService(GtnWsReturnsFileIOService gtnWsReturnsFileIOService) {
		this.gtnWsReturnsFileIOService = gtnWsReturnsFileIOService;
	}

	public GtnFrameworkSqlQueryEngine getGtnFrameworkSqlQueryEngine() {
		return gtnFrameworkSqlQueryEngine;
	}

	public void setGtnFrameworkSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine) {
		this.gtnFrameworkSqlQueryEngine = gtnFrameworkSqlQueryEngine;
	}

	public GtnUIFrameworkWebserviceResponse configureResponseForProjectionView(String projectionMasterSid) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		try {
			gtnForecastBean = getProjectionHierarchyInfo(projectionMasterSid);

			forecastResponse.setGtnForecastBean(gtnForecastBean);
			generalWSResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException exception) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_GETTING_DATA, exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		gtnResponse.setGtnWsForecastResponse(forecastResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnForecastBean getProjectionHierarchyInfo(String projectionMasterSid) throws GtnFrameworkGeneralException {
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		Map<String, List<String>> selectedHierarchy = new HashMap<>();
		String hierarchyNbrQuery = gtnWsSqlService.getQuery("HIERARCHY_NO_DETAILS");
		List<Object[]> hierarchyNbrResultList = (List<Object[]>) gtnFrameworkSqlQueryEngine.executeSelectQuery(
				hierarchyNbrQuery, new Object[] { projectionMasterSid },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });

		int listSize = hierarchyNbrResultList.size();
		String levelNo = "";
		String lastLevelNo = "";
		List<String> rightTableHierarchyList = new ArrayList<>();
		if (listSize != 0) {
			for (int i = 0; i < listSize; i++) {
				levelNo = String.valueOf(hierarchyNbrResultList.get(i)[1]);
				if (lastLevelNo != "" && !lastLevelNo.equals(levelNo)) {
					selectedHierarchy.put(lastLevelNo, rightTableHierarchyList);
					rightTableHierarchyList = new ArrayList<>();
				}
				rightTableHierarchyList.add(String.valueOf(hierarchyNbrResultList.get(i)[0]));
				lastLevelNo = levelNo;
			}

			selectedHierarchy.put(lastLevelNo, rightTableHierarchyList);
		}

		gtnForecastBean.setRightTableHierarchy(selectedHierarchy);
		return gtnForecastBean;
	}

	public GtnUIFrameworkWebserviceResponse configureResponseForProjectionSelection(String projectionMasterSid) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		try {
			gtnForecastBean = getProjectionSelectionInfo(projectionMasterSid);

			forecastResponse.setGtnForecastBean(gtnForecastBean);
			generalWSResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException exception) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_GETTING_DATA, exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		gtnResponse.setGtnWsForecastResponse(forecastResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	private GtnForecastBean getProjectionSelectionInfo(String projectionMasterSid) throws GtnFrameworkGeneralException {
		logger.info("Enter getProjectionSelectionInfo");

		GtnForecastBean projectionSelectionBeanWithResult = null;
		String frequencyQuery = gtnWsSqlService.getQuery("FREQUENCY_DETAILS");
		List<Object> projectionSelectionResultList = (List<Object>) gtnFrameworkSqlQueryEngine.executeSelectQuery(
				frequencyQuery, new Object[] { projectionMasterSid },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });

		String psSelectQuery = gtnWsSqlService.getQuery("RETURNS_PROJECTION_SELECTION_DETAILS");
		List<Object> psSelectQueryResultList = (List<Object>) gtnFrameworkSqlQueryEngine.executeSelectQuery(
				psSelectQuery, new Object[] { projectionMasterSid },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });

		String hierarchyNbrQuery = gtnWsSqlService.getQuery("HIERARCHY_NO_DETAILS");
		List<Object[]> hierarchyNbrResultList = (List<Object[]>) gtnFrameworkSqlQueryEngine.executeSelectQuery(
				hierarchyNbrQuery, new Object[] { projectionMasterSid },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });

		projectionSelectionResultList.addAll(psSelectQueryResultList);
		projectionSelectionBeanWithResult = setForecastBean(projectionSelectionResultList, hierarchyNbrResultList);

		logger.info("Exit getProjectionSelectionInfo");
		return projectionSelectionBeanWithResult;

	}

	private GtnForecastBean setForecastBean(List<Object> projectionSelectionResultList,
			List<Object[]> hierarchyNbrResultList) {
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		Map<String, List<String>> selectedHierarchy = new HashMap<>();
		if (projectionSelectionResultList != null) {
			gtnForecastBean.setFrequency(String.valueOf(projectionSelectionResultList.get(0)));
			gtnForecastBean.setHistory(String.valueOf(projectionSelectionResultList.get(1)));
			gtnForecastBean.setForecastType(String.valueOf(projectionSelectionResultList.get(2)));
			gtnForecastBean.setProjectionPeriodOrder(String.valueOf(projectionSelectionResultList.get(3)));

		}
		int listSize = hierarchyNbrResultList.size();
		StringBuilder hierarchyNo = new StringBuilder();
		String levelNo = "";
		String lastLevelNo = "";
		List<String> rightTableHierarchyList = new ArrayList<>();
		for (int i = 0; i < listSize; i++) {
			levelNo = String.valueOf(hierarchyNbrResultList.get(i)[1]);
			if (lastLevelNo != "" && !lastLevelNo.equals(levelNo)) {
				selectedHierarchy.put(lastLevelNo, rightTableHierarchyList);
				rightTableHierarchyList = new ArrayList<>();
			}
			hierarchyNo.append("('" + hierarchyNbrResultList.get(i)[0] + "')");
			rightTableHierarchyList.add(String.valueOf(hierarchyNbrResultList.get(i)[0]));
			if (i != listSize - 1) {
				hierarchyNo.append(",");
			}

			lastLevelNo = levelNo;
		}
		selectedHierarchy.put(lastLevelNo, rightTableHierarchyList);
		gtnForecastBean.setSelectedHierarchyNo(hierarchyNo.toString());
		gtnForecastBean.setRightTableHierarchy(selectedHierarchy);
		return gtnForecastBean;
	}

	@SuppressWarnings("unchecked")
	public List<String> getExistingViewName() throws GtnFrameworkGeneralException {
		List<String> viewNameResultList;

		String viewNameQuery = gtnWsSqlService.getQuery("EXISTING_VIEW_NAME");

		viewNameResultList = (List<String>) gtnFrameworkSqlQueryEngine.executeSelectQuery(viewNameQuery);

		return viewNameResultList;
	}

	public GtnUIFrameworkWebserviceResponse configureResponseForExistingViewName() {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		GtnWsForecastResponse forecastResponse = new GtnWsForecastResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		try {
			gtnForecastBean.setExistingViewName(getExistingViewName());
			forecastResponse.setGtnForecastBean(gtnForecastBean);
			generalWSResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException exception) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_GETTING_DATA, exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		gtnResponse.setGtnWsForecastResponse(forecastResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public boolean getCreatedFiles(GtnForecastBean gtnForecastBean, boolean isDataSelectionReload)
			throws GtnFrameworkGeneralException {
		boolean createdFileFlag;
		try {
			gtnWsReturnsFileIOService.createFile(gtnForecastBean);
			String basePath = gtnWsReturnsFileIOService.getFilePath(
					gtnWsReturnsFileIOService.getModuleName(gtnForecastBean), gtnForecastBean.getUserId(),
					gtnForecastBean.getForecastSessionId(), gtnForecastBean.getTestFilePath());
			List<List<Object>> masterFileData = gtnWsReturnsFileIOService.readJSONDataFromFile(
					basePath + "RETURNS_FORECAST_MASTER" + GtnFrameworkCommonStringConstants.STPL_EXTENSION,
					List.class);

			gtnWsReturnsFileIOService.createTreeFile(gtnForecastBean, masterFileData,
					isDataSelectionReload ? "RETURNS_FORECAST_MASTER_DS_RELOAD" : "RETURNS_FORECAST_MASTER");
			gtnWsReturnsFileIOService.createGroupedDataFile(gtnForecastBean, masterFileData);
			createdFileFlag = true;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new GtnFrameworkGeneralException("Error in  getCreatedFiles: " + ex);
		}
		return createdFileFlag;
	}

}
