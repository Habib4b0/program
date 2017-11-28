package com.stpl.ifs.util.sqlutil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
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

	public static List<Object[]> getResultFromProcedure(String sqlQuery, Object[] paramArray)
			throws GtnFrameworkGeneralException {
		LOGGER.debug("Enter getResultFromProcedure ");

		try {
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext.lookup(DATA_POOL);
			if (datasource != null) {
				return callResultProcedure(sqlQuery, paramArray, datasource);
			}
			LOGGER.debug("Exiting getResultFromProcedure");
			return Collections.emptyList();
		} catch (NamingException namingException) {
			throw new GtnFrameworkGeneralException("Exception getResultFromProcedure method", namingException);
		}
	}

	private static List<Object[]> callResultProcedure(String sqlQuery, Object[] paramArray, DataSource datasource) {
		try (Connection connection = datasource.getConnection();
				CallableStatement statement = connection.prepareCall(sqlQuery);) {
			for (int i = 0; i < paramArray.length; i++) {
                                LOGGER.debug(i + " -- " + paramArray[i]);
				statement.setObject(i+1, paramArray[i]);
			}
			LOGGER.debug("Ending callResultProcedure");
			return convertResultSetToList(statement.executeQuery());
		} catch (Exception ex) {
			LOGGER.error("Exception in callResultProcedure", ex);
		}
		return Collections.emptyList();
	}
        
	public static ResultSet getResultSetFromProcedure(String sqlQuery, Object[] paramArray)
			throws GtnFrameworkGeneralException {
		LOGGER.debug("Enter getResultFromProcedure ");

		try {
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext.lookup(DATA_POOL);
			if (datasource != null) {
				return callResultSetProcedure(sqlQuery, paramArray, datasource);
			}
			LOGGER.debug("Exiting getResultFromProcedure");
			return null;
		} catch (NamingException namingException) {
			LOGGER.error("Exception getResultFromProcedure", namingException);
			throw new GtnFrameworkGeneralException("Exception getResultFromProcedure", namingException);
		}
	}

	private static ResultSet callResultSetProcedure(String sqlQuery, Object[] paramArray, DataSource datasource) {
		try (Connection connection = datasource.getConnection();
				CallableStatement statement = connection.prepareCall(sqlQuery);) {
			for (int i = 0; i < paramArray.length; i++) {
				statement.setObject(i+1, paramArray[i]);
			}
			LOGGER.debug("Ending callResultProcedure");
			return statement.executeQuery();
		} catch (Exception ex) {
			LOGGER.error("Exception in callResultProcedure", ex);
		}
		return null;
	}
        
        private static List<Object[]> convertResultSetToList(ResultSet rs) {
        List<Object[]> objList = new ArrayList<>();

        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            Object[] header = new Object[columnCount];
            for (int i = 1; i <= columnCount; ++i) {
                Object label = rsMetaData.getColumnLabel(i);
                header[i - 1] = label;
            }
            while (rs.next()) {
                Object[] str = new Object[columnCount];
                for (int i = 1; i <= columnCount; ++i) {
                    Object obj = rs.getObject(i);
                    str[i - 1] = obj;
                }
                objList.add(str);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);

        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }
        return objList;
    }
}
