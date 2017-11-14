/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.util.List;

import org.hibernate.SessionFactory;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author STPL
 */
public class GtnWsReturnsDatabaseService {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsReturnsDatabaseService.class);
	private SessionFactory sessionFactory;
	private GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine;
	
	public GtnWsReturnsDatabaseService() {
	super();
	}
	
	

	public GtnWsReturnsDatabaseService(SessionFactory sessionFactory,
			GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine) {
		super();
		this.sessionFactory = sessionFactory;
		this.gtnFrameworkSqlQueryEngine = gtnFrameworkSqlQueryEngine;
	}



	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnFrameworkSqlQueryEngine() {
		return gtnFrameworkSqlQueryEngine;
	}

	public void setGtnFrameworkSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine) {
		this.gtnFrameworkSqlQueryEngine = gtnFrameworkSqlQueryEngine;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog("Executing query in GtnWsReturnsDatabaseService: " + sqlQuery);

		return gtnFrameworkSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	public int executeUpdate(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog("Executing query in GtnWsReturnsDatabaseService: " + sqlQuery);
		return gtnFrameworkSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery);
	}
}
