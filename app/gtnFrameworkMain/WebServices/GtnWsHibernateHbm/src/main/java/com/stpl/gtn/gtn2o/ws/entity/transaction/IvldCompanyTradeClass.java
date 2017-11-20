package com.stpl.gtn.gtn2o.ws.entity.transaction;
// Generated May 8, 2017 5:24:12 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * IvldCompanyTradeClass generated by hbm2java
 */
@SuppressWarnings("serial")
public class IvldCompanyTradeClass  implements java.io.Serializable {


     private int ivldCompanyTradeClassSid;
     private String tradeClassIntfid;
     private String companyId;
     private String tradeClassStartDate;
     private String tradeClassEndDate;
     private String tradeClass;
     private String priorTradeClass;
     private String priorTradeClassStartDate;
     private String lastUpdatedDate;
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
     private String reprocessedFlag;
     private boolean checkRecord;

    public IvldCompanyTradeClass() {
    }

	
    public IvldCompanyTradeClass(int ivldCompanyTradeClassSid, String tradeClassIntfid) {
        this.ivldCompanyTradeClassSid = ivldCompanyTradeClassSid;
        this.tradeClassIntfid = tradeClassIntfid;
    }
    public IvldCompanyTradeClass(int ivldCompanyTradeClassSid, String tradeClassIntfid, String companyId, String tradeClassStartDate, String tradeClassEndDate, String tradeClass, String priorTradeClass, String priorTradeClassStartDate, String lastUpdatedDate, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String batchId, String source, String addChgDelIndicator, String reasonForFailure, Date intfInsertedDate, String errorCode, String errorField, String reprocessedFlag, boolean checkRecord) {
       this.ivldCompanyTradeClassSid = ivldCompanyTradeClassSid;
       this.tradeClassIntfid = tradeClassIntfid;
       this.companyId = companyId;
       this.tradeClassStartDate = tradeClassStartDate;
       this.tradeClassEndDate = tradeClassEndDate;
       this.tradeClass = tradeClass;
       this.priorTradeClass = priorTradeClass;
       this.priorTradeClassStartDate = priorTradeClassStartDate;
       this.lastUpdatedDate = lastUpdatedDate;
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
       this.reprocessedFlag = reprocessedFlag;
       this.checkRecord = checkRecord;
    }
   
    public int getIvldCompanyTradeClassSid() {
        return this.ivldCompanyTradeClassSid;
    }
    
    public void setIvldCompanyTradeClassSid(int ivldCompanyTradeClassSid) {
        this.ivldCompanyTradeClassSid = ivldCompanyTradeClassSid;
    }
    public String getTradeClassIntfid() {
        return this.tradeClassIntfid;
    }
    
    public void setTradeClassIntfid(String tradeClassIntfid) {
        this.tradeClassIntfid = tradeClassIntfid;
    }
    public String getCompanyId() {
        return this.companyId;
    }
    
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
    public String getTradeClass() {
        return this.tradeClass;
    }
    
    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }
    public String getPriorTradeClass() {
        return this.priorTradeClass;
    }
    
    public void setPriorTradeClass(String priorTradeClass) {
        this.priorTradeClass = priorTradeClass;
    }
    public String getPriorTradeClassStartDate() {
        return this.priorTradeClassStartDate;
    }
    
    public void setPriorTradeClassStartDate(String priorTradeClassStartDate) {
        this.priorTradeClassStartDate = priorTradeClassStartDate;
    }
    public String getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    
    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
    public String getReprocessedFlag() {
        return this.reprocessedFlag;
    }
    
    public void setReprocessedFlag(String reprocessedFlag) {
        this.reprocessedFlag = reprocessedFlag;
    }
    public boolean getCheckRecord() {
        return this.checkRecord;
    }
    
    public void setCheckRecord(boolean checkRecord) {
        this.checkRecord = checkRecord;
    }




}


