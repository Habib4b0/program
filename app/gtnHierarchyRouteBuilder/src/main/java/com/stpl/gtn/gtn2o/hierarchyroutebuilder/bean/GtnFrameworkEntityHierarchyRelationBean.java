package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

public class GtnFrameworkEntityHierarchyRelationBean {
	private String tableName;
	private String hierarchyType;
	private int entityId;

	public GtnFrameworkEntityHierarchyRelationBean(int entityId, String tableName, String hierarchyType) {
		this.tableName = tableName;
		this.hierarchyType = hierarchyType;
		this.entityId = entityId;
	}

	public GtnFrameworkEntityHierarchyRelationBean() {
		}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
}
