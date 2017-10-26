package com.stpl.app.contract.global.dto;

import java.io.Serializable;

import com.stpl.ifs.util.HelperUtils;

/**
 * The Class ItemPricingDTO.
 */
public class ItemPricingDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 8767211638880293192L;
    /**
     * The item system id.
     */
    private int itemSystemId;
    /**
     * The qualifier id.
     */
    private int qualifierId;
    /**
     * The identifier code qualifier.
     */
    private String identifierCodeQualifier = HelperUtils.EMPTY;
    /**
     * The identifier code qualifier name.
     */
    private String identifierCodeQualifierName = HelperUtils.EMPTY;
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
    private String createdDate;
    /**
     * The modified by.
     */
    private String modifiedBy;
    /**
     * The modified date.
     */
    private String modifiedDate;
    /**
     * The error.
     */
    private boolean error;
    /**
     * The item uom.
     */
    private String itemUom = HelperUtils.EMPTY;
    /**
     * The item uom text.
     */
    private String itemUomText;
    /**
     * The item price.
     */
    private String itemPrice = HelperUtils.EMPTY;

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
     * Gets the qualifier id.
     *
     * @return the qualifier id
     */
    public int getQualifierId() {
        return qualifierId;
    }

    /**
     * Sets the qualifier id.
     *
     * @param qualifierId the qualifier id
     */
    public void setQualifierId(final int qualifierId) {
        this.qualifierId = qualifierId;
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
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modified date
     */
    public void setModifiedDate(final String modifiedDate) {
        this.modifiedDate = modifiedDate;
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
     * Gets the item uom.
     *
     * @return the item uom
     */
    public String getItemUom() {
        return itemUom;
    }

    /**
     * Sets the item uom.
     *
     * @param itemUom the item uom
     */
    public void setItemUom(final String itemUom) {
        this.itemUom = itemUom;
    }
}
