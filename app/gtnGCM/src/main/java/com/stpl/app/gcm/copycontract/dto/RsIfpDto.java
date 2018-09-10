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
public class RsIfpDto implements Serializable {

    private String rebateScheduleTransRefNo = StringUtils.EMPTY;
    /**
     * The company familyplan system id.
     */
    private int companyFamilyplanSystemId;
    /**
     * The formula method id.
     */
    private String formulaMethodId;
    /**
     * The payment frequency.
     */
    private Integer paymentFrequency = 0;
    /**
     * The rebate schedule trans ref name.
     */
    private String statusRebate = StringUtils.EMPTY;
    private String rebateSchduleStatusvalue = StringUtils.EMPTY;
    private String rebateScheduleTransRefName = StringUtils.EMPTY;
    private String rebatenumber = StringUtils.EMPTY;
    private String rebatetype = StringUtils.EMPTY;
    private String rsStartdate = StringUtils.EMPTY;
    private String rsEndDate = StringUtils.EMPTY;
    private String therapeuticClass = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    private String itemStartDate = StringUtils.EMPTY;
    private String rebatePlan = StringUtils.EMPTY;
    private String formulaId = StringUtils.EMPTY;
    /**
     * The item end date.
     */
    private String itemEndDate = StringUtils.EMPTY;
    /**
     * The modified date.
     */
    private Date modifiedDate;
    /**
     * The parent rebate schedule name.
     */
    private String parentRebateScheduleName = StringUtils.EMPTY;
    /**
     * The item familyplan system id.
     */
    private int itemFamilyplanSystemId;
    /**
     * The parent rebate schedule id.
     */
    private String parentRebateScheduleId = StringUtils.EMPTY;
    /**
     * The rebate plan level.
     */
    private Integer rebatePlanLevel = 0;
    /**
     * The rebate rule type.
     */
    private Integer rebateRuleType = 0;
    /**
     * The inbound status.
     */
    private String inboundStatus;

    public RsIfpDto() {
        super();
    }

    public int getManfCompanyMasterSid() {
        return manfCompanyMasterSid;
    }

    public void setManfCompanyMasterSid(int manfCompanyMasterSid) {
        this.manfCompanyMasterSid = manfCompanyMasterSid;
    }
    /**
     * The rebate schedule status.
     */
    private Integer rebateScheduleStatus = 0;
    private int manfCompanyMasterSid;

