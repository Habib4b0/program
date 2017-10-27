package com.stpl.app.contract.global.dto;

import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.ifs.util.HelperUtils;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;

public class CFPCompanyDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -2675538584167565502L;
    /**
     * The company system id.
     */
    private String companySystemId = HelperUtils.EMPTY;
    /**
     * The company id.
     */
    private String companyId = HelperUtils.EMPTY;
    /**
     * The company no.
     */
    private String companyNo = HelperUtils.EMPTY;
    /**
     * The company name.
     */
    private String companyName = HelperUtils.EMPTY;
    /**
     * The company start date.
     */
    private Date companyStartDate;
    /**
     * The company end date.
     */
    private Date companyEndDate;
    /**
     * The company family plan system id.
     */
    private String companyFamilyPlanSystemId = HelperUtils.EMPTY;
    /**
     * The company family plan id.
     */
    private String companyFamilyPlanId = HelperUtils.EMPTY;
    /**
     * The company family plan no.
     */
    private String companyFamilyPlanNo = HelperUtils.EMPTY;
    /**
     * The company family plan name.
     */
    private String companyFamilyPlanName = HelperUtils.EMPTY;
    /**
     * The company status.
     */
    private String companyStatus = HelperUtils.EMPTY;
    /**
     * The company family plan start date.
     */
    private Date companyFamilyPlanStartDate;
    /**
     * The company family plan end date.
     */
    private Date companyFamilyPlanEndDate;

    /**
     * The cfp details system id.
     */
    private String cfpDetailsSystemId = HelperUtils.EMPTY;
    /**
     * The checkbox.
     */
    private Boolean checkbox;
    /**
     * The company family plan status.
     */
    private String companyFamilyPlanStatus = HelperUtils.EMPTY;
    /**
     * The trade class.
     */
    private String tradeClass = HelperUtils.EMPTY;
    /**
     * The trade class start date.
     */
    private String tradeClassStartDate = HelperUtils.EMPTY;
    /**
     * The trade class end date.
     */
    private String tradeClassEndDate = HelperUtils.EMPTY;
    /**
     * The attached date.
     */
    private Date attachedDate;
    /**
     * The company type.
     */
    private String companyType = HelperUtils.EMPTY;
    /**
     * The cfpcompany list.
     */
    private List<CFPCompanyDTO> cfpcompanyList = new ArrayList<>();
    /**
     * The company list.
     */
    private List<CompanyMasterDTO> companyList = new ArrayList<>();
    /**
     * The trading partner contract no.
     */
    private String tradingPartnerContractNo = HelperUtils.EMPTY;
    /**
     * The created date.
     */
    private String createdDate = StringUtils.EMPTY;
    

    private String tempCFPSystemID;
    private String companyCategory;
    private String cfpDetailsTradeClass;
    private String modifiedDate = StringUtils.EMPTY;
    private String cfpDetailsModifiedBy;
    private String createdBy = StringUtils.EMPTY;
    private HelperDTO cfpStatus = new HelperDTO(0, Constants.SELECT_ONE);
    private HelperDTO cfptype = new HelperDTO(0, Constants.SELECT_ONE);
    private String modifiedBy = StringUtils.EMPTY;
    private HelperDTO cfpCategory = new HelperDTO(0, Constants.SELECT_ONE);
    private HelperDTO cfptrade = new HelperDTO(0, Constants.SELECT_ONE);
    private HelperDTO cfpDesignation = new HelperDTO(0, Constants.SELECT_ONE);
    private String parentCfp = StringUtils.EMPTY;
    private String parentCfpName = StringUtils.EMPTY;
    private HelperDTO salesInclusion = new HelperDTO(0, Constants.SELECT_ONE);
     private String companyGroup;
     
     private String recordType;
     private String parentFlagSID = StringUtils.EMPTY;

    public String getParentFlagSID() {
        return parentFlagSID;
    }

    public void setParentFlagSID(String parentFlagSID) {
        this.parentFlagSID = parentFlagSID;
    }

    public String getTempCFPSystemID() {
        return tempCFPSystemID;
    }

    public void setTempCFPSystemID(String tempCFPSystemID) {
        this.tempCFPSystemID = tempCFPSystemID;
    }    

    /**
     * The Constructor.
     */
    public CFPCompanyDTO() {
        // default constructor
    }

    /**
     * The Constructor.
     *
     * @param companySystemId the company system id
     * @param companyFamilyPlanId the company family plan id
     * @param companyFamilyPlanSystemId the company family plan system id
     * @param companyFamilyPlanName the company family plan name
     * @param companyFamilyPlanNo the company family plan no,
     * @param companyType the company type
     * @param companyId the company id,
     * @param companyNo the company no
     * @param companyName the company name,
     * @param companyStatus the company status
     * @param tradeClass the trade class,
     * @param companyStartDate the company start date
     * @param companyEndDate the company end date,
     * @param tradeClassStartDate the trade class start date
     * @param tradeClassEndDate the trade class end date,
     * @param attachedDate the attached date
     * @param createdDate the created date
     */
    public CFPCompanyDTO(final String companySystemId, final String companyFamilyPlanId, final String companyFamilyPlanSystemId, final String companyFamilyPlanName, final String companyFamilyPlanNo,
            final String companyType, final String companyId, final String companyNo, final String companyName, final String companyStatus, final String tradeClass, final Date companyStartDate,
            final Date companyEndDate, final String tradeClassStartDate, final String tradeClassEndDate, final Date attachedDate, final String createdDate) {

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
        this.companyType = companyType;
        this.companyStatus = companyStatus;
        this.tradeClass = tradeClass;
        this.tradeClassStartDate = tradeClassStartDate;
        this.tradeClassEndDate = tradeClassEndDate;
        this.attachedDate = attachedDate;
        this.companyFamilyPlanStartDate = companyFamilyPlanStartDate;
        this.companyFamilyPlanEndDate = companyFamilyPlanEndDate;
        this.createdDate = createdDate;

    }

    /**
     * The Constructor.
     *
     * @param companySystemId the company system id,
     * @param companyFamilyPlanId the company family plan id
     * @param companyFamilyPlanSystemId the company family plan system id
     * @param companyFamilyPlanName the company family plan name
     * @param companyFamilyPlanNo the company family plan no
     * @param companyNo the company no
     * @param companyName the company name
     * @param companyStartDate the company start date,
     * @param companyEndDate the company end date
     * @param status the status
     * @param companyFamilyPlanStartDate the company family plan start date
     * @param companyFamilyPlanEndDate the company family plan end date
     * @param checkBox the check box,
     * @param createdDate the created date
     */
    public CFPCompanyDTO(final String companySystemId, final String companyFamilyPlanId, final String companyFamilyPlanSystemId, final String companyFamilyPlanName, final String companyFamilyPlanNo,
            final String companyNo, final String companyName, final Date companyStartDate, final Date companyEndDate, final String status, final Date companyFamilyPlanStartDate,
            final Date companyFamilyPlanEndDate, final boolean checkBox, final String createdDate) {

        this.companySystemId = companySystemId;

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
        this.createdDate = createdDate;

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
    public String getCompanyFamilyPlanStatus() {
        return companyFamilyPlanStatus;
    }

    /**
     * Sets the company family plan status.
     *
     * @param companyFamilyPlanStatus the company family plan status
     */
    public void setCompanyFamilyPlanStatus(final String companyFamilyPlanStatus) {
        this.companyFamilyPlanStatus = companyFamilyPlanStatus;
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
    public Date getAttachedDate() {
        return attachedDate;
    }

    /**
     * Sets the attached date.
     *
     * @param attachedDate the attached date
     */
    public void setAttachedDate(final Date attachedDate) {
        this.attachedDate = attachedDate;
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
     * Gets the company family plan system id.
     *
     * @return the company family plan system id
     */
    public String getCompanyFamilyPlanSystemId() {
        return companyFamilyPlanSystemId;
    }

    /**
     * Sets the company family plan system id.
     *
     * @param companyFamilyPlanSystemId the company family plan system id
     */
    public void setCompanyFamilyPlanSystemId(final String companyFamilyPlanSystemId) {
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
     * Gets the company family plan end date.
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
     * Gets the cfpcompany list.
     *
     * @return the cfpcompany list
     */
    public List<CFPCompanyDTO> getCfpcompanyList() {
        return cfpcompanyList;
    }

    /**
     * Sets the cfpcompany list.
     *
     * @param cfpcompanyList the cfpcompany list
     */
    public void setCfpcompanyList(final List<CFPCompanyDTO> cfpcompanyList) {
        this.cfpcompanyList = cfpcompanyList;
    }

    /**
     * Gets the company list.
     *
     * @return the company list
     */
    public List<CompanyMasterDTO> getCompanyList() {
        return companyList;
    }

    /**
     * Sets the company list.
     *
     * @param companyList the company list
     */
    public void setCompanyList(final List<CompanyMasterDTO> companyList) {
        this.companyList = companyList;
    }

    /**
     * Gets the cfp details system id.
     *
     * @return the cfp details system id
     */
    public String getCfpDetailsSystemId() {
        return cfpDetailsSystemId;
    }

    /**
     * Sets the cfp details system id.
     *
     * @param cfpDetailsSystemId the cfp details system id
     */
    public void setCfpDetailsSystemId(final String cfpDetailsSystemId) {
        this.cfpDetailsSystemId = cfpDetailsSystemId;
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

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }

    public String getCfpDetailsTradeClass() {
        return cfpDetailsTradeClass;
    }

    public void setCfpDetailsTradeClass(String cfpDetailsTradeClass) {
        this.cfpDetailsTradeClass = cfpDetailsTradeClass;
    }

    public String getCfpDetailsModifiedBy() {
        return cfpDetailsModifiedBy;
    }

    public void setCfpDetailsModifiedBy(String cfpDetailsModifiedBy) {
        this.cfpDetailsModifiedBy = cfpDetailsModifiedBy;
    }

    public HelperDTO getCfpStatus() {
        return cfpStatus;
    }

    public void setCfpStatus(HelperDTO cfpStatus) {
        this.cfpStatus = cfpStatus;
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

    public HelperDTO getCfptype() {
        return cfptype;
    }

    public void setCfptype(HelperDTO cfptype) {
        this.cfptype = cfptype;
    }

    public HelperDTO getCfpCategory() {
        return cfpCategory;
    }

    public void setCfpCategory(HelperDTO cfpCategory) {
        this.cfpCategory = cfpCategory;
    }

    public HelperDTO getCfptrade() {
        return cfptrade;
    }

    public void setCfptrade(HelperDTO cfptrade) {
        this.cfptrade = cfptrade;
    }

    public HelperDTO getCfpDesignation() {
        return cfpDesignation;
    }

    public void setCfpDesignation(HelperDTO cfpDesignation) {
        this.cfpDesignation = cfpDesignation;
    }

    public String getParentCfp() {
        return parentCfp;
    }

    public void setParentCfp(String parentCfp) {
        this.parentCfp = parentCfp;
    }

    public String getParentCfpName() {
        return parentCfpName;
    }

    public void setParentCfpName(String parentCfpName) {
        this.parentCfpName = parentCfpName;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public HelperDTO getSalesInclusion() {
        return salesInclusion;
    }

    public void setSalesInclusion(HelperDTO salesInclusion) {
        this.salesInclusion = salesInclusion;
    }

    public String getCompanyGroup() {
        return companyGroup;
    }

    public void setCompanyGroup(String companyGroup) {
        this.companyGroup = companyGroup;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    @Override
    public int hashCode() {
        int hash = NumericConstants.SEVEN;
        hash = NumericConstants.THIRTY_SEVEN * hash + Objects.hashCode(this.tempCFPSystemID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CFPCompanyDTO other = (CFPCompanyDTO) obj;
        if (!Objects.equals(this.tempCFPSystemID, other.tempCFPSystemID)) {
            return false;
        }
        return true;
    }

   
    
    

}
