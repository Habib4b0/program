/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.workflow.bean;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author STPL
 */
public class GtnWsForecastProjectionSubmitBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GtnWsForecastProjectionSubmitBean() {
        super();
    }

    private int forecastProjectionId;

    private String forecastProjectionModuleName;

    private String forecastProjectionWorkflowIdGeneratorXmlPath;

    private String forecastProjectionNotes;

    private String forecastProjectionDescription;

    private String forecastProjectionWorkflowId;

    private int forecastProjectionNoOfApproval;

    private int forecastProjectionApprovalLevel;

    private long forecastProjectionProcessId;

    private String forecastProjectionWorkflowStatus = null;

    private String forecastProjectionUserType = null;

    private String forecastProjectionVariableName;

    private Map<String, Object> forecastProjectionSubmitWorkflowParam;

    public int getProjectionId() {
        return forecastProjectionId;
    }

    public void setProjectionId(int projectionId) {
        this.forecastProjectionId = projectionId;
    }

    public String getModuleName() {
        return forecastProjectionModuleName;
    }

    public void setModuleName(String moduleName) {
        this.forecastProjectionModuleName = moduleName;
    }

    public String getWorkflowIdGeneratorXmlPath() {
        return forecastProjectionWorkflowIdGeneratorXmlPath;
    }

    public void setWorkflowIdGeneratorXmlPath(String workflowIdGeneratorXmlPath) {
        this.forecastProjectionWorkflowIdGeneratorXmlPath = workflowIdGeneratorXmlPath;
    }

    public String getNotes() {
        return forecastProjectionNotes;
    }

    public void setNotes(String notes) {
        this.forecastProjectionNotes = notes;
    }

    public String getDescription() {
        return forecastProjectionDescription;
    }

    public void setDescription(String description) {
        this.forecastProjectionDescription = description;
    }

    public String getWorkflowId() {
        return forecastProjectionWorkflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.forecastProjectionWorkflowId = workflowId;
    }

    public int getNoOfApproval() {
        return forecastProjectionNoOfApproval;
    }

    public void setNoOfApproval(int noOfApproval) {
        this.forecastProjectionNoOfApproval = noOfApproval;
    }

    public int getApprovalLevel() {
        return forecastProjectionApprovalLevel;
    }

    public void setApprovalLevel(int approvalLevel) {
        this.forecastProjectionApprovalLevel = approvalLevel;
    }

    public long getProcessId() {
        return forecastProjectionProcessId;
    }

    public void setProcessId(long processId) {
        this.forecastProjectionProcessId = processId;
    }

    public String getWorkflowStatus() {
        return forecastProjectionWorkflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.forecastProjectionWorkflowStatus = workflowStatus;
    }

    public String getUserType() {
        return forecastProjectionUserType;
    }

    public void setUserType(String userType) {
        this.forecastProjectionUserType = userType;
    }

    public String getVariableName() {
        return forecastProjectionVariableName;
    }

    public void setVariableName(String variableName) {
        this.forecastProjectionVariableName = variableName;
    }

    public Map<String, Object> getSubmitWorkflowParam() {
        return forecastProjectionSubmitWorkflowParam;
    }

    public void setSubmitWorkflowParam(Map<String, Object> submitWorkflowParam) {
        this.forecastProjectionSubmitWorkflowParam = submitWorkflowParam;
    }
    

}
