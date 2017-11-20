package com.stpl.gtn.gtn2o.ws.module.returnsforecasting.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineMainConfig;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkQueryEngineMain;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 *
 * @author Stpl
 */

@RestController

public class GtnWsProjectionDeleteController {
    public GtnWsProjectionDeleteController(){
        /**
         * empty constructor
         */
    }

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProjectionDeleteController.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkQueryEngineMain gtnFrameworkQueryEngineMain;

	@PostMapping(value = "/gtnForecastReturns/deleteService")
	public void saveProjectionMaster(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnFrameworkQueryEngineMainConfig mainConfig = new GtnFrameworkQueryEngineMainConfig();
		mainConfig = buildQueryConfigForDeleteProjection(mainConfig, gtnWsRequest);
		executeQuery(mainConfig);

	}

	@PostMapping(value = "/viewDeleteService")
	public void saveProjectionViewMaster(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnFrameworkQueryEngineMainConfig mainConfig = new GtnFrameworkQueryEngineMainConfig();
		mainConfig = buildQueryConfigForDeleteProjectionView(mainConfig, gtnWsRequest);
		executeQuery(mainConfig);

	}

	private GtnFrameworkQueryEngineMainConfig buildQueryConfigForDeleteProjectionView(
			GtnFrameworkQueryEngineMainConfig mainConfig, GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnFrameworkQueryEngineConfig projMasterConfig = new GtnFrameworkQueryEngineConfig();
		projMasterConfig = getProjectionViewDeleteQueryConfig(projMasterConfig, gtnWsRequest);
		mainConfig.setRootEngineConfig(projMasterConfig);
		return mainConfig;
	}

	private GtnFrameworkQueryEngineConfig getProjectionViewDeleteQueryConfig(
			GtnFrameworkQueryEngineConfig projMasterConfig, GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		String projectionMasterSid = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean()
				.getProjectionMasterSid();
		List<GtnFrameworkQueryConfig> pmqueries = new ArrayList<>();

		pmqueries.addAll(getChildTableDeleteViewQuery(projectionMasterSid));

		projMasterConfig.setQueryConfigList(pmqueries);

		return projMasterConfig;
	}

	private Collection<? extends GtnFrameworkQueryConfig> getChildTableDeleteViewQuery(String projectionMasterSid) {
		logger.info("Enter getChildTableDeleteQuery");
		List<GtnFrameworkQueryConfig> childDeleteQueries = new ArrayList<>();
		String productHierarchyViewDeleteQuery = getProductHierarchyDeleteQuery(projectionMasterSid);
		String projectionViewMasterDeleteQuery = getProjectionViewMasterDeleteQuery(projectionMasterSid);
		String projectionMasterDeleteQuery = getProjectionMasterDeleteQuery(projectionMasterSid);

		GtnFrameworkQueryConfig productHierarchyViewDeleteQueryConfig = new GtnFrameworkQueryConfig();
		productHierarchyViewDeleteQueryConfig.setQuery(productHierarchyViewDeleteQuery);
		productHierarchyViewDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		productHierarchyViewDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig projectionViewMasterDeleteQueryConfig = new GtnFrameworkQueryConfig();
		projectionViewMasterDeleteQueryConfig.setQuery(projectionViewMasterDeleteQuery);
		projectionViewMasterDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		projectionViewMasterDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig projectionMasterDeleteQueryConfig = new GtnFrameworkQueryConfig();
		projectionMasterDeleteQueryConfig.setQuery(projectionMasterDeleteQuery);
		projectionMasterDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		projectionMasterDeleteQueryConfig.setDataTypeArray(null);

		childDeleteQueries.add(productHierarchyViewDeleteQueryConfig);
		childDeleteQueries.add(projectionViewMasterDeleteQueryConfig);
		childDeleteQueries.add(projectionMasterDeleteQueryConfig);
		logger.info("Exit getChildTableDeleteQuery");
		return childDeleteQueries;
	}

	private String getProjectionViewMasterDeleteQuery(String projectionMasterSid) {

		return "DELETE  FROM FORECASTING_VIEW_MASTER WHERE PROJECTION_ID=" + projectionMasterSid;
	}

	private GtnFrameworkQueryEngineMainConfig buildQueryConfigForDeleteProjection(
			GtnFrameworkQueryEngineMainConfig mainConfig, GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnFrameworkQueryEngineConfig projMasterConfig = new GtnFrameworkQueryEngineConfig();
		projMasterConfig = getProjectionDeleteQueryConfig(projMasterConfig, gtnWsRequest);
		mainConfig.setRootEngineConfig(projMasterConfig);
		return mainConfig;
	}

	@SuppressWarnings("unchecked")
	private GtnFrameworkQueryEngineConfig getProjectionDeleteQueryConfig(GtnFrameworkQueryEngineConfig projMasterConfig,
			GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		Map<String, Object> inputMap = (Map<String, Object>) gtnWsRequest.getGtnWsGeneralRequest()
				.getComboBoxWhereclauseParamList().get(0);
		String projectionMasterSid = String.valueOf(inputMap.get("sysId"));
		List<GtnFrameworkQueryConfig> pmqueries = new ArrayList<>();

		pmqueries.addAll(getChildTableDeleteQuery(projectionMasterSid));

		projMasterConfig.setQueryConfigList(pmqueries);

		return projMasterConfig;
	}

