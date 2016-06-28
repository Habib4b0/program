/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sathyaseelan.v
 */
public class NepFormulaLookUpDTO {
    
    private int nepFormulaSystemID;
    private String nepFormulaNo = StringUtils.EMPTY;
    private String nepFormulaName = StringUtils.EMPTY;
    private String nepFormula = StringUtils.EMPTY;
    private HelperDTO nepFormulaType = new HelperDTO(ConstantsUtils.SELECT_ONE);
    private String nepFormulaTypeDesc;
    private boolean isActive;
    private Date modifiedDate;
    private Date createdDate;
    private String nepFormulaID = StringUtils.EMPTY;
    private String itemId = StringUtils.EMPTY;
    private String modifiedBy;
    private String createdBy;

    public HelperDTO getNepFormulaType() {
        return nepFormulaType;
    }

    public void setNepFormulaType(HelperDTO nepFormulaType) {
        this.nepFormulaType = nepFormulaType;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getNepFormulaSystemID() {
        return nepFormulaSystemID;
    }

    public void setNepFormulaSystemID(int nepFormulaSystemID) {
        this.nepFormulaSystemID = nepFormulaSystemID;
    }

    public String getNepFormulaNo() {
        return nepFormulaNo;
    }

    public void setNepFormulaNo(String nepFormulaNo) {
        this.nepFormulaNo = nepFormulaNo;
    }

    public String getNepFormulaName() {
        return nepFormulaName;
    }

    public void setNepFormulaName(String nepFormulaName) {
        this.nepFormulaName = nepFormulaName;
    }

    public String getNepFormula() {
        return nepFormula;
    }

    public void setNepFormula(String nepFormula) {
        this.nepFormula = nepFormula;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getNepFormulaID() {
        return nepFormulaID;
    }

    public void setNepFormulaID(String nepFormulaID) {
        this.nepFormulaID = nepFormulaID;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getNepFormulaTypeDesc() {
        return nepFormulaTypeDesc;
    }

    public void setNepFormulaTypeDesc(String nepFormulaTypeDesc) {
        this.nepFormulaTypeDesc = nepFormulaTypeDesc;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
