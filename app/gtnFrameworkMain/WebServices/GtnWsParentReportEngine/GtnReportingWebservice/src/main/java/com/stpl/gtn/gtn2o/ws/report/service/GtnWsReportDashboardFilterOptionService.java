package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardFilterBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsQueryConstants;
import com.stpl.gtn.gtn2o.ws.report.serviceimpl.GtnWsReportDataSelectionSqlGenerateServiceImpl;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

@Service
public class GtnWsReportDashboardFilterOptionService {

	private static final String STRING_CLOSE_BRACES = ") ";

	private static final String STRING_OPEN_BRACES = " (";

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportDashboardFilterOptionService.class);

	public GtnWsReportDashboardFilterOptionService() {
		super();
	}

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsReportSqlService reportSqlService;

	@SuppressWarnings("unchecked")
	public List<Object[]> getCustAndProdLevelValues(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {

		GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean();
		GtnWsReportDashboardBean dashboardBean = gtnWsRequest.getGtnWsReportRequest().getGtnWsReportDashboardBean();

		String custProdLevelQuery = reportSqlService.getQuery("filterOptionCustLevelProdLevelLoadQuery");
		gtnLogger.debug(custProdLevelQuery);
		double hierarchySid = dataSelectionBean.getProductHierarchySid();
		int hierarchyVersionNo = dataSelectionBean.getProductHierarchyVersionNo();
		int forecastLevel = dataSelectionBean.getProductHierarchyForecastLevel();
		if (dashboardBean.getHierarchyType().equals(GtnWsHierarchyType.CUSTOMER)) {
			hierarchySid = dataSelectionBean.getCustomerHierarchySid();
			hierarchyVersionNo = dataSelectionBean.getCustomerHierarchyVersionNo();
			forecastLevel = dataSelectionBean.getCustomerHierarchyForecastLevel();
		}
		Object[] parameterValues = { hierarchySid, hierarchyVersionNo, forecastLevel };
		GtnFrameworkDataType[] dataTypes = { GtnFrameworkDataType.DOUBLE, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER };
		List<Object[]> hierarchyData = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(custProdLevelQuery,
				parameterValues, dataTypes);
		return Optional.ofNullable(hierarchyData).get();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDeductionLevelValues(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean();
		Object[] parameterValues = { dataSelectionBean.getProductRelationshipBuilderSid() };
		GtnFrameworkDataType[] dataTypes = { GtnFrameworkDataType.INTEGER };
		String custProdLevelQuery = reportSqlService.getQuery("filterOptionDeductionLevelLoadQuery");
		gtnLogger.debug(custProdLevelQuery);
		List<Object[]> deductionList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(custProdLevelQuery,
				parameterValues, dataTypes);
		return Optional.ofNullable(deductionList).get();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadCustomerLevelFilter(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnWsReportRequest reportRequest = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest();
		GtnWsReportDashboardFilterBean filterBean = reportRequest.getFilterBean();
		GtnWsReportDataSelectionBean dataSelectionBean = reportRequest.getDataSelectionBean();
		String finalQuery = "";
		List<Object[]> resultList = getTableForSelectedLevel(dataSelectionBean, filterBean);
		if (!resultList.isEmpty()) {
			String userDefined = isDefinedLevel(dataSelectionBean, filterBean);
			boolean isUserDefined = "User Defined".equals(userDefined);
			finalQuery = getUserDefinedQuery(dataSelectionBean, filterBean);
			if (!isUserDefined) {
				GtnForecastHierarchyInputBean forecastInputBean = createInputBean(dataSelectionBean, filterBean);
				GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
				forecastRequest.setInputBean(forecastInputBean);
				gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(forecastRequest);
				finalQuery = getQueryForLoadingFilterDdlb(gtnUIFrameworkWebserviceRequest);
			}
			if (filterBean.getHierarchyType().equals("C") && filterBean.getCustomerLevelNo() != 0) {
				finalQuery = getDynamicProductDeductionLoadQuery(dataSelectionBean, filterBean, isUserDefined,
						finalQuery);
			} else {
				finalQuery = getDynamicCustomerDeductionLoadQuery(dataSelectionBean, filterBean, isUserDefined,
						finalQuery);
			}
			Map<String, String> tableNameMap = dataSelectionBean.getSessionTableMap();
			String replacedQuery = GtnWsReportDataSelectionSqlGenerateServiceImpl.replaceTableNames(finalQuery,
					tableNameMap);
			return (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(replacedQuery);
		}
		return Collections.emptyList();

	}

	@SuppressWarnings("unchecked")
	private List<Object[]> getTableForSelectedLevel(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean) throws GtnFrameworkGeneralException {
		int hierarchyDefinitionSid = 0;
		int levelNo = 0;
		int hierarchyVersionNo = 0;
		if (filterBean.getHierarchyType().equals("C") && filterBean.getCustomerLevelNo() != 0) {
			hierarchyDefinitionSid = (int) dataSelectionBean.getCustomerHierarchySid();
			levelNo = filterBean.getCustomerLevelNo();
			hierarchyVersionNo = dataSelectionBean.getCustomerHierarchyVersionNo();
		} else {
			hierarchyDefinitionSid = (int) dataSelectionBean.getProductHierarchySid();
			levelNo = filterBean.getProductLevelNo();
			hierarchyVersionNo = dataSelectionBean.getProductHierarchyVersionNo();
		}
		Object[] params = { hierarchyDefinitionSid, levelNo, hierarchyVersionNo };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER };
		return (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(reportSqlService.getQuery("report-customer-filter"), params, paramsType);
	}

	private String getDynamicCustomerDeductionLoadQuery(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean, boolean isUserDefined, String query)
			throws GtnFrameworkGeneralException {
		String queryStr = (filterBean.getSelectedCustomerList().isEmpty() || isUserDefined) ? query
				: getProductCustomerFilterQuery(filterBean, dataSelectionBean) + query;
		return getFinalJoinedQuery(queryStr, filterBean, isUserDefined);
	}

	private String getCustDeductionJoinedQuery(String query, GtnWsReportDashboardFilterBean filterBean,
			boolean isUserDefined) {
		StringBuilder queryString = new StringBuilder(query);
		if (!filterBean.getSelectedProductList().isEmpty() && !isUserDefined) {
			queryString.insert(query.lastIndexOf(GtnWsQueryConstants.WHERE),
					" JOIN #HIER_PRODUCT HP ON ST_CCP_HIERARCHY.PROD_HIERARCHY_NO LIKE HP.HIERARCHY_NO + '%'  ");
		}
		if (!filterBean.getSelectedDeductionList().isEmpty() && !isUserDefined) {
			List<String> dedQuery = getDeductionLevelQuery(filterBean);
			queryString.insert(query.lastIndexOf(GtnWsQueryConstants.WHERE), reportSqlService
					.getQuery(GtnWsQueryConstants.DEDUCTION_DYNAMIC_FILTER)
					+ " JOIN RS_CONTRACT rc on rc.CONTRACT_MASTER_SID = cm.CONTRACT_MASTER_SID JOIN RS_CONTRACT_DETAILS rcd on rcd.RS_CONTRACT_SID = rc.RS_CONTRACT_SID and rcd.ITEM_MASTER_SID = im.ITEM_MASTER_SID "
					+ dedQuery.get(0));
			queryString.append(" and ").append(dedQuery.get(1)).append(STRING_OPEN_BRACES)
					.append(filterBean.getSelectedDeductionList().toString().replace("[", "").replace("]", ""))
					.append(STRING_CLOSE_BRACES);
		}
		return queryString.toString();
	}

	private List<String> getDeductionLevelQuery(GtnWsReportDashboardFilterBean filterBean) {
		int deductionLevel = filterBean.getDeductionLevelNo();
		List<String> clause = new ArrayList<>();
		String joinClause = "";
		String whereClause = "";
		switch (deductionLevel) {
		case 1:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.RS_CATEGORY  AND HT.HELPER_TABLE_SID <> 0 ";
			whereClause = GtnWsQueryConstants.HT_HELPER_TABLE_SID_IN;
			break;
		case 2:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.RS_TYPE AND HT.HELPER_TABLE_SID <> 0 ";
			whereClause = GtnWsQueryConstants.HT_HELPER_TABLE_SID_IN;
			break;
		case 3:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.REBATE_PROGRAM_TYPE AND HT.HELPER_TABLE_SID <> 0 ";
			whereClause = GtnWsQueryConstants.HT_HELPER_TABLE_SID_IN;
			break;
		case 4:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC1 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC1'";
			whereClause = GtnWsQueryConstants.HT_HELPER_TABLE_SID_IN;
			break;
		case 5:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC2 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC2'";
			whereClause = GtnWsQueryConstants.HT_HELPER_TABLE_SID_IN;
			break;
		case 6:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC3 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC3'";
			whereClause = GtnWsQueryConstants.HT_HELPER_TABLE_SID_IN;
			break;
		case 7:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC4 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC4'";
			whereClause = GtnWsQueryConstants.HT_HELPER_TABLE_SID_IN;
			break;
		case 8:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC5 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC5'";
			whereClause = GtnWsQueryConstants.HT_HELPER_TABLE_SID_IN;
			break;
		case 9:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC6 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC6'";
			whereClause = GtnWsQueryConstants.HT_HELPER_TABLE_SID_IN;
			break;
		case 10:
			joinClause = "";
			whereClause = "rcd.RS_CONTRACT_SID in";
			break;
		default:
			break;

		}
		clause.add(joinClause);
		clause.add(whereClause);
		return clause;
	}

	private String getDynamicProductDeductionLoadQuery(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean, boolean isUserDefined, String query)
			throws GtnFrameworkGeneralException {
		String queryStr = (filterBean.getSelectedProductList().isEmpty() || isUserDefined) ? query
				: getCustProductFilterQuery(filterBean, dataSelectionBean) + query;
		return getCustDeductionJoinedQuery(queryStr, filterBean, isUserDefined);
	}

	private String getFinalJoinedQuery(String query, GtnWsReportDashboardFilterBean filterBean, boolean isUserDefined) {
		StringBuilder queryString = new StringBuilder(query);
		if (!filterBean.getSelectedCustomerList().isEmpty() && !isUserDefined) {
			queryString.insert(query.lastIndexOf(GtnWsQueryConstants.WHERE),
					"JOIN #HIER_CUST HP ON ST_CCP_HIERARCHY.CUST_HIERARCHY_NO LIKE HP.HIERARCHY_NO + '%'  ");
		}
		if (!filterBean.getSelectedDeductionList().isEmpty() && !isUserDefined) {
			List<String> dedQuery = getDeductionLevelQuery(filterBean);
			queryString.insert(query.lastIndexOf(GtnWsQueryConstants.WHERE), reportSqlService
					.getQuery(GtnWsQueryConstants.DEDUCTION_DYNAMIC_FILTER)
					+ " JOIN RS_CONTRACT rc on rc.CONTRACT_MASTER_SID = cm.CONTRACT_MASTER_SID JOIN RS_CONTRACT_DETAILS rcd on rcd.RS_CONTRACT_SID = rc.RS_CONTRACT_SID and rcd.ITEM_MASTER_SID = im.ITEM_MASTER_SID "
					+ dedQuery.get(0));
			queryString.append(" and ");
			queryString.append(dedQuery.get(1)).append(STRING_OPEN_BRACES)
					.append(filterBean.getSelectedDeductionList().toString().replace("[", "").replace("]", ""))
					.append(STRING_CLOSE_BRACES);
		}
		return queryString.toString();
	}

	private String getProductCustomerFilterQuery(GtnWsReportDashboardFilterBean filterBean,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		String query = reportSqlService.getQuery("customer-dynamic-filter");
		int customerRelationBuilderSid = dataSelectionBean.getCustomerRelationshipBuilderSid();
		List<Object> selectedList = filterBean.getSelectedCustomerList();
		query = query
				.replace(GtnWsQueryConstants.LEVELVALUES, selectedList.toString().replace("[", "").replace("]", ""))
				.replace("?", String.valueOf(customerRelationBuilderSid));
		return query;
	}

	private String getCustProductFilterQuery(GtnWsReportDashboardFilterBean filterBean,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		String query = reportSqlService.getQuery("product-dynamic-filter");
		int prodRelationSid = dataSelectionBean.getProductRelationshipBuilderSid();
		List<Object> selectedList = filterBean.getSelectedProductList();
		query = query
				.replace(GtnWsQueryConstants.LEVELVALUES, selectedList.toString().replace("[", "").replace("]", ""))
				.replace("?", String.valueOf(prodRelationSid));
		return query;
	}

	private String getUserDefinedQuery(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean) {
		int relationVersionNo = 0;
		int relationshipBuilderSid = 0;
		int relationLevelNo = 0;
		if (filterBean.getHierarchyType().equals("C") && filterBean.getCustomerLevelNo() != 0) {
			relationVersionNo = dataSelectionBean.getCustomerRelationshipVersionNo();
			relationshipBuilderSid = dataSelectionBean.getCustomerRelationshipBuilderSid();
			relationLevelNo = filterBean.getCustomerLevelNo();
		} else {
			relationVersionNo = dataSelectionBean.getProductRelationshipVersionNo();
			relationshipBuilderSid = dataSelectionBean.getProductRelationshipBuilderSid();
			relationLevelNo = filterBean.getProductLevelNo();
		}
		List<Object> inputList = new ArrayList<>();
		inputList.add(relationVersionNo);
		inputList.add(relationshipBuilderSid);
		inputList.add(relationLevelNo);
		return reportSqlService.getQuery(inputList, "report-customer-userdefinedlevel");
	}

	public String getQueryForLoadingFilterDdlb(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse relationResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_HIERARCHY_CONTROL
						+ GtnWebServiceUrlConstants.GTN_REPORT_LOAD_MULTISELECT_DDLB,
				gtnUIFrameworkWebserviceRequest,
				getGsnWsSecurityToken(gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest().getUserId(),
						gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest().getSessionId()));
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();
	}

	private GtnWsSecurityToken getGsnWsSecurityToken(String userId, String sessionId) {
		GtnWsSecurityToken wsToken = new GtnWsSecurityToken();
		wsToken.setUserId(userId);
		wsToken.setSessionId(sessionId);
		return wsToken;
	}

	private GtnForecastHierarchyInputBean createInputBean(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean) {
		GtnForecastHierarchyInputBean forecastInputBean = new GtnForecastHierarchyInputBean();
		if (filterBean.getHierarchyType().equals("C") && filterBean.getCustomerLevelNo() != 0) {
			forecastInputBean.setLevelNo(filterBean.getCustomerLevelNo());
			forecastInputBean.setHierarchyDefinitionSid((int) dataSelectionBean.getCustomerHierarchySid());
			forecastInputBean.setHierarchyVersionNo(dataSelectionBean.getCustomerHierarchyVersionNo());
			forecastInputBean.setHierarchyIndicator(filterBean.getHierarchyType());
		} else {
			forecastInputBean.setLevelNo(filterBean.getProductLevelNo());
			forecastInputBean.setHierarchyDefinitionSid((int) dataSelectionBean.getProductHierarchySid());
			forecastInputBean.setHierarchyVersionNo(dataSelectionBean.getProductHierarchyVersionNo());
			forecastInputBean.setHierarchyIndicator(filterBean.getHierarchyType());
		}
		return forecastInputBean;
	}

	@SuppressWarnings("unchecked")
	public String isDefinedLevel(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean) throws GtnFrameworkGeneralException {
		int hierarchyDefinitionSid = 0;
		int levelNo = 0;
		if (filterBean.getHierarchyType().equals("C") && filterBean.getCustomerLevelNo() != 0) {
			hierarchyDefinitionSid = (int) dataSelectionBean.getCustomerHierarchySid();
			levelNo = filterBean.getCustomerLevelNo();
		} else {
			hierarchyDefinitionSid = (int) dataSelectionBean.getProductHierarchySid();
			levelNo = filterBean.getProductLevelNo();
		}
		Object[] params = { hierarchyDefinitionSid, levelNo };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
		List<String> userDefinedList = (List<String>) gtnSqlQueryEngine.executeSelectQuery(
				reportSqlService.getQuery("report-customer-userdefinedlevelvalue"), params, paramsType);
		return userDefinedList.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> loadDeductionFilter(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {
		GtnWsReportDashboardFilterBean filterBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
				.getFilterBean();
		GtnWsReportDataSelectionBean dataSelectionBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
				.getDataSelectionBean();
		int deductionLevel = filterBean.getDeductionLevelNo();
		StringBuilder query = new StringBuilder();
		String selectClause = " HT.DESCRIPTION,HT.HELPER_TABLE_SID ";
		String joinClause = "";
		String udcJoinClause = " JOIN UDCS  UDC ON UDC.MASTER_SID=RC.RS_CONTRACT_SID AND UDC.MASTER_TYPE='RS_CONTRACT' ";

		switch (deductionLevel) {
		case 1:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.RS_CATEGORY  AND HT.HELPER_TABLE_SID <> 0 ";
			udcJoinClause = "";
			break;
		case 2:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.RS_TYPE AND HT.HELPER_TABLE_SID <> 0 ";
			udcJoinClause = "";
			break;
		case 3:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RC.REBATE_PROGRAM_TYPE AND HT.HELPER_TABLE_SID <> 0 ";
			udcJoinClause = "";
			break;
		case 4:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC1 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC1'";
			break;

		case 5:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC2 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC2'";
			break;
		case 6:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC3 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC3'";
			break;
		case 7:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC4 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC4'";
			break;
		case 8:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC5 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC5'";
			break;
		case 9:
			joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC6 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC6'";
			break;
		case 10:
			selectClause = " RC.RS_NAME,RC.RS_CONTRACT_SID ";
			joinClause = "";
			udcJoinClause = "";
			break;
		default:
			break;

		}
		query.append("SELECT ").append(selectClause).append(reportSqlService.getQuery("deductionFilterLoadQuery"));
		query.append(udcJoinClause).append(joinClause);
		if (!filterBean.getSelectedProductList().isEmpty()) {
			String oldCustomerQuery = query.toString();
			query = new StringBuilder();
			oldCustomerQuery = reportSqlService.getQuery("product-dynamic-filter") + oldCustomerQuery
					+ " JOIN #HIER_PRODUCT HP ON ST_CCP_HIERARCHY.PROD_HIERARCHY_NO LIKE HP.HIERARCHY_NO + '%' ";
			int prodRelationSid = dataSelectionBean.getProductRelationshipBuilderSid();
			List<Object> selectedList = filterBean.getSelectedProductList();
			oldCustomerQuery = oldCustomerQuery
					.replace(GtnWsQueryConstants.LEVELVALUES, selectedList.toString().replace("[", "").replace("]", ""))
					.replace("?", String.valueOf(prodRelationSid));
			query.append(oldCustomerQuery);
		}
		if (!filterBean.getSelectedCustomerList().isEmpty()) {
			String oldProductQuery = query.toString();
			query = new StringBuilder();
			oldProductQuery = reportSqlService.getQuery("customer-dynamic-filter") + oldProductQuery
					+ " JOIN #HIER_CUST HC ON ST_CCP_HIERARCHY.CUST_HIERARCHY_NO LIKE HC.HIERARCHY_NO + '%'  ";
			int customerRelationBuilderSid = dataSelectionBean.getCustomerRelationshipBuilderSid();
			List<Object> selectedList = filterBean.getSelectedCustomerList();
			oldProductQuery = oldProductQuery
					.replace(GtnWsQueryConstants.LEVELVALUES, selectedList.toString().replace("[", "").replace("]", ""))
					.replace("?", String.valueOf(customerRelationBuilderSid));
			query.append(oldProductQuery);
		}
		query.append(" GROUP BY ").append(selectClause);
		Map<String, String> tableNameMap = dataSelectionBean.getSessionTableMap();
		String replacedQuery = GtnWsReportDataSelectionSqlGenerateServiceImpl.replaceTableNames(query.toString(),
				tableNameMap);
		return (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(replacedQuery);
	}

	public List<Object[]> getFilteredValues(GtnUIFrameworkWebserviceRequest request)
			throws GtnFrameworkGeneralException {
		GtnWsReportRequest reportRequest = request.getGtnWsReportRequest();
		GtnWsReportDashboardFilterBean filterBean = reportRequest.getFilterBean();
		GtnWsReportDataSelectionBean dataSelectionBean = reportRequest.getDataSelectionBean();
		StringBuilder getCCPSidQuery = new StringBuilder();

		if (filterBean.getSelectedCustomerList().isEmpty() && filterBean.getSelectedProductList().isEmpty()
				&& filterBean.getSelectedDeductionList().isEmpty()) {
			return Collections.emptyList();
		} else {
			String customerLoadQuery = getCustomerCCP(filterBean, dataSelectionBean);
			String productLoadQuery = getProductCCP(filterBean, dataSelectionBean);
			getCCPSidQuery.append(customerLoadQuery).append(productLoadQuery);
			getCCPSidQuery.append(reportSqlService.getQuery("filterOptionsGenerateQuery"));

			StringBuilder joinQuery = new StringBuilder();
			if (!filterBean.getSelectedCustomerList().isEmpty()) {
				joinQuery
						.append(" JOIN #HIER_CUST HC ON ST_CCP_HIERARCHY.CUST_HIERARCHY_NO LIKE HC.HIERARCHY_NO + '%'");
			}
			if (!filterBean.getSelectedProductList().isEmpty()) {
				joinQuery.append(
						" JOIN #HIER_PRODUCT HP ON ST_CCP_HIERARCHY.PROD_HIERARCHY_NO LIKE HP.HIERARCHY_NO + '%'");
			}
			getCCPSidQuery = joinQuery.toString().equals("") ? getCCPSidQuery : getCCPSidQuery.append(joinQuery);

			String finalQuery = getDeductionCCP(getCCPSidQuery, filterBean);
			Map<String, String> tableNameMap = dataSelectionBean.getSessionTableMap();
			String replacedQuery = GtnWsReportDataSelectionSqlGenerateServiceImpl.replaceTableNames(finalQuery,
					tableNameMap);
			return (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(replacedQuery);
		}
	}

	private String getCustomerCCP(GtnWsReportDashboardFilterBean filterBean,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		String customerLoadQuery = "";
		if (!filterBean.getSelectedCustomerList().isEmpty()) {
			customerLoadQuery = getProductCustomerFilterQuery(filterBean, dataSelectionBean);
		}
		return customerLoadQuery;
	}

	private String getProductCCP(GtnWsReportDashboardFilterBean filterBean,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		String productLoadQuery = "";
		if (!filterBean.getSelectedProductList().isEmpty()) {
			productLoadQuery = getCustProductFilterQuery(filterBean, dataSelectionBean);
		}
		return productLoadQuery;
	}

	private String getDeductionCCP(StringBuilder getCCPSidQuery, GtnWsReportDashboardFilterBean filterBean) {
		StringBuilder queryString = getCCPSidQuery.toString().equals("") ? new StringBuilder()
				: new StringBuilder(getCCPSidQuery);
		if (!filterBean.getSelectedDeductionList().isEmpty()) {
			queryString.insert(queryString.lastIndexOf("from"), ",rc.RS_CONTRACT_SID \n");
			List<String> dedQuery = getDeductionLevelQuery(filterBean);
			queryString.append(GtnWsQueryConstants.WHERE);
			queryString.insert(queryString.lastIndexOf(GtnWsQueryConstants.WHERE), reportSqlService
					.getQuery(GtnWsQueryConstants.DEDUCTION_DYNAMIC_FILTER)
					+ " JOIN RS_CONTRACT rc on rc.CONTRACT_MASTER_SID = cm.CONTRACT_MASTER_SID \n"
					+ " JOIN RS_CONTRACT_DETAILS rcd on rcd.RS_CONTRACT_SID = rc.RS_CONTRACT_SID and rcd.ITEM_MASTER_SID = im.ITEM_MASTER_SID "
					+ dedQuery.get(0));
			queryString.append(" cm.inbound_status <> 'D' and \n" + " com.inbound_status <> 'D' and \n"
					+ " im.inbound_status <> 'D' and \n" + "rc.inbound_status <> 'D' and \n"
					+ "rcd.inbound_status <> 'D' and \n");
			queryString.append(dedQuery.get(1)).append(STRING_OPEN_BRACES)
					.append(filterBean.getSelectedDeductionList().toString().replace("[", "").replace("]", ""))
					.append(STRING_CLOSE_BRACES);
		}
		return queryString.toString();
	}
}
