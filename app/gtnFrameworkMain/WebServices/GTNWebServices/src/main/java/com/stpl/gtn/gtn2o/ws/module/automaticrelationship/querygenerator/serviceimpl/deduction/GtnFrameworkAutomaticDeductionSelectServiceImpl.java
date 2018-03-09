package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.deduction;

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

@Component("DeductionSelect")
@Scope(value = "singleton")
public class GtnFrameworkAutomaticDeductionSelectServiceImpl implements GtnFrameworkSelectQueryGeneratorService {
	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	public GtnFrameworkAutomaticDeductionSelectServiceImpl() {
		super();
	}
	public void addSelectClause(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean, GtnFrameworkQueryGeneratorBean querygeneratorBean,
			int updatedVersionNo, int levelNo) {

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
		String parentNode = getParentNode(previousHierarchyLevelBean);
		String hierarchyNo = getHierarchyNo(relationBean, hierarchyLevelDefinitionList, levelNo);
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

	private String getHierarchyNo(GtnWsRelationshipBuilderBean relationBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList, int levelNo) {
		StringBuilder hierarcyNo = new StringBuilder();
		hierarcyNo.append("CONCAT(" + relationBean.getRelationshipBuilderSid() + ",'-'");
		for (int i = 0; i < levelNo + 1; i++) {
			GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyLevelDefinitionList.get(i).getTableName(),
							hierarchyLevelDefinitionList.get(i).getFieldName());
			hierarcyNo.append(",");
			hierarcyNo.append(keyBean.getActualTtableName());
			hierarcyNo.append(".");
			hierarcyNo.append(keyBean.getWhereClauseColumn());
			hierarcyNo.append(",'.'");
		}
		hierarcyNo.append(")");
		return hierarcyNo.toString();
	}

	private String getParentNode(HierarchyLevelDefinitionBean previousHierarchyLevelBean) {
		if (previousHierarchyLevelBean == null)
			return "''";
		StringBuilder parentNode = new StringBuilder();
		String tempString;
		parentNode.append("CONCAT(");
		parentNode.append(previousHierarchyLevelBean.getLevelNo());
		parentNode.append(",'~',");
		GtnFrameworkSingleColumnRelationBean previousKeyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(previousHierarchyLevelBean.getTableName(),
						previousHierarchyLevelBean.getFieldName());
		tempString = previousKeyBean.getActualTtableName() + "." + previousKeyBean.getWhereClauseColumn();
		parentNode.append(tempString);
		parentNode.append(")");
		return parentNode.toString();
	}
}
