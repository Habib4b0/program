package com.stpl.gtn.gtn2o.hierarchyroutebuilder.service;

import java.util.List;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkRouteBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public interface GtnFrameworkHierarchyService {
	public GtnFrameworkRouteBean getRoutePath(int sourceEntityId, int destinationEntityId);

	public void getQueryByTableNameAndHierarchyTypeForMultiLevel(List<String> tableNameList, String hierarchyType,
			GtnFrameworkQueryGeneratorBean queryBean) throws GtnFrameworkGeneralException;

	public void getSelectColumnsForRelationShipBuilder(GtnFrameworkSelectColumnRelationBean keyBean,
			GtnFrameworkQueryGeneratorBean queryBean);

	public void addTableJoin(GtnFrameworkSelectColumnRelationBean keyBean, GtnFrameworkQueryGeneratorBean queryBean);

	public List<String> getMappingColumns(GtnFrameworkSelectColumnRelationBean keyBean);

	public void getWhereQuery(List<GtnFrameworkSelectColumnRelationBean> keyListBeanList,
			GtnFrameworkQueryGeneratorBean queryBean);

	public void getInboundRestrictionQueryForAutoUpdate(GtnFrameworkQueryGeneratorBean queryBaen);
}
