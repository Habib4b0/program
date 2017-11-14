package com.stpl.gtn.gtn2o.ws.entity.transaction;


import java.util.Date;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;

@SuppressWarnings("serial")
public class CfpModel  implements java.io.Serializable {


     private int cfpModelSid;
     private HelperTable helperTableByCfpStatus;
     private HelperTable helperTableByCfpTradeClass;
     private HelperTable helperTableByCfpType;
     private HelperTable helperTableByCfpCategory;
     private String cfpId;
     private String cfpNo;
     private String cfpName;
     private String cfpDesignation;
     private Date cfpStartDate;
     private Date cfpEndDate;
     private Integer parentCfpId;
     private String parentCfpName;
     private String internalNotes;
     private char inboundStatus;
     private boolean recordLockStatus;
     private String batchId;
     private String source;
	private String createdBy;
     private Date createdDate;
	private String modifiedBy;
     private Date modifiedDate;
     private Integer salesInclusion;

    public CfpModel() {
    }

	
	public CfpModel(int cfpModelSid, HelperTable helperTableByCfpStatus, String cfpId, String cfpNo, String cfpName,
			Date cfpStartDate, char inboundStatus, boolean recordLockStatus, String createdBy, Date createdDate,
			String modifiedBy, Date modifiedDate) {
        this.cfpModelSid = cfpModelSid;
        this.helperTableByCfpStatus = helperTableByCfpStatus;
        this.cfpId = cfpId;
        this.cfpNo = cfpNo;
        this.cfpName = cfpName;
        this.cfpStartDate = cfpStartDate;
        this.inboundStatus = inboundStatus;
        this.recordLockStatus = recordLockStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

	public CfpModel(int cfpModelSid, HelperTable helperTableByCfpStatus, HelperTable helperTableByCfpTradeClass,
			HelperTable helperTableByCfpType, HelperTable helperTableByCfpCategory, String cfpId, String cfpNo,
			String cfpName, String cfpDesignation, Date cfpStartDate, Date cfpEndDate, Integer parentCfpId,
			String parentCfpName, String internalNotes, char inboundStatus, boolean recordLockStatus, String batchId,
			String source, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate,
			Integer salesInclusion) {
       this.cfpModelSid = cfpModelSid;
       this.helperTableByCfpStatus = helperTableByCfpStatus;
       this.helperTableByCfpTradeClass = helperTableByCfpTradeClass;
       this.helperTableByCfpType = helperTableByCfpType;
       this.helperTableByCfpCategory = helperTableByCfpCategory;
       this.cfpId = cfpId;
       this.cfpNo = cfpNo;
       this.cfpName = cfpName;
       this.cfpDesignation = cfpDesignation;
       this.cfpStartDate = cfpStartDate;
       this.cfpEndDate = cfpEndDate;
       this.parentCfpId = parentCfpId;
       this.parentCfpName = parentCfpName;
       this.internalNotes = internalNotes;
       this.inboundStatus = inboundStatus;
       this.recordLockStatus = recordLockStatus;
       this.batchId = batchId;
       this.source = source;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
       this.salesInclusion = salesInclusion;
    }
   
    public int getCfpModelSid() {
        return this.cfpModelSid;
    }
    
    public void setCfpModelSid(int cfpModelSid) {
        this.cfpModelSid = cfpModelSid;
    }
    public HelperTable getHelperTableByCfpStatus() {
        return this.helperTableByCfpStatus;
    }
    
    public void setHelperTableByCfpStatus(HelperTable helperTableByCfpStatus) {
        this.helperTableByCfpStatus = helperTableByCfpStatus;
    }
    public HelperTable getHelperTableByCfpTradeClass() {
        return this.helperTableByCfpTradeClass;
    }
    
    public void setHelperTableByCfpTradeClass(HelperTable helperTableByCfpTradeClass) {
        this.helperTableByCfpTradeClass = helperTableByCfpTradeClass;
    }
    public HelperTable getHelperTableByCfpType() {
        return this.helperTableByCfpType;
    }
    
    public void setHelperTableByCfpType(HelperTable helperTableByCfpType) {
        this.helperTableByCfpType = helperTableByCfpType;
    }
    public HelperTable getHelperTableByCfpCategory() {
        return this.helperTableByCfpCategory;
    }
    
    public void setHelperTableByCfpCategory(HelperTable helperTableByCfpCategory) {
        this.helperTableByCfpCategory = helperTableByCfpCategory;
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
    public String getCfpDesignation() {
        return this.cfpDesignation;
    }
    
    public void setCfpDesignation(String cfpDesignation) {
        this.cfpDesignation = cfpDesignation;
    }
    public Date getCfpStartDate() {
        return this.cfpStartDate;
    }
    
    public void setCfpStartDate(Date cfpStartDate) {
        this.cfpStartDate = cfpStartDate;
    }
    public Date getCfpEndDate() {
        return this.cfpEndDate;
    }
    
    public void setCfpEndDate(Date cfpEndDate) {
        this.cfpEndDate = cfpEndDate;
    }
    public Integer getParentCfpId() {
        return this.parentCfpId;
    }
    
    public void setParentCfpId(Integer parentCfpId) {
        this.parentCfpId = parentCfpId;
    }
    public String getParentCfpName() {
        return this.parentCfpName;
    }
    
    public void setParentCfpName(String parentCfpName) {
        this.parentCfpName = parentCfpName;
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
    public Integer getSalesInclusion() {
        return this.salesInclusion;
    }
    
    public void setSalesInclusion(Integer salesInclusion) {
        this.salesInclusion = salesInclusion;
    }




}


