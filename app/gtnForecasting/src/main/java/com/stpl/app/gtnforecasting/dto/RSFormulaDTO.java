/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.dto;

import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Shrihariharan
 */
public class RSFormulaDTO {
   
   private int formulaSystemID; 
   private String formulaNo = StringUtils.EMPTY;
   private String formulaName =StringUtils.EMPTY; 
   private String formula=StringUtils.EMPTY; 
   private HelperDTO formulaType=new HelperDTO(0,ConstantsUtils.SELECT_ONE); 
   private int formulaTypeId;
   private boolean isActive;
   private Date modifiedDate;
   private Date createdDate;
   private String formulaID = StringUtils.EMPTY;
   private String itemId = StringUtils.EMPTY;
   private String version = StringUtils.EMPTY;
   
   private String deductionType = StringUtils.EMPTY;
   private String deductionSubType = StringUtils.EMPTY;
   private String deductionCategory = StringUtils.EMPTY;
   private String indicator = StringUtils.EMPTY;
   private String propertyId = StringUtils.EMPTY;

    public RSFormulaDTO() {
        super();
    }
   
    public int getForectastingFormulaSid() {
        return formulaSystemID;
    }

    public void setForectastingFormulaSid(int forectastingFormulaSid) {
        this.formulaSystemID = forectastingFormulaSid;
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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getModifiedDate() {
        return modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
    }

    public Date getCreatedDate() {
        return createdDate == null ? null : (Date) createdDate.clone();
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public HelperDTO getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(HelperDTO formulaType) {
        this.formulaType = formulaType;
    }

    public int getFormulaSystemID() {
        return formulaSystemID;
    }

    public void setFormulaSystemID(int formulaSystemID) {
        this.formulaSystemID = formulaSystemID;
    }   

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getDeductionSubType() {
        return deductionSubType;
    }

    public void setDeductionSubType(String deductionSubType) {
        this.deductionSubType = deductionSubType;
    }

    public String getDeductionCategory() {
        return deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        this.deductionCategory = deductionCategory;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
    
    
}
