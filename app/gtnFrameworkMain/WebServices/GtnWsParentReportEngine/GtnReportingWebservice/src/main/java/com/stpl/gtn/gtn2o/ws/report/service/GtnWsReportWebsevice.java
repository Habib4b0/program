package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportingDashboardSaveProfileLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.report.serviceimpl.GtnWsReportDataSelectionSqlGenerateServiceImpl;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Service
public class GtnWsReportWebsevice {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportWebsevice.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportSqlService sqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnReportJsonService gtnReportJsonService;

	public GtnWsReportWebsevice() {
		super();
	}

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

	public List<Object[]> loadHierarchyResults(GtnUIFrameworkWebserviceRequest request, boolean isCustomerHierarchy)
			throws GtnFrameworkGeneralException {
		List<Object> inputList = new ArrayList<>();
		Map<String, String> criteriaMap = new HashMap<>();
		for (GtnWebServiceSearchCriteria searchCriteria : request.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				criteriaMap.put(searchCriteria.getFieldId(), getCriteria(searchCriteria));
			}
		}
		if (isCustomerHierarchy) {
			inputList.add("'Customer Hierarchy'");
			String hierarchyName = criteriaMap.get("hierarchyName");
			inputList.add("'" + hierarchyName + "'");
		} else {
			inputList.add("'Product Hierarchy'");
			String hierarchyName = criteriaMap.get("hierarchyName");
			inputList.add("'" + hierarchyName + "'");
		}
		inputList.add("'" + criteriaMap.get("hierarchyType") + "'");
		String hierarchyLoadQuery = sqlService.getQuery(inputList, "getHierarchyResults");
		List<Object[]> hierarchyResultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(hierarchyLoadQuery);
		return hierarchyResultList;
	}

	public Date loadForecastEligibleDate() throws GtnFrameworkGeneralException {
		List forecastEligibleDate = gtnSqlQueryEngine
				.executeSelectQuery(sqlService.getQuery("loadForecastEligibleDate"));
		return forecastEligibleDate != null && !forecastEligibleDate.isEmpty() ? (Date) forecastEligibleDate.get(0)
				: null;
	}

	public List<Object[]> loadViewResults(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest,
			boolean viewMode) {
		try (Session session = sessionFactory.openSession()) {
			List<Object> inputList = new ArrayList<>();
			String userId = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest().getUserId();
			String viewType = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchQueryName();
			Map<String, String> criteriaMap = new HashMap<>();
			for (GtnWebServiceSearchCriteria searchCriteria : gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList()) {
				if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
					criteriaMap.put(searchCriteria.getFieldId(), getCriteria(searchCriteria));
				}
			}
			inputList.add("'" + viewType + "'");
			if (viewMode) {
				String privateViewName = criteriaMap.get("privateViewName");
				inputList.add("'" + privateViewName + "'");
				inputList.add(" AND CREATED_BY = " + userId);
                                inputList.add(0);
			} else {
				String viewName = criteriaMap.get("publicViewName");
				inputList.add("'" + viewName + "'");
				inputList.add(StringUtils.EMPTY);
                                inputList.add(0);
			}

			String viewQuery = sqlService.getQuery(inputList, "loadViewResults");
			SQLQuery query = session.createSQLQuery(viewQuery).addScalar("VIEW_NAME", new StringType())
					.addScalar("CREATED_DATE", new DateType()).addScalar("MODIFIED_DATE", new DateType())
					.addScalar("CREATED_BY", new IntegerType()).addScalar("VIEW_ID", new IntegerType())
					.addScalar("VIEW_DATA", new StringType());
			List<Object[]> resultList = query.list();
			return resultList;
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
			return null;
		}
	}

	public List<Object[]> loadComparisonAvailableTable(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		Map<String, String> criteriaMap = new HashMap<>();
		List<Object[]> comparisonResults = null;
		for (GtnWebServiceSearchCriteria searchCriteria : gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				criteriaMap.put(searchCriteria.getFieldId(), getCriteria(searchCriteria));
			}
		}
		if (criteriaMap.get("customViewName") == null) {
			return Collections.emptyList();
		} else {
			comparisonResults = criteriaMap.get("projectionType").equals("F")
					? loadProjectionComparisonResults(criteriaMap)
					: loadCFFComparisonResults(criteriaMap);
			return comparisonResults;
		}
	}

	private List<Object[]> loadProjectionComparisonResults(Map<String, String> criteriaMap)
			throws GtnFrameworkGeneralException {
		List<String> inputList = getInputList(criteriaMap);
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(sqlService.getQuery(inputList, "loadProjectionComparisonResults"));
		return resultList;
	}

	private List<String> getInputList(Map<String, String> criteriaMap) {
		List<String> inputList = new ArrayList<>();
		boolean isProjectionStatus = false;
		if (criteriaMap.get("workflowStatus").equals("Saved")) {
			isProjectionStatus = true;
		}
		String workflowJoinQuery = isProjectionStatus ? "" : (sqlService.getQuery("workflowJoinQuery"));
		String customViewMasterSid = criteriaMap.get("customViewName");
		String marketType = criteriaMap.get("marketType") == null ? "%" : criteriaMap.get("marketType");
		String comparisonBrand = criteriaMap.get("comparisonBrand") == null ? "%" : criteriaMap.get("comparisonBrand");
		String projectionName = criteriaMap.get("projectionName") == null ? "%" : criteriaMap.get("projectionName");
		String contractHolder = criteriaMap.get("contractHolder") == null ? "%" : criteriaMap.get("contractHolder");
		String ndcName = criteriaMap.get("ndcName") == null ? "%" : criteriaMap.get("ndcName");
		String comparisonNDC = criteriaMap.get("comparisonNDC") == null ? "%" : criteriaMap.get("comparisonNDC");
		String contract = criteriaMap.get("contract") == null ? "%" : criteriaMap.get("contract");
		String projectionDescription = criteriaMap.get("projectionDescription") == null ? "%"
				: criteriaMap.get("projectionDescription");
		inputList.add(workflowJoinQuery);
		inputList.add(customViewMasterSid);
		inputList.add("'" + marketType + "'");
		inputList.add("'" + comparisonBrand + "'");
		inputList.add("'" + projectionName + "'");
		inputList.add("'" + contractHolder + "'");
		inputList.add("'" + ndcName + "'");
		inputList.add("'" + comparisonNDC + "'");
		inputList.add("'" + contract + "'");
		inputList.add("'" + projectionDescription + "'");
		return inputList;
	}

	private List<Object[]> loadCFFComparisonResults(Map<String, String> criteriaMap)
			throws GtnFrameworkGeneralException {
		List<String> inputList = getInputList(criteriaMap);
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(sqlService.getQuery(inputList, "loadCFFComparisonResults"));
		return resultList;
	}

	private String getCriteria(GtnWebServiceSearchCriteria searchCriteria) {
		switch (searchCriteria.getFieldId()) {
		case "privateViewName":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "publicViewName":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "hierarchyName":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "hierarchyType":
			return searchCriteria.getFilterValue1();
		case "projectionType":
			return searchCriteria.getFilterValue1();
		case "workflowStatus":
			return searchCriteria.getFilterValue1();
		case "projectionName":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "marketType":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "comparisonBrand":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "contractHolder":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "comparisonNDC":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "projectionDescription":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "contract":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "ndcName":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "fromPeriod":
			return searchCriteria.getFilterValue1();
		case "toPeriod":
			return searchCriteria.getFilterValue1();
		case "customViewName":
			return searchCriteria.getFilterValue1();
		default:
			return null;
		}

	}

	public int checkViewRecordCount(GtnWsReportDataSelectionBean dataSelectionBean, int userId)
			throws GtnFrameworkGeneralException {
		int recordCount = 0;
		String query = sqlService.getQuery("getViewCount");
		Object[] params = { dataSelectionBean.getViewName(), dataSelectionBean.getViewType(), userId };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.INTEGER };
		List<Integer> resultList = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(query, params, paramsType);
		recordCount = resultList.get(0);
		return recordCount;
	}

	public int checkReportProfileViewRecordCount(GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean, int userId)
			throws GtnFrameworkGeneralException {
		int reportProfileCountRecordCount = 0;
		String reportProfileCountQuery = sqlService.getQuery("getViewCount");
		Object[] reportProfileCountParams = { reportingDashboardSaveProfileLookupBean.getReportProfileviewName(), reportingDashboardSaveProfileLookupBean.getReportProfileviewType(), userId };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.INTEGER };
		List<Integer> reportProfileCountResultList = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(reportProfileCountQuery, reportProfileCountParams, paramsType);
		reportProfileCountRecordCount = reportProfileCountResultList.get(0);
		return reportProfileCountRecordCount;
	}
	
	public int saveReportingMaster(GtnWsReportDataSelectionBean dataSelectionBean, int userId)
			throws GtnFrameworkGeneralException {
		List<Object> inputList = new ArrayList<>();
		inputList.add("'" + dataSelectionBean.getViewName() + "'");
		inputList.add("'" + dataSelectionBean.getViewType() + "'");
		inputList.add(userId);
		inputList.add(userId);
		String viewData = gtnReportJsonService.convertObjectAsJsonString(dataSelectionBean).replaceAll("'", "\\\\");
		inputList.add("'" + viewData + "'");
		inputList.add(0);
		String query = sqlService.getQuery(inputList, "insertView");
		int count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
		return count;
	}
	
	public int saveReportProfileMaster(GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean, int userId)
			throws GtnFrameworkGeneralException {
		List<Object> reportProfileInputList = new ArrayList<>();
		reportProfileInputList.add("'" + reportingDashboardSaveProfileLookupBean.getReportProfileviewName() + "'");
		reportProfileInputList.add("'" + reportingDashboardSaveProfileLookupBean.getReportProfileviewType() + "'");
		reportProfileInputList.add(userId);
		reportProfileInputList.add(userId);
		String reportProfileViewData = gtnReportJsonService.convertObjectAsJsonString(reportingDashboardSaveProfileLookupBean).replaceAll("'", "\\\\");
		reportProfileInputList.add("'" + reportProfileViewData + "'");
		reportProfileInputList.add(1);
		String reportProfileQuery = sqlService.getQuery(reportProfileInputList, "insertView");
		int reportProfileCount = gtnSqlQueryEngine.executeInsertOrUpdateQuery(reportProfileQuery);
		return reportProfileCount;
	}

	public String getFromAndToDateLoadQuery(String comboBoxType, String frequency) {
		String subQuery;
		if (comboBoxType.equals("FROM")) {
			if (frequency.startsWith("Mon")) {
				subQuery = GtnWsQueryConstants.SUB_QUERY_REPORT_FROM_DATE_MONTH;
			} else if (frequency.startsWith("Quar")) {
				subQuery = GtnWsQueryConstants.SUB_QUERY_REPORT_FROM_DATE_QUARTER;
			} else if (frequency.startsWith("Semi")) {
				subQuery = GtnWsQueryConstants.SUB_QUERY_REPORT_FROM_DATE_SEMI_ANNUAL;
			} else {
				subQuery = GtnWsQueryConstants.SUB_QUERY_REPORT_FROM_DATE_YEAR;
			}
		} else {
			if (frequency.startsWith("Mon")) {
				subQuery = GtnWsQueryConstants.SUB_QUERY_REPORT_TO_DATE_MONTH;
			} else if (frequency.startsWith("Quar")) {
				subQuery = GtnWsQueryConstants.SUB_QUERY_REPORT_TO_DATE_QUARTER;
			} else if (frequency.startsWith("Semi")) {
				subQuery = GtnWsQueryConstants.SUB_QUERY_REPORT_TO_DATE_SEMI_ANNUAL;
			} else {
				subQuery = GtnWsQueryConstants.SUB_QUERY_REPORT_TO_DATE_YEAR;
			}
		}
		return subQuery;
	}

	public String setFilterValueList(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		String filter = "";
		Map<String, String> dbColumnIdMap = getDataBaseColumnIdName();
		Map<String, String> dbColumnDataTypeMap = getDataBaseColumnDatatype();
		List<GtnWebServiceSearchCriteria> searchCriteriaList = gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList();
		if (!searchCriteriaList.isEmpty()) {
			for (GtnWebServiceSearchCriteria searchCriteria : searchCriteriaList) {
				filter = getFilterValuesForDataAssumptions(filter, dbColumnIdMap, dbColumnDataTypeMap, searchCriteria);
			}
		}
		return filter;
	}

	private String getFilterValuesForDataAssumptions(String filter, Map<String, String> dbColumnIdMap,
			Map<String, String> dbColumnDataTypeMap, GtnWebServiceSearchCriteria searchCriteria) {
		String filterString = filter;
		String filterId = dbColumnIdMap.get(searchCriteria.getFieldId());
		String filterValue = searchCriteria.getFilterValue1();
		String filterExpression = searchCriteria.getExpression();
		if (!dbColumnDataTypeMap.get(searchCriteria.getFieldId()).equals("Date")) {
			filterString = filterString + "AND" + " " + filterId + " " + filterExpression + " " + "'%" + filterValue + "%'";
		} else {
			String[] splitedArray = filterValue.split(" ");
			filterString = getFilterValueForDateFields(filterString, filterValue, splitedArray);
		}
		return filterString;
	}

	private String getFilterValueForDateFields(String filter, String filterValue, String[] splitedArray) {
		String filterString = filter;
		if ("Show all".equals(filterValue)) {
			filterString = filterString + "";
		} else if (!filterValue.startsWith(" ") && splitedArray.length >= 3) {

			filterString = filterString + " AND" + " CONVERT(date, FROM_PERIOD) >= CONVERT(date, '" + splitedArray[0] + "')"
					+ " AND" + " CONVERT(date, FROM_PERIOD) <= CONVERT(date, '" + splitedArray[2] + "')";

		} else if (!filterValue.startsWith(" ") && splitedArray.length < 3) {

			filterString = filterString + " AND" + " CONVERT(date, FROM_PERIOD) >= CONVERT(date, '" + splitedArray[0] + "')";

		} else {
			filterString = filterString + " AND" + " CONVERT(date, FROM_PERIOD) <= CONVERT(date, '" + splitedArray[2] + "')";
		}
		return filterString;
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
		dbColumnDataTypeMap.put("file", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("company", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("businessUnit", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("type", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("version", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("activeFrom", GtnWsQueryConstants.CONSTANT_DATE);
		dbColumnDataTypeMap.put("fromPeriod", GtnWsQueryConstants.CONSTANT_DATE);
		dbColumnDataTypeMap.put("toPeriod", GtnWsQueryConstants.CONSTANT_DATE);
		return dbColumnDataTypeMap;
	}

	public List<Object[]> resultListCustomization(List<Object[]> resultList) {
		List<Object[]> customizedResultList = new ArrayList<>();
		try {
			for (Object[] object : resultList) {
				Object[] obj = object;
				for (int i = 0; i < obj.length; i++) {
					obj[i] = obj[i] == null ? "" : String.valueOf(obj[i]);
				}
				customizedResultList.add(object);
			}
		} catch (Exception ex) {
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + ex);
		}
		return customizedResultList;
	}

	public GtnUIFrameworkWebserviceResponse deleteView(GtnWsReportDataSelectionBean dataSelectionBean, int userId) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		String query = sqlService.getQuery("deleteView");
		Object[] params = { dataSelectionBean.getViewId(), userId };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
		try {
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, paramsType);
			response.getGtnWsGeneralResponse().setSucess(true);
		} catch (GtnFrameworkGeneralException e) {
			response.getGtnWsGeneralResponse().setSucess(false);
		}
		return response;
	}

	public List<Object[]> getUOMDDLBValues(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkGeneralException {
		List<Object[]> uomResultSet = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(
				GtnWsReportDataSelectionSqlGenerateServiceImpl.replaceTableNames(sqlService.getQuery("getUOMDetails"),
						dataSelectionBean.getSessionTableMap()));
		return uomResultSet;
	}
}
