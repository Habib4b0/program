package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.concurrency;

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
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFramworkDeductionCheckForAutoUpdateQuery {

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;

	public GtnFrameworkQueryGeneratorBean getCheckForUpdateQuery(HierarchyLevelDefinitionBean hierarchyLevelBean,
			int relationShipBuilderSid, List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		GtnFrameworkFileReadWriteService fileServiceDeduction = new GtnFrameworkFileReadWriteService();
		GtnFrameworkHierarchyQueryBean hierarchyQuery = fileServiceDeduction.getQueryFromFile(
				hierarchyLevelBean.getHierarchyDefinitionSid(), hierarchyLevelBean.getHierarchyLevelDefinitionSid(),
				hierarchyLevelBean.getVersionNo());
		GtnFrameworkQueryGeneratorBean queryGenerartorBeanDeduction = hierarchyQuery.getQuery();
		String hierarchyNoSelectClause = getHierarchyNo(hierarchyLevelDefinitionList, hierarchyLevelBean,
				relationShipBuilderSid).toString();
		addJoinClause(queryGenerartorBeanDeduction);
		queryGenerartorBeanDeduction.addSelectClauseBean(null, "HIERARCHY_NO", false, hierarchyNoSelectClause);
		queryGenerartorBeanDeduction.removeWhereClauseConfigListByIndex(1,
				queryGenerartorBeanDeduction.getWhereClauseConfigList().size() - 1);
		gtnHierarchyServiceBuilder.getInboundRestrictionQueryForAutoUpdate(queryGenerartorBeanDeduction);
		queryGenerartorBeanDeduction.addWhereClauseBean("RS_CONTRACT_DETAILS.ITEM_MASTER_SID", null,
				GtnFrameworkOperatorType.IN, GtnFrameworkDataType.NULL_ALLOWED, null);
		return queryGenerartorBeanDeduction;
	}

	private void addJoinClause(GtnFrameworkQueryGeneratorBean queryGenerartorBean) {
		GtnFrameworkJoinClauseBean rsDetailsJoin = queryGenerartorBean.addJoinClauseBean("RS_CONTRACT_DETAILS",
				"RS_CONTRACT_DETAILS", GtnFrameworkJoinType.JOIN);
		rsDetailsJoin.addConditionBean("RS_CONTRACT.RS_CONTRACT_SID", "RS_CONTRACT_DETAILS.RS_CONTRACT_SID",
				GtnFrameworkOperatorType.EQUAL_TO);
	}

	private StringBuilder getHierarchyNo(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
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

}
