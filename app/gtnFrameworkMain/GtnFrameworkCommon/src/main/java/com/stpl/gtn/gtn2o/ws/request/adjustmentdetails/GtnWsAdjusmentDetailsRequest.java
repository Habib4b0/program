/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.adjustmentdetails;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnWsAdjusmentDetailsRequest implements Serializable {

    private Integer adjustmentType;
    private Integer glCompany;
    private Integer businessUnit;
    private String workFlowId;
    private String workFlowName;
    private String customerNo;
    private String customerName;
    private String itemNo;
    private String itemName;
    private Integer deductionLevel;
    private String deductionValue;
    private Date createdDate;
    private Date glDate;
    private String originalBatchId;
    private String brandName;
    private Date redemptionPeriod;
    private Integer postingIndicator;
    private Integer accountCategory;
    private Integer accountType;
    private Integer adjustmentLevel;
    private String account;
    private Integer armAdjustmentDetailsMasterSid;
    private String deductionLevelCaption;

    public String getDeductionLevelCaption() {
        return deductionLevelCaption;
    }

    public void setDeductionLevelCaption(String deductionLevel) {
        this.deductionLevelCaption = deductionLevel;
    }

    public Integer getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(Integer adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public Integer getGlCompany() {
        return glCompany;
    }

    public void setGlCompany(Integer glCompany) {
        this.glCompany = glCompany;
    }

    public Integer getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(Integer businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getWorkFlowName() {
        return workFlowName;
    }

    public void setWorkFlowName(String workFlowName) {
        this.workFlowName = workFlowName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

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

    public Integer getDeductionLevel() {
        return deductionLevel;
    }

    public void setDeductionLevel(Integer deductionLevel) {
        this.deductionLevel = deductionLevel;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public String getOriginalBatchId() {
        return originalBatchId;
    }

    public void setOriginalBatchId(String originalBatchId) {
        this.originalBatchId = originalBatchId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Date getRedemptionPeriod() {
        return redemptionPeriod;
    }

    public void setRedemptionPeriod(Date redemptionPeriod) {
        this.redemptionPeriod = redemptionPeriod;
    }

    public Integer getPostingIndicator() {
        return postingIndicator;
    }

    public void setPostingIndicator(Integer postingIndicator) {
        this.postingIndicator = postingIndicator;
    }

    public Integer getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(Integer accountCategory) {
        this.accountCategory = accountCategory;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getAdjustmentLevel() {
        return adjustmentLevel;
    }

    public void setAdjustmentLevel(Integer adjustmentLevel) {
        this.adjustmentLevel = adjustmentLevel;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getArmAdjustmentDetailsMasterSid() {
        return armAdjustmentDetailsMasterSid;
    }

    public void setArmAdjustmentDetailsMasterSid(Integer armAdjustmentDetailsMasterSid) {
        this.armAdjustmentDetailsMasterSid = armAdjustmentDetailsMasterSid;
    }

    public String getDeductionValue() {
        return deductionValue;
    }

    public void setDeductionValue(String deductionValue) {
        this.deductionValue = deductionValue;
    }

}
