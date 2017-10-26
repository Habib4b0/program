package com.stpl.app.global.priceschedule.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
// TODO: Auto-generated Javadoc

/**
 * This class is used to hold Price Schedule Parameters.
 *
 * @author manikanta
 */
public class SearchPriceScheduleDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 5277385554711328371L;
    /**
     * Parent price schedule name Field.
     */
    private String parentPriceScheduleName = StringUtils.EMPTY;
    /**
     * Parent price schedule Id Field.
     */
    private String parentPriceScheduleId = StringUtils.EMPTY;
    /**
     * Price schedule name Field.
     */
    private Date priceScheduleStartDate;
    /**
     * Price schedule No Field.
     */
    private String priceScheduleNo = StringUtils.EMPTY;
    /**
     * Price schedule Name Field.
     */
    private String priceScheduleName = StringUtils.EMPTY;
    /**
     * Price schedule Id Field.
     */
    private String priceScheduleId = StringUtils.EMPTY;
    /**
     * Price schedule Type Field.
     */
    private String priceScheduleType = StringUtils.EMPTY;
    /**
     * Modified Date Field.
     */
    private Date modifiedDate;
    /**
     * The price schedule system id.
     */
    private int priceScheduleSystemId;
    
    private String psSystemId = StringUtils.EMPTY;
    
    private String parentId = StringUtils.EMPTY;
    
    private String parentName  = StringUtils.EMPTY;
    
    private String itemId  = StringUtils.EMPTY;
    
    private String itemNo  = StringUtils.EMPTY;
    
    private String itemName  = StringUtils.EMPTY;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    /**
     * The record lock status.
     */
    private String recordLockStatus = StringUtils.EMPTY;
    /**
     * The created date.
     */
    private Date createdDate;
    /**
     * The created by.
     */
    private String createdBy = StringUtils.EMPTY;
    /**
     * The price schedule designation.
     */
    private String priceScheduleDesignation = StringUtils.EMPTY;
    /**
     * The price schedule end date.
     */
    private Date priceScheduleEndDate;
    /**
     * The price schedule status.
     */
    private String priceScheduleStatus = StringUtils.EMPTY;
    /**
     * The batch id.
     */
    private String batchId = StringUtils.EMPTY;
    /**
     * The price schedule category.
     */
    private String priceScheduleCategory = StringUtils.EMPTY;
    /**
     * The trade class.
     */
    private String tradeClass = StringUtils.EMPTY;
    /**
     * The inbound status.
     */
    private String inboundStatus = StringUtils.EMPTY;
    /**
     * The modified by.
     */
    private String modifiedBy = StringUtils.EMPTY;

       private String priceScheduleIdValue;
    private String priceScheduleNoValue;
    private String priceScheduleNameValue;
    private String priceScheduleStatusValue;
    private String priceScheduleTypeValue;

    public String getPsSystemId() {
        return psSystemId;
    }

    public void setPsSystemId(String psSystemId) {
        this.psSystemId = psSystemId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    
    
    /**
     * Gets the parent price schedule name.
     *
     * @return the parentPriceScheduleName
     */
    public String getParentPriceScheduleName() {
        return parentPriceScheduleName;
    }

    /**
     * Sets the parent price schedule name.
     *
     * @param parentPriceScheduleName the parentPriceScheduleName to set
     */
    public void setParentPriceScheduleName(final String parentPriceScheduleName) {
        this.parentPriceScheduleName = parentPriceScheduleName;
    }

    /**
     * Gets the parent price schedule id.
     *
     * @return the parentPriceScheduleId
     */
    public String getParentPriceScheduleId() {
        return parentPriceScheduleId;
    }

    /**
     * Sets the parent price schedule id.
     *
     * @param parentPriceScheduleId the parentPriceScheduleId to set
     */
    public void setParentPriceScheduleId(final String parentPriceScheduleId) {
        this.parentPriceScheduleId = parentPriceScheduleId;
    }

    /**
     * Gets the price schedule start date.
     *
     * @return the priceScheduleStartDate
     */
    public Date getPriceScheduleStartDate() {
        return priceScheduleStartDate;
    }

    /**
     * Sets the price schedule start date.
     *
     * @param priceScheduleStartDate the priceScheduleStartDate to set
     */
    public void setPriceScheduleStartDate(final Date priceScheduleStartDate) {
        this.priceScheduleStartDate = priceScheduleStartDate;
    }

    /**
     * Gets the price schedule no.
     *
     * @return the priceScheduleNo
     */
    public String getPriceScheduleNo() {
        return priceScheduleNo;
    }

    /**
     * Sets the price schedule no.
     *
     * @param priceScheduleNo the priceScheduleNo to set
     */
    public void setPriceScheduleNo(final String priceScheduleNo) {
        this.priceScheduleNo = priceScheduleNo;
    }

    /**
     * Gets the price schedule name.
     *
     * @return the priceScheduleName
     */
    public String getPriceScheduleName() {
        return priceScheduleName;
    }

    /**
     * Sets the price schedule name.
     *
     * @param priceScheduleName the priceScheduleName to set
     */
    public void setPriceScheduleName(final String priceScheduleName) {
        this.priceScheduleName = priceScheduleName;
    }

    /**
     * Gets the price schedule id.
     *
     * @return the priceScheduleId
     */
    public String getPriceScheduleId() {
        return priceScheduleId;
    }

    /**
     * Sets the price schedule id.
     *
     * @param priceScheduleId the priceScheduleId to set
     */
    public void setPriceScheduleId(final String priceScheduleId) {
        this.priceScheduleId = priceScheduleId;
    }

    /**
     * Gets the price schedule type.
     *
     * @return the priceScheduleType
     */
    public String getPriceScheduleType() {
        return priceScheduleType;
    }

    /**
     * Sets the price schedule type.
     *
     * @param priceScheduleType the priceScheduleType to set
     */
    public void setPriceScheduleType(final String priceScheduleType) {
        this.priceScheduleType = priceScheduleType;
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
     * Gets the price schedule system id.
     *
     * @return the priceScheduleSystemId
     */
    public int getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    /**
     * Sets the price schedule system id.
     *
     * @param priceScheduleSystemId the priceScheduleSystemId to set
     */
    public void setPriceScheduleSystemId(final int priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    /**
     * Gets the record lock status.
     *
     * @return the recordLockStatus
     */
    public String getRecordLockStatus() {
        return recordLockStatus;
    }

    /**
     * Sets the record lock status.
     *
     * @param recordLockStatus the recordLockStatus to set
     */
    public void setRecordLockStatus(final String recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
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
     * Gets the created by.
     *
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the price schedule designation.
     *
     * @return the priceScheduleDesignation
     */
    public String getPriceScheduleDesignation() {
        return priceScheduleDesignation;
    }

    /**
     * Sets the price schedule designation.
     *
     * @param priceScheduleDesignation the priceScheduleDesignation to set
     */
    public void setPriceScheduleDesignation(final String priceScheduleDesignation) {
        this.priceScheduleDesignation = priceScheduleDesignation;
    }

    /**
     * Gets the price schedule end date.
     *
     * @return the priceScheduleEndDate
     */
    public Date getPriceScheduleEndDate() {
        return priceScheduleEndDate;
    }

    /**
     * Sets the price schedule end date.
     *
     * @param priceScheduleEndDate the priceScheduleEndDate to set
     */
    public void setPriceScheduleEndDate(final Date priceScheduleEndDate) {
        this.priceScheduleEndDate = priceScheduleEndDate;
    }

    /**
     * Gets the price schedule status.
     *
     * @return the priceScheduleStatus
     */
    public String getPriceScheduleStatus() {
        return priceScheduleStatus;
    }

    /**
     * Sets the price schedule status.
     *
     * @param priceScheduleStatus the priceScheduleStatus to set
     */
    public void setPriceScheduleStatus(final String priceScheduleStatus) {
        this.priceScheduleStatus = priceScheduleStatus;
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
     * Gets the price schedule category.
     *
     * @return the priceScheduleCategory
     */
    public String getPriceScheduleCategory() {
        return priceScheduleCategory;
    }

    /**
     * Sets the price schedule category.
     *
     * @param priceScheduleCategory the priceScheduleCategory to set
     */
    public void setPriceScheduleCategory(final String priceScheduleCategory) {
        this.priceScheduleCategory = priceScheduleCategory;
    }

    /**
     * Gets the trade class.
     *
     * @return the tradeClass
     */
    public String getTradeClass() {
        return tradeClass;
    }

    /**
     * Sets the trade class.
     *
     * @param tradeClass the tradeClass to set
     */
    public void setTradeClass(final String tradeClass) {
        this.tradeClass = tradeClass;
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
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getPriceScheduleIdValue() {
        return priceScheduleIdValue;
    }

    public void setPriceScheduleIdValue(String priceScheduleIdValue) {
        this.priceScheduleIdValue = priceScheduleIdValue;
    }

    public String getPriceScheduleNoValue() {
        return priceScheduleNoValue;
    }

    public void setPriceScheduleNoValue(String priceScheduleNoValue) {
        this.priceScheduleNoValue = priceScheduleNoValue;
    }

    public String getPriceScheduleNameValue() {
        return priceScheduleNameValue;
    }

    public void setPriceScheduleNameValue(String priceScheduleNameValue) {
        this.priceScheduleNameValue = priceScheduleNameValue;
    }

    public String getPriceScheduleStatusValue() {
        return priceScheduleStatusValue;
    }

    public void setPriceScheduleStatusValue(String priceScheduleStatusValue) {
        this.priceScheduleStatusValue = priceScheduleStatusValue;
    }

    public String getPriceScheduleTypeValue() {
        return priceScheduleTypeValue;
    }

    public void setPriceScheduleTypeValue(String priceScheduleTypeValue) {
        this.priceScheduleTypeValue = priceScheduleTypeValue;
    }
}
