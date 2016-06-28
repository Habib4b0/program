package com.stpl.app.contract.global.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.HelperUtils;

/**
 * The Class SearchCFPForm.
 */
@SuppressWarnings("PMD.UnusedPrivateField")
public class SearchCFPForm implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -8263156369169705432L;
    /**
     * The system id.
     */
    private int systemId;
    /**
     * The error messages.
     */
    private String errorMessages = StringUtils.EMPTY;
    /**
     * The company id.
     */
    private String companyId = StringUtils.EMPTY;
    /**
     * The company no.
     */
    private String companyNo = StringUtils.EMPTY;
    /**
     * The company name.
     */
    private String companyName = StringUtils.EMPTY;
    /**
     * The company status.
     */
    private HelperDTO companyStatus;
    /**
     * The company type.
     */
    private HelperDTO companyType;
    /**
     * The identifier.
     */
    private String identifier = StringUtils.EMPTY;
    /**
     * The company type desc.
     */
    private String companyTypeDesc = StringUtils.EMPTY;
    /**
     * The company status desc.
     */
    private String companyStatusDesc = StringUtils.EMPTY;
    /**
     * The identifier type desc.
     */
    private HelperDTO identifierTypeDesc;
    /**
     * The search flag.
     */
    private Boolean searchFlag;
    /**
     * The qualifier flag.
     */
    private Boolean qualifierFlag;
    /**
     * The identifier flag.
     */
    private Boolean identifierFlag;
    /**
     * The success message.
     */
    private String successMessage;
    /**
     * The qualifier id.
     */
    private String qualifierId;
    /**
     * The item start date.
     */
    private Date itemStartDate;
    /**
     * The record lock status.
     */
    private String recordLockStatus;
    /**
     * The company start date.
     */
    private String companyStartDate;
    /**
     * The company end date.
     */
    private String companyEndDate;
    /**
     * The prior parent company no.
     */
    private String priorParentCompanyNo = HelperUtils.EMPTY;
    /**
     * The state.
     */
    private HelperDTO state;
    /**
     * The financial system.
     */
    private String financialSystem = HelperUtils.EMPTY;
    /**
     * The company group.
     */
    private HelperDTO companyGroup;
    /**
     * The company category.
     */
    private HelperDTO companyCategory;
    /**
     * The last updated date.
     */
    private Date lastUpdatedDate;
    /**
     * The parent end date.
     */
    private String parentEndDate;
    /**
     * The modified date.
     */
    private Date modifiedDate;
    /**
     * The lives.
     */
    private int lives;
    /**
     * The organization key.
     */
    private String organizationKey;
    /**
     * The created date.
     */
    private Date createdDate;
    /**
     * The created by.
     */
    private String createdBy;
    /**
     * The trade class.
     */
    private String tradeClass;
    /**
     * The inbound status.
     */
    private String inboundStatus;
    /**
     * The modified by.
     */
    private String modifiedBy;
    /**
     * The udc6.
     */
    private HelperDTO udc6;
    /**
     * The udc5.
     */
    private HelperDTO udc5;
    /**
     * The udc4.
     */
    private HelperDTO udc4;
    /**
     * The udc1.
     */
    private HelperDTO udc1;
    /**
     * The udc2.
     */
    private HelperDTO udc2;
    /**
     * The zip code.
     */
    private String zipCode = HelperUtils.EMPTY;
    /**
     * The udc3.
     */
    private HelperDTO udc3;
    /**
     * The country.
     */
    private HelperDTO country;
    /**
     * The address1.
     */
    private String address1 = HelperUtils.EMPTY;
    /**
     * The address2.
     */
    private String address2 = HelperUtils.EMPTY;
    /**
     * The trade class end date.
     */
    private String tradeClassEndDate;
    /**
     * The trade class start date.
     */
    private String tradeClassStartDate;
    /**
     * The prior parent start date.
     */
    private String priorParentStartDate;
    /**
     * The batch id.
     */
    private String batchId;
    /**
     * The region code.
     */
    private String regionCode = HelperUtils.EMPTY;
    /**
     * The city.
     */
    private String city = HelperUtils.EMPTY;
    /**
     * The parent company no.
     */
    private String parentCompanyNo = HelperUtils.EMPTY;
    /**
     * The parent start date.
     */
    private String parentStartDate;

    /**
     * Gets the system id.
     *
     * @return the system id
     */
    public int getSystemId() {
        return systemId;
    }

    /**
     * Sets the system id.
     *
     * @param systemId the system id
     */
    public void setSystemId(final int systemId) {
        this.systemId = systemId;
    }

    /**
     * Gets the error messages.
     *
     * @return the error messages
     */
    public String getErrorMessages() {
        return errorMessages;
    }

    /**
     * Sets the error messages.
     *
     * @param errorMessages the error messages
     */
    public void setErrorMessages(final String errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * Gets the company id.
     *
     * @return the company id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * Sets the company id.
     *
     * @param companyId the company id
     */
    public void setCompanyId(final String companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets the company no.
     *
     * @return the company no
     */
    public String getCompanyNo() {
        return companyNo;
    }

    /**
     * Gets the search flag.
     *
     * @return the search flag
     */
    public Boolean getSearchFlag() {
        return searchFlag;
    }

    /**
     * Gets the qualifier flag.
     *
     * @return the qualifier flag
     */
    public Boolean getQualifierFlag() {
        return qualifierFlag;
    }

    /**
     * Gets the identifier flag.
     *
     * @return the identifier flag
     */
    public Boolean getIdentifierFlag() {
        return identifierFlag;
    }

    /**
     * Sets the company no.
     *
     * @param companyNo the company no
     */
    public void setCompanyNo(final String companyNo) {
        this.companyNo = companyNo;
    }

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name.
     *
     * @param companyName the company name
     */
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets the company status.
     *
     * @return the company status
     */
    public HelperDTO getCompanyStatus() {
        return companyStatus;
    }

    /**
     * Sets the company status.
     *
     * @param companyStatus the company status
     */
    public void setCompanyStatus(final HelperDTO companyStatus) {
        this.companyStatus = companyStatus;
    }

    /**
     * Gets the company type.
     *
     * @return the company type
     */
    public HelperDTO getCompanyType() {
        return companyType;
    }

    /**
     * Sets the company type.
     *
     * @param companyType the company type
     */
    public void setCompanyType(final HelperDTO companyType) {
        this.companyType = companyType;
    }

    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the identifier.
     *
     * @param identifier the identifier
     */
    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the company type desc.
     *
     * @return the company type desc
     */
    public String getCompanyTypeDesc() {
        return companyTypeDesc;
    }

    /**
     * Sets the company type desc.
     *
     * @param companyTypeDesc the company type desc
     */
    public void setCompanyTypeDesc(final String companyTypeDesc) {
        this.companyTypeDesc = companyTypeDesc;
    }

    /**
     * Gets the company status desc.
     *
     * @return the company status desc
     */
    public String getCompanyStatusDesc() {
        return companyStatusDesc;
    }

    /**
     * Sets the company status desc.
     *
     * @param companyStatusDesc the company status desc
     */
    public void setCompanyStatusDesc(final String companyStatusDesc) {
        this.companyStatusDesc = companyStatusDesc;
    }

    /**
     * Gets the identifier type desc.
     *
     * @return the identifier type desc
     */
    public HelperDTO getIdentifierTypeDesc() {
        return identifierTypeDesc;
    }

    /**
     * Sets the identifier type desc.
     *
     * @param identifierTypeDesc the identifier type desc
     */
    public void setIdentifierTypeDesc(final HelperDTO identifierTypeDesc) {
        this.identifierTypeDesc = identifierTypeDesc;
    }

    /**
     * Checks if is search flag.
     *
     * @return true, if checks if is search flag
     */
    public Boolean isSearchFlag() {
        return searchFlag;
    }

    /**
     * Sets the search flag.
     *
     * @param searchFlag the search flag
     */
    public void setSearchFlag(final Boolean searchFlag) {
        this.searchFlag = searchFlag;
    }

    /**
     * Checks if is qualifier flag.
     *
     * @return true, if checks if is qualifier flag
     */
    public Boolean isQualifierFlag() {
        return qualifierFlag;
    }

    /**
     * Sets the qualifier flag.
     *
     * @param qualifierFlag the qualifier flag
     */
    public void setQualifierFlag(final Boolean qualifierFlag) {
        this.qualifierFlag = qualifierFlag;
    }

    /**
     * Checks if is identifier flag.
     *
     * @return true, if checks if is identifier flag
     */
    public Boolean isIdentifierFlag() {
        return identifierFlag;
    }

    /**
     * Sets the identifier flag.
     *
     * @param identifierFlag the identifier flag
     */
    public void setIdentifierFlag(final Boolean identifierFlag) {
        this.identifierFlag = identifierFlag;
    }

    /**
     * Gets the success message.
     *
     * @return the success message
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Sets the success message.
     *
     * @param successMessage the success message
     */
    public void setSuccessMessage(final String successMessage) {
        this.successMessage = successMessage;
    }

    /**
     * Gets the qualifier id.
     *
     * @return the qualifier id
     */
    public String getQualifierId() {
        return qualifierId;
    }

    /**
     * Sets the qualifier id.
     *
     * @param qualifierId the qualifier id
     */
    public void setQualifierId(final String qualifierId) {
        this.qualifierId = qualifierId;
    }

    /**
     * Gets the item start date.
     *
     * @return the item start date
     */
    public Date getItemStartDate() {
        return itemStartDate;
    }

    /**
     * Sets the item start date.
     *
     * @param itemStartDate the item start date
     */
    public void setItemStartDate(final Date itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    /**
     * Gets the record lock status.
     *
     * @return the record lock status
     */
    public String getRecordLockStatus() {
        return recordLockStatus;
    }

    /**
     * Sets the record lock status.
     *
     * @param recordLockStatus the record lock status
     */
    public void setRecordLockStatus(final String recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    /**
     * Gets the company start date.
     *
     * @return the company start date
     */
    public String getCompanyStartDate() {
        return companyStartDate;
    }

    /**
     * Sets the company start date.
     *
     * @param companyStartDate the company start date
     */
    public void setCompanyStartDate(final String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    /**
     * Gets the company end date.
     *
     * @return the company end date
     */
    public String getCompanyEndDate() {
        return companyEndDate;
    }

    /**
     * Sets the company end date.
     *
     * @param companyEndDate the company end date
     */
    public void setCompanyEndDate(final String companyEndDate) {
        this.companyEndDate = companyEndDate;
    }

    /**
     * Gets the prior parent company no.
     *
     * @return the prior parent company no
     */
    public String getPriorParentCompanyNo() {
        return priorParentCompanyNo;
    }

    /**
     * Sets the prior parent company no.
     *
     * @param priorParentCompanyNo the prior parent company no
     */
    public void setPriorParentCompanyNo(final String priorParentCompanyNo) {
        this.priorParentCompanyNo = priorParentCompanyNo;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public HelperDTO getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the state
     */
    public void setState(final HelperDTO state) {
        this.state = state;
    }

    /**
     * Gets the financial system.
     *
     * @return the financial system
     */
    public String getFinancialSystem() {
        return financialSystem;
    }

    /**
     * Sets the financial system.
     *
     * @param financialSystem the financial system
     */
    public void setFinancialSystem(final String financialSystem) {
        this.financialSystem = financialSystem;
    }

    /**
     * Gets the company group.
     *
     * @return the company group
     */
    public HelperDTO getCompanyGroup() {
        return companyGroup;
    }

    /**
     * Sets the company group.
     *
     * @param companyGroup the company group
     */
    public void setCompanyGroup(final HelperDTO companyGroup) {
        this.companyGroup = companyGroup;
    }

    /**
     * Gets the company category.
     *
     * @return the company category
     */
    public HelperDTO getCompanyCategory() {
        return companyCategory;
    }

    /**
     * Sets the company category.
     *
     * @param companyCategory the company category
     */
    public void setCompanyCategory(final HelperDTO companyCategory) {
        this.companyCategory = companyCategory;
    }

    /**
     * Gets the last updated date.
     *
     * @return the last updated date
     */
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * Sets the last updated date.
     *
     * @param lastUpdatedDate the last updated date
     */
    public void setLastUpdatedDate(final Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * Gets the parent end date.
     *
     * @return the parent end date
     */
    public String getParentEndDate() {
        return parentEndDate;
    }

    /**
     * Sets the parent end date.
     *
     * @param parentEndDate the parent end date
     */
    public void setParentEndDate(final String parentEndDate) {
        this.parentEndDate = parentEndDate;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modified date
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the lives.
     *
     * @param lives the lives
     */
    public void setLives(final int lives) {
        this.lives = lives;
    }

    /**
     * Gets the organization key.
     *
     * @return the organization key
     */
    public String getOrganizationKey() {
        return organizationKey;
    }

    /**
     * Sets the organization key.
     *
     * @param organizationKey the organization key
     */
    public void setOrganizationKey(final String organizationKey) {
        this.organizationKey = organizationKey;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
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
     * Gets the trade class.
     *
     * @return the trade class
     */
    public String getTradeClass() {
        return tradeClass;
    }

    /**
     * Sets the trade class.
     *
     * @param tradeClass the trade class
     */
    public void setTradeClass(final String tradeClass) {
        this.tradeClass = tradeClass;
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
    public HelperDTO getUdc6() {
        return udc6;
    }

    /**
     * Sets the udc6.
     *
     * @param udc6 the udc6
     */
    public void setUdc6(final HelperDTO udc6) {
        this.udc6 = udc6;
    }

    /**
     * Gets the udc5.
     *
     * @return the udc5
     */
    public HelperDTO getUdc5() {
        return udc5;
    }

    /**
     * Sets the udc5.
     *
     * @param udc5 the udc5
     */
    public void setUdc5(final HelperDTO udc5) {
        this.udc5 = udc5;
    }

    /**
     * Gets the udc4.
     *
     * @return the udc4
     */
    public HelperDTO getUdc4() {
        return udc4;
    }

    /**
     * Sets the udc4.
     *
     * @param udc4 the udc4
     */
    public void setUdc4(final HelperDTO udc4) {
        this.udc4 = udc4;
    }

    /**
     * Gets the udc1.
     *
     * @return the udc1
     */
    public HelperDTO getUdc1() {
        return udc1;
    }

    /**
     * Sets the udc1.
     *
     * @param udc1 the udc1
     */
    public void setUdc1(final HelperDTO udc1) {
        this.udc1 = udc1;
    }

    /**
     * Gets the udc2.
     *
     * @return the udc2
     */
    public HelperDTO getUdc2() {
        return udc2;
    }

    /**
     * Sets the udc2.
     *
     * @param udc2 the udc2
     */
    public void setUdc2(final HelperDTO udc2) {
        this.udc2 = udc2;
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
     * Gets the udc3.
     *
     * @return the udc3
     */
    public HelperDTO getUdc3() {
        return udc3;
    }

    /**
     * Sets the udc3.
     *
     * @param udc3 the udc3
     */
    public void setUdc3(final HelperDTO udc3) {
        this.udc3 = udc3;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public HelperDTO getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country the country
     */
    public void setCountry(final HelperDTO country) {
        this.country = country;
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
     * Gets the trade class end date.
     *
     * @return the trade class end date
     */
    public String getTradeClassEndDate() {
        return tradeClassEndDate;
    }

    /**
     * Sets the trade class end date.
     *
     * @param tradeClassEndDate the trade class end date
     */
    public void setTradeClassEndDate(final String tradeClassEndDate) {
        this.tradeClassEndDate = tradeClassEndDate;
    }

    /**
     * Gets the trade class start date.
     *
     * @return the trade class start date
     */
    public String getTradeClassStartDate() {
        return tradeClassStartDate;
    }

    /**
     * Sets the trade class start date.
     *
     * @param tradeClassStartDate the trade class start date
     */
    public void setTradeClassStartDate(final String tradeClassStartDate) {
        this.tradeClassStartDate = tradeClassStartDate;
    }

    /**
     * Gets the prior parent start date.
     *
     * @return the prior parent start date
     */
    public String getPriorParentStartDate() {
        return priorParentStartDate;
    }

    /**
     * Sets the prior parent start date.
     *
     * @param priorParentStartDate the prior parent start date
     */
    public void setPriorParentStartDate(final String priorParentStartDate) {
        this.priorParentStartDate = priorParentStartDate;
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
     * Gets the region code.
     *
     * @return the region code
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * Sets the region code.
     *
     * @param regionCode the region code
     */
    public void setRegionCode(final String regionCode) {
        this.regionCode = regionCode;
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
     * Gets the parent company no.
     *
     * @return the parent company no
     */
    public String getParentCompanyNo() {
        return parentCompanyNo;
    }

    /**
     * Sets the parent company no.
     *
     * @param parentCompanyNo the parent company no
     */
    public void setParentCompanyNo(final String parentCompanyNo) {
        this.parentCompanyNo = parentCompanyNo;
    }

    /**
     * Gets the parent start date.
     *
     * @return the parent start date
     */
    public String getParentStartDate() {
        return parentStartDate;
    }

    /**
     * Sets the parent start date.
     *
     * @param parentStartDate the parent start date
     */
    public void setParentStartDate(final String parentStartDate) {
        this.parentStartDate = parentStartDate;
    }
}
