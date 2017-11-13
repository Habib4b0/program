package com.stpl.gtn.gtn2o.ws.entity.transaction;


import java.util.Date;

@SuppressWarnings("serial")
public class IvldPriceSchedule  implements java.io.Serializable {


     private int ivldPriceScheduleSid;
     private String priceScheduleIntfid;
     private String priceScheduleId;
     private String priceScheduleNo;
     private String priceScheduleName;
     private String priceScheduleType;
     private String priceScheduleCategory;
     private String tradeClass;
     private String priceScheduleDesignation;
     private String parentPriceScheduleId;
     private String parentPriceScheduleName;
     private String priceScheduleStatus;
     private String priceScheduleStartDate;
     private String priceScheduleEndDate;
     private String itemId;
     private String identifierCodeQualifier;
     private String itemNo;
     private String itemName;
     private String attachedStatus;
     private String attachedDate;
     private String contractPriceStartDate;
     private String contractPriceEndDate;
     private String priceType;
     private String contractPrice;
     private String priceRevision;
     private String revisionDate;
     private String priceProtectionStartDate;
     private String priceProtectionEndDate;
     private String priceToleranceType;
     private String priceTolerance;
     private String basePrice;
     private String priceToleranceFrequency;
     private String priceToleranceInterval;
     private String pricePlanId;
     private String pricePlanName;
     private String suggestedPrice;
     private String contractId;
     private String cfpId;
     private String mfpId;
     private String ifpId;
     private String price;
     private String createdBy;
     private Date createdDate;
     private String modifiedBy;
     private Date modifiedDate;
     private String batchId;
     private String source;
     private String addChgDelIndicator;
     private String reasonForFailure;
     private Date intfInsertedDate;
     private String errorCode;
     private String errorField;
     private boolean checkRecord;
     private String reprocessedFlag;

    public IvldPriceSchedule() {
    }

	
    public IvldPriceSchedule(int ivldPriceScheduleSid, String priceScheduleIntfid) {
        this.ivldPriceScheduleSid = ivldPriceScheduleSid;
        this.priceScheduleIntfid = priceScheduleIntfid;
    }
    public IvldPriceSchedule(int ivldPriceScheduleSid, String priceScheduleIntfid, String priceScheduleId, String priceScheduleNo, String priceScheduleName, String priceScheduleType, String priceScheduleCategory, String tradeClass, String priceScheduleDesignation, String parentPriceScheduleId, String parentPriceScheduleName, String priceScheduleStatus, String priceScheduleStartDate, String priceScheduleEndDate, String itemId, String identifierCodeQualifier, String itemNo, String itemName, String attachedStatus, String attachedDate, String contractPriceStartDate, String contractPriceEndDate, String priceType, String contractPrice, String priceRevision, String revisionDate, String priceProtectionStartDate, String priceProtectionEndDate, String priceToleranceType, String priceTolerance, String basePrice, String priceToleranceFrequency, String priceToleranceInterval, String pricePlanId, String pricePlanName, String suggestedPrice, String contractId, String cfpId, String mfpId, String ifpId, String price, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String batchId, String source, String addChgDelIndicator, String reasonForFailure, Date intfInsertedDate, String errorCode, String errorField, boolean checkRecord, String reprocessedFlag) {
       this.ivldPriceScheduleSid = ivldPriceScheduleSid;
       this.priceScheduleIntfid = priceScheduleIntfid;
       this.priceScheduleId = priceScheduleId;
       this.priceScheduleNo = priceScheduleNo;
       this.priceScheduleName = priceScheduleName;
       this.priceScheduleType = priceScheduleType;
       this.priceScheduleCategory = priceScheduleCategory;
       this.tradeClass = tradeClass;
       this.priceScheduleDesignation = priceScheduleDesignation;
       this.parentPriceScheduleId = parentPriceScheduleId;
       this.parentPriceScheduleName = parentPriceScheduleName;
       this.priceScheduleStatus = priceScheduleStatus;
       this.priceScheduleStartDate = priceScheduleStartDate;
       this.priceScheduleEndDate = priceScheduleEndDate;
       this.itemId = itemId;
       this.identifierCodeQualifier = identifierCodeQualifier;
       this.itemNo = itemNo;
       this.itemName = itemName;
       this.attachedStatus = attachedStatus;
       this.attachedDate = attachedDate;
       this.contractPriceStartDate = contractPriceStartDate;
       this.contractPriceEndDate = contractPriceEndDate;
       this.priceType = priceType;
       this.contractPrice = contractPrice;
       this.priceRevision = priceRevision;
       this.revisionDate = revisionDate;
       this.priceProtectionStartDate = priceProtectionStartDate;
       this.priceProtectionEndDate = priceProtectionEndDate;
       this.priceToleranceType = priceToleranceType;
       this.priceTolerance = priceTolerance;
       this.basePrice = basePrice;
       this.priceToleranceFrequency = priceToleranceFrequency;
       this.priceToleranceInterval = priceToleranceInterval;
       this.pricePlanId = pricePlanId;
       this.pricePlanName = pricePlanName;
       this.suggestedPrice = suggestedPrice;
       this.contractId = contractId;
       this.cfpId = cfpId;
       this.mfpId = mfpId;
       this.ifpId = ifpId;
       this.price = price;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
       this.batchId = batchId;
       this.source = source;
       this.addChgDelIndicator = addChgDelIndicator;
       this.reasonForFailure = reasonForFailure;
       this.intfInsertedDate = intfInsertedDate;
       this.errorCode = errorCode;
       this.errorField = errorField;
       this.checkRecord = checkRecord;
       this.reprocessedFlag = reprocessedFlag;
    }
   
