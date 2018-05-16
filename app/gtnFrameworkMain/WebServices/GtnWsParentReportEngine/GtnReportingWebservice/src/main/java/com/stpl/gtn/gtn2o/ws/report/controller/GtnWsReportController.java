package com.stpl.gtn.gtn2o.ws.report.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsCustomTreeData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportWebsevice;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsTreeService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
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

		List<Object[]> resultList = null;
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		resultList = gtnWsReportWebsevice.loadHierarchyResults();
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
		gtnSearchResponse.setResultSet(dataTable);
		response.setGtnSerachResponse(gtnSearchResponse);
		return response;
	}

	@RequestMapping(value = GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadProductHierarchyResults(
			@RequestBody GtnUIFrameworkWebserviceRequest request) throws GtnFrameworkGeneralException {

		List<Object[]> resultList = null;
		GtnSerachResponse gtnSearchResponse = new GtnSerachResponse();
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		resultList = gtnWsReportWebsevice.loadProductHierarchyResults();
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		dataTable.addData(resultList);
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
		System.out.println("----inside-GtnWsGeneralController-----------");
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
			gtnLogger.error("Exception in " + exception);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}

	private String getComboboxTypeForReportFromAndToDate(String comboBoxType) {
		List<Object[]> resultList = null;
		String subQuery = "";
		String mainQuery = ";WITH cte AS "
				+ "(SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE AS  TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER BY VERSION_NO DESC) SELECT    DESCRIPTION  FROM   forecast_config f JOIN HELPER_TABLE h ON f.HIST_FREQ = h.HELPER_TABLE_SID WHERE  FROM_DATE=(SELECT FROM_DATE FROM   cte c)";
		try {
			resultList = executeQuery(mainQuery);
			String frequency = String.valueOf(resultList.get(0));
			if (comboBoxType.equals("FROM")) {
				if (frequency.startsWith("Mon")) {
					subQuery = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE AS  TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER BY VERSION_NO DESC) SELECT Min(period_sid) AS PERIOD_SID, CONCAT(SUBSTRING(DATENAME(mm,PERIOD_DATE),1,3),' ',YEAR) FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c  WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY YEAR,PERIOD_DATE ORDER  BY YEAR,PERIOD_DATE";
				} else if (frequency.startsWith("Quar")) {
					subQuery = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE   TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER  BY VERSION_NO DESC) SELECT Min(period_sid) AS PERIOD_SID, Concat('Q', QUARTER, ' - ', YEAR) FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY Concat('Q', QUARTER, ' - ', YEAR), year, QUARTER ORDER  BY YEAR, QUARTER, Concat('Q', QUARTER, ' - ', YEAR) ";
				} else if (frequency.startsWith("Semi")) {
					subQuery = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE AS  TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER BY VERSION_NO DESC) SELECT Min(period_sid) AS PERIOD_SID, CONCAT('S',SEMI_ANNUAL,'-',YEAR) FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY YEAR,SEMI_ANNUAL ORDER  BY YEAR,SEMI_ANNUAL";
				} else {
					subQuery = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE   TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER  BY VERSION_NO DESC) SELECT Min(period_sid) AS PERIOD_SID,YEAR FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY YEAR ORDER  BY YEAR";
				}
			} else {
				if (frequency.startsWith("Mon")) {
					subQuery = "     	;WITH cte     AS (SELECT TOP 1 FROM_DATE AS FROM_DATE,               TO_DATE AS  TO_DATE  		FROM   forecast_config f         		JOIN HELPER_TABLE h           			ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID  		WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE'        	   AND DESCRIPTION = 'Commercial'  		ORDER BY VERSION_NO DESC) 	SELECT TOP 1 Min(period_sid) AS PERIOD_SID,      	CONCAT(SUBSTRING(DATENAME(mm,PERIOD_DATE),1,3),' ',YEAR) 	FROM   PERIOD p 	WHERE  EXISTS (SELECT 1                FROM   cte c                WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) 	GROUP  BY YEAR,PERIOD_DATE 	ORDER  BY PERIOD_SID DESC";
				} else if (frequency.startsWith("Quar")) {
					subQuery = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE   TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER  BY VERSION_NO DESC) SELECT TOP 1 Min(period_sid) AS PERIOD_SID, Concat('Q', QUARTER, ' - ', YEAR) FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY Concat('Q', QUARTER, ' - ', YEAR), year, QUARTER ORDER  BY PERIOD_SID DESC ";
				} else if (frequency.startsWith("Semi")) {
					subQuery = "          	;WITH cte     AS (SELECT TOP 1 FROM_DATE AS FROM_DATE,               TO_DATE AS  TO_DATE  		FROM   forecast_config f         		JOIN HELPER_TABLE h           			ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID  		WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE'        	   AND DESCRIPTION = 'Commercial'  		ORDER BY VERSION_NO DESC) 	SELECT TOP 1 Min(period_sid) AS PERIOD_SID,      	CONCAT('S',SEMI_ANNUAL,'-',YEAR) 	FROM   PERIOD p 	WHERE  EXISTS (SELECT 1                FROM   cte c                WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) 	GROUP  BY YEAR,SEMI_ANNUAL 	ORDER  BY PERIOD_SID DESC";
				} else {
					subQuery = ";WITH cte     AS (SELECT TOP 1 FROM_DATE AS FROM_DATE,                      TO_DATE   TO_DATE         FROM   forecast_config f                JOIN HELPER_TABLE h                  ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID         WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE'                AND DESCRIPTION = 'Commercial'         ORDER  BY VERSION_NO DESC)	SELECT TOP 1 Min(period_sid) AS PERIOD_SID,YEAR	FROM   PERIOD p	WHERE  EXISTS (SELECT 1	               FROM   cte c	               WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE)	GROUP  BY YEAR	ORDER  BY PERIOD_SID DESC";
				}
			}
		} catch (Exception e) {
			gtnLogger.error("Exception in " + e);
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

				String filter = setFilterValueList(gtnWsRequest);

				finalQuery = finalQuery.replace("@filter", filter);
				resultList = executeQuery(finalQuery);
				resultList = resultListCustomization(resultList);
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(resultList);
				wsSearchResponse.setResultSet(gtnUIFrameworkDataTable);
			}
		} catch (GtnFrameworkGeneralException e) {
			gtnLogger.error("Exception in " + e);
		}
		wsResponse.setGtnSerachResponse(wsSearchResponse);
		return wsResponse;
	}

	private String setFilterValueList(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		String filter = "";
		Map<String, String> dbColumnIdMap = getDataBaseColumnIdName();
		Map<String, String> dbColumnDataTypeMap = getDataBaseColumnDatatype();
		List<GtnWebServiceSearchCriteria> searchCriteriaList = gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList();
		if (!searchCriteriaList.isEmpty()) {
			for (GtnWebServiceSearchCriteria searchCriteria : searchCriteriaList) {
				String filterId = dbColumnIdMap.get(searchCriteria.getFieldId());
				String filterValue = searchCriteria.getFilterValue1();
				String filterExpression = searchCriteria.getExpression();
				if (!dbColumnDataTypeMap.get(searchCriteria.getFieldId()).equals("Date")) {
					filter = filter + "AND" + " " + filterId + " " + filterExpression + " " + "'%" + filterValue + "%'";
				} else {
					String[] splitedArray = filterValue.split(" ");
					if ("Show all".equals(filterValue)) {
						filter = filter + "";
					} else if (!filterValue.startsWith(" ") && splitedArray.length >= 3) {

						filter = filter + " AND" + " CONVERT(date, FROM_PERIOD) >= CONVERT(date, '" + splitedArray[0]
								+ "')" + " AND" + " CONVERT(date, FROM_PERIOD) <= CONVERT(date, '" + splitedArray[2]
								+ "')";

					} else if (!filterValue.startsWith(" ") && splitedArray.length < 3) {

						filter = filter + " AND" + " CONVERT(date, FROM_PERIOD) >= CONVERT(date, '" + splitedArray[0]
								+ "')";

					} else {
						filter = filter + " AND" + " CONVERT(date, FROM_PERIOD) <= CONVERT(date, '" + splitedArray[2]
								+ "')";
					}
				}
			}
		}
		return filter;
	}


	private Map<String, String> getDataBaseColumnIdName() {
		Map<String, String> dbColumnIdMap = new HashMap<>();
		dbColumnIdMap.put("file", "FORECAST_NAME");
		dbColumnIdMap.put("company", "company.COMPANY_NAME");
		dbColumnIdMap.put("businessUnit", "businessunit.COMPANY_NAME");
		dbColumnIdMap.put("type", "ht.DESCRIPTION");
		dbColumnIdMap.put("version", "VERSION");
		dbColumnIdMap.put("activeFrom", "ACTIVE_FROM");
		dbColumnIdMap.put("fromPeriod", "FROM_PERIOD");
		dbColumnIdMap.put("toPeriod", "TO_PERIOD");
		return dbColumnIdMap;
	}

	private Map<String, String> getDataBaseColumnDatatype() {
		Map<String, String> dbColumnDataTypeMap = new HashMap<>();
		dbColumnDataTypeMap.put("file", "String");
		dbColumnDataTypeMap.put("company", "String");
		dbColumnDataTypeMap.put("businessUnit", "String");
		dbColumnDataTypeMap.put("type", "String");
		dbColumnDataTypeMap.put("version", "String");
		dbColumnDataTypeMap.put("activeFrom", "Date");
		dbColumnDataTypeMap.put("fromPeriod", "Date");
		dbColumnDataTypeMap.put("toPeriod", "Date");
		return dbColumnDataTypeMap;
	}

	public List<Object[]> resultListCustomization(List<Object[]> resultList) {
		List<Object[]> customizedResultList = new ArrayList<>();
		try{
		for (Object[] object : resultList) {
			Object[] obj = object;
			for (int i = 0; i < obj.length; i++) {
				obj[i] = obj[i]==null?"":String.valueOf(obj[i]);
			}
			customizedResultList.add(object);
		}
		}
		catch(Exception ex){
			gtnLogger.error("Exception in " + ex);
		}
		return customizedResultList;
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

		GtnWsReportEngineTreeNode customerTree = gtnWsTreeService
				.getCustomerTree(gtnWsReportDashboardBean.getTableNameWithUniqueId(MongoStringConstants.CUSTOMER_TREE));

		GtnWsReportEngineTreeNode productTree = gtnWsTreeService
				.getCustomerTree(gtnWsReportDashboardBean.getTableNameWithUniqueId(MongoStringConstants.PRODUCT_TREE));

		List<Object[]> ccpList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery("Select * from "
				+ gtnWsReportDashboardBean.getTableNameWithUniqueId(MongoStringConstants.ST_CCPD_SESSION_TABLE_NAME));

		List<Object[]> deductionList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery("Select * from " + gtnWsReportDashboardBean
						.getTableNameWithUniqueId(MongoStringConstants.ST_DEDUCTION_SESSION_TABLE_NAME));

		gtnWsTreeService.buildCustomTree(root, customTreeData, customerTree, productTree, deductionList, ccpList);
		gtnWsTreeService.saveCustomTree(root,
				gtnWsReportDashboardBean.getTableNameWithUniqueId(gtnWsReportDashboardBean.getCustomViewName()));
		return new GtnUIFrameworkWebserviceResponse();
	}

}
