/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.dto;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class LookUpDTO implements Serializable {

    private String viewName = StringUtils.EMPTY;
    private String createdBy = StringUtils.EMPTY;
    private Date createdDate ;
    private int viewMasterSid =0;
    private String exclusionAndInclusionValue= StringUtils.EMPTY;
    private String inventCalculationValue= StringUtils.EMPTY;
    private boolean selectFlag = false;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getViewMasterSid() {
        return viewMasterSid;
    }

    public void setViewMasterSid(int viewMasterSid) {
        this.viewMasterSid = viewMasterSid;
    }

    public String getExclusionAndInclusionValue() {
        return exclusionAndInclusionValue;
    }

    public void setExclusionAndInclusionValue(String exclusionAndInclusionValue) {
        this.exclusionAndInclusionValue = exclusionAndInclusionValue;
    }

    public String getInventCalculationValue() {
        return inventCalculationValue;
    }

    public void setInventCalculationValue(String inventCalculationValue) {
        this.inventCalculationValue = inventCalculationValue;
    }

    public boolean isSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
