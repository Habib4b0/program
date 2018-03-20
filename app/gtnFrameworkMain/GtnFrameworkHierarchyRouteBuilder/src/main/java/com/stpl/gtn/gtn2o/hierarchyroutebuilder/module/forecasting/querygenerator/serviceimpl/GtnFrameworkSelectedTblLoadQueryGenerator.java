package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.serviceimpl;

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
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFrameworkSelectedTblLoadQueryGenerator {

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private GtnFrameworkFileReadWriteService fileService;
	private static final String RELATION_HIERARCHY_JOIN = "HIERARCHY_NO_JOIN.HIERARCHY_NO";
	private static final String RELATION_HIERARCHY_LEVEL_JOIN = "HIERARCHY_NO_JOIN.LEVEL_NO";
	private static final String RS_CONTRACT = "RS_CONTRACT";
	private static final String CFP_CONTRACT_SID = "CFP_CONTRACT.CFP_CONTRACT_SID";
	private static final String RS_CONTRACT_DETAILS = "RS_CONTRACT_DETAILS";
	private static final String COMPANY_MASTER_SID = "COMPANY_MASTER.COMPANY_MASTER_SID";
	private static final String ITEM_MASTER_SID = "ITEM_MASTER.ITEM_MASTER_SID";
	private static final String RELATIONSHIP_BUILD_VERSION = "RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO";
	private static final String RELATIONSHIP_BUILD_HIERARCHY_NO = "RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO";
	private static final String RELATIONSHIP_LEVEL_DEFN = "RELATIONSHIP_LEVEL_DEFINITION";

	public String getChildLevelQueryForProduct(HierarchyLevelDefinitionBean selectedHierarchyLevelDto,
			String businessUnitValue) {
		GtnFrameworkQueryGeneratorBean finalQueryBean = getQueryForLinkedLevel(selectedHierarchyLevelDto);
		finalQueryBean.removeSelectClauseByIndex(0);
		finalQueryBean.removeSelectClauseByIndex(0);
		finalQueryBean.addSelectClauseBean(RELATION_HIERARCHY_LEVEL_JOIN, null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.RELATIONSHIP_LEVEL_VALUES", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.PARENT_NODE", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.LEVEL_NAME", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_LEVEL_DEFINITION.LEVEL_VALUE_REFERENCE", null, Boolean.TRUE,
				null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_LEVEL_DEFINITION.TABLE_NAME", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_LEVEL_DEFINITION.FIELD_NAME", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.RELATIONSHIP_LEVEL_SID", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean(RELATION_HIERARCHY_JOIN, null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.RELATIONSHIP_BUILDER_SID", null, Boolean.TRUE, null);
		GtnFrameworkJoinClauseBean relationTableJoin = finalQueryBean.addJoinClauseBean(RELATIONSHIP_LEVEL_DEFN,
				RELATIONSHIP_LEVEL_DEFN, GtnFrameworkJoinType.JOIN);
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(selectedHierarchyLevelDto.getTableName(),
						selectedHierarchyLevelDto.getFieldName());
		relationTableJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_VALUES",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationTableJoin.addConditionBean(RELATIONSHIP_BUILD_VERSION, null, GtnFrameworkOperatorType.EQUAL_TO);
		relationTableJoin.addConditionBean(RELATIONSHIP_BUILD_HIERARCHY_NO, null, GtnFrameworkOperatorType.LIKE);
		GtnFrameworkJoinClauseBean hierarchyTableJoin = finalQueryBean.addJoinClauseBean(RELATIONSHIP_LEVEL_DEFN,
				"HIERARCHY_NO_JOIN", GtnFrameworkJoinType.JOIN);
		hierarchyTableJoin.addConditionBean(RELATION_HIERARCHY_JOIN, "RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO+'%'",
				GtnFrameworkOperatorType.LIKE);
		hierarchyTableJoin.addConditionBean("HIERARCHY_NO_JOIN.VERSION_NO", null, GtnFrameworkOperatorType.EQUAL_TO);
		hierarchyTableJoin.addConditionBean(RELATION_HIERARCHY_LEVEL_JOIN, null, GtnFrameworkOperatorType.LESSTHAN);
		hierarchyTableJoin.addConditionBean(RELATION_HIERARCHY_JOIN, null, GtnFrameworkOperatorType.NOT_IN);
		GtnFrameworkJoinClauseBean hierarchyLevelDefnTableJoin = finalQueryBean.addJoinClauseBean(
				"HIERARCHY_LEVEL_DEFINITION", "HIERARCHY_LEVEL_DEFINITION", GtnFrameworkJoinType.JOIN);
		hierarchyLevelDefnTableJoin.addConditionBean("HIERARCHY_LEVEL_DEFINITION.HIERARCHY_LEVEL_DEFINITION_SID",
				"HIERARCHY_NO_JOIN.HIERARCHY_LEVEL_DEFINITION_SID", GtnFrameworkOperatorType.EQUAL_TO);
		finalQueryBean.addWhereClauseBean("ITEM_MASTER.ORGANIZATION_KEY", null, GtnFrameworkOperatorType.EQUAL_TO,
				GtnFrameworkDataType.STRING, businessUnitValue);
		finalQueryBean.addOrderByClauseBean(RELATION_HIERARCHY_LEVEL_JOIN, "ASC");
		return finalQueryBean.generateQuery();
	}

	public String getQueryForSelectedCustomer(GtnForecastHierarchyInputBean inputBean,
			List<HierarchyLevelDefinitionBean> hierarchyDefinitionList)
			throws GtnFrameworkGeneralException {
		HierarchyLevelDefinitionBean LastHierarchyLevelDto = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyDefinitionList);
		GtnFrameworkQueryGeneratorBean queryBean = getQueryToFilterCustomerProduct(LastHierarchyLevelDto,
				inputBean.getGroupFilterCompenies());
		addRelationSelectClauseForSelectedTable(queryBean);
		addRelationShipJoinMoveQuery(queryBean, inputBean.getSelectedHierarchyLevelDto(), LastHierarchyLevelDto,
				hierarchyDefinitionList);
		getDeductionJoin(inputBean.getDeductionLevel(), inputBean.getDeductionValue(), queryBean);
		return queryBean.generateQuery();
	}

	private GtnFrameworkQueryGeneratorBean getQueryToFilterCustomerProduct(
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto, List<String> groupFilteredItems) {
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(selectedHierarchyLevelDto);
		addGroupFilterCondition(selectedHierarchyLevelDto, groupFilteredItems, queryBean);
		return queryBean;
	}

	private GtnFrameworkQueryGeneratorBean addRelationSelectClauseForSelectedTable(
			GtnFrameworkQueryGeneratorBean queryBean) {
		queryBean.removeAllSelectClause();
		queryBean.addSelectClauseBean("PARENT_RELATION.HIERARCHY_NO", "HIERARCHY_NO", Boolean.TRUE, null);
		return queryBean;
	}

	public void getDeductionJoin(String dedLevel, String dedValue, GtnFrameworkQueryGeneratorBean queryBean) {
		if (!dedLevel.isEmpty() && !dedValue.isEmpty()) {
			GtnFrameworkJoinClauseBean rsContractJoin = queryBean.addJoinClauseBean(RS_CONTRACT, RS_CONTRACT,
					GtnFrameworkJoinType.JOIN);
			rsContractJoin.addConditionBean("RS_CONTRACT.CONTRACT_MASTER_SID", "CONTRACT_MASTER.CONTRACT_MASTER_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			rsContractJoin.addConditionBean(CFP_CONTRACT_SID, "RS_CONTRACT.CFP_CONTRACT_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			rsContractJoin.addConditionBean("RS_CONTRACT." + dedLevel, dedValue, GtnFrameworkOperatorType.EQUAL_TO);

			GtnFrameworkJoinClauseBean rsContractDetailsJoin = queryBean.addJoinClauseBean(RS_CONTRACT_DETAILS,
					RS_CONTRACT_DETAILS, GtnFrameworkJoinType.JOIN);
			rsContractDetailsJoin.addConditionBean("RS_CONTRACT_DETAILS.RS_CONTRACT_SID", "RS_CONTRACT.RS_CONTRACT_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			rsContractDetailsJoin.addConditionBean(COMPANY_MASTER_SID, "CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
		}
	}

	private void addRelationShipJoinMoveQuery(GtnFrameworkQueryGeneratorBean queryBean,
			GtnFrameworkRelationshipLevelDefintionBean selectedHierarchyLevelDto,
			HierarchyLevelDefinitionBean lastLinkedHierarchyLevelDto,
			List<HierarchyLevelDefinitionBean> levelHierarchyLevelDefinitionList) {
		queryBean.removeAllWhereClauseConfigList();

		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(lastLinkedHierarchyLevelDto.getTableName(),
						lastLinkedHierarchyLevelDto.getFieldName());
		String relationShipLevelDef = "RELATIONSHIP_LEVEL_DEFINITION";
		String relationShipBuilderSidDef = "RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID";
		GtnFrameworkJoinClauseBean relationMoveJoin = queryBean.addJoinClauseBean(relationShipLevelDef,
				relationShipLevelDef, GtnFrameworkJoinType.JOIN);
		relationMoveJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_VALUES",
				keyBean.getActualTtableName() + "." + keyBean.getPrimaryKeyColumnName(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationMoveJoin.addConditionBean(relationShipBuilderSidDef, null, GtnFrameworkOperatorType.EQUAL_TO);
		relationMoveJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationMoveJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationMoveJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO",
				"'".concat(selectedHierarchyLevelDto.getHierarchyNo()).concat("%'"), GtnFrameworkOperatorType.LIKE);
		relationMoveJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO",
				getHierarchyNoForRelationShip(levelHierarchyLevelDefinitionList, lastLinkedHierarchyLevelDto),
				GtnFrameworkOperatorType.LIKE);
		relationTwoParentJoin(queryBean, relationShipLevelDef, GtnFrameworkOperatorType.LESSTHANOREQUALTO);

	}

	public void relationTwoParentJoin(GtnFrameworkQueryGeneratorBean queryBean, String relationShipLevelDef,
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

	public GtnFrameworkQueryGeneratorBean getQueryForLinkedLevel(
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto) {
		System.out.println(selectedHierarchyLevelDto.getHierarchyLevelDefinitionSid());
		System.out.println(selectedHierarchyLevelDto.getHierarchyDefinitionSid());
		GtnFrameworkHierarchyQueryBean queryBean = fileService.getQueryFromFile(
				selectedHierarchyLevelDto.getHierarchyDefinitionSid(),
				selectedHierarchyLevelDto.getHierarchyLevelDefinitionSid(), selectedHierarchyLevelDto.getVersionNo());
		return queryBean.getQuery();

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
		finalQuery.append(" ,'%')");
		return finalQuery.toString();
	}

	public void addGroupFilterCondition(HierarchyLevelDefinitionBean selectedHierarchyLevelDto,
			List<String> groupFilteredItems, GtnFrameworkQueryGeneratorBean finalQueryBean) {
		StringBuilder query = new StringBuilder();
		GtnFrameworkSingleColumnRelationBean keyRealtionBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(selectedHierarchyLevelDto.getTableName(),
						selectedHierarchyLevelDto.getFieldName());
		query.append(keyRealtionBean.getActualTtableName());
		query.append('.');
		query.append(keyRealtionBean.getWhereClauseColumn());
		finalQueryBean.addWhereClauseBean(query.toString(), null, GtnFrameworkOperatorType.IN,
				GtnFrameworkDataType.NULL_ALLOWED, "?");
		getWhereQueryBasedOnHierarchyType(selectedHierarchyLevelDto.getHierarchyCategory(), groupFilteredItems,
				finalQueryBean);
	}

	private void getWhereQueryBasedOnHierarchyType(String hieType, List<String> groupFilteredCompanies,
			GtnFrameworkQueryGeneratorBean finalQueryBean) {
		if (groupFilteredCompanies.isEmpty())
			return;
		if ("Customer Hierarchy".equals(hieType)) {
			finalQueryBean.addWhereClauseBean(COMPANY_MASTER_SID, null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.LIST, groupFilteredCompanies);
		} else {
			finalQueryBean.addWhereClauseBean(ITEM_MASTER_SID, null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.LIST, groupFilteredCompanies);
		}
	}

}
