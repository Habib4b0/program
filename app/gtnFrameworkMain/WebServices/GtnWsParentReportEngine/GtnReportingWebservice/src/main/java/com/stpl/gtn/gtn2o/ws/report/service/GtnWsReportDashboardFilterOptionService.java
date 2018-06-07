package com.stpl.gtn.gtn2o.ws.report.service;

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
	public String loadCustomerLevelFilter(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
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
			finalQuery = getDynamicLoadQuery(dataSelectionBean, filterBean, isUserDefined, finalQuery);
		}
		return finalQuery;
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> getTableForSelectedLevel(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean) throws GtnFrameworkGeneralException {
		int hierarchyDefinitionSid = (int) dataSelectionBean.getCustomerHierarchySid();
		int levelNo = filterBean.getCustomerLevelNo();
		int hierarchyVersionNo = dataSelectionBean.getCustomerHierarchyVersionNo();
		Object[] params = { hierarchyDefinitionSid, levelNo, hierarchyVersionNo };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER };
		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(reportSqlService.getQuery("report-customer-filter"), params, paramsType);
		return resultList;
	}

	private String getDynamicLoadQuery(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean, boolean isUserDefined, String query) {
		query = (filterBean.getSelectedProductList().isEmpty() || filterBean.getSelectedProductList() == null
				|| isUserDefined) ? query : getProductFilterQuery(filterBean, dataSelectionBean) + query;
		return query;
	}

	private String getProductFilterQuery(GtnWsReportDashboardFilterBean filterBean,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		String query = reportSqlService.getQuery("product-dynamic-filter");
		int relationshipBuilderSid = dataSelectionBean.getProductRelationshipBuilderSid();
		Object[] params = { filterBean.getSelectedProductList(), relationshipBuilderSid };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.IN_LIST, GtnFrameworkDataType.INTEGER };
		return gtnSqlQueryEngine.generateSQLQuery(sessionFactory.openSession(), query, params, paramsType)
				.getQueryString();
	}

	private String getUserDefinedQuery(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean) {
		int relationVersionNo = dataSelectionBean.getCustomerRelationshipVersionNo();
		int relationshipBuilderSid = dataSelectionBean.getCustomerRelationshipBuilderSid();
		int relationLevelNo = filterBean.getCustomerLevelNo();
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
		GtnUIFrameworkWebserviceResponse relationResponse = client.callGtnWebServiceUrl(
				"http://localhost:8085/GTNWebServices/GtnHierarchyQueryGenerator/reportLoadMultiselectDdlb",
				gtnUIFrameworkWebserviceRequest,
				getGsnWsSecurityToken(gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest().getUserId(),
						gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest().getSessionId()));
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
		forecastInputBean.setLevelNo(filterBean.getCustomerLevelNo());
		forecastInputBean.setHierarchyDefinitionSid((int) dataSelectionBean.getCustomerHierarchySid());
		forecastInputBean.setHierarchyVersionNo(dataSelectionBean.getCustomerHierarchyVersionNo());
		forecastInputBean.setHierarchyIndicator(filterBean.getHierarchyType());
		return forecastInputBean;
	}

	@SuppressWarnings("unchecked")
	public String isDefinedLevel(GtnWsReportDataSelectionBean dataSelectionBean,
			GtnWsReportDashboardFilterBean filterBean) throws GtnFrameworkGeneralException {
		int hierarchyDefinitionSid = (int) dataSelectionBean.getCustomerHierarchySid();
		int levelNo = filterBean.getCustomerLevelNo();
		Object[] params = { hierarchyDefinitionSid, levelNo };
		GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
		List<String> userDefinedList = (List<String>) gtnSqlQueryEngine.executeSelectQuery(
				reportSqlService.getQuery("report-customer-userdefinedlevelvalue"), params, paramsType);
		return userDefinedList.get(0);
	}

}
