/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanukumar
 */
public class ViewDTO implements Serializable{
    
    private String viewName=StringUtils.EMPTY;
    private String reportName=StringUtils.EMPTY;
    private String description=StringUtils.EMPTY;
    private String company=StringUtils.EMPTY;
    private String marketType=StringUtils.EMPTY;
    private String deductionType=StringUtils.EMPTY;
    private String customerGroup=StringUtils.EMPTY;
    private String contract=StringUtils.EMPTY;
    private String productGroup=StringUtils.EMPTY;
    private String product=StringUtils.EMPTY;
    private String productIdentifier=StringUtils.EMPTY;
    private String fromPeriod=StringUtils.EMPTY;
    private String toPeriod=StringUtils.EMPTY;
    private String createdDate=StringUtils.EMPTY;
    private String modifiedDate=StringUtils.EMPTY;
    private String createdBy=StringUtils.EMPTY;
    private String viewType=StringUtils.EMPTY;
    private int accountClosureId=0;
    private int viewMasterId=0;
    private String deductionSubType=StringUtils.EMPTY;
    private HelperDTO martketType_DTO;
    private HelperDTO company_DTO;
    private HelperDTO deductionType_DTO;
    private HelperDTO subDeductionType_DTO;
    private HelperDTO product_DTO;
    private HelperDTO productIdentifier_DTO;
    private HelperDTO contract_DTO;
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public int getAccountClosureId() {
        return accountClosureId;
    }

    public void setAccountClosureId(int accountClosureId) {
        this.accountClosureId = accountClosureId;
    }

    public int getViewMasterId() {
        return viewMasterId;
    }
    public void setViewMasterId(int viewMasterId) {
        this.viewMasterId = viewMasterId;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getDeductionSubType() {
        return deductionSubType;
    }

    public void setDeductionSubType(String deductionSubType) {
        this.deductionSubType = deductionSubType;
    }

    public HelperDTO getMartketType_DTO() {
        return martketType_DTO;
    }

    public void setMartketType_DTO(HelperDTO martketType_DTO) {
        this.martketType_DTO = martketType_DTO;
    }

    public HelperDTO getCompany_DTO() {
        return company_DTO;
    }

    public void setCompany_DTO(HelperDTO company_DTO) {
        this.company_DTO = company_DTO;
    }

    public HelperDTO getDeductionType_DTO() {
        return deductionType_DTO;
    }

    public void setDeductionType_DTO(HelperDTO deductionType_DTO) {
        this.deductionType_DTO = deductionType_DTO;
    }

    public HelperDTO getSubDeductionType_DTO() {
        return subDeductionType_DTO;
    }

    public void setSubDeductionType_DTO(HelperDTO subDeductionType_DTO) {
        this.subDeductionType_DTO = subDeductionType_DTO;
    }

    public HelperDTO getProduct_DTO() {
        return product_DTO;
    }

    public void setProduct_DTO(HelperDTO product_DTO) {
        this.product_DTO = product_DTO;
    }

    public HelperDTO getProductIdentifier_DTO() {
        return productIdentifier_DTO;
    }

    public void setProductIdentifier_DTO(HelperDTO productIdentifier_DTO) {
        this.productIdentifier_DTO = productIdentifier_DTO;
    }

    public HelperDTO getContract_DTO() {
        return contract_DTO;
    }

    public void setContract_DTO(HelperDTO contract_DTO) {
        this.contract_DTO = contract_DTO;
    }
    
}
