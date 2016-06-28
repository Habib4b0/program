package com.stpl.app.contract.global.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.stpl.ifs.util.HelperDTO;

/**
 * The Class SearchItemForm.
 */
public class SearchItemForm implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -771491040115614681L;
    /**
     * The system id.
     */
    private String systemId;
    /**
     * The error messages.
     */
    private String errorMessages = StringUtils.EMPTY;
    /**
     * The item id.
     */
    private String itemId = StringUtils.EMPTY;
    /**
     * The item no.
     */
    private String itemNo = StringUtils.EMPTY;
    /**
     * The item name.
     */
    private String itemName = StringUtils.EMPTY;
    /**
     * The item status.
     */
    private HelperDTO itemStatus;
    /**
     * The manufacturer no.
     */
    private String manufacturerNo = StringUtils.EMPTY;
    /**
     * The item type.
     */
    private HelperDTO itemType;
    /**
     * The item desc.
     */
    private String itemDesc = StringUtils.EMPTY;
    /**
     * The identifier.
     */
    private String identifier = StringUtils.EMPTY;
    /**
     * The item type desc.
     */
    private String itemTypeDesc = StringUtils.EMPTY;
    /**
     * The item status desc.
     */
    private String itemStatusDesc = StringUtils.EMPTY;
    /**
     * The identifier type desc.
     */
    private HelperDTO identifierTypeDesc;
    /**
     * The qualifier id.
     */
    private String qualifierId = StringUtils.EMPTY;
    /**
     * The manufacturer id.
     */
    private String manufacturerId = StringUtils.EMPTY;
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
     * The item start date.
     */
    private String itemStartDate;
    /**
     * The record lock status.
     */
    private String recordLockStatus;
    /**
     * The item code.
     */
    private String itemCode = StringUtils.EMPTY;
    /**
     * The package size.
     */
    private String packageSize = StringUtils.EMPTY;
    /**
     * The package size intro date.
     */
    private String packageSizeIntroDate;
    /**
     * The upps.
     */
    private String upps = StringUtils.EMPTY;
    /**
     * The item end date.
     */
    @NotBlank(message = "Item End  String should  be present")
    private String itemEndDate;
    /**
     * The labeler code.
     */
    private String labelerCode = StringUtils.EMPTY;
    /**
     * The organization key.
     */
    private String organizationKey = StringUtils.EMPTY;
    /**
     * The acquisition date.
     */
    private String acquisitionDate;
    /**
     * The authorized generic.
     */
    private String authorizedGeneric;
    /**
     * The authorized generic start date.
     */
    private String authorizedGenericStartDate;
    /**
     * The authorized generic end date.
     */
    private String authorizedGenericEndDate;
    /**
     * The form.
     */
    private String form;
    /**
     * The strength.
     */
    private String strength;
    /**
     * Therapeutic class.
     */
    private String therapeuticClass;
    /**
     * The first sale date.
     */
    private String firstSaleDate;
    /**
     * The item type indication.
     */
    private String itemTypeIndication;
    /**
     * The item class.
     */
    private String itemClass = StringUtils.EMPTY;
    /**
     * The market termination date.
     */
    private String marketTerminationDate;
    /**
     * The new formulation indicator.
     */
    private String newFormulationIndicator;
    /**
     * The new formulation.
     */
    private String newFormulation;
    /**
     * The new formulation start date.
     */
    private String newFormulationStartDate;
    /**
     * The new formulation end date.
     */
    private String newFormulationEndDate;
    /**
     * The pediatric exclusive indicator.
     */
    private String pediatricExclusiveIndicator;
    /**
     * The pediatric exclusive start date.
     */
    private String pediatricExclusiveStartDate;
    /**
     * The pediatric exclusive end date.
     */
    private String pediatricExclusiveEndDate;
    /**
     * The clotting factor indicator.
     */
    private String clottingFactorIndicator;
    /**
     * The clotting factor start date.
     */
    private String clottingFactorStartDate;
    /**
     * The clotting factor end date.
     */
    private String clottingFactorEndDate;
    /**
     * The primary uom.
     */
    private String primaryUom;
    /**
     * The secondary uom.
     */
    private String secondaryUom;
    /**
     * The shelf life.
     */
    private String shelfLife;
    /**
     * The shelf life type.
     */
    private String shelfLifeType;
    /**
     * The dual pricing indicator.
     */
    private String dualPricingIndicator;
    /**
     * The item family id.
     */
    private String itemFamilyId;
    /**
     * The udc1.
     */
    private String udc1;
    /**
     * The item indicator.
     */
    private boolean itemIndicator;
    /**
     * The udc2.
     */
    private String udc2;
    /**
     * The udc3.
     */
    private String udc3;
    /**
     * The udc4.
     */
    private String udc4;
    /**
     * The udc5.
     */
    private String udc5;
    /**
     * The udc6.
     */
    private String udc6;
    /**
     * The acquired amp.
     */
    private BigDecimal acquiredAmp;
    /**
     * The acquired bamp.
     */
    private BigDecimal acquiredBamp;
    /**
     * The obra bamp.
     */
    private BigDecimal obraBamp;
    /**
     * The dra.
     */
    private BigDecimal dra;
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
     * The batch id.
     */
    private String batchId;
    /**
     * The brand.
     */
    private String brand;
    /**
     * The doses per unit.
     */
    private String dosesPerUnit;
    /**
     * The inbound status.
     */
    private Character inboundStatus;

    /**
     * Gets the search flag.
     *
     * @return the search flag
     */
    public Boolean getSearchFlag() {
        return searchFlag;
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
     * Gets the identifier flag.
     *
     * @return the identifier flag
     */
    public Boolean getIdentifierFlag() {
        return identifierFlag;
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
     * Gets the item no.
     *
     * @return the item no
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * Sets the item no.
     *
     * @param itemNo the item no
     */
    public void setItemNo(final String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * Gets the item name.
     *
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the item name.
     *
     * @param itemName the item name
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the item status.
     *
     * @return the item status
     */
    public HelperDTO getItemStatus() {
        return itemStatus;
    }

    /**
     * Sets the item status.
     *
     * @param itemStatus the item status
     */
    public void setItemStatus(final HelperDTO itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * Gets the manufacturer no.
     *
     * @return the manufacturer no
     */
    public String getManufacturerNo() {
        return manufacturerNo;
    }

    /**
     * Sets the manufacturer no.
     *
     * @param manufacturerNo the manufacturer no
     */
    public void setManufacturerNo(final String manufacturerNo) {
        this.manufacturerNo = manufacturerNo;
    }

    /**
     * Gets the item type.
     *
     * @return the item type
     */
    public HelperDTO getItemType() {
        return itemType;
    }

    /**
     * Sets the item type.
     *
     * @param itemType the item type
     */
    public void setItemType(final HelperDTO itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the item desc.
     *
     * @return the item desc
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * Sets the item desc.
     *
     * @param itemDesc the item desc
     */
    public void setItemDesc(final String itemDesc) {
        this.itemDesc = itemDesc;
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
     * Gets the item type desc.
     *
     * @return the item type desc
     */
    public String getItemTypeDesc() {
        return itemTypeDesc;
    }

    /**
     * Sets the item type desc.
     *
     * @param itemTypeDesc the item type desc
     */
    public void setItemTypeDesc(final String itemTypeDesc) {
        this.itemTypeDesc = itemTypeDesc;
    }

    /**
     * Gets the item status desc.
     *
     * @return the item status desc
     */
    public String getItemStatusDesc() {
        return itemStatusDesc;
    }

    /**
     * Sets the item status desc.
     *
     * @param itemStatusDesc the item status desc
     */
    public void setItemStatusDesc(final String itemStatusDesc) {
        this.itemStatusDesc = itemStatusDesc;
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
     * Gets the manufacturer id.
     *
     * @return the manufacturer id
     */
    public String getManufacturerId() {
        return manufacturerId;
    }

    /**
     * Sets the manufacturer id.
     *
     * @param manufacturerId the manufacturer id
     */
    public void setManufacturerId(final String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * Checks if is search flag.
     *
     * @return true, if checks if is search flag
     */
    public Boolean isSearchFlag() {
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
     * Checks if is qualifier flag.
     *
     * @return true, if checks if is qualifier flag
     */
    public Boolean isQualifierFlag() {
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
     * Checks if is identifier flag.
     *
     * @return true, if checks if is identifier flag
     */
    public Boolean isIdentifierFlag() {
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
    public String getItemStartDate() {
        return itemStartDate;
    }

    /**
     * Sets the item start date.
     *
     * @param itemStartDate the item start date
     */
    public void setItemStartDate(final String itemStartDate) {
        this.itemStartDate = itemStartDate;
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
     * Gets the item code.
     *
     * @return the item code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the item code.
     *
     * @param itemCode the item code
     */
    public void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * Gets the package size.
     *
     * @return the package size
     */
    public String getPackageSize() {
        return packageSize;
    }

    /**
     * Sets the package size.
     *
     * @param packageSize the package size
     */
    public void setPackageSize(final String packageSize) {
        this.packageSize = packageSize;
    }

    /**
     * Gets the package size intro date.
     *
     * @return the package size intro date
     */
    public String getPackageSizeIntroDate() {
        return packageSizeIntroDate;
    }

    /**
     * Sets the package size intro date.
     *
     * @param packageSizeIntroDate the package size intro date
     */
    public void setPackageSizeIntroDate(final String packageSizeIntroDate) {
        this.packageSizeIntroDate = packageSizeIntroDate;
    }

    /**
     * Gets the upps.
     *
     * @return the upps
     */
    public String getUpps() {
        return upps;
    }

    /**
     * Sets the upps.
     *
     * @param upps the upps
     */
    public void setUpps(final String upps) {
        this.upps = upps;
    }

    /**
     * Gets the item end date.
     *
     * @return the item end date
     */
    public String getItemEndDate() {
        return itemEndDate;
    }

    /**
     * Sets the item end date.
     *
     * @param itemEndDate the item end date
     */
    public void setItemEndDate(final String itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    /**
     * Gets the labeler code.
     *
     * @return the labeler code
     */
    public String getLabelerCode() {
        return labelerCode;
    }

    /**
     * Sets the labeler code.
     *
     * @param labelerCode the labeler code
     */
    public void setLabelerCode(final String labelerCode) {
        this.labelerCode = labelerCode;
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
     * Gets the acquisition date.
     *
     * @return the acquisition date
     */
    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Sets the acquisition date.
     *
     * @param acquisitionDate the acquisition date
     */
    public void setAcquisitionDate(final String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    /**
     * Gets the authorized generic.
     *
     * @return the authorized generic
     */
    public String getAuthorizedGeneric() {
        return authorizedGeneric;
    }

    /**
     * Sets the authorized generic.
     *
     * @param authorizedGeneric the authorized generic
     */
    public void setAuthorizedGeneric(final String authorizedGeneric) {
        this.authorizedGeneric = authorizedGeneric;
    }

    /**
     * Gets the authorized generic start date.
     *
     * @return the authorized generic start date
     */
    public String getAuthorizedGenericStartDate() {
        return authorizedGenericStartDate;
    }

    /**
     * Sets the authorized generic start date.
     *
     * @param authorizedGenericStartDate the authorized generic start date
     */
    public void setAuthorizedGenericStartDate(final String authorizedGenericStartDate) {
        this.authorizedGenericStartDate = authorizedGenericStartDate;
    }

    /**
     * Gets the authorized generic end date.
     *
     * @return the authorized generic end date
     */
    public String getAuthorizedGenericEndDate() {
        return authorizedGenericEndDate;
    }

    /**
     * Sets the authorized generic end date.
     *
     * @param authorizedGenericEndDate the authorized generic end date
     */
    public void setAuthorizedGenericEndDate(final String authorizedGenericEndDate) {
        this.authorizedGenericEndDate = authorizedGenericEndDate;
    }

    /**
     * Gets the form.
     *
     * @return the form
     */
    public String getForm() {
        return form;
    }

    /**
     * Sets the form.
     *
     * @param form the form
     */
    public void setForm(final String form) {
        this.form = form;
    }

    /**
     * Gets the strength.
     *
     * @return the strength
     */
    public String getStrength() {
        return strength;
    }

    /**
     * Sets the strength.
     *
     * @param strength the strength
     */
    public void setStrength(final String strength) {
        this.strength = strength;
    }

    /**
     * Gets the therapeutic class.
     *
     * @return the therapeutic class
     */
    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    /**
     * Sets the therapeutic class.
     *
     * @param therapeuticClass therapeutic class
     */
    public void setTherapeuticClass(final String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    /**
     * Gets the first sale date.
     *
     * @return the first sale date
     */
    public String getFirstSaleDate() {
        return firstSaleDate;
    }

    /**
     * Sets the first sale date.
     *
     * @param firstSaleDate the first sale date
     */
    public void setFirstSaleDate(final String firstSaleDate) {
        this.firstSaleDate = firstSaleDate;
    }

    /**
     * Gets the item type indication.
     *
     * @return the item type indication
     */
    public String getItemTypeIndication() {
        return itemTypeIndication;
    }

    /**
     * Sets the item type indication.
     *
     * @param itemTypeIndication the item type indication
     */
    public void setItemTypeIndication(final String itemTypeIndication) {
        this.itemTypeIndication = itemTypeIndication;
    }

    /**
     * Gets the item class.
     *
     * @return the item class
     */
    public String getItemClass() {
        return itemClass;
    }

    /**
     * Sets the item class.
     *
     * @param itemClass the item class
     */
    public void setItemClass(final String itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * Gets the market termination date.
     *
     * @return the market termination date
     */
    public String getMarketTerminationDate() {
        return marketTerminationDate;
    }

    /**
     * Sets the market termination date.
     *
     * @param marketTerminationDate the market termination date
     */
    public void setMarketTerminationDate(final String marketTerminationDate) {
        this.marketTerminationDate = marketTerminationDate;
    }

    /**
     * Gets the new formulation indicator.
     *
     * @return the new formulation indicator
     */
    public String getNewFormulationIndicator() {
        return newFormulationIndicator;
    }

    /**
     * Sets the new formulation indicator.
     *
     * @param newFormulationIndicator the new formulation indicator
     */
    public void setNewFormulationIndicator(final String newFormulationIndicator) {
        this.newFormulationIndicator = newFormulationIndicator;
    }

    /**
     * Gets the new formulation.
     *
     * @return the new formulation
     */
    public String getNewFormulation() {
        return newFormulation;
    }

    /**
     * Sets the new formulation.
     *
     * @param newFormulation the new formulation
     */
    public void setNewFormulation(final String newFormulation) {
        this.newFormulation = newFormulation;
    }

    /**
     * Gets the new formulation start date.
     *
     * @return the new formulation start date
     */
    public String getNewFormulationStartDate() {
        return newFormulationStartDate;
    }

    /**
     * Sets the new formulation start date.
     *
     * @param newFormulationStartDate the new formulation start date
     */
    public void setNewFormulationStartDate(final String newFormulationStartDate) {
        this.newFormulationStartDate = newFormulationStartDate;
    }

    /**
     * Gets the new formulation end date.
     *
     * @return the new formulation end date
     */
    public String getNewFormulationEndDate() {
        return newFormulationEndDate;
    }

    /**
     * Sets the new formulation end date.
     *
     * @param newFormulationEndDate the new formulation end date
     */
    public void setNewFormulationEndDate(final String newFormulationEndDate) {
        this.newFormulationEndDate = newFormulationEndDate;
    }

    /**
     * Gets the pediatric exclusive indicator.
     *
     * @return the pediatric exclusive indicator
     */
    public String getPediatricExclusiveIndicator() {
        return pediatricExclusiveIndicator;
    }

    /**
     * Sets the pediatric exclusive indicator.
     *
     * @param pediatricExclusiveIndicator the pediatric exclusive indicator
     */
    public void setPediatricExclusiveIndicator(final String pediatricExclusiveIndicator) {
        this.pediatricExclusiveIndicator = pediatricExclusiveIndicator;
    }

    /**
     * Gets the pediatric exclusive start date.
     *
     * @return the pediatric exclusive start date
     */
    public String getPediatricExclusiveStartDate() {
        return pediatricExclusiveStartDate;
    }

    /**
     * Sets the pediatric exclusive start date.
     *
     * @param pediatricExclusiveStartDate the pediatric exclusive start date
     */
    public void setPediatricExclusiveStartDate(final String pediatricExclusiveStartDate) {
        this.pediatricExclusiveStartDate = pediatricExclusiveStartDate;
    }

    /**
     * Gets the pediatric exclusive end date.
     *
     * @return the pediatric exclusive end date
     */
    public String getPediatricExclusiveEndDate() {
        return pediatricExclusiveEndDate;
    }

    /**
     * Sets the pediatric exclusive end date.
     *
     * @param pediatricExclusiveEndDate the pediatric exclusive end date
     */
    public void setPediatricExclusiveEndDate(final String pediatricExclusiveEndDate) {
        this.pediatricExclusiveEndDate = pediatricExclusiveEndDate;
    }

    /**
     * Gets the clotting factor indicator.
     *
     * @return the clotting factor indicator
     */
    public String getClottingFactorIndicator() {
        return clottingFactorIndicator;
    }

    /**
     * Sets the clotting factor indicator.
     *
     * @param clottingFactorIndicator the clotting factor indicator
     */
    public void setClottingFactorIndicator(final String clottingFactorIndicator) {
        this.clottingFactorIndicator = clottingFactorIndicator;
    }

    /**
     * Gets the clotting factor start date.
     *
     * @return the clotting factor start date
     */
    public String getClottingFactorStartDate() {
        return clottingFactorStartDate;
    }

    /**
     * Sets the clotting factor start date.
     *
     * @param clottingFactorStartDate the clotting factor start date
     */
    public void setClottingFactorStartDate(final String clottingFactorStartDate) {
        this.clottingFactorStartDate = clottingFactorStartDate;
    }

    /**
     * Gets the clotting factor end date.
     *
     * @return the clotting factor end date
     */
    public String getClottingFactorEndDate() {
        return clottingFactorEndDate;
    }

    /**
     * Sets the clotting factor end date.
     *
     * @param clottingFactorEndDate the clotting factor end date
     */
    public void setClottingFactorEndDate(final String clottingFactorEndDate) {
        this.clottingFactorEndDate = clottingFactorEndDate;
    }

    /**
     * Gets the primary uom.
     *
     * @return the primary uom
     */
    public String getPrimaryUom() {
        return primaryUom;
    }

    /**
     * Sets the primary uom.
     *
     * @param primaryUom the primary uom
     */
    public void setPrimaryUom(final String primaryUom) {
        this.primaryUom = primaryUom;
    }

    /**
     * Gets the secondary uom.
     *
     * @return the secondary uom
     */
    public String getSecondaryUom() {
        return secondaryUom;
    }

    /**
     * Sets the secondary uom.
     *
     * @param secondaryUom the secondary uom
     */
    public void setSecondaryUom(final String secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    /**
     * Gets the shelf life.
     *
     * @return the shelf life
     */
    public String getShelfLife() {
        return shelfLife;
    }

    /**
     * Sets the shelf life.
     *
     * @param shelfLife the shelf life
     */
    public void setShelfLife(final String shelfLife) {
        this.shelfLife = shelfLife;
    }

    /**
     * Gets the shelf life type.
     *
     * @return the shelf life type
     */
    public String getShelfLifeType() {
        return shelfLifeType;
    }

    /**
     * Sets the shelf life type.
     *
     * @param shelfLifeType the shelf life type
     */
    public void setShelfLifeType(final String shelfLifeType) {
        this.shelfLifeType = shelfLifeType;
    }

    /**
     * Gets the dual pricing indicator.
     *
     * @return the dual pricing indicator
     */
    public String getDualPricingIndicator() {
        return dualPricingIndicator;
    }

    /**
     * Sets the dual pricing indicator.
     *
     * @param dualPricingIndicator the dual pricing indicator
     */
    public void setDualPricingIndicator(final String dualPricingIndicator) {
        this.dualPricingIndicator = dualPricingIndicator;
    }

    /**
     * Gets the item family id.
     *
     * @return the item family id
     */
    public String getItemFamilyId() {
        return itemFamilyId;
    }

    /**
     * Sets the item family id.
     *
     * @param itemFamilyId the item family id
     */
    public void setItemFamilyId(final String itemFamilyId) {
        this.itemFamilyId = itemFamilyId;
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
     * Checks if is item indicator.
     *
     * @return true, if checks if is item indicator
     */
    public boolean isItemIndicator() {
        return itemIndicator;
    }

    /**
     * Sets the item indicator.
     *
     * @param itemIndicator the item indicator
     */
    public void setItemIndicator(final boolean itemIndicator) {
        this.itemIndicator = itemIndicator;
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
     * Gets the acquired amp.
     *
     * @return the acquired amp
     */
    public BigDecimal getAcquiredAmp() {
        return acquiredAmp;
    }

    /**
     * Sets the acquired amp.
     *
     * @param acquiredAmp the acquired amp
     */
    public void setAcquiredAmp(final BigDecimal acquiredAmp) {
        this.acquiredAmp = acquiredAmp;
    }

    /**
     * Gets the acquired bamp.
     *
     * @return the acquired bamp
     */
    public BigDecimal getAcquiredBamp() {
        return acquiredBamp;
    }

    /**
     * Sets the acquired bamp.
     *
     * @param acquiredBamp the acquired bamp
     */
    public void setAcquiredBamp(final BigDecimal acquiredBamp) {
        this.acquiredBamp = acquiredBamp;
    }

    /**
     * Gets the obra bamp.
     *
     * @return the obra bamp
     */
    public BigDecimal getObraBamp() {
        return obraBamp;
    }

    /**
     * Sets the obra bamp.
     *
     * @param obraBamp the obra bamp
     */
    public void setObraBamp(final BigDecimal obraBamp) {
        this.obraBamp = obraBamp;
    }

    /**
     * Gets the dra.
     *
     * @return the dra
     */
    public BigDecimal getDra() {
        return dra;
    }

    /**
     * Sets the dra.
     *
     * @param dra the dra
     */
    public void setDra(final BigDecimal dra) {
        this.dra = dra;
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
     * Gets the brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand.
     *
     * @param brand the brand
     */
    public void setBrand(final String brand) {
        this.brand = brand;
    }

    /**
     * Gets the doses per unit.
     *
     * @return the doses per unit
     */
    public String getDosesPerUnit() {
        return dosesPerUnit;
    }

    /**
     * Sets the doses per unit.
     *
     * @param dosesPerUnit the doses per unit
     */
    public void setDosesPerUnit(final String dosesPerUnit) {
        this.dosesPerUnit = dosesPerUnit;
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
}
