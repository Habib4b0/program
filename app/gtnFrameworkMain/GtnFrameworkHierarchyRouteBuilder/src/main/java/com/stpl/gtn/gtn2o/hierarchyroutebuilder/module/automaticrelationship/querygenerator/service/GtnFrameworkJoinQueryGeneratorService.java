package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service;

import java.util.List;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

public interface GtnFrameworkJoinQueryGeneratorService {

	void addJoinClause(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList, int levelNo);

}
