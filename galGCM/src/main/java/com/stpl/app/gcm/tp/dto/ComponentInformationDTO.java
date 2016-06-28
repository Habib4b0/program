/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sriram
 */
public class ComponentInformationDTO {

    /* Fields for RS */
    String itemNo = StringUtils.EMPTY;
    String itemName = StringUtils.EMPTY;
    String therapeuticClass = StringUtils.EMPTY;
    String brand = StringUtils.EMPTY;
    String status = StringUtils.EMPTY;
    Date startDate;
    Date endDate;
    String formulaId = StringUtils.EMPTY;
    String formulaType = StringUtils.EMPTY;
    String formulaName = StringUtils.EMPTY;
    String rebatePlanId = StringUtils.EMPTY;
    String rebatePlanName = StringUtils.EMPTY;
    String rebateAmount = StringUtils.EMPTY;
    String bundleNo = StringUtils.EMPTY;

    /* Field for PS */
    String priceType = StringUtils.EMPTY;
    String pricePlanNo = StringUtils.EMPTY;
    String pricePlanName = StringUtils.EMPTY;
    String priceProtectionStatus = StringUtils.EMPTY;
    String priceProtectionEndDate = StringUtils.EMPTY;
    String priceProtectionStartDate = StringUtils.EMPTY;
    String priceProtectionPriceType = StringUtils.EMPTY;
    String priceToleranceInterval = StringUtils.EMPTY;
    String priceToleranceFrequency = StringUtils.EMPTY;
    String priceToleranceType = StringUtils.EMPTY;
    String maxIncrementalChange = StringUtils.EMPTY;
    String priceTolerance = StringUtils.EMPTY;
    String resetEligible = StringUtils.EMPTY;
    String resetType = StringUtils.EMPTY;
    String resetDate = StringUtils.EMPTY;
    String resetInterval = StringUtils.EMPTY;
    String resetFrequency = StringUtils.EMPTY;

    /* Field for CFP */
    String companyNo = StringUtils.EMPTY;
    String companyName = StringUtils.EMPTY;
    String tradeClass = StringUtils.EMPTY;
    String companyStatus = StringUtils.EMPTY;
    Date attachedDate;

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

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getRebatePlanId() {
        return rebatePlanId;
    }

    public void setRebatePlanId(String rebatePlanId) {
        this.rebatePlanId = rebatePlanId;
    }

    public String getRebatePlanName() {
        return rebatePlanName;
    }

    public void setRebatePlanName(String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    public String getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(String rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public String getBundleNo() {
        return bundleNo;
    }

    public void setBundleNo(String bundleNo) {
        this.bundleNo = bundleNo;
    }

    public String getPricePlanNo() {
        return pricePlanNo;
    }

    public void setPricePlanNo(String pricePlanNo) {
        this.pricePlanNo = pricePlanNo;
    }

    public String getPricePlanName() {
        return pricePlanName;
    }

    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }

    public String getPriceProtectionEndDate() {
        return priceProtectionEndDate;
    }

    public void setPriceProtectionEndDate(String priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate;
    }

    public String getPriceProtectionStartDate() {
        return priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(String priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
    }
    
    public String getMaxIncrementalChange() {
        return maxIncrementalChange;
    }

    public void setMaxIncrementalChange(String maxIncrementalChange) {
        this.maxIncrementalChange = maxIncrementalChange;
    }

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    public String getResetEligible() {
        return resetEligible;
    }

    public void setResetEligible(String resetEligible) {
        this.resetEligible = resetEligible;
    }

    public String getResetDate() {
        return resetDate;
    }

    public void setResetDate(String resetDate) {
        this.resetDate = resetDate;
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
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

    public String getPriceProtectionStatus() {
        return priceProtectionStatus;
    }

    public void setPriceProtectionStatus(String priceProtectionStatus) {
        this.priceProtectionStatus = priceProtectionStatus;
    }

    public String getPriceProtectionPriceType() {
        return priceProtectionPriceType;
    }

    public void setPriceProtectionPriceType(String priceProtectionPriceType) {
        this.priceProtectionPriceType = priceProtectionPriceType;
    }

    public String getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    public void setPriceToleranceInterval(String priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    public String getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(String priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    public String getPriceToleranceType() {
        return priceToleranceType;
    }

    public void setPriceToleranceType(String priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public String getResetInterval() {
        return resetInterval;
    }

    public void setResetInterval(String resetInterval) {
        this.resetInterval = resetInterval;
    }

    public String getResetFrequency() {
        return resetFrequency;
    }

    public void setResetFrequency(String resetFrequency) {
        this.resetFrequency = resetFrequency;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    }
