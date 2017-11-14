/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.logic;

import static com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants.REPLACE_STRING;
import static com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants.getConditions;
import static com.stpl.gtn.gtn2o.ws.util.GtnWsConstants.NULL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.bean.GtnWsHierarchyRuleBean;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.controller.GtnWsRelationshipBuilderController;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsRelationshipBuilderHelperLogic {

	private final Set<String> tableNames = new HashSet<>();
	private final Set<String> columnNames = new HashSet<>();
	private final Map<Integer, String> inclusionExclusionRules = new HashMap<>();
	private final GtnWsRelationshipBuilderController controller;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsRelationshipBuilderHelperLogic.class);

	public GtnWsRelationshipBuilderHelperLogic(GtnWsRelationshipBuilderController controller) {
		this.controller = controller;
	}

	public GtnWsRelationshipBuilderController getController() {
		return controller;
	}

	public void addTableName(String tableName) {
		tableNames.add(tableName);
	}

	public void addColumnName(String columnName) {
		columnNames.add(columnName);
	}

	@SuppressWarnings({ "rawtypes" })
	public String finderImplInLogic(String tableName, String columnName, List hierListValues, boolean isFirst) {

		final String hierarchyType = hierListValues.get(0).toString();
		final String hierarchyCategory = hierListValues.get(1).toString();
		final int levelNo = Integer.parseInt(hierListValues.get(2).toString());
		String rule = String.valueOf(hierListValues.get(3));
		final GtnFrameworkSingleColumnRelationBean dto = controller.getGtnFrameworkEntityMasterBean()
				.getKeyRelationBeanUsingTableIdAndColumnName(tableName, columnName);

		final GtnFrameworkHierarchyService queryService = controller.getHierarchyService();

		String sqlString = "";
		final String totalDataToLoad = isFirst ? GtnFrameworkCommonStringConstants.STRING_EMPTY : " TOP 150 ";
		if (dto != null) {
			String joinCondition = "";
			String whereCondition = "";
			final List<String> columnList = queryService.getMappingColumns(dto);
			sqlString = "SELECT DISTINCT " + totalDataToLoad + columnList.get(0) + "," + columnList.get(1)
					+ GtnFrameworkWebserviceConstant.FROM + tableName;
			joinCondition += addTableJoin(dto);
			whereCondition = " WHERE " + dto.getActualTtableName() + "." + dto.getWhereClauseColumn()
					+ GtnFrameworkWebserviceConstant.IS_NOT_NULL;

			if (!"null".equals(rule) && StringUtils.isNotBlank(rule)) {

				rule = buildFinalQuery(tableName, columnName, rule, dto.getJoinColumnTable(),
						dto.getJoinColumnTable() + dto.getDescriptionClauseColumn());
				whereCondition += GtnFrameworkWebserviceConstant.AND2 + rule + " ";
			}
			sqlString += joinCondition + whereCondition;
		} else {

			sqlString = "SELECT  DISTINCT " + totalDataToLoad + " " + columnName + GtnFrameworkWebserviceConstant.FROM
					+ tableName + GtnFrameworkWebserviceConstant.WHERE + columnName
					+ GtnFrameworkWebserviceConstant.IS_NOT_NULL;
			if (!"null".equals(rule) && StringUtils.isNotBlank(rule)) {

				rule = buildFinalQuery(tableName, columnName, rule, "", columnName);
				sqlString += GtnFrameworkWebserviceConstant.AND2 + rule + " ";
			}
		}
		if (1 == levelNo && "Data Selection".equals(hierarchyCategory) && "Primary".equals(hierarchyType)
				&& "COMPANY_MASTER".equals(tableName)) {
			sqlString += " and COMPANY_TYPE='GLCOMP'";
		}
		return sqlString;

	}

	public String addTableJoin(GtnFrameworkSingleColumnRelationBean keyBean) {
		if (keyBean.getMappingColumnName() != null && !keyBean.getMappingColumnName().isEmpty()) {
			StringBuilder tempQuery = new StringBuilder();
			tempQuery.append(" JOIN ");
			tempQuery.append(keyBean.getReferenceTableName());
			tempQuery.append(" as HELPER_JOIN on HELPER_JOIN.");
			tempQuery.append(keyBean.getMappingColumnName());
			tempQuery.append(" = ");
			tempQuery.append(keyBean.getActualTtableName());
			tempQuery.append(".");
			tempQuery.append(keyBean.getActualColumnName());
			return tempQuery.toString();
		}
		return "";
	}

	private String buildFinalQuery(String tableName, String columnName, String rule, String mainTableAlias,
			String referenceColumn) {
		String tempRule = rule;
		String rules = rule;
		while (tempRule.contains(REPLACE_STRING)) {
			final String condition = tempRule.substring(tempRule.indexOf('('), tempRule.indexOf(')') + 1);
			if (condition.contains(REPLACE_STRING + columnName)) {
				rules = rules.replace(REPLACE_STRING + columnName, referenceColumn);
			} else if (StringUtils.isNotBlank(condition)) {
				final String[] colArray = condition.split(" ");
				String otherColumn = colArray[1];
				final String conditionMethod = colArray[2];
				final String value = colArray[3];
				otherColumn = otherColumn.replace(REPLACE_STRING, "");
				controller.getGtnFrameworkEntityMasterBean().getKeyRelationBeanUsingTableIdAndColumnName(tableName,
						columnName);
				final GtnFrameworkSingleColumnRelationBean dto = controller.getGtnFrameworkEntityMasterBean()
						.getKeyRelationBeanUsingTableIdAndColumnName(tableName, columnName);
				if (dto != null) {
					final StringBuilder subQuery = new StringBuilder();
					subQuery.append(" (").append(mainTableAlias).append(otherColumn).append(" IN (SELECT ")
							.append(dto.getMappingColumnName()).append(GtnFrameworkWebserviceConstant.FROM)
							.append(dto.getReferenceTableName()).append(GtnFrameworkWebserviceConstant.WHERE)
							.append(dto.getWhereClauseColumn()).append(" ").append(conditionMethod).append(" ")
							.append(value).append(" ");
					subQuery.append(" ))");
					rules = rules.replace(condition, subQuery);
				} else {
					final String replaceCondition = condition.replace(REPLACE_STRING, mainTableAlias);
					rules = rules.replace(condition, replaceCondition);
				}
			}
			tempRule = tempRule.replace(condition, "");
		}
		return rules;
	}

	private String getRuleNameString(List<String> ruleName) {
		final StringBuilder rule = new StringBuilder();
		for (int i = 0; i < ruleName.size(); i++) {

			rule.append("'").append(ruleName.get(i)).append("',");
		}
		rule.append(rule.substring(0, rule.length() - 1));
		return rule.toString();
	}

	@SuppressWarnings({ "rawtypes" })
	public Map<Integer, String> getInclusionExclusionRulesWithoutBPM(String hierarchyDefName,
			GtnWsRelationshipBuilderController controller) {
		inclusionExclusionRules.clear();
		final List<String> rulesName = new ArrayList<>();
		final Map<String, Object> ruleMap = new HashMap<>();
		try {
			final String query = "select * from dbo.HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID \n"
					+ "in (select HIERARCHY_DEFINITION_SID from dbo.HIERARCHY_DEFINITION where HIERARCHY_NAME='"
					+ hierarchyDefName + "')";
			int totalLevels = 0;

			final List list = executeAndGetData(controller, query);
			if (!list.isEmpty()) {
				totalLevels = list.size();
				for (int i = 0; i < totalLevels; i++) {
					final Object[] obj = (Object[]) list.get(i);
					final String inclusionRuleType = String.valueOf(obj[12]);
					final String exclusionRuleType = String.valueOf(obj[14]);
					addRuleName(inclusionRuleType, rulesName, String.valueOf(obj[13]));
					addRuleName(exclusionRuleType, rulesName, String.valueOf(obj[15]));
				}
				if (!rulesName.isEmpty()) {
					final String ruleName1 = getRuleNameString(rulesName);
					final String rulesValue = "select HRD.RULE_NAME,HRD.\"TABLE_NAME\",HRD.\"COLUMN_NAME\",HRD.\"CONDITION\",HRD.\"VALUE\",HRD.INBOUND_STATUS from "
							+ "dbo.HIERARCHY_RULES_DEFINITION HRD where RULE_NAME in (" + ruleName1 + ")";
					final List ruleValueList = executeAndGetData(controller, rulesValue);
					putRuleValuesInmap(ruleValueList, ruleMap);
				}
			}

			for (int i = 0; i < totalLevels; i++) {
				final StringBuilder rules = new StringBuilder("");
				final Integer levelNo = i + 1;
				if (!list.isEmpty()) {
					final Object[] obj = (Object[]) list.get(i);
					// For Inclusion Rule Group
					appendInclusionExclusionConditions(rules, ruleMap, obj, "Inclusion");
					// For Exclusion Rule Group
					appendInclusionExclusionConditions(rules, ruleMap, obj, GtnFrameworkWebserviceConstant.EXCLUSION);
					inclusionExclusionRules.put(levelNo, rules.toString());
				}
			}
		} catch (final Exception e) {
			logger.error("Exception in getInclusionExclusionRulesWithoutBPM", e);
		}
		return inclusionExclusionRules;
	}

	@SuppressWarnings("rawtypes")
	void addRuleName(String ruleType, List<String> rulesName, String ruleName) {
		if (!ruleType.equals(NULL) && !ruleType.equals(StringUtils.EMPTY)) {
			if (!GtnFrameworkWebserviceConstant.GROUP.equals(ruleType)) {
				rulesName.add(ruleName);
			} else {
				final String ruleGroupQuery = "select distinct HRD.RULE_NAME from dbo.HIERARCHY_LEVEL_DEFINITION HLD JOIN dbo.HIERARCHY_RULES_DEFINITION HRD on HLD.INCLUSION_RULE=HRD.RULE_FLOW_GROUP_NAME\n"
						+ "AND HRD.RULE_FLOW_GROUP_NAME='" + ruleName + "'";
				final List ruleGroupList = executeAndGetData(controller, ruleGroupQuery);
				if (!ruleGroupList.isEmpty()) {
					for (final Object rule : ruleGroupList) {
						rulesName.add(String.valueOf(rule));
					}
				}
			}
		}
	}

	void putRuleValuesInmap(List<?> ruleValueList, Map<String, Object> ruleMap) {
		if (ruleValueList != null) {
			for (int j = 0; j < ruleValueList.size(); j++) {
				final Object[] ob = (Object[]) ruleValueList.get(j);
				final String key = String.valueOf(ob[0]);
				ruleMap.put(key, ruleValueList.get(j));
			}
		}
	}

	void appendInclusionExclusionConditions(StringBuilder rules, Map<String, Object> ruleMap, Object[] obj,
			String inclusionOrExclusion) throws GtnFrameworkGeneralException {

		int index = 12;
		int conditionIndex = 16;
		int condtion1Index = 2;
		if (GtnFrameworkWebserviceConstant.EXCLUSION.equals(inclusionOrExclusion)) {
			index = 14;
			conditionIndex = 17;
			condtion1Index = 3;
		}
		final String ruleType = String.valueOf(obj[index]);
		final String rule = String.valueOf(obj[index + 1]);
		final String condition = String.valueOf(obj[conditionIndex]);
		GtnWsHierarchyRuleBean dto;

		if (GtnFrameworkWebserviceConstant.GROUP.equalsIgnoreCase(ruleType)) {
			final List<String> ruleNameList = getGroupRuleList(rule, controller);
			final List<GtnWsHierarchyRuleBean> exclusionList = new ArrayList<>();
			for (final String ruleName : ruleNameList) {
				dto = new GtnWsHierarchyRuleBean();
				dto.setRuleName(ruleName);
				final Object[] object = (Object[]) ruleMap.get(ruleName);
				dto.setCondition1(String.valueOf(object[condtion1Index]));
				dto.setDimension(String.valueOf(object[1]));
				dto.setAttribute(String.valueOf(object[2]));
				dto.setValue1(String.valueOf(object[4]));
				exclusionList.add(dto);
			}
			if (!exclusionList.isEmpty()) {
				appendAndCondition(rules, inclusionOrExclusion);
				rules.append(buildQuery(exclusionList, inclusionOrExclusion, condition));
			}
			// For single Exclusion Rule
		} else {
			dto = new GtnWsHierarchyRuleBean();
			dto.setRuleName(rule);
			if (rule != null && !"null".equals(rule) && !"".equals(rule) && ruleMap.get(rule) != null) {
				appendAndCondition(rules, inclusionOrExclusion);
				final Object[] obje = (Object[]) ruleMap.get(rule);
				dto.setCondition1(String.valueOf(obje[3]));
				dto.setDimension(String.valueOf(obje[1]));
				dto.setAttribute(String.valueOf(obje[2]));
				dto.setValue1(String.valueOf(obje[4]));
				rules.append(buildQuery(dto, inclusionOrExclusion));
			}
		}
	}

	StringBuilder appendAndCondition(StringBuilder rules, String inclusionOrExclusion) {
		if (rules.length() > 1 && GtnFrameworkWebserviceConstant.EXCLUSION.equals(inclusionOrExclusion)) {
			rules.append(GtnFrameworkWebserviceConstant.AND2);
		}
		return rules;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List executeAndGetData(GtnWsRelationshipBuilderController controller, String query) {
		try {

			return new ArrayList<>(controller.executeQuery(query));
		} catch (final GtnFrameworkGeneralException ex) {
			logger.error("Exception in getInclusionExclusionRulesWithoutBPM listTemp", ex);
		}
		return Collections.emptyList();
	}

	private String buildQuery(GtnWsHierarchyRuleBean dto, String ruleType) {
		String query = "";

		if (GtnFrameworkWebserviceConstant.EXCLUSION.equals(ruleType)) {
			query = " not ";
		}
		final boolean startsWith = "starts with".equals(dto.getCondition1()) || "".equals(dto.getCondition1()) ? true
				: false;
		final boolean endsWith = "ends with".equals(dto.getCondition1()) || "".equals(dto.getCondition1()) ? true
				: false;
		if ("in".equals(dto.getCondition1()) && StringUtils.isNotBlank(dto.getValue1())) {
			dto.setValue1("('" + dto.getValue1().replace(",", "','") + "')");
		}
		addTableName(dto.getDimension());
		addColumnName(dto.getAttribute());
		query += " ( " + REPLACE_STRING + dto.getAttribute() + " " + getConditions(dto.getCondition1()) + " '"
				+ (endsWith ? "%" : "") + dto.getValue1() + (startsWith ? "%" : "") + "' ) ";
		return query;
	}

	private String buildQuery(List<GtnWsHierarchyRuleBean> dtoList, String ruleType, String condition)
			throws GtnFrameworkGeneralException {
		final StringBuilder query = new StringBuilder();
		try {
			int i = 1;
			for (final GtnWsHierarchyRuleBean dto : dtoList) {
				if (i > 1 && !"".equals(query.toString())) {
					if ("OR".equals(condition)) {
						query.append(" or ");
					} else {
						query.append(GtnFrameworkWebserviceConstant.AND2);
					}
				}
				final String subQuery = buildQuery(dto, ruleType);
				if ("".equals(query.toString()) && !"".equals(subQuery)) {
					query.append("(");
				}
				query.append(subQuery);
				i++;
			}
			if (StringUtils.isNotBlank(query.toString())) {
				query.append(")");
			}
		} catch (final Exception e) {
			throw new GtnFrameworkGeneralException("Exception in buildQuery", e);
		}
		return query.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> getGroupRuleList(String ruleGroup, GtnWsRelationshipBuilderController controller)
			throws GtnFrameworkGeneralException {
		final List<String> ruleList = new ArrayList<>();
		final List tempList = new ArrayList<>();
		try {
			final String ruleQuery = "select RULE_NAME from dbo.HIERARCHY_RULES_DEFINITION where RULE_FLOW_GROUP_NAME='"
					+ ruleGroup + "'";
			tempList.addAll(controller.executeQuery(ruleQuery));
			final List ruleNameList = tempList;
			if (!ruleNameList.isEmpty()) {
				for (final Object rule : ruleNameList) {
					ruleList.add(String.valueOf(rule));
				}
			}
		} catch (final Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getGroupRuleList", e);
		}
		return ruleList;
	}

	public void getLinkedValueQuery(HierarchyLevelDefinitionBean destinationHierarchyBean, int hierarchDefSid,
			int levelNo, List<HierarchyLevelDefinitionBean> hierarchyList, GtnFrameworkQueryGeneratorBean queryBean)
			throws GtnFrameworkGeneralException {
		final GtnFrameworkHierarchyService queryService = controller.getHierarchyService();
		final String hierarchyType = getHierarchyTypeFromSid(hierarchDefSid);
		final Set<String> tableNameList = getDefaultTableNameList(hierarchyType);
		Set<String> hierarchyTableList = HierarchyLevelDefinitionBean.getTableNameSet(hierarchyList);
		tableNameList.addAll(hierarchyTableList);
		if (tableNameList.isEmpty()) {
			return;
		}
		final GtnFrameworkSingleColumnRelationBean destinationkeyBean = controller.getGtnFrameworkEntityMasterBean()
				.getKeyRelationBeanUsingTableIdAndColumnName(destinationHierarchyBean.getTableName(),
						destinationHierarchyBean.getFieldName());
		queryService.getSelectColumnsForRelationShipBuilder(destinationkeyBean, queryBean);
		queryService.getQueryByTableNameAndHierarchyTypeForMultiLevel(new ArrayList<>(tableNameList), hierarchyType,
				queryBean);

		if (!destinationkeyBean.isDescriptionColumnAvailable()) {
			queryService.addTableJoin(destinationkeyBean, queryBean);
		}
		final List<GtnFrameworkSingleColumnRelationBean> keyListBeanList = getKeyListBean(levelNo, hierarchyList);
		queryService.getWhereQuery(keyListBeanList, queryBean);
	}

	private Set<String> getDefaultTableNameList(String hierarchyType) {
		Set<String> selectedTableNamesList = new HashSet<>();
		if ("Customer Hierarchy".equalsIgnoreCase(hierarchyType)) {
			selectedTableNamesList.add("COMPANY_MASTER");
			selectedTableNamesList.add("CONTRACT_MASTER");
		} else if ("PRODUCT HIERARCHY".equalsIgnoreCase(hierarchyType)) {
			selectedTableNamesList.add("ITEM_MASTER");
		}
		return selectedTableNamesList;
	}

	private String getHierarchyTypeFromSid(int hierarchyDefinitionSid) throws GtnFrameworkGeneralException {
		final String query = getController().getQuery("getHierarchyCatBySid");
		final Object[] params = { hierarchyDefinitionSid };
		final GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER };
		final List<?> results = getController().executeQuery(query, params, paramsType);
		return results.isEmpty() ? "" : results.get(0).toString();
	}


	public List<GtnFrameworkSingleColumnRelationBean> getKeyListBean(int levelNo,
			List<HierarchyLevelDefinitionBean> hierarchyList) {
		final List<GtnFrameworkSingleColumnRelationBean> keyListBeanList = new ArrayList<>();
		for (int i = 0; i < levelNo - 1; i++) {
			final HierarchyLevelDefinitionBean hierarchyBean = hierarchyList.get(i);
			if (!GtnFrameworkWebserviceConstant.USER_DEFINED.equals(hierarchyBean.getLevelValueReference())) {
				final GtnFrameworkSingleColumnRelationBean keyListBean = controller.getGtnFrameworkEntityMasterBean()
						.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyBean.getTableName(),
								hierarchyBean.getFieldName());
				keyListBeanList.add(keyListBean);
			}
		}
		return keyListBeanList;
	}

	public List<String> getMasterSidList(GtnWsRelationshipBuilderRequest rbRequest,
			List<HierarchyLevelDefinitionBean> hierarchyList) {
		final int levelNo = rbRequest.getLevelNo();
		final List<String> masterSidList = new ArrayList<>();
		int primaryKeyPosition = rbRequest.getPrimarySIDList().size() - 1;
		for (int i = 0; i < levelNo - 1; i++) {
			final HierarchyLevelDefinitionBean hierarchyBean = hierarchyList.get(i);
			if (!GtnFrameworkWebserviceConstant.USER_DEFINED.equals(hierarchyBean.getLevelValueReference())) {
				masterSidList.add(rbRequest.getPrimarySIDList().get(primaryKeyPosition));
			}
			primaryKeyPosition--;
		}
		return masterSidList;
	}

	public List<String> getMasterSidList(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			List<HierarchyLevelDefinitionBean> hierarchyList) {
		final int levelNo = Integer.parseInt(
				gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(3).getFilterValue1());
		final List<String> masterSidList = new ArrayList<>();
		List<String> primaryIdList = (List<String>) gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList().get(4).getFilterValue3();
		int primaryKeyPosition = primaryIdList.size() - 1;
		for (int i = 0; i < levelNo - 1; i++) {
			final HierarchyLevelDefinitionBean hierarchyBean = hierarchyList.get(i);
			if (!GtnFrameworkWebserviceConstant.USER_DEFINED.equals(hierarchyBean.getLevelValueReference())) {
				masterSidList.add(primaryIdList.get(primaryKeyPosition));
			}
			primaryKeyPosition--;
		}
		return masterSidList;
	}

}
