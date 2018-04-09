/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processmonitor.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author
 */
@Service()
@Scope(value = "singleton")
public class GtnWsProcessMonitorTableService {
	public GtnWsProcessMonitorTableService() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessMonitorTableService.class);

	private Map<String, String> filterAndSortingCriteriaMap = new HashMap<>();

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public org.hibernate.SessionFactory getSysSessionFactory() {
		return sysSessionFactory;
	}

	public GtnUIFrameworkWebserviceResponse tableProcessMonitor(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getProcessMonitorCount"
					: "getProcessMonitorResults";
			List<Object> inputlist = getSearchInput(gtnWsRequest);
			@SuppressWarnings("unchecked")
			List<Object[]> result = executeQuery(getQuery(inputlist, queryName));
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(result);
				gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		gtnResponse.setGtnWsGeneralResponse(generalResponse);
		return gtnResponse;
	}

	private List<Object> getSearchInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();

		try {

			list.add(getSysSchemaCatalog());

			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				list.addAll(
						getSortedInputs(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		}
		return list;
	}

	private List<Object> getSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> list = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			list.add(filterAndSortingCriteriaMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			list.add(filterAndSortingCriteriaMap().get("processName") + " ASC");
		}
		return list;
	}

	private Map<String, String> filterAndSortingCriteriaMap() {
		if (filterAndSortingCriteriaMap.isEmpty()) {
			filterAndSortingCriteriaMap.put("processName", "WP.PROCESS_NAME");
			filterAndSortingCriteriaMap.put("processType", "PT.description");
			filterAndSortingCriteriaMap.put("slaCalendarMasterSid", "WP.SLA_CALENDAR_MASTER_SID");
			filterAndSortingCriteriaMap.put("modifiedDate", "WP.MODIFIED_DATE");
			filterAndSortingCriteriaMap.put("modifiedBy", "usr.lastName + ' ' + usr.middleName + ' ' + usr.firstName");
		}
		return filterAndSortingCriteriaMap;
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return getGtnSqlQueryEngine().executeSelectQuery(sqlQuery);
	}

	public String getQuery(String sqlId) {
		return getGtnWsSqlService().getQuery(sqlId);
	}

	@SuppressWarnings("rawtypes")
	public String getQuery(List input, String queryName) {
		StringBuilder sql = new StringBuilder();
		try {
			sql = new StringBuilder(getQuery(queryName));
			if (input != null) {
				for (Object temp : input) {
					sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
				}
			}

		} catch (Exception ex) {
			logger.error("Exception in get Query-", ex);
		}
		return sql.toString();
	}

	public String getSysSchemaCatalog() throws GtnFrameworkGeneralException {
		String catalog = "";
		try (Connection connection = getSysSessionFactory().getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			catalog = connection.getCatalog();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in getSysSchemaCatalog", ex);
		}
		return catalog;
	}
}
