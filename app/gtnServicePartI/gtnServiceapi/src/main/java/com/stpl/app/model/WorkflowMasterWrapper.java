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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link WorkflowMaster}.
 * </p>
 *
 * @author
 * @see WorkflowMaster
 * @generated
 */
@ProviderType
public class WorkflowMasterWrapper implements WorkflowMaster,
	ModelWrapper<WorkflowMaster> {
	public WorkflowMasterWrapper(WorkflowMaster workflowMaster) {
		_workflowMaster = workflowMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return WorkflowMaster.class;
	}

	@Override
	public String getModelClassName() {
		return WorkflowMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("fileSize", getFileSize());
		attributes.put("workflowStatusId", getWorkflowStatusId());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("approvalLevel", getApprovalLevel());
		attributes.put("noOfApproval", getNoOfApproval());
		attributes.put("fileName", getFileName());
		attributes.put("uploadedBy", getUploadedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("notes", getNotes());
		attributes.put("workflowMasterSid", getWorkflowMasterSid());
		attributes.put("workflowId", getWorkflowId());
		attributes.put("projectionMasterSid", getProjectionMasterSid());
		attributes.put("uploadedDate", getUploadedDate());
		attributes.put("fileType", getFileType());
		attributes.put("approvedBy", getApprovedBy());
		attributes.put("workflowDescrption", getWorkflowDescrption());
		attributes.put("approvedDate", getApprovedDate());
		attributes.put("contractMasterSid", getContractMasterSid());
		attributes.put("contractStructure", getContractStructure());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String fileSize = (String)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}

		Integer workflowStatusId = (Integer)attributes.get("workflowStatusId");

		if (workflowStatusId != null) {
			setWorkflowStatusId(workflowStatusId);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer approvalLevel = (Integer)attributes.get("approvalLevel");

		if (approvalLevel != null) {
			setApprovalLevel(approvalLevel);
		}

		Integer noOfApproval = (Integer)attributes.get("noOfApproval");

		if (noOfApproval != null) {
			setNoOfApproval(noOfApproval);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		String uploadedBy = (String)attributes.get("uploadedBy");

		if (uploadedBy != null) {
			setUploadedBy(uploadedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		String notes = (String)attributes.get("notes");

		if (notes != null) {
			setNotes(notes);
		}

		Integer workflowMasterSid = (Integer)attributes.get("workflowMasterSid");

		if (workflowMasterSid != null) {
			setWorkflowMasterSid(workflowMasterSid);
		}

		String workflowId = (String)attributes.get("workflowId");

		if (workflowId != null) {
			setWorkflowId(workflowId);
		}

		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}

		Date uploadedDate = (Date)attributes.get("uploadedDate");

		if (uploadedDate != null) {
			setUploadedDate(uploadedDate);
		}

		String fileType = (String)attributes.get("fileType");

		if (fileType != null) {
			setFileType(fileType);
		}

		Integer approvedBy = (Integer)attributes.get("approvedBy");

		if (approvedBy != null) {
			setApprovedBy(approvedBy);
		}

		String workflowDescrption = (String)attributes.get("workflowDescrption");

		if (workflowDescrption != null) {
			setWorkflowDescrption(workflowDescrption);
		}

		Date approvedDate = (Date)attributes.get("approvedDate");

		if (approvedDate != null) {
			setApprovedDate(approvedDate);
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
		return new WorkflowMasterWrapper((WorkflowMaster)_workflowMaster.clone());
	}

	@Override
	public int compareTo(WorkflowMaster workflowMaster) {
		return _workflowMaster.compareTo(workflowMaster);
	}

	/**
	* Returns the acc closure master sid of this workflow master.
	*
	* @return the acc closure master sid of this workflow master
	*/
	@Override
	public int getAccClosureMasterSid() {
		return _workflowMaster.getAccClosureMasterSid();
	}

	/**
	* Returns the approval level of this workflow master.
	*
	* @return the approval level of this workflow master
	*/
	@Override
	public int getApprovalLevel() {
		return _workflowMaster.getApprovalLevel();
	}

	/**
	* Returns the approved by of this workflow master.
	*
	* @return the approved by of this workflow master
	*/
	@Override
	public int getApprovedBy() {
		return _workflowMaster.getApprovedBy();
	}

	/**
	* Returns the approved date of this workflow master.
	*
	* @return the approved date of this workflow master
	*/
	@Override
	public Date getApprovedDate() {
		return _workflowMaster.getApprovedDate();
	}

	/**
	* Returns the contract master sid of this workflow master.
	*
	* @return the contract master sid of this workflow master
	*/
	@Override
	public int getContractMasterSid() {
		return _workflowMaster.getContractMasterSid();
	}

	/**
	* Returns the contract structure of this workflow master.
	*
	* @return the contract structure of this workflow master
	*/
	@Override
	public java.lang.String getContractStructure() {
		return _workflowMaster.getContractStructure();
	}

	/**
	* Returns the created by of this workflow master.
	*
	* @return the created by of this workflow master
	*/
	@Override
	public int getCreatedBy() {
		return _workflowMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this workflow master.
	*
	* @return the created date of this workflow master
	*/
	@Override
	public Date getCreatedDate() {
		return _workflowMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _workflowMaster.getExpandoBridge();
	}

	/**
	* Returns the file name of this workflow master.
	*
	* @return the file name of this workflow master
	*/
	@Override
	public java.lang.String getFileName() {
		return _workflowMaster.getFileName();
	}

	/**
	* Returns the file size of this workflow master.
	*
	* @return the file size of this workflow master
	*/
	@Override
	public java.lang.String getFileSize() {
		return _workflowMaster.getFileSize();
	}

	/**
	* Returns the file type of this workflow master.
	*
	* @return the file type of this workflow master
	*/
	@Override
	public java.lang.String getFileType() {
		return _workflowMaster.getFileType();
	}

	/**
	* Returns the modified by of this workflow master.
	*
	* @return the modified by of this workflow master
	*/
	@Override
	public int getModifiedBy() {
		return _workflowMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this workflow master.
	*
	* @return the modified date of this workflow master
	*/
	@Override
	public Date getModifiedDate() {
		return _workflowMaster.getModifiedDate();
	}

	/**
	* Returns the no of approval of this workflow master.
	*
	* @return the no of approval of this workflow master
	*/
	@Override
	public int getNoOfApproval() {
		return _workflowMaster.getNoOfApproval();
	}

	/**
	* Returns the notes of this workflow master.
	*
	* @return the notes of this workflow master
	*/
	@Override
	public java.lang.String getNotes() {
		return _workflowMaster.getNotes();
	}

	/**
	* Returns the primary key of this workflow master.
	*
	* @return the primary key of this workflow master
	*/
	@Override
	public int getPrimaryKey() {
		return _workflowMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workflowMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection master sid of this workflow master.
	*
	* @return the projection master sid of this workflow master
	*/
	@Override
	public int getProjectionMasterSid() {
		return _workflowMaster.getProjectionMasterSid();
	}

	/**
	* Returns the uploaded by of this workflow master.
	*
	* @return the uploaded by of this workflow master
	*/
	@Override
	public java.lang.String getUploadedBy() {
		return _workflowMaster.getUploadedBy();
	}

	/**
	* Returns the uploaded date of this workflow master.
	*
	* @return the uploaded date of this workflow master
	*/
	@Override
	public Date getUploadedDate() {
		return _workflowMaster.getUploadedDate();
	}

	/**
	* Returns the workflow descrption of this workflow master.
	*
	* @return the workflow descrption of this workflow master
	*/
	@Override
	public java.lang.String getWorkflowDescrption() {
		return _workflowMaster.getWorkflowDescrption();
	}

	/**
	* Returns the workflow ID of this workflow master.
	*
	* @return the workflow ID of this workflow master
	*/
	@Override
	public java.lang.String getWorkflowId() {
		return _workflowMaster.getWorkflowId();
	}

	/**
	* Returns the workflow master sid of this workflow master.
	*
	* @return the workflow master sid of this workflow master
	*/
	@Override
	public int getWorkflowMasterSid() {
		return _workflowMaster.getWorkflowMasterSid();
	}

	/**
	* Returns the workflow status ID of this workflow master.
	*
	* @return the workflow status ID of this workflow master
	*/
	@Override
	public int getWorkflowStatusId() {
		return _workflowMaster.getWorkflowStatusId();
	}

	@Override
	public int hashCode() {
		return _workflowMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _workflowMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _workflowMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _workflowMaster.isNew();
	}

	@Override
	public void persist() {
		_workflowMaster.persist();
	}

	/**
	* Sets the acc closure master sid of this workflow master.
	*
	* @param accClosureMasterSid the acc closure master sid of this workflow master
	*/
	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_workflowMaster.setAccClosureMasterSid(accClosureMasterSid);
	}

	/**
	* Sets the approval level of this workflow master.
	*
	* @param approvalLevel the approval level of this workflow master
	*/
	@Override
	public void setApprovalLevel(int approvalLevel) {
		_workflowMaster.setApprovalLevel(approvalLevel);
	}

	/**
	* Sets the approved by of this workflow master.
	*
	* @param approvedBy the approved by of this workflow master
	*/
	@Override
	public void setApprovedBy(int approvedBy) {
		_workflowMaster.setApprovedBy(approvedBy);
	}

	/**
	* Sets the approved date of this workflow master.
	*
	* @param approvedDate the approved date of this workflow master
	*/
	@Override
	public void setApprovedDate(Date approvedDate) {
		_workflowMaster.setApprovedDate(approvedDate);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_workflowMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the contract master sid of this workflow master.
	*
	* @param contractMasterSid the contract master sid of this workflow master
	*/
	@Override
	public void setContractMasterSid(int contractMasterSid) {
		_workflowMaster.setContractMasterSid(contractMasterSid);
	}

	/**
	* Sets the contract structure of this workflow master.
	*
	* @param contractStructure the contract structure of this workflow master
	*/
	@Override
	public void setContractStructure(java.lang.String contractStructure) {
		_workflowMaster.setContractStructure(contractStructure);
	}

	/**
	* Sets the created by of this workflow master.
	*
	* @param createdBy the created by of this workflow master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_workflowMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this workflow master.
	*
	* @param createdDate the created date of this workflow master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_workflowMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_workflowMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_workflowMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_workflowMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this workflow master.
	*
	* @param fileName the file name of this workflow master
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_workflowMaster.setFileName(fileName);
	}

	/**
	* Sets the file size of this workflow master.
	*
	* @param fileSize the file size of this workflow master
	*/
	@Override
	public void setFileSize(java.lang.String fileSize) {
		_workflowMaster.setFileSize(fileSize);
	}

	/**
	* Sets the file type of this workflow master.
	*
	* @param fileType the file type of this workflow master
	*/
	@Override
	public void setFileType(java.lang.String fileType) {
		_workflowMaster.setFileType(fileType);
	}

	/**
	* Sets the modified by of this workflow master.
	*
	* @param modifiedBy the modified by of this workflow master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_workflowMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this workflow master.
	*
	* @param modifiedDate the modified date of this workflow master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_workflowMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_workflowMaster.setNew(n);
	}

	/**
	* Sets the no of approval of this workflow master.
	*
	* @param noOfApproval the no of approval of this workflow master
	*/
	@Override
	public void setNoOfApproval(int noOfApproval) {
		_workflowMaster.setNoOfApproval(noOfApproval);
	}

	/**
	* Sets the notes of this workflow master.
	*
	* @param notes the notes of this workflow master
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_workflowMaster.setNotes(notes);
	}

	/**
	* Sets the primary key of this workflow master.
	*
	* @param primaryKey the primary key of this workflow master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_workflowMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_workflowMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection master sid of this workflow master.
	*
	* @param projectionMasterSid the projection master sid of this workflow master
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_workflowMaster.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the uploaded by of this workflow master.
	*
	* @param uploadedBy the uploaded by of this workflow master
	*/
	@Override
	public void setUploadedBy(java.lang.String uploadedBy) {
		_workflowMaster.setUploadedBy(uploadedBy);
	}

	/**
	* Sets the uploaded date of this workflow master.
	*
	* @param uploadedDate the uploaded date of this workflow master
	*/
	@Override
	public void setUploadedDate(Date uploadedDate) {
		_workflowMaster.setUploadedDate(uploadedDate);
	}

	/**
	* Sets the workflow descrption of this workflow master.
	*
	* @param workflowDescrption the workflow descrption of this workflow master
	*/
	@Override
	public void setWorkflowDescrption(java.lang.String workflowDescrption) {
		_workflowMaster.setWorkflowDescrption(workflowDescrption);
	}

	/**
	* Sets the workflow ID of this workflow master.
	*
	* @param workflowId the workflow ID of this workflow master
	*/
	@Override
	public void setWorkflowId(java.lang.String workflowId) {
		_workflowMaster.setWorkflowId(workflowId);
	}

	/**
	* Sets the workflow master sid of this workflow master.
	*
	* @param workflowMasterSid the workflow master sid of this workflow master
	*/
	@Override
	public void setWorkflowMasterSid(int workflowMasterSid) {
		_workflowMaster.setWorkflowMasterSid(workflowMasterSid);
	}

	/**
	* Sets the workflow status ID of this workflow master.
	*
	* @param workflowStatusId the workflow status ID of this workflow master
	*/
	@Override
	public void setWorkflowStatusId(int workflowStatusId) {
		_workflowMaster.setWorkflowStatusId(workflowStatusId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WorkflowMaster> toCacheModel() {
		return _workflowMaster.toCacheModel();
	}

	@Override
	public WorkflowMaster toEscapedModel() {
		return new WorkflowMasterWrapper(_workflowMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _workflowMaster.toString();
	}

	@Override
	public WorkflowMaster toUnescapedModel() {
		return new WorkflowMasterWrapper(_workflowMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _workflowMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowMasterWrapper)) {
			return false;
		}

		WorkflowMasterWrapper workflowMasterWrapper = (WorkflowMasterWrapper)obj;

		if (Objects.equals(_workflowMaster,
					workflowMasterWrapper._workflowMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public WorkflowMaster getWrappedModel() {
		return _workflowMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _workflowMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _workflowMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_workflowMaster.resetOriginalValues();
	}

	private final WorkflowMaster _workflowMaster;
}