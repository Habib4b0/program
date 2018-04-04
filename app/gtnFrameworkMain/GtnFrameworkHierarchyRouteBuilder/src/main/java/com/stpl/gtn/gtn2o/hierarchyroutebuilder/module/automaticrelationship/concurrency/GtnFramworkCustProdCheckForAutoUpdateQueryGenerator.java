package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.concurrency;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFramworkTableBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFramworkCustProdCheckForAutoUpdateQueryGenerator {

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;

	private static final String RELATIONSHIP_LEVEL_DEFINITION = "RELATIONSHIP_LEVEL_DEFINITION";

	public GtnFrameworkQueryGeneratorBean getFinalCheckQuery(HierarchyLevelDefinitionBean currnetHierarchyLevelBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		GtnFrameworkQueryGeneratorBean hierarchyQuery = getCheckForUpdateQuery(currnetHierarchyLevelBean,
				previousHierarchyLevelBean, hierarchyLevelDefinitionList);
		hierarchyQuery.removeAllWhereClauseConfigList();
		gtnHierarchyServiceBuilder.getInboundRestrictionQueryForAutoUpdate(hierarchyQuery);
		return hierarchyQuery;
	}

	private GtnFrameworkQueryGeneratorBean getCheckForUpdateQuery(HierarchyLevelDefinitionBean hierarchyLevelBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
		GtnFrameworkHierarchyQueryBean hierarchyQuery = fileService.getQueryFromFile(
				hierarchyLevelBean.getHierarchyDefinitionSid(), hierarchyLevelBean.getHierarchyLevelDefinitionSid(),
				hierarchyLevelBean.getVersionNo());
		GtnFrameworkQueryGeneratorBean queryGenerartorBean = hierarchyQuery.getQuery();
		addJoinClause(hierarchyLevelBean, previousHierarchyLevelBean, queryGenerartorBean,
				hierarchyLevelDefinitionList);
		String hierarchyNoSelectClause = getHierarchyNo(hierarchyLevelDefinitionList, hierarchyLevelBean).toString();
		queryGenerartorBean.addSelectClauseBean(null, "HIERARCHY_NO", false, hierarchyNoSelectClause);
		return queryGenerartorBean;
	}

	private void addJoinClause(HierarchyLevelDefinitionBean hierarchyLevelBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean,
			GtnFrameworkQueryGeneratorBean queryGenerartorBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(previousHierarchyLevelBean.getTableName(),
						previousHierarchyLevelBean.getFieldName());
		GtnFrameworkJoinClauseBean relationJoin = queryGenerartorBean.addJoinClauseBean("#SELECTED_RElATION_SHIP",
				RELATIONSHIP_LEVEL_DEFINITION, GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO",
				getHierarchyNoForRelationShip(hierarchyLevelDefinitionList, previousHierarchyLevelBean),
				GtnFrameworkOperatorType.LIKE);
		GtnFrameworkJoinClauseBean relationDateJoin = queryGenerartorBean.addJoinClauseBean("#SELECTED_RElATION_SHIP",
				"RELATIONSHIP_LEVEL_DEFINITION2", GtnFrameworkJoinType.JOIN);
		relationDateJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION2.RELATIONSHIP_BUILDER_SID",
				"RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", GtnFrameworkOperatorType.EQUAL_TO);
		relationDateJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION2.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationDateJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION2.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		for (GtnFrameworkJoinClauseBean joinClauseBean : queryGenerartorBean.getJoinClauseConfigList()) {
			GtnFramworkTableBean tableBean = gtnFrameworkEntityMasterBean
					.getEntityBeanByTableName(joinClauseBean.getJoinTableName());
			if (tableBean == null || tableBean.getModifiedDateColumn() == null)
				continue;
			relationDateJoin.addOrConditionBean(tableBean.getModifiedDateColumn(joinClauseBean.getJoinTableAliesName()),
					"RELATIONSHIP_LEVEL_DEFINITION.MODIFIED_DATE", GtnFrameworkOperatorType.GREATERTHANOREQUALTO);
		}
		GtnFramworkTableBean tableBean = gtnFrameworkEntityMasterBean
				.getEntityBeanByTableName(queryGenerartorBean.getFromTableName());
		if (tableBean.getModifiedDateColumn() == null)
			return;
		relationDateJoin.addOrConditionBean(tableBean.getModifiedDateColumn(queryGenerartorBean.getFromTableAlies()),
				"RELATIONSHIP_LEVEL_DEFINITION.MODIFIED_DATE", GtnFrameworkOperatorType.GREATERTHANOREQUALTO);
		relationDateJoin.addOrConditionBean("RELATIONSHIP_LEVEL_DEFINITION2.HIERARCHY_NO",
				getHierarchyNo(hierarchyLevelDefinitionList, hierarchyLevelBean).toString(),
				GtnFrameworkOperatorType.LIKE);

	}

	private StringBuilder getHierarchyNo(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder initialQuery = new StringBuilder();
		StringBuilder query = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = hierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				initialQuery.append(",'%'");
				initialQuery.append(",'.'");
				continue;
			}
			initialQuery.append(",");
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			initialQuery.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			initialQuery.append(",'.'");
		}
		query.append("concat( RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID,'-'");
		query.append(initialQuery);
		query.append(")");
		return query;
	}

	private String getHierarchyNoForRelationShip(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder tempQuery = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean levelBean = hierarchyLevelDefinitionList.get(i);
			if (levelBean.getTableName().isEmpty()) {
				tempQuery.append(",'%'");
				tempQuery.append(",'.'");
				continue;
			}
			tempQuery.append(",");
			GtnFrameworkSingleColumnRelationBean keyRelaitonBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(levelBean.getTableName(), levelBean.getFieldName());
			tempQuery.append(keyRelaitonBean.getActualTtableName() + "." + keyRelaitonBean.getWhereClauseColumn());
			tempQuery.append(",'.'");
		}
		finalQuery.append("concat( RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID,'-'");
		finalQuery.append(tempQuery);
		tempQuery.append(",'%'");
		finalQuery.append(")");
		return finalQuery.toString();
	}
}
