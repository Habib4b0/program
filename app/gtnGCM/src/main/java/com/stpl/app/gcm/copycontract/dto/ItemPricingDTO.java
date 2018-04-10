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
public class ItemPricingDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1993045322981429124L;

    /**
     * The item system id.
     */
    private int itemSystemId;

    /**
     * The item pricing qualifier name.
     */
    private HelperDTO itemPricingQualifierNameDDLB;

    /**
     * The item pricing qualifier id.
     */
    private int itemPricingQualifierId;

    /**
     * The identifier code qualifier.
     */
    private String identifierCodeQualifier = StringUtils.EMPTY;

    /**
     * The identifier code qualifier name.
     */
    private String identifierCodeQualifierName = StringUtils.EMPTY;

    /**
     * The expiration required.
     */
    private String expirationRequired;

    /**
     * The specific tp required.
     */
    private String specificTpRequired;

    /**
     * The identifier code.
     */
    private String identifierCode;

    /**
     * The pricing code status.
     */
    private int pricingCodeStatus;

    /**
     * The pricing code status.
     */
    private String pricingCodeStatusView;

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
    private String entityCodeSid = StringUtils.EMPTY;

    /**
     * The identifier status desc.
     */
    private String identifierStatusDesc;

    /**
     * The entity code desc.
     */
    private String entityCodeDesc;

    /**
     * The qualifiers attached.
     */
    private String qualifiersAttached;

    /**
     * The type id.
     */
    private int typeId;

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
     * The item pricing system id.
     */
    private int itemPricingSystemId;

    /**
     * The modified date.
     */
    private Date modifiedDate;

    /**
     * The error.
     */
    private boolean error;

    /**
     * The item uom.
     */
    private int itemUom;

    /**
     * The item uom.
     */
    private String itemUomView;

    /**
     * The item uom text.
     */
    private String itemUomText;

    /**
     * The item price.
     */
    private String itemPrice = StringUtils.EMPTY;

    /**
     * The item pricing qualifier name.
     */
    private String itemPricingQualifierName;

    /**
     * The source.
     */
    private String source = StringUtils.EMPTY;

    /**
     * The price start date.
     */
    private Date priceStartDate;

    /**
     * The price end date.
     */
    private Date priceEndDate;

    private boolean recordLockStatus;

    private int createdByValue;

    public ItemPricingDTO() {
        super();
    }

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
     * gets ItemPricingQualifierNameDDLB.
     *
     * @return
     */
    public HelperDTO getItemPricingQualifierNameDDLB() {
        return itemPricingQualifierNameDDLB;
    }

    /**
     * sets ItemPricingQualifierNameDDLB.
     *
     * @param itemPricingQualifierNameDDLB
     */
    public void setItemPricingQualifierNameDDLB(final HelperDTO itemPricingQualifierNameDDLB) {
        this.itemPricingQualifierNameDDLB = itemPricingQualifierNameDDLB;
    }

    /**
     * Gets the pricing code status.
     *
     * @return the pricing code status
     */
    public int getPricingCodeStatus() {
        return pricingCodeStatus;
    }

    /**
     * Sets the pricing code status.
     *
     * @param pricingCodeStatus the pricing code status
     */
    public void setPricingCodeStatus(final int pricingCodeStatus) {
        this.pricingCodeStatus = pricingCodeStatus;
    }

    /**
     * Gets the source.
     *
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source.
     *
     * @param source the source
     */
    public void setSource(final String source) {
        this.source = source;
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
     * Gets the item pricing qualifier id.
     *
     * @return the item pricing qualifier id
     */
    public int getItemPricingQualifierId() {
        return itemPricingQualifierId;
    }

    /**
     * Sets the item pricing qualifier id.
     *
     * @param itemPricingQualifierId the item pricing qualifier id
     */
    public void setItemPricingQualifierId(final int itemPricingQualifierId) {
        this.itemPricingQualifierId = itemPricingQualifierId;
    }

    /**
     * Gets the identifier code qualifier.
     *
     * @return the identifier code qualifier
     */
    public String getIdentifierCodeQualifier() {
        return identifierCodeQualifier;
    }

    /**
     * Sets the identifier code qualifier.
     *
     * @param identifierCodeQualifier the identifier code qualifier
     */
    public void setIdentifierCodeQualifier(final String identifierCodeQualifier) {
        this.identifierCodeQualifier = identifierCodeQualifier;
    }

    /**
     * Gets the identifier code qualifier name.
     *
     * @return the identifier code qualifier name
     */
    public String getIdentifierCodeQualifierName() {
        return identifierCodeQualifierName;
    }

    /**
     * Sets the identifier code qualifier name.
     *
     * @param identifierCodeQualifierName the identifier code qualifier name
     */
    public void setIdentifierCodeQualifierName(final String identifierCodeQualifierName) {
        this.identifierCodeQualifierName = identifierCodeQualifierName;
    }

    /**
     * Gets the expiration required.
     *
     * @return the expiration required
     */
    public String getExpirationRequired() {
        return expirationRequired;
    }

    /**
     * Sets the expiration required.
     *
     * @param expirationRequired the expiration required
     */
    public void setExpirationRequired(final String expirationRequired) {
        this.expirationRequired = expirationRequired;
    }

    /**
     * Gets the specific tp required.
     *
     * @return the specific tp required
     */
    public String getSpecificTpRequired() {
        return specificTpRequired;
    }

    /**
     * Sets the specific tp required.
     *
     * @param specificTpRequired the specific tp required
     */
    public void setSpecificTpRequired(final String specificTpRequired) {
        this.specificTpRequired = specificTpRequired;
    }

    /**
     * Gets the identifier code.
     *
     * @return the identifier code
     */
    public String getIdentifierCode() {
        return identifierCode;
    }

    /**
     * Sets the identifier code.
     *
     * @param identifierCode the identifier code
     */
    public void setIdentifierCode(final String identifierCode) {
        this.identifierCode = identifierCode;
    }

    /**
     * Gets the identifier status desc.
     *
     * @return the identifier status desc
     */
    public String getIdentifierStatusDesc() {
        return identifierStatusDesc;
    }

    /**
     * Sets the identifier status desc.
     *
     * @param identifierStatusDesc the identifier status desc
     */
    public void setIdentifierStatusDesc(final String identifierStatusDesc) {
        this.identifierStatusDesc = identifierStatusDesc;
    }

    /**
     * Gets the entity code desc.
     *
     * @return the entity code desc
     */
    public String getEntityCodeDesc() {
        return entityCodeDesc;
    }

    /**
     * Sets the entity code desc.
     *
     * @param entityCodeDesc the entity code desc
     */
    public void setEntityCodeDesc(final String entityCodeDesc) {
        this.entityCodeDesc = entityCodeDesc;
    }

    /**
     * Gets the qualifiers attached.
     *
     * @return the qualifiers attached
     */
    public String getQualifiersAttached() {
        return qualifiersAttached;
    }

    /**
     * Sets the qualifiers attached.
     *
     * @param qualifiersAttached the qualifiers attached
     */
    public void setQualifiersAttached(final String qualifiersAttached) {
        this.qualifiersAttached = qualifiersAttached;
    }

    /**
     * Gets the type id.
     *
     * @return the type id
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Sets the type id.
     *
     * @param typeId the type id
     */
    public void setTypeId(final int typeId) {
        this.typeId = typeId;
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
     * Checks if is error.
     *
     * @return true, if checks if is error
     */
    public boolean isError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the error
     */
    public void setError(final boolean error) {
        this.error = error;
    }

    /**
     * Gets the item uom text.
     *
     * @return the item uom text
     */
    public String getItemUomText() {
        return itemUomText;
    }

    /**
     * Sets the item uom text.
     *
     * @param itemUomText the item uom text
     */
    public void setItemUomText(final String itemUomText) {
        this.itemUomText = itemUomText;
    }

    /**
     * Gets the item price.
     *
     * @return the item price
     */
    public String getItemPrice() {
        return itemPrice;
    }

    /**
     * Sets the item price.
     *
     * @param itemPrice the item price
     */
    public void setItemPrice(final String itemPrice) {
        this.itemPrice = itemPrice;
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
     * Gets the item pricing qualifier name.
     *
     * @return the item pricing qualifier name
     */
    public String getItemPricingQualifierName() {
        return itemPricingQualifierName;
    }

    /**
     * Sets the item pricing qualifier name.
     *
     * @param itemPricingQualifierName the item pricing qualifier name
     */
    public void setItemPricingQualifierName(final String itemPricingQualifierName) {
        this.itemPricingQualifierName = itemPricingQualifierName;
    }

    /**
     * Gets the item uom.
     *
     * @return the item uom
     */
    public int getItemUom() {
        return itemUom;
    }

    /**
     * Sets the item uom.
     *
     * @param itemUom the item uom
     */
    public void setItemUom(final int itemUom) {
        this.itemUom = itemUom;
    }

    /**
     * Gets the price start date.
     *
     * @return the price start date
     */
    public Date getPriceStartDate() {
        return priceStartDate == null ? null : (Date) priceStartDate.clone();
    }

    /**
     * Sets the price start date.
     *
     * @param priceStartDate the price start date
     */
    public void setPriceStartDate(final Date priceStartDate) {
        this.priceStartDate = priceStartDate == null ? null : (Date) priceStartDate.clone();
    }

    /**
     * Gets the price end date.
     *
     * @return the price end date
     */
    public Date getPriceEndDate() {
        return priceEndDate == null ? null : (Date) priceEndDate.clone();
    }

    /**
     * Sets the price end date.
     *
     * @param priceEndDate the price end date
     */
    public void setPriceEndDate(final Date priceEndDate) {
        this.priceEndDate = priceEndDate == null ? null : (Date) priceEndDate.clone();
    }

    /**
     * Gets the item pricing system id.
     *
     * @return the item pricing system id
     */
    public int getItemPricingSystemId() {
        return itemPricingSystemId;
    }

    /**
     * Sets the item pricing system id.
     *
     * @param itemPricingSystemId the item pricing system id
     */
    public void setItemPricingSystemId(final int itemPricingSystemId) {
        this.itemPricingSystemId = itemPricingSystemId;
    }

    public String getPricingCodeStatusView() {
        return pricingCodeStatusView;
    }

    public void setPricingCodeStatusView(String pricingCodeStatusView) {
        this.pricingCodeStatusView = pricingCodeStatusView;
    }

    public String getItemUomView() {
        return itemUomView;
    }

    public void setItemUomView(String itemUomView) {
        this.itemUomView = itemUomView;
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
}
