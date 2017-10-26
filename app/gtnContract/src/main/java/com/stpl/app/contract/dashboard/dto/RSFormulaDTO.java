/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Shrihariharan
 */
public class RSFormulaDTO {
    
   
   private int forectastingFormulaSid; 
   private String formulaNo = StringUtils.EMPTY;
   private String formulaName =StringUtils.EMPTY; 
   private String formula=StringUtils.EMPTY; 
   private String formulaType=StringUtils.EMPTY; 
   private int formulaTypeId;
   private boolean isActive;
   private Date modifiedDate;
   private Date createdDate;
   private String formulaID = StringUtils.EMPTY;
   
    public int getForectastingFormulaSid() {
        return forectastingFormulaSid;
    }

    public void setForectastingFormulaSid(int forectastingFormulaSid) {
        this.forectastingFormulaSid = forectastingFormulaSid;
    }

    public String getFormulaNo() {
        return formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(String formulaType) {
        this.formulaType = formulaType;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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

    public int getFormulaTypeId() {
        return formulaTypeId;
    }

    public void setFormulaTypeId(int formulaTypeId) {
        this.formulaTypeId = formulaTypeId;
    }

    public String getFormulaID() {
        return formulaID;
    }

    public void setFormulaID(String formulaID) {
        this.formulaID = formulaID;
    }
   
}
