package com.stpl.gtn.gtn2o.hierarchyroutebuilder.service;

import java.util.List;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkRouteBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;

public interface GtnFrameworkHierarchyService {
	public GtnFrameworkRouteBean getRoutePath(int sourceEntityId, int destinationEntityId);

	public String createQuery(GtnFrameworkRouteBean routeBean);

	public GtnFrameworkRouteBean getPathByTableNameAndHierarchyType(String sourceTableName, String destinationTableName,
			String hierarchyType);

	public String creatQueryForMultiLevelHierarchy(List<Integer> entityList);

	public String getQueryByTableNameAndHierarchyTypeForMultiLevel(List<String> tableNameList, String hierarchyType);

	public List<String> getMappingColumns(GtnFrameworkSingleColumnRelationBean keyBean);

	public String addTableJoin(GtnFrameworkSingleColumnRelationBean keyBean);

}
