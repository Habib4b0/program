/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.common.dto;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sathyaseelan.v
 */
public class SessionDTO implements Cloneable {

    private String mode;
    private Integer sessionId;
    private Date sessionDate;
    private Integer userId;
    private String action = StringUtils.EMPTY;
    private String editOperation;
    private String userName;
    private Date currentDate;
    private String vaadinSessionId;
    private long processId;
    private int workflowId;
    private String workflowStatus;
    private String workflowUserType;
    private int noOfApproval;
    private int approvalLevel;
    private String configType;
    private String description = StringUtils.EMPTY;
    private String adjustmentType = StringUtils.EMPTY;
    private boolean workFlow;
    private int projectionId;
    private Map<String,String> currentTableNames = new LinkedHashMap<>();
    private Map<String,Map<String,String>> transactionTables = new LinkedHashMap<>();
    private String screenName;

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public SessionDTO clone() throws CloneNotSupportedException {
        SessionDTO selection = (SessionDTO) super.clone();
        selection.setSessionDate(sessionDate);
        selection.setSessionId(sessionId);
        selection.setMode(mode);
        return selection;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getProcessId() {
        return processId;
    }

    public void setProcessId(long processId) {
        this.processId = processId;
    }

    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getWorkflowUserType() {
        return workflowUserType;
    }

    public void setWorkflowUserType(String workflowUserType) {
        this.workflowUserType = workflowUserType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public boolean isWorkFlow() {
        return workFlow;
    }

    public void setWorkFlow(boolean workFlow) {
        this.workFlow = workFlow;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public String getEditOperation() {
        return editOperation;
    }

    public void setEditOperation(String editOperation) {
        this.editOperation = editOperation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getVaadinSessionId() {
        return vaadinSessionId;
    }

    public void setVaadinSessionId(String vaadinSessionId) {
        this.vaadinSessionId = vaadinSessionId;
    }

    public String getConfigType() {
        return configType;
}

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public Map<String, String> getCurrentTableNames() {
        return currentTableNames;
    }

    public void setCurrentTableNames(Map<String, String> currentTableNames) {
        this.currentTableNames = currentTableNames;
    }

    public void addCurrentTableNames(final String key, final String value) {
        currentTableNames.put(key, value);
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Map<String, Map<String, String>> getTransactionTables() {
        return transactionTables;
    }

    public void setTransactionTables(Map<String, Map<String, String>> transactionTables) {
        this.transactionTables = transactionTables;
    }

    public void addTransactionTables(final String key, final Map<String, String> value) {
        transactionTables.put(key, value);
    }
}
