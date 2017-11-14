package com.stpl.gtn.gtn2o.ws.entity.transaction;


import java.util.Date;

@SuppressWarnings("serial")
public class StagCfp  implements java.io.Serializable {


     private int stagCfpSid;
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

    public StagCfp() {
    }

	
    public StagCfp(int stagCfpSid, String cfpIntfid, String cfpId, String cfpStatus, String cfpStartDate, String tradingPartnerId, String startDate, String contractId, String attachedStatus, String attachedDate, String batchId, String addChgDelIndicator) {
        this.stagCfpSid = stagCfpSid;
        this.cfpIntfid = cfpIntfid;
        this.cfpId = cfpId;
        this.cfpStatus = cfpStatus;
        this.cfpStartDate = cfpStartDate;
        this.tradingPartnerId = tradingPartnerId;
        this.startDate = startDate;
        this.contractId = contractId;
        this.attachedStatus = attachedStatus;
        this.attachedDate = attachedDate;
        this.batchId = batchId;
        this.addChgDelIndicator = addChgDelIndicator;
    }
    public StagCfp(int stagCfpSid, String cfpIntfid, String cfpId, String cfpNo, String cfpName, String cfpType, String cfpCategory, String cfpDesignation, String parentCfpId, String parentCfpName, String cfpStatus, String cfpTradeClass, String cfpStartDate, String cfpEndDate, String tradingPartnerId, String identifierCodeQualifier, String tradingPartnerNo, String tradingPartnerName, String tradingPartnerContractNo, String startDate, String endDate, String contractId, String attachedStatus, String attachedDate, String tradeClass, String tradeClassStartDate, String tradeClassEndDate, String internalNotes, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String batchId, String source, String addChgDelIndicator) {
       this.stagCfpSid = stagCfpSid;
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
    }
   
    public int getStagCfpSid() {
        return this.stagCfpSid;
    }
    
    public void setStagCfpSid(int stagCfpSid) {
        this.stagCfpSid = stagCfpSid;
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
    
    public String getTradeClass() {
        return this.tradeClass;
    }
    
    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getCfpStartDate() {
        return cfpStartDate;
    }

    public void setCfpStartDate(String cfpStartDate) {
        this.cfpStartDate = cfpStartDate;
    }

    public String getCfpEndDate() {
        return cfpEndDate;
    }

    public void setCfpEndDate(String cfpEndDate) {
        this.cfpEndDate = cfpEndDate;
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

    public String getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(String attachedDate) {
        this.attachedDate = attachedDate;
    }

    public String getTradeClassStartDate() {
        return tradeClassStartDate;
    }

    public void setTradeClassStartDate(String tradeClassStartDate) {
        this.tradeClassStartDate = tradeClassStartDate;
    }

    public String getTradeClassEndDate() {
        return tradeClassEndDate;
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




}


