package com.stpl.gtn.gtn2o.ui.framework.component.grid.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.stpl.gtn.gtn2o.ui.framework.component.grid.bean.Row;
public class FetchData {
    private static final Logger logger = LogManager.getLogger(FetchData.class);
	FetchData() {

	}

	public static List<Object[]> fetchResult(String query, Object... params) {
		// System.out.println("Count query" + query);
            logger.info(" fetchResult query" + query);
		for (int i = 0; i < params.length; i++) {
			query=query.replaceFirst("\\?", params[i]+"");
                        logger.info("params [" + i + "]  " + params[i]);
		}
		QueryRunner queryRunner = new QueryRunner();
		ResultSetHandler<List<Object[]>> resultSetHandler = getListResultSetHandler();
		Connection conn = getDbConnection();
		try {
			return queryRunner.query(conn, query, resultSetHandler);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static List<Row> fetchResultAsRow(String query, Object... params) {
//		if (params.length == 1) {
			logger.info(" fetchResultAsRow query" + query);
//		}
		for (int i = 0; i < params.length; i++) {
			query=query.replaceFirst("\\?", params[i]+"");
                         logger.info("params [" + i + "]  " + params[i]);
		}
		QueryRunner queryRunner = new QueryRunner();
		ResultSetHandler<List<Row>> resultSetHandler = getResultSetHandler();
		Connection conn = getDbConnection();
                try {
                List<Row> result=queryRunner.query(conn, query, resultSetHandler);
                logger.info("result size= " + result.size());
		
			return result;
		} catch (Exception e) {
                        logger.info("in fetchResultAsRow Error= " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}


	private static ResultSetHandler<List<Row>> getResultSetHandler() {
		return (ResultSet rs) -> {
                    List<Row> rows = new ArrayList<>();
                    while (rs.next()) {
                        ResultSetMetaData meta = rs.getMetaData();
                        int cols = meta.getColumnCount();
                        Row row = new Row();
                        for (int i = 0; i < cols; i++) {
                            // System.out.println("column" + meta.getColumnName(i + 1));
                            // System.out.println("value" + rs.getObject(i + 1));
                            row.setValue(meta.getColumnName(i + 1), rs.getObject(i + 1));
                        }
                        rows.add(row);
                    }
                    return rows;
                };
	}

	private static ResultSetHandler<List<Object[]>> getListResultSetHandler() {
		return (ResultSet rs) -> {
                    List<Object[]> resultList = new ArrayList<>();
                    while (rs.next()) {
                        ResultSetMetaData meta = rs.getMetaData();
                        int cols = meta.getColumnCount();
                        Object[] result = new Object[cols];
                        
                        for (int i = 0; i < cols; i++) {
                            result[i] = rs.getObject(i + 1);
                        }
                        resultList.add(result);
                    }
                    return resultList;
                };
	}
	public static Connection getDbConnection()  {
            Connection connection=null; 
		String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";

		try {
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
			if (datasource != null) {
				connection = datasource.getConnection();
			} else {
				logger.error("Failed to lookup datasource.");
			}
		} catch (NamingException | SQLException  ex) {
			logger.error("Cannot get connection: " + ex);
		}
		return connection;
	}
//	static Connection getDbConnection() {
//		try {
//			String userName = "bpigtn_db";
//			String password = "^D$b2K5!";
//
//			String url = "jdbc:sqlserver://10.4.48.18:1433;databaseName=BPIGTN_AGN_APPL7_QA";
//
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//			return DriverManager.getConnection(url, userName, password);
//		} catch (Exception e) {
//                    logger.error("in getDbConnection Error= "  ,e);
//			e.printStackTrace();
//			return null;
//		}
//	}

}
