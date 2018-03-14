package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.serviceimpl.custprod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkSelectQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Component("CustProdSelect")
@Scope(value = "singleton")
public class GtnFramewrokAutomaticCustProdSelectServiceImpl implements GtnFrameworkSelectQueryGeneratorService {
	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	public GtnFramewrokAutomaticCustProdSelectServiceImpl() {
		super();
	}


	private String getParentNodeForCustomerAndProdHie(HierarchyLevelDefinitionBean previousHierarchyLevelBean) {
		if (previousHierarchyLevelBean == null)
			return "''";
		StringBuilder parentNodeString = new StringBuilder();
		String tempString;
		parentNodeString.append("CONCAT(");
		parentNodeString.append(previousHierarchyLevelBean.getLevelNo());
		parentNodeString.append(",'~',");
		if (previousHierarchyLevelBean.isUserDefined()) {
			parentNodeString.append(previousHierarchyLevelBean.getDefaultVlaue());
			parentNodeString.append(")");
			return parentNodeString.toString();
		}
		GtnFrameworkSingleColumnRelationBean previousKeyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(previousHierarchyLevelBean.getTableName(),
						previousHierarchyLevelBean.getFieldName());
		tempString = previousKeyBean.getActualTtableName() + "." + previousKeyBean.getWhereClauseColumn();
		parentNodeString.append(tempString);
		parentNodeString.append(")");
		return parentNodeString.toString();
	}

	@Override
	public void addSelectClause(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean, GtnFrameworkQueryGeneratorBean querygeneratorBean,
			int updatedVersionNo, int levelNo) {
		HierarchyLevelDefinitionBean hierarchyLevelBean = hierarchyLevelDefinitionList.get(levelNo);
		HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
				.getPreviousLinkedLevel(hierarchyLevelDefinitionList, hierarchyLevelBean);
		querygeneratorBean.removeSelectClauseByIndex(0);
		querygeneratorBean.addSelectClauseBean(null, "RELATIONSHIP_BUILDER_SID", Boolean.FALSE,
				String.valueOf(relationBean.getRelationshipBuilderSid()));
		querygeneratorBean.addSelectClauseBean(null, "HIERARCHY_LEVEL_DEFINITION_SID", Boolean.FALSE,
				String.valueOf(hierarchyLevelBean.getHierarchyLevelDefinitionSid()));

		querygeneratorBean.addSelectClauseBean(null, "LEVEL_NO", Boolean.FALSE,
				String.valueOf(hierarchyLevelBean.getLevelNo()));
		querygeneratorBean.addSelectClauseBean(null, "LEVEL_NAME", Boolean.FALSE,
				"'" + hierarchyLevelBean.getLevelName() + "'");
		String parentNode = getParentNodeForCustomerAndProdHie(previousHierarchyLevelBean);
		String hierarchyNo = getHierarchyNo(hierarchyLevelDefinitionList, hierarchyLevelBean);
		querygeneratorBean.addSelectClauseBean(null, "PARENT_NODE", Boolean.FALSE, parentNode);
		querygeneratorBean.addSelectClauseBean(null, "HIERARCHY_NO", Boolean.FALSE, hierarchyNo);
		querygeneratorBean.addSelectClauseBean(null, "FLAG", Boolean.FALSE, "'F'");
		querygeneratorBean.addSelectClauseBean(null, "CREATED_BY", Boolean.FALSE,
				String.valueOf(relationBean.getCreatedBy()));
		querygeneratorBean.addSelectClauseBean(null, "CREATED_DATE", Boolean.FALSE, "getdate()");
		querygeneratorBean.addSelectClauseBean(null, "MODIFIED_BY", Boolean.FALSE,
				String.valueOf(relationBean.getModifiedBy()));
		querygeneratorBean.addSelectClauseBean(null, "MODIFIED_DATE", Boolean.FALSE, "getdate()");
		querygeneratorBean.addSelectClauseBean(null, "VERSION_NO", Boolean.FALSE, String.valueOf(updatedVersionNo));
		querygeneratorBean.addSelectClauseBean(null, "PARENT_HIERARCHY_NO", Boolean.FALSE, hierarchyNo);
		querygeneratorBean.addSelectClauseBean(null, "NEETTOINSERT", Boolean.FALSE, "1");

	}

	private String getHierarchyNo(List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedCustomerHierarchyLevelDto) {
		StringBuilder query = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		HierarchyLevelDefinitionBean previousHierarchyBean = HierarchyLevelDefinitionBean
				.getPreviousLinkedLevel(customerHierarchyLevelDefinitionList, selectedCustomerHierarchyLevelDto);
		int i;
		if (previousHierarchyBean == null)
			i = selectedCustomerHierarchyLevelDto.getLevelNo() - 1;
		else
			i = previousHierarchyBean.getLevelNo();
		for (; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = customerHierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				query.append("," + leveldto.getDefaultVlaue());
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
		finalQuery.append("isnull(\r\n" + "				USERDEFINED_RELATION_JOIN.HIERARCHY_NO,\r\n"
				+ "				concat(RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO");
		finalQuery.append(query);
		finalQuery.append("))");
		return finalQuery.toString();
	}

}
