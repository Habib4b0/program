/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkFileReadWriteService;
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
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.HierarchyDefinition;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.HierarchyLevelDefinition;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipLevelDefinition;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.logic.GtnWsSearchQueryGenerationLogic;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
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
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

/**
 *
 * @author Mahesh.James
 */
@Service
@Scope(value = "singleton")
public class GtnWsRelationshipBuilderService {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsRelationshipBuilderService.class);

	@Autowired
	private GtnWsRelationshipBuilderHelperService helperLogic;

	@Autowired
	private GtnWsRelationshipBuilderHierarchyFileGeneratorService gtnWsRelationshipBuilderHierarchyFileGenerator;

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
	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService autoMaticRelationService;

	@Autowired
	private GtnFrameworkAutomaticService automaticService;

	private static final String HELPER_JOIN_DESCRIPTION = "HELPER_JOIN .DESCRIPTION";

	public GtnWsRelationshipBuilderService() {
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

	public GtnWsRelationshipBuilderHierarchyFileGeneratorService getGtnWsRelationshipBuilderHierarchyFileGenerator() {
		return gtnWsRelationshipBuilderHierarchyFileGenerator;
	}

	public void setGtnWsRelationshipBuilderHierarchyFileGenerator(
			GtnWsRelationshipBuilderHierarchyFileGeneratorService gtnWsRelationshipBuilderHierarchyFileGenerator) {
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
			hierarchyLevelBean.customize(result);
			hierarchyList.add(hierarchyLevelBean);
		}
		Collections.sort(hierarchyList);
		return hierarchyList;
	}

	@SuppressWarnings({ "unchecked" })
	public GtnWsRelationshipBuilderResponse getHierarchyVersionNo(GtnWsRelationshipBuilderRequest rbRequest,
			GtnWsRelationshipBuilderResponse rbResponse) throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(GtnFrameworkWebserviceConstant.HIST);
		inputlist.add(String.valueOf(rbRequest.getHierarchyDefSId()));
		List<Integer> result = (List<Integer>) executeQuery(
				gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyVersionNo"));
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
		List<Object[]> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
				"getRBHierarchyLevelDefinitionByLevelNo"));
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
		List<Object[]> result = executeQuery(
				gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyDefinition"));
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
		List<Object[]> result = executeQuery(
				gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist, "getRBHierarchyDefinition"));
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
		StringBuilder hierarcgylevelValuequeryBuilder = new StringBuilder(gtnWsRelationshipBuilderHierarchyFileGenerator
				.getQueryReplaced(inputlist, "getRBHierarchyLevelValueByLevelNo"));
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
		List<Object[]> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
				"getRBRelationshipLevelDefinition"));
		if (result != null) {
			rbResponse.setRelationshipBuilderBeanList(setHistRelationshipLevelDefinitionValues(result));
		}
		return rbResponse;
	}

	public GtnUIFrameworkDataTable getUserDefinedLevelValues(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			List<Integer> modifiedHiddenIdList, int levelNo) throws GtnFrameworkGeneralException {
		GtnUIFrameworkDataTable datTableData = new GtnUIFrameworkDataTable();
		List<HierarchyLevelValuesBean> heirarchyLevelValueBeanList = getHistHierarchyLevelDefinitionValuesByLevelNo(
				gtnWsRequest, levelNo);
		for (int i = 0; i < heirarchyLevelValueBeanList.size(); i++) {
			final HierarchyLevelValuesBean valueBean = heirarchyLevelValueBeanList.get(i);
			if (!modifiedHiddenIdList.contains(valueBean.getHierarchyLevelValuesSid())) {
				HierarchyLevelsBean levelValuesDTO = new HierarchyLevelsBean();
				levelValuesDTO.setLevelValue(valueBean.getLevelValues());
				levelValuesDTO.setHiddenId(String.valueOf(valueBean.getHierarchyLevelValuesSid()));
				levelValuesDTO.setHierarchyLevelSystemId(valueBean.getHierarchyLevelDefinitionSid());
				levelValuesDTO.setParentNode("0");
				levelValuesDTO.setRelationshipLevelSystemId(Integer.parseInt("0"));
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

	@SuppressWarnings("unchecked")
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
			List<Object[]> levelValues = new ArrayList<>();
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
				Object[] obj = levelValues.get(i);
				HierarchyLevelsBean levelValuesDTO = new HierarchyLevelsBean();
				levelValuesDTO.setLevelValue(String.valueOf(obj[0]));
				levelValuesDTO.setHiddenId(String.valueOf(obj[1]));
				levelValuesDTO.setParentNode("0");
				levelValuesDTO.setRelationshipLevelSystemId(Integer.parseInt("0"));
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
		List<Integer> modifiedHiddenList = getModifiedHiddenIdList(gtnWsRequest);
		if (GtnFrameworkWebserviceConstant.USER_DEFINED
				.equals(hierarchyLevelDefinitionList.get(0).getLevelValueReference())) {
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				searchResponse.setCount(getUserDefinedLevelCount(gtnWsRequest, levelNo));
			} else {
				searchResponse.setResultSet(getUserDefinedLevelValues(gtnWsRequest, modifiedHiddenList, levelNo));
			}

		} else {
			return getFilteredValue2(gtnWsRequest, modifiedHiddenList, levelNo);
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

		String counQuery = gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
				"getRBHierarchyLevelValueByLevelNoCount") + getUserFilterClause(gtnWsRequest);
		counQuery = counQuery.replaceAll(GtnWsRelationshipBuilderConstants.VALUE_PROPERTY_ID, "HLV.LEVEL_VALUES");
		@SuppressWarnings("unchecked")
		List<Object[]> result = executeQuery(counQuery);
		if (result != null) {
			return Integer.parseInt(String.valueOf(result.get(0)));
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
			List<Integer> modifiedHiddenList, int levelNo) throws GtnFrameworkGeneralException {
		GtnSerachResponse serachResponse = new GtnSerachResponse();
		try {
			List<HierarchyLevelDefinitionBean> hierarchyList = gtnWsRelationshipBuilderHierarchyFileGenerator
					.getRBHierarchyLevelDefinitionBySid(
							Integer.parseInt(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList()
									.get(0).getFilterValue1()),
							Integer.parseInt(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList()
									.get(1).getFilterValue1()));
			HierarchyLevelDefinitionBean destinationHierarchyBean = gtnWsRelationshipBuilderHierarchyFileGenerator
					.getHierarchyBeanByLevelNo(hierarchyList, levelNo);
			if (destinationHierarchyBean != null) {
				GtnFrameworkFileReadWriteService fileReadWriteService = new GtnFrameworkFileReadWriteService();
				GtnFrameworkHierarchyQueryBean queryBaen = fileReadWriteService.getQueryFromFile(
						destinationHierarchyBean.getHierarchyDefinitionSid(),
						destinationHierarchyBean.getHierarchyLevelDefinitionSid(),
						destinationHierarchyBean.getVersionNo());
				GtnFrameworkQueryGeneratorBean finalQueryBean = queryBaen.getQuery();
				List<Object> primaryKeyPositionList = helperLogic.getMasterSidList(gtnWsRequest, hierarchyList,
						levelNo);

				logger.info("finalQuery--->>" + finalQueryBean.generateQuery());

				GtnFrameworkSelectColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
						.getKeyRelationBeanUsingTableIdAndColumnName(destinationHierarchyBean.getTableName(),
								destinationHierarchyBean.getFieldName());
				hierarchyService.getInboundRestrictionQueryForAutoUpdate(finalQueryBean);
				List<Integer> notInList = getNotInList(gtnWsRequest);
				notInList.addAll(modifiedHiddenList);
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
				appendHelperTableDescriptionRestriction(queryBuilder, "AND");
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
				printFinalQuery(query);
				List<Object[]> result = executeQuery(query, primaryKeyPositionList.toArray(), datatypes);

				String nextPrimayKey = keyBean.getActualColumnName();
				GtnUIFrameworkDataTable dataTable = setLevelValueBean(result, destinationHierarchyBean, nextPrimayKey);
				serachResponse.setCount(dataTable.getDataTable().size());
				serachResponse.setResultSet(dataTable);
			}
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in getFilteredValue", ex);
		}
		return serachResponse;
	}

	private void appendHelperTableDescriptionRestriction(StringBuilder queryBuilder, String append) {
		if (queryBuilder.toString().contains(HELPER_JOIN_DESCRIPTION)) {
			queryBuilder.append(" " + append + " HELPER_JOIN.DESCRIPTION  <> '-SELECT ONE-' ");
		}
	}

	private void printFinalQuery(String query) {
		if (query.contains(HELPER_JOIN_DESCRIPTION)) {
			logger.info("finalQuery concat--->>" + query);
		}
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

	@SuppressWarnings("unchecked")
	public List<Integer> getModifiedHiddenIdList(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		List<Integer> modifiedHiddenIdList = new ArrayList<>();
		if (gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().size() > 2 && gtnWsRequest
				.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(2).getFilterValue3() != null) {
			for (Map<String, Object> levelBeanMap : (List<Map<String, Object>>) gtnWsRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList().get(2).getFilterValue3()) {
				GtnWsRecordBean levelBean = new ObjectMapper().convertValue(levelBeanMap, GtnWsRecordBean.class);
				modifiedHiddenIdList.add(
						levelBean.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
			}
		}
		return modifiedHiddenIdList;
	}

	public GtnWsRelationshipBuilderResponse loadRelationship(GtnWsRelationshipBuilderRequest rbRequest,
			GtnWsRelationshipBuilderResponse rbResponse) throws GtnFrameworkGeneralException, InterruptedException {
		automaticService.waitForRelaitonUpdatetoFinish();
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

	public List<GtnWsRecordBean> loadAutoBuildData(int hierarchyDefSid, int hierarchyVersionNo,
			GtnWsRecordBean selectedTreeBean, List<String> hiddenIdList)
			throws GtnFrameworkGeneralException {
		List<HierarchyLevelDefinitionBean> hierarchyList = gtnWsRelationshipBuilderHierarchyFileGenerator
				.getRBHierarchyLevelDefinitionBySid(hierarchyDefSid, hierarchyVersionNo);

		String gethiddenIdhierarchyNo = getHierarchyNoToBuildTree(hiddenIdList);
		selectedTreeBean.setPropertyValueByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal(),
				gethiddenIdhierarchyNo);
		List<GtnWsRecordBean> recordBeanList = getNextlevelDataForAutoBuild(hierarchyList, selectedTreeBean,
				hiddenIdList);
		sortGtnWsRecordBean(recordBeanList);
		Map<String, GtnWsRecordBean> finalSavedLevelsList1 = new HashMap<>();
		finalSavedLevelsList1.put(gethiddenIdhierarchyNo, selectedTreeBean);
		for (int i = 0; i < recordBeanList.size(); i++) {
			GtnWsRecordBean levelBean = recordBeanList.get(i);
			finalSavedLevelsList1.put(
					levelBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal()),
					levelBean);
		}
		for (int i = 0; i < recordBeanList.size(); i++) {
			GtnWsRecordBean treeNode = recordBeanList.get(i);
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
		}

		return selectedTreeBean.getChildList();
	}

	private String getHierarchyNoToBuildTree(List<String> finalMasterSid) {
		List<String> tempMasterSid = new ArrayList<>(finalMasterSid);
		Collections.reverse(tempMasterSid);
		StringBuilder hierarchyNo = new StringBuilder();
		for (String masterSid : tempMasterSid) {
			hierarchyNo.append(masterSid);
			hierarchyNo.append(".");
		}
		hierarchyNo.replace(hierarchyNo.lastIndexOf("."), hierarchyNo.lastIndexOf(".") + 1, ".");
		return hierarchyNo.toString();
	}

	private List<GtnWsRecordBean> getNextlevelDataForAutoBuild(List<HierarchyLevelDefinitionBean> hierarchyList,
			GtnWsRecordBean selectedTreeBean, List<String> masterSidList)
			throws GtnFrameworkGeneralException {

		String levelNo = selectedTreeBean
				.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal());
		List<GtnWsRecordBean> userDefinedLevelDataList = new ArrayList<>();
		List<GtnWsRecordBean> linkedLevelDataList = new ArrayList<>();
		linkedLevelDataList.add(selectedTreeBean);
		HierarchyLevelDefinitionBean.getLastLinkedLevelNo(hierarchyList);
		int selectedLevelNo = Integer.parseInt(levelNo);
		String hirarchyNo = "";
		List<String> finalMasterSid = getMasterSidList(masterSidList, hierarchyList);
		StringBuilder finalQuery = new StringBuilder();
		int linkedLevelValueCount = HierarchyLevelDefinitionBean.countLinkedLevelsAboveSelectedLevelNo(hierarchyList,
				selectedLevelNo);
		String gethiddenIdhierarchyNo = getHiddenIdHierarchyNo(masterSidList);
		for (int i = selectedLevelNo + 1; i <= HierarchyLevelDefinitionBean.getLastLinkedLevelNo(hierarchyList); i++) {
			String query = null;
			HierarchyLevelDefinitionBean hierarchyBean = HierarchyLevelDefinitionBean.getBeanByLevelNo(i,
					hierarchyList);
			if (hierarchyBean.isUserDefined()) {
				List<Object> input = getInputForUserDefinedLevel(hierarchyBean);
				List<Object[]> masterSid = getQueryForUserDefinedLevel(input);
				GtnWsRecordBean userDefData = customizeRelationDataForAutoBuild(masterSid).get(0);
				userDefinedLevelDataList.addAll(customizeRelationDataForAutoBuild(masterSid));
				hirarchyNo = getHierarchyNoforQuery(hirarchyNo, userDefData
						.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal()));
			} else {
				GtnFrameworkFileReadWriteService fileReadWriteService = new GtnFrameworkFileReadWriteService();
				GtnFrameworkHierarchyQueryBean queryBaen = fileReadWriteService.getQueryFromFile(
						hierarchyBean.getHierarchyDefinitionSid(), hierarchyBean.getHierarchyLevelDefinitionSid(),
						hierarchyBean.getVersionNo());
				GtnFrameworkQueryGeneratorBean finalQueryBean = queryBaen.getQuery();
				finalQueryBean.removeWhereClauseAboveGivenIndex(linkedLevelValueCount);
				hirarchyNo = getSelectClauseForAutoBuild(hirarchyNo, hierarchyBean, finalQueryBean,
						gethiddenIdhierarchyNo);
				query = gtnWsSqlService.getReplacedQuery(finalMasterSid, finalQueryBean.generateQuery());
				query = checkForSelectOne(query);
				if (finalQuery.length() > 0) {
					finalQuery.append(" UNION ALL ");
				}

				finalQuery.append(query);
			}

		}
		@SuppressWarnings("unchecked")
		List<Object[]> result = executeQuery(finalQuery.toString());
		linkedLevelDataList.addAll(customizeRelationDataForAutoBuild(result));
		if (!userDefinedLevelDataList.isEmpty())
			getIntermediateUserDefinedData(linkedLevelDataList, userDefinedLevelDataList, hierarchyList);
		return linkedLevelDataList;

	}

	private String checkForSelectOne(String query) {
		String quer = query;
		if (quer.contains(HELPER_JOIN_DESCRIPTION)) {
			quer = quer.concat(" AND HELPER_JOIN .DESCRIPTION<> '-Select One-' ");
		}
		return quer;
	}

	private void getIntermediateUserDefinedData(List<GtnWsRecordBean> linkedLevelDataList,
			List<GtnWsRecordBean> userDefinedLevelDataList, List<HierarchyLevelDefinitionBean> hierarchyList) {
		List<GtnWsRecordBean> finalDataList = new ArrayList<>();
		for (HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean : hierarchyList) {
			finalDataList.clear();
			if (hierarchyLevelDefinitionBean.isUserDefined()) {
				getUserdefinedDataCombination(linkedLevelDataList, userDefinedLevelDataList, finalDataList,
						hierarchyLevelDefinitionBean);
			}
			linkedLevelDataList.addAll(finalDataList);
		}
	}

	public void getUserdefinedDataCombination(List<GtnWsRecordBean> linkedLevelDataList,
			List<GtnWsRecordBean> userDefinedLevelDataList, List<GtnWsRecordBean> finalDataList,
			HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean) {
		for (GtnWsRecordBean userdefinedData : userDefinedLevelDataList) {
			if (hierarchyLevelDefinitionBean.getLevelNo() == Integer.parseInt(userdefinedData
					.getPropertyValueByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()).toString())) {
				for (GtnWsRecordBean linkedBean : linkedLevelDataList) {
					if (Integer.parseInt(
							linkedBean.getPropertyValueByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal())
									.toString()) == hierarchyLevelDefinitionBean.getLevelNo() - 1) {
						GtnWsRecordBean newUserDefinedData = userdefinedData.cloneGtnWsRecordBean();
						String masterId = userdefinedData
								.getPropertyValueByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal())
								.toString();
						String linkedHierarchyno = linkedBean
								.getPropertyValueByIndex(GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal())
								.toString();
						newUserDefinedData.setPropertyValueByIndex(
								GtnWsRelationshipBuilderKeyConstant.HIERARCHY_NO.ordinal(),
								(linkedHierarchyno + masterId + "."));
						finalDataList.add(newUserDefinedData);
					}
				}

			}

		}
	}

	private List<Object> getInputForUserDefinedLevel(HierarchyLevelDefinitionBean hierarchyBean) {
		List<Object> input = new ArrayList<>();
		input.add(hierarchyBean.getHierarchyLevelDefinitionSid());
		input.add(hierarchyBean.getLevelNo());
		input.add(hierarchyBean.getLevelName());
		input.add("HIERARCHY_LEVEL_VALUES_SID");
		input.add(hierarchyBean.getHierarchyLevelDefinitionSid());
		return input;
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> getQueryForUserDefinedLevel(List<Object> input) throws GtnFrameworkGeneralException {
		String query = gtnWsSqlService.getQuery(input, "getUserDefinedLevelForAutoBuild");
		return executeQuery(query);
	}

	private List<String> getMasterSidList(List<String> masterSidList,
			List<HierarchyLevelDefinitionBean> hierarchyList) {
		List<String> tempMasterSid = new ArrayList<>(masterSidList);
		Collections.reverse(tempMasterSid);
		List<String> finalMasterSid = new ArrayList<>();
		for (int i = 0; i < tempMasterSid.size(); i++) {
			if (!hierarchyList.get(i).isUserDefined()) {
				finalMasterSid.add(tempMasterSid.get(i));
			}

		}
		return finalMasterSid;
	}

	private String getHiddenIdHierarchyNo(List<String> masterSidList) {
		StringBuilder hierarchyNo = new StringBuilder();
		List<String> tempMasterSid = new ArrayList<>(masterSidList);
		Collections.reverse(tempMasterSid);
		for (String masterSid : tempMasterSid) {
			hierarchyNo.append(masterSid);
			hierarchyNo.append(",'.',");
		}
		hierarchyNo.replace(hierarchyNo.lastIndexOf(","), hierarchyNo.lastIndexOf(",") + 1, ",");
		return hierarchyNo.toString();
	}

	public List<GtnWsRecordBean> customizeRelationDataForAutoBuild(List<Object[]> result) {
		List<GtnWsRecordBean> recordBeanList = new ArrayList<>();
		for (Object[] data : result) {
			HierarchyLevelsBean levelValuesDTO = new HierarchyLevelsBean();
			levelValuesDTO.setLevelValue(String.valueOf(data[0]));
			levelValuesDTO.setHiddenId(String.valueOf(data[1]));
			levelValuesDTO.setHierarchyLevelSystemId(Integer.parseInt(data[2].toString()));
			levelValuesDTO.setLevelNo(String.valueOf(data[3].toString()));
			levelValuesDTO.setLevelName(String.valueOf(data[4].toString()));
			levelValuesDTO.setHierarchyNo(String.valueOf(data[5].toString()));
			GtnWsRecordBean recordBean = getGtnWsRecordBean(levelValuesDTO);
			recordBeanList.add(recordBean);
		}
		return recordBeanList;
	}

	public String getSelectClauseForAutoBuild(String hirarchyNo, HierarchyLevelDefinitionBean hierarchyBean,
			GtnFrameworkQueryGeneratorBean finalQueryBean, String gethiddenIdhierarchyNo) {
		finalQueryBean.addSelectClauseBean(null, "HIERARCHY_DEFINITION_SID", Boolean.FALSE,
				String.valueOf(hierarchyBean.getHierarchyLevelDefinitionSid()));
		finalQueryBean.addSelectClauseBean(null, "LEVEL_NO", Boolean.FALSE, String.valueOf(hierarchyBean.getLevelNo()));
		finalQueryBean.addSelectClauseBean(null, "LELVEL_NAME", Boolean.FALSE,
				String.valueOf("'" + hierarchyBean.getLevelName() + "'"));
		GtnFrameworkSelectColumnRelationBean keyRelationBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyBean.getTableName(),
						hierarchyBean.getFieldName());
		String tempHierarchyNo;
		tempHierarchyNo = getHierarchyNoforQuery(hirarchyNo, keyRelationBean.getMasterIdColumn());
		String finalHierarchyNo = "CONCAT(" + gethiddenIdhierarchyNo + tempHierarchyNo + ")";
		finalQueryBean.addSelectClauseBean(null, "HIERARCHY_NO", Boolean.FALSE, finalHierarchyNo);
		return tempHierarchyNo;
	}

	public String getHierarchyNoforQuery(String hirarchyNo, String value) {
		String tempHiearchyNo = hirarchyNo;
		if (!hirarchyNo.isEmpty()) {
			tempHiearchyNo += "," + value + ", '.'";
		} else
			tempHiearchyNo += value + ", '.'";
		return tempHiearchyNo;
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
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
					"getRBhierarchyLevelDefn"));
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
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
					"getRBuserDefindLevelValue"));
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

	private HierarchyLevelsBean getRelationPrimaryColumnValues(GtnFrameworkSelectColumnRelationBean keyRelationBean,
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
			GtnFrameworkSelectColumnRelationBean keyRelationBean) {
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
			GtnFrameworkSelectColumnRelationBean keyRelationBean = gtnFrameworkEntityMasterBean
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
			if (checkForDuplicateCompanyTree(rbRequest.getRsTreeNodeList().get(0), rbRequest.getRbSysId(),
					rbRequest.getHierarchyDefSId())) {
				rbResponse.setSuccess(false);
				rbResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
				rbResponse.setMessage(
						"Selected company already associated with other relationship. Please select different company for the level 1.");
				return;
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
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
					"getRBSaveCheckRelationshipName"));
			if (result != null && !result.isEmpty()) {
				relationCount = Integer.parseInt(String.valueOf(result.get(0)));
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
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
					"getRBSaveCheckHierarchyDefinition"));
			if (result != null && !result.isEmpty()) {
				hierarchyCount = Integer.parseInt(String.valueOf(result.get(0)));
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
			List result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
					"getRBSaveCheckRelationshipLevelDefinition"));
			if (result != null && !result.isEmpty()) {
				builderCount = Integer.parseInt(String.valueOf(result.get(0)));
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
				List resultList = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator
						.getQueryReplaced(inputlist, "getRBSaveCheckUsedRelationship"));
				if (resultList != null && !resultList.isEmpty()) {
					Object[] result = (Object[]) resultList.get(0);
					custCount = Integer.parseInt(String.valueOf(result[0]));
					prodCount = Integer.parseInt(String.valueOf(result[1]));
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

	private void updateRelationshipBuilderFromRequest(RelationshipBuilder relationshipBuilder,
			GtnWsRelationshipBuilderRequest rbRequest, Session session) throws GtnFrameworkGeneralException {
		try {
			relationshipBuilder.setRelationshipName(rbRequest.getRelationshipName());
			relationshipBuilder.setRelationshipDescription(rbRequest.getRelationshipDescription());
			relationshipBuilder.setHelperTable(
					GtnCommonUtil.getHelperTable(rbRequest.getRelationshipType(), "RELATIONSHIP_TYPE", session));
			relationshipBuilder
					.setHierarchyDefinition(session.load(HierarchyDefinition.class, rbRequest.getHierarchyDefSId()));
			relationshipBuilder.setHierarchyVersion(rbRequest.getHierarchyVersionNo());
			relationshipBuilder.setStartDate(rbRequest.getStartDate());
			relationshipBuilder.setBuildType(rbRequest.getBuildType());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in updateRelationshipBuilderFromRequest", e);
		}
	}

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
				relationshipBuilder.setRelationshipBuilderSid(rbRequest.getRbSysId());
				relationshipBuilder.setModifiedBy(rbRequest.getUserId());
				relationshipBuilder.setModifiedDate(date);
				relationshipBuilder.setCreatedBy(rbRequest.getCreatedById());
				relationshipBuilder.setVersionNo(rbRequest.getVersionNo() + 1);
			}
			updateRelationshipBuilderFromRequest(relationshipBuilder, rbRequest, session);
			session.saveOrUpdate(relationshipBuilder);
			saveRelationshipBuilderLevels(relationshipBuilder, date, "", "", rbRequest.getRsTreeNodeList(), session);

			getSuccessRelationshipBuilder(relationshipBuilder, rbResponse);
			rbResponse.setMessageType("success");
			rbResponse.setMessage("'" + rbRequest.getRelationshipName() + "' has been saved successfully.");
			inputlist.add(String.valueOf(rbRequest.getHierarchyDefSId()));
			inputlist.add(String.valueOf(rbRequest.getHierarchyVersionNo()));
			tx.commit();
			autoMaticRelationService.checkManualRelation(relationshipBuilder.getRelationshipBuilderSid());
			rbResponse.setSuccess(true);
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
			String parentHierarchyNo, String parentNode, List<GtnWsRecordBean> levelBeanList, Session session)
			throws GtnFrameworkGeneralException {
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
						? Integer.toString(parentNodeIndex)
						: parentNode);
				String hierarchyNo = parentHierarchyNo + hierarchyLevelDTO.getHiddenId() + ".";
				relationshipLevel.setHierarchyNo(relationshipBuilder.getRelationshipBuilderSid() + "-" + hierarchyNo);
				relationshipLevel.setCreatedDate(date);
				relationshipLevel.setModifiedDate(date);
				relationshipLevel.setCreatedBy(relationshipBuilder.getCreatedBy());
				relationshipLevel.setModifiedBy(relationshipBuilder.getModifiedBy());
				relationshipLevel.setVersionNo(relationshipBuilder.getVersionNo());
				relationshipLevel
						.setParentHierarchyNo(relationshipBuilder.getRelationshipBuilderSid() + "-" + hierarchyNo);
				session.saveOrUpdate(relationshipLevel);

				if (levelBean.getChildList() != null && !levelBean.getChildList().isEmpty()) {
					saveRelationshipBuilderLevels(
							relationshipBuilder, date, hierarchyNo, hierarchyLevelDTO.getLevelNo()
									+ GtnFrameworkWebserviceConstant.STRING_TILT + hierarchyLevelDTO.getHiddenId(),
							levelBean.getChildList(), session);
				}
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save RelationshipLevelDefinition", e);
		}
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
								? lhsHierarchyNo.substring(0, lhsHierarchyNo.lastIndexOf('.'))
								: lhsHierarchyNo;
						String rhsStr = rhsHierarchyNo.endsWith(".")
								? rhsHierarchyNo.substring(0, rhsHierarchyNo.lastIndexOf('.'))
								: rhsHierarchyNo;
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
			HierarchyLevelDefinitionBean destinationHierarchyBean, String nextPrimayKey) {
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		if (result != null && !result.isEmpty()) {
			for (int i = 0; i < result.size(); i++) {
				Object[] obj = result.get(i);
				HierarchyLevelsBean levelValuesDTO = new HierarchyLevelsBean();
				levelValuesDTO.setLevelValue(String.valueOf(obj[0]));
				levelValuesDTO.setHiddenId(String.valueOf(obj[1]));
				levelValuesDTO.setHierarchyLevelSystemId(destinationHierarchyBean.getHierarchyLevelDefinitionSid());
				levelValuesDTO.setParentNode("0");
				levelValuesDTO.setRelationshipLevelSystemId(Integer.parseInt("0"));
				levelValuesDTO.setLevelName(destinationHierarchyBean.getLevelName());
				levelValuesDTO.setLevelNo(String.valueOf(destinationHierarchyBean.getLevelNo()));
				levelValuesDTO.setPrimaryKeyColumn(nextPrimayKey);
				levelValuesDTO.setLevelValueReference(destinationHierarchyBean.getLevelValueReference());
				GtnUIFrameworkDataRow newDataRow = new GtnUIFrameworkDataRow();
				newDataRow.setColList(getLevelBeanAsList(levelValuesDTO));
				dataTable.addDataRow(newDataRow);
			}
		}
		return dataTable;

	}

	public List<String> getRelationQueries(int relationshipSid,
			List<HierarchyLevelDefinitionBean> levelHierarchyLevelDefinitionList, int versionNo) {
		List<String> queryList = new ArrayList<>();
		List<String> input = new ArrayList<>();
		for (HierarchyLevelDefinitionBean levelDto : levelHierarchyLevelDefinitionList) {
			if (!levelDto.isUserDefined()) {
				input.add(String.valueOf(levelDto.getLevelNo()));
				input.add(String.valueOf(relationshipSid));
				input.add(String.valueOf(versionNo));
				String relationQuery = gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(input,
						"relationShipSubQueryForSubQuery");
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
		List<Object[]> result = executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
				"getRBHierarchyLevelDefinitionByProductRelationSid"));
		return gtnWsRelationshipBuilderHierarchyFileGenerator.gettHierarchyLevelDefinitionListMain(result);
	}

	@SuppressWarnings("unchecked")
	private List<Integer> getNotInList(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsSearchRequest searchRequest = gtnWsRequest.getGtnWsSearchRequest();
		List<GtnWebServiceSearchCriteria> searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList();
		if (searchCriteria.size() < 5)
			return Collections.emptyList();
		return (List<Integer>) searchCriteria.get(5).getFilterValue3();
	}

	@SuppressWarnings("unchecked")
	public List<String> getRBHierarchyLevelNameList(int hierarchyDefSid, int versionNo)
			throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(String.valueOf(hierarchyDefSid));
		inputlist.add(String.valueOf(versionNo));
		return executeQuery(gtnWsRelationshipBuilderHierarchyFileGenerator.getQueryReplaced(inputlist,
				"getRBHierarchyLevelNameList"));
	}

	public GtnWsRelationshipBuilderBean getCustomizedRelationShipBean(RelationshipBuilder relationshipBuilder,
			Session session) {
		GtnWsRelationshipBuilderBean relationShipBuilderBean = new GtnWsRelationshipBuilderBean();
		relationShipBuilderBean.setRelationshipBuilderSid(relationshipBuilder.getRelationshipBuilderSid());
		relationShipBuilderBean.setRelationshipName(relationshipBuilder.getRelationshipName());
		relationShipBuilderBean.setRelationshipDescription(relationshipBuilder.getRelationshipDescription());
		relationShipBuilderBean
				.setHierarchyDefinitionSid(relationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid());
		relationShipBuilderBean.setStartDate(relationshipBuilder.getStartDate());
		relationShipBuilderBean.setBuildType(relationshipBuilder.getBuildType());
		relationShipBuilderBean.setVersionNo(relationshipBuilder.getVersionNo());
		relationShipBuilderBean.setHierarchyVersion(relationshipBuilder.getHierarchyVersion());
		relationShipBuilderBean.setCreatedBy(relationshipBuilder.getCreatedBy());
		relationShipBuilderBean.setCreatedDate(relationshipBuilder.getCreatedDate());
		relationShipBuilderBean.setModifiedBy(relationshipBuilder.getModifiedBy());
		relationShipBuilderBean.setModifiedDate(relationshipBuilder.getModifiedDate());
		relationShipBuilderBean.setDeductionRelation(relationshipBuilder.getDeductionRelation());
		HelperTable hierarchyCat = session.load(HelperTable.class,
				relationshipBuilder.getHierarchyDefinition().getHierarchyCategory());
		relationShipBuilderBean.setHierarchycategory(hierarchyCat.getDescription());
		return relationShipBuilderBean;
	}

}
