
package com.stpl.gtn.gtn2o.queryengine.engine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineMainConfig;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkQueryEngineMain {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkQueryEngineMain.class);

	public void executeQuery(Session session, GtnFrameworkQueryEngineMainConfig mainConfig) {

		GtnFrameworkQueryEngineConfig rootConfig = mainConfig.getRootEngineConfig();
		executeQueryConfig(session, rootConfig, mainConfig);

	}

	private void executeQueryConfig(Session session, GtnFrameworkQueryEngineConfig queryEngineConfig,
			GtnFrameworkQueryEngineMainConfig mainConfig) {
		List<GtnFrameworkQueryConfig> childQueries = queryEngineConfig.getQueryConfigList();
		for (GtnFrameworkQueryConfig currentQuery : childQueries) {
			executeQueryIndividual(session, currentQuery, mainConfig);
		}
		if (queryEngineConfig.getChildQueryEngineConfigList() != null) {
			for (GtnFrameworkQueryEngineConfig childEngineConfig : queryEngineConfig.getChildQueryEngineConfigList()) {
				executeQueryConfig(session, childEngineConfig, mainConfig);
			}
		}
	}

	private void executeQueryIndividual(Session session, GtnFrameworkQueryConfig currentQuery,
			GtnFrameworkQueryEngineMainConfig mainConfig) {
		Query sqlQuery = session.createSQLQuery(currentQuery.getQuery());
		String[] dataTypeArray = currentQuery.getDataTypeArray();
		if (dataTypeArray != null) {
			for (int i = 0; i < dataTypeArray.length; i++) {
				this.setQueryParam(sqlQuery, i, currentQuery, mainConfig);
			}
		}
		if (currentQuery.isUpdateOrDeleteQuery()) {
			doUpdateOrDelete(sqlQuery);
		} else {
			if (currentQuery.isInsertOrSelectQuery()) {
				doInsert(session, sqlQuery, currentQuery, mainConfig);
			} else {
				doSelect(sqlQuery, currentQuery, mainConfig);
			}
		}

	}

	private void setQueryParam(Query sqlQuery, int currentQueryIndex, GtnFrameworkQueryConfig currentQuery,
			GtnFrameworkQueryEngineMainConfig mainConfig) {

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

	private void doSelect(Query sqlQuery, GtnFrameworkQueryConfig queryConfig,
			GtnFrameworkQueryEngineMainConfig mainConfig) {
		@SuppressWarnings("unchecked")
		List<Object[]> queryResultSet = (List<Object[]>) sqlQuery.list();
		Object[] firstRow = queryResultSet.get(0);
		int[] resultPositionArray = queryConfig.getResultStoragePositionArray();

		for (int i = 0; i < firstRow.length; i++) {
			int currentResultPosition = resultPositionArray[i];
			mainConfig.getQueryMemoryArray()[currentResultPosition] = firstRow[i];
		}

	}

	private void doInsert(Session session, Query sqlQuery, GtnFrameworkQueryConfig queryConfig,
			GtnFrameworkQueryEngineMainConfig mainConfig) {

		sqlQuery.executeUpdate();
		if (queryConfig.getResultStoragePositionArray() != null) {
			Query query = session.createSQLQuery("SELECT @@IDENTITY");
			Object id = query.uniqueResult();
			int pos = queryConfig.getResultStoragePositionArray()[0];
			mainConfig.getQueryMemoryArray()[pos] = ((BigDecimal) id).intValue();
		}

	}

	private int doUpdateOrDelete(Query sqlQuery) {
		logger.queryLog("Executing query : " + sqlQuery);
		int updateOrDeletedRecordCount = 0;
		updateOrDeletedRecordCount = sqlQuery.executeUpdate();
		return updateOrDeletedRecordCount;

	}
}
