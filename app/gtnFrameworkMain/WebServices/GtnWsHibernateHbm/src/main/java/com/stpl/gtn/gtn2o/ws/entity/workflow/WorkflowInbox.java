package com.stpl.gtn.gtn2o.ws.entity.workflow;
// Generated Feb 22, 2017 7:31:26 PM by Hibernate Tools 4.3.1

import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import java.util.Date;

/**
 * WorkflowInbox generated by hbm2java
 */
public class WorkflowInbox implements java.io.Serializable {

	private int workflowInboxSid;
     private CompanyMaster companyMaster;
     private String viewName;
     private String viewType;
     private String businessProcess;
     private String workflowId;
     private String workflowName;
     private Integer workflowStatus;
     private Integer approvedBy;
     private Integer wfCreatedBy;
     private Date creationFromDate;
     private Date creationToDate;
     private int createdBy;
     private Date createdDate;
     private Integer modifiedBy;
     private Date modifiedDate;
     private String workflowDescription;
     private Date approvedDateFrom;
     private Date approvedDateTo;
     private String contractId;
     private String contractNo;
     private String contractName;
     private Integer contractType;
     private String companyId;
     private String companyNo;
     private String companyName;
     private String itemId;
     private String itemNo;
     private String itemName;
     private String deductionLevel;
     private Integer deductionValue;
     private Integer businessUnit;
     private String brandId;
     private String brandName;
     private Date glDate;
     private String deductionNo;
     private String deductionName;
     private String adjustmentType;
     private String businessunitid;
     private String businessunitno;
     private String businessunitname;

