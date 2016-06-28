package com.stpl.app.contract.global.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import com.stpl.ifs.util.HelperUtils;
import org.hibernate.validator.constraints.NotBlank;


public class CompanyMasterDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 4694713154611977873L;
    /**
     * The company system id.
     */
    private String companySystemId;
    /**
     * The company id.
     */
    @NotBlank(message = "Company Id should  be present")
    private String companyId = HelperUtils.EMPTY;
    /**
     * The company no.
     */
    @NotBlank(message = "Company No should  be present")
    private String companyNo = HelperUtils.EMPTY;
    /**
     * The company name.
     */
    @NotBlank(message = "Company Name should  be present")
    private String companyName = HelperUtils.EMPTY;
    /**
     * The company start date.
     */
    @NotBlank(message = "Company Start Date should  be present")
    private String companyStartDate;
    /**
     * The company end date.
     */
    @NotBlank(message = "Company End Date should  be present")
    private String companyEndDate;
    /**
     * The prior parent company no.
     */
    private String priorParentCompanyNo = HelperUtils.EMPTY;
    /**
     * The state.
     */
    @NotBlank(message = "State should  be present")
    private String state;
    /**
     * The financial system.
     */
    private String financialSystem = HelperUtils.EMPTY;
    /**
     * The company group.
     */
    private String companyGroup;
    /**
     * The company category.
     */
    private String companyCategory;
    /**
     * The last updated date.
     */
    private Date lastUpdatedDate;
    /**
     * The parent end date.
     */
    private Date parentEndDate;
    /**
     * The modified date.
     */
    private Date modifiedDate;
    /**
     * The lives.
     */
    private String lives = StringUtils.EMPTY;
    /**
     * The organization key.
     */
    private String organizationKey;
    /**
     * The created date.
     */
    private String createdDate;
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
    private String udc6;
    /**
     * The udc5.
     */
    private String udc5;
    /**
     * The udc4.
     */
    private String udc4;
    /**
     * The udc1.
     */
    private String udc1;
    /**
     * The udc2.
     */
    private String udc2;
    /**
     * The zip code.
     */
    @NotBlank(message = "Zip Code should  be present")
    private String zipCode = HelperUtils.EMPTY;
    /**
     * The udc3.
     */
    private String udc3;
    /**
     * The country.
     */
    @NotBlank(message = "Country should  be present")
    private String country;
    /**
     * The address1.
     */
    @NotBlank(message = "Address 1 should  be present")
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
     * The company type.
     */
    private String companyType;
    
    private String displayCompanyType;
    /**
     * The trade class start date.
     */
    private String tradeClassStartDate;
    /**
     * The prior parent start date.
     */
    private Date priorParentStartDate;
    /**
     * The batch id.
     */
    private String batchId;
    /**
     * The company status.
     */
    private String companyStatus;
    
    private String displayCompanyStatus;
    /**
     * The region code.
     */
    private String regionCode = HelperUtils.EMPTY;
    /**
     * The city.
     */
    @NotBlank(message = "City should  be present")
    private String city = HelperUtils.EMPTY;
    /**
     * The parent company no.
     */
    private String parentCompanyNo = HelperUtils.EMPTY;
    /**
     * The parent start date.
     */
    private Date parentStartDate;
    /**
     * The record lock status.
     */
    private String recordLockStatus;

    
    
    /**
     * The record lock status.
     */
    private String tempCfpDetailsSystemId;
    
    public String getTempCfpDetailsSystemId() {
        return tempCfpDetailsSystemId;
    }

    public void setTempCfpDetailsSystemId(String tempCfpDetailsSystemId) {
        this.tempCfpDetailsSystemId = tempCfpDetailsSystemId;
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
     * Gets the udc1.
     *
     * @return the udc1
     */
    public String getUdc1() {
        return udc1;
    }

    /**
     * Sets the udc1.
     *
     * @param udc1 the udc1
     */
    public void setUdc1(final String udc1) {
        this.udc1 = udc1;
    }

    /**
     * Gets the udc2.
     *
     * @return the udc2
     */
    public String getUdc2() {
        return udc2;
    }

    /**
     * Sets the udc2.
     *
     * @param udc2 the udc2
     */
    public void setUdc2(final String udc2) {
        this.udc2 = udc2;
    }

    /**
     * Gets the udc3.
     *
     * @return the udc3
     */
    public String getUdc3() {
        return udc3;
    }

    /**
     * Sets the udc3.
     *
     * @param udc3 the udc3
     */
    public void setUdc3(final String udc3) {
        this.udc3 = udc3;
    }

    /**
     * Gets the udc4.
     *
     * @return the udc4
     */
    public String getUdc4() {
        return udc4;
    }

    /**
     * Sets the udc4.
     *
     * @param udc4 the udc4
     */
    public void setUdc4(final String udc4) {
        this.udc4 = udc4;
    }

    /**
     * Gets the udc5.
     *
     * @return the udc5
     */
    public String getUdc5() {
        return udc5;
    }

    /**
     * Sets the udc5.
     *
     * @param udc5 the udc5
     */
    public void setUdc5(final String udc5) {
        this.udc5 = udc5;
    }

    /**
     * Gets the udc6.
     *
     * @return the udc6
     */
    public String getUdc6() {
        return udc6;
    }

    /**
     * Sets the udc6.
     *
     * @param udc6 the udc6
     */
    public void setUdc6(final String udc6) {
        this.udc6 = udc6;
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
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
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
     * Gets the company system id.
     *
     * @return the company system id
     */
    public String getCompanySystemId() {
        return companySystemId;
    }

    /**
     * Sets the company system id.
     *
     * @param companySystemId the company system id
     */
    public void setCompanySystemId(final String companySystemId) {
        this.companySystemId = companySystemId;
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
    public String getCompanyGroup() {
        return companyGroup;
    }

    /**
     * Sets the company group.
     *
     * @param companyGroup the company group
     */
    public void setCompanyGroup(final String companyGroup) {
        this.companyGroup = companyGroup;
    }

    /**
     * Gets the company category.
     *
     * @return the company category
     */
    public String getCompanyCategory() {
        return companyCategory;
    }

    /**
     * Sets the company category.
     *
     * @param companyCategory the company category
     */
    public void setCompanyCategory(final String companyCategory) {
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
    public Date getParentEndDate() {
        return parentEndDate;
    }

    /**
     * Sets the parent end date.
     *
     * @param parentEndDate the parent end date
     */
    public void setParentEndDate(final Date parentEndDate) {
        this.parentEndDate = parentEndDate;
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
     * Gets the country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country the country
     */
    public void setCountry(final String country) {
        this.country = country;
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
     * Gets the company type.
     *
     * @return the company type
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * Sets the company type.
     *
     * @param companyType the company type
     */
    public void setCompanyType(final String companyType) {
        this.companyType = companyType;
    }

    /**
     * Gets the prior parent start date.
     *
     * @return the prior parent start date
     */
    public Date getPriorParentStartDate() {
        return priorParentStartDate;
    }

    /**
     * Sets the prior parent start date.
     *
     * @param priorParentStartDate the prior parent start date
     */
    public void setPriorParentStartDate(final Date priorParentStartDate) {
        this.priorParentStartDate = priorParentStartDate;
    }

    /**
     * Gets the company status.
     *
     * @return the company status
     */
    public String getCompanyStatus() {
        return companyStatus;
    }

    /**
     * Sets the company status.
     *
     * @param companyStatus the company status
     */
    public void setCompanyStatus(final String companyStatus) {
        this.companyStatus = companyStatus;
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
    public Date getParentStartDate() {
        return parentStartDate;
    }

    /**
     * Sets the parent start date.
     *
     * @param parentStartDate the parent start date
     */
    public void setParentStartDate(final Date parentStartDate) {
        this.parentStartDate = parentStartDate;
    }

    /**
     * Gets the lives.
     *
     * @return the lives
     */
    public String getLives() {
        return lives;
    }

    /**
     * Sets the lives.
     *
     * @param lives the lives
     */
    public void setLives(final String lives) {
        this.lives = lives;
    }

    public String getDisplayCompanyType() {
        return displayCompanyType;
    }

    public void setDisplayCompanyType(String displayCompanyType) {
        this.displayCompanyType = displayCompanyType;
    }

    public String getDisplayCompanyStatus() {
        return displayCompanyStatus;
    }

    public void setDisplayCompanyStatus(String displayCompanyStatus) {
        this.displayCompanyStatus = displayCompanyStatus;
    }
    
    
}