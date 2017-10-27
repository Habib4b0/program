package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.dao.GtnFrameworkQueryEngineService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.dao.GtnFrameworkQueryEngineServiceImpl;

public class GtnFrameworkEntityMasterBean {

	private static GtnFrameworkEntityMasterBean entityMasterBean = new GtnFrameworkEntityMasterBean();

	private final List<GtnFrameworkEntityBean> entityList = new ArrayList<>();
	private final List<GtnFramworkTableRelationBean> entityRelationList = new ArrayList<>();
	private final List<GtnFramworkTableBean> entityTableList = new ArrayList<>();
	private final List<GtnFrameworkRouteBean> entityRouteList = new ArrayList<>();
	private final List<GtnFrameworkSingleColumnRelationBean> singleColumnRelationList = new ArrayList<>();
	private final List<GtnFrameworkEntityHierarchyRelationBean> entityHirarchyRelationList = new ArrayList<>();

	private GtnFrameworkEntityMasterBean() {
		initSetup();
	}

	public void initSetup() {
		insertToEntityRelationList();
		insertToEntityList();
		insertToTableList();
		insertToKeyRelationList();
		insertToHierarchyRelationList();
	}

	public static GtnFrameworkEntityMasterBean getInstance() {
		return entityMasterBean;
	}

	public List<GtnFrameworkEntityBean> getEntityList() {
		return Collections.unmodifiableList(entityList);
	}

	public void addEntityList(GtnFrameworkEntityBean entityItem) {
		entityList.add(entityItem);
	}

	public List<GtnFramworkTableBean> getEntityTableList() {
		return Collections.unmodifiableList(entityTableList);
	}

	public void addEntityTableList(GtnFramworkTableBean entityTableItem) {
		entityTableList.add(entityTableItem);
	}

	public List<GtnFramworkTableRelationBean> getEntityRelationList() {
		return Collections.unmodifiableList(entityRelationList);
	}

	public void addEntityRelationList(GtnFramworkTableRelationBean entityRelationItem) {
		entityRelationList.add(entityRelationItem);
	}

	public void addKeyRelationList(GtnFrameworkSingleColumnRelationBean keyRelationItem) {
		singleColumnRelationList.add(keyRelationItem);
	}

	public void addEntityHierarchyRelationList(GtnFrameworkEntityHierarchyRelationBean entityHierarchyRelationItem) {
		entityHirarchyRelationList.add(entityHierarchyRelationItem);
	}

	public List<GtnFrameworkRouteBean> getEntityRouteList() {
		return Collections.unmodifiableList(entityRouteList);
	}

	public void addEntityRouteList(GtnFrameworkRouteBean entityRouteItem) {
		entityRouteList.add(entityRouteItem);
	}

	public GtnFrameworkEntityBean getEntityBean(int sourceEntityId) {
		for (GtnFrameworkEntityBean gtnFrameworkEntityBean : entityList) {
			int tableId = gtnFrameworkEntityBean.getEntityMasterSid();
			if (tableId == sourceEntityId)
				return gtnFrameworkEntityBean;

		}
		return null;
	}

	public GtnFramworkTableBean getEntityBeanByTableName(String tableName) {
		for (GtnFramworkTableBean gtnFrameworkTableBean : entityTableList) {
			String tableId = gtnFrameworkTableBean.getTablename();
			if (tableId.equals(tableName))
				return gtnFrameworkTableBean;

		}
		return null;
	}

	public List<GtnFramworkTableRelationBean> getListOfLeftRelatedBeans(int tableId, int prevId) {
		List<GtnFramworkTableRelationBean> tableRelationList = new ArrayList<>();
		for (GtnFramworkTableRelationBean gtnFramworkEntityRelationBean : entityRelationList) {
			int rightKey = gtnFramworkEntityRelationBean.getRightTableMasterSid();
			int lefttKey = gtnFramworkEntityRelationBean.getLefTableMasterSid();
			if (lefttKey == tableId && rightKey != prevId)
				tableRelationList.add(gtnFramworkEntityRelationBean);
		}
		return tableRelationList;
	}

	public List<GtnFramworkTableRelationBean> getListOfRightRelatedBeans(int tableId, int prevId) {
		List<GtnFramworkTableRelationBean> tableRelationList = new ArrayList<>();
		for (GtnFramworkTableRelationBean gtnFramworkEntityRelationBean : entityRelationList) {
			int rightKey = gtnFramworkEntityRelationBean.getRightTableMasterSid();
			int lefttKey = gtnFramworkEntityRelationBean.getLefTableMasterSid();
			if (rightKey == tableId && lefttKey != prevId)
				tableRelationList.add(gtnFramworkEntityRelationBean);
		}
		return tableRelationList;
	}

