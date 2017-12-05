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
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
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
			HierarchyLevelDefinitionBean previousHierarchyLevelBean = hierarchyLevelDefinitionList.get(index - 1);
			List<Object> inputs = new ArrayList<>();
			inputs.add(relationBean.getRelationshipBuilderSid());
			inputs.add(previousHierarchyLevelBean.getLevelNo());
			inputs.add(relationBean.getVersionNo());
			List<Object> inputsForFinalQuery = null;
			String finalQeury = null;
			if (!atomicBoolean.get()) {
				GtnFrameworkQueryGeneratorBean hierarchyQuery = getCheckForUpdateQuery(currnetHierarchyLevelBean,
						previousHierarchyLevelBean);
				List<String> whereQueryList = getRelationQueriesForThread(relationBean.getRelationshipBuilderSid(),
						relationBean.getVersionNo(), hierarchyLevelDefinitionList.subList(0, index)
								.toArray(new HierarchyLevelDefinitionBean[index]));
				inputs.addAll(whereQueryList);
				gtnHierarchyServiceBuilder.getInboundRestrictionQueryForAutoUpdate(hierarchyQuery);
				String replacedQuery = gtnWsSqlService.getReplacedQuery(inputs, hierarchyQuery.generateQuery());

				inputsForFinalQuery = new ArrayList<>();
				inputsForFinalQuery.add(relationBean.getRelationshipBuilderSid());
				inputsForFinalQuery.add(currnetHierarchyLevelBean.getLevelNo());
				inputsForFinalQuery.add(relationBean.getVersionNo());
				inputsForFinalQuery.add(replacedQuery);
			}

			if (!atomicBoolean.get() && inputsForFinalQuery != null)
				finalQeury = gtnWsSqlService.getQuery(inputsForFinalQuery, "checkForUpdateInAutomaticRelation");
			List<?> result;
			if (!atomicBoolean.get()) {
				result = gtnSqlQueryEngine.executeSelectQuery(finalQeury);
				if (Integer.parseInt(result.get(0).toString()) == 1)
					atomicBoolean.compareAndSet(Boolean.FALSE, Boolean.TRUE);
			}
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error(" Error " + e.getErrorMessage());
		}
	}

	private GtnFrameworkQueryGeneratorBean getCheckForUpdateQuery(HierarchyLevelDefinitionBean hierarchyLevelBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean) {
		GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
		GtnFrameworkHierarchyQueryBean hierarchyQuery = fileService.getQueryFromFile(
				hierarchyLevelBean.getHierarchyDefinitionSid(), hierarchyLevelBean.getHierarchyLevelDefinitionSid(),
				hierarchyLevelBean.getVersionNo());
		GtnFrameworkQueryGeneratorBean queryGenerartorBean = hierarchyQuery.getQuery();
		addJoinClause(previousHierarchyLevelBean, queryGenerartorBean);
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyLevelBean.getTableName(),
						hierarchyLevelBean.getFieldName());
		String selectClause = "CONCAT( RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO," + keyBean.getActualTtableName()
				+ "." + keyBean.getWhereClauseColumn() + ",'.')";
		queryGenerartorBean.addSelectClauseBean(null, "HIERARCHY_NO", false,
				selectClause);
		return queryGenerartorBean;
	}

	private List<String> getRelationQueriesForThread(int relationshipSid, int versionNo,
			HierarchyLevelDefinitionBean... hierarchyLevelDefinitionList) {
		List<String> queryList = new ArrayList<>();
		List<Object> input = new ArrayList<>();
		for (HierarchyLevelDefinitionBean levelDtoRelation : hierarchyLevelDefinitionList) {
			if (!levelDtoRelation.isUserDefined()) {
				input.add(levelDtoRelation.getLevelNo());
				input.add(relationshipSid);
				input.add(versionNo);
				String relationQuery = gtnWsSqlService.getQuery(input, "relationShipSubQueryForSubQuery");
				queryList.add(relationQuery);
				input.clear();
			}
		}
		return queryList;
	}

	private void addJoinClause(HierarchyLevelDefinitionBean hierarchyLevelBean,
			GtnFrameworkQueryGeneratorBean queryGenerartorBean) {
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyLevelBean.getTableName(),
						hierarchyLevelBean.getFieldName());
		GtnFrameworkJoinClauseBean relationJoin = queryGenerartorBean.addJoinClauseBean("RELATIONSHIP_LEVEL_DEFINITION",
				"RELATIONSHIP_LEVEL_DEFINITION", GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
	}


}
