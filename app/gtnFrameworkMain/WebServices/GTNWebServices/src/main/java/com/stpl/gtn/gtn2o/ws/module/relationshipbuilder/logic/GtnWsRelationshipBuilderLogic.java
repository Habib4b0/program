/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.HierarchyDefinition;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.HierarchyLevelDefinition;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipLevelDefinition;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.logic.GtnWsSearchQueryGenerationLogic;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.bean.GtnWsHierarchyBean;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.bean.GtnWsSortedHierarchyBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelValuesBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelsBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

/**
 *
 * @author Mahesh.James
 */
@Service
@Scope(value = "singleton")
public class GtnWsRelationshipBuilderLogic {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsRelationshipBuilderLogic.class);

	@Autowired
	private GtnWsRelationshipBuilderHelperLogic helperLogic;

	@Autowired
	private GtnWsRelationshipBuilderHierarchyFileGenerator gtnWsRelationshipBuilderHierarchyFileGenerator;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	public GtnWsRelationshipBuilderLogic() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery, params, type);
	}

	public int executeUpdateQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery);
	}

	public int executeUpdateQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery, params, type);
	}

	public String getQuery(String sqlId) {
		return gtnWsSqlService.getQuery(sqlId);
	}

	public Object[] createParams(Object... params) {
		return params;
	}

	public GtnFrameworkDataType[] createDataTypes(GtnFrameworkDataType... dataTypes) {
		return dataTypes;
	}

	public GtnWsRelationshipBuilderHierarchyFileGenerator getGtnWsRelationshipBuilderHierarchyFileGenerator() {
		return gtnWsRelationshipBuilderHierarchyFileGenerator;
	}

	public void setGtnWsRelationshipBuilderHierarchyFileGenerator(
			GtnWsRelationshipBuilderHierarchyFileGenerator gtnWsRelationshipBuilderHierarchyFileGenerator) {
		this.gtnWsRelationshipBuilderHierarchyFileGenerator = gtnWsRelationshipBuilderHierarchyFileGenerator;
	}

	private List<GtnWsRelationshipLevelDefinitionBean> setHistRelationshipLevelDefinitionValues(
			List<Object[]> resultList) {
		List<GtnWsRelationshipLevelDefinitionBean> hierarchyList = new ArrayList<>();
		GtnWsRelationshipLevelDefinitionBean relationshipLevelBean = null;
		for (Object[] result : resultList) {
			relationshipLevelBean = new GtnWsRelationshipLevelDefinitionBean();
			relationshipLevelBean.setRelationshipLevelSystemId((Integer) result[0]);
			relationshipLevelBean.setHierarchyLevelSystemId((Integer) result[1]);
			relationshipLevelBean.setRekationshipLevelValue((String) result[2]);
			relationshipLevelBean.setHierarchyNo((String) result[3]);
			relationshipLevelBean.setParentNode((String) result[4]);
			relationshipLevelBean.setLevelNo((String) result[5]);
			relationshipLevelBean.setLevelName((String) result[6]);
			hierarchyList.add(relationshipLevelBean);
		}

		return hierarchyList;

	}

	private List<HierarchyLevelValuesBean> setHistHierarchyLevelDefinitionValues(List<Object[]> resultList) {
		List<HierarchyLevelValuesBean> hierarchyList = new ArrayList<>();
		HierarchyLevelValuesBean hierarchyLevelBean = null;

		for (Object[] result : resultList) {
			hierarchyLevelBean = new HierarchyLevelValuesBean();
			hierarchyLevelBean.setHierarchyLevelValuesSid((Integer) result[0]);
			hierarchyLevelBean.setHierarchyLevelDefinitionSid((Integer) result[1]);
			hierarchyLevelBean.setLevelValues((String) result[2]);
			hierarchyLevelBean.setLevelNo((((BigDecimal) result[3]).intValue()));
			hierarchyLevelBean.setLevelName((String) result[4]);
			hierarchyList.add(hierarchyLevelBean);
		}

		return hierarchyList;

	}

	private HierarchyDefinitionBean setHierarchyDefinitionBean(Object[] result) {

		HierarchyDefinitionBean hierarchyDefinitionBean = new HierarchyDefinitionBean();

		hierarchyDefinitionBean.setHierarchyDefinitionSid((Integer) result[0]);
		hierarchyDefinitionBean.setHierarchyName((String) result[1]);
		hierarchyDefinitionBean.setHierarchyType((String) result[2]);
		hierarchyDefinitionBean.setHierarchyCategory((String) result[3]);
		hierarchyDefinitionBean.setNoOfLevels((Integer) result[4]);
		return hierarchyDefinitionBean;
	}

	private List<HierarchyLevelDefinitionBean> gettHierarchyLevelDefinitionList(List<Object[]> resultList) {
		List<HierarchyLevelDefinitionBean> hierarchyList = new ArrayList<>();
		HierarchyLevelDefinitionBean hierarchyLevelBean = null;

		for (Object[] result : resultList) {
			hierarchyLevelBean = new HierarchyLevelDefinitionBean();
			gtnWsRelationshipBuilderHierarchyFileGenerator.getHierarchyCustomizer(hierarchyLevelBean, result);
			hierarchyList.add(hierarchyLevelBean);
		}
		Collections.sort(hierarchyList);
		return hierarchyList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GtnWsRelationshipBuilderResponse getHierarchyVersionNo(GtnWsRelationshipBuilderRequest rbRequest,
			GtnWsRelationshipBuilderResponse rbResponse) throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(GtnFrameworkWebserviceConstant.HIST);
		inputlist.add(String.valueOf(rbRequest.getHierarchyDefSId()));
		List<Integer> result = (List<Integer>) executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyVersionNo"));
		if (result != null && !result.isEmpty()) {
			rbResponse.setSelectedVersionNo(result.get(0) != null ? result.get(0) : 0);
			rbResponse.setHierarchyVersionNo(result);
		} else {
			rbResponse.setSelectedVersionNo(0);
		}
		return rbResponse;
	}

	public List<HierarchyLevelDefinitionBean> getHistHierarchyLevelDefinitionByLevelNO(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, int levelNo) throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(String.valueOf(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0).getFilterValue1()));
		inputlist.add(String.valueOf(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(1).getFilterValue1()));
		inputlist.add(String.valueOf(levelNo));
		@SuppressWarnings("unchecked")
		List<Object[]> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyLevelDefinitionByLevelNo"));
		if (result != null) {
			return gettHierarchyLevelDefinitionList(result);
		}
		return Collections.emptyList();
	}

	@SuppressWarnings("unchecked")
	public GtnWsRelationshipBuilderResponse getHierarchyDefinition(GtnWsRelationshipBuilderRequest rbRequest,
			GtnWsRelationshipBuilderResponse rbResponse) throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(GtnFrameworkWebserviceConstant.HIST);
		inputlist.add(String.valueOf(rbRequest.getHierarchyDefSId()));
		List<Object[]> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyDefinition"));
		if (result != null) {
			rbResponse.setHierarchyDefinitionBean(setHierarchyDefinitionBean(result.get(0)));
		}
		return rbResponse;
	}

	@SuppressWarnings("unchecked")
	public HierarchyDefinitionBean getHierarchyDefinition(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(GtnFrameworkWebserviceConstant.HIST);
		inputlist.add(String.valueOf(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0).getFilterValue1()));
		List<Object[]> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyDefinition"));
		if (result != null) {
			return setHierarchyDefinitionBean(result.get(0));
		}
		return null;
	}

	public List<HierarchyLevelValuesBean> getHistHierarchyLevelDefinitionValuesByLevelNo(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, int levelNo) throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(String.valueOf(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0).getFilterValue1()));
		inputlist.add(String.valueOf(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(1).getFilterValue1()));
		inputlist.add(String.valueOf(levelNo));
		StringBuilder hierarcgylevelValuequeryBuilder = new StringBuilder(
				gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyLevelValueByLevelNo"));
		GtnWsSearchQueryGenerationLogic queryGenerationLogic = new GtnWsSearchQueryGenerationLogic();
		hierarcgylevelValuequeryBuilder.append(getUserFilterClause(gtnWsRequest));
		setDefaultOrderBy(gtnWsRequest);
		queryGenerationLogic.appendOrderBy(hierarcgylevelValuequeryBuilder,
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList());
		queryGenerationLogic.appendOffset(hierarcgylevelValuequeryBuilder,
				gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart(),
				gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
		String query = hierarcgylevelValuequeryBuilder.toString()
				.replaceAll(GtnWsRelationshipBuilderConstants.VALUE_PROPERTY_ID, "HLV.LEVEL_VALUES");
		@SuppressWarnings("unchecked")
		List<Object[]> result = executeQuery(query);
		if (result != null) {
			return setHistHierarchyLevelDefinitionValues(result);
		}
		return Collections.emptyList();
	}

	private void setDefaultOrderBy(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		if (gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList() == null
				|| gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList().isEmpty()) {
			gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceOrderByCriteriaList(Arrays.asList(
					new GtnWebServiceOrderByCriteria(GtnWsRelationshipBuilderConstants.VALUE_PROPERTY_ID, "ASC")));
		}
	}

	public GtnWsRelationshipBuilderResponse getHistRelationshipLevelDefinitionValues(
			GtnWsRelationshipBuilderRequest rbRequest, GtnWsRelationshipBuilderResponse rbResponse)
			throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(String.valueOf(rbRequest.getRbSysId()));
		inputlist.add(String.valueOf(rbRequest.getVersionNo()));
		@SuppressWarnings("unchecked")
		List<Object[]> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBRelationshipLevelDefinition"));
		if (result != null) {
			rbResponse.setRelationshipBuilderBeanList(setHistRelationshipLevelDefinitionValues(result));
		}
		return rbResponse;
	}

	public GtnUIFrameworkDataTable getUserDefinedLevelValues(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			List<String> modifiedHiddenIdList, int levelNo) throws GtnFrameworkGeneralException {
		GtnUIFrameworkDataTable datTableData = new GtnUIFrameworkDataTable();
		List<HierarchyLevelValuesBean> heirarchyLevelValueBeanList = getHistHierarchyLevelDefinitionValuesByLevelNo(
				gtnWsRequest, levelNo);
		for (int i = 0; i < heirarchyLevelValueBeanList.size(); i++) {
			final HierarchyLevelValuesBean valueBean = heirarchyLevelValueBeanList.get(i);
			if (!modifiedHiddenIdList.contains(String.valueOf(valueBean.getHierarchyLevelValuesSid()))) {
				HierarchyLevelsBean levelValuesDTO = new HierarchyLevelsBean();
				levelValuesDTO.setLevelValue(valueBean.getLevelValues());
				levelValuesDTO.setHiddenId(String.valueOf(valueBean.getHierarchyLevelValuesSid()));
				levelValuesDTO.setHierarchyLevelSystemId(valueBean.getHierarchyLevelDefinitionSid());
				levelValuesDTO.setParentNode("0");
				levelValuesDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
				levelValuesDTO.setLevelName(valueBean.getLevelName());
				levelValuesDTO.setLevelNo(String.valueOf(valueBean.getLevelNo()));
				levelValuesDTO.setLevelValueReference(GtnFrameworkWebserviceConstant.USER_DEFINED);
				GtnUIFrameworkDataRow newDataRow = new GtnUIFrameworkDataRow();
				newDataRow.setColList(getLevelBeanAsList(levelValuesDTO));
				datTableData.addDataRow(newDataRow);
			}
		}

		return datTableData;
	}

	public GtnSerachResponse getLinkedLevelValues(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			HierarchyDefinitionBean hierarchyDefinitionBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		GtnUIFrameworkDataTable datTableData = new GtnUIFrameworkDataTable();
		final List<Object> hierarchyInputList = new ArrayList<>();
		hierarchyInputList.add(hierarchyDefinitionBean.getHierarchyType());
		hierarchyInputList.add(hierarchyDefinitionBean.getHierarchyCategory());
		boolean isFirst = true;
		Map<Integer, String> inclustionExculstionRules = helperLogic
				.getInclusionExclusionRulesWithoutBPM(hierarchyDefinitionBean.getHierarchyName());
		for (HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean : hierarchyLevelDefinitionList) {
			final int levelSystemId = hierarchyLevelDefinitionBean.getHierarchyLevelDefinitionSid();
			final String tableName = hierarchyLevelDefinitionBean.getTableName();
			final String columnName = hierarchyLevelDefinitionBean.getFieldName();
			final String rule = inclustionExculstionRules.get(hierarchyLevelDefinitionBean.getLevelNo());
			hierarchyInputList.add(2, Integer.parseInt(String.valueOf(hierarchyLevelDefinitionBean.getLevelNo())));
			hierarchyInputList.add(3, rule);
			String sqlString = helperLogic.finderImplInLogic(tableName, columnName, hierarchyInputList, isFirst);
			String primaryKeyColumn = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(tableName, columnName).getLevelValueColumnName();
			List levelValues = new ArrayList();
			try {
				StringBuilder queryBuilder = new StringBuilder(sqlString);
				GtnWsSearchQueryGenerationLogic queryGenerationLogic = new GtnWsSearchQueryGenerationLogic();
				queryBuilder.append(getUserFilterClause(gtnWsRequest));
				if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
					setDefaultOrderBy(gtnWsRequest);
					queryGenerationLogic.appendOrderBy(queryBuilder,
							gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList());
					queryGenerationLogic.appendOffset(queryBuilder,
							gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart(),
							gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
				}
				String query = queryBuilder.toString().replaceAll(GtnWsRelationshipBuilderConstants.VALUE_PROPERTY_ID,
						primaryKeyColumn);
				levelValues.addAll(executeQuery(query));
			} catch (Exception ex) {
				logger.error("Exception in getLinkedLevelValues levelValues", ex);
			}
			for (int i = 0; i < levelValues.size(); i++) {
				Object[] obj = (Object[]) levelValues.get(i);
				HierarchyLevelsBean levelValuesDTO = new HierarchyLevelsBean();
				levelValuesDTO.setLevelValue(String.valueOf(obj[0]));
				levelValuesDTO.setHiddenId(String.valueOf(obj[1]));
				levelValuesDTO.setParentNode("0");
				levelValuesDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
				levelValuesDTO.setHierarchyLevelSystemId(levelSystemId);
				levelValuesDTO.setLevelName(String.valueOf(hierarchyLevelDefinitionBean.getLevelName()));
				levelValuesDTO.setLevelNo(String.valueOf(hierarchyLevelDefinitionBean.getLevelNo()));
				levelValuesDTO.setPrimaryKeyColumn(primaryKeyColumn);
				levelValuesDTO.setLevelValueReference(hierarchyLevelDefinitionBean.getLevelValueReference());
				GtnUIFrameworkDataRow newDataRow = new GtnUIFrameworkDataRow();
				newDataRow.setColList(getLevelBeanAsList(levelValuesDTO));
				datTableData.addDataRow(newDataRow);
			}
		}
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			searchResponse.setCount(datTableData.getDataTable().size());
		} else {
			searchResponse.setResultSet(datTableData);
		}
		return searchResponse;
	}

	public GtnSerachResponse getHistAllLevelValues(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		GtnSerachResponse searchResponse = new GtnSerachResponse();
		int levelNo = 1;
		if (gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().size() > 3) {
			String levelNoValue = gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(3)
					.getFilterValue1();
			if (levelNoValue.matches("[0-9]+")) {
				levelNo = Integer.parseInt(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList()
						.get(3).getFilterValue1());
			}
		}
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = getHistHierarchyLevelDefinitionByLevelNO(
				gtnWsRequest, levelNo);
		if (hierarchyLevelDefinitionList == null || hierarchyLevelDefinitionList.isEmpty()) {
			return searchResponse;
		}
		HierarchyDefinitionBean hierarchyDefinitionBean = getHierarchyDefinition(gtnWsRequest);
		List<String> modifiedHiddenList = getModifiedHiddenIdList(gtnWsRequest);
		if (GtnFrameworkWebserviceConstant.USER_DEFINED
				.equals(hierarchyLevelDefinitionList.get(0).getLevelValueReference())) {
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				searchResponse.setCount(getUserDefinedLevelCount(gtnWsRequest, levelNo));
			} else {
				searchResponse.setResultSet(getUserDefinedLevelValues(gtnWsRequest, modifiedHiddenList, levelNo));
			}

		} else {
			if (levelNo != 1) {
				return getFilteredValue2(gtnWsRequest, modifiedHiddenList);
			}
			return getLinkedLevelValues(gtnWsRequest, hierarchyDefinitionBean, hierarchyLevelDefinitionList);
		}

		return searchResponse;

	}

	public int getUserDefinedLevelCount(GtnUIFrameworkWebserviceRequest gtnWsRequest, int levelNo)
			throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(String.valueOf(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(0).getFilterValue1()));
		inputlist.add(String.valueOf(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(1).getFilterValue1()));
		inputlist.add(String.valueOf(levelNo));

		String counQuery = gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyLevelValueByLevelNoCount")
				+ getUserFilterClause(gtnWsRequest);
		counQuery = counQuery.replaceAll(GtnWsRelationshipBuilderConstants.VALUE_PROPERTY_ID, "HLV.LEVEL_VALUES");
		@SuppressWarnings("unchecked")
		List<Object[]> result = executeQuery(counQuery);
		if (result != null) {
			return Integer.valueOf(String.valueOf(result.get(0)));
		}
		return 0;
	}

	private void getUserFilterClause(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnFrameworkQueryGeneratorBean finalQueryBean, String selectPrimaryColumn) {
		StringBuilder queryBuilder = new StringBuilder();
		for (GtnWebServiceSearchCriteria searchFilterCriteria : gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchFilterCriteria.isFilter()) {
				queryBuilder.append("%").append(searchFilterCriteria.getFilterValue1()).append("%");
				finalQueryBean.addWhereClauseBean(selectPrimaryColumn, null, GtnFrameworkOperatorType.LIKE,
						GtnFrameworkDataType.STRING, queryBuilder.toString());
			}
		}
	}

	private String getUserFilterClause(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		StringBuilder queryBuilder = new StringBuilder();
		for (GtnWebServiceSearchCriteria searchFilterCriteria : gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchFilterCriteria.isFilter()) {
				queryBuilder.append(" AND ").append(searchFilterCriteria.getFieldId()).append(" LIKE '%")
						.append(searchFilterCriteria.getFilterValue1().replaceAll("'", "''")).append("%' ");
			}
		}
		return queryBuilder.toString();
	}

	@SuppressWarnings("unchecked")
	public GtnSerachResponse getFilteredValue2(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			List<String> modifiedHiddenList) throws GtnFrameworkGeneralException {
		GtnSerachResponse serachResponse = new GtnSerachResponse();
		try {
			List<HierarchyLevelDefinitionBean> hierarchyList = gtnWsRelationshipBuilderHierarchyFileGenerator
					.getRBHierarchyLevelDefinitionBySid(
							Integer.parseInt(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList()
									.get(0).getFilterValue1()),
							Integer.parseInt(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList()
									.get(1).getFilterValue1()));
			int levelNo = Integer.parseInt(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(3).getFilterValue1());
			HierarchyLevelDefinitionBean destinationHierarchyBean = gtnWsRelationshipBuilderHierarchyFileGenerator
					.getHierarchyBeanByLevelNo(hierarchyList, levelNo);
			if (destinationHierarchyBean != null) {
				GtnFrameworkFileReadWriteService fileReadWriteService = new GtnFrameworkFileReadWriteService();
				GtnFrameworkHierarchyQueryBean queryBaen = fileReadWriteService.getQueryFromFile(
						destinationHierarchyBean.getHierarchyDefinitionSid(),
						destinationHierarchyBean.getHierarchyLevelDefinitionSid(),
						destinationHierarchyBean.getVersionNo());
				GtnFrameworkQueryGeneratorBean finalQueryBean = queryBaen.getQuery();
				List<Object> primaryKeyPositionList = helperLogic.getMasterSidList(gtnWsRequest, hierarchyList);

				logger.info("finalQuery--->>" + finalQueryBean.generateQuery());

				GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
						.getKeyRelationBeanUsingTableIdAndColumnName(destinationHierarchyBean.getTableName(),
								destinationHierarchyBean.getFieldName());
				getInboundRestriction(hierarchyList, finalQueryBean);

				List<Integer> notInList = getNotInList(gtnWsRequest);
				if (!notInList.isEmpty()) {
					String notInQueryStr = keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn();
					finalQueryBean.addWhereClauseBean(notInQueryStr, null, GtnFrameworkOperatorType.NOT_IN,
							GtnFrameworkDataType.IN_LIST, notInList);
					primaryKeyPositionList.add(notInList);
				}

				GtnFrameworkDataType[] datatypes = new GtnFrameworkDataType[primaryKeyPositionList.size()];
				for (int i = 0; i < datatypes.length; i++) {
					if (i == datatypes.length - 1 && !notInList.isEmpty()) {
						datatypes[i] = GtnFrameworkDataType.IN_LIST;
					} else {
						datatypes[i] = GtnFrameworkDataType.STRING;
					}
				}

				GtnWsSearchQueryGenerationLogic queryGenerationLogic = new GtnWsSearchQueryGenerationLogic();
				String selectPrimaryColumn = keyBean.getLevelValueColumnName();
				getUserFilterClause(gtnWsRequest, finalQueryBean, selectPrimaryColumn);
				StringBuilder queryBuilder = new StringBuilder(finalQueryBean.generateQuery());
				if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {

					setDefaultOrderBy(gtnWsRequest);
					queryGenerationLogic.appendOrderBy(queryBuilder,
							gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList());
					queryGenerationLogic.appendOffset(queryBuilder,
							gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart(),
							gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
				}
				String query = queryBuilder.toString().replaceAll(GtnWsRelationshipBuilderConstants.VALUE_PROPERTY_ID,
						selectPrimaryColumn);

				List<Object[]> result = executeQuery(query, primaryKeyPositionList.toArray(), datatypes);

				String nextPrimayKey = keyBean.getActualColumnName();
				GtnUIFrameworkDataTable dataTable = setLevelValueBean(result, destinationHierarchyBean, nextPrimayKey,
						modifiedHiddenList);
				serachResponse.setCount(dataTable.getDataTable().size());
				serachResponse.setResultSet(dataTable);
			}
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in getFilteredValue", ex);
		}
		return serachResponse;
	}

	private void getInboundRestriction(List<HierarchyLevelDefinitionBean> hierarchyList,
			GtnFrameworkQueryGeneratorBean queryBaen) {
		Set<String> tableNamelist = HierarchyLevelDefinitionBean.getTableNameSet(hierarchyList);
		hierarchyService.getInboundRestrictionQuery(tableNamelist, queryBaen);
	}

	public GtnWsRelationshipBuilderResponse getModifiedHiddenIdList(GtnWsRelationshipBuilderRequest rbRequest,
			GtnWsRelationshipBuilderResponse rbResponse) {
		rbResponse.clearHiddenIdList();
		if (rbRequest.getRsTreeNodeList() != null) {
			for (GtnWsRecordBean levelBean : rbRequest.getRsTreeNodeList()) {
				rbResponse.addToHiddenIdList(
						levelBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
			}
		}
		return rbResponse;
	}

	public List<String> getModifiedHiddenIdList(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		List<String> modifiedHiddenIdList = new ArrayList<>();
		if (gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().size() > 2 && gtnWsRequest
				.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(2).getFilterValue3() != null) {
			for (Map<String, Object> levelBeanMap : (List<Map<String, Object>>) gtnWsRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList().get(2).getFilterValue3()) {
				GtnWsRecordBean levelBean = new ObjectMapper().convertValue(levelBeanMap, GtnWsRecordBean.class);
				modifiedHiddenIdList.add(
						levelBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
			}
		}
		return modifiedHiddenIdList;
	}

	public GtnWsRelationshipBuilderResponse loadRelationship(GtnWsRelationshipBuilderRequest rbRequest,
			GtnWsRelationshipBuilderResponse rbResponse) throws GtnFrameworkGeneralException {
		getSavedHistLevelValuesList(rbRequest, rbResponse);
		sortGtnWsRecordBean(rbResponse.getRbTreeNodeList());
		Map<String, GtnWsRecordBean> finalSavedLevelsList1 = new HashMap<>();
		for (int i = 0; i < rbResponse.getRbTreeNodeList().size(); i++) {
			GtnWsRecordBean levelBean = rbResponse.getRbTreeNodeList().get(i);
			finalSavedLevelsList1.put(
					levelBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal()),
					levelBean);
		}
		List<GtnWsRecordBean> relationshipTreeNode = new ArrayList<>();
		for (int i = 0; i < rbResponse.getRbTreeNodeList().size(); i++) {
			GtnWsRecordBean treeNode = rbResponse.getRbTreeNodeList().get(i);
			String hierarchyNo = treeNode
					.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal())
					.substring(0, treeNode
							.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal())
							.length() - 1);
			GtnWsRecordBean parentTreeNode = null;
			if (hierarchyNo.lastIndexOf('.') != -1) {
				String parentHierrarchy = hierarchyNo.substring(0, hierarchyNo.lastIndexOf('.') + 1);
				parentTreeNode = finalSavedLevelsList1.get(parentHierrarchy);
			}
			if (parentTreeNode != null) {
				parentTreeNode.addChild(treeNode);
			}
			int treeLevelNo = treeNode
					.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal());
			if (treeLevelNo == 1) {
				relationshipTreeNode.add(treeNode);
			}
		}
		rbResponse.setRbTreeNodeList(relationshipTreeNode);
		return rbResponse;
	}

	public GtnWsRelationshipBuilderResponse getSavedHistLevelValuesList(GtnWsRelationshipBuilderRequest rbRequest,
			GtnWsRelationshipBuilderResponse rbResponse) throws GtnFrameworkGeneralException {
		if (rbRequest.getRbSysId() != 0) {
			getHistRelationshipLevelDefinitionValues(rbRequest, rbResponse);
			rbResponse.clearRbTreeNodeList();
			for (GtnWsRelationshipLevelDefinitionBean relationBean : rbResponse.getRelationshipBuilderBeanList()) {
				HierarchyLevelsBean levelValuesDTO = new HierarchyLevelsBean();
				levelValuesDTO.setHierarchyNo(relationBean.getHierarchyNo());
				getHierarchyLevels(relationBean, levelValuesDTO, String.valueOf(rbRequest.getHierarchyVersionNo()));
				levelValuesDTO.setRelationshipLevelSystemId(relationBean.getRelationshipLevelSystemId());
				levelValuesDTO.setParentNode(relationBean.getParentNode());
				levelValuesDTO.setHierarchyLevelSystemId(relationBean.getHierarchyLevelSystemId());
				levelValuesDTO.setLevelName(relationBean.getLevelName());
				levelValuesDTO.setLevelNo(String.valueOf(relationBean.getLevelNo()));
				GtnWsRecordBean recordBean = getGtnWsRecordBean(levelValuesDTO);
				rbResponse.addToRbTreeNodeList(recordBean);
			}
		}

		return rbResponse;
	}

	@SuppressWarnings("rawtypes")
	private HierarchyLevelsBean getHierarchyLevels(GtnWsRelationshipLevelDefinitionBean relationBean,
			HierarchyLevelsBean levelValuesDTO, String relationVersionNo) throws GtnFrameworkGeneralException {
		try {
			List<String> inputlist = new ArrayList<>();
			inputlist.add(String.valueOf(relationBean.getHierarchyLevelSystemId()));
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBhierarchyLevelDefn"));
			if (result != null && !result.isEmpty()) {
				Object[] obj = (Object[]) result.get(0);
				String refType = String.valueOf(obj[0]);
				String tableName = String.valueOf(obj[1]);
				String columnName = String.valueOf(obj[2]);
				if (GtnFrameworkWebserviceConstant.USER_DEFINED.equals(refType)) {
					getRelationUserDefinedLevelValues(relationBean, levelValuesDTO, relationVersionNo);
					levelValuesDTO.setLevelValueReference(GtnFrameworkWebserviceConstant.USER_DEFINED);
				} else {
					getRelationlinkedLevelValues(relationBean, levelValuesDTO, tableName, columnName);
					levelValuesDTO.setLevelValueReference("Linked");
				}

			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getHierarchyLevels", e);
		}
		return levelValuesDTO;
	}

	@SuppressWarnings("rawtypes")
	private HierarchyLevelsBean getRelationUserDefinedLevelValues(GtnWsRelationshipLevelDefinitionBean relationBean,
			HierarchyLevelsBean levelValuesDTO, String relationVersionNo) throws GtnFrameworkGeneralException {
		try {
			List<String> inputlist = new ArrayList<>();
			inputlist.add(relationBean.getRekationshipLevelValue());
			inputlist.add(relationVersionNo);
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBuserDefindLevelValue"));
			if (result != null && !result.isEmpty()) {
				Object[] obje = (Object[]) result.get(0);
				levelValuesDTO.setLevelValue(String.valueOf(obje[0]));
				levelValuesDTO.setHiddenId(String.valueOf(obje[1]));
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getRelationUserDefinedLevelValues", e);
		}
		return levelValuesDTO;
	}

	private HierarchyLevelsBean getRelationPrimaryColumnValues(GtnFrameworkSingleColumnRelationBean keyRelationBean,
			GtnWsRelationshipLevelDefinitionBean relationBean, HierarchyLevelsBean levelValuesDTO)
			throws GtnFrameworkGeneralException {
		try {

			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			List<String> columnAndTableNameList = hierarchyService.getMappingColumns(keyRelationBean);
			queryBean.addSelectClauseBean(columnAndTableNameList.get(0), null, Boolean.TRUE, null);
			queryBean.addSelectClauseBean(columnAndTableNameList.get(1), null, Boolean.TRUE, null);
			queryBean.setFromTableNameWithAlies(keyRelationBean.getActualTtableName(),
					keyRelationBean.getActualTtableName());
			hierarchyService.addTableJoin(keyRelationBean, queryBean);
			queryBean.addWhereClauseBean(columnAndTableNameList.get(1), null, GtnFrameworkOperatorType.EQUAL_TO,
					GtnFrameworkDataType.STRING, relationBean.getRekationshipLevelValue());
			return getHierarchyLevelBean(executeQuery(queryBean.generateQuery()), levelValuesDTO, keyRelationBean);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getRelationPrimaryColumnValues", e);
		}
	}

	@SuppressWarnings("rawtypes")
	private HierarchyLevelsBean getHierarchyLevelBean(List result, HierarchyLevelsBean levelValuesDTO,
			GtnFrameworkSingleColumnRelationBean keyRelationBean) {
		if (result != null && !result.isEmpty()) {
			Object[] obj = (Object[]) result.get(0);
			levelValuesDTO.setLevelValue(String.valueOf(obj[0]));
			levelValuesDTO.setHiddenId(String.valueOf(obj[1]));
			levelValuesDTO.setPrimaryKeyColumn(keyRelationBean.getWhereClauseColumn());
		}
		return levelValuesDTO;
	}

	private HierarchyLevelsBean getRelationlinkedLevelValues(GtnWsRelationshipLevelDefinitionBean relationBean,
			HierarchyLevelsBean levelValuesDTO, String tableName, String columnName)
			throws GtnFrameworkGeneralException {
		try {
			GtnFrameworkSingleColumnRelationBean keyRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(tableName, columnName);
			if (keyRelationBean != null) {
				getRelationPrimaryColumnValues(keyRelationBean, relationBean, levelValuesDTO);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getRelationlinkedLevelValues", e);
		}
		return levelValuesDTO;
	}

	public void checkSaveRelationship(GtnWsRelationshipBuilderRequest rbRequest,
			GtnWsRelationshipBuilderResponse rbResponse) throws GtnFrameworkGeneralException {
		try {
			rbResponse.setSuccess(true);
			if (StringUtils.isBlank(rbRequest.getRelationshipName())) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
				rbResponse.setMessage("Please enter Relationship Name.");
				return;
			}
			if (StringUtils.isBlank(rbRequest.getRelationshipDescription())) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
				rbResponse.setMessage("Please enter Relationship Description.");
				return;
			}
			if (rbRequest.getHierarchyDefSId() == 0) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
				rbResponse.setMessage("Please select the Hierarchy Name.");
				return;
			}
			if (rbRequest.getHierarchyVersionNo() == 0) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
				rbResponse.setMessage("Please select the Hierarchy Version.");
				return;
			}
			if (rbRequest.getStartDate() == null) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
				rbResponse.setMessage("Please select Start Date.");
				return;
			}
			if (rbRequest.getRbSysId() == 0 && checkDuplicateRelationshipName(rbRequest)) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
				rbResponse.setMessage("Entered Relationship Name already exists.");
				return;
			}
			if (rbRequest.getRsTreeNodeList() == null || rbRequest.getRsTreeNodeList().isEmpty()) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
				rbResponse.setMessage("Please make a tree.");
				return;
			}
			if (checkHierarchyLevelsToSave(rbRequest.getHierarchyDefSId(), rbRequest.getHierarchyVersionNo(),
					rbRequest.getBuildType())) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
				rbResponse.setMessage("The tree cannot be completed as there are user-defined levels in between");
				return;
			}
			if (checkForDuplicateCompanyTree(rbRequest.getRsTreeNodeList().get(0), rbRequest.getRbSysId(),
					rbRequest.getHierarchyDefSId())) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
				rbResponse.setMessage(
						"Selected company already associated with other relationship. Please select different company for the level 1.");
				return;
			}
			if (checkUsedRelationship(rbRequest.getRbSysId())) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.EDIT);
				rbResponse.setMessage(
						"Cannot Edit the relationship which is already associated with existing projection.");
			}
		} catch (Exception e) {
			rbResponse.setSuccess(false);
			throw new GtnFrameworkGeneralException("Exception in checkSaveRelationship", e);
		}

	}

	@SuppressWarnings("rawtypes")
	private boolean checkDuplicateRelationshipName(GtnWsRelationshipBuilderRequest rbRequest)
			throws GtnFrameworkGeneralException {
		int relationCount = 0;
		try {
			List<String> inputlist = new ArrayList<>();
			inputlist.add(rbRequest.getRelationshipName());
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBSaveCheckRelationshipName"));
			if (result != null && !result.isEmpty()) {
				relationCount = Integer.valueOf(String.valueOf(result.get(0)));
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkDuplicateRelationshipName", e);
		}
		return relationCount != 0;
	}

	@SuppressWarnings("rawtypes")
	public boolean checkForDuplicateCompanyTree(final GtnWsRecordBean companyBean, final int selectedRelationshipId,
			int hierarchySid) throws GtnFrameworkGeneralException {
		boolean companyTreeExists = false;
		try {
			int hierarchyCount = 0;
			List<String> inputlist = new ArrayList<>();
			inputlist.add(String.valueOf(hierarchySid));
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBSaveCheckHierarchyDefinition"));
			if (result != null && !result.isEmpty()) {
				hierarchyCount = Integer.valueOf(String.valueOf(result.get(0)));
			}
			if (hierarchyCount != 0) {
				companyTreeExists = checkForDuplicateTree(companyBean, selectedRelationshipId);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkForDuplicateCompanyTree", e);
		}
		return companyTreeExists;
	}

	@SuppressWarnings("rawtypes")
	public boolean checkForDuplicateTree(final GtnWsRecordBean companyBean, final int selectedRelationshipId)
			throws GtnFrameworkGeneralException {
		boolean companyTreeExists = false;
		try {
			int builderCount = 0;
			List<String> inputlist = new ArrayList<>();
			inputlist
					.add(companyBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
			inputlist.add("1");
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBSaveCheckRelationshipLevelDefinition"));
			if (result != null && !result.isEmpty()) {
				builderCount = Integer.valueOf(String.valueOf(result.get(0)));
			}
			if (builderCount != 0 && (selectedRelationshipId == 0 || builderCount != 1)) {
				companyTreeExists = true;
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkForDuplicateTree", e);
		}
		return companyTreeExists;
	}

	@SuppressWarnings("rawtypes")
	public boolean checkUsedRelationship(final int selectedRelationshipId) throws GtnFrameworkGeneralException {
		boolean relationUsed = false;
		try {
			if (selectedRelationshipId != 0) {
				int custCount = 0;
				int prodCount = 0;
				List<String> inputlist = new ArrayList<>();
				inputlist.add(String.valueOf(selectedRelationshipId));
				List resultList = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBSaveCheckUsedRelationship"));
				if (resultList != null && !resultList.isEmpty()) {
					Object[] result = (Object[]) resultList.get(0);
					custCount = Integer.valueOf(String.valueOf(result[0]));
					prodCount = Integer.valueOf(String.valueOf(result[1]));
				}
				if (custCount > 0 || prodCount > 0) {
					relationUsed = true;
				}
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkUsedRelationship", e);
		}
		return relationUsed;
	}

	@SuppressWarnings("unchecked")
	private void updateRelationshipBuilderFromRequest(RelationshipBuilder relationshipBuilder,
			GtnWsRelationshipBuilderRequest rbRequest, Session session, boolean isDeduction,
			RelationshipBuilder productrelationshipBuilder) throws GtnFrameworkGeneralException {
		try {
			if (!isDeduction) {
				relationshipBuilder.setRelationshipName(rbRequest.getRelationshipName());
				relationshipBuilder.setRelationshipDescription(rbRequest.getRelationshipDescription());
				relationshipBuilder.setHelperTable(
						GtnCommonUtil.getHelperTable(rbRequest.getRelationshipType(), "RELATIONSHIP_TYPE", session));
				relationshipBuilder.setHierarchyDefinition(
						session.load(HierarchyDefinition.class, rbRequest.getHierarchyDefSId()));
				relationshipBuilder.setHierarchyVersion(rbRequest.getHierarchyVersionNo());
				relationshipBuilder.setStartDate(rbRequest.getStartDate());
				relationshipBuilder.setBuildType(rbRequest.getBuildType());
			} else {
				List<Integer> resultsetHierarchy = executeQuery(getQuery("getHierarchyDefSidRB"));
				relationshipBuilder.setRelationshipName(rbRequest.getRelationshipName() + "Deduction");
				relationshipBuilder.setRelationshipDescription(rbRequest.getRelationshipDescription());
				relationshipBuilder.setHelperTable(
						GtnCommonUtil.getHelperTable(rbRequest.getRelationshipType(), "RELATIONSHIP_TYPE", session));
				relationshipBuilder
						.setHierarchyDefinition(session.load(HierarchyDefinition.class, resultsetHierarchy.get(0)));
				relationshipBuilder.setHierarchyVersion(rbRequest.getHierarchyVersionNo());
				relationshipBuilder.setStartDate(rbRequest.getStartDate());
				relationshipBuilder.setBuildType(rbRequest.getBuildType());
				relationshipBuilder.setDeductionRelation(productrelationshipBuilder.getRelationshipBuilderSid());
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in updateRelationshipBuilderFromRequest", e);
		}
	}

	@SuppressWarnings("unchecked")
	public void saveRelationship(GtnWsRelationshipBuilderRequest rbRequest, GtnWsRelationshipBuilderResponse rbResponse)
			throws GtnFrameworkGeneralException {
		rbResponse.setSuccess(true);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<String> inputlist = new ArrayList<>();
		try {
			RelationshipBuilder relationshipBuilder = new RelationshipBuilder();
			Date date = new Date();
			if (rbRequest.getRbSysId() == 0) {
				relationshipBuilder = new RelationshipBuilder();
				relationshipBuilder.setCreatedBy(rbRequest.getUserId());
				relationshipBuilder.setCreatedDate(date);
				relationshipBuilder.setModifiedDate(date);
				relationshipBuilder.setVersionNo(rbRequest.getVersionNo());
			} else {
				relationshipBuilder = session.load(RelationshipBuilder.class, rbRequest.getRbSysId());
				relationshipBuilder = new RelationshipBuilder();
				relationshipBuilder.setRelationshipBuilderSid(rbRequest.getRbSysId());
				relationshipBuilder.setModifiedBy(rbRequest.getUserId());
				relationshipBuilder.setModifiedDate(date);
				relationshipBuilder.setCreatedBy(rbRequest.getCreatedById());
				relationshipBuilder.setCreatedDate(date);
				relationshipBuilder.setVersionNo(rbRequest.getVersionNo() + 1);
			}
			updateRelationshipBuilderFromRequest(relationshipBuilder, rbRequest, session, false, relationshipBuilder);
			session.saveOrUpdate(relationshipBuilder);
			saveRelationshipBuilderLevels(relationshipBuilder, date, "", "", rbRequest.getRsTreeNodeList(), session,
					"");
			tx.commit();
			getSuccessRelationshipBuilder(relationshipBuilder, rbResponse);
			rbResponse.setMessageType("success");
			rbResponse.setMessage("'" + rbRequest.getRelationshipName() + "' has been saved successfully.");
			inputlist.add(String.valueOf(rbRequest.getHierarchyDefSId()));
			List<Object> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getHierarchyCatBySid"));
			if ("Product Hierarchy".equals(result.get(0).toString())) {
				saveDeductionRelationship(rbRequest, rbResponse, relationshipBuilder);
			}

		} catch (Exception e) {
			tx.rollback();
			rbResponse.setSuccess(false);
			rbResponse.setMessageType("fail");
			rbResponse.setMessage(rbRequest.getRelationshipName() + " has not been saved.");
			logger.error("Exception in saveRelationship", e);
			throw new GtnFrameworkGeneralException("Exception in save relationship ", e);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public void saveDeductionRelationship(GtnWsRelationshipBuilderRequest drbRequest,
			GtnWsRelationshipBuilderResponse drbResponse, RelationshipBuilder productrelationshipBuilder)
			throws GtnFrameworkGeneralException {
		drbResponse.setSuccess(true);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			RelationshipBuilder relationBuilder = new RelationshipBuilder();
			Date date = new Date();
			if (drbRequest.getRbSysId() == 0) {
				relationBuilder = new RelationshipBuilder();
				relationBuilder.setCreatedBy(drbRequest.getUserId());
				relationBuilder.setCreatedDate(date);
				relationBuilder.setModifiedDate(date);
				relationBuilder.setVersionNo(drbRequest.getVersionNo());
			} else {
				List<String> input = new ArrayList<>();
				input.add(String.valueOf(drbRequest.getRbSysId()));
				List resultset = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(input, "getProductRelationId"));
				relationBuilder = session.load(RelationshipBuilder.class, Integer.valueOf(resultset.get(0).toString()));
				deletAssociatedHierarchy(relationBuilder, session);
				relationBuilder.setModifiedBy(drbRequest.getUserId());
				relationBuilder.setModifiedDate(date);
				relationBuilder.setCreatedBy(drbRequest.getCreatedById());
				relationBuilder.setCreatedDate(date);
				relationBuilder.setVersionNo(drbRequest.getVersionNo() + 1);
			}
			updateRelationshipBuilderFromRequest(relationBuilder, drbRequest, session, true,
					productrelationshipBuilder);
			session.saveOrUpdate(relationBuilder);
			tx.commit();
			tx = session.beginTransaction();
			saveDeductionRelationshipBuilderLevels(relationBuilder, session, productrelationshipBuilder);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			drbResponse.setSuccess(false);
			drbResponse.setMessageType("fail");
			drbResponse.setMessage(drbRequest.getRelationshipName() + " has not been saved.");
			logger.error("Exception in saveRelationship", e);
			throw new GtnFrameworkGeneralException("Exception in save relationship ", e);
		} finally {
			session.close();
		}
	}

	public void deleteRelationship(GtnWsRelationshipBuilderRequest rbRequest,
			GtnWsRelationshipBuilderResponse rbResponse) throws GtnFrameworkGeneralException {
		rbResponse.setSuccess(true);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			if (checkUsedRelationship(rbRequest.getRbSysId())) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.DELETE);
				rbResponse.setMessage(
						"Cannot Delete the relationship which is already associated with existing projection.");
				return;
			}
			RelationshipBuilder relationshipBuilder = session.load(RelationshipBuilder.class, rbRequest.getRbSysId());
			deletAssociatedHierarchy(relationshipBuilder, session);
			session.delete(relationshipBuilder);
			tx.commit();
			rbResponse.setMessageType("success");
			rbResponse.setMessage(rbRequest.getRelationshipName() + " has been deleted Successfully.");
		} catch (Exception e) {
			tx.rollback();
			rbResponse.setSuccess(false);
			rbResponse.setMessageType(GtnFrameworkCommonStringConstants.DELETE);
			rbResponse.setMessage(rbRequest.getRelationshipName() + " has not been deleted.");
			logger.error("Exception in deleteRelationship", e);
			throw new GtnFrameworkGeneralException("Exception in deleteRelationship ", e);
		} finally {
			session.close();
		}
	}

	private void getSuccessRelationshipBuilder(RelationshipBuilder relationshipBuilder,
			GtnWsRelationshipBuilderResponse rbResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsRecordBean levelBean = new GtnWsRecordBean();
			List<Object> properties = new ArrayList<>();
			properties.add(relationshipBuilder.getRelationshipName());
			properties.add(relationshipBuilder.getRelationshipDescription());
			properties.add(relationshipBuilder.getHelperTable().getDescription());
			properties.add(relationshipBuilder.getHierarchyDefinition().getHierarchyName());
			properties.add(relationshipBuilder.getVersionNo());
			properties.add(relationshipBuilder.getStartDate());
			properties.add(relationshipBuilder.getCreatedDate());
			properties.add(relationshipBuilder.getModifiedDate());
			properties.add("");
			properties.add(relationshipBuilder.getRelationshipBuilderSid());
			properties.add(relationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid());
			properties.add(relationshipBuilder.getBuildType());
			properties.add(relationshipBuilder.getHierarchyVersion());
			properties.add(relationshipBuilder.getHierarchyDefinition().getNoOfLevels());
			properties.add(relationshipBuilder.getCreatedBy());
			properties.add(relationshipBuilder.getHelperTable().getHelperTableSid());
			levelBean.setProperties(properties);
			rbResponse.setMainNode(levelBean);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getSuccessRelationshipBuilder", e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void saveRelationshipBuilderLevels(RelationshipBuilder relationshipBuilder, Date date,
			String parentHierarchyNo, String parentNode, List<GtnWsRecordBean> levelBeanList, Session session,
			String pathMasterSid) throws GtnFrameworkGeneralException {
		try {
			int parentNodeIndex = 0;
			List list = new ArrayList<>();
			for (GtnWsRecordBean levelBean : levelBeanList) {
				HierarchyLevelsBean hierarchyLevelDTO = getHierarchyLevelsBeanFromRecordBean(levelBean);
				if ("NDC".equals(hierarchyLevelDTO.getLevelName())) {
					list.add(hierarchyLevelDTO.getHiddenId());
				}
				RelationshipLevelDefinition relationshipLevel = new RelationshipLevelDefinition();
				relationshipLevel.setRelationshipBuilder(relationshipBuilder);
				relationshipLevel.setHierarchyLevelDefinition(
						session.load(HierarchyLevelDefinition.class, hierarchyLevelDTO.getHierarchyLevelSystemId()));
				relationshipLevel.setRelationshipLevelValues(hierarchyLevelDTO.getHiddenId());
				relationshipLevel.setLevelNo(hierarchyLevelDTO.getLevelNo());
				relationshipLevel.setLevelName(hierarchyLevelDTO.getLevelName());
				relationshipLevel.setParentNode(GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(parentNode)
						? Integer.toString(parentNodeIndex) : parentNode);
				String hierarchyNo = parentHierarchyNo + hierarchyLevelDTO.getHiddenId() + ".";
				relationshipLevel.setHierarchyNo(relationshipBuilder.getRelationshipBuilderSid() + "-" + hierarchyNo);
				relationshipLevel.setCreatedDate(date);
				relationshipLevel.setModifiedDate(date);
				relationshipLevel.setCreatedBy(relationshipBuilder.getCreatedBy());
				relationshipLevel.setModifiedBy(relationshipBuilder.getModifiedBy());
				relationshipLevel.setVersionNo(relationshipBuilder.getVersionNo());
				String parentPathMasterSid;
				if (pathMasterSid.isEmpty()) {
					parentPathMasterSid = hierarchyLevelDTO.getHiddenId();
				} else {
					parentPathMasterSid = pathMasterSid + GtnFrameworkWebserviceConstant.STRING_TILT
							+ hierarchyLevelDTO.getHiddenId();
				}
				relationshipLevel.setParentHierarchyNo(relationshipBuilder.getRelationshipBuilderSid()
						+ GtnFrameworkWebserviceConstant.STRING_TILT + parentPathMasterSid);
				session.saveOrUpdate(relationshipLevel);

				if (levelBean.getChildList() != null && !levelBean.getChildList().isEmpty()) {
					saveRelationshipBuilderLevels(relationshipBuilder, date, hierarchyNo,
							hierarchyLevelDTO.getLevelNo() + GtnFrameworkWebserviceConstant.STRING_TILT
									+ hierarchyLevelDTO.getHiddenId(),
							levelBean.getChildList(), session, parentPathMasterSid);
				}
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save RelationshipLevelDefinition", e);
		}
	}

	public void saveDeductionRelationshipBuilderLevels(RelationshipBuilder relationshipBuilder, Session session,
			RelationshipBuilder productrelationshipBuilder) throws GtnFrameworkGeneralException {
		try {
			getSelectQuery(relationshipBuilder, productrelationshipBuilder, session);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save RelationshipLevelDefinition", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectQuery(RelationshipBuilder relationshipBuilder,
			RelationshipBuilder productrelationshipBuilder, Session session) throws GtnFrameworkGeneralException {
		List<String> resultsetinput = new ArrayList<>();
		List<String> resultsetinput1 = new ArrayList<>();
		List<Object> input = new ArrayList<>();
		Map<Integer, Integer> hierarchyLevelMap = new HashMap<>();
		Map<Integer, Object> hierarchyLevelNameMap = new HashMap<>();
		String itemMastersids = StringUtils.EMPTY;

		List<HierarchyLevelDefinitionBean> hierarchyList = getRBHierarchyLevelDefinitionBySid(
				productrelationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid(),
				productrelationshipBuilder.getHierarchyVersion(),
				productrelationshipBuilder.getRelationshipBuilderSid());

		int levelNo = HierarchyLevelDefinitionBean.getLastLinkedLevel(hierarchyList);

		HierarchyLevelDefinitionBean lastLevelBean = HierarchyLevelDefinitionBean.getBeanByLevelNo(levelNo,
				hierarchyList);
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(lastLevelBean.getTableName(),
						lastLevelBean.getFieldName());
		List<String> whereClauseColumn = hierarchyService.getMappingColumns(keyBean);
		List<String> whereQueries = getRelationQueries(productrelationshipBuilder.getRelationshipBuilderSid(),
				hierarchyList.subList(0, levelNo), productrelationshipBuilder.getVersionNo());
		HierarchyLevelDefinitionBean destinationHierarchyBean = gtnWsRelationshipBuilderHierarchyFileGenerator
				.getHierarchyBeanByLevelNo(hierarchyList, levelNo);
		if (destinationHierarchyBean != null) {
			GtnFrameworkFileReadWriteService fileReadWriteService = new GtnFrameworkFileReadWriteService();
			GtnFrameworkHierarchyQueryBean queryBean = fileReadWriteService.getQueryFromFile(
					destinationHierarchyBean.getHierarchyDefinitionSid(),
					destinationHierarchyBean.getHierarchyLevelDefinitionSid(), destinationHierarchyBean.getVersionNo());
			GtnFrameworkQueryGeneratorBean finalQueryBean = queryBean.getQuery();
			finalQueryBean.addSelectClauseBean(GtnWsRelationshipBuilderConstants.ITEM_MASTER_TABLE_COLUMN_NAME, null,
					Boolean.TRUE, null);
			finalQueryBean.addWhereClauseBean(whereClauseColumn.get(1), null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.NULL_ALLOWED, "?");

			String finalQuery = finalQueryBean.generateQuery();

			List<Object[]> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(whereQueries, finalQuery));
			for (Object[] objects : result) {
				input.add(String.valueOf(objects[objects.length - 1]));
				itemMastersids = Arrays.toString(input.toArray()).replace("[", StringUtils.EMPTY).replace("]",
						StringUtils.EMPTY);
			}
		}
		resultsetinput.add(itemMastersids);
		List<Object[]> resultset = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(resultsetinput, "getDeductionData"));
		resultsetinput1.add(String.valueOf(relationshipBuilder.getRelationshipBuilderSid()));
		resultsetinput1.add(String.valueOf(relationshipBuilder.getHierarchyVersion()));
		List<Integer[]> resultsetHierarchy = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(resultsetinput1, "getHierarchyDefSid"));
		for (int i = 0; i < resultsetHierarchy.size(); i++) {
			final Object[] obj = resultsetHierarchy.get(i);
			hierarchyLevelMap.put(Integer.valueOf(obj[0].toString()), Integer.valueOf(obj[1].toString()));
			hierarchyLevelNameMap.put(Integer.valueOf(obj[0].toString()), obj[2]);
		}
		generateHierarchyNumber(resultset, relationshipBuilder, session, hierarchyLevelMap, hierarchyLevelNameMap);
		return resultset;
	}

	@SuppressWarnings("unchecked")
	public void deletAssociatedHierarchy(RelationshipBuilder relationshipBuilder, Session session)
			throws GtnFrameworkGeneralException {
		try {
			Criteria cr = session.createCriteria(RelationshipLevelDefinition.class)
					.add(Restrictions.eq("relationshipBuilder", relationshipBuilder));
			List<RelationshipLevelDefinition> results = cr.list();
			if (results != null && !results.isEmpty()) {
				for (RelationshipLevelDefinition relationshipLevel : results) {
					session.delete(relationshipLevel);
				}
				session.flush();
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in deleting RelationshipLevelDefinition", e);
		}
	}

	public void sortGtnWsRecordBean(List<GtnWsRecordBean> levelsList) {
		Collections.sort(levelsList, new Comparator<GtnWsRecordBean>() {
			@Override
			public int compare(final GtnWsRecordBean lhs, GtnWsRecordBean rhs) {
				try {
					int lhsLevel = lhs
							.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal());
					int rhsLevel = rhs
							.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal());
					if (lhsLevel == rhsLevel) {
						String lhsHierarchyNo = lhs
								.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal());
						String rhsHierarchyNo = rhs
								.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal());
						String lhsStr = lhsHierarchyNo.endsWith(".")
								? lhsHierarchyNo.substring(0, lhsHierarchyNo.lastIndexOf('.')) : lhsHierarchyNo;
						String rhsStr = rhsHierarchyNo.endsWith(".")
								? rhsHierarchyNo.substring(0, rhsHierarchyNo.lastIndexOf('.')) : rhsHierarchyNo;
						int lhsHiddenId = Integer.parseInt(lhsStr.substring(lhsStr.lastIndexOf('.') + 1));
						int rhsHiddenId = Integer.parseInt(rhsStr.substring(rhsStr.lastIndexOf('.') + 1));
						return lhsHiddenId - rhsHiddenId;
					}
					return lhsLevel - rhsLevel;
				} catch (Exception e) {
					logger.error("Exception in sortGtnWsRecordBean", e);
				}
				return 0;
			}
		});
	}

	public GtnWsRecordBean getGtnWsRecordBean(HierarchyLevelsBean levelBean) {
		GtnWsRecordBean dto = new GtnWsRecordBean();
		Object value = GtnWsRelationshipBuilderConstants.VALUE_PROPERTY_ID;
		dto.setRecordHeader(Arrays.asList(value));
		List<Object> properties = Arrays.<Object>asList(levelBean.getLevelValue(), levelBean.getLevelNo(),
				levelBean.getLevelName(), levelBean.getParentNode(), levelBean.getPrimaryKeyColumn(),
				levelBean.getHierarchyLevelSystemId(), levelBean.getRelationshipLevelSystemId(),
				levelBean.getHiddenId(), levelBean.getHierarchyNo(), levelBean.getLevelValueReference(), false);
		dto.setProperties(properties);
		dto.addAdditionalProperty(levelBean.getLevelNo());
		return dto;
	}

	public List<Object> getLevelBeanAsList(HierarchyLevelsBean levelBean) {
		List<Object> dataRowColumnValueList = new ArrayList<>();
		dataRowColumnValueList.add(levelBean.getLevelValue());
		dataRowColumnValueList.add(levelBean.getLevelNo());
		dataRowColumnValueList.add(levelBean.getLevelName());
		dataRowColumnValueList.add(levelBean.getParentNode());
		dataRowColumnValueList.add(levelBean.getPrimaryKeyColumn());
		dataRowColumnValueList.add(levelBean.getHierarchyLevelSystemId());
		dataRowColumnValueList.add(levelBean.getRelationshipLevelSystemId());
		dataRowColumnValueList.add(levelBean.getHiddenId());
		dataRowColumnValueList.add(levelBean.getHierarchyNo());
		dataRowColumnValueList.add(levelBean.getLevelValueReference());
		return dataRowColumnValueList;
	}

	public HierarchyLevelsBean getHierarchyLevelsBeanFromRecordBean(GtnWsRecordBean recordBean) {
		HierarchyLevelsBean dto = new HierarchyLevelsBean();
		dto.setLevelValue(recordBean.getStringPropertyByIndex(0));
		dto.setLevelNo(recordBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
		dto.setLevelName(recordBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NAME.ordinal()));
		dto.setParentNode(
				recordBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.PARENT_NODE.ordinal()));
		dto.setPrimaryKeyColumn(
				recordBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.PRIMARY_COLUMN.ordinal()));
		dto.setHierarchyLevelSystemId(recordBean
				.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_LEVEL_SID.ordinal()));
		dto.setRelationshipLevelSystemId(
				recordBean.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.RELATION_LEVEL_SID.ordinal()));
		dto.setHiddenId(recordBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
		dto.setHierarchyNo(
				recordBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal()));
		return dto;
	}

	private GtnUIFrameworkDataTable setLevelValueBean(List<Object[]> result,
			HierarchyLevelDefinitionBean destinationHierarchyBean, String nextPrimayKey, List<String> hiddenIdList) {
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		if (result != null && !result.isEmpty()) {
			for (int i = 0; i < result.size(); i++) {
				Object[] obj = result.get(i);
				if (!hiddenIdList.contains(String.valueOf(obj[1]))) {
					HierarchyLevelsBean levelValuesDTO = new HierarchyLevelsBean();
					levelValuesDTO.setLevelValue(String.valueOf(obj[0]));
					levelValuesDTO.setHiddenId(String.valueOf(obj[1]));
					levelValuesDTO.setHierarchyLevelSystemId(destinationHierarchyBean.getHierarchyLevelDefinitionSid());
					levelValuesDTO.setParentNode("0");
					levelValuesDTO.setRelationshipLevelSystemId(Integer.valueOf("0"));
					levelValuesDTO.setLevelName(destinationHierarchyBean.getLevelName());
					levelValuesDTO.setLevelNo(String.valueOf(destinationHierarchyBean.getLevelNo()));
					levelValuesDTO.setPrimaryKeyColumn(nextPrimayKey);
					levelValuesDTO.setLevelValueReference(destinationHierarchyBean.getLevelValueReference());
					GtnUIFrameworkDataRow newDataRow = new GtnUIFrameworkDataRow();
					newDataRow.setColList(getLevelBeanAsList(levelValuesDTO));
					dataTable.addDataRow(newDataRow);
				}
			}
		}
		return dataTable;

	}

	public void generateHierarchyNumber(List<Object[]> resultSet, RelationshipBuilder relationshipBuilder,
			Session session, Map<Integer, Integer> hierarchyLevelMap, Map<Integer, Object> hierarchyLevelNameMap) {
		Map<Integer, GtnWsHierarchyBean> levelMap = new HashMap<>();
		Date date = new Date();
		String pathMasterSid = "";
		for (int i = 0; i < resultSet.size(); i++) {
			StringBuilder previousRelationshipValue = new StringBuilder();
			getDeductionHierarchyNo(resultSet.get(i), levelMap, previousRelationshipValue);
		}
		List<GtnWsSortedHierarchyBean> veryFinalValue = new ArrayList<>();
		for (Map.Entry<Integer, GtnWsHierarchyBean> entry : levelMap.entrySet()) {
			GtnWsHierarchyBean dto = entry.getValue();
			for (int i = 0; i < dto.getHierarchyNo().size(); i++) {
				GtnWsSortedHierarchyBean sortValue = new GtnWsSortedHierarchyBean();
				sortValue.setHierarchyDetails(dto.getRelationshipLevelValues().get(i)
						+ GtnFrameworkWebserviceConstant.STRING_HASH + dto.getHierarchyNo().get(i)
						+ GtnFrameworkWebserviceConstant.STRING_HASH + dto.getParentNode().get(i));
				veryFinalValue.add(sortValue);
			}
		}
		Collections.sort(veryFinalValue);
		for (GtnWsSortedHierarchyBean string : veryFinalValue) {
			String[] bits = string.getSortedHierarchy().split("#");
			String lastOne = bits[bits.length - 1];
			String value = lastOne.contains(GtnFrameworkWebserviceConstant.STRING_TILT)
					? lastOne.split(GtnFrameworkWebserviceConstant.STRING_TILT)[0] : lastOne;
			int n1 = Integer.parseInt(value.trim());
			int n3 = n1 + 1;
			RelationshipLevelDefinition relationshipLevel = new RelationshipLevelDefinition();
			relationshipLevel.setRelationshipBuilder(relationshipBuilder);
			relationshipLevel.setHierarchyLevelDefinition(
					session.load(HierarchyLevelDefinition.class, hierarchyLevelMap.get(n3)));
			relationshipLevel.setRelationshipLevelValues(string.getSortedHierarchy().split("#")[0]);
			relationshipLevel.setLevelNo(String.valueOf(n3));
			relationshipLevel.setLevelName(hierarchyLevelNameMap.get(n3).toString());
			relationshipLevel.setParentNode(lastOne.trim());
			relationshipLevel.setHierarchyNo(relationshipBuilder.getRelationshipBuilderSid() + "-"
					+ string.getSortedHierarchy().split("#")[1].trim());
			relationshipLevel.setCreatedDate(date);
			relationshipLevel.setModifiedDate(date);
			relationshipLevel.setCreatedBy(relationshipBuilder.getCreatedBy());
			relationshipLevel.setModifiedBy(relationshipBuilder.getModifiedBy());
			relationshipLevel.setVersionNo(relationshipBuilder.getVersionNo());
			String parentPathMasterSid;
			if (pathMasterSid.isEmpty()) {
				parentPathMasterSid = string.getSortedHierarchy().split("#")[0];
			} else {
				parentPathMasterSid = pathMasterSid.trim() + GtnFrameworkWebserviceConstant.STRING_TILT
						+ string.getSortedHierarchy().split("#")[0].trim();
			}
			pathMasterSid = parentPathMasterSid;
			relationshipLevel.setParentHierarchyNo((relationshipBuilder.getRelationshipBuilderSid()
					+ GtnFrameworkWebserviceConstant.STRING_TILT + parentPathMasterSid).trim());
			session.saveOrUpdate(relationshipLevel);

		}
	}

	private void getDeductionHierarchyNo(Object[] objects, Map<Integer, GtnWsHierarchyBean> levelMap,
			StringBuilder previousRelationshipValue) {
		int levelNo = 0;
		boolean isHierarchyNeeded = false;
		for (int i = 0; i < objects.length; i++) {
			Object object = objects[i];
			previousRelationshipValue.append(object).append(GtnFrameworkWebserviceConstant.STRING_TILT);
			if (levelMap.get(levelNo) == null) {
				GtnWsHierarchyBean dto = new GtnWsHierarchyBean();
				getParentDto(dto, levelNo, levelMap);
				dto.addHierarchyNo(getHierarchyNo(null, true, dto.getParentHierarchyNo()));
				dto.addRelationshipLevelValues(object);
				dto.addParentRelationshipLevelValues(previousRelationshipValue.toString());
				dto.setLevelNo(levelNo);
				levelMap.put(levelNo++, dto);
			} else {
				GtnWsHierarchyBean parentdto = null;
				parentdto = levelNo > 0 ? levelMap.get(levelNo - 1) : null;

				GtnWsHierarchyBean dto = levelMap.get(levelNo++);
				if (!dto.getRelationshipLevelValues().contains(object) || isHierarchyNeeded
						|| !dto.getParentRelationshipLevelValues().contains(previousRelationshipValue.toString())) {
					dto.addRelationshipLevelValues(object);
					getChildHierarchyNo(parentdto, dto, levelNo);
					dto.addHierarchyNo(
							getHierarchyNo(dto.getHierarchyNo(), isHierarchyNeeded, dto.getParentHierarchyNo()));
					dto.addParentRelationshipLevelValues(previousRelationshipValue.toString());
					dto.setLevelNo(levelNo > 0 ? levelNo - 1 : 0);
					isHierarchyNeeded = true;
				}
			}
		}
	}

	private void getChildHierarchyNo(GtnWsHierarchyBean parentdto, GtnWsHierarchyBean dto, int levelNo) {
		if (parentdto != null) {
			dto.setParentHierarchyNo(
					String.valueOf(parentdto.getHierarchyNo().get(parentdto.getHierarchyNo().size() - 1)));
			dto.addParentNode(levelNo - 1 + GtnFrameworkWebserviceConstant.STRING_TILT + String.valueOf(
					parentdto.getRelationshipLevelValues().get(parentdto.getRelationshipLevelValues().size() - 1)));
		} else if (levelNo - 1 == 0) {
			dto.addParentNode(GtnFrameworkWebserviceConstant.ZERO);
		}

	}

	private void getParentDto(GtnWsHierarchyBean dto, int levelNo, Map<Integer, GtnWsHierarchyBean> levelMap) {
		if (levelNo > 0) {
			GtnWsHierarchyBean parentdto = levelMap.get(levelNo - 1);
			dto.setParentHierarchyNo(
					String.valueOf(parentdto.getHierarchyNo().get(parentdto.getHierarchyNo().size() - 1)));
			dto.addParentNode(levelNo + GtnFrameworkWebserviceConstant.STRING_TILT + String.valueOf(
					parentdto.getRelationshipLevelValues().get(parentdto.getRelationshipLevelValues().size() - 1)));
		} else {
			dto.addParentNode(GtnFrameworkWebserviceConstant.ZERO);
		}

	}

	private int setNewHierarchyNo(List<String> lis) {
		if (!lis.isEmpty()) {
			List<Integer> numberList = new ArrayList<>();
			for (String string : lis) {
				String[] splitted = string.split("\\.");
				numberList.add(Integer.valueOf(splitted[splitted.length - 1]));
			}
			Collections.sort(numberList);
			return numberList.get(numberList.size() - 1) + 1;
		} else {
			return 0;
		}
	}

	private String getHierarchyNo(List<String> lis, boolean isNewLevelNo, String parentHierarchyNo) {
		String hierNo = "";
		if (isNewLevelNo) {
			hierNo = parentHierarchyNo + "1.";
		} else {
			hierNo = parentHierarchyNo + setNewHierarchyNo(lis) + ".";
		}
		return hierNo;
	}

	private List<String> getRelationQueries(int relationshipSid,
			List<HierarchyLevelDefinitionBean> levelHierarchyLevelDefinitionList, int versionNo) {
		List<String> queryList = new ArrayList<>();
		List<String> input = new ArrayList<>();
		for (HierarchyLevelDefinitionBean levelDto : levelHierarchyLevelDefinitionList) {
			if (!levelDto.isUserDefined()) {
				input.add(String.valueOf(levelDto.getLevelNo()));
				input.add(String.valueOf(relationshipSid));
				input.add(String.valueOf(versionNo));
				String relationQuery = gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(input, "relationShipSubQueryForSubQuery");
				queryList.add(relationQuery);
				input.clear();
			}
		}
		return queryList;
	}

	@SuppressWarnings("unchecked")
	public List<HierarchyLevelDefinitionBean> getRBHierarchyLevelDefinitionBySid(int hierarchyDefSid, int versionNo,
			int prodRelationShipBuilderSid) throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(String.valueOf(prodRelationShipBuilderSid));
		inputlist.add(String.valueOf(hierarchyDefSid));
		inputlist.add(String.valueOf(versionNo));
		List<Object[]> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyLevelDefinitionByProductRelationSid"));
		return gtnWsRelationshipBuilderHierarchyFileGenerator.gettHierarchyLevelDefinitionListMain(result);
	}

	@SuppressWarnings("unchecked")
	private List<Integer> getNotInList(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsSearchRequest searchRequest = gtnWsRequest.getGtnWsSearchRequest();
		List<GtnWebServiceSearchCriteria> searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList();
		return (List<Integer>) searchCriteria.get(5).getFilterValue3();
	}

	@SuppressWarnings("unchecked")
	public List<String> getRBHierarchyLevelNameList(int hierarchyDefSid, int versionNo)
			throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(String.valueOf(hierarchyDefSid));
		inputlist.add(String.valueOf(versionNo));
		return executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyLevelNameList"));
	}

	private boolean checkHierarchyLevelsToSave(int hierarchyDefSid, int hierarchyVersion, String buildType)
			throws GtnFrameworkGeneralException {
		boolean saveFlag = false;
		List<String> hierarchyLevels = getRBHierarchyLevelNameList(hierarchyDefSid, hierarchyVersion);
		if ("Automatic".equals(buildType) && hierarchyLevels != null && !hierarchyLevels.isEmpty()
				&& !checkUserDefinedCondition(hierarchyLevels)) {
			saveFlag = true;
		}
		return saveFlag;
	}

	private boolean checkUserDefinedCondition(List<String> hierarchyLevels) {
		return hierarchyLevels.contains(GtnFrameworkWebserviceConstant.USER_DEFINED)
				&& (hierarchyLevels.lastIndexOf(GtnFrameworkWebserviceConstant.USER_DEFINED) == 0 || hierarchyLevels
						.lastIndexOf(GtnFrameworkWebserviceConstant.USER_DEFINED) == hierarchyLevels.size() - 1);
	}

}
