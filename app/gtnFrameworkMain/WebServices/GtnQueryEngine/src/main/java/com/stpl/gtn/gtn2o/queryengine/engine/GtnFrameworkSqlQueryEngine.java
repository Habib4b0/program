package com.stpl.gtn.gtn2o.queryengine.engine;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnQueryLogger;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Abishek.Ram
 */

@Service()
@Scope(value = "singleton")
public class GtnFrameworkSqlQueryEngine {

	@Autowired
	private GtnQueryLogger queryLogger;

	@Autowired
	private SessionFactory sessionFactory;

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkSqlQueryEngine.class);

	public GtnFrameworkSqlQueryEngine() {
		super();
	}

	public GtnFrameworkSqlQueryEngine(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnQueryLogger getQueryLogger() {
		return queryLogger;
	}

	public void setQueryLogger(GtnQueryLogger queryLogger) {
		this.queryLogger = queryLogger;
	}

	public List<?> executeSelectQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		List<?> list = null;
		try (Session session = getSessionFactory().openSession()) {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			Query query = session.createSQLQuery(sqlQuery);
			list = query.list();
			queryLogger.endQueryLog(startTime, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> executeSelectQuery(String sqlQuery, String filename) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		List<Object[]> list = null;
		try (Session session = getSessionFactory().openSession()) {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			Query query = session.createSQLQuery(sqlQuery);
			list = query.list();
			queryLogger.endQueryLog(startTime, sqlQuery);
			createQueryFile(filename, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return list;
	}

	public List<?> executeSelectQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		Session session = getSessionFactory().openSession();
		List<?> queyValuelist = null;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			Query query = generateSQLQuery(session, sqlQuery, params, type);
			queyValuelist = query.list();
			queryLogger.endQueryLog(startTime, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			session.close();
		}
		return queyValuelist;
	}

	public List<?> executeSelectQueryList(String sqlQuery, String queryParameterListids, List<?> queryParameterList)
			throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		Session session = getSessionFactory().openSession();
		List<?> queyValuelist = null;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			Query query = session.createSQLQuery(sqlQuery);
			query.setParameterList(queryParameterListids, queryParameterList);
			queyValuelist = query.list();
			queryLogger.endQueryLog(startTime, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			session.close();
		}
		return queyValuelist;
	}

	public List<?> executeSelectQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type, String fileName)
			throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		Session session = getSessionFactory().openSession();
		List<?> queyValuelist = null;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			Query query = generateSQLQuery(session, sqlQuery, params, type);
			queyValuelist = query.list();
			queryLogger.endQueryLog(startTime, sqlQuery);
			createQueryFile(fileName, query.toString());
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			session.close();
		}
		return queyValuelist;
	}

	@SuppressWarnings("unchecked")
	public Query generateSQLQuery(Session session, String sqlQuery, Object[] params, GtnFrameworkDataType[] type) {
		Query query = session.createSQLQuery(sqlQuery);
		for (int i = 0; i < params.length; i++) {

			switch (type[i]) {
			case STRING:
				query.setString(i, (String) params[i]);
				break;
			case DATE:
				query.setDate(i, (Date) params[i]);
				break;
			case INTEGER:
				query.setInteger(i, (int) params[i]);
				break;
			case LIST:
				query.setParameter(i, params[i]);
				break;
			case DOUBLE:
				query.setDouble(i, (double) params[i]);
				break;
			case NULL_ALLOWED:
				query.setParameter(i, params[i]);
				break;
				
			case IN_LIST:
				query.setParameterList("inParameter", (List<Object>) params[i]);
				break;
			case BIG_DECIMAL:
				query.setBigDecimal(i, (BigDecimal) params[i]);
				break;
			 

			default:
				query.setParameter(i, params[i]);
			}
		}
		return query;
	}

	public int executeInsertOrUpdateQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		Session session = getSessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		int id = 0;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			trx.begin();
			Query query = generateSQLQuery(session, sqlQuery, params, type);
			id = query.executeUpdate();
			trx.commit();
			queryLogger.endQueryLog(startTime, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			trx.rollback();
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			session.close();
		}
		return id;
	}

	public int executeInsertOrUpdateQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		Session session = getSessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		int updateOrDeletedRecordCount = 0;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			trx.begin();
			Query query = session.createSQLQuery(sqlQuery);
			updateOrDeletedRecordCount = query.executeUpdate();
			trx.commit();
			queryLogger.endQueryLog(startTime, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			trx.rollback();
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			session.close();
		}
		return updateOrDeletedRecordCount;
	}

	public List<?> executeSelectQuery(Class<?> modelClass, List<Criterion> restictionCriterionList)
			throws GtnFrameworkGeneralException {
		List<?> dataList = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = generateCriteria(modelClass, restictionCriterionList, session);
			dataList = criteria.list();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_CRITERIA_QUERY,
					ex);
		}
		return dataList;
	}

	public List<?> executeSelectQuery(Class<?> modelClass, List<Criterion> restictionCriterionList, Session session)
			throws GtnFrameworkGeneralException {
		List<?> dataList = null;
		try {
			Criteria criteria = generateCriteria(modelClass, restictionCriterionList, session);
			dataList = criteria.list();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_CRITERIA_QUERY,
					ex);
		}
		return dataList;
	}

	public List<?> executeSelectQuery(Class<?> modelClass, List<Criterion> restictionCriterionList,
			Projection projection) throws GtnFrameworkGeneralException {
		List<?> dataList = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = generateCriteria(modelClass, restictionCriterionList, session);
			criteria.setProjection(projection);
			dataList = criteria.list();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_CRITERIA_QUERY,
					ex);
		}
		return dataList;
	}

	public List<?> executeSelectQuery(Class<?> modelClass, List<Criterion> restictionCriterionList,
			Projection projection, Session session) throws GtnFrameworkGeneralException {
		List<?> dataList = null;
		try {
			Criteria criteria = generateCriteria(modelClass, restictionCriterionList, session);
			criteria.setProjection(projection);
			dataList = criteria.list();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_CRITERIA_QUERY,
					ex);
		}
		return dataList;
	}

	public Criteria generateCriteria(Class<?> modelClass, List<Criterion> restictionCriterionList, Session session) {
		Criteria criteria = session.createCriteria(modelClass);
		if (restictionCriterionList != null) {
			for (Criterion criterion : restictionCriterionList) {
				criteria.add(criterion);
			}
		}
		return criteria;
	}

	public void createQueryFile(String fileName, String queryString) throws GtnFrameworkGeneralException {
		if (fileName.contains(GtnFrameworkCommonStringConstants.SERIALIZATION_EXTENSION)) {
			fileName = fileName.replace(GtnFrameworkCommonStringConstants.SERIALIZATION_EXTENSION,
					GtnFrameworkCommonStringConstants.QUERY
							+ GtnFrameworkCommonStringConstants.SERIALIZATION_EXTENSION);
		}
		fileName = fileName.replace(GtnFrameworkCommonStringConstants.STPL_EXTENSION,
				GtnFrameworkCommonStringConstants.QUERY + GtnFrameworkCommonStringConstants.STPL_EXTENSION);
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(queryString);
			objectOutputStream.flush();
		} catch (IOException e) {
			throw new GtnFrameworkGeneralException(e);
		}
	}

	public int executeInsertAndUpdateHqlQuery(String hqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + hqlQuery);
		int updatedRecordCount = 0;
		try (Session session = getSessionFactory().openSession()) {
			long startTime = queryLogger.startQueryLog(hqlQuery);
			Query query = session.createQuery(hqlQuery);
			updatedRecordCount = query.executeUpdate();
			queryLogger.endQueryLog(startTime, hqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + hqlQuery,
					ex);
		}
		return updatedRecordCount;
	}

	public int executeInsertOrUpdateQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type,
			Session session) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		int id = 0;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			Query query = generateSQLQuery(session, sqlQuery, params, type);
			id = query.executeUpdate();
			queryLogger.endQueryLog(startTime, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return id;
	}

	public List<?> executeSelectQuery(String sqlQuery, List<Object> paramList) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		Session session = getSessionFactory().openSession();
		List<?> queryValueList = null;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			Query query = session.createSQLQuery(sqlQuery);
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i, paramList.get(i));
			}
			queryValueList = query.list();
			queryLogger.endQueryLog(startTime, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			session.close();
		}
		return queryValueList;
	}

	public List executeSelectQuery(String sqlQuery, Session session) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		List<?> queryValueList = null;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			Query query = session.createSQLQuery(sqlQuery);
			queryValueList = query.list();
			queryLogger.endQueryLog(startTime, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return queryValueList;
	}

	public int executeCountQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		int count = 0;
		try (Session session = sessionFactory.openSession()) {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			Query query = session.createSQLQuery(sqlQuery);
			List<?> queryValueList = query.list();
			if (queryValueList != null && !queryValueList.isEmpty()) {
				count = (Integer) queryValueList.get(0);
			}
			queryLogger.endQueryLog(startTime, sqlQuery);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return count;
	}

}
