package com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean;

import java.util.Date;

public class GtnWsRelationshipBuilderBean {
	
	
	public GtnWsRelationshipBuilderBean() {
		super();
	}

	private int relationshipBuilderSid;
	private int hierarchyDefinitionSid;
	private String relationshipName;
	private String relationshipDescription;
	private Date startDate;
	private String buildType;
	private int versionNo;
	private Integer hierarchyVersion;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private Integer deductionRelation;
	private String hierarchycategory;

	public int getRelationshipBuilderSid() {
		return relationshipBuilderSid;
	}

	public void setRelationshipBuilderSid(int relationshipBuilderSid) {
		this.relationshipBuilderSid = relationshipBuilderSid;
	}

	public int getHierarchyDefinitionSid() {
		return hierarchyDefinitionSid;
	}

	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		this.hierarchyDefinitionSid = hierarchyDefinitionSid;
	}

	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	public String getRelationshipDescription() {
		return relationshipDescription;
	}

	public void setRelationshipDescription(String relationshipDescription) {
		this.relationshipDescription = relationshipDescription;
	}

	public Date getStartDate() {
		return startDate == null ? null : (Date) startDate.clone();
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate == null ? null : (Date) startDate.clone();
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public Integer getHierarchyVersion() {
		return hierarchyVersion;
	}

	public void setHierarchyVersion(Integer hierarchyVersion) {
		this.hierarchyVersion = hierarchyVersion;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public Integer getDeductionRelation() {
		return deductionRelation;
	}

	public void setDeductionRelation(Integer deductionRelation) {
		this.deductionRelation = deductionRelation;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public String getHierarchycategory() {
		return hierarchycategory;
	}

	public void setHierarchycategory(String hierarchycategory) {
		this.hierarchycategory = hierarchycategory;
	}



}
