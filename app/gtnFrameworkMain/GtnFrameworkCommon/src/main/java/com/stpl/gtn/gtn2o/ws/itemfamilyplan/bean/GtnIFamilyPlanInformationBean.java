package com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnIFamilyPlanInformationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnIFamilyPlanInformationBean() {
		super();
	}

	private Integer ifpSid;
	private String ifpId;
	private String ifpName;
	private String ifpNo;
	private Date ifpStartDate;
	private Date ifpEndDate;
	private Integer ifpType;
	private Integer ifpStatus;
	private Integer ifpCategory;
	private Integer ifpDesignation;
	private String parentIfpId;
	private String parentIfpName;
	private String internalNotes;

	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private boolean recordLockStatus;

	public Integer getIfpSid() {
		return ifpSid;
	}

	public void setIfpSid(Integer ifpSid) {
		this.ifpSid = ifpSid;
	}

	public String getIfpId() {
		return ifpId;
	}

	public void setIfpId(String ifpId) {
		this.ifpId = ifpId;
	}

	public String getIfpName() {
		return ifpName;
	}

	public void setIfpName(String ifpName) {
		this.ifpName = ifpName;
	}

	public String getIfpNo() {
		return ifpNo;
	}

	public void setIfpNo(String ifpNo) {
		this.ifpNo = ifpNo;
	}

	public Date getIfpStartDate() {
		return  ifpStartDate==null ? null :(Date)ifpStartDate.clone();
	}

	public void setIfpStartDate(Date ifpStartDate) {
		this.ifpStartDate = ifpStartDate==null ? null :(Date)ifpStartDate.clone();
	}

	public Date getIfpEndDate() {
		return  ifpEndDate==null ? null :(Date)ifpEndDate.clone();
	}

	public void setIfpEndDate(Date ifpEndDate) {
		this.ifpEndDate = ifpEndDate==null ? null :(Date)ifpEndDate.clone();
	}

	public Integer getIfpType() {
		return ifpType;
	}

	public void setIfpType(Integer ifpType) {
		this.ifpType = ifpType;
	}

	public Integer getIfpStatus() {
		return ifpStatus;
	}

	public void setIfpStatus(Integer ifpStatus) {
		this.ifpStatus = ifpStatus;
	}

	public Integer getIfpCategory() {
		return ifpCategory;
	}

	public void setIfpCategory(Integer ifpCategory) {
		this.ifpCategory = ifpCategory;
	}

	public Integer getIfpDesignation() {
		return ifpDesignation;
	}

	public void setIfpDesignation(Integer ifpDesignation) {
		this.ifpDesignation = ifpDesignation;
	}

	public String getParentIfpId() {
		return parentIfpId;
	}

	public void setParentIfpId(String parentIfpId) {
		this.parentIfpId = parentIfpId;
	}

	public String getParentIfpName() {
		return parentIfpName;
	}

	public void setParentIfpName(String parentIfpName) {
		this.parentIfpName = parentIfpName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return  createdDate==null ? null :(Date)createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate==null ? null :(Date)createdDate.clone();
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return  modifiedDate==null ? null :(Date)modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate==null ? null :(Date)modifiedDate.clone();
	}

	public boolean isRecordLockStatus() {
		return recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	public String getInternalNotes() {
		return internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

}