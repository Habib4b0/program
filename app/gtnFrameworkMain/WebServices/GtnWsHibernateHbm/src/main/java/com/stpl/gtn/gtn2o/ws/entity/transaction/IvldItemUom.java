package com.stpl.gtn.gtn2o.ws.entity.transaction;
// Generated Feb 6, 2018 3:06:35 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * IvldItemUom generated by hbm2java
 */
public class IvldItemUom  implements java.io.Serializable {


     private int ivldItemUomSid;
     private Double itemUomConversionId;
     private String itemId;
     private String itemNo;
     private String itemName;
     private String primaryUomBaselineFactor;
     private String primaryUomCode;
     private String primaryUomName;
     private String secondaryUomConversionFactor;
     private String secondaryUomCode;
     private String secondaryUomName;
     private String status;
     private Date uploadDate;
     private String modifiedBy;
     private Date modifiedDate;
     private String createdBy;
     private Date createdDate;
     private String addChgDelIndicator;
     private String batchId;
     private String source;
     private String reasonForFailure;
     private Date intfInsertedDate;
     private String errorCode;
     private String errorField;
     private String reprocessedFlag;
     private Boolean checkRecord;

    public IvldItemUom() {
    }

	
    public IvldItemUom(int ivldItemUomSid, Double itemUomConversionId) {
        this.ivldItemUomSid = ivldItemUomSid;
        this.itemUomConversionId = itemUomConversionId;
    }
    public IvldItemUom(int ivldItemUomSid, Double itemUomConversionId, String itemId, String itemNo, String itemName, String primaryUomBaselineFactor, String primaryUomCode, String primaryUomName, String secondaryUomConversionFactor, String secondaryUomCode, String secondaryUomName, String status, Date uploadDate, String modifiedBy, Date modifiedDate, String createdBy, Date createdDate, String addChgDelIndicator, String batchId, String source, String reasonForFailure, Date intfInsertedDate, String errorCode, String errorField, String reprocessedFlag, Boolean checkRecord) {
       this.ivldItemUomSid = ivldItemUomSid;
       this.itemUomConversionId = itemUomConversionId;
       this.itemId = itemId;
       this.itemNo = itemNo;
       this.itemName = itemName;
       this.primaryUomBaselineFactor = primaryUomBaselineFactor;
       this.primaryUomCode = primaryUomCode;
       this.primaryUomName = primaryUomName;
       this.secondaryUomConversionFactor = secondaryUomConversionFactor;
       this.secondaryUomCode = secondaryUomCode;
       this.secondaryUomName = secondaryUomName;
       this.status = status;
       this.uploadDate = uploadDate;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.addChgDelIndicator = addChgDelIndicator;
       this.batchId = batchId;
       this.source = source;
       this.reasonForFailure = reasonForFailure;
       this.intfInsertedDate = intfInsertedDate;
       this.errorCode = errorCode;
       this.errorField = errorField;
       this.reprocessedFlag = reprocessedFlag;
       this.checkRecord = checkRecord;
    }
   
    public int getIvldItemUomSid() {
        return this.ivldItemUomSid;
    }
    
    public void setIvldItemUomSid(int ivldItemUomSid) {
        this.ivldItemUomSid = ivldItemUomSid;
    }
    public Double getItemUomConversionId() {
        return this.itemUomConversionId;
    }
    
    public void setItemUomConversionId(Double itemUomConversionId) {
        this.itemUomConversionId = itemUomConversionId;
    }
    public String getItemId() {
        return this.itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
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
    public String getPrimaryUomBaselineFactor() {
        return this.primaryUomBaselineFactor;
    }
    
    public void setPrimaryUomBaselineFactor(String primaryUomBaselineFactor) {
        this.primaryUomBaselineFactor = primaryUomBaselineFactor;
    }
    public String getPrimaryUomCode() {
        return this.primaryUomCode;
    }
    
    public void setPrimaryUomCode(String primaryUomCode) {
        this.primaryUomCode = primaryUomCode;
    }
    public String getPrimaryUomName() {
        return this.primaryUomName;
    }
    
    public void setPrimaryUomName(String primaryUomName) {
        this.primaryUomName = primaryUomName;
    }
    public String getSecondaryUomConversionFactor() {
        return this.secondaryUomConversionFactor;
    }
    
    public void setSecondaryUomConversionFactor(String secondaryUomConversionFactor) {
        this.secondaryUomConversionFactor = secondaryUomConversionFactor;
    }
    public String getSecondaryUomCode() {
        return this.secondaryUomCode;
    }
    
    public void setSecondaryUomCode(String secondaryUomCode) {
        this.secondaryUomCode = secondaryUomCode;
    }
    public String getSecondaryUomName() {
        return this.secondaryUomName;
    }
    
    public void setSecondaryUomName(String secondaryUomName) {
        this.secondaryUomName = secondaryUomName;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getUploadDate() {
        return this.uploadDate;
    }
    
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
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
    public String getAddChgDelIndicator() {
        return this.addChgDelIndicator;
    }
    
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        this.addChgDelIndicator = addChgDelIndicator;
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
    public String getReprocessedFlag() {
        return this.reprocessedFlag;
    }
    
    public void setReprocessedFlag(String reprocessedFlag) {
        this.reprocessedFlag = reprocessedFlag;
    }
    public Boolean getCheckRecord() {
        return this.checkRecord;
    }
    
    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }




}


