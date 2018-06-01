package com.stpl.gtn.gtn2o.ws.report.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsCustomTreeData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportWebsevice;
import com.stpl.gtn.gtn2o.ws.report.serviceimpl.GtnWsTreeService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceDateResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportResponse;

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

	@Autowired
	private GtnWsTreeService gtnWsTreeService;

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
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadHierarchyResults(request, true);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductHierarchyResults(
			@RequestBody GtnUIFrameworkWebserviceRequest request) throws GtnFrameworkGeneralException {
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadHierarchyResults(request, false);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOAD_PRIVATEVIEWLOOKUP_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadViewResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException, IOException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadViewResults(gtnUIFrameworkWebserviceRequest, true);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOAD_PUBLICVIEWLOOKUP_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadPublicViewResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException, IOException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		List<Object[]> publicViewResultList = gtnWsReportWebsevice.loadViewResults(gtnUIFrameworkWebserviceRequest,
				false);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(publicViewResultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_LOADELIGIBLEDATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadForecastEligibleDate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnUIFrameworkWebserviceDateResponse dateResponse = new GtnUIFrameworkWebserviceDateResponse();
		Date forecastEligibleDate = gtnWsReportWebsevice.loadForecastEligibleDate();
		dateResponse.setResultValue(forecastEligibleDate);
		gtnResponse.setGtnUIFrameworkWebserviceDateResponse(dateResponse);
		return gtnResponse;
	}

	@RequestMapping(value = "/hierarchyDefinition", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getHierarchySidAndLevelDefId(
			@RequestBody GtnUIFrameworkWebserviceRequest request) throws GtnFrameworkGeneralException {

		GtnWsReportRequest gtnWsReportRequest = request.getGtnWsReportRequest();
		GtnReportHierarchyLookupBean lookupBean = gtnWsReportRequest.getCustomerHierarchyLookupBean();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		String query = GtnWsQueryConstants.HIERARCHY_SID_AND_LEVEL_DEFINITION_SID;
		query.replace("@HIERARCHY_DEFINITION_SID", String.valueOf(lookupBean.getHierarchyDefSid()))
				.replace("@VERSION_NO", String.valueOf(lookupBean.getVersionNo()));
		List<Object[]> results = executeQuery(query);
		GtnWsReportResponse gtnWsReportResponse = new GtnWsReportResponse();
		gtnWsReportResponse.setResultList(results);
		response.setGtnWsReportResponse(gtnWsReportResponse);
		return response;
	}

	@RequestMapping(value = "/gtnWsReportComboboxLoad", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getComboBoxResultSet(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(true);

		try {
			GtnWsReportAllListConfig gtnWsReportAllListConfig = new GtnWsReportAllListConfig();
			Map<String, String> comboBoxTypeResponseMap = gtnWsReportAllListConfig.getComboboxLoadMap();
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			String comboBoxType = generalWSRequest.getComboBoxType();
			String query = comboBoxTypeResponseMap.get(comboBoxType);
			List<Object[]> resultList = null;
			if (query != null) {
				comboBoxType = getComboboxTypeForReportFromAndToDate(query);
				resultList = executeQuery(comboBoxType);
				GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
				comboBoxResponse.setComboBoxList(resultList);
				gtnResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
			}

		} catch (Exception exception) {
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + exception);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}

	private String getComboboxTypeForReportFromAndToDate(String comboBoxType) {
		List<Object[]> resultList = null;
		String subQuery = "";
		String mainQuery = GtnWsQueryConstants.MAIN_QUERY_REPORT_FROM_AND_TO_DATE;
		try {
			resultList = executeQuery(mainQuery);
			String frequency = String.valueOf(resultList.get(0));
			subQuery = gtnWsReportWebsevice.getFromAndToDateLoadQuery(comboBoxType, frequency);
		} catch (Exception e) {
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + e);
		}
		return subQuery;
	}

	@RequestMapping(value = "/gtnWsReportLoadDataAssumptions", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getDataAssumptionsResults(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse wsResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse wsGeneralResponse = new GtnWsGeneralResponse();
		GtnSerachResponse wsSearchResponse = new GtnSerachResponse();
		List<Object[]> resultList = null;
		wsGeneralResponse.setSucess(true);
		boolean count = gtnWsRequest.getGtnWsSearchRequest().isCount();

		try {
			if (count) {
				resultList = executeQuery(GtnWsQueryConstants.DATA_ASSUMPTIONS_COUNT_QUERY);
				wsSearchResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));
			}

			else {
				String finalQuery = GtnWsQueryConstants.DATA_ASSUMPTIONS_RESULT_QUERY;

				String filter = gtnWsReportWebsevice.setFilterValueList(gtnWsRequest);

				finalQuery = finalQuery.replace("@filter", filter);
				resultList = executeQuery(finalQuery);
				resultList = gtnWsReportWebsevice.resultListCustomization(resultList);
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(resultList);
				wsSearchResponse.setResultSet(gtnUIFrameworkDataTable);
			}
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + e);
		}
		wsResponse.setGtnSerachResponse(wsSearchResponse);
		return wsResponse;
	}

	@SuppressWarnings({ "rawtypes" })
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		gtnSqlQueryEngine.setSessionFactory(sessionFactory);
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_BUILD_CUSTOM_TREE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse buildCustomTree(@RequestBody GtnUIFrameworkWebserviceRequest request)
			throws GtnFrameworkGeneralException {

		GtnWsReportRequest gtnWsReportRequest = request.getGtnWsReportRequest();
		GtnWsReportDashboardBean gtnWsReportDashboardBean = gtnWsReportRequest.getGtnWsReportDashboardBean();

		GtnWsReportEngineTreeNode root = new GtnWsReportEngineTreeNode();

		GtnWsCustomTreeData customTreeData = gtnWsTreeService.getCustomTreeData(
				MongoStringConstants.CUSTOM_VIEW_COLLECTION, gtnWsReportDashboardBean.getCustomViewName());

		GtnWsReportEngineTreeNode customerTree = gtnWsTreeService.getCustomerTree(MongoStringConstants.CUSTOMER_TREE,
				true, gtnWsReportDashboardBean.getSessionId());

		GtnWsReportEngineTreeNode productTree = gtnWsTreeService.getCustomerTree(MongoStringConstants.PRODUCT_TREE,
				true, gtnWsReportDashboardBean.getSessionId());

		@SuppressWarnings("unchecked")
		List<Object[]> ccpList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery("Select * from "
				+ gtnWsReportDashboardBean.getTableNameWithUniqueId(MongoStringConstants.ST_CCPD_SESSION_TABLE_NAME));

		@SuppressWarnings("unchecked")
		List<Object[]> deductionList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery("Select * from " + gtnWsReportDashboardBean
						.getTableNameWithUniqueId(MongoStringConstants.ST_DEDUCTION_SESSION_TABLE_NAME));
		long start = System.currentTimeMillis();
		gtnWsTreeService.buildCustomTree(root, customTreeData, customerTree, productTree, deductionList, ccpList);
		gtnLogger.info("Time taken to build Tree =" + (System.currentTimeMillis() - start));
		gtnWsTreeService.saveCustomTree(root,
				gtnWsReportDashboardBean.getTableNameWithUniqueId(gtnWsReportDashboardBean.getCustomViewName()));
		return new GtnUIFrameworkWebserviceResponse();
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_SAVEVIEW_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveView(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnWsReportRequest reportRequest = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest();
		GtnWsReportDataSelectionBean dataSelectionBean = reportRequest.getDataSelectionBean();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		int userId = Integer.valueOf(generalRequest.getUserId());
		int recordCount = gtnWsReportWebsevice.checkViewRecordCount(dataSelectionBean, userId);
		if (recordCount == 0) {
			gtnWsReportWebsevice.saveReportingMaster(dataSelectionBean, userId);
			generalResponse.setSucess(true);
		} else {
			generalResponse.setSucess(false);
		}
		response.setGtnWsGeneralResponse(generalResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORRT_DELETEVIEW_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteView(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnWsReportRequest reportRequest = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		int userId = Integer.valueOf(generalRequest.getUserId());
		GtnWsReportDataSelectionBean dataSelectionBean = reportRequest.getDataSelectionBean();
		return gtnWsReportWebsevice.deleteView(dataSelectionBean, userId);
	}
	
	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_COMPARISONLOOKUP_AVAILABLETABLE_LOADSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadComparisonAvailableTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		List<Object[]> resultList = gtnWsReportWebsevice.loadComparisonAvailableTable(gtnUIFrameworkWebserviceRequest);
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		searchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(searchResponse);
		return response;
	}
}
