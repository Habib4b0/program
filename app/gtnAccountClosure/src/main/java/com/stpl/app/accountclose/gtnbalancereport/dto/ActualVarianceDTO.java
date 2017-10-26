/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.dto;

import java.io.Serializable;

/**
 *
 * @author santanukumar
 */
public class ActualVarianceDTO implements Serializable{
    private String ndcNo;
    private String ndcName;
    private String ndcDescription;
    private String brand;
    private String contractNo;
    private String contractName;
    private String customerNo;
    private String customerName;
    private String amount;
    private String accrualPeriod;
    private String accrualPeriodStartDate;
    private String accrualPeriodEndDate;
    private String glDate;
    private String settlementNo;
    private String invoiceNo;
    private String dispensedDate;
    private String uploadDate;

    public String getNdcNo() {
        return ndcNo;
    }

    public void setNdcNo(String ndcNo) {
        this.ndcNo = ndcNo;
    }

    public String getNdcName() {
        return ndcName;
    }

    public void setNdcName(String ndcName) {
        this.ndcName = ndcName;
    }

    public String getNdcDescription() {
        return ndcDescription;
    }

    public void setNdcDescription(String ndcDescription) {
        this.ndcDescription = ndcDescription;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccrualPeriod() {
        return accrualPeriod;
    }

    public void setAccrualPeriod(String accrualPeriod) {
        this.accrualPeriod = accrualPeriod;
    }

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

    public String getGlDate() {
        return glDate;
    }

    public void setGlDate(String glDate) {
        this.glDate = glDate;
    }

    public String getSettlementNo() {
        return settlementNo;
    }

    public void setSettlementNo(String settlementNo) {
        this.settlementNo = settlementNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDispensedDate() {
        return dispensedDate;
    }

    public void setDispensedDate(String dispensedDate) {
        this.dispensedDate = dispensedDate;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    
}
