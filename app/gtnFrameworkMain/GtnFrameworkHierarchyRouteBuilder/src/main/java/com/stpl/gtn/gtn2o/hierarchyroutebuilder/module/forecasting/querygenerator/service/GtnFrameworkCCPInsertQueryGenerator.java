package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.service;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

public interface GtnFrameworkCCPInsertQueryGenerator {
	public StringBuilder getCCPInsertQuery(List<GtnFrameworkRelationshipLevelDefintionBean> selectedRelationLevelList,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList, int relationVersionNo);
}
