package com.stpl.gtn.gtn2o.ws.transaction.service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsCallEtlService;

@Service
public class GtnWsTransactionReprocessIOService {

	public GtnWsTransactionReprocessIOService() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsTransactionReprocessIOService.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;
	@Autowired
	private GtnWsCallEtlService gtnWsCallEtlService;

	public void getReprocessRecords(GtnWsTransactionRequest gtnWsTransactionRequest)
			throws GtnFrameworkGeneralException {

		String whereCondition;
		List<String> selectedColumn = new ArrayList<>();
		String stagingColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String invalidColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String inavlidTableName = gtnWsTransactionRequest.getTableName();

		for (Field object : gtnWebServiceAllListConfig
				.getTransctionDynamicClass(
						GtnFrameworkWebserviceConstant.COMSTPLGTNGTN2OWSENTITYTRANSACTION + inavlidTableName)
				.getDeclaredFields()) {
			selectedColumn.add(object.getName());
		}
		selectedColumn = removeUnwantedColumns(selectedColumn, inavlidTableName);

		if (gtnWsTransactionRequest.isReprocessFlag()) {
			List<String> selectColumns = new ArrayList<>();
			selectColumns.addAll(selectedColumn);
			replaceBatchIdColumn(selectColumns);
			stagingColumnName = removeUnwantedColumnForStagingtable(selectedColumn, inavlidTableName);
			invalidColumnName = Arrays.toString(selectColumns.toArray());
		}
		try {
			ClassMetadata classMetadata = gtnSqlQueryEngine.getSessionFactory()
					.getClassMetadata(gtnWebServiceAllListConfig.getTransctionDynamicClass(
							GtnFrameworkWebserviceConstant.COMSTPLGTNGTN2OWSENTITYTRANSACTION + inavlidTableName));
			if (!gtnWsTransactionRequest.getReprocessIds().isEmpty()) {
				List<String> reprocessid = gtnWsTransactionRequest.getReprocessIds();
				whereCondition = getWhereQueries(gtnWsTransactionRequest, classMetadata, reprocessid,
						GtnFrameworkOperatorType.IN.getOperaterType(), gtnWsTransactionRequest.getStagInsertColumns());

			} else {
				whereCondition = getWhereClauseForReprocess(gtnWsTransactionRequest);
				if (!gtnWsTransactionRequest.getUncheckedIds().isEmpty()) {
					List<String> unCheckedid = gtnWsTransactionRequest.getUncheckedIds();
					whereCondition += getWhereQueries(gtnWsTransactionRequest, classMetadata, unCheckedid,
							GtnFrameworkOperatorType.NOT_IN.getOperaterType(),
							gtnWsTransactionRequest.getStagInsertColumns());
				}
			}

			if (gtnWsTransactionRequest.isReprocessFlag()) {
				String insertQuery = "INSERT INTO " + gtnWsTransactionRequest.getStagingTableName() + "("
						+ stagingColumnName.substring(1, stagingColumnName.length() - 1) + ") " + " SELECT "
						+ invalidColumnName.substring(1, invalidColumnName.length() - 1) + " FROM " + inavlidTableName
						+ " WHERE " + whereCondition;
				LOGGER.debug("insertQuery----" + insertQuery);
				int updatedRecord = gtnSqlQueryEngine.executeInsertAndUpdateHqlQuery(insertQuery);
				LOGGER.debug("updatedRecord----" + updatedRecord);
				updateConditionAndCallEtlService(gtnWsTransactionRequest, whereCondition, inavlidTableName,
						inavlidTableName);
			} else {
				updateConditionFlag(gtnWsTransactionRequest, whereCondition, inavlidTableName);
			}

		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Error in search query : ", ex);
		}
	}

	private String removeUnwantedColumnForStagingtable(List<String> selectedColumn, String inavlidTableName) {
		if ("IvldAccrualInbound".equals(inavlidTableName)) {
			int index = selectedColumn.indexOf("accrualIntfid");
			selectedColumn.remove("accrualIntfid");
			selectedColumn.add(index, "accrualInboundInterfaceId");

		}
		return Arrays.toString(selectedColumn.toArray());

	}

