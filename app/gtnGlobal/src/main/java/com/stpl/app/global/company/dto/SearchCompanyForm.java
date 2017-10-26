package com.stpl.app.global.company.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.HelperUtils;
import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchCompanyForm.
 */
public class SearchCompanyForm implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 613970373488326921L;

    /**
     * The system id.
     */
    private String systemId;

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
    private String companyStatus = StringUtils.EMPTY;

    
    /** The company system id. */
    private String companySystemId; 
    /**
     * The company type.
     */
    private String companyType;

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
    private String companyStartDate = StringUtils.EMPTY;

     /**
     * The company start date.
     */
    private Date compStartDate;

   
    /**
     * The company end date.
     */
    private String companyEndDate = StringUtils.EMPTY;

      /**
     * The company start date.
     */
    private Date compEndDate;
    /**
     * The prior parent company no.
     */
    private String priorParentCompanyNo = HelperUtils.EMPTY;
    /**
     * The financial system.
     */
    private String financialSystem = HelperUtils.EMPTY;

    /**
     * The company group.
     */
    private String companyGroup = StringUtils.EMPTY;

    /**
     * The company category.
     */
    private String companyCategory = StringUtils.EMPTY;

    /**
     * The last updated date.
     */
    private Date lastUpdatedDate;

    /**
     * The parent end date.
     */
    private String parentEndDate  = StringUtils.EMPTY;
    
    /**
     * The parent end date.
     */
    private Date parentEDate;

    /**
     * The modified date.
     */
    private Date modifiedDate;

    /**
     * The lives.
     */
    private String lives = HelperUtils.EMPTY;

    /**
     * The organization key.
     */
    private String organizationKey = StringUtils.EMPTY;

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
    private String tradeClass = StringUtils.EMPTY;

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
    private String udc6 = StringUtils.EMPTY;

    /**
     * The udc5.
     */
    private String udc5 = StringUtils.EMPTY;

    /**
     * The udc4.
     */
    private String udc4 = StringUtils.EMPTY;

    /**
     * The udc1.
     */
    private String udc1 = StringUtils.EMPTY;

    /**
     * The udc2.
     */
    private String udc2 = StringUtils.EMPTY;

    /**
     * The udc3.
     */
    private String udc3 = StringUtils.EMPTY;

    /**
     * The country.
     */
    private String country = StringUtils.EMPTY;

    /**
     * The state.
     */
    private String state = StringUtils.EMPTY;

    /**
     * The zip code.
     */
    private String zipCode = HelperUtils.EMPTY;

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
    private String tradeClassEndDate = StringUtils.EMPTY;
    
     /**
     * The trade class end date.
     */
    private Date tradeEndDate;

    /**
     * The trade class start date.
     */
    private String tradeClassStartDate = StringUtils.EMPTY;
    
    /**
     * The trade class start date.
     */
    private Date tradeStartDate;

    /**
     * The prior parent start date.
     */
    private String priorParentStartDate = StringUtils.EMPTY;
    
     /**
     * The prior parent start date.
     */
    private Date priorParentSDate;

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
    private String parentStartDate = StringUtils.EMPTY;
        
     /**
     * The parent start date.
     */
    private Date parentSDate;
    
     /**
     * The identifier type desc.
     */
    private HelperDTO identifierType;

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
     * Gets the financial system.
     *
     * @return the financial system
     */
    public String getFinancialSystem() {
        return StringUtils.trimToEmpty(financialSystem);
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
     * Gets the system id.
     *
     * @return the system id
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Sets the system id.
     *
     * @param systemId the system id
     */
    public void setSystemId(final String systemId) {
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
     * Gets the search flag.
     *
     * @return the search flag
     */
    public Boolean getSearchFlag() {
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
     * Gets the qualifier flag.
     *
     * @return the qualifier flag
     */
    public Boolean getQualifierFlag() {
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
     * Gets the identifier flag.
     *
     * @return the identifier flag
     */
    public Boolean getIdentifierFlag() {
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
     * @param companySystemId the new company system id
     */
    public void setCompanySystemId(final String companySystemId) {
        this.companySystemId = companySystemId;
    }
    
     public Date getCompStartDate() {
        return compStartDate;
    }

    public void setCompStartDate(Date compStartDate) {
        this.compStartDate = compStartDate;
    }

    public Date getCompEndDate() {
        return compEndDate;
    }

    public void setCompEndDate(Date compEndDate) {
        this.compEndDate = compEndDate;
    }

    public Date getParentEDate() {
        return parentEDate;
    }

    public void setParentEDate(Date parentEDate) {
        this.parentEDate = parentEDate;
    }

    public Date getTradeEndDate() {
        return tradeEndDate;
    }

    public void setTradeEndDate(Date tradeEndDate) {
        this.tradeEndDate = tradeEndDate;
    }

    public Date getTradeStartDate() {
        return tradeStartDate;
    }

    public void setTradeStartDate(Date tradeStartDate) {
        this.tradeStartDate = tradeStartDate;
    }

    public Date getPriorParentSDate() {
        return priorParentSDate;
    }

    public void setPriorParentSDate(Date priorParentSDate) {
        this.priorParentSDate = priorParentSDate;
    }

    public Date getParentSDate() {
        return parentSDate;
    }

    public void setParentSDate(Date parentSDate) {
        this.parentSDate = parentSDate;
    }
    
    
   public static Comparator<SearchCompanyForm> companyIdAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyId1 = s1.getCompanyId().toUpperCase();
	   String companyId2 = s2.getCompanyId().toUpperCase();
	   return companyId1.compareTo(companyId2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyIdDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyId1 = s1.getCompanyId().toUpperCase();
	   String companyId2 = s2.getCompanyId().toUpperCase();
	   return companyId2.compareTo(companyId1);
    }};     
      
   public static Comparator<SearchCompanyForm> companyNoAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyNo1 = s1.getCompanyNo().toUpperCase();
	   String companyNo2 = s2.getCompanyNo().toUpperCase();
	   return companyNo1.compareTo(companyNo2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyNoDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyNo1 = s1.getCompanyNo().toUpperCase();
	   String companyNo2 = s2.getCompanyNo().toUpperCase();
	   return companyNo2.compareTo(companyNo1);
    }};     
   
   
   public static Comparator<SearchCompanyForm> companyNameAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyName1 = s1.getCompanyName().toUpperCase();
	   String companyName2 = s2.getCompanyName().toUpperCase();
	   return companyName1.compareTo(companyName2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyNameDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyName1 = s1.getCompanyName().toUpperCase();
	   String companyName2 = s2.getCompanyName().toUpperCase();
	   return companyName2.compareTo(companyName1);
    }};     
   
   
   public static Comparator<SearchCompanyForm> companyStatusAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyStatus1 = String.valueOf(s1.getCompanyStatus()).toUpperCase();
	   String companyStatus2 = String.valueOf(s2.getCompanyStatus()).toUpperCase();
	   return companyStatus1.compareTo(companyStatus2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyStatusDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyStatus1 = String.valueOf(s1.getCompanyStatus()).toUpperCase();
	   String companyStatus2 = String.valueOf(s2.getCompanyStatus()).toUpperCase();
	   return companyStatus2.compareTo(companyStatus1);
    }};        
   
   public static Comparator<SearchCompanyForm> companyTypeAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyStatus1 = String.valueOf(s1.getCompanyType()).toUpperCase();
	   String companyStatus2 = String.valueOf(s2.getCompanyType()).toUpperCase();
	   return companyStatus1.compareTo(companyStatus2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyTypeDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyStatus1 = String.valueOf(s1.getCompanyType()).toUpperCase();
	   String companyStatus2 = String.valueOf(s2.getCompanyType()).toUpperCase();
	   return companyStatus2.compareTo(companyStatus1);
    }};        
 
   
   public static Comparator<SearchCompanyForm> companyQualifierNameAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyQualifierName1 = s1.getIdentifierTypeDesc().getDescription().toUpperCase();
	   String companyQualifierName2 = s2.getIdentifierTypeDesc().getDescription().toUpperCase();
	   return companyQualifierName1.compareTo(companyQualifierName2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyQualifierNameDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyQualifierName1 = s1.getIdentifierTypeDesc().getDescription().toUpperCase();
	   String companyQualifierName2 = s2.getIdentifierTypeDesc().getDescription().toUpperCase();
	   return companyQualifierName2.compareTo(companyQualifierName1);
    }};         
   
   
   public static Comparator<SearchCompanyForm> companyIdentifierAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyIdentifier1 = s1.getIdentifier().toUpperCase();
	   String companyIdentifier2 = s2.getIdentifier().toUpperCase();
	   return companyIdentifier1.compareTo(companyIdentifier2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyIdentiferDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String companyIdentifier1 = s1.getIdentifier().toUpperCase();
	   String companyIdentifier2 = s2.getIdentifier().toUpperCase();
	   return companyIdentifier2.compareTo(companyIdentifier1);
    }};        
   
   
   public static Comparator<SearchCompanyForm> companyStartDateAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String startDate1 = s1.getCompanyStartDate().toUpperCase();
	   String startDate2 = s2.getCompanyStartDate().toUpperCase();
	   return startDate1.compareTo(startDate2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyStartDateDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String startDate1 = s1.getCompanyStartDate().toUpperCase();
	   String startDate2 = s2.getCompanyStartDate().toUpperCase();
	   return startDate2.compareTo(startDate1);
    }};           
   
   
   public static Comparator<SearchCompanyForm> companyEndDateAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String endDate1 = s1.getCompanyEndDate().toUpperCase();
	   String endDate2 = s2.getCompanyEndDate().toUpperCase();
	   return endDate1.compareTo(endDate2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyEndDateDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String endDate1 = s1.getCompanyEndDate().toUpperCase();
	   String endDate2 = s2.getCompanyEndDate().toUpperCase();
	   return endDate2.compareTo(endDate1);
    }};              
   
   
   public static Comparator<SearchCompanyForm> companyTradeClassAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String tradeClass1 = s1.getTradeClass().toUpperCase();
	   String tradeClass2 = s2.getTradeClass().toUpperCase();
	   return tradeClass1.compareTo(tradeClass2);
    }};      
    
   public static Comparator<SearchCompanyForm> companyTradeClassDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String tradeClass1 = s1.getTradeClass().toUpperCase();
	   String tradeClass2 = s2.getTradeClass().toUpperCase();
	   return tradeClass2.compareTo(tradeClass1);
    }};               
   
   
   public static Comparator<SearchCompanyForm> companyTcStartDateAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String tcStartDate1 = s1.getTradeClassStartDate().toUpperCase();
	   String tcStartDate2 = s2.getTradeClassStartDate().toUpperCase();
	   return tcStartDate1.compareTo(tcStartDate2);
    }};      
   
   public static Comparator<SearchCompanyForm> companyTcStartDateDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String tcStartDate1 = s1.getTradeClassStartDate().toUpperCase();
	   String tcStartDate2 = s2.getTradeClassStartDate().toUpperCase();
	   return tcStartDate2.compareTo(tcStartDate1);
    }};    
    
   public static Comparator<SearchCompanyForm> companyTcEndDateAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String tcEndDate1 = s1.getTradeClassEndDate().toUpperCase();
	   String tcEndDate2 = s2.getTradeClassEndDate().toUpperCase();
	   return tcEndDate2.compareTo(tcEndDate1);
    }};                  
      
   public static Comparator<SearchCompanyForm> companyTcEndDateDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String tcEndDate1 = s1.getTradeClassEndDate().toUpperCase();
	   String tcEndDate2 = s2.getTradeClassEndDate().toUpperCase();
	   return tcEndDate2.compareTo(tcEndDate1);
    }};            
   
   
   public static Comparator<SearchCompanyForm> companyLivesAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String lives1 = s1.getLives().toUpperCase();
	   String lives2 = s2.getLives().toUpperCase();
	   return lives1.compareTo(lives2);
    }};                  
      
   public static Comparator<SearchCompanyForm> companyLivesDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String lives1 = s1.getLives().toUpperCase();
	   String lives2 = s2.getLives().toUpperCase();
	   return lives2.compareTo(lives1);
    }};    
   
   
   
   public static Comparator<SearchCompanyForm> companyGroupAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String compnyGroup1 = s1.getCompanyGroup().toUpperCase();
	   String compnyGroup2 = s2.getCompanyGroup().toUpperCase();
	   return compnyGroup1.compareTo(compnyGroup2);
    }};                  
      
   public static Comparator<SearchCompanyForm> companyGroupDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String compnyGroup1 = s1.getCompanyGroup().toUpperCase();
	   String compnyGroup2 = s2.getCompanyGroup().toUpperCase();
	   return compnyGroup2.compareTo(compnyGroup1);
    }};       
   
   
   public static Comparator<SearchCompanyForm> companyCategoryAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getCompanyCategory().toUpperCase();
	   String value2 = s2.getCompanyCategory().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> companyCategoryDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getCompanyCategory().toUpperCase();
	   String value2 = s2.getCompanyCategory().toUpperCase();
	   return value2.compareTo(value1);
    }};      
   
   public static Comparator<SearchCompanyForm> organisationAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getOrganizationKey().toUpperCase();
	   String value2 = s2.getOrganizationKey().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> organisationDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getOrganizationKey().toUpperCase();
	   String value2 = s2.getOrganizationKey().toUpperCase();
	   return value2.compareTo(value1);
    }};         
   
   
   public static Comparator<SearchCompanyForm> financialAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getFinancialSystem().toUpperCase();
	   String value2 = s2.getFinancialSystem().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> financialDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getFinancialSystem().toUpperCase();
	   String value2 = s2.getFinancialSystem().toUpperCase();
	   return value2.compareTo(value1);
    }};            
   
   
   public static Comparator<SearchCompanyForm> parentNoAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getParentCompanyNo().toUpperCase();
	   String value2 = s2.getParentCompanyNo().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> parentNoDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getParentCompanyNo().toUpperCase();
	   String value2 = s2.getParentCompanyNo().toUpperCase();
	   return value2.compareTo(value1);
    }};      
   
   
   public static Comparator<SearchCompanyForm> parentStartDateAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getParentStartDate().toUpperCase();
	   String value2 = s2.getParentStartDate().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> parentStartDateDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getParentStartDate().toUpperCase();
	   String value2 = s2.getParentStartDate().toUpperCase();
	   return value2.compareTo(value1);
    }};       
   
   public static Comparator<SearchCompanyForm> parentEndDateAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getParentEndDate().toUpperCase();
	   String value2 = s2.getParentEndDate().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> parentEndDateDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getParentEndDate().toUpperCase();
	   String value2 = s2.getParentEndDate().toUpperCase();
	   return value2.compareTo(value1);
    }};          
   
   public static Comparator<SearchCompanyForm> priorParentNoAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getPriorParentCompanyNo().toUpperCase();
	   String value2 = s2.getPriorParentCompanyNo().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> priorParentNoDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getPriorParentCompanyNo().toUpperCase();
	   String value2 = s2.getPriorParentCompanyNo().toUpperCase();
	   return value2.compareTo(value1);
    }};             
   
   public static Comparator<SearchCompanyForm> priorParentStartDateAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getPriorParentStartDate().toUpperCase();
	   String value2 = s2.getPriorParentStartDate().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> priorParentStartDateDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getPriorParentStartDate().toUpperCase();
	   String value2 = s2.getPriorParentStartDate().toUpperCase();
	   return value2.compareTo(value1);
    }};                
   
   public static Comparator<SearchCompanyForm> regionCodeAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getRegionCode().toUpperCase();
	   String value2 = s2.getRegionCode().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> regionCodeDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getRegionCode().toUpperCase();
	   String value2 = s2.getRegionCode().toUpperCase();
	   return value2.compareTo(value1);
    }};       
   
   public static Comparator<SearchCompanyForm> udc1Asc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc1().toUpperCase();
	   String value2 = s2.getUdc1().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> udc1Dsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc1().toUpperCase();
	   String value2 = s2.getUdc1().toUpperCase();
	   return value2.compareTo(value1);
    }};     
   
   public static Comparator<SearchCompanyForm> udc2Asc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc2().toUpperCase();
	   String value2 = s2.getUdc2().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> udc2Dsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc2().toUpperCase();
	   String value2 = s2.getUdc2().toUpperCase();
	   return value2.compareTo(value1);
    }};        
   
   public static Comparator<SearchCompanyForm> udc3Asc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc3().toUpperCase();
	   String value2 = s2.getUdc3().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> udc3Dsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc3().toUpperCase();
	   String value2 = s2.getUdc3().toUpperCase();
	   return value2.compareTo(value1);
    }};      
   
   public static Comparator<SearchCompanyForm> udc4Asc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc4().toUpperCase();
	   String value2 = s2.getUdc4().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> udc4Dsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc4().toUpperCase();
	   String value2 = s2.getUdc4().toUpperCase();
	   return value2.compareTo(value1);
    }};     
   
   public static Comparator<SearchCompanyForm> udc5Asc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc5().toUpperCase();
	   String value2 = s2.getUdc5().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> udc5Dsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc5().toUpperCase();
	   String value2 = s2.getUdc5().toUpperCase();
	   return value2.compareTo(value1);
    }};        
   
   public static Comparator<SearchCompanyForm> udc6Asc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc6().toUpperCase();
	   String value2 = s2.getUdc6().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> udc6Dsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getUdc6().toUpperCase();
	   String value2 = s2.getUdc6().toUpperCase();
	   return value2.compareTo(value1);
    }};           
   
   public static Comparator<SearchCompanyForm> companyAddess1Asc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String address1 = s1.getAddress1().toUpperCase();
	   String address2 = s2.getAddress1().toUpperCase();
	   //ascending order
	   return address1.compareTo(address2);
    }};    
   
   public static Comparator<SearchCompanyForm> companyAddess1Dsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String address1 = s1.getAddress1().toUpperCase();
	   String address2 = s2.getAddress1().toUpperCase();
	   //descending order
	   return address2.compareTo(address1);
    }};   
   
   public static Comparator<SearchCompanyForm> companyAddess2Asc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String address1 = s1.getAddress2().toUpperCase();
	   String address2 = s2.getAddress2().toUpperCase();
	   //ascending order
	   return address1.compareTo(address2);
    }};    
   
   public static Comparator<SearchCompanyForm> companyAddess2Dsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String address1 = s1.getAddress2().toUpperCase();
	   String address2 = s2.getAddress2().toUpperCase();
	   //descending order
	   return address2.compareTo(address1);
    }};      
   
   public static Comparator<SearchCompanyForm> zipCodeAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getZipCode().toUpperCase();
	   String value2 = s2.getZipCode().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> zipCodeDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getZipCode().toUpperCase();
	   String value2 = s2.getZipCode().toUpperCase();
	   return value2.compareTo(value1);
    }};         
   
   public static Comparator<SearchCompanyForm> cityAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getCity().toUpperCase();
	   String value2 = s2.getCity().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> cityDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getCity().toUpperCase();
	   String value2 = s2.getCity().toUpperCase();
	   return value2.compareTo(value1);
    }};         
   
   public static Comparator<SearchCompanyForm> stateAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getState().toUpperCase();
	   String value2 = s2.getState().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> stateDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getState().toUpperCase();
	   String value2 = s2.getState().toUpperCase();
	   return value2.compareTo(value1);
    }};    
   
   public static Comparator<SearchCompanyForm> countryAsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getCountry().toUpperCase();
	   String value2 = s2.getCountry().toUpperCase();
	   return value1.compareTo(value2);
    }};                  
      
   public static Comparator<SearchCompanyForm> countryDsc = new Comparator<SearchCompanyForm>() {
	public int compare(SearchCompanyForm s1, SearchCompanyForm s2) {
	   String value1 = s1.getCountry().toUpperCase();
	   String value2 = s2.getCountry().toUpperCase();
	   return value2.compareTo(value1);
    }};       

    public HelperDTO getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(HelperDTO identifierType) {
        this.identifierType = identifierType;
    }
}
