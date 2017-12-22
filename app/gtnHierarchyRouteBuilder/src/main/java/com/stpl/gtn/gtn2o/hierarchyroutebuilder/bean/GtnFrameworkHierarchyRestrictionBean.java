package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

public class GtnFrameworkHierarchyRestrictionBean {
	private String actualTableName;
	private String actualColumnName;
	private String referencTableName;
	private String referenceColumnName;
	private String restrictionColumnName;
	private String restrictionValue;
	private String hierarchyTableMasterSid;

	public String getActualTableName() {
		return actualTableName;
	}

	public void setActualTableName(String actualTableName) {
		this.actualTableName = actualTableName;
	}

	public String getActualColumnName() {
		return actualColumnName;
	}

	public void setActualColumnName(String actualColumnName) {
		this.actualColumnName = actualColumnName;
	}

	public String getReferencTableName() {
		return referencTableName;
	}

	public void setReferencTableName(String referencTableName) {
		this.referencTableName = referencTableName;
	}

	public String getReferenceColumnName() {
		return referenceColumnName;
	}

	public void setReferenceColumnName(String referenceColumnName) {
		this.referenceColumnName = referenceColumnName;
	}

	public String getRestrictionColumnName() {
		return restrictionColumnName;
	}

	public void setRestrictionColumnName(String restrictionColumnName) {
		this.restrictionColumnName = restrictionColumnName;
	}

	public String getRestrictionValue() {
		return restrictionValue;
	}

	public void setRestrictionValue(String restrictionValue) {
		this.restrictionValue = restrictionValue;
	}

	public String getHierarchyTableMasterSid() {
		return hierarchyTableMasterSid;
	}

	public void setHierarchyTableMasterSid(String hierarchyTableMasterSid) {
		this.hierarchyTableMasterSid = hierarchyTableMasterSid;
	}
}
