/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author kasiammal.m
 */
public class IFPItemDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * The ifp details system id.
     */
    private int ifpDetailsSystemId;
    /**
     * The item system id.
     */
    private String itemSystemId;
    /**
     * The item familyplan id.
     */
    private String itemFamilyplanId = StringUtils.EMPTY;
    /**
     * The item familyplan system id.
     */
    private String itemFamilyplanSystemId;
    /**
     * The item familyplan name.
     */
    private String itemFamilyplanName = StringUtils.EMPTY;

    /**
     * The item familyplan no.
     */
    private String itemFamilyplanNo = StringUtils.EMPTY;
    private String itemFamilyplanDesignation = StringUtils.EMPTY;
    /**
     * The item start date.
     */
    private String itemStartDate = StringUtils.EMPTY;
   
    /**
     * The item end date.
     */
    private String itemEndDate = StringUtils.EMPTY;
   
    /**
     * The item no.
     */
    private String itemNo = StringUtils.EMPTY;
    /**
     * The item name.
     */
    private String itemName = StringUtils.EMPTY;
    /**
     * The item uom.
     */
    private String itemUom = StringUtils.EMPTY;
    /**
     * The primary uom.
     */
    private String primaryUom = StringUtils.EMPTY;
    /**
     * The therapeutic class.
     */
    private String therapeuticClass = StringUtils.EMPTY;
    /**
     * The brand.
     */
    private String brand = StringUtils.EMPTY;
    /**
     * The form.
     */
    private String form = StringUtils.EMPTY;
    /**
     * The strength.
     */
    private String strength = StringUtils.EMPTY;
    /**
     * The package size.
     */
    private String packageSize = StringUtils.EMPTY;
    /**
     * The unique date.
     */
    private String uniqueDate;
    /**
     * The checkbox.
     */
    private Boolean checkbox = false;
    /**
     * The item status.
     */
    private String itemStatus = StringUtils.EMPTY;

    private String itemDesc = StringUtils.EMPTY;
    /**
     * The ifp start date.
     */
    private String ifpStartDate = StringUtils.EMPTY;
    
    /**
     * The ifp end date.
     */
    private String ifpEndDate = StringUtils.EMPTY;
    
    /**
     * The item id.
     */
    private String itemId = StringUtils.EMPTY;
    /**
     * The item details list.
     */
    private List<IFPItemDTO> itemDetailsList = new ArrayList<>();
    /**
     * The items list.
     */
    private List<ItemMasterDTO> itemsList = new ArrayList<>();
    //Added by Aaral
    /**
     * The item familyplan status.
     */
    private Integer itemFamilyplanStatus = 0;
    
     private Integer IFPtype = 0;
    private String itemFamilyplanType = StringUtils.EMPTY;

    private String displayIFPStatus = StringUtils.EMPTY;
    /**
     * The item familyplan start date.
     */
    private Date itemFamilyplanStartDate;
    /**
     * The item familyplan end date.
     */
    private Date itemFamilyplanEndDate;
    /**
     * The created by.
     */
    private String createdBy = StringUtils.EMPTY;
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
     * The attached date.
     */
    private Date attachedDate;

    private Integer tempIFPDetailsSystemId;

    private boolean checkFlag = false;

    /**
     * The createdById.
     */
    private String createdById = StringUtils.EMPTY;

    public Integer getTempIFPDetailsSystemId() {
        return tempIFPDetailsSystemId;
    }

    public void setTempIFPDetailsSystemId(Integer tempIFPDetailsSystemId) {
        this.tempIFPDetailsSystemId = tempIFPDetailsSystemId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * Instantiates a new ifp item dto.
     *
     * @param itemSystemId the item system id,@param itemFamilyplanId the item
     * familyplan id
     * @param itemFamilyplanSystemId the item familyplan system id
     * @param itemFamilyplanName the item familyplan name,@param
     * itemFamilyplanNo the item familyplan no
     * @param itemId the item id,@param itemNo the item no
     * @param itemName the item name,@param itemStatus the item status
     * @param primaryUom the primary uom,@param packagesize the packagesize
     * @param itemStartDate the item start date,@param itemEndDate the item end
     * date
     * @param therapeuticClass the therapeutic class,@param brand the brand
     * @param form the form,@param strength the strength,@param
     * itemFamilyplanStatus the item familyplan status
     * @param itemFamilyplanStartDate the item familyplan start date
     * @param uniqueDate the unique date
     */
    public IFPItemDTO(final String itemSystemId, final String itemFamilyplanId,
            final String itemFamilyplanSystemId, final String itemFamilyplanName,
            final String itemFamilyplanNo, final String itemId, final String itemNo, final String itemName, final String itemStatus,
            final String primaryUom, final String packagesize, final String itemStartDate,
            final String itemEndDate, final String therapeuticClass, final String brand, final String form, final String strength,
            final Integer itemFamilyplanStatus, final Date itemFamilyplanStartDate, final String uniqueDate) {
        this.itemSystemId = itemSystemId;
        this.itemFamilyplanId = itemFamilyplanId;
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
        this.itemFamilyplanName = itemFamilyplanName;
        this.itemFamilyplanNo = itemFamilyplanNo;
        this.itemId = itemId;
        this.itemFamilyplanStatus = itemFamilyplanStatus;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.itemStatus = itemStatus;
        this.primaryUom = primaryUom;
        this.packageSize = packagesize;
        this.itemStartDate = itemStartDate;
        this.itemEndDate = itemEndDate;
        this.therapeuticClass = therapeuticClass;
        this.brand = brand;
        this.form = form;
        this.strength = strength;

        this.itemFamilyplanStartDate = itemFamilyplanStartDate == null ? null : (Date) itemFamilyplanStartDate.clone();
        this.uniqueDate = uniqueDate;

    }

    /**
     * Instantiates a new ifp item dto.
     *
     * @param itemSystemId the item system id
     * @param itemFamilyplanId the item familyplan id
     * @param itemFamilyplanSystemId the item familyplan system id
     * @param itemFamilyplanName the item familyplan name
     * @param itemFamilyplanNo the item familyplan no
     * @param itemId the item id
     * @param itemNo the item no
     * @param itemName the item name
     * @param primaryUom primary uom, @param packagesize packagesize
     * @param itemStartDate item start date, @param itemEndDate item end date
     */
    public IFPItemDTO(final String itemSystemId, final String itemFamilyplanId,
            final String itemFamilyplanSystemId, final String itemFamilyplanName,
            final String itemFamilyplanNo, final String itemId, final String itemNo, final String itemName,
            final String primaryUom, final String packagesize, final String itemStartDate,
            final String itemEndDate) {
        this.itemSystemId = itemSystemId;
        this.itemFamilyplanId = itemFamilyplanId;
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
        this.itemFamilyplanName = itemFamilyplanName;
        this.itemFamilyplanNo = itemFamilyplanNo;

        this.itemId = itemId;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.primaryUom = primaryUom;
        this.packageSize = packagesize;
        this.itemStartDate = itemStartDate;
        this.itemEndDate = itemEndDate;

    }

    /**
     * Instantiates a new ifp item dto.
     *
     * @param ifpDetailsSystemId the ifp details system id,@param itemSystemId
     * the system id
     * @param itemFamilyplanId -item familyplan id,@param itemFamilyplanSystemId
     * the system id
     * @param itemFamilyplanName the item familyplan name,@param
     * itemFamilyplanNo the item familyplan no
     * @param itemId the item id,@param itemNo the item no,@param itemName the
     * item name
     * @param itemStatus the item status,@param primaryUom the primary
     * uom,@param packagesize the packagesize
     * @param itemStartDate the item start date,@param itemEndDate the item end
     * date
     * @param itemFamilyplanStatus the item familyplan status,@param
     * itemFamilyplanStartDate the item familyplan start date
     * @param itemFamilyplanEndDate the item familyplan end date,@param
     * therapeuticClass the therapeutic class
     * @param brand the brand,@param form the form,@param strength the
     * strength,@param attachedDate the attached date
     * @param checkBox the check box,@param createdDate the created date,@param
     * createdBy the created by
     * @param uniqueDate the unique date
     */
    public IFPItemDTO(final int ifpDetailsSystemId, final String itemSystemId, final String itemFamilyplanId,
            final String itemFamilyplanSystemId, final String itemFamilyplanName,
            final String itemFamilyplanNo, final String itemId, final String itemNo, final String itemName, final String itemStatus,
            final String primaryUom, final String packagesize, final String itemStartDate,
            final String itemEndDate, final Integer itemFamilyplanStatus, final Date itemFamilyplanStartDate,
            final Date itemFamilyplanEndDate, final String therapeuticClass, final String brand,
            final String form, final String strength, final Date attachedDate, final boolean checkBox, final Date createdDate, final String createdBy,
            final String uniqueDate) {
        this.ifpDetailsSystemId = ifpDetailsSystemId;
        this.itemSystemId = itemSystemId;
        this.itemFamilyplanId = itemFamilyplanId;
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
        this.itemFamilyplanName = itemFamilyplanName;
        this.itemFamilyplanNo = itemFamilyplanNo;
        this.itemId = itemId;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.itemStatus = itemStatus;
        this.primaryUom = primaryUom;
        this.packageSize = packagesize;
        this.itemStartDate = itemStartDate;
        this.itemEndDate = itemEndDate;
        this.itemFamilyplanStatus = itemFamilyplanStatus;
        this.itemFamilyplanStartDate = itemFamilyplanStartDate == null ? null : (Date) itemFamilyplanStartDate.clone();
        this.itemFamilyplanEndDate = itemFamilyplanEndDate  == null ? null : (Date) itemFamilyplanEndDate.clone();
        this.therapeuticClass = therapeuticClass;
        this.brand = brand;
        this.form = form;
        this.strength = strength;
        this.attachedDate = attachedDate == null ? null : (Date) attachedDate.clone();
        this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
        this.checkbox = checkBox;
        this.uniqueDate = uniqueDate;
        this.createdBy = createdBy;

    }

    /**
     * Gets the ifp details system id.
     *
     * @return the ifp details system id
     */
    public int getIfpDetailsSystemId() {
        return ifpDetailsSystemId;
    }

    /**
     * Sets the ifp details system id.
     *
     * @param ifpDetailsSystemId the new ifp details system id
     */
    public void setIfpDetailsSystemId(final int ifpDetailsSystemId) {
        this.ifpDetailsSystemId = ifpDetailsSystemId;
    }

    /**
     * Gets the unique date.
     *
     * @return the unique date
     */
    public String getUniqueDate() {
        return uniqueDate;
    }

    /**
     * Sets the unique date.
     *
     * @param uniqueDate the new unique date
     */
    public void setUniqueDate(final String uniqueDate) {
        this.uniqueDate = uniqueDate;
    }

    /**
     * Instantiates a new ifp item dto.
     */
    public IFPItemDTO() {
        // TODO Auto-generated constructor stub
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
     * @param checkbox the new checkbox
     */
    public void setCheckbox(final Boolean checkbox) {
        this.checkbox = checkbox;
    }
    /**
     * Gets the item system id.
     *
     * @return the item system id
     */
    public String getItemSystemId() {
        return itemSystemId;
    }

    /**
     * Sets the item system id.
     *
     * @param itemSystemId the new item system id
     */
    public void setItemSystemId(final String itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    /**
     * Gets the item familyplan id.
     *
     * @return the item familyplan id
     */
    public String getItemFamilyplanId() {
        return itemFamilyplanId;
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
     * @param primaryUom the new primary uom
     */
    public void setPrimaryUom(final String primaryUom) {
        this.primaryUom = primaryUom;
    }

    /**
     * Sets the item familyplan id.
     *
     * @param itemFamilyplanId the new item familyplan id
     */
    public void setItemFamilyplanId(final String itemFamilyplanId) {
        this.itemFamilyplanId = itemFamilyplanId;
    }

    /**
     * Gets the item familyplan system id.
     *
     * @return the item familyplan system id
     */
    public String getItemFamilyplanSystemId() {
        return itemFamilyplanSystemId;
    }

    /**
     * Sets the item familyplan system id.
     *
     * @param itemFamilyplanSystemId the new item familyplan system id
     */
    public void setItemFamilyplanSystemId(final String itemFamilyplanSystemId) {
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
    }

    /**
     * Gets the item familyplan name.
     *
     * @return the item familyplan name
     */
    public String getItemFamilyplanName() {
        return itemFamilyplanName;
    }

    /**
     * Sets the item familyplan name.
     *
     * @param itemFamilyplanName the new item familyplan name
     */
    public void setItemFamilyplanName(final String itemFamilyplanName) {
        this.itemFamilyplanName = itemFamilyplanName;
    }

    /**
     * Gets the item familyplan no.
     *
     * @return the item familyplan no
     */
    public String getItemFamilyplanNo() {
        return itemFamilyplanNo;
    }

    /**
     * Sets the item familyplan no.
     *
     * @param itemFamilyplanNo the new item familyplan no
     */
    public void setItemFamilyplanNo(final String itemFamilyplanNo) {
        this.itemFamilyplanNo = itemFamilyplanNo;
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
     * @param itemStartDate the new item start date
     */
    public void setItemStartDate(final String itemStartDate) {
        this.itemStartDate = itemStartDate;
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
     * @param itemEndDate the new item end date
     */
    public void setItemEndDate(final String itemEndDate) {
        this.itemEndDate = itemEndDate;
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
     * @param itemNo the new item no
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
     * @param itemName the new item name
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
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
     * @param itemUom the new item uom
     */
    public void setItemUom(final String itemUom) {
        this.itemUom = itemUom;
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
     * @param packageSize the new package size
     */
    public void setPackageSize(final String packageSize) {
        this.packageSize = packageSize;
    }

    /**
     * Gets the item status.
     *
     * @return the item status
     */
    public String getItemStatus() {
        return itemStatus;
    }

    /**
     * Sets the item status.
     *
     * @param itemStatus the new item status
     */
    public void setItemStatus(final String itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * Gets the ifp start date.
     *
     * @return the ifp start date
     */
    public String getIfpStartDate() {
        return ifpStartDate;
    }

    /**
     * Sets the ifp start date.
     *
     * @param ifpStartDate the new ifp start date
     */
    public void setIfpStartDate(final String ifpStartDate) {
        this.ifpStartDate = ifpStartDate;
    }

    /**
     * Gets the ifp end date.
     *
     * @return the ifp end date
     */
    public String getIfpEndDate() {
        return ifpEndDate;
    }

    /**
     * Sets the ifp end date.
     *
     * @param ifpEndDate the new ifp end date
     */
    public void setIfpEndDate(final String ifpEndDate) {
        this.ifpEndDate = ifpEndDate;
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
     * @param therapeuticClass the new therapeutic class
     */
    public void setTherapeuticClass(final String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
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
     * @param brand the new brand
     */
    public void setBrand(final String brand) {
        this.brand = brand;
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
     * @param form the new form
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
     * @param strength the new strength
     */
    public void setStrength(final String strength) {
        this.strength = strength;
    }

    /**
     * Gets the item familyplan status.
     *
     * @return the item familyplan status
     */
    public Integer getItemFamilyplanStatus() {
        return itemFamilyplanStatus;
    }

    /**
     * Sets the item familyplan status.
     *
     * @param itemFamilyplanStatus the new item familyplan status
     */
    public void setItemFamilyplanStatus(final Integer itemFamilyplanStatus) {
        this.itemFamilyplanStatus = itemFamilyplanStatus;
    }

    /**
     * Gets the item familyplan start date.
     *
     * @return the item familyplan start date
     */
    public Date getItemFamilyplanStartDate() {
        return itemFamilyplanStartDate == null ? null : (Date) itemFamilyplanStartDate.clone();
    }

    /**
     * Sets the item familyplan start date.
     *
     * @param itemFamilyplanStartDate the new item familyplan start date
     */
    public void setItemFamilyplanStartDate(final Date itemFamilyplanStartDate) {
        this.itemFamilyplanStartDate = itemFamilyplanStartDate == null ? null : (Date) itemFamilyplanStartDate.clone();
    }

    /**
     * Gets the item familyplan end date.
     *
     * @return the item familyplan end date
     */
    public Date getItemFamilyplanEndDate() {
        return itemFamilyplanEndDate == null ? null : (Date) itemFamilyplanEndDate.clone();
    }

    /**
     * Sets the item familyplan end date.
     *
     * @param itemFamilyplanEndDate the new item familyplan end date
     */
    public void setItemFamilyplanEndDate(final Date itemFamilyplanEndDate) {
        this.itemFamilyplanEndDate = itemFamilyplanEndDate == null ? null : (Date) itemFamilyplanEndDate.clone();
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
     * @param createdBy the new created by
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
     * @param createdDate the new created date
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
     * @param modifiedBy the new modified by
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
     * @param modifiedDate the new modified date
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    /**
     * Gets the attached date.
     *
     * @return the attached date
     */
    public Date getAttachedDate() {
        return attachedDate == null ? null : (Date) attachedDate.clone();
    }

    /**
     * Sets the attached date.
     *
     * @param attachedDate the new attached date
     */
    public void setAttachedDate(final Date attachedDate) {
        this.attachedDate = attachedDate == null ? null : (Date) attachedDate.clone();
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
     * @param itemId the new item id
     */
    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the item details list.
     *
     * @return the item details list
     */
    public List<IFPItemDTO> getItemDetailsList() {
        return itemDetailsList == null ? itemDetailsList : Collections.unmodifiableList(itemDetailsList);
    }

    /**
     * Sets the item details list.
     *
     * @param itemDetailsList the new item details list
     */
    public void setItemDetailsList(final List<IFPItemDTO> itemDetailsList) {
        this.itemDetailsList = itemDetailsList == null ? itemDetailsList : Collections.unmodifiableList(itemDetailsList);
    }

    /**
     * Gets the items list.
     *
     * @return the items list
     */
    public List<ItemMasterDTO> getItemsList() {
        return itemsList == null ? itemsList : Collections.unmodifiableList(itemsList);
    }

    /**
     * Sets the items list.
     *
     * @param itemsList the new items list
     */
    public void setItemsList(final List<ItemMasterDTO> itemsList) {
        this.itemsList = itemsList == null ? itemsList : Collections.unmodifiableList(itemsList);
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getDisplayIFPStatus() {
        return displayIFPStatus;
    }

    public void setDisplayIFPStatus(String displayIFPStatus) {
        this.displayIFPStatus = displayIFPStatus;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getItemFamilyplanType() {
        return itemFamilyplanType;
    }

    public void setItemFamilyplanType(String itemFamilyplanType) {
        this.itemFamilyplanType = itemFamilyplanType;
    }

    public String getItemFamilyplanDesignation() {
        return itemFamilyplanDesignation;
    }

    public void setItemFamilyplanDesignation(String itemFamilyplanDesignation) {
        this.itemFamilyplanDesignation = itemFamilyplanDesignation;
    }

    public Integer getIFPtype() {
        return IFPtype;
    }

    public void setIFPtype(Integer IFPtype) {
        this.IFPtype = IFPtype;
    }

}
