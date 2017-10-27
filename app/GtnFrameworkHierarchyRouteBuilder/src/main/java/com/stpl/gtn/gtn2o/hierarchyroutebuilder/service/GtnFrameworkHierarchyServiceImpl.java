package com.stpl.gtn.gtn2o.hierarchyroutebuilder.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityHierarchyRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkRouteBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFramworkTableBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFramworkTableRelationBean;

public class GtnFrameworkHierarchyServiceImpl implements GtnFrameworkHierarchyService {

	private GtnFrameworkEntityMasterBean entityMasterBean = GtnFrameworkEntityMasterBean.getInstance();

	public GtnFrameworkRouteBean getRoutePath(int sourceEntityId, int destinationEntityId) {

		GtnFrameworkEntityBean sourceEntityBean = entityMasterBean.getEntityBean(sourceEntityId);
		GtnFrameworkEntityBean destinationEntityBean;
		int sourceTableId = sourceEntityBean.getMasterTableId();
		int destinationTableId = 0;
		if (destinationEntityId != 0) {
			destinationEntityBean = entityMasterBean.getEntityBean(destinationEntityId);
			destinationTableId = destinationEntityBean.getMasterTableId();
		}
		GtnFrameworkRouteBean routeBean = new GtnFrameworkRouteBean();
		routeBean.setRouteFrom(sourceEntityId);
		routeBean.setRouteTo(destinationEntityId);

		getPath(destinationTableId, sourceTableId, 0, routeBean);
		return routeBean;
	}

	private boolean getPath(int destinationTableId, int sourceTableId, int prevId, GtnFrameworkRouteBean routeBean) {
		routeBean.addPathListValue(sourceTableId);
		if (sourceTableId == destinationTableId) {
			return true;
		}
		List<GtnFramworkTableRelationBean> leftSideTableIdList = entityMasterBean
				.getListOfLeftRelatedBeans(sourceTableId, prevId);

		for (GtnFramworkTableRelationBean iterable_element : leftSideTableIdList) {
			if (destinationTableId == 0) {
				routeBean.addPathListValue(iterable_element.getRightTableMasterSid());
				continue;
			}
			boolean isPath = getPath(destinationTableId, iterable_element.getRightTableMasterSid(),
					iterable_element.getLefTableMasterSid(), routeBean);
			if (isPath) {
				return true;
			}
			routeBean.removePathListLastValue();
		}

		List<GtnFramworkTableRelationBean> rightSideTableIdList = entityMasterBean
				.getListOfRightRelatedBeans(sourceTableId, prevId);
		for (GtnFramworkTableRelationBean iterable_element : rightSideTableIdList) {
			if (destinationTableId == 0) {
				routeBean.addPathListValue(iterable_element.getLefTableMasterSid());
				continue;
			}
			boolean isPath = getPath(destinationTableId, iterable_element.getLefTableMasterSid(),
					iterable_element.getRightTableMasterSid(), routeBean);
			if (isPath) {
				return true;
			}
			routeBean.removePathListLastValue();
		}
		return false;
	}

	private boolean getPath1(int sourceTableId, int destinationTableId, GtnFrameworkRouteBean routeBean) {

		List<GtnFramworkTableRelationBean> relatedBeanList = entityMasterBean.getListOfAllRelatedBeans(sourceTableId);

		if (relatedBeanList == null) {
			return false;
		}

		if (destinationTableId == 0) {
			for (GtnFramworkTableRelationBean currentBean : relatedBeanList) {
				routeBean.addPathListValue(currentBean.getLefTableMasterSid());
				routeBean.addPathListValue(currentBean.getRightTableMasterSid());
			}
			return true;
		}

		for (GtnFramworkTableRelationBean currentBean : relatedBeanList) {

			if ((currentBean.getLefTableMasterSid() == sourceTableId)
					&& (currentBean.getRightTableMasterSid() == destinationTableId)) {
				routeBean.addPathListValue(currentBean.getLefTableMasterSid());
				routeBean.addPathListValue(currentBean.getRightTableMasterSid());
				return true;

			}

			if ((currentBean.getLefTableMasterSid() == destinationTableId)
					&& (currentBean.getRightTableMasterSid() == sourceTableId)) {
				routeBean.addPathListValue(currentBean.getLefTableMasterSid());
				routeBean.addPathListValue(currentBean.getRightTableMasterSid());
				return true;
			}

		}

		for (GtnFramworkTableRelationBean currentBean : relatedBeanList) {

			if (currentBean.getLefTableMasterSid() == sourceTableId) {
				getPath1(currentBean.getRightTableMasterSid(), destinationTableId, routeBean);
			}

			if (currentBean.getRightTableMasterSid() == sourceTableId) {
				getPath1(currentBean.getLefTableMasterSid(), destinationTableId, routeBean);
			}
		}

		return false;
	}

	public String createQuery(GtnFrameworkRouteBean routeBean) {
		return createQuery(routeBean, 0, true);
	}

