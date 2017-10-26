package com.stpl.app.contract.global.dto;

import java.io.Serializable;
import java.util.Date;

import com.stpl.ifs.util.HelperUtils;

/**
 * A DTO object which is used as a form object in create user and edit user
 * forms.
 *
 * @author arulmurugan
 */
public class ItemIrtIdentifierDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 5017473592380464906L;
    /**
     * The item system id.
     */
    private int itemSystemId;
    /**
     * The item identifier.
     */
    private String itemIdentifier = HelperUtils.EMPTY;
    /**
     * The item irt qualifier id.
     */
    private int itemIrtQualifierId;
    /**
     * The item irt qualifier name.
     */
    private String itemIrtQualifierName = HelperUtils.EMPTY;
    /**
     * The identifier status.
     */
    private String identifierStatus = HelperUtils.EMPTY;
    /**
     * The start date.
     */
    private String startDate;
    /**
     * The end date.
     */
    private String endDate;
    /**
     * The entity code.
     */
    private String entityCode = HelperUtils.EMPTY;
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
     * The item irt identifier indicator.
     */
    private boolean itemIrtIdentifierIndicator;

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
    public String getIdentifierStatus() {
        return identifierStatus;
    }

    /**
     * Sets the identifier status.
     *
     * @param identifierStatus the identifier status
     */
    public void setIdentifierStatus(final String identifierStatus) {
        this.identifierStatus = identifierStatus;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(final String endDate) {
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
}