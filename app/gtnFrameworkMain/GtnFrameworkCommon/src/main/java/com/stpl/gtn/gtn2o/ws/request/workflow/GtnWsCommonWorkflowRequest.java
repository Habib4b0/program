/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.workflow;

import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractWorkflowBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;

public class GtnWsCommonWorkflowRequest implements GtnWSRequestData {

	public GtnWsCommonWorkflowRequest() {
		super();
	}

	private GtnWsContractWorkflowBean contractBean;
	private String moduleName;
	private String workflowGeneratorPath;
	private String moduleKey;
	private String defaultValue;
	private long persistanceId;

	private int processInstanceId;
	private boolean roleMatched;

	private GtnWsWorkflowInboxBean gtnWorkflowInboxBean;

	public GtnWsContractWorkflowBean getContractBean() {
		return contractBean;
	}

	public void setContractBean(GtnWsContractWorkflowBean contractBean) {
		this.contractBean = contractBean;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getWorkflowGeneratorPath() {
		return workflowGeneratorPath;
	}

	public void setWorkflowGeneratorPath(String workflowGeneratorPath) {
		this.workflowGeneratorPath = workflowGeneratorPath;
	}

	public String getModuleKey() {
		return moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public long getPersistanceId() {
		return persistanceId;
	}

	public void setPersistanceId(long persistanceId) {
		this.persistanceId = persistanceId;
	}

	public GtnWsWorkflowInboxBean getGtnWorkflowInboxBean() {
		return gtnWorkflowInboxBean;
	}

	public void setGtnWorkflowInboxBean(GtnWsWorkflowInboxBean gtnWorkflowInboxBean) {
		this.gtnWorkflowInboxBean = gtnWorkflowInboxBean;
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


}
