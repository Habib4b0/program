package com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnCFamilyPlanInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnCFamilyPlanInformation() {
		super();
	}

	private Integer cfpSid;
	private String cfpId = "";
	private String cfpName = "";
	private String cfpNo = "";
	private Date cfpStartDate;
	private Date cfpEndDate;
	private Integer cfpType;
	private Integer cfpStatus;
	private Integer cfpCategory;
	private Integer cfpTradeClass;
	private Integer cfpDesignation;
	private String parentCfpId = "";
	private String parentCfpName = "";
	private Integer salesInclusion;
	private String createdBy = "";
	private Date createdDate;
	private String modifiedBy = "";
	private Date modifiedDate;
	private boolean recordLockStatus;
	private String internalNotes = "";

	public Integer getCfpSid() {
		return cfpSid;
	}

	public void setCfpSid(Integer cfpSid) {
		this.cfpSid = cfpSid;
	}

	public String getCfpId() {
		return cfpId;
	}

	public void setCfpId(String cfpId) {
		this.cfpId = cfpId;
	}

	public String getCfpName() {
		return cfpName;
	}

	public void setCfpName(String cfpName) {
		this.cfpName = cfpName;
	}

	public String getCfpNo() {
		return cfpNo;
	}

	public void setCfpNo(String cfpNo) {
		this.cfpNo = cfpNo;
	}

	public Date getCfpStartDate() {
		return cfpStartDate == null ? null : (Date) cfpStartDate.clone();
	}

	public void setCfpStartDate(Date cfpStartDate) {
		this.cfpStartDate = cfpStartDate == null ? null : (Date) cfpStartDate.clone();
	}

	public Date getCfpEndDate() {
		return cfpEndDate == null ? null : (Date) cfpEndDate.clone();
	}

	public void setCfpEndDate(Date cfpEndDate) {
		this.cfpEndDate = cfpEndDate == null ? null : (Date) cfpEndDate.clone();
	}

	public Integer getCfpType() {
		return cfpType;
	}

	public void setCfpType(Integer cfpType) {
		this.cfpType = cfpType;
	}

	public Integer getCfpStatus() {
		return cfpStatus;
	}

	public void setCfpStatus(Integer cfpStatus) {
		this.cfpStatus = cfpStatus;
	}

	public Integer getCfpCategory() {
		return cfpCategory;
	}

	public void setCfpCategory(Integer cfpCategory) {
		this.cfpCategory = cfpCategory;
	}

	public Integer getCfpTradeClass() {
		return cfpTradeClass;
	}

	public void setCfpTradeClass(Integer cfpTradeClass) {
		this.cfpTradeClass = cfpTradeClass;
	}

	public Integer getCfpDesignation() {
		return cfpDesignation;
	}

	public void setCfpDesignation(Integer cfpDesignation) {
		this.cfpDesignation = cfpDesignation;
	}

	public String getParentCfpId() {
		return parentCfpId;
	}

	public void setParentCfpId(String parentCfpId) {
		this.parentCfpId = parentCfpId;
	}

	public String getParentCfpName() {
		return parentCfpName;
	}

	public void setParentCfpName(String parentCfpName) {
		this.parentCfpName = parentCfpName;
	}

	public Integer getSalesInclusion() {
		return salesInclusion;
	}

	public void setSalesInclusion(Integer salesInclusion) {
		this.salesInclusion = salesInclusion;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public String getInternalNotes() {
		return internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

	public boolean isRecordLockStatus() {
		return recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}
}