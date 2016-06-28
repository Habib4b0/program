/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * The Class CFPDTO.
 *
 * @author suthan
 */
public class CFPDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -5242969461368056961L;
    /**
     * The parent company family plan name.
     */
    public String parentCompanyFamilyPlanName = StringUtils.EMPTY;
    /**
     * The internal notes.
     */
    public String internalNotes = StringUtils.EMPTY;
    /**
     * The record lock status.
     */
    public boolean recordLockStatus;
    /**
     * The company family plan trade class.
     */
    public int companyFamilyPlanTradeClass;
    /**
     * The created by.
     */
    public int createdBy;
    
        
    public String systemId =  StringUtils.EMPTY;
    /**
     * The company family plan id.
     */
    public String companyFamilyPlanId = StringUtils.EMPTY;
    /**
     * The company family plan name.
     */
    public String companyFamilyPlanName = StringUtils.EMPTY;
    /**
     * The company family plan category.
     */
    public int companyFamilyPlanCategory;
    /**
     * The batch id.
     */
    public String batchId = StringUtils.EMPTY;
    
    private String companyFamilyPlanStatusValue = StringUtils.EMPTY;
    /**
     * The company family plan type.
     */
    public HelperDTO companyFamilyPlanType  = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The inbound status.
     */
    public String inboundStatus = StringUtils.EMPTY;
    /**
     * The modified by.
     */
    public int modifiedBy;
    /**
     * The company family plan designation.
     */
    public String companyFamilyPlanDesignation = StringUtils.EMPTY;
    /**
     * The company family plan no.
     */
    public String companyFamilyPlanNo = StringUtils.EMPTY;
    /**
     * The company family plan status.
     */
    public HelperDTO companyFamilyPlanStatus  = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The company family plan start date.
     */
    public Date companyFamilyPlanStartDate;
    /**
     * The company family plan end date.
     */
    public Date companyFamilyPlanEndDate;
    /**
     * The modified date.
     */
    public Date modifiedDate;
    /**
     * The created date.
     */
    public Date createdDate;
    /**
     * The parent company family plan id.
     */
    public String parentCompanyFamilyPlanId = StringUtils.EMPTY;
    /**
     * The company family plan system id.
     */
    public int companyFamilyPlanSystemId;
    /**
     * The company family plan id.
     */
    public String cfpId = StringUtils.EMPTY;
    /**
     * The company family plan name.
     */
    public String cfpNo = StringUtils.EMPTY;
    /**
     * The parent company family plan name.
     */
    public String cfpName = StringUtils.EMPTY;
    /**
     * The parent company family plan type.
     */
    public String cfpType = StringUtils.EMPTY;

    /**
     * The parent company family plan type.
     */
    public int parentCFPId ;
    
    public String companyId = StringUtils.EMPTY;
    
    public String companyNo = StringUtils.EMPTY;
    
    public String companyName = StringUtils.EMPTY;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
    
    
    /**
     * Gets the parent company family plan name.
     *
     * @return the parentCompanyFamilyPlanName
     */
    public String getParentCompanyFamilyPlanName() {
        return parentCompanyFamilyPlanName;
    }

    /**
     * Sets the parent company family plan name.
     *
     * @param parentCompanyFamilyPlanName the parentCompanyFamilyPlanName to set
     */
    public void setParentCompanyFamilyPlanName(final String parentCompanyFamilyPlanName) {
        this.parentCompanyFamilyPlanName = parentCompanyFamilyPlanName;
    }

    /**
     * Gets the internal notes.
     *
     * @return the internalNotes
     */
    public String getInternalNotes() {
        return internalNotes;
    }

    /**
     * Sets the internal notes.
     *
     * @param internalNotes the internalNotes to set
     */
    public void setInternalNotes(final String internalNotes) {
        this.internalNotes = internalNotes;
    }

    /**
     * Gets the record lock status.
     *
     * @return the recordLockStatus
     */
    public boolean getRecordLockStatus() {
        return recordLockStatus;
    }

    /**
     * Sets the record lock status.
     *
     * @param recordLockStatus the recordLockStatus to set
     */
    public void setRecordLockStatus(final boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    /**
     * Gets the company family plan trade class.
     *
     * @return the companyFamilyPlanTradeClass
     */
    public int getCompanyFamilyPlanTradeClass() {
        return companyFamilyPlanTradeClass;
    }

    /**
     * Sets the company family plan trade class.
     *
     * @param companyFamilyPlanTradeClass the companyFamilyPlanTradeClass to set
     */
    public void setCompanyFamilyPlanTradeClass(final int companyFamilyPlanTradeClass) {
        this.companyFamilyPlanTradeClass = companyFamilyPlanTradeClass;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(final int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the created by.
     *
     * @return the createdBy
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * Gets the company family plan id.
     *
     * @return the companyFamilyPlanId
     */
    public String getCompanyFamilyPlanId() {
        return companyFamilyPlanId;
    }

    /**
     * Sets the company family plan id.
     *
     * @param companyFamilyPlanId the companyFamilyPlanId to set
     */
    public void setCompanyFamilyPlanId(final String companyFamilyPlanId) {
        this.companyFamilyPlanId = companyFamilyPlanId;
    }

    /**
     * Gets the company family plan name.
     *
     * @return the companyFamilyPlanName
     */
    public String getCompanyFamilyPlanName() {
        return companyFamilyPlanName;
    }

    /**
     * Sets the company family plan name.
     *
     * @param companyFamilyPlanName the companyFamilyPlanName to set
     */
    public void setCompanyFamilyPlanName(final String companyFamilyPlanName) {
        this.companyFamilyPlanName = companyFamilyPlanName;
    }

    /**
     * Gets the company family plan category.
     *
     * @return the companyFamilyPlanCategory
     */
    public int getCompanyFamilyPlanCategory() {
        return companyFamilyPlanCategory;
    }

    /**
     * Sets the company family plan category.
     *
     * @param companyFamilyPlanCategory the companyFamilyPlanCategory to set
     */
    public void setCompanyFamilyPlanCategory(final int companyFamilyPlanCategory) {
        this.companyFamilyPlanCategory = companyFamilyPlanCategory;
    }

    /**
     * Gets the batch id.
     *
     * @return the batchId
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * Sets the batch id.
     *
     * @param batchId the batchId to set
     */
    public void setBatchId(final String batchId) {
        this.batchId = batchId;
    }

    /**
     * Gets the company family plan type.
     *
     * @return the companyFamilyPlanType
     */
    public HelperDTO getCompanyFamilyPlanType() {
        return companyFamilyPlanType;
    }

    /**
     * Sets the company family plan type.
     *
     * @param companyFamilyPlanType the companyFamilyPlanType to set
     */
    public void setCompanyFamilyPlanType(final HelperDTO companyFamilyPlanType) {
        this.companyFamilyPlanType = companyFamilyPlanType;
    }

    /**
     * Gets the inbound status.
     *
     * @return the inboundStatus
     */
    public String getInboundStatus() {
        return inboundStatus;
    }

    /**
     * Sets the inbound status.
     *
     * @param inboundStatus the inboundStatus to set
     */
    public void setInboundStatus(final String inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    /**
     * Gets the modified by.
     *
     * @return the modifiedBy
     */
    public int getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(final int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets the company family plan designation.
     *
     * @return the companyFamilyPlanDesignation
     */
    public String getCompanyFamilyPlanDesignation() {
        return companyFamilyPlanDesignation;
    }

    /**
     * Sets the company family plan designation.
     *
     * @param companyFamilyPlanDesignation the companyFamilyPlanDesignation to
     * set
     */
    public void setCompanyFamilyPlanDesignation(final String companyFamilyPlanDesignation) {
        this.companyFamilyPlanDesignation = companyFamilyPlanDesignation;
    }

    /**
     * Gets the company family plan no.
     *
     * @return the companyFamilyPlanNo
     */
    public String getCompanyFamilyPlanNo() {
        return companyFamilyPlanNo;
    }

    /**
     * Sets the company family plan no.
     *
     * @param companyFamilyPlanNo the companyFamilyPlanNo to set
     */
    public void setCompanyFamilyPlanNo(final String companyFamilyPlanNo) {
        this.companyFamilyPlanNo = companyFamilyPlanNo;
    }

    /**
     * Gets the company family plan status.
     *
     * @return the companyFamilyPlanStatus
     */
    public HelperDTO getCompanyFamilyPlanStatus() {
        return companyFamilyPlanStatus;
    }

    /**
     * Sets the company family plan status.
     *
     * @param companyFamilyPlanStatus the companyFamilyPlanStatus to set
     */
    public void setCompanyFamilyPlanStatus(final HelperDTO companyFamilyPlanStatus) {
        this.companyFamilyPlanStatus = companyFamilyPlanStatus;
    }

    /**
     * Gets the company family plan start date.
     *
     * @return the companyFamilyPlanStartDate
     */
    public Date getCompanyFamilyPlanStartDate() {
        return companyFamilyPlanStartDate;
    }

    /**
     * Sets the company family plan start date.
     *
     * @param companyFamilyPlanStartDate the companyFamilyPlanStartDate to set
     */
    public void setCompanyFamilyPlanStartDate(final Date companyFamilyPlanStartDate) {
        this.companyFamilyPlanStartDate = companyFamilyPlanStartDate;
    }

    /**
     * Gets the company family plan end date.
     *
     * @return the companyFamilyPlanEndDate
     */
    public Date getCompanyFamilyPlanEndDate() {
        return companyFamilyPlanEndDate;
    }

    /**
     * Sets the company family plan end date.
     *
     * @param companyFamilyPlanEndDate the companyFamilyPlanEndDate to set
     */
    public void setCompanyFamilyPlanEndDate(final Date companyFamilyPlanEndDate) {
        this.companyFamilyPlanEndDate = companyFamilyPlanEndDate;
    }

    /**
     * Gets the modified date.
     *
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the created date.
     *
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the parent company family plan id.
     *
     * @return the parentCompanyFamilyPlanId
     */
    public String getParentCompanyFamilyPlanId() {
        return parentCompanyFamilyPlanId;
    }

    /**
     * Sets the parent company family plan id.
     *
     * @param parentCompanyFamilyPlanId the parentCompanyFamilyPlanId to set
     */
    public void setParentCompanyFamilyPlanId(final String parentCompanyFamilyPlanId) {
        this.parentCompanyFamilyPlanId = parentCompanyFamilyPlanId;
    }

    /**
     * Gets the company family plan system id.
     *
     * @return the companyFamilyPlanSystemId
     */
    public int getCompanyFamilyPlanSystemId() {
        return companyFamilyPlanSystemId;
    }

    /**
     * Sets the company family plan system id.
     *
     * @param companyFamilyPlanSystemId the companyFamilyPlanSystemId to set
     */
    public void setCompanyFamilyPlanSystemId(final int companyFamilyPlanSystemId) {
        this.companyFamilyPlanSystemId = companyFamilyPlanSystemId;
    }

    public String getCompanyFamilyPlanStatusValue() {
        return companyFamilyPlanStatusValue;
    }

    public void setCompanyFamilyPlanStatusValue(String companyFamilyPlanStatusValue) {
        this.companyFamilyPlanStatusValue = companyFamilyPlanStatusValue;
    }

    public int getParentCFPId() {
        return parentCFPId;
    }

    public void setParentCFPId(int parentCFPId) {
        this.parentCFPId = parentCFPId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
    
    
    
    }
