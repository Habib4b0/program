package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardFilterBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

@Service
public class GtnWsReportDashboardFilterOptionService {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportDashboardFilterOptionService.class);

	@Autowired
	private GtnWsReportSqlService reportSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private SessionFactory sessionFactory;

	public GtnWsReportDashboardFilterOptionService() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCustAndProdLevelValues(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {

		GtnWsReportDataSelectionBean dataSelectionBean = Optional.ofNullable(gtnWsRequest)
				.map(GtnUIFrameworkWebserviceRequest::getGtnWsReportRequest).map(GtnWsReportRequest::getReportBean)
				.map(GtnWsReportBean::getDataSelectionBean).orElse(new GtnWsReportDataSelectionBean());

		GtnWsReportDashboardBean gtnWsReportDashboardBean = Optional.ofNullable(gtnWsRequest)
				.map(GtnUIFrameworkWebserviceRequest::getGtnWsReportRequest)
				.map(GtnWsReportRequest::getGtnWsReportDashboardBean).orElse(new GtnWsReportDashboardBean());
		String custProdLevelQuery = reportSqlService.getQuery("filterOptionCustLevelProdLevelLoadQuery");
		gtnLogger.debug(custProdLevelQuery);
		double hierarchySid = dataSelectionBean.getProductHierarchySid();
		int forecastLevel = dataSelectionBean.getProductHierarchyForecastLevel();
		if (gtnWsReportDashboardBean.getHierarchyType().equals(GtnWsHierarchyType.CUSTOMER)) {
			hierarchySid = dataSelectionBean.getCustomerHierarchySid();
			forecastLevel = dataSelectionBean.getCustomerHierarchyForecastLevel();
		}
		Object[] parameterValues = { hierarchySid, forecastLevel };
		GtnFrameworkDataType[] dataTypes = { GtnFrameworkDataType.DOUBLE, GtnFrameworkDataType.INTEGER };
		List<Object[]> hierarchyData = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(custProdLevelQuery,
				parameterValues, dataTypes);
		return Optional.ofNullable(hierarchyData).get();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDeductionLevelValues() throws GtnFrameworkGeneralException {
		String custProdLevelQuery = reportSqlService.getQuery("filterOptionDeductionLevelLoadQuery");
		gtnLogger.debug(custProdLevelQuery);
		List<Object[]> deductionList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(custProdLevelQuery);
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
			if (filterBean.getHierarchyType().equals("C")) {
				finalQuery = getDynamicProductLoadQuery(dataSelectionBean, filterBean, isUserDefined, finalQuery);
			} else {
				finalQuery = getDynamicCustomerLoadQuery(dataSelectionBean, filterBean, isUserDefined, finalQuery);
			}
			finalQuery = replaceTableNames(finalQuery, dataSelectionBean, filterBean, isUserDefined);
		}
		List<Object[]> finalResultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(finalQuery);
		System.out.println("size" + finalResultList.size());
		for (Object[] object : finalResultList) {
			System.out.println("name" + object[0]);
			System.out.println("sid" + object[1]);
		}
		return (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(finalQuery);
	}

	private String replaceTableNames(String finalQuery, GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean, boolean isUserDefined) {
		StringBuilder queryString = new StringBuilder(finalQuery);
		System.out.println("selectedProduct" + filterBean.getSelectedProductList().isEmpty());
		System.out.println("isUserDefined" + isUserDefined);
		if (!filterBean.getSelectedProductList().isEmpty() || isUserDefined) {
			queryString.insert(finalQuery.lastIndexOf("WHERE"),
					" JOIN #HIER_PRODUCT HP ON ST_CCP_HIERARCHY.PROD_HIERARCHY_NO LIKE HP.HIERARCHY_NO+'%'  ");
		}
		System.out.println("--------------->" + queryString.toString().replace("ST_CCP_HIERARCHY",
				"ST_CCP_HIERARCHY_" + dataSelectionBean.getSessionId()));
		return queryString.toString().replace("ST_CCP_HIERARCHY",
				"ST_CCP_HIERARCHY_" + dataSelectionBean.getSessionId());
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> getTableForSelectedLevel(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean) throws GtnFrameworkGeneralException {
		int hierarchyDefinitionSid = 0;
		int levelNo = 0;
		int hierarchyVersionNo = 0;
		if (filterBean.getHierarchyType().equals("C")) {
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
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(reportSqlService.getQuery("report-customer-filter"), params, paramsType);
		return resultList;
	}

	private String getDynamicCustomerLoadQuery(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean, boolean isUserDefined, String query) {
		query = (filterBean.getSelectedProductList().isEmpty() || filterBean.getSelectedProductList() == null
				|| isUserDefined) ? query : getFilterQuery(filterBean, dataSelectionBean) + query;
		return query;
	}

	private String getDynamicProductLoadQuery(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean, boolean isUserDefined, String query) {
		query = (filterBean.getSelectedProductList().isEmpty() || filterBean.getSelectedProductList() == null
				|| isUserDefined) ? query : getFilterQuery(filterBean, dataSelectionBean) + query;
		return query;
	}

	private String getFilterQuery(GtnWsReportDashboardFilterBean filterBean,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		int relationshipBuilderSid = 0;
		List<Object> selectedList = new ArrayList<>();
		String query = null;
		if (filterBean.getHierarchyType().equals("C")) {
			relationshipBuilderSid = dataSelectionBean.getProductRelationshipBuilderSid();
			selectedList = filterBean.getSelectedProductList();
			System.out.println("size" + selectedList.size());
			System.out.println("item" + selectedList.get(0));
			query = reportSqlService.getQuery("product-dynamic-filter");
		} else {
			relationshipBuilderSid = dataSelectionBean.getCustomerRelationshipBuilderSid();
			selectedList = filterBean.getSelectedCustomerList();
			query = reportSqlService.getQuery("customer-dynamic-filter");
		}
		Object[] params = { selectedList, relationshipBuilderSid };
		System.out.println("sid" + relationshipBuilderSid);
		System.out.println("*****" + params);
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.IN_LIST, GtnFrameworkDataType.INTEGER };
		return gtnSqlQueryEngine.generateSQLQuery(sessionFactory.openSession(), query, params, paramsType)
				.getQueryString();
	}

	private String getUserDefinedQuery(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean) {
		int relationVersionNo = 0;
		int relationshipBuilderSid = 0;
		int relationLevelNo = 0;
		if (filterBean.getHierarchyType().equals("C")) {
			relationVersionNo = dataSelectionBean.getCustomerRelationshipVersionNo();
			relationshipBuilderSid = dataSelectionBean.getCustomerRelationshipBuilderSid();
			relationLevelNo = filterBean.getCustomerLevelNo();
		} else {
			relationVersionNo = dataSelectionBean.getProductRelationshipVersionNo();
			relationshipBuilderSid = dataSelectionBean.getProductRelationshipBuilderSid();
			relationLevelNo = filterBean.getProductLevelNo();
		}
		Object[] params = { relationVersionNo, relationshipBuilderSid, relationLevelNo };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER };
		return gtnSqlQueryEngine
				.generateSQLQuery(sessionFactory.openSession(),
						reportSqlService.getQuery("report-customer-userdefinedlevel"), params, paramsType)
				.getQueryString();
	}

	public String getQueryForLoadingFilterDdlb(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		/*
		 * GtnUIFrameworkWebserviceResponse relationResponse =
		 * client.callGtnWebServiceUrl(
		 * "http://localhost:8085/GTNWebServices/GtnHierarchyQueryGenerator/reportLoadMultiselectDdlb",
		 * gtnUIFrameworkWebserviceRequest,
		 * getGsnWsSecurityToken(gtnUIFrameworkWebserviceRequest.
		 * getGtnWsGeneralRequest().getUserId(),
		 * gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest().getSessionId
		 * ()));
		 */
		RestTemplate rest = new RestTemplate();
		GtnUIFrameworkWebserviceResponse relationResponseTest = rest.postForObject(
				"http://localhost:8085/GTNWebServices/GtnHierarchyQueryGenerator/reportLoadMultiselectDdlb",
				gtnUIFrameworkWebserviceRequest, GtnUIFrameworkWebserviceResponse.class);
		GtnWsForecastResponse foreCastResponse = relationResponseTest.getGtnWsForecastResponse();
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
		if (filterBean.getHierarchyType().equals("C")) {
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
		if (filterBean.getHierarchyType().equals("C")) {
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

	public List<Object[]> loadDeductionFilter(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		return null;

	}
}
