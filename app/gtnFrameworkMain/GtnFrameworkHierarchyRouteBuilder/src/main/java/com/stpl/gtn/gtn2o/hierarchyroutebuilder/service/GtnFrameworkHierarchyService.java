package com.stpl.gtn.gtn2o.hierarchyroutebuilder.service;

import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkRouteBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;

public interface GtnFrameworkHierarchyService {
	public GtnFrameworkRouteBean getRoutePath(int sourceEntityId, int destinationEntityId);

	public void createQuery(GtnFrameworkRouteBean routeBean, GtnFrameworkQueryGeneratorBean queryBean);

	public GtnFrameworkRouteBean getPathByTableNameAndHierarchyType(String sourceTableName, String destinationTableName,
			String hierarchyType);

	public void creatQueryForMultiLevelHierarchy(List<Integer> entityList,
			GtnFrameworkQueryGeneratorBean queryBean);

	public void getQueryByTableNameAndHierarchyTypeForMultiLevel(List<String> tableNameList,
			String hierarchyType, GtnFrameworkQueryGeneratorBean queryBean);

	public void getSelectColumnsForRelationShipBuilder(GtnFrameworkSingleColumnRelationBean keyBean,
			GtnFrameworkQueryGeneratorBean queryBean);

	public void addTableJoin(GtnFrameworkSingleColumnRelationBean keyBean, GtnFrameworkQueryGeneratorBean queryBean);

	public List<String> getMappingColumns(GtnFrameworkSingleColumnRelationBean keyBean);

	public void getWhereQuery(List<GtnFrameworkSingleColumnRelationBean> keyListBeanList,
			GtnFrameworkQueryGeneratorBean queryBean);

	public void getInboundRestrictionQuery(Set<String> tableNameSet, GtnFrameworkQueryGeneratorBean queryBaen);

	void getInboundRestrictionQueryForAutoUpdate(GtnFrameworkQueryGeneratorBean queryBaen);
}
