package com.stpl.gtn.gtn2o.ws.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportWebsevice;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = GtnWsForecastConstants.GTN_FORECAST_SERVICE)
public class GtnWsForecastController {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportController.class);

	public GtnWsForecastController() {
		super();
	}

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	private GtnWsReportWebsevice gtnWsReportWebsevice;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	@RequestMapping(value = GtnWsForecastConstants.GTN_FORECAST_CUSTOMERHIERARCHY_SEARCHSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadHierarchyResults(@RequestBody GtnUIFrameworkWebserviceRequest request)
			throws GtnFrameworkGeneralException {
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadHierarchyResults(request, true);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

}
