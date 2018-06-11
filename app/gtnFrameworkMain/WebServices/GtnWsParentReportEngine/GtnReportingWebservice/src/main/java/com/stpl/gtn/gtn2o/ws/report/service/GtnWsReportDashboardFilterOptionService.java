package com.stpl.gtn.gtn2o.ws.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
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

		GtnWsReportDataSelectionBean dataSelectionBean = gtnWsRequest.getGtnWsReportRequest().getDataSelectionBean();
		GtnWsReportDashboardBean dashboardBean = gtnWsRequest.getGtnWsReportRequest().getGtnWsReportDashboardBean();

		String custProdLevelQuery = reportSqlService.getQuery("filterOptionCustLevelProdLevelLoadQuery");
		gtnLogger.debug(custProdLevelQuery);
		double hierarchySid = dataSelectionBean.getProductHierarchySid();
		int forecastLevel = dataSelectionBean.getProductHierarchyForecastLevel();
		if (dashboardBean.getHierarchyType().equals(GtnWsHierarchyType.CUSTOMER)) {
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
		GtnUIFrameworkWebserviceResponse relationResponse = client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_HIERARCHY_CONTROL + GtnWebServiceUrlConstants.GTN_REPORT_LOAD_MULTISELECT_DDLB,
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

	public List<Object[]> loadDeductionFilter(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) throws GtnFrameworkGeneralException {
		GtnWsReportDashboardFilterBean filterBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest()
				.getFilterBean();
		GtnWsReportDataSelectionBean dataSelectionBean = gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().getDataSelectionBean();
		int deductionLevel = filterBean.getDeductionLevelNo();
		List<Object[]> deductionValuesList = new ArrayList<>();
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
			System.out.println("query is----->" +query.toString());
			if (!filterBean.getSelectedProductList().isEmpty()) {
				String oldCustomerQuery = query.toString();
				query = new StringBuilder();
				oldCustomerQuery = reportSqlService.getQuery("product-dynamic-filter") + oldCustomerQuery
						+ " JOIN #HIER_PRODUCT HP ON CCP.PROD_HIERARCHY_NO LIKE HP.HIERARCHY_NO+'%' ";
				/*oldCustomerQuery = oldCustomerQuery
						.replace(Constant.LEVEL_VALUES, productFilter.toString().replace("[", "").replace("]", ""))
						.replace(Constant.RELBUILD_SID, projectionDto.getSessionDTO().getProdRelationshipBuilderSid());*/
				query.append(oldCustomerQuery);
			}
			if (!filterBean.getSelectedCustomerList().isEmpty()) {
				String oldProductQuery = query.toString();
				query = new StringBuilder();
				oldProductQuery = reportSqlService.getQuery("customer-dynamic-filter") + oldProductQuery
						+ " JOIN #HIER_CUST HC ON CCP.CUST_HIERARCHY_NO LIKE HC.HIERARCHY_NO+'%' ";
				/*oldProductQuery = oldProductQuery
						.replace(Constant.LEVEL_VALUES, customerFilter.toString().replace("[", "").replace("]", ""))
						.replace(Constant.RELBUILD_SID, projectionDto.getSessionDTO().getCustRelationshipBuilderSid());*/
				query.append(oldProductQuery);
			}
			query.append(" GROUP BY ").append(selectClause);
			if (deductionLevel == 1) {
				String scheduleCategoryQuery = "SELECT HT.DESCRIPTION ,HT.HELPER_TABLE_SID FROM HELPER_TABLE HT WHERE HELPER_TABLE_SID IN ("
						+ "SELECT RS.RS_CATEGORY FROM ST_NM_DISCOUNT_PROJ_MASTER DPM INNER JOIN ST_CCP_HIERARCHY CCP\n"
						+ "ON CCP.CCP_DETAILS_SID = DPM.CCP_DETAILS_SID INNER JOIN RS_CONTRACT RS ON DPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID )"
						+ "GROUP BY HT.HELPER_TABLE_SID, HT.DESCRIPTION ";
				query.delete(0, query.length());
				query.append(scheduleCategoryQuery);
			}
			deductionValuesList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query.toString().replace("ST_CCP_HIERARCHY",
					"ST_CCP_HIERARCHY_" + dataSelectionBean.getSessionId()));
			System.out.println("size-------->" + deductionValuesList.size());
			for (Object[] objects : deductionValuesList) {
				System.out.println("rs_name---->" + objects[0]);
				System.out.println("rs_sid---->" + objects[1]);
			}
		return deductionValuesList;

	}
}
