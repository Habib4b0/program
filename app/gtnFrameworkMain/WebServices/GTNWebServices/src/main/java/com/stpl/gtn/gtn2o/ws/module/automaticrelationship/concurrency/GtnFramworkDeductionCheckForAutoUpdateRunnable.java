package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
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
public class GtnFramworkDeductionCheckForAutoUpdateRunnable implements Runnable {

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;

	private int index;
	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnFramworkDeductionCheckForAutoUpdateRunnable.class);
	private GtnWsRelationshipBuilderBean relationBean;
	private List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList;
	private List<Integer> itemMastersidList;
	private AtomicBoolean atomicBoolean;

	public GtnFramworkDeductionCheckForAutoUpdateRunnable() {
		super();
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}
	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}
	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public List<Integer> getItemMastersidList() {
		return itemMastersidList;
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




	public void setItemMastersidList(List<Integer> itemMastersidList) {
		this.itemMastersidList = itemMastersidList;
	}

	public GtnFrameworkHierarchyService getGtnHierarchyServiceBuilder() {
		return gtnHierarchyServiceBuilder;
	}


	public GtnWsRelationshipBuilderBean getRelationBean() {
		return relationBean;
	}

	public void setHierarchyLevelDefinitionList(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		this.hierarchyLevelDefinitionList = Collections.unmodifiableList(hierarchyLevelDefinitionList);
	}

	public void setRelationBean(GtnWsRelationshipBuilderBean relationBean) {
		this.relationBean = relationBean;
	}

	public List<HierarchyLevelDefinitionBean> getHierarchyLevelDefinitionList() {
		return Collections.unmodifiableList(hierarchyLevelDefinitionList);
	}


	public int getIndex() {
		return index;
	}

	public void setAtomicBoolean(AtomicBoolean atomicBoolean) {
		this.atomicBoolean = atomicBoolean;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public AtomicBoolean getAtomicBoolean() {
		return atomicBoolean;
	}


	@Override
	public void run() {
		try {
			HierarchyLevelDefinitionBean currnetHierarchyLevelBean = hierarchyLevelDefinitionList.get(index);

			HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
					.getPreviousLinkedLevel(hierarchyLevelDefinitionList, currnetHierarchyLevelBean);
			List<Object> input = new ArrayList<>();
			input.add(relationBean.getRelationshipBuilderSid());
			input.add(previousHierarchyLevelBean.getLevelNo());
			input.add(relationBean.getVersionNo());
			input.add(getListToString(itemMastersidList));
			List<Object> finalInputForQuery = null;
			String finalQeury = null;
			if (!atomicBoolean.get()) {
				GtnFrameworkQueryGeneratorBean hierarchyQuery = getCheckForUpdateQuery(currnetHierarchyLevelBean,
						previousHierarchyLevelBean);

				String query = gtnWsSqlService.getReplacedQuery(input, hierarchyQuery.generateQuery());
				finalInputForQuery = new ArrayList<>();
				finalInputForQuery.add(relationBean.getRelationshipBuilderSid());
				finalInputForQuery.add(currnetHierarchyLevelBean.getLevelNo());
				finalInputForQuery.add(relationBean.getVersionNo());
				finalInputForQuery.add(query);
			}

			if (!atomicBoolean.get() && finalInputForQuery != null)
				finalQeury = gtnWsSqlService.getQuery(finalInputForQuery, "checkForUpdateInAutomaticRelation");
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
		String hierarchyNoSelectClause = getHierarchyNo(hierarchyLevelDefinitionList, hierarchyLevelBean).toString();
		addJoinClause(previousHierarchyLevelBean, queryGenerartorBean);
		queryGenerartorBean.addSelectClauseBean(null, "HIERARCHY_NO", false,
				hierarchyNoSelectClause);
		queryGenerartorBean.removeAllWhereClauseConfigList();
		gtnHierarchyServiceBuilder.getInboundRestrictionQueryForAutoUpdate(queryGenerartorBean);
		queryGenerartorBean.addWhereClauseBean("RS_CONTRACT_DETAILS.ITEM_MASTER_SID", null, GtnFrameworkOperatorType.IN,
				GtnFrameworkDataType.NULL_ALLOWED, null);
		return queryGenerartorBean;
	}


	private void addJoinClause(HierarchyLevelDefinitionBean hierarchyLevelBean,
			GtnFrameworkQueryGeneratorBean queryGenerartorBean) {
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyLevelBean.getTableName(),
						hierarchyLevelBean.getFieldName());
		GtnFrameworkJoinClauseBean rsDetailsJoin = queryGenerartorBean.addJoinClauseBean("RS_CONTRACT_DETAILS",
				"RS_CONTRACT_DETAILS", GtnFrameworkJoinType.JOIN);
		rsDetailsJoin.addConditionBean("RS_CONTRACT.RS_CONTRACT_SID", "RS_CONTRACT_DETAILS.RS_CONTRACT_SID",
				GtnFrameworkOperatorType.EQUAL_TO);

		GtnFrameworkJoinClauseBean relationJoinBean = queryGenerartorBean.addJoinClauseBean("RELATIONSHIP_LEVEL_DEFINITION",
				"RELATIONSHIP_LEVEL_DEFINITION", GtnFrameworkJoinType.JOIN);
		relationJoinBean.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoinBean.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoinBean.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoinBean.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoinBean.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO",
				getHierarchyNoForRelationShip(hierarchyLevelDefinitionList, hierarchyLevelBean),
				GtnFrameworkOperatorType.LIKE);

	}

	public StringBuilder getHierarchyNo(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder initialQuery = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
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
		finalQuery.append("concat( RELATIONSHIP_BUILDER_SID,'-'");
		finalQuery.append(initialQuery);
		finalQuery.append(")");
		return finalQuery;
	}

	public String getHierarchyNoForRelationShip(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder query = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean hierarchyDto = hierarchyLevelDefinitionList.get(i);
			if (hierarchyDto.getTableName().isEmpty()) {
				query.append(",'%'");
				query.append(",'.'");
				continue;
			}
			query.append(",");
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyDto.getTableName(), hierarchyDto.getFieldName());
			query.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			query.append(",'.'");
		}
		finalQuery.append("concat( RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID,'-'");
		finalQuery.append(query);
		query.append(",'%'");
		finalQuery.append(")");
		return finalQuery.toString();
	}

	private String getListToString(Collection<?> masterSids) {
		StringBuilder result = new StringBuilder();
		if (masterSids != null && !masterSids.isEmpty()) {
			for (Object value : masterSids) {
				result.append("'" + value + "' ,");
			}
			result.deleteCharAt(result.length() - 1);
			return result.toString();
		}
		return "''";
	}

}
