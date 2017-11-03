package com.stpl.gtn.gtn2o.hierarchyroutebuilder.service;

import java.util.List;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;

public class GtnRelationShipRouteBuilderServiceImpl implements GtnRelationShipRouteBuilderService {
	public String getSelectColumnsForRelationShipBuilder(GtnFrameworkSingleColumnRelationBean keyBean) {

		GtnFrameworkHierarchyService hierarchyService = new GtnFrameworkHierarchyServiceImpl();
		List<String> mappingColumn = hierarchyService.getMappingColumns(keyBean);
		String query = "";
		if (!mappingColumn.isEmpty())
			query += "Select DISTINCT " + mappingColumn.get(0) + " , " + mappingColumn.get(1) + " FROM ";
		return query;
	}

}
