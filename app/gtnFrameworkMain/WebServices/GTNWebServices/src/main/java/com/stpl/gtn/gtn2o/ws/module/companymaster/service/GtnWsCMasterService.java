package com.stpl.gtn.gtn2o.ws.module.companymaster.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;

@Service
@Scope(value = "singleton")
public class GtnWsCMasterService {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCMasterService.class);

	public GtnWsCMasterService() {
		super();
	}

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public GtnSerachResponse getCompantMasterSearch(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
			throws GtnFrameworkGeneralException {

		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();

		String queryName = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchQueryName();
		boolean isCount = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().isCount();
		GtnWsSearchQueryConfigLoaderType cmSearchQueryconfigLoaderType = gtnUIFrameworkWebserviceRequest
				.getGtnWsSearchRequest().getSearchConfigLodaderType();
		GtnWsSearchQueryConfigLoader cmSearchQueryConfigLoader = (GtnWsSearchQueryConfigLoader) cmSearchQueryconfigLoaderType
				.returnSearchQueryConfigLoader(gtnWebServiceAllListConfig.getDynamicClassObjectMap());
		GtnWsSearchQueryConfig cmSearchQueryConfig = cmSearchQueryConfigLoader.getSearchQueryConfigMap().get(queryName);

		GtnFrameworkQueryGeneratorBean generatorBean = getCompanyMasterQueryGeneratorBean(
				gtnUIFrameworkWebserviceRequest, cmSearchQueryConfig);

		String generatedQuery = generatorBean.generateQuery();

		List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(generatedQuery);

		if (isCount) {
			gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(resultList.get(0))));

		} else {
			getCustomisedResult(resultList, cmSearchQueryConfig,
					gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchColumnNameList());
			GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
			gtnUIFrameworkDataTable.addData(resultList);
			gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
		}

		return gtnSerachResponse;
	}

	public GtnFrameworkQueryGeneratorBean getCompanyMasterQueryGeneratorBean(
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest,
			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig) {
		logger.info("Entering getCompanyMasterQueryGeneratorBean method");
		GtnFrameworkQueryGeneratorBean queryGeneratorConfig = new GtnFrameworkQueryGeneratorBean();
		getSearchColumnBean(gtnUIFrameworkWebserviceRequest, gtnWebServiceSearchQueryConfig, queryGeneratorConfig);
		getJoinClauseBean(queryGeneratorConfig);
		getWhereClauseBean(queryGeneratorConfig, gtnWebServiceSearchQueryConfig, gtnUIFrameworkWebserviceRequest);
		getOrderByClause(queryGeneratorConfig, gtnWebServiceSearchQueryConfig, gtnUIFrameworkWebserviceRequest);
		queryGeneratorConfig.setFromTableNameWithAlies("company_Master", "cm");
		logger.info("Exiting getCompanyMasterQueryGeneratorBean method");
		return queryGeneratorConfig;
	}

	private void getSearchColumnBean(GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest,
			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig,
			GtnFrameworkQueryGeneratorBean queryGeneratorConfig) {
		List<Object> visibleColumnList = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest()
				.getSearchColumnNameList();
		if (visibleColumnList != null && !visibleColumnList.isEmpty()) {
			for (int i = 0; i < visibleColumnList.size(); i++) {
				String currentColumn = visibleColumnList.get(i).toString();
				queryGeneratorConfig.addSelectClauseBean(
						getTableColumnForField(currentColumn, gtnWebServiceSearchQueryConfig), null, Boolean.TRUE,
						null);
			}

		}

	}

	private void getJoinClauseBean(GtnFrameworkQueryGeneratorBean queryGeneratorConfig) {
		String cmMasterSid = "cm.company_Master_Sid";
		GtnFrameworkJoinClauseBean tradeClassjoinClauseBean = queryGeneratorConfig
				.addJoinClauseBean("company_Trade_Class", "trade", GtnFrameworkJoinType.LEFT_JOIN);
		tradeClassjoinClauseBean.addConditionBean(cmMasterSid, "trade.company_Master_Sid",
				GtnFrameworkOperatorType.EQUAL_TO);
		tradeClassjoinClauseBean.addConditionBean("trade.inbound_Status", null, GtnFrameworkOperatorType.NOT_EQUAL_TO);

		GtnFrameworkJoinClauseBean cmpnyParentjoinClauseBean = queryGeneratorConfig
				.addJoinClauseBean("company_Parent_Details", "parent", GtnFrameworkJoinType.LEFT_JOIN);
		cmpnyParentjoinClauseBean.addConditionBean(cmMasterSid, "parent.company_Master_Sid",
				GtnFrameworkOperatorType.EQUAL_TO);
		cmpnyParentjoinClauseBean.addConditionBean("parent.inbound_Status", null,
				GtnFrameworkOperatorType.NOT_EQUAL_TO);

		GtnFrameworkJoinClauseBean udcsJoinClauseBean = queryGeneratorConfig.addJoinClauseBean(" udcs ", "udc",
				GtnFrameworkJoinType.LEFT_JOIN);
		udcsJoinClauseBean.addConditionBean(cmMasterSid, "udc.master_Sid", GtnFrameworkOperatorType.EQUAL_TO);
		udcsJoinClauseBean.addConditionBean("udc.master_Type", null, GtnFrameworkOperatorType.EQUAL_TO);

		GtnFrameworkJoinClauseBean cmJoinClauseBean = queryGeneratorConfig.addJoinClauseBean(" company_Master  ",
				"comp", GtnFrameworkJoinType.LEFT_JOIN);
		cmJoinClauseBean.addConditionBean("comp.company_Master_Sid", "parent.Company_Master_Sid",
				GtnFrameworkOperatorType.EQUAL_TO);

		GtnFrameworkJoinClauseBean cm1JoinClauseBean = queryGeneratorConfig.addJoinClauseBean(" company_Master  ",
				"comp1", GtnFrameworkJoinType.LEFT_JOIN);
		cm1JoinClauseBean.addConditionBean("comp1.company_Master_Sid", "parent.prior_Parent_Cmpy_Master_Sid",
				GtnFrameworkOperatorType.EQUAL_TO);

		GtnFrameworkJoinClauseBean cmIdentifierJoinClauseBean = queryGeneratorConfig
				.addJoinClauseBean(" COMPANY_IDENTIFIER   ", "CID", GtnFrameworkJoinType.JOIN);
		cmIdentifierJoinClauseBean.addConditionBean("CID.COMPANY_MASTER_SID", "CM.COMPANY_MASTER_SID",
				GtnFrameworkOperatorType.EQUAL_TO);

		GtnFrameworkJoinClauseBean cmQualifierJoinClauseBean = queryGeneratorConfig
				.addJoinClauseBean(" COMPANY_QUALIFIER ", "CQ", GtnFrameworkJoinType.JOIN);
		cmQualifierJoinClauseBean.addConditionBean("CQ.COMPANY_QUALIFIER_SID", "CID.COMPANY_QUALIFIER_SID",
				GtnFrameworkOperatorType.EQUAL_TO);

	}

	private void getWhereClauseBean(GtnFrameworkQueryGeneratorBean queryGeneratorConfig,
			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig,
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		List<String> defaultWhereClauseList = gtnWebServiceSearchQueryConfig.getWhereClauseLeftPartList();
		if (defaultWhereClauseList != null && !defaultWhereClauseList.isEmpty()) {
			for (int i = 0; i < defaultWhereClauseList.size(); i++) {
				queryGeneratorConfig.addWhereClauseBean(defaultWhereClauseList.get(i), null,
						GtnFrameworkOperatorType.NOT_EQUAL_TO, GtnFrameworkDataType.STRING, "D");
			}
		}
		GtnWsSearchRequest gtnWsSearchRequest = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest();
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = gtnWsSearchRequest
				.getGtnWebServiceSearchCriteriaList();
		if (gtnWebServiceSearchCriteriaList != null && !gtnWebServiceSearchCriteriaList.isEmpty()) {
			for (GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria : gtnWebServiceSearchCriteriaList) {
				String expressionType = gtnWebServiceSearchCriteria.getExpression();
				String criteriaFieldName = getTableColumnForWhereClause(gtnWebServiceSearchCriteria.getFieldId(),
						gtnWebServiceSearchQueryConfig);
				GtnFrameworkOperatorType operatorType = getOperatorType(expressionType);
				queryGeneratorConfig.addWhereClauseBean(criteriaFieldName, null, operatorType,
						GtnFrameworkDataType.NULL_ALLOWED, null);
			}
		}
	}

	private void getOrderByClause(GtnFrameworkQueryGeneratorBean queryGeneratorConfig,
			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig,
			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		List<GtnWebServiceOrderByCriteria> defaultOrderByClauseList = gtnWebServiceSearchQueryConfig.getOrderByClause();
		GtnWsSearchRequest gtnWsSearchRequest = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest();
		List<GtnWebServiceOrderByCriteria> orderByClauseList = gtnWsSearchRequest.getGtnWebServiceOrderByCriteriaList();
		if (orderByClauseList != null && !orderByClauseList.isEmpty()) {
			for (int i = 0; i < orderByClauseList.size(); i++) {
				String propertyId = orderByClauseList.get(i).getPropertyId();
				String columnNameWithAlies = getTableColumnForWhereClause(propertyId, gtnWebServiceSearchQueryConfig);
				queryGeneratorConfig.addOrderByClauseBean(columnNameWithAlies,
						defaultOrderByClauseList.get(i).getOrderByCriteria());
			}
		} else if (defaultOrderByClauseList != null && !defaultOrderByClauseList.isEmpty()) {
			for (int i = 0; i < defaultOrderByClauseList.size(); i++) {
				String propertyId = defaultOrderByClauseList.get(i).getPropertyId();
				queryGeneratorConfig.addOrderByClauseBean(propertyId,
						defaultOrderByClauseList.get(i).getOrderByCriteria());
			}

		}
	}

	private String getTableColumnForField(String fieldName, GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig) {
		Map<String, GtnWsColumnDetailsConfig> columnDetailsMap = gtnWebServiceSearchQueryConfig
				.getFieldToColumnDetailsMap();
		if (columnDetailsMap.containsKey(fieldName)) {
			return columnDetailsMap.get(fieldName).getColumnNameForSelectClause();
		} else {
			throw new IllegalArgumentException("Details Not Found for ---" + fieldName);
		}
	}

	private String getTableColumnForWhereClause(String fieldName, GtnWsSearchQueryConfig searchQueryConfig) {
		Map<String, GtnWsColumnDetailsConfig> columnDetailsMap = searchQueryConfig.getFieldToColumnDetailsMap();
		if (columnDetailsMap.containsKey(fieldName)) {
			return columnDetailsMap.get(fieldName).getColumnNameForWhereClause().contains("HELPER_TABLE_SID")
					? columnDetailsMap.get(fieldName).getHelperTableMappedColumnNameForOrderByClause()
					: columnDetailsMap.get(fieldName).getColumnNameForWhereClause();
		} else {
			throw new IllegalArgumentException("Details Not Found for ---" + fieldName);
		}
	}

	private List<String> findTypeList(GtnWsSearchQueryConfig searchQueryConfig, List<Object> columnNameList) {
		Map<String, GtnWsColumnDetailsConfig> columnDetailsMap = searchQueryConfig.getFieldToColumnDetailsMap();
		List<String> typeList = new ArrayList<>();
		for (Object column : columnNameList) {
			if (columnDetailsMap.get(column) != null) {
				typeList.add(columnDetailsMap.get(column).getDataType());
			}
		}
		return typeList;
	}

	public Object getHelperTypeValue(Object object) {
		Object defaultValue = "";
		Map<Integer, String> helperMap = gtnWebServiceAllListConfig.getIdDescMap();
		String helperValue = String.valueOf(object);
		if (StringUtils.isNotBlank(helperValue) && (Integer.valueOf(helperValue) != 0)) {
			defaultValue = helperMap.get(Integer.valueOf(helperValue));
		}
		return defaultValue;
	}

	public String getStringFromObject(final Object str) {
		String stringOut = StringUtils.trimToEmpty(String.valueOf(str));
		if (GtnWsConstants.NULL.equals(stringOut) || GtnWsConstants.SELECT_ONE.equals(stringOut)) {
			stringOut = StringUtils.EMPTY;
		}
		return stringOut;
	}

	public Integer getIntegerFromObject(final Object obj) {
		return Integer.parseInt(String.valueOf(obj));
	}

	public void getCustomisedResult(final List<Object[]> resultList, GtnWsSearchQueryConfig searchQueryConfig,
			List<Object> columnName) {
		List<String> typeList = findTypeList(searchQueryConfig, columnName);

		if (resultList != null) {

			for (int i = 0; i < resultList.size(); i++) {
				final Object[] obj = resultList.get(i);
				for (int index = 0; index < obj.length; index++) {
					switch (typeList.get(index)) {
					case "String":
						obj[index] = getStringFromObject(obj[index]);
						break;
					case "Integer":
						obj[index] = getIntegerFromObject(obj[index]);
						break;
					case "Helper":
						obj[index] = getHelperTypeValue(obj[index]);
						break;
					case "Boolean":
						obj[index] = Boolean.parseBoolean(getStringFromObject(obj[index]));
						break;
					case "User":
						obj[index] = gtnWebServiceAllListConfig.getUserIdNameMap().get(obj[index]);
						break;
					case "Date":
						Date startDate = (Date) obj[index];
						obj[index] = startDate;
						break;
					case "BigDecimal":
						BigDecimal numericColumn = (BigDecimal) obj[index];
						obj[index] = numericColumn == null ? numericColumn : numericColumn.doubleValue();
						break;
					default:
						obj[index] = (obj[index]);
						break;
					}
				}
			}
		}
	}

	private GtnFrameworkOperatorType getOperatorType(String expressionType) {
		GtnFrameworkOperatorType operatorType = null;

		switch (expressionType) {
		case "BETWEEN":
			operatorType = GtnFrameworkOperatorType.BETWEEN;
			break;
		case "LIKE":
			operatorType = GtnFrameworkOperatorType.LIKE;
			break;
		case "EQUAL":
		case "EQUALS":
			operatorType = GtnFrameworkOperatorType.EQUAL_TO;
			break;

		case "GREATER":
			operatorType = GtnFrameworkOperatorType.GREATERTHAN;
			break;
		case "LESS":
			operatorType = GtnFrameworkOperatorType.LESSTHAN;
			break;
		case "in":
			operatorType = GtnFrameworkOperatorType.IN;
			break;
		default:
			operatorType = null;
			break;
		}
		return operatorType;
	}

}
