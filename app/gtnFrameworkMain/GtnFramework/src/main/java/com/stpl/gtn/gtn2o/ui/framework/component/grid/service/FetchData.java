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
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.Collections;

public class FetchData {

	private static final Logger logger = LogManager.getLogger(FetchData.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	FetchData() {

	}

	public static List<Object[]> fetchResult(String query, Object... params) {
		logger.info(" fetchResult query" + query);
		QueryRunner queryRunner = new QueryRunner();
		ResultSetHandler<List<Object[]>> resultSetHandler = getListResultSetHandler();
		Connection conn = getDbConnection();
		try {
			return queryRunner.query(conn, replaceParameters(params, query), resultSetHandler);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		}

	}

	public static List<GtnWsRecordBean> fetchResultAsRow(Object[] visibleColumns, String query, Object... params) {
		logger.info(" callWebService query" + query);
		QueryRunner queryRunner = new QueryRunner();
		ResultSetHandler<List<GtnWsRecordBean>> resultSetHandler = getResultSetHandler(visibleColumns);
		Connection conn = getDbConnection();
		try {
			List<GtnWsRecordBean> result = queryRunner.query(conn, replaceParameters(params, query), resultSetHandler);
			logger.info("result size= " + result.size());

			return result;
		} catch (Exception e) {
			logger.error("in callWebService Error= " + e.getMessage());
			return Collections.emptyList();
		}

	}

	public static List<GtnWsRecordBean> callWebService(GtnUIFrameworkPagedTreeTableConfig tableConfig,
			String moduleName, GtnWsSearchRequest request, String componentId) {
		List<GtnWsRecordBean> records = new ArrayList<>();
		try {
			String url = request.isCount() ? tableConfig.getCountUrl() : tableConfig.getResultSetUrl();
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
			actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			GtnUIFrameworkActionExecutor.executeActionClass(componentId,
					tableConfig.getGridRequestGenerateActionClass(), actionConfig);
			GtnUIFrameworkWebserviceRequest webserviceRequest = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(componentId).getCustomPagedTreeTableRequest();
			webserviceRequest.setGtnWsSearchRequest(request);
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(url, moduleName,
					webserviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			if (response != null && response.getGtnWsReportResponse() != null) {
				records = mapper.convertValue(response.getGtnWsReportResponse().getRecordBeanResultList(),
						new TypeReference<List<GtnWsRecordBean>>() {
						});
			}


			logger.info("result size= " + records.size());
		} catch (Exception e) {
			logger.error("in fetchResultAsRow Error= ", e);
		}
		return records;
	}

	public static String replaceParameters(Object[] params, String query) {
		logger.info("params " + Arrays.toString(params));
                String result=query;
		for (Object param : params) {
			String parameter = String.valueOf(param).replaceAll("\\*", "\\%");
			result = result.replaceFirst("\\?", parameter);

		}
		return result;
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

}