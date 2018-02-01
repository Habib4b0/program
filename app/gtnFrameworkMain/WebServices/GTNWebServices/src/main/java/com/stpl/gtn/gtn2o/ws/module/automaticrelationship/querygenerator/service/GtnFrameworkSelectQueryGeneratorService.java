package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service;

import java.util.List;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

public interface GtnFrameworkSelectQueryGeneratorService {

	void addSelectClause(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean, GtnFrameworkQueryGeneratorBean querygeneratorBean,
			int updatedVersionNo, int levelNo);

}
