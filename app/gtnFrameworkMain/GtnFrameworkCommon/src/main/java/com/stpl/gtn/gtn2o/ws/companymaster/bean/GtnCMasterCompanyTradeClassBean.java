package com.stpl.gtn.gtn2o.ws.companymaster.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnCMasterCompanyTradeClassBean implements Serializable {

	private static final long serialVersionUID = -9097721006292699769L;

	public GtnCMasterCompanyTradeClassBean() {
		super();
	}

	private Integer companyMasterSystemId;

	private Date companyTradeClassStartDate;

	private Date companyTradeClassEndDate;

	private Integer companyTradeClassSid;

	private String companyTradeClassValue;

	private String companyPriorTradeClass;

	private Date companyPriorTradeClassStartDate;

	private Date lastUpdatedDate;

	private String inboundStatus;

	private Integer recordLockStatus;

	private String batchId;

	private String source;

	private Integer createdBy;

	private Date createdDate;

	private Integer modifiedBy;

	private Date modifiedDate;

	private String createdByName;

	private String modifiedByName;

	public Date getCompanyPriorTradeClassStartDate() {
		return companyPriorTradeClassStartDate == null ? null : (Date) companyPriorTradeClassStartDate.clone();
	}

	public void setCompanyPriorTradeClassStartDate(Date companyPriorTradeClassStartDate) {
		this.companyPriorTradeClassStartDate = companyPriorTradeClassStartDate == null ? null
				: (Date) companyPriorTradeClassStartDate.clone();
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

	public Integer getCompanyMasterSystemId() {
		return companyMasterSystemId;
	}

	public void setCompanyMasterSystemId(Integer companyMasterSystemId) {
		this.companyMasterSystemId = companyMasterSystemId;
	}

	public Integer getRecordLockStatus() {
		return recordLockStatus;
	}

	public void setRecordLockStatus(Integer recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Integer getCompanyTradeClassSid() {
		return companyTradeClassSid;
	}

	public void setCompanyTradeClassSid(Integer companyTradeClass) {
		this.companyTradeClassSid = companyTradeClass;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public void setCompanyTradeClassStartDate(Date companyTradeClassStartDate) {
		this.companyTradeClassStartDate = companyTradeClassStartDate == null ? null
				: (Date) companyTradeClassStartDate.clone();
	}

	public Date getCompanyTradeClassEndDate() {
		return companyTradeClassEndDate == null ? null : (Date) companyTradeClassEndDate.clone();
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public Date getCompanyTradeClassStartDate() {
		return companyTradeClassStartDate == null ? null : (Date) companyTradeClassStartDate.clone();
	}

	public void setCompanyTradeClassEndDate(Date companyTradeClassEndDate) {
		this.companyTradeClassEndDate = companyTradeClassEndDate == null ? null
				: (Date) companyTradeClassEndDate.clone();
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCompanyTradeClassValue() {
		return companyTradeClassValue;
	}

	public void setCompanyTradeClassValue(String companyTradeClassValue) {
		this.companyTradeClassValue = companyTradeClassValue;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getCompanyPriorTradeClass() {
		return companyPriorTradeClass;
	}

	public void setCompanyPriorTradeClass(String companyPriorTradeClass) {
		this.companyPriorTradeClass = companyPriorTradeClass;
	}

	public String getModifiedByName() {
		return modifiedByName;
	}

	public void setModifiedByName(String modifiedByName) {
		this.modifiedByName = modifiedByName;
	}
}
