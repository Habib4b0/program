/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.dto;

import com.stpl.app.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vinodhini
 */
public class NsfDto {
    public String formulaId= StringUtils.EMPTY;
    public String formulaNo= StringUtils.EMPTY;
    public String formulaName= StringUtils.EMPTY;
    public HelperDTO formulaCategory;   
    public String contractSelected= StringUtils.EMPTY;
    int nsRuleId;
    HelperDTO formulaType= new HelperDTO(0, Constants.SELECT_ONE);   
    public String nsfRuleName= StringUtils.EMPTY;
    boolean isSelected;
    
    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
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

    public HelperDTO getFormulaCategory() {
        return formulaCategory;
    }

    public void setFormulaCategory(HelperDTO formulaCategory) {
        this.formulaCategory = formulaCategory;
    }

    public int getNsRuleId() {
        return nsRuleId;
    }

    public void setNsRuleId(int nsRuleId) {
        this.nsRuleId = nsRuleId;
    }

    public String getContractSelected() {
        return contractSelected;
    }

    public void setContractSelected(String contractSelected) {
        this.contractSelected = contractSelected;
    }

    public HelperDTO getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(HelperDTO formulaType) {
        this.formulaType = formulaType;
    }

    public String getNsfRuleName() {
        return nsfRuleName;
    }

    public void setNsfRuleName(String nsfRuleName) {
        this.nsfRuleName = nsfRuleName;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