    public Integer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Integer manufacturer) {
        this.manufacturer = manufacturer;
    }
    /**
     * The modified by.
     */
    private String modifiedBy;
    private Integer manufacturer;
    /**
     * The udc6.
     */
    private Integer udc6;
    /**
     * The udc5.
     */
    private Integer udc5;
    /**
     * The udc4.
     */
    private Integer udc4;
    /**
     * The rebate schedule no.
     */
    private String rebateScheduleNo = StringUtils.EMPTY;
    /**
     * The payment method.
     */
    private Integer paymentMethod;
    /**
     * The udc1.
     */
    private Integer udc1;
    /**
     * The zip code.
     */
    private String zipCode;
    /**
     * The udc2.
     */
    private Integer udc2;
    /**
     * The member familyplan system id.
     */
    private int memberFamilyplanSystemId;
    /**
     * The udc3.
     */
    private Integer udc3;
    /**
     * The rebate rule association.
     */
    private String rebateRuleAssociation;
    /**
     * The internal notes.
     */
    private String internalNotes;
    /**
     * The record lock status.
     */
    private boolean recordLockStatus;
    /**
     * The rebate schedule designation.
     */
    private Integer rebateScheduleDesignation;
    /**
     * The rebate schedule trans ref id.
     */
    private String rebateScheduleTransRefId;
    /**
     * The rebate schedule name.
     */
    private String rebateScheduleName = StringUtils.EMPTY;
    /**
     * The rebate program type.
     */
    private int rebateProgramType;
    /**
     * The interest bearing basis.
     */
    private String interestBearingBasis;
    /**
     * The city.
     */
    private String city;
    /**
     * The rebate processing type.
     */
    private String rebateProcessingType;
    /**
     * The state.
     */
    private String state;
    /**
     * The rebate frequency.
     */
    private Integer rebateFrequency = 0;
    /**
     * The rebate schedule id.
     */
    private String rebateScheduleId = StringUtils.EMPTY;
    /**
     * The make payable to.
     */
    private String makePayableTo;
    /**
     * The price schedule system id.
     */
    private int priceScheduleSystemId;
    /**
     * The created by.
     */
    private String createdBy;
    /**
     * The created date.
     */
    private Date createdDate;
    /**
     * The rebate schedule system id.
     */
    private Integer rebateScheduleSystemId;
    /**
     * The trade class.
     */
    private Integer tradeClass;
    /**
     * The interest bearing indicator.
     */
    private String interestBearingIndicator;
    /**
     * The payment terms.
     */
    private Integer paymentTerms;
    /**
     * The address1.
     */
    private String address1;
    /**
     * The rebate schedule type.
     */
    private int rebateScheduleType;
    /**
     * The address2.
     */
    private String address2;
    /**
     * The validation profile.
     */
    private Integer validationProfile;
    /**
     * The rebate schedule category.
     */
    private Integer rebateScheduleCategory;
    /**
     * The calendar.
     */
    private Integer calendar;
    /**
     * The item rebate end date.
     */
    private String itemRebateEndDate = StringUtils.EMPTY;
    /**
     * The payment grace period.
     */
    private String paymentGracePeriod = StringUtils.EMPTY;
    /**
     * The batch id.
     */
    private String batchId;
    /**
     * The item rebate start date.
     */
    private String itemRebateStartDate = StringUtils.EMPTY;
    //Added 
    /**
     * The rebate schedule alias.
     */
    private String rebateScheduleAlias = StringUtils.EMPTY;
    /**
     * The ifpNo
     */
    private String ifpNo = StringUtils.EMPTY;
    /**
     * TheifpName.
     */
    private String ifpName = StringUtils.EMPTY;
    /**
     * The itemNo.
     */
    private String itemNo = StringUtils.EMPTY;
    /**
     * The itemName.
     */
    private String itemName = StringUtils.EMPTY;
    /**
     * The ifpType
     */
    private Integer ifpType = 0;
    /**
     * The ifpStartDate.
     */
    private Date ifpStartDate;
    /**
     * The ifpEndDate.
     */
    private Date ifpEndDate;
    /**
     * The ifpNo
     */
    private String ifpNumber = StringUtils.EMPTY;

    /**
     * Gets the rebate schedule trans ref no.
     *
     * @return the rebate schedule trans ref no
     */
    public String getRebateScheduleTransRefNo() {
        return rebateScheduleTransRefNo;
    }

    /**
     * Sets the rebate schedule trans ref no.
     *
     * @param rebateScheduleTransRefNo the rebate schedule trans ref no
     */
    public void setRebateScheduleTransRefNo(final String rebateScheduleTransRefNo) {
        this.rebateScheduleTransRefNo = rebateScheduleTransRefNo;
    }

    /**
     * Gets the company familyplan system id.
     *
     * @return the company familyplan system id
     */
    public int getCompanyFamilyplanSystemId() {
        return companyFamilyplanSystemId;
    }

    /**
     * Sets the company familyplan system id.
     *
     * @param companyFamilyplanSystemId the company familyplan system id
     */
    public void setCompanyFamilyplanSystemId(final int companyFamilyplanSystemId) {
        this.companyFamilyplanSystemId = companyFamilyplanSystemId;
    }

    /**
     * Gets the formula method id.
     *
     * @return the formula method id
     */
    public String getFormulaMethodId() {
        return formulaMethodId;
    }

    /**
     * Sets the formula method id.
     *
     * @param formulaMethodId the formula method id
     */
    public void setFormulaMethodId(final String formulaMethodId) {
        this.formulaMethodId = formulaMethodId;
    }

    /**
     * Gets the payment frequency.
     *
     * @return the payment frequency
     */
    public Integer getPaymentFrequency() {
        return paymentFrequency;
    }

    /**
     * Sets the payment frequency.
     *
     * @param paymentFrequency the payment frequency
     */
    public void setPaymentFrequency(final Integer paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    /**
     * Gets the rebate schedule trans ref name.
     *
     * @return the rebate schedule trans ref name
     */
    public String getRebateScheduleTransRefName() {
        return rebateScheduleTransRefName;
    }

    /**
     * Sets the rebate schedule trans ref name.
     *
     * @param rebateScheduleTransRefName the rebate schedule trans ref name
     */
    public void setRebateScheduleTransRefName(final String rebateScheduleTransRefName) {
        this.rebateScheduleTransRefName = rebateScheduleTransRefName;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modified date
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    /**
     * Gets the parent rebate schedule name.
     *
     * @return the parent rebate schedule name
     */
    public String getParentRebateScheduleName() {
        return parentRebateScheduleName;
    }

    /**
     * Sets the parent rebate schedule name.
     *
     * @param parentRebateScheduleName the parent rebate schedule name
     */
    public void setParentRebateScheduleName(final String parentRebateScheduleName) {
        this.parentRebateScheduleName = parentRebateScheduleName;
    }

    /**
     * Gets the item familyplan system id.
     *
     * @return the item familyplan system id
     */
    public int getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    /**
     * Sets the item familyplan system id.
     *
     * @param itemFamilyplanSystemId the item familyplan system id
     */
    public void setItemFamilyplanSystemId(final int itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    /**
     * Gets the parent rebate schedule id.
     *
     * @return the parent rebate schedule id
     */
    public String getParentRebateScheduleId() {
        return parentRebateScheduleId;
    }

    /**
     * Sets the parent rebate schedule id.
     *
     * @param parentRebateScheduleId the parent rebate schedule id
     */
    public void setParentRebateScheduleId(final String parentRebateScheduleId) {
        this.parentRebateScheduleId = parentRebateScheduleId;
    }

    /**
     * Gets the rebate plan level.
     *
     * @return the rebate plan level
     */
    public Integer getRebatePlanLevel() {
        return rebatePlanLevel;
    }

    /**
     * Sets the rebate plan level.
     *
     * @param rebatePlanLevel the rebate plan level
     */
    public void setRebatePlanLevel(final Integer rebatePlanLevel) {
        this.rebatePlanLevel = rebatePlanLevel;
    }

    /**
     * Gets the rebate rule type.
     *
     * @return the rebate rule type
     */
    public int getRebateRuleType() {
        return rebateRuleType;
    }

    /**
     * Sets the rebate rule type.
     *
     * @param rebateRuleType the rebate rule type
     */
    public void setRebateRuleType(final Integer rebateRuleType) {
        this.rebateRuleType = rebateRuleType;
    }

    /**
     * Gets the inbound status.
     *
     * @return the inbound status
     */
    public String getInboundStatus() {
        return inboundStatus;
    }

    /**
     * Sets the inbound status.
     *
     * @param inboundStatus the inbound status
     */
    public void setInboundStatus(final String inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    /**
     * Gets the rebate schedule status.
     *
     * @return the rebate schedule status
     */
    public Integer getRebateScheduleStatus() {
        return rebateScheduleStatus;
    }

    /**
     * Sets the rebate schedule status.
     *
     * @param rebateScheduleStatus the rebate schedule status
     */
    public void setRebateScheduleStatus(final Integer rebateScheduleStatus) {
        this.rebateScheduleStatus = rebateScheduleStatus;
    }

    /**
     * Gets the modified by.
     *
     * @return the modified by
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the modified by
     */
    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets the udc6.
     *
     * @return the udc6
     */
    public Integer getUdc6() {
        return udc6;
    }

    /**
     * Sets the udc6.
     *
     * @param udc6 the udc6
     */
    public void setUdc6(final Integer udc6) {
        this.udc6 = udc6;
    }

    /**
     * Gets the udc5.
     *
     * @return the udc5
     */
    public Integer getUdc5() {
        return udc5;
    }

    /**
     * Sets the udc5.
     *
     * @param udc5 the udc5
     */
    public void setUdc5(final Integer udc5) {
        this.udc5 = udc5;
    }

    /**
     * Gets the udc4.
     *
     * @return the udc4
     */
    public Integer getUdc4() {
        return udc4;
    }

    /**
     * Sets the udc4.
     *
     * @param udc4 the udc4
     */
    public void setUdc4(final Integer udc4) {
        this.udc4 = udc4;
    }

    /**
     * Gets the rebate schedule no.
     *
     * @return the rebate schedule no
     */
    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    /**
     * Sets the rebate schedule no.
     *
     * @param rebateScheduleNo the rebate schedule no
     */
    public void setRebateScheduleNo(final String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    /**
     * Gets the payment method.
     *
     * @return the payment method
     */
    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the payment method.
     *
     * @param paymentMethod the payment method
     */
    public void setPaymentMethod(final Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets the udc1.
     *
     * @return the udc1
     */
    public Integer getUdc1() {
        return udc1;
    }

    /**
     * Sets the udc1.
     *
     * @param udc1 the udc1
     */
    public void setUdc1(final Integer udc1) {
        this.udc1 = udc1;
    }

    /**
     * Gets the zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the udc2.
     *
     * @return the udc2
     */
    public Integer getUdc2() {
        return udc2;
    }

    /**
     * Sets the udc2.
     *
     * @param udc2 the udc2
     */
    public void setUdc2(final Integer udc2) {
        this.udc2 = udc2;
    }

    /**
     * Gets the member familyplan system id.
     *
     * @return the member familyplan system id
     */
    public int getMemberFamilyplanSystemId() {
        return memberFamilyplanSystemId;
    }

    /**
     * Sets the member familyplan system id.
     *
     * @param memberFamilyplanSystemId the member familyplan system id
     */
    public void setMemberFamilyplanSystemId(final int memberFamilyplanSystemId) {
        this.memberFamilyplanSystemId = memberFamilyplanSystemId;
    }

    /**
     * Gets the udc3.
     *
     * @return the udc3
     */
    public Integer getUdc3() {
        return udc3;
    }

    /**
     * Sets the udc3.
     *
     * @param udc3 the udc3
     */
    public void setUdc3(final Integer udc3) {
        this.udc3 = udc3;
    }

    /**
     * Gets the rebate rule association.
     *
     * @return the rebate rule association
     */
    public String getRebateRuleAssociation() {
        return rebateRuleAssociation;
    }

    /**
     * Sets the rebate rule association.
     *
     * @param rebateRuleAssociation the rebate rule association
     */
    public void setRebateRuleAssociation(final String rebateRuleAssociation) {
        this.rebateRuleAssociation = rebateRuleAssociation;
    }

    /**
     * Gets the internal notes.
     *
     * @return the internal notes
     */
    public String getInternalNotes() {
        return internalNotes;
    }

    /**
     * Sets the internal notes.
     *
     * @param internalNotes the internal notes
     */
    public void setInternalNotes(final String internalNotes) {
        this.internalNotes = internalNotes;
    }

    /**
     * Gets the record lock status.
     *
     * @return the record lock status
     */
    public boolean getRecordLockStatus() {
        return recordLockStatus;
    }

    /**
     * Sets the record lock status.
     *
     * @param recordLockStatus the record lock status
     */
    public void setRecordLockStatus(final boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    /**
     * Gets the rebate schedule designation.
     *
     * @return the rebate schedule designation
     */
    public Integer getRebateScheduleDesignation() {
        return rebateScheduleDesignation;
    }

    /**
     * Sets the rebate schedule designation.
     *
     * @param rebateScheduleDesignation the rebate schedule designation
     */
    public void setRebateScheduleDesignation(final Integer rebateScheduleDesignation) {
        this.rebateScheduleDesignation = rebateScheduleDesignation;
    }

    /**
     * Gets the rebate schedule trans ref id.
     *
     * @return the rebate schedule trans ref id
     */
    public String getRebateScheduleTransRefId() {
        return rebateScheduleTransRefId;
    }

    /**
     * Sets the rebate schedule trans ref id.
     *
     * @param rebateScheduleTransRefId the rebate schedule trans ref id
     */
    public void setRebateScheduleTransRefId(final String rebateScheduleTransRefId) {
        this.rebateScheduleTransRefId = rebateScheduleTransRefId;
    }

    /**
     * Gets the rebate schedule name.
     *
     * @return the rebate schedule name
     */
    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    /**
     * Sets the rebate schedule name.
     *
     * @param rebateScheduleName the rebate schedule name
     */
    public void setRebateScheduleName(final String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }

    /**
     * Gets the rebate program type.
     *
     * @return the rebate program type
     */
    public int getRebateProgramType() {
        return rebateProgramType;
    }

    /**
     * Sets the rebate program type.
     *
     * @param rebateProgramType the rebate program type
     */
    public void setRebateProgramType(final int rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    /**
     * Gets the interest bearing basis.
     *
     * @return the interest bearing basis
     */
    public String getInterestBearingBasis() {
        return interestBearingBasis;
    }

    /**
     * Sets the interest bearing basis.
     *
     * @param interestBearingBasis the interest bearing basis
     */
    public void setInterestBearingBasis(final String interestBearingBasis) {
        this.interestBearingBasis = interestBearingBasis;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Gets the rebate processing type.
     *
     * @return the rebate processing type
     */
    public String getRebateProcessingType() {
        return rebateProcessingType;
    }

    /**
     * Sets the rebate processing type.
     *
     * @param rebateProcessingType the rebate processing type
     */
    public void setRebateProcessingType(final String rebateProcessingType) {
        this.rebateProcessingType = rebateProcessingType;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * Gets the rebate frequency.
     *
     * @return the rebate frequency
     */
    public Integer getRebateFrequency() {
        return rebateFrequency;
    }

    /**
     * Sets the rebate frequency.
     *
     * @param rebateFrequency the rebate frequency
     */
    public void setRebateFrequency(final Integer rebateFrequency) {
        this.rebateFrequency = rebateFrequency;
    }

    /**
     * Gets the rebate schedule id.
     *
     * @return the rebate schedule id
     */
    public String getRebateScheduleId() {
        return rebateScheduleId;
    }

    /**
     * Sets the rebate schedule id.
     *
     * @param rebateScheduleId the rebate schedule id
     */
    public void setRebateScheduleId(final String rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }

    /**
     * Gets the make payable to.
     *
     * @return the make payable to
     */
    public String getMakePayableTo() {
        return makePayableTo;
    }

    /**
     * Sets the make payable to.
     *
     * @param makePayableTo the make payable to
     */
    public void setMakePayableTo(final String makePayableTo) {
        this.makePayableTo = makePayableTo;
    }

    /**
     * Gets the price schedule system id.
     *
     * @return the price schedule system id
     */
    public int getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    /**
     * Sets the price schedule system id.
     *
     * @param priceScheduleSystemId the price schedule system id
     */
    public void setPriceScheduleSystemId(final int priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the created by
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate == null ? null : (Date) createdDate.clone();
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
    }

    /**
     * Gets the rebate schedule system id.
     *
     * @return the rebate schedule system id
     */
    public Integer getRebateScheduleSystemId() {
        return rebateScheduleSystemId;
    }

    /**
     * Sets the rebate schedule system id.
     *
     * @param rebateScheduleSystemId the rebate schedule system id
     */
    public void setRebateScheduleSystemId(final Integer rebateScheduleSystemId) {
        this.rebateScheduleSystemId = rebateScheduleSystemId;
    }

    /**
     * Gets the trade class.
     *
     * @return the trade class
     */
    public Integer getTradeClass() {
        return tradeClass;
    }

    /**
     * Sets the trade class.
     *
     * @param tradeClass the trade class
     */
    public void setTradeClass(final Integer tradeClass) {
        this.tradeClass = tradeClass;
    }

    /**
     * Gets the interest bearing indicator.
     *
     * @return the interest bearing indicator
     */
    public String getInterestBearingIndicator() {
        return interestBearingIndicator;
    }

    /**
     * Sets the interest bearing indicator.
     *
     * @param interestBearingIndicator the interest bearing indicator
     */
    public void setInterestBearingIndicator(final String interestBearingIndicator) {
        this.interestBearingIndicator = interestBearingIndicator;
    }

    /**
     * Gets the payment terms.
     *
     * @return the payment terms
     */
    public Integer getPaymentTerms() {
        return paymentTerms;
    }

    /**
     * Sets the payment terms.
     *
     * @param paymentTerms the payment terms
     */
    public void setPaymentTerms(final Integer paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    /**
     * Gets the address1.
     *
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the address1.
     *
     * @param address1 the address1
     */
    public void setAddress1(final String address1) {
        this.address1 = address1;
    }

    /**
     * Gets the rebate schedule type.
     *
     * @return the rebate schedule type
     */
    public int getRebateScheduleType() {
        return rebateScheduleType;
    }

    /**
     * Sets the rebate schedule type.
     *
     * @param rebateScheduleType the rebate schedule type
     */
    public void setRebateScheduleType(final int rebateScheduleType) {
        this.rebateScheduleType = rebateScheduleType;
    }

    /**
     * Gets the address2.
     *
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the address2.
     *
     * @param address2 the address2
     */
    public void setAddress2(final String address2) {
        this.address2 = address2;
    }

    /**
     * Gets the validation profile.
     *
     * @return the validation profile
     */
    public Integer getValidationProfile() {
        return validationProfile;
    }

    /**
     * Sets the validation profile.
     *
     * @param validationProfile the validation profile
     */
    public void setValidationProfile(final Integer validationProfile) {
        this.validationProfile = validationProfile;
    }

    /**
     * Gets the rebate schedule category.
     *
     * @return the rebate schedule category
     */
    public Integer getRebateScheduleCategory() {
        return rebateScheduleCategory;
    }

    /**
     * Sets the rebate schedule category.
     *
     * @param rebateScheduleCategory the rebate schedule category
     */
    public void setRebateScheduleCategory(final Integer rebateScheduleCategory) {
        this.rebateScheduleCategory = rebateScheduleCategory;
    }

    /**
     * Gets the calendar.
     *
     * @return the calendar
     */
    public Integer getCalendar() {
        return calendar;
    }

    /**
     * Sets the calendar.
     *
     * @param calendar the calendar
     */
    public void setCalendar(final Integer calendar) {
        this.calendar = calendar;
    }

    /**
     * Gets the item rebate end date.
     *
     * @return the item rebate end date
     */
    public String getItemRebateEndDate() {
        return itemRebateEndDate;
    }

    /**
     * Sets the item rebate end date.
     *
     * @param itemRebateEndDate the item rebate end date
     */
    public void setItemRebateEndDate(final String itemRebateEndDate) {
        this.itemRebateEndDate = itemRebateEndDate;
    }

    /**
     * Gets the payment grace period.
     *
     * @return the payment grace period
     */
    public String getPaymentGracePeriod() {
        return paymentGracePeriod;
    }

    /**
     * Sets the payment grace period.
     *
     * @param paymentGracePeriod the payment grace period
     */
    public void setPaymentGracePeriod(final String paymentGracePeriod) {
        this.paymentGracePeriod = paymentGracePeriod;
    }

    /**
     * Gets the batch id.
     *
     * @return the batch id
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * Sets the batch id.
     *
     * @param batchId the batch id
     */
    public void setBatchId(final String batchId) {
        this.batchId = batchId;
    }

    /**
     * Gets the item rebate start date.
     *
     * @return the item rebate start date
     */
    public String getItemRebateStartDate() {
        return itemRebateStartDate;
    }

    /**
     * Sets the item rebate start date.
     *
     * @param itemRebateStartDate the item rebate start date
     */
    public void setItemRebateStartDate(final String itemRebateStartDate) {
        this.itemRebateStartDate = itemRebateStartDate;
    }

    /**
     * Gets the rebate schedule alias.
     *
     * @return the rebate schedule alias
     */
    public String getRebateScheduleAlias() {
        return rebateScheduleAlias;
    }

    /**
     * Sets the rebate schedule alias.
     *
     * @param rebateScheduleAlias the rebate schedule alias
     */
    public void setRebateScheduleAlias(final String rebateScheduleAlias) {
        this.rebateScheduleAlias = rebateScheduleAlias;
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

    public Integer getIfpType() {
        return ifpType;
    }

    public void setIfpType(Integer ifpType) {
        this.ifpType = ifpType;
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

    public String getIfpNumber() {
        return ifpNumber;
    }

    public void setIfpNumber(String ifpNumber) {
        this.ifpNumber = ifpNumber;
    }

    public String getRebatenumber() {
        return rebatenumber;
    }

    public void setRebatenumber(String rebatenumber) {
        this.rebatenumber = rebatenumber;
    }

    public String getRebatetype() {
        return rebatetype;
    }

    public void setRebatetype(String rebatetype) {
        this.rebatetype = rebatetype;
    }

    public String getRSStartdate() {
        return rsStartdate;
    }

    public void setRSStartdate(String rsStartDate) {
        this.rsStartdate = rsStartDate;
    }

    public String getRSenddate() {
        return rsEndDate;
    }

    public void setRSenddate(String rsEndDate) {
        this.rsEndDate = rsEndDate;
    }

    public String getRebateSchduleStatusvalue() {
        return rebateSchduleStatusvalue;
    }

    public void setRebateSchduleStatusvalue(String rebateSchduleStatusValue) {
        this.rebateSchduleStatusvalue = rebateSchduleStatusValue;
    }

    public String getStatusRebate() {
        return statusRebate;
    }

    public void setStatusRebate(String statusRebate) {
        this.statusRebate = statusRebate;
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

    public String getItemEndDate() {
        return itemEndDate;
    }

    public void setItemEndDate(String itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

}
