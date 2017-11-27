package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.custprod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkSelectQueryGeneratorService;
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

	private String getHierarchyNo(HierarchyLevelDefinitionBean hierarchyLevelBean) {
		StringBuilder hierarcyNo = new StringBuilder();
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyLevelBean.getTableName(),
						hierarchyLevelBean.getFieldName());
		hierarcyNo.append("CONCAT( RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO,");
		hierarcyNo.append(keyBean.getActualTtableName());
		hierarcyNo.append(".");
		hierarcyNo.append(keyBean.getWhereClauseColumn());
		hierarcyNo.append(",'.')");
		return hierarcyNo.toString();
	}

	private String getParentNodeForCustomerAndProdHie(HierarchyLevelDefinitionBean previousHierarchyLevelBean) {
		if (previousHierarchyLevelBean == null)
			return "''";
		StringBuilder parentNodeString = new StringBuilder();
		String tempString;
		parentNodeString.append("CONCAT(");
		parentNodeString.append(previousHierarchyLevelBean.getLevelNo());
		parentNodeString.append(",'~',");
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
			int updatedVersionNo, String userId, int levelNo) {
		HierarchyLevelDefinitionBean hierarchyLevelBean = hierarchyLevelDefinitionList.get(levelNo);
		HierarchyLevelDefinitionBean previousHierarchyLevelBean = levelNo > 0
				? hierarchyLevelDefinitionList.get(levelNo - 1) : null;
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
		String hierarchyNo = getHierarchyNo(hierarchyLevelBean);
		querygeneratorBean.addSelectClauseBean(null, "PARENT_NODE", Boolean.FALSE, parentNode);
		querygeneratorBean.addSelectClauseBean(null, "HIERARCHY_NO", Boolean.FALSE, hierarchyNo);
		querygeneratorBean.addSelectClauseBean(null, "FLAG", Boolean.FALSE, "'F'");
		querygeneratorBean.addSelectClauseBean(null, "CREATED_BY", Boolean.FALSE, userId);
		querygeneratorBean.addSelectClauseBean(null, "CREATED_DATE", Boolean.FALSE, "getdate()");
		querygeneratorBean.addSelectClauseBean(null, "MODIFIED_BY", Boolean.FALSE, userId);
		querygeneratorBean.addSelectClauseBean(null, "MODIFIED_DATE", Boolean.FALSE, "getdate()");
		querygeneratorBean.addSelectClauseBean(null, "VERSION_NO", Boolean.FALSE, String.valueOf(updatedVersionNo));
		querygeneratorBean.addSelectClauseBean(null, "PARENT_HIERARCHY_NO", Boolean.FALSE, hierarchyNo);

	}
}
