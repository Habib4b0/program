package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.serviceimpl;

import java.util.List;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkJoinQueryGeneratorService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkSelectQueryGeneratorService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

public class GtnFrameworkQueryGeneraterServiceImpl {

	private GtnFrameworkSelectQueryGeneratorService selectQueryGenerator;
	private GtnFrameworkJoinQueryGeneratorService joinQueryGenerator;
	private GtnFrameworkWhereQueryGeneratorService whereQueryGenerator;

	public GtnFrameworkQueryGeneraterServiceImpl(GtnFrameworkSelectQueryGeneratorService selectQueryGenerator,
			GtnFrameworkJoinQueryGeneratorService joinQueryGenerator,
			GtnFrameworkWhereQueryGeneratorService whereQueryGenerator) {
		this.selectQueryGenerator = selectQueryGenerator;
		this.joinQueryGenerator = joinQueryGenerator;
		this.whereQueryGenerator = whereQueryGenerator;
	}

	public void generateQuery(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean, GtnFrameworkQueryGeneratorBean querygeneratorBean,
			int updatedVersionNo, int levelNo) throws GtnFrameworkGeneralException {
		selectQueryGenerator.addSelectClause(hierarchyLevelDefinitionList, relationBean, querygeneratorBean,
				updatedVersionNo, levelNo);
		joinQueryGenerator.addJoinClause(querygeneratorBean,
				hierarchyLevelDefinitionList, levelNo);
		whereQueryGenerator.addWhereClause(querygeneratorBean, relationBean);

	}

}
