/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author santanukumar
 */
public class WorkflowMasterDTO implements Serializable {

    private static final long serialVersionUID = -1L;
    public int workflowMasterSystemId;
    public Date createdDate;
    public int createdBy;
    public int approvedBy;
    public int projectionId;
    public int modifiedBy;
    public Date modifiedDate;
    public int workflowStatus;
    public String workflowId;
    public String workflowName;
    public String workflowStatusStr;
    public int aacSid;
    public String notes;
    public int noOfApprovals;

    public int getWorkflowMasterSystemId() {
        return workflowMasterSystemId;
    }

    public void setWorkflowMasterSystemId(int workflowMasterSystemId) {
        this.workflowMasterSystemId = workflowMasterSystemId;
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

    public int getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
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

    public int getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(int workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getWorkflowStatusStr() {
        return workflowStatusStr;
    }

    public void setWorkflowStatusStr(String workflowStatusStr) {
        this.workflowStatusStr = workflowStatusStr;
    }

    public int getAacSid() {
        return aacSid;
    }

    public void setAacSid(int aacSid) {
        this.aacSid = aacSid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getNoOfApprovals() {
        return noOfApprovals;
    }

    public void setNoOfApprovals(int noOfApprovals) {
        this.noOfApprovals = noOfApprovals;
    }

}
