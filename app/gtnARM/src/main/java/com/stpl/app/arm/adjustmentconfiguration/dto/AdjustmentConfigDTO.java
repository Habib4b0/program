/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentconfiguration.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Srithar.Raju
 */
public class AdjustmentConfigDTO {

    String modeDdlb;
    String transactionName = StringUtils.EMPTY;
    String transactionDesc = StringUtils.EMPTY;
    int methodologyDDLB;
    int adjustmentConfigSid;
    String methodology;
    String redemptionPeriod;
    Date createdDate;
    String createdBy;
    Date modifiedDate;
    String modifiedBy;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
