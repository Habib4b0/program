/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Harlin
 */
public class ComponentDetailsDTO {

    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String therapyClass = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private Date startDate;
    private Date endDate;
    private String priceType = StringUtils.EMPTY;
    private Date priceProtrctionStartDate;
    private String rebatePlan = StringUtils.EMPTY;
    private String formulaId = StringUtils.EMPTY;
    private Date attachedDate;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getRebatePlan() {
        return rebatePlan;
    }

    public void setRebatePlan(String rebatePlan) {
        this.rebatePlan = rebatePlan;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPriceProtrctionStartDate() {
        return priceProtrctionStartDate;
    }

    public void setPriceProtrctionStartDate(Date priceProtrctionStartDate) {
        this.priceProtrctionStartDate = priceProtrctionStartDate;
    }

    public Date getAttachedDate() {
        return attachedDate;
    }

    public void setAttachedDate(Date attachedDate) {
        this.attachedDate = attachedDate;
    }

}
