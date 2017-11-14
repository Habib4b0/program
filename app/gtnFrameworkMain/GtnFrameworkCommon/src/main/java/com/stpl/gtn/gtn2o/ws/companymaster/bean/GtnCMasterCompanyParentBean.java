package com.stpl.gtn.gtn2o.ws.companymaster.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnCMasterCompanyParentBean implements Serializable {

	public GtnCMasterCompanyParentBean() {
		super();
	}

	private static final long serialVersionUID = 6212967435256303815L;

	private Integer companyMasterSystemId;

	private Integer parentCompanyMasterSystemId;

	private Date companyParentStartDate;

	private Date companyParentEndDate;

	private String priorParentCmpyMasterSystemId;

	private Date priorParentStartDate;

	private Date lastUpdatedDate;

	private String inboundStatus;

	private Integer recordLockStatus;

	private String batchId;

	private String source;

	private Integer createdBy;

	private Date createdDate;

	private Integer modifiedBy;

	private Date modifiedDate;

	private String companyNo;

	private String companyName;

	private String createdByName;

	private String modifiedByName;

	public Date getCompanyParentStartDate() {
		return companyParentStartDate == null ? null : (Date) companyParentStartDate.clone();
	}

	public void setCompanyParentStartDate(Date companyParentStartDate) {
		this.companyParentStartDate = companyParentStartDate == null ? null : (Date) companyParentStartDate.clone();
	}

	public String getPriorParentCmpyMasterSystemId() {
		return priorParentCmpyMasterSystemId;
	}

	public void setPriorParentCmpyMasterSystemId(String priorParentCmpyMasterSystemId) {
		this.priorParentCmpyMasterSystemId = priorParentCmpyMasterSystemId;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate == null ? null : (Date) lastUpdatedDate.clone();
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate == null ? null : (Date) lastUpdatedDate.clone();
	}

	public String getInboundStatus() {
		return inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public Integer getRecordLockStatus() {
		return recordLockStatus;
	}

	public void setRecordLockStatus(Integer recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	public Integer getCompanyMasterSystemId() {
		return companyMasterSystemId;
	}

	public void setCompanyMasterSystemId(Integer companyMasterSystemId) {
		this.companyMasterSystemId = companyMasterSystemId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Integer getParentCompanyMasterSystemId() {
		return parentCompanyMasterSystemId;
	}

	public void setParentCompanyMasterSystemId(Integer parentCompanyMasterSystemId) {
		this.parentCompanyMasterSystemId = parentCompanyMasterSystemId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getCompanyParentEndDate() {
		return companyParentEndDate == null ? null : (Date) companyParentEndDate.clone();
	}

	public void setCompanyParentEndDate(Date companyParentEndDate) {
		this.companyParentEndDate = companyParentEndDate == null ? null : (Date) companyParentEndDate.clone();
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getPriorParentStartDate() {
		return priorParentStartDate == null ? null : (Date) priorParentStartDate.clone();
	}

	public void setPriorParentStartDate(Date priorParentStartDate) {
		this.priorParentStartDate = priorParentStartDate == null ? null : (Date) priorParentStartDate.clone();
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getModifiedByName() {
		return modifiedByName;
	}

	public void setModifiedByName(String modifiedByName) {
		this.modifiedByName = modifiedByName;
	}
}
