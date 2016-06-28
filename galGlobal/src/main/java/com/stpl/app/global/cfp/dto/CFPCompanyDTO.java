package com.stpl.app.global.cfp.dto;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperUtils;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 * The Class CFPCompanyDTO.
 */
public class CFPCompanyDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -9084529327026132883L;
    /**
     * The company system id.
     */
    private String companySystemId=StringUtils.EMPTY;
    /**
     * The company id.
     */
    private String companyId=StringUtils.EMPTY;
    /**
     * The company no.
     */
    private String companyNo=StringUtils.EMPTY;
    /**
     * The company name.
     */
    private String companyName=StringUtils.EMPTY;
    /**
     * The company start date.
     */
    private String companyStartDate=StringUtils.EMPTY;
    /**
     * The company end date.
     */
    private String companyEndDate=StringUtils.EMPTY;
    /**
     * The company family plan system id.
     */
    private int companyFamilyPlanSystemId;
    /**
     * The company family plan id.
     */
    private String companyFamilyPlanId = StringUtils.EMPTY;
    /**
     * The company family plan no.
     */
    private String companyFamilyPlanNo = StringUtils.EMPTY;
    /**
     * The company family plan name.
     */
    private String companyFamilyPlanName = StringUtils.EMPTY;
    /**
     * The company status.
     */
    private int companyStatus;
    /**
     * The company family plan start date.
     */
    private Date companyFamilyPlanStartDate;
    /**
     * The company family plan end date.
     */
    private Date companyFamilyPlanEndDate;
    /**
     * The checkbox.
     */
    private Boolean checkbox;
    /**
     * The company type.
     */
    private String companyType=StringUtils.EMPTY;
    
    /**
     * The company type value.
     */
    private String companyTypeValue=StringUtils.EMPTY;
    
    /**
     * The company status value.
     */
    private String companyStatusValue=StringUtils.EMPTY;
    
    /**
     * The company family plan status value
     */
    private String companyFamilyPlanStatusValue=StringUtils.EMPTY;

    /**
     * The company family plan status.
     */
    private HelperDTO companyFamilyPlanStatus =  new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The company family plan type.
     */
    private HelperDTO companyFamilyPlanType = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The trade class.
     */
    private HelperDTO companyFamilyPlanTradeClass = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    /**
     * The companyFamilyPlanCategory
     */
    private HelperDTO companyFamilyPlanCategory = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    /**
     * The companyFamilyPlanDesignation
     */
    private HelperDTO companyFamilyPlanDesignation=new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    
    /**
     * The parentCompanyFamilyPlanName
     */
    private String parentCompanyFamilyPlanName = StringUtils.EMPTY;
    
    /**
     * The parentCompanyFamilyPlanId.
     */
    private String parentCompanyFamilyPlanId = StringUtils.EMPTY;
    
    private String companyCategory = StringUtils.EMPTY;
    
    /**
     * The createdBy.
     */
    private String createdBy= StringUtils.EMPTY;
    
      /**
     * The modifiedBy.
     */
    private String modifiedBy= StringUtils.EMPTY;
    
      /**
     * The modifiedBy.
     */
    private Date modifiedDate;
    
     
    private String companyCategoryValue= HelperUtils.EMPTY;
    
    
    private String companyGroupValue= HelperUtils.EMPTY;
    
    private String tradeClassValue= HelperUtils.EMPTY;

    public String getTradeClassValue() {
        return tradeClassValue;
    }

    public void setTradeClassValue(String tradeClassValue) {
        this.tradeClassValue = tradeClassValue;
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
     * The record lock status.
     */
    private boolean recordLockStatus;
    
    /**
     * The inbound status.
     */
    private String inboundStatus = StringUtils.EMPTY;
    /**
     * The trade class.
     */
    private String tradeClass=StringUtils.EMPTY;
    /**
     * The trade class start date.
     */
    private String tradeClassStartDate=StringUtils.EMPTY;
    /**
     * The trade class end date.
     */
    private String tradeClassEndDate=StringUtils.EMPTY;
    /**
     * The attached date.
     */
    private String attachedDate=StringUtils.EMPTY;
    /**
     * The created date.
     */
    private Date createdDate;
    /**
     * The trading partner contract no.
     */
    private String tradingPartnerContractNo = StringUtils.EMPTY;
    /**
     * The operationStatus.
     */
    private String operationStatus = StringUtils.EMPTY;
    /**
     * The CFP Details SystemId.
     */
    private int cfpDetailsSystemId = 0;
    /**
     * The CFP Details SystemId.
     */
    private int tempCfpDetailsSystemId = 0;
    /**
     * The userID.
     */
    private String userID = StringUtils.EMPTY;
    /**
     * The sessionID.
     */
    private String sessionID = StringUtils.EMPTY;
    
    private boolean checkFlag =false;
    
    private String searchFields=ConstantsUtils.SELECT_ONE;
    
    private String searchValue = StringUtils.EMPTY;
    
    private String massCheck=StringUtils.EMPTY;
    
    private String internalNotes=StringUtils.EMPTY;
    
    private Integer parentFlagSID=0;
    
     /**
     * The company start date.
     */
    private Date cfpCompanyStartDate;
    /**
     * The company end date.
     */
    private Date cfpCompanyEndDate;

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }
    
    
    
    /**
     * The attached date.
     */
    private Date cfpAttachedDate;
    
    public int getTempCfpDetailsSystemId() {
        return tempCfpDetailsSystemId;
    }

    public void setTempCfpDetailsSystemId(int tempCfpDetailsSystemId) {
        this.tempCfpDetailsSystemId = tempCfpDetailsSystemId;
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

    
    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public int getCfpDetailsSystemId() {
        return cfpDetailsSystemId;
    }

    public void setCfpDetailsSystemId(int cfpDetailsSystemId) {
        this.cfpDetailsSystemId = cfpDetailsSystemId;
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
     * Gets the company family plan status.
     *
     * @return the company family plan status
     */
    public HelperDTO getCompanyFamilyPlanStatus() {
        return companyFamilyPlanStatus;
    }

    /**
     * Sets the company family plan status.
     *
     * @param companyFamilyPlanStatus the company family plan status
     */
    public void setCompanyFamilyPlanStatus(final HelperDTO companyFamilyPlanStatus) {
        this.companyFamilyPlanStatus = companyFamilyPlanStatus;
    }

    public HelperDTO getCompanyFamilyPlanType() {
        return companyFamilyPlanType;
    }

    public void setCompanyFamilyPlanType(HelperDTO companyFamilyPlanType) {
        this.companyFamilyPlanType = companyFamilyPlanType;
    }

    public HelperDTO getCompanyFamilyPlanTradeClass() {
        return companyFamilyPlanTradeClass;
    }

    public void setCompanyFamilyPlanTradeClass(HelperDTO companyFamilyPlanTradeClass) {
        this.companyFamilyPlanTradeClass = companyFamilyPlanTradeClass;
    }

    public HelperDTO getCompanyFamilyPlanCategory() {
        return companyFamilyPlanCategory;
    }

    public void setCompanyFamilyPlanCategory(HelperDTO companyFamilyPlanCategory) {
        this.companyFamilyPlanCategory = companyFamilyPlanCategory;
    }

    public HelperDTO getCompanyFamilyPlanDesignation() {
        return companyFamilyPlanDesignation;
    }

    public void setCompanyFamilyPlanDesignation(HelperDTO companyFamilyPlanDesignation) {
        this.companyFamilyPlanDesignation = companyFamilyPlanDesignation;
    }

    public String getParentCompanyFamilyPlanName() {
        return parentCompanyFamilyPlanName;
    }

    public void setParentCompanyFamilyPlanName(String parentCompanyFamilyPlanName) {
        this.parentCompanyFamilyPlanName = parentCompanyFamilyPlanName;
    }

    public String getParentCompanyFamilyPlanId() {
        return parentCompanyFamilyPlanId;
    }

    public void setParentCompanyFamilyPlanId(String parentCompanyFamilyPlanId) {
        this.parentCompanyFamilyPlanId = parentCompanyFamilyPlanId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isRecordLockStatus() {
        return recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    public String getInboundStatus() {
        return inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        this.inboundStatus = inboundStatus;
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
     * The Constructor.
     *
     * @param companySystemId the system id
     * @param companyFamilyPlanId the company family plan id
     * @param companyFamilyPlanSystemId the company family plan system id
     * @param companyFamilyPlanName the company family plan name
     * @param companyFamilyPlanNo the company family plan no
     * @param companyType the company type,
     * @param companyId the company id,
     * @param companyNo the company no
     * @param companyName the company name,
     * @param companyStatus the company status,
     * @param tradeClass the trade class
     * @param tradeClassStartDate the start date,
     * @param tradeClassEndDate the end date
     * @param attachedDate the attached date
     * @param companyStartDate the start date,
     * @param companyEndDate the end date
     * @param createdDate the created date
     */
    public CFPCompanyDTO() {
    }

    public CFPCompanyDTO(final String companySystemId, final String companyFamilyPlanId,
            final int companyFamilyPlanSystemId, final String companyFamilyPlanName,
            final String companyFamilyPlanNo, final String companyType, final String companyId, final String companyNo,
            final String companyName, final int companyStatus, final String tradeClass,
            final String tradeClassStartDate, final String tradeClassEndDate,
            final String attachedDate, final String companyStartDate, final String companyEndDate, final Date createdDate) {

        this.companySystemId = companySystemId;

        this.companyNo = companyNo;
        this.companyName = companyName;
        this.companyStartDate = companyStartDate;
        this.companyEndDate = companyEndDate;
        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
        this.companyFamilyPlanId = companyFamilyPlanId;
        this.companyFamilyPlanNo = companyFamilyPlanNo;
        this.companyFamilyPlanName = companyFamilyPlanName;
        this.companyType = companyType;
        this.companyId = companyId;
        this.companyStatus = companyStatus;
        this.tradeClass = tradeClass;
        this.tradeClassStartDate = tradeClassStartDate;
        this.tradeClassEndDate = tradeClassEndDate;
        this.attachedDate = attachedDate;
        this.createdDate = createdDate;

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
     * Gets the attached date.
     *
     * @return the attached date
     */
    public String getAttachedDate() {
        return attachedDate;
    }

    /**
     * Sets the attached date.
     *
     * @param attachedDate the attached date
     */
    public void setAttachedDate(final String attachedDate) {
        this.attachedDate = attachedDate;
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
     * The Constructor.
     *
     * @param companySystemId -system id,
     * @param companyFamilyPlanId the company family plan id
     * @param companyFamilyPlanSystemId-system id,
     * @param companyFamilyPlanName the company family plan name
     * @param companyFamilyPlanNo the company family plan no
     * @param companyFamilyPlanStatus the company family plan status
     * @param tradeClass the trade class,
     * @param tradeClassStartDate the trade class start date
     * @param tradeClassEndDate the trade class end date,
     * @param attachedDate the attached date
     * @param companyId the company id,
     * @param companyNo the company no
     * @param companyName the company name,
     * @param companyStartDate the company start date
     * @param companyEndDate the company end date,
     * @param status the status
     * @param companyFamilyPlanStartDate the start date,
     * @param companyFamilyPlanEndDate the end date
     * @param companyType the company type,
     * @param checkBox the check box,
     * @param createdDate the created date
     */
    public CFPCompanyDTO(final String companySystemId, final String companyFamilyPlanId,
            final int companyFamilyPlanSystemId, final String companyFamilyPlanName,
            final String companyFamilyPlanNo, final HelperDTO companyFamilyPlanStatus, final HelperDTO companyFamilyPlanType,
            final String tradeClass, final String tradeClassStartDate,
            final String tradeClassEndDate, final String attachedDate, final String companyId, final String companyNo,
            final String companyName, final String companyStartDate, final String companyEndDate,
            final int status, final Date companyFamilyPlanStartDate,
            final Date companyFamilyPlanEndDate, final String companyType, final boolean checkBox, final Date createdDate) {

        this.companySystemId = companySystemId;

        this.companyId = companyId;
        this.companyNo = companyNo;
        this.companyName = companyName;
        this.companyStartDate = companyStartDate;
        this.companyEndDate = companyEndDate;
        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
        this.companyFamilyPlanId = companyFamilyPlanId;
        this.companyFamilyPlanNo = companyFamilyPlanNo;
        this.companyFamilyPlanName = companyFamilyPlanName;
        this.companyFamilyPlanStartDate = companyFamilyPlanStartDate;
        this.companyFamilyPlanEndDate = companyFamilyPlanEndDate;
        this.companyStatus = status;
        this.checkbox = checkBox;

        this.companyType = companyType;
        this.companyFamilyPlanStatus = companyFamilyPlanStatus;
        this.companyFamilyPlanType = companyFamilyPlanType;
        this.tradeClass = tradeClass;
        this.tradeClassStartDate = tradeClassStartDate;
        this.tradeClassEndDate = tradeClassEndDate;
        this.attachedDate = attachedDate;
        this.createdDate = createdDate;

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
     * Gets the company family plan system id.
     *
     * @return the company family plan system id
     */
    public int getCompanyFamilyPlanSystemId() {
        return companyFamilyPlanSystemId;
    }

    /**
     * Sets the company family plan system id.
     *
     * @param companyFamilyPlanSystemId the company family plan system id
     */
    public void setCompanyFamilyPlanSystemId(final int companyFamilyPlanSystemId) {
        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
    }

    /**
     * Gets the company family plan id.
     *
     * @return the company family plan id
     */
    public String getCompanyFamilyPlanId() {
        return companyFamilyPlanId;
    }

    /**
     * Sets the company family plan id.
     *
     * @param companyFamilyPlanId the company family plan id
     */
    public void setCompanyFamilyPlanId(final String companyFamilyPlanId) {
        this.companyFamilyPlanId = companyFamilyPlanId;
    }

    /**
     * Gets the company family plan no.
     *
     * @return the company family plan no
     */
    public String getCompanyFamilyPlanNo() {
        return companyFamilyPlanNo;
    }

    /**
     * Sets the company family plan no.
     *
     * @param companyFamilyPlanNo the company family plan no
     */
    public void setCompanyFamilyPlanNo(final String companyFamilyPlanNo) {
        this.companyFamilyPlanNo = companyFamilyPlanNo;
    }

    /**
     * Gets the company family plan name.
     *
     * @return the company family plan name
     */
    public String getCompanyFamilyPlanName() {
        return companyFamilyPlanName;
    }

    /**
     * Sets the company family plan name.
     *
     * @param companyFamilyPlanName the company family plan name
     */
    public void setCompanyFamilyPlanName(final String companyFamilyPlanName) {
        this.companyFamilyPlanName = companyFamilyPlanName;
    }

    /**
     * Gets the company status.
     *
     * @return the company status
     */
    public int getCompanyStatus() {
        return companyStatus;
    }

    /**
     * Sets the company status.
     *
     * @param companyStatus the company status
     */
    public void setCompanyStatus(final int companyStatus) {
        this.companyStatus = companyStatus;
    }

    /**
     * Gets the company family plan start date.
     *
     * @return the company family plan start date
     */
    public Date getCompanyFamilyPlanStartDate() {
        return companyFamilyPlanStartDate;
    }

    /**
     * Sets the company family plan start date.
     *
     * @param companyFamilyPlanStartDate the company family plan start date
     */
    public void setCompanyFamilyPlanStartDate(final Date companyFamilyPlanStartDate) {
        this.companyFamilyPlanStartDate = companyFamilyPlanStartDate;
    }

    /**
     * testing.
     *
     * @return the company family plan end date
     */
    public Date getCompanyFamilyPlanEndDate() {
        return companyFamilyPlanEndDate;
    }

    /**
     * Sets the company family plan end date.
     *
     * @param companyFamilyPlanEndDate the company family plan end date
     */
    public void setCompanyFamilyPlanEndDate(final Date companyFamilyPlanEndDate) {
        this.companyFamilyPlanEndDate = companyFamilyPlanEndDate;
    }

    /**
     * Gets the checkbox.
     *
     * @return the checkbox
     */
    public Boolean getCheckbox() {
        return checkbox;
    }

    /**
     * Sets the checkbox.
     *
     * @param checkbox the checkbox
     */
    public void setCheckbox(final Boolean checkbox) {
        this.checkbox = checkbox;
    }

    /**
     * Gets the trading partner contract no.
     *
     * @return the trading partner contract no
     */
    public String getTradingPartnerContractNo() {
        return tradingPartnerContractNo;
    }

    /**
     * Sets the trading partner contract no.
     *
     * @param tradingPartnerContractNo the trading partner contract no
     */
    public void setTradingPartnerContractNo(final String tradingPartnerContractNo) {
        this.tradingPartnerContractNo = tradingPartnerContractNo;
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
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

    public String getCompanyFamilyPlanStatusValue() {
        return companyFamilyPlanStatusValue;
    }

    public void setCompanyFamilyPlanStatusValue(String companyFamilyPlanStatusValue) {
        this.companyFamilyPlanStatusValue = companyFamilyPlanStatusValue;
    }

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getMassCheck() {
        return massCheck;
    }

    public void setMassCheck(String massCheck) {
        this.massCheck = massCheck;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public Integer getParentFlagSID() {
        return parentFlagSID;
    }

    public void setParentFlagSID(Integer parentFlagSID) {
        this.parentFlagSID = parentFlagSID;
    }

    public Date getCfpCompanyStartDate() {       
        return cfpCompanyStartDate;
    }

    public void setCfpCompanyStartDate(Date cfpCompanyStartDate) {
        this.cfpCompanyStartDate = cfpCompanyStartDate;
    }

    public Date getCfpCompanyEndDate() {
        return cfpCompanyEndDate;
    }

    public void setCfpCompanyEndDate(Date cfpCompanyEndDate) {
        this.cfpCompanyEndDate = cfpCompanyEndDate;
    }

    public Date getCfpAttachedDate() {
        return cfpAttachedDate;
    }

    public void setCfpAttachedDate(Date cfpAttachedDate) {
        this.cfpAttachedDate = cfpAttachedDate;
    }
    private HelperDTO salesInclusion =  new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    public HelperDTO getSalesInclusion() {
        return salesInclusion;
    }

    public void setSalesInclusion(HelperDTO salesInclusion) {
        this.salesInclusion = salesInclusion;
    }
    
    
}