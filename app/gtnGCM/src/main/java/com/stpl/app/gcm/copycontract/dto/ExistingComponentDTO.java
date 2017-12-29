/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 */
public class ExistingComponentDTO {
    private String companyFamilyPlanId;
    private String companyFamilyPlanNo;
    private String companyFamilyPlanStatusValue;
    private String companyFamilyPlanStartDate;
    private String companyFamilyPlanEndDate;
    private String itemFamilyplanId;
    private String itemFamilyplanNo;
    private String displayIFPStatus;
    private String ifpStartDate;
    private String ifpEndDate;
    private String priceScheduleIdValue;
    private String priceScheduleNoValue;
    private String priceScheduleStatusValue;
    private String priceScheduleStartDate;
    private String priceScheduleEndDate;
    private String itemFamilyplanName;
    private String priceScheduleTypeValue;
    private String priceScheduleCategoryValue;
    private String priceScheduleDesignationValue;
    private String rebateScheduleId;
    private String rebateScheduleNo;
    private String statusRebate;
    private String itemRebateStartDate;
    private String itemRebateEndDate;
    private String ifpName;
    private String paymentFrequency;
    private String rebateProgramType;
    private String interestBearingBasis;
    private String companyFamilyPlanName = StringUtils.EMPTY;
    private String companyFamilyPlanTypeValue = StringUtils.EMPTY;
    private String companyFamilyPlanCategoryValue = StringUtils.EMPTY;
    private String companyFamilyTradeClassValue = StringUtils.EMPTY;
    private String companyFamilyPlanDesignation = StringUtils.EMPTY;
    private int companyFamilyPlanSystemId;
    private int ifpDetailsSystemId;
    private String itemFamilyplanType = StringUtils.EMPTY;
    private Integer IFPtype = 0;
    private Integer itemFamilyplanStatus = 0;
    private String itemFamilyplanDesignation = StringUtils.EMPTY;
    private String priceScheduleSystemId;
    private String priceScheduleNameValue = StringUtils.EMPTY;
    private Integer PStatus = 0;
    private Integer Ptype = 0;
    private Integer rebateScheduleSystemId;
    private String rebateScheduleName = StringUtils.EMPTY;
    private Integer rebateScheduleStatus = 0;
    private String rebatetype = StringUtils.EMPTY;
    private String companyId = StringUtils.EMPTY;
    private String tradeClassEndDate = StringUtils.EMPTY;
    private String companyStatusValue = StringUtils.EMPTY;
    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String tradeClassStartDate = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String therapeuticClass = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    private String itemStartDate = StringUtils.EMPTY;
    private String itemEndDate = StringUtils.EMPTY;
    private String priceType = StringUtils.EMPTY;
    private String priceProtectionStartDate = StringUtils.EMPTY;
    private String rebatePlan= StringUtils.EMPTY;
    private String formulaId= StringUtils.EMPTY;
    private String startDate=StringUtils.EMPTY;
    private String endDate=StringUtils.EMPTY;
    private String tradeClass=StringUtils.EMPTY;
    private  Date attachedDate;
    private String pricePlanNo = StringUtils.EMPTY;
    private String pricePlanName = StringUtils.EMPTY;
    private String priceProtectionStatus = StringUtils.EMPTY;
    private String priceProtectionEndDate= StringUtils.EMPTY;
    private String priceProtectionPriceType = StringUtils.EMPTY;
    private String priceToleranceInterval = StringUtils.EMPTY;
    private String priceToleranceFrequency = StringUtils.EMPTY;
    private String priceToleranceType = StringUtils.EMPTY;
    private String priceTolerance = StringUtils.EMPTY;
    private String maxIncrementalChange = StringUtils.EMPTY;
    private String reset = StringUtils.EMPTY;
    private String eligibility = StringUtils.EMPTY;
    private String resetType = StringUtils.EMPTY;
    private String resetDate = StringUtils.EMPTY;
    private String resetIntervel = StringUtils.EMPTY;
    private String resetFrequency = StringUtils.EMPTY;
    private String formulaType = StringUtils.EMPTY;
    private String formulaName = StringUtils.EMPTY;
    private String rebatePlanId = StringUtils.EMPTY;
    private String rebatePlanName = StringUtils.EMPTY;
    private String rebateAmount = StringUtils.EMPTY;
    private String bundleNo = StringUtils.EMPTY;
    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPriceProtectionStartDate() {
        return priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(String priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
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

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(String itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    public String getItemEndDate() {
        return itemEndDate;
    }

    public void setItemEndDate(String itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    public String getTradeClassEndDate() {
        return tradeClassEndDate;
    }

    public void setTradeClassEndDate(String tradeClassEndDate) {
        this.tradeClassEndDate = tradeClassEndDate;
    }

    public String getCompanyStatusValue() {
        return companyStatusValue;
    }

    public void setCompanyStatusValue(String companyStatusValue) {
        this.companyStatusValue = companyStatusValue;
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

    public String getTradeClassStartDate() {
        return tradeClassStartDate;
    }

    public void setTradeClassStartDate(String tradeClassStartDate) {
        this.tradeClassStartDate = tradeClassStartDate;
    }

    public Integer getRebateScheduleSystemId() {
        return rebateScheduleSystemId;
    }

    public void setRebateScheduleSystemId(Integer rebateScheduleSystemId) {
        this.rebateScheduleSystemId = rebateScheduleSystemId;
    }

    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }

    public Integer getRebateScheduleStatus() {
        return rebateScheduleStatus;
    }

    public void setRebateScheduleStatus(Integer rebateScheduleStatus) {
        this.rebateScheduleStatus = rebateScheduleStatus;
    }

    public String getRebatetype() {
        return rebatetype;
    }

    public void setRebatetype(String rebatetype) {
        this.rebatetype = rebatetype;
    }

    public String getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    public void setPriceScheduleSystemId(String priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    public String getPriceScheduleNameValue() {
        return priceScheduleNameValue;
    }

    public void setPriceScheduleNameValue(String priceScheduleNameValue) {
        this.priceScheduleNameValue = priceScheduleNameValue;
    }

    public Integer getPStatus() {
        return PStatus;
    }

    public void setPStatus(Integer PStatus) {
        this.PStatus = PStatus;
    }

    public Integer getPtype() {
        return Ptype;
    }

    public void setPtype(Integer Ptype) {
        this.Ptype = Ptype;
    }

    public int getIfpDetailsSystemId() {
        return ifpDetailsSystemId;
    }

    public void setIfpDetailsSystemId(int ifpDetailsSystemId) {
        this.ifpDetailsSystemId = ifpDetailsSystemId;
    }

    public String getItemFamilyplanType() {
        return itemFamilyplanType;
    }

    public void setItemFamilyplanType(String itemFamilyplanType) {
        this.itemFamilyplanType = itemFamilyplanType;
    }

    public Integer getIFPtype() {
        return IFPtype;
    }

    public void setIFPtype(Integer IFPtype) {
        this.IFPtype = IFPtype;
    }

    public Integer getItemFamilyplanStatus() {
        return itemFamilyplanStatus;
    }

    public void setItemFamilyplanStatus(Integer itemFamilyplanStatus) {
        this.itemFamilyplanStatus = itemFamilyplanStatus;
    }

    public String getItemFamilyplanDesignation() {
        return itemFamilyplanDesignation;
    }

    public void setItemFamilyplanDesignation(String itemFamilyplanDesignation) {
        this.itemFamilyplanDesignation = itemFamilyplanDesignation;
    }

    public int getCompanyFamilyPlanSystemId() {
        return companyFamilyPlanSystemId;
    }

    public void setCompanyFamilyPlanSystemId(int companyFamilyPlanSystemId) {
        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
    }

    public String getCompanyFamilyPlanDesignation() {
        return companyFamilyPlanDesignation;
    }

    public void setCompanyFamilyPlanDesignation(String companyFamilyPlanDesignation) {
        this.companyFamilyPlanDesignation = companyFamilyPlanDesignation;
    }

    public String getCompanyFamilyPlanCategoryValue() {
        return companyFamilyPlanCategoryValue;
    }

    public void setCompanyFamilyPlanCategoryValue(String companyFamilyPlanCategoryValue) {
        this.companyFamilyPlanCategoryValue = companyFamilyPlanCategoryValue;
    }

    public String getCompanyFamilyTradeClassValue() {
        return companyFamilyTradeClassValue;
    }

    public void setCompanyFamilyTradeClassValue(String companyFamilyTradeClassValue) {
        this.companyFamilyTradeClassValue = companyFamilyTradeClassValue;
    }

    public String getCompanyFamilyPlanTypeValue() {
        return companyFamilyPlanTypeValue;
    }

    public void setCompanyFamilyPlanTypeValue(String companyFamilyPlanTypeValue) {
        this.companyFamilyPlanTypeValue = companyFamilyPlanTypeValue;
    }

    public String getCompanyFamilyPlanName() {
        return companyFamilyPlanName;
    }

    public void setCompanyFamilyPlanName(String companyFamilyPlanName) {
        this.companyFamilyPlanName = companyFamilyPlanName;
    }

    public String getCompanyFamilyPlanId() {
        return companyFamilyPlanId;
    }

    public void setCompanyFamilyPlanId(String companyFamilyPlanId) {
        this.companyFamilyPlanId = companyFamilyPlanId;
    }

    public String getCompanyFamilyPlanNo() {
        return companyFamilyPlanNo;
    }

    public void setCompanyFamilyPlanNo(String companyFamilyPlanNo) {
        this.companyFamilyPlanNo = companyFamilyPlanNo;
    }

    public String getCompanyFamilyPlanStatusValue() {
        return companyFamilyPlanStatusValue;
    }

    public void setCompanyFamilyPlanStatusValue(String companyFamilyPlanStatusValue) {
        this.companyFamilyPlanStatusValue = companyFamilyPlanStatusValue;
    }

    public String getCompanyFamilyPlanStartDate() {
        return companyFamilyPlanStartDate;
    }

    public void setCompanyFamilyPlanStartDate(String companyFamilyPlanStartDate) {
        this.companyFamilyPlanStartDate = companyFamilyPlanStartDate;
    }

    public String getCompanyFamilyPlanEndDate() {
        return companyFamilyPlanEndDate;
    }

    public void setCompanyFamilyPlanEndDate(String companyFamilyPlanEndDate) {
        this.companyFamilyPlanEndDate = companyFamilyPlanEndDate;
    }

    public String getItemFamilyplanId() {
        return itemFamilyplanId;
    }

    public void setItemFamilyplanId(String itemFamilyplanId) {
        this.itemFamilyplanId = itemFamilyplanId;
    }

    public String getItemFamilyplanNo() {
        return itemFamilyplanNo;
    }

    public void setItemFamilyplanNo(String itemFamilyplanNo) {
        this.itemFamilyplanNo = itemFamilyplanNo;
    }

    public String getDisplayIFPStatus() {
        return displayIFPStatus;
    }

    public void setDisplayIFPStatus(String displayIFPStatus) {
        this.displayIFPStatus = displayIFPStatus;
    }

    public String getIfpStartDate() {
        return ifpStartDate;
    }

    public void setIfpStartDate(String ifpStartDate) {
        this.ifpStartDate = ifpStartDate;
    }

    public String getIfpEndDate() {
        return ifpEndDate;
    }

    public void setIfpEndDate(String ifpEndDate) {
        this.ifpEndDate = ifpEndDate;
    }

    public String getPriceScheduleIdValue() {
        return priceScheduleIdValue;
    }

    public void setPriceScheduleIdValue(String priceScheduleIdValue) {
        this.priceScheduleIdValue = priceScheduleIdValue;
    }

    public String getPriceScheduleNoValue() {
        return priceScheduleNoValue;
    }

    public void setPriceScheduleNoValue(String priceScheduleNoValue) {
        this.priceScheduleNoValue = priceScheduleNoValue;
    }

    public String getPriceScheduleStatusValue() {
        return priceScheduleStatusValue;
    }

    public void setPriceScheduleStatusValue(String priceScheduleStatusValue) {
        this.priceScheduleStatusValue = priceScheduleStatusValue;
    }

    public String getPriceScheduleStartDate() {
        return priceScheduleStartDate;
    }

    public void setPriceScheduleStartDate(String priceScheduleStartDate) {
        this.priceScheduleStartDate = priceScheduleStartDate;
    }

    public String getPriceScheduleEndDate() {
        return priceScheduleEndDate;
    }

    public void setPriceScheduleEndDate(String priceScheduleEndDate) {
        this.priceScheduleEndDate = priceScheduleEndDate;
    }

    public String getItemFamilyplanName() {
        return itemFamilyplanName;
    }

    public void setItemFamilyplanName(String itemFamilyplanName) {
        this.itemFamilyplanName = itemFamilyplanName;
    }

    public String getPriceScheduleTypeValue() {
        return priceScheduleTypeValue;
    }

    public void setPriceScheduleTypeValue(String priceScheduleTypeValue) {
        this.priceScheduleTypeValue = priceScheduleTypeValue;
    }

    public String getPriceScheduleCategoryValue() {
        return priceScheduleCategoryValue;
    }

    public void setPriceScheduleCategoryValue(String priceScheduleCategoryValue) {
        this.priceScheduleCategoryValue = priceScheduleCategoryValue;
    }

    public String getPriceScheduleDesignationValue() {
        return priceScheduleDesignationValue;
    }

    public void setPriceScheduleDesignationValue(String priceScheduleDesignationValue) {
        this.priceScheduleDesignationValue = priceScheduleDesignationValue;
    }

    public String getRebateScheduleId() {
        return rebateScheduleId;
    }

    public void setRebateScheduleId(String rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }

    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    public String getStatusRebate() {
        return statusRebate;
    }

    public void setStatusRebate(String statusRebate) {
        this.statusRebate = statusRebate;
    }

    public String getItemRebateStartDate() {
        return itemRebateStartDate;
    }

    public void setItemRebateStartDate(String itemRebateStartDate) {
        this.itemRebateStartDate = itemRebateStartDate;
    }

    public String getItemRebateEndDate() {
        return itemRebateEndDate;
    }

    public void setItemRebateEndDate(String itemRebateEndDate) {
        this.itemRebateEndDate = itemRebateEndDate;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public String getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(String rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    public String getInterestBearingBasis() {
        return interestBearingBasis;
    }

    public void setInterestBearingBasis(String interestBearingBasis) {
        this.interestBearingBasis = interestBearingBasis;
    }

    public String getRebatePlan() {
        return rebatePlan;
    }

    public void setRebatePlan(String rebatePlan) {
        this.rebatePlan = rebatePlan;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public Date getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate;
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

    public String getPriceProtectionStatus() {
        return priceProtectionStatus;
    }

    public void setPriceProtectionStatus(String priceProtectionStatus) {
        this.priceProtectionStatus = priceProtectionStatus;
    }

    public String getPriceProtectionEndDate() {
        return priceProtectionEndDate;
    }

    public void setPriceProtectionEndDate(String priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate;
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

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    public String getMaxIncrementalChange() {
        return maxIncrementalChange;
    }

    public void setMaxIncrementalChange(String maxIncrementalChange) {
        this.maxIncrementalChange = maxIncrementalChange;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public String getResetDate() {
        return resetDate;
    }

    public void setResetDate(String resetDate) {
        this.resetDate = resetDate;
    }

    public String getResetIntervel() {
        return resetIntervel;
    }

    public void setResetIntervel(String resetIntervel) {
        this.resetIntervel = resetIntervel;
    }

    public String getResetFrequency() {
        return resetFrequency;
    }

    public void setResetFrequency(String resetFrequency) {
        this.resetFrequency = resetFrequency;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
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

}
