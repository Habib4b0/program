package com.stpl.ifs.util.sqlutil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

public class GtnSqlUtil {
	private GtnSqlUtil() {

	}

	private static final Logger LOGGER = Logger.getLogger(GtnSqlUtil.class);
	public static final String DATA_POOL = "java:jboss/datasources/jdbc/appDataPool";

	public static boolean procedureCallService(String sqlQuery, Object[] paramArray) {
		LOGGER.debug("Enter procedureCallService ");

		try {
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext.lookup(DATA_POOL);
			if (datasource != null) {
				return callProcedure(sqlQuery, paramArray, datasource);
			}

		} catch (NamingException namingException) {
			LOGGER.error("Exception procedureCallService", namingException);
		}

		LOGGER.debug("Exiting procedureCallService");
		return false;
	}

	private static boolean callProcedure(String sqlQuery, Object[] paramArray, DataSource datasource) {
		try (Connection connection = datasource.getConnection();
				CallableStatement statement = connection.prepareCall(sqlQuery);) {
			for (int i = 0; i < paramArray.length; i++) {
				statement.setObject(i, paramArray[i]);
			}
			statement.execute();
			LOGGER.debug("Ending callProcedure");
			return true;

		} catch (Exception ex) {
			LOGGER.error("Exception in procedure call", ex);
		}
		return false;
	}

	public static ResultSet getResultFromProcedure(String sqlQuery, Object[] paramArray) throws Exception {
		LOGGER.debug("Enter getResultFromProcedure ");

		try {
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext.lookup(DATA_POOL);
			if (datasource != null) {
				return callResultProcedure(sqlQuery, paramArray, datasource);
			}
			LOGGER.debug("Exiting getResultFromProcedure");
			return null;
		} catch (NamingException namingException) {
			LOGGER.error("Exception getResultFromProcedure", namingException);
			throw new Exception("Exception getResultFromProcedure", namingException);
		}
	}

	private static ResultSet callResultProcedure(String sqlQuery, Object[] paramArray, DataSource datasource) {
		try (Connection connection = datasource.getConnection();
				CallableStatement statement = connection.prepareCall(sqlQuery);) {
			for (int i = 0; i < paramArray.length; i++) {
				statement.setObject(i + 1, paramArray[i]);
			}
			LOGGER.debug("Ending callResultProcedure");
			return statement.executeQuery();
		} catch (Exception ex) {
			LOGGER.error("Exception in callResultProcedure", ex);
		}
		return null;
	}
}
