package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFramworkTableBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "prototype")
public class GtnFramworkCheckForAutoUpdateRunnable implements Runnable {

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;

	private GtnWsRelationshipBuilderBean relationBean;
	private List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList;
	private int index;
	private AtomicBoolean atomicBoolean;
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFramworkCheckForAutoUpdateRunnable.class);
        private static final String RELATIONSHIP_LEVEL_DEFINITION = "RELATIONSHIP_LEVEL_DEFINITION";

	public GtnFramworkCheckForAutoUpdateRunnable() {
		super();
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}
	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnFrameworkEntityMasterBean(GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean) {
		this.gtnFrameworkEntityMasterBean = gtnFrameworkEntityMasterBean;
	}
	public GtnFrameworkEntityMasterBean getGtnFrameworkEntityMasterBean() {
		return gtnFrameworkEntityMasterBean;
	}

	public void setGtnHierarchyServiceBuilder(GtnFrameworkHierarchyService gtnHierarchyServiceBuilder) {
		this.gtnHierarchyServiceBuilder = gtnHierarchyServiceBuilder;
	}


	public GtnFrameworkHierarchyService getGtnHierarchyServiceBuilder() {
		return gtnHierarchyServiceBuilder;
	}


	public GtnWsRelationshipBuilderBean getRelationBean() {
		return relationBean;
	}

	public void setRelationBean(GtnWsRelationshipBuilderBean relationBean) {
		this.relationBean = relationBean;
	}

	public List<HierarchyLevelDefinitionBean> getHierarchyLevelDefinitionList() {
		return Collections.unmodifiableList(hierarchyLevelDefinitionList);
	}

	public void setHierarchyLevelDefinitionList(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		this.hierarchyLevelDefinitionList = Collections.unmodifiableList(hierarchyLevelDefinitionList);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public AtomicBoolean getAtomicBoolean() {
		return atomicBoolean;
	}

	public void setAtomicBoolean(AtomicBoolean atomicBoolean) {
		this.atomicBoolean = atomicBoolean;
	}

	@Override
	public void run() {
		try {
			HierarchyLevelDefinitionBean currnetHierarchyLevelBean = hierarchyLevelDefinitionList.get(index);

			HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
					.getPreviousLinkedLevel(hierarchyLevelDefinitionList, currnetHierarchyLevelBean);
			List<Object> inputs = new ArrayList<>();
			inputs.add(relationBean.getRelationshipBuilderSid());
			inputs.add(previousHierarchyLevelBean.getLevelNo());
			inputs.add(relationBean.getVersionNo());
			inputs.add(currnetHierarchyLevelBean.getLevelNo());
			inputs.add(relationBean.getVersionNo());
			List<Object> inputsForFinalQuery = null;
			String finalQeury = null;
			if (!atomicBoolean.get()) {
				GtnFrameworkQueryGeneratorBean hierarchyQuery = getCheckForUpdateQuery(currnetHierarchyLevelBean,
						previousHierarchyLevelBean);
				hierarchyQuery.removeAllWhereClauseConfigList();
				gtnHierarchyServiceBuilder.getInboundRestrictionQueryForAutoUpdate(hierarchyQuery);
				String query = gtnWsSqlService.getReplacedQuery(inputs, hierarchyQuery.generateQuery());
				inputsForFinalQuery = new ArrayList<>();
				inputsForFinalQuery.add(relationBean.getRelationshipBuilderSid());
				inputsForFinalQuery.add(currnetHierarchyLevelBean.getLevelNo());
				inputsForFinalQuery.add(relationBean.getVersionNo());
				inputsForFinalQuery.add(query);
			}

			if (!atomicBoolean.get() && inputsForFinalQuery != null)
				finalQeury = gtnWsSqlService.getQuery(inputsForFinalQuery, "checkForUpdateInAutomaticRelation");
			List<?> result;
			if (!atomicBoolean.get()) {
				
				result = gtnSqlQueryEngine.executeSelectQuery(finalQeury);
				if (Integer.parseInt(result.get(0).toString()) == 1)
					atomicBoolean.compareAndSet(Boolean.FALSE, Boolean.TRUE);
			}
		} catch (Exception e) {
			
			LOGGER.error(" Error " + e.getMessage());
		}
	}

	private GtnFrameworkQueryGeneratorBean getCheckForUpdateQuery(HierarchyLevelDefinitionBean hierarchyLevelBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean) {
		GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
		GtnFrameworkHierarchyQueryBean hierarchyQuery = fileService.getQueryFromFile(
				hierarchyLevelBean.getHierarchyDefinitionSid(), hierarchyLevelBean.getHierarchyLevelDefinitionSid(),
				hierarchyLevelBean.getVersionNo());
		GtnFrameworkQueryGeneratorBean queryGenerartorBean = hierarchyQuery.getQuery();
		addJoinClause(hierarchyLevelBean, previousHierarchyLevelBean, queryGenerartorBean);
		String hierarchyNoSelectClause = getHierarchyNo(hierarchyLevelDefinitionList, hierarchyLevelBean).toString();
		queryGenerartorBean.addSelectClauseBean(null, "HIERARCHY_NO", false,
				hierarchyNoSelectClause);
		return queryGenerartorBean;
	}


	private void addJoinClause(HierarchyLevelDefinitionBean hierarchyLevelBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean,
			GtnFrameworkQueryGeneratorBean queryGenerartorBean) {
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(previousHierarchyLevelBean.getTableName(),
						previousHierarchyLevelBean.getFieldName());
		GtnFrameworkJoinClauseBean relationJoin = queryGenerartorBean.addJoinClauseBean(RELATIONSHIP_LEVEL_DEFINITION,
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
		GtnFrameworkJoinClauseBean relationDateJoin = queryGenerartorBean.addJoinClauseBean(
				RELATIONSHIP_LEVEL_DEFINITION, "RELATIONSHIP_LEVEL_DEFINITION2", GtnFrameworkJoinType.JOIN);
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

	public StringBuilder getHierarchyNo(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
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

	public String getHierarchyNoForRelationShip(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder tempQuery = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = hierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				tempQuery.append(",'%'");
				tempQuery.append(",'.'");
				continue;
			}
			tempQuery.append(",");
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			tempQuery.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			tempQuery.append(",'.'");
		}
		finalQuery.append("concat( RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID,'-'");
		finalQuery.append(tempQuery);
		tempQuery.append(",'%'");
		finalQuery.append(")");
		return finalQuery.toString();
	}

}
