/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dto;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author kasiammal.m
 */
public class PSIFPDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -7336041461233983663L;
    private String itemFamilyPlanId = StringUtils.EMPTY;
    private String itemFamilyPlanStatus = StringUtils.EMPTY;
    private Date ifpStartDate;
    private Date ifpEndDate;
    private String ifpType = StringUtils.EMPTY;
    private String ifpCategory = StringUtils.EMPTY;
    private String priceScheduleStartDate = StringUtils.EMPTY;
    private String priceScheduleIdValue = StringUtils.EMPTY;
    private String therapeuticClass = StringUtils.EMPTY;
    private Integer PStatus = 0;
    private Integer Ptype = 0;
    /**
     * Item Family plan No Field
     */
    private String itemFamilyplanNo;
    /**
     * Item Family plan No Field
     */
    private String itemFamilyplanName;
    /**
     * Item System Id system generated field in Item Master
     */
    private String itemSystemId;
    /**
     * System generated field in item Family Plan
     */
    private String itemFamilyplanSystemId;
    /**
     * System generated field in Price Schedule
     */
    private String priceScheduleSystemId;
    /**
     * System generated field in Price Schedule details
     */
    private String priceScheduleDetailsSystemId;
    /**
     * Item No Field
     */
    private String itemNo = StringUtils.EMPTY;
    /**
     * Item Name Field
     */
    private String itemName = StringUtils.EMPTY;
    /**
     * Price Protection Start Date Field
     */
    private String priceProtectionStartDate = StringUtils.EMPTY;
    /**
     * Price Protection End Date Field
     */
    private Date priceProtectionEndDate;
    /**
     * Base Price in Item Master Field
     */
    private String basePrice = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    /**
     * Price Type in Item Master Field
     */
    private String priceType = StringUtils.EMPTY;
    
    /**
     * Price Tolerance Field
     */
    private String priceTolerance = StringUtils.EMPTY;
    /**
     * Attached Status Field
     */
    private String attachedStatus = StringUtils.EMPTY;
    /**
     * Price Tolerance Type Field
     */
    private int priceToleranceType = 0;
    /**
     * priceToleranceTypeValue
     */
    private String priceToleranceTypeValue = StringUtils.EMPTY;
    /**
     * Price Tolerance Interval Field
     */
    private int priceToleranceInterval = 0;
    /**
     * priceToleranceFrequencyValue
     */
    private String priceToleranceIntervalValue = StringUtils.EMPTY;
    /**
     * Price Tolerance Frequency Field
     */
    private int priceToleranceFrequency = 0;
    /**
     * Price Tolerance Frequency Field
     */
    private String priceToleranceFrequencyValue = StringUtils.EMPTY;
    /**
     * Price Field
     */
    private String price = StringUtils.EMPTY;
    /**
     * Contract Price Field
     */
    private String contractPrice = StringUtils.EMPTY;
    /**
     * Contract Price Start Date Field
     */
    private Date contractPriceStartDate;
    /**
     * Contract Price End Date Field
     */
    private Date contractPriceEndDate;
    /**
     * Revision Date Field
     */
    private Date revisionDate;
    /**
     * Attached Date Field
     */
    private Date attachedDate;
    private String priceScheduleEndDate = StringUtils.EMPTY;
    /**
     * Check Box Field
     */
    private Boolean checkRecord = false;
    private int tempPsDetailsSystemId;
    /**
     * The userID.
     */
    private String userID = StringUtils.EMPTY;
    /**
     * The sessionID.
     */
    private String sessionID = StringUtils.EMPTY;
    /**
     * The createdBy.
     */
    private String createdBy = StringUtils.EMPTY;
    /**
     * The createdDate.
     */
    private Date createdDate;
    /**
     * The modifiedBy.
     */
    private String itemStartDate = StringUtils.EMPTY;
    
    /**
     * The item end date.
     */
    private String itemEndDate = StringUtils.EMPTY;
    
    private String modifiedBy = StringUtils.EMPTY;
    /**
     * The modifiedDate.
     */
    private Date modifiedDate;
    /**
     * The operationFlag.
     */
    private String operation;
    private String priceScheduleStatusValue;
    private String priceScheduleTypeValue;
    private String priceScheduleDesignationValue;
    private String priceScheduleCategoryValue;
    private String priceScheduleTradeValue;
    private String priceScheduleNoValue = StringUtils.EMPTY;
    private String priceScheduleNameValue = StringUtils.EMPTY;

    /**
     * Default Constructor
     */
    public PSIFPDTO() {
        /**
         * PSIFPDTO
         */
    }

    /**
     * Parameterized Constructor
     *
     * @param itemSystemId
     * @param itemFamilyplanNo
     * @param itemFamilyplanSystemId
     * @param itemNo
     * @param itemName
     * @param priceScheduleSystemId
     * @param attachedDate
     */
    public PSIFPDTO(final String itemSystemId, final String itemFamilyplanNo,
            final String itemFamilyplanSystemId, final String itemNo, final String itemName,
            final String priceScheduleSystemId, final Date attachedDate) {
        this.itemSystemId = itemSystemId;
        this.itemFamilyplanNo = itemFamilyplanNo;
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.priceScheduleSystemId = priceScheduleSystemId;
        this.attachedDate = attachedDate == null ? null : (Date) attachedDate.clone();
    }

    public String getItemFamilyPlanStatus() {
        return itemFamilyPlanStatus;
    }

    public void setItemFamilyPlanStatus(String itemFamilyPlanStatus) {
        this.itemFamilyPlanStatus = itemFamilyPlanStatus;
    }

    public Date getIfpStartDate() {
        return ifpStartDate == null ? null : (Date) ifpStartDate.clone();
    }

    public void setIfpStartDate(Date ifpStartDate) {
        this.ifpStartDate = ifpStartDate == null ? null : (Date) ifpStartDate.clone();
    }

    public Date getIfpEndDate() {
        return ifpEndDate == null ? null : (Date) ifpEndDate.clone();
    }

    public void setIfpEndDate(Date ifpEndDate) {
        this.ifpEndDate = ifpEndDate == null ? null : (Date) ifpEndDate.clone();
    }

    public String getIfpType() {
        return ifpType;
    }

    public void setIfpType(String ifpType) {
        this.ifpType = ifpType;
    }

    public String getIfpCategory() {
        return ifpCategory;
    }

    public void setIfpCategory(String ifpCategory) {
        this.ifpCategory = ifpCategory;
    }

    public String getItemFamilyPlanId() {
        return itemFamilyPlanId;
    }

    public void setItemFamilyPlanId(String itemFamilyPlanId) {
        this.itemFamilyPlanId = itemFamilyPlanId;
    }

    /**
     * @return the itemFamilyplanNo
     */
    public String getItemFamilyplanNo() {
        return itemFamilyplanNo;
    }

    /**
     * @param itemFamilyplanNo the itemFamilyplanNo to set
     */
    public void setItemFamilyplanNo(final String itemFamilyplanNo) {
        this.itemFamilyplanNo = itemFamilyplanNo;
    }

    /**
     * @return the itemSystemId
     */
    public String getItemSystemId() {
        return itemSystemId;
    }

    /**
     * @param itemSystemId the itemSystemId to set
     */
    public void setItemSystemId(final String itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    /**
     * @return the itemFamilyplanSystemId
     */
    public String getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    /**
     * @param itemFamilyplanSystemId the itemFamilyplanSystemId to set
     */
    public void setItemFamilyplanSystemId(final String itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    /**
     * @return the priceScheduleSystemId
     */
    public String getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    /**
     * @param priceScheduleSystemId the priceScheduleSystemId to set
     */
    public void setPriceScheduleSystemId(final String priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    /**
     * @return the itemNo
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * @param itemNo the itemNo to set
     */
    public void setItemNo(final String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the priceProtectionStartDate
     */
    public String getPriceProtectionStartDate() {
        return priceProtectionStartDate;
    }

    /**
     * @param priceProtectionStartDate the priceProtectionStartDate to set
     */
    public void setPriceProtectionStartDate(final String priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
    }

    /**
     * @return the priceProtectionEndDate
     */
    public Date getPriceProtectionEndDate() {
        return priceProtectionEndDate == null ? null : (Date) priceProtectionEndDate.clone();
    }

    /**
     * @param priceProtectionEndDate the priceProtectionEndDate to set
     */
    public void setPriceProtectionEndDate(final Date priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate == null ? null : (Date) priceProtectionEndDate.clone();
    }

    /**
     * @return the basePrice
     */
    public String getBasePrice() {
        return basePrice;
    }

    /**
     * @param basePrice the basePrice to set
     */
    public void setBasePrice(final String basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * @return the priceType
     */
    public String getPriceType() {
        return priceType;
    }

    /**
     * @param priceType the priceType to set
     */
    public void setPriceType(final String priceType) {
        this.priceType = priceType;
    }

    /**
     * @return the priceTolerance
     */
    public String getPriceTolerance() {
        return priceTolerance;
    }

    /**
     * @param priceTolerance the priceTolerance to set
     */
    public void setPriceTolerance(final String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    /**
     * @return the attachedStatus
     */
    public String getAttachedStatus() {
        return attachedStatus;
    }

    /**
     * @param attachedStatus the attachedStatus to set
     */
    public void setAttachedStatus(final String attachedStatus) {
        this.attachedStatus = attachedStatus;
    }

    /**
     * @return the priceToleranceFrequency
     */
    public int getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    /**
     * @param priceToleranceFrequency the priceToleranceFrequency to set
     */
    public void setPriceToleranceFrequency(final int priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(final String price) {
        this.price = price;
    }

    /**
     * @return the contractPrice
     */
    public String getContractPrice() {
        return contractPrice;
    }

    /**
     * @param contractPrice the contractPrice to set
     */
    public void setContractPrice(final String contractPrice) {
        this.contractPrice = contractPrice;
    }

    /**
     * @return the contractPriceStartDate
     */
    public Date getContractPriceStartDate() {
        return contractPriceStartDate == null ? null : (Date) contractPriceStartDate.clone();
    }

    /**
     * @param contractPriceStartDate the contractPriceStartDate to set
     */
    public void setContractPriceStartDate(final Date contractPriceStartDate) {
        this.contractPriceStartDate = contractPriceStartDate == null ? null : (Date) contractPriceStartDate.clone();
    }

    /**
     * @return the contractPriceEndDate
     */
    public Date getContractPriceEndDate() {
        return contractPriceEndDate == null ? null : (Date) contractPriceEndDate.clone();
    }

    /**
     * @param contractPriceEndDate the contractPriceEndDate to set
     */
    public void setContractPriceEndDate(final Date contractPriceEndDate) {
        this.contractPriceEndDate = contractPriceEndDate == null ? null : (Date) contractPriceEndDate.clone();
    }

    /**
     * @return the revisionDate
     */
    public Date getRevisionDate() {
        return revisionDate == null ? null : (Date) revisionDate.clone();
    }

    /**
     * @param revisionDate the revisionDate to set
     */
    public void setRevisionDate(final Date revisionDate) {
        this.revisionDate = revisionDate == null ? null : (Date) revisionDate.clone();
    }

    /**
     * @return the attachedDate
     */
    public Date getAttachedDate() {
        return attachedDate == null ? null : (Date) attachedDate.clone();
    }

    /**
     * @param attachedDate the attachedDate to set
     */
    public void setAttachedDate(final Date attachedDate) {
        this.attachedDate = attachedDate == null ? null : (Date) attachedDate.clone();
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getItemFamilyplanName() {
        return itemFamilyplanName;
    }

    public void setItemFamilyplanName(String itemFamilyplanName) {
        this.itemFamilyplanName = itemFamilyplanName;
    }

    public int getTempPsDetailsSystemId() {
        return tempPsDetailsSystemId;
    }

    public void setTempPsDetailsSystemId(int tempPsDetailsSystemId) {
        this.tempPsDetailsSystemId = tempPsDetailsSystemId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate == null ? null : (Date) createdDate.clone();
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    public String getPriceScheduleDetailsSystemId() {
        return priceScheduleDetailsSystemId;
    }

    public void setPriceScheduleDetailsSystemId(String priceScheduleDetailsSystemId) {
        this.priceScheduleDetailsSystemId = priceScheduleDetailsSystemId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPriceToleranceFrequencyValue() {
        return priceToleranceFrequencyValue;
    }

    public void setPriceToleranceFrequencyValue(String priceToleranceFrequencyValue) {
        this.priceToleranceFrequencyValue = priceToleranceFrequencyValue;
    }

    public int getPriceToleranceType() {
        return priceToleranceType;
    }

    public void setPriceToleranceType(int priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    public int getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    public void setPriceToleranceInterval(int priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    public String getPriceToleranceIntervalValue() {
        return priceToleranceIntervalValue;
    }

    public void setPriceToleranceIntervalValue(String priceToleranceIntervalValue) {
        this.priceToleranceIntervalValue = priceToleranceIntervalValue;
    }

    public String getPriceToleranceTypeValue() {
        return priceToleranceTypeValue;
    }

    public void setPriceToleranceTypeValue(String priceToleranceTypeValue) {
        this.priceToleranceTypeValue = priceToleranceTypeValue;
    }

    public String getPriceScheduleStatusValue() {
        return priceScheduleStatusValue;
    }

    public void setPriceScheduleStatusValue(String priceScheduleStatusValue) {
        this.priceScheduleStatusValue = priceScheduleStatusValue;
    }

    public String getPriceScheduleTypeValue() {
        return priceScheduleTypeValue;
    }

    public void setPriceScheduleTypeValue(String priceScheduleTypeValue) {
        this.priceScheduleTypeValue = priceScheduleTypeValue;
    }

    public String getPriceScheduleDesignationValue() {
        return priceScheduleDesignationValue;
    }

    public void setPriceScheduleDesignationValue(String priceScheduleDesignationValue) {
        this.priceScheduleDesignationValue = priceScheduleDesignationValue;
    }

    public String getPriceScheduleCategoryValue() {
        return priceScheduleCategoryValue;
    }

    public void setPriceScheduleCategoryValue(String priceScheduleCategoryValue) {
        this.priceScheduleCategoryValue = priceScheduleCategoryValue;
    }

    public String getPriceScheduleTradeValue() {
        return priceScheduleTradeValue;
    }

    public void setPriceScheduleTradeValue(String priceScheduleTradeValue) {
        this.priceScheduleTradeValue = priceScheduleTradeValue;
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

    public String getPriceScheduleNameValue() {
        return priceScheduleNameValue;
    }

    public void setPriceScheduleNameValue(String priceScheduleNameValue) {
        this.priceScheduleNameValue = priceScheduleNameValue;
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
}
