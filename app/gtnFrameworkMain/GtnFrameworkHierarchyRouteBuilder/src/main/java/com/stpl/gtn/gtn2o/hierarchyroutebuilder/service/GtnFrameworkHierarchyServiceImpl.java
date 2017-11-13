package com.stpl.gtn.gtn2o.hierarchyroutebuilder.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityHierarchyRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkRouteBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFramworkTableBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFramworkTableRelationBean;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

public class GtnFrameworkHierarchyServiceImpl implements GtnFrameworkHierarchyService {

	@Autowired
	private GtnFrameworkEntityMasterBean entityMasterBean;

	private GtnFrameworkHierarchyServiceImpl() {

	}

	public GtnFrameworkEntityMasterBean getEntityMasterBean() {
		return entityMasterBean;
	}

	public void setEntityMasterBean(GtnFrameworkEntityMasterBean entityMasterBean) {
		this.entityMasterBean = entityMasterBean;
	}

	@Override
	public GtnFrameworkRouteBean getRoutePath(int sourceEntityId, int destinationEntityId) {

		final GtnFrameworkEntityBean sourceEntityBean = entityMasterBean.getEntityBean(sourceEntityId);
		GtnFrameworkEntityBean destinationEntityBean;
		final int sourceTableId = sourceEntityBean.getMasterTableId();
		int destinationTableId = 0;
		if (destinationEntityId != 0) {
			destinationEntityBean = entityMasterBean.getEntityBean(destinationEntityId);
			destinationTableId = destinationEntityBean.getMasterTableId();
		}
		final GtnFrameworkRouteBean routeBean = new GtnFrameworkRouteBean();
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
		final List<GtnFramworkTableRelationBean> leftSideTableIdList = entityMasterBean
				.getListOfLeftRelatedBeans(sourceTableId, prevId);

		for (final GtnFramworkTableRelationBean iterable_element : leftSideTableIdList) {
			if (destinationTableId == 0) {
				routeBean.addPathListValue(iterable_element.getRightTableMasterSid());
				continue;
			}
			final boolean isPath = getPath(destinationTableId, iterable_element.getRightTableMasterSid(),
					iterable_element.getLefTableMasterSid(), routeBean);
			if (isPath) {
				return true;
			}
			routeBean.removePathListLastValue();
		}

		final List<GtnFramworkTableRelationBean> rightSideTableIdList = entityMasterBean
				.getListOfRightRelatedBeans(sourceTableId, prevId);
		for (final GtnFramworkTableRelationBean iterable_element : rightSideTableIdList) {
			if (destinationTableId == 0) {
				routeBean.addPathListValue(iterable_element.getLefTableMasterSid());
				continue;
			}
			final boolean isPath = getPath(destinationTableId, iterable_element.getLefTableMasterSid(),
					iterable_element.getRightTableMasterSid(), routeBean);
			if (isPath) {
				return true;
			}
			routeBean.removePathListLastValue();
		}
		return false;
	}

	@Override
	public void createQuery(GtnFrameworkRouteBean routeBean, GtnFrameworkQueryGeneratorBean queryBean) {
		createQuery(routeBean, 0, queryBean);
	}

