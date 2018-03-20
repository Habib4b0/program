package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.service;

import java.util.List;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

public interface GtnFrameworkLevelLoadQueryGenerator {
	public GtnFrameworkQueryGeneratorBean getAvailableTableLoadQuery(GtnForecastHierarchyInputBean inputBean,
			HierarchyLevelDefinitionBean lastLevelDto, List<HierarchyLevelDefinitionBean> hierarchyDefinitionList);
}
