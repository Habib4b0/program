/*
 * To change this license header= StringUtils.EMPTY; choose License Headers in Project Properties.
 * To change this template file= StringUtils.EMPTY; choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.dto;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class CFFOutBoundDTO {

    private Integer financialForecastId = 0;
    private String financialForecastName = StringUtils.EMPTY;
    private String type = StringUtils.EMPTY;
    private String projectId = StringUtils.EMPTY;
    private String projectionName = StringUtils.EMPTY;
    private Integer year = 0;
    private Integer month = 0;
    private String contractId = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String contractType = StringUtils.EMPTY;
    private String contractHolderId = StringUtils.EMPTY;
    private String contractHolderNo = StringUtils.EMPTY;
    private String contractHolderName = StringUtils.EMPTY;
    private String customerId = StringUtils.EMPTY;
    private String customerNo = StringUtils.EMPTY;
    private String customerName = StringUtils.EMPTY;
    private String itemId = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String salesDollars = StringUtils.EMPTY;
    private String salesUnits = StringUtils.EMPTY;
    private String salesInclusion = StringUtils.EMPTY;
    private String deductionId = StringUtils.EMPTY;
    private String deductionNo = StringUtils.EMPTY;
    private String deductionName = StringUtils.EMPTY;
    private String deductionCategory = StringUtils.EMPTY;
    private String deductionType = StringUtils.EMPTY;
    private String deductionProgram = StringUtils.EMPTY;
    private String deductionInclusion = StringUtils.EMPTY;
    private String deductionCategory1 = StringUtils.EMPTY;
    private String deductionCategory2 = StringUtils.EMPTY;
    private String deductionCategory3 = StringUtils.EMPTY;
    private String deductionCategory4 = StringUtils.EMPTY;
    private String deductionCategory5 = StringUtils.EMPTY;
    private String deductionCategory6 = StringUtils.EMPTY;
    private String deductionDollars = StringUtils.EMPTY;
    private String deductionRate = StringUtils.EMPTY;
    private String deductionPerUnit = StringUtils.EMPTY;
    private String netSalesDollar = StringUtils.EMPTY;
    private String cogsAmount = StringUtils.EMPTY;
    private String cogsPerUnit = StringUtils.EMPTY;
    private String netProfitDollars = StringUtils.EMPTY;
    private String netProfitPerUnit = StringUtils.EMPTY;
    private String companyId = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String businessUnitId = StringUtils.EMPTY;
    private String businessUnitNo = StringUtils.EMPTY;
    private String businessUnitName = StringUtils.EMPTY;
    private String financialForecastCreationDate = StringUtils.EMPTY;
    private String financialForecastApprovalDate = StringUtils.EMPTY;
    private String outboundStatus = StringUtils.EMPTY;
    private String originalBatchId = StringUtils.EMPTY;
    private Boolean checkRecord = Boolean.FALSE;
    private Integer cffDetailsSid = 0;
    private Integer rsModelSid = 0;
    private Integer periodSid = 0;

    public Integer getFinancialForecastId() {
        return financialForecastId;
    }

    public void setFinancialForecastId(Integer financialForecastId) {
        this.financialForecastId = financialForecastId;
    }

    public String getFinancialForecastName() {
        return financialForecastName;
    }

    public void setFinancialForecastName(String financialForecastName) {
        this.financialForecastName = financialForecastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractHolderId() {
        return contractHolderId;
    }

    public void setContractHolderId(String contractHolderId) {
        this.contractHolderId = contractHolderId;
    }

    public String getContractHolderNo() {
        return contractHolderNo;
    }

    public void setContractHolderNo(String contractHolderNo) {
        this.contractHolderNo = contractHolderNo;
    }

    public String getContractHolderName() {
        return contractHolderName;
    }

    public void setContractHolderName(String contractHolderName) {
        this.contractHolderName = contractHolderName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getSalesDollars() {
        return salesDollars;
    }

    public void setSalesDollars(String salesDollars) {
        this.salesDollars = salesDollars;
    }

    public String getSalesUnits() {
        return salesUnits;
    }

    public void setSalesUnits(String salesUnits) {
        this.salesUnits = salesUnits;
    }

    public String getSalesInclusion() {
        return salesInclusion;
    }

    public void setSalesInclusion(String salesInclusion) {
        this.salesInclusion = salesInclusion;
    }

    public String getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(String deductionId) {
        this.deductionId = deductionId;
    }

    public String getDeductionNo() {
        return deductionNo;
    }

    public void setDeductionNo(String deductionNo) {
        this.deductionNo = deductionNo;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }

    public String getDeductionCategory() {
        return deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        this.deductionCategory = deductionCategory;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getDeductionProgram() {
        return deductionProgram;
    }

    public void setDeductionProgram(String deductionProgram) {
        this.deductionProgram = deductionProgram;
    }

    public String getDeductionInclusion() {
        return deductionInclusion;
    }

    public void setDeductionInclusion(String deductionInclusion) {
        this.deductionInclusion = deductionInclusion;
    }

    public String getDeductionCategory1() {
        return deductionCategory1;
    }

    public void setDeductionCategory1(String deductionCategory1) {
        this.deductionCategory1 = deductionCategory1;
    }

    public String getDeductionCategory2() {
        return deductionCategory2;
    }

    public void setDeductionCategory2(String deductionCategory2) {
        this.deductionCategory2 = deductionCategory2;
    }

    public String getDeductionCategory3() {
        return deductionCategory3;
    }

    public void setDeductionCategory3(String deductionCategory3) {
        this.deductionCategory3 = deductionCategory3;
    }

    public String getDeductionCategory4() {
        return deductionCategory4;
    }

    public void setDeductionCategory4(String deductionCategory4) {
        this.deductionCategory4 = deductionCategory4;
    }

    public String getDeductionCategory5() {
        return deductionCategory5;
    }

    public void setDeductionCategory5(String deductionCategory5) {
        this.deductionCategory5 = deductionCategory5;
    }

    public String getDeductionCategory6() {
        return deductionCategory6;
    }

    public void setDeductionCategory6(String deductionCategory6) {
        this.deductionCategory6 = deductionCategory6;
    }

    public String getDeductionDollars() {
        return deductionDollars;
    }

    public void setDeductionDollars(String deductionDollars) {
        this.deductionDollars = deductionDollars;
    }

    public String getDeductionRate() {
        return deductionRate;
    }

    public void setDeductionRate(String deductionRate) {
        this.deductionRate = deductionRate;
    }

    public String getDeductionPerUnit() {
        return deductionPerUnit;
    }

    public void setDeductionPerUnit(String deductionPerUnit) {
        this.deductionPerUnit = deductionPerUnit;
    }

    public String getNetSalesDollar() {
        return netSalesDollar;
    }

    public void setNetSalesDollar(String netSalesDollar) {
        this.netSalesDollar = netSalesDollar;
    }

    public String getCogsAmount() {
        return cogsAmount;
    }

    public void setCogsAmount(String cogsAmount) {
        this.cogsAmount = cogsAmount;
    }

    public String getCogsPerUnit() {
        return cogsPerUnit;
    }

    public void setCogsPerUnit(String cogsPerUnit) {
        this.cogsPerUnit = cogsPerUnit;
    }

    public String getNetProfitDollars() {
        return netProfitDollars;
    }

    public void setNetProfitDollars(String netProfitDollars) {
        this.netProfitDollars = netProfitDollars;
    }

    public String getNetProfitPerUnit() {
        return netProfitPerUnit;
    }

    public void setNetProfitPerUnit(String netProfitPerUnit) {
        this.netProfitPerUnit = netProfitPerUnit;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getBusinessUnitNo() {
        return businessUnitNo;
    }

    public void setBusinessUnitNo(String businessUnitNo) {
        this.businessUnitNo = businessUnitNo;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public String getFinancialForecastCreationDate() {
        return financialForecastCreationDate;
    }

    public void setFinancialForecastCreationDate(String financialForecastCreationDate) {
        this.financialForecastCreationDate = financialForecastCreationDate;
    }

    public String getFinancialForecastApprovalDate() {
        return financialForecastApprovalDate;
    }

    public void setFinancialForecastApprovalDate(String financialForecastApprovalDate) {
        this.financialForecastApprovalDate = financialForecastApprovalDate;
    }

    public String getOutboundStatus() {
        return outboundStatus;
    }

    public void setOutboundStatus(String outboundStatus) {
        this.outboundStatus = outboundStatus;
    }

    public String getOriginalBatchId() {
        return originalBatchId;
    }

    public void setOriginalBatchId(String originalBatchId) {
        this.originalBatchId = originalBatchId;
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public Integer getCffDetailsSid() {
        return cffDetailsSid;
    }

    public void setCffDetailsSid(Integer cffDetailsSid) {
        this.cffDetailsSid = cffDetailsSid;
    }

    public Integer getRsModelSid() {
        return rsModelSid;
    }

    public void setRsModelSid(Integer rsModelSid) {
        this.rsModelSid = rsModelSid;
    }

    public Integer getPeriodSid() {
        return periodSid;
    }

    public void setPeriodSid(Integer periodSid) {
        this.periodSid = periodSid;
    }

}
