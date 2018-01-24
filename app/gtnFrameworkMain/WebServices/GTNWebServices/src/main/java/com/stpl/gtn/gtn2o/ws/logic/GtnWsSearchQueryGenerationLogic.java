package com.stpl.gtn.gtn2o.ws.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

public class GtnWsSearchQueryGenerationLogic {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsSearchQueryGenerationLogic.class);

	private GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig;
	private GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest;
	private static final String AND_OPERATOR = " AND ";
	private static final String WHERE_OPERATOR = " WHERE ";

	public GtnWsSearchQueryGenerationLogic(GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig,
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		this.gtnWebServiceSearchQueryConfig = gtnWebServiceSearchQueryConfig;
		this.gtnUIFrameworkWebserviceRequest = gtnUIFrameworkWebserviceRequest;
	}

	public GtnWsSearchQueryGenerationLogic(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		this.gtnUIFrameworkWebserviceRequest = gtnUIFrameworkWebserviceRequest;
	}

	public GtnWsSearchQueryGenerationLogic() {
		super();
	}

	public String generateSearchQuery() {
		StringBuilder finalQuery = generateSearchQuerySelectClause();

		finalQuery.append(gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().isCount()
				? gtnWebServiceSearchQueryConfig.getCountQuery() : gtnWebServiceSearchQueryConfig.getSearchQuery());

		finalQuery.append(generateSearchQueryWhereClause());

		if (!gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().isCount()) {
			finalQuery.append(generateSearchQueryOrderByAndOffset());
		}

		if (gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().isCount()
				&& !gtnWebServiceSearchQueryConfig.getCountAliasAtEnd().isEmpty()) {
			finalQuery.append(" ) ").append(gtnWebServiceSearchQueryConfig.getCountAliasAtEnd());
		}
		return finalQuery.toString();
	}

	public StringBuilder generateSearchQuerySelectClause() {
		boolean isCount = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().isCount();
		StringBuilder finalQuery = new StringBuilder();

		if (isCount) {
			if (gtnWebServiceSearchQueryConfig.getCountQuerySelectClause().isEmpty()) {
				finalQuery.append(" Select distinct count(1) ");
			} else {
				finalQuery.append(gtnWebServiceSearchQueryConfig.getCountQuerySelectClause());
			}
			return finalQuery;
		}

		List<Object> visibleColumnList = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest()
				.getSearchColumnNameList();

		if (visibleColumnList != null && !visibleColumnList.isEmpty()) {
			finalQuery.append("Select distinct ");
			for (int i = 0; i < visibleColumnList.size() - 1; i++) {
				String currentColumn = visibleColumnList.get(i).toString();
				appendSelectColumn(currentColumn, finalQuery);
				finalQuery.append(" as ");
				finalQuery.append(currentColumn);
				finalQuery.append(" , ");
			}
			appendSelectColumn(visibleColumnList.get(visibleColumnList.size() - 1).toString(), finalQuery);
			finalQuery.append(" as ");
			finalQuery.append(visibleColumnList.get(visibleColumnList.size() - 1).toString());
			finalQuery.append("  ");
			appendSelectColumnForSorting(finalQuery);
		}
		return finalQuery;
	}

	public void appendSelectColumn(String currentColumn, StringBuilder finalQuery) {
		finalQuery.append(getTableColumnForField(currentColumn));
	}

	public String generateSearchQueryWhereClause() {
		StringBuilder finalQuery = new StringBuilder();
		boolean isWhereAppended = false;
		List<String> whereClauseList = gtnWebServiceSearchQueryConfig.getWhereClauseList();
		if (whereClauseList != null && !whereClauseList.isEmpty()) {
			finalQuery.append(WHERE_OPERATOR);
			isWhereAppended = true;
			for (int i = 0; i < whereClauseList.size() - 1; i++) {
				finalQuery.append(whereClauseList.get(i)).append(AND_OPERATOR);
			}
			finalQuery.append(whereClauseList.get(whereClauseList.size() - 1));
		}

		finalQuery.append(generateFilterRelatedWhereClause(
				gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList(),
				isWhereAppended));

		return finalQuery.toString();
	}

	public String generateSearchQueryOrderByAndOffset() {
		StringBuilder finalQuery = new StringBuilder();
		generateOrderBy(finalQuery);
		if (finalQuery.length() > 0) {
			finalQuery.append(generateOffset());
		} else {
			return GtnFrameworkCommonStringConstants.STRING_EMPTY;
		}

		return finalQuery.toString();
	}

	public void getuserRelatedOrderBy(StringBuilder finalQuery) {
		finalQuery.append(" ORDER BY ");
		List<GtnWebServiceOrderByCriteria> orderByClause = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest()
				.getGtnWebServiceOrderByCriteriaList();
		for (int i = 0; i < orderByClause.size() - 1; i++) {
			finalQuery.append(getTableColumnForWhereClause(orderByClause.get(i).getPropertyId())).append(" ")
					.append(orderByClause.get(i).getOrderByCriteria()).append(",");
		}
		finalQuery.append(getTableColumnForWhereClause(orderByClause.get(orderByClause.size() - 1).getPropertyId()))
				.append(" ").append(orderByClause.get(orderByClause.size() - 1).getOrderByCriteria());
	}

	private String getTableColumnForField(String fieldName) {
		Map<String, GtnWsColumnDetailsConfig> componetMap = gtnWebServiceSearchQueryConfig.getFieldToColumnDetailsMap();
		if (componetMap.containsKey(fieldName)) {
			return componetMap.get(fieldName).getColumnNameForSelectClause();
		} else {
			throw new IllegalArgumentException("Details Not Found for ---" + fieldName);
		}
	}

	private String getTableColumnForWhereClause(String fieldName) {
		Map<String, GtnWsColumnDetailsConfig> componentMap = gtnWebServiceSearchQueryConfig
				.getFieldToColumnDetailsMap();
		if (componentMap.containsKey(fieldName)) {
			return componentMap.get(fieldName).getDataType()
					.equals(GtnFrameworkWebserviceConstant.HELPER)
							? componentMap.get(fieldName).getHelperTableMappedColumnNameForOrderByClause()
							: componentMap.get(fieldName).getColumnNameForWhereClause();

		} else {
			throw new IllegalArgumentException("Details Not Found for ---" + fieldName);
		}
	}

	public String generateFilterRelatedWhereClause(List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList,
			boolean isWhereAppended) {
		boolean isWhereCondition = isWhereAppended;
		StringBuilder finalWhere = new StringBuilder("");
		if (gtnWebServiceSearchCriteriaList != null && !gtnWebServiceSearchCriteriaList.isEmpty()) {
			Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = gtnWebServiceSearchQueryConfig
					.getFieldToColumnDetailsMap();
			StringBuilder whereSqlBuilder = new StringBuilder();
			for (int i = 0; i < gtnWebServiceSearchCriteriaList.size() - 1; i++) {
				if (!isWhereCondition) {
					whereSqlBuilder.append(WHERE_OPERATOR).append(" ");
					isWhereCondition = true;
				} else {
					whereSqlBuilder.append(AND_OPERATOR).append(" ");
				}
				appendForFilter(gtnWebServiceSearchCriteriaList.get(i), fieldToColumnDetailsMap, whereSqlBuilder);
			}
			whereSqlBuilder.append(isWhereCondition ? AND_OPERATOR : WHERE_OPERATOR).append(" ");
			appendForFilter(gtnWebServiceSearchCriteriaList.get(gtnWebServiceSearchCriteriaList.size() - 1),
					fieldToColumnDetailsMap, whereSqlBuilder);

			finalWhere.append(whereSqlBuilder.toString());
		}

		finalWhere.append(generateRecordTypeWhereClause(gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest(),
				isWhereCondition));
		return finalWhere.toString();
	}

	private void appendForFilter(GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria,
			Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap, StringBuilder whereSqlBuilder) {
		try {
			String uiColumn = gtnWebServiceSearchCriteria.getFieldId();
			GtnWsColumnDetailsConfig gtnWebServiceColumnDetailsConfig = fieldToColumnDetailsMap.get(uiColumn);
			String dbName = gtnWebServiceColumnDetailsConfig.getColumnNameForWhereAndOrderByClause();
			dbName = castDateInQuery(gtnWebServiceColumnDetailsConfig, dbName);
			generateSqlBasedOnExpression(gtnWebServiceSearchCriteria, whereSqlBuilder, dbName);
		} catch (ParseException ex) {
			logger.debug(ex.getMessage());
		}
	}

	private String castDateInQuery(GtnWsColumnDetailsConfig gtnWebServiceColumnDetailsConfig, String dbName) {
		String dbColumnName = dbName;
		if (gtnWebServiceColumnDetailsConfig.getDataType().equalsIgnoreCase("Date")) {
			String apppendOperator = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			if (dbColumnName.contains(GtnFrameworkCommonStringConstants.GREATER_THAN)) {
				apppendOperator = GtnFrameworkCommonStringConstants.GREATER_THAN;
				dbColumnName = dbColumnName.replace(GtnFrameworkCommonStringConstants.GREATER_THAN,
						GtnFrameworkCommonStringConstants.STRING_EMPTY);
			} else if (dbColumnName.contains(GtnFrameworkCommonStringConstants.LESS_THAN)) {
				apppendOperator = GtnFrameworkCommonStringConstants.LESS_THAN;
				dbColumnName = dbColumnName.replace(GtnFrameworkCommonStringConstants.LESS_THAN,
						GtnFrameworkCommonStringConstants.STRING_EMPTY);
			}
			dbColumnName = GtnFrameworkWebserviceConstant.CAST + dbColumnName
					+ GtnFrameworkCommonStringConstants.AS_DATE + apppendOperator;
		}
		return dbColumnName;
	}

	public void generateSqlBasedOnExpression(GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria,
			StringBuilder whereSqlBuilder, String dbName) throws ParseException {
		SimpleDateFormat dbDate = new SimpleDateFormat("yyyy-MM-dd");
		switch (gtnWebServiceSearchCriteria.getExpression()) {
		case "BETWEEN":

			whereSqlBuilder.append(dbName).append(" > '")
					.append(dbDate.format(new SimpleDateFormat(GtnFrameworkWebserviceConstant.EEE_MMM_DD_KKMMSS_Z_YYYY)
							.parse(gtnWebServiceSearchCriteria.getFilterValue1())))
					.append("' AND ");
			whereSqlBuilder.append(dbName).append(" < '")
					.append(dbDate.format(new SimpleDateFormat(GtnFrameworkWebserviceConstant.EEE_MMM_DD_KKMMSS_Z_YYYY)
							.parse(gtnWebServiceSearchCriteria.getFilterValue2())))
					.append("'");
			break;
		case "LIKE": 
			whereSqlBuilder.append(dbName).append(" ").append(gtnWebServiceSearchCriteria.getExpression()).append(" '")
					.append(gtnWebServiceSearchCriteria.getFilterValue1().replace('*', '%').replace("_", "[_]")).append("' ");
			break;
		case "EQUAL":
			whereSqlBuilder.append(dbName).append(" = '").append(gtnWebServiceSearchCriteria.getFilterValue1())
					.append("' ");
			break;

		case "EQUALS":
			whereSqlBuilder.append(dbName).append(" = '").append(gtnWebServiceSearchCriteria.getFilterValue1())
					.append("' ");
			break;
		case "GREATER":
			whereSqlBuilder.append(dbName).append(" > '").append(gtnWebServiceSearchCriteria.getFilterValue1())
					.append("' ");
			break;
		case "LESS":
			whereSqlBuilder.append(dbName).append(" < '").append(gtnWebServiceSearchCriteria.getFilterValue1())
					.append("' ");
			break;
		case "GREATER_OR_EQUAL":
			whereSqlBuilder.append(dbName).append(" > '")
					.append(dbDate.format(new SimpleDateFormat(GtnFrameworkWebserviceConstant.EEE_MMM_DD_KKMMSS_Z_YYYY)
							.parse(gtnWebServiceSearchCriteria.getFilterValue1())))
					.append("' OR ");
			whereSqlBuilder.append(dbName).append(" = '")
					.append(dbDate.format(new SimpleDateFormat(GtnFrameworkWebserviceConstant.EEE_MMM_DD_KKMMSS_Z_YYYY)
							.parse(gtnWebServiceSearchCriteria.getFilterValue1())))
					.append("'");
			break;

		case "LESS_OR_EQUAL":
			whereSqlBuilder.append(dbName).append(" < '")
					.append(dbDate.format(new SimpleDateFormat(GtnFrameworkWebserviceConstant.EEE_MMM_DD_KKMMSS_Z_YYYY)
							.parse(gtnWebServiceSearchCriteria.getFilterValue1())))
					.append("' OR ");
			whereSqlBuilder.append(dbName).append(" = '")
					.append(dbDate.format(new SimpleDateFormat(GtnFrameworkWebserviceConstant.EEE_MMM_DD_KKMMSS_Z_YYYY)
							.parse(gtnWebServiceSearchCriteria.getFilterValue1())))
					.append("'");
			break;

		default:
			whereSqlBuilder.append(dbName).append(" ").append(gtnWebServiceSearchCriteria.getExpression()).append(" '")
					.append(gtnWebServiceSearchCriteria.getFilterValue1()).append("' ");
			break;
		}
	}

	private void generateOrderBy(StringBuilder finalQuery) {
		List<GtnWebServiceOrderByCriteria> orderByClause = gtnWebServiceSearchQueryConfig.getOrderByClause();
		if (gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList() != null
				&& !gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()
						.isEmpty()) {
			getuserRelatedOrderBy(finalQuery);
		} else if (orderByClause != null && !orderByClause.isEmpty()) {
			appendOrderBy(finalQuery, orderByClause);
		}
	}

	public void appendOrderBy(List<GtnWebServiceOrderByCriteria> orderByClause,
			GtnFrameworkQueryGeneratorBean finalQueryBean) {
		for (int i = 0; i < orderByClause.size() - 1; i++) {
			finalQueryBean.addOrderByClauseBean(orderByClause.get(i).getPropertyId(),
					orderByClause.get(i).getOrderByCriteria());
		}
		finalQueryBean.addOrderByClauseBean(orderByClause.get(orderByClause.size() - 1).getPropertyId(),
				orderByClause.get(orderByClause.size() - 1).getOrderByCriteria());
	}

	public void appendOrderBy(StringBuilder finalQuery, List<GtnWebServiceOrderByCriteria> orderByClause) {
		finalQuery.append(" ORDER BY ");
		for (int i = 0; i < orderByClause.size() - 1; i++) {
			finalQuery.append(orderByClause.get(i).getPropertyId()).append(" ")
					.append(orderByClause.get(i).getOrderByCriteria()).append(",");
		}
		finalQuery.append(orderByClause.get(orderByClause.size() - 1).getPropertyId()).append(" ")
				.append(orderByClause.get(orderByClause.size() - 1).getOrderByCriteria());

	}

	public void appendOffset(StringBuilder offsetQuery, int startRecord, int offset) {
		offsetQuery.append(" OFFSET ");
		offsetQuery.append(startRecord);
		offsetQuery.append(" ROWS FETCH NEXT ");
		offsetQuery.append(offset);
		offsetQuery.append(" ROWS ONLY;");
	}

	private String generateOffset() {
		int startRecord = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getTableRecordStart();
		int offset = 1;
		if (gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getTableRecordOffset() != 0) {
			offset = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getTableRecordOffset();
		}
		StringBuilder offsetQuery = new StringBuilder();
		appendOffset(offsetQuery, startRecord, offset);
		return offsetQuery.toString();
	}

	private String generateRecordTypeWhereClause(GtnWsSearchRequest gtnWsSearchRequest, boolean isWhereClause) {
		String finalTempleate = "";
		if (gtnWsSearchRequest.getGtnWsRecordTypeBean() != null) {
			Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = gtnWebServiceSearchQueryConfig
					.getFieldToColumnDetailsMap();
			GtnWsColumnDetailsConfig startDateConfig = fieldToColumnDetailsMap
					.get(gtnWsSearchRequest.getGtnWsRecordTypeBean().getStartDateColumn());
			GtnWsColumnDetailsConfig endDateConfig = fieldToColumnDetailsMap
					.get(gtnWsSearchRequest.getGtnWsRecordTypeBean().getEndDateColumn());
			String startDate = GtnFrameworkWebserviceConstant.CAST + startDateConfig.getTableAlias() + "."
					+ startDateConfig.getDbColumnName() + GtnFrameworkCommonStringConstants.AS_DATE;
			String endDate = GtnFrameworkWebserviceConstant.CAST + endDateConfig.getTableAlias() + "."
					+ endDateConfig.getDbColumnName() + GtnFrameworkCommonStringConstants.AS_DATE;

			boolean cuurent = gtnWsSearchRequest.getGtnWsRecordTypeBean().isCurrent();
			boolean history = gtnWsSearchRequest.getGtnWsRecordTypeBean().isHistory();
			boolean future = gtnWsSearchRequest.getGtnWsRecordTypeBean().isFuture();
			String appendClause = isWhereClause ? AND_OPERATOR : WHERE_OPERATOR;
			if (cuurent && history && future) {
				finalTempleate += appendClause + " ( CAST(GETDATE() AS DATE) BETWEEN " + startDate
						+ GtnFrameworkWebserviceConstant.AND_CAST_ISNULL + endDate + ", GETDATE()) as DATE) ";
				finalTempleate += " OR " + " " + endDate + " <   CAST(GETDATE() AS DATE) ";
				finalTempleate += " OR " + " " + startDate + " >   CAST(GETDATE() AS DATE) )";
			} else if (cuurent && history) {
				finalTempleate += appendClause + "  (   CAST(GETDATE() AS DATE) BETWEEN " + startDate
						+ GtnFrameworkWebserviceConstant.AND_CAST_ISNULL + endDate + ", GETDATE()) as DATE) OR "
						+ endDate + " <   CAST(GETDATE() AS DATE)) ";
			} else if (future && history) {
				finalTempleate += appendClause + "  (" + " " + endDate + " <   CAST(GETDATE() AS DATE) OR " + startDate
						+ " >   CAST(GETDATE() AS DATE)) ";
			} else if (cuurent && future) {
				finalTempleate += appendClause + "  (  CAST(GETDATE() AS DATE) BETWEEN " + startDate
						+ GtnFrameworkWebserviceConstant.AND_CAST_ISNULL + endDate + ", GETDATE()) as DATE) OR "
						+ startDate + " >   CAST(GETDATE() AS DATE)) ";
			} else if (cuurent) {
				finalTempleate += appendClause + "   CAST(GETDATE() AS DATE) BETWEEN " + startDate
						+ GtnFrameworkWebserviceConstant.AND_CAST_ISNULL + endDate + ", GETDATE()) as DATE) ";
			} else if (history) {
				finalTempleate += appendClause + " " + endDate + " <   CAST(GETDATE() AS DATE) ";
			} else if (future) {
				finalTempleate += appendClause + " " + startDate + " >   CAST(GETDATE() AS DATE) ";
			}
		}

		return finalTempleate;
	}

	private void appendSelectColumnForSorting(StringBuilder finalQuery) {
		List<GtnWebServiceOrderByCriteria> orderByClause = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest()
				.getGtnWebServiceOrderByCriteriaList();
		if (orderByClause != null && !orderByClause.isEmpty()) {
			for (int i = 0; i < orderByClause.size() - 1; i++) {
				finalQuery.append(",");
				finalQuery.append(getTableColumnForWhereClause(orderByClause.get(i).getPropertyId())).append(" ")
						.append(orderByClause.get(i).getOrderByCriteria());
			}
			finalQuery.append(",");
			finalQuery.append(getTableColumnForWhereClause(orderByClause.get(orderByClause.size() - 1).getPropertyId()))
					.append(" ");

		}

	}
}
