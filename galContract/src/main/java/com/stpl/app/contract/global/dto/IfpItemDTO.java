package com.stpl.app.contract.global.dto;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;


public class IfpItemDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 6541383672039429455L;
    /**
     * The item system id.
     */
    public String itemSystemId;
    /**
     * The item familyplan id.
     */
    public String itemFamilyplanId;
    /**
     * The item familyplan system id.
     */
    public String itemFamilyplanSystemId;
    /**
     * The item familyplan name.
     */
    public String itemFamilyplanName;
    /**
     * The item familyplan no.
     */
    public String itemFamilyplanNo;
    /**
     * The item start date.
     */
    public String itemStartDate;
    /**
     * The item end date.
     */
    public String itemEndDate;
    /**
     * The item no.
     */
    public String itemNo;
    /**
     * The item name.
     */
    public String itemName;
    /**
     * The uom.
     */
    public String uom;
    /**
     * The package size.
     */
    public String packageSize;
    /**
     * The checkbox.
     */
    private Boolean checkbox = false;
    /**
     * The item status.
     */
    private String itemStatus;
    /**
     * The ifp start date.
     */
    private Date ifpStartDate;
    /**
     * The ifp end date.
     */
    private Date ifpEndDate;
    
    private String tempItemPriceRebateSystemId;
    
    private String form = StringUtils.EMPTY;
    
    /** The item desc. */
    private String itemDesc=StringUtils.EMPTY;
    
    /** The strength. */
    private String strength;
	
	/** Therapeutic class. */
    private String therapeuticClass =StringUtils.EMPTY;
    
    /** The brand. */
    private String brand;
	

    public String getTempItemPriceRebateSystemId() {
        return tempItemPriceRebateSystemId;
    }

    public void setTempItemPriceRebateSystemId(String tempItemPriceRebateSystemId) {
        this.tempItemPriceRebateSystemId = tempItemPriceRebateSystemId;
    }

    /**
     * The Constructor.
     *
     * @param itemSystemId the item system id,
     * @param itemFamilyplanId the item familyplan id
     * @param itemFamilyplanSystemId the item familyplan system id,
     * @param itemFamilyplanName the item familyplan name
     * @param itemFamilyplanNo the item familyplan no
     * @param itemNo the item no
     * @param itemName the item name
     * @param uom the Uom
     * @param packagesize the packagesize
     * @param itemStartDate the item start date
     * @param itemEndDate the item end date
     */
    public IfpItemDTO(final String itemSystemId, final String itemFamilyplanId, final String itemFamilyplanSystemId, final String itemFamilyplanName, final String itemFamilyplanNo,
            final String itemNo, final String itemName, final String uom, final String packagesize, final String itemStartDate, final String itemEndDate) {
        this.itemSystemId = itemSystemId;
        this.itemFamilyplanId = itemFamilyplanId;
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
        this.itemFamilyplanName = itemFamilyplanName;
        this.itemFamilyplanNo = itemFamilyplanNo;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.uom = uom;
        this.packageSize = packagesize;
        this.itemStartDate = itemStartDate;
        this.itemEndDate = itemEndDate;

    }

    /**
     * The Constructor.
     *
     * @param itemSystemId the item system id
     * @param itemFamilyplanId the item familyplan id
     * @param itemFamilyplanSystemId the item familyplan system id
     * @param itemFamilyplanName the item familyplan name
     * @param itemFamilyplanNo the item familyplan no
     * @param itemNo the item no,
     * @param itemName the item name,
     * @param uom the Uom
     * @param packagesize the packagesize,
     * @param itemStartDate the item start date
     * @param itemEndDate the item end date,
     * @param status the status
     * @param ifpStartDate the ifp start date,
     * @param ifpEndDate the ifp end date
     * @param checkBox the check box
     */
    public IfpItemDTO(final String itemSystemId, final String itemFamilyplanId, final String itemFamilyplanSystemId, final String itemFamilyplanName, final String itemFamilyplanNo,
            final String itemNo, final String itemName, final String uom, final String packagesize, final String itemStartDate, final String itemEndDate, final String status, final Date ifpStartDate,
            final Date ifpEndDate, final boolean checkBox) {
        this.itemSystemId = itemSystemId;
        this.itemFamilyplanId = itemFamilyplanId;
        this.itemFamilyplanSystemId = itemFamilyplanSystemId;
        this.itemFamilyplanName = itemFamilyplanName;
        this.itemFamilyplanNo = itemFamilyplanNo;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.uom = uom;
        this.packageSize = packagesize;
        this.itemStartDate = itemStartDate;
        this.itemEndDate = itemEndDate;
        this.itemStatus = status;
        this.ifpStartDate = ifpStartDate;
        this.ifpEndDate = ifpEndDate;
        this.checkbox = checkBox;

    }

    /**
     * The Constructor.
     */
    public IfpItemDTO() {
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
    public String getItemSystemId() {
        return itemSystemId;
    }

    /**
     * Sets the item system id.
     *
     * @param itemSystemId the item system id
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
     * Sets the item familyplan id.
     *
     * @param itemFamilyplanId the item familyplan id
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
     * @param itemFamilyplanSystemId the item familyplan system id
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
     * @param itemFamilyplanName the item familyplan name
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
     * @param itemFamilyplanNo the item familyplan no
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
     * @param itemStartDate the item start date
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
     * @param itemEndDate the item end date
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
     * Gets the uom.
     *
     * @return the uom
     */
    public String getUom() {
        return uom;
    }

    /**
     * Sets the uom.
     *
     * @param uom the uom
     */
    public void setUom(final String uom) {
        this.uom = uom;
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
     * @param itemStatus the item status
     */
    public void setItemStatus(final String itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * Gets the ifp start date.
     *
     * @return the ifp start date
     */
    public Date getIfpStartDate() {
        return ifpStartDate;
    }

    /**
     * Sets the ifp start date.
     *
     * @param ifpStartDate the ifp start date
     */
    public void setIfpStartDate(final Date ifpStartDate) {
        this.ifpStartDate = ifpStartDate;
    }

    /**
     * Gets the ifp end date.
     *
     * @return the ifp end date
     */
    public Date getIfpEndDate() {
        return ifpEndDate;
    }

    /**
     * Sets the ifp end date.
     *
     * @param ifpEndDate the ifp end date
     */
    public void setIfpEndDate(final Date ifpEndDate) {
        this.ifpEndDate = ifpEndDate;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
}
