/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link WorkflowProcessInfo}.
 * </p>
 *
 * @author
 * @see WorkflowProcessInfo
 * @generated
 */
@ProviderType
public class WorkflowProcessInfoWrapper implements WorkflowProcessInfo,
	ModelWrapper<WorkflowProcessInfo> {
	public WorkflowProcessInfoWrapper(WorkflowProcessInfo workflowProcessInfo) {
		_workflowProcessInfo = workflowProcessInfo;
	}

	@Override
	public Class<?> getModelClass() {
		return WorkflowProcessInfo.class;
	}

	@Override
	public String getModelClassName() {
		return WorkflowProcessInfo.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("processInstanceId", getProcessInstanceId());
		attributes.put("projectionMasterSid", getProjectionMasterSid());
		attributes.put("workflowProcessInfoSid", getWorkflowProcessInfoSid());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("contractStructure", getContractStructure());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer processInstanceId = (Integer)attributes.get("processInstanceId");

		if (processInstanceId != null) {
			setProcessInstanceId(processInstanceId);
		}

		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}

		Integer workflowProcessInfoSid = (Integer)attributes.get(
				"workflowProcessInfoSid");

		if (workflowProcessInfoSid != null) {
			setWorkflowProcessInfoSid(workflowProcessInfoSid);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		Integer contractMasterSid = (Integer)attributes.get("contractMasterSid");

		if (contractMasterSid != null) {
			setContractMasterSid(contractMasterSid);
		}

		String contractStructure = (String)attributes.get("contractStructure");

		if (contractStructure != null) {
			setContractStructure(contractStructure);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WorkflowProcessInfoWrapper((WorkflowProcessInfo)_workflowProcessInfo.clone());
	}

	@Override
	public int compareTo(WorkflowProcessInfo workflowProcessInfo) {
		return _workflowProcessInfo.compareTo(workflowProcessInfo);
	}

	/**
	* Returns the acc closure master sid of this workflow process info.
	*
	* @return the acc closure master sid of this workflow process info
	*/
	@Override
	public int getAccClosureMasterSid() {
		return _workflowProcessInfo.getAccClosureMasterSid();
	}

	/**
	* Returns the contract master sid of this workflow process info.
	*
	* @return the contract master sid of this workflow process info
	*/
	@Override
	public int getContractMasterSid() {
		return _workflowProcessInfo.getContractMasterSid();
	}

	/**
	* Returns the contract structure of this workflow process info.
	*
	* @return the contract structure of this workflow process info
	*/
	@Override
	public java.lang.String getContractStructure() {
		return _workflowProcessInfo.getContractStructure();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _workflowProcessInfo.getExpandoBridge();
	}

	/**
	* Returns the primary key of this workflow process info.
	*
	* @return the primary key of this workflow process info
	*/
	@Override
	public int getPrimaryKey() {
		return _workflowProcessInfo.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workflowProcessInfo.getPrimaryKeyObj();
	}

	/**
	* Returns the process instance ID of this workflow process info.
	*
	* @return the process instance ID of this workflow process info
	*/
	@Override
	public int getProcessInstanceId() {
		return _workflowProcessInfo.getProcessInstanceId();
	}

	/**
	* Returns the projection master sid of this workflow process info.
	*
	* @return the projection master sid of this workflow process info
	*/
	@Override
	public int getProjectionMasterSid() {
		return _workflowProcessInfo.getProjectionMasterSid();
	}

	/**
	* Returns the workflow process info sid of this workflow process info.
	*
	* @return the workflow process info sid of this workflow process info
	*/
	@Override
	public int getWorkflowProcessInfoSid() {
		return _workflowProcessInfo.getWorkflowProcessInfoSid();
	}

	@Override
	public int hashCode() {
		return _workflowProcessInfo.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _workflowProcessInfo.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _workflowProcessInfo.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _workflowProcessInfo.isNew();
	}

	@Override
	public void persist() {
		_workflowProcessInfo.persist();
	}

	/**
	* Sets the acc closure master sid of this workflow process info.
	*
	* @param accClosureMasterSid the acc closure master sid of this workflow process info
	*/
	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_workflowProcessInfo.setAccClosureMasterSid(accClosureMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workflowProcessInfo.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract master sid of this workflow process info.
	*
	* @param contractMasterSid the contract master sid of this workflow process info
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_workflowProcessInfo.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the contract structure of this workflow process info.
	*
	* @param contractStructure the contract structure of this workflow process info
	*/
	@Override
	public void setContractStructure(java.lang.String contractStructure) {
		_workflowProcessInfo.setContractStructure(contractStructure);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_workflowProcessInfo.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_workflowProcessInfo.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_workflowProcessInfo.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_workflowProcessInfo.setNew(n);
	}

	/**
	* Sets the primary key of this workflow process info.
	*
	* @param primaryKey the primary key of this workflow process info
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_workflowProcessInfo.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_workflowProcessInfo.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the process instance ID of this workflow process info.
	*
	* @param processInstanceId the process instance ID of this workflow process info
	*/
	@Override
	public void setProcessInstanceId(int processInstanceId) {
		_workflowProcessInfo.setProcessInstanceId(processInstanceId);
	}

	/**
	* Sets the projection master sid of this workflow process info.
	*
	* @param projectionMasterSid the projection master sid of this workflow process info
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_workflowProcessInfo.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the workflow process info sid of this workflow process info.
	*
	* @param workflowProcessInfoSid the workflow process info sid of this workflow process info
	*/
	@Override
	public void setWorkflowProcessInfoSid(int workflowProcessInfoSid) {
		_workflowProcessInfo.setWorkflowProcessInfoSid(workflowProcessInfoSid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WorkflowProcessInfo> toCacheModel() {
		return _workflowProcessInfo.toCacheModel();
	}

	@Override
	public WorkflowProcessInfo toEscapedModel() {
		return new WorkflowProcessInfoWrapper(_workflowProcessInfo.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _workflowProcessInfo.toString();
	}

	@Override
	public WorkflowProcessInfo toUnescapedModel() {
		return new WorkflowProcessInfoWrapper(_workflowProcessInfo.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _workflowProcessInfo.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowProcessInfoWrapper)) {
			return false;
		}

		WorkflowProcessInfoWrapper workflowProcessInfoWrapper = (WorkflowProcessInfoWrapper)obj;

		if (Objects.equals(_workflowProcessInfo,
					workflowProcessInfoWrapper._workflowProcessInfo)) {
			return true;
		}

		return false;
	}

	@Override
	public WorkflowProcessInfo getWrappedModel() {
		return _workflowProcessInfo;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _workflowProcessInfo.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _workflowProcessInfo.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_workflowProcessInfo.resetOriginalValues();
	}

	private final WorkflowProcessInfo _workflowProcessInfo;
}