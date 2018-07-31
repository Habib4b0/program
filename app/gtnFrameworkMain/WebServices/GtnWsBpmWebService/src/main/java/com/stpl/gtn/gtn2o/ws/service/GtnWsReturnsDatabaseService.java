/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.projectionmaster.ProjectionMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import org.springframework.stereotype.Service;

/**
 *
 * @author STPL
 */
@Service
public class GtnWsReturnsDatabaseService {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsReturnsDatabaseService.class);
	@Autowired
	private SessionFactory sessionFactory;
	public GtnWsReturnsDatabaseService() {
		super();
	}

	public int executeUpdate(String sqlQuery, Object[] params) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY_IN_GTN_WS_RETURNS_DATABASE_SE + sqlQuery);
		Session session = sessionFactory.openSession();
		int count = 0;
		try {
			Query query = generateSQLQuery(session, sqlQuery, params);
			count = query.executeUpdate();
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			session.close();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public HelperTable getHelperTableByDescription(String description, String listName, Session session) {
		Criteria cr = session.createCriteria(HelperTable.class).add(Restrictions.eq("description", description))
				.add(Restrictions.eq("listName", listName));
		List<HelperTable> results = cr.list();
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ProjectionMaster getProjectionMaster(int projectionId, Session session) {
		Criteria cr = session.createCriteria(ProjectionMaster.class)
				.add(Restrictions.eq("projectionMasterSid", projectionId));
		List<ProjectionMaster> results = cr.list();
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}



	public Query generateSQLQuery(Session session, String sqlQuery, Object[] params) {
		Query query = session.createSQLQuery(sqlQuery);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query;
	}
}
