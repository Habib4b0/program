package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.serviceimpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.service.GtnFrameworkLevelLoadQueryGenerator;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFrameworkProdLevelQueryGenerator implements GtnFrameworkLevelLoadQueryGenerator {

	@Autowired
	private GtnFrameworkFileReadWriteService fileService;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	private static final String RS_CONTRACT = "RS_CONTRACT";
	private static final String ITEM_MASTER_SID = "ITEM_MASTER.ITEM_MASTER_SID";
	private static final String CFP_CONTRACT_SID = "CFP_CONTRACT.CFP_CONTRACT_SID";
	private static final String RS_CONTRACT_DETAILS = "RS_CONTRACT_DETAILS";

	public GtnFrameworkQueryGeneratorBean getAvailableTableLoadQuery(GtnForecastHierarchyInputBean inputBean,
			HierarchyLevelDefinitionBean lastLevelDto, List<HierarchyLevelDefinitionBean> hierarchyDefinitionList) {
		GtnFrameworkQueryGeneratorBean queryBean = getQueryToFilterCustomerProduct(inputBean, lastLevelDto);
		addRelationSelectClause(queryBean, inputBean);
		addRelationShipJoin(queryBean, lastLevelDto, hierarchyDefinitionList);
		getRscontractJoinForProduct(inputBean.getDeductionLevel(), inputBean.getDeductionValue(), queryBean);
		addNdcSelectAndJoin(inputBean.isNdc(), queryBean);
		return queryBean;
	}

	private GtnFrameworkQueryGeneratorBean addRelationSelectClause(GtnFrameworkQueryGeneratorBean queryBean,
			GtnForecastHierarchyInputBean inputBean) {
		queryBean.removeAllSelectClause();
		queryBean.addSelectClauseBean("PARENT_RELATION.RELATIONSHIP_LEVEL_VALUES", "RELATIONSHIP_LEVEL_VALUES",
				Boolean.TRUE, null);
		queryBean.addSelectClauseBean("PARENT_RELATION.LEVEL_NO", "LEVEL_NO", Boolean.TRUE, null);
		queryBean.addSelectClauseBean("PARENT_RELATION.PARENT_NODE", "PARENT_NODE", Boolean.TRUE, null);
		queryBean.addSelectClauseBean("PARENT_RELATION.RELATIONSHIP_LEVEL_SID", "RELATIONSHIP_LEVEL_SID", Boolean.TRUE,
				null);
		queryBean.addSelectClauseBean("PARENT_RELATION.HIERARCHY_NO", "HIERARCHY_NO", Boolean.TRUE, null);
		queryBean.addSelectClauseBean("PARENT_RELATION.RELATIONSHIP_BUILDER_SID", "RELATIONSHIP_BUILDER_SID",
				Boolean.TRUE, null);
		return queryBean;
	}

	private void addRelationShipJoin(GtnFrameworkQueryGeneratorBean queryBean,
			HierarchyLevelDefinitionBean lastLevelDto,
			List<HierarchyLevelDefinitionBean> hierarchyDefinitionList) {
		queryBean.removeAllWhereClauseConfigList();

		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(lastLevelDto.getTableName(), lastLevelDto.getFieldName());
		String relationShipLevelDef = "RELATIONSHIP_LEVEL_DEFINITION";
		String relationShipBuilderSidDef = "RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID";
		GtnFrameworkJoinClauseBean relationJoin = queryBean.addJoinClauseBean(relationShipLevelDef,
				relationShipLevelDef, GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean(relationShipBuilderSidDef, null, GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO",
				getHierarchyNoForRelationShip(hierarchyDefinitionList, lastLevelDto),
				GtnFrameworkOperatorType.LIKE);
		relationTwoParentJoin(queryBean, relationShipLevelDef, GtnFrameworkOperatorType.EQUAL_TO);

	}

	private void relationTwoParentJoin(GtnFrameworkQueryGeneratorBean queryBean, String relationShipLevelDef,
			GtnFrameworkOperatorType operator) {
		GtnFrameworkJoinClauseBean relationJoinTwo = queryBean.addJoinClauseBean(relationShipLevelDef,
				"PARENT_RELATION", GtnFrameworkJoinType.JOIN);
		relationJoinTwo.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID",
				"PARENT_RELATION.RELATIONSHIP_BUILDER_SID", GtnFrameworkOperatorType.EQUAL_TO);
		relationJoinTwo.addConditionBean("PARENT_RELATION.VERSION_NO", null, GtnFrameworkOperatorType.EQUAL_TO);
		relationJoinTwo.addConditionBean("PARENT_RELATION.LEVEL_NO", null, operator);
		relationJoinTwo.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO",
				"PARENT_RELATION.HIERARCHY_NO+'%'", GtnFrameworkOperatorType.LIKE);
	}

	private String getHierarchyNoForRelationShip(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto) {
		StringBuilder query = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < selectedHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = hierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				query.append(",'%'");
				query.append(",'.'");
				continue;
			}
			query.append(",");
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
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
	private void getRscontractJoinForProduct(String dedLevel, String dedValue,
			GtnFrameworkQueryGeneratorBean queryBean) {
		GtnFrameworkJoinClauseBean ccpJoin = queryBean.addJoinClauseBean("CCP_DETAILS", "CCP_DETAILS",
				GtnFrameworkJoinType.JOIN);
		ccpJoin.addConditionBean(ITEM_MASTER_SID, "CCP_DETAILS.ITEM_MASTER_SID", GtnFrameworkOperatorType.EQUAL_TO);
		if (!dedLevel.isEmpty() && !dedValue.isEmpty()) {
			GtnFrameworkJoinClauseBean rsContracDetailstJoin = queryBean.addJoinClauseBean(RS_CONTRACT_DETAILS,
					RS_CONTRACT_DETAILS, GtnFrameworkJoinType.JOIN);
			rsContracDetailstJoin.addConditionBean("RS_CONTRACT_DETAILS.ITEM_MASTER_SID", ITEM_MASTER_SID,
					GtnFrameworkOperatorType.EQUAL_TO);

			GtnFrameworkJoinClauseBean rsContractJoin = queryBean.addJoinClauseBean(RS_CONTRACT, RS_CONTRACT,
					GtnFrameworkJoinType.JOIN);
			rsContractJoin.addConditionBean("RS_CONTRACT.RS_CONTRACT_SID", "RS_CONTRACT_DETAILS.RS_CONTRACT_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			rsContractJoin.addConditionBean("RS_CONTRACT." + dedLevel, dedValue, GtnFrameworkOperatorType.EQUAL_TO);

			GtnFrameworkJoinClauseBean cfpContractDetailsJoin = queryBean.addJoinClauseBean("CFP_CONTRACT_DETAILS",
					"CFP_CONTRACT_DETAILS", GtnFrameworkJoinType.JOIN);
			cfpContractDetailsJoin.addConditionBean("CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID",
					"CCP_DETAILS.COMPANY_MASTER_SID", GtnFrameworkOperatorType.EQUAL_TO);

			GtnFrameworkJoinClauseBean cfpContractJoin = queryBean.addJoinClauseBean("CFP_CONTRACT", "CFP_CONTRACT",
					GtnFrameworkJoinType.JOIN);
			cfpContractJoin.addConditionBean(CFP_CONTRACT_SID, "CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			cfpContractJoin.addConditionBean("RS_CONTRACT.CFP_CONTRACT_SID", CFP_CONTRACT_SID,
					GtnFrameworkOperatorType.EQUAL_TO);
		}
	}

	public void addNdcSelectAndJoin(boolean isNdc, GtnFrameworkQueryGeneratorBean queryBean) {
		if (isNdc) {
			GtnFrameworkJoinClauseBean strengthJoin = queryBean.addJoinClauseBean("HELPER_TABLE", "STR",
					GtnFrameworkJoinType.JOIN);
			strengthJoin.addConditionBean("STr.HELPER_TABLE_SID", "ITEM_MASTER.STRENGTH",
					GtnFrameworkOperatorType.EQUAL_TO);
			GtnFrameworkJoinClauseBean formJoin = queryBean.addJoinClauseBean("HELPER_TABLE", "FORM",
					GtnFrameworkJoinType.JOIN);
			formJoin.addConditionBean("FORm.HELPER_TABLE_SID", "ITEM_MASTER.FORM", GtnFrameworkOperatorType.EQUAL_TO);
			queryBean.addSelectClauseBean("STr.DESCRIPTION", null, Boolean.TRUE, null);
			queryBean.addSelectClauseBean("FORm.DESCRIPTION", null, Boolean.TRUE, null);
		}

	}

	private GtnFrameworkQueryGeneratorBean getQueryToFilterCustomerProduct(GtnForecastHierarchyInputBean inputBean,
			HierarchyLevelDefinitionBean lastLevelDto) {
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(lastLevelDto,
				Collections.<String>emptyList());
		addGroupFilterCondition(inputBean, queryBean, lastLevelDto);
		return queryBean;
	}

	public GtnFrameworkQueryGeneratorBean getQueryForLinkedLevel(HierarchyLevelDefinitionBean lastLevelDto,
			List<String> groupFilteredItems) {
		GtnFrameworkHierarchyQueryBean queryBaen = fileService.getQueryFromFile(
				lastLevelDto.getHierarchyDefinitionSid(), lastLevelDto.getHierarchyLevelDefinitionSid(),
				lastLevelDto.getVersionNo());
		return queryBaen.getQuery();

	}

	private void addGroupFilterCondition(GtnForecastHierarchyInputBean inputBean,
			GtnFrameworkQueryGeneratorBean finalQueryBean, HierarchyLevelDefinitionBean lastLevelDto) {
		StringBuilder query = new StringBuilder();
		GtnFrameworkSingleColumnRelationBean keyRealtionBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(lastLevelDto.getTableName(), lastLevelDto.getFieldName());
		query.append(keyRealtionBean.getActualTtableName());
		query.append(".");
		query.append(keyRealtionBean.getWhereClauseColumn());
		finalQueryBean.addWhereClauseBean(query.toString(), null, GtnFrameworkOperatorType.IN,
				GtnFrameworkDataType.NULL_ALLOWED, "?");
		getWhereQueryBasedOnHierarchyType(inputBean.getHierarchyType(), inputBean.getGroupFilterCompenies(),
				finalQueryBean);
	}

	private void getWhereQueryBasedOnHierarchyType(String hieType, List<String> groupFilteredCompanies,
			GtnFrameworkQueryGeneratorBean finalQueryBean) {
		if (groupFilteredCompanies == null || groupFilteredCompanies.isEmpty())
			return;
		finalQueryBean.addWhereClauseBean(ITEM_MASTER_SID, null, GtnFrameworkOperatorType.IN, GtnFrameworkDataType.LIST,
				groupFilteredCompanies);
	}

	public GtnFrameworkQueryGeneratorBean getCustomerContractSidQuery(
			List<HierarchyLevelDefinitionBean> selectedCustomerContractList,
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList, boolean isProduct) {
		if (selectedCustomerContractList == null || selectedCustomerContractList.isEmpty())
			return null;

		HierarchyLevelDefinitionBean lastLinketLevel = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(customerHierarchyLevelDefinitionList);
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(lastLinketLevel,
				Collections.<String>emptyList());
		queryBean.removeAllWhereClauseConfigList();
		queryBean.removeSelectClauseByIndex(0);
		queryBean.removeSelectClauseByIndex(0);
		if (isProduct) {
			queryBean.addSelectClauseBean("ITEM_MASTER.ITEM_MASTER_SID", "ITEM_MASTER_SID1", Boolean.TRUE, null);
			return queryBean;
		}
		queryBean.addSelectClauseBean("COMPANY_MASTER.COMPANY_MASTER_SID", "COMPANY_MASTER_SID1", Boolean.TRUE, null);
		queryBean.addSelectClauseBean("CONTRACT_MASTER.CONTRACT_MASTER_SID", "CONTRACT_MASTER_SID1", Boolean.TRUE,
				null);
		return queryBean;
	}
}

