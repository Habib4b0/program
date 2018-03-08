/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dto;

import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author kasiammal.m
 */
public class ItemIrtIdentifierDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -849365522985110991L;

    /**
     * The item system id.
     */
    private int itemSystemId;

    /**
     * The item irt qualifier name.
     */
    private HelperDTO itemIrtQualifierNameDDLB;

    /**
     * The item identifier.
     */
    private String itemIdentifier = StringUtils.EMPTY;

    /**
     * The item irt qualifier id.
     */
    private int itemIrtQualifierId;

    /**
     * The item irt qualifier name.
     */
    private String itemIrtQualifierName = StringUtils.EMPTY;

    /**
     * The identifier status.
     */
    private int identifierStatus;

    /**
     * The identifier status.
     */
    private String identifierStatusView;

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
    private String entityCode = StringUtils.EMPTY;

    /**
     * The entity code.
     */
    private String entityCodeName = StringUtils.EMPTY;

    private String entityCodeSid = StringUtils.EMPTY;

    /**
     * The created by.
     */
    private String createdBy;

    /**
     * The created date.
     */
    private Date createdDate;

    /**
     * The modified by.
     */
    private String modifiedBy = StringUtils.EMPTY;

    /**
     * The modified date.
     */
    private Date modifiedDate;

    /**
     * The batch id.
     */
    private String batchId = StringUtils.EMPTY;

    /**
     * The inbound status.
     */
    private String inboundStatus;

    /**
     * The item irt identifier indicator.
     */
    private boolean itemIrtIdentifierIndicator;

    /**
     * The item id.
     */
    private String itemId = StringUtils.EMPTY;

    /**
     * The view start date.
     */
    private String viewStartDate = StringUtils.EMPTY;

    /**
     * The view end date.
     */
    private String viewEndDate = StringUtils.EMPTY;

    /**
     * The irt identifier system id.
     */
    private int irtIdentifierSystemId;

    /**
     * The checkbox.
     */
    private boolean checkbox = false;

    private boolean recordLockStatus;

    private String identifierStatusDdlb;

    private int createdByValue;

    public int getCreatedByValue() {
        return createdByValue;
    }

    public void setCreatedByValue(int createdByValue) {
        this.createdByValue = createdByValue;
    }

    public int getModifiedByValue() {
        return modifiedByValue;
    }

    public void setModifiedByValue(int modifiedByValue) {
        this.modifiedByValue = modifiedByValue;
    }
    private int modifiedByValue;

    /**
     *
     * @return
     */
    public HelperDTO getItemIrtQualifierNameDDLB() {
        return itemIrtQualifierNameDDLB;
    }

    /**
     *
     * @param itemIrtQualifierNameDDLB
     */
    public void setItemIrtQualifierNameDDLB(final HelperDTO itemIrtQualifierNameDDLB) {
        this.itemIrtQualifierNameDDLB = itemIrtQualifierNameDDLB;
    }

    /**
     * Gets the item id.
     *
     * @return the item id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Sets the item id.
     *
     * @param itemId the item id
     */
    public void setItemId(final String itemId) {
        this.itemId = itemId;
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
     * Gets the item system id.
     *
     * @return the item system id
     */
    public int getItemSystemId() {
        return itemSystemId;
    }

    /**
     * Sets the item system id.
     *
     * @param itemSystemId the item system id
     */
    public void setItemSystemId(final int itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    /**
     * Gets the item identifier.
     *
     * @return the item identifier
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Sets the item identifier.
     *
     * @param itemIdentifier the item identifier
     */
    public void setItemIdentifier(final String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    /**
     * Gets the item irt qualifier id.
     *
     * @return the item irt qualifier id
     */
    public int getItemIrtQualifierId() {
        return itemIrtQualifierId;
    }

    /**
     * Sets the item irt qualifier id.
     *
     * @param itemIrtQualifierId the item irt qualifier id
     */
    public void setItemIrtQualifierId(final int itemIrtQualifierId) {
        this.itemIrtQualifierId = itemIrtQualifierId;
    }

    /**
     * Gets the item irt qualifier name.
     *
     * @return the item irt qualifier name
     */
    public String getItemIrtQualifierName() {
        return itemIrtQualifierName;
    }

    /**
     * Sets the item irt qualifier name.
     *
     * @param itemIrtQualifierName the item irt qualifier name
     */
    public void setItemIrtQualifierName(final String itemIrtQualifierName) {
        this.itemIrtQualifierName = itemIrtQualifierName;
    }

    /**
     * Gets the identifier status.
     *
     * @return the identifier status
     */
    public int getIdentifierStatus() {
        return identifierStatus;
    }

    /**
     * Sets the identifier status.
     *
     * @param identifierStatus the identifier status
     */
    public void setIdentifierStatus(final int identifierStatus) {
        this.identifierStatus = identifierStatus;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    /**
     * Sets the start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate == null ? null : (Date) endDate.clone();
    }

    /**
     * Sets the end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate == null ? null : (Date) endDate.clone();
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
     * Checks if is item irt identifier indicator.
     *
     * @return true, if checks if is item irt identifier indicator
     */
    public boolean isItemIrtIdentifierIndicator() {
        return itemIrtIdentifierIndicator;
    }

    /**
     * Sets the item irt identifier indicator.
     *
     * @param itemIrtIdentifierIndicator the item irt identifier indicator
     */
    public void setItemIrtIdentifierIndicator(final boolean itemIrtIdentifierIndicator) {
        this.itemIrtIdentifierIndicator = itemIrtIdentifierIndicator;
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
     * Gets the irt identifier system id.
     *
     * @return the irt identifier system id
     */
    public int getIrtIdentifierSystemId() {
        return irtIdentifierSystemId;
    }

    /**
     * Sets the irt identifier system id.
     *
     * @param irtIdentifierSystemId the irt identifier system id
     */
    public void setIrtIdentifierSystemId(final int irtIdentifierSystemId) {
        this.irtIdentifierSystemId = irtIdentifierSystemId;
    }

    public String getIdentifierStatusView() {
        return identifierStatusView;
    }

    public void setIdentifierStatusView(String identifierStatusView) {
        this.identifierStatusView = identifierStatusView;
    }

    public boolean isRecordLockStatus() {
        return recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    public String getEntityCodeSid() {
        return entityCodeSid;
    }

    public void setEntityCodeSid(String entityCodeSid) {
        this.entityCodeSid = entityCodeSid;
    }

    public String getIdentifierStatusDdlb() {
        return identifierStatusDdlb;
    }

    public void setIdentifierStatusDdlb(String identifierStatusDdlb) {
        this.identifierStatusDdlb = identifierStatusDdlb;
    }

    public String getEntityCodeName() {
        return entityCodeName;
    }

    public void setEntityCodeName(String entityCodeName) {
        this.entityCodeName = entityCodeName;
    }
}