	public GtnFramworkTableBean getTableBeanUsingTableId(int tableId) {
		for (GtnFramworkTableBean gtnFramworkTableMasterBean : entityTableList) {
			if (gtnFramworkTableMasterBean.getTableId() == tableId) {
				return gtnFramworkTableMasterBean;
			}
		}
		return null;
	}

	public GtnFramworkTableRelationBean getRelationBeanUsingTableId(int leftTableId, int rightTableId) {
		for (GtnFramworkTableRelationBean gtnFramworkEntityRelationBean : entityRelationList) {
			int rightKey = gtnFramworkEntityRelationBean.getRightTableMasterSid();
			int lefttKey = gtnFramworkEntityRelationBean.getLefTableMasterSid();
			if (lefttKey == leftTableId && rightKey == rightTableId) {
				return gtnFramworkEntityRelationBean;
			}
			if (lefttKey == rightTableId && rightKey == leftTableId) {
				return gtnFramworkEntityRelationBean;
			}
		}
		return null;
	}

	public List<GtnFramworkTableRelationBean> getListOfAllRelatedBeans(int sourceTableId) {
		List<GtnFramworkTableRelationBean> outputList = new ArrayList<>();

		for (GtnFramworkTableRelationBean currentTableRelationBean : entityRelationList) {

			if (currentTableRelationBean.getLefTableMasterSid() == sourceTableId) {
				outputList.add(currentTableRelationBean);
			}

			if (currentTableRelationBean.getRightTableMasterSid() == sourceTableId) {
				outputList.add(currentTableRelationBean);
			}

		}
		return outputList;
	}

	public GtnFrameworkSingleColumnRelationBean getKeyRelationBeanUsingTableIdAndColumnName(String tableName,
			String columnName) {
		for (GtnFrameworkSingleColumnRelationBean gtnFrameworkKeyListBean : singleColumnRelationList) {
			String actualTableName = gtnFrameworkKeyListBean.getActualTtableName();
			String actualColumnName = gtnFrameworkKeyListBean.getActualColumnName();
			if (actualTableName.equals(tableName) && actualColumnName.equals(columnName)) {
				return gtnFrameworkKeyListBean;
			}
		}
		return null;
	}

	private void insertToEntityRelationList() {
		String query = "SELECT	LEFT_TABLE_SID,	RIGHT_TABLE_SID,	LEFT_COLUMN_NAME,	RIGHT_COLUMN_NAME FROM	HIERARCHY_TABLE_RELATION";
		GtnFrameworkQueryEngineService service = new GtnFrameworkQueryEngineServiceImpl();
		@SuppressWarnings("unchecked")
		List<Object[]> results = service.executeSelectQuery(query);

		for (Object[] dbRelationBean : results) {
			GtnFramworkTableRelationBean relationBean = new GtnFramworkTableRelationBean();
			relationBean.setLefTableMasterSid(
					Integer.valueOf(dbRelationBean[0] == null ? "0" : dbRelationBean[0].toString()));

			relationBean.setRightTableMasterSid(
					Integer.valueOf(dbRelationBean[1] == null ? "0" : dbRelationBean[1].toString()));
			relationBean.setLeftColumnName(dbRelationBean[2] == null ? "" : dbRelationBean[2].toString());
			relationBean.setRightColumnName(dbRelationBean[3] == null ? "" : dbRelationBean[3].toString());
			addEntityRelationList(relationBean);

		}

	}

	private void insertToEntityList() {
		String query = "SELECT	ENTITY_ID,ENTITY_NAME,HIERARCHY_TABLE_MASTER_SID FROM	HIERARCHY_ENTITY_MASTER";
		GtnFrameworkQueryEngineService service = new GtnFrameworkQueryEngineServiceImpl();
		@SuppressWarnings("unchecked")
		List<Object[]> results = service.executeSelectQuery(query);

		for (Object[] dbEntityBean : results) {
			GtnFrameworkEntityBean entityBean = new GtnFrameworkEntityBean();
			entityBean.setEntityMasterSid(Integer.valueOf(dbEntityBean[0] == null ? "0" : dbEntityBean[0].toString()));
			entityBean.setEntityname(dbEntityBean[1] == null ? "" : dbEntityBean[1].toString());
			entityBean.setMasterTableId(Integer.valueOf(dbEntityBean[2] == null ? "0" : dbEntityBean[2].toString()));
			addEntityList(entityBean);
		}
	}

