package com.stpl.gtn.gtn2o.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.CustomerHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsReportWebsevice;

@RestController
@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_SERVICE)
public class GtnWsReportController {

	public GtnWsReportController() {

	}

	GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportController.class);

	@Autowired
	private GtnWsReportWebsevice gtnWsReportWebsevice;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_CUSTOMERHIERARCHY_SEARCHSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadHierarchyResults(@RequestBody GtnUIFrameworkWebserviceRequest request)
			throws GtnFrameworkGeneralException {
		List<Object[]> resultList;
		GtnWsReportRequest gtnWsReportRequest = request.getGtnWsReportRequest();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();

                GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		if (gtnWsReportRequest != null) {
			CustomerHierarchyLookupBean hierarchyBean = gtnWsReportRequest.getCustomerHierarchyLookupBean();
			resultList = gtnWsReportWebsevice.loadHierarchyResults(hierarchyBean);
		}else{
			resultList = gtnWsReportWebsevice.loadHierarchyResults();
		}
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductHierarchyResults(
			@RequestBody GtnUIFrameworkWebserviceRequest request) throws GtnFrameworkGeneralException {
		List<Object[]> resultList;
		GtnWsReportRequest gtnWsReportRequest = request.getGtnWsReportRequest();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		if (gtnWsReportRequest != null) {
			CustomerHierarchyLookupBean hierarchyBean = gtnWsReportRequest.getCustomerHierarchyLookupBean();
			resultList = gtnWsReportWebsevice.loadProductHierarchyResults(hierarchyBean);
		}else{
			resultList = gtnWsReportWebsevice.loadProductHierarchyResults();
		}
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOADRELATIONSHIP_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadRelationshipValues(@RequestBody GtnUIFrameworkWebserviceRequest request) throws GtnFrameworkGeneralException{
		GtnWsReportRequest gtnWsReportRequest = request.getGtnWsReportRequest();
		CustomerHierarchyLookupBean lookupBean = gtnWsReportRequest.getCustomerHierarchyLookupBean();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		gtnWsReportWebsevice.loadRelationshipValues(lookupBean);
		return response;
	}
}
