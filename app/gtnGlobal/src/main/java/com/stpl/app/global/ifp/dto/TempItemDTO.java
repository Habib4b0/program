/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.ifp.dto;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import org.apache.commons.lang.StringUtils;

public class TempItemDTO {
    private String itemNo=StringUtils.EMPTY;
    private String itemName=StringUtils.EMPTY;
    private String tempItemSystemId=StringUtils.EMPTY;
    private String itemStatus= StringUtils.EMPTY;
    private HelperDTO form = new HelperDTO(0,ConstantsUtils.SELECT_ONE);
    private String displayForm = StringUtils.EMPTY;
    private String packageSize =StringUtils.EMPTY;
    private String strength  =StringUtils.EMPTY;
    private Integer strengthID  =0;
    private Integer therapeuticClassId = 0;
    private String therapeuticClass = StringUtils.EMPTY;
    private String brand =StringUtils.EMPTY;
    private String itemDesc  =StringUtils.EMPTY;

    
    public Integer getStrengthID() {
        return strengthID;
    }

    public String getDisplayForm() {
        return displayForm;
    }

    public void setDisplayForm(String displayForm) {
        this.displayForm = displayForm;
    }

    public void setStrengthID(Integer strengthID) {
        this.strengthID = strengthID;
    }

    public Integer getTherapeuticClassId() {
        return therapeuticClassId;
    }

    public void setTherapeuticClassId(Integer therapeuticClassValue) {
        this.therapeuticClassId = therapeuticClassValue;
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

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public HelperDTO getForm() {
        return form;
    }

    public void setForm(HelperDTO form) {
        this.form = form;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
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

    public String getTempItemSystemId() {
        return tempItemSystemId;
    }

    public void setTempItemSystemId(String tempItemSystemId) {
        this.tempItemSystemId = tempItemSystemId;
    }
}
