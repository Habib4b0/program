/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.dto;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanukumar
 */
public class DataSelectionDTO implements Serializable {

    private String reportName = StringUtils.EMPTY;
    private String description = StringUtils.EMPTY;
    private String company = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private String deductionType = StringUtils.EMPTY;
    private String customerGroup = StringUtils.EMPTY;
    private String deductionSubType = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String productGroup = StringUtils.EMPTY;
    private String fromPeriod = StringUtils.EMPTY;
    private String toPeriod = StringUtils.EMPTY;
    private String productName = StringUtils.EMPTY;
    private String productIdentifier = StringUtils.EMPTY;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy = StringUtils.EMPTY;
    private String modifiedBy = StringUtils.EMPTY;
    private Date fromDate;
    private Date toDate;
    private String companySid = StringUtils.EMPTY;
    private String contractSid = StringUtils.EMPTY;
    private String itemSid = StringUtils.EMPTY;
    private String accountClosureSid = StringUtils.EMPTY;
    private boolean count = false;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private String deductionCategory = StringUtils.EMPTY;
    private String accountNo = StringUtils.EMPTY;
    private Integer customerGRpSid = 0;
    private Integer productGrpSid = 0;

    public Integer getCustomerGRpSid() {
        return customerGRpSid;
    }

    public void setCustomerGRpSid(Integer customerGRpSid) {
        this.customerGRpSid = customerGRpSid;
    }

    public Integer getProductGrpSid() {
        return productGrpSid;
    }

    public void setProductGrpSid(Integer productGrpSid) {
        this.productGrpSid = productGrpSid;
    }
    
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getDeductionCategory() {
        return deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        this.deductionCategory = deductionCategory;
    }
  
    public String getReportName() {
        return reportName;
    }

    public void setReportName(final String reportName) {
        this.reportName = reportName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(final String company) {
        this.company = company;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(final String marketType) {
        this.marketType = marketType;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(final String deductionType) {
        this.deductionType = deductionType;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(final String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getDeductionSubType() {
        return deductionSubType;
    }

    public void setDeductionSubType(String deductionSubType) {
        this.deductionSubType = deductionSubType;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getFromPeriod() {
        return fromPeriod;
    }

    public void setFromPeriod(String fromPeriod) {
        this.fromPeriod = fromPeriod;
    }

    public String getToPeriod() {
        return toPeriod;
    }

    public void setToPeriod(String toPeriod) {
        this.toPeriod = toPeriod;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getCompanySid() {
        return companySid;
    }

    public void setCompanySid(String companySid) {
        this.companySid = companySid;
    }

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public String getItemSid() {
        return itemSid;
    }

    public void setItemSid(String itemSid) {
        this.itemSid = itemSid;
    }

    public String getAccountClosureSid() {
        return accountClosureSid;
    }

    public void setAccountClosureSid(String accountClosureSid) {
        this.accountClosureSid = accountClosureSid;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }
    
}
