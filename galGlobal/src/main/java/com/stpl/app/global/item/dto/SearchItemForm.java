package com.stpl.app.global.item.dto;

import com.stpl.app.util.ConstantsUtils;
import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.stpl.app.util.HelperDTO;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchItemForm.
 */
public class SearchItemForm implements Serializable {
	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2456121686748264335L;

    /**
     * The dto.
     */
    private HelperDTO dto = new HelperDTO(ConstantsUtils.SELECT_ONE);

    /**
     * The system id.
     */
    private String systemId = StringUtils.EMPTY;
    ;
	
	/** The error messages. */
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
    private String itemStatus = StringUtils.EMPTY;

    /**
     * The item type.
     */
    private String itemType = StringUtils.EMPTY;

    /**
     * The manufacturer no.
     */
    private String manufacturerNo = StringUtils.EMPTY;

    /**
     * The item desc.
     */
    private String itemDesc = StringUtils.EMPTY;

    /**
     * The item identifier.
     */
    private String itemIdentifier = StringUtils.EMPTY;

    /**
     * The item type desc.
     */
    private String itemTypeDesc = StringUtils.EMPTY;

    /**
     * The item status desc.
     */
    private String itemStatusDesc = StringUtils.EMPTY;

    /**
     * The item irt qualifier name.
     */
    private HelperDTO itemIrtQualifierName;

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
    private boolean recordLockStatus;

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
    private String upps;

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
    private Integer form;

    /**
     * The strength.
     */
    private Integer strength;

    /**
     * Therapeutic class.
     */
    private String therapeuticClass = StringUtils.EMPTY;

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
    private String itemClass;

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
    private int shelfLifeType;

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

    private Date itemMasterStartDate;

    private Date itemMasterEndDate;

    /**
     * The pediatric exclusive start date.
     */
    private Date pediatricExcStartDate;

    /**
     * The pediatric exclusive end date.
     */
    private Date pediatricExcEndDate;

    /**
     * The clotting factor start date.
     */
    private Date clottingFactStartDate;

    /**
     * The clotting factor end date.
     */
    private Date clottingFactEndDate;

    /**
     * The ndc9.
     */
    private HelperDTO ndc9 = new HelperDTO(ConstantsUtils.SELECT_ONE);

    /**
     * The ndc8.
     */
    private HelperDTO ndc8 = new HelperDTO(ConstantsUtils.SELECT_ONE);

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

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

    private int brandMasterSid;

    /**
     * The doses per unit.
     */
    private String dosesPerUnit;

    /**
     * The inbound status.
     */
    private Character inboundStatus;

    /**
     * The brand id.
     */
    private String brandId;

    /**
     * The cacc ddlb.
     */
    private HelperDTO brandDdlb = new HelperDTO(ConstantsUtils.SELECT_ONE); 

    /**
     * The item irt qualifier name ddlb.
     */
    private HelperDTO itemIrtQualifierNameDDLB = new HelperDTO(ConstantsUtils.SELECT_ONE);

    /**
     * The Item Display Brand.
     */
    private String displayBrand;

    /**
     * Gets the brand ddlb.
     *
     * @return the brand ddlb
     */
    public HelperDTO getBrandDdlb() {
        return brandDdlb;
    }

    /**
     * Sets the brand ddlb.
     *
     * @param brandDdlb the new brand ddlb
     */
    public void setBrandDdlb(final HelperDTO brandDdlb) {
        this.brandDdlb = brandDdlb;
    }

    /**
     * Gets the system id.
     *
     * @return the systemId
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Sets the system id.
     *
     * @param systemId the systemId to set
     */
    public void setSystemId(final String systemId) {
        this.systemId = systemId;
    }

    /**
     * Gets the error messages.
     *
     * @return the errorMessages
     */
    public String getErrorMessages() {
        return errorMessages;
    }

