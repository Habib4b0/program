package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanInformation;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.companyfamilyplan.CfpModel;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.constants.GtnWsCfpQueryContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.cfpresponse.GtnWsCfpReponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;
import com.stpl.gtn.gtn2o.ws.util.GtnWsCommonQueryContants;

@Service()
@Scope(value = "singleton")
public class GtnWsCfpAddService {
	public GtnWsCfpAddService() {
		/**
		 * empty constructor
		 */
	}

	private static final String DATE_FORMAT = "EEE MMM dd kk:mm:ss z yyyy";

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCfpAddService.class);

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String leftTableSearchQuery(GtnUIFrameworkWebserviceRequest cfpCompanyAdditionRequest) {

		boolean isCount = cfpCompanyAdditionRequest.getGtnWsSearchRequest().isCount();
		StringBuilder cfpCompanyAdditionSql = new StringBuilder();
		if (isCount) {
			cfpCompanyAdditionSql.append(gtnWsSqlService.getQuery("getCompanyAdditionSearchCountQuery"));
		} else {
			cfpCompanyAdditionSql.append(gtnWsSqlService.getQuery("getCompanyAdditionSearchResultsQuery"));
		}
		String searchFilter = generateFilterRelatedWhereClauseCMAdditionTab(
				cfpCompanyAdditionRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		if (!searchFilter.isEmpty()) {
			cfpCompanyAdditionSql.append(searchFilter);
		}
		if (!cfpCompanyAdditionRequest.getGtnWsSearchRequest().isCount()) {
			cfpCompanyAdditionSql.append(generateSearchQueryOrderByAndOffset(cfpCompanyAdditionRequest));
		}
		return cfpCompanyAdditionSql.toString();

	}

	public String rightTableSearchQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		boolean isCount = gtnWsRequest.getGtnWsSearchRequest().isCount();
		StringBuilder sql = new StringBuilder();
		GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
		if (isCount) {
			sql.append(gtnWsSqlService.getQuery("getCFPCompanyAdditionRightTableCountQuery"));
		} else {
			sql.append(gtnWsSqlService.getQuery("getCFPCompanyAdditionRightTableSearchQuery"));
		}
		String searchFilter = generateFilterRelatedWhereClauseCMAdditionTab(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		String userId = generalWSRequest.getUserId();
		String sessionId = generalWSRequest.getSessionId();
		if (userId != null && sessionId != null) {
			sql.append(" WHERE USERS_SID = '").append(userId).append("' ");
			sql.append(" AND SESSION_ID = '").append(sessionId).append("' ");
		}
		if (!searchFilter.isEmpty()) {
			sql.append(searchFilter);
		}

		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			sql.append(generateSearchQueryOrderByAndOffset(gtnWsRequest));
		}
		return sql.toString();

	}

	public String getMoveAllLeftQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		StringBuilder sql = new StringBuilder();
		String query = gtnWsSqlService.getQuery("getCFPCompanyAdditionMoveAllLeftQuery");

		sql.append(query);
		String searchFilter = generateFilterRelatedWhereClauseCMAdditionTab(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		if (!searchFilter.isEmpty()) {
			sql.append(searchFilter);
		}

		return sql.toString();

	}

	public String getMoveAllRightQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		StringBuilder sql = new StringBuilder();
		String query = gtnWsSqlService.getQuery("getCFPCompanyAdditionMoveAllRightQuery");
		String searchFilter = generateFilterRelatedWhereClauseCMAdditionTab(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		query = query.replace("[$WHERE_CLAUSE]", searchFilter);
		sql.append(query);
		return sql.toString();

	}

	private String generateFilterRelatedWhereClauseCMAdditionTab(
			List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList) {

		StringBuilder sql = new StringBuilder();
		String field = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String value = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String expersion = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String filterValue2 = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (gtnWebServiceSearchCriteriaList != null && !gtnWebServiceSearchCriteriaList.isEmpty()) {
			for (GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria : gtnWebServiceSearchCriteriaList) {
				String uiColumn = gtnWebServiceSearchCriteria.getFieldId();
				String filterValue1 = gtnWebServiceSearchCriteria.getFilterValue1();
				filterValue2 = gtnWebServiceSearchCriteria.getFilterValue2();
				if (uiColumn.contains("Field")) {
					field = getDbColumForFilterField(field, filterValue1);
				} else if (uiColumn.contains("Value")) {
					expersion = gtnWebServiceSearchCriteria.getExpression();
					value = filterValue1;
				} else {
					sql.append(GtnFrameworkWebserviceConstant.AND_COLUMN);
					sql.append(getWhereClauseForAColumn(gtnWebServiceSearchCriteria.getExpression(),
							getCmAdditionTabColumns(uiColumn, true), filterValue1, filterValue2));
				}
			}
			if (!GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(expersion)
					&& !GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(field)
					&& !GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(value)) {
				sql.append(GtnFrameworkWebserviceConstant.AND_COLUMN);
				sql.append(getWhereClauseForAColumn(expersion, field, value, filterValue2));
			}
		}

		return sql.toString();
	}

	/**
	 * @param field
	 * @param filterValue1
	 * @return
	 */
	private String getDbColumForFilterField(String field, String filterValue1) {
		if ("Company Name".equals(filterValue1)) {
			return "CM.COMPANY_NAME";
		} else if ("Company No".equals(filterValue1)) {
			return "CM.COMPANY_NO";
		} else if ("Company Type".equals(filterValue1)) {
			return "HTYPE.DESCRIPTION";
		} else if ("Company Status".equals(filterValue1)) {
			return "HSTATUS.DESCRIPTION";
		}
		return field;
	}

	private String generateSearchQueryOrderByAndOffset(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		StringBuilder finalQuery = new StringBuilder();
		if (gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList() != null
				&& !gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList().isEmpty()) {
			finalQuery.append(" ORDER BY ");
			finalQuery.append(getSortRelatedOrderByClause(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));

		} else {
			finalQuery.append(" ORDER BY CM.COMPANY_ID ");

		}

		finalQuery.append(GtnCommonUtil.getGeneralOffsetQuery(gtnWsRequest));
		return finalQuery.toString();
	}

	private String getSortRelatedOrderByClause(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String comma = ",";
		String separator = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String columnName;
		StringBuilder sortByQuery = new StringBuilder();
		for (GtnWebServiceOrderByCriteria gtnWebServiceOrderByCriteria : gtnWebServiceOrderByCriteriaList) {
			String property = gtnWebServiceOrderByCriteria.getPropertyId();
			columnName = getCmAdditionTabColumns(property, false);
			sortByQuery.append(separator).append(columnName).append(" ")
					.append(gtnWebServiceOrderByCriteria.getOrderByCriteria());
			separator = comma;
		}
		return sortByQuery.toString();
	}

	private String getCmAdditionTabColumns(String property, boolean isFilter) {
		String companyAdditionColumnName;
		switch (property) {
		case "companyId":
			companyAdditionColumnName = "CM.COMPANY_ID";
			break;
		case "companyNo":
			companyAdditionColumnName = "CM.COMPANY_NO";
			break;
		case "companyName":
			companyAdditionColumnName = "CM.COMPANY_NAME";
			break;
		case "companyStatus":
			companyAdditionColumnName = isFilter ? "HSTATUS.HELPER_TABLE_SID" : "HSTATUS.DESCRIPTION";
			break;
		case "companyType":
			companyAdditionColumnName = isFilter ? "HTYPE.HELPER_TABLE_SID" : "HTYPE.DESCRIPTION";
			break;
		case "tradeClass":
			companyAdditionColumnName = isFilter ? "HTRADE.HELPER_TABLE_SID" : "HTRADE.DESCRIPTION";
			break;
		case "companyCategory":
			companyAdditionColumnName = isFilter ? "HCATEGORY.HELPER_TABLE_SID" : "HCATEGORY.DESCRIPTION";
			break;
		case "companyGroup":
			companyAdditionColumnName = isFilter ? "HGROUP.HELPER_TABLE_SID" : "HGROUP.DESCRIPTION";
			break;
		default:
			companyAdditionColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			break;
		}
		return companyAdditionColumnName;
	}

	private String getWhereClauseForAColumn(String expression, String dbName, String filterValue, String filterValue2) {
		StringBuilder whereSqlBuilder = new StringBuilder();
		SimpleDateFormat dbDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			switch (expression) {
			case "BETWEEN":

				whereSqlBuilder.append(dbName).append(" > '")
						.append(dbDate.format(new SimpleDateFormat(DATE_FORMAT).parse(filterValue))).append("' AND ");
				whereSqlBuilder.append(dbName).append(" < '")
						.append(dbDate.format(new SimpleDateFormat(DATE_FORMAT).parse(filterValue2))).append("'");
				break;
			case "LIKE":
				whereSqlBuilder.append(dbName).append(" ").append(expression).append(" '%")
						.append(filterValue.replace('*', '%')).append("%' ");
				break;
			case "EQUAL":
				whereSqlBuilder.append(dbName).append(" = '").append(filterValue).append("' ");
				break;

			case "EQUALS":
				whereSqlBuilder.append(dbName).append(" ='").append(filterValue).append("' ");
				break;
			case "GREATER":
				whereSqlBuilder.append(dbName).append(" > '").append(filterValue).append("' ");
				break;
			case "LESS":
				whereSqlBuilder.append(dbName).append(" < '").append(filterValue).append("' ");
				break;
			case "GREATER_OR_EQUAL":
				whereSqlBuilder.append(dbName).append(" > '")
						.append(dbDate.format(new SimpleDateFormat(DATE_FORMAT).parse(filterValue))).append("' OR ");
				whereSqlBuilder.append(dbName).append(" = '")
						.append(dbDate.format(new SimpleDateFormat(DATE_FORMAT).parse(filterValue))).append("'");
				break;

			case "LESS_OR_EQUAL":
				whereSqlBuilder.append(dbName).append(" < '")
						.append(dbDate.format(new SimpleDateFormat(DATE_FORMAT).parse(filterValue))).append("' OR ");
				whereSqlBuilder.append(dbName).append(" = '")
						.append(dbDate.format(new SimpleDateFormat(DATE_FORMAT).parse(filterValue))).append("'");
				break;

			default:
				whereSqlBuilder.append(dbName).append(" ").append(expression).append(" '").append(filterValue)
						.append("' ");
				break;
			}
			return whereSqlBuilder.toString();
		} catch (ParseException ex) {
			return whereSqlBuilder.toString();
		}
	}

	public String companiesTabResultTable(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		boolean isCount = gtnWsRequest.getGtnWsSearchRequest().isCount();
		StringBuilder sql = new StringBuilder();
		String query;
		if (isCount) {
			query = gtnWsSqlService.getQuery("getCFPCompaniesResultTableCountQuery");
		} else {
			query = gtnWsSqlService.getQuery("getCFPCompaniesResultTableSearchQuery");
		}
		sql.append(query);

		String searchFilter = generateFilterRelatedWhereClauseCMTab(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		if (!searchFilter.isEmpty()) {
			sql.append(searchFilter);
		}

		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			sql.append(generateSearchQueryOrderByAndOffsetCompaniesTab(gtnWsRequest));
		}
		return sql.toString();

	}

	private String generateFilterRelatedWhereClauseCMTab(
			List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList) {

		StringBuilder sql = new StringBuilder();

		if (!gtnWebServiceSearchCriteriaList.isEmpty()) {
			boolean current = false;
			boolean history = false;
			boolean future = false;
			for (GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria : gtnWebServiceSearchCriteriaList) {
				String uiColumn = gtnWebServiceSearchCriteria.getFieldId();
				String filterValue1 = gtnWebServiceSearchCriteria.getFilterValue1();
				String filterValue2 = gtnWebServiceSearchCriteria.getFilterValue2();
				if ("cfpCompaniesTabRecordType".equals(uiColumn)) {
					current = filterValue1.contains("Current");
					history = filterValue1.contains("History");
					future = filterValue1.contains("Future");
				} else {
					sql.append(GtnFrameworkWebserviceConstant.AND_COLUMN);
					sql.append(getWhereClauseForAColumn(gtnWebServiceSearchCriteria.getExpression(),
							getCompaniesTabColumns(uiColumn, true), filterValue1, filterValue2));
				}
			}
			String freq = getFrequencyQuery(current, history, future);
			if (!freq.isEmpty()) {
				sql.append(freq);
			}
		}

		return sql.toString();
	}

	private String generateSearchQueryOrderByAndOffsetCompaniesTab(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		StringBuilder finalQuery = new StringBuilder();
		if (gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList() != null
				&& !gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList().isEmpty()) {
			finalQuery.append(" ORDER BY ");
			finalQuery.append(getSortRelatedOrderByClauseCompaniesTab(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));

		} else {
			finalQuery.append(" ORDER BY CM.COMPANY_ID ");

		}

		finalQuery.append(GtnCommonUtil.getGeneralOffsetQuery(gtnWsRequest));
		return finalQuery.toString();
	}

	private String getSortRelatedOrderByClauseCompaniesTab(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String comma = ",";
		String separator = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String columnName;
		StringBuilder sortByQuery = new StringBuilder();
		for (GtnWebServiceOrderByCriteria gtnWebServiceOrderByCriteria : gtnWebServiceOrderByCriteriaList) {
			String property = gtnWebServiceOrderByCriteria.getPropertyId();
			columnName = getCompaniesTabColumns(property, false);
			sortByQuery.append(separator).append(columnName).append(" ")
					.append(gtnWebServiceOrderByCriteria.getOrderByCriteria());
			separator = comma;
		}
		return sortByQuery.toString();
	}

	private String getCompaniesTabColumns(String property, boolean isFilter) {
		String companiesTabColumnName;
		switch (property) {
		case GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID:
			companiesTabColumnName = "ICD.COMPANY_ID";
			break;
		case GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO:
			companiesTabColumnName = "ICD.COMPANY_NO";
			break;
		case GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME:
			companiesTabColumnName = "ICD.COMPANY_NAME";
			break;
		case GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS_VALUE:
			companiesTabColumnName = isFilter ? "hCfpStatus.HELPER_TABLE_SID" : "hCfpStatus.DESCRIPTION";
			break;
		case GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_START_DATE:
			companiesTabColumnName = "ICD.CFP_DETAILS_START_DATE";
			break;
		case GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_END_DATE:
			companiesTabColumnName = "ICD.CFP_DETAILS_END_DATE";
			break;
		case GtnFrameworkCommonConstants.COMPANY_STATUS_VALUE:
			companiesTabColumnName = isFilter ? "hCMstatus.HELPER_TABLE_SID" : "hCMstatus.DESCRIPTION";
			break;
		case GtnFrameworkCommonConstants.COMPANY_TYPE_VALUE:
			companiesTabColumnName = isFilter ? "htype.HELPER_TABLE_SID" : "htype.DESCRIPTION";
			break;
		case GtnFrameworkCommonConstants.TRADE_CLASS:
			companiesTabColumnName = "ICD.CFP_DETAILS_TRADE_CLASS";
			break;
		case GtnFrameworkCommonConstants.PROPERTY_COMPANY_CATEGORY:
			companiesTabColumnName = isFilter ? "HCATEGORY.HELPER_TABLE_SID" : "HCATEGORY.DESCRIPTION";
			break;
		case GtnFrameworkCommonConstants.TRADING_PARTNER_CONTRACT_NO:
			companiesTabColumnName = "ICD.TRADING_PARTNER_CONTRACT_NO";
			break;
		case GtnFrameworkCommonConstants.CFP_ATTACHED_DATE:
			companiesTabColumnName = "ICD.CFP_DETAILS_ATTACHED_DATE";
			break;
		case GtnFrameworkCommonConstants.MODIFIED_DATE:
			companiesTabColumnName = "ICD.CFP_DETAILS_MODIFIED_DATE";
			break;
		case GtnFrameworkCommonConstants.MODIFIED_BY:
			companiesTabColumnName = "ICD.CFP_DETAILS_MODIFIED_BY";
			break;
		case GtnFrameworkCommonConstants.CREATED_DATE:
			companiesTabColumnName = "ICD.CFP_DETAILS_CREATED_DATE";
			break;
		case GtnFrameworkCommonConstants.CREATED_BY:
			companiesTabColumnName = "ICD.CFP_DETAILS_CREATED_BY";
			break;
		case GtnFrameworkCommonConstants.CHECK_RECORD_ID:
			companiesTabColumnName = "ICD.CHECK_RECORD";
			break;
		default:
			companiesTabColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			break;
		}
		return companiesTabColumnName;
	}

	private static String getFrequencyQuery(boolean cuurent, boolean history, boolean future) {
		String cfpCompaniesTabFrewuencySql = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (cuurent && history && future) {
			cfpCompaniesTabFrewuencySql += " AND ( CAST(GETDATE() as DATE) BETWEEN CAST(ICD.CFP_DETAILS_START_DATE AS DATE) AND  CAST(ISNULL(ICD.CFP_DETAILS_END_DATE, GETDATE())  AS DATE) ";
			cfpCompaniesTabFrewuencySql += " OR "
					+ " CAST(ICD.CFP_DETAILS_END_DATE AS DATE) <  CAST(GETDATE() as DATE) ";
			cfpCompaniesTabFrewuencySql += " OR "
					+ " CAST(ICD.CFP_DETAILS_START_DATE AS DATE) >  CAST(GETDATE() as DATE) )";
		} else if ((cuurent && history)) {
			cfpCompaniesTabFrewuencySql += " AND (  CAST(GETDATE() as DATE) BETWEEN CAST(ICD.CFP_DETAILS_START_DATE AS DATE)  AND  CAST(ISNULL(ICD.CFP_DETAILS_END_DATE, GETDATE()) AS DATE)  OR CAST(ICD.CFP_DETAILS_END_DATE AS DATE)  <   CAST(GETDATE() as DATE)) ";
		} else if ((history && future)) {
			cfpCompaniesTabFrewuencySql += " AND ("
					+ " CAST(ICD.CFP_DETAILS_END_DATE AS DATE)  <  CAST(GETDATE() as DATE) OR CAST(ICD.CFP_DETAILS_START_DATE AS DATE)  >  CAST(GETDATE() as DATE)) ";
		} else if ((future && cuurent)) {
			cfpCompaniesTabFrewuencySql += " AND (  CAST(GETDATE() as DATE) BETWEEN CAST(ICD.CFP_DETAILS_START_DATE AS DATE)  AND  CAST(ISNULL(ICD.CFP_DETAILS_END_DATE, GETDATE()) AS DATE) OR CAST(ICD.CFP_DETAILS_START_DATE AS DATE)  >   CAST(GETDATE() as DATE)) ";
		} else if (cuurent) {
			cfpCompaniesTabFrewuencySql += " AND  CAST(GETDATE() as DATE) BETWEEN CAST(ICD.CFP_DETAILS_START_DATE AS DATE)  AND  CAST(ISNULL(ICD.CFP_DETAILS_END_DATE, GETDATE()) AS DATE)  ";
		} else if (history) {
			cfpCompaniesTabFrewuencySql += GtnFrameworkWebserviceConstant.AND_COLUMN
					+ " CAST(ICD.CFP_DETAILS_END_DATE AS DATE)  <  CAST(GETDATE() as DATE) ";
		} else if (future) {
			cfpCompaniesTabFrewuencySql += GtnFrameworkWebserviceConstant.AND_COLUMN
					+ " CAST(ICD.CFP_DETAILS_START_DATE AS DATE)  > CAST(GETDATE() as DATE) ";
		}
		return cfpCompaniesTabFrewuencySql;
	}

	public String companiesUpdateColumnQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		StringBuilder sql = new StringBuilder();
		GtnWsCfpRequest gtnWsCfpRequest = gtnWsRequest.getGtnWsCfpRequest();
		String query = gtnWsSqlService.getQuery("getCFPCompaniesUpdateColumnQuery");

		GtnCFamilyPlanCommonUpdateBean inputs = gtnWsCfpRequest.getGtnCFamilyPlan().getUpdateBean();
		String columnName;
		if ("CFP Start Date".equals(inputs.getColumnName())) {
			columnName = "CFP_DETAILS_START_DATE";
		} else if ("CFP End Date".equals(inputs.getColumnName())) {
			columnName = "CFP_DETAILS_END_DATE";
		} else {
			columnName = "CFP_DETAILS_ATTACHED_STATUS";
		}
		query = query.replace("[$UPDATE_CLAUSE]", columnName);
		query = query.replace("[$UPDATE_CLAUSE_VALUE]", String.valueOf(inputs.getValue()));
		String subQuery = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (inputs.getImtdCfpDetailsSid() != null) {
			subQuery += "AND IMTD_CFP_DETAILS_SID = '" + inputs.getImtdCfpDetailsSid() + "' ";
		}
		if ("populate".equals(inputs.getPopulateType())) {
			subQuery += " AND CHECK_RECORD = 1 ";
		}
		query = query.replace("[$WHERE_CLAUSE]", subQuery);
		sql.append(query);
		return sql.toString();

	}

	public void getCfpFetchQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse response) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		GtnCFamilyPlan cfp = new GtnCFamilyPlan();
		GtnWsCfpReponse cfpResponse = new GtnWsCfpReponse();
		GtnCFamilyPlanInformation cfpInfo = new GtnCFamilyPlanInformation();
		cfp.setCfpInfo(cfpInfo);
		try {
			int systemId = gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo().getCfpSid();

			CfpModel masterData = session.get(CfpModel.class, systemId);
			cfpInfo.setCfpId(masterData.getCfpId());
			cfpInfo.setCfpNo(masterData.getCfpNo());
			cfpInfo.setCfpName(masterData.getCfpName());
			cfpInfo.setCfpStatus(masterData.getHelperTableByCfpStatus() == null ? 0
					: masterData.getHelperTableByCfpStatus().getHelperTableSid());
			cfpInfo.setCfpStartDate(masterData.getCfpStartDate());
			cfpInfo.setCfpEndDate(masterData.getCfpEndDate());
			cfpInfo.setCfpType(masterData.getHelperTableByCfpType() == null ? 0
					: masterData.getHelperTableByCfpType().getHelperTableSid());
			cfpInfo.setCfpCategory(masterData.getHelperTableByCfpCategory() == null ? 0
					: masterData.getHelperTableByCfpCategory().getHelperTableSid());
			cfpInfo.setCfpTradeClass(masterData.getHelperTableByCfpTradeClass() == null ? 0
					: masterData.getHelperTableByCfpTradeClass().getHelperTableSid());
			String desig = masterData.getCfpDesignation() == null
					|| GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(masterData.getCfpDesignation())
					|| GtnFrameworkCommonStringConstants.STRING_NULL.equals(masterData.getCfpDesignation()) ? "0"
							: masterData.getCfpDesignation();
			cfpInfo.setCfpDesignation(Integer.valueOf(desig));
			if (masterData.getParentCfpId() != null && masterData.getParentCfpId() > 0) {
				CfpModel parentCfr = session.get(CfpModel.class, masterData.getParentCfpId());
				cfpInfo.setParentCfpId(parentCfr.getCfpId());
				cfpInfo.setParentCfpName(parentCfr.getCfpName());
			} else {
				cfpInfo.setParentCfpId(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				cfpInfo.setParentCfpName(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			}
			cfpInfo.setCreatedBy(gtnWebServiceAllListConfig.getUserIdNameMap().get(masterData.getCreatedBy()));
			cfpInfo.setCreatedDate(masterData.getCreatedDate());
			cfpInfo.setModifiedBy(gtnWebServiceAllListConfig.getUserIdNameMap().get(masterData.getModifiedBy()));
			cfpInfo.setModifiedDate(masterData.getModifiedDate());
			cfpInfo.setSalesInclusion(masterData.getSalesInclusion());
			cfpInfo.setRecordLockStatus(masterData.isRecordLockStatus());
			cfpInfo.setInternalNotes(masterData.getInternalNotes());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in getCfpFetchQuery ", e);
		} finally {
			session.close();
		}
		cfp.setNotesTabList(
				getCfpNotesTabDetails(gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo().getCfpSid()));
		cfpResponse.setGtnCFamilyPlan(cfp);
		response.setGtnWsCfpReponse(cfpResponse);
	}

	public String updateCfpCompaniesTabQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery("getCfpCompaniesTabEditQuery");
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId(),
					gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo().getCfpSid() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER };
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);

			return query;
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in updateCfpCompaniesTabQuery ", e);
		}

	}

	public int getCfpCompaniesTabDeleteQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery("getCfpCompaniesTabDeleteQuery");
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getCfpCompaniesTabDeleteQuery ", e);
		}

	}

	public boolean deleteCfp(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		int systemId = gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo().getCfpSid();
		deleteCfpTempDetails(gtnWsRequest.getGtnWsGeneralRequest());
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CfpModel cfpModel = session.get(CfpModel.class, systemId);
			cfpModel.setInboundStatus('D');
			session.saveOrUpdate(cfpModel);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			logger.error(e.getMessage());
			return false;
		} finally {
			session.close();
		}
	}

	private void deleteCfpTempDetails(GtnWsGeneralRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		String deleteCmInfoQuery = GtnWsCfpQueryContants.CFP_TEMP_TABLE_DELETE_QUERY + gtnWsRequest.getUserId()
				+ " AND SESSION_ID = '" + gtnWsRequest.getSessionId() + "'";
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(deleteCmInfoQuery);
	}

	private List<NotesTabBean> getCfpNotesTabDetails(int systemId) throws GtnFrameworkGeneralException {
		logger.info("Enter getCfpNotesTabDetails");
		String cmNotesTabDetailsSelectQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_SELECT + +systemId
				+ " AND MASTER_TABLE_NAME='CFP_MODEL'";
		List<?> cmNotesDetailsResultList = gtnSqlQueryEngine.executeSelectQuery(cmNotesTabDetailsSelectQuery);
		return GtnCommonUtil.getNotesTabBean(cmNotesDetailsResultList, gtnWebServiceAllListConfig);
	}
}
