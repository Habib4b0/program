/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vinodhini
 */
public class FixedDollarSaveDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -3123312853409349119L;

    private boolean saveFlag;
    private String accountNo;
    private Date toDate;
    private int itemMasterSid;
    private String description;
    private String reportName;
    private int rsType;
    private String productIdentifier;
    private Date modifiedDate;
    private int workflowStatus;
    private String moduleType;
    private Date fromDate;
    private int contractType;
    private int glCompanyMasterSid;
    private Date createdDate;
    private int createdBy;
    private int contractMasterSid;
    private String accrualPeriod;
    private String companyGroupSid;
    private int accClosureMasterSid;
    private int rsCategory;
    private int adjustmentType;
    private int modifiedBy;
    private String itemGroupSid;
    private int rebateProgramType;

    public boolean isSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public int getRsType() {
        return rsType;
    }

    public void setRsType(int rsType) {
        this.rsType = rsType;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(int workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public int getContractType() {
        return contractType;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    public int getGlCompanyMasterSid() {
        return glCompanyMasterSid;
    }

    public void setGlCompanyMasterSid(int glCompanyMasterSid) {
        this.glCompanyMasterSid = glCompanyMasterSid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public String getAccrualPeriod() {
        return accrualPeriod;
    }

    public void setAccrualPeriod(String accrualPeriod) {
        this.accrualPeriod = accrualPeriod;
    }

    public String getCompanyGroupSid() {
        return companyGroupSid;
    }

    public void setCompanyGroupSid(String companyGroupSid) {
        this.companyGroupSid = companyGroupSid;
    }

    public int getAccClosureMasterSid() {
        return accClosureMasterSid;
    }

    public void setAccClosureMasterSid(int accClosureMasterSid) {
        this.accClosureMasterSid = accClosureMasterSid;
    }

    public int getRsCategory() {
        return rsCategory;
    }

    public void setRsCategory(int rsCategory) {
        this.rsCategory = rsCategory;
    }

    public int getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(int adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getItemGroupSid() {
        return itemGroupSid;
    }

    public void setItemGroupSid(String itemGroupSid) {
        this.itemGroupSid = itemGroupSid;
    }

    public int getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(int rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

}
