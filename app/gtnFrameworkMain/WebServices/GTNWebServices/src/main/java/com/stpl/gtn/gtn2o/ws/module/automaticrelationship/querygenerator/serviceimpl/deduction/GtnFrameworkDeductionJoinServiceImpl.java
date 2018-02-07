package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.deduction;

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

@Component("DeductionJoin")
@Scope(value = "singleton")
public class GtnFrameworkDeductionJoinServiceImpl implements GtnFrameworkJoinQueryGeneratorService {

	public GtnFrameworkDeductionJoinServiceImpl() {
		super();
	}

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Override
	public void addJoinClause(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList, int levelNo) {
		HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean = hierarchyLevelDefinitionList.get(levelNo);
		GtnFrameworkJoinClauseBean rsDetailsJoin = querygeneratorBean.addJoinClauseBean("RS_CONTRACT_DETAILS",
				"RS_CONTRACT_DETAILS", GtnFrameworkJoinType.JOIN);
		rsDetailsJoin.addConditionBean("RS_CONTRACT.RS_CONTRACT_SID", "RS_CONTRACT_DETAILS.RS_CONTRACT_SID",
				GtnFrameworkOperatorType.EQUAL_TO);

		String relationshipDef = "RELATIONSHIP_LEVEL_DEFINITION";
		GtnFrameworkJoinClauseBean relationshipJoin = querygeneratorBean.addJoinClauseBean(relationshipDef,
				relationshipDef, GtnFrameworkJoinType.JOIN);
		relationshipJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", null,
				GtnFrameworkOperatorType.EQUAL_TO);

		GtnFrameworkJoinClauseBean relationshipDateJoin = querygeneratorBean.addJoinClauseBean(relationshipDef,
				"RELATIONSHIP_LEVEL_DEFINITION2", GtnFrameworkJoinType.JOIN);
		relationshipDateJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION2.RELATIONSHIP_BUILDER_SID",
				"RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", GtnFrameworkOperatorType.EQUAL_TO);
		relationshipDateJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION2.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationshipDateJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION2.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		for (GtnFrameworkJoinClauseBean joinClauseBean : querygeneratorBean.getJoinClauseConfigList()) {
			GtnFramworkTableBean tableBean = gtnFrameworkEntityMasterBean
					.getEntityBeanByTableName(querygeneratorBean.getFromTableName());
			if (!joinClauseBean.getJoinTableName().equals("UDCS")) {
				if (tableBean == null || tableBean.getModifiedDateColumn() == null)
					continue;
				relationshipDateJoin.addOrConditionBean(
						tableBean.getModifiedDateColumn(joinClauseBean.getJoinTableAliesName()),
						"RELATIONSHIP_LEVEL_DEFINITION.MODIFIED_DATE", GtnFrameworkOperatorType.GREATERTHANOREQUALTO);
			}
		}

		GtnFramworkTableBean tableBean = gtnFrameworkEntityMasterBean
				.getEntityBeanByTableName(querygeneratorBean.getFromTableName());
		if (!querygeneratorBean.getFromTableName().equals("UDCS")) {
			if (tableBean.getModifiedDateColumn() == null)
				return;
			relationshipDateJoin.addOrConditionBean(
					tableBean.getModifiedDateColumn(querygeneratorBean.getFromTableAlies()),
					"RELATIONSHIP_LEVEL_DEFINITION.MODIFIED_DATE", GtnFrameworkOperatorType.GREATERTHANOREQUALTO);
			relationshipDateJoin.addOrConditionBean("RELATIONSHIP_LEVEL_DEFINITION2.HIERARCHY_NO",
					getHierarchyNo(hierarchyLevelDefinitionList, hierarchyLevelDefinitionBean),
					GtnFrameworkOperatorType.LIKE);
		}
	}

	private String getHierarchyNo(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean) {
		StringBuilder query = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < hierarchyLevelDefinitionBean.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean levelDto = hierarchyLevelDefinitionList.get(i);
			if (levelDto.getTableName().isEmpty()) {
				query.append(",'%'");
				query.append(",'.'");
				continue;
			}
			query.append(",");
			GtnFrameworkSingleColumnRelationBean gtnFrameworkSingleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(levelDto.getTableName(), levelDto.getFieldName());
			query.append(gtnFrameworkSingleColumnRelationBean.getActualTtableName() + "."
					+ gtnFrameworkSingleColumnRelationBean.getWhereClauseColumn());
			query.append(",'.'");
		}
		finalQuery.append("concat( RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID,'-'");
		finalQuery.append(query);
		query.append(",'%'");
		finalQuery.append(")");
		return finalQuery.toString();
	}

}
