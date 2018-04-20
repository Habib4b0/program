/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentconfiguration.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Srithar.Raju
 */
public class AdjustmentConfigDTO {

    private String modeDdlb;
    private String transactionName = StringUtils.EMPTY;
    private String transactionDesc = StringUtils.EMPTY;
    private int methodologyDDLB;
    private int adjustmentConfigSid;
    private String methodology;
    private String redemptionPeriod;
    private String createdDate;
    private String createdBy;
    private String modifiedDate;
    private String modifiedBy;

    public AdjustmentConfigDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getModeDdlb() {
        return modeDdlb;
    }

    public void setModeDdlb(String modeDdlb) {
        this.modeDdlb = modeDdlb;
    }

    public int getMethodologyDDLB() {
        return methodologyDDLB;
    }

    public void setMethodologyDDLB(int methodologyDDLB) {
        this.methodologyDDLB = methodologyDDLB;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getRedemptionPeriod() {
        return redemptionPeriod;
    }

    public void setRedemptionPeriod(String redemptionPeriod) {
        this.redemptionPeriod = redemptionPeriod;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public int getAdjustmentConfigSid() {
        return adjustmentConfigSid;
    }

    public void setAdjustmentConfigSid(int adjustmentConfigSid) {
        this.adjustmentConfigSid = adjustmentConfigSid;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
