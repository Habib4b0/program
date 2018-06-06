/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.workflow;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.response.GtnWSResponseData;

public class GtnWsCommonWorkflowResponse implements GtnWSResponseData {

    public GtnWsCommonWorkflowResponse() {
        super();
    }

    private String workflowId;
    private boolean hasPermission;
    private long persistanceId;
    private int processInstanceId;
    private boolean roleMatched;
    private List<String> roleList;
    private String processVariable;
    private List<GtnWsRecordBean> resultList = new ArrayList<>();
	private String friendlyUrl;

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public boolean isHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public long getPersistanceId() {
        return persistanceId;
    }

    public void setPersistanceId(long persistanceId) {
        this.persistanceId = persistanceId;
    }

    public List<GtnWsRecordBean> getResultList() {
        return resultList == null ? resultList : new ArrayList<>(resultList);
    }

    public void setResultList(List<GtnWsRecordBean> resultList) {
        this.resultList = resultList == null ? resultList : new ArrayList<>(resultList);
    }

    public int getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(int processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public boolean isRoleMatched() {
        return roleMatched;
    }

    public void setRoleMatched(boolean roleMatched) {
        this.roleMatched = roleMatched;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public String getProcessVariable() {
        return processVariable;
    }

    public void setProcessVariable(String processVariable) {
        this.processVariable = processVariable;
    }

	public String getFriendlyUrl() {
		return friendlyUrl;
	}

	public void setFriendlyUrl(String friendlyUrl) {
		this.friendlyUrl = friendlyUrl;
	}

}
