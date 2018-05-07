package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;

public class GtnReportHierarchyLevelBean {

	private String level;
	private Integer levelNo;
	private String parentNode;
	private Integer relationshipLevelSid;
	private String relationshipLevelValue;
	private String tableName;
	private String fieldName;
	private String levelValueReference;
	private boolean fromCompany;
	private boolean fromItem;
	private boolean fromContract;
	private String hierarchyNo;
	private String parentHierarchyNo;
	private Integer treeLevelNo;
	private Integer hierarchyDefSid;
	private String hierarchyIndicator;
	private String relationShipBuilderId;
	private String hierarchyLevelDefId;
	private String ndc;
	private String form;
	private String strength;
	private String relationShipSid;
	private String displayValue;
	private String relationshipLevelName;
	private int count = 0;
	private String deductionLevel;
	private String deductionValue;
	private String hierarchyType;
	private Integer hierarchyVersionNo;
	private int relationShipVersionNo;

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

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public Integer getRelationshipLevelSid() {
		return relationshipLevelSid;
	}

	public void setRelationshipLevelSid(Integer relationshipLevelSid) {
		this.relationshipLevelSid = relationshipLevelSid;
	}

	public String getRelationshipLevelValue() {
		return relationshipLevelValue;
	}

	public void setRelationshipLevelValue(String relationshipLevelValue) {
		this.relationshipLevelValue = relationshipLevelValue;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getLevelValueReference() {
		return levelValueReference;
	}

	public void setLevelValueReference(String levelValueReference) {
		this.levelValueReference = levelValueReference;
	}

	public boolean isFromCompany() {
		return fromCompany;
	}

	public void setFromCompany(boolean fromCompany) {
		this.fromCompany = fromCompany;
	}

	public boolean isFromItem() {
		return fromItem;
	}

	public void setFromItem(boolean fromItem) {
		this.fromItem = fromItem;
	}

	public boolean isFromContract() {
		return fromContract;
	}

	public void setFromContract(boolean fromContract) {
		this.fromContract = fromContract;
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public String getParentHierarchyNo() {
		return parentHierarchyNo;
	}

	public void setParentHierarchyNo(String parentHierarchyNo) {
		this.parentHierarchyNo = parentHierarchyNo;
	}

	public Integer getTreeLevelNo() {
		return treeLevelNo;
	}

	public void setTreeLevelNo(Integer treeLevelNo) {
		this.treeLevelNo = treeLevelNo;
	}

	public String getHierarchyIndicator() {
		return hierarchyIndicator;
	}

	public void setHierarchyIndicator(String hierarchyIndicator) {
		this.hierarchyIndicator = hierarchyIndicator;
	}

	public String getRelationShipBuilderId() {
		return relationShipBuilderId;
	}

	public void setRelationShipBuilderId(String relationShipBuilderId) {
		this.relationShipBuilderId = relationShipBuilderId;
	}

	public String getHierarchyLevelDefSid() {
		return hierarchyLevelDefId;
	}

	public void setHierarchyLevelDefSid(String hierarchyLevelDefId) {
		this.hierarchyLevelDefId = hierarchyLevelDefId;
	}

	public String getNdc() {
		return ndc;
	}

	public void setNdc(String ndc) {
		this.ndc = ndc;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getRelationShipSid() {
		return relationShipSid;
	}

	public void setRelationShipSid(String relationShipSid) {
		this.relationShipSid = relationShipSid;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getRelationshipLevelName() {
		return relationshipLevelName;
	}

	public void setRelationshipLevelName(String relationshipLevelName) {
		this.relationshipLevelName = relationshipLevelName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDeductionLevel() {
		return deductionLevel;
	}

	public void setDeductionLevel(String deductionLevel) {
		this.deductionLevel = deductionLevel;
	}

	public String getDeductionValue() {
		return deductionValue;
	}

	public void setDeductionValue(String deductionValue) {
		this.deductionValue = deductionValue;
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
	
	public static GtnReportHierarchyLevelBean getLastLinkedLevel(List<GtnReportHierarchyLevelBean> customerHierarchyLevelDefinitionList) {
		for (int i = customerHierarchyLevelDefinitionList.size() - 1; i >= 0; i--) {
			if (!customerHierarchyLevelDefinitionList.get(i).tableName.isEmpty()) {
				return customerHierarchyLevelDefinitionList.get(i);
			}
		}
		return null;
	}

}
