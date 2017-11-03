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
					query.append(getMultiJoinQuery(leftTableBean.getTablename(), rightTableBean.getTablename()));
				} else {
					String rightTableName = rightTableBean.getTablename();
					query.append(getSingleJoinQuery(rightTableName));
				}
				query.append(getOnConditionQueryByTableId(relationBean.getLefTableMasterSid(),
						relationBean.getRightTableMasterSid(), relationBean.getLeftColumnName(),
						relationBean.getRightColumnName()));
			}
		}
		return query;
	}

	public StringBuilder getOnConditionQuery(String leftTableName, String rightTableName, String leftColumnName,
			String rightColumnName) {
		StringBuilder query = new StringBuilder();
		query.append(leftTableName);
		query.append(" . ");
		query.append(leftColumnName);
		query.append(" = ");
		query.append(rightTableName);
		query.append(" . ");
		query.append(rightColumnName);
		System.out.println("query-->>" + query);
		return query;
	}

	private StringBuilder getOnConditionQueryByTableId(int leftTableId, int rightTableId, String leftColumnName,
			String rightColumnName) {
		GtnFramworkTableBean leftTableBean = entityMasterBean.getTableBeanUsingTableId(leftTableId);
		GtnFramworkTableBean rightTableBean = entityMasterBean.getTableBeanUsingTableId(rightTableId);
		return getOnConditionQuery(leftTableBean.getTablename(), rightTableBean.getTablename(), leftColumnName,
				rightColumnName);
	}

	public StringBuilder getSingleJoinQuery(String rightTableName) {
		StringBuilder query = new StringBuilder();
		query.append(" JOIN ");
		query.append(rightTableName);
		query.append(" as ");
		query.append(rightTableName);
		query.append(" ON ");
		return query;
	}

	public StringBuilder getMultiJoinQuery(String leftTableName, String rightTableName) {
		StringBuilder query = new StringBuilder();
		query.append(leftTableName);
		query.append(" as ");
		query.append(leftTableName);
		query.append(getSingleJoinQuery(rightTableName));
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

	public List<String> getMappingColumns(GtnFrameworkSingleColumnRelationBean keyBean) {
		List<String> columnMappingList = new ArrayList<>();
		String mappingColumn = "";
		String mainColumn = "";
		String mainColumnName = "";
		if (keyBean.getDescColumnName() == null || keyBean.getDescColumnName().isEmpty()) {
			mainColumnName = keyBean.getPrimaryKeyColumnName();
			mainColumn = keyBean.getActualTtableName() + "." + keyBean.getActualColumnName();
			mappingColumn = keyBean.getActualTtableName() + "." + keyBean.getPrimaryKeyColumnName();
		} else {
			mainColumnName = keyBean.getActualColumnName();
			mainColumn = "HELPER_JOIN ." + keyBean.getDescColumnName();
			mappingColumn = "HELPER_JOIN ." + keyBean.getMappingColumnName();
		}
		columnMappingList.add(mainColumn);
		columnMappingList.add(mappingColumn);
		columnMappingList.add(mainColumnName);
		return columnMappingList;
	}

	public String addTableJoin(GtnFrameworkSingleColumnRelationBean keyBean) {
		if (keyBean.getMappingColumnName() != null && !keyBean.getMappingColumnName().isEmpty()) {
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
		return "";
	}

}
