package com.stpl.gtn.gtn2o.ws.entity.transaction;

import java.util.Date;

@SuppressWarnings("serial")
public class GlBalanceMaster implements java.io.Serializable {

	private int glBalanceMasterSid;
	private String accountId;
	private String accountNo;
	private Double balance;
	private Date uploadDate;
	private String isActive;
	private Date insertedDate;
	private String year;
	private String period;
	private String closeIndicator;
	private Character inboundStatus;
	private boolean recordLockStatus;
	private String batchId;
	private String source;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

	public GlBalanceMaster() {
	}

	public GlBalanceMaster(int glBalanceMasterSid, String accountId, String accountNo, Double balance, Date uploadDate,
			boolean recordLockStatus, String batchId, String createdBy, Date createdDate, String modifiedBy,
			Date modifiedDate) {
		this.glBalanceMasterSid = glBalanceMasterSid;
		this.accountId = accountId;
		this.accountNo = accountNo;
		this.balance = balance;
		this.uploadDate = uploadDate;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public GlBalanceMaster(int glBalanceMasterSid, String accountId, String accountNo, Double balance, Date uploadDate,
			String isActive, Date insertedDate, String year, String period, String closeIndicator,
			Character inboundStatus, boolean recordLockStatus, String batchId, String source, String createdBy,
			Date createdDate, String modifiedBy, Date modifiedDate) {
		this.glBalanceMasterSid = glBalanceMasterSid;
		this.accountId = accountId;
		this.accountNo = accountNo;
		this.balance = balance;
		this.uploadDate = uploadDate;
		this.isActive = isActive;
		this.insertedDate = insertedDate;
		this.year = year;
		this.period = period;
		this.closeIndicator = closeIndicator;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.source = source;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public int getGlBalanceMasterSid() {
		return this.glBalanceMasterSid;
	}

	public void setGlBalanceMasterSid(int glBalanceMasterSid) {
		this.glBalanceMasterSid = glBalanceMasterSid;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getInsertedDate() {
		return this.insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCloseIndicator() {
		return this.closeIndicator;
	}

	public void setCloseIndicator(String closeIndicator) {
		this.closeIndicator = closeIndicator;
	}

	public Character getInboundStatus() {
		return this.inboundStatus;
	}

	public void setInboundStatus(Character inboundStatus) {
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

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
