
package com.stpl.gtn.gtn2o.ws.queryengine.engine;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnQueryLogger;
import com.stpl.gtn.gtn2o.ws.queryengine.constants.GtnWsQueryEngineConstants;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

public class GtnFrameworkWsSqlQueryEngine extends GtnCommonWebServiceImplClass {
	@Autowired
	private GtnQueryLogger queryLogger;

	@Autowired
	private SessionFactory sessionFactory;

	public GtnFrameworkWsSqlQueryEngine() {
		super(GtnFrameworkWsSqlQueryEngine.class);
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

	@SuppressWarnings("unchecked")
	public List<Object[]> executeSelectQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		List<Object[]> list = null;
		try (Session session = getSessionFactory().openSession()) {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.START
					+ new SimpleDateFormat(GtnWsQueryEngineConstants.TIME).format(new Date(startTime)));
			Query query = session.createSQLQuery(sqlQuery);
			list = query.list();
			queryLogger.endQueryLog(startTime, sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.END

					+ new SimpleDateFormat(GtnWsQueryEngineConstants.TIME)
							.format(new Date(System.currentTimeMillis())));
			logger.info(GtnWsQueryEngineConstants.TOTAL + (double) (System.currentTimeMillis() - startTime) / 1000
					+ " secs");
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return list;
	}

	public List<Object[]> executeSelectQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		Session session = getSessionFactory().openSession();
		List<Object[]> queyValuelist = null;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.START
					+ new SimpleDateFormat(GtnWsQueryEngineConstants.TIME).format(new Date(startTime)));
			
			Query query = generateSQLQuery(session, sqlQuery, params, type);
			queyValuelist = query.list();
			queryLogger.endQueryLog(startTime, sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.END + new SimpleDateFormat(GtnWsQueryEngineConstants.TIME)
					.format(new Date(System.currentTimeMillis())));
			logger.info(GtnWsQueryEngineConstants.TOTAL + (double) (System.currentTimeMillis() - startTime) / 1000
					+ "secs");
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
		debugQuery(sqlQuery, params, type);
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
	
	public List<Object[]> executeScalarResults(String sqlquery,Map<String, GtnFrameworkDataType> inputMap ,Object[] params, GtnFrameworkDataType[] type) {
		
		
		Session session = sessionFactory.openSession();

		SQLQuery query=session.createSQLQuery(sqlquery) ;
		
		for(Map.Entry<String, GtnFrameworkDataType> mapEntry : inputMap.entrySet()) {
			GtnFrameworkDataType dataType = mapEntry.getValue();
			switch(dataType) {
			   case STRING:
				   query = query.addScalar(mapEntry.getKey(), new StringType());
				   break;
			   case INTEGER:
				   query = query.addScalar(mapEntry.getKey(), new IntegerType());
				   break;
			   case DATE:
				   query = query.addScalar(mapEntry.getKey(), new DateType());
				   break;
			   default:
				   break;
			}
		}
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
		return query.list();
	}

	private void debugQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type) {

		String query = sqlQuery;
		for (int i = 0; i < params.length; i++) {
			switch (type[i]) {
			case STRING:
				query = query.replace("?", "'" + params[i] + "'");
				break;
			case DATE:
				if (params[i] != "" && params[i] != "null") {
					java.sql.Date sql = new java.sql.Date(((Date) params[i]).getTime());
					query = query.replace("?", "'" + (sql) + "'");
				} else {
					query = "";
				}
				break;
			case INTEGER:
				query = query.replace("?", String.valueOf(params[i]));
				break;
			case DOUBLE:
				query = query.replace("?", String.valueOf(params[i]));
				break;
			case NULL_ALLOWED:
				query = query.replace("?", String.valueOf(params[i]));
				break;

			case IN_LIST:
				query = query.replace("inParameter", String.valueOf(params[i]));
				break;
			case BIG_DECIMAL:
				query = query.replace("?", String.valueOf(params[i]));
				break;

			default:
				query = query.replace("?", String.valueOf(params[i]));
				break;
			}
		}
		logger.debug(query);
	}

	public int executeInsertOrUpdateQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		Session session = getSessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		int id = 0;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.START
					+ new SimpleDateFormat(GtnWsQueryEngineConstants.TIME).format(new Date(startTime)));
			trx.begin();
			Query query = generateSQLQuery(session, sqlQuery, params, type);
			id = query.executeUpdate();
			trx.commit();
			queryLogger.endQueryLog(startTime, sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.END + new SimpleDateFormat(GtnWsQueryEngineConstants.TIME)
					.format(new Date(System.currentTimeMillis())));
			logger.info(GtnWsQueryEngineConstants.TOTAL + (double) (System.currentTimeMillis() - startTime) / 1000
					+ "secs");
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
		Transaction tr = session.beginTransaction();
		int updateOrDeletedRecordCount = 0;
		try {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.START
					+ new SimpleDateFormat(GtnWsQueryEngineConstants.TIME).format(new Date(startTime)));
			tr.begin();
			Query query = session.createSQLQuery(sqlQuery);
			updateOrDeletedRecordCount = query.executeUpdate();
			tr.commit();
			queryLogger.endQueryLog(startTime, sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.END + new SimpleDateFormat(GtnWsQueryEngineConstants.TIME)
					.format(new Date(System.currentTimeMillis())));
			logger.info(GtnWsQueryEngineConstants.TOTAL + (double) (System.currentTimeMillis() - startTime) / 1000
					+ "secs");
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			tr.rollback();
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		} finally {
			session.close();
		}
		return updateOrDeletedRecordCount;
	}

	public int executeCountQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		int count = 0;
		try (Session session = sessionFactory.openSession()) {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.START
					+ new SimpleDateFormat(GtnWsQueryEngineConstants.TIME).format(new Date(startTime)));
			Query query = session.createSQLQuery(sqlQuery);
			List<?> queryValueList = query.list();
			if (queryValueList != null && !queryValueList.isEmpty()) {
				count = (Integer) queryValueList.get(0);
			}
			queryLogger.endQueryLog(startTime, sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.END + new SimpleDateFormat(GtnWsQueryEngineConstants.TIME)
					.format(new Date(System.currentTimeMillis())));
			logger.info(GtnWsQueryEngineConstants.TOTAL + (double) (System.currentTimeMillis() - startTime) / 1000
					+ "secs");
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return count;
	}

	public int executeCountQueryWithParams(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		logger.queryLog(GtnFrameworkWebserviceConstant.EXECUTING_QUERY + sqlQuery);
		int count = 0;
		try (Session session = sessionFactory.openSession()) {
			long startTime = queryLogger.startQueryLog(sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.START
					+ new SimpleDateFormat(GtnWsQueryEngineConstants.TIME).format(new Date(startTime)));
			Query query = generateSQLQuery(session, sqlQuery, params, type);
			List<?> queryValueList = query.list();
			if (queryValueList != null && !queryValueList.isEmpty()) {
				count = (Integer) queryValueList.get(0);
			}
			queryLogger.endQueryLog(startTime, sqlQuery);
			logger.debug(GtnWsQueryEngineConstants.END + new SimpleDateFormat(GtnWsQueryEngineConstants.TIME)
					.format(new Date(System.currentTimeMillis())));
			logger.info(GtnWsQueryEngineConstants.TOTAL + (double) (System.currentTimeMillis() - startTime) / 1000
					+ "secs");
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY + sqlQuery,
					ex);
		}
		return count;
	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		return null;
	}
	  @Override
	    public void initCallOnFailure() {
	        // Default Method
	}
}


   
