package com.stpl.gtn.gtn2o.ws.report.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
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

	@Autowired 
    private org.hibernate.SessionFactory sysSessionFactory;
	
	public org.hibernate.SessionFactory getSysSessionFactory() {
		return sysSessionFactory;
	}

	public void setSysSessionFactory(org.hibernate.SessionFactory sysSessionFactory) {
		this.sysSessionFactory = sysSessionFactory;
	}

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
		hierarchyLoadQuery = hierarchyLoadQuery.replace("@filter", setFilterForHierarchy(request));
		List<Object[]> hierarchyResultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(hierarchyLoadQuery);
		return resultListCustomization(hierarchyResultList);
	}

	public Date loadForecastEligibleDate() throws GtnFrameworkGeneralException {
		List forecastEligibleDate = gtnSqlQueryEngine
				.executeSelectQuery(sqlService.getQuery("loadForecastEligibleDate"));
		return forecastEligibleDate != null && !forecastEligibleDate.isEmpty() ? (Date) forecastEligibleDate.get(0)
				: null;
	}
	
	public List<Object[]> getLoadViewResultsCount(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest,
			boolean viewMode, int viewCheck) {
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			List<Object> inputList = new ArrayList<>();
			String userId = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest().getUserId();
			String viewType = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchQueryName();
			String viewName = "";
			Map<String, String> criteriaMap = new HashMap<>();
			for (GtnWebServiceSearchCriteria searchCriteria : gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList()) {
				if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
					criteriaMap.put(searchCriteria.getFieldId(), getCriteria(searchCriteria));
				}
			}
			if (viewCheck == 1) {
				viewType = criteriaMap.get("reportProfileLookup_viewType");
				viewName = criteriaMap.get("reportProfileLookup_viewName");
				if(viewName==null) 
					viewName = "%";
				if (viewType.startsWith("Priv")) {
					viewMode = true;
				} else {
					viewMode = false;
				}
			}
			inputList.add(connection.getCatalog());
			inputList.add("'" + viewType + "'");
			if (viewMode) {
				if (viewCheck == 0) {
					viewName = criteriaMap.get("privateViewName");
				}
				inputList.add("'" + viewName + "'");
				inputList.add(" AND CREATED_BY = " + userId);
				inputList.add(viewCheck);
			} else {
				if (viewCheck == 0) {
					viewName = criteriaMap.get("publicViewName");
				}
				inputList.add("'" + viewName + "'");
				inputList.add(StringUtils.EMPTY);
				inputList.add(viewCheck);
			}
			
			String viewQuery = sqlService.getQuery(inputList, "getLoadViewResultsCount");
			return executeGetLoadViewResultsQueryCount(viewQuery,gtnUIFrameworkWebserviceRequest);
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
			return null;
		}
	}
	

	public List<Object[]> loadViewResults(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest,
			boolean viewMode, int viewCheck) {
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			List<Object> inputList = new ArrayList<>();
			String userId = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest().getUserId();
			String viewType = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchQueryName();
			String viewName = "";
			Map<String, String> criteriaMap = new HashMap<>();
			for (GtnWebServiceSearchCriteria searchCriteria : gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList()) {
				if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
					criteriaMap.put(searchCriteria.getFieldId(), getCriteria(searchCriteria));
				}
			}
			if (viewCheck == 1) {
				viewType = criteriaMap.get("reportProfileLookup_viewType");
				viewName = criteriaMap.get("reportProfileLookup_viewName");
				if(viewName==null) 
					viewName = "%";
				if (viewType.startsWith("Priv")) {
					viewMode = true;
				} else {
					viewMode = false;
				}
			}
			inputList.add(connection.getCatalog());
			inputList.add("'" + viewType + "'");
			if (viewMode) {
				if (viewCheck == 0) {
					viewName = criteriaMap.get("privateViewName");
				}
				inputList.add("'" + viewName + "'");
				inputList.add(" AND CREATED_BY = " + userId);
				inputList.add(viewCheck);
			} else {
				if (viewCheck == 0) {
					viewName = criteriaMap.get("publicViewName");
				}
				inputList.add("'" + viewName + "'");
				inputList.add(StringUtils.EMPTY);
				inputList.add(viewCheck);
			}
			int noOfRowsForFetchClause = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getTableRecordOffset();
			if(noOfRowsForFetchClause==0)
				noOfRowsForFetchClause = 10;
			inputList.add(gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getTableRecordStart());
			inputList.add(noOfRowsForFetchClause);

			String viewQuery = sqlService.getQuery(inputList, "loadViewResults");
			return executeLoadViewResultsQuery(viewQuery,gtnUIFrameworkWebserviceRequest);
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
			return null;
		}
	}

	private List<Object[]> executeLoadViewResultsQuery(String viewQuery, GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		try(Session session = sessionFactory.openSession()){
			viewQuery = viewQuery.replace("@filter", setFilterForHierarchy(gtnUIFrameworkWebserviceRequest));
		SQLQuery query = session.createSQLQuery(viewQuery).addScalar("VIEW_NAME", new StringType())
				.addScalar("CREATED_DATE", new DateType()).addScalar("MODIFIED_DATE", new DateType())
				.addScalar("CREATED_BY", new StringType()).addScalar("VIEW_ID", new IntegerType())
				.addScalar("VIEW_DATA", new StringType());
		List<Object[]> resultList = query.list();
		return resultList;
		}catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	private List<Object[]> executeGetLoadViewResultsQueryCount(String viewQuery, GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		try(Session session = sessionFactory.openSession()){
			viewQuery = viewQuery.replace("@filter", setFilterForHierarchy(gtnUIFrameworkWebserviceRequest));
		SQLQuery query = session.createSQLQuery(viewQuery);
		List<Object[]> resultList = query.list();
		return resultList;
		}catch (Exception ex) {
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
					? loadProjectionComparisonResults(criteriaMap,gtnUIFrameworkWebserviceRequest,"loadResult") : loadCFFComparisonResults(criteriaMap,gtnUIFrameworkWebserviceRequest,"loadResult");
			return comparisonResults;
		}
	}
	
	public List<Object[]> getComparisonAvailableTableCount(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
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
					? loadProjectionComparisonResults(criteriaMap,gtnUIFrameworkWebserviceRequest,"count") : loadCFFComparisonResults(criteriaMap,gtnUIFrameworkWebserviceRequest,"count");
			return comparisonResults;
		}
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> loadProjectionComparisonResults(Map<String, String> criteriaMap, GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest , String queryType)
			throws GtnFrameworkGeneralException {
		List<String> inputList = getInputList(criteriaMap, gtnUIFrameworkWebserviceRequest,queryType);
               
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(
				queryType.equals("loadResult") ? sqlService.getQuery(inputList, "loadProjectionComparisonResults")
						: sqlService.getQuery(inputList, "getProjectionComparisonResultsCount"));
		
		return queryType.equals("loadResult") ?resultListCustomization(resultList):resultList;
	}
	
	private List<Object[]> loadCFFComparisonResults(Map<String, String> criteriaMap,GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest , String queryType)
			throws GtnFrameworkGeneralException {
		
		List<String> inputList = getCffInputList(criteriaMap,gtnUIFrameworkWebserviceRequest,queryType);
		if(!inputList.isEmpty()) {
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(queryType.equals("loadResult")?sqlService.getQuery(inputList, "loadCFFComparisonResults"):sqlService.getQuery(inputList, "getCFFComparisonResultsCount"));
		return resultList;
		}
		return Collections.emptyList();
	}
	

	private List<String> getInputList(Map<String, String> criteriaMap,
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest, String queryType) {
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			List<String> inputList = new ArrayList<>();
			boolean isProjectionStatus = false;
			if (criteriaMap.get("workflowStatus").equals("Saved")) {
				isProjectionStatus = true;
			}
			String workflowJoinQuery = isProjectionStatus ? "" : (sqlService.getQuery("workflowJoinQuery"));
			String customViewMasterSid = criteriaMap.get("customViewName");
			String marketType = criteriaMap.get("marketType") == null ? "%" : criteriaMap.get("marketType");
			String comparisonBrand = criteriaMap.get("brand") == null ? "%" : criteriaMap.get("brand");
			String projectionName = criteriaMap.get("projectionName") == null ? "%" : criteriaMap.get("projectionName");
			String contractHolder = criteriaMap.get("contractHolder") == null ? "%" : criteriaMap.get("contractHolder");
			String ndcName = criteriaMap.get("ndcName") == null ? "%" : criteriaMap.get("ndcName");
			String comparisonNDC = criteriaMap.get("comparisonNDC") == null ? "%" : criteriaMap.get("comparisonNDC");
			String contract = criteriaMap.get("contract") == null ? "%" : criteriaMap.get("contract");
			String projectionDescription = criteriaMap.get("description") == null ? "%"
					: criteriaMap.get("description");
			String fromPeriod = criteriaMap.get("fromPeriod") == null ? "" : criteriaMap.get("fromPeriod");
			String toPeriod = criteriaMap.get("toPeriod") == null ? "" : criteriaMap.get("toPeriod");
			String createdDate = criteriaMap.get("createdDate") == null || criteriaMap.get("createdDate").equals("null")
					? "%"
					: criteriaMap.get("createdDate");
			String createdBy = criteriaMap.get("createdBy") == null ? "%" : criteriaMap.get("createdBy");
			String whereCondition = isProjectionStatus
					? "ISNULL(PM.IS_APPROVED,'') NOT IN('Y','C','A','R') AND PM.SAVE_FLAG = 1"
					: "HT1.list_name = 'WorkFlowStatus' and HT1.description =" + "'" + criteriaMap.get("workflowStatus")
							+ "'";
			int tableRecordStart = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getTableRecordStart();
			int tableRecordOffset = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getTableRecordOffset();
			inputList.add(workflowJoinQuery);
			inputList.add(customViewMasterSid);
			inputList.add(connection.getCatalog());
			inputList.add(whereCondition);
			inputList.add("'" + marketType + "'");
			inputList.add("'" + comparisonBrand + "'");
			inputList.add("'" + projectionName + "'");
			inputList.add("'" + contract + "'");
			inputList.add("'" + ndcName + "'");
			inputList.add("'" + comparisonNDC + "'");
			inputList.add("'" + contractHolder + "'");
			inputList.add("'" + projectionDescription + "'");
			inputList.add("'" + createdDate + "'");
			getFromAndToPeriod(inputList, fromPeriod, toPeriod);
			inputList.add("'" + createdBy + "'");
			if (queryType.equals("loadResult")) {
				inputList.add("" + tableRecordStart + "");
				inputList.add("" + tableRecordOffset + "");
			}
			return inputList;
		} catch (SQLException e) {
			gtnLogger.error(e + "");
			return Collections.emptyList();
		}
	}
	
	private void getFromAndToPeriod(List<String> inputList, String fromPeriod, String toPeriod) {
		if (!fromPeriod.isEmpty() && !toPeriod.isEmpty()) {
			inputList.add("'" + fromPeriod + "'"); 
			inputList.add("'" + toPeriod + "'"); 
			inputList.add(null);
			inputList.add(null);
		} else if (fromPeriod.isEmpty() && !toPeriod.isEmpty()) {
			inputList.add(null); 
			inputList.add(null); 
			inputList.add(null);
			inputList.add("'" + toPeriod + "'"); 
		} else if (!fromPeriod.isEmpty() && toPeriod.isEmpty()) {
			inputList.add(null); 
			inputList.add(null); 
			inputList.add("'" + fromPeriod + "'"); 
			inputList.add(null); 
		} else {
			inputList.add("''"); 
			inputList.add("''"); 
			inputList.add("''"); 
			inputList.add("''"); 
		}
	}
	
	private List<String> getCffInputList(Map<String, String> criteriaMap,
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest, String queryType) {
		List<String> cffInputList = new ArrayList<>();

		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			String workFlowStatus = criteriaMap.get("workflowStatus");
			if (workFlowStatus.equals("Submitted")) {
				workFlowStatus = "Pending";
			}
			String projectionName = criteriaMap.get("projectionName") == null ? "%" : criteriaMap.get("projectionName");
			String projectionDescription = criteriaMap.get("description") == null ? "%"
					: criteriaMap.get("description");
			String fromPeriod = criteriaMap.get("fromPeriod") == null ? "" : criteriaMap.get("fromPeriod");
			String toPeriod = criteriaMap.get("toPeriod") == null ? "" : criteriaMap.get("toPeriod");
			String customViewMasterSid = criteriaMap.get("customViewName");
			String contract = criteriaMap.get("contract") == null ? "%" : criteriaMap.get("contract");
			String marketType = criteriaMap.get("marketType") == null ? "%" : criteriaMap.get("marketType");
			String contractHolder = criteriaMap.get("contractHolder") == null ? "%" : criteriaMap.get("contractHolder");
			String ndcName = criteriaMap.get("ndcName") == null ? "%" : criteriaMap.get("ndcName");
			String comparisonNDC = criteriaMap.get("comparisonNDC") == null ? "%" : criteriaMap.get("comparisonNDC");
			String comparisonBrand = criteriaMap.get("brand") == null ? "%" : criteriaMap.get("brand");
			String createdDate = criteriaMap.get("createdDate") == null || criteriaMap.get("createdDate").equals("null")
					? "%"
					: criteriaMap.get("createdDate");
			String createdBy = criteriaMap.get("createdBy") == null ? "%" : criteriaMap.get("createdBy");
			int tableRecordStart = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getTableRecordStart();
			int tableRecordOffset = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getTableRecordOffset();
			cffInputList.add("'" + workFlowStatus + "'");
			cffInputList.add("'" + projectionName + "'");
			cffInputList.add("'" + projectionDescription + "'");
			getFromAndToPeriod(cffInputList, fromPeriod, toPeriod);
			cffInputList.add("'" + customViewMasterSid + "'");
			cffInputList.add("'" + contract + "'");
			cffInputList.add("'" + ndcName + "'");
			cffInputList.add("'" + comparisonNDC + "'");
			cffInputList.add(connection.getCatalog());
			cffInputList.add("'" + marketType + "'");
			cffInputList.add("'" + contractHolder + "'");
			cffInputList.add("'" + comparisonBrand + "'");
			cffInputList.add("'" + createdDate + "'");
			cffInputList.add("'" + createdBy + "'");
			if (queryType.equals("loadResult")) {
				cffInputList.add("" + tableRecordStart + "");
				cffInputList.add("" + tableRecordOffset + "");
			}
			return cffInputList;
		} catch (SQLException e) {
			gtnLogger.error(e + " ");
			return cffInputList;
		}

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
		case "brand":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "contractHolder":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "comparisonNDC":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "description":
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
		case "reportProfileLookup_viewName":
			return searchCriteria.getFilterValue1().replace("*", "%");
		case "reportProfileLookup_viewType":
			return searchCriteria.getFilterValue1();
                case "createdDate":
			return searchCriteria.getFilterValue1().replace("*", "%");     
                case "createdBy":
			return searchCriteria.getFilterValue1().replace("*", "%"); 
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

	public int checkUpdateViewRecordCount(GtnWsReportDataSelectionBean dataSelectionBean, int userId)
			throws GtnFrameworkGeneralException {
		int recordCount = 0;
		String query = sqlService.getQuery("getUpdateViewCount");
		Object[] params = { dataSelectionBean.getViewId() };
		GtnFrameworkDataType[] paramsType = {GtnFrameworkDataType.INTEGER};
		List<Integer> resultList = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(query, params, paramsType);
		recordCount = resultList.get(0);
		return recordCount;
	}

	public int checkUpdateViewRecordCountForReportProfile(
			GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean, int userId)
			throws GtnFrameworkGeneralException {
		int recordCount = 0;
		String query = sqlService.getQuery("getUpdateViewCount");
		Object[] params = { reportingDashboardSaveProfileLookupBean.getReportProfileViewId()};
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER };
		List<Integer> resultList = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(query, params, paramsType);
		recordCount = resultList.get(0);
		return recordCount;
	}

	public int checkReportProfileViewRecordCount(
			GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean, int userId)
			throws GtnFrameworkGeneralException {
		int reportProfileCountRecordCount = 0;
		String reportProfileCountQuery = sqlService.getQuery("getViewCount");
		Object[] reportProfileCountParams = { reportingDashboardSaveProfileLookupBean.getReportProfileviewName(),
				reportingDashboardSaveProfileLookupBean.getReportProfileviewType(), userId };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.INTEGER };
		List<Integer> reportProfileCountResultList = (List<Integer>) gtnSqlQueryEngine
				.executeSelectQuery(reportProfileCountQuery, reportProfileCountParams, paramsType);
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

	public int updateReportingViewMaster(GtnWsReportDataSelectionBean dataSelectionBean, int userId)
			throws GtnFrameworkGeneralException {
		List<Object> inputList = new ArrayList<>();
		inputList.add("'" + dataSelectionBean.getViewName() + "'");
		inputList.add("'" + dataSelectionBean.getViewType() + "'");
		inputList.add(userId);
		String viewData = gtnReportJsonService.convertObjectAsJsonString(dataSelectionBean).replaceAll("'", "\\\\");
		inputList.add("'" + viewData + "'");
		inputList.add(dataSelectionBean.getViewId());
		inputList.add(userId);
		String query = sqlService.getQuery(inputList, "updatePrivatePublicView");
		int count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
		return count;
	}

	public int saveReportProfileMaster(
			GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean, int userId)
			throws GtnFrameworkGeneralException {
		List<Object> reportProfileInputList = new ArrayList<>();
		reportProfileInputList.add("'" + reportingDashboardSaveProfileLookupBean.getReportProfileviewName() + "'");
		reportProfileInputList.add("'" + reportingDashboardSaveProfileLookupBean.getReportProfileviewType() + "'");
		reportProfileInputList.add(userId);
		reportProfileInputList.add(userId);
		String reportProfileViewData = gtnReportJsonService
				.convertObjectAsJsonString(reportingDashboardSaveProfileLookupBean).replaceAll("'", "\\\\");
		reportProfileInputList.add("'" + reportProfileViewData + "'");
		reportProfileInputList.add(1);
		String reportProfileQuery = sqlService.getQuery(reportProfileInputList, "insertView");
		int reportProfileCount = gtnSqlQueryEngine.executeInsertOrUpdateQuery(reportProfileQuery);
		return reportProfileCount;
	}

	public int updateReportProfileMaster(
			GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean, int userId)
			throws GtnFrameworkGeneralException {
		List<Object> reportProfileUpdateInputList = new ArrayList<>();
		reportProfileUpdateInputList
				.add("'" + reportingDashboardSaveProfileLookupBean.getReportProfileviewName() + "'");
		reportProfileUpdateInputList
				.add("'" + reportingDashboardSaveProfileLookupBean.getReportProfileviewType() + "'");
		reportProfileUpdateInputList.add(userId);
		String viewData = gtnReportJsonService.convertObjectAsJsonString(reportingDashboardSaveProfileLookupBean)
				.replaceAll("'", "\\\\");
		reportProfileUpdateInputList.add("'" + viewData + "'");
		reportProfileUpdateInputList.add(reportingDashboardSaveProfileLookupBean.getReportProfileViewId());
		reportProfileUpdateInputList.add(userId);
		String query = sqlService.getQuery(reportProfileUpdateInputList, "updatePrivatePublicView");
		int count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
		return count;
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

	public String setFilterForHierarchy(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		String filter = "";
		Map<String, String> dbColumnIdMap = getHierarchyDataBaseColumnIdName();
		Map<String, String> dbColumnDataTypeMap = getHierarchyDataBaseColumnDatatype();
		return setFilterValueList(gtnUIFrameworkWebserviceRequest, filter, dbColumnIdMap, dbColumnDataTypeMap);
	}

	public String setFilterForDataAssumptions(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		String filter = "";
		Map<String, String> dbColumnIdMap = getDataBaseColumnIdName();
		Map<String, String> dbColumnDataTypeMap = getDataBaseColumnDatatype();
		return setFilterValueList(gtnUIFrameworkWebserviceRequest, filter, dbColumnIdMap, dbColumnDataTypeMap);
	}

	public String setFilterValueList(GtnUIFrameworkWebserviceRequest gtnWsRequest, String filter,
			Map<String, String> dbColumnIdMap, Map<String, String> dbColumnDataTypeMap) {
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
		String filterId = Optional.ofNullable(dbColumnIdMap.get(searchCriteria.getFieldId())).isPresent() == true
				? dbColumnIdMap.get(searchCriteria.getFieldId()) : "";
		String filterValue = searchCriteria.getFilterValue1().equals("null")?"":searchCriteria.getFilterValue1();
		String filterDataType = Optional.ofNullable(dbColumnDataTypeMap.get(searchCriteria.getFieldId()))
				.isPresent() == true ? dbColumnDataTypeMap.get(searchCriteria.getFieldId()) : "";
		String filterExpression = searchCriteria.getExpression();
		if (filterDataType.equals("")) {
			return filterString;
		}
		if (!filterDataType.equals("Date")) {
			filterString = filterString + "AND" + " " + filterId + " " + filterExpression + " " + "'" + filterValue
					+ "%'";
		} else {
			String[] splitedArray = filterValue.split(" ");
			filterString = getFilterValueForDateFields(filterString, filterValue, splitedArray, filterId);
		}
		return filterString;
	}

	private String getFilterValueForDateFields(String filter, String filterValue, String[] splitedArray,
			String filterId) {
		String filterString = filter;
		if ("Show all".equals(filterValue)|| filterValue.equals(" - ")) {
			filterString = filterString + "";
		} else if (!filterValue.startsWith(" ") && splitedArray.length >= 3) {

			filterString = filterString + " AND" + " CONVERT(date," + filterId + ") >= CONVERT(date, '"
					+ splitedArray[0] + "')" + " AND" + " CONVERT(date, " + filterId + ") <= CONVERT(date, '"
					+ splitedArray[2] + "')";

		} else if (!filterValue.startsWith(" ") && splitedArray.length < 3) {

			filterString = filterString + " AND" + " CONVERT(date, " + filterId + ") >= CONVERT(date, '"
					+ splitedArray[0] + "')";

		} else {
			filterString = filterString + " AND" + " CONVERT(date, " + filterId + ") <= CONVERT(date, '"
					+ splitedArray[2] + "')";
		}
		return filterString;
	}

	private Map<String, String> getDataBaseColumnIdName() {
		Map<String, String> dbColumnIdMap = new HashMap<>();
		dbColumnIdMap.put("file", "FORECAST_NAME");
		dbColumnIdMap.put("company", "A.COMPANY_NAME");
		dbColumnIdMap.put("businessUnit", "A.BUSINESS_UNIT");
		dbColumnIdMap.put("type", "A.FILE_TYPE");
		dbColumnIdMap.put("version", "VERSION");
		dbColumnIdMap.put("activeFrom", "FROM_PERIOD");		
		dbColumnIdMap.put("toPeriod", "TO_PERIOD");
                dbColumnIdMap.put("activeFile", "ACTIVE_FILE");
		return dbColumnIdMap;
	}

	private Map<String, String> getHierarchyDataBaseColumnIdName() {
		Map<String, String> dbColumnIdMap = new HashMap<>();
		dbColumnIdMap.put("custHierarchyLookupHierName", "c.HIERARCHY_NAME");
		dbColumnIdMap.put("custHierarchyLookupHighestLevel", "a.LEVEL_NO");
		dbColumnIdMap.put("custHierarchyLookupLowestLevel", "b.LEVEL_NO");
		dbColumnIdMap.put("custHierarchyLookupCreatedDate", "c.CREATED_DATE");
		dbColumnIdMap.put("custHierarchyLookupModifiedDate", "c.MODIFIED_DATE");
		dbColumnIdMap.put("productHierarchyName", "c.HIERARCHY_NAME");
		dbColumnIdMap.put("highestLevel", "a.LEVEL_NO");
		dbColumnIdMap.put("lowestLevel", "b.LEVEL_NO");
		dbColumnIdMap.put("createdDate", "c.CREATED_DATE");
		dbColumnIdMap.put("modifiedDate", "c.MODIFIED_DATE");
		dbColumnIdMap.put("viewNameFilter", "VIEW_NAME");
		dbColumnIdMap.put("createdDateFilter", "CREATED_DATE");
		dbColumnIdMap.put("modifiedDateFilter", "MODIFIED_DATE");
		dbColumnIdMap.put("createdByFilter", "LASTNAME+', '+FIRSTNAME");
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
		dbColumnDataTypeMap.put("toPeriod", GtnWsQueryConstants.CONSTANT_DATE);
		dbColumnDataTypeMap.put("activeFile", GtnWsQueryConstants.CONSTANT_STRING);
		return dbColumnDataTypeMap;
	}

	private Map<String, String> getHierarchyDataBaseColumnDatatype() {
		Map<String, String> dbColumnDataTypeMap = new HashMap<>();
		dbColumnDataTypeMap.put("custHierarchyLookupHierName", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("custHierarchyLookupHighestLevel", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("custHierarchyLookupLowestLevel", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("custHierarchyLookupCreatedDate", GtnWsQueryConstants.CONSTANT_DATE);
		dbColumnDataTypeMap.put("custHierarchyLookupModifiedDate", GtnWsQueryConstants.CONSTANT_DATE);
		dbColumnDataTypeMap.put("productHierarchyName", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("highestLevel", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("lowestLevel", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("createdDate", GtnWsQueryConstants.CONSTANT_DATE);
		dbColumnDataTypeMap.put("modifiedDate", GtnWsQueryConstants.CONSTANT_DATE);
		dbColumnDataTypeMap.put("viewNameFilter", GtnWsQueryConstants.CONSTANT_STRING);
		dbColumnDataTypeMap.put("createdDateFilter", GtnWsQueryConstants.CONSTANT_DATE);
		dbColumnDataTypeMap.put("modifiedDateFilter", GtnWsQueryConstants.CONSTANT_DATE);
		dbColumnDataTypeMap.put("createdByFilter", GtnWsQueryConstants.CONSTANT_STRING);
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
			int id = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, paramsType);
			response.getGtnWsGeneralResponse().setSucess(true);
			if(id==0) {
				response.getGtnWsGeneralResponse().setSucess(false);
			}
		} catch (GtnFrameworkGeneralException e) {
			response.getGtnWsGeneralResponse().setSucess(false);
		}
		return response;
	}
        
        public GtnUIFrameworkWebserviceResponse deleteValidation(GtnWsReportDataSelectionBean dataSelectionBean, int userId) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		String query = sqlService.getQuery("deleteValidation");
		Object[] params = { dataSelectionBean.getViewId(), userId };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
		try {
			List<Object> count = (List<Object>)gtnSqlQueryEngine.executeSelectQuery(query, params, paramsType);			
			if(count.get(0).equals(0)) {
				response.getGtnWsGeneralResponse().setSucess(false);
			}
                        else{
                            response.getGtnWsGeneralResponse().setSucess(true);
                        }
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