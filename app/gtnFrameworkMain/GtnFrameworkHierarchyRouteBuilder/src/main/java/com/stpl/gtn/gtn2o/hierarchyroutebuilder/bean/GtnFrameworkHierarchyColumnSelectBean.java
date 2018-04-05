package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

public class GtnFrameworkHierarchyColumnSelectBean {

	private int masterTableSid;
	private String actualColumnName;
	private String referenceTableName;
	private String mappingColumnName;
	private String descColumnName;

	public GtnFrameworkHierarchyColumnSelectBean() {
		super();
	}

	public int getMasterTableSid() {
		return masterTableSid;
	}

	public String getActualColumnName() {
		return actualColumnName;
	}

	public void setMasterTableSid(int masterTableSid) {
		this.masterTableSid = masterTableSid;
	}

	public String getReferenceTableName() {
		return referenceTableName;
	}

	public String getMappingColumnName() {
		return mappingColumnName;
	}

	public void setDescColumnName(String descColumnName) {
		this.descColumnName = descColumnName;
	}
	public void setActualColumnName(String actualColumnName) {
		this.actualColumnName = actualColumnName;
	}

	public void setReferenceTableName(String referenceTableName) {
		this.referenceTableName = referenceTableName;
	}

	public void setMappingColumnName(String mappingColumnName) {
		this.mappingColumnName = mappingColumnName;
	}

	public String getDescColumnName() {
		return descColumnName;
	}


}
