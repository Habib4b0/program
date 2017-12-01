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
 * This class is a wrapper for {@link HistWorkflowMaster}.
 * </p>
 *
 * @author
 * @see HistWorkflowMaster
 * @generated
 */
@ProviderType
public class HistWorkflowMasterWrapper implements HistWorkflowMaster,
	ModelWrapper<HistWorkflowMaster> {
	public HistWorkflowMasterWrapper(HistWorkflowMaster histWorkflowMaster) {
		_histWorkflowMaster = histWorkflowMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return HistWorkflowMaster.class;
	}

	@Override
	public String getModelClassName() {
		return HistWorkflowMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdBy", getCreatedBy());
		attributes.put("fileSize", getFileSize());
		attributes.put("actionDate", getActionDate());
		attributes.put("workflowStatusId", getWorkflowStatusId());
		attributes.put("actionFlag", getActionFlag());
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

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		Integer workflowStatusId = (Integer)attributes.get("workflowStatusId");

		if (workflowStatusId != null) {
			setWorkflowStatusId(workflowStatusId);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
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
	}

	@Override
	public java.lang.Object clone() {
		return new HistWorkflowMasterWrapper((HistWorkflowMaster)_histWorkflowMaster.clone());
	}

	@Override
	public int compareTo(HistWorkflowMaster histWorkflowMaster) {
		return _histWorkflowMaster.compareTo(histWorkflowMaster);
	}

	/**
	* Returns the acc closure master sid of this hist workflow master.
	*
	* @return the acc closure master sid of this hist workflow master
	*/
	@Override
	public int getAccClosureMasterSid() {
		return _histWorkflowMaster.getAccClosureMasterSid();
	}

	/**
	* Returns the action date of this hist workflow master.
	*
	* @return the action date of this hist workflow master
	*/
	@Override
	public Date getActionDate() {
		return _histWorkflowMaster.getActionDate();
	}

	/**
	* Returns the action flag of this hist workflow master.
	*
	* @return the action flag of this hist workflow master
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histWorkflowMaster.getActionFlag();
	}

	/**
	* Returns the approval level of this hist workflow master.
	*
	* @return the approval level of this hist workflow master
	*/
	@Override
	public int getApprovalLevel() {
		return _histWorkflowMaster.getApprovalLevel();
	}

	/**
	* Returns the approved by of this hist workflow master.
	*
	* @return the approved by of this hist workflow master
	*/
	@Override
	public int getApprovedBy() {
		return _histWorkflowMaster.getApprovedBy();
	}

	/**
	* Returns the approved date of this hist workflow master.
	*
	* @return the approved date of this hist workflow master
	*/
	@Override
	public Date getApprovedDate() {
		return _histWorkflowMaster.getApprovedDate();
	}

	/**
	* Returns the created by of this hist workflow master.
	*
	* @return the created by of this hist workflow master
	*/
	@Override
	public int getCreatedBy() {
		return _histWorkflowMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this hist workflow master.
	*
	* @return the created date of this hist workflow master
	*/
	@Override
	public Date getCreatedDate() {
		return _histWorkflowMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histWorkflowMaster.getExpandoBridge();
	}

	/**
	* Returns the file name of this hist workflow master.
	*
	* @return the file name of this hist workflow master
	*/
	@Override
	public java.lang.String getFileName() {
		return _histWorkflowMaster.getFileName();
	}

	/**
	* Returns the file size of this hist workflow master.
	*
	* @return the file size of this hist workflow master
	*/
	@Override
	public java.lang.String getFileSize() {
		return _histWorkflowMaster.getFileSize();
	}

	/**
	* Returns the file type of this hist workflow master.
	*
	* @return the file type of this hist workflow master
	*/
	@Override
	public java.lang.String getFileType() {
		return _histWorkflowMaster.getFileType();
	}

	/**
	* Returns the modified by of this hist workflow master.
	*
	* @return the modified by of this hist workflow master
	*/
	@Override
	public int getModifiedBy() {
		return _histWorkflowMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist workflow master.
	*
	* @return the modified date of this hist workflow master
	*/
	@Override
	public Date getModifiedDate() {
		return _histWorkflowMaster.getModifiedDate();
	}

	/**
	* Returns the no of approval of this hist workflow master.
	*
	* @return the no of approval of this hist workflow master
	*/
	@Override
	public int getNoOfApproval() {
		return _histWorkflowMaster.getNoOfApproval();
	}

	/**
	* Returns the notes of this hist workflow master.
	*
	* @return the notes of this hist workflow master
	*/
	@Override
	public java.lang.String getNotes() {
		return _histWorkflowMaster.getNotes();
	}

	/**
	* Returns the primary key of this hist workflow master.
	*
	* @return the primary key of this hist workflow master
	*/
	@Override
	public int getPrimaryKey() {
		return _histWorkflowMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histWorkflowMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection master sid of this hist workflow master.
	*
	* @return the projection master sid of this hist workflow master
	*/
	@Override
	public int getProjectionMasterSid() {
		return _histWorkflowMaster.getProjectionMasterSid();
	}

	/**
	* Returns the uploaded by of this hist workflow master.
	*
	* @return the uploaded by of this hist workflow master
	*/
	@Override
	public java.lang.String getUploadedBy() {
		return _histWorkflowMaster.getUploadedBy();
	}

	/**
	* Returns the uploaded date of this hist workflow master.
	*
	* @return the uploaded date of this hist workflow master
	*/
	@Override
	public Date getUploadedDate() {
		return _histWorkflowMaster.getUploadedDate();
	}

	/**
	* Returns the workflow descrption of this hist workflow master.
	*
	* @return the workflow descrption of this hist workflow master
	*/
	@Override
	public java.lang.String getWorkflowDescrption() {
		return _histWorkflowMaster.getWorkflowDescrption();
	}

	/**
	* Returns the workflow ID of this hist workflow master.
	*
	* @return the workflow ID of this hist workflow master
	*/
	@Override
	public java.lang.String getWorkflowId() {
		return _histWorkflowMaster.getWorkflowId();
	}

	/**
	* Returns the workflow master sid of this hist workflow master.
	*
	* @return the workflow master sid of this hist workflow master
	*/
	@Override
	public int getWorkflowMasterSid() {
		return _histWorkflowMaster.getWorkflowMasterSid();
	}

	/**
	* Returns the workflow status ID of this hist workflow master.
	*
	* @return the workflow status ID of this hist workflow master
	*/
	@Override
	public int getWorkflowStatusId() {
		return _histWorkflowMaster.getWorkflowStatusId();
	}

	@Override
	public int hashCode() {
		return _histWorkflowMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histWorkflowMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histWorkflowMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histWorkflowMaster.isNew();
	}

	@Override
	public void persist() {
		_histWorkflowMaster.persist();
	}

	/**
	* Sets the acc closure master sid of this hist workflow master.
	*
	* @param accClosureMasterSid the acc closure master sid of this hist workflow master
	*/
	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_histWorkflowMaster.setAccClosureMasterSid(accClosureMasterSid);
	}

	/**
	* Sets the action date of this hist workflow master.
	*
	* @param actionDate the action date of this hist workflow master
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_histWorkflowMaster.setActionDate(actionDate);
	}

	/**
	* Sets the action flag of this hist workflow master.
	*
	* @param actionFlag the action flag of this hist workflow master
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histWorkflowMaster.setActionFlag(actionFlag);
	}

	/**
	* Sets the approval level of this hist workflow master.
	*
	* @param approvalLevel the approval level of this hist workflow master
	*/
	@Override
	public void setApprovalLevel(int approvalLevel) {
		_histWorkflowMaster.setApprovalLevel(approvalLevel);
	}

	/**
	* Sets the approved by of this hist workflow master.
	*
	* @param approvedBy the approved by of this hist workflow master
	*/
	@Override
	public void setApprovedBy(int approvedBy) {
		_histWorkflowMaster.setApprovedBy(approvedBy);
	}

	/**
	* Sets the approved date of this hist workflow master.
	*
	* @param approvedDate the approved date of this hist workflow master
	*/
	@Override
	public void setApprovedDate(Date approvedDate) {
		_histWorkflowMaster.setApprovedDate(approvedDate);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histWorkflowMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hist workflow master.
	*
	* @param createdBy the created by of this hist workflow master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histWorkflowMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist workflow master.
	*
	* @param createdDate the created date of this hist workflow master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histWorkflowMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histWorkflowMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histWorkflowMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histWorkflowMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file name of this hist workflow master.
	*
	* @param fileName the file name of this hist workflow master
	*/
	@Override
	public void setFileName(java.lang.String fileName) {
		_histWorkflowMaster.setFileName(fileName);
	}

	/**
	* Sets the file size of this hist workflow master.
	*
	* @param fileSize the file size of this hist workflow master
	*/
	@Override
	public void setFileSize(java.lang.String fileSize) {
		_histWorkflowMaster.setFileSize(fileSize);
	}

	/**
	* Sets the file type of this hist workflow master.
	*
	* @param fileType the file type of this hist workflow master
	*/
	@Override
	public void setFileType(java.lang.String fileType) {
		_histWorkflowMaster.setFileType(fileType);
	}

	/**
	* Sets the modified by of this hist workflow master.
	*
	* @param modifiedBy the modified by of this hist workflow master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histWorkflowMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist workflow master.
	*
	* @param modifiedDate the modified date of this hist workflow master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histWorkflowMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histWorkflowMaster.setNew(n);
	}

	/**
	* Sets the no of approval of this hist workflow master.
	*
	* @param noOfApproval the no of approval of this hist workflow master
	*/
	@Override
	public void setNoOfApproval(int noOfApproval) {
		_histWorkflowMaster.setNoOfApproval(noOfApproval);
	}

	/**
	* Sets the notes of this hist workflow master.
	*
	* @param notes the notes of this hist workflow master
	*/
	@Override
	public void setNotes(java.lang.String notes) {
		_histWorkflowMaster.setNotes(notes);
	}

	/**
	* Sets the primary key of this hist workflow master.
	*
	* @param primaryKey the primary key of this hist workflow master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_histWorkflowMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histWorkflowMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection master sid of this hist workflow master.
	*
	* @param projectionMasterSid the projection master sid of this hist workflow master
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_histWorkflowMaster.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the uploaded by of this hist workflow master.
	*
	* @param uploadedBy the uploaded by of this hist workflow master
	*/
	@Override
	public void setUploadedBy(java.lang.String uploadedBy) {
		_histWorkflowMaster.setUploadedBy(uploadedBy);
	}

	/**
	* Sets the uploaded date of this hist workflow master.
	*
	* @param uploadedDate the uploaded date of this hist workflow master
	*/
	@Override
	public void setUploadedDate(Date uploadedDate) {
		_histWorkflowMaster.setUploadedDate(uploadedDate);
	}

	/**
	* Sets the workflow descrption of this hist workflow master.
	*
	* @param workflowDescrption the workflow descrption of this hist workflow master
	*/
	@Override
	public void setWorkflowDescrption(java.lang.String workflowDescrption) {
		_histWorkflowMaster.setWorkflowDescrption(workflowDescrption);
	}

	/**
	* Sets the workflow ID of this hist workflow master.
	*
	* @param workflowId the workflow ID of this hist workflow master
	*/
	@Override
	public void setWorkflowId(java.lang.String workflowId) {
		_histWorkflowMaster.setWorkflowId(workflowId);
	}

	/**
	* Sets the workflow master sid of this hist workflow master.
	*
	* @param workflowMasterSid the workflow master sid of this hist workflow master
	*/
	@Override
	public void setWorkflowMasterSid(int workflowMasterSid) {
		_histWorkflowMaster.setWorkflowMasterSid(workflowMasterSid);
	}

	/**
	* Sets the workflow status ID of this hist workflow master.
	*
	* @param workflowStatusId the workflow status ID of this hist workflow master
	*/
	@Override
	public void setWorkflowStatusId(int workflowStatusId) {
		_histWorkflowMaster.setWorkflowStatusId(workflowStatusId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistWorkflowMaster> toCacheModel() {
		return _histWorkflowMaster.toCacheModel();
	}

	@Override
	public HistWorkflowMaster toEscapedModel() {
		return new HistWorkflowMasterWrapper(_histWorkflowMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histWorkflowMaster.toString();
	}

	@Override
	public HistWorkflowMaster toUnescapedModel() {
		return new HistWorkflowMasterWrapper(_histWorkflowMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histWorkflowMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistWorkflowMasterWrapper)) {
			return false;
		}

		HistWorkflowMasterWrapper histWorkflowMasterWrapper = (HistWorkflowMasterWrapper)obj;

		if (Objects.equals(_histWorkflowMaster,
					histWorkflowMasterWrapper._histWorkflowMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public HistWorkflowMaster getWrappedModel() {
		return _histWorkflowMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histWorkflowMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histWorkflowMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histWorkflowMaster.resetOriginalValues();
	}

	private final HistWorkflowMaster _histWorkflowMaster;
}