	private String createQuery(GtnFrameworkRouteBean routeBean, int startIndex, boolean isMultiLevel) {
		StringBuilder query = new StringBuilder();
		List<Integer> pathList = routeBean.getPathList();
		if (pathList.size() == 1) {
			GtnFramworkTableBean leftTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(0));
			query.append(leftTableBean.getTablename());
			query.append(" as ");
			query.append(leftTableBean.getTablename());
			return query.toString();
		}
		for (int index = startIndex; index < pathList.size() - 1; index++) {
			GtnFramworkTableBean leftTableBean;
			GtnFramworkTableBean rightTableBean;
			if (routeBean.getRouteTo() == 0) {
				leftTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(0));
				rightTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(index + 1));
			} else {
				leftTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(index));
				rightTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(index + 1));
			}

			StringBuilder queryPart = buildQuery(leftTableBean, rightTableBean, index, isMultiLevel);
			query.append(queryPart);

		}

		return query.toString();
	}

	private StringBuilder buildQuery(GtnFramworkTableBean leftTableBean, GtnFramworkTableBean rightTableBean, int index,
			boolean isMultiLevel) {
		StringBuilder query = new StringBuilder();
		if (leftTableBean != null && rightTableBean != null) {
			GtnFramworkTableRelationBean relationBean = entityMasterBean
					.getRelationBeanUsingTableId(leftTableBean.getTableId(), rightTableBean.getTableId());
			if (relationBean != null) {
				if (index == 0 && isMultiLevel) {
					query.append(leftTableBean.getTablename());
					query.append(" as ");
					query.append(leftTableBean.getTablename());
					query.append(" JOIN ");
					query.append(rightTableBean.getTablename());
					query.append(" as ");
					query.append(rightTableBean.getTablename());
					query.append(" ON ");
				} else {
					query.append(" JOIN ");
					query.append(rightTableBean.getTablename());
					query.append(" as ");
					query.append(rightTableBean.getTablename());
					query.append(" ON ");
				}
				query.append(leftTableBean.getTablename());
				query.append(" . ");
				query.append(relationBean.getLeftColumnName());
				query.append(" = ");
				query.append(rightTableBean.getTablename());
				query.append(" . ");
				query.append(relationBean.getRightColumnName());
			}
		}
		return query;
	}

	@Override
	public GtnFrameworkRouteBean getPathByTableNameAndHierarchyType(String sourceTableName, String destinationTableName,
			String hierarchyType) {

		GtnFrameworkEntityHierarchyRelationBean sourceHierarchyBean = entityMasterBean
				.getEntityHirarchyRelationBean(sourceTableName, hierarchyType);
		GtnFrameworkEntityHierarchyRelationBean destinationHierarchyBean = entityMasterBean
				.getEntityHirarchyRelationBean(destinationTableName, hierarchyType);
		return getRoutePath(sourceHierarchyBean.getEntityId(), destinationHierarchyBean.getEntityId());
	}

	@Override
	public String getQueryByTableNameAndHierarchyTypeForMultiLevel(List<String> tableNameList, String hierarchyType) {

		List<Integer> entityIdList = new ArrayList<>();
		for (String tableName : tableNameList) {
			GtnFrameworkEntityHierarchyRelationBean sourceHierarchyBean = entityMasterBean
					.getEntityHirarchyRelationBean(tableName, hierarchyType);
			entityIdList.add(sourceHierarchyBean.getEntityId());
		}
		return creatQueryForMultiLevelHierarchy(entityIdList);
	}

	public String creatQueryForMultiLevelHierarchy(List<Integer> tableIdList) {

		StringBuilder query = new StringBuilder();
		Set<Integer> path = new HashSet<>();
		if (tableIdList.size() > 1) {
			GtnFrameworkRouteBean routeBean = getRoutePath(tableIdList.get(0), tableIdList.get(1));
			path.addAll(routeBean.getPathList());
			query.append(createQuery(routeBean));
		} else {
			GtnFrameworkRouteBean routeBean = getRoutePath(tableIdList.get(0), 0);
			query.append(createQuery(routeBean));
		}
		for (int i = 2; i < tableIdList.size(); i++) {
			GtnFrameworkRouteBean routeBean = getRoutePath(tableIdList.get(i - 1), tableIdList.get(i));
			int index = getDistinctPathValue(routeBean.getPathList(), path);
			if (index != -1) {
				path.addAll(routeBean.getPathList());
				query.append(createQuery(routeBean, index, false));
			}
		}
		return query.toString();
	}

	private int getDistinctPathValue(List<Integer> currentPath, Set<Integer> initialPath) {
		List<Integer> tempCurrnetPaht = new ArrayList<>(currentPath);
		tempCurrnetPaht.removeAll(initialPath);

		int index = -1;
		if (!tempCurrnetPaht.isEmpty()) {
			index = currentPath.indexOf(tempCurrnetPaht.get(0)) - 1;
		}

		return index;
	}

	public String getSelectColumnsForRelationShipBuilder(GtnFrameworkSingleColumnRelationBean keyBean) {
		String query = "";
		String mappingColumn = "";
		String mainColumn = "";
		if (keyBean.getDescColumnName() == null || keyBean.getDescColumnName().isEmpty()) {
			mainColumn = keyBean.getActualTtableName() + "." + keyBean.getActualColumnName();
			mappingColumn = keyBean.getActualTtableName() + "." + keyBean.getPrimaryKeyColumnName();
		} else {
			mainColumn = "HELPER_JOIN ." + keyBean.getDescColumnName();
			mappingColumn = "HELPER_JOIN ." + keyBean.getMappingColumnName();
		}
		query += "Select DISTINCT " + mainColumn + " , " + mappingColumn + " FROM ";
		return query;
	}

	public String addTableJoin(GtnFrameworkSingleColumnRelationBean keyBean) {
		StringBuilder tempQuery = new StringBuilder();
		tempQuery.append(" JOIN ");
		tempQuery.append(keyBean.getReferenceTableName());
		tempQuery.append(" as HELPER_JOIN on HELPER_JOIN.");
		tempQuery.append(keyBean.getMappingColumnName());
		tempQuery.append(" = ");
		tempQuery.append(keyBean.getActualTtableName());
		tempQuery.append(".");
		tempQuery.append(keyBean.getActualColumnName());
		return tempQuery.toString();
	}

}
