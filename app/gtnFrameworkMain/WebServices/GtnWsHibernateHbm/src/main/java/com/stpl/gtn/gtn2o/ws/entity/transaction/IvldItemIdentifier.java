package com.stpl.gtn.gtn2o.ws.entity.transaction;
// Generated Mar 20, 2017 5:05:43 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * IvldItemIdentifier generated by hbm2java
 */
@SuppressWarnings("serial")
public class IvldItemIdentifier implements java.io.Serializable {

	private int ivldItemIdentifierSid;
	private String itemIdentifierIntfid;
	private int identifierStatus;
	private String startDate;
	private String endDate;
	private String entityCode;
	private String batchId;
	private String source;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private String itemId;
	private String itemNo;
	private String itemIdentifier;
	private String itemName;
	private String identifierCodeQualifierName;
	private String identifierCodeQualifier;
	private String itemStatus;
	private String addChgDelIndicator;
	private String reasonForFailure;
    private Date intfInsertedDate;
    private String errorCode;
    private String errorField;
    private String reprocessedFlag;
    private boolean checkRecord;

	public IvldItemIdentifier() {
	}

	public IvldItemIdentifier(int ivldItemIdentifierSid, String itemIdentifierIntfid) {
		this.ivldItemIdentifierSid = ivldItemIdentifierSid;
		this.itemIdentifierIntfid = itemIdentifierIntfid;
	}


	public int getIvldItemIdentifierSid() {
		return ivldItemIdentifierSid;
	}
	
	public void setItemIdentifierSid(int ivldItemIdentifierSid) {
		this.ivldItemIdentifierSid = ivldItemIdentifierSid;
	}

	public void setIvldItemIdentifierSid(int ivldItemIdentifierSid) {
		this.ivldItemIdentifierSid = ivldItemIdentifierSid;
	}

	public String getItemIdentifierIntfid() {
		return itemIdentifierIntfid;
	}

	public void setItemIdentifierIntfid(String itemIdentifierIntfid) {
		this.itemIdentifierIntfid = itemIdentifierIntfid;
	}

	public int getIdentifierStatus() {
		return this.identifierStatus;
	}

	public void setIdentifierStatus(int identifierStatus) {
		this.identifierStatus = identifierStatus;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEntityCode() {
		return this.entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
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

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getIdentifierCodeQualifierName() {
		return identifierCodeQualifierName;
	}

	public void setIdentifierCodeQualifierName(String identifierCodeQualifierName) {
		this.identifierCodeQualifierName = identifierCodeQualifierName;
	}

	public String getIdentifierCodeQualifier() {
		return identifierCodeQualifier;
	}

	public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
		this.identifierCodeQualifier = identifierCodeQualifier;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getAddChgDelIndicator() {
		return addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		this.addChgDelIndicator = addChgDelIndicator;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public String getReasonForFailure() {
		return reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		this.reasonForFailure = reasonForFailure;
	}

	public Date getIntfInsertedDate() {
		return intfInsertedDate;
	}

	public void setIntfInsertedDate(Date intfInsertedDate) {
		this.intfInsertedDate = intfInsertedDate;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

	public String getReprocessedFlag() {
		return reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		this.reprocessedFlag = reprocessedFlag;
	}

	public boolean isCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(boolean checkRecord) {
		this.checkRecord = checkRecord;
	}

}
