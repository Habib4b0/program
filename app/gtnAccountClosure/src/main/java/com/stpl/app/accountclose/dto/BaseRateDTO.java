/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import org.asi.container.ExtMapDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class BaseRateDTO extends ExtMapDTO {

    private String companyId = StringUtils.EMPTY;
    private Integer currentRate;
    private String ccpSid = StringUtils.EMPTY;
    private String companySid = StringUtils.EMPTY;
    private String itemSid = StringUtils.EMPTY;
    private String contractSid = StringUtils.EMPTY;
    private String discType = StringUtils.EMPTY;
    private String discSubType = StringUtils.EMPTY;
    private String custGroup = StringUtils.EMPTY;
    private String itemGroup = StringUtils.EMPTY;
    private String contractType = StringUtils.EMPTY;
    private String contract = StringUtils.EMPTY;
    private String product = StringUtils.EMPTY;
    private String ndc = StringUtils.EMPTY;
    private String sessionId;
    private Integer acMasterSid = 0;
    private Integer value = 0;
    private Integer acDetailsSid = 0;
    private String methodologyName = StringUtils.EMPTY;
    private Date methodStartDate;
    private Date methodEndDate;
    private String frequecny = StringUtils.EMPTY;
    private String salesBasis = StringUtils.EMPTY;
    private String rowType = StringUtils.EMPTY;
    private String provisionGrowthRate = StringUtils.EMPTY;
    private String salesGrowthRate = StringUtils.EMPTY;
    private String dampingFactor = StringUtils.EMPTY;
    private String levelValue = StringUtils.EMPTY;
    private String glComapnySid = StringUtils.EMPTY;
    private String contractTypeSid = StringUtils.EMPTY;
    private String rateOrPayment = "0";

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(Integer currentRate) {
        this.currentRate = currentRate;
    }

    public String getCcpSid() {
        return ccpSid;
    }

    public void setCcpSid(String ccpSid) {
        this.ccpSid = ccpSid;
    }

    public String getCompanySid() {
        return companySid;
    }

    public void setCompanySid(String companySid) {
        this.companySid = companySid;
    }

    public String getItemSid() {
        return itemSid;
    }

    public void setItemSid(String itemSid) {
        this.itemSid = itemSid;
    }

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public String getDiscType() {
        return discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public String getDiscSubType() {
        return discSubType;
    }

    public void setDiscSubType(String discSubType) {
        this.discSubType = discSubType;
    }

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
    }

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getAcMasterSid() {
        return acMasterSid;
    }

    public void setAcMasterSid(Integer acMasterSid) {
        this.acMasterSid = acMasterSid;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getAcDetailsSid() {
        return acDetailsSid;
    }

    public void setAcDetailsSid(Integer acDetailsSid) {
        this.acDetailsSid = acDetailsSid;
    }

    public String getMethodologyName() {
        return methodologyName;
    }

    public void setMethodologyName(String methodologyName) {
        this.methodologyName = methodologyName;
    }

    public Date getMethodStartDate() {
        return methodStartDate;
    }

    public void setMethodStartDate(Date methodStartDate) {
        this.methodStartDate = methodStartDate;
    }

    public Date getMethodEndDate() {
        return methodEndDate;
    }

    public void setMethodEndDate(Date methodEndDate) {
        this.methodEndDate = methodEndDate;
    }

    public String getFrequecny() {
        return frequecny;
    }

    public void setFrequecny(String frequecny) {
        this.frequecny = frequecny;
    }

    public String getSalesBasis() {
        return salesBasis;
    }

    public void setSalesBasis(String salesBasis) {
        this.salesBasis = salesBasis;
    }

    public String getRowType() {
        return rowType;
    }

    public void setRowType(String rowType) {
        this.rowType = rowType;
    }

    public String getProvisionGrowthRate() {
        return provisionGrowthRate;
    }

    public void setProvisionGrowthRate(String provisionGrowthRate) {
        this.provisionGrowthRate = provisionGrowthRate;
    }

    public String getSalesGrowthRate() {
        return salesGrowthRate;
    }

    public void setSalesGrowthRate(String salesGrowthRate) {
        this.salesGrowthRate = salesGrowthRate;
    }

    public String getDampingFactor() {
        return dampingFactor;
    }

    public void setDampingFactor(String dampingFactor) {
        this.dampingFactor = dampingFactor;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getGlComapnySid() {
        return glComapnySid;
    }

    public void setGlComapnySid(String glComapnySid) {
        this.glComapnySid = glComapnySid;
    }

    public String getContractTypeSid() {
        return contractTypeSid;
    }

    public void setContractTypeSid(String contractTypeSid) {
        this.contractTypeSid = contractTypeSid;
    }

    public String getRateOrPayment() {
        return rateOrPayment;
    }

    public void setRateOrPayment(String rateOrPayment) {
        this.rateOrPayment = rateOrPayment;
    }
}
