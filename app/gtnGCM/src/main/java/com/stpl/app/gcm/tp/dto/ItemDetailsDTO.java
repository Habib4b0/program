/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed
 */
public class ItemDetailsDTO implements Serializable {

    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String therapyClass = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private String itemStartDate = StringUtils.EMPTY;
    private String itemEndDate = StringUtils.EMPTY;
    private String rebatePlan = StringUtils.EMPTY;
    private String formulaId = StringUtils.EMPTY;

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

    public String getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(String itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    public String getItemEndDate() {
        return itemEndDate;
    }

    public void setItemEndDate(String itemEndDate) {
        this.itemEndDate = itemEndDate;
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

}
