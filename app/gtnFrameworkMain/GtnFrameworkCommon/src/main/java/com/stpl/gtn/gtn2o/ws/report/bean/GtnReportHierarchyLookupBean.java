package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.Date;

public class GtnReportHierarchyLookupBean {
	
	public GtnReportHierarchyLookupBean(){
		super();
	}

	private String hierarchyName;
	private String hierarchyType;
	private String hierarchyLevelName;
	private String hierarchyHighestLevel;
	private String hierarchyLowestLevel;
	private Date createdDate;
	private Date modifiedDate;
	private int versionNo;
	private int hierarchyDefSid;

	public String getHierarchyName() {
		return hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
	}

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public String getHierarchyHighestLevel() {
		return hierarchyHighestLevel;
	}

	public void setHierarchyHighestLevel(String hierarchyHighestLevel) {
		this.hierarchyHighestLevel = hierarchyHighestLevel;
	}

	public String getHierarchyLowestLevel() {
		return hierarchyLowestLevel;
	}

	public void setHierarchyLowestLevel(String hierarchyLowestLevel) {
		this.hierarchyLowestLevel = hierarchyLowestLevel;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public int getHierarchyDefSid() {
		return hierarchyDefSid;
	}

	public void setHierarchyDefSid(int hierarchyDefSid) {
		this.hierarchyDefSid = hierarchyDefSid;
	}

	public String getHierarchyLevelName() {
		return hierarchyLevelName;
	}

	public void setHierarchyLevelName(String hierarchyLevelName) {
		this.hierarchyLevelName = hierarchyLevelName;
	}

}
