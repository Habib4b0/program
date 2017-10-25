/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author kasiammal.m
 */
public class AccrualdetailsDTO {

    Boolean recordLockStatus=new Boolean(false);
    String glCompanyName =StringUtils.EMPTY;
    String companyCostCenter =StringUtils.EMPTY;
    String accountNo =StringUtils.EMPTY;
    String accountName =StringUtils.EMPTY;
    String contractNo =StringUtils.EMPTY;
    String brandName =StringUtils.EMPTY;
    String itemNo =StringUtils.EMPTY;
    String itemName =StringUtils.EMPTY;
    String accrualType =StringUtils.EMPTY;
    String programType =StringUtils.EMPTY;
    String programNo =StringUtils.EMPTY;
    Date accrualPeriodStartDate;
    Date accrualPeriodEndDate;
    Date postingDate;
    String postingIndicator =StringUtils.EMPTY;
    Date glDate;
    String glAccount =StringUtils.EMPTY;
    String subLedger =StringUtils.EMPTY;
    String subLedgerType =StringUtils.EMPTY;
    String documentType =StringUtils.EMPTY;
    String salesMasterId =StringUtils.EMPTY;
    String accrualId =StringUtils.EMPTY;
    String createdBy =StringUtils.EMPTY;
    Date createdDate;
    String source =StringUtils.EMPTY;
    String batchId =StringUtils.EMPTY;
    String postingStatus =StringUtils.EMPTY;
    int accrualDetailsSid = 0;
    Boolean checkrecord = false;

    public Boolean getRecordLockStatus() {
        return recordLockStatus;
    }
   
    public void setRecordLockStatus(Boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    public String getGlCompanyName() {
        return glCompanyName;
}

    public void setGlCompanyName(String glCompanyName) {
        this.glCompanyName = glCompanyName;
    }

    public String getCompanyCostCenter() {
        return companyCostCenter;
    }

    public void setCompanyCostCenter(String companyCostCenter) {
        this.companyCostCenter = companyCostCenter;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAccrualType() {
        return accrualType;
    }

    public void setAccrualType(String accrualType) {
        this.accrualType = accrualType;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getProgramNo() {
        return programNo;
    }

    public void setProgramNo(String programNo) {
        this.programNo = programNo;
    }

    public Date getAccrualPeriodStartDate() {
        return accrualPeriodStartDate;
    }

    public void setAccrualPeriodStartDate(Date accrualPeriodStartDate) {
        this.accrualPeriodStartDate = accrualPeriodStartDate;
    }

    public Date getAccrualPeriodEndDate() {
        return accrualPeriodEndDate;
    }

    public void setAccrualPeriodEndDate(Date accrualPeriodEndDate) {
        this.accrualPeriodEndDate = accrualPeriodEndDate;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getPostingIndicator() {
        return postingIndicator;
    }

    public void setPostingIndicator(String postingIndicator) {
        this.postingIndicator = postingIndicator;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public String getGlAccount() {
        return glAccount;
    }

    public void setGlAccount(String glAccount) {
        this.glAccount = glAccount;
    }

    public String getSubLedger() {
        return subLedger;
    }

    public void setSubLedger(String subLedger) {
        this.subLedger = subLedger;
    }

    public String getSubLedgerType() {
        return subLedgerType;
    }

    public void setSubLedgerType(String subLedgerType) {
        this.subLedgerType = subLedgerType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getSalesMasterId() {
        return salesMasterId;
    }

    public void setSalesMasterId(String salesMasterId) {
        this.salesMasterId = salesMasterId;
    }

    public String getAccrualId() {
        return accrualId;
    }

    public void setAccrualId(String accrualId) {
        this.accrualId = accrualId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getPostingStatus() {
        return postingStatus;
    }

    public void setPostingStatus(String postingStatus) {
        this.postingStatus = postingStatus;
    }

    public int getAccrualDetailsSid() {
        return accrualDetailsSid;
    }

    public void setAccrualDetailsSid(int accrualDetailsSid) {
        this.accrualDetailsSid = accrualDetailsSid;
    }

    public Boolean getCheckrecord() {
        return checkrecord;
    }

    public void setCheckrecord(Boolean checkrecord) {
        this.checkrecord = checkrecord;
    }
    

}
