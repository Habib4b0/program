package com.stpl.gtn.gtn2o.ws.companymaster.bean;

import java.io.Serializable;
import java.util.Date;

public class GtnCMasterIdentifierInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnCMasterIdentifierInfoBean() {
		super();
	}

	private Integer companyQualifierSid;

	private Integer companyMasterSid;

	private String companyIdentifierValue;

	private String companyQualifierValue;

	private Integer identifierStatus;

	private String identifierStatusValue;

	private Date companyIdentifierStartDate;

	private Date companyIdentifierEndDate;

	private String entityCode;

	private String inboundStatus;

	private Integer recordLockStatus;

	private String batchId;

	private String source;

	private String createdByName;

	private String modifiedByName;

	private Integer createdBy;

	private Date createdDate;

	private Integer modifiedBy;

	private Date modifiedDate;

	public Integer getCompanyMasterSid() {
		return companyMasterSid;
	}

	public void setCompanyMasterSid(Integer companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

	public Date getCompanyIdentifierStartDate() {
		return companyIdentifierStartDate == null ? null : (Date) companyIdentifierStartDate.clone();
	}

	public void setCompanyIdentifierStartDate(Date companyIdentifierStartDate) {
		this.companyIdentifierStartDate = companyIdentifierStartDate == null ? null
				: (Date) companyIdentifierStartDate.clone();
	}

	public Date getCompanyIdentifierEndDate() {
		return companyIdentifierEndDate == null ? null : (Date) companyIdentifierEndDate.clone();
	}

	public void setCompanyIdentifierEndDate(Date companyIdentifierEndDate) {
		this.companyIdentifierEndDate = companyIdentifierEndDate == null ? null
				: (Date) companyIdentifierEndDate.clone();
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
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

	public String getCompanyIdentifierValue() {
		return companyIdentifierValue;
	}

	public void setCompanyIdentifierValue(String companyIdentifierValue) {
		this.companyIdentifierValue = companyIdentifierValue;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Integer getIdentifierStatus() {
		return identifierStatus;
	}

	public void setIdentifierStatus(Integer identifierStatus) {
		this.identifierStatus = identifierStatus;
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

	public Integer getCompanyQualifierSid() {
		return companyQualifierSid;
	}

	public void setCompanyQualifierSid(Integer companyQualifierSid) {
		this.companyQualifierSid = companyQualifierSid;
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public String getCompanyQualifierValue() {
		return companyQualifierValue;
	}

	public void setCompanyQualifierValue(String companyQualifierValue) {
		this.companyQualifierValue = companyQualifierValue;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
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

	public String getIdentifierStatusValue() {
		return identifierStatusValue;
	}

	public void setIdentifierStatusValue(String identifierStatusValue) {
		this.identifierStatusValue = identifierStatusValue;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
