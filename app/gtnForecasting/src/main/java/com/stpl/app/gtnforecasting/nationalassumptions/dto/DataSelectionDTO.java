/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.dto;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gopinath
 */
public class DataSelectionDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1452005647890417950L;
    private String product;
    private String productName;
    private String projectionName;  
    private String productGroup;
    private String createdDate;
    private String modifiedDate;
    private Date createdDateSearch;
    private Date modifiedDateSearch;
    private String productGroupName;
    private String company;
    private int itemMasterSid;
    private String contractNo;
    private String contractName;
    private String customerName;
    private String customerAlias;
    private String marketType;
    private String subledgerCode;
    private String productNo;
    private String costCenter;
    private String brandId;
    private int projectionId;
    private String therapeuticClass;
    private String createdBy;
    private String modifiedBy;
    private String companyMasterSid;
    private Integer itemGroupSid;
    private Integer businessUnit;
    private String businessUnitName;
    private String companySid = StringUtils.EMPTY;

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }   

    public DataSelectionDTO(String product, String productName) {
        this.product = product;
        this.productName = productName;
    }

    public DataSelectionDTO() {
        super();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAlias() {
        return customerAlias;
    }

    public void setCustomerAlias(String customerAlias) {
        this.customerAlias = customerAlias;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getSubledgerCode() {
        return subledgerCode;
    }

    public void setSubledgerCode(String subledgerCode) {
        this.subledgerCode = subledgerCode;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }   

    public int getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }   

    public String getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public Integer getItemGroupSid() {
        return itemGroupSid;
    }

    public void setItemGroupSid(Integer itemGroupSid) {
        this.itemGroupSid = itemGroupSid;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDateSearch() {
        return createdDateSearch;
    }

    public void setCreatedDateSearch(Date createdDateSearch) {
        this.createdDateSearch = createdDateSearch;
    }

    public Date getModifiedDateSearch() {
        return modifiedDateSearch;
    }

    public void setModifiedDateSearch(Date modifiedDateSearch) {
        this.modifiedDateSearch = modifiedDateSearch;
    }



    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

  public Integer getBusinessUnit() {
        return businessUnit;
    }
 
    public void setBusinessUnit(Integer businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
}

    public void setBusinessUnitName(String businessUnitSystemName) {
        this.businessUnitName = businessUnitSystemName;
    }

    public String getCompanySid() {
        return companySid;
    }

    public void setCompanySid(String companySid) {
        this.companySid = companySid;
    }

}