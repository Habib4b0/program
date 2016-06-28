/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.vaadin.ui.Component;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Manasa
 */
public class CFFResultsDTO {
    
    /**
     * The workflow id.
     */
    private String workflowId = StringUtils.EMPTY;

    /**
     * The workflow name.
     */
    private String workflowName= StringUtils.EMPTY;

    /**
     * The creation date.
     */
    private Date createdDate;

    /**
     * The created by.
     */
    private String createdBy = StringUtils.EMPTY;

    /**
     * The approval date.
     */
    private Date approvalDate;

    /**
     * The approved by.
     */
    private String approvedBy= StringUtils.EMPTY;
    
    /**
     * The prior latest estimate.
     */
    private String priorLatestEstimate;

    /**
     * The prior update cycle.
     */
    private String priorUpdateCycle;

    /**
     * The projection id.
     */
    private int projectionId;

    /**
     * The workflow master system id.
     */
    private int workflowMasterSystemID;
    /**
     * The forecast workflow id.
     */
    private Component forecastWorkflowId;
    
    private String projectionName=StringUtils.EMPTY;
    
    private int projectionMasterSid;
    
    private String projectionType = StringUtils.EMPTY;
    
    private int ccpDetailsSid;
    
    private String workflowStatus = StringUtils.EMPTY;

   
    public String getProjectionType() {
        return projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }
    
    
    
    /**
     * Gets CCP details Sid
     * @return ccpDetailsSid
     */
    public int getCcpDetailsSid() {
        return ccpDetailsSid;
    }
    
    /**
     * Sets the CCP details Sid
     * @param ccpDetailsSid 
     */
    public void setCcpDetailsSid(int ccpDetailsSid) {
        this.ccpDetailsSid = ccpDetailsSid;
    }
    
    /**
     * Gets Workflow status
     * @return workflowStatus
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }
    
    /**
     * Sets workflowStatus
     * @param workflowStatus 
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }
    
    /**
     * Gets the Projection Master Sid
     * @return Projection Master Sid
     */
    public int getProjectionMasterSid() {
        return projectionMasterSid;
    }
    
    /**
     * Sets the Projection master Sid
     * @param projectionMasterSid 
     */
    public void setProjectionMasterSid(int projectionMasterSid) {
        this.projectionMasterSid = projectionMasterSid;
    }
    
    /**
     * Gets the workflow master system id.
     *
     * @return the workflow master system id
     */
    public int getWorkflowMasterSystemID() {
        return workflowMasterSystemID;
    }

    /**
     * Sets the workflow master system id.
     *
     * @param workflowMasterSystemID the new workflow master system id
     */
    public void setWorkflowMasterSystemID(final int workflowMasterSystemID) {
        this.workflowMasterSystemID = workflowMasterSystemID;
    }

    /**
     * Gets the forecast workflow id.
     *
     * @return the forecast workflow id
     */
    public Component getForecastWorkflowId() {
        return forecastWorkflowId;
    }

    /**
     * Sets the forecast workflow id.
     *
     * @param forecastWorkflowId the new forecast workflow id
     */
    public void setForecastWorkflowId(final Component forecastWorkflowId) {
        this.forecastWorkflowId = forecastWorkflowId;
    }

    /**
     * Gets the workflow id.
     *
     * @return the workflow id
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * Sets the workflow id.
     *
     * @param workflowId the new workflow id
     */
    public void setWorkflowId(final String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * Gets the workflow name.
     *
     * @return the workflow name
     */
    public String getWorkflowName() {
        return workflowName;
    }

    /**
     * Sets the workflow name.
     *
     * @param workflowName the new workflow name
     */
    public void setWorkflowName(final String workflowName) {
        this.workflowName = workflowName;
    }

  

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * Gets Created Date
     * @return created Date
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    
    /**
     *  sets Created Date
     * @param createdDate 
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * Gets Approval date
     * @return the approval Date
     */
    public Date getApprovalDate() {
        return approvalDate;
    }
    
    /**
     * Sets the Approval date
     * @param approvalDate 
     */
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    

    /**
     * Gets the approved by.
     *
     * @return the approved by
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * Sets the approved by.
     *
     * @param approvedBy the new approved by
     */
    public void setApprovedBy(final String approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * Gets the prior latest estimate.
     *
     * @return the prior latest estimate
     */
    public String getPriorLatestEstimate() {
        return priorLatestEstimate;
    }

    /**
     * Sets the prior latest estimate.
     *
     * @param priorLatestEstimate the new prior latest estimate
     */
    public void setPriorLatestEstimate(final String priorLatestEstimate) {
        this.priorLatestEstimate = priorLatestEstimate;
    }

    /**
     * Gets the prior update cycle.
     *
     * @return the prior update cycle
     */
    public String getPriorUpdateCycle() {
        return priorUpdateCycle;
    }

    /**
     * Sets the prior update cycle.
     *
     * @param priorUpdateCycle the new prior update cycle
     */
    public void setPriorUpdateCycle(final String priorUpdateCycle) {
        this.priorUpdateCycle = priorUpdateCycle;
    }

    /**
     * Gets the projection id.
     *
     * @return the projection id
     */
    public int getProjectionId() {
        return projectionId;
    }

    /**
     * Sets the projection id.
     *
     * @param projectionId the new projection id
     */
    public void setProjectionId(final int projectionId) {
        this.projectionId = projectionId;
    }

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
    }

}
