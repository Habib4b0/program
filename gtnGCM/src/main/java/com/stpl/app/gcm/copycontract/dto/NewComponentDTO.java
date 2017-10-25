/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dto;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author nandhakumar
 */
public class NewComponentDTO implements Serializable {

    Date startDate;
    Date endDate;
    String priceType = StringUtils.EMPTY;
    Boolean check = false;
    String ppStartDate = StringUtils.EMPTY;
    String status = StringUtils.EMPTY;
    String modelId = StringUtils.EMPTY;
    String ifpNo = StringUtils.EMPTY;
    String ifpName = StringUtils.EMPTY;
    String ifpId = StringUtils.EMPTY;
    String ifpStatus = StringUtils.EMPTY;
    String ifpType = StringUtils.EMPTY;
    String category = StringUtils.EMPTY;
    String dashboardId = StringUtils.EMPTY;
    String dashboardNumber = StringUtils.EMPTY;
    String dashboardName = StringUtils.EMPTY;
    String levelNo = StringUtils.EMPTY;
    String itemMasterId = StringUtils.EMPTY;
    String hiddenId = StringUtils.EMPTY;

    String companyId = StringUtils.EMPTY;
    String companyNo = StringUtils.EMPTY;
    String companyName = StringUtils.EMPTY;
    String companyStatus = StringUtils.EMPTY;
    String companyStartDate = StringUtils.EMPTY;
    String companyEndDate = StringUtils.EMPTY;
    String psStartDate = StringUtils.EMPTY;
    String psEndDate = StringUtils.EMPTY;
    String companyType = StringUtils.EMPTY;
    String companyCategory = StringUtils.EMPTY;
    String tradeClass = StringUtils.EMPTY;
    private String itemId = StringUtils.EMPTY;
    String itemNo = StringUtils.EMPTY;
    String itemName = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    String therapyClass = StringUtils.EMPTY;
    String brand = StringUtils.EMPTY;
    private String itemType = StringUtils.EMPTY;
    private String form = StringUtils.EMPTY;
    private String strength = StringUtils.EMPTY;
    String marketType = StringUtils.EMPTY;
    String contractHolder = StringUtils.EMPTY;
    String aliasType = StringUtils.EMPTY;
    String aliasNumber = StringUtils.EMPTY;
    Date aliasstartdate;
    Date aliasenddate;
    String savedSystemId = StringUtils.EMPTY;
    String rebatePlan = StringUtils.EMPTY;
    String formulaId = StringUtils.EMPTY;
    String tempIDForPS = StringUtils.EMPTY;
    Date attachedDate;
    String price = "0.0";
    private String pricePlanNo = StringUtils.EMPTY;
    private String pricePlanName = StringUtils.EMPTY;
    private String priceProtectionStatus = StringUtils.EMPTY;
    private Date priceProtectionStartDate;
    private Date priceProtectionEndDate;
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
    int start;
    int offset;

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

    public Date getPriceProtectionStartDate() {
        return priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
    }

    public Date getPriceProtectionEndDate() {
        return priceProtectionEndDate;
    }

    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
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

    public Date getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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

    public String getTherapyClass() {
        return therapyClass;
    }

    public void setTherapyClass(String therapyClass) {
        this.therapyClass = therapyClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getPpStartDate() {
        return ppStartDate;
    }

    public void setPpStartDate(String ppStartDate) {
        this.ppStartDate = ppStartDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getIfpNo() {
        return ifpNo;
    }

    public void setIfpNo(String ifpNo) {
        this.ifpNo = ifpNo;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public String getIfpId() {
        return ifpId;
    }

    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }

    public String getIfpStatus() {
        return ifpStatus;
    }

    public void setIfpStatus(String ifpStatus) {
        this.ifpStatus = ifpStatus;
    }

    public String getIfpType() {
        return ifpType;
    }

    public void setIfpType(String ifpType) {
        this.ifpType = ifpType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
    }

    public String getDashboardNumber() {
        return dashboardNumber;
    }

    public void setDashboardNumber(String dashboardNumber) {
        this.dashboardNumber = dashboardNumber;
    }

    public String getDashboardName() {
        return dashboardName;
    }

    public void setDashboardName(String dashboardName) {
        this.dashboardName = dashboardName;
    }

    public String getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo;
    }

    public String getItemMasterId() {
        return itemMasterId;
    }

    public void setItemMasterId(String itemMasterId) {
        this.itemMasterId = itemMasterId;
    }

    public String getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(String hiddenId) {
        this.hiddenId = hiddenId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String CompanyId) {
        this.companyId = CompanyId;
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

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public String getCompanyEndDate() {
        return companyEndDate;
    }

    public void setCompanyEndDate(String companyEndDate) {
        this.companyEndDate = companyEndDate;
    }

    public String getPsStartDate() {
        return psStartDate;
    }

    public void setPsStartDate(String psStartDate) {
        this.psStartDate = psStartDate;
    }

    public String getPsEndDate() {
        return psEndDate;
    }

    public void setPsEndDate(String psEndDate) {
        this.psEndDate = psEndDate;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {

        this.strength = strength;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public String getAliasType() {
        return aliasType;
    }

    public void setAliasType(String aliasType) {
        this.aliasType = aliasType;
    }

    public String getAliasNumber() {
        return aliasNumber;
    }

    public void setAliasNumber(String aliasNumber) {
        this.aliasNumber = aliasNumber;
    }

    public Date getAliasstartdate() {
        return aliasstartdate;
    }

    public void setAliasstartdate(Date aliasstartdate) {
        this.aliasstartdate = aliasstartdate;
    }

    public Date getAliasenddate() {
        return aliasenddate;
    }

    public void setAliasenddate(Date aliasenddate) {
        this.aliasenddate = aliasenddate;
    }

    public String getSavedSystemId() {
        return savedSystemId;
    }

    public void setSavedSystemId(String savedSystemId) {
        this.savedSystemId = savedSystemId;
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

    public String getTempIDForPS() {
        return tempIDForPS;
    }

    public void setTempIDForPS(String tempIDForPS) {
        this.tempIDForPS = tempIDForPS;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
