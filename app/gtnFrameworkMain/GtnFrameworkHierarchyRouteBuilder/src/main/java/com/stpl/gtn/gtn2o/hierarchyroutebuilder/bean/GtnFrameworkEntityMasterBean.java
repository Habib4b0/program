package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchyEntityMaster;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchySingleColumnRelation;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchyTableMaster;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchyTableRelation;
import com.stpl.gtn.gtn2o.ws.entity.hierarchyroutebuilder.HierarchyTypeTableRelation;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Service
public class GtnFrameworkEntityMasterBean {

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private final List<GtnFrameworkEntityBean> entityList = new ArrayList<>();
	private final List<GtnFramworkTableRelationBean> entityRelationList = new ArrayList<>();
	private final List<GtnFramworkTableBean> entityTableList = new ArrayList<>();
	private final List<GtnFrameworkRouteBean> entityRouteList = new ArrayList<>();
	private final List<GtnFrameworkSingleColumnRelationBean> singleColumnRelationList = new ArrayList<>();
	private final List<GtnFrameworkEntityHierarchyRelationBean> entityHirarchyRelationList = new ArrayList<>();
	private final List<GtnFrameworkHierarchyRestrictionBean> hierachyRestrcionList = new ArrayList<>();

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkEntityMasterBean.class);

	public GtnFrameworkEntityMasterBean() throws GtnFrameworkGeneralException {
		super();
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public void initSetup() {
		insertToEntityRelationList();
		insertToEntityList();
		insertToTableList();
		insertToKeyRelationList();
		insertToHierarchyRelationList();
		insertToHierarchyRestrictionList();
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

	public void addRestrictionBeanList(GtnFrameworkHierarchyRestrictionBean entityRouteItem) {
		hierachyRestrcionList.add(entityRouteItem);
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
		if (outputList.isEmpty()) {
			return null;
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

	@SuppressWarnings("unchecked")
	private void insertToEntityRelationList() {

		SessionFactory sessionFactory = gtnSqlQueryEngine.getSessionFactory();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(HierarchyTableRelation.class);
		List<HierarchyTableRelation> results = criteria.list();

		for (HierarchyTableRelation dbRelationBean : results) {
			GtnFramworkTableRelationBean relationBean = new GtnFramworkTableRelationBean();
			relationBean
					.setLefTableMasterSid(dbRelationBean.getHierarchyTableMasterByLeftTableSid().getMasterTableSid());
			relationBean.setRightTableMasterSid(
					dbRelationBean.getHierarchyTableMasterByRightTableSid().getMasterTableSid());
			relationBean.setLeftColumnName(dbRelationBean.getLeftColumnName());
			relationBean.setRightColumnName(dbRelationBean.getRightColumnName());
			addEntityRelationList(relationBean);

		}

	}

	private void insertToEntityList() {

		Session session = gtnSqlQueryEngine.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(HierarchyEntityMaster.class);
		@SuppressWarnings("unchecked")
		List<HierarchyEntityMaster> results = criteria.list();

		for (HierarchyEntityMaster dbEntityBean : results) {
			GtnFrameworkEntityBean entityBean = new GtnFrameworkEntityBean();
			entityBean.setEntityMasterSid(dbEntityBean.getEntityId());
			entityBean.setEntityname(dbEntityBean.getEntityName());
			entityBean.setMasterTableId(dbEntityBean.getHierarchyTableMasterSid());
			addEntityList(entityBean);
		}
	}

	private void insertToTableList() {

		Session session = gtnSqlQueryEngine.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(HierarchyTableMaster.class);
		@SuppressWarnings("unchecked")
		List<HierarchyTableMaster> results = criteria.list();

		for (HierarchyTableMaster dbTableMasterBean : results) {
			GtnFramworkTableBean tableMasterBean = new GtnFramworkTableBean();
			tableMasterBean.setTableId(dbTableMasterBean.getMasterTableSid());
			tableMasterBean.setTablename(dbTableMasterBean.getTableName());
			tableMasterBean.setInboundStatusColumn(dbTableMasterBean.getColumnName());
			tableMasterBean.setInboundStatusValue(dbTableMasterBean.getValue());
			addEntityTableList(tableMasterBean);
		}

	}

	private void insertToKeyRelationList() {
		Session session = gtnSqlQueryEngine.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(HierarchySingleColumnRelation.class);
		@SuppressWarnings("unchecked")
		List<HierarchySingleColumnRelation> results = criteria.list();

		for (HierarchySingleColumnRelation dbSingleColumnRelationBean : results) {
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = new GtnFrameworkSingleColumnRelationBean();
			singleColumnRelationBean.setMastersid(dbSingleColumnRelationBean.getColumnRelationSid());
			singleColumnRelationBean.setActualTtableName(dbSingleColumnRelationBean.getActualTableName());
			singleColumnRelationBean.setActualColumnName(dbSingleColumnRelationBean.getActualColumnName());
			singleColumnRelationBean.setReferenceTableName(dbSingleColumnRelationBean.getReferenceTableName());
			singleColumnRelationBean.setMappingColumnName(dbSingleColumnRelationBean.getMappingColumnName());
			singleColumnRelationBean.setDescColumnName(dbSingleColumnRelationBean.getDescColumnName());
			singleColumnRelationBean.setPrimaryKeyColumnName(dbSingleColumnRelationBean.getPrimaryKeyColumnName());
			addKeyRelationList(singleColumnRelationBean);

		}
	}

	private void insertToHierarchyRelationList() {

		Session session = gtnSqlQueryEngine.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(HierarchyTypeTableRelation.class);
		@SuppressWarnings("unchecked")
		List<HierarchyTypeTableRelation> results = criteria.list();

		for (HierarchyTypeTableRelation dbEntityBean : results) {
			GtnFrameworkEntityHierarchyRelationBean entityBean = new GtnFrameworkEntityHierarchyRelationBean();
			entityBean.setTableName(dbEntityBean.getId().getTableName());
			entityBean.setEntityId(dbEntityBean.getId().getEntityId());
			entityBean.setHierarchyType(dbEntityBean.getId().getHierarchyType());
			addEntityHierarchyRelationList(entityBean);
		}

	}

	@SuppressWarnings("unchecked")
	private void insertToHierarchyRestrictionList() {
		try {
			String query = "select HIERARCHY_TABLE_MASTER_SID,	ACTUAL_TABLE_NAME,	ACTUAL_COLUMN_NAME	,REFERENCE_TABLE_NAME,	REFERENCE_COLUMN_NAME,	RESTRICTION_COLUMN_NAME,	RESTRICTION_VALUE,	JOIN_SEQUENCE,OPERATOR_TYPE from HIERARCHY_RESTRICTION_MASTER";
			List<Object[]> result = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);
			for (Object[] dbTableMasterBean : result) {
				GtnFrameworkHierarchyRestrictionBean tableRestrictionBean = new GtnFrameworkHierarchyRestrictionBean();
				tableRestrictionBean.setHierarchyTableMasterSid(Integer.valueOf(dbTableMasterBean[0].toString()));
				tableRestrictionBean
						.setActualTableName(dbTableMasterBean[1] != null ? dbTableMasterBean[1].toString() : "");
				tableRestrictionBean
						.setActualColumnName(dbTableMasterBean[2] != null ? dbTableMasterBean[2].toString() : "");
				tableRestrictionBean
						.setReferencTableName(dbTableMasterBean[3] != null ? dbTableMasterBean[3].toString() : "");
				tableRestrictionBean
						.setReferenceColumnName(dbTableMasterBean[4] != null ? dbTableMasterBean[4].toString() : "");
				tableRestrictionBean
						.setRestrictionColumnName(dbTableMasterBean[5] != null ? dbTableMasterBean[5].toString() : "");
				tableRestrictionBean
						.setRestrictionValue(dbTableMasterBean[6] != null ? dbTableMasterBean[6].toString() : "");
				tableRestrictionBean
						.setJoinSequence(dbTableMasterBean[7] != null ? Integer.valueOf(dbTableMasterBean[7].toString())
								: Integer.MAX_VALUE);
				tableRestrictionBean
						.setOperatorType(dbTableMasterBean[8] != null ? dbTableMasterBean[8].toString() : "");
				addRestrictionBeanList(tableRestrictionBean);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
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

	public List<GtnFrameworkHierarchyRestrictionBean> getRestrictionBean(Integer pathValue) {
		List<GtnFrameworkHierarchyRestrictionBean> restrictionList = new ArrayList<>();
		for (GtnFrameworkHierarchyRestrictionBean hierachyRestrcionBean : hierachyRestrcionList) {
			if (hierachyRestrcionBean.getHierarchyTableMasterSid() == pathValue)
				restrictionList.add(hierachyRestrcionBean);
		}
		return restrictionList;
	}
}
