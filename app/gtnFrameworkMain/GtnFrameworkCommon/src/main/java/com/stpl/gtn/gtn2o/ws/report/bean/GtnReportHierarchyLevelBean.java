package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;

public class GtnReportHierarchyLevelBean {

	public GtnReportHierarchyLevelBean() {
		super();
	}

	private String level;
	private Integer levelNo;
	private Integer relationshipLevelSid;
	private String relationshipLevelValue;
	private String tableName;
	private String fieldName;
	private String levelValueReference;
	private String hierarchyNo;
	private Integer hierarchyDefSid;
	private String relationShipBuilderId;
	private String hierarchyLevelDefId;
	private String relationShipSid;
	private String hierarchyType;
	private Integer hierarchyVersionNo;
	private int relationShipVersionNo;

	public String getFieldName() {
		return fieldName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}

	public Integer getRelationshipLevelSid() {
		return relationshipLevelSid;
	}

	public void setRelationshipLevelSid(Integer relationshipLevelSid) {
		this.relationshipLevelSid = relationshipLevelSid;
	}

	public void setRelationshipLevelValue(String relationshipLevelValue) {
		this.relationshipLevelValue = relationshipLevelValue;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLevelValueReference() {
		return levelValueReference;
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public String getRelationShipBuilderId() {
		return relationShipBuilderId;
	}

	public void setRelationShipBuilderId(String relationShipBuilderId) {
		this.relationShipBuilderId = relationShipBuilderId;
	}

	public String getTableName() {
		return tableName;
	}

	public String getHierarchyLevelDefSid() {
		return hierarchyLevelDefId;
	}

	public void setHierarchyLevelDefSid(String hierarchyLevelDefId) {
		this.hierarchyLevelDefId = hierarchyLevelDefId;
	}

	public String getRelationShipSid() {
		return relationShipSid;
	}

	public void setRelationShipSid(String relationShipSid) {
		this.relationShipSid = relationShipSid;
	}

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public Integer getHierarchyVersionNo() {
		return hierarchyVersionNo;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setHierarchyVersionNo(Integer hierarchyVersionNo) {
		this.hierarchyVersionNo = hierarchyVersionNo;
	}

	public int getRelationShipVersionNo() {
		return relationShipVersionNo;
	}

	public void setRelationShipVersionNo(int relationShipVersionNo) {
		this.relationShipVersionNo = relationShipVersionNo;
	}

	public Integer getHierarchyDefSid() {
		return hierarchyDefSid;
	}

	public void setHierarchyDefSid(Integer hierarchyDefSid) {
		this.hierarchyDefSid = hierarchyDefSid;
	}

	public void setLevelValueReference(String levelValueReference) {
		this.levelValueReference = levelValueReference;
	}

	public static GtnReportHierarchyLevelBean getLastLinkedLevel(
			List<GtnReportHierarchyLevelBean> customerHierarchyLevelDefinitionList) {
		for (int i = customerHierarchyLevelDefinitionList.size() - 1; i >= 0; i--) {
			if (!customerHierarchyLevelDefinitionList.get(i).tableName.isEmpty()) {
				return customerHierarchyLevelDefinitionList.get(i);
			}
		}
		return null;
	}

	public String getRelationshipLevelValue() {
		return relationshipLevelValue;
	}

}
