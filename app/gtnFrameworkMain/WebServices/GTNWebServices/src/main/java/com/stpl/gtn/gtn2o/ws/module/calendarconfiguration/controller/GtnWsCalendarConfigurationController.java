/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.calendarconfiguration.controller;

import java.sql.Connection;
import java.util.List;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.calendarconfiguration.constants.GtnWsCalendarConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.calendarconfiguration.logic.GtnWsCalendarConfigurationLogic;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.calendarconfiguration.GtnWsCalendarConfigurationResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 *
 * @author Abhiram.Giri
 */
@RestController
@RequestMapping(value = GtnWsCalendarConfigurationConstants.GTN_CALENDAR_CONFIGURATION_SERVICE)
public class GtnWsCalendarConfigurationController {
	public GtnWsCalendarConfigurationController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCalendarConfigurationController.class);

	private final GtnWsCalendarConfigurationLogic logic = new GtnWsCalendarConfigurationLogic(this);

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public GtnWsCalendarConfigurationLogic getLogic() {
		return logic;
	}

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	public String getQuery(String sqlId) {
		return getGtnWsSqlService().getQuery(sqlId);
	}

	public Object[] createParams(Object... params) {
		return params;
	}

	public GtnFrameworkDataType[] createDataTypes(GtnFrameworkDataType... dataTypes) {
		return dataTypes;
	}

	@RequestMapping(value = GtnWsCalendarConfigurationConstants.GET_CALENDAR_CONFIGURATION_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCalendarConfigurationTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			getLogic().getCalendarConfigurationTableData(gtnWsRequest, gtnResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsCalendarConfigurationConstants.SAVE_CALENDAR_CONFIGURATION, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveCalendarConfiguration(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters saveCalendarConfiguration");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsCalendarConfigurationResponse ccResponse = new GtnWsCalendarConfigurationResponse();
			gtnResponse.setCalendarConfigurationResponse(ccResponse);
			getLogic().saveCalendarConfiguration(gtnWsRequest.getCalendarConfigurationRequest(), ccResponse);
		} catch (Exception ex) {
			logger.error("Exception in saveCalendarConfiguration", ex);
		}

		logger.info("Exit saveCalendarConfiguration");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsCalendarConfigurationConstants.DELETE_CALENDAR_CONFIGURATION, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteCalendarConfiguration(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters deleteCalendarConfiguration");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsCalendarConfigurationResponse ccResponse = new GtnWsCalendarConfigurationResponse();
			gtnResponse.setCalendarConfigurationResponse(ccResponse);
			getLogic().deleteCalendarConfiguration(gtnWsRequest.getCalendarConfigurationRequest(), ccResponse);
		} catch (Exception ex) {
			logger.error("Exception in deleteCalendarConfiguration", ex);
		}

		logger.info("Exit deleteCalendarConfiguration");
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsCalendarConfigurationConstants.LOAD_CALENDAR_CONFIGURATION, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCalendarConfiguration(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enters loadCalendarConfiguration");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsCalendarConfigurationResponse ccResponse = new GtnWsCalendarConfigurationResponse();
			gtnResponse.setCalendarConfigurationResponse(ccResponse);
			getLogic().getCalendarConfigurationHolidays(gtnWsRequest.getCalendarConfigurationRequest(), ccResponse);
		} catch (Exception ex) {
			logger.error("Exception in loadCalendarConfiguration", ex);
		}

		logger.info("Exit loadCalendarConfiguration");
		return gtnResponse;
	}

	public String getSysSchemaCatalog() throws GtnFrameworkGeneralException {
		String catalog = "";
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			catalog = connection.getCatalog();
		} catch (Exception ex) {
			logger.error("Exception in getSysSchemaCatalog", ex);
			throw new GtnFrameworkGeneralException("Exception in getSysSchemaCatalog", ex);
		}
		return catalog;
	}
}
