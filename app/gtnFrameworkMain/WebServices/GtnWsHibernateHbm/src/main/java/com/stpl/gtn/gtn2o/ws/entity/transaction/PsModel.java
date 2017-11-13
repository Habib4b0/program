package com.stpl.gtn.gtn2o.ws.entity.transaction;


import java.util.Date;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;

@SuppressWarnings("serial")
public class PsModel  implements java.io.Serializable {


     private int psModelSid;
     private HelperTable helperTableByPsStatus;
     private HelperTable helperTableByPsDesignation;
     private HelperTable helperTableByPsCategory;
     private HelperTable helperTableByPsTradeClass;
     private HelperTable helperTableByPsType;
     private String priceScheduleId;
     private String priceScheduleNo;
     private String priceScheduleName;
     private Date priceScheduleStartDate;
     private Date priceScheduleEndDate;
     private String parentPriceScheduleId;
     private String parentPriceScheduleName;
     private String internalNotes;
     private char inboundStatus;
     private boolean recordLockStatus;
     private String batchId;
     private String source;
     private String createdBy;
     private Date createdDate;
     private String modifiedBy;
     private Date modifiedDate;

    public PsModel() {
    }

	
    public PsModel(int psModelSid, String priceScheduleId, Date priceScheduleStartDate, char inboundStatus, boolean recordLockStatus, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
        this.psModelSid = psModelSid;
        this.priceScheduleId = priceScheduleId;
        this.priceScheduleStartDate = priceScheduleStartDate;
        this.inboundStatus = inboundStatus;
        this.recordLockStatus = recordLockStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }
    public PsModel(int psModelSid, HelperTable helperTableByPsStatus, HelperTable helperTableByPsDesignation, HelperTable helperTableByPsCategory, HelperTable helperTableByPsTradeClass, HelperTable helperTableByPsType, String priceScheduleId, String priceScheduleNo, String priceScheduleName, Date priceScheduleStartDate, Date priceScheduleEndDate, String parentPriceScheduleId, String parentPriceScheduleName, String internalNotes, char inboundStatus, boolean recordLockStatus, String batchId, String source, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
       this.psModelSid = psModelSid;
       this.helperTableByPsStatus = helperTableByPsStatus;
       this.helperTableByPsDesignation = helperTableByPsDesignation;
       this.helperTableByPsCategory = helperTableByPsCategory;
       this.helperTableByPsTradeClass = helperTableByPsTradeClass;
       this.helperTableByPsType = helperTableByPsType;
       this.priceScheduleId = priceScheduleId;
       this.priceScheduleNo = priceScheduleNo;
       this.priceScheduleName = priceScheduleName;
       this.priceScheduleStartDate = priceScheduleStartDate;
       this.priceScheduleEndDate = priceScheduleEndDate;
       this.parentPriceScheduleId = parentPriceScheduleId;
       this.parentPriceScheduleName = parentPriceScheduleName;
       this.internalNotes = internalNotes;
       this.inboundStatus = inboundStatus;
       this.recordLockStatus = recordLockStatus;
       this.batchId = batchId;
       this.source = source;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
    }
   
    public int getPsModelSid() {
        return this.psModelSid;
    }
    
    public void setPsModelSid(int psModelSid) {
        this.psModelSid = psModelSid;
    }
    public HelperTable getHelperTableByPsStatus() {
        return this.helperTableByPsStatus;
    }
    
    public void setHelperTableByPsStatus(HelperTable helperTableByPsStatus) {
        this.helperTableByPsStatus = helperTableByPsStatus;
    }
    public HelperTable getHelperTableByPsDesignation() {
        return this.helperTableByPsDesignation;
    }
    
    public void setHelperTableByPsDesignation(HelperTable helperTableByPsDesignation) {
        this.helperTableByPsDesignation = helperTableByPsDesignation;
    }
    public HelperTable getHelperTableByPsCategory() {
        return this.helperTableByPsCategory;
    }
    
    public void setHelperTableByPsCategory(HelperTable helperTableByPsCategory) {
        this.helperTableByPsCategory = helperTableByPsCategory;
    }
    public HelperTable getHelperTableByPsTradeClass() {
        return this.helperTableByPsTradeClass;
    }
    
    public void setHelperTableByPsTradeClass(HelperTable helperTableByPsTradeClass) {
        this.helperTableByPsTradeClass = helperTableByPsTradeClass;
    }
    public HelperTable getHelperTableByPsType() {
        return this.helperTableByPsType;
    }
    
    public void setHelperTableByPsType(HelperTable helperTableByPsType) {
        this.helperTableByPsType = helperTableByPsType;
    }
   
    public String getInternalNotes() {
        return this.internalNotes;
    }
    
    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
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
    
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getPriceScheduleId() {
        return priceScheduleId;
    }

    public void setPriceScheduleId(String priceScheduleId) {
        this.priceScheduleId = priceScheduleId;
    }

    public String getPriceScheduleNo() {
        return priceScheduleNo;
    }

    public void setPriceScheduleNo(String priceScheduleNo) {
        this.priceScheduleNo = priceScheduleNo;
    }

    public String getPriceScheduleName() {
        return priceScheduleName;
    }

    public void setPriceScheduleName(String priceScheduleName) {
        this.priceScheduleName = priceScheduleName;
    }

    public Date getPriceScheduleStartDate() {
        return priceScheduleStartDate;
    }

    public void setPriceScheduleStartDate(Date priceScheduleStartDate) {
        this.priceScheduleStartDate = priceScheduleStartDate;
    }

    public Date getPriceScheduleEndDate() {
        return priceScheduleEndDate;
    }

    public void setPriceScheduleEndDate(Date priceScheduleEndDate) {
        this.priceScheduleEndDate = priceScheduleEndDate;
    }

    public String getParentPriceScheduleId() {
        return parentPriceScheduleId;
    }

    public void setParentPriceScheduleId(String parentPriceScheduleId) {
        this.parentPriceScheduleId = parentPriceScheduleId;
    }

    public String getParentPriceScheduleName() {
        return parentPriceScheduleName;
    }

    public void setParentPriceScheduleName(String parentPriceScheduleName) {
        this.parentPriceScheduleName = parentPriceScheduleName;
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
   
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }




}


