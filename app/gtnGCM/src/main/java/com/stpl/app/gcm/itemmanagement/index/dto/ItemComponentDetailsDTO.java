/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.dto;

import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 */
public class ItemComponentDetailsDTO implements Cloneable {

    private Boolean checkRecord;
    private String id = StringUtils.EMPTY;
    private String number = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String therapyClass = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String form = StringUtils.EMPTY;
    private String strength = StringUtils.EMPTY;
    private HelperDTO itemStatus;
    private Date itemStartDate;
    private Date itemEndDate;
    private String itemMasterSid = StringUtils.EMPTY;
    private String UOM = StringUtils.EMPTY;
    private String packageSize = StringUtils.EMPTY;
    private String price = StringUtils.EMPTY;
    private HelperDTO priceType;
    private String contractPrice = StringUtils.EMPTY;
    private Date cpStartDate;
    private Date cpEndDate;
    private String priceTolerance = StringUtils.EMPTY;
    private Date priceProtectionStartDate;
    private Date priceProtectionEndDate;
    private HelperDTO priceToleranceType;
    private HelperDTO priceToleranceInterval;
    private HelperDTO priceToleranceFrequency;
    private String basePrice = StringUtils.EMPTY;
    private Date revisionDate;
    private HelperDTO attachedStatus;
    private Date attachedDate;
    private String columnName = StringUtils.EMPTY;
    private Integer caseNo = 1;

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTherapyClass() {
        return therapyClass;
    }

    public void setTherapyClass(String therapyClass) {
        this.therapyClass = therapyClass;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public HelperDTO getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(HelperDTO itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Date getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(Date itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    public Date getItemEndDate() {
        return itemEndDate;
    }

    public void setItemEndDate(Date itemEndDate) {
        this.itemEndDate = itemEndDate;
    }

    public String getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(String itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public HelperDTO getPriceType() {
        return priceType;
    }

    public void setPriceType(HelperDTO priceType) {
        this.priceType = priceType;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }

    public Date getCpStartDate() {
        return cpStartDate;
    }

    public void setCpStartDate(Date cpStartDate) {
        this.cpStartDate = cpStartDate;
    }

    public Date getCpEndDate() {
        return cpEndDate;
    }

    public void setCpEndDate(Date cpEndDate) {
        this.cpEndDate = cpEndDate;
    }

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    public Date getPriceProtectionStartDate() {
        return priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate;
    }

    public Date getPriceProtectionEndDate() {
        return priceProtectionEndDate;
    }

    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate;
    }

    public HelperDTO getPriceToleranceType() {
        return priceToleranceType;
    }

    public void setPriceToleranceType(HelperDTO priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    public HelperDTO getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    public void setPriceToleranceInterval(HelperDTO priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public HelperDTO getAttachedStatus() {
        return attachedStatus;
    }

    public void setAttachedStatus(HelperDTO attachedStatus) {
        this.attachedStatus = attachedStatus;
    }

    public Date getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate;
    }

    public HelperDTO getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(HelperDTO priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(Integer caseNo) {
        this.caseNo = caseNo;
    }

    @Override
    public ItemComponentDetailsDTO clone() throws CloneNotSupportedException {
        ItemComponentDetailsDTO selection = new ItemComponentDetailsDTO();
        selection.setCheckRecord(checkRecord);
        selection.setId(id);
        selection.setNumber(number);
        selection.setItemName(itemName);
        selection.setTherapyClass(therapyClass);
        selection.setBrand(brand);
        selection.setForm(form);
        selection.setStrength(strength);
        selection.setItemStatus(itemStatus);
        selection.setItemStartDate(itemStartDate);
        selection.setItemEndDate(itemEndDate);
        selection.setItemMasterSid(itemMasterSid);
        selection.setUOM(UOM);
        selection.setPackageSize(packageSize);
        selection.setPrice(price);
        selection.setPriceType(priceType);
        selection.setContractPrice(contractPrice);
        selection.setCpStartDate(cpStartDate);
        selection.setCpEndDate(cpEndDate);
        selection.setPriceTolerance(priceTolerance);
        selection.setPriceProtectionStartDate(priceProtectionStartDate);
        selection.setPriceProtectionEndDate(priceProtectionEndDate);
        selection.setPriceToleranceType(priceToleranceType);
        selection.setPriceToleranceInterval(priceToleranceInterval);
        selection.setPriceToleranceFrequency(priceToleranceFrequency);
        selection.setBasePrice(basePrice);
        selection.setRevisionDate(revisionDate);
        selection.setAttachedDate(attachedDate);
        selection.setAttachedStatus(attachedStatus);
        selection.setColumnName(columnName);
        selection.setCaseNo(caseNo);
        return selection;
    }
}