	private List<String> removeUnwantedColumns(List<String> selectedColumn, String inavlidTableName) {
		final List<String> notCommonColumnsList = Arrays.asList("reasonForFailure", "intfInsertedDate", "errorCode",
				"errorField", "reprocessedFlag", "checkRecord");
		List<String> selectedColumnList = new ArrayList<>(selectedColumn);

		for (String columnName : notCommonColumnsList) {
			selectedColumnList.remove(columnName);
		}
		selectedColumnList = selectedColumnList.subList(1, selectedColumnList.size());

		if ("IvldItemIdentifier".equals(inavlidTableName)) {
			selectedColumnList.remove("identifierStatus");
		} else if ("IvldActualMaster".equals(inavlidTableName)) {
			selectedColumnList.remove("settlementMethodNo");
		} else if ("IvldAccrualInbound".equals(inavlidTableName)) {
			selectedColumnList.remove("glCompanyMasterSid");
			selectedColumnList.remove("glCompanyName");
			selectedColumnList.remove("programType");
			selectedColumnList.remove("programNo");
			selectedColumnList.remove("postingIndicator");
		}
		return selectedColumnList;
	}

	private void updateConditionFlag(GtnWsTransactionRequest gtnWsTransactionRequest, String whereCondition,
			String tableName) throws GtnFrameworkGeneralException {
		StringBuilder conditionalUpdate = new StringBuilder(" UPDATE ");
		conditionalUpdate.append(tableName).append(" SET ");
		Object[] updateColumn = gtnWsTransactionRequest.getStagUpdateColumns();
		Object[] updateColumnValues = gtnWsTransactionRequest.getStagUpdateColumnsValues();
		if (updateColumn.length != 0) {
			for (int i = 0; i < updateColumn.length; i++) {
				conditionalUpdate.append(updateColumn[i]).append("= '").append(updateColumnValues[i]).append("' ");
				if (i != updateColumn.length - 1) {
					conditionalUpdate.append(',');
				}
			}
			conditionalUpdate.append(" where ").append(whereCondition);
			int updatedRecordCount = gtnSqlQueryEngine.executeInsertAndUpdateHqlQuery(conditionalUpdate.toString());
			LOGGER.debug("record updated" + updatedRecordCount);
		}
	}

