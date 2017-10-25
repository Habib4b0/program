package com.stpl.app.global.company.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.stpl.app.util.HelperUtils;
import com.stpl.ifs.util.HelperDTO;

// TODO: Auto-generated Javadoc
/**
 * A DTO object which is used as a form object in create user and edit user
 * forms.
 *
 * @author arulmurugan
 */
public class CompanyMasterDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 599570797610289442L;

    /**
     * The company system id.
     */
    private String companySystemId;
    
    
    private String companyCategoryValue= HelperUtils.EMPTY;
    
    
    private String companyGroupValue= HelperUtils.EMPTY;
    
     private String tradeClassValue= HelperUtils.EMPTY;

    public String getTradeClassValue() {
        return tradeClassValue;
    }

    public void setTradeClassValue(String tradeClassValue) {
        this.tradeClassValue = tradeClassValue;
    }
    
    /**
     * The company system id.
     */
    private String SystemId = HelperUtils.EMPTY;

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
    
    private String infoId=StringUtils.EMPTY;

    private String infoNo = StringUtils.EMPTY;
    
    private String infoName = StringUtils.EMPTY;

    /**
     * The company name.
     */
    @NotBlank(message = "Company Name should  be present")
    private String companyName = HelperUtils.EMPTY;

    /**
     * The company start date.
     */
    @NotBlank(message = "Company Start Date should  be present")
    private Date companyStartDate;

    /**
     * The company end date.
     */
    @NotBlank(message = "Company End Date should  be present")
    private Date companyEndDate;

    /**
     * The prior parent company no.
     */
    private String priorParentCompanyNo = HelperUtils.EMPTY;

    /**
     * The state.
     */
    @NotBlank(message = "State should  be present")
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
    private HelperDTO organizationKey;

    /**
     * The created date.
     */
    private Date createdDate;

    /**
     * The created by.
     */
    private String createdBy = StringUtils.EMPTY;

    /**
     * The created by.
     */
    private Integer createdUserId = 0;

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
    private String modifiedBy = StringUtils.EMPTY;

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
    @NotBlank(message = "Zip Code should  be present")
    private String zipCode = HelperUtils.EMPTY;

    /**
     * The udc3.
     */
    private HelperDTO udc3;

    /**
     * The country.
     */
    @NotBlank(message = "Country should  be present")
    private HelperDTO country;

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
    private Date tradeClassEndDate;

    /**
     * The company type.
     */
    private HelperDTO companyType;
    
    /**
     * The company type value.
     */
    private String companyTypeValue;

    /**
     * The trade class start date.
     */
    private Date tradeClassStartDate;

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
    private HelperDTO companyStatus;
    
     /**
     * The company status value.
     */
    private String companyStatusValue;

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
    
    /** The old company no. */
    private String oldCompanyNo = HelperUtils.EMPTY;

    /**
     * The parent start date.
     */
    private Date parentStartDate;

    /**
     * The trade class1.
     */
    private HelperDTO tradeClass1;

    /**
     * The trade class s date.
     */
    private Date tradeClassSDate;

    /**
     * The trade class e date.
     */
    private Date tradeClassEDate;

    /**
     * The company identifier list.
     */
    private List<CompanyCrtIdentifierDTO> companyIdentifierList = new ArrayList<CompanyCrtIdentifierDTO>();

    /**
     * The record lock status.
     */
    private String recordLockStatus;
    
    /** The old parent id. */
    private int oldParentId;
    
    /** The parent company sys id. */
    private int parentCompanySysId;
    
    /** The parent company details sys id. */
    private int parentCompanyDetailsSysId;
    
    /** The prior parent company sys id. */
    private int priorParentCompanySysId;
    
    /** The trade class sys id. */
    private int tradeClassSysId;
    
    /** The prior trade class. */
    private String priorTradeClass=StringUtils.EMPTY;
    
    /** The old tc. */
    private String oldTC=StringUtils.EMPTY;
    
    /** The old parent start date. */
    private Date oldParentStartDate;
    
    /** The prior trade class start date. */
    private Date priorTradeClassStartDate;
    
    /** The old tc date. */
    private Date oldTCDate;

    private int tempCfpDetailsSystemId;
    
    private String internalNotes=StringUtils.EMPTY;
    
    /** The prior trade class. */
    private String source=StringUtils.EMPTY;

    private String parentCompanyName =StringUtils.EMPTY;
    
    
    public int getTempCfpDetailsSystemId() {
        return tempCfpDetailsSystemId;
    }

    public void setTempCfpDetailsSystemId(int tempCfpDetailsSystemId) {
        this.tempCfpDetailsSystemId = tempCfpDetailsSystemId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSystemId() {
        return SystemId;
    }

    public void setSystemId(String SystemId) {
        this.SystemId = SystemId;
    }

    public String getParentCompanyName() {
        return parentCompanyName;
    }

    public void setParentCompanyName(String parentCompanyName) {
        this.parentCompanyName = parentCompanyName;
    }
        /**
     * Gets the trade class1.
     *
     * @return the trade class1
     */
    public HelperDTO getTradeClass1() {
        return tradeClass1;
    }

    
    /**
     * Sets the trade class1.
     *
     * @param tradeClass1 the trade class1
     */
    public void setTradeClass1(HelperDTO tradeClass1) {
        this.tradeClass1 = tradeClass1;
    }

    /**
     * Gets the trade class s date.
     *
     * @return the trade class s date
     */
    public Date getTradeClassSDate() {
        return tradeClassSDate;
    }

    /**
     * Sets the trade class s date.
     *
     * @param tradeClassSDate the trade class s date
     */
    public void setTradeClassSDate(final Date tradeClassSDate) {
        this.tradeClassSDate = tradeClassSDate;
    }

    /**
     * Gets the trade class e date.
     *
     * @return the trade class e date
     */
    public Date getTradeClassEDate() {
        return tradeClassEDate;
    }

    /**
     * Sets the trade class e date.
     *
     * @param tradeClassEDate the trade class e date
     */
    public void setTradeClassEDate(final Date tradeClassEDate) {
        this.tradeClassEDate = tradeClassEDate;
    }

    /**
     * Gets the organization key.
     *
     * @return the organization key
     */
    public HelperDTO getOrganizationKey() {
        return organizationKey;
    }

    /**
     * Sets the organization key.
     *
     * @param organizationKey the organization key
     */
    public void setOrganizationKey(final HelperDTO organizationKey) {
        this.organizationKey = organizationKey;
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
     * Gets the company identifier list.
     *
     * @return the company identifier list
     */
    public List<CompanyCrtIdentifierDTO> getCompanyIdentifierList() {
        return companyIdentifierList;
    }

    /**
     * Sets the company identifier list.
     *
     * @param companyIdentifierList the company identifier list
     */
    public void setCompanyIdentifierList(final List<CompanyCrtIdentifierDTO> companyIdentifierList) {
        this.companyIdentifierList = companyIdentifierList;
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
     * Gets the company start date.
     *
     * @return the company start date
     */
    public Date getCompanyStartDate() {
        return companyStartDate;
    }

    /**
     * Sets the company start date.
     *
     * @param companyStartDate the company start date
     */
    public void setCompanyStartDate(final Date companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    /**
     * Gets the company end date.
     *
     * @return the company end date
     */
    public Date getCompanyEndDate() {
        return companyEndDate;
    }

    /**
     * Sets the company end date.
     *
     * @param companyEndDate the company end date
     */
    public void setCompanyEndDate(final Date companyEndDate) {
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
    public Date getTradeClassEndDate() {
        return tradeClassEndDate;
    }

    /**
     * Sets the trade class end date.
     *
     * @param tradeClassEndDate the trade class end date
     */
    public void setTradeClassEndDate(final Date tradeClassEndDate) {
        this.tradeClassEndDate = tradeClassEndDate;
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

    public String getCompanyCategoryValue() {
        return companyCategoryValue;
    }

    public void setCompanyCategoryValue(String companyCategoryValue) {
        this.companyCategoryValue = companyCategoryValue;
    }

    public String getCompanyGroupValue() {
        return companyGroupValue;
    }

    public void setCompanyGroupValue(String companyGroupValue) {
        this.companyGroupValue = companyGroupValue;
    }

    /**
     * Gets the trade class start date.
     *
     * @return the trade class start date
     */
    public Date getTradeClassStartDate() {
        return tradeClassStartDate;
    }

    /**
     * Sets the trade class start date.
     *
     * @param tradeClassStartDate the trade class start date
     */
    public void setTradeClassStartDate(final Date tradeClassStartDate) {
        this.tradeClassStartDate = tradeClassStartDate;
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

    /**
     * Gets the parent company sys id.
     *
     * @return the parent company sys id
     */
    public int getParentCompanySysId() {
        return parentCompanySysId;
    }

    /**
     * Sets the parent company sys id.
     *
     * @param parentCompanySysId the new parent company sys id
     */
    public void setParentCompanySysId(final int parentCompanySysId) {
        this.parentCompanySysId = parentCompanySysId;
    }

    /**
     * Gets the old parent id.
     *
     * @return the old parent id
     */
    public int getOldParentId() {
        return oldParentId;
    }

    /**
     * Sets the old parent id.
     *
     * @param oldParentId the new old parent id
     */
    public void setOldParentId(final int oldParentId) {
        this.oldParentId = oldParentId;
    }

    /**
     * Gets the parent company details sys id.
     *
     * @return the parent company details sys id
     */
    public int getParentCompanyDetailsSysId() {
        return parentCompanyDetailsSysId;
    }

    /**
     * Sets the parent company details sys id.
     *
     * @param parentCompanyDetailsSysId the new parent company details sys id
     */
    public void setParentCompanyDetailsSysId(final int parentCompanyDetailsSysId) {
        this.parentCompanyDetailsSysId = parentCompanyDetailsSysId;
    }

    /**
     * Gets the prior parent company sys id.
     *
     * @return the prior parent company sys id
     */
    public int getPriorParentCompanySysId() {
        return priorParentCompanySysId;
    }

    /**
     * Sets the prior parent company sys id.
     *
     * @param priorParentCompanySysId the new prior parent company sys id
     */
    public void setPriorParentCompanySysId(final int priorParentCompanySysId) {
        this.priorParentCompanySysId = priorParentCompanySysId;
    }

    /**
     * Gets the trade class sys id.
     *
     * @return the trade class sys id
     */
    public int getTradeClassSysId() {
        return tradeClassSysId;
    }

    /**
     * Sets the trade class sys id.
     *
     * @param tradeClassSysId the new trade class sys id
     */
    public void setTradeClassSysId(final int tradeClassSysId) {
        this.tradeClassSysId = tradeClassSysId;
    }

    /**
     * Gets the prior trade class.
     *
     * @return the prior trade class
     */
    public String getPriorTradeClass() {
        return priorTradeClass;
    }

    /**
     * Sets the prior trade class.
     *
     * @param priorTradeClass the new prior trade class
     */
    public void setPriorTradeClass(final String priorTradeClass) {
        this.priorTradeClass = priorTradeClass;
    }

    /**
     * Gets the old tc.
     *
     * @return the old tc
     */
    public String getOldTC() {
        return oldTC;
    }

    /**
     * Sets the old tc.
     *
     * @param oldTC the new old tc
     */
    public void setOldTC(final String oldTC) {
        this.oldTC = oldTC;
    }

    /**
     * Gets the old parent start date.
     *
     * @return the old parent start date
     */
    public Date getOldParentStartDate() {
        return oldParentStartDate;
    }

    /**
     * Sets the old parent start date.
     *
     * @param oldParentStartDate the new old parent start date
     */
    public void setOldParentStartDate(final Date oldParentStartDate) {
        this.oldParentStartDate = oldParentStartDate;
    }

    /**
     * Gets the prior trade class start date.
     *
     * @return the prior trade class start date
     */
    public Date getPriorTradeClassStartDate() {
        return priorTradeClassStartDate;
    }

    /**
     * Sets the prior trade class start date.
     *
     * @param priorTradeClassStartDate the new prior trade class start date
     */
    public void setPriorTradeClassStartDate(final Date priorTradeClassStartDate) {
        this.priorTradeClassStartDate = priorTradeClassStartDate;
    }

    /**
     * Gets the old tc date.
     *
     * @return the old tc date
     */
    public Date getOldTCDate() {
        return oldTCDate;
    }

    /**
     * Sets the old tc date.
     *
     * @param oldTCDate the new old tc date
     */
    public void setOldTCDate(final Date oldTCDate) {
        this.oldTCDate = oldTCDate;
    }

    /**
     * Gets the old company no.
     *
     * @return the old company no
     */
    public String getOldCompanyNo() {
        return oldCompanyNo;
    }

    /**
     * Sets the old company no.
     *
     * @param oldCompanyNo the new old company no
     */
    public void setOldCompanyNo(final String oldCompanyNo) {
        this.oldCompanyNo = oldCompanyNo;
    }

    public String getCompanyTypeValue() {
        return companyTypeValue;
    }

    public void setCompanyTypeValue(String companyTypeValue) {
        this.companyTypeValue = companyTypeValue;
    }

    public String getCompanyStatusValue() {
        return companyStatusValue;
    }

    public void setCompanyStatusValue(String companyStatusValue) {
        this.companyStatusValue = companyStatusValue;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public Integer getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getInfoNo() {
        return infoNo;
    }

    public void setInfoNo(String infoNo) {
        this.infoNo = infoNo;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }
    
}