    public WorkflowInbox() {
    }

	
    public WorkflowInbox(int workflowInboxSid, String viewName, String viewType, String businessProcess, int createdBy, Date createdDate) {
        this.workflowInboxSid = workflowInboxSid;
        this.viewName = viewName;
        this.viewType = viewType;
        this.businessProcess = businessProcess;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    public WorkflowInbox(int workflowInboxSid, CompanyMaster companyMaster, String viewName, String viewType, String businessProcess, String workflowId, String workflowName, Integer workflowStatus, Integer approvedBy, Integer wfCreatedBy, Date creationFromDate, Date creationToDate, int createdBy, Date createdDate, Integer modifiedBy, Date modifiedDate, String workflowDescription, Date approvedDateFrom, Date approvedDateTo, String contractId, String contractNo, String contractName, Integer contractType, String companyId, String companyNo, String companyName, String itemId, String itemNo, String itemName, String deductionLevel, Integer deductionValue, Integer businessUnit, String brandId, String brandName, Date glDate, String deductionNo, String deductionName, String adjustmentType, String businessunitid, String businessunitno, String businessunitname) {
       this.workflowInboxSid = workflowInboxSid;
       this.companyMaster = companyMaster;
       this.viewName = viewName;
       this.viewType = viewType;
       this.businessProcess = businessProcess;
       this.workflowId = workflowId;
       this.workflowName = workflowName;
       this.workflowStatus = workflowStatus;
       this.approvedBy = approvedBy;
       this.wfCreatedBy = wfCreatedBy;
       this.creationFromDate = creationFromDate;
       this.creationToDate = creationToDate;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
       this.workflowDescription = workflowDescription;
       this.approvedDateFrom = approvedDateFrom;
       this.approvedDateTo = approvedDateTo;
       this.contractId = contractId;
       this.contractNo = contractNo;
       this.contractName = contractName;
       this.contractType = contractType;
       this.companyId = companyId;
       this.companyNo = companyNo;
       this.companyName = companyName;
       this.itemId = itemId;
       this.itemNo = itemNo;
       this.itemName = itemName;
       this.deductionLevel = deductionLevel;
       this.deductionValue = deductionValue;
       this.businessUnit = businessUnit;
       this.brandId = brandId;
       this.brandName = brandName;
       this.glDate = glDate;
       this.deductionNo = deductionNo;
       this.deductionName = deductionName;
       this.adjustmentType = adjustmentType;
       this.businessunitid = businessunitid;
       this.businessunitno = businessunitno;
       this.businessunitname = businessunitname;
    }
   
    public int getWorkflowInboxSid() {
        return this.workflowInboxSid;
    }
    
    public void setWorkflowInboxSid(int workflowInboxSid) {
        this.workflowInboxSid = workflowInboxSid;
    }
    public CompanyMaster getCompanyMaster() {
        return this.companyMaster;
    }
    
    public void setCompanyMaster(CompanyMaster companyMaster) {
        this.companyMaster = companyMaster;
    }
    public String getViewName() {
        return this.viewName;
    }
    
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    public String getViewType() {
        return this.viewType;
    }
    
    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
    public String getBusinessProcess() {
        return this.businessProcess;
    }
    
    public void setBusinessProcess(String businessProcess) {
        this.businessProcess = businessProcess;
    }
    public String getWorkflowId() {
        return this.workflowId;
    }
    
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }
    public String getWorkflowName() {
        return this.workflowName;
    }
    
    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }
    public Integer getWorkflowStatus() {
        return this.workflowStatus;
    }
    
    public void setWorkflowStatus(Integer workflowStatus) {
        this.workflowStatus = workflowStatus;
    }
    public Integer getApprovedBy() {
        return this.approvedBy;
    }
    
    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }
    public Integer getWfCreatedBy() {
        return this.wfCreatedBy;
    }
    
    public void setWfCreatedBy(Integer wfCreatedBy) {
        this.wfCreatedBy = wfCreatedBy;
    }
    public Date getCreationFromDate() {
        return this.creationFromDate;
    }
    
    public void setCreationFromDate(Date creationFromDate) {
        this.creationFromDate = creationFromDate;
    }
    public Date getCreationToDate() {
        return this.creationToDate;
    }
    
    public void setCreationToDate(Date creationToDate) {
        this.creationToDate = creationToDate;
    }
    public int getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Integer getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public String getWorkflowDescription() {
        return this.workflowDescription;
    }
    
    public void setWorkflowDescription(String workflowDescription) {
        this.workflowDescription = workflowDescription;
    }
    public Date getApprovedDateFrom() {
        return this.approvedDateFrom;
    }
    
    public void setApprovedDateFrom(Date approvedDateFrom) {
        this.approvedDateFrom = approvedDateFrom;
    }
    public Date getApprovedDateTo() {
        return this.approvedDateTo;
    }
    
    public void setApprovedDateTo(Date approvedDateTo) {
        this.approvedDateTo = approvedDateTo;
    }
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getContractNo() {
        return this.contractNo;
    }
    
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
    public String getContractName() {
        return this.contractName;
    }
    
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
    public Integer getContractType() {
        return this.contractType;
    }
    
    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }
    public String getCompanyId() {
        return this.companyId;
    }
    
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getCompanyNo() {
        return this.companyNo;
    }
    
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
    public String getDeductionLevel() {
        return this.deductionLevel;
    }
    
    public void setDeductionLevel(String deductionLevel) {
        this.deductionLevel = deductionLevel;
    }
    public Integer getDeductionValue() {
        return this.deductionValue;
    }
    
    public void setDeductionValue(Integer deductionValue) {
        this.deductionValue = deductionValue;
    }
    public Integer getBusinessUnit() {
        return this.businessUnit;
    }
    
    public void setBusinessUnit(Integer businessUnit) {
        this.businessUnit = businessUnit;
    }
    public String getBrandId() {
        return this.brandId;
    }
    
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public String getBrandName() {
        return this.brandName;
    }
    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public Date getGlDate() {
        return this.glDate;
    }
    
    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }
    public String getDeductionNo() {
        return this.deductionNo;
    }
    
    public void setDeductionNo(String deductionNo) {
        this.deductionNo = deductionNo;
    }
    public String getDeductionName() {
        return this.deductionName;
    }
    
    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }
    public String getAdjustmentType() {
        return this.adjustmentType;
    }
    
    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }
    public String getBusinessunitid() {
        return this.businessunitid;
    }
    
    public void setBusinessunitid(String businessunitid) {
        this.businessunitid = businessunitid;
    }
    public String getBusinessunitno() {
        return this.businessunitno;
    }
    
    public void setBusinessunitno(String businessunitno) {
        this.businessunitno = businessunitno;
    }
    public String getBusinessunitname() {
        return this.businessunitname;
    }
    
    public void setBusinessunitname(String businessunitname) {
        this.businessunitname = businessunitname;
    }




}


