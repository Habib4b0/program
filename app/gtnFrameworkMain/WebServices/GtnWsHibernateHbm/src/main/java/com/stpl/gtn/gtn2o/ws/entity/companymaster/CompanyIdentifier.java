package com.stpl.gtn.gtn2o.ws.entity.companymaster;
// Generated Jan 5, 2017 4:57:07 PM by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;

/**
 * CompanyIdentifier generated by hbm2java
 */
public class CompanyIdentifier implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1444222014749373400L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int companyIdentifierSid;
	private CompanyMaster companyMaster;
	private CompanyQualifier companyQualifier;
	private HelperTable helperTable;
	private String companyIdentifierValue;
	private Date startDate;
	private Date endDate;
	private String entityCode;
	private char inboundStatus;
	private boolean recordLockStatus;
	private String batchId;
	private String source;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;

	public CompanyIdentifier() {
	}

	public CompanyIdentifier(CompanyMaster companyMaster, CompanyQualifier companyQualifier, HelperTable helperTable,
			String companyIdentifierValue, Date startDate, char inboundStatus, boolean recordLockStatus, String batchId,
			int createdBy, Date createdDate, int modifiedBy, Date modifiedDate) {
		this.companyMaster = companyMaster;
		this.companyQualifier = companyQualifier;
		this.helperTable = helperTable;
		this.companyIdentifierValue = companyIdentifierValue;
		this.startDate = startDate;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public CompanyIdentifier(CompanyMaster companyMaster, CompanyQualifier companyQualifier, HelperTable helperTable,
			String companyIdentifierValue, Date startDate, Date endDate, String entityCode, char inboundStatus,
			boolean recordLockStatus, String batchId, String source, int createdBy, Date createdDate, int modifiedBy,
			Date modifiedDate) {
		this.companyMaster = companyMaster;
		this.companyQualifier = companyQualifier;
		this.helperTable = helperTable;
		this.companyIdentifierValue = companyIdentifierValue;
		this.startDate = startDate;
		this.endDate = endDate;
		this.entityCode = entityCode;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.source = source;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public int getCompanyIdentifierSid() {
		return this.companyIdentifierSid;
	}

	public void setCompanyIdentifierSid(int companyIdentifierSid) {
		this.companyIdentifierSid = companyIdentifierSid;
	}

	public CompanyMaster getCompanyMaster() {
		return this.companyMaster;
	}

	public void setCompanyMaster(CompanyMaster companyMaster) {
		this.companyMaster = companyMaster;
	}

	public CompanyQualifier getCompanyQualifier() {
		return this.companyQualifier;
	}

	public void setCompanyQualifier(CompanyQualifier companyQualifier) {
		this.companyQualifier = companyQualifier;
	}

	public HelperTable getHelperTable() {
		return this.helperTable;
	}

	public void setHelperTable(HelperTable helperTable) {
		this.helperTable = helperTable;
	}

	public String getCompanyIdentifierValue() {
		return this.companyIdentifierValue;
	}

	public void setCompanyIdentifierValue(String companyIdentifierValue) {
		this.companyIdentifierValue = companyIdentifierValue;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEntityCode() {
		return this.entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public char getInboundStatus() {
		return this.inboundStatus;
	}

	public void setInboundStatus(char inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public boolean isRecordLockStatus() {
		return this.recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
