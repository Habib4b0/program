package com.stpl.app.global.company.dto;

import com.stpl.app.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;

import com.stpl.app.util.HelperUtils;
import org.apache.commons.lang.StringUtils;

/**
 * A DTO object which is used as a form object in create user and edit user
 * forms.
 *
 * @author arulmurugan
 */
public class CompanyCrtIdentifierDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -2469701546676000621L;
    /**
     * The crt identifier system id.
     */
    private int crtIdentifierSystemId;
    /**
     * The company system id.
     */
    private int companySystemId;
    /**
     * The company identifier.
     */
    private String companyIdentifier = HelperUtils.EMPTY;
    /**
     * The company qualifier.
     */
    private String companyQualifier = HelperUtils.EMPTY;
    /**
     * The company crt qualifier id.
     */
    private int companyCrtQualifierId;
    /**
     * The company crt qualifier name.
     */
    private String companyCrtQualifierName = HelperUtils.EMPTY;
    private int companyCrtQualifierSid;
    /**
     * The identifier status.
     */
    private com.stpl.ifs.util.HelperDTO identifierStatus;
     /**
     * The identifier status.
     */
    private String identifierStatusValue;
    /**
     * The start date.
     */
    private Date startDate;
    /**
     * The end date.
     */
    private Date endDate;
    /**
     * The entity code.
     */
    private String entityCode = HelperUtils.EMPTY;
    /**
     * The created by.
     */
    private String createdBy = HelperUtils.EMPTY;
    /**
     * The created date.
     */
    private Date createdDate = null;
    /**cre
     * The modified by.
     */
    private String modifiedBy = HelperUtils.EMPTY;
    /**
     * The modified date.
     */
    private Date modifiedDate;
    /**
     * The batch id.
     */
    private String batchId = HelperUtils.EMPTY;
    /**
     * The inbound status.
     */
    private Character inboundStatus;
    /**
     * The company crt identifier indicator.
     */
    private boolean companyCrtIdentifierIndicator;
    /**
     * The checkbox.
     */
    private Boolean checkbox = false;
    /**
     * The view start date.
     */
    private String viewStartDate;
    /**
     * The view end date.
     */
    private String viewEndDate;
    
    private String effectiveDates;
    
    private String notes = StringUtils.EMPTY;

    public String getEffectiveDates() {
        return effectiveDates;
    }

    public void setEffectiveDates(String effectiveDates) {
        this.effectiveDates = effectiveDates;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
   
    /**
     * The company crt qualifier name.
     */
    private HelperDTO companyCrtQualifierNameDDLB;
    
    private boolean recordLockStatus;
    
    private String viewCreatedDate;

    public String getViewCreatedDate() {
        return viewCreatedDate;
    }

    public void setViewCreatedDate(String viewCreatedDate) {
        this.viewCreatedDate = viewCreatedDate;
    }

    public String getViewModifiedDate() {
        return viewModifiedDate;
    }

    public void setViewModifiedDate(String viewModifiedDate) {
        this.viewModifiedDate = viewModifiedDate;
    }
    
    private String viewModifiedDate;
    
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
     * Gets the crt identifier system id.
     *
     * @return the crt identifier system id
     */
    public int getCrtIdentifierSystemId() {
        return crtIdentifierSystemId;
    }

    /**
     * Sets the crt identifier system id.
     *
     * @param crtIdentifierSystemId the crt identifier system id
     */
    public void setCrtIdentifierSystemId(final int crtIdentifierSystemId) {
        this.crtIdentifierSystemId = crtIdentifierSystemId;
    }

    /**
     * Gets the identifier status.
     *
     * @return the identifier status
     */
    public com.stpl.ifs.util.HelperDTO getIdentifierStatus() {
        return identifierStatus;
    }

    /**
     * Sets the identifier status.
     *
     * @param identifierStatus the identifier status
     */
    public void setIdentifierStatus(final com.stpl.ifs.util.HelperDTO identifierStatus) {
        this.identifierStatus = identifierStatus;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the entity code.
     *
     * @return the entity code
     */
    public String getEntityCode() {
        return entityCode;
    }

    /**
     * Sets the entity code.
     *
     * @param entityCode the entity code
     */
    public void setEntityCode(final String entityCode) {
        this.entityCode = entityCode;
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
     * Gets the inbound status.
     *
     * @return the inbound status
     */
    public Character getInboundStatus() {
        return inboundStatus;
    }

    /**
     * Sets the inbound status.
     *
     * @param inboundStatus the inbound status
     */
    public void setInboundStatus(final Character inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    /**
     * Gets the company system id.
     *
     * @return the company system id
     */
    public int getCompanySystemId() {
        return companySystemId;
    }

    /**
     * Sets the company system id.
     *
     * @param companySystemId the company system id
     */
    public void setCompanySystemId(final int companySystemId) {
        this.companySystemId = companySystemId;
    }

    /**
     * Gets the company identifier.
     *
     * @return the company identifier
     */
    public String getCompanyIdentifier() {
        return companyIdentifier;
    }

    /**
     * Sets the company identifier.
     *
     * @param companyIdentifier the company identifier
     */
    public void setCompanyIdentifier(final String companyIdentifier) {
        this.companyIdentifier = companyIdentifier;
    }

    /**
     * Gets the company crt qualifier id.
     *
     * @return the company crt qualifier id
     */
    public int getCompanyCrtQualifierId() {
        return companyCrtQualifierId;
    }

    /**
     * Sets the company crt qualifier id.
     *
     * @param companyCrtQualifierId the company crt qualifier id
     */
    public void setCompanyCrtQualifierId(final int companyCrtQualifierId) {
        this.companyCrtQualifierId = companyCrtQualifierId;
    }

    /**
     * Gets the company crt qualifier name.
     *
     * @return the company crt qualifier name
     */
    public String getCompanyCrtQualifierName() {
        return companyCrtQualifierName;
    }

    /**
     * Sets the company crt qualifier name.
     *
     * @param companyCrtQualifierName the company crt qualifier name
     */
    public void setCompanyCrtQualifierName(final String companyCrtQualifierName) {
        this.companyCrtQualifierName = companyCrtQualifierName;
    }

    /**
     * Checks if is company crt identifier indicator.
     *
     * @return true, if checks if is company crt identifier indicator
     */
    public boolean isCompanyCrtIdentifierIndicator() {
        return companyCrtIdentifierIndicator;
    }

    /**
     * Sets the company crt identifier indicator.
     *
     * @param companyCrtIdentifierIndicator the company crt identifier indicator
     */
    public void setCompanyCrtIdentifierIndicator(
            final boolean companyCrtIdentifierIndicator) {
        this.companyCrtIdentifierIndicator = companyCrtIdentifierIndicator;
    }

    /**
     * Gets the company qualifier.
     *
     * @return the company qualifier
     */
    public String getCompanyQualifier() {
        return companyQualifier;
    }

    /**
     * Sets the company qualifier.
     *
     * @param companyQualifier the company qualifier
     */
    public void setCompanyQualifier(final String companyQualifier) {
        this.companyQualifier = companyQualifier;
    }

    /**
     * Gets the view start date.
     *
     * @return the view start date
     */
    public String getViewStartDate() {
        return viewStartDate;
    }

    /**
     * Sets the view start date.
     *
     * @param viewStartDate the view start date
     */
    public void setViewStartDate(final String viewStartDate) {
        this.viewStartDate = viewStartDate;
    }

    /**
     * Gets the view end date.
     *
     * @return the view end date
     */
    public String getViewEndDate() {
        return viewEndDate;
    }

    /**
     * Sets the view end date.
     *
     * @param viewEndDate the view end date
     */
    public void setViewEndDate(final String viewEndDate) {
        this.viewEndDate = viewEndDate;
    }

    /**
     * Getting companyCrtQualifierNameDDLB
     *
     * @return
     */
    public HelperDTO getCompanyCrtQualifierNameDDLB() {
        return companyCrtQualifierNameDDLB;
    }

    /**
     * Setting companyCrtQualifierNameDDLB
     *
     * @param companyCrtQualifierNameDDLB
     */
    public void setCompanyCrtQualifierNameDDLB(final HelperDTO companyCrtQualifierNameDDLB) {
        this.companyCrtQualifierNameDDLB = companyCrtQualifierNameDDLB;
    }        

    public int getCompanyCrtQualifierSid() {
        return companyCrtQualifierSid;
    }

    public void setCompanyCrtQualifierSid(int companyCrtQualifierSid) {
        this.companyCrtQualifierSid = companyCrtQualifierSid;
    }

    public boolean isRecordLockStatus() {
        return recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    public String getIdentifierStatusValue() {
        return identifierStatusValue;
    }

    public void setIdentifierStatusValue(String identifierStatusValue) {
        this.identifierStatusValue = identifierStatusValue;
    }
}