	private void createQuery(GtnFrameworkRouteBean routeBean, int startIndex,
			GtnFrameworkQueryGeneratorBean queryBean) {
		final List<Integer> pathList = routeBean.getPathList();
		if (!pathList.isEmpty()) {
			GtnFramworkTableBean leftTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(0));
			if (startIndex == 0) {
				final StringBuilder query = new StringBuilder();
				query.append(leftTableBean.getTablename());
				query.append(GtnFrameworkCommonConstants.AS_WITH_SPACE);
				query.append(leftTableBean.getTablename());
				queryBean.setFromTableNameWithAlies(query.toString());
			}
			GtnFramworkTableBean rightTableBean = null;
			for (int index = startIndex; index < pathList.size() - 1; index++) {
				if (routeBean.getRouteTo() == 0) {
					leftTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(0));
					rightTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(index + 1));
				} else {
					leftTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(index));
					rightTableBean = entityMasterBean.getTableBeanUsingTableId(pathList.get(index + 1));
				}
				buildQuery(leftTableBean, rightTableBean, queryBean);
			}
		}
	}

	private void buildQuery(GtnFramworkTableBean leftTableBean, GtnFramworkTableBean rightTableBean,
			GtnFrameworkQueryGeneratorBean queryBean) {
		if (leftTableBean != null && rightTableBean != null) {
			GtnFramworkTableRelationBean relationBean = entityMasterBean
					.getRelationBeanUsingTableId(leftTableBean.getTableId(), rightTableBean.getTableId());
			if (relationBean != null) {
				GtnFramworkTableBean tempLeftTableBean = entityMasterBean
						.getTableBeanUsingTableId(relationBean.getLefTableMasterSid());
				GtnFramworkTableBean tempLrightTableBean = entityMasterBean
						.getTableBeanUsingTableId(relationBean.getRightTableMasterSid());
				GtnFrameworkJoinClauseBean joinBean = queryBean.addJoinClauseBean(rightTableBean.getTablename(),
						rightTableBean.getTablename(), GtnFrameworkJoinType.JOIN);
				joinBean.addConditionBean(tempLeftTableBean.getTablename() + "." + relationBean.getLeftColumnName(),
						tempLrightTableBean.getTablename() + "." + relationBean.getRightColumnName(),
						GtnFrameworkOperatorType.EQUAL_TO);
			}
		}

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
		return query;
	}

	public StringBuilder getSingleJoinQuery(String rightTableName) {
		StringBuilder query = new StringBuilder();
		query.append(" JOIN ");
		query.append(rightTableName);
		query.append(GtnFrameworkCommonConstants.AS_WITH_SPACE);
		query.append(rightTableName);
		query.append(" ON ");
		return query;
	}

	public StringBuilder getMultiJoinQuery(String leftTableName, String rightTableName) {
		StringBuilder query = new StringBuilder();
		query.append(leftTableName);
		query.append(GtnFrameworkCommonConstants.AS_WITH_SPACE);
		query.append(leftTableName);
		query.append(getSingleJoinQuery(rightTableName));
		return query;
	}

	@Override
	public GtnFrameworkRouteBean getPathByTableNameAndHierarchyType(String sourceTableName, String destinationTableName,
			String hierarchyType) {

		final GtnFrameworkEntityHierarchyRelationBean sourceHierarchyBean = entityMasterBean
				.getEntityHirarchyRelationBean(sourceTableName, hierarchyType);
		final GtnFrameworkEntityHierarchyRelationBean destinationHierarchyBean = entityMasterBean
				.getEntityHirarchyRelationBean(destinationTableName, hierarchyType);
		return getRoutePath(sourceHierarchyBean.getEntityId(), destinationHierarchyBean.getEntityId());
	}

	@Override
	public void getQueryByTableNameAndHierarchyTypeForMultiLevel(List<String> tableNameList, String hierarchyType,
			GtnFrameworkQueryGeneratorBean queryBean) {

		final List<Integer> entityIdList = new ArrayList<>();
		for (final String tableName : tableNameList) {
			final GtnFrameworkEntityHierarchyRelationBean sourceHierarchyBean = entityMasterBean
					.getEntityHirarchyRelationBean(tableName, hierarchyType);
			entityIdList.add(sourceHierarchyBean.getEntityId());
		}
		creatQueryForMultiLevelHierarchy(entityIdList, queryBean);
	}

	@Override
	public void creatQueryForMultiLevelHierarchy(List<Integer> tableIdList, GtnFrameworkQueryGeneratorBean queryBean) {
		final Set<Integer> path = new HashSet<>();
		if (tableIdList.size() > 1) {
			final GtnFrameworkRouteBean routeBean = getRoutePath(tableIdList.get(0), tableIdList.get(1));
			path.addAll(routeBean.getPathList());
			createQuery(routeBean, queryBean);
		} else {
			final GtnFrameworkRouteBean routeBean = getRoutePath(tableIdList.get(0), 0);
			createQuery(routeBean, queryBean);
		}
		for (int i = 2; i < tableIdList.size(); i++) {
			final GtnFrameworkRouteBean routeBean = getRoutePath(tableIdList.get(i - 1), tableIdList.get(i));
			final int index = getDistinctPathValue(routeBean.getPathList(), path);
			if (index != -1) {
				path.addAll(routeBean.getPathList());
				createQuery(routeBean, index, queryBean);
			}
		}
	}

	private int getDistinctPathValue(List<Integer> currentPath, Set<Integer> initialPath) {
		final List<Integer> tempCurrnetPaht = new ArrayList<>(currentPath);
		tempCurrnetPaht.removeAll(initialPath);

		int index = -1;
		if (!tempCurrnetPaht.isEmpty()) {
			index = currentPath.indexOf(tempCurrnetPaht.get(0)) - 1;
		}

		return index;
	}

	@Override
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

	public void addTableJoin(GtnFrameworkSingleColumnRelationBean keyBean, GtnFrameworkQueryGeneratorBean queryBean) {
		if (keyBean.getMappingColumnName() != null && !keyBean.getMappingColumnName().isEmpty()) {
			GtnFrameworkJoinClauseBean joinBean = queryBean.addJoinClauseBean(keyBean.getReferenceTableName(),
					"HELPER_JOIN", GtnFrameworkJoinType.JOIN);
			joinBean.addConditionBean("HELPER_JOIN" + "." + keyBean.getMappingColumnName(),
					keyBean.getActualTtableName() + "." + keyBean.getActualColumnName(),
					GtnFrameworkOperatorType.EQUAL_TO);
		}
	}

	@Override
	public void getSelectColumnsForRelationShipBuilder(GtnFrameworkSingleColumnRelationBean keyBean,
			GtnFrameworkQueryGeneratorBean queryBean) {
		final List<String> mappingColumn = getMappingColumns(keyBean);
		if (!mappingColumn.isEmpty()) {
			queryBean.addSelectClauseBean(mappingColumn.get(0));
			queryBean.addSelectClauseBean(mappingColumn.get(1));
		}
	}

	@Override
	public void getWhereQuery(List<GtnFrameworkSingleColumnRelationBean> keyListBeanList,
			GtnFrameworkQueryGeneratorBean queryBean) {
		for (final GtnFrameworkSingleColumnRelationBean keyListBean : keyListBeanList) {
			queryBean.addWhereClauseBean(keyListBean.getActualTtableName() + "." + keyListBean.getWhereClauseColumn(),
					null, GtnFrameworkOperatorType.IN, GtnFrameworkDataType.NULL_ALLOWED, "?");
		}
	}

	@Override
	public void getInboundRestrictionQuery(Set<String> tableNameSet, GtnFrameworkQueryGeneratorBean queryBaen) {
		for (String tableName : tableNameSet) {
			GtnFramworkTableBean tableBean = entityMasterBean.getEntityBeanByTableName(tableName);
			queryBaen.addWhereClauseBean(tableBean.getTableNameAndInboudColumn().toString(), null,
					GtnFrameworkOperatorType.NOT_EQUAL_TO, GtnFrameworkDataType.STRING,
					tableBean.getInboundStatusValue());
		}
	}

}
