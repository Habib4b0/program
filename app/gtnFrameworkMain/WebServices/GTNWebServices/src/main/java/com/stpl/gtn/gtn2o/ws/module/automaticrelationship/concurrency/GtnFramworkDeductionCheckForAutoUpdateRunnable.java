package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "prototype")
public class GtnFramworkDeductionCheckForAutoUpdateRunnable implements Callable<String> {

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
		return Collections.unmodifiableList(itemMastersidList);
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
		this.itemMastersidList = Collections.unmodifiableList(itemMastersidList);
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

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String call() throws Exception {
		try {
			HierarchyLevelDefinitionBean currnetHierarchyLevelBean = hierarchyLevelDefinitionList.get(index);

			HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
					.getPreviousLinkedLevel(hierarchyLevelDefinitionList, currnetHierarchyLevelBean);
			List<Object> input = new ArrayList<>();
			input.add(getListToString(itemMastersidList));
			List<Object> finalInputForQuery = null;
			GtnFrameworkQueryGeneratorBean hierarchyQuery = getCheckForUpdateQuery(currnetHierarchyLevelBean,
					previousHierarchyLevelBean, relationBean.getRelationshipBuilderSid());
			String query = gtnWsSqlService.getReplacedQuery(input, hierarchyQuery.generateQuery());
			finalInputForQuery = new ArrayList<>();
			finalInputForQuery.add(relationBean.getRelationshipBuilderSid());
			finalInputForQuery.add(currnetHierarchyLevelBean.getLevelNo());
			finalInputForQuery.add(relationBean.getVersionNo());
			finalInputForQuery.add(query);
			return gtnWsSqlService.getQuery(finalInputForQuery, "checkForUpdateInAutomaticRelation");
		} catch (Exception e) {
			LOGGER.error(" Error " + e.getMessage());
		}
		return "";
	}

	private GtnFrameworkQueryGeneratorBean getCheckForUpdateQuery(HierarchyLevelDefinitionBean hierarchyLevelBean,
			HierarchyLevelDefinitionBean previousHierarchyLevelBean, int relationShipBuilderSid) {
		GtnFrameworkFileReadWriteService fileServiceDeduction = new GtnFrameworkFileReadWriteService();
		GtnFrameworkHierarchyQueryBean hierarchyQuery = fileServiceDeduction.getQueryFromFile(
				hierarchyLevelBean.getHierarchyDefinitionSid(), hierarchyLevelBean.getHierarchyLevelDefinitionSid(),
				hierarchyLevelBean.getVersionNo());
		GtnFrameworkQueryGeneratorBean queryGenerartorBeanDeduction = hierarchyQuery.getQuery();
		String hierarchyNoSelectClause = getHierarchyNo(hierarchyLevelDefinitionList, hierarchyLevelBean,
				relationShipBuilderSid).toString();
		addJoinClause(previousHierarchyLevelBean, queryGenerartorBeanDeduction);
		queryGenerartorBeanDeduction.addSelectClauseBean(null, "HIERARCHY_NO", false, hierarchyNoSelectClause);
		queryGenerartorBeanDeduction.removeWhereClauseConfigListByIndex(1,
				queryGenerartorBeanDeduction.getWhereClauseConfigList().size() - 1);
		gtnHierarchyServiceBuilder.getInboundRestrictionQueryForAutoUpdate(queryGenerartorBeanDeduction);
		queryGenerartorBeanDeduction.addWhereClauseBean("RS_CONTRACT_DETAILS.ITEM_MASTER_SID", null,
				GtnFrameworkOperatorType.IN, GtnFrameworkDataType.NULL_ALLOWED, null);
		return queryGenerartorBeanDeduction;
	}

	private void addJoinClause(HierarchyLevelDefinitionBean hierarchyLevelBean,
			GtnFrameworkQueryGeneratorBean queryGenerartorBean) {
		GtnFrameworkJoinClauseBean rsDetailsJoin = queryGenerartorBean.addJoinClauseBean("RS_CONTRACT_DETAILS",
				"RS_CONTRACT_DETAILS", GtnFrameworkJoinType.JOIN);
		rsDetailsJoin.addConditionBean("RS_CONTRACT.RS_CONTRACT_SID", "RS_CONTRACT_DETAILS.RS_CONTRACT_SID",
				GtnFrameworkOperatorType.EQUAL_TO);
	}

	public StringBuilder getHierarchyNo(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto, int relationShipBuilderSid) {
		StringBuilder initialQueryDeduction = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean levelDTO = hierarchyLevelDefinitionList.get(i);
			if (levelDTO.getTableName().isEmpty()) {
				initialQueryDeduction.append(",'%'");
				initialQueryDeduction.append(",'.'");
				continue;
			}
			initialQueryDeduction.append(",");
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(levelDTO.getTableName(), levelDTO.getFieldName());
			initialQueryDeduction.append(singleColumnRelationBean.getActualTtableName()).append(".")
					.append(singleColumnRelationBean.getWhereClauseColumn());
			initialQueryDeduction.append(",'.'");
		}
		finalQuery.append("concat( " + relationShipBuilderSid + ",'-'");
		finalQuery.append(initialQueryDeduction);
		finalQuery.append(")");
		return finalQuery;
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
