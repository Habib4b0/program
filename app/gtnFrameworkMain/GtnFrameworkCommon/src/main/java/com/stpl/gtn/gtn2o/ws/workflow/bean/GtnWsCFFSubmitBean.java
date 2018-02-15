/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.workflow.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author STPL
 */
public class GtnWsCFFSubmitBean implements Serializable {

    public GtnWsCFFSubmitBean() {
        super();
    }

    private int projectionId;

    private String moduleName;

    private String workflowIdGeneratorXmlPath;

    private String notes;

    private String description;

    private String workflowId;

    private int noOfApproval;

    private int approvalLevel;

    private long processId;

    private String workflowStatus = null;

    private String userType = null;

    private String variableName;

    private Map<String, Object> submitWorkflowParam;
    
    private Object value;

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getWorkflowIdGeneratorXmlPath() {
        return workflowIdGeneratorXmlPath;
    }

    public void setWorkflowIdGeneratorXmlPath(String workflowIdGeneratorXmlPath) {
        this.workflowIdGeneratorXmlPath = workflowIdGeneratorXmlPath;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public int getNoOfApproval() {
        return noOfApproval;
    }

    public void setNoOfApproval(int noOfApproval) {
        this.noOfApproval = noOfApproval;
    }

    public int getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(int approvalLevel) {
        this.approvalLevel = approvalLevel;
    }

    public long getProcessId() {
        return processId;
    }

    public void setProcessId(long processId) {
        this.processId = processId;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Map<String, Object> getSubmitWorkflowParam() {
        return submitWorkflowParam;
    }

    public void setSubmitWorkflowParam(Map<String, Object> submitWorkflowParam) {
        this.submitWorkflowParam = submitWorkflowParam;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(value);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        value = in.readObject();
    }
    }