	private void insertToTableList() {
		String query = "SELECT	MASTER_TABLE_SID,	TABLE_NAME FROM	HIERARCHY_TABLE_MASTER";
		GtnFrameworkQueryEngineService service = new GtnFrameworkQueryEngineServiceImpl();
		@SuppressWarnings("unchecked")
		List<Object[]> results = service.executeSelectQuery(query);

		for (Object[] dbTableMasterBean : results) {
			GtnFramworkTableBean tableMasterBean = new GtnFramworkTableBean();
			tableMasterBean
					.setTableId(Integer.valueOf(dbTableMasterBean[0] == null ? "0" : dbTableMasterBean[0].toString()));
			tableMasterBean.setTablename(dbTableMasterBean[1] == null ? "" : dbTableMasterBean[1].toString());
			addEntityTableList(tableMasterBean);
		}

	}

	private void insertToKeyRelationList() {
		String query = "SELECT	COLUMN_RELATION_SID, ACTUAL_TABLE_NAME,	ACTUAL_COLUMN_NAME,	REFERENCE_TABLE_NAME,	MAPPING_COLUMN_NAME,	DESC_COLUMN_NAME,	PRIMARY_KEY_COLUMN_NAME FROM	HIERARCHY_SINGLE_COLUMN_RELATION ";
		GtnFrameworkQueryEngineService service = new GtnFrameworkQueryEngineServiceImpl();
		@SuppressWarnings("unchecked")
		List<Object[]> results = service.executeSelectQuery(query);

		for (Object[] dbSingleColumnRelationBean : results) {
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = new GtnFrameworkSingleColumnRelationBean();
			singleColumnRelationBean.setMastersid(Integer
					.valueOf(dbSingleColumnRelationBean[0] == null ? "0" : dbSingleColumnRelationBean[0].toString()));
			singleColumnRelationBean.setActualTtableName(
					dbSingleColumnRelationBean[1] == null ? "" : dbSingleColumnRelationBean[1].toString());
			singleColumnRelationBean.setActualColumnName(
					dbSingleColumnRelationBean[2] == null ? "" : dbSingleColumnRelationBean[2].toString());
			singleColumnRelationBean.setReferenceTableName(
					dbSingleColumnRelationBean[3] == null ? "" : dbSingleColumnRelationBean[3].toString());
			singleColumnRelationBean.setMappingColumnName(
					dbSingleColumnRelationBean[4] == null ? "" : dbSingleColumnRelationBean[4].toString());
			singleColumnRelationBean.setDescColumnName(
					dbSingleColumnRelationBean[5] == null ? "" : dbSingleColumnRelationBean[5].toString());
			singleColumnRelationBean.setPrimaryKeyColumnName(
					dbSingleColumnRelationBean[6] == null ? "" : dbSingleColumnRelationBean[6].toString());
			addKeyRelationList(singleColumnRelationBean);

		}
	}

	private void insertToHierarchyRelationList() {
		String query = "SELECT ENTITY_ID,	TABLE_NAME,	HIERARCHY_TYPE FROM	HIERARCHY_TYPE_TABLE_RELATION ";
		GtnFrameworkQueryEngineService service = new GtnFrameworkQueryEngineServiceImpl();
		@SuppressWarnings("unchecked")
		List<Object[]> results = service.executeSelectQuery(query);
		for (Object[] dbEntityBean : results) {
			GtnFrameworkEntityHierarchyRelationBean entityBean = new GtnFrameworkEntityHierarchyRelationBean();
			entityBean.setEntityId(Integer.valueOf(dbEntityBean[0] == null ? "0" : dbEntityBean[0].toString()));
			entityBean.setTableName(dbEntityBean[1] == null ? "" : dbEntityBean[1].toString());
			entityBean.setHierarchyType(dbEntityBean[2] == null ? "" : dbEntityBean[2].toString());
			addEntityHierarchyRelationList(entityBean);
		}

	}

	public GtnFrameworkEntityHierarchyRelationBean getEntityHirarchyRelationBean(String tableName,
			String hierarchyType) {
		for (GtnFrameworkEntityHierarchyRelationBean entityHirarchyRelationBean : entityHirarchyRelationList) {
			if (entityHirarchyRelationBean.getTableName().equalsIgnoreCase(tableName)
					&& entityHirarchyRelationBean.getHierarchyType().equalsIgnoreCase(hierarchyType)) {
				return entityHirarchyRelationBean;
			}
		}
		return null;
	}
}