	private Collection<? extends GtnFrameworkQueryConfig> getChildTableDeleteQuery(String projectionMasterSid) {
		logger.info("Enter getChildTableDeleteQuery");
		List<GtnFrameworkQueryConfig> childDeleteQueries = new ArrayList<>();
		String productHierarchyDeleteQuery = getProductHierarchyDeleteQuery(projectionMasterSid);
		String returnsProjSelectionDeleteQuery = getProjectionSelectionDeleteQuery(projectionMasterSid);
		String returnsProjMasterDeleteQuery = getReturnsProjMasterDeleteQuery(projectionMasterSid);
		String returnsProjDetailsDeleteQuery = getReturnsProjDetailsDeleteQuery(projectionMasterSid);
		String actualsDeleteQuery = getReturnsActualsDeleteQuery(projectionMasterSid);
		String returnDetailsDeleteQuery = getReturnsDetailsDeleteQuery(projectionMasterSid);
		String projectionMasterDeleteQuery = getProjectionMasterDeleteQuery(projectionMasterSid);

		GtnFrameworkQueryConfig productHierarchyDeleteQueryConfig = new GtnFrameworkQueryConfig();
		productHierarchyDeleteQueryConfig.setQuery(productHierarchyDeleteQuery);
		productHierarchyDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		productHierarchyDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig returnsProjSelectionDeleteQueryConfig = new GtnFrameworkQueryConfig();
		returnsProjSelectionDeleteQueryConfig.setQuery(returnsProjSelectionDeleteQuery);
		returnsProjSelectionDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		returnsProjSelectionDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig returnsProjMasterDeleteQueryConfig = new GtnFrameworkQueryConfig();
		returnsProjMasterDeleteQueryConfig.setQuery(returnsProjMasterDeleteQuery);
		returnsProjMasterDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		returnsProjMasterDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig returnsProjDetailsDeleteQueryConfig = new GtnFrameworkQueryConfig();
		returnsProjDetailsDeleteQueryConfig.setQuery(returnsProjDetailsDeleteQuery);
		returnsProjDetailsDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		returnsProjDetailsDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig actualsDeleteQueryConfig = new GtnFrameworkQueryConfig();
		actualsDeleteQueryConfig.setQuery(actualsDeleteQuery);
		actualsDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		actualsDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig projectionMasterDeleteQueryConfig = new GtnFrameworkQueryConfig();
		projectionMasterDeleteQueryConfig.setQuery(projectionMasterDeleteQuery);
		projectionMasterDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		projectionMasterDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig returnDetailsDeleteQueryConfig = new GtnFrameworkQueryConfig();
		returnDetailsDeleteQueryConfig.setQuery(returnDetailsDeleteQuery);
		returnDetailsDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		returnDetailsDeleteQueryConfig.setDataTypeArray(null);

		childDeleteQueries.add(productHierarchyDeleteQueryConfig);
		childDeleteQueries.add(returnsProjSelectionDeleteQueryConfig);
		childDeleteQueries.add(returnsProjMasterDeleteQueryConfig);
		childDeleteQueries.add(returnsProjDetailsDeleteQueryConfig);
		childDeleteQueries.add(actualsDeleteQueryConfig);
		childDeleteQueries.add(returnDetailsDeleteQueryConfig);
		childDeleteQueries.add(projectionMasterDeleteQueryConfig);

		logger.info("Exit getChildTableDeleteQuery");
		return childDeleteQueries;
	}

	private String getProductHierarchyDeleteQuery(String projectionMasterSid) {
		return "DELETE  FROM PROJECTION_PROD_HIERARCHY WHERE PROJECTION_MASTER_SID=" + projectionMasterSid;
	}

	private String getProjectionMasterDeleteQuery(String projectionMasterSid) {
		return "DELETE  FROM PROJECTION_MASTER WHERE PROJECTION_MASTER_SID=" + projectionMasterSid;
	}

	private String getReturnsActualsDeleteQuery(String projectionMasterSid) {

		return "DELETE RM FROM RETURNS_ACTUALS RM JOIN RETURNS_DETAILS RD ON RD.RETURNS_DETAILS_SID = RM.RETURNS_DETAILS_SID WHERE RD.PROJECTION_MASTER_SID = "
				+ projectionMasterSid;
	}

	private String getReturnsProjDetailsDeleteQuery(String projectionMasterSid) {

		return "DELETE RM FROM RETURNS_PROJ_DETAILS RM	JOIN RETURNS_DETAILS RD ON RD.RETURNS_DETAILS_SID = RM.RETURNS_DETAILS_SID WHERE RD.PROJECTION_MASTER_SID = "
				+ projectionMasterSid;
	}

	private String getReturnsProjMasterDeleteQuery(String projectionMasterSid) {

		return "DELETE RM FROM RETURNS_PROJ_MASTER RM JOIN RETURNS_DETAILS RD ON RD.RETURNS_DETAILS_SID = RM.RETURNS_DETAILS_SID WHERE RD.PROJECTION_MASTER_SID = "
				+ projectionMasterSid;
	}

	private String getReturnsDetailsDeleteQuery(String projectionMasterSid) {
		return "DELETE FROM RETURNS_DETAILS WHERE PROJECTION_MASTER_SID =" + projectionMasterSid;
	}

	private String getProjectionSelectionDeleteQuery(String projectionMasterSid) {

		return "DELETE  FROM RETURNS_PROJECTION_SELECTION WHERE PROJECTION_MASTER_SID=" + projectionMasterSid;
	}

	public GtnUIFrameworkWebserviceResponse executeQuery(GtnFrameworkQueryEngineMainConfig mainConfig) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		Session current = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = current.beginTransaction();
			gtnFrameworkQueryEngineMain.executeQuery(current, mainConfig);
			tx.commit();
		} catch (Exception ex) {
			logger.error("Exception in executig query-", ex);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(ex);
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			current.close();
		}
		generalWSResponse.setSucess(true);
		logger.info("Exit saveProjectMaster");
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}
}
