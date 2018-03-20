package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.serviceimpl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.service.GtnFrameworkCCPInsertQueryGenerator;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
@Component("ProductQueryGenerator")
public class GtnFrameworkCCPProductQueryGenerator implements GtnFrameworkCCPInsertQueryGenerator {

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Autowired
	private GtnFrameworkFileReadWriteService fileReadWriteService;

	private static final String RELATIONSHIP_BUILD_HIERARCHY_NO = "RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO";
	private static final String RELATIONSHIP_LEVEL_DEFN = "RELATIONSHIP_LEVEL_DEFINITION";
	private static final String RELATIONSHIP_LEVEL_RELATIONSHIP_BUILDER_SID = "RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID";
	private static final String RELATIONSHIP_BUILD_VERSION = "RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO";
	private static final String RELATIONSHIP_LEVEL_NO = "RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO";
	private static final String RELATIONSHIP_RELATIONSHIP_LEVEL_VALUES = "RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_VALUES";

	public StringBuilder getCCPInsertQuery(List<GtnFrameworkRelationshipLevelDefintionBean> selectedRelationLevelList,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList, 
			int relationVersionNo) {
		HierarchyLevelDefinitionBean lastLinketLevel = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyLevelDefinitionList);
		GtnFrameworkQueryGeneratorBean queryBean = getCustomerContractSidQuery(selectedRelationLevelList,
				hierarchyLevelDefinitionList, lastLinketLevel);
		queryBean.addSelectClauseBean(RELATIONSHIP_BUILD_HIERARCHY_NO, null, Boolean.TRUE, null);
		getParentHierarchyCondition(queryBean, lastLinketLevel);
		getWhereQueryForCustomerAndContract(selectedRelationLevelList, hierarchyLevelDefinitionList, queryBean,
				relationVersionNo);
		StringBuilder query = new StringBuilder(queryBean.generateQuery());
		return query;

	}

	private void getParentHierarchyCondition(GtnFrameworkQueryGeneratorBean queryBean,
			HierarchyLevelDefinitionBean lastLinketLevel) {
		GtnFrameworkSingleColumnRelationBean keyListBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(lastLinketLevel.getTableName(),
						lastLinketLevel.getFieldName());
		GtnFrameworkJoinClauseBean relationJoin = queryBean.addJoinClauseBean(RELATIONSHIP_LEVEL_DEFN,
				RELATIONSHIP_LEVEL_DEFN, GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean(RELATIONSHIP_RELATIONSHIP_LEVEL_VALUES, keyListBean.getMasterIdColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean(RELATIONSHIP_BUILD_HIERARCHY_NO, null, GtnFrameworkOperatorType.LIKE);
		relationJoin.addConditionBean(RELATIONSHIP_BUILD_VERSION, null, GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean(RELATIONSHIP_LEVEL_NO, null, GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean(RELATIONSHIP_LEVEL_RELATIONSHIP_BUILDER_SID, null,
				GtnFrameworkOperatorType.EQUAL_TO);
	}

	public StringBuilder getParentHierarchyNo(
			List<GtnFrameworkRelationshipLevelDefintionBean> customerHierarchyLevelDefinitionList,
			GtnFrameworkRelationshipLevelDefintionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder query = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			GtnFrameworkRelationshipLevelDefintionBean leveldto = customerHierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				query.append(",'%'");
				query.append(",'.'");
				continue;
			}
			query.append(',');
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			query.append(singleColumnRelationBean.getActualTtableName()).append('.')
					.append(singleColumnRelationBean.getWhereClauseColumn());
			query.append(",'.'");
		}
		finalQuery.append("concat( RELATIONSHIP_BUILDER_SID,'-'");
		finalQuery.append(query);
		finalQuery.append(')');
		return finalQuery;
	}

	public GtnFrameworkQueryGeneratorBean getCustomerContractSidQuery(
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerContractList,
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean lastLinketLevel) {
		if (selectedCustomerContractList == null || selectedCustomerContractList.isEmpty())
			return null;
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(lastLinketLevel,
				Collections.<String>emptyList());
		queryBean.removeAllWhereClauseConfigList();
		queryBean.removeSelectClauseByIndex(0);
		queryBean.removeSelectClauseByIndex(0);
			queryBean.addSelectClauseBean("ITEM_MASTER.ITEM_MASTER_SID", "ITEM_MASTER_SID1", Boolean.TRUE, null);
			return queryBean;
	}

	public GtnFrameworkQueryGeneratorBean getQueryForLinkedLevel(
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto, List<String> groupFilteredItems) {
		GtnFrameworkHierarchyQueryBean queryBaen = fileReadWriteService.getQueryFromFile(
				selectedHierarchyLevelDto.getHierarchyDefinitionSid(),
				selectedHierarchyLevelDto.getHierarchyLevelDefinitionSid(),
				selectedHierarchyLevelDto.getVersionNo());
		return queryBaen.getQuery();

	}

	private void getWhereQueryForCustomerAndContract(
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerContractList,
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList,
			GtnFrameworkQueryGeneratorBean queryBean, int relationVersionNo) {
		List<GtnFrameworkRelationshipLevelDefintionBean> modifiableList = new ArrayList<>(selectedCustomerContractList);
		Collections.sort(modifiableList);

		int maxlevelNo = 0;
		for (GtnFrameworkRelationshipLevelDefintionBean leveldto : modifiableList) {
			if (maxlevelNo < leveldto.getLevelNo())
				maxlevelNo = leveldto.getLevelNo();
		}
		getWhereQueryByAllRelationShip(modifiableList, maxlevelNo, 0, queryBean);
	}

	@SuppressWarnings("unchecked")
	public void getWhereQueryByAllRelationShip(List<GtnFrameworkRelationshipLevelDefintionBean> modifiableList,
			int maxlevelNo, int startPosition, GtnFrameworkQueryGeneratorBean queryBean) {
		String whereClauseFieldName = "";
		for (int i = startPosition; i < maxlevelNo; i++) {
			List<Object> dataList = GtnFrameworkRelationshipLevelDefintionBean
					.getLinkedLevelListByLevelNo(modifiableList, i + 1);

			Set<String> masterSids = (Set<String>) dataList.get(0);
			List<GtnFrameworkRelationshipLevelDefintionBean> levelBeanList = (List<GtnFrameworkRelationshipLevelDefintionBean>) dataList
					.get(1);
			if (levelBeanList.isEmpty())
				continue;

			String tableName = levelBeanList.get(0).getTableName();
			String fieldName = levelBeanList.get(0).getFieldName();

			GtnFrameworkSingleColumnRelationBean keyListBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(tableName, fieldName);

			whereClauseFieldName = keyListBean.getWhereClauseColumn();
			queryBean.addWhereClauseBean(keyListBean.getActualTtableName() + "." + whereClauseFieldName, null,
					GtnFrameworkOperatorType.IN, GtnFrameworkDataType.LIST, new ArrayList<>(masterSids));
		}

	}

}
