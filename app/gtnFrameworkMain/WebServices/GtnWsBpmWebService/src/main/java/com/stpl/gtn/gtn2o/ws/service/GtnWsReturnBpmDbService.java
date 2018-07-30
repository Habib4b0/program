/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.service;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Satheesh.Nagendran
 */
public class GtnWsReturnBpmDbService {
 	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsReturnsDatabaseService.class);
	@Autowired
	private SessionFactory bpmSessionFactory;
        @Autowired
        private GtnWsReturnsDatabaseService gtnWsReturnsDatabaseService;
        
	@SuppressWarnings("rawtypes")
	public List bpmexecuteQuery(String sqlQuery, Object[] params) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY_IN_GTN_WS_RETURNS_DATABASE_SE + sqlQuery);
		Session bpmSession = bpmSessionFactory.openSession();
		List list = null;
		try {
			Query query = gtnWsReturnsDatabaseService.generateSQLQuery(bpmSession, sqlQuery, params);
			list = query.list();
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			bpmSession.close();
		}
		return list;
	}
}