    /**
     * Sets the error messages.
     *
     * @param errorMessages the errorMessages to set
     */
    public void setErrorMessages(final String errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * Gets the item id.
     *
     * @return the itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Sets the item id.
     *
     * @param itemId the itemId to set
     */
    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the item no.
     *
     * @return the itemNo
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * Sets the item no.
     *
     * @param itemNo the itemNo to set
     */
    public void setItemNo(final String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * Gets the item name.
     *
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the item name.
     *
     * @param itemName the itemName to set
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the item status.
     *
     * @return the itemStatus
     */
    public String getItemStatus() {
        return itemStatus;
    }

    /**
     * Sets the item status.
     *
     * @param itemStatus the itemStatus to set
     */
    public void setItemStatus(final String itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * Gets the item type.
     *
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Sets the item type.
     *
     * @param itemType the itemType to set
     */
    public void setItemType(final String itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the manufacturer no.
     *
     * @return the manufacturerNo
     */
    public String getManufacturerNo() {
        return manufacturerNo;
    }

    /**
     * Sets the manufacturer no.
     *
     * @param manufacturerNo the manufacturerNo to set
     */
    public void setManufacturerNo(final String manufacturerNo) {
        this.manufacturerNo = manufacturerNo;
    }

    /**
     * Gets the item desc.
     *
     * @return the itemDesc
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * Sets the item desc.
     *
     * @param itemDesc the itemDesc to set
     */
    public void setItemDesc(final String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * Gets the item identifier.
     *
     * @return the itemIdentifier
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Sets the item identifier.
     *
     * @param itemIdentifier the itemIdentifier to set
     */
    public void setItemIdentifier(final String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    /**
     * Gets the item type desc.
     *
     * @return the itemTypeDesc
     */
    public String getItemTypeDesc() {
        return itemTypeDesc;
    }

    /**
     * Sets the item type desc.
     *
     * @param itemTypeDesc the itemTypeDesc to set
     */
    public void setItemTypeDesc(final String itemTypeDesc) {
        this.itemTypeDesc = itemTypeDesc;
    }

    /**
     * Gets the item status desc.
     *
     * @return the itemStatusDesc
     */
    public String getItemStatusDesc() {
        return itemStatusDesc;
    }

    /**
     * Sets the item status desc.
     *
     * @param itemStatusDesc the itemStatusDesc to set
     */
    public void setItemStatusDesc(final String itemStatusDesc) {
        this.itemStatusDesc = itemStatusDesc;
    }

    /**
     * Gets the item irt qualifier name.
     *
     * @return the itemIrtQualifierName
     */
    public HelperDTO getItemIrtQualifierName() {
        return itemIrtQualifierName;
    }

    /**
     * Sets the item irt qualifier name.
     *
     * @param itemIrtQualifierName the itemIrtQualifierName to set
     */
    public void setItemIrtQualifierName(final HelperDTO itemIrtQualifierName) {
        this.itemIrtQualifierName = itemIrtQualifierName;
    }

    /**
     * Gets the qualifier id.
     *
     * @return the qualifierId
     */
    public String getQualifierId() {
        return qualifierId;
    }

    /**
     * Sets the qualifier id.
     *
     * @param qualifierId the qualifierId to set
     */
    public void setQualifierId(final String qualifierId) {
        this.qualifierId = qualifierId;
    }

    /**
     * Gets the manufacturer id.
     *
     * @return the manufacturerId
     */
    public String getManufacturerId() {
        return manufacturerId;
    }

    /**
     * Sets the manufacturer id.
     *
     * @param manufacturerId the manufacturerId to set
     */
    public void setManufacturerId(final String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * Gets the search flag.
     *
     * @return the searchFlag
     */
    public Boolean getSearchFlag() {
        return searchFlag;
    }

    /**
     * Sets the search flag.
     *
     * @param searchFlag the searchFlag to set
     */
    public void setSearchFlag(final Boolean searchFlag) {
        this.searchFlag = searchFlag;
    }

    /**
     * Gets the qualifier flag.
     *
     * @return the qualifierFlag
     */
    public Boolean getQualifierFlag() {
        return qualifierFlag;
    }

    /**
     * Sets the qualifier flag.
     *
     * @param qualifierFlag the qualifierFlag to set
     */
    public void setQualifierFlag(final Boolean qualifierFlag) {
        this.qualifierFlag = qualifierFlag;
    }

    /**
     * Gets the identifier flag.
     *
     * @return the identifierFlag
     */
    public Boolean getIdentifierFlag() {
        return identifierFlag;
    }

    /**
     * Sets the identifier flag.
     *
     * @param identifierFlag the identifierFlag to set
     */
    public void setIdentifierFlag(final Boolean identifierFlag) {
        this.identifierFlag = identifierFlag;
    }

    /**
     * Gets the success message.
     *
     * @return the successMessage
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Sets the success message.
     *
     * @param successMessage the successMessage to set
     */
    public void setSuccessMessage(final String successMessage) {
        this.successMessage = successMessage;
    }

    /**
     * Gets the item start date.
     *
     * @return the itemStartDate
     */
    public String getItemStartDate() {
        return itemStartDate;
    }

    /**
     * Sets the item start date.
     *
     * @param itemStartDate the itemStartDate to set
     */
    public void setItemStartDate(final String itemStartDate) {
        this.itemStartDate = itemStartDate;
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
     * Gets the item code.
     *
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the item code.
     *
     * @param itemCode the itemCode to set
     */
    public void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * Gets the package size.
     *
     * @return the packageSize
     */
    public String getPackageSize() {
        return packageSize;
    }

    /**
     * Sets the package size.
     *
     * @param packageSize the packageSize to set
     */
    public void setPackageSize(final String packageSize) {
        this.packageSize = packageSize;
    }

    /**
     * Gets the package size intro date.
     *
     * @return the packageSizeIntroDate
     */
    public String getPackageSizeIntroDate() {
        return packageSizeIntroDate;
    }

    /**
     * Sets the package size intro date.
     *
     * @param packageSizeIntroDate the packageSizeIntroDate to set
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
     * @param upps the upps to set
     */
    public void setUpps(final String upps) {
        this.upps = upps;
    }

    /**
     * Gets the item end date.
     *
     * @return the itemEndDate
     */
    public String getItemEndDate() {
        return itemEndDate;
    }

    /**
     * Sets the item end date.
     *
     * @param itemEndDate the itemEndDate to set
     */
    public void setItemEndDate(final String itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    /**
     * Gets the labeler code.
     *
     * @return the labelerCode
     */
    public String getLabelerCode() {
        return labelerCode;
    }

    /**
     * Sets the labeler code.
     *
     * @param labelerCode the labelerCode to set
     */
    public void setLabelerCode(final String labelerCode) {
        this.labelerCode = labelerCode;
    }

    /**
     * Gets the organization key.
     *
     * @return the organizationKey
     */
    public String getOrganizationKey() {
        return organizationKey;
    }

    /**
     * Sets the organization key.
     *
     * @param organizationKey the organizationKey to set
     */
    public void setOrganizationKey(final String organizationKey) {
        this.organizationKey = organizationKey;
    }

    /**
     * Gets the acquisition date.
     *
     * @return the acquisitionDate
     */
    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Sets the acquisition date.
     *
     * @param acquisitionDate the acquisitionDate to set
     */
    public void setAcquisitionDate(final String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    /**
     * Gets the authorized generic.
     *
     * @return the authorizedGeneric
     */
    public String getAuthorizedGeneric() {
        return authorizedGeneric;
    }

    /**
     * Sets the authorized generic.
     *
     * @param authorizedGeneric the authorizedGeneric to set
     */
    public void setAuthorizedGeneric(final String authorizedGeneric) {
        this.authorizedGeneric = authorizedGeneric;
    }

    /**
     * Gets the authorized generic start date.
     *
     * @return the authorizedGenericStartDate
     */
    public String getAuthorizedGenericStartDate() {
        return authorizedGenericStartDate;
    }

    /**
     * Sets the authorized generic start date.
     *
     * @param authorizedGenericStartDate the authorizedGenericStartDate to set
     */
    public void setAuthorizedGenericStartDate(final String authorizedGenericStartDate) {
        this.authorizedGenericStartDate = authorizedGenericStartDate;
    }

    /**
     * Gets the authorized generic end date.
     *
     * @return the authorizedGenericEndDate
     */
    public String getAuthorizedGenericEndDate() {
        return authorizedGenericEndDate;
    }

    /**
     * Sets the authorized generic end date.
     *
     * @param authorizedGenericEndDate the authorizedGenericEndDate to set
     */
    public void setAuthorizedGenericEndDate(final String authorizedGenericEndDate) {
        this.authorizedGenericEndDate = authorizedGenericEndDate;
    }

    /**
     * Gets the form.
     *
     * @return the form
     */
    public Integer getForm() {
        return form;
    }

    /**
     * Sets the form.
     *
     * @param form the form to set
     */
    public void setForm(final Integer form) {
        this.form = form;
    }

    /**
     * Gets the strength.
     *
     * @return the strength
     */
    public Integer getStrength() {
        return strength;
    }

    /**
     * Sets the strength.
     *
     * @param strength the strength to set
     */
    public void setStrength(final Integer strength) {
        this.strength = strength;
    }

    /**
     * Gets the therapeutic class.
     *
     * @return the therapeuticClass
     */
    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    /**
     * Sets the therapeutic class.
     *
     * @param therapeuticClass the therapeuticClass to set
     */
    public void setTherapeuticClass(final String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    /**
     * Gets the first sale date.
     *
     * @return the firstSaleDate
     */
    public String getFirstSaleDate() {
        return firstSaleDate;
    }

    /**
     * Sets the first sale date.
     *
     * @param firstSaleDate the firstSaleDate to set
     */
    public void setFirstSaleDate(final String firstSaleDate) {
        this.firstSaleDate = firstSaleDate;
    }

    /**
     * Gets the item type indication.
     *
     * @return the itemTypeIndication
     */
    public String getItemTypeIndication() {
        return itemTypeIndication;
    }

    /**
     * Sets the item type indication.
     *
     * @param itemTypeIndication the itemTypeIndication to set
     */
    public void setItemTypeIndication(final String itemTypeIndication) {
        this.itemTypeIndication = itemTypeIndication;
    }

    /**
     * Gets the item class.
     *
     * @return the itemClass
     */
    public String getItemClass() {
        return itemClass;
    }

    /**
     * Sets the item class.
     *
     * @param itemClass the itemClass to set
     */
    public void setItemClass(final String itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * Gets the market termination date.
     *
     * @return the marketTerminationDate
     */
    public String getMarketTerminationDate() {
        return marketTerminationDate;
    }

    /**
     * Sets the market termination date.
     *
     * @param marketTerminationDate the marketTerminationDate to set
     */
    public void setMarketTerminationDate(final String marketTerminationDate) {
        this.marketTerminationDate = marketTerminationDate;
    }

    /**
     * Gets the new formulation indicator.
     *
     * @return the newFormulationIndicator
     */
    public String getNewFormulationIndicator() {
        return newFormulationIndicator;
    }

    /**
     * Sets the new formulation indicator.
     *
     * @param newFormulationIndicator the newFormulationIndicator to set
     */
    public void setNewFormulationIndicator(final String newFormulationIndicator) {
        this.newFormulationIndicator = newFormulationIndicator;
    }

    /**
     * Gets the new formulation.
     *
     * @return the newFormulation
     */
    public String getNewFormulation() {
        return newFormulation;
    }

    /**
     * Sets the new formulation.
     *
     * @param newFormulation the newFormulation to set
     */
    public void setNewFormulation(final String newFormulation) {
        this.newFormulation = newFormulation;
    }

    /**
     * Gets the new formulation start date.
     *
     * @return the newFormulationStartDate
     */
    public String getNewFormulationStartDate() {
        return newFormulationStartDate;
    }

    /**
     * Sets the new formulation start date.
     *
     * @param newFormulationStartDate the newFormulationStartDate to set
     */
    public void setNewFormulationStartDate(final String newFormulationStartDate) {
        this.newFormulationStartDate = newFormulationStartDate;
    }

    /**
     * Gets the new formulation end date.
     *
     * @return the newFormulationEndDate
     */
    public String getNewFormulationEndDate() {
        return newFormulationEndDate;
    }

    /**
     * Sets the new formulation end date.
     *
     * @param newFormulationEndDate the newFormulationEndDate to set
     */
    public void setNewFormulationEndDate(final String newFormulationEndDate) {
        this.newFormulationEndDate = newFormulationEndDate;
    }

    /**
     * Gets the pediatric exclusive indicator.
     *
     * @return the pediatricExclusiveIndicator
     */
    public String getPediatricExclusiveIndicator() {
        return pediatricExclusiveIndicator;
    }

    /**
     * Sets the pediatric exclusive indicator.
     *
     * @param pediatricExclusiveIndicator the pediatricExclusiveIndicator to set
     */
    public void setPediatricExclusiveIndicator(final String pediatricExclusiveIndicator) {
        this.pediatricExclusiveIndicator = pediatricExclusiveIndicator;
    }

    /**
     * Gets the pediatric exclusive start date.
     *
     * @return the pediatricExclusiveStartDate
     */
    public String getPediatricExclusiveStartDate() {
        return pediatricExclusiveStartDate;
    }

    /**
     * Sets the pediatric exclusive start date.
     *
     * @param pediatricExclusiveStartDate the pediatricExclusiveStartDate to set
     */
    public void setPediatricExclusiveStartDate(final String pediatricExclusiveStartDate) {
        this.pediatricExclusiveStartDate = pediatricExclusiveStartDate;
    }

    /**
     * Gets the pediatric exclusive end date.
     *
     * @return the pediatricExclusiveEndDate
     */
    public String getPediatricExclusiveEndDate() {
        return pediatricExclusiveEndDate;
    }

    /**
     * Sets the pediatric exclusive end date.
     *
     * @param pediatricExclusiveEndDate the pediatricExclusiveEndDate to set
     */
    public void setPediatricExclusiveEndDate(final String pediatricExclusiveEndDate) {
        this.pediatricExclusiveEndDate = pediatricExclusiveEndDate;
    }

    /**
     * Gets the clotting factor indicator.
     *
     * @return the clottingFactorIndicator
     */
    public String getClottingFactorIndicator() {
        return clottingFactorIndicator;
    }

    /**
     * Sets the clotting factor indicator.
     *
     * @param clottingFactorIndicator the clottingFactorIndicator to set
     */
    public void setClottingFactorIndicator(final String clottingFactorIndicator) {
        this.clottingFactorIndicator = clottingFactorIndicator;
    }

    /**
     * Gets the clotting factor start date.
     *
     * @return the clottingFactorStartDate
     */
    public String getClottingFactorStartDate() {
        return clottingFactorStartDate;
    }

    /**
     * Sets the clotting factor start date.
     *
     * @param clottingFactorStartDate the clottingFactorStartDate to set
     */
    public void setClottingFactorStartDate(final String clottingFactorStartDate) {
        this.clottingFactorStartDate = clottingFactorStartDate;
    }

    /**
     * Gets the clotting factor end date.
     *
     * @return the clottingFactorEndDate
     */
    public String getClottingFactorEndDate() {
        return clottingFactorEndDate;
    }

    /**
     * Sets the clotting factor end date.
     *
     * @param clottingFactorEndDate the clottingFactorEndDate to set
     */
    public void setClottingFactorEndDate(final String clottingFactorEndDate) {
        this.clottingFactorEndDate = clottingFactorEndDate;
    }

    /**
     * Gets the primary uom.
     *
     * @return the primaryUom
     */
    public String getPrimaryUom() {
        return primaryUom;
    }

    /**
     * Sets the primary uom.
     *
     * @param primaryUom the primaryUom to set
     */
    public void setPrimaryUom(final String primaryUom) {
        this.primaryUom = primaryUom;
    }

    /**
     * Gets the secondary uom.
     *
     * @return the secondaryUom
     */
    public String getSecondaryUom() {
        return secondaryUom;
    }

    /**
     * Sets the secondary uom.
     *
     * @param secondaryUom the secondaryUom to set
     */
    public void setSecondaryUom(final String secondaryUom) {
        this.secondaryUom = secondaryUom;
    }

    /**
     * Gets the shelf life.
     *
     * @return the shelfLife
     */
    public String getShelfLife() {
        return shelfLife;
    }

    /**
     * Sets the shelf life.
     *
     * @param shelfLife the shelfLife to set
     */
    public void setShelfLife(final String shelfLife) {
        this.shelfLife = shelfLife;
    }

    /**
     * Gets the shelf life type.
     *
     * @return the shelfLifeType
     */
    public int getShelfLifeType() {
        return shelfLifeType;
    }

    /**
     * Sets the shelf life type.
     *
     * @param shelfLifeType the shelfLifeType to set
     */
    public void setShelfLifeType(final int shelfLifeType) {
        this.shelfLifeType = shelfLifeType;
    }

    /**
     * Gets the dual pricing indicator.
     *
     * @return the dualPricingIndicator
     */
    public String getDualPricingIndicator() {
        return dualPricingIndicator;
    }

    /**
     * Sets the dual pricing indicator.
     *
     * @param dualPricingIndicator the dualPricingIndicator to set
     */
    public void setDualPricingIndicator(final String dualPricingIndicator) {
        this.dualPricingIndicator = dualPricingIndicator;
    }

    /**
     * Gets the item family id.
     *
     * @return the itemFamilyId
     */
    public String getItemFamilyId() {
        return itemFamilyId;
    }

    /**
     * Sets the item family id.
     *
     * @param itemFamilyId the itemFamilyId to set
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
     * @param udc1 the udc1 to set
     */
    public void setUdc1(final String udc1) {
        this.udc1 = udc1;
    }

    /**
     * Checks if is item indicator.
     *
     * @return the itemIndicator
     */
    public boolean isItemIndicator() {
        return itemIndicator;
    }

    /**
     * Sets the item indicator.
     *
     * @param itemIndicator the itemIndicator to set
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
     * @param udc2 the udc2 to set
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
     * @param udc3 the udc3 to set
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
     * @param udc4 the udc4 to set
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
     * @param udc5 the udc5 to set
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
     * @param udc6 the udc6 to set
     */
    public void setUdc6(final String udc6) {
        this.udc6 = udc6;
    }

    /**
     * Gets the acquired amp.
     *
     * @return the acquiredAmp
     */
    public BigDecimal getAcquiredAmp() {
        return acquiredAmp;
    }

    /**
     * Sets the acquired amp.
     *
     * @param acquiredAmp the acquiredAmp to set
     */
    public void setAcquiredAmp(final BigDecimal acquiredAmp) {
        this.acquiredAmp = acquiredAmp;
    }

    /**
     * Gets the acquired bamp.
     *
     * @return the acquiredBamp
     */
    public BigDecimal getAcquiredBamp() {
        return acquiredBamp;
    }

    /**
     * Sets the acquired bamp.
     *
     * @param acquiredBamp the acquiredBamp to set
     */
    public void setAcquiredBamp(final BigDecimal acquiredBamp) {
        this.acquiredBamp = acquiredBamp;
    }

    /**
     * Gets the obra bamp.
     *
     * @return the obraBamp
     */
    public BigDecimal getObraBamp() {
        return obraBamp;
    }

    /**
     * Sets the obra bamp.
     *
     * @param obraBamp the obraBamp to set
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
     * @param dra the dra to set
     */
    public void setDra(final BigDecimal dra) {
        this.dra = dra;
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
     * Gets the created date.
     *
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
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

    /**
     * Gets the modified date.
     *
     * @return the modifiedDate
     */
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(final String modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    public int getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    /**
     * Gets the doses per unit.
     *
     * @return the dosesPerUnit
     */
    public String getDosesPerUnit() {
        return dosesPerUnit;
    }

    /**
     * Sets the doses per unit.
     *
     * @param dosesPerUnit the dosesPerUnit to set
     */
    public void setDosesPerUnit(final String dosesPerUnit) {
        this.dosesPerUnit = dosesPerUnit;
    }

    /**
     * Gets the inbound status.
     *
     * @return the inboundStatus
     */
    public Character getInboundStatus() {
        return inboundStatus;
    }

    /**
     * Sets the inbound status.
     *
     * @param inboundStatus the inboundStatus to set
     */
    public void setInboundStatus(final Character inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    /**
     * Gets the brand id.
     *
     * @return the brandId
     */
    public String getBrandId() {
        return brandId;
    }

    /**
     * Sets the brand id.
     *
     * @param brandId the brandId to set
     */
    public void setBrandId(final String brandId) {
        this.brandId = brandId;
    }

    /**
     * Gets the item irt qualifier name ddlb.
     *
     * @return the item irt qualifier name ddlb
     */
    public HelperDTO getItemIrtQualifierNameDDLB() {
        return itemIrtQualifierNameDDLB;
    }

    /**
     * Sets the item irt qualifier name ddlb.
     *
     * @param itemIrtQualifierNameDDLB the new item irt qualifier name ddlb
     */
    public void setItemIrtQualifierNameDDLB(final HelperDTO itemIrtQualifierNameDDLB) {
        this.itemIrtQualifierNameDDLB = itemIrtQualifierNameDDLB;
    }

    public Date getItemMasterStartDate() {
        return itemMasterStartDate;
    }

    public void setItemMasterStartDate(Date itemMasterStartDate) {
        this.itemMasterStartDate = itemMasterStartDate;
    }

    public Date getItemMasterEndDate() {
        return itemMasterEndDate;
    }

    public void setItemMasterEndDate(Date itemMasterEndDate) {
        this.itemMasterEndDate = itemMasterEndDate;
    }

    public Date getPediatricExcStartDate() {
        return pediatricExcStartDate;
    }

    public void setPediatricExcStartDate(Date pediatricExcStartDate) {
        this.pediatricExcStartDate = pediatricExcStartDate;
    }

    public Date getPediatricExcEndDate() {
        return pediatricExcEndDate;
    }

    public void setPediatricExcEndDate(Date pediatricExcEndDate) {
        this.pediatricExcEndDate = pediatricExcEndDate;
    }

    public Date getClottingFactStartDate() {
        return clottingFactStartDate;
    }

    public void setClottingFactStartDate(Date clottingFactStartDate) {
        this.clottingFactStartDate = clottingFactStartDate;
    }

    public Date getClottingFactEndDate() {
        return clottingFactEndDate;
    }

    public void setClottingFactEndDate(Date clottingFactEndDate) {
        this.clottingFactEndDate = clottingFactEndDate;
    }

    public HelperDTO getNdc9() {
        return ndc9;
    }

    public void setNdc9(HelperDTO ndc9) {
        this.ndc9 = ndc9;
    }

    public HelperDTO getNdc8() {
        return ndc8;
    }

    public void setNdc8(HelperDTO ndc8) {
        this.ndc8 = ndc8;
    }

    public String getDisplayBrand() {
        return displayBrand;
    }

    public void setDisplayBrand(String displayBrand) {
        this.displayBrand = displayBrand;
    }
}
