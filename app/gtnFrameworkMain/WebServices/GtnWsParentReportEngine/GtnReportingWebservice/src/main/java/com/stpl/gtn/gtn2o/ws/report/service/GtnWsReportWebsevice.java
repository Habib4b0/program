package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service
public class GtnWsReportWebsevice {

	GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportWebsevice.class);
	
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportSqlService sqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

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
			boolean viewMode) throws GtnFrameworkGeneralException {
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
		} else {
			String viewName = criteriaMap.get("publicViewName");
			inputList.add("'" + viewName + "'");
		}
		inputList.add(userId);
		String viewQuery = sqlService.getQuery(inputList, "loadViewResults");
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(viewQuery);
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
		default:
			return null;
		}

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
		String filterId = dbColumnIdMap.get(searchCriteria.getFieldId());
		String filterValue = searchCriteria.getFilterValue1();
		String filterExpression = searchCriteria.getExpression();
		if (!dbColumnDataTypeMap.get(searchCriteria.getFieldId()).equals("Date")) {
			filter = filter + "AND" + " " + filterId + " " + filterExpression + " " + "'%" + filterValue + "%'";
		} else {
			String[] splitedArray = filterValue.split(" ");
			filter = getFilterValueForDateFields(filter, filterValue, splitedArray);
		}
		return filter;
	}

	private String getFilterValueForDateFields(String filter, String filterValue, String[] splitedArray) {
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
			gtnLogger.error(GtnWsQueryConstants.EXCEPTION_IN + ex);
		}
		return customizedResultList;
	}
}
