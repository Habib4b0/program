package com.stpl.gtn.gtn2o.ws.entity.transaction;


import java.util.Date;

@SuppressWarnings("serial")
public class IvldCfp  implements java.io.Serializable {


     private int ivldCfpSid;
     private String cfpIntfid;
     private String cfpId;
     private String cfpNo;
     private String cfpName;
     private String cfpType;
     private String cfpCategory;
     private String cfpDesignation;
     private String parentCfpId;
     private String parentCfpName;
     private String cfpStatus;
     private String cfpTradeClass;
     private String cfpStartDate;
     private String cfpEndDate;
     private String tradingPartnerId;
     private String identifierCodeQualifier;
     private String tradingPartnerNo;
     private String tradingPartnerName;
     private String tradingPartnerContractNo;
     private String startDate;
     private String endDate;
     private String contractId;
     private String attachedStatus;
     private String attachedDate;
     private String tradeClass;
     private String tradeClassStartDate;
     private String tradeClassEndDate;
     private String internalNotes;
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

    public IvldCfp() {
    }

	
    public IvldCfp(int ivldCfpSid, String cfpIntfid) {
        this.ivldCfpSid = ivldCfpSid;
        this.cfpIntfid = cfpIntfid;
    }
    public IvldCfp(int ivldCfpSid, String cfpIntfid, String cfpId, String cfpNo, String cfpName, String cfpType, String cfpCategory, String cfpDesignation, String parentCfpId, String parentCfpName, String cfpStatus, String cfpTradeClass, String cfpStartDate, String cfpEndDate, String tradingPartnerId, String identifierCodeQualifier, String tradingPartnerNo, String tradingPartnerName, String tradingPartnerContractNo, String startDate, String endDate, String contractId, String attachedStatus, String attachedDate, String tradeClass, String tradeClassStartDate, String tradeClassEndDate, String internalNotes, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String batchId, String source, String addChgDelIndicator, String reasonForFailure, Date intfInsertedDate, String errorCode, String errorField, boolean checkRecord, String reprocessedFlag) {
       this.ivldCfpSid = ivldCfpSid;
       this.cfpIntfid = cfpIntfid;
       this.cfpId = cfpId;
       this.cfpNo = cfpNo;
       this.cfpName = cfpName;
       this.cfpType = cfpType;
       this.cfpCategory = cfpCategory;
       this.cfpDesignation = cfpDesignation;
       this.parentCfpId = parentCfpId;
       this.parentCfpName = parentCfpName;
       this.cfpStatus = cfpStatus;
       this.cfpTradeClass = cfpTradeClass;
       this.cfpStartDate = cfpStartDate;
       this.cfpEndDate = cfpEndDate;
       this.tradingPartnerId = tradingPartnerId;
       this.identifierCodeQualifier = identifierCodeQualifier;
       this.tradingPartnerNo = tradingPartnerNo;
       this.tradingPartnerName = tradingPartnerName;
       this.tradingPartnerContractNo = tradingPartnerContractNo;
       this.startDate = startDate;
       this.endDate = endDate;
       this.contractId = contractId;
       this.attachedStatus = attachedStatus;
       this.attachedDate = attachedDate;
       this.tradeClass = tradeClass;
       this.tradeClassStartDate = tradeClassStartDate;
       this.tradeClassEndDate = tradeClassEndDate;
       this.internalNotes = internalNotes;
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
   
    public int getIvldCfpSid() {
        return this.ivldCfpSid;
    }
    
    public void setIvldCfpSid(int ivldCfpSid) {
        this.ivldCfpSid = ivldCfpSid;
    }
    public String getCfpIntfid() {
        return this.cfpIntfid;
    }
    
    public void setCfpIntfid(String cfpIntfid) {
        this.cfpIntfid = cfpIntfid;
    }
    public String getCfpId() {
        return this.cfpId;
    }
    
    public void setCfpId(String cfpId) {
        this.cfpId = cfpId;
    }
    public String getCfpNo() {
        return this.cfpNo;
    }
    
    public void setCfpNo(String cfpNo) {
        this.cfpNo = cfpNo;
    }
    public String getCfpName() {
        return this.cfpName;
    }
    
    public void setCfpName(String cfpName) {
        this.cfpName = cfpName;
    }
    public String getCfpType() {
        return this.cfpType;
    }
    
    public void setCfpType(String cfpType) {
        this.cfpType = cfpType;
    }
    public String getCfpCategory() {
        return this.cfpCategory;
    }
    
    public void setCfpCategory(String cfpCategory) {
        this.cfpCategory = cfpCategory;
    }
    public String getCfpDesignation() {
        return this.cfpDesignation;
    }
    
    public void setCfpDesignation(String cfpDesignation) {
        this.cfpDesignation = cfpDesignation;
    }
    public String getParentCfpId() {
        return this.parentCfpId;
    }
    
    public void setParentCfpId(String parentCfpId) {
        this.parentCfpId = parentCfpId;
    }
    public String getParentCfpName() {
        return this.parentCfpName;
    }
    
    public void setParentCfpName(String parentCfpName) {
        this.parentCfpName = parentCfpName;
    }
    public String getCfpStatus() {
        return this.cfpStatus;
    }
    
    public void setCfpStatus(String cfpStatus) {
        this.cfpStatus = cfpStatus;
    }
    public String getCfpTradeClass() {
        return this.cfpTradeClass;
    }
    
    public void setCfpTradeClass(String cfpTradeClass) {
        this.cfpTradeClass = cfpTradeClass;
    }
    public String getCfpStartDate() {
        return this.cfpStartDate;
    }
    
    public void setCfpStartDate(String cfpStartDate) {
        this.cfpStartDate = cfpStartDate;
    }
    public String getCfpEndDate() {
        return this.cfpEndDate;
    }
    
    public void setCfpEndDate(String cfpEndDate) {
        this.cfpEndDate = cfpEndDate;
    }
    public String getTradingPartnerId() {
        return this.tradingPartnerId;
    }
    
    public void setTradingPartnerId(String tradingPartnerId) {
        this.tradingPartnerId = tradingPartnerId;
    }
    public String getIdentifierCodeQualifier() {
        return this.identifierCodeQualifier;
    }
    
    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        this.identifierCodeQualifier = identifierCodeQualifier;
    }
    public String getTradingPartnerNo() {
        return this.tradingPartnerNo;
    }
    
    public void setTradingPartnerNo(String tradingPartnerNo) {
        this.tradingPartnerNo = tradingPartnerNo;
    }
    public String getTradingPartnerName() {
        return this.tradingPartnerName;
    }
    
    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }
    public String getTradingPartnerContractNo() {
        return this.tradingPartnerContractNo;
    }
    
    public void setTradingPartnerContractNo(String tradingPartnerContractNo) {
        this.tradingPartnerContractNo = tradingPartnerContractNo;
    }
    public String getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
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
    public String getTradeClass() {
        return this.tradeClass;
    }
    
    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }
    public String getTradeClassStartDate() {
        return this.tradeClassStartDate;
    }
    
    public void setTradeClassStartDate(String tradeClassStartDate) {
        this.tradeClassStartDate = tradeClassStartDate;
    }
    public String getTradeClassEndDate() {
        return this.tradeClassEndDate;
    }
    
    public void setTradeClassEndDate(String tradeClassEndDate) {
        this.tradeClassEndDate = tradeClassEndDate;
    }
    public String getInternalNotes() {
        return this.internalNotes;
    }
    
    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
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


