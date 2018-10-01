package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.ifpresponse.GtnWsIfpReponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;
import com.stpl.gtn.gtn2o.ws.util.GtnWsCommonQueryContants;

@Service()
@Scope(value = "singleton")
public class GtnWsIfpAddService {
	public GtnWsIfpAddService() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsIfpAddService.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public String ifpLeftTableSearchQuery(GtnUIFrameworkWebserviceRequest ifpLeftTableRequest) {

		boolean isCount = ifpLeftTableRequest.getGtnWsSearchRequest().isCount();
		StringBuilder ifpLeftTableSql = new StringBuilder();
		String searchFilter = generateFilterRelatedWhereClauseIMAdditionTab(
				ifpLeftTableRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		
		if (isCount) {
			ifpLeftTableSql.append(gtnWsSqlService.getQuery("getItemAdditionSearchCountQuery"));
		} else {
			ifpLeftTableSql.append(gtnWsSqlService.getQuery("getItemAdditionSearchResultsQuery"));
		}
		
		if (!searchFilter.isEmpty()) {
			ifpLeftTableSql.append(searchFilter);
		}
		if (!ifpLeftTableRequest.getGtnWsSearchRequest().isCount()) {
			ifpLeftTableSql.append(generateSearchQueryOrderByAndOffset(ifpLeftTableRequest));
		}
		return ifpLeftTableSql.toString();

	}

	public String ifpRightTableSearchQuery(GtnUIFrameworkWebserviceRequest ifpRightTableRequest) {

		boolean isCount = ifpRightTableRequest.getGtnWsSearchRequest().isCount();
		StringBuilder ifpRightTableSql = new StringBuilder();
		GtnWsGeneralRequest generalWSRequest = ifpRightTableRequest.getGtnWsGeneralRequest();
		
		String searchFilter = generateFilterRelatedWhereClauseIMAdditionTab(
				ifpRightTableRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		
		if (isCount) {
			ifpRightTableSql.append(gtnWsSqlService.getQuery("getIFPItemAdditionRightTableCountQuery"));
		} else {
			ifpRightTableSql.append(gtnWsSqlService.getQuery("getIFPItemAdditionRightTableSearchQuery"));
		}
		
		String userId = generalWSRequest.getUserId();
		String sessionId = generalWSRequest.getSessionId();
		if (userId != null && sessionId != null) {
			ifpRightTableSql.append(" WHERE USERS_SID = '").append(userId).append("' ");
			ifpRightTableSql.append(" AND SESSION_ID = '").append(sessionId).append("' ");
		}
		if (!searchFilter.isEmpty()) {
			ifpRightTableSql.append(searchFilter);
		}

		if (!ifpRightTableRequest.getGtnWsSearchRequest().isCount()) {
			ifpRightTableSql.append(generateSearchQueryOrderByAndOffset(ifpRightTableRequest));
		}
		return ifpRightTableSql.toString();

	}

	public String getIfpMoveAllLeftQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		StringBuilder sql = new StringBuilder();
		String query = gtnWsSqlService.getQuery("getIfpItemAdditionMoveAllLeftQuery");

		sql.append(query);
		String searchFilter = generateFilterRelatedWhereClauseIMAdditionTab(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		if (!searchFilter.isEmpty()) {
			sql.append(searchFilter);
		}

		return sql.toString();

	}

	public String getIfpMoveAllRightQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		StringBuilder sql = new StringBuilder();
		String query = gtnWsSqlService.getQuery("getIfpItemsAdditionMoveAllRightQuery");

		String searchFilter = generateFilterRelatedWhereClauseIMAdditionTab(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		query = query.replace("[$WHERE_CLAUSE]", searchFilter);
		sql.append(query);
		return sql.toString();

	}

	private String generateFilterRelatedWhereClauseIMAdditionTab(
			List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList) {

		StringBuilder sql = new StringBuilder();
		String field = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String field2 = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String value = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String expersion = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (gtnWebServiceSearchCriteriaList != null && !gtnWebServiceSearchCriteriaList.isEmpty()) {
			for (GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria : gtnWebServiceSearchCriteriaList) {
				String uiColumn = gtnWebServiceSearchCriteria.getFieldId();
				String filterValue1 = gtnWebServiceSearchCriteria.getFilterValue1();

				if (uiColumn.contains("Field")) {
					field = getDbColumnForFilterField(filterValue1);
				} else if (uiColumn.contains("Value")) {
					expersion = gtnWebServiceSearchCriteria.getExpression();
					value = filterValue1;
				} else {
					sql.append(GtnFrameworkWebserviceConstant.AND_COLUMN);
					sql.append(getWhereClauseForAColumn(gtnWebServiceSearchCriteria.getExpression(),
							getImAdditionTabColumns(uiColumn), field2, filterValue1));
				}
			}
			if (!GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(expersion)
					&& !GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(field)
					&& !GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(value)) {
				sql.append(GtnFrameworkWebserviceConstant.AND_COLUMN);
				sql.append(getWhereClauseForAColumn(expersion, field, field2, value));
			}
		}

		return sql.toString();
	}

	public String getDbColumnForFilterField(String filterValue1) {
		String ifpItemAdditionFilterField = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (null != filterValue1) {
			switch (filterValue1) {
			case "Item ID":
				ifpItemAdditionFilterField = "IM.ITEM_ID";
				break;
			case "Item Desc":
				ifpItemAdditionFilterField = "IM.ITEM_DESC";
				break;
			case "Item No":
				ifpItemAdditionFilterField = "IM.ITEM_NO";
				break;
			case "Item Name":
				ifpItemAdditionFilterField = "IM.ITEM_NAME";
				break;
			case "Item Status":
				ifpItemAdditionFilterField = GtnFrameworkWebserviceConstant.STADESCRIPTION;
				break;
			case "Strength":
				ifpItemAdditionFilterField = GtnFrameworkWebserviceConstant.STRDESCRIPTION;
				break;
			case "Therapeutic Class":
				ifpItemAdditionFilterField = GtnFrameworkWebserviceConstant.TCDESCRIPTION;
				break;
			case "Form":
				ifpItemAdditionFilterField = GtnFrameworkWebserviceConstant.FODESCRIPTION;
				break;
			case "Brand":
				ifpItemAdditionFilterField = GtnFrameworkWebserviceConstant.BMBRAND_NAME;
				break;
			case "UOM":
				ifpItemAdditionFilterField = "UOM.DESCRIPTION";
				break;
			default:
				break;
			}

		}
		return ifpItemAdditionFilterField;
	}

	private String generateSearchQueryOrderByAndOffset(GtnUIFrameworkWebserviceRequest ifpAdditionTabWsRequest) {
		StringBuilder ifpAdditionTabFinalQuery = new StringBuilder();
		if (ifpAdditionTabWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList() != null
				&& !ifpAdditionTabWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList().isEmpty()) {
			ifpAdditionTabFinalQuery.append(" ORDER BY ");
			ifpAdditionTabFinalQuery.append(getSortRelatedOrderByClause(
					ifpAdditionTabWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
		} else {
			ifpAdditionTabFinalQuery.append(" ORDER BY IM.ITEM_NO ");

		}

		ifpAdditionTabFinalQuery.append(GtnCommonUtil.getGeneralOffsetQuery(ifpAdditionTabWsRequest));
		return ifpAdditionTabFinalQuery.toString();
	}

	private String getSortRelatedOrderByClause(List<GtnWebServiceOrderByCriteria> ifpAdditionTabOrderByCriteriaList) {
		String comma = ",";
		String separator = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		StringBuilder ifpAdditionTabSortByQuery = new StringBuilder();
		for (GtnWebServiceOrderByCriteria ifpAdditionTabOrderByCriteria : ifpAdditionTabOrderByCriteriaList) {
			String property = ifpAdditionTabOrderByCriteria.getPropertyId();
			ifpAdditionTabSortByQuery.append(separator).append(getImAdditionTabColumns(property)).append(' ')
					.append(ifpAdditionTabOrderByCriteria.getOrderByCriteria());
			separator = comma;
		}
		return ifpAdditionTabSortByQuery.toString();
	}

	public String getImAdditionTabColumns(String property) {
		String ifpItemAdditionColumnName;
		switch (property) {
		case "itemId":
			ifpItemAdditionColumnName = "IM.ITEM_ID";
			break;
		case "itemNo":
			ifpItemAdditionColumnName = "IM.ITEM_NO";
			break;
		case "itemName":
			ifpItemAdditionColumnName = "IM.ITEM_NAME";
			break;
		case "itemDesc":
			ifpItemAdditionColumnName = "IM.ITEM_DESC";
			break;
		case "itemStatus":
			ifpItemAdditionColumnName = GtnFrameworkWebserviceConstant.STADESCRIPTION;
			break;
		case "form":
			ifpItemAdditionColumnName = GtnFrameworkWebserviceConstant.FODESCRIPTION;
			break;
		case "strength":
			ifpItemAdditionColumnName = GtnFrameworkWebserviceConstant.STRDESCRIPTION;
			break;
		case "therapeuticClass":
			ifpItemAdditionColumnName = GtnFrameworkWebserviceConstant.TCDESCRIPTION;
			break;
		case "brand":
			ifpItemAdditionColumnName = GtnFrameworkWebserviceConstant.BMBRAND_NAME;
			break;
		case "UOM":
			ifpItemAdditionColumnName = "UOM.DESCRIPTION";
			break;
		case "therapeutic Class":
			ifpItemAdditionColumnName = GtnFrameworkWebserviceConstant.TCDESCRIPTION;
			break;
		default:
			ifpItemAdditionColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			break;
		}
		return ifpItemAdditionColumnName;
	}

	public String getWhereClauseForAColumn(String expersion, String field, String field2, String value) {
		StringBuilder itemsWhereClauseSql = new StringBuilder();
		SimpleDateFormat dbDate = new SimpleDateFormat("yyyy-MM-dd");
		switch (expersion) {
		case "BETWEEN":
			itemsWhereClauseSql.append(field).append(" < '").append(dbDate.format(value)).append("' AND ");
			itemsWhereClauseSql.append(field).append(" > '").append(dbDate.format(field2)).append("' ");
			break;
		case "LIKE":
			itemsWhereClauseSql.append(field).append(' ').append(expersion).append(" '%")
					.append(value.replace('*', '%')).append("%' ");
			break;
		case "EQUAL":
			itemsWhereClauseSql.append(field).append(" = '").append(value).append("' ");
			break;

		case "EQUALS":
			itemsWhereClauseSql.append(field).append(" ='").append(value).append("' ");
			break;
		case "GREATER":
			itemsWhereClauseSql.append(field).append(" > '").append(value).append("' ");
			break;
		case "LESS":
			itemsWhereClauseSql.append(field).append(" < '").append(value).append("' ");
			break;
		default:
			itemsWhereClauseSql.append(field).append(' ').append(expersion).append(" '").append(value).append("' ");
			break;
		}
		return itemsWhereClauseSql.toString();
	}

	public String itemsTabResultTable(GtnUIFrameworkWebserviceRequest itemsTabResultRequest) {

		boolean isCount = itemsTabResultRequest.getGtnWsSearchRequest().isCount();
		StringBuilder itemsTabResultSql = new StringBuilder();
		String query;
		if (isCount) {
			query = gtnWsSqlService.getQuery("getIfpItemsResultTableCountQuery");
		} else {
			query = gtnWsSqlService.getQuery("getIfpItemsResultTableSearchQuery");
		}
		itemsTabResultSql.append(query);

		String searchFilter = generateFilterRelatedWhereClauseItemsTab(
				itemsTabResultRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList());
		if (!searchFilter.isEmpty()) {
			itemsTabResultSql.append(searchFilter);
		}

		if (!itemsTabResultRequest.getGtnWsSearchRequest().isCount()) {
			itemsTabResultSql.append(generateSearchQueryOrderByAndOffsetItemsTab(itemsTabResultRequest));
		}
		return itemsTabResultSql.toString();

	}

	private String generateFilterRelatedWhereClauseItemsTab(
			List<GtnWebServiceSearchCriteria> itemsTabSearchCriteriaList) {

		StringBuilder itemsTabSql = new StringBuilder();

		if (!itemsTabSearchCriteriaList.isEmpty()) {
			boolean current = false;
			boolean history = false;
			boolean future = false;
			for (GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria : itemsTabSearchCriteriaList) {
				String uiColumn = gtnWebServiceSearchCriteria.getFieldId();
				String filterValue1 = gtnWebServiceSearchCriteria.getFilterValue1();
				String filterValue2 = gtnWebServiceSearchCriteria.getFilterValue2();
				if ("ifpItemsTabRecordType".equals(uiColumn)) {
					current = filterValue1.contains("Current");
					history = filterValue1.contains("History");
					future = filterValue1.contains("Future");
				} else {
					itemsTabSql.append(GtnFrameworkWebserviceConstant.AND_COLUMN);
					itemsTabSql.append(getWhereClauseForAColumn(gtnWebServiceSearchCriteria.getExpression(),
							getItemsTabColumns(uiColumn), filterValue2, filterValue1));
				}
			}
			String freq = getFrequencyQuery(current, history, future);
			if (!freq.isEmpty()) {
				itemsTabSql.append(freq);
			}
		}

		return itemsTabSql.toString();
	}

	private String generateSearchQueryOrderByAndOffsetItemsTab(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		StringBuilder itemsTabFinalQuery = new StringBuilder();
		if (gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList() != null
				&& !gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList().isEmpty()) {
			itemsTabFinalQuery.append(" ORDER BY ");
			itemsTabFinalQuery.append(getSortRelatedOrderByClauseItemsTab(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));

		} else {
			itemsTabFinalQuery.append(" ORDER BY ICD.ITEM_NO ");

		}

		itemsTabFinalQuery.append(GtnCommonUtil.getGeneralOffsetQuery(gtnWsRequest));
		return itemsTabFinalQuery.toString();
	}

	private String getSortRelatedOrderByClauseItemsTab(List<GtnWebServiceOrderByCriteria> itemsTabOrderByCriteriaList) {
		String comma = ",";
		String separator = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		StringBuilder itemsTabSortByQuery = new StringBuilder();
		for (GtnWebServiceOrderByCriteria gtnWebServiceOrderByCriteria : itemsTabOrderByCriteriaList) {
			String property = gtnWebServiceOrderByCriteria.getPropertyId();
			itemsTabSortByQuery.append(separator).append(getItemsTabColumns(property)).append(' ')
					.append(gtnWebServiceOrderByCriteria.getOrderByCriteria());
			separator = comma;
		}
		return itemsTabSortByQuery.toString();
	}

	public String getItemsTabColumns(String property) {
		String idpItemsTabColumnName;
		switch (property) {
		case "itemNo":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_ITEM_NO;
			break;
		case "itemName":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_ITEM_NAME;
			break;
		case "itemDesc":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_ITEM_DESC;
			break;
		case "itemFamilyPlanStatus":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_IFP_DETAILS_ATTACHED_STATUS;
			break;
		case "itemFamilyPlanStartDate":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_IFP_DETAILS_START_DATE;
			break;
		case "itemFamilyPlanEndDate":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_IFP_DETAILS_END_DATE;
			break;
		case "itemStatusValue":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.STADESCRIPTION;
			break;
		case "from":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.FODESCRIPTION;
			break;
		case "strength":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.STRDESCRIPTION;
			break;
		case "therapeuticClass":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.TCDESCRIPTION;
			break;
		case "brand":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.BMBRAND_NAME;
			break;
		case "ifpAttachedDate":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_IFP_DETAILS_ATTACHED_DATE;
			break;
		case "modifiedDate":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_IFP_DETAILS_MODIFIED_DATE;
			break;
		case "modifiedBy":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_IFP_DETAILS_MODIFIED_BY;
			break;
		case "createdDate":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_IFP_DETAILS_CREATED_DATE;
			break;
		case "createdBy":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_IFP_DETAILS_CREATED_BY;
			break;
		case "checkRecordId":
			idpItemsTabColumnName = GtnFrameworkWebserviceConstant.ICD_CHECK_BOX;
			break;
		default:
			idpItemsTabColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			break;
		}
		return idpItemsTabColumnName;
	}

	public static String getFrequencyQuery(boolean cuurent, boolean history, boolean future) {
		String ifpItemsTabFrequencySql = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (cuurent && history && future) {
			ifpItemsTabFrequencySql += " AND ( CAST(GETDATE() as DATE) BETWEEN CAST(ICD.ifp_DETAILS_START_DATE AS DATE) AND CAST(ISNULL(ICD.ifp_DETAILS_END_DATE, GETDATE()) as DATE) ";
			ifpItemsTabFrequencySql += " OR " + " CAST(ICD.ifp_DETAILS_END_DATE as DATE) <  CAST(GETDATE() as DATE) ";
			ifpItemsTabFrequencySql += " OR "
					+ " CAST(ICD.ifp_DETAILS_START_DATE AS DATE) >  CAST(GETDATE() as DATE) )";
		} else if ((cuurent && history)) {
			ifpItemsTabFrequencySql += " AND (  CAST(GETDATE() as DATE) BETWEEN CAST(ICD.ifp_DETAILS_START_DATE AS DATE) AND CAST(ISNULL(ICD.ifp_DETAILS_END_DATE, GETDATE()) as DATE) OR CAST(ICD.ifp_DETAILS_END_DATE as DATE) <  CAST(GETDATE() as DATE)) ";
		} else if ((history && future)) {
			ifpItemsTabFrequencySql += " AND ("
					+ " CAST(ICD.ifp_DETAILS_END_DATE AS DATE) <  CAST(GETDATE() as DATE) OR CAST(ICD.ifp_DETAILS_START_DATE AS DATE) >  CAST(GETDATE() as DATE)) ";
		} else if ((future && cuurent)) {
			ifpItemsTabFrequencySql += " AND (  CAST(GETDATE() as DATE) BETWEEN CAST(ICD.ifp_DETAILS_START_DATE AS DATE) AND CAST(ISNULL(ICD.ifp_DETAILS_END_DATE, GETDATE()) AS DATE) OR CAST(ICD.ifp_DETAILS_START_DATE AS DATE) >  CAST(GETDATE() as DATE)) ";
		} else if (cuurent) {
			ifpItemsTabFrequencySql += " AND  CAST(GETDATE() as DATE) BETWEEN CAST(ICD.ifp_DETAILS_START_DATE AS DATE) AND CAST(ISNULL(ICD.ifp_DETAILS_END_DATE, GETDATE()) AS DATE) ";
		} else if (history) {
			ifpItemsTabFrequencySql += GtnFrameworkWebserviceConstant.AND_COLUMN
					+ " CAST(ICD.ifp_DETAILS_END_DATE AS DATE) <  CAST(GETDATE() as DATE) ";
		} else if (future) {
			ifpItemsTabFrequencySql += GtnFrameworkWebserviceConstant.AND_COLUMN
					+ " CAST(ICD.ifp_DETAILS_START_DATE AS DATE) >  CAST(GETDATE() as DATE) ";
		}
		return ifpItemsTabFrequencySql;
	}

	public String companiesUpdateColumnQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		StringBuilder sql = new StringBuilder();
		String query = gtnWsSqlService.getQuery("getIfpItemsUpdateColumnQuery");

		GtnIFamilyPlanBean inputs = gtnWsRequest.getGtnWsIfpRequest().getGtnIFamilyPlan();
		String columnName;
		if ("IFP Start Date".equals(inputs.getUpdateBean().getColumnName())) {
			columnName = "IFP_DETAILS_START_DATE";
		} else if ("IFP End Date".equals(inputs.getUpdateBean().getColumnName())) {
			columnName = "IFP_DETAILS_END_DATE";
		} else {
			columnName = "IFP_DETAILS_ATTACHED_STATUS";
		}
		query = query.replace("[$UPDATE_CLAUSE]", columnName);
		query = query.replace("[$UPDATE_CLAUSE_VALUE]", String.valueOf(inputs.getUpdateBean().getValue()));
		String subQuery = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (inputs.getUpdateBean().getImtdIfpDetailsSid() != null
				&& !inputs.getUpdateBean().getImtdIfpDetailsSid().isEmpty()) {
			subQuery += "AND IMTD_IFP_DETAILS_SID = '" + inputs.getUpdateBean().getImtdIfpDetailsSid() + "' ";
		}
		if ("populate".equals(inputs.getUpdateBean().getPopulateType())) {
			subQuery += " AND CHECK_BOX = 1 ";
		}
		query = query.replace("[$WHERE_CLAUSE]", subQuery);
		sql.append(query);
		return sql.toString();

	}

	public void getIfpFetchQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse response) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		GtnIFamilyPlanBean cfp = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		cfp.setIfpInfo(ifpInfo);
		try {
			int systemId = gtnWsRequest.getGtnWsIfpRequest().getGtnIFamilyPlan().getIfpInfo().getIfpSid();
			IfpModel ifpModel = session.get(IfpModel.class, systemId);
			ifpInfo.setIfpId(ifpModel.getIfpId());
			ifpInfo.setIfpNo(ifpModel.getIfpNo());
			ifpInfo.setIfpName(ifpModel.getIfpName());
			ifpInfo.setIfpType(ifpModel.getHelperTableByIfpType() == null ? 0
					: ifpModel.getHelperTableByIfpType().getHelperTableSid());
			ifpInfo.setIfpCategory(ifpModel.getHelperTableByIfpCategory() == null ? 0
					: ifpModel.getHelperTableByIfpCategory().getHelperTableSid());
			ifpInfo.setIfpDesignation(ifpModel.getHelperTableByIfpDesignation() == null ? 0
					: ifpModel.getHelperTableByIfpDesignation().getHelperTableSid());
			ifpInfo.setIfpStatus(ifpModel.getHelperTableByIfpStatus() == null ? 0
					: ifpModel.getHelperTableByIfpStatus().getHelperTableSid());
			ifpInfo.setIfpStartDate(ifpModel.getIfpStartDate());
			ifpInfo.setIfpEndDate(ifpModel.getIfpEndDate());
			ifpInfo.setParentIfpId(ifpModel.getParentIfpId());
			ifpInfo.setParentIfpName(ifpModel.getParentIfpName());
			ifpInfo.setRecordLockStatus(ifpModel.isRecordLockStatus());
			ifpInfo.setCreatedBy(gtnWebServiceAllListConfig.getUserIdNameMap().get(ifpModel.getCreatedBy()));
			ifpInfo.setCreatedDate(ifpModel.getCreatedDate());
			ifpInfo.setModifiedBy(gtnWebServiceAllListConfig.getUserIdNameMap().get(ifpModel.getModifiedBy()));
			ifpInfo.setModifiedDate(ifpModel.getModifiedDate());
			ifpInfo.setInternalNotes(ifpModel.getInternalNotes());
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in getIfpFetchQuery ", e);

		} finally {
			session.close();
		}

		GtnWsIfpReponse cfpResponse = new GtnWsIfpReponse();
		cfp.setNotesTabList(
				getIfpNotesTabDetails(gtnWsRequest.getGtnWsIfpRequest().getGtnIFamilyPlan().getIfpInfo().getIfpSid()));
		cfpResponse.setGtnIFamilyPlan(cfp);
		response.setGtnWsIfpReponse(cfpResponse);

	}

	public int updateIfpCompaniesTabQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {

		String query = gtnWsSqlService.getQuery("getIfpItemsTabEditQuery");
		Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
				gtnWsRequest.getGtnWsGeneralRequest().getSessionId(),
				gtnWsRequest.getGtnWsIfpRequest().getGtnIFamilyPlan().getIfpInfo().getIfpSid() };
		GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.INTEGER };
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);

	}

	public int getIfpTabDeleteQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {

		String query = gtnWsSqlService.getQuery("getIfpCompaniesTabDeleteQuery");
		Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
				gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
		GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);

	}

	public boolean deleteIfpModel(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		int systemId = gtnWsRequest.getGtnWsIfpRequest().getGtnIFamilyPlan().getIfpInfo().getIfpSid();
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			IfpModel ifpModel = session.get(IfpModel.class, systemId);
			ifpModel.setInboundStatus('D');
			session.saveOrUpdate(ifpModel);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			return false;

		} finally {
			session.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getIfpNotesTabDetails(int systemId) throws GtnFrameworkGeneralException {
		logger.info("Enter getIfpNotesTabDetails");
		String ifpNotesTabDetailsSelectQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_SELECT + systemId
				+ " AND MASTER_TABLE_NAME='IFP_MODEL'";
		List<Object[]> imNotesDetailsResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(ifpNotesTabDetailsSelectQuery);
		return GtnCommonUtil.getNotesTabBean(imNotesDetailsResultList, gtnWebServiceAllListConfig);
	}

	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getCfpNotesTabAttachDetails(int systemId) throws GtnFrameworkGeneralException {
		logger.info("Enter getifpNotesTabAttachDetails");
		String ifpNotesTabAttachDetailsSelectQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_ATTACHMENT_SELECT + +systemId
				+ " AND MASTER_TABLE_NAME='IFP_MODEL'";
		List<Object[]> imNotesDetailsResultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(ifpNotesTabAttachDetailsSelectQuery);
		return GtnCommonUtil.getNotesTabBean(imNotesDetailsResultList, gtnWebServiceAllListConfig);
	}
	
	public int checkAllItems(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {

		String query = gtnWsSqlService.getQuery("getIfpItemsCheckAllQuery");
		boolean isCheckAll = (boolean) gtnWsRequest.getGtnWsIfpRequest().getGtnIFamilyPlan().getUpdateBean().getValue();
		Object[] params = { isCheckAll ? "1" : "0", gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
				gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
		GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);

	}
}