    public int getIvldPriceScheduleSid() {
        return this.ivldPriceScheduleSid;
    }
    
    public void setIvldPriceScheduleSid(int ivldPriceScheduleSid) {
        this.ivldPriceScheduleSid = ivldPriceScheduleSid;
    }
    public String getPriceScheduleIntfid() {
        return this.priceScheduleIntfid;
    }
    
    public void setPriceScheduleIntfid(String priceScheduleIntfid) {
        this.priceScheduleIntfid = priceScheduleIntfid;
    }
    public String getPriceScheduleId() {
        return this.priceScheduleId;
    }
    
    public void setPriceScheduleId(String priceScheduleId) {
        this.priceScheduleId = priceScheduleId;
    }
    public String getPriceScheduleNo() {
        return this.priceScheduleNo;
    }
    
    public void setPriceScheduleNo(String priceScheduleNo) {
        this.priceScheduleNo = priceScheduleNo;
    }
    public String getPriceScheduleName() {
        return this.priceScheduleName;
    }
    
    public void setPriceScheduleName(String priceScheduleName) {
        this.priceScheduleName = priceScheduleName;
    }
    public String getPriceScheduleType() {
        return this.priceScheduleType;
    }
    
    public void setPriceScheduleType(String priceScheduleType) {
        this.priceScheduleType = priceScheduleType;
    }
    public String getPriceScheduleCategory() {
        return this.priceScheduleCategory;
    }
    
