package com.stpl.gtn.gtn2o.ws.queryengine.engine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.gtn.gtn2o.ws.queryengine.config.GtnFrameworkWsQueryConfig;
import com.stpl.gtn.gtn2o.ws.queryengine.config.GtnFrameworkWsQueryEngineConfig;
import com.stpl.gtn.gtn2o.ws.queryengine.config.GtnFrameworkWsQueryEngineMainConfig;

public class GtnFrameworkWsQueryEngineMain {

	private GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
			.getGTNLogger(GtnFrameworkWsQueryEngineMain.class);

	public void executeQuery(Session session, GtnFrameworkWsQueryEngineMainConfig mainConfig) {

		GtnFrameworkWsQueryEngineConfig rootConfig = mainConfig.getRootEngineConfig();
		executeQueryConfig(session, rootConfig, mainConfig);

	}

	private void executeQueryConfig(Session session, GtnFrameworkWsQueryEngineConfig queryEngineConfig,
			GtnFrameworkWsQueryEngineMainConfig mainConfig) {
		List<GtnFrameworkWsQueryConfig> childQueries = queryEngineConfig.getQueryConfigList();
		for (GtnFrameworkWsQueryConfig currentQuery : childQueries) {
			executeQueryIndividual(session, currentQuery, mainConfig);
		}
		if (queryEngineConfig.getChildQueryEngineConfigList() != null) {
			for (GtnFrameworkWsQueryEngineConfig childEngineConfig : queryEngineConfig
					.getChildQueryEngineConfigList()) {
				executeQueryConfig(session, childEngineConfig, mainConfig);
			}
		}
	}

	private void executeQueryIndividual(Session session, GtnFrameworkWsQueryConfig currentQuery,
			GtnFrameworkWsQueryEngineMainConfig mainConfig) {
		Query sqlQuery = session.createSQLQuery(currentQuery.getQuery());
		String[] dataTypeArray = currentQuery.getDataTypeArray();
		if (dataTypeArray != null) {
			for (int i = 0; i < dataTypeArray.length; i++) {
				this.setQueryParam(sqlQuery, i, currentQuery, mainConfig);
			}
		}
		if (currentQuery.isUpdateOrDeleteQuery()) {
			doUpdateOrDelete(sqlQuery, currentQuery, mainConfig);
		} else {
			if (currentQuery.isInsertOrSelectQuery()) {
				doInsert(session, sqlQuery, currentQuery, mainConfig);
			} else {
				doSelect(sqlQuery, currentQuery, mainConfig);
			}
		}

	}

	private void setQueryParam(Query sqlQuery, int currentQueryIndex, GtnFrameworkWsQueryConfig currentQuery,
			GtnFrameworkWsQueryEngineMainConfig mainConfig) {

		int currentParamPos = currentQuery.getParamPositionArray()[currentQueryIndex];
		Object currentParamObj = mainConfig.getQueryMemoryArray()[currentParamPos];
		if (currentParamObj == null) {
			sqlQuery.setParameter(currentQueryIndex, null);
			return;
		} else {
			String[] dataTypeArray = currentQuery.getDataTypeArray();
			if ("String".equals(dataTypeArray[currentQueryIndex])) {
				sqlQuery.setString(currentQueryIndex, (String) currentParamObj);
				return;
			}
			if ("Date".equals(dataTypeArray[currentQueryIndex])) {
				sqlQuery.setDate(currentQueryIndex, (Date) currentParamObj);
				return;
			}
			if ("Integer".equals(dataTypeArray[currentQueryIndex])) {
				sqlQuery.setInteger(currentQueryIndex, (Integer) currentParamObj);
				return;
			}
			if ("Double".equals(dataTypeArray[currentQueryIndex])) {
				sqlQuery.setDouble(currentQueryIndex, (Double) currentParamObj);
				return;
			}
			if ("byte[]".equals(dataTypeArray[currentQueryIndex])) {
				sqlQuery.setByte(currentQueryIndex, (byte) currentParamObj);
				return;
			}
		}
	}

	private void doSelect(Query sqlQuery, GtnFrameworkWsQueryConfig queryConfig,
			GtnFrameworkWsQueryEngineMainConfig mainConfig) {
		@SuppressWarnings("unchecked")
		List<Object[]> queryResultSet = (List<Object[]>) sqlQuery.list();
		Object[] firstRow = queryResultSet.get(0);
		int[] resultPositionArray = queryConfig.getResultStoragePositionArray();

		for (int i = 0; i < firstRow.length; i++) {
			int currentResultPosition = resultPositionArray[i];
			mainConfig.getQueryMemoryArray()[currentResultPosition] = firstRow[i];
		}

	}

	private void doInsert(Session session, Query sqlQuery, GtnFrameworkWsQueryConfig queryConfig,
			GtnFrameworkWsQueryEngineMainConfig mainConfig) {

		sqlQuery.executeUpdate();
		if (queryConfig.getResultStoragePositionArray() != null) {
			Query query = session.createSQLQuery("SELECT @@IDENTITY");
			Object id = query.uniqueResult();
			int pos = queryConfig.getResultStoragePositionArray()[0];
			mainConfig.getQueryMemoryArray()[pos] = ((BigDecimal) id).intValue();
		}

	}

	private int doUpdateOrDelete(Query sqlQuery, GtnFrameworkWsQueryConfig currentQuery,
			GtnFrameworkWsQueryEngineMainConfig mainConfig) {
		gtnLogger.queryLog("Executing query : " + sqlQuery);
		int updateOrDeletedRecordCount = 0;
		updateOrDeletedRecordCount = sqlQuery.executeUpdate();
		return updateOrDeletedRecordCount;

	}

}
