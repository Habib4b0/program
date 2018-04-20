/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.workflow.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author asha
 */
public class WorkflowMasterDTO implements Serializable {

    private static final long serialVersionUID = -1L;
    private int workflowMasterSystemId;
    private Date createdDate;
    private int createdBy;
    private int approvedBy;
    private int projectionId;
    private int modifiedBy;
    private Date modifiedDate;
    private int workflowStatus;
    private String workflowId;
    private String workflowName;
    private String workflowStatusStr;
    private String notes;
    private int noOfApprovals;
    private int approvalLevel;
    private String docDetailsSid;
    private String fileName;
    private String workflowDescription;
    private Date approvedDate;

    public WorkflowMasterDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDocDetailsSid() {
        return docDetailsSid;
    }

    public void setDocDetailsSid(String docDetailsSid) {
        this.docDetailsSid = docDetailsSid;
    }

    /**
     * @return the workflowMasterSystemId
     */
    public int getWorkflowMasterSystemId() {
        return workflowMasterSystemId;
    }

    /**
     * @param workflowMasterSystemId the workflowMasterSystemId to set
     */
    public void setWorkflowMasterSystemId(int workflowMasterSystemId) {
        this.workflowMasterSystemId = workflowMasterSystemId;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return new Date(createdDate.getTime());
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = new Date(createdDate.getTime());
    }

    /**
     * @return the createdBy
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the approvedBy
     */
    public int getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * @return the projectionId
     */
    public int getProjectionId() {
        return projectionId;
    }

    /**
     * @param projectionId the projectionId to set
     */
    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    /**
     * @return the modifiedBy
     */
    public int getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return new Date(modifiedDate.getTime());
    }

    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = new Date(modifiedDate.getTime());
    }

    /**
     * @return the workflowStatus
     */
    public int getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * @param workflowStatus the workflowStatus to set
     */
    public void setWorkflowStatus(int workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    /**
     * @return the workflowId
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * @param workflowId the workflowId to set
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * @return the workflowName
     */
    public String getWorkflowName() {
        return workflowName;
    }

    /**
     * @param workflowName the workflowName to set
     */
    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    /**
     * @return the workflowStatusStr
     */
    public String getWorkflowStatusStr() {
        return workflowStatusStr;
    }

    /**
     * @param workflowStatusStr the workflowStatusStr to set
     */
    public void setWorkflowStatusStr(String workflowStatusStr) {
        this.workflowStatusStr = workflowStatusStr;
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

    public int getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(int approvalLevel) {
        this.approvalLevel = approvalLevel;
    }

    public String getWorkflowDescription() {
        return workflowDescription;
    }

    public void setWorkflowDescription(String workflowDescription) {
        this.workflowDescription = workflowDescription;
    }

    public Date getApprovedDate() {
        return new Date(approvedDate.getTime());
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = new Date(approvedDate.getTime());
    }

}
