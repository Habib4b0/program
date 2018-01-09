package com.stpl.app.global.abstractsearch.dto;

import com.stpl.app.util.ConstantsUtils;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.HelperUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class SearchCriteriaDTO.
 */
public class SearchCriteriaDTO implements Serializable {
  
    private static final Logger LOGGER = Logger.getLogger(SearchCriteriaDTO.class); 

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 3584109928269363711L;
    

    public SearchCriteriaDTO() {
        LOGGER.debug("Inside SearchCriteriaDTO: Overidden method");
        
    }

    /**
     * The item no.
     */
    @NotBlank(message = "The item No must not be empty")
    private String text3 = HelperUtils.EMPTY;

    /**
     * The item id.
     */
    private String text2 = HelperUtils.EMPTY;

    /**
     * The item name.
     */
    private String text4 = HelperUtils.EMPTY;

    /**
     * The item status.
     */
    private HelperDTO combo1 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * The item start date.
     */
    private Date itemStartDate;

    /**
     * The item end date.
     */
    private Date itemEndDate;

    /**
     * The item qualifier.
     */
    private HelperDTO itemQualifier = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * The item identifier.
     */
    private String text7 = HelperUtils.EMPTY;

    /**
     * The system id.
     */
    private String text1 = HelperUtils.EMPTY;

    /**
     * The error messages.
     */
    private String errorMessages = HelperUtils.EMPTY;

    /**
     * The manufacturer no.
     */
    private String manufacturerNo = HelperUtils.EMPTY;

    /**
     * The brand.
     */
    private String brand;

    /**
     * The item type.
     */
    private HelperDTO combo2 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * The item desc.
     */
    private String text5 = HelperUtils.EMPTY;

    /**
     * The identifier.
     */
    private String identifier = HelperUtils.EMPTY;

    /**
     * The item type desc.
     */
    private String itemTypeDesc = HelperUtils.EMPTY;

    /**
     * The item status desc.
     */
    private String itemStatusDesc = HelperUtils.EMPTY;

    /**
     * The item irt qualifier name.
     */
    private HelperDTO itemIrtQualifierName;

    /**
     * The qualifier id.
     */
    private String qualifierId = HelperUtils.EMPTY;

	/** The manufacturer id. */
	private String manufacturerId = HelperUtils.EMPTY;

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
     * The record lock status.
     */
    private String recordLockStatus;

    /**
     * The item irt qualifier name.
     */
    private HelperDTO combo5 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * The brand.
     */
    private HelperDTO combo6 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * Therapeutic class.
     */
    private String text6 = HelperUtils.EMPTY;
   
    private String text8 = HelperUtils.EMPTY;
    
    private String text9 = HelperUtils.EMPTY;
    
    /**
     * The form.
     */
    private HelperDTO combo4 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * The strength.
     */
    private HelperDTO combo8 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * The ndc9.
     */
    private HelperDTO combo3 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    /**
     * The ndc8.
     */
    private HelperDTO combo7 = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private String itemBatchId = StringUtils.EMPTY;

    /**
     * Gets the item start date.
     *
     * @return the item start date
     */
    public Date getItemStartDate() {
        return itemStartDate;
    }

    /**
     * Sets the item start date.
     *
     * @param itemStartDate the item start date
     */
    public void setItemStartDate(final Date itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    /**
     * Gets the item end date.
     *
     * @return the item end date
     */
    public Date getItemEndDate() {
        return itemEndDate;
    }

    /**
     * Sets the item end date.
     *
     * @param itemEndDate the item end date
     */
    public void setItemEndDate(final Date itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    /**
     * Gets the item qualifier.
     *
     * @return the item qualifier
     */
    public HelperDTO getItemQualifier() {
        return itemQualifier;
    }

    /**
     * Sets the item qualifier.
     *
     * @param itemQualifier the item qualifier
     */
    public void setItemQualifier(final HelperDTO itemQualifier) {
        this.itemQualifier = itemQualifier;
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
     * Gets the item irt qualifier name.
     *
     * @return the item irt qualifier name
     */
    public HelperDTO getItemIrtQualifierName() {
        return itemIrtQualifierName;
    }

    /**
     * Sets the item irt qualifier name.
     *
     * @param itemIrtQualifierName the item irt qualifier name
     */
    public void setItemIrtQualifierName(final HelperDTO itemIrtQualifierName) {
        this.itemIrtQualifierName = itemIrtQualifierName;
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

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    public String getText7() {
        return text7;
    }

    public void setText7(String text7) {
        this.text7 = text7;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText5() {
        return text5;
    }

    public void setText5(String text5) {
        this.text5 = text5;
    }

    public HelperDTO getCombo5() {
        return combo5;
    }

    public void setCombo5(HelperDTO combo5) {
        this.combo5 = combo5;
    }

    public HelperDTO getCombo6() {
        return combo6;
    }

    public void setCombo6(HelperDTO combo6) {
        this.combo6 = combo6;
    }

    public String getText6() {
        return text6;
    }

    public void setText6(String text6) {
        this.text6 = text6;
    }

    public HelperDTO getCombo3() {
        return combo3;
    }

    public void setCombo3(HelperDTO combo3) {
        this.combo3 = combo3;
    }

    public HelperDTO getCombo7() {
        return combo7;
    }

    public void setCombo7(HelperDTO combo7) {
        this.combo7 = combo7;
    }

    public HelperDTO getCombo1() {
        return combo1;
    }

    public void setCombo1(HelperDTO combo1) {
        this.combo1 = combo1;
    }

    public HelperDTO getCombo2() {
        return combo2;
    }

    public void setCombo2(HelperDTO combo2) {
        this.combo2 = combo2;
    }

    public HelperDTO getCombo4() {
        return combo4;
    }

    public void setCombo4(HelperDTO combo4) {
        this.combo4 = combo4;
    }

    public HelperDTO getCombo8() {
        return combo8;
    }

    public void setCombo8(HelperDTO combo8) {
        this.combo8 = combo8;
    }

    public String getText9() {
        return text9;
    }

    public void setText9(String text9) {
        this.text9 = text9;
    }
     public String getText8() {
        return text8;
    }

    public void setText8(String text8) {
        this.text8 = text8;
    }

    public String getItemBatchId() {
        return itemBatchId;
    }

    public void setItemBatchId(String itemBatchId) {
        this.itemBatchId = itemBatchId;
    }
    
}