    public void setPriceScheduleCategory(String priceScheduleCategory) {
        this.priceScheduleCategory = priceScheduleCategory;
    }
    public String getTradeClass() {
        return this.tradeClass;
    }
    
    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }
    public String getPriceScheduleDesignation() {
        return this.priceScheduleDesignation;
    }
    
    public void setPriceScheduleDesignation(String priceScheduleDesignation) {
        this.priceScheduleDesignation = priceScheduleDesignation;
    }
    public String getParentPriceScheduleId() {
        return this.parentPriceScheduleId;
    }
    
    public void setParentPriceScheduleId(String parentPriceScheduleId) {
        this.parentPriceScheduleId = parentPriceScheduleId;
    }
    public String getParentPriceScheduleName() {
        return this.parentPriceScheduleName;
    }
    
    public void setParentPriceScheduleName(String parentPriceScheduleName) {
        this.parentPriceScheduleName = parentPriceScheduleName;
    }
    public String getPriceScheduleStatus() {
        return this.priceScheduleStatus;
    }
    
    public void setPriceScheduleStatus(String priceScheduleStatus) {
        this.priceScheduleStatus = priceScheduleStatus;
    }
    public String getPriceScheduleStartDate() {
        return this.priceScheduleStartDate;
    }
    
    public void setPriceScheduleStartDate(String priceScheduleStartDate) {
        this.priceScheduleStartDate = priceScheduleStartDate;
    }
    public String getPriceScheduleEndDate() {
        return this.priceScheduleEndDate;
    }
    
    public void setPriceScheduleEndDate(String priceScheduleEndDate) {
        this.priceScheduleEndDate = priceScheduleEndDate;
    }
    public String getItemId() {
        return this.itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getIdentifierCodeQualifier() {
        return this.identifierCodeQualifier;
    }
    
    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        this.identifierCodeQualifier = identifierCodeQualifier;
    }
    public String getItemNo() {
        return this.itemNo;
    }
    
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }
    public String getItemName() {
        return this.itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getAttachedStatus() {
        return this.attachedStatus;
    }
    
    public void setAttachedStatus(String attachedStatus) {
        this.attachedStatus = attachedStatus;
    }
    public String getAttachedDate() {
        return this.attachedDate;
    }
    
    public void setAttachedDate(String attachedDate) {
        this.attachedDate = attachedDate;
    }
    public String getContractPriceStartDate() {
        return this.contractPriceStartDate;
    }
    
    public void setContractPriceStartDate(String contractPriceStartDate) {
        this.contractPriceStartDate = contractPriceStartDate;
    }
    public String getContractPriceEndDate() {
        return this.contractPriceEndDate;
    }
    
    public void setContractPriceEndDate(String contractPriceEndDate) {
        this.contractPriceEndDate = contractPriceEndDate;
    }
    public String getPriceType() {
        return this.priceType;
    }
    
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }
    public String getContractPrice() {
        return this.contractPrice;
    }
    
    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }
    public String getPriceRevision() {
        return this.priceRevision;
    }
    
    public void setPriceRevision(String priceRevision) {
        this.priceRevision = priceRevision;
    }
    public String getRevisionDate() {
        return this.revisionDate;
    }
    
    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }
    public String getPriceProtectionStartDate() {
        return this.priceProtectionStartDate;
    }
    
    public void setPriceProtectionStartDate(String priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
    }
    public String getPriceProtectionEndDate() {
        return this.priceProtectionEndDate;
    }
    
    public void setPriceProtectionEndDate(String priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate;
    }
    public String getPriceToleranceType() {
        return this.priceToleranceType;
    }
    
    public void setPriceToleranceType(String priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }
    public String getPriceTolerance() {
        return this.priceTolerance;
    }
    
    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }
    public String getBasePrice() {
        return this.basePrice;
    }
    
    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }
    public String getPriceToleranceFrequency() {
        return this.priceToleranceFrequency;
    }
    
    public void setPriceToleranceFrequency(String priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }
    public String getPriceToleranceInterval() {
        return this.priceToleranceInterval;
    }
    
    public void setPriceToleranceInterval(String priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }
    public String getPricePlanId() {
        return this.pricePlanId;
    }
    
    public void setPricePlanId(String pricePlanId) {
        this.pricePlanId = pricePlanId;
    }
    public String getPricePlanName() {
        return this.pricePlanName;
    }
    
    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }
    public String getSuggestedPrice() {
        return this.suggestedPrice;
    }
    
    public void setSuggestedPrice(String suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getCfpId() {
        return this.cfpId;
    }
    
    public void setCfpId(String cfpId) {
        this.cfpId = cfpId;
    }
    public String getMfpId() {
        return this.mfpId;
    }
    
    public void setMfpId(String mfpId) {
        this.mfpId = mfpId;
    }
    public String getIfpId() {
        return this.ifpId;
    }
    
    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }
    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
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
    public String getAddChgDelIndicator() {
        return this.addChgDelIndicator;
    }
    
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        this.addChgDelIndicator = addChgDelIndicator;
    }
    public String getReasonForFailure() {
        return this.reasonForFailure;
    }
    
    public void setReasonForFailure(String reasonForFailure) {
        this.reasonForFailure = reasonForFailure;
    }
    public Date getIntfInsertedDate() {
        return this.intfInsertedDate;
    }
    
    public void setIntfInsertedDate(Date intfInsertedDate) {
        this.intfInsertedDate = intfInsertedDate;
    }
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorField() {
        return this.errorField;
    }
    
    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }
    public boolean getCheckRecord() {
        return this.checkRecord;
    }
    
    public void setCheckRecord(boolean checkRecord) {
        this.checkRecord = checkRecord;
    }
    public String getReprocessedFlag() {
        return this.reprocessedFlag;
    }
    
    public void setReprocessedFlag(String reprocessedFlag) {
        this.reprocessedFlag = reprocessedFlag;
    }




}


