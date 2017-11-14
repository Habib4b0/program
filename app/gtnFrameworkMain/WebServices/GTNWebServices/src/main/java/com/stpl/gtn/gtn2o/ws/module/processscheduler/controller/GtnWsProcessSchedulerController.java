/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.processscheduler.constants.GtnWsProcessScedulerConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 *
 * @author
 */
@RestController
@RequestMapping(value = GtnWsProcessScedulerConstants.GTN_PROCESS_SCHEDULER_SERVICE_SCREEN)
public class GtnWsProcessSchedulerController {
	public GtnWsProcessSchedulerController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessSchedulerController.class);

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@RequestMapping(value = GtnWsProcessScedulerConstants.GET_PROCESS_SCHEDULER_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getProcesMonitorTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest processSchedulerRequest) throws GtnFrameworkGeneralException {
		logger.info("Enter ProcessScheduler");
		GtnUIFrameworkWebserviceResponse processSchedulerResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
		String queryName = processSchedulerRequest.getGtnWsSearchRequest().isCount() ? "getProcessSchedulerCount"
				: "getProcessSchedulerManualResults";

		List<Object> inputlist = getSearchInput(processSchedulerRequest);
		@SuppressWarnings("unchecked")
		List<Object[]> result = executeQuery(gtnWsSqlService.getQuery(inputlist, queryName));

		if (processSchedulerRequest.getGtnWsSearchRequest().isCount()) {
			gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
		} else {
			GtnUIFrameworkDataTable processSchedulerDataTable = new GtnUIFrameworkDataTable();
			processSchedulerDataTable.addData(result);
			gtnSerachResponse.setResultSet(processSchedulerDataTable);
		}

		processSchedulerResponse.setGtnSerachResponse(gtnSerachResponse);
		generalResponse.setSucess(true);
		processSchedulerResponse.setGtnWsGeneralResponse(generalResponse);
		return processSchedulerResponse;
	}

	private List<Object> getSearchInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();

		try {

			list.add(getSysSchemaCatalog());

			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				list.addAll(getSortedInputs());
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	public String getSysSchemaCatalog() throws GtnFrameworkGeneralException {
		String catalog = "";
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			catalog = connection.getCatalog();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new GtnFrameworkGeneralException("Exception in getSysSchemaCatalog", ex);
		}
		return catalog;
	}

	private List<Object> getSortedInputs() {
		List<Object> list = new ArrayList<>();
		list.add("WP.PROCESS_NAME" + " DESC");
		return list;
	}
}
