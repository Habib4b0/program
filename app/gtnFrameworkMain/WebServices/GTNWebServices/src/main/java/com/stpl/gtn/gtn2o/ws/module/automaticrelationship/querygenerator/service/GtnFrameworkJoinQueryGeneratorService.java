package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

public interface GtnFrameworkJoinQueryGeneratorService {

	void addJoinClause(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			HierarchyLevelDefinitionBean hierarchyLevelBean);

}
