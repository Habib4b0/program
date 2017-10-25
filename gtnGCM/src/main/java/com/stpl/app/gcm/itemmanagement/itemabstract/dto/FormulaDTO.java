/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed.hameed
 */
public class FormulaDTO {

    private String formulaId = StringUtils.EMPTY;
    private String formulaNo = StringUtils.EMPTY;
    private String formulaName = StringUtils.EMPTY;
    private Integer formulaSid = 0;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;

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

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public boolean isIsCount() {
        return isCount;
    }

    public void setIsCount(boolean isCount) {
        this.isCount = isCount;
    }

    public Integer getFormulaSid() {
        return formulaSid;
    }

    public void setFormulaSid(Integer formulaSid) {
        this.formulaSid = formulaSid;
    }

}
