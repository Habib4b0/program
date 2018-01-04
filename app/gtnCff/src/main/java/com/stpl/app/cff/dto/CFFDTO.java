/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import java.util.Date;

/**
 *
 * @author Manasa
 */
public class CFFDTO {
    
    private String updateCycleApprovalName="";
    private Date createdDate;
    private int createdBy;
    private String updateCycleFlag;
    private int approvalSequence;
    private Date approvedDate;
    private String approvedBy;
    private int cffMasterId;
    private String latestEstimateFlag;
    private String latestEstimateApprovalName="";
    private int modifiedBy;
    private Date modifiedDate;   
    private String projectionIds; 

    public String getUpdateCycleApprovalName() {
        return updateCycleApprovalName;
    }

    public void setUpdateCycleApprovalName(String updateCycleApprovalName) {
        this.updateCycleApprovalName = updateCycleApprovalName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdateCycleFlag() {
        return updateCycleFlag;
    }

    public void setUpdateCycleFlag(String updateCycleFlag) {
        this.updateCycleFlag = updateCycleFlag;
    }

    public int getApprovalSequence() {
        return approvalSequence;
    }

    public void setApprovalSequence(int approvalSequence) {
        this.approvalSequence = approvalSequence;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public int getCffMasterId() {
        return cffMasterId;
    }

    public void setCffMasterId(int cffMasterId) {
        this.cffMasterId = cffMasterId;
    }

    public String getLatestEstimateFlag() {
        return latestEstimateFlag;
    }

    public void setLatestEstimateFlag(String latestEstimateFlag) {
        this.latestEstimateFlag = latestEstimateFlag;
    }

    public String getLatestEstimateApprovalName() {
        return latestEstimateApprovalName;
    }

    public void setLatestEstimateApprovalName(String latestEstimateApprovalName) {
        this.latestEstimateApprovalName = latestEstimateApprovalName;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getProjectionIds() {
        return projectionIds;
    }

    public void setProjectionIds(String projectionIds) {
        this.projectionIds = projectionIds;
    }
    
    
}
