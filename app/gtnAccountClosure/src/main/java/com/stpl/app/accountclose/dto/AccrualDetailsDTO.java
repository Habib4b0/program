/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class AccrualDetailsDTO {

    private String businessUnit = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String accrualType = StringUtils.EMPTY;
    private String postingDate = StringUtils.EMPTY;
    private String costCenter = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String programType = StringUtils.EMPTY;
    private String glDate = StringUtils.EMPTY;
    private String customerNo = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String programNo = StringUtils.EMPTY;
    private String glAccount = StringUtils.EMPTY;
    private String customerName = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String accrualPeriod = StringUtils.EMPTY;
    private String postingIndicator = StringUtils.EMPTY;
    private String accrualPeriodStartDate = StringUtils.EMPTY;
    private String accrualPeriodEndDate = StringUtils.EMPTY;
    private String amount = StringUtils.EMPTY;
    private String subLedger = StringUtils.EMPTY;
    private String subLedgerType = StringUtils.EMPTY;
    private String documentType = StringUtils.EMPTY;
    private Integer salesMasterId = 0;
    private Integer accrualId = 0;
    private String createdBy = StringUtils.EMPTY;
    private String createdDate = StringUtils.EMPTY;
    private String source = StringUtils.EMPTY;
    private String batchId = StringUtils.EMPTY;

    public String getAccrualPeriodStartDate() {
        return accrualPeriodStartDate;
    }

    public void setAccrualPeriodStartDate(String accrualPeriodStartDate) {
        this.accrualPeriodStartDate = accrualPeriodStartDate;
    }

    public String getAccrualPeriodEndDate() {
        return accrualPeriodEndDate;
    }

    public void setAccrualPeriodEndDate(String accrualPeriodEndDate) {
        this.accrualPeriodEndDate = accrualPeriodEndDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public Integer getSalesMasterId() {
        return salesMasterId;
    }

    public void setSalesMasterId(Integer salesMasterId) {
        this.salesMasterId = salesMasterId;
    }

    public Integer getAccrualId() {
        return accrualId;
    }

    public void setAccrualId(Integer accrualId) {
        this.accrualId = accrualId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
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

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getAccrualType() {
        return accrualType;
    }

    public void setAccrualType(String accrualType) {
        this.accrualType = accrualType;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getGlDate() {
        return glDate;
    }

    public void setGlDate(String glDate) {
        this.glDate = glDate;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getProgramNo() {
        return programNo;
    }

    public void setProgramNo(String programNo) {
        this.programNo = programNo;
    }

    public String getGlAccount() {
        return glAccount;
    }

    public void setGlAccount(String glAccount) {
        this.glAccount = glAccount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAccrualPeriod() {
        return accrualPeriod;
    }

    public void setAccrualPeriod(String accrualPeriod) {
        this.accrualPeriod = accrualPeriod;
    }

    public String getPostingIndicator() {
        return postingIndicator;
    }

    public void setPostingIndicator(String postingIndicator) {
        this.postingIndicator = postingIndicator;
    }
}
