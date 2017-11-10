package com.stpl.gtn.gtn2o.ws.entity.transaction;


import java.util.Date;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;

@SuppressWarnings("serial")
public class IfpModel  implements java.io.Serializable {


     private int ifpModelSid;
	private HelperTable helperTableByIfpType;
	private HelperTable helperTableByIfpDesignation;
	private HelperTable helperTableByIfpStatus;
	private HelperTable helperTableByIfpCategory;
     private String ifpId;
     private String ifpNo;
     private String ifpName;
     private Date ifpStartDate;
     private Date ifpEndDate;
     private String parentIfpId;
     private String parentIfpName;
     private String totalVolumeCommitment;
     private String totalDollarCommitment;
     private String totalMarketshareCommitment;
     private String commitmentPeriod;
     private String internalNotes;
     private char inboundStatus;
     private boolean recordLockStatus;
     private String batchId;
     private String source;
	private String createdBy;
     private Date createdDate;
	private String modifiedBy;
     private Date modifiedDate;

    public IfpModel() {
    }

	
	public IfpModel(int ifpModelSid, HelperTable helperTableByIfpStatus, String ifpId, String ifpNo, String ifpName,
			Date ifpStartDate, char inboundStatus, boolean recordLockStatus, String createdBy, Date createdDate,
			String modifiedBy, Date modifiedDate) {
        this.ifpModelSid = ifpModelSid;
        this.helperTableByIfpStatus = helperTableByIfpStatus;
        this.ifpId = ifpId;
        this.ifpNo = ifpNo;
        this.ifpName = ifpName;
        this.ifpStartDate = ifpStartDate;
        this.inboundStatus = inboundStatus;
        this.recordLockStatus = recordLockStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

	public IfpModel(int ifpModelSid, HelperTable helperTableByIfpType, HelperTable helperTableByIfpDesignation,
			HelperTable helperTableByIfpStatus, HelperTable helperTableByIfpCategory, String ifpId, String ifpNo,
			String ifpName, Date ifpStartDate, Date ifpEndDate, String parentIfpId, String parentIfpName,
			String totalVolumeCommitment, String totalDollarCommitment, String totalMarketshareCommitment,
			String commitmentPeriod, String internalNotes, char inboundStatus, boolean recordLockStatus, String batchId,
			String source, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
       this.ifpModelSid = ifpModelSid;
       this.helperTableByIfpType = helperTableByIfpType;
       this.helperTableByIfpDesignation = helperTableByIfpDesignation;
       this.helperTableByIfpStatus = helperTableByIfpStatus;
       this.helperTableByIfpCategory = helperTableByIfpCategory;
       this.ifpId = ifpId;
       this.ifpNo = ifpNo;
       this.ifpName = ifpName;
       this.ifpStartDate = ifpStartDate;
       this.ifpEndDate = ifpEndDate;
       this.parentIfpId = parentIfpId;
       this.parentIfpName = parentIfpName;
       this.totalVolumeCommitment = totalVolumeCommitment;
       this.totalDollarCommitment = totalDollarCommitment;
       this.totalMarketshareCommitment = totalMarketshareCommitment;
       this.commitmentPeriod = commitmentPeriod;
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
   
    public int getIfpModelSid() {
        return this.ifpModelSid;
    }
    
    public void setIfpModelSid(int ifpModelSid) {
        this.ifpModelSid = ifpModelSid;
    }
    public HelperTable getHelperTableByIfpType() {
        return this.helperTableByIfpType;
    }
    
    public void setHelperTableByIfpType(HelperTable helperTableByIfpType) {
        this.helperTableByIfpType = helperTableByIfpType;
    }
    public HelperTable getHelperTableByIfpDesignation() {
        return this.helperTableByIfpDesignation;
    }
    
    public void setHelperTableByIfpDesignation(HelperTable helperTableByIfpDesignation) {
        this.helperTableByIfpDesignation = helperTableByIfpDesignation;
    }
    public HelperTable getHelperTableByIfpStatus() {
        return this.helperTableByIfpStatus;
    }
    
    public void setHelperTableByIfpStatus(HelperTable helperTableByIfpStatus) {
        this.helperTableByIfpStatus = helperTableByIfpStatus;
    }
    public HelperTable getHelperTableByIfpCategory() {
        return this.helperTableByIfpCategory;
    }
    
    public void setHelperTableByIfpCategory(HelperTable helperTableByIfpCategory) {
        this.helperTableByIfpCategory = helperTableByIfpCategory;
    }
    public String getIfpId() {
        return this.ifpId;
    }
    
    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }
    public String getIfpNo() {
        return this.ifpNo;
    }
    
    public void setIfpNo(String ifpNo) {
        this.ifpNo = ifpNo;
    }
    public String getIfpName() {
        return this.ifpName;
    }
    
    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }
    public Date getIfpStartDate() {
        return this.ifpStartDate;
    }
    
    public void setIfpStartDate(Date ifpStartDate) {
        this.ifpStartDate = ifpStartDate;
    }
    public Date getIfpEndDate() {
        return this.ifpEndDate;
    }
    
    public void setIfpEndDate(Date ifpEndDate) {
        this.ifpEndDate = ifpEndDate;
    }
    public String getParentIfpId() {
        return this.parentIfpId;
    }
    
    public void setParentIfpId(String parentIfpId) {
        this.parentIfpId = parentIfpId;
    }
    public String getParentIfpName() {
        return this.parentIfpName;
    }
    
    public void setParentIfpName(String parentIfpName) {
        this.parentIfpName = parentIfpName;
    }
    public String getTotalVolumeCommitment() {
        return this.totalVolumeCommitment;
    }
    
    public void setTotalVolumeCommitment(String totalVolumeCommitment) {
        this.totalVolumeCommitment = totalVolumeCommitment;
    }
    public String getTotalDollarCommitment() {
        return this.totalDollarCommitment;
    }
    
    public void setTotalDollarCommitment(String totalDollarCommitment) {
        this.totalDollarCommitment = totalDollarCommitment;
    }
    public String getTotalMarketshareCommitment() {
        return this.totalMarketshareCommitment;
    }
    
    public void setTotalMarketshareCommitment(String totalMarketshareCommitment) {
        this.totalMarketshareCommitment = totalMarketshareCommitment;
    }
    public String getCommitmentPeriod() {
        return this.commitmentPeriod;
    }
    
    public void setCommitmentPeriod(String commitmentPeriod) {
        this.commitmentPeriod = commitmentPeriod;
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




}


