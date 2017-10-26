/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.common.dto;

import com.stpl.app.model.WorkflowMaster;

/**
 *
 * @author shyam.d
 */
public class SessionDTO {

    private int systemId;
    private int contractSystemId;
    private int cfpSystemId;
    private int ifpSystemId;
    private int psSystemId;
    private int rsSystemId;
    private String edit;
    private String companyId;
    private String companyNo;
    private String companyName;
    private String parentSysId;
    private String mode;
    private String parentLookUpId;
    private String uiSessionId;
    private String sessionDate;
    private String isSave;
    private String userId;
    private long processIntanceId;
    private WorkflowMaster workflow;
    private String userType;
    private Boolean hasPermission;
    private String workflowStatus;
    

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public int getCfpSystemId() {
        return cfpSystemId;
    }

    public void setCfpSystemId(int cfpSystemId) {
        this.cfpSystemId = cfpSystemId;
    }

    public int getIfpSystemId() {
        return ifpSystemId;
    }

    public void setIfpSystemId(int ifpSystemId) {
        this.ifpSystemId = ifpSystemId;
    }

    public int getContractSystemId() {
        return contractSystemId;
    }

    public void setContractSystemId(int contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    public int getPsSystemId() {
        return psSystemId;
    }

    public void setPsSystemId(int psSystemId) {
        this.psSystemId = psSystemId;
    }

    public int getRsSystemId() {
        return rsSystemId;
    }

    public void setRsSystemId(int rsSystemId) {
        this.rsSystemId = rsSystemId;
    }

    public String getIsSave() {
        return isSave;
    }

    public void setIsSave(String isSave) {
        this.isSave = isSave;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getUiSessionId() {
        return uiSessionId;
    }

    public void setUiSessionId(String uiSessionId) {
        this.uiSessionId = uiSessionId;
    }

    public String getParentLookUpId() {
        return parentLookUpId;
    }

    public void setParentLookUpId(String parentLookUpId) {
        this.parentLookUpId = parentLookUpId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getParentSysId() {
        return parentSysId;
    }

    public void setParentSysId(String parentSysId) {
        this.parentSysId = parentSysId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getProcessIntanceId() {
        return processIntanceId;
    }

    public void setProcessIntanceId(long processIntanceId) {
        this.processIntanceId = processIntanceId;
    }

    public WorkflowMaster getWorkflow() {
        return workflow;
    }

    public void setWorkflow(WorkflowMaster workflow) {
        this.workflow = workflow;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean isHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(Boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

   

}
