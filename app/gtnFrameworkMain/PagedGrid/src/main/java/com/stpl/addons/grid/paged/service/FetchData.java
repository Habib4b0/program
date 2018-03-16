package com.stpl.addons.grid.paged.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import com.stpl.addons.grid.paged.bean.Row;

public class FetchData {

	FetchData() {

	}

	public static List<Object[]> fetchResult(String query, Object... params) {
		// System.out.println("Count query" + query);
		for (int i = 0; i < params.length; i++) {
			System.out.println("params [" + i + "]  " + params[i]);
		}
		QueryRunner queryRunner = new QueryRunner();
		ResultSetHandler<List<Object[]>> resultSetHandler = getListResultSetHandler();
		Connection conn = getDbConnection();
		try {
			return queryRunner.query(conn, query, resultSetHandler, params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static List<Row> fetchResultAsRow(String query, Object... params) {
//		if (params.length == 1) {
			System.out.println(" query" + query);
//		}
		for (int i = 0; i < params.length; i++) {
			System.out.println("params [" + i + "]  " + params[i]);
		}
		QueryRunner queryRunner = new QueryRunner();
		ResultSetHandler<List<Row>> resultSetHandler = getResultSetHandler();
		Connection conn = getDbConnection();
                try {
                List<Row> result=queryRunner.query(conn, query, resultSetHandler, params);
                System.out.println("result size= " + result.size());
		
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


	private static ResultSetHandler<List<Row>> getResultSetHandler() {
		return new ResultSetHandler<List<Row>>() {
			public List<Row> handle(ResultSet rs) throws SQLException {
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

			}
		};
	}

	private static ResultSetHandler<List<Object[]>> getListResultSetHandler() {
		return new ResultSetHandler<List<Object[]>>() {
			public List<Object[]> handle(ResultSet rs) throws SQLException {
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

			}
		};
	}

	static Connection getDbConnection() {
		try {
			String userName = "bpigtn_db";
			String password = "^D$b2K5!";

			String url = "jdbc:sqlserver://10.4.48.18:1433;databaseName=BPIGTN_AGN_APPL7_QA";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			return DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
