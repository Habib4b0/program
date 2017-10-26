/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanukumar
 */
public class CustomerProductDTO implements Serializable{
    
    private String contractNo=StringUtils.EMPTY;
    private String customerName=StringUtils.EMPTY;
    private String contractName=StringUtils.EMPTY;
    private String customerNo=StringUtils.EMPTY;
   
    
    private String sub_ledgerCode=StringUtils.EMPTY;
    private String productName=StringUtils.EMPTY;
    private String costCenter=StringUtils.EMPTY;
    private String brandName=StringUtils.EMPTY;
    
    private String marketTypeId=StringUtils.EMPTY;;
    private String deductionTypeId=StringUtils.EMPTY;
    private String deductionSubTypeId=StringUtils.EMPTY;
    private String contractId=StringUtils.EMPTY;
    private Integer startIndex=0;
    private Integer endIndex=0;
    private String companyMasterSid=StringUtils.EMPTY;
    private String contractMasterSid=StringUtils.EMPTY;
    private String itemMasterSid=StringUtils.EMPTY;
    private String companyId=StringUtils.EMPTY;
    private String idenfier=StringUtils.EMPTY;
    private String customerAlias=StringUtils.EMPTY;
    private boolean count = false;
    
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCustomerAlias() {
        return customerAlias;
    }

    public void setCustomerAlias(String customerAlias) {
        this.customerAlias = customerAlias;
    }

    public String getSub_ledgerCode() {
        return sub_ledgerCode;
    }

    public void setSub_ledgerCode(String sub_ledgerCode) {
        this.sub_ledgerCode = sub_ledgerCode;
    }
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
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

    public String getMarketTypeId() {
        return marketTypeId;
    }

    public void setMarketTypeId(String marketTypeId) {
        this.marketTypeId = marketTypeId;
    }

    public String getDeductionTypeId() {
        return deductionTypeId;
    }

    public void setDeductionTypeId(String deductionTypeId) {
        this.deductionTypeId = deductionTypeId;
    }

    public String getDeductionSubTypeId() {
        return deductionSubTypeId;
    }

    public void setDeductionSubTypeId(String deductionSubTypeId) {
        this.deductionSubTypeId = deductionSubTypeId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
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

    public String getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(String contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public String getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(String itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIdenfier() {
        return idenfier;
    }

    public void setIdenfier(String idenfier) {
        this.idenfier = idenfier;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }
    
}