	private String getWhereClauseForReprocess(GtnWsTransactionRequest gtnWsTransactionRequest) {

		Map<String, String> columnDataTypeMap = new HashMap<>();
		StringBuilder whereClause = new StringBuilder();

		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			for (Field object : gtnWebServiceAllListConfig
					.getTransctionDynamicClass(GtnFrameworkWebserviceConstant.COMSTPLGTNGTN2OWSENTITYTRANSACTION
							+ gtnWsTransactionRequest.getTableName())
					.getDeclaredFields()) {
				String columnID = object.getName();
				String columnType = object.getType().getName();
				if (object.getType().isPrimitive()) {
					columnType = ClassUtils.primitiveToWrapper(object.getType()).getSimpleName();
				}
				columnDataTypeMap.put(columnID, columnType);
			}
			for (GtnWebServiceSearchCriteria columns : gtnWsTransactionRequest.getSearchCriteria()) {
				if (whereClause.length() != 0) {
					whereClause.append(" and ");
				}
				SimpleDateFormat dateformate = new SimpleDateFormat(
						GtnFrameworkCommonConstants.E_MMM_DD_HH_MM_SS_Z_YYYY);
				boolean isDate = "java.util.Date".equalsIgnoreCase(columnDataTypeMap.get(columns.getFieldId()));
				String type = columnDataTypeMap.get(columns.getFieldId());
				String value = columns.isFilter() ? "%" + columns.getFilterValue1() + "%" : columns.getFilterValue1();
				String filterValue1 = columns.getFilterValue1();

				switch (columns.getExpression()) {
				case "BETWEEN":
					if (isDate) {
						whereClause.append(columns.getFieldId()).append(" > '").append(formattedDate.format(dateformate.parse(columns.getFilterValue1()) + "' and "
                                                    + columns.getFieldId() + " < '"
                                                    + formattedDate.format(dateformate.parse(columns.getFilterValue2())) + "'"));
					} else {
						whereClause.append(columns.getFieldId()).append(" >'").append(columns.getFilterValue1()).append("' and ").append(columns.getFieldId()).append(" < '").append(columns.getFilterValue2()).append("'");
					}
					break;
				case "LIKE":
					whereClause.append(columns.getFieldId()).append(" like '").append(value.replaceAll("\\*", "%")).append("'");
					break;
				case "EQUAL":
					whereClause.append(columns.getFieldId()).append(" = '").append(getValueBasedOnType(type, value, filterValue1)).append("'");
					break;

				case "EQUALS":
					whereClause.append(columns.getFieldId()).append(" = '").append(getValueBasedOnType(type, value, filterValue1)).append("'");
					break;
				case "GREATER":
					whereClause.append(columns.getFieldId()).append(" > ").append(getValueBasedOnType(type, value, filterValue1));
					break;
				case "LESS":
					whereClause.append(columns.getFieldId()).append(" < ").append(getValueBasedOnType(type, value, filterValue1));
					break;
				case "GREATER_OR_EQUAL":
					whereClause.append(columns.getFieldId()).append(" >= ").append(getValueBasedOnType(type, value, filterValue1));
					break;

				case "LESS_OR_EQUAL":
					whereClause.append(columns.getFieldId()).append(" <= ").append(getValueBasedOnType(type, value, filterValue1));
					break;
				case "AND":
					whereClause.append(columns.getFieldId()).append(" < ").append(Double.valueOf(columns.getFilterValue2()));
					whereClause.append(" AND ").append(columns.getFieldId()).append(" > ")
							.append(Double.valueOf(columns.getFilterValue1()));
					break;

				case "DATE_BETWEEN":
					SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy hh:mmaa");
					Date date1 = new SimpleDateFormat(GtnFrameworkCommonConstants.E_MMM_DD_HH_MM_SS_Z_YYYY)
							.parse(columns.getFilterValue1());
					value = formatter.format(date1);
					whereClause.append(columns.getFieldId()).append(" like '%").append(value).append("%'");
					break;

				default:
					whereClause.append(columns.getFieldId()).append(" like '").append(value.replaceAll("\\*", "%")).append("'");
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return whereClause.toString();
	}

	Object getValueBasedOnType(String type, String value, String filterValue) throws ParseException {
		SimpleDateFormat dateformate = new SimpleDateFormat(GtnFrameworkCommonConstants.E_MMM_DD_HH_MM_SS_Z_YYYY);
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ("java.util.Date".equalsIgnoreCase(type)) {
			return formattedDate.format(dateformate.parse(filterValue));
		} else if ("java.lang.Double".equalsIgnoreCase(type)) {
			return Double.valueOf(filterValue);
		} else if ("Integer".equalsIgnoreCase(type)) {
			return Integer.valueOf(value);
		} else {
			return value;
		}

	}

	private void runJobForReprocess(String tableName) {
		LOGGER.debug("inside runjob");
		try {
			String scriptUrl = gtnWsCallEtlService.buildUrl(tableName);
			gtnWsCallEtlService.runShellScript(scriptUrl);
			LOGGER.debug("---------------run job ends--------------");
		} catch (Exception e) {
			LOGGER.error("error", e);
		}
	}

	private void replaceBatchIdColumn(List<String> selectColumns) {
		if (selectColumns.contains("batchId")) {
			selectColumns.set(selectColumns.indexOf("batchId"), "'IVLD_' + replace(batchId, 'IVLD_', '')");
		}
	}

	public void outboundRecords(GtnWsTransactionRequest gtnWsTransactionRequest) throws GtnFrameworkGeneralException {
		String outboundWhereCondition;
		String inavlidTableName = gtnWsTransactionRequest.getTableName();
		try {
			ClassMetadata outboundClassMetadata = gtnSqlQueryEngine.getSessionFactory()
					.getClassMetadata(gtnWebServiceAllListConfig.getTransctionDynamicClass(
							GtnFrameworkWebserviceConstant.COMSTPLGTNGTN2OWSENTITYTRANSACTION + inavlidTableName));
			if (!gtnWsTransactionRequest.getReprocessIds().isEmpty()) {
				List<String> reprocessid = gtnWsTransactionRequest.getReprocessIds();
				outboundWhereCondition = getWhereQueries(gtnWsTransactionRequest, outboundClassMetadata, reprocessid,
						GtnFrameworkOperatorType.IN.getOperaterType(), gtnWsTransactionRequest.getStagInsertColumns());

			} else {
				outboundWhereCondition = getWhereClauseForReprocess(gtnWsTransactionRequest);
				if (!gtnWsTransactionRequest.getUncheckedIds().isEmpty()) {
					List<String> unCheckedid = gtnWsTransactionRequest.getUncheckedIds();
					outboundWhereCondition += getWhereQueries(gtnWsTransactionRequest, outboundClassMetadata,
							unCheckedid, GtnFrameworkOperatorType.NOT_IN.getOperaterType(),
							gtnWsTransactionRequest.getStagInsertColumns());
				}
			}

			if (gtnWsTransactionRequest.isReprocessFlag()) {
				String insertQuery = "INSERT INTO " + gtnWsTransactionRequest.getStagingTableName() + "("
						+ StringUtils.join(gtnWsTransactionRequest.getStagInsertColumns(), ",") + ") " + " SELECT "
						+ StringUtils.join(gtnWsTransactionRequest.getStagInsertColumns(), ",") + " FROM "
						+ gtnWsTransactionRequest.getOutBoundTableName() + " WHERE " + outboundWhereCondition;
				LOGGER.debug("insertQuery----" + insertQuery);
				int updatedRecordCount = gtnSqlQueryEngine.executeInsertAndUpdateHqlQuery(insertQuery);
				LOGGER.debug("updatedRecord----" + updatedRecordCount);
			}
			updateConditionAndCallEtlService(gtnWsTransactionRequest, outboundWhereCondition,
					gtnWsTransactionRequest.getStagingTableName(), inavlidTableName);
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Error in search query : ", ex);
		}
	}

	private void updateConditionAndCallEtlService(GtnWsTransactionRequest gtnWsTransactionRequest,
			String whereCondition, String tableName, String etlJobName) {
		try {
			updateConditionFlag(gtnWsTransactionRequest, whereCondition, tableName);
			if (gtnWsTransactionRequest.isReprocessFlag()) {
				runJobForReprocess(etlJobName);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	private String getWhereQueries(GtnWsTransactionRequest gtnWsTransactionRequest, ClassMetadata classMetadata,
			List<String> ids, String condition, Object[] extraColumns) {
		StringBuilder whereQuery = new StringBuilder();
		if (!gtnWsTransactionRequest.isOutBoundModule()) {
			return Projections.property(classMetadata.getIdentifierPropertyName()) + condition
					+ StringUtils.join(ids.toArray(), ",") + " )";
		}
		for (int i = 0; i < extraColumns.length; i++) {
			whereQuery.append(extraColumns[i]).append(condition).append(StringUtils.join(getSidsList(ids, i), ","))
					.append(" )");
			if (i != extraColumns.length - 1) {
				whereQuery.append(" and ");
			}
		}
		return whereQuery.toString();
	}

	private Object[] getSidsList(List<String> ids, int index) {
		Set<String> obj = new HashSet<>();
		for (int i = 0; i < ids.size(); i++) {
			String value = ids.get(i);
			if (value.contains("_")) {
				obj.add(value.split("_")[index]);
			} else {
				obj.add(value);
			}
		}
		return obj.toArray();
	}

}
