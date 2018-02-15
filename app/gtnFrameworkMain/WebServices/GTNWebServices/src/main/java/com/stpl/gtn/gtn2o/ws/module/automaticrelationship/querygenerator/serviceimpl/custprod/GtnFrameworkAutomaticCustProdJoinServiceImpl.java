package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.custprod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFramworkTableBean;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkJoinQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Component("CustProdJoin")
@Scope(value = "singleton")
public class GtnFrameworkAutomaticCustProdJoinServiceImpl implements GtnFrameworkJoinQueryGeneratorService {

	public GtnFrameworkAutomaticCustProdJoinServiceImpl() {
		super();
	}

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Override
	public void addJoinClause(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList, int levelNo) {
		HierarchyLevelDefinitionBean hierarchyLevelBean = customerHierarchyLevelDefinitionList.get(levelNo);
		HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
				.getPreviousLinkedLevel(customerHierarchyLevelDefinitionList, hierarchyLevelBean);
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(previousHierarchyLevelBean.getTableName(),
						previousHierarchyLevelBean.getFieldName());
		String relationShipLevelDef = "RELATIONSHIP_LEVEL_DEFINITION";
		String relationShipBuilderSidDef = "RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID";
		GtnFrameworkJoinClauseBean relationJoin = querygeneratorBean.addJoinClauseBean(relationShipLevelDef,
				relationShipLevelDef, GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean(relationShipBuilderSidDef, null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO",
				getHierarchyNoForRelationShip(customerHierarchyLevelDefinitionList, previousHierarchyLevelBean),
				GtnFrameworkOperatorType.LIKE);

		GtnFrameworkJoinClauseBean relationDateJoin = querygeneratorBean.addJoinClauseBean(relationShipLevelDef,
				"RELATION_DATE_FILTER", GtnFrameworkJoinType.JOIN);
		relationDateJoin.addConditionBean("RELATION_DATE_FILTER.RELATIONSHIP_BUILDER_SID",
				relationShipBuilderSidDef, GtnFrameworkOperatorType.EQUAL_TO);
		relationDateJoin.addConditionBean("RELATION_DATE_FILTER.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationDateJoin.addConditionBean("RELATION_DATE_FILTER.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		for (GtnFrameworkJoinClauseBean joinClauseBean : querygeneratorBean.getJoinClauseConfigList()) {
			GtnFramworkTableBean tableBean = gtnFrameworkEntityMasterBean
					.getEntityBeanByTableName(joinClauseBean.getJoinTableName());
			if (tableBean == null || tableBean.getModifiedDateColumn() == null)
				continue;
			relationDateJoin.addOrConditionBean(tableBean.getModifiedDateColumn(joinClauseBean.getJoinTableAliesName()),
					"RELATION_DATE_FILTER.MODIFIED_DATE", GtnFrameworkOperatorType.GREATERTHANOREQUALTO);
		}
		GtnFramworkTableBean tableBean = gtnFrameworkEntityMasterBean
				.getEntityBeanByTableName(querygeneratorBean.getFromTableName());
		if (tableBean.getModifiedDateColumn() == null)
			return;
		relationDateJoin.addOrConditionBean(tableBean.getModifiedDateColumn(querygeneratorBean.getFromTableAlies()),
				"RELATIONSHIP_LEVEL_DEFINITION.MODIFIED_DATE", GtnFrameworkOperatorType.GREATERTHANOREQUALTO);
		relationDateJoin.addOrConditionBean("RELATION_DATE_FILTER.HIERARCHY_NO",
				getHierarchyNo(customerHierarchyLevelDefinitionList, hierarchyLevelBean),
				GtnFrameworkOperatorType.LIKE);
		
		
		GtnFrameworkJoinClauseBean relationHIerachyJOin = querygeneratorBean.addJoinClauseBean(relationShipLevelDef,
				"USERDEFINED_RELATION_JOIN", GtnFrameworkJoinType.LEFT_JOIN);
		relationHIerachyJOin.addConditionBean(relationShipBuilderSidDef,
				"USERDEFINED_RELATION_JOIN.RELATIONSHIP_BUILDER_SID", GtnFrameworkOperatorType.EQUAL_TO);
		relationHIerachyJOin.addConditionBean("USERDEFINED_RELATION_JOIN.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationHIerachyJOin.addConditionBean("USERDEFINED_RELATION_JOIN.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationHIerachyJOin.addConditionBean("USERDEFINED_RELATION_JOIN .HIERARCHY_NO",
				getHierarchyNo(customerHierarchyLevelDefinitionList, hierarchyLevelBean),
				GtnFrameworkOperatorType.LIKE);

	}

	public String getHierarchyNo(List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto) {
		StringBuilder custProdQuery = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		HierarchyLevelDefinitionBean previousHierarchyBean = HierarchyLevelDefinitionBean
				.getPreviousLinkedLevel(customerHierarchyLevelDefinitionList, selectedHierarchyLevelDto);
		int i;
		if (previousHierarchyBean == null)
			i = selectedHierarchyLevelDto.getLevelNo() - 1;
		else
			i = previousHierarchyBean.getLevelNo();
		for (; i < selectedHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = customerHierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				custProdQuery.append(", '%'");
				custProdQuery.append(",'.'");
				continue;
			}
			custProdQuery.append(",");
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			custProdQuery.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			custProdQuery.append(",'.'");
		}
		finalQuery.append(" concat(RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO");
		finalQuery.append(custProdQuery);
		finalQuery.append(")");
		return finalQuery.toString();
	}

	public String getHierarchyNoForRelationShip(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
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

}
