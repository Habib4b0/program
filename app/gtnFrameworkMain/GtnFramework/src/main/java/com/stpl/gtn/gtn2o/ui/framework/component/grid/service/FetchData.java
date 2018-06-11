package com.stpl.gtn.gtn2o.ui.framework.component.grid.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class FetchData {

	private static final Logger logger = LogManager.getLogger(FetchData.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	FetchData() {

	}

	public static List<Object[]> fetchResult(String query, Object... params) {
		// System.out.println("Count query" + query);
		logger.info(" fetchResult query" + query);
		query = replaceParameters(params, query);
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

	public static List<GtnWsRecordBean> fetchResultAsRow(Object[] visibleColumns, String query, Object... params) {
		// if (params.length == 1) {
		logger.info(" callWebService query" + query);
		query = replaceParameters(params, query);
		QueryRunner queryRunner = new QueryRunner();
		ResultSetHandler<List<GtnWsRecordBean>> resultSetHandler = getResultSetHandler(visibleColumns);
		Connection conn = getDbConnection();
		try {
			List<GtnWsRecordBean> result = queryRunner.query(conn, query, resultSetHandler);
			logger.info("result size= " + result.size());

			return result;
		} catch (Exception e) {
			logger.info("in callWebService Error= " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	public static List<GtnWsRecordBean> callWebService(GtnUIFrameworkPagedTreeTableConfig tableConfig,
			String moduleName, GtnWsSearchRequest request) {
		List<GtnWsRecordBean> records = new ArrayList<>();
		try {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsReportRequest reportRequest = new GtnWsReportRequest();
			String url = request.isCount() ? tableConfig.getCountUrl() : tableConfig.getResultSetUrl();
			serviceRequest.setGtnWsSearchRequest(request);
			serviceRequest.setGtnWsReportRequest(reportRequest);
                        GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
                        dataSelectionBean.setSessionId(tableConfig.getGtnWsReportDashboardBean().getSessionId());
                        reportRequest.setDataSelectionBean(dataSelectionBean);
			reportRequest.setGtnWsReportDashboardBean(tableConfig.getGtnWsReportDashboardBean());
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(url, moduleName, serviceRequest,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (response != null && response.getGtnWsReportResponse() != null) {
				records = mapper.convertValue(response.getGtnWsReportResponse().getRecordBeanResultList(),
						new TypeReference<List<GtnWsRecordBean>>() {
						});
			}

			// List<GtnWsRecordBean> result = (List<GtnWsRecordBean>)
			// response.getGtnReportResponse().getResultList();
			logger.info("result size= " + records.size());
		} catch (Exception e) {
			logger.info("in fetchResultAsRow Error= ", e);
			e.printStackTrace();
		}
		return records;
	}

	public static String replaceParameters(Object[] params, String query) {
		logger.info("params " + Arrays.toString(params));
		for (Object param : params) {
			String parameter = String.valueOf(param).replaceAll("\\*", "\\%");
			query = query.replaceFirst("\\?", parameter);

		}
		return query;
	}

	private static ResultSetHandler<List<GtnWsRecordBean>> getResultSetHandler(Object[] visibleColumns) {
		return (ResultSet rs) -> {
			List<GtnWsRecordBean> rows = new ArrayList<>();
			while (rs.next()) {
				ResultSetMetaData meta = rs.getMetaData();
				int cols = meta.getColumnCount();
				GtnWsRecordBean row = new GtnWsRecordBean();
				row.setRecordHeader(Arrays.asList(visibleColumns));
				for (int i = 0; i < cols; i++) {
					// System.out.println("column" + meta.getColumnName(i + 1));
					// System.out.println("value" + rs.getObject(i + 1));
					row.addProperties(meta.getColumnName(i + 1), rs.getObject(i + 1));
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

	public static Connection getDbConnection() {
		Connection connection = null;
		String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";

		try {
			Context initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
			if (datasource != null) {
				connection = datasource.getConnection();
			} else {
				logger.error("Failed to lookup datasource.");
			}
		} catch (NamingException | SQLException ex) {
			logger.error("Cannot get connection: " + ex);
		}
		return connection;
	}
	// static Connection getDbConnection() {
	// try {
	// String userName = "bpigtn_db";
	// String password = "^D$b2K5!";
	//
	// String url =
	// "jdbc:sqlserver://10.4.48.18:1433;databaseName=BPIGTN_AGN_APPL7_QA";
	//
	// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	//
	// return DriverManager.getConnection(url, userName, password);
	// } catch (Exception e) {
	// logger.error("in getDbConnection Error= " ,e);
	// e.printStackTrace();
	// return null;
	// }
	// }
}