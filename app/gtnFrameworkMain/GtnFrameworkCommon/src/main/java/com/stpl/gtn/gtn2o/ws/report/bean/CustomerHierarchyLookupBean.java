package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;
import java.util.Map;

public class CustomerHierarchyLookupBean {

	private String hierarchyName;
	private String hierarchyType;
	private String hierarchyIndicator;
	private String hierarchyHighestLevel;
	private String hierarchyLowestLevel;
	private String createdDate;
	private String modifiedDate;
	private List<String> levels;
	private List<String> levelValues;
	private Map<Integer, String> levelNoMap;
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

	public String getHierarchyIndicator() {
		return hierarchyIndicator;
	}

	public void setHierarchyIndicator(String hierarchyIndicator) {
		this.hierarchyIndicator = hierarchyIndicator;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
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

}
