package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.custprod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
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
			HierarchyLevelDefinitionBean hierarchyLevelBean) {
		GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyLevelBean.getTableName(),
						hierarchyLevelBean.getFieldName());
		GtnFrameworkJoinClauseBean relationJoin = querygeneratorBean.addJoinClauseBean("RELATIONSHIP_LEVEL_DEFINITION",
				"RELATIONSHIP_LEVEL_DEFINITION", GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_Values",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.level_no", null,
				GtnFrameworkOperatorType.EQUAL_TO);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);

	}

}